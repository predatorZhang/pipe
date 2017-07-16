-- Create table
create table AD_DJ_LIQUID
(
  DBID       NUMBER(19) primary key not null,
  CELL       VARCHAR2(255 CHAR),
  DEVCODE    VARCHAR2(255 CHAR),
  LIQUIDDATA VARCHAR2(255 CHAR),
  LOGTIME    DATE,
  SIGNAL     VARCHAR2(255 CHAR),
  STATUS     VARCHAR2(255 CHAR),
  UPTIME     DATE
)