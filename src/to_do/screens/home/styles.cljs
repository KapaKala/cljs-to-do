(ns to-do.screens.home.styles)

(def styles {:container       {:flex             1
                               :justify-content  "flex-start"
                               :align-items      "center"
                               :background-color "white"}
             :header          {:width           "95%"
                               :padding-left    5
                               :padding-right   5
                               :padding-top     10
                               :padding-bottom  10
                               :flex-direction  "row"
                               :justify-content "space-between"
                               :align-items     "center"}
             :title           {:font-size   32
                               :font-family "roboto-mono-bold"
                               :font-weight "bold"}
             :entry-container {:width            "100%"
                               :margin-bottom    "2.5%"
                               :padding          25
                               :background-color "black"}
             :entry-text {:color "white"
                          :font-size 18
                          :font-family "roboto-mono-regular"}})
