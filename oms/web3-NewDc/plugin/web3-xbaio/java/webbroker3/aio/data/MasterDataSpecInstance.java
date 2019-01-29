head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec comp_bank_condition_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("pay_scheme_id",java.sql.Types.VARCHAR,11,0,false,true,"cooperate_bank_master","pay_scheme_id"),
    new ColumnSpec("max_amount_daily",java.sql.Types.NUMERIC,12,0,true,false,null,null),
    new ColumnSpec("max_amount_once",java.sql.Types.NUMERIC,12,0,true,false,null,null),
    new ColumnSpec("min_amount_once",java.sql.Types.NUMERIC,12,0,true,false,null,null),
    new ColumnSpec("amount_unit",java.sql.Types.NUMERIC,12,0,true,false,null,null),
    new ColumnSpec("max_count",java.sql.Types.NUMERIC,3,0,true,false,null,null),
    new ColumnSpec("shop_id",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("access_key",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("sonar_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("payment_method",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("complete_flag",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("limit_time",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("suspension_start_time",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("suspension_end_time",java.sql.Types.VARCHAR,6,0,true,false,null,null),
  };
  private static final ColumnSpec cooperate_bank_master_columns[] = {
    new ColumnSpec("pay_scheme_id",java.sql.Types.VARCHAR,11,0,false,true,null,null),
    new ColumnSpec("name",java.sql.Types.VARCHAR,40,0,false,false,null,null),
    new ColumnSpec("short_name",java.sql.Types.VARCHAR,40,0,false,false,null,null),
  };
  private static final ColumnSpec other_order_executed_count_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("record_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("product_div",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("pay_scheme_id",java.sql.Types.VARCHAR,11,0,false,true,null,null),
    new ColumnSpec("order_chanel",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("biz_date",java.sql.Types.VARCHAR,8,0,false,true,null,null),
    new ColumnSpec("buy_order_count",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("sell_order_count",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("execute_count",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("change_count",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("cancel_count",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("expire_count",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec amount_range_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("fund_type",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("transaction_type",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("max_amount",java.sql.Types.NUMERIC,11,0,true,false,null,null),
    new ColumnSpec("min_amount",java.sql.Types.NUMERIC,11,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec comp_bank_career_management_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("pay_scheme_id",java.sql.Types.VARCHAR,11,0,false,true,null,null),
    new ColumnSpec("career_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("mg_flg",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_update_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_update_user",java.sql.Types.VARCHAR,20,0,true,false,null,null),
  };
  private static final ColumnSpec career_shop_id_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("career_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("shop_id",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("pf_url",java.sql.Types.VARCHAR,200,0,false,false,null,null),
    new ColumnSpec("return_url",java.sql.Types.VARCHAR,200,0,false,false,null,null),
    new ColumnSpec("last_update_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_update_user",java.sql.Types.VARCHAR,20,0,true,false,null,null),
  };
  private static final ColumnSpec security_product_columns[] = {
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,"product","product_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("estimation_ratio",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("fit_flg",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("apply_term_from",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("apply_term_to",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("reason",java.sql.Types.VARCHAR,200,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec fx_account_columns[] = {
    new ColumnSpec("fx_account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("fx_system_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("fx_account_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("fx_last_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("fx_first_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("fx_mail_address",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("fx_login_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec fx_account_code_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("fx_system_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("fx_course_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("fx_account_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec comp_fx_condition_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("fx_system_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("group_name",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("url",java.sql.Types.VARCHAR,200,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("fx_head_of_login_id",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("single_sign_on_url",java.sql.Types.VARCHAR,200,0,true,false,null,null),
    new ColumnSpec("valid_term",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("fx_system_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("ext_connect_system_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("trading_time_type",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("online_acc_open",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("soap_connect_div",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("acc_type",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("mrf_allow_div",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("acc_open_real_update",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("question_check_div",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("fx_system_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("get_transferable_amt_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
  };
  private static final ColumnSpec fx_unnecessary_explanation_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("fx_serial_no",java.sql.Types.NUMERIC,8,0,false,true,null,null),
    new ColumnSpec("fx_valid_flag",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec fx_transfer_master_columns[] = {
    new ColumnSpec("fx_system_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("transfer_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("delivery_date_div",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("remark_code",java.sql.Types.VARCHAR,4,0,false,false,null,null),
    new ColumnSpec("remark_name",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("io_list_trade_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec sec_receipt_delivery_action_columns[] = {
    new ColumnSpec("sec_receipt_delivery_action_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("work_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("execute_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("record_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("product_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("product_group",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("name_method_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("remarks_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("custdy_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("deal_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("io_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("io_group",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sec_receipt_delivery_group",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("market_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("executed_price",java.sql.Types.NUMERIC,10,2,true,false,null,null),
    new ColumnSpec("executed_amount",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("quantity_unit",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("delivery_method",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("foreign_sec_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("settlement_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("exchange_rate",java.sql.Types.NUMERIC,15,8,true,false,null,null),
    new ColumnSpec("account_name",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("product_name",java.sql.Types.VARCHAR,100,0,true,false,null,null),
    new ColumnSpec("specific_account_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("delivery_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec feq_account_columns[] = {
    new ColumnSpec("feq_account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("last_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("first_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("feq_account_code",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("account_open_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("first_transfer_flag",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("comp_bank_condition",comp_bank_condition_columns,"inv=0"),
    new TableSpec("cooperate_bank_master",cooperate_bank_master_columns,"inv=0"),
    new TableSpec("other_order_executed_count",other_order_executed_count_columns,"inv=0"),
    new TableSpec("amount_range",amount_range_columns,"inv=0"),
    new TableSpec("comp_bank_career_management",comp_bank_career_management_columns,"inv=0"),
    new TableSpec("career_shop_id",career_shop_id_columns,"inv=0"),
    new TableSpec("security_product",security_product_columns,"inv=0"),
    new TableSpec("fx_account",fx_account_columns,"inv=0"),
    new TableSpec("fx_account_code",fx_account_code_columns,"inv=0"),
    new TableSpec("comp_fx_condition",comp_fx_condition_columns,"inv=0"),
    new TableSpec("fx_unnecessary_explanation",fx_unnecessary_explanation_columns,"inv=0"),
    new TableSpec("fx_transfer_master",fx_transfer_master_columns,"inv=0"),
    new TableSpec("sec_receipt_delivery_action",sec_receipt_delivery_action_columns,"inv=0"),
    new TableSpec("feq_account",feq_account_columns,"inv=0"),
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
