create table todo_items (
    id serial not null primary key,
    description varchar(200) not null,
    created timestamp not null,
    done boolean default false
);
