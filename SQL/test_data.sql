-- Adding data for User table
--	(1, '2020-08-10 11:11:10.311000', '2020-08-10 11:11:10.311000', 'charles@gmail.com', '$2a$10$cQS32vwpB4kILA37gg3GSOCb7PuVL25sOAhk.yJGobMEsD75mr8Iy', 'charles'),
--	(2, '2022-06-12 13:41:30.312000', '2022-06-12 13:41:30.312000', 'gabriel@gmail.com', '$2a$10$u8r5JascYdo/C3Ma8Gevn.gi90Yj1TsxoXXCGvMwccfsnJwDAPXmK', 'gabriel'),
--    (3, '2023-09-13 14:21:50.311000', '2023-09-13 14:21:50.311000', 'alexandre@gmail.com', '$2a$10$QiS1uhFleCBBNge3cXmzs.s5mYVa9tRQYj/cX17ZBEYy0c7tHaGTS', 'alexandre');

USE `payMyBuddy`;
INSERT INTO `user` VALUES
	(1, '2020-08-10 11:11:10.311000', '2020-08-10 11:11:10.311000', 'ibrahimovic@gmail.com', '$2a$10$xysGwpZ5dITn6Cp05gM.ZOukR8FKtC0acpfgEWz7JehXxMzNLKLX6', 'ibrahimovic'),
	(2, '2022-06-12 13:41:30.312000', '2022-06-12 13:41:30.312000', 'cristiano_ronaldo@gmail.com', '$2a$10$NtBprXvh7tazYzjmn0d0I.qVkGexDGHPQGyLCPGW4sAoyRqrC94Ju', 'cristiano_ronaldo'),
    (3, '2023-09-13 14:21:50.311000', '2023-09-13 14:21:50.311000', 'lionel_messi@gmail.com', '$2a$10$pPngu5hQgs3bBZCdss4m5OcVxpA9fv3UomG0OFY4e7CFfxDnwFtim', 'lionel_messi');


SELECT * FROM user;
SELECT id, email, user_name FROM user;
SELECT id, email, userName FROM user;
-- ================================================
-- Adding data for Account
--
INSERT INTO `account` VALUE
	(1, '2021-11-03 09:17:13.581000', '2021-11-03 09:17:13.121000', '1050', 1),
	(2, '2021-11-03 09:17:13.581000', '2021-11-03 09:17:13.121000', '990', 2),
	(3, '2021-11-03 09:17:13.581000', '2021-11-03 09:17:13.121000', '0', 3);

SELECT * FROM account;

-- ================================================
-- Adding data for Friends table
--

INSERT INTO `friends` VALUES
	(7, '2021-11-03 10:16:14.581000', '2021-11-03 19:17:13.121000',1,3),
    (9, '2021-11-03 12:19:12.543000', '2021-11-03 19:17:13.823000',2,3);

SELECT * FROM friends;

-- ================================================
-- Adding data for Transaction table
--
INSERT INTO `transaction` VALUES
    (8, '2021-11-03 10:16:14.581000', '2021-11-03 09:17:13.121000', 101.90, 0.01, 'Gift', 'INITIATED', 1, 2, 1),
    (10, '2021-11-03 10:16:14.781000', '2021-11-03 09:17:13.781000', 10, 0.03, 'Debt', 'SUCCESS', 2, 3, 2),
    (11, '2021-11-03 10:16:14.651000', '2021-11-03 09:17:13.651000', 200.15, 0.04, 'Gift', 'INITIATED', 1, 3, 1);

SELECT * FROM transaction;