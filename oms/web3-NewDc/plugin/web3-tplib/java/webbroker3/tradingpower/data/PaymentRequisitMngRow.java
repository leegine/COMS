head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.29.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	PaymentRequisitMngRow.java;


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
 * PaymentRequisitMngRowインタフェースは変更不可でリードオンリーである<em>payment_requisit_mng</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link PaymentRequisitMngRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PaymentRequisitMngPK 
 */
public interface PaymentRequisitMngRow extends Row {


  /** 
   * この{@@link PaymentRequisitMngRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "payment_requisit_mng", "account" );


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
   * <em>trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTraderCode();


  /** 
   * <em>trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderCodeIsSet();


  /** 
   * <em>trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderCodeIsModified();


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
   * <em>account_balance</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAccountBalance();


  /** 
   * <em>account_balance</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountBalanceIsNull();


  /** 
   * <em>account_balance</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalanceIsSet();


  /** 
   * <em>account_balance</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalanceIsModified();


  /** 
   * <em>today_executed_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayExecutedAmount();


  /** 
   * <em>today_executed_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayExecutedAmountIsNull();


  /** 
   * <em>today_executed_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmountIsSet();


  /** 
   * <em>today_executed_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayExecutedAmountIsModified();


  /** 
   * <em>today_unexecuted_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayUnexecutedAmount();


  /** 
   * <em>today_unexecuted_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayUnexecutedAmountIsNull();


  /** 
   * <em>today_unexecuted_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmountIsSet();


  /** 
   * <em>today_unexecuted_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayUnexecutedAmountIsModified();


  /** 
   * <em>sonar_cash_margin_deposit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSonarCashMarginDeposit();


  /** 
   * <em>sonar_cash_margin_deposit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSonarCashMarginDepositIsNull();


  /** 
   * <em>sonar_cash_margin_deposit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarCashMarginDepositIsSet();


  /** 
   * <em>sonar_cash_margin_deposit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarCashMarginDepositIsModified();


  /** 
   * <em>transfer_to_margin_deposit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTransferToMarginDeposit();


  /** 
   * <em>transfer_to_margin_deposit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTransferToMarginDepositIsNull();


  /** 
   * <em>transfer_to_margin_deposit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferToMarginDepositIsSet();


  /** 
   * <em>transfer_to_margin_deposit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferToMarginDepositIsModified();


  /** 
   * <em>transfer_from_margin_deposit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTransferFromMarginDeposit();


  /** 
   * <em>transfer_from_margin_deposit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTransferFromMarginDepositIsNull();


  /** 
   * <em>transfer_from_margin_deposit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferFromMarginDepositIsSet();


  /** 
   * <em>transfer_from_margin_deposit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferFromMarginDepositIsModified();


  /** 
   * <em>cash_margin_deposit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashMarginDeposit();


  /** 
   * <em>cash_margin_deposit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCashMarginDepositIsNull();


  /** 
   * <em>cash_margin_deposit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDepositIsSet();


  /** 
   * <em>cash_margin_deposit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDepositIsModified();


  /** 
   * <em>account_cash</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAccountCash();


  /** 
   * <em>account_cash</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountCashIsNull();


  /** 
   * <em>account_cash</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCashIsSet();


  /** 
   * <em>account_cash</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCashIsModified();


  /** 
   * <em>day_trade_restraint</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayTradeRestraint();


  /** 
   * <em>day_trade_restraint</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayTradeRestraintIsNull();


  /** 
   * <em>day_trade_restraint</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraintIsSet();


  /** 
   * <em>day_trade_restraint</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayTradeRestraintIsModified();


  /** 
   * <em>contract_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAmount();


  /** 
   * <em>contract_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAmountIsNull();


  /** 
   * <em>contract_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmountIsSet();


  /** 
   * <em>contract_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAmountIsModified();


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetProfitLoss();


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
   * @@return doubleの値 
   */
  public double getContractTotalCost();


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
   * <em>undeli_contract_loss</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUndeliContractLoss();


  /** 
   * <em>undeli_contract_loss</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUndeliContractLossIsNull();


  /** 
   * <em>undeli_contract_loss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractLossIsSet();


  /** 
   * <em>undeli_contract_loss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUndeliContractLossIsModified();


  /** 
   * <em>substitute_security_asset</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSubstituteSecurityAsset();


  /** 
   * <em>substitute_security_asset</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubstituteSecurityAssetIsNull();


  /** 
   * <em>substitute_security_asset</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAssetIsSet();


  /** 
   * <em>substitute_security_asset</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAssetIsModified();


  /** 
   * <em>margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDepositRate();


  /** 
   * <em>margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginDepositRateIsNull();


  /** 
   * <em>margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRateIsSet();


  /** 
   * <em>margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRateIsModified();


  /** 
   * <em>margin_deposit_rate_prebizdate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDepositRatePrebizdate();


  /** 
   * <em>margin_deposit_rate_prebizdate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginDepositRatePrebizdateIsNull();


  /** 
   * <em>margin_deposit_rate_prebizdate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRatePrebizdateIsSet();


  /** 
   * <em>margin_deposit_rate_prebizdate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRatePrebizdateIsModified();


  /** 
   * <em>substitute_security_asset_pre</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSubstituteSecurityAssetPre();


  /** 
   * <em>substitute_security_asset_pre</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubstituteSecurityAssetPreIsNull();


  /** 
   * <em>substitute_security_asset_pre</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAssetPreIsSet();


  /** 
   * <em>substitute_security_asset_pre</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteSecurityAssetPreIsModified();


  /** 
   * <em>today_repay_contract_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayRepayContractAmount();


  /** 
   * <em>today_repay_contract_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayRepayContractAmountIsNull();


  /** 
   * <em>today_repay_contract_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayRepayContractAmountIsSet();


  /** 
   * <em>today_repay_contract_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayRepayContractAmountIsModified();


  /** 
   * <em>account_balance_non_pay</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAccountBalanceNonPay();


  /** 
   * <em>account_balance_non_pay</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountBalanceNonPayIsNull();


  /** 
   * <em>account_balance_non_pay</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalanceNonPayIsSet();


  /** 
   * <em>account_balance_non_pay</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalanceNonPayIsModified();


  /** 
   * <em>account_balance_non_pay_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getAccountBalanceNonPayDate();


  /** 
   * <em>account_balance_non_pay_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalanceNonPayDateIsSet();


  /** 
   * <em>account_balance_non_pay_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountBalanceNonPayDateIsModified();


  /** 
   * <em>debit_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDebitAmount();


  /** 
   * <em>debit_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDebitAmountIsNull();


  /** 
   * <em>debit_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmountIsSet();


  /** 
   * <em>debit_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDebitAmountIsModified();


  /** 
   * <em>special_debit_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSpecialDebitAmount();


  /** 
   * <em>special_debit_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSpecialDebitAmountIsNull();


  /** 
   * <em>special_debit_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmountIsSet();


  /** 
   * <em>special_debit_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialDebitAmountIsModified();


  /** 
   * <em>lack_account_balance</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLackAccountBalance();


  /** 
   * <em>lack_account_balance</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLackAccountBalanceIsNull();


  /** 
   * <em>lack_account_balance</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLackAccountBalanceIsSet();


  /** 
   * <em>lack_account_balance</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLackAccountBalanceIsModified();


  /** 
   * <em>first_deposit_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getFirstDepositAmount();


  /** 
   * <em>first_deposit_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFirstDepositAmountIsNull();


  /** 
   * <em>first_deposit_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstDepositAmountIsSet();


  /** 
   * <em>first_deposit_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstDepositAmountIsModified();


  /** 
   * <em>first_settlement</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getFirstSettlement();


  /** 
   * <em>first_settlement</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFirstSettlementIsNull();


  /** 
   * <em>first_settlement</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstSettlementIsSet();


  /** 
   * <em>first_settlement</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstSettlementIsModified();


  /** 
   * <em>first_deposit_pass_day</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getFirstDepositPassDay();


  /** 
   * <em>first_deposit_pass_day</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFirstDepositPassDayIsNull();


  /** 
   * <em>first_deposit_pass_day</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstDepositPassDayIsSet();


  /** 
   * <em>first_deposit_pass_day</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstDepositPassDayIsModified();


  /** 
   * <em>first_deposit_occurred_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getFirstDepositOccurredDate();


  /** 
   * <em>first_deposit_occurred_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstDepositOccurredDateIsSet();


  /** 
   * <em>first_deposit_occurred_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstDepositOccurredDateIsModified();


  /** 
   * <em>second_deposit_non_pay</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecondDepositNonPay();


  /** 
   * <em>second_deposit_non_pay</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSecondDepositNonPayIsNull();


  /** 
   * <em>second_deposit_non_pay</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondDepositNonPayIsSet();


  /** 
   * <em>second_deposit_non_pay</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondDepositNonPayIsModified();


  /** 
   * <em>second_settlement_non_pay</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecondSettlementNonPay();


  /** 
   * <em>second_settlement_non_pay</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSecondSettlementNonPayIsNull();


  /** 
   * <em>second_settlement_non_pay</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondSettlementNonPayIsSet();


  /** 
   * <em>second_settlement_non_pay</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondSettlementNonPayIsModified();


  /** 
   * <em>second_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecondDeposit1();


  /** 
   * <em>second_deposit_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSecondDeposit1IsNull();


  /** 
   * <em>second_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondDeposit1IsSet();


  /** 
   * <em>second_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondDeposit1IsModified();


  /** 
   * <em>second_settlement_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecondSettlement1();


  /** 
   * <em>second_settlement_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSecondSettlement1IsNull();


  /** 
   * <em>second_settlement_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondSettlement1IsSet();


  /** 
   * <em>second_settlement_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondSettlement1IsModified();


  /** 
   * <em>second_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecondDeposit2();


  /** 
   * <em>second_deposit_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSecondDeposit2IsNull();


  /** 
   * <em>second_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondDeposit2IsSet();


  /** 
   * <em>second_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondDeposit2IsModified();


  /** 
   * <em>second_settlement_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecondSettlement2();


  /** 
   * <em>second_settlement_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSecondSettlement2IsNull();


  /** 
   * <em>second_settlement_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondSettlement2IsSet();


  /** 
   * <em>second_settlement_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondSettlement2IsModified();


  /** 
   * <em>first_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getFirstDepositRate();


  /** 
   * <em>first_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFirstDepositRateIsNull();


  /** 
   * <em>first_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstDepositRateIsSet();


  /** 
   * <em>first_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstDepositRateIsModified();


  /** 
   * <em>second_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecondDepositRate();


  /** 
   * <em>second_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSecondDepositRateIsNull();


  /** 
   * <em>second_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondDepositRateIsSet();


  /** 
   * <em>second_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondDepositRateIsModified();


  /** 
   * <em>second_deposit_back_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSecondDepositBackRate();


  /** 
   * <em>second_deposit_back_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSecondDepositBackRateIsNull();


  /** 
   * <em>second_deposit_back_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondDepositBackRateIsSet();


  /** 
   * <em>second_deposit_back_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecondDepositBackRateIsModified();


  /** 
   * <em>first_deposit_pass_day_valid</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getFirstDepositPassDayValid();


  /** 
   * <em>first_deposit_pass_day_valid</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFirstDepositPassDayValidIsNull();


  /** 
   * <em>first_deposit_pass_day_valid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstDepositPassDayValidIsSet();


  /** 
   * <em>first_deposit_pass_day_valid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstDepositPassDayValidIsModified();


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
