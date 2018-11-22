(ns to-do.screens.home.core
  (:require [re-frame.core :refer [subscribe dispatch]]
            [to-do.imports :refer [safe-area-view scroll-view view text status-bar]]
            [to-do.common.components :refer [header form-modal overlay]]
            [to-do.utils.sqlite.subs]
            [to-do.utils.animation.core :refer [animated-value animated-view interpolate]]
            [to-do.screens.home.components :refer [render-entry instructions]]
            [to-do.screens.home.styles :refer [styles]]
            [reagent.core :as r]))

(defn home-screen [props]
  (let [navigate (.. (clj->js props) -navigation -navigate)
        input-val (r/atom nil)
        toggle-state (r/atom false)
        save-input (fn [invocation] (when invocation (do (dispatch [:add-entry @input-val])
                                                         (reset! input-val nil))))
        current-table (subscribe [:current-table])
        entries (subscribe [:entries])
        ani-val (animated-value 0)]
    (when (some? @current-table)
      [safe-area-view {:style (:container styles)}
       [status-bar {:bar-style "dark-content" :animated true}]
       [animated-view {:style {:flex 1 :transform [{:scale (interpolate ani-val [0 1] [1 0.9])}]}}
        [header {:title     @current-table
                 :color     "black"
                 :icon-name "ios-list-box"
                 :on-press  #(navigate "Settings")}]
        (if (or (empty? @entries) (nil? @entries))
          [instructions]
          [scroll-view {:style {:width "100%"} :content-container-style {:justify-content "center" :padding-bottom 50}}
           (for [entry @entries] ^{:key (:id entry)} [render-entry entry])])]
       [overlay ani-val]
       [form-modal {:ani-val          ani-val
                    :toggle-state     toggle-state
                    :background-color "blue"
                    :primary-color    "white"
                    :on-complete      #(save-input %)
                    :input-val        input-val
                    :form-text        "What would you like to remember?"}]])))
