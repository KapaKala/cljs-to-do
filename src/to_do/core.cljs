(ns to-do.core
  (:require [reagent.core :as r]
            [re-frame.core :refer [dispatch-sync dispatch subscribe]]
            [oops.core :refer [ocall]]
            [to-do.handlers]
            [to-do.subs]
            [to-do.imports :refer [expo font]]
            [to-do.routing :refer [routing]]
            [to-do.utils.sqlite.core :as sql]
            [kitchen-async.promise :as p]))

(defn app-root []
  (let [fonts-loaded (subscribe [:get-fonts-loaded])]
    (r/create-class
      {:component-did-mount
       #(p/do (.loadAsync font #js{"roboto-mono-bold"    (js/require "./assets/fonts/RobotoMono-Bold.ttf")
                                   "roboto-mono-regular" (js/require "./assets/fonts/RobotoMono-Regular.ttf")})
              (dispatch [:set-fonts-loaded]))
       :reagent-render
       (fn []
         (when @fonts-loaded
           [(r/adapt-react-class (routing))]))})))

(defn init []
  (dispatch-sync [:initialize-db])
  (sql/initialize)
  (ocall expo "registerRootComponent" (r/reactify-component app-root)))
