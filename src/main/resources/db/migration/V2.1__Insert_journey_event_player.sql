-- Inserting data into the JOURNEY table
INSERT INTO JOURNEY (journeyName, startLocationId, endLocationId, startTime, optimalJourney, optimalTravelTime)
VALUES
    ('Journey 1: easy', 'NSR:StopPlace:58366', 'NSR:StopPlace:59977', '2024-06-01 10:00:00', 2, 34020),
    ('Journey 2: middle', 'NSR:StopPlace:22329', 'NSR:StopPlace:9625', '2024-06-01 11:00:00', 6, 57780),
    ('Journey 3: hard', 'NSR:StopPlace:58366', 'NSR:StopPlace:198', '2024-06-01 12:00:00', 7, 123000);

-- Inserting data into the EVENT table
INSERT INTO EVENT (eventName, journeyId)
VALUES
    ('Event 1', 1),
    ('Event 2', 2),
    ('Event 3', 3);

-- Inserting data into the PLAYER table
INSERT INTO PLAYER (playerName, score, totalSteps, totalTravelTime, totalPlayTime, email, phoneNr, eventId)
VALUES
    ('Player 1', 100, 2, 1200, 120, 'player1@example.com', '12345678', 1),
    ('Player 2', 70, 3, 1800, 180, 'player2@example.com', '09876543', 2),
    ('Player 3', 50, 4, 2400, 240, 'player3@example.com', '11223344', 3);
