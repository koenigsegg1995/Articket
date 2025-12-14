-- //////////////////////////////////// DATABASE 設定 ///////////////////////////////////////////
-- ! 移除 DATABASE --------------------------------------------------------
DROP DATABASE IF EXISTS articket;
-- ! 移除 DATABASE --------------------------------------------------------

-- 建立 DATABASE articket ------------------------------------------------
CREATE DATABASE articket
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE articket;
-- 建立 DATABASE articket ------------------------------------------------

-- //////////////////////////////////// DATABASE 設定 ///////////////////////////////////////////


-- //////////////////////////////////// 建立表格 ////////////////////////////////////////////////
-- 會員相關 ---------------------------------------------------------------
CREATE TABLE general_member(
	member_id                       INT           AUTO_INCREMENT    COMMENT "會員ID",
    member_account                  VARCHAR(100)  NOT NULL          COMMENT "帳號(E-mail)",
    member_password                 VARCHAR(255)  NOT NULL          COMMENT "密碼",
    member_name                     VARCHAR(100)  NOT NULL          COMMENT "姓名",
    member_phone                    VARCHAR(20)   NOT NULL           COMMENT "電話",
    member_nickname                 VARCHAR(20)   NOT NULL           COMMENT "暱稱",
    member_address                  VARCHAR(255)                    COMMENT "地址",
    national_id                     VARCHAR(10)   NOT NULL          COMMENT "身分證字號",
    gender                          VARCHAR(10)   NOT NULL           COMMENT "性別",
    birthday                        DATE                            COMMENT "生日",
    member_picture                  MEDIUMBLOB                      COMMENT "大頭照",
    member_status                   INT           NOT NULL          COMMENT "帳號狀態 0:帳號已黑單 1:帳號正常",
    member_create_time               DATETIME
		                                          DEFAULT CURRENT_TIMESTAMP
		                                                            COMMENT "帳號建立時間",
    
    CONSTRAINT pk_general_member PRIMARY KEY (member_id)
) COMMENT "一般會員";

CREATE TABLE partner_member(
	partner_id                      INT           AUTO_INCREMENT    COMMENT "廠商ID",
    tax_id                          VARCHAR(8)    NOT NULL          COMMENT "統一編號",
    partner_name                    VARCHAR(100)  NOT NULL          COMMENT "公司名稱",
    partner_heading                 VARCHAR(100)  NOT NULL          COMMENT "抬頭",
    partner_address                 VARCHAR(255)  NOT NULL          COMMENT "地址",
    partner_phone                   VARCHAR(20)   NOT NULL          COMMENT "連絡電話",
    partner_contact_person          VARCHAR(100)  NOT NULL          COMMENT "聯絡人",
    partner_password                VARCHAR(255)  NOT NULL          COMMENT "密碼",
    partner_email                   VARCHAR(100)  NOT NULL          COMMENT "電子信箱",
    partner_create_time             DATETIME
		                                          DEFAULT CURRENT_TIMESTAMP
							                                        COMMENT "帳號建立時間",
    partner_account_status          INT           NOT NULL          COMMENT "帳號狀態 0:黑名單 1.使用中 2.申請中",
    
    CONSTRAINT pk_partner_member PRIMARY KEY (partner_id)
) COMMENT "廠商會員";

CREATE TABLE administrator(
	administrator_id                INT           AUTO_INCREMENT    COMMENT "管理員ID",
    administrator_account           VARCHAR(100)  NOT NULL          COMMENT "帳號",
    administrator_password          VARCHAR(255)  NOT NULL          COMMENT "密碼",
    administrator_create_time       DATETIME
								                  DEFAULT CURRENT_TIMESTAMP
								                                    COMMENT "帳號建立時間",
    administrator_status            INT           NOT NULL          COMMENT "帳號狀態 0:帳號正常 1:帳號停用",
    
    CONSTRAINT pk_administrator PRIMARY KEY (administrator_id)
) COMMENT "管理員";

CREATE TABLE member_coupon(
	member_coupon_id                INT           AUTO_INCREMENT    COMMENT "會員優惠券ID",
	member_id                       INT           NOT NULL          COMMENT "會員ID",
	coupon_type_id                  INT           NOT NULL          COMMENT "優惠券類型ID",
    member_coupon_expiration_date   DATE          NOT NULL          COMMENT "有效期限",
    member_coupon_status            INT           NOT NULL          COMMENT "使用狀態 0:未使用 1:已使用",
    member_coupon_create_time       DATETIME
		                                          DEFAULT CURRENT_TIMESTAMP
		                                                            COMMENT "建立時間",

    CONSTRAINT pk_member_coupon PRIMARY KEY (member_coupon_id)
) COMMENT "會員優惠券";
-- 會員相關 ---------------------------------------------------------------

-- 場館相關 ---------------------------------------------------------------
CREATE TABLE venue(
	venue_id                        INT           AUTO_INCREMENT    COMMENT "場館ID",
	venue_name                      VARCHAR(255)  NOT NULL          COMMENT "場館名稱",
	venue_phone                     VARCHAR(20)   NOT NULL          COMMENT "電話",
	venue_contact_person            VARCHAR(100)  NOT NULL          COMMENT "聯絡人",
	venue_address                   VARCHAR(255)  NOT NULL          COMMENT "地址",
	venue_location                  VARCHAR(10)   NOT NULL          COMMENT "地區 如北區、中區、南區",

	CONSTRAINT pk_venue_id PRIMARY KEY (venue_id)
) COMMENT "場館";

CREATE TABLE venue_area(
	venue_area_id                   INT           AUTO_INCREMENT    COMMENT "場館區域ID",
	venue_id                        INT           NOT NULL          COMMENT "場館ID",
	venue_area_name                 VARCHAR(10)   NOT NULL          COMMENT "區域代號 如NA, NB, NC, MA, MB, MC, SA, SB, SC",
	
    CONSTRAINT pk_area_id PRIMARY KEY (venue_area_id)
) COMMENT "場館區域";

CREATE TABLE seat(
	seat_id                         INT           AUTO_INCREMENT     COMMENT "座位ID",
    venue_id                        INT           NOT NULL           COMMENT "場館ID",
	venue_area_id                   INT           NOT NULL           COMMENT "場館區域ID",
	seat_name                       VARCHAR(10)   NOT NULL           COMMENT "座位代號 如NA001, NA002, NA003, NA004",
	seat_row                        INT           NOT NULL           COMMENT "行",
	seat_number                     INT           NOT NULL           COMMENT "號",
    
	CONSTRAINT pk_seat_id PRIMARY KEY (seat_id)
) COMMENT "座位";

CREATE TABLE venue_rental(
	venue_rental_id                  INT          AUTO_INCREMENT     COMMENT "場地申請資料ID",
	venue_id                         INT          NOT NULL           COMMENT "場館ID",
	partner_id                       INT          NOT NULL           COMMENT "廠商會員ID",
	activity_name                    VARCHAR(255) NOT NULL           COMMENT "活動名稱",
	proposal                         LONGBLOB                        COMMENT "企劃書",
	venue_rental_status              INT          NOT NULL           COMMENT "申請狀態 0:不通過 1:通過 3:審核中 4:取消中 5:已取消",
	venue_rental_start_date          DATE         NOT NULL           COMMENT "租用開始日期",
	venue_rental_end_date            DATE         NOT NULL           COMMENT "租用結束日期",
	venue_rental_create_time         DATETIME
		                                          DEFAULT CURRENT_TIMESTAMP
		                                                             COMMENT "申請建立時間",
	venue_rental_code                VARCHAR(255) NOT NULL           COMMENT "場地申請編號",
    
	CONSTRAINT pk_venue_rental_id PRIMARY KEY (venue_rental_id)
) COMMENT "場地申請資料";

CREATE TABLE venue_time_slot(
	venue_time_slot_id               INT          AUTO_INCREMENT     COMMENT "場館時段ID",
	venue_rental_id                  INT                             COMMENT "場地申請資料ID",
	venue_time_slot_date             DATE         NOT NULL           COMMENT "日期",
	venue_time_slot                  INT          NOT NULL           COMMENT "時段 1:早 2:午 3:晚",
	venue_time_slot_status           INT          NOT NULL           COMMENT "時段狀態 0: 未被租借 1: 已被租借 2: 不提供租借 3: 活動",
    
	CONSTRAINT pk_venue_time_slot_id PRIMARY KEY (venue_time_slot_id)
) COMMENT "場館時段";

CREATE TABLE seat_status(
	seat_status_id                   INT          AUTO_INCREMENT     COMMENT "座位狀態ID",
	activity_time_slot_id            INT          NOT NULL           COMMENT "活動時段ID",
	seat_id                          INT          NOT NULL           COMMENT "座位ID",
	seat_status                      INT          NOT NULL           COMMENT "座位狀態 0: 空位 1: 已經購買 2: 廠商保留位 3: 不可使用(目前沒有用到這個資料，考慮未來可能會加入這個狀態)",
    
	CONSTRAINT pk_seat_status_id PRIMARY KEY (seat_status_id)
) COMMENT "座位狀態";

CREATE TABLE activity_area_price(
	activity_area_price_id           INT          AUTO_INCREMENT     COMMENT "活動區域價格ID",
	venue_area_id                    INT          NOT NULL           COMMENT "區域ID",
	activity_id                      INT          NOT NULL           COMMENT "活動ID",
	activity_area_price              DECIMAL(7,2) NOT NULL           COMMENT "活動區域價格",

	CONSTRAINT pk_activity_area_price_id PRIMARY KEY (activity_area_price_id)
) COMMENT "活動區域價格";
-- 場館相關 ---------------------------------------------------------------

-- 活動相關 ---------------------------------------------------------------
CREATE TABLE activity(
	activity_id                      INT          AUTO_INCREMENT     COMMENT "活動ID",
    partner_id                       INT          NOT NULL           COMMENT "廠商ID",
    venue_id                         INT          NOT NULL           COMMENT "場館ID",
    venue_rental_id                  INT          NOT NULL           COMMENT "申請資料ID",
    activity_name                    VARCHAR(255) NOT NULL           COMMENT "名稱",
    activity_content                 TEXT         NOT NULL           COMMENT "內容",
    activity_create_time             DATETIME
		                                          DEFAULT CURRENT_TIMESTAMP
                                                  ON UPDATE CURRENT_TIMESTAMP
								                                     COMMENT "建立時間",
    activity_post_time               DATE                            COMMENT "排程時間",
    activity_tag                     VARCHAR(255) NOT NULL           COMMENT "類型標籤",
    activity_status                  INT          NOT NULL           COMMENT "活動設定狀態 0:未設定 1:已設定",
    ticket_set_status                INT          NOT NULL           COMMENT "票券設定狀態 0:未設定 1:已設定",
    sell_time                        DATE                            COMMENT "起售日",
    
    CONSTRAINT pk_activity PRIMARY KEY (activity_id)
) COMMENT "活動";

CREATE TABLE activity_picture(
	activity_picture_id              INT          AUTO_INCREMENT     COMMENT "活動圖片",
    activity_id                      INT          NOT NULL           COMMENT "活動ID",
    activity_picture                 MEDIUMBLOB                      COMMENT "活動圖片",
    
    CONSTRAINT pk_activity_picture PRIMARY KEY (activity_picture_id)
) COMMENT "活動圖片";

CREATE TABLE activity_collection(
	activity_collection_id           INT          AUTO_INCREMENT     COMMENT "活動收藏ID",
    member_id                        INT          NOT NULL           COMMENT "會員ID",
    activity_id                      INT          NOT NULL           COMMENT "活動ID",
    activity_collection_time         DATETIME
		                                          DEFAULT CURRENT_TIMESTAMP
											      ON UPDATE CURRENT_TIMESTAMP
		                                                             COMMENT "活動收藏時間",
        
	CONSTRAINT pk_activity_collection PRIMARY KEY (activity_collection_id)
) COMMENT "活動收藏";

CREATE TABLE activity_time_slot(
	activity_time_slot_id            INT          AUTO_INCREMENT     COMMENT "時段ID",
    activity_id                      INT          NOT NULL           COMMENT "活動ID",
    activity_time_slot_date          DATE         NOT NULL           COMMENT "日期",
    activity_time_slot               INT          NOT NULL           COMMENT "時段 1:早 2:午 3:晚",
    activity_time_slot_seat_amount   INT          NOT NULL           COMMENT "時段剩餘座位數",
    
    CONSTRAINT pk_activity_time_slot PRIMARY KEY (activity_time_slot_id)
) COMMENT "活動時段";
-- 活動相關 ---------------------------------------------------------------

-- 票券相關 ---------------------------------------------------------------
CREATE TABLE book_ticket(
	book_ticket_id                   INT          AUTO_INCREMENT     COMMENT "票券訂單ID",
    member_id                        INT          NOT NULL           COMMENT "會員ID(買家)",
    activity_id                      INT          NOT NULL           COMMENT "活動ID",
    activity_time_slot_id            INT          NOT NULL           COMMENT "時段ID",
    member_coupon_id                 INT                             COMMENT "會員優惠券ID",
    book_time                        DATETIME
                                                  DEFAULT CURRENT_TIMESTAMP
				  		                          ON UPDATE CURRENT_TIMESTAMP
									                                 COMMENT "訂購日期",
    ticket_quantity                  INT          NOT NULL           COMMENT "數量",
    total_price                      DECIMAL(7,2) NOT NULL           COMMENT "總金額",
    
    CONSTRAINT pk_book_ticket PRIMARY KEY (book_ticket_id)
) COMMENT "票券訂單";

CREATE TABLE ticket(
	ticket_id                        INT          AUTO_INCREMENT     COMMENT "票券ID",
    member_id                        INT          NOT NULL           COMMENT "會員(擁有者)",
    seat_status_id                   INT          NOT NULL           COMMENT "座位狀態ID",
    activity_area_price_id           INT          NOT NULL           COMMENT "活動區域價格ID",
    book_ticket_id                   INT          NOT NULL           COMMENT "票券訂單ID",
    activity_time_slot_id            INT          NOT NULL           COMMENT "時段ID",
    
    CONSTRAINT pk_ticket PRIMARY KEY (ticket_id)
) COMMENT "票券";

CREATE TABLE coupon_type(
	coupon_type_id                   INT          AUTO_INCREMENT     COMMENT "優惠券類型ID",
    coupon_type_name                 VARCHAR(255) NOT NULL           COMMENT "優惠券類型名稱",
    coupon_type_regulation           TEXT         NOT NULL           COMMENT "使用規則",
    coupon_type_discount             DECIMAL(7,2) NOT NULL           COMMENT "折扣數",
    
    CONSTRAINT pk_coupon_type PRIMARY KEY (coupon_type_id)
) COMMENT "優惠券";
-- 票券相關 ---------------------------------------------------------------

-- 社群相關 ---------------------------------------------------------------
CREATE TABLE article (
	article_id	                     INT          AUTO_INCREMENT     COMMENT "文章ID",
    article_category                 VARCHAR(100) NOT NULL           COMMENT "文章類別",
	article_title	                 VARCHAR(100) NOT NULL           COMMENT "文章標題",
	member_id  		                 INT          NOT NULL           COMMENT "會員ID",
    article_content                  TEXT         NOT NULL           COMMENT "文章內容",
    board_id                         INT          NOT NULL           COMMENT "各板ID",
    article_status                   INT          NOT NULL           COMMENT "文章狀態 0.不顯示 1.顯示",
	article_create_time              DATETIME
					                              DEFAULT CURRENT_TIMESTAMP
					                              ON UPDATE CURRENT_TIMESTAMP
					                                                 COMMENT "文章建立時間",
                          
	CONSTRAINT pk_article PRIMARY KEY (article_id)
) COMMENT "文章";

CREATE TABLE message (
	message_id	                    INT           AUTO_INCREMENT     COMMENT "留言ID",
	member_id	                    INT           NOT NULL           COMMENT "會員ID",
	article_id		                INT           NOT NULL           COMMENT "文章ID",
    message_content                 TEXT          NOT NULL           COMMENT "留言內容",
    message_status                  INT           NOT NULL           COMMENT "留言狀態 0.不顯示 1.顯示",
    message_create_time             DATETIME
					                              DEFAULT CURRENT_TIMESTAMP
					                              ON UPDATE CURRENT_TIMESTAMP
							                                         COMMENT "留言時間",
                          
	CONSTRAINT pk_message PRIMARY KEY (message_id)
) COMMENT "文章留言";

CREATE TABLE article_collection (
	article_collection_id           INT           AUTO_INCREMENT     COMMENT "文章收藏ID",
	member_id	                    INT           NOT NULL           COMMENT "會員ID",
	article_id		                INT           NOT NULL           COMMENT "文章ID",
    collection_create_time          DATETIME
					                              DEFAULT CURRENT_TIMESTAMP
					                              ON UPDATE CURRENT_TIMESTAMP
							                                         COMMENT "收藏時間",
                             
	CONSTRAINT pk_article_collection PRIMARY KEY (article_collection_id)
) COMMENT "文章收藏";

CREATE TABLE board (
	board_id                        INT           AUTO_INCREMENT     COMMENT "各板ID",
	board_name	                    VARCHAR(100)  NOT NULL           COMMENT "各板名稱",
    
	CONSTRAINT pk_board PRIMARY KEY (board_id)
) COMMENT "文章各板";

CREATE TABLE heart (
	heart_id                        INT           AUTO_INCREMENT     COMMENT "點讚ID",
	member_id	                    INT           NOT NULL           COMMENT "會員ID",
    article_id		                INT           NOT NULL           COMMENT "文章ID",
    heart_create_time               DATETIME
					                              DEFAULT CURRENT_TIMESTAMP
					                                                COMMENT "點讚時間",
                          
	CONSTRAINT pk_heart PRIMARY KEY (heart_id)
) COMMENT "文章點讚";

CREATE TABLE article_img (
	article_img_id                  INT           AUTO_INCREMENT     COMMENT "文章圖片ID",
	article_id	         		    INT           NOT NULL           COMMENT "文章ID",
    article_pic		     	   	    MEDIUMBLOB                       COMMENT "圖片",
    article_img_create_time  		DATETIME
												  DEFAULT CURRENT_TIMESTAMP
											   	  ON UPDATE CURRENT_TIMESTAMP
												                     COMMENT "圖片建立時間",
                          
	CONSTRAINT pk_article_img PRIMARY KEY (article_img_id)
) COMMENT "文章圖片";

CREATE TABLE prosecute (
	prosecute_id           		    INT           AUTO_INCREMENT     COMMENT "檢舉ID",
	member_id	        		    INT           NOT NULL           COMMENT "檢舉人ID",
    article_id		      		    INT                              COMMENT "被檢舉文章ID",
    prosecute_reason       		    VARCHAR(255)  NOT NULL           COMMENT "被檢舉原因",
    message_id	          		    INT                              COMMENT "被檢舉留言ID",
    prosecute_create_time   		DATE          NOT NULL           COMMENT "檢舉日期",
    prosecute_status       		    INT           NOT NULL           COMMENT "檢舉狀態 0: 正常 1: 不顯示",
    
	CONSTRAINT pk__prosecute PRIMARY KEY (prosecute_id)
) COMMENT "檢舉";
-- 社群相關 ---------------------------------------------------------------

-- 商城相關 ---------------------------------------------------------------
CREATE TABLE commodity(
    commodity_id                    INT           AUTO_INCREMENT     COMMENT "商品ID",
    commodity_name         		    VARCHAR(255)  NOT NULL           COMMENT "商品名稱",
    commodity_price        		    DECIMAL(7,2)  NOT NULL           COMMENT "商品價格",
    commodity_stock        		    INT           NOT NULL           COMMENT "商品庫存",
    commodity_content      		    TEXT          NOT NULL           COMMENT "商品內容",
    activity_id            		    INT           NOT NULL           COMMENT "活動ID",
    partner_id             		    INT           NOT NULL           COMMENT "廠商ID",
    commodity_status       		    INT           NOT NULL           COMMENT "商品狀態 0:下架 1:在售",
    commodity_post_time     	    DATE          NOT NULL           COMMENT "上架時間",
    commodity_remove_time   	    DATE                             COMMENT "下架時間",
    commodity_update_time   		DATETIME
                                                  DEFAULT CURRENT_TIMESTAMP
					                              ON UPDATE CURRENT_TIMESTAMP
																     COMMENT "更新時間",
    commodity_create_time           DATETIME
                                                  DEFAULT CURRENT_TIMESTAMP
                                                                     COMMENT "建立時間",
                                     
    CONSTRAINT pk_commodity PRIMARY KEY (commodity_id)
) COMMENT "商品";

CREATE TABLE commodity_picture(
	commodity_picture_id            INT           AUTO_INCREMENT     COMMENT "商品圖片",
    commodity_id                    INT           NOT NULL           COMMENT "商品ID",
    commodity_picture               MEDIUMBLOB                       COMMENT "商品圖片",
    
    CONSTRAINT pk_commodity_picture PRIMARY KEY (commodity_picture_id)
) COMMENT "商品圖片";

CREATE TABLE orders(
    order_id               	  	    INT           AUTO_INCREMENT     COMMENT "訂單ID",
    member_id              		    INT           NOT NULL           COMMENT "會員ID",
    recipient           		    VARCHAR(100)  NOT NULL           COMMENT "收件人姓名",
    recipient_phone      		    VARCHAR(20)   NOT NULL           COMMENT "收件人電話",
    recipient_email      		    VARCHAR(100)  NOT NULL           COMMENT "收件人E-mail",
    recipient_address     		    VARCHAR(255)  NOT NULL           COMMENT "收件地址",
    member_coupon_id                INT                              COMMENT "會員優惠券ID",
    actual_amount        	        DECIMAL(7,2)  NOT NULL           COMMENT "實付金額",
    order_status          	        INT           NOT NULL           COMMENT "訂單狀態 0:取消 1:未出貨 2:已出貨 3:完成訂單 4:退貨中 5:完成退貨",
    pay_status           		    INT           NOT NULL           COMMENT "支付狀態",
    pay_time         		        DATETIME
											      DEFAULT CURRENT_TIMESTAMP
					                                                 COMMENT "付款時間",
    ship_time             		    DATETIME                         COMMENT "出貨時間",
                                  
    CONSTRAINT pk_orders PRIMARY KEY (order_id)
) COMMENT "訂單";

CREATE TABLE order_item(
    order_item_id           	    INT           AUTO_INCREMENT     COMMENT "訂單明細ID",
    order_id               		    INT           NOT NULL           COMMENT "訂單ID",
    commodity_id          		    INT           NOT NULL           COMMENT "商品ID",
    commodity_order_price           DECIMAL(7,2)  NOT NULL           COMMENT "商品下訂價格",
    order_item_quantity             INT           NOT NULL           COMMENT "商品數量",
    order_item_total_price          DECIMAL(7,2)  NOT NULL           COMMENT "單一商品總價",
    order_item_create_time   		DATETIME
                                                  DEFAULT CURRENT_TIMESTAMP
                                                                     COMMENT "建立時間",
                            
    CONSTRAINT pk_order_item PRIMARY KEY (order_item_id)
) COMMENT "訂單明細";


CREATE TABLE cart(
    cart_id                		    INT           AUTO_INCREMENT     COMMENT "購物車ID",
    member_id              		    INT           NOT NULL           COMMENT "會員ID",
    cart_total_price                DECIMAL(7,2)  NOT NULL           COMMENT "購物車總價",
    cart_create_time        		DATETIME
											      DEFAULT CURRENT_TIMESTAMP
                                                                     COMMENT "建立時間",
                        
    CONSTRAINT pk_cart PRIMARY KEY (cart_id)
) COMMENT "購物車";

CREATE TABLE cart_item(
	cart_item_id					INT			  AUTO_INCREMENT	 COMMENT "購物車明細ID",
    cart_id						    INT			  NOT NULL			 COMMENT "購物車ID",
    commodity_id					INT			  NOT NULL		 	 COMMENT "商品ID",
    checked_quantity			    INT			  NOT NULL		 	 COMMENT "購買商品數量",
    
    CONSTRAINT pk_cart_item PRIMARY KEY (cart_item_id)
) COMMENT "購物車明細";

-- 商城相關 ---------------------------------------------------------------

-- 最新消息相關 -----------------------------------------------------------
CREATE TABLE news(
    news_id                		    INT            AUTO_INCREMENT    COMMENT "消息ID",
    administrator_id      		    INT            NOT NULL          COMMENT "管理員ID",
    news_title             		    VARCHAR(255)   NOT NULL          COMMENT "標題",
    news_content         		    TEXT           NOT NULL          COMMENT "內容",
    news_status          		    INT            NOT NULL          COMMENT "狀態 0:隱藏 1:正常顯示 2:置頂",
    news_create_time      		    DATETIME
		                                           DEFAULT CURRENT_TIMESTAMP
					                               ON UPDATE CURRENT_TIMESTAMP
					                                                 COMMENT "發布時間",
                                     
    CONSTRAINT pk_news PRIMARY KEY (news_id)
) COMMENT "最新消息";

CREATE TABLE announcement(
    announcement_id         		 INT           AUTO_INCREMENT    COMMENT "公告ID",
    administrator_id       		     INT           NOT NULL          COMMENT "管理員ID",
    announcement_title     		     VARCHAR(255)  NOT NULL          COMMENT "標題",
    announcement_content    		 TEXT          NOT NULL          COMMENT "內容",
    announcement_status     		 INT           NOT NULL          COMMENT "狀態 0:隱藏 1:正常顯示 2:置頂",
    announcement_create_time 		 DATETIME
												   DEFAULT CURRENT_TIMESTAMP
					                               ON UPDATE CURRENT_TIMESTAMP
					                                                 COMMENT "發布時間",
                                     
    CONSTRAINT pk_news PRIMARY KEY (announcement_id)
) COMMENT "公告";
-- 最新消息相關 -----------------------------------------------------------
-- //////////////////////////////////// 建立表格 ////////////////////////////////////////////////


# -- //////////////////////////////////// 建立 FK /////////////////////////////////////////////////
# -- 會員相關 ---------------------------------------------------------------
# ALTER TABLE member_coupon
# 	ADD CONSTRAINT fk_member_coupon_general_member_memberID
# 	FOREIGN KEY (member_id) REFERENCES general_member (member_id),
# 	ADD CONSTRAINT fk_member_coupon_coupon_type_coupon_type_id
# 	FOREIGN KEY (coupon_type_id) REFERENCES  coupon_type (coupon_type_id);
# -- 會員相關 ---------------------------------------------------------------
#
# -- 場館相關 ---------------------------------------------------------------
# ALTER TABLE venue_area
# 	ADD CONSTRAINT fk_venue_area_venue_venue_id
# 	FOREIGN KEY (venue_id) REFERENCES venue (venue_id);
#
# ALTER TABLE seat
# 	ADD CONSTRAINT fk_seat_venue_area_venue_area_id
# 	FOREIGN KEY (venue_area_id) REFERENCES venue_area (venue_area_id),
#     ADD CONSTRAINT fk_seat_venue_venue_id
#     FOREIGN KEY (venue_id) REFERENCES venue (venue_id);
#
# ALTER TABLE venue_rental
# 	ADD CONSTRAINT fk_venue_rental_venue_venue_id
# 	FOREIGN KEY (venue_id) REFERENCES venue (venue_id),
# 	ADD CONSTRAINT fk_venue_rental_partner_member_partner_id
# 	FOREIGN KEY (partner_id) REFERENCES partner_member (partner_id);
#
# ALTER TABLE venue_time_slot
# 	ADD CONSTRAINT fk_venue_time_slot_venue_rental_venue_rental_id
# 	FOREIGN KEY (venue_rental_id) REFERENCES venue_rental (venue_rental_id);
#
# ALTER TABLE seat_status
# 	ADD CONSTRAINT fk_seat_status_activity_time_slot_activity_time_slot_id
# 	FOREIGN KEY (activity_time_slot_id) REFERENCES activity_time_slot (activity_time_slot_id),
# 	ADD CONSTRAINT fk_seat_status_seat_seat_id
# 	FOREIGN KEY (seat_id) REFERENCES seat (seat_id);
#
# ALTER TABLE activity_area_price
# 	ADD CONSTRAINT fk_activity_area_price_venue_area_venue_area_id
# 	FOREIGN KEY (venue_area_id) REFERENCES venue_area (venue_area_id),
# 	ADD CONSTRAINT fk_activity_area_price_activity_activity_id
# 	FOREIGN KEY (activity_id) REFERENCES activity (activity_id);
# -- 場館相關 ---------------------------------------------------------------
#
# -- 活動相關 ---------------------------------------------------------------
# ALTER TABLE activity
# 	ADD CONSTRAINT fk_activity_partner_member_partner_id
# 	FOREIGN KEY (partner_id) REFERENCES partner_member (partner_id),
# 	ADD CONSTRAINT fk_activity_venue_venue_id
# 	FOREIGN KEY (venue_id) REFERENCES venue (venue_id),
# 	ADD CONSTRAINT fk_activity_venue_rental_venue_rental_id
# 	FOREIGN KEY (venue_rental_id) REFERENCES venue_rental (venue_rental_id);
#
# ALTER TABLE activity_picture
# 	ADD CONSTRAINT fk_activity_picture_activity_activity_id
#     FOREIGN KEY (activity_id) REFERENCES activity (activity_id);
#
# ALTER TABLE activity_collection
# 	ADD CONSTRAINT fk_activity_collection_general_member_member_id
# 	FOREIGN KEY (member_id) REFERENCES general_member (member_id),
# 	ADD CONSTRAINT fk_activity_collection_activity_activity_id
# 	FOREIGN KEY (activity_id) REFERENCES activity (activity_id);
#
# ALTER TABLE activity_time_slot
# 	ADD CONSTRAINT fk_activity_time_slot_activity_activity_id
# 	FOREIGN KEY (activity_id) REFERENCES activity (activity_id);
# -- 活動相關 ---------------------------------------------------------------
#
# -- 票券相關 ---------------------------------------------------------------
# ALTER TABLE book_ticket
# 	ADD CONSTRAINT fk_book_ticket_general_member_member_id
# 	FOREIGN KEY (member_id) REFERENCES general_member (member_id),
# 	ADD CONSTRAINT fk_book_ticket_activity_activity_id
# 	FOREIGN KEY (activity_id) REFERENCES activity (activity_id),
# 	ADD CONSTRAINT fk_book_ticket_activity_time_slot_activity_time_slot_id
# 	FOREIGN KEY (activity_time_slot_id) REFERENCES activity_time_slot (activity_time_slot_id),
#     ADD CONSTRAINT fk_book_ticket__member_coupon_member_coupon_id
#     FOREIGN KEY (member_coupon_id) REFERENCES member_coupon (member_coupon_id);
#
# ALTER TABLE ticket
# 	ADD CONSTRAINT fk_ticket_general_member_member_id
# 	FOREIGN KEY (member_id) REFERENCES general_member (member_id),
# 	ADD CONSTRAINT fk_ticket_seat_status_seat_status_id
# 	FOREIGN KEY (seat_status_id) REFERENCES seat_status (seat_status_id),
# 	ADD CONSTRAINT fk_ticket_activity_area_price_activity_area_price_id
# 	FOREIGN KEY (activity_area_price_id) REFERENCES activity_area_price (activity_area_price_id),
# 	ADD CONSTRAINT fk_ticket_book_ticket_book_ticket_id
# 	FOREIGN KEY (book_ticket_id) REFERENCES book_ticket (book_ticket_id),
# 	ADD CONSTRAINT fk_ticket_activity_time_slot_activity_time_slot_id
# 	FOREIGN KEY (activity_time_slot_id) REFERENCES activity_time_slot (activity_time_slot_id);
# -- 票券相關 ---------------------------------------------------------------
#
# -- 社群相關 ---------------------------------------------------------------
# ALTER TABLE article
# 	ADD CONSTRAINT fk_article_general_member_member_id
# 	FOREIGN KEY (member_id) REFERENCES general_member (member_id),
# 	ADD CONSTRAINT  fk_article_board_board_id
# 	FOREIGN KEY (board_id) REFERENCES board (board_id);
#
# ALTER TABLE message
# 	ADD CONSTRAINT fk_message_general_member_member_id
# 	FOREIGN KEY (member_id) REFERENCES general_member (member_id),
# 	ADD CONSTRAINT fk_message_article_article_id
# 	FOREIGN KEY (article_id) REFERENCES article (article_id);
#
# ALTER TABLE article_collection
# 	ADD CONSTRAINT fk_article_collection_general_member_member_id
# 	FOREIGN KEY (member_id) REFERENCES general_member (member_id),
# 	ADD CONSTRAINT fk_article_collection_article_article_id
# 	FOREIGN KEY (article_id) REFERENCES article (article_id);
#
# ALTER TABLE heart
# 	ADD CONSTRAINT fk_heart_general_member_member_id
# 	FOREIGN KEY (member_id) REFERENCES general_member (member_id),
# 	ADD CONSTRAINT fk_heart_article_article_id
# 	FOREIGN KEY (article_id) REFERENCES article (article_id);
#
# ALTER TABLE article_img
# 	ADD CONSTRAINT fk_article_img_article_article_id
# 	FOREIGN KEY (article_id) REFERENCES article (article_id)
#     ON DELETE CASCADE ON UPDATE CASCADE;
#
# ALTER TABLE prosecute
# 	ADD CONSTRAINT fk_prosecute_general_member_member_id
# 	FOREIGN KEY (member_id) REFERENCES general_member (member_id),
# 	ADD CONSTRAINT fk_prosecute_article_article_id
# 	FOREIGN KEY (article_id) REFERENCES article (article_id),
# 	ADD CONSTRAINT fk_prosecute_message_message_id
# 	FOREIGN KEY (message_id) REFERENCES message (message_id);
# -- 社群相關 ---------------------------------------------------------------
#
# -- 商城相關 ---------------------------------------------------------------
# ALTER TABLE commodity
# 	ADD CONSTRAINT fk_commodity_activity_activity_id
# 	FOREIGN KEY (activity_id) REFERENCES activity (activity_id),
# 	ADD CONSTRAINT fk_commodity_partner_member_partner_id
# 	FOREIGN KEY (partner_id) REFERENCES partner_member (partner_id);
#
# ALTER TABLE commodity_picture
# 	ADD CONSTRAINT fk_commodity_picture_commodity_activity_id
# 	FOREIGN KEY (commodity_id) REFERENCES commodity (commodity_id);
#
# ALTER TABLE orders
# 	ADD CONSTRAINT fk_orders_general_member_member_id
# 	FOREIGN KEY (member_id) REFERENCES general_member (member_id),
# 	ADD CONSTRAINT fk_orders_member_coupon_member_coupon_id
# 	FOREIGN KEY (member_coupon_id) REFERENCES member_coupon (member_coupon_id);
#
# ALTER TABLE order_item
# 	ADD CONSTRAINT fk_order_item_orders_order_id
# 	FOREIGN KEY (order_id) REFERENCES orders (order_id),
# 	ADD CONSTRAINT fk_order_item_commodity_commodity_id
# 	FOREIGN KEY (commodity_id) REFERENCES commodity (commodity_id);
#
# ALTER TABLE cart
# 	ADD CONSTRAINT fk_cart_general_member_member_id
# 	FOREIGN KEY (member_id) REFERENCES general_member(member_id);
#
# ALTER TABLE cart_item
# 	ADD CONSTRAINT fk_cart_item_cart_cart_id
#     FOREIGN KEY (cart_id) REFERENCES cart (cart_id),
#     ADD CONSTRAINT fk_cartItem_commodity_commodity_id
#     FOREIGN KEY (commodity_id) REFERENCES commodity (commodity_id);
# -- 商城相關 ---------------------------------------------------------------
#
# -- 最新消息相關 -----------------------------------------------------------
# ALTER TABLE news
# 	ADD CONSTRAINT fk_news_administrator_administrator_id
# 	FOREIGN KEY (administrator_id) REFERENCES administrator(administrator_id);
#
# ALTER TABLE announcement
# 	ADD CONSTRAINT fk_announcement_administrator_administrator_id
# 	FOREIGN KEY (administrator_id) REFERENCES administrator(administrator_id);
# -- 最新消息相關 -----------------------------------------------------------
# -- //////////////////////////////////// 建立 FK /////////////////////////////////////////////////