(ns to-do.utils.animation.core
  (:require [reagent.core :as r]
    [to-do.imports :refer [ReactNative]]))

(def animated (.-Animated ReactNative))
(def value (.-Value animated))
(defn animated-value [val] (value. val))
(def animated-view (r/adapt-react-class (.-View animated)))
(def animated-text (r/adapt-react-class (.-Text animated)))
(def easing (.-Easing ReactNative))

(defn sequence [seq] (.sequence animated (clj->js seq)))
(defn parallel [seq] (.parallel animated (clj->js seq)))

(defn timing [val to-val duration] (.timing animated val #js{:toValue to-val
                                                             :duration duration
                                                             :easing (.bezier easing 0.0, 0.0, 0.2, 1)}))

(defn interpolate [val input output] (.interpolate val #js{:inputRange (clj->js input)
                                                           :outputRange (clj->js output)
                                                           :extrapolate "clamp"}))

(defn start-animation [animation cb] (.start animation cb))
