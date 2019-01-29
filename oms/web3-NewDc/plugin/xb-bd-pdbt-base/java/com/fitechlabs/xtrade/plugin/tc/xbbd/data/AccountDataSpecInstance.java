head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.57.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	AccountDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class AccountDataSpecInstance extends DataSpec {

  private static final ColumnSpec bond_fin_transaction_columns[] = {
    new ColumnSpec("fin_transaction_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"bond_product","product_id"),
    new ColumnSpec("fin_transaction_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("fin_transaction_categ",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("fin_transaction_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("net_amount",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,true,false,"market","market_id"),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,true,false,"bond_order","order_id"),
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,true,false,"bond_order_unit","order_unit_id"),
    new ColumnSpec("order_execution_id",java.sql.Types.NUMERIC,18,0,true,false,"bond_order_execution","order_execution_id"),
    new ColumnSpec("commission_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("commission_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("asset_id",java.sql.Types.NUMERIC,18,0,true,false,"asset","asset_id"),
    new ColumnSpec("capital_gain",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("capital_gain_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("transfered_asset_mng_fee",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("transfered_asset_mng_fee_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("transfered_asset_setup_fee",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("transfered_asset_setup_fee_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("transfered_asset_book_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("accrued_interest",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec bond_order_columns[] = {
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec bond_order_action_columns[] = {
    new ColumnSpec("order_action_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,"trader","trader_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"bond_order","order_id"),
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,false,false,"bond_order_unit","order_unit_id"),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,true,false,"market","market_id"),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("deal_type",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("order_event_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("execution_condition_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("confirmed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("confirmed_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("executed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("order_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("expiration_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_exec_status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("order_action_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("executed_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"bond_product","product_id"),
    new ColumnSpec("exec_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("foreign_exec_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("foreign_delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("payment_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("base_fx_rate",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("exec_fx_rate",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("trading_price",java.sql.Types.DECIMAL,21,6,true,false,null,null),
    new ColumnSpec("foreign_trading_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("accrued_interest",java.sql.Types.DECIMAL,21,6,true,false,null,null),
    new ColumnSpec("foreign_accrued_interest",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("estimated_price",java.sql.Types.DECIMAL,21,6,true,false,null,null),
    new ColumnSpec("foreign_estimated_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("adjustment_before_maturity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("elapsed_days",java.sql.Types.NUMERIC,5,0,true,false,null,null),
    new ColumnSpec("calc_base_days",java.sql.Types.NUMERIC,5,0,true,false,null,null),
    new ColumnSpec("custodian_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("administrator_code",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("error_reason_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec bond_order_execution_columns[] = {
    new ColumnSpec("order_execution_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,"branch","branch_id"),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,"trader","trader_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"bond_order","order_id"),
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,false,false,"bond_order_unit","order_unit_id"),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,true,false,"market","market_id"),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("exec_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("exec_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("exec_quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("exec_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("biz_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"bond_product","product_id"),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec bond_order_unit_columns[] = {
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,"branch","branch_id"),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,"trader","trader_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"bond_order","order_id"),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_categ",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("deal_type",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("last_order_action_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("last_execution_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,true,false,"market","market_id"),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("execution_condition_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("foreign_delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("expiration_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("confirmed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("confirmed_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("executed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("executed_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("order_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_open_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("expiration_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("lock_status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("order_exec_status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("biz_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("foreign_biz_date",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"bond_product","product_id"),
    new ColumnSpec("order_chanel",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("received_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("sonar_trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("bond_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("currency_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("settlement_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("auto_exec_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("executed_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("base_fx_rate",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("exec_fx_rate",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("trading_price",java.sql.Types.DECIMAL,21,6,true,false,null,null),
    new ColumnSpec("foreign_trading_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("accrued_interest",java.sql.Types.DECIMAL,21,6,true,false,null,null),
    new ColumnSpec("foreign_accrued_interest",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("estimated_price",java.sql.Types.DECIMAL,21,6,true,false,null,null),
    new ColumnSpec("foreign_estimated_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("adjustment_before_maturity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("elapsed_days",java.sql.Types.NUMERIC,5,0,true,false,null,null),
    new ColumnSpec("calc_base_days",java.sql.Types.NUMERIC,5,0,true,false,null,null),
    new ColumnSpec("exec_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("foreign_exec_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("payment_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("custodian_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("host_send_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("administrator_code",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("error_reason_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("exec_host_reflect_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("cancel_host_reflect_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("bond_fin_transaction",bond_fin_transaction_columns,"inv=?.account_id"),
    new TableSpec("bond_order",bond_order_columns,"inv=?.account_id"),
    new TableSpec("bond_order_action",bond_order_action_columns,"inv=?.account_id"),
    new TableSpec("bond_order_execution",bond_order_execution_columns,"inv=?.account_id"),
    new TableSpec("bond_order_unit",bond_order_unit_columns,"inv=?.account_id"),
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
