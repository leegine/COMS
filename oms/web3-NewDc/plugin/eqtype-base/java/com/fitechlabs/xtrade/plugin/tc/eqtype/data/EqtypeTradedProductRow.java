head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.37.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeTradedProductRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * EqtypeTradedProductRowインタフェースは変更不可でリードオンリーである<em>eqtype_traded_product</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link EqtypeTradedProductRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeTradedProductPK 
 */
public interface EqtypeTradedProductRow extends Row {


  /** 
   * この{@@link EqtypeTradedProductRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "eqtype_traded_product", "master" );


  /** 
   * <em>traded_product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getTradedProductId();


  /** 
   * <em>traded_product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradedProductIdIsSet();


  /** 
   * <em>traded_product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradedProductIdIsModified();


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInstitutionCode();


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsSet();


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsModified();


  /** 
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getProductId();


  /** 
   * <em>product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductIdIsSet();


  /** 
   * <em>product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductIdIsModified();


  /** 
   * <em>market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarketId();


  /** 
   * <em>market_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketIdIsSet();


  /** 
   * <em>market_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketIdIsModified();


  /** 
   * <em>valid_until_biz_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getValidUntilBizDate();


  /** 
   * <em>valid_until_biz_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getValidUntilBizDateIsSet();


  /** 
   * <em>valid_until_biz_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getValidUntilBizDateIsModified();


  /** 
   * <em>list_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getListFlag();


  /** 
   * <em>list_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getListFlagIsSet();


  /** 
   * <em>list_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getListFlagIsModified();


  /** 
   * <em>list_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getListType();


  /** 
   * <em>list_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getListTypeIsSet();


  /** 
   * <em>list_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getListTypeIsModified();


  /** 
   * <em>new_list_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNewListType();


  /** 
   * <em>new_list_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewListTypeIsSet();


  /** 
   * <em>new_list_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewListTypeIsModified();


  /** 
   * <em>listed_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getListedDate();


  /** 
   * <em>listed_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getListedDateIsSet();


  /** 
   * <em>listed_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getListedDateIsModified();


  /** 
   * <em>unlisted_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getUnlistedDate();


  /** 
   * <em>unlisted_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnlistedDateIsSet();


  /** 
   * <em>unlisted_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnlistedDateIsModified();


  /** 
   * <em>marginable_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMarginableFlag();


  /** 
   * <em>marginable_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginableFlagIsSet();


  /** 
   * <em>marginable_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginableFlagIsModified();


  /** 
   * <em>shortable_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getShortableFlag();


  /** 
   * <em>shortable_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortableFlagIsSet();


  /** 
   * <em>shortable_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortableFlagIsModified();


  /** 
   * <em>open_otc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOpenOtcDiv();


  /** 
   * <em>open_otc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenOtcDivIsSet();


  /** 
   * <em>open_otc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenOtcDivIsModified();


  /** 
   * <em>margin_sys_product_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginSysProductType();


  /** 
   * <em>margin_sys_product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSysProductTypeIsSet();


  /** 
   * <em>margin_sys_product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSysProductTypeIsModified();


  /** 
   * <em>margin_gen_product_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginGenProductType();


  /** 
   * <em>margin_gen_product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginGenProductTypeIsSet();


  /** 
   * <em>margin_gen_product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginGenProductTypeIsModified();


  /** 
   * <em>mini_stock_can_dealt</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMiniStockCanDealt();


  /** 
   * <em>mini_stock_can_dealt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiniStockCanDealtIsSet();


  /** 
   * <em>mini_stock_can_dealt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiniStockCanDealtIsModified();


  /** 
   * <em>buy_cash_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getBuyCashStop();


  /** 
   * <em>buy_cash_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBuyCashStopIsNull();


  /** 
   * <em>buy_cash_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyCashStopIsSet();


  /** 
   * <em>buy_cash_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyCashStopIsModified();


  /** 
   * <em>sell_cash_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSellCashStop();


  /** 
   * <em>sell_cash_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellCashStopIsNull();


  /** 
   * <em>sell_cash_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellCashStopIsSet();


  /** 
   * <em>sell_cash_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellCashStopIsModified();


  /** 
   * <em>buy_spot_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getBuySpotMarketOrdDesStop();


  /** 
   * <em>buy_spot_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBuySpotMarketOrdDesStopIsNull();


  /** 
   * <em>buy_spot_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuySpotMarketOrdDesStopIsSet();


  /** 
   * <em>buy_spot_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuySpotMarketOrdDesStopIsModified();


  /** 
   * <em>sell_spot_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSellSpotMarketOrdDesStop();


  /** 
   * <em>sell_spot_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellSpotMarketOrdDesStopIsNull();


  /** 
   * <em>sell_spot_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellSpotMarketOrdDesStopIsSet();


  /** 
   * <em>sell_spot_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellSpotMarketOrdDesStopIsModified();


  /** 
   * <em>long_margin_sys_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLongMarginSysStop();


  /** 
   * <em>long_margin_sys_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLongMarginSysStopIsNull();


  /** 
   * <em>long_margin_sys_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongMarginSysStopIsSet();


  /** 
   * <em>long_margin_sys_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongMarginSysStopIsModified();


  /** 
   * <em>short_margin_sys_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getShortMarginSysStop();


  /** 
   * <em>short_margin_sys_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortMarginSysStopIsNull();


  /** 
   * <em>short_margin_sys_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMarginSysStopIsSet();


  /** 
   * <em>short_margin_sys_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMarginSysStopIsModified();


  /** 
   * <em>long_ms_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLongMsMarketOrdDesStop();


  /** 
   * <em>long_ms_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLongMsMarketOrdDesStopIsNull();


  /** 
   * <em>long_ms_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongMsMarketOrdDesStopIsSet();


  /** 
   * <em>long_ms_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongMsMarketOrdDesStopIsModified();


  /** 
   * <em>short_ms_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getShortMsMarketOrdDesStop();


  /** 
   * <em>short_ms_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortMsMarketOrdDesStopIsNull();


  /** 
   * <em>short_ms_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMsMarketOrdDesStopIsSet();


  /** 
   * <em>short_ms_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMsMarketOrdDesStopIsModified();


  /** 
   * <em>long_margin_gen_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLongMarginGenStop();


  /** 
   * <em>long_margin_gen_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLongMarginGenStopIsNull();


  /** 
   * <em>long_margin_gen_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongMarginGenStopIsSet();


  /** 
   * <em>long_margin_gen_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongMarginGenStopIsModified();


  /** 
   * <em>short_margin_gen_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getShortMarginGenStop();


  /** 
   * <em>short_margin_gen_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortMarginGenStopIsNull();


  /** 
   * <em>short_margin_gen_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMarginGenStopIsSet();


  /** 
   * <em>short_margin_gen_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMarginGenStopIsModified();


  /** 
   * <em>long_mg_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLongMgMarketOrdDesStop();


  /** 
   * <em>long_mg_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLongMgMarketOrdDesStopIsNull();


  /** 
   * <em>long_mg_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongMgMarketOrdDesStopIsSet();


  /** 
   * <em>long_mg_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongMgMarketOrdDesStopIsModified();


  /** 
   * <em>short_mg_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getShortMgMarketOrdDesStop();


  /** 
   * <em>short_mg_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortMgMarketOrdDesStopIsNull();


  /** 
   * <em>short_mg_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMgMarketOrdDesStopIsSet();


  /** 
   * <em>short_mg_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMgMarketOrdDesStopIsModified();


  /** 
   * <em>long_close_margin_sys_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLongCloseMarginSysStop();


  /** 
   * <em>long_close_margin_sys_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLongCloseMarginSysStopIsNull();


  /** 
   * <em>long_close_margin_sys_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongCloseMarginSysStopIsSet();


  /** 
   * <em>long_close_margin_sys_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongCloseMarginSysStopIsModified();


  /** 
   * <em>short_close_margin_sys_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getShortCloseMarginSysStop();


  /** 
   * <em>short_close_margin_sys_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortCloseMarginSysStopIsNull();


  /** 
   * <em>short_close_margin_sys_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortCloseMarginSysStopIsSet();


  /** 
   * <em>short_close_margin_sys_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortCloseMarginSysStopIsModified();


  /** 
   * <em>long_cms_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLongCmsMarketOrdDesStop();


  /** 
   * <em>long_cms_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLongCmsMarketOrdDesStopIsNull();


  /** 
   * <em>long_cms_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongCmsMarketOrdDesStopIsSet();


  /** 
   * <em>long_cms_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongCmsMarketOrdDesStopIsModified();


  /** 
   * <em>short_cms_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getShortCmsMarketOrdDesStop();


  /** 
   * <em>short_cms_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortCmsMarketOrdDesStopIsNull();


  /** 
   * <em>short_cms_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortCmsMarketOrdDesStopIsSet();


  /** 
   * <em>short_cms_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortCmsMarketOrdDesStopIsModified();


  /** 
   * <em>long_close_margin_gen_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLongCloseMarginGenStop();


  /** 
   * <em>long_close_margin_gen_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLongCloseMarginGenStopIsNull();


  /** 
   * <em>long_close_margin_gen_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongCloseMarginGenStopIsSet();


  /** 
   * <em>long_close_margin_gen_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongCloseMarginGenStopIsModified();


  /** 
   * <em>short_close_margin_gen_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getShortCloseMarginGenStop();


  /** 
   * <em>short_close_margin_gen_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortCloseMarginGenStopIsNull();


  /** 
   * <em>short_close_margin_gen_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortCloseMarginGenStopIsSet();


  /** 
   * <em>short_close_margin_gen_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortCloseMarginGenStopIsModified();


  /** 
   * <em>long_cmg_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLongCmgMarketOrdDesStop();


  /** 
   * <em>long_cmg_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLongCmgMarketOrdDesStopIsNull();


  /** 
   * <em>long_cmg_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongCmgMarketOrdDesStopIsSet();


  /** 
   * <em>long_cmg_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongCmgMarketOrdDesStopIsModified();


  /** 
   * <em>short_cmg_market_ord_des_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getShortCmgMarketOrdDesStop();


  /** 
   * <em>short_cmg_market_ord_des_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortCmgMarketOrdDesStopIsNull();


  /** 
   * <em>short_cmg_market_ord_des_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortCmgMarketOrdDesStopIsSet();


  /** 
   * <em>short_cmg_market_ord_des_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortCmgMarketOrdDesStopIsModified();


  /** 
   * <em>long_swap_margin_sys_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLongSwapMarginSysStop();


  /** 
   * <em>long_swap_margin_sys_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLongSwapMarginSysStopIsNull();


  /** 
   * <em>long_swap_margin_sys_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongSwapMarginSysStopIsSet();


  /** 
   * <em>long_swap_margin_sys_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongSwapMarginSysStopIsModified();


  /** 
   * <em>short_swap_margin_sys_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getShortSwapMarginSysStop();


  /** 
   * <em>short_swap_margin_sys_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortSwapMarginSysStopIsNull();


  /** 
   * <em>short_swap_margin_sys_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortSwapMarginSysStopIsSet();


  /** 
   * <em>short_swap_margin_sys_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortSwapMarginSysStopIsModified();


  /** 
   * <em>long_swap_margin_gen_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLongSwapMarginGenStop();


  /** 
   * <em>long_swap_margin_gen_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLongSwapMarginGenStopIsNull();


  /** 
   * <em>long_swap_margin_gen_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongSwapMarginGenStopIsSet();


  /** 
   * <em>long_swap_margin_gen_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongSwapMarginGenStopIsModified();


  /** 
   * <em>short_swap_margin_gen_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getShortSwapMarginGenStop();


  /** 
   * <em>short_swap_margin_gen_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortSwapMarginGenStopIsNull();


  /** 
   * <em>short_swap_margin_gen_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortSwapMarginGenStopIsSet();


  /** 
   * <em>short_swap_margin_gen_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortSwapMarginGenStopIsModified();


  /** 
   * <em>buy_mini_stock_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getBuyMiniStockStop();


  /** 
   * <em>buy_mini_stock_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBuyMiniStockStopIsNull();


  /** 
   * <em>buy_mini_stock_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyMiniStockStopIsSet();


  /** 
   * <em>buy_mini_stock_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyMiniStockStopIsModified();


  /** 
   * <em>sell_mini_stock_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSellMiniStockStop();


  /** 
   * <em>sell_mini_stock_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellMiniStockStopIsNull();


  /** 
   * <em>sell_mini_stock_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellMiniStockStopIsSet();


  /** 
   * <em>sell_mini_stock_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellMiniStockStopIsModified();


  /** 
   * <em>lot_size</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLotSize();


  /** 
   * <em>lot_size</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLotSizeIsSet();


  /** 
   * <em>lot_size</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLotSizeIsModified();


  /** 
   * <em>long_margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLongMarginDepositRate();


  /** 
   * <em>long_margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLongMarginDepositRateIsNull();


  /** 
   * <em>long_margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongMarginDepositRateIsSet();


  /** 
   * <em>long_margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongMarginDepositRateIsModified();


  /** 
   * <em>short_margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getShortMarginDepositRate();


  /** 
   * <em>short_margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortMarginDepositRateIsNull();


  /** 
   * <em>short_margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMarginDepositRateIsSet();


  /** 
   * <em>short_margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMarginDepositRateIsModified();


  /** 
   * <em>long_cash_margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLongCashMarginDepositRate();


  /** 
   * <em>long_cash_margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLongCashMarginDepositRateIsNull();


  /** 
   * <em>long_cash_margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongCashMarginDepositRateIsSet();


  /** 
   * <em>long_cash_margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongCashMarginDepositRateIsModified();


  /** 
   * <em>short_cash_margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getShortCashMarginDepositRate();


  /** 
   * <em>short_cash_margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortCashMarginDepositRateIsNull();


  /** 
   * <em>short_cash_margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortCashMarginDepositRateIsSet();


  /** 
   * <em>short_cash_margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortCashMarginDepositRateIsModified();


  /** 
   * <em>last_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLastClosingPrice();


  /** 
   * <em>last_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastClosingPriceIsSet();


  /** 
   * <em>last_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastClosingPriceIsModified();


  /** 
   * <em>price_range_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPriceRangeType();


  /** 
   * <em>price_range_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceRangeTypeIsSet();


  /** 
   * <em>price_range_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceRangeTypeIsModified();


  /** 
   * <em>price_range_unit_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPriceRangeUnitType();


  /** 
   * <em>price_range_unit_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceRangeUnitTypeIsSet();


  /** 
   * <em>price_range_unit_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceRangeUnitTypeIsModified();


  /** 
   * <em>high_compulsive_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getHighCompulsivePriceRange();


  /** 
   * <em>high_compulsive_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getHighCompulsivePriceRangeIsNull();


  /** 
   * <em>high_compulsive_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHighCompulsivePriceRangeIsSet();


  /** 
   * <em>high_compulsive_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHighCompulsivePriceRangeIsModified();


  /** 
   * <em>low_compulsive_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLowCompulsivePriceRange();


  /** 
   * <em>low_compulsive_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLowCompulsivePriceRangeIsNull();


  /** 
   * <em>low_compulsive_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLowCompulsivePriceRangeIsSet();


  /** 
   * <em>low_compulsive_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLowCompulsivePriceRangeIsModified();


  /** 
   * <em>stop_high_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getStopHighPrice();


  /** 
   * <em>stop_high_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getStopHighPriceIsNull();


  /** 
   * <em>stop_high_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopHighPriceIsSet();


  /** 
   * <em>stop_high_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopHighPriceIsModified();


  /** 
   * <em>stop_low_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getStopLowPrice();


  /** 
   * <em>stop_low_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getStopLowPriceIsNull();


  /** 
   * <em>stop_low_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopLowPriceIsSet();


  /** 
   * <em>stop_low_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopLowPriceIsModified();


  /** 
   * <em>price_range_ratio</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPriceRangeRatio();


  /** 
   * <em>price_range_ratio</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPriceRangeRatioIsNull();


  /** 
   * <em>price_range_ratio</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceRangeRatioIsSet();


  /** 
   * <em>price_range_ratio</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceRangeRatioIsModified();


  /** 
   * <em>compulsive_limited_unit</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getCompulsiveLimitedUnit();


  /** 
   * <em>compulsive_limited_unit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCompulsiveLimitedUnitIsNull();


  /** 
   * <em>compulsive_limited_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCompulsiveLimitedUnitIsSet();


  /** 
   * <em>compulsive_limited_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCompulsiveLimitedUnitIsModified();


  /** 
   * <em>mini_stock_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMiniStockFlag();


  /** 
   * <em>mini_stock_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiniStockFlagIsSet();


  /** 
   * <em>mini_stock_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiniStockFlagIsModified();


  /** 
   * <em>today_dep_fund_reg</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getTodayDepFundReg();


  /** 
   * <em>today_dep_fund_reg</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRegIsSet();


  /** 
   * <em>today_dep_fund_reg</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRegIsModified();


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLastUpdater();


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsSet();


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsModified();


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getCreatedTimestamp();


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatedTimestampIsSet();


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatedTimestampIsModified();


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getLastUpdatedTimestamp();


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdatedTimestampIsSet();


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdatedTimestampIsModified();


  /** 
   * <em>base_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getBasePrice();


  /** 
   * <em>base_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBasePriceIsSet();


  /** 
   * <em>base_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBasePriceIsModified();


}
@
