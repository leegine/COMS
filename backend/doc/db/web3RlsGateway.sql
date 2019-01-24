-------------------------------------------------------------------------------
--ルールエンジン連携関連
-------------------------------------------------------------------------------

--1. Table名: ルールエンジンへのリクエストテーブル
CREATE TABLE oms_con_order_request (
    account_id 			NUMBER(18)		NOT NULL,
    sub_account_id		NUMBER(18)		NOT NULL,
    order_type			NUMBER(6)		NOT NULL,
    request_type			NUMBER(6)		NOT NULL,
    order_id 			NUMBER(18)		NOT NULL,
    product_type			NUMBER(6) 		NOT NULL,
    sub_order_id			NUMBER(18)		DEFAULT  '0' NOT NULL,
    sub_product_type		NUMBER(6) 		DEFAULT  '0' NOT NULL,
    serial_no_in_parent		NUMBER(8)		DEFAULT  '0' NOT NULL,
    status				VARCHAR2(1)		DEFAULT  '0' NOT NULL,
    rls_accepted_timestamp	DATE 			NULL,
    created_timestamp		DATE DEFAULT		sysdate NOT NULL,
    last_updated_timestamp	DATE DEFAULT		sysdate NOT NULL
);

CREATE UNIQUE INDEX XPKoms_con_order_request ON oms_con_order_request
(
	account_id,sub_account_id,order_type,request_type,order_id,product_type,sub_order_id,sub_product_type
) ;

CREATE  INDEX XIE1oms_con_order_request ON oms_con_order_request
(
	account_id,status
) ;

ALTER TABLE oms_con_order_request
       ADD  ( CONSTRAINT oms_con_order_request_PK PRIMARY KEY (account_id,sub_account_id,order_type,request_type,order_id,product_type,sub_order_id,sub_product_type) ) ;
       

--2. Table名: ルールエンジンからの通知テーブル
CREATE TABLE rls_con_order_hit_notify (
    account_id 			NUMBER(18)		NOT NULL,
    sub_account_id		NUMBER(18)		NOT NULL,
    order_type			NUMBER(6)		NOT NULL,
    order_id 			NUMBER(18)		NOT NULL,
    product_type			NUMBER(6) 		NOT NULL,
    status				VARCHAR2(1)		DEFAULT  '0' NOT NULL,
    notify_type			VARCHAR2(1)		DEFAULT  '0' NOT NULL,
    parent_order_id 		NUMBER(18)		NULL,
    parent_product_type	NUMBER(6) 		NULL,
    serial_no_in_parent           NUMBER(8)                DEFAULT  '0' NOT NULL,
    order_submit_error_code  VARCHAR2(2)		NULL,
    hit_tick_timestamp		DATE 			NULL,
    rls_hit_timestamp		DATE 			NULL,
    order_submit_timestamp	DATE 			NULL,
    submitter_login_id		NUMBER(18)		DEFAULT NULL,
    created_timestamp		DATE DEFAULT		sysdate NOT NULL,
    last_updated_timestamp	DATE DEFAULT		sysdate NOT NULL
);

CREATE UNIQUE INDEX XPKrls_con_order_hit_notify ON rls_con_order_hit_notify
(
	account_id,sub_account_id,order_type,order_id,product_type
) ;

CREATE  INDEX XIE1rls_con_order_hit_notify ON rls_con_order_hit_notify
(
	account_id,status
) ;

ALTER TABLE rls_con_order_hit_notify
       ADD  ( CONSTRAINT rls_con_order_hit_notify_PK PRIMARY KEY (account_id,sub_account_id,order_type,order_id,product_type) ) ;       
       
       

--3. Table名: 手動発注履歴テーブル
CREATE TABLE rls_manual_submit_history (
    history_id 			NUMBER(18)		NOT NULL,
    account_id 			NUMBER(18)		NOT NULL,
    sub_account_id		NUMBER(18)		NOT NULL,
    order_type			NUMBER(6)		NOT NULL,
    order_id 			NUMBER(18)		NOT NULL,
    product_type			NUMBER(6) 		NOT NULL,
    status				VARCHAR2(1)		DEFAULT  '0' NOT NULL,
    notify_type			VARCHAR2(1)		DEFAULT  '0' NOT NULL,
    parent_order_id 		NUMBER(18)		NULL,
    parent_product_type	NUMBER(6) 		NULL,
    serial_no_in_parent           NUMBER(8)                DEFAULT  '0' NOT NULL,
    order_submit_error_code  VARCHAR2(2)		NULL,
    hit_tick_timestamp		DATE 			NULL,
    rls_hit_timestamp		DATE 			NULL,
    order_submit_timestamp	DATE 			NULL,
    submitter_login_id		NUMBER(18)		DEFAULT NULL,    
    created_timestamp		DATE DEFAULT		sysdate NOT NULL,
    last_updated_timestamp	DATE DEFAULT		sysdate NOT NULL
);

CREATE UNIQUE INDEX XPKrls_manual_submit_history ON rls_manual_submit_history
(
	history_id
) ;

CREATE INDEX XIE1rls_manual_submit_history ON rls_manual_submit_history
(
	account_id,sub_account_id,order_type,order_id,product_type
) ;

ALTER TABLE rls_manual_submit_history
       ADD  ( CONSTRAINT rls_manual_submit_history_PK PRIMARY KEY (history_id) ) ;       
