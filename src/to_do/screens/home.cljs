(ns to-do.screens.home
  (:require [to-do.imports :refer [view text]]))

(defn home-screen [props]
  [view {:style {:flex 1 :justify-content "center" :align-items "center"}}
   [text "Hello"]])