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
    ('Player 2', 'player2@example.com', '12345679'),
    ('Player 3', 'player3@example.com', '12345680'),
    ('Player 4', 'player4@example.com', '12345681'),
    ('Player 5', 'player5@example.com', '12345682'),
    ('Player 6', 'player6@example.com', '12345683'),
    ('Player 7', 'player7@example.com', '12345684'),
    ('Player 8', 'player8@example.com', '12345685'),
    ('Player 9', 'player9@example.com', '12345686'),
    ('Player 10', 'player10@example.com', '12345687'),
    ('Player 11', 'player11@example.com', '12345688'),
    ('Player 12', 'player12@example.com', '12345689'),
    ('Player 13', 'player13@example.com', '12345690'),
    ('Player 14', 'player14@example.com', '12345691'),
    ('Player 15', 'player15@example.com', '12345692'),
    ('Player 16', 'player16@example.com', '12345693'),
    ('Player 17', 'player17@example.com', '12345694'),
    ('Player 18', 'player18@example.com', '12345695'),
    ('Player 19', 'player19@example.com', '12345696'),
    ('Player 20', 'player20@example.com', '12345697');

-- Inserting data into the SCORE table
-- Ensure event_id and player_id refer to existing records in EVENT and PLAYER tables
INSERT INTO SCORE (score_value, total_step_number, total_travel_time, total_play_time, event_id, player_id)
VALUES
    (100, 2, 34020, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event1:easy'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 1')),
    (70, 3, 340200, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event1:easy'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 2')),
    (50, 4, 3402000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event1:easy'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 3')),
    (100, 6, 57780, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event2:middle'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 1')),
    (70, 7, 577800, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event2:middle'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 2')),
    (50, 8, 5778000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event2:middle'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 3')),
    (100, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event3:hard'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 1')),
    (70, 8, 1230000, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event3:hard'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 2')),
    (50, 9, 12300000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event3:hard'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 3')),
    (100, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 1')),
    (50, 9, 12300000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 2')),
    (70, 8, 1230000, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 3')),
    (20, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 4')),
    (34, 9, 12300000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 5')),
    (12, 8, 1230000, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 6')),
    (1, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 7')),
    (2, 9, 12300000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 8')),
    (4, 8, 1230000, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 9')),
    (3, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 10')),
    (5, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 1')),
    (9, 9, 12300000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 2')),
    (12, 8, 1230000, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 3')),
    (11, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 4')),
    (13, 9, 12300000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 5')),
    (14, 8, 1230000, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 6')),
    (0, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player 7'));
