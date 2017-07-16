-------------------------------------------------------------------------------
--  防腐层分析表
-------------------------------------------------------------------------------
create table SCOTT.YJ_ANA_COATING
(
  DBID        NUMBER(19) primary key not null,
  DETECTTIME  TIMESTAMP(6),
  ENVIRONMENT VARCHAR2(255 CHAR),
  MEMO        VARCHAR2(255 CHAR),
  PIPEID      VARCHAR2(255 CHAR),
  PIPETYPE    VARCHAR2(255 CHAR),
  RESULT      VARCHAR2(255 CHAR),
  STREET      VARCHAR2(255 CHAR),
  VALUE       FLOAT,
  LATITUDE    FLOAT,
  LONGITUDE   FLOAT
);
-------------------------------------------------------------------------------
--  防腐层分析表序列
-------------------------------------------------------------------------------
create sequence SCOTT.SEQ_YJ_ANA_COATING
minvalue 1
maxvalue 999999999999999999999999999
start with 61
increment by 1
cache 20;
-------------------------------------------------------------------------------
--  形变分析表
-------------------------------------------------------------------------------
create table SCOTT.YJ_ANA_DEFORMATION
(
  DBID       NUMBER(19) primary key not null,
  DETECTTIME TIMESTAMP(6),
  MEMO       VARCHAR2(255 CHAR),
  PIPEID     VARCHAR2(255 CHAR),
  PIPETYPE   VARCHAR2(255 CHAR),
  RESULT     VARCHAR2(255 CHAR),
  STREET     VARCHAR2(255 CHAR),
  VALUE      FLOAT,
  LATITUDE   FLOAT,
  LONGITUDE  FLOAT
);
-------------------------------------------------------------------------------
--  形变分析表序列
-------------------------------------------------------------------------------
create sequence SCOTT.SEQ_YJ_ANA_DEFORMATION
minvalue 1
maxvalue 999999999999999999999999999
start with 61
increment by 1
cache 20;
-------------------------------------------------------------------------------
--  噪声分析表
-------------------------------------------------------------------------------
create table SCOTT.YJ_ANA_NOISE
(
  DBID       NUMBER(19) primary key not null,
  DETECTTIME TIMESTAMP(6),
  MEMO       VARCHAR2(255 CHAR),
  PIPEID     VARCHAR2(255 CHAR),
  PIPETYPE   VARCHAR2(255 CHAR),
  RESULT     VARCHAR2(255 CHAR),
  STREET     VARCHAR2(255 CHAR),
  VALUE      FLOAT,
  LATITUDE   FLOAT,
  LONGITUDE  FLOAT
);
-------------------------------------------------------------------------------
--  噪声分析表序列
-------------------------------------------------------------------------------
create sequence SCOTT.SEQ_YJ_ANA_NOISE
minvalue 1
maxvalue 999999999999999999999999999
start with 61
increment by 1
cache 20;
-------------------------------------------------------------------------------
--  壁厚分析表
-------------------------------------------------------------------------------
create table SCOTT.YJ_ANA_PIPETHICKNESS
(
  DBID       NUMBER(19) primary key not null,
  DETECTTIME TIMESTAMP(6),
  MEMO       VARCHAR2(255 CHAR),
  PIPEID     VARCHAR2(255 CHAR),
  PIPETYPE   VARCHAR2(255 CHAR),
  RESULT     VARCHAR2(255 CHAR),
  STREET     VARCHAR2(255 CHAR),
  THICKNESS  FLOAT,
  LATITUDE   FLOAT,
  LONGITUDE  FLOAT
);
-------------------------------------------------------------------------------
--  壁厚分析序列
-------------------------------------------------------------------------------
create sequence SCOTT.SEQ_YJ_ANA_PIPETHICKNESS
minvalue 1
maxvalue 999999999999999999999999999
start with 61
increment by 1
cache 20;