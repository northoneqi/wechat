/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50067
Source Host           : localhost:3306
Source Database       : wwshop

Target Server Type    : MYSQL
Target Server Version : 50067
File Encoding         : 65001

Date: 2016-04-18 13:20:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_banktransaction`
-- ----------------------------
DROP TABLE IF EXISTS `t_banktransaction`;
CREATE TABLE `t_banktransaction` (
`ID`  int(11) NOT NULL AUTO_INCREMENT ,
`CustomerID`  int(11) NOT NULL ,
`OrderID`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`OrderMoney`  decimal(19,4) NULL DEFAULT NULL ,
`PayType`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`BankType`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BankName`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`TransactionID`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Statu`  int(11) NOT NULL ,
`AddTime`  datetime NOT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
/*!50003 AUTO_INCREMENT=143 */

;

-- ----------------------------
-- Table structure for `t_cardmoneyconvert`
-- ----------------------------
DROP TABLE IF EXISTS `t_cardmoneyconvert`;
CREATE TABLE `t_cardmoneyconvert` (
`ConvertId`  int(11) NOT NULL ,
`CardNumber`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ConvertMoney`  double NULL DEFAULT NULL ,
`GroupId`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OperationMen`  int(11) NULL DEFAULT NULL ,
`OperationTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`Remark`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ConvertId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_column_columnconsut`
-- ----------------------------
DROP TABLE IF EXISTS `t_column_columnconsut`;
CREATE TABLE `t_column_columnconsut` (
`ColumnConsut_ID`  int(11) NOT NULL ,
`ColumnID`  int(11) NULL DEFAULT NULL ,
`ConsutID`  int(11) NULL DEFAULT NULL ,
`AddTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`State`  int(11) NULL DEFAULT NULL ,
`PublishTime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
PRIMARY KEY (`ColumnConsut_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_column_columninfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_column_columninfo`;
CREATE TABLE `t_column_columninfo` (
`ColumnID`  int(11) NOT NULL ,
`ColumnName`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ParentID`  int(11) NULL DEFAULT NULL ,
`ClickTimes`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`ColumnID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_column_consult`
-- ----------------------------
DROP TABLE IF EXISTS `t_column_consult`;
CREATE TABLE `t_column_consult` (
`ConsultID`  int(11) NOT NULL ,
`Title`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`FTitle`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`Describe`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Href`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`HrefImg`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`AddTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`ClickTimes`  int(11) NULL DEFAULT NULL ,
`State`  int(11) NULL DEFAULT NULL ,
`Publisher`  int(11) NULL DEFAULT NULL ,
`Flag`  int(11) NULL DEFAULT NULL ,
`ColumnIDOld`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`ConsultID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_customers`
-- ----------------------------
DROP TABLE IF EXISTS `t_customers`;
CREATE TABLE `t_customers` (
`CustomerID`  int(11) NOT NULL ,
`Name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Sex`  varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Birthday`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`IdentityID`  varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`MinCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Mobile`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`QQ`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`EMail`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Address`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsPay`  int(11) NULL DEFAULT NULL ,
`Source`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`CustomerID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_mciroactivity`
-- ----------------------------
DROP TABLE IF EXISTS `t_mciroactivity`;
CREATE TABLE `t_mciroactivity` (
`ActivityId`  int(11) NOT NULL ,
`ActivityName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BeginDate`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`StopDate`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`PrizeCount`  int(11) NULL DEFAULT NULL ,
`BeginTime`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`EndTime`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PeiSongCnt`  int(11) NULL DEFAULT NULL ,
`SKU`  int(11) NULL DEFAULT NULL ,
`ShowURL`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ActivityId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_mcirograbbox`
-- ----------------------------
DROP TABLE IF EXISTS `t_mcirograbbox`;
CREATE TABLE `t_mcirograbbox` (
`ID`  int(11) NOT NULL ,
`OpenId`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreatTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`PeisongStatus`  int(11) NULL DEFAULT NULL ,
`SKU`  int(11) NULL DEFAULT NULL ,
`ActivityId`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_mcirojoinin`
-- ----------------------------
DROP TABLE IF EXISTS `t_mcirojoinin`;
CREATE TABLE `t_mcirojoinin` (
`LuckyId`  int(11) NOT NULL ,
`ActivityId`  int(11) NULL DEFAULT NULL ,
`OpenId`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Url`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Source`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`checklog`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`LuckyId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_mciroluckyprize`
-- ----------------------------
DROP TABLE IF EXISTS `t_mciroluckyprize`;
CREATE TABLE `t_mciroluckyprize` (
`ID`  int(11) NOT NULL ,
`ActivityId`  int(11) NULL DEFAULT NULL ,
`OpenId`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`prizeId`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`isPrize`  int(11) NULL DEFAULT NULL ,
`PrizeDate`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_mciroprize`
-- ----------------------------
DROP TABLE IF EXISTS `t_mciroprize`;
CREATE TABLE `t_mciroprize` (
`PrizeId`  int(11) NOT NULL ,
`ActivityId`  int(11) NULL DEFAULT NULL ,
`PrizeContent`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PrizeNumber`  int(11) NULL DEFAULT NULL ,
`Probably`  int(11) NULL DEFAULT NULL ,
`note`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CardNo`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CardState`  int(11) NULL DEFAULT NULL ,
`prizeType`  int(11) NULL DEFAULT NULL ,
`StartDate`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`StopDate`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
PRIMARY KEY (`PrizeId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_mcirouserinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_mcirouserinfo`;
CREATE TABLE `t_mcirouserinfo` (
`ID`  int(11) NOT NULL ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Address`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Telephone`  varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PeisongTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`PeisongMoment`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OpenId`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Area`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ActivityId`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_orderaccept`
-- ----------------------------
DROP TABLE IF EXISTS `t_orderaccept`;
CREATE TABLE `t_orderaccept` (
`AcceptId`  int(11) NOT NULL ,
`OrderCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`AcceptSite`  int(11) NULL DEFAULT NULL ,
`Accpeted`  tinyint(4) NULL DEFAULT NULL ,
`AcceptMen`  int(11) NULL DEFAULT NULL ,
`AcceptTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`AcceptType`  int(11) NULL DEFAULT NULL ,
`AcceptException`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Remark`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`AcceptId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_orderacceptlog`
-- ----------------------------
DROP TABLE IF EXISTS `t_orderacceptlog`;
CREATE TABLE `t_orderacceptlog` (
`LogId`  int(11) NOT NULL ,
`LogTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`LogType`  int(11) NULL DEFAULT NULL ,
`LogContent`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Logger`  int(11) NULL DEFAULT NULL ,
`OrderCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`LogSite`  int(11) NULL DEFAULT NULL ,
`IsShow`  tinyint(4) NULL DEFAULT NULL ,
PRIMARY KEY (`LogId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_orderaddress`
-- ----------------------------
DROP TABLE IF EXISTS `t_orderaddress`;
CREATE TABLE `t_orderaddress` (
`AddressId`  int(11) NOT NULL ,
`Consignee`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Address`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Province`  int(11) NULL DEFAULT NULL ,
`City`  int(11) NULL DEFAULT NULL ,
`Area`  int(11) NULL DEFAULT NULL ,
`Range`  int(11) NULL DEFAULT NULL ,
`Mobile`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Telephone`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Email`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PostCode`  varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UserId`  int(11) NULL DEFAULT NULL ,
`SendDate`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`SendTimeSpan`  int(11) NULL DEFAULT NULL ,
`IsCall`  tinyint(4) NULL DEFAULT NULL ,
`DistriType`  int(11) NULL DEFAULT NULL ,
`DistriMoney`  double NULL DEFAULT NULL ,
`SendDateDetail`  int(11) NULL DEFAULT NULL ,
`TranStation`  int(11) NULL DEFAULT NULL ,
`OrderCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`AddressId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_orderbasic`
-- ----------------------------
DROP TABLE IF EXISTS `t_orderbasic`;
CREATE TABLE `t_orderbasic` (
`OrderId`  int(11) NOT NULL ,
`OrderCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`SonOrderCcde`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UserId`  int(11) NULL DEFAULT NULL ,
`AddTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`Remark`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OrderType`  int(11) NULL DEFAULT NULL ,
`OrderClass`  int(11) NULL DEFAULT NULL ,
`PreferPrice`  double NULL DEFAULT NULL ,
`Saler`  int(11) NULL DEFAULT NULL ,
`SaleSite`  int(11) NULL DEFAULT NULL ,
`PayType`  int(11) NULL DEFAULT NULL ,
`PromotionId`  int(11) NULL DEFAULT NULL ,
`Writer`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`OrderId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_ordercart`
-- ----------------------------
DROP TABLE IF EXISTS `t_ordercart`;
CREATE TABLE `t_ordercart` (
`CartId`  int(11) NOT NULL ,
`UserId`  int(11) NULL DEFAULT NULL ,
`SKU`  int(11) NULL DEFAULT NULL ,
`IsMain`  tinyint(4) NULL DEFAULT NULL ,
`BuyNum`  int(11) NULL DEFAULT NULL ,
`OrderClass`  int(11) NULL DEFAULT NULL ,
`GroupId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`openId`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`CartId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_ordercommonaddress`
-- ----------------------------
DROP TABLE IF EXISTS `t_ordercommonaddress`;
CREATE TABLE `t_ordercommonaddress` (
`OAddressID`  int(11) NOT NULL ,
`OConsignee`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OAddress`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OProvince`  int(11) NULL DEFAULT NULL ,
`OCity`  int(11) NULL DEFAULT NULL ,
`OArea`  int(11) NULL DEFAULT NULL ,
`ORange`  int(11) NULL DEFAULT NULL ,
`OMbile`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OTelephone`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UserId`  int(11) NULL DEFAULT NULL ,
`OEmail`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OPostCode`  varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`TranStation`  int(11) NULL DEFAULT NULL ,
`DefAddress`  tinyint(4) NULL DEFAULT NULL ,
PRIMARY KEY (`OAddressID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_orderdetail`
-- ----------------------------
DROP TABLE IF EXISTS `t_orderdetail`;
CREATE TABLE `t_orderdetail` (
`OrderDetailId`  int(11) NOT NULL ,
`OrderCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UserId`  int(11) NULL DEFAULT NULL ,
`SKU`  int(11) NULL DEFAULT NULL ,
`ProNum`  int(11) NULL DEFAULT NULL ,
`Unit`  int(11) NULL DEFAULT NULL ,
`IsCountByWeight`  tinyint(4) NULL DEFAULT NULL ,
`BuyPrice`  double NULL DEFAULT NULL ,
`OldPrice`  double NULL DEFAULT NULL ,
`PromotionId`  int(11) NULL DEFAULT NULL ,
`Biaozhong`  double NULL DEFAULT NULL ,
`Shizhong`  double NULL DEFAULT NULL ,
`Subtotal`  double NULL DEFAULT NULL ,
`SKUName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`OrderDetailId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_orderincome`
-- ----------------------------
DROP TABLE IF EXISTS `t_orderincome`;
CREATE TABLE `t_orderincome` (
`OrderPaymentId`  int(11) NOT NULL AUTO_INCREMENT ,
`OrderCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CardPayType`  int(11) NULL DEFAULT NULL ,
`money`  decimal(10,0) NULL DEFAULT NULL ,
`CardNumber`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OutOrInType`  int(11) NULL DEFAULT NULL ,
`Remark`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`AddTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`OrderPaymentId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
/*!50003 AUTO_INCREMENT=1 */

;

-- ----------------------------
-- Table structure for `t_orderinvoice`
-- ----------------------------
DROP TABLE IF EXISTS `t_orderinvoice`;
CREATE TABLE `t_orderinvoice` (
`InvoiceId`  int(11) NOT NULL ,
`InvoiceCode`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`HeadType`  int(11) NULL DEFAULT NULL ,
`InvoiceType`  int(11) NULL DEFAULT NULL ,
`IsOpen`  tinyint(4) NULL DEFAULT NULL ,
`InvoiceInfo`  int(11) NULL DEFAULT NULL ,
`UserId`  int(11) NULL DEFAULT NULL ,
`OrderCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`InvoiceId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_param`
-- ----------------------------
DROP TABLE IF EXISTS `t_param`;
CREATE TABLE `t_param` (
`ParaId`  int(11) NOT NULL ,
`ParaName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ParaValue`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ParentId`  int(11) NULL DEFAULT NULL ,
`OnlyGroupFlag`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OnlyGroupName`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ParaId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_productbasicinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_productbasicinfo`;
CREATE TABLE `t_productbasicinfo` (
`ProId`  int(11) NOT NULL ,
`BrandId`  int(11) NULL DEFAULT NULL ,
`ClassId`  int(11) NULL DEFAULT NULL ,
`ProName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ProDescri`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`ProPackInfo`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ProAddtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`ProSericeNum`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UnitMent`  int(11) NULL DEFAULT NULL ,
`ProIdOld`  int(11) NULL DEFAULT NULL ,
`ISWX`  int(11) NULL DEFAULT NULL ,
`WXProName`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsStockSale`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`ProId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_productbasicskuinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_productbasicskuinfo`;
CREATE TABLE `t_productbasicskuinfo` (
`ProSKUId`  int(11) NOT NULL ,
`SKU`  int(11) NULL DEFAULT NULL ,
`SKUName`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`ProId`  int(11) NULL DEFAULT NULL ,
`MarketPrice`  double NULL DEFAULT NULL ,
`SellPrice`  double NULL DEFAULT NULL ,
`ListPagePic`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ColorPic`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`SKUAddtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`Volume`  int(11) NULL DEFAULT NULL ,
`SaleVolume`  int(11) NULL DEFAULT NULL ,
`Comment`  int(11) NULL DEFAULT NULL ,
`Popularity`  int(11) NULL DEFAULT NULL ,
`IsShow`  tinyint(4) NULL DEFAULT NULL ,
`SkuFlag`  int(11) NULL DEFAULT NULL ,
`IsWeb`  tinyint(4) NULL DEFAULT NULL ,
`IsCallCenter`  tinyint(4) NULL DEFAULT NULL ,
`IsSite`  tinyint(4) NULL DEFAULT NULL ,
`TaxRate`  tinyint(4) NULL DEFAULT NULL ,
`AttList`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`IsUseCal`  tinyint(4) NULL DEFAULT NULL ,
`PercentOf`  double NULL DEFAULT NULL ,
`AveragePrice`  double NULL DEFAULT NULL ,
`ProSKUIdOld`  int(11) NULL DEFAULT NULL ,
`ISWX`  int(11) NULL DEFAULT NULL ,
`WXProName`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsStockSale`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`ProSKUId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_productclass`
-- ----------------------------
DROP TABLE IF EXISTS `t_productclass`;
CREATE TABLE `t_productclass` (
`ClassId`  int(11) NOT NULL ,
`ClassName`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ParentId`  int(11) NULL DEFAULT NULL ,
`Level`  int(11) NULL DEFAULT NULL ,
`OrderSplit`  int(11) NULL DEFAULT NULL ,
`IsShow`  tinyint(4) NULL DEFAULT NULL ,
`ImgFlag`  int(11) NULL DEFAULT NULL ,
`ClassIdOld`  int(11) NULL DEFAULT NULL ,
`IsWXShow`  int(11) NULL DEFAULT NULL ,
`WXClassName`  varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ClassId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_shopcardno`
-- ----------------------------
DROP TABLE IF EXISTS `t_shopcardno`;
CREATE TABLE `t_shopcardno` (
`CardId`  int(11) NOT NULL ,
`CardNo`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CardPwd`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`SaleState`  int(11) NULL DEFAULT NULL ,
`UserState`  int(11) NULL DEFAULT NULL ,
`CardType`  int(11) NULL DEFAULT NULL ,
`Addtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`BeginDate`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`EndDate`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`Remark`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`EnableCombineCard`  int(11) NULL DEFAULT NULL ,
`EnableBackCard`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`CardId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_shopcardorderdetail`
-- ----------------------------
DROP TABLE IF EXISTS `t_shopcardorderdetail`;
CREATE TABLE `t_shopcardorderdetail` (
`CardOrderId`  int(11) NOT NULL AUTO_INCREMENT ,
`OrderCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`SunOrderCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OrderDetailId`  int(11) NULL DEFAULT NULL ,
`CardNo`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`FaceValue`  decimal(10,0) NULL DEFAULT NULL ,
`GivenValue`  decimal(10,0) NULL DEFAULT NULL ,
`Type`  bit(1) NULL DEFAULT NULL ,
`DateState`  bit(1) NULL DEFAULT NULL ,
`Remark`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`CardOrderId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
/*!50003 AUTO_INCREMENT=1 */

;

-- ----------------------------
-- Table structure for `t_shopusercardno`
-- ----------------------------
DROP TABLE IF EXISTS `t_shopusercardno`;
CREATE TABLE `t_shopusercardno` (
`ID`  int(11) NOT NULL ,
`UserID`  int(11) NULL DEFAULT NULL ,
`CardNo`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Remark`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_userbasic`
-- ----------------------------
DROP TABLE IF EXISTS `t_userbasic`;
CREATE TABLE `t_userbasic` (
`UserID`  int(11) NOT NULL ,
`Username`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UserPwd`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`NickName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UIntegral`  int(11) NULL DEFAULT NULL ,
`Phone`  varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Email`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PhoneCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CodeTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`EValidation`  tinyint(4) NULL DEFAULT NULL ,
`PValidation`  tinyint(4) NULL DEFAULT NULL ,
`Regtime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`UserType`  int(11) NULL DEFAULT NULL ,
`UserLevel`  int(11) NULL DEFAULT NULL ,
`UserPic`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UserMoney`  double NULL DEFAULT NULL ,
`TrueName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UserState`  int(11) NULL DEFAULT NULL ,
`OtherCode`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`EmailOverTime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`HasSendTime`  int(11) NULL DEFAULT NULL ,
`SendData`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`AccountType`  int(11) NULL DEFAULT NULL ,
`Address`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Province`  int(11) NULL DEFAULT NULL ,
`City`  int(11) NULL DEFAULT NULL ,
`Area`  int(11) NULL DEFAULT NULL ,
`Range`  int(11) NULL DEFAULT NULL ,
`WX_OPENID`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`UserID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_weixinorder`
-- ----------------------------
DROP TABLE IF EXISTS `t_weixinorder`;
CREATE TABLE `t_weixinorder` (
`WeixinOrderId`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`WeixinOrderCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OrderCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OrderTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`WeixinOrderId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_weixinpayinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_weixinpayinfo`;
CREATE TABLE `t_weixinpayinfo` (
`WeixinpayId`  varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`TradeMode`  int(11) NULL DEFAULT NULL ,
`TradeState`  int(11) NULL DEFAULT NULL ,
`Partner`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BankType`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BankBillno`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`TotalFee`  int(11) NULL DEFAULT NULL ,
`FeeType`  int(11) NULL DEFAULT NULL ,
`NotifyId`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`TransactionId`  varchar(28) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OutTradeNo`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`TimeEnd`  varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`TransportFee`  int(11) NULL DEFAULT NULL ,
`ProductFee`  int(11) NULL DEFAULT NULL ,
`Discount`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`WeixinpayId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_zc_backinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_zc_backinfo`;
CREATE TABLE `t_zc_backinfo` (
`ActivityId`  int(11) NOT NULL ,
`Playnum`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Iscanal`  tinyint(4) NULL DEFAULT NULL ,
`Ispayall`  tinyint(4) NULL DEFAULT NULL ,
`Isplaymore`  tinyint(4) NULL DEFAULT NULL ,
`Ispaymore`  tinyint(4) NULL DEFAULT NULL ,
`Activityinfo`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ActivityId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_zc_orderbasic`
-- ----------------------------
DROP TABLE IF EXISTS `t_zc_orderbasic`;
CREATE TABLE `t_zc_orderbasic` (
`zccode`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`sku`  int(11) NULL DEFAULT NULL ,
`Province`  int(11) NULL DEFAULT NULL ,
`City`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`area`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`address`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`copies`  int(11) NULL DEFAULT NULL ,
`isfinish`  int(11) NULL DEFAULT NULL ,
`WX_OrderCode`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`addTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`EndTime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`OpenId`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Name`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BuyNumber`  int(11) NULL DEFAULT NULL ,
`iscancel`  int(11) NULL DEFAULT NULL ,
`tel`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ispayment`  int(11) NULL DEFAULT NULL ,
`payurl`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`senddate`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sendtimespan`  int(11) NULL DEFAULT NULL ,
`skuname`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`goodprice`  double NULL DEFAULT NULL ,
`paymenttime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
PRIMARY KEY (`zccode`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_zc_payinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_zc_payinfo`;
CREATE TABLE `t_zc_payinfo` (
`Id`  int(11) NOT NULL ,
`zccode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Paymoney`  double NULL DEFAULT NULL ,
`Payname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PayopenId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`addtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`out_trade_no`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`transport_fee`  int(11) NULL DEFAULT NULL ,
`trade_state`  int(11) NULL DEFAULT NULL ,
`trade_mode`  int(11) NULL DEFAULT NULL ,
`partner`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`bank_type`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`bank_billno`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`total_fee`  int(11) NULL DEFAULT NULL ,
`fee_type`  int(11) NULL DEFAULT NULL ,
`notify_id`  varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`transaction_id`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`time_end`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`product_fee`  int(11) NULL DEFAULT NULL ,
`discount`  int(11) NULL DEFAULT NULL ,
`refundStatus`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`Id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `t_zc_productinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_zc_productinfo`;
CREATE TABLE `t_zc_productinfo` (
`Id`  int(11) NOT NULL ,
`ActivityId`  int(11) NULL DEFAULT NULL ,
`SellPrice`  double NULL DEFAULT NULL ,
`goodpng`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sku`  int(11) NULL DEFAULT NULL ,
`goodname`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`Id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Auto increment value for `t_banktransaction`
-- ----------------------------
ALTER TABLE `t_banktransaction` AUTO_INCREMENT=143;

-- ----------------------------
-- Auto increment value for `t_orderincome`
-- ----------------------------
ALTER TABLE `t_orderincome` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `t_shopcardorderdetail`
-- ----------------------------
ALTER TABLE `t_shopcardorderdetail` AUTO_INCREMENT=1;
