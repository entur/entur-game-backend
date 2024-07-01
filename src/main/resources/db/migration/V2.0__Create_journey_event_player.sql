CREATE TABLE EVENT (
    eventId serial PRIMARY KEY,
    eventName VARCHAR(100) NOT NULL,
    startLocationId VARCHAR(100) NOT NULL,
    endLocationId VARCHAR(100) NOT NULL,
    startTime TIMESTAMP NOT NULL,
    optimalStepNumber INT NOT NULL,
    optimalTravelTime INT NOT NULL
);

CREATE TABLE PLAYER (
    playerId serial PRIMARY KEY,
    playerName VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    phoneNumber VARCHAR(16) NOT NULL UNIQUE -- varchar because of leading zeroes and plus sign
);

CREATE TABLE SCORE (
    scoreId serial PRIMARY KEY,
    scoreValue INT NOT NULL,
    totalStepNumber INT NOT NULL,
    totalTravelTime INT NOT NULL,
    totalPlayTime INT NOT NULL,
    eventId INT NOT NULL,
    playerId INT NOT NULL,
    FOREIGN KEY (eventId) REFERENCES EVENT(eventId),
    FOREIGN KEY (playerId) REFERENCES PLAYER(playerId)
);