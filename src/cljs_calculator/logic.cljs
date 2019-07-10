;; cljs-calculator/src/logic.cljs by philvoyer

(ns cljs-calculator.logic
  (:require
   [clojure.string :as string]
   [cljs.reader :as reader]
   [cljs.core :as cljs]
   [reagent.core :as reagent]))

(def exp-as-seq (reagent/atom []))
(def exp-as-str (reagent/atom ""))

(def token-new (reagent/atom nil))
(def token-last (reagent/atom nil))

(def trace #(do (println %) %))

(defn trace-exp []
  (println "expression as seq:" @exp-as-seq)
  (println "expression as str:" @exp-as-str)
  (println "new token:" @token-new)
  (println "last token:" @token-last))

(defn clear []
  (reset! exp-as-seq [])
  (reset! exp-as-str "")
  (reset! token-new nil)
  (reset! token-last nil)
  (trace-exp))

(defn is-number? [token]
  (cond
    (= token "(") false
    (= token ")") false
    (= token "^") false
    (= token ".") true
    :else (number? (reader/read-string token))))

(defn pop-last-token []
  (when (> (count @exp-as-seq) 0)
    (swap! exp-as-seq
      (fn [current-state]
        (pop current-state)))))

(defn update-exp []
  (when (and (is-number? @token-new) (is-number? (str (peek @exp-as-seq))))
    (pop-last-token))
  (swap! exp-as-seq
    (fn [current-state]
      (into [] (conj current-state
        @token-last))))
  (swap! exp-as-str (fn [] (string/join " " @exp-as-seq)))
  (trace-exp))

(defn add-token [token]
  (reset! token-new token)
  (if (is-number? @token-new)
    (if (is-number? @token-last)
      (swap! token-last
             (fn [current-state]
               (str current-state @token-new)))
      (reset! token-last @token-new))
    (reset! token-last @token-new))
  (update-exp))

(defn op-dot []
  (when (= (string/index-of @token-last ".") nil)
    (add-token ".")))

(defn op-invert-sign []
  (when (is-number? @token-last)
    (let [last-token-as-number (reader/read-string @token-last)]
      (reset! token-last (str (* last-token-as-number -1.0)))
      (update-exp))))

(defn op-power []
  (add-token "^"))

(defn op-square []
  (when (is-number? @token-last)
    (let [last-token-as-number (reader/read-string @token-last)]
      (reset! exp-as-seq [])
      (reset! token-last (str (Math/sqrt last-token-as-number)))
      (update-exp))))

(defn op-percentage []
  (when (is-number? @token-last)
    (let [last-token-as-number (reader/read-string @token-last)]
      (reset! exp-as-seq [])
      (reset! token-last (str (/ last-token-as-number 100.0)))
      (update-exp))))

(defn op-factorial []
  (when (is-number? @token-last)
    (let [last-token-as-number (reader/read-string @token-last)]
      (when (and (integer? last-token-as-number) (> last-token-as-number 0))
        (reset! exp-as-seq [])
        (reset! token-last (str (reduce * (range 1 (inc last-token-as-number)))))
        (update-exp)))))

(defn op-clear []
  (println "clear expression")
  (clear))

(defn parentheses-balanced? []
  (let [unique-token-count (frequencies @exp-as-seq)]
    (= (unique-token-count "(") (unique-token-count ")"))))

(defn compute-power [x n]
  (Math/pow x n))

(defn string-tokenizer [str]
  (let [initial-state '()
        regex-delimiter #" "
        regex-number #"^\d+$"
        token-splitter #(string/split % regex-delimiter)
        is-number? #(and % (re-find regex-number %))]
    (reverse (reduce
      (fn [[t & ts :as token-list] token]
        (if (and (is-number? token) (is-number? t))
          (cons (str t token) ts)
          (cons token token-list)))
      initial-state, (token-splitter str)))))

(defn dijkstra-shunting-yard [token-list]
  (let [op-precedence-map {"+" 0, "-" 0, "*" 1, "/" 1, "^" 2}]
    (flatten (reduce
      (fn [[reverse-polish-solver stack] token]
        (let [weak-op? #(and (contains? op-precedence-map %) (<= (op-precedence-map token) (op-precedence-map %)))
              token-is-not-left-paren? #(not= "(" %)]
          (cond
            (= token "(") [reverse-polish-solver (cons token stack)]
            (= token ")") [(vec (concat reverse-polish-solver (take-while token-is-not-left-paren? stack))) (rest (drop-while token-is-not-left-paren? stack))]
            (contains? op-precedence-map token) [(vec (concat reverse-polish-solver (take-while weak-op? stack))) (cons token (drop-while weak-op? stack))]
            :else [(conj reverse-polish-solver token) stack])))
      [[] ()] token-list))))

(defn reverse-polish-solver [token-list]
  (let [operator {"+" +, "-" -, "*" *, "/" /, "^" compute-power}]
    (first (reduce
      (fn [stack token]
        (if (contains? operator token)
          (cons ((operator token) (second stack) (first stack)) (drop 2 stack))
          (cons (reader/read-string token) stack)))
        [] token-list))))

(def calculator
  (comp reverse-polish-solver dijkstra-shunting-yard string-tokenizer))

(def calculator-dbg
  (comp reverse-polish-solver trace dijkstra-shunting-yard trace string-tokenizer))

(defn calculate [exp]
  (println "calculate:" exp)
  (calculator-dbg exp)
  (println (calculator exp))
  (calculator exp))

(defn evaluate []
  (if (parentheses-balanced?)
    (let [exp-value (calculate @exp-as-str)]
      (reset! exp-as-seq [exp-value])
      (reset! exp-as-str (str exp-value))
      (reset! token-new nil)
      (reset! token-last @exp-as-str))
    (do
      (reset! exp-as-seq [])
      (reset! exp-as-str "unbalanced ( )")
      (reset! token-new nil)
      (reset! token-last nil)))
  (trace-exp))

(defn op-equal []
  (println "evaluate expression:" @exp-as-str)
  (try (evaluate)
    (catch js/Object e "exception has been caught during expression evaluation")))

(defn start []
  (println "start application logic")
  (clear))

(println "namespace loaded: logic.cljs")
