CREATE TABLE IF NOT EXISTS users_roles
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    foreign key (role_id) references roles (id),
    foreign key (user_id) references users (id)
);