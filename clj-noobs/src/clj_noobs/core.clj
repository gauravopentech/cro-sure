(ns clj-noobs.core
  (:gen-class)
  (:require [clj-noobs.vampire-hunter :as vh]
            [clj-noobs.fwpd.vamp-search :as vs]
            [clj-noobs.pegthings.plaything :as pt]))

(defn -main
  "Calling Vampire hunter"
  [& args]
  (println "Get ready to play peg thing!") (pt/prompt-rows))

;  (println
   ; (vh/unify)
   ; (vs/vamp-suspects (vs/glitter-filter 3 (vs/mapify (vs/parse (slurp vs/filename)))))
   ;(time (vh/vampire-related-details 0))
;   ))
