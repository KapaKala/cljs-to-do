(ns to-do.screens.settings.core
  (:require [to-do.imports :refer [safe-area-view view text]]))

(defn settings-screen [props]
  [safe-area-view {:style {:justify-content "center" :align-items "center"}}
   [text "Hello from settings"]])
