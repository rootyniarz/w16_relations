CREATE TABLE toy (
toy_id SERIAL NOT NULL,
what VARCHAR(32) NOT NULL,
color VARCHAR(32) NOT NULL,
PRIMARY KEY (toy_id)
);

CREATE TABLE owner (
owner_id SERIAL NOT NULL,
name VARCHAR(32) NOT NULL,
surname VARCHAR(32) NOT NULL,
phone VARCHAR(32) NOT NULL,
email VARCHAR(32) NOT NULL,
PRIMARY KEY (owner_id)
);

CREATE TABLE pet (
pet_id SERIAL NOT NULL,
name VARCHAR(32) NOT NULL,
breed VARCHAR(32) NOT NULL,
owner_id INT,
PRIMARY KEY (pet_id),
CONSTRAINT fk_pet_owner FOREIGN KEY (owner_id) REFERENCES owner (owner_id)
);

CREATE TABLE pet_toy
(
  pet_toy_id SERIAL NOT NULL,
  pet_id INT NOT NULL,
  toy_id INT NOT NULL,
  PRIMARY KEY (pet_toy_id),
  CONSTRAINT fk_pet_toy_pet
  FOREIGN KEY (pet_id) REFERENCES pet (pet_id),
  CONSTRAINT fk_pet_toy_toy
  FOREIGN KEY (toy_id) REFERENCES toy (toy_id)
);