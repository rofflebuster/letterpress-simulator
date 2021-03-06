(ns letterpress-bot.board
  "Functions for viewing and updating the tile map.")

(defn defended?
  "True if the given tile is defended and false otherwise."
  [m [x y :as tile]]
  (if-let [c (m tile)]
    (->> [[0 1] [1 0] [-1 0] [0 -1]]
         (map (fn [[dx dy]] [(+ dx x) (+ dy y)]))
         (filter #(= c (m %)))
         count
         (<= 2))
    false))

(defn score
  "Returns the score for the provided player."
  [m player]
  (->> m
       vals
       (filter #(= player %))
       count))

(defn update
  "
  Return a new board with the tiles owned by the given player.
  This does not validate that the tile sequence forms a legal word.
  "
  [m player tiles]
  (into m (map #(vector % player) tiles)))
