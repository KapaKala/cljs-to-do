(ns to-do.screens.home
  (:require [re-frame.core :refer [dispatch subscribe]]
    [to-do.imports :refer [safe-area-view text button]]
            [goog.object :as goog]
            [to-do.utils.sqlite.actions]
            [to-do.utils.sqlite.subs]
            [to-do.utils.sqlite.core :as sql]))

(defn home-screen [props]
  (let [navigate (.. (clj->js props) -navigation -navigate)
        entries (subscribe [:get-entries])]
    [safe-area-view {:style {:flex 1 :justify-content "center" :align-items "center" :background-color "white"}}
     [button {:title "Navigate" :on-press #(navigate "Settings")}]
     [button {:title "Add entry" :on-press #(dispatch [:add-entry "asd"])}]
     [button {:title "Clear entries" :on-press #(sql/clear-entries)}]
     [text "Hello from home"]
     [text (str @entries)]]))