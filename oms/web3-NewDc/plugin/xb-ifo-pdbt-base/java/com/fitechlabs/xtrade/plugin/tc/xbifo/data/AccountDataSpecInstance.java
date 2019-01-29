head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.06.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	AccountDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbifo.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class AccountDataSpecInstance extends DataSpec {

  private static final ColumnSpec ifo_closing_contract_spec_columns[] = {
    new ColumnSpec("closing_contract_spec_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_order","order_id"),
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_order_unit","order_unit_id"),
    new ColumnSpec("contract_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_contract","contract_id"),
    new ColumnSpec("closing_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("confirmed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("executed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec ifo_contract_columns[] = {
    new ColumnSpec("contract_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("unit_size",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("original_quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("original_contract_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("contract_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("contract_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("open_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("close_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("setup_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("setup_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("management_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("management_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("interest_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("interest_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_product","product_id"),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("session_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec ifo_locked_contract_details_columns[] = {
    new ColumnSpec("contract_id",java.sql.Types.NUMERIC,18,0,false,true,"ifo_contract","contract_id"),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("locked_quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec ifo_fin_transaction_columns[] = {
    new ColumnSpec("fin_transaction_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_product","product_id"),
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
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,true,false,"ifo_order","order_id"),
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,true,false,"ifo_order_unit","order_unit_id"),
    new ColumnSpec("order_execution_id",java.sql.Types.NUMERIC,18,0,true,false,"ifo_order_execution","order_execution_id"),
    new ColumnSpec("commission_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("commission_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_paid_value",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_setup_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_setup_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("capital_gain",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("capital_gain_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("contract_id",java.sql.Types.NUMERIC,18,0,true,false,"ifo_contract","contract_id"),
    new ColumnSpec("imported_management_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_management_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_interest_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_interest_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec ifo_order_columns[] = {
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec ifo_order_action_columns[] = {
    new ColumnSpec("order_action_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_order","order_id"),
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_order_unit","order_unit_id"),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,true,false,"market","market_id"),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_event_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("execution_condition_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("order_condition_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_cond_operator",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_price_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_order_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("w_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("expiration_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("confirmed_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("confirmed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("executed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("order_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("expiration_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_action_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("executed_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_product","product_id"),
    new ColumnSpec("estimated_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("modify_cancel_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("closing_order",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_reason_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("request_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("org_order_condition_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("org_order_cond_operator",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("org_stop_price_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("org_stop_order_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("org_w_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("org_w_limit_exec_cond_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("w_limit_exec_cond_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("w_limit_before_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("w_limit_before_exec_cond_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("confirmed_exec_condition_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("expiration_date_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec ifo_order_execution_columns[] = {
    new ColumnSpec("order_execution_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,"branch","branch_id"),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,"trader","trader_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_order","order_id"),
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_order_unit","order_unit_id"),
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
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_product","product_id"),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec ifo_order_unit_columns[] = {
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,"branch","branch_id"),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,"trader","trader_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_order","order_id"),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_categ",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("last_order_action_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("last_execution_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("future_option_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,true,false,"market","market_id"),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("execution_condition_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("order_condition_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_cond_operator",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_price_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_order_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("w_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("expiration_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("confirmed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("confirmed_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("executed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("executed_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("order_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_open_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("expiration_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("biz_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_product","product_id"),
    new ColumnSpec("order_chanel",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("received_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("voucher_no",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("comm_tbl_no",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("comm_tbl_sub_no",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sonar_trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("estimated_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("sonar_traded_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("sonar_market_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("comm_product_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("modify_cancel_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("confirmed_order_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("confirmed_estimated_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("confirmed_exec_condition_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("closing_order",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_reason_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("request_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("first_order_unit_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("org_order_condition_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("org_order_cond_operator",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("org_stop_price_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("org_stop_order_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("org_w_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("org_w_limit_exec_cond_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("w_limit_exec_cond_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("w_limit_before_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("w_limit_before_exec_cond_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("submit_order_delay_flag",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("submit_order_route_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("confirmed_order_rev",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("order_rev",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("evening_session_carryover_flag",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("session_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("day_trade_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("reserve_order_exist_flag",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("expiration_date_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("ifo_closing_contract_spec",ifo_closing_contract_spec_columns,"inv=?.account_id"),
    new TableSpec("ifo_contract",ifo_contract_columns,"inv=?.account_id"),
    new TableSpec("ifo_locked_contract_details",ifo_locked_contract_details_columns,"inv=?.account_id"),
    new TableSpec("ifo_fin_transaction",ifo_fin_transaction_columns,"inv=?.account_id"),
    new TableSpec("ifo_order",ifo_order_columns,"inv=?.account_id"),
    new TableSpec("ifo_order_action",ifo_order_action_columns,"inv=?.account_id"),
    new TableSpec("ifo_order_execution",ifo_order_execution_columns,"inv=?.account_id"),
    new TableSpec("ifo_order_unit",ifo_order_unit_columns,"inv=?.account_id"),
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
