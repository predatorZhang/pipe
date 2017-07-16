-- Create sequence
create sequence SEQ_DEVICE_TYPE_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;


-- Create table
create table ALARM_DEVICE_TYPE
(
  DBID     NUMBER(19) primary key not null,
  ACTIVE   NUMBER(1) not null,
  LOCATION VARCHAR2(255 CHAR),
  TYPECODE VARCHAR2(255 CHAR) not null,
  TYPENAME VARCHAR2(255 CHAR) not null
)