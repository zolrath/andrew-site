(ns andrew-site.views.common
  (:use andrew-site.views.content
        noir.core
        hiccup.core
        hiccup.page-helpers))

(defpartial split-name [name]
  (let [[first last] (clojure.string/split name #"\s")]
    (list (link-to {:class "active"} "#wrap" first [:br] [:em last]))))

(defpartial header [topright title name]
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
       [:h2 topright]]]
     [:div#information
      [:div#info.grid_13
       [:h2 title]]
      [:div#name.grid_11
       [:h1 name]]]]]])

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
     [:p certifications]]
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

(defpartial main-body []
  [:div#wrapContent.container_24
   (jobs-list all-jobs)
   (sidebar)])

(defpartial layout [& content]
  (html5
   [:head
    [:title "Andrew Marrone"]
    (include-css "/css/reset.css" "/css/960_24.css" "/css/styles.css")
    (include-js "/js/jquery-1.4.2-min.js" "/js/smooth-scroll.js" "/js/custom.js")]
   [:body
    [:div#wrap
     (header top-right desired-title (split-name header-name))
     (main-body)]]))
