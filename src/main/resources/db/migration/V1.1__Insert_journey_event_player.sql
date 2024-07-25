-- Inserting data into the PLAYER table
INSERT INTO PLAYER (player_name, email, phone_number)
VALUES
    ('Player1', 'player1@example.com', '10000001'),
    ('Player2', 'player2@example.com', '10000002'),
    ('Player3', 'player3@example.com', '10000003'),
    ('Player4', 'player4@example.com', '10000004'),
    ('Player5', 'player5@example.com', '10000005'),
    ('Player6', 'player6@example.com', '10000006'),
    ('Player7', 'player7@example.com', '10000007'),
    ('Player8', 'player8@example.com', '10000008'),
    ('Player9', 'player9@example.com', '10000009'),
    ('Player10', 'player10@example.com', '10000010'),
    ('Player11', 'player11@example.com', '10000011'),
    ('Player12', 'player12@example.com', '10000012'),
    ('Player13', 'player13@example.com', '10000013'),
    ('Player14', 'player14@example.com', '10000014'),
    ('Player15', 'player15@example.com', '10000015'),
    ('Player16', 'player16@example.com', '10000016'),
    ('Player17', 'player17@example.com', '10000017'),
    ('Player18', 'player18@example.com', '10000018'),
    ('Player19', 'player19@example.com', '10000019'),
    ('Player20', 'player20@example.com', '10000020');

-- Inserting data into the EVENT table
INSERT INTO EVENT (event_name, start_location_id, end_location_id, start_time, optimal_step_number, optimal_travel_time, is_active)
VALUES
    ('event1:easy', 'NSR:StopPlace:58366', 'NSR:StopPlace:59977', '2024-06-01 10:00:00', 2, 34020, false),
    ('event2:middle', 'NSR:StopPlace:22329', 'NSR:StopPlace:9625', '2024-06-01 11:00:00', 6, 57780, false),
    ('event3:hard', 'NSR:StopPlace:58366', 'NSR:StopPlace:198', '2024-06-01 12:00:00', 7, 123000, false),
    ('event4', 'NSR:StopPlace:58366', 'NSR:StopPlace:198', '2024-06-01 12:00:00', 7, 123000, true);

-- Inserting data into the SCORE table
-- Ensure event_id and player_id refer to existing records in EVENT and PLAYER tables
INSERT INTO SCORE (score_value, total_step_number, total_travel_time, total_play_time, event_id, player_id)
VALUES
    (100, 2, 34020, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event1:easy'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player1')),
    (70, 3, 340200, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event1:easy'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player2')),
    (50, 4, 3402000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event1:easy'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player3')),
    (100, 6, 57780, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event2:middle'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player1')),
    (70, 7, 577800, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event2:middle'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player2')),
    (50, 8, 5778000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event2:middle'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player3')),
    (100, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event3:hard'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player1')),
    (70, 8, 1230000, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event3:hard'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player2')),
    (50, 9, 12300000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event3:hard'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player3')),
    (100, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player1')),
    (50, 9, 12300000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player2')),
    (70, 8, 1230000, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player3')),
    (20, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player4')),
    (34, 9, 12300000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player5')),
    (12, 8, 1230000, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player6')),
    (1, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player7')),
    (2, 9, 12300000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player8')),
    (4, 8, 1230000, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player9')),
    (3, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player10')),
    (5, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player11')),
    (9, 9, 12300000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player12')),
    (12, 8, 1230000, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player13')),
    (11, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player14')),
    (13, 9, 12300000, 200, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player15')),
    (14, 8, 1230000, 150, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player16')),
    (0, 7, 123000, 100, (SELECT event_id FROM EVENT WHERE event_name = 'event4'), (SELECT player_id FROM PLAYER WHERE player_name = 'Player17'));
