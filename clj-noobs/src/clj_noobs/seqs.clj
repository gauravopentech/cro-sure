(ns clj-noobs.seqs)

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

; useing map to extract :real keyword
(map :real identities)

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))

; Mapping functions calls to a collection
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))
(stats [3 4 10])
; => (17 3 17/3)
(stats [80 1 44 13 6])
; => (144 5 144/5)

; using reduce to transform map list
(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {}
        {:max 30 :min 10})

; using reduce as a filter on hashmap
(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map)) {}
        {:human 4.1
         :critter 3.9})

(take 3 [1 2 3 4 5 6 7 8 9 10])
; => (1 2 3)
(drop 3 [1 2 3 4 5 6 7 8 9 10])
; => (4 5 6 7 8 9 10)

; take while
(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(take-while #(< (:month %) 3) food-journal)

; drop while
(drop-while #(< (:month %) 3) food-journal)

; both together
(take-while #(< (:month %) 4)
            (drop-while #(< (:month %) 2) food-journal))

; filter
(filter #(< (:human %) 5) food-journal)
(filter #(< (:month %) 3) food-journal)

; some
(some #(> (:critter %) 5) food-journal) ; returns true or nil
(some #(> (:critter %) 3) food-journal) ; returns true or nil
(some #(and (> (:critter %) 3) %) food-journal) ; returns the entry which returned true

; repeat
(concat (take 8 (repeat "na")) ["Batman!"])

; repeatedly
(take 3 (repeatedly (fn [] (rand-int 10))))

; custom lazy sequence
(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(take 10 (even-numbers))
