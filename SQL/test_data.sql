-- Adding data for User table 
INSERT INTO `user` VALUES
	(1, '2020-08-10 11:11:10.311000', '2020-08-10 11:11:10.311000', 'fuatk@gmail.com', '123456789ab', 'fuatk'),
	(2, '2022-06-12 13:41:30.312000', '2022-06-12 13:41:30.312000', 'ronaldo@gmail.com', '123456cr', 'cristiano'),
    (3, '2023-09-13 14:21:50.311000', '2023-09-13 14:21:50.311000', 'messi@gmail.com', '123123messi', 'messi');

SELECT * FROM user;
-- ================================================
-- Adding data for Account
--
INSERT INTO `account` VALUE
	(4, '2021-11-03 09:17:13.581000', '2021-11-03 09:17:13.121000', '105.90', 1),
	(5, '2021-11-03 09:17:13.581000', '2021-11-03 09:17:13.121000', '99.15', 2),
	(6, '2021-11-03 09:17:13.581000', '2021-11-03 09:17:13.121000', '67.12', 3);

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
    (8, '2021-11-03 10:16:14.581000', '2021-11-03 09:17:13.121000', 101.90, 0.01, 'Gift', 'INITIATED', 6, 4),
    (10, '2021-11-03 10:16:14.781000', '2021-11-03 09:17:13.781000', 10, 0.03, 'Debt', 'SUCCESS', 6, 5),
    (11, '2021-11-03 10:16:14.651000', '2021-11-03 09:17:13.651000', 200.15, 0.04, 'Gift', 'INITIATED', 4, 5),
    (12, '2021-11-03 10:16:14.846000', '2021-11-03 09:17:13.846000', 175.38, 0.05, 'OK', 'FAILED', 4, 6),
    (13, '2021-11-03 10:16:14.652000', '2021-11-03 09:17:13.652000', 150.12, 0.05, 'Debt', 'PROCESSING', 5, 4),
	(14, '2021-11-03 10:16:14.789000', '2021-11-03 09:17:13.789000', 185, 0.05, 'Gift', 'SUCCESS', 4, 6);
    
SELECT * FROM transaction;