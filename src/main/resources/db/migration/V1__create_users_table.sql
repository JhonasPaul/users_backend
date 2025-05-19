CREATE TABLE IF NOT EXISTS users
(
    id       int auto_increment primary key,
    name     VARCHAR(50)  not null,
    lastname VARCHAR(50)  not null,
    username varchar(50)  not null unique,
    password varchar(255) not null,
    email    varchar(50)  not null unique
);
