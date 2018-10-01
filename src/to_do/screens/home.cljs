(ns to-do.screens.home
  (:require [to-do.imports :refer [safe-area-view text button]]
            [goog.object :as goog]))

(defn home-screen [props]
  (let [navigate (.. (clj->js props) -navigation -navigate)]
    [safe-area-view {:style {:flex 1 :justify-content "center" :align-items "center" :background-color "white"}}
     [button {:title "Navigate" :on-press #(navigate "Settings")}]
     [text "Hello from home"]]))