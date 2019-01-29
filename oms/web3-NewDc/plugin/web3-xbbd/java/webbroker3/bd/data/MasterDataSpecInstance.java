head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec custodian_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("custodian_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("custodian_name",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec bond_auto_exec_limit_action_columns[] = {
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,"bond_product","product_id"),
    new ColumnSpec("execution_update_date",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("auto_exec_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("auto_exec_limit",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("online_disp_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec bond_product_coupon_columns[] = {
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,"bond_product","product_id"),
    new ColumnSpec("interest_payment_day",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("coupon",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec accrued_interest_calc_cond_columns[] = {
    new ColumnSpec("accrued_interest_calc_type",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("calc_type_name",java.sql.Types.VARCHAR,60,0,false,false,null,null),
    new ColumnSpec("base_date_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("base_days_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("elapsed_days_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("non_elapsed_days_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("taxation_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("calc_base_form",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("unit_price_scale",java.sql.Types.NUMERIC,2,0,true,false,null,null),
    new ColumnSpec("unit_round_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("amount_round_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec bond_branch_condition_columns[] = {
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("enforce_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("asset_check_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_lock_use_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("payment_date_set_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("branch_recruit_limit_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec bond_issue_coupon_type_columns[] = {
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("issue_coupon_type",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec bond_order_accept_action_columns[] = {
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("order_accept_date",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("order_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("order_count",java.sql.Types.NUMERIC,8,0,true,false,null,null),
    new ColumnSpec("total_order_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec bond_branch_recruit_limit_columns[] = {
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("recruit_limit",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("custodian",custodian_columns,"inv=0"),
    new TableSpec("bond_auto_exec_limit_action",bond_auto_exec_limit_action_columns,"inv=0"),
    new TableSpec("bond_product_coupon",bond_product_coupon_columns,"inv=0"),
    new TableSpec("accrued_interest_calc_cond",accrued_interest_calc_cond_columns,"inv=0"),
    new TableSpec("bond_branch_condition",bond_branch_condition_columns,"inv=0"),
    new TableSpec("bond_issue_coupon_type",bond_issue_coupon_type_columns,"inv=0"),
    new TableSpec("bond_order_accept_action",bond_order_accept_action_columns,"inv=0"),
    new TableSpec("bond_branch_recruit_limit",bond_branch_recruit_limit_columns,"inv=0"),
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
