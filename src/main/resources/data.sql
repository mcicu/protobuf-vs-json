DROP TABLE IF EXISTS beacons;

CREATE TABLE beacons (
  id VARCHAR(250)  PRIMARY KEY,
  latitude Number(8,3) NOT NULL,
  longitude Number(8,3) NOT NULL
);

INSERT INTO beacons (id, latitude, longitude) VALUES
  ('beacon-1-pacific', 123.111, 222.111),
  ('beacon-2-atlantic', 133.222, 212.222),
  ('beacon-3-mediterranean', 223.333, 122.333);
