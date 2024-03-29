CREATE USER accela_user WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  CREATEDB
  NOCREATEROLE
  NOREPLICATION;

CREATE DATABASE acceladb
    WITH
    OWNER = accela_user
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE TABLE person(
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(25) NOT NULL,
	last_name VARCHAR(25) NOT NULL
);