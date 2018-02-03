/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 13.0 		*/
/*  Created On : 13-11-2017 18:37:53 				*/
/*  DBMS       : MySql 						*/
/* ---------------------------------------------------- */

SET FOREIGN_KEY_CHECKS=0
;

/* Drop Tables */

DROP TABLE IF EXISTS `Demo` CASCADE
;


/* Create Tables */

CREATE TABLE `Demo`
(
  `ID` INT NOT NULL,
  `Name` VARCHAR(50) NOT NULL,
  `Timestamp` DATETIME NOT NULL,
  CONSTRAINT `PK_Adverts` PRIMARY KEY (`ID` ASC)
)

;
