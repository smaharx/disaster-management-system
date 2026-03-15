create database disasterrelief;
use disasterrelief;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin','staff') NOT NULL
);
INSERT INTO users (username, password, role) VALUES
('Deepak', '123', 'admin'),
('Najaf', '123', 'Admin'),('Shahzaib', '123', 'admin');
select username, password, role from users;
CREATE TABLE families (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    location VARCHAR(100),
    points INT,
    queue_type VARCHAR(20),
    food_need INT ,
     water_need INT ,
     medical_need INT
);


select * from families;
select points from families order by points desc;
CREATE TABLE allocation_summary (
    id INT AUTO_INCREMENT PRIMARY KEY,
    family_name VARCHAR(100),
    queue_type VARCHAR(20),
	served INT
);
select * from allocation_summary;


select * from users;