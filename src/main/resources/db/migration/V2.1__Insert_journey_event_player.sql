-- Inserting data into the JOURNEY table
INSERT INTO JOURNEY (name, startLocationId, endLocationId, startTime, optimalJourney, optimalTravelTime)
VALUES
    ('Journey 1', 'LocationA', 'LocationB', '2022-12-01 10:00:00', 1, 120),
    ('Journey 2', 'LocationB', 'LocationC', '2022-12-02 11:00:00', 2, 150),
    ('Journey 3', 'LocationC', 'LocationD', '2022-12-03 12:00:00', 3, 180);

-- Inserting data into the EVENT table
INSERT INTO EVENT (name, journeyId)
VALUES
    ('Event 1', 1),
    ('Event 2', 2),
    ('Event 3', 3);

-- Inserting data into the PLAYER table
INSERT INTO PLAYER (name, score, totalSteps, totalTravelTime, totalPlayTime, email, phoneNr, eventId)
VALUES
    ('Player 1', 100, 2, 1200, 120, 'player1@example.com', '12345678', 1),
    ('Player 2', 70, 3, 1800, 180, 'player2@example.com', '09876543', 2),
    ('Player 3', 50, 4, 2400, 240, 'player3@example.com', '11223344', 3);
