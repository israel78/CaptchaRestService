
CREATE TABLE USER (
               ID INTEGER DEFAULT NOT NULL AUTO_INCREMENT primary key ,
               NAME VARCHAR(100) NOT NULL,
               PASS VARCHAR(100) NOT NULL
                );

CREATE TABLE CAPTCHA_SETTINGS (
               ID INTEGER NOT NULL AUTO_INCREMENT,
               NUM_CHARACT INTEGER NOT NULL,
               ATTEMPS INTEGER NOT NULL,
               ALFA BOOLEAN NOT NULL
);

INSERT INTO USER (NAME,PASS) VALUES
('antonio','antonio');
INSERT INTO USER (NAME,PASS) VALUES
('pedro','pedro');

INSERT INTO CAPTCHA_SETTINGS (NUM_CHARACT,ATTEMPS,ALFA) VALUES (4,3,1);

