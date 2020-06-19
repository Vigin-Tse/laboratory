-- --------------------------------------------------------
-- 主机:                           10.10.11.193
-- 服务器版本:                        5.7.30 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 bpg.bpg_partial_data 结构
CREATE TABLE IF NOT EXISTS `bpg_partial_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sensor_id` int(11) DEFAULT NULL COMMENT '传感器ID',
  `file_len` int(11) DEFAULT NULL COMMENT '文件长度L',
  `specific_version_number` int(11) DEFAULT NULL COMMENT '规范版本号',
  `file_generation_time` timestamp NULL DEFAULT NULL COMMENT '文件生成时间',
  `site_name` varchar(128) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '站点名称',
  `site_code` varchar(32) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '站点编码',
  `weather` smallint(6) DEFAULT NULL COMMENT '天气',
  `temperature` smallint(6) DEFAULT NULL COMMENT '温度',
  `humidity` smallint(6) DEFAULT NULL COMMENT '湿度',
  `equip_manufacturers` varchar(32) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '仪器厂家',
  `equip_model` varchar(32) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '仪器型号',
  `equip_version_num` int(11) DEFAULT NULL COMMENT '仪器版本号',
  `equip_serial_num` varchar(32) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '仪器序列号',
  `system_frequency` smallint(6) DEFAULT NULL COMMENT '系统频率',
  `atlas_number` smallint(6) DEFAULT NULL COMMENT '图谱数量N',
  `sync_frequency` smallint(6) DEFAULT NULL COMMENT '同步频率',
  `atlas_type_coding` smallint(6) DEFAULT NULL COMMENT '图谱类型编码',
  `atlas_data_length` int(11) DEFAULT NULL COMMENT '图谱数据长度',
  `atlas_properties` smallint(6) DEFAULT NULL COMMENT '图谱性质',
  `atlas_generation_time` timestamp NULL DEFAULT NULL COMMENT '图谱生成时间',
  `power_equipment_name` varchar(128) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '电力设备名称',
  `power_equipment_code` varchar(32) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '电力设备编码',
  `test_point_name` varchar(128) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '测点名称',
  `measuring_point_coding` varchar(32) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '测点编码',
  `detection_channel_flag` smallint(6) DEFAULT NULL COMMENT '检测通道标志',
  `stored_data_type` smallint(6) DEFAULT NULL COMMENT '存储数据类型t',
  `amplitude_unit` smallint(6) DEFAULT NULL COMMENT '幅值单位',
  `lower_amplitude_limit` smallint(6) DEFAULT NULL COMMENT '幅值下限',
  `amplitude_cap` smallint(6) DEFAULT NULL COMMENT '幅值上限',
  `frequency_band` smallint(6) DEFAULT NULL COMMENT '频带',
  `lower_frequency` smallint(6) DEFAULT NULL COMMENT '下限频率',
  `upper_frequency` smallint(6) DEFAULT NULL COMMENT '上限频率',
  `phase_window_number` int(11) DEFAULT NULL COMMENT '相位窗数m',
  `quantized_amplitude` int(11) DEFAULT NULL COMMENT '量化幅值n',
  `power_frequency_cycle` int(11) DEFAULT NULL COMMENT '工频周期数p',
  `discharge_type_probability` int(11) DEFAULT NULL COMMENT '放电类型概率',
  `discharge_threshold` int(11) DEFAULT NULL COMMENT '放电阀值',
  `discharge_times` int(11) DEFAULT NULL COMMENT '放电次数',
  `discharge` int(11) DEFAULT NULL COMMENT '放电量',
  `reserve1` varchar(134) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '预留1',
  `reserve2` int(11) DEFAULT NULL COMMENT '预留2',
  `crc_check` int(11) DEFAULT NULL COMMENT 'CRC32校验',
  `acquisition_time` datetime DEFAULT NULL COMMENT '采集时间',
  `mark` varchar(32) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_esperanto_ci COMMENT='局放传感器数据表';

-- 正在导出表  bpg.bpg_partial_data 的数据：~40 rows (大约)


-- 导出  表 bpg.bpg_partial_send 结构
CREATE TABLE IF NOT EXISTS `bpg_partial_send` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sensor_id` int(11) DEFAULT NULL COMMENT '传感器ID',
  `column_name` varchar(25) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '检测量列名',
  `detection` varchar(20) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '监测量描述',
  `data_type` varchar(10) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '数据类型',
  `len` int(11) DEFAULT NULL COMMENT '字节长度',
  `register_addr_start` varchar(16) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '寄存器地址(起始)',
  `register_addr_end` varchar(16) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '寄存器地址(终止)',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '是否生效（0:生效|1:失效）',
  `mark` varchar(200) COLLATE utf8_esperanto_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_esperanto_ci COMMENT='局放数据报文发送信息表';

-- 正在导出表  bpg.bpg_partial_send 的数据：~42 rows (大约)
/*!40000 ALTER TABLE `bpg_partial_send` DISABLE KEYS */;
INSERT INTO `bpg_partial_send` (`id`, `sensor_id`, `column_name`, `detection`, `data_type`, `len`, `register_addr_start`, `register_addr_end`, `deleted`, `mark`) VALUES
	(1, 1, 'fileLen', '文件长度L', 'Integer', 4, '0x0000', '0x0001', 1, ''),
	(2, 1, 'equipVersionNum', '规范版本号', 'Integer', 4, '0x0002', '0x0003', 1, ''),
	(3, 1, 'fileGenerationTime', '文件生成时间', 'Date', 8, '0x0004', '0x0007', 1, ''),
	(4, 1, 'siteName', '站点名称', 'String', 128, '0x0008', '0x0047', 1, 'UNICODE'),
	(5, 1, 'siteCode', '站点编码', 'String', 32, '0x0048', '0x0057', 1, 'ASCII'),
	(6, 1, 'weather', '天气', 'Integer', 2, '0x0058', '0x0058', 1, ''),
	(7, 1, 'temperature', '温度', 'Integer', 2, '0x0059', '0x0059', 1, ''),
	(8, 1, 'humidity', '湿度', 'Integer', 2, '0x005a', '0x005a', 1, ''),
	(9, 1, 'equipManufacturers', '仪器厂家', 'String', 32, '0x005b', '0x006a', 1, 'UNICODE'),
	(10, 1, 'equipModel', '仪器型号', 'String', 32, '0x006b', '0x007a', 1, 'UNICODE'),
	(11, 1, 'equipVersionNum', '仪器版本号', 'Integer', 4, '0x007b', '0x007c', 1, ''),
	(12, 1, 'equipSerialNum', '仪器序列号', 'String', 32, '0x007d', '0x008c', 1, 'ASCII'),
	(13, 1, 'systemFrequency', '系统频率', 'Integer', 2, '0x008d', '0x008d', 0, ''),
	(14, 1, 'atlasNumber', '图谱数量N', 'Integer', 2, '0x008e', '0x008e', 0, ''),
	(15, 1, 'syncFrequency', '同步频率', 'Integer', 2, '0x008f', '0x008f', 0, ''),
	(16, 1, 'atlasTypeCoding', '图谱类型编码', 'Integer', 2, '0x0090', '0x0090', 0, ''),
	(17, 1, 'atlasDataLength', '图谱数据长度', 'Integer', 4, '0x0091', '0x0092', 0, ''),
	(18, 1, 'atlasGenerationTime', '图谱生成时间', 'Date', 8, '0x0093', '0x0096', 0, ''),
	(19, 1, 'atlasProperties', '图谱性质', 'Integer', 2, '0x0097', '0x009', 0, ''),
	(20, 1, 'powerEquipmentName', '电力设备名称', 'String', 128, '0x0098', '0x00d7', 1, 'UNICODE'),
	(21, 1, 'powerEquipmentCode', '电力设备编码', 'String', 32, '0x00d8', '0x00e7', 1, 'ASCII'),
	(22, 1, 'testPointName', '测点名称', 'String', 128, '0x00e8', '0x0127', 1, 'UNICODE'),
	(23, 1, 'measuringPointCoding', '测点编码', 'String', 32, '0x0128', '0x0137', 1, 'ASCII'),
	(24, 1, 'detectionChannelFlag', '检测通道标志', 'Integer', 2, '0x0138', '0x0138', 0, ''),
	(25, 1, 'storedDataType', '存储数据类型t', 'Integer', 2, '0x0139', '0x0139', 0, ''),
	(26, 1, 'amplitudeUnit', '幅值单位', 'Integer', 2, '0x013a', '0x013a', 0, ''),
	(27, 1, 'lowerAmplitudeLimit', '幅值下限', 'Integer', 2, '0x013b', '0x013b', 0, ''),
	(28, 1, 'amplitudeCap', '幅值上限', 'Integer', 2, '0x013c', '0x013c', 0, ''),
	(29, 1, 'frequencyBand', '频带', 'Integer', 2, '0x013d', '0x013d', 0, ''),
	(30, 1, 'lowerFrequency', '下限频率', 'Integer', 2, '0x013e', '0x013e', 0, ''),
	(31, 1, 'upperFrequency', '上限频率', 'Integer', 2, '0x013f', '0x013f', 0, ''),
	(32, 1, 'phaseWindowNumber', '相位窗数m', 'Integer', 4, '0x0140', '0x0141', 0, ''),
	(33, 1, 'quantizedAmplitude', '量化幅值n', 'Integer', 4, '0x0142', '0x0143', 0, ''),
	(34, 1, 'powerFrequencyCycle', '工频周期数p', 'Integer', 4, '0x0144', '0x0145', 0, ''),
	(35, 1, 'dischargeTypeProbability', '放电类型概率', 'Integer', 8, '0x0146', '0x0149', 0, ''),
	(36, 1, 'dischargeThreshold', '放电阀值', 'Integer', 2, '0x014a', '0x014a', 0, ''),
	(37, 1, 'dischargeTimes', '放电次数', 'Integer', 2, '0x014b', '0x014b', 0, ''),
	(38, 1, 'discharge', '放电量', 'Integer', 2, '0x014c', '0x014c', 0, ''),
	(39, 1, 'reserve1', '预留1', 'String', 134, '0x014d', '0x018f', 1, ''),
	(40, 1, 'atlasData', '局部放电图谱数据', 'Json', 1000, '0x0190', '0x289f', 0, ''),
	(41, 1, 'reserve2', '预留2', 'Integer', 32, '0x28a0', '0x28af', 1, ''),
	(42, 1, 'crcCheck', 'CRC32校验', 'Integer', 4, '0x28b0', '0x28b1', 0, '');
/*!40000 ALTER TABLE `bpg_partial_send` ENABLE KEYS */;

-- 导出  表 bpg.bpg_partial_sensor 结构
CREATE TABLE IF NOT EXISTS `bpg_partial_sensor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATION_ID` int(11) DEFAULT NULL COMMENT '所属站点ID（暂弃置）',
  `SENSOR_NAME` varchar(32) DEFAULT '' COMMENT '设备名称',
  `SENSOR_CODE` varchar(100) DEFAULT 'code' COMMENT '设备编号',
  `IP_ADDRESS` varchar(100) DEFAULT 'code' COMMENT '设备IP',
  `PORT` int(11) DEFAULT '0' COMMENT '设备端口',
  `REMARKS` varchar(255) DEFAULT '' COMMENT '备注',
  `DELETED` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='局放设备表';

-- 正在导出表  bpg.bpg_partial_sensor 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `bpg_partial_sensor` DISABLE KEYS */;
INSERT INTO `bpg_partial_sensor` (`ID`, `STATION_ID`, `SENSOR_NAME`, `SENSOR_CODE`, `IP_ADDRESS`, `PORT`, `REMARKS`, `DELETED`) VALUES
	(1, 1, 'KD5600', 'KD5600', '192.168.0.170', 4001, '', 0);
/*!40000 ALTER TABLE `bpg_partial_sensor` ENABLE KEYS */;

-- 导出  表 bpg.bpg_vibration_sensor 结构
CREATE TABLE IF NOT EXISTS `bpg_vibration_sensor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `STATION_ID` int(11) DEFAULT NULL COMMENT '所属站点ID（暂弃置）',
  `SENSOR_NAME` varchar(32) DEFAULT '' COMMENT '设备名称',
  `SENSOR_CODE` varchar(50) DEFAULT 'code' COMMENT '设备编号',
  `SENSOR_MODE` int(1) DEFAULT '1' COMMENT '设备读取数据模式，1：standalone|2：remote',
  `IP_ADDRESS` varchar(50) DEFAULT 'code' COMMENT '设备IP',
  `SAMPLING_LABEL` varchar(50) DEFAULT NULL COMMENT '传感器标签',
  `REMARKS` varchar(255) DEFAULT '' COMMENT '备注',
  `DELETED` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='振动设备表';

-- 正在导出表  bpg.bpg_vibration_sensor 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `bpg_vibration_sensor` DISABLE KEYS */;
INSERT INTO `bpg_vibration_sensor` (`ID`, `STATION_ID`, `SENSOR_NAME`, `SENSOR_CODE`, `SENSOR_MODE`, `IP_ADDRESS`, `SAMPLING_LABEL`, `REMARKS`, `DELETED`) VALUES
	(1, 1, 'Vimo X1', '001', 1, '192.168.1.187', NULL, '', 0);
/*!40000 ALTER TABLE `bpg_vibration_sensor` ENABLE KEYS */;

-- 导出  表 bpg.bpg_vobration_gather 结构
CREATE TABLE IF NOT EXISTS `bpg_vobration_gather` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `VIBRATION_ID` int(11) DEFAULT NULL COMMENT '振动设备id',
  `MODE` varchar(50) DEFAULT NULL COMMENT '模式',
  `BAND_VALUE` int(11) DEFAULT NULL COMMENT '取样频率',
  `ACQUISITION_TIME` datetime DEFAULT NULL COMMENT '采集时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='振动采集结果基本信息记录';


/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
