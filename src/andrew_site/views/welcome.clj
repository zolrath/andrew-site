(ns andrew-site.views.welcome
  (:require [andrew-site.views.common :as common]
            [andrew-site.models.db :as db]
            [noir.content.pages :as pages])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpage "/" []
  (db/maybe-init)
  (common/layout (common/jobs-list db/jobs) (common/sidebar)))
