
-------------------------------------------------------------------------------
--  插入异常处理记录表
-------------------------------------------------------------------------------
insert into JG_DEALTRACK (DBID, ALARMTIME, CONTEXT, DEALTIME, DETAIL, EQUIPMENT_ID, EQUIPMENTNAME, PHONE, REGION, STAFF)
values (1, to_timestamp('15-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '一号工地zs-01440-0028-sclw-2288警，请张帆速去处理！', to_timestamp('16-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '噪声超标', '1440-0028-sclw-2288', '扬尘噪声监测仪', '13765428764', '一号工地', '张帆');
insert into JG_DEALTRACK (DBID, ALARMTIME, CONTEXT, DEALTIME, DETAIL, EQUIPMENT_ID, EQUIPMENTNAME, PHONE, REGION, STAFF)
values (2, to_timestamp('15-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '一号工地fc-01440-0028-sclw-2288警，请张帆速去处理！', to_timestamp('16-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '粉尘超标', '1440-0028-sclw-2288', '扬尘噪声监测仪', '13765428764', '一号工地', '张帆');
insert into JG_DEALTRACK (DBID, ALARMTIME, CONTEXT, DEALTIME, DETAIL, EQUIPMENT_ID, EQUIPMENTNAME, PHONE, REGION, STAFF)
values (3, to_timestamp('16-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '一号工地zs-01440-0028-sclw-2288警，请张帆速去处理！', to_timestamp('17-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '噪声超标', '1440-0028-sclw-2288', '扬尘噪声监测仪', '13765428764', '一号工地', '张帆');
insert into JG_DEALTRACK (DBID, ALARMTIME, CONTEXT, DEALTIME, DETAIL, EQUIPMENT_ID, EQUIPMENTNAME, PHONE, REGION, STAFF)
values (4, to_timestamp('16-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '一号工地fc-01440-0028-sclw-2288警，请张帆速去处理！', to_timestamp('17-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '粉尘超标', '1440-0028-sclw-2288', '扬尘噪声监测仪', '13765428764', '一号工地', '张帆');
insert into JG_DEALTRACK (DBID, ALARMTIME, CONTEXT, DEALTIME, DETAIL, EQUIPMENT_ID, EQUIPMENTNAME, PHONE, REGION, STAFF)
values (5, to_timestamp('17-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '一号工地fc-01440-0028-sclw-2288警，请张帆速去处理！', to_timestamp('18-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '粉尘超标', '1440-0028-sclw-2288', '扬尘噪声监测仪', '13765428764', '一号工地', '张帆');
insert into JG_DEALTRACK (DBID, ALARMTIME, CONTEXT, DEALTIME, DETAIL, EQUIPMENT_ID, EQUIPMENTNAME, PHONE, REGION, STAFF)
values (6, to_timestamp('11-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '一号工地1440-0028-sclw-2261警，请张帆速去处理！', to_timestamp('12-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '粉尘超标', '1440-0028-sclw-2261', '扬尘噪声监测仪', '13765428764', '二号工地', '张帆');
insert into JG_DEALTRACK (DBID, ALARMTIME, CONTEXT, DEALTIME, DETAIL, EQUIPMENT_ID, EQUIPMENTNAME, PHONE, REGION, STAFF)
values (7, to_timestamp('11-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '一号工地1440-0028-sclw-2261警，请张帆速去处理！', to_timestamp('12-08-2015 14:37:42.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '噪声超标', '1440-0028-sclw-2261', '扬尘噪声监测仪', '13765428764', '二号工地', '张帆');

-------------------------------------------------------------------------------
--  插入报警阈值表
-------------------------------------------------------------------------------
insert into JG_ALARM_YZ (DBID, DUST_YZ, NOISE_YZ)
values (1, 80, 50);
