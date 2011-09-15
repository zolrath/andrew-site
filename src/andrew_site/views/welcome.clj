(ns andrew-site.views.welcome
  (:require [andrew-site.views.common :as common]
            [noir.content.pages :as pages])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpage "/" []
  (common/layout (common/list-jobs jobs) (common/sidebar)))
