(ns aws-spot-prices.db
  (:require [clojure.data.json :as json])
  (:use korma.core)
  (:use korma.db))

(defn spot-prices [] (
   (defdb sqll (sqlite3 { :db "dev.db" }))
   (defentity PriceObjects)
   (json/write-str
     (select PriceObjects (limit 5)))))
