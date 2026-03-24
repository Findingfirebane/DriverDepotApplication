-- create database vehiclemanagement;

use vehiclemanagement;
drop table if EXISTS user_notifications;
drop table if exists user_saved_vehicles;

drop table if EXISTS vehicles;
drop table if EXISTS users;
drop table if EXISTS notifications;
drop table if  exists roles;

create table vehicles(
    id int AUTO_INCREMENT PRIMARY KEY,
    make varchar(25) not null,
    model varchar(25) not null,
    year int not null,
    mileage int not null,
    msrp decimal(8,2) not null,
    stock int null,
    details varchar(500) null
);

create table roles(
    id int AUTO_INCREMENT primary key,
    role_name varchar(20) not null
);

create table users(
    id int AUTO_INCREMENT PRIMARY KEY,
    user_name varchar(50) not null,
    password varchar(50) not null,
    email varchar(50) not null,
    role_id int not null,

    foreign key(role_id) references roles(id)
);



create table user_saved_vehicles(
    user_id int not null,
    vehicle_id int not null,
    primary key(user_id,vehicle_id)
);

create table notifications(
    id int AUTO_INCREMENT PRIMARY KEY,
    vehicle varchar(100) not null,
    customer_name varchar(100) not null,
    customer_email varchar(100) not null,
    message text null,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table user_notifications(
    user_id int not null,
    notification_id int not null,
    primary key( user_id, notification_id)
);

alter table user_saved_vehicles
add constraint fk_user_saved_vehicles_userid
foreign key (user_id) references users(id) on delete cascade,
add constraint fk_user_saved_vehicles_vehicleid
foreign key (vehicle_id) references vehicles(id) on delete cascade;

alter table user_notifications
add constraint fk_user_notifications_userid
foreign key (user_id) references users(id) on delete cascade,
add constraint fk_user_notifications_notificationid
foreign key (notification_id) references notifications(id)on delete cascade;

