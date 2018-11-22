(ns to-do.common.components
  (:require [to-do.imports :refer [view text touchable-opacity text-input icon width height StatusBar Keyboard button]]
            [to-do.utils.animation.core :refer [animated-view start-animation interpolate timing]]
            [to-do.common.styles :refer [styles]]))

(defn header [{:keys [title color on-press icon-name]}]
  [view {:style (:header styles)}
   [text {:style (merge (:title styles) {:color color})} title]
   [touchable-opacity {:on-press #(on-press) :hit-slop {:top 25 :right 25 :bottom 25 :left 25}}
    [icon {:name icon-name :size 32 :color color}]]])

(defn form-modal [{:keys [ani-val toggle-state input-val form-text on-complete background-color primary-color]}]
  ; Toggle's callback function receives a boolean argument, signifying whether or not the animation finished
  (let [toggle (fn [callback] (do (start-animation (timing ani-val (if @toggle-state 0 1) 350) callback)
                                  (.setBarStyle StatusBar
                                                (if @toggle-state
                                                  (if (= background-color "blue") "dark-content" "light-content")
                                                  (if (= background-color "blue") "light-content" "dark-content")))
                                  (reset! toggle-state (not @toggle-state))
                                  (.dismiss Keyboard)))
        empty-input? (clojure.string/blank? @input-val)]
    [animated-view {:style (merge (:add-entry-container styles)
                                  {:width  width
                                   :height (interpolate ani-val [0 1] [50 height])})}
     [animated-view {:style (merge (:entry-form-container styles)
                                   {:background-color background-color
                                    :transform        [{:translateY (interpolate ani-val [0 1] [height 0])}]})}
      [text {:style (merge (:form-title styles) {:color primary-color})} form-text]
      [text-input {:value                   @input-val
                   :clear-button-mode       "while-editing"
                   :underline-color-android "rgba(0, 0, 0, 0)"
                   :on-change-text          #(reset! input-val %)
                   :on-submit-editing       #(if empty-input? (.dismiss Keyboard) (toggle on-complete))
                   :style                   (merge (:form-input styles) {:border-color primary-color})}]
      [touchable-opacity {:on-press #(toggle on-complete)
                          :disabled empty-input?}
       [view {:style (merge (:form-button styles)
                            {:border-color primary-color
                             :opacity      (if empty-input? 0.5 1)})}
        [text {:style (merge (:form-button-text styles)
                             {:color primary-color})} "Save"]]]]
     [touchable-opacity {:style    (merge (:add-toggle-button styles) {:background-color background-color})
                         :on-press #(toggle nil)}
      [animated-view {:style {:transform [{:rotate (interpolate ani-val [0 1] ["0deg" "45deg"])}]}}
       [icon {:name "ios-add" :size 48 :color primary-color}]]]]))

(defn overlay [ani-val]
  [animated-view {:style          (merge (:overlay styles) {:opacity (interpolate ani-val [0 1] [0 0.333])})
                  :pointer-events "none"}])
