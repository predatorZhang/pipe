-------------------------------------------------------------------------------
--  故障预警表
-------------------------------------------------------------------------------
create table YJ_WARNING_ALARMPIPE
(
  DBID      NUMBER(19) primary key not null,
  ACTIVE    NUMBER(1),
  ALARMTIME TIMESTAMP(6),
  FAULTDESC VARCHAR2(255 CHAR),
  FAULTTYPE VARCHAR2(255 CHAR),
  MEMO      VARCHAR2(255 CHAR),
  PIPEID    VARCHAR2(255 CHAR),
  PIPETYPE  VARCHAR2(255 CHAR),
  STRATEGY  VARCHAR2(255 CHAR),
  STREET    VARCHAR2(255 CHAR)
);
-------------------------------------------------------------------------------
--  故障预警表序列
-------------------------------------------------------------------------------
create sequence SEQ_YJ_WARNING_ALARMPIPE
minvalue 1
maxvalue 999999999999999999999999999
start with 21
increment by 1
cache 20;
-------------------------------------------------------------------------------
--  故障诊断表
-------------------------------------------------------------------------------
create table YJ_WARNING_DIAGNOSIS
(
  DBID      NUMBER(19) primary key not null,
  CHECKTIME TIMESTAMP(6),
  FAULTDESC VARCHAR2(255 CHAR),
  FAULTTYPE VARCHAR2(255 CHAR),
  ISNORMAL  NUMBER(1),
  MEMO      VARCHAR2(255 CHAR),
  PIPEID    VARCHAR2(255 CHAR),
  PIPETYPE  VARCHAR2(255 CHAR),
  STRATEGY  VARCHAR2(255 CHAR),
  STREET    VARCHAR2(255 CHAR)
);

-------------------------------------------------------------------------------
--  故障诊断表序列
-------------------------------------------------------------------------------
create sequence SEQ_YJ_WARNING_DIAGNOSIS
minvalue 1
maxvalue 999999999999999999999999999
start with 41
increment by 1
cache 20;
-------------------------------------------------------------------------------
--  故障预测表
-------------------------------------------------------------------------------
create table YJ_WARNING_FORECAST
(
  DBID      NUMBER(19) primary key not null,
  CHECKTIME TIMESTAMP(6),
  FAULTDESC VARCHAR2(255 CHAR),
  FAULTTYPE VARCHAR2(255 CHAR),
  ISNORMAL  NUMBER(1),
  MEMO      VARCHAR2(255 CHAR),
  PIPEID    VARCHAR2(255 CHAR),
  PIPETYPE  VARCHAR2(255 CHAR),
  STRATEGY  VARCHAR2(255 CHAR),
  STREET    VARCHAR2(255 CHAR)
);
-------------------------------------------------------------------------------
--  故障预测表序列
-------------------------------------------------------------------------------
create sequence SEQ_YJ_WARNING_FORECAST
minvalue 1
maxvalue 999999999999999999999999999
start with 21
increment by 1
cache 20;
-------------------------------------------------------------------------------
--  健康度表
-------------------------------------------------------------------------------
create table YJ_WARNING_HEALTH
(
  DBID       NUMBER(19) primary key not null,
  DETECTTIME TIMESTAMP(6),
  RANK       VARCHAR2(255 CHAR),
  MEMO       VARCHAR2(255 CHAR),
  PIPEID     VARCHAR2(255 CHAR),
  PIPETYPE   VARCHAR2(255 CHAR),
  RESULT     VARCHAR2(255 CHAR),
  STREET     VARCHAR2(255 CHAR),
  SUGGESTION VARCHAR2(255 CHAR)
);
-------------------------------------------------------------------------------
--  健康度表序列
-------------------------------------------------------------------------------
create sequence SEQ_YJ_WARNING_HEALTH
minvalue 1
maxvalue 999999999999999999999999999
start with 21
increment by 1
cache 20;