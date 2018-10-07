(ns to-do.imports
  (:require [reagent.core :as r]))

(defonce ReactNative (js/require "react-native"))
(def expo (js/require "expo"))
(def font (.-Font expo))

(def AtExpoIcons (js/require "@expo/vector-icons"))
(def icon (r/adapt-react-class (.-Ionicons AtExpoIcons)))

(def view (r/adapt-react-class (.-View ReactNative)))
(def safe-area-view (r/adapt-react-class (.-SafeAreaView ReactNative)))
(def scroll-view (r/adapt-react-class (.-ScrollView ReactNative)))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def button (r/adapt-react-class (.-Button ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))
(def touchable-opacity (r/adapt-react-class (.-TouchableOpacity ReactNative)))
