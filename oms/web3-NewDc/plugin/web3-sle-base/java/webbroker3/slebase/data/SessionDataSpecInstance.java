head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SessionDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.slebase.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec sle_send_q_columns[] = {
    new ColumnSpec("queue_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("broker_name",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("biz_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("op_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("change_quantity",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("change_limit_price",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("already_execd_quantity",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("confirmed_by_sle_rcvd_q",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_emp_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("send_process_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec sle_send_q_errors_columns[] = {
    new ColumnSpec("queue_id",java.sql.Types.NUMERIC,18,0,false,true,"sle_send_q","queue_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("open_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec sle_rcvd_q_columns[] = {
    new ColumnSpec("queue_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("xblocks_product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("replies_type",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("replies_number",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("replies_index",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("sub_status",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("internal_ref",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("exchange_no",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("trade_number",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("gl_id",java.sql.Types.VARCHAR,15,0,true,false,null,null),
    new ColumnSpec("stock_code",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("side",java.sql.Types.NUMERIC,5,0,true,false,null,null),
    new ColumnSpec("modality",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("price",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("trading_phase",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("exec_price",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("avg_price",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("exec_qty",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("remaining_qty",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("number_of_trades",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("order_time",java.sql.Types.VARCHAR,14,0,true,false,null,null),
    new ColumnSpec("trade_booking_time",java.sql.Types.VARCHAR,14,0,true,false,null,null),
    new ColumnSpec("exec_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_emp_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("execution_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("f_delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("fx_rate",java.sql.Types.NUMERIC,7,4,true,false,null,null),
    new ColumnSpec("exec_serial_no",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("route_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("ack_type",java.sql.Types.NUMERIC,5,0,true,false,null,null),
    new ColumnSpec("ackd_command",java.sql.Types.NUMERIC,5,0,true,false,null,null),
    new ColumnSpec("old_qty",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("old_price",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("cancelled_qty",java.sql.Types.NUMERIC,18,6,true,false,null,null),
    new ColumnSpec("trigger_param",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("reject_cmd_type",java.sql.Types.NUMERIC,5,0,true,false,null,null),
    new ColumnSpec("reject_code",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("reject_time",java.sql.Types.VARCHAR,14,0,true,false,null,null),
    new ColumnSpec("exchange_msg_type",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("exchange_msg_code",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("user_number",java.sql.Types.NUMERIC,5,0,true,false,null,null),
    new ColumnSpec("memo",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("op_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("accept_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("elimination_message",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec sle_exchange_order_key_mng_columns[] = {
    new ColumnSpec("xblocks_product_type",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("exchange_order_key",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec sle_conn_status_changes_columns[] = {
    new ColumnSpec("que_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("new_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("event_acked_div",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("sle_conn_div",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("proc_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("sle_send_q",sle_send_q_columns,null),
    new TableSpec("sle_send_q_errors",sle_send_q_errors_columns,null),
    new TableSpec("sle_rcvd_q",sle_rcvd_q_columns,null),
    new TableSpec("sle_exchange_order_key_mng",sle_exchange_order_key_mng_columns,null),
    new TableSpec("sle_conn_status_changes",sle_conn_status_changes_columns,null),
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
