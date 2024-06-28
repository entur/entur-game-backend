CREATE TABLE journey (
    id serial PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    startId INT NOT NULL,
    stopId INT NOT NULL,
    dateTime TIMESTAMP NOT NULL,
    description VARCHAR(255),
    optimalJourney INT NOT NULL,
    optimalTravelTime INT NOT NULL,
    total_options INT NOT NULL,
    total_playtime VARCHAR(30) NOT NULL,
    total_travel_time VARCHAR(30) NOT NULL
);

CREATE TABLE event (
    id serial NOT NULL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    journey_id INT NOT NULL,
    FOREIGN KEY (journey_id) REFERENCES journey(id)
);