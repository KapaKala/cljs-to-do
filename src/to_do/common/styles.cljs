(ns to-do.common.styles)

(def styles {:header               {:margin-horizontal "2.5%"
                                    :padding           10
                                    :flex-direction    "row"
                                    :justify-content   "space-between"
                                    :align-items       "baseline"}
             :title                {:font-size   32
                                    :font-family "roboto-mono-bold"
                                    :font-weight "bold"}
             :add-entry-container  {:position        "absolute"
                                    :justify-content "center"
                                    :align-items     "center"
                                    :bottom          0}
             :add-toggle-button    {:width           50
                                    :height          50
                                    :position        "absolute"
                                    :justify-content "center"
                                    :align-items     "center"
                                    :bottom          0}
             :entry-form-container {:flex            1
                                    :width           "100%"
                                    :height          "90%"
                                    :justify-content "center"
                                    :align-items     "center"}
             :form-title           {:font-size   18
                                    :font-family "roboto-mono-bold"
                                    :color       "white"}
             :form-input           {:width            "95%"
                                    :height           50
                                    :padding          10
                                    :margin           "5%"
                                    :background-color "white"
                                    :font-family      "roboto-mono-regular"
                                    :border-width     1}
             :form-button          {:border-width 1
                                    :padding      10}
             :form-button-text     {:font-family "roboto-mono-regular"}
             :overlay              {:position         "absolute"
                                    :top              0
                                    :bottom           0
                                    :left             0
                                    :right            0
                                    :background-color "black"}})