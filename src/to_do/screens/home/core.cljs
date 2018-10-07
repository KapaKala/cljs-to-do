(ns to-do.screens.home.core
  (:require [re-frame.core :refer [subscribe dispatch]]
            [to-do.imports :refer [safe-area-view view scroll-view text icon touchable-opacity]]
            [to-do.utils.sqlite.subs]
            [to-do.screens.home.styles :refer [styles]]))

(defn render-entry [entry]
  ^{:key entry} [view {:style (:entry-container styles)}
                 [text {:style {:color "grey" :font-family "roboto-mono-regular"}} (:id entry)]
                 [text {:style (:entry-text styles)} (:value entry)]])

(defn home-screen [props]
  (let [navigate (.. (clj->js props) -navigation -navigate)
        entries (subscribe [:get-entries])]
    [safe-area-view {:style (:container styles)}
     [view {:style (:header styles)}
      [text {:style (:title styles)} "To-do"]
      [touchable-opacity {:on-press #(navigate "Settings")}
       [icon {:name "ios-cog" :size 32}]]]
     [scroll-view {:style {:width "95%"} :content-container-style {:justify-content "center"}}
      (map render-entry @entries)]]))
