CREATE TABLE pets (
    pet_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    breed VARCHAR(255) NOT NULL
);

CREATE TABLE donations (
    donation_id INT PRIMARY KEY AUTO_INCREMENT,
    donor_name VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL
);


CREATE TABLE adoption_events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    event_name VARCHAR(255) NOT NULL,
    event_date DATETIME NOT NULL
);


CREATE TABLE participants (
    participant_id INT PRIMARY KEY AUTO_INCREMENT,
    event_name VARCHAR(255),
    participant_name VARCHAR(255)
);


CREATE TABLE adopted(
adopted_id INT PRIMARY KEY AUTO_INCREMENT,
owner_name VARCHAR(255),
pet_name VARCHAR(255)
);