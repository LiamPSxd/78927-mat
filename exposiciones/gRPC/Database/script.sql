CREATE DATABASE pagosdb;
USE pagosdb;

CREATE TABLE pagos(
	id INT PRIMARY KEY AUTO_INCREMENT,
    folio INT,
    titular VARCHAR(40),
    concepto VARCHAR(40),
    cantidad FLOAT,
    estado BOOLEAN
);

insert into pagos (folio,titular,concepto,cantidad,estado) values (1,"Breayan","Coca",20.00,true);
delete from pagos;
select folio,titular,concepto,cantidad,estado from pagos;

ALTER USER 'root'@'localhost' IDENTIFIED WITH 'mysql_native_password' BY '1887';
FLUSH PRIVILEGES;

SHOW GRANTS FOR 'root'@'localhost';

