(ns to-do.common.components
  (:require [to-do.imports :refer [view text touchable-opacity icon]]
            [to-do.common.styles :refer [styles]]))

(defn header [{:keys [title color on-press icon-name]}]
  [view {:style (:header styles)}
   [text {:style (merge (:title styles) {:color color})} title]
   [touchable-opacity {:on-press #(on-press)}
    [icon {:name icon-name :size 32 :color color}]]])
