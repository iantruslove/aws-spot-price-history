(ns aws-spot-prices.core
  (:use aws-spot-prices.db)
  (:use ring.adapter.jetty)
  (:use ring.middleware.reload)
  (:use ring.middleware.params)
  (:use ring.middleware.stacktrace)
  (:use [clojure.tools.logging :only (info error)]))


(defn handler [request]
  {:status 200
   :headers {"Content-type" "application/json"
             "Access-Control-Allow-Origin" "*" }
   :body (db-spot-prices2 (:params request) ) })

(def app
  (-> handler
      (wrap-stacktrace)
      (wrap-params)
      (wrap-reload '(aws-spot-prices.core))
      ))

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
