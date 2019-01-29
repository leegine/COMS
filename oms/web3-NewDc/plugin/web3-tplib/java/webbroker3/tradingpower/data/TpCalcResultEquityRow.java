head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultEquityRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * TpCalcResultEquityRowインタフェースは変更不可でリードオンリーである<em>tp_calc_result_equity</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link TpCalcResultEquityRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCalcResultEquityPK 
 */
public interface TpCalcResultEquityRow extends Row {


  /** 
   * この{@@link TpCalcResultEquityRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "tp_calc_result_equity", "account" );


  /** 
   * <em>calc_result_equity_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getCalcResultEquityId();


  /** 
   * <em>calc_result_equity_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcResultEquityIdIsSet();


  /** 
   * <em>calc_result_equity_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcResultEquityIdIsModified();


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
   * <em>account_balance_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAccountBalance0();


  /** 
   * <em>account_balance_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalance0IsSet();


  /** 
   * <em>account_balance_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalance0IsModified();


  /** 
   * <em>account_balance_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAccountBalance1();


  /** 
   * <em>account_balance_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalance1IsSet();


  /** 
   * <em>account_balance_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalance1IsModified();


  /** 
   * <em>account_balance_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAccountBalance2();


  /** 
   * <em>account_balance_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalance2IsSet();


  /** 
   * <em>account_balance_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalance2IsModified();


  /** 
   * <em>account_balance_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAccountBalance3();


  /** 
   * <em>account_balance_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalance3IsSet();


  /** 
   * <em>account_balance_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalance3IsModified();


  /** 
   * <em>account_balance_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAccountBalance4();


  /** 
   * <em>account_balance_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalance4IsSet();


  /** 
   * <em>account_balance_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalance4IsModified();


  /** 
   * <em>account_balance_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAccountBalance5();


  /** 
   * <em>account_balance_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalance5IsSet();


  /** 
   * <em>account_balance_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalance5IsModified();


  /** 
   * <em>today_executed_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayExecutedAmount0();


  /** 
   * <em>today_executed_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmount0IsSet();


  /** 
   * <em>today_executed_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmount0IsModified();


  /** 
   * <em>today_executed_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayExecutedAmount1();


  /** 
   * <em>today_executed_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmount1IsSet();


  /** 
   * <em>today_executed_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmount1IsModified();


  /** 
   * <em>today_executed_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayExecutedAmount2();


  /** 
   * <em>today_executed_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmount2IsSet();


  /** 
   * <em>today_executed_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmount2IsModified();


  /** 
   * <em>today_executed_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayExecutedAmount3();


  /** 
   * <em>today_executed_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmount3IsSet();


  /** 
   * <em>today_executed_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmount3IsModified();


  /** 
   * <em>today_executed_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayExecutedAmount4();


  /** 
   * <em>today_executed_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmount4IsSet();


  /** 
   * <em>today_executed_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmount4IsModified();


  /** 
   * <em>today_executed_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayExecutedAmount5();


  /** 
   * <em>today_executed_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmount5IsSet();


  /** 
   * <em>today_executed_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmount5IsModified();


  /** 
   * <em>today_unexecuted_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayUnexecutedAmount0();


  /** 
   * <em>today_unexecuted_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmount0IsSet();


  /** 
   * <em>today_unexecuted_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmount0IsModified();


  /** 
   * <em>today_unexecuted_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayUnexecutedAmount1();


  /** 
   * <em>today_unexecuted_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmount1IsSet();


  /** 
   * <em>today_unexecuted_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmount1IsModified();


  /** 
   * <em>today_unexecuted_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayUnexecutedAmount2();


  /** 
   * <em>today_unexecuted_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmount2IsSet();


  /** 
   * <em>today_unexecuted_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmount2IsModified();


  /** 
   * <em>today_unexecuted_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayUnexecutedAmount3();


  /** 
   * <em>today_unexecuted_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmount3IsSet();


  /** 
   * <em>today_unexecuted_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmount3IsModified();


  /** 
   * <em>today_unexecuted_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayUnexecutedAmount4();


  /** 
   * <em>today_unexecuted_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmount4IsSet();


  /** 
   * <em>today_unexecuted_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmount4IsModified();


  /** 
   * <em>today_unexecuted_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayUnexecutedAmount5();


  /** 
   * <em>today_unexecuted_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmount5IsSet();


  /** 
   * <em>today_unexecuted_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmount5IsModified();


  /** 
   * <em>day_trade_restraint_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayTradeRestraint0();


  /** 
   * <em>day_trade_restraint_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraint0IsSet();


  /** 
   * <em>day_trade_restraint_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraint0IsModified();


  /** 
   * <em>day_trade_restraint_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayTradeRestraint1();


  /** 
   * <em>day_trade_restraint_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraint1IsSet();


  /** 
   * <em>day_trade_restraint_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraint1IsModified();


  /** 
   * <em>day_trade_restraint_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayTradeRestraint2();


  /** 
   * <em>day_trade_restraint_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraint2IsSet();


  /** 
   * <em>day_trade_restraint_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraint2IsModified();


  /** 
   * <em>day_trade_restraint_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayTradeRestraint3();


  /** 
   * <em>day_trade_restraint_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraint3IsSet();


  /** 
   * <em>day_trade_restraint_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraint3IsModified();


  /** 
   * <em>day_trade_restraint_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayTradeRestraint4();


  /** 
   * <em>day_trade_restraint_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraint4IsSet();


  /** 
   * <em>day_trade_restraint_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraint4IsModified();


  /** 
   * <em>other_restraint_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOtherRestraint0();


  /** 
   * <em>other_restraint_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherRestraint0IsSet();


  /** 
   * <em>other_restraint_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherRestraint0IsModified();


  /** 
   * <em>other_restraint_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOtherRestraint1();


  /** 
   * <em>other_restraint_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherRestraint1IsSet();


  /** 
   * <em>other_restraint_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherRestraint1IsModified();


  /** 
   * <em>other_restraint_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOtherRestraint2();


  /** 
   * <em>other_restraint_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherRestraint2IsSet();


  /** 
   * <em>other_restraint_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherRestraint2IsModified();


  /** 
   * <em>other_restraint_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOtherRestraint3();


  /** 
   * <em>other_restraint_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherRestraint3IsSet();


  /** 
   * <em>other_restraint_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherRestraint3IsModified();


  /** 
   * <em>other_restraint_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOtherRestraint4();


  /** 
   * <em>other_restraint_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherRestraint4IsSet();


  /** 
   * <em>other_restraint_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherRestraint4IsModified();


  /** 
   * <em>other_restraint_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOtherRestraint5();


  /** 
   * <em>other_restraint_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherRestraint5IsSet();


  /** 
   * <em>other_restraint_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherRestraint5IsModified();


  /** 
   * <em>trust_security_asset_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTrustSecurityAsset0();


  /** 
   * <em>trust_security_asset_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustSecurityAsset0IsSet();


  /** 
   * <em>trust_security_asset_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustSecurityAsset0IsModified();


  /** 
   * <em>trust_security_asset_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTrustSecurityAsset1();


  /** 
   * <em>trust_security_asset_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustSecurityAsset1IsSet();


  /** 
   * <em>trust_security_asset_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustSecurityAsset1IsModified();


  /** 
   * <em>trust_security_asset_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTrustSecurityAsset2();


  /** 
   * <em>trust_security_asset_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustSecurityAsset2IsSet();


  /** 
   * <em>trust_security_asset_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustSecurityAsset2IsModified();


  /** 
   * <em>trust_security_asset_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTrustSecurityAsset3();


  /** 
   * <em>trust_security_asset_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustSecurityAsset3IsSet();


  /** 
   * <em>trust_security_asset_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustSecurityAsset3IsModified();


  /** 
   * <em>trust_security_asset_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTrustSecurityAsset4();


  /** 
   * <em>trust_security_asset_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustSecurityAsset4IsSet();


  /** 
   * <em>trust_security_asset_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustSecurityAsset4IsModified();


  /** 
   * <em>trust_security_asset_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTrustSecurityAsset5();


  /** 
   * <em>trust_security_asset_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustSecurityAsset5IsSet();


  /** 
   * <em>trust_security_asset_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustSecurityAsset5IsModified();


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
   * <em>cash_deposit_restraint_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashDepositRestraint0();


  /** 
   * <em>cash_deposit_restraint_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashDepositRestraint0IsSet();


  /** 
   * <em>cash_deposit_restraint_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashDepositRestraint0IsModified();


  /** 
   * <em>cash_deposit_restraint_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashDepositRestraint1();


  /** 
   * <em>cash_deposit_restraint_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashDepositRestraint1IsSet();


  /** 
   * <em>cash_deposit_restraint_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashDepositRestraint1IsModified();


  /** 
   * <em>cash_deposit_restraint_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashDepositRestraint2();


  /** 
   * <em>cash_deposit_restraint_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashDepositRestraint2IsSet();


  /** 
   * <em>cash_deposit_restraint_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashDepositRestraint2IsModified();


  /** 
   * <em>cash_deposit_restraint_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashDepositRestraint3();


  /** 
   * <em>cash_deposit_restraint_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashDepositRestraint3IsSet();


  /** 
   * <em>cash_deposit_restraint_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashDepositRestraint3IsModified();


  /** 
   * <em>cash_deposit_restraint_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashDepositRestraint4();


  /** 
   * <em>cash_deposit_restraint_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashDepositRestraint4IsSet();


  /** 
   * <em>cash_deposit_restraint_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashDepositRestraint4IsModified();


  /** 
   * <em>cash_deposit_restraint_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashDepositRestraint5();


  /** 
   * <em>cash_deposit_restraint_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashDepositRestraint5IsSet();


  /** 
   * <em>cash_deposit_restraint_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashDepositRestraint5IsModified();


  /** 
   * <em>day_trade_restraint_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayTradeRestraint5();


  /** 
   * <em>day_trade_restraint_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayTradeRestraint5IsNull();


  /** 
   * <em>day_trade_restraint_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraint5IsSet();


  /** 
   * <em>day_trade_restraint_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraint5IsModified();


}
@
