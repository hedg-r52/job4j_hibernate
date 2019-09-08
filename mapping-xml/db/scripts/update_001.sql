-- Кузов
create table auto_bodies_xml
(
    id serial primary key,
    name varchar(100)
);

-- Двигатель
create table auto_engines_xml
(
    id serial primary key,
    name varchar(100)
);

-- Коробка передач
create table auto_transmissions_xml
(
    id serial primary key,
    name varchar(100)
);

-- Машины
create table auto_autos_xml
(
    id serial primary key,
    name varchar(100),
    body_id int references auto_bodies_xml(id),
    engine_id int references auto_engines_xml(id),
    transmission_id int references auto_transmissions_xml(id)
);