CREATE DATABASE IF NOT EXISTS EXPICO;

USE EXPICO;

#Owners Personal Info
CREATE TABLE o100(
	owner_id int auto_increment primary key,
	owner_gov_id int,
	owner_name varchar(100),
	owner_nif int not null,
	owner_cell_number varchar(15),
	owner_email varchar(100)
);

#Financial Movements
CREATE TABLE f100(
	movement_id int auto_increment primary key,
    movement_date date,
    movement_credit double(10,2),
    movement_debit double(10,2)
);

#VAT
CREATE TABLE f101(
	vat_id int auto_increment primary key,
    movement_id int not null,
    vat_percentage double(4,2),
    vat_value_purchase double(10,2),
    vat_value_sale double(10,2),
    foreign key(movement_id) references f100(movement_id)
);

#Cattle
CREATE TABLE b100(
	bovine_id int auto_increment primary key,
	bovine_prefix varchar(5),
    bovine_code int,
    breed varchar(50),
    color varchar(50),
    gender varchar(20) not null,
    birth_date date not null,
    bovine_name varchar(100),
    bovine_status varchar(20) not null,
    date_of_death date,
    cause_of_death varchar(150),
    mothers_code int,
    fathers_code int,
    last_known_owner_id int,
    foreign key (last_known_owner_id) references o100(owner_id)
);

#Bought Cattle
CREATE TABLE b101(
    buy_id int auto_increment primary key,
    bovine_id int not null,
    buy_date date not null,
    seller_id int not null,
    total_price double(10,2),
    vat_percentage double(4,2),
    movement_id int,
    foreign key(bovine_id) references b100(bovine_id)
);

#Sold Cattle
CREATE TABLE b102(
    sale_id int auto_increment primary key,
    bovine_id int not null,
    sell_date date not null,
    buyer_id int not null,
    total_price double(10,2),
    vat_percentage double(4,2),
    movement_id int,
    foreign key(bovine_id) references b100(bovine_id)
);

#Butchered Cattle
CREATE TABLE b103(
    butcher_id int auto_increment primary key,
    bovine_id int not null,
    butcher_date date not null,
    total_weight int,
    net_weight int not null,
    price_per_kilo double(5,2),
    total_price double(10,2),
    vat double(4,2),
    movement_id int,
    foreign key(bovine_id) references b100(bovine_id)
);

#Vaccine table
CREATE TABLE m100(
    vaccine_id int auto_increment primary key,
    vaccine_reference varchar(50),
    brand varchar(50)
);

#Vaccination of cattle
CREATE TABLE b104(
    id int auto_increment primary key,
    bovine_id int not null,
    vaccine_id int not null,
    date_of_vaccine date,
    dosage int,
    foreign key(bovine_id) references b100(bovine_id),
    foreign key(vaccine_id) references m100(vaccine_id)
);

#Land
CREATE TABLE l100(
	land_id int auto_increment primary key,
    parcelario_code int,
    path_caderneta_predial varchar(200),
    path_descricao_predial varchar(200),
    path_escritura varchar(200),
    gps_location varchar(150),
    total_ha double
);

#Vehicles
CREATE TABLE v100(
	vehicle_id int auto_increment primary key,
    vehicle_type varchar(100) not null,
    license_plate varchar(20),
    vehicle_registration_number varchar(50),
    brand varchar(100),
    last_oil_change_date date,
    last_oil_change_km int,
    next_oil_change_date date,
    next_oil_change_km int
);