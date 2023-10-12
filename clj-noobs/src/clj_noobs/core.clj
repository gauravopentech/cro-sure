(ns clj-noobs.core
  (:gen-class)
  (:require [clj-noobs.vampire-hunter :as vh] [clj-noobs.fwpd.vamp-search :as vs]))

(defn -main
  "Calling Vampire hunter"
  [& args]

  (println
   ; (vh/unify)
   (vs/vamp-suspects (vs/glitter-filter 3 (vs/mapify (vs/parse (slurp vs/filename)))))
   ;(time (vh/vampire-related-details 0))
   ))
