

CREATE TABLE account_preferences (
       account_id           NUMERIC(18) NOT NULL,
       name                 VARCHAR(80) NOT NULL,
       name_serial_no       NUMERIC(6) NOT NULL,
       value                VARCHAR(200) NOT NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKaccount_preferences ON account_preferences
(
       account_id                     ,
       name                           ,
       name_serial_no                 
);

CREATE INDEX XIE1account_preferences ON account_preferences
(
       account_id                     
);


ALTER TABLE account_preferences
       ADD  ( PRIMARY KEY (account_id, name, name_serial_no) ) ;


CREATE TABLE asset (
       asset_id             NUMERIC(18) NOT NULL,
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       product_type         NUMERIC(6) NOT NULL,
       quantity             DECIMAL(18,6) NOT NULL,
       book_value           DECIMAL(18,6) NOT NULL,
       setup_fee            DECIMAL(18,6) DEFAULT 0 NOT NULL,
       setup_fee_tax        DECIMAL(18,6) DEFAULT 0 NOT NULL,
       management_fee       DECIMAL(18,6) DEFAULT 0 NOT NULL,
       management_fee_tax   DECIMAL(18,6) DEFAULT 0 NOT NULL,
       product_id           NUMERIC(18) NOT NULL,
       tax_type             NUMERIC(6) NOT NULL,
       created_timestamp    DATE NOT NULL,
       last_updated_timestamp DATE NOT NULL
);

CREATE UNIQUE INDEX XPKasset ON asset
(
       asset_id                       
);

CREATE INDEX XIE1asset ON asset
(
       account_id                     ,
       sub_account_id                 
);


ALTER TABLE asset
       ADD  ( PRIMARY KEY (asset_id) ) ;


CREATE TABLE asset_unit (
       asset_unit_id        NUMERIC(18) NOT NULL,
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       product_id           NUMERIC(18) NOT NULL,
       asset_id             NUMERIC(18) NOT NULL,
       product_type         NUMERIC(6) NOT NULL,
       market_id            NUMERIC(18) NOT NULL,
       original_quantity    DECIMAL(18,6) NOT NULL,
       quantity             DECIMAL(18,6) NOT NULL,
       acquired_price       DECIMAL(18,6) NOT NULL,
       acquired_timestamp   DATE NOT NULL,
       delivery_date        DATE NOT NULL,
       created_timestamp    DATE NOT NULL,
       last_updated_timestamp DATE NOT NULL
);

CREATE UNIQUE INDEX XPKasset_unit ON asset_unit
(
       asset_unit_id                  
);

CREATE INDEX XIE1asset_unit ON asset_unit
(
       account_id                     ,
       sub_account_id                 
);

CREATE INDEX XIE2asset_unit ON asset_unit
(
       asset_id                       
);


ALTER TABLE asset_unit
       ADD  ( PRIMARY KEY (asset_unit_id) ) ;


CREATE TABLE asset_unit_sales (
       asset_unit_sales_id  NUMERIC(18) NOT NULL,
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       asset_unit_id        NUMERIC(18) NOT NULL,
       fin_transaction_id   NUMERIC(18) NOT NULL,
       quantity             DECIMAL(18,6) DEFAULT 0 NOT NULL,
       created_timestamp    DATE NOT NULL,
       last_updated_timestamp DATE NOT NULL
);

CREATE UNIQUE INDEX XPKasset_unit_sales ON asset_unit_sales
(
       asset_unit_sales_id            
);


ALTER TABLE asset_unit_sales
       ADD  ( PRIMARY KEY (asset_unit_sales_id) ) ;


CREATE TABLE branch (
       branch_id            NUMERIC(18) NOT NULL,
       branch_code          VARCHAR(20) NOT NULL,
       branch_name          VARCHAR(80) NOT NULL,
       branch_name_alt1     VARCHAR(80) NULL,
       branch_type          NUMERIC(6) NOT NULL,
       institution_id       NUMERIC(18) NOT NULL,
       institution_code     VARCHAR(20) NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKbranch ON branch
(
       branch_id                      
);

CREATE UNIQUE INDEX XAK1branch ON branch
(
       institution_id                 ,
       branch_code                    
);

CREATE UNIQUE INDEX XAK2branch ON branch
(
       institution_code               ,
       branch_code                    
);


ALTER TABLE branch
       ADD  ( PRIMARY KEY (branch_id) ) ;


CREATE TABLE gen_fin_transaction (
       transaction_id       NUMERIC(18) NOT NULL,
       delivery_date        DATE NOT NULL,
       transaction_description VARCHAR(200) NOT NULL,
       net_amount           DECIMAL(18,6) NOT NULL,
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       trade_module_name    VARCHAR(100) NULL,
       transaction_date     DATE NOT NULL,
       transaction_type     NUMERIC(6) NOT NULL,
       delete_flag          NUMERIC(1) DEFAULT 0 NOT NULL,
       biz_date             VARCHAR(8) NOT NULL,
       created_timestamp    DATE NOT NULL,
       last_updated_timestamp DATE NOT NULL
);

CREATE UNIQUE INDEX XPKgen_fin_transaction ON gen_fin_transaction
(
       transaction_id                 
);

CREATE INDEX XIE1gen_fin_transaction ON gen_fin_transaction
(
       account_id                     ,
       sub_account_id                 
);


ALTER TABLE gen_fin_transaction
       ADD  ( PRIMARY KEY (transaction_id) ) ;


CREATE TABLE institution (
       institution_id       NUMERIC(18) NOT NULL,
       institution_code     VARCHAR(20) NOT NULL,
       institution_name     VARCHAR(80) NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKinstitution ON institution
(
       institution_id                 
);

CREATE UNIQUE INDEX XAK1institution ON institution
(
       institution_code               
);


ALTER TABLE institution
       ADD  ( PRIMARY KEY (institution_id) ) ;


CREATE TABLE limit_price_range_defs (
       limit_price_range_defs_id NUMERIC(18) NOT NULL,
       market_id            NUMERIC(18) NOT NULL,
       cap_price            DECIMAL(18,6) NOT NULL,
       range1                DECIMAL(18,6) NOT NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKlimit_price_range_de ON limit_price_range_defs
(
       limit_price_range_defs_id      
);

CREATE UNIQUE INDEX XAK1limit_price_range_d ON limit_price_range_defs
(
       market_id                      ,
       cap_price                      
);


ALTER TABLE limit_price_range_defs
       ADD  ( PRIMARY KEY (limit_price_range_defs_id) ) ;


CREATE TABLE locked_asset_details (
       asset_id             NUMERIC(18) NOT NULL,
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       locked_quantity      DECIMAL(18,6) NOT NULL,
       created_timestamp    DATE NOT NULL,
       last_updated_timestamp DATE NOT NULL
);

CREATE UNIQUE INDEX XPKlocked_asset_details ON locked_asset_details
(
       asset_id                       
);


ALTER TABLE locked_asset_details
       ADD  ( PRIMARY KEY (asset_id) ) ;


CREATE TABLE main_account (
       account_id           NUMERIC(18) NOT NULL,
       institution_id       NUMERIC(18) NOT NULL,
       institution_code     VARCHAR(20) NOT NULL,
       account_code         VARCHAR(20) NOT NULL,
       family_name          VARCHAR(20) NOT NULL,
       middle_name          VARCHAR(20) NULL,
       given_name           VARCHAR(20) NOT NULL,
       family_name_alt1     VARCHAR(20) NULL,
       middle_name_alt1     VARCHAR(20) NULL,
       given_name_alt1      VARCHAR(20) NULL,
       family_name_alt2     VARCHAR(20) NULL,
       middle_name_alt2     VARCHAR(20) NULL,
       given_name_alt2      VARCHAR(20) NULL,
       address_line1        VARCHAR(40) NOT NULL,
       address_line2        VARCHAR(40) NULL,
       zip_code             VARCHAR(6) NOT NULL,
       sub_zip_code         VARCHAR(10) NULL,
       telephone            VARCHAR(20) NULL,
       mobile               VARCHAR(20) NULL,
       fax                  VARCHAR(20) NULL,
       email_address        VARCHAR(100) NULL,
       email_address_alt1   VARCHAR(100) NULL,
       trading_password     VARCHAR(18) NOT NULL,
       account_status       NUMERIC(6) NOT NULL,
       account_type         NUMERIC(6) NOT NULL,
       branch_id            NUMERIC(18) NOT NULL,
       branch_code          VARCHAR(20) NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKmain_account ON main_account
(
       account_id                     
);

CREATE UNIQUE INDEX XAK1main_account ON main_account
(
       institution_id                 ,
       branch_id                      ,
       account_code                   
);

CREATE UNIQUE INDEX XAK2main_account ON main_account
(
       institution_id                 ,
       branch_code                    ,
       account_code                   
);

CREATE INDEX XIE1main_account ON main_account
(
       branch_id                      
);

CREATE INDEX XIE2main_account ON main_account
(
       branch_code                    
);


ALTER TABLE main_account
       ADD  ( PRIMARY KEY (account_id) ) ;


CREATE TABLE market (
       market_id            NUMERIC(18) NOT NULL,
       institution_code     VARCHAR(20) NOT NULL,
       market_code          VARCHAR(20) NOT NULL,
       market_name          VARCHAR(20) NOT NULL,
       market_name_alt1     VARCHAR(20) NULL,
       market_name_alt2     VARCHAR(20) NULL,
       open_time            VARCHAR(6) NOT NULL,
       close_time           VARCHAR(6) NOT NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKmarket ON market
(
       market_id                      
);

CREATE UNIQUE INDEX XAK1market ON market
(
       institution_code               ,
       market_code                    
);


ALTER TABLE market
       ADD  ( PRIMARY KEY (market_id) ) ;


CREATE TABLE market_calendar (
       market_id            NUMERIC(18) NOT NULL,
       trade_date           DATE NOT NULL,
       holiday_flag         NUMERIC(1) DEFAULT 1 NOT NULL,
       trade_open_time      VARCHAR(6) NULL,
       trade_close_time     VARCHAR(6) NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKmarket_calendar ON market_calendar
(
       market_id                      ,
       trade_date                     
);


ALTER TABLE market_calendar
       ADD  ( PRIMARY KEY (market_id, trade_date) ) ;


CREATE TABLE market_preferences (
       market_id            NUMERIC(18) NOT NULL,
       name                 VARCHAR(80) NOT NULL,
       name_serial_no       NUMERIC(6) NOT NULL,
       value                VARCHAR(200) NOT NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKmarket_preferences ON market_preferences
(
       market_id                      ,
       name                           ,
       name_serial_no                 
);


ALTER TABLE market_preferences
       ADD  ( PRIMARY KEY (market_id, name, name_serial_no) ) ;


CREATE TABLE participant (
       account_id           NUMERIC(18) NOT NULL,
       participant_id       NUMERIC(18) NOT NULL,
       participant_code     VARCHAR(40) NOT NULL,
       participant_middle_name VARCHAR(20) NULL,
       participant_family_name VARCHAR(40) NULL,
       participant_given_name VARCHAR(40) NULL,
       participant_middle_name_alt1 VARCHAR(20) NULL,
       participant_family_name_alt1 VARCHAR(40) NULL,
       participant_given_name_alt1 VARCHAR(40) NULL,
       participant_type     NUMERIC(6) NOT NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKparticipant ON participant
(
       account_id                     ,
       participant_id                 
);


ALTER TABLE participant
       ADD  ( PRIMARY KEY (account_id, participant_id) ) ;


CREATE TABLE product (
       product_id           NUMERIC(18) NOT NULL,
       institution_code     VARCHAR(20) NOT NULL,
       product_type         NUMERIC(6) NOT NULL,
       standard_name        VARCHAR(250) NOT NULL,
       name_alt1            VARCHAR(250) NULL,
       name_alt2            VARCHAR(250) NULL,
       lot_size             DECIMAL(18,6) NOT NULL,
       margin_ratio         DECIMAL(18,6) NOT NULL,
       primary_market_id    NUMERIC(18) NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKproduct ON product
(
       product_id                     
);

CREATE INDEX XIE1product ON product
(
       institution_code               ,
       product_type                   
);


ALTER TABLE product
       ADD  ( PRIMARY KEY (product_id) ) ;


CREATE TABLE sub_account (
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       sub_account_type     NUMERIC(6) NOT NULL,
       sub_account_status   NUMERIC(6) NOT NULL,
       open_date            DATE NULL,
       close_date           DATE NULL,
       cash_balance         DECIMAL(18,6) NULL,
       branch_id            NUMERIC(18) NULL,
       institution_id       NUMERIC(18) NOT NULL,
       institution_code     VARCHAR(20) NOT NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKsub_account ON sub_account
(
       account_id                     ,
       sub_account_id                 
);

CREATE UNIQUE INDEX XAK1sub_account_aid_stype ON sub_account
(
       account_id                     ,
       sub_account_type               
);


ALTER TABLE sub_account
       ADD  ( PRIMARY KEY (account_id, sub_account_id) ) ;


CREATE TABLE sub_account_preferences (
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       name                 VARCHAR(80) NOT NULL,
       name_serial_no       NUMERIC(6) NOT NULL,
       value                VARCHAR(200) NOT NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKsub_account_preferences ON sub_account_preferences
(
       account_id                     ,
       sub_account_id                 ,
       name                           ,
       name_serial_no                 
);

CREATE INDEX XIE1sub_account_preferences ON sub_account_preferences
(
       account_id                     ,
       sub_account_id                 
);


ALTER TABLE sub_account_preferences
       ADD  ( PRIMARY KEY (account_id, sub_account_id, name, 
              name_serial_no) ) ;


CREATE TABLE system_preferences (
       name                 VARCHAR(80) NOT NULL,
       value                VARCHAR(200) NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKsystem_preferences ON system_preferences
(
       name                           
);


ALTER TABLE system_preferences
       ADD  ( PRIMARY KEY (name) ) ;


CREATE TABLE tick_values_defs (
       tick_values_defs_id  NUMERIC(18) NOT NULL,
       market_id            NUMERIC(18) NOT NULL,
       product_type         NUMERIC(6) NOT NULL,
       product_id           NUMERIC(18) NULL,
       cap_price            DECIMAL(18,6) NOT NULL,
       tick_value           DECIMAL(18,6) NOT NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKtick_values_defs ON tick_values_defs
(
       tick_values_defs_id            
);

CREATE INDEX XIE1tick_values_defs ON tick_values_defs
(
       market_id                      ,
       product_type                   ,
       cap_price                      
);


ALTER TABLE tick_values_defs
       ADD  ( PRIMARY KEY (tick_values_defs_id) ) ;


CREATE TABLE traded_product (
       traded_product_id    NUMERIC(18) NOT NULL,
       institution_code     VARCHAR(20) NOT NULL,
       product_id           NUMERIC(18) NOT NULL,
       market_id            NUMERIC(18) NOT NULL,
       margin_ratio         DECIMAL(18,6) NULL,
       suspension_flag      NUMERIC(1) DEFAULT 0 NOT NULL,
       base_date            DATE NOT NULL,
       daily_delivery_date  DATE NULL,
       product_type         NUMERIC(6) NOT NULL,
       collateral_flag      NUMERIC(6) NOT NULL,
       valid_until_biz_date VARCHAR(8) NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKtraded_product ON traded_product
(
       traded_product_id              
);

CREATE UNIQUE INDEX XAK1traded_product ON traded_product
(
       product_id                     ,
       market_id                      
);


ALTER TABLE traded_product
       ADD  ( PRIMARY KEY (traded_product_id) ) ;


CREATE TABLE traded_product_calendar (
       traded_product_id    NUMERIC(18) NOT NULL,
       trade_date           DATE NOT NULL,
       product_id           NUMERIC(18) NOT NULL,
       market_id            NUMERIC(18) NOT NULL,
       holiday_flag         NUMERIC(1) DEFAULT 1 NOT NULL,
       trade_open_time      VARCHAR(6) NULL,
       trade_close_time     VARCHAR(6) NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKtraded_product_calendar ON traded_product_calendar
(
       traded_product_id              ,
       trade_date                     
);

CREATE UNIQUE INDEX XAK1traded_product_calendar ON traded_product_calendar
(
       product_id                     ,
       market_id                      ,
       trade_date                     
);


ALTER TABLE traded_product_calendar
       ADD  ( PRIMARY KEY (traded_product_id, trade_date) ) ;


CREATE TABLE traded_product_updq (
       traded_product_id    NUMERIC(18) NOT NULL,
       valid_until_biz_date VARCHAR(8) NOT NULL,
       product_id           NUMERIC(18) NOT NULL,
       market_id            NUMERIC(18) NOT NULL,
       institution_code     VARCHAR(20) NOT NULL,
       margin_ratio         DECIMAL(18,6) NULL,
       suspension_flag      NUMERIC(1) DEFAULT 0 NOT NULL,
       base_date            DATE NOT NULL,
       daily_delivery_date  DATE NULL,
       product_type         NUMERIC(6) NOT NULL,
       collateral_flag      NUMERIC(6) NOT NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKtraded_product_updq ON traded_product_updq
(
       traded_product_id              ,
       valid_until_biz_date           
);

CREATE UNIQUE INDEX XAK1traded_product_updq ON traded_product_updq
(
       product_id                     ,
       market_id                      ,
       valid_until_biz_date           
);


ALTER TABLE traded_product_updq
       ADD  ( PRIMARY KEY (traded_product_id, valid_until_biz_date) ) ;


CREATE TABLE trader (
       trader_id            NUMERIC(18) NOT NULL,
       trader_code          VARCHAR(20) NOT NULL,
       trader_type          NUMERIC(6) NOT NULL,
       login_id             NUMERIC(18) NOT NULL,
       institution_code     VARCHAR(20) NOT NULL,
       branch_id            NUMERIC(18) NOT NULL,
       branch_code          VARCHAR(20) NOT NULL,
       family_name          VARCHAR(20) NOT NULL,
       middle_name          VARCHAR(20) NULL,
       given_name           VARCHAR(20) NOT NULL,
       family_name_alt1     VARCHAR(20) NULL,
       middle_name_alt1     VARCHAR(20) NULL,
       given_name_alt1      VARCHAR(20) NULL,
       family_name_alt2     VARCHAR(20) NULL,
       middle_name_alt2     VARCHAR(20) NULL,
       given_name_alt2      VARCHAR(20) NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKtrader ON trader
(
       trader_id                      
);

CREATE UNIQUE INDEX XAK1trader_code ON trader
(
       institution_code               ,
       branch_code                    ,
       trader_code                    
);

CREATE UNIQUE INDEX XAK2trader ON trader
(
       login_id                       
);

CREATE UNIQUE INDEX XAK3trader ON trader
(
       branch_id                      ,
       trader_code                    
);


ALTER TABLE trader
       ADD  ( PRIMARY KEY (trader_id) ) ;




CREATE TABLE eqtype_closing_contract_spec (
       closing_contract_spec_id NUMERIC(18) NOT NULL,
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       order_id             NUMERIC(18) NOT NULL,
       order_unit_id        NUMERIC(18) NOT NULL,
       contract_id          NUMERIC(18) NOT NULL,
       closing_serial_no    NUMERIC(8) NOT NULL,
       quantity             DECIMAL(18,6) NOT NULL,
       confirmed_quantity   DECIMAL(18,6) NULL,
       executed_quantity    DECIMAL(18,6) NULL,
       created_timestamp    DATE NOT NULL,
       last_updated_timestamp DATE NOT NULL
);

CREATE UNIQUE INDEX XPKeqtype_closing_contract_spe ON eqtype_closing_contract_spec
(
       closing_contract_spec_id       
);

CREATE UNIQUE INDEX XAK1eqtype_closing_contract_sp ON eqtype_closing_contract_spec
(
       order_unit_id                  ,
       contract_id                    
);

CREATE UNIQUE INDEX XAK2eqtype_closing_contract_sp ON eqtype_closing_contract_spec
(
       order_unit_id                  ,
       closing_serial_no              
);

CREATE INDEX XIE1eqtype_closing_contract_sp ON eqtype_closing_contract_spec
(
       order_unit_id                  
);


ALTER TABLE eqtype_closing_contract_spec
       ADD  ( PRIMARY KEY (closing_contract_spec_id) ) ;


CREATE TABLE eqtype_contract (
       contract_id          NUMERIC(18) NOT NULL,
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       market_id            NUMERIC(18) NOT NULL,
       original_quantity    DECIMAL(18,6) NOT NULL,
       quantity             DECIMAL(18,6) NOT NULL,
       original_contract_price DECIMAL(18,6) NOT NULL,
       contract_price       DECIMAL(18,6) NOT NULL,
       contract_type        NUMERIC(6) NOT NULL,
       open_date            DATE NOT NULL,
       close_date           DATE NOT NULL,
       setup_fee            DECIMAL(18,6) DEFAULT 0 NOT NULL,
       setup_fee_tax        DECIMAL(18,6) DEFAULT 0 NOT NULL,
       management_fee       DECIMAL(18,6) DEFAULT 0 NOT NULL,
       management_fee_tax   DECIMAL(18,6) DEFAULT 0 NOT NULL,
       interest_fee         DECIMAL(18,6) DEFAULT 0 NOT NULL,
       interest_fee_tax     DECIMAL(18,6) DEFAULT 0 NOT NULL,
       product_id           NUMERIC(18) NOT NULL,
       product_type         NUMERIC(6) NOT NULL,
       created_timestamp    DATE NOT NULL,
       last_updated_timestamp DATE NOT NULL
);

CREATE UNIQUE INDEX XPKeqtype_contract ON eqtype_contract
(
       contract_id                    
);

CREATE INDEX XIE1eqtype_contract ON eqtype_contract
(
       account_id                     ,
       sub_account_id                 
);


ALTER TABLE eqtype_contract
       ADD  ( PRIMARY KEY (contract_id) ) ;


CREATE TABLE eqtype_fin_transaction (
       fin_transaction_id   NUMERIC(18) NOT NULL,
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       product_id           NUMERIC(18) NOT NULL,
       fin_transaction_type NUMERIC(6) NOT NULL,
       fin_transaction_categ NUMERIC(6) NOT NULL,
       fin_transaction_timestamp DATE NOT NULL,
       tax_type             NUMERIC(6) NOT NULL,
       delivery_date        DATE NOT NULL,
       net_amount           DECIMAL(18,6) NOT NULL,
       product_type         NUMERIC(6) NOT NULL,
       market_id            NUMERIC(18) NULL,
       price                DECIMAL(18,6) NULL,
       quantity             DECIMAL(18,6) NOT NULL,
       order_id             NUMERIC(18) NULL,
       order_unit_id        NUMERIC(18) NULL,
       order_execution_id   NUMERIC(18) NULL,
       commission_fee       DECIMAL(18,6) DEFAULT 0 NOT NULL,
       commission_fee_tax   DECIMAL(18,6) DEFAULT 0 NOT NULL,
       asset_id             NUMERIC(18) NULL,
       imported_paid_value  DECIMAL(18,6) DEFAULT 0 NOT NULL,
       imported_setup_fee   DECIMAL(18,6) DEFAULT 0 NOT NULL,
       imported_setup_fee_tax DECIMAL(18,6) DEFAULT 0 NOT NULL,
       capital_gain         DECIMAL(18,6) DEFAULT 0 NOT NULL,
       capital_gain_tax     DECIMAL(18,6) DEFAULT 0 NOT NULL,
       contract_id          NUMERIC(18) NULL,
       imported_management_fee DECIMAL(18,6) DEFAULT 0 NOT NULL,
       imported_management_fee_tax DECIMAL(18,6) DEFAULT 0 NOT NULL,
       imported_interest_fee DECIMAL(18,6) DEFAULT 0 NOT NULL,
       imported_interest_fee_tax DECIMAL(18,6) DEFAULT 0 NOT NULL,
       transfered_asset_mng_fee DECIMAL(18,6) DEFAULT 0 NULL,
       transfered_asset_mng_fee_tax DECIMAL(18,6) DEFAULT 0 NULL,
       transfered_asset_setup_fee DECIMAL(18,6) DEFAULT 0 NULL,
       transfered_asset_setup_fee_tax DECIMAL(18,6) DEFAULT 0 NULL,
       transfered_asset_book_value DECIMAL(18,6) NULL,
       delete_flag          NUMERIC(1) DEFAULT 0 NOT NULL,
       accrued_interest     DECIMAL(18,6) NULL,
       created_timestamp    DATE NOT NULL,
       last_updated_timestamp DATE NOT NULL
);

CREATE UNIQUE INDEX XPKeqtype_fin_transaction ON eqtype_fin_transaction
(
       fin_transaction_id             
);

CREATE INDEX XIE1eqtype_fin_transaction ON eqtype_fin_transaction
(
       account_id                     ,
       sub_account_id                 
);


ALTER TABLE eqtype_fin_transaction
       ADD  ( PRIMARY KEY (fin_transaction_id) ) ;


CREATE TABLE eqtype_locked_contract_details (
       contract_id          NUMERIC(18) NOT NULL,
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       locked_quantity      DECIMAL(18,6) NOT NULL,
       created_timestamp    DATE NOT NULL,
       last_updated_timestamp DATE NOT NULL
);

CREATE UNIQUE INDEX XPKeqtype_locked_contract_deta ON eqtype_locked_contract_details
(
       contract_id                    
);


ALTER TABLE eqtype_locked_contract_details
       ADD  ( PRIMARY KEY (contract_id) ) ;


CREATE TABLE eqtype_order (
       order_id             NUMERIC(18) NOT NULL,
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       product_type         NUMERIC(6) NOT NULL,
       created_timestamp    DATE NOT NULL,
       last_updated_timestamp DATE NOT NULL
);

CREATE UNIQUE INDEX XPKeqtype_order ON eqtype_order
(
       order_id                       
);

CREATE INDEX XIE1eqtype_order ON eqtype_order
(
       account_id                     ,
       sub_account_id                 
);


ALTER TABLE eqtype_order
       ADD  ( PRIMARY KEY (order_id) ) ;


CREATE TABLE eqtype_order_action (
       order_action_id      NUMERIC(18) NOT NULL,
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       order_id             NUMERIC(18) NOT NULL,
       order_unit_id        NUMERIC(18) NOT NULL,
       market_id            NUMERIC(18) NULL,
       order_type           NUMERIC(6) NOT NULL,
       order_event_type     NUMERIC(6) NOT NULL,
       price                DECIMAL(18,6) NULL,
       execution_condition_type NUMERIC(6) NULL,
       quantity             DECIMAL(18,6) NOT NULL,
       confirmed_price      DECIMAL(18,6) NULL,
       confirmed_quantity   DECIMAL(18,6) NULL,
       executed_quantity    DECIMAL(18,6) NULL,
       order_status         NUMERIC(6) NOT NULL,
       expiration_status    NUMERIC(6) NOT NULL,
       order_action_serial_no NUMERIC(8) NOT NULL,
       executed_price       DECIMAL(18,6) NULL,
       product_type         NUMERIC(6) NOT NULL,
       product_id           NUMERIC(18) NOT NULL,
       quantity_type        NUMERIC(6) DEFAULT 1 NOT NULL,
       created_timestamp    DATE NOT NULL,
       last_updated_timestamp DATE NOT NULL
);

CREATE UNIQUE INDEX XPKeqtype_order_action ON eqtype_order_action
(
       order_action_id                
);

CREATE INDEX XIE1eqtype_order_action ON eqtype_order_action
(
       account_id                     ,
       sub_account_id                 
);

CREATE INDEX XIE2eqtype_order_action ON eqtype_order_action
(
       order_unit_id                  
);


ALTER TABLE eqtype_order_action
       ADD  ( PRIMARY KEY (order_action_id) ) ;


CREATE TABLE eqtype_order_execution (
       order_execution_id   NUMERIC(18) NOT NULL,
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       branch_id            NUMERIC(18) NOT NULL,
       trader_id            NUMERIC(18) NULL,
       order_id             NUMERIC(18) NOT NULL,
       order_unit_id        NUMERIC(18) NOT NULL,
       order_type           NUMERIC(6) NOT NULL,
       product_type         NUMERIC(6) NOT NULL,
       market_id            NUMERIC(18) NULL,
       delivery_date        DATE NOT NULL,
       exec_serial_no       NUMERIC(8) NOT NULL,
       exec_price           DECIMAL(18,6) NULL,
       exec_quantity        DECIMAL(18,6) NOT NULL,
       exec_timestamp       DATE NOT NULL,
       delete_flag          NUMERIC(1) DEFAULT 0 NOT NULL,
       biz_date             VARCHAR(8) NOT NULL,
       quantity_type        NUMERIC(6) DEFAULT 1 NOT NULL,
       product_id           NUMERIC(18) NOT NULL,
       created_timestamp    DATE NOT NULL,
       last_updated_timestamp DATE NOT NULL
);

CREATE UNIQUE INDEX XPKeqtype_order_execution ON eqtype_order_execution
(
       order_execution_id             
);

CREATE INDEX XIE1eqtype_order_execution ON eqtype_order_execution
(
       account_id                     ,
       sub_account_id                 
);

CREATE INDEX XIE2eqtype_order_execution ON eqtype_order_execution
(
       order_unit_id                  
);


ALTER TABLE eqtype_order_execution
       ADD  ( PRIMARY KEY (order_execution_id) ) ;


CREATE TABLE eqtype_order_unit (
       order_unit_id        NUMERIC(18) NOT NULL,
       account_id           NUMERIC(18) NOT NULL,
       sub_account_id       NUMERIC(18) NOT NULL,
       branch_id            NUMERIC(18) NOT NULL,
       trader_id            NUMERIC(18) NULL,
       order_id             NUMERIC(18) NOT NULL,
       order_type           NUMERIC(6) NOT NULL,
       order_categ          NUMERIC(6) NOT NULL,
       last_order_action_serial_no NUMERIC(8) DEFAULT 0 NOT NULL,
       last_execution_serial_no NUMERIC(8) DEFAULT 0 NOT NULL,
       product_type         NUMERIC(6) NOT NULL,
       market_id            NUMERIC(18) NULL,
       quantity             DECIMAL(18,6) NOT NULL,
       limit_price          DECIMAL(18,6) NULL,
       execution_condition_type NUMERIC(6) NULL,
       delivery_date        DATE NOT NULL,
       expiration_date      DATE NULL,
       confirmed_quantity   DECIMAL(18,6) NULL,
       confirmed_price      DECIMAL(18,6) NULL,
       executed_quantity    DECIMAL(18,6) NULL,
       executed_amount      DECIMAL(18,6) NULL,
       order_status         NUMERIC(6) DEFAULT 1 NOT NULL,
       order_open_status    NUMERIC(6) DEFAULT 1 NOT NULL,
       expiration_status    NUMERIC(6) NOT NULL,
       tax_type             NUMERIC(6) NOT NULL,
       biz_date             VARCHAR(8) NOT NULL,
       product_id           NUMERIC(18) NOT NULL,
       quantity_type        NUMERIC(6) DEFAULT 1 NOT NULL,
       created_timestamp    DATE NOT NULL,
       last_updated_timestamp DATE NOT NULL
);

CREATE UNIQUE INDEX XPKeqtype_order_unit ON eqtype_order_unit
(
       order_unit_id                  
);

CREATE INDEX XIE1eqtype_order_unit ON eqtype_order_unit
(
       account_id                     ,
       sub_account_id                 ,
       order_open_status              
);

CREATE INDEX XIE2eqtype_order_unit ON eqtype_order_unit
(
       order_id                       
);


ALTER TABLE eqtype_order_unit
       ADD  ( PRIMARY KEY (order_unit_id) ) ;


CREATE TABLE eqtype_product (
       product_id           NUMERIC(18) NOT NULL,
       institution_code     VARCHAR(20) NOT NULL,
       product_code         VARCHAR(20) NOT NULL,
       product_type         NUMERIC(6) NOT NULL,
       mini_stock_lot_size  DECIMAL(18,6) NULL,
       yearly_books_closing_date DATE NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKeqtype_product ON eqtype_product
(
       product_id                     
);

CREATE UNIQUE INDEX XAK1eqtype_product ON eqtype_product
(
       institution_code               ,
       product_code                   
);


ALTER TABLE eqtype_product
       ADD  ( PRIMARY KEY (product_id) ) ;


CREATE TABLE eqtype_traded_product (
       traded_product_id    NUMERIC(18) NOT NULL,
       institution_code     VARCHAR(20) NOT NULL,
       product_id           NUMERIC(18) NOT NULL,
       market_id            NUMERIC(18) NOT NULL,
       list_flag            NUMERIC(1) DEFAULT 1 NOT NULL,
       listed_date          DATE NOT NULL,
       unlisted_date        DATE NULL,
       marginable_flag      NUMERIC(1) DEFAULT 0 NOT NULL,
       shortable_flag       NUMERIC(1) DEFAULT 0 NOT NULL,
       last_closing_price   DECIMAL(18,6) NOT NULL,
       stop_high_price      DECIMAL(18,6) NULL,
       stop_low_price       DECIMAL(18,6) NULL,
       price_range_ratio    DECIMAL(18,6) NULL,
       mini_stock_flag      NUMERIC(6) NOT NULL,
       valid_until_biz_date VARCHAR(8) NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKeqtype_traded_product ON eqtype_traded_product
(
       traded_product_id              
);

CREATE UNIQUE INDEX XAK1eqtype_traded_product ON eqtype_traded_product
(
       product_id                     ,
       market_id                      
);


ALTER TABLE eqtype_traded_product
       ADD  ( PRIMARY KEY (traded_product_id) ) ;


CREATE TABLE eqtype_traded_product_updq (
       traded_product_id    NUMERIC(18) NOT NULL,
       valid_until_biz_date VARCHAR(8) NOT NULL,
       institution_code     VARCHAR(20) NOT NULL,
       product_id           NUMERIC(18) NOT NULL,
       market_id            NUMERIC(18) NOT NULL,
       list_flag            NUMERIC(1) DEFAULT 1 NOT NULL,
       listed_date          DATE NOT NULL,
       unlisted_date        DATE NULL,
       marginable_flag      NUMERIC(1) DEFAULT 0 NOT NULL,
       shortable_flag       NUMERIC(1) DEFAULT 0 NOT NULL,
       last_closing_price   DECIMAL(18,6) NOT NULL,
       stop_high_price      DECIMAL(18,6) NULL,
       stop_low_price       DECIMAL(18,6) NULL,
       price_range_ratio    DECIMAL(18,6) NULL,
       mini_stock_flag      NUMERIC(6) NOT NULL,
       created_timestamp    DATE ,
       last_updated_timestamp DATE 
);

CREATE UNIQUE INDEX XPKeqtype_traded_product_updq ON eqtype_traded_product_updq
(
       traded_product_id              ,
       valid_until_biz_date           
);

CREATE UNIQUE INDEX XAK1eqtype_traded_product_updq ON eqtype_traded_product_updq
(
       product_id                     ,
       market_id                      ,
       valid_until_biz_date           
);


ALTER TABLE eqtype_traded_product_updq
       ADD  ( PRIMARY KEY (traded_product_id, valid_until_biz_date) ) ;

