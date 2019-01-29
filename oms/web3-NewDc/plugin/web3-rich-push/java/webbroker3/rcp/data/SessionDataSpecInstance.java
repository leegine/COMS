head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.28.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	SessionDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec rich_push_history_columns[] = {
    new ColumnSpec("serlnum",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("tpk",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("type",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("proc_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec rich_push_equity_order_accept_columns[] = {
    new ColumnSpec("tpk",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("type",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("submit_order_route_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("serlnum",java.sql.Types.NUMERIC,18,0,false,true,null,null),
  };
  private static final ColumnSpec rich_push_swap_order_accept_columns[] = {
    new ColumnSpec("tpk",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("type",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("submit_order_route_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("serlnum",java.sql.Types.NUMERIC,18,0,false,true,null,null),
  };
  private static final ColumnSpec rich_push_equity_change_cancel_columns[] = {
    new ColumnSpec("tpk",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("type",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("modified_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("modified_limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("modified_execution_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("modified_price_condition_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("modified_result",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("canmod_receipt_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("submit_order_route_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("serlnum",java.sql.Types.NUMERIC,18,0,false,true,null,null),
  };
  private static final ColumnSpec rich_push_equity_cont_columns[] = {
    new ColumnSpec("tpk",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("type",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("exec_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("exec_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("exec_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("dealed_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("submit_order_route_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("serlnum",java.sql.Types.NUMERIC,18,0,false,true,null,null),
  };
  private static final ColumnSpec rich_push_equity_lapse_columns[] = {
    new ColumnSpec("tpk",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("type",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("executed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("reason_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("close_notify_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("submit_order_route_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("serlnum",java.sql.Types.NUMERIC,18,0,false,true,null,null),
  };
  private static final ColumnSpec rich_push_ifo_order_accept_columns[] = {
    new ColumnSpec("tpk",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("type",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("underlying_product_code",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("month_of_delivery",java.sql.Types.VARCHAR,6,0,false,false,null,null),
    new ColumnSpec("derivative_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("strike_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("serlnum",java.sql.Types.NUMERIC,18,0,false,true,null,null),
  };
  private static final ColumnSpec rich_push_ifo_change_cancel_columns[] = {
    new ColumnSpec("tpk",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("type",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("modified_quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("modified_limit_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("modified_execution_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("modified_result",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("canmod_receipt_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("underlying_product_code",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("month_of_delivery",java.sql.Types.VARCHAR,6,0,false,false,null,null),
    new ColumnSpec("derivative_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("strike_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("serlnum",java.sql.Types.NUMERIC,18,0,false,true,null,null),
  };
  private static final ColumnSpec rich_push_ifo_cont_columns[] = {
    new ColumnSpec("tpk",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("type",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("exec_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("exec_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("exec_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("dealed_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("underlying_product_code",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("month_of_delivery",java.sql.Types.VARCHAR,6,0,false,false,null,null),
    new ColumnSpec("derivative_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("strike_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("serlnum",java.sql.Types.NUMERIC,18,0,false,true,null,null),
  };
  private static final ColumnSpec rich_push_ifo_lapse_columns[] = {
    new ColumnSpec("tpk",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("type",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("executed_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("reason_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("close_notify_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("underlying_product_code",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("month_of_delivery",java.sql.Types.VARCHAR,6,0,false,false,null,null),
    new ColumnSpec("derivative_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("strike_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("serlnum",java.sql.Types.NUMERIC,18,0,false,true,null,null),
  };
  private static final ColumnSpec rich_push_history_top_columns[] = {
    new ColumnSpec("tpk",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("type",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("serlnum",java.sql.Types.NUMERIC,18,0,false,true,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("rich_push_history",rich_push_history_columns,null),
    new TableSpec("rich_push_equity_order_accept",rich_push_equity_order_accept_columns,null),
    new TableSpec("rich_push_swap_order_accept",rich_push_swap_order_accept_columns,null),
    new TableSpec("rich_push_equity_change_cancel",rich_push_equity_change_cancel_columns,null),
    new TableSpec("rich_push_equity_cont",rich_push_equity_cont_columns,null),
    new TableSpec("rich_push_equity_lapse",rich_push_equity_lapse_columns,null),
    new TableSpec("rich_push_ifo_order_accept",rich_push_ifo_order_accept_columns,null),
    new TableSpec("rich_push_ifo_change_cancel",rich_push_ifo_change_cancel_columns,null),
    new TableSpec("rich_push_ifo_cont",rich_push_ifo_cont_columns,null),
    new TableSpec("rich_push_ifo_lapse",rich_push_ifo_lapse_columns,null),
    new TableSpec("rich_push_history_top",rich_push_history_top_columns,null),
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
