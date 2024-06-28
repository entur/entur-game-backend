-- Rename primary key for journey table
ALTER TABLE journey RENAME COLUMN id TO journey_id;

-- Rename primary key for event table
ALTER TABLE event RENAME COLUMN id TO event_id;