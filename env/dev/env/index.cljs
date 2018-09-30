(ns env.index
  (:require [env.dev :as dev]))

;; undo main.js goog preamble hack
(set! js/window.goog js/undefined)

(-> (js/require "figwheel-bridge")
    (.withModules #js {"react-native" (js/require "react-native"), "expo" (js/require "expo"), "@expo/vector-icons" (js/require "@expo/vector-icons"), "react" (js/require "react"), "create-react-class" (js/require "create-react-class"), "./assets/icons/app.png" (js/require "../../../assets/icons/app.png"), "./assets/icons/loading.png" (js/require "../../../assets/icons/loading.png"), "./assets/images/cljs.png" (js/require "../../../assets/images/cljs.png")}
)
    (.start "main" "expo" "192.168.100.10"))
