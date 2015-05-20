-- DATABASE SCHEMA ---
create table category_group (
	category_group_id serial primary key, 
	category_group_name varchar(20) not null
);

create table category (
	category_id serial primary key,
	category_group_id integer references category_group (category_group_id),
	category_name varchar(20) not null,
	size_property integer not null check(size_property > 0)
);

create table element_group (
	element_group_id serial primary key,
	element_group_name varchar(20) not null
);

create table element (
	element_id serial primary key,
	element_group_id integer references element_group (element_group_id),
	category_id integer references category (category_id),
	name_property varchar(20) not null,
	charge_property integer not null,
	attribute_property varchar(20) not null
);

create table Users(
	username varchar(255) primary key, 
	passwd varchar(255)
);

create table UserRoles(
	username varchar(255),
	role varchar(32)
);

-- DATA --
-- ADMIN USER/ROLE --
insert into users (username, passwd) values ('admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=');
insert into userroles (username, role) values ('admin', 'ADMIN');
	
-- USERA USER/ROLE --
insert into users (username, passwd) values ('100004', 'nRhqD0cpVRYH4YybpZXVZDIdWg33IdwL+FqIhQxptNM=');
insert into userroles (username, role) values ('100004', 'USERA');
insert into users (username, passwd) values ('100007', 'qbdbLzJ+wN8SyDSmLSLFTqbkHRJDfrVWEm+KeG4qU8c=');
insert into userroles (username, role) values ('100007', 'USERA');
	
-- USERB USER/ROLE --
insert into users (username, passwd) values ('100005', 'z0iKzE2VpyWDGgFi/wYCN2iz3Sd06+zHgVj7PMyHtkw=');
insert into userroles (username, role) values ('100005', 'USERB');
insert into users (username, passwd) values ('100011', 'gWLlCYYb8LnM21Etart4DtA76st3fGK3YVYqnCRY3l0=');
insert into userroles (username, role) values ('100011', 'USERB');
		
-- USERC USER/ROLE --
insert into users (username, passwd) values ('100002', 'P7g2IpUFwC2F7wKGsMkyE9txB2bYQfANkdte2ureE2s=');
insert into userroles (username, role) values ('100002', 'USERC');
insert into users (username, passwd) values ('1000008', 'xhl6hR7L+Z0V3NeHmZvfOw86XWsKx0dCg5MFx0vf+PQ=');
insert into userroles (username, role) values ('1000008', 'USERC');

insert into category_group (category_group_name) values ('CAVE');
insert into category_group (category_group_name) values ('FOREST');
insert into category_group (category_group_name) values ('TOWER');
	
insert into element_group (element_group_name) values('ELF');
insert into element_group (element_group_name) values('MAGE');
insert into element_group (element_group_name) values('DRAGON');
	
insert into category (category_group_id, category_name, size_property)
select category_group_id, 'An amazing cave', 150 from category_group where category_group_name = 'CAVE';
insert into category (category_group_id, category_name, size_property)
select category_group_id, 'Old cave', 450 from category_group where category_group_name = 'CAVE';
insert into category (category_group_id, category_name, size_property)
select category_group_id, 'Deep cave', 250 from category_group where category_group_name = 'CAVE';
insert into category (category_group_id, category_name, size_property)
select category_group_id, 'Dark forest', 150000 from category_group where category_group_name = 'FOREST';
insert into category (category_group_id, category_name, size_property)
select category_group_id, 'Small forest', 45 from category_group where category_group_name = 'FOREST';
insert into category (category_group_id, category_name, size_property)
select category_group_id, 'High tower', 2500 from category_group where category_group_name = 'TOWER';

insert into element (category_id, name_property, charge_property, attribute_property)
select category_id, 'Rat', 10, 'None' from category where category_id = 1;
insert into element (category_id, name_property, charge_property, attribute_property)
select category_id, 'Big rat', 15, 'None' from category where category_id = 1;
insert into element (category_id, name_property, charge_property, attribute_property)
select category_id, 'Old rat', 8, 'None' from category where category_id = 2;
insert into element (category_id, name_property, charge_property, attribute_property)
select category_id, 'Young rat', 17, 'None' from category where category_id = 2;
insert into element (category_id, name_property, charge_property, attribute_property)
select category_id, 'Rat', 10, 'None' from category where category_id = 3;
insert into element (category_id, name_property, charge_property, attribute_property)
select category_id, 'Elf', 15, 'bow' from category where category_id = 4;
insert into element (category_id, name_property, charge_property, attribute_property)
select category_id, 'Old elf', 155, 'Crossbow' from category where category_id = 4;
insert into element (category_id, name_property, charge_property, attribute_property)
select category_id, 'Red elf', 15, 'Bow' from category where category_id = 5;
insert into element (category_id, name_property, charge_property, attribute_property)
select category_id, 'Super mage', 1500, 'Crystal ball' from category where category_id = 6;
insert into element (category_id, name_property, charge_property, attribute_property)
select category_id, 'Old mage', 1500, 'Wand' from category where category_id = 6;
