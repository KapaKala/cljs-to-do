(ns to-do.screens.home.styles)

(def styles {:container                 {:flex             1
                                         :background-color "white"}
             :header                    {:margin-horizontal "2.5%"
                                         :padding-vertical 10
                                         :flex-direction  "row"
                                         :justify-content "space-between"
                                         :align-items     "center"}
             :overlay                   {:position         "absolute"
                                         :top              0
                                         :bottom           0
                                         :left             0
                                         :right            0
                                         :background-color "black"}
             :title                     {:font-size   32
                                         :font-family "roboto-mono-bold"
                                         :font-weight "bold"}
             :entry-container           {:margin-horizontal "2.5%"
                                         :padding-horizontal 25
                                         :background-color "black"
                                         :justify-content "center"}
             :entry-text                {:color       "white"
                                         :font-size   18
                                         :font-family "roboto-mono-regular"}
             :add-entry-container       {:position        "absolute"
                                         :justify-content "center"
                                         :align-items     "center"
                                         :bottom          0}
             :add-toggle-button         {:width            50
                                         :height           50
                                         :position         "absolute"
                                         :justify-content  "center"
                                         :align-items      "center"
                                         :background-color "#0000ff"
                                         :bottom           0}
             :entry-form-container      {:flex             1
                                         :width            "100%"
                                         :height           "90%"
                                         :justify-content  "center"
                                         :align-items      "center"
                                         :background-color "#0000ff"}
             :form-title                {:font-size   18
                                         :font-family "roboto-mono-bold"
                                         :color       "white"}
             :form-input                {:width            "95%"
                                         :height           50
                                         :padding          10
                                         :margin           "5%"
                                         :background-color "white"
                                         :font-family      "roboto-mono-regular"}
             :form-button               {:border-color "white"
                                         :border-width 1
                                         :padding      10}
             :form-button-disabled      {:border-color "#6666ff"
                                         :border-width 1
                                         :padding      10}
             :form-button-text          {:color       "white"
                                         :font-family "roboto-mono-regular"}
             :form-button-text-disabled {:color       "#6666ff"
                                         :font-family "roboto-mono-regular"}})
