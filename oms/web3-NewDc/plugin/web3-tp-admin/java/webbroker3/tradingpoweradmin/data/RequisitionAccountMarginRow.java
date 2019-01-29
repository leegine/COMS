head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.57.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	RequisitionAccountMarginRow.java;


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
 * RequisitionAccountMarginRowインタフェースは変更不可でリードオンリーである<em>requisition_account_margin</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link RequisitionAccountMarginRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RequisitionAccountMarginPK 
 */
public interface RequisitionAccountMarginRow extends Row {


  /** 
   * この{@@link RequisitionAccountMarginRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "requisition_account_margin", "session" );


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
   * <em>debit_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDebitAmount0();


  /** 
   * <em>debit_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmount0IsSet();


  /** 
   * <em>debit_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmount0IsModified();


  /** 
   * <em>debit_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDebitAmount1();


  /** 
   * <em>debit_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmount1IsSet();


  /** 
   * <em>debit_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmount1IsModified();


  /** 
   * <em>debit_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDebitAmount2();


  /** 
   * <em>debit_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmount2IsSet();


  /** 
   * <em>debit_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmount2IsModified();


  /** 
   * <em>debit_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDebitAmount3();


  /** 
   * <em>debit_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmount3IsSet();


  /** 
   * <em>debit_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmount3IsModified();


  /** 
   * <em>debit_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDebitAmount4();


  /** 
   * <em>debit_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmount4IsSet();


  /** 
   * <em>debit_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmount4IsModified();


  /** 
   * <em>debit_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDebitAmount5();


  /** 
   * <em>debit_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmount5IsSet();


  /** 
   * <em>debit_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmount5IsModified();


  /** 
   * <em>special_debit_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSpecialDebitAmount0();


  /** 
   * <em>special_debit_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmount0IsSet();


  /** 
   * <em>special_debit_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmount0IsModified();


  /** 
   * <em>special_debit_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSpecialDebitAmount1();


  /** 
   * <em>special_debit_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmount1IsSet();


  /** 
   * <em>special_debit_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmount1IsModified();


  /** 
   * <em>special_debit_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSpecialDebitAmount2();


  /** 
   * <em>special_debit_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmount2IsSet();


  /** 
   * <em>special_debit_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmount2IsModified();


  /** 
   * <em>special_debit_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSpecialDebitAmount3();


  /** 
   * <em>special_debit_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmount3IsSet();


  /** 
   * <em>special_debit_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmount3IsModified();


  /** 
   * <em>special_debit_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSpecialDebitAmount4();


  /** 
   * <em>special_debit_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmount4IsSet();


  /** 
   * <em>special_debit_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmount4IsModified();


  /** 
   * <em>special_debit_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSpecialDebitAmount5();


  /** 
   * <em>special_debit_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmount5IsSet();


  /** 
   * <em>special_debit_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmount5IsModified();


  /** 
   * <em>receipt_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getReceiptMarginDeposit0();


  /** 
   * <em>receipt_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptMarginDeposit0IsSet();


  /** 
   * <em>receipt_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptMarginDeposit0IsModified();


  /** 
   * <em>receipt_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getReceiptMarginDeposit1();


  /** 
   * <em>receipt_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptMarginDeposit1IsSet();


  /** 
   * <em>receipt_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptMarginDeposit1IsModified();


  /** 
   * <em>receipt_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getReceiptMarginDeposit2();


  /** 
   * <em>receipt_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptMarginDeposit2IsSet();


  /** 
   * <em>receipt_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptMarginDeposit2IsModified();


  /** 
   * <em>receipt_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getReceiptMarginDeposit3();


  /** 
   * <em>receipt_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptMarginDeposit3IsSet();


  /** 
   * <em>receipt_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptMarginDeposit3IsModified();


  /** 
   * <em>receipt_margin_deposit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getReceiptMarginDeposit4();


  /** 
   * <em>receipt_margin_deposit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptMarginDeposit4IsSet();


  /** 
   * <em>receipt_margin_deposit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptMarginDeposit4IsModified();


  /** 
   * <em>receipt_margin_deposit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getReceiptMarginDeposit5();


  /** 
   * <em>receipt_margin_deposit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptMarginDeposit5IsSet();


  /** 
   * <em>receipt_margin_deposit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptMarginDeposit5IsModified();


  /** 
   * <em>margin_maintenance_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginMaintenanceAmount0();


  /** 
   * <em>margin_maintenance_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceAmount0IsSet();


  /** 
   * <em>margin_maintenance_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceAmount0IsModified();


  /** 
   * <em>margin_maintenance_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginMaintenanceAmount1();


  /** 
   * <em>margin_maintenance_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceAmount1IsSet();


  /** 
   * <em>margin_maintenance_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceAmount1IsModified();


  /** 
   * <em>margin_maintenance_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginMaintenanceAmount2();


  /** 
   * <em>margin_maintenance_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceAmount2IsSet();


  /** 
   * <em>margin_maintenance_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceAmount2IsModified();


  /** 
   * <em>margin_maintenance_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginMaintenanceAmount3();


  /** 
   * <em>margin_maintenance_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceAmount3IsSet();


  /** 
   * <em>margin_maintenance_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceAmount3IsModified();


  /** 
   * <em>margin_maintenance_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginMaintenanceAmount4();


  /** 
   * <em>margin_maintenance_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceAmount4IsSet();


  /** 
   * <em>margin_maintenance_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceAmount4IsModified();


  /** 
   * <em>margin_maintenance_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginMaintenanceAmount5();


  /** 
   * <em>margin_maintenance_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceAmount5IsSet();


  /** 
   * <em>margin_maintenance_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceAmount5IsModified();


  /** 
   * <em>margin_deposit_rate_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDepositRate0();


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
   * <em>margin_deposit_rate_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDepositRate5();


  /** 
   * <em>margin_deposit_rate_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRate5IsSet();


  /** 
   * <em>margin_deposit_rate_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRate5IsModified();


  /** 
   * <em>margin_claimed_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginClaimedAmount0();


  /** 
   * <em>margin_claimed_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginClaimedAmount0IsSet();


  /** 
   * <em>margin_claimed_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginClaimedAmount0IsModified();


  /** 
   * <em>margin_claimed_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginClaimedAmount1();


  /** 
   * <em>margin_claimed_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginClaimedAmount1IsSet();


  /** 
   * <em>margin_claimed_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginClaimedAmount1IsModified();


  /** 
   * <em>margin_claimed_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginClaimedAmount2();


  /** 
   * <em>margin_claimed_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginClaimedAmount2IsSet();


  /** 
   * <em>margin_claimed_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginClaimedAmount2IsModified();


  /** 
   * <em>margin_claimed_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginClaimedAmount3();


  /** 
   * <em>margin_claimed_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginClaimedAmount3IsSet();


  /** 
   * <em>margin_claimed_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginClaimedAmount3IsModified();


  /** 
   * <em>margin_claimed_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginClaimedAmount4();


  /** 
   * <em>margin_claimed_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginClaimedAmount4IsSet();


  /** 
   * <em>margin_claimed_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginClaimedAmount4IsModified();


  /** 
   * <em>margin_claimed_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginClaimedAmount5();


  /** 
   * <em>margin_claimed_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginClaimedAmount5IsSet();


  /** 
   * <em>margin_claimed_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginClaimedAmount5IsModified();


}
@
