create table ADDRESS (
address_id SERIAL not null,
country VARCHAR(32) not null,
city VARCHAR(32) not null,
postal_code VARCHAR(32) not null,
address VARCHAR(32) not null,
primary key (address_id)
);