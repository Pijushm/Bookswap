
INSERT INTO `genre` VALUES (1, 'ACTION');
INSERT INTO `genre` VALUES (2, 'ADVENTURE');
INSERT INTO `genre` VALUES (3, 'BIOGRAPHICAl');
INSERT INTO `genre` VALUES (4, 'BUSINESS');
INSERT INTO `genre` VALUES (5, 'CHILDRENS');
INSERT INTO `genre` VALUES (6, 'CLASSIC');
INSERT INTO `genre` VALUES (7, 'COMING OF AGE');
INSERT INTO `genre` VALUES (8, 'COMPUTERS');
INSERT INTO `genre` VALUES (9, 'CRIME');
INSERT INTO `genre` VALUES (10, 'FANTASY');
INSERT INTO `genre` VALUES (11, 'GRAPHIC NOVEL');
INSERT INTO `genre` VALUES (12, 'HORROR');

INSERT INTO `bswapuser` VALUES ('Pijushm', 0, 'Rangpur', 'pijush.m@gmail.com',1, 'Pijush', 'Mohanto', '{noop}test123');

INSERT INTO `book` VALUES (1, 'Harper Lee', 2, 'Good', NULL, 0, 'To Kill a Mockingbid', 0, 1, 0, 22, 1, 'Pijushm');
INSERT INTO `book` VALUES (2, 'Sunil Gongopaddhay', 2, 'Good', NULL, 0, 'Prothom Alo', 0, 1, 0, 22, 1, 'Pijushm');
INSERT INTO `book` VALUES (3, 'Sunil Gongopaddhay', 2, 'Good', NULL, 0, 'Prothom Alo', 0, 1, 0, 22, 1, 'Pijushm');
INSERT INTO `book` VALUES (4, 'Sunil Gongopaddhay', 2, 'Good', NULL, 0, 'Prothom Alo', 0, 1, 0, 22, 1, 'Pijushm');
INSERT INTO `book` VALUES (5, 'Sunil Gongopaddhay', 2, 'Good', NULL, 0, 'Prothom Alo', 0, 1, 0, 22, 1, 'Pijushm');

INSERT INTO `genre_books` VALUES(1,6);
INSERT INTO `genre_books` VALUES(2,6);

INSERT INTO authorities VALUES (1,'ROLE_USER','Pijushm')

