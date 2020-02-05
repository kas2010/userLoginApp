create database user_login;

use user_login;

-- drop table users;

create table users
(
    id       bigint not null auto_increment,
    name     varchar(50),
    surname  varchar(50),
    login    varchar(15),
    password varchar(15),
    born     date,
    counter  int default 0,
    primary key (id),
    unique (login)
)
    engine = InnoDB;

insert into users(id, name, surname, login, password, born, counter)
values (1, 'User1', 'User1', 'u1', 'p1', '2009-08-09', 0);
