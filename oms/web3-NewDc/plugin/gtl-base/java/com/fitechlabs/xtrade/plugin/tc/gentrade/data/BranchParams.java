head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.39.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	BranchParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * branchテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BranchRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BranchParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BranchParams}が{@@link BranchRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchPK 
 * @@see BranchRow 
 */
public class BranchParams extends Params implements BranchRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "branch";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BranchRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BranchRow.TYPE;
  }


  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>institution_id</em>カラムの値 
   */
  public  long  institution_id;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>branch_name</em>カラムの値 
   */
  public  String  branch_name;

  /** 
   * <em>branch_name_alt1</em>カラムの値 
   */
  public  String  branch_name_alt1;

  /** 
   * <em>branch_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum  branch_type;

  /** 
   * <em>max_handling_price_ind</em>カラムの値 
   */
  public  Long  max_handling_price_ind;

  /** 
   * <em>max_handling_price_corp</em>カラムの値 
   */
  public  Long  max_handling_price_corp;

  /** 
   * <em>max_handling_price_ind_option</em>カラムの値 
   */
  public  Long  max_handling_price_ind_option;

  /** 
   * <em>max_handling_price_corp_option</em>カラムの値 
   */
  public  Long  max_handling_price_corp_option;

  /** 
   * <em>max_handling_price_ind_future</em>カラムの値 
   */
  public  Long  max_handling_price_ind_future;

  /** 
   * <em>max_handling_price_corp_future</em>カラムの値 
   */
  public  Long  max_handling_price_corp_future;

  /** 
   * <em>max_cont_price_all_ind</em>カラムの値 
   */
  public  Double  max_cont_price_all_ind;

  /** 
   * <em>max_cont_price_all_corp</em>カラムの値 
   */
  public  Double  max_cont_price_all_corp;

  /** 
   * <em>max_cont_price_product_ind</em>カラムの値 
   */
  public  Double  max_cont_price_product_ind;

  /** 
   * <em>max_cont_price_product_corp</em>カラムの値 
   */
  public  Double  max_cont_price_product_corp;

  /** 
   * <em>max_cont_price_1day_ind</em>カラムの値 
   */
  public  Double  max_cont_price_1day_ind;

  /** 
   * <em>max_cont_price_1day_corp</em>カラムの値 
   */
  public  Double  max_cont_price_1day_corp;

  /** 
   * <em>handling_market_make</em>カラムの値 
   */
  public  Integer  handling_market_make;

  /** 
   * <em>handling_not_loan_trans_stock</em>カラムの値 
   */
  public  Integer  handling_not_loan_trans_stock;

  /** 
   * <em>fin_sales_law_execution</em>カラムの値 
   */
  public  String  fin_sales_law_execution;

  /** 
   * <em>email_address</em>カラムの値 
   */
  public  String  email_address;

  /** 
   * <em>login_stop_div</em>カラムの値 
   */
  public  String  login_stop_div;

  /** 
   * <em>admin_type_id</em>カラムの値 
   */
  public  Long  admin_type_id;

  /** 
   * <em>trader_type_id</em>カラムの値 
   */
  public  Long  trader_type_id;

  /** 
   * <em>account_type_id</em>カラムの値 
   */
  public  Long  account_type_id;

  /** 
   * <em>account_group_id</em>カラムの値 
   */
  public  Long  account_group_id;

  /** 
   * <em>account_code_min</em>カラムの値 
   */
  public  Integer  account_code_min;

  /** 
   * <em>account_code_max</em>カラムの値 
   */
  public  Integer  account_code_max;

  /** 
   * <em>account_code_check_mode</em>カラムの値 
   */
  public  String  account_code_check_mode;

  /** 
   * <em>insider_default_regist_div</em>カラムの値 
   */
  public  String  insider_default_regist_div;

  /** 
   * <em>margin_sys_div</em>カラムの値 
   */
  public  String  margin_sys_div;

  /** 
   * <em>margin_gen_div</em>カラムの値 
   */
  public  String  margin_gen_div;

  /** 
   * <em>fstk_div</em>カラムの値 
   */
  public  String  fstk_div;

  /** 
   * <em>mstk_div</em>カラムの値 
   */
  public  String  mstk_div;

  /** 
   * <em>option_div</em>カラムの値 
   */
  public  String  option_div;

  /** 
   * <em>future_div</em>カラムの値 
   */
  public  String  future_div;

  /** 
   * <em>mf_div</em>カラムの値 
   */
  public  String  mf_div;

  /** 
   * <em>ruito_div</em>カラムの値 
   */
  public  String  ruito_div;

  /** 
   * <em>qualified_investor_confirm_div</em>カラムの値 
   */
  public  String  qualified_investor_confirm_div;

  /** 
   * <em>margin_deposit_rate</em>カラムの値 
   */
  public  Double  margin_deposit_rate;

  /** 
   * <em>cash_margin_deposit_rate</em>カラムの値 
   */
  public  Double  cash_margin_deposit_rate;

  /** 
   * <em>margin_maintenance_rate</em>カラムの値 
   */
  public  Double  margin_maintenance_rate;

  /** 
   * <em>min_margin_deposit</em>カラムの値 
   */
  public  Double  min_margin_deposit;

  /** 
   * <em>min_ifo_deposit</em>カラムの値 
   */
  public  Double  min_ifo_deposit;

  /** 
   * <em>calc_substitute_rate</em>カラムの値 
   */
  public  Double  calc_substitute_rate;

  /** 
   * <em>margin_sec_check_rate</em>カラムの値 
   */
  public  Double  margin_sec_check_rate;

  /** 
   * <em>short_margin_restrain_div</em>カラムの値 
   */
  public  String  short_margin_restrain_div;

  /** 
   * <em>short_margin_restrain_unit</em>カラムの値 
   */
  public  Double  short_margin_restrain_unit;

  /** 
   * <em>short_sell_order_valid_minute</em>カラムの値 
   */
  public  Integer  short_sell_order_valid_minute;

  /** 
   * <em>margin_sec_transfer_max_count</em>カラムの値 
   */
  public  Integer  margin_sec_transfer_max_count;

  /** 
   * <em>close_worng_equity_margin</em>カラムの値 
   */
  public  Integer  close_worng_equity_margin;

  /** 
   * <em>close_worng_option</em>カラムの値 
   */
  public  Integer  close_worng_option;

  /** 
   * <em>close_worng_sys_future</em>カラムの値 
   */
  public  Integer  close_worng_sys_future;

  /** 
   * <em>daily_interest_adjust_amount</em>カラムの値 
   */
  public  Double  daily_interest_adjust_amount;

  /** 
   * <em>pay_auto_calc_div</em>カラムの値 
   */
  public  String  pay_auto_calc_div;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>max_handling_price_close_div</em>カラムの値 
   */
  public  String  max_handling_price_close_div;

  /** 
   * <em>off_floor_div</em>カラムの値 
   */
  public  String  off_floor_div;

  /** 
   * <em>close_worng_feq</em>カラムの値 
   */
  public  Integer  close_worng_feq;

  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean institution_id_is_set = false;
  private boolean institution_id_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean branch_name_is_set = false;
  private boolean branch_name_is_modified = false;
  private boolean branch_name_alt1_is_set = false;
  private boolean branch_name_alt1_is_modified = false;
  private boolean branch_type_is_set = false;
  private boolean branch_type_is_modified = false;
  private boolean max_handling_price_ind_is_set = false;
  private boolean max_handling_price_ind_is_modified = false;
  private boolean max_handling_price_corp_is_set = false;
  private boolean max_handling_price_corp_is_modified = false;
  private boolean max_handling_price_ind_option_is_set = false;
  private boolean max_handling_price_ind_option_is_modified = false;
  private boolean max_handling_price_corp_option_is_set = false;
  private boolean max_handling_price_corp_option_is_modified = false;
  private boolean max_handling_price_ind_future_is_set = false;
  private boolean max_handling_price_ind_future_is_modified = false;
  private boolean max_handling_price_corp_future_is_set = false;
  private boolean max_handling_price_corp_future_is_modified = false;
  private boolean max_cont_price_all_ind_is_set = false;
  private boolean max_cont_price_all_ind_is_modified = false;
  private boolean max_cont_price_all_corp_is_set = false;
  private boolean max_cont_price_all_corp_is_modified = false;
  private boolean max_cont_price_product_ind_is_set = false;
  private boolean max_cont_price_product_ind_is_modified = false;
  private boolean max_cont_price_product_corp_is_set = false;
  private boolean max_cont_price_product_corp_is_modified = false;
  private boolean max_cont_price_1day_ind_is_set = false;
  private boolean max_cont_price_1day_ind_is_modified = false;
  private boolean max_cont_price_1day_corp_is_set = false;
  private boolean max_cont_price_1day_corp_is_modified = false;
  private boolean handling_market_make_is_set = false;
  private boolean handling_market_make_is_modified = false;
  private boolean handling_not_loan_trans_stock_is_set = false;
  private boolean handling_not_loan_trans_stock_is_modified = false;
  private boolean fin_sales_law_execution_is_set = false;
  private boolean fin_sales_law_execution_is_modified = false;
  private boolean email_address_is_set = false;
  private boolean email_address_is_modified = false;
  private boolean login_stop_div_is_set = false;
  private boolean login_stop_div_is_modified = false;
  private boolean admin_type_id_is_set = false;
  private boolean admin_type_id_is_modified = false;
  private boolean trader_type_id_is_set = false;
  private boolean trader_type_id_is_modified = false;
  private boolean account_type_id_is_set = false;
  private boolean account_type_id_is_modified = false;
  private boolean account_group_id_is_set = false;
  private boolean account_group_id_is_modified = false;
  private boolean account_code_min_is_set = false;
  private boolean account_code_min_is_modified = false;
  private boolean account_code_max_is_set = false;
  private boolean account_code_max_is_modified = false;
  private boolean account_code_check_mode_is_set = false;
  private boolean account_code_check_mode_is_modified = false;
  private boolean insider_default_regist_div_is_set = false;
  private boolean insider_default_regist_div_is_modified = false;
  private boolean margin_sys_div_is_set = false;
  private boolean margin_sys_div_is_modified = false;
  private boolean margin_gen_div_is_set = false;
  private boolean margin_gen_div_is_modified = false;
  private boolean fstk_div_is_set = false;
  private boolean fstk_div_is_modified = false;
  private boolean mstk_div_is_set = false;
  private boolean mstk_div_is_modified = false;
  private boolean option_div_is_set = false;
  private boolean option_div_is_modified = false;
  private boolean future_div_is_set = false;
  private boolean future_div_is_modified = false;
  private boolean mf_div_is_set = false;
  private boolean mf_div_is_modified = false;
  private boolean ruito_div_is_set = false;
  private boolean ruito_div_is_modified = false;
  private boolean qualified_investor_confirm_div_is_set = false;
  private boolean qualified_investor_confirm_div_is_modified = false;
  private boolean margin_deposit_rate_is_set = false;
  private boolean margin_deposit_rate_is_modified = false;
  private boolean cash_margin_deposit_rate_is_set = false;
  private boolean cash_margin_deposit_rate_is_modified = false;
  private boolean margin_maintenance_rate_is_set = false;
  private boolean margin_maintenance_rate_is_modified = false;
  private boolean min_margin_deposit_is_set = false;
  private boolean min_margin_deposit_is_modified = false;
  private boolean min_ifo_deposit_is_set = false;
  private boolean min_ifo_deposit_is_modified = false;
  private boolean calc_substitute_rate_is_set = false;
  private boolean calc_substitute_rate_is_modified = false;
  private boolean margin_sec_check_rate_is_set = false;
  private boolean margin_sec_check_rate_is_modified = false;
  private boolean short_margin_restrain_div_is_set = false;
  private boolean short_margin_restrain_div_is_modified = false;
  private boolean short_margin_restrain_unit_is_set = false;
  private boolean short_margin_restrain_unit_is_modified = false;
  private boolean short_sell_order_valid_minute_is_set = false;
  private boolean short_sell_order_valid_minute_is_modified = false;
  private boolean margin_sec_transfer_max_count_is_set = false;
  private boolean margin_sec_transfer_max_count_is_modified = false;
  private boolean close_worng_equity_margin_is_set = false;
  private boolean close_worng_equity_margin_is_modified = false;
  private boolean close_worng_option_is_set = false;
  private boolean close_worng_option_is_modified = false;
  private boolean close_worng_sys_future_is_set = false;
  private boolean close_worng_sys_future_is_modified = false;
  private boolean daily_interest_adjust_amount_is_set = false;
  private boolean daily_interest_adjust_amount_is_modified = false;
  private boolean pay_auto_calc_div_is_set = false;
  private boolean pay_auto_calc_div_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean max_handling_price_close_div_is_set = false;
  private boolean max_handling_price_close_div_is_modified = false;
  private boolean off_floor_div_is_set = false;
  private boolean off_floor_div_is_modified = false;
  private boolean close_worng_feq_is_set = false;
  private boolean close_worng_feq_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "branch_id=" + branch_id
      + "," + "institution_code=" +institution_code
      + "," + "institution_id=" +institution_id
      + "," + "branch_code=" +branch_code
      + "," + "branch_name=" +branch_name
      + "," + "branch_name_alt1=" +branch_name_alt1
      + "," + "branch_type=" +branch_type
      + "," + "max_handling_price_ind=" +max_handling_price_ind
      + "," + "max_handling_price_corp=" +max_handling_price_corp
      + "," + "max_handling_price_ind_option=" +max_handling_price_ind_option
      + "," + "max_handling_price_corp_option=" +max_handling_price_corp_option
      + "," + "max_handling_price_ind_future=" +max_handling_price_ind_future
      + "," + "max_handling_price_corp_future=" +max_handling_price_corp_future
      + "," + "max_cont_price_all_ind=" +max_cont_price_all_ind
      + "," + "max_cont_price_all_corp=" +max_cont_price_all_corp
      + "," + "max_cont_price_product_ind=" +max_cont_price_product_ind
      + "," + "max_cont_price_product_corp=" +max_cont_price_product_corp
      + "," + "max_cont_price_1day_ind=" +max_cont_price_1day_ind
      + "," + "max_cont_price_1day_corp=" +max_cont_price_1day_corp
      + "," + "handling_market_make=" +handling_market_make
      + "," + "handling_not_loan_trans_stock=" +handling_not_loan_trans_stock
      + "," + "fin_sales_law_execution=" +fin_sales_law_execution
      + "," + "email_address=" +email_address
      + "," + "login_stop_div=" +login_stop_div
      + "," + "admin_type_id=" +admin_type_id
      + "," + "trader_type_id=" +trader_type_id
      + "," + "account_type_id=" +account_type_id
      + "," + "account_group_id=" +account_group_id
      + "," + "account_code_min=" +account_code_min
      + "," + "account_code_max=" +account_code_max
      + "," + "account_code_check_mode=" +account_code_check_mode
      + "," + "insider_default_regist_div=" +insider_default_regist_div
      + "," + "margin_sys_div=" +margin_sys_div
      + "," + "margin_gen_div=" +margin_gen_div
      + "," + "fstk_div=" +fstk_div
      + "," + "mstk_div=" +mstk_div
      + "," + "option_div=" +option_div
      + "," + "future_div=" +future_div
      + "," + "mf_div=" +mf_div
      + "," + "ruito_div=" +ruito_div
      + "," + "qualified_investor_confirm_div=" +qualified_investor_confirm_div
      + "," + "margin_deposit_rate=" +margin_deposit_rate
      + "," + "cash_margin_deposit_rate=" +cash_margin_deposit_rate
      + "," + "margin_maintenance_rate=" +margin_maintenance_rate
      + "," + "min_margin_deposit=" +min_margin_deposit
      + "," + "min_ifo_deposit=" +min_ifo_deposit
      + "," + "calc_substitute_rate=" +calc_substitute_rate
      + "," + "margin_sec_check_rate=" +margin_sec_check_rate
      + "," + "short_margin_restrain_div=" +short_margin_restrain_div
      + "," + "short_margin_restrain_unit=" +short_margin_restrain_unit
      + "," + "short_sell_order_valid_minute=" +short_sell_order_valid_minute
      + "," + "margin_sec_transfer_max_count=" +margin_sec_transfer_max_count
      + "," + "close_worng_equity_margin=" +close_worng_equity_margin
      + "," + "close_worng_option=" +close_worng_option
      + "," + "close_worng_sys_future=" +close_worng_sys_future
      + "," + "daily_interest_adjust_amount=" +daily_interest_adjust_amount
      + "," + "pay_auto_calc_div=" +pay_auto_calc_div
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "max_handling_price_close_div=" +max_handling_price_close_div
      + "," + "off_floor_div=" +off_floor_div
      + "," + "close_worng_feq=" +close_worng_feq
      + "}";
  }


  /** 
   * 値が未設定のBranchParamsオブジェクトを作成します。 
   */
  public BranchParams() {
    branch_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBranchRowオブジェクトの値を利用してBranchParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBranchRowオブジェクト 
   */
  public BranchParams( BranchRow p_row )
  {
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    institution_id = p_row.getInstitutionId();
    institution_id_is_set = p_row.getInstitutionIdIsSet();
    institution_id_is_modified = p_row.getInstitutionIdIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    branch_name = p_row.getBranchName();
    branch_name_is_set = p_row.getBranchNameIsSet();
    branch_name_is_modified = p_row.getBranchNameIsModified();
    branch_name_alt1 = p_row.getBranchNameAlt1();
    branch_name_alt1_is_set = p_row.getBranchNameAlt1IsSet();
    branch_name_alt1_is_modified = p_row.getBranchNameAlt1IsModified();
    branch_type = p_row.getBranchType();
    branch_type_is_set = p_row.getBranchTypeIsSet();
    branch_type_is_modified = p_row.getBranchTypeIsModified();
    if ( !p_row.getMaxHandlingPriceIndIsNull() )
      max_handling_price_ind = new Long( p_row.getMaxHandlingPriceInd() );
    max_handling_price_ind_is_set = p_row.getMaxHandlingPriceIndIsSet();
    max_handling_price_ind_is_modified = p_row.getMaxHandlingPriceIndIsModified();
    if ( !p_row.getMaxHandlingPriceCorpIsNull() )
      max_handling_price_corp = new Long( p_row.getMaxHandlingPriceCorp() );
    max_handling_price_corp_is_set = p_row.getMaxHandlingPriceCorpIsSet();
    max_handling_price_corp_is_modified = p_row.getMaxHandlingPriceCorpIsModified();
    if ( !p_row.getMaxHandlingPriceIndOptionIsNull() )
      max_handling_price_ind_option = new Long( p_row.getMaxHandlingPriceIndOption() );
    max_handling_price_ind_option_is_set = p_row.getMaxHandlingPriceIndOptionIsSet();
    max_handling_price_ind_option_is_modified = p_row.getMaxHandlingPriceIndOptionIsModified();
    if ( !p_row.getMaxHandlingPriceCorpOptionIsNull() )
      max_handling_price_corp_option = new Long( p_row.getMaxHandlingPriceCorpOption() );
    max_handling_price_corp_option_is_set = p_row.getMaxHandlingPriceCorpOptionIsSet();
    max_handling_price_corp_option_is_modified = p_row.getMaxHandlingPriceCorpOptionIsModified();
    if ( !p_row.getMaxHandlingPriceIndFutureIsNull() )
      max_handling_price_ind_future = new Long( p_row.getMaxHandlingPriceIndFuture() );
    max_handling_price_ind_future_is_set = p_row.getMaxHandlingPriceIndFutureIsSet();
    max_handling_price_ind_future_is_modified = p_row.getMaxHandlingPriceIndFutureIsModified();
    if ( !p_row.getMaxHandlingPriceCorpFutureIsNull() )
      max_handling_price_corp_future = new Long( p_row.getMaxHandlingPriceCorpFuture() );
    max_handling_price_corp_future_is_set = p_row.getMaxHandlingPriceCorpFutureIsSet();
    max_handling_price_corp_future_is_modified = p_row.getMaxHandlingPriceCorpFutureIsModified();
    if ( !p_row.getMaxContPriceAllIndIsNull() )
      max_cont_price_all_ind = new Double( p_row.getMaxContPriceAllInd() );
    max_cont_price_all_ind_is_set = p_row.getMaxContPriceAllIndIsSet();
    max_cont_price_all_ind_is_modified = p_row.getMaxContPriceAllIndIsModified();
    if ( !p_row.getMaxContPriceAllCorpIsNull() )
      max_cont_price_all_corp = new Double( p_row.getMaxContPriceAllCorp() );
    max_cont_price_all_corp_is_set = p_row.getMaxContPriceAllCorpIsSet();
    max_cont_price_all_corp_is_modified = p_row.getMaxContPriceAllCorpIsModified();
    if ( !p_row.getMaxContPriceProductIndIsNull() )
      max_cont_price_product_ind = new Double( p_row.getMaxContPriceProductInd() );
    max_cont_price_product_ind_is_set = p_row.getMaxContPriceProductIndIsSet();
    max_cont_price_product_ind_is_modified = p_row.getMaxContPriceProductIndIsModified();
    if ( !p_row.getMaxContPriceProductCorpIsNull() )
      max_cont_price_product_corp = new Double( p_row.getMaxContPriceProductCorp() );
    max_cont_price_product_corp_is_set = p_row.getMaxContPriceProductCorpIsSet();
    max_cont_price_product_corp_is_modified = p_row.getMaxContPriceProductCorpIsModified();
    if ( !p_row.getMaxContPrice1dayIndIsNull() )
      max_cont_price_1day_ind = new Double( p_row.getMaxContPrice1dayInd() );
    max_cont_price_1day_ind_is_set = p_row.getMaxContPrice1dayIndIsSet();
    max_cont_price_1day_ind_is_modified = p_row.getMaxContPrice1dayIndIsModified();
    if ( !p_row.getMaxContPrice1dayCorpIsNull() )
      max_cont_price_1day_corp = new Double( p_row.getMaxContPrice1dayCorp() );
    max_cont_price_1day_corp_is_set = p_row.getMaxContPrice1dayCorpIsSet();
    max_cont_price_1day_corp_is_modified = p_row.getMaxContPrice1dayCorpIsModified();
    if ( !p_row.getHandlingMarketMakeIsNull() )
      handling_market_make = new Integer( p_row.getHandlingMarketMake() );
    handling_market_make_is_set = p_row.getHandlingMarketMakeIsSet();
    handling_market_make_is_modified = p_row.getHandlingMarketMakeIsModified();
    if ( !p_row.getHandlingNotLoanTransStockIsNull() )
      handling_not_loan_trans_stock = new Integer( p_row.getHandlingNotLoanTransStock() );
    handling_not_loan_trans_stock_is_set = p_row.getHandlingNotLoanTransStockIsSet();
    handling_not_loan_trans_stock_is_modified = p_row.getHandlingNotLoanTransStockIsModified();
    fin_sales_law_execution = p_row.getFinSalesLawExecution();
    fin_sales_law_execution_is_set = p_row.getFinSalesLawExecutionIsSet();
    fin_sales_law_execution_is_modified = p_row.getFinSalesLawExecutionIsModified();
    email_address = p_row.getEmailAddress();
    email_address_is_set = p_row.getEmailAddressIsSet();
    email_address_is_modified = p_row.getEmailAddressIsModified();
    login_stop_div = p_row.getLoginStopDiv();
    login_stop_div_is_set = p_row.getLoginStopDivIsSet();
    login_stop_div_is_modified = p_row.getLoginStopDivIsModified();
    if ( !p_row.getAdminTypeIdIsNull() )
      admin_type_id = new Long( p_row.getAdminTypeId() );
    admin_type_id_is_set = p_row.getAdminTypeIdIsSet();
    admin_type_id_is_modified = p_row.getAdminTypeIdIsModified();
    if ( !p_row.getTraderTypeIdIsNull() )
      trader_type_id = new Long( p_row.getTraderTypeId() );
    trader_type_id_is_set = p_row.getTraderTypeIdIsSet();
    trader_type_id_is_modified = p_row.getTraderTypeIdIsModified();
    if ( !p_row.getAccountTypeIdIsNull() )
      account_type_id = new Long( p_row.getAccountTypeId() );
    account_type_id_is_set = p_row.getAccountTypeIdIsSet();
    account_type_id_is_modified = p_row.getAccountTypeIdIsModified();
    if ( !p_row.getAccountGroupIdIsNull() )
      account_group_id = new Long( p_row.getAccountGroupId() );
    account_group_id_is_set = p_row.getAccountGroupIdIsSet();
    account_group_id_is_modified = p_row.getAccountGroupIdIsModified();
    if ( !p_row.getAccountCodeMinIsNull() )
      account_code_min = new Integer( p_row.getAccountCodeMin() );
    account_code_min_is_set = p_row.getAccountCodeMinIsSet();
    account_code_min_is_modified = p_row.getAccountCodeMinIsModified();
    if ( !p_row.getAccountCodeMaxIsNull() )
      account_code_max = new Integer( p_row.getAccountCodeMax() );
    account_code_max_is_set = p_row.getAccountCodeMaxIsSet();
    account_code_max_is_modified = p_row.getAccountCodeMaxIsModified();
    account_code_check_mode = p_row.getAccountCodeCheckMode();
    account_code_check_mode_is_set = p_row.getAccountCodeCheckModeIsSet();
    account_code_check_mode_is_modified = p_row.getAccountCodeCheckModeIsModified();
    insider_default_regist_div = p_row.getInsiderDefaultRegistDiv();
    insider_default_regist_div_is_set = p_row.getInsiderDefaultRegistDivIsSet();
    insider_default_regist_div_is_modified = p_row.getInsiderDefaultRegistDivIsModified();
    margin_sys_div = p_row.getMarginSysDiv();
    margin_sys_div_is_set = p_row.getMarginSysDivIsSet();
    margin_sys_div_is_modified = p_row.getMarginSysDivIsModified();
    margin_gen_div = p_row.getMarginGenDiv();
    margin_gen_div_is_set = p_row.getMarginGenDivIsSet();
    margin_gen_div_is_modified = p_row.getMarginGenDivIsModified();
    fstk_div = p_row.getFstkDiv();
    fstk_div_is_set = p_row.getFstkDivIsSet();
    fstk_div_is_modified = p_row.getFstkDivIsModified();
    mstk_div = p_row.getMstkDiv();
    mstk_div_is_set = p_row.getMstkDivIsSet();
    mstk_div_is_modified = p_row.getMstkDivIsModified();
    option_div = p_row.getOptionDiv();
    option_div_is_set = p_row.getOptionDivIsSet();
    option_div_is_modified = p_row.getOptionDivIsModified();
    future_div = p_row.getFutureDiv();
    future_div_is_set = p_row.getFutureDivIsSet();
    future_div_is_modified = p_row.getFutureDivIsModified();
    mf_div = p_row.getMfDiv();
    mf_div_is_set = p_row.getMfDivIsSet();
    mf_div_is_modified = p_row.getMfDivIsModified();
    ruito_div = p_row.getRuitoDiv();
    ruito_div_is_set = p_row.getRuitoDivIsSet();
    ruito_div_is_modified = p_row.getRuitoDivIsModified();
    qualified_investor_confirm_div = p_row.getQualifiedInvestorConfirmDiv();
    qualified_investor_confirm_div_is_set = p_row.getQualifiedInvestorConfirmDivIsSet();
    qualified_investor_confirm_div_is_modified = p_row.getQualifiedInvestorConfirmDivIsModified();
    if ( !p_row.getMarginDepositRateIsNull() )
      margin_deposit_rate = new Double( p_row.getMarginDepositRate() );
    margin_deposit_rate_is_set = p_row.getMarginDepositRateIsSet();
    margin_deposit_rate_is_modified = p_row.getMarginDepositRateIsModified();
    if ( !p_row.getCashMarginDepositRateIsNull() )
      cash_margin_deposit_rate = new Double( p_row.getCashMarginDepositRate() );
    cash_margin_deposit_rate_is_set = p_row.getCashMarginDepositRateIsSet();
    cash_margin_deposit_rate_is_modified = p_row.getCashMarginDepositRateIsModified();
    if ( !p_row.getMarginMaintenanceRateIsNull() )
      margin_maintenance_rate = new Double( p_row.getMarginMaintenanceRate() );
    margin_maintenance_rate_is_set = p_row.getMarginMaintenanceRateIsSet();
    margin_maintenance_rate_is_modified = p_row.getMarginMaintenanceRateIsModified();
    if ( !p_row.getMinMarginDepositIsNull() )
      min_margin_deposit = new Double( p_row.getMinMarginDeposit() );
    min_margin_deposit_is_set = p_row.getMinMarginDepositIsSet();
    min_margin_deposit_is_modified = p_row.getMinMarginDepositIsModified();
    if ( !p_row.getMinIfoDepositIsNull() )
      min_ifo_deposit = new Double( p_row.getMinIfoDeposit() );
    min_ifo_deposit_is_set = p_row.getMinIfoDepositIsSet();
    min_ifo_deposit_is_modified = p_row.getMinIfoDepositIsModified();
    if ( !p_row.getCalcSubstituteRateIsNull() )
      calc_substitute_rate = new Double( p_row.getCalcSubstituteRate() );
    calc_substitute_rate_is_set = p_row.getCalcSubstituteRateIsSet();
    calc_substitute_rate_is_modified = p_row.getCalcSubstituteRateIsModified();
    if ( !p_row.getMarginSecCheckRateIsNull() )
      margin_sec_check_rate = new Double( p_row.getMarginSecCheckRate() );
    margin_sec_check_rate_is_set = p_row.getMarginSecCheckRateIsSet();
    margin_sec_check_rate_is_modified = p_row.getMarginSecCheckRateIsModified();
    short_margin_restrain_div = p_row.getShortMarginRestrainDiv();
    short_margin_restrain_div_is_set = p_row.getShortMarginRestrainDivIsSet();
    short_margin_restrain_div_is_modified = p_row.getShortMarginRestrainDivIsModified();
    if ( !p_row.getShortMarginRestrainUnitIsNull() )
      short_margin_restrain_unit = new Double( p_row.getShortMarginRestrainUnit() );
    short_margin_restrain_unit_is_set = p_row.getShortMarginRestrainUnitIsSet();
    short_margin_restrain_unit_is_modified = p_row.getShortMarginRestrainUnitIsModified();
    if ( !p_row.getShortSellOrderValidMinuteIsNull() )
      short_sell_order_valid_minute = new Integer( p_row.getShortSellOrderValidMinute() );
    short_sell_order_valid_minute_is_set = p_row.getShortSellOrderValidMinuteIsSet();
    short_sell_order_valid_minute_is_modified = p_row.getShortSellOrderValidMinuteIsModified();
    if ( !p_row.getMarginSecTransferMaxCountIsNull() )
      margin_sec_transfer_max_count = new Integer( p_row.getMarginSecTransferMaxCount() );
    margin_sec_transfer_max_count_is_set = p_row.getMarginSecTransferMaxCountIsSet();
    margin_sec_transfer_max_count_is_modified = p_row.getMarginSecTransferMaxCountIsModified();
    if ( !p_row.getCloseWorngEquityMarginIsNull() )
      close_worng_equity_margin = new Integer( p_row.getCloseWorngEquityMargin() );
    close_worng_equity_margin_is_set = p_row.getCloseWorngEquityMarginIsSet();
    close_worng_equity_margin_is_modified = p_row.getCloseWorngEquityMarginIsModified();
    if ( !p_row.getCloseWorngOptionIsNull() )
      close_worng_option = new Integer( p_row.getCloseWorngOption() );
    close_worng_option_is_set = p_row.getCloseWorngOptionIsSet();
    close_worng_option_is_modified = p_row.getCloseWorngOptionIsModified();
    if ( !p_row.getCloseWorngSysFutureIsNull() )
      close_worng_sys_future = new Integer( p_row.getCloseWorngSysFuture() );
    close_worng_sys_future_is_set = p_row.getCloseWorngSysFutureIsSet();
    close_worng_sys_future_is_modified = p_row.getCloseWorngSysFutureIsModified();
    if ( !p_row.getDailyInterestAdjustAmountIsNull() )
      daily_interest_adjust_amount = new Double( p_row.getDailyInterestAdjustAmount() );
    daily_interest_adjust_amount_is_set = p_row.getDailyInterestAdjustAmountIsSet();
    daily_interest_adjust_amount_is_modified = p_row.getDailyInterestAdjustAmountIsModified();
    pay_auto_calc_div = p_row.getPayAutoCalcDiv();
    pay_auto_calc_div_is_set = p_row.getPayAutoCalcDivIsSet();
    pay_auto_calc_div_is_modified = p_row.getPayAutoCalcDivIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    max_handling_price_close_div = p_row.getMaxHandlingPriceCloseDiv();
    max_handling_price_close_div_is_set = p_row.getMaxHandlingPriceCloseDivIsSet();
    max_handling_price_close_div_is_modified = p_row.getMaxHandlingPriceCloseDivIsModified();
    off_floor_div = p_row.getOffFloorDiv();
    off_floor_div_is_set = p_row.getOffFloorDivIsSet();
    off_floor_div_is_modified = p_row.getOffFloorDivIsModified();
    if ( !p_row.getCloseWorngFeqIsNull() )
      close_worng_feq = new Integer( p_row.getCloseWorngFeq() );
    close_worng_feq_is_set = p_row.getCloseWorngFeqIsSet();
    close_worng_feq_is_modified = p_row.getCloseWorngFeqIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
      institution_id_is_set = true;
      institution_id_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      branch_name_is_set = true;
      branch_name_is_modified = true;
      branch_name_alt1_is_set = true;
      branch_name_alt1_is_modified = true;
      branch_type_is_set = true;
      branch_type_is_modified = true;
      max_handling_price_ind_is_set = true;
      max_handling_price_ind_is_modified = true;
      max_handling_price_corp_is_set = true;
      max_handling_price_corp_is_modified = true;
      max_handling_price_ind_option_is_set = true;
      max_handling_price_ind_option_is_modified = true;
      max_handling_price_corp_option_is_set = true;
      max_handling_price_corp_option_is_modified = true;
      max_handling_price_ind_future_is_set = true;
      max_handling_price_ind_future_is_modified = true;
      max_handling_price_corp_future_is_set = true;
      max_handling_price_corp_future_is_modified = true;
      max_cont_price_all_ind_is_set = true;
      max_cont_price_all_ind_is_modified = true;
      max_cont_price_all_corp_is_set = true;
      max_cont_price_all_corp_is_modified = true;
      max_cont_price_product_ind_is_set = true;
      max_cont_price_product_ind_is_modified = true;
      max_cont_price_product_corp_is_set = true;
      max_cont_price_product_corp_is_modified = true;
      max_cont_price_1day_ind_is_set = true;
      max_cont_price_1day_ind_is_modified = true;
      max_cont_price_1day_corp_is_set = true;
      max_cont_price_1day_corp_is_modified = true;
      handling_market_make_is_set = true;
      handling_market_make_is_modified = true;
      handling_not_loan_trans_stock_is_set = true;
      handling_not_loan_trans_stock_is_modified = true;
      fin_sales_law_execution_is_set = true;
      fin_sales_law_execution_is_modified = true;
      email_address_is_set = true;
      email_address_is_modified = true;
      login_stop_div_is_set = true;
      login_stop_div_is_modified = true;
      admin_type_id_is_set = true;
      admin_type_id_is_modified = true;
      trader_type_id_is_set = true;
      trader_type_id_is_modified = true;
      account_type_id_is_set = true;
      account_type_id_is_modified = true;
      account_group_id_is_set = true;
      account_group_id_is_modified = true;
      account_code_min_is_set = true;
      account_code_min_is_modified = true;
      account_code_max_is_set = true;
      account_code_max_is_modified = true;
      account_code_check_mode_is_set = true;
      account_code_check_mode_is_modified = true;
      insider_default_regist_div_is_set = true;
      insider_default_regist_div_is_modified = true;
      margin_sys_div_is_set = true;
      margin_sys_div_is_modified = true;
      margin_gen_div_is_set = true;
      margin_gen_div_is_modified = true;
      fstk_div_is_set = true;
      fstk_div_is_modified = true;
      mstk_div_is_set = true;
      mstk_div_is_modified = true;
      option_div_is_set = true;
      option_div_is_modified = true;
      future_div_is_set = true;
      future_div_is_modified = true;
      mf_div_is_set = true;
      mf_div_is_modified = true;
      ruito_div_is_set = true;
      ruito_div_is_modified = true;
      qualified_investor_confirm_div_is_set = true;
      qualified_investor_confirm_div_is_modified = true;
      margin_deposit_rate_is_set = true;
      margin_deposit_rate_is_modified = true;
      cash_margin_deposit_rate_is_set = true;
      cash_margin_deposit_rate_is_modified = true;
      margin_maintenance_rate_is_set = true;
      margin_maintenance_rate_is_modified = true;
      min_margin_deposit_is_set = true;
      min_margin_deposit_is_modified = true;
      min_ifo_deposit_is_set = true;
      min_ifo_deposit_is_modified = true;
      calc_substitute_rate_is_set = true;
      calc_substitute_rate_is_modified = true;
      margin_sec_check_rate_is_set = true;
      margin_sec_check_rate_is_modified = true;
      short_margin_restrain_div_is_set = true;
      short_margin_restrain_div_is_modified = true;
      short_margin_restrain_unit_is_set = true;
      short_margin_restrain_unit_is_modified = true;
      short_sell_order_valid_minute_is_set = true;
      short_sell_order_valid_minute_is_modified = true;
      margin_sec_transfer_max_count_is_set = true;
      margin_sec_transfer_max_count_is_modified = true;
      close_worng_equity_margin_is_set = true;
      close_worng_equity_margin_is_modified = true;
      close_worng_option_is_set = true;
      close_worng_option_is_modified = true;
      close_worng_sys_future_is_set = true;
      close_worng_sys_future_is_modified = true;
      daily_interest_adjust_amount_is_set = true;
      daily_interest_adjust_amount_is_modified = true;
      pay_auto_calc_div_is_set = true;
      pay_auto_calc_div_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      max_handling_price_close_div_is_set = true;
      max_handling_price_close_div_is_modified = true;
      off_floor_div_is_set = true;
      off_floor_div_is_modified = true;
      close_worng_feq_is_set = true;
      close_worng_feq_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof BranchRow ) )
       return false;
    return fieldsEqual( (BranchRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBranchRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BranchRow row )
  {
    if ( branch_id != row.getBranchId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( institution_id != row.getInstitutionId() )
      return false;
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( branch_name == null ) {
      if ( row.getBranchName() != null )
        return false;
    } else if ( !branch_name.equals( row.getBranchName() ) ) {
        return false;
    }
    if ( branch_name_alt1 == null ) {
      if ( row.getBranchNameAlt1() != null )
        return false;
    } else if ( !branch_name_alt1.equals( row.getBranchNameAlt1() ) ) {
        return false;
    }
    if ( branch_type == null ) {
      if ( row.getBranchType() != null )
        return false;
    } else if ( !branch_type.equals( row.getBranchType() ) ) {
        return false;
    }
    if ( max_handling_price_ind == null ) {
      if ( !row.getMaxHandlingPriceIndIsNull() )
        return false;
    } else if ( row.getMaxHandlingPriceIndIsNull() || ( max_handling_price_ind.longValue() != row.getMaxHandlingPriceInd() ) ) {
        return false;
    }
    if ( max_handling_price_corp == null ) {
      if ( !row.getMaxHandlingPriceCorpIsNull() )
        return false;
    } else if ( row.getMaxHandlingPriceCorpIsNull() || ( max_handling_price_corp.longValue() != row.getMaxHandlingPriceCorp() ) ) {
        return false;
    }
    if ( max_handling_price_ind_option == null ) {
      if ( !row.getMaxHandlingPriceIndOptionIsNull() )
        return false;
    } else if ( row.getMaxHandlingPriceIndOptionIsNull() || ( max_handling_price_ind_option.longValue() != row.getMaxHandlingPriceIndOption() ) ) {
        return false;
    }
    if ( max_handling_price_corp_option == null ) {
      if ( !row.getMaxHandlingPriceCorpOptionIsNull() )
        return false;
    } else if ( row.getMaxHandlingPriceCorpOptionIsNull() || ( max_handling_price_corp_option.longValue() != row.getMaxHandlingPriceCorpOption() ) ) {
        return false;
    }
    if ( max_handling_price_ind_future == null ) {
      if ( !row.getMaxHandlingPriceIndFutureIsNull() )
        return false;
    } else if ( row.getMaxHandlingPriceIndFutureIsNull() || ( max_handling_price_ind_future.longValue() != row.getMaxHandlingPriceIndFuture() ) ) {
        return false;
    }
    if ( max_handling_price_corp_future == null ) {
      if ( !row.getMaxHandlingPriceCorpFutureIsNull() )
        return false;
    } else if ( row.getMaxHandlingPriceCorpFutureIsNull() || ( max_handling_price_corp_future.longValue() != row.getMaxHandlingPriceCorpFuture() ) ) {
        return false;
    }
    if ( max_cont_price_all_ind == null ) {
      if ( !row.getMaxContPriceAllIndIsNull() )
        return false;
    } else if ( row.getMaxContPriceAllIndIsNull() || ( max_cont_price_all_ind.doubleValue() != row.getMaxContPriceAllInd() ) ) {
        return false;
    }
    if ( max_cont_price_all_corp == null ) {
      if ( !row.getMaxContPriceAllCorpIsNull() )
        return false;
    } else if ( row.getMaxContPriceAllCorpIsNull() || ( max_cont_price_all_corp.doubleValue() != row.getMaxContPriceAllCorp() ) ) {
        return false;
    }
    if ( max_cont_price_product_ind == null ) {
      if ( !row.getMaxContPriceProductIndIsNull() )
        return false;
    } else if ( row.getMaxContPriceProductIndIsNull() || ( max_cont_price_product_ind.doubleValue() != row.getMaxContPriceProductInd() ) ) {
        return false;
    }
    if ( max_cont_price_product_corp == null ) {
      if ( !row.getMaxContPriceProductCorpIsNull() )
        return false;
    } else if ( row.getMaxContPriceProductCorpIsNull() || ( max_cont_price_product_corp.doubleValue() != row.getMaxContPriceProductCorp() ) ) {
        return false;
    }
    if ( max_cont_price_1day_ind == null ) {
      if ( !row.getMaxContPrice1dayIndIsNull() )
        return false;
    } else if ( row.getMaxContPrice1dayIndIsNull() || ( max_cont_price_1day_ind.doubleValue() != row.getMaxContPrice1dayInd() ) ) {
        return false;
    }
    if ( max_cont_price_1day_corp == null ) {
      if ( !row.getMaxContPrice1dayCorpIsNull() )
        return false;
    } else if ( row.getMaxContPrice1dayCorpIsNull() || ( max_cont_price_1day_corp.doubleValue() != row.getMaxContPrice1dayCorp() ) ) {
        return false;
    }
    if ( handling_market_make == null ) {
      if ( !row.getHandlingMarketMakeIsNull() )
        return false;
    } else if ( row.getHandlingMarketMakeIsNull() || ( handling_market_make.intValue() != row.getHandlingMarketMake() ) ) {
        return false;
    }
    if ( handling_not_loan_trans_stock == null ) {
      if ( !row.getHandlingNotLoanTransStockIsNull() )
        return false;
    } else if ( row.getHandlingNotLoanTransStockIsNull() || ( handling_not_loan_trans_stock.intValue() != row.getHandlingNotLoanTransStock() ) ) {
        return false;
    }
    if ( fin_sales_law_execution == null ) {
      if ( row.getFinSalesLawExecution() != null )
        return false;
    } else if ( !fin_sales_law_execution.equals( row.getFinSalesLawExecution() ) ) {
        return false;
    }
    if ( email_address == null ) {
      if ( row.getEmailAddress() != null )
        return false;
    } else if ( !email_address.equals( row.getEmailAddress() ) ) {
        return false;
    }
    if ( login_stop_div == null ) {
      if ( row.getLoginStopDiv() != null )
        return false;
    } else if ( !login_stop_div.equals( row.getLoginStopDiv() ) ) {
        return false;
    }
    if ( admin_type_id == null ) {
      if ( !row.getAdminTypeIdIsNull() )
        return false;
    } else if ( row.getAdminTypeIdIsNull() || ( admin_type_id.longValue() != row.getAdminTypeId() ) ) {
        return false;
    }
    if ( trader_type_id == null ) {
      if ( !row.getTraderTypeIdIsNull() )
        return false;
    } else if ( row.getTraderTypeIdIsNull() || ( trader_type_id.longValue() != row.getTraderTypeId() ) ) {
        return false;
    }
    if ( account_type_id == null ) {
      if ( !row.getAccountTypeIdIsNull() )
        return false;
    } else if ( row.getAccountTypeIdIsNull() || ( account_type_id.longValue() != row.getAccountTypeId() ) ) {
        return false;
    }
    if ( account_group_id == null ) {
      if ( !row.getAccountGroupIdIsNull() )
        return false;
    } else if ( row.getAccountGroupIdIsNull() || ( account_group_id.longValue() != row.getAccountGroupId() ) ) {
        return false;
    }
    if ( account_code_min == null ) {
      if ( !row.getAccountCodeMinIsNull() )
        return false;
    } else if ( row.getAccountCodeMinIsNull() || ( account_code_min.intValue() != row.getAccountCodeMin() ) ) {
        return false;
    }
    if ( account_code_max == null ) {
      if ( !row.getAccountCodeMaxIsNull() )
        return false;
    } else if ( row.getAccountCodeMaxIsNull() || ( account_code_max.intValue() != row.getAccountCodeMax() ) ) {
        return false;
    }
    if ( account_code_check_mode == null ) {
      if ( row.getAccountCodeCheckMode() != null )
        return false;
    } else if ( !account_code_check_mode.equals( row.getAccountCodeCheckMode() ) ) {
        return false;
    }
    if ( insider_default_regist_div == null ) {
      if ( row.getInsiderDefaultRegistDiv() != null )
        return false;
    } else if ( !insider_default_regist_div.equals( row.getInsiderDefaultRegistDiv() ) ) {
        return false;
    }
    if ( margin_sys_div == null ) {
      if ( row.getMarginSysDiv() != null )
        return false;
    } else if ( !margin_sys_div.equals( row.getMarginSysDiv() ) ) {
        return false;
    }
    if ( margin_gen_div == null ) {
      if ( row.getMarginGenDiv() != null )
        return false;
    } else if ( !margin_gen_div.equals( row.getMarginGenDiv() ) ) {
        return false;
    }
    if ( fstk_div == null ) {
      if ( row.getFstkDiv() != null )
        return false;
    } else if ( !fstk_div.equals( row.getFstkDiv() ) ) {
        return false;
    }
    if ( mstk_div == null ) {
      if ( row.getMstkDiv() != null )
        return false;
    } else if ( !mstk_div.equals( row.getMstkDiv() ) ) {
        return false;
    }
    if ( option_div == null ) {
      if ( row.getOptionDiv() != null )
        return false;
    } else if ( !option_div.equals( row.getOptionDiv() ) ) {
        return false;
    }
    if ( future_div == null ) {
      if ( row.getFutureDiv() != null )
        return false;
    } else if ( !future_div.equals( row.getFutureDiv() ) ) {
        return false;
    }
    if ( mf_div == null ) {
      if ( row.getMfDiv() != null )
        return false;
    } else if ( !mf_div.equals( row.getMfDiv() ) ) {
        return false;
    }
    if ( ruito_div == null ) {
      if ( row.getRuitoDiv() != null )
        return false;
    } else if ( !ruito_div.equals( row.getRuitoDiv() ) ) {
        return false;
    }
    if ( qualified_investor_confirm_div == null ) {
      if ( row.getQualifiedInvestorConfirmDiv() != null )
        return false;
    } else if ( !qualified_investor_confirm_div.equals( row.getQualifiedInvestorConfirmDiv() ) ) {
        return false;
    }
    if ( margin_deposit_rate == null ) {
      if ( !row.getMarginDepositRateIsNull() )
        return false;
    } else if ( row.getMarginDepositRateIsNull() || ( margin_deposit_rate.doubleValue() != row.getMarginDepositRate() ) ) {
        return false;
    }
    if ( cash_margin_deposit_rate == null ) {
      if ( !row.getCashMarginDepositRateIsNull() )
        return false;
    } else if ( row.getCashMarginDepositRateIsNull() || ( cash_margin_deposit_rate.doubleValue() != row.getCashMarginDepositRate() ) ) {
        return false;
    }
    if ( margin_maintenance_rate == null ) {
      if ( !row.getMarginMaintenanceRateIsNull() )
        return false;
    } else if ( row.getMarginMaintenanceRateIsNull() || ( margin_maintenance_rate.doubleValue() != row.getMarginMaintenanceRate() ) ) {
        return false;
    }
    if ( min_margin_deposit == null ) {
      if ( !row.getMinMarginDepositIsNull() )
        return false;
    } else if ( row.getMinMarginDepositIsNull() || ( min_margin_deposit.doubleValue() != row.getMinMarginDeposit() ) ) {
        return false;
    }
    if ( min_ifo_deposit == null ) {
      if ( !row.getMinIfoDepositIsNull() )
        return false;
    } else if ( row.getMinIfoDepositIsNull() || ( min_ifo_deposit.doubleValue() != row.getMinIfoDeposit() ) ) {
        return false;
    }
    if ( calc_substitute_rate == null ) {
      if ( !row.getCalcSubstituteRateIsNull() )
        return false;
    } else if ( row.getCalcSubstituteRateIsNull() || ( calc_substitute_rate.doubleValue() != row.getCalcSubstituteRate() ) ) {
        return false;
    }
    if ( margin_sec_check_rate == null ) {
      if ( !row.getMarginSecCheckRateIsNull() )
        return false;
    } else if ( row.getMarginSecCheckRateIsNull() || ( margin_sec_check_rate.doubleValue() != row.getMarginSecCheckRate() ) ) {
        return false;
    }
    if ( short_margin_restrain_div == null ) {
      if ( row.getShortMarginRestrainDiv() != null )
        return false;
    } else if ( !short_margin_restrain_div.equals( row.getShortMarginRestrainDiv() ) ) {
        return false;
    }
    if ( short_margin_restrain_unit == null ) {
      if ( !row.getShortMarginRestrainUnitIsNull() )
        return false;
    } else if ( row.getShortMarginRestrainUnitIsNull() || ( short_margin_restrain_unit.doubleValue() != row.getShortMarginRestrainUnit() ) ) {
        return false;
    }
    if ( short_sell_order_valid_minute == null ) {
      if ( !row.getShortSellOrderValidMinuteIsNull() )
        return false;
    } else if ( row.getShortSellOrderValidMinuteIsNull() || ( short_sell_order_valid_minute.intValue() != row.getShortSellOrderValidMinute() ) ) {
        return false;
    }
    if ( margin_sec_transfer_max_count == null ) {
      if ( !row.getMarginSecTransferMaxCountIsNull() )
        return false;
    } else if ( row.getMarginSecTransferMaxCountIsNull() || ( margin_sec_transfer_max_count.intValue() != row.getMarginSecTransferMaxCount() ) ) {
        return false;
    }
    if ( close_worng_equity_margin == null ) {
      if ( !row.getCloseWorngEquityMarginIsNull() )
        return false;
    } else if ( row.getCloseWorngEquityMarginIsNull() || ( close_worng_equity_margin.intValue() != row.getCloseWorngEquityMargin() ) ) {
        return false;
    }
    if ( close_worng_option == null ) {
      if ( !row.getCloseWorngOptionIsNull() )
        return false;
    } else if ( row.getCloseWorngOptionIsNull() || ( close_worng_option.intValue() != row.getCloseWorngOption() ) ) {
        return false;
    }
    if ( close_worng_sys_future == null ) {
      if ( !row.getCloseWorngSysFutureIsNull() )
        return false;
    } else if ( row.getCloseWorngSysFutureIsNull() || ( close_worng_sys_future.intValue() != row.getCloseWorngSysFuture() ) ) {
        return false;
    }
    if ( daily_interest_adjust_amount == null ) {
      if ( !row.getDailyInterestAdjustAmountIsNull() )
        return false;
    } else if ( row.getDailyInterestAdjustAmountIsNull() || ( daily_interest_adjust_amount.doubleValue() != row.getDailyInterestAdjustAmount() ) ) {
        return false;
    }
    if ( pay_auto_calc_div == null ) {
      if ( row.getPayAutoCalcDiv() != null )
        return false;
    } else if ( !pay_auto_calc_div.equals( row.getPayAutoCalcDiv() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    if ( max_handling_price_close_div == null ) {
      if ( row.getMaxHandlingPriceCloseDiv() != null )
        return false;
    } else if ( !max_handling_price_close_div.equals( row.getMaxHandlingPriceCloseDiv() ) ) {
        return false;
    }
    if ( off_floor_div == null ) {
      if ( row.getOffFloorDiv() != null )
        return false;
    } else if ( !off_floor_div.equals( row.getOffFloorDiv() ) ) {
        return false;
    }
    if ( close_worng_feq == null ) {
      if ( !row.getCloseWorngFeqIsNull() )
        return false;
    } else if ( row.getCloseWorngFeqIsNull() || ( close_worng_feq.intValue() != row.getCloseWorngFeq() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) branch_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) institution_id)
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (branch_name!=null? branch_name.hashCode(): 0) 
        + (branch_name_alt1!=null? branch_name_alt1.hashCode(): 0) 
        + (branch_type!=null? branch_type.hashCode(): 0) 
        + (max_handling_price_ind!=null? max_handling_price_ind.hashCode(): 0) 
        + (max_handling_price_corp!=null? max_handling_price_corp.hashCode(): 0) 
        + (max_handling_price_ind_option!=null? max_handling_price_ind_option.hashCode(): 0) 
        + (max_handling_price_corp_option!=null? max_handling_price_corp_option.hashCode(): 0) 
        + (max_handling_price_ind_future!=null? max_handling_price_ind_future.hashCode(): 0) 
        + (max_handling_price_corp_future!=null? max_handling_price_corp_future.hashCode(): 0) 
        + (max_cont_price_all_ind!=null? max_cont_price_all_ind.hashCode(): 0) 
        + (max_cont_price_all_corp!=null? max_cont_price_all_corp.hashCode(): 0) 
        + (max_cont_price_product_ind!=null? max_cont_price_product_ind.hashCode(): 0) 
        + (max_cont_price_product_corp!=null? max_cont_price_product_corp.hashCode(): 0) 
        + (max_cont_price_1day_ind!=null? max_cont_price_1day_ind.hashCode(): 0) 
        + (max_cont_price_1day_corp!=null? max_cont_price_1day_corp.hashCode(): 0) 
        + (handling_market_make!=null? handling_market_make.hashCode(): 0) 
        + (handling_not_loan_trans_stock!=null? handling_not_loan_trans_stock.hashCode(): 0) 
        + (fin_sales_law_execution!=null? fin_sales_law_execution.hashCode(): 0) 
        + (email_address!=null? email_address.hashCode(): 0) 
        + (login_stop_div!=null? login_stop_div.hashCode(): 0) 
        + (admin_type_id!=null? admin_type_id.hashCode(): 0) 
        + (trader_type_id!=null? trader_type_id.hashCode(): 0) 
        + (account_type_id!=null? account_type_id.hashCode(): 0) 
        + (account_group_id!=null? account_group_id.hashCode(): 0) 
        + (account_code_min!=null? account_code_min.hashCode(): 0) 
        + (account_code_max!=null? account_code_max.hashCode(): 0) 
        + (account_code_check_mode!=null? account_code_check_mode.hashCode(): 0) 
        + (insider_default_regist_div!=null? insider_default_regist_div.hashCode(): 0) 
        + (margin_sys_div!=null? margin_sys_div.hashCode(): 0) 
        + (margin_gen_div!=null? margin_gen_div.hashCode(): 0) 
        + (fstk_div!=null? fstk_div.hashCode(): 0) 
        + (mstk_div!=null? mstk_div.hashCode(): 0) 
        + (option_div!=null? option_div.hashCode(): 0) 
        + (future_div!=null? future_div.hashCode(): 0) 
        + (mf_div!=null? mf_div.hashCode(): 0) 
        + (ruito_div!=null? ruito_div.hashCode(): 0) 
        + (qualified_investor_confirm_div!=null? qualified_investor_confirm_div.hashCode(): 0) 
        + (margin_deposit_rate!=null? margin_deposit_rate.hashCode(): 0) 
        + (cash_margin_deposit_rate!=null? cash_margin_deposit_rate.hashCode(): 0) 
        + (margin_maintenance_rate!=null? margin_maintenance_rate.hashCode(): 0) 
        + (min_margin_deposit!=null? min_margin_deposit.hashCode(): 0) 
        + (min_ifo_deposit!=null? min_ifo_deposit.hashCode(): 0) 
        + (calc_substitute_rate!=null? calc_substitute_rate.hashCode(): 0) 
        + (margin_sec_check_rate!=null? margin_sec_check_rate.hashCode(): 0) 
        + (short_margin_restrain_div!=null? short_margin_restrain_div.hashCode(): 0) 
        + (short_margin_restrain_unit!=null? short_margin_restrain_unit.hashCode(): 0) 
        + (short_sell_order_valid_minute!=null? short_sell_order_valid_minute.hashCode(): 0) 
        + (margin_sec_transfer_max_count!=null? margin_sec_transfer_max_count.hashCode(): 0) 
        + (close_worng_equity_margin!=null? close_worng_equity_margin.hashCode(): 0) 
        + (close_worng_option!=null? close_worng_option.hashCode(): 0) 
        + (close_worng_sys_future!=null? close_worng_sys_future.hashCode(): 0) 
        + (daily_interest_adjust_amount!=null? daily_interest_adjust_amount.hashCode(): 0) 
        + (pay_auto_calc_div!=null? pay_auto_calc_div.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (max_handling_price_close_div!=null? max_handling_price_close_div.hashCode(): 0) 
        + (off_floor_div!=null? off_floor_div.hashCode(): 0) 
        + (close_worng_feq!=null? close_worng_feq.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !institution_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_id' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !branch_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_name' must be set before inserting.");
		if ( !branch_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_type' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("branch_id",new Long(branch_id));
		map.put("institution_code",institution_code);
		map.put("institution_id",new Long(institution_id));
		map.put("branch_code",branch_code);
		map.put("branch_name",branch_name);
		if ( branch_name_alt1 != null )
			map.put("branch_name_alt1",branch_name_alt1);
		map.put("branch_type",branch_type);
		if ( max_handling_price_ind != null )
			map.put("max_handling_price_ind",max_handling_price_ind);
		if ( max_handling_price_corp != null )
			map.put("max_handling_price_corp",max_handling_price_corp);
		if ( max_handling_price_ind_option != null )
			map.put("max_handling_price_ind_option",max_handling_price_ind_option);
		if ( max_handling_price_corp_option != null )
			map.put("max_handling_price_corp_option",max_handling_price_corp_option);
		if ( max_handling_price_ind_future != null )
			map.put("max_handling_price_ind_future",max_handling_price_ind_future);
		if ( max_handling_price_corp_future != null )
			map.put("max_handling_price_corp_future",max_handling_price_corp_future);
		if ( max_cont_price_all_ind != null )
			map.put("max_cont_price_all_ind",max_cont_price_all_ind);
		if ( max_cont_price_all_corp != null )
			map.put("max_cont_price_all_corp",max_cont_price_all_corp);
		if ( max_cont_price_product_ind != null )
			map.put("max_cont_price_product_ind",max_cont_price_product_ind);
		if ( max_cont_price_product_corp != null )
			map.put("max_cont_price_product_corp",max_cont_price_product_corp);
		if ( max_cont_price_1day_ind != null )
			map.put("max_cont_price_1day_ind",max_cont_price_1day_ind);
		if ( max_cont_price_1day_corp != null )
			map.put("max_cont_price_1day_corp",max_cont_price_1day_corp);
		if ( handling_market_make != null )
			map.put("handling_market_make",handling_market_make);
		if ( handling_not_loan_trans_stock != null )
			map.put("handling_not_loan_trans_stock",handling_not_loan_trans_stock);
		if ( fin_sales_law_execution != null )
			map.put("fin_sales_law_execution",fin_sales_law_execution);
		if ( email_address != null )
			map.put("email_address",email_address);
		if ( login_stop_div != null )
			map.put("login_stop_div",login_stop_div);
		if ( admin_type_id != null )
			map.put("admin_type_id",admin_type_id);
		if ( trader_type_id != null )
			map.put("trader_type_id",trader_type_id);
		if ( account_type_id != null )
			map.put("account_type_id",account_type_id);
		if ( account_group_id != null )
			map.put("account_group_id",account_group_id);
		if ( account_code_min != null )
			map.put("account_code_min",account_code_min);
		if ( account_code_max != null )
			map.put("account_code_max",account_code_max);
		if ( account_code_check_mode != null )
			map.put("account_code_check_mode",account_code_check_mode);
		if ( insider_default_regist_div_is_set )
			map.put("insider_default_regist_div",insider_default_regist_div);
		if ( margin_sys_div != null )
			map.put("margin_sys_div",margin_sys_div);
		if ( margin_gen_div != null )
			map.put("margin_gen_div",margin_gen_div);
		if ( fstk_div != null )
			map.put("fstk_div",fstk_div);
		if ( mstk_div != null )
			map.put("mstk_div",mstk_div);
		if ( option_div != null )
			map.put("option_div",option_div);
		if ( future_div != null )
			map.put("future_div",future_div);
		if ( mf_div != null )
			map.put("mf_div",mf_div);
		if ( ruito_div != null )
			map.put("ruito_div",ruito_div);
		if ( qualified_investor_confirm_div != null )
			map.put("qualified_investor_confirm_div",qualified_investor_confirm_div);
		if ( margin_deposit_rate != null )
			map.put("margin_deposit_rate",margin_deposit_rate);
		if ( cash_margin_deposit_rate != null )
			map.put("cash_margin_deposit_rate",cash_margin_deposit_rate);
		if ( margin_maintenance_rate != null )
			map.put("margin_maintenance_rate",margin_maintenance_rate);
		if ( min_margin_deposit != null )
			map.put("min_margin_deposit",min_margin_deposit);
		if ( min_ifo_deposit != null )
			map.put("min_ifo_deposit",min_ifo_deposit);
		if ( calc_substitute_rate != null )
			map.put("calc_substitute_rate",calc_substitute_rate);
		if ( margin_sec_check_rate != null )
			map.put("margin_sec_check_rate",margin_sec_check_rate);
		if ( short_margin_restrain_div != null )
			map.put("short_margin_restrain_div",short_margin_restrain_div);
		if ( short_margin_restrain_unit != null )
			map.put("short_margin_restrain_unit",short_margin_restrain_unit);
		if ( short_sell_order_valid_minute != null )
			map.put("short_sell_order_valid_minute",short_sell_order_valid_minute);
		if ( margin_sec_transfer_max_count != null )
			map.put("margin_sec_transfer_max_count",margin_sec_transfer_max_count);
		if ( close_worng_equity_margin != null )
			map.put("close_worng_equity_margin",close_worng_equity_margin);
		if ( close_worng_option != null )
			map.put("close_worng_option",close_worng_option);
		if ( close_worng_sys_future != null )
			map.put("close_worng_sys_future",close_worng_sys_future);
		if ( daily_interest_adjust_amount != null )
			map.put("daily_interest_adjust_amount",daily_interest_adjust_amount);
		if ( pay_auto_calc_div != null )
			map.put("pay_auto_calc_div",pay_auto_calc_div);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( max_handling_price_close_div != null )
			map.put("max_handling_price_close_div",max_handling_price_close_div);
		if ( off_floor_div != null )
			map.put("off_floor_div",off_floor_div);
		if ( close_worng_feq != null )
			map.put("close_worng_feq",close_worng_feq);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( institution_id_is_modified )
			map.put("institution_id",new Long(institution_id));
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( branch_name_is_modified )
			map.put("branch_name",branch_name);
		if ( branch_name_alt1_is_modified )
			map.put("branch_name_alt1",branch_name_alt1);
		if ( branch_type_is_modified )
			map.put("branch_type",branch_type);
		if ( max_handling_price_ind_is_modified )
			map.put("max_handling_price_ind",max_handling_price_ind);
		if ( max_handling_price_corp_is_modified )
			map.put("max_handling_price_corp",max_handling_price_corp);
		if ( max_handling_price_ind_option_is_modified )
			map.put("max_handling_price_ind_option",max_handling_price_ind_option);
		if ( max_handling_price_corp_option_is_modified )
			map.put("max_handling_price_corp_option",max_handling_price_corp_option);
		if ( max_handling_price_ind_future_is_modified )
			map.put("max_handling_price_ind_future",max_handling_price_ind_future);
		if ( max_handling_price_corp_future_is_modified )
			map.put("max_handling_price_corp_future",max_handling_price_corp_future);
		if ( max_cont_price_all_ind_is_modified )
			map.put("max_cont_price_all_ind",max_cont_price_all_ind);
		if ( max_cont_price_all_corp_is_modified )
			map.put("max_cont_price_all_corp",max_cont_price_all_corp);
		if ( max_cont_price_product_ind_is_modified )
			map.put("max_cont_price_product_ind",max_cont_price_product_ind);
		if ( max_cont_price_product_corp_is_modified )
			map.put("max_cont_price_product_corp",max_cont_price_product_corp);
		if ( max_cont_price_1day_ind_is_modified )
			map.put("max_cont_price_1day_ind",max_cont_price_1day_ind);
		if ( max_cont_price_1day_corp_is_modified )
			map.put("max_cont_price_1day_corp",max_cont_price_1day_corp);
		if ( handling_market_make_is_modified )
			map.put("handling_market_make",handling_market_make);
		if ( handling_not_loan_trans_stock_is_modified )
			map.put("handling_not_loan_trans_stock",handling_not_loan_trans_stock);
		if ( fin_sales_law_execution_is_modified )
			map.put("fin_sales_law_execution",fin_sales_law_execution);
		if ( email_address_is_modified )
			map.put("email_address",email_address);
		if ( login_stop_div_is_modified )
			map.put("login_stop_div",login_stop_div);
		if ( admin_type_id_is_modified )
			map.put("admin_type_id",admin_type_id);
		if ( trader_type_id_is_modified )
			map.put("trader_type_id",trader_type_id);
		if ( account_type_id_is_modified )
			map.put("account_type_id",account_type_id);
		if ( account_group_id_is_modified )
			map.put("account_group_id",account_group_id);
		if ( account_code_min_is_modified )
			map.put("account_code_min",account_code_min);
		if ( account_code_max_is_modified )
			map.put("account_code_max",account_code_max);
		if ( account_code_check_mode_is_modified )
			map.put("account_code_check_mode",account_code_check_mode);
		if ( insider_default_regist_div_is_modified )
			map.put("insider_default_regist_div",insider_default_regist_div);
		if ( margin_sys_div_is_modified )
			map.put("margin_sys_div",margin_sys_div);
		if ( margin_gen_div_is_modified )
			map.put("margin_gen_div",margin_gen_div);
		if ( fstk_div_is_modified )
			map.put("fstk_div",fstk_div);
		if ( mstk_div_is_modified )
			map.put("mstk_div",mstk_div);
		if ( option_div_is_modified )
			map.put("option_div",option_div);
		if ( future_div_is_modified )
			map.put("future_div",future_div);
		if ( mf_div_is_modified )
			map.put("mf_div",mf_div);
		if ( ruito_div_is_modified )
			map.put("ruito_div",ruito_div);
		if ( qualified_investor_confirm_div_is_modified )
			map.put("qualified_investor_confirm_div",qualified_investor_confirm_div);
		if ( margin_deposit_rate_is_modified )
			map.put("margin_deposit_rate",margin_deposit_rate);
		if ( cash_margin_deposit_rate_is_modified )
			map.put("cash_margin_deposit_rate",cash_margin_deposit_rate);
		if ( margin_maintenance_rate_is_modified )
			map.put("margin_maintenance_rate",margin_maintenance_rate);
		if ( min_margin_deposit_is_modified )
			map.put("min_margin_deposit",min_margin_deposit);
		if ( min_ifo_deposit_is_modified )
			map.put("min_ifo_deposit",min_ifo_deposit);
		if ( calc_substitute_rate_is_modified )
			map.put("calc_substitute_rate",calc_substitute_rate);
		if ( margin_sec_check_rate_is_modified )
			map.put("margin_sec_check_rate",margin_sec_check_rate);
		if ( short_margin_restrain_div_is_modified )
			map.put("short_margin_restrain_div",short_margin_restrain_div);
		if ( short_margin_restrain_unit_is_modified )
			map.put("short_margin_restrain_unit",short_margin_restrain_unit);
		if ( short_sell_order_valid_minute_is_modified )
			map.put("short_sell_order_valid_minute",short_sell_order_valid_minute);
		if ( margin_sec_transfer_max_count_is_modified )
			map.put("margin_sec_transfer_max_count",margin_sec_transfer_max_count);
		if ( close_worng_equity_margin_is_modified )
			map.put("close_worng_equity_margin",close_worng_equity_margin);
		if ( close_worng_option_is_modified )
			map.put("close_worng_option",close_worng_option);
		if ( close_worng_sys_future_is_modified )
			map.put("close_worng_sys_future",close_worng_sys_future);
		if ( daily_interest_adjust_amount_is_modified )
			map.put("daily_interest_adjust_amount",daily_interest_adjust_amount);
		if ( pay_auto_calc_div_is_modified )
			map.put("pay_auto_calc_div",pay_auto_calc_div);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( max_handling_price_close_div_is_modified )
			map.put("max_handling_price_close_div",max_handling_price_close_div);
		if ( off_floor_div_is_modified )
			map.put("off_floor_div",off_floor_div);
		if ( close_worng_feq_is_modified )
			map.put("close_worng_feq",close_worng_feq);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( institution_id_is_set )
				map.put("institution_id",new Long(institution_id));
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( branch_name_is_set )
				map.put("branch_name",branch_name);
			map.put("branch_name_alt1",branch_name_alt1);
			if ( branch_type_is_set )
				map.put("branch_type",branch_type);
			map.put("max_handling_price_ind",max_handling_price_ind);
			map.put("max_handling_price_corp",max_handling_price_corp);
			map.put("max_handling_price_ind_option",max_handling_price_ind_option);
			map.put("max_handling_price_corp_option",max_handling_price_corp_option);
			map.put("max_handling_price_ind_future",max_handling_price_ind_future);
			map.put("max_handling_price_corp_future",max_handling_price_corp_future);
			map.put("max_cont_price_all_ind",max_cont_price_all_ind);
			map.put("max_cont_price_all_corp",max_cont_price_all_corp);
			map.put("max_cont_price_product_ind",max_cont_price_product_ind);
			map.put("max_cont_price_product_corp",max_cont_price_product_corp);
			map.put("max_cont_price_1day_ind",max_cont_price_1day_ind);
			map.put("max_cont_price_1day_corp",max_cont_price_1day_corp);
			map.put("handling_market_make",handling_market_make);
			map.put("handling_not_loan_trans_stock",handling_not_loan_trans_stock);
			map.put("fin_sales_law_execution",fin_sales_law_execution);
			map.put("email_address",email_address);
			map.put("login_stop_div",login_stop_div);
			map.put("admin_type_id",admin_type_id);
			map.put("trader_type_id",trader_type_id);
			map.put("account_type_id",account_type_id);
			map.put("account_group_id",account_group_id);
			map.put("account_code_min",account_code_min);
			map.put("account_code_max",account_code_max);
			map.put("account_code_check_mode",account_code_check_mode);
			if ( insider_default_regist_div_is_set )
				map.put("insider_default_regist_div",insider_default_regist_div);
			map.put("margin_sys_div",margin_sys_div);
			map.put("margin_gen_div",margin_gen_div);
			map.put("fstk_div",fstk_div);
			map.put("mstk_div",mstk_div);
			map.put("option_div",option_div);
			map.put("future_div",future_div);
			map.put("mf_div",mf_div);
			map.put("ruito_div",ruito_div);
			map.put("qualified_investor_confirm_div",qualified_investor_confirm_div);
			map.put("margin_deposit_rate",margin_deposit_rate);
			map.put("cash_margin_deposit_rate",cash_margin_deposit_rate);
			map.put("margin_maintenance_rate",margin_maintenance_rate);
			map.put("min_margin_deposit",min_margin_deposit);
			map.put("min_ifo_deposit",min_ifo_deposit);
			map.put("calc_substitute_rate",calc_substitute_rate);
			map.put("margin_sec_check_rate",margin_sec_check_rate);
			map.put("short_margin_restrain_div",short_margin_restrain_div);
			map.put("short_margin_restrain_unit",short_margin_restrain_unit);
			map.put("short_sell_order_valid_minute",short_sell_order_valid_minute);
			map.put("margin_sec_transfer_max_count",margin_sec_transfer_max_count);
			map.put("close_worng_equity_margin",close_worng_equity_margin);
			map.put("close_worng_option",close_worng_option);
			map.put("close_worng_sys_future",close_worng_sys_future);
			map.put("daily_interest_adjust_amount",daily_interest_adjust_amount);
			map.put("pay_auto_calc_div",pay_auto_calc_div);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("max_handling_price_close_div",max_handling_price_close_div);
			map.put("off_floor_div",off_floor_div);
			map.put("close_worng_feq",close_worng_feq);
		}
		return map;
	}


  /** 
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
  }


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>institution_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getInstitutionId()
  {
    return institution_id;
  }


  /** 
   * <em>institution_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionIdIsSet() {
    return institution_id_is_set;
  }


  /** 
   * <em>institution_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionIdIsModified() {
    return institution_id_is_modified;
  }


  /** 
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
  }


  /** 
   * <em>branch_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchName()
  {
    return branch_name;
  }


  /** 
   * <em>branch_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchNameIsSet() {
    return branch_name_is_set;
  }


  /** 
   * <em>branch_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchNameIsModified() {
    return branch_name_is_modified;
  }


  /** 
   * <em>branch_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchNameAlt1()
  {
    return branch_name_alt1;
  }


  /** 
   * <em>branch_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchNameAlt1IsSet() {
    return branch_name_alt1_is_set;
  }


  /** 
   * <em>branch_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchNameAlt1IsModified() {
    return branch_name_alt1_is_modified;
  }


  /** 
   * <em>branch_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum getBranchType()
  {
    return branch_type;
  }


  /** 
   * <em>branch_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchTypeIsSet() {
    return branch_type_is_set;
  }


  /** 
   * <em>branch_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchTypeIsModified() {
    return branch_type_is_modified;
  }


  /** 
   * <em>max_handling_price_ind</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMaxHandlingPriceInd()
  {
    return ( max_handling_price_ind==null? ((long)0): max_handling_price_ind.longValue() );
  }


  /** 
   * <em>max_handling_price_ind</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxHandlingPriceIndIsNull()
  {
    return max_handling_price_ind == null;
  }


  /** 
   * <em>max_handling_price_ind</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceIndIsSet() {
    return max_handling_price_ind_is_set;
  }


  /** 
   * <em>max_handling_price_ind</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceIndIsModified() {
    return max_handling_price_ind_is_modified;
  }


  /** 
   * <em>max_handling_price_corp</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMaxHandlingPriceCorp()
  {
    return ( max_handling_price_corp==null? ((long)0): max_handling_price_corp.longValue() );
  }


  /** 
   * <em>max_handling_price_corp</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxHandlingPriceCorpIsNull()
  {
    return max_handling_price_corp == null;
  }


  /** 
   * <em>max_handling_price_corp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceCorpIsSet() {
    return max_handling_price_corp_is_set;
  }


  /** 
   * <em>max_handling_price_corp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceCorpIsModified() {
    return max_handling_price_corp_is_modified;
  }


  /** 
   * <em>max_handling_price_ind_option</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMaxHandlingPriceIndOption()
  {
    return ( max_handling_price_ind_option==null? ((long)0): max_handling_price_ind_option.longValue() );
  }


  /** 
   * <em>max_handling_price_ind_option</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxHandlingPriceIndOptionIsNull()
  {
    return max_handling_price_ind_option == null;
  }


  /** 
   * <em>max_handling_price_ind_option</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceIndOptionIsSet() {
    return max_handling_price_ind_option_is_set;
  }


  /** 
   * <em>max_handling_price_ind_option</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceIndOptionIsModified() {
    return max_handling_price_ind_option_is_modified;
  }


  /** 
   * <em>max_handling_price_corp_option</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMaxHandlingPriceCorpOption()
  {
    return ( max_handling_price_corp_option==null? ((long)0): max_handling_price_corp_option.longValue() );
  }


  /** 
   * <em>max_handling_price_corp_option</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxHandlingPriceCorpOptionIsNull()
  {
    return max_handling_price_corp_option == null;
  }


  /** 
   * <em>max_handling_price_corp_option</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceCorpOptionIsSet() {
    return max_handling_price_corp_option_is_set;
  }


  /** 
   * <em>max_handling_price_corp_option</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceCorpOptionIsModified() {
    return max_handling_price_corp_option_is_modified;
  }


  /** 
   * <em>max_handling_price_ind_future</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMaxHandlingPriceIndFuture()
  {
    return ( max_handling_price_ind_future==null? ((long)0): max_handling_price_ind_future.longValue() );
  }


  /** 
   * <em>max_handling_price_ind_future</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxHandlingPriceIndFutureIsNull()
  {
    return max_handling_price_ind_future == null;
  }


  /** 
   * <em>max_handling_price_ind_future</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceIndFutureIsSet() {
    return max_handling_price_ind_future_is_set;
  }


  /** 
   * <em>max_handling_price_ind_future</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceIndFutureIsModified() {
    return max_handling_price_ind_future_is_modified;
  }


  /** 
   * <em>max_handling_price_corp_future</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMaxHandlingPriceCorpFuture()
  {
    return ( max_handling_price_corp_future==null? ((long)0): max_handling_price_corp_future.longValue() );
  }


  /** 
   * <em>max_handling_price_corp_future</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxHandlingPriceCorpFutureIsNull()
  {
    return max_handling_price_corp_future == null;
  }


  /** 
   * <em>max_handling_price_corp_future</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceCorpFutureIsSet() {
    return max_handling_price_corp_future_is_set;
  }


  /** 
   * <em>max_handling_price_corp_future</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceCorpFutureIsModified() {
    return max_handling_price_corp_future_is_modified;
  }


  /** 
   * <em>max_cont_price_all_ind</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMaxContPriceAllInd()
  {
    return ( max_cont_price_all_ind==null? ((double)0): max_cont_price_all_ind.doubleValue() );
  }


  /** 
   * <em>max_cont_price_all_ind</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxContPriceAllIndIsNull()
  {
    return max_cont_price_all_ind == null;
  }


  /** 
   * <em>max_cont_price_all_ind</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPriceAllIndIsSet() {
    return max_cont_price_all_ind_is_set;
  }


  /** 
   * <em>max_cont_price_all_ind</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPriceAllIndIsModified() {
    return max_cont_price_all_ind_is_modified;
  }


  /** 
   * <em>max_cont_price_all_corp</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMaxContPriceAllCorp()
  {
    return ( max_cont_price_all_corp==null? ((double)0): max_cont_price_all_corp.doubleValue() );
  }


  /** 
   * <em>max_cont_price_all_corp</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxContPriceAllCorpIsNull()
  {
    return max_cont_price_all_corp == null;
  }


  /** 
   * <em>max_cont_price_all_corp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPriceAllCorpIsSet() {
    return max_cont_price_all_corp_is_set;
  }


  /** 
   * <em>max_cont_price_all_corp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPriceAllCorpIsModified() {
    return max_cont_price_all_corp_is_modified;
  }


  /** 
   * <em>max_cont_price_product_ind</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMaxContPriceProductInd()
  {
    return ( max_cont_price_product_ind==null? ((double)0): max_cont_price_product_ind.doubleValue() );
  }


  /** 
   * <em>max_cont_price_product_ind</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxContPriceProductIndIsNull()
  {
    return max_cont_price_product_ind == null;
  }


  /** 
   * <em>max_cont_price_product_ind</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPriceProductIndIsSet() {
    return max_cont_price_product_ind_is_set;
  }


  /** 
   * <em>max_cont_price_product_ind</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPriceProductIndIsModified() {
    return max_cont_price_product_ind_is_modified;
  }


  /** 
   * <em>max_cont_price_product_corp</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMaxContPriceProductCorp()
  {
    return ( max_cont_price_product_corp==null? ((double)0): max_cont_price_product_corp.doubleValue() );
  }


  /** 
   * <em>max_cont_price_product_corp</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxContPriceProductCorpIsNull()
  {
    return max_cont_price_product_corp == null;
  }


  /** 
   * <em>max_cont_price_product_corp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPriceProductCorpIsSet() {
    return max_cont_price_product_corp_is_set;
  }


  /** 
   * <em>max_cont_price_product_corp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPriceProductCorpIsModified() {
    return max_cont_price_product_corp_is_modified;
  }


  /** 
   * <em>max_cont_price_1day_ind</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMaxContPrice1dayInd()
  {
    return ( max_cont_price_1day_ind==null? ((double)0): max_cont_price_1day_ind.doubleValue() );
  }


  /** 
   * <em>max_cont_price_1day_ind</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxContPrice1dayIndIsNull()
  {
    return max_cont_price_1day_ind == null;
  }


  /** 
   * <em>max_cont_price_1day_ind</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPrice1dayIndIsSet() {
    return max_cont_price_1day_ind_is_set;
  }


  /** 
   * <em>max_cont_price_1day_ind</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPrice1dayIndIsModified() {
    return max_cont_price_1day_ind_is_modified;
  }


  /** 
   * <em>max_cont_price_1day_corp</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMaxContPrice1dayCorp()
  {
    return ( max_cont_price_1day_corp==null? ((double)0): max_cont_price_1day_corp.doubleValue() );
  }


  /** 
   * <em>max_cont_price_1day_corp</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxContPrice1dayCorpIsNull()
  {
    return max_cont_price_1day_corp == null;
  }


  /** 
   * <em>max_cont_price_1day_corp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPrice1dayCorpIsSet() {
    return max_cont_price_1day_corp_is_set;
  }


  /** 
   * <em>max_cont_price_1day_corp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPrice1dayCorpIsModified() {
    return max_cont_price_1day_corp_is_modified;
  }


  /** 
   * <em>handling_market_make</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getHandlingMarketMake()
  {
    return ( handling_market_make==null? ((int)0): handling_market_make.intValue() );
  }


  /** 
   * <em>handling_market_make</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getHandlingMarketMakeIsNull()
  {
    return handling_market_make == null;
  }


  /** 
   * <em>handling_market_make</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHandlingMarketMakeIsSet() {
    return handling_market_make_is_set;
  }


  /** 
   * <em>handling_market_make</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHandlingMarketMakeIsModified() {
    return handling_market_make_is_modified;
  }


  /** 
   * <em>handling_not_loan_trans_stock</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getHandlingNotLoanTransStock()
  {
    return ( handling_not_loan_trans_stock==null? ((int)0): handling_not_loan_trans_stock.intValue() );
  }


  /** 
   * <em>handling_not_loan_trans_stock</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getHandlingNotLoanTransStockIsNull()
  {
    return handling_not_loan_trans_stock == null;
  }


  /** 
   * <em>handling_not_loan_trans_stock</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHandlingNotLoanTransStockIsSet() {
    return handling_not_loan_trans_stock_is_set;
  }


  /** 
   * <em>handling_not_loan_trans_stock</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHandlingNotLoanTransStockIsModified() {
    return handling_not_loan_trans_stock_is_modified;
  }


  /** 
   * <em>fin_sales_law_execution</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinSalesLawExecution()
  {
    return fin_sales_law_execution;
  }


  /** 
   * <em>fin_sales_law_execution</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinSalesLawExecutionIsSet() {
    return fin_sales_law_execution_is_set;
  }


  /** 
   * <em>fin_sales_law_execution</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinSalesLawExecutionIsModified() {
    return fin_sales_law_execution_is_modified;
  }


  /** 
   * <em>email_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEmailAddress()
  {
    return email_address;
  }


  /** 
   * <em>email_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressIsSet() {
    return email_address_is_set;
  }


  /** 
   * <em>email_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressIsModified() {
    return email_address_is_modified;
  }


  /** 
   * <em>login_stop_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLoginStopDiv()
  {
    return login_stop_div;
  }


  /** 
   * <em>login_stop_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginStopDivIsSet() {
    return login_stop_div_is_set;
  }


  /** 
   * <em>login_stop_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginStopDivIsModified() {
    return login_stop_div_is_modified;
  }


  /** 
   * <em>admin_type_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAdminTypeId()
  {
    return ( admin_type_id==null? ((long)0): admin_type_id.longValue() );
  }


  /** 
   * <em>admin_type_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAdminTypeIdIsNull()
  {
    return admin_type_id == null;
  }


  /** 
   * <em>admin_type_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdminTypeIdIsSet() {
    return admin_type_id_is_set;
  }


  /** 
   * <em>admin_type_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdminTypeIdIsModified() {
    return admin_type_id_is_modified;
  }


  /** 
   * <em>trader_type_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTraderTypeId()
  {
    return ( trader_type_id==null? ((long)0): trader_type_id.longValue() );
  }


  /** 
   * <em>trader_type_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTraderTypeIdIsNull()
  {
    return trader_type_id == null;
  }


  /** 
   * <em>trader_type_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderTypeIdIsSet() {
    return trader_type_id_is_set;
  }


  /** 
   * <em>trader_type_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderTypeIdIsModified() {
    return trader_type_id_is_modified;
  }


  /** 
   * <em>account_type_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountTypeId()
  {
    return ( account_type_id==null? ((long)0): account_type_id.longValue() );
  }


  /** 
   * <em>account_type_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountTypeIdIsNull()
  {
    return account_type_id == null;
  }


  /** 
   * <em>account_type_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountTypeIdIsSet() {
    return account_type_id_is_set;
  }


  /** 
   * <em>account_type_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountTypeIdIsModified() {
    return account_type_id_is_modified;
  }


  /** 
   * <em>account_group_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountGroupId()
  {
    return ( account_group_id==null? ((long)0): account_group_id.longValue() );
  }


  /** 
   * <em>account_group_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountGroupIdIsNull()
  {
    return account_group_id == null;
  }


  /** 
   * <em>account_group_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountGroupIdIsSet() {
    return account_group_id_is_set;
  }


  /** 
   * <em>account_group_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountGroupIdIsModified() {
    return account_group_id_is_modified;
  }


  /** 
   * <em>account_code_min</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getAccountCodeMin()
  {
    return ( account_code_min==null? ((int)0): account_code_min.intValue() );
  }


  /** 
   * <em>account_code_min</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountCodeMinIsNull()
  {
    return account_code_min == null;
  }


  /** 
   * <em>account_code_min</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeMinIsSet() {
    return account_code_min_is_set;
  }


  /** 
   * <em>account_code_min</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeMinIsModified() {
    return account_code_min_is_modified;
  }


  /** 
   * <em>account_code_max</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getAccountCodeMax()
  {
    return ( account_code_max==null? ((int)0): account_code_max.intValue() );
  }


  /** 
   * <em>account_code_max</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountCodeMaxIsNull()
  {
    return account_code_max == null;
  }


  /** 
   * <em>account_code_max</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeMaxIsSet() {
    return account_code_max_is_set;
  }


  /** 
   * <em>account_code_max</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeMaxIsModified() {
    return account_code_max_is_modified;
  }


  /** 
   * <em>account_code_check_mode</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCodeCheckMode()
  {
    return account_code_check_mode;
  }


  /** 
   * <em>account_code_check_mode</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeCheckModeIsSet() {
    return account_code_check_mode_is_set;
  }


  /** 
   * <em>account_code_check_mode</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeCheckModeIsModified() {
    return account_code_check_mode_is_modified;
  }


  /** 
   * <em>insider_default_regist_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInsiderDefaultRegistDiv()
  {
    return insider_default_regist_div;
  }


  /** 
   * <em>insider_default_regist_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderDefaultRegistDivIsSet() {
    return insider_default_regist_div_is_set;
  }


  /** 
   * <em>insider_default_regist_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderDefaultRegistDivIsModified() {
    return insider_default_regist_div_is_modified;
  }


  /** 
   * <em>margin_sys_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginSysDiv()
  {
    return margin_sys_div;
  }


  /** 
   * <em>margin_sys_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSysDivIsSet() {
    return margin_sys_div_is_set;
  }


  /** 
   * <em>margin_sys_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSysDivIsModified() {
    return margin_sys_div_is_modified;
  }


  /** 
   * <em>margin_gen_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginGenDiv()
  {
    return margin_gen_div;
  }


  /** 
   * <em>margin_gen_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginGenDivIsSet() {
    return margin_gen_div_is_set;
  }


  /** 
   * <em>margin_gen_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginGenDivIsModified() {
    return margin_gen_div_is_modified;
  }


  /** 
   * <em>fstk_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFstkDiv()
  {
    return fstk_div;
  }


  /** 
   * <em>fstk_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFstkDivIsSet() {
    return fstk_div_is_set;
  }


  /** 
   * <em>fstk_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFstkDivIsModified() {
    return fstk_div_is_modified;
  }


  /** 
   * <em>mstk_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMstkDiv()
  {
    return mstk_div;
  }


  /** 
   * <em>mstk_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMstkDivIsSet() {
    return mstk_div_is_set;
  }


  /** 
   * <em>mstk_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMstkDivIsModified() {
    return mstk_div_is_modified;
  }


  /** 
   * <em>option_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOptionDiv()
  {
    return option_div;
  }


  /** 
   * <em>option_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOptionDivIsSet() {
    return option_div_is_set;
  }


  /** 
   * <em>option_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOptionDivIsModified() {
    return option_div_is_modified;
  }


  /** 
   * <em>future_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFutureDiv()
  {
    return future_div;
  }


  /** 
   * <em>future_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureDivIsSet() {
    return future_div_is_set;
  }


  /** 
   * <em>future_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureDivIsModified() {
    return future_div_is_modified;
  }


  /** 
   * <em>mf_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMfDiv()
  {
    return mf_div;
  }


  /** 
   * <em>mf_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMfDivIsSet() {
    return mf_div_is_set;
  }


  /** 
   * <em>mf_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMfDivIsModified() {
    return mf_div_is_modified;
  }


  /** 
   * <em>ruito_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRuitoDiv()
  {
    return ruito_div;
  }


  /** 
   * <em>ruito_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoDivIsSet() {
    return ruito_div_is_set;
  }


  /** 
   * <em>ruito_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoDivIsModified() {
    return ruito_div_is_modified;
  }


  /** 
   * <em>qualified_investor_confirm_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getQualifiedInvestorConfirmDiv()
  {
    return qualified_investor_confirm_div;
  }


  /** 
   * <em>qualified_investor_confirm_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQualifiedInvestorConfirmDivIsSet() {
    return qualified_investor_confirm_div_is_set;
  }


  /** 
   * <em>qualified_investor_confirm_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQualifiedInvestorConfirmDivIsModified() {
    return qualified_investor_confirm_div_is_modified;
  }


  /** 
   * <em>margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDepositRate()
  {
    return ( margin_deposit_rate==null? ((double)0): margin_deposit_rate.doubleValue() );
  }


  /** 
   * <em>margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDepositRateIsNull()
  {
    return margin_deposit_rate == null;
  }


  /** 
   * <em>margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRateIsSet() {
    return margin_deposit_rate_is_set;
  }


  /** 
   * <em>margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRateIsModified() {
    return margin_deposit_rate_is_modified;
  }


  /** 
   * <em>cash_margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashMarginDepositRate()
  {
    return ( cash_margin_deposit_rate==null? ((double)0): cash_margin_deposit_rate.doubleValue() );
  }


  /** 
   * <em>cash_margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashMarginDepositRateIsNull()
  {
    return cash_margin_deposit_rate == null;
  }


  /** 
   * <em>cash_margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDepositRateIsSet() {
    return cash_margin_deposit_rate_is_set;
  }


  /** 
   * <em>cash_margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDepositRateIsModified() {
    return cash_margin_deposit_rate_is_modified;
  }


  /** 
   * <em>margin_maintenance_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginMaintenanceRate()
  {
    return ( margin_maintenance_rate==null? ((double)0): margin_maintenance_rate.doubleValue() );
  }


  /** 
   * <em>margin_maintenance_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginMaintenanceRateIsNull()
  {
    return margin_maintenance_rate == null;
  }


  /** 
   * <em>margin_maintenance_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceRateIsSet() {
    return margin_maintenance_rate_is_set;
  }


  /** 
   * <em>margin_maintenance_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceRateIsModified() {
    return margin_maintenance_rate_is_modified;
  }


  /** 
   * <em>min_margin_deposit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMinMarginDeposit()
  {
    return ( min_margin_deposit==null? ((double)0): min_margin_deposit.doubleValue() );
  }


  /** 
   * <em>min_margin_deposit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMinMarginDepositIsNull()
  {
    return min_margin_deposit == null;
  }


  /** 
   * <em>min_margin_deposit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinMarginDepositIsSet() {
    return min_margin_deposit_is_set;
  }


  /** 
   * <em>min_margin_deposit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinMarginDepositIsModified() {
    return min_margin_deposit_is_modified;
  }


  /** 
   * <em>min_ifo_deposit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMinIfoDeposit()
  {
    return ( min_ifo_deposit==null? ((double)0): min_ifo_deposit.doubleValue() );
  }


  /** 
   * <em>min_ifo_deposit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMinIfoDepositIsNull()
  {
    return min_ifo_deposit == null;
  }


  /** 
   * <em>min_ifo_deposit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinIfoDepositIsSet() {
    return min_ifo_deposit_is_set;
  }


  /** 
   * <em>min_ifo_deposit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinIfoDepositIsModified() {
    return min_ifo_deposit_is_modified;
  }


  /** 
   * <em>calc_substitute_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCalcSubstituteRate()
  {
    return ( calc_substitute_rate==null? ((double)0): calc_substitute_rate.doubleValue() );
  }


  /** 
   * <em>calc_substitute_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCalcSubstituteRateIsNull()
  {
    return calc_substitute_rate == null;
  }


  /** 
   * <em>calc_substitute_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcSubstituteRateIsSet() {
    return calc_substitute_rate_is_set;
  }


  /** 
   * <em>calc_substitute_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcSubstituteRateIsModified() {
    return calc_substitute_rate_is_modified;
  }


  /** 
   * <em>margin_sec_check_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginSecCheckRate()
  {
    return ( margin_sec_check_rate==null? ((double)0): margin_sec_check_rate.doubleValue() );
  }


  /** 
   * <em>margin_sec_check_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginSecCheckRateIsNull()
  {
    return margin_sec_check_rate == null;
  }


  /** 
   * <em>margin_sec_check_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSecCheckRateIsSet() {
    return margin_sec_check_rate_is_set;
  }


  /** 
   * <em>margin_sec_check_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSecCheckRateIsModified() {
    return margin_sec_check_rate_is_modified;
  }


  /** 
   * <em>short_margin_restrain_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShortMarginRestrainDiv()
  {
    return short_margin_restrain_div;
  }


  /** 
   * <em>short_margin_restrain_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMarginRestrainDivIsSet() {
    return short_margin_restrain_div_is_set;
  }


  /** 
   * <em>short_margin_restrain_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMarginRestrainDivIsModified() {
    return short_margin_restrain_div_is_modified;
  }


  /** 
   * <em>short_margin_restrain_unit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getShortMarginRestrainUnit()
  {
    return ( short_margin_restrain_unit==null? ((double)0): short_margin_restrain_unit.doubleValue() );
  }


  /** 
   * <em>short_margin_restrain_unit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortMarginRestrainUnitIsNull()
  {
    return short_margin_restrain_unit == null;
  }


  /** 
   * <em>short_margin_restrain_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMarginRestrainUnitIsSet() {
    return short_margin_restrain_unit_is_set;
  }


  /** 
   * <em>short_margin_restrain_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMarginRestrainUnitIsModified() {
    return short_margin_restrain_unit_is_modified;
  }


  /** 
   * <em>short_sell_order_valid_minute</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getShortSellOrderValidMinute()
  {
    return ( short_sell_order_valid_minute==null? ((int)0): short_sell_order_valid_minute.intValue() );
  }


  /** 
   * <em>short_sell_order_valid_minute</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortSellOrderValidMinuteIsNull()
  {
    return short_sell_order_valid_minute == null;
  }


  /** 
   * <em>short_sell_order_valid_minute</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortSellOrderValidMinuteIsSet() {
    return short_sell_order_valid_minute_is_set;
  }


  /** 
   * <em>short_sell_order_valid_minute</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortSellOrderValidMinuteIsModified() {
    return short_sell_order_valid_minute_is_modified;
  }


  /** 
   * <em>margin_sec_transfer_max_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getMarginSecTransferMaxCount()
  {
    return ( margin_sec_transfer_max_count==null? ((int)0): margin_sec_transfer_max_count.intValue() );
  }


  /** 
   * <em>margin_sec_transfer_max_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginSecTransferMaxCountIsNull()
  {
    return margin_sec_transfer_max_count == null;
  }


  /** 
   * <em>margin_sec_transfer_max_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSecTransferMaxCountIsSet() {
    return margin_sec_transfer_max_count_is_set;
  }


  /** 
   * <em>margin_sec_transfer_max_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSecTransferMaxCountIsModified() {
    return margin_sec_transfer_max_count_is_modified;
  }


  /** 
   * <em>close_worng_equity_margin</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getCloseWorngEquityMargin()
  {
    return ( close_worng_equity_margin==null? ((int)0): close_worng_equity_margin.intValue() );
  }


  /** 
   * <em>close_worng_equity_margin</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCloseWorngEquityMarginIsNull()
  {
    return close_worng_equity_margin == null;
  }


  /** 
   * <em>close_worng_equity_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseWorngEquityMarginIsSet() {
    return close_worng_equity_margin_is_set;
  }


  /** 
   * <em>close_worng_equity_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseWorngEquityMarginIsModified() {
    return close_worng_equity_margin_is_modified;
  }


  /** 
   * <em>close_worng_option</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getCloseWorngOption()
  {
    return ( close_worng_option==null? ((int)0): close_worng_option.intValue() );
  }


  /** 
   * <em>close_worng_option</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCloseWorngOptionIsNull()
  {
    return close_worng_option == null;
  }


  /** 
   * <em>close_worng_option</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseWorngOptionIsSet() {
    return close_worng_option_is_set;
  }


  /** 
   * <em>close_worng_option</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseWorngOptionIsModified() {
    return close_worng_option_is_modified;
  }


  /** 
   * <em>close_worng_sys_future</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getCloseWorngSysFuture()
  {
    return ( close_worng_sys_future==null? ((int)0): close_worng_sys_future.intValue() );
  }


  /** 
   * <em>close_worng_sys_future</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCloseWorngSysFutureIsNull()
  {
    return close_worng_sys_future == null;
  }


  /** 
   * <em>close_worng_sys_future</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseWorngSysFutureIsSet() {
    return close_worng_sys_future_is_set;
  }


  /** 
   * <em>close_worng_sys_future</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseWorngSysFutureIsModified() {
    return close_worng_sys_future_is_modified;
  }


  /** 
   * <em>daily_interest_adjust_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDailyInterestAdjustAmount()
  {
    return ( daily_interest_adjust_amount==null? ((double)0): daily_interest_adjust_amount.doubleValue() );
  }


  /** 
   * <em>daily_interest_adjust_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDailyInterestAdjustAmountIsNull()
  {
    return daily_interest_adjust_amount == null;
  }


  /** 
   * <em>daily_interest_adjust_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDailyInterestAdjustAmountIsSet() {
    return daily_interest_adjust_amount_is_set;
  }


  /** 
   * <em>daily_interest_adjust_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDailyInterestAdjustAmountIsModified() {
    return daily_interest_adjust_amount_is_modified;
  }


  /** 
   * <em>pay_auto_calc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPayAutoCalcDiv()
  {
    return pay_auto_calc_div;
  }


  /** 
   * <em>pay_auto_calc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayAutoCalcDivIsSet() {
    return pay_auto_calc_div_is_set;
  }


  /** 
   * <em>pay_auto_calc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayAutoCalcDivIsModified() {
    return pay_auto_calc_div_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * <em>max_handling_price_close_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMaxHandlingPriceCloseDiv()
  {
    return max_handling_price_close_div;
  }


  /** 
   * <em>max_handling_price_close_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceCloseDivIsSet() {
    return max_handling_price_close_div_is_set;
  }


  /** 
   * <em>max_handling_price_close_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxHandlingPriceCloseDivIsModified() {
    return max_handling_price_close_div_is_modified;
  }


  /** 
   * <em>off_floor_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOffFloorDiv()
  {
    return off_floor_div;
  }


  /** 
   * <em>off_floor_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOffFloorDivIsSet() {
    return off_floor_div_is_set;
  }


  /** 
   * <em>off_floor_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOffFloorDivIsModified() {
    return off_floor_div_is_modified;
  }


  /** 
   * <em>close_worng_feq</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getCloseWorngFeq()
  {
    return ( close_worng_feq==null? ((int)0): close_worng_feq.intValue() );
  }


  /** 
   * <em>close_worng_feq</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCloseWorngFeqIsNull()
  {
    return close_worng_feq == null;
  }


  /** 
   * <em>close_worng_feq</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseWorngFeqIsSet() {
    return close_worng_feq_is_set;
  }


  /** 
   * <em>close_worng_feq</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseWorngFeqIsModified() {
    return close_worng_feq_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new BranchPK(branch_id);
  }


  /** 
   * <em>branch_id</em>カラムの値を設定します。 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>institution_id</em>カラムの値を設定します。 
   *
   * @@param p_institutionId <em>institution_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setInstitutionId( long p_institutionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_id = p_institutionId;
    institution_id_is_set = true;
    institution_id_is_modified = true;
  }


  /** 
   * <em>branch_code</em>カラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>branch_name</em>カラムの値を設定します。 
   *
   * @@param p_branchName <em>branch_name</em>カラムの値をあらわす80桁以下のString値 
   */
  public final void setBranchName( String p_branchName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_name = p_branchName;
    branch_name_is_set = true;
    branch_name_is_modified = true;
  }


  /** 
   * <em>branch_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_branchNameAlt1 <em>branch_name_alt1</em>カラムの値をあらわす80桁以下のString値 
   */
  public final void setBranchNameAlt1( String p_branchNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_name_alt1 = p_branchNameAlt1;
    branch_name_alt1_is_set = true;
    branch_name_alt1_is_modified = true;
  }


  /** 
   * <em>branch_type</em>カラムの値を設定します。 
   *
   * @@param p_branchType <em>branch_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum値 
   */
  public final void setBranchType( com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum p_branchType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_type = p_branchType;
    branch_type_is_set = true;
    branch_type_is_modified = true;
  }


  /** 
   * <em>max_handling_price_ind</em>カラムの値を設定します。 
   *
   * @@param p_maxHandlingPriceInd <em>max_handling_price_ind</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setMaxHandlingPriceInd( long p_maxHandlingPriceInd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_handling_price_ind = new Long(p_maxHandlingPriceInd);
    max_handling_price_ind_is_set = true;
    max_handling_price_ind_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_handling_price_ind</em>カラムに値を設定します。 
   */
  public final void setMaxHandlingPriceInd( Long p_maxHandlingPriceInd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_handling_price_ind = p_maxHandlingPriceInd;
    max_handling_price_ind_is_set = true;
    max_handling_price_ind_is_modified = true;
  }


  /** 
   * <em>max_handling_price_corp</em>カラムの値を設定します。 
   *
   * @@param p_maxHandlingPriceCorp <em>max_handling_price_corp</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setMaxHandlingPriceCorp( long p_maxHandlingPriceCorp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_handling_price_corp = new Long(p_maxHandlingPriceCorp);
    max_handling_price_corp_is_set = true;
    max_handling_price_corp_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_handling_price_corp</em>カラムに値を設定します。 
   */
  public final void setMaxHandlingPriceCorp( Long p_maxHandlingPriceCorp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_handling_price_corp = p_maxHandlingPriceCorp;
    max_handling_price_corp_is_set = true;
    max_handling_price_corp_is_modified = true;
  }


  /** 
   * <em>max_handling_price_ind_option</em>カラムの値を設定します。 
   *
   * @@param p_maxHandlingPriceIndOption <em>max_handling_price_ind_option</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setMaxHandlingPriceIndOption( long p_maxHandlingPriceIndOption )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_handling_price_ind_option = new Long(p_maxHandlingPriceIndOption);
    max_handling_price_ind_option_is_set = true;
    max_handling_price_ind_option_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_handling_price_ind_option</em>カラムに値を設定します。 
   */
  public final void setMaxHandlingPriceIndOption( Long p_maxHandlingPriceIndOption )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_handling_price_ind_option = p_maxHandlingPriceIndOption;
    max_handling_price_ind_option_is_set = true;
    max_handling_price_ind_option_is_modified = true;
  }


  /** 
   * <em>max_handling_price_corp_option</em>カラムの値を設定します。 
   *
   * @@param p_maxHandlingPriceCorpOption <em>max_handling_price_corp_option</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setMaxHandlingPriceCorpOption( long p_maxHandlingPriceCorpOption )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_handling_price_corp_option = new Long(p_maxHandlingPriceCorpOption);
    max_handling_price_corp_option_is_set = true;
    max_handling_price_corp_option_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_handling_price_corp_option</em>カラムに値を設定します。 
   */
  public final void setMaxHandlingPriceCorpOption( Long p_maxHandlingPriceCorpOption )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_handling_price_corp_option = p_maxHandlingPriceCorpOption;
    max_handling_price_corp_option_is_set = true;
    max_handling_price_corp_option_is_modified = true;
  }


  /** 
   * <em>max_handling_price_ind_future</em>カラムの値を設定します。 
   *
   * @@param p_maxHandlingPriceIndFuture <em>max_handling_price_ind_future</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setMaxHandlingPriceIndFuture( long p_maxHandlingPriceIndFuture )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_handling_price_ind_future = new Long(p_maxHandlingPriceIndFuture);
    max_handling_price_ind_future_is_set = true;
    max_handling_price_ind_future_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_handling_price_ind_future</em>カラムに値を設定します。 
   */
  public final void setMaxHandlingPriceIndFuture( Long p_maxHandlingPriceIndFuture )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_handling_price_ind_future = p_maxHandlingPriceIndFuture;
    max_handling_price_ind_future_is_set = true;
    max_handling_price_ind_future_is_modified = true;
  }


  /** 
   * <em>max_handling_price_corp_future</em>カラムの値を設定します。 
   *
   * @@param p_maxHandlingPriceCorpFuture <em>max_handling_price_corp_future</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setMaxHandlingPriceCorpFuture( long p_maxHandlingPriceCorpFuture )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_handling_price_corp_future = new Long(p_maxHandlingPriceCorpFuture);
    max_handling_price_corp_future_is_set = true;
    max_handling_price_corp_future_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_handling_price_corp_future</em>カラムに値を設定します。 
   */
  public final void setMaxHandlingPriceCorpFuture( Long p_maxHandlingPriceCorpFuture )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_handling_price_corp_future = p_maxHandlingPriceCorpFuture;
    max_handling_price_corp_future_is_set = true;
    max_handling_price_corp_future_is_modified = true;
  }


  /** 
   * <em>max_cont_price_all_ind</em>カラムの値を設定します。 
   *
   * @@param p_maxContPriceAllInd <em>max_cont_price_all_ind</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMaxContPriceAllInd( double p_maxContPriceAllInd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_all_ind = new Double(p_maxContPriceAllInd);
    max_cont_price_all_ind_is_set = true;
    max_cont_price_all_ind_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_cont_price_all_ind</em>カラムに値を設定します。 
   */
  public final void setMaxContPriceAllInd( Double p_maxContPriceAllInd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_all_ind = p_maxContPriceAllInd;
    max_cont_price_all_ind_is_set = true;
    max_cont_price_all_ind_is_modified = true;
  }


  /** 
   * <em>max_cont_price_all_corp</em>カラムの値を設定します。 
   *
   * @@param p_maxContPriceAllCorp <em>max_cont_price_all_corp</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMaxContPriceAllCorp( double p_maxContPriceAllCorp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_all_corp = new Double(p_maxContPriceAllCorp);
    max_cont_price_all_corp_is_set = true;
    max_cont_price_all_corp_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_cont_price_all_corp</em>カラムに値を設定します。 
   */
  public final void setMaxContPriceAllCorp( Double p_maxContPriceAllCorp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_all_corp = p_maxContPriceAllCorp;
    max_cont_price_all_corp_is_set = true;
    max_cont_price_all_corp_is_modified = true;
  }


  /** 
   * <em>max_cont_price_product_ind</em>カラムの値を設定します。 
   *
   * @@param p_maxContPriceProductInd <em>max_cont_price_product_ind</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMaxContPriceProductInd( double p_maxContPriceProductInd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_product_ind = new Double(p_maxContPriceProductInd);
    max_cont_price_product_ind_is_set = true;
    max_cont_price_product_ind_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_cont_price_product_ind</em>カラムに値を設定します。 
   */
  public final void setMaxContPriceProductInd( Double p_maxContPriceProductInd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_product_ind = p_maxContPriceProductInd;
    max_cont_price_product_ind_is_set = true;
    max_cont_price_product_ind_is_modified = true;
  }


  /** 
   * <em>max_cont_price_product_corp</em>カラムの値を設定します。 
   *
   * @@param p_maxContPriceProductCorp <em>max_cont_price_product_corp</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMaxContPriceProductCorp( double p_maxContPriceProductCorp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_product_corp = new Double(p_maxContPriceProductCorp);
    max_cont_price_product_corp_is_set = true;
    max_cont_price_product_corp_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_cont_price_product_corp</em>カラムに値を設定します。 
   */
  public final void setMaxContPriceProductCorp( Double p_maxContPriceProductCorp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_product_corp = p_maxContPriceProductCorp;
    max_cont_price_product_corp_is_set = true;
    max_cont_price_product_corp_is_modified = true;
  }


  /** 
   * <em>max_cont_price_1day_ind</em>カラムの値を設定します。 
   *
   * @@param p_maxContPrice1dayInd <em>max_cont_price_1day_ind</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMaxContPrice1dayInd( double p_maxContPrice1dayInd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_1day_ind = new Double(p_maxContPrice1dayInd);
    max_cont_price_1day_ind_is_set = true;
    max_cont_price_1day_ind_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_cont_price_1day_ind</em>カラムに値を設定します。 
   */
  public final void setMaxContPrice1dayInd( Double p_maxContPrice1dayInd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_1day_ind = p_maxContPrice1dayInd;
    max_cont_price_1day_ind_is_set = true;
    max_cont_price_1day_ind_is_modified = true;
  }


  /** 
   * <em>max_cont_price_1day_corp</em>カラムの値を設定します。 
   *
   * @@param p_maxContPrice1dayCorp <em>max_cont_price_1day_corp</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMaxContPrice1dayCorp( double p_maxContPrice1dayCorp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_1day_corp = new Double(p_maxContPrice1dayCorp);
    max_cont_price_1day_corp_is_set = true;
    max_cont_price_1day_corp_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_cont_price_1day_corp</em>カラムに値を設定します。 
   */
  public final void setMaxContPrice1dayCorp( Double p_maxContPrice1dayCorp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_1day_corp = p_maxContPrice1dayCorp;
    max_cont_price_1day_corp_is_set = true;
    max_cont_price_1day_corp_is_modified = true;
  }


  /** 
   * <em>handling_market_make</em>カラムの値を設定します。 
   *
   * @@param p_handlingMarketMake <em>handling_market_make</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setHandlingMarketMake( int p_handlingMarketMake )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    handling_market_make = new Integer(p_handlingMarketMake);
    handling_market_make_is_set = true;
    handling_market_make_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>handling_market_make</em>カラムに値を設定します。 
   */
  public final void setHandlingMarketMake( Integer p_handlingMarketMake )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    handling_market_make = p_handlingMarketMake;
    handling_market_make_is_set = true;
    handling_market_make_is_modified = true;
  }


  /** 
   * <em>handling_not_loan_trans_stock</em>カラムの値を設定します。 
   *
   * @@param p_handlingNotLoanTransStock <em>handling_not_loan_trans_stock</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setHandlingNotLoanTransStock( int p_handlingNotLoanTransStock )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    handling_not_loan_trans_stock = new Integer(p_handlingNotLoanTransStock);
    handling_not_loan_trans_stock_is_set = true;
    handling_not_loan_trans_stock_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>handling_not_loan_trans_stock</em>カラムに値を設定します。 
   */
  public final void setHandlingNotLoanTransStock( Integer p_handlingNotLoanTransStock )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    handling_not_loan_trans_stock = p_handlingNotLoanTransStock;
    handling_not_loan_trans_stock_is_set = true;
    handling_not_loan_trans_stock_is_modified = true;
  }


  /** 
   * <em>fin_sales_law_execution</em>カラムの値を設定します。 
   *
   * @@param p_finSalesLawExecution <em>fin_sales_law_execution</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFinSalesLawExecution( String p_finSalesLawExecution )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_sales_law_execution = p_finSalesLawExecution;
    fin_sales_law_execution_is_set = true;
    fin_sales_law_execution_is_modified = true;
  }


  /** 
   * <em>email_address</em>カラムの値を設定します。 
   *
   * @@param p_emailAddress <em>email_address</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setEmailAddress( String p_emailAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    email_address = p_emailAddress;
    email_address_is_set = true;
    email_address_is_modified = true;
  }


  /** 
   * <em>login_stop_div</em>カラムの値を設定します。 
   *
   * @@param p_loginStopDiv <em>login_stop_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setLoginStopDiv( String p_loginStopDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    login_stop_div = p_loginStopDiv;
    login_stop_div_is_set = true;
    login_stop_div_is_modified = true;
  }


  /** 
   * <em>admin_type_id</em>カラムの値を設定します。 
   *
   * @@param p_adminTypeId <em>admin_type_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAdminTypeId( long p_adminTypeId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    admin_type_id = new Long(p_adminTypeId);
    admin_type_id_is_set = true;
    admin_type_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>admin_type_id</em>カラムに値を設定します。 
   */
  public final void setAdminTypeId( Long p_adminTypeId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    admin_type_id = p_adminTypeId;
    admin_type_id_is_set = true;
    admin_type_id_is_modified = true;
  }


  /** 
   * <em>trader_type_id</em>カラムの値を設定します。 
   *
   * @@param p_traderTypeId <em>trader_type_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setTraderTypeId( long p_traderTypeId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_type_id = new Long(p_traderTypeId);
    trader_type_id_is_set = true;
    trader_type_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>trader_type_id</em>カラムに値を設定します。 
   */
  public final void setTraderTypeId( Long p_traderTypeId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trader_type_id = p_traderTypeId;
    trader_type_id_is_set = true;
    trader_type_id_is_modified = true;
  }


  /** 
   * <em>account_type_id</em>カラムの値を設定します。 
   *
   * @@param p_accountTypeId <em>account_type_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountTypeId( long p_accountTypeId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_type_id = new Long(p_accountTypeId);
    account_type_id_is_set = true;
    account_type_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_type_id</em>カラムに値を設定します。 
   */
  public final void setAccountTypeId( Long p_accountTypeId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_type_id = p_accountTypeId;
    account_type_id_is_set = true;
    account_type_id_is_modified = true;
  }


  /** 
   * <em>account_group_id</em>カラムの値を設定します。 
   *
   * @@param p_accountGroupId <em>account_group_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountGroupId( long p_accountGroupId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_group_id = new Long(p_accountGroupId);
    account_group_id_is_set = true;
    account_group_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_group_id</em>カラムに値を設定します。 
   */
  public final void setAccountGroupId( Long p_accountGroupId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_group_id = p_accountGroupId;
    account_group_id_is_set = true;
    account_group_id_is_modified = true;
  }


  /** 
   * <em>account_code_min</em>カラムの値を設定します。 
   *
   * @@param p_accountCodeMin <em>account_code_min</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setAccountCodeMin( int p_accountCodeMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code_min = new Integer(p_accountCodeMin);
    account_code_min_is_set = true;
    account_code_min_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_code_min</em>カラムに値を設定します。 
   */
  public final void setAccountCodeMin( Integer p_accountCodeMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_code_min = p_accountCodeMin;
    account_code_min_is_set = true;
    account_code_min_is_modified = true;
  }


  /** 
   * <em>account_code_max</em>カラムの値を設定します。 
   *
   * @@param p_accountCodeMax <em>account_code_max</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setAccountCodeMax( int p_accountCodeMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code_max = new Integer(p_accountCodeMax);
    account_code_max_is_set = true;
    account_code_max_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_code_max</em>カラムに値を設定します。 
   */
  public final void setAccountCodeMax( Integer p_accountCodeMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_code_max = p_accountCodeMax;
    account_code_max_is_set = true;
    account_code_max_is_modified = true;
  }


  /** 
   * <em>account_code_check_mode</em>カラムの値を設定します。 
   *
   * @@param p_accountCodeCheckMode <em>account_code_check_mode</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountCodeCheckMode( String p_accountCodeCheckMode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code_check_mode = p_accountCodeCheckMode;
    account_code_check_mode_is_set = true;
    account_code_check_mode_is_modified = true;
  }


  /** 
   * <em>insider_default_regist_div</em>カラムの値を設定します。 
   *
   * @@param p_insiderDefaultRegistDiv <em>insider_default_regist_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInsiderDefaultRegistDiv( String p_insiderDefaultRegistDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    insider_default_regist_div = p_insiderDefaultRegistDiv;
    insider_default_regist_div_is_set = true;
    insider_default_regist_div_is_modified = true;
  }


  /** 
   * <em>margin_sys_div</em>カラムの値を設定します。 
   *
   * @@param p_marginSysDiv <em>margin_sys_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginSysDiv( String p_marginSysDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sys_div = p_marginSysDiv;
    margin_sys_div_is_set = true;
    margin_sys_div_is_modified = true;
  }


  /** 
   * <em>margin_gen_div</em>カラムの値を設定します。 
   *
   * @@param p_marginGenDiv <em>margin_gen_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginGenDiv( String p_marginGenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_gen_div = p_marginGenDiv;
    margin_gen_div_is_set = true;
    margin_gen_div_is_modified = true;
  }


  /** 
   * <em>fstk_div</em>カラムの値を設定します。 
   *
   * @@param p_fstkDiv <em>fstk_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFstkDiv( String p_fstkDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fstk_div = p_fstkDiv;
    fstk_div_is_set = true;
    fstk_div_is_modified = true;
  }


  /** 
   * <em>mstk_div</em>カラムの値を設定します。 
   *
   * @@param p_mstkDiv <em>mstk_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMstkDiv( String p_mstkDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mstk_div = p_mstkDiv;
    mstk_div_is_set = true;
    mstk_div_is_modified = true;
  }


  /** 
   * <em>option_div</em>カラムの値を設定します。 
   *
   * @@param p_optionDiv <em>option_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOptionDiv( String p_optionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    option_div = p_optionDiv;
    option_div_is_set = true;
    option_div_is_modified = true;
  }


  /** 
   * <em>future_div</em>カラムの値を設定します。 
   *
   * @@param p_futureDiv <em>future_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFutureDiv( String p_futureDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    future_div = p_futureDiv;
    future_div_is_set = true;
    future_div_is_modified = true;
  }


  /** 
   * <em>mf_div</em>カラムの値を設定します。 
   *
   * @@param p_mfDiv <em>mf_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMfDiv( String p_mfDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mf_div = p_mfDiv;
    mf_div_is_set = true;
    mf_div_is_modified = true;
  }


  /** 
   * <em>ruito_div</em>カラムの値を設定します。 
   *
   * @@param p_ruitoDiv <em>ruito_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRuitoDiv( String p_ruitoDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_div = p_ruitoDiv;
    ruito_div_is_set = true;
    ruito_div_is_modified = true;
  }


  /** 
   * <em>qualified_investor_confirm_div</em>カラムの値を設定します。 
   *
   * @@param p_qualifiedInvestorConfirmDiv <em>qualified_investor_confirm_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setQualifiedInvestorConfirmDiv( String p_qualifiedInvestorConfirmDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    qualified_investor_confirm_div = p_qualifiedInvestorConfirmDiv;
    qualified_investor_confirm_div_is_set = true;
    qualified_investor_confirm_div_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate <em>margin_deposit_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDepositRate( double p_marginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate = new Double(p_marginDepositRate);
    margin_deposit_rate_is_set = true;
    margin_deposit_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_deposit_rate</em>カラムに値を設定します。 
   */
  public final void setMarginDepositRate( Double p_marginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate = p_marginDepositRate;
    margin_deposit_rate_is_set = true;
    margin_deposit_rate_is_modified = true;
  }


  /** 
   * <em>cash_margin_deposit_rate</em>カラムの値を設定します。 
   *
   * @@param p_cashMarginDepositRate <em>cash_margin_deposit_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashMarginDepositRate( double p_cashMarginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_margin_deposit_rate = new Double(p_cashMarginDepositRate);
    cash_margin_deposit_rate_is_set = true;
    cash_margin_deposit_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_margin_deposit_rate</em>カラムに値を設定します。 
   */
  public final void setCashMarginDepositRate( Double p_cashMarginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_margin_deposit_rate = p_cashMarginDepositRate;
    cash_margin_deposit_rate_is_set = true;
    cash_margin_deposit_rate_is_modified = true;
  }


  /** 
   * <em>margin_maintenance_rate</em>カラムの値を設定します。 
   *
   * @@param p_marginMaintenanceRate <em>margin_maintenance_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginMaintenanceRate( double p_marginMaintenanceRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_maintenance_rate = new Double(p_marginMaintenanceRate);
    margin_maintenance_rate_is_set = true;
    margin_maintenance_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_maintenance_rate</em>カラムに値を設定します。 
   */
  public final void setMarginMaintenanceRate( Double p_marginMaintenanceRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_maintenance_rate = p_marginMaintenanceRate;
    margin_maintenance_rate_is_set = true;
    margin_maintenance_rate_is_modified = true;
  }


  /** 
   * <em>min_margin_deposit</em>カラムの値を設定します。 
   *
   * @@param p_minMarginDeposit <em>min_margin_deposit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMinMarginDeposit( double p_minMarginDeposit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    min_margin_deposit = new Double(p_minMarginDeposit);
    min_margin_deposit_is_set = true;
    min_margin_deposit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>min_margin_deposit</em>カラムに値を設定します。 
   */
  public final void setMinMarginDeposit( Double p_minMarginDeposit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    min_margin_deposit = p_minMarginDeposit;
    min_margin_deposit_is_set = true;
    min_margin_deposit_is_modified = true;
  }


  /** 
   * <em>min_ifo_deposit</em>カラムの値を設定します。 
   *
   * @@param p_minIfoDeposit <em>min_ifo_deposit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMinIfoDeposit( double p_minIfoDeposit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    min_ifo_deposit = new Double(p_minIfoDeposit);
    min_ifo_deposit_is_set = true;
    min_ifo_deposit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>min_ifo_deposit</em>カラムに値を設定します。 
   */
  public final void setMinIfoDeposit( Double p_minIfoDeposit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    min_ifo_deposit = p_minIfoDeposit;
    min_ifo_deposit_is_set = true;
    min_ifo_deposit_is_modified = true;
  }


  /** 
   * <em>calc_substitute_rate</em>カラムの値を設定します。 
   *
   * @@param p_calcSubstituteRate <em>calc_substitute_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCalcSubstituteRate( double p_calcSubstituteRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_substitute_rate = new Double(p_calcSubstituteRate);
    calc_substitute_rate_is_set = true;
    calc_substitute_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>calc_substitute_rate</em>カラムに値を設定します。 
   */
  public final void setCalcSubstituteRate( Double p_calcSubstituteRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    calc_substitute_rate = p_calcSubstituteRate;
    calc_substitute_rate_is_set = true;
    calc_substitute_rate_is_modified = true;
  }


  /** 
   * <em>margin_sec_check_rate</em>カラムの値を設定します。 
   *
   * @@param p_marginSecCheckRate <em>margin_sec_check_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginSecCheckRate( double p_marginSecCheckRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sec_check_rate = new Double(p_marginSecCheckRate);
    margin_sec_check_rate_is_set = true;
    margin_sec_check_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_sec_check_rate</em>カラムに値を設定します。 
   */
  public final void setMarginSecCheckRate( Double p_marginSecCheckRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sec_check_rate = p_marginSecCheckRate;
    margin_sec_check_rate_is_set = true;
    margin_sec_check_rate_is_modified = true;
  }


  /** 
   * <em>short_margin_restrain_div</em>カラムの値を設定します。 
   *
   * @@param p_shortMarginRestrainDiv <em>short_margin_restrain_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setShortMarginRestrainDiv( String p_shortMarginRestrainDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_margin_restrain_div = p_shortMarginRestrainDiv;
    short_margin_restrain_div_is_set = true;
    short_margin_restrain_div_is_modified = true;
  }


  /** 
   * <em>short_margin_restrain_unit</em>カラムの値を設定します。 
   *
   * @@param p_shortMarginRestrainUnit <em>short_margin_restrain_unit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setShortMarginRestrainUnit( double p_shortMarginRestrainUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_margin_restrain_unit = new Double(p_shortMarginRestrainUnit);
    short_margin_restrain_unit_is_set = true;
    short_margin_restrain_unit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_margin_restrain_unit</em>カラムに値を設定します。 
   */
  public final void setShortMarginRestrainUnit( Double p_shortMarginRestrainUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_margin_restrain_unit = p_shortMarginRestrainUnit;
    short_margin_restrain_unit_is_set = true;
    short_margin_restrain_unit_is_modified = true;
  }


  /** 
   * <em>short_sell_order_valid_minute</em>カラムの値を設定します。 
   *
   * @@param p_shortSellOrderValidMinute <em>short_sell_order_valid_minute</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setShortSellOrderValidMinute( int p_shortSellOrderValidMinute )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_sell_order_valid_minute = new Integer(p_shortSellOrderValidMinute);
    short_sell_order_valid_minute_is_set = true;
    short_sell_order_valid_minute_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_sell_order_valid_minute</em>カラムに値を設定します。 
   */
  public final void setShortSellOrderValidMinute( Integer p_shortSellOrderValidMinute )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_sell_order_valid_minute = p_shortSellOrderValidMinute;
    short_sell_order_valid_minute_is_set = true;
    short_sell_order_valid_minute_is_modified = true;
  }


  /** 
   * <em>margin_sec_transfer_max_count</em>カラムの値を設定します。 
   *
   * @@param p_marginSecTransferMaxCount <em>margin_sec_transfer_max_count</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setMarginSecTransferMaxCount( int p_marginSecTransferMaxCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sec_transfer_max_count = new Integer(p_marginSecTransferMaxCount);
    margin_sec_transfer_max_count_is_set = true;
    margin_sec_transfer_max_count_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_sec_transfer_max_count</em>カラムに値を設定します。 
   */
  public final void setMarginSecTransferMaxCount( Integer p_marginSecTransferMaxCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sec_transfer_max_count = p_marginSecTransferMaxCount;
    margin_sec_transfer_max_count_is_set = true;
    margin_sec_transfer_max_count_is_modified = true;
  }


  /** 
   * <em>close_worng_equity_margin</em>カラムの値を設定します。 
   *
   * @@param p_closeWorngEquityMargin <em>close_worng_equity_margin</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setCloseWorngEquityMargin( int p_closeWorngEquityMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_worng_equity_margin = new Integer(p_closeWorngEquityMargin);
    close_worng_equity_margin_is_set = true;
    close_worng_equity_margin_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>close_worng_equity_margin</em>カラムに値を設定します。 
   */
  public final void setCloseWorngEquityMargin( Integer p_closeWorngEquityMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    close_worng_equity_margin = p_closeWorngEquityMargin;
    close_worng_equity_margin_is_set = true;
    close_worng_equity_margin_is_modified = true;
  }


  /** 
   * <em>close_worng_option</em>カラムの値を設定します。 
   *
   * @@param p_closeWorngOption <em>close_worng_option</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setCloseWorngOption( int p_closeWorngOption )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_worng_option = new Integer(p_closeWorngOption);
    close_worng_option_is_set = true;
    close_worng_option_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>close_worng_option</em>カラムに値を設定します。 
   */
  public final void setCloseWorngOption( Integer p_closeWorngOption )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    close_worng_option = p_closeWorngOption;
    close_worng_option_is_set = true;
    close_worng_option_is_modified = true;
  }


  /** 
   * <em>close_worng_sys_future</em>カラムの値を設定します。 
   *
   * @@param p_closeWorngSysFuture <em>close_worng_sys_future</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setCloseWorngSysFuture( int p_closeWorngSysFuture )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_worng_sys_future = new Integer(p_closeWorngSysFuture);
    close_worng_sys_future_is_set = true;
    close_worng_sys_future_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>close_worng_sys_future</em>カラムに値を設定します。 
   */
  public final void setCloseWorngSysFuture( Integer p_closeWorngSysFuture )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    close_worng_sys_future = p_closeWorngSysFuture;
    close_worng_sys_future_is_set = true;
    close_worng_sys_future_is_modified = true;
  }


  /** 
   * <em>daily_interest_adjust_amount</em>カラムの値を設定します。 
   *
   * @@param p_dailyInterestAdjustAmount <em>daily_interest_adjust_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDailyInterestAdjustAmount( double p_dailyInterestAdjustAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    daily_interest_adjust_amount = new Double(p_dailyInterestAdjustAmount);
    daily_interest_adjust_amount_is_set = true;
    daily_interest_adjust_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>daily_interest_adjust_amount</em>カラムに値を設定します。 
   */
  public final void setDailyInterestAdjustAmount( Double p_dailyInterestAdjustAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    daily_interest_adjust_amount = p_dailyInterestAdjustAmount;
    daily_interest_adjust_amount_is_set = true;
    daily_interest_adjust_amount_is_modified = true;
  }


  /** 
   * <em>pay_auto_calc_div</em>カラムの値を設定します。 
   *
   * @@param p_payAutoCalcDiv <em>pay_auto_calc_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPayAutoCalcDiv( String p_payAutoCalcDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_auto_calc_div = p_payAutoCalcDiv;
    pay_auto_calc_div_is_set = true;
    pay_auto_calc_div_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * <em>max_handling_price_close_div</em>カラムの値を設定します。 
   *
   * @@param p_maxHandlingPriceCloseDiv <em>max_handling_price_close_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMaxHandlingPriceCloseDiv( String p_maxHandlingPriceCloseDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_handling_price_close_div = p_maxHandlingPriceCloseDiv;
    max_handling_price_close_div_is_set = true;
    max_handling_price_close_div_is_modified = true;
  }


  /** 
   * <em>off_floor_div</em>カラムの値を設定します。 
   *
   * @@param p_offFloorDiv <em>off_floor_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOffFloorDiv( String p_offFloorDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    off_floor_div = p_offFloorDiv;
    off_floor_div_is_set = true;
    off_floor_div_is_modified = true;
  }


  /** 
   * <em>close_worng_feq</em>カラムの値を設定します。 
   *
   * @@param p_closeWorngFeq <em>close_worng_feq</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setCloseWorngFeq( int p_closeWorngFeq )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_worng_feq = new Integer(p_closeWorngFeq);
    close_worng_feq_is_set = true;
    close_worng_feq_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>close_worng_feq</em>カラムに値を設定します。 
   */
  public final void setCloseWorngFeq( Integer p_closeWorngFeq )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    close_worng_feq = p_closeWorngFeq;
    close_worng_feq_is_set = true;
    close_worng_feq_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("admin_type_id") ) {
                    return this.admin_type_id;
                }
                else if ( name.equals("account_type_id") ) {
                    return this.account_type_id;
                }
                else if ( name.equals("account_group_id") ) {
                    return this.account_group_id;
                }
                else if ( name.equals("account_code_min") ) {
                    return this.account_code_min;
                }
                else if ( name.equals("account_code_max") ) {
                    return this.account_code_max;
                }
                else if ( name.equals("account_code_check_mode") ) {
                    return this.account_code_check_mode;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                else if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("branch_name") ) {
                    return this.branch_name;
                }
                else if ( name.equals("branch_name_alt1") ) {
                    return this.branch_name_alt1;
                }
                else if ( name.equals("branch_type") ) {
                    return this.branch_type;
                }
                break;
            case 'c':
                if ( name.equals("cash_margin_deposit_rate") ) {
                    return this.cash_margin_deposit_rate;
                }
                else if ( name.equals("calc_substitute_rate") ) {
                    return this.calc_substitute_rate;
                }
                else if ( name.equals("close_worng_equity_margin") ) {
                    return this.close_worng_equity_margin;
                }
                else if ( name.equals("close_worng_option") ) {
                    return this.close_worng_option;
                }
                else if ( name.equals("close_worng_sys_future") ) {
                    return this.close_worng_sys_future;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("close_worng_feq") ) {
                    return this.close_worng_feq;
                }
                break;
            case 'd':
                if ( name.equals("daily_interest_adjust_amount") ) {
                    return this.daily_interest_adjust_amount;
                }
                break;
            case 'e':
                if ( name.equals("email_address") ) {
                    return this.email_address;
                }
                break;
            case 'f':
                if ( name.equals("fin_sales_law_execution") ) {
                    return this.fin_sales_law_execution;
                }
                else if ( name.equals("fstk_div") ) {
                    return this.fstk_div;
                }
                else if ( name.equals("future_div") ) {
                    return this.future_div;
                }
                break;
            case 'h':
                if ( name.equals("handling_market_make") ) {
                    return this.handling_market_make;
                }
                else if ( name.equals("handling_not_loan_trans_stock") ) {
                    return this.handling_not_loan_trans_stock;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("institution_id") ) {
                    return new Long( institution_id );
                }
                else if ( name.equals("insider_default_regist_div") ) {
                    return this.insider_default_regist_div;
                }
                break;
            case 'l':
                if ( name.equals("login_stop_div") ) {
                    return this.login_stop_div;
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("max_handling_price_ind") ) {
                    return this.max_handling_price_ind;
                }
                else if ( name.equals("max_handling_price_corp") ) {
                    return this.max_handling_price_corp;
                }
                else if ( name.equals("max_handling_price_ind_option") ) {
                    return this.max_handling_price_ind_option;
                }
                else if ( name.equals("max_handling_price_corp_option") ) {
                    return this.max_handling_price_corp_option;
                }
                else if ( name.equals("max_handling_price_ind_future") ) {
                    return this.max_handling_price_ind_future;
                }
                else if ( name.equals("max_handling_price_corp_future") ) {
                    return this.max_handling_price_corp_future;
                }
                else if ( name.equals("max_cont_price_all_ind") ) {
                    return this.max_cont_price_all_ind;
                }
                else if ( name.equals("max_cont_price_all_corp") ) {
                    return this.max_cont_price_all_corp;
                }
                else if ( name.equals("max_cont_price_product_ind") ) {
                    return this.max_cont_price_product_ind;
                }
                else if ( name.equals("max_cont_price_product_corp") ) {
                    return this.max_cont_price_product_corp;
                }
                else if ( name.equals("max_cont_price_1day_ind") ) {
                    return this.max_cont_price_1day_ind;
                }
                else if ( name.equals("max_cont_price_1day_corp") ) {
                    return this.max_cont_price_1day_corp;
                }
                else if ( name.equals("margin_sys_div") ) {
                    return this.margin_sys_div;
                }
                else if ( name.equals("margin_gen_div") ) {
                    return this.margin_gen_div;
                }
                else if ( name.equals("mstk_div") ) {
                    return this.mstk_div;
                }
                else if ( name.equals("mf_div") ) {
                    return this.mf_div;
                }
                else if ( name.equals("margin_deposit_rate") ) {
                    return this.margin_deposit_rate;
                }
                else if ( name.equals("margin_maintenance_rate") ) {
                    return this.margin_maintenance_rate;
                }
                else if ( name.equals("min_margin_deposit") ) {
                    return this.min_margin_deposit;
                }
                else if ( name.equals("min_ifo_deposit") ) {
                    return this.min_ifo_deposit;
                }
                else if ( name.equals("margin_sec_check_rate") ) {
                    return this.margin_sec_check_rate;
                }
                else if ( name.equals("margin_sec_transfer_max_count") ) {
                    return this.margin_sec_transfer_max_count;
                }
                else if ( name.equals("max_handling_price_close_div") ) {
                    return this.max_handling_price_close_div;
                }
                break;
            case 'o':
                if ( name.equals("option_div") ) {
                    return this.option_div;
                }
                else if ( name.equals("off_floor_div") ) {
                    return this.off_floor_div;
                }
                break;
            case 'p':
                if ( name.equals("pay_auto_calc_div") ) {
                    return this.pay_auto_calc_div;
                }
                break;
            case 'q':
                if ( name.equals("qualified_investor_confirm_div") ) {
                    return this.qualified_investor_confirm_div;
                }
                break;
            case 'r':
                if ( name.equals("ruito_div") ) {
                    return this.ruito_div;
                }
                break;
            case 's':
                if ( name.equals("short_margin_restrain_div") ) {
                    return this.short_margin_restrain_div;
                }
                else if ( name.equals("short_margin_restrain_unit") ) {
                    return this.short_margin_restrain_unit;
                }
                else if ( name.equals("short_sell_order_valid_minute") ) {
                    return this.short_sell_order_valid_minute;
                }
                break;
            case 't':
                if ( name.equals("trader_type_id") ) {
                    return this.trader_type_id;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("admin_type_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'admin_type_id' must be Long: '"+value+"' is not." );
                    this.admin_type_id = (Long) value;
                    if (this.admin_type_id_is_set)
                        this.admin_type_id_is_modified = true;
                    this.admin_type_id_is_set = true;
                    return;
                }
                else if ( name.equals("account_type_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_type_id' must be Long: '"+value+"' is not." );
                    this.account_type_id = (Long) value;
                    if (this.account_type_id_is_set)
                        this.account_type_id_is_modified = true;
                    this.account_type_id_is_set = true;
                    return;
                }
                else if ( name.equals("account_group_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_group_id' must be Long: '"+value+"' is not." );
                    this.account_group_id = (Long) value;
                    if (this.account_group_id_is_set)
                        this.account_group_id_is_modified = true;
                    this.account_group_id_is_set = true;
                    return;
                }
                else if ( name.equals("account_code_min") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'account_code_min' must be Integer: '"+value+"' is not." );
                    this.account_code_min = (Integer) value;
                    if (this.account_code_min_is_set)
                        this.account_code_min_is_modified = true;
                    this.account_code_min_is_set = true;
                    return;
                }
                else if ( name.equals("account_code_max") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'account_code_max' must be Integer: '"+value+"' is not." );
                    this.account_code_max = (Integer) value;
                    if (this.account_code_max_is_set)
                        this.account_code_max_is_modified = true;
                    this.account_code_max_is_set = true;
                    return;
                }
                else if ( name.equals("account_code_check_mode") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code_check_mode' must be String: '"+value+"' is not." );
                    this.account_code_check_mode = (String) value;
                    if (this.account_code_check_mode_is_set)
                        this.account_code_check_mode_is_modified = true;
                    this.account_code_check_mode_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                else if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("branch_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_name' must be String: '"+value+"' is not." );
                    this.branch_name = (String) value;
                    if (this.branch_name_is_set)
                        this.branch_name_is_modified = true;
                    this.branch_name_is_set = true;
                    return;
                }
                else if ( name.equals("branch_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_name_alt1' must be String: '"+value+"' is not." );
                    this.branch_name_alt1 = (String) value;
                    if (this.branch_name_alt1_is_set)
                        this.branch_name_alt1_is_modified = true;
                    this.branch_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("branch_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum) )
                        throw new IllegalArgumentException( "value for 'branch_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum: '"+value+"' is not." );
                    this.branch_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum) value;
                    if (this.branch_type_is_set)
                        this.branch_type_is_modified = true;
                    this.branch_type_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("cash_margin_deposit_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_margin_deposit_rate' must be Double: '"+value+"' is not." );
                    this.cash_margin_deposit_rate = (Double) value;
                    if (this.cash_margin_deposit_rate_is_set)
                        this.cash_margin_deposit_rate_is_modified = true;
                    this.cash_margin_deposit_rate_is_set = true;
                    return;
                }
                else if ( name.equals("calc_substitute_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'calc_substitute_rate' must be Double: '"+value+"' is not." );
                    this.calc_substitute_rate = (Double) value;
                    if (this.calc_substitute_rate_is_set)
                        this.calc_substitute_rate_is_modified = true;
                    this.calc_substitute_rate_is_set = true;
                    return;
                }
                else if ( name.equals("close_worng_equity_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'close_worng_equity_margin' must be Integer: '"+value+"' is not." );
                    this.close_worng_equity_margin = (Integer) value;
                    if (this.close_worng_equity_margin_is_set)
                        this.close_worng_equity_margin_is_modified = true;
                    this.close_worng_equity_margin_is_set = true;
                    return;
                }
                else if ( name.equals("close_worng_option") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'close_worng_option' must be Integer: '"+value+"' is not." );
                    this.close_worng_option = (Integer) value;
                    if (this.close_worng_option_is_set)
                        this.close_worng_option_is_modified = true;
                    this.close_worng_option_is_set = true;
                    return;
                }
                else if ( name.equals("close_worng_sys_future") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'close_worng_sys_future' must be Integer: '"+value+"' is not." );
                    this.close_worng_sys_future = (Integer) value;
                    if (this.close_worng_sys_future_is_set)
                        this.close_worng_sys_future_is_modified = true;
                    this.close_worng_sys_future_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("close_worng_feq") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'close_worng_feq' must be Integer: '"+value+"' is not." );
                    this.close_worng_feq = (Integer) value;
                    if (this.close_worng_feq_is_set)
                        this.close_worng_feq_is_modified = true;
                    this.close_worng_feq_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("daily_interest_adjust_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'daily_interest_adjust_amount' must be Double: '"+value+"' is not." );
                    this.daily_interest_adjust_amount = (Double) value;
                    if (this.daily_interest_adjust_amount_is_set)
                        this.daily_interest_adjust_amount_is_modified = true;
                    this.daily_interest_adjust_amount_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("email_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'email_address' must be String: '"+value+"' is not." );
                    this.email_address = (String) value;
                    if (this.email_address_is_set)
                        this.email_address_is_modified = true;
                    this.email_address_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fin_sales_law_execution") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_sales_law_execution' must be String: '"+value+"' is not." );
                    this.fin_sales_law_execution = (String) value;
                    if (this.fin_sales_law_execution_is_set)
                        this.fin_sales_law_execution_is_modified = true;
                    this.fin_sales_law_execution_is_set = true;
                    return;
                }
                else if ( name.equals("fstk_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fstk_div' must be String: '"+value+"' is not." );
                    this.fstk_div = (String) value;
                    if (this.fstk_div_is_set)
                        this.fstk_div_is_modified = true;
                    this.fstk_div_is_set = true;
                    return;
                }
                else if ( name.equals("future_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'future_div' must be String: '"+value+"' is not." );
                    this.future_div = (String) value;
                    if (this.future_div_is_set)
                        this.future_div_is_modified = true;
                    this.future_div_is_set = true;
                    return;
                }
                break;
            case 'h':
                if ( name.equals("handling_market_make") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'handling_market_make' must be Integer: '"+value+"' is not." );
                    this.handling_market_make = (Integer) value;
                    if (this.handling_market_make_is_set)
                        this.handling_market_make_is_modified = true;
                    this.handling_market_make_is_set = true;
                    return;
                }
                else if ( name.equals("handling_not_loan_trans_stock") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'handling_not_loan_trans_stock' must be Integer: '"+value+"' is not." );
                    this.handling_not_loan_trans_stock = (Integer) value;
                    if (this.handling_not_loan_trans_stock_is_set)
                        this.handling_not_loan_trans_stock_is_modified = true;
                    this.handling_not_loan_trans_stock_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("institution_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'institution_id' must be Long: '"+value+"' is not." );
                    this.institution_id = ((Long) value).longValue();
                    if (this.institution_id_is_set)
                        this.institution_id_is_modified = true;
                    this.institution_id_is_set = true;
                    return;
                }
                else if ( name.equals("insider_default_regist_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'insider_default_regist_div' must be String: '"+value+"' is not." );
                    this.insider_default_regist_div = (String) value;
                    if (this.insider_default_regist_div_is_set)
                        this.insider_default_regist_div_is_modified = true;
                    this.insider_default_regist_div_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("login_stop_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'login_stop_div' must be String: '"+value+"' is not." );
                    this.login_stop_div = (String) value;
                    if (this.login_stop_div_is_set)
                        this.login_stop_div_is_modified = true;
                    this.login_stop_div_is_set = true;
                    return;
                }
                else if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("max_handling_price_ind") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'max_handling_price_ind' must be Long: '"+value+"' is not." );
                    this.max_handling_price_ind = (Long) value;
                    if (this.max_handling_price_ind_is_set)
                        this.max_handling_price_ind_is_modified = true;
                    this.max_handling_price_ind_is_set = true;
                    return;
                }
                else if ( name.equals("max_handling_price_corp") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'max_handling_price_corp' must be Long: '"+value+"' is not." );
                    this.max_handling_price_corp = (Long) value;
                    if (this.max_handling_price_corp_is_set)
                        this.max_handling_price_corp_is_modified = true;
                    this.max_handling_price_corp_is_set = true;
                    return;
                }
                else if ( name.equals("max_handling_price_ind_option") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'max_handling_price_ind_option' must be Long: '"+value+"' is not." );
                    this.max_handling_price_ind_option = (Long) value;
                    if (this.max_handling_price_ind_option_is_set)
                        this.max_handling_price_ind_option_is_modified = true;
                    this.max_handling_price_ind_option_is_set = true;
                    return;
                }
                else if ( name.equals("max_handling_price_corp_option") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'max_handling_price_corp_option' must be Long: '"+value+"' is not." );
                    this.max_handling_price_corp_option = (Long) value;
                    if (this.max_handling_price_corp_option_is_set)
                        this.max_handling_price_corp_option_is_modified = true;
                    this.max_handling_price_corp_option_is_set = true;
                    return;
                }
                else if ( name.equals("max_handling_price_ind_future") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'max_handling_price_ind_future' must be Long: '"+value+"' is not." );
                    this.max_handling_price_ind_future = (Long) value;
                    if (this.max_handling_price_ind_future_is_set)
                        this.max_handling_price_ind_future_is_modified = true;
                    this.max_handling_price_ind_future_is_set = true;
                    return;
                }
                else if ( name.equals("max_handling_price_corp_future") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'max_handling_price_corp_future' must be Long: '"+value+"' is not." );
                    this.max_handling_price_corp_future = (Long) value;
                    if (this.max_handling_price_corp_future_is_set)
                        this.max_handling_price_corp_future_is_modified = true;
                    this.max_handling_price_corp_future_is_set = true;
                    return;
                }
                else if ( name.equals("max_cont_price_all_ind") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_cont_price_all_ind' must be Double: '"+value+"' is not." );
                    this.max_cont_price_all_ind = (Double) value;
                    if (this.max_cont_price_all_ind_is_set)
                        this.max_cont_price_all_ind_is_modified = true;
                    this.max_cont_price_all_ind_is_set = true;
                    return;
                }
                else if ( name.equals("max_cont_price_all_corp") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_cont_price_all_corp' must be Double: '"+value+"' is not." );
                    this.max_cont_price_all_corp = (Double) value;
                    if (this.max_cont_price_all_corp_is_set)
                        this.max_cont_price_all_corp_is_modified = true;
                    this.max_cont_price_all_corp_is_set = true;
                    return;
                }
                else if ( name.equals("max_cont_price_product_ind") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_cont_price_product_ind' must be Double: '"+value+"' is not." );
                    this.max_cont_price_product_ind = (Double) value;
                    if (this.max_cont_price_product_ind_is_set)
                        this.max_cont_price_product_ind_is_modified = true;
                    this.max_cont_price_product_ind_is_set = true;
                    return;
                }
                else if ( name.equals("max_cont_price_product_corp") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_cont_price_product_corp' must be Double: '"+value+"' is not." );
                    this.max_cont_price_product_corp = (Double) value;
                    if (this.max_cont_price_product_corp_is_set)
                        this.max_cont_price_product_corp_is_modified = true;
                    this.max_cont_price_product_corp_is_set = true;
                    return;
                }
                else if ( name.equals("max_cont_price_1day_ind") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_cont_price_1day_ind' must be Double: '"+value+"' is not." );
                    this.max_cont_price_1day_ind = (Double) value;
                    if (this.max_cont_price_1day_ind_is_set)
                        this.max_cont_price_1day_ind_is_modified = true;
                    this.max_cont_price_1day_ind_is_set = true;
                    return;
                }
                else if ( name.equals("max_cont_price_1day_corp") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_cont_price_1day_corp' must be Double: '"+value+"' is not." );
                    this.max_cont_price_1day_corp = (Double) value;
                    if (this.max_cont_price_1day_corp_is_set)
                        this.max_cont_price_1day_corp_is_modified = true;
                    this.max_cont_price_1day_corp_is_set = true;
                    return;
                }
                else if ( name.equals("margin_sys_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_sys_div' must be String: '"+value+"' is not." );
                    this.margin_sys_div = (String) value;
                    if (this.margin_sys_div_is_set)
                        this.margin_sys_div_is_modified = true;
                    this.margin_sys_div_is_set = true;
                    return;
                }
                else if ( name.equals("margin_gen_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_gen_div' must be String: '"+value+"' is not." );
                    this.margin_gen_div = (String) value;
                    if (this.margin_gen_div_is_set)
                        this.margin_gen_div_is_modified = true;
                    this.margin_gen_div_is_set = true;
                    return;
                }
                else if ( name.equals("mstk_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mstk_div' must be String: '"+value+"' is not." );
                    this.mstk_div = (String) value;
                    if (this.mstk_div_is_set)
                        this.mstk_div_is_modified = true;
                    this.mstk_div_is_set = true;
                    return;
                }
                else if ( name.equals("mf_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mf_div' must be String: '"+value+"' is not." );
                    this.mf_div = (String) value;
                    if (this.mf_div_is_set)
                        this.mf_div_is_modified = true;
                    this.mf_div_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate = (Double) value;
                    if (this.margin_deposit_rate_is_set)
                        this.margin_deposit_rate_is_modified = true;
                    this.margin_deposit_rate_is_set = true;
                    return;
                }
                else if ( name.equals("margin_maintenance_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_maintenance_rate' must be Double: '"+value+"' is not." );
                    this.margin_maintenance_rate = (Double) value;
                    if (this.margin_maintenance_rate_is_set)
                        this.margin_maintenance_rate_is_modified = true;
                    this.margin_maintenance_rate_is_set = true;
                    return;
                }
                else if ( name.equals("min_margin_deposit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'min_margin_deposit' must be Double: '"+value+"' is not." );
                    this.min_margin_deposit = (Double) value;
                    if (this.min_margin_deposit_is_set)
                        this.min_margin_deposit_is_modified = true;
                    this.min_margin_deposit_is_set = true;
                    return;
                }
                else if ( name.equals("min_ifo_deposit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'min_ifo_deposit' must be Double: '"+value+"' is not." );
                    this.min_ifo_deposit = (Double) value;
                    if (this.min_ifo_deposit_is_set)
                        this.min_ifo_deposit_is_modified = true;
                    this.min_ifo_deposit_is_set = true;
                    return;
                }
                else if ( name.equals("margin_sec_check_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_sec_check_rate' must be Double: '"+value+"' is not." );
                    this.margin_sec_check_rate = (Double) value;
                    if (this.margin_sec_check_rate_is_set)
                        this.margin_sec_check_rate_is_modified = true;
                    this.margin_sec_check_rate_is_set = true;
                    return;
                }
                else if ( name.equals("margin_sec_transfer_max_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'margin_sec_transfer_max_count' must be Integer: '"+value+"' is not." );
                    this.margin_sec_transfer_max_count = (Integer) value;
                    if (this.margin_sec_transfer_max_count_is_set)
                        this.margin_sec_transfer_max_count_is_modified = true;
                    this.margin_sec_transfer_max_count_is_set = true;
                    return;
                }
                else if ( name.equals("max_handling_price_close_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'max_handling_price_close_div' must be String: '"+value+"' is not." );
                    this.max_handling_price_close_div = (String) value;
                    if (this.max_handling_price_close_div_is_set)
                        this.max_handling_price_close_div_is_modified = true;
                    this.max_handling_price_close_div_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("option_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'option_div' must be String: '"+value+"' is not." );
                    this.option_div = (String) value;
                    if (this.option_div_is_set)
                        this.option_div_is_modified = true;
                    this.option_div_is_set = true;
                    return;
                }
                else if ( name.equals("off_floor_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'off_floor_div' must be String: '"+value+"' is not." );
                    this.off_floor_div = (String) value;
                    if (this.off_floor_div_is_set)
                        this.off_floor_div_is_modified = true;
                    this.off_floor_div_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("pay_auto_calc_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pay_auto_calc_div' must be String: '"+value+"' is not." );
                    this.pay_auto_calc_div = (String) value;
                    if (this.pay_auto_calc_div_is_set)
                        this.pay_auto_calc_div_is_modified = true;
                    this.pay_auto_calc_div_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("qualified_investor_confirm_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'qualified_investor_confirm_div' must be String: '"+value+"' is not." );
                    this.qualified_investor_confirm_div = (String) value;
                    if (this.qualified_investor_confirm_div_is_set)
                        this.qualified_investor_confirm_div_is_modified = true;
                    this.qualified_investor_confirm_div_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("ruito_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ruito_div' must be String: '"+value+"' is not." );
                    this.ruito_div = (String) value;
                    if (this.ruito_div_is_set)
                        this.ruito_div_is_modified = true;
                    this.ruito_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("short_margin_restrain_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_margin_restrain_div' must be String: '"+value+"' is not." );
                    this.short_margin_restrain_div = (String) value;
                    if (this.short_margin_restrain_div_is_set)
                        this.short_margin_restrain_div_is_modified = true;
                    this.short_margin_restrain_div_is_set = true;
                    return;
                }
                else if ( name.equals("short_margin_restrain_unit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'short_margin_restrain_unit' must be Double: '"+value+"' is not." );
                    this.short_margin_restrain_unit = (Double) value;
                    if (this.short_margin_restrain_unit_is_set)
                        this.short_margin_restrain_unit_is_modified = true;
                    this.short_margin_restrain_unit_is_set = true;
                    return;
                }
                else if ( name.equals("short_sell_order_valid_minute") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'short_sell_order_valid_minute' must be Integer: '"+value+"' is not." );
                    this.short_sell_order_valid_minute = (Integer) value;
                    if (this.short_sell_order_valid_minute_is_set)
                        this.short_sell_order_valid_minute_is_modified = true;
                    this.short_sell_order_valid_minute_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_type_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'trader_type_id' must be Long: '"+value+"' is not." );
                    this.trader_type_id = (Long) value;
                    if (this.trader_type_id_is_set)
                        this.trader_type_id_is_modified = true;
                    this.trader_type_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
