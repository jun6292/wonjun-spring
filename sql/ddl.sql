CREATE TABLE diaries (
    diary_id INT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR NOT NULL,
    title VARCHAR NOT NULL,
    content VARCHAR NOT NULL,
    created_date TIMESTAMP,
    status VARCHAR DEFAULT "ACTIVE",
    PRIMARY KEY (diary_id),
    FOREIGN KEY (user_name) REFERENCES users(user_id)
);

CREATE TABLE users (
    user_id INT NOT NULL,
    user_name VARCHAR NOT NULL,
    intro VARCHAR DEFAULT NULL,
    created_date TIMESTAMP,
    PRIMARY KEY (user_id)
);

