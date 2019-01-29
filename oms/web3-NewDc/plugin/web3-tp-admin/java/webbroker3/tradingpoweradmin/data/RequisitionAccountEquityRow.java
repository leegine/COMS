head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	RequisitionAccountEquityRow.java;


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
 * RequisitionAccountEquityRowインタフェースは変更不可でリードオンリーである<em>requisition_account_equity</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link RequisitionAccountEquityRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RequisitionAccountEquityPK 
 */
public interface RequisitionAccountEquityRow extends Row {


  /** 
   * この{@@link RequisitionAccountEquityRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "requisition_account_equity", "session" );


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
   * <em>real_account_balance_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getRealAccountBalance0();


  /** 
   * <em>real_account_balance_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealAccountBalance0IsSet();


  /** 
   * <em>real_account_balance_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealAccountBalance0IsModified();


  /** 
   * <em>real_account_balance_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getRealAccountBalance1();


  /** 
   * <em>real_account_balance_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealAccountBalance1IsSet();


  /** 
   * <em>real_account_balance_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealAccountBalance1IsModified();


  /** 
   * <em>real_account_balance_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getRealAccountBalance2();


  /** 
   * <em>real_account_balance_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealAccountBalance2IsSet();


  /** 
   * <em>real_account_balance_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealAccountBalance2IsModified();


  /** 
   * <em>real_account_balance_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getRealAccountBalance3();


  /** 
   * <em>real_account_balance_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealAccountBalance3IsSet();


  /** 
   * <em>real_account_balance_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealAccountBalance3IsModified();


  /** 
   * <em>real_account_balance_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getRealAccountBalance4();


  /** 
   * <em>real_account_balance_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealAccountBalance4IsSet();


  /** 
   * <em>real_account_balance_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealAccountBalance4IsModified();


  /** 
   * <em>real_account_balance_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getRealAccountBalance5();


  /** 
   * <em>real_account_balance_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealAccountBalance5IsSet();


  /** 
   * <em>real_account_balance_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealAccountBalance5IsModified();


  /** 
   * <em>security_asset_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecurityAsset0();


  /** 
   * <em>security_asset_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurityAsset0IsSet();


  /** 
   * <em>security_asset_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurityAsset0IsModified();


  /** 
   * <em>security_asset_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecurityAsset1();


  /** 
   * <em>security_asset_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurityAsset1IsSet();


  /** 
   * <em>security_asset_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurityAsset1IsModified();


  /** 
   * <em>security_asset_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecurityAsset2();


  /** 
   * <em>security_asset_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurityAsset2IsSet();


  /** 
   * <em>security_asset_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurityAsset2IsModified();


  /** 
   * <em>security_asset_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecurityAsset3();


  /** 
   * <em>security_asset_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurityAsset3IsSet();


  /** 
   * <em>security_asset_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurityAsset3IsModified();


  /** 
   * <em>security_asset_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecurityAsset4();


  /** 
   * <em>security_asset_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurityAsset4IsSet();


  /** 
   * <em>security_asset_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurityAsset4IsModified();


  /** 
   * <em>security_asset_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecurityAsset5();


  /** 
   * <em>security_asset_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurityAsset5IsSet();


  /** 
   * <em>security_asset_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurityAsset5IsModified();


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


}
@
