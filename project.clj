(defproject aws-spot-prices "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.8.3"]
            [lein-swank "1.4.5"]]
  :ring { :handler aws-spot-prices.core/app }
  :dev-dependencies [[midje "1.5.0"]]
  :main aws-spot-prices.core
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/data.json "0.2.1"]
                 [compojure "1.1.5"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [korma "0.3.0-beta9"]
                 [ring "1.1.8"]
                 [ring-cors "0.1.0"]
                 [org.clojure/tools.logging "0.2.6"]
                 ])
