head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultMarginRow.java;


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
 * TpCalcResultMarginRowインタフェースは変更不可でリードオンリーである<em>tp_calc_result_margin</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link TpCalcResultMarginRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCalcResultMarginPK 
 */
public interface TpCalcResultMarginRow extends Row {


  /** 
   * この{@@link TpCalcResultMarginRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "tp_calc_result_margin", "account" );


  /** 
   * <em>calc_result_margin_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getCalcResultMarginId();


  /** 
   * <em>calc_result_margin_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcResultMarginIdIsSet();


  /** 
   * <em>calc_result_margin_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcResultMarginIdIsModified();


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
   * <em>substitute_security_asset_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSubstituteSecurityAsset0();


  /** 
   * <em>substitute_security_asset_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAsset0IsSet();


  /** 
   * <em>substitute_security_asset_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAsset0IsModified();


  /** 
   * <em>substitute_security_asset_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSubstituteSecurityAsset1();


  /** 
   * <em>substitute_security_asset_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAsset1IsSet();


  /** 
   * <em>substitute_security_asset_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAsset1IsModified();


  /** 
   * <em>substitute_security_asset_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSubstituteSecurityAsset2();


  /** 
   * <em>substitute_security_asset_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAsset2IsSet();


  /** 
   * <em>substitute_security_asset_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAsset2IsModified();


  /** 
   * <em>substitute_security_asset_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSubstituteSecurityAsset3();


  /** 
   * <em>substitute_security_asset_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAsset3IsSet();


  /** 
   * <em>substitute_security_asset_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAsset3IsModified();


  /** 
   * <em>substitute_security_asset_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSubstituteSecurityAsset4();


  /** 
   * <em>substitute_security_asset_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAsset4IsSet();


  /** 
   * <em>substitute_security_asset_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAsset4IsModified();


  /** 
   * <em>substitute_security_asset_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSubstituteSecurityAsset5();


  /** 
   * <em>substitute_security_asset_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAsset5IsSet();


  /** 
   * <em>substitute_security_asset_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAsset5IsModified();


  /** 
   * <em>contract_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAmount0();


  /** 
   * <em>contract_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmount0IsSet();


  /** 
   * <em>contract_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmount0IsModified();


  /** 
   * <em>contract_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAmount1();


  /** 
   * <em>contract_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmount1IsSet();


  /** 
   * <em>contract_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmount1IsModified();


  /** 
   * <em>contract_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAmount2();


  /** 
   * <em>contract_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmount2IsSet();


  /** 
   * <em>contract_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmount2IsModified();


  /** 
   * <em>contract_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAmount3();


  /** 
   * <em>contract_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmount3IsSet();


  /** 
   * <em>contract_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmount3IsModified();


  /** 
   * <em>contract_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAmount4();


  /** 
   * <em>contract_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmount4IsSet();


  /** 
   * <em>contract_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmount4IsModified();


  /** 
   * <em>contract_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAmount5();


  /** 
   * <em>contract_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmount5IsSet();


  /** 
   * <em>contract_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmount5IsModified();


  /** 
   * <em>day_repay_contract_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayContractAmount0();


  /** 
   * <em>day_repay_contract_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayContractAmount0IsSet();


  /** 
   * <em>day_repay_contract_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayContractAmount0IsModified();


  /** 
   * <em>day_repay_contract_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayContractAmount1();


  /** 
   * <em>day_repay_contract_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayContractAmount1IsSet();


  /** 
   * <em>day_repay_contract_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayContractAmount1IsModified();


  /** 
   * <em>day_repay_contract_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayContractAmount2();


  /** 
   * <em>day_repay_contract_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayContractAmount2IsSet();


  /** 
   * <em>day_repay_contract_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayContractAmount2IsModified();


  /** 
   * <em>day_repay_contract_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayContractAmount3();


  /** 
   * <em>day_repay_contract_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayContractAmount3IsSet();


  /** 
   * <em>day_repay_contract_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayContractAmount3IsModified();


  /** 
   * <em>day_repay_contract_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayContractAmount4();


  /** 
   * <em>day_repay_contract_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayContractAmount4IsSet();


  /** 
   * <em>day_repay_contract_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayContractAmount4IsModified();


  /** 
   * <em>day_repay_contract_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayContractAmount5();


  /** 
   * <em>day_repay_contract_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayContractAmount5IsSet();


  /** 
   * <em>day_repay_contract_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayContractAmount5IsModified();


  /** 
   * <em>margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDeposit0();


  /** 
   * <em>margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDeposit0IsSet();


  /** 
   * <em>margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDeposit0IsModified();


  /** 
   * <em>margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDeposit1();


  /** 
   * <em>margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDeposit1IsSet();


  /** 
   * <em>margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDeposit1IsModified();


  /** 
   * <em>margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDeposit2();


  /** 
   * <em>margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDeposit2IsSet();


  /** 
   * <em>margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDeposit2IsModified();


  /** 
   * <em>margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDeposit3();


  /** 
   * <em>margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDeposit3IsSet();


  /** 
   * <em>margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDeposit3IsModified();


  /** 
   * <em>margin_deposit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDeposit4();


  /** 
   * <em>margin_deposit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDeposit4IsSet();


  /** 
   * <em>margin_deposit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDeposit4IsModified();


  /** 
   * <em>margin_deposit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDeposit5();


  /** 
   * <em>margin_deposit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDeposit5IsSet();


  /** 
   * <em>margin_deposit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDeposit5IsModified();


  /** 
   * <em>cash_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashMarginDeposit0();


  /** 
   * <em>cash_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDeposit0IsSet();


  /** 
   * <em>cash_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDeposit0IsModified();


  /** 
   * <em>cash_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashMarginDeposit1();


  /** 
   * <em>cash_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDeposit1IsSet();


  /** 
   * <em>cash_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDeposit1IsModified();


  /** 
   * <em>cash_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashMarginDeposit2();


  /** 
   * <em>cash_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDeposit2IsSet();


  /** 
   * <em>cash_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDeposit2IsModified();


  /** 
   * <em>cash_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashMarginDeposit3();


  /** 
   * <em>cash_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDeposit3IsSet();


  /** 
   * <em>cash_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDeposit3IsModified();


  /** 
   * <em>cash_margin_deposit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashMarginDeposit4();


  /** 
   * <em>cash_margin_deposit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDeposit4IsSet();


  /** 
   * <em>cash_margin_deposit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDeposit4IsModified();


  /** 
   * <em>cash_margin_deposit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashMarginDeposit5();


  /** 
   * <em>cash_margin_deposit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDeposit5IsSet();


  /** 
   * <em>cash_margin_deposit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDeposit5IsModified();


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetProfitLoss();


  /** 
   * <em>contract_asset_profit_loss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitLossIsSet();


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitLossIsModified();


  /** 
   * <em>undeli_contract_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliContractAmount0();


  /** 
   * <em>undeli_contract_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractAmount0IsSet();


  /** 
   * <em>undeli_contract_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractAmount0IsModified();


  /** 
   * <em>undeli_contract_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliContractAmount1();


  /** 
   * <em>undeli_contract_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractAmount1IsSet();


  /** 
   * <em>undeli_contract_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractAmount1IsModified();


  /** 
   * <em>undeli_contract_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliContractAmount2();


  /** 
   * <em>undeli_contract_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractAmount2IsSet();


  /** 
   * <em>undeli_contract_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractAmount2IsModified();


  /** 
   * <em>undeli_contract_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliContractAmount3();


  /** 
   * <em>undeli_contract_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractAmount3IsSet();


  /** 
   * <em>undeli_contract_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractAmount3IsModified();


  /** 
   * <em>undeli_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliMarginDeposit0();


  /** 
   * <em>undeli_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliMarginDeposit0IsSet();


  /** 
   * <em>undeli_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliMarginDeposit0IsModified();


  /** 
   * <em>undeli_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliMarginDeposit1();


  /** 
   * <em>undeli_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliMarginDeposit1IsSet();


  /** 
   * <em>undeli_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliMarginDeposit1IsModified();


  /** 
   * <em>undeli_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliMarginDeposit2();


  /** 
   * <em>undeli_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliMarginDeposit2IsSet();


  /** 
   * <em>undeli_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliMarginDeposit2IsModified();


  /** 
   * <em>undeli_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliMarginDeposit3();


  /** 
   * <em>undeli_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliMarginDeposit3IsSet();


  /** 
   * <em>undeli_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliMarginDeposit3IsModified();


  /** 
   * <em>undeli_cash_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliCashMarginDeposit0();


  /** 
   * <em>undeli_cash_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliCashMarginDeposit0IsSet();


  /** 
   * <em>undeli_cash_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliCashMarginDeposit0IsModified();


  /** 
   * <em>undeli_cash_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliCashMarginDeposit1();


  /** 
   * <em>undeli_cash_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliCashMarginDeposit1IsSet();


  /** 
   * <em>undeli_cash_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliCashMarginDeposit1IsModified();


  /** 
   * <em>undeli_cash_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliCashMarginDeposit2();


  /** 
   * <em>undeli_cash_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliCashMarginDeposit2IsSet();


  /** 
   * <em>undeli_cash_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliCashMarginDeposit2IsModified();


  /** 
   * <em>undeli_cash_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliCashMarginDeposit3();


  /** 
   * <em>undeli_cash_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliCashMarginDeposit3IsSet();


  /** 
   * <em>undeli_cash_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliCashMarginDeposit3IsModified();


  /** 
   * <em>undeli_contract_loss_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliContractLoss0();


  /** 
   * <em>undeli_contract_loss_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractLoss0IsSet();


  /** 
   * <em>undeli_contract_loss_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractLoss0IsModified();


  /** 
   * <em>undeli_contract_loss_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliContractLoss1();


  /** 
   * <em>undeli_contract_loss_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractLoss1IsSet();


  /** 
   * <em>undeli_contract_loss_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractLoss1IsModified();


  /** 
   * <em>undeli_contract_loss_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliContractLoss2();


  /** 
   * <em>undeli_contract_loss_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractLoss2IsSet();


  /** 
   * <em>undeli_contract_loss_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractLoss2IsModified();


  /** 
   * <em>undeli_contract_loss_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliContractLoss3();


  /** 
   * <em>undeli_contract_loss_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractLoss3IsSet();


  /** 
   * <em>undeli_contract_loss_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractLoss3IsModified();


  /** 
   * <em>undeli_contract_profit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliContractProfit0();


  /** 
   * <em>undeli_contract_profit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractProfit0IsSet();


  /** 
   * <em>undeli_contract_profit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractProfit0IsModified();


  /** 
   * <em>undeli_contract_profit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliContractProfit1();


  /** 
   * <em>undeli_contract_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractProfit1IsSet();


  /** 
   * <em>undeli_contract_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractProfit1IsModified();


  /** 
   * <em>undeli_contract_profit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliContractProfit2();


  /** 
   * <em>undeli_contract_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractProfit2IsSet();


  /** 
   * <em>undeli_contract_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractProfit2IsModified();


  /** 
   * <em>undeli_contract_profit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliContractProfit3();


  /** 
   * <em>undeli_contract_profit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractProfit3IsSet();


  /** 
   * <em>undeli_contract_profit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractProfit3IsModified();


  /** 
   * <em>contract_total_cost</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractTotalCost();


  /** 
   * <em>contract_total_cost</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractTotalCostIsSet();


  /** 
   * <em>contract_total_cost</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractTotalCostIsModified();


  /** 
   * <em>mark_to_market_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarkToMarketDiv();


  /** 
   * <em>mark_to_market_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarkToMarketDivIsSet();


  /** 
   * <em>mark_to_market_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarkToMarketDivIsModified();


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
   * <em>contract_asset_profit_loss_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetProfitLoss1();


  /** 
   * <em>contract_asset_profit_loss_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetProfitLoss1IsNull();


  /** 
   * <em>contract_asset_profit_loss_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitLoss1IsSet();


  /** 
   * <em>contract_asset_profit_loss_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitLoss1IsModified();


  /** 
   * <em>contract_asset_profit_loss_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetProfitLoss2();


  /** 
   * <em>contract_asset_profit_loss_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetProfitLoss2IsNull();


  /** 
   * <em>contract_asset_profit_loss_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitLoss2IsSet();


  /** 
   * <em>contract_asset_profit_loss_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitLoss2IsModified();


  /** 
   * <em>contract_asset_profit_loss_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetProfitLoss3();


  /** 
   * <em>contract_asset_profit_loss_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetProfitLoss3IsNull();


  /** 
   * <em>contract_asset_profit_loss_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitLoss3IsSet();


  /** 
   * <em>contract_asset_profit_loss_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitLoss3IsModified();


  /** 
   * <em>contract_asset_profit_loss_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetProfitLoss4();


  /** 
   * <em>contract_asset_profit_loss_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetProfitLoss4IsNull();


  /** 
   * <em>contract_asset_profit_loss_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitLoss4IsSet();


  /** 
   * <em>contract_asset_profit_loss_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitLoss4IsModified();


  /** 
   * <em>contract_asset_profit_loss_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetProfitLoss5();


  /** 
   * <em>contract_asset_profit_loss_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetProfitLoss5IsNull();


  /** 
   * <em>contract_asset_profit_loss_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitLoss5IsSet();


  /** 
   * <em>contract_asset_profit_loss_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitLoss5IsModified();


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


  /** 
   * <em>contract_total_cost_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractTotalCost1();


  /** 
   * <em>contract_total_cost_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractTotalCost1IsNull();


  /** 
   * <em>contract_total_cost_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractTotalCost1IsSet();


  /** 
   * <em>contract_total_cost_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractTotalCost1IsModified();


  /** 
   * <em>contract_total_cost_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractTotalCost2();


  /** 
   * <em>contract_total_cost_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractTotalCost2IsNull();


  /** 
   * <em>contract_total_cost_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractTotalCost2IsSet();


  /** 
   * <em>contract_total_cost_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractTotalCost2IsModified();


  /** 
   * <em>contract_total_cost_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractTotalCost3();


  /** 
   * <em>contract_total_cost_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractTotalCost3IsNull();


  /** 
   * <em>contract_total_cost_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractTotalCost3IsSet();


  /** 
   * <em>contract_total_cost_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractTotalCost3IsModified();


  /** 
   * <em>contract_total_cost_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractTotalCost4();


  /** 
   * <em>contract_total_cost_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractTotalCost4IsNull();


  /** 
   * <em>contract_total_cost_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractTotalCost4IsSet();


  /** 
   * <em>contract_total_cost_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractTotalCost4IsModified();


  /** 
   * <em>contract_total_cost_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractTotalCost5();


  /** 
   * <em>contract_total_cost_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractTotalCost5IsNull();


  /** 
   * <em>contract_total_cost_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractTotalCost5IsSet();


  /** 
   * <em>contract_total_cost_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractTotalCost5IsModified();


}
@
