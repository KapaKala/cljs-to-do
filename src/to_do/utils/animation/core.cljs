(ns to-do.utils.animation.core
  (:require [reagent.core :as r]
    [to-do.imports :refer [ReactNative]]))

(def animated (.-Animated ReactNative))
(def value (.-Value animated))
(defn animated-value [val] (value. val))
(def animated-view (r/adapt-react-class (.-View animated)))
(def easing (.-Easing ReactNative))

(defn timing [val to-val duration] (.timing animated val #js{:toValue to-val
                                                             :duration duration
                                                             :easing (.in easing)
                                                             }))

(defn interpolate [val input output] (.interpolate val #js{:inputRange (clj->js input)
                                                           :outputRange (clj->js output)}))

(defn start-animation [animation cb] (.start animation cb))
