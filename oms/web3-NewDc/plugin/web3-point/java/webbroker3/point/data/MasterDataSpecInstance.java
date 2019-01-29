head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.50.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.point.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec point_category_master_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("category_no",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("category_name",java.sql.Types.VARCHAR,80,0,false,false,null,null),
    new ColumnSpec("category_outline",java.sql.Types.VARCHAR,400,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec point_convert_master_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("fund_type",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("tran_type",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("buy_sell_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("convert_base",java.sql.Types.NUMERIC,7,2,true,false,null,null),
    new ColumnSpec("convert_value",java.sql.Types.NUMERIC,7,2,true,false,null,null),
    new ColumnSpec("special_term_start",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("special_term_end",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("special_convert_base",java.sql.Types.NUMERIC,7,2,true,false,null,null),
    new ColumnSpec("special_convert_value",java.sql.Types.NUMERIC,7,2,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec point_premium_master_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,"point_category_master","institution_code"),
    new ColumnSpec("category_no",java.sql.Types.NUMERIC,6,0,false,false,"point_category_master","category_no"),
    new ColumnSpec("premium_no",java.sql.Types.VARCHAR,5,0,false,true,null,null),
    new ColumnSpec("premium_name",java.sql.Types.VARCHAR,80,0,false,false,null,null),
    new ColumnSpec("required_point",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("start_date_time",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("end_date_time",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec point_total_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("period",java.sql.Types.VARCHAR,6,0,false,true,null,null),
    new ColumnSpec("total_get_point",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("total_apply_point",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("total_adjust_point",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("withdrawn_apply_point",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("withdrawn_adjust_point",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec point_trade_data_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("work_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("buy_sell_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("fund_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("fund_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("tran_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("comm_amt",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("get_point",java.sql.Types.NUMERIC,8,2,true,false,null,null),
    new ColumnSpec("proc_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("exec_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec point_apply_columns[] = {
    new ColumnSpec("apply_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("premium_no",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("used_point",java.sql.Types.NUMERIC,8,0,true,false,null,null),
    new ColumnSpec("apply_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("apply_root_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("apply_accept_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("apply_accept_user",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("apply_accept_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("apply_cancel_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("apply_cancel_user",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("apply_cancel_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec point_adjust_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("adjust_point",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec point_term_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("term_of_validity",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("display_period",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec orix_trade_bonus_plan_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("apply_month_curr",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("trd_point_curr",java.sql.Types.NUMERIC,3,0,true,false,null,null),
    new ColumnSpec("cmp_point_curr",java.sql.Types.NUMERIC,3,0,true,false,null,null),
    new ColumnSpec("cut_rate_curr",java.sql.Types.VARCHAR,32,0,true,false,null,null),
    new ColumnSpec("apply_month_next",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("trd_point_next",java.sql.Types.NUMERIC,3,0,true,false,null,null),
    new ColumnSpec("cmp_point_next",java.sql.Types.NUMERIC,3,0,true,false,null,null),
    new ColumnSpec("cut_rate_next",java.sql.Types.VARCHAR,32,0,true,false,null,null),
    new ColumnSpec("sub_acc_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("free_term_from",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("free_term_to",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("point_category_master",point_category_master_columns,"inv=0"),
    new TableSpec("point_convert_master",point_convert_master_columns,"inv=0"),
    new TableSpec("point_premium_master",point_premium_master_columns,"inv=0"),
    new TableSpec("point_total",point_total_columns,"inv=0"),
    new TableSpec("point_trade_data",point_trade_data_columns,"inv=0"),
    new TableSpec("point_apply",point_apply_columns,"inv=0"),
    new TableSpec("point_adjust",point_adjust_columns,"inv=0"),
    new TableSpec("point_term",point_term_columns,"inv=0"),
    new TableSpec("orix_trade_bonus_plan",orix_trade_bonus_plan_columns,"inv=0"),
  };

  private static final MasterDataSpecInstance instance = new MasterDataSpecInstance();

  private MasterDataSpecInstance() {
    super( tables );
  }

  public static DataSpec getInstance() {
    return instance;
  };

}
@
