
-------------------------------------------------------------------------------
--  插入危险源数据表
-------------------------------------------------------------------------------
insert into yj_wxy_info (DBID, ACTIVE, DESCRIPTION, ERRORMODE, FILENAME, FILEPATH, LATITUDE, LONGITUDE, MEMO, SOURCE_GRADE, SOURCE_NAME)
values (181, 1, '给水管线容易泄露', '给水爆管', 'Balloon.dll', '20151224/f684975e-2dc1-400e-b820-dc8dbc89f780.dll', '31.187724993214107', '120.61086778810059', '', '二级', '危险源1');
insert into yj_wxy_info (DBID, ACTIVE, DESCRIPTION, ERRORMODE, FILENAME, FILEPATH, LATITUDE, LONGITUDE, MEMO, SOURCE_GRADE, SOURCE_NAME)
values (182, 1, '燃气管线容易爆炸', '燃气爆管', 'Common.dll', '20151224/483e8520-6518-4b39-acc0-1da94bb680b2.dll', '31.187283907164055', '120.611214735135', '', '一级', '危险源2');

insert into yj_wxy_alarm (DBID, ALARM_ACTIVE, ALARM_CODE, ALARM_DATE, ISSEND, ALARM_MESSAGE, MESSAGE_STATE, DANGERSOURCE_ID)
values (1, 1, '1', '23-8月 -15 05.40.40.378000 下午', 0, '燃气管线发生泄漏', 0, 182);