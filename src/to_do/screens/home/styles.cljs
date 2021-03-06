(ns to-do.screens.home.styles)

(def styles {:container              {:flex             1
                                      :background-color "white"}

             :entry-container        {:margin-horizontal  "2.5%"
                                      :padding-horizontal 25
                                      :background-color   "black"
                                      :justify-content    "center"}
             :entry-text             {:color       "white"
                                      :font-size   18
                                      :font-family "roboto-mono-regular"}
             :instructions-container {:flex            1
                                      :justify-content "center"
                                      :align-items     "center"
                                      :padding         25
                                      :padding-bottom  100}
             :instructions-text      {:font-family "roboto-mono-regular"
                                      :color       "grey"}})
