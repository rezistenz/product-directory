drop table category if exists cascade;
drop table product if exists cascade;
drop table sequences if exists cascade;

create table category (
    id bigint not null,
    description clob(255),
    name varchar(200),
    primary key (id)
);

create table product (
    id bigint not null,
    createDate timestamp,
    description clob(255),
    imageUrl clob(255),
    name varchar(200),
    price numeric(19,2),
    producer varchar(500),
    category_id bigint,
    primary key (id)
);

alter table product 
    add constraint FK_product_category_id
    foreign key (category_id) 
    references category;

create table sequences (
     name varchar(255),
     value bigint 
);