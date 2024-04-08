CREATE DATABASE IF NOT EXISTS EXPICO;

USE EXPICO;

#Owners Personal Info
CREATE TABLE o100(
	owner_internal_id int auto_increment,
	owner_nif int,
	owner_gov_id int,
	owner_name varchar(100),
	owner_cell_number varchar(15),
	owner_email varchar(100),
    primary key(ownerInternalId, ownerNIF)
);

CREATE INDEX idx_o100 ON o100 (ownerNIF);

#Financial Movements
CREATE TABLE f100(
	movement_internal_id int auto_increment primary key,
    movement_date date,
    movement_credit double(10,2),
    movement_debit double(10,2)
);

#VAT
CREATE TABLE f101(
	vat_internal_id int auto_increment primary key,
    movement_id int not null,
    vat_percentage double(4,2),
    vat_value_purchase double(10,2),
    vat_value_sale double(10,2),
    foreign key(movement_id) references f100(movement_internal_id)
);

#Cattle
CREATE TABLE b100(
	bovine_internal_id int auto_increment,
    bovine_code varchar(50),
    bovine_breed varchar(50),
    bovine_color varchar(50),
    bovine_gender varchar(20) not null,
    bovine_birth_date date not null,
    bovine_name varchar(100),
    bovine_status varchar(20) not null,
    date_of_death date,
    cause_of_death varchar(150),
    mothers_code varchar(50),
    fathers_code varchar(50),
    last_known_owner_nif int,
    primary key (bovine_internal_id, bovine_code),
    foreign key (last_known_owner_nif) references o100(owner_nif)
);

CREATE INDEX idx_b100 ON b100 (bovineCode);

#Bought Cattle
CREATE TABLE b101(
    purchase_internal_id int auto_increment primary key,
    bovine_code varchar(50) not null,
    purchase_date date not null,
    sellers_nif int not null,
    total_price double(10,2),
    vat_percentage double(4,2),
    movement_internal_id int,
    foreign key(bovine_code) references b100(bovine_code)
);

#Sold Cattle
CREATE TABLE b102(
    sale_internal_id int auto_increment primary key,
    bovine_code varchar(50) not null,
    sale_date date not null,
    buyers_nif int not null,
    total_price double(10,2),
    vat_percentage double(4,2),
    movement_internal_id int,
    foreign key(bovine_code) references b100(bovine_code)
);

#Butchered Cattle
CREATE TABLE b103(
    butcher_internal_id int auto_increment primary key,
    bovine_code varchar(50) not null,
    butcher_date date not null,
    total_weight int,
    net_weight int not null,
    price_per_kilo double(5,2),
    total_price double(10,2),
    vat_percentage double(4,2),
    movement_internal_id int,
    foreign key(bovine_code) references b100(bovine_code)
);

#Vaccine table
CREATE TABLE m100(
    vaccine_internal_id int auto_increment primary key,
    vaccine_reference varchar(50),
    vaccine_brand varchar(50)
);

#Vaccination of cattle
CREATE TABLE b104(
    vaccination_internal_id int auto_increment primary key,
    bovine_code varchar(50) not null,
    vaccine_code int not null,
    vaccination_date date,
    vaccine_dosage int,
    foreign key(bovine_code) references b100(bovine_code),
    foreign key(vaccine_code) references m100(vaccine_internal_id)
);

#Land
CREATE TABLE l100(
	land_internal_id int auto_increment primary key,
    land_parcelario_code int,
    land_path_caderneta_predial varchar(200),
    land_path_descricao_predial varchar(200),
    land_path_escritura varchar(200),
    land_gps_location varchar(150),
    land_total_ha double
);

#Vehicles
CREATE TABLE v100(
	vehicle_id int auto_increment primary key,
    vehicle_type varchar(100) not null,
    vehicle_license_plate varchar(20),
    vehicle_registration_number varchar(50),
    vehicle_brand varchar(100),
    vehicle_last_oil_change_date date,
    vehicle_last_oil_change_km int,
    vehicle_next_oil_change_date date,
    vehicle_next_oil_change_km int
);