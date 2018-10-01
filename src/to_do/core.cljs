(ns to-do.core
    (:require [reagent.core :as r]
              [re-frame.core :refer [dispatch-sync]]
              [oops.core :refer [ocall]]
              [to-do.handlers]
              [to-do.imports :refer [expo]]
              [to-do.routing :refer [routing]]))

(defn app-root []
 [(r/adapt-react-class (routing))])

(defn init []
  (dispatch-sync [:initialize-db])
  (ocall expo "registerRootComponent" (r/reactify-component app-root)))
