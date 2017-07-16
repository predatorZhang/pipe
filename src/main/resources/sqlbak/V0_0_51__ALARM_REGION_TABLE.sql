-- Create sequence
create sequence SEQ_REGION_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

-- Create table
create table ALARM_REGION
(
  DBID             NUMBER(19) primary key not null,
  ACTIVE           NUMBER(1),
  REGION_AREA      VARCHAR2(255 CHAR),
  REGION_NAME      VARCHAR2(255 CHAR),
  PARENT_REGION_ID NUMBER(19) constraint FK_79VM4EOMMGO75HFWFMSVGCAD0 references ALARM_REGION (DBID)
)