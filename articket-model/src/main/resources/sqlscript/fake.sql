USE articket;

-- 清除所有表格數據
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE Board;
TRUNCATE TABLE CouponType;
TRUNCATE TABLE GeneralMember;
TRUNCATE TABLE PartnerMember;
TRUNCATE TABLE Administrator;
TRUNCATE TABLE Venue;
TRUNCATE TABLE VenueArea;
TRUNCATE TABLE Seat;
TRUNCATE TABLE VenueRental;
TRUNCATE TABLE VenueTimeSlot;
TRUNCATE TABLE Activity;
TRUNCATE TABLE ActivityTimeSlot;
TRUNCATE TABLE MemberCoupon;
TRUNCATE TABLE BookTicket;
TRUNCATE TABLE Ticket;
TRUNCATE TABLE SeatStatus;
TRUNCATE TABLE ActivityPicture;
TRUNCATE TABLE ActivityCollection;
TRUNCATE TABLE Article;
TRUNCATE TABLE Message;
TRUNCATE TABLE ArticleCollection;
TRUNCATE TABLE Heart;
TRUNCATE TABLE ArticleImg;
TRUNCATE TABLE Prosecute;
TRUNCATE TABLE Commodity;
TRUNCATE TABLE CommodityPicture;
TRUNCATE TABLE Orders;
TRUNCATE TABLE OrderItem;
TRUNCATE TABLE Cart;
TRUNCATE TABLE CartItem;
TRUNCATE TABLE News;
TRUNCATE TABLE Announcement;
TRUNCATE TABLE ActivityAreaPrice;
SET FOREIGN_KEY_CHECKS = 1;

-- 插入 Board 測試資料
INSERT INTO Board (boardName) VALUES
('K-pop Discussions'),
('Concert Reviews');

-- 插入 CouponType 測試資料
INSERT INTO CouponType (couponTypeName, couponTypeRegulation, couponTypeDiscount) VALUES
('10% Off', 'Valid for one purchase only', 10.00),
('20% Off', 'Valid for one purchase only', 20.00);

-- 插入 GeneralMember 測試資料
INSERT INTO GeneralMember (memberAccount, memberPassword, memberName, memberPhone, memberNickName, memberAddress, nationalID, gender, birthday, memberPicture, memberStatus) VALUES 
('john.doe@example.com', 'password123', 'John Doe', '0912345678', 'Johnny', '123 Main St, Cityville', 'A123456789', 'Male', '1985-01-01', NULL, 1),
('jane.smith@example.com', 'password456', 'Jane Smith', '0923456789', 'Janey', '456 Elm St, Townsville', 'B987654321', 'Female', '1990-02-02', NULL, 1),
('sam.wilson@example.com', 'password789', 'Sam Wilson', '0934567890', 'Sammy', '789 Oak St, Villageville', 'C135792468', 'Male', '1982-03-03', NULL, 1),
('lisa.brown@example.com', 'password101', 'Lisa Brown', '0945678901', 'Lissy', '101 Pine St, Hamletville', 'D864209753', 'Female', '1979-04-04', NULL, 1),
('mike.davis@example.com', 'password202', 'Mike Davis', '0956789012', 'Mikey', '202 Birch St, Boroughville', 'E246813579', 'Male', '1988-05-05', NULL, 1),
('emma.johnson@example.com', 'password303', 'Emma Johnson', '0967890123', 'Emmy', '303 Cedar St, Metropolis', 'F975310864', 'Female', '1992-06-06', NULL, 1),
('chris.lee@example.com', 'password404', 'Chris Lee', '0978901234', 'Chrissy', '404 Maple St, Capital', 'G182736455', 'Male', '1984-07-07', NULL, 1),
('anna.williams@example.com', 'password505', 'Anna Williams', '0989012345', 'Anny', '505 Spruce St, Urbanville', 'H546372819', 'Female', '1991-08-08', NULL, 1),
('david.miller@example.com', 'password606', 'David Miller', '0990123456', 'Davy', '606 Redwood St, Citytown', 'I719284635', 'Male', '1983-09-09', NULL, 1),
('olivia.jones@example.com', 'password707', 'Olivia Jones', '0901234567', 'Livvy', '707 Sequoia St, Towncity', 'J823746190', 'Female', '1987-10-10', NULL, 1);

-- 插入 PartnerMember 測試資料
INSERT INTO PartnerMember (taxID, partnerName, partnerHeading, partnerAddress, partnerPhone, partnerContactPerson, partnerPassword, partnerEmail, partnerAccountStatus) VALUES
('12345678', 'Starship Entertainment', 'Starship Ent.', '台北市信義區信義路五段7號', '02-3456-7890', '何宜倫', 'password123', 'contact@starship.com', 1),
('87654321', 'SM Entertainment', 'SM Ent.', '台北市大安區和平東路三段10號', '02-9876-5432', '楊凱盛', 'password123', 'contact@sm.com', 1),
('23456789', 'JYP Entertainment', 'JYP Ent.', '台北市松山區南京東路四段100號', '02-2345-6789', '高傳智', 'password123', 'contact@jyp.com', 1),
('98765432', 'YG Entertainment', 'YG Ent.', '台北市中正區忠孝西路一段12號', '02-8765-4321', '林俊逸', 'password123', 'contact@yg.com', 1),
('34567890', 'BigHit Entertainment', 'BigHit Ent.', '台北市中山區中山北路三段50號', '02-3456-7891', '陳奕迅', 'password123', 'contact@bighit.com', 1);

-- 插入 Administrator 測試資料
INSERT INTO Administrator (administratorAccount, administratorPassword, administratorStatus) VALUES
('admin', 'admin123', 1),
('manager', 'manager123', 1);

-- 插入 Venue 測試資料
INSERT INTO Venue (venueName, venuePhone, venueContactPerson, venueAddress, venueLocation) VALUES
('北館', '02-1234-5678', '何宜倫', '台北市松山區復興北路123號', '台北'),
('中館', '02-2345-6789', '楊凱盛', '台中市西屯區市政路456號', '台中'),
('南館', '02-2345-6789', '高傳智', '高雄市前鎮區中華五路789號', '高雄');

-- 插入 VenueArea 測試資料
INSERT INTO VenueArea (venueID, venueAreaName) VALUES
(1, '主舞台'),
(1, 'VIP 區域'),
(1, '普通區域'),
(2, '主舞台'),
(2, 'VIP 區域'),
(2, '普通區域'),
(3, '主舞台'),
(3, 'VIP 區域'),
(3, '普通區域');

-- 插入 Seat 測試資料
INSERT INTO Seat (venueID, venueAreaID, seatName, seatRow, seatNumber) VALUES
(1, 1, 'A1', 1, 1),
(1, 1, 'A2', 1, 2),
(1, 2, 'B1', 1, 1),
(1, 2, 'B2', 1, 2),
(1, 3, 'C1', 1, 1),
(1, 3, 'C2', 1, 2),
(2, 4, 'A1', 1, 1),
(2, 4, 'A2', 1, 2),
(2, 5, 'B1', 1, 1),
(2, 5, 'B2', 1, 2),
(2, 6, 'C1', 1, 1),
(2, 6, 'C2', 1, 2),
(3, 7, 'A1', 1, 1),
(3, 7, 'A2', 1, 2),
(3, 8, 'B1', 1, 1),
(3, 8, 'B2', 1, 2),
(3, 9, 'C1', 1, 1),
(3, 9, 'C2', 1, 2);

-- 插入 VenueRental 測試資料
INSERT INTO VenueRental (venueID, partnerID, activityName, proposal, venueRentalStatus, venueRentalStartDate, venueRentalEndDate, venueRentalCode) VALUES
(1, 1, 'IVE 演唱會', NULL, 1, '2024-09-01', '2024-09-05',CONCAT('G', LPAD(3001000 + 1, 7, '0'))),
(2, 2, '話劇表演 - 李爾王', NULL, 1, '2024-10-01', '2024-10-07',CONCAT('G', LPAD(3001000 + 2, 7, '0'))),
(3, 3, 'EXO 演唱會', NULL, 1, '2024-11-01', '2024-11-10',CONCAT('G', LPAD(3001000 + 3, 7, '0'))),
(1, 1, '台灣民俗舞蹈表演', NULL, 1, '2024-12-01', '2024-12-05',CONCAT('G', LPAD(3001000 + 4, 7, '0'))),
(2, 2, 'Twice 演唱會', NULL, 1, '2025-01-01', '2025-01-08',CONCAT('G', LPAD(3001000 + 5, 7, '0'))),
(3, 3, '交響音樂會', NULL, 1, '2025-02-01', '2025-02-10',CONCAT('G', LPAD(3001000 + 6, 7, '0'))),
(1, 1, '魔術表演秀', NULL, 1, '2025-03-01', '2025-03-04',CONCAT('G', LPAD(3001000 + 7, 7, '0'))),
(2, 2, 'Stray Kids 演唱會', NULL, 1, '2025-04-01', '2025-04-06',CONCAT('G', LPAD(3001000 + 8, 7, '0'))),
(3, 3, '台灣傳統戲劇表演', NULL, 1, '2025-05-01', '2025-05-07',CONCAT('G', LPAD(3001000 + 9, 7, '0'))),
(1, 1, 'GOT7 演唱會', NULL, 1, '2025-06-01', '2025-06-09',CONCAT('G', LPAD(3001000 + 10, 7, '0')));

-- 插入 VenueTimeSlot 測試資料
INSERT INTO VenueTimeSlot (venueRentalID, venueTimeSlotDate, venueTimeSlot, venueTimeSlotStatus) VALUES
(1, '2024-09-01', 1, 1),
(1, '2024-09-01', 2, 1),
(1, '2024-09-01', 3, 1),
(1, '2024-09-02', 1, 1),
(1, '2024-09-02', 2, 1),
(1, '2024-09-02', 3, 1),
(2, '2024-10-01', 1, 1),
(2, '2024-10-01', 2, 1),
(2, '2024-10-01', 3, 1),
(2, '2024-10-02', 1, 1),
(2, '2024-10-02', 2, 1),
(2, '2024-10-02', 3, 1),
(3, '2024-11-01', 1, 1),
(3, '2024-11-01', 2, 1),
(3, '2024-11-01', 3, 1),
(3, '2024-11-02', 1, 1),
(3, '2024-11-02', 2, 1),
(3, '2024-11-02', 3, 1),
(4, '2024-12-01', 1, 1),
(4, '2024-12-01', 2, 1),
(4, '2024-12-01', 3, 1),
(4, '2024-12-02', 1, 1),
(4, '2024-12-02', 2, 1),
(4, '2024-12-02', 3, 1),
(5, '2025-01-01', 1, 1),
(5, '2025-01-01', 2, 1),
(5, '2025-01-01', 3, 1),
(5, '2025-01-02', 1, 1),
(5, '2025-01-02', 2, 1),
(5, '2025-01-02', 3, 1),
(6, '2025-02-01', 1, 1),
(6, '2025-02-01', 2, 1),
(6, '2025-02-01', 3, 1),
(6, '2025-02-02', 1, 1),
(6, '2025-02-02', 2, 1),
(6, '2025-02-02', 3, 1),
(7, '2025-03-01', 1, 1),
(7, '2025-03-01', 2, 1),
(7, '2025-03-01', 3, 1),
(7, '2025-03-02', 1, 1),
(7, '2025-03-02', 2, 1),
(7, '2025-03-02', 3, 1),
(8, '2025-04-01', 1, 1),
(8, '2025-04-01', 2, 1),
(8, '2025-04-01', 3, 1),
(8, '2025-04-02', 1, 1),
(8, '2025-04-02', 2, 1),
(8, '2025-04-02', 3, 1),
(9, '2025-05-01', 1, 1),
(9, '2025-05-01', 2, 1),
(9, '2025-05-01', 3, 1),
(9, '2025-05-02', 1, 1),
(9, '2025-05-02', 2, 1),
(9, '2025-05-02', 3, 1),
(10, '2025-06-01', 1, 1),
(10, '2025-06-01', 2, 1),
(10, '2025-06-01', 3, 1),
(10, '2025-06-02', 1, 1),
(10, '2025-06-02', 2, 1),
(10, '2025-06-02', 3, 1);

-- 插入 Activity 測試資料
INSERT INTO Activity (partnerID, venueID, venueRentalID, activityName, activityContent, activityPostTime, activityTag, activityStatus, ticketSetStatus, sellTime) VALUES
(1, 1, 1, 'IVE Live Concert', 'Join IVE for an unforgettable live concert.', '2024-07-01', 'K-pop', 1, 1, '2024-07-15'),
(2, 2, 2, 'SM Town Concert', 'Experience the best of SM Town.', '2024-08-01', 'K-pop', 1, 1, '2024-08-15');

-- 插入 ActivityTimeSlot 測試資料
INSERT INTO ActivityTimeSlot (activityID, activityTimeSlotDate, activityTimeSlot, activityTimeSlotSeatAmount) VALUES
(1, '2024-09-01', 1, 100),
(1, '2024-09-01', 2, 100),
(1, '2024-09-02', 1, 100),
(1, '2024-09-02', 2, 100),
(2, '2024-10-01', 1, 100),
(2, '2024-10-01', 2, 100),
(2, '2024-10-02', 1, 100),
(2, '2024-10-02', 2, 100);

-- 插入 MemberCoupon 測試資料
INSERT INTO MemberCoupon (memberID, couponTypeID, memberCouponExpirationDate, memberCouponStatus) VALUES
(1, 1, '2024-12-31', 0),
(2, 2, '2024-12-31', 0),
(3, 1, '2024-12-31', 0),
(4, 2, '2024-12-31', 0),
(5, 1, '2024-12-31', 0),
(6, 2, '2024-12-31', 0);

-- 插入 BookTicket 測試資料
INSERT INTO BookTicket (memberID, activityID, activityTimeSlotID, memberCouponID, ticketQuantity, totalPrice) VALUES
(1, 1, 1, 1, 2, 200.00),
(2, 1, 2, 2, 1, 100.00),
(3, 2, 3, 3, 2, 300.00),
(4, 2, 4, 4, 1, 150.00);

-- 插入 SeatStatus 測試資料
INSERT INTO SeatStatus (activityTimeSlotID, seatID, seatStatus) VALUES
(1, 1, 0),
(1, 2, 0),
(2, 3, 0),
(2, 4, 0),
(3, 5, 0),
(3, 6, 0),
(4, 7, 0),
(4, 8, 0);

-- 插入 ActivityAreaPrice 測試資料
INSERT INTO ActivityAreaPrice (venueAreaID, activityID, activityAreaPrice) VALUES
(1, 1, 15000.00),
(2, 1, 15000.00),
(3, 1, 2000.00),
(4, 2, 1200.00),
(5, 2, 1800.00),
(6, 2, 2500.00);

-- 插入 Ticket 測試資料
INSERT INTO Ticket (memberID, seatStatusID, activityAreaPriceID, bookTicketID, activityTimeSlotID) VALUES
(1, 1, 1, 1, 1),
(2, 2, 2, 2, 2),
(3, 3, 3, 3, 3),
(4, 4, 4, 4, 4);

-- 插入 ActivityPicture 測試資料
INSERT INTO ActivityPicture (activityID, activityPicture) VALUES
(1, NULL),
(2, NULL);

-- 插入 ActivityCollection 測試資料
INSERT INTO ActivityCollection (memberID, activityID) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2);

-- 插入 Article 測試資料
INSERT INTO Article (articleCategory, articleTitle, memberID, articleContent, boardID, articleStatus) VALUES
('K-pop', 'IVE Concert Experience', 1, 'The concert was amazing!', 1, 1),
('K-pop', 'IVE Fan Meeting', 2, 'I met IVE members!', 1, 1),
('K-pop', 'SM Town Concert Review', 3, 'The SM Town concert was fantastic!', 1, 1),
('K-pop', 'New K-pop Group Debut', 4, 'Check out this new K-pop group!', 1, 1);

-- 插入 Message 測試資料
INSERT INTO Message (memberID, articleID, messageContent, messageStatus) VALUES
(1, 1, 'I agree, it was fantastic!', 1),
(2, 2, 'I wish I could have been there!', 1),
(3, 3, 'Great review!', 1),
(4, 4, 'I am excited to see them!', 1);

-- 插入 ArticleCollection 測試資料
INSERT INTO ArticleCollection (memberID, articleID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- 插入 Heart 測試資料
INSERT INTO Heart (memberID, articleID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- 插入 ArticleImg 測試資料
INSERT INTO ArticleImg (articleID, articlePic) VALUES
(1, NULL),
(2, NULL),
(3, NULL),
(4, NULL);

-- 插入 Prosecute 測試資料
INSERT INTO Prosecute (memberID, articleID, prosecuteReason, prosecuteCreateTime, prosecuteStatus) VALUES
(1, 2, 'Inappropriate content', '2024-08-01', 0),
(2, 3, 'Spam', '2024-08-02', 0);

-- 插入 Commodity 測試資料
INSERT INTO Commodity (commodityName, commodityPrice, commodityStock, commodityContent, activityID, partnerID, commodityStatus, commodityPostTime) VALUES
('IVE T-shirt', 30.00, 500, 'Official IVE concert T-shirt', 1, 1, 1, '2024-07-01'),
('IVE Poster', 15.00, 300, 'Official IVE concert poster', 1, 1, 1, '2024-07-01'),
('SM Town T-shirt', 35.00, 400, 'Official SM Town concert T-shirt', 2, 2, 1, '2024-08-01'),
('SM Town Poster', 20.00, 200, 'Official SM Town concert poster', 2, 2, 1, '2024-08-01');

-- 插入 CommodityPicture 測試資料
INSERT INTO CommodityPicture (commodityID, commodityPicture) VALUES
(1, NULL),
(2, NULL),
(3, NULL),
(4, NULL);

-- 插入 Orders 測試資料
INSERT INTO Orders (memberID, recipient, recipientPhone, recipientEmail, recipientAddress, actualAmount, orderStatus, payStatus) VALUES
(1, 'An Yujin', '010-1234-5678', 'yujin@example.com', 'Seoul, South Korea', 60.00, 1, 1),
(2, 'Jang Wonyoung', '010-2345-6789', 'wonyoung@example.com', 'Seoul, South Korea', 45.00, 1, 1),
(3, 'Kim Ji Won', '010-3456-7890', 'liz@example.com', 'Seoul, South Korea', 70.00, 1, 1),
(4, 'Naoi Rei', '010-4567-8901', 'rei@example.com', 'Tokyo, Japan', 55.00, 1, 1);

-- 插入 OrderItem 測試資料
INSERT INTO OrderItem (orderID, commodityID, commodityOrderPrice, orderItemQuantity, orderItemTotalPrice) VALUES
(1, 1, 30.00, 1, 30.00),
(1, 2, 15.00, 2, 30.00),
(2, 2, 15.00, 3, 45.00),
(3, 3, 35.00, 2, 70.00),
(4, 4, 20.00, 1, 20.00);

-- 插入 Cart 測試資料
INSERT INTO Cart (memberID, cartTotalPrice) VALUES
(1, 60.00),
(2, 45.00),
(3, 70.00),
(4, 55.00);

-- 插入 CartItem 測試資料
INSERT INTO CartItem (cartID, commodityID, checkedQuantity) VALUES
(1, 1, 1),
(1, 2, 2),
(2, 2, 3),
(3, 3, 2),
(4, 4, 1);

-- 插入 News 測試資料
INSERT INTO News (administratorID, newsTitle, newsContent, newsStatus) VALUES
(1, 'IVE Concert Announcement', 'We are excited to announce the IVE live concert in September!', 1),
(2, 'SM Town Concert Announcement', 'Don\'t miss the SM Town concert in October!', 1);

-- 插入 Announcement 測試資料
INSERT INTO Announcement (administratorID, announcementTitle, announcementContent, announcementStatus) VALUES
(1, 'IVE Concert Tickets', 'Tickets for the IVE concert will go on sale soon. Stay tuned!', 1),
(2, 'SM Town Concert Tickets', 'Tickets for the SM Town concert will go on sale soon. Stay tuned!', 1);