(ns to-do.core
    (:require [reagent.core :as r :refer [atom]]
              [re-frame.core :refer [subscribe dispatch dispatch-sync]]
              [oops.core :refer [ocall]]
              [to-do.handlers]
              [to-do.subs]
              [to-do.imports :refer [expo]]
              [to-do.screens.home :refer [home-screen]]))

(def ReactNative (js/require "react-native"))

(defn app-root []
 [home-screen])

(defn init []
  (dispatch-sync [:initialize-db])
  (ocall expo "registerRootComponent" (r/reactify-component app-root)))
