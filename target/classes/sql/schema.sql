drop table accounts cascade;
drop table adverts cascade ;
drop table sold_items cascade ;
drop table favourites cascade ;
drop table avatars cascade ;
drop table file_info cascade ;

create table accounts(
    id bigserial primary key,
    first_name varchar(20),
    last_name varchar(20),
    email varchar(25),
    password varchar,
    phone_number bigint,
    city varchar(15),
    address varchar(50),
    avatar_uuid varchar default 'default.png'
);

create table adverts(
  id bigserial primary key,
  name varchar(100),
  description varchar(500),
  price integer,
  author_id bigint,
  category varchar(50),
  city varchar(15),
  address varchar(20),
  sales_start_date varchar,
  sales_end_date varchar,
  status bool
);

create table favourites(
    id bigserial primary key,
    account_id bigint,
    advert_id bigint
);

create table avatars(
    id bigserial primary key,
    avatar_id bigint,
    account_id bigint
);

create table sold_items(
  id bigserial primary key,
  account_id bigint,
  advert_id bigint
);

create table file_info(
    id bigserial primary key,
    original_file_name varchar,
    storage_file_name varchar,
    size bigint,
    mime_type varchar,
    type varchar,
    load_account bigint
);


alter table file_info
add foreign key (load_account) references accounts(id);

alter table avatars
add foreign key (account_id) references accounts(id);

alter table avatars
    add foreign key (avatar_id) references file_info(id);

alter table sold_items
add foreign key (account_id) references accounts(id);

alter table sold_items
add foreign key (advert_id) references adverts(id);

alter table favourites
add foreign key (account_id) references accounts(id);

alter table favourites
add foreign key (advert_id) references adverts(id);

alter table adverts
add foreign key (author_id) references accounts(id);