
-------------------------------------------------------------------------------
--  插入预警数据表
-------------------------------------------------------------------------------
insert into YJ_WARNING_ALARMPIPE (DBID, ACTIVE, ALARMTIME, FAULTDESC, FAULTTYPE, PROBABLITY, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET)
values (1, 1, to_timestamp('18-08-2015 11:05:12.887000', 'dd-mm-yyyy hh24:mi:ss.ff'), '管线破裂', '爆管','0.85',  null, 'GX_JSL_3000_HDL_153', '给水管线', null, '湖东路');
insert into YJ_WARNING_ALARMPIPE (DBID, ACTIVE, ALARMTIME, FAULTDESC, FAULTTYPE, PROBABLITY, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET)
values (2, 1, to_timestamp('18-08-2015 11:14:54.442000', 'dd-mm-yyyy hh24:mi:ss.ff'), '管线破裂', '爆管','0.9',  null, 'GX_JSL_3000_HDL_152', '给水管线', null, '湖东路');

insert into YJ_WARNING_DIAGNOSIS (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, ISACTUALOCCUR, ISCORRECT, PRECISION)
values (7, '', to_timestamp('18-08-2015 15:39:16.933000', 'dd-mm-yyyy hh24:mi:ss.ff'), '', '泄漏', '0.3', 1, null, 'GX_JSL_3000_HDL_185', '给水管线', null, '湖东路', '', '', '');
insert into YJ_WARNING_DIAGNOSIS (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, ISACTUALOCCUR, ISCORRECT, PRECISION)
values (8, '', to_timestamp('18-08-2015 15:39:44.606000', 'dd-mm-yyyy hh24:mi:ss.ff'), '', '泄漏', '0.2', 1, null, 'GX_JSL_3000_HDL_152', '给水管线', null, '湖东路', '', '', '');
insert into YJ_WARNING_DIAGNOSIS (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, ISACTUALOCCUR, ISCORRECT, PRECISION)
values (1, '', to_timestamp('18-08-2015 15:24:00.160000', 'dd-mm-yyyy hh24:mi:ss.ff'), '', '泄漏', '0.1', 1, null, 'GX_JSL_3000_HDL_152', '给水管线', null, '湖东路', '', '', '');
insert into YJ_WARNING_DIAGNOSIS (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, ISACTUALOCCUR, ISCORRECT, PRECISION)
values (5, '', to_timestamp('18-08-2015 15:35:37.304000', 'dd-mm-yyyy hh24:mi:ss.ff'), '', '泄漏', '0.3', 1, null, 'GX_JSL_3000_HDL_186', '给水管线', null, '湖东路', '', '', '');
insert into YJ_WARNING_DIAGNOSIS (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, ISACTUALOCCUR, ISCORRECT, PRECISION)
values (6, '', to_timestamp('18-08-2015 15:35:59.718000', 'dd-mm-yyyy hh24:mi:ss.ff'), '', '泄漏', '0.3', 1, null, 'GX_JSL_3000_HDL_187', '给水管线', null, '湖东路', '', '', '');
insert into YJ_WARNING_DIAGNOSIS (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, ISACTUALOCCUR, ISCORRECT, PRECISION)
values (9, '', to_timestamp('18-08-2015 15:41:35.842000', 'dd-mm-yyyy hh24:mi:ss.ff'), '', '泄漏', '0.3', 1, null, 'GX_JSL_3000_HDL_152', '给水管线', null, '湖东路', '', '', '');
insert into YJ_WARNING_DIAGNOSIS (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, ISACTUALOCCUR, ISCORRECT, PRECISION)
values (10, '', to_timestamp('18-08-2015 15:42:40.500000', 'dd-mm-yyyy hh24:mi:ss.ff'), '给水管线发生严重泄漏', '泄漏', '0.7', 0, null, 'GX_JSL_3000_HDL_151', '给水管线', '建议及时进行管线排查，及时进行管线修复', '湖东路', '', '', '');
insert into YJ_WARNING_DIAGNOSIS (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, ISACTUALOCCUR, ISCORRECT, PRECISION)
values (21, '', to_timestamp('19-08-2015 08:54:36.269000', 'dd-mm-yyyy hh24:mi:ss.ff'), '', '泄漏', '0.3', 1, null, null, '给水管线', null, '湖东路', '', '', '');
insert into YJ_WARNING_DIAGNOSIS (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, ISACTUALOCCUR, ISCORRECT, PRECISION)
values (2, '', to_timestamp('18-08-2015 15:28:48.492000', 'dd-mm-yyyy hh24:mi:ss.ff'), '', '泄漏', '0.3', 1, null, 'GX_JSL_3000_HDL_153', '给水管线', null, '湖东路', '', '', '');
insert into YJ_WARNING_DIAGNOSIS (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, ISACTUALOCCUR, ISCORRECT, PRECISION)
values (3, '', to_timestamp('18-08-2015 15:28:52.192000', 'dd-mm-yyyy hh24:mi:ss.ff'), '', '泄漏', '0.3', 1, null, 'GX_JSL_3000_HDL_151', '给水管线', null, '湖东路', '', '', '');
insert into YJ_WARNING_DIAGNOSIS (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, ISACTUALOCCUR, ISCORRECT, PRECISION)
values (4, '', to_timestamp('18-08-2015 15:32:49.017000', 'dd-mm-yyyy hh24:mi:ss.ff'), '', '泄漏', '0.3', 1, null, 'GX_JSL_3000_HDL_154', '给水管线', null, '湖东路', '', '', '');


insert into YJ_WARNING_FORECAST (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, PROBBIAS, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, DIAGPROB, DIAGTIME, DIAGRECORDID, ISCORRECT, PRECISION)
values (1, '', to_timestamp('19-08-2015 08:55:52.166000', 'dd-mm-yyyy hh24:mi:ss.ff'), '检测到给水管线噪声值过大，有发生泄露的可能性', '泄漏', '0.9', '', 0, null, 'GX_JSL_3000_HDL_151', '给水管线', '建议及时进行管线排查，如存在故障及时进行管线修复', '湖东路', '', '', '', '', '');

insert into YJ_WARNING_FORECAST (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, PROBBIAS, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, DIAGPROB, DIAGTIME, DIAGRECORDID, ISCORRECT, PRECISION)
values (2, '', to_timestamp('19-08-2015 08:55:56.075000', 'dd-mm-yyyy hh24:mi:ss.ff'), '', null, '0.1', '', 1, null, 'GX_JSL_3000_HDL_152', '给水管线', null, '湖东路', '', '', '', '', '');
insert into YJ_WARNING_FORECAST (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, PROBBIAS, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, DIAGPROB, DIAGTIME, DIAGRECORDID, ISCORRECT, PRECISION)
values (3, '', to_timestamp('19-08-2015 08:56:09.299000', 'dd-mm-yyyy hh24:mi:ss.ff'), '检测到给水管线噪声值过大，有发生泄露的可能性', '泄漏', '0.8', '', 0, null, 'GX_JSL_3000_HDL_153', '给水管线', '建议及时进行管线排查，如存在故障及时进行管线修复', '湖东路', '', '', '', '', '');
insert into YJ_WARNING_FORECAST (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, PROBBIAS, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, DIAGPROB, DIAGTIME, DIAGRECORDID, ISCORRECT, PRECISION)
values (4, '', to_timestamp('19-08-2015 08:56:16.734000', 'dd-mm-yyyy hh24:mi:ss.ff'), '', null, '0.1', '', 1, null, 'GX_JSL_3000_HDL_154', '给水管线', null, '湖东路', '', '', '', '', '');
insert into YJ_WARNING_FORECAST (DBID, RECORDID, CHECKTIME, FAULTDESC, FAULTTYPE, PROBABLITY, PROBBIAS, ISNORMAL, MEMO, PIPEID, PIPETYPE, STRATEGY, STREET, DIAGPROB, DIAGTIME, DIAGRECORDID, ISCORRECT, PRECISION)
values (5, '', to_timestamp('19-08-2015 08:56:20.859000', 'dd-mm-yyyy hh24:mi:ss.ff'), '', null, '0.1', '', 1, null, 'GX_JSL_3000_HDL_152', '给水管线', null, '湖东路', '', '', '', '', '');

insert into YJ_WARNING_HEALTH (DBID, EVALTIME, RANK, MEMO, PIPEID, PIPETYPE, RESULT, STREET, SUGGESTION, EVALRECORDID)
values (1, to_timestamp('19-08-2015 08:56:40.668000', 'dd-mm-yyyy hh24:mi:ss.ff'), '亚健康', null, 'GX_JSL_3000_HDL_151', '给水管线', '当前管线健康度为亚健康', '湖东路', '建议及时进行管线修复', '');
insert into YJ_WARNING_HEALTH (DBID, EVALTIME, RANK, MEMO, PIPEID, PIPETYPE, RESULT, STREET, SUGGESTION, EVALRECORDID)
values (2, to_timestamp('19-08-2015 08:56:44.113000', 'dd-mm-yyyy hh24:mi:ss.ff'), '疾病', null, 'GX_JSL_3000_HDL_152', '给水管线', '当前管线存在疾病', '湖东路', '建议及时进行管线修复或更换', '');
insert into YJ_WARNING_HEALTH (DBID, EVALTIME, RANK, MEMO, PIPEID, PIPETYPE, RESULT, STREET, SUGGESTION, EVALRECORDID)
values (3, to_timestamp('19-08-2015 08:56:48.049000', 'dd-mm-yyyy hh24:mi:ss.ff'), '亚健康', null, 'GX_JSL_3000_HDL_153', '给水管线', '当前管线健康度为亚健康', '湖东路', '建议及时进行管线修复', '');
insert into YJ_WARNING_HEALTH (DBID, EVALTIME, RANK, MEMO, PIPEID, PIPETYPE, RESULT, STREET, SUGGESTION, EVALRECORDID)
values (4, to_timestamp('19-08-2015 08:58:27.514000', 'dd-mm-yyyy hh24:mi:ss.ff'), '疾病', null, 'GX_JSL_3000_HDL_152', '给水管线', '当前管线存在疾病', '湖东路', '建议及时进行管线修复或更换', '');
insert into YJ_WARNING_HEALTH (DBID, EVALTIME, RANK, MEMO, PIPEID, PIPETYPE, RESULT, STREET, SUGGESTION, EVALRECORDID)
values (5, to_timestamp('19-08-2015 09:51:08.982000', 'dd-mm-yyyy hh24:mi:ss.ff'), '严重疾病', null, 'GX_JSL_3000_HDL_154', '给水管线', '当前管线存在严重疾病，存在严重故障隐患！', '湖东路', '建议及时进行管线修复或更换', '');
insert into YJ_WARNING_HEALTH (DBID, EVALTIME, RANK, MEMO, PIPEID, PIPETYPE, RESULT, STREET, SUGGESTION, EVALRECORDID)
values (6, to_timestamp('20-08-2015 09:51:08.982000', 'dd-mm-yyyy hh24:mi:ss.ff'), '健康', null, 'GX_JSL_3000_HDL_187', '给水管线', '当前管线运行状态健康！', '湖东路', '', '');