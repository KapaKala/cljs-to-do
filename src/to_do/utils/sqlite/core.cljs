(ns to-do.utils.sqlite.core
  (:require [re-frame.core :refer [dispatch dispatch-sync subscribe]]
            [to-do.imports :refer [expo]]
            [to-do.utils.asyncstorage.core :as as]
            [to-do.utils.sqlite.subs]
            [kitchen-async.promise :as p]))

(defonce sqlite (.-SQLite expo))
(def db (.openDatabase sqlite "entries.db"))

(def current-table (subscribe [:current-table]))

(defn transaction [sql-statement args on-success on-error]
  (.transaction db (fn [tx] (.executeSql tx sql-statement (clj->js args) on-success on-error))))

(defn create-table []
  (transaction
    (str "create table if not exists " (str @current-table) "(id integer primary key not null, done int, value text)")
    nil
    (fn [_] (console.log (str "created sql table " @current-table)))
    (fn [_ err] (console.log err))))

(defn get-entries []
  (transaction
    (str "select * from " @current-table)
    nil
    (fn [_ res] (dispatch [:set-entries (js->clj (.. res -rows -_array) :keywordize-keys true)]))
    (fn [_ err] (console.log err))))

(defn add-entry [entry on-success on-error]
  (transaction
    (str "insert into " @current-table " (done, value) values (?, ?)")
    [0 entry]
    on-success
    on-error))

(defn delete-entry [id on-success on-error]
  (transaction
    (str "delete from " @current-table " where id = ?")
    [id]
    on-success
    on-error))

(defn complete-entry [id on-success on-error]
  (transaction
    (str "update " @current-table " set done = 1 where id = ?")
    [id]
    on-success
    on-error))

(defn clear-entries []
  (transaction
    (str "delete from " @current-table)
    nil
    #(get-entries)
    (fn [_ err] (console.log err))))

(defn get-tables []
  (transaction
    "select * from sqlite_master where type = 'table'"
    nil
    (fn [_ res] (dispatch [:set-tables (js->clj (.. res -rows -_array) :keywordize-keys true)]))
    (fn [_ err] (console.log err))))

(defn drop-table [table]
  (transaction
    (str "drop table if exists " table)
    nil
    #(get-tables)
    (fn [_ err] (console.log err))))

(defn initialize []
  (as/get-item
    "table"
    (fn [res] (p/do (dispatch-sync [:set-current-table (if (nil? res) "to_do" res)])
                    (when (some? @current-table)
                      (do (create-table)
                          (get-entries)
                          (get-tables)))))
    (fn [err] (console.warn err))))
