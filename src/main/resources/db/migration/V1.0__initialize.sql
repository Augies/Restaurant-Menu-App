create table menume.restaurant
(
    id int not null auto_increment,
    name varchar(255) not null,
    description varchar(1032),
    location varchar(1023) not null,
    logoUrl varchar(1023),
    websiteUrl varchar(1023),
    costLevel varchar(15),
    foodCategory varchar(15),
    distance decimal,
    phoneNumber varchar(15),
    constraint restaurant_pk
        primary key (id)
);

create table menume.menu
(
    id int auto_increment not null,
    mealTime varchar(15),
    restaurantId int not null,
    constraint menu_restaurant_id_fk
        foreign key (restaurantId) references menume.restaurant (id),
    constraint menu_pk
        primary key (id)
);

create table menume.item
(
    id int auto_increment not null,
    name varchar(63) not null,
    description varchar(1023) not null,
    cost decimal,
    calories int,
    menuId int,
    constraint item_pk
        primary key (id),
    constraint item_menu_id_pk
        foreign key (menuId) references menume.menu(id)
);

create table menume.dietaryRestriction
(
    id int auto_increment not null,
    name varchar(31) not null,
    constraint dietaryRestriction_pk
        primary key(id)
);

create table menume.itemRestriction
(
    id int auto_increment not null,
    itemId int not null,
    dietaryRestrictionId int not null,
    constraint itemRestriction_item_id_fk
        foreign key (itemId) references menume.item(id),
    constraint itemRestriction_dietaryRestriction_id_fk
        foreign key (dietaryRestrictionId) references menume.dietaryRestriction(id),
    constraint itemRestriction_pk
        primary key(id)
);