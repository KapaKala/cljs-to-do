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
(def keyboard-avoiding-view (r/adapt-react-class (.-KeyboardAvoidingView ReactNative)))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def button (r/adapt-react-class (.-Button ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))
(def touchable-opacity (r/adapt-react-class (.-TouchableOpacity ReactNative)))
(def touchable-without-feedback (r/adapt-react-class (.-TouchableWithoutFeedback ReactNative)))
(def text-input (r/adapt-react-class (.-TextInput ReactNative)))
(def Keyboard (.-Keyboard ReactNative))
(def StatusBar (.-StatusBar ReactNative))
(def status-bar (r/adapt-react-class StatusBar))

(def dimensions (.get (.-Dimensions ReactNative) "window"))
(def width (.-width dimensions))
(def height (.-height dimensions))
