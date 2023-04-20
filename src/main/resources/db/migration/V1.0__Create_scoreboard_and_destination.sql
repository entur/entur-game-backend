CREATE TABLE destination (
    id VARCHAR(60) NOT NULL PRIMARY KEY,
    destination VARCHAR(60) NOT NULL
);

CREATE TABLE player_score (
	id serial PRIMARY KEY,
	nickname VARCHAR(30) NOT NULL,
	score INT,
	total_options INT NOT NULL,
	total_playtime VARCHAR(30) NOT NULL,
	total_travel_time VARCHAR(30) NOT NULL,
    from_destination VARCHAR(60) NOT NULL,
    to_destination VARCHAR(60) NOT NULL,
    FOREIGN KEY (from_destination) REFERENCES destination(id),
    FOREIGN KEY (to_destination) REFERENCES destination(id)
);