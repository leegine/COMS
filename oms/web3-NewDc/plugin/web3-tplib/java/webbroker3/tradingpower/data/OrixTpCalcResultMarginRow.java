head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	OrixTpCalcResultMarginRow.java;


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
 * OrixTpCalcResultMarginRowインタフェースは変更不可でリードオンリーである<em>orix_tp_calc_result_margin</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link OrixTpCalcResultMarginRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrixTpCalcResultMarginPK 
 */
public interface OrixTpCalcResultMarginRow extends Row {


  /** 
   * この{@@link OrixTpCalcResultMarginRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "orix_tp_calc_result_margin", "account" );


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
   * <em>work_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getWorkDate();


  /** 
   * <em>work_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWorkDateIsSet();


  /** 
   * <em>work_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWorkDateIsModified();


  /** 
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBranchCode();


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeIsSet();


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeIsModified();


  /** 
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountCode();


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeIsSet();


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeIsModified();


  /** 
   * <em>asset_evaluation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAssetEvaluationDiv();


  /** 
   * <em>asset_evaluation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetEvaluationDivIsSet();


  /** 
   * <em>asset_evaluation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetEvaluationDivIsModified();


  /** 
   * <em>account_balance_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAccountBalance0();


  /** 
   * <em>account_balance_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountBalance0IsNull();


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
   * @@return longの値 
   */
  public long getAccountBalance1();


  /** 
   * <em>account_balance_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountBalance1IsNull();


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
   * @@return longの値 
   */
  public long getAccountBalance2();


  /** 
   * <em>account_balance_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountBalance2IsNull();


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
   * @@return longの値 
   */
  public long getAccountBalance3();


  /** 
   * <em>account_balance_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountBalance3IsNull();


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
   * @@return longの値 
   */
  public long getAccountBalance4();


  /** 
   * <em>account_balance_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountBalance4IsNull();


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
   * <em>today_unexecuted_amount_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getTodayUnexecutedAmount1();


  /** 
   * <em>today_unexecuted_amount_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayUnexecutedAmount1IsNull();


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
   * @@return longの値 
   */
  public long getTodayUnexecutedAmount2();


  /** 
   * <em>today_unexecuted_amount_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayUnexecutedAmount2IsNull();


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
   * @@return longの値 
   */
  public long getTodayUnexecutedAmount3();


  /** 
   * <em>today_unexecuted_amount_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayUnexecutedAmount3IsNull();


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
   * @@return longの値 
   */
  public long getTodayUnexecutedAmount4();


  /** 
   * <em>today_unexecuted_amount_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayUnexecutedAmount4IsNull();


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
   * <em>day_trade_restraint_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getDayTradeRestraint0();


  /** 
   * <em>day_trade_restraint_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayTradeRestraint0IsNull();


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
   * @@return longの値 
   */
  public long getDayTradeRestraint1();


  /** 
   * <em>day_trade_restraint_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayTradeRestraint1IsNull();


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
   * @@return longの値 
   */
  public long getDayTradeRestraint2();


  /** 
   * <em>day_trade_restraint_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayTradeRestraint2IsNull();


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
   * @@return longの値 
   */
  public long getDayTradeRestraint3();


  /** 
   * <em>day_trade_restraint_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayTradeRestraint3IsNull();


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
   * @@return longの値 
   */
  public long getDayTradeRestraint4();


  /** 
   * <em>day_trade_restraint_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayTradeRestraint4IsNull();


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
   * @@return longの値 
   */
  public long getOtherRestraint0();


  /** 
   * <em>other_restraint_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOtherRestraint0IsNull();


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
   * @@return longの値 
   */
  public long getOtherRestraint1();


  /** 
   * <em>other_restraint_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOtherRestraint1IsNull();


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
   * @@return longの値 
   */
  public long getOtherRestraint2();


  /** 
   * <em>other_restraint_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOtherRestraint2IsNull();


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
   * @@return longの値 
   */
  public long getOtherRestraint3();


  /** 
   * <em>other_restraint_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOtherRestraint3IsNull();


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
   * @@return longの値 
   */
  public long getOtherRestraint4();


  /** 
   * <em>other_restraint_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOtherRestraint4IsNull();


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
   * <em>margin_account_balance_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginAccountBalance0();


  /** 
   * <em>margin_account_balance_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginAccountBalance0IsNull();


  /** 
   * <em>margin_account_balance_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccountBalance0IsSet();


  /** 
   * <em>margin_account_balance_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccountBalance0IsModified();


  /** 
   * <em>margin_account_balance_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginAccountBalance1();


  /** 
   * <em>margin_account_balance_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginAccountBalance1IsNull();


  /** 
   * <em>margin_account_balance_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccountBalance1IsSet();


  /** 
   * <em>margin_account_balance_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccountBalance1IsModified();


  /** 
   * <em>margin_account_balance_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginAccountBalance2();


  /** 
   * <em>margin_account_balance_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginAccountBalance2IsNull();


  /** 
   * <em>margin_account_balance_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccountBalance2IsSet();


  /** 
   * <em>margin_account_balance_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccountBalance2IsModified();


  /** 
   * <em>margin_account_balance_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginAccountBalance3();


  /** 
   * <em>margin_account_balance_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginAccountBalance3IsNull();


  /** 
   * <em>margin_account_balance_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccountBalance3IsSet();


  /** 
   * <em>margin_account_balance_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccountBalance3IsModified();


  /** 
   * <em>margin_account_balance_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginAccountBalance4();


  /** 
   * <em>margin_account_balance_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginAccountBalance4IsNull();


  /** 
   * <em>margin_account_balance_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccountBalance4IsSet();


  /** 
   * <em>margin_account_balance_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccountBalance4IsModified();


  /** 
   * <em>substitute_security_asset_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSubstituteSecurityAsset0();


  /** 
   * <em>substitute_security_asset_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubstituteSecurityAsset0IsNull();


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
   * @@return longの値 
   */
  public long getSubstituteSecurityAsset1();


  /** 
   * <em>substitute_security_asset_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubstituteSecurityAsset1IsNull();


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
   * @@return longの値 
   */
  public long getSubstituteSecurityAsset2();


  /** 
   * <em>substitute_security_asset_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubstituteSecurityAsset2IsNull();


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
   * @@return longの値 
   */
  public long getSubstituteSecurityAsset3();


  /** 
   * <em>substitute_security_asset_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubstituteSecurityAsset3IsNull();


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
   * @@return longの値 
   */
  public long getSubstituteSecurityAsset4();


  /** 
   * <em>substitute_security_asset_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubstituteSecurityAsset4IsNull();


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
   * <em>contract_asset_profit_loss</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getContractAssetProfitLoss();


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetProfitLossIsNull();


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
   * <em>contract_total_cost</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getContractTotalCost();


  /** 
   * <em>contract_total_cost</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractTotalCostIsNull();


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
   * <em>undeli_contract_loss_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getUndeliContractLoss0();


  /** 
   * <em>undeli_contract_loss_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUndeliContractLoss0IsNull();


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
   * @@return longの値 
   */
  public long getUndeliContractLoss1();


  /** 
   * <em>undeli_contract_loss_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUndeliContractLoss1IsNull();


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
   * @@return longの値 
   */
  public long getUndeliContractLoss2();


  /** 
   * <em>undeli_contract_loss_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUndeliContractLoss2IsNull();


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
   * @@return longの値 
   */
  public long getUndeliContractLoss3();


  /** 
   * <em>undeli_contract_loss_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUndeliContractLoss3IsNull();


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
   * <em>today_repay_contract_pre_asset</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getTodayRepayContractPreAsset();


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayRepayContractPreAssetIsNull();


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayRepayContractPreAssetIsSet();


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayRepayContractPreAssetIsModified();


  /** 
   * <em>contract_amount_day_repay_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getContractAmountDayRepay0();


  /** 
   * <em>contract_amount_day_repay_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAmountDayRepay0IsNull();


  /** 
   * <em>contract_amount_day_repay_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmountDayRepay0IsSet();


  /** 
   * <em>contract_amount_day_repay_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmountDayRepay0IsModified();


  /** 
   * <em>contract_amount_day_repay_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getContractAmountDayRepay1();


  /** 
   * <em>contract_amount_day_repay_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAmountDayRepay1IsNull();


  /** 
   * <em>contract_amount_day_repay_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmountDayRepay1IsSet();


  /** 
   * <em>contract_amount_day_repay_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmountDayRepay1IsModified();


  /** 
   * <em>contract_amount_day_repay_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getContractAmountDayRepay2();


  /** 
   * <em>contract_amount_day_repay_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAmountDayRepay2IsNull();


  /** 
   * <em>contract_amount_day_repay_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmountDayRepay2IsSet();


  /** 
   * <em>contract_amount_day_repay_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmountDayRepay2IsModified();


  /** 
   * <em>contract_amount_day_repay_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getContractAmountDayRepay3();


  /** 
   * <em>contract_amount_day_repay_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAmountDayRepay3IsNull();


  /** 
   * <em>contract_amount_day_repay_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmountDayRepay3IsSet();


  /** 
   * <em>contract_amount_day_repay_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmountDayRepay3IsModified();


  /** 
   * <em>contract_amount_day_repay_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getContractAmountDayRepay4();


  /** 
   * <em>contract_amount_day_repay_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAmountDayRepay4IsNull();


  /** 
   * <em>contract_amount_day_repay_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmountDayRepay4IsSet();


  /** 
   * <em>contract_amount_day_repay_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmountDayRepay4IsModified();


  /** 
   * <em>margin_power_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginPower0();


  /** 
   * <em>margin_power_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginPower0IsNull();


  /** 
   * <em>margin_power_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginPower0IsSet();


  /** 
   * <em>margin_power_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginPower0IsModified();


  /** 
   * <em>margin_power_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginPower1();


  /** 
   * <em>margin_power_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginPower1IsNull();


  /** 
   * <em>margin_power_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginPower1IsSet();


  /** 
   * <em>margin_power_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginPower1IsModified();


  /** 
   * <em>margin_power_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginPower2();


  /** 
   * <em>margin_power_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginPower2IsNull();


  /** 
   * <em>margin_power_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginPower2IsSet();


  /** 
   * <em>margin_power_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginPower2IsModified();


  /** 
   * <em>margin_power_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginPower3();


  /** 
   * <em>margin_power_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginPower3IsNull();


  /** 
   * <em>margin_power_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginPower3IsSet();


  /** 
   * <em>margin_power_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginPower3IsModified();


  /** 
   * <em>margin_power_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginPower4();


  /** 
   * <em>margin_power_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginPower4IsNull();


  /** 
   * <em>margin_power_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginPower4IsSet();


  /** 
   * <em>margin_power_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginPower4IsModified();


  /** 
   * <em>margin_trading_power_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginTradingPower1();


  /** 
   * <em>margin_trading_power_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginTradingPower1IsNull();


  /** 
   * <em>margin_trading_power_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTradingPower1IsSet();


  /** 
   * <em>margin_trading_power_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTradingPower1IsModified();


  /** 
   * <em>margin_trading_power_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginTradingPower2();


  /** 
   * <em>margin_trading_power_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginTradingPower2IsNull();


  /** 
   * <em>margin_trading_power_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTradingPower2IsSet();


  /** 
   * <em>margin_trading_power_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTradingPower2IsModified();


  /** 
   * <em>margin_trading_power_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginTradingPower3();


  /** 
   * <em>margin_trading_power_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginTradingPower3IsNull();


  /** 
   * <em>margin_trading_power_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTradingPower3IsSet();


  /** 
   * <em>margin_trading_power_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTradingPower3IsModified();


  /** 
   * <em>margin_trading_power_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginTradingPower4();


  /** 
   * <em>margin_trading_power_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginTradingPower4IsNull();


  /** 
   * <em>margin_trading_power_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTradingPower4IsSet();


  /** 
   * <em>margin_trading_power_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTradingPower4IsModified();


  /** 
   * <em>margin_deposit_rate_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDepositRate0();


  /** 
   * <em>margin_deposit_rate_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginDepositRate0IsNull();


  /** 
   * <em>margin_deposit_rate_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRate0IsSet();


  /** 
   * <em>margin_deposit_rate_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRate0IsModified();


  /** 
   * <em>margin_deposit_rate_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDepositRate1();


  /** 
   * <em>margin_deposit_rate_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginDepositRate1IsNull();


  /** 
   * <em>margin_deposit_rate_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRate1IsSet();


  /** 
   * <em>margin_deposit_rate_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRate1IsModified();


  /** 
   * <em>margin_deposit_rate_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDepositRate2();


  /** 
   * <em>margin_deposit_rate_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginDepositRate2IsNull();


  /** 
   * <em>margin_deposit_rate_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRate2IsSet();


  /** 
   * <em>margin_deposit_rate_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRate2IsModified();


  /** 
   * <em>margin_deposit_rate_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDepositRate3();


  /** 
   * <em>margin_deposit_rate_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginDepositRate3IsNull();


  /** 
   * <em>margin_deposit_rate_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRate3IsSet();


  /** 
   * <em>margin_deposit_rate_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRate3IsModified();


  /** 
   * <em>margin_deposit_rate_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDepositRate4();


  /** 
   * <em>margin_deposit_rate_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginDepositRate4IsNull();


  /** 
   * <em>margin_deposit_rate_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRate4IsSet();


  /** 
   * <em>margin_deposit_rate_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRate4IsModified();


  /** 
   * <em>act_rec_trading_power_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getActRecTradingPower3();


  /** 
   * <em>act_rec_trading_power_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getActRecTradingPower3IsNull();


  /** 
   * <em>act_rec_trading_power_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getActRecTradingPower3IsSet();


  /** 
   * <em>act_rec_trading_power_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getActRecTradingPower3IsModified();


  /** 
   * <em>act_rec_trading_power_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getActRecTradingPower4();


  /** 
   * <em>act_rec_trading_power_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getActRecTradingPower4IsNull();


  /** 
   * <em>act_rec_trading_power_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getActRecTradingPower4IsSet();


  /** 
   * <em>act_rec_trading_power_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getActRecTradingPower4IsModified();


  /** 
   * <em>act_rec_trading_power_4_dash</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getActRecTradingPower4Dash();


  /** 
   * <em>act_rec_trading_power_4_dash</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getActRecTradingPower4DashIsNull();


  /** 
   * <em>act_rec_trading_power_4_dash</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getActRecTradingPower4DashIsSet();


  /** 
   * <em>act_rec_trading_power_4_dash</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getActRecTradingPower4DashIsModified();


  /** 
   * <em>equity_trading_power_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getEquityTradingPower3();


  /** 
   * <em>equity_trading_power_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getEquityTradingPower3IsNull();


  /** 
   * <em>equity_trading_power_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityTradingPower3IsSet();


  /** 
   * <em>equity_trading_power_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityTradingPower3IsModified();


  /** 
   * <em>equity_trading_power_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getEquityTradingPower4();


  /** 
   * <em>equity_trading_power_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getEquityTradingPower4IsNull();


  /** 
   * <em>equity_trading_power_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityTradingPower4IsSet();


  /** 
   * <em>equity_trading_power_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityTradingPower4IsModified();


  /** 
   * <em>equity_trading_power_4_dash</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getEquityTradingPower4Dash();


  /** 
   * <em>equity_trading_power_4_dash</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getEquityTradingPower4DashIsNull();


  /** 
   * <em>equity_trading_power_4_dash</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityTradingPower4DashIsSet();


  /** 
   * <em>equity_trading_power_4_dash</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityTradingPower4DashIsModified();


  /** 
   * <em>undeli_contract_amount_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getUndeliContractAmount0();


  /** 
   * <em>undeli_contract_amount_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUndeliContractAmount0IsNull();


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
   * @@return longの値 
   */
  public long getUndeliContractAmount1();


  /** 
   * <em>undeli_contract_amount_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUndeliContractAmount1IsNull();


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
   * @@return longの値 
   */
  public long getUndeliContractAmount2();


  /** 
   * <em>undeli_contract_amount_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUndeliContractAmount2IsNull();


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
   * @@return longの値 
   */
  public long getUndeliContractAmount3();


  /** 
   * <em>undeli_contract_amount_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUndeliContractAmount3IsNull();


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
   * <em>margin_draw_power_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginDrawPower0();


  /** 
   * <em>margin_draw_power_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginDrawPower0IsNull();


  /** 
   * <em>margin_draw_power_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDrawPower0IsSet();


  /** 
   * <em>margin_draw_power_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDrawPower0IsModified();


  /** 
   * <em>margin_draw_power_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginDrawPower1();


  /** 
   * <em>margin_draw_power_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginDrawPower1IsNull();


  /** 
   * <em>margin_draw_power_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDrawPower1IsSet();


  /** 
   * <em>margin_draw_power_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDrawPower1IsModified();


  /** 
   * <em>margin_draw_power_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginDrawPower2();


  /** 
   * <em>margin_draw_power_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginDrawPower2IsNull();


  /** 
   * <em>margin_draw_power_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDrawPower2IsSet();


  /** 
   * <em>margin_draw_power_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDrawPower2IsModified();


  /** 
   * <em>margin_draw_power_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginDrawPower3();


  /** 
   * <em>margin_draw_power_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginDrawPower3IsNull();


  /** 
   * <em>margin_draw_power_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDrawPower3IsSet();


  /** 
   * <em>margin_draw_power_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDrawPower3IsModified();


  /** 
   * <em>margin_draw_power_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarginDrawPower4();


  /** 
   * <em>margin_draw_power_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginDrawPower4IsNull();


  /** 
   * <em>margin_draw_power_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDrawPower4IsSet();


  /** 
   * <em>margin_draw_power_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDrawPower4IsModified();


  /** 
   * <em>other_trading_power_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getOtherTradingPower1();


  /** 
   * <em>other_trading_power_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOtherTradingPower1IsNull();


  /** 
   * <em>other_trading_power_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherTradingPower1IsSet();


  /** 
   * <em>other_trading_power_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherTradingPower1IsModified();


  /** 
   * <em>other_trading_power_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getOtherTradingPower2();


  /** 
   * <em>other_trading_power_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOtherTradingPower2IsNull();


  /** 
   * <em>other_trading_power_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherTradingPower2IsSet();


  /** 
   * <em>other_trading_power_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherTradingPower2IsModified();


  /** 
   * <em>other_trading_power_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getOtherTradingPower3();


  /** 
   * <em>other_trading_power_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOtherTradingPower3IsNull();


  /** 
   * <em>other_trading_power_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherTradingPower3IsSet();


  /** 
   * <em>other_trading_power_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherTradingPower3IsModified();


  /** 
   * <em>other_trading_power_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getOtherTradingPower4();


  /** 
   * <em>other_trading_power_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOtherTradingPower4IsNull();


  /** 
   * <em>other_trading_power_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherTradingPower4IsSet();


  /** 
   * <em>other_trading_power_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherTradingPower4IsModified();


  /** 
   * <em>demandamount0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getDemandamount0();


  /** 
   * <em>demandamount0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDemandamount0IsNull();


  /** 
   * <em>demandamount0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDemandamount0IsSet();


  /** 
   * <em>demandamount0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDemandamount0IsModified();


  /** 
   * <em>demandamount1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getDemandamount1();


  /** 
   * <em>demandamount1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDemandamount1IsNull();


  /** 
   * <em>demandamount1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDemandamount1IsSet();


  /** 
   * <em>demandamount1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDemandamount1IsModified();


  /** 
   * <em>demandamount2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getDemandamount2();


  /** 
   * <em>demandamount2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDemandamount2IsNull();


  /** 
   * <em>demandamount2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDemandamount2IsSet();


  /** 
   * <em>demandamount2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDemandamount2IsModified();


  /** 
   * <em>demandamount3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getDemandamount3();


  /** 
   * <em>demandamount3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDemandamount3IsNull();


  /** 
   * <em>demandamount3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDemandamount3IsSet();


  /** 
   * <em>demandamount3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDemandamount3IsModified();


  /** 
   * <em>demandamount4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getDemandamount4();


  /** 
   * <em>demandamount4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDemandamount4IsNull();


  /** 
   * <em>demandamount4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDemandamount4IsSet();


  /** 
   * <em>demandamount4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDemandamount4IsModified();


  /** 
   * <em>payment_amount_designate0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getPaymentAmountDesignate0();


  /** 
   * <em>payment_amount_designate0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentAmountDesignate0IsNull();


  /** 
   * <em>payment_amount_designate0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate0IsSet();


  /** 
   * <em>payment_amount_designate0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate0IsModified();


  /** 
   * <em>payment_amount_designate1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getPaymentAmountDesignate1();


  /** 
   * <em>payment_amount_designate1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentAmountDesignate1IsNull();


  /** 
   * <em>payment_amount_designate1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate1IsSet();


  /** 
   * <em>payment_amount_designate1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate1IsModified();


  /** 
   * <em>payment_amount_designate2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getPaymentAmountDesignate2();


  /** 
   * <em>payment_amount_designate2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentAmountDesignate2IsNull();


  /** 
   * <em>payment_amount_designate2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate2IsSet();


  /** 
   * <em>payment_amount_designate2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate2IsModified();


  /** 
   * <em>margin_sec_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginSecProductCode();


  /** 
   * <em>margin_sec_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSecProductCodeIsSet();


  /** 
   * <em>margin_sec_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSecProductCodeIsModified();


  /** 
   * <em>margin_sec_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginSecRate();


  /** 
   * <em>margin_sec_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginSecRateIsNull();


  /** 
   * <em>margin_sec_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSecRateIsSet();


  /** 
   * <em>margin_sec_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSecRateIsModified();


  /** 
   * <em>equity_asset_executed</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getEquityAssetExecuted();


  /** 
   * <em>equity_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getEquityAssetExecutedIsNull();


  /** 
   * <em>equity_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityAssetExecutedIsSet();


  /** 
   * <em>equity_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityAssetExecutedIsModified();


  /** 
   * <em>ruito_asset_executed</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getRuitoAssetExecuted();


  /** 
   * <em>ruito_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRuitoAssetExecutedIsNull();


  /** 
   * <em>ruito_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRuitoAssetExecutedIsSet();


  /** 
   * <em>ruito_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRuitoAssetExecutedIsModified();


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMutualFundAssetExecuted();


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMutualFundAssetExecutedIsNull();


  /** 
   * <em>mutual_fund_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMutualFundAssetExecutedIsSet();


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMutualFundAssetExecutedIsModified();


  /** 
   * <em>bond_asset_executed</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getBondAssetExecuted();


  /** 
   * <em>bond_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBondAssetExecutedIsNull();


  /** 
   * <em>bond_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondAssetExecutedIsSet();


  /** 
   * <em>bond_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondAssetExecutedIsModified();


  /** 
   * <em>trading_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradingStop();


  /** 
   * <em>trading_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingStopIsSet();


  /** 
   * <em>trading_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingStopIsModified();


  /** 
   * <em>margin_open_position_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginOpenPositionStop();


  /** 
   * <em>margin_open_position_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginOpenPositionStopIsSet();


  /** 
   * <em>margin_open_position_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginOpenPositionStopIsModified();


  /** 
   * <em>payment_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPaymentStop();


  /** 
   * <em>payment_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentStopIsSet();


  /** 
   * <em>payment_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentStopIsModified();


  /** 
   * <em>other_trading_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOtherTradingStop();


  /** 
   * <em>other_trading_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherTradingStopIsSet();


  /** 
   * <em>other_trading_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherTradingStopIsModified();


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
