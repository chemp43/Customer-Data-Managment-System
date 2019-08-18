USE mma;

DROP TABLE IF EXISTS Customer;

CREATE TABLE Customer
(
    CustomerID   INT          PRIMARY KEY AUTO_INCREMENT,
    FirstName    VARCHAR(50), 
    LastName     VARCHAR(50), 
    EmailAddress VARCHAR(50)
);

INSERT INTO Customer VALUES 
(2, 'Michael ', 'Martin', 'michealmartin@hotmail.com'), 
(3, 'Marjorie ', 'Galvan', 'mGalvan@yahoo.com'), 
(4, 'Rebecca', 'Cain', 'cainr76@aol.com'), 
(5, 'Sam', 'Schildt', 'sam@cerners.com');