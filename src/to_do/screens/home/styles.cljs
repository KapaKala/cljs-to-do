(ns to-do.screens.home.styles)

(def styles {:container                 {:flex             1
                                         :background-color "white"}

             :header                    {:width           "100%"
                                         :padding-left    "2.5%"
                                         :padding-right   "2.5%"
                                         :padding-top     10
                                         :padding-bottom  10
                                         :flex-direction  "row"
                                         :justify-content "space-between"
                                         :align-items     "center"}
             :title                     {:font-size   32
                                         :font-family "roboto-mono-bold"
                                         :font-weight "bold"}
             :entry-container           {:margin-left      "2.5%"
                                         :margin-right     "2.5%"
                                         :margin-bottom    "2.5%"
                                         :padding          25
                                         :background-color "black"
                                         :min-height 100}
             :entry-text                {:color       "white"
                                         :font-size   18
                                         :font-family "roboto-mono-regular"}
             :add-entry-container       {:position         "absolute"
                                         :justify-content "center"
                                         :align-items "center"
                                         :bottom           0}
             :add-toggle-button         {:width           50
                                         :height          50
                                         :position        "absolute"
                                         :justify-content "center"
                                         :align-items     "center"
                                         :background-color "#0000ff"
                                         :bottom          0}
             :entry-form-container      {:flex            1
                                         :width "100%"
                                         :height "90%"
                                         :justify-content "center"
                                         :align-items     "center"
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
