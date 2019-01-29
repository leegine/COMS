head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.28.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AccountProductOrderStopRow.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * AccountProductOrderStopRowインタフェースは変更不可でリードオンリーである<em>account_product_order_stop</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link AccountProductOrderStopRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccountProductOrderStopPK 
 */
public interface AccountProductOrderStopRow extends Row {


  /** 
   * この{@@link AccountProductOrderStopRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "account_product_order_stop", "account" );


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
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getBranchId();


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchIdIsSet();


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchIdIsModified();


  /** 
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAccountId();


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountIdIsSet();


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountIdIsModified();


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
   * <em>apply_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getApplyStartDate();


  /** 
   * <em>apply_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getApplyStartDateIsSet();


  /** 
   * <em>apply_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getApplyStartDateIsModified();


  /** 
   * <em>apply_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getApplyEndDate();


  /** 
   * <em>apply_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getApplyEndDateIsSet();


  /** 
   * <em>apply_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getApplyEndDateIsModified();


  /** 
   * <em>stop_trade_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStopTradeReason();


  /** 
   * <em>stop_trade_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopTradeReasonIsSet();


  /** 
   * <em>stop_trade_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopTradeReasonIsModified();


  /** 
   * <em>stop_trade_div_buy_cash</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStopTradeDivBuyCash();


  /** 
   * <em>stop_trade_div_buy_cash</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopTradeDivBuyCashIsSet();


  /** 
   * <em>stop_trade_div_buy_cash</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopTradeDivBuyCashIsModified();


  /** 
   * <em>stop_trade_div_sell_cash</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStopTradeDivSellCash();


  /** 
   * <em>stop_trade_div_sell_cash</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopTradeDivSellCashIsSet();


  /** 
   * <em>stop_trade_div_sell_cash</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopTradeDivSellCashIsModified();


  /** 
   * <em>stop_trade_div_long_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStopTradeDivLongMargin();


  /** 
   * <em>stop_trade_div_long_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopTradeDivLongMarginIsSet();


  /** 
   * <em>stop_trade_div_long_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopTradeDivLongMarginIsModified();


  /** 
   * <em>stop_trade_div_short_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStopTradeDivShortMargin();


  /** 
   * <em>stop_trade_div_short_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopTradeDivShortMarginIsSet();


  /** 
   * <em>stop_trade_div_short_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopTradeDivShortMarginIsModified();


  /** 
   * <em>stop_div_long_close_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStopDivLongCloseMargin();


  /** 
   * <em>stop_div_long_close_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopDivLongCloseMarginIsSet();


  /** 
   * <em>stop_div_long_close_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopDivLongCloseMarginIsModified();


  /** 
   * <em>stop_div_short_close_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStopDivShortCloseMargin();


  /** 
   * <em>stop_div_short_close_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopDivShortCloseMarginIsSet();


  /** 
   * <em>stop_div_short_close_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopDivShortCloseMarginIsModified();


  /** 
   * <em>stop_div_long_swap_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStopDivLongSwapMargin();


  /** 
   * <em>stop_div_long_swap_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopDivLongSwapMarginIsSet();


  /** 
   * <em>stop_div_long_swap_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopDivLongSwapMarginIsModified();


  /** 
   * <em>stop_div_short_swap_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStopDivShortSwapMargin();


  /** 
   * <em>stop_div_short_swap_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopDivShortSwapMarginIsSet();


  /** 
   * <em>stop_div_short_swap_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopDivShortSwapMarginIsModified();


  /** 
   * <em>stop_div_buy_mini_stock</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStopDivBuyMiniStock();


  /** 
   * <em>stop_div_buy_mini_stock</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopDivBuyMiniStockIsSet();


  /** 
   * <em>stop_div_buy_mini_stock</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopDivBuyMiniStockIsModified();


  /** 
   * <em>stop_div_sell_mini_stock</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStopDivSellMiniStock();


  /** 
   * <em>stop_div_sell_mini_stock</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopDivSellMiniStockIsSet();


  /** 
   * <em>stop_div_sell_mini_stock</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopDivSellMiniStockIsModified();


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


}
@
