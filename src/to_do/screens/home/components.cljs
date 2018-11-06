(ns to-do.screens.home.components
  (:require [re-frame.core :refer [dispatch]]
            [to-do.utils.sqlite.actions]
            [to-do.imports :refer [view text touchable-opacity text-input icon width height Keyboard StatusBar]]
            [to-do.utils.animation.core :refer [animated animated-view animated-value animated-text interpolate timing start-animation sequence parallel]]
            [to-do.screens.home.styles :refer [styles]]
            [reagent.core :as r]))

(defn render-entry [entry]
  (let [last-tap (r/atom nil)
        completion-animation (animated-value (if (= 1 (:done entry)) 0.9 1))
        fade-animation (animated-value 0)
        height-animation (animated-value 100)
        padding-animation (animated-value 1)
        max-height (r/atom nil)
        set-height (fn [height] (do (start-animation (timing height-animation height 1) #())
                                    (reset! max-height height)))
        delet-this (fn [invocation] (when invocation (dispatch [:delete-entry (:id entry)])))
        complete-entry (fn [invocation] (when invocation (dispatch [:complete-entry (:id entry)])))
        handle-tap (fn [done] (if (and (some? @last-tap) (< (- (.now js/Date) @last-tap) 300))
                                (if (= done 1)
                                  (start-animation (sequence [(timing fade-animation 0 250)
                                                              (parallel [(timing height-animation 0 250)
                                                                         (timing padding-animation 0 250)])])
                                                   (fn [invocation] (delet-this invocation)))
                                  (do (start-animation
                                        (timing completion-animation 0.9 250)
                                        (fn [invocation] (complete-entry invocation)))
                                      (reset! last-tap nil)))
                                (reset! last-tap (.now js/Date))))
        multiplied-animation (.multiply animated fade-animation completion-animation)
        completed-style {:opacity          (interpolate multiplied-animation [0 0.9 1] [0 0.7 1])
                         :margin-bottom    (interpolate padding-animation [0 1] ["0%" "2.5%"])
                         :padding-vertical (interpolate padding-animation [0 1] [0 25])
                         :transform        [{:scale completion-animation}]}
        text-animation {:opacity fade-animation}]
    (r/create-class
      {:display-name
       "render-entry"
       :component-did-mount
       (fn [] (start-animation (timing fade-animation 1 250) #()))
       :reagent-render
       (let [] (fn [entry] [touchable-opacity {:on-press #(handle-tap (:done entry))}
                            [animated-view {:style    (merge (:entry-container styles) (when (not (nil? @max-height)) {:height height-animation}) completed-style)
                                            :onLayout #(when (nil? @max-height) (set-height (.. % -nativeEvent -layout -height)))}
                             [animated-text {:style (merge {:color "grey" :font-family "roboto-mono-regular"} text-animation)} (:id entry)]
                             [animated-text {:style (merge (:entry-text styles) text-animation)} (:value entry)]]]))})))

(defn add-entry-form [props]
  (let [ani-val (:ani-val props)
        toggle (:toggle props)
        input-val (:input-val props)
        save-input #(toggle (fn [invocation] (when invocation (do (dispatch [:add-entry @input-val])
                                                                  (reset! input-val nil)))))]
    [animated-view {:style (merge (:entry-form-container styles) {:transform [{:translateY (interpolate ani-val [0 1] [height 0])}]})}
     [text {:style (:form-title styles)} "What would you like to remember?"]
     [text-input {:value             @input-val
                  :on-change-text    #(reset! input-val %)
                  :on-submit-editing #(when (some? @input-val) (save-input))
                  :style             (:form-input styles)}]
     [touchable-opacity {:on-press #(save-input)
                         :disabled (nil? @input-val)
                         :style    (if (some? @input-val) (:form-button styles) (:form-button-disabled styles))}
      [text {:style (if (some? @input-val) (:form-button-text styles) (:form-button-text-disabled styles))} "Save"]]]))

(defn add-entry-thing [props]
  (let [ani-val props
        input-val (r/atom nil)
        toggle-state (r/atom false)
        toggle (fn [cb] (do (start-animation (timing ani-val (if @toggle-state 0 1) 250) cb)
                            (.setBarStyle StatusBar (if @toggle-state "dark-content" "light-content"))
                            (reset! toggle-state (not @toggle-state))
                            (.dismiss Keyboard)))]
    [animated-view {:style (merge (:add-entry-container styles) {:width  width
                                                                 :height (interpolate ani-val [0 1] [50 height])})}
     [add-entry-form {:ani-val ani-val :input-val input-val :toggle toggle}]
     [touchable-opacity {:style (:add-toggle-button styles) :on-press #(toggle nil)}
      [animated-view {:style {:transform [{:rotate (interpolate ani-val [0 1] ["0deg" "45deg"])}]}}
       [icon {:name "ios-add" :size 48 :color "white"}]]]]))
