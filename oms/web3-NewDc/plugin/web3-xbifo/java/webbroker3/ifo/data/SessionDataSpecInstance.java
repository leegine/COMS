head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	SessionDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec ifo_order_exec_send_mail_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("future_option_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,true,null,null),
    new ColumnSpec("order_exec_status",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("order_action_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("taxation_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("received_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("execution_condition_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("executed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("delivaly_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("send_process_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("error_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("resend_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("resend_process_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("reason_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("email_address",java.sql.Types.VARCHAR,100,0,true,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec host_fotype_closing_cont_spec_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("target_product_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("delivary_month",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("future_option_product_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("strike_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("split_type",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("contract_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("closing_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("open_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("contract_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("create_datetime",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec host_fotype_close_order_notify_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("executed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("reason_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("close_notify_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("virtual_server_number_market",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("notice_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("notice_number",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("close_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec host_option_order_exec_notify_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("exec_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("exec_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("exec_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("dealed_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("virtual_server_number_market",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("notice_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("notice_number",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("exec_number",java.sql.Types.NUMERIC,8,0,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec host_fotype_order_accept_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("submit_order_route_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("virtual_server_number_market",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("notice_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("notice_number",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("accept_number",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec host_fotype_order_clmd_notify_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("modified_quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("modified_limit_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("modified_execution_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("modified_result",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("canmod_receipt_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("mod_submit_order_route_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("modified_order_rev",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("virtual_server_number_market",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("notice_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("notice_number",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("modified_stop_order_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("modified_w_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec host_fotype_order_receipt_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("order_date_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("trade_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("commision_product_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("underlying_product_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("sonar_market_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sonar_traded_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("execution_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("execution_condition",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("create_datetime",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("biz_datetime",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("channel",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("factor",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("estimated_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec host_fotype_order_history_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("sonar_market_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("target_product_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("delivery_month_yyyy",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("delivery_month_mm",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("future_option_product_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("strike_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("corp_code",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("front_order_exchange_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("front_order_system_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("front_order_trade_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("received_date_time",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("received_time",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("data_class_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("data_class_detail_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("buy_sell_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("execution_condition",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("front_order_time",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("sell_order_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("buy_order_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("stop_order_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("w_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("transaction_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("contract_check",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_chanel",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("commision_number",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("commision_branch_number",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("commision_product_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("change_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("modified_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("modified_execution_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("modified_stop_order_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("modified_w_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cancel_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("org_corp_code",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("canceled_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("exec_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("exec_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("exec_time",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("price_mark",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("exec_number",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("accept_number",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("virtual_server_number_market",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("executed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("stop_mark",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("expiration_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("reason_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("expiration_time",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec host_fotype_order_all_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("received_date_time_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("sonar_market_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("received_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_action_serial_no",java.sql.Types.NUMERIC,8,0,true,false,null,null),
    new ColumnSpec("submit_order_route_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("target_product_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("delivery_month_yyyy",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("delivery_month_mm",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("future_option_product_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("strike_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("split_type",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("sell_order_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("buy_order_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("execution_condition",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_order_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("w_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("transaction_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ticket_number",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("contract_check",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_chanel",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("commision_number",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("commision_branch_number",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("commision_product_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("change_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("change_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("change_execution_condition",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("change_stop_order_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("change_w_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cancel_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("front_order_exchange_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("front_order_system_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("front_order_trade_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("tradeaudit_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("corp_code",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("org_corp_code",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("virtual_server_number_jsoes",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("market_order_number",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("amg_send_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("amg_ack_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("market_ack_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("all_order_change_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("market_close_flag",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("ifo_order_exec_send_mail",ifo_order_exec_send_mail_columns,null),
    new TableSpec("host_fotype_closing_cont_spec",host_fotype_closing_cont_spec_columns,"rowid_pk=true"),
    new TableSpec("host_fotype_close_order_notify",host_fotype_close_order_notify_columns,"rowid_pk=true"),
    new TableSpec("host_option_order_exec_notify",host_option_order_exec_notify_columns,"rowid_pk=true"),
    new TableSpec("host_fotype_order_accept",host_fotype_order_accept_columns,"rowid_pk=true"),
    new TableSpec("host_fotype_order_clmd_notify",host_fotype_order_clmd_notify_columns,"rowid_pk=true"),
    new TableSpec("host_fotype_order_receipt",host_fotype_order_receipt_columns,"rowid_pk=true"),
    new TableSpec("host_fotype_order_history",host_fotype_order_history_columns,"rowid_pk=true"),
    new TableSpec("host_fotype_order_all",host_fotype_order_all_columns,"rowid_pk=true"),
  };

  private static final SessionDataSpecInstance instance = new SessionDataSpecInstance();

  private SessionDataSpecInstance() {
    super( tables );
  }

  public static DataSpec getInstance() {
    return instance;
  };

}
@
