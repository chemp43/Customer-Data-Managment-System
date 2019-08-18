-- create and select the database
DROP database if exists mma;
create database mma;
use mma;

-- create the Product table
CREATE TABLE Product(
	ProductID	INT				primary key		auto_increment,
    Code		varchar(10)		not null		unique,
    Description	varchar(255)	not null,
    ListPrice	Decimal(10,2)	not null
);

-- insert some rows into the Product table
insert into Product values
(1,	'java', 'Murach''s Java Programming', '57.50'),
(2, 'jsp', 'Murach''s Java Servlets and JSP', '57.50'),
(3, 'mysql', 'Murach''s MySQL', '54.50'),
(4, 'android', 'Murach''s Android Programming', '57.50'),
(5, 'html5', 'Murach''s HTML5 and CSS3', '54.50'),
(6, 'oracle', 'Murach''s Oracle and PL/SQL', '54.50'),
(7, 'javascript', 'Murach''s JavaScript and jQuery', '54.50');

-- create a user and grant privileges to that user
GRANT SELECT, INSERT, DELETE, UPDATE
on mma.*
to mma_user@localhost
IDENTIFIED BY 'sesame';