create table if not exists Shaurma_Order
(
    id              identity,
    delivery_Name   varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City   varchar(50) not null,
    delivery_State  varchar(2)  not null,
    delivery_Zip    varchar(10) not null,
    cc_number       varchar(16) not null,
    cc_expiration   varchar(5)  not null,
    cc_cvv          varchar(3)  not null,
    placed_at       timestamp   not null
);

create table if not exists Shaurma
(
    id                identity,
    name              varchar(50) not null,
    shaurma_order     bigint      not null,
    shaurma_order_key bigint      not null,
    created_at        timestamp   not null
);

create table if not exists Ingredient_Ref
(
    ingredient_id    varchar(4) not null,
    shaurma          bigint     not null,
    shaurma_key      bigint     not null
);

create table if not exists Ingredient
(
    id   varchar(4) primary key,
    name varchar(25) not null,
    type varchar(10) not null
);

alter table Shaurma
    add foreign key (shaurma_order) references Shaurma_Order (id);
alter table Ingredient_Ref
    add foreign key (ingredient_id) references Ingredient (id);
alter table Ingredient_Ref
    add foreign key (shaurma) references Shaurma (id);
