head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.50.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	AccountDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class AccountDataSpecInstance extends DataSpec {

  private static final ColumnSpec ipo_order_columns[] = {
    new ColumnSpec("ipo_order_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("ipo_product_id",java.sql.Types.NUMERIC,18,0,false,false,"ipo_product","ipo_product_id"),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,"branch","branch_id"),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("last_order_action_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("order_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_open_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("current_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("bookbuilding_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("bookbuilding_created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("elected_quantity",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("lot_result",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("lot_result_retry",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("substitute_priority",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("send_mail_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("offering_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("application_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("pay_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("offering_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("lot_number",java.sql.Types.VARCHAR,10,0,true,false,null,null),
  };
  private static final ColumnSpec ipo_bookbuilding_order_action_columns[] = {
    new ColumnSpec("bookbuilding_order_action_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("ipo_order_id",java.sql.Types.NUMERIC,18,0,false,false,"ipo_order","ipo_order_id"),
    new ColumnSpec("ipo_product_id",java.sql.Types.NUMERIC,18,0,false,false,"ipo_product","ipo_product_id"),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,"branch","branch_id"),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("order_action_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("order_status",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("order_open_status",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("current_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("bookbuilding_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("bookbuilding_created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("action_send_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("ipo_order",ipo_order_columns,"inv=?.account_id"),
    new TableSpec("ipo_bookbuilding_order_action",ipo_bookbuilding_order_action_columns,"inv=?.account_id"),
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
