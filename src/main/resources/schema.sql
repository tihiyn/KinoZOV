-- users
create table if not exists myuser
(
    id integer primary key,
    name varchar not null,
    password varchar not null,
    role varchar not null
);
