head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.31.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EnableOrderConditionRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * EnableOrderConditionRowインタフェースは変更不可でリードオンリーである<em>enable_order_condition</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link EnableOrderConditionRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EnableOrderConditionPK 
 */
public interface EnableOrderConditionRow extends Row {


  /** 
   * この{@@link EnableOrderConditionRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "enable_order_condition", "master" );


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
   * <em>product_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType();


  /** 
   * <em>product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductTypeIsSet();


  /** 
   * <em>product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductTypeIsModified();


  /** 
   * <em>future_option_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFutureOptionDiv();


  /** 
   * <em>future_option_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFutureOptionDivIsSet();


  /** 
   * <em>future_option_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFutureOptionDivIsModified();


  /** 
   * <em>margin_trading_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginTradingDiv();


  /** 
   * <em>margin_trading_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTradingDivIsSet();


  /** 
   * <em>margin_trading_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTradingDivIsModified();


  /** 
   * <em>market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarketCode();


  /** 
   * <em>market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketCodeIsSet();


  /** 
   * <em>market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketCodeIsModified();


  /** 
   * <em>carried_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCarriedOrder();


  /** 
   * <em>carried_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCarriedOrderIsSet();


  /** 
   * <em>carried_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCarriedOrderIsModified();


  /** 
   * <em>carried_order_lapse_date_spec</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCarriedOrderLapseDateSpec();


  /** 
   * <em>carried_order_lapse_date_spec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCarriedOrderLapseDateSpecIsSet();


  /** 
   * <em>carried_order_lapse_date_spec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCarriedOrderLapseDateSpecIsModified();


  /** 
   * <em>carried_order_day_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getCarriedOrderDayCount();


  /** 
   * <em>carried_order_day_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCarriedOrderDayCountIsNull();


  /** 
   * <em>carried_order_day_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCarriedOrderDayCountIsSet();


  /** 
   * <em>carried_order_day_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCarriedOrderDayCountIsModified();


  /** 
   * <em>mart_price_buy_to_open</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMartPriceBuyToOpen();


  /** 
   * <em>mart_price_buy_to_open</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMartPriceBuyToOpenIsSet();


  /** 
   * <em>mart_price_buy_to_open</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMartPriceBuyToOpenIsModified();


  /** 
   * <em>mart_price_sell_to_open</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMartPriceSellToOpen();


  /** 
   * <em>mart_price_sell_to_open</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMartPriceSellToOpenIsSet();


  /** 
   * <em>mart_price_sell_to_open</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMartPriceSellToOpenIsModified();


  /** 
   * <em>mart_price_order_settle_cont</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMartPriceOrderSettleCont();


  /** 
   * <em>mart_price_order_settle_cont</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMartPriceOrderSettleContIsSet();


  /** 
   * <em>mart_price_order_settle_cont</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMartPriceOrderSettleContIsModified();


  /** 
   * <em>current_price_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCurrentPriceOrder();


  /** 
   * <em>current_price_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentPriceOrderIsSet();


  /** 
   * <em>current_price_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentPriceOrderIsModified();


  /** 
   * <em>ease_current_price_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEaseCurrentPriceOrder();


  /** 
   * <em>ease_current_price_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEaseCurrentPriceOrderIsSet();


  /** 
   * <em>ease_current_price_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEaseCurrentPriceOrderIsModified();


  /** 
   * <em>market_price_rest_limit_price</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarketPriceRestLimitPrice();


  /** 
   * <em>market_price_rest_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketPriceRestLimitPriceIsSet();


  /** 
   * <em>market_price_rest_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketPriceRestLimitPriceIsModified();


  /** 
   * <em>market_price_rest_cancel</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarketPriceRestCancel();


  /** 
   * <em>market_price_rest_cancel</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketPriceRestCancelIsSet();


  /** 
   * <em>market_price_rest_cancel</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketPriceRestCancelIsModified();


  /** 
   * <em>at_market_open</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAtMarketOpen();


  /** 
   * <em>at_market_open</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAtMarketOpenIsSet();


  /** 
   * <em>at_market_open</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAtMarketOpenIsModified();


  /** 
   * <em>at_market_close</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAtMarketClose();


  /** 
   * <em>at_market_close</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAtMarketCloseIsSet();


  /** 
   * <em>at_market_close</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAtMarketCloseIsModified();


  /** 
   * <em>no_exec_at_mart_close</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNoExecAtMartClose();


  /** 
   * <em>no_exec_at_mart_close</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNoExecAtMartCloseIsSet();


  /** 
   * <em>no_exec_at_mart_close</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNoExecAtMartCloseIsModified();


  /** 
   * <em>stop_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStopOrder();


  /** 
   * <em>stop_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopOrderIsSet();


  /** 
   * <em>stop_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopOrderIsModified();


  /** 
   * <em>w_limit_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getWLimitOrder();


  /** 
   * <em>w_limit_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWLimitOrderIsSet();


  /** 
   * <em>w_limit_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWLimitOrderIsModified();


  /** 
   * <em>chain_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getChainOrder();


  /** 
   * <em>chain_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChainOrderIsSet();


  /** 
   * <em>chain_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChainOrderIsModified();


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
   * <em>evening_session_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEveningSessionOrder();


  /** 
   * <em>evening_session_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEveningSessionOrderIsSet();


  /** 
   * <em>evening_session_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEveningSessionOrderIsModified();


}
@
