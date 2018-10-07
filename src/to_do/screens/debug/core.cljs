(ns to-do.screens.debug.core
  (:require [re-frame.core :refer [dispatch subscribe]]
            [to-do.imports :refer [safe-area-view text button view scroll-view]]
            [goog.object :as goog]
            [to-do.utils.sqlite.actions]
            [to-do.utils.sqlite.subs]
            [to-do.utils.sqlite.core :as sql]))

(def styles {:container       {:flex 1 :justify-content "center" :align-items "center" :background-color "white"}
             :content-wrapper {:justify-content "center" :align-items "center"}
             :content         {:flex-direction "row"}
             :title           {:font-size 16 :font-weight "bold"}})

(defn debug-screen [props]
  (let [navigate (.. (clj->js props) -navigation -navigate)
        entries (subscribe [:get-entries])]
    [safe-area-view {:style (:container styles)}
     [view {:style (:content-wrapper styles)}
      [text {:style (:title styles)} "Screens"]
      [view {:style (:content styles)}
       [button {:title "Home" :on-press #(navigate "Home")}]
       [button {:title "Settings" :on-press #(navigate "Settings")}]]]
     [view {:style (:content-wrapper styles)}
      [text {:style (:title styles)} "Actions"]
      [view {:style (:content styles)}
       [button {:title "Add entry" :on-press #(dispatch [:add-entry "asd"])}]
       [button {:title "Clear entries" :on-press #(sql/clear-entries)}]]]
     [scroll-view
      [text (str @entries)]]]))