DROP TABLE IF EXISTS Roles CASCADE;

DROP TABLE IF EXISTS Products CASCADE;

DROP TABLE IF EXISTS Cart CASCADE;

DROP TABLE IF EXISTS Users CASCADE;

DROP TABLE IF EXISTS Reviews CASCADE;

CREATE TABLE Roles (
    id VARCHAR NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE Products (
    id VARCHAR NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL,
    description VARCHAR,
    price DECIMAL NOT NULL,
    qty_on_hand INT DEFAULT 0
);

CREATE TABLE Cart (
    id VARCHAR NOT NULL PRIMARY KEY,
    product_id VARCHAR NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE Users (
    id VARCHAR NOT NULL PRIMARY KEY,
    username VARCHAR NOT NULL UNIQUE,
    PASSWORD VARCHAR NOT NULL,
    cart_id VARCHAR NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES cart(id)
);

CREATE TABLE Reviews (
    id VARCHAR NOT NULL PRIMARY KEY,
    rating INT,
    COMMENT VARCHAR,
    user_id VARCHAR NOT NULL,
    product_id VARCHAR NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);