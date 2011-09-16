(ns andrew-site.models.db
  (:use noir.core
        hiccup.core
        hiccup.page-helpers
        somnium.congomongo
        [somnium.congomongo.config :only [*mongo-config*]]))

(defn split-mongo-url [url]
  "Parses mongodb url from heroku, eg. mongodb://user:pass@localhost:1234/db"
  (let [matcher (re-matcher #"^.*://(.*?):(.*?)@(.*?):(\d+)/(.*)$" url)] ;; Setup the regex.
    (when (.find matcher) ;; Check if it matches.
      (zipmap [:match :user :pass :host :port :db] (re-groups matcher))))) ;; Construct an options map.

(defn maybe-init []
  "Checks if connection and collection exist, otherwise initialize."
  (when (not (connection? *mongo-config*)) ;; If global connection doesn't exist yet.
    (let [mongo-url (get (System/getenv) "MONGOHQ_URL") ;; Heroku puts it here.
          config    (split-mongo-url mongo-url)] ;; Extract options.
      (println "Initializing mongo @ " mongo-url)
      (mongo! :db (:db config) :host (:host config) :port (Integer. (:port config))) ;; Setup global mongo.
      (authenticate (:user config) (:pass config)) ;; Setup u/p.)))

(def major-skills
  (map #(:skill %) (fetch :skills :where {:level "major"})))

(def lesser-skills
  (map #(:skill %) (fetch :skills :where {:level "lesser"})))

(defn add-major-skill [skill]
  (insert! :skills {:level "major" :skill skill}))

(defn add-lesser-skill [skill]
  (insert! :skills {:level "lesser" :skill skill}))

(defn add-job [{:keys [name start-year end-year url description]}]
  (insert! :jobs {:name name :start-year start-year :end-year end-year :url url :description description}))

(def jobs
  (fetch :jobs))
