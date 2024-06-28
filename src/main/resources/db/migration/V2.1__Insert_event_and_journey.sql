-- Inserting data into the journey table
INSERT INTO journey (name, startId, stopId, dateTime, description, optimalJourney, optimalTravelTime, total_options, total_playtime, total_travel_time)
VALUES ('Journey 1', 1, 2, '2022-12-01 10:00:00', 'This is a description for Journey 1', 1, 120, 3, '01:00:00', '02:00:00'),
       ('Journey 2', 2, 3, '2022-12-02 11:00:00', 'This is a description for Journey 2', 2, 150, 4, '01:30:00', '02:30:00');

-- Inserting data into the event table
INSERT INTO event (name, journey_id)
VALUES ('Event 1', 1),
       ('Event 2', 2);