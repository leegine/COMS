head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.56.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88640f7e4e;
filename	AccountDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbaio.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class AccountDataSpecInstance extends DataSpec {

  private static final ColumnSpec aio_fin_transaction_columns[] = {
    new ColumnSpec("fin_transaction_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"product","product_id"),
    new ColumnSpec("fin_transaction_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("fin_transaction_categ",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("fin_transaction_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("net_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"aio_order","order_id"),
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,false,false,"aio_order_unit","order_unit_id"),
    new ColumnSpec("commission_fee",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("commission_fee_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec aio_order_columns[] = {
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec aio_order_action_columns[] = {
    new ColumnSpec("order_action_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"aio_order","order_id"),
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,false,false,"aio_order_unit","order_unit_id"),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_event_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("order_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_action_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("expiration_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"product","product_id"),
    new ColumnSpec("cancel_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("error_reason_code",java.sql.Types.VARCHAR,4,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_user",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("currency_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("convert_amount",java.sql.Types.DECIMAL,21,6,true,false,null,null),
  };
  private static final ColumnSpec aio_order_unit_columns[] = {
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,"branch","branch_id"),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,"trader","trader_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"aio_order","order_id"),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_categ",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("last_order_action_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("order_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_open_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("expiration_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("biz_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"product","product_id"),
    new ColumnSpec("tax_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("mini_stock_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("received_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("sonar_trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("com_debit_number",java.sql.Types.VARCHAR,15,0,true,false,null,null),
    new ColumnSpec("guarantee_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("payment_application_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("cancel_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_reason_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("mq_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("description",java.sql.Types.VARCHAR,100,0,true,false,null,null),
    new ColumnSpec("est_transfer_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("transfer_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("transfer_done_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("currency_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("convert_amount",java.sql.Types.DECIMAL,21,6,true,false,null,null),
    new ColumnSpec("remark_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("remark_name",java.sql.Types.VARCHAR,10,0,true,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("aio_fin_transaction",aio_fin_transaction_columns,"inv=?.account_id"),
    new TableSpec("aio_order",aio_order_columns,"inv=?.account_id"),
    new TableSpec("aio_order_action",aio_order_action_columns,"inv=?.account_id"),
    new TableSpec("aio_order_unit",aio_order_unit_columns,"inv=?.account_id"),
  };

  private static final AccountDataSpecInstance instance = new AccountDataSpecInstance();

  private AccountDataSpecInstance() {
    super( tables );
  }

  public static DataSpec getInstance() {
    return instance;
  };

}
@
