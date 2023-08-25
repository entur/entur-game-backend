DELETE FROM player_Score;

ALTER TABLE player_score
    ADD CONSTRAINT unique_name_constraint UNIQUE (name);

ALTER TABLE player_score
    ADD CONSTRAINT unique_email_constraint UNIQUE (email);

ALTER TABLE player_score
    ADD CONSTRAINT unique_phone_number_constraint UNIQUE (phone_number);