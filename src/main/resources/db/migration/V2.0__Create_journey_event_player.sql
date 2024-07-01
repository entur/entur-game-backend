CREATE TABLE JOURNEY (
                         journeyId serial PRIMARY KEY,
                         journeyName VARCHAR(100) NOT NULL,
                         startLocationId VARCHAR(100) NOT NULL,
                         endLocationId VARCHAR(100) NOT NULL,
                         startTime TIMESTAMP NOT NULL,
                         optimalJourney INT NOT NULL,
                         optimalTravelTime INT NOT NULL
);

CREATE TABLE EVENT (
                       eventId serial PRIMARY KEY,
                       eventName VARCHAR(100) NOT NULL,
                       journeyId INT NOT NULL,
                       FOREIGN KEY (journeyId) REFERENCES JOURNEY(journeyId)
);

CREATE TABLE PLAYER (
                        playerId serial PRIMARY KEY,
                        playerName VARCHAR(30) NOT NULL,
                        score INT,
                        totalSteps INT,
                        totalTravelTime INT,
                        totalPlayTime INT,
                        email VARCHAR(255) NOT NULL,
                        phoneNr VARCHAR(16) NOT NULL, -- varchar because of leading zeroes and plus sign
                        eventId INT,
                        FOREIGN KEY (eventId) REFERENCES EVENT(eventId)
);
