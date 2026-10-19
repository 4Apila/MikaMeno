DROP TABLE IF EXISTS tapahtuma_osallistujat;
DROP TABLE IF EXISTS tapahtuma;
DROP TABLE IF EXISTS kayttaja;

CREATE TABLE kayttaja (
    kayttajatunnus VARCHAR(255) PRIMARY KEY,
    salasana_hash VARCHAR(255) NOT NULL,
    rooli VARCHAR(50) NOT NULL
);

INSERT INTO kayttaja (kayttajatunnus, salasana_hash, rooli)
VALUES
('user', '$2a$10$yWF76fZnXUeH..Vzh.T/6OdKzpqF2dkCCPzrkKC2cCXesM0CQQu5', 'USER'),
('admin', '$2a$10$/IWA0v20JnmRyacyF5EiYes1j3qSlRgMNFU66rsjNu4FI8kzKqPye', 'ADMIN'),
('johanna', '$2a$10$QMzuzJ1SxPKDsqDud.srtOWkumOx0ZyGmVE81p2eF1xKlRIKV93uq', 'ADMIN'),
('minna', '$2a$10$YR2S9kEJflfClppjMT0OeeWMcg4b2szEPJv4mZy93j18DnRZbmc6G', 'USER'),
('miro', '$2a$10$ZugkQsxHmQDSAKAJQgSrHehS4WRyVsZOGqo7zX3c5P0DodEfh7Ka6', 'USER'),
('antti', '$2a$10$cwZvQFmFTDHOLnStgurJxO8YEcOxqFLd1LbIA.6JWyrj7Qoe0hGcq', 'USER'),
('lera', '$2a$10$dz.3ePqJAOHbXS4QWHh8zuRHlKQ0wznsNgooDvMrcNJQmoWaksjMe', 'USER'),
('bardia', '$2a$10$XsFiT6q1lMXX.xbKSLBK2.pk9pUGVGSTmG51UNADRRgxQHYGxlgki', 'USER'),
('esko', '$2a$10$Ka0onTnIhgtbJ22MCDp9../QbSQJq/QUPBXQsxV/Y0tTuxpQ8d.JS', 'USER');

CREATE TABLE tapahtuma (
    id BIGSERIAL PRIMARY KEY,
    nimi VARCHAR(255) NOT NULL,
    alkupv TIMESTAMP NOT NULL,
    loppupv TIMESTAMP NOT NULL,
    paikka VARCHAR(255) NOT NULL,
    kuvaus VARCHAR(2000),
    max_osallistujat INTEGER,
    kayttajatunnus VARCHAR(255),
    FOREIGN KEY (kayttajatunnus)
        REFERENCES kayttaja(kayttajatunnus)
);

INSERT INTO tapahtuma
    (nimi, alkupv, loppupv, paikka, kuvaus,
     max_osallistujat, kayttajatunnus)
VALUES
    ('Halloween', '2026-11-30 16:00:00',
     '2026-11-30 23:30:00', 'Aapelinkatu',
     'Halloweenin viettoa, kurpitsan kaiverrusta ja kauhuleffa', 5, 'johanna'),

     ('Kirjamessut', '2026-11-23 10:00:00',
     '2026-11-25 21:00:00', 'Messukeskus',
     'Helsingin kirjamessut', null, 'antti'),

     ('Kirjakerho: viides vuodenaika', '2026-12-02 18:15:00',
     '2026-12-02 21:15:00', 'Köydenpunojankatu',
     'Keskustelua kirjasta Viides vuodenaika. Kirjailija N. K. Jemisin', 8, 'lera'),

     ('Piparileivonnaiset', '2026-12-06 10:00:00',
     '2026-12-06 18:00:00', 'Aapelinkatu',
     'Perinteinen piparkakkuleivonta. OPM eli oma purkki mukaan!', 10, 'miro'),

     ('City of Dreams', '2026-10-26 18:15:00',
     '2026-10-26 21:00:00', 'Aapelinkatu',
     'Session #51: Let the sleeping dragon lie', 7, 'miro'),

     ('City of Dreams ANNIVERSARY', '2026-11-07 17:00:00',
     '2026-11-08 02:00:00', 'Ilmoitetaan myöhemmin',
     'Session #52: The Anniversary Edition', 8, 'miro');

CREATE TABLE tapahtuma_osallistujat (
    tapahtuma_id BIGINT NOT NULL,
    osallistujat_kayttajatunnus VARCHAR(255) NOT NULL,
    PRIMARY KEY (tapahtuma_id, osallistujat_kayttajatunnus),
    CONSTRAINT fk_osallistujat_tapahtuma
        FOREIGN KEY (tapahtuma_id)
        REFERENCES tapahtuma(id),
    CONSTRAINT fk_osallistujat_kayttaja
        FOREIGN KEY (osallistujat_kayttajatunnus)
        REFERENCES kayttaja(kayttajatunnus)
);

-- Testihaut
SELECT * FROM kayttaja;
SELECT * FROM tapahtuma;
SELECT * FROM tapahtuma_osallistujat;
