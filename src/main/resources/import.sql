INSERT INTO users (name, lastname, username, password, email) VALUES ('Pool', 'Ortiz', 'pool', '123123', 'pool@mail.com');
INSERT INTO users (name, lastname, username, password, email) VALUES ('Josefa', 'Doe', 'admin', '123123', 'josefa@mail.com');
INSERT INTO users (name, lastname, username, password, email) VALUES ('John Juan', 'Doe Roe', 'john', '321321', 'john@email.com');


insert into roles(name) values ('ROLE_USER');
insert into roles(name) values ('ROLE_ADMIN');


insert into users_roles(user_id, role_id) VALUES (1, 1);
insert into users_roles(user_id, role_id) VALUES (2, 1);
insert into users_roles(user_id, role_id) VALUES (2, 2);
insert into users_roles(user_id, role_id) VALUES (3, 2);
insert into users_roles(user_id, role_id) VALUES (3, 1);
