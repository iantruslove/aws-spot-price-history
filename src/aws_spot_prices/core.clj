(ns aws-spot-prices.core
  (:use aws-spot-prices.db)
  (:use ring.adapter.jetty)
  (:use ring.middleware.reload)
  (:use ring.middleware.stacktrace))

(defn handler [req]
  ("oops"))

(defn handler2 [request]
  {:status 200
   :headers {"Content-type" "text/plain"}
   :body (str "Request:\n\n"
              (with-out-str (clojure.pprint/pprint request))) })

(defn handler3 [request]
  {:status 200
   :headers {"Content-type" "application/json"
             "Access-Control-Allow-Origin" "*"}
   :body (spot-prices) })

(def app
  (-> #'handler3
    (wrap-reload '(aws-spot-prices.core))
    (wrap-stacktrace)))

(defn boot []
  (run-jetty #'app {:port 8080}))
