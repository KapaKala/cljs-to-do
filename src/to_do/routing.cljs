(ns to-do.routing
  (:require [reagent.core :as r]
            [to-do.screens.debug.core :refer [debug-screen]]
            [to-do.screens.home.core :refer [home-screen]]
            [to-do.screens.settings.core :refer [settings-screen]]))

(def ReactNavigation (js/require "react-navigation"))
(def stack-navigator (.-createStackNavigator ReactNavigation))

(defn routing []
  (stack-navigator #js{
                       ;:Debug    (r/reactify-component debug-screen)
                       :Home     (r/reactify-component home-screen)
                       :Settings (r/reactify-component settings-screen)}
                   #js{:headerMode "none"}))
