-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.4-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- bgg 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `bgg` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bgg`;

-- 테이블 bgg.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `b_idx` int(10) NOT NULL AUTO_INCREMENT,
  `b_title` varchar(255) NOT NULL,
  `b_content` varchar(255) NOT NULL,
  `b_date` varchar(255) DEFAULT NULL,
  `b_writer` varchar(255) DEFAULT NULL,
  `u_idx` int(10) DEFAULT NULL,
  PRIMARY KEY (`b_idx`),
  KEY `u_idx` (`u_idx`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`u_idx`) REFERENCES `user` (`u_idx`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- 테이블 데이터 bgg.board:~11 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` (`b_idx`, `b_title`, `b_content`, `b_date`, `b_writer`, `u_idx`) VALUES
	(1, 'test', 'test', NULL, NULL, NULL),
	(2, 'test', 'test', NULL, NULL, NULL),
	(3, 'test', 'testgfdgfdg', NULL, NULL, NULL),
	(4, 'test', 'fdfd', NULL, NULL, NULL),
	(5, '아침에 잠이온다', '피곤하다', NULL, NULL, NULL),
	(6, '아침에 잠이온다', '피곤하다', NULL, NULL, NULL),
	(7, '테스트 10', '101010', NULL, NULL, NULL),
	(8, '테스트 10', '101010', NULL, NULL, NULL),
	(9, 'test', '으ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ', NULL, NULL, NULL),
	(10, '데이트 날짜 입력 테스트', '데이트 날짜 입력 테스트', '2023-01-19 09:14:28', NULL, NULL),
	(11, '데이트 날짜 입력 테스트2', '데이트 날짜 입력 테스트2', '2023-01-19 09:16:02', NULL, NULL),
	(12, '데이트 날짜 입력 테스트2', '데이트 날짜 입력 테스트2', '2023-01-19 09:19:34', NULL, NULL),
	(13, '데이트 날짜 입력 테스트2', '데이트 날짜 입력 테스트2', '2023-01-19 09:20:16', NULL, NULL);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

-- 테이블 bgg.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `u_idx` int(10) NOT NULL AUTO_INCREMENT,
  `u_id` varchar(255) NOT NULL,
  `u_pw` varchar(255) NOT NULL,
  `u_name` varchar(255) NOT NULL,
  `u_tel` varchar(255) NOT NULL,
  `u_age` varchar(255) NOT NULL,
  PRIMARY KEY (`u_idx`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- 테이블 데이터 bgg.user:~56 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`u_idx`, `u_id`, `u_pw`, `u_name`, `u_tel`, `u_age`) VALUES
	(1, 'abc', '1234', 'NAME1', '010-0000-0000', '0'),
	(2, 'a', '111', '김땡땡', '010-1111-1111', '20'),
	(3, 'b', '222', '이모씨', '010-2222-2222', '37'),
	(4, 'ccc', '34343', '강하다', '010-2121-3232', '58'),
	(6, 'zyzy', '252d', '대한민국', '010-4747-3634', '88'),
	(8, 'baba', 'cfdfd', '바비', '010-8787-7978', '69'),
	(9, 'wew', 'qqqq', '박찬호', '010-7777-3232', '23'),
	(11, 'b1', '1111', '박길동', '090-3333-7777', '49'),
	(12, 'bb1', '2222', '고길동', '090-4444-6666', '61'),
	(13, 'cc3', 'c1c1', '최길동', '090-1111-3333', '74'),
	(17, 'baba', 'cfdfd', '바비', '010-8787-7978', '69'),
	(18, 'wew', 'qqqq', '박찬호', '010-7777-3232', '23'),
	(19, 'a1', '5555', '강호동', '090-4343-4444', '32'),
	(20, 'b1', '1111', '박길동', '090-3333-7777', '49'),
	(21, 'bb1', '2222', '고길동', '090-4444-6666', '61'),
	(22, 'cc3', 'c1c1', '최길동', '090-1111-3333', '74'),
	(23, 'baba', 'cfdfd', '바비', '010-8787-7978', '69'),
	(24, 'wew', 'qqqq', '박찬호', '010-7777-3232', '23'),
	(25, 'a1', '5555', '강호동', '090-4343-4444', '32'),
	(26, 'b1', '1111', '박길동', '090-3333-7777', '49'),
	(27, 'bb1', '2222', '고길동', '090-4444-6666', '61'),
	(28, 'cc3', 'c1c1', '최길동', '090-1111-3333', '74'),
	(29, 'baba', 'cfdfd', '바비', '010-8787-7978', '69'),
	(30, 'wew', 'qqqq', '박찬호', '010-7777-3232', '23'),
	(31, 'a1', '5555', '강호동', '090-4343-4444', '32'),
	(32, 'b1', '1111', '박길동', '090-3333-7777', '49'),
	(33, 'bb1', '2222', '고길동', '090-4444-6666', '61'),
	(34, 'cc3', 'c1c1', '최길동', '090-1111-3333', '74'),
	(35, 'baba', 'cfdfd', '바비', '010-8787-7978', '69'),
	(36, 'wew', 'qqqq', '박찬호', '010-7777-3232', '23'),
	(37, 'a1', '5555', '강호동', '090-4343-4444', '32'),
	(38, 'b1', '1111', '박길동', '090-3333-7777', '49'),
	(39, 'bb1', '2222', '고길동', '090-4444-6666', '61'),
	(40, 'cc3', 'c1c1', '최길동', '090-1111-3333', '74'),
	(41, 'baba', 'cfdfd', '바비', '010-8787-7978', '69'),
	(42, 'wew', 'qqqq', '박찬호', '010-7777-3232', '23'),
	(43, 'a1', '5555', '강호동', '090-4343-4444', '32'),
	(44, 'b1', '1111', '박길동', '090-3333-7777', '49'),
	(45, 'bb1', '2222', '고길동', '090-4444-6666', '61'),
	(46, 'cc3', 'c1c1', '최길동', '090-1111-3333', '74'),
	(47, 'a', '111', '김땡땡', '010-1111-1111', '20'),
	(48, 'b', '222', '이모씨', '010-2222-2222', '37'),
	(49, 'ccc', '34343', '강하다', '010-2121-3232', '58'),
	(50, 'dfdf', '545', '홍길동', '010-2898-4767', '44'),
	(51, 'zyzy', '252d', '대한민국', '010-4747-3634', '88'),
	(52, 'a1234', 'bbbb', '만세', '010-8487-7978', '69'),
	(53, 'mm', 'mm', 'mmm', '1-2-3', '11'),
	(54, 'aaaa', '1111', '엘컴퓨터', '111-1111', '50'),
	(55, 'aaaa', '1111', '엘컴퓨터', '111-1111', '50'),
	(56, 'aaaa', '1111', '엘컴퓨터', '111-1111', '50'),
	(57, 'aaaa', '1111', '엘컴퓨터1111', '111-1111', '50'),
	(58, 'aaaa', '1111', '엘컴퓨터1111', '111-1111', '50'),
	(59, 'aaaa', '1111', '엘컴퓨터1111ss2', '111-1111', '50'),
	(60, '', '', '', '--', ''),
	(61, '답글', '11', '11', '11-2-3', '43'),
	(62, '', '', '', '--', '');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
