(ns to-do.utils.sqlite.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :get-entries
  (fn [db _]
    (:entries db)))

(reg-sub
  :get-entry-error
  (fn [db _]
    (:entry-error db)))