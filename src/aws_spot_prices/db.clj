(ns aws-spot-prices.db
  (:require [clojure.data.json :as json])
  (:use korma.core)
  (:use korma.db)
  (:use [clojure.tools.logging :only (info error)]))

;; (defn dump-request [filters]
;;   (info "dumping...")
;;   (with-out-str (clojure.pprint/pprint filters)))


(defn get-korma-fn [fn-name]
  (ns-resolve 'korma.core (symbol fn-name)))

(defn apply-filter [query filter-name-val]
  (let [[filter-name filter-val] filter-name-val]
    (condp = filter-name
      "limit" ((get-korma-fn filter-name) query filter-val)
      "offset" ((get-korma-fn filter-name) query filter-val)
      "startDate" (where query (>= :timestamp filter-val))
      "endDate" (where query (<= :timestamp filter-val))
      "sortField" (order query (keyword filter-val)))))

(defn apply-filters [selector-base filters]
  (loop [selector selector-base
         filters filters]
    (if (empty? filters)
      selector
      (recur (apply-filter selector (first filters)) (rest filters)))))

(defn db-spot-prices2 [filters]
  (defdb sqll (sqlite3 { :db "dev.db"}))
  (defentity PriceObjects)
  (json/write-str
   (select (apply-filters (select* PriceObjects)
                          filters))))
