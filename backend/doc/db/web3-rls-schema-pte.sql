-- Table名: スレッドごとアカウントレンジ
CREATE TABLE rls_account_range_per_thread (
    purpose_type			NUMBER(6)		NOT NULL,
    account_id_start		NUMBER(18)		NOT NULL,
    account_id_end			NUMBER(18)		NOT NULL,
    thread_no 				NUMBER(3)		NOT NULL,
    created_timestamp       DATE DEFAULT SYSDATE NOT NULL,
    last_updated_timestamp  DATE DEFAULT SYSDATE NOT NULL
)
TABLESPACE WBRK3_UPD_DATA01 PCTFREE 20 PCTUSED 80
STORAGE(FREELISTS 8 FREELIST GROUPS 3);

CREATE UNIQUE INDEX XPKrls_account_range_perthread ON rls_account_range_per_thread
(
	purpose_type, account_id_start, account_id_end
)
TABLESPACE WBRK3_UPD_INDEX01 PCTFREE 10 STORAGE(FREELISTS 8 FREELIST GROUPS 3);

ALTER TABLE rls_account_range_per_thread
       ADD  ( CONSTRAINT rls_account_range_per_threadPK PRIMARY KEY (purpose_type, account_id_start, account_id_end) );
       


-- Table名: 発注失敗注文
CREATE TABLE rls_order_miss (
    miss_type                       VARCHAR2(1)                      NOT NULL,
    account_id                      NUMBER(18)                       NOT NULL,
    sub_account_id                  NUMBER(18)                       NOT NULL,
    order_id                        NUMBER(18)                       NOT NULL,
    order_unit_id                   NUMBER(18)                       NOT NULL,
    order_action_serial_no          NUMBER(8)                        NOT NULL,
    order_event_type                NUMBER(6)                        NOT NULL,
    product_type                    NUMBER(6)                        NOT NULL,
    product_id                      NUMBER(18)                       NOT NULL,
    market_id                       NUMBER(18)                       NOT NULL,
    oms_cond_order_type             NUMBER(6)                        NOT NULL,
    order_cond_operator             VARCHAR2(1)                      NULL,
    stop_order_price                DECIMAL(18,6)                    NULL,
    hit_tick_price                  DECIMAL(18,6)                    NULL,
    hit_tick_timestamp              DATE                             NULL,
    order_accepted_timestamp        DATE                             NOT NULL,
    detect_type                     VARCHAR2(1)                      NOT NULL,
    created_timestamp               DATE            DEFAULT sysdate  NOT NULL,
    last_updated_timestamp          DATE            DEFAULT sysdate  NOT NULL
)
TABLESPACE WBRK3_UPD_DATA01 PCTFREE 20 PCTUSED 80
STORAGE(FREELISTS 8 FREELIST GROUPS 3)
PARTITION BY range(account_id)
(PARTITION P1 VALUES LESS THAN (116800000000001),
 PARTITION P2 VALUES LESS THAN (133600000000001),
 PARTITION P3 VALUES LESS THAN (150400000000001),
 PARTITION P4 VALUES LESS THAN (167200000000001),
 PARTITION P5 VALUES LESS THAN (184000000000001),
 PARTITION P6 VALUES LESS THAN (MAXVALUE));


CREATE UNIQUE INDEX XPKrls_order_miss ON rls_order_miss
(
	account_id, sub_account_id, miss_type, order_unit_id, order_action_serial_no, product_type, oms_cond_order_type, detect_type
) 
TABLESPACE WBRK3_UPD_INDEX01 PCTFREE 10 STORAGE(FREELISTS 8 FREELIST GROUPS 3) LOCAL;


ALTER TABLE rls_order_miss
       ADD  ( CONSTRAINT rls_order_miss_PK PRIMARY KEY (account_id, sub_account_id, miss_type, order_unit_id, order_action_serial_no, product_type, oms_cond_order_type, detect_type) ) ;
       
CREATE INDEX XIE1rls_order_miss ON rls_order_miss
(
	order_id, product_type
) 
TABLESPACE WBRK3_UPD_INDEX01 PCTFREE 10 STORAGE(FREELISTS 8 FREELIST GROUPS 3) LOCAL;


