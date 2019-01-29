head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.38.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeTradedProductParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * eqtype_traded_productテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link EqtypeTradedProductRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link EqtypeTradedProductParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link EqtypeTradedProductParams}が{@@link EqtypeTradedProductRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeTradedProductPK 
 * @@see EqtypeTradedProductRow 
 */
public class EqtypeTradedProductParams extends Params implements EqtypeTradedProductRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "eqtype_traded_product";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = EqtypeTradedProductRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return EqtypeTradedProductRow.TYPE;
  }


  /** 
   * <em>traded_product_id</em>カラムの値 
   */
  public  long  traded_product_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  long  market_id;

  /** 
   * <em>valid_until_biz_date</em>カラムの値 
   */
  public  String  valid_until_biz_date;

  /** 
   * <em>list_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  list_flag;

  /** 
   * <em>list_type</em>カラムの値 
   */
  public  String  list_type;

  /** 
   * <em>new_list_type</em>カラムの値 
   */
  public  String  new_list_type;

  /** 
   * <em>listed_date</em>カラムの値 
   */
  public  java.sql.Timestamp  listed_date;

  /** 
   * <em>unlisted_date</em>カラムの値 
   */
  public  java.sql.Timestamp  unlisted_date;

  /** 
   * <em>marginable_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  marginable_flag;

  /** 
   * <em>shortable_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  shortable_flag;

  /** 
   * <em>open_otc_div</em>カラムの値 
   */
  public  String  open_otc_div;

  /** 
   * <em>margin_sys_product_type</em>カラムの値 
   */
  public  String  margin_sys_product_type;

  /** 
   * <em>margin_gen_product_type</em>カラムの値 
   */
  public  String  margin_gen_product_type;

  /** 
   * <em>mini_stock_can_dealt</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  mini_stock_can_dealt;

  /** 
   * <em>buy_cash_stop</em>カラムの値 
   */
  public  Integer  buy_cash_stop;

  /** 
   * <em>sell_cash_stop</em>カラムの値 
   */
  public  Integer  sell_cash_stop;

  /** 
   * <em>buy_spot_market_ord_des_stop</em>カラムの値 
   */
  public  Integer  buy_spot_market_ord_des_stop;

  /** 
   * <em>sell_spot_market_ord_des_stop</em>カラムの値 
   */
  public  Integer  sell_spot_market_ord_des_stop;

  /** 
   * <em>long_margin_sys_stop</em>カラムの値 
   */
  public  Integer  long_margin_sys_stop;

  /** 
   * <em>short_margin_sys_stop</em>カラムの値 
   */
  public  Integer  short_margin_sys_stop;

  /** 
   * <em>long_ms_market_ord_des_stop</em>カラムの値 
   */
  public  Integer  long_ms_market_ord_des_stop;

  /** 
   * <em>short_ms_market_ord_des_stop</em>カラムの値 
   */
  public  Integer  short_ms_market_ord_des_stop;

  /** 
   * <em>long_margin_gen_stop</em>カラムの値 
   */
  public  Integer  long_margin_gen_stop;

  /** 
   * <em>short_margin_gen_stop</em>カラムの値 
   */
  public  Integer  short_margin_gen_stop;

  /** 
   * <em>long_mg_market_ord_des_stop</em>カラムの値 
   */
  public  Integer  long_mg_market_ord_des_stop;

  /** 
   * <em>short_mg_market_ord_des_stop</em>カラムの値 
   */
  public  Integer  short_mg_market_ord_des_stop;

  /** 
   * <em>long_close_margin_sys_stop</em>カラムの値 
   */
  public  Integer  long_close_margin_sys_stop;

  /** 
   * <em>short_close_margin_sys_stop</em>カラムの値 
   */
  public  Integer  short_close_margin_sys_stop;

  /** 
   * <em>long_cms_market_ord_des_stop</em>カラムの値 
   */
  public  Integer  long_cms_market_ord_des_stop;

  /** 
   * <em>short_cms_market_ord_des_stop</em>カラムの値 
   */
  public  Integer  short_cms_market_ord_des_stop;

  /** 
   * <em>long_close_margin_gen_stop</em>カラムの値 
   */
  public  Integer  long_close_margin_gen_stop;

  /** 
   * <em>short_close_margin_gen_stop</em>カラムの値 
   */
  public  Integer  short_close_margin_gen_stop;

  /** 
   * <em>long_cmg_market_ord_des_stop</em>カラムの値 
   */
  public  Integer  long_cmg_market_ord_des_stop;

  /** 
   * <em>short_cmg_market_ord_des_stop</em>カラムの値 
   */
  public  Integer  short_cmg_market_ord_des_stop;

  /** 
   * <em>long_swap_margin_sys_stop</em>カラムの値 
   */
  public  Integer  long_swap_margin_sys_stop;

  /** 
   * <em>short_swap_margin_sys_stop</em>カラムの値 
   */
  public  Integer  short_swap_margin_sys_stop;

  /** 
   * <em>long_swap_margin_gen_stop</em>カラムの値 
   */
  public  Integer  long_swap_margin_gen_stop;

  /** 
   * <em>short_swap_margin_gen_stop</em>カラムの値 
   */
  public  Integer  short_swap_margin_gen_stop;

  /** 
   * <em>buy_mini_stock_stop</em>カラムの値 
   */
  public  Integer  buy_mini_stock_stop;

  /** 
   * <em>sell_mini_stock_stop</em>カラムの値 
   */
  public  Integer  sell_mini_stock_stop;

  /** 
   * <em>lot_size</em>カラムの値 
   */
  public  double  lot_size;

  /** 
   * <em>long_margin_deposit_rate</em>カラムの値 
   */
  public  Double  long_margin_deposit_rate;

  /** 
   * <em>short_margin_deposit_rate</em>カラムの値 
   */
  public  Double  short_margin_deposit_rate;

  /** 
   * <em>long_cash_margin_deposit_rate</em>カラムの値 
   */
  public  Double  long_cash_margin_deposit_rate;

  /** 
   * <em>short_cash_margin_deposit_rate</em>カラムの値 
   */
  public  Double  short_cash_margin_deposit_rate;

  /** 
   * <em>last_closing_price</em>カラムの値 
   */
  public  double  last_closing_price;

  /** 
   * <em>price_range_type</em>カラムの値 
   */
  public  String  price_range_type;

  /** 
   * <em>price_range_unit_type</em>カラムの値 
   */
  public  String  price_range_unit_type;

  /** 
   * <em>high_compulsive_price_range</em>カラムの値 
   */
  public  Double  high_compulsive_price_range;

  /** 
   * <em>low_compulsive_price_range</em>カラムの値 
   */
  public  Double  low_compulsive_price_range;

  /** 
   * <em>stop_high_price</em>カラムの値 
   */
  public  Double  stop_high_price;

  /** 
   * <em>stop_low_price</em>カラムの値 
   */
  public  Double  stop_low_price;

  /** 
   * <em>price_range_ratio</em>カラムの値 
   */
  public  Double  price_range_ratio;

  /** 
   * <em>compulsive_limited_unit</em>カラムの値 
   */
  public  Integer  compulsive_limited_unit;

  /** 
   * <em>mini_stock_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  mini_stock_flag;

  /** 
   * <em>today_dep_fund_reg</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  today_dep_fund_reg;

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
   * <em>base_price</em>カラムの値 
   */
  public  double  base_price;

  private boolean traded_product_id_is_set = false;
  private boolean traded_product_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean valid_until_biz_date_is_set = false;
  private boolean valid_until_biz_date_is_modified = false;
  private boolean list_flag_is_set = false;
  private boolean list_flag_is_modified = false;
  private boolean list_type_is_set = false;
  private boolean list_type_is_modified = false;
  private boolean new_list_type_is_set = false;
  private boolean new_list_type_is_modified = false;
  private boolean listed_date_is_set = false;
  private boolean listed_date_is_modified = false;
  private boolean unlisted_date_is_set = false;
  private boolean unlisted_date_is_modified = false;
  private boolean marginable_flag_is_set = false;
  private boolean marginable_flag_is_modified = false;
  private boolean shortable_flag_is_set = false;
  private boolean shortable_flag_is_modified = false;
  private boolean open_otc_div_is_set = false;
  private boolean open_otc_div_is_modified = false;
  private boolean margin_sys_product_type_is_set = false;
  private boolean margin_sys_product_type_is_modified = false;
  private boolean margin_gen_product_type_is_set = false;
  private boolean margin_gen_product_type_is_modified = false;
  private boolean mini_stock_can_dealt_is_set = false;
  private boolean mini_stock_can_dealt_is_modified = false;
  private boolean buy_cash_stop_is_set = false;
  private boolean buy_cash_stop_is_modified = false;
  private boolean sell_cash_stop_is_set = false;
  private boolean sell_cash_stop_is_modified = false;
  private boolean buy_spot_market_ord_des_stop_is_set = false;
  private boolean buy_spot_market_ord_des_stop_is_modified = false;
  private boolean sell_spot_market_ord_des_stop_is_set = false;
  private boolean sell_spot_market_ord_des_stop_is_modified = false;
  private boolean long_margin_sys_stop_is_set = false;
  private boolean long_margin_sys_stop_is_modified = false;
  private boolean short_margin_sys_stop_is_set = false;
  private boolean short_margin_sys_stop_is_modified = false;
  private boolean long_ms_market_ord_des_stop_is_set = false;
  private boolean long_ms_market_ord_des_stop_is_modified = false;
  private boolean short_ms_market_ord_des_stop_is_set = false;
  private boolean short_ms_market_ord_des_stop_is_modified = false;
  private boolean long_margin_gen_stop_is_set = false;
  private boolean long_margin_gen_stop_is_modified = false;
  private boolean short_margin_gen_stop_is_set = false;
  private boolean short_margin_gen_stop_is_modified = false;
  private boolean long_mg_market_ord_des_stop_is_set = false;
  private boolean long_mg_market_ord_des_stop_is_modified = false;
  private boolean short_mg_market_ord_des_stop_is_set = false;
  private boolean short_mg_market_ord_des_stop_is_modified = false;
  private boolean long_close_margin_sys_stop_is_set = false;
  private boolean long_close_margin_sys_stop_is_modified = false;
  private boolean short_close_margin_sys_stop_is_set = false;
  private boolean short_close_margin_sys_stop_is_modified = false;
  private boolean long_cms_market_ord_des_stop_is_set = false;
  private boolean long_cms_market_ord_des_stop_is_modified = false;
  private boolean short_cms_market_ord_des_stop_is_set = false;
  private boolean short_cms_market_ord_des_stop_is_modified = false;
  private boolean long_close_margin_gen_stop_is_set = false;
  private boolean long_close_margin_gen_stop_is_modified = false;
  private boolean short_close_margin_gen_stop_is_set = false;
  private boolean short_close_margin_gen_stop_is_modified = false;
  private boolean long_cmg_market_ord_des_stop_is_set = false;
  private boolean long_cmg_market_ord_des_stop_is_modified = false;
  private boolean short_cmg_market_ord_des_stop_is_set = false;
  private boolean short_cmg_market_ord_des_stop_is_modified = false;
  private boolean long_swap_margin_sys_stop_is_set = false;
  private boolean long_swap_margin_sys_stop_is_modified = false;
  private boolean short_swap_margin_sys_stop_is_set = false;
  private boolean short_swap_margin_sys_stop_is_modified = false;
  private boolean long_swap_margin_gen_stop_is_set = false;
  private boolean long_swap_margin_gen_stop_is_modified = false;
  private boolean short_swap_margin_gen_stop_is_set = false;
  private boolean short_swap_margin_gen_stop_is_modified = false;
  private boolean buy_mini_stock_stop_is_set = false;
  private boolean buy_mini_stock_stop_is_modified = false;
  private boolean sell_mini_stock_stop_is_set = false;
  private boolean sell_mini_stock_stop_is_modified = false;
  private boolean lot_size_is_set = false;
  private boolean lot_size_is_modified = false;
  private boolean long_margin_deposit_rate_is_set = false;
  private boolean long_margin_deposit_rate_is_modified = false;
  private boolean short_margin_deposit_rate_is_set = false;
  private boolean short_margin_deposit_rate_is_modified = false;
  private boolean long_cash_margin_deposit_rate_is_set = false;
  private boolean long_cash_margin_deposit_rate_is_modified = false;
  private boolean short_cash_margin_deposit_rate_is_set = false;
  private boolean short_cash_margin_deposit_rate_is_modified = false;
  private boolean last_closing_price_is_set = false;
  private boolean last_closing_price_is_modified = false;
  private boolean price_range_type_is_set = false;
  private boolean price_range_type_is_modified = false;
  private boolean price_range_unit_type_is_set = false;
  private boolean price_range_unit_type_is_modified = false;
  private boolean high_compulsive_price_range_is_set = false;
  private boolean high_compulsive_price_range_is_modified = false;
  private boolean low_compulsive_price_range_is_set = false;
  private boolean low_compulsive_price_range_is_modified = false;
  private boolean stop_high_price_is_set = false;
  private boolean stop_high_price_is_modified = false;
  private boolean stop_low_price_is_set = false;
  private boolean stop_low_price_is_modified = false;
  private boolean price_range_ratio_is_set = false;
  private boolean price_range_ratio_is_modified = false;
  private boolean compulsive_limited_unit_is_set = false;
  private boolean compulsive_limited_unit_is_modified = false;
  private boolean mini_stock_flag_is_set = false;
  private boolean mini_stock_flag_is_modified = false;
  private boolean today_dep_fund_reg_is_set = false;
  private boolean today_dep_fund_reg_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean base_price_is_set = false;
  private boolean base_price_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "traded_product_id=" + traded_product_id
      + "," + "institution_code=" +institution_code
      + "," + "product_id=" +product_id
      + "," + "market_id=" +market_id
      + "," + "valid_until_biz_date=" +valid_until_biz_date
      + "," + "list_flag=" +list_flag
      + "," + "list_type=" +list_type
      + "," + "new_list_type=" +new_list_type
      + "," + "listed_date=" +listed_date
      + "," + "unlisted_date=" +unlisted_date
      + "," + "marginable_flag=" +marginable_flag
      + "," + "shortable_flag=" +shortable_flag
      + "," + "open_otc_div=" +open_otc_div
      + "," + "margin_sys_product_type=" +margin_sys_product_type
      + "," + "margin_gen_product_type=" +margin_gen_product_type
      + "," + "mini_stock_can_dealt=" +mini_stock_can_dealt
      + "," + "buy_cash_stop=" +buy_cash_stop
      + "," + "sell_cash_stop=" +sell_cash_stop
      + "," + "buy_spot_market_ord_des_stop=" +buy_spot_market_ord_des_stop
      + "," + "sell_spot_market_ord_des_stop=" +sell_spot_market_ord_des_stop
      + "," + "long_margin_sys_stop=" +long_margin_sys_stop
      + "," + "short_margin_sys_stop=" +short_margin_sys_stop
      + "," + "long_ms_market_ord_des_stop=" +long_ms_market_ord_des_stop
      + "," + "short_ms_market_ord_des_stop=" +short_ms_market_ord_des_stop
      + "," + "long_margin_gen_stop=" +long_margin_gen_stop
      + "," + "short_margin_gen_stop=" +short_margin_gen_stop
      + "," + "long_mg_market_ord_des_stop=" +long_mg_market_ord_des_stop
      + "," + "short_mg_market_ord_des_stop=" +short_mg_market_ord_des_stop
      + "," + "long_close_margin_sys_stop=" +long_close_margin_sys_stop
      + "," + "short_close_margin_sys_stop=" +short_close_margin_sys_stop
      + "," + "long_cms_market_ord_des_stop=" +long_cms_market_ord_des_stop
      + "," + "short_cms_market_ord_des_stop=" +short_cms_market_ord_des_stop
      + "," + "long_close_margin_gen_stop=" +long_close_margin_gen_stop
      + "," + "short_close_margin_gen_stop=" +short_close_margin_gen_stop
      + "," + "long_cmg_market_ord_des_stop=" +long_cmg_market_ord_des_stop
      + "," + "short_cmg_market_ord_des_stop=" +short_cmg_market_ord_des_stop
      + "," + "long_swap_margin_sys_stop=" +long_swap_margin_sys_stop
      + "," + "short_swap_margin_sys_stop=" +short_swap_margin_sys_stop
      + "," + "long_swap_margin_gen_stop=" +long_swap_margin_gen_stop
      + "," + "short_swap_margin_gen_stop=" +short_swap_margin_gen_stop
      + "," + "buy_mini_stock_stop=" +buy_mini_stock_stop
      + "," + "sell_mini_stock_stop=" +sell_mini_stock_stop
      + "," + "lot_size=" +lot_size
      + "," + "long_margin_deposit_rate=" +long_margin_deposit_rate
      + "," + "short_margin_deposit_rate=" +short_margin_deposit_rate
      + "," + "long_cash_margin_deposit_rate=" +long_cash_margin_deposit_rate
      + "," + "short_cash_margin_deposit_rate=" +short_cash_margin_deposit_rate
      + "," + "last_closing_price=" +last_closing_price
      + "," + "price_range_type=" +price_range_type
      + "," + "price_range_unit_type=" +price_range_unit_type
      + "," + "high_compulsive_price_range=" +high_compulsive_price_range
      + "," + "low_compulsive_price_range=" +low_compulsive_price_range
      + "," + "stop_high_price=" +stop_high_price
      + "," + "stop_low_price=" +stop_low_price
      + "," + "price_range_ratio=" +price_range_ratio
      + "," + "compulsive_limited_unit=" +compulsive_limited_unit
      + "," + "mini_stock_flag=" +mini_stock_flag
      + "," + "today_dep_fund_reg=" +today_dep_fund_reg
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "base_price=" +base_price
      + "}";
  }


  /** 
   * 値が未設定のEqtypeTradedProductParamsオブジェクトを作成します。 
   */
  public EqtypeTradedProductParams() {
    traded_product_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のEqtypeTradedProductRowオブジェクトの値を利用してEqtypeTradedProductParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するEqtypeTradedProductRowオブジェクト 
   */
  public EqtypeTradedProductParams( EqtypeTradedProductRow p_row )
  {
    traded_product_id = p_row.getTradedProductId();
    traded_product_id_is_set = p_row.getTradedProductIdIsSet();
    traded_product_id_is_modified = p_row.getTradedProductIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    valid_until_biz_date = p_row.getValidUntilBizDate();
    valid_until_biz_date_is_set = p_row.getValidUntilBizDateIsSet();
    valid_until_biz_date_is_modified = p_row.getValidUntilBizDateIsModified();
    list_flag = p_row.getListFlag();
    list_flag_is_set = p_row.getListFlagIsSet();
    list_flag_is_modified = p_row.getListFlagIsModified();
    list_type = p_row.getListType();
    list_type_is_set = p_row.getListTypeIsSet();
    list_type_is_modified = p_row.getListTypeIsModified();
    new_list_type = p_row.getNewListType();
    new_list_type_is_set = p_row.getNewListTypeIsSet();
    new_list_type_is_modified = p_row.getNewListTypeIsModified();
    listed_date = p_row.getListedDate();
    listed_date_is_set = p_row.getListedDateIsSet();
    listed_date_is_modified = p_row.getListedDateIsModified();
    unlisted_date = p_row.getUnlistedDate();
    unlisted_date_is_set = p_row.getUnlistedDateIsSet();
    unlisted_date_is_modified = p_row.getUnlistedDateIsModified();
    marginable_flag = p_row.getMarginableFlag();
    marginable_flag_is_set = p_row.getMarginableFlagIsSet();
    marginable_flag_is_modified = p_row.getMarginableFlagIsModified();
    shortable_flag = p_row.getShortableFlag();
    shortable_flag_is_set = p_row.getShortableFlagIsSet();
    shortable_flag_is_modified = p_row.getShortableFlagIsModified();
    open_otc_div = p_row.getOpenOtcDiv();
    open_otc_div_is_set = p_row.getOpenOtcDivIsSet();
    open_otc_div_is_modified = p_row.getOpenOtcDivIsModified();
    margin_sys_product_type = p_row.getMarginSysProductType();
    margin_sys_product_type_is_set = p_row.getMarginSysProductTypeIsSet();
    margin_sys_product_type_is_modified = p_row.getMarginSysProductTypeIsModified();
    margin_gen_product_type = p_row.getMarginGenProductType();
    margin_gen_product_type_is_set = p_row.getMarginGenProductTypeIsSet();
    margin_gen_product_type_is_modified = p_row.getMarginGenProductTypeIsModified();
    mini_stock_can_dealt = p_row.getMiniStockCanDealt();
    mini_stock_can_dealt_is_set = p_row.getMiniStockCanDealtIsSet();
    mini_stock_can_dealt_is_modified = p_row.getMiniStockCanDealtIsModified();
    if ( !p_row.getBuyCashStopIsNull() )
      buy_cash_stop = new Integer( p_row.getBuyCashStop() );
    buy_cash_stop_is_set = p_row.getBuyCashStopIsSet();
    buy_cash_stop_is_modified = p_row.getBuyCashStopIsModified();
    if ( !p_row.getSellCashStopIsNull() )
      sell_cash_stop = new Integer( p_row.getSellCashStop() );
    sell_cash_stop_is_set = p_row.getSellCashStopIsSet();
    sell_cash_stop_is_modified = p_row.getSellCashStopIsModified();
    if ( !p_row.getBuySpotMarketOrdDesStopIsNull() )
      buy_spot_market_ord_des_stop = new Integer( p_row.getBuySpotMarketOrdDesStop() );
    buy_spot_market_ord_des_stop_is_set = p_row.getBuySpotMarketOrdDesStopIsSet();
    buy_spot_market_ord_des_stop_is_modified = p_row.getBuySpotMarketOrdDesStopIsModified();
    if ( !p_row.getSellSpotMarketOrdDesStopIsNull() )
      sell_spot_market_ord_des_stop = new Integer( p_row.getSellSpotMarketOrdDesStop() );
    sell_spot_market_ord_des_stop_is_set = p_row.getSellSpotMarketOrdDesStopIsSet();
    sell_spot_market_ord_des_stop_is_modified = p_row.getSellSpotMarketOrdDesStopIsModified();
    if ( !p_row.getLongMarginSysStopIsNull() )
      long_margin_sys_stop = new Integer( p_row.getLongMarginSysStop() );
    long_margin_sys_stop_is_set = p_row.getLongMarginSysStopIsSet();
    long_margin_sys_stop_is_modified = p_row.getLongMarginSysStopIsModified();
    if ( !p_row.getShortMarginSysStopIsNull() )
      short_margin_sys_stop = new Integer( p_row.getShortMarginSysStop() );
    short_margin_sys_stop_is_set = p_row.getShortMarginSysStopIsSet();
    short_margin_sys_stop_is_modified = p_row.getShortMarginSysStopIsModified();
    if ( !p_row.getLongMsMarketOrdDesStopIsNull() )
      long_ms_market_ord_des_stop = new Integer( p_row.getLongMsMarketOrdDesStop() );
    long_ms_market_ord_des_stop_is_set = p_row.getLongMsMarketOrdDesStopIsSet();
    long_ms_market_ord_des_stop_is_modified = p_row.getLongMsMarketOrdDesStopIsModified();
    if ( !p_row.getShortMsMarketOrdDesStopIsNull() )
      short_ms_market_ord_des_stop = new Integer( p_row.getShortMsMarketOrdDesStop() );
    short_ms_market_ord_des_stop_is_set = p_row.getShortMsMarketOrdDesStopIsSet();
    short_ms_market_ord_des_stop_is_modified = p_row.getShortMsMarketOrdDesStopIsModified();
    if ( !p_row.getLongMarginGenStopIsNull() )
      long_margin_gen_stop = new Integer( p_row.getLongMarginGenStop() );
    long_margin_gen_stop_is_set = p_row.getLongMarginGenStopIsSet();
    long_margin_gen_stop_is_modified = p_row.getLongMarginGenStopIsModified();
    if ( !p_row.getShortMarginGenStopIsNull() )
      short_margin_gen_stop = new Integer( p_row.getShortMarginGenStop() );
    short_margin_gen_stop_is_set = p_row.getShortMarginGenStopIsSet();
    short_margin_gen_stop_is_modified = p_row.getShortMarginGenStopIsModified();
    if ( !p_row.getLongMgMarketOrdDesStopIsNull() )
      long_mg_market_ord_des_stop = new Integer( p_row.getLongMgMarketOrdDesStop() );
    long_mg_market_ord_des_stop_is_set = p_row.getLongMgMarketOrdDesStopIsSet();
    long_mg_market_ord_des_stop_is_modified = p_row.getLongMgMarketOrdDesStopIsModified();
    if ( !p_row.getShortMgMarketOrdDesStopIsNull() )
      short_mg_market_ord_des_stop = new Integer( p_row.getShortMgMarketOrdDesStop() );
    short_mg_market_ord_des_stop_is_set = p_row.getShortMgMarketOrdDesStopIsSet();
    short_mg_market_ord_des_stop_is_modified = p_row.getShortMgMarketOrdDesStopIsModified();
    if ( !p_row.getLongCloseMarginSysStopIsNull() )
      long_close_margin_sys_stop = new Integer( p_row.getLongCloseMarginSysStop() );
    long_close_margin_sys_stop_is_set = p_row.getLongCloseMarginSysStopIsSet();
    long_close_margin_sys_stop_is_modified = p_row.getLongCloseMarginSysStopIsModified();
    if ( !p_row.getShortCloseMarginSysStopIsNull() )
      short_close_margin_sys_stop = new Integer( p_row.getShortCloseMarginSysStop() );
    short_close_margin_sys_stop_is_set = p_row.getShortCloseMarginSysStopIsSet();
    short_close_margin_sys_stop_is_modified = p_row.getShortCloseMarginSysStopIsModified();
    if ( !p_row.getLongCmsMarketOrdDesStopIsNull() )
      long_cms_market_ord_des_stop = new Integer( p_row.getLongCmsMarketOrdDesStop() );
    long_cms_market_ord_des_stop_is_set = p_row.getLongCmsMarketOrdDesStopIsSet();
    long_cms_market_ord_des_stop_is_modified = p_row.getLongCmsMarketOrdDesStopIsModified();
    if ( !p_row.getShortCmsMarketOrdDesStopIsNull() )
      short_cms_market_ord_des_stop = new Integer( p_row.getShortCmsMarketOrdDesStop() );
    short_cms_market_ord_des_stop_is_set = p_row.getShortCmsMarketOrdDesStopIsSet();
    short_cms_market_ord_des_stop_is_modified = p_row.getShortCmsMarketOrdDesStopIsModified();
    if ( !p_row.getLongCloseMarginGenStopIsNull() )
      long_close_margin_gen_stop = new Integer( p_row.getLongCloseMarginGenStop() );
    long_close_margin_gen_stop_is_set = p_row.getLongCloseMarginGenStopIsSet();
    long_close_margin_gen_stop_is_modified = p_row.getLongCloseMarginGenStopIsModified();
    if ( !p_row.getShortCloseMarginGenStopIsNull() )
      short_close_margin_gen_stop = new Integer( p_row.getShortCloseMarginGenStop() );
    short_close_margin_gen_stop_is_set = p_row.getShortCloseMarginGenStopIsSet();
    short_close_margin_gen_stop_is_modified = p_row.getShortCloseMarginGenStopIsModified();
    if ( !p_row.getLongCmgMarketOrdDesStopIsNull() )
      long_cmg_market_ord_des_stop = new Integer( p_row.getLongCmgMarketOrdDesStop() );
    long_cmg_market_ord_des_stop_is_set = p_row.getLongCmgMarketOrdDesStopIsSet();
    long_cmg_market_ord_des_stop_is_modified = p_row.getLongCmgMarketOrdDesStopIsModified();
    if ( !p_row.getShortCmgMarketOrdDesStopIsNull() )
      short_cmg_market_ord_des_stop = new Integer( p_row.getShortCmgMarketOrdDesStop() );
    short_cmg_market_ord_des_stop_is_set = p_row.getShortCmgMarketOrdDesStopIsSet();
    short_cmg_market_ord_des_stop_is_modified = p_row.getShortCmgMarketOrdDesStopIsModified();
    if ( !p_row.getLongSwapMarginSysStopIsNull() )
      long_swap_margin_sys_stop = new Integer( p_row.getLongSwapMarginSysStop() );
    long_swap_margin_sys_stop_is_set = p_row.getLongSwapMarginSysStopIsSet();
    long_swap_margin_sys_stop_is_modified = p_row.getLongSwapMarginSysStopIsModified();
    if ( !p_row.getShortSwapMarginSysStopIsNull() )
      short_swap_margin_sys_stop = new Integer( p_row.getShortSwapMarginSysStop() );
    short_swap_margin_sys_stop_is_set = p_row.getShortSwapMarginSysStopIsSet();
    short_swap_margin_sys_stop_is_modified = p_row.getShortSwapMarginSysStopIsModified();
    if ( !p_row.getLongSwapMarginGenStopIsNull() )
      long_swap_margin_gen_stop = new Integer( p_row.getLongSwapMarginGenStop() );
    long_swap_margin_gen_stop_is_set = p_row.getLongSwapMarginGenStopIsSet();
    long_swap_margin_gen_stop_is_modified = p_row.getLongSwapMarginGenStopIsModified();
    if ( !p_row.getShortSwapMarginGenStopIsNull() )
      short_swap_margin_gen_stop = new Integer( p_row.getShortSwapMarginGenStop() );
    short_swap_margin_gen_stop_is_set = p_row.getShortSwapMarginGenStopIsSet();
    short_swap_margin_gen_stop_is_modified = p_row.getShortSwapMarginGenStopIsModified();
    if ( !p_row.getBuyMiniStockStopIsNull() )
      buy_mini_stock_stop = new Integer( p_row.getBuyMiniStockStop() );
    buy_mini_stock_stop_is_set = p_row.getBuyMiniStockStopIsSet();
    buy_mini_stock_stop_is_modified = p_row.getBuyMiniStockStopIsModified();
    if ( !p_row.getSellMiniStockStopIsNull() )
      sell_mini_stock_stop = new Integer( p_row.getSellMiniStockStop() );
    sell_mini_stock_stop_is_set = p_row.getSellMiniStockStopIsSet();
    sell_mini_stock_stop_is_modified = p_row.getSellMiniStockStopIsModified();
    lot_size = p_row.getLotSize();
    lot_size_is_set = p_row.getLotSizeIsSet();
    lot_size_is_modified = p_row.getLotSizeIsModified();
    if ( !p_row.getLongMarginDepositRateIsNull() )
      long_margin_deposit_rate = new Double( p_row.getLongMarginDepositRate() );
    long_margin_deposit_rate_is_set = p_row.getLongMarginDepositRateIsSet();
    long_margin_deposit_rate_is_modified = p_row.getLongMarginDepositRateIsModified();
    if ( !p_row.getShortMarginDepositRateIsNull() )
      short_margin_deposit_rate = new Double( p_row.getShortMarginDepositRate() );
    short_margin_deposit_rate_is_set = p_row.getShortMarginDepositRateIsSet();
    short_margin_deposit_rate_is_modified = p_row.getShortMarginDepositRateIsModified();
    if ( !p_row.getLongCashMarginDepositRateIsNull() )
      long_cash_margin_deposit_rate = new Double( p_row.getLongCashMarginDepositRate() );
    long_cash_margin_deposit_rate_is_set = p_row.getLongCashMarginDepositRateIsSet();
    long_cash_margin_deposit_rate_is_modified = p_row.getLongCashMarginDepositRateIsModified();
    if ( !p_row.getShortCashMarginDepositRateIsNull() )
      short_cash_margin_deposit_rate = new Double( p_row.getShortCashMarginDepositRate() );
    short_cash_margin_deposit_rate_is_set = p_row.getShortCashMarginDepositRateIsSet();
    short_cash_margin_deposit_rate_is_modified = p_row.getShortCashMarginDepositRateIsModified();
    last_closing_price = p_row.getLastClosingPrice();
    last_closing_price_is_set = p_row.getLastClosingPriceIsSet();
    last_closing_price_is_modified = p_row.getLastClosingPriceIsModified();
    price_range_type = p_row.getPriceRangeType();
    price_range_type_is_set = p_row.getPriceRangeTypeIsSet();
    price_range_type_is_modified = p_row.getPriceRangeTypeIsModified();
    price_range_unit_type = p_row.getPriceRangeUnitType();
    price_range_unit_type_is_set = p_row.getPriceRangeUnitTypeIsSet();
    price_range_unit_type_is_modified = p_row.getPriceRangeUnitTypeIsModified();
    if ( !p_row.getHighCompulsivePriceRangeIsNull() )
      high_compulsive_price_range = new Double( p_row.getHighCompulsivePriceRange() );
    high_compulsive_price_range_is_set = p_row.getHighCompulsivePriceRangeIsSet();
    high_compulsive_price_range_is_modified = p_row.getHighCompulsivePriceRangeIsModified();
    if ( !p_row.getLowCompulsivePriceRangeIsNull() )
      low_compulsive_price_range = new Double( p_row.getLowCompulsivePriceRange() );
    low_compulsive_price_range_is_set = p_row.getLowCompulsivePriceRangeIsSet();
    low_compulsive_price_range_is_modified = p_row.getLowCompulsivePriceRangeIsModified();
    if ( !p_row.getStopHighPriceIsNull() )
      stop_high_price = new Double( p_row.getStopHighPrice() );
    stop_high_price_is_set = p_row.getStopHighPriceIsSet();
    stop_high_price_is_modified = p_row.getStopHighPriceIsModified();
    if ( !p_row.getStopLowPriceIsNull() )
      stop_low_price = new Double( p_row.getStopLowPrice() );
    stop_low_price_is_set = p_row.getStopLowPriceIsSet();
    stop_low_price_is_modified = p_row.getStopLowPriceIsModified();
    if ( !p_row.getPriceRangeRatioIsNull() )
      price_range_ratio = new Double( p_row.getPriceRangeRatio() );
    price_range_ratio_is_set = p_row.getPriceRangeRatioIsSet();
    price_range_ratio_is_modified = p_row.getPriceRangeRatioIsModified();
    if ( !p_row.getCompulsiveLimitedUnitIsNull() )
      compulsive_limited_unit = new Integer( p_row.getCompulsiveLimitedUnit() );
    compulsive_limited_unit_is_set = p_row.getCompulsiveLimitedUnitIsSet();
    compulsive_limited_unit_is_modified = p_row.getCompulsiveLimitedUnitIsModified();
    mini_stock_flag = p_row.getMiniStockFlag();
    mini_stock_flag_is_set = p_row.getMiniStockFlagIsSet();
    mini_stock_flag_is_modified = p_row.getMiniStockFlagIsModified();
    today_dep_fund_reg = p_row.getTodayDepFundReg();
    today_dep_fund_reg_is_set = p_row.getTodayDepFundRegIsSet();
    today_dep_fund_reg_is_modified = p_row.getTodayDepFundRegIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    base_price = p_row.getBasePrice();
    base_price_is_set = p_row.getBasePriceIsSet();
    base_price_is_modified = p_row.getBasePriceIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      valid_until_biz_date_is_set = true;
      valid_until_biz_date_is_modified = true;
      list_flag_is_set = true;
      list_flag_is_modified = true;
      list_type_is_set = true;
      list_type_is_modified = true;
      new_list_type_is_set = true;
      new_list_type_is_modified = true;
      listed_date_is_set = true;
      listed_date_is_modified = true;
      unlisted_date_is_set = true;
      unlisted_date_is_modified = true;
      marginable_flag_is_set = true;
      marginable_flag_is_modified = true;
      shortable_flag_is_set = true;
      shortable_flag_is_modified = true;
      open_otc_div_is_set = true;
      open_otc_div_is_modified = true;
      margin_sys_product_type_is_set = true;
      margin_sys_product_type_is_modified = true;
      margin_gen_product_type_is_set = true;
      margin_gen_product_type_is_modified = true;
      mini_stock_can_dealt_is_set = true;
      mini_stock_can_dealt_is_modified = true;
      buy_cash_stop_is_set = true;
      buy_cash_stop_is_modified = true;
      sell_cash_stop_is_set = true;
      sell_cash_stop_is_modified = true;
      buy_spot_market_ord_des_stop_is_set = true;
      buy_spot_market_ord_des_stop_is_modified = true;
      sell_spot_market_ord_des_stop_is_set = true;
      sell_spot_market_ord_des_stop_is_modified = true;
      long_margin_sys_stop_is_set = true;
      long_margin_sys_stop_is_modified = true;
      short_margin_sys_stop_is_set = true;
      short_margin_sys_stop_is_modified = true;
      long_ms_market_ord_des_stop_is_set = true;
      long_ms_market_ord_des_stop_is_modified = true;
      short_ms_market_ord_des_stop_is_set = true;
      short_ms_market_ord_des_stop_is_modified = true;
      long_margin_gen_stop_is_set = true;
      long_margin_gen_stop_is_modified = true;
      short_margin_gen_stop_is_set = true;
      short_margin_gen_stop_is_modified = true;
      long_mg_market_ord_des_stop_is_set = true;
      long_mg_market_ord_des_stop_is_modified = true;
      short_mg_market_ord_des_stop_is_set = true;
      short_mg_market_ord_des_stop_is_modified = true;
      long_close_margin_sys_stop_is_set = true;
      long_close_margin_sys_stop_is_modified = true;
      short_close_margin_sys_stop_is_set = true;
      short_close_margin_sys_stop_is_modified = true;
      long_cms_market_ord_des_stop_is_set = true;
      long_cms_market_ord_des_stop_is_modified = true;
      short_cms_market_ord_des_stop_is_set = true;
      short_cms_market_ord_des_stop_is_modified = true;
      long_close_margin_gen_stop_is_set = true;
      long_close_margin_gen_stop_is_modified = true;
      short_close_margin_gen_stop_is_set = true;
      short_close_margin_gen_stop_is_modified = true;
      long_cmg_market_ord_des_stop_is_set = true;
      long_cmg_market_ord_des_stop_is_modified = true;
      short_cmg_market_ord_des_stop_is_set = true;
      short_cmg_market_ord_des_stop_is_modified = true;
      long_swap_margin_sys_stop_is_set = true;
      long_swap_margin_sys_stop_is_modified = true;
      short_swap_margin_sys_stop_is_set = true;
      short_swap_margin_sys_stop_is_modified = true;
      long_swap_margin_gen_stop_is_set = true;
      long_swap_margin_gen_stop_is_modified = true;
      short_swap_margin_gen_stop_is_set = true;
      short_swap_margin_gen_stop_is_modified = true;
      buy_mini_stock_stop_is_set = true;
      buy_mini_stock_stop_is_modified = true;
      sell_mini_stock_stop_is_set = true;
      sell_mini_stock_stop_is_modified = true;
      lot_size_is_set = true;
      lot_size_is_modified = true;
      long_margin_deposit_rate_is_set = true;
      long_margin_deposit_rate_is_modified = true;
      short_margin_deposit_rate_is_set = true;
      short_margin_deposit_rate_is_modified = true;
      long_cash_margin_deposit_rate_is_set = true;
      long_cash_margin_deposit_rate_is_modified = true;
      short_cash_margin_deposit_rate_is_set = true;
      short_cash_margin_deposit_rate_is_modified = true;
      last_closing_price_is_set = true;
      last_closing_price_is_modified = true;
      price_range_type_is_set = true;
      price_range_type_is_modified = true;
      price_range_unit_type_is_set = true;
      price_range_unit_type_is_modified = true;
      high_compulsive_price_range_is_set = true;
      high_compulsive_price_range_is_modified = true;
      low_compulsive_price_range_is_set = true;
      low_compulsive_price_range_is_modified = true;
      stop_high_price_is_set = true;
      stop_high_price_is_modified = true;
      stop_low_price_is_set = true;
      stop_low_price_is_modified = true;
      price_range_ratio_is_set = true;
      price_range_ratio_is_modified = true;
      compulsive_limited_unit_is_set = true;
      compulsive_limited_unit_is_modified = true;
      mini_stock_flag_is_set = true;
      mini_stock_flag_is_modified = true;
      today_dep_fund_reg_is_set = true;
      today_dep_fund_reg_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      base_price_is_set = true;
      base_price_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof EqtypeTradedProductRow ) )
       return false;
    return fieldsEqual( (EqtypeTradedProductRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のEqtypeTradedProductRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( EqtypeTradedProductRow row )
  {
    if ( traded_product_id != row.getTradedProductId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_id != row.getProductId() )
      return false;
    if ( market_id != row.getMarketId() )
      return false;
    if ( valid_until_biz_date == null ) {
      if ( row.getValidUntilBizDate() != null )
        return false;
    } else if ( !valid_until_biz_date.equals( row.getValidUntilBizDate() ) ) {
        return false;
    }
    if ( list_flag == null ) {
      if ( row.getListFlag() != null )
        return false;
    } else if ( !list_flag.equals( row.getListFlag() ) ) {
        return false;
    }
    if ( list_type == null ) {
      if ( row.getListType() != null )
        return false;
    } else if ( !list_type.equals( row.getListType() ) ) {
        return false;
    }
    if ( new_list_type == null ) {
      if ( row.getNewListType() != null )
        return false;
    } else if ( !new_list_type.equals( row.getNewListType() ) ) {
        return false;
    }
    if ( listed_date == null ) {
      if ( row.getListedDate() != null )
        return false;
    } else if ( !listed_date.equals( row.getListedDate() ) ) {
        return false;
    }
    if ( unlisted_date == null ) {
      if ( row.getUnlistedDate() != null )
        return false;
    } else if ( !unlisted_date.equals( row.getUnlistedDate() ) ) {
        return false;
    }
    if ( marginable_flag == null ) {
      if ( row.getMarginableFlag() != null )
        return false;
    } else if ( !marginable_flag.equals( row.getMarginableFlag() ) ) {
        return false;
    }
    if ( shortable_flag == null ) {
      if ( row.getShortableFlag() != null )
        return false;
    } else if ( !shortable_flag.equals( row.getShortableFlag() ) ) {
        return false;
    }
    if ( open_otc_div == null ) {
      if ( row.getOpenOtcDiv() != null )
        return false;
    } else if ( !open_otc_div.equals( row.getOpenOtcDiv() ) ) {
        return false;
    }
    if ( margin_sys_product_type == null ) {
      if ( row.getMarginSysProductType() != null )
        return false;
    } else if ( !margin_sys_product_type.equals( row.getMarginSysProductType() ) ) {
        return false;
    }
    if ( margin_gen_product_type == null ) {
      if ( row.getMarginGenProductType() != null )
        return false;
    } else if ( !margin_gen_product_type.equals( row.getMarginGenProductType() ) ) {
        return false;
    }
    if ( mini_stock_can_dealt == null ) {
      if ( row.getMiniStockCanDealt() != null )
        return false;
    } else if ( !mini_stock_can_dealt.equals( row.getMiniStockCanDealt() ) ) {
        return false;
    }
    if ( buy_cash_stop == null ) {
      if ( !row.getBuyCashStopIsNull() )
        return false;
    } else if ( row.getBuyCashStopIsNull() || ( buy_cash_stop.intValue() != row.getBuyCashStop() ) ) {
        return false;
    }
    if ( sell_cash_stop == null ) {
      if ( !row.getSellCashStopIsNull() )
        return false;
    } else if ( row.getSellCashStopIsNull() || ( sell_cash_stop.intValue() != row.getSellCashStop() ) ) {
        return false;
    }
    if ( buy_spot_market_ord_des_stop == null ) {
      if ( !row.getBuySpotMarketOrdDesStopIsNull() )
        return false;
    } else if ( row.getBuySpotMarketOrdDesStopIsNull() || ( buy_spot_market_ord_des_stop.intValue() != row.getBuySpotMarketOrdDesStop() ) ) {
        return false;
    }
    if ( sell_spot_market_ord_des_stop == null ) {
      if ( !row.getSellSpotMarketOrdDesStopIsNull() )
        return false;
    } else if ( row.getSellSpotMarketOrdDesStopIsNull() || ( sell_spot_market_ord_des_stop.intValue() != row.getSellSpotMarketOrdDesStop() ) ) {
        return false;
    }
    if ( long_margin_sys_stop == null ) {
      if ( !row.getLongMarginSysStopIsNull() )
        return false;
    } else if ( row.getLongMarginSysStopIsNull() || ( long_margin_sys_stop.intValue() != row.getLongMarginSysStop() ) ) {
        return false;
    }
    if ( short_margin_sys_stop == null ) {
      if ( !row.getShortMarginSysStopIsNull() )
        return false;
    } else if ( row.getShortMarginSysStopIsNull() || ( short_margin_sys_stop.intValue() != row.getShortMarginSysStop() ) ) {
        return false;
    }
    if ( long_ms_market_ord_des_stop == null ) {
      if ( !row.getLongMsMarketOrdDesStopIsNull() )
        return false;
    } else if ( row.getLongMsMarketOrdDesStopIsNull() || ( long_ms_market_ord_des_stop.intValue() != row.getLongMsMarketOrdDesStop() ) ) {
        return false;
    }
    if ( short_ms_market_ord_des_stop == null ) {
      if ( !row.getShortMsMarketOrdDesStopIsNull() )
        return false;
    } else if ( row.getShortMsMarketOrdDesStopIsNull() || ( short_ms_market_ord_des_stop.intValue() != row.getShortMsMarketOrdDesStop() ) ) {
        return false;
    }
    if ( long_margin_gen_stop == null ) {
      if ( !row.getLongMarginGenStopIsNull() )
        return false;
    } else if ( row.getLongMarginGenStopIsNull() || ( long_margin_gen_stop.intValue() != row.getLongMarginGenStop() ) ) {
        return false;
    }
    if ( short_margin_gen_stop == null ) {
      if ( !row.getShortMarginGenStopIsNull() )
        return false;
    } else if ( row.getShortMarginGenStopIsNull() || ( short_margin_gen_stop.intValue() != row.getShortMarginGenStop() ) ) {
        return false;
    }
    if ( long_mg_market_ord_des_stop == null ) {
      if ( !row.getLongMgMarketOrdDesStopIsNull() )
        return false;
    } else if ( row.getLongMgMarketOrdDesStopIsNull() || ( long_mg_market_ord_des_stop.intValue() != row.getLongMgMarketOrdDesStop() ) ) {
        return false;
    }
    if ( short_mg_market_ord_des_stop == null ) {
      if ( !row.getShortMgMarketOrdDesStopIsNull() )
        return false;
    } else if ( row.getShortMgMarketOrdDesStopIsNull() || ( short_mg_market_ord_des_stop.intValue() != row.getShortMgMarketOrdDesStop() ) ) {
        return false;
    }
    if ( long_close_margin_sys_stop == null ) {
      if ( !row.getLongCloseMarginSysStopIsNull() )
        return false;
    } else if ( row.getLongCloseMarginSysStopIsNull() || ( long_close_margin_sys_stop.intValue() != row.getLongCloseMarginSysStop() ) ) {
        return false;
    }
    if ( short_close_margin_sys_stop == null ) {
      if ( !row.getShortCloseMarginSysStopIsNull() )
        return false;
    } else if ( row.getShortCloseMarginSysStopIsNull() || ( short_close_margin_sys_stop.intValue() != row.getShortCloseMarginSysStop() ) ) {
        return false;
    }
    if ( long_cms_market_ord_des_stop == null ) {
      if ( !row.getLongCmsMarketOrdDesStopIsNull() )
        return false;
    } else if ( row.getLongCmsMarketOrdDesStopIsNull() || ( long_cms_market_ord_des_stop.intValue() != row.getLongCmsMarketOrdDesStop() ) ) {
        return false;
    }
    if ( short_cms_market_ord_des_stop == null ) {
      if ( !row.getShortCmsMarketOrdDesStopIsNull() )
        return false;
    } else if ( row.getShortCmsMarketOrdDesStopIsNull() || ( short_cms_market_ord_des_stop.intValue() != row.getShortCmsMarketOrdDesStop() ) ) {
        return false;
    }
    if ( long_close_margin_gen_stop == null ) {
      if ( !row.getLongCloseMarginGenStopIsNull() )
        return false;
    } else if ( row.getLongCloseMarginGenStopIsNull() || ( long_close_margin_gen_stop.intValue() != row.getLongCloseMarginGenStop() ) ) {
        return false;
    }
    if ( short_close_margin_gen_stop == null ) {
      if ( !row.getShortCloseMarginGenStopIsNull() )
        return false;
    } else if ( row.getShortCloseMarginGenStopIsNull() || ( short_close_margin_gen_stop.intValue() != row.getShortCloseMarginGenStop() ) ) {
        return false;
    }
    if ( long_cmg_market_ord_des_stop == null ) {
      if ( !row.getLongCmgMarketOrdDesStopIsNull() )
        return false;
    } else if ( row.getLongCmgMarketOrdDesStopIsNull() || ( long_cmg_market_ord_des_stop.intValue() != row.getLongCmgMarketOrdDesStop() ) ) {
        return false;
    }
    if ( short_cmg_market_ord_des_stop == null ) {
      if ( !row.getShortCmgMarketOrdDesStopIsNull() )
        return false;
    } else if ( row.getShortCmgMarketOrdDesStopIsNull() || ( short_cmg_market_ord_des_stop.intValue() != row.getShortCmgMarketOrdDesStop() ) ) {
        return false;
    }
    if ( long_swap_margin_sys_stop == null ) {
      if ( !row.getLongSwapMarginSysStopIsNull() )
        return false;
    } else if ( row.getLongSwapMarginSysStopIsNull() || ( long_swap_margin_sys_stop.intValue() != row.getLongSwapMarginSysStop() ) ) {
        return false;
    }
    if ( short_swap_margin_sys_stop == null ) {
      if ( !row.getShortSwapMarginSysStopIsNull() )
        return false;
    } else if ( row.getShortSwapMarginSysStopIsNull() || ( short_swap_margin_sys_stop.intValue() != row.getShortSwapMarginSysStop() ) ) {
        return false;
    }
    if ( long_swap_margin_gen_stop == null ) {
      if ( !row.getLongSwapMarginGenStopIsNull() )
        return false;
    } else if ( row.getLongSwapMarginGenStopIsNull() || ( long_swap_margin_gen_stop.intValue() != row.getLongSwapMarginGenStop() ) ) {
        return false;
    }
    if ( short_swap_margin_gen_stop == null ) {
      if ( !row.getShortSwapMarginGenStopIsNull() )
        return false;
    } else if ( row.getShortSwapMarginGenStopIsNull() || ( short_swap_margin_gen_stop.intValue() != row.getShortSwapMarginGenStop() ) ) {
        return false;
    }
    if ( buy_mini_stock_stop == null ) {
      if ( !row.getBuyMiniStockStopIsNull() )
        return false;
    } else if ( row.getBuyMiniStockStopIsNull() || ( buy_mini_stock_stop.intValue() != row.getBuyMiniStockStop() ) ) {
        return false;
    }
    if ( sell_mini_stock_stop == null ) {
      if ( !row.getSellMiniStockStopIsNull() )
        return false;
    } else if ( row.getSellMiniStockStopIsNull() || ( sell_mini_stock_stop.intValue() != row.getSellMiniStockStop() ) ) {
        return false;
    }
    if ( lot_size != row.getLotSize() )
      return false;
    if ( long_margin_deposit_rate == null ) {
      if ( !row.getLongMarginDepositRateIsNull() )
        return false;
    } else if ( row.getLongMarginDepositRateIsNull() || ( long_margin_deposit_rate.doubleValue() != row.getLongMarginDepositRate() ) ) {
        return false;
    }
    if ( short_margin_deposit_rate == null ) {
      if ( !row.getShortMarginDepositRateIsNull() )
        return false;
    } else if ( row.getShortMarginDepositRateIsNull() || ( short_margin_deposit_rate.doubleValue() != row.getShortMarginDepositRate() ) ) {
        return false;
    }
    if ( long_cash_margin_deposit_rate == null ) {
      if ( !row.getLongCashMarginDepositRateIsNull() )
        return false;
    } else if ( row.getLongCashMarginDepositRateIsNull() || ( long_cash_margin_deposit_rate.doubleValue() != row.getLongCashMarginDepositRate() ) ) {
        return false;
    }
    if ( short_cash_margin_deposit_rate == null ) {
      if ( !row.getShortCashMarginDepositRateIsNull() )
        return false;
    } else if ( row.getShortCashMarginDepositRateIsNull() || ( short_cash_margin_deposit_rate.doubleValue() != row.getShortCashMarginDepositRate() ) ) {
        return false;
    }
    if ( last_closing_price != row.getLastClosingPrice() )
      return false;
    if ( price_range_type == null ) {
      if ( row.getPriceRangeType() != null )
        return false;
    } else if ( !price_range_type.equals( row.getPriceRangeType() ) ) {
        return false;
    }
    if ( price_range_unit_type == null ) {
      if ( row.getPriceRangeUnitType() != null )
        return false;
    } else if ( !price_range_unit_type.equals( row.getPriceRangeUnitType() ) ) {
        return false;
    }
    if ( high_compulsive_price_range == null ) {
      if ( !row.getHighCompulsivePriceRangeIsNull() )
        return false;
    } else if ( row.getHighCompulsivePriceRangeIsNull() || ( high_compulsive_price_range.doubleValue() != row.getHighCompulsivePriceRange() ) ) {
        return false;
    }
    if ( low_compulsive_price_range == null ) {
      if ( !row.getLowCompulsivePriceRangeIsNull() )
        return false;
    } else if ( row.getLowCompulsivePriceRangeIsNull() || ( low_compulsive_price_range.doubleValue() != row.getLowCompulsivePriceRange() ) ) {
        return false;
    }
    if ( stop_high_price == null ) {
      if ( !row.getStopHighPriceIsNull() )
        return false;
    } else if ( row.getStopHighPriceIsNull() || ( stop_high_price.doubleValue() != row.getStopHighPrice() ) ) {
        return false;
    }
    if ( stop_low_price == null ) {
      if ( !row.getStopLowPriceIsNull() )
        return false;
    } else if ( row.getStopLowPriceIsNull() || ( stop_low_price.doubleValue() != row.getStopLowPrice() ) ) {
        return false;
    }
    if ( price_range_ratio == null ) {
      if ( !row.getPriceRangeRatioIsNull() )
        return false;
    } else if ( row.getPriceRangeRatioIsNull() || ( price_range_ratio.doubleValue() != row.getPriceRangeRatio() ) ) {
        return false;
    }
    if ( compulsive_limited_unit == null ) {
      if ( !row.getCompulsiveLimitedUnitIsNull() )
        return false;
    } else if ( row.getCompulsiveLimitedUnitIsNull() || ( compulsive_limited_unit.intValue() != row.getCompulsiveLimitedUnit() ) ) {
        return false;
    }
    if ( mini_stock_flag == null ) {
      if ( row.getMiniStockFlag() != null )
        return false;
    } else if ( !mini_stock_flag.equals( row.getMiniStockFlag() ) ) {
        return false;
    }
    if ( today_dep_fund_reg == null ) {
      if ( row.getTodayDepFundReg() != null )
        return false;
    } else if ( !today_dep_fund_reg.equals( row.getTodayDepFundReg() ) ) {
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
    if ( base_price != row.getBasePrice() )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) traded_product_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) product_id)
        + ((int) market_id)
        + (valid_until_biz_date!=null? valid_until_biz_date.hashCode(): 0) 
        + (list_flag!=null? list_flag.hashCode(): 0) 
        + (list_type!=null? list_type.hashCode(): 0) 
        + (new_list_type!=null? new_list_type.hashCode(): 0) 
        + (listed_date!=null? listed_date.hashCode(): 0) 
        + (unlisted_date!=null? unlisted_date.hashCode(): 0) 
        + (marginable_flag!=null? marginable_flag.hashCode(): 0) 
        + (shortable_flag!=null? shortable_flag.hashCode(): 0) 
        + (open_otc_div!=null? open_otc_div.hashCode(): 0) 
        + (margin_sys_product_type!=null? margin_sys_product_type.hashCode(): 0) 
        + (margin_gen_product_type!=null? margin_gen_product_type.hashCode(): 0) 
        + (mini_stock_can_dealt!=null? mini_stock_can_dealt.hashCode(): 0) 
        + (buy_cash_stop!=null? buy_cash_stop.hashCode(): 0) 
        + (sell_cash_stop!=null? sell_cash_stop.hashCode(): 0) 
        + (buy_spot_market_ord_des_stop!=null? buy_spot_market_ord_des_stop.hashCode(): 0) 
        + (sell_spot_market_ord_des_stop!=null? sell_spot_market_ord_des_stop.hashCode(): 0) 
        + (long_margin_sys_stop!=null? long_margin_sys_stop.hashCode(): 0) 
        + (short_margin_sys_stop!=null? short_margin_sys_stop.hashCode(): 0) 
        + (long_ms_market_ord_des_stop!=null? long_ms_market_ord_des_stop.hashCode(): 0) 
        + (short_ms_market_ord_des_stop!=null? short_ms_market_ord_des_stop.hashCode(): 0) 
        + (long_margin_gen_stop!=null? long_margin_gen_stop.hashCode(): 0) 
        + (short_margin_gen_stop!=null? short_margin_gen_stop.hashCode(): 0) 
        + (long_mg_market_ord_des_stop!=null? long_mg_market_ord_des_stop.hashCode(): 0) 
        + (short_mg_market_ord_des_stop!=null? short_mg_market_ord_des_stop.hashCode(): 0) 
        + (long_close_margin_sys_stop!=null? long_close_margin_sys_stop.hashCode(): 0) 
        + (short_close_margin_sys_stop!=null? short_close_margin_sys_stop.hashCode(): 0) 
        + (long_cms_market_ord_des_stop!=null? long_cms_market_ord_des_stop.hashCode(): 0) 
        + (short_cms_market_ord_des_stop!=null? short_cms_market_ord_des_stop.hashCode(): 0) 
        + (long_close_margin_gen_stop!=null? long_close_margin_gen_stop.hashCode(): 0) 
        + (short_close_margin_gen_stop!=null? short_close_margin_gen_stop.hashCode(): 0) 
        + (long_cmg_market_ord_des_stop!=null? long_cmg_market_ord_des_stop.hashCode(): 0) 
        + (short_cmg_market_ord_des_stop!=null? short_cmg_market_ord_des_stop.hashCode(): 0) 
        + (long_swap_margin_sys_stop!=null? long_swap_margin_sys_stop.hashCode(): 0) 
        + (short_swap_margin_sys_stop!=null? short_swap_margin_sys_stop.hashCode(): 0) 
        + (long_swap_margin_gen_stop!=null? long_swap_margin_gen_stop.hashCode(): 0) 
        + (short_swap_margin_gen_stop!=null? short_swap_margin_gen_stop.hashCode(): 0) 
        + (buy_mini_stock_stop!=null? buy_mini_stock_stop.hashCode(): 0) 
        + (sell_mini_stock_stop!=null? sell_mini_stock_stop.hashCode(): 0) 
        + ((int) lot_size)
        + (long_margin_deposit_rate!=null? long_margin_deposit_rate.hashCode(): 0) 
        + (short_margin_deposit_rate!=null? short_margin_deposit_rate.hashCode(): 0) 
        + (long_cash_margin_deposit_rate!=null? long_cash_margin_deposit_rate.hashCode(): 0) 
        + (short_cash_margin_deposit_rate!=null? short_cash_margin_deposit_rate.hashCode(): 0) 
        + ((int) last_closing_price)
        + (price_range_type!=null? price_range_type.hashCode(): 0) 
        + (price_range_unit_type!=null? price_range_unit_type.hashCode(): 0) 
        + (high_compulsive_price_range!=null? high_compulsive_price_range.hashCode(): 0) 
        + (low_compulsive_price_range!=null? low_compulsive_price_range.hashCode(): 0) 
        + (stop_high_price!=null? stop_high_price.hashCode(): 0) 
        + (stop_low_price!=null? stop_low_price.hashCode(): 0) 
        + (price_range_ratio!=null? price_range_ratio.hashCode(): 0) 
        + (compulsive_limited_unit!=null? compulsive_limited_unit.hashCode(): 0) 
        + (mini_stock_flag!=null? mini_stock_flag.hashCode(): 0) 
        + (today_dep_fund_reg!=null? today_dep_fund_reg.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + ((int) base_price)
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !market_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_id' must be set before inserting.");
		if ( !list_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'list_type' must be set before inserting.");
		if ( !new_list_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'new_list_type' must be set before inserting.");
		if ( !listed_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'listed_date' must be set before inserting.");
		if ( !mini_stock_can_dealt_is_set )
			throw new IllegalArgumentException("non-nullable field 'mini_stock_can_dealt' must be set before inserting.");
		if ( !lot_size_is_set )
			throw new IllegalArgumentException("non-nullable field 'lot_size' must be set before inserting.");
		if ( !last_closing_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_closing_price' must be set before inserting.");
		if ( !mini_stock_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'mini_stock_flag' must be set before inserting.");
		if ( !base_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'base_price' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("traded_product_id",new Long(traded_product_id));
		map.put("institution_code",institution_code);
		map.put("product_id",new Long(product_id));
		map.put("market_id",new Long(market_id));
		if ( valid_until_biz_date != null )
			map.put("valid_until_biz_date",valid_until_biz_date);
		if ( list_flag_is_set )
			map.put("list_flag",list_flag);
		map.put("list_type",list_type);
		map.put("new_list_type",new_list_type);
		map.put("listed_date",listed_date);
		if ( unlisted_date != null )
			map.put("unlisted_date",unlisted_date);
		if ( marginable_flag_is_set )
			map.put("marginable_flag",marginable_flag);
		if ( shortable_flag_is_set )
			map.put("shortable_flag",shortable_flag);
		if ( open_otc_div != null )
			map.put("open_otc_div",open_otc_div);
		if ( margin_sys_product_type != null )
			map.put("margin_sys_product_type",margin_sys_product_type);
		if ( margin_gen_product_type != null )
			map.put("margin_gen_product_type",margin_gen_product_type);
		map.put("mini_stock_can_dealt",mini_stock_can_dealt);
		if ( buy_cash_stop_is_set )
			map.put("buy_cash_stop",buy_cash_stop);
		if ( sell_cash_stop_is_set )
			map.put("sell_cash_stop",sell_cash_stop);
		if ( buy_spot_market_ord_des_stop != null )
			map.put("buy_spot_market_ord_des_stop",buy_spot_market_ord_des_stop);
		if ( sell_spot_market_ord_des_stop != null )
			map.put("sell_spot_market_ord_des_stop",sell_spot_market_ord_des_stop);
		if ( long_margin_sys_stop_is_set )
			map.put("long_margin_sys_stop",long_margin_sys_stop);
		if ( short_margin_sys_stop_is_set )
			map.put("short_margin_sys_stop",short_margin_sys_stop);
		if ( long_ms_market_ord_des_stop != null )
			map.put("long_ms_market_ord_des_stop",long_ms_market_ord_des_stop);
		if ( short_ms_market_ord_des_stop != null )
			map.put("short_ms_market_ord_des_stop",short_ms_market_ord_des_stop);
		if ( long_margin_gen_stop_is_set )
			map.put("long_margin_gen_stop",long_margin_gen_stop);
		if ( short_margin_gen_stop_is_set )
			map.put("short_margin_gen_stop",short_margin_gen_stop);
		if ( long_mg_market_ord_des_stop != null )
			map.put("long_mg_market_ord_des_stop",long_mg_market_ord_des_stop);
		if ( short_mg_market_ord_des_stop != null )
			map.put("short_mg_market_ord_des_stop",short_mg_market_ord_des_stop);
		if ( long_close_margin_sys_stop_is_set )
			map.put("long_close_margin_sys_stop",long_close_margin_sys_stop);
		if ( short_close_margin_sys_stop_is_set )
			map.put("short_close_margin_sys_stop",short_close_margin_sys_stop);
		if ( long_cms_market_ord_des_stop != null )
			map.put("long_cms_market_ord_des_stop",long_cms_market_ord_des_stop);
		if ( short_cms_market_ord_des_stop != null )
			map.put("short_cms_market_ord_des_stop",short_cms_market_ord_des_stop);
		if ( long_close_margin_gen_stop_is_set )
			map.put("long_close_margin_gen_stop",long_close_margin_gen_stop);
		if ( short_close_margin_gen_stop_is_set )
			map.put("short_close_margin_gen_stop",short_close_margin_gen_stop);
		if ( long_cmg_market_ord_des_stop != null )
			map.put("long_cmg_market_ord_des_stop",long_cmg_market_ord_des_stop);
		if ( short_cmg_market_ord_des_stop != null )
			map.put("short_cmg_market_ord_des_stop",short_cmg_market_ord_des_stop);
		if ( long_swap_margin_sys_stop_is_set )
			map.put("long_swap_margin_sys_stop",long_swap_margin_sys_stop);
		if ( short_swap_margin_sys_stop_is_set )
			map.put("short_swap_margin_sys_stop",short_swap_margin_sys_stop);
		if ( long_swap_margin_gen_stop_is_set )
			map.put("long_swap_margin_gen_stop",long_swap_margin_gen_stop);
		if ( short_swap_margin_gen_stop_is_set )
			map.put("short_swap_margin_gen_stop",short_swap_margin_gen_stop);
		if ( buy_mini_stock_stop_is_set )
			map.put("buy_mini_stock_stop",buy_mini_stock_stop);
		if ( sell_mini_stock_stop_is_set )
			map.put("sell_mini_stock_stop",sell_mini_stock_stop);
		map.put("lot_size",new Double(lot_size));
		if ( long_margin_deposit_rate != null )
			map.put("long_margin_deposit_rate",long_margin_deposit_rate);
		if ( short_margin_deposit_rate != null )
			map.put("short_margin_deposit_rate",short_margin_deposit_rate);
		if ( long_cash_margin_deposit_rate != null )
			map.put("long_cash_margin_deposit_rate",long_cash_margin_deposit_rate);
		if ( short_cash_margin_deposit_rate != null )
			map.put("short_cash_margin_deposit_rate",short_cash_margin_deposit_rate);
		map.put("last_closing_price",new Double(last_closing_price));
		if ( price_range_type != null )
			map.put("price_range_type",price_range_type);
		if ( price_range_unit_type != null )
			map.put("price_range_unit_type",price_range_unit_type);
		if ( high_compulsive_price_range != null )
			map.put("high_compulsive_price_range",high_compulsive_price_range);
		if ( low_compulsive_price_range != null )
			map.put("low_compulsive_price_range",low_compulsive_price_range);
		if ( stop_high_price != null )
			map.put("stop_high_price",stop_high_price);
		if ( stop_low_price != null )
			map.put("stop_low_price",stop_low_price);
		if ( price_range_ratio != null )
			map.put("price_range_ratio",price_range_ratio);
		if ( compulsive_limited_unit != null )
			map.put("compulsive_limited_unit",compulsive_limited_unit);
		map.put("mini_stock_flag",mini_stock_flag);
		if ( today_dep_fund_reg_is_set )
			map.put("today_dep_fund_reg",today_dep_fund_reg);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("base_price",new Double(base_price));
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if ( valid_until_biz_date_is_modified )
			map.put("valid_until_biz_date",valid_until_biz_date);
		if ( list_flag_is_modified )
			map.put("list_flag",list_flag);
		if ( list_type_is_modified )
			map.put("list_type",list_type);
		if ( new_list_type_is_modified )
			map.put("new_list_type",new_list_type);
		if ( listed_date_is_modified )
			map.put("listed_date",listed_date);
		if ( unlisted_date_is_modified )
			map.put("unlisted_date",unlisted_date);
		if ( marginable_flag_is_modified )
			map.put("marginable_flag",marginable_flag);
		if ( shortable_flag_is_modified )
			map.put("shortable_flag",shortable_flag);
		if ( open_otc_div_is_modified )
			map.put("open_otc_div",open_otc_div);
		if ( margin_sys_product_type_is_modified )
			map.put("margin_sys_product_type",margin_sys_product_type);
		if ( margin_gen_product_type_is_modified )
			map.put("margin_gen_product_type",margin_gen_product_type);
		if ( mini_stock_can_dealt_is_modified )
			map.put("mini_stock_can_dealt",mini_stock_can_dealt);
		if ( buy_cash_stop_is_modified )
			map.put("buy_cash_stop",buy_cash_stop);
		if ( sell_cash_stop_is_modified )
			map.put("sell_cash_stop",sell_cash_stop);
		if ( buy_spot_market_ord_des_stop_is_modified )
			map.put("buy_spot_market_ord_des_stop",buy_spot_market_ord_des_stop);
		if ( sell_spot_market_ord_des_stop_is_modified )
			map.put("sell_spot_market_ord_des_stop",sell_spot_market_ord_des_stop);
		if ( long_margin_sys_stop_is_modified )
			map.put("long_margin_sys_stop",long_margin_sys_stop);
		if ( short_margin_sys_stop_is_modified )
			map.put("short_margin_sys_stop",short_margin_sys_stop);
		if ( long_ms_market_ord_des_stop_is_modified )
			map.put("long_ms_market_ord_des_stop",long_ms_market_ord_des_stop);
		if ( short_ms_market_ord_des_stop_is_modified )
			map.put("short_ms_market_ord_des_stop",short_ms_market_ord_des_stop);
		if ( long_margin_gen_stop_is_modified )
			map.put("long_margin_gen_stop",long_margin_gen_stop);
		if ( short_margin_gen_stop_is_modified )
			map.put("short_margin_gen_stop",short_margin_gen_stop);
		if ( long_mg_market_ord_des_stop_is_modified )
			map.put("long_mg_market_ord_des_stop",long_mg_market_ord_des_stop);
		if ( short_mg_market_ord_des_stop_is_modified )
			map.put("short_mg_market_ord_des_stop",short_mg_market_ord_des_stop);
		if ( long_close_margin_sys_stop_is_modified )
			map.put("long_close_margin_sys_stop",long_close_margin_sys_stop);
		if ( short_close_margin_sys_stop_is_modified )
			map.put("short_close_margin_sys_stop",short_close_margin_sys_stop);
		if ( long_cms_market_ord_des_stop_is_modified )
			map.put("long_cms_market_ord_des_stop",long_cms_market_ord_des_stop);
		if ( short_cms_market_ord_des_stop_is_modified )
			map.put("short_cms_market_ord_des_stop",short_cms_market_ord_des_stop);
		if ( long_close_margin_gen_stop_is_modified )
			map.put("long_close_margin_gen_stop",long_close_margin_gen_stop);
		if ( short_close_margin_gen_stop_is_modified )
			map.put("short_close_margin_gen_stop",short_close_margin_gen_stop);
		if ( long_cmg_market_ord_des_stop_is_modified )
			map.put("long_cmg_market_ord_des_stop",long_cmg_market_ord_des_stop);
		if ( short_cmg_market_ord_des_stop_is_modified )
			map.put("short_cmg_market_ord_des_stop",short_cmg_market_ord_des_stop);
		if ( long_swap_margin_sys_stop_is_modified )
			map.put("long_swap_margin_sys_stop",long_swap_margin_sys_stop);
		if ( short_swap_margin_sys_stop_is_modified )
			map.put("short_swap_margin_sys_stop",short_swap_margin_sys_stop);
		if ( long_swap_margin_gen_stop_is_modified )
			map.put("long_swap_margin_gen_stop",long_swap_margin_gen_stop);
		if ( short_swap_margin_gen_stop_is_modified )
			map.put("short_swap_margin_gen_stop",short_swap_margin_gen_stop);
		if ( buy_mini_stock_stop_is_modified )
			map.put("buy_mini_stock_stop",buy_mini_stock_stop);
		if ( sell_mini_stock_stop_is_modified )
			map.put("sell_mini_stock_stop",sell_mini_stock_stop);
		if ( lot_size_is_modified )
			map.put("lot_size",new Double(lot_size));
		if ( long_margin_deposit_rate_is_modified )
			map.put("long_margin_deposit_rate",long_margin_deposit_rate);
		if ( short_margin_deposit_rate_is_modified )
			map.put("short_margin_deposit_rate",short_margin_deposit_rate);
		if ( long_cash_margin_deposit_rate_is_modified )
			map.put("long_cash_margin_deposit_rate",long_cash_margin_deposit_rate);
		if ( short_cash_margin_deposit_rate_is_modified )
			map.put("short_cash_margin_deposit_rate",short_cash_margin_deposit_rate);
		if ( last_closing_price_is_modified )
			map.put("last_closing_price",new Double(last_closing_price));
		if ( price_range_type_is_modified )
			map.put("price_range_type",price_range_type);
		if ( price_range_unit_type_is_modified )
			map.put("price_range_unit_type",price_range_unit_type);
		if ( high_compulsive_price_range_is_modified )
			map.put("high_compulsive_price_range",high_compulsive_price_range);
		if ( low_compulsive_price_range_is_modified )
			map.put("low_compulsive_price_range",low_compulsive_price_range);
		if ( stop_high_price_is_modified )
			map.put("stop_high_price",stop_high_price);
		if ( stop_low_price_is_modified )
			map.put("stop_low_price",stop_low_price);
		if ( price_range_ratio_is_modified )
			map.put("price_range_ratio",price_range_ratio);
		if ( compulsive_limited_unit_is_modified )
			map.put("compulsive_limited_unit",compulsive_limited_unit);
		if ( mini_stock_flag_is_modified )
			map.put("mini_stock_flag",mini_stock_flag);
		if ( today_dep_fund_reg_is_modified )
			map.put("today_dep_fund_reg",today_dep_fund_reg);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( base_price_is_modified )
			map.put("base_price",new Double(base_price));
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
			map.put("valid_until_biz_date",valid_until_biz_date);
			if ( list_flag_is_set )
				map.put("list_flag",list_flag);
			if ( list_type_is_set )
				map.put("list_type",list_type);
			if ( new_list_type_is_set )
				map.put("new_list_type",new_list_type);
			if ( listed_date_is_set )
				map.put("listed_date",listed_date);
			map.put("unlisted_date",unlisted_date);
			if ( marginable_flag_is_set )
				map.put("marginable_flag",marginable_flag);
			if ( shortable_flag_is_set )
				map.put("shortable_flag",shortable_flag);
			map.put("open_otc_div",open_otc_div);
			map.put("margin_sys_product_type",margin_sys_product_type);
			map.put("margin_gen_product_type",margin_gen_product_type);
			if ( mini_stock_can_dealt_is_set )
				map.put("mini_stock_can_dealt",mini_stock_can_dealt);
			if ( buy_cash_stop_is_set )
				map.put("buy_cash_stop",buy_cash_stop);
			if ( sell_cash_stop_is_set )
				map.put("sell_cash_stop",sell_cash_stop);
			map.put("buy_spot_market_ord_des_stop",buy_spot_market_ord_des_stop);
			map.put("sell_spot_market_ord_des_stop",sell_spot_market_ord_des_stop);
			if ( long_margin_sys_stop_is_set )
				map.put("long_margin_sys_stop",long_margin_sys_stop);
			if ( short_margin_sys_stop_is_set )
				map.put("short_margin_sys_stop",short_margin_sys_stop);
			map.put("long_ms_market_ord_des_stop",long_ms_market_ord_des_stop);
			map.put("short_ms_market_ord_des_stop",short_ms_market_ord_des_stop);
			if ( long_margin_gen_stop_is_set )
				map.put("long_margin_gen_stop",long_margin_gen_stop);
			if ( short_margin_gen_stop_is_set )
				map.put("short_margin_gen_stop",short_margin_gen_stop);
			map.put("long_mg_market_ord_des_stop",long_mg_market_ord_des_stop);
			map.put("short_mg_market_ord_des_stop",short_mg_market_ord_des_stop);
			if ( long_close_margin_sys_stop_is_set )
				map.put("long_close_margin_sys_stop",long_close_margin_sys_stop);
			if ( short_close_margin_sys_stop_is_set )
				map.put("short_close_margin_sys_stop",short_close_margin_sys_stop);
			map.put("long_cms_market_ord_des_stop",long_cms_market_ord_des_stop);
			map.put("short_cms_market_ord_des_stop",short_cms_market_ord_des_stop);
			if ( long_close_margin_gen_stop_is_set )
				map.put("long_close_margin_gen_stop",long_close_margin_gen_stop);
			if ( short_close_margin_gen_stop_is_set )
				map.put("short_close_margin_gen_stop",short_close_margin_gen_stop);
			map.put("long_cmg_market_ord_des_stop",long_cmg_market_ord_des_stop);
			map.put("short_cmg_market_ord_des_stop",short_cmg_market_ord_des_stop);
			if ( long_swap_margin_sys_stop_is_set )
				map.put("long_swap_margin_sys_stop",long_swap_margin_sys_stop);
			if ( short_swap_margin_sys_stop_is_set )
				map.put("short_swap_margin_sys_stop",short_swap_margin_sys_stop);
			if ( long_swap_margin_gen_stop_is_set )
				map.put("long_swap_margin_gen_stop",long_swap_margin_gen_stop);
			if ( short_swap_margin_gen_stop_is_set )
				map.put("short_swap_margin_gen_stop",short_swap_margin_gen_stop);
			if ( buy_mini_stock_stop_is_set )
				map.put("buy_mini_stock_stop",buy_mini_stock_stop);
			if ( sell_mini_stock_stop_is_set )
				map.put("sell_mini_stock_stop",sell_mini_stock_stop);
			if ( lot_size_is_set )
				map.put("lot_size",new Double(lot_size));
			map.put("long_margin_deposit_rate",long_margin_deposit_rate);
			map.put("short_margin_deposit_rate",short_margin_deposit_rate);
			map.put("long_cash_margin_deposit_rate",long_cash_margin_deposit_rate);
			map.put("short_cash_margin_deposit_rate",short_cash_margin_deposit_rate);
			if ( last_closing_price_is_set )
				map.put("last_closing_price",new Double(last_closing_price));
			map.put("price_range_type",price_range_type);
			map.put("price_range_unit_type",price_range_unit_type);
			map.put("high_compulsive_price_range",high_compulsive_price_range);
			map.put("low_compulsive_price_range",low_compulsive_price_range);
			map.put("stop_high_price",stop_high_price);
			map.put("stop_low_price",stop_low_price);
			map.put("price_range_ratio",price_range_ratio);
			map.put("compulsive_limited_unit",compulsive_limited_unit);
			if ( mini_stock_flag_is_set )
				map.put("mini_stock_flag",mini_stock_flag);
			if ( today_dep_fund_reg_is_set )
				map.put("today_dep_fund_reg",today_dep_fund_reg);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( base_price_is_set )
				map.put("base_price",new Double(base_price));
		}
		return map;
	}


  /** 
   * <em>traded_product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTradedProductId()
  {
    return traded_product_id;
  }


  /** 
   * <em>traded_product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradedProductIdIsSet() {
    return traded_product_id_is_set;
  }


  /** 
   * <em>traded_product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradedProductIdIsModified() {
    return traded_product_id_is_modified;
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
   * <em>market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarketId()
  {
    return market_id;
  }


  /** 
   * <em>market_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsSet() {
    return market_id_is_set;
  }


  /** 
   * <em>market_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsModified() {
    return market_id_is_modified;
  }


  /** 
   * <em>valid_until_biz_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getValidUntilBizDate()
  {
    return valid_until_biz_date;
  }


  /** 
   * <em>valid_until_biz_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidUntilBizDateIsSet() {
    return valid_until_biz_date_is_set;
  }


  /** 
   * <em>valid_until_biz_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidUntilBizDateIsModified() {
    return valid_until_biz_date_is_modified;
  }


  /** 
   * <em>list_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getListFlag()
  {
    return list_flag;
  }


  /** 
   * <em>list_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListFlagIsSet() {
    return list_flag_is_set;
  }


  /** 
   * <em>list_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListFlagIsModified() {
    return list_flag_is_modified;
  }


  /** 
   * <em>list_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getListType()
  {
    return list_type;
  }


  /** 
   * <em>list_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListTypeIsSet() {
    return list_type_is_set;
  }


  /** 
   * <em>list_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListTypeIsModified() {
    return list_type_is_modified;
  }


  /** 
   * <em>new_list_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNewListType()
  {
    return new_list_type;
  }


  /** 
   * <em>new_list_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewListTypeIsSet() {
    return new_list_type_is_set;
  }


  /** 
   * <em>new_list_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewListTypeIsModified() {
    return new_list_type_is_modified;
  }


  /** 
   * <em>listed_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getListedDate()
  {
    return listed_date;
  }


  /** 
   * <em>listed_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListedDateIsSet() {
    return listed_date_is_set;
  }


  /** 
   * <em>listed_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListedDateIsModified() {
    return listed_date_is_modified;
  }


  /** 
   * <em>unlisted_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getUnlistedDate()
  {
    return unlisted_date;
  }


  /** 
   * <em>unlisted_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnlistedDateIsSet() {
    return unlisted_date_is_set;
  }


  /** 
   * <em>unlisted_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnlistedDateIsModified() {
    return unlisted_date_is_modified;
  }


  /** 
   * <em>marginable_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMarginableFlag()
  {
    return marginable_flag;
  }


  /** 
   * <em>marginable_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginableFlagIsSet() {
    return marginable_flag_is_set;
  }


  /** 
   * <em>marginable_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginableFlagIsModified() {
    return marginable_flag_is_modified;
  }


  /** 
   * <em>shortable_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getShortableFlag()
  {
    return shortable_flag;
  }


  /** 
   * <em>shortable_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortableFlagIsSet() {
    return shortable_flag_is_set;
  }


  /** 
   * <em>shortable_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortableFlagIsModified() {
    return shortable_flag_is_modified;
  }


  /** 
   * <em>open_otc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOpenOtcDiv()
  {
    return open_otc_div;
  }


  /** 
   * <em>open_otc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenOtcDivIsSet() {
    return open_otc_div_is_set;
  }


  /** 
   * <em>open_otc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenOtcDivIsModified() {
    return open_otc_div_is_modified;
  }


  /** 
   * <em>margin_sys_product_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginSysProductType()
  {
    return margin_sys_product_type;
  }


  /** 
   * <em>margin_sys_product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSysProductTypeIsSet() {
    return margin_sys_product_type_is_set;
  }


  /** 
   * <em>margin_sys_product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSysProductTypeIsModified() {
    return margin_sys_product_type_is_modified;
  }


  /** 
   * <em>margin_gen_product_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginGenProductType()
  {
    return margin_gen_product_type;
  }


  /** 
   * <em>margin_gen_product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginGenProductTypeIsSet() {
    return margin_gen_product_type_is_set;
  }


  /** 
   * <em>margin_gen_product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginGenProductTypeIsModified() {
    return margin_gen_product_type_is_modified;
  }


  /** 
   * <em>mini_stock_can_dealt</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMiniStockCanDealt()
  {
    return mini_stock_can_dealt;
  }


  /** 
   * <em>mini_stock_can_dealt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockCanDealtIsSet() {
    return mini_stock_can_dealt_is_set;
  }


  /** 
   * <em>mini_stock_can_dealt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockCanDealtIsModified() {
    return mini_stock_can_dealt_is_modified;
  }


  /** 
   * <em>buy_cash_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getBuyCashStop()
  {
    return ( buy_cash_stop==null? ((int)0): buy_cash_stop.intValue() );
  }


  /** 
   * <em>buy_cash_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyCashStopIsNull()
  {
    return buy_cash_stop == null;
  }


  /** 
   * <em>buy_cash_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyCashStopIsSet() {
    return buy_cash_stop_is_set;
  }


  /** 
   * <em>buy_cash_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyCashStopIsModified() {
    return buy_cash_stop_is_modified;
  }


  /** 
   * <em>sell_cash_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSellCashStop()
  {
    return ( sell_cash_stop==null? ((int)0): sell_cash_stop.intValue() );
  }


  /** 
   * <em>sell_cash_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellCashStopIsNull()
  {
    return sell_cash_stop == null;
  }


  /** 
   * <em>sell_cash_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellCashStopIsSet() {
    return sell_cash_stop_is_set;
  }


  /** 
   * <em>sell_cash_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellCashStopIsModified() {
    return sell_cash_stop_is_modified;
  }


  /** 
   * <em>buy_spot_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getBuySpotMarketOrdDesStop()
  {
    return ( buy_spot_market_ord_des_stop==null? ((int)0): buy_spot_market_ord_des_stop.intValue() );
  }


  /** 
   * <em>buy_spot_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuySpotMarketOrdDesStopIsNull()
  {
    return buy_spot_market_ord_des_stop == null;
  }


  /** 
   * <em>buy_spot_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuySpotMarketOrdDesStopIsSet() {
    return buy_spot_market_ord_des_stop_is_set;
  }


  /** 
   * <em>buy_spot_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuySpotMarketOrdDesStopIsModified() {
    return buy_spot_market_ord_des_stop_is_modified;
  }


  /** 
   * <em>sell_spot_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSellSpotMarketOrdDesStop()
  {
    return ( sell_spot_market_ord_des_stop==null? ((int)0): sell_spot_market_ord_des_stop.intValue() );
  }


  /** 
   * <em>sell_spot_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellSpotMarketOrdDesStopIsNull()
  {
    return sell_spot_market_ord_des_stop == null;
  }


  /** 
   * <em>sell_spot_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellSpotMarketOrdDesStopIsSet() {
    return sell_spot_market_ord_des_stop_is_set;
  }


  /** 
   * <em>sell_spot_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellSpotMarketOrdDesStopIsModified() {
    return sell_spot_market_ord_des_stop_is_modified;
  }


  /** 
   * <em>long_margin_sys_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLongMarginSysStop()
  {
    return ( long_margin_sys_stop==null? ((int)0): long_margin_sys_stop.intValue() );
  }


  /** 
   * <em>long_margin_sys_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLongMarginSysStopIsNull()
  {
    return long_margin_sys_stop == null;
  }


  /** 
   * <em>long_margin_sys_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongMarginSysStopIsSet() {
    return long_margin_sys_stop_is_set;
  }


  /** 
   * <em>long_margin_sys_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongMarginSysStopIsModified() {
    return long_margin_sys_stop_is_modified;
  }


  /** 
   * <em>short_margin_sys_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getShortMarginSysStop()
  {
    return ( short_margin_sys_stop==null? ((int)0): short_margin_sys_stop.intValue() );
  }


  /** 
   * <em>short_margin_sys_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortMarginSysStopIsNull()
  {
    return short_margin_sys_stop == null;
  }


  /** 
   * <em>short_margin_sys_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMarginSysStopIsSet() {
    return short_margin_sys_stop_is_set;
  }


  /** 
   * <em>short_margin_sys_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMarginSysStopIsModified() {
    return short_margin_sys_stop_is_modified;
  }


  /** 
   * <em>long_ms_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLongMsMarketOrdDesStop()
  {
    return ( long_ms_market_ord_des_stop==null? ((int)0): long_ms_market_ord_des_stop.intValue() );
  }


  /** 
   * <em>long_ms_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLongMsMarketOrdDesStopIsNull()
  {
    return long_ms_market_ord_des_stop == null;
  }


  /** 
   * <em>long_ms_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongMsMarketOrdDesStopIsSet() {
    return long_ms_market_ord_des_stop_is_set;
  }


  /** 
   * <em>long_ms_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongMsMarketOrdDesStopIsModified() {
    return long_ms_market_ord_des_stop_is_modified;
  }


  /** 
   * <em>short_ms_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getShortMsMarketOrdDesStop()
  {
    return ( short_ms_market_ord_des_stop==null? ((int)0): short_ms_market_ord_des_stop.intValue() );
  }


  /** 
   * <em>short_ms_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortMsMarketOrdDesStopIsNull()
  {
    return short_ms_market_ord_des_stop == null;
  }


  /** 
   * <em>short_ms_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMsMarketOrdDesStopIsSet() {
    return short_ms_market_ord_des_stop_is_set;
  }


  /** 
   * <em>short_ms_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMsMarketOrdDesStopIsModified() {
    return short_ms_market_ord_des_stop_is_modified;
  }


  /** 
   * <em>long_margin_gen_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLongMarginGenStop()
  {
    return ( long_margin_gen_stop==null? ((int)0): long_margin_gen_stop.intValue() );
  }


  /** 
   * <em>long_margin_gen_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLongMarginGenStopIsNull()
  {
    return long_margin_gen_stop == null;
  }


  /** 
   * <em>long_margin_gen_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongMarginGenStopIsSet() {
    return long_margin_gen_stop_is_set;
  }


  /** 
   * <em>long_margin_gen_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongMarginGenStopIsModified() {
    return long_margin_gen_stop_is_modified;
  }


  /** 
   * <em>short_margin_gen_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getShortMarginGenStop()
  {
    return ( short_margin_gen_stop==null? ((int)0): short_margin_gen_stop.intValue() );
  }


  /** 
   * <em>short_margin_gen_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortMarginGenStopIsNull()
  {
    return short_margin_gen_stop == null;
  }


  /** 
   * <em>short_margin_gen_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMarginGenStopIsSet() {
    return short_margin_gen_stop_is_set;
  }


  /** 
   * <em>short_margin_gen_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMarginGenStopIsModified() {
    return short_margin_gen_stop_is_modified;
  }


  /** 
   * <em>long_mg_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLongMgMarketOrdDesStop()
  {
    return ( long_mg_market_ord_des_stop==null? ((int)0): long_mg_market_ord_des_stop.intValue() );
  }


  /** 
   * <em>long_mg_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLongMgMarketOrdDesStopIsNull()
  {
    return long_mg_market_ord_des_stop == null;
  }


  /** 
   * <em>long_mg_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongMgMarketOrdDesStopIsSet() {
    return long_mg_market_ord_des_stop_is_set;
  }


  /** 
   * <em>long_mg_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongMgMarketOrdDesStopIsModified() {
    return long_mg_market_ord_des_stop_is_modified;
  }


  /** 
   * <em>short_mg_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getShortMgMarketOrdDesStop()
  {
    return ( short_mg_market_ord_des_stop==null? ((int)0): short_mg_market_ord_des_stop.intValue() );
  }


  /** 
   * <em>short_mg_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortMgMarketOrdDesStopIsNull()
  {
    return short_mg_market_ord_des_stop == null;
  }


  /** 
   * <em>short_mg_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMgMarketOrdDesStopIsSet() {
    return short_mg_market_ord_des_stop_is_set;
  }


  /** 
   * <em>short_mg_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMgMarketOrdDesStopIsModified() {
    return short_mg_market_ord_des_stop_is_modified;
  }


  /** 
   * <em>long_close_margin_sys_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLongCloseMarginSysStop()
  {
    return ( long_close_margin_sys_stop==null? ((int)0): long_close_margin_sys_stop.intValue() );
  }


  /** 
   * <em>long_close_margin_sys_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLongCloseMarginSysStopIsNull()
  {
    return long_close_margin_sys_stop == null;
  }


  /** 
   * <em>long_close_margin_sys_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongCloseMarginSysStopIsSet() {
    return long_close_margin_sys_stop_is_set;
  }


  /** 
   * <em>long_close_margin_sys_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongCloseMarginSysStopIsModified() {
    return long_close_margin_sys_stop_is_modified;
  }


  /** 
   * <em>short_close_margin_sys_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getShortCloseMarginSysStop()
  {
    return ( short_close_margin_sys_stop==null? ((int)0): short_close_margin_sys_stop.intValue() );
  }


  /** 
   * <em>short_close_margin_sys_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortCloseMarginSysStopIsNull()
  {
    return short_close_margin_sys_stop == null;
  }


  /** 
   * <em>short_close_margin_sys_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortCloseMarginSysStopIsSet() {
    return short_close_margin_sys_stop_is_set;
  }


  /** 
   * <em>short_close_margin_sys_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortCloseMarginSysStopIsModified() {
    return short_close_margin_sys_stop_is_modified;
  }


  /** 
   * <em>long_cms_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLongCmsMarketOrdDesStop()
  {
    return ( long_cms_market_ord_des_stop==null? ((int)0): long_cms_market_ord_des_stop.intValue() );
  }


  /** 
   * <em>long_cms_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLongCmsMarketOrdDesStopIsNull()
  {
    return long_cms_market_ord_des_stop == null;
  }


  /** 
   * <em>long_cms_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongCmsMarketOrdDesStopIsSet() {
    return long_cms_market_ord_des_stop_is_set;
  }


  /** 
   * <em>long_cms_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongCmsMarketOrdDesStopIsModified() {
    return long_cms_market_ord_des_stop_is_modified;
  }


  /** 
   * <em>short_cms_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getShortCmsMarketOrdDesStop()
  {
    return ( short_cms_market_ord_des_stop==null? ((int)0): short_cms_market_ord_des_stop.intValue() );
  }


  /** 
   * <em>short_cms_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortCmsMarketOrdDesStopIsNull()
  {
    return short_cms_market_ord_des_stop == null;
  }


  /** 
   * <em>short_cms_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortCmsMarketOrdDesStopIsSet() {
    return short_cms_market_ord_des_stop_is_set;
  }


  /** 
   * <em>short_cms_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortCmsMarketOrdDesStopIsModified() {
    return short_cms_market_ord_des_stop_is_modified;
  }


  /** 
   * <em>long_close_margin_gen_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLongCloseMarginGenStop()
  {
    return ( long_close_margin_gen_stop==null? ((int)0): long_close_margin_gen_stop.intValue() );
  }


  /** 
   * <em>long_close_margin_gen_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLongCloseMarginGenStopIsNull()
  {
    return long_close_margin_gen_stop == null;
  }


  /** 
   * <em>long_close_margin_gen_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongCloseMarginGenStopIsSet() {
    return long_close_margin_gen_stop_is_set;
  }


  /** 
   * <em>long_close_margin_gen_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongCloseMarginGenStopIsModified() {
    return long_close_margin_gen_stop_is_modified;
  }


  /** 
   * <em>short_close_margin_gen_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getShortCloseMarginGenStop()
  {
    return ( short_close_margin_gen_stop==null? ((int)0): short_close_margin_gen_stop.intValue() );
  }


  /** 
   * <em>short_close_margin_gen_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortCloseMarginGenStopIsNull()
  {
    return short_close_margin_gen_stop == null;
  }


  /** 
   * <em>short_close_margin_gen_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortCloseMarginGenStopIsSet() {
    return short_close_margin_gen_stop_is_set;
  }


  /** 
   * <em>short_close_margin_gen_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortCloseMarginGenStopIsModified() {
    return short_close_margin_gen_stop_is_modified;
  }


  /** 
   * <em>long_cmg_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLongCmgMarketOrdDesStop()
  {
    return ( long_cmg_market_ord_des_stop==null? ((int)0): long_cmg_market_ord_des_stop.intValue() );
  }


  /** 
   * <em>long_cmg_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLongCmgMarketOrdDesStopIsNull()
  {
    return long_cmg_market_ord_des_stop == null;
  }


  /** 
   * <em>long_cmg_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongCmgMarketOrdDesStopIsSet() {
    return long_cmg_market_ord_des_stop_is_set;
  }


  /** 
   * <em>long_cmg_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongCmgMarketOrdDesStopIsModified() {
    return long_cmg_market_ord_des_stop_is_modified;
  }


  /** 
   * <em>short_cmg_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getShortCmgMarketOrdDesStop()
  {
    return ( short_cmg_market_ord_des_stop==null? ((int)0): short_cmg_market_ord_des_stop.intValue() );
  }


  /** 
   * <em>short_cmg_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortCmgMarketOrdDesStopIsNull()
  {
    return short_cmg_market_ord_des_stop == null;
  }


  /** 
   * <em>short_cmg_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortCmgMarketOrdDesStopIsSet() {
    return short_cmg_market_ord_des_stop_is_set;
  }


  /** 
   * <em>short_cmg_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortCmgMarketOrdDesStopIsModified() {
    return short_cmg_market_ord_des_stop_is_modified;
  }


  /** 
   * <em>long_swap_margin_sys_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLongSwapMarginSysStop()
  {
    return ( long_swap_margin_sys_stop==null? ((int)0): long_swap_margin_sys_stop.intValue() );
  }


  /** 
   * <em>long_swap_margin_sys_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLongSwapMarginSysStopIsNull()
  {
    return long_swap_margin_sys_stop == null;
  }


  /** 
   * <em>long_swap_margin_sys_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongSwapMarginSysStopIsSet() {
    return long_swap_margin_sys_stop_is_set;
  }


  /** 
   * <em>long_swap_margin_sys_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongSwapMarginSysStopIsModified() {
    return long_swap_margin_sys_stop_is_modified;
  }


  /** 
   * <em>short_swap_margin_sys_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getShortSwapMarginSysStop()
  {
    return ( short_swap_margin_sys_stop==null? ((int)0): short_swap_margin_sys_stop.intValue() );
  }


  /** 
   * <em>short_swap_margin_sys_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortSwapMarginSysStopIsNull()
  {
    return short_swap_margin_sys_stop == null;
  }


  /** 
   * <em>short_swap_margin_sys_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortSwapMarginSysStopIsSet() {
    return short_swap_margin_sys_stop_is_set;
  }


  /** 
   * <em>short_swap_margin_sys_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortSwapMarginSysStopIsModified() {
    return short_swap_margin_sys_stop_is_modified;
  }


  /** 
   * <em>long_swap_margin_gen_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLongSwapMarginGenStop()
  {
    return ( long_swap_margin_gen_stop==null? ((int)0): long_swap_margin_gen_stop.intValue() );
  }


  /** 
   * <em>long_swap_margin_gen_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLongSwapMarginGenStopIsNull()
  {
    return long_swap_margin_gen_stop == null;
  }


  /** 
   * <em>long_swap_margin_gen_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongSwapMarginGenStopIsSet() {
    return long_swap_margin_gen_stop_is_set;
  }


  /** 
   * <em>long_swap_margin_gen_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongSwapMarginGenStopIsModified() {
    return long_swap_margin_gen_stop_is_modified;
  }


  /** 
   * <em>short_swap_margin_gen_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getShortSwapMarginGenStop()
  {
    return ( short_swap_margin_gen_stop==null? ((int)0): short_swap_margin_gen_stop.intValue() );
  }


  /** 
   * <em>short_swap_margin_gen_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortSwapMarginGenStopIsNull()
  {
    return short_swap_margin_gen_stop == null;
  }


  /** 
   * <em>short_swap_margin_gen_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortSwapMarginGenStopIsSet() {
    return short_swap_margin_gen_stop_is_set;
  }


  /** 
   * <em>short_swap_margin_gen_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortSwapMarginGenStopIsModified() {
    return short_swap_margin_gen_stop_is_modified;
  }


  /** 
   * <em>buy_mini_stock_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getBuyMiniStockStop()
  {
    return ( buy_mini_stock_stop==null? ((int)0): buy_mini_stock_stop.intValue() );
  }


  /** 
   * <em>buy_mini_stock_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyMiniStockStopIsNull()
  {
    return buy_mini_stock_stop == null;
  }


  /** 
   * <em>buy_mini_stock_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyMiniStockStopIsSet() {
    return buy_mini_stock_stop_is_set;
  }


  /** 
   * <em>buy_mini_stock_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyMiniStockStopIsModified() {
    return buy_mini_stock_stop_is_modified;
  }


  /** 
   * <em>sell_mini_stock_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSellMiniStockStop()
  {
    return ( sell_mini_stock_stop==null? ((int)0): sell_mini_stock_stop.intValue() );
  }


  /** 
   * <em>sell_mini_stock_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellMiniStockStopIsNull()
  {
    return sell_mini_stock_stop == null;
  }


  /** 
   * <em>sell_mini_stock_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellMiniStockStopIsSet() {
    return sell_mini_stock_stop_is_set;
  }


  /** 
   * <em>sell_mini_stock_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellMiniStockStopIsModified() {
    return sell_mini_stock_stop_is_modified;
  }


  /** 
   * <em>lot_size</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLotSize()
  {
    return lot_size;
  }


  /** 
   * <em>lot_size</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLotSizeIsSet() {
    return lot_size_is_set;
  }


  /** 
   * <em>lot_size</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLotSizeIsModified() {
    return lot_size_is_modified;
  }


  /** 
   * <em>long_margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLongMarginDepositRate()
  {
    return ( long_margin_deposit_rate==null? ((double)0): long_margin_deposit_rate.doubleValue() );
  }


  /** 
   * <em>long_margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLongMarginDepositRateIsNull()
  {
    return long_margin_deposit_rate == null;
  }


  /** 
   * <em>long_margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongMarginDepositRateIsSet() {
    return long_margin_deposit_rate_is_set;
  }


  /** 
   * <em>long_margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongMarginDepositRateIsModified() {
    return long_margin_deposit_rate_is_modified;
  }


  /** 
   * <em>short_margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getShortMarginDepositRate()
  {
    return ( short_margin_deposit_rate==null? ((double)0): short_margin_deposit_rate.doubleValue() );
  }


  /** 
   * <em>short_margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortMarginDepositRateIsNull()
  {
    return short_margin_deposit_rate == null;
  }


  /** 
   * <em>short_margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMarginDepositRateIsSet() {
    return short_margin_deposit_rate_is_set;
  }


  /** 
   * <em>short_margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortMarginDepositRateIsModified() {
    return short_margin_deposit_rate_is_modified;
  }


  /** 
   * <em>long_cash_margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLongCashMarginDepositRate()
  {
    return ( long_cash_margin_deposit_rate==null? ((double)0): long_cash_margin_deposit_rate.doubleValue() );
  }


  /** 
   * <em>long_cash_margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLongCashMarginDepositRateIsNull()
  {
    return long_cash_margin_deposit_rate == null;
  }


  /** 
   * <em>long_cash_margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongCashMarginDepositRateIsSet() {
    return long_cash_margin_deposit_rate_is_set;
  }


  /** 
   * <em>long_cash_margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongCashMarginDepositRateIsModified() {
    return long_cash_margin_deposit_rate_is_modified;
  }


  /** 
   * <em>short_cash_margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getShortCashMarginDepositRate()
  {
    return ( short_cash_margin_deposit_rate==null? ((double)0): short_cash_margin_deposit_rate.doubleValue() );
  }


  /** 
   * <em>short_cash_margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getShortCashMarginDepositRateIsNull()
  {
    return short_cash_margin_deposit_rate == null;
  }


  /** 
   * <em>short_cash_margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortCashMarginDepositRateIsSet() {
    return short_cash_margin_deposit_rate_is_set;
  }


  /** 
   * <em>short_cash_margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortCashMarginDepositRateIsModified() {
    return short_cash_margin_deposit_rate_is_modified;
  }


  /** 
   * <em>last_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLastClosingPrice()
  {
    return last_closing_price;
  }


  /** 
   * <em>last_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastClosingPriceIsSet() {
    return last_closing_price_is_set;
  }


  /** 
   * <em>last_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastClosingPriceIsModified() {
    return last_closing_price_is_modified;
  }


  /** 
   * <em>price_range_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPriceRangeType()
  {
    return price_range_type;
  }


  /** 
   * <em>price_range_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceRangeTypeIsSet() {
    return price_range_type_is_set;
  }


  /** 
   * <em>price_range_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceRangeTypeIsModified() {
    return price_range_type_is_modified;
  }


  /** 
   * <em>price_range_unit_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPriceRangeUnitType()
  {
    return price_range_unit_type;
  }


  /** 
   * <em>price_range_unit_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceRangeUnitTypeIsSet() {
    return price_range_unit_type_is_set;
  }


  /** 
   * <em>price_range_unit_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceRangeUnitTypeIsModified() {
    return price_range_unit_type_is_modified;
  }


  /** 
   * <em>high_compulsive_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getHighCompulsivePriceRange()
  {
    return ( high_compulsive_price_range==null? ((double)0): high_compulsive_price_range.doubleValue() );
  }


  /** 
   * <em>high_compulsive_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getHighCompulsivePriceRangeIsNull()
  {
    return high_compulsive_price_range == null;
  }


  /** 
   * <em>high_compulsive_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHighCompulsivePriceRangeIsSet() {
    return high_compulsive_price_range_is_set;
  }


  /** 
   * <em>high_compulsive_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHighCompulsivePriceRangeIsModified() {
    return high_compulsive_price_range_is_modified;
  }


  /** 
   * <em>low_compulsive_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLowCompulsivePriceRange()
  {
    return ( low_compulsive_price_range==null? ((double)0): low_compulsive_price_range.doubleValue() );
  }


  /** 
   * <em>low_compulsive_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLowCompulsivePriceRangeIsNull()
  {
    return low_compulsive_price_range == null;
  }


  /** 
   * <em>low_compulsive_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLowCompulsivePriceRangeIsSet() {
    return low_compulsive_price_range_is_set;
  }


  /** 
   * <em>low_compulsive_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLowCompulsivePriceRangeIsModified() {
    return low_compulsive_price_range_is_modified;
  }


  /** 
   * <em>stop_high_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getStopHighPrice()
  {
    return ( stop_high_price==null? ((double)0): stop_high_price.doubleValue() );
  }


  /** 
   * <em>stop_high_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getStopHighPriceIsNull()
  {
    return stop_high_price == null;
  }


  /** 
   * <em>stop_high_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopHighPriceIsSet() {
    return stop_high_price_is_set;
  }


  /** 
   * <em>stop_high_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopHighPriceIsModified() {
    return stop_high_price_is_modified;
  }


  /** 
   * <em>stop_low_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getStopLowPrice()
  {
    return ( stop_low_price==null? ((double)0): stop_low_price.doubleValue() );
  }


  /** 
   * <em>stop_low_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getStopLowPriceIsNull()
  {
    return stop_low_price == null;
  }


  /** 
   * <em>stop_low_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopLowPriceIsSet() {
    return stop_low_price_is_set;
  }


  /** 
   * <em>stop_low_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopLowPriceIsModified() {
    return stop_low_price_is_modified;
  }


  /** 
   * <em>price_range_ratio</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPriceRangeRatio()
  {
    return ( price_range_ratio==null? ((double)0): price_range_ratio.doubleValue() );
  }


  /** 
   * <em>price_range_ratio</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPriceRangeRatioIsNull()
  {
    return price_range_ratio == null;
  }


  /** 
   * <em>price_range_ratio</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceRangeRatioIsSet() {
    return price_range_ratio_is_set;
  }


  /** 
   * <em>price_range_ratio</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceRangeRatioIsModified() {
    return price_range_ratio_is_modified;
  }


  /** 
   * <em>compulsive_limited_unit</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getCompulsiveLimitedUnit()
  {
    return ( compulsive_limited_unit==null? ((int)0): compulsive_limited_unit.intValue() );
  }


  /** 
   * <em>compulsive_limited_unit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCompulsiveLimitedUnitIsNull()
  {
    return compulsive_limited_unit == null;
  }


  /** 
   * <em>compulsive_limited_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompulsiveLimitedUnitIsSet() {
    return compulsive_limited_unit_is_set;
  }


  /** 
   * <em>compulsive_limited_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompulsiveLimitedUnitIsModified() {
    return compulsive_limited_unit_is_modified;
  }


  /** 
   * <em>mini_stock_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMiniStockFlag()
  {
    return mini_stock_flag;
  }


  /** 
   * <em>mini_stock_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockFlagIsSet() {
    return mini_stock_flag_is_set;
  }


  /** 
   * <em>mini_stock_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockFlagIsModified() {
    return mini_stock_flag_is_modified;
  }


  /** 
   * <em>today_dep_fund_reg</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getTodayDepFundReg()
  {
    return today_dep_fund_reg;
  }


  /** 
   * <em>today_dep_fund_reg</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRegIsSet() {
    return today_dep_fund_reg_is_set;
  }


  /** 
   * <em>today_dep_fund_reg</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRegIsModified() {
    return today_dep_fund_reg_is_modified;
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
   * <em>base_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBasePrice()
  {
    return base_price;
  }


  /** 
   * <em>base_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBasePriceIsSet() {
    return base_price_is_set;
  }


  /** 
   * <em>base_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBasePriceIsModified() {
    return base_price_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new EqtypeTradedProductPK(traded_product_id);
  }


  /** 
   * <em>traded_product_id</em>カラムの値を設定します。 
   *
   * @@param p_tradedProductId <em>traded_product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setTradedProductId( long p_tradedProductId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    traded_product_id = p_tradedProductId;
    traded_product_id_is_set = true;
    traded_product_id_is_modified = true;
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
   * <em>market_id</em>カラムの値を設定します。 
   *
   * @@param p_marketId <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * <em>valid_until_biz_date</em>カラムの値を設定します。 
   *
   * @@param p_validUntilBizDate <em>valid_until_biz_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setValidUntilBizDate( String p_validUntilBizDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    valid_until_biz_date = p_validUntilBizDate;
    valid_until_biz_date_is_set = true;
    valid_until_biz_date_is_modified = true;
  }


  /** 
   * <em>list_flag</em>カラムの値を設定します。 
   *
   * @@param p_listFlag <em>list_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setListFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_listFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    list_flag = p_listFlag;
    list_flag_is_set = true;
    list_flag_is_modified = true;
  }


  /** 
   * <em>list_type</em>カラムの値を設定します。 
   *
   * @@param p_listType <em>list_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setListType( String p_listType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    list_type = p_listType;
    list_type_is_set = true;
    list_type_is_modified = true;
  }


  /** 
   * <em>new_list_type</em>カラムの値を設定します。 
   *
   * @@param p_newListType <em>new_list_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setNewListType( String p_newListType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_list_type = p_newListType;
    new_list_type_is_set = true;
    new_list_type_is_modified = true;
  }


  /** 
   * <em>listed_date</em>カラムの値を設定します。 
   *
   * @@param p_listedDate <em>listed_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setListedDate( java.sql.Timestamp p_listedDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    listed_date = p_listedDate;
    listed_date_is_set = true;
    listed_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>listed_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setListedDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    listed_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    listed_date_is_set = true;
    listed_date_is_modified = true;
  }


  /** 
   * <em>unlisted_date</em>カラムの値を設定します。 
   *
   * @@param p_unlistedDate <em>unlisted_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setUnlistedDate( java.sql.Timestamp p_unlistedDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unlisted_date = p_unlistedDate;
    unlisted_date_is_set = true;
    unlisted_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>unlisted_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setUnlistedDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unlisted_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    unlisted_date_is_set = true;
    unlisted_date_is_modified = true;
  }


  /** 
   * <em>marginable_flag</em>カラムの値を設定します。 
   *
   * @@param p_marginableFlag <em>marginable_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setMarginableFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_marginableFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    marginable_flag = p_marginableFlag;
    marginable_flag_is_set = true;
    marginable_flag_is_modified = true;
  }


  /** 
   * <em>shortable_flag</em>カラムの値を設定します。 
   *
   * @@param p_shortableFlag <em>shortable_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setShortableFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_shortableFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    shortable_flag = p_shortableFlag;
    shortable_flag_is_set = true;
    shortable_flag_is_modified = true;
  }


  /** 
   * <em>open_otc_div</em>カラムの値を設定します。 
   *
   * @@param p_openOtcDiv <em>open_otc_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOpenOtcDiv( String p_openOtcDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_otc_div = p_openOtcDiv;
    open_otc_div_is_set = true;
    open_otc_div_is_modified = true;
  }


  /** 
   * <em>margin_sys_product_type</em>カラムの値を設定します。 
   *
   * @@param p_marginSysProductType <em>margin_sys_product_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginSysProductType( String p_marginSysProductType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sys_product_type = p_marginSysProductType;
    margin_sys_product_type_is_set = true;
    margin_sys_product_type_is_modified = true;
  }


  /** 
   * <em>margin_gen_product_type</em>カラムの値を設定します。 
   *
   * @@param p_marginGenProductType <em>margin_gen_product_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginGenProductType( String p_marginGenProductType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_gen_product_type = p_marginGenProductType;
    margin_gen_product_type_is_set = true;
    margin_gen_product_type_is_modified = true;
  }


  /** 
   * <em>mini_stock_can_dealt</em>カラムの値を設定します。 
   *
   * @@param p_miniStockCanDealt <em>mini_stock_can_dealt</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setMiniStockCanDealt( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_miniStockCanDealt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_can_dealt = p_miniStockCanDealt;
    mini_stock_can_dealt_is_set = true;
    mini_stock_can_dealt_is_modified = true;
  }


  /** 
   * <em>buy_cash_stop</em>カラムの値を設定します。 
   *
   * @@param p_buyCashStop <em>buy_cash_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setBuyCashStop( int p_buyCashStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_cash_stop = new Integer(p_buyCashStop);
    buy_cash_stop_is_set = true;
    buy_cash_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_cash_stop</em>カラムに値を設定します。 
   */
  public final void setBuyCashStop( Integer p_buyCashStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_cash_stop = p_buyCashStop;
    buy_cash_stop_is_set = true;
    buy_cash_stop_is_modified = true;
  }


  /** 
   * <em>sell_cash_stop</em>カラムの値を設定します。 
   *
   * @@param p_sellCashStop <em>sell_cash_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setSellCashStop( int p_sellCashStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_cash_stop = new Integer(p_sellCashStop);
    sell_cash_stop_is_set = true;
    sell_cash_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_cash_stop</em>カラムに値を設定します。 
   */
  public final void setSellCashStop( Integer p_sellCashStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_cash_stop = p_sellCashStop;
    sell_cash_stop_is_set = true;
    sell_cash_stop_is_modified = true;
  }


  /** 
   * <em>buy_spot_market_ord_des_stop</em>カラムの値を設定します。 
   *
   * @@param p_buySpotMarketOrdDesStop <em>buy_spot_market_ord_des_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setBuySpotMarketOrdDesStop( int p_buySpotMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_spot_market_ord_des_stop = new Integer(p_buySpotMarketOrdDesStop);
    buy_spot_market_ord_des_stop_is_set = true;
    buy_spot_market_ord_des_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_spot_market_ord_des_stop</em>カラムに値を設定します。 
   */
  public final void setBuySpotMarketOrdDesStop( Integer p_buySpotMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_spot_market_ord_des_stop = p_buySpotMarketOrdDesStop;
    buy_spot_market_ord_des_stop_is_set = true;
    buy_spot_market_ord_des_stop_is_modified = true;
  }


  /** 
   * <em>sell_spot_market_ord_des_stop</em>カラムの値を設定します。 
   *
   * @@param p_sellSpotMarketOrdDesStop <em>sell_spot_market_ord_des_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setSellSpotMarketOrdDesStop( int p_sellSpotMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_spot_market_ord_des_stop = new Integer(p_sellSpotMarketOrdDesStop);
    sell_spot_market_ord_des_stop_is_set = true;
    sell_spot_market_ord_des_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_spot_market_ord_des_stop</em>カラムに値を設定します。 
   */
  public final void setSellSpotMarketOrdDesStop( Integer p_sellSpotMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_spot_market_ord_des_stop = p_sellSpotMarketOrdDesStop;
    sell_spot_market_ord_des_stop_is_set = true;
    sell_spot_market_ord_des_stop_is_modified = true;
  }


  /** 
   * <em>long_margin_sys_stop</em>カラムの値を設定します。 
   *
   * @@param p_longMarginSysStop <em>long_margin_sys_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setLongMarginSysStop( int p_longMarginSysStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_margin_sys_stop = new Integer(p_longMarginSysStop);
    long_margin_sys_stop_is_set = true;
    long_margin_sys_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>long_margin_sys_stop</em>カラムに値を設定します。 
   */
  public final void setLongMarginSysStop( Integer p_longMarginSysStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    long_margin_sys_stop = p_longMarginSysStop;
    long_margin_sys_stop_is_set = true;
    long_margin_sys_stop_is_modified = true;
  }


  /** 
   * <em>short_margin_sys_stop</em>カラムの値を設定します。 
   *
   * @@param p_shortMarginSysStop <em>short_margin_sys_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setShortMarginSysStop( int p_shortMarginSysStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_margin_sys_stop = new Integer(p_shortMarginSysStop);
    short_margin_sys_stop_is_set = true;
    short_margin_sys_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_margin_sys_stop</em>カラムに値を設定します。 
   */
  public final void setShortMarginSysStop( Integer p_shortMarginSysStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_margin_sys_stop = p_shortMarginSysStop;
    short_margin_sys_stop_is_set = true;
    short_margin_sys_stop_is_modified = true;
  }


  /** 
   * <em>long_ms_market_ord_des_stop</em>カラムの値を設定します。 
   *
   * @@param p_longMsMarketOrdDesStop <em>long_ms_market_ord_des_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setLongMsMarketOrdDesStop( int p_longMsMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_ms_market_ord_des_stop = new Integer(p_longMsMarketOrdDesStop);
    long_ms_market_ord_des_stop_is_set = true;
    long_ms_market_ord_des_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>long_ms_market_ord_des_stop</em>カラムに値を設定します。 
   */
  public final void setLongMsMarketOrdDesStop( Integer p_longMsMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    long_ms_market_ord_des_stop = p_longMsMarketOrdDesStop;
    long_ms_market_ord_des_stop_is_set = true;
    long_ms_market_ord_des_stop_is_modified = true;
  }


  /** 
   * <em>short_ms_market_ord_des_stop</em>カラムの値を設定します。 
   *
   * @@param p_shortMsMarketOrdDesStop <em>short_ms_market_ord_des_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setShortMsMarketOrdDesStop( int p_shortMsMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_ms_market_ord_des_stop = new Integer(p_shortMsMarketOrdDesStop);
    short_ms_market_ord_des_stop_is_set = true;
    short_ms_market_ord_des_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_ms_market_ord_des_stop</em>カラムに値を設定します。 
   */
  public final void setShortMsMarketOrdDesStop( Integer p_shortMsMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_ms_market_ord_des_stop = p_shortMsMarketOrdDesStop;
    short_ms_market_ord_des_stop_is_set = true;
    short_ms_market_ord_des_stop_is_modified = true;
  }


  /** 
   * <em>long_margin_gen_stop</em>カラムの値を設定します。 
   *
   * @@param p_longMarginGenStop <em>long_margin_gen_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setLongMarginGenStop( int p_longMarginGenStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_margin_gen_stop = new Integer(p_longMarginGenStop);
    long_margin_gen_stop_is_set = true;
    long_margin_gen_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>long_margin_gen_stop</em>カラムに値を設定します。 
   */
  public final void setLongMarginGenStop( Integer p_longMarginGenStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    long_margin_gen_stop = p_longMarginGenStop;
    long_margin_gen_stop_is_set = true;
    long_margin_gen_stop_is_modified = true;
  }


  /** 
   * <em>short_margin_gen_stop</em>カラムの値を設定します。 
   *
   * @@param p_shortMarginGenStop <em>short_margin_gen_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setShortMarginGenStop( int p_shortMarginGenStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_margin_gen_stop = new Integer(p_shortMarginGenStop);
    short_margin_gen_stop_is_set = true;
    short_margin_gen_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_margin_gen_stop</em>カラムに値を設定します。 
   */
  public final void setShortMarginGenStop( Integer p_shortMarginGenStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_margin_gen_stop = p_shortMarginGenStop;
    short_margin_gen_stop_is_set = true;
    short_margin_gen_stop_is_modified = true;
  }


  /** 
   * <em>long_mg_market_ord_des_stop</em>カラムの値を設定します。 
   *
   * @@param p_longMgMarketOrdDesStop <em>long_mg_market_ord_des_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setLongMgMarketOrdDesStop( int p_longMgMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_mg_market_ord_des_stop = new Integer(p_longMgMarketOrdDesStop);
    long_mg_market_ord_des_stop_is_set = true;
    long_mg_market_ord_des_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>long_mg_market_ord_des_stop</em>カラムに値を設定します。 
   */
  public final void setLongMgMarketOrdDesStop( Integer p_longMgMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    long_mg_market_ord_des_stop = p_longMgMarketOrdDesStop;
    long_mg_market_ord_des_stop_is_set = true;
    long_mg_market_ord_des_stop_is_modified = true;
  }


  /** 
   * <em>short_mg_market_ord_des_stop</em>カラムの値を設定します。 
   *
   * @@param p_shortMgMarketOrdDesStop <em>short_mg_market_ord_des_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setShortMgMarketOrdDesStop( int p_shortMgMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_mg_market_ord_des_stop = new Integer(p_shortMgMarketOrdDesStop);
    short_mg_market_ord_des_stop_is_set = true;
    short_mg_market_ord_des_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_mg_market_ord_des_stop</em>カラムに値を設定します。 
   */
  public final void setShortMgMarketOrdDesStop( Integer p_shortMgMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_mg_market_ord_des_stop = p_shortMgMarketOrdDesStop;
    short_mg_market_ord_des_stop_is_set = true;
    short_mg_market_ord_des_stop_is_modified = true;
  }


  /** 
   * <em>long_close_margin_sys_stop</em>カラムの値を設定します。 
   *
   * @@param p_longCloseMarginSysStop <em>long_close_margin_sys_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setLongCloseMarginSysStop( int p_longCloseMarginSysStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_close_margin_sys_stop = new Integer(p_longCloseMarginSysStop);
    long_close_margin_sys_stop_is_set = true;
    long_close_margin_sys_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>long_close_margin_sys_stop</em>カラムに値を設定します。 
   */
  public final void setLongCloseMarginSysStop( Integer p_longCloseMarginSysStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    long_close_margin_sys_stop = p_longCloseMarginSysStop;
    long_close_margin_sys_stop_is_set = true;
    long_close_margin_sys_stop_is_modified = true;
  }


  /** 
   * <em>short_close_margin_sys_stop</em>カラムの値を設定します。 
   *
   * @@param p_shortCloseMarginSysStop <em>short_close_margin_sys_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setShortCloseMarginSysStop( int p_shortCloseMarginSysStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_close_margin_sys_stop = new Integer(p_shortCloseMarginSysStop);
    short_close_margin_sys_stop_is_set = true;
    short_close_margin_sys_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_close_margin_sys_stop</em>カラムに値を設定します。 
   */
  public final void setShortCloseMarginSysStop( Integer p_shortCloseMarginSysStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_close_margin_sys_stop = p_shortCloseMarginSysStop;
    short_close_margin_sys_stop_is_set = true;
    short_close_margin_sys_stop_is_modified = true;
  }


  /** 
   * <em>long_cms_market_ord_des_stop</em>カラムの値を設定します。 
   *
   * @@param p_longCmsMarketOrdDesStop <em>long_cms_market_ord_des_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setLongCmsMarketOrdDesStop( int p_longCmsMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_cms_market_ord_des_stop = new Integer(p_longCmsMarketOrdDesStop);
    long_cms_market_ord_des_stop_is_set = true;
    long_cms_market_ord_des_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>long_cms_market_ord_des_stop</em>カラムに値を設定します。 
   */
  public final void setLongCmsMarketOrdDesStop( Integer p_longCmsMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    long_cms_market_ord_des_stop = p_longCmsMarketOrdDesStop;
    long_cms_market_ord_des_stop_is_set = true;
    long_cms_market_ord_des_stop_is_modified = true;
  }


  /** 
   * <em>short_cms_market_ord_des_stop</em>カラムの値を設定します。 
   *
   * @@param p_shortCmsMarketOrdDesStop <em>short_cms_market_ord_des_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setShortCmsMarketOrdDesStop( int p_shortCmsMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_cms_market_ord_des_stop = new Integer(p_shortCmsMarketOrdDesStop);
    short_cms_market_ord_des_stop_is_set = true;
    short_cms_market_ord_des_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_cms_market_ord_des_stop</em>カラムに値を設定します。 
   */
  public final void setShortCmsMarketOrdDesStop( Integer p_shortCmsMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_cms_market_ord_des_stop = p_shortCmsMarketOrdDesStop;
    short_cms_market_ord_des_stop_is_set = true;
    short_cms_market_ord_des_stop_is_modified = true;
  }


  /** 
   * <em>long_close_margin_gen_stop</em>カラムの値を設定します。 
   *
   * @@param p_longCloseMarginGenStop <em>long_close_margin_gen_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setLongCloseMarginGenStop( int p_longCloseMarginGenStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_close_margin_gen_stop = new Integer(p_longCloseMarginGenStop);
    long_close_margin_gen_stop_is_set = true;
    long_close_margin_gen_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>long_close_margin_gen_stop</em>カラムに値を設定します。 
   */
  public final void setLongCloseMarginGenStop( Integer p_longCloseMarginGenStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    long_close_margin_gen_stop = p_longCloseMarginGenStop;
    long_close_margin_gen_stop_is_set = true;
    long_close_margin_gen_stop_is_modified = true;
  }


  /** 
   * <em>short_close_margin_gen_stop</em>カラムの値を設定します。 
   *
   * @@param p_shortCloseMarginGenStop <em>short_close_margin_gen_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setShortCloseMarginGenStop( int p_shortCloseMarginGenStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_close_margin_gen_stop = new Integer(p_shortCloseMarginGenStop);
    short_close_margin_gen_stop_is_set = true;
    short_close_margin_gen_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_close_margin_gen_stop</em>カラムに値を設定します。 
   */
  public final void setShortCloseMarginGenStop( Integer p_shortCloseMarginGenStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_close_margin_gen_stop = p_shortCloseMarginGenStop;
    short_close_margin_gen_stop_is_set = true;
    short_close_margin_gen_stop_is_modified = true;
  }


  /** 
   * <em>long_cmg_market_ord_des_stop</em>カラムの値を設定します。 
   *
   * @@param p_longCmgMarketOrdDesStop <em>long_cmg_market_ord_des_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setLongCmgMarketOrdDesStop( int p_longCmgMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_cmg_market_ord_des_stop = new Integer(p_longCmgMarketOrdDesStop);
    long_cmg_market_ord_des_stop_is_set = true;
    long_cmg_market_ord_des_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>long_cmg_market_ord_des_stop</em>カラムに値を設定します。 
   */
  public final void setLongCmgMarketOrdDesStop( Integer p_longCmgMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    long_cmg_market_ord_des_stop = p_longCmgMarketOrdDesStop;
    long_cmg_market_ord_des_stop_is_set = true;
    long_cmg_market_ord_des_stop_is_modified = true;
  }


  /** 
   * <em>short_cmg_market_ord_des_stop</em>カラムの値を設定します。 
   *
   * @@param p_shortCmgMarketOrdDesStop <em>short_cmg_market_ord_des_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setShortCmgMarketOrdDesStop( int p_shortCmgMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_cmg_market_ord_des_stop = new Integer(p_shortCmgMarketOrdDesStop);
    short_cmg_market_ord_des_stop_is_set = true;
    short_cmg_market_ord_des_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_cmg_market_ord_des_stop</em>カラムに値を設定します。 
   */
  public final void setShortCmgMarketOrdDesStop( Integer p_shortCmgMarketOrdDesStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_cmg_market_ord_des_stop = p_shortCmgMarketOrdDesStop;
    short_cmg_market_ord_des_stop_is_set = true;
    short_cmg_market_ord_des_stop_is_modified = true;
  }


  /** 
   * <em>long_swap_margin_sys_stop</em>カラムの値を設定します。 
   *
   * @@param p_longSwapMarginSysStop <em>long_swap_margin_sys_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setLongSwapMarginSysStop( int p_longSwapMarginSysStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_swap_margin_sys_stop = new Integer(p_longSwapMarginSysStop);
    long_swap_margin_sys_stop_is_set = true;
    long_swap_margin_sys_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>long_swap_margin_sys_stop</em>カラムに値を設定します。 
   */
  public final void setLongSwapMarginSysStop( Integer p_longSwapMarginSysStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    long_swap_margin_sys_stop = p_longSwapMarginSysStop;
    long_swap_margin_sys_stop_is_set = true;
    long_swap_margin_sys_stop_is_modified = true;
  }


  /** 
   * <em>short_swap_margin_sys_stop</em>カラムの値を設定します。 
   *
   * @@param p_shortSwapMarginSysStop <em>short_swap_margin_sys_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setShortSwapMarginSysStop( int p_shortSwapMarginSysStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_swap_margin_sys_stop = new Integer(p_shortSwapMarginSysStop);
    short_swap_margin_sys_stop_is_set = true;
    short_swap_margin_sys_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_swap_margin_sys_stop</em>カラムに値を設定します。 
   */
  public final void setShortSwapMarginSysStop( Integer p_shortSwapMarginSysStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_swap_margin_sys_stop = p_shortSwapMarginSysStop;
    short_swap_margin_sys_stop_is_set = true;
    short_swap_margin_sys_stop_is_modified = true;
  }


  /** 
   * <em>long_swap_margin_gen_stop</em>カラムの値を設定します。 
   *
   * @@param p_longSwapMarginGenStop <em>long_swap_margin_gen_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setLongSwapMarginGenStop( int p_longSwapMarginGenStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_swap_margin_gen_stop = new Integer(p_longSwapMarginGenStop);
    long_swap_margin_gen_stop_is_set = true;
    long_swap_margin_gen_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>long_swap_margin_gen_stop</em>カラムに値を設定します。 
   */
  public final void setLongSwapMarginGenStop( Integer p_longSwapMarginGenStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    long_swap_margin_gen_stop = p_longSwapMarginGenStop;
    long_swap_margin_gen_stop_is_set = true;
    long_swap_margin_gen_stop_is_modified = true;
  }


  /** 
   * <em>short_swap_margin_gen_stop</em>カラムの値を設定します。 
   *
   * @@param p_shortSwapMarginGenStop <em>short_swap_margin_gen_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setShortSwapMarginGenStop( int p_shortSwapMarginGenStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_swap_margin_gen_stop = new Integer(p_shortSwapMarginGenStop);
    short_swap_margin_gen_stop_is_set = true;
    short_swap_margin_gen_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_swap_margin_gen_stop</em>カラムに値を設定します。 
   */
  public final void setShortSwapMarginGenStop( Integer p_shortSwapMarginGenStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_swap_margin_gen_stop = p_shortSwapMarginGenStop;
    short_swap_margin_gen_stop_is_set = true;
    short_swap_margin_gen_stop_is_modified = true;
  }


  /** 
   * <em>buy_mini_stock_stop</em>カラムの値を設定します。 
   *
   * @@param p_buyMiniStockStop <em>buy_mini_stock_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setBuyMiniStockStop( int p_buyMiniStockStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_mini_stock_stop = new Integer(p_buyMiniStockStop);
    buy_mini_stock_stop_is_set = true;
    buy_mini_stock_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_mini_stock_stop</em>カラムに値を設定します。 
   */
  public final void setBuyMiniStockStop( Integer p_buyMiniStockStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_mini_stock_stop = p_buyMiniStockStop;
    buy_mini_stock_stop_is_set = true;
    buy_mini_stock_stop_is_modified = true;
  }


  /** 
   * <em>sell_mini_stock_stop</em>カラムの値を設定します。 
   *
   * @@param p_sellMiniStockStop <em>sell_mini_stock_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setSellMiniStockStop( int p_sellMiniStockStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_mini_stock_stop = new Integer(p_sellMiniStockStop);
    sell_mini_stock_stop_is_set = true;
    sell_mini_stock_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_mini_stock_stop</em>カラムに値を設定します。 
   */
  public final void setSellMiniStockStop( Integer p_sellMiniStockStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_mini_stock_stop = p_sellMiniStockStop;
    sell_mini_stock_stop_is_set = true;
    sell_mini_stock_stop_is_modified = true;
  }


  /** 
   * <em>lot_size</em>カラムの値を設定します。 
   *
   * @@param p_lotSize <em>lot_size</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLotSize( double p_lotSize )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    lot_size = p_lotSize;
    lot_size_is_set = true;
    lot_size_is_modified = true;
  }


  /** 
   * <em>long_margin_deposit_rate</em>カラムの値を設定します。 
   *
   * @@param p_longMarginDepositRate <em>long_margin_deposit_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLongMarginDepositRate( double p_longMarginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_margin_deposit_rate = new Double(p_longMarginDepositRate);
    long_margin_deposit_rate_is_set = true;
    long_margin_deposit_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>long_margin_deposit_rate</em>カラムに値を設定します。 
   */
  public final void setLongMarginDepositRate( Double p_longMarginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    long_margin_deposit_rate = p_longMarginDepositRate;
    long_margin_deposit_rate_is_set = true;
    long_margin_deposit_rate_is_modified = true;
  }


  /** 
   * <em>short_margin_deposit_rate</em>カラムの値を設定します。 
   *
   * @@param p_shortMarginDepositRate <em>short_margin_deposit_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setShortMarginDepositRate( double p_shortMarginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_margin_deposit_rate = new Double(p_shortMarginDepositRate);
    short_margin_deposit_rate_is_set = true;
    short_margin_deposit_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_margin_deposit_rate</em>カラムに値を設定します。 
   */
  public final void setShortMarginDepositRate( Double p_shortMarginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_margin_deposit_rate = p_shortMarginDepositRate;
    short_margin_deposit_rate_is_set = true;
    short_margin_deposit_rate_is_modified = true;
  }


  /** 
   * <em>long_cash_margin_deposit_rate</em>カラムの値を設定します。 
   *
   * @@param p_longCashMarginDepositRate <em>long_cash_margin_deposit_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLongCashMarginDepositRate( double p_longCashMarginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_cash_margin_deposit_rate = new Double(p_longCashMarginDepositRate);
    long_cash_margin_deposit_rate_is_set = true;
    long_cash_margin_deposit_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>long_cash_margin_deposit_rate</em>カラムに値を設定します。 
   */
  public final void setLongCashMarginDepositRate( Double p_longCashMarginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    long_cash_margin_deposit_rate = p_longCashMarginDepositRate;
    long_cash_margin_deposit_rate_is_set = true;
    long_cash_margin_deposit_rate_is_modified = true;
  }


  /** 
   * <em>short_cash_margin_deposit_rate</em>カラムの値を設定します。 
   *
   * @@param p_shortCashMarginDepositRate <em>short_cash_margin_deposit_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setShortCashMarginDepositRate( double p_shortCashMarginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_cash_margin_deposit_rate = new Double(p_shortCashMarginDepositRate);
    short_cash_margin_deposit_rate_is_set = true;
    short_cash_margin_deposit_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>short_cash_margin_deposit_rate</em>カラムに値を設定します。 
   */
  public final void setShortCashMarginDepositRate( Double p_shortCashMarginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    short_cash_margin_deposit_rate = p_shortCashMarginDepositRate;
    short_cash_margin_deposit_rate_is_set = true;
    short_cash_margin_deposit_rate_is_modified = true;
  }


  /** 
   * <em>last_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_lastClosingPrice <em>last_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLastClosingPrice( double p_lastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_closing_price = p_lastClosingPrice;
    last_closing_price_is_set = true;
    last_closing_price_is_modified = true;
  }


  /** 
   * <em>price_range_type</em>カラムの値を設定します。 
   *
   * @@param p_priceRangeType <em>price_range_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPriceRangeType( String p_priceRangeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price_range_type = p_priceRangeType;
    price_range_type_is_set = true;
    price_range_type_is_modified = true;
  }


  /** 
   * <em>price_range_unit_type</em>カラムの値を設定します。 
   *
   * @@param p_priceRangeUnitType <em>price_range_unit_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPriceRangeUnitType( String p_priceRangeUnitType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price_range_unit_type = p_priceRangeUnitType;
    price_range_unit_type_is_set = true;
    price_range_unit_type_is_modified = true;
  }


  /** 
   * <em>high_compulsive_price_range</em>カラムの値を設定します。 
   *
   * @@param p_highCompulsivePriceRange <em>high_compulsive_price_range</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setHighCompulsivePriceRange( double p_highCompulsivePriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    high_compulsive_price_range = new Double(p_highCompulsivePriceRange);
    high_compulsive_price_range_is_set = true;
    high_compulsive_price_range_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>high_compulsive_price_range</em>カラムに値を設定します。 
   */
  public final void setHighCompulsivePriceRange( Double p_highCompulsivePriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    high_compulsive_price_range = p_highCompulsivePriceRange;
    high_compulsive_price_range_is_set = true;
    high_compulsive_price_range_is_modified = true;
  }


  /** 
   * <em>low_compulsive_price_range</em>カラムの値を設定します。 
   *
   * @@param p_lowCompulsivePriceRange <em>low_compulsive_price_range</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLowCompulsivePriceRange( double p_lowCompulsivePriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    low_compulsive_price_range = new Double(p_lowCompulsivePriceRange);
    low_compulsive_price_range_is_set = true;
    low_compulsive_price_range_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>low_compulsive_price_range</em>カラムに値を設定します。 
   */
  public final void setLowCompulsivePriceRange( Double p_lowCompulsivePriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    low_compulsive_price_range = p_lowCompulsivePriceRange;
    low_compulsive_price_range_is_set = true;
    low_compulsive_price_range_is_modified = true;
  }


  /** 
   * <em>stop_high_price</em>カラムの値を設定します。 
   *
   * @@param p_stopHighPrice <em>stop_high_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setStopHighPrice( double p_stopHighPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_high_price = new Double(p_stopHighPrice);
    stop_high_price_is_set = true;
    stop_high_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>stop_high_price</em>カラムに値を設定します。 
   */
  public final void setStopHighPrice( Double p_stopHighPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    stop_high_price = p_stopHighPrice;
    stop_high_price_is_set = true;
    stop_high_price_is_modified = true;
  }


  /** 
   * <em>stop_low_price</em>カラムの値を設定します。 
   *
   * @@param p_stopLowPrice <em>stop_low_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setStopLowPrice( double p_stopLowPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_low_price = new Double(p_stopLowPrice);
    stop_low_price_is_set = true;
    stop_low_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>stop_low_price</em>カラムに値を設定します。 
   */
  public final void setStopLowPrice( Double p_stopLowPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    stop_low_price = p_stopLowPrice;
    stop_low_price_is_set = true;
    stop_low_price_is_modified = true;
  }


  /** 
   * <em>price_range_ratio</em>カラムの値を設定します。 
   *
   * @@param p_priceRangeRatio <em>price_range_ratio</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPriceRangeRatio( double p_priceRangeRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price_range_ratio = new Double(p_priceRangeRatio);
    price_range_ratio_is_set = true;
    price_range_ratio_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>price_range_ratio</em>カラムに値を設定します。 
   */
  public final void setPriceRangeRatio( Double p_priceRangeRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    price_range_ratio = p_priceRangeRatio;
    price_range_ratio_is_set = true;
    price_range_ratio_is_modified = true;
  }


  /** 
   * <em>compulsive_limited_unit</em>カラムの値を設定します。 
   *
   * @@param p_compulsiveLimitedUnit <em>compulsive_limited_unit</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setCompulsiveLimitedUnit( int p_compulsiveLimitedUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    compulsive_limited_unit = new Integer(p_compulsiveLimitedUnit);
    compulsive_limited_unit_is_set = true;
    compulsive_limited_unit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>compulsive_limited_unit</em>カラムに値を設定します。 
   */
  public final void setCompulsiveLimitedUnit( Integer p_compulsiveLimitedUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    compulsive_limited_unit = p_compulsiveLimitedUnit;
    compulsive_limited_unit_is_set = true;
    compulsive_limited_unit_is_modified = true;
  }


  /** 
   * <em>mini_stock_flag</em>カラムの値を設定します。 
   *
   * @@param p_miniStockFlag <em>mini_stock_flag</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setMiniStockFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_miniStockFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_flag = p_miniStockFlag;
    mini_stock_flag_is_set = true;
    mini_stock_flag_is_modified = true;
  }


  /** 
   * <em>today_dep_fund_reg</em>カラムの値を設定します。 
   *
   * @@param p_todayDepFundReg <em>today_dep_fund_reg</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setTodayDepFundReg( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_todayDepFundReg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_dep_fund_reg = p_todayDepFundReg;
    today_dep_fund_reg_is_set = true;
    today_dep_fund_reg_is_modified = true;
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
   * <em>base_price</em>カラムの値を設定します。 
   *
   * @@param p_basePrice <em>base_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBasePrice( double p_basePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_price = p_basePrice;
    base_price_is_set = true;
    base_price_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'b':
                if ( name.equals("buy_cash_stop") ) {
                    return this.buy_cash_stop;
                }
                else if ( name.equals("buy_spot_market_ord_des_stop") ) {
                    return this.buy_spot_market_ord_des_stop;
                }
                else if ( name.equals("buy_mini_stock_stop") ) {
                    return this.buy_mini_stock_stop;
                }
                else if ( name.equals("base_price") ) {
                    return new Double( base_price );
                }
                break;
            case 'c':
                if ( name.equals("compulsive_limited_unit") ) {
                    return this.compulsive_limited_unit;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'h':
                if ( name.equals("high_compulsive_price_range") ) {
                    return this.high_compulsive_price_range;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("list_flag") ) {
                    return this.list_flag;
                }
                else if ( name.equals("list_type") ) {
                    return this.list_type;
                }
                else if ( name.equals("listed_date") ) {
                    return this.listed_date;
                }
                else if ( name.equals("long_margin_sys_stop") ) {
                    return this.long_margin_sys_stop;
                }
                else if ( name.equals("long_ms_market_ord_des_stop") ) {
                    return this.long_ms_market_ord_des_stop;
                }
                else if ( name.equals("long_margin_gen_stop") ) {
                    return this.long_margin_gen_stop;
                }
                else if ( name.equals("long_mg_market_ord_des_stop") ) {
                    return this.long_mg_market_ord_des_stop;
                }
                else if ( name.equals("long_close_margin_sys_stop") ) {
                    return this.long_close_margin_sys_stop;
                }
                else if ( name.equals("long_cms_market_ord_des_stop") ) {
                    return this.long_cms_market_ord_des_stop;
                }
                else if ( name.equals("long_close_margin_gen_stop") ) {
                    return this.long_close_margin_gen_stop;
                }
                else if ( name.equals("long_cmg_market_ord_des_stop") ) {
                    return this.long_cmg_market_ord_des_stop;
                }
                else if ( name.equals("long_swap_margin_sys_stop") ) {
                    return this.long_swap_margin_sys_stop;
                }
                else if ( name.equals("long_swap_margin_gen_stop") ) {
                    return this.long_swap_margin_gen_stop;
                }
                else if ( name.equals("lot_size") ) {
                    return new Double( lot_size );
                }
                else if ( name.equals("long_margin_deposit_rate") ) {
                    return this.long_margin_deposit_rate;
                }
                else if ( name.equals("long_cash_margin_deposit_rate") ) {
                    return this.long_cash_margin_deposit_rate;
                }
                else if ( name.equals("last_closing_price") ) {
                    return new Double( last_closing_price );
                }
                else if ( name.equals("low_compulsive_price_range") ) {
                    return this.low_compulsive_price_range;
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                else if ( name.equals("marginable_flag") ) {
                    return this.marginable_flag;
                }
                else if ( name.equals("margin_sys_product_type") ) {
                    return this.margin_sys_product_type;
                }
                else if ( name.equals("margin_gen_product_type") ) {
                    return this.margin_gen_product_type;
                }
                else if ( name.equals("mini_stock_can_dealt") ) {
                    return this.mini_stock_can_dealt;
                }
                else if ( name.equals("mini_stock_flag") ) {
                    return this.mini_stock_flag;
                }
                break;
            case 'n':
                if ( name.equals("new_list_type") ) {
                    return this.new_list_type;
                }
                break;
            case 'o':
                if ( name.equals("open_otc_div") ) {
                    return this.open_otc_div;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("price_range_type") ) {
                    return this.price_range_type;
                }
                else if ( name.equals("price_range_unit_type") ) {
                    return this.price_range_unit_type;
                }
                else if ( name.equals("price_range_ratio") ) {
                    return this.price_range_ratio;
                }
                break;
            case 's':
                if ( name.equals("shortable_flag") ) {
                    return this.shortable_flag;
                }
                else if ( name.equals("sell_cash_stop") ) {
                    return this.sell_cash_stop;
                }
                else if ( name.equals("sell_spot_market_ord_des_stop") ) {
                    return this.sell_spot_market_ord_des_stop;
                }
                else if ( name.equals("short_margin_sys_stop") ) {
                    return this.short_margin_sys_stop;
                }
                else if ( name.equals("short_ms_market_ord_des_stop") ) {
                    return this.short_ms_market_ord_des_stop;
                }
                else if ( name.equals("short_margin_gen_stop") ) {
                    return this.short_margin_gen_stop;
                }
                else if ( name.equals("short_mg_market_ord_des_stop") ) {
                    return this.short_mg_market_ord_des_stop;
                }
                else if ( name.equals("short_close_margin_sys_stop") ) {
                    return this.short_close_margin_sys_stop;
                }
                else if ( name.equals("short_cms_market_ord_des_stop") ) {
                    return this.short_cms_market_ord_des_stop;
                }
                else if ( name.equals("short_close_margin_gen_stop") ) {
                    return this.short_close_margin_gen_stop;
                }
                else if ( name.equals("short_cmg_market_ord_des_stop") ) {
                    return this.short_cmg_market_ord_des_stop;
                }
                else if ( name.equals("short_swap_margin_sys_stop") ) {
                    return this.short_swap_margin_sys_stop;
                }
                else if ( name.equals("short_swap_margin_gen_stop") ) {
                    return this.short_swap_margin_gen_stop;
                }
                else if ( name.equals("sell_mini_stock_stop") ) {
                    return this.sell_mini_stock_stop;
                }
                else if ( name.equals("short_margin_deposit_rate") ) {
                    return this.short_margin_deposit_rate;
                }
                else if ( name.equals("short_cash_margin_deposit_rate") ) {
                    return this.short_cash_margin_deposit_rate;
                }
                else if ( name.equals("stop_high_price") ) {
                    return this.stop_high_price;
                }
                else if ( name.equals("stop_low_price") ) {
                    return this.stop_low_price;
                }
                break;
            case 't':
                if ( name.equals("traded_product_id") ) {
                    return new Long( traded_product_id );
                }
                else if ( name.equals("today_dep_fund_reg") ) {
                    return this.today_dep_fund_reg;
                }
                break;
            case 'u':
                if ( name.equals("unlisted_date") ) {
                    return this.unlisted_date;
                }
                break;
            case 'v':
                if ( name.equals("valid_until_biz_date") ) {
                    return this.valid_until_biz_date;
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
            case 'b':
                if ( name.equals("buy_cash_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'buy_cash_stop' must be Integer: '"+value+"' is not." );
                    this.buy_cash_stop = (Integer) value;
                    if (this.buy_cash_stop_is_set)
                        this.buy_cash_stop_is_modified = true;
                    this.buy_cash_stop_is_set = true;
                    return;
                }
                else if ( name.equals("buy_spot_market_ord_des_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'buy_spot_market_ord_des_stop' must be Integer: '"+value+"' is not." );
                    this.buy_spot_market_ord_des_stop = (Integer) value;
                    if (this.buy_spot_market_ord_des_stop_is_set)
                        this.buy_spot_market_ord_des_stop_is_modified = true;
                    this.buy_spot_market_ord_des_stop_is_set = true;
                    return;
                }
                else if ( name.equals("buy_mini_stock_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'buy_mini_stock_stop' must be Integer: '"+value+"' is not." );
                    this.buy_mini_stock_stop = (Integer) value;
                    if (this.buy_mini_stock_stop_is_set)
                        this.buy_mini_stock_stop_is_modified = true;
                    this.buy_mini_stock_stop_is_set = true;
                    return;
                }
                else if ( name.equals("base_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'base_price' must be Double: '"+value+"' is not." );
                    this.base_price = ((Double) value).doubleValue();
                    if (this.base_price_is_set)
                        this.base_price_is_modified = true;
                    this.base_price_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("compulsive_limited_unit") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'compulsive_limited_unit' must be Integer: '"+value+"' is not." );
                    this.compulsive_limited_unit = (Integer) value;
                    if (this.compulsive_limited_unit_is_set)
                        this.compulsive_limited_unit_is_modified = true;
                    this.compulsive_limited_unit_is_set = true;
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
            case 'h':
                if ( name.equals("high_compulsive_price_range") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'high_compulsive_price_range' must be Double: '"+value+"' is not." );
                    this.high_compulsive_price_range = (Double) value;
                    if (this.high_compulsive_price_range_is_set)
                        this.high_compulsive_price_range_is_modified = true;
                    this.high_compulsive_price_range_is_set = true;
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
                break;
            case 'l':
                if ( name.equals("list_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'list_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.list_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.list_flag_is_set)
                        this.list_flag_is_modified = true;
                    this.list_flag_is_set = true;
                    return;
                }
                else if ( name.equals("list_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'list_type' must be String: '"+value+"' is not." );
                    this.list_type = (String) value;
                    if (this.list_type_is_set)
                        this.list_type_is_modified = true;
                    this.list_type_is_set = true;
                    return;
                }
                else if ( name.equals("listed_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'listed_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.listed_date = (java.sql.Timestamp) value;
                    if (this.listed_date_is_set)
                        this.listed_date_is_modified = true;
                    this.listed_date_is_set = true;
                    return;
                }
                else if ( name.equals("long_margin_sys_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'long_margin_sys_stop' must be Integer: '"+value+"' is not." );
                    this.long_margin_sys_stop = (Integer) value;
                    if (this.long_margin_sys_stop_is_set)
                        this.long_margin_sys_stop_is_modified = true;
                    this.long_margin_sys_stop_is_set = true;
                    return;
                }
                else if ( name.equals("long_ms_market_ord_des_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'long_ms_market_ord_des_stop' must be Integer: '"+value+"' is not." );
                    this.long_ms_market_ord_des_stop = (Integer) value;
                    if (this.long_ms_market_ord_des_stop_is_set)
                        this.long_ms_market_ord_des_stop_is_modified = true;
                    this.long_ms_market_ord_des_stop_is_set = true;
                    return;
                }
                else if ( name.equals("long_margin_gen_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'long_margin_gen_stop' must be Integer: '"+value+"' is not." );
                    this.long_margin_gen_stop = (Integer) value;
                    if (this.long_margin_gen_stop_is_set)
                        this.long_margin_gen_stop_is_modified = true;
                    this.long_margin_gen_stop_is_set = true;
                    return;
                }
                else if ( name.equals("long_mg_market_ord_des_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'long_mg_market_ord_des_stop' must be Integer: '"+value+"' is not." );
                    this.long_mg_market_ord_des_stop = (Integer) value;
                    if (this.long_mg_market_ord_des_stop_is_set)
                        this.long_mg_market_ord_des_stop_is_modified = true;
                    this.long_mg_market_ord_des_stop_is_set = true;
                    return;
                }
                else if ( name.equals("long_close_margin_sys_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'long_close_margin_sys_stop' must be Integer: '"+value+"' is not." );
                    this.long_close_margin_sys_stop = (Integer) value;
                    if (this.long_close_margin_sys_stop_is_set)
                        this.long_close_margin_sys_stop_is_modified = true;
                    this.long_close_margin_sys_stop_is_set = true;
                    return;
                }
                else if ( name.equals("long_cms_market_ord_des_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'long_cms_market_ord_des_stop' must be Integer: '"+value+"' is not." );
                    this.long_cms_market_ord_des_stop = (Integer) value;
                    if (this.long_cms_market_ord_des_stop_is_set)
                        this.long_cms_market_ord_des_stop_is_modified = true;
                    this.long_cms_market_ord_des_stop_is_set = true;
                    return;
                }
                else if ( name.equals("long_close_margin_gen_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'long_close_margin_gen_stop' must be Integer: '"+value+"' is not." );
                    this.long_close_margin_gen_stop = (Integer) value;
                    if (this.long_close_margin_gen_stop_is_set)
                        this.long_close_margin_gen_stop_is_modified = true;
                    this.long_close_margin_gen_stop_is_set = true;
                    return;
                }
                else if ( name.equals("long_cmg_market_ord_des_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'long_cmg_market_ord_des_stop' must be Integer: '"+value+"' is not." );
                    this.long_cmg_market_ord_des_stop = (Integer) value;
                    if (this.long_cmg_market_ord_des_stop_is_set)
                        this.long_cmg_market_ord_des_stop_is_modified = true;
                    this.long_cmg_market_ord_des_stop_is_set = true;
                    return;
                }
                else if ( name.equals("long_swap_margin_sys_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'long_swap_margin_sys_stop' must be Integer: '"+value+"' is not." );
                    this.long_swap_margin_sys_stop = (Integer) value;
                    if (this.long_swap_margin_sys_stop_is_set)
                        this.long_swap_margin_sys_stop_is_modified = true;
                    this.long_swap_margin_sys_stop_is_set = true;
                    return;
                }
                else if ( name.equals("long_swap_margin_gen_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'long_swap_margin_gen_stop' must be Integer: '"+value+"' is not." );
                    this.long_swap_margin_gen_stop = (Integer) value;
                    if (this.long_swap_margin_gen_stop_is_set)
                        this.long_swap_margin_gen_stop_is_modified = true;
                    this.long_swap_margin_gen_stop_is_set = true;
                    return;
                }
                else if ( name.equals("lot_size") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'lot_size' must be Double: '"+value+"' is not." );
                    this.lot_size = ((Double) value).doubleValue();
                    if (this.lot_size_is_set)
                        this.lot_size_is_modified = true;
                    this.lot_size_is_set = true;
                    return;
                }
                else if ( name.equals("long_margin_deposit_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'long_margin_deposit_rate' must be Double: '"+value+"' is not." );
                    this.long_margin_deposit_rate = (Double) value;
                    if (this.long_margin_deposit_rate_is_set)
                        this.long_margin_deposit_rate_is_modified = true;
                    this.long_margin_deposit_rate_is_set = true;
                    return;
                }
                else if ( name.equals("long_cash_margin_deposit_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'long_cash_margin_deposit_rate' must be Double: '"+value+"' is not." );
                    this.long_cash_margin_deposit_rate = (Double) value;
                    if (this.long_cash_margin_deposit_rate_is_set)
                        this.long_cash_margin_deposit_rate_is_modified = true;
                    this.long_cash_margin_deposit_rate_is_set = true;
                    return;
                }
                else if ( name.equals("last_closing_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'last_closing_price' must be Double: '"+value+"' is not." );
                    this.last_closing_price = ((Double) value).doubleValue();
                    if (this.last_closing_price_is_set)
                        this.last_closing_price_is_modified = true;
                    this.last_closing_price_is_set = true;
                    return;
                }
                else if ( name.equals("low_compulsive_price_range") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'low_compulsive_price_range' must be Double: '"+value+"' is not." );
                    this.low_compulsive_price_range = (Double) value;
                    if (this.low_compulsive_price_range_is_set)
                        this.low_compulsive_price_range_is_modified = true;
                    this.low_compulsive_price_range_is_set = true;
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
                if ( name.equals("market_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = ((Long) value).longValue();
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                else if ( name.equals("marginable_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'marginable_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.marginable_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.marginable_flag_is_set)
                        this.marginable_flag_is_modified = true;
                    this.marginable_flag_is_set = true;
                    return;
                }
                else if ( name.equals("margin_sys_product_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_sys_product_type' must be String: '"+value+"' is not." );
                    this.margin_sys_product_type = (String) value;
                    if (this.margin_sys_product_type_is_set)
                        this.margin_sys_product_type_is_modified = true;
                    this.margin_sys_product_type_is_set = true;
                    return;
                }
                else if ( name.equals("margin_gen_product_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_gen_product_type' must be String: '"+value+"' is not." );
                    this.margin_gen_product_type = (String) value;
                    if (this.margin_gen_product_type_is_set)
                        this.margin_gen_product_type_is_modified = true;
                    this.margin_gen_product_type_is_set = true;
                    return;
                }
                else if ( name.equals("mini_stock_can_dealt") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'mini_stock_can_dealt' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.mini_stock_can_dealt = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.mini_stock_can_dealt_is_set)
                        this.mini_stock_can_dealt_is_modified = true;
                    this.mini_stock_can_dealt_is_set = true;
                    return;
                }
                else if ( name.equals("mini_stock_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'mini_stock_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.mini_stock_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.mini_stock_flag_is_set)
                        this.mini_stock_flag_is_modified = true;
                    this.mini_stock_flag_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("new_list_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'new_list_type' must be String: '"+value+"' is not." );
                    this.new_list_type = (String) value;
                    if (this.new_list_type_is_set)
                        this.new_list_type_is_modified = true;
                    this.new_list_type_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("open_otc_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'open_otc_div' must be String: '"+value+"' is not." );
                    this.open_otc_div = (String) value;
                    if (this.open_otc_div_is_set)
                        this.open_otc_div_is_modified = true;
                    this.open_otc_div_is_set = true;
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
                else if ( name.equals("price_range_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'price_range_type' must be String: '"+value+"' is not." );
                    this.price_range_type = (String) value;
                    if (this.price_range_type_is_set)
                        this.price_range_type_is_modified = true;
                    this.price_range_type_is_set = true;
                    return;
                }
                else if ( name.equals("price_range_unit_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'price_range_unit_type' must be String: '"+value+"' is not." );
                    this.price_range_unit_type = (String) value;
                    if (this.price_range_unit_type_is_set)
                        this.price_range_unit_type_is_modified = true;
                    this.price_range_unit_type_is_set = true;
                    return;
                }
                else if ( name.equals("price_range_ratio") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'price_range_ratio' must be Double: '"+value+"' is not." );
                    this.price_range_ratio = (Double) value;
                    if (this.price_range_ratio_is_set)
                        this.price_range_ratio_is_modified = true;
                    this.price_range_ratio_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("shortable_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'shortable_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.shortable_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.shortable_flag_is_set)
                        this.shortable_flag_is_modified = true;
                    this.shortable_flag_is_set = true;
                    return;
                }
                else if ( name.equals("sell_cash_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sell_cash_stop' must be Integer: '"+value+"' is not." );
                    this.sell_cash_stop = (Integer) value;
                    if (this.sell_cash_stop_is_set)
                        this.sell_cash_stop_is_modified = true;
                    this.sell_cash_stop_is_set = true;
                    return;
                }
                else if ( name.equals("sell_spot_market_ord_des_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sell_spot_market_ord_des_stop' must be Integer: '"+value+"' is not." );
                    this.sell_spot_market_ord_des_stop = (Integer) value;
                    if (this.sell_spot_market_ord_des_stop_is_set)
                        this.sell_spot_market_ord_des_stop_is_modified = true;
                    this.sell_spot_market_ord_des_stop_is_set = true;
                    return;
                }
                else if ( name.equals("short_margin_sys_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'short_margin_sys_stop' must be Integer: '"+value+"' is not." );
                    this.short_margin_sys_stop = (Integer) value;
                    if (this.short_margin_sys_stop_is_set)
                        this.short_margin_sys_stop_is_modified = true;
                    this.short_margin_sys_stop_is_set = true;
                    return;
                }
                else if ( name.equals("short_ms_market_ord_des_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'short_ms_market_ord_des_stop' must be Integer: '"+value+"' is not." );
                    this.short_ms_market_ord_des_stop = (Integer) value;
                    if (this.short_ms_market_ord_des_stop_is_set)
                        this.short_ms_market_ord_des_stop_is_modified = true;
                    this.short_ms_market_ord_des_stop_is_set = true;
                    return;
                }
                else if ( name.equals("short_margin_gen_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'short_margin_gen_stop' must be Integer: '"+value+"' is not." );
                    this.short_margin_gen_stop = (Integer) value;
                    if (this.short_margin_gen_stop_is_set)
                        this.short_margin_gen_stop_is_modified = true;
                    this.short_margin_gen_stop_is_set = true;
                    return;
                }
                else if ( name.equals("short_mg_market_ord_des_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'short_mg_market_ord_des_stop' must be Integer: '"+value+"' is not." );
                    this.short_mg_market_ord_des_stop = (Integer) value;
                    if (this.short_mg_market_ord_des_stop_is_set)
                        this.short_mg_market_ord_des_stop_is_modified = true;
                    this.short_mg_market_ord_des_stop_is_set = true;
                    return;
                }
                else if ( name.equals("short_close_margin_sys_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'short_close_margin_sys_stop' must be Integer: '"+value+"' is not." );
                    this.short_close_margin_sys_stop = (Integer) value;
                    if (this.short_close_margin_sys_stop_is_set)
                        this.short_close_margin_sys_stop_is_modified = true;
                    this.short_close_margin_sys_stop_is_set = true;
                    return;
                }
                else if ( name.equals("short_cms_market_ord_des_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'short_cms_market_ord_des_stop' must be Integer: '"+value+"' is not." );
                    this.short_cms_market_ord_des_stop = (Integer) value;
                    if (this.short_cms_market_ord_des_stop_is_set)
                        this.short_cms_market_ord_des_stop_is_modified = true;
                    this.short_cms_market_ord_des_stop_is_set = true;
                    return;
                }
                else if ( name.equals("short_close_margin_gen_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'short_close_margin_gen_stop' must be Integer: '"+value+"' is not." );
                    this.short_close_margin_gen_stop = (Integer) value;
                    if (this.short_close_margin_gen_stop_is_set)
                        this.short_close_margin_gen_stop_is_modified = true;
                    this.short_close_margin_gen_stop_is_set = true;
                    return;
                }
                else if ( name.equals("short_cmg_market_ord_des_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'short_cmg_market_ord_des_stop' must be Integer: '"+value+"' is not." );
                    this.short_cmg_market_ord_des_stop = (Integer) value;
                    if (this.short_cmg_market_ord_des_stop_is_set)
                        this.short_cmg_market_ord_des_stop_is_modified = true;
                    this.short_cmg_market_ord_des_stop_is_set = true;
                    return;
                }
                else if ( name.equals("short_swap_margin_sys_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'short_swap_margin_sys_stop' must be Integer: '"+value+"' is not." );
                    this.short_swap_margin_sys_stop = (Integer) value;
                    if (this.short_swap_margin_sys_stop_is_set)
                        this.short_swap_margin_sys_stop_is_modified = true;
                    this.short_swap_margin_sys_stop_is_set = true;
                    return;
                }
                else if ( name.equals("short_swap_margin_gen_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'short_swap_margin_gen_stop' must be Integer: '"+value+"' is not." );
                    this.short_swap_margin_gen_stop = (Integer) value;
                    if (this.short_swap_margin_gen_stop_is_set)
                        this.short_swap_margin_gen_stop_is_modified = true;
                    this.short_swap_margin_gen_stop_is_set = true;
                    return;
                }
                else if ( name.equals("sell_mini_stock_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sell_mini_stock_stop' must be Integer: '"+value+"' is not." );
                    this.sell_mini_stock_stop = (Integer) value;
                    if (this.sell_mini_stock_stop_is_set)
                        this.sell_mini_stock_stop_is_modified = true;
                    this.sell_mini_stock_stop_is_set = true;
                    return;
                }
                else if ( name.equals("short_margin_deposit_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'short_margin_deposit_rate' must be Double: '"+value+"' is not." );
                    this.short_margin_deposit_rate = (Double) value;
                    if (this.short_margin_deposit_rate_is_set)
                        this.short_margin_deposit_rate_is_modified = true;
                    this.short_margin_deposit_rate_is_set = true;
                    return;
                }
                else if ( name.equals("short_cash_margin_deposit_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'short_cash_margin_deposit_rate' must be Double: '"+value+"' is not." );
                    this.short_cash_margin_deposit_rate = (Double) value;
                    if (this.short_cash_margin_deposit_rate_is_set)
                        this.short_cash_margin_deposit_rate_is_modified = true;
                    this.short_cash_margin_deposit_rate_is_set = true;
                    return;
                }
                else if ( name.equals("stop_high_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'stop_high_price' must be Double: '"+value+"' is not." );
                    this.stop_high_price = (Double) value;
                    if (this.stop_high_price_is_set)
                        this.stop_high_price_is_modified = true;
                    this.stop_high_price_is_set = true;
                    return;
                }
                else if ( name.equals("stop_low_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'stop_low_price' must be Double: '"+value+"' is not." );
                    this.stop_low_price = (Double) value;
                    if (this.stop_low_price_is_set)
                        this.stop_low_price_is_modified = true;
                    this.stop_low_price_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("traded_product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'traded_product_id' must be Long: '"+value+"' is not." );
                    this.traded_product_id = ((Long) value).longValue();
                    if (this.traded_product_id_is_set)
                        this.traded_product_id_is_modified = true;
                    this.traded_product_id_is_set = true;
                    return;
                }
                else if ( name.equals("today_dep_fund_reg") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'today_dep_fund_reg' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.today_dep_fund_reg = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.today_dep_fund_reg_is_set)
                        this.today_dep_fund_reg_is_modified = true;
                    this.today_dep_fund_reg_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("unlisted_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'unlisted_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.unlisted_date = (java.sql.Timestamp) value;
                    if (this.unlisted_date_is_set)
                        this.unlisted_date_is_modified = true;
                    this.unlisted_date_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("valid_until_biz_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'valid_until_biz_date' must be String: '"+value+"' is not." );
                    this.valid_until_biz_date = (String) value;
                    if (this.valid_until_biz_date_is_set)
                        this.valid_until_biz_date_is_modified = true;
                    this.valid_until_biz_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
