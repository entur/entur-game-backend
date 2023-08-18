ALTER TABLE player_score
ALTER COLUMN phone_number TYPE integer USING phone_number::integer;