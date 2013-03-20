(ns aws-spot-prices.db
  (:require [clojure.data.json :as json])
  (:use korma.core)
  (:use korma.db)
  (:use [clojure.tools.logging :only (info error)]))

;; (defn dump-request [filters]
;;   (info "dumping...")
;;   (with-out-str (clojure.pprint/pprint filters)))

(defn db-spot-prices2 [filters]
  (defdb sqll (sqlite3 { :db "dev.db"}))
  (defentity PriceObjects)
  (json/write-str
   (select (apply-filters (select* PriceObjects)
                          filters))))

(defn apply-filters [selector-base filters]
  (loop [selector selector-base
         filters filters]
    (if (empty? filters)
      selector
      (recur (apply-filter selector (first filters)) (rest filters)))))

(defn apply-filter [korma-selector filter]
  (info filter)
  ((ns-resolve 'korma.core (symbol (filter 0)))
   korma-selector (filter 1)))

;; (defn db-spot-prices []
;;   (defdb sqll (sqlite3 { :db "dev.db" }))
;;   (defentity PriceObjects)
;;   (json/write-str
;;    (select PriceObjects (where { :timestamp [>= "2013-01-02"]}) (limit 2) (offset 3))))
