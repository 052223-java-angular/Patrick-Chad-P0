CREATE TABLE Roles (id VARCHAR NOT NULL,
                    name VARCHAR NOT NULL,
                    user_id VARCHAR NOT NULL
                    PRIMARY KEY (id), 
                    FOREIGN KEY (user_id) REFERENCES users(id));

CREATE TABLE Users (id VARCHAR NOT NULL, 
                    username VARCHAR NOT NULL UNIQUE,
                    password VARCHAR NOT NULL,
                    cart_id  VARCHAR NOT NULL,
                    PRIMARY KEY (id)
                    FOREIGN KEY cart_id REFERENCES cart(id));

CREATE TABLE Reviews(id VARCHAR NOT NULL, 
                     rating INT, 
                     user_id VARCHAR NOT NULL,
                     comment VARCHAR,
                     user_id VARCHAR NOT NULL, 
                     product_id VARCHAR NOT NULL,
                     PRIMARY KEY(id)
                     FOREIGN KEY user_id REFERENCES users(id)
                     FOREIGN KEY product_id REFERENCES products(id));
CREATE TABLE Products (id VARCHAR NOT NULL, 
                       name VARCHAR NOT NULL, 
                       description VARCHAR, 
                       price DOUBLE NOT NULL,
                       qty_on_hand INT DEFAULT 0
                       PRIMARY KEY (id));

CREATE TABLE Cart (id, VARCHAR NOT NULL, 
                   product_id VARCHAR NOT NULL,
                   PRIMARY KEY (id),
                   FOREIGN KEY product_id REFERENCES products(id));
                    