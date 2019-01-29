head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.38;	author zhang-tengyu;	state Exp;
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
@package webbroker3.mf.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec mutual_fund_product_category_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("category_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("category_name",java.sql.Types.VARCHAR,100,0,false,false,null,null),
    new ColumnSpec("parent_category_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec mutual_fund_2nd_product_sonar_columns[] = {
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("work_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("reg_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_update_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("reg_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("product_name_kana",java.sql.Types.VARCHAR,16,0,true,false,null,null),
    new ColumnSpec("product_name_kanji",java.sql.Types.VARCHAR,26,0,true,false,null,null),
    new ColumnSpec("recruit_unit",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("dealing_unit_qty",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("recruit_min_qty",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("buy_min_qty",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("sell_min_qty",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("swt_min_qty",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("recruit_unit_qty",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("buy_unit_qty",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("sell_unit_qty",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("swt_unit_qty",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("recruit_min_amt",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("buy_min_amt",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("sell_min_amt",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("swt_min_amt",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("recruit_unit_amt",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("buy_unit_amt",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("sell_unit_amt",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("swt_unit_amt",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("recruit_qty_spec_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("recruit_amt_spec_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_qty_spec_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_amt_spec_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_qty_spec_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_amt_spec_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("swt_qty_spec_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("swt_amt_spec_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("input_unit_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("cal_price_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_exception_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("swt_trade_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("swt_group",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("swt_companion_subject_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_disable_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("swt_perference_enable_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("redemption_perference_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("redemption_commission_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("pre_redemption_begin_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("closing_date_cal_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("closing_spec_date",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("annual_profit_qty_times",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("sell_advance_order_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_advance_order_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_undelivering_term",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("buy_undelivering_term",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("recruit_stop_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("dealing_stop_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("storage_stop_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("specific_corpus_app",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("perference_qualify",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("collateral_qualify",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("operate_report_send_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("operate_report_send_month1",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("operate_report_send_month2",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("biz_asset_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("biz_asset_name",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("prospectus_conversion_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("frgn_buy_min_amt",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("frgn_sell_min_amt",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("frgn_buy_unit_amt",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("frgn_sell_unit_amt",java.sql.Types.NUMERIC,9,0,true,false,null,null),
  };
  private static final ColumnSpec mutual_fund_product_sonar_columns[] = {
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("product_name_kana",java.sql.Types.VARCHAR,32,0,true,false,null,null),
    new ColumnSpec("product_name_kanji",java.sql.Types.VARCHAR,32,0,true,false,null,null),
    new ColumnSpec("product_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("work_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("effect_generating_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("invalid_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("closing_date1",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("closing_date2",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("redemption_extend_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("redemption_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("first_recruitment_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("recruit_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("recruit_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("recruit_price",java.sql.Types.NUMERIC,5,0,true,false,null,null),
    new ColumnSpec("payment_start_date1",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("payment_start_date2",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("storage_stop_flag",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("trade_stop_flag",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("obliterate_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("corpus_price",java.sql.Types.NUMERIC,5,0,true,false,null,null),
    new ColumnSpec("open_close_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("dayreport_product_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("recruit_sales",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stock_type_bond_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("contract_institution_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("purchs_deduction_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("spot_closing_date1",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("spot_closing_date2",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("calc_unit",java.sql.Types.NUMERIC,7,0,true,false,null,null),
    new ColumnSpec("biz_asset_product_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("biz_asset_evaluate_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("profit_balance_confirm_data",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("profit_term_quantity",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("general_profit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("spcprofit_distribution_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("taxinlots_aftertax_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("taxaggregate_aftertax_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("pay_start_date_advanced_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("method_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("setting_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("sell_forbidden_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("adding_forbidden_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("profit_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("best_exception_product_flag",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("currency_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("profit_distribution_regdate",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("consign_contact_product_code",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("mutualassoc_product_code",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("trust_bank_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("consign_institution_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("average_trust_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("same_check_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("same_div",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("recruit_short_swt_check_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_short_swt_check_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_short_swt_check_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("recruit_start_stop",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("collateral_qualified_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("collateral_evaluation",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("collateral_ratio",java.sql.Types.DECIMAL,18,6,true,false,null,null),
  };
  private static final ColumnSpec mutual_fund_inst_cond_sonar_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,true,null,null),
    new ColumnSpec("reg_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_accept_limit_time_usual",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("order_accept_limit_time_half",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("buy_forbid_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_forbid_end_date",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("sell_forbid_end_date",java.sql.Types.VARCHAR,8,0,true,false,null,null),
  };
  private static final ColumnSpec mutual_fund_inst_commission_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,true,null,null),
    new ColumnSpec("deal_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("order_chanel",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("valid_date_from",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("valid_date_to",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("commission_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("unit_count",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("amount_from_01",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("amount_to_01",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("commission_price_rate_01",java.sql.Types.DECIMAL,7,2,true,false,null,null),
    new ColumnSpec("amount_from_02",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("amount_to_02",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("commission_price_rate_02",java.sql.Types.DECIMAL,7,2,true,false,null,null),
    new ColumnSpec("amount_from_03",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("amount_to_03",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("commission_price_rate_03",java.sql.Types.DECIMAL,7,2,true,false,null,null),
    new ColumnSpec("amount_from_04",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("amount_to_04",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("commission_price_rate_04",java.sql.Types.DECIMAL,7,2,true,false,null,null),
    new ColumnSpec("amount_from_05",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("amount_to_05",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("commission_price_rate_05",java.sql.Types.DECIMAL,7,2,true,false,null,null),
    new ColumnSpec("amount_from_06",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("amount_to_06",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("commission_price_rate_06",java.sql.Types.DECIMAL,7,2,true,false,null,null),
    new ColumnSpec("amount_from_07",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("amount_to_07",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("commission_price_rate_07",java.sql.Types.DECIMAL,7,2,true,false,null,null),
    new ColumnSpec("amount_from_08",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("amount_to_08",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("commission_price_rate_08",java.sql.Types.DECIMAL,7,2,true,false,null,null),
    new ColumnSpec("amount_from_09",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("amount_to_09",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("commission_price_rate_09",java.sql.Types.DECIMAL,7,2,true,false,null,null),
    new ColumnSpec("amount_from_10",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("amount_to_10",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("commission_price_rate_10",java.sql.Types.DECIMAL,7,2,true,false,null,null),
    new ColumnSpec("open_date_from",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("open_date_to",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("collection_rate",java.sql.Types.DECIMAL,9,6,true,false,null,null),
    new ColumnSpec("regist_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec mf_exemption_account_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("exemption_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec mutual_fund_frgncal_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,20,0,false,true,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,true,null,null),
    new ColumnSpec("holiday",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec mutual_fund_day_balance_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("work_day",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("rec_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("trade_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("include_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("account_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("bal_quantity",java.sql.Types.NUMERIC,12,0,true,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("mutual_fund_product_category",mutual_fund_product_category_columns,"inv=0"),
    new TableSpec("mutual_fund_2nd_product_sonar",mutual_fund_2nd_product_sonar_columns,"inv=0"),
    new TableSpec("mutual_fund_product_sonar",mutual_fund_product_sonar_columns,"inv=0"),
    new TableSpec("mutual_fund_inst_cond_sonar",mutual_fund_inst_cond_sonar_columns,"inv=0"),
    new TableSpec("mutual_fund_inst_commission",mutual_fund_inst_commission_columns,"inv=0"),
    new TableSpec("mf_exemption_account",mf_exemption_account_columns,"inv=0"),
    new TableSpec("mutual_fund_frgncal",mutual_fund_frgncal_columns,"inv=0"),
    new TableSpec("mutual_fund_day_balance",mutual_fund_day_balance_columns,"inv=0"),
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
