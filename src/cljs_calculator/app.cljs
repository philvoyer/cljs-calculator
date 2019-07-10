;; cljs-calculator/src/app.cljs by philvoyer

(ns ^:figwheel-hooks cljs-calculator.app
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent]
   [cljs-calculator.logic :as logic]
   [cljs-calculator.ui :as ui]))

(defonce text-app-title "cljs-calculator")
(defonce text-made-with "Programmed with Spacemacs, ClojureScript and Reagent.")
(defonce text-made-by "philvoyer")

(defonce app-states
  (reagent/atom {
    :title text-app-title
    :made-with text-made-with
    :made-by text-made-by}))

(defn render-page []
  [:div
   [ui/text-h1 (:title @app-states)]
   [ui/render-calculator]
   [ui/text-h4 (str "made with ♥ by " (:made-by @app-states))]
   [ui/text-h4 (:made-with @app-states)]
   [ui/text-link "https://github.com/philvoyer/cljs-calculator" "source code on github"]
   [ui/text-link "https://www.linkedin.com/in/philvoyer" "linkedin/philvoyer"]])

(defn get-app-element []
  (gdom/getElement "app"))

(defn mount [element]
  (reagent/render-component [render-page] element))

(defn mount-app-element []
  (when-let [element (get-app-element)]
    (mount element))
  (println "on application mount")
  (logic/start))

(defn ^:after-load on-reload []
  (mount-app-element)
  (println "on application reload")
  (logic/start))

(println "namespace loaded: app.cljs")

(mount-app-element)
