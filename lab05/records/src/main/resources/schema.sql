drop table if exists record;

create table record
(
    id          int auto_increment
        primary key,
    station_id  int          not null,
    description varchar(255) not null,
    created_at  datetime     not null
);