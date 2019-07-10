;; cljs-calculator/src/ui.cljs by philvoyer

(ns cljs-calculator.ui
  (:require
   [reagent.core :as reagent]
   [cljs-calculator.logic :as logic]))

(defn spacer []
  [:div.spacer-vertical])

(defn text-h1 [text]
  [:h1 text])

(defn text-h2 [text]
  [:h2 text])

(defn text-h3 [text]
  [:h3 text])

(defn text-h4 [text]
  [:h4 text])

(defn text-link [href label]
  [:div.url
   [:a {:href href} label]])

(defn text-box [current]
  [:div.text-input
   [:input {:type "text"
            :value @current
            :on-change #(reset! current (-> % .-target .-value))}]])

(defn button [label callback]
  [:div
   [:button {:on-click callback} label]])

(defn button-callback-number-0 []
  (println "button pressed: 0")
  (logic/add-token "0"))

(defn button-callback-number-1 []
  (println "button pressed: 1")
  (logic/add-token "1"))

(defn button-callback-number-2 []
  (println "button pressed: 2")
  (logic/add-token "2"))

(defn button-callback-number-3 []
  (println "button pressed: 3")
  (logic/add-token "3"))

(defn button-callback-number-4 []
  (println "button pressed: 4")
  (logic/add-token "4"))

(defn button-callback-number-5 []
  (println "button pressed: 5")
  (logic/add-token "5"))

(defn button-callback-number-6 []
  (println "button pressed: 6")
  (logic/add-token "6"))

(defn button-callback-number-7 []
  (println "button pressed: 7")
  (logic/add-token "7"))

(defn button-callback-number-8 []
  (println "button pressed: 8")
  (logic/add-token "8"))

(defn button-callback-number-9 []
  (println "button pressed: 9")
  (logic/add-token "9"))

(defn button-callback-op-paren-left []
  (println "button pressed: (")
  (logic/add-token "("))

(defn button-callback-op-paren-right []
  (println "button pressed: )")
  (logic/add-token ")"))

(defn button-callback-op-addition []
  (println "button pressed: +")
  (logic/add-token "+"))

(defn button-callback-op-substraction []
  (println "button pressed: -")
  (logic/add-token "-"))

(defn button-callback-op-multiplication []
  (logic/add-token "*"))

(defn button-callback-op-division []
  (println "button pressed: /")
  (logic/add-token "/"))

(defn button-callback-op-power []
  (println "button pressed: ^")
  (logic/op-power))

(defn button-callback-op-dot []
  (println "button pressed: .")
  (logic/op-dot))

(defn button-callback-op-invert-sign []
  (println "button pressed: ±")
  (logic/op-invert-sign))

(defn button-callback-op-square []
  (println "button pressed: √")
  (logic/op-square))

(defn button-callback-op-percentage []
  (println "button pressed: %")
  (logic/op-percentage))

(defn button-callback-op-factorial []
  (println "button pressed: !")
  (logic/op-factorial))

(defn button-callback-op-clear []
  (println "button pressed: C")
  (logic/op-clear))

(defn button-callback-op-equal []
  (println "button pressed: =")
  (logic/op-equal))

(defn button-callback-do-nothing []
  (println "do nothing"))

(defn calculator-keypad []
  [:div.center
   [:table.table
    {:width "80%"}
    [:tbody
     [:tr
      [:td [button "C" button-callback-op-clear]]
      [:td [button "(" button-callback-op-paren-left]]
      [:td [button ")" button-callback-op-paren-right]]
      [:td [button "/" button-callback-op-division]]]
     [:tr
      [:td [button "^" button-callback-op-power]]
      [:td [button "√" button-callback-op-square]]
      [:td [button "%" button-callback-op-percentage]]
      [:td [button "!" button-callback-op-factorial]]]
     [:tr
      [:td [button "7" button-callback-number-7]]
      [:td [button "8" button-callback-number-8]]
      [:td [button "9" button-callback-number-9]]
      [:td [button "*" button-callback-op-multiplication]]]
     [:tr
      [:td [button "4" button-callback-number-4]]
      [:td [button "5" button-callback-number-5]]
      [:td [button "6" button-callback-number-6]]
      [:td [button "-" button-callback-op-substraction]]]
     [:tr
      [:td [button "1" button-callback-number-1]]
      [:td [button "2" button-callback-number-2]]
      [:td [button "3" button-callback-number-3]]
      [:td [button "+" button-callback-op-addition]]]
     [:tr
      [:td [button "±" button-callback-op-invert-sign]]
      [:td [button "0" button-callback-number-0]]
      [:td [button "." button-callback-op-dot]]
      [:td [button "=" button-callback-op-equal]]]
     ]]])

(defn render-calculator []
  [:div
   [text-h3 "expression"]
   [text-box logic/exp-as-str]
   [text-h3 "keypad"]
   [calculator-keypad]
   [spacer]])

(println "namespace loaded: ui.cljs")
