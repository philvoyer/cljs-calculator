# cljs-calculator

A minimalistic calculator in ClojureScript.

Based on the Dijkstra's Shunting Yard algorithm to solve equation in reverse polish notation.

Use spaces as delimiter if expression is typed directly in the textbox.

Programmed with `Spacemacs`, `ClojureScript` and `Reagent`.

Hot reload of code with `Figwheel.main`.

## Technologies

- [Emacs 26.2](https://www.gnu.org/software/emacs/)
- [Spacemacs 0.200.13](https://github.com/syl20bnr/spacemacs)
- [Clojure 1.9.0](https://clojure.org)
- [ClojureScript 1.10.339](https://clojurescript.org)
- [Figwheel.main 0.1.9](https://figwheel.org)
- [Reagent 0.8.1](https://reagent-project.github.io)

## Compilation

1. Install dependencies listed above.
2. Type this command line in shell from project folder: `lein fig:build` for debug or `lein fig:min` for release.
3. Open web browser at : http://localhost:9500/.
4. Open web browser at : http://localhost:9500/figwheel-extra-main/auto-testing for unit tests.
