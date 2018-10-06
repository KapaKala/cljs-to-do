(ns to-do.core
    (:require [reagent.core :as r]
              [re-frame.core :refer [dispatch-sync]]
              [oops.core :refer [ocall]]
              [to-do.handlers]
              [to-do.imports :refer [expo]]
              [to-do.routing :refer [routing]]
              [to-do.utils.sqlite.core :as sql]))

(defn app-root []
 [(r/adapt-react-class (routing))])

(defn init []
  (dispatch-sync [:initialize-db])
  (do (sql/create-table)
      (sql/get-entries))
  (ocall expo "registerRootComponent" (r/reactify-component app-root)))
