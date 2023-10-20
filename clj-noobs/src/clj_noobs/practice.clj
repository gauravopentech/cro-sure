(ns clj-noobs.practice
  (:require [clojure.string :as s]))

; with recursion
(defn sum
  ([vals] (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (sum (rest vals) (+ (first vals) accumulating-total)))))

; with 'recur'
(defn fast_sum
  ([vals]
   (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (recur (rest vals) (+ (first vals) accumulating-total)))))

(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))

; composition of functions
; equivalent of (inc (* 2 3))
((comp inc *) 2 3)

; other comp example
(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

; (c-int character)
(def c-int (comp :intelligence :attributes))

; (c-str character)
(def c-str (comp :strength :attributes))

; (c-dex character)
(def c-dex (comp :dexterity :attributes))

; plain spell slots fn
(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))

; spell-slots with comp
(def spell-slots-comp (comp int inc #(/ % 2) c-int))

;------------------------------ 
; memoize

; unmemoized fn
(defn sleepy-identity
  "Returns the given value after 1 second"
  [x]
  (Thread/sleep 1000)
  x)

; takes 1 sec
(sleepy-identity "Mr. Fantastico")
; takes 1 sec
(sleepy-identity "Mr. Fantastico")

; memoized fn
(def memo-sleepy-identity (memoize sleepy-identity))

; takes 1 sec
(memo-sleepy-identity "Mr. Fantastico")

; runs without delay
(memo-sleepy-identity "Mr. Fantastico")
