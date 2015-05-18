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