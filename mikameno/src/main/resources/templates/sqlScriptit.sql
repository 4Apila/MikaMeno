-- Poistetaan taulut oikeassa järjestyksessä riippuvuuksien takia
DROP TABLE IF EXISTS tapahtuma;
DROP TABLE IF EXISTS kayttaja;

-- owner-taulu
CREATE TABLE tapahtuma (
    id BIGSERIAL PRIMARY KEY,
    nimi VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    city VARCHAR(50),
    ssn VARCHAR(15) NOT NULL,
    yob INT
);

INSERT INTO owner (firstname, lastname, city, ssn, yob) 
VALUES 
('Maria', 'Marison', 'Manse', '150574-113I', 1974),
('Minnie', 'Minison', 'Turku', '250170-111M', 1970);

-- car-taulu
CREATE TABLE car (
    id BIGSERIAL PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    color VARCHAR(50) NOT NULL,
    register_number VARCHAR(50) NOT NULL,
    production_year INT,
    price BIGINT,
    ownerid BIGINT REFERENCES owner(id)
);

INSERT INTO car (brand, model, color, register_number, production_year, price, ownerid) 
VALUES 
('Ford', 'Taunus', 'blue', 'xxx-111', 1979, 12000, 1), 
('Volkswagen', 'Golf', 'red', 'yyy-222', 1990, 7000, 1);

-- application_user-taulu
CREATE TABLE kayttaja (
    id BIGSERIAL PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL
);

INSERT INTO application_user (firstname, lastname, username, password, role) 
VALUES 
('Minna', 'Pellikka', 'user', '$2a$10$1DTvwpXVBArGFixHBuzVJObjTuXhIOkx5pse6KsYs8/C2ckxnGEou', 'USER'),
('Admin', 'User', 'admin', '$2a$10$cDZgyF4xaPMmmoRW3OVcmuf.8o2YSx8.M7CeRKqi.1PVw.t3E8uEC', 'ADMIN');

-- Testihaut
SELECT * FROM car;
SELECT * FROM owner;
SELECT * FROM application_user;