head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.57.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	PaymentRequisitionEquityRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpoweradmin.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * PaymentRequisitionEquityRowインタフェースは変更不可でリードオンリーである<em>payment_requisition_equity</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link PaymentRequisitionEquityRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PaymentRequisitionEquityPK 
 */
public interface PaymentRequisitionEquityRow extends Row {


  /** 
   * この{@@link PaymentRequisitionEquityRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "payment_requisition_equity", "session" );


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
   * <em>family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFamilyName();


  /** 
   * <em>family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNameIsSet();


  /** 
   * <em>family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNameIsModified();


  /** 
   * <em>account_attribute</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountAttribute();


  /** 
   * <em>account_attribute</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountAttributeIsSet();


  /** 
   * <em>account_attribute</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountAttributeIsModified();


  /** 
   * <em>sonar_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarTraderCode();


  /** 
   * <em>sonar_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeIsSet();


  /** 
   * <em>sonar_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeIsModified();


  /** 
   * <em>calc_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getCalcDate();


  /** 
   * <em>calc_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcDateIsSet();


  /** 
   * <em>calc_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcDateIsModified();


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
   * <em>ifo_open_position_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoOpenPositionStop();


  /** 
   * <em>ifo_open_position_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoOpenPositionStopIsSet();


  /** 
   * <em>ifo_open_position_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoOpenPositionStopIsModified();


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
   * <em>payment_requisition_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentRequisitionAmount0();


  /** 
   * <em>payment_requisition_amount_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentRequisitionAmount0IsNull();


  /** 
   * <em>payment_requisition_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionAmount0IsSet();


  /** 
   * <em>payment_requisition_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionAmount0IsModified();


  /** 
   * <em>payment_requisition_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentRequisitionAmount1();


  /** 
   * <em>payment_requisition_amount_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentRequisitionAmount1IsNull();


  /** 
   * <em>payment_requisition_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionAmount1IsSet();


  /** 
   * <em>payment_requisition_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionAmount1IsModified();


  /** 
   * <em>payment_requisition_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentRequisitionAmount2();


  /** 
   * <em>payment_requisition_amount_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentRequisitionAmount2IsNull();


  /** 
   * <em>payment_requisition_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionAmount2IsSet();


  /** 
   * <em>payment_requisition_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionAmount2IsModified();


  /** 
   * <em>payment_requisition_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentRequisitionAmount3();


  /** 
   * <em>payment_requisition_amount_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentRequisitionAmount3IsNull();


  /** 
   * <em>payment_requisition_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionAmount3IsSet();


  /** 
   * <em>payment_requisition_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionAmount3IsModified();


  /** 
   * <em>payment_requisition_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentRequisitionAmount4();


  /** 
   * <em>payment_requisition_amount_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentRequisitionAmount4IsNull();


  /** 
   * <em>payment_requisition_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionAmount4IsSet();


  /** 
   * <em>payment_requisition_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionAmount4IsModified();


  /** 
   * <em>payment_requisition_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentRequisitionAmount5();


  /** 
   * <em>payment_requisition_amount_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentRequisitionAmount5IsNull();


  /** 
   * <em>payment_requisition_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionAmount5IsSet();


  /** 
   * <em>payment_requisition_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionAmount5IsModified();


  /** 
   * <em>payment_requisition_division_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentRequisitionDivision0();


  /** 
   * <em>payment_requisition_division_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentRequisitionDivision0IsNull();


  /** 
   * <em>payment_requisition_division_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionDivision0IsSet();


  /** 
   * <em>payment_requisition_division_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionDivision0IsModified();


  /** 
   * <em>payment_requisition_division_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentRequisitionDivision1();


  /** 
   * <em>payment_requisition_division_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentRequisitionDivision1IsNull();


  /** 
   * <em>payment_requisition_division_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionDivision1IsSet();


  /** 
   * <em>payment_requisition_division_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionDivision1IsModified();


  /** 
   * <em>payment_requisition_division_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentRequisitionDivision2();


  /** 
   * <em>payment_requisition_division_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentRequisitionDivision2IsNull();


  /** 
   * <em>payment_requisition_division_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionDivision2IsSet();


  /** 
   * <em>payment_requisition_division_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionDivision2IsModified();


  /** 
   * <em>payment_requisition_division_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentRequisitionDivision3();


  /** 
   * <em>payment_requisition_division_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentRequisitionDivision3IsNull();


  /** 
   * <em>payment_requisition_division_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionDivision3IsSet();


  /** 
   * <em>payment_requisition_division_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionDivision3IsModified();


  /** 
   * <em>payment_requisition_division_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentRequisitionDivision4();


  /** 
   * <em>payment_requisition_division_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentRequisitionDivision4IsNull();


  /** 
   * <em>payment_requisition_division_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionDivision4IsSet();


  /** 
   * <em>payment_requisition_division_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionDivision4IsModified();


  /** 
   * <em>payment_requisition_division_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentRequisitionDivision5();


  /** 
   * <em>payment_requisition_division_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentRequisitionDivision5IsNull();


  /** 
   * <em>payment_requisition_division_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionDivision5IsSet();


  /** 
   * <em>payment_requisition_division_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentRequisitionDivision5IsModified();


  /** 
   * <em>account_balance_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAccountBalance0();


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
   * @@return doubleの値 
   */
  public double getAccountBalance1();


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
   * @@return doubleの値 
   */
  public double getAccountBalance2();


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
   * @@return doubleの値 
   */
  public double getAccountBalance3();


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
   * @@return doubleの値 
   */
  public double getAccountBalance4();


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
   * <em>account_balance_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAccountBalance5();


  /** 
   * <em>account_balance_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountBalance5IsNull();


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
   * <em>cash_balance0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashBalance0();


  /** 
   * <em>cash_balance0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCashBalance0IsNull();


  /** 
   * <em>cash_balance0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashBalance0IsSet();


  /** 
   * <em>cash_balance0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashBalance0IsModified();


  /** 
   * <em>cash_balance1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashBalance1();


  /** 
   * <em>cash_balance1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCashBalance1IsNull();


  /** 
   * <em>cash_balance1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashBalance1IsSet();


  /** 
   * <em>cash_balance1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashBalance1IsModified();


  /** 
   * <em>cash_balance2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashBalance2();


  /** 
   * <em>cash_balance2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCashBalance2IsNull();


  /** 
   * <em>cash_balance2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashBalance2IsSet();


  /** 
   * <em>cash_balance2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashBalance2IsModified();


  /** 
   * <em>cash_balance3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashBalance3();


  /** 
   * <em>cash_balance3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCashBalance3IsNull();


  /** 
   * <em>cash_balance3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashBalance3IsSet();


  /** 
   * <em>cash_balance3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashBalance3IsModified();


  /** 
   * <em>cash_balance4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashBalance4();


  /** 
   * <em>cash_balance4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCashBalance4IsNull();


  /** 
   * <em>cash_balance4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashBalance4IsSet();


  /** 
   * <em>cash_balance4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashBalance4IsModified();


  /** 
   * <em>cash_balance5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashBalance5();


  /** 
   * <em>cash_balance5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCashBalance5IsNull();


  /** 
   * <em>cash_balance5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashBalance5IsSet();


  /** 
   * <em>cash_balance5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashBalance5IsModified();


  /** 
   * <em>day_trade_restraint_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayTradeRestraint0();


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
   * @@return doubleの値 
   */
  public double getDayTradeRestraint1();


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
   * @@return doubleの値 
   */
  public double getDayTradeRestraint2();


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
   * @@return doubleの値 
   */
  public double getDayTradeRestraint3();


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
   * @@return doubleの値 
   */
  public double getDayTradeRestraint4();


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
   * @@return doubleの値 
   */
  public double getOtherRestraint0();


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
   * @@return doubleの値 
   */
  public double getOtherRestraint1();


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
   * @@return doubleの値 
   */
  public double getOtherRestraint2();


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
   * @@return doubleの値 
   */
  public double getOtherRestraint3();


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
   * @@return doubleの値 
   */
  public double getOtherRestraint4();


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
   * <em>other_restraint_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOtherRestraint5();


  /** 
   * <em>other_restraint_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOtherRestraint5IsNull();


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


}
@
