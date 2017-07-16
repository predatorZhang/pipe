
-------------------------------------------------------------------------------
--  工地数据表
-------------------------------------------------------------------------------
create table JG_REGION
(
  DBID       NUMBER(19) primary key not null,
  REGIONCODE VARCHAR2(255 CHAR),
  REGIONNAME VARCHAR2(255 CHAR),
  STATUS     NUMBER(10),
  PARENT_ID  NUMBER(19)
);

alter table JG_REGION
  add constraint FK_IWW5C6MXGOIGIBCEJ3JNGAU76 foreign key (PARENT_ID)
  references JG_REGION (DBID);

-------------------------------------------------------------------------------
--  人员类型表
-------------------------------------------------------------------------------
create table JG_USERTYPE
(
  DBID      NUMBER(19) primary key not null,
  STATUS    NUMBER(10),
  USER_TYPE VARCHAR2(255 CHAR)
);
-------------------------------------------------------------------------------
--  人员表
-------------------------------------------------------------------------------
create table JG_USER
(
  DBID        NUMBER(19) primary key not null,
  AGE         VARCHAR2(255 CHAR) not null,
  PASSWORD    VARCHAR2(255 CHAR) not null,
  PHONE       VARCHAR2(255 CHAR) not null,
  SEX         VARCHAR2(255 CHAR) not null,
  STATUS      NUMBER(10) not null,
  USERNAME    VARCHAR2(255 CHAR) not null,
  REGION_ID   NUMBER(19),
  USERTYPE_ID NUMBER(19)
);

alter table JG_USER
  add constraint FK_9PD7G8A7MKECDXKJ432IQC25A foreign key (REGION_ID)
  references JG_REGION (DBID);
alter table JG_USER
  add constraint FK_BP6RAP20UMJ9L09AOFTWQRXKH foreign key (USERTYPE_ID)
  references JG_USERTYPE (DBID);
-------------------------------------------------------------------------------
--  设备类型表
-------------------------------------------------------------------------------
create table JG_EQUIPMENT_TYPE
(
  DBID           NUMBER(19) primary key not null,
  EQUIPMENT_NAME VARCHAR2(255 CHAR),
  STATUS         NUMBER(10)
);

-------------------------------------------------------------------------------
--  设备表
-------------------------------------------------------------------------------
create table JG_EQUIPMENT
(
  DBID             NUMBER(19) primary key not null,
  EQUIPMENT_ID     VARCHAR2(255 CHAR),
  SIM              VARCHAR2(255 CHAR),
  STATUS           NUMBER(10),
  CREATTIME        TIMESTAMP(6),
  EQUIPMENTTYPE_ID NUMBER(19),
  USER_ID          NUMBER(19),
  REGION_ID        NUMBER(19)
);

alter table JG_EQUIPMENT
  add constraint UK_8FP97DQR6FGNJJVH4FQDQBD8D unique (EQUIPMENT_ID)
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

alter table JG_EQUIPMENT
  add constraint FK_37UXJQ8EY0113DX4MMAVOJAGD foreign key (EQUIPMENTTYPE_ID)
  references JG_EQUIPMENT_TYPE (DBID);
alter table JG_EQUIPMENT
  add constraint FK_ELIIAV1OXUIOOHG2TUFNCOTOL foreign key (USER_ID)
  references JG_USER (DBID);
alter table JG_EQUIPMENT
  add constraint FK_O9AQJVYFIKX59VLANEQM8WON3 foreign key (REGION_ID)
  references JG_REGION (DBID);

-------------------------------------------------------------------------------
--  日志表
-------------------------------------------------------------------------------
create table JG_LOG
(
  DBID    NUMBER(19) primary key not null,
  HANDLE  VARCHAR2(255 CHAR),
  LOGTYPE VARCHAR2(255 CHAR),
  STAFF   VARCHAR2(255 CHAR),
  TIME    TIMESTAMP(6)
);
-------------------------------------------------------------------------------
--  报警表
-------------------------------------------------------------------------------
create table JG_ALARM
(
  DBID          NUMBER(19) primary key not null,
  ALARM_TIME    TIMESTAMP(6),
  DETAILS       VARCHAR2(255 CHAR),
  EQUIPMENTID   VARCHAR2(255 CHAR),
  EQUIPMENTNAME VARCHAR2(255 CHAR),
  RESULT        VARCHAR2(255 CHAR),
  SHOW          NUMBER(10),
  REGION_ID     NUMBER(19)
);
alter table JG_ALARM
  add constraint FK_IO011Q2UTGJCAR7YPD4DRK3F9 foreign key (REGION_ID)
  references JG_REGION (DBID);
-------------------------------------------------------------------------------
--  上图步骤表
-------------------------------------------------------------------------------
create table JG_MAPSTEP
(
  DBID NUMBER(19) primary key not null,
  STEP VARCHAR2(255 CHAR)
);
-------------------------------------------------------------------------------
--  管线数据类型表
-------------------------------------------------------------------------------
create table JG_PIPETYPE
(
  DBID     NUMBER(19) primary key not null,
  COLOR_A  NUMBER(10),
  COLOR_B  NUMBER(10),
  COLOR_G  NUMBER(10),
  COLOR_R  NUMBER(10),
  DATATYPE VARCHAR2(255 CHAR)
);
-------------------------------------------------------------------------------
--  上传图层表
-------------------------------------------------------------------------------

create table JG_CONSTRUCTMAP
(
  DBID      NUMBER(19) primary key not null,
  CODE      VARCHAR2(255 CHAR),
  MAPNAME   VARCHAR2(255 CHAR),
  STATUS    VARCHAR2(255 CHAR),
  UPTIME    TIMESTAMP(6),
  UPUSER    VARCHAR2(255 CHAR),
  REGION_ID NUMBER(19)
);
alter table JG_CONSTRUCTMAP
  add constraint UK_56EMAEY8XIL3ER459OWYJM9MW unique (CODE)
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
alter table JG_CONSTRUCTMAP
  add constraint FK_CEYVL7JBW3M8CUAJKA28PMHI6 foreign key (REGION_ID)
  references JG_REGION (DBID);

-------------------------------------------------------------------------------
--  上传图层管段表
-------------------------------------------------------------------------------
create table JG_PIPELINE
(
  DBID        NUMBER(19) primary key not null,
  BEGINDEEP   FLOAT,
  BEGINLAT    FLOAT not null,
  BEGINLOG    FLOAT not null,
  BEGINZ      FLOAT not null,
  BELONGCOM   VARCHAR2(255 CHAR),
  BUILDCOM    VARCHAR2(255 CHAR),
  CODE        VARCHAR2(255 CHAR) not null,
  D_TYPE      VARCHAR2(255 CHAR),
  DIAMETER    FLOAT not null,
  ENDDEEP     FLOAT,
  ENDLAT      FLOAT not null,
  ENDLOG      FLOAT not null,
  ENDZ        FLOAT not null,
  HOLE_ALL    FLOAT not null,
  HOR_NUM     FLOAT not null,
  MATERIAL    VARCHAR2(255 CHAR),
  MEMO        VARCHAR2(255 CHAR),
  STREET      VARCHAR2(255 CHAR),
  USERYEARS   VARCHAR2(255 CHAR),
  VER_ALL     FLOAT not null,
  MAP_ID      NUMBER(19),
  PIPETYPE_ID NUMBER(19)
);

alter table JG_PIPELINE
  add constraint UK_NGU7UYG1EMTTXJK8EHPUMT1N unique (CODE)
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

alter table JG_PIPELINE
  add constraint FK_5AVXOB71VKTSATTNOOE4CC8GL foreign key (PIPETYPE_ID)
  references JG_PIPETYPE (DBID);
alter table JG_PIPELINE
  add constraint FK_MLS9L0TMUNVMJEF9B4NYVD1GE foreign key (MAP_ID)
  references JG_CONSTRUCTMAP (DBID);

-------------------------------------------------------------------------------
--  上传数据反馈表
-------------------------------------------------------------------------------
create table JG_MAPERROR
(
  DBID        NUMBER(19) primary key not null,
  BZ          VARCHAR2(255 CHAR),
  DATATYPE    VARCHAR2(255 CHAR),
  DESCRIPTION VARCHAR2(255 CHAR),
  DOWNTIME    TIMESTAMP(6),
  DOWNUSER    VARCHAR2(255 CHAR),
  REGION      VARCHAR2(255 CHAR),
  STATUS      VARCHAR2(255 CHAR),
  UPTIME      TIMESTAMP(6),
  UPUSER      VARCHAR2(255 CHAR),
  MAP_ID      NUMBER(19),
  MAPSTEP_ID  NUMBER(19)
);
alter table JG_MAPERROR
  add constraint FK_EVPSS282DPWMEKQGVYDP8BW3C foreign key (MAPSTEP_ID)
  references JG_MAPSTEP (DBID);
alter table JG_MAPERROR
  add constraint FK_RPVQTFSY87L09IJYSPON8O4WU foreign key (MAP_ID)
  references JG_CONSTRUCTMAP (DBID);

-------------------------------------------------------------------------------
--  序列
-------------------------------------------------------------------------------
create sequence SEQ_JG_ALARM
minvalue 1
maxvalue 999999999999999999999999999
start with 2
increment by 1
cache 20;

create sequence SEQ_JG_CONSTRUCTMAP_ID
minvalue 1
maxvalue 999999999999999999999999999
start with 2
increment by 1
cache 20;


create sequence SEQ_JG_EQUIPMENT_ID
minvalue 1
maxvalue 999999999999999999999999999
start with 141
increment by 1
cache 20;

create sequence SEQ_JG_EQUIPMENT_TYPE
minvalue 1
maxvalue 999999999999999999999999999
start with 32
increment by 1
cache 20;

create sequence SEQ_JG_LOG
minvalue 1
maxvalue 999999999999999999999999999
start with 381
increment by 1
cache 20;

create sequence SEQ_JG_MAPERROR_ID
minvalue 1
maxvalue 999999999999999999999999999
start with 2
increment by 1
cache 20;

create sequence SEQ_JG_MAPSTEP_ID
minvalue 1
maxvalue 999999999999999999999999999
start with 4
increment by 1
cache 20;

create sequence SEQ_JG_PIPELINE_ID
minvalue 1
maxvalue 999999999999999999999999999
start with 7
increment by 1
cache 20;

create sequence SEQ_JG_PIPETYPE_ID
minvalue 1
maxvalue 999999999999999999999999999
start with 21
increment by 1
cache 20;

create sequence SEQ_JG_REGION
minvalue 1
maxvalue 999999999999999999999999999
start with 181
increment by 1
cache 20;

create sequence SEQ_JG_USER
minvalue 1
maxvalue 999999999999999999999999999
start with 61
increment by 1
cache 20;

create sequence SEQ_JG_USERTYPE_ID
minvalue 1
maxvalue 999999999999999999999999999
start with 33
increment by 1
cache 20;

create sequence SEQ_JG_USER_ID
minvalue 1
maxvalue 999999999999999999999999999
start with 21
increment by 1
cache 20;



