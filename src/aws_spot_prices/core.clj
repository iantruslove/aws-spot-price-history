(ns aws-spot-prices.core
  (:use aws-spot-prices.db)
  (:use ring.adapter.jetty)
  (:use ring.middleware.reload)
  (:use ring.middleware.params)
  (:use ring.middleware.stacktrace)
  (:use [clojure.tools.logging :only (info error)]))

(defn ok [data]
  {:status 200
   :headers {"Content-type" "application/json"
             "Access-Control-Allow-Origin" "*" }
   :body data })

(defn handler [request]
  (ok (db-spot-prices (:params request))))

(def app
  (-> handler
      (wrap-stacktrace)
      (wrap-params)
      (wrap-reload '(aws-spot-prices.core))
      ))

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
