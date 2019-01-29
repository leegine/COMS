head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.59.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondProductParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * bond_productテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BondProductRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BondProductParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BondProductParams}が{@@link BondProductRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondProductPK 
 * @@see BondProductRow 
 */
public class BondProductParams extends Params implements BondProductRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bond_product";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BondProductRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BondProductRow.TYPE;
  }


  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>product_issue_code</em>カラムの値 
   */
  public  String  product_issue_code;

  /** 
   * <em>bond_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum  bond_type;

  /** 
   * <em>host_product_code</em>カラムの値 
   */
  public  String  host_product_code;

  /** 
   * <em>host_product_issue_code</em>カラムの値 
   */
  public  String  host_product_issue_code;

  /** 
   * <em>issue_date</em>カラムの値 
   */
  public  java.sql.Timestamp  issue_date;

  /** 
   * <em>issue_price</em>カラムの値 
   */
  public  double  issue_price;

  /** 
   * <em>issue_amount</em>カラムの値 
   */
  public  Double  issue_amount;

  /** 
   * <em>par_value</em>カラムの値 
   */
  public  double  par_value;

  /** 
   * <em>maturity_date</em>カラムの値 
   */
  public  java.sql.Timestamp  maturity_date;

  /** 
   * <em>redemption_price</em>カラムの値 
   */
  public  double  redemption_price;

  /** 
   * <em>coupon_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum  coupon_type;

  /** 
   * <em>coupon</em>カラムの値 
   */
  public  double  coupon;

  /** 
   * <em>yearly_interest_payments</em>カラムの値 
   */
  public  int  yearly_interest_payments;

  /** 
   * <em>interest_payment_day_1st</em>カラムの値 
   */
  public  String  interest_payment_day_1st;

  /** 
   * <em>interest_payment_day_2nd</em>カラムの値 
   */
  public  String  interest_payment_day_2nd;

  /** 
   * <em>first_interest_payment_day</em>カラムの値 
   */
  public  java.sql.Timestamp  first_interest_payment_day;

  /** 
   * <em>interest_payment_day</em>カラムの値 
   */
  public  java.sql.Timestamp  interest_payment_day;

  /** 
   * <em>interest_payment_day2</em>カラムの値 
   */
  public  java.sql.Timestamp  interest_payment_day2;

  /** 
   * <em>interest_payment_day3</em>カラムの値 
   */
  public  java.sql.Timestamp  interest_payment_day3;

  /** 
   * <em>interest_payment_day4</em>カラムの値 
   */
  public  java.sql.Timestamp  interest_payment_day4;

  /** 
   * <em>host_recruit_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  host_recruit_start_date;

  /** 
   * <em>host_recruit_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  host_recruit_end_date;

  /** 
   * <em>trade_handle_div</em>カラムの値 
   */
  public  String  trade_handle_div;

  /** 
   * <em>trade_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  trade_start_date;

  /** 
   * <em>trade_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  trade_end_date;

  /** 
   * <em>recruit_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  recruit_start_date;

  /** 
   * <em>recruit_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  recruit_end_date;

  /** 
   * <em>trade_type</em>カラムの値 
   */
  public  String  trade_type;

  /** 
   * <em>product_name</em>カラムの値 
   */
  public  String  product_name;

  /** 
   * <em>buy_price</em>カラムの値 
   */
  public  Double  buy_price;

  /** 
   * <em>sell_price</em>カラムの値 
   */
  public  Double  sell_price;

  /** 
   * <em>trade_unit</em>カラムの値 
   */
  public  double  trade_unit;

  /** 
   * <em>min_face_amount</em>カラムの値 
   */
  public  double  min_face_amount;

  /** 
   * <em>max_face_amount</em>カラムの値 
   */
  public  Double  max_face_amount;

  /** 
   * <em>cal_linked_market_code</em>カラムの値 
   */
  public  String  cal_linked_market_code;

  /** 
   * <em>buy_delivery_date_shiftdays</em>カラムの値 
   */
  public  Integer  buy_delivery_date_shiftdays;

  /** 
   * <em>sell_delivery_date_shiftdays</em>カラムの値 
   */
  public  Integer  sell_delivery_date_shiftdays;

  /** 
   * <em>auto_exec_div</em>カラムの値 
   */
  public  String  auto_exec_div;

  /** 
   * <em>auto_exec_amount</em>カラムの値 
   */
  public  Double  auto_exec_amount;

  /** 
   * <em>auto_exec_limit</em>カラムの値 
   */
  public  Double  auto_exec_limit;

  /** 
   * <em>custodian_code</em>カラムの値 
   */
  public  String  custodian_code;

  /** 
   * <em>host_product_name_1</em>カラムの値 
   */
  public  String  host_product_name_1;

  /** 
   * <em>host_product_name_2</em>カラムの値 
   */
  public  String  host_product_name_2;

  /** 
   * <em>host_short_product_name</em>カラムの値 
   */
  public  String  host_short_product_name;

  /** 
   * <em>isin_code</em>カラムの値 
   */
  public  String  isin_code;

  /** 
   * <em>bond_categ_code</em>カラムの値 
   */
  public  String  bond_categ_code;

  /** 
   * <em>currency_code</em>カラムの値 
   */
  public  String  currency_code;

  /** 
   * <em>issue_market_code</em>カラムの値 
   */
  public  String  issue_market_code;

  /** 
   * <em>issue_association_code</em>カラムの値 
   */
  public  String  issue_association_code;

  /** 
   * <em>accrued_interest_calc_type</em>カラムの値 
   */
  public  String  accrued_interest_calc_type;

  /** 
   * <em>accrued_interest_start_day</em>カラムの値 
   */
  public  java.sql.Timestamp  accrued_interest_start_day;

  /** 
   * <em>special_payment_div</em>カラムの値 
   */
  public  String  special_payment_div;

  /** 
   * <em>floating_interest_period_div</em>カラムの値 
   */
  public  String  floating_interest_period_div;

  /** 
   * <em>floating_interest_period</em>カラムの値 
   */
  public  String  floating_interest_period;

  /** 
   * <em>floating_interest_type</em>カラムの値 
   */
  public  String  floating_interest_type;

  /** 
   * <em>floating_interest_spread</em>カラムの値 
   */
  public  Double  floating_interest_spread;

  /** 
   * <em>floating_min_coupon</em>カラムの値 
   */
  public  Double  floating_min_coupon;

  /** 
   * <em>tax_free_div</em>カラムの値 
   */
  public  String  tax_free_div;

  /** 
   * <em>s_and_p</em>カラムの値 
   */
  public  String  s_and_p;

  /** 
   * <em>moodys</em>カラムの値 
   */
  public  String  moodys;

  /** 
   * <em>cusip</em>カラムの値 
   */
  public  String  cusip;

  /** 
   * <em>buying_fx_rate</em>カラムの値 
   */
  public  Double  buying_fx_rate;

  /** 
   * <em>trading_time_check_div</em>カラムの値 
   */
  public  String  trading_time_check_div;

  /** 
   * <em>mediator_commission_rate</em>カラムの値 
   */
  public  Double  mediator_commission_rate;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>admin_last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  admin_last_updated_timestamp;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>recruit_invitation_div</em>カラムの値 
   */
  public  String  recruit_invitation_div;

  /** 
   * <em>recruit_accept_div</em>カラムの値 
   */
  public  String  recruit_accept_div;

  /** 
   * <em>redemption_term</em>カラムの値 
   */
  public  Integer  redemption_term;

  /** 
   * <em>min_issue_coupon_type</em>カラムの値 
   */
  public  String  min_issue_coupon_type;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>prospectus_check_div</em>カラムの値 
   */
  public  String  prospectus_check_div;

  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean product_issue_code_is_set = false;
  private boolean product_issue_code_is_modified = false;
  private boolean bond_type_is_set = false;
  private boolean bond_type_is_modified = false;
  private boolean host_product_code_is_set = false;
  private boolean host_product_code_is_modified = false;
  private boolean host_product_issue_code_is_set = false;
  private boolean host_product_issue_code_is_modified = false;
  private boolean issue_date_is_set = false;
  private boolean issue_date_is_modified = false;
  private boolean issue_price_is_set = false;
  private boolean issue_price_is_modified = false;
  private boolean issue_amount_is_set = false;
  private boolean issue_amount_is_modified = false;
  private boolean par_value_is_set = false;
  private boolean par_value_is_modified = false;
  private boolean maturity_date_is_set = false;
  private boolean maturity_date_is_modified = false;
  private boolean redemption_price_is_set = false;
  private boolean redemption_price_is_modified = false;
  private boolean coupon_type_is_set = false;
  private boolean coupon_type_is_modified = false;
  private boolean coupon_is_set = false;
  private boolean coupon_is_modified = false;
  private boolean yearly_interest_payments_is_set = false;
  private boolean yearly_interest_payments_is_modified = false;
  private boolean interest_payment_day_1st_is_set = false;
  private boolean interest_payment_day_1st_is_modified = false;
  private boolean interest_payment_day_2nd_is_set = false;
  private boolean interest_payment_day_2nd_is_modified = false;
  private boolean first_interest_payment_day_is_set = false;
  private boolean first_interest_payment_day_is_modified = false;
  private boolean interest_payment_day_is_set = false;
  private boolean interest_payment_day_is_modified = false;
  private boolean interest_payment_day2_is_set = false;
  private boolean interest_payment_day2_is_modified = false;
  private boolean interest_payment_day3_is_set = false;
  private boolean interest_payment_day3_is_modified = false;
  private boolean interest_payment_day4_is_set = false;
  private boolean interest_payment_day4_is_modified = false;
  private boolean host_recruit_start_date_is_set = false;
  private boolean host_recruit_start_date_is_modified = false;
  private boolean host_recruit_end_date_is_set = false;
  private boolean host_recruit_end_date_is_modified = false;
  private boolean trade_handle_div_is_set = false;
  private boolean trade_handle_div_is_modified = false;
  private boolean trade_start_date_is_set = false;
  private boolean trade_start_date_is_modified = false;
  private boolean trade_end_date_is_set = false;
  private boolean trade_end_date_is_modified = false;
  private boolean recruit_start_date_is_set = false;
  private boolean recruit_start_date_is_modified = false;
  private boolean recruit_end_date_is_set = false;
  private boolean recruit_end_date_is_modified = false;
  private boolean trade_type_is_set = false;
  private boolean trade_type_is_modified = false;
  private boolean product_name_is_set = false;
  private boolean product_name_is_modified = false;
  private boolean buy_price_is_set = false;
  private boolean buy_price_is_modified = false;
  private boolean sell_price_is_set = false;
  private boolean sell_price_is_modified = false;
  private boolean trade_unit_is_set = false;
  private boolean trade_unit_is_modified = false;
  private boolean min_face_amount_is_set = false;
  private boolean min_face_amount_is_modified = false;
  private boolean max_face_amount_is_set = false;
  private boolean max_face_amount_is_modified = false;
  private boolean cal_linked_market_code_is_set = false;
  private boolean cal_linked_market_code_is_modified = false;
  private boolean buy_delivery_date_shiftdays_is_set = false;
  private boolean buy_delivery_date_shiftdays_is_modified = false;
  private boolean sell_delivery_date_shiftdays_is_set = false;
  private boolean sell_delivery_date_shiftdays_is_modified = false;
  private boolean auto_exec_div_is_set = false;
  private boolean auto_exec_div_is_modified = false;
  private boolean auto_exec_amount_is_set = false;
  private boolean auto_exec_amount_is_modified = false;
  private boolean auto_exec_limit_is_set = false;
  private boolean auto_exec_limit_is_modified = false;
  private boolean custodian_code_is_set = false;
  private boolean custodian_code_is_modified = false;
  private boolean host_product_name_1_is_set = false;
  private boolean host_product_name_1_is_modified = false;
  private boolean host_product_name_2_is_set = false;
  private boolean host_product_name_2_is_modified = false;
  private boolean host_short_product_name_is_set = false;
  private boolean host_short_product_name_is_modified = false;
  private boolean isin_code_is_set = false;
  private boolean isin_code_is_modified = false;
  private boolean bond_categ_code_is_set = false;
  private boolean bond_categ_code_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean issue_market_code_is_set = false;
  private boolean issue_market_code_is_modified = false;
  private boolean issue_association_code_is_set = false;
  private boolean issue_association_code_is_modified = false;
  private boolean accrued_interest_calc_type_is_set = false;
  private boolean accrued_interest_calc_type_is_modified = false;
  private boolean accrued_interest_start_day_is_set = false;
  private boolean accrued_interest_start_day_is_modified = false;
  private boolean special_payment_div_is_set = false;
  private boolean special_payment_div_is_modified = false;
  private boolean floating_interest_period_div_is_set = false;
  private boolean floating_interest_period_div_is_modified = false;
  private boolean floating_interest_period_is_set = false;
  private boolean floating_interest_period_is_modified = false;
  private boolean floating_interest_type_is_set = false;
  private boolean floating_interest_type_is_modified = false;
  private boolean floating_interest_spread_is_set = false;
  private boolean floating_interest_spread_is_modified = false;
  private boolean floating_min_coupon_is_set = false;
  private boolean floating_min_coupon_is_modified = false;
  private boolean tax_free_div_is_set = false;
  private boolean tax_free_div_is_modified = false;
  private boolean s_and_p_is_set = false;
  private boolean s_and_p_is_modified = false;
  private boolean moodys_is_set = false;
  private boolean moodys_is_modified = false;
  private boolean cusip_is_set = false;
  private boolean cusip_is_modified = false;
  private boolean buying_fx_rate_is_set = false;
  private boolean buying_fx_rate_is_modified = false;
  private boolean trading_time_check_div_is_set = false;
  private boolean trading_time_check_div_is_modified = false;
  private boolean mediator_commission_rate_is_set = false;
  private boolean mediator_commission_rate_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean admin_last_updated_timestamp_is_set = false;
  private boolean admin_last_updated_timestamp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean recruit_invitation_div_is_set = false;
  private boolean recruit_invitation_div_is_modified = false;
  private boolean recruit_accept_div_is_set = false;
  private boolean recruit_accept_div_is_modified = false;
  private boolean redemption_term_is_set = false;
  private boolean redemption_term_is_modified = false;
  private boolean min_issue_coupon_type_is_set = false;
  private boolean min_issue_coupon_type_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean prospectus_check_div_is_set = false;
  private boolean prospectus_check_div_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "product_id=" + product_id
      + "," + "institution_code=" +institution_code
      + "," + "product_code=" +product_code
      + "," + "product_issue_code=" +product_issue_code
      + "," + "bond_type=" +bond_type
      + "," + "host_product_code=" +host_product_code
      + "," + "host_product_issue_code=" +host_product_issue_code
      + "," + "issue_date=" +issue_date
      + "," + "issue_price=" +issue_price
      + "," + "issue_amount=" +issue_amount
      + "," + "par_value=" +par_value
      + "," + "maturity_date=" +maturity_date
      + "," + "redemption_price=" +redemption_price
      + "," + "coupon_type=" +coupon_type
      + "," + "coupon=" +coupon
      + "," + "yearly_interest_payments=" +yearly_interest_payments
      + "," + "interest_payment_day_1st=" +interest_payment_day_1st
      + "," + "interest_payment_day_2nd=" +interest_payment_day_2nd
      + "," + "first_interest_payment_day=" +first_interest_payment_day
      + "," + "interest_payment_day=" +interest_payment_day
      + "," + "interest_payment_day2=" +interest_payment_day2
      + "," + "interest_payment_day3=" +interest_payment_day3
      + "," + "interest_payment_day4=" +interest_payment_day4
      + "," + "host_recruit_start_date=" +host_recruit_start_date
      + "," + "host_recruit_end_date=" +host_recruit_end_date
      + "," + "trade_handle_div=" +trade_handle_div
      + "," + "trade_start_date=" +trade_start_date
      + "," + "trade_end_date=" +trade_end_date
      + "," + "recruit_start_date=" +recruit_start_date
      + "," + "recruit_end_date=" +recruit_end_date
      + "," + "trade_type=" +trade_type
      + "," + "product_name=" +product_name
      + "," + "buy_price=" +buy_price
      + "," + "sell_price=" +sell_price
      + "," + "trade_unit=" +trade_unit
      + "," + "min_face_amount=" +min_face_amount
      + "," + "max_face_amount=" +max_face_amount
      + "," + "cal_linked_market_code=" +cal_linked_market_code
      + "," + "buy_delivery_date_shiftdays=" +buy_delivery_date_shiftdays
      + "," + "sell_delivery_date_shiftdays=" +sell_delivery_date_shiftdays
      + "," + "auto_exec_div=" +auto_exec_div
      + "," + "auto_exec_amount=" +auto_exec_amount
      + "," + "auto_exec_limit=" +auto_exec_limit
      + "," + "custodian_code=" +custodian_code
      + "," + "host_product_name_1=" +host_product_name_1
      + "," + "host_product_name_2=" +host_product_name_2
      + "," + "host_short_product_name=" +host_short_product_name
      + "," + "isin_code=" +isin_code
      + "," + "bond_categ_code=" +bond_categ_code
      + "," + "currency_code=" +currency_code
      + "," + "issue_market_code=" +issue_market_code
      + "," + "issue_association_code=" +issue_association_code
      + "," + "accrued_interest_calc_type=" +accrued_interest_calc_type
      + "," + "accrued_interest_start_day=" +accrued_interest_start_day
      + "," + "special_payment_div=" +special_payment_div
      + "," + "floating_interest_period_div=" +floating_interest_period_div
      + "," + "floating_interest_period=" +floating_interest_period
      + "," + "floating_interest_type=" +floating_interest_type
      + "," + "floating_interest_spread=" +floating_interest_spread
      + "," + "floating_min_coupon=" +floating_min_coupon
      + "," + "tax_free_div=" +tax_free_div
      + "," + "s_and_p=" +s_and_p
      + "," + "moodys=" +moodys
      + "," + "cusip=" +cusip
      + "," + "buying_fx_rate=" +buying_fx_rate
      + "," + "trading_time_check_div=" +trading_time_check_div
      + "," + "mediator_commission_rate=" +mediator_commission_rate
      + "," + "last_updater=" +last_updater
      + "," + "admin_last_updated_timestamp=" +admin_last_updated_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "recruit_invitation_div=" +recruit_invitation_div
      + "," + "recruit_accept_div=" +recruit_accept_div
      + "," + "redemption_term=" +redemption_term
      + "," + "min_issue_coupon_type=" +min_issue_coupon_type
      + "," + "delivery_date=" +delivery_date
      + "," + "prospectus_check_div=" +prospectus_check_div
      + "}";
  }


  /** 
   * 値が未設定のBondProductParamsオブジェクトを作成します。 
   */
  public BondProductParams() {
    product_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBondProductRowオブジェクトの値を利用してBondProductParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBondProductRowオブジェクト 
   */
  public BondProductParams( BondProductRow p_row )
  {
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    product_issue_code = p_row.getProductIssueCode();
    product_issue_code_is_set = p_row.getProductIssueCodeIsSet();
    product_issue_code_is_modified = p_row.getProductIssueCodeIsModified();
    bond_type = p_row.getBondType();
    bond_type_is_set = p_row.getBondTypeIsSet();
    bond_type_is_modified = p_row.getBondTypeIsModified();
    host_product_code = p_row.getHostProductCode();
    host_product_code_is_set = p_row.getHostProductCodeIsSet();
    host_product_code_is_modified = p_row.getHostProductCodeIsModified();
    host_product_issue_code = p_row.getHostProductIssueCode();
    host_product_issue_code_is_set = p_row.getHostProductIssueCodeIsSet();
    host_product_issue_code_is_modified = p_row.getHostProductIssueCodeIsModified();
    issue_date = p_row.getIssueDate();
    issue_date_is_set = p_row.getIssueDateIsSet();
    issue_date_is_modified = p_row.getIssueDateIsModified();
    issue_price = p_row.getIssuePrice();
    issue_price_is_set = p_row.getIssuePriceIsSet();
    issue_price_is_modified = p_row.getIssuePriceIsModified();
    if ( !p_row.getIssueAmountIsNull() )
      issue_amount = new Double( p_row.getIssueAmount() );
    issue_amount_is_set = p_row.getIssueAmountIsSet();
    issue_amount_is_modified = p_row.getIssueAmountIsModified();
    par_value = p_row.getParValue();
    par_value_is_set = p_row.getParValueIsSet();
    par_value_is_modified = p_row.getParValueIsModified();
    maturity_date = p_row.getMaturityDate();
    maturity_date_is_set = p_row.getMaturityDateIsSet();
    maturity_date_is_modified = p_row.getMaturityDateIsModified();
    redemption_price = p_row.getRedemptionPrice();
    redemption_price_is_set = p_row.getRedemptionPriceIsSet();
    redemption_price_is_modified = p_row.getRedemptionPriceIsModified();
    coupon_type = p_row.getCouponType();
    coupon_type_is_set = p_row.getCouponTypeIsSet();
    coupon_type_is_modified = p_row.getCouponTypeIsModified();
    coupon = p_row.getCoupon();
    coupon_is_set = p_row.getCouponIsSet();
    coupon_is_modified = p_row.getCouponIsModified();
    yearly_interest_payments = p_row.getYearlyInterestPayments();
    yearly_interest_payments_is_set = p_row.getYearlyInterestPaymentsIsSet();
    yearly_interest_payments_is_modified = p_row.getYearlyInterestPaymentsIsModified();
    interest_payment_day_1st = p_row.getInterestPaymentDay1st();
    interest_payment_day_1st_is_set = p_row.getInterestPaymentDay1stIsSet();
    interest_payment_day_1st_is_modified = p_row.getInterestPaymentDay1stIsModified();
    interest_payment_day_2nd = p_row.getInterestPaymentDay2nd();
    interest_payment_day_2nd_is_set = p_row.getInterestPaymentDay2ndIsSet();
    interest_payment_day_2nd_is_modified = p_row.getInterestPaymentDay2ndIsModified();
    first_interest_payment_day = p_row.getFirstInterestPaymentDay();
    first_interest_payment_day_is_set = p_row.getFirstInterestPaymentDayIsSet();
    first_interest_payment_day_is_modified = p_row.getFirstInterestPaymentDayIsModified();
    interest_payment_day = p_row.getInterestPaymentDay();
    interest_payment_day_is_set = p_row.getInterestPaymentDayIsSet();
    interest_payment_day_is_modified = p_row.getInterestPaymentDayIsModified();
    interest_payment_day2 = p_row.getInterestPaymentDay2();
    interest_payment_day2_is_set = p_row.getInterestPaymentDay2IsSet();
    interest_payment_day2_is_modified = p_row.getInterestPaymentDay2IsModified();
    interest_payment_day3 = p_row.getInterestPaymentDay3();
    interest_payment_day3_is_set = p_row.getInterestPaymentDay3IsSet();
    interest_payment_day3_is_modified = p_row.getInterestPaymentDay3IsModified();
    interest_payment_day4 = p_row.getInterestPaymentDay4();
    interest_payment_day4_is_set = p_row.getInterestPaymentDay4IsSet();
    interest_payment_day4_is_modified = p_row.getInterestPaymentDay4IsModified();
    host_recruit_start_date = p_row.getHostRecruitStartDate();
    host_recruit_start_date_is_set = p_row.getHostRecruitStartDateIsSet();
    host_recruit_start_date_is_modified = p_row.getHostRecruitStartDateIsModified();
    host_recruit_end_date = p_row.getHostRecruitEndDate();
    host_recruit_end_date_is_set = p_row.getHostRecruitEndDateIsSet();
    host_recruit_end_date_is_modified = p_row.getHostRecruitEndDateIsModified();
    trade_handle_div = p_row.getTradeHandleDiv();
    trade_handle_div_is_set = p_row.getTradeHandleDivIsSet();
    trade_handle_div_is_modified = p_row.getTradeHandleDivIsModified();
    trade_start_date = p_row.getTradeStartDate();
    trade_start_date_is_set = p_row.getTradeStartDateIsSet();
    trade_start_date_is_modified = p_row.getTradeStartDateIsModified();
    trade_end_date = p_row.getTradeEndDate();
    trade_end_date_is_set = p_row.getTradeEndDateIsSet();
    trade_end_date_is_modified = p_row.getTradeEndDateIsModified();
    recruit_start_date = p_row.getRecruitStartDate();
    recruit_start_date_is_set = p_row.getRecruitStartDateIsSet();
    recruit_start_date_is_modified = p_row.getRecruitStartDateIsModified();
    recruit_end_date = p_row.getRecruitEndDate();
    recruit_end_date_is_set = p_row.getRecruitEndDateIsSet();
    recruit_end_date_is_modified = p_row.getRecruitEndDateIsModified();
    trade_type = p_row.getTradeType();
    trade_type_is_set = p_row.getTradeTypeIsSet();
    trade_type_is_modified = p_row.getTradeTypeIsModified();
    product_name = p_row.getProductName();
    product_name_is_set = p_row.getProductNameIsSet();
    product_name_is_modified = p_row.getProductNameIsModified();
    if ( !p_row.getBuyPriceIsNull() )
      buy_price = new Double( p_row.getBuyPrice() );
    buy_price_is_set = p_row.getBuyPriceIsSet();
    buy_price_is_modified = p_row.getBuyPriceIsModified();
    if ( !p_row.getSellPriceIsNull() )
      sell_price = new Double( p_row.getSellPrice() );
    sell_price_is_set = p_row.getSellPriceIsSet();
    sell_price_is_modified = p_row.getSellPriceIsModified();
    trade_unit = p_row.getTradeUnit();
    trade_unit_is_set = p_row.getTradeUnitIsSet();
    trade_unit_is_modified = p_row.getTradeUnitIsModified();
    min_face_amount = p_row.getMinFaceAmount();
    min_face_amount_is_set = p_row.getMinFaceAmountIsSet();
    min_face_amount_is_modified = p_row.getMinFaceAmountIsModified();
    if ( !p_row.getMaxFaceAmountIsNull() )
      max_face_amount = new Double( p_row.getMaxFaceAmount() );
    max_face_amount_is_set = p_row.getMaxFaceAmountIsSet();
    max_face_amount_is_modified = p_row.getMaxFaceAmountIsModified();
    cal_linked_market_code = p_row.getCalLinkedMarketCode();
    cal_linked_market_code_is_set = p_row.getCalLinkedMarketCodeIsSet();
    cal_linked_market_code_is_modified = p_row.getCalLinkedMarketCodeIsModified();
    if ( !p_row.getBuyDeliveryDateShiftdaysIsNull() )
      buy_delivery_date_shiftdays = new Integer( p_row.getBuyDeliveryDateShiftdays() );
    buy_delivery_date_shiftdays_is_set = p_row.getBuyDeliveryDateShiftdaysIsSet();
    buy_delivery_date_shiftdays_is_modified = p_row.getBuyDeliveryDateShiftdaysIsModified();
    if ( !p_row.getSellDeliveryDateShiftdaysIsNull() )
      sell_delivery_date_shiftdays = new Integer( p_row.getSellDeliveryDateShiftdays() );
    sell_delivery_date_shiftdays_is_set = p_row.getSellDeliveryDateShiftdaysIsSet();
    sell_delivery_date_shiftdays_is_modified = p_row.getSellDeliveryDateShiftdaysIsModified();
    auto_exec_div = p_row.getAutoExecDiv();
    auto_exec_div_is_set = p_row.getAutoExecDivIsSet();
    auto_exec_div_is_modified = p_row.getAutoExecDivIsModified();
    if ( !p_row.getAutoExecAmountIsNull() )
      auto_exec_amount = new Double( p_row.getAutoExecAmount() );
    auto_exec_amount_is_set = p_row.getAutoExecAmountIsSet();
    auto_exec_amount_is_modified = p_row.getAutoExecAmountIsModified();
    if ( !p_row.getAutoExecLimitIsNull() )
      auto_exec_limit = new Double( p_row.getAutoExecLimit() );
    auto_exec_limit_is_set = p_row.getAutoExecLimitIsSet();
    auto_exec_limit_is_modified = p_row.getAutoExecLimitIsModified();
    custodian_code = p_row.getCustodianCode();
    custodian_code_is_set = p_row.getCustodianCodeIsSet();
    custodian_code_is_modified = p_row.getCustodianCodeIsModified();
    host_product_name_1 = p_row.getHostProductName1();
    host_product_name_1_is_set = p_row.getHostProductName1IsSet();
    host_product_name_1_is_modified = p_row.getHostProductName1IsModified();
    host_product_name_2 = p_row.getHostProductName2();
    host_product_name_2_is_set = p_row.getHostProductName2IsSet();
    host_product_name_2_is_modified = p_row.getHostProductName2IsModified();
    host_short_product_name = p_row.getHostShortProductName();
    host_short_product_name_is_set = p_row.getHostShortProductNameIsSet();
    host_short_product_name_is_modified = p_row.getHostShortProductNameIsModified();
    isin_code = p_row.getIsinCode();
    isin_code_is_set = p_row.getIsinCodeIsSet();
    isin_code_is_modified = p_row.getIsinCodeIsModified();
    bond_categ_code = p_row.getBondCategCode();
    bond_categ_code_is_set = p_row.getBondCategCodeIsSet();
    bond_categ_code_is_modified = p_row.getBondCategCodeIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    issue_market_code = p_row.getIssueMarketCode();
    issue_market_code_is_set = p_row.getIssueMarketCodeIsSet();
    issue_market_code_is_modified = p_row.getIssueMarketCodeIsModified();
    issue_association_code = p_row.getIssueAssociationCode();
    issue_association_code_is_set = p_row.getIssueAssociationCodeIsSet();
    issue_association_code_is_modified = p_row.getIssueAssociationCodeIsModified();
    accrued_interest_calc_type = p_row.getAccruedInterestCalcType();
    accrued_interest_calc_type_is_set = p_row.getAccruedInterestCalcTypeIsSet();
    accrued_interest_calc_type_is_modified = p_row.getAccruedInterestCalcTypeIsModified();
    accrued_interest_start_day = p_row.getAccruedInterestStartDay();
    accrued_interest_start_day_is_set = p_row.getAccruedInterestStartDayIsSet();
    accrued_interest_start_day_is_modified = p_row.getAccruedInterestStartDayIsModified();
    special_payment_div = p_row.getSpecialPaymentDiv();
    special_payment_div_is_set = p_row.getSpecialPaymentDivIsSet();
    special_payment_div_is_modified = p_row.getSpecialPaymentDivIsModified();
    floating_interest_period_div = p_row.getFloatingInterestPeriodDiv();
    floating_interest_period_div_is_set = p_row.getFloatingInterestPeriodDivIsSet();
    floating_interest_period_div_is_modified = p_row.getFloatingInterestPeriodDivIsModified();
    floating_interest_period = p_row.getFloatingInterestPeriod();
    floating_interest_period_is_set = p_row.getFloatingInterestPeriodIsSet();
    floating_interest_period_is_modified = p_row.getFloatingInterestPeriodIsModified();
    floating_interest_type = p_row.getFloatingInterestType();
    floating_interest_type_is_set = p_row.getFloatingInterestTypeIsSet();
    floating_interest_type_is_modified = p_row.getFloatingInterestTypeIsModified();
    if ( !p_row.getFloatingInterestSpreadIsNull() )
      floating_interest_spread = new Double( p_row.getFloatingInterestSpread() );
    floating_interest_spread_is_set = p_row.getFloatingInterestSpreadIsSet();
    floating_interest_spread_is_modified = p_row.getFloatingInterestSpreadIsModified();
    if ( !p_row.getFloatingMinCouponIsNull() )
      floating_min_coupon = new Double( p_row.getFloatingMinCoupon() );
    floating_min_coupon_is_set = p_row.getFloatingMinCouponIsSet();
    floating_min_coupon_is_modified = p_row.getFloatingMinCouponIsModified();
    tax_free_div = p_row.getTaxFreeDiv();
    tax_free_div_is_set = p_row.getTaxFreeDivIsSet();
    tax_free_div_is_modified = p_row.getTaxFreeDivIsModified();
    s_and_p = p_row.getSAndP();
    s_and_p_is_set = p_row.getSAndPIsSet();
    s_and_p_is_modified = p_row.getSAndPIsModified();
    moodys = p_row.getMoodys();
    moodys_is_set = p_row.getMoodysIsSet();
    moodys_is_modified = p_row.getMoodysIsModified();
    cusip = p_row.getCusip();
    cusip_is_set = p_row.getCusipIsSet();
    cusip_is_modified = p_row.getCusipIsModified();
    if ( !p_row.getBuyingFxRateIsNull() )
      buying_fx_rate = new Double( p_row.getBuyingFxRate() );
    buying_fx_rate_is_set = p_row.getBuyingFxRateIsSet();
    buying_fx_rate_is_modified = p_row.getBuyingFxRateIsModified();
    trading_time_check_div = p_row.getTradingTimeCheckDiv();
    trading_time_check_div_is_set = p_row.getTradingTimeCheckDivIsSet();
    trading_time_check_div_is_modified = p_row.getTradingTimeCheckDivIsModified();
    if ( !p_row.getMediatorCommissionRateIsNull() )
      mediator_commission_rate = new Double( p_row.getMediatorCommissionRate() );
    mediator_commission_rate_is_set = p_row.getMediatorCommissionRateIsSet();
    mediator_commission_rate_is_modified = p_row.getMediatorCommissionRateIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    admin_last_updated_timestamp = p_row.getAdminLastUpdatedTimestamp();
    admin_last_updated_timestamp_is_set = p_row.getAdminLastUpdatedTimestampIsSet();
    admin_last_updated_timestamp_is_modified = p_row.getAdminLastUpdatedTimestampIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    recruit_invitation_div = p_row.getRecruitInvitationDiv();
    recruit_invitation_div_is_set = p_row.getRecruitInvitationDivIsSet();
    recruit_invitation_div_is_modified = p_row.getRecruitInvitationDivIsModified();
    recruit_accept_div = p_row.getRecruitAcceptDiv();
    recruit_accept_div_is_set = p_row.getRecruitAcceptDivIsSet();
    recruit_accept_div_is_modified = p_row.getRecruitAcceptDivIsModified();
    if ( !p_row.getRedemptionTermIsNull() )
      redemption_term = new Integer( p_row.getRedemptionTerm() );
    redemption_term_is_set = p_row.getRedemptionTermIsSet();
    redemption_term_is_modified = p_row.getRedemptionTermIsModified();
    min_issue_coupon_type = p_row.getMinIssueCouponType();
    min_issue_coupon_type_is_set = p_row.getMinIssueCouponTypeIsSet();
    min_issue_coupon_type_is_modified = p_row.getMinIssueCouponTypeIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    prospectus_check_div = p_row.getProspectusCheckDiv();
    prospectus_check_div_is_set = p_row.getProspectusCheckDivIsSet();
    prospectus_check_div_is_modified = p_row.getProspectusCheckDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      product_issue_code_is_set = true;
      product_issue_code_is_modified = true;
      bond_type_is_set = true;
      bond_type_is_modified = true;
      host_product_code_is_set = true;
      host_product_code_is_modified = true;
      host_product_issue_code_is_set = true;
      host_product_issue_code_is_modified = true;
      issue_date_is_set = true;
      issue_date_is_modified = true;
      issue_price_is_set = true;
      issue_price_is_modified = true;
      issue_amount_is_set = true;
      issue_amount_is_modified = true;
      par_value_is_set = true;
      par_value_is_modified = true;
      maturity_date_is_set = true;
      maturity_date_is_modified = true;
      redemption_price_is_set = true;
      redemption_price_is_modified = true;
      coupon_type_is_set = true;
      coupon_type_is_modified = true;
      coupon_is_set = true;
      coupon_is_modified = true;
      yearly_interest_payments_is_set = true;
      yearly_interest_payments_is_modified = true;
      interest_payment_day_1st_is_set = true;
      interest_payment_day_1st_is_modified = true;
      interest_payment_day_2nd_is_set = true;
      interest_payment_day_2nd_is_modified = true;
      first_interest_payment_day_is_set = true;
      first_interest_payment_day_is_modified = true;
      interest_payment_day_is_set = true;
      interest_payment_day_is_modified = true;
      interest_payment_day2_is_set = true;
      interest_payment_day2_is_modified = true;
      interest_payment_day3_is_set = true;
      interest_payment_day3_is_modified = true;
      interest_payment_day4_is_set = true;
      interest_payment_day4_is_modified = true;
      host_recruit_start_date_is_set = true;
      host_recruit_start_date_is_modified = true;
      host_recruit_end_date_is_set = true;
      host_recruit_end_date_is_modified = true;
      trade_handle_div_is_set = true;
      trade_handle_div_is_modified = true;
      trade_start_date_is_set = true;
      trade_start_date_is_modified = true;
      trade_end_date_is_set = true;
      trade_end_date_is_modified = true;
      recruit_start_date_is_set = true;
      recruit_start_date_is_modified = true;
      recruit_end_date_is_set = true;
      recruit_end_date_is_modified = true;
      trade_type_is_set = true;
      trade_type_is_modified = true;
      product_name_is_set = true;
      product_name_is_modified = true;
      buy_price_is_set = true;
      buy_price_is_modified = true;
      sell_price_is_set = true;
      sell_price_is_modified = true;
      trade_unit_is_set = true;
      trade_unit_is_modified = true;
      min_face_amount_is_set = true;
      min_face_amount_is_modified = true;
      max_face_amount_is_set = true;
      max_face_amount_is_modified = true;
      cal_linked_market_code_is_set = true;
      cal_linked_market_code_is_modified = true;
      buy_delivery_date_shiftdays_is_set = true;
      buy_delivery_date_shiftdays_is_modified = true;
      sell_delivery_date_shiftdays_is_set = true;
      sell_delivery_date_shiftdays_is_modified = true;
      auto_exec_div_is_set = true;
      auto_exec_div_is_modified = true;
      auto_exec_amount_is_set = true;
      auto_exec_amount_is_modified = true;
      auto_exec_limit_is_set = true;
      auto_exec_limit_is_modified = true;
      custodian_code_is_set = true;
      custodian_code_is_modified = true;
      host_product_name_1_is_set = true;
      host_product_name_1_is_modified = true;
      host_product_name_2_is_set = true;
      host_product_name_2_is_modified = true;
      host_short_product_name_is_set = true;
      host_short_product_name_is_modified = true;
      isin_code_is_set = true;
      isin_code_is_modified = true;
      bond_categ_code_is_set = true;
      bond_categ_code_is_modified = true;
      currency_code_is_set = true;
      currency_code_is_modified = true;
      issue_market_code_is_set = true;
      issue_market_code_is_modified = true;
      issue_association_code_is_set = true;
      issue_association_code_is_modified = true;
      accrued_interest_calc_type_is_set = true;
      accrued_interest_calc_type_is_modified = true;
      accrued_interest_start_day_is_set = true;
      accrued_interest_start_day_is_modified = true;
      special_payment_div_is_set = true;
      special_payment_div_is_modified = true;
      floating_interest_period_div_is_set = true;
      floating_interest_period_div_is_modified = true;
      floating_interest_period_is_set = true;
      floating_interest_period_is_modified = true;
      floating_interest_type_is_set = true;
      floating_interest_type_is_modified = true;
      floating_interest_spread_is_set = true;
      floating_interest_spread_is_modified = true;
      floating_min_coupon_is_set = true;
      floating_min_coupon_is_modified = true;
      tax_free_div_is_set = true;
      tax_free_div_is_modified = true;
      s_and_p_is_set = true;
      s_and_p_is_modified = true;
      moodys_is_set = true;
      moodys_is_modified = true;
      cusip_is_set = true;
      cusip_is_modified = true;
      buying_fx_rate_is_set = true;
      buying_fx_rate_is_modified = true;
      trading_time_check_div_is_set = true;
      trading_time_check_div_is_modified = true;
      mediator_commission_rate_is_set = true;
      mediator_commission_rate_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      admin_last_updated_timestamp_is_set = true;
      admin_last_updated_timestamp_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      recruit_invitation_div_is_set = true;
      recruit_invitation_div_is_modified = true;
      recruit_accept_div_is_set = true;
      recruit_accept_div_is_modified = true;
      redemption_term_is_set = true;
      redemption_term_is_modified = true;
      min_issue_coupon_type_is_set = true;
      min_issue_coupon_type_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      prospectus_check_div_is_set = true;
      prospectus_check_div_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof BondProductRow ) )
       return false;
    return fieldsEqual( (BondProductRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBondProductRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BondProductRow row )
  {
    if ( product_id != row.getProductId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( product_issue_code == null ) {
      if ( row.getProductIssueCode() != null )
        return false;
    } else if ( !product_issue_code.equals( row.getProductIssueCode() ) ) {
        return false;
    }
    if ( bond_type == null ) {
      if ( row.getBondType() != null )
        return false;
    } else if ( !bond_type.equals( row.getBondType() ) ) {
        return false;
    }
    if ( host_product_code == null ) {
      if ( row.getHostProductCode() != null )
        return false;
    } else if ( !host_product_code.equals( row.getHostProductCode() ) ) {
        return false;
    }
    if ( host_product_issue_code == null ) {
      if ( row.getHostProductIssueCode() != null )
        return false;
    } else if ( !host_product_issue_code.equals( row.getHostProductIssueCode() ) ) {
        return false;
    }
    if ( issue_date == null ) {
      if ( row.getIssueDate() != null )
        return false;
    } else if ( !issue_date.equals( row.getIssueDate() ) ) {
        return false;
    }
    if ( issue_price != row.getIssuePrice() )
      return false;
    if ( issue_amount == null ) {
      if ( !row.getIssueAmountIsNull() )
        return false;
    } else if ( row.getIssueAmountIsNull() || ( issue_amount.doubleValue() != row.getIssueAmount() ) ) {
        return false;
    }
    if ( par_value != row.getParValue() )
      return false;
    if ( maturity_date == null ) {
      if ( row.getMaturityDate() != null )
        return false;
    } else if ( !maturity_date.equals( row.getMaturityDate() ) ) {
        return false;
    }
    if ( redemption_price != row.getRedemptionPrice() )
      return false;
    if ( coupon_type == null ) {
      if ( row.getCouponType() != null )
        return false;
    } else if ( !coupon_type.equals( row.getCouponType() ) ) {
        return false;
    }
    if ( coupon != row.getCoupon() )
      return false;
    if ( yearly_interest_payments != row.getYearlyInterestPayments() )
      return false;
    if ( interest_payment_day_1st == null ) {
      if ( row.getInterestPaymentDay1st() != null )
        return false;
    } else if ( !interest_payment_day_1st.equals( row.getInterestPaymentDay1st() ) ) {
        return false;
    }
    if ( interest_payment_day_2nd == null ) {
      if ( row.getInterestPaymentDay2nd() != null )
        return false;
    } else if ( !interest_payment_day_2nd.equals( row.getInterestPaymentDay2nd() ) ) {
        return false;
    }
    if ( first_interest_payment_day == null ) {
      if ( row.getFirstInterestPaymentDay() != null )
        return false;
    } else if ( !first_interest_payment_day.equals( row.getFirstInterestPaymentDay() ) ) {
        return false;
    }
    if ( interest_payment_day == null ) {
      if ( row.getInterestPaymentDay() != null )
        return false;
    } else if ( !interest_payment_day.equals( row.getInterestPaymentDay() ) ) {
        return false;
    }
    if ( interest_payment_day2 == null ) {
      if ( row.getInterestPaymentDay2() != null )
        return false;
    } else if ( !interest_payment_day2.equals( row.getInterestPaymentDay2() ) ) {
        return false;
    }
    if ( interest_payment_day3 == null ) {
      if ( row.getInterestPaymentDay3() != null )
        return false;
    } else if ( !interest_payment_day3.equals( row.getInterestPaymentDay3() ) ) {
        return false;
    }
    if ( interest_payment_day4 == null ) {
      if ( row.getInterestPaymentDay4() != null )
        return false;
    } else if ( !interest_payment_day4.equals( row.getInterestPaymentDay4() ) ) {
        return false;
    }
    if ( host_recruit_start_date == null ) {
      if ( row.getHostRecruitStartDate() != null )
        return false;
    } else if ( !host_recruit_start_date.equals( row.getHostRecruitStartDate() ) ) {
        return false;
    }
    if ( host_recruit_end_date == null ) {
      if ( row.getHostRecruitEndDate() != null )
        return false;
    } else if ( !host_recruit_end_date.equals( row.getHostRecruitEndDate() ) ) {
        return false;
    }
    if ( trade_handle_div == null ) {
      if ( row.getTradeHandleDiv() != null )
        return false;
    } else if ( !trade_handle_div.equals( row.getTradeHandleDiv() ) ) {
        return false;
    }
    if ( trade_start_date == null ) {
      if ( row.getTradeStartDate() != null )
        return false;
    } else if ( !trade_start_date.equals( row.getTradeStartDate() ) ) {
        return false;
    }
    if ( trade_end_date == null ) {
      if ( row.getTradeEndDate() != null )
        return false;
    } else if ( !trade_end_date.equals( row.getTradeEndDate() ) ) {
        return false;
    }
    if ( recruit_start_date == null ) {
      if ( row.getRecruitStartDate() != null )
        return false;
    } else if ( !recruit_start_date.equals( row.getRecruitStartDate() ) ) {
        return false;
    }
    if ( recruit_end_date == null ) {
      if ( row.getRecruitEndDate() != null )
        return false;
    } else if ( !recruit_end_date.equals( row.getRecruitEndDate() ) ) {
        return false;
    }
    if ( trade_type == null ) {
      if ( row.getTradeType() != null )
        return false;
    } else if ( !trade_type.equals( row.getTradeType() ) ) {
        return false;
    }
    if ( product_name == null ) {
      if ( row.getProductName() != null )
        return false;
    } else if ( !product_name.equals( row.getProductName() ) ) {
        return false;
    }
    if ( buy_price == null ) {
      if ( !row.getBuyPriceIsNull() )
        return false;
    } else if ( row.getBuyPriceIsNull() || ( buy_price.doubleValue() != row.getBuyPrice() ) ) {
        return false;
    }
    if ( sell_price == null ) {
      if ( !row.getSellPriceIsNull() )
        return false;
    } else if ( row.getSellPriceIsNull() || ( sell_price.doubleValue() != row.getSellPrice() ) ) {
        return false;
    }
    if ( trade_unit != row.getTradeUnit() )
      return false;
    if ( min_face_amount != row.getMinFaceAmount() )
      return false;
    if ( max_face_amount == null ) {
      if ( !row.getMaxFaceAmountIsNull() )
        return false;
    } else if ( row.getMaxFaceAmountIsNull() || ( max_face_amount.doubleValue() != row.getMaxFaceAmount() ) ) {
        return false;
    }
    if ( cal_linked_market_code == null ) {
      if ( row.getCalLinkedMarketCode() != null )
        return false;
    } else if ( !cal_linked_market_code.equals( row.getCalLinkedMarketCode() ) ) {
        return false;
    }
    if ( buy_delivery_date_shiftdays == null ) {
      if ( !row.getBuyDeliveryDateShiftdaysIsNull() )
        return false;
    } else if ( row.getBuyDeliveryDateShiftdaysIsNull() || ( buy_delivery_date_shiftdays.intValue() != row.getBuyDeliveryDateShiftdays() ) ) {
        return false;
    }
    if ( sell_delivery_date_shiftdays == null ) {
      if ( !row.getSellDeliveryDateShiftdaysIsNull() )
        return false;
    } else if ( row.getSellDeliveryDateShiftdaysIsNull() || ( sell_delivery_date_shiftdays.intValue() != row.getSellDeliveryDateShiftdays() ) ) {
        return false;
    }
    if ( auto_exec_div == null ) {
      if ( row.getAutoExecDiv() != null )
        return false;
    } else if ( !auto_exec_div.equals( row.getAutoExecDiv() ) ) {
        return false;
    }
    if ( auto_exec_amount == null ) {
      if ( !row.getAutoExecAmountIsNull() )
        return false;
    } else if ( row.getAutoExecAmountIsNull() || ( auto_exec_amount.doubleValue() != row.getAutoExecAmount() ) ) {
        return false;
    }
    if ( auto_exec_limit == null ) {
      if ( !row.getAutoExecLimitIsNull() )
        return false;
    } else if ( row.getAutoExecLimitIsNull() || ( auto_exec_limit.doubleValue() != row.getAutoExecLimit() ) ) {
        return false;
    }
    if ( custodian_code == null ) {
      if ( row.getCustodianCode() != null )
        return false;
    } else if ( !custodian_code.equals( row.getCustodianCode() ) ) {
        return false;
    }
    if ( host_product_name_1 == null ) {
      if ( row.getHostProductName1() != null )
        return false;
    } else if ( !host_product_name_1.equals( row.getHostProductName1() ) ) {
        return false;
    }
    if ( host_product_name_2 == null ) {
      if ( row.getHostProductName2() != null )
        return false;
    } else if ( !host_product_name_2.equals( row.getHostProductName2() ) ) {
        return false;
    }
    if ( host_short_product_name == null ) {
      if ( row.getHostShortProductName() != null )
        return false;
    } else if ( !host_short_product_name.equals( row.getHostShortProductName() ) ) {
        return false;
    }
    if ( isin_code == null ) {
      if ( row.getIsinCode() != null )
        return false;
    } else if ( !isin_code.equals( row.getIsinCode() ) ) {
        return false;
    }
    if ( bond_categ_code == null ) {
      if ( row.getBondCategCode() != null )
        return false;
    } else if ( !bond_categ_code.equals( row.getBondCategCode() ) ) {
        return false;
    }
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( issue_market_code == null ) {
      if ( row.getIssueMarketCode() != null )
        return false;
    } else if ( !issue_market_code.equals( row.getIssueMarketCode() ) ) {
        return false;
    }
    if ( issue_association_code == null ) {
      if ( row.getIssueAssociationCode() != null )
        return false;
    } else if ( !issue_association_code.equals( row.getIssueAssociationCode() ) ) {
        return false;
    }
    if ( accrued_interest_calc_type == null ) {
      if ( row.getAccruedInterestCalcType() != null )
        return false;
    } else if ( !accrued_interest_calc_type.equals( row.getAccruedInterestCalcType() ) ) {
        return false;
    }
    if ( accrued_interest_start_day == null ) {
      if ( row.getAccruedInterestStartDay() != null )
        return false;
    } else if ( !accrued_interest_start_day.equals( row.getAccruedInterestStartDay() ) ) {
        return false;
    }
    if ( special_payment_div == null ) {
      if ( row.getSpecialPaymentDiv() != null )
        return false;
    } else if ( !special_payment_div.equals( row.getSpecialPaymentDiv() ) ) {
        return false;
    }
    if ( floating_interest_period_div == null ) {
      if ( row.getFloatingInterestPeriodDiv() != null )
        return false;
    } else if ( !floating_interest_period_div.equals( row.getFloatingInterestPeriodDiv() ) ) {
        return false;
    }
    if ( floating_interest_period == null ) {
      if ( row.getFloatingInterestPeriod() != null )
        return false;
    } else if ( !floating_interest_period.equals( row.getFloatingInterestPeriod() ) ) {
        return false;
    }
    if ( floating_interest_type == null ) {
      if ( row.getFloatingInterestType() != null )
        return false;
    } else if ( !floating_interest_type.equals( row.getFloatingInterestType() ) ) {
        return false;
    }
    if ( floating_interest_spread == null ) {
      if ( !row.getFloatingInterestSpreadIsNull() )
        return false;
    } else if ( row.getFloatingInterestSpreadIsNull() || ( floating_interest_spread.doubleValue() != row.getFloatingInterestSpread() ) ) {
        return false;
    }
    if ( floating_min_coupon == null ) {
      if ( !row.getFloatingMinCouponIsNull() )
        return false;
    } else if ( row.getFloatingMinCouponIsNull() || ( floating_min_coupon.doubleValue() != row.getFloatingMinCoupon() ) ) {
        return false;
    }
    if ( tax_free_div == null ) {
      if ( row.getTaxFreeDiv() != null )
        return false;
    } else if ( !tax_free_div.equals( row.getTaxFreeDiv() ) ) {
        return false;
    }
    if ( s_and_p == null ) {
      if ( row.getSAndP() != null )
        return false;
    } else if ( !s_and_p.equals( row.getSAndP() ) ) {
        return false;
    }
    if ( moodys == null ) {
      if ( row.getMoodys() != null )
        return false;
    } else if ( !moodys.equals( row.getMoodys() ) ) {
        return false;
    }
    if ( cusip == null ) {
      if ( row.getCusip() != null )
        return false;
    } else if ( !cusip.equals( row.getCusip() ) ) {
        return false;
    }
    if ( buying_fx_rate == null ) {
      if ( !row.getBuyingFxRateIsNull() )
        return false;
    } else if ( row.getBuyingFxRateIsNull() || ( buying_fx_rate.doubleValue() != row.getBuyingFxRate() ) ) {
        return false;
    }
    if ( trading_time_check_div == null ) {
      if ( row.getTradingTimeCheckDiv() != null )
        return false;
    } else if ( !trading_time_check_div.equals( row.getTradingTimeCheckDiv() ) ) {
        return false;
    }
    if ( mediator_commission_rate == null ) {
      if ( !row.getMediatorCommissionRateIsNull() )
        return false;
    } else if ( row.getMediatorCommissionRateIsNull() || ( mediator_commission_rate.doubleValue() != row.getMediatorCommissionRate() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( admin_last_updated_timestamp == null ) {
      if ( row.getAdminLastUpdatedTimestamp() != null )
        return false;
    } else if ( !admin_last_updated_timestamp.equals( row.getAdminLastUpdatedTimestamp() ) ) {
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
    if ( recruit_invitation_div == null ) {
      if ( row.getRecruitInvitationDiv() != null )
        return false;
    } else if ( !recruit_invitation_div.equals( row.getRecruitInvitationDiv() ) ) {
        return false;
    }
    if ( recruit_accept_div == null ) {
      if ( row.getRecruitAcceptDiv() != null )
        return false;
    } else if ( !recruit_accept_div.equals( row.getRecruitAcceptDiv() ) ) {
        return false;
    }
    if ( redemption_term == null ) {
      if ( !row.getRedemptionTermIsNull() )
        return false;
    } else if ( row.getRedemptionTermIsNull() || ( redemption_term.intValue() != row.getRedemptionTerm() ) ) {
        return false;
    }
    if ( min_issue_coupon_type == null ) {
      if ( row.getMinIssueCouponType() != null )
        return false;
    } else if ( !min_issue_coupon_type.equals( row.getMinIssueCouponType() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( prospectus_check_div == null ) {
      if ( row.getProspectusCheckDiv() != null )
        return false;
    } else if ( !prospectus_check_div.equals( row.getProspectusCheckDiv() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) product_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (product_issue_code!=null? product_issue_code.hashCode(): 0) 
        + (bond_type!=null? bond_type.hashCode(): 0) 
        + (host_product_code!=null? host_product_code.hashCode(): 0) 
        + (host_product_issue_code!=null? host_product_issue_code.hashCode(): 0) 
        + (issue_date!=null? issue_date.hashCode(): 0) 
        + ((int) issue_price)
        + (issue_amount!=null? issue_amount.hashCode(): 0) 
        + ((int) par_value)
        + (maturity_date!=null? maturity_date.hashCode(): 0) 
        + ((int) redemption_price)
        + (coupon_type!=null? coupon_type.hashCode(): 0) 
        + ((int) coupon)
        + ((int) yearly_interest_payments)
        + (interest_payment_day_1st!=null? interest_payment_day_1st.hashCode(): 0) 
        + (interest_payment_day_2nd!=null? interest_payment_day_2nd.hashCode(): 0) 
        + (first_interest_payment_day!=null? first_interest_payment_day.hashCode(): 0) 
        + (interest_payment_day!=null? interest_payment_day.hashCode(): 0) 
        + (interest_payment_day2!=null? interest_payment_day2.hashCode(): 0) 
        + (interest_payment_day3!=null? interest_payment_day3.hashCode(): 0) 
        + (interest_payment_day4!=null? interest_payment_day4.hashCode(): 0) 
        + (host_recruit_start_date!=null? host_recruit_start_date.hashCode(): 0) 
        + (host_recruit_end_date!=null? host_recruit_end_date.hashCode(): 0) 
        + (trade_handle_div!=null? trade_handle_div.hashCode(): 0) 
        + (trade_start_date!=null? trade_start_date.hashCode(): 0) 
        + (trade_end_date!=null? trade_end_date.hashCode(): 0) 
        + (recruit_start_date!=null? recruit_start_date.hashCode(): 0) 
        + (recruit_end_date!=null? recruit_end_date.hashCode(): 0) 
        + (trade_type!=null? trade_type.hashCode(): 0) 
        + (product_name!=null? product_name.hashCode(): 0) 
        + (buy_price!=null? buy_price.hashCode(): 0) 
        + (sell_price!=null? sell_price.hashCode(): 0) 
        + ((int) trade_unit)
        + ((int) min_face_amount)
        + (max_face_amount!=null? max_face_amount.hashCode(): 0) 
        + (cal_linked_market_code!=null? cal_linked_market_code.hashCode(): 0) 
        + (buy_delivery_date_shiftdays!=null? buy_delivery_date_shiftdays.hashCode(): 0) 
        + (sell_delivery_date_shiftdays!=null? sell_delivery_date_shiftdays.hashCode(): 0) 
        + (auto_exec_div!=null? auto_exec_div.hashCode(): 0) 
        + (auto_exec_amount!=null? auto_exec_amount.hashCode(): 0) 
        + (auto_exec_limit!=null? auto_exec_limit.hashCode(): 0) 
        + (custodian_code!=null? custodian_code.hashCode(): 0) 
        + (host_product_name_1!=null? host_product_name_1.hashCode(): 0) 
        + (host_product_name_2!=null? host_product_name_2.hashCode(): 0) 
        + (host_short_product_name!=null? host_short_product_name.hashCode(): 0) 
        + (isin_code!=null? isin_code.hashCode(): 0) 
        + (bond_categ_code!=null? bond_categ_code.hashCode(): 0) 
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (issue_market_code!=null? issue_market_code.hashCode(): 0) 
        + (issue_association_code!=null? issue_association_code.hashCode(): 0) 
        + (accrued_interest_calc_type!=null? accrued_interest_calc_type.hashCode(): 0) 
        + (accrued_interest_start_day!=null? accrued_interest_start_day.hashCode(): 0) 
        + (special_payment_div!=null? special_payment_div.hashCode(): 0) 
        + (floating_interest_period_div!=null? floating_interest_period_div.hashCode(): 0) 
        + (floating_interest_period!=null? floating_interest_period.hashCode(): 0) 
        + (floating_interest_type!=null? floating_interest_type.hashCode(): 0) 
        + (floating_interest_spread!=null? floating_interest_spread.hashCode(): 0) 
        + (floating_min_coupon!=null? floating_min_coupon.hashCode(): 0) 
        + (tax_free_div!=null? tax_free_div.hashCode(): 0) 
        + (s_and_p!=null? s_and_p.hashCode(): 0) 
        + (moodys!=null? moodys.hashCode(): 0) 
        + (cusip!=null? cusip.hashCode(): 0) 
        + (buying_fx_rate!=null? buying_fx_rate.hashCode(): 0) 
        + (trading_time_check_div!=null? trading_time_check_div.hashCode(): 0) 
        + (mediator_commission_rate!=null? mediator_commission_rate.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (admin_last_updated_timestamp!=null? admin_last_updated_timestamp.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (recruit_invitation_div!=null? recruit_invitation_div.hashCode(): 0) 
        + (recruit_accept_div!=null? recruit_accept_div.hashCode(): 0) 
        + (redemption_term!=null? redemption_term.hashCode(): 0) 
        + (min_issue_coupon_type!=null? min_issue_coupon_type.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (prospectus_check_div!=null? prospectus_check_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_code' must be set before inserting.");
		if ( !product_issue_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_issue_code' must be set before inserting.");
		if ( !bond_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'bond_type' must be set before inserting.");
		if ( !host_product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'host_product_code' must be set before inserting.");
		if ( !host_product_issue_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'host_product_issue_code' must be set before inserting.");
		if ( !issue_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'issue_date' must be set before inserting.");
		if ( !issue_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'issue_price' must be set before inserting.");
		if ( !par_value_is_set )
			throw new IllegalArgumentException("non-nullable field 'par_value' must be set before inserting.");
		if ( !maturity_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'maturity_date' must be set before inserting.");
		if ( !redemption_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'redemption_price' must be set before inserting.");
		if ( !coupon_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'coupon_type' must be set before inserting.");
		if ( !coupon_is_set )
			throw new IllegalArgumentException("non-nullable field 'coupon' must be set before inserting.");
		if ( !trade_handle_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'trade_handle_div' must be set before inserting.");
		if ( !trade_unit_is_set )
			throw new IllegalArgumentException("non-nullable field 'trade_unit' must be set before inserting.");
		if ( !min_face_amount_is_set )
			throw new IllegalArgumentException("non-nullable field 'min_face_amount' must be set before inserting.");
		if ( !auto_exec_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'auto_exec_div' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("product_id",new Long(product_id));
		map.put("institution_code",institution_code);
		map.put("product_code",product_code);
		map.put("product_issue_code",product_issue_code);
		map.put("bond_type",bond_type);
		map.put("host_product_code",host_product_code);
		map.put("host_product_issue_code",host_product_issue_code);
		map.put("issue_date",issue_date);
		map.put("issue_price",new Double(issue_price));
		if ( issue_amount != null )
			map.put("issue_amount",issue_amount);
		map.put("par_value",new Double(par_value));
		map.put("maturity_date",maturity_date);
		map.put("redemption_price",new Double(redemption_price));
		map.put("coupon_type",coupon_type);
		map.put("coupon",new Double(coupon));
		if ( yearly_interest_payments_is_set )
			map.put("yearly_interest_payments",new Integer(yearly_interest_payments));
		if ( interest_payment_day_1st != null )
			map.put("interest_payment_day_1st",interest_payment_day_1st);
		if ( interest_payment_day_2nd != null )
			map.put("interest_payment_day_2nd",interest_payment_day_2nd);
		if ( first_interest_payment_day != null )
			map.put("first_interest_payment_day",first_interest_payment_day);
		if ( interest_payment_day != null )
			map.put("interest_payment_day",interest_payment_day);
		if ( interest_payment_day2 != null )
			map.put("interest_payment_day2",interest_payment_day2);
		if ( interest_payment_day3 != null )
			map.put("interest_payment_day3",interest_payment_day3);
		if ( interest_payment_day4 != null )
			map.put("interest_payment_day4",interest_payment_day4);
		if ( host_recruit_start_date != null )
			map.put("host_recruit_start_date",host_recruit_start_date);
		if ( host_recruit_end_date != null )
			map.put("host_recruit_end_date",host_recruit_end_date);
		map.put("trade_handle_div",trade_handle_div);
		if ( trade_start_date != null )
			map.put("trade_start_date",trade_start_date);
		if ( trade_end_date != null )
			map.put("trade_end_date",trade_end_date);
		if ( recruit_start_date != null )
			map.put("recruit_start_date",recruit_start_date);
		if ( recruit_end_date != null )
			map.put("recruit_end_date",recruit_end_date);
		if ( trade_type != null )
			map.put("trade_type",trade_type);
		if ( product_name != null )
			map.put("product_name",product_name);
		if ( buy_price != null )
			map.put("buy_price",buy_price);
		if ( sell_price != null )
			map.put("sell_price",sell_price);
		map.put("trade_unit",new Double(trade_unit));
		map.put("min_face_amount",new Double(min_face_amount));
		if ( max_face_amount != null )
			map.put("max_face_amount",max_face_amount);
		if ( cal_linked_market_code != null )
			map.put("cal_linked_market_code",cal_linked_market_code);
		if ( buy_delivery_date_shiftdays != null )
			map.put("buy_delivery_date_shiftdays",buy_delivery_date_shiftdays);
		if ( sell_delivery_date_shiftdays != null )
			map.put("sell_delivery_date_shiftdays",sell_delivery_date_shiftdays);
		map.put("auto_exec_div",auto_exec_div);
		if ( auto_exec_amount != null )
			map.put("auto_exec_amount",auto_exec_amount);
		if ( auto_exec_limit != null )
			map.put("auto_exec_limit",auto_exec_limit);
		if ( custodian_code != null )
			map.put("custodian_code",custodian_code);
		if ( host_product_name_1 != null )
			map.put("host_product_name_1",host_product_name_1);
		if ( host_product_name_2 != null )
			map.put("host_product_name_2",host_product_name_2);
		if ( host_short_product_name != null )
			map.put("host_short_product_name",host_short_product_name);
		if ( isin_code != null )
			map.put("isin_code",isin_code);
		if ( bond_categ_code != null )
			map.put("bond_categ_code",bond_categ_code);
		if ( currency_code != null )
			map.put("currency_code",currency_code);
		if ( issue_market_code != null )
			map.put("issue_market_code",issue_market_code);
		if ( issue_association_code != null )
			map.put("issue_association_code",issue_association_code);
		if ( accrued_interest_calc_type != null )
			map.put("accrued_interest_calc_type",accrued_interest_calc_type);
		if ( accrued_interest_start_day != null )
			map.put("accrued_interest_start_day",accrued_interest_start_day);
		if ( special_payment_div != null )
			map.put("special_payment_div",special_payment_div);
		if ( floating_interest_period_div != null )
			map.put("floating_interest_period_div",floating_interest_period_div);
		if ( floating_interest_period != null )
			map.put("floating_interest_period",floating_interest_period);
		if ( floating_interest_type != null )
			map.put("floating_interest_type",floating_interest_type);
		if ( floating_interest_spread != null )
			map.put("floating_interest_spread",floating_interest_spread);
		if ( floating_min_coupon != null )
			map.put("floating_min_coupon",floating_min_coupon);
		if ( tax_free_div != null )
			map.put("tax_free_div",tax_free_div);
		if ( s_and_p != null )
			map.put("s_and_p",s_and_p);
		if ( moodys != null )
			map.put("moodys",moodys);
		if ( cusip != null )
			map.put("cusip",cusip);
		if ( buying_fx_rate != null )
			map.put("buying_fx_rate",buying_fx_rate);
		if ( trading_time_check_div != null )
			map.put("trading_time_check_div",trading_time_check_div);
		if ( mediator_commission_rate != null )
			map.put("mediator_commission_rate",mediator_commission_rate);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( admin_last_updated_timestamp != null )
			map.put("admin_last_updated_timestamp",admin_last_updated_timestamp);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( recruit_invitation_div != null )
			map.put("recruit_invitation_div",recruit_invitation_div);
		if ( recruit_accept_div != null )
			map.put("recruit_accept_div",recruit_accept_div);
		if ( redemption_term != null )
			map.put("redemption_term",redemption_term);
		if ( min_issue_coupon_type != null )
			map.put("min_issue_coupon_type",min_issue_coupon_type);
		if ( delivery_date != null )
			map.put("delivery_date",delivery_date);
		if ( prospectus_check_div != null )
			map.put("prospectus_check_div",prospectus_check_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( product_issue_code_is_modified )
			map.put("product_issue_code",product_issue_code);
		if ( bond_type_is_modified )
			map.put("bond_type",bond_type);
		if ( host_product_code_is_modified )
			map.put("host_product_code",host_product_code);
		if ( host_product_issue_code_is_modified )
			map.put("host_product_issue_code",host_product_issue_code);
		if ( issue_date_is_modified )
			map.put("issue_date",issue_date);
		if ( issue_price_is_modified )
			map.put("issue_price",new Double(issue_price));
		if ( issue_amount_is_modified )
			map.put("issue_amount",issue_amount);
		if ( par_value_is_modified )
			map.put("par_value",new Double(par_value));
		if ( maturity_date_is_modified )
			map.put("maturity_date",maturity_date);
		if ( redemption_price_is_modified )
			map.put("redemption_price",new Double(redemption_price));
		if ( coupon_type_is_modified )
			map.put("coupon_type",coupon_type);
		if ( coupon_is_modified )
			map.put("coupon",new Double(coupon));
		if ( yearly_interest_payments_is_modified )
			map.put("yearly_interest_payments",new Integer(yearly_interest_payments));
		if ( interest_payment_day_1st_is_modified )
			map.put("interest_payment_day_1st",interest_payment_day_1st);
		if ( interest_payment_day_2nd_is_modified )
			map.put("interest_payment_day_2nd",interest_payment_day_2nd);
		if ( first_interest_payment_day_is_modified )
			map.put("first_interest_payment_day",first_interest_payment_day);
		if ( interest_payment_day_is_modified )
			map.put("interest_payment_day",interest_payment_day);
		if ( interest_payment_day2_is_modified )
			map.put("interest_payment_day2",interest_payment_day2);
		if ( interest_payment_day3_is_modified )
			map.put("interest_payment_day3",interest_payment_day3);
		if ( interest_payment_day4_is_modified )
			map.put("interest_payment_day4",interest_payment_day4);
		if ( host_recruit_start_date_is_modified )
			map.put("host_recruit_start_date",host_recruit_start_date);
		if ( host_recruit_end_date_is_modified )
			map.put("host_recruit_end_date",host_recruit_end_date);
		if ( trade_handle_div_is_modified )
			map.put("trade_handle_div",trade_handle_div);
		if ( trade_start_date_is_modified )
			map.put("trade_start_date",trade_start_date);
		if ( trade_end_date_is_modified )
			map.put("trade_end_date",trade_end_date);
		if ( recruit_start_date_is_modified )
			map.put("recruit_start_date",recruit_start_date);
		if ( recruit_end_date_is_modified )
			map.put("recruit_end_date",recruit_end_date);
		if ( trade_type_is_modified )
			map.put("trade_type",trade_type);
		if ( product_name_is_modified )
			map.put("product_name",product_name);
		if ( buy_price_is_modified )
			map.put("buy_price",buy_price);
		if ( sell_price_is_modified )
			map.put("sell_price",sell_price);
		if ( trade_unit_is_modified )
			map.put("trade_unit",new Double(trade_unit));
		if ( min_face_amount_is_modified )
			map.put("min_face_amount",new Double(min_face_amount));
		if ( max_face_amount_is_modified )
			map.put("max_face_amount",max_face_amount);
		if ( cal_linked_market_code_is_modified )
			map.put("cal_linked_market_code",cal_linked_market_code);
		if ( buy_delivery_date_shiftdays_is_modified )
			map.put("buy_delivery_date_shiftdays",buy_delivery_date_shiftdays);
		if ( sell_delivery_date_shiftdays_is_modified )
			map.put("sell_delivery_date_shiftdays",sell_delivery_date_shiftdays);
		if ( auto_exec_div_is_modified )
			map.put("auto_exec_div",auto_exec_div);
		if ( auto_exec_amount_is_modified )
			map.put("auto_exec_amount",auto_exec_amount);
		if ( auto_exec_limit_is_modified )
			map.put("auto_exec_limit",auto_exec_limit);
		if ( custodian_code_is_modified )
			map.put("custodian_code",custodian_code);
		if ( host_product_name_1_is_modified )
			map.put("host_product_name_1",host_product_name_1);
		if ( host_product_name_2_is_modified )
			map.put("host_product_name_2",host_product_name_2);
		if ( host_short_product_name_is_modified )
			map.put("host_short_product_name",host_short_product_name);
		if ( isin_code_is_modified )
			map.put("isin_code",isin_code);
		if ( bond_categ_code_is_modified )
			map.put("bond_categ_code",bond_categ_code);
		if ( currency_code_is_modified )
			map.put("currency_code",currency_code);
		if ( issue_market_code_is_modified )
			map.put("issue_market_code",issue_market_code);
		if ( issue_association_code_is_modified )
			map.put("issue_association_code",issue_association_code);
		if ( accrued_interest_calc_type_is_modified )
			map.put("accrued_interest_calc_type",accrued_interest_calc_type);
		if ( accrued_interest_start_day_is_modified )
			map.put("accrued_interest_start_day",accrued_interest_start_day);
		if ( special_payment_div_is_modified )
			map.put("special_payment_div",special_payment_div);
		if ( floating_interest_period_div_is_modified )
			map.put("floating_interest_period_div",floating_interest_period_div);
		if ( floating_interest_period_is_modified )
			map.put("floating_interest_period",floating_interest_period);
		if ( floating_interest_type_is_modified )
			map.put("floating_interest_type",floating_interest_type);
		if ( floating_interest_spread_is_modified )
			map.put("floating_interest_spread",floating_interest_spread);
		if ( floating_min_coupon_is_modified )
			map.put("floating_min_coupon",floating_min_coupon);
		if ( tax_free_div_is_modified )
			map.put("tax_free_div",tax_free_div);
		if ( s_and_p_is_modified )
			map.put("s_and_p",s_and_p);
		if ( moodys_is_modified )
			map.put("moodys",moodys);
		if ( cusip_is_modified )
			map.put("cusip",cusip);
		if ( buying_fx_rate_is_modified )
			map.put("buying_fx_rate",buying_fx_rate);
		if ( trading_time_check_div_is_modified )
			map.put("trading_time_check_div",trading_time_check_div);
		if ( mediator_commission_rate_is_modified )
			map.put("mediator_commission_rate",mediator_commission_rate);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( admin_last_updated_timestamp_is_modified )
			map.put("admin_last_updated_timestamp",admin_last_updated_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( recruit_invitation_div_is_modified )
			map.put("recruit_invitation_div",recruit_invitation_div);
		if ( recruit_accept_div_is_modified )
			map.put("recruit_accept_div",recruit_accept_div);
		if ( redemption_term_is_modified )
			map.put("redemption_term",redemption_term);
		if ( min_issue_coupon_type_is_modified )
			map.put("min_issue_coupon_type",min_issue_coupon_type);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( prospectus_check_div_is_modified )
			map.put("prospectus_check_div",prospectus_check_div);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( product_code_is_set )
				map.put("product_code",product_code);
			if ( product_issue_code_is_set )
				map.put("product_issue_code",product_issue_code);
			if ( bond_type_is_set )
				map.put("bond_type",bond_type);
			if ( host_product_code_is_set )
				map.put("host_product_code",host_product_code);
			if ( host_product_issue_code_is_set )
				map.put("host_product_issue_code",host_product_issue_code);
			if ( issue_date_is_set )
				map.put("issue_date",issue_date);
			if ( issue_price_is_set )
				map.put("issue_price",new Double(issue_price));
			map.put("issue_amount",issue_amount);
			if ( par_value_is_set )
				map.put("par_value",new Double(par_value));
			if ( maturity_date_is_set )
				map.put("maturity_date",maturity_date);
			if ( redemption_price_is_set )
				map.put("redemption_price",new Double(redemption_price));
			if ( coupon_type_is_set )
				map.put("coupon_type",coupon_type);
			if ( coupon_is_set )
				map.put("coupon",new Double(coupon));
			if ( yearly_interest_payments_is_set )
				map.put("yearly_interest_payments",new Integer(yearly_interest_payments));
			map.put("interest_payment_day_1st",interest_payment_day_1st);
			map.put("interest_payment_day_2nd",interest_payment_day_2nd);
			map.put("first_interest_payment_day",first_interest_payment_day);
			map.put("interest_payment_day",interest_payment_day);
			map.put("interest_payment_day2",interest_payment_day2);
			map.put("interest_payment_day3",interest_payment_day3);
			map.put("interest_payment_day4",interest_payment_day4);
			map.put("host_recruit_start_date",host_recruit_start_date);
			map.put("host_recruit_end_date",host_recruit_end_date);
			if ( trade_handle_div_is_set )
				map.put("trade_handle_div",trade_handle_div);
			map.put("trade_start_date",trade_start_date);
			map.put("trade_end_date",trade_end_date);
			map.put("recruit_start_date",recruit_start_date);
			map.put("recruit_end_date",recruit_end_date);
			map.put("trade_type",trade_type);
			map.put("product_name",product_name);
			map.put("buy_price",buy_price);
			map.put("sell_price",sell_price);
			if ( trade_unit_is_set )
				map.put("trade_unit",new Double(trade_unit));
			if ( min_face_amount_is_set )
				map.put("min_face_amount",new Double(min_face_amount));
			map.put("max_face_amount",max_face_amount);
			map.put("cal_linked_market_code",cal_linked_market_code);
			map.put("buy_delivery_date_shiftdays",buy_delivery_date_shiftdays);
			map.put("sell_delivery_date_shiftdays",sell_delivery_date_shiftdays);
			if ( auto_exec_div_is_set )
				map.put("auto_exec_div",auto_exec_div);
			map.put("auto_exec_amount",auto_exec_amount);
			map.put("auto_exec_limit",auto_exec_limit);
			map.put("custodian_code",custodian_code);
			map.put("host_product_name_1",host_product_name_1);
			map.put("host_product_name_2",host_product_name_2);
			map.put("host_short_product_name",host_short_product_name);
			map.put("isin_code",isin_code);
			map.put("bond_categ_code",bond_categ_code);
			map.put("currency_code",currency_code);
			map.put("issue_market_code",issue_market_code);
			map.put("issue_association_code",issue_association_code);
			map.put("accrued_interest_calc_type",accrued_interest_calc_type);
			map.put("accrued_interest_start_day",accrued_interest_start_day);
			map.put("special_payment_div",special_payment_div);
			map.put("floating_interest_period_div",floating_interest_period_div);
			map.put("floating_interest_period",floating_interest_period);
			map.put("floating_interest_type",floating_interest_type);
			map.put("floating_interest_spread",floating_interest_spread);
			map.put("floating_min_coupon",floating_min_coupon);
			map.put("tax_free_div",tax_free_div);
			map.put("s_and_p",s_and_p);
			map.put("moodys",moodys);
			map.put("cusip",cusip);
			map.put("buying_fx_rate",buying_fx_rate);
			map.put("trading_time_check_div",trading_time_check_div);
			map.put("mediator_commission_rate",mediator_commission_rate);
			map.put("last_updater",last_updater);
			map.put("admin_last_updated_timestamp",admin_last_updated_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("recruit_invitation_div",recruit_invitation_div);
			map.put("recruit_accept_div",recruit_accept_div);
			map.put("redemption_term",redemption_term);
			map.put("min_issue_coupon_type",min_issue_coupon_type);
			map.put("delivery_date",delivery_date);
			map.put("prospectus_check_div",prospectus_check_div);
		}
		return map;
	}


  /** 
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getProductId()
  {
    return product_id;
  }


  /** 
   * <em>product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsSet() {
    return product_id_is_set;
  }


  /** 
   * <em>product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsModified() {
    return product_id_is_modified;
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
   * <em>product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsSet() {
    return product_code_is_set;
  }


  /** 
   * <em>product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsModified() {
    return product_code_is_modified;
  }


  /** 
   * <em>product_issue_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductIssueCode()
  {
    return product_issue_code;
  }


  /** 
   * <em>product_issue_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIssueCodeIsSet() {
    return product_issue_code_is_set;
  }


  /** 
   * <em>product_issue_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIssueCodeIsModified() {
    return product_issue_code_is_modified;
  }


  /** 
   * <em>bond_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum getBondType()
  {
    return bond_type;
  }


  /** 
   * <em>bond_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondTypeIsSet() {
    return bond_type_is_set;
  }


  /** 
   * <em>bond_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondTypeIsModified() {
    return bond_type_is_modified;
  }


  /** 
   * <em>host_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHostProductCode()
  {
    return host_product_code;
  }


  /** 
   * <em>host_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostProductCodeIsSet() {
    return host_product_code_is_set;
  }


  /** 
   * <em>host_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostProductCodeIsModified() {
    return host_product_code_is_modified;
  }


  /** 
   * <em>host_product_issue_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHostProductIssueCode()
  {
    return host_product_issue_code;
  }


  /** 
   * <em>host_product_issue_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostProductIssueCodeIsSet() {
    return host_product_issue_code_is_set;
  }


  /** 
   * <em>host_product_issue_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostProductIssueCodeIsModified() {
    return host_product_issue_code_is_modified;
  }


  /** 
   * <em>issue_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getIssueDate()
  {
    return issue_date;
  }


  /** 
   * <em>issue_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIssueDateIsSet() {
    return issue_date_is_set;
  }


  /** 
   * <em>issue_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIssueDateIsModified() {
    return issue_date_is_modified;
  }


  /** 
   * <em>issue_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getIssuePrice()
  {
    return issue_price;
  }


  /** 
   * <em>issue_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIssuePriceIsSet() {
    return issue_price_is_set;
  }


  /** 
   * <em>issue_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIssuePriceIsModified() {
    return issue_price_is_modified;
  }


  /** 
   * <em>issue_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getIssueAmount()
  {
    return ( issue_amount==null? ((double)0): issue_amount.doubleValue() );
  }


  /** 
   * <em>issue_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getIssueAmountIsNull()
  {
    return issue_amount == null;
  }


  /** 
   * <em>issue_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIssueAmountIsSet() {
    return issue_amount_is_set;
  }


  /** 
   * <em>issue_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIssueAmountIsModified() {
    return issue_amount_is_modified;
  }


  /** 
   * <em>par_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getParValue()
  {
    return par_value;
  }


  /** 
   * <em>par_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParValueIsSet() {
    return par_value_is_set;
  }


  /** 
   * <em>par_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParValueIsModified() {
    return par_value_is_modified;
  }


  /** 
   * <em>maturity_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getMaturityDate()
  {
    return maturity_date;
  }


  /** 
   * <em>maturity_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaturityDateIsSet() {
    return maturity_date_is_set;
  }


  /** 
   * <em>maturity_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaturityDateIsModified() {
    return maturity_date_is_modified;
  }


  /** 
   * <em>redemption_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRedemptionPrice()
  {
    return redemption_price;
  }


  /** 
   * <em>redemption_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRedemptionPriceIsSet() {
    return redemption_price_is_set;
  }


  /** 
   * <em>redemption_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRedemptionPriceIsModified() {
    return redemption_price_is_modified;
  }


  /** 
   * <em>coupon_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum getCouponType()
  {
    return coupon_type;
  }


  /** 
   * <em>coupon_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCouponTypeIsSet() {
    return coupon_type_is_set;
  }


  /** 
   * <em>coupon_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCouponTypeIsModified() {
    return coupon_type_is_modified;
  }


  /** 
   * <em>coupon</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCoupon()
  {
    return coupon;
  }


  /** 
   * <em>coupon</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCouponIsSet() {
    return coupon_is_set;
  }


  /** 
   * <em>coupon</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCouponIsModified() {
    return coupon_is_modified;
  }


  /** 
   * <em>yearly_interest_payments</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getYearlyInterestPayments()
  {
    return yearly_interest_payments;
  }


  /** 
   * <em>yearly_interest_payments</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getYearlyInterestPaymentsIsSet() {
    return yearly_interest_payments_is_set;
  }


  /** 
   * <em>yearly_interest_payments</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getYearlyInterestPaymentsIsModified() {
    return yearly_interest_payments_is_modified;
  }


  /** 
   * <em>interest_payment_day_1st</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInterestPaymentDay1st()
  {
    return interest_payment_day_1st;
  }


  /** 
   * <em>interest_payment_day_1st</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestPaymentDay1stIsSet() {
    return interest_payment_day_1st_is_set;
  }


  /** 
   * <em>interest_payment_day_1st</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestPaymentDay1stIsModified() {
    return interest_payment_day_1st_is_modified;
  }


  /** 
   * <em>interest_payment_day_2nd</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInterestPaymentDay2nd()
  {
    return interest_payment_day_2nd;
  }


  /** 
   * <em>interest_payment_day_2nd</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestPaymentDay2ndIsSet() {
    return interest_payment_day_2nd_is_set;
  }


  /** 
   * <em>interest_payment_day_2nd</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestPaymentDay2ndIsModified() {
    return interest_payment_day_2nd_is_modified;
  }


  /** 
   * <em>first_interest_payment_day</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getFirstInterestPaymentDay()
  {
    return first_interest_payment_day;
  }


  /** 
   * <em>first_interest_payment_day</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstInterestPaymentDayIsSet() {
    return first_interest_payment_day_is_set;
  }


  /** 
   * <em>first_interest_payment_day</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstInterestPaymentDayIsModified() {
    return first_interest_payment_day_is_modified;
  }


  /** 
   * <em>interest_payment_day</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getInterestPaymentDay()
  {
    return interest_payment_day;
  }


  /** 
   * <em>interest_payment_day</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestPaymentDayIsSet() {
    return interest_payment_day_is_set;
  }


  /** 
   * <em>interest_payment_day</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestPaymentDayIsModified() {
    return interest_payment_day_is_modified;
  }


  /** 
   * <em>interest_payment_day2</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getInterestPaymentDay2()
  {
    return interest_payment_day2;
  }


  /** 
   * <em>interest_payment_day2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestPaymentDay2IsSet() {
    return interest_payment_day2_is_set;
  }


  /** 
   * <em>interest_payment_day2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestPaymentDay2IsModified() {
    return interest_payment_day2_is_modified;
  }


  /** 
   * <em>interest_payment_day3</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getInterestPaymentDay3()
  {
    return interest_payment_day3;
  }


  /** 
   * <em>interest_payment_day3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestPaymentDay3IsSet() {
    return interest_payment_day3_is_set;
  }


  /** 
   * <em>interest_payment_day3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestPaymentDay3IsModified() {
    return interest_payment_day3_is_modified;
  }


  /** 
   * <em>interest_payment_day4</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getInterestPaymentDay4()
  {
    return interest_payment_day4;
  }


  /** 
   * <em>interest_payment_day4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestPaymentDay4IsSet() {
    return interest_payment_day4_is_set;
  }


  /** 
   * <em>interest_payment_day4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestPaymentDay4IsModified() {
    return interest_payment_day4_is_modified;
  }


  /** 
   * <em>host_recruit_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getHostRecruitStartDate()
  {
    return host_recruit_start_date;
  }


  /** 
   * <em>host_recruit_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostRecruitStartDateIsSet() {
    return host_recruit_start_date_is_set;
  }


  /** 
   * <em>host_recruit_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostRecruitStartDateIsModified() {
    return host_recruit_start_date_is_modified;
  }


  /** 
   * <em>host_recruit_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getHostRecruitEndDate()
  {
    return host_recruit_end_date;
  }


  /** 
   * <em>host_recruit_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostRecruitEndDateIsSet() {
    return host_recruit_end_date_is_set;
  }


  /** 
   * <em>host_recruit_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostRecruitEndDateIsModified() {
    return host_recruit_end_date_is_modified;
  }


  /** 
   * <em>trade_handle_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradeHandleDiv()
  {
    return trade_handle_div;
  }


  /** 
   * <em>trade_handle_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeHandleDivIsSet() {
    return trade_handle_div_is_set;
  }


  /** 
   * <em>trade_handle_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeHandleDivIsModified() {
    return trade_handle_div_is_modified;
  }


  /** 
   * <em>trade_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTradeStartDate()
  {
    return trade_start_date;
  }


  /** 
   * <em>trade_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeStartDateIsSet() {
    return trade_start_date_is_set;
  }


  /** 
   * <em>trade_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeStartDateIsModified() {
    return trade_start_date_is_modified;
  }


  /** 
   * <em>trade_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTradeEndDate()
  {
    return trade_end_date;
  }


  /** 
   * <em>trade_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeEndDateIsSet() {
    return trade_end_date_is_set;
  }


  /** 
   * <em>trade_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeEndDateIsModified() {
    return trade_end_date_is_modified;
  }


  /** 
   * <em>recruit_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getRecruitStartDate()
  {
    return recruit_start_date;
  }


  /** 
   * <em>recruit_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitStartDateIsSet() {
    return recruit_start_date_is_set;
  }


  /** 
   * <em>recruit_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitStartDateIsModified() {
    return recruit_start_date_is_modified;
  }


  /** 
   * <em>recruit_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getRecruitEndDate()
  {
    return recruit_end_date;
  }


  /** 
   * <em>recruit_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitEndDateIsSet() {
    return recruit_end_date_is_set;
  }


  /** 
   * <em>recruit_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitEndDateIsModified() {
    return recruit_end_date_is_modified;
  }


  /** 
   * <em>trade_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradeType()
  {
    return trade_type;
  }


  /** 
   * <em>trade_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeTypeIsSet() {
    return trade_type_is_set;
  }


  /** 
   * <em>trade_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeTypeIsModified() {
    return trade_type_is_modified;
  }


  /** 
   * <em>product_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductName()
  {
    return product_name;
  }


  /** 
   * <em>product_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductNameIsSet() {
    return product_name_is_set;
  }


  /** 
   * <em>product_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductNameIsModified() {
    return product_name_is_modified;
  }


  /** 
   * <em>buy_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBuyPrice()
  {
    return ( buy_price==null? ((double)0): buy_price.doubleValue() );
  }


  /** 
   * <em>buy_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyPriceIsNull()
  {
    return buy_price == null;
  }


  /** 
   * <em>buy_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyPriceIsSet() {
    return buy_price_is_set;
  }


  /** 
   * <em>buy_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyPriceIsModified() {
    return buy_price_is_modified;
  }


  /** 
   * <em>sell_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSellPrice()
  {
    return ( sell_price==null? ((double)0): sell_price.doubleValue() );
  }


  /** 
   * <em>sell_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellPriceIsNull()
  {
    return sell_price == null;
  }


  /** 
   * <em>sell_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellPriceIsSet() {
    return sell_price_is_set;
  }


  /** 
   * <em>sell_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellPriceIsModified() {
    return sell_price_is_modified;
  }


  /** 
   * <em>trade_unit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTradeUnit()
  {
    return trade_unit;
  }


  /** 
   * <em>trade_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeUnitIsSet() {
    return trade_unit_is_set;
  }


  /** 
   * <em>trade_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeUnitIsModified() {
    return trade_unit_is_modified;
  }


  /** 
   * <em>min_face_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMinFaceAmount()
  {
    return min_face_amount;
  }


  /** 
   * <em>min_face_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinFaceAmountIsSet() {
    return min_face_amount_is_set;
  }


  /** 
   * <em>min_face_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinFaceAmountIsModified() {
    return min_face_amount_is_modified;
  }


  /** 
   * <em>max_face_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMaxFaceAmount()
  {
    return ( max_face_amount==null? ((double)0): max_face_amount.doubleValue() );
  }


  /** 
   * <em>max_face_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxFaceAmountIsNull()
  {
    return max_face_amount == null;
  }


  /** 
   * <em>max_face_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxFaceAmountIsSet() {
    return max_face_amount_is_set;
  }


  /** 
   * <em>max_face_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxFaceAmountIsModified() {
    return max_face_amount_is_modified;
  }


  /** 
   * <em>cal_linked_market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCalLinkedMarketCode()
  {
    return cal_linked_market_code;
  }


  /** 
   * <em>cal_linked_market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalLinkedMarketCodeIsSet() {
    return cal_linked_market_code_is_set;
  }


  /** 
   * <em>cal_linked_market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalLinkedMarketCodeIsModified() {
    return cal_linked_market_code_is_modified;
  }


  /** 
   * <em>buy_delivery_date_shiftdays</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getBuyDeliveryDateShiftdays()
  {
    return ( buy_delivery_date_shiftdays==null? ((int)0): buy_delivery_date_shiftdays.intValue() );
  }


  /** 
   * <em>buy_delivery_date_shiftdays</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyDeliveryDateShiftdaysIsNull()
  {
    return buy_delivery_date_shiftdays == null;
  }


  /** 
   * <em>buy_delivery_date_shiftdays</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyDeliveryDateShiftdaysIsSet() {
    return buy_delivery_date_shiftdays_is_set;
  }


  /** 
   * <em>buy_delivery_date_shiftdays</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyDeliveryDateShiftdaysIsModified() {
    return buy_delivery_date_shiftdays_is_modified;
  }


  /** 
   * <em>sell_delivery_date_shiftdays</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSellDeliveryDateShiftdays()
  {
    return ( sell_delivery_date_shiftdays==null? ((int)0): sell_delivery_date_shiftdays.intValue() );
  }


  /** 
   * <em>sell_delivery_date_shiftdays</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellDeliveryDateShiftdaysIsNull()
  {
    return sell_delivery_date_shiftdays == null;
  }


  /** 
   * <em>sell_delivery_date_shiftdays</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellDeliveryDateShiftdaysIsSet() {
    return sell_delivery_date_shiftdays_is_set;
  }


  /** 
   * <em>sell_delivery_date_shiftdays</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellDeliveryDateShiftdaysIsModified() {
    return sell_delivery_date_shiftdays_is_modified;
  }


  /** 
   * <em>auto_exec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAutoExecDiv()
  {
    return auto_exec_div;
  }


  /** 
   * <em>auto_exec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAutoExecDivIsSet() {
    return auto_exec_div_is_set;
  }


  /** 
   * <em>auto_exec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAutoExecDivIsModified() {
    return auto_exec_div_is_modified;
  }


  /** 
   * <em>auto_exec_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAutoExecAmount()
  {
    return ( auto_exec_amount==null? ((double)0): auto_exec_amount.doubleValue() );
  }


  /** 
   * <em>auto_exec_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAutoExecAmountIsNull()
  {
    return auto_exec_amount == null;
  }


  /** 
   * <em>auto_exec_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAutoExecAmountIsSet() {
    return auto_exec_amount_is_set;
  }


  /** 
   * <em>auto_exec_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAutoExecAmountIsModified() {
    return auto_exec_amount_is_modified;
  }


  /** 
   * <em>auto_exec_limit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAutoExecLimit()
  {
    return ( auto_exec_limit==null? ((double)0): auto_exec_limit.doubleValue() );
  }


  /** 
   * <em>auto_exec_limit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAutoExecLimitIsNull()
  {
    return auto_exec_limit == null;
  }


  /** 
   * <em>auto_exec_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAutoExecLimitIsSet() {
    return auto_exec_limit_is_set;
  }


  /** 
   * <em>auto_exec_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAutoExecLimitIsModified() {
    return auto_exec_limit_is_modified;
  }


  /** 
   * <em>custodian_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCustodianCode()
  {
    return custodian_code;
  }


  /** 
   * <em>custodian_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustodianCodeIsSet() {
    return custodian_code_is_set;
  }


  /** 
   * <em>custodian_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustodianCodeIsModified() {
    return custodian_code_is_modified;
  }


  /** 
   * <em>host_product_name_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHostProductName1()
  {
    return host_product_name_1;
  }


  /** 
   * <em>host_product_name_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostProductName1IsSet() {
    return host_product_name_1_is_set;
  }


  /** 
   * <em>host_product_name_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostProductName1IsModified() {
    return host_product_name_1_is_modified;
  }


  /** 
   * <em>host_product_name_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHostProductName2()
  {
    return host_product_name_2;
  }


  /** 
   * <em>host_product_name_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostProductName2IsSet() {
    return host_product_name_2_is_set;
  }


  /** 
   * <em>host_product_name_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostProductName2IsModified() {
    return host_product_name_2_is_modified;
  }


  /** 
   * <em>host_short_product_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHostShortProductName()
  {
    return host_short_product_name;
  }


  /** 
   * <em>host_short_product_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostShortProductNameIsSet() {
    return host_short_product_name_is_set;
  }


  /** 
   * <em>host_short_product_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostShortProductNameIsModified() {
    return host_short_product_name_is_modified;
  }


  /** 
   * <em>isin_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIsinCode()
  {
    return isin_code;
  }


  /** 
   * <em>isin_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIsinCodeIsSet() {
    return isin_code_is_set;
  }


  /** 
   * <em>isin_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIsinCodeIsModified() {
    return isin_code_is_modified;
  }


  /** 
   * <em>bond_categ_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBondCategCode()
  {
    return bond_categ_code;
  }


  /** 
   * <em>bond_categ_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondCategCodeIsSet() {
    return bond_categ_code_is_set;
  }


  /** 
   * <em>bond_categ_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondCategCodeIsModified() {
    return bond_categ_code_is_modified;
  }


  /** 
   * <em>currency_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCurrencyCode()
  {
    return currency_code;
  }


  /** 
   * <em>currency_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsSet() {
    return currency_code_is_set;
  }


  /** 
   * <em>currency_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsModified() {
    return currency_code_is_modified;
  }


  /** 
   * <em>issue_market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIssueMarketCode()
  {
    return issue_market_code;
  }


  /** 
   * <em>issue_market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIssueMarketCodeIsSet() {
    return issue_market_code_is_set;
  }


  /** 
   * <em>issue_market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIssueMarketCodeIsModified() {
    return issue_market_code_is_modified;
  }


  /** 
   * <em>issue_association_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIssueAssociationCode()
  {
    return issue_association_code;
  }


  /** 
   * <em>issue_association_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIssueAssociationCodeIsSet() {
    return issue_association_code_is_set;
  }


  /** 
   * <em>issue_association_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIssueAssociationCodeIsModified() {
    return issue_association_code_is_modified;
  }


  /** 
   * <em>accrued_interest_calc_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccruedInterestCalcType()
  {
    return accrued_interest_calc_type;
  }


  /** 
   * <em>accrued_interest_calc_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccruedInterestCalcTypeIsSet() {
    return accrued_interest_calc_type_is_set;
  }


  /** 
   * <em>accrued_interest_calc_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccruedInterestCalcTypeIsModified() {
    return accrued_interest_calc_type_is_modified;
  }


  /** 
   * <em>accrued_interest_start_day</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAccruedInterestStartDay()
  {
    return accrued_interest_start_day;
  }


  /** 
   * <em>accrued_interest_start_day</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccruedInterestStartDayIsSet() {
    return accrued_interest_start_day_is_set;
  }


  /** 
   * <em>accrued_interest_start_day</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccruedInterestStartDayIsModified() {
    return accrued_interest_start_day_is_modified;
  }


  /** 
   * <em>special_payment_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSpecialPaymentDiv()
  {
    return special_payment_div;
  }


  /** 
   * <em>special_payment_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialPaymentDivIsSet() {
    return special_payment_div_is_set;
  }


  /** 
   * <em>special_payment_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialPaymentDivIsModified() {
    return special_payment_div_is_modified;
  }


  /** 
   * <em>floating_interest_period_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFloatingInterestPeriodDiv()
  {
    return floating_interest_period_div;
  }


  /** 
   * <em>floating_interest_period_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFloatingInterestPeriodDivIsSet() {
    return floating_interest_period_div_is_set;
  }


  /** 
   * <em>floating_interest_period_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFloatingInterestPeriodDivIsModified() {
    return floating_interest_period_div_is_modified;
  }


  /** 
   * <em>floating_interest_period</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFloatingInterestPeriod()
  {
    return floating_interest_period;
  }


  /** 
   * <em>floating_interest_period</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFloatingInterestPeriodIsSet() {
    return floating_interest_period_is_set;
  }


  /** 
   * <em>floating_interest_period</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFloatingInterestPeriodIsModified() {
    return floating_interest_period_is_modified;
  }


  /** 
   * <em>floating_interest_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFloatingInterestType()
  {
    return floating_interest_type;
  }


  /** 
   * <em>floating_interest_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFloatingInterestTypeIsSet() {
    return floating_interest_type_is_set;
  }


  /** 
   * <em>floating_interest_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFloatingInterestTypeIsModified() {
    return floating_interest_type_is_modified;
  }


  /** 
   * <em>floating_interest_spread</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getFloatingInterestSpread()
  {
    return ( floating_interest_spread==null? ((double)0): floating_interest_spread.doubleValue() );
  }


  /** 
   * <em>floating_interest_spread</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFloatingInterestSpreadIsNull()
  {
    return floating_interest_spread == null;
  }


  /** 
   * <em>floating_interest_spread</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFloatingInterestSpreadIsSet() {
    return floating_interest_spread_is_set;
  }


  /** 
   * <em>floating_interest_spread</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFloatingInterestSpreadIsModified() {
    return floating_interest_spread_is_modified;
  }


  /** 
   * <em>floating_min_coupon</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getFloatingMinCoupon()
  {
    return ( floating_min_coupon==null? ((double)0): floating_min_coupon.doubleValue() );
  }


  /** 
   * <em>floating_min_coupon</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFloatingMinCouponIsNull()
  {
    return floating_min_coupon == null;
  }


  /** 
   * <em>floating_min_coupon</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFloatingMinCouponIsSet() {
    return floating_min_coupon_is_set;
  }


  /** 
   * <em>floating_min_coupon</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFloatingMinCouponIsModified() {
    return floating_min_coupon_is_modified;
  }


  /** 
   * <em>tax_free_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTaxFreeDiv()
  {
    return tax_free_div;
  }


  /** 
   * <em>tax_free_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxFreeDivIsSet() {
    return tax_free_div_is_set;
  }


  /** 
   * <em>tax_free_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxFreeDivIsModified() {
    return tax_free_div_is_modified;
  }


  /** 
   * <em>s_and_p</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSAndP()
  {
    return s_and_p;
  }


  /** 
   * <em>s_and_p</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSAndPIsSet() {
    return s_and_p_is_set;
  }


  /** 
   * <em>s_and_p</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSAndPIsModified() {
    return s_and_p_is_modified;
  }


  /** 
   * <em>moodys</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMoodys()
  {
    return moodys;
  }


  /** 
   * <em>moodys</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMoodysIsSet() {
    return moodys_is_set;
  }


  /** 
   * <em>moodys</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMoodysIsModified() {
    return moodys_is_modified;
  }


  /** 
   * <em>cusip</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCusip()
  {
    return cusip;
  }


  /** 
   * <em>cusip</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCusipIsSet() {
    return cusip_is_set;
  }


  /** 
   * <em>cusip</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCusipIsModified() {
    return cusip_is_modified;
  }


  /** 
   * <em>buying_fx_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBuyingFxRate()
  {
    return ( buying_fx_rate==null? ((double)0): buying_fx_rate.doubleValue() );
  }


  /** 
   * <em>buying_fx_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyingFxRateIsNull()
  {
    return buying_fx_rate == null;
  }


  /** 
   * <em>buying_fx_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyingFxRateIsSet() {
    return buying_fx_rate_is_set;
  }


  /** 
   * <em>buying_fx_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyingFxRateIsModified() {
    return buying_fx_rate_is_modified;
  }


  /** 
   * <em>trading_time_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradingTimeCheckDiv()
  {
    return trading_time_check_div;
  }


  /** 
   * <em>trading_time_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingTimeCheckDivIsSet() {
    return trading_time_check_div_is_set;
  }


  /** 
   * <em>trading_time_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingTimeCheckDivIsModified() {
    return trading_time_check_div_is_modified;
  }


  /** 
   * <em>mediator_commission_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMediatorCommissionRate()
  {
    return ( mediator_commission_rate==null? ((double)0): mediator_commission_rate.doubleValue() );
  }


  /** 
   * <em>mediator_commission_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMediatorCommissionRateIsNull()
  {
    return mediator_commission_rate == null;
  }


  /** 
   * <em>mediator_commission_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMediatorCommissionRateIsSet() {
    return mediator_commission_rate_is_set;
  }


  /** 
   * <em>mediator_commission_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMediatorCommissionRateIsModified() {
    return mediator_commission_rate_is_modified;
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
   * <em>admin_last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAdminLastUpdatedTimestamp()
  {
    return admin_last_updated_timestamp;
  }


  /** 
   * <em>admin_last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdminLastUpdatedTimestampIsSet() {
    return admin_last_updated_timestamp_is_set;
  }


  /** 
   * <em>admin_last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdminLastUpdatedTimestampIsModified() {
    return admin_last_updated_timestamp_is_modified;
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
   * <em>recruit_invitation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRecruitInvitationDiv()
  {
    return recruit_invitation_div;
  }


  /** 
   * <em>recruit_invitation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitInvitationDivIsSet() {
    return recruit_invitation_div_is_set;
  }


  /** 
   * <em>recruit_invitation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitInvitationDivIsModified() {
    return recruit_invitation_div_is_modified;
  }


  /** 
   * <em>recruit_accept_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRecruitAcceptDiv()
  {
    return recruit_accept_div;
  }


  /** 
   * <em>recruit_accept_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitAcceptDivIsSet() {
    return recruit_accept_div_is_set;
  }


  /** 
   * <em>recruit_accept_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitAcceptDivIsModified() {
    return recruit_accept_div_is_modified;
  }


  /** 
   * <em>redemption_term</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getRedemptionTerm()
  {
    return ( redemption_term==null? ((int)0): redemption_term.intValue() );
  }


  /** 
   * <em>redemption_term</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRedemptionTermIsNull()
  {
    return redemption_term == null;
  }


  /** 
   * <em>redemption_term</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRedemptionTermIsSet() {
    return redemption_term_is_set;
  }


  /** 
   * <em>redemption_term</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRedemptionTermIsModified() {
    return redemption_term_is_modified;
  }


  /** 
   * <em>min_issue_coupon_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMinIssueCouponType()
  {
    return min_issue_coupon_type;
  }


  /** 
   * <em>min_issue_coupon_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinIssueCouponTypeIsSet() {
    return min_issue_coupon_type_is_set;
  }


  /** 
   * <em>min_issue_coupon_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinIssueCouponTypeIsModified() {
    return min_issue_coupon_type_is_modified;
  }


  /** 
   * <em>delivery_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDeliveryDate()
  {
    return delivery_date;
  }


  /** 
   * <em>delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsSet() {
    return delivery_date_is_set;
  }


  /** 
   * <em>delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsModified() {
    return delivery_date_is_modified;
  }


  /** 
   * <em>prospectus_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProspectusCheckDiv()
  {
    return prospectus_check_div;
  }


  /** 
   * <em>prospectus_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProspectusCheckDivIsSet() {
    return prospectus_check_div_is_set;
  }


  /** 
   * <em>prospectus_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProspectusCheckDivIsModified() {
    return prospectus_check_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new BondProductPK(product_id);
  }


  /** 
   * <em>product_id</em>カラムの値を設定します。 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setProductId( long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = p_productId;
    product_id_is_set = true;
    product_id_is_modified = true;
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>product_issue_code</em>カラムの値を設定します。 
   *
   * @@param p_productIssueCode <em>product_issue_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setProductIssueCode( String p_productIssueCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_issue_code = p_productIssueCode;
    product_issue_code_is_set = true;
    product_issue_code_is_modified = true;
  }


  /** 
   * <em>bond_type</em>カラムの値を設定します。 
   *
   * @@param p_bondType <em>bond_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum値 
   */
  public final void setBondType( com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum p_bondType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_type = p_bondType;
    bond_type_is_set = true;
    bond_type_is_modified = true;
  }


  /** 
   * <em>host_product_code</em>カラムの値を設定します。 
   *
   * @@param p_hostProductCode <em>host_product_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setHostProductCode( String p_hostProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    host_product_code = p_hostProductCode;
    host_product_code_is_set = true;
    host_product_code_is_modified = true;
  }


  /** 
   * <em>host_product_issue_code</em>カラムの値を設定します。 
   *
   * @@param p_hostProductIssueCode <em>host_product_issue_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setHostProductIssueCode( String p_hostProductIssueCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    host_product_issue_code = p_hostProductIssueCode;
    host_product_issue_code_is_set = true;
    host_product_issue_code_is_modified = true;
  }


  /** 
   * <em>issue_date</em>カラムの値を設定します。 
   *
   * @@param p_issueDate <em>issue_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setIssueDate( java.sql.Timestamp p_issueDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    issue_date = p_issueDate;
    issue_date_is_set = true;
    issue_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>issue_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setIssueDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    issue_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    issue_date_is_set = true;
    issue_date_is_modified = true;
  }


  /** 
   * <em>issue_price</em>カラムの値を設定します。 
   *
   * @@param p_issuePrice <em>issue_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setIssuePrice( double p_issuePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    issue_price = p_issuePrice;
    issue_price_is_set = true;
    issue_price_is_modified = true;
  }


  /** 
   * <em>issue_amount</em>カラムの値を設定します。 
   *
   * @@param p_issueAmount <em>issue_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setIssueAmount( double p_issueAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    issue_amount = new Double(p_issueAmount);
    issue_amount_is_set = true;
    issue_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>issue_amount</em>カラムに値を設定します。 
   */
  public final void setIssueAmount( Double p_issueAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    issue_amount = p_issueAmount;
    issue_amount_is_set = true;
    issue_amount_is_modified = true;
  }


  /** 
   * <em>par_value</em>カラムの値を設定します。 
   *
   * @@param p_parValue <em>par_value</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setParValue( double p_parValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    par_value = p_parValue;
    par_value_is_set = true;
    par_value_is_modified = true;
  }


  /** 
   * <em>maturity_date</em>カラムの値を設定します。 
   *
   * @@param p_maturityDate <em>maturity_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setMaturityDate( java.sql.Timestamp p_maturityDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    maturity_date = p_maturityDate;
    maturity_date_is_set = true;
    maturity_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>maturity_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setMaturityDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    maturity_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    maturity_date_is_set = true;
    maturity_date_is_modified = true;
  }


  /** 
   * <em>redemption_price</em>カラムの値を設定します。 
   *
   * @@param p_redemptionPrice <em>redemption_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRedemptionPrice( double p_redemptionPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    redemption_price = p_redemptionPrice;
    redemption_price_is_set = true;
    redemption_price_is_modified = true;
  }


  /** 
   * <em>coupon_type</em>カラムの値を設定します。 
   *
   * @@param p_couponType <em>coupon_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum値 
   */
  public final void setCouponType( com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum p_couponType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    coupon_type = p_couponType;
    coupon_type_is_set = true;
    coupon_type_is_modified = true;
  }


  /** 
   * <em>coupon</em>カラムの値を設定します。 
   *
   * @@param p_coupon <em>coupon</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCoupon( double p_coupon )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    coupon = p_coupon;
    coupon_is_set = true;
    coupon_is_modified = true;
  }


  /** 
   * <em>yearly_interest_payments</em>カラムの値を設定します。 
   *
   * @@param p_yearlyInterestPayments <em>yearly_interest_payments</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setYearlyInterestPayments( int p_yearlyInterestPayments )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    yearly_interest_payments = p_yearlyInterestPayments;
    yearly_interest_payments_is_set = true;
    yearly_interest_payments_is_modified = true;
  }


  /** 
   * <em>interest_payment_day_1st</em>カラムの値を設定します。 
   *
   * @@param p_interestPaymentDay1st <em>interest_payment_day_1st</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setInterestPaymentDay1st( String p_interestPaymentDay1st )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_payment_day_1st = p_interestPaymentDay1st;
    interest_payment_day_1st_is_set = true;
    interest_payment_day_1st_is_modified = true;
  }


  /** 
   * <em>interest_payment_day_2nd</em>カラムの値を設定します。 
   *
   * @@param p_interestPaymentDay2nd <em>interest_payment_day_2nd</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setInterestPaymentDay2nd( String p_interestPaymentDay2nd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_payment_day_2nd = p_interestPaymentDay2nd;
    interest_payment_day_2nd_is_set = true;
    interest_payment_day_2nd_is_modified = true;
  }


  /** 
   * <em>first_interest_payment_day</em>カラムの値を設定します。 
   *
   * @@param p_firstInterestPaymentDay <em>first_interest_payment_day</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setFirstInterestPaymentDay( java.sql.Timestamp p_firstInterestPaymentDay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    first_interest_payment_day = p_firstInterestPaymentDay;
    first_interest_payment_day_is_set = true;
    first_interest_payment_day_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>first_interest_payment_day</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setFirstInterestPaymentDay( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    first_interest_payment_day = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    first_interest_payment_day_is_set = true;
    first_interest_payment_day_is_modified = true;
  }


  /** 
   * <em>interest_payment_day</em>カラムの値を設定します。 
   *
   * @@param p_interestPaymentDay <em>interest_payment_day</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setInterestPaymentDay( java.sql.Timestamp p_interestPaymentDay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_payment_day = p_interestPaymentDay;
    interest_payment_day_is_set = true;
    interest_payment_day_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>interest_payment_day</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setInterestPaymentDay( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_payment_day = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    interest_payment_day_is_set = true;
    interest_payment_day_is_modified = true;
  }


  /** 
   * <em>interest_payment_day2</em>カラムの値を設定します。 
   *
   * @@param p_interestPaymentDay2 <em>interest_payment_day2</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setInterestPaymentDay2( java.sql.Timestamp p_interestPaymentDay2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_payment_day2 = p_interestPaymentDay2;
    interest_payment_day2_is_set = true;
    interest_payment_day2_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>interest_payment_day2</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setInterestPaymentDay2( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_payment_day2 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    interest_payment_day2_is_set = true;
    interest_payment_day2_is_modified = true;
  }


  /** 
   * <em>interest_payment_day3</em>カラムの値を設定します。 
   *
   * @@param p_interestPaymentDay3 <em>interest_payment_day3</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setInterestPaymentDay3( java.sql.Timestamp p_interestPaymentDay3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_payment_day3 = p_interestPaymentDay3;
    interest_payment_day3_is_set = true;
    interest_payment_day3_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>interest_payment_day3</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setInterestPaymentDay3( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_payment_day3 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    interest_payment_day3_is_set = true;
    interest_payment_day3_is_modified = true;
  }


  /** 
   * <em>interest_payment_day4</em>カラムの値を設定します。 
   *
   * @@param p_interestPaymentDay4 <em>interest_payment_day4</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setInterestPaymentDay4( java.sql.Timestamp p_interestPaymentDay4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_payment_day4 = p_interestPaymentDay4;
    interest_payment_day4_is_set = true;
    interest_payment_day4_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>interest_payment_day4</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setInterestPaymentDay4( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_payment_day4 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    interest_payment_day4_is_set = true;
    interest_payment_day4_is_modified = true;
  }


  /** 
   * <em>host_recruit_start_date</em>カラムの値を設定します。 
   *
   * @@param p_hostRecruitStartDate <em>host_recruit_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setHostRecruitStartDate( java.sql.Timestamp p_hostRecruitStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    host_recruit_start_date = p_hostRecruitStartDate;
    host_recruit_start_date_is_set = true;
    host_recruit_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>host_recruit_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setHostRecruitStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    host_recruit_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    host_recruit_start_date_is_set = true;
    host_recruit_start_date_is_modified = true;
  }


  /** 
   * <em>host_recruit_end_date</em>カラムの値を設定します。 
   *
   * @@param p_hostRecruitEndDate <em>host_recruit_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setHostRecruitEndDate( java.sql.Timestamp p_hostRecruitEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    host_recruit_end_date = p_hostRecruitEndDate;
    host_recruit_end_date_is_set = true;
    host_recruit_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>host_recruit_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setHostRecruitEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    host_recruit_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    host_recruit_end_date_is_set = true;
    host_recruit_end_date_is_modified = true;
  }


  /** 
   * <em>trade_handle_div</em>カラムの値を設定します。 
   *
   * @@param p_tradeHandleDiv <em>trade_handle_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTradeHandleDiv( String p_tradeHandleDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_handle_div = p_tradeHandleDiv;
    trade_handle_div_is_set = true;
    trade_handle_div_is_modified = true;
  }


  /** 
   * <em>trade_start_date</em>カラムの値を設定します。 
   *
   * @@param p_tradeStartDate <em>trade_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTradeStartDate( java.sql.Timestamp p_tradeStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_start_date = p_tradeStartDate;
    trade_start_date_is_set = true;
    trade_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>trade_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTradeStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trade_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    trade_start_date_is_set = true;
    trade_start_date_is_modified = true;
  }


  /** 
   * <em>trade_end_date</em>カラムの値を設定します。 
   *
   * @@param p_tradeEndDate <em>trade_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTradeEndDate( java.sql.Timestamp p_tradeEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_end_date = p_tradeEndDate;
    trade_end_date_is_set = true;
    trade_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>trade_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTradeEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trade_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    trade_end_date_is_set = true;
    trade_end_date_is_modified = true;
  }


  /** 
   * <em>recruit_start_date</em>カラムの値を設定します。 
   *
   * @@param p_recruitStartDate <em>recruit_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setRecruitStartDate( java.sql.Timestamp p_recruitStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_start_date = p_recruitStartDate;
    recruit_start_date_is_set = true;
    recruit_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>recruit_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setRecruitStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    recruit_start_date_is_set = true;
    recruit_start_date_is_modified = true;
  }


  /** 
   * <em>recruit_end_date</em>カラムの値を設定します。 
   *
   * @@param p_recruitEndDate <em>recruit_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setRecruitEndDate( java.sql.Timestamp p_recruitEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_end_date = p_recruitEndDate;
    recruit_end_date_is_set = true;
    recruit_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>recruit_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setRecruitEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    recruit_end_date_is_set = true;
    recruit_end_date_is_modified = true;
  }


  /** 
   * <em>trade_type</em>カラムの値を設定します。 
   *
   * @@param p_tradeType <em>trade_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTradeType( String p_tradeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_type = p_tradeType;
    trade_type_is_set = true;
    trade_type_is_modified = true;
  }


  /** 
   * <em>product_name</em>カラムの値を設定します。 
   *
   * @@param p_productName <em>product_name</em>カラムの値をあらわす64桁以下のString値 
   */
  public final void setProductName( String p_productName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_name = p_productName;
    product_name_is_set = true;
    product_name_is_modified = true;
  }


  /** 
   * <em>buy_price</em>カラムの値を設定します。 
   *
   * @@param p_buyPrice <em>buy_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBuyPrice( double p_buyPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_price = new Double(p_buyPrice);
    buy_price_is_set = true;
    buy_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_price</em>カラムに値を設定します。 
   */
  public final void setBuyPrice( Double p_buyPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_price = p_buyPrice;
    buy_price_is_set = true;
    buy_price_is_modified = true;
  }


  /** 
   * <em>sell_price</em>カラムの値を設定します。 
   *
   * @@param p_sellPrice <em>sell_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSellPrice( double p_sellPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_price = new Double(p_sellPrice);
    sell_price_is_set = true;
    sell_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_price</em>カラムに値を設定します。 
   */
  public final void setSellPrice( Double p_sellPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_price = p_sellPrice;
    sell_price_is_set = true;
    sell_price_is_modified = true;
  }


  /** 
   * <em>trade_unit</em>カラムの値を設定します。 
   *
   * @@param p_tradeUnit <em>trade_unit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTradeUnit( double p_tradeUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_unit = p_tradeUnit;
    trade_unit_is_set = true;
    trade_unit_is_modified = true;
  }


  /** 
   * <em>min_face_amount</em>カラムの値を設定します。 
   *
   * @@param p_minFaceAmount <em>min_face_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMinFaceAmount( double p_minFaceAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    min_face_amount = p_minFaceAmount;
    min_face_amount_is_set = true;
    min_face_amount_is_modified = true;
  }


  /** 
   * <em>max_face_amount</em>カラムの値を設定します。 
   *
   * @@param p_maxFaceAmount <em>max_face_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMaxFaceAmount( double p_maxFaceAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_face_amount = new Double(p_maxFaceAmount);
    max_face_amount_is_set = true;
    max_face_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_face_amount</em>カラムに値を設定します。 
   */
  public final void setMaxFaceAmount( Double p_maxFaceAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_face_amount = p_maxFaceAmount;
    max_face_amount_is_set = true;
    max_face_amount_is_modified = true;
  }


  /** 
   * <em>cal_linked_market_code</em>カラムの値を設定します。 
   *
   * @@param p_calLinkedMarketCode <em>cal_linked_market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCalLinkedMarketCode( String p_calLinkedMarketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cal_linked_market_code = p_calLinkedMarketCode;
    cal_linked_market_code_is_set = true;
    cal_linked_market_code_is_modified = true;
  }


  /** 
   * <em>buy_delivery_date_shiftdays</em>カラムの値を設定します。 
   *
   * @@param p_buyDeliveryDateShiftdays <em>buy_delivery_date_shiftdays</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setBuyDeliveryDateShiftdays( int p_buyDeliveryDateShiftdays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_delivery_date_shiftdays = new Integer(p_buyDeliveryDateShiftdays);
    buy_delivery_date_shiftdays_is_set = true;
    buy_delivery_date_shiftdays_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_delivery_date_shiftdays</em>カラムに値を設定します。 
   */
  public final void setBuyDeliveryDateShiftdays( Integer p_buyDeliveryDateShiftdays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_delivery_date_shiftdays = p_buyDeliveryDateShiftdays;
    buy_delivery_date_shiftdays_is_set = true;
    buy_delivery_date_shiftdays_is_modified = true;
  }


  /** 
   * <em>sell_delivery_date_shiftdays</em>カラムの値を設定します。 
   *
   * @@param p_sellDeliveryDateShiftdays <em>sell_delivery_date_shiftdays</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setSellDeliveryDateShiftdays( int p_sellDeliveryDateShiftdays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_delivery_date_shiftdays = new Integer(p_sellDeliveryDateShiftdays);
    sell_delivery_date_shiftdays_is_set = true;
    sell_delivery_date_shiftdays_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_delivery_date_shiftdays</em>カラムに値を設定します。 
   */
  public final void setSellDeliveryDateShiftdays( Integer p_sellDeliveryDateShiftdays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_delivery_date_shiftdays = p_sellDeliveryDateShiftdays;
    sell_delivery_date_shiftdays_is_set = true;
    sell_delivery_date_shiftdays_is_modified = true;
  }


  /** 
   * <em>auto_exec_div</em>カラムの値を設定します。 
   *
   * @@param p_autoExecDiv <em>auto_exec_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAutoExecDiv( String p_autoExecDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    auto_exec_div = p_autoExecDiv;
    auto_exec_div_is_set = true;
    auto_exec_div_is_modified = true;
  }


  /** 
   * <em>auto_exec_amount</em>カラムの値を設定します。 
   *
   * @@param p_autoExecAmount <em>auto_exec_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAutoExecAmount( double p_autoExecAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    auto_exec_amount = new Double(p_autoExecAmount);
    auto_exec_amount_is_set = true;
    auto_exec_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>auto_exec_amount</em>カラムに値を設定します。 
   */
  public final void setAutoExecAmount( Double p_autoExecAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    auto_exec_amount = p_autoExecAmount;
    auto_exec_amount_is_set = true;
    auto_exec_amount_is_modified = true;
  }


  /** 
   * <em>auto_exec_limit</em>カラムの値を設定します。 
   *
   * @@param p_autoExecLimit <em>auto_exec_limit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAutoExecLimit( double p_autoExecLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    auto_exec_limit = new Double(p_autoExecLimit);
    auto_exec_limit_is_set = true;
    auto_exec_limit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>auto_exec_limit</em>カラムに値を設定します。 
   */
  public final void setAutoExecLimit( Double p_autoExecLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    auto_exec_limit = p_autoExecLimit;
    auto_exec_limit_is_set = true;
    auto_exec_limit_is_modified = true;
  }


  /** 
   * <em>custodian_code</em>カラムの値を設定します。 
   *
   * @@param p_custodianCode <em>custodian_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCustodianCode( String p_custodianCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    custodian_code = p_custodianCode;
    custodian_code_is_set = true;
    custodian_code_is_modified = true;
  }


  /** 
   * <em>host_product_name_1</em>カラムの値を設定します。 
   *
   * @@param p_hostProductName1 <em>host_product_name_1</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setHostProductName1( String p_hostProductName1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    host_product_name_1 = p_hostProductName1;
    host_product_name_1_is_set = true;
    host_product_name_1_is_modified = true;
  }


  /** 
   * <em>host_product_name_2</em>カラムの値を設定します。 
   *
   * @@param p_hostProductName2 <em>host_product_name_2</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setHostProductName2( String p_hostProductName2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    host_product_name_2 = p_hostProductName2;
    host_product_name_2_is_set = true;
    host_product_name_2_is_modified = true;
  }


  /** 
   * <em>host_short_product_name</em>カラムの値を設定します。 
   *
   * @@param p_hostShortProductName <em>host_short_product_name</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setHostShortProductName( String p_hostShortProductName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    host_short_product_name = p_hostShortProductName;
    host_short_product_name_is_set = true;
    host_short_product_name_is_modified = true;
  }


  /** 
   * <em>isin_code</em>カラムの値を設定します。 
   *
   * @@param p_isinCode <em>isin_code</em>カラムの値をあらわす12桁以下のString値 
   */
  public final void setIsinCode( String p_isinCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    isin_code = p_isinCode;
    isin_code_is_set = true;
    isin_code_is_modified = true;
  }


  /** 
   * <em>bond_categ_code</em>カラムの値を設定します。 
   *
   * @@param p_bondCategCode <em>bond_categ_code</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setBondCategCode( String p_bondCategCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_categ_code = p_bondCategCode;
    bond_categ_code_is_set = true;
    bond_categ_code_is_modified = true;
  }


  /** 
   * <em>currency_code</em>カラムの値を設定します。 
   *
   * @@param p_currencyCode <em>currency_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>issue_market_code</em>カラムの値を設定します。 
   *
   * @@param p_issueMarketCode <em>issue_market_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setIssueMarketCode( String p_issueMarketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    issue_market_code = p_issueMarketCode;
    issue_market_code_is_set = true;
    issue_market_code_is_modified = true;
  }


  /** 
   * <em>issue_association_code</em>カラムの値を設定します。 
   *
   * @@param p_issueAssociationCode <em>issue_association_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setIssueAssociationCode( String p_issueAssociationCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    issue_association_code = p_issueAssociationCode;
    issue_association_code_is_set = true;
    issue_association_code_is_modified = true;
  }


  /** 
   * <em>accrued_interest_calc_type</em>カラムの値を設定します。 
   *
   * @@param p_accruedInterestCalcType <em>accrued_interest_calc_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setAccruedInterestCalcType( String p_accruedInterestCalcType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    accrued_interest_calc_type = p_accruedInterestCalcType;
    accrued_interest_calc_type_is_set = true;
    accrued_interest_calc_type_is_modified = true;
  }


  /** 
   * <em>accrued_interest_start_day</em>カラムの値を設定します。 
   *
   * @@param p_accruedInterestStartDay <em>accrued_interest_start_day</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAccruedInterestStartDay( java.sql.Timestamp p_accruedInterestStartDay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    accrued_interest_start_day = p_accruedInterestStartDay;
    accrued_interest_start_day_is_set = true;
    accrued_interest_start_day_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>accrued_interest_start_day</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAccruedInterestStartDay( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    accrued_interest_start_day = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    accrued_interest_start_day_is_set = true;
    accrued_interest_start_day_is_modified = true;
  }


  /** 
   * <em>special_payment_div</em>カラムの値を設定します。 
   *
   * @@param p_specialPaymentDiv <em>special_payment_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSpecialPaymentDiv( String p_specialPaymentDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_payment_div = p_specialPaymentDiv;
    special_payment_div_is_set = true;
    special_payment_div_is_modified = true;
  }


  /** 
   * <em>floating_interest_period_div</em>カラムの値を設定します。 
   *
   * @@param p_floatingInterestPeriodDiv <em>floating_interest_period_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFloatingInterestPeriodDiv( String p_floatingInterestPeriodDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    floating_interest_period_div = p_floatingInterestPeriodDiv;
    floating_interest_period_div_is_set = true;
    floating_interest_period_div_is_modified = true;
  }


  /** 
   * <em>floating_interest_period</em>カラムの値を設定します。 
   *
   * @@param p_floatingInterestPeriod <em>floating_interest_period</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setFloatingInterestPeriod( String p_floatingInterestPeriod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    floating_interest_period = p_floatingInterestPeriod;
    floating_interest_period_is_set = true;
    floating_interest_period_is_modified = true;
  }


  /** 
   * <em>floating_interest_type</em>カラムの値を設定します。 
   *
   * @@param p_floatingInterestType <em>floating_interest_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setFloatingInterestType( String p_floatingInterestType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    floating_interest_type = p_floatingInterestType;
    floating_interest_type_is_set = true;
    floating_interest_type_is_modified = true;
  }


  /** 
   * <em>floating_interest_spread</em>カラムの値を設定します。 
   *
   * @@param p_floatingInterestSpread <em>floating_interest_spread</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setFloatingInterestSpread( double p_floatingInterestSpread )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    floating_interest_spread = new Double(p_floatingInterestSpread);
    floating_interest_spread_is_set = true;
    floating_interest_spread_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>floating_interest_spread</em>カラムに値を設定します。 
   */
  public final void setFloatingInterestSpread( Double p_floatingInterestSpread )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    floating_interest_spread = p_floatingInterestSpread;
    floating_interest_spread_is_set = true;
    floating_interest_spread_is_modified = true;
  }


  /** 
   * <em>floating_min_coupon</em>カラムの値を設定します。 
   *
   * @@param p_floatingMinCoupon <em>floating_min_coupon</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setFloatingMinCoupon( double p_floatingMinCoupon )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    floating_min_coupon = new Double(p_floatingMinCoupon);
    floating_min_coupon_is_set = true;
    floating_min_coupon_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>floating_min_coupon</em>カラムに値を設定します。 
   */
  public final void setFloatingMinCoupon( Double p_floatingMinCoupon )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    floating_min_coupon = p_floatingMinCoupon;
    floating_min_coupon_is_set = true;
    floating_min_coupon_is_modified = true;
  }


  /** 
   * <em>tax_free_div</em>カラムの値を設定します。 
   *
   * @@param p_taxFreeDiv <em>tax_free_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTaxFreeDiv( String p_taxFreeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_free_div = p_taxFreeDiv;
    tax_free_div_is_set = true;
    tax_free_div_is_modified = true;
  }


  /** 
   * <em>s_and_p</em>カラムの値を設定します。 
   *
   * @@param p_sAndP <em>s_and_p</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setSAndP( String p_sAndP )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    s_and_p = p_sAndP;
    s_and_p_is_set = true;
    s_and_p_is_modified = true;
  }


  /** 
   * <em>moodys</em>カラムの値を設定します。 
   *
   * @@param p_moodys <em>moodys</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setMoodys( String p_moodys )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    moodys = p_moodys;
    moodys_is_set = true;
    moodys_is_modified = true;
  }


  /** 
   * <em>cusip</em>カラムの値を設定します。 
   *
   * @@param p_cusip <em>cusip</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setCusip( String p_cusip )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cusip = p_cusip;
    cusip_is_set = true;
    cusip_is_modified = true;
  }


  /** 
   * <em>buying_fx_rate</em>カラムの値を設定します。 
   *
   * @@param p_buyingFxRate <em>buying_fx_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBuyingFxRate( double p_buyingFxRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buying_fx_rate = new Double(p_buyingFxRate);
    buying_fx_rate_is_set = true;
    buying_fx_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buying_fx_rate</em>カラムに値を設定します。 
   */
  public final void setBuyingFxRate( Double p_buyingFxRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buying_fx_rate = p_buyingFxRate;
    buying_fx_rate_is_set = true;
    buying_fx_rate_is_modified = true;
  }


  /** 
   * <em>trading_time_check_div</em>カラムの値を設定します。 
   *
   * @@param p_tradingTimeCheckDiv <em>trading_time_check_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTradingTimeCheckDiv( String p_tradingTimeCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_time_check_div = p_tradingTimeCheckDiv;
    trading_time_check_div_is_set = true;
    trading_time_check_div_is_modified = true;
  }


  /** 
   * <em>mediator_commission_rate</em>カラムの値を設定します。 
   *
   * @@param p_mediatorCommissionRate <em>mediator_commission_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMediatorCommissionRate( double p_mediatorCommissionRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mediator_commission_rate = new Double(p_mediatorCommissionRate);
    mediator_commission_rate_is_set = true;
    mediator_commission_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>mediator_commission_rate</em>カラムに値を設定します。 
   */
  public final void setMediatorCommissionRate( Double p_mediatorCommissionRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mediator_commission_rate = p_mediatorCommissionRate;
    mediator_commission_rate_is_set = true;
    mediator_commission_rate_is_modified = true;
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
   * <em>admin_last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_adminLastUpdatedTimestamp <em>admin_last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAdminLastUpdatedTimestamp( java.sql.Timestamp p_adminLastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    admin_last_updated_timestamp = p_adminLastUpdatedTimestamp;
    admin_last_updated_timestamp_is_set = true;
    admin_last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>admin_last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAdminLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    admin_last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    admin_last_updated_timestamp_is_set = true;
    admin_last_updated_timestamp_is_modified = true;
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
   * <em>recruit_invitation_div</em>カラムの値を設定します。 
   *
   * @@param p_recruitInvitationDiv <em>recruit_invitation_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setRecruitInvitationDiv( String p_recruitInvitationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_invitation_div = p_recruitInvitationDiv;
    recruit_invitation_div_is_set = true;
    recruit_invitation_div_is_modified = true;
  }


  /** 
   * <em>recruit_accept_div</em>カラムの値を設定します。 
   *
   * @@param p_recruitAcceptDiv <em>recruit_accept_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setRecruitAcceptDiv( String p_recruitAcceptDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_accept_div = p_recruitAcceptDiv;
    recruit_accept_div_is_set = true;
    recruit_accept_div_is_modified = true;
  }


  /** 
   * <em>redemption_term</em>カラムの値を設定します。 
   *
   * @@param p_redemptionTerm <em>redemption_term</em>カラムの値をあらわす3桁以下のint値 
   */
  public final void setRedemptionTerm( int p_redemptionTerm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    redemption_term = new Integer(p_redemptionTerm);
    redemption_term_is_set = true;
    redemption_term_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>redemption_term</em>カラムに値を設定します。 
   */
  public final void setRedemptionTerm( Integer p_redemptionTerm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    redemption_term = p_redemptionTerm;
    redemption_term_is_set = true;
    redemption_term_is_modified = true;
  }


  /** 
   * <em>min_issue_coupon_type</em>カラムの値を設定します。 
   *
   * @@param p_minIssueCouponType <em>min_issue_coupon_type</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setMinIssueCouponType( String p_minIssueCouponType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    min_issue_coupon_type = p_minIssueCouponType;
    min_issue_coupon_type_is_set = true;
    min_issue_coupon_type_is_modified = true;
  }


  /** 
   * <em>delivery_date</em>カラムの値を設定します。 
   *
   * @@param p_deliveryDate <em>delivery_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDeliveryDate( java.sql.Timestamp p_deliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = p_deliveryDate;
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>delivery_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


  /** 
   * <em>prospectus_check_div</em>カラムの値を設定します。 
   *
   * @@param p_prospectusCheckDiv <em>prospectus_check_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setProspectusCheckDiv( String p_prospectusCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    prospectus_check_div = p_prospectusCheckDiv;
    prospectus_check_div_is_set = true;
    prospectus_check_div_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("auto_exec_div") ) {
                    return this.auto_exec_div;
                }
                else if ( name.equals("auto_exec_amount") ) {
                    return this.auto_exec_amount;
                }
                else if ( name.equals("auto_exec_limit") ) {
                    return this.auto_exec_limit;
                }
                else if ( name.equals("accrued_interest_calc_type") ) {
                    return this.accrued_interest_calc_type;
                }
                else if ( name.equals("accrued_interest_start_day") ) {
                    return this.accrued_interest_start_day;
                }
                else if ( name.equals("admin_last_updated_timestamp") ) {
                    return this.admin_last_updated_timestamp;
                }
                break;
            case 'b':
                if ( name.equals("bond_type") ) {
                    return this.bond_type;
                }
                else if ( name.equals("buy_price") ) {
                    return this.buy_price;
                }
                else if ( name.equals("buy_delivery_date_shiftdays") ) {
                    return this.buy_delivery_date_shiftdays;
                }
                else if ( name.equals("bond_categ_code") ) {
                    return this.bond_categ_code;
                }
                else if ( name.equals("buying_fx_rate") ) {
                    return this.buying_fx_rate;
                }
                break;
            case 'c':
                if ( name.equals("coupon_type") ) {
                    return this.coupon_type;
                }
                else if ( name.equals("coupon") ) {
                    return new Double( coupon );
                }
                else if ( name.equals("cal_linked_market_code") ) {
                    return this.cal_linked_market_code;
                }
                else if ( name.equals("custodian_code") ) {
                    return this.custodian_code;
                }
                else if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("cusip") ) {
                    return this.cusip;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                break;
            case 'f':
                if ( name.equals("first_interest_payment_day") ) {
                    return this.first_interest_payment_day;
                }
                else if ( name.equals("floating_interest_period_div") ) {
                    return this.floating_interest_period_div;
                }
                else if ( name.equals("floating_interest_period") ) {
                    return this.floating_interest_period;
                }
                else if ( name.equals("floating_interest_type") ) {
                    return this.floating_interest_type;
                }
                else if ( name.equals("floating_interest_spread") ) {
                    return this.floating_interest_spread;
                }
                else if ( name.equals("floating_min_coupon") ) {
                    return this.floating_min_coupon;
                }
                break;
            case 'h':
                if ( name.equals("host_product_code") ) {
                    return this.host_product_code;
                }
                else if ( name.equals("host_product_issue_code") ) {
                    return this.host_product_issue_code;
                }
                else if ( name.equals("host_recruit_start_date") ) {
                    return this.host_recruit_start_date;
                }
                else if ( name.equals("host_recruit_end_date") ) {
                    return this.host_recruit_end_date;
                }
                else if ( name.equals("host_product_name_1") ) {
                    return this.host_product_name_1;
                }
                else if ( name.equals("host_product_name_2") ) {
                    return this.host_product_name_2;
                }
                else if ( name.equals("host_short_product_name") ) {
                    return this.host_short_product_name;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("issue_date") ) {
                    return this.issue_date;
                }
                else if ( name.equals("issue_price") ) {
                    return new Double( issue_price );
                }
                else if ( name.equals("issue_amount") ) {
                    return this.issue_amount;
                }
                else if ( name.equals("interest_payment_day_1st") ) {
                    return this.interest_payment_day_1st;
                }
                else if ( name.equals("interest_payment_day_2nd") ) {
                    return this.interest_payment_day_2nd;
                }
                else if ( name.equals("interest_payment_day") ) {
                    return this.interest_payment_day;
                }
                else if ( name.equals("interest_payment_day2") ) {
                    return this.interest_payment_day2;
                }
                else if ( name.equals("interest_payment_day3") ) {
                    return this.interest_payment_day3;
                }
                else if ( name.equals("interest_payment_day4") ) {
                    return this.interest_payment_day4;
                }
                else if ( name.equals("isin_code") ) {
                    return this.isin_code;
                }
                else if ( name.equals("issue_market_code") ) {
                    return this.issue_market_code;
                }
                else if ( name.equals("issue_association_code") ) {
                    return this.issue_association_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("maturity_date") ) {
                    return this.maturity_date;
                }
                else if ( name.equals("min_face_amount") ) {
                    return new Double( min_face_amount );
                }
                else if ( name.equals("max_face_amount") ) {
                    return this.max_face_amount;
                }
                else if ( name.equals("moodys") ) {
                    return this.moodys;
                }
                else if ( name.equals("mediator_commission_rate") ) {
                    return this.mediator_commission_rate;
                }
                else if ( name.equals("min_issue_coupon_type") ) {
                    return this.min_issue_coupon_type;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("product_issue_code") ) {
                    return this.product_issue_code;
                }
                else if ( name.equals("par_value") ) {
                    return new Double( par_value );
                }
                else if ( name.equals("product_name") ) {
                    return this.product_name;
                }
                else if ( name.equals("prospectus_check_div") ) {
                    return this.prospectus_check_div;
                }
                break;
            case 'r':
                if ( name.equals("redemption_price") ) {
                    return new Double( redemption_price );
                }
                else if ( name.equals("recruit_start_date") ) {
                    return this.recruit_start_date;
                }
                else if ( name.equals("recruit_end_date") ) {
                    return this.recruit_end_date;
                }
                else if ( name.equals("recruit_invitation_div") ) {
                    return this.recruit_invitation_div;
                }
                else if ( name.equals("recruit_accept_div") ) {
                    return this.recruit_accept_div;
                }
                else if ( name.equals("redemption_term") ) {
                    return this.redemption_term;
                }
                break;
            case 's':
                if ( name.equals("sell_price") ) {
                    return this.sell_price;
                }
                else if ( name.equals("sell_delivery_date_shiftdays") ) {
                    return this.sell_delivery_date_shiftdays;
                }
                else if ( name.equals("special_payment_div") ) {
                    return this.special_payment_div;
                }
                else if ( name.equals("s_and_p") ) {
                    return this.s_and_p;
                }
                break;
            case 't':
                if ( name.equals("trade_handle_div") ) {
                    return this.trade_handle_div;
                }
                else if ( name.equals("trade_start_date") ) {
                    return this.trade_start_date;
                }
                else if ( name.equals("trade_end_date") ) {
                    return this.trade_end_date;
                }
                else if ( name.equals("trade_type") ) {
                    return this.trade_type;
                }
                else if ( name.equals("trade_unit") ) {
                    return new Double( trade_unit );
                }
                else if ( name.equals("tax_free_div") ) {
                    return this.tax_free_div;
                }
                else if ( name.equals("trading_time_check_div") ) {
                    return this.trading_time_check_div;
                }
                break;
            case 'y':
                if ( name.equals("yearly_interest_payments") ) {
                    return new Integer( yearly_interest_payments );
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
                if ( name.equals("auto_exec_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'auto_exec_div' must be String: '"+value+"' is not." );
                    this.auto_exec_div = (String) value;
                    if (this.auto_exec_div_is_set)
                        this.auto_exec_div_is_modified = true;
                    this.auto_exec_div_is_set = true;
                    return;
                }
                else if ( name.equals("auto_exec_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'auto_exec_amount' must be Double: '"+value+"' is not." );
                    this.auto_exec_amount = (Double) value;
                    if (this.auto_exec_amount_is_set)
                        this.auto_exec_amount_is_modified = true;
                    this.auto_exec_amount_is_set = true;
                    return;
                }
                else if ( name.equals("auto_exec_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'auto_exec_limit' must be Double: '"+value+"' is not." );
                    this.auto_exec_limit = (Double) value;
                    if (this.auto_exec_limit_is_set)
                        this.auto_exec_limit_is_modified = true;
                    this.auto_exec_limit_is_set = true;
                    return;
                }
                else if ( name.equals("accrued_interest_calc_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'accrued_interest_calc_type' must be String: '"+value+"' is not." );
                    this.accrued_interest_calc_type = (String) value;
                    if (this.accrued_interest_calc_type_is_set)
                        this.accrued_interest_calc_type_is_modified = true;
                    this.accrued_interest_calc_type_is_set = true;
                    return;
                }
                else if ( name.equals("accrued_interest_start_day") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'accrued_interest_start_day' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.accrued_interest_start_day = (java.sql.Timestamp) value;
                    if (this.accrued_interest_start_day_is_set)
                        this.accrued_interest_start_day_is_modified = true;
                    this.accrued_interest_start_day_is_set = true;
                    return;
                }
                else if ( name.equals("admin_last_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'admin_last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.admin_last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.admin_last_updated_timestamp_is_set)
                        this.admin_last_updated_timestamp_is_modified = true;
                    this.admin_last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("bond_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum) )
                        throw new IllegalArgumentException( "value for 'bond_type' must be com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum: '"+value+"' is not." );
                    this.bond_type = (com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum) value;
                    if (this.bond_type_is_set)
                        this.bond_type_is_modified = true;
                    this.bond_type_is_set = true;
                    return;
                }
                else if ( name.equals("buy_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'buy_price' must be Double: '"+value+"' is not." );
                    this.buy_price = (Double) value;
                    if (this.buy_price_is_set)
                        this.buy_price_is_modified = true;
                    this.buy_price_is_set = true;
                    return;
                }
                else if ( name.equals("buy_delivery_date_shiftdays") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'buy_delivery_date_shiftdays' must be Integer: '"+value+"' is not." );
                    this.buy_delivery_date_shiftdays = (Integer) value;
                    if (this.buy_delivery_date_shiftdays_is_set)
                        this.buy_delivery_date_shiftdays_is_modified = true;
                    this.buy_delivery_date_shiftdays_is_set = true;
                    return;
                }
                else if ( name.equals("bond_categ_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bond_categ_code' must be String: '"+value+"' is not." );
                    this.bond_categ_code = (String) value;
                    if (this.bond_categ_code_is_set)
                        this.bond_categ_code_is_modified = true;
                    this.bond_categ_code_is_set = true;
                    return;
                }
                else if ( name.equals("buying_fx_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'buying_fx_rate' must be Double: '"+value+"' is not." );
                    this.buying_fx_rate = (Double) value;
                    if (this.buying_fx_rate_is_set)
                        this.buying_fx_rate_is_modified = true;
                    this.buying_fx_rate_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("coupon_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum) )
                        throw new IllegalArgumentException( "value for 'coupon_type' must be com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum: '"+value+"' is not." );
                    this.coupon_type = (com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum) value;
                    if (this.coupon_type_is_set)
                        this.coupon_type_is_modified = true;
                    this.coupon_type_is_set = true;
                    return;
                }
                else if ( name.equals("coupon") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'coupon' must be Double: '"+value+"' is not." );
                    this.coupon = ((Double) value).doubleValue();
                    if (this.coupon_is_set)
                        this.coupon_is_modified = true;
                    this.coupon_is_set = true;
                    return;
                }
                else if ( name.equals("cal_linked_market_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cal_linked_market_code' must be String: '"+value+"' is not." );
                    this.cal_linked_market_code = (String) value;
                    if (this.cal_linked_market_code_is_set)
                        this.cal_linked_market_code_is_modified = true;
                    this.cal_linked_market_code_is_set = true;
                    return;
                }
                else if ( name.equals("custodian_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'custodian_code' must be String: '"+value+"' is not." );
                    this.custodian_code = (String) value;
                    if (this.custodian_code_is_set)
                        this.custodian_code_is_modified = true;
                    this.custodian_code_is_set = true;
                    return;
                }
                else if ( name.equals("currency_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_code' must be String: '"+value+"' is not." );
                    this.currency_code = (String) value;
                    if (this.currency_code_is_set)
                        this.currency_code_is_modified = true;
                    this.currency_code_is_set = true;
                    return;
                }
                else if ( name.equals("cusip") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cusip' must be String: '"+value+"' is not." );
                    this.cusip = (String) value;
                    if (this.cusip_is_set)
                        this.cusip_is_modified = true;
                    this.cusip_is_set = true;
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
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("first_interest_payment_day") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'first_interest_payment_day' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.first_interest_payment_day = (java.sql.Timestamp) value;
                    if (this.first_interest_payment_day_is_set)
                        this.first_interest_payment_day_is_modified = true;
                    this.first_interest_payment_day_is_set = true;
                    return;
                }
                else if ( name.equals("floating_interest_period_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'floating_interest_period_div' must be String: '"+value+"' is not." );
                    this.floating_interest_period_div = (String) value;
                    if (this.floating_interest_period_div_is_set)
                        this.floating_interest_period_div_is_modified = true;
                    this.floating_interest_period_div_is_set = true;
                    return;
                }
                else if ( name.equals("floating_interest_period") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'floating_interest_period' must be String: '"+value+"' is not." );
                    this.floating_interest_period = (String) value;
                    if (this.floating_interest_period_is_set)
                        this.floating_interest_period_is_modified = true;
                    this.floating_interest_period_is_set = true;
                    return;
                }
                else if ( name.equals("floating_interest_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'floating_interest_type' must be String: '"+value+"' is not." );
                    this.floating_interest_type = (String) value;
                    if (this.floating_interest_type_is_set)
                        this.floating_interest_type_is_modified = true;
                    this.floating_interest_type_is_set = true;
                    return;
                }
                else if ( name.equals("floating_interest_spread") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'floating_interest_spread' must be Double: '"+value+"' is not." );
                    this.floating_interest_spread = (Double) value;
                    if (this.floating_interest_spread_is_set)
                        this.floating_interest_spread_is_modified = true;
                    this.floating_interest_spread_is_set = true;
                    return;
                }
                else if ( name.equals("floating_min_coupon") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'floating_min_coupon' must be Double: '"+value+"' is not." );
                    this.floating_min_coupon = (Double) value;
                    if (this.floating_min_coupon_is_set)
                        this.floating_min_coupon_is_modified = true;
                    this.floating_min_coupon_is_set = true;
                    return;
                }
                break;
            case 'h':
                if ( name.equals("host_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'host_product_code' must be String: '"+value+"' is not." );
                    this.host_product_code = (String) value;
                    if (this.host_product_code_is_set)
                        this.host_product_code_is_modified = true;
                    this.host_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("host_product_issue_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'host_product_issue_code' must be String: '"+value+"' is not." );
                    this.host_product_issue_code = (String) value;
                    if (this.host_product_issue_code_is_set)
                        this.host_product_issue_code_is_modified = true;
                    this.host_product_issue_code_is_set = true;
                    return;
                }
                else if ( name.equals("host_recruit_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'host_recruit_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.host_recruit_start_date = (java.sql.Timestamp) value;
                    if (this.host_recruit_start_date_is_set)
                        this.host_recruit_start_date_is_modified = true;
                    this.host_recruit_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("host_recruit_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'host_recruit_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.host_recruit_end_date = (java.sql.Timestamp) value;
                    if (this.host_recruit_end_date_is_set)
                        this.host_recruit_end_date_is_modified = true;
                    this.host_recruit_end_date_is_set = true;
                    return;
                }
                else if ( name.equals("host_product_name_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'host_product_name_1' must be String: '"+value+"' is not." );
                    this.host_product_name_1 = (String) value;
                    if (this.host_product_name_1_is_set)
                        this.host_product_name_1_is_modified = true;
                    this.host_product_name_1_is_set = true;
                    return;
                }
                else if ( name.equals("host_product_name_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'host_product_name_2' must be String: '"+value+"' is not." );
                    this.host_product_name_2 = (String) value;
                    if (this.host_product_name_2_is_set)
                        this.host_product_name_2_is_modified = true;
                    this.host_product_name_2_is_set = true;
                    return;
                }
                else if ( name.equals("host_short_product_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'host_short_product_name' must be String: '"+value+"' is not." );
                    this.host_short_product_name = (String) value;
                    if (this.host_short_product_name_is_set)
                        this.host_short_product_name_is_modified = true;
                    this.host_short_product_name_is_set = true;
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
                else if ( name.equals("issue_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'issue_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.issue_date = (java.sql.Timestamp) value;
                    if (this.issue_date_is_set)
                        this.issue_date_is_modified = true;
                    this.issue_date_is_set = true;
                    return;
                }
                else if ( name.equals("issue_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'issue_price' must be Double: '"+value+"' is not." );
                    this.issue_price = ((Double) value).doubleValue();
                    if (this.issue_price_is_set)
                        this.issue_price_is_modified = true;
                    this.issue_price_is_set = true;
                    return;
                }
                else if ( name.equals("issue_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'issue_amount' must be Double: '"+value+"' is not." );
                    this.issue_amount = (Double) value;
                    if (this.issue_amount_is_set)
                        this.issue_amount_is_modified = true;
                    this.issue_amount_is_set = true;
                    return;
                }
                else if ( name.equals("interest_payment_day_1st") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'interest_payment_day_1st' must be String: '"+value+"' is not." );
                    this.interest_payment_day_1st = (String) value;
                    if (this.interest_payment_day_1st_is_set)
                        this.interest_payment_day_1st_is_modified = true;
                    this.interest_payment_day_1st_is_set = true;
                    return;
                }
                else if ( name.equals("interest_payment_day_2nd") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'interest_payment_day_2nd' must be String: '"+value+"' is not." );
                    this.interest_payment_day_2nd = (String) value;
                    if (this.interest_payment_day_2nd_is_set)
                        this.interest_payment_day_2nd_is_modified = true;
                    this.interest_payment_day_2nd_is_set = true;
                    return;
                }
                else if ( name.equals("interest_payment_day") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'interest_payment_day' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.interest_payment_day = (java.sql.Timestamp) value;
                    if (this.interest_payment_day_is_set)
                        this.interest_payment_day_is_modified = true;
                    this.interest_payment_day_is_set = true;
                    return;
                }
                else if ( name.equals("interest_payment_day2") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'interest_payment_day2' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.interest_payment_day2 = (java.sql.Timestamp) value;
                    if (this.interest_payment_day2_is_set)
                        this.interest_payment_day2_is_modified = true;
                    this.interest_payment_day2_is_set = true;
                    return;
                }
                else if ( name.equals("interest_payment_day3") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'interest_payment_day3' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.interest_payment_day3 = (java.sql.Timestamp) value;
                    if (this.interest_payment_day3_is_set)
                        this.interest_payment_day3_is_modified = true;
                    this.interest_payment_day3_is_set = true;
                    return;
                }
                else if ( name.equals("interest_payment_day4") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'interest_payment_day4' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.interest_payment_day4 = (java.sql.Timestamp) value;
                    if (this.interest_payment_day4_is_set)
                        this.interest_payment_day4_is_modified = true;
                    this.interest_payment_day4_is_set = true;
                    return;
                }
                else if ( name.equals("isin_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'isin_code' must be String: '"+value+"' is not." );
                    this.isin_code = (String) value;
                    if (this.isin_code_is_set)
                        this.isin_code_is_modified = true;
                    this.isin_code_is_set = true;
                    return;
                }
                else if ( name.equals("issue_market_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'issue_market_code' must be String: '"+value+"' is not." );
                    this.issue_market_code = (String) value;
                    if (this.issue_market_code_is_set)
                        this.issue_market_code_is_modified = true;
                    this.issue_market_code_is_set = true;
                    return;
                }
                else if ( name.equals("issue_association_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'issue_association_code' must be String: '"+value+"' is not." );
                    this.issue_association_code = (String) value;
                    if (this.issue_association_code_is_set)
                        this.issue_association_code_is_modified = true;
                    this.issue_association_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
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
                if ( name.equals("maturity_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'maturity_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.maturity_date = (java.sql.Timestamp) value;
                    if (this.maturity_date_is_set)
                        this.maturity_date_is_modified = true;
                    this.maturity_date_is_set = true;
                    return;
                }
                else if ( name.equals("min_face_amount") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'min_face_amount' must be Double: '"+value+"' is not." );
                    this.min_face_amount = ((Double) value).doubleValue();
                    if (this.min_face_amount_is_set)
                        this.min_face_amount_is_modified = true;
                    this.min_face_amount_is_set = true;
                    return;
                }
                else if ( name.equals("max_face_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_face_amount' must be Double: '"+value+"' is not." );
                    this.max_face_amount = (Double) value;
                    if (this.max_face_amount_is_set)
                        this.max_face_amount_is_modified = true;
                    this.max_face_amount_is_set = true;
                    return;
                }
                else if ( name.equals("moodys") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'moodys' must be String: '"+value+"' is not." );
                    this.moodys = (String) value;
                    if (this.moodys_is_set)
                        this.moodys_is_modified = true;
                    this.moodys_is_set = true;
                    return;
                }
                else if ( name.equals("mediator_commission_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'mediator_commission_rate' must be Double: '"+value+"' is not." );
                    this.mediator_commission_rate = (Double) value;
                    if (this.mediator_commission_rate_is_set)
                        this.mediator_commission_rate_is_modified = true;
                    this.mediator_commission_rate_is_set = true;
                    return;
                }
                else if ( name.equals("min_issue_coupon_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'min_issue_coupon_type' must be String: '"+value+"' is not." );
                    this.min_issue_coupon_type = (String) value;
                    if (this.min_issue_coupon_type_is_set)
                        this.min_issue_coupon_type_is_modified = true;
                    this.min_issue_coupon_type_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                else if ( name.equals("product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                else if ( name.equals("product_issue_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_issue_code' must be String: '"+value+"' is not." );
                    this.product_issue_code = (String) value;
                    if (this.product_issue_code_is_set)
                        this.product_issue_code_is_modified = true;
                    this.product_issue_code_is_set = true;
                    return;
                }
                else if ( name.equals("par_value") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'par_value' must be Double: '"+value+"' is not." );
                    this.par_value = ((Double) value).doubleValue();
                    if (this.par_value_is_set)
                        this.par_value_is_modified = true;
                    this.par_value_is_set = true;
                    return;
                }
                else if ( name.equals("product_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_name' must be String: '"+value+"' is not." );
                    this.product_name = (String) value;
                    if (this.product_name_is_set)
                        this.product_name_is_modified = true;
                    this.product_name_is_set = true;
                    return;
                }
                else if ( name.equals("prospectus_check_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'prospectus_check_div' must be String: '"+value+"' is not." );
                    this.prospectus_check_div = (String) value;
                    if (this.prospectus_check_div_is_set)
                        this.prospectus_check_div_is_modified = true;
                    this.prospectus_check_div_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("redemption_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'redemption_price' must be Double: '"+value+"' is not." );
                    this.redemption_price = ((Double) value).doubleValue();
                    if (this.redemption_price_is_set)
                        this.redemption_price_is_modified = true;
                    this.redemption_price_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'recruit_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.recruit_start_date = (java.sql.Timestamp) value;
                    if (this.recruit_start_date_is_set)
                        this.recruit_start_date_is_modified = true;
                    this.recruit_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'recruit_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.recruit_end_date = (java.sql.Timestamp) value;
                    if (this.recruit_end_date_is_set)
                        this.recruit_end_date_is_modified = true;
                    this.recruit_end_date_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_invitation_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'recruit_invitation_div' must be String: '"+value+"' is not." );
                    this.recruit_invitation_div = (String) value;
                    if (this.recruit_invitation_div_is_set)
                        this.recruit_invitation_div_is_modified = true;
                    this.recruit_invitation_div_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_accept_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'recruit_accept_div' must be String: '"+value+"' is not." );
                    this.recruit_accept_div = (String) value;
                    if (this.recruit_accept_div_is_set)
                        this.recruit_accept_div_is_modified = true;
                    this.recruit_accept_div_is_set = true;
                    return;
                }
                else if ( name.equals("redemption_term") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'redemption_term' must be Integer: '"+value+"' is not." );
                    this.redemption_term = (Integer) value;
                    if (this.redemption_term_is_set)
                        this.redemption_term_is_modified = true;
                    this.redemption_term_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sell_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'sell_price' must be Double: '"+value+"' is not." );
                    this.sell_price = (Double) value;
                    if (this.sell_price_is_set)
                        this.sell_price_is_modified = true;
                    this.sell_price_is_set = true;
                    return;
                }
                else if ( name.equals("sell_delivery_date_shiftdays") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sell_delivery_date_shiftdays' must be Integer: '"+value+"' is not." );
                    this.sell_delivery_date_shiftdays = (Integer) value;
                    if (this.sell_delivery_date_shiftdays_is_set)
                        this.sell_delivery_date_shiftdays_is_modified = true;
                    this.sell_delivery_date_shiftdays_is_set = true;
                    return;
                }
                else if ( name.equals("special_payment_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'special_payment_div' must be String: '"+value+"' is not." );
                    this.special_payment_div = (String) value;
                    if (this.special_payment_div_is_set)
                        this.special_payment_div_is_modified = true;
                    this.special_payment_div_is_set = true;
                    return;
                }
                else if ( name.equals("s_and_p") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 's_and_p' must be String: '"+value+"' is not." );
                    this.s_and_p = (String) value;
                    if (this.s_and_p_is_set)
                        this.s_and_p_is_modified = true;
                    this.s_and_p_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trade_handle_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trade_handle_div' must be String: '"+value+"' is not." );
                    this.trade_handle_div = (String) value;
                    if (this.trade_handle_div_is_set)
                        this.trade_handle_div_is_modified = true;
                    this.trade_handle_div_is_set = true;
                    return;
                }
                else if ( name.equals("trade_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'trade_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.trade_start_date = (java.sql.Timestamp) value;
                    if (this.trade_start_date_is_set)
                        this.trade_start_date_is_modified = true;
                    this.trade_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("trade_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'trade_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.trade_end_date = (java.sql.Timestamp) value;
                    if (this.trade_end_date_is_set)
                        this.trade_end_date_is_modified = true;
                    this.trade_end_date_is_set = true;
                    return;
                }
                else if ( name.equals("trade_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trade_type' must be String: '"+value+"' is not." );
                    this.trade_type = (String) value;
                    if (this.trade_type_is_set)
                        this.trade_type_is_modified = true;
                    this.trade_type_is_set = true;
                    return;
                }
                else if ( name.equals("trade_unit") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'trade_unit' must be Double: '"+value+"' is not." );
                    this.trade_unit = ((Double) value).doubleValue();
                    if (this.trade_unit_is_set)
                        this.trade_unit_is_modified = true;
                    this.trade_unit_is_set = true;
                    return;
                }
                else if ( name.equals("tax_free_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tax_free_div' must be String: '"+value+"' is not." );
                    this.tax_free_div = (String) value;
                    if (this.tax_free_div_is_set)
                        this.tax_free_div_is_modified = true;
                    this.tax_free_div_is_set = true;
                    return;
                }
                else if ( name.equals("trading_time_check_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_time_check_div' must be String: '"+value+"' is not." );
                    this.trading_time_check_div = (String) value;
                    if (this.trading_time_check_div_is_set)
                        this.trading_time_check_div_is_modified = true;
                    this.trading_time_check_div_is_set = true;
                    return;
                }
                break;
            case 'y':
                if ( name.equals("yearly_interest_payments") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'yearly_interest_payments' must be Integer: '"+value+"' is not." );
                    this.yearly_interest_payments = ((Integer) value).intValue();
                    if (this.yearly_interest_payments_is_set)
                        this.yearly_interest_payments_is_modified = true;
                    this.yearly_interest_payments_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
