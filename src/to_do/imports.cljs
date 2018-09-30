(ns to-do.imports
  (:require [reagent.core :as r]))

(defonce ReactNative (js/require "react-native"))
(def expo (js/require "expo"))

(def AtExpo (js/require "@expo/vector-icons"))
(def ionicons (.-Ionicons AtExpo))
(def icon (r/adapt-react-class ionicons))

(def view (r/adapt-react-class (.-View ReactNative)))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))
(def toucable-opacity (r/adapt-react-class (.-TouchableOpacity ReactNative)))
