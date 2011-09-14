(ns andrew-site.views.content
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(def top-right
  (list "Interested? " (mail-to "jobs@andrewmarrone.com?subject=Employment%20Opportunity" "Contact me!")))

(def desired-title
  (list "Junior" [:br] [:em "Systems Administrator"]))

(def header-name "Andrew Marrone")

(def major-skills
  ["Shell Scripting" "Ubuntu" "CentOS" "OS X" "VMWare"])

(def lesser-skills
  ["ESXi" "Microsoft Exchange" "Windows Server 2003" "Active Directory"])

(def profile
  "Three years help desk experience while taking on more admin roles, regarded by peers and supervisors as a top-notch performer always eager to learn more.<br />
Working on my RCHSA certification when there is spare time.")

(def certifications
  (link-to {:target "_blank"} "apluscert.pef" (image "aplus.jpg")))

;; List jobs here, add to vector to display on page.

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
