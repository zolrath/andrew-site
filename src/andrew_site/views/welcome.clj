(ns andrew-site.views.welcome
  (:require [andrew-site.views.common :as common]
            [noir.content.pages :as pages])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpage "/" []
  {"Cache-Control" "no-cache, must-revalidate"}
  (common/layout "Nothing"))
