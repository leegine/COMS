head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	SessionDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec oms_con_order_request_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("request_type",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("sub_order_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("sub_product_type",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("serial_no_in_parent",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("rls_accepted_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec rls_con_order_hit_notify_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("notify_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("parent_order_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("parent_product_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("serial_no_in_parent",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("order_submit_error_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("hit_tick_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("rls_hit_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_submit_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("submitter_login_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec rls_manual_submit_history_columns[] = {
    new ColumnSpec("history_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("notify_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("parent_order_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("parent_product_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("serial_no_in_parent",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("order_submit_error_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("hit_tick_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("rls_hit_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_submit_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("submitter_login_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec rls_order_miss_columns[] = {
    new ColumnSpec("miss_type",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("order_action_serial_no",java.sql.Types.NUMERIC,8,0,false,true,null,null),
    new ColumnSpec("order_event_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("oms_cond_order_type",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("order_cond_operator",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_order_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("hit_tick_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("hit_tick_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_accepted_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("detect_type",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("order_submit_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec rls_account_columns[] = {
    new ColumnSpec("id",java.sql.Types.NUMERIC,0,0,false,true,null,null),
  };
  private static final ColumnSpec rls_cond_order_columns[] = {
    new ColumnSpec("id",java.sql.Types.NUMERIC,38,0,false,true,null,null),
    new ColumnSpec("type",java.sql.Types.NUMERIC,0,0,false,false,null,null),
    new ColumnSpec("parent_id",java.sql.Types.NUMERIC,0,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,0,0,false,false,null,null),
    new ColumnSpec("seq_num",java.sql.Types.NUMERIC,0,0,false,false,null,null),
    new ColumnSpec("act_ratio",java.sql.Types.NUMERIC,0,0,false,false,null,null),
    new ColumnSpec("tstamp",java.sql.Types.TIMESTAMP,9,0,false,false,null,null),
  };
  private static final ColumnSpec rls_oms_order_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,0,0,false,false,null,null),
    new ColumnSpec("ord_id",java.sql.Types.NUMERIC,0,0,false,true,null,null),
    new ColumnSpec("prod_id",java.sql.Types.NUMERIC,0,0,false,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,0,0,false,false,null,null),
    new ColumnSpec("ord_type",java.sql.Types.NUMERIC,0,0,false,true,null,null),
    new ColumnSpec("exec_type",java.sql.Types.NUMERIC,0,0,false,false,null,null),
    new ColumnSpec("side",java.sql.Types.NUMERIC,0,0,false,false,null,null),
    new ColumnSpec("orig_qty",java.sql.Types.NUMERIC,0,0,false,false,null,null),
    new ColumnSpec("price",java.sql.Types.NUMERIC,0,0,true,false,null,null),
    new ColumnSpec("cond_ord_id",java.sql.Types.NUMERIC,0,0,false,false,"rls_cond_order","id"),
    new ColumnSpec("tstamp",java.sql.Types.TIMESTAMP,9,0,false,false,null,null),
  };
  private static final ColumnSpec rls_price_cond_columns[] = {
    new ColumnSpec("cond_ord_id",java.sql.Types.NUMERIC,0,0,false,true,"rls_cond_order","id"),
    new ColumnSpec("price",java.sql.Types.NUMERIC,0,0,false,false,null,null),
    new ColumnSpec("direction",java.sql.Types.NUMERIC,0,0,false,false,null,null),
    new ColumnSpec("prod_id",java.sql.Types.NUMERIC,0,0,false,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("oms_con_order_request",oms_con_order_request_columns,null),
    new TableSpec("rls_con_order_hit_notify",rls_con_order_hit_notify_columns,null),
    new TableSpec("rls_manual_submit_history",rls_manual_submit_history_columns,null),
    new TableSpec("rls_order_miss",rls_order_miss_columns,null),
    new TableSpec("rls_account",rls_account_columns,null),
    new TableSpec("rls_cond_order",rls_cond_order_columns,null),
    new TableSpec("rls_oms_order",rls_oms_order_columns,null),
    new TableSpec("rls_price_cond",rls_price_cond_columns,null),
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
