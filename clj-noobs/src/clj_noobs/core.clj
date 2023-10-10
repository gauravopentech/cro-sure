(ns clj-noobs.core
  (:gen-class)
  (:require [clj-noobs.vampire-hunter :as vh]))

(defn -main
  "Calling Vampire hunter"
  [& args]

  (println
   (vh/unify)
   (time (vh/vampire-related-details 0))
   (time (def mapped-details (map vh/vampire-related-details (range 0 1000000))))))
