head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.15;	author zhang-tengyu;	state Exp;
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
@package webbroker3.xbruito.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec host_ruito_order_accept_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_ruito_order_cancel_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("course",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("plan",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("cancel_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_ruito_cancel_accept_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_mrf_order_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("mrf_fund_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("return_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("tax_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_order_amount",java.sql.Types.NUMERIC,11,0,true,false,null,null),
    new ColumnSpec("return_method",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_ruito_order_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("course",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("plan",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("pay_amount",java.sql.Types.NUMERIC,11,0,true,false,null,null),
    new ColumnSpec("settle_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("pay_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("transfer_comm",java.sql.Types.NUMERIC,5,0,true,false,null,null),
    new ColumnSpec("product",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("transfer_route",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("tax_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("conv_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("partner",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("pay_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_ruito_order_notify_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("bizdate_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("course",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("plan",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("amount",java.sql.Types.NUMERIC,11,0,true,false,null,null),
    new ColumnSpec("payment_method",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_rcv_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_cancel_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_cancel_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("error_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("specify_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("channel",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_sell_cancel_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("course",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("plan",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("loan_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("product_issue_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("cancel_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_mrf_order_cancel_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("mrf_fund_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("cancel_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_mrf_cancel_accept_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_mrf_order_accept_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec host_ruito_sell_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("course",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("plan",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("loan_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("product_issue_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("tax_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("return_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("amount",java.sql.Types.NUMERIC,11,0,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.NUMERIC,11,0,true,false,null,null),
    new ColumnSpec("mortgage_sell",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("host_ruito_order_accept",host_ruito_order_accept_columns,null),
    new TableSpec("host_ruito_order_cancel",host_ruito_order_cancel_columns,null),
    new TableSpec("host_ruito_cancel_accept",host_ruito_cancel_accept_columns,null),
    new TableSpec("host_mrf_order",host_mrf_order_columns,null),
    new TableSpec("host_ruito_order",host_ruito_order_columns,null),
    new TableSpec("host_ruito_order_notify",host_ruito_order_notify_columns,null),
    new TableSpec("host_sell_cancel",host_sell_cancel_columns,null),
    new TableSpec("host_mrf_order_cancel",host_mrf_order_cancel_columns,null),
    new TableSpec("host_mrf_cancel_accept",host_mrf_cancel_accept_columns,null),
    new TableSpec("host_mrf_order_accept",host_mrf_order_accept_columns,null),
    new TableSpec("host_ruito_sell",host_ruito_sell_columns,null),
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
