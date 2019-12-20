-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.26 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 xinhua_library 的数据库结构
CREATE DATABASE IF NOT EXISTS `xinhua_library` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `xinhua_library`;

-- 导出  表 xinhua_library.administrator 结构
CREATE TABLE IF NOT EXISTS `administrator` (
  `admin_username` varchar(50) NOT NULL,
  `admin_password` varchar(50) NOT NULL,
  `admin_name` varchar(50) NOT NULL,
  `admin_sex` varchar(50) NOT NULL,
  `admin_tel` char(11) NOT NULL,
  PRIMARY KEY (`admin_username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 正在导出表  xinhua_library.administrator 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` (`admin_username`, `admin_password`, `admin_name`, `admin_sex`, `admin_tel`) VALUES
	('peiqi', 'peiqi', 'peiqi', '男', '123456789');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;

-- 导出  表 xinhua_library.bookdb 结构
CREATE TABLE IF NOT EXISTS `bookdb` (
  `book_id` varchar(255) NOT NULL,
  `book_name` varchar(255) NOT NULL,
  `book_author` varchar(255) NOT NULL,
  `book_press` varchar(255) NOT NULL,
  `book_category` varchar(255) NOT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `book_desc` varchar(255) NOT NULL,
  `book_price` double(10,0) NOT NULL,
  `book_kunumber` int(11) NOT NULL,
  `book_xiaonumber` int(11) NOT NULL,
  PRIMARY KEY (`book_id`) USING BTREE,
  KEY `bookdb_ibfk_1` (`book_category`) USING BTREE,
  CONSTRAINT `bookdb_ibfk_1` FOREIGN KEY (`book_category`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 正在导出表  xinhua_library.bookdb 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `bookdb` DISABLE KEYS */;
INSERT INTO `bookdb` (`book_id`, `book_name`, `book_author`, `book_press`, `book_category`, `filename`, `path`, `book_desc`, `book_price`, `book_kunumber`, `book_xiaonumber`) VALUES
	('001cefd4-5079-49fd-859c-b37fb2eaf401', '数据结构', '111', '111', '6ff143bf-bc1a-4ac3-8920-009b2cad0385', '3db3b8d1-e060-464b-99b9-02b1b3c05ad1.', '2019-12-20', 'jjj', 22, 222, 0),
	('1b4860df-7969-4a0b-80b5-535ca956ddc6', '愿所有相遇，都恰逢其时', 'DTT', '浙江文艺出版社', 'f242e1ee-9e16-4766-9066-18d166e96f55', '53e5e9d9-09ea-4c06-b719-da2275aaf146.jpg', '2019-12-18', '一切都是*好的安排，结束的，已然结束；相逢的，终将遇见，不早不晚。41篇暖意萦怀的生活感悟，让你不畏将来，不念过去，在忙乱的世界中守住内心的安定和富足', 40, 17, 0),
	('239d8907-3407-4d81-9173-80881d767bd9', '皮囊', '蔡崇达', '天津人民出版社', 'f242e1ee-9e16-4766-9066-18d166e96f55', '11c56e1f-0541-4932-ab86-3090054dd3b1.jpg', '2019-12-18', '刘德华、韩寒、白岩松感动推荐。2019全新升级，书封采用具有肌肤质感的SKINS纸。命运是一条闪闪发光的金色河流，我们彼此相遇，卸下皮囊，以心相交。', 46, 50, 0),
	('25e2bafe-52c7-471e-9099-a1ad9ccc376e', '经济学原理', '马歇尔 ', '湖南文艺出版社', '6ff143bf-bc1a-4ac3-8920-009b2cad0385', 'e603429a-d9ba-4960-8b20-bcb667d4be9d.jpg', '2019-12-18', '经济学诞生的标志，三部划时代的巨著之一，一本任何人都读得懂的经济学巨著，微观经济学的奠基之作。', 32, 12, 0),
	('59a57997-130c-4cd2-b56c-a8382b607782', '计算机视觉：模型、学习和推理', '[英]西蒙 J.D. 普林斯', '机械工业出版社', '65528719-e8f0-466f-b7b7-253d01893545', 'c0da12b4-0d88-4518-b582-295d4da0b259.jpg', '2019-12-18', '计算机视觉的经典教材，全面讲述与现代计算机视觉研究和应用相关的机器学习基础知识和核心技术。', 94, 222, 0),
	('8d9c370e-1df9-4685-b6de-0adadfab3a04', '自在独行', '贾平凹', '长江文艺出版社', 'f5ee42b1-6b72-40b5-8bec-2d7ef670dece', '2f31d88a-995b-4161-aac7-a22b11105815.jpg', '2019-12-18', '《自在独行》是2016年6月长江文艺出版社出版发行的图书，作者是贾平凹。这本书的目的是写给生命的行者，愿他们能懂得孤独的真义，在生活里多一些从容潇洒。', 44, 29, 1),
	('9c1119bf-07ea-4fa0-befc-76d87e1d0944', '大话计算机', '冬瓜哥', '清华大学出版社', '65528719-e8f0-466f-b7b7-253d01893545', '0f0442cb-f5f8-4a49-a5fb-beb1948769c8.jpg', '2019-12-18', '本书第11章没有脑图，无脑阅读即可。一本多图多思想的计算机科普奇书，一本从高中生到博士生都能汲取丰富营养的奇书，一本全彩印刷、卓越体验的立体化奇书。', 526, 111, 0),
	('a990e8e1-ce43-404b-bcdf-63fffdb99f72', '算法导论', '(美)科尔曼', '机械工业出版社', 'cd32feab-ded1-4aa0-a6d2-a0246d672a4e', 'e935e655-9572-4e24-8a90-b7c2092d7050.jpg', '2019-12-18', '影响全球千万程序员的“算法圣经”！国内外千余所高校采用！MIT四大名师联手铸就', 93, 85, 0),
	('b917d360-22a1-40bd-b5e9-1a450f1f410b', '战舰的秘密', '英国DK公司', '中国大百科全书出版社', 'd59ca229-def6-4574-8197-5587fa9b8c6f', 'a66ae764-0ac5-4934-95a0-03062c3f7aeb.jpg', '2019-12-18', '英国卫报评为“90年代zui伟大的三大童书之一”，全球销量超百万册！解密剖面结构，走进千毫毕现的内部世界！国际阅读学会“儿童的选择”奖', 180, 99, 0);
/*!40000 ALTER TABLE `bookdb` ENABLE KEYS */;

-- 导出  表 xinhua_library.category 结构
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` varchar(255) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `category_desc` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 正在导出表  xinhua_library.category 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`category_id`, `category_name`, `category_desc`) VALUES
	('65528719-e8f0-466f-b7b7-253d01893545', '科学技术', '科学技术'),
	('6ff143bf-bc1a-4ac3-8920-009b2cad0385', '经济金融', '经济金融'),
	('78a6706c-5ed8-4bd0-9604-9090425073de', '计算机', '计算机'),
	('cd32feab-ded1-4aa0-a6d2-a0246d672a4e', '教育考试', '教育考试'),
	('d59ca229-def6-4574-8197-5587fa9b8c6f', '少儿童书', '少儿童书'),
	('f242e1ee-9e16-4766-9066-18d166e96f55', '文学艺术', '文学艺术'),
	('f5ee42b1-6b72-40b5-8bec-2d7ef670dece', '人文社科', '人文社科');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- 导出  表 xinhua_library.favorite 结构
CREATE TABLE IF NOT EXISTS `favorite` (
  `favorite_id` varchar(100) NOT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `book_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`favorite_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `book_id` (`book_id`) USING BTREE,
  CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `favorite_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `bookdb` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 正在导出表  xinhua_library.favorite 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
INSERT INTO `favorite` (`favorite_id`, `user_id`, `book_id`) VALUES
	('502842bf-568a-488d-b797-485642224a0b', '6738a54a-dde7-41b9-81fb-f0904b6b9ece', 'b917d360-22a1-40bd-b5e9-1a450f1f410b');
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;

-- 导出  表 xinhua_library.orderitems 结构
CREATE TABLE IF NOT EXISTS `orderitems` (
  `id` varchar(100) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `book_id` varchar(100) DEFAULT NULL,
  `ordernum` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `book_id_fk` (`book_id`) USING BTREE,
  KEY `ordernum_fk` (`ordernum`) USING BTREE,
  CONSTRAINT `book_id_fk` FOREIGN KEY (`book_id`) REFERENCES `bookdb` (`book_id`),
  CONSTRAINT `ordernum_fk` FOREIGN KEY (`ordernum`) REFERENCES `orders` (`ordernum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 正在导出表  xinhua_library.orderitems 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
INSERT INTO `orderitems` (`id`, `quantity`, `price`, `book_id`, `ordernum`) VALUES
	('20191218610048090459600', 1, 44, '8d9c370e-1df9-4685-b6de-0adadfab3a04', '20191218610048089331900');
/*!40000 ALTER TABLE `orderitems` ENABLE KEYS */;

-- 导出  表 xinhua_library.orders 结构
CREATE TABLE IF NOT EXISTS `orders` (
  `ordernum` varchar(100) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `time` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `userId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ordernum`) USING BTREE,
  KEY `user_Id_fk` (`userId`) USING BTREE,
  CONSTRAINT `user_Id_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 正在导出表  xinhua_library.orders 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`ordernum`, `quantity`, `money`, `time`, `status`, `userId`) VALUES
	('20191218610048089331900', 1, 44, '2019-12-18 17:25:44', 0, '6ee42063-d851-4387-b517-4bb25a1d8383');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- 导出  表 xinhua_library.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` varchar(100) NOT NULL,
  `user_username` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_sex` varchar(50) DEFAULT NULL,
  `user_tel` char(11) DEFAULT NULL,
  `user_address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 正在导出表  xinhua_library.user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `user_username`, `user_password`, `user_name`, `user_sex`, `user_tel`, `user_address`) VALUES
	('6738a54a-dde7-41b9-81fb-f0904b6b9ece', '11111', '11111', '', '男', '11111111111', '111'),
	('6ee42063-d851-4387-b517-4bb25a1d8383', 'qiaozhi', 'qiaozhi', 'qiaozhi', '?', '2017110447', '55555');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
