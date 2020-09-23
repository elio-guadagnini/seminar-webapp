CREATE TABLE Seminar(
   id            INTEGER PRIMARY KEY,
   name          VARCHAR(255) NOT NULL,
   description   VARCHAR(255),
   location      VARCHAR(255) NOT NULL,
   totalSeats    NUMERIC NOT NULL,
   start     DATETIME NOT NULL
);