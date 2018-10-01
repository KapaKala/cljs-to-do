(ns to-do.screens.settings
  (:require [to-do.imports :refer [view text]]))

(defn settings-screen [props]
  [view {:style {:justify-content "center" :align-items "center"}}
   [text "Hello from settings"]])
