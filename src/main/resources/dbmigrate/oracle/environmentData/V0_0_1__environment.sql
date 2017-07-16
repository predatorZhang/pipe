-------------------------------------------------------------------------------
--  扬尘噪声记录表
-------------------------------------------------------------------------------
create table DATAPACK
(
  ID            NUMBER(19) primary key not null,
  AIRPRESSURE   FLOAT,
  BOARD_HU      FLOAT,
  BOARD_LAT     FLOAT,
  BOARD_LNG     FLOAT,
  BOARD_TP      FLOAT,
  CREATEDATE    VARCHAR2(255 CHAR),
  DEVICEID      VARCHAR2(255 CHAR),
  DUST          FLOAT,
  HUMIDITY      FLOAT,
  IP            VARCHAR2(255 CHAR),
  NOISE         FLOAT,
  PM25          FLOAT,
  PORT          NUMBER(19),
  TEMPERATURE   FLOAT,
  VOLTAGE       FLOAT,
  WINDDIRECTION FLOAT,
  WINDSSPEED    FLOAT
);

-------------------------------------------------------------------------------
--  异常处理记录表
-------------------------------------------------------------------------------
create table JG_DEALTRACK
(
  DBID          NUMBER(19) primary key not null,
  ALARMTIME     TIMESTAMP(6),
  CONTEXT       VARCHAR2(255 CHAR),
  DEALTIME      TIMESTAMP(6),
  DETAIL        VARCHAR2(255 CHAR),
  EQUIPMENT_ID  VARCHAR2(255 CHAR),
  EQUIPMENTNAME VARCHAR2(255 CHAR),
  PHONE         VARCHAR2(255 CHAR),
  REGION        VARCHAR2(255 CHAR),
  STAFF         VARCHAR2(255 CHAR)
);
-------------------------------------------------------------------------------
--  阈值表
-------------------------------------------------------------------------------
create table JG_ALARM_YZ
(
  DBID       NUMBER(19) primary key not null,
  DUST_YZ FLOAT,
  NOISE_YZ FLOAT
);

-------------------------------------------------------------------------------
--  序列表
-------------------------------------------------------------------------------
create sequence SEQ_JG_DATAPACK_ID
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

create sequence SEQ_JG_DEALTRACK
minvalue 1
maxvalue 999999999999999999999999999
start with 8
increment by 1
cache 20;

create sequence SEQ_JG_ALARM_YZ
minvalue 1
maxvalue 999999999999999999999999999
start with 2
increment by 1
cache 20;