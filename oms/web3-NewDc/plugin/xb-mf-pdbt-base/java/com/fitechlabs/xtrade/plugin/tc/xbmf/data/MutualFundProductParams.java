head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.11.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MutualFundProductParams.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * mutual_fund_productテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link MutualFundProductRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link MutualFundProductParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link MutualFundProductParams}が{@@link MutualFundProductRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundProductPK 
 * @@see MutualFundProductRow 
 */
public class MutualFundProductParams extends Params implements MutualFundProductRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mutual_fund_product";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = MutualFundProductRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return MutualFundProductRow.TYPE;
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
   * <em>fund_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum  fund_type;

  /** 
   * <em>init_purchase_min_qty</em>カラムの値 
   */
  public  long  init_purchase_min_qty;

  /** 
   * <em>addtl_purchase_min_qty</em>カラムの値 
   */
  public  long  addtl_purchase_min_qty;

  /** 
   * <em>mutualassoc_product_code</em>カラムの値 
   */
  public  String  mutualassoc_product_code;

  /** 
   * <em>system_handling_div</em>カラムの値 
   */
  public  String  system_handling_div;

  /** 
   * <em>buy_limit_div</em>カラムの値 
   */
  public  String  buy_limit_div;

  /** 
   * <em>standard_name</em>カラムの値 
   */
  public  String  standard_name;

  /** 
   * <em>eng_product_name</em>カラムの値 
   */
  public  String  eng_product_name;

  /** 
   * <em>setting_date</em>カラムの値 
   */
  public  java.sql.Timestamp  setting_date;

  /** 
   * <em>redemption_date</em>カラムの値 
   */
  public  java.sql.Timestamp  redemption_date;

  /** 
   * <em>sell_ban_date</em>カラムの値 
   */
  public  java.sql.Timestamp  sell_ban_date;

  /** 
   * <em>swt_possible_group_id</em>カラムの値 
   */
  public  Integer  swt_possible_group_id;

  /** 
   * <em>category_code</em>カラムの値 
   */
  public  String  category_code;

  /** 
   * <em>indication_ranking</em>カラムの値 
   */
  public  Integer  indication_ranking;

  /** 
   * <em>buy_constant_value</em>カラムの値 
   */
  public  Double  buy_constant_value;

  /** 
   * <em>sell_constant_value</em>カラムの値 
   */
  public  Double  sell_constant_value;

  /** 
   * <em>reference_constant_value</em>カラムの値 
   */
  public  Double  reference_constant_value;

  /** 
   * <em>constant_value_app_date</em>カラムの値 
   */
  public  java.sql.Timestamp  constant_value_app_date;

  /** 
   * <em>currency_code</em>カラムの値 
   */
  public  String  currency_code;

  /** 
   * <em>constant_value_calc_unit</em>カラムの値 
   */
  public  Integer  constant_value_calc_unit;

  /** 
   * <em>buy_settlement_div</em>カラムの値 
   */
  public  String  buy_settlement_div;

  /** 
   * <em>sell_settlement_div</em>カラムの値 
   */
  public  String  sell_settlement_div;

  /** 
   * <em>delivery_method</em>カラムの値 
   */
  public  String  delivery_method;

  /** 
   * <em>buy_specity_div</em>カラムの値 
   */
  public  String  buy_specity_div;

  /** 
   * <em>sell_specify_div</em>カラムの値 
   */
  public  String  sell_specify_div;

  /** 
   * <em>swt_specify_div</em>カラムの値 
   */
  public  String  swt_specify_div;

  /** 
   * <em>stock_type_bond_type</em>カラムの値 
   */
  public  String  stock_type_bond_type;

  /** 
   * <em>contract_institution_type</em>カラムの値 
   */
  public  String  contract_institution_type;

  /** 
   * <em>perference_money_div</em>カラムの値 
   */
  public  String  perference_money_div;

  /** 
   * <em>input_unit</em>カラムの値 
   */
  public  String  input_unit;

  /** 
   * <em>new_buy_min_qty</em>カラムの値 
   */
  public  Long  new_buy_min_qty;

  /** 
   * <em>add_buy_min_qty</em>カラムの値 
   */
  public  Long  add_buy_min_qty;

  /** 
   * <em>sell_min_qty</em>カラムの値 
   */
  public  Long  sell_min_qty;

  /** 
   * <em>swt_min_qty</em>カラムの値 
   */
  public  Long  swt_min_qty;

  /** 
   * <em>new_buy_unit_qty</em>カラムの値 
   */
  public  Long  new_buy_unit_qty;

  /** 
   * <em>add_buy_unit_qty</em>カラムの値 
   */
  public  Long  add_buy_unit_qty;

  /** 
   * <em>sell_unit_qty</em>カラムの値 
   */
  public  Long  sell_unit_qty;

  /** 
   * <em>swt_unit_qty</em>カラムの値 
   */
  public  Long  swt_unit_qty;

  /** 
   * <em>new_buy_min_amt</em>カラムの値 
   */
  public  Long  new_buy_min_amt;

  /** 
   * <em>add_buy_min_amt</em>カラムの値 
   */
  public  Long  add_buy_min_amt;

  /** 
   * <em>sell_min_amt</em>カラムの値 
   */
  public  Long  sell_min_amt;

  /** 
   * <em>swt_min_amt</em>カラムの値 
   */
  public  Long  swt_min_amt;

  /** 
   * <em>new_buy_unit_amt</em>カラムの値 
   */
  public  Long  new_buy_unit_amt;

  /** 
   * <em>add_buy_unit_amt</em>カラムの値 
   */
  public  Long  add_buy_unit_amt;

  /** 
   * <em>sell_unit_amt</em>カラムの値 
   */
  public  Long  sell_unit_amt;

  /** 
   * <em>swt_unit_amt</em>カラムの値 
   */
  public  Long  swt_unit_amt;

  /** 
   * <em>buy_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  buy_start_date;

  /** 
   * <em>buy_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  buy_end_date;

  /** 
   * <em>sell_swt_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  sell_swt_start_date;

  /** 
   * <em>sell_swt_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  sell_swt_end_date;

  /** 
   * <em>buy_claim_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  buy_claim_start_date;

  /** 
   * <em>buy_claim_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  buy_claim_end_date;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>online_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  online_updated_timestamp;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>recruit_constant_value</em>カラムの値 
   */
  public  Double  recruit_constant_value;

  /** 
   * <em>recruit_settlement_div</em>カラムの値 
   */
  public  String  recruit_settlement_div;

  /** 
   * <em>recruit_specity_div</em>カラムの値 
   */
  public  String  recruit_specity_div;

  /** 
   * <em>recruit_min_qty</em>カラムの値 
   */
  public  Long  recruit_min_qty;

  /** 
   * <em>recruit_unit_qty</em>カラムの値 
   */
  public  Long  recruit_unit_qty;

  /** 
   * <em>recruit_min_amt</em>カラムの値 
   */
  public  Long  recruit_min_amt;

  /** 
   * <em>recruit_unit_amt</em>カラムの値 
   */
  public  Long  recruit_unit_amt;

  /** 
   * <em>recruit_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  recruit_start_date;

  /** 
   * <em>recruit_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  recruit_end_date;

  /** 
   * <em>cal_price_div</em>カラムの値 
   */
  public  String  cal_price_div;

  /** 
   * <em>plowback_product_div</em>カラムの値 
   */
  public  String  plowback_product_div;

  /** 
   * <em>recruit_start_date_sonar</em>カラムの値 
   */
  public  java.sql.Timestamp  recruit_start_date_sonar;

  /** 
   * <em>recruit_end_date_sonar</em>カラムの値 
   */
  public  java.sql.Timestamp  recruit_end_date_sonar;

  /** 
   * <em>fixed_buy_possible_div</em>カラムの値 
   */
  public  String  fixed_buy_possible_div;

  /** 
   * <em>unit_type_product_div</em>カラムの値 
   */
  public  String  unit_type_product_div;

  /** 
   * <em>frgn_new_buy_min_amt</em>カラムの値 
   */
  public  Long  frgn_new_buy_min_amt;

  /** 
   * <em>frgn_add_buy_min_amt</em>カラムの値 
   */
  public  Long  frgn_add_buy_min_amt;

  /** 
   * <em>frgn_sell_min_amt</em>カラムの値 
   */
  public  Long  frgn_sell_min_amt;

  /** 
   * <em>frgn_new_buy_unit_amt</em>カラムの値 
   */
  public  Long  frgn_new_buy_unit_amt;

  /** 
   * <em>frgn_add_buy_unit_amt</em>カラムの値 
   */
  public  Long  frgn_add_buy_unit_amt;

  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値 
   */
  public  Long  frgn_sell_unit_amt;

  /** 
   * <em>recruit_commission_div</em>カラムの値 
   */
  public  String  recruit_commission_div;

  /** 
   * <em>open_close_div</em>カラムの値 
   */
  public  String  open_close_div;

  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean product_issue_code_is_set = false;
  private boolean product_issue_code_is_modified = false;
  private boolean fund_type_is_set = false;
  private boolean fund_type_is_modified = false;
  private boolean init_purchase_min_qty_is_set = false;
  private boolean init_purchase_min_qty_is_modified = false;
  private boolean addtl_purchase_min_qty_is_set = false;
  private boolean addtl_purchase_min_qty_is_modified = false;
  private boolean mutualassoc_product_code_is_set = false;
  private boolean mutualassoc_product_code_is_modified = false;
  private boolean system_handling_div_is_set = false;
  private boolean system_handling_div_is_modified = false;
  private boolean buy_limit_div_is_set = false;
  private boolean buy_limit_div_is_modified = false;
  private boolean standard_name_is_set = false;
  private boolean standard_name_is_modified = false;
  private boolean eng_product_name_is_set = false;
  private boolean eng_product_name_is_modified = false;
  private boolean setting_date_is_set = false;
  private boolean setting_date_is_modified = false;
  private boolean redemption_date_is_set = false;
  private boolean redemption_date_is_modified = false;
  private boolean sell_ban_date_is_set = false;
  private boolean sell_ban_date_is_modified = false;
  private boolean swt_possible_group_id_is_set = false;
  private boolean swt_possible_group_id_is_modified = false;
  private boolean category_code_is_set = false;
  private boolean category_code_is_modified = false;
  private boolean indication_ranking_is_set = false;
  private boolean indication_ranking_is_modified = false;
  private boolean buy_constant_value_is_set = false;
  private boolean buy_constant_value_is_modified = false;
  private boolean sell_constant_value_is_set = false;
  private boolean sell_constant_value_is_modified = false;
  private boolean reference_constant_value_is_set = false;
  private boolean reference_constant_value_is_modified = false;
  private boolean constant_value_app_date_is_set = false;
  private boolean constant_value_app_date_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean constant_value_calc_unit_is_set = false;
  private boolean constant_value_calc_unit_is_modified = false;
  private boolean buy_settlement_div_is_set = false;
  private boolean buy_settlement_div_is_modified = false;
  private boolean sell_settlement_div_is_set = false;
  private boolean sell_settlement_div_is_modified = false;
  private boolean delivery_method_is_set = false;
  private boolean delivery_method_is_modified = false;
  private boolean buy_specity_div_is_set = false;
  private boolean buy_specity_div_is_modified = false;
  private boolean sell_specify_div_is_set = false;
  private boolean sell_specify_div_is_modified = false;
  private boolean swt_specify_div_is_set = false;
  private boolean swt_specify_div_is_modified = false;
  private boolean stock_type_bond_type_is_set = false;
  private boolean stock_type_bond_type_is_modified = false;
  private boolean contract_institution_type_is_set = false;
  private boolean contract_institution_type_is_modified = false;
  private boolean perference_money_div_is_set = false;
  private boolean perference_money_div_is_modified = false;
  private boolean input_unit_is_set = false;
  private boolean input_unit_is_modified = false;
  private boolean new_buy_min_qty_is_set = false;
  private boolean new_buy_min_qty_is_modified = false;
  private boolean add_buy_min_qty_is_set = false;
  private boolean add_buy_min_qty_is_modified = false;
  private boolean sell_min_qty_is_set = false;
  private boolean sell_min_qty_is_modified = false;
  private boolean swt_min_qty_is_set = false;
  private boolean swt_min_qty_is_modified = false;
  private boolean new_buy_unit_qty_is_set = false;
  private boolean new_buy_unit_qty_is_modified = false;
  private boolean add_buy_unit_qty_is_set = false;
  private boolean add_buy_unit_qty_is_modified = false;
  private boolean sell_unit_qty_is_set = false;
  private boolean sell_unit_qty_is_modified = false;
  private boolean swt_unit_qty_is_set = false;
  private boolean swt_unit_qty_is_modified = false;
  private boolean new_buy_min_amt_is_set = false;
  private boolean new_buy_min_amt_is_modified = false;
  private boolean add_buy_min_amt_is_set = false;
  private boolean add_buy_min_amt_is_modified = false;
  private boolean sell_min_amt_is_set = false;
  private boolean sell_min_amt_is_modified = false;
  private boolean swt_min_amt_is_set = false;
  private boolean swt_min_amt_is_modified = false;
  private boolean new_buy_unit_amt_is_set = false;
  private boolean new_buy_unit_amt_is_modified = false;
  private boolean add_buy_unit_amt_is_set = false;
  private boolean add_buy_unit_amt_is_modified = false;
  private boolean sell_unit_amt_is_set = false;
  private boolean sell_unit_amt_is_modified = false;
  private boolean swt_unit_amt_is_set = false;
  private boolean swt_unit_amt_is_modified = false;
  private boolean buy_start_date_is_set = false;
  private boolean buy_start_date_is_modified = false;
  private boolean buy_end_date_is_set = false;
  private boolean buy_end_date_is_modified = false;
  private boolean sell_swt_start_date_is_set = false;
  private boolean sell_swt_start_date_is_modified = false;
  private boolean sell_swt_end_date_is_set = false;
  private boolean sell_swt_end_date_is_modified = false;
  private boolean buy_claim_start_date_is_set = false;
  private boolean buy_claim_start_date_is_modified = false;
  private boolean buy_claim_end_date_is_set = false;
  private boolean buy_claim_end_date_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean online_updated_timestamp_is_set = false;
  private boolean online_updated_timestamp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean recruit_constant_value_is_set = false;
  private boolean recruit_constant_value_is_modified = false;
  private boolean recruit_settlement_div_is_set = false;
  private boolean recruit_settlement_div_is_modified = false;
  private boolean recruit_specity_div_is_set = false;
  private boolean recruit_specity_div_is_modified = false;
  private boolean recruit_min_qty_is_set = false;
  private boolean recruit_min_qty_is_modified = false;
  private boolean recruit_unit_qty_is_set = false;
  private boolean recruit_unit_qty_is_modified = false;
  private boolean recruit_min_amt_is_set = false;
  private boolean recruit_min_amt_is_modified = false;
  private boolean recruit_unit_amt_is_set = false;
  private boolean recruit_unit_amt_is_modified = false;
  private boolean recruit_start_date_is_set = false;
  private boolean recruit_start_date_is_modified = false;
  private boolean recruit_end_date_is_set = false;
  private boolean recruit_end_date_is_modified = false;
  private boolean cal_price_div_is_set = false;
  private boolean cal_price_div_is_modified = false;
  private boolean plowback_product_div_is_set = false;
  private boolean plowback_product_div_is_modified = false;
  private boolean recruit_start_date_sonar_is_set = false;
  private boolean recruit_start_date_sonar_is_modified = false;
  private boolean recruit_end_date_sonar_is_set = false;
  private boolean recruit_end_date_sonar_is_modified = false;
  private boolean fixed_buy_possible_div_is_set = false;
  private boolean fixed_buy_possible_div_is_modified = false;
  private boolean unit_type_product_div_is_set = false;
  private boolean unit_type_product_div_is_modified = false;
  private boolean frgn_new_buy_min_amt_is_set = false;
  private boolean frgn_new_buy_min_amt_is_modified = false;
  private boolean frgn_add_buy_min_amt_is_set = false;
  private boolean frgn_add_buy_min_amt_is_modified = false;
  private boolean frgn_sell_min_amt_is_set = false;
  private boolean frgn_sell_min_amt_is_modified = false;
  private boolean frgn_new_buy_unit_amt_is_set = false;
  private boolean frgn_new_buy_unit_amt_is_modified = false;
  private boolean frgn_add_buy_unit_amt_is_set = false;
  private boolean frgn_add_buy_unit_amt_is_modified = false;
  private boolean frgn_sell_unit_amt_is_set = false;
  private boolean frgn_sell_unit_amt_is_modified = false;
  private boolean recruit_commission_div_is_set = false;
  private boolean recruit_commission_div_is_modified = false;
  private boolean open_close_div_is_set = false;
  private boolean open_close_div_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "product_id=" + product_id
      + "," + "institution_code=" +institution_code
      + "," + "product_code=" +product_code
      + "," + "product_issue_code=" +product_issue_code
      + "," + "fund_type=" +fund_type
      + "," + "init_purchase_min_qty=" +init_purchase_min_qty
      + "," + "addtl_purchase_min_qty=" +addtl_purchase_min_qty
      + "," + "mutualassoc_product_code=" +mutualassoc_product_code
      + "," + "system_handling_div=" +system_handling_div
      + "," + "buy_limit_div=" +buy_limit_div
      + "," + "standard_name=" +standard_name
      + "," + "eng_product_name=" +eng_product_name
      + "," + "setting_date=" +setting_date
      + "," + "redemption_date=" +redemption_date
      + "," + "sell_ban_date=" +sell_ban_date
      + "," + "swt_possible_group_id=" +swt_possible_group_id
      + "," + "category_code=" +category_code
      + "," + "indication_ranking=" +indication_ranking
      + "," + "buy_constant_value=" +buy_constant_value
      + "," + "sell_constant_value=" +sell_constant_value
      + "," + "reference_constant_value=" +reference_constant_value
      + "," + "constant_value_app_date=" +constant_value_app_date
      + "," + "currency_code=" +currency_code
      + "," + "constant_value_calc_unit=" +constant_value_calc_unit
      + "," + "buy_settlement_div=" +buy_settlement_div
      + "," + "sell_settlement_div=" +sell_settlement_div
      + "," + "delivery_method=" +delivery_method
      + "," + "buy_specity_div=" +buy_specity_div
      + "," + "sell_specify_div=" +sell_specify_div
      + "," + "swt_specify_div=" +swt_specify_div
      + "," + "stock_type_bond_type=" +stock_type_bond_type
      + "," + "contract_institution_type=" +contract_institution_type
      + "," + "perference_money_div=" +perference_money_div
      + "," + "input_unit=" +input_unit
      + "," + "new_buy_min_qty=" +new_buy_min_qty
      + "," + "add_buy_min_qty=" +add_buy_min_qty
      + "," + "sell_min_qty=" +sell_min_qty
      + "," + "swt_min_qty=" +swt_min_qty
      + "," + "new_buy_unit_qty=" +new_buy_unit_qty
      + "," + "add_buy_unit_qty=" +add_buy_unit_qty
      + "," + "sell_unit_qty=" +sell_unit_qty
      + "," + "swt_unit_qty=" +swt_unit_qty
      + "," + "new_buy_min_amt=" +new_buy_min_amt
      + "," + "add_buy_min_amt=" +add_buy_min_amt
      + "," + "sell_min_amt=" +sell_min_amt
      + "," + "swt_min_amt=" +swt_min_amt
      + "," + "new_buy_unit_amt=" +new_buy_unit_amt
      + "," + "add_buy_unit_amt=" +add_buy_unit_amt
      + "," + "sell_unit_amt=" +sell_unit_amt
      + "," + "swt_unit_amt=" +swt_unit_amt
      + "," + "buy_start_date=" +buy_start_date
      + "," + "buy_end_date=" +buy_end_date
      + "," + "sell_swt_start_date=" +sell_swt_start_date
      + "," + "sell_swt_end_date=" +sell_swt_end_date
      + "," + "buy_claim_start_date=" +buy_claim_start_date
      + "," + "buy_claim_end_date=" +buy_claim_end_date
      + "," + "last_updater=" +last_updater
      + "," + "online_updated_timestamp=" +online_updated_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "recruit_constant_value=" +recruit_constant_value
      + "," + "recruit_settlement_div=" +recruit_settlement_div
      + "," + "recruit_specity_div=" +recruit_specity_div
      + "," + "recruit_min_qty=" +recruit_min_qty
      + "," + "recruit_unit_qty=" +recruit_unit_qty
      + "," + "recruit_min_amt=" +recruit_min_amt
      + "," + "recruit_unit_amt=" +recruit_unit_amt
      + "," + "recruit_start_date=" +recruit_start_date
      + "," + "recruit_end_date=" +recruit_end_date
      + "," + "cal_price_div=" +cal_price_div
      + "," + "plowback_product_div=" +plowback_product_div
      + "," + "recruit_start_date_sonar=" +recruit_start_date_sonar
      + "," + "recruit_end_date_sonar=" +recruit_end_date_sonar
      + "," + "fixed_buy_possible_div=" +fixed_buy_possible_div
      + "," + "unit_type_product_div=" +unit_type_product_div
      + "," + "frgn_new_buy_min_amt=" +frgn_new_buy_min_amt
      + "," + "frgn_add_buy_min_amt=" +frgn_add_buy_min_amt
      + "," + "frgn_sell_min_amt=" +frgn_sell_min_amt
      + "," + "frgn_new_buy_unit_amt=" +frgn_new_buy_unit_amt
      + "," + "frgn_add_buy_unit_amt=" +frgn_add_buy_unit_amt
      + "," + "frgn_sell_unit_amt=" +frgn_sell_unit_amt
      + "," + "recruit_commission_div=" +recruit_commission_div
      + "," + "open_close_div=" +open_close_div
      + "}";
  }


  /** 
   * 値が未設定のMutualFundProductParamsオブジェクトを作成します。 
   */
  public MutualFundProductParams() {
    product_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のMutualFundProductRowオブジェクトの値を利用してMutualFundProductParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するMutualFundProductRowオブジェクト 
   */
  public MutualFundProductParams( MutualFundProductRow p_row )
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
    fund_type = p_row.getFundType();
    fund_type_is_set = p_row.getFundTypeIsSet();
    fund_type_is_modified = p_row.getFundTypeIsModified();
    init_purchase_min_qty = p_row.getInitPurchaseMinQty();
    init_purchase_min_qty_is_set = p_row.getInitPurchaseMinQtyIsSet();
    init_purchase_min_qty_is_modified = p_row.getInitPurchaseMinQtyIsModified();
    addtl_purchase_min_qty = p_row.getAddtlPurchaseMinQty();
    addtl_purchase_min_qty_is_set = p_row.getAddtlPurchaseMinQtyIsSet();
    addtl_purchase_min_qty_is_modified = p_row.getAddtlPurchaseMinQtyIsModified();
    mutualassoc_product_code = p_row.getMutualassocProductCode();
    mutualassoc_product_code_is_set = p_row.getMutualassocProductCodeIsSet();
    mutualassoc_product_code_is_modified = p_row.getMutualassocProductCodeIsModified();
    system_handling_div = p_row.getSystemHandlingDiv();
    system_handling_div_is_set = p_row.getSystemHandlingDivIsSet();
    system_handling_div_is_modified = p_row.getSystemHandlingDivIsModified();
    buy_limit_div = p_row.getBuyLimitDiv();
    buy_limit_div_is_set = p_row.getBuyLimitDivIsSet();
    buy_limit_div_is_modified = p_row.getBuyLimitDivIsModified();
    standard_name = p_row.getStandardName();
    standard_name_is_set = p_row.getStandardNameIsSet();
    standard_name_is_modified = p_row.getStandardNameIsModified();
    eng_product_name = p_row.getEngProductName();
    eng_product_name_is_set = p_row.getEngProductNameIsSet();
    eng_product_name_is_modified = p_row.getEngProductNameIsModified();
    setting_date = p_row.getSettingDate();
    setting_date_is_set = p_row.getSettingDateIsSet();
    setting_date_is_modified = p_row.getSettingDateIsModified();
    redemption_date = p_row.getRedemptionDate();
    redemption_date_is_set = p_row.getRedemptionDateIsSet();
    redemption_date_is_modified = p_row.getRedemptionDateIsModified();
    sell_ban_date = p_row.getSellBanDate();
    sell_ban_date_is_set = p_row.getSellBanDateIsSet();
    sell_ban_date_is_modified = p_row.getSellBanDateIsModified();
    if ( !p_row.getSwtPossibleGroupIdIsNull() )
      swt_possible_group_id = new Integer( p_row.getSwtPossibleGroupId() );
    swt_possible_group_id_is_set = p_row.getSwtPossibleGroupIdIsSet();
    swt_possible_group_id_is_modified = p_row.getSwtPossibleGroupIdIsModified();
    category_code = p_row.getCategoryCode();
    category_code_is_set = p_row.getCategoryCodeIsSet();
    category_code_is_modified = p_row.getCategoryCodeIsModified();
    if ( !p_row.getIndicationRankingIsNull() )
      indication_ranking = new Integer( p_row.getIndicationRanking() );
    indication_ranking_is_set = p_row.getIndicationRankingIsSet();
    indication_ranking_is_modified = p_row.getIndicationRankingIsModified();
    if ( !p_row.getBuyConstantValueIsNull() )
      buy_constant_value = new Double( p_row.getBuyConstantValue() );
    buy_constant_value_is_set = p_row.getBuyConstantValueIsSet();
    buy_constant_value_is_modified = p_row.getBuyConstantValueIsModified();
    if ( !p_row.getSellConstantValueIsNull() )
      sell_constant_value = new Double( p_row.getSellConstantValue() );
    sell_constant_value_is_set = p_row.getSellConstantValueIsSet();
    sell_constant_value_is_modified = p_row.getSellConstantValueIsModified();
    if ( !p_row.getReferenceConstantValueIsNull() )
      reference_constant_value = new Double( p_row.getReferenceConstantValue() );
    reference_constant_value_is_set = p_row.getReferenceConstantValueIsSet();
    reference_constant_value_is_modified = p_row.getReferenceConstantValueIsModified();
    constant_value_app_date = p_row.getConstantValueAppDate();
    constant_value_app_date_is_set = p_row.getConstantValueAppDateIsSet();
    constant_value_app_date_is_modified = p_row.getConstantValueAppDateIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    if ( !p_row.getConstantValueCalcUnitIsNull() )
      constant_value_calc_unit = new Integer( p_row.getConstantValueCalcUnit() );
    constant_value_calc_unit_is_set = p_row.getConstantValueCalcUnitIsSet();
    constant_value_calc_unit_is_modified = p_row.getConstantValueCalcUnitIsModified();
    buy_settlement_div = p_row.getBuySettlementDiv();
    buy_settlement_div_is_set = p_row.getBuySettlementDivIsSet();
    buy_settlement_div_is_modified = p_row.getBuySettlementDivIsModified();
    sell_settlement_div = p_row.getSellSettlementDiv();
    sell_settlement_div_is_set = p_row.getSellSettlementDivIsSet();
    sell_settlement_div_is_modified = p_row.getSellSettlementDivIsModified();
    delivery_method = p_row.getDeliveryMethod();
    delivery_method_is_set = p_row.getDeliveryMethodIsSet();
    delivery_method_is_modified = p_row.getDeliveryMethodIsModified();
    buy_specity_div = p_row.getBuySpecityDiv();
    buy_specity_div_is_set = p_row.getBuySpecityDivIsSet();
    buy_specity_div_is_modified = p_row.getBuySpecityDivIsModified();
    sell_specify_div = p_row.getSellSpecifyDiv();
    sell_specify_div_is_set = p_row.getSellSpecifyDivIsSet();
    sell_specify_div_is_modified = p_row.getSellSpecifyDivIsModified();
    swt_specify_div = p_row.getSwtSpecifyDiv();
    swt_specify_div_is_set = p_row.getSwtSpecifyDivIsSet();
    swt_specify_div_is_modified = p_row.getSwtSpecifyDivIsModified();
    stock_type_bond_type = p_row.getStockTypeBondType();
    stock_type_bond_type_is_set = p_row.getStockTypeBondTypeIsSet();
    stock_type_bond_type_is_modified = p_row.getStockTypeBondTypeIsModified();
    contract_institution_type = p_row.getContractInstitutionType();
    contract_institution_type_is_set = p_row.getContractInstitutionTypeIsSet();
    contract_institution_type_is_modified = p_row.getContractInstitutionTypeIsModified();
    perference_money_div = p_row.getPerferenceMoneyDiv();
    perference_money_div_is_set = p_row.getPerferenceMoneyDivIsSet();
    perference_money_div_is_modified = p_row.getPerferenceMoneyDivIsModified();
    input_unit = p_row.getInputUnit();
    input_unit_is_set = p_row.getInputUnitIsSet();
    input_unit_is_modified = p_row.getInputUnitIsModified();
    if ( !p_row.getNewBuyMinQtyIsNull() )
      new_buy_min_qty = new Long( p_row.getNewBuyMinQty() );
    new_buy_min_qty_is_set = p_row.getNewBuyMinQtyIsSet();
    new_buy_min_qty_is_modified = p_row.getNewBuyMinQtyIsModified();
    if ( !p_row.getAddBuyMinQtyIsNull() )
      add_buy_min_qty = new Long( p_row.getAddBuyMinQty() );
    add_buy_min_qty_is_set = p_row.getAddBuyMinQtyIsSet();
    add_buy_min_qty_is_modified = p_row.getAddBuyMinQtyIsModified();
    if ( !p_row.getSellMinQtyIsNull() )
      sell_min_qty = new Long( p_row.getSellMinQty() );
    sell_min_qty_is_set = p_row.getSellMinQtyIsSet();
    sell_min_qty_is_modified = p_row.getSellMinQtyIsModified();
    if ( !p_row.getSwtMinQtyIsNull() )
      swt_min_qty = new Long( p_row.getSwtMinQty() );
    swt_min_qty_is_set = p_row.getSwtMinQtyIsSet();
    swt_min_qty_is_modified = p_row.getSwtMinQtyIsModified();
    if ( !p_row.getNewBuyUnitQtyIsNull() )
      new_buy_unit_qty = new Long( p_row.getNewBuyUnitQty() );
    new_buy_unit_qty_is_set = p_row.getNewBuyUnitQtyIsSet();
    new_buy_unit_qty_is_modified = p_row.getNewBuyUnitQtyIsModified();
    if ( !p_row.getAddBuyUnitQtyIsNull() )
      add_buy_unit_qty = new Long( p_row.getAddBuyUnitQty() );
    add_buy_unit_qty_is_set = p_row.getAddBuyUnitQtyIsSet();
    add_buy_unit_qty_is_modified = p_row.getAddBuyUnitQtyIsModified();
    if ( !p_row.getSellUnitQtyIsNull() )
      sell_unit_qty = new Long( p_row.getSellUnitQty() );
    sell_unit_qty_is_set = p_row.getSellUnitQtyIsSet();
    sell_unit_qty_is_modified = p_row.getSellUnitQtyIsModified();
    if ( !p_row.getSwtUnitQtyIsNull() )
      swt_unit_qty = new Long( p_row.getSwtUnitQty() );
    swt_unit_qty_is_set = p_row.getSwtUnitQtyIsSet();
    swt_unit_qty_is_modified = p_row.getSwtUnitQtyIsModified();
    if ( !p_row.getNewBuyMinAmtIsNull() )
      new_buy_min_amt = new Long( p_row.getNewBuyMinAmt() );
    new_buy_min_amt_is_set = p_row.getNewBuyMinAmtIsSet();
    new_buy_min_amt_is_modified = p_row.getNewBuyMinAmtIsModified();
    if ( !p_row.getAddBuyMinAmtIsNull() )
      add_buy_min_amt = new Long( p_row.getAddBuyMinAmt() );
    add_buy_min_amt_is_set = p_row.getAddBuyMinAmtIsSet();
    add_buy_min_amt_is_modified = p_row.getAddBuyMinAmtIsModified();
    if ( !p_row.getSellMinAmtIsNull() )
      sell_min_amt = new Long( p_row.getSellMinAmt() );
    sell_min_amt_is_set = p_row.getSellMinAmtIsSet();
    sell_min_amt_is_modified = p_row.getSellMinAmtIsModified();
    if ( !p_row.getSwtMinAmtIsNull() )
      swt_min_amt = new Long( p_row.getSwtMinAmt() );
    swt_min_amt_is_set = p_row.getSwtMinAmtIsSet();
    swt_min_amt_is_modified = p_row.getSwtMinAmtIsModified();
    if ( !p_row.getNewBuyUnitAmtIsNull() )
      new_buy_unit_amt = new Long( p_row.getNewBuyUnitAmt() );
    new_buy_unit_amt_is_set = p_row.getNewBuyUnitAmtIsSet();
    new_buy_unit_amt_is_modified = p_row.getNewBuyUnitAmtIsModified();
    if ( !p_row.getAddBuyUnitAmtIsNull() )
      add_buy_unit_amt = new Long( p_row.getAddBuyUnitAmt() );
    add_buy_unit_amt_is_set = p_row.getAddBuyUnitAmtIsSet();
    add_buy_unit_amt_is_modified = p_row.getAddBuyUnitAmtIsModified();
    if ( !p_row.getSellUnitAmtIsNull() )
      sell_unit_amt = new Long( p_row.getSellUnitAmt() );
    sell_unit_amt_is_set = p_row.getSellUnitAmtIsSet();
    sell_unit_amt_is_modified = p_row.getSellUnitAmtIsModified();
    if ( !p_row.getSwtUnitAmtIsNull() )
      swt_unit_amt = new Long( p_row.getSwtUnitAmt() );
    swt_unit_amt_is_set = p_row.getSwtUnitAmtIsSet();
    swt_unit_amt_is_modified = p_row.getSwtUnitAmtIsModified();
    buy_start_date = p_row.getBuyStartDate();
    buy_start_date_is_set = p_row.getBuyStartDateIsSet();
    buy_start_date_is_modified = p_row.getBuyStartDateIsModified();
    buy_end_date = p_row.getBuyEndDate();
    buy_end_date_is_set = p_row.getBuyEndDateIsSet();
    buy_end_date_is_modified = p_row.getBuyEndDateIsModified();
    sell_swt_start_date = p_row.getSellSwtStartDate();
    sell_swt_start_date_is_set = p_row.getSellSwtStartDateIsSet();
    sell_swt_start_date_is_modified = p_row.getSellSwtStartDateIsModified();
    sell_swt_end_date = p_row.getSellSwtEndDate();
    sell_swt_end_date_is_set = p_row.getSellSwtEndDateIsSet();
    sell_swt_end_date_is_modified = p_row.getSellSwtEndDateIsModified();
    buy_claim_start_date = p_row.getBuyClaimStartDate();
    buy_claim_start_date_is_set = p_row.getBuyClaimStartDateIsSet();
    buy_claim_start_date_is_modified = p_row.getBuyClaimStartDateIsModified();
    buy_claim_end_date = p_row.getBuyClaimEndDate();
    buy_claim_end_date_is_set = p_row.getBuyClaimEndDateIsSet();
    buy_claim_end_date_is_modified = p_row.getBuyClaimEndDateIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    online_updated_timestamp = p_row.getOnlineUpdatedTimestamp();
    online_updated_timestamp_is_set = p_row.getOnlineUpdatedTimestampIsSet();
    online_updated_timestamp_is_modified = p_row.getOnlineUpdatedTimestampIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    if ( !p_row.getRecruitConstantValueIsNull() )
      recruit_constant_value = new Double( p_row.getRecruitConstantValue() );
    recruit_constant_value_is_set = p_row.getRecruitConstantValueIsSet();
    recruit_constant_value_is_modified = p_row.getRecruitConstantValueIsModified();
    recruit_settlement_div = p_row.getRecruitSettlementDiv();
    recruit_settlement_div_is_set = p_row.getRecruitSettlementDivIsSet();
    recruit_settlement_div_is_modified = p_row.getRecruitSettlementDivIsModified();
    recruit_specity_div = p_row.getRecruitSpecityDiv();
    recruit_specity_div_is_set = p_row.getRecruitSpecityDivIsSet();
    recruit_specity_div_is_modified = p_row.getRecruitSpecityDivIsModified();
    if ( !p_row.getRecruitMinQtyIsNull() )
      recruit_min_qty = new Long( p_row.getRecruitMinQty() );
    recruit_min_qty_is_set = p_row.getRecruitMinQtyIsSet();
    recruit_min_qty_is_modified = p_row.getRecruitMinQtyIsModified();
    if ( !p_row.getRecruitUnitQtyIsNull() )
      recruit_unit_qty = new Long( p_row.getRecruitUnitQty() );
    recruit_unit_qty_is_set = p_row.getRecruitUnitQtyIsSet();
    recruit_unit_qty_is_modified = p_row.getRecruitUnitQtyIsModified();
    if ( !p_row.getRecruitMinAmtIsNull() )
      recruit_min_amt = new Long( p_row.getRecruitMinAmt() );
    recruit_min_amt_is_set = p_row.getRecruitMinAmtIsSet();
    recruit_min_amt_is_modified = p_row.getRecruitMinAmtIsModified();
    if ( !p_row.getRecruitUnitAmtIsNull() )
      recruit_unit_amt = new Long( p_row.getRecruitUnitAmt() );
    recruit_unit_amt_is_set = p_row.getRecruitUnitAmtIsSet();
    recruit_unit_amt_is_modified = p_row.getRecruitUnitAmtIsModified();
    recruit_start_date = p_row.getRecruitStartDate();
    recruit_start_date_is_set = p_row.getRecruitStartDateIsSet();
    recruit_start_date_is_modified = p_row.getRecruitStartDateIsModified();
    recruit_end_date = p_row.getRecruitEndDate();
    recruit_end_date_is_set = p_row.getRecruitEndDateIsSet();
    recruit_end_date_is_modified = p_row.getRecruitEndDateIsModified();
    cal_price_div = p_row.getCalPriceDiv();
    cal_price_div_is_set = p_row.getCalPriceDivIsSet();
    cal_price_div_is_modified = p_row.getCalPriceDivIsModified();
    plowback_product_div = p_row.getPlowbackProductDiv();
    plowback_product_div_is_set = p_row.getPlowbackProductDivIsSet();
    plowback_product_div_is_modified = p_row.getPlowbackProductDivIsModified();
    recruit_start_date_sonar = p_row.getRecruitStartDateSonar();
    recruit_start_date_sonar_is_set = p_row.getRecruitStartDateSonarIsSet();
    recruit_start_date_sonar_is_modified = p_row.getRecruitStartDateSonarIsModified();
    recruit_end_date_sonar = p_row.getRecruitEndDateSonar();
    recruit_end_date_sonar_is_set = p_row.getRecruitEndDateSonarIsSet();
    recruit_end_date_sonar_is_modified = p_row.getRecruitEndDateSonarIsModified();
    fixed_buy_possible_div = p_row.getFixedBuyPossibleDiv();
    fixed_buy_possible_div_is_set = p_row.getFixedBuyPossibleDivIsSet();
    fixed_buy_possible_div_is_modified = p_row.getFixedBuyPossibleDivIsModified();
    unit_type_product_div = p_row.getUnitTypeProductDiv();
    unit_type_product_div_is_set = p_row.getUnitTypeProductDivIsSet();
    unit_type_product_div_is_modified = p_row.getUnitTypeProductDivIsModified();
    if ( !p_row.getFrgnNewBuyMinAmtIsNull() )
      frgn_new_buy_min_amt = new Long( p_row.getFrgnNewBuyMinAmt() );
    frgn_new_buy_min_amt_is_set = p_row.getFrgnNewBuyMinAmtIsSet();
    frgn_new_buy_min_amt_is_modified = p_row.getFrgnNewBuyMinAmtIsModified();
    if ( !p_row.getFrgnAddBuyMinAmtIsNull() )
      frgn_add_buy_min_amt = new Long( p_row.getFrgnAddBuyMinAmt() );
    frgn_add_buy_min_amt_is_set = p_row.getFrgnAddBuyMinAmtIsSet();
    frgn_add_buy_min_amt_is_modified = p_row.getFrgnAddBuyMinAmtIsModified();
    if ( !p_row.getFrgnSellMinAmtIsNull() )
      frgn_sell_min_amt = new Long( p_row.getFrgnSellMinAmt() );
    frgn_sell_min_amt_is_set = p_row.getFrgnSellMinAmtIsSet();
    frgn_sell_min_amt_is_modified = p_row.getFrgnSellMinAmtIsModified();
    if ( !p_row.getFrgnNewBuyUnitAmtIsNull() )
      frgn_new_buy_unit_amt = new Long( p_row.getFrgnNewBuyUnitAmt() );
    frgn_new_buy_unit_amt_is_set = p_row.getFrgnNewBuyUnitAmtIsSet();
    frgn_new_buy_unit_amt_is_modified = p_row.getFrgnNewBuyUnitAmtIsModified();
    if ( !p_row.getFrgnAddBuyUnitAmtIsNull() )
      frgn_add_buy_unit_amt = new Long( p_row.getFrgnAddBuyUnitAmt() );
    frgn_add_buy_unit_amt_is_set = p_row.getFrgnAddBuyUnitAmtIsSet();
    frgn_add_buy_unit_amt_is_modified = p_row.getFrgnAddBuyUnitAmtIsModified();
    if ( !p_row.getFrgnSellUnitAmtIsNull() )
      frgn_sell_unit_amt = new Long( p_row.getFrgnSellUnitAmt() );
    frgn_sell_unit_amt_is_set = p_row.getFrgnSellUnitAmtIsSet();
    frgn_sell_unit_amt_is_modified = p_row.getFrgnSellUnitAmtIsModified();
    recruit_commission_div = p_row.getRecruitCommissionDiv();
    recruit_commission_div_is_set = p_row.getRecruitCommissionDivIsSet();
    recruit_commission_div_is_modified = p_row.getRecruitCommissionDivIsModified();
    open_close_div = p_row.getOpenCloseDiv();
    open_close_div_is_set = p_row.getOpenCloseDivIsSet();
    open_close_div_is_modified = p_row.getOpenCloseDivIsModified();
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
      fund_type_is_set = true;
      fund_type_is_modified = true;
      init_purchase_min_qty_is_set = true;
      init_purchase_min_qty_is_modified = true;
      addtl_purchase_min_qty_is_set = true;
      addtl_purchase_min_qty_is_modified = true;
      mutualassoc_product_code_is_set = true;
      mutualassoc_product_code_is_modified = true;
      system_handling_div_is_set = true;
      system_handling_div_is_modified = true;
      buy_limit_div_is_set = true;
      buy_limit_div_is_modified = true;
      standard_name_is_set = true;
      standard_name_is_modified = true;
      eng_product_name_is_set = true;
      eng_product_name_is_modified = true;
      setting_date_is_set = true;
      setting_date_is_modified = true;
      redemption_date_is_set = true;
      redemption_date_is_modified = true;
      sell_ban_date_is_set = true;
      sell_ban_date_is_modified = true;
      swt_possible_group_id_is_set = true;
      swt_possible_group_id_is_modified = true;
      category_code_is_set = true;
      category_code_is_modified = true;
      indication_ranking_is_set = true;
      indication_ranking_is_modified = true;
      buy_constant_value_is_set = true;
      buy_constant_value_is_modified = true;
      sell_constant_value_is_set = true;
      sell_constant_value_is_modified = true;
      reference_constant_value_is_set = true;
      reference_constant_value_is_modified = true;
      constant_value_app_date_is_set = true;
      constant_value_app_date_is_modified = true;
      currency_code_is_set = true;
      currency_code_is_modified = true;
      constant_value_calc_unit_is_set = true;
      constant_value_calc_unit_is_modified = true;
      buy_settlement_div_is_set = true;
      buy_settlement_div_is_modified = true;
      sell_settlement_div_is_set = true;
      sell_settlement_div_is_modified = true;
      delivery_method_is_set = true;
      delivery_method_is_modified = true;
      buy_specity_div_is_set = true;
      buy_specity_div_is_modified = true;
      sell_specify_div_is_set = true;
      sell_specify_div_is_modified = true;
      swt_specify_div_is_set = true;
      swt_specify_div_is_modified = true;
      stock_type_bond_type_is_set = true;
      stock_type_bond_type_is_modified = true;
      contract_institution_type_is_set = true;
      contract_institution_type_is_modified = true;
      perference_money_div_is_set = true;
      perference_money_div_is_modified = true;
      input_unit_is_set = true;
      input_unit_is_modified = true;
      new_buy_min_qty_is_set = true;
      new_buy_min_qty_is_modified = true;
      add_buy_min_qty_is_set = true;
      add_buy_min_qty_is_modified = true;
      sell_min_qty_is_set = true;
      sell_min_qty_is_modified = true;
      swt_min_qty_is_set = true;
      swt_min_qty_is_modified = true;
      new_buy_unit_qty_is_set = true;
      new_buy_unit_qty_is_modified = true;
      add_buy_unit_qty_is_set = true;
      add_buy_unit_qty_is_modified = true;
      sell_unit_qty_is_set = true;
      sell_unit_qty_is_modified = true;
      swt_unit_qty_is_set = true;
      swt_unit_qty_is_modified = true;
      new_buy_min_amt_is_set = true;
      new_buy_min_amt_is_modified = true;
      add_buy_min_amt_is_set = true;
      add_buy_min_amt_is_modified = true;
      sell_min_amt_is_set = true;
      sell_min_amt_is_modified = true;
      swt_min_amt_is_set = true;
      swt_min_amt_is_modified = true;
      new_buy_unit_amt_is_set = true;
      new_buy_unit_amt_is_modified = true;
      add_buy_unit_amt_is_set = true;
      add_buy_unit_amt_is_modified = true;
      sell_unit_amt_is_set = true;
      sell_unit_amt_is_modified = true;
      swt_unit_amt_is_set = true;
      swt_unit_amt_is_modified = true;
      buy_start_date_is_set = true;
      buy_start_date_is_modified = true;
      buy_end_date_is_set = true;
      buy_end_date_is_modified = true;
      sell_swt_start_date_is_set = true;
      sell_swt_start_date_is_modified = true;
      sell_swt_end_date_is_set = true;
      sell_swt_end_date_is_modified = true;
      buy_claim_start_date_is_set = true;
      buy_claim_start_date_is_modified = true;
      buy_claim_end_date_is_set = true;
      buy_claim_end_date_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      online_updated_timestamp_is_set = true;
      online_updated_timestamp_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      recruit_constant_value_is_set = true;
      recruit_constant_value_is_modified = true;
      recruit_settlement_div_is_set = true;
      recruit_settlement_div_is_modified = true;
      recruit_specity_div_is_set = true;
      recruit_specity_div_is_modified = true;
      recruit_min_qty_is_set = true;
      recruit_min_qty_is_modified = true;
      recruit_unit_qty_is_set = true;
      recruit_unit_qty_is_modified = true;
      recruit_min_amt_is_set = true;
      recruit_min_amt_is_modified = true;
      recruit_unit_amt_is_set = true;
      recruit_unit_amt_is_modified = true;
      recruit_start_date_is_set = true;
      recruit_start_date_is_modified = true;
      recruit_end_date_is_set = true;
      recruit_end_date_is_modified = true;
      cal_price_div_is_set = true;
      cal_price_div_is_modified = true;
      plowback_product_div_is_set = true;
      plowback_product_div_is_modified = true;
      recruit_start_date_sonar_is_set = true;
      recruit_start_date_sonar_is_modified = true;
      recruit_end_date_sonar_is_set = true;
      recruit_end_date_sonar_is_modified = true;
      fixed_buy_possible_div_is_set = true;
      fixed_buy_possible_div_is_modified = true;
      unit_type_product_div_is_set = true;
      unit_type_product_div_is_modified = true;
      frgn_new_buy_min_amt_is_set = true;
      frgn_new_buy_min_amt_is_modified = true;
      frgn_add_buy_min_amt_is_set = true;
      frgn_add_buy_min_amt_is_modified = true;
      frgn_sell_min_amt_is_set = true;
      frgn_sell_min_amt_is_modified = true;
      frgn_new_buy_unit_amt_is_set = true;
      frgn_new_buy_unit_amt_is_modified = true;
      frgn_add_buy_unit_amt_is_set = true;
      frgn_add_buy_unit_amt_is_modified = true;
      frgn_sell_unit_amt_is_set = true;
      frgn_sell_unit_amt_is_modified = true;
      recruit_commission_div_is_set = true;
      recruit_commission_div_is_modified = true;
      open_close_div_is_set = true;
      open_close_div_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof MutualFundProductRow ) )
       return false;
    return fieldsEqual( (MutualFundProductRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のMutualFundProductRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( MutualFundProductRow row )
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
    if ( fund_type == null ) {
      if ( row.getFundType() != null )
        return false;
    } else if ( !fund_type.equals( row.getFundType() ) ) {
        return false;
    }
    if ( init_purchase_min_qty != row.getInitPurchaseMinQty() )
      return false;
    if ( addtl_purchase_min_qty != row.getAddtlPurchaseMinQty() )
      return false;
    if ( mutualassoc_product_code == null ) {
      if ( row.getMutualassocProductCode() != null )
        return false;
    } else if ( !mutualassoc_product_code.equals( row.getMutualassocProductCode() ) ) {
        return false;
    }
    if ( system_handling_div == null ) {
      if ( row.getSystemHandlingDiv() != null )
        return false;
    } else if ( !system_handling_div.equals( row.getSystemHandlingDiv() ) ) {
        return false;
    }
    if ( buy_limit_div == null ) {
      if ( row.getBuyLimitDiv() != null )
        return false;
    } else if ( !buy_limit_div.equals( row.getBuyLimitDiv() ) ) {
        return false;
    }
    if ( standard_name == null ) {
      if ( row.getStandardName() != null )
        return false;
    } else if ( !standard_name.equals( row.getStandardName() ) ) {
        return false;
    }
    if ( eng_product_name == null ) {
      if ( row.getEngProductName() != null )
        return false;
    } else if ( !eng_product_name.equals( row.getEngProductName() ) ) {
        return false;
    }
    if ( setting_date == null ) {
      if ( row.getSettingDate() != null )
        return false;
    } else if ( !setting_date.equals( row.getSettingDate() ) ) {
        return false;
    }
    if ( redemption_date == null ) {
      if ( row.getRedemptionDate() != null )
        return false;
    } else if ( !redemption_date.equals( row.getRedemptionDate() ) ) {
        return false;
    }
    if ( sell_ban_date == null ) {
      if ( row.getSellBanDate() != null )
        return false;
    } else if ( !sell_ban_date.equals( row.getSellBanDate() ) ) {
        return false;
    }
    if ( swt_possible_group_id == null ) {
      if ( !row.getSwtPossibleGroupIdIsNull() )
        return false;
    } else if ( row.getSwtPossibleGroupIdIsNull() || ( swt_possible_group_id.intValue() != row.getSwtPossibleGroupId() ) ) {
        return false;
    }
    if ( category_code == null ) {
      if ( row.getCategoryCode() != null )
        return false;
    } else if ( !category_code.equals( row.getCategoryCode() ) ) {
        return false;
    }
    if ( indication_ranking == null ) {
      if ( !row.getIndicationRankingIsNull() )
        return false;
    } else if ( row.getIndicationRankingIsNull() || ( indication_ranking.intValue() != row.getIndicationRanking() ) ) {
        return false;
    }
    if ( buy_constant_value == null ) {
      if ( !row.getBuyConstantValueIsNull() )
        return false;
    } else if ( row.getBuyConstantValueIsNull() || ( buy_constant_value.doubleValue() != row.getBuyConstantValue() ) ) {
        return false;
    }
    if ( sell_constant_value == null ) {
      if ( !row.getSellConstantValueIsNull() )
        return false;
    } else if ( row.getSellConstantValueIsNull() || ( sell_constant_value.doubleValue() != row.getSellConstantValue() ) ) {
        return false;
    }
    if ( reference_constant_value == null ) {
      if ( !row.getReferenceConstantValueIsNull() )
        return false;
    } else if ( row.getReferenceConstantValueIsNull() || ( reference_constant_value.doubleValue() != row.getReferenceConstantValue() ) ) {
        return false;
    }
    if ( constant_value_app_date == null ) {
      if ( row.getConstantValueAppDate() != null )
        return false;
    } else if ( !constant_value_app_date.equals( row.getConstantValueAppDate() ) ) {
        return false;
    }
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( constant_value_calc_unit == null ) {
      if ( !row.getConstantValueCalcUnitIsNull() )
        return false;
    } else if ( row.getConstantValueCalcUnitIsNull() || ( constant_value_calc_unit.intValue() != row.getConstantValueCalcUnit() ) ) {
        return false;
    }
    if ( buy_settlement_div == null ) {
      if ( row.getBuySettlementDiv() != null )
        return false;
    } else if ( !buy_settlement_div.equals( row.getBuySettlementDiv() ) ) {
        return false;
    }
    if ( sell_settlement_div == null ) {
      if ( row.getSellSettlementDiv() != null )
        return false;
    } else if ( !sell_settlement_div.equals( row.getSellSettlementDiv() ) ) {
        return false;
    }
    if ( delivery_method == null ) {
      if ( row.getDeliveryMethod() != null )
        return false;
    } else if ( !delivery_method.equals( row.getDeliveryMethod() ) ) {
        return false;
    }
    if ( buy_specity_div == null ) {
      if ( row.getBuySpecityDiv() != null )
        return false;
    } else if ( !buy_specity_div.equals( row.getBuySpecityDiv() ) ) {
        return false;
    }
    if ( sell_specify_div == null ) {
      if ( row.getSellSpecifyDiv() != null )
        return false;
    } else if ( !sell_specify_div.equals( row.getSellSpecifyDiv() ) ) {
        return false;
    }
    if ( swt_specify_div == null ) {
      if ( row.getSwtSpecifyDiv() != null )
        return false;
    } else if ( !swt_specify_div.equals( row.getSwtSpecifyDiv() ) ) {
        return false;
    }
    if ( stock_type_bond_type == null ) {
      if ( row.getStockTypeBondType() != null )
        return false;
    } else if ( !stock_type_bond_type.equals( row.getStockTypeBondType() ) ) {
        return false;
    }
    if ( contract_institution_type == null ) {
      if ( row.getContractInstitutionType() != null )
        return false;
    } else if ( !contract_institution_type.equals( row.getContractInstitutionType() ) ) {
        return false;
    }
    if ( perference_money_div == null ) {
      if ( row.getPerferenceMoneyDiv() != null )
        return false;
    } else if ( !perference_money_div.equals( row.getPerferenceMoneyDiv() ) ) {
        return false;
    }
    if ( input_unit == null ) {
      if ( row.getInputUnit() != null )
        return false;
    } else if ( !input_unit.equals( row.getInputUnit() ) ) {
        return false;
    }
    if ( new_buy_min_qty == null ) {
      if ( !row.getNewBuyMinQtyIsNull() )
        return false;
    } else if ( row.getNewBuyMinQtyIsNull() || ( new_buy_min_qty.longValue() != row.getNewBuyMinQty() ) ) {
        return false;
    }
    if ( add_buy_min_qty == null ) {
      if ( !row.getAddBuyMinQtyIsNull() )
        return false;
    } else if ( row.getAddBuyMinQtyIsNull() || ( add_buy_min_qty.longValue() != row.getAddBuyMinQty() ) ) {
        return false;
    }
    if ( sell_min_qty == null ) {
      if ( !row.getSellMinQtyIsNull() )
        return false;
    } else if ( row.getSellMinQtyIsNull() || ( sell_min_qty.longValue() != row.getSellMinQty() ) ) {
        return false;
    }
    if ( swt_min_qty == null ) {
      if ( !row.getSwtMinQtyIsNull() )
        return false;
    } else if ( row.getSwtMinQtyIsNull() || ( swt_min_qty.longValue() != row.getSwtMinQty() ) ) {
        return false;
    }
    if ( new_buy_unit_qty == null ) {
      if ( !row.getNewBuyUnitQtyIsNull() )
        return false;
    } else if ( row.getNewBuyUnitQtyIsNull() || ( new_buy_unit_qty.longValue() != row.getNewBuyUnitQty() ) ) {
        return false;
    }
    if ( add_buy_unit_qty == null ) {
      if ( !row.getAddBuyUnitQtyIsNull() )
        return false;
    } else if ( row.getAddBuyUnitQtyIsNull() || ( add_buy_unit_qty.longValue() != row.getAddBuyUnitQty() ) ) {
        return false;
    }
    if ( sell_unit_qty == null ) {
      if ( !row.getSellUnitQtyIsNull() )
        return false;
    } else if ( row.getSellUnitQtyIsNull() || ( sell_unit_qty.longValue() != row.getSellUnitQty() ) ) {
        return false;
    }
    if ( swt_unit_qty == null ) {
      if ( !row.getSwtUnitQtyIsNull() )
        return false;
    } else if ( row.getSwtUnitQtyIsNull() || ( swt_unit_qty.longValue() != row.getSwtUnitQty() ) ) {
        return false;
    }
    if ( new_buy_min_amt == null ) {
      if ( !row.getNewBuyMinAmtIsNull() )
        return false;
    } else if ( row.getNewBuyMinAmtIsNull() || ( new_buy_min_amt.longValue() != row.getNewBuyMinAmt() ) ) {
        return false;
    }
    if ( add_buy_min_amt == null ) {
      if ( !row.getAddBuyMinAmtIsNull() )
        return false;
    } else if ( row.getAddBuyMinAmtIsNull() || ( add_buy_min_amt.longValue() != row.getAddBuyMinAmt() ) ) {
        return false;
    }
    if ( sell_min_amt == null ) {
      if ( !row.getSellMinAmtIsNull() )
        return false;
    } else if ( row.getSellMinAmtIsNull() || ( sell_min_amt.longValue() != row.getSellMinAmt() ) ) {
        return false;
    }
    if ( swt_min_amt == null ) {
      if ( !row.getSwtMinAmtIsNull() )
        return false;
    } else if ( row.getSwtMinAmtIsNull() || ( swt_min_amt.longValue() != row.getSwtMinAmt() ) ) {
        return false;
    }
    if ( new_buy_unit_amt == null ) {
      if ( !row.getNewBuyUnitAmtIsNull() )
        return false;
    } else if ( row.getNewBuyUnitAmtIsNull() || ( new_buy_unit_amt.longValue() != row.getNewBuyUnitAmt() ) ) {
        return false;
    }
    if ( add_buy_unit_amt == null ) {
      if ( !row.getAddBuyUnitAmtIsNull() )
        return false;
    } else if ( row.getAddBuyUnitAmtIsNull() || ( add_buy_unit_amt.longValue() != row.getAddBuyUnitAmt() ) ) {
        return false;
    }
    if ( sell_unit_amt == null ) {
      if ( !row.getSellUnitAmtIsNull() )
        return false;
    } else if ( row.getSellUnitAmtIsNull() || ( sell_unit_amt.longValue() != row.getSellUnitAmt() ) ) {
        return false;
    }
    if ( swt_unit_amt == null ) {
      if ( !row.getSwtUnitAmtIsNull() )
        return false;
    } else if ( row.getSwtUnitAmtIsNull() || ( swt_unit_amt.longValue() != row.getSwtUnitAmt() ) ) {
        return false;
    }
    if ( buy_start_date == null ) {
      if ( row.getBuyStartDate() != null )
        return false;
    } else if ( !buy_start_date.equals( row.getBuyStartDate() ) ) {
        return false;
    }
    if ( buy_end_date == null ) {
      if ( row.getBuyEndDate() != null )
        return false;
    } else if ( !buy_end_date.equals( row.getBuyEndDate() ) ) {
        return false;
    }
    if ( sell_swt_start_date == null ) {
      if ( row.getSellSwtStartDate() != null )
        return false;
    } else if ( !sell_swt_start_date.equals( row.getSellSwtStartDate() ) ) {
        return false;
    }
    if ( sell_swt_end_date == null ) {
      if ( row.getSellSwtEndDate() != null )
        return false;
    } else if ( !sell_swt_end_date.equals( row.getSellSwtEndDate() ) ) {
        return false;
    }
    if ( buy_claim_start_date == null ) {
      if ( row.getBuyClaimStartDate() != null )
        return false;
    } else if ( !buy_claim_start_date.equals( row.getBuyClaimStartDate() ) ) {
        return false;
    }
    if ( buy_claim_end_date == null ) {
      if ( row.getBuyClaimEndDate() != null )
        return false;
    } else if ( !buy_claim_end_date.equals( row.getBuyClaimEndDate() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( online_updated_timestamp == null ) {
      if ( row.getOnlineUpdatedTimestamp() != null )
        return false;
    } else if ( !online_updated_timestamp.equals( row.getOnlineUpdatedTimestamp() ) ) {
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
    if ( recruit_constant_value == null ) {
      if ( !row.getRecruitConstantValueIsNull() )
        return false;
    } else if ( row.getRecruitConstantValueIsNull() || ( recruit_constant_value.doubleValue() != row.getRecruitConstantValue() ) ) {
        return false;
    }
    if ( recruit_settlement_div == null ) {
      if ( row.getRecruitSettlementDiv() != null )
        return false;
    } else if ( !recruit_settlement_div.equals( row.getRecruitSettlementDiv() ) ) {
        return false;
    }
    if ( recruit_specity_div == null ) {
      if ( row.getRecruitSpecityDiv() != null )
        return false;
    } else if ( !recruit_specity_div.equals( row.getRecruitSpecityDiv() ) ) {
        return false;
    }
    if ( recruit_min_qty == null ) {
      if ( !row.getRecruitMinQtyIsNull() )
        return false;
    } else if ( row.getRecruitMinQtyIsNull() || ( recruit_min_qty.longValue() != row.getRecruitMinQty() ) ) {
        return false;
    }
    if ( recruit_unit_qty == null ) {
      if ( !row.getRecruitUnitQtyIsNull() )
        return false;
    } else if ( row.getRecruitUnitQtyIsNull() || ( recruit_unit_qty.longValue() != row.getRecruitUnitQty() ) ) {
        return false;
    }
    if ( recruit_min_amt == null ) {
      if ( !row.getRecruitMinAmtIsNull() )
        return false;
    } else if ( row.getRecruitMinAmtIsNull() || ( recruit_min_amt.longValue() != row.getRecruitMinAmt() ) ) {
        return false;
    }
    if ( recruit_unit_amt == null ) {
      if ( !row.getRecruitUnitAmtIsNull() )
        return false;
    } else if ( row.getRecruitUnitAmtIsNull() || ( recruit_unit_amt.longValue() != row.getRecruitUnitAmt() ) ) {
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
    if ( cal_price_div == null ) {
      if ( row.getCalPriceDiv() != null )
        return false;
    } else if ( !cal_price_div.equals( row.getCalPriceDiv() ) ) {
        return false;
    }
    if ( plowback_product_div == null ) {
      if ( row.getPlowbackProductDiv() != null )
        return false;
    } else if ( !plowback_product_div.equals( row.getPlowbackProductDiv() ) ) {
        return false;
    }
    if ( recruit_start_date_sonar == null ) {
      if ( row.getRecruitStartDateSonar() != null )
        return false;
    } else if ( !recruit_start_date_sonar.equals( row.getRecruitStartDateSonar() ) ) {
        return false;
    }
    if ( recruit_end_date_sonar == null ) {
      if ( row.getRecruitEndDateSonar() != null )
        return false;
    } else if ( !recruit_end_date_sonar.equals( row.getRecruitEndDateSonar() ) ) {
        return false;
    }
    if ( fixed_buy_possible_div == null ) {
      if ( row.getFixedBuyPossibleDiv() != null )
        return false;
    } else if ( !fixed_buy_possible_div.equals( row.getFixedBuyPossibleDiv() ) ) {
        return false;
    }
    if ( unit_type_product_div == null ) {
      if ( row.getUnitTypeProductDiv() != null )
        return false;
    } else if ( !unit_type_product_div.equals( row.getUnitTypeProductDiv() ) ) {
        return false;
    }
    if ( frgn_new_buy_min_amt == null ) {
      if ( !row.getFrgnNewBuyMinAmtIsNull() )
        return false;
    } else if ( row.getFrgnNewBuyMinAmtIsNull() || ( frgn_new_buy_min_amt.longValue() != row.getFrgnNewBuyMinAmt() ) ) {
        return false;
    }
    if ( frgn_add_buy_min_amt == null ) {
      if ( !row.getFrgnAddBuyMinAmtIsNull() )
        return false;
    } else if ( row.getFrgnAddBuyMinAmtIsNull() || ( frgn_add_buy_min_amt.longValue() != row.getFrgnAddBuyMinAmt() ) ) {
        return false;
    }
    if ( frgn_sell_min_amt == null ) {
      if ( !row.getFrgnSellMinAmtIsNull() )
        return false;
    } else if ( row.getFrgnSellMinAmtIsNull() || ( frgn_sell_min_amt.longValue() != row.getFrgnSellMinAmt() ) ) {
        return false;
    }
    if ( frgn_new_buy_unit_amt == null ) {
      if ( !row.getFrgnNewBuyUnitAmtIsNull() )
        return false;
    } else if ( row.getFrgnNewBuyUnitAmtIsNull() || ( frgn_new_buy_unit_amt.longValue() != row.getFrgnNewBuyUnitAmt() ) ) {
        return false;
    }
    if ( frgn_add_buy_unit_amt == null ) {
      if ( !row.getFrgnAddBuyUnitAmtIsNull() )
        return false;
    } else if ( row.getFrgnAddBuyUnitAmtIsNull() || ( frgn_add_buy_unit_amt.longValue() != row.getFrgnAddBuyUnitAmt() ) ) {
        return false;
    }
    if ( frgn_sell_unit_amt == null ) {
      if ( !row.getFrgnSellUnitAmtIsNull() )
        return false;
    } else if ( row.getFrgnSellUnitAmtIsNull() || ( frgn_sell_unit_amt.longValue() != row.getFrgnSellUnitAmt() ) ) {
        return false;
    }
    if ( recruit_commission_div == null ) {
      if ( row.getRecruitCommissionDiv() != null )
        return false;
    } else if ( !recruit_commission_div.equals( row.getRecruitCommissionDiv() ) ) {
        return false;
    }
    if ( open_close_div == null ) {
      if ( row.getOpenCloseDiv() != null )
        return false;
    } else if ( !open_close_div.equals( row.getOpenCloseDiv() ) ) {
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
        + (fund_type!=null? fund_type.hashCode(): 0) 
        + ((int) init_purchase_min_qty)
        + ((int) addtl_purchase_min_qty)
        + (mutualassoc_product_code!=null? mutualassoc_product_code.hashCode(): 0) 
        + (system_handling_div!=null? system_handling_div.hashCode(): 0) 
        + (buy_limit_div!=null? buy_limit_div.hashCode(): 0) 
        + (standard_name!=null? standard_name.hashCode(): 0) 
        + (eng_product_name!=null? eng_product_name.hashCode(): 0) 
        + (setting_date!=null? setting_date.hashCode(): 0) 
        + (redemption_date!=null? redemption_date.hashCode(): 0) 
        + (sell_ban_date!=null? sell_ban_date.hashCode(): 0) 
        + (swt_possible_group_id!=null? swt_possible_group_id.hashCode(): 0) 
        + (category_code!=null? category_code.hashCode(): 0) 
        + (indication_ranking!=null? indication_ranking.hashCode(): 0) 
        + (buy_constant_value!=null? buy_constant_value.hashCode(): 0) 
        + (sell_constant_value!=null? sell_constant_value.hashCode(): 0) 
        + (reference_constant_value!=null? reference_constant_value.hashCode(): 0) 
        + (constant_value_app_date!=null? constant_value_app_date.hashCode(): 0) 
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (constant_value_calc_unit!=null? constant_value_calc_unit.hashCode(): 0) 
        + (buy_settlement_div!=null? buy_settlement_div.hashCode(): 0) 
        + (sell_settlement_div!=null? sell_settlement_div.hashCode(): 0) 
        + (delivery_method!=null? delivery_method.hashCode(): 0) 
        + (buy_specity_div!=null? buy_specity_div.hashCode(): 0) 
        + (sell_specify_div!=null? sell_specify_div.hashCode(): 0) 
        + (swt_specify_div!=null? swt_specify_div.hashCode(): 0) 
        + (stock_type_bond_type!=null? stock_type_bond_type.hashCode(): 0) 
        + (contract_institution_type!=null? contract_institution_type.hashCode(): 0) 
        + (perference_money_div!=null? perference_money_div.hashCode(): 0) 
        + (input_unit!=null? input_unit.hashCode(): 0) 
        + (new_buy_min_qty!=null? new_buy_min_qty.hashCode(): 0) 
        + (add_buy_min_qty!=null? add_buy_min_qty.hashCode(): 0) 
        + (sell_min_qty!=null? sell_min_qty.hashCode(): 0) 
        + (swt_min_qty!=null? swt_min_qty.hashCode(): 0) 
        + (new_buy_unit_qty!=null? new_buy_unit_qty.hashCode(): 0) 
        + (add_buy_unit_qty!=null? add_buy_unit_qty.hashCode(): 0) 
        + (sell_unit_qty!=null? sell_unit_qty.hashCode(): 0) 
        + (swt_unit_qty!=null? swt_unit_qty.hashCode(): 0) 
        + (new_buy_min_amt!=null? new_buy_min_amt.hashCode(): 0) 
        + (add_buy_min_amt!=null? add_buy_min_amt.hashCode(): 0) 
        + (sell_min_amt!=null? sell_min_amt.hashCode(): 0) 
        + (swt_min_amt!=null? swt_min_amt.hashCode(): 0) 
        + (new_buy_unit_amt!=null? new_buy_unit_amt.hashCode(): 0) 
        + (add_buy_unit_amt!=null? add_buy_unit_amt.hashCode(): 0) 
        + (sell_unit_amt!=null? sell_unit_amt.hashCode(): 0) 
        + (swt_unit_amt!=null? swt_unit_amt.hashCode(): 0) 
        + (buy_start_date!=null? buy_start_date.hashCode(): 0) 
        + (buy_end_date!=null? buy_end_date.hashCode(): 0) 
        + (sell_swt_start_date!=null? sell_swt_start_date.hashCode(): 0) 
        + (sell_swt_end_date!=null? sell_swt_end_date.hashCode(): 0) 
        + (buy_claim_start_date!=null? buy_claim_start_date.hashCode(): 0) 
        + (buy_claim_end_date!=null? buy_claim_end_date.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (online_updated_timestamp!=null? online_updated_timestamp.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (recruit_constant_value!=null? recruit_constant_value.hashCode(): 0) 
        + (recruit_settlement_div!=null? recruit_settlement_div.hashCode(): 0) 
        + (recruit_specity_div!=null? recruit_specity_div.hashCode(): 0) 
        + (recruit_min_qty!=null? recruit_min_qty.hashCode(): 0) 
        + (recruit_unit_qty!=null? recruit_unit_qty.hashCode(): 0) 
        + (recruit_min_amt!=null? recruit_min_amt.hashCode(): 0) 
        + (recruit_unit_amt!=null? recruit_unit_amt.hashCode(): 0) 
        + (recruit_start_date!=null? recruit_start_date.hashCode(): 0) 
        + (recruit_end_date!=null? recruit_end_date.hashCode(): 0) 
        + (cal_price_div!=null? cal_price_div.hashCode(): 0) 
        + (plowback_product_div!=null? plowback_product_div.hashCode(): 0) 
        + (recruit_start_date_sonar!=null? recruit_start_date_sonar.hashCode(): 0) 
        + (recruit_end_date_sonar!=null? recruit_end_date_sonar.hashCode(): 0) 
        + (fixed_buy_possible_div!=null? fixed_buy_possible_div.hashCode(): 0) 
        + (unit_type_product_div!=null? unit_type_product_div.hashCode(): 0) 
        + (frgn_new_buy_min_amt!=null? frgn_new_buy_min_amt.hashCode(): 0) 
        + (frgn_add_buy_min_amt!=null? frgn_add_buy_min_amt.hashCode(): 0) 
        + (frgn_sell_min_amt!=null? frgn_sell_min_amt.hashCode(): 0) 
        + (frgn_new_buy_unit_amt!=null? frgn_new_buy_unit_amt.hashCode(): 0) 
        + (frgn_add_buy_unit_amt!=null? frgn_add_buy_unit_amt.hashCode(): 0) 
        + (frgn_sell_unit_amt!=null? frgn_sell_unit_amt.hashCode(): 0) 
        + (recruit_commission_div!=null? recruit_commission_div.hashCode(): 0) 
        + (open_close_div!=null? open_close_div.hashCode(): 0) 
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
		if ( !fund_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'fund_type' must be set before inserting.");
		if ( !init_purchase_min_qty_is_set )
			throw new IllegalArgumentException("non-nullable field 'init_purchase_min_qty' must be set before inserting.");
		if ( !addtl_purchase_min_qty_is_set )
			throw new IllegalArgumentException("non-nullable field 'addtl_purchase_min_qty' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
		if ( !online_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'online_updated_timestamp' must be set before inserting.");
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
		map.put("fund_type",fund_type);
		map.put("init_purchase_min_qty",new Long(init_purchase_min_qty));
		map.put("addtl_purchase_min_qty",new Long(addtl_purchase_min_qty));
		if ( mutualassoc_product_code != null )
			map.put("mutualassoc_product_code",mutualassoc_product_code);
		if ( system_handling_div != null )
			map.put("system_handling_div",system_handling_div);
		if ( buy_limit_div != null )
			map.put("buy_limit_div",buy_limit_div);
		if ( standard_name != null )
			map.put("standard_name",standard_name);
		if ( eng_product_name != null )
			map.put("eng_product_name",eng_product_name);
		if ( setting_date != null )
			map.put("setting_date",setting_date);
		if ( redemption_date != null )
			map.put("redemption_date",redemption_date);
		if ( sell_ban_date != null )
			map.put("sell_ban_date",sell_ban_date);
		if ( swt_possible_group_id != null )
			map.put("swt_possible_group_id",swt_possible_group_id);
		if ( category_code != null )
			map.put("category_code",category_code);
		if ( indication_ranking != null )
			map.put("indication_ranking",indication_ranking);
		if ( buy_constant_value != null )
			map.put("buy_constant_value",buy_constant_value);
		if ( sell_constant_value != null )
			map.put("sell_constant_value",sell_constant_value);
		if ( reference_constant_value != null )
			map.put("reference_constant_value",reference_constant_value);
		if ( constant_value_app_date != null )
			map.put("constant_value_app_date",constant_value_app_date);
		if ( currency_code != null )
			map.put("currency_code",currency_code);
		if ( constant_value_calc_unit != null )
			map.put("constant_value_calc_unit",constant_value_calc_unit);
		if ( buy_settlement_div != null )
			map.put("buy_settlement_div",buy_settlement_div);
		if ( sell_settlement_div != null )
			map.put("sell_settlement_div",sell_settlement_div);
		if ( delivery_method != null )
			map.put("delivery_method",delivery_method);
		if ( buy_specity_div != null )
			map.put("buy_specity_div",buy_specity_div);
		if ( sell_specify_div != null )
			map.put("sell_specify_div",sell_specify_div);
		if ( swt_specify_div != null )
			map.put("swt_specify_div",swt_specify_div);
		if ( stock_type_bond_type != null )
			map.put("stock_type_bond_type",stock_type_bond_type);
		if ( contract_institution_type != null )
			map.put("contract_institution_type",contract_institution_type);
		if ( perference_money_div != null )
			map.put("perference_money_div",perference_money_div);
		if ( input_unit != null )
			map.put("input_unit",input_unit);
		if ( new_buy_min_qty != null )
			map.put("new_buy_min_qty",new_buy_min_qty);
		if ( add_buy_min_qty != null )
			map.put("add_buy_min_qty",add_buy_min_qty);
		if ( sell_min_qty != null )
			map.put("sell_min_qty",sell_min_qty);
		if ( swt_min_qty != null )
			map.put("swt_min_qty",swt_min_qty);
		if ( new_buy_unit_qty != null )
			map.put("new_buy_unit_qty",new_buy_unit_qty);
		if ( add_buy_unit_qty != null )
			map.put("add_buy_unit_qty",add_buy_unit_qty);
		if ( sell_unit_qty != null )
			map.put("sell_unit_qty",sell_unit_qty);
		if ( swt_unit_qty != null )
			map.put("swt_unit_qty",swt_unit_qty);
		if ( new_buy_min_amt != null )
			map.put("new_buy_min_amt",new_buy_min_amt);
		if ( add_buy_min_amt != null )
			map.put("add_buy_min_amt",add_buy_min_amt);
		if ( sell_min_amt != null )
			map.put("sell_min_amt",sell_min_amt);
		if ( swt_min_amt != null )
			map.put("swt_min_amt",swt_min_amt);
		if ( new_buy_unit_amt != null )
			map.put("new_buy_unit_amt",new_buy_unit_amt);
		if ( add_buy_unit_amt != null )
			map.put("add_buy_unit_amt",add_buy_unit_amt);
		if ( sell_unit_amt != null )
			map.put("sell_unit_amt",sell_unit_amt);
		if ( swt_unit_amt != null )
			map.put("swt_unit_amt",swt_unit_amt);
		if ( buy_start_date != null )
			map.put("buy_start_date",buy_start_date);
		if ( buy_end_date != null )
			map.put("buy_end_date",buy_end_date);
		if ( sell_swt_start_date != null )
			map.put("sell_swt_start_date",sell_swt_start_date);
		if ( sell_swt_end_date != null )
			map.put("sell_swt_end_date",sell_swt_end_date);
		if ( buy_claim_start_date != null )
			map.put("buy_claim_start_date",buy_claim_start_date);
		if ( buy_claim_end_date != null )
			map.put("buy_claim_end_date",buy_claim_end_date);
		map.put("last_updater",last_updater);
		map.put("online_updated_timestamp",online_updated_timestamp);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( recruit_constant_value != null )
			map.put("recruit_constant_value",recruit_constant_value);
		if ( recruit_settlement_div != null )
			map.put("recruit_settlement_div",recruit_settlement_div);
		if ( recruit_specity_div != null )
			map.put("recruit_specity_div",recruit_specity_div);
		if ( recruit_min_qty != null )
			map.put("recruit_min_qty",recruit_min_qty);
		if ( recruit_unit_qty != null )
			map.put("recruit_unit_qty",recruit_unit_qty);
		if ( recruit_min_amt != null )
			map.put("recruit_min_amt",recruit_min_amt);
		if ( recruit_unit_amt != null )
			map.put("recruit_unit_amt",recruit_unit_amt);
		if ( recruit_start_date != null )
			map.put("recruit_start_date",recruit_start_date);
		if ( recruit_end_date != null )
			map.put("recruit_end_date",recruit_end_date);
		if ( cal_price_div != null )
			map.put("cal_price_div",cal_price_div);
		if ( plowback_product_div != null )
			map.put("plowback_product_div",plowback_product_div);
		if ( recruit_start_date_sonar != null )
			map.put("recruit_start_date_sonar",recruit_start_date_sonar);
		if ( recruit_end_date_sonar != null )
			map.put("recruit_end_date_sonar",recruit_end_date_sonar);
		if ( fixed_buy_possible_div != null )
			map.put("fixed_buy_possible_div",fixed_buy_possible_div);
		if ( unit_type_product_div != null )
			map.put("unit_type_product_div",unit_type_product_div);
		if ( frgn_new_buy_min_amt != null )
			map.put("frgn_new_buy_min_amt",frgn_new_buy_min_amt);
		if ( frgn_add_buy_min_amt != null )
			map.put("frgn_add_buy_min_amt",frgn_add_buy_min_amt);
		if ( frgn_sell_min_amt != null )
			map.put("frgn_sell_min_amt",frgn_sell_min_amt);
		if ( frgn_new_buy_unit_amt != null )
			map.put("frgn_new_buy_unit_amt",frgn_new_buy_unit_amt);
		if ( frgn_add_buy_unit_amt != null )
			map.put("frgn_add_buy_unit_amt",frgn_add_buy_unit_amt);
		if ( frgn_sell_unit_amt != null )
			map.put("frgn_sell_unit_amt",frgn_sell_unit_amt);
		if ( recruit_commission_div != null )
			map.put("recruit_commission_div",recruit_commission_div);
		if ( open_close_div != null )
			map.put("open_close_div",open_close_div);
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
		if ( fund_type_is_modified )
			map.put("fund_type",fund_type);
		if ( init_purchase_min_qty_is_modified )
			map.put("init_purchase_min_qty",new Long(init_purchase_min_qty));
		if ( addtl_purchase_min_qty_is_modified )
			map.put("addtl_purchase_min_qty",new Long(addtl_purchase_min_qty));
		if ( mutualassoc_product_code_is_modified )
			map.put("mutualassoc_product_code",mutualassoc_product_code);
		if ( system_handling_div_is_modified )
			map.put("system_handling_div",system_handling_div);
		if ( buy_limit_div_is_modified )
			map.put("buy_limit_div",buy_limit_div);
		if ( standard_name_is_modified )
			map.put("standard_name",standard_name);
		if ( eng_product_name_is_modified )
			map.put("eng_product_name",eng_product_name);
		if ( setting_date_is_modified )
			map.put("setting_date",setting_date);
		if ( redemption_date_is_modified )
			map.put("redemption_date",redemption_date);
		if ( sell_ban_date_is_modified )
			map.put("sell_ban_date",sell_ban_date);
		if ( swt_possible_group_id_is_modified )
			map.put("swt_possible_group_id",swt_possible_group_id);
		if ( category_code_is_modified )
			map.put("category_code",category_code);
		if ( indication_ranking_is_modified )
			map.put("indication_ranking",indication_ranking);
		if ( buy_constant_value_is_modified )
			map.put("buy_constant_value",buy_constant_value);
		if ( sell_constant_value_is_modified )
			map.put("sell_constant_value",sell_constant_value);
		if ( reference_constant_value_is_modified )
			map.put("reference_constant_value",reference_constant_value);
		if ( constant_value_app_date_is_modified )
			map.put("constant_value_app_date",constant_value_app_date);
		if ( currency_code_is_modified )
			map.put("currency_code",currency_code);
		if ( constant_value_calc_unit_is_modified )
			map.put("constant_value_calc_unit",constant_value_calc_unit);
		if ( buy_settlement_div_is_modified )
			map.put("buy_settlement_div",buy_settlement_div);
		if ( sell_settlement_div_is_modified )
			map.put("sell_settlement_div",sell_settlement_div);
		if ( delivery_method_is_modified )
			map.put("delivery_method",delivery_method);
		if ( buy_specity_div_is_modified )
			map.put("buy_specity_div",buy_specity_div);
		if ( sell_specify_div_is_modified )
			map.put("sell_specify_div",sell_specify_div);
		if ( swt_specify_div_is_modified )
			map.put("swt_specify_div",swt_specify_div);
		if ( stock_type_bond_type_is_modified )
			map.put("stock_type_bond_type",stock_type_bond_type);
		if ( contract_institution_type_is_modified )
			map.put("contract_institution_type",contract_institution_type);
		if ( perference_money_div_is_modified )
			map.put("perference_money_div",perference_money_div);
		if ( input_unit_is_modified )
			map.put("input_unit",input_unit);
		if ( new_buy_min_qty_is_modified )
			map.put("new_buy_min_qty",new_buy_min_qty);
		if ( add_buy_min_qty_is_modified )
			map.put("add_buy_min_qty",add_buy_min_qty);
		if ( sell_min_qty_is_modified )
			map.put("sell_min_qty",sell_min_qty);
		if ( swt_min_qty_is_modified )
			map.put("swt_min_qty",swt_min_qty);
		if ( new_buy_unit_qty_is_modified )
			map.put("new_buy_unit_qty",new_buy_unit_qty);
		if ( add_buy_unit_qty_is_modified )
			map.put("add_buy_unit_qty",add_buy_unit_qty);
		if ( sell_unit_qty_is_modified )
			map.put("sell_unit_qty",sell_unit_qty);
		if ( swt_unit_qty_is_modified )
			map.put("swt_unit_qty",swt_unit_qty);
		if ( new_buy_min_amt_is_modified )
			map.put("new_buy_min_amt",new_buy_min_amt);
		if ( add_buy_min_amt_is_modified )
			map.put("add_buy_min_amt",add_buy_min_amt);
		if ( sell_min_amt_is_modified )
			map.put("sell_min_amt",sell_min_amt);
		if ( swt_min_amt_is_modified )
			map.put("swt_min_amt",swt_min_amt);
		if ( new_buy_unit_amt_is_modified )
			map.put("new_buy_unit_amt",new_buy_unit_amt);
		if ( add_buy_unit_amt_is_modified )
			map.put("add_buy_unit_amt",add_buy_unit_amt);
		if ( sell_unit_amt_is_modified )
			map.put("sell_unit_amt",sell_unit_amt);
		if ( swt_unit_amt_is_modified )
			map.put("swt_unit_amt",swt_unit_amt);
		if ( buy_start_date_is_modified )
			map.put("buy_start_date",buy_start_date);
		if ( buy_end_date_is_modified )
			map.put("buy_end_date",buy_end_date);
		if ( sell_swt_start_date_is_modified )
			map.put("sell_swt_start_date",sell_swt_start_date);
		if ( sell_swt_end_date_is_modified )
			map.put("sell_swt_end_date",sell_swt_end_date);
		if ( buy_claim_start_date_is_modified )
			map.put("buy_claim_start_date",buy_claim_start_date);
		if ( buy_claim_end_date_is_modified )
			map.put("buy_claim_end_date",buy_claim_end_date);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( online_updated_timestamp_is_modified )
			map.put("online_updated_timestamp",online_updated_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( recruit_constant_value_is_modified )
			map.put("recruit_constant_value",recruit_constant_value);
		if ( recruit_settlement_div_is_modified )
			map.put("recruit_settlement_div",recruit_settlement_div);
		if ( recruit_specity_div_is_modified )
			map.put("recruit_specity_div",recruit_specity_div);
		if ( recruit_min_qty_is_modified )
			map.put("recruit_min_qty",recruit_min_qty);
		if ( recruit_unit_qty_is_modified )
			map.put("recruit_unit_qty",recruit_unit_qty);
		if ( recruit_min_amt_is_modified )
			map.put("recruit_min_amt",recruit_min_amt);
		if ( recruit_unit_amt_is_modified )
			map.put("recruit_unit_amt",recruit_unit_amt);
		if ( recruit_start_date_is_modified )
			map.put("recruit_start_date",recruit_start_date);
		if ( recruit_end_date_is_modified )
			map.put("recruit_end_date",recruit_end_date);
		if ( cal_price_div_is_modified )
			map.put("cal_price_div",cal_price_div);
		if ( plowback_product_div_is_modified )
			map.put("plowback_product_div",plowback_product_div);
		if ( recruit_start_date_sonar_is_modified )
			map.put("recruit_start_date_sonar",recruit_start_date_sonar);
		if ( recruit_end_date_sonar_is_modified )
			map.put("recruit_end_date_sonar",recruit_end_date_sonar);
		if ( fixed_buy_possible_div_is_modified )
			map.put("fixed_buy_possible_div",fixed_buy_possible_div);
		if ( unit_type_product_div_is_modified )
			map.put("unit_type_product_div",unit_type_product_div);
		if ( frgn_new_buy_min_amt_is_modified )
			map.put("frgn_new_buy_min_amt",frgn_new_buy_min_amt);
		if ( frgn_add_buy_min_amt_is_modified )
			map.put("frgn_add_buy_min_amt",frgn_add_buy_min_amt);
		if ( frgn_sell_min_amt_is_modified )
			map.put("frgn_sell_min_amt",frgn_sell_min_amt);
		if ( frgn_new_buy_unit_amt_is_modified )
			map.put("frgn_new_buy_unit_amt",frgn_new_buy_unit_amt);
		if ( frgn_add_buy_unit_amt_is_modified )
			map.put("frgn_add_buy_unit_amt",frgn_add_buy_unit_amt);
		if ( frgn_sell_unit_amt_is_modified )
			map.put("frgn_sell_unit_amt",frgn_sell_unit_amt);
		if ( recruit_commission_div_is_modified )
			map.put("recruit_commission_div",recruit_commission_div);
		if ( open_close_div_is_modified )
			map.put("open_close_div",open_close_div);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( product_code_is_set )
				map.put("product_code",product_code);
			if ( product_issue_code_is_set )
				map.put("product_issue_code",product_issue_code);
			if ( fund_type_is_set )
				map.put("fund_type",fund_type);
			if ( init_purchase_min_qty_is_set )
				map.put("init_purchase_min_qty",new Long(init_purchase_min_qty));
			if ( addtl_purchase_min_qty_is_set )
				map.put("addtl_purchase_min_qty",new Long(addtl_purchase_min_qty));
			map.put("mutualassoc_product_code",mutualassoc_product_code);
			map.put("system_handling_div",system_handling_div);
			map.put("buy_limit_div",buy_limit_div);
			map.put("standard_name",standard_name);
			map.put("eng_product_name",eng_product_name);
			map.put("setting_date",setting_date);
			map.put("redemption_date",redemption_date);
			map.put("sell_ban_date",sell_ban_date);
			map.put("swt_possible_group_id",swt_possible_group_id);
			map.put("category_code",category_code);
			map.put("indication_ranking",indication_ranking);
			map.put("buy_constant_value",buy_constant_value);
			map.put("sell_constant_value",sell_constant_value);
			map.put("reference_constant_value",reference_constant_value);
			map.put("constant_value_app_date",constant_value_app_date);
			map.put("currency_code",currency_code);
			map.put("constant_value_calc_unit",constant_value_calc_unit);
			map.put("buy_settlement_div",buy_settlement_div);
			map.put("sell_settlement_div",sell_settlement_div);
			map.put("delivery_method",delivery_method);
			map.put("buy_specity_div",buy_specity_div);
			map.put("sell_specify_div",sell_specify_div);
			map.put("swt_specify_div",swt_specify_div);
			map.put("stock_type_bond_type",stock_type_bond_type);
			map.put("contract_institution_type",contract_institution_type);
			map.put("perference_money_div",perference_money_div);
			map.put("input_unit",input_unit);
			map.put("new_buy_min_qty",new_buy_min_qty);
			map.put("add_buy_min_qty",add_buy_min_qty);
			map.put("sell_min_qty",sell_min_qty);
			map.put("swt_min_qty",swt_min_qty);
			map.put("new_buy_unit_qty",new_buy_unit_qty);
			map.put("add_buy_unit_qty",add_buy_unit_qty);
			map.put("sell_unit_qty",sell_unit_qty);
			map.put("swt_unit_qty",swt_unit_qty);
			map.put("new_buy_min_amt",new_buy_min_amt);
			map.put("add_buy_min_amt",add_buy_min_amt);
			map.put("sell_min_amt",sell_min_amt);
			map.put("swt_min_amt",swt_min_amt);
			map.put("new_buy_unit_amt",new_buy_unit_amt);
			map.put("add_buy_unit_amt",add_buy_unit_amt);
			map.put("sell_unit_amt",sell_unit_amt);
			map.put("swt_unit_amt",swt_unit_amt);
			map.put("buy_start_date",buy_start_date);
			map.put("buy_end_date",buy_end_date);
			map.put("sell_swt_start_date",sell_swt_start_date);
			map.put("sell_swt_end_date",sell_swt_end_date);
			map.put("buy_claim_start_date",buy_claim_start_date);
			map.put("buy_claim_end_date",buy_claim_end_date);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( online_updated_timestamp_is_set )
				map.put("online_updated_timestamp",online_updated_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("recruit_constant_value",recruit_constant_value);
			map.put("recruit_settlement_div",recruit_settlement_div);
			map.put("recruit_specity_div",recruit_specity_div);
			map.put("recruit_min_qty",recruit_min_qty);
			map.put("recruit_unit_qty",recruit_unit_qty);
			map.put("recruit_min_amt",recruit_min_amt);
			map.put("recruit_unit_amt",recruit_unit_amt);
			map.put("recruit_start_date",recruit_start_date);
			map.put("recruit_end_date",recruit_end_date);
			map.put("cal_price_div",cal_price_div);
			map.put("plowback_product_div",plowback_product_div);
			map.put("recruit_start_date_sonar",recruit_start_date_sonar);
			map.put("recruit_end_date_sonar",recruit_end_date_sonar);
			map.put("fixed_buy_possible_div",fixed_buy_possible_div);
			map.put("unit_type_product_div",unit_type_product_div);
			map.put("frgn_new_buy_min_amt",frgn_new_buy_min_amt);
			map.put("frgn_add_buy_min_amt",frgn_add_buy_min_amt);
			map.put("frgn_sell_min_amt",frgn_sell_min_amt);
			map.put("frgn_new_buy_unit_amt",frgn_new_buy_unit_amt);
			map.put("frgn_add_buy_unit_amt",frgn_add_buy_unit_amt);
			map.put("frgn_sell_unit_amt",frgn_sell_unit_amt);
			map.put("recruit_commission_div",recruit_commission_div);
			map.put("open_close_div",open_close_div);
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
   * <em>fund_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum getFundType()
  {
    return fund_type;
  }


  /** 
   * <em>fund_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundTypeIsSet() {
    return fund_type_is_set;
  }


  /** 
   * <em>fund_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundTypeIsModified() {
    return fund_type_is_modified;
  }


  /** 
   * <em>init_purchase_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getInitPurchaseMinQty()
  {
    return init_purchase_min_qty;
  }


  /** 
   * <em>init_purchase_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInitPurchaseMinQtyIsSet() {
    return init_purchase_min_qty_is_set;
  }


  /** 
   * <em>init_purchase_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInitPurchaseMinQtyIsModified() {
    return init_purchase_min_qty_is_modified;
  }


  /** 
   * <em>addtl_purchase_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAddtlPurchaseMinQty()
  {
    return addtl_purchase_min_qty;
  }


  /** 
   * <em>addtl_purchase_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddtlPurchaseMinQtyIsSet() {
    return addtl_purchase_min_qty_is_set;
  }


  /** 
   * <em>addtl_purchase_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddtlPurchaseMinQtyIsModified() {
    return addtl_purchase_min_qty_is_modified;
  }


  /** 
   * <em>mutualassoc_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMutualassocProductCode()
  {
    return mutualassoc_product_code;
  }


  /** 
   * <em>mutualassoc_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMutualassocProductCodeIsSet() {
    return mutualassoc_product_code_is_set;
  }


  /** 
   * <em>mutualassoc_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMutualassocProductCodeIsModified() {
    return mutualassoc_product_code_is_modified;
  }


  /** 
   * <em>system_handling_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSystemHandlingDiv()
  {
    return system_handling_div;
  }


  /** 
   * <em>system_handling_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSystemHandlingDivIsSet() {
    return system_handling_div_is_set;
  }


  /** 
   * <em>system_handling_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSystemHandlingDivIsModified() {
    return system_handling_div_is_modified;
  }


  /** 
   * <em>buy_limit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyLimitDiv()
  {
    return buy_limit_div;
  }


  /** 
   * <em>buy_limit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyLimitDivIsSet() {
    return buy_limit_div_is_set;
  }


  /** 
   * <em>buy_limit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyLimitDivIsModified() {
    return buy_limit_div_is_modified;
  }


  /** 
   * <em>standard_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStandardName()
  {
    return standard_name;
  }


  /** 
   * <em>standard_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandardNameIsSet() {
    return standard_name_is_set;
  }


  /** 
   * <em>standard_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandardNameIsModified() {
    return standard_name_is_modified;
  }


  /** 
   * <em>eng_product_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEngProductName()
  {
    return eng_product_name;
  }


  /** 
   * <em>eng_product_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEngProductNameIsSet() {
    return eng_product_name_is_set;
  }


  /** 
   * <em>eng_product_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEngProductNameIsModified() {
    return eng_product_name_is_modified;
  }


  /** 
   * <em>setting_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSettingDate()
  {
    return setting_date;
  }


  /** 
   * <em>setting_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettingDateIsSet() {
    return setting_date_is_set;
  }


  /** 
   * <em>setting_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettingDateIsModified() {
    return setting_date_is_modified;
  }


  /** 
   * <em>redemption_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getRedemptionDate()
  {
    return redemption_date;
  }


  /** 
   * <em>redemption_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRedemptionDateIsSet() {
    return redemption_date_is_set;
  }


  /** 
   * <em>redemption_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRedemptionDateIsModified() {
    return redemption_date_is_modified;
  }


  /** 
   * <em>sell_ban_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSellBanDate()
  {
    return sell_ban_date;
  }


  /** 
   * <em>sell_ban_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellBanDateIsSet() {
    return sell_ban_date_is_set;
  }


  /** 
   * <em>sell_ban_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellBanDateIsModified() {
    return sell_ban_date_is_modified;
  }


  /** 
   * <em>swt_possible_group_id</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSwtPossibleGroupId()
  {
    return ( swt_possible_group_id==null? ((int)0): swt_possible_group_id.intValue() );
  }


  /** 
   * <em>swt_possible_group_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSwtPossibleGroupIdIsNull()
  {
    return swt_possible_group_id == null;
  }


  /** 
   * <em>swt_possible_group_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtPossibleGroupIdIsSet() {
    return swt_possible_group_id_is_set;
  }


  /** 
   * <em>swt_possible_group_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtPossibleGroupIdIsModified() {
    return swt_possible_group_id_is_modified;
  }


  /** 
   * <em>category_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCategoryCode()
  {
    return category_code;
  }


  /** 
   * <em>category_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCategoryCodeIsSet() {
    return category_code_is_set;
  }


  /** 
   * <em>category_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCategoryCodeIsModified() {
    return category_code_is_modified;
  }


  /** 
   * <em>indication_ranking</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getIndicationRanking()
  {
    return ( indication_ranking==null? ((int)0): indication_ranking.intValue() );
  }


  /** 
   * <em>indication_ranking</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getIndicationRankingIsNull()
  {
    return indication_ranking == null;
  }


  /** 
   * <em>indication_ranking</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIndicationRankingIsSet() {
    return indication_ranking_is_set;
  }


  /** 
   * <em>indication_ranking</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIndicationRankingIsModified() {
    return indication_ranking_is_modified;
  }


  /** 
   * <em>buy_constant_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBuyConstantValue()
  {
    return ( buy_constant_value==null? ((double)0): buy_constant_value.doubleValue() );
  }


  /** 
   * <em>buy_constant_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyConstantValueIsNull()
  {
    return buy_constant_value == null;
  }


  /** 
   * <em>buy_constant_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyConstantValueIsSet() {
    return buy_constant_value_is_set;
  }


  /** 
   * <em>buy_constant_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyConstantValueIsModified() {
    return buy_constant_value_is_modified;
  }


  /** 
   * <em>sell_constant_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSellConstantValue()
  {
    return ( sell_constant_value==null? ((double)0): sell_constant_value.doubleValue() );
  }


  /** 
   * <em>sell_constant_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellConstantValueIsNull()
  {
    return sell_constant_value == null;
  }


  /** 
   * <em>sell_constant_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellConstantValueIsSet() {
    return sell_constant_value_is_set;
  }


  /** 
   * <em>sell_constant_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellConstantValueIsModified() {
    return sell_constant_value_is_modified;
  }


  /** 
   * <em>reference_constant_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getReferenceConstantValue()
  {
    return ( reference_constant_value==null? ((double)0): reference_constant_value.doubleValue() );
  }


  /** 
   * <em>reference_constant_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getReferenceConstantValueIsNull()
  {
    return reference_constant_value == null;
  }


  /** 
   * <em>reference_constant_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReferenceConstantValueIsSet() {
    return reference_constant_value_is_set;
  }


  /** 
   * <em>reference_constant_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReferenceConstantValueIsModified() {
    return reference_constant_value_is_modified;
  }


  /** 
   * <em>constant_value_app_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getConstantValueAppDate()
  {
    return constant_value_app_date;
  }


  /** 
   * <em>constant_value_app_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConstantValueAppDateIsSet() {
    return constant_value_app_date_is_set;
  }


  /** 
   * <em>constant_value_app_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConstantValueAppDateIsModified() {
    return constant_value_app_date_is_modified;
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
   * <em>constant_value_calc_unit</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getConstantValueCalcUnit()
  {
    return ( constant_value_calc_unit==null? ((int)0): constant_value_calc_unit.intValue() );
  }


  /** 
   * <em>constant_value_calc_unit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getConstantValueCalcUnitIsNull()
  {
    return constant_value_calc_unit == null;
  }


  /** 
   * <em>constant_value_calc_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConstantValueCalcUnitIsSet() {
    return constant_value_calc_unit_is_set;
  }


  /** 
   * <em>constant_value_calc_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConstantValueCalcUnitIsModified() {
    return constant_value_calc_unit_is_modified;
  }


  /** 
   * <em>buy_settlement_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuySettlementDiv()
  {
    return buy_settlement_div;
  }


  /** 
   * <em>buy_settlement_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuySettlementDivIsSet() {
    return buy_settlement_div_is_set;
  }


  /** 
   * <em>buy_settlement_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuySettlementDivIsModified() {
    return buy_settlement_div_is_modified;
  }


  /** 
   * <em>sell_settlement_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellSettlementDiv()
  {
    return sell_settlement_div;
  }


  /** 
   * <em>sell_settlement_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellSettlementDivIsSet() {
    return sell_settlement_div_is_set;
  }


  /** 
   * <em>sell_settlement_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellSettlementDivIsModified() {
    return sell_settlement_div_is_modified;
  }


  /** 
   * <em>delivery_method</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeliveryMethod()
  {
    return delivery_method;
  }


  /** 
   * <em>delivery_method</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryMethodIsSet() {
    return delivery_method_is_set;
  }


  /** 
   * <em>delivery_method</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryMethodIsModified() {
    return delivery_method_is_modified;
  }


  /** 
   * <em>buy_specity_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuySpecityDiv()
  {
    return buy_specity_div;
  }


  /** 
   * <em>buy_specity_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuySpecityDivIsSet() {
    return buy_specity_div_is_set;
  }


  /** 
   * <em>buy_specity_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuySpecityDivIsModified() {
    return buy_specity_div_is_modified;
  }


  /** 
   * <em>sell_specify_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellSpecifyDiv()
  {
    return sell_specify_div;
  }


  /** 
   * <em>sell_specify_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellSpecifyDivIsSet() {
    return sell_specify_div_is_set;
  }


  /** 
   * <em>sell_specify_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellSpecifyDivIsModified() {
    return sell_specify_div_is_modified;
  }


  /** 
   * <em>swt_specify_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwtSpecifyDiv()
  {
    return swt_specify_div;
  }


  /** 
   * <em>swt_specify_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtSpecifyDivIsSet() {
    return swt_specify_div_is_set;
  }


  /** 
   * <em>swt_specify_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtSpecifyDivIsModified() {
    return swt_specify_div_is_modified;
  }


  /** 
   * <em>stock_type_bond_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStockTypeBondType()
  {
    return stock_type_bond_type;
  }


  /** 
   * <em>stock_type_bond_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStockTypeBondTypeIsSet() {
    return stock_type_bond_type_is_set;
  }


  /** 
   * <em>stock_type_bond_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStockTypeBondTypeIsModified() {
    return stock_type_bond_type_is_modified;
  }


  /** 
   * <em>contract_institution_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getContractInstitutionType()
  {
    return contract_institution_type;
  }


  /** 
   * <em>contract_institution_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInstitutionTypeIsSet() {
    return contract_institution_type_is_set;
  }


  /** 
   * <em>contract_institution_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInstitutionTypeIsModified() {
    return contract_institution_type_is_modified;
  }


  /** 
   * <em>perference_money_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPerferenceMoneyDiv()
  {
    return perference_money_div;
  }


  /** 
   * <em>perference_money_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPerferenceMoneyDivIsSet() {
    return perference_money_div_is_set;
  }


  /** 
   * <em>perference_money_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPerferenceMoneyDivIsModified() {
    return perference_money_div_is_modified;
  }


  /** 
   * <em>input_unit</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInputUnit()
  {
    return input_unit;
  }


  /** 
   * <em>input_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInputUnitIsSet() {
    return input_unit_is_set;
  }


  /** 
   * <em>input_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInputUnitIsModified() {
    return input_unit_is_modified;
  }


  /** 
   * <em>new_buy_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getNewBuyMinQty()
  {
    return ( new_buy_min_qty==null? ((long)0): new_buy_min_qty.longValue() );
  }


  /** 
   * <em>new_buy_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNewBuyMinQtyIsNull()
  {
    return new_buy_min_qty == null;
  }


  /** 
   * <em>new_buy_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewBuyMinQtyIsSet() {
    return new_buy_min_qty_is_set;
  }


  /** 
   * <em>new_buy_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewBuyMinQtyIsModified() {
    return new_buy_min_qty_is_modified;
  }


  /** 
   * <em>add_buy_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAddBuyMinQty()
  {
    return ( add_buy_min_qty==null? ((long)0): add_buy_min_qty.longValue() );
  }


  /** 
   * <em>add_buy_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAddBuyMinQtyIsNull()
  {
    return add_buy_min_qty == null;
  }


  /** 
   * <em>add_buy_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddBuyMinQtyIsSet() {
    return add_buy_min_qty_is_set;
  }


  /** 
   * <em>add_buy_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddBuyMinQtyIsModified() {
    return add_buy_min_qty_is_modified;
  }


  /** 
   * <em>sell_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSellMinQty()
  {
    return ( sell_min_qty==null? ((long)0): sell_min_qty.longValue() );
  }


  /** 
   * <em>sell_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellMinQtyIsNull()
  {
    return sell_min_qty == null;
  }


  /** 
   * <em>sell_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellMinQtyIsSet() {
    return sell_min_qty_is_set;
  }


  /** 
   * <em>sell_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellMinQtyIsModified() {
    return sell_min_qty_is_modified;
  }


  /** 
   * <em>swt_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSwtMinQty()
  {
    return ( swt_min_qty==null? ((long)0): swt_min_qty.longValue() );
  }


  /** 
   * <em>swt_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSwtMinQtyIsNull()
  {
    return swt_min_qty == null;
  }


  /** 
   * <em>swt_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtMinQtyIsSet() {
    return swt_min_qty_is_set;
  }


  /** 
   * <em>swt_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtMinQtyIsModified() {
    return swt_min_qty_is_modified;
  }


  /** 
   * <em>new_buy_unit_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getNewBuyUnitQty()
  {
    return ( new_buy_unit_qty==null? ((long)0): new_buy_unit_qty.longValue() );
  }


  /** 
   * <em>new_buy_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNewBuyUnitQtyIsNull()
  {
    return new_buy_unit_qty == null;
  }


  /** 
   * <em>new_buy_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewBuyUnitQtyIsSet() {
    return new_buy_unit_qty_is_set;
  }


  /** 
   * <em>new_buy_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewBuyUnitQtyIsModified() {
    return new_buy_unit_qty_is_modified;
  }


  /** 
   * <em>add_buy_unit_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAddBuyUnitQty()
  {
    return ( add_buy_unit_qty==null? ((long)0): add_buy_unit_qty.longValue() );
  }


  /** 
   * <em>add_buy_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAddBuyUnitQtyIsNull()
  {
    return add_buy_unit_qty == null;
  }


  /** 
   * <em>add_buy_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddBuyUnitQtyIsSet() {
    return add_buy_unit_qty_is_set;
  }


  /** 
   * <em>add_buy_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddBuyUnitQtyIsModified() {
    return add_buy_unit_qty_is_modified;
  }


  /** 
   * <em>sell_unit_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSellUnitQty()
  {
    return ( sell_unit_qty==null? ((long)0): sell_unit_qty.longValue() );
  }


  /** 
   * <em>sell_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellUnitQtyIsNull()
  {
    return sell_unit_qty == null;
  }


  /** 
   * <em>sell_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellUnitQtyIsSet() {
    return sell_unit_qty_is_set;
  }


  /** 
   * <em>sell_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellUnitQtyIsModified() {
    return sell_unit_qty_is_modified;
  }


  /** 
   * <em>swt_unit_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSwtUnitQty()
  {
    return ( swt_unit_qty==null? ((long)0): swt_unit_qty.longValue() );
  }


  /** 
   * <em>swt_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSwtUnitQtyIsNull()
  {
    return swt_unit_qty == null;
  }


  /** 
   * <em>swt_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtUnitQtyIsSet() {
    return swt_unit_qty_is_set;
  }


  /** 
   * <em>swt_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtUnitQtyIsModified() {
    return swt_unit_qty_is_modified;
  }


  /** 
   * <em>new_buy_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getNewBuyMinAmt()
  {
    return ( new_buy_min_amt==null? ((long)0): new_buy_min_amt.longValue() );
  }


  /** 
   * <em>new_buy_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNewBuyMinAmtIsNull()
  {
    return new_buy_min_amt == null;
  }


  /** 
   * <em>new_buy_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewBuyMinAmtIsSet() {
    return new_buy_min_amt_is_set;
  }


  /** 
   * <em>new_buy_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewBuyMinAmtIsModified() {
    return new_buy_min_amt_is_modified;
  }


  /** 
   * <em>add_buy_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAddBuyMinAmt()
  {
    return ( add_buy_min_amt==null? ((long)0): add_buy_min_amt.longValue() );
  }


  /** 
   * <em>add_buy_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAddBuyMinAmtIsNull()
  {
    return add_buy_min_amt == null;
  }


  /** 
   * <em>add_buy_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddBuyMinAmtIsSet() {
    return add_buy_min_amt_is_set;
  }


  /** 
   * <em>add_buy_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddBuyMinAmtIsModified() {
    return add_buy_min_amt_is_modified;
  }


  /** 
   * <em>sell_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSellMinAmt()
  {
    return ( sell_min_amt==null? ((long)0): sell_min_amt.longValue() );
  }


  /** 
   * <em>sell_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellMinAmtIsNull()
  {
    return sell_min_amt == null;
  }


  /** 
   * <em>sell_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellMinAmtIsSet() {
    return sell_min_amt_is_set;
  }


  /** 
   * <em>sell_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellMinAmtIsModified() {
    return sell_min_amt_is_modified;
  }


  /** 
   * <em>swt_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSwtMinAmt()
  {
    return ( swt_min_amt==null? ((long)0): swt_min_amt.longValue() );
  }


  /** 
   * <em>swt_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSwtMinAmtIsNull()
  {
    return swt_min_amt == null;
  }


  /** 
   * <em>swt_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtMinAmtIsSet() {
    return swt_min_amt_is_set;
  }


  /** 
   * <em>swt_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtMinAmtIsModified() {
    return swt_min_amt_is_modified;
  }


  /** 
   * <em>new_buy_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getNewBuyUnitAmt()
  {
    return ( new_buy_unit_amt==null? ((long)0): new_buy_unit_amt.longValue() );
  }


  /** 
   * <em>new_buy_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNewBuyUnitAmtIsNull()
  {
    return new_buy_unit_amt == null;
  }


  /** 
   * <em>new_buy_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewBuyUnitAmtIsSet() {
    return new_buy_unit_amt_is_set;
  }


  /** 
   * <em>new_buy_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewBuyUnitAmtIsModified() {
    return new_buy_unit_amt_is_modified;
  }


  /** 
   * <em>add_buy_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAddBuyUnitAmt()
  {
    return ( add_buy_unit_amt==null? ((long)0): add_buy_unit_amt.longValue() );
  }


  /** 
   * <em>add_buy_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAddBuyUnitAmtIsNull()
  {
    return add_buy_unit_amt == null;
  }


  /** 
   * <em>add_buy_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddBuyUnitAmtIsSet() {
    return add_buy_unit_amt_is_set;
  }


  /** 
   * <em>add_buy_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddBuyUnitAmtIsModified() {
    return add_buy_unit_amt_is_modified;
  }


  /** 
   * <em>sell_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSellUnitAmt()
  {
    return ( sell_unit_amt==null? ((long)0): sell_unit_amt.longValue() );
  }


  /** 
   * <em>sell_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellUnitAmtIsNull()
  {
    return sell_unit_amt == null;
  }


  /** 
   * <em>sell_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellUnitAmtIsSet() {
    return sell_unit_amt_is_set;
  }


  /** 
   * <em>sell_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellUnitAmtIsModified() {
    return sell_unit_amt_is_modified;
  }


  /** 
   * <em>swt_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSwtUnitAmt()
  {
    return ( swt_unit_amt==null? ((long)0): swt_unit_amt.longValue() );
  }


  /** 
   * <em>swt_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSwtUnitAmtIsNull()
  {
    return swt_unit_amt == null;
  }


  /** 
   * <em>swt_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtUnitAmtIsSet() {
    return swt_unit_amt_is_set;
  }


  /** 
   * <em>swt_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtUnitAmtIsModified() {
    return swt_unit_amt_is_modified;
  }


  /** 
   * <em>buy_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getBuyStartDate()
  {
    return buy_start_date;
  }


  /** 
   * <em>buy_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyStartDateIsSet() {
    return buy_start_date_is_set;
  }


  /** 
   * <em>buy_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyStartDateIsModified() {
    return buy_start_date_is_modified;
  }


  /** 
   * <em>buy_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getBuyEndDate()
  {
    return buy_end_date;
  }


  /** 
   * <em>buy_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyEndDateIsSet() {
    return buy_end_date_is_set;
  }


  /** 
   * <em>buy_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyEndDateIsModified() {
    return buy_end_date_is_modified;
  }


  /** 
   * <em>sell_swt_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSellSwtStartDate()
  {
    return sell_swt_start_date;
  }


  /** 
   * <em>sell_swt_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellSwtStartDateIsSet() {
    return sell_swt_start_date_is_set;
  }


  /** 
   * <em>sell_swt_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellSwtStartDateIsModified() {
    return sell_swt_start_date_is_modified;
  }


  /** 
   * <em>sell_swt_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSellSwtEndDate()
  {
    return sell_swt_end_date;
  }


  /** 
   * <em>sell_swt_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellSwtEndDateIsSet() {
    return sell_swt_end_date_is_set;
  }


  /** 
   * <em>sell_swt_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellSwtEndDateIsModified() {
    return sell_swt_end_date_is_modified;
  }


  /** 
   * <em>buy_claim_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getBuyClaimStartDate()
  {
    return buy_claim_start_date;
  }


  /** 
   * <em>buy_claim_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyClaimStartDateIsSet() {
    return buy_claim_start_date_is_set;
  }


  /** 
   * <em>buy_claim_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyClaimStartDateIsModified() {
    return buy_claim_start_date_is_modified;
  }


  /** 
   * <em>buy_claim_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getBuyClaimEndDate()
  {
    return buy_claim_end_date;
  }


  /** 
   * <em>buy_claim_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyClaimEndDateIsSet() {
    return buy_claim_end_date_is_set;
  }


  /** 
   * <em>buy_claim_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyClaimEndDateIsModified() {
    return buy_claim_end_date_is_modified;
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
   * <em>online_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOnlineUpdatedTimestamp()
  {
    return online_updated_timestamp;
  }


  /** 
   * <em>online_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlineUpdatedTimestampIsSet() {
    return online_updated_timestamp_is_set;
  }


  /** 
   * <em>online_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlineUpdatedTimestampIsModified() {
    return online_updated_timestamp_is_modified;
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
   * <em>recruit_constant_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRecruitConstantValue()
  {
    return ( recruit_constant_value==null? ((double)0): recruit_constant_value.doubleValue() );
  }


  /** 
   * <em>recruit_constant_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRecruitConstantValueIsNull()
  {
    return recruit_constant_value == null;
  }


  /** 
   * <em>recruit_constant_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitConstantValueIsSet() {
    return recruit_constant_value_is_set;
  }


  /** 
   * <em>recruit_constant_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitConstantValueIsModified() {
    return recruit_constant_value_is_modified;
  }


  /** 
   * <em>recruit_settlement_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRecruitSettlementDiv()
  {
    return recruit_settlement_div;
  }


  /** 
   * <em>recruit_settlement_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitSettlementDivIsSet() {
    return recruit_settlement_div_is_set;
  }


  /** 
   * <em>recruit_settlement_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitSettlementDivIsModified() {
    return recruit_settlement_div_is_modified;
  }


  /** 
   * <em>recruit_specity_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRecruitSpecityDiv()
  {
    return recruit_specity_div;
  }


  /** 
   * <em>recruit_specity_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitSpecityDivIsSet() {
    return recruit_specity_div_is_set;
  }


  /** 
   * <em>recruit_specity_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitSpecityDivIsModified() {
    return recruit_specity_div_is_modified;
  }


  /** 
   * <em>recruit_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getRecruitMinQty()
  {
    return ( recruit_min_qty==null? ((long)0): recruit_min_qty.longValue() );
  }


  /** 
   * <em>recruit_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRecruitMinQtyIsNull()
  {
    return recruit_min_qty == null;
  }


  /** 
   * <em>recruit_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitMinQtyIsSet() {
    return recruit_min_qty_is_set;
  }


  /** 
   * <em>recruit_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitMinQtyIsModified() {
    return recruit_min_qty_is_modified;
  }


  /** 
   * <em>recruit_unit_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getRecruitUnitQty()
  {
    return ( recruit_unit_qty==null? ((long)0): recruit_unit_qty.longValue() );
  }


  /** 
   * <em>recruit_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRecruitUnitQtyIsNull()
  {
    return recruit_unit_qty == null;
  }


  /** 
   * <em>recruit_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitUnitQtyIsSet() {
    return recruit_unit_qty_is_set;
  }


  /** 
   * <em>recruit_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitUnitQtyIsModified() {
    return recruit_unit_qty_is_modified;
  }


  /** 
   * <em>recruit_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getRecruitMinAmt()
  {
    return ( recruit_min_amt==null? ((long)0): recruit_min_amt.longValue() );
  }


  /** 
   * <em>recruit_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRecruitMinAmtIsNull()
  {
    return recruit_min_amt == null;
  }


  /** 
   * <em>recruit_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitMinAmtIsSet() {
    return recruit_min_amt_is_set;
  }


  /** 
   * <em>recruit_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitMinAmtIsModified() {
    return recruit_min_amt_is_modified;
  }


  /** 
   * <em>recruit_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getRecruitUnitAmt()
  {
    return ( recruit_unit_amt==null? ((long)0): recruit_unit_amt.longValue() );
  }


  /** 
   * <em>recruit_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRecruitUnitAmtIsNull()
  {
    return recruit_unit_amt == null;
  }


  /** 
   * <em>recruit_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitUnitAmtIsSet() {
    return recruit_unit_amt_is_set;
  }


  /** 
   * <em>recruit_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitUnitAmtIsModified() {
    return recruit_unit_amt_is_modified;
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
   * <em>cal_price_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCalPriceDiv()
  {
    return cal_price_div;
  }


  /** 
   * <em>cal_price_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalPriceDivIsSet() {
    return cal_price_div_is_set;
  }


  /** 
   * <em>cal_price_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalPriceDivIsModified() {
    return cal_price_div_is_modified;
  }


  /** 
   * <em>plowback_product_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPlowbackProductDiv()
  {
    return plowback_product_div;
  }


  /** 
   * <em>plowback_product_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPlowbackProductDivIsSet() {
    return plowback_product_div_is_set;
  }


  /** 
   * <em>plowback_product_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPlowbackProductDivIsModified() {
    return plowback_product_div_is_modified;
  }


  /** 
   * <em>recruit_start_date_sonar</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getRecruitStartDateSonar()
  {
    return recruit_start_date_sonar;
  }


  /** 
   * <em>recruit_start_date_sonar</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitStartDateSonarIsSet() {
    return recruit_start_date_sonar_is_set;
  }


  /** 
   * <em>recruit_start_date_sonar</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitStartDateSonarIsModified() {
    return recruit_start_date_sonar_is_modified;
  }


  /** 
   * <em>recruit_end_date_sonar</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getRecruitEndDateSonar()
  {
    return recruit_end_date_sonar;
  }


  /** 
   * <em>recruit_end_date_sonar</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitEndDateSonarIsSet() {
    return recruit_end_date_sonar_is_set;
  }


  /** 
   * <em>recruit_end_date_sonar</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitEndDateSonarIsModified() {
    return recruit_end_date_sonar_is_modified;
  }


  /** 
   * <em>fixed_buy_possible_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFixedBuyPossibleDiv()
  {
    return fixed_buy_possible_div;
  }


  /** 
   * <em>fixed_buy_possible_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFixedBuyPossibleDivIsSet() {
    return fixed_buy_possible_div_is_set;
  }


  /** 
   * <em>fixed_buy_possible_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFixedBuyPossibleDivIsModified() {
    return fixed_buy_possible_div_is_modified;
  }


  /** 
   * <em>unit_type_product_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getUnitTypeProductDiv()
  {
    return unit_type_product_div;
  }


  /** 
   * <em>unit_type_product_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitTypeProductDivIsSet() {
    return unit_type_product_div_is_set;
  }


  /** 
   * <em>unit_type_product_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitTypeProductDivIsModified() {
    return unit_type_product_div_is_modified;
  }


  /** 
   * <em>frgn_new_buy_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFrgnNewBuyMinAmt()
  {
    return ( frgn_new_buy_min_amt==null? ((long)0): frgn_new_buy_min_amt.longValue() );
  }


  /** 
   * <em>frgn_new_buy_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFrgnNewBuyMinAmtIsNull()
  {
    return frgn_new_buy_min_amt == null;
  }


  /** 
   * <em>frgn_new_buy_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnNewBuyMinAmtIsSet() {
    return frgn_new_buy_min_amt_is_set;
  }


  /** 
   * <em>frgn_new_buy_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnNewBuyMinAmtIsModified() {
    return frgn_new_buy_min_amt_is_modified;
  }


  /** 
   * <em>frgn_add_buy_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFrgnAddBuyMinAmt()
  {
    return ( frgn_add_buy_min_amt==null? ((long)0): frgn_add_buy_min_amt.longValue() );
  }


  /** 
   * <em>frgn_add_buy_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFrgnAddBuyMinAmtIsNull()
  {
    return frgn_add_buy_min_amt == null;
  }


  /** 
   * <em>frgn_add_buy_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnAddBuyMinAmtIsSet() {
    return frgn_add_buy_min_amt_is_set;
  }


  /** 
   * <em>frgn_add_buy_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnAddBuyMinAmtIsModified() {
    return frgn_add_buy_min_amt_is_modified;
  }


  /** 
   * <em>frgn_sell_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFrgnSellMinAmt()
  {
    return ( frgn_sell_min_amt==null? ((long)0): frgn_sell_min_amt.longValue() );
  }


  /** 
   * <em>frgn_sell_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFrgnSellMinAmtIsNull()
  {
    return frgn_sell_min_amt == null;
  }


  /** 
   * <em>frgn_sell_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnSellMinAmtIsSet() {
    return frgn_sell_min_amt_is_set;
  }


  /** 
   * <em>frgn_sell_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnSellMinAmtIsModified() {
    return frgn_sell_min_amt_is_modified;
  }


  /** 
   * <em>frgn_new_buy_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFrgnNewBuyUnitAmt()
  {
    return ( frgn_new_buy_unit_amt==null? ((long)0): frgn_new_buy_unit_amt.longValue() );
  }


  /** 
   * <em>frgn_new_buy_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFrgnNewBuyUnitAmtIsNull()
  {
    return frgn_new_buy_unit_amt == null;
  }


  /** 
   * <em>frgn_new_buy_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnNewBuyUnitAmtIsSet() {
    return frgn_new_buy_unit_amt_is_set;
  }


  /** 
   * <em>frgn_new_buy_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnNewBuyUnitAmtIsModified() {
    return frgn_new_buy_unit_amt_is_modified;
  }


  /** 
   * <em>frgn_add_buy_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFrgnAddBuyUnitAmt()
  {
    return ( frgn_add_buy_unit_amt==null? ((long)0): frgn_add_buy_unit_amt.longValue() );
  }


  /** 
   * <em>frgn_add_buy_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFrgnAddBuyUnitAmtIsNull()
  {
    return frgn_add_buy_unit_amt == null;
  }


  /** 
   * <em>frgn_add_buy_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnAddBuyUnitAmtIsSet() {
    return frgn_add_buy_unit_amt_is_set;
  }


  /** 
   * <em>frgn_add_buy_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnAddBuyUnitAmtIsModified() {
    return frgn_add_buy_unit_amt_is_modified;
  }


  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFrgnSellUnitAmt()
  {
    return ( frgn_sell_unit_amt==null? ((long)0): frgn_sell_unit_amt.longValue() );
  }


  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFrgnSellUnitAmtIsNull()
  {
    return frgn_sell_unit_amt == null;
  }


  /** 
   * <em>frgn_sell_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnSellUnitAmtIsSet() {
    return frgn_sell_unit_amt_is_set;
  }


  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnSellUnitAmtIsModified() {
    return frgn_sell_unit_amt_is_modified;
  }


  /** 
   * <em>recruit_commission_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRecruitCommissionDiv()
  {
    return recruit_commission_div;
  }


  /** 
   * <em>recruit_commission_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitCommissionDivIsSet() {
    return recruit_commission_div_is_set;
  }


  /** 
   * <em>recruit_commission_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitCommissionDivIsModified() {
    return recruit_commission_div_is_modified;
  }


  /** 
   * <em>open_close_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOpenCloseDiv()
  {
    return open_close_div;
  }


  /** 
   * <em>open_close_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenCloseDivIsSet() {
    return open_close_div_is_set;
  }


  /** 
   * <em>open_close_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenCloseDivIsModified() {
    return open_close_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new MutualFundProductPK(product_id);
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
   * <em>fund_type</em>カラムの値を設定します。 
   *
   * @@param p_fundType <em>fund_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum値 
   */
  public final void setFundType( com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum p_fundType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_type = p_fundType;
    fund_type_is_set = true;
    fund_type_is_modified = true;
  }


  /** 
   * <em>init_purchase_min_qty</em>カラムの値を設定します。 
   *
   * @@param p_initPurchaseMinQty <em>init_purchase_min_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setInitPurchaseMinQty( long p_initPurchaseMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    init_purchase_min_qty = p_initPurchaseMinQty;
    init_purchase_min_qty_is_set = true;
    init_purchase_min_qty_is_modified = true;
  }


  /** 
   * <em>addtl_purchase_min_qty</em>カラムの値を設定します。 
   *
   * @@param p_addtlPurchaseMinQty <em>addtl_purchase_min_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setAddtlPurchaseMinQty( long p_addtlPurchaseMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    addtl_purchase_min_qty = p_addtlPurchaseMinQty;
    addtl_purchase_min_qty_is_set = true;
    addtl_purchase_min_qty_is_modified = true;
  }


  /** 
   * <em>mutualassoc_product_code</em>カラムの値を設定します。 
   *
   * @@param p_mutualassocProductCode <em>mutualassoc_product_code</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setMutualassocProductCode( String p_mutualassocProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mutualassoc_product_code = p_mutualassocProductCode;
    mutualassoc_product_code_is_set = true;
    mutualassoc_product_code_is_modified = true;
  }


  /** 
   * <em>system_handling_div</em>カラムの値を設定します。 
   *
   * @@param p_systemHandlingDiv <em>system_handling_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSystemHandlingDiv( String p_systemHandlingDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    system_handling_div = p_systemHandlingDiv;
    system_handling_div_is_set = true;
    system_handling_div_is_modified = true;
  }


  /** 
   * <em>buy_limit_div</em>カラムの値を設定します。 
   *
   * @@param p_buyLimitDiv <em>buy_limit_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBuyLimitDiv( String p_buyLimitDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_limit_div = p_buyLimitDiv;
    buy_limit_div_is_set = true;
    buy_limit_div_is_modified = true;
  }


  /** 
   * <em>standard_name</em>カラムの値を設定します。 
   *
   * @@param p_standardName <em>standard_name</em>カラムの値をあらわす64桁以下のString値 
   */
  public final void setStandardName( String p_standardName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    standard_name = p_standardName;
    standard_name_is_set = true;
    standard_name_is_modified = true;
  }


  /** 
   * <em>eng_product_name</em>カラムの値を設定します。 
   *
   * @@param p_engProductName <em>eng_product_name</em>カラムの値をあらわす64桁以下のString値 
   */
  public final void setEngProductName( String p_engProductName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    eng_product_name = p_engProductName;
    eng_product_name_is_set = true;
    eng_product_name_is_modified = true;
  }


  /** 
   * <em>setting_date</em>カラムの値を設定します。 
   *
   * @@param p_settingDate <em>setting_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSettingDate( java.sql.Timestamp p_settingDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    setting_date = p_settingDate;
    setting_date_is_set = true;
    setting_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>setting_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSettingDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    setting_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    setting_date_is_set = true;
    setting_date_is_modified = true;
  }


  /** 
   * <em>redemption_date</em>カラムの値を設定します。 
   *
   * @@param p_redemptionDate <em>redemption_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setRedemptionDate( java.sql.Timestamp p_redemptionDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    redemption_date = p_redemptionDate;
    redemption_date_is_set = true;
    redemption_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>redemption_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setRedemptionDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    redemption_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    redemption_date_is_set = true;
    redemption_date_is_modified = true;
  }


  /** 
   * <em>sell_ban_date</em>カラムの値を設定します。 
   *
   * @@param p_sellBanDate <em>sell_ban_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSellBanDate( java.sql.Timestamp p_sellBanDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_ban_date = p_sellBanDate;
    sell_ban_date_is_set = true;
    sell_ban_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>sell_ban_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSellBanDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_ban_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    sell_ban_date_is_set = true;
    sell_ban_date_is_modified = true;
  }


  /** 
   * <em>swt_possible_group_id</em>カラムの値を設定します。 
   *
   * @@param p_swtPossibleGroupId <em>swt_possible_group_id</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setSwtPossibleGroupId( int p_swtPossibleGroupId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_possible_group_id = new Integer(p_swtPossibleGroupId);
    swt_possible_group_id_is_set = true;
    swt_possible_group_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>swt_possible_group_id</em>カラムに値を設定します。 
   */
  public final void setSwtPossibleGroupId( Integer p_swtPossibleGroupId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    swt_possible_group_id = p_swtPossibleGroupId;
    swt_possible_group_id_is_set = true;
    swt_possible_group_id_is_modified = true;
  }


  /** 
   * <em>category_code</em>カラムの値を設定します。 
   *
   * @@param p_categoryCode <em>category_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCategoryCode( String p_categoryCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    category_code = p_categoryCode;
    category_code_is_set = true;
    category_code_is_modified = true;
  }


  /** 
   * <em>indication_ranking</em>カラムの値を設定します。 
   *
   * @@param p_indicationRanking <em>indication_ranking</em>カラムの値をあらわす3桁以下のint値 
   */
  public final void setIndicationRanking( int p_indicationRanking )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    indication_ranking = new Integer(p_indicationRanking);
    indication_ranking_is_set = true;
    indication_ranking_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>indication_ranking</em>カラムに値を設定します。 
   */
  public final void setIndicationRanking( Integer p_indicationRanking )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    indication_ranking = p_indicationRanking;
    indication_ranking_is_set = true;
    indication_ranking_is_modified = true;
  }


  /** 
   * <em>buy_constant_value</em>カラムの値を設定します。 
   *
   * @@param p_buyConstantValue <em>buy_constant_value</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBuyConstantValue( double p_buyConstantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_constant_value = new Double(p_buyConstantValue);
    buy_constant_value_is_set = true;
    buy_constant_value_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_constant_value</em>カラムに値を設定します。 
   */
  public final void setBuyConstantValue( Double p_buyConstantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_constant_value = p_buyConstantValue;
    buy_constant_value_is_set = true;
    buy_constant_value_is_modified = true;
  }


  /** 
   * <em>sell_constant_value</em>カラムの値を設定します。 
   *
   * @@param p_sellConstantValue <em>sell_constant_value</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSellConstantValue( double p_sellConstantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_constant_value = new Double(p_sellConstantValue);
    sell_constant_value_is_set = true;
    sell_constant_value_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_constant_value</em>カラムに値を設定します。 
   */
  public final void setSellConstantValue( Double p_sellConstantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_constant_value = p_sellConstantValue;
    sell_constant_value_is_set = true;
    sell_constant_value_is_modified = true;
  }


  /** 
   * <em>reference_constant_value</em>カラムの値を設定します。 
   *
   * @@param p_referenceConstantValue <em>reference_constant_value</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setReferenceConstantValue( double p_referenceConstantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reference_constant_value = new Double(p_referenceConstantValue);
    reference_constant_value_is_set = true;
    reference_constant_value_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>reference_constant_value</em>カラムに値を設定します。 
   */
  public final void setReferenceConstantValue( Double p_referenceConstantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    reference_constant_value = p_referenceConstantValue;
    reference_constant_value_is_set = true;
    reference_constant_value_is_modified = true;
  }


  /** 
   * <em>constant_value_app_date</em>カラムの値を設定します。 
   *
   * @@param p_constantValueAppDate <em>constant_value_app_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setConstantValueAppDate( java.sql.Timestamp p_constantValueAppDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    constant_value_app_date = p_constantValueAppDate;
    constant_value_app_date_is_set = true;
    constant_value_app_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>constant_value_app_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setConstantValueAppDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    constant_value_app_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    constant_value_app_date_is_set = true;
    constant_value_app_date_is_modified = true;
  }


  /** 
   * <em>currency_code</em>カラムの値を設定します。 
   *
   * @@param p_currencyCode <em>currency_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>constant_value_calc_unit</em>カラムの値を設定します。 
   *
   * @@param p_constantValueCalcUnit <em>constant_value_calc_unit</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setConstantValueCalcUnit( int p_constantValueCalcUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    constant_value_calc_unit = new Integer(p_constantValueCalcUnit);
    constant_value_calc_unit_is_set = true;
    constant_value_calc_unit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>constant_value_calc_unit</em>カラムに値を設定します。 
   */
  public final void setConstantValueCalcUnit( Integer p_constantValueCalcUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    constant_value_calc_unit = p_constantValueCalcUnit;
    constant_value_calc_unit_is_set = true;
    constant_value_calc_unit_is_modified = true;
  }


  /** 
   * <em>buy_settlement_div</em>カラムの値を設定します。 
   *
   * @@param p_buySettlementDiv <em>buy_settlement_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBuySettlementDiv( String p_buySettlementDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_settlement_div = p_buySettlementDiv;
    buy_settlement_div_is_set = true;
    buy_settlement_div_is_modified = true;
  }


  /** 
   * <em>sell_settlement_div</em>カラムの値を設定します。 
   *
   * @@param p_sellSettlementDiv <em>sell_settlement_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSellSettlementDiv( String p_sellSettlementDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_settlement_div = p_sellSettlementDiv;
    sell_settlement_div_is_set = true;
    sell_settlement_div_is_modified = true;
  }


  /** 
   * <em>delivery_method</em>カラムの値を設定します。 
   *
   * @@param p_deliveryMethod <em>delivery_method</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDeliveryMethod( String p_deliveryMethod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_method = p_deliveryMethod;
    delivery_method_is_set = true;
    delivery_method_is_modified = true;
  }


  /** 
   * <em>buy_specity_div</em>カラムの値を設定します。 
   *
   * @@param p_buySpecityDiv <em>buy_specity_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBuySpecityDiv( String p_buySpecityDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_specity_div = p_buySpecityDiv;
    buy_specity_div_is_set = true;
    buy_specity_div_is_modified = true;
  }


  /** 
   * <em>sell_specify_div</em>カラムの値を設定します。 
   *
   * @@param p_sellSpecifyDiv <em>sell_specify_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSellSpecifyDiv( String p_sellSpecifyDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_specify_div = p_sellSpecifyDiv;
    sell_specify_div_is_set = true;
    sell_specify_div_is_modified = true;
  }


  /** 
   * <em>swt_specify_div</em>カラムの値を設定します。 
   *
   * @@param p_swtSpecifyDiv <em>swt_specify_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSwtSpecifyDiv( String p_swtSpecifyDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_specify_div = p_swtSpecifyDiv;
    swt_specify_div_is_set = true;
    swt_specify_div_is_modified = true;
  }


  /** 
   * <em>stock_type_bond_type</em>カラムの値を設定します。 
   *
   * @@param p_stockTypeBondType <em>stock_type_bond_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStockTypeBondType( String p_stockTypeBondType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stock_type_bond_type = p_stockTypeBondType;
    stock_type_bond_type_is_set = true;
    stock_type_bond_type_is_modified = true;
  }


  /** 
   * <em>contract_institution_type</em>カラムの値を設定します。 
   *
   * @@param p_contractInstitutionType <em>contract_institution_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setContractInstitutionType( String p_contractInstitutionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_institution_type = p_contractInstitutionType;
    contract_institution_type_is_set = true;
    contract_institution_type_is_modified = true;
  }


  /** 
   * <em>perference_money_div</em>カラムの値を設定します。 
   *
   * @@param p_perferenceMoneyDiv <em>perference_money_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPerferenceMoneyDiv( String p_perferenceMoneyDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    perference_money_div = p_perferenceMoneyDiv;
    perference_money_div_is_set = true;
    perference_money_div_is_modified = true;
  }


  /** 
   * <em>input_unit</em>カラムの値を設定します。 
   *
   * @@param p_inputUnit <em>input_unit</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInputUnit( String p_inputUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    input_unit = p_inputUnit;
    input_unit_is_set = true;
    input_unit_is_modified = true;
  }


  /** 
   * <em>new_buy_min_qty</em>カラムの値を設定します。 
   *
   * @@param p_newBuyMinQty <em>new_buy_min_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setNewBuyMinQty( long p_newBuyMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_buy_min_qty = new Long(p_newBuyMinQty);
    new_buy_min_qty_is_set = true;
    new_buy_min_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>new_buy_min_qty</em>カラムに値を設定します。 
   */
  public final void setNewBuyMinQty( Long p_newBuyMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    new_buy_min_qty = p_newBuyMinQty;
    new_buy_min_qty_is_set = true;
    new_buy_min_qty_is_modified = true;
  }


  /** 
   * <em>add_buy_min_qty</em>カラムの値を設定します。 
   *
   * @@param p_addBuyMinQty <em>add_buy_min_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setAddBuyMinQty( long p_addBuyMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    add_buy_min_qty = new Long(p_addBuyMinQty);
    add_buy_min_qty_is_set = true;
    add_buy_min_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>add_buy_min_qty</em>カラムに値を設定します。 
   */
  public final void setAddBuyMinQty( Long p_addBuyMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    add_buy_min_qty = p_addBuyMinQty;
    add_buy_min_qty_is_set = true;
    add_buy_min_qty_is_modified = true;
  }


  /** 
   * <em>sell_min_qty</em>カラムの値を設定します。 
   *
   * @@param p_sellMinQty <em>sell_min_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setSellMinQty( long p_sellMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_min_qty = new Long(p_sellMinQty);
    sell_min_qty_is_set = true;
    sell_min_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_min_qty</em>カラムに値を設定します。 
   */
  public final void setSellMinQty( Long p_sellMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_min_qty = p_sellMinQty;
    sell_min_qty_is_set = true;
    sell_min_qty_is_modified = true;
  }


  /** 
   * <em>swt_min_qty</em>カラムの値を設定します。 
   *
   * @@param p_swtMinQty <em>swt_min_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setSwtMinQty( long p_swtMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_min_qty = new Long(p_swtMinQty);
    swt_min_qty_is_set = true;
    swt_min_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>swt_min_qty</em>カラムに値を設定します。 
   */
  public final void setSwtMinQty( Long p_swtMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    swt_min_qty = p_swtMinQty;
    swt_min_qty_is_set = true;
    swt_min_qty_is_modified = true;
  }


  /** 
   * <em>new_buy_unit_qty</em>カラムの値を設定します。 
   *
   * @@param p_newBuyUnitQty <em>new_buy_unit_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setNewBuyUnitQty( long p_newBuyUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_buy_unit_qty = new Long(p_newBuyUnitQty);
    new_buy_unit_qty_is_set = true;
    new_buy_unit_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>new_buy_unit_qty</em>カラムに値を設定します。 
   */
  public final void setNewBuyUnitQty( Long p_newBuyUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    new_buy_unit_qty = p_newBuyUnitQty;
    new_buy_unit_qty_is_set = true;
    new_buy_unit_qty_is_modified = true;
  }


  /** 
   * <em>add_buy_unit_qty</em>カラムの値を設定します。 
   *
   * @@param p_addBuyUnitQty <em>add_buy_unit_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setAddBuyUnitQty( long p_addBuyUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    add_buy_unit_qty = new Long(p_addBuyUnitQty);
    add_buy_unit_qty_is_set = true;
    add_buy_unit_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>add_buy_unit_qty</em>カラムに値を設定します。 
   */
  public final void setAddBuyUnitQty( Long p_addBuyUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    add_buy_unit_qty = p_addBuyUnitQty;
    add_buy_unit_qty_is_set = true;
    add_buy_unit_qty_is_modified = true;
  }


  /** 
   * <em>sell_unit_qty</em>カラムの値を設定します。 
   *
   * @@param p_sellUnitQty <em>sell_unit_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setSellUnitQty( long p_sellUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_unit_qty = new Long(p_sellUnitQty);
    sell_unit_qty_is_set = true;
    sell_unit_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_unit_qty</em>カラムに値を設定します。 
   */
  public final void setSellUnitQty( Long p_sellUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_unit_qty = p_sellUnitQty;
    sell_unit_qty_is_set = true;
    sell_unit_qty_is_modified = true;
  }


  /** 
   * <em>swt_unit_qty</em>カラムの値を設定します。 
   *
   * @@param p_swtUnitQty <em>swt_unit_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setSwtUnitQty( long p_swtUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_unit_qty = new Long(p_swtUnitQty);
    swt_unit_qty_is_set = true;
    swt_unit_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>swt_unit_qty</em>カラムに値を設定します。 
   */
  public final void setSwtUnitQty( Long p_swtUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    swt_unit_qty = p_swtUnitQty;
    swt_unit_qty_is_set = true;
    swt_unit_qty_is_modified = true;
  }


  /** 
   * <em>new_buy_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_newBuyMinAmt <em>new_buy_min_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setNewBuyMinAmt( long p_newBuyMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_buy_min_amt = new Long(p_newBuyMinAmt);
    new_buy_min_amt_is_set = true;
    new_buy_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>new_buy_min_amt</em>カラムに値を設定します。 
   */
  public final void setNewBuyMinAmt( Long p_newBuyMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    new_buy_min_amt = p_newBuyMinAmt;
    new_buy_min_amt_is_set = true;
    new_buy_min_amt_is_modified = true;
  }


  /** 
   * <em>add_buy_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_addBuyMinAmt <em>add_buy_min_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setAddBuyMinAmt( long p_addBuyMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    add_buy_min_amt = new Long(p_addBuyMinAmt);
    add_buy_min_amt_is_set = true;
    add_buy_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>add_buy_min_amt</em>カラムに値を設定します。 
   */
  public final void setAddBuyMinAmt( Long p_addBuyMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    add_buy_min_amt = p_addBuyMinAmt;
    add_buy_min_amt_is_set = true;
    add_buy_min_amt_is_modified = true;
  }


  /** 
   * <em>sell_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_sellMinAmt <em>sell_min_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setSellMinAmt( long p_sellMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_min_amt = new Long(p_sellMinAmt);
    sell_min_amt_is_set = true;
    sell_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_min_amt</em>カラムに値を設定します。 
   */
  public final void setSellMinAmt( Long p_sellMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_min_amt = p_sellMinAmt;
    sell_min_amt_is_set = true;
    sell_min_amt_is_modified = true;
  }


  /** 
   * <em>swt_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_swtMinAmt <em>swt_min_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setSwtMinAmt( long p_swtMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_min_amt = new Long(p_swtMinAmt);
    swt_min_amt_is_set = true;
    swt_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>swt_min_amt</em>カラムに値を設定します。 
   */
  public final void setSwtMinAmt( Long p_swtMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    swt_min_amt = p_swtMinAmt;
    swt_min_amt_is_set = true;
    swt_min_amt_is_modified = true;
  }


  /** 
   * <em>new_buy_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_newBuyUnitAmt <em>new_buy_unit_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setNewBuyUnitAmt( long p_newBuyUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_buy_unit_amt = new Long(p_newBuyUnitAmt);
    new_buy_unit_amt_is_set = true;
    new_buy_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>new_buy_unit_amt</em>カラムに値を設定します。 
   */
  public final void setNewBuyUnitAmt( Long p_newBuyUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    new_buy_unit_amt = p_newBuyUnitAmt;
    new_buy_unit_amt_is_set = true;
    new_buy_unit_amt_is_modified = true;
  }


  /** 
   * <em>add_buy_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_addBuyUnitAmt <em>add_buy_unit_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setAddBuyUnitAmt( long p_addBuyUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    add_buy_unit_amt = new Long(p_addBuyUnitAmt);
    add_buy_unit_amt_is_set = true;
    add_buy_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>add_buy_unit_amt</em>カラムに値を設定します。 
   */
  public final void setAddBuyUnitAmt( Long p_addBuyUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    add_buy_unit_amt = p_addBuyUnitAmt;
    add_buy_unit_amt_is_set = true;
    add_buy_unit_amt_is_modified = true;
  }


  /** 
   * <em>sell_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_sellUnitAmt <em>sell_unit_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setSellUnitAmt( long p_sellUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_unit_amt = new Long(p_sellUnitAmt);
    sell_unit_amt_is_set = true;
    sell_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_unit_amt</em>カラムに値を設定します。 
   */
  public final void setSellUnitAmt( Long p_sellUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_unit_amt = p_sellUnitAmt;
    sell_unit_amt_is_set = true;
    sell_unit_amt_is_modified = true;
  }


  /** 
   * <em>swt_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_swtUnitAmt <em>swt_unit_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setSwtUnitAmt( long p_swtUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_unit_amt = new Long(p_swtUnitAmt);
    swt_unit_amt_is_set = true;
    swt_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>swt_unit_amt</em>カラムに値を設定します。 
   */
  public final void setSwtUnitAmt( Long p_swtUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    swt_unit_amt = p_swtUnitAmt;
    swt_unit_amt_is_set = true;
    swt_unit_amt_is_modified = true;
  }


  /** 
   * <em>buy_start_date</em>カラムの値を設定します。 
   *
   * @@param p_buyStartDate <em>buy_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setBuyStartDate( java.sql.Timestamp p_buyStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_start_date = p_buyStartDate;
    buy_start_date_is_set = true;
    buy_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>buy_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setBuyStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    buy_start_date_is_set = true;
    buy_start_date_is_modified = true;
  }


  /** 
   * <em>buy_end_date</em>カラムの値を設定します。 
   *
   * @@param p_buyEndDate <em>buy_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setBuyEndDate( java.sql.Timestamp p_buyEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_end_date = p_buyEndDate;
    buy_end_date_is_set = true;
    buy_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>buy_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setBuyEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    buy_end_date_is_set = true;
    buy_end_date_is_modified = true;
  }


  /** 
   * <em>sell_swt_start_date</em>カラムの値を設定します。 
   *
   * @@param p_sellSwtStartDate <em>sell_swt_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSellSwtStartDate( java.sql.Timestamp p_sellSwtStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_swt_start_date = p_sellSwtStartDate;
    sell_swt_start_date_is_set = true;
    sell_swt_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>sell_swt_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSellSwtStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_swt_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    sell_swt_start_date_is_set = true;
    sell_swt_start_date_is_modified = true;
  }


  /** 
   * <em>sell_swt_end_date</em>カラムの値を設定します。 
   *
   * @@param p_sellSwtEndDate <em>sell_swt_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSellSwtEndDate( java.sql.Timestamp p_sellSwtEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_swt_end_date = p_sellSwtEndDate;
    sell_swt_end_date_is_set = true;
    sell_swt_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>sell_swt_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSellSwtEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_swt_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    sell_swt_end_date_is_set = true;
    sell_swt_end_date_is_modified = true;
  }


  /** 
   * <em>buy_claim_start_date</em>カラムの値を設定します。 
   *
   * @@param p_buyClaimStartDate <em>buy_claim_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setBuyClaimStartDate( java.sql.Timestamp p_buyClaimStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_claim_start_date = p_buyClaimStartDate;
    buy_claim_start_date_is_set = true;
    buy_claim_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>buy_claim_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setBuyClaimStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_claim_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    buy_claim_start_date_is_set = true;
    buy_claim_start_date_is_modified = true;
  }


  /** 
   * <em>buy_claim_end_date</em>カラムの値を設定します。 
   *
   * @@param p_buyClaimEndDate <em>buy_claim_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setBuyClaimEndDate( java.sql.Timestamp p_buyClaimEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_claim_end_date = p_buyClaimEndDate;
    buy_claim_end_date_is_set = true;
    buy_claim_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>buy_claim_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setBuyClaimEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_claim_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    buy_claim_end_date_is_set = true;
    buy_claim_end_date_is_modified = true;
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
   * <em>online_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_onlineUpdatedTimestamp <em>online_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOnlineUpdatedTimestamp( java.sql.Timestamp p_onlineUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    online_updated_timestamp = p_onlineUpdatedTimestamp;
    online_updated_timestamp_is_set = true;
    online_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>online_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOnlineUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    online_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    online_updated_timestamp_is_set = true;
    online_updated_timestamp_is_modified = true;
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
   * <em>recruit_constant_value</em>カラムの値を設定します。 
   *
   * @@param p_recruitConstantValue <em>recruit_constant_value</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRecruitConstantValue( double p_recruitConstantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_constant_value = new Double(p_recruitConstantValue);
    recruit_constant_value_is_set = true;
    recruit_constant_value_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>recruit_constant_value</em>カラムに値を設定します。 
   */
  public final void setRecruitConstantValue( Double p_recruitConstantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_constant_value = p_recruitConstantValue;
    recruit_constant_value_is_set = true;
    recruit_constant_value_is_modified = true;
  }


  /** 
   * <em>recruit_settlement_div</em>カラムの値を設定します。 
   *
   * @@param p_recruitSettlementDiv <em>recruit_settlement_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRecruitSettlementDiv( String p_recruitSettlementDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_settlement_div = p_recruitSettlementDiv;
    recruit_settlement_div_is_set = true;
    recruit_settlement_div_is_modified = true;
  }


  /** 
   * <em>recruit_specity_div</em>カラムの値を設定します。 
   *
   * @@param p_recruitSpecityDiv <em>recruit_specity_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRecruitSpecityDiv( String p_recruitSpecityDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_specity_div = p_recruitSpecityDiv;
    recruit_specity_div_is_set = true;
    recruit_specity_div_is_modified = true;
  }


  /** 
   * <em>recruit_min_qty</em>カラムの値を設定します。 
   *
   * @@param p_recruitMinQty <em>recruit_min_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setRecruitMinQty( long p_recruitMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_min_qty = new Long(p_recruitMinQty);
    recruit_min_qty_is_set = true;
    recruit_min_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>recruit_min_qty</em>カラムに値を設定します。 
   */
  public final void setRecruitMinQty( Long p_recruitMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_min_qty = p_recruitMinQty;
    recruit_min_qty_is_set = true;
    recruit_min_qty_is_modified = true;
  }


  /** 
   * <em>recruit_unit_qty</em>カラムの値を設定します。 
   *
   * @@param p_recruitUnitQty <em>recruit_unit_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setRecruitUnitQty( long p_recruitUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_unit_qty = new Long(p_recruitUnitQty);
    recruit_unit_qty_is_set = true;
    recruit_unit_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>recruit_unit_qty</em>カラムに値を設定します。 
   */
  public final void setRecruitUnitQty( Long p_recruitUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_unit_qty = p_recruitUnitQty;
    recruit_unit_qty_is_set = true;
    recruit_unit_qty_is_modified = true;
  }


  /** 
   * <em>recruit_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_recruitMinAmt <em>recruit_min_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setRecruitMinAmt( long p_recruitMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_min_amt = new Long(p_recruitMinAmt);
    recruit_min_amt_is_set = true;
    recruit_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>recruit_min_amt</em>カラムに値を設定します。 
   */
  public final void setRecruitMinAmt( Long p_recruitMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_min_amt = p_recruitMinAmt;
    recruit_min_amt_is_set = true;
    recruit_min_amt_is_modified = true;
  }


  /** 
   * <em>recruit_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_recruitUnitAmt <em>recruit_unit_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setRecruitUnitAmt( long p_recruitUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_unit_amt = new Long(p_recruitUnitAmt);
    recruit_unit_amt_is_set = true;
    recruit_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>recruit_unit_amt</em>カラムに値を設定します。 
   */
  public final void setRecruitUnitAmt( Long p_recruitUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_unit_amt = p_recruitUnitAmt;
    recruit_unit_amt_is_set = true;
    recruit_unit_amt_is_modified = true;
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
   * <em>cal_price_div</em>カラムの値を設定します。 
   *
   * @@param p_calPriceDiv <em>cal_price_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCalPriceDiv( String p_calPriceDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cal_price_div = p_calPriceDiv;
    cal_price_div_is_set = true;
    cal_price_div_is_modified = true;
  }


  /** 
   * <em>plowback_product_div</em>カラムの値を設定します。 
   *
   * @@param p_plowbackProductDiv <em>plowback_product_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPlowbackProductDiv( String p_plowbackProductDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    plowback_product_div = p_plowbackProductDiv;
    plowback_product_div_is_set = true;
    plowback_product_div_is_modified = true;
  }


  /** 
   * <em>recruit_start_date_sonar</em>カラムの値を設定します。 
   *
   * @@param p_recruitStartDateSonar <em>recruit_start_date_sonar</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setRecruitStartDateSonar( java.sql.Timestamp p_recruitStartDateSonar )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_start_date_sonar = p_recruitStartDateSonar;
    recruit_start_date_sonar_is_set = true;
    recruit_start_date_sonar_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>recruit_start_date_sonar</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setRecruitStartDateSonar( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_start_date_sonar = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    recruit_start_date_sonar_is_set = true;
    recruit_start_date_sonar_is_modified = true;
  }


  /** 
   * <em>recruit_end_date_sonar</em>カラムの値を設定します。 
   *
   * @@param p_recruitEndDateSonar <em>recruit_end_date_sonar</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setRecruitEndDateSonar( java.sql.Timestamp p_recruitEndDateSonar )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_end_date_sonar = p_recruitEndDateSonar;
    recruit_end_date_sonar_is_set = true;
    recruit_end_date_sonar_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>recruit_end_date_sonar</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setRecruitEndDateSonar( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_end_date_sonar = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    recruit_end_date_sonar_is_set = true;
    recruit_end_date_sonar_is_modified = true;
  }


  /** 
   * <em>fixed_buy_possible_div</em>カラムの値を設定します。 
   *
   * @@param p_fixedBuyPossibleDiv <em>fixed_buy_possible_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFixedBuyPossibleDiv( String p_fixedBuyPossibleDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fixed_buy_possible_div = p_fixedBuyPossibleDiv;
    fixed_buy_possible_div_is_set = true;
    fixed_buy_possible_div_is_modified = true;
  }


  /** 
   * <em>unit_type_product_div</em>カラムの値を設定します。 
   *
   * @@param p_unitTypeProductDiv <em>unit_type_product_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setUnitTypeProductDiv( String p_unitTypeProductDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unit_type_product_div = p_unitTypeProductDiv;
    unit_type_product_div_is_set = true;
    unit_type_product_div_is_modified = true;
  }


  /** 
   * <em>frgn_new_buy_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_frgnNewBuyMinAmt <em>frgn_new_buy_min_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setFrgnNewBuyMinAmt( long p_frgnNewBuyMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_new_buy_min_amt = new Long(p_frgnNewBuyMinAmt);
    frgn_new_buy_min_amt_is_set = true;
    frgn_new_buy_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>frgn_new_buy_min_amt</em>カラムに値を設定します。 
   */
  public final void setFrgnNewBuyMinAmt( Long p_frgnNewBuyMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_new_buy_min_amt = p_frgnNewBuyMinAmt;
    frgn_new_buy_min_amt_is_set = true;
    frgn_new_buy_min_amt_is_modified = true;
  }


  /** 
   * <em>frgn_add_buy_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_frgnAddBuyMinAmt <em>frgn_add_buy_min_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setFrgnAddBuyMinAmt( long p_frgnAddBuyMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_add_buy_min_amt = new Long(p_frgnAddBuyMinAmt);
    frgn_add_buy_min_amt_is_set = true;
    frgn_add_buy_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>frgn_add_buy_min_amt</em>カラムに値を設定します。 
   */
  public final void setFrgnAddBuyMinAmt( Long p_frgnAddBuyMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_add_buy_min_amt = p_frgnAddBuyMinAmt;
    frgn_add_buy_min_amt_is_set = true;
    frgn_add_buy_min_amt_is_modified = true;
  }


  /** 
   * <em>frgn_sell_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_frgnSellMinAmt <em>frgn_sell_min_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setFrgnSellMinAmt( long p_frgnSellMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_sell_min_amt = new Long(p_frgnSellMinAmt);
    frgn_sell_min_amt_is_set = true;
    frgn_sell_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>frgn_sell_min_amt</em>カラムに値を設定します。 
   */
  public final void setFrgnSellMinAmt( Long p_frgnSellMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_sell_min_amt = p_frgnSellMinAmt;
    frgn_sell_min_amt_is_set = true;
    frgn_sell_min_amt_is_modified = true;
  }


  /** 
   * <em>frgn_new_buy_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_frgnNewBuyUnitAmt <em>frgn_new_buy_unit_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setFrgnNewBuyUnitAmt( long p_frgnNewBuyUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_new_buy_unit_amt = new Long(p_frgnNewBuyUnitAmt);
    frgn_new_buy_unit_amt_is_set = true;
    frgn_new_buy_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>frgn_new_buy_unit_amt</em>カラムに値を設定します。 
   */
  public final void setFrgnNewBuyUnitAmt( Long p_frgnNewBuyUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_new_buy_unit_amt = p_frgnNewBuyUnitAmt;
    frgn_new_buy_unit_amt_is_set = true;
    frgn_new_buy_unit_amt_is_modified = true;
  }


  /** 
   * <em>frgn_add_buy_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_frgnAddBuyUnitAmt <em>frgn_add_buy_unit_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setFrgnAddBuyUnitAmt( long p_frgnAddBuyUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_add_buy_unit_amt = new Long(p_frgnAddBuyUnitAmt);
    frgn_add_buy_unit_amt_is_set = true;
    frgn_add_buy_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>frgn_add_buy_unit_amt</em>カラムに値を設定します。 
   */
  public final void setFrgnAddBuyUnitAmt( Long p_frgnAddBuyUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_add_buy_unit_amt = p_frgnAddBuyUnitAmt;
    frgn_add_buy_unit_amt_is_set = true;
    frgn_add_buy_unit_amt_is_modified = true;
  }


  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_frgnSellUnitAmt <em>frgn_sell_unit_amt</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setFrgnSellUnitAmt( long p_frgnSellUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_sell_unit_amt = new Long(p_frgnSellUnitAmt);
    frgn_sell_unit_amt_is_set = true;
    frgn_sell_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>frgn_sell_unit_amt</em>カラムに値を設定します。 
   */
  public final void setFrgnSellUnitAmt( Long p_frgnSellUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_sell_unit_amt = p_frgnSellUnitAmt;
    frgn_sell_unit_amt_is_set = true;
    frgn_sell_unit_amt_is_modified = true;
  }


  /** 
   * <em>recruit_commission_div</em>カラムの値を設定します。 
   *
   * @@param p_recruitCommissionDiv <em>recruit_commission_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRecruitCommissionDiv( String p_recruitCommissionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_commission_div = p_recruitCommissionDiv;
    recruit_commission_div_is_set = true;
    recruit_commission_div_is_modified = true;
  }


  /** 
   * <em>open_close_div</em>カラムの値を設定します。 
   *
   * @@param p_openCloseDiv <em>open_close_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOpenCloseDiv( String p_openCloseDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_close_div = p_openCloseDiv;
    open_close_div_is_set = true;
    open_close_div_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("addtl_purchase_min_qty") ) {
                    return new Long( addtl_purchase_min_qty );
                }
                else if ( name.equals("add_buy_min_qty") ) {
                    return this.add_buy_min_qty;
                }
                else if ( name.equals("add_buy_unit_qty") ) {
                    return this.add_buy_unit_qty;
                }
                else if ( name.equals("add_buy_min_amt") ) {
                    return this.add_buy_min_amt;
                }
                else if ( name.equals("add_buy_unit_amt") ) {
                    return this.add_buy_unit_amt;
                }
                break;
            case 'b':
                if ( name.equals("buy_limit_div") ) {
                    return this.buy_limit_div;
                }
                else if ( name.equals("buy_constant_value") ) {
                    return this.buy_constant_value;
                }
                else if ( name.equals("buy_settlement_div") ) {
                    return this.buy_settlement_div;
                }
                else if ( name.equals("buy_specity_div") ) {
                    return this.buy_specity_div;
                }
                else if ( name.equals("buy_start_date") ) {
                    return this.buy_start_date;
                }
                else if ( name.equals("buy_end_date") ) {
                    return this.buy_end_date;
                }
                else if ( name.equals("buy_claim_start_date") ) {
                    return this.buy_claim_start_date;
                }
                else if ( name.equals("buy_claim_end_date") ) {
                    return this.buy_claim_end_date;
                }
                break;
            case 'c':
                if ( name.equals("category_code") ) {
                    return this.category_code;
                }
                else if ( name.equals("constant_value_app_date") ) {
                    return this.constant_value_app_date;
                }
                else if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("constant_value_calc_unit") ) {
                    return this.constant_value_calc_unit;
                }
                else if ( name.equals("contract_institution_type") ) {
                    return this.contract_institution_type;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("cal_price_div") ) {
                    return this.cal_price_div;
                }
                break;
            case 'd':
                if ( name.equals("delivery_method") ) {
                    return this.delivery_method;
                }
                break;
            case 'e':
                if ( name.equals("eng_product_name") ) {
                    return this.eng_product_name;
                }
                break;
            case 'f':
                if ( name.equals("fund_type") ) {
                    return this.fund_type;
                }
                else if ( name.equals("fixed_buy_possible_div") ) {
                    return this.fixed_buy_possible_div;
                }
                else if ( name.equals("frgn_new_buy_min_amt") ) {
                    return this.frgn_new_buy_min_amt;
                }
                else if ( name.equals("frgn_add_buy_min_amt") ) {
                    return this.frgn_add_buy_min_amt;
                }
                else if ( name.equals("frgn_sell_min_amt") ) {
                    return this.frgn_sell_min_amt;
                }
                else if ( name.equals("frgn_new_buy_unit_amt") ) {
                    return this.frgn_new_buy_unit_amt;
                }
                else if ( name.equals("frgn_add_buy_unit_amt") ) {
                    return this.frgn_add_buy_unit_amt;
                }
                else if ( name.equals("frgn_sell_unit_amt") ) {
                    return this.frgn_sell_unit_amt;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("init_purchase_min_qty") ) {
                    return new Long( init_purchase_min_qty );
                }
                else if ( name.equals("indication_ranking") ) {
                    return this.indication_ranking;
                }
                else if ( name.equals("input_unit") ) {
                    return this.input_unit;
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
                if ( name.equals("mutualassoc_product_code") ) {
                    return this.mutualassoc_product_code;
                }
                break;
            case 'n':
                if ( name.equals("new_buy_min_qty") ) {
                    return this.new_buy_min_qty;
                }
                else if ( name.equals("new_buy_unit_qty") ) {
                    return this.new_buy_unit_qty;
                }
                else if ( name.equals("new_buy_min_amt") ) {
                    return this.new_buy_min_amt;
                }
                else if ( name.equals("new_buy_unit_amt") ) {
                    return this.new_buy_unit_amt;
                }
                break;
            case 'o':
                if ( name.equals("online_updated_timestamp") ) {
                    return this.online_updated_timestamp;
                }
                else if ( name.equals("open_close_div") ) {
                    return this.open_close_div;
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
                else if ( name.equals("perference_money_div") ) {
                    return this.perference_money_div;
                }
                else if ( name.equals("plowback_product_div") ) {
                    return this.plowback_product_div;
                }
                break;
            case 'r':
                if ( name.equals("redemption_date") ) {
                    return this.redemption_date;
                }
                else if ( name.equals("reference_constant_value") ) {
                    return this.reference_constant_value;
                }
                else if ( name.equals("recruit_constant_value") ) {
                    return this.recruit_constant_value;
                }
                else if ( name.equals("recruit_settlement_div") ) {
                    return this.recruit_settlement_div;
                }
                else if ( name.equals("recruit_specity_div") ) {
                    return this.recruit_specity_div;
                }
                else if ( name.equals("recruit_min_qty") ) {
                    return this.recruit_min_qty;
                }
                else if ( name.equals("recruit_unit_qty") ) {
                    return this.recruit_unit_qty;
                }
                else if ( name.equals("recruit_min_amt") ) {
                    return this.recruit_min_amt;
                }
                else if ( name.equals("recruit_unit_amt") ) {
                    return this.recruit_unit_amt;
                }
                else if ( name.equals("recruit_start_date") ) {
                    return this.recruit_start_date;
                }
                else if ( name.equals("recruit_end_date") ) {
                    return this.recruit_end_date;
                }
                else if ( name.equals("recruit_start_date_sonar") ) {
                    return this.recruit_start_date_sonar;
                }
                else if ( name.equals("recruit_end_date_sonar") ) {
                    return this.recruit_end_date_sonar;
                }
                else if ( name.equals("recruit_commission_div") ) {
                    return this.recruit_commission_div;
                }
                break;
            case 's':
                if ( name.equals("system_handling_div") ) {
                    return this.system_handling_div;
                }
                else if ( name.equals("standard_name") ) {
                    return this.standard_name;
                }
                else if ( name.equals("setting_date") ) {
                    return this.setting_date;
                }
                else if ( name.equals("sell_ban_date") ) {
                    return this.sell_ban_date;
                }
                else if ( name.equals("swt_possible_group_id") ) {
                    return this.swt_possible_group_id;
                }
                else if ( name.equals("sell_constant_value") ) {
                    return this.sell_constant_value;
                }
                else if ( name.equals("sell_settlement_div") ) {
                    return this.sell_settlement_div;
                }
                else if ( name.equals("sell_specify_div") ) {
                    return this.sell_specify_div;
                }
                else if ( name.equals("swt_specify_div") ) {
                    return this.swt_specify_div;
                }
                else if ( name.equals("stock_type_bond_type") ) {
                    return this.stock_type_bond_type;
                }
                else if ( name.equals("sell_min_qty") ) {
                    return this.sell_min_qty;
                }
                else if ( name.equals("swt_min_qty") ) {
                    return this.swt_min_qty;
                }
                else if ( name.equals("sell_unit_qty") ) {
                    return this.sell_unit_qty;
                }
                else if ( name.equals("swt_unit_qty") ) {
                    return this.swt_unit_qty;
                }
                else if ( name.equals("sell_min_amt") ) {
                    return this.sell_min_amt;
                }
                else if ( name.equals("swt_min_amt") ) {
                    return this.swt_min_amt;
                }
                else if ( name.equals("sell_unit_amt") ) {
                    return this.sell_unit_amt;
                }
                else if ( name.equals("swt_unit_amt") ) {
                    return this.swt_unit_amt;
                }
                else if ( name.equals("sell_swt_start_date") ) {
                    return this.sell_swt_start_date;
                }
                else if ( name.equals("sell_swt_end_date") ) {
                    return this.sell_swt_end_date;
                }
                break;
            case 'u':
                if ( name.equals("unit_type_product_div") ) {
                    return this.unit_type_product_div;
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
                if ( name.equals("addtl_purchase_min_qty") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'addtl_purchase_min_qty' must be Long: '"+value+"' is not." );
                    this.addtl_purchase_min_qty = ((Long) value).longValue();
                    if (this.addtl_purchase_min_qty_is_set)
                        this.addtl_purchase_min_qty_is_modified = true;
                    this.addtl_purchase_min_qty_is_set = true;
                    return;
                }
                else if ( name.equals("add_buy_min_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'add_buy_min_qty' must be Long: '"+value+"' is not." );
                    this.add_buy_min_qty = (Long) value;
                    if (this.add_buy_min_qty_is_set)
                        this.add_buy_min_qty_is_modified = true;
                    this.add_buy_min_qty_is_set = true;
                    return;
                }
                else if ( name.equals("add_buy_unit_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'add_buy_unit_qty' must be Long: '"+value+"' is not." );
                    this.add_buy_unit_qty = (Long) value;
                    if (this.add_buy_unit_qty_is_set)
                        this.add_buy_unit_qty_is_modified = true;
                    this.add_buy_unit_qty_is_set = true;
                    return;
                }
                else if ( name.equals("add_buy_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'add_buy_min_amt' must be Long: '"+value+"' is not." );
                    this.add_buy_min_amt = (Long) value;
                    if (this.add_buy_min_amt_is_set)
                        this.add_buy_min_amt_is_modified = true;
                    this.add_buy_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("add_buy_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'add_buy_unit_amt' must be Long: '"+value+"' is not." );
                    this.add_buy_unit_amt = (Long) value;
                    if (this.add_buy_unit_amt_is_set)
                        this.add_buy_unit_amt_is_modified = true;
                    this.add_buy_unit_amt_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("buy_limit_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_limit_div' must be String: '"+value+"' is not." );
                    this.buy_limit_div = (String) value;
                    if (this.buy_limit_div_is_set)
                        this.buy_limit_div_is_modified = true;
                    this.buy_limit_div_is_set = true;
                    return;
                }
                else if ( name.equals("buy_constant_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'buy_constant_value' must be Double: '"+value+"' is not." );
                    this.buy_constant_value = (Double) value;
                    if (this.buy_constant_value_is_set)
                        this.buy_constant_value_is_modified = true;
                    this.buy_constant_value_is_set = true;
                    return;
                }
                else if ( name.equals("buy_settlement_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_settlement_div' must be String: '"+value+"' is not." );
                    this.buy_settlement_div = (String) value;
                    if (this.buy_settlement_div_is_set)
                        this.buy_settlement_div_is_modified = true;
                    this.buy_settlement_div_is_set = true;
                    return;
                }
                else if ( name.equals("buy_specity_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_specity_div' must be String: '"+value+"' is not." );
                    this.buy_specity_div = (String) value;
                    if (this.buy_specity_div_is_set)
                        this.buy_specity_div_is_modified = true;
                    this.buy_specity_div_is_set = true;
                    return;
                }
                else if ( name.equals("buy_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'buy_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.buy_start_date = (java.sql.Timestamp) value;
                    if (this.buy_start_date_is_set)
                        this.buy_start_date_is_modified = true;
                    this.buy_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("buy_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'buy_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.buy_end_date = (java.sql.Timestamp) value;
                    if (this.buy_end_date_is_set)
                        this.buy_end_date_is_modified = true;
                    this.buy_end_date_is_set = true;
                    return;
                }
                else if ( name.equals("buy_claim_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'buy_claim_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.buy_claim_start_date = (java.sql.Timestamp) value;
                    if (this.buy_claim_start_date_is_set)
                        this.buy_claim_start_date_is_modified = true;
                    this.buy_claim_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("buy_claim_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'buy_claim_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.buy_claim_end_date = (java.sql.Timestamp) value;
                    if (this.buy_claim_end_date_is_set)
                        this.buy_claim_end_date_is_modified = true;
                    this.buy_claim_end_date_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("category_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'category_code' must be String: '"+value+"' is not." );
                    this.category_code = (String) value;
                    if (this.category_code_is_set)
                        this.category_code_is_modified = true;
                    this.category_code_is_set = true;
                    return;
                }
                else if ( name.equals("constant_value_app_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'constant_value_app_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.constant_value_app_date = (java.sql.Timestamp) value;
                    if (this.constant_value_app_date_is_set)
                        this.constant_value_app_date_is_modified = true;
                    this.constant_value_app_date_is_set = true;
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
                else if ( name.equals("constant_value_calc_unit") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'constant_value_calc_unit' must be Integer: '"+value+"' is not." );
                    this.constant_value_calc_unit = (Integer) value;
                    if (this.constant_value_calc_unit_is_set)
                        this.constant_value_calc_unit_is_modified = true;
                    this.constant_value_calc_unit_is_set = true;
                    return;
                }
                else if ( name.equals("contract_institution_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'contract_institution_type' must be String: '"+value+"' is not." );
                    this.contract_institution_type = (String) value;
                    if (this.contract_institution_type_is_set)
                        this.contract_institution_type_is_modified = true;
                    this.contract_institution_type_is_set = true;
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
                else if ( name.equals("cal_price_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cal_price_div' must be String: '"+value+"' is not." );
                    this.cal_price_div = (String) value;
                    if (this.cal_price_div_is_set)
                        this.cal_price_div_is_modified = true;
                    this.cal_price_div_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("delivery_method") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delivery_method' must be String: '"+value+"' is not." );
                    this.delivery_method = (String) value;
                    if (this.delivery_method_is_set)
                        this.delivery_method_is_modified = true;
                    this.delivery_method_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("eng_product_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'eng_product_name' must be String: '"+value+"' is not." );
                    this.eng_product_name = (String) value;
                    if (this.eng_product_name_is_set)
                        this.eng_product_name_is_modified = true;
                    this.eng_product_name_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fund_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum) )
                        throw new IllegalArgumentException( "value for 'fund_type' must be com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum: '"+value+"' is not." );
                    this.fund_type = (com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum) value;
                    if (this.fund_type_is_set)
                        this.fund_type_is_modified = true;
                    this.fund_type_is_set = true;
                    return;
                }
                else if ( name.equals("fixed_buy_possible_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fixed_buy_possible_div' must be String: '"+value+"' is not." );
                    this.fixed_buy_possible_div = (String) value;
                    if (this.fixed_buy_possible_div_is_set)
                        this.fixed_buy_possible_div_is_modified = true;
                    this.fixed_buy_possible_div_is_set = true;
                    return;
                }
                else if ( name.equals("frgn_new_buy_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'frgn_new_buy_min_amt' must be Long: '"+value+"' is not." );
                    this.frgn_new_buy_min_amt = (Long) value;
                    if (this.frgn_new_buy_min_amt_is_set)
                        this.frgn_new_buy_min_amt_is_modified = true;
                    this.frgn_new_buy_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("frgn_add_buy_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'frgn_add_buy_min_amt' must be Long: '"+value+"' is not." );
                    this.frgn_add_buy_min_amt = (Long) value;
                    if (this.frgn_add_buy_min_amt_is_set)
                        this.frgn_add_buy_min_amt_is_modified = true;
                    this.frgn_add_buy_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("frgn_sell_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'frgn_sell_min_amt' must be Long: '"+value+"' is not." );
                    this.frgn_sell_min_amt = (Long) value;
                    if (this.frgn_sell_min_amt_is_set)
                        this.frgn_sell_min_amt_is_modified = true;
                    this.frgn_sell_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("frgn_new_buy_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'frgn_new_buy_unit_amt' must be Long: '"+value+"' is not." );
                    this.frgn_new_buy_unit_amt = (Long) value;
                    if (this.frgn_new_buy_unit_amt_is_set)
                        this.frgn_new_buy_unit_amt_is_modified = true;
                    this.frgn_new_buy_unit_amt_is_set = true;
                    return;
                }
                else if ( name.equals("frgn_add_buy_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'frgn_add_buy_unit_amt' must be Long: '"+value+"' is not." );
                    this.frgn_add_buy_unit_amt = (Long) value;
                    if (this.frgn_add_buy_unit_amt_is_set)
                        this.frgn_add_buy_unit_amt_is_modified = true;
                    this.frgn_add_buy_unit_amt_is_set = true;
                    return;
                }
                else if ( name.equals("frgn_sell_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'frgn_sell_unit_amt' must be Long: '"+value+"' is not." );
                    this.frgn_sell_unit_amt = (Long) value;
                    if (this.frgn_sell_unit_amt_is_set)
                        this.frgn_sell_unit_amt_is_modified = true;
                    this.frgn_sell_unit_amt_is_set = true;
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
                else if ( name.equals("init_purchase_min_qty") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'init_purchase_min_qty' must be Long: '"+value+"' is not." );
                    this.init_purchase_min_qty = ((Long) value).longValue();
                    if (this.init_purchase_min_qty_is_set)
                        this.init_purchase_min_qty_is_modified = true;
                    this.init_purchase_min_qty_is_set = true;
                    return;
                }
                else if ( name.equals("indication_ranking") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'indication_ranking' must be Integer: '"+value+"' is not." );
                    this.indication_ranking = (Integer) value;
                    if (this.indication_ranking_is_set)
                        this.indication_ranking_is_modified = true;
                    this.indication_ranking_is_set = true;
                    return;
                }
                else if ( name.equals("input_unit") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'input_unit' must be String: '"+value+"' is not." );
                    this.input_unit = (String) value;
                    if (this.input_unit_is_set)
                        this.input_unit_is_modified = true;
                    this.input_unit_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
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
                if ( name.equals("mutualassoc_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mutualassoc_product_code' must be String: '"+value+"' is not." );
                    this.mutualassoc_product_code = (String) value;
                    if (this.mutualassoc_product_code_is_set)
                        this.mutualassoc_product_code_is_modified = true;
                    this.mutualassoc_product_code_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("new_buy_min_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'new_buy_min_qty' must be Long: '"+value+"' is not." );
                    this.new_buy_min_qty = (Long) value;
                    if (this.new_buy_min_qty_is_set)
                        this.new_buy_min_qty_is_modified = true;
                    this.new_buy_min_qty_is_set = true;
                    return;
                }
                else if ( name.equals("new_buy_unit_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'new_buy_unit_qty' must be Long: '"+value+"' is not." );
                    this.new_buy_unit_qty = (Long) value;
                    if (this.new_buy_unit_qty_is_set)
                        this.new_buy_unit_qty_is_modified = true;
                    this.new_buy_unit_qty_is_set = true;
                    return;
                }
                else if ( name.equals("new_buy_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'new_buy_min_amt' must be Long: '"+value+"' is not." );
                    this.new_buy_min_amt = (Long) value;
                    if (this.new_buy_min_amt_is_set)
                        this.new_buy_min_amt_is_modified = true;
                    this.new_buy_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("new_buy_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'new_buy_unit_amt' must be Long: '"+value+"' is not." );
                    this.new_buy_unit_amt = (Long) value;
                    if (this.new_buy_unit_amt_is_set)
                        this.new_buy_unit_amt_is_modified = true;
                    this.new_buy_unit_amt_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("online_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'online_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.online_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.online_updated_timestamp_is_set)
                        this.online_updated_timestamp_is_modified = true;
                    this.online_updated_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("open_close_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'open_close_div' must be String: '"+value+"' is not." );
                    this.open_close_div = (String) value;
                    if (this.open_close_div_is_set)
                        this.open_close_div_is_modified = true;
                    this.open_close_div_is_set = true;
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
                else if ( name.equals("perference_money_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'perference_money_div' must be String: '"+value+"' is not." );
                    this.perference_money_div = (String) value;
                    if (this.perference_money_div_is_set)
                        this.perference_money_div_is_modified = true;
                    this.perference_money_div_is_set = true;
                    return;
                }
                else if ( name.equals("plowback_product_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'plowback_product_div' must be String: '"+value+"' is not." );
                    this.plowback_product_div = (String) value;
                    if (this.plowback_product_div_is_set)
                        this.plowback_product_div_is_modified = true;
                    this.plowback_product_div_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("redemption_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'redemption_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.redemption_date = (java.sql.Timestamp) value;
                    if (this.redemption_date_is_set)
                        this.redemption_date_is_modified = true;
                    this.redemption_date_is_set = true;
                    return;
                }
                else if ( name.equals("reference_constant_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'reference_constant_value' must be Double: '"+value+"' is not." );
                    this.reference_constant_value = (Double) value;
                    if (this.reference_constant_value_is_set)
                        this.reference_constant_value_is_modified = true;
                    this.reference_constant_value_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_constant_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'recruit_constant_value' must be Double: '"+value+"' is not." );
                    this.recruit_constant_value = (Double) value;
                    if (this.recruit_constant_value_is_set)
                        this.recruit_constant_value_is_modified = true;
                    this.recruit_constant_value_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_settlement_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'recruit_settlement_div' must be String: '"+value+"' is not." );
                    this.recruit_settlement_div = (String) value;
                    if (this.recruit_settlement_div_is_set)
                        this.recruit_settlement_div_is_modified = true;
                    this.recruit_settlement_div_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_specity_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'recruit_specity_div' must be String: '"+value+"' is not." );
                    this.recruit_specity_div = (String) value;
                    if (this.recruit_specity_div_is_set)
                        this.recruit_specity_div_is_modified = true;
                    this.recruit_specity_div_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_min_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'recruit_min_qty' must be Long: '"+value+"' is not." );
                    this.recruit_min_qty = (Long) value;
                    if (this.recruit_min_qty_is_set)
                        this.recruit_min_qty_is_modified = true;
                    this.recruit_min_qty_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_unit_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'recruit_unit_qty' must be Long: '"+value+"' is not." );
                    this.recruit_unit_qty = (Long) value;
                    if (this.recruit_unit_qty_is_set)
                        this.recruit_unit_qty_is_modified = true;
                    this.recruit_unit_qty_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'recruit_min_amt' must be Long: '"+value+"' is not." );
                    this.recruit_min_amt = (Long) value;
                    if (this.recruit_min_amt_is_set)
                        this.recruit_min_amt_is_modified = true;
                    this.recruit_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'recruit_unit_amt' must be Long: '"+value+"' is not." );
                    this.recruit_unit_amt = (Long) value;
                    if (this.recruit_unit_amt_is_set)
                        this.recruit_unit_amt_is_modified = true;
                    this.recruit_unit_amt_is_set = true;
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
                else if ( name.equals("recruit_start_date_sonar") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'recruit_start_date_sonar' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.recruit_start_date_sonar = (java.sql.Timestamp) value;
                    if (this.recruit_start_date_sonar_is_set)
                        this.recruit_start_date_sonar_is_modified = true;
                    this.recruit_start_date_sonar_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_end_date_sonar") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'recruit_end_date_sonar' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.recruit_end_date_sonar = (java.sql.Timestamp) value;
                    if (this.recruit_end_date_sonar_is_set)
                        this.recruit_end_date_sonar_is_modified = true;
                    this.recruit_end_date_sonar_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_commission_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'recruit_commission_div' must be String: '"+value+"' is not." );
                    this.recruit_commission_div = (String) value;
                    if (this.recruit_commission_div_is_set)
                        this.recruit_commission_div_is_modified = true;
                    this.recruit_commission_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("system_handling_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'system_handling_div' must be String: '"+value+"' is not." );
                    this.system_handling_div = (String) value;
                    if (this.system_handling_div_is_set)
                        this.system_handling_div_is_modified = true;
                    this.system_handling_div_is_set = true;
                    return;
                }
                else if ( name.equals("standard_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'standard_name' must be String: '"+value+"' is not." );
                    this.standard_name = (String) value;
                    if (this.standard_name_is_set)
                        this.standard_name_is_modified = true;
                    this.standard_name_is_set = true;
                    return;
                }
                else if ( name.equals("setting_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'setting_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.setting_date = (java.sql.Timestamp) value;
                    if (this.setting_date_is_set)
                        this.setting_date_is_modified = true;
                    this.setting_date_is_set = true;
                    return;
                }
                else if ( name.equals("sell_ban_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'sell_ban_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.sell_ban_date = (java.sql.Timestamp) value;
                    if (this.sell_ban_date_is_set)
                        this.sell_ban_date_is_modified = true;
                    this.sell_ban_date_is_set = true;
                    return;
                }
                else if ( name.equals("swt_possible_group_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'swt_possible_group_id' must be Integer: '"+value+"' is not." );
                    this.swt_possible_group_id = (Integer) value;
                    if (this.swt_possible_group_id_is_set)
                        this.swt_possible_group_id_is_modified = true;
                    this.swt_possible_group_id_is_set = true;
                    return;
                }
                else if ( name.equals("sell_constant_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'sell_constant_value' must be Double: '"+value+"' is not." );
                    this.sell_constant_value = (Double) value;
                    if (this.sell_constant_value_is_set)
                        this.sell_constant_value_is_modified = true;
                    this.sell_constant_value_is_set = true;
                    return;
                }
                else if ( name.equals("sell_settlement_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_settlement_div' must be String: '"+value+"' is not." );
                    this.sell_settlement_div = (String) value;
                    if (this.sell_settlement_div_is_set)
                        this.sell_settlement_div_is_modified = true;
                    this.sell_settlement_div_is_set = true;
                    return;
                }
                else if ( name.equals("sell_specify_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_specify_div' must be String: '"+value+"' is not." );
                    this.sell_specify_div = (String) value;
                    if (this.sell_specify_div_is_set)
                        this.sell_specify_div_is_modified = true;
                    this.sell_specify_div_is_set = true;
                    return;
                }
                else if ( name.equals("swt_specify_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_specify_div' must be String: '"+value+"' is not." );
                    this.swt_specify_div = (String) value;
                    if (this.swt_specify_div_is_set)
                        this.swt_specify_div_is_modified = true;
                    this.swt_specify_div_is_set = true;
                    return;
                }
                else if ( name.equals("stock_type_bond_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stock_type_bond_type' must be String: '"+value+"' is not." );
                    this.stock_type_bond_type = (String) value;
                    if (this.stock_type_bond_type_is_set)
                        this.stock_type_bond_type_is_modified = true;
                    this.stock_type_bond_type_is_set = true;
                    return;
                }
                else if ( name.equals("sell_min_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sell_min_qty' must be Long: '"+value+"' is not." );
                    this.sell_min_qty = (Long) value;
                    if (this.sell_min_qty_is_set)
                        this.sell_min_qty_is_modified = true;
                    this.sell_min_qty_is_set = true;
                    return;
                }
                else if ( name.equals("swt_min_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'swt_min_qty' must be Long: '"+value+"' is not." );
                    this.swt_min_qty = (Long) value;
                    if (this.swt_min_qty_is_set)
                        this.swt_min_qty_is_modified = true;
                    this.swt_min_qty_is_set = true;
                    return;
                }
                else if ( name.equals("sell_unit_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sell_unit_qty' must be Long: '"+value+"' is not." );
                    this.sell_unit_qty = (Long) value;
                    if (this.sell_unit_qty_is_set)
                        this.sell_unit_qty_is_modified = true;
                    this.sell_unit_qty_is_set = true;
                    return;
                }
                else if ( name.equals("swt_unit_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'swt_unit_qty' must be Long: '"+value+"' is not." );
                    this.swt_unit_qty = (Long) value;
                    if (this.swt_unit_qty_is_set)
                        this.swt_unit_qty_is_modified = true;
                    this.swt_unit_qty_is_set = true;
                    return;
                }
                else if ( name.equals("sell_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sell_min_amt' must be Long: '"+value+"' is not." );
                    this.sell_min_amt = (Long) value;
                    if (this.sell_min_amt_is_set)
                        this.sell_min_amt_is_modified = true;
                    this.sell_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("swt_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'swt_min_amt' must be Long: '"+value+"' is not." );
                    this.swt_min_amt = (Long) value;
                    if (this.swt_min_amt_is_set)
                        this.swt_min_amt_is_modified = true;
                    this.swt_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("sell_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sell_unit_amt' must be Long: '"+value+"' is not." );
                    this.sell_unit_amt = (Long) value;
                    if (this.sell_unit_amt_is_set)
                        this.sell_unit_amt_is_modified = true;
                    this.sell_unit_amt_is_set = true;
                    return;
                }
                else if ( name.equals("swt_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'swt_unit_amt' must be Long: '"+value+"' is not." );
                    this.swt_unit_amt = (Long) value;
                    if (this.swt_unit_amt_is_set)
                        this.swt_unit_amt_is_modified = true;
                    this.swt_unit_amt_is_set = true;
                    return;
                }
                else if ( name.equals("sell_swt_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'sell_swt_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.sell_swt_start_date = (java.sql.Timestamp) value;
                    if (this.sell_swt_start_date_is_set)
                        this.sell_swt_start_date_is_modified = true;
                    this.sell_swt_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("sell_swt_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'sell_swt_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.sell_swt_end_date = (java.sql.Timestamp) value;
                    if (this.sell_swt_end_date_is_set)
                        this.sell_swt_end_date_is_modified = true;
                    this.sell_swt_end_date_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("unit_type_product_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'unit_type_product_div' must be String: '"+value+"' is not." );
                    this.unit_type_product_div = (String) value;
                    if (this.unit_type_product_div_is_set)
                        this.unit_type_product_div_is_modified = true;
                    this.unit_type_product_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
