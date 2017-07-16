-------------------------------------------------------------------------------
--  危险区域表
-------------------------------------------------------------------------------
create table SCOTT.YJ_WXQU_INFO
(
  DBID          NUMBER(19) primary key not null,
  ACTIVE        NUMBER(1),
  AREA_GRADE    VARCHAR2(255 CHAR),
  AREA_NAME     VARCHAR2(255 CHAR),
  DESCRIPTION   VARCHAR2(255 CHAR),
  FILENAME      VARCHAR2(255 CHAR),
  FILEPATH      VARCHAR2(255 CHAR),
  AREA_LOCATION VARCHAR2(255 CHAR),
  MEMO          VARCHAR2(255 CHAR)
);
-------------------------------------------------------------------------------
--  危险区域表序列
-------------------------------------------------------------------------------
create sequence SCOTT.SEQ_YJ_WXQU_INFO
minvalue 1
maxvalue 999999999999999999999999999
start with 281
increment by 1
cache 20;