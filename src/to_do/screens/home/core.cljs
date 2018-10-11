(ns to-do.screens.home.core
  (:require [re-frame.core :refer [subscribe dispatch]]
            [to-do.imports :refer [safe-area-view scroll-view view text]]
            [to-do.utils.sqlite.subs]
            [to-do.utils.animation.core :refer [animated-value animated-view interpolate]]
            [to-do.screens.home.components :refer [header render-entry add-entry-thing]]
            [to-do.screens.home.styles :refer [styles]]))

(defn home-screen [props]
  (let [navigate (.. (clj->js props) -navigation -navigate)
        entries (subscribe [:get-entries])
        ani-val (animated-value 0)]
    [safe-area-view {:style (:container styles)}
     [animated-view {:style {:flex 1 :transform [{:scale (interpolate ani-val [0 1] [1 0.8])}]}}
      [header navigate]
      [scroll-view {:style {:width "100%"} :content-container-style {:justify-content "center"}}
       (if (or (empty? @entries) (nil? @entries))
         [view {:style {:flex 1 :justify-content "center" :align-items "center"}}
          [text {:style {:font-family "roboto-mono-regular" :color "grey"}} "Start off by pressing the button in the bottom"]]
         (map render-entry @entries))]]
     [add-entry-thing ani-val]]))
