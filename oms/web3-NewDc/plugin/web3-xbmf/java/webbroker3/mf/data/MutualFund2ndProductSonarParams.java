head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFund2ndProductSonarParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * mutual_fund_2nd_product_sonarテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link MutualFund2ndProductSonarRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link MutualFund2ndProductSonarParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link MutualFund2ndProductSonarParams}が{@@link MutualFund2ndProductSonarRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFund2ndProductSonarPK 
 * @@see MutualFund2ndProductSonarRow 
 */
public class MutualFund2ndProductSonarParams extends Params implements MutualFund2ndProductSonarRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mutual_fund_2nd_product_sonar";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = MutualFund2ndProductSonarRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return MutualFund2ndProductSonarRow.TYPE;
  }


  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>work_date</em>カラムの値 
   */
  public  java.sql.Timestamp  work_date;

  /** 
   * <em>reg_date</em>カラムの値 
   */
  public  java.sql.Timestamp  reg_date;

  /** 
   * <em>last_update_date</em>カラムの値 
   */
  public  java.sql.Timestamp  last_update_date;

  /** 
   * <em>reg_div</em>カラムの値 
   */
  public  String  reg_div;

  /** 
   * <em>product_name_kana</em>カラムの値 
   */
  public  String  product_name_kana;

  /** 
   * <em>product_name_kanji</em>カラムの値 
   */
  public  String  product_name_kanji;

  /** 
   * <em>recruit_unit</em>カラムの値 
   */
  public  Integer  recruit_unit;

  /** 
   * <em>dealing_unit_qty</em>カラムの値 
   */
  public  Integer  dealing_unit_qty;

  /** 
   * <em>recruit_min_qty</em>カラムの値 
   */
  public  Integer  recruit_min_qty;

  /** 
   * <em>buy_min_qty</em>カラムの値 
   */
  public  Integer  buy_min_qty;

  /** 
   * <em>sell_min_qty</em>カラムの値 
   */
  public  Integer  sell_min_qty;

  /** 
   * <em>swt_min_qty</em>カラムの値 
   */
  public  Integer  swt_min_qty;

  /** 
   * <em>recruit_unit_qty</em>カラムの値 
   */
  public  Integer  recruit_unit_qty;

  /** 
   * <em>buy_unit_qty</em>カラムの値 
   */
  public  Integer  buy_unit_qty;

  /** 
   * <em>sell_unit_qty</em>カラムの値 
   */
  public  Integer  sell_unit_qty;

  /** 
   * <em>swt_unit_qty</em>カラムの値 
   */
  public  Integer  swt_unit_qty;

  /** 
   * <em>recruit_min_amt</em>カラムの値 
   */
  public  Integer  recruit_min_amt;

  /** 
   * <em>buy_min_amt</em>カラムの値 
   */
  public  Integer  buy_min_amt;

  /** 
   * <em>sell_min_amt</em>カラムの値 
   */
  public  Integer  sell_min_amt;

  /** 
   * <em>swt_min_amt</em>カラムの値 
   */
  public  Integer  swt_min_amt;

  /** 
   * <em>recruit_unit_amt</em>カラムの値 
   */
  public  Integer  recruit_unit_amt;

  /** 
   * <em>buy_unit_amt</em>カラムの値 
   */
  public  Integer  buy_unit_amt;

  /** 
   * <em>sell_unit_amt</em>カラムの値 
   */
  public  Integer  sell_unit_amt;

  /** 
   * <em>swt_unit_amt</em>カラムの値 
   */
  public  Integer  swt_unit_amt;

  /** 
   * <em>recruit_qty_spec_div</em>カラムの値 
   */
  public  String  recruit_qty_spec_div;

  /** 
   * <em>recruit_amt_spec_div</em>カラムの値 
   */
  public  String  recruit_amt_spec_div;

  /** 
   * <em>buy_qty_spec_div</em>カラムの値 
   */
  public  String  buy_qty_spec_div;

  /** 
   * <em>buy_amt_spec_div</em>カラムの値 
   */
  public  String  buy_amt_spec_div;

  /** 
   * <em>sell_qty_spec_div</em>カラムの値 
   */
  public  String  sell_qty_spec_div;

  /** 
   * <em>sell_amt_spec_div</em>カラムの値 
   */
  public  String  sell_amt_spec_div;

  /** 
   * <em>swt_qty_spec_div</em>カラムの値 
   */
  public  String  swt_qty_spec_div;

  /** 
   * <em>swt_amt_spec_div</em>カラムの値 
   */
  public  String  swt_amt_spec_div;

  /** 
   * <em>input_unit_div</em>カラムの値 
   */
  public  String  input_unit_div;

  /** 
   * <em>cal_price_div</em>カラムの値 
   */
  public  String  cal_price_div;

  /** 
   * <em>sell_exception_div</em>カラムの値 
   */
  public  String  sell_exception_div;

  /** 
   * <em>swt_trade_div</em>カラムの値 
   */
  public  String  swt_trade_div;

  /** 
   * <em>swt_group</em>カラムの値 
   */
  public  String  swt_group;

  /** 
   * <em>swt_companion_subject_div</em>カラムの値 
   */
  public  String  swt_companion_subject_div;

  /** 
   * <em>buy_disable_div</em>カラムの値 
   */
  public  String  buy_disable_div;

  /** 
   * <em>swt_perference_enable_div</em>カラムの値 
   */
  public  String  swt_perference_enable_div;

  /** 
   * <em>redemption_perference_price</em>カラムの値 
   */
  public  Double  redemption_perference_price;

  /** 
   * <em>redemption_commission_rate</em>カラムの値 
   */
  public  Double  redemption_commission_rate;

  /** 
   * <em>pre_redemption_begin_date</em>カラムの値 
   */
  public  java.sql.Timestamp  pre_redemption_begin_date;

  /** 
   * <em>closing_date_cal_div</em>カラムの値 
   */
  public  String  closing_date_cal_div;

  /** 
   * <em>closing_spec_date</em>カラムの値 
   */
  public  String  closing_spec_date;

  /** 
   * <em>annual_profit_qty_times</em>カラムの値 
   */
  public  String  annual_profit_qty_times;

  /** 
   * <em>sell_advance_order_div</em>カラムの値 
   */
  public  String  sell_advance_order_div;

  /** 
   * <em>buy_advance_order_div</em>カラムの値 
   */
  public  String  buy_advance_order_div;

  /** 
   * <em>sell_undelivering_term</em>カラムの値 
   */
  public  String  sell_undelivering_term;

  /** 
   * <em>buy_undelivering_term</em>カラムの値 
   */
  public  String  buy_undelivering_term;

  /** 
   * <em>recruit_stop_div</em>カラムの値 
   */
  public  String  recruit_stop_div;

  /** 
   * <em>dealing_stop_div</em>カラムの値 
   */
  public  String  dealing_stop_div;

  /** 
   * <em>storage_stop_div</em>カラムの値 
   */
  public  String  storage_stop_div;

  /** 
   * <em>specific_corpus_app</em>カラムの値 
   */
  public  String  specific_corpus_app;

  /** 
   * <em>perference_qualify</em>カラムの値 
   */
  public  String  perference_qualify;

  /** 
   * <em>collateral_qualify</em>カラムの値 
   */
  public  String  collateral_qualify;

  /** 
   * <em>operate_report_send_div</em>カラムの値 
   */
  public  String  operate_report_send_div;

  /** 
   * <em>operate_report_send_month1</em>カラムの値 
   */
  public  String  operate_report_send_month1;

  /** 
   * <em>operate_report_send_month2</em>カラムの値 
   */
  public  String  operate_report_send_month2;

  /** 
   * <em>biz_asset_code</em>カラムの値 
   */
  public  String  biz_asset_code;

  /** 
   * <em>biz_asset_name</em>カラムの値 
   */
  public  String  biz_asset_name;

  /** 
   * <em>prospectus_conversion_date</em>カラムの値 
   */
  public  java.sql.Timestamp  prospectus_conversion_date;

  /** 
   * <em>frgn_buy_min_amt</em>カラムの値 
   */
  public  Integer  frgn_buy_min_amt;

  /** 
   * <em>frgn_sell_min_amt</em>カラムの値 
   */
  public  Integer  frgn_sell_min_amt;

  /** 
   * <em>frgn_buy_unit_amt</em>カラムの値 
   */
  public  Integer  frgn_buy_unit_amt;

  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値 
   */
  public  Integer  frgn_sell_unit_amt;

  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean work_date_is_set = false;
  private boolean work_date_is_modified = false;
  private boolean reg_date_is_set = false;
  private boolean reg_date_is_modified = false;
  private boolean last_update_date_is_set = false;
  private boolean last_update_date_is_modified = false;
  private boolean reg_div_is_set = false;
  private boolean reg_div_is_modified = false;
  private boolean product_name_kana_is_set = false;
  private boolean product_name_kana_is_modified = false;
  private boolean product_name_kanji_is_set = false;
  private boolean product_name_kanji_is_modified = false;
  private boolean recruit_unit_is_set = false;
  private boolean recruit_unit_is_modified = false;
  private boolean dealing_unit_qty_is_set = false;
  private boolean dealing_unit_qty_is_modified = false;
  private boolean recruit_min_qty_is_set = false;
  private boolean recruit_min_qty_is_modified = false;
  private boolean buy_min_qty_is_set = false;
  private boolean buy_min_qty_is_modified = false;
  private boolean sell_min_qty_is_set = false;
  private boolean sell_min_qty_is_modified = false;
  private boolean swt_min_qty_is_set = false;
  private boolean swt_min_qty_is_modified = false;
  private boolean recruit_unit_qty_is_set = false;
  private boolean recruit_unit_qty_is_modified = false;
  private boolean buy_unit_qty_is_set = false;
  private boolean buy_unit_qty_is_modified = false;
  private boolean sell_unit_qty_is_set = false;
  private boolean sell_unit_qty_is_modified = false;
  private boolean swt_unit_qty_is_set = false;
  private boolean swt_unit_qty_is_modified = false;
  private boolean recruit_min_amt_is_set = false;
  private boolean recruit_min_amt_is_modified = false;
  private boolean buy_min_amt_is_set = false;
  private boolean buy_min_amt_is_modified = false;
  private boolean sell_min_amt_is_set = false;
  private boolean sell_min_amt_is_modified = false;
  private boolean swt_min_amt_is_set = false;
  private boolean swt_min_amt_is_modified = false;
  private boolean recruit_unit_amt_is_set = false;
  private boolean recruit_unit_amt_is_modified = false;
  private boolean buy_unit_amt_is_set = false;
  private boolean buy_unit_amt_is_modified = false;
  private boolean sell_unit_amt_is_set = false;
  private boolean sell_unit_amt_is_modified = false;
  private boolean swt_unit_amt_is_set = false;
  private boolean swt_unit_amt_is_modified = false;
  private boolean recruit_qty_spec_div_is_set = false;
  private boolean recruit_qty_spec_div_is_modified = false;
  private boolean recruit_amt_spec_div_is_set = false;
  private boolean recruit_amt_spec_div_is_modified = false;
  private boolean buy_qty_spec_div_is_set = false;
  private boolean buy_qty_spec_div_is_modified = false;
  private boolean buy_amt_spec_div_is_set = false;
  private boolean buy_amt_spec_div_is_modified = false;
  private boolean sell_qty_spec_div_is_set = false;
  private boolean sell_qty_spec_div_is_modified = false;
  private boolean sell_amt_spec_div_is_set = false;
  private boolean sell_amt_spec_div_is_modified = false;
  private boolean swt_qty_spec_div_is_set = false;
  private boolean swt_qty_spec_div_is_modified = false;
  private boolean swt_amt_spec_div_is_set = false;
  private boolean swt_amt_spec_div_is_modified = false;
  private boolean input_unit_div_is_set = false;
  private boolean input_unit_div_is_modified = false;
  private boolean cal_price_div_is_set = false;
  private boolean cal_price_div_is_modified = false;
  private boolean sell_exception_div_is_set = false;
  private boolean sell_exception_div_is_modified = false;
  private boolean swt_trade_div_is_set = false;
  private boolean swt_trade_div_is_modified = false;
  private boolean swt_group_is_set = false;
  private boolean swt_group_is_modified = false;
  private boolean swt_companion_subject_div_is_set = false;
  private boolean swt_companion_subject_div_is_modified = false;
  private boolean buy_disable_div_is_set = false;
  private boolean buy_disable_div_is_modified = false;
  private boolean swt_perference_enable_div_is_set = false;
  private boolean swt_perference_enable_div_is_modified = false;
  private boolean redemption_perference_price_is_set = false;
  private boolean redemption_perference_price_is_modified = false;
  private boolean redemption_commission_rate_is_set = false;
  private boolean redemption_commission_rate_is_modified = false;
  private boolean pre_redemption_begin_date_is_set = false;
  private boolean pre_redemption_begin_date_is_modified = false;
  private boolean closing_date_cal_div_is_set = false;
  private boolean closing_date_cal_div_is_modified = false;
  private boolean closing_spec_date_is_set = false;
  private boolean closing_spec_date_is_modified = false;
  private boolean annual_profit_qty_times_is_set = false;
  private boolean annual_profit_qty_times_is_modified = false;
  private boolean sell_advance_order_div_is_set = false;
  private boolean sell_advance_order_div_is_modified = false;
  private boolean buy_advance_order_div_is_set = false;
  private boolean buy_advance_order_div_is_modified = false;
  private boolean sell_undelivering_term_is_set = false;
  private boolean sell_undelivering_term_is_modified = false;
  private boolean buy_undelivering_term_is_set = false;
  private boolean buy_undelivering_term_is_modified = false;
  private boolean recruit_stop_div_is_set = false;
  private boolean recruit_stop_div_is_modified = false;
  private boolean dealing_stop_div_is_set = false;
  private boolean dealing_stop_div_is_modified = false;
  private boolean storage_stop_div_is_set = false;
  private boolean storage_stop_div_is_modified = false;
  private boolean specific_corpus_app_is_set = false;
  private boolean specific_corpus_app_is_modified = false;
  private boolean perference_qualify_is_set = false;
  private boolean perference_qualify_is_modified = false;
  private boolean collateral_qualify_is_set = false;
  private boolean collateral_qualify_is_modified = false;
  private boolean operate_report_send_div_is_set = false;
  private boolean operate_report_send_div_is_modified = false;
  private boolean operate_report_send_month1_is_set = false;
  private boolean operate_report_send_month1_is_modified = false;
  private boolean operate_report_send_month2_is_set = false;
  private boolean operate_report_send_month2_is_modified = false;
  private boolean biz_asset_code_is_set = false;
  private boolean biz_asset_code_is_modified = false;
  private boolean biz_asset_name_is_set = false;
  private boolean biz_asset_name_is_modified = false;
  private boolean prospectus_conversion_date_is_set = false;
  private boolean prospectus_conversion_date_is_modified = false;
  private boolean frgn_buy_min_amt_is_set = false;
  private boolean frgn_buy_min_amt_is_modified = false;
  private boolean frgn_sell_min_amt_is_set = false;
  private boolean frgn_sell_min_amt_is_modified = false;
  private boolean frgn_buy_unit_amt_is_set = false;
  private boolean frgn_buy_unit_amt_is_modified = false;
  private boolean frgn_sell_unit_amt_is_set = false;
  private boolean frgn_sell_unit_amt_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "product_code=" + product_code
      + "," + "institution_code=" + institution_code
      + "," + "work_date=" +work_date
      + "," + "reg_date=" +reg_date
      + "," + "last_update_date=" +last_update_date
      + "," + "reg_div=" +reg_div
      + "," + "product_name_kana=" +product_name_kana
      + "," + "product_name_kanji=" +product_name_kanji
      + "," + "recruit_unit=" +recruit_unit
      + "," + "dealing_unit_qty=" +dealing_unit_qty
      + "," + "recruit_min_qty=" +recruit_min_qty
      + "," + "buy_min_qty=" +buy_min_qty
      + "," + "sell_min_qty=" +sell_min_qty
      + "," + "swt_min_qty=" +swt_min_qty
      + "," + "recruit_unit_qty=" +recruit_unit_qty
      + "," + "buy_unit_qty=" +buy_unit_qty
      + "," + "sell_unit_qty=" +sell_unit_qty
      + "," + "swt_unit_qty=" +swt_unit_qty
      + "," + "recruit_min_amt=" +recruit_min_amt
      + "," + "buy_min_amt=" +buy_min_amt
      + "," + "sell_min_amt=" +sell_min_amt
      + "," + "swt_min_amt=" +swt_min_amt
      + "," + "recruit_unit_amt=" +recruit_unit_amt
      + "," + "buy_unit_amt=" +buy_unit_amt
      + "," + "sell_unit_amt=" +sell_unit_amt
      + "," + "swt_unit_amt=" +swt_unit_amt
      + "," + "recruit_qty_spec_div=" +recruit_qty_spec_div
      + "," + "recruit_amt_spec_div=" +recruit_amt_spec_div
      + "," + "buy_qty_spec_div=" +buy_qty_spec_div
      + "," + "buy_amt_spec_div=" +buy_amt_spec_div
      + "," + "sell_qty_spec_div=" +sell_qty_spec_div
      + "," + "sell_amt_spec_div=" +sell_amt_spec_div
      + "," + "swt_qty_spec_div=" +swt_qty_spec_div
      + "," + "swt_amt_spec_div=" +swt_amt_spec_div
      + "," + "input_unit_div=" +input_unit_div
      + "," + "cal_price_div=" +cal_price_div
      + "," + "sell_exception_div=" +sell_exception_div
      + "," + "swt_trade_div=" +swt_trade_div
      + "," + "swt_group=" +swt_group
      + "," + "swt_companion_subject_div=" +swt_companion_subject_div
      + "," + "buy_disable_div=" +buy_disable_div
      + "," + "swt_perference_enable_div=" +swt_perference_enable_div
      + "," + "redemption_perference_price=" +redemption_perference_price
      + "," + "redemption_commission_rate=" +redemption_commission_rate
      + "," + "pre_redemption_begin_date=" +pre_redemption_begin_date
      + "," + "closing_date_cal_div=" +closing_date_cal_div
      + "," + "closing_spec_date=" +closing_spec_date
      + "," + "annual_profit_qty_times=" +annual_profit_qty_times
      + "," + "sell_advance_order_div=" +sell_advance_order_div
      + "," + "buy_advance_order_div=" +buy_advance_order_div
      + "," + "sell_undelivering_term=" +sell_undelivering_term
      + "," + "buy_undelivering_term=" +buy_undelivering_term
      + "," + "recruit_stop_div=" +recruit_stop_div
      + "," + "dealing_stop_div=" +dealing_stop_div
      + "," + "storage_stop_div=" +storage_stop_div
      + "," + "specific_corpus_app=" +specific_corpus_app
      + "," + "perference_qualify=" +perference_qualify
      + "," + "collateral_qualify=" +collateral_qualify
      + "," + "operate_report_send_div=" +operate_report_send_div
      + "," + "operate_report_send_month1=" +operate_report_send_month1
      + "," + "operate_report_send_month2=" +operate_report_send_month2
      + "," + "biz_asset_code=" +biz_asset_code
      + "," + "biz_asset_name=" +biz_asset_name
      + "," + "prospectus_conversion_date=" +prospectus_conversion_date
      + "," + "frgn_buy_min_amt=" +frgn_buy_min_amt
      + "," + "frgn_sell_min_amt=" +frgn_sell_min_amt
      + "," + "frgn_buy_unit_amt=" +frgn_buy_unit_amt
      + "," + "frgn_sell_unit_amt=" +frgn_sell_unit_amt
      + "}";
  }


  /** 
   * 値が未設定のMutualFund2ndProductSonarParamsオブジェクトを作成します。 
   */
  public MutualFund2ndProductSonarParams() {
    product_code_is_modified = true;
    institution_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のMutualFund2ndProductSonarRowオブジェクトの値を利用してMutualFund2ndProductSonarParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するMutualFund2ndProductSonarRowオブジェクト 
   */
  public MutualFund2ndProductSonarParams( MutualFund2ndProductSonarRow p_row )
  {
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    work_date = p_row.getWorkDate();
    work_date_is_set = p_row.getWorkDateIsSet();
    work_date_is_modified = p_row.getWorkDateIsModified();
    reg_date = p_row.getRegDate();
    reg_date_is_set = p_row.getRegDateIsSet();
    reg_date_is_modified = p_row.getRegDateIsModified();
    last_update_date = p_row.getLastUpdateDate();
    last_update_date_is_set = p_row.getLastUpdateDateIsSet();
    last_update_date_is_modified = p_row.getLastUpdateDateIsModified();
    reg_div = p_row.getRegDiv();
    reg_div_is_set = p_row.getRegDivIsSet();
    reg_div_is_modified = p_row.getRegDivIsModified();
    product_name_kana = p_row.getProductNameKana();
    product_name_kana_is_set = p_row.getProductNameKanaIsSet();
    product_name_kana_is_modified = p_row.getProductNameKanaIsModified();
    product_name_kanji = p_row.getProductNameKanji();
    product_name_kanji_is_set = p_row.getProductNameKanjiIsSet();
    product_name_kanji_is_modified = p_row.getProductNameKanjiIsModified();
    if ( !p_row.getRecruitUnitIsNull() )
      recruit_unit = new Integer( p_row.getRecruitUnit() );
    recruit_unit_is_set = p_row.getRecruitUnitIsSet();
    recruit_unit_is_modified = p_row.getRecruitUnitIsModified();
    if ( !p_row.getDealingUnitQtyIsNull() )
      dealing_unit_qty = new Integer( p_row.getDealingUnitQty() );
    dealing_unit_qty_is_set = p_row.getDealingUnitQtyIsSet();
    dealing_unit_qty_is_modified = p_row.getDealingUnitQtyIsModified();
    if ( !p_row.getRecruitMinQtyIsNull() )
      recruit_min_qty = new Integer( p_row.getRecruitMinQty() );
    recruit_min_qty_is_set = p_row.getRecruitMinQtyIsSet();
    recruit_min_qty_is_modified = p_row.getRecruitMinQtyIsModified();
    if ( !p_row.getBuyMinQtyIsNull() )
      buy_min_qty = new Integer( p_row.getBuyMinQty() );
    buy_min_qty_is_set = p_row.getBuyMinQtyIsSet();
    buy_min_qty_is_modified = p_row.getBuyMinQtyIsModified();
    if ( !p_row.getSellMinQtyIsNull() )
      sell_min_qty = new Integer( p_row.getSellMinQty() );
    sell_min_qty_is_set = p_row.getSellMinQtyIsSet();
    sell_min_qty_is_modified = p_row.getSellMinQtyIsModified();
    if ( !p_row.getSwtMinQtyIsNull() )
      swt_min_qty = new Integer( p_row.getSwtMinQty() );
    swt_min_qty_is_set = p_row.getSwtMinQtyIsSet();
    swt_min_qty_is_modified = p_row.getSwtMinQtyIsModified();
    if ( !p_row.getRecruitUnitQtyIsNull() )
      recruit_unit_qty = new Integer( p_row.getRecruitUnitQty() );
    recruit_unit_qty_is_set = p_row.getRecruitUnitQtyIsSet();
    recruit_unit_qty_is_modified = p_row.getRecruitUnitQtyIsModified();
    if ( !p_row.getBuyUnitQtyIsNull() )
      buy_unit_qty = new Integer( p_row.getBuyUnitQty() );
    buy_unit_qty_is_set = p_row.getBuyUnitQtyIsSet();
    buy_unit_qty_is_modified = p_row.getBuyUnitQtyIsModified();
    if ( !p_row.getSellUnitQtyIsNull() )
      sell_unit_qty = new Integer( p_row.getSellUnitQty() );
    sell_unit_qty_is_set = p_row.getSellUnitQtyIsSet();
    sell_unit_qty_is_modified = p_row.getSellUnitQtyIsModified();
    if ( !p_row.getSwtUnitQtyIsNull() )
      swt_unit_qty = new Integer( p_row.getSwtUnitQty() );
    swt_unit_qty_is_set = p_row.getSwtUnitQtyIsSet();
    swt_unit_qty_is_modified = p_row.getSwtUnitQtyIsModified();
    if ( !p_row.getRecruitMinAmtIsNull() )
      recruit_min_amt = new Integer( p_row.getRecruitMinAmt() );
    recruit_min_amt_is_set = p_row.getRecruitMinAmtIsSet();
    recruit_min_amt_is_modified = p_row.getRecruitMinAmtIsModified();
    if ( !p_row.getBuyMinAmtIsNull() )
      buy_min_amt = new Integer( p_row.getBuyMinAmt() );
    buy_min_amt_is_set = p_row.getBuyMinAmtIsSet();
    buy_min_amt_is_modified = p_row.getBuyMinAmtIsModified();
    if ( !p_row.getSellMinAmtIsNull() )
      sell_min_amt = new Integer( p_row.getSellMinAmt() );
    sell_min_amt_is_set = p_row.getSellMinAmtIsSet();
    sell_min_amt_is_modified = p_row.getSellMinAmtIsModified();
    if ( !p_row.getSwtMinAmtIsNull() )
      swt_min_amt = new Integer( p_row.getSwtMinAmt() );
    swt_min_amt_is_set = p_row.getSwtMinAmtIsSet();
    swt_min_amt_is_modified = p_row.getSwtMinAmtIsModified();
    if ( !p_row.getRecruitUnitAmtIsNull() )
      recruit_unit_amt = new Integer( p_row.getRecruitUnitAmt() );
    recruit_unit_amt_is_set = p_row.getRecruitUnitAmtIsSet();
    recruit_unit_amt_is_modified = p_row.getRecruitUnitAmtIsModified();
    if ( !p_row.getBuyUnitAmtIsNull() )
      buy_unit_amt = new Integer( p_row.getBuyUnitAmt() );
    buy_unit_amt_is_set = p_row.getBuyUnitAmtIsSet();
    buy_unit_amt_is_modified = p_row.getBuyUnitAmtIsModified();
    if ( !p_row.getSellUnitAmtIsNull() )
      sell_unit_amt = new Integer( p_row.getSellUnitAmt() );
    sell_unit_amt_is_set = p_row.getSellUnitAmtIsSet();
    sell_unit_amt_is_modified = p_row.getSellUnitAmtIsModified();
    if ( !p_row.getSwtUnitAmtIsNull() )
      swt_unit_amt = new Integer( p_row.getSwtUnitAmt() );
    swt_unit_amt_is_set = p_row.getSwtUnitAmtIsSet();
    swt_unit_amt_is_modified = p_row.getSwtUnitAmtIsModified();
    recruit_qty_spec_div = p_row.getRecruitQtySpecDiv();
    recruit_qty_spec_div_is_set = p_row.getRecruitQtySpecDivIsSet();
    recruit_qty_spec_div_is_modified = p_row.getRecruitQtySpecDivIsModified();
    recruit_amt_spec_div = p_row.getRecruitAmtSpecDiv();
    recruit_amt_spec_div_is_set = p_row.getRecruitAmtSpecDivIsSet();
    recruit_amt_spec_div_is_modified = p_row.getRecruitAmtSpecDivIsModified();
    buy_qty_spec_div = p_row.getBuyQtySpecDiv();
    buy_qty_spec_div_is_set = p_row.getBuyQtySpecDivIsSet();
    buy_qty_spec_div_is_modified = p_row.getBuyQtySpecDivIsModified();
    buy_amt_spec_div = p_row.getBuyAmtSpecDiv();
    buy_amt_spec_div_is_set = p_row.getBuyAmtSpecDivIsSet();
    buy_amt_spec_div_is_modified = p_row.getBuyAmtSpecDivIsModified();
    sell_qty_spec_div = p_row.getSellQtySpecDiv();
    sell_qty_spec_div_is_set = p_row.getSellQtySpecDivIsSet();
    sell_qty_spec_div_is_modified = p_row.getSellQtySpecDivIsModified();
    sell_amt_spec_div = p_row.getSellAmtSpecDiv();
    sell_amt_spec_div_is_set = p_row.getSellAmtSpecDivIsSet();
    sell_amt_spec_div_is_modified = p_row.getSellAmtSpecDivIsModified();
    swt_qty_spec_div = p_row.getSwtQtySpecDiv();
    swt_qty_spec_div_is_set = p_row.getSwtQtySpecDivIsSet();
    swt_qty_spec_div_is_modified = p_row.getSwtQtySpecDivIsModified();
    swt_amt_spec_div = p_row.getSwtAmtSpecDiv();
    swt_amt_spec_div_is_set = p_row.getSwtAmtSpecDivIsSet();
    swt_amt_spec_div_is_modified = p_row.getSwtAmtSpecDivIsModified();
    input_unit_div = p_row.getInputUnitDiv();
    input_unit_div_is_set = p_row.getInputUnitDivIsSet();
    input_unit_div_is_modified = p_row.getInputUnitDivIsModified();
    cal_price_div = p_row.getCalPriceDiv();
    cal_price_div_is_set = p_row.getCalPriceDivIsSet();
    cal_price_div_is_modified = p_row.getCalPriceDivIsModified();
    sell_exception_div = p_row.getSellExceptionDiv();
    sell_exception_div_is_set = p_row.getSellExceptionDivIsSet();
    sell_exception_div_is_modified = p_row.getSellExceptionDivIsModified();
    swt_trade_div = p_row.getSwtTradeDiv();
    swt_trade_div_is_set = p_row.getSwtTradeDivIsSet();
    swt_trade_div_is_modified = p_row.getSwtTradeDivIsModified();
    swt_group = p_row.getSwtGroup();
    swt_group_is_set = p_row.getSwtGroupIsSet();
    swt_group_is_modified = p_row.getSwtGroupIsModified();
    swt_companion_subject_div = p_row.getSwtCompanionSubjectDiv();
    swt_companion_subject_div_is_set = p_row.getSwtCompanionSubjectDivIsSet();
    swt_companion_subject_div_is_modified = p_row.getSwtCompanionSubjectDivIsModified();
    buy_disable_div = p_row.getBuyDisableDiv();
    buy_disable_div_is_set = p_row.getBuyDisableDivIsSet();
    buy_disable_div_is_modified = p_row.getBuyDisableDivIsModified();
    swt_perference_enable_div = p_row.getSwtPerferenceEnableDiv();
    swt_perference_enable_div_is_set = p_row.getSwtPerferenceEnableDivIsSet();
    swt_perference_enable_div_is_modified = p_row.getSwtPerferenceEnableDivIsModified();
    if ( !p_row.getRedemptionPerferencePriceIsNull() )
      redemption_perference_price = new Double( p_row.getRedemptionPerferencePrice() );
    redemption_perference_price_is_set = p_row.getRedemptionPerferencePriceIsSet();
    redemption_perference_price_is_modified = p_row.getRedemptionPerferencePriceIsModified();
    if ( !p_row.getRedemptionCommissionRateIsNull() )
      redemption_commission_rate = new Double( p_row.getRedemptionCommissionRate() );
    redemption_commission_rate_is_set = p_row.getRedemptionCommissionRateIsSet();
    redemption_commission_rate_is_modified = p_row.getRedemptionCommissionRateIsModified();
    pre_redemption_begin_date = p_row.getPreRedemptionBeginDate();
    pre_redemption_begin_date_is_set = p_row.getPreRedemptionBeginDateIsSet();
    pre_redemption_begin_date_is_modified = p_row.getPreRedemptionBeginDateIsModified();
    closing_date_cal_div = p_row.getClosingDateCalDiv();
    closing_date_cal_div_is_set = p_row.getClosingDateCalDivIsSet();
    closing_date_cal_div_is_modified = p_row.getClosingDateCalDivIsModified();
    closing_spec_date = p_row.getClosingSpecDate();
    closing_spec_date_is_set = p_row.getClosingSpecDateIsSet();
    closing_spec_date_is_modified = p_row.getClosingSpecDateIsModified();
    annual_profit_qty_times = p_row.getAnnualProfitQtyTimes();
    annual_profit_qty_times_is_set = p_row.getAnnualProfitQtyTimesIsSet();
    annual_profit_qty_times_is_modified = p_row.getAnnualProfitQtyTimesIsModified();
    sell_advance_order_div = p_row.getSellAdvanceOrderDiv();
    sell_advance_order_div_is_set = p_row.getSellAdvanceOrderDivIsSet();
    sell_advance_order_div_is_modified = p_row.getSellAdvanceOrderDivIsModified();
    buy_advance_order_div = p_row.getBuyAdvanceOrderDiv();
    buy_advance_order_div_is_set = p_row.getBuyAdvanceOrderDivIsSet();
    buy_advance_order_div_is_modified = p_row.getBuyAdvanceOrderDivIsModified();
    sell_undelivering_term = p_row.getSellUndeliveringTerm();
    sell_undelivering_term_is_set = p_row.getSellUndeliveringTermIsSet();
    sell_undelivering_term_is_modified = p_row.getSellUndeliveringTermIsModified();
    buy_undelivering_term = p_row.getBuyUndeliveringTerm();
    buy_undelivering_term_is_set = p_row.getBuyUndeliveringTermIsSet();
    buy_undelivering_term_is_modified = p_row.getBuyUndeliveringTermIsModified();
    recruit_stop_div = p_row.getRecruitStopDiv();
    recruit_stop_div_is_set = p_row.getRecruitStopDivIsSet();
    recruit_stop_div_is_modified = p_row.getRecruitStopDivIsModified();
    dealing_stop_div = p_row.getDealingStopDiv();
    dealing_stop_div_is_set = p_row.getDealingStopDivIsSet();
    dealing_stop_div_is_modified = p_row.getDealingStopDivIsModified();
    storage_stop_div = p_row.getStorageStopDiv();
    storage_stop_div_is_set = p_row.getStorageStopDivIsSet();
    storage_stop_div_is_modified = p_row.getStorageStopDivIsModified();
    specific_corpus_app = p_row.getSpecificCorpusApp();
    specific_corpus_app_is_set = p_row.getSpecificCorpusAppIsSet();
    specific_corpus_app_is_modified = p_row.getSpecificCorpusAppIsModified();
    perference_qualify = p_row.getPerferenceQualify();
    perference_qualify_is_set = p_row.getPerferenceQualifyIsSet();
    perference_qualify_is_modified = p_row.getPerferenceQualifyIsModified();
    collateral_qualify = p_row.getCollateralQualify();
    collateral_qualify_is_set = p_row.getCollateralQualifyIsSet();
    collateral_qualify_is_modified = p_row.getCollateralQualifyIsModified();
    operate_report_send_div = p_row.getOperateReportSendDiv();
    operate_report_send_div_is_set = p_row.getOperateReportSendDivIsSet();
    operate_report_send_div_is_modified = p_row.getOperateReportSendDivIsModified();
    operate_report_send_month1 = p_row.getOperateReportSendMonth1();
    operate_report_send_month1_is_set = p_row.getOperateReportSendMonth1IsSet();
    operate_report_send_month1_is_modified = p_row.getOperateReportSendMonth1IsModified();
    operate_report_send_month2 = p_row.getOperateReportSendMonth2();
    operate_report_send_month2_is_set = p_row.getOperateReportSendMonth2IsSet();
    operate_report_send_month2_is_modified = p_row.getOperateReportSendMonth2IsModified();
    biz_asset_code = p_row.getBizAssetCode();
    biz_asset_code_is_set = p_row.getBizAssetCodeIsSet();
    biz_asset_code_is_modified = p_row.getBizAssetCodeIsModified();
    biz_asset_name = p_row.getBizAssetName();
    biz_asset_name_is_set = p_row.getBizAssetNameIsSet();
    biz_asset_name_is_modified = p_row.getBizAssetNameIsModified();
    prospectus_conversion_date = p_row.getProspectusConversionDate();
    prospectus_conversion_date_is_set = p_row.getProspectusConversionDateIsSet();
    prospectus_conversion_date_is_modified = p_row.getProspectusConversionDateIsModified();
    if ( !p_row.getFrgnBuyMinAmtIsNull() )
      frgn_buy_min_amt = new Integer( p_row.getFrgnBuyMinAmt() );
    frgn_buy_min_amt_is_set = p_row.getFrgnBuyMinAmtIsSet();
    frgn_buy_min_amt_is_modified = p_row.getFrgnBuyMinAmtIsModified();
    if ( !p_row.getFrgnSellMinAmtIsNull() )
      frgn_sell_min_amt = new Integer( p_row.getFrgnSellMinAmt() );
    frgn_sell_min_amt_is_set = p_row.getFrgnSellMinAmtIsSet();
    frgn_sell_min_amt_is_modified = p_row.getFrgnSellMinAmtIsModified();
    if ( !p_row.getFrgnBuyUnitAmtIsNull() )
      frgn_buy_unit_amt = new Integer( p_row.getFrgnBuyUnitAmt() );
    frgn_buy_unit_amt_is_set = p_row.getFrgnBuyUnitAmtIsSet();
    frgn_buy_unit_amt_is_modified = p_row.getFrgnBuyUnitAmtIsModified();
    if ( !p_row.getFrgnSellUnitAmtIsNull() )
      frgn_sell_unit_amt = new Integer( p_row.getFrgnSellUnitAmt() );
    frgn_sell_unit_amt_is_set = p_row.getFrgnSellUnitAmtIsSet();
    frgn_sell_unit_amt_is_modified = p_row.getFrgnSellUnitAmtIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      work_date_is_set = true;
      work_date_is_modified = true;
      reg_date_is_set = true;
      reg_date_is_modified = true;
      last_update_date_is_set = true;
      last_update_date_is_modified = true;
      reg_div_is_set = true;
      reg_div_is_modified = true;
      product_name_kana_is_set = true;
      product_name_kana_is_modified = true;
      product_name_kanji_is_set = true;
      product_name_kanji_is_modified = true;
      recruit_unit_is_set = true;
      recruit_unit_is_modified = true;
      dealing_unit_qty_is_set = true;
      dealing_unit_qty_is_modified = true;
      recruit_min_qty_is_set = true;
      recruit_min_qty_is_modified = true;
      buy_min_qty_is_set = true;
      buy_min_qty_is_modified = true;
      sell_min_qty_is_set = true;
      sell_min_qty_is_modified = true;
      swt_min_qty_is_set = true;
      swt_min_qty_is_modified = true;
      recruit_unit_qty_is_set = true;
      recruit_unit_qty_is_modified = true;
      buy_unit_qty_is_set = true;
      buy_unit_qty_is_modified = true;
      sell_unit_qty_is_set = true;
      sell_unit_qty_is_modified = true;
      swt_unit_qty_is_set = true;
      swt_unit_qty_is_modified = true;
      recruit_min_amt_is_set = true;
      recruit_min_amt_is_modified = true;
      buy_min_amt_is_set = true;
      buy_min_amt_is_modified = true;
      sell_min_amt_is_set = true;
      sell_min_amt_is_modified = true;
      swt_min_amt_is_set = true;
      swt_min_amt_is_modified = true;
      recruit_unit_amt_is_set = true;
      recruit_unit_amt_is_modified = true;
      buy_unit_amt_is_set = true;
      buy_unit_amt_is_modified = true;
      sell_unit_amt_is_set = true;
      sell_unit_amt_is_modified = true;
      swt_unit_amt_is_set = true;
      swt_unit_amt_is_modified = true;
      recruit_qty_spec_div_is_set = true;
      recruit_qty_spec_div_is_modified = true;
      recruit_amt_spec_div_is_set = true;
      recruit_amt_spec_div_is_modified = true;
      buy_qty_spec_div_is_set = true;
      buy_qty_spec_div_is_modified = true;
      buy_amt_spec_div_is_set = true;
      buy_amt_spec_div_is_modified = true;
      sell_qty_spec_div_is_set = true;
      sell_qty_spec_div_is_modified = true;
      sell_amt_spec_div_is_set = true;
      sell_amt_spec_div_is_modified = true;
      swt_qty_spec_div_is_set = true;
      swt_qty_spec_div_is_modified = true;
      swt_amt_spec_div_is_set = true;
      swt_amt_spec_div_is_modified = true;
      input_unit_div_is_set = true;
      input_unit_div_is_modified = true;
      cal_price_div_is_set = true;
      cal_price_div_is_modified = true;
      sell_exception_div_is_set = true;
      sell_exception_div_is_modified = true;
      swt_trade_div_is_set = true;
      swt_trade_div_is_modified = true;
      swt_group_is_set = true;
      swt_group_is_modified = true;
      swt_companion_subject_div_is_set = true;
      swt_companion_subject_div_is_modified = true;
      buy_disable_div_is_set = true;
      buy_disable_div_is_modified = true;
      swt_perference_enable_div_is_set = true;
      swt_perference_enable_div_is_modified = true;
      redemption_perference_price_is_set = true;
      redemption_perference_price_is_modified = true;
      redemption_commission_rate_is_set = true;
      redemption_commission_rate_is_modified = true;
      pre_redemption_begin_date_is_set = true;
      pre_redemption_begin_date_is_modified = true;
      closing_date_cal_div_is_set = true;
      closing_date_cal_div_is_modified = true;
      closing_spec_date_is_set = true;
      closing_spec_date_is_modified = true;
      annual_profit_qty_times_is_set = true;
      annual_profit_qty_times_is_modified = true;
      sell_advance_order_div_is_set = true;
      sell_advance_order_div_is_modified = true;
      buy_advance_order_div_is_set = true;
      buy_advance_order_div_is_modified = true;
      sell_undelivering_term_is_set = true;
      sell_undelivering_term_is_modified = true;
      buy_undelivering_term_is_set = true;
      buy_undelivering_term_is_modified = true;
      recruit_stop_div_is_set = true;
      recruit_stop_div_is_modified = true;
      dealing_stop_div_is_set = true;
      dealing_stop_div_is_modified = true;
      storage_stop_div_is_set = true;
      storage_stop_div_is_modified = true;
      specific_corpus_app_is_set = true;
      specific_corpus_app_is_modified = true;
      perference_qualify_is_set = true;
      perference_qualify_is_modified = true;
      collateral_qualify_is_set = true;
      collateral_qualify_is_modified = true;
      operate_report_send_div_is_set = true;
      operate_report_send_div_is_modified = true;
      operate_report_send_month1_is_set = true;
      operate_report_send_month1_is_modified = true;
      operate_report_send_month2_is_set = true;
      operate_report_send_month2_is_modified = true;
      biz_asset_code_is_set = true;
      biz_asset_code_is_modified = true;
      biz_asset_name_is_set = true;
      biz_asset_name_is_modified = true;
      prospectus_conversion_date_is_set = true;
      prospectus_conversion_date_is_modified = true;
      frgn_buy_min_amt_is_set = true;
      frgn_buy_min_amt_is_modified = true;
      frgn_sell_min_amt_is_set = true;
      frgn_sell_min_amt_is_modified = true;
      frgn_buy_unit_amt_is_set = true;
      frgn_buy_unit_amt_is_modified = true;
      frgn_sell_unit_amt_is_set = true;
      frgn_sell_unit_amt_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof MutualFund2ndProductSonarRow ) )
       return false;
    return fieldsEqual( (MutualFund2ndProductSonarRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のMutualFund2ndProductSonarRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( MutualFund2ndProductSonarRow row )
  {
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( work_date == null ) {
      if ( row.getWorkDate() != null )
        return false;
    } else if ( !work_date.equals( row.getWorkDate() ) ) {
        return false;
    }
    if ( reg_date == null ) {
      if ( row.getRegDate() != null )
        return false;
    } else if ( !reg_date.equals( row.getRegDate() ) ) {
        return false;
    }
    if ( last_update_date == null ) {
      if ( row.getLastUpdateDate() != null )
        return false;
    } else if ( !last_update_date.equals( row.getLastUpdateDate() ) ) {
        return false;
    }
    if ( reg_div == null ) {
      if ( row.getRegDiv() != null )
        return false;
    } else if ( !reg_div.equals( row.getRegDiv() ) ) {
        return false;
    }
    if ( product_name_kana == null ) {
      if ( row.getProductNameKana() != null )
        return false;
    } else if ( !product_name_kana.equals( row.getProductNameKana() ) ) {
        return false;
    }
    if ( product_name_kanji == null ) {
      if ( row.getProductNameKanji() != null )
        return false;
    } else if ( !product_name_kanji.equals( row.getProductNameKanji() ) ) {
        return false;
    }
    if ( recruit_unit == null ) {
      if ( !row.getRecruitUnitIsNull() )
        return false;
    } else if ( row.getRecruitUnitIsNull() || ( recruit_unit.intValue() != row.getRecruitUnit() ) ) {
        return false;
    }
    if ( dealing_unit_qty == null ) {
      if ( !row.getDealingUnitQtyIsNull() )
        return false;
    } else if ( row.getDealingUnitQtyIsNull() || ( dealing_unit_qty.intValue() != row.getDealingUnitQty() ) ) {
        return false;
    }
    if ( recruit_min_qty == null ) {
      if ( !row.getRecruitMinQtyIsNull() )
        return false;
    } else if ( row.getRecruitMinQtyIsNull() || ( recruit_min_qty.intValue() != row.getRecruitMinQty() ) ) {
        return false;
    }
    if ( buy_min_qty == null ) {
      if ( !row.getBuyMinQtyIsNull() )
        return false;
    } else if ( row.getBuyMinQtyIsNull() || ( buy_min_qty.intValue() != row.getBuyMinQty() ) ) {
        return false;
    }
    if ( sell_min_qty == null ) {
      if ( !row.getSellMinQtyIsNull() )
        return false;
    } else if ( row.getSellMinQtyIsNull() || ( sell_min_qty.intValue() != row.getSellMinQty() ) ) {
        return false;
    }
    if ( swt_min_qty == null ) {
      if ( !row.getSwtMinQtyIsNull() )
        return false;
    } else if ( row.getSwtMinQtyIsNull() || ( swt_min_qty.intValue() != row.getSwtMinQty() ) ) {
        return false;
    }
    if ( recruit_unit_qty == null ) {
      if ( !row.getRecruitUnitQtyIsNull() )
        return false;
    } else if ( row.getRecruitUnitQtyIsNull() || ( recruit_unit_qty.intValue() != row.getRecruitUnitQty() ) ) {
        return false;
    }
    if ( buy_unit_qty == null ) {
      if ( !row.getBuyUnitQtyIsNull() )
        return false;
    } else if ( row.getBuyUnitQtyIsNull() || ( buy_unit_qty.intValue() != row.getBuyUnitQty() ) ) {
        return false;
    }
    if ( sell_unit_qty == null ) {
      if ( !row.getSellUnitQtyIsNull() )
        return false;
    } else if ( row.getSellUnitQtyIsNull() || ( sell_unit_qty.intValue() != row.getSellUnitQty() ) ) {
        return false;
    }
    if ( swt_unit_qty == null ) {
      if ( !row.getSwtUnitQtyIsNull() )
        return false;
    } else if ( row.getSwtUnitQtyIsNull() || ( swt_unit_qty.intValue() != row.getSwtUnitQty() ) ) {
        return false;
    }
    if ( recruit_min_amt == null ) {
      if ( !row.getRecruitMinAmtIsNull() )
        return false;
    } else if ( row.getRecruitMinAmtIsNull() || ( recruit_min_amt.intValue() != row.getRecruitMinAmt() ) ) {
        return false;
    }
    if ( buy_min_amt == null ) {
      if ( !row.getBuyMinAmtIsNull() )
        return false;
    } else if ( row.getBuyMinAmtIsNull() || ( buy_min_amt.intValue() != row.getBuyMinAmt() ) ) {
        return false;
    }
    if ( sell_min_amt == null ) {
      if ( !row.getSellMinAmtIsNull() )
        return false;
    } else if ( row.getSellMinAmtIsNull() || ( sell_min_amt.intValue() != row.getSellMinAmt() ) ) {
        return false;
    }
    if ( swt_min_amt == null ) {
      if ( !row.getSwtMinAmtIsNull() )
        return false;
    } else if ( row.getSwtMinAmtIsNull() || ( swt_min_amt.intValue() != row.getSwtMinAmt() ) ) {
        return false;
    }
    if ( recruit_unit_amt == null ) {
      if ( !row.getRecruitUnitAmtIsNull() )
        return false;
    } else if ( row.getRecruitUnitAmtIsNull() || ( recruit_unit_amt.intValue() != row.getRecruitUnitAmt() ) ) {
        return false;
    }
    if ( buy_unit_amt == null ) {
      if ( !row.getBuyUnitAmtIsNull() )
        return false;
    } else if ( row.getBuyUnitAmtIsNull() || ( buy_unit_amt.intValue() != row.getBuyUnitAmt() ) ) {
        return false;
    }
    if ( sell_unit_amt == null ) {
      if ( !row.getSellUnitAmtIsNull() )
        return false;
    } else if ( row.getSellUnitAmtIsNull() || ( sell_unit_amt.intValue() != row.getSellUnitAmt() ) ) {
        return false;
    }
    if ( swt_unit_amt == null ) {
      if ( !row.getSwtUnitAmtIsNull() )
        return false;
    } else if ( row.getSwtUnitAmtIsNull() || ( swt_unit_amt.intValue() != row.getSwtUnitAmt() ) ) {
        return false;
    }
    if ( recruit_qty_spec_div == null ) {
      if ( row.getRecruitQtySpecDiv() != null )
        return false;
    } else if ( !recruit_qty_spec_div.equals( row.getRecruitQtySpecDiv() ) ) {
        return false;
    }
    if ( recruit_amt_spec_div == null ) {
      if ( row.getRecruitAmtSpecDiv() != null )
        return false;
    } else if ( !recruit_amt_spec_div.equals( row.getRecruitAmtSpecDiv() ) ) {
        return false;
    }
    if ( buy_qty_spec_div == null ) {
      if ( row.getBuyQtySpecDiv() != null )
        return false;
    } else if ( !buy_qty_spec_div.equals( row.getBuyQtySpecDiv() ) ) {
        return false;
    }
    if ( buy_amt_spec_div == null ) {
      if ( row.getBuyAmtSpecDiv() != null )
        return false;
    } else if ( !buy_amt_spec_div.equals( row.getBuyAmtSpecDiv() ) ) {
        return false;
    }
    if ( sell_qty_spec_div == null ) {
      if ( row.getSellQtySpecDiv() != null )
        return false;
    } else if ( !sell_qty_spec_div.equals( row.getSellQtySpecDiv() ) ) {
        return false;
    }
    if ( sell_amt_spec_div == null ) {
      if ( row.getSellAmtSpecDiv() != null )
        return false;
    } else if ( !sell_amt_spec_div.equals( row.getSellAmtSpecDiv() ) ) {
        return false;
    }
    if ( swt_qty_spec_div == null ) {
      if ( row.getSwtQtySpecDiv() != null )
        return false;
    } else if ( !swt_qty_spec_div.equals( row.getSwtQtySpecDiv() ) ) {
        return false;
    }
    if ( swt_amt_spec_div == null ) {
      if ( row.getSwtAmtSpecDiv() != null )
        return false;
    } else if ( !swt_amt_spec_div.equals( row.getSwtAmtSpecDiv() ) ) {
        return false;
    }
    if ( input_unit_div == null ) {
      if ( row.getInputUnitDiv() != null )
        return false;
    } else if ( !input_unit_div.equals( row.getInputUnitDiv() ) ) {
        return false;
    }
    if ( cal_price_div == null ) {
      if ( row.getCalPriceDiv() != null )
        return false;
    } else if ( !cal_price_div.equals( row.getCalPriceDiv() ) ) {
        return false;
    }
    if ( sell_exception_div == null ) {
      if ( row.getSellExceptionDiv() != null )
        return false;
    } else if ( !sell_exception_div.equals( row.getSellExceptionDiv() ) ) {
        return false;
    }
    if ( swt_trade_div == null ) {
      if ( row.getSwtTradeDiv() != null )
        return false;
    } else if ( !swt_trade_div.equals( row.getSwtTradeDiv() ) ) {
        return false;
    }
    if ( swt_group == null ) {
      if ( row.getSwtGroup() != null )
        return false;
    } else if ( !swt_group.equals( row.getSwtGroup() ) ) {
        return false;
    }
    if ( swt_companion_subject_div == null ) {
      if ( row.getSwtCompanionSubjectDiv() != null )
        return false;
    } else if ( !swt_companion_subject_div.equals( row.getSwtCompanionSubjectDiv() ) ) {
        return false;
    }
    if ( buy_disable_div == null ) {
      if ( row.getBuyDisableDiv() != null )
        return false;
    } else if ( !buy_disable_div.equals( row.getBuyDisableDiv() ) ) {
        return false;
    }
    if ( swt_perference_enable_div == null ) {
      if ( row.getSwtPerferenceEnableDiv() != null )
        return false;
    } else if ( !swt_perference_enable_div.equals( row.getSwtPerferenceEnableDiv() ) ) {
        return false;
    }
    if ( redemption_perference_price == null ) {
      if ( !row.getRedemptionPerferencePriceIsNull() )
        return false;
    } else if ( row.getRedemptionPerferencePriceIsNull() || ( redemption_perference_price.doubleValue() != row.getRedemptionPerferencePrice() ) ) {
        return false;
    }
    if ( redemption_commission_rate == null ) {
      if ( !row.getRedemptionCommissionRateIsNull() )
        return false;
    } else if ( row.getRedemptionCommissionRateIsNull() || ( redemption_commission_rate.doubleValue() != row.getRedemptionCommissionRate() ) ) {
        return false;
    }
    if ( pre_redemption_begin_date == null ) {
      if ( row.getPreRedemptionBeginDate() != null )
        return false;
    } else if ( !pre_redemption_begin_date.equals( row.getPreRedemptionBeginDate() ) ) {
        return false;
    }
    if ( closing_date_cal_div == null ) {
      if ( row.getClosingDateCalDiv() != null )
        return false;
    } else if ( !closing_date_cal_div.equals( row.getClosingDateCalDiv() ) ) {
        return false;
    }
    if ( closing_spec_date == null ) {
      if ( row.getClosingSpecDate() != null )
        return false;
    } else if ( !closing_spec_date.equals( row.getClosingSpecDate() ) ) {
        return false;
    }
    if ( annual_profit_qty_times == null ) {
      if ( row.getAnnualProfitQtyTimes() != null )
        return false;
    } else if ( !annual_profit_qty_times.equals( row.getAnnualProfitQtyTimes() ) ) {
        return false;
    }
    if ( sell_advance_order_div == null ) {
      if ( row.getSellAdvanceOrderDiv() != null )
        return false;
    } else if ( !sell_advance_order_div.equals( row.getSellAdvanceOrderDiv() ) ) {
        return false;
    }
    if ( buy_advance_order_div == null ) {
      if ( row.getBuyAdvanceOrderDiv() != null )
        return false;
    } else if ( !buy_advance_order_div.equals( row.getBuyAdvanceOrderDiv() ) ) {
        return false;
    }
    if ( sell_undelivering_term == null ) {
      if ( row.getSellUndeliveringTerm() != null )
        return false;
    } else if ( !sell_undelivering_term.equals( row.getSellUndeliveringTerm() ) ) {
        return false;
    }
    if ( buy_undelivering_term == null ) {
      if ( row.getBuyUndeliveringTerm() != null )
        return false;
    } else if ( !buy_undelivering_term.equals( row.getBuyUndeliveringTerm() ) ) {
        return false;
    }
    if ( recruit_stop_div == null ) {
      if ( row.getRecruitStopDiv() != null )
        return false;
    } else if ( !recruit_stop_div.equals( row.getRecruitStopDiv() ) ) {
        return false;
    }
    if ( dealing_stop_div == null ) {
      if ( row.getDealingStopDiv() != null )
        return false;
    } else if ( !dealing_stop_div.equals( row.getDealingStopDiv() ) ) {
        return false;
    }
    if ( storage_stop_div == null ) {
      if ( row.getStorageStopDiv() != null )
        return false;
    } else if ( !storage_stop_div.equals( row.getStorageStopDiv() ) ) {
        return false;
    }
    if ( specific_corpus_app == null ) {
      if ( row.getSpecificCorpusApp() != null )
        return false;
    } else if ( !specific_corpus_app.equals( row.getSpecificCorpusApp() ) ) {
        return false;
    }
    if ( perference_qualify == null ) {
      if ( row.getPerferenceQualify() != null )
        return false;
    } else if ( !perference_qualify.equals( row.getPerferenceQualify() ) ) {
        return false;
    }
    if ( collateral_qualify == null ) {
      if ( row.getCollateralQualify() != null )
        return false;
    } else if ( !collateral_qualify.equals( row.getCollateralQualify() ) ) {
        return false;
    }
    if ( operate_report_send_div == null ) {
      if ( row.getOperateReportSendDiv() != null )
        return false;
    } else if ( !operate_report_send_div.equals( row.getOperateReportSendDiv() ) ) {
        return false;
    }
    if ( operate_report_send_month1 == null ) {
      if ( row.getOperateReportSendMonth1() != null )
        return false;
    } else if ( !operate_report_send_month1.equals( row.getOperateReportSendMonth1() ) ) {
        return false;
    }
    if ( operate_report_send_month2 == null ) {
      if ( row.getOperateReportSendMonth2() != null )
        return false;
    } else if ( !operate_report_send_month2.equals( row.getOperateReportSendMonth2() ) ) {
        return false;
    }
    if ( biz_asset_code == null ) {
      if ( row.getBizAssetCode() != null )
        return false;
    } else if ( !biz_asset_code.equals( row.getBizAssetCode() ) ) {
        return false;
    }
    if ( biz_asset_name == null ) {
      if ( row.getBizAssetName() != null )
        return false;
    } else if ( !biz_asset_name.equals( row.getBizAssetName() ) ) {
        return false;
    }
    if ( prospectus_conversion_date == null ) {
      if ( row.getProspectusConversionDate() != null )
        return false;
    } else if ( !prospectus_conversion_date.equals( row.getProspectusConversionDate() ) ) {
        return false;
    }
    if ( frgn_buy_min_amt == null ) {
      if ( !row.getFrgnBuyMinAmtIsNull() )
        return false;
    } else if ( row.getFrgnBuyMinAmtIsNull() || ( frgn_buy_min_amt.intValue() != row.getFrgnBuyMinAmt() ) ) {
        return false;
    }
    if ( frgn_sell_min_amt == null ) {
      if ( !row.getFrgnSellMinAmtIsNull() )
        return false;
    } else if ( row.getFrgnSellMinAmtIsNull() || ( frgn_sell_min_amt.intValue() != row.getFrgnSellMinAmt() ) ) {
        return false;
    }
    if ( frgn_buy_unit_amt == null ) {
      if ( !row.getFrgnBuyUnitAmtIsNull() )
        return false;
    } else if ( row.getFrgnBuyUnitAmtIsNull() || ( frgn_buy_unit_amt.intValue() != row.getFrgnBuyUnitAmt() ) ) {
        return false;
    }
    if ( frgn_sell_unit_amt == null ) {
      if ( !row.getFrgnSellUnitAmtIsNull() )
        return false;
    } else if ( row.getFrgnSellUnitAmtIsNull() || ( frgn_sell_unit_amt.intValue() != row.getFrgnSellUnitAmt() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (product_code!=null? product_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (work_date!=null? work_date.hashCode(): 0) 
        + (reg_date!=null? reg_date.hashCode(): 0) 
        + (last_update_date!=null? last_update_date.hashCode(): 0) 
        + (reg_div!=null? reg_div.hashCode(): 0) 
        + (product_name_kana!=null? product_name_kana.hashCode(): 0) 
        + (product_name_kanji!=null? product_name_kanji.hashCode(): 0) 
        + (recruit_unit!=null? recruit_unit.hashCode(): 0) 
        + (dealing_unit_qty!=null? dealing_unit_qty.hashCode(): 0) 
        + (recruit_min_qty!=null? recruit_min_qty.hashCode(): 0) 
        + (buy_min_qty!=null? buy_min_qty.hashCode(): 0) 
        + (sell_min_qty!=null? sell_min_qty.hashCode(): 0) 
        + (swt_min_qty!=null? swt_min_qty.hashCode(): 0) 
        + (recruit_unit_qty!=null? recruit_unit_qty.hashCode(): 0) 
        + (buy_unit_qty!=null? buy_unit_qty.hashCode(): 0) 
        + (sell_unit_qty!=null? sell_unit_qty.hashCode(): 0) 
        + (swt_unit_qty!=null? swt_unit_qty.hashCode(): 0) 
        + (recruit_min_amt!=null? recruit_min_amt.hashCode(): 0) 
        + (buy_min_amt!=null? buy_min_amt.hashCode(): 0) 
        + (sell_min_amt!=null? sell_min_amt.hashCode(): 0) 
        + (swt_min_amt!=null? swt_min_amt.hashCode(): 0) 
        + (recruit_unit_amt!=null? recruit_unit_amt.hashCode(): 0) 
        + (buy_unit_amt!=null? buy_unit_amt.hashCode(): 0) 
        + (sell_unit_amt!=null? sell_unit_amt.hashCode(): 0) 
        + (swt_unit_amt!=null? swt_unit_amt.hashCode(): 0) 
        + (recruit_qty_spec_div!=null? recruit_qty_spec_div.hashCode(): 0) 
        + (recruit_amt_spec_div!=null? recruit_amt_spec_div.hashCode(): 0) 
        + (buy_qty_spec_div!=null? buy_qty_spec_div.hashCode(): 0) 
        + (buy_amt_spec_div!=null? buy_amt_spec_div.hashCode(): 0) 
        + (sell_qty_spec_div!=null? sell_qty_spec_div.hashCode(): 0) 
        + (sell_amt_spec_div!=null? sell_amt_spec_div.hashCode(): 0) 
        + (swt_qty_spec_div!=null? swt_qty_spec_div.hashCode(): 0) 
        + (swt_amt_spec_div!=null? swt_amt_spec_div.hashCode(): 0) 
        + (input_unit_div!=null? input_unit_div.hashCode(): 0) 
        + (cal_price_div!=null? cal_price_div.hashCode(): 0) 
        + (sell_exception_div!=null? sell_exception_div.hashCode(): 0) 
        + (swt_trade_div!=null? swt_trade_div.hashCode(): 0) 
        + (swt_group!=null? swt_group.hashCode(): 0) 
        + (swt_companion_subject_div!=null? swt_companion_subject_div.hashCode(): 0) 
        + (buy_disable_div!=null? buy_disable_div.hashCode(): 0) 
        + (swt_perference_enable_div!=null? swt_perference_enable_div.hashCode(): 0) 
        + (redemption_perference_price!=null? redemption_perference_price.hashCode(): 0) 
        + (redemption_commission_rate!=null? redemption_commission_rate.hashCode(): 0) 
        + (pre_redemption_begin_date!=null? pre_redemption_begin_date.hashCode(): 0) 
        + (closing_date_cal_div!=null? closing_date_cal_div.hashCode(): 0) 
        + (closing_spec_date!=null? closing_spec_date.hashCode(): 0) 
        + (annual_profit_qty_times!=null? annual_profit_qty_times.hashCode(): 0) 
        + (sell_advance_order_div!=null? sell_advance_order_div.hashCode(): 0) 
        + (buy_advance_order_div!=null? buy_advance_order_div.hashCode(): 0) 
        + (sell_undelivering_term!=null? sell_undelivering_term.hashCode(): 0) 
        + (buy_undelivering_term!=null? buy_undelivering_term.hashCode(): 0) 
        + (recruit_stop_div!=null? recruit_stop_div.hashCode(): 0) 
        + (dealing_stop_div!=null? dealing_stop_div.hashCode(): 0) 
        + (storage_stop_div!=null? storage_stop_div.hashCode(): 0) 
        + (specific_corpus_app!=null? specific_corpus_app.hashCode(): 0) 
        + (perference_qualify!=null? perference_qualify.hashCode(): 0) 
        + (collateral_qualify!=null? collateral_qualify.hashCode(): 0) 
        + (operate_report_send_div!=null? operate_report_send_div.hashCode(): 0) 
        + (operate_report_send_month1!=null? operate_report_send_month1.hashCode(): 0) 
        + (operate_report_send_month2!=null? operate_report_send_month2.hashCode(): 0) 
        + (biz_asset_code!=null? biz_asset_code.hashCode(): 0) 
        + (biz_asset_name!=null? biz_asset_name.hashCode(): 0) 
        + (prospectus_conversion_date!=null? prospectus_conversion_date.hashCode(): 0) 
        + (frgn_buy_min_amt!=null? frgn_buy_min_amt.hashCode(): 0) 
        + (frgn_sell_min_amt!=null? frgn_sell_min_amt.hashCode(): 0) 
        + (frgn_buy_unit_amt!=null? frgn_buy_unit_amt.hashCode(): 0) 
        + (frgn_sell_unit_amt!=null? frgn_sell_unit_amt.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("product_code",product_code);
		map.put("institution_code",institution_code);
		if ( work_date != null )
			map.put("work_date",work_date);
		if ( reg_date != null )
			map.put("reg_date",reg_date);
		if ( last_update_date != null )
			map.put("last_update_date",last_update_date);
		if ( reg_div != null )
			map.put("reg_div",reg_div);
		if ( product_name_kana != null )
			map.put("product_name_kana",product_name_kana);
		if ( product_name_kanji != null )
			map.put("product_name_kanji",product_name_kanji);
		if ( recruit_unit != null )
			map.put("recruit_unit",recruit_unit);
		if ( dealing_unit_qty != null )
			map.put("dealing_unit_qty",dealing_unit_qty);
		if ( recruit_min_qty != null )
			map.put("recruit_min_qty",recruit_min_qty);
		if ( buy_min_qty != null )
			map.put("buy_min_qty",buy_min_qty);
		if ( sell_min_qty != null )
			map.put("sell_min_qty",sell_min_qty);
		if ( swt_min_qty != null )
			map.put("swt_min_qty",swt_min_qty);
		if ( recruit_unit_qty != null )
			map.put("recruit_unit_qty",recruit_unit_qty);
		if ( buy_unit_qty != null )
			map.put("buy_unit_qty",buy_unit_qty);
		if ( sell_unit_qty != null )
			map.put("sell_unit_qty",sell_unit_qty);
		if ( swt_unit_qty != null )
			map.put("swt_unit_qty",swt_unit_qty);
		if ( recruit_min_amt != null )
			map.put("recruit_min_amt",recruit_min_amt);
		if ( buy_min_amt != null )
			map.put("buy_min_amt",buy_min_amt);
		if ( sell_min_amt != null )
			map.put("sell_min_amt",sell_min_amt);
		if ( swt_min_amt != null )
			map.put("swt_min_amt",swt_min_amt);
		if ( recruit_unit_amt != null )
			map.put("recruit_unit_amt",recruit_unit_amt);
		if ( buy_unit_amt != null )
			map.put("buy_unit_amt",buy_unit_amt);
		if ( sell_unit_amt != null )
			map.put("sell_unit_amt",sell_unit_amt);
		if ( swt_unit_amt != null )
			map.put("swt_unit_amt",swt_unit_amt);
		if ( recruit_qty_spec_div != null )
			map.put("recruit_qty_spec_div",recruit_qty_spec_div);
		if ( recruit_amt_spec_div != null )
			map.put("recruit_amt_spec_div",recruit_amt_spec_div);
		if ( buy_qty_spec_div != null )
			map.put("buy_qty_spec_div",buy_qty_spec_div);
		if ( buy_amt_spec_div != null )
			map.put("buy_amt_spec_div",buy_amt_spec_div);
		if ( sell_qty_spec_div != null )
			map.put("sell_qty_spec_div",sell_qty_spec_div);
		if ( sell_amt_spec_div != null )
			map.put("sell_amt_spec_div",sell_amt_spec_div);
		if ( swt_qty_spec_div != null )
			map.put("swt_qty_spec_div",swt_qty_spec_div);
		if ( swt_amt_spec_div != null )
			map.put("swt_amt_spec_div",swt_amt_spec_div);
		if ( input_unit_div != null )
			map.put("input_unit_div",input_unit_div);
		if ( cal_price_div != null )
			map.put("cal_price_div",cal_price_div);
		if ( sell_exception_div != null )
			map.put("sell_exception_div",sell_exception_div);
		if ( swt_trade_div != null )
			map.put("swt_trade_div",swt_trade_div);
		if ( swt_group != null )
			map.put("swt_group",swt_group);
		if ( swt_companion_subject_div != null )
			map.put("swt_companion_subject_div",swt_companion_subject_div);
		if ( buy_disable_div != null )
			map.put("buy_disable_div",buy_disable_div);
		if ( swt_perference_enable_div != null )
			map.put("swt_perference_enable_div",swt_perference_enable_div);
		if ( redemption_perference_price != null )
			map.put("redemption_perference_price",redemption_perference_price);
		if ( redemption_commission_rate != null )
			map.put("redemption_commission_rate",redemption_commission_rate);
		if ( pre_redemption_begin_date != null )
			map.put("pre_redemption_begin_date",pre_redemption_begin_date);
		if ( closing_date_cal_div != null )
			map.put("closing_date_cal_div",closing_date_cal_div);
		if ( closing_spec_date != null )
			map.put("closing_spec_date",closing_spec_date);
		if ( annual_profit_qty_times != null )
			map.put("annual_profit_qty_times",annual_profit_qty_times);
		if ( sell_advance_order_div != null )
			map.put("sell_advance_order_div",sell_advance_order_div);
		if ( buy_advance_order_div != null )
			map.put("buy_advance_order_div",buy_advance_order_div);
		if ( sell_undelivering_term != null )
			map.put("sell_undelivering_term",sell_undelivering_term);
		if ( buy_undelivering_term != null )
			map.put("buy_undelivering_term",buy_undelivering_term);
		if ( recruit_stop_div != null )
			map.put("recruit_stop_div",recruit_stop_div);
		if ( dealing_stop_div != null )
			map.put("dealing_stop_div",dealing_stop_div);
		if ( storage_stop_div != null )
			map.put("storage_stop_div",storage_stop_div);
		if ( specific_corpus_app != null )
			map.put("specific_corpus_app",specific_corpus_app);
		if ( perference_qualify != null )
			map.put("perference_qualify",perference_qualify);
		if ( collateral_qualify != null )
			map.put("collateral_qualify",collateral_qualify);
		if ( operate_report_send_div != null )
			map.put("operate_report_send_div",operate_report_send_div);
		if ( operate_report_send_month1 != null )
			map.put("operate_report_send_month1",operate_report_send_month1);
		if ( operate_report_send_month2 != null )
			map.put("operate_report_send_month2",operate_report_send_month2);
		if ( biz_asset_code != null )
			map.put("biz_asset_code",biz_asset_code);
		if ( biz_asset_name != null )
			map.put("biz_asset_name",biz_asset_name);
		if ( prospectus_conversion_date != null )
			map.put("prospectus_conversion_date",prospectus_conversion_date);
		if ( frgn_buy_min_amt != null )
			map.put("frgn_buy_min_amt",frgn_buy_min_amt);
		if ( frgn_sell_min_amt != null )
			map.put("frgn_sell_min_amt",frgn_sell_min_amt);
		if ( frgn_buy_unit_amt != null )
			map.put("frgn_buy_unit_amt",frgn_buy_unit_amt);
		if ( frgn_sell_unit_amt != null )
			map.put("frgn_sell_unit_amt",frgn_sell_unit_amt);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( work_date_is_modified )
			map.put("work_date",work_date);
		if ( reg_date_is_modified )
			map.put("reg_date",reg_date);
		if ( last_update_date_is_modified )
			map.put("last_update_date",last_update_date);
		if ( reg_div_is_modified )
			map.put("reg_div",reg_div);
		if ( product_name_kana_is_modified )
			map.put("product_name_kana",product_name_kana);
		if ( product_name_kanji_is_modified )
			map.put("product_name_kanji",product_name_kanji);
		if ( recruit_unit_is_modified )
			map.put("recruit_unit",recruit_unit);
		if ( dealing_unit_qty_is_modified )
			map.put("dealing_unit_qty",dealing_unit_qty);
		if ( recruit_min_qty_is_modified )
			map.put("recruit_min_qty",recruit_min_qty);
		if ( buy_min_qty_is_modified )
			map.put("buy_min_qty",buy_min_qty);
		if ( sell_min_qty_is_modified )
			map.put("sell_min_qty",sell_min_qty);
		if ( swt_min_qty_is_modified )
			map.put("swt_min_qty",swt_min_qty);
		if ( recruit_unit_qty_is_modified )
			map.put("recruit_unit_qty",recruit_unit_qty);
		if ( buy_unit_qty_is_modified )
			map.put("buy_unit_qty",buy_unit_qty);
		if ( sell_unit_qty_is_modified )
			map.put("sell_unit_qty",sell_unit_qty);
		if ( swt_unit_qty_is_modified )
			map.put("swt_unit_qty",swt_unit_qty);
		if ( recruit_min_amt_is_modified )
			map.put("recruit_min_amt",recruit_min_amt);
		if ( buy_min_amt_is_modified )
			map.put("buy_min_amt",buy_min_amt);
		if ( sell_min_amt_is_modified )
			map.put("sell_min_amt",sell_min_amt);
		if ( swt_min_amt_is_modified )
			map.put("swt_min_amt",swt_min_amt);
		if ( recruit_unit_amt_is_modified )
			map.put("recruit_unit_amt",recruit_unit_amt);
		if ( buy_unit_amt_is_modified )
			map.put("buy_unit_amt",buy_unit_amt);
		if ( sell_unit_amt_is_modified )
			map.put("sell_unit_amt",sell_unit_amt);
		if ( swt_unit_amt_is_modified )
			map.put("swt_unit_amt",swt_unit_amt);
		if ( recruit_qty_spec_div_is_modified )
			map.put("recruit_qty_spec_div",recruit_qty_spec_div);
		if ( recruit_amt_spec_div_is_modified )
			map.put("recruit_amt_spec_div",recruit_amt_spec_div);
		if ( buy_qty_spec_div_is_modified )
			map.put("buy_qty_spec_div",buy_qty_spec_div);
		if ( buy_amt_spec_div_is_modified )
			map.put("buy_amt_spec_div",buy_amt_spec_div);
		if ( sell_qty_spec_div_is_modified )
			map.put("sell_qty_spec_div",sell_qty_spec_div);
		if ( sell_amt_spec_div_is_modified )
			map.put("sell_amt_spec_div",sell_amt_spec_div);
		if ( swt_qty_spec_div_is_modified )
			map.put("swt_qty_spec_div",swt_qty_spec_div);
		if ( swt_amt_spec_div_is_modified )
			map.put("swt_amt_spec_div",swt_amt_spec_div);
		if ( input_unit_div_is_modified )
			map.put("input_unit_div",input_unit_div);
		if ( cal_price_div_is_modified )
			map.put("cal_price_div",cal_price_div);
		if ( sell_exception_div_is_modified )
			map.put("sell_exception_div",sell_exception_div);
		if ( swt_trade_div_is_modified )
			map.put("swt_trade_div",swt_trade_div);
		if ( swt_group_is_modified )
			map.put("swt_group",swt_group);
		if ( swt_companion_subject_div_is_modified )
			map.put("swt_companion_subject_div",swt_companion_subject_div);
		if ( buy_disable_div_is_modified )
			map.put("buy_disable_div",buy_disable_div);
		if ( swt_perference_enable_div_is_modified )
			map.put("swt_perference_enable_div",swt_perference_enable_div);
		if ( redemption_perference_price_is_modified )
			map.put("redemption_perference_price",redemption_perference_price);
		if ( redemption_commission_rate_is_modified )
			map.put("redemption_commission_rate",redemption_commission_rate);
		if ( pre_redemption_begin_date_is_modified )
			map.put("pre_redemption_begin_date",pre_redemption_begin_date);
		if ( closing_date_cal_div_is_modified )
			map.put("closing_date_cal_div",closing_date_cal_div);
		if ( closing_spec_date_is_modified )
			map.put("closing_spec_date",closing_spec_date);
		if ( annual_profit_qty_times_is_modified )
			map.put("annual_profit_qty_times",annual_profit_qty_times);
		if ( sell_advance_order_div_is_modified )
			map.put("sell_advance_order_div",sell_advance_order_div);
		if ( buy_advance_order_div_is_modified )
			map.put("buy_advance_order_div",buy_advance_order_div);
		if ( sell_undelivering_term_is_modified )
			map.put("sell_undelivering_term",sell_undelivering_term);
		if ( buy_undelivering_term_is_modified )
			map.put("buy_undelivering_term",buy_undelivering_term);
		if ( recruit_stop_div_is_modified )
			map.put("recruit_stop_div",recruit_stop_div);
		if ( dealing_stop_div_is_modified )
			map.put("dealing_stop_div",dealing_stop_div);
		if ( storage_stop_div_is_modified )
			map.put("storage_stop_div",storage_stop_div);
		if ( specific_corpus_app_is_modified )
			map.put("specific_corpus_app",specific_corpus_app);
		if ( perference_qualify_is_modified )
			map.put("perference_qualify",perference_qualify);
		if ( collateral_qualify_is_modified )
			map.put("collateral_qualify",collateral_qualify);
		if ( operate_report_send_div_is_modified )
			map.put("operate_report_send_div",operate_report_send_div);
		if ( operate_report_send_month1_is_modified )
			map.put("operate_report_send_month1",operate_report_send_month1);
		if ( operate_report_send_month2_is_modified )
			map.put("operate_report_send_month2",operate_report_send_month2);
		if ( biz_asset_code_is_modified )
			map.put("biz_asset_code",biz_asset_code);
		if ( biz_asset_name_is_modified )
			map.put("biz_asset_name",biz_asset_name);
		if ( prospectus_conversion_date_is_modified )
			map.put("prospectus_conversion_date",prospectus_conversion_date);
		if ( frgn_buy_min_amt_is_modified )
			map.put("frgn_buy_min_amt",frgn_buy_min_amt);
		if ( frgn_sell_min_amt_is_modified )
			map.put("frgn_sell_min_amt",frgn_sell_min_amt);
		if ( frgn_buy_unit_amt_is_modified )
			map.put("frgn_buy_unit_amt",frgn_buy_unit_amt);
		if ( frgn_sell_unit_amt_is_modified )
			map.put("frgn_sell_unit_amt",frgn_sell_unit_amt);
		if (map.size() == 0) {
			map.put("work_date",work_date);
			map.put("reg_date",reg_date);
			map.put("last_update_date",last_update_date);
			map.put("reg_div",reg_div);
			map.put("product_name_kana",product_name_kana);
			map.put("product_name_kanji",product_name_kanji);
			map.put("recruit_unit",recruit_unit);
			map.put("dealing_unit_qty",dealing_unit_qty);
			map.put("recruit_min_qty",recruit_min_qty);
			map.put("buy_min_qty",buy_min_qty);
			map.put("sell_min_qty",sell_min_qty);
			map.put("swt_min_qty",swt_min_qty);
			map.put("recruit_unit_qty",recruit_unit_qty);
			map.put("buy_unit_qty",buy_unit_qty);
			map.put("sell_unit_qty",sell_unit_qty);
			map.put("swt_unit_qty",swt_unit_qty);
			map.put("recruit_min_amt",recruit_min_amt);
			map.put("buy_min_amt",buy_min_amt);
			map.put("sell_min_amt",sell_min_amt);
			map.put("swt_min_amt",swt_min_amt);
			map.put("recruit_unit_amt",recruit_unit_amt);
			map.put("buy_unit_amt",buy_unit_amt);
			map.put("sell_unit_amt",sell_unit_amt);
			map.put("swt_unit_amt",swt_unit_amt);
			map.put("recruit_qty_spec_div",recruit_qty_spec_div);
			map.put("recruit_amt_spec_div",recruit_amt_spec_div);
			map.put("buy_qty_spec_div",buy_qty_spec_div);
			map.put("buy_amt_spec_div",buy_amt_spec_div);
			map.put("sell_qty_spec_div",sell_qty_spec_div);
			map.put("sell_amt_spec_div",sell_amt_spec_div);
			map.put("swt_qty_spec_div",swt_qty_spec_div);
			map.put("swt_amt_spec_div",swt_amt_spec_div);
			map.put("input_unit_div",input_unit_div);
			map.put("cal_price_div",cal_price_div);
			map.put("sell_exception_div",sell_exception_div);
			map.put("swt_trade_div",swt_trade_div);
			map.put("swt_group",swt_group);
			map.put("swt_companion_subject_div",swt_companion_subject_div);
			map.put("buy_disable_div",buy_disable_div);
			map.put("swt_perference_enable_div",swt_perference_enable_div);
			map.put("redemption_perference_price",redemption_perference_price);
			map.put("redemption_commission_rate",redemption_commission_rate);
			map.put("pre_redemption_begin_date",pre_redemption_begin_date);
			map.put("closing_date_cal_div",closing_date_cal_div);
			map.put("closing_spec_date",closing_spec_date);
			map.put("annual_profit_qty_times",annual_profit_qty_times);
			map.put("sell_advance_order_div",sell_advance_order_div);
			map.put("buy_advance_order_div",buy_advance_order_div);
			map.put("sell_undelivering_term",sell_undelivering_term);
			map.put("buy_undelivering_term",buy_undelivering_term);
			map.put("recruit_stop_div",recruit_stop_div);
			map.put("dealing_stop_div",dealing_stop_div);
			map.put("storage_stop_div",storage_stop_div);
			map.put("specific_corpus_app",specific_corpus_app);
			map.put("perference_qualify",perference_qualify);
			map.put("collateral_qualify",collateral_qualify);
			map.put("operate_report_send_div",operate_report_send_div);
			map.put("operate_report_send_month1",operate_report_send_month1);
			map.put("operate_report_send_month2",operate_report_send_month2);
			map.put("biz_asset_code",biz_asset_code);
			map.put("biz_asset_name",biz_asset_name);
			map.put("prospectus_conversion_date",prospectus_conversion_date);
			map.put("frgn_buy_min_amt",frgn_buy_min_amt);
			map.put("frgn_sell_min_amt",frgn_sell_min_amt);
			map.put("frgn_buy_unit_amt",frgn_buy_unit_amt);
			map.put("frgn_sell_unit_amt",frgn_sell_unit_amt);
		}
		return map;
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
   * <em>work_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getWorkDate()
  {
    return work_date;
  }


  /** 
   * <em>work_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWorkDateIsSet() {
    return work_date_is_set;
  }


  /** 
   * <em>work_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWorkDateIsModified() {
    return work_date_is_modified;
  }


  /** 
   * <em>reg_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getRegDate()
  {
    return reg_date;
  }


  /** 
   * <em>reg_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegDateIsSet() {
    return reg_date_is_set;
  }


  /** 
   * <em>reg_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegDateIsModified() {
    return reg_date_is_modified;
  }


  /** 
   * <em>last_update_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdateDate()
  {
    return last_update_date;
  }


  /** 
   * <em>last_update_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateDateIsSet() {
    return last_update_date_is_set;
  }


  /** 
   * <em>last_update_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateDateIsModified() {
    return last_update_date_is_modified;
  }


  /** 
   * <em>reg_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRegDiv()
  {
    return reg_div;
  }


  /** 
   * <em>reg_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegDivIsSet() {
    return reg_div_is_set;
  }


  /** 
   * <em>reg_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegDivIsModified() {
    return reg_div_is_modified;
  }


  /** 
   * <em>product_name_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductNameKana()
  {
    return product_name_kana;
  }


  /** 
   * <em>product_name_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductNameKanaIsSet() {
    return product_name_kana_is_set;
  }


  /** 
   * <em>product_name_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductNameKanaIsModified() {
    return product_name_kana_is_modified;
  }


  /** 
   * <em>product_name_kanji</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductNameKanji()
  {
    return product_name_kanji;
  }


  /** 
   * <em>product_name_kanji</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductNameKanjiIsSet() {
    return product_name_kanji_is_set;
  }


  /** 
   * <em>product_name_kanji</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductNameKanjiIsModified() {
    return product_name_kanji_is_modified;
  }


  /** 
   * <em>recruit_unit</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getRecruitUnit()
  {
    return ( recruit_unit==null? ((int)0): recruit_unit.intValue() );
  }


  /** 
   * <em>recruit_unit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRecruitUnitIsNull()
  {
    return recruit_unit == null;
  }


  /** 
   * <em>recruit_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitUnitIsSet() {
    return recruit_unit_is_set;
  }


  /** 
   * <em>recruit_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitUnitIsModified() {
    return recruit_unit_is_modified;
  }


  /** 
   * <em>dealing_unit_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getDealingUnitQty()
  {
    return ( dealing_unit_qty==null? ((int)0): dealing_unit_qty.intValue() );
  }


  /** 
   * <em>dealing_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDealingUnitQtyIsNull()
  {
    return dealing_unit_qty == null;
  }


  /** 
   * <em>dealing_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDealingUnitQtyIsSet() {
    return dealing_unit_qty_is_set;
  }


  /** 
   * <em>dealing_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDealingUnitQtyIsModified() {
    return dealing_unit_qty_is_modified;
  }


  /** 
   * <em>recruit_min_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getRecruitMinQty()
  {
    return ( recruit_min_qty==null? ((int)0): recruit_min_qty.intValue() );
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
   * <em>buy_min_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getBuyMinQty()
  {
    return ( buy_min_qty==null? ((int)0): buy_min_qty.intValue() );
  }


  /** 
   * <em>buy_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyMinQtyIsNull()
  {
    return buy_min_qty == null;
  }


  /** 
   * <em>buy_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyMinQtyIsSet() {
    return buy_min_qty_is_set;
  }


  /** 
   * <em>buy_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyMinQtyIsModified() {
    return buy_min_qty_is_modified;
  }


  /** 
   * <em>sell_min_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSellMinQty()
  {
    return ( sell_min_qty==null? ((int)0): sell_min_qty.intValue() );
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
   * @@return intの値 
   */
  public final int getSwtMinQty()
  {
    return ( swt_min_qty==null? ((int)0): swt_min_qty.intValue() );
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
   * <em>recruit_unit_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getRecruitUnitQty()
  {
    return ( recruit_unit_qty==null? ((int)0): recruit_unit_qty.intValue() );
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
   * <em>buy_unit_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getBuyUnitQty()
  {
    return ( buy_unit_qty==null? ((int)0): buy_unit_qty.intValue() );
  }


  /** 
   * <em>buy_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyUnitQtyIsNull()
  {
    return buy_unit_qty == null;
  }


  /** 
   * <em>buy_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyUnitQtyIsSet() {
    return buy_unit_qty_is_set;
  }


  /** 
   * <em>buy_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyUnitQtyIsModified() {
    return buy_unit_qty_is_modified;
  }


  /** 
   * <em>sell_unit_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSellUnitQty()
  {
    return ( sell_unit_qty==null? ((int)0): sell_unit_qty.intValue() );
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
   * @@return intの値 
   */
  public final int getSwtUnitQty()
  {
    return ( swt_unit_qty==null? ((int)0): swt_unit_qty.intValue() );
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
   * <em>recruit_min_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getRecruitMinAmt()
  {
    return ( recruit_min_amt==null? ((int)0): recruit_min_amt.intValue() );
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
   * <em>buy_min_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getBuyMinAmt()
  {
    return ( buy_min_amt==null? ((int)0): buy_min_amt.intValue() );
  }


  /** 
   * <em>buy_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyMinAmtIsNull()
  {
    return buy_min_amt == null;
  }


  /** 
   * <em>buy_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyMinAmtIsSet() {
    return buy_min_amt_is_set;
  }


  /** 
   * <em>buy_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyMinAmtIsModified() {
    return buy_min_amt_is_modified;
  }


  /** 
   * <em>sell_min_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSellMinAmt()
  {
    return ( sell_min_amt==null? ((int)0): sell_min_amt.intValue() );
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
   * @@return intの値 
   */
  public final int getSwtMinAmt()
  {
    return ( swt_min_amt==null? ((int)0): swt_min_amt.intValue() );
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
   * <em>recruit_unit_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getRecruitUnitAmt()
  {
    return ( recruit_unit_amt==null? ((int)0): recruit_unit_amt.intValue() );
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
   * <em>buy_unit_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getBuyUnitAmt()
  {
    return ( buy_unit_amt==null? ((int)0): buy_unit_amt.intValue() );
  }


  /** 
   * <em>buy_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyUnitAmtIsNull()
  {
    return buy_unit_amt == null;
  }


  /** 
   * <em>buy_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyUnitAmtIsSet() {
    return buy_unit_amt_is_set;
  }


  /** 
   * <em>buy_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyUnitAmtIsModified() {
    return buy_unit_amt_is_modified;
  }


  /** 
   * <em>sell_unit_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSellUnitAmt()
  {
    return ( sell_unit_amt==null? ((int)0): sell_unit_amt.intValue() );
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
   * @@return intの値 
   */
  public final int getSwtUnitAmt()
  {
    return ( swt_unit_amt==null? ((int)0): swt_unit_amt.intValue() );
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
   * <em>recruit_qty_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRecruitQtySpecDiv()
  {
    return recruit_qty_spec_div;
  }


  /** 
   * <em>recruit_qty_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitQtySpecDivIsSet() {
    return recruit_qty_spec_div_is_set;
  }


  /** 
   * <em>recruit_qty_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitQtySpecDivIsModified() {
    return recruit_qty_spec_div_is_modified;
  }


  /** 
   * <em>recruit_amt_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRecruitAmtSpecDiv()
  {
    return recruit_amt_spec_div;
  }


  /** 
   * <em>recruit_amt_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitAmtSpecDivIsSet() {
    return recruit_amt_spec_div_is_set;
  }


  /** 
   * <em>recruit_amt_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitAmtSpecDivIsModified() {
    return recruit_amt_spec_div_is_modified;
  }


  /** 
   * <em>buy_qty_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyQtySpecDiv()
  {
    return buy_qty_spec_div;
  }


  /** 
   * <em>buy_qty_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyQtySpecDivIsSet() {
    return buy_qty_spec_div_is_set;
  }


  /** 
   * <em>buy_qty_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyQtySpecDivIsModified() {
    return buy_qty_spec_div_is_modified;
  }


  /** 
   * <em>buy_amt_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyAmtSpecDiv()
  {
    return buy_amt_spec_div;
  }


  /** 
   * <em>buy_amt_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyAmtSpecDivIsSet() {
    return buy_amt_spec_div_is_set;
  }


  /** 
   * <em>buy_amt_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyAmtSpecDivIsModified() {
    return buy_amt_spec_div_is_modified;
  }


  /** 
   * <em>sell_qty_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellQtySpecDiv()
  {
    return sell_qty_spec_div;
  }


  /** 
   * <em>sell_qty_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellQtySpecDivIsSet() {
    return sell_qty_spec_div_is_set;
  }


  /** 
   * <em>sell_qty_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellQtySpecDivIsModified() {
    return sell_qty_spec_div_is_modified;
  }


  /** 
   * <em>sell_amt_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellAmtSpecDiv()
  {
    return sell_amt_spec_div;
  }


  /** 
   * <em>sell_amt_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellAmtSpecDivIsSet() {
    return sell_amt_spec_div_is_set;
  }


  /** 
   * <em>sell_amt_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellAmtSpecDivIsModified() {
    return sell_amt_spec_div_is_modified;
  }


  /** 
   * <em>swt_qty_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwtQtySpecDiv()
  {
    return swt_qty_spec_div;
  }


  /** 
   * <em>swt_qty_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtQtySpecDivIsSet() {
    return swt_qty_spec_div_is_set;
  }


  /** 
   * <em>swt_qty_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtQtySpecDivIsModified() {
    return swt_qty_spec_div_is_modified;
  }


  /** 
   * <em>swt_amt_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwtAmtSpecDiv()
  {
    return swt_amt_spec_div;
  }


  /** 
   * <em>swt_amt_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtAmtSpecDivIsSet() {
    return swt_amt_spec_div_is_set;
  }


  /** 
   * <em>swt_amt_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtAmtSpecDivIsModified() {
    return swt_amt_spec_div_is_modified;
  }


  /** 
   * <em>input_unit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInputUnitDiv()
  {
    return input_unit_div;
  }


  /** 
   * <em>input_unit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInputUnitDivIsSet() {
    return input_unit_div_is_set;
  }


  /** 
   * <em>input_unit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInputUnitDivIsModified() {
    return input_unit_div_is_modified;
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
   * <em>sell_exception_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellExceptionDiv()
  {
    return sell_exception_div;
  }


  /** 
   * <em>sell_exception_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellExceptionDivIsSet() {
    return sell_exception_div_is_set;
  }


  /** 
   * <em>sell_exception_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellExceptionDivIsModified() {
    return sell_exception_div_is_modified;
  }


  /** 
   * <em>swt_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwtTradeDiv()
  {
    return swt_trade_div;
  }


  /** 
   * <em>swt_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtTradeDivIsSet() {
    return swt_trade_div_is_set;
  }


  /** 
   * <em>swt_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtTradeDivIsModified() {
    return swt_trade_div_is_modified;
  }


  /** 
   * <em>swt_group</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwtGroup()
  {
    return swt_group;
  }


  /** 
   * <em>swt_group</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtGroupIsSet() {
    return swt_group_is_set;
  }


  /** 
   * <em>swt_group</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtGroupIsModified() {
    return swt_group_is_modified;
  }


  /** 
   * <em>swt_companion_subject_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwtCompanionSubjectDiv()
  {
    return swt_companion_subject_div;
  }


  /** 
   * <em>swt_companion_subject_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtCompanionSubjectDivIsSet() {
    return swt_companion_subject_div_is_set;
  }


  /** 
   * <em>swt_companion_subject_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtCompanionSubjectDivIsModified() {
    return swt_companion_subject_div_is_modified;
  }


  /** 
   * <em>buy_disable_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyDisableDiv()
  {
    return buy_disable_div;
  }


  /** 
   * <em>buy_disable_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyDisableDivIsSet() {
    return buy_disable_div_is_set;
  }


  /** 
   * <em>buy_disable_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyDisableDivIsModified() {
    return buy_disable_div_is_modified;
  }


  /** 
   * <em>swt_perference_enable_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwtPerferenceEnableDiv()
  {
    return swt_perference_enable_div;
  }


  /** 
   * <em>swt_perference_enable_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtPerferenceEnableDivIsSet() {
    return swt_perference_enable_div_is_set;
  }


  /** 
   * <em>swt_perference_enable_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtPerferenceEnableDivIsModified() {
    return swt_perference_enable_div_is_modified;
  }


  /** 
   * <em>redemption_perference_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRedemptionPerferencePrice()
  {
    return ( redemption_perference_price==null? ((double)0): redemption_perference_price.doubleValue() );
  }


  /** 
   * <em>redemption_perference_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRedemptionPerferencePriceIsNull()
  {
    return redemption_perference_price == null;
  }


  /** 
   * <em>redemption_perference_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRedemptionPerferencePriceIsSet() {
    return redemption_perference_price_is_set;
  }


  /** 
   * <em>redemption_perference_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRedemptionPerferencePriceIsModified() {
    return redemption_perference_price_is_modified;
  }


  /** 
   * <em>redemption_commission_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRedemptionCommissionRate()
  {
    return ( redemption_commission_rate==null? ((double)0): redemption_commission_rate.doubleValue() );
  }


  /** 
   * <em>redemption_commission_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRedemptionCommissionRateIsNull()
  {
    return redemption_commission_rate == null;
  }


  /** 
   * <em>redemption_commission_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRedemptionCommissionRateIsSet() {
    return redemption_commission_rate_is_set;
  }


  /** 
   * <em>redemption_commission_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRedemptionCommissionRateIsModified() {
    return redemption_commission_rate_is_modified;
  }


  /** 
   * <em>pre_redemption_begin_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getPreRedemptionBeginDate()
  {
    return pre_redemption_begin_date;
  }


  /** 
   * <em>pre_redemption_begin_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPreRedemptionBeginDateIsSet() {
    return pre_redemption_begin_date_is_set;
  }


  /** 
   * <em>pre_redemption_begin_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPreRedemptionBeginDateIsModified() {
    return pre_redemption_begin_date_is_modified;
  }


  /** 
   * <em>closing_date_cal_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getClosingDateCalDiv()
  {
    return closing_date_cal_div;
  }


  /** 
   * <em>closing_date_cal_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClosingDateCalDivIsSet() {
    return closing_date_cal_div_is_set;
  }


  /** 
   * <em>closing_date_cal_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClosingDateCalDivIsModified() {
    return closing_date_cal_div_is_modified;
  }


  /** 
   * <em>closing_spec_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getClosingSpecDate()
  {
    return closing_spec_date;
  }


  /** 
   * <em>closing_spec_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClosingSpecDateIsSet() {
    return closing_spec_date_is_set;
  }


  /** 
   * <em>closing_spec_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClosingSpecDateIsModified() {
    return closing_spec_date_is_modified;
  }


  /** 
   * <em>annual_profit_qty_times</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAnnualProfitQtyTimes()
  {
    return annual_profit_qty_times;
  }


  /** 
   * <em>annual_profit_qty_times</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAnnualProfitQtyTimesIsSet() {
    return annual_profit_qty_times_is_set;
  }


  /** 
   * <em>annual_profit_qty_times</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAnnualProfitQtyTimesIsModified() {
    return annual_profit_qty_times_is_modified;
  }


  /** 
   * <em>sell_advance_order_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellAdvanceOrderDiv()
  {
    return sell_advance_order_div;
  }


  /** 
   * <em>sell_advance_order_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellAdvanceOrderDivIsSet() {
    return sell_advance_order_div_is_set;
  }


  /** 
   * <em>sell_advance_order_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellAdvanceOrderDivIsModified() {
    return sell_advance_order_div_is_modified;
  }


  /** 
   * <em>buy_advance_order_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyAdvanceOrderDiv()
  {
    return buy_advance_order_div;
  }


  /** 
   * <em>buy_advance_order_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyAdvanceOrderDivIsSet() {
    return buy_advance_order_div_is_set;
  }


  /** 
   * <em>buy_advance_order_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyAdvanceOrderDivIsModified() {
    return buy_advance_order_div_is_modified;
  }


  /** 
   * <em>sell_undelivering_term</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellUndeliveringTerm()
  {
    return sell_undelivering_term;
  }


  /** 
   * <em>sell_undelivering_term</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellUndeliveringTermIsSet() {
    return sell_undelivering_term_is_set;
  }


  /** 
   * <em>sell_undelivering_term</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellUndeliveringTermIsModified() {
    return sell_undelivering_term_is_modified;
  }


  /** 
   * <em>buy_undelivering_term</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyUndeliveringTerm()
  {
    return buy_undelivering_term;
  }


  /** 
   * <em>buy_undelivering_term</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyUndeliveringTermIsSet() {
    return buy_undelivering_term_is_set;
  }


  /** 
   * <em>buy_undelivering_term</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyUndeliveringTermIsModified() {
    return buy_undelivering_term_is_modified;
  }


  /** 
   * <em>recruit_stop_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRecruitStopDiv()
  {
    return recruit_stop_div;
  }


  /** 
   * <em>recruit_stop_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitStopDivIsSet() {
    return recruit_stop_div_is_set;
  }


  /** 
   * <em>recruit_stop_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecruitStopDivIsModified() {
    return recruit_stop_div_is_modified;
  }


  /** 
   * <em>dealing_stop_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDealingStopDiv()
  {
    return dealing_stop_div;
  }


  /** 
   * <em>dealing_stop_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDealingStopDivIsSet() {
    return dealing_stop_div_is_set;
  }


  /** 
   * <em>dealing_stop_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDealingStopDivIsModified() {
    return dealing_stop_div_is_modified;
  }


  /** 
   * <em>storage_stop_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStorageStopDiv()
  {
    return storage_stop_div;
  }


  /** 
   * <em>storage_stop_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStorageStopDivIsSet() {
    return storage_stop_div_is_set;
  }


  /** 
   * <em>storage_stop_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStorageStopDivIsModified() {
    return storage_stop_div_is_modified;
  }


  /** 
   * <em>specific_corpus_app</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSpecificCorpusApp()
  {
    return specific_corpus_app;
  }


  /** 
   * <em>specific_corpus_app</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecificCorpusAppIsSet() {
    return specific_corpus_app_is_set;
  }


  /** 
   * <em>specific_corpus_app</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecificCorpusAppIsModified() {
    return specific_corpus_app_is_modified;
  }


  /** 
   * <em>perference_qualify</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPerferenceQualify()
  {
    return perference_qualify;
  }


  /** 
   * <em>perference_qualify</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPerferenceQualifyIsSet() {
    return perference_qualify_is_set;
  }


  /** 
   * <em>perference_qualify</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPerferenceQualifyIsModified() {
    return perference_qualify_is_modified;
  }


  /** 
   * <em>collateral_qualify</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCollateralQualify()
  {
    return collateral_qualify;
  }


  /** 
   * <em>collateral_qualify</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCollateralQualifyIsSet() {
    return collateral_qualify_is_set;
  }


  /** 
   * <em>collateral_qualify</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCollateralQualifyIsModified() {
    return collateral_qualify_is_modified;
  }


  /** 
   * <em>operate_report_send_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOperateReportSendDiv()
  {
    return operate_report_send_div;
  }


  /** 
   * <em>operate_report_send_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperateReportSendDivIsSet() {
    return operate_report_send_div_is_set;
  }


  /** 
   * <em>operate_report_send_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperateReportSendDivIsModified() {
    return operate_report_send_div_is_modified;
  }


  /** 
   * <em>operate_report_send_month1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOperateReportSendMonth1()
  {
    return operate_report_send_month1;
  }


  /** 
   * <em>operate_report_send_month1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperateReportSendMonth1IsSet() {
    return operate_report_send_month1_is_set;
  }


  /** 
   * <em>operate_report_send_month1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperateReportSendMonth1IsModified() {
    return operate_report_send_month1_is_modified;
  }


  /** 
   * <em>operate_report_send_month2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOperateReportSendMonth2()
  {
    return operate_report_send_month2;
  }


  /** 
   * <em>operate_report_send_month2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperateReportSendMonth2IsSet() {
    return operate_report_send_month2_is_set;
  }


  /** 
   * <em>operate_report_send_month2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperateReportSendMonth2IsModified() {
    return operate_report_send_month2_is_modified;
  }


  /** 
   * <em>biz_asset_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBizAssetCode()
  {
    return biz_asset_code;
  }


  /** 
   * <em>biz_asset_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBizAssetCodeIsSet() {
    return biz_asset_code_is_set;
  }


  /** 
   * <em>biz_asset_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBizAssetCodeIsModified() {
    return biz_asset_code_is_modified;
  }


  /** 
   * <em>biz_asset_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBizAssetName()
  {
    return biz_asset_name;
  }


  /** 
   * <em>biz_asset_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBizAssetNameIsSet() {
    return biz_asset_name_is_set;
  }


  /** 
   * <em>biz_asset_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBizAssetNameIsModified() {
    return biz_asset_name_is_modified;
  }


  /** 
   * <em>prospectus_conversion_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getProspectusConversionDate()
  {
    return prospectus_conversion_date;
  }


  /** 
   * <em>prospectus_conversion_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProspectusConversionDateIsSet() {
    return prospectus_conversion_date_is_set;
  }


  /** 
   * <em>prospectus_conversion_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProspectusConversionDateIsModified() {
    return prospectus_conversion_date_is_modified;
  }


  /** 
   * <em>frgn_buy_min_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getFrgnBuyMinAmt()
  {
    return ( frgn_buy_min_amt==null? ((int)0): frgn_buy_min_amt.intValue() );
  }


  /** 
   * <em>frgn_buy_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFrgnBuyMinAmtIsNull()
  {
    return frgn_buy_min_amt == null;
  }


  /** 
   * <em>frgn_buy_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnBuyMinAmtIsSet() {
    return frgn_buy_min_amt_is_set;
  }


  /** 
   * <em>frgn_buy_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnBuyMinAmtIsModified() {
    return frgn_buy_min_amt_is_modified;
  }


  /** 
   * <em>frgn_sell_min_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getFrgnSellMinAmt()
  {
    return ( frgn_sell_min_amt==null? ((int)0): frgn_sell_min_amt.intValue() );
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
   * <em>frgn_buy_unit_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getFrgnBuyUnitAmt()
  {
    return ( frgn_buy_unit_amt==null? ((int)0): frgn_buy_unit_amt.intValue() );
  }


  /** 
   * <em>frgn_buy_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFrgnBuyUnitAmtIsNull()
  {
    return frgn_buy_unit_amt == null;
  }


  /** 
   * <em>frgn_buy_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnBuyUnitAmtIsSet() {
    return frgn_buy_unit_amt_is_set;
  }


  /** 
   * <em>frgn_buy_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrgnBuyUnitAmtIsModified() {
    return frgn_buy_unit_amt_is_modified;
  }


  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getFrgnSellUnitAmt()
  {
    return ( frgn_sell_unit_amt==null? ((int)0): frgn_sell_unit_amt.intValue() );
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new MutualFund2ndProductSonarPK(product_code, institution_code);
  }


  /** 
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>work_date</em>カラムの値を設定します。 
   *
   * @@param p_workDate <em>work_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setWorkDate( java.sql.Timestamp p_workDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    work_date = p_workDate;
    work_date_is_set = true;
    work_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>work_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setWorkDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    work_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    work_date_is_set = true;
    work_date_is_modified = true;
  }


  /** 
   * <em>reg_date</em>カラムの値を設定します。 
   *
   * @@param p_regDate <em>reg_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setRegDate( java.sql.Timestamp p_regDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reg_date = p_regDate;
    reg_date_is_set = true;
    reg_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>reg_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setRegDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    reg_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    reg_date_is_set = true;
    reg_date_is_modified = true;
  }


  /** 
   * <em>last_update_date</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdateDate <em>last_update_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdateDate( java.sql.Timestamp p_lastUpdateDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_date = p_lastUpdateDate;
    last_update_date_is_set = true;
    last_update_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_update_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdateDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_update_date_is_set = true;
    last_update_date_is_modified = true;
  }


  /** 
   * <em>reg_div</em>カラムの値を設定します。 
   *
   * @@param p_regDiv <em>reg_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRegDiv( String p_regDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reg_div = p_regDiv;
    reg_div_is_set = true;
    reg_div_is_modified = true;
  }


  /** 
   * <em>product_name_kana</em>カラムの値を設定します。 
   *
   * @@param p_productNameKana <em>product_name_kana</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setProductNameKana( String p_productNameKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_name_kana = p_productNameKana;
    product_name_kana_is_set = true;
    product_name_kana_is_modified = true;
  }


  /** 
   * <em>product_name_kanji</em>カラムの値を設定します。 
   *
   * @@param p_productNameKanji <em>product_name_kanji</em>カラムの値をあらわす26桁以下のString値 
   */
  public final void setProductNameKanji( String p_productNameKanji )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_name_kanji = p_productNameKanji;
    product_name_kanji_is_set = true;
    product_name_kanji_is_modified = true;
  }


  /** 
   * <em>recruit_unit</em>カラムの値を設定します。 
   *
   * @@param p_recruitUnit <em>recruit_unit</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setRecruitUnit( int p_recruitUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_unit = new Integer(p_recruitUnit);
    recruit_unit_is_set = true;
    recruit_unit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>recruit_unit</em>カラムに値を設定します。 
   */
  public final void setRecruitUnit( Integer p_recruitUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_unit = p_recruitUnit;
    recruit_unit_is_set = true;
    recruit_unit_is_modified = true;
  }


  /** 
   * <em>dealing_unit_qty</em>カラムの値を設定します。 
   *
   * @@param p_dealingUnitQty <em>dealing_unit_qty</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setDealingUnitQty( int p_dealingUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dealing_unit_qty = new Integer(p_dealingUnitQty);
    dealing_unit_qty_is_set = true;
    dealing_unit_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>dealing_unit_qty</em>カラムに値を設定します。 
   */
  public final void setDealingUnitQty( Integer p_dealingUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    dealing_unit_qty = p_dealingUnitQty;
    dealing_unit_qty_is_set = true;
    dealing_unit_qty_is_modified = true;
  }


  /** 
   * <em>recruit_min_qty</em>カラムの値を設定します。 
   *
   * @@param p_recruitMinQty <em>recruit_min_qty</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setRecruitMinQty( int p_recruitMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_min_qty = new Integer(p_recruitMinQty);
    recruit_min_qty_is_set = true;
    recruit_min_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>recruit_min_qty</em>カラムに値を設定します。 
   */
  public final void setRecruitMinQty( Integer p_recruitMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_min_qty = p_recruitMinQty;
    recruit_min_qty_is_set = true;
    recruit_min_qty_is_modified = true;
  }


  /** 
   * <em>buy_min_qty</em>カラムの値を設定します。 
   *
   * @@param p_buyMinQty <em>buy_min_qty</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setBuyMinQty( int p_buyMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_min_qty = new Integer(p_buyMinQty);
    buy_min_qty_is_set = true;
    buy_min_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_min_qty</em>カラムに値を設定します。 
   */
  public final void setBuyMinQty( Integer p_buyMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_min_qty = p_buyMinQty;
    buy_min_qty_is_set = true;
    buy_min_qty_is_modified = true;
  }


  /** 
   * <em>sell_min_qty</em>カラムの値を設定します。 
   *
   * @@param p_sellMinQty <em>sell_min_qty</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setSellMinQty( int p_sellMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_min_qty = new Integer(p_sellMinQty);
    sell_min_qty_is_set = true;
    sell_min_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_min_qty</em>カラムに値を設定します。 
   */
  public final void setSellMinQty( Integer p_sellMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_min_qty = p_sellMinQty;
    sell_min_qty_is_set = true;
    sell_min_qty_is_modified = true;
  }


  /** 
   * <em>swt_min_qty</em>カラムの値を設定します。 
   *
   * @@param p_swtMinQty <em>swt_min_qty</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setSwtMinQty( int p_swtMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_min_qty = new Integer(p_swtMinQty);
    swt_min_qty_is_set = true;
    swt_min_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>swt_min_qty</em>カラムに値を設定します。 
   */
  public final void setSwtMinQty( Integer p_swtMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    swt_min_qty = p_swtMinQty;
    swt_min_qty_is_set = true;
    swt_min_qty_is_modified = true;
  }


  /** 
   * <em>recruit_unit_qty</em>カラムの値を設定します。 
   *
   * @@param p_recruitUnitQty <em>recruit_unit_qty</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setRecruitUnitQty( int p_recruitUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_unit_qty = new Integer(p_recruitUnitQty);
    recruit_unit_qty_is_set = true;
    recruit_unit_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>recruit_unit_qty</em>カラムに値を設定します。 
   */
  public final void setRecruitUnitQty( Integer p_recruitUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_unit_qty = p_recruitUnitQty;
    recruit_unit_qty_is_set = true;
    recruit_unit_qty_is_modified = true;
  }


  /** 
   * <em>buy_unit_qty</em>カラムの値を設定します。 
   *
   * @@param p_buyUnitQty <em>buy_unit_qty</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setBuyUnitQty( int p_buyUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_unit_qty = new Integer(p_buyUnitQty);
    buy_unit_qty_is_set = true;
    buy_unit_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_unit_qty</em>カラムに値を設定します。 
   */
  public final void setBuyUnitQty( Integer p_buyUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_unit_qty = p_buyUnitQty;
    buy_unit_qty_is_set = true;
    buy_unit_qty_is_modified = true;
  }


  /** 
   * <em>sell_unit_qty</em>カラムの値を設定します。 
   *
   * @@param p_sellUnitQty <em>sell_unit_qty</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setSellUnitQty( int p_sellUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_unit_qty = new Integer(p_sellUnitQty);
    sell_unit_qty_is_set = true;
    sell_unit_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_unit_qty</em>カラムに値を設定します。 
   */
  public final void setSellUnitQty( Integer p_sellUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_unit_qty = p_sellUnitQty;
    sell_unit_qty_is_set = true;
    sell_unit_qty_is_modified = true;
  }


  /** 
   * <em>swt_unit_qty</em>カラムの値を設定します。 
   *
   * @@param p_swtUnitQty <em>swt_unit_qty</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setSwtUnitQty( int p_swtUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_unit_qty = new Integer(p_swtUnitQty);
    swt_unit_qty_is_set = true;
    swt_unit_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>swt_unit_qty</em>カラムに値を設定します。 
   */
  public final void setSwtUnitQty( Integer p_swtUnitQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    swt_unit_qty = p_swtUnitQty;
    swt_unit_qty_is_set = true;
    swt_unit_qty_is_modified = true;
  }


  /** 
   * <em>recruit_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_recruitMinAmt <em>recruit_min_amt</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setRecruitMinAmt( int p_recruitMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_min_amt = new Integer(p_recruitMinAmt);
    recruit_min_amt_is_set = true;
    recruit_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>recruit_min_amt</em>カラムに値を設定します。 
   */
  public final void setRecruitMinAmt( Integer p_recruitMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_min_amt = p_recruitMinAmt;
    recruit_min_amt_is_set = true;
    recruit_min_amt_is_modified = true;
  }


  /** 
   * <em>buy_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_buyMinAmt <em>buy_min_amt</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setBuyMinAmt( int p_buyMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_min_amt = new Integer(p_buyMinAmt);
    buy_min_amt_is_set = true;
    buy_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_min_amt</em>カラムに値を設定します。 
   */
  public final void setBuyMinAmt( Integer p_buyMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_min_amt = p_buyMinAmt;
    buy_min_amt_is_set = true;
    buy_min_amt_is_modified = true;
  }


  /** 
   * <em>sell_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_sellMinAmt <em>sell_min_amt</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setSellMinAmt( int p_sellMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_min_amt = new Integer(p_sellMinAmt);
    sell_min_amt_is_set = true;
    sell_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_min_amt</em>カラムに値を設定します。 
   */
  public final void setSellMinAmt( Integer p_sellMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_min_amt = p_sellMinAmt;
    sell_min_amt_is_set = true;
    sell_min_amt_is_modified = true;
  }


  /** 
   * <em>swt_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_swtMinAmt <em>swt_min_amt</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setSwtMinAmt( int p_swtMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_min_amt = new Integer(p_swtMinAmt);
    swt_min_amt_is_set = true;
    swt_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>swt_min_amt</em>カラムに値を設定します。 
   */
  public final void setSwtMinAmt( Integer p_swtMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    swt_min_amt = p_swtMinAmt;
    swt_min_amt_is_set = true;
    swt_min_amt_is_modified = true;
  }


  /** 
   * <em>recruit_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_recruitUnitAmt <em>recruit_unit_amt</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setRecruitUnitAmt( int p_recruitUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_unit_amt = new Integer(p_recruitUnitAmt);
    recruit_unit_amt_is_set = true;
    recruit_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>recruit_unit_amt</em>カラムに値を設定します。 
   */
  public final void setRecruitUnitAmt( Integer p_recruitUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_unit_amt = p_recruitUnitAmt;
    recruit_unit_amt_is_set = true;
    recruit_unit_amt_is_modified = true;
  }


  /** 
   * <em>buy_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_buyUnitAmt <em>buy_unit_amt</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setBuyUnitAmt( int p_buyUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_unit_amt = new Integer(p_buyUnitAmt);
    buy_unit_amt_is_set = true;
    buy_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_unit_amt</em>カラムに値を設定します。 
   */
  public final void setBuyUnitAmt( Integer p_buyUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_unit_amt = p_buyUnitAmt;
    buy_unit_amt_is_set = true;
    buy_unit_amt_is_modified = true;
  }


  /** 
   * <em>sell_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_sellUnitAmt <em>sell_unit_amt</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setSellUnitAmt( int p_sellUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_unit_amt = new Integer(p_sellUnitAmt);
    sell_unit_amt_is_set = true;
    sell_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_unit_amt</em>カラムに値を設定します。 
   */
  public final void setSellUnitAmt( Integer p_sellUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_unit_amt = p_sellUnitAmt;
    sell_unit_amt_is_set = true;
    sell_unit_amt_is_modified = true;
  }


  /** 
   * <em>swt_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_swtUnitAmt <em>swt_unit_amt</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setSwtUnitAmt( int p_swtUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_unit_amt = new Integer(p_swtUnitAmt);
    swt_unit_amt_is_set = true;
    swt_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>swt_unit_amt</em>カラムに値を設定します。 
   */
  public final void setSwtUnitAmt( Integer p_swtUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    swt_unit_amt = p_swtUnitAmt;
    swt_unit_amt_is_set = true;
    swt_unit_amt_is_modified = true;
  }


  /** 
   * <em>recruit_qty_spec_div</em>カラムの値を設定します。 
   *
   * @@param p_recruitQtySpecDiv <em>recruit_qty_spec_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRecruitQtySpecDiv( String p_recruitQtySpecDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_qty_spec_div = p_recruitQtySpecDiv;
    recruit_qty_spec_div_is_set = true;
    recruit_qty_spec_div_is_modified = true;
  }


  /** 
   * <em>recruit_amt_spec_div</em>カラムの値を設定します。 
   *
   * @@param p_recruitAmtSpecDiv <em>recruit_amt_spec_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRecruitAmtSpecDiv( String p_recruitAmtSpecDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_amt_spec_div = p_recruitAmtSpecDiv;
    recruit_amt_spec_div_is_set = true;
    recruit_amt_spec_div_is_modified = true;
  }


  /** 
   * <em>buy_qty_spec_div</em>カラムの値を設定します。 
   *
   * @@param p_buyQtySpecDiv <em>buy_qty_spec_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBuyQtySpecDiv( String p_buyQtySpecDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_qty_spec_div = p_buyQtySpecDiv;
    buy_qty_spec_div_is_set = true;
    buy_qty_spec_div_is_modified = true;
  }


  /** 
   * <em>buy_amt_spec_div</em>カラムの値を設定します。 
   *
   * @@param p_buyAmtSpecDiv <em>buy_amt_spec_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBuyAmtSpecDiv( String p_buyAmtSpecDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_amt_spec_div = p_buyAmtSpecDiv;
    buy_amt_spec_div_is_set = true;
    buy_amt_spec_div_is_modified = true;
  }


  /** 
   * <em>sell_qty_spec_div</em>カラムの値を設定します。 
   *
   * @@param p_sellQtySpecDiv <em>sell_qty_spec_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSellQtySpecDiv( String p_sellQtySpecDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_qty_spec_div = p_sellQtySpecDiv;
    sell_qty_spec_div_is_set = true;
    sell_qty_spec_div_is_modified = true;
  }


  /** 
   * <em>sell_amt_spec_div</em>カラムの値を設定します。 
   *
   * @@param p_sellAmtSpecDiv <em>sell_amt_spec_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSellAmtSpecDiv( String p_sellAmtSpecDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_amt_spec_div = p_sellAmtSpecDiv;
    sell_amt_spec_div_is_set = true;
    sell_amt_spec_div_is_modified = true;
  }


  /** 
   * <em>swt_qty_spec_div</em>カラムの値を設定します。 
   *
   * @@param p_swtQtySpecDiv <em>swt_qty_spec_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSwtQtySpecDiv( String p_swtQtySpecDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_qty_spec_div = p_swtQtySpecDiv;
    swt_qty_spec_div_is_set = true;
    swt_qty_spec_div_is_modified = true;
  }


  /** 
   * <em>swt_amt_spec_div</em>カラムの値を設定します。 
   *
   * @@param p_swtAmtSpecDiv <em>swt_amt_spec_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSwtAmtSpecDiv( String p_swtAmtSpecDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_amt_spec_div = p_swtAmtSpecDiv;
    swt_amt_spec_div_is_set = true;
    swt_amt_spec_div_is_modified = true;
  }


  /** 
   * <em>input_unit_div</em>カラムの値を設定します。 
   *
   * @@param p_inputUnitDiv <em>input_unit_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInputUnitDiv( String p_inputUnitDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    input_unit_div = p_inputUnitDiv;
    input_unit_div_is_set = true;
    input_unit_div_is_modified = true;
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
   * <em>sell_exception_div</em>カラムの値を設定します。 
   *
   * @@param p_sellExceptionDiv <em>sell_exception_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSellExceptionDiv( String p_sellExceptionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_exception_div = p_sellExceptionDiv;
    sell_exception_div_is_set = true;
    sell_exception_div_is_modified = true;
  }


  /** 
   * <em>swt_trade_div</em>カラムの値を設定します。 
   *
   * @@param p_swtTradeDiv <em>swt_trade_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSwtTradeDiv( String p_swtTradeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_trade_div = p_swtTradeDiv;
    swt_trade_div_is_set = true;
    swt_trade_div_is_modified = true;
  }


  /** 
   * <em>swt_group</em>カラムの値を設定します。 
   *
   * @@param p_swtGroup <em>swt_group</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setSwtGroup( String p_swtGroup )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_group = p_swtGroup;
    swt_group_is_set = true;
    swt_group_is_modified = true;
  }


  /** 
   * <em>swt_companion_subject_div</em>カラムの値を設定します。 
   *
   * @@param p_swtCompanionSubjectDiv <em>swt_companion_subject_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSwtCompanionSubjectDiv( String p_swtCompanionSubjectDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_companion_subject_div = p_swtCompanionSubjectDiv;
    swt_companion_subject_div_is_set = true;
    swt_companion_subject_div_is_modified = true;
  }


  /** 
   * <em>buy_disable_div</em>カラムの値を設定します。 
   *
   * @@param p_buyDisableDiv <em>buy_disable_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBuyDisableDiv( String p_buyDisableDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_disable_div = p_buyDisableDiv;
    buy_disable_div_is_set = true;
    buy_disable_div_is_modified = true;
  }


  /** 
   * <em>swt_perference_enable_div</em>カラムの値を設定します。 
   *
   * @@param p_swtPerferenceEnableDiv <em>swt_perference_enable_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSwtPerferenceEnableDiv( String p_swtPerferenceEnableDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_perference_enable_div = p_swtPerferenceEnableDiv;
    swt_perference_enable_div_is_set = true;
    swt_perference_enable_div_is_modified = true;
  }


  /** 
   * <em>redemption_perference_price</em>カラムの値を設定します。 
   *
   * @@param p_redemptionPerferencePrice <em>redemption_perference_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRedemptionPerferencePrice( double p_redemptionPerferencePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    redemption_perference_price = new Double(p_redemptionPerferencePrice);
    redemption_perference_price_is_set = true;
    redemption_perference_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>redemption_perference_price</em>カラムに値を設定します。 
   */
  public final void setRedemptionPerferencePrice( Double p_redemptionPerferencePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    redemption_perference_price = p_redemptionPerferencePrice;
    redemption_perference_price_is_set = true;
    redemption_perference_price_is_modified = true;
  }


  /** 
   * <em>redemption_commission_rate</em>カラムの値を設定します。 
   *
   * @@param p_redemptionCommissionRate <em>redemption_commission_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRedemptionCommissionRate( double p_redemptionCommissionRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    redemption_commission_rate = new Double(p_redemptionCommissionRate);
    redemption_commission_rate_is_set = true;
    redemption_commission_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>redemption_commission_rate</em>カラムに値を設定します。 
   */
  public final void setRedemptionCommissionRate( Double p_redemptionCommissionRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    redemption_commission_rate = p_redemptionCommissionRate;
    redemption_commission_rate_is_set = true;
    redemption_commission_rate_is_modified = true;
  }


  /** 
   * <em>pre_redemption_begin_date</em>カラムの値を設定します。 
   *
   * @@param p_preRedemptionBeginDate <em>pre_redemption_begin_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setPreRedemptionBeginDate( java.sql.Timestamp p_preRedemptionBeginDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pre_redemption_begin_date = p_preRedemptionBeginDate;
    pre_redemption_begin_date_is_set = true;
    pre_redemption_begin_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>pre_redemption_begin_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setPreRedemptionBeginDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    pre_redemption_begin_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    pre_redemption_begin_date_is_set = true;
    pre_redemption_begin_date_is_modified = true;
  }


  /** 
   * <em>closing_date_cal_div</em>カラムの値を設定します。 
   *
   * @@param p_closingDateCalDiv <em>closing_date_cal_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setClosingDateCalDiv( String p_closingDateCalDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    closing_date_cal_div = p_closingDateCalDiv;
    closing_date_cal_div_is_set = true;
    closing_date_cal_div_is_modified = true;
  }


  /** 
   * <em>closing_spec_date</em>カラムの値を設定します。 
   *
   * @@param p_closingSpecDate <em>closing_spec_date</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setClosingSpecDate( String p_closingSpecDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    closing_spec_date = p_closingSpecDate;
    closing_spec_date_is_set = true;
    closing_spec_date_is_modified = true;
  }


  /** 
   * <em>annual_profit_qty_times</em>カラムの値を設定します。 
   *
   * @@param p_annualProfitQtyTimes <em>annual_profit_qty_times</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setAnnualProfitQtyTimes( String p_annualProfitQtyTimes )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    annual_profit_qty_times = p_annualProfitQtyTimes;
    annual_profit_qty_times_is_set = true;
    annual_profit_qty_times_is_modified = true;
  }


  /** 
   * <em>sell_advance_order_div</em>カラムの値を設定します。 
   *
   * @@param p_sellAdvanceOrderDiv <em>sell_advance_order_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSellAdvanceOrderDiv( String p_sellAdvanceOrderDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_advance_order_div = p_sellAdvanceOrderDiv;
    sell_advance_order_div_is_set = true;
    sell_advance_order_div_is_modified = true;
  }


  /** 
   * <em>buy_advance_order_div</em>カラムの値を設定します。 
   *
   * @@param p_buyAdvanceOrderDiv <em>buy_advance_order_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBuyAdvanceOrderDiv( String p_buyAdvanceOrderDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_advance_order_div = p_buyAdvanceOrderDiv;
    buy_advance_order_div_is_set = true;
    buy_advance_order_div_is_modified = true;
  }


  /** 
   * <em>sell_undelivering_term</em>カラムの値を設定します。 
   *
   * @@param p_sellUndeliveringTerm <em>sell_undelivering_term</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setSellUndeliveringTerm( String p_sellUndeliveringTerm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_undelivering_term = p_sellUndeliveringTerm;
    sell_undelivering_term_is_set = true;
    sell_undelivering_term_is_modified = true;
  }


  /** 
   * <em>buy_undelivering_term</em>カラムの値を設定します。 
   *
   * @@param p_buyUndeliveringTerm <em>buy_undelivering_term</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setBuyUndeliveringTerm( String p_buyUndeliveringTerm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_undelivering_term = p_buyUndeliveringTerm;
    buy_undelivering_term_is_set = true;
    buy_undelivering_term_is_modified = true;
  }


  /** 
   * <em>recruit_stop_div</em>カラムの値を設定します。 
   *
   * @@param p_recruitStopDiv <em>recruit_stop_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRecruitStopDiv( String p_recruitStopDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_stop_div = p_recruitStopDiv;
    recruit_stop_div_is_set = true;
    recruit_stop_div_is_modified = true;
  }


  /** 
   * <em>dealing_stop_div</em>カラムの値を設定します。 
   *
   * @@param p_dealingStopDiv <em>dealing_stop_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDealingStopDiv( String p_dealingStopDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dealing_stop_div = p_dealingStopDiv;
    dealing_stop_div_is_set = true;
    dealing_stop_div_is_modified = true;
  }


  /** 
   * <em>storage_stop_div</em>カラムの値を設定します。 
   *
   * @@param p_storageStopDiv <em>storage_stop_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStorageStopDiv( String p_storageStopDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    storage_stop_div = p_storageStopDiv;
    storage_stop_div_is_set = true;
    storage_stop_div_is_modified = true;
  }


  /** 
   * <em>specific_corpus_app</em>カラムの値を設定します。 
   *
   * @@param p_specificCorpusApp <em>specific_corpus_app</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSpecificCorpusApp( String p_specificCorpusApp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    specific_corpus_app = p_specificCorpusApp;
    specific_corpus_app_is_set = true;
    specific_corpus_app_is_modified = true;
  }


  /** 
   * <em>perference_qualify</em>カラムの値を設定します。 
   *
   * @@param p_perferenceQualify <em>perference_qualify</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPerferenceQualify( String p_perferenceQualify )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    perference_qualify = p_perferenceQualify;
    perference_qualify_is_set = true;
    perference_qualify_is_modified = true;
  }


  /** 
   * <em>collateral_qualify</em>カラムの値を設定します。 
   *
   * @@param p_collateralQualify <em>collateral_qualify</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCollateralQualify( String p_collateralQualify )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    collateral_qualify = p_collateralQualify;
    collateral_qualify_is_set = true;
    collateral_qualify_is_modified = true;
  }


  /** 
   * <em>operate_report_send_div</em>カラムの値を設定します。 
   *
   * @@param p_operateReportSendDiv <em>operate_report_send_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOperateReportSendDiv( String p_operateReportSendDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    operate_report_send_div = p_operateReportSendDiv;
    operate_report_send_div_is_set = true;
    operate_report_send_div_is_modified = true;
  }


  /** 
   * <em>operate_report_send_month1</em>カラムの値を設定します。 
   *
   * @@param p_operateReportSendMonth1 <em>operate_report_send_month1</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setOperateReportSendMonth1( String p_operateReportSendMonth1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    operate_report_send_month1 = p_operateReportSendMonth1;
    operate_report_send_month1_is_set = true;
    operate_report_send_month1_is_modified = true;
  }


  /** 
   * <em>operate_report_send_month2</em>カラムの値を設定します。 
   *
   * @@param p_operateReportSendMonth2 <em>operate_report_send_month2</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setOperateReportSendMonth2( String p_operateReportSendMonth2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    operate_report_send_month2 = p_operateReportSendMonth2;
    operate_report_send_month2_is_set = true;
    operate_report_send_month2_is_modified = true;
  }


  /** 
   * <em>biz_asset_code</em>カラムの値を設定します。 
   *
   * @@param p_bizAssetCode <em>biz_asset_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBizAssetCode( String p_bizAssetCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    biz_asset_code = p_bizAssetCode;
    biz_asset_code_is_set = true;
    biz_asset_code_is_modified = true;
  }


  /** 
   * <em>biz_asset_name</em>カラムの値を設定します。 
   *
   * @@param p_bizAssetName <em>biz_asset_name</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setBizAssetName( String p_bizAssetName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    biz_asset_name = p_bizAssetName;
    biz_asset_name_is_set = true;
    biz_asset_name_is_modified = true;
  }


  /** 
   * <em>prospectus_conversion_date</em>カラムの値を設定します。 
   *
   * @@param p_prospectusConversionDate <em>prospectus_conversion_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setProspectusConversionDate( java.sql.Timestamp p_prospectusConversionDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    prospectus_conversion_date = p_prospectusConversionDate;
    prospectus_conversion_date_is_set = true;
    prospectus_conversion_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>prospectus_conversion_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setProspectusConversionDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    prospectus_conversion_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    prospectus_conversion_date_is_set = true;
    prospectus_conversion_date_is_modified = true;
  }


  /** 
   * <em>frgn_buy_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_frgnBuyMinAmt <em>frgn_buy_min_amt</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setFrgnBuyMinAmt( int p_frgnBuyMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_buy_min_amt = new Integer(p_frgnBuyMinAmt);
    frgn_buy_min_amt_is_set = true;
    frgn_buy_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>frgn_buy_min_amt</em>カラムに値を設定します。 
   */
  public final void setFrgnBuyMinAmt( Integer p_frgnBuyMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_buy_min_amt = p_frgnBuyMinAmt;
    frgn_buy_min_amt_is_set = true;
    frgn_buy_min_amt_is_modified = true;
  }


  /** 
   * <em>frgn_sell_min_amt</em>カラムの値を設定します。 
   *
   * @@param p_frgnSellMinAmt <em>frgn_sell_min_amt</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setFrgnSellMinAmt( int p_frgnSellMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_sell_min_amt = new Integer(p_frgnSellMinAmt);
    frgn_sell_min_amt_is_set = true;
    frgn_sell_min_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>frgn_sell_min_amt</em>カラムに値を設定します。 
   */
  public final void setFrgnSellMinAmt( Integer p_frgnSellMinAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_sell_min_amt = p_frgnSellMinAmt;
    frgn_sell_min_amt_is_set = true;
    frgn_sell_min_amt_is_modified = true;
  }


  /** 
   * <em>frgn_buy_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_frgnBuyUnitAmt <em>frgn_buy_unit_amt</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setFrgnBuyUnitAmt( int p_frgnBuyUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_buy_unit_amt = new Integer(p_frgnBuyUnitAmt);
    frgn_buy_unit_amt_is_set = true;
    frgn_buy_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>frgn_buy_unit_amt</em>カラムに値を設定します。 
   */
  public final void setFrgnBuyUnitAmt( Integer p_frgnBuyUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_buy_unit_amt = p_frgnBuyUnitAmt;
    frgn_buy_unit_amt_is_set = true;
    frgn_buy_unit_amt_is_modified = true;
  }


  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値を設定します。 
   *
   * @@param p_frgnSellUnitAmt <em>frgn_sell_unit_amt</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setFrgnSellUnitAmt( int p_frgnSellUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_sell_unit_amt = new Integer(p_frgnSellUnitAmt);
    frgn_sell_unit_amt_is_set = true;
    frgn_sell_unit_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>frgn_sell_unit_amt</em>カラムに値を設定します。 
   */
  public final void setFrgnSellUnitAmt( Integer p_frgnSellUnitAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    frgn_sell_unit_amt = p_frgnSellUnitAmt;
    frgn_sell_unit_amt_is_set = true;
    frgn_sell_unit_amt_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("annual_profit_qty_times") ) {
                    return this.annual_profit_qty_times;
                }
                break;
            case 'b':
                if ( name.equals("buy_min_qty") ) {
                    return this.buy_min_qty;
                }
                else if ( name.equals("buy_unit_qty") ) {
                    return this.buy_unit_qty;
                }
                else if ( name.equals("buy_min_amt") ) {
                    return this.buy_min_amt;
                }
                else if ( name.equals("buy_unit_amt") ) {
                    return this.buy_unit_amt;
                }
                else if ( name.equals("buy_qty_spec_div") ) {
                    return this.buy_qty_spec_div;
                }
                else if ( name.equals("buy_amt_spec_div") ) {
                    return this.buy_amt_spec_div;
                }
                else if ( name.equals("buy_disable_div") ) {
                    return this.buy_disable_div;
                }
                else if ( name.equals("buy_advance_order_div") ) {
                    return this.buy_advance_order_div;
                }
                else if ( name.equals("buy_undelivering_term") ) {
                    return this.buy_undelivering_term;
                }
                else if ( name.equals("biz_asset_code") ) {
                    return this.biz_asset_code;
                }
                else if ( name.equals("biz_asset_name") ) {
                    return this.biz_asset_name;
                }
                break;
            case 'c':
                if ( name.equals("cal_price_div") ) {
                    return this.cal_price_div;
                }
                else if ( name.equals("closing_date_cal_div") ) {
                    return this.closing_date_cal_div;
                }
                else if ( name.equals("closing_spec_date") ) {
                    return this.closing_spec_date;
                }
                else if ( name.equals("collateral_qualify") ) {
                    return this.collateral_qualify;
                }
                break;
            case 'd':
                if ( name.equals("dealing_unit_qty") ) {
                    return this.dealing_unit_qty;
                }
                else if ( name.equals("dealing_stop_div") ) {
                    return this.dealing_stop_div;
                }
                break;
            case 'f':
                if ( name.equals("frgn_buy_min_amt") ) {
                    return this.frgn_buy_min_amt;
                }
                else if ( name.equals("frgn_sell_min_amt") ) {
                    return this.frgn_sell_min_amt;
                }
                else if ( name.equals("frgn_buy_unit_amt") ) {
                    return this.frgn_buy_unit_amt;
                }
                else if ( name.equals("frgn_sell_unit_amt") ) {
                    return this.frgn_sell_unit_amt;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("input_unit_div") ) {
                    return this.input_unit_div;
                }
                break;
            case 'l':
                if ( name.equals("last_update_date") ) {
                    return this.last_update_date;
                }
                break;
            case 'o':
                if ( name.equals("operate_report_send_div") ) {
                    return this.operate_report_send_div;
                }
                else if ( name.equals("operate_report_send_month1") ) {
                    return this.operate_report_send_month1;
                }
                else if ( name.equals("operate_report_send_month2") ) {
                    return this.operate_report_send_month2;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("product_name_kana") ) {
                    return this.product_name_kana;
                }
                else if ( name.equals("product_name_kanji") ) {
                    return this.product_name_kanji;
                }
                else if ( name.equals("pre_redemption_begin_date") ) {
                    return this.pre_redemption_begin_date;
                }
                else if ( name.equals("perference_qualify") ) {
                    return this.perference_qualify;
                }
                else if ( name.equals("prospectus_conversion_date") ) {
                    return this.prospectus_conversion_date;
                }
                break;
            case 'r':
                if ( name.equals("reg_date") ) {
                    return this.reg_date;
                }
                else if ( name.equals("reg_div") ) {
                    return this.reg_div;
                }
                else if ( name.equals("recruit_unit") ) {
                    return this.recruit_unit;
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
                else if ( name.equals("recruit_qty_spec_div") ) {
                    return this.recruit_qty_spec_div;
                }
                else if ( name.equals("recruit_amt_spec_div") ) {
                    return this.recruit_amt_spec_div;
                }
                else if ( name.equals("redemption_perference_price") ) {
                    return this.redemption_perference_price;
                }
                else if ( name.equals("redemption_commission_rate") ) {
                    return this.redemption_commission_rate;
                }
                else if ( name.equals("recruit_stop_div") ) {
                    return this.recruit_stop_div;
                }
                break;
            case 's':
                if ( name.equals("sell_min_qty") ) {
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
                else if ( name.equals("sell_qty_spec_div") ) {
                    return this.sell_qty_spec_div;
                }
                else if ( name.equals("sell_amt_spec_div") ) {
                    return this.sell_amt_spec_div;
                }
                else if ( name.equals("swt_qty_spec_div") ) {
                    return this.swt_qty_spec_div;
                }
                else if ( name.equals("swt_amt_spec_div") ) {
                    return this.swt_amt_spec_div;
                }
                else if ( name.equals("sell_exception_div") ) {
                    return this.sell_exception_div;
                }
                else if ( name.equals("swt_trade_div") ) {
                    return this.swt_trade_div;
                }
                else if ( name.equals("swt_group") ) {
                    return this.swt_group;
                }
                else if ( name.equals("swt_companion_subject_div") ) {
                    return this.swt_companion_subject_div;
                }
                else if ( name.equals("swt_perference_enable_div") ) {
                    return this.swt_perference_enable_div;
                }
                else if ( name.equals("sell_advance_order_div") ) {
                    return this.sell_advance_order_div;
                }
                else if ( name.equals("sell_undelivering_term") ) {
                    return this.sell_undelivering_term;
                }
                else if ( name.equals("storage_stop_div") ) {
                    return this.storage_stop_div;
                }
                else if ( name.equals("specific_corpus_app") ) {
                    return this.specific_corpus_app;
                }
                break;
            case 'w':
                if ( name.equals("work_date") ) {
                    return this.work_date;
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
                if ( name.equals("annual_profit_qty_times") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'annual_profit_qty_times' must be String: '"+value+"' is not." );
                    this.annual_profit_qty_times = (String) value;
                    if (this.annual_profit_qty_times_is_set)
                        this.annual_profit_qty_times_is_modified = true;
                    this.annual_profit_qty_times_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("buy_min_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'buy_min_qty' must be Integer: '"+value+"' is not." );
                    this.buy_min_qty = (Integer) value;
                    if (this.buy_min_qty_is_set)
                        this.buy_min_qty_is_modified = true;
                    this.buy_min_qty_is_set = true;
                    return;
                }
                else if ( name.equals("buy_unit_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'buy_unit_qty' must be Integer: '"+value+"' is not." );
                    this.buy_unit_qty = (Integer) value;
                    if (this.buy_unit_qty_is_set)
                        this.buy_unit_qty_is_modified = true;
                    this.buy_unit_qty_is_set = true;
                    return;
                }
                else if ( name.equals("buy_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'buy_min_amt' must be Integer: '"+value+"' is not." );
                    this.buy_min_amt = (Integer) value;
                    if (this.buy_min_amt_is_set)
                        this.buy_min_amt_is_modified = true;
                    this.buy_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("buy_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'buy_unit_amt' must be Integer: '"+value+"' is not." );
                    this.buy_unit_amt = (Integer) value;
                    if (this.buy_unit_amt_is_set)
                        this.buy_unit_amt_is_modified = true;
                    this.buy_unit_amt_is_set = true;
                    return;
                }
                else if ( name.equals("buy_qty_spec_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_qty_spec_div' must be String: '"+value+"' is not." );
                    this.buy_qty_spec_div = (String) value;
                    if (this.buy_qty_spec_div_is_set)
                        this.buy_qty_spec_div_is_modified = true;
                    this.buy_qty_spec_div_is_set = true;
                    return;
                }
                else if ( name.equals("buy_amt_spec_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_amt_spec_div' must be String: '"+value+"' is not." );
                    this.buy_amt_spec_div = (String) value;
                    if (this.buy_amt_spec_div_is_set)
                        this.buy_amt_spec_div_is_modified = true;
                    this.buy_amt_spec_div_is_set = true;
                    return;
                }
                else if ( name.equals("buy_disable_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_disable_div' must be String: '"+value+"' is not." );
                    this.buy_disable_div = (String) value;
                    if (this.buy_disable_div_is_set)
                        this.buy_disable_div_is_modified = true;
                    this.buy_disable_div_is_set = true;
                    return;
                }
                else if ( name.equals("buy_advance_order_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_advance_order_div' must be String: '"+value+"' is not." );
                    this.buy_advance_order_div = (String) value;
                    if (this.buy_advance_order_div_is_set)
                        this.buy_advance_order_div_is_modified = true;
                    this.buy_advance_order_div_is_set = true;
                    return;
                }
                else if ( name.equals("buy_undelivering_term") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_undelivering_term' must be String: '"+value+"' is not." );
                    this.buy_undelivering_term = (String) value;
                    if (this.buy_undelivering_term_is_set)
                        this.buy_undelivering_term_is_modified = true;
                    this.buy_undelivering_term_is_set = true;
                    return;
                }
                else if ( name.equals("biz_asset_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'biz_asset_code' must be String: '"+value+"' is not." );
                    this.biz_asset_code = (String) value;
                    if (this.biz_asset_code_is_set)
                        this.biz_asset_code_is_modified = true;
                    this.biz_asset_code_is_set = true;
                    return;
                }
                else if ( name.equals("biz_asset_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'biz_asset_name' must be String: '"+value+"' is not." );
                    this.biz_asset_name = (String) value;
                    if (this.biz_asset_name_is_set)
                        this.biz_asset_name_is_modified = true;
                    this.biz_asset_name_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("cal_price_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cal_price_div' must be String: '"+value+"' is not." );
                    this.cal_price_div = (String) value;
                    if (this.cal_price_div_is_set)
                        this.cal_price_div_is_modified = true;
                    this.cal_price_div_is_set = true;
                    return;
                }
                else if ( name.equals("closing_date_cal_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'closing_date_cal_div' must be String: '"+value+"' is not." );
                    this.closing_date_cal_div = (String) value;
                    if (this.closing_date_cal_div_is_set)
                        this.closing_date_cal_div_is_modified = true;
                    this.closing_date_cal_div_is_set = true;
                    return;
                }
                else if ( name.equals("closing_spec_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'closing_spec_date' must be String: '"+value+"' is not." );
                    this.closing_spec_date = (String) value;
                    if (this.closing_spec_date_is_set)
                        this.closing_spec_date_is_modified = true;
                    this.closing_spec_date_is_set = true;
                    return;
                }
                else if ( name.equals("collateral_qualify") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'collateral_qualify' must be String: '"+value+"' is not." );
                    this.collateral_qualify = (String) value;
                    if (this.collateral_qualify_is_set)
                        this.collateral_qualify_is_modified = true;
                    this.collateral_qualify_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("dealing_unit_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'dealing_unit_qty' must be Integer: '"+value+"' is not." );
                    this.dealing_unit_qty = (Integer) value;
                    if (this.dealing_unit_qty_is_set)
                        this.dealing_unit_qty_is_modified = true;
                    this.dealing_unit_qty_is_set = true;
                    return;
                }
                else if ( name.equals("dealing_stop_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dealing_stop_div' must be String: '"+value+"' is not." );
                    this.dealing_stop_div = (String) value;
                    if (this.dealing_stop_div_is_set)
                        this.dealing_stop_div_is_modified = true;
                    this.dealing_stop_div_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("frgn_buy_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'frgn_buy_min_amt' must be Integer: '"+value+"' is not." );
                    this.frgn_buy_min_amt = (Integer) value;
                    if (this.frgn_buy_min_amt_is_set)
                        this.frgn_buy_min_amt_is_modified = true;
                    this.frgn_buy_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("frgn_sell_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'frgn_sell_min_amt' must be Integer: '"+value+"' is not." );
                    this.frgn_sell_min_amt = (Integer) value;
                    if (this.frgn_sell_min_amt_is_set)
                        this.frgn_sell_min_amt_is_modified = true;
                    this.frgn_sell_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("frgn_buy_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'frgn_buy_unit_amt' must be Integer: '"+value+"' is not." );
                    this.frgn_buy_unit_amt = (Integer) value;
                    if (this.frgn_buy_unit_amt_is_set)
                        this.frgn_buy_unit_amt_is_modified = true;
                    this.frgn_buy_unit_amt_is_set = true;
                    return;
                }
                else if ( name.equals("frgn_sell_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'frgn_sell_unit_amt' must be Integer: '"+value+"' is not." );
                    this.frgn_sell_unit_amt = (Integer) value;
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
                else if ( name.equals("input_unit_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'input_unit_div' must be String: '"+value+"' is not." );
                    this.input_unit_div = (String) value;
                    if (this.input_unit_div_is_set)
                        this.input_unit_div_is_modified = true;
                    this.input_unit_div_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_update_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_update_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_update_date = (java.sql.Timestamp) value;
                    if (this.last_update_date_is_set)
                        this.last_update_date_is_modified = true;
                    this.last_update_date_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("operate_report_send_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'operate_report_send_div' must be String: '"+value+"' is not." );
                    this.operate_report_send_div = (String) value;
                    if (this.operate_report_send_div_is_set)
                        this.operate_report_send_div_is_modified = true;
                    this.operate_report_send_div_is_set = true;
                    return;
                }
                else if ( name.equals("operate_report_send_month1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'operate_report_send_month1' must be String: '"+value+"' is not." );
                    this.operate_report_send_month1 = (String) value;
                    if (this.operate_report_send_month1_is_set)
                        this.operate_report_send_month1_is_modified = true;
                    this.operate_report_send_month1_is_set = true;
                    return;
                }
                else if ( name.equals("operate_report_send_month2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'operate_report_send_month2' must be String: '"+value+"' is not." );
                    this.operate_report_send_month2 = (String) value;
                    if (this.operate_report_send_month2_is_set)
                        this.operate_report_send_month2_is_modified = true;
                    this.operate_report_send_month2_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                else if ( name.equals("product_name_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_name_kana' must be String: '"+value+"' is not." );
                    this.product_name_kana = (String) value;
                    if (this.product_name_kana_is_set)
                        this.product_name_kana_is_modified = true;
                    this.product_name_kana_is_set = true;
                    return;
                }
                else if ( name.equals("product_name_kanji") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_name_kanji' must be String: '"+value+"' is not." );
                    this.product_name_kanji = (String) value;
                    if (this.product_name_kanji_is_set)
                        this.product_name_kanji_is_modified = true;
                    this.product_name_kanji_is_set = true;
                    return;
                }
                else if ( name.equals("pre_redemption_begin_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'pre_redemption_begin_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.pre_redemption_begin_date = (java.sql.Timestamp) value;
                    if (this.pre_redemption_begin_date_is_set)
                        this.pre_redemption_begin_date_is_modified = true;
                    this.pre_redemption_begin_date_is_set = true;
                    return;
                }
                else if ( name.equals("perference_qualify") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'perference_qualify' must be String: '"+value+"' is not." );
                    this.perference_qualify = (String) value;
                    if (this.perference_qualify_is_set)
                        this.perference_qualify_is_modified = true;
                    this.perference_qualify_is_set = true;
                    return;
                }
                else if ( name.equals("prospectus_conversion_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'prospectus_conversion_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.prospectus_conversion_date = (java.sql.Timestamp) value;
                    if (this.prospectus_conversion_date_is_set)
                        this.prospectus_conversion_date_is_modified = true;
                    this.prospectus_conversion_date_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("reg_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'reg_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.reg_date = (java.sql.Timestamp) value;
                    if (this.reg_date_is_set)
                        this.reg_date_is_modified = true;
                    this.reg_date_is_set = true;
                    return;
                }
                else if ( name.equals("reg_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reg_div' must be String: '"+value+"' is not." );
                    this.reg_div = (String) value;
                    if (this.reg_div_is_set)
                        this.reg_div_is_modified = true;
                    this.reg_div_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_unit") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'recruit_unit' must be Integer: '"+value+"' is not." );
                    this.recruit_unit = (Integer) value;
                    if (this.recruit_unit_is_set)
                        this.recruit_unit_is_modified = true;
                    this.recruit_unit_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_min_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'recruit_min_qty' must be Integer: '"+value+"' is not." );
                    this.recruit_min_qty = (Integer) value;
                    if (this.recruit_min_qty_is_set)
                        this.recruit_min_qty_is_modified = true;
                    this.recruit_min_qty_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_unit_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'recruit_unit_qty' must be Integer: '"+value+"' is not." );
                    this.recruit_unit_qty = (Integer) value;
                    if (this.recruit_unit_qty_is_set)
                        this.recruit_unit_qty_is_modified = true;
                    this.recruit_unit_qty_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'recruit_min_amt' must be Integer: '"+value+"' is not." );
                    this.recruit_min_amt = (Integer) value;
                    if (this.recruit_min_amt_is_set)
                        this.recruit_min_amt_is_modified = true;
                    this.recruit_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'recruit_unit_amt' must be Integer: '"+value+"' is not." );
                    this.recruit_unit_amt = (Integer) value;
                    if (this.recruit_unit_amt_is_set)
                        this.recruit_unit_amt_is_modified = true;
                    this.recruit_unit_amt_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_qty_spec_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'recruit_qty_spec_div' must be String: '"+value+"' is not." );
                    this.recruit_qty_spec_div = (String) value;
                    if (this.recruit_qty_spec_div_is_set)
                        this.recruit_qty_spec_div_is_modified = true;
                    this.recruit_qty_spec_div_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_amt_spec_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'recruit_amt_spec_div' must be String: '"+value+"' is not." );
                    this.recruit_amt_spec_div = (String) value;
                    if (this.recruit_amt_spec_div_is_set)
                        this.recruit_amt_spec_div_is_modified = true;
                    this.recruit_amt_spec_div_is_set = true;
                    return;
                }
                else if ( name.equals("redemption_perference_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'redemption_perference_price' must be Double: '"+value+"' is not." );
                    this.redemption_perference_price = (Double) value;
                    if (this.redemption_perference_price_is_set)
                        this.redemption_perference_price_is_modified = true;
                    this.redemption_perference_price_is_set = true;
                    return;
                }
                else if ( name.equals("redemption_commission_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'redemption_commission_rate' must be Double: '"+value+"' is not." );
                    this.redemption_commission_rate = (Double) value;
                    if (this.redemption_commission_rate_is_set)
                        this.redemption_commission_rate_is_modified = true;
                    this.redemption_commission_rate_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_stop_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'recruit_stop_div' must be String: '"+value+"' is not." );
                    this.recruit_stop_div = (String) value;
                    if (this.recruit_stop_div_is_set)
                        this.recruit_stop_div_is_modified = true;
                    this.recruit_stop_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sell_min_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sell_min_qty' must be Integer: '"+value+"' is not." );
                    this.sell_min_qty = (Integer) value;
                    if (this.sell_min_qty_is_set)
                        this.sell_min_qty_is_modified = true;
                    this.sell_min_qty_is_set = true;
                    return;
                }
                else if ( name.equals("swt_min_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'swt_min_qty' must be Integer: '"+value+"' is not." );
                    this.swt_min_qty = (Integer) value;
                    if (this.swt_min_qty_is_set)
                        this.swt_min_qty_is_modified = true;
                    this.swt_min_qty_is_set = true;
                    return;
                }
                else if ( name.equals("sell_unit_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sell_unit_qty' must be Integer: '"+value+"' is not." );
                    this.sell_unit_qty = (Integer) value;
                    if (this.sell_unit_qty_is_set)
                        this.sell_unit_qty_is_modified = true;
                    this.sell_unit_qty_is_set = true;
                    return;
                }
                else if ( name.equals("swt_unit_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'swt_unit_qty' must be Integer: '"+value+"' is not." );
                    this.swt_unit_qty = (Integer) value;
                    if (this.swt_unit_qty_is_set)
                        this.swt_unit_qty_is_modified = true;
                    this.swt_unit_qty_is_set = true;
                    return;
                }
                else if ( name.equals("sell_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sell_min_amt' must be Integer: '"+value+"' is not." );
                    this.sell_min_amt = (Integer) value;
                    if (this.sell_min_amt_is_set)
                        this.sell_min_amt_is_modified = true;
                    this.sell_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("swt_min_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'swt_min_amt' must be Integer: '"+value+"' is not." );
                    this.swt_min_amt = (Integer) value;
                    if (this.swt_min_amt_is_set)
                        this.swt_min_amt_is_modified = true;
                    this.swt_min_amt_is_set = true;
                    return;
                }
                else if ( name.equals("sell_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sell_unit_amt' must be Integer: '"+value+"' is not." );
                    this.sell_unit_amt = (Integer) value;
                    if (this.sell_unit_amt_is_set)
                        this.sell_unit_amt_is_modified = true;
                    this.sell_unit_amt_is_set = true;
                    return;
                }
                else if ( name.equals("swt_unit_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'swt_unit_amt' must be Integer: '"+value+"' is not." );
                    this.swt_unit_amt = (Integer) value;
                    if (this.swt_unit_amt_is_set)
                        this.swt_unit_amt_is_modified = true;
                    this.swt_unit_amt_is_set = true;
                    return;
                }
                else if ( name.equals("sell_qty_spec_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_qty_spec_div' must be String: '"+value+"' is not." );
                    this.sell_qty_spec_div = (String) value;
                    if (this.sell_qty_spec_div_is_set)
                        this.sell_qty_spec_div_is_modified = true;
                    this.sell_qty_spec_div_is_set = true;
                    return;
                }
                else if ( name.equals("sell_amt_spec_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_amt_spec_div' must be String: '"+value+"' is not." );
                    this.sell_amt_spec_div = (String) value;
                    if (this.sell_amt_spec_div_is_set)
                        this.sell_amt_spec_div_is_modified = true;
                    this.sell_amt_spec_div_is_set = true;
                    return;
                }
                else if ( name.equals("swt_qty_spec_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_qty_spec_div' must be String: '"+value+"' is not." );
                    this.swt_qty_spec_div = (String) value;
                    if (this.swt_qty_spec_div_is_set)
                        this.swt_qty_spec_div_is_modified = true;
                    this.swt_qty_spec_div_is_set = true;
                    return;
                }
                else if ( name.equals("swt_amt_spec_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_amt_spec_div' must be String: '"+value+"' is not." );
                    this.swt_amt_spec_div = (String) value;
                    if (this.swt_amt_spec_div_is_set)
                        this.swt_amt_spec_div_is_modified = true;
                    this.swt_amt_spec_div_is_set = true;
                    return;
                }
                else if ( name.equals("sell_exception_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_exception_div' must be String: '"+value+"' is not." );
                    this.sell_exception_div = (String) value;
                    if (this.sell_exception_div_is_set)
                        this.sell_exception_div_is_modified = true;
                    this.sell_exception_div_is_set = true;
                    return;
                }
                else if ( name.equals("swt_trade_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_trade_div' must be String: '"+value+"' is not." );
                    this.swt_trade_div = (String) value;
                    if (this.swt_trade_div_is_set)
                        this.swt_trade_div_is_modified = true;
                    this.swt_trade_div_is_set = true;
                    return;
                }
                else if ( name.equals("swt_group") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_group' must be String: '"+value+"' is not." );
                    this.swt_group = (String) value;
                    if (this.swt_group_is_set)
                        this.swt_group_is_modified = true;
                    this.swt_group_is_set = true;
                    return;
                }
                else if ( name.equals("swt_companion_subject_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_companion_subject_div' must be String: '"+value+"' is not." );
                    this.swt_companion_subject_div = (String) value;
                    if (this.swt_companion_subject_div_is_set)
                        this.swt_companion_subject_div_is_modified = true;
                    this.swt_companion_subject_div_is_set = true;
                    return;
                }
                else if ( name.equals("swt_perference_enable_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_perference_enable_div' must be String: '"+value+"' is not." );
                    this.swt_perference_enable_div = (String) value;
                    if (this.swt_perference_enable_div_is_set)
                        this.swt_perference_enable_div_is_modified = true;
                    this.swt_perference_enable_div_is_set = true;
                    return;
                }
                else if ( name.equals("sell_advance_order_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_advance_order_div' must be String: '"+value+"' is not." );
                    this.sell_advance_order_div = (String) value;
                    if (this.sell_advance_order_div_is_set)
                        this.sell_advance_order_div_is_modified = true;
                    this.sell_advance_order_div_is_set = true;
                    return;
                }
                else if ( name.equals("sell_undelivering_term") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_undelivering_term' must be String: '"+value+"' is not." );
                    this.sell_undelivering_term = (String) value;
                    if (this.sell_undelivering_term_is_set)
                        this.sell_undelivering_term_is_modified = true;
                    this.sell_undelivering_term_is_set = true;
                    return;
                }
                else if ( name.equals("storage_stop_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'storage_stop_div' must be String: '"+value+"' is not." );
                    this.storage_stop_div = (String) value;
                    if (this.storage_stop_div_is_set)
                        this.storage_stop_div_is_modified = true;
                    this.storage_stop_div_is_set = true;
                    return;
                }
                else if ( name.equals("specific_corpus_app") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'specific_corpus_app' must be String: '"+value+"' is not." );
                    this.specific_corpus_app = (String) value;
                    if (this.specific_corpus_app_is_set)
                        this.specific_corpus_app_is_modified = true;
                    this.specific_corpus_app_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("work_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'work_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.work_date = (java.sql.Timestamp) value;
                    if (this.work_date_is_set)
                        this.work_date_is_modified = true;
                    this.work_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
