drop table if exists station;

create table station
(
    id       int auto_increment
        primary key,
    name     varchar(255) not null,
    location varchar(255) not null
);