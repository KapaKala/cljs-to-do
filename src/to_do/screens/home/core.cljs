(ns to-do.screens.home.core
  (:require [re-frame.core :refer [subscribe dispatch]]
            [to-do.imports :refer [safe-area-view scroll-view view text]]
            [to-do.utils.sqlite.subs]
            [to-do.utils.animation.core :refer [animated-value animated-view interpolate]]
            [to-do.screens.home.components :refer [header render-entry add-entry-thing]]
            [to-do.screens.home.styles :refer [styles]]))

(defn home-screen [props]
  (let [navigate (.. (clj->js props) -navigation -navigate)
        entries (subscribe [:entries])
        ani-val (animated-value 0)]
    [safe-area-view {:style (:container styles)}
     [animated-view {:style {:flex 1 :transform [{:scale (interpolate ani-val [0 1] [1 0.9])}]}}
      [header navigate]
      (if (or (empty? @entries) (nil? @entries))
        [view {:style {:flex 1 :justify-content "center" :align-items "center" :padding 25 :padding-bottom 100}}
         [text {:style {:font-family "roboto-mono-regular" :color "grey"}}
          [text "Add entries by pressing the button in the bottom.\n\n"]
          [text "Double tapping entries marks them as completed.\n\n"]
          [text "Delete entries by double tapping completed entries."]]]
        [scroll-view {:style {:width "100%"} :content-container-style {:justify-content "center" :padding-bottom 50}}
         (for [entry @entries] ^{:key (:id entry)} [render-entry entry])])]
     [animated-view {:style (merge (:overlay styles) {:opacity (interpolate ani-val [0 1] [0 0.333])})
                     :pointer-events "none"}]
     [add-entry-thing ani-val]]))
