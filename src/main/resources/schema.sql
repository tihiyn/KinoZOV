-- users
create table if not exists myuser
(
    id serial primary key,
    name varchar not null,
    password varchar not null,
    role varchar not null
);
