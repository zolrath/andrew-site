(ns andrew-site.views.common
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(def major-skills
  ["Shell Scripting" "Ubuntu" "CentOS" "OS X" "VMWare"])

(def lesser-skills
  ["ESXi" "Microsoft Exchange" "Windows Server 2003" "Active Directory"])

(def profile
  "Three years help desk experience while taking on more admin roles, regarded by peers and supervisors as a top-notch performer always eager to learn more.<br />
Working on my RCHSA certification when there is spare time.")

(defpartial header []
  [:div#wrapHeader
   [:div#header
    [:div.container_24
     [:div#topBar
      [:div#years.grid_13
       [:ul#yearsNav
        [:li.yearsLi "2008"]
        [:li.yearsLi (link-to "#y2009" "2009")]
        [:li.yearsLi (link-to "#y2010" "2010")]
        [:li.yearsLi.lastYear (link-to {:class "active"} "#wrap" "2011")]]]
      [:div#topBarRight.grid_11
       [:h2 "Interested? " (mail-to "jobs@andrewmarrone.com?subject=Employment%20Opportunity" "Contact me!")]]]
     [:div#information
      [:div#info.grid_13
       [:h2 "Junior" [:br] [:em "Systems Administrator"]]]
      [:div#name.grid_11
       [:h1 (link-to {:class "active"} "#wrap" "Andrew<br /><em>Marrone</em>")]]]]]])

(defpartial sidebar []
  [:div#sidebar.grid_11
   [:ul#sidebarNav
    [:li.slidebarLi (link-to {:class "active"} "#wrap" "Profile")]
    [:li.slidebarLi (link-to "#education" "Certifications")]
    [:li.slidebarLi (link-to "#skills" "Skills")]
    [:li (link-to {:target "_blank"} "AndrewMarrone.pdf" "Resume")]]
   [:div#sidebarContent.grid_8
    [:div#profile.sidebarPanel
     [:h4 "Profile"]
     [:p profile]]
    [:div#education.sidebarPanel
     [:h4 "Certifications"]
     (link-to {:target "_blank"} "apluscert.pef" (image "aplus.jpg"))]
    [:div#skills.sidebarPanel
     [:div.grid_4.alpha
      [:h4 "Major"]
      [:ul (for [skill major-skills] [:li skill])]]
     [:div.grid_4.omega
      [:h4 "Lesser"]
      [:ul (for [skill lesser-skills] [:li skill])]]]]])



(defpartial job-item [{:keys [company-name start-year end-year url job-description]}]
  [:div.yearPanel {:id (str "y" start-year)}
   [:div.resumeEntry
    [:div.when.grid_3
     [:p end-year [:br] [:em start-year]]]
    [:div.description.grid_9
     [:h3 company-name]
     [:h5 (link-to url "Link")]
     [:p job-description]]
    [:div.clear]]])

(defpartial jobs-list [items]
  [:div#content.grid_13
   (map job-item items)])

(declare all-jobs)

(defpartial main-body []
  [:div#wrapContent.container_24
   (jobs-list all-jobs)
   (sidebar)])

(def underground-elephant
  {:company-name "Underground Elephant"
   :start-year "2011"
   :end-year "2011"
   :url "http://www.undergroundelephant.com"
   :job-description "Junior Systems Administrator pulling double duty as the Help Desk Lead. Responsible for local server setup, log monitoring, data backup, and security patches when not working with our vendors and contractors."})

(def family-health-centers
  {:company-name "Family Health Centers"
   :start-year "2010"
   :end-year "2011"
   :url "http://www.fhcsd.org"
   :job-description "Handled 50% of all tickets for the organization and still managed to automate essential processesses, write a knowledge base, and deploy company phones."})

(def ed-venture
  {:company-name "Ed Venture Partners"
   :start-year "2010"
   :end-year "2010"
   :url "http://www.edventurepartners.com"
   :job-description "Flying around the country with an expense account and a fancy suit, but it wasn't for me. While at EdVenture Partners I managed five concurrent marketing projects in three states, all centered around the 2011 Chevrolet lineup."})

(def marriage-equality
  {:company-name "Marriage Equality"
   :start-year "2008"
   :end-year "2010"
   :url "http://www.marriageequality.org"
   :job-description "What got me started. Thought I was keeping busy between classes - do some good, that sort of thing."})


(def all-jobs [underground-elephant family-health-centers ed-venture marriage-equality])

(defpartial layout [& content]
            (html5
              [:head
               [:title "Andrew Marrone"]
               (include-css "/css/reset.css" "/css/960_24.css" "/css/styles.css")
               (include-js "/js/jquery-1.4.2-min.js" "/js/smooth-scroll.js" "/js/custom.js")]
              [:body
               [:div#wrap
                (header)
                (main-body)]]))
