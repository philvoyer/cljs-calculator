;; cljs-calculator/test/cljs_calculator/core_test.cljs by philvoyer

(ns cljs-calculator.core-test
    (:require
     [cljs.test :refer-macros [deftest is testing]]
     [cljs-calculator.logic :as logic]))

(deftest test-calculate-addition
  (is (= (logic/calculate "1 + 1") 2)))

(deftest test-calculate-substraction
  (is (= (logic/calculate "1 - 1") 0)))

(deftest test-calculate-multiplication
  (is (= (logic/calculate "1 * 1") 1)))

(deftest test-calculate-division
  (is (= (logic/calculate "1 / 1") 1)))

(deftest test-calculate-equation-1
  (is (= (logic/calculate "1 + 2") 3)))

(deftest test-calculate-equation-2
  (is (= (logic/calculate "1 + -1") 0)))

(deftest test-calculate-equation-3
  (is (= (logic/calculate "-1 - -1") 0)))

(deftest test-calculate-equation-4
  (is (= (logic/calculate "5 - 4") 1)))

(deftest test-calculate-equation-5
  (is (= (logic/calculate "5 * 2") 10)))

(deftest test-calculate-equation-6
  (is (= (logic/calculate "( 2 + 5 ) * 3") 21)))

(deftest test-calculate-equation-7
  (is (= (logic/calculate "10 / 2") 5)))

(deftest test-calculate-equation-8
  (is (= (logic/calculate "2 + 2 * 5 + 5") 17)))

(deftest test-calculate-equation-9
  (is (= (logic/calculate "2.8 * 3 - 1") 7.399999999999999)))

(deftest test-calculate-equation-10
  (is (= (logic/calculate "2 ^ 8") 256)))

(deftest test-calculate-equation-11
  (is (= (logic/calculate "2 ^ 8 * 5 - 1") 1279)))

(deftest test-calculate-equation-12
  (is (= (logic/calculate "3 + 4 * 5 / ( 3 + 2 )") 7)))

(deftest test-calculate-equation-13
  (is (= (logic/calculate "( ( ( 3 + -4 ) * ( 5 + -6 ) ) + ( 6 / -2 ) )") -2)))

(deftest test-calculate-equation-14
  (is (= (logic/calculate "4 + ( ( 2.25 + 1.75 ) * ( 2 ^ 2 ) )") 20)))

(deftest test-calculate-equation-15
  (is (= (logic/calculate "( ( ( 2 ^ 4 ) + ( 0.5 - ( 1.5 * -1 ) ) ) * 2 ) ^ 0.5") 6)))
