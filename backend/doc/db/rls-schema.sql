--ðt«¶ðID
CREATE SEQUENCE RLS_COND_ORDER_HISTORY_ID_SEQ
  START WITH 1
  NOMAXVALUE;

--ðt«¶ID
CREATE SEQUENCE RLS_COND_ORDER_ID_SEQ
  START WITH 1
  INCREMENT BY 1000
  NOMAXVALUE;

--ðt«¶OP
CREATE SEQUENCE RLS_COND_ORDER_OP_SEQ
  START WITH 1
  INCREMENT BY 1000
  NOMAXVALUE;

--ðt«¶
CREATE SEQUENCE RLS_COND_ORDER_SEQ
  START WITH 1
  NOMAXVALUE;

--[Cxg
CREATE SEQUENCE RLS_EVENT_SEQ
  START WITH 1
  NOMAXVALUE;

--sê
CREATE SEQUENCE RLS_MARKET_RSP_SEQ
  START WITH 1
  INCREMENT BY 1000;

--OMS¶ID
CREATE SEQUENCE RLS_OMS_ORDER_ID_SEQ
  START WITH 1
  INCREMENT BY 1000
  NOMAXVALUE;

--OMS¶
CREATE SEQUENCE RLS_OMS_ORDER_SEQ
  START WITH 1
  NOMAXVALUE;


--AJEg
CREATE TABLE RLS_ACCOUNT
(
  ID  NUMBER   NOT NULL
);

--ðt«¶
CREATE TABLE RLS_COND_ORDER
(
  ID          NUMBER(38)                        NOT NULL,
  TYPE        NUMBER                            NOT NULL,
  PARENT_ID   NUMBER,
  STATUS      NUMBER(1)                         DEFAULT 0                     NOT NULL,
  ACCOUNT_ID  NUMBER                            NOT NULL,
  SEQ_NUM     NUMBER                            NOT NULL,
  ACT_RATIO   NUMBER                            DEFAULT 1                     NOT NULL,
  TSTAMP      TIMESTAMP(9)                      DEFAULT SYSTIMESTAMP          NOT NULL
);

--ðt«¶ð
CREATE TABLE RLS_COND_ORDER_HIST
(
  ID             NUMBER                         NOT NULL,
  COND_ORD_ID    NUMBER                         NOT NULL,
  OP_TYPE        NUMBER                         NOT NULL,
  TIMESTAMP      TIMESTAMP(9)                   DEFAULT SYSTIMESTAMP          NOT NULL,
  OP_ID          NUMBER                         NOT NULL,
  P_PRICE        NUMBER,
  P_DIRECTION    NUMBER,
  T_EXEC_TIME    DATE,
  OMS_EXEC_TYPE  NUMBER,
  OMS_ORIG_QTY   NUMBER,
  OMS_PRICE      NUMBER,
  EVENT_SEQ_NUM  NUMBER                         NOT NULL
);

--OMS¶
CREATE TABLE RLS_OMS_ORDER
(
  ACCOUNT_ID   NUMBER                           NOT NULL,
  ORD_ID       NUMBER                           NOT NULL,
  PROD_ID      NUMBER                           NOT NULL,
  MARKET_ID    NUMBER                           NOT NULL,
  ORD_TYPE     NUMBER                           NOT NULL,
  EXEC_TYPE    NUMBER                           NOT NULL,
  SIDE         NUMBER                           NOT NULL,
  ORIG_QTY     NUMBER                           NOT NULL,
  PRICE        NUMBER,
  COND_ORD_ID  NUMBER                           NOT NULL,
  TSTAMP       TIMESTAMP(9)                     DEFAULT SYSTIMESTAMP          NOT NULL
);

--[GWì¬OMS¶
CREATE TABLE RLS_OMS_ORDER_GEN
(
  ID           NUMBER                           NOT NULL,
  INIT_ORD_ID  NUMBER                           NOT NULL,
  EXEC_TYPE    NUMBER                           NOT NULL,
  ORIG_QTY     NUMBER                           NOT NULL,
  PRICE        NUMBER,
  ORD_TYPE     NUMBER                           NOT NULL,
  SEQ_NMBR     NUMBER                           NOT NULL
);

--lið
CREATE TABLE RLS_PRICE_COND
(
  COND_ORD_ID  NUMBER                           NOT NULL,
  PRICE        NUMBER                           NOT NULL,
  DIRECTION    NUMBER                           NOT NULL,
  PROD_ID      NUMBER                           NOT NULL,
  MARKET_ID    NUMBER                           NOT NULL
);

--eBbJ[
CREATE TABLE RLS_TICKER
(
  PROD_ID    NUMBER                             NOT NULL,
  MARKET_ID  NUMBER                             NOT NULL
);

--^Cð
CREATE TABLE RLS_TIME_COND
(
  COND_ORD_ID  NUMBER                           NOT NULL,
  EXEC_TIME    DATE                             NOT NULL
);


CREATE INDEX XIE1RLS_COND_ORDER ON RLS_COND_ORDER(ACCOUNT_ID);


CREATE INDEX XIE2RLS_COND_ORDER ON RLS_COND_ORDER(PARENT_ID);


CREATE UNIQUE INDEX XPKRLS_OMS_ORDER_GEN ON RLS_OMS_ORDER_GEN(ID, ORD_TYPE);


CREATE UNIQUE INDEX XPKRLS_OMS_ORDER ON RLS_OMS_ORDER(ORD_ID, ORD_TYPE);


CREATE UNIQUE INDEX XPKRLS_ACCOUNT ON RLS_ACCOUNT(ID);


CREATE UNIQUE INDEX XPKRLS_COND_ORDER ON RLS_COND_ORDER(ID);


CREATE UNIQUE INDEX XPKRLS_COND_ORD_HIST ON RLS_COND_ORDER_HIST(ID);


CREATE UNIQUE INDEX XPKRLS_PRICE_COND ON RLS_PRICE_COND(COND_ORD_ID);


CREATE UNIQUE INDEX XPKRLS_TICKER ON RLS_TICKER(PROD_ID, MARKET_ID);


CREATE UNIQUE INDEX XPKRLS_TIME_COND ON RLS_TIME_COND(COND_ORD_ID);


--`F[ð
CREATE TABLE RLS_CHAIN_COND
(
  COND_ORD_ID  NUMBER                           NOT NULL,
  INCREMENTAL  NUMBER                           NOT NULL
);

--sê
CREATE TABLE RLS_MARKET_RSP
(
  ACT_ID         NUMBER                         NOT NULL,
  RSP_TYPE       NUMBER                         NOT NULL,
  ORD_ID         NUMBER                         NOT NULL,
  QTY            NUMBER                         NOT NULL,
  FAILED_QTY     NUMBER,
  REJECTED       NUMBER                         NOT NULL,
  ORD_TYPE       NUMBER                         NOT NULL,
  TSTAMP         TIMESTAMP(9)                   DEFAULT SYSTIMESTAMP          NOT NULL,
  EVENT_SEQ_NUM  NUMBER                         NOT NULL
);


CREATE INDEX XIE1RLS_MARKET_RSP ON RLS_MARKET_RSP(ORD_ID, ORD_TYPE);


CREATE UNIQUE INDEX XPKRLS_CHAIN_COND ON RLS_CHAIN_COND(COND_ORD_ID);


CREATE UNIQUE INDEX XPKRLS_MARKET_RSP ON RLS_MARKET_RSP(ACT_ID);


ALTER TABLE RLS_ACCOUNT ADD (
  CONSTRAINT RLS_ACCOUNT_PK PRIMARY KEY (ID) USING INDEX
);

ALTER TABLE RLS_COND_ORDER ADD (
  CONSTRAINT RLS_COND_ORDER_PK PRIMARY KEY (ID) USING INDEX 
);

ALTER TABLE RLS_COND_ORDER_HIST ADD (
  CONSTRAINT RLS_COND_ORD_HIST_PK PRIMARY KEY (ID) USING INDEX 
);

ALTER TABLE RLS_OMS_ORDER ADD (
  CONSTRAINT RLS_OMS_ORDER_PK PRIMARY KEY (ORD_ID, ORD_TYPE) USING INDEX 
);

ALTER TABLE RLS_OMS_ORDER_GEN ADD (
  CONSTRAINT RLS_OMS_ORDER_GEN_PK PRIMARY KEY (ID, ORD_TYPE) USING INDEX 
);

ALTER TABLE RLS_PRICE_COND ADD (
  CONSTRAINT RLS_PRICE_COND_PK PRIMARY KEY (COND_ORD_ID) USING INDEX 
);

ALTER TABLE RLS_TICKER ADD (
  CONSTRAINT RLS_TICKER_PK PRIMARY KEY (PROD_ID, MARKET_ID) USING INDEX 
);

ALTER TABLE RLS_TIME_COND ADD (
  CONSTRAINT RLS_TIME_COND_PK PRIMARY KEY (COND_ORD_ID) USING INDEX 
);

ALTER TABLE RLS_CHAIN_COND ADD (
  CONSTRAINT RLS_CHAIN_COND_PK PRIMARY KEY (COND_ORD_ID) USING INDEX 
);

ALTER TABLE RLS_MARKET_RSP ADD (
  CONSTRAINT RLS_MARKET_RSP_PK PRIMARY KEY (ACT_ID) USING INDEX 
);

ALTER TABLE RLS_COND_ORDER_HIST ADD (
  CONSTRAINT RLS_COND_ORDER_HIST_FK FOREIGN KEY (COND_ORD_ID) 
    REFERENCES RLS_COND_ORDER (ID));

ALTER TABLE RLS_OMS_ORDER ADD (
  CONSTRAINT RLS_OMS_ORDER_FK FOREIGN KEY (COND_ORD_ID) 
    REFERENCES RLS_COND_ORDER (ID));

ALTER TABLE RLS_OMS_ORDER_GEN ADD (
  CONSTRAINT RLS_OMS_ORDER_GEN_FK FOREIGN KEY (INIT_ORD_ID, ORD_TYPE) 
    REFERENCES RLS_OMS_ORDER (ORD_ID,ORD_TYPE));

ALTER TABLE RLS_PRICE_COND ADD (
  CONSTRAINT RLS_PRICE_COND_FK FOREIGN KEY (COND_ORD_ID) 
    REFERENCES RLS_COND_ORDER (ID));

ALTER TABLE RLS_TIME_COND ADD (
  CONSTRAINT RLS_TIME_COND_FK FOREIGN KEY (COND_ORD_ID) 
    REFERENCES RLS_COND_ORDER (ID));

ALTER TABLE RLS_CHAIN_COND ADD (
  CONSTRAINT RLS_CHAIN_COND_FK FOREIGN KEY (COND_ORD_ID) 
    REFERENCES RLS_COND_ORDER (ID));

ALTER TABLE RLS_MARKET_RSP ADD (
  CONSTRAINT RLS_MARKET_RSP_FK FOREIGN KEY (ORD_ID, ORD_TYPE) 
    REFERENCES RLS_OMS_ORDER_GEN (ID,ORD_TYPE));


CREATE OR REPLACE VIEW RLS_COND_ORD_HUMAN
(ID, PARENT_ID, TYPE, STATUS, ACCOUNT_ID, SEQ_NUM, ACT_RATIO, TSTAMP)
AS 
SELECT ID, PARENT_ID, 
DECODE(TYPE, 1, 'SIMPLE', 2, 'PRICE', 3, 'TIME', 4, 'EXCLUSIVE', 5, 'CHAIN') AS type, 
DECODE(STATUS, 0, 'PENDING', 2, 'ACTIVATED', 3, 'CANCELED', 4, 'COMPLETED') AS status,
ACCOUNT_ID,SEQ_NUM,ACT_RATIO,TSTAMP FROM rls_cond_order;

--OMS¶ñèÊ
CREATE OR REPLACE VIEW RLS_OMS_ORDER_EXEC_QTY
(ORD_ID, ORD_TYPE, EXEC_QTY)
AS 
SELECT r.ord_id, r.ord_type, SUM(r.qty) AS exec_qty FROM rls_market_rsp r WHERE r.rsp_type=4 GROUP BY r.ord_id, r.ord_type;

--OMS¶EX
CREATE OR REPLACE VIEW RLS_OMS_ORDER_EX
(ACCOUNT_ID, ORD_ID, PROD_ID, MARKET_ID, ORD_TYPE, EXEC_TYPE, SIDE, ORIG_QTY, PRICE, COND_ORD_ID, 
 TSTAMP, GO_ID, GO_EXEC_TYPE, GO_ORIG_QTY, GO_PRICE, EXEC_QTY)
AS 
SELECT o.*, og.id as go_id, og.exec_type as go_exec_type, og.orig_qty as go_orig_qty, og.price as go_price, ex.exec_qty
FROM
rls_oms_order o
LEFT JOIN rls_oms_order_gen og ON og.init_ord_id = o.ord_id AND og.ord_type = o.ord_type
LEFT JOIN rls_oms_order_exec_qty ex ON og.id = ex.ord_id AND og.ord_type = ex.ord_type;

