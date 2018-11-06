(ns to-do.utils.sqlite.actions
  (:require [re-frame.core :refer [dispatch reg-event-db reg-fx reg-event-fx]]
            [to-do.utils.sqlite.core :as sql]
            [to-do.utils.asyncstorage.core :as as]))

(reg-event-db
  :set-entries
  (fn [db [_ entries]]
    (assoc db :entries entries)))

(reg-event-db
  :set-entry-error
  (fn [db [_ error]]
    (assoc db :entry-error error)))

(reg-event-fx
  :complete-entry
  (fn [cofx [_ id]]
    {:complete-sql-entry id}))

(reg-fx
  :complete-sql-entry
  (fn [id] (sql/complete-entry id #(sql/get-entries) #(println %))))

(reg-event-fx
  :add-entry
  (fn [cofx [_ entry]]
    {:entry-to-sql entry}))

(reg-fx
  :entry-to-sql
  (fn [entry] (sql/add-entry
                entry
                #(sql/get-entries)
                #(println %))))

(reg-event-fx
  :delete-entry
  (fn [cofx [_ id]]
    {:delete-sql-entry id}))

(reg-fx
  :delete-sql-entry
  (fn [id] (sql/delete-entry id #(sql/get-entries) #(println %))))

(reg-event-db
  :set-tables
  (fn [db [_ tables]]
    (assoc db :tables tables)))

(reg-event-fx
  :set-current-table
  (fn [cofx [_ table]]
    {:db (assoc (:db cofx) :current-table table)
     :table-to-async-storage table}))

(reg-fx
  :table-to-async-storage
  (fn [table]
    (as/set-item "table" table #() #())))
