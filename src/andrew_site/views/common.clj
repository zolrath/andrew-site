(ns andrew-site.views.common
  (:require [andrew-site.models.db :as db])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpartial layout [& content]
  (html5
   [:head
    [:title "Andrew Marrone"]
    (include-css "/css/reset.css" "/css/960_24.css" "/css/styles.css")
    (include-js "/js/jquery-1.4.2-min.js" "/js/smooth-scroll.js" "/js/custom.js")]
   [:body
    [:div#wrap
     content]]))
