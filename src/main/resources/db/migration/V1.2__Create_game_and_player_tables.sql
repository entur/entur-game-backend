CREATE TABLE game
(
    id VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    winner_id INTEGER,
    owner_id INTEGER,
    PRIMARY KEY (id)
);
CREATE TABLE player
(
    id       serial NOT NULL,
    nickname VARCHAR(255),
    game_id VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES game (id)
);

ALTER TABLE game ADD CONSTRAINT fk_game_winner_player_id FOREIGN KEY (winner_id) REFERENCES player (id);
ALTER TABLE game ADD CONSTRAINT fk_game_owner_id FOREIGN KEY (winner_id) REFERENCES player (id);