CREATE TABLE IF NOT EXISTS roles
(
    id int auto_increment primary key,
    name varchar(50) not null,
    user_id int not null,
    foreign key (user_id) references users(id)
);