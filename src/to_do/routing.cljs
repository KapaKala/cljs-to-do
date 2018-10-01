(ns to-do.routing
  (:require [reagent.core :as r]
            [to-do.screens.home :refer [home-screen]]
            [to-do.screens.settings :refer [settings-screen]]))

(def ReactNavigation (js/require "react-navigation"))
(def stack-navigator (.-createStackNavigator ReactNavigation))

(defn routing []
  (stack-navigator #js{:Home     (r/reactify-component home-screen)
                       :Settings (r/reactify-component settings-screen)}
                   #js{:headerMode "none"}))
