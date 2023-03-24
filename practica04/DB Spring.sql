DROP DATABASE springboot;
CREATE DATABASE IF NOT EXISTS springboot;

USE springboot;

CREATE USER IF NOT EXISTS 'liam'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON springboot.* TO 'root'@'localhost';
GRANT ALL PRIVILEGES ON springboot.* TO 'liam'@'localhost';
FLUSH PRIVILEGES;

SHOW GRANTS FOR root@'localhost';
SHOW GRANTS FOR liam@'localhost';