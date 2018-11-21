(ns to-do.screens.settings.components
  (:require [re-frame.core :refer [dispatch subscribe]]
            [to-do.imports :refer [view text icon touchable-opacity scroll-view]]
            [to-do.utils.animation.core :refer [animated-view animated start-animation timing]]
            [to-do.screens.settings.styles :refer [styles]]))

(defn render-table [table current-table]
  [touchable-opacity {:on-press #(dispatch [:set-current-table (:name table)])}
   [animated-view {:style (:table-container styles)}
    [text {:style {:font-family (if (= (:name table) current-table) "roboto-mono-bold" "roboto-mono-regular") :font-size 18 :color "white"}} (:name table)]
    (if (= (:name table) "to_do")
      [text {:style {:font-family "roboto-mono-regular" :color "white"}} "default"]
      [touchable-opacity {:hit-slop {:top 25 :right 25 :bottom 25 :left 25} :on-press #(dispatch [:delete-table (:name table)])}
       [icon {:color "white" :size 32 :name "ios-close"}]])]])
