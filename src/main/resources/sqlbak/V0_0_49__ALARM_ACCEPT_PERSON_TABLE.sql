-- Create sequence 
create sequence SEQ_ALARM_RECORD_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;


-- Create table
create table ALARM_ACCEPT_PERSON
(
  DBID       NUMBER(19) primary key not null,
  ACTIVE     NUMBER(1),
  EMAIL      VARCHAR2(255 CHAR),
  PASSWORD   VARCHAR2(255 CHAR),
  PERSONCODE VARCHAR2(255 CHAR) not null,
  PERSONNAME VARCHAR2(255 CHAR) not null,
  TELEPHONE  VARCHAR2(255 CHAR)
)