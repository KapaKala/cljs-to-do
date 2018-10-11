(ns env.index
  (:require [env.dev :as dev]))

;; undo main.js goog preamble hack
(set! js/window.goog js/undefined)

(-> (js/require "figwheel-bridge")
    (.withModules #js {"./assets/icons/loading.png" (js/require "../../../assets/icons/loading.png"), "expo" (js/require "expo"), "./assets/images/cljs.png" (js/require "../../../assets/images/cljs.png"), "./assets/icons/app.png" (js/require "../../../assets/icons/app.png"), "./assets/fonts/RobotoMono-Bold.ttf" (js/require "../../../assets/fonts/RobotoMono-Bold.ttf"), "react-native" (js/require "react-native"), "react-navigation" (js/require "react-navigation"), "react" (js/require "react"), "create-react-class" (js/require "create-react-class"), "@expo/vector-icons" (js/require "@expo/vector-icons"), "./assets/fonts/RobotoMono-Regular.ttf" (js/require "../../../assets/fonts/RobotoMono-Regular.ttf")}
)
    (.start "main" "expo" "192.168.100.11"))
