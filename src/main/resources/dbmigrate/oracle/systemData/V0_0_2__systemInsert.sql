
-------------------------------------------------------------------------------
--  插入流量数据表
-------------------------------------------------------------------------------
insert into JG_REGION (DBID, REGIONCODE, REGIONNAME, STATUS, PARENT_ID)
values (162, 'sz001', '一号工地', 1, null);
insert into JG_REGION (DBID, REGIONCODE, REGIONNAME, STATUS, PARENT_ID)
values (163, 'sz002', '二号工地', 1, null);

insert into JG_USERTYPE (DBID, STATUS, USER_TYPE)
values (30, 1, '系统管理员');
insert into JG_USERTYPE (DBID, STATUS, USER_TYPE)
values (31, 1, '移动端用户');
insert into JG_USERTYPE (DBID, STATUS, USER_TYPE)
values (32, 1, '施工负责人');


insert into JG_USER (DBID, AGE, PASSWORD, PHONE, SEX, STATUS, USERNAME, REGION_ID, USERTYPE_ID)
values (1, '32', 'zf', '13432345346', '男', 1, '张帆', 162, 30);
insert into JG_USER (DBID, AGE, PASSWORD, PHONE, SEX, STATUS, USERNAME, REGION_ID, USERTYPE_ID)
values (2, '32', 'ym', '13577543567', '男', 1, '杨孟', 162, 30);
insert into JG_USER (DBID, AGE, PASSWORD, PHONE, SEX, STATUS, USERNAME, REGION_ID, USERTYPE_ID)
values (3, '34', 'zs', '13678895424', '男', 1, '张三', 162, 31);
insert into JG_USER (DBID, AGE, PASSWORD, PHONE, SEX, STATUS, USERNAME, REGION_ID, USERTYPE_ID)
values (4, '23', 'ls', '1345789765', '男', 1, '李四', 162, 31);
insert into JG_USER (DBID, AGE, PASSWORD, PHONE, SEX, STATUS, USERNAME, REGION_ID, USERTYPE_ID)
values (5, '34', 'ww', '1274638462', '男', 1, '王五', 162, 32);

insert into JG_EQUIPMENT_TYPE (DBID, EQUIPMENT_NAME, STATUS)
values (29, '扬尘噪声监测仪', 1);
insert into JG_EQUIPMENT_TYPE (DBID, EQUIPMENT_NAME, STATUS)
values (30, '超声波液位计', 0);
insert into JG_EQUIPMENT_TYPE (DBID, EQUIPMENT_NAME, STATUS)
values (31, '视频摄像机', 1);

insert into JG_EQUIPMENT (DBID, EQUIPMENT_ID, SIM, STATUS, CREATTIME, EQUIPMENTTYPE_ID, USER_ID, REGION_ID)
values (121, '1440-0028-sclw-2288', '无', 1, to_timestamp('22-09-2015 10:23:15.854000', 'dd-mm-yyyy hh24:mi:ss.ff'), 29, 1, 163);
insert into JG_EQUIPMENT (DBID, EQUIPMENT_ID, SIM, STATUS, CREATTIME, EQUIPMENTTYPE_ID, USER_ID, REGION_ID)
values (124, '1440-0028-sclw-2261', '无', 1, to_timestamp('22-09-2015 11:01:20.189000', 'dd-mm-yyyy hh24:mi:ss.ff'), 29, 1, 162);

insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (341, '新增区域', '操作日志', '张帆', to_timestamp('22-09-2015 10:12:13.523000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (342, '新增区域', '操作日志', '张帆', to_timestamp('22-09-2015 10:14:51.936000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (343, '新增区域', '操作日志', '张帆', to_timestamp('22-09-2015 10:15:14.149000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (344, '查看人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:16:38.716000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (345, '查看用户类型', '操作日志', '张帆', to_timestamp('22-09-2015 10:16:42.730000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (346, '查看工地', '操作日志', '张帆', to_timestamp('22-09-2015 10:16:47.004000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (347, '设备查询', '操作日志', '张帆', to_timestamp('22-09-2015 10:16:49.396000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (348, '查看人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:17:05.382000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (349, '查看人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:21:05.215000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (350, '查看用户类型', '操作日志', '张帆', to_timestamp('22-09-2015 10:21:06.856000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (351, '查看人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:21:09.246000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (352, '人员编辑', '操作日志', '张帆', to_timestamp('22-09-2015 10:21:09.842000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (353, '新增人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:21:35.093000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (354, '查看人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:21:35.459000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (355, '人员编辑', '操作日志', '张帆', to_timestamp('22-09-2015 10:21:36.773000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (356, '新增人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:21:53.318000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (357, '查看人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:21:53.665000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (358, '人员编辑', '操作日志', '张帆', to_timestamp('22-09-2015 10:21:54.867000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (359, '新增人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:22:14.783000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (360, '查看人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:22:15.120000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (361, '人员编辑', '操作日志', '张帆', to_timestamp('22-09-2015 10:22:16.298000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (362, '新增人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:22:30.693000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (363, '查看人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:22:31.039000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (364, '人员编辑', '操作日志', '张帆', to_timestamp('22-09-2015 10:22:33.552000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (365, '新增人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:22:51.669000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (366, '查看人员', '操作日志', '张帆', to_timestamp('22-09-2015 10:22:52.025000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (367, '设备查询', '操作日志', '张帆', to_timestamp('22-09-2015 10:22:54.891000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (368, '新增设备', '操作日志', '张帆', to_timestamp('22-09-2015 10:23:15.897000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (369, '新增设备', '操作日志', '张帆', to_timestamp('22-09-2015 11:01:20.643000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (370, '查看人员', '操作日志', '张帆', to_timestamp('22-09-2015 11:02:05.899000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (371, '查看工地', '操作日志', '张帆', to_timestamp('22-09-2015 11:58:31.092000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (372, '设备查询', '操作日志', '张帆', to_timestamp('22-09-2015 11:58:34.038000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (373, '查看人员', '操作日志', '张帆', to_timestamp('22-09-2015 12:03:17.082000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into JG_LOG (DBID, HANDLE, LOGTYPE, STAFF, TIME)
values (374, '人员编辑', '操作日志', '张帆', to_timestamp('22-09-2015 12:03:29.531000', 'dd-mm-yyyy hh24:mi:ss.ff'));

insert into JG_ALARM (DBID, ALARM_TIME, DETAILS, EQUIPMENTID, EQUIPMENTNAME, RESULT, SHOW, REGION_ID)
values (0, to_timestamp('22-09-2015 10:12:13.523000', 'dd-mm-yyyy hh24:mi:ss.ff'), '噪声超标', '1440-0028-sclw-2288', '扬尘噪声检测仪', '未处理', 0,162);
insert into JG_ALARM (DBID, ALARM_TIME, DETAILS, EQUIPMENTID, EQUIPMENTNAME, RESULT, SHOW, REGION_ID)
values (1, to_timestamp('22-09-2015 10:12:13.523000', 'dd-mm-yyyy hh24:mi:ss.ff'), '粉尘超标', '1440-0028-sclw-2261', '扬尘噪声检测仪', '未处理', 0,163);

insert into JG_MAPSTEP (DBID, STEP)
values (0, '数据预审核');
insert into JG_MAPSTEP (DBID, STEP)
values (1, '自动上图');
insert into JG_MAPSTEP (DBID, STEP)
values (2, '碰撞审核');
insert into JG_MAPSTEP (DBID, STEP)
values (3, '竣工数据验收');

insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (0, 255, 150, 100, 255, '供电管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (1, 255, 150, 0, 255, '路灯管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (2, 255, 0, 255, 0, '交通信号管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (3, 255, 0, 255, 0, '电信管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (4, 255, 0, 255, 0, '有线电视管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (5, 255, 0, 255, 0, '网通管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (6, 255, 0, 255, 0, '移动管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (7, 255, 0, 255, 0, '联通管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (8, 255, 0, 255, 0, '铁通管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (9, 255, 0, 255, 0, '电通管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (10, 255, 0, 255, 0, '共通管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (11, 255, 0, 255, 0, '监控管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (12, 255, 255, 0, 0, '给水管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (13, 255, 0, 120, 230, '雨水管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (14, 255, 0, 64, 128, '污水管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (15, 255, 120, 180, 255, '雨污合流');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (16, 255, 255, 255, 0, '中水管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (17, 255, 100, 0, 200, '燃气管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (18, 255, 64, 0, 215, '天然气管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (19, 255, 0, 0, 255, '热力管线');
insert into JG_PIPETYPE (DBID, COLOR_A, COLOR_B, COLOR_G, COLOR_R, DATATYPE)
values (20, 255, 44, 57, 84, '垃圾管线');

insert into JG_CONSTRUCTMAP (DBID, CODE, MAPNAME, STATUS, UPTIME, UPUSER, REGION_ID)
values (0, 'js', '给水测试', '未处理', to_timestamp('22-09-2015 10:12:13.523000', 'dd-mm-yyyy hh24:mi:ss.ff'), '张帆', 162);
insert into JG_CONSTRUCTMAP (DBID, CODE, MAPNAME, STATUS, UPTIME, UPUSER, REGION_ID)
values (1, 'ys', '雨水测试', '未处理', to_timestamp('22-09-2015 10:12:13.523000', 'dd-mm-yyyy hh24:mi:ss.ff'), '杨孟', 163);

insert into JG_PIPELINE (DBID, BEGINDEEP, BEGINLAT, BEGINLOG, BEGINZ, BELONGCOM, BUILDCOM, CODE, D_TYPE, DIAMETER, ENDDEEP, ENDLAT, ENDLOG, ENDZ, HOLE_ALL, HOR_NUM, MATERIAL, MEMO, STREET, USERYEARS, VER_ALL, MAP_ID, PIPETYPE_ID)
values (0, .5, 31.454332, 116.234532, .5, null, null, 'js001', null, 1.5, .5, 31.543322, 116.2364751, .5, 1, 1, null, null, null, null, 1, 0, 12);
insert into JG_PIPELINE (DBID, BEGINDEEP, BEGINLAT, BEGINLOG, BEGINZ, BELONGCOM, BUILDCOM, CODE, D_TYPE, DIAMETER, ENDDEEP, ENDLAT, ENDLOG, ENDZ, HOLE_ALL, HOR_NUM, MATERIAL, MEMO, STREET, USERYEARS, VER_ALL, MAP_ID, PIPETYPE_ID)
values (1, .5, 31.543322, 116.2364751, .5, null, null, 'js002', null, 1.5, .5, 31.544335, 116.3445432, .5, 1, 1, null, null, null, null, 1, 0, 12);
insert into JG_PIPELINE (DBID, BEGINDEEP, BEGINLAT, BEGINLOG, BEGINZ, BELONGCOM, BUILDCOM, CODE, D_TYPE, DIAMETER, ENDDEEP, ENDLAT, ENDLOG, ENDZ, HOLE_ALL, HOR_NUM, MATERIAL, MEMO, STREET, USERYEARS, VER_ALL, MAP_ID, PIPETYPE_ID)
values (2, .5, 31.544335, 116.3445432, .5, null, null, 'js003', null, 1.5, .5, 31.452254, 116.4543343, .5, 1, 1, null, null, null, null, 1, 0, 12);
insert into JG_PIPELINE (DBID, BEGINDEEP, BEGINLAT, BEGINLOG, BEGINZ, BELONGCOM, BUILDCOM, CODE, D_TYPE, DIAMETER, ENDDEEP, ENDLAT, ENDLOG, ENDZ, HOLE_ALL, HOR_NUM, MATERIAL, MEMO, STREET, USERYEARS, VER_ALL, MAP_ID, PIPETYPE_ID)
values (3, .5, 31.565432, 116.2323222, .5, null, null, 'ys001', null, 1.5, .5, 31.452223, 116.2345522, .5, 1, 1, null, null, null, null, 1, 1, 13);
insert into JG_PIPELINE (DBID, BEGINDEEP, BEGINLAT, BEGINLOG, BEGINZ, BELONGCOM, BUILDCOM, CODE, D_TYPE, DIAMETER, ENDDEEP, ENDLAT, ENDLOG, ENDZ, HOLE_ALL, HOR_NUM, MATERIAL, MEMO, STREET, USERYEARS, VER_ALL, MAP_ID, PIPETYPE_ID)
values (4, .5, 31.452223, 116.2345522, .5, null, null, 'ys002', null, 1.5, .5, 31.543426, 116.5444322, .5, 1, 1, null, null, null, null, 1, 1, 13);
insert into JG_PIPELINE (DBID, BEGINDEEP, BEGINLAT, BEGINLOG, BEGINZ, BELONGCOM, BUILDCOM, CODE, D_TYPE, DIAMETER, ENDDEEP, ENDLAT, ENDLOG, ENDZ, HOLE_ALL, HOR_NUM, MATERIAL, MEMO, STREET, USERYEARS, VER_ALL, MAP_ID, PIPETYPE_ID)
values (5, .5, 31.543426, 116.5444322, .5, null, null, 'ys003', null, 1.5, .5, 31.544263, 116.2653411, .5, 1, 1, null, null, null, null, 1, 1, 13);

insert into JG_MAPERROR (DBID, BZ, DATATYPE, DESCRIPTION, DOWNTIME, DOWNUSER, REGION, STATUS, UPTIME, UPUSER, MAP_ID, MAPSTEP_ID)
values (0, null, '给水管线', '给水管线与现有管线发生碰撞！', to_timestamp('22-09-2015 10:12:13.523000', 'dd-mm-yyyy hh24:mi:ss.ff'), '杨孟', '一号工地', '未处理', to_timestamp('22-09-2015 10:12:13.523000', 'dd-mm-yyyy hh24:mi:ss.ff'), '张帆', 0, 2);
insert into JG_MAPERROR (DBID, BZ, DATATYPE, DESCRIPTION, DOWNTIME, DOWNUSER, REGION, STATUS, UPTIME, UPUSER, MAP_ID, MAPSTEP_ID)
values (1, null, '雨水管线', '雨水管线竣工图与施工图之间差异较大没不满足施工允许误差！', to_timestamp('22-09-2015 10:12:13.523000', 'dd-mm-yyyy hh24:mi:ss.ff'), '张帆', '一号工地', '未处理', to_timestamp('22-09-2015 10:12:13.523000', 'dd-mm-yyyy hh24:mi:ss.ff'), '杨孟', 1, 3);








