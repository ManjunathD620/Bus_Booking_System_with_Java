create database bus;

use bus;

create table employee(
	salary int not null,
    driverId int auto_increment,
    driverName varchar(20) not null,
    dob date not null,
    primary key (driverId)
);

create table vehicle(
	regNO varchar(10) not null,
    driverId int ,
    pickup varchar(10) not null,
    destination varchar(10) not null,
    timing time,
    ticket_price int not null,
    foreign key (driverId) references employee(driverId) on delete cascade,
    primary key (destination,pickup,timing),
    unique(regNO)
);

create table ticket(
	userName varchar(10) not null,
    pickup varchar(10) not null,
    destination varchar(10) not null,
    amount int not null,
    ticketNo int not null auto_increment,
    seatNo int not null,
    phoneNo bigint not null,
    pickupDate date not null,
    regNO varchar(10) not null,
    pickup_time time not null,
    unique (ticketNo),
    primary key(userName,phoneNo,pickupDate)
);

create table clients(
	userId int not null auto_increment,
	userName varchar(10) not null,
    userPassword varchar(255) not null,
    state varchar(20) not null,
    district varchar(20) not null,
	pincode int not null,
    dob date not null,
    phoneNo bigint not null,
    gender varchar(10) not null,
    primary key (userId)
);
