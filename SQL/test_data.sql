-- Adding data for User table 
-- "aaa@gmail.com: " + encoder.encode("123456789ab")
-- "bbb@email.com: " + encoder.encode("123456cr")
-- "ccc@gmail.com: " + encoder.encode("123123messi"
INSERT INTO `user` VALUES
	(1, '2020-08-10 11:11:10.311000', '2020-08-10 11:11:10.311000', 'aaa@gmail.com', '$2a$10$JdTi7Db4fCBzJdVIfE3nK.MvWfIGPW7zuetTf4re9KO6nsIULhlzG', 'maradona'),
	(2, '2022-06-12 13:41:30.312000', '2022-06-12 13:41:30.312000', 'bbb@email.com', '$2a$10$N3nmrIbqZ7ufMpdKjuhO8eSMQGKfDDA2wIoHPsR8e6nPmfYyeC0Ki', 'cristiano'),
    (3, '2023-09-13 14:21:50.311000', '2023-09-13 14:21:50.311000', 'ccc@gmail.com', '$2a$10$rqMYZJpl07JLBdMlfzJRR.Z0TML7jNSXcK7uLHE1hfGuWg/oMPbJe', 'messii'),
	(4, '2020-08-10 11:11:10.311000', '2020-08-10 11:11:10.311000', 'ddd@gmail.com', '$2a$10$JdTi7Db4fCBzJdVIfE3nK.MvWfIGPW7zuetTf4re9KO6nsIULhlzG', 'makalele'),
	(5, '2022-06-12 13:41:30.312000', '2022-06-12 13:41:30.312000', 'eee@email.com', '$2a$10$N3nmrIbqZ7ufMpdKjuhO8eSMQGKfDDA2wIoHPsR8e6nPmfYyeC0Ki', 'alonso'),
    (6, '2023-09-13 14:21:50.311000', '2023-09-13 14:21:50.311000', 'fff@gmail.com', '$2a$10$rqMYZJpl07JLBdMlfzJRR.Z0TML7jNSXcK7uLHE1hfGuWg/oMPbJe', 'sanchez');

SELECT * FROM user;
SELECT id, email, user_name FROM user;
SELECT id, email, userName FROM user;
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
    (8, '2021-11-03 10:16:14.581000', '2021-11-03 09:17:13.121000', 101.90, 0.01, 'Gift', 'INITIATED', 6, 4, 4),
    (10, '2021-11-03 10:16:14.781000', '2021-11-03 09:17:13.781000', 10, 0.03, 'Debt', 'SUCCESS', 6, 5, 5),
    (11, '2021-11-03 10:16:14.651000', '2021-11-03 09:17:13.651000', 200.15, 0.04, 'Gift', 'INITIATED', 4, 5, 6),
    (12, '2021-11-03 10:16:14.846000', '2021-11-03 09:17:13.846000', 175.38, 0.05, 'OK', 'FAILED', 4, 6, 4),
    (13, '2021-11-03 10:16:14.652000', '2021-11-03 09:17:13.652000', 150.12, 0.05, 'Debt', 'PROCESSING', 5, 4, 5),
	(14, '2021-11-03 10:16:14.789000', '2021-11-03 09:17:13.789000', 185, 0.05, 'Gift', 'SUCCESS', 4, 6, 6);
    
SELECT * FROM transaction;