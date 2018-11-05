(ns to-do.utils.sqlite.core
  (:require [re-frame.core :refer [dispatch]]
            [to-do.imports :refer [expo]]))

(defonce sqlite (.-SQLite expo))
(def db (.openDatabase sqlite "entries"))

(defn transaction [sql-statement args on-success on-error]
  (.transaction db (fn [tx] (.executeSql tx sql-statement (clj->js args) on-success on-error))))

(defn create-table []
  (transaction
    "create table if not exists entries (id integer primary key not null, done int, value text)"
    nil
    #(println "created sql table")
    #(println "failed creating sql table :(")))

(defn get-entries []
  (transaction "select * from entries"
               nil
               (fn [_ res] (dispatch [:set-entries (js->clj (.. res -rows -_array) :keywordize-keys true)]))
               (fn [error] (console.warn error))))

(defn add-entry [entry on-success on-error]
  (transaction "insert into entries (done, value) values (?, ?)" [0 entry] on-success on-error))

(defn delete-entry [id on-success on-error]
  (transaction "delete from entries where id = ?" [id] on-success on-error))

(defn complete-entry [id on-success on-error]
  (transaction "update entries set done = 1 where id = ?" [id] on-success on-error))

(defn clear-entries []
  (transaction "delete from entries" nil #(get-entries) #(console.warn "failed clearing table :(")))
