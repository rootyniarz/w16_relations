CREATE TABLE event
(
  event_id SERIAL NOT NULL,
  event_name VARCHAR(32) NOT NULL,
  date_time TIMESTAMP WITH TIME ZONE NOT NULL,
  capacity INT NOT NULL,
  version INT NOT NULL,
  PRIMARY KEY (event_id),
  UNIQUE (event_name)
);

CREATE TABLE ticket
(
  ticket_id SERIAL NOT NULL,
  first_name VARCHAR(32) NOT NULL,
  last_name VARCHAR(32) NOT NULL,
  event_id INT NOT NULL,
  PRIMARY KEY (ticket_id),
  CONSTRAINT fk_ticket_event
    FOREIGN KEY (event_id)
        REFERENCES event (event_id)
);