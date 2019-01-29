head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
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
@package webbroker3.feq.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec feq_order_emp_number_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("feq_order_emp_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("latest_order_emp_number",java.sql.Types.VARCHAR,4,0,false,false,null,null),
    new ColumnSpec("biz_date",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec host_feq_close_order_notify_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("order_emp_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("executed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("reason_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("close_notify_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec host_feq_order_exec_notify_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("order_emp_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("exec_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("exec_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("exec_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("f_delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("exec_serial_no",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("order_biz_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("fx_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("dealed_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("execution_seq_no",java.sql.Types.NUMERIC,8,0,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec host_feq_order_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("sonar_trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("order_emp_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("order_action_serial_no",java.sql.Types.NUMERIC,8,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("sell_order_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("buy_order_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("execution_condition",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sonar_traded_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("sonar_market_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("ticket_number",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("received_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("received_date_time_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_chanel",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("factor",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("commision_number",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("commision_branch_number",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("commision_product_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec host_feq_order_accept_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("order_emp_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec host_feq_changecancel_order_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("execution_condition",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("change_order_time",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("change_order_date_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("cancel_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("cancel_order_time",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("cancel_order_date_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("order_emp_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("order_action_serial_no",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("create_datetime",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec feq_order_change_status_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("original_order_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("new_order_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("received_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("original_order_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("original_order_error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("new_order_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("new_order_error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("feq_order_emp_number",feq_order_emp_number_columns,null),
    new TableSpec("host_feq_close_order_notify",host_feq_close_order_notify_columns,"rowid_pk=true"),
    new TableSpec("host_feq_order_exec_notify",host_feq_order_exec_notify_columns,"rowid_pk=true"),
    new TableSpec("host_feq_order",host_feq_order_columns,"rowid_pk=true"),
    new TableSpec("host_feq_order_accept",host_feq_order_accept_columns,"rowid_pk=true"),
    new TableSpec("host_feq_changecancel_order",host_feq_changecancel_order_columns,"rowid_pk=true"),
    new TableSpec("feq_order_change_status",feq_order_change_status_columns,null),
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
