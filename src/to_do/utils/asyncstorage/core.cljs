(ns to-do.utils.asyncstorage.core
  (:require [to-do.imports :refer [AsyncStorage]]
            [kitchen-async.promise :as p]))

(defn set-item [keyword item on-success on-error]
  (-> (.setItem AsyncStorage keyword item)
      (.then (fn [res] (on-success res)))
      (.catch (fn [err] (on-error err)))))

(defn get-item [keyword on-success on-error]
  (-> (.getItem AsyncStorage keyword)
      (p/then (fn [res] (on-success res)))
      (p/catch* (fn [err] (on-error err)))))
