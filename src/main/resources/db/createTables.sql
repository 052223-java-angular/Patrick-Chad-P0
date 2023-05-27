DROP TABLE IF EXISTS Roles CASCADE;

DROP TABLE IF EXISTS Categories CASCADE;

DROP TABLE IF EXISTS Products CASCADE;

DROP TABLE IF EXISTS Cart CASCADE;

DROP TABLE IF EXISTS Users CASCADE;

DROP TABLE IF EXISTS Reviews CASCADE;

CREATE TABLE Users (
    id VARCHAR NOT NULL PRIMARY KEY,
    username VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL
);


CREATE TABLE Roles (
    id VARCHAR NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL,
    user_id VARCHAR not NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE Categories (
    id VARCHAR NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE Products (
    id VARCHAR NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL,
    description VARCHAR,
    price DECIMAL NOT NULL,
    qty_on_hand INT DEFAULT 0,
    category_id VARCHAR NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE Cart (
    id VARCHAR NOT NULL PRIMARY KEY,
    count INT DEFAULT 1,
    product_id VARCHAR NOT NULL,
    user_id VARCHAR NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (user_id) REFERENCES users(id)

    
);



CREATE TABLE Reviews (
    id VARCHAR NOT NULL PRIMARY KEY,
    rating INT,
    comment VARCHAR,
    user_id VARCHAR NOT NULL,
    product_id VARCHAR NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);