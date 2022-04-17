/*
SQLyog Professional v12.09 (64 bit)
MySQL - 8.0.23 : Database - cquptacademic
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cquptacademic` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `cquptacademic`;

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评论者昵称',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评论内容',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `paperid` bigint DEFAULT NULL COMMENT '评论关联的论文id',
  `parentcommentid` bigint DEFAULT NULL COMMENT '父id',
  `admincomment` int DEFAULT NULL COMMENT '管理员评论(1表示是)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `comment` */

insert  into `comment`(`id`,`nickname`,`email`,`content`,`avatar`,`createtime`,`paperid`,`parentcommentid`,`admincomment`) values (1,'刘博文','111@qq.com','测试评论','/images/cquptLOGO.jpg','2022-02-16 01:02:20',21,-1,1),(2,'李四','333@qq.com','测试子评论','/images/cquptLOGO.jpg','2022-02-16 01:03:23',21,1,0),(3,'李四','333@qq.com','test','/images/cquptLOGO.jpg','2022-02-16 17:55:58',21,-1,0),(4,'李四','333@qq.com','刘博文测试评论2022/2/16','/images/cquptLOGO.jpg',NULL,21,-1,0),(5,'李四','333@qq.com','测试回复','/images/cquptLOGO.jpg',NULL,21,4,0),(7,'刘博文','111@qq.com','测试管理员评论','/images/cquptLOGO.jpg',NULL,21,-1,1),(8,'刘博文','111@qq.com','评论','/images/cquptLOGO.jpg',NULL,18,-1,1),(9,'刘博文','111@qq.com','test','/images/cquptLOGO.jpg',NULL,18,8,1),(10,'刘博文','111@qq.com','test','/images/cquptLOGO.jpg',NULL,23,-1,1),(11,'刘博文','111@qq.com','这是回复','/images/cquptLOGO.jpg',NULL,23,10,1),(12,'刘博文','111@qq.com','test2','/images/cquptLOGO.jpg',NULL,23,-1,1),(13,'刘博文','11@qq.com','1','test.jpg',NULL,1,-1,1),(14,'刘博文','11@qq.com','2','test.jpg',NULL,1,13,1),(15,'刘博文','11@qq.com','test','test.jpg',NULL,1,-1,1),(16,'刘博文','11@qq.com','11111','test.jpg',NULL,1,-1,1),(17,'刘博文','11@qq.com','test','test.jpg',NULL,11,-1,1),(18,'刘博文','11@qq.com','test','test.jpg',NULL,11,-1,1),(19,'刘博文','11@qq.com','test','test.jpg',NULL,1,-1,1),(20,'刘博文','11@qq.com','头像显示bug修复完成 2022/4/18','test.jpg',NULL,1,-1,1),(21,'李四','333@qq.com','测试普通用户头像显示','cquptLOGO.jpg',NULL,1,20,0),(22,'刘博文','11@qq.com','测试评论记录正常显示','test.jpg',NULL,1,-1,1);

/*Table structure for table `commenthistory` */

DROP TABLE IF EXISTS `commenthistory`;

CREATE TABLE `commenthistory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL COMMENT '评论者用户名',
  `content` varchar(255) NOT NULL COMMENT '评论具体内容',
  `commenttime` date DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `commenthistory` */

insert  into `commenthistory`(`id`,`username`,`content`,`commenttime`) values (2,'lbw','测试评论记录正常显示','2022-04-18'),(4,'1','1','2022-04-18'),(5,'2','2','2022-04-18');

/*Table structure for table `loginfo` */

DROP TABLE IF EXISTS `loginfo`;

CREATE TABLE `loginfo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `loginip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `logintime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=538 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `loginfo` */

insert  into `loginfo`(`id`,`loginname`,`loginip`,`logintime`) values (528,'lbw','127.0.0.1','2022-04-17 23:58:16'),(529,'lbw','127.0.0.1','2022-04-18 00:00:35'),(530,'lbw','127.0.0.1','2022-04-18 00:15:48'),(531,'lbw','127.0.0.1','2022-04-18 00:19:30'),(532,'lbw','127.0.0.1','2022-04-18 00:20:45'),(533,'lbw','127.0.0.1','2022-04-18 00:23:05'),(534,'ls','127.0.0.1','2022-04-18 00:23:50'),(535,'lbw','127.0.0.1','2022-04-18 00:48:11'),(536,'lbw','127.0.0.1','2022-04-18 00:49:28'),(537,'lbw','127.0.0.1','2022-04-18 00:54:35');

/*Table structure for table `paper` */

DROP TABLE IF EXISTS `paper`;

CREATE TABLE `paper` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '论文id',
  `commentabled` int DEFAULT NULL COMMENT '是否可评论(1可以2不可以)',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '论文具体内容',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `description` longtext COMMENT '论文描述',
  `firstpicture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '论文首图(存储路径)',
  `published` int DEFAULT NULL COMMENT '论文状态(1保存2发布)',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '论文标题',
  `updatetime` datetime DEFAULT NULL COMMENT '论文发布时间',
  `views` int DEFAULT NULL COMMENT '论文浏览次数',
  `typeid` bigint DEFAULT NULL COMMENT '论文类型id',
  `userid` bigint DEFAULT NULL COMMENT '论文发布者id',
  `commentcount` int DEFAULT NULL COMMENT '评论总数',
  `zip` varchar(255) DEFAULT NULL COMMENT '论文对应的附件',
  `flag` varchar(255) DEFAULT NULL COMMENT '论文标记',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `type的外键关联` (`typeid`),
  CONSTRAINT `type的外键关联` FOREIGN KEY (`typeid`) REFERENCES `type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `paper` */

insert  into `paper`(`id`,`commentabled`,`content`,`createtime`,`description`,`firstpicture`,`published`,`title`,`updatetime`,`views`,`typeid`,`userid`,`commentcount`,`zip`,`flag`) values (1,1,'# 通用目标检测的深度学习：综述\r\n## 摘要\r\n目标检测是计算机视觉中最基本和最具挑战性的问题之一，它试图从自然图像中的大量预定义类别中定位目标实例。深度学习技术已经成为一种直接从数据中学习特征表示的强大策略，并在通用目标检测领域取得了显著突破。鉴于这一快速发展的时期，本文的目标是对深度学习技术在这一领域的最新成就进行全面的调查。本次调查包括300多项研究贡献，涵盖了一般对象检测的许多方面：检测框架、对象特征表示、对象建议生成、上下文建模、培训策略和评估指标。我们通过确定未来研究的前景来完成调查。\r\n关键词目标检测·深度学习·卷积神经网络·目标识别\r\n\r\n# 1导言\r\n作为计算机视觉中一个长期存在的、基本的和具有挑战性的问题，目标检测（如图1所示）几十年来一直是一个活跃的研究领域（Fis）-\r\nchler和Elschlager，1973年）。目标检测的目标是确定图像中是否存在来自给定类别（如人类、汽车、自行车、狗或猫）的任何对象实例，如果存在，则返回每个对象实例的空间位置和范围（例如，通过边界框Everingham et al.2010；Russakovsky et al.2015）。作为Cornerston图像理解和计算机视觉，目标检测构成了解决复杂或高级视觉任务（如分割、场景理解、目标跟踪、图像字幕、事件检测和活动识别）的基础。目标检测支持广泛的应用，包括机器人视觉、消费电子、安全、自动驾驶、人机交互、基于内容的图像检索、智能视频监控和增强现实。\r\n最近，深度学习技术（Hinton和Salakhutdinov，2006；LeCun等人，2015）已经成为从数据中自动学习特征表示的强大方法。具体而言，这些技术在对象检测方面提供了重大改进，如图3所示。\r\n如图2所示，对象检测可分为两种类型之一（Grauman和Leibe 2011；Zhang等人2013）：检测特定实例与检测广泛类别。第一种类型旨在检测特定对象的实例（如唐纳德·特朗普的脸、埃菲尔铁塔或邻居的狗），本质上是一个匹配问题。\r\n第二种类型的目标是检测某些预定义对象类别（例如人类、汽车、自行车和狗）的实例（通常是以前看不到的）。历史上，目标检测领域的大部分工作都集中在单个类别（通常是人脸和行人）或几个特定类别的检测上。相比之下，在过去几年中，研究界已开始朝着更具挑战性的目标迈进，即建立通用目标检测系统，在该系统中，目标检测能力的广度可与人类相媲美。\r\nKrizhevsky等人（2012a）提出了一种称为AlexNet的深度卷积神经网络（DCNN），该网络在大规模视觉识别挑战赛（ILSVRC）中实现了破纪录的图像分类精度（Russakovsky等人，2015）。从那时起，计算机视觉的大部分领域的研究重点都是具体的深度学习方法，实际上包括通用目标检测领域（Girshick等人2014；He等人2014；Girshick 2015；Sermanet al.2014；Ren等人2017）。虽然已经取得了巨大的进步，如图3所示，但我们不知道这方面的全面调查\r\n如表1所示，已经发布了许多著名的物体检测调查。这些调查包括许多关于特定物体检测问题的优秀调查，如行人检测（Enzweiler和Gavrila，2009年；Geronimo等人，2010年；Dollar等人，2012年）、人脸检测（Yang等人，2002年；Zafeiriouetal，2015年）、车辆检测（Suntetal，2006年）和文本检测（Ye和Doermann 2015）。除了Zhang等人（2013年）就对象类检测主题进行的调查外，最近直接关注通用对象检测问题的调查相对较少。然而，Grauman和Leibe（2011年）、Andreopoulos和Tsotsos（2013年）对该研究进行了审查而Zhang等人（2013年）大多在2012年之前，因此在深度学习和相关方法最近取得显著成功并占据主导地位之前。\r\n深度学习允许计算模型学习极其复杂、微妙和抽象的表示，推动了广泛问题的重大进展，如视觉识别、目标检测、语音识别、自然语言处理、医学图像分析、药物发现和基因组学works，DCNNs（LeCun et al.1998，2015；Krizhevsky et al.2012a）在图像、视频、语音和音频处理方面取得了突破。可以肯定的是，已经发表了许多关于深度学习的调查，包括Bengioteal.（2013）、LeCunetal.（2015）、Litjensetal.（2017）、Gu et al.（2018），最近在ICCV和CVPR的教程中。\r\n尽管最近在目标检测领域取得了巨大的进步，但这项技术仍然比人类视觉要原始得多，还不能令人满意地解决现实世界中的挑战，如方法论。2.2.我们看到了许多长期存在的挑战：\r\n•在开放世界中工作：对任何数量的环境变化都具有鲁棒性，能够进化或适应。•受限条件下的对象检测：从标记较弱的数据或少量边界框注释、可穿戴设备、看不见的对象类别等学习。\r\n•其他模式下的目标检测：视频、RGBD图像、3D点云、激光雷达、遥感图像等。\r\n基于这些挑战，我们看到了以下未来研究方向：\r\n(1)最终目标是开发能够准确、高效地识别和定位开放世界场景中数千个或更多对象类别中的实例的对象检测，其水平与人类视觉系统相当。一般来说，对象检测算法无法识别外部的对象类别r训练数据集，但理想情况下应具有识别新对象类别的能力（Lake等人2015；Hariharan和Girshick 2017）。当前检测数据集（Everingham等人2010；Russakovsky等人2015；Lin等人2014）仅包含几十到数百个类别，远远少于人类可以识别的类别。需要开发具有更多类别的新的更大规模数据集（Hoffman等人2014；Singh等人2018a；Redmon和Farhadi 2017）。开放世界学习\r\n（2） 通用目标检测成功的原因之一是开发了高级检测框架，包括基于区域的[RCNN（Girshicketal.2014）、FastRCNN（Girshick 2015）、快速RCNN（Ren et al.2015）、Mask RCNN（He et al.2017）]和一级检测器[YOLO（Redmon et al.2016）、SSD（Liu et al.2016）].基于区域的检测器具有更高的精确度，单级检测器通常更高级和更简单。对象检测器严重依赖于底层主干网络，这些主干网络已针对图像分类进行了优化，可能会导致学习偏差；从头开始学习对象检测器可能有助于新的检测框架。更好、更高效的检测框架\r\n（3） CNN的深度显著增加，从几层[AlexNet（Krizhevsky et al.2012b）]到数百层[ResNet（He et al.2016），DenseNet（Huang et al.2017a）]。这些网络有数百万到数亿个参数，需要大量数据和GPU进行训练。为了减少或消除网络冗余，设计紧凑、轻量级网络的研究兴趣越来越大（Chen等人2017a；Alvarez和Salzmann 2016；Huang等人2018；Howard等人2017；Lin等人2017c；Yu等人2018）和网络加速（Cheng等人2018c；Hubara等人2016；Han等人2016；Li等人2017a，c；Wei等人2018）。紧凑高效的CNN功能\r\n（4）深度学习绕过人工特征工程，这需要具有较强领域知识的人类专家，然而DCNNs需要类似的显著的专门知识。很自然地考虑检测骨干架构的自动化设计，例如最近的自动机器学习（AutoML）（QuunMin等人，2018）。，已应用于图像分类和对象检测（Caiteal.2018；Chenetal.2019c；Ghiasietal.2019；Liuetal.2018a；ZophandLe2016；Zoph等人，2018）。自动神经结构搜索\r\n（5） 为了更丰富、更详细地理解图像内容，需要解决像素级对象实例分割问题（Lin等人2014；He等人2017；Hu等人2018c），这在需要单个对象精确边界的潜在应用中可以发挥重要作用。对象实例分割\r\n（6） 当前最先进的探测器采用从带有对象边界框或分割遮罩的标记数据中学习的全监督模型（Everingham等人2015；Lin等人2014；Russakovsky等人2015；Lin等人2014）。然而，全监督学习具有严重的局限性，特别是在边界框注释的收集是劳动密集型的，并且图像数量很大的情况下。在没有完全标记的培训数据的情况下，完全监督学习是不可扩展的，因此有必要了解在仅提供弱/部分注释数据的情况下如何利用CNN的力量（Bilen和Vedaldi 2016；Diba等人2017；Shi等人2017）。弱监督检测\r\n（7） 深度探测器的成功在很大程度上依赖于大量带注释的训练数据。当标记数据稀少时，深度探测器的性能经常恶化，无法很好地推广。相比之下，人类（甚至儿童）可以从极少的示例中快速学习视觉概念，并且通常可以很好地概括（Biederman 1987b；Lake et al.2015；FeiFei et al.2006）。因此，仅从几个示例学习的能力，如fewshotdetection，非常引人注目少镜头/零镜头目标检测\r\n（Chen等人，2018a；Dong等人，2018；Finn等人，2017；Kang等人，2018；Lake等人，2015；Ren等人，2018；Schwartz等人，2019）。更受约束的零炮目标检测定位和识别以前从未见过的目标类别（Bansal等人2018；Demirel等人2018；Rahman等人2018b，a），这对于需要智能和增量发现新目标类别的终身学习机器至关重要。[21] \r\n（8） 大多数探测器基于静止的2D图像；其他模式下的目标检测在自主车辆、无人机和机器人等领域具有高度相关性。这些模式在有效利用深度（Chen等人2015c；Pepik等人2015；Xiang等人2014；Wu等人2015）、视频（Feichtenhofer等人2017；Kang等人2016）和点云（Qi等人2017、2018）方面提出了新的挑战。其他模式中的目标检测\r\n（9） 最近，人们在学习通用表示法方面的努力不断增加，这些表示法在多个图像领域中都是有效的，如自然图像、视频、航空图像和医学CT图像（Rebuffi et al.2017、2018）。大多数此类研究侧重于图像分类，很少针对目标检测（Wang et al.2019），开发的检测器通常是特定领域的。独立于图像域的目标检测和跨域目标检测是未来的重要方向。通用目标检测：\r\n通用目标检测的研究领域还很不完善。然而，考虑到过去5年的突破，我们对未来的发展和机遇持乐观态度。\r\n## 总结\r\n深度学习。。。。。\r\n\r\n','2022-02-08 14:03:03','目标检测是计算机视觉中最基本和最具挑战性的问题之一，它试图从自然图像中的大量预定义类别中定位目标实例。深度学习技术已经成为一种直接从数据中学习特征表示的强大策略，并在通用目标检测领域取得了显著突破。鉴于这一快速发展的时期，本文的目标是对深度学习技术在这一领域的最新成就进行全面的调查。',NULL,1,'通用目标检测综述','2022-03-15 22:58:04',41,19,1,122,'通用目标检测综述.docx','1'),(2,1,'测试内容','2022-02-09 00:39:31','测试描述',NULL,1,'测试标题','2022-02-09 00:40:12',100,19,1,11,'FirstImportTest.xls','1'),(3,0,'# 标题\r\n## 二级标题\r\n内容测试内容测试内容测试内容测试内容测试内容测试内容测试\r\n内容测试内容测试内容测试内容测试内容测试内容测试内容测试\r\n内容测试内容测试内容测试内容测试内容测试内容测试内容测试\r\n内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试\r\nvvv\r\nv内容测试内容测试内容测试内容测试内容测试内容测试内容测试\r\n内容测试内容测试内容测试内容测试内容测试内容测试内容测试\r\n内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试','2022-02-09 21:58:13','前端描述测试',NULL,NULL,'前端标题测试111111','2022-02-09 21:58:13',0,19,1,0,'FirstImportTest.xls',NULL),(8,0,'sdasda\r\n# 刘博文\r\n的撒大苏打\r\n\r\n大飒飒打撒\r\nss','2022-02-13 17:55:03','dsadsad',NULL,NULL,'ddssd大苏打实打实大苏打撒旦刘博文222','2022-02-16 21:09:44',5,2,1,0,'FirstImportTest.xls','原创'),(11,1,'# 标题\r\nsdadas\r\n```java\r\nimport utils\r\n```\r\ndsadas\r\n','2022-02-13 20:24:54','test sdasdsadas',NULL,NULL,'2022年2月13论文测试最终版','2022-02-13 20:24:54',2,4,1,0,'FirstImportTest.xls',NULL),(13,0,'1','2022-02-13 20:31:43','1',NULL,NULL,'1','2022-02-13 20:31:43',0,7,1,0,'FirstImportTest.xls',NULL),(14,1,'121','2022-02-13 20:33:46','1',NULL,NULL,'2121','2022-02-13 20:33:46',1,2,1,0,'FirstImportTest.xls',NULL),(15,0,'1212','2022-02-13 20:40:20','1',NULL,NULL,'1','2022-02-13 20:40:20',1,2,1,0,'FirstImportTest.xls',NULL),(16,1,'1','2022-02-13 20:42:00','1',NULL,NULL,'1','2022-02-13 20:42:00',0,2,1,0,'FirstImportTest.xls',NULL),(17,1,'1','2022-02-13 20:47:39','11',NULL,NULL,'1','2022-02-13 20:47:39',0,2,1,0,'自荐信.doc',NULL),(18,1,'1','2022-02-13 20:49:27','1',NULL,NULL,'1','2022-02-13 20:49:27',4,3,1,0,'FirstImportTest.xls',NULL),(20,0,'1大苏打撒旦发生大撒大撒大撒','2022-02-13 20:50:55','1',NULL,NULL,'1edaasdasdas','2022-02-13 20:50:55',3,3,1,0,'FirstImportTest.xls',NULL),(23,1,'# 一级标题\r\ndasd\r\n实打实打算\r\n撒大苏打','2022-02-16 21:33:29','的撒大',NULL,NULL,'新增论文','2022-02-16 21:33:29',4,19,1,0,'book_mall.sql',NULL),(26,1,'无附件','2022-02-19 22:04:47','1','test.jpg',NULL,'无附件','2022-02-19 22:04:47',0,2,1,0,'',NULL);

/*Table structure for table `papertag` */

DROP TABLE IF EXISTS `papertag`;

CREATE TABLE `papertag` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `paperid` bigint NOT NULL COMMENT '论文id',
  `tagid` int NOT NULL COMMENT '标签id(采用联合主键)',
  PRIMARY KEY (`id`),
  KEY `tag外键关联` (`tagid`),
  KEY `级联删除` (`paperid`),
  CONSTRAINT `tag外键关联` FOREIGN KEY (`tagid`) REFERENCES `tag` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `级联删除` FOREIGN KEY (`paperid`) REFERENCES `paper` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `papertag` */

insert  into `papertag`(`id`,`paperid`,`tagid`) values (2,1,2),(3,1,3),(4,2,1),(5,2,5),(6,2,10),(8,8,4),(15,11,4),(16,11,6),(17,11,7),(20,13,8),(21,13,9),(22,14,3),(23,14,4),(24,15,3),(25,15,4),(26,20,6),(27,20,4),(28,20,5),(34,23,5),(35,23,6),(40,26,2),(41,26,3);

/*Table structure for table `paperuser` */

DROP TABLE IF EXISTS `paperuser`;

CREATE TABLE `paperuser` (
  `id` int NOT NULL AUTO_INCREMENT,
  `papername` varchar(255) NOT NULL COMMENT '论文名称',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `readtime` datetime DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `paperuser` */

insert  into `paperuser`(`id`,`papername`,`username`,`readtime`) values (4,'通用目标检测综述','lbw','2022-04-17 23:34:52'),(5,'新增论文','lbw','2022-04-17 23:35:01'),(6,'1','lbw','2022-04-17 23:35:03'),(8,'2022年2月13论文测试最终版','lbw','2022-04-17 23:35:12'),(9,'通用目标检测综述','lbw','2022-04-18 00:00:42'),(10,'通用目标检测综述','lbw','2022-04-18 00:15:51'),(11,'通用目标检测综述','lbw','2022-04-18 00:16:03'),(12,'通用目标检测综述','lbw','2022-04-18 00:19:39'),(13,'通用目标检测综述','lbw','2022-04-18 00:19:47'),(14,'通用目标检测综述','lbw','2022-04-18 00:20:50'),(15,'通用目标检测综述','lbw','2022-04-18 00:20:55'),(16,'通用目标检测综述','lbw','2022-04-18 00:23:11'),(17,'通用目标检测综述','ls','2022-04-18 00:23:52'),(18,'通用目标检测综述','lbw','2022-04-18 00:54:38'),(19,'通用目标检测综述','lbw','2022-04-18 00:56:53');

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `tag` */

insert  into `tag`(`id`,`name`) values (1,'机器学习'),(2,'算法设计与分析'),(3,'深度学习'),(4,'java'),(5,'Mysql'),(6,'Python'),(7,'javaee'),(8,'Hadoop'),(9,'软件工程'),(10,'Mybatis'),(11,'数据结构'),(12,'tensorflow'),(13,'pytorch'),(14,'目标检测'),(15,'安卓'),(16,'VUE'),(17,'前端'),(18,'分布式系统2'),(19,'神经网络2');

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `type` */

insert  into `type`(`id`,`name`) values (2,'工程科技1111'),(3,'工程科技2'),(4,'农业科技'),(5,'哲学与人文科学'),(6,'社会科学1'),(7,'社会科学2'),(8,'经济与管理科学'),(9,'基础科学'),(10,'医药卫生科技'),(11,'力学'),(12,'地球物理学'),(13,'一般化学工业'),(19,'信息科技');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像(存储路径)',
  `createtime` datetime DEFAULT NULL COMMENT '用户创建时间',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `nickname` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `type` int DEFAULT NULL COMMENT '用户类型(1用户，2管理员)',
  `updatetime` datetime DEFAULT NULL COMMENT '用户信息更新时间',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录名',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `user` */

insert  into `user`(`id`,`avatar`,`createtime`,`email`,`nickname`,`password`,`type`,`updatetime`,`username`,`address`) values (1,'test.jpg','2022-02-08 00:00:00','11@qq.com','刘博文','e10adc3949ba59abbe56e057f20f883e',2,'2022-02-18 21:06:53','lbw','重庆沙坪坝111222222211122'),(2,'cquptLOGO.jpg','2022-02-09 00:21:23','222@qq.com','张三','e10adc3949ba59abbe56e057f20f883e',2,'2022-02-09 00:21:51','zs','重庆渝中区'),(3,'cquptLOGO.jpg','2022-02-09 00:22:37','333@qq.com','李四','e10adc3949ba59abbe56e057f20f883e',1,'2022-02-09 00:23:20','ls','重庆江北区'),(9,'cquptLOGO.jpg','2022-04-17 18:56:34','weqwe','小红','e10adc3949ba59abbe56e057f20f883e',1,'2022-04-17 18:56:34','xh','大苏打萨达'),(10,'cquptLOGO.jpg','2022-04-18 01:02:45','222@qq.com','刘博文2','e10adc3949ba59abbe56e057f20f883e',1,'2022-04-18 01:02:45','www','cqupt');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
