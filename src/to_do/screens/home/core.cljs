(ns to-do.screens.home.core
  (:require [to-do.imports :refer [safe-area-view view text]]
            ))

(defn home-screen [props]
  [safe-area-view
   [text "Hello from home"]])
