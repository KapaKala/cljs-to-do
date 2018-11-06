(ns to-do.utils.sqlite.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :entries
  (fn [db _]
    (:entries db)))

(reg-sub
  :entry-error
  (fn [db _]
    (:entry-error db)))

(reg-sub
  :tables
  (fn [db _]
    (:tables db)))

(reg-sub
  :current-table
  (fn [db _]
    (:current-table db)))
