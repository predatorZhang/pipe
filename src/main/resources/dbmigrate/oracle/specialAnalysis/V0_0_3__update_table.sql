drop table YJ_ANA_COATING;
drop table YJ_ANA_DEFORMATION;
drop table YJ_ANA_NOISE;
drop table YJ_ANA_PIPETHICKNESS;

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
  THRESHOLD   FLOAT,
  LATITUDE    FLOAT,
  LONGITUDE   FLOAT
);
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
  REASON     VARCHAR2(255 CHAR),
  STREET     VARCHAR2(255 CHAR),
  VALUE      FLOAT,
  THRESHOLD  FLOAT,
  LATITUDE   FLOAT,
  LONGITUDE  FLOAT
);
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
  THRESHOLD  FLOAT,
  LATITUDE   FLOAT,
  LONGITUDE  FLOAT
);
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
  THRESHOLD  FLOAT,
  LATITUDE   FLOAT,
  LONGITUDE  FLOAT
);