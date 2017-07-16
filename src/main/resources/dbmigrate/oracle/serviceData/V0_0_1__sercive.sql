-------------------------------------------------------------------------------
--  施工进度表
-------------------------------------------------------------------------------
create table JG_CONSTRUCT
(
  DBID          NUMBER(19) primary key not null,
  COMPANY       VARCHAR2(255 CHAR),
  DESCRIPTION   VARCHAR2(255 CHAR),
  EXPECTTIME    TIMESTAMP(6),
  FINISHTIME    TIMESTAMP(6),
  ISFINISHED    NUMBER(1),
  MANAGER       VARCHAR2(255 CHAR),
  CONSTRUCTNAME VARCHAR2(255 CHAR),
  CONSTRUCTNUM  VARCHAR2(255 CHAR),
  SCHEDULE      VARCHAR2(255 CHAR)
);
-------------------------------------------------------------------------------
--  序列表
-------------------------------------------------------------------------------
create sequence SEQ_JG_CONSTRUCT_ID
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;
