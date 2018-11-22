(ns to-do.screens.home.components
  (:require [re-frame.core :refer [dispatch]]
            [to-do.utils.sqlite.actions]
            [to-do.imports :refer [view text touchable-opacity text-input icon width height Keyboard StatusBar]]
            [to-do.utils.animation.core :refer [animated animated-view animated-value animated-text interpolate
                                                timing start-animation sequence parallel]]
            [to-do.screens.home.styles :refer [styles]]
            [reagent.core :as r]))

(defn render-entry [entry]
  (let [last-tap (r/atom nil)
        completion-animation (animated-value (if (= 1 (:done entry)) 0.9 1))
        fade-animation (animated-value 0)
        height-animation (animated-value 100)
        padding-animation (animated-value 1)
        multiplied-animation (.multiply animated fade-animation completion-animation)
        max-height (r/atom nil)
        set-height (fn [height] (do (start-animation (timing height-animation height 1) #())
                                    (reset! max-height height)))
        delet-this (fn [invocation] (when invocation (dispatch [:delete-entry (:id entry)])))
        complete-entry (fn [invocation] (when invocation (dispatch [:complete-entry (:id entry)])))
        handle-tap (fn [done]
                     ; Check for double tap (there has been less than 300ms since previous tap)
                     (if (and (some? @last-tap) (< (- (.now js/Date) @last-tap) 300))
                       (if (= done 1)
                         ; If entry is done, do a fade out animation and delete entry from state when complete
                         (start-animation (sequence [(timing fade-animation 0 250)
                                                     (parallel [(timing height-animation 0 250)
                                                                (timing padding-animation 0 250)])])
                                          (fn [invocation] (delet-this invocation)))
                         ; Otherwise animate scale and opacity, then update entry as done
                         (do (start-animation
                               (timing completion-animation 0.9 250)
                               (fn [invocation] (complete-entry invocation)))
                             (reset! last-tap nil)))
                       (reset! last-tap (.now js/Date))))
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
       (fn [entry]
         [touchable-opacity {:on-press #(handle-tap (:done entry))}
          [animated-view {:style    (merge (:entry-container styles)
                                           (when (not (nil? @max-height)) {:height height-animation})
                                           completed-style)
                          :onLayout #(when (nil? @max-height) (set-height (.. % -nativeEvent -layout -height)))}
           [animated-text {:style (merge {:color "grey" :font-family "roboto-mono-regular"} text-animation)} (:id entry)]
           [animated-text {:style (merge (:entry-text styles) text-animation)} (:value entry)]]])})))

(defn instructions []
  (let [opacity-animation (animated-value 0)]
    (r/create-class
      {:component-did-mount
       (fn [] (start-animation (timing opacity-animation 1 250) nil))
       :reagent-render
       (fn []
         [animated-view {:style (merge (:instructions-container styles) {:opacity opacity-animation})}
          [text {:style (:instructions-text styles)}
           [text "Add entries by pressing the button in the bottom.\n\n"]
           [text "Double tapping entries marks them as completed.\n\n"]
           [text "Delete entries by double tapping completed entries."]]])})))
