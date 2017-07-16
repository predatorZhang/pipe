-------------------------------------------------------------------------------
--  图层审核表
-------------------------------------------------------------------------------
create table JG_MAPAUDITRESULT
(
  DBID       NUMBER(19) primary key not null,
  AUDITTIME  TIMESTAMP (6),
  MAPID      VARCHAR2(255 CHAR),
  MEMO       VARCHAR2(255 CHAR),
  RESULT     CLOB,
  USERNAME   VARCHAR2(255 CHAR)
);
-------------------------------------------------------------------------------
--  序列
-------------------------------------------------------------------------------
create sequence SEQ_JG_MAPAUDITRESULT_ID
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;
