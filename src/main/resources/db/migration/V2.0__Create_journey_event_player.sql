CREATE TABLE EVENT (
    event_id serial PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    start_location_id VARCHAR(100) NOT NULL,
    end_location_id VARCHAR(100) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    optimal_step_number INT NOT NULL,
    optimal_travel_time INT NOT NULL
);

CREATE TABLE PLAYER (
    player_id serial PRIMARY KEY,
    player_name VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(16) NOT NULL UNIQUE -- varchar because of leading zeroes and plus sign
);

CREATE TABLE SCORE (
    score_id serial PRIMARY KEY,
    score_value INT NOT NULL,
    total_step_number INT NOT NULL,
    total_travel_time INT NOT NULL,
    total_play_time INT NOT NULL,
    event_id INT NOT NULL,
    player_id INT NOT NULL,
    FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
    FOREIGN KEY (player_id) REFERENCES PLAYER(player_id)
);