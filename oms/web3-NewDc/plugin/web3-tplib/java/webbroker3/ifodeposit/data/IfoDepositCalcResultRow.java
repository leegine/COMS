head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	IfoDepositCalcResultRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifodeposit.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * IfoDepositCalcResultRowインタフェースは変更不可でリードオンリーである<em>ifo_deposit_calc_result</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link IfoDepositCalcResultRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoDepositCalcResultPK 
 */
public interface IfoDepositCalcResultRow extends Row {


  /** 
   * この{@@link IfoDepositCalcResultRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "ifo_deposit_calc_result", "session" );


  /** 
   * <em>deposit_info_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getDepositInfoId();


  /** 
   * <em>deposit_info_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositInfoIdIsSet();


  /** 
   * <em>deposit_info_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositInfoIdIsModified();


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
   * <em>base_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBaseDate();


  /** 
   * <em>base_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBaseDateIsSet();


  /** 
   * <em>base_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBaseDateIsModified();


  /** 
   * <em>balance_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBalance0();


  /** 
   * <em>balance_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBalance0IsSet();


  /** 
   * <em>balance_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBalance0IsModified();


  /** 
   * <em>balance_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBalance1();


  /** 
   * <em>balance_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBalance1IsSet();


  /** 
   * <em>balance_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBalance1IsModified();


  /** 
   * <em>balance_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBalance2();


  /** 
   * <em>balance_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBalance2IsSet();


  /** 
   * <em>balance_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBalance2IsModified();


  /** 
   * <em>ifo_deposit_balance_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositBalance0();


  /** 
   * <em>ifo_deposit_balance_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositBalance0IsSet();


  /** 
   * <em>ifo_deposit_balance_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositBalance0IsModified();


  /** 
   * <em>ifo_deposit_balance_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositBalance1();


  /** 
   * <em>ifo_deposit_balance_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositBalance1IsSet();


  /** 
   * <em>ifo_deposit_balance_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositBalance1IsModified();


  /** 
   * <em>ifo_deposit_balance_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositBalance2();


  /** 
   * <em>ifo_deposit_balance_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositBalance2IsSet();


  /** 
   * <em>ifo_deposit_balance_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositBalance2IsModified();


  /** 
   * <em>ifo_deposit_balance_f0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositBalanceF0();


  /** 
   * <em>ifo_deposit_balance_f0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositBalanceF0IsSet();


  /** 
   * <em>ifo_deposit_balance_f0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositBalanceF0IsModified();


  /** 
   * <em>ifo_deposit_balance_f1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositBalanceF1();


  /** 
   * <em>ifo_deposit_balance_f1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositBalanceF1IsSet();


  /** 
   * <em>ifo_deposit_balance_f1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositBalanceF1IsModified();


  /** 
   * <em>ifo_deposit_balance_f2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositBalanceF2();


  /** 
   * <em>ifo_deposit_balance_f2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositBalanceF2IsSet();


  /** 
   * <em>ifo_deposit_balance_f2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositBalanceF2IsModified();


  /** 
   * <em>today_transfer_amt_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTodayTransferAmt0();


  /** 
   * <em>today_transfer_amt_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayTransferAmt0IsSet();


  /** 
   * <em>today_transfer_amt_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayTransferAmt0IsModified();


  /** 
   * <em>today_transfer_amt_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTodayTransferAmt1();


  /** 
   * <em>today_transfer_amt_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayTransferAmt1IsSet();


  /** 
   * <em>today_transfer_amt_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayTransferAmt1IsModified();


  /** 
   * <em>today_transfer_amt_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTodayTransferAmt2();


  /** 
   * <em>today_transfer_amt_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayTransferAmt2IsSet();


  /** 
   * <em>today_transfer_amt_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayTransferAmt2IsModified();


  /** 
   * <em>today_fut_settle_profit_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTodayFutSettleProfit0();


  /** 
   * <em>today_fut_settle_profit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayFutSettleProfit0IsSet();


  /** 
   * <em>today_fut_settle_profit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayFutSettleProfit0IsModified();


  /** 
   * <em>today_fut_settle_profit_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTodayFutSettleProfit1();


  /** 
   * <em>today_fut_settle_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayFutSettleProfit1IsSet();


  /** 
   * <em>today_fut_settle_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayFutSettleProfit1IsModified();


  /** 
   * <em>today_fut_settle_profit_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTodayFutSettleProfit2();


  /** 
   * <em>today_fut_settle_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayFutSettleProfit2IsSet();


  /** 
   * <em>today_fut_settle_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayFutSettleProfit2IsModified();


  /** 
   * <em>today_op_net_amt_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTodayOpNetAmt0();


  /** 
   * <em>today_op_net_amt_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayOpNetAmt0IsSet();


  /** 
   * <em>today_op_net_amt_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayOpNetAmt0IsModified();


  /** 
   * <em>today_op_net_amt_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTodayOpNetAmt1();


  /** 
   * <em>today_op_net_amt_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayOpNetAmt1IsSet();


  /** 
   * <em>today_op_net_amt_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayOpNetAmt1IsModified();


  /** 
   * <em>today_op_net_amt_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTodayOpNetAmt2();


  /** 
   * <em>today_op_net_amt_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayOpNetAmt2IsSet();


  /** 
   * <em>today_op_net_amt_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayOpNetAmt2IsModified();


  /** 
   * <em>fut_eval_profit_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFutEvalProfit0();


  /** 
   * <em>fut_eval_profit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFutEvalProfit0IsSet();


  /** 
   * <em>fut_eval_profit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFutEvalProfit0IsModified();


  /** 
   * <em>fut_eval_profit_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFutEvalProfit1();


  /** 
   * <em>fut_eval_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFutEvalProfit1IsSet();


  /** 
   * <em>fut_eval_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFutEvalProfit1IsModified();


  /** 
   * <em>fut_eval_profit_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFutEvalProfit2();


  /** 
   * <em>fut_eval_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFutEvalProfit2IsSet();


  /** 
   * <em>fut_eval_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFutEvalProfit2IsModified();


  /** 
   * <em>long_fut_eval_profit_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLongFutEvalProfit0();


  /** 
   * <em>long_fut_eval_profit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongFutEvalProfit0IsSet();


  /** 
   * <em>long_fut_eval_profit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongFutEvalProfit0IsModified();


  /** 
   * <em>long_fut_eval_profit_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLongFutEvalProfit1();


  /** 
   * <em>long_fut_eval_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongFutEvalProfit1IsSet();


  /** 
   * <em>long_fut_eval_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongFutEvalProfit1IsModified();


  /** 
   * <em>long_fut_eval_profit_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLongFutEvalProfit2();


  /** 
   * <em>long_fut_eval_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongFutEvalProfit2IsSet();


  /** 
   * <em>long_fut_eval_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongFutEvalProfit2IsModified();


  /** 
   * <em>short_fut_eval_profit_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShortFutEvalProfit0();


  /** 
   * <em>short_fut_eval_profit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortFutEvalProfit0IsSet();


  /** 
   * <em>short_fut_eval_profit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortFutEvalProfit0IsModified();


  /** 
   * <em>short_fut_eval_profit_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShortFutEvalProfit1();


  /** 
   * <em>short_fut_eval_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortFutEvalProfit1IsSet();


  /** 
   * <em>short_fut_eval_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortFutEvalProfit1IsModified();


  /** 
   * <em>short_fut_eval_profit_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShortFutEvalProfit2();


  /** 
   * <em>short_fut_eval_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortFutEvalProfit2IsSet();


  /** 
   * <em>short_fut_eval_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortFutEvalProfit2IsModified();


  /** 
   * <em>acceptance_ifo_deposit_bal_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAcceptanceIfoDepositBal0();


  /** 
   * <em>acceptance_ifo_deposit_bal_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptanceIfoDepositBal0IsSet();


  /** 
   * <em>acceptance_ifo_deposit_bal_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptanceIfoDepositBal0IsModified();


  /** 
   * <em>acceptance_ifo_deposit_bal_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAcceptanceIfoDepositBal1();


  /** 
   * <em>acceptance_ifo_deposit_bal_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptanceIfoDepositBal1IsSet();


  /** 
   * <em>acceptance_ifo_deposit_bal_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptanceIfoDepositBal1IsModified();


  /** 
   * <em>acceptance_ifo_deposit_bal_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAcceptanceIfoDepositBal2();


  /** 
   * <em>acceptance_ifo_deposit_bal_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptanceIfoDepositBal2IsSet();


  /** 
   * <em>acceptance_ifo_deposit_bal_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptanceIfoDepositBal2IsModified();


  /** 
   * <em>acceptance_ifo_deposit_bal_f0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAcceptanceIfoDepositBalF0();


  /** 
   * <em>acceptance_ifo_deposit_bal_f0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptanceIfoDepositBalF0IsSet();


  /** 
   * <em>acceptance_ifo_deposit_bal_f0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptanceIfoDepositBalF0IsModified();


  /** 
   * <em>acceptance_ifo_deposit_bal_f1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAcceptanceIfoDepositBalF1();


  /** 
   * <em>acceptance_ifo_deposit_bal_f1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptanceIfoDepositBalF1IsSet();


  /** 
   * <em>acceptance_ifo_deposit_bal_f1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptanceIfoDepositBalF1IsModified();


  /** 
   * <em>acceptance_ifo_deposit_bal_f2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAcceptanceIfoDepositBalF2();


  /** 
   * <em>acceptance_ifo_deposit_bal_f2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptanceIfoDepositBalF2IsSet();


  /** 
   * <em>acceptance_ifo_deposit_bal_f2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptanceIfoDepositBalF2IsModified();


  /** 
   * <em>net_op_value_total_amt_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNetOpValueTotalAmt0();


  /** 
   * <em>net_op_value_total_amt_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetOpValueTotalAmt0IsSet();


  /** 
   * <em>net_op_value_total_amt_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetOpValueTotalAmt0IsModified();


  /** 
   * <em>net_op_value_total_amt_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNetOpValueTotalAmt1();


  /** 
   * <em>net_op_value_total_amt_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetOpValueTotalAmt1IsSet();


  /** 
   * <em>net_op_value_total_amt_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetOpValueTotalAmt1IsModified();


  /** 
   * <em>net_op_value_total_amt_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNetOpValueTotalAmt2();


  /** 
   * <em>net_op_value_total_amt_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetOpValueTotalAmt2IsSet();


  /** 
   * <em>net_op_value_total_amt_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetOpValueTotalAmt2IsModified();


  /** 
   * <em>net_op_value_total_amt_f0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNetOpValueTotalAmtF0();


  /** 
   * <em>net_op_value_total_amt_f0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetOpValueTotalAmtF0IsSet();


  /** 
   * <em>net_op_value_total_amt_f0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetOpValueTotalAmtF0IsModified();


  /** 
   * <em>net_op_value_total_amt_f1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNetOpValueTotalAmtF1();


  /** 
   * <em>net_op_value_total_amt_f1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetOpValueTotalAmtF1IsSet();


  /** 
   * <em>net_op_value_total_amt_f1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetOpValueTotalAmtF1IsModified();


  /** 
   * <em>net_op_value_total_amt_f2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNetOpValueTotalAmtF2();


  /** 
   * <em>net_op_value_total_amt_f2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetOpValueTotalAmtF2IsSet();


  /** 
   * <em>net_op_value_total_amt_f2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetOpValueTotalAmtF2IsModified();


  /** 
   * <em>ifo_deposit_necessary_amt_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositNecessaryAmt0();


  /** 
   * <em>ifo_deposit_necessary_amt_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositNecessaryAmt0IsSet();


  /** 
   * <em>ifo_deposit_necessary_amt_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositNecessaryAmt0IsModified();


  /** 
   * <em>ifo_deposit_necessary_amt_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositNecessaryAmt1();


  /** 
   * <em>ifo_deposit_necessary_amt_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositNecessaryAmt1IsSet();


  /** 
   * <em>ifo_deposit_necessary_amt_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositNecessaryAmt1IsModified();


  /** 
   * <em>ifo_deposit_necessary_amt_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositNecessaryAmt2();


  /** 
   * <em>ifo_deposit_necessary_amt_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositNecessaryAmt2IsSet();


  /** 
   * <em>ifo_deposit_necessary_amt_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositNecessaryAmt2IsModified();


  /** 
   * <em>ifo_deposit_necessary_amt_f0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositNecessaryAmtF0();


  /** 
   * <em>ifo_deposit_necessary_amt_f0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositNecessaryAmtF0IsSet();


  /** 
   * <em>ifo_deposit_necessary_amt_f0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositNecessaryAmtF0IsModified();


  /** 
   * <em>ifo_deposit_necessary_amt_f1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositNecessaryAmtF1();


  /** 
   * <em>ifo_deposit_necessary_amt_f1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositNecessaryAmtF1IsSet();


  /** 
   * <em>ifo_deposit_necessary_amt_f1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositNecessaryAmtF1IsModified();


  /** 
   * <em>ifo_deposit_necessary_amt_f2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositNecessaryAmtF2();


  /** 
   * <em>ifo_deposit_necessary_amt_f2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositNecessaryAmtF2IsSet();


  /** 
   * <em>ifo_deposit_necessary_amt_f2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositNecessaryAmtF2IsModified();


  /** 
   * <em>ifo_deposit_power_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositPower0();


  /** 
   * <em>ifo_deposit_power_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositPower0IsSet();


  /** 
   * <em>ifo_deposit_power_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositPower0IsModified();


  /** 
   * <em>ifo_deposit_power_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositPower1();


  /** 
   * <em>ifo_deposit_power_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositPower1IsSet();


  /** 
   * <em>ifo_deposit_power_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositPower1IsModified();


  /** 
   * <em>ifo_deposit_power_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositPower2();


  /** 
   * <em>ifo_deposit_power_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositPower2IsSet();


  /** 
   * <em>ifo_deposit_power_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositPower2IsModified();


  /** 
   * <em>ifo_deposit_transfer_power_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositTransferPower0();


  /** 
   * <em>ifo_deposit_transfer_power_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositTransferPower0IsSet();


  /** 
   * <em>ifo_deposit_transfer_power_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositTransferPower0IsModified();


  /** 
   * <em>ifo_deposit_transfer_power_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositTransferPower1();


  /** 
   * <em>ifo_deposit_transfer_power_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositTransferPower1IsSet();


  /** 
   * <em>ifo_deposit_transfer_power_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositTransferPower1IsModified();


  /** 
   * <em>ifo_deposit_transfer_power_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositTransferPower2();


  /** 
   * <em>ifo_deposit_transfer_power_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositTransferPower2IsSet();


  /** 
   * <em>ifo_deposit_transfer_power_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositTransferPower2IsModified();


  /** 
   * <em>bull_quantity_nk225_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBullQuantityNk2250();


  /** 
   * <em>bull_quantity_nk225_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBullQuantityNk2250IsSet();


  /** 
   * <em>bull_quantity_nk225_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBullQuantityNk2250IsModified();


  /** 
   * <em>bull_quantity_nk225_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBullQuantityNk2251();


  /** 
   * <em>bull_quantity_nk225_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBullQuantityNk2251IsSet();


  /** 
   * <em>bull_quantity_nk225_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBullQuantityNk2251IsModified();


  /** 
   * <em>bull_quantity_nk225_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBullQuantityNk2252();


  /** 
   * <em>bull_quantity_nk225_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBullQuantityNk2252IsSet();


  /** 
   * <em>bull_quantity_nk225_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBullQuantityNk2252IsModified();


  /** 
   * <em>bear_quantity_nk225_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBearQuantityNk2250();


  /** 
   * <em>bear_quantity_nk225_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBearQuantityNk2250IsSet();


  /** 
   * <em>bear_quantity_nk225_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBearQuantityNk2250IsModified();


  /** 
   * <em>bear_quantity_nk225_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBearQuantityNk2251();


  /** 
   * <em>bear_quantity_nk225_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBearQuantityNk2251IsSet();


  /** 
   * <em>bear_quantity_nk225_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBearQuantityNk2251IsModified();


  /** 
   * <em>bear_quantity_nk225_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBearQuantityNk2252();


  /** 
   * <em>bear_quantity_nk225_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBearQuantityNk2252IsSet();


  /** 
   * <em>bear_quantity_nk225_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBearQuantityNk2252IsModified();


  /** 
   * <em>long_pos_nk225_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLongPosNk2250();


  /** 
   * <em>long_pos_nk225_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongPosNk2250IsSet();


  /** 
   * <em>long_pos_nk225_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongPosNk2250IsModified();


  /** 
   * <em>long_pos_nk225_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLongPosNk2251();


  /** 
   * <em>long_pos_nk225_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongPosNk2251IsSet();


  /** 
   * <em>long_pos_nk225_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongPosNk2251IsModified();


  /** 
   * <em>long_pos_nk225_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLongPosNk2252();


  /** 
   * <em>long_pos_nk225_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongPosNk2252IsSet();


  /** 
   * <em>long_pos_nk225_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongPosNk2252IsModified();


  /** 
   * <em>part_long_pos_nk225_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPartLongPosNk2250();


  /** 
   * <em>part_long_pos_nk225_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartLongPosNk2250IsSet();


  /** 
   * <em>part_long_pos_nk225_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartLongPosNk2250IsModified();


  /** 
   * <em>part_long_pos_nk225_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPartLongPosNk2251();


  /** 
   * <em>part_long_pos_nk225_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartLongPosNk2251IsSet();


  /** 
   * <em>part_long_pos_nk225_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartLongPosNk2251IsModified();


  /** 
   * <em>part_long_pos_nk225_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPartLongPosNk2252();


  /** 
   * <em>part_long_pos_nk225_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartLongPosNk2252IsSet();


  /** 
   * <em>part_long_pos_nk225_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartLongPosNk2252IsModified();


  /** 
   * <em>short_pos_nk225_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShortPosNk2250();


  /** 
   * <em>short_pos_nk225_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortPosNk2250IsSet();


  /** 
   * <em>short_pos_nk225_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortPosNk2250IsModified();


  /** 
   * <em>short_pos_nk225_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShortPosNk2251();


  /** 
   * <em>short_pos_nk225_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortPosNk2251IsSet();


  /** 
   * <em>short_pos_nk225_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortPosNk2251IsModified();


  /** 
   * <em>short_pos_nk225_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShortPosNk2252();


  /** 
   * <em>short_pos_nk225_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortPosNk2252IsSet();


  /** 
   * <em>short_pos_nk225_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortPosNk2252IsModified();


  /** 
   * <em>part_short_pos_nk225_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPartShortPosNk2250();


  /** 
   * <em>part_short_pos_nk225_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartShortPosNk2250IsSet();


  /** 
   * <em>part_short_pos_nk225_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartShortPosNk2250IsModified();


  /** 
   * <em>part_short_pos_nk225_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPartShortPosNk2251();


  /** 
   * <em>part_short_pos_nk225_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartShortPosNk2251IsSet();


  /** 
   * <em>part_short_pos_nk225_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartShortPosNk2251IsModified();


  /** 
   * <em>part_short_pos_nk225_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPartShortPosNk2252();


  /** 
   * <em>part_short_pos_nk225_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartShortPosNk2252IsSet();


  /** 
   * <em>part_short_pos_nk225_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartShortPosNk2252IsModified();


  /** 
   * <em>bull_quantity_nk225_mini_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBullQuantityNk225Mini0();


  /** 
   * <em>bull_quantity_nk225_mini_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBullQuantityNk225Mini0IsSet();


  /** 
   * <em>bull_quantity_nk225_mini_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBullQuantityNk225Mini0IsModified();


  /** 
   * <em>bull_quantity_nk225_mini_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBullQuantityNk225Mini1();


  /** 
   * <em>bull_quantity_nk225_mini_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBullQuantityNk225Mini1IsSet();


  /** 
   * <em>bull_quantity_nk225_mini_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBullQuantityNk225Mini1IsModified();


  /** 
   * <em>bull_quantity_nk225_mini_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBullQuantityNk225Mini2();


  /** 
   * <em>bull_quantity_nk225_mini_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBullQuantityNk225Mini2IsSet();


  /** 
   * <em>bull_quantity_nk225_mini_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBullQuantityNk225Mini2IsModified();


  /** 
   * <em>bear_quantity_nk225_mini_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBearQuantityNk225Mini0();


  /** 
   * <em>bear_quantity_nk225_mini_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBearQuantityNk225Mini0IsSet();


  /** 
   * <em>bear_quantity_nk225_mini_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBearQuantityNk225Mini0IsModified();


  /** 
   * <em>bear_quantity_nk225_mini_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBearQuantityNk225Mini1();


  /** 
   * <em>bear_quantity_nk225_mini_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBearQuantityNk225Mini1IsSet();


  /** 
   * <em>bear_quantity_nk225_mini_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBearQuantityNk225Mini1IsModified();


  /** 
   * <em>bear_quantity_nk225_mini_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBearQuantityNk225Mini2();


  /** 
   * <em>bear_quantity_nk225_mini_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBearQuantityNk225Mini2IsSet();


  /** 
   * <em>bear_quantity_nk225_mini_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBearQuantityNk225Mini2IsModified();


  /** 
   * <em>long_pos_nk225_mini_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLongPosNk225Mini0();


  /** 
   * <em>long_pos_nk225_mini_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongPosNk225Mini0IsSet();


  /** 
   * <em>long_pos_nk225_mini_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongPosNk225Mini0IsModified();


  /** 
   * <em>long_pos_nk225_mini_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLongPosNk225Mini1();


  /** 
   * <em>long_pos_nk225_mini_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongPosNk225Mini1IsSet();


  /** 
   * <em>long_pos_nk225_mini_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongPosNk225Mini1IsModified();


  /** 
   * <em>long_pos_nk225_mini_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLongPosNk225Mini2();


  /** 
   * <em>long_pos_nk225_mini_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongPosNk225Mini2IsSet();


  /** 
   * <em>long_pos_nk225_mini_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLongPosNk225Mini2IsModified();


  /** 
   * <em>part_long_pos_nk225_mini_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPartLongPosNk225Mini0();


  /** 
   * <em>part_long_pos_nk225_mini_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartLongPosNk225Mini0IsSet();


  /** 
   * <em>part_long_pos_nk225_mini_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartLongPosNk225Mini0IsModified();


  /** 
   * <em>part_long_pos_nk225_mini_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPartLongPosNk225Mini1();


  /** 
   * <em>part_long_pos_nk225_mini_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartLongPosNk225Mini1IsSet();


  /** 
   * <em>part_long_pos_nk225_mini_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartLongPosNk225Mini1IsModified();


  /** 
   * <em>part_long_pos_nk225_mini_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPartLongPosNk225Mini2();


  /** 
   * <em>part_long_pos_nk225_mini_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartLongPosNk225Mini2IsSet();


  /** 
   * <em>part_long_pos_nk225_mini_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartLongPosNk225Mini2IsModified();


  /** 
   * <em>short_pos_nk225_mini_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShortPosNk225Mini0();


  /** 
   * <em>short_pos_nk225_mini_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortPosNk225Mini0IsSet();


  /** 
   * <em>short_pos_nk225_mini_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortPosNk225Mini0IsModified();


  /** 
   * <em>short_pos_nk225_mini_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShortPosNk225Mini1();


  /** 
   * <em>short_pos_nk225_mini_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortPosNk225Mini1IsSet();


  /** 
   * <em>short_pos_nk225_mini_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortPosNk225Mini1IsModified();


  /** 
   * <em>short_pos_nk225_mini_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShortPosNk225Mini2();


  /** 
   * <em>short_pos_nk225_mini_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortPosNk225Mini2IsSet();


  /** 
   * <em>short_pos_nk225_mini_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortPosNk225Mini2IsModified();


  /** 
   * <em>part_short_pos_nk225_mini_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPartShortPosNk225Mini0();


  /** 
   * <em>part_short_pos_nk225_mini_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartShortPosNk225Mini0IsSet();


  /** 
   * <em>part_short_pos_nk225_mini_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartShortPosNk225Mini0IsModified();


  /** 
   * <em>part_short_pos_nk225_mini_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPartShortPosNk225Mini1();


  /** 
   * <em>part_short_pos_nk225_mini_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartShortPosNk225Mini1IsSet();


  /** 
   * <em>part_short_pos_nk225_mini_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartShortPosNk225Mini1IsModified();


  /** 
   * <em>part_short_pos_nk225_mini_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPartShortPosNk225Mini2();


  /** 
   * <em>part_short_pos_nk225_mini_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartShortPosNk225Mini2IsSet();


  /** 
   * <em>part_short_pos_nk225_mini_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPartShortPosNk225Mini2IsModified();


  /** 
   * <em>buy_contract_amt_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyContractAmt0();


  /** 
   * <em>buy_contract_amt_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyContractAmt0IsSet();


  /** 
   * <em>buy_contract_amt_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyContractAmt0IsModified();


  /** 
   * <em>buy_contract_amt_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyContractAmt1();


  /** 
   * <em>buy_contract_amt_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyContractAmt1IsSet();


  /** 
   * <em>buy_contract_amt_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyContractAmt1IsModified();


  /** 
   * <em>buy_contract_amt_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyContractAmt2();


  /** 
   * <em>buy_contract_amt_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyContractAmt2IsSet();


  /** 
   * <em>buy_contract_amt_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyContractAmt2IsModified();


  /** 
   * <em>buy_contract_amt_f0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyContractAmtF0();


  /** 
   * <em>buy_contract_amt_f0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyContractAmtF0IsSet();


  /** 
   * <em>buy_contract_amt_f0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyContractAmtF0IsModified();


  /** 
   * <em>buy_contract_amt_f1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyContractAmtF1();


  /** 
   * <em>buy_contract_amt_f1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyContractAmtF1IsSet();


  /** 
   * <em>buy_contract_amt_f1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyContractAmtF1IsModified();


  /** 
   * <em>buy_contract_amt_f2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyContractAmtF2();


  /** 
   * <em>buy_contract_amt_f2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyContractAmtF2IsSet();


  /** 
   * <em>buy_contract_amt_f2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyContractAmtF2IsModified();


  /** 
   * <em>sell_contract_amt_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellContractAmt0();


  /** 
   * <em>sell_contract_amt_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellContractAmt0IsSet();


  /** 
   * <em>sell_contract_amt_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellContractAmt0IsModified();


  /** 
   * <em>sell_contract_amt_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellContractAmt1();


  /** 
   * <em>sell_contract_amt_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellContractAmt1IsSet();


  /** 
   * <em>sell_contract_amt_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellContractAmt1IsModified();


  /** 
   * <em>sell_contract_amt_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellContractAmt2();


  /** 
   * <em>sell_contract_amt_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellContractAmt2IsSet();


  /** 
   * <em>sell_contract_amt_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellContractAmt2IsModified();


  /** 
   * <em>sell_contract_amt_f0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellContractAmtF0();


  /** 
   * <em>sell_contract_amt_f0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellContractAmtF0IsSet();


  /** 
   * <em>sell_contract_amt_f0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellContractAmtF0IsModified();


  /** 
   * <em>sell_contract_amt_f1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellContractAmtF1();


  /** 
   * <em>sell_contract_amt_f1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellContractAmtF1IsSet();


  /** 
   * <em>sell_contract_amt_f1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellContractAmtF1IsModified();


  /** 
   * <em>sell_contract_amt_f2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellContractAmtF2();


  /** 
   * <em>sell_contract_amt_f2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellContractAmtF2IsSet();


  /** 
   * <em>sell_contract_amt_f2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellContractAmtF2IsModified();


  /** 
   * <em>non_pay_amt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNonPayAmt();


  /** 
   * <em>non_pay_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNonPayAmtIsSet();


  /** 
   * <em>non_pay_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNonPayAmtIsModified();


  /** 
   * <em>today_claim_amt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTodayClaimAmt();


  /** 
   * <em>today_claim_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayClaimAmtIsSet();


  /** 
   * <em>today_claim_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayClaimAmtIsModified();


  /** 
   * <em>tomorrow_claim_amt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTomorrowClaimAmt();


  /** 
   * <em>tomorrow_claim_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTomorrowClaimAmtIsSet();


  /** 
   * <em>tomorrow_claim_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTomorrowClaimAmtIsModified();


  /** 
   * <em>delivery_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getDeliveryDate();


  /** 
   * <em>delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveryDateIsSet();


  /** 
   * <em>delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveryDateIsModified();


  /** 
   * <em>ifo_deposit_insufficient_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositInsufficientFlag();


  /** 
   * <em>ifo_deposit_insufficient_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositInsufficientFlagIsSet();


  /** 
   * <em>ifo_deposit_insufficient_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositInsufficientFlagIsModified();


  /** 
   * <em>ifo_deposit_index_nk225</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositIndexNk225();


  /** 
   * <em>ifo_deposit_index_nk225</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositIndexNk225IsSet();


  /** 
   * <em>ifo_deposit_index_nk225</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositIndexNk225IsModified();


  /** 
   * <em>ifo_deposit_index_nk225_mini</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoDepositIndexNk225Mini();


  /** 
   * <em>ifo_deposit_index_nk225_mini</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositIndexNk225MiniIsSet();


  /** 
   * <em>ifo_deposit_index_nk225_mini</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoDepositIndexNk225MiniIsModified();


  /** 
   * <em>after_tomorrow_claim_amt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAfterTomorrowClaimAmt();


  /** 
   * <em>after_tomorrow_claim_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAfterTomorrowClaimAmtIsSet();


  /** 
   * <em>after_tomorrow_claim_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAfterTomorrowClaimAmtIsModified();


  /** 
   * <em>tomorrow_claim_amt_evening</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTomorrowClaimAmtEvening();


  /** 
   * <em>tomorrow_claim_amt_evening</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTomorrowClaimAmtEveningIsSet();


  /** 
   * <em>tomorrow_claim_amt_evening</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTomorrowClaimAmtEveningIsModified();


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getStatus();


  /** 
   * <em>status</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getStatusIsNull();


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsSet();


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsModified();


  /** 
   * <em>error_message</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getErrorMessage();


  /** 
   * <em>error_message</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getErrorMessageIsSet();


  /** 
   * <em>error_message</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getErrorMessageIsModified();


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
