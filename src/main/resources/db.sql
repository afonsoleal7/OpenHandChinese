DROP DATABASE IF EXISTS javabank;
CREATE DATABASE javabank;
USE javabank;

DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS account;

CREATE TABLE customer (
  id            INT AUTO_INCREMENT PRIMARY KEY,
  email         VARCHAR(255),
  first_name    VARCHAR(255),
  last_name     VARCHAR(255),
  phone         VARCHAR(255),
  creation_time DATETIME,
  update_time   DATETIME
);

CREATE TABLE account(
  account_type  VARCHAR(31) NOT NULL,
  id            INT AUTO_INCREMENT PRIMARY KEY,
  balance       DOUBLE NOT NULL,
  customer_id   INT NOT NULL ,
  creation_time DATETIME,
  update_time   DATETIME,
  FOREIGN KEY (customer_id) REFERENCES customer (id)
);

DELETE FROM customer;
INSERT INTO customer(id, creation_time, update_time, first_name, last_name, email, phone) VALUES
(1, TIMESTAMP '2017-10-10 08:45:56.468', TIMESTAMP '2017-10-10 08:45:56.468', 'Sérgio', 'Gouveia', 'sg@mail.pt', '919909812'),
(2, TIMESTAMP '2017-10-10 08:45:56.481', TIMESTAMP '2017-10-10 08:45:56.481', 'Sara', 'Talefe', 'talefe@academia.com', '929671231'),
(3, TIMESTAMP '2017-10-10 08:45:56.482', TIMESTAMP '2017-10-10 08:45:56.482', 'Rúben', 'Maya', 'danlo@tinder.com', '91923145'),
(4, TIMESTAMP '2017-10-10 08:45:56.482', TIMESTAMP '2017-10-10 08:45:56.482', 'Sara', 'Lopes', 'saralopes@noob.b', '96789098'),
(5, TIMESTAMP '2017-10-10 08:45:56.482', TIMESTAMP '2017-10-10 08:45:56.482', 'Diogo', 'Rolo', 'rolinho@soulindo.true', '911222333');


DELETE FROM account;
INSERT INTO account(account_type, id, creation_time, update_time, balance, customer_id) VALUES
('CHECKING', 1, TIMESTAMP '2017-10-10 10:18:53.819', TIMESTAMP '2017-10-10 10:22:58.578', 1500.0, 1),
('SAVINGS', 2, TIMESTAMP '2017-10-10 10:23:02.194', TIMESTAMP '2017-10-10 10:23:19.801', 650.0, 1),
('CHECKING', 3, TIMESTAMP '2017-10-10 14:30:37.769', TIMESTAMP '2017-10-10 14:30:43.042', 0, 1),
('CHECKING', 4, TIMESTAMP '2017-10-10 14:30:38.426', TIMESTAMP '2017-10-10 14:30:46.471', 120.0, 2),
('SAVINGS', 5, TIMESTAMP '2017-10-10 14:30:37.769', TIMESTAMP '2017-10-10 14:30:43.042', 12981.0, 2),
('CHECKING', 6, TIMESTAMP '2017-10-10 14:30:38.426', TIMESTAMP '2017-10-10 14:30:46.471', 671.52, 3),
('SAVINGS', 7, TIMESTAMP '2017-10-10 14:30:37.769', TIMESTAMP '2017-10-10 14:30:43.042', 12.0, 3);

