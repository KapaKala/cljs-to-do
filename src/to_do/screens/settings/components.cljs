(ns to-do.screens.settings.components
  (:require [re-frame.core :refer [dispatch subscribe]]
            [reagent.core :as r]
            [to-do.imports :refer [view text icon touchable-opacity scroll-view]]
            [to-do.utils.animation.core :refer [animated-view animated-value animated-text
                                                start-animation interpolate timing sequence]]
            [to-do.screens.settings.styles :refer [styles]]))

(defn render-table [table current-table]
  (let [opacity-animation (animated-value 0)
        height-animation (animated-value 1)
        confirm-animation (animated-value 0)
        confirm (r/atom false)
        delet-this #(start-animation (sequence [(timing opacity-animation 0 250)
                                                (timing height-animation 0 250)])
                                     (fn [invocation] (when invocation (dispatch [:delete-table (:name table)]))))]
    (r/create-class
      {:component-did-mount
       (fn [] (start-animation (timing opacity-animation 1 250) #()))
       :reagent-render
       (fn [table current-table]
         [touchable-opacity {:on-press #(dispatch [:set-current-table (:name table)])}
          [animated-view {:style (merge (:table-container styles)
                                        {:opacity          opacity-animation
                                         :height           (interpolate height-animation [0 1] [0 60])
                                         :padding-vertical (interpolate height-animation [0 1] [0 12.5])})}
           [text {:style (merge (:text styles)
                                {:font-size 18}
                                (when (= (:name table) current-table) {:font-family "roboto-mono-bold"}))}
            (:name table)]
           (if (= (:name table) "to_do")
             [text {:style {:font-family "roboto-mono-regular" :color "white"}} "default"]
             [view {:style {:flex-direction "row" :align-items "center"}}
              [animated-view {:style {:opacity confirm-animation :flex-direction "row" :align-items "center" :padding-right 25}}
               [text {:style (merge (:text styles) {:padding-right 25})} "Confirm"]
               [touchable-opacity {:hit-slop {:top 10 :right 10 :bottom 10 :left 10}
                                   :on-press #(delet-this) :disabled (not @confirm)}
                [icon {:color "white" :size 32 :name "ios-checkmark-circle-outline"}]]]
              [touchable-opacity {:hit-slop {:top 10 :right 10 :bottom 10 :left 10}
                                  :on-press #(do (start-animation (timing confirm-animation (if @confirm 0 1) 250) nil)
                                                 (reset! confirm (not @confirm)))}
               [icon {:color "white" :size 32 :name "ios-close-circle-outline"}]]])]])})))
