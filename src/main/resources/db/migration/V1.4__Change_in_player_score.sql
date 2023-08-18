ALTER TABLE player_score
ADD email VARCHAR(255);

ALTER TABLE player_Score
RENAME COLUMN nickname TO name;

ALTER TABLE player
RENAME COLUMN nickname TO name;




