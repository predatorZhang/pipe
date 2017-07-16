-------------------------------------------------------------------------------
--  附属物数据类型表
-------------------------------------------------------------------------------
create table JG_APPENDANTTYPE
(
  DBID     NUMBER(19) primary key not null,
  TYPE     VARCHAR2(255 CHAR),
  NAME     VARCHAR2(255 CHAR),
  CODE     VARCHAR2(255 CHAR),
  MEMO     VARCHAR2(255 CHAR)
);
-------------------------------------------------------------------------------
--  管点数据类型表
-------------------------------------------------------------------------------
create table JG_POINTTYPE
(
  DBID     NUMBER(19) primary key not null,
  TYPE     VARCHAR2(255 CHAR),
  NAME     VARCHAR2(255 CHAR),
  CODE     VARCHAR2(255 CHAR),
  MEMO     VARCHAR2(255 CHAR)
);
-------------------------------------------------------------------------------
--  上传附属物表
-------------------------------------------------------------------------------
create table JG_APPENDANT
(
  DBID        NUMBER(19) primary key not null,
  LATITUDE    FLOAT not null,
  LONGITUDE   FLOAT not null,
  DEPTH       FLOAT not null,
  HEIGHT      FLOAT,
  CODE        VARCHAR2(255 CHAR) not null,
  NAME        VARCHAR2(255 CHAR),
  TYPE        VARCHAR2(255 CHAR),
  TYPECODE    VARCHAR2(255 CHAR),
  PICTURE     VARCHAR2(255 CHAR),
  STATE       VARCHAR2(255 CHAR),
  STREET      VARCHAR2(255 CHAR),
  TIME        VARCHAR2(255 CHAR),
  BELONGCOM   VARCHAR2(255 CHAR),
  MODELPATH   VARCHAR2(255 CHAR),
  MEMO        VARCHAR2(255 CHAR),
  MAP_ID      NUMBER(19)
);

alter table JG_APPENDANT
  add constraint UK_NGU7UYG1EMPOIXJK8EHPUMT1N unique (CODE)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table JG_APPENDANT
  add constraint FK_MLS9L0TMUKEYJEF9B4NYVD1GE foreign key (MAP_ID)
  references JG_CONSTRUCTMAP (DBID);
-------------------------------------------------------------------------------
--  上传特征点表
-------------------------------------------------------------------------------
create table JG_KEYPOINT
(
  DBID        NUMBER(19) primary key not null,
  POINTX      FLOAT not null,
  POINTY      FLOAT not null,
  POINTZ      FLOAT not null,
  HEIGHT      FLOAT,
  CODE        VARCHAR2(255 CHAR) not null,
  NAME        VARCHAR2(255 CHAR),
  TYPE        VARCHAR2(255 CHAR),
  TYPECODE    VARCHAR2(255 CHAR),
  STREET      VARCHAR2(255 CHAR),
  TIME        VARCHAR2(255 CHAR),
  BELONGCOM   VARCHAR2(255 CHAR),
  MODELPATH   VARCHAR2(255 CHAR),
  MEMO        VARCHAR2(255 CHAR),
  MAP_ID      NUMBER(19)
);

alter table JG_KEYPOINT
  add constraint UK_NGU7UYG1EMTTXJK8POIUMT1N unique (CODE)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table JG_KEYPOINT
  add constraint FK_MLS9L0KEYNVMJEF9B4NYVD1GE foreign key (MAP_ID)
  references JG_CONSTRUCTMAP (DBID);

-------------------------------------------------------------------------------
--  序列
-------------------------------------------------------------------------------
create sequence SEQ_JG_APPENDANTTYPE
minvalue 1
maxvalue 999999999999999999999999999
start with 372
increment by 1
cache 20;

create sequence SEQ_JG_POINTTYPE
minvalue 1
maxvalue 999999999999999999999999999
start with 428
increment by 1
cache 20;

create sequence SEQ_JG_APPENDANT
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

create sequence SEQ_JG_KEYPOINT
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;
