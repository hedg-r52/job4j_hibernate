-- Кузов
create table auto_bodies
(
    id serial primary key,
    name varchar(100)
);

-- Двигатель
create table auto_engines
(
    id serial primary key,
    name varchar(100)
);

-- Коробка передач
create table auto_transmissions
(
    id serial primary key,
    name varchar(100)
);

-- Машины
create table auto_autos
(
    id serial primary key,
    name varchar(100),
    body_id int references auto_bodies(id),
    engine_id int references auto_engines(id),
    transmission_id int references auto_transmissions(id)
);