(ns to-do.screens.settings.core
  (:require [re-frame.core :refer [dispatch subscribe]]
            [to-do.imports :refer [safe-area-view view button text touchable-opacity icon scroll-view status-bar]]
            [to-do.utils.animation.core :refer [animated-view animated-value interpolate]]
            [to-do.common.components :refer [header form-modal overlay]]
            [to-do.screens.settings.components :refer [render-table]]
            [to-do.screens.settings.styles :refer [styles]]
            [to-do.utils.sqlite.core :as sql]
            [to-do.utils.sqlite.subs]
            [reagent.core :as r]
            [clojure.string :as s]))

(defn input->snake-case [input]
  (s/lower-case (s/replace (s/trim input) " " "_")))

(defn settings-screen [props]
  (let [go-back (.. (clj->js props) -navigation -goBack)
        tables (subscribe [:tables])
        current-table (subscribe [:current-table])
        ani-val (animated-value 0)
        input-val (r/atom nil)
        toggle-state (r/atom false)
        save-input (fn [invocation] (when invocation (let [altered-input (input->snake-case @input-val)]
                                                       (do (sql/create-table {:table altered-input})
                                                           (dispatch [:set-current-table altered-input])
                                                           (sql/get-tables)
                                                           (reset! input-val nil)))))]
    [safe-area-view {:style (:container styles)}
     [status-bar {:bar-style "light-content" :animated true}]
     [animated-view {:style {:flex 1 :transform [{:scale (interpolate ani-val [0 1] [1 0.9])}]}}
      [header {:title     "Your Lists"
               :color     "white"
               :icon-name "ios-arrow-round-back"
               :on-press  #(go-back)}]
      [scroll-view {:style {:flex 1}}
       (doall (for [table @tables] ^{:key table} [render-table table @current-table]))]]
     [overlay ani-val]
     [form-modal {:ani-val          ani-val
                  :toggle-state     toggle-state
                  :background-color "white"
                  :primary-color    "blue"
                  :on-complete      #(save-input %)
                  :input-val        input-val
                  :form-text        "Name your list"}]]))
