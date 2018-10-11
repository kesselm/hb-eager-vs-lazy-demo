DROP SCHEMA IF EXISTS hb_01_one_to_one_uni;

CREATE SCHEMA hb_01_one_to_one_uni;

SET SCHEMA 'hb_01_one_to_one_uni';

DROP TABLE IF EXISTS instructor_detail;

CREATE TABLE instructor_detail (
  id serial primary key,
  youtube_channel varchar(128) DEFAULT NULL,
  hobby varchar(45) DEFAULT NULL
);

DROP TABLE IF EXISTS instructor;

CREATE TABLE instructor (
  id serial primary key,
  first_name varchar(45) DEFAULT NULL,
  last_name varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  instructor_detail_id int DEFAULT NULL
);

DROP TABLE IF EXISTS course;

 CREATE TABLE course(
  id serial primary key,
  title varchar(128) DEFAULT NULL,
  instructor_id int DEFAULT Null
);