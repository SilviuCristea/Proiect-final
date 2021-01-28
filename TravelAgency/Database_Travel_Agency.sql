drop database if exists travel_agency;

create database travel_agency;

use travel_agency;

create table continents(
id int not null primary key auto_increment,
name varchar(20) not null unique
);

create table countries(
id int not null primary key auto_increment,
name varchar(30) not null unique,
continent_id int not null,
constraint fk_continent_id
foreign key (continent_id)
references continents(id)
);

create table cities(
id int not null primary key auto_increment,
name varchar(20) not null,
country_id int not null,
constraint fk_country_id
foreign key (country_id)
references countries(id)
);

create table airports(
id int not null primary key auto_increment,
name varchar(30) not null,
city_airport_id int,
constraint fk_city_airport_id
foreign key (city_airport_id)
references cities(id)
);

create table flights(
id int not null primary key auto_increment,
flight_date date not null,
flight_hour time not null,
flight_to varchar(20) not null,
price int not null,
available_seats int not null,
airport_id int not null,
constraint fk_airport_id
foreign key (airport_id)
references airports(id)
);

create table hotels(
id int not null primary key auto_increment,
name varchar(30) not null,
stars int not null,
description varchar(100) not null,
city_id int not null,
constraint fk_city_id
foreign key (city_id)
references cities(id)
);

create table rooms(
id int not null primary key auto_increment,
type varchar(20) not null,
number int not null,
extra_bed tinyint,
hotel_id int not null,
constraint fk_hotel_id
foreign key(hotel_id)
references hotels(id)
);

create table trips(
id int not null primary key auto_increment,
name varchar(30) not null,
airport_id int,
constraint fk_airports_trips
foreign key (airport_id)
references airports(id),
hotel_id int not null,
constraint fk_hotels_trips
foreign key (hotel_id)
references hotels(id), 
departure_date date not null,
return_date date not null,
number_days int not null,
trip_type varchar(20),
adult_price double not null,
kid_price double not null,
promoted tinyint,
adult_bed int not null,
kids_bed int,
stock int not null
);


create table clients(
id int not null primary key auto_increment,
first_name varchar(20) not null,
surname varchar(20) not null,
year_of_birth int not null,
adress varchar(100) not null,
phone_number varchar(20) not null,
email varchar(30) not null
);

create table users(
id int not null primary key auto_increment,
user_name varchar(30) not null unique,
password varchar(300) not null,
login tinyint,
admin_user tinyint,
client_id int not null unique,
constraint fk_clients_users
foreign key (client_id)
references clients(id)
);

create table purchases(
id int not null primary key auto_increment,
amount double not null,
trip_id int not null,
constraint fk_trips_purchases
foreign key (trip_id)
references trips(id)
);


create table clients_purchases(
id int not null primary key auto_increment,
client_id int,
constraint fk_clients_purchases
foreign key(client_id)
references clients(id),
purchase_id int,
constraint fk_purchases_clients
foreign key(purchase_id)
references purchases(id)
);
