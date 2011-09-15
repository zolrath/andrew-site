(ns andrew-site.views.content
  (:use noir.core
        hiccup.core
        hiccup.page-helpers
        somnium.congomongo
        [somnium.congomongo.config :only [*mongo-config*]]))

(mongo! :db "app1002329" :host "staff.mongohq.com" :port 10001)
(authenticate "heroku" "4df8f6a5a6484618f44d7359d0d9f2bf")

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
      (authenticate (:user config) (:pass config)) ;; Setup u/p.
      (or (collection-exists? :firstcollection) ;; Create collection named 'firstcollection' if it doesn't exist.
            (create-collection! :firstcollection)))))

(def top-right
  (list "Interested? " (mail-to "jobs@andrewmarrone.com?subject=Employment%20Opportunity" "Contact me!")))

(def desired-title
  (list "Junior" [:br] [:em "Systems Administrator"]))

(def header-name "Andrew Marrone")

(comment (def major-skills
   ["Shell Scripting" "Ubuntu" "CentOS" "OS X" "VMWare"]))

(comment (def lesser-skills
   ["ESXi" "Microsoft Exchange" "Windows Server 2003" "Active Directory"]))

(def profile
  "Three years help desk experience while taking on more admin roles, regarded by peers and supervisors as a top-notch performer always eager to learn more.<br />
Working on my RCHSA certification when there is spare time.")

(def certifications
  (link-to {:target "_blank"} "apluscert.pdf" (image "img/aplus.jpg")))

;; Two methods of listing jobs. Either def entries for each job then include in the all-jobs vector,
;; or simply add maps to the jobs def. The vector allows you to easily re-order and temporarily remove
;; items if you wanted to but the second method requires less duplication as you don't need to write
;; the name of your entries into the vector after making the map.

(def underground-elephant
  {:name "Underground Elephant"
   :start-year "2011"
   :end-year "2011"
   :url "http://www.undergroundelephant.com"
   :description "Junior Systems Administrator pulling double duty as the Help Desk Lead. Responsible for local server setup, log monitoring, data backup, and security patches when not working with our vendors and contractors."})

(def family-health-centers
  {:name "Family Health Centers"
   :start-year "2010"
   :end-year "2011"
   :url "http://www.fhcsd.org"
   :description "Handled 50% of all tickets for the organization and still managed to automate essential processesses, write a knowledge base, and deploy company phones."})

(def ed-venture
  {:name "EdVenture Partners"
   :start-year "2010"
   :end-year "2010"
   :url "http://www.edventurepartners.com"
   :description "Flying around the country with an expense account and a fancy suit, but it wasn't for me. While at EdVenture Partners I managed five concurrent marketing projects in three states, all centered around the 2011 Chevrolet lineup."})

(def marriage-equality
  {:name "Marriage Equality"
   :start-year "2008"
   :end-year "2010"
   :url "http://www.marriageequality.org"
   :description "What got me started. Thought I was keeping busy between classes - do some good, that sort of thing."})

(def all-jobs [underground-elephant family-health-centers ed-venture marriage-equality])

(def map-of-jobs
  '({:company-name "Underground Elephant"
     :start-year "2011"
     :end-year "2011"
     :url "http://www.undergroundelephant.com"
     :job-description "Junior Systems Administrator pulling double duty as the Help Desk Lead. Responsible for local server setup, log monitoring, data backup, and security patches when not working with our vendors and contractors."}

    {:company-name "Family Health Centers"
     :start-year "2010"
     :end-year "2011"
     :url "http://www.fhcsd.org"
     :job-description "Handled 50% of all tickets for the organization and still managed to automate essential processesses, write a knowledge base, and deploy company phones."}

    {:company-name "EdVenture Partners"
     :start-year "2010"
     :end-year "2010"
     :url "http://www.edventurepartners.com"
     :job-description "Flying around the country with an expense account and a fancy suit, but it wasn't for me. While at EdVenture Partners I managed five concurrent marketing projects in three states, all centered around the 2011 Chevrolet lineup."}

    {:company-name "Marriage Equality"
     :start-year "2008"
     :end-year "2010"
     :url "http://www.marriageequality.org"
     :job-description "What got me started. Thought I was keeping busy between classes - do some good, that sort of thing."}))
