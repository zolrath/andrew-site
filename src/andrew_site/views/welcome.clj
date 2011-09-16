(ns andrew-site.views.welcome
  (:require [andrew-site.views.common :as common]
            [andrew-site.models.db :as db]
            [noir.content.pages :as pages])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers
        somnium.congomongo
        [somnium.congomongo.config :only [*mongo-config*]))

(defpage "/" []
  (maybe-init)
  (let [jobs db/jobs]
   (common/layout (db/jobs-list jobs) (common/sidebar))))
