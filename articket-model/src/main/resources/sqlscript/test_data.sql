USE articket;

-- 清除所有表格數據
-- SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE board;
TRUNCATE TABLE coupon_type;
TRUNCATE TABLE general_member;
TRUNCATE TABLE partner_member;
TRUNCATE TABLE administrator;
TRUNCATE TABLE venue;
TRUNCATE TABLE venue_area;
TRUNCATE TABLE seat;
TRUNCATE TABLE venue_rental;
TRUNCATE TABLE venue_time_slot;
TRUNCATE TABLE activity;
TRUNCATE TABLE activity_time_slot;
TRUNCATE TABLE member_coupon;
TRUNCATE TABLE book_ticket;
TRUNCATE TABLE ticket;
TRUNCATE TABLE seat_status;
TRUNCATE TABLE activity_picture;
TRUNCATE TABLE activity_collection;
TRUNCATE TABLE article;
TRUNCATE TABLE message;
TRUNCATE TABLE article_collection;
TRUNCATE TABLE heart;
TRUNCATE TABLE article_img;
TRUNCATE TABLE prosecute;
TRUNCATE TABLE commodity;
TRUNCATE TABLE commodity_picture;
TRUNCATE TABLE orders;
TRUNCATE TABLE order_item;
TRUNCATE TABLE cart;
TRUNCATE TABLE cart_item;
TRUNCATE TABLE news;
TRUNCATE TABLE announcement;
TRUNCATE TABLE activity_area_price;
-- SET FOREIGN_KEY_CHECKS = 1;

-- 插入 board 測試資料
INSERT INTO board (board_name) VALUES
('K-pop Discussions'),
('Concert Reviews');

-- 插入 coupon_type 測試資料
INSERT INTO coupon_type (coupon_type_name, coupon_type_regulation, coupon_type_discount) VALUES
('10% Off', 'Valid for one purchase only', 10.00),
('20% Off', 'Valid for one purchase only', 20.00);

-- 插入 general_member 測試資料
INSERT INTO general_member (member_account, member_password, member_name, member_phone, member_nickname, member_address, national_id, gender, birthday, member_picture, member_status) VALUES
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

-- 插入 partner_member 測試資料
INSERT INTO partner_member (tax_id, partner_name, partner_heading, partner_address, partner_phone, partner_contact_person, partner_password, partner_email, partner_account_status) VALUES
('12345678', 'Starship Entertainment', 'Starship Ent.', '台北市信義區信義路五段7號', '02-3456-7890', '何宜倫', 'password123', 'contact@starship.com', 1),
('87654321', 'SM Entertainment', 'SM Ent.', '台北市大安區和平東路三段10號', '02-9876-5432', '楊凱盛', 'password123', 'contact@sm.com', 1),
('23456789', 'JYP Entertainment', 'JYP Ent.', '台北市松山區南京東路四段100號', '02-2345-6789', '高傳智', 'password123', 'contact@jyp.com', 1),
('98765432', 'YG Entertainment', 'YG Ent.', '台北市中正區忠孝西路一段12號', '02-8765-4321', '林俊逸', 'password123', 'contact@yg.com', 1),
('34567890', 'BigHit Entertainment', 'BigHit Ent.', '台北市中山區中山北路三段50號', '02-3456-7891', '陳奕迅', 'password123', 'contact@bighit.com', 1);

-- 插入 administrator 測試資料
INSERT INTO administrator (administrator_account, administrator_password, administrator_status) VALUES
('admin', 'admin123', 1),
('manager', 'manager123', 1);

-- 插入 venue 測試資料
INSERT INTO venue (venue_name, venue_phone, venue_contact_person, venue_address, venue_location) VALUES
('北館', '02-1234-5678', '何宜倫', '台北市松山區復興北路123號', '台北'),
('中館', '02-2345-6789', '楊凱盛', '台中市西屯區市政路456號', '台中'),
('南館', '02-2345-6789', '高傳智', '高雄市前鎮區中華五路789號', '高雄');

-- 插入 venue_area 測試資料
INSERT INTO venue_area (venue_id, venue_area_name) VALUES
(1, '主舞台'),
(1, 'VIP 區域'),
(1, '普通區域'),
(2, '主舞台'),
(2, 'VIP 區域'),
(2, '普通區域'),
(3, '主舞台'),
(3, 'VIP 區域'),
(3, '普通區域');

-- 插入 seat 測試資料
INSERT INTO seat (venue_id, venue_area_id, seat_name, seat_row, seat_number) VALUES
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

-- 插入 venue_rental 測試資料
INSERT INTO venue_rental (venue_id, partner_id, activity_name, proposal, venue_rental_status, venue_rental_start_date, venue_rental_end_date, venue_rental_code) VALUES
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

-- 插入 venue_time_slot 測試資料
INSERT INTO venue_time_slot (venue_rental_id, venue_time_slot_date, venue_time_slot, venue_time_slot_status) VALUES
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

-- 插入 activity 測試資料
INSERT INTO activity (partner_id, venue_id, venue_rental_id, activity_name, activity_content, activity_post_time, activity_tag, activity_status, ticket_set_status, sell_time) VALUES
(1, 1, 1, 'IVE Live Concert', 'Join IVE for an unforgettable live concert.', '2024-07-01', 'K-pop', 1, 1, '2024-07-15'),
(2, 2, 2, 'SM Town Concert', 'Experience the best of SM Town.', '2024-08-01', 'K-pop', 1, 1, '2024-08-15');

-- 插入 activity_time_slot 測試資料
INSERT INTO activity_time_slot (activity_id, activity_time_slot_date, activity_time_slot, activity_time_slot_seat_amount) VALUES
(1, '2024-09-01', 1, 100),
(1, '2024-09-01', 2, 100),
(1, '2024-09-02', 1, 100),
(1, '2024-09-02', 2, 100),
(2, '2024-10-01', 1, 100),
(2, '2024-10-01', 2, 100),
(2, '2024-10-02', 1, 100),
(2, '2024-10-02', 2, 100);

-- 插入 member_coupon 測試資料
INSERT INTO member_coupon (member_id, coupon_type_id, member_coupon_expiration_date, member_coupon_status) VALUES
(1, 1, '2024-12-31', 0),
(2, 2, '2024-12-31', 0),
(3, 1, '2024-12-31', 0),
(4, 2, '2024-12-31', 0),
(5, 1, '2024-12-31', 0),
(6, 2, '2024-12-31', 0);

-- 插入 book_ticket 測試資料
INSERT INTO book_ticket (member_id, activity_id, activity_time_slot_id, member_coupon_id, ticket_quantity, total_price) VALUES
(1, 1, 1, 1, 2, 200.00),
(2, 1, 2, 2, 1, 100.00),
(3, 2, 3, 3, 2, 300.00),
(4, 2, 4, 4, 1, 150.00);

-- 插入 seat_status 測試資料
INSERT INTO seat_status (activity_time_slot_id, seat_id, seat_status) VALUES
(1, 1, 0),
(1, 2, 0),
(2, 3, 0),
(2, 4, 0),
(3, 5, 0),
(3, 6, 0),
(4, 7, 0),
(4, 8, 0);

-- 插入 activity_area_price 測試資料
INSERT INTO activity_area_price (venue_area_id, activity_id, activity_area_price) VALUES
(1, 1, 15000.00),
(2, 1, 15000.00),
(3, 1, 2000.00),
(4, 2, 1200.00),
(5, 2, 1800.00),
(6, 2, 2500.00);

-- 插入 ticket 測試資料
INSERT INTO ticket (member_id, seat_status_id, activity_area_price_id, book_ticket_id, activity_time_slot_id) VALUES
(1, 1, 1, 1, 1),
(2, 2, 2, 2, 2),
(3, 3, 3, 3, 3),
(4, 4, 4, 4, 4);

-- 插入 activity_picture 測試資料
INSERT INTO activity_picture (activity_id, activity_picture) VALUES
(1, NULL),
(2, NULL);

-- 插入 activity_collection 測試資料
INSERT INTO activity_collection (member_id, activity_id) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2);

-- 插入 article 測試資料
INSERT INTO article (article_category, article_title, member_id, article_content, board_id, article_status) VALUES
('K-pop', 'IVE Concert Experience', 1, 'The concert was amazing!', 1, 1),
('K-pop', 'IVE Fan Meeting', 2, 'I met IVE members!', 1, 1),
('K-pop', 'SM Town Concert Review', 3, 'The SM Town concert was fantastic!', 1, 1),
('K-pop', 'New K-pop Group Debut', 4, 'Check out this new K-pop group!', 1, 1);

-- 插入 message 測試資料
INSERT INTO message (member_id, article_id, message_content, message_status) VALUES
(1, 1, 'I agree, it was fantastic!', 1),
(2, 2, 'I wish I could have been there!', 1),
(3, 3, 'Great review!', 1),
(4, 4, 'I am excited to see them!', 1);

-- 插入 article_collection 測試資料
INSERT INTO article_collection (member_id, article_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- 插入 heart 測試資料
INSERT INTO heart (member_id, article_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- 插入 article_img 測試資料
INSERT INTO article_img (article_id, article_pic) VALUES
(1, NULL),
(2, NULL),
(3, NULL),
(4, NULL);

-- 插入 prosecute 測試資料
INSERT INTO prosecute (member_id, article_id, prosecute_reason, prosecute_create_time, prosecute_status) VALUES
(1, 2, 'Inappropriate content', '2024-08-01', 0),
(2, 3, 'Spam', '2024-08-02', 0);

-- 插入 commodity 測試資料
INSERT INTO commodity (commodity_name, commodity_price, commodity_stock, commodity_content, activity_id, partner_id, commodity_status, commodity_post_time) VALUES
('IVE T-shirt', 30.00, 500, 'Official IVE concert T-shirt', 1, 1, 1, '2024-07-01'),
('IVE Poster', 15.00, 300, 'Official IVE concert poster', 1, 1, 1, '2024-07-01'),
('SM Town T-shirt', 35.00, 400, 'Official SM Town concert T-shirt', 2, 2, 1, '2024-08-01'),
('SM Town Poster', 20.00, 200, 'Official SM Town concert poster', 2, 2, 1, '2024-08-01');

-- 插入 commodity_picture 測試資料
INSERT INTO commodity_picture (commodity_id, commodity_picture) VALUES
(1, NULL),
(2, NULL),
(3, NULL),
(4, NULL);

-- 插入 orders 測試資料
INSERT INTO orders (member_id, recipient, recipient_phone, recipient_email, recipient_address, actual_amount, order_status, pay_status) VALUES
(1, 'An Yujin', '010-1234-5678', 'yujin@example.com', 'Seoul, South Korea', 60.00, 1, 1),
(2, 'Jang Wonyoung', '010-2345-6789', 'wonyoung@example.com', 'Seoul, South Korea', 45.00, 1, 1),
(3, 'Kim Ji Won', '010-3456-7890', 'liz@example.com', 'Seoul, South Korea', 70.00, 1, 1),
(4, 'Naoi Rei', '010-4567-8901', 'rei@example.com', 'Tokyo, Japan', 55.00, 1, 1);

-- 插入 order_item 測試資料
INSERT INTO order_item (order_id, commodity_id, commodity_order_price, order_item_quantity, order_item_total_price) VALUES
(1, 1, 30.00, 1, 30.00),
(1, 2, 15.00, 2, 30.00),
(2, 2, 15.00, 3, 45.00),
(3, 3, 35.00, 2, 70.00),
(4, 4, 20.00, 1, 20.00);

-- 插入 cart 測試資料
INSERT INTO cart (member_id, cart_total_price) VALUES
(1, 60.00),
(2, 45.00),
(3, 70.00),
(4, 55.00);

-- 插入 cart_item 測試資料
INSERT INTO cart_item (cart_id, commodity_id, checked_quantity) VALUES
(1, 1, 1),
(1, 2, 2),
(2, 2, 3),
(3, 3, 2),
(4, 4, 1);

-- 插入 news 測試資料
INSERT INTO news (administrator_id, news_title, news_content, news_status) VALUES
(1, 'IVE Concert Announcement', 'We are excited to announce the IVE live concert in September!', 1),
(2, 'SM Town Concert Announcement', 'Don\'t miss the SM Town concert in October!', 1);

-- 插入 announcement 測試資料
INSERT INTO announcement (administrator_id, announcement_title, announcement_content, announcement_status) VALUES
(1, 'IVE Concert Tickets', 'Tickets for the IVE concert will go on sale soon. Stay tuned!', 1),
(2, 'SM Town Concert Tickets', 'Tickets for the SM Town concert will go on sale soon. Stay tuned!', 1);