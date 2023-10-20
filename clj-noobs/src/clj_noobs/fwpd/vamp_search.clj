(ns clj-noobs.fwpd.vamp-search
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def filename (io/resource
               "suspects.csv"))
; (-> (ClassLoader/getSystemResource *file*) clojure.java.io/file .getParent)
; (slurp filename)

(def vamp-keys [:name :glitter-index])

(defn str->int [str]
  (Integer. str))

(def conversions {:name identity :glitter-index str->int})

(defn convert [vamp-key value]
  ((get conversions vamp-key) value))

; (parse (slurp filename))
(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(string/split % #",")
       (string/split string #"\n")))

; (mapify (parse (slurp filename)))
(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

; (glitter-filter 3 (mapify (parse (slurp filename))))
(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

; (vamp-suspects (glitter-filter 3 (mapify (parse (slurp filename)))))
(defn vamp-suspects
  [filteredrecords]
  (map :name filteredrecords))

(def validators {:name (fn [record val] (= (:name record) val))
                 :glitter-index (fn [record val] (= (:glitter-index record) val))})

; try validate again
(defn validate
  [coll record]
  (some (fn [rec] (and)) coll))

; (append (mapify (parse (slurp filename))) "Zrin" 7)
(defn append
  ([coll toadd]
   (into coll toadd))
  ([coll nname glitter-index]
   (into coll [(assoc {} :name nname :glitter-index glitter-index)])))

; (record->str (append (mapify (parse (slurp filename))) "Zrin" 7))
(defn record->str
  [records]
  (string/join "\n" (map #(str (:name %) "," (:glitter-index %)) records)))

; (savetocsv (append (mapify (parse (slurp filename))) "Zrin" 7))
(defn savetocsv
  [records]
  (spit filename (record->str records)))

