

drop database if exists project;
create database project;
use project;

set foreign_key_checks=0;
drop table if exists login_table;
drop table if exists room;
drop table if exists session_table;
set foreign_key_checks=1;

create table if not exists login_table(_id int auto_increment not null,
                                        email varchar(50) unique not null,
                                        password varchar(50) not null,
                                        primary key(_id),
                                        index(email),
                                        type enum('ADMIN','STUDENT')
                                        );

create table if not exists room(_id int auto_increment not null,
                                type enum('AC','No/AC'),
                                location int,
                                charge  int,
                                r_status enum("AVAILABLE","NOAVAILABLE"),
                                p_status enum("PAID","NOPAID"),
                                s_id int,
                                primary key(_id)
                                );

create table if not exists session_table(_id int,
                                        session_key varchar(100)
                                );

