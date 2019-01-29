head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.08.49.18;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5cc4d8b0586158f;
filename	AccountDataSpecInstance.java;

1.1
date	2011.03.14.05.22.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AccountDataSpecInstance.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class AccountDataSpecInstance extends DataSpec {

  private static final ColumnSpec insider_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,true,"branch","branch_id"),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,"product","product_id"),
    new ColumnSpec("relation_div",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("officer_name",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("post_name",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("regist_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("sonar_created_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec account_product_order_stop_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,true,"branch","branch_id"),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("apply_start_date",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("apply_end_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("stop_trade_reason",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("stop_trade_div_buy_cash",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_trade_div_sell_cash",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_trade_div_long_margin",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_trade_div_short_margin",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_div_long_close_margin",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_div_short_close_margin",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_div_long_swap_margin",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_div_short_swap_margin",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_div_buy_mini_stock",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stop_div_sell_mini_stock",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec account_info_mst_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("family_name_alt1",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("real_name1",java.sql.Types.VARCHAR,66,0,true,false,null,null),
    new ColumnSpec("real_name2",java.sql.Types.VARCHAR,66,0,true,false,null,null),
    new ColumnSpec("occupation_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("represent_family_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("represent_given_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("represent_family_name_alt1",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("represent_given_name_alt1",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("represent_power",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_family_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_given_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_family_name_alt1",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_given_name_alt1",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_department",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_post",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_zip_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("director_address1",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_address2",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_address3",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_era_born",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("director_born_date",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("director_corp_telephone",java.sql.Types.VARCHAR,16,0,true,false,null,null),
    new ColumnSpec("other_contact",java.sql.Types.VARCHAR,16,0,true,false,null,null),
    new ColumnSpec("office",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("office_zip_code",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("office_address",java.sql.Types.VARCHAR,100,0,true,false,null,null),
    new ColumnSpec("department",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("post",java.sql.Types.VARCHAR,36,0,true,false,null,null),
    new ColumnSpec("office_telephone",java.sql.Types.VARCHAR,16,0,true,false,null,null),
    new ColumnSpec("mobile",java.sql.Types.VARCHAR,16,0,true,false,null,null),
    new ColumnSpec("contact_priority1",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("contact_priority2",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("contact_priority3",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("nationality",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("nationality_other",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("occupation_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("fax",java.sql.Types.VARCHAR,16,0,true,false,null,null),
    new ColumnSpec("annual_income_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("asset_value_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("fund_budget_amount_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("invest_purpose_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("invest_plan_period_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_flag1",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag2",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag3",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag4",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag5",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag6",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag7",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag8",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag9",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag10",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_div1",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div2",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div3",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div4",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div5",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div6",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div7",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div8",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div9",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div10",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("interest_flag1",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag2",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag3",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag4",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag5",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag6",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag7",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag8",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag9",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag10",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("appli_motivat_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("appli_motivat_div_detail",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("use_institution_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("internet_trade_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("introduce_branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
  };
  private static final ColumnSpec exclusive_use_account_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("fin_institution_name",java.sql.Types.VARCHAR,32,0,true,false,null,null),
    new ColumnSpec("fin_branch_name",java.sql.Types.VARCHAR,32,0,true,false,null,null),
    new ColumnSpec("fin_branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("fin_account_type_name",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("fin_account_no",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("fin_account_name",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("fin_institution_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
  };
  private static final ColumnSpec order_unit_introduce_div_columns[] = {
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("introduce_branch_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("introduce_branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec security_shortage_account_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("proc_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("payment_stop_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("payment_stop_amount",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec stock_secured_loan_columns[] = {
    new ColumnSpec("stock_loan_account_code",java.sql.Types.VARCHAR,10,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("account_open_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("appli_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("account_open_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_data_reception_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("cancel_data_reception_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("close_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("y_customer_data",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("examin_lock_flag",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("branch_lock",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("mng_lock_flag",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("mng_lock_flag_advance",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("mng_lock_flag_unpay_margin",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("mng_lock_flag_short_security",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("mng_lock_flag_unsubstit_depo",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec security_cashout_restraint_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("use_enable_limit",java.sql.Types.DECIMAL,11,0,true,false,null,null),
    new ColumnSpec("cashout_restraint",java.sql.Types.DECIMAL,11,0,true,false,null,null),
    new ColumnSpec("cashout_enablie_amount",java.sql.Types.DECIMAL,11,0,true,false,null,null),
    new ColumnSpec("agree_cancel_flg",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("amount_lock_flg",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("remark",java.sql.Types.VARCHAR,200,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec doc_delivery_management_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("document_div",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,true,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("document_category",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("deemed_delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec acc_open_div_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("acc_type",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("acc_open_div",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("acc_open_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec ele_delivery_management_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("ele_del_regi_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("ele_del_regi_upd_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("trading_report_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("trading_report_reg_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("trading_report_div_upd_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("position_report_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("position_report_reg_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("position_report_div_upd_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("ope_report_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("ope_report_reg_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("ope_report_div_upd_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("ord_rul_report_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("ord_rul_rep_reg_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("ord_rul_report_div_upd_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("report_div1",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("report_reg_div1",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("report_div_upd_date1",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("report_div2",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("report_reg_div2",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("report_div_upd_date2",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("report_div3",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("report_reg_div3",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("report_div_upd_date3",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("report_div4",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("report_reg_div4",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("report_div_upd_date4",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("report_div5",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("report_reg_div5",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("report_div_upd_date5",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("insider",insider_columns,"inv=?.account_id"),
    new TableSpec("account_product_order_stop",account_product_order_stop_columns,"inv=?.account_id"),
    new TableSpec("account_info_mst",account_info_mst_columns,"inv=?.account_id"),
    new TableSpec("exclusive_use_account",exclusive_use_account_columns,"inv=?.account_id"),
    new TableSpec("order_unit_introduce_div",order_unit_introduce_div_columns,"inv=?.account_id"),
    new TableSpec("security_shortage_account",security_shortage_account_columns,"inv=?.account_id"),
    new TableSpec("stock_secured_loan",stock_secured_loan_columns,"inv=?.account_id"),
    new TableSpec("security_cashout_restraint",security_cashout_restraint_columns,"inv=?.account_id"),
    new TableSpec("doc_delivery_management",doc_delivery_management_columns,"inv=?.account_id"),
    new TableSpec("acc_open_div",acc_open_div_columns,"inv=?.account_id"),
    new TableSpec("ele_delivery_management",ele_delivery_management_columns,"inv=?.account_id"),
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


1.1
log
@*** empty log message ***
@
text
@d231 38
d281 1
@

