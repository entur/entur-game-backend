-- Inserting data into the EVENT table
INSERT INTO EVENT (event_name, start_location_id, end_location_id, start_time, optimal_step_number, optimal_travel_time, is_active)
VALUES
    ('event1:easy', 'NSR:StopPlace:58366', 'NSR:StopPlace:59977', '2024-06-01 10:00:00', 2, 34020, false),
    ('event2:middle', 'NSR:StopPlace:22329', 'NSR:StopPlace:9625', '2024-06-01 11:00:00', 6, 57780, false),
    ('event3:hard', 'NSR:StopPlace:58366', 'NSR:StopPlace:198', '2024-06-01 12:00:00', 7, 123000, false),
    ('event4', 'NSR:StopPlace:58366', 'NSR:StopPlace:198', '2024-06-01 12:00:00', 7, 123000, true);

-- Inserting data into the PLAYER table
INSERT INTO PLAYER (player_name, email, phone_number)
VALUES
    ('Player 1', 'player1@example.com', '12345678'),
    ('Player 2', 'player2@example.com', '09876543'),
    ('Player 3', 'player3@example.com', '11223344');

-- Inserting data into the SCORE table
INSERT INTO SCORE (score_value, total_step_number, total_travel_time, total_play_time, event_id, player_id)
VALUES
    (100, 2, 34020, 100, 1, 1),
    (70, 3, 340200, 150, 1, 2),
    (50, 4, 3402000, 200, 1, 3),
    (100, 6, 57780, 100, 2, 1),
    (70, 7, 577800, 150, 2, 2),
    (50, 8, 5778000, 200, 2, 3),
    (100, 7, 123000, 100, 3, 1),
    (70, 8, 1230000, 150, 3, 2),
    (50, 9, 12300000, 200, 3, 3);
