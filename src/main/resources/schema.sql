CREATE TABLE users(
    id IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    age INT,
    birth_date DATE
);

INSERT INTO users(name, surname, age, birth_date) VALUES
('John', 'Carter', 29, '1994-06-25'),
('Penny', 'Backward', 19, '2004-09-27'),
('Lexi', 'Stark', 14, '2008-05-12');