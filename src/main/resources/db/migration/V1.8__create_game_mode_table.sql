CREATE TABLE game_mode (
    id VARCHAR(30) NOT NULL PRIMARY KEY,
    game_name VARCHAR(100) NOT NULL,
    game_description VARCHAR(100) NOT NULL,
    nsr_start_location VARCHAR(100) NOT NULL,
    nsr_start_name VARCHAR(100) NOT NULL,
    nsr_start_latitude DECIMAL NOT NULL,
    nsr_start_longitude DECIMAL NOT NULL,
    nsr_end_location VARCHAR(100) NOT NULL,
    nsr_end_name VARCHAR(100) NOT NULL,
    difficulty VARCHAR(50),
    optimal_route INTEGER,
    optimal_travel_time INTEGER
)