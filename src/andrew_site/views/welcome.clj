(ns andrew-site.views.welcome
  (:require [andrew-site.views.common :as common]
            [andrew-site.views.content :as content]
            [noir.content.pages :as pages])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpage "/" []
  (let [jobs (content/jobs)]
   (common/layout (common/jobs-list jobs) (common/sidebar))))
