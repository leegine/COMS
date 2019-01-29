head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.31.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	GenCurrencyRow.java;


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
 * GenCurrencyRowインタフェースは変更不可でリードオンリーである<em>gen_currency</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link GenCurrencyRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see GenCurrencyPK 
 */
public interface GenCurrencyRow extends Row {


  /** 
   * この{@@link GenCurrencyRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "gen_currency", "master" );


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
   * <em>currency_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCurrencyCode();


  /** 
   * <em>currency_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyCodeIsSet();


  /** 
   * <em>currency_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyCodeIsModified();


  /** 
   * <em>currency_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCurrencyName();


  /** 
   * <em>currency_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyNameIsSet();


  /** 
   * <em>currency_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyNameIsModified();


  /** 
   * <em>currency_short_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCurrencyShortName();


  /** 
   * <em>currency_short_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyShortNameIsSet();


  /** 
   * <em>currency_short_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyShortNameIsModified();


  /** 
   * <em>current_sell_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCurrentSellRate();


  /** 
   * <em>current_sell_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCurrentSellRateIsNull();


  /** 
   * <em>current_sell_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentSellRateIsSet();


  /** 
   * <em>current_sell_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentSellRateIsModified();


  /** 
   * <em>prev_sell_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPrevSellRate();


  /** 
   * <em>prev_sell_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPrevSellRateIsNull();


  /** 
   * <em>prev_sell_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrevSellRateIsSet();


  /** 
   * <em>prev_sell_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrevSellRateIsModified();


  /** 
   * <em>current_buy_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCurrentBuyRate();


  /** 
   * <em>current_buy_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCurrentBuyRateIsNull();


  /** 
   * <em>current_buy_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentBuyRateIsSet();


  /** 
   * <em>current_buy_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentBuyRateIsModified();


  /** 
   * <em>prev_buy_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPrevBuyRate();


  /** 
   * <em>prev_buy_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPrevBuyRateIsNull();


  /** 
   * <em>prev_buy_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrevBuyRateIsSet();


  /** 
   * <em>prev_buy_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrevBuyRateIsModified();


  /** 
   * <em>current_sell_exec_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCurrentSellExecRate();


  /** 
   * <em>current_sell_exec_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCurrentSellExecRateIsNull();


  /** 
   * <em>current_sell_exec_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentSellExecRateIsSet();


  /** 
   * <em>current_sell_exec_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentSellExecRateIsModified();


  /** 
   * <em>prev_sell_exec_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPrevSellExecRate();


  /** 
   * <em>prev_sell_exec_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPrevSellExecRateIsNull();


  /** 
   * <em>prev_sell_exec_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrevSellExecRateIsSet();


  /** 
   * <em>prev_sell_exec_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrevSellExecRateIsModified();


  /** 
   * <em>current_buy_exec_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCurrentBuyExecRate();


  /** 
   * <em>current_buy_exec_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCurrentBuyExecRateIsNull();


  /** 
   * <em>current_buy_exec_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentBuyExecRateIsSet();


  /** 
   * <em>current_buy_exec_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentBuyExecRateIsModified();


  /** 
   * <em>prev_buy_exec_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPrevBuyExecRate();


  /** 
   * <em>prev_buy_exec_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPrevBuyExecRateIsNull();


  /** 
   * <em>prev_buy_exec_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrevBuyExecRateIsSet();


  /** 
   * <em>prev_buy_exec_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrevBuyExecRateIsModified();


  /** 
   * <em>scale</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getScale();


  /** 
   * <em>scale</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getScaleIsSet();


  /** 
   * <em>scale</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getScaleIsModified();


  /** 
   * <em>change_jpy_round_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getChangeJpyRoundDiv();


  /** 
   * <em>change_jpy_round_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChangeJpyRoundDivIsSet();


  /** 
   * <em>change_jpy_round_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChangeJpyRoundDivIsModified();


  /** 
   * <em>change_f_ccy_round_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getChangeFCcyRoundDiv();


  /** 
   * <em>change_f_ccy_round_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChangeFCcyRoundDivIsSet();


  /** 
   * <em>change_f_ccy_round_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChangeFCcyRoundDivIsModified();


  /** 
   * <em>rate_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRateLastUpdater();


  /** 
   * <em>rate_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRateLastUpdaterIsSet();


  /** 
   * <em>rate_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRateLastUpdaterIsModified();


  /** 
   * <em>exec_rate_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExecRateLastUpdater();


  /** 
   * <em>exec_rate_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecRateLastUpdaterIsSet();


  /** 
   * <em>exec_rate_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecRateLastUpdaterIsModified();


  /** 
   * <em>rate_update_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getRateUpdateTimestamp();


  /** 
   * <em>rate_update_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRateUpdateTimestampIsSet();


  /** 
   * <em>rate_update_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRateUpdateTimestampIsModified();


  /** 
   * <em>exec_rate_update_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getExecRateUpdateTimestamp();


  /** 
   * <em>exec_rate_update_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecRateUpdateTimestampIsSet();


  /** 
   * <em>exec_rate_update_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecRateUpdateTimestampIsModified();


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
