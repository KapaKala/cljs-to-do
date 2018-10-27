(ns to-do.screens.home.components
  (:require [re-frame.core :refer [dispatch]]
            [to-do.utils.sqlite.actions]
            [to-do.imports :refer [view text touchable-opacity text-input icon width height Keyboard]]
            [to-do.utils.animation.core :refer [animated-view animated-value interpolate timing start-animation]]
            [to-do.screens.home.styles :refer [styles]]
            [reagent.core :as r]))

(defn render-entry [entry]
  ^{:key (:id entry)} [view {:style (:entry-container styles)}
                       [text {:style {:color "grey" :font-family "roboto-mono-regular"}} (:id entry)]
                       [text {:style (:entry-text styles)} (:value entry)]])

(defn header [navigate]
  [view {:style (:header styles)}
   [text {:style (:title styles)} "To-do"]
   [touchable-opacity {:on-press #(navigate "Settings")}
    [icon {:name "ios-cog" :size 32}]]])


(defn add-entry-form [props]
  (let [ani-val (:ani-val props)
        toggle (:toggle props)
        input-val (:input-val props)
        save-input #(toggle (fn [invocation] (when invocation (do (dispatch [:add-entry @input-val])
                                                                  (reset! input-val nil)))))]
    [animated-view {:style (merge (:entry-form-container styles) {:transform [{:translateY (interpolate ani-val [0 1] [(* height 0.666) 0])}]})}
     [text {:style (:form-title styles)} "What would you like to remember?"]
     [text {:style (:form-title styles)} @input-val]
     [text-input {:value             @input-val
                  :on-change-text    #(reset! input-val %)
                  :on-submit-editing #(when (some? @input-val) (save-input))
                  :style             (:form-input styles)}]
     [touchable-opacity {:on-press #(save-input)
                         :disabled (nil? @input-val)
                         :style    (if (some? @input-val) (:form-button styles) (:form-button-disabled styles))}
      [text {:style (if (some? @input-val) (:form-button-text styles) (:form-button-text-disabled styles))} "Save"]]]))

(defn add-entry-thing [props]
  (let [ani-val (animated-value 0)
        input-val (r/atom "kakka")
        toggle-state (r/atom false)
        toggle (fn [cb] (do (start-animation (timing ani-val (if @toggle-state 0 1) 250) cb)
                            (reset! toggle-state (not @toggle-state))
                            (.dismiss Keyboard)))]
    [animated-view {:style (merge (:add-entry-container styles) {:width
                                                                 width
                                                                 :height
                                                                 (interpolate ani-val [0 1] [50 (* height 0.666)])})}
     [add-entry-form {:ani-val ani-val :input-val input-val :toggle toggle}]
     [touchable-opacity {:style (:add-toggle-button styles) :on-press #(toggle)}
      [animated-view {:style {:transform [{:rotate (interpolate ani-val [0 1] ["0deg" "45deg"])}]}}
       [icon {:name "ios-add" :size 48 :color "white"}]]]]))