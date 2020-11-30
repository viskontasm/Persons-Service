CREATE TABLE logs (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  entry_date TIMESTAMP,
  logger VARCHAR,
  level VARCHAR,
  message VARCHAR,
  exception VARCHAR
);
