(ns to-do.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :get-greeting
  (fn [db _]
    (:greeting db)))

(reg-sub
  :get-fonts-loaded
  (fn [db _]
    (:fonts-loaded db)))