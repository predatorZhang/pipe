-------------------------------------------------------------------------------
--  危险源表
-------------------------------------------------------------------------------
create table SCOTT.YJ_WXY_INFO
(
  DBID         NUMBER(19) primary key not null,
  ACTIVE       NUMBER(1),
  DESCRIPTION  VARCHAR2(255 CHAR),
  ERRORMODE    VARCHAR2(255 CHAR),
  FILENAME     VARCHAR2(255 CHAR),
  FILEPATH     VARCHAR2(255 CHAR),
  LATITUDE     VARCHAR2(255 CHAR),
  LONGITUDE    VARCHAR2(255 CHAR),
  MEMO         VARCHAR2(255 CHAR),
  SOURCE_GRADE VARCHAR2(255 CHAR),
  SOURCE_NAME  VARCHAR2(255 CHAR)
);
-------------------------------------------------------------------------------
--  危险源表序列
-------------------------------------------------------------------------------
create sequence SCOTT.SEQ_YJ_WXY_INFO
minvalue 1
maxvalue 999999999999999999999999999
start with 181
increment by 1
cache 20;
-------------------------------------------------------------------------------
--  危险源预警表
-------------------------------------------------------------------------------
create table SCOTT.YJ_WXY_ALARM
(
  DBID            NUMBER(19) primary key not null,
  ALARM_ACTIVE    NUMBER(1),
  ALARM_CODE      VARCHAR2(255 CHAR),
  ALARM_DATE      TIMESTAMP(6),
  ISSEND          NUMBER(1),
  ALARM_MESSAGE   VARCHAR2(255 CHAR),
  MESSAGE_STATE   NUMBER(10),
  DANGERSOURCE_ID NUMBER(19)
);
alter table SCOTT.YJ_WXY_ALARM
  add constraint FK_ESPSBIBHMSBBTHAJN8XCA4FFP foreign key (DANGERSOURCE_ID)
  references SCOTT.YJ_WXY_INFO (DBID);
-------------------------------------------------------------------------------
--  危险源预警表序列
-------------------------------------------------------------------------------
create sequence SCOTT.SEQ_YJ_WXY_ALARM
minvalue 1
maxvalue 999999999999999999999999999
start with 61
increment by 1
cache 20;