head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.37;	author zhang-tengyu;	state Exp;
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
@package webbroker3.mf.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec host_xbmf_cancel_accept_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,true,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_xbmf_order_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("specify_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("settlement_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_order_quantity",java.sql.Types.NUMERIC,13,0,true,false,null,null),
    new ColumnSpec("buy_order_quantity",java.sql.Types.NUMERIC,13,0,true,false,null,null),
    new ColumnSpec("ticket_number",java.sql.Types.NUMERIC,4,0,true,false,null,null),
    new ColumnSpec("pr_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("commission_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("deposit_check_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,true,null,null),
    new ColumnSpec("create_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("create_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("swt_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("swt_product_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("swt_tax_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("claim_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_chanel",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("payment_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec host_xbmf_order_cancel_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("cancel_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,true,null,null),
    new ColumnSpec("order_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_xbmf_order_accept_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,true,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_xbmf_order_notify_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_date_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,true,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trade_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("specify_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("pr_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("settlement_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("commission_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("deposit_check_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("estimated_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("constant_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("estimated_unit",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("create_datetime",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("biz_datetime",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_cancel_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_cancel_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("sonar_fund_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("delivery_method",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("check_flag",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("swt_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("swt_product_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("swt_tax_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("claim_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("input_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("payment_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("cpu_no",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec host_frgn_mmf_order_accept_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,true,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_frgn_mmf_order_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("specify_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_order_quantity",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("buy_order_quantity",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ticket_number",java.sql.Types.NUMERIC,4,0,true,false,null,null),
    new ColumnSpec("buy_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("settlement_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("execute_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,true,null,null),
    new ColumnSpec("create_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("create_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec mutual_fund_exchange_rate_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("currency_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("exec_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("tt_selling_rate",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("tt_buying_rate",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("exchange_calc_unit",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec mf_fixed_buying_cond_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,true,null,null),
    new ColumnSpec("draw_date",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("monthly_buy_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("increase_buy_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("valid_start_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec mf_fixed_buying_draw_account_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("fin_institution_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("fin_institution_code",java.sql.Types.VARCHAR,4,0,false,false,null,null),
    new ColumnSpec("fin_branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("deposit_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("draw_account_no",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("draw_account_name_kana",java.sql.Types.VARCHAR,30,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec mf_fixed_buying_change_hist_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,true,null,null),
    new ColumnSpec("valid_start_date",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("serial_no",java.sql.Types.NUMERIC,8,0,false,true,null,null),
    new ColumnSpec("monthly_buy_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("increase_buy_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("fin_draw_increase_buy_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("change_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("biz_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_chanel",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec mf_fixed_buying_change_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,true,null,null),
    new ColumnSpec("valid_start_date",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("monthly_buy_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("increase_buy_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("fin_draw_increase_buy_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("change_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("biz_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_chanel",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("host_xbmf_cancel_accept",host_xbmf_cancel_accept_columns,"inv=0"),
    new TableSpec("host_xbmf_order",host_xbmf_order_columns,"inv=0"),
    new TableSpec("host_xbmf_order_cancel",host_xbmf_order_cancel_columns,"inv=0"),
    new TableSpec("host_xbmf_order_accept",host_xbmf_order_accept_columns,"inv=0"),
    new TableSpec("host_xbmf_order_notify",host_xbmf_order_notify_columns,"inv=0"),
    new TableSpec("host_frgn_mmf_order_accept",host_frgn_mmf_order_accept_columns,"inv=0"),
    new TableSpec("host_frgn_mmf_order",host_frgn_mmf_order_columns,"inv=0"),
    new TableSpec("mutual_fund_exchange_rate",mutual_fund_exchange_rate_columns,"inv=0"),
    new TableSpec("mf_fixed_buying_cond",mf_fixed_buying_cond_columns,"inv=0"),
    new TableSpec("mf_fixed_buying_draw_account",mf_fixed_buying_draw_account_columns,"inv=0"),
    new TableSpec("mf_fixed_buying_change_hist",mf_fixed_buying_change_hist_columns,"inv=0"),
    new TableSpec("mf_fixed_buying_change",mf_fixed_buying_change_columns,"inv=0"),
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
