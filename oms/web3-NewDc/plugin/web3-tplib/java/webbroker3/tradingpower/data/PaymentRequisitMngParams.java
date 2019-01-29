head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	PaymentRequisitMngParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * payment_requisit_mngテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link PaymentRequisitMngRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link PaymentRequisitMngParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link PaymentRequisitMngParams}が{@@link PaymentRequisitMngRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PaymentRequisitMngPK 
 * @@see PaymentRequisitMngRow 
 */
public class PaymentRequisitMngParams extends Params implements PaymentRequisitMngRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "payment_requisit_mng";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = PaymentRequisitMngRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return PaymentRequisitMngRow.TYPE;
  }


  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>trader_code</em>カラムの値 
   */
  public  String  trader_code;

  /** 
   * <em>family_name</em>カラムの値 
   */
  public  String  family_name;

  /** 
   * <em>account_attribute</em>カラムの値 
   */
  public  String  account_attribute;

  /** 
   * <em>calc_date</em>カラムの値 
   */
  public  java.sql.Timestamp  calc_date;

  /** 
   * <em>account_balance</em>カラムの値 
   */
  public  Double  account_balance;

  /** 
   * <em>today_executed_amount</em>カラムの値 
   */
  public  Double  today_executed_amount;

  /** 
   * <em>today_unexecuted_amount</em>カラムの値 
   */
  public  Double  today_unexecuted_amount;

  /** 
   * <em>sonar_cash_margin_deposit</em>カラムの値 
   */
  public  Double  sonar_cash_margin_deposit;

  /** 
   * <em>transfer_to_margin_deposit</em>カラムの値 
   */
  public  Double  transfer_to_margin_deposit;

  /** 
   * <em>transfer_from_margin_deposit</em>カラムの値 
   */
  public  Double  transfer_from_margin_deposit;

  /** 
   * <em>cash_margin_deposit</em>カラムの値 
   */
  public  Double  cash_margin_deposit;

  /** 
   * <em>account_cash</em>カラムの値 
   */
  public  Double  account_cash;

  /** 
   * <em>day_trade_restraint</em>カラムの値 
   */
  public  Double  day_trade_restraint;

  /** 
   * <em>contract_amount</em>カラムの値 
   */
  public  Double  contract_amount;

  /** 
   * <em>contract_asset_profit_loss</em>カラムの値 
   */
  public  Double  contract_asset_profit_loss;

  /** 
   * <em>contract_total_cost</em>カラムの値 
   */
  public  Double  contract_total_cost;

  /** 
   * <em>undeli_contract_loss</em>カラムの値 
   */
  public  Double  undeli_contract_loss;

  /** 
   * <em>substitute_security_asset</em>カラムの値 
   */
  public  Double  substitute_security_asset;

  /** 
   * <em>margin_deposit_rate</em>カラムの値 
   */
  public  Double  margin_deposit_rate;

  /** 
   * <em>margin_deposit_rate_prebizdate</em>カラムの値 
   */
  public  Double  margin_deposit_rate_prebizdate;

  /** 
   * <em>substitute_security_asset_pre</em>カラムの値 
   */
  public  Double  substitute_security_asset_pre;

  /** 
   * <em>today_repay_contract_amount</em>カラムの値 
   */
  public  Double  today_repay_contract_amount;

  /** 
   * <em>account_balance_non_pay</em>カラムの値 
   */
  public  Double  account_balance_non_pay;

  /** 
   * <em>account_balance_non_pay_date</em>カラムの値 
   */
  public  java.sql.Timestamp  account_balance_non_pay_date;

  /** 
   * <em>debit_amount</em>カラムの値 
   */
  public  Double  debit_amount;

  /** 
   * <em>special_debit_amount</em>カラムの値 
   */
  public  Double  special_debit_amount;

  /** 
   * <em>lack_account_balance</em>カラムの値 
   */
  public  Double  lack_account_balance;

  /** 
   * <em>first_deposit_amount</em>カラムの値 
   */
  public  Double  first_deposit_amount;

  /** 
   * <em>first_settlement</em>カラムの値 
   */
  public  Double  first_settlement;

  /** 
   * <em>first_deposit_pass_day</em>カラムの値 
   */
  public  Double  first_deposit_pass_day;

  /** 
   * <em>first_deposit_occurred_date</em>カラムの値 
   */
  public  java.sql.Timestamp  first_deposit_occurred_date;

  /** 
   * <em>second_deposit_non_pay</em>カラムの値 
   */
  public  Double  second_deposit_non_pay;

  /** 
   * <em>second_settlement_non_pay</em>カラムの値 
   */
  public  Double  second_settlement_non_pay;

  /** 
   * <em>second_deposit_1</em>カラムの値 
   */
  public  Double  second_deposit_1;

  /** 
   * <em>second_settlement_1</em>カラムの値 
   */
  public  Double  second_settlement_1;

  /** 
   * <em>second_deposit_2</em>カラムの値 
   */
  public  Double  second_deposit_2;

  /** 
   * <em>second_settlement_2</em>カラムの値 
   */
  public  Double  second_settlement_2;

  /** 
   * <em>first_deposit_rate</em>カラムの値 
   */
  public  Double  first_deposit_rate;

  /** 
   * <em>second_deposit_rate</em>カラムの値 
   */
  public  Double  second_deposit_rate;

  /** 
   * <em>second_deposit_back_rate</em>カラムの値 
   */
  public  Double  second_deposit_back_rate;

  /** 
   * <em>first_deposit_pass_day_valid</em>カラムの値 
   */
  public  Double  first_deposit_pass_day_valid;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean family_name_is_set = false;
  private boolean family_name_is_modified = false;
  private boolean account_attribute_is_set = false;
  private boolean account_attribute_is_modified = false;
  private boolean calc_date_is_set = false;
  private boolean calc_date_is_modified = false;
  private boolean account_balance_is_set = false;
  private boolean account_balance_is_modified = false;
  private boolean today_executed_amount_is_set = false;
  private boolean today_executed_amount_is_modified = false;
  private boolean today_unexecuted_amount_is_set = false;
  private boolean today_unexecuted_amount_is_modified = false;
  private boolean sonar_cash_margin_deposit_is_set = false;
  private boolean sonar_cash_margin_deposit_is_modified = false;
  private boolean transfer_to_margin_deposit_is_set = false;
  private boolean transfer_to_margin_deposit_is_modified = false;
  private boolean transfer_from_margin_deposit_is_set = false;
  private boolean transfer_from_margin_deposit_is_modified = false;
  private boolean cash_margin_deposit_is_set = false;
  private boolean cash_margin_deposit_is_modified = false;
  private boolean account_cash_is_set = false;
  private boolean account_cash_is_modified = false;
  private boolean day_trade_restraint_is_set = false;
  private boolean day_trade_restraint_is_modified = false;
  private boolean contract_amount_is_set = false;
  private boolean contract_amount_is_modified = false;
  private boolean contract_asset_profit_loss_is_set = false;
  private boolean contract_asset_profit_loss_is_modified = false;
  private boolean contract_total_cost_is_set = false;
  private boolean contract_total_cost_is_modified = false;
  private boolean undeli_contract_loss_is_set = false;
  private boolean undeli_contract_loss_is_modified = false;
  private boolean substitute_security_asset_is_set = false;
  private boolean substitute_security_asset_is_modified = false;
  private boolean margin_deposit_rate_is_set = false;
  private boolean margin_deposit_rate_is_modified = false;
  private boolean margin_deposit_rate_prebizdate_is_set = false;
  private boolean margin_deposit_rate_prebizdate_is_modified = false;
  private boolean substitute_security_asset_pre_is_set = false;
  private boolean substitute_security_asset_pre_is_modified = false;
  private boolean today_repay_contract_amount_is_set = false;
  private boolean today_repay_contract_amount_is_modified = false;
  private boolean account_balance_non_pay_is_set = false;
  private boolean account_balance_non_pay_is_modified = false;
  private boolean account_balance_non_pay_date_is_set = false;
  private boolean account_balance_non_pay_date_is_modified = false;
  private boolean debit_amount_is_set = false;
  private boolean debit_amount_is_modified = false;
  private boolean special_debit_amount_is_set = false;
  private boolean special_debit_amount_is_modified = false;
  private boolean lack_account_balance_is_set = false;
  private boolean lack_account_balance_is_modified = false;
  private boolean first_deposit_amount_is_set = false;
  private boolean first_deposit_amount_is_modified = false;
  private boolean first_settlement_is_set = false;
  private boolean first_settlement_is_modified = false;
  private boolean first_deposit_pass_day_is_set = false;
  private boolean first_deposit_pass_day_is_modified = false;
  private boolean first_deposit_occurred_date_is_set = false;
  private boolean first_deposit_occurred_date_is_modified = false;
  private boolean second_deposit_non_pay_is_set = false;
  private boolean second_deposit_non_pay_is_modified = false;
  private boolean second_settlement_non_pay_is_set = false;
  private boolean second_settlement_non_pay_is_modified = false;
  private boolean second_deposit_1_is_set = false;
  private boolean second_deposit_1_is_modified = false;
  private boolean second_settlement_1_is_set = false;
  private boolean second_settlement_1_is_modified = false;
  private boolean second_deposit_2_is_set = false;
  private boolean second_deposit_2_is_modified = false;
  private boolean second_settlement_2_is_set = false;
  private boolean second_settlement_2_is_modified = false;
  private boolean first_deposit_rate_is_set = false;
  private boolean first_deposit_rate_is_modified = false;
  private boolean second_deposit_rate_is_set = false;
  private boolean second_deposit_rate_is_modified = false;
  private boolean second_deposit_back_rate_is_set = false;
  private boolean second_deposit_back_rate_is_modified = false;
  private boolean first_deposit_pass_day_valid_is_set = false;
  private boolean first_deposit_pass_day_valid_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "account_id=" + account_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "family_name=" +family_name
      + "," + "account_attribute=" +account_attribute
      + "," + "calc_date=" +calc_date
      + "," + "account_balance=" +account_balance
      + "," + "today_executed_amount=" +today_executed_amount
      + "," + "today_unexecuted_amount=" +today_unexecuted_amount
      + "," + "sonar_cash_margin_deposit=" +sonar_cash_margin_deposit
      + "," + "transfer_to_margin_deposit=" +transfer_to_margin_deposit
      + "," + "transfer_from_margin_deposit=" +transfer_from_margin_deposit
      + "," + "cash_margin_deposit=" +cash_margin_deposit
      + "," + "account_cash=" +account_cash
      + "," + "day_trade_restraint=" +day_trade_restraint
      + "," + "contract_amount=" +contract_amount
      + "," + "contract_asset_profit_loss=" +contract_asset_profit_loss
      + "," + "contract_total_cost=" +contract_total_cost
      + "," + "undeli_contract_loss=" +undeli_contract_loss
      + "," + "substitute_security_asset=" +substitute_security_asset
      + "," + "margin_deposit_rate=" +margin_deposit_rate
      + "," + "margin_deposit_rate_prebizdate=" +margin_deposit_rate_prebizdate
      + "," + "substitute_security_asset_pre=" +substitute_security_asset_pre
      + "," + "today_repay_contract_amount=" +today_repay_contract_amount
      + "," + "account_balance_non_pay=" +account_balance_non_pay
      + "," + "account_balance_non_pay_date=" +account_balance_non_pay_date
      + "," + "debit_amount=" +debit_amount
      + "," + "special_debit_amount=" +special_debit_amount
      + "," + "lack_account_balance=" +lack_account_balance
      + "," + "first_deposit_amount=" +first_deposit_amount
      + "," + "first_settlement=" +first_settlement
      + "," + "first_deposit_pass_day=" +first_deposit_pass_day
      + "," + "first_deposit_occurred_date=" +first_deposit_occurred_date
      + "," + "second_deposit_non_pay=" +second_deposit_non_pay
      + "," + "second_settlement_non_pay=" +second_settlement_non_pay
      + "," + "second_deposit_1=" +second_deposit_1
      + "," + "second_settlement_1=" +second_settlement_1
      + "," + "second_deposit_2=" +second_deposit_2
      + "," + "second_settlement_2=" +second_settlement_2
      + "," + "first_deposit_rate=" +first_deposit_rate
      + "," + "second_deposit_rate=" +second_deposit_rate
      + "," + "second_deposit_back_rate=" +second_deposit_back_rate
      + "," + "first_deposit_pass_day_valid=" +first_deposit_pass_day_valid
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のPaymentRequisitMngParamsオブジェクトを作成します。 
   */
  public PaymentRequisitMngParams() {
    account_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のPaymentRequisitMngRowオブジェクトの値を利用してPaymentRequisitMngParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するPaymentRequisitMngRowオブジェクト 
   */
  public PaymentRequisitMngParams( PaymentRequisitMngRow p_row )
  {
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    family_name = p_row.getFamilyName();
    family_name_is_set = p_row.getFamilyNameIsSet();
    family_name_is_modified = p_row.getFamilyNameIsModified();
    account_attribute = p_row.getAccountAttribute();
    account_attribute_is_set = p_row.getAccountAttributeIsSet();
    account_attribute_is_modified = p_row.getAccountAttributeIsModified();
    calc_date = p_row.getCalcDate();
    calc_date_is_set = p_row.getCalcDateIsSet();
    calc_date_is_modified = p_row.getCalcDateIsModified();
    if ( !p_row.getAccountBalanceIsNull() )
      account_balance = new Double( p_row.getAccountBalance() );
    account_balance_is_set = p_row.getAccountBalanceIsSet();
    account_balance_is_modified = p_row.getAccountBalanceIsModified();
    if ( !p_row.getTodayExecutedAmountIsNull() )
      today_executed_amount = new Double( p_row.getTodayExecutedAmount() );
    today_executed_amount_is_set = p_row.getTodayExecutedAmountIsSet();
    today_executed_amount_is_modified = p_row.getTodayExecutedAmountIsModified();
    if ( !p_row.getTodayUnexecutedAmountIsNull() )
      today_unexecuted_amount = new Double( p_row.getTodayUnexecutedAmount() );
    today_unexecuted_amount_is_set = p_row.getTodayUnexecutedAmountIsSet();
    today_unexecuted_amount_is_modified = p_row.getTodayUnexecutedAmountIsModified();
    if ( !p_row.getSonarCashMarginDepositIsNull() )
      sonar_cash_margin_deposit = new Double( p_row.getSonarCashMarginDeposit() );
    sonar_cash_margin_deposit_is_set = p_row.getSonarCashMarginDepositIsSet();
    sonar_cash_margin_deposit_is_modified = p_row.getSonarCashMarginDepositIsModified();
    if ( !p_row.getTransferToMarginDepositIsNull() )
      transfer_to_margin_deposit = new Double( p_row.getTransferToMarginDeposit() );
    transfer_to_margin_deposit_is_set = p_row.getTransferToMarginDepositIsSet();
    transfer_to_margin_deposit_is_modified = p_row.getTransferToMarginDepositIsModified();
    if ( !p_row.getTransferFromMarginDepositIsNull() )
      transfer_from_margin_deposit = new Double( p_row.getTransferFromMarginDeposit() );
    transfer_from_margin_deposit_is_set = p_row.getTransferFromMarginDepositIsSet();
    transfer_from_margin_deposit_is_modified = p_row.getTransferFromMarginDepositIsModified();
    if ( !p_row.getCashMarginDepositIsNull() )
      cash_margin_deposit = new Double( p_row.getCashMarginDeposit() );
    cash_margin_deposit_is_set = p_row.getCashMarginDepositIsSet();
    cash_margin_deposit_is_modified = p_row.getCashMarginDepositIsModified();
    if ( !p_row.getAccountCashIsNull() )
      account_cash = new Double( p_row.getAccountCash() );
    account_cash_is_set = p_row.getAccountCashIsSet();
    account_cash_is_modified = p_row.getAccountCashIsModified();
    if ( !p_row.getDayTradeRestraintIsNull() )
      day_trade_restraint = new Double( p_row.getDayTradeRestraint() );
    day_trade_restraint_is_set = p_row.getDayTradeRestraintIsSet();
    day_trade_restraint_is_modified = p_row.getDayTradeRestraintIsModified();
    if ( !p_row.getContractAmountIsNull() )
      contract_amount = new Double( p_row.getContractAmount() );
    contract_amount_is_set = p_row.getContractAmountIsSet();
    contract_amount_is_modified = p_row.getContractAmountIsModified();
    if ( !p_row.getContractAssetProfitLossIsNull() )
      contract_asset_profit_loss = new Double( p_row.getContractAssetProfitLoss() );
    contract_asset_profit_loss_is_set = p_row.getContractAssetProfitLossIsSet();
    contract_asset_profit_loss_is_modified = p_row.getContractAssetProfitLossIsModified();
    if ( !p_row.getContractTotalCostIsNull() )
      contract_total_cost = new Double( p_row.getContractTotalCost() );
    contract_total_cost_is_set = p_row.getContractTotalCostIsSet();
    contract_total_cost_is_modified = p_row.getContractTotalCostIsModified();
    if ( !p_row.getUndeliContractLossIsNull() )
      undeli_contract_loss = new Double( p_row.getUndeliContractLoss() );
    undeli_contract_loss_is_set = p_row.getUndeliContractLossIsSet();
    undeli_contract_loss_is_modified = p_row.getUndeliContractLossIsModified();
    if ( !p_row.getSubstituteSecurityAssetIsNull() )
      substitute_security_asset = new Double( p_row.getSubstituteSecurityAsset() );
    substitute_security_asset_is_set = p_row.getSubstituteSecurityAssetIsSet();
    substitute_security_asset_is_modified = p_row.getSubstituteSecurityAssetIsModified();
    if ( !p_row.getMarginDepositRateIsNull() )
      margin_deposit_rate = new Double( p_row.getMarginDepositRate() );
    margin_deposit_rate_is_set = p_row.getMarginDepositRateIsSet();
    margin_deposit_rate_is_modified = p_row.getMarginDepositRateIsModified();
    if ( !p_row.getMarginDepositRatePrebizdateIsNull() )
      margin_deposit_rate_prebizdate = new Double( p_row.getMarginDepositRatePrebizdate() );
    margin_deposit_rate_prebizdate_is_set = p_row.getMarginDepositRatePrebizdateIsSet();
    margin_deposit_rate_prebizdate_is_modified = p_row.getMarginDepositRatePrebizdateIsModified();
    if ( !p_row.getSubstituteSecurityAssetPreIsNull() )
      substitute_security_asset_pre = new Double( p_row.getSubstituteSecurityAssetPre() );
    substitute_security_asset_pre_is_set = p_row.getSubstituteSecurityAssetPreIsSet();
    substitute_security_asset_pre_is_modified = p_row.getSubstituteSecurityAssetPreIsModified();
    if ( !p_row.getTodayRepayContractAmountIsNull() )
      today_repay_contract_amount = new Double( p_row.getTodayRepayContractAmount() );
    today_repay_contract_amount_is_set = p_row.getTodayRepayContractAmountIsSet();
    today_repay_contract_amount_is_modified = p_row.getTodayRepayContractAmountIsModified();
    if ( !p_row.getAccountBalanceNonPayIsNull() )
      account_balance_non_pay = new Double( p_row.getAccountBalanceNonPay() );
    account_balance_non_pay_is_set = p_row.getAccountBalanceNonPayIsSet();
    account_balance_non_pay_is_modified = p_row.getAccountBalanceNonPayIsModified();
    account_balance_non_pay_date = p_row.getAccountBalanceNonPayDate();
    account_balance_non_pay_date_is_set = p_row.getAccountBalanceNonPayDateIsSet();
    account_balance_non_pay_date_is_modified = p_row.getAccountBalanceNonPayDateIsModified();
    if ( !p_row.getDebitAmountIsNull() )
      debit_amount = new Double( p_row.getDebitAmount() );
    debit_amount_is_set = p_row.getDebitAmountIsSet();
    debit_amount_is_modified = p_row.getDebitAmountIsModified();
    if ( !p_row.getSpecialDebitAmountIsNull() )
      special_debit_amount = new Double( p_row.getSpecialDebitAmount() );
    special_debit_amount_is_set = p_row.getSpecialDebitAmountIsSet();
    special_debit_amount_is_modified = p_row.getSpecialDebitAmountIsModified();
    if ( !p_row.getLackAccountBalanceIsNull() )
      lack_account_balance = new Double( p_row.getLackAccountBalance() );
    lack_account_balance_is_set = p_row.getLackAccountBalanceIsSet();
    lack_account_balance_is_modified = p_row.getLackAccountBalanceIsModified();
    if ( !p_row.getFirstDepositAmountIsNull() )
      first_deposit_amount = new Double( p_row.getFirstDepositAmount() );
    first_deposit_amount_is_set = p_row.getFirstDepositAmountIsSet();
    first_deposit_amount_is_modified = p_row.getFirstDepositAmountIsModified();
    if ( !p_row.getFirstSettlementIsNull() )
      first_settlement = new Double( p_row.getFirstSettlement() );
    first_settlement_is_set = p_row.getFirstSettlementIsSet();
    first_settlement_is_modified = p_row.getFirstSettlementIsModified();
    if ( !p_row.getFirstDepositPassDayIsNull() )
      first_deposit_pass_day = new Double( p_row.getFirstDepositPassDay() );
    first_deposit_pass_day_is_set = p_row.getFirstDepositPassDayIsSet();
    first_deposit_pass_day_is_modified = p_row.getFirstDepositPassDayIsModified();
    first_deposit_occurred_date = p_row.getFirstDepositOccurredDate();
    first_deposit_occurred_date_is_set = p_row.getFirstDepositOccurredDateIsSet();
    first_deposit_occurred_date_is_modified = p_row.getFirstDepositOccurredDateIsModified();
    if ( !p_row.getSecondDepositNonPayIsNull() )
      second_deposit_non_pay = new Double( p_row.getSecondDepositNonPay() );
    second_deposit_non_pay_is_set = p_row.getSecondDepositNonPayIsSet();
    second_deposit_non_pay_is_modified = p_row.getSecondDepositNonPayIsModified();
    if ( !p_row.getSecondSettlementNonPayIsNull() )
      second_settlement_non_pay = new Double( p_row.getSecondSettlementNonPay() );
    second_settlement_non_pay_is_set = p_row.getSecondSettlementNonPayIsSet();
    second_settlement_non_pay_is_modified = p_row.getSecondSettlementNonPayIsModified();
    if ( !p_row.getSecondDeposit1IsNull() )
      second_deposit_1 = new Double( p_row.getSecondDeposit1() );
    second_deposit_1_is_set = p_row.getSecondDeposit1IsSet();
    second_deposit_1_is_modified = p_row.getSecondDeposit1IsModified();
    if ( !p_row.getSecondSettlement1IsNull() )
      second_settlement_1 = new Double( p_row.getSecondSettlement1() );
    second_settlement_1_is_set = p_row.getSecondSettlement1IsSet();
    second_settlement_1_is_modified = p_row.getSecondSettlement1IsModified();
    if ( !p_row.getSecondDeposit2IsNull() )
      second_deposit_2 = new Double( p_row.getSecondDeposit2() );
    second_deposit_2_is_set = p_row.getSecondDeposit2IsSet();
    second_deposit_2_is_modified = p_row.getSecondDeposit2IsModified();
    if ( !p_row.getSecondSettlement2IsNull() )
      second_settlement_2 = new Double( p_row.getSecondSettlement2() );
    second_settlement_2_is_set = p_row.getSecondSettlement2IsSet();
    second_settlement_2_is_modified = p_row.getSecondSettlement2IsModified();
    if ( !p_row.getFirstDepositRateIsNull() )
      first_deposit_rate = new Double( p_row.getFirstDepositRate() );
    first_deposit_rate_is_set = p_row.getFirstDepositRateIsSet();
    first_deposit_rate_is_modified = p_row.getFirstDepositRateIsModified();
    if ( !p_row.getSecondDepositRateIsNull() )
      second_deposit_rate = new Double( p_row.getSecondDepositRate() );
    second_deposit_rate_is_set = p_row.getSecondDepositRateIsSet();
    second_deposit_rate_is_modified = p_row.getSecondDepositRateIsModified();
    if ( !p_row.getSecondDepositBackRateIsNull() )
      second_deposit_back_rate = new Double( p_row.getSecondDepositBackRate() );
    second_deposit_back_rate_is_set = p_row.getSecondDepositBackRateIsSet();
    second_deposit_back_rate_is_modified = p_row.getSecondDepositBackRateIsModified();
    if ( !p_row.getFirstDepositPassDayValidIsNull() )
      first_deposit_pass_day_valid = new Double( p_row.getFirstDepositPassDayValid() );
    first_deposit_pass_day_valid_is_set = p_row.getFirstDepositPassDayValidIsSet();
    first_deposit_pass_day_valid_is_modified = p_row.getFirstDepositPassDayValidIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      trader_code_is_set = true;
      trader_code_is_modified = true;
      family_name_is_set = true;
      family_name_is_modified = true;
      account_attribute_is_set = true;
      account_attribute_is_modified = true;
      calc_date_is_set = true;
      calc_date_is_modified = true;
      account_balance_is_set = true;
      account_balance_is_modified = true;
      today_executed_amount_is_set = true;
      today_executed_amount_is_modified = true;
      today_unexecuted_amount_is_set = true;
      today_unexecuted_amount_is_modified = true;
      sonar_cash_margin_deposit_is_set = true;
      sonar_cash_margin_deposit_is_modified = true;
      transfer_to_margin_deposit_is_set = true;
      transfer_to_margin_deposit_is_modified = true;
      transfer_from_margin_deposit_is_set = true;
      transfer_from_margin_deposit_is_modified = true;
      cash_margin_deposit_is_set = true;
      cash_margin_deposit_is_modified = true;
      account_cash_is_set = true;
      account_cash_is_modified = true;
      day_trade_restraint_is_set = true;
      day_trade_restraint_is_modified = true;
      contract_amount_is_set = true;
      contract_amount_is_modified = true;
      contract_asset_profit_loss_is_set = true;
      contract_asset_profit_loss_is_modified = true;
      contract_total_cost_is_set = true;
      contract_total_cost_is_modified = true;
      undeli_contract_loss_is_set = true;
      undeli_contract_loss_is_modified = true;
      substitute_security_asset_is_set = true;
      substitute_security_asset_is_modified = true;
      margin_deposit_rate_is_set = true;
      margin_deposit_rate_is_modified = true;
      margin_deposit_rate_prebizdate_is_set = true;
      margin_deposit_rate_prebizdate_is_modified = true;
      substitute_security_asset_pre_is_set = true;
      substitute_security_asset_pre_is_modified = true;
      today_repay_contract_amount_is_set = true;
      today_repay_contract_amount_is_modified = true;
      account_balance_non_pay_is_set = true;
      account_balance_non_pay_is_modified = true;
      account_balance_non_pay_date_is_set = true;
      account_balance_non_pay_date_is_modified = true;
      debit_amount_is_set = true;
      debit_amount_is_modified = true;
      special_debit_amount_is_set = true;
      special_debit_amount_is_modified = true;
      lack_account_balance_is_set = true;
      lack_account_balance_is_modified = true;
      first_deposit_amount_is_set = true;
      first_deposit_amount_is_modified = true;
      first_settlement_is_set = true;
      first_settlement_is_modified = true;
      first_deposit_pass_day_is_set = true;
      first_deposit_pass_day_is_modified = true;
      first_deposit_occurred_date_is_set = true;
      first_deposit_occurred_date_is_modified = true;
      second_deposit_non_pay_is_set = true;
      second_deposit_non_pay_is_modified = true;
      second_settlement_non_pay_is_set = true;
      second_settlement_non_pay_is_modified = true;
      second_deposit_1_is_set = true;
      second_deposit_1_is_modified = true;
      second_settlement_1_is_set = true;
      second_settlement_1_is_modified = true;
      second_deposit_2_is_set = true;
      second_deposit_2_is_modified = true;
      second_settlement_2_is_set = true;
      second_settlement_2_is_modified = true;
      first_deposit_rate_is_set = true;
      first_deposit_rate_is_modified = true;
      second_deposit_rate_is_set = true;
      second_deposit_rate_is_modified = true;
      second_deposit_back_rate_is_set = true;
      second_deposit_back_rate_is_modified = true;
      first_deposit_pass_day_valid_is_set = true;
      first_deposit_pass_day_valid_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof PaymentRequisitMngRow ) )
       return false;
    return fieldsEqual( (PaymentRequisitMngRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のPaymentRequisitMngRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( PaymentRequisitMngRow row )
  {
    if ( account_id != row.getAccountId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( trader_code == null ) {
      if ( row.getTraderCode() != null )
        return false;
    } else if ( !trader_code.equals( row.getTraderCode() ) ) {
        return false;
    }
    if ( family_name == null ) {
      if ( row.getFamilyName() != null )
        return false;
    } else if ( !family_name.equals( row.getFamilyName() ) ) {
        return false;
    }
    if ( account_attribute == null ) {
      if ( row.getAccountAttribute() != null )
        return false;
    } else if ( !account_attribute.equals( row.getAccountAttribute() ) ) {
        return false;
    }
    if ( calc_date == null ) {
      if ( row.getCalcDate() != null )
        return false;
    } else if ( !calc_date.equals( row.getCalcDate() ) ) {
        return false;
    }
    if ( account_balance == null ) {
      if ( !row.getAccountBalanceIsNull() )
        return false;
    } else if ( row.getAccountBalanceIsNull() || ( account_balance.doubleValue() != row.getAccountBalance() ) ) {
        return false;
    }
    if ( today_executed_amount == null ) {
      if ( !row.getTodayExecutedAmountIsNull() )
        return false;
    } else if ( row.getTodayExecutedAmountIsNull() || ( today_executed_amount.doubleValue() != row.getTodayExecutedAmount() ) ) {
        return false;
    }
    if ( today_unexecuted_amount == null ) {
      if ( !row.getTodayUnexecutedAmountIsNull() )
        return false;
    } else if ( row.getTodayUnexecutedAmountIsNull() || ( today_unexecuted_amount.doubleValue() != row.getTodayUnexecutedAmount() ) ) {
        return false;
    }
    if ( sonar_cash_margin_deposit == null ) {
      if ( !row.getSonarCashMarginDepositIsNull() )
        return false;
    } else if ( row.getSonarCashMarginDepositIsNull() || ( sonar_cash_margin_deposit.doubleValue() != row.getSonarCashMarginDeposit() ) ) {
        return false;
    }
    if ( transfer_to_margin_deposit == null ) {
      if ( !row.getTransferToMarginDepositIsNull() )
        return false;
    } else if ( row.getTransferToMarginDepositIsNull() || ( transfer_to_margin_deposit.doubleValue() != row.getTransferToMarginDeposit() ) ) {
        return false;
    }
    if ( transfer_from_margin_deposit == null ) {
      if ( !row.getTransferFromMarginDepositIsNull() )
        return false;
    } else if ( row.getTransferFromMarginDepositIsNull() || ( transfer_from_margin_deposit.doubleValue() != row.getTransferFromMarginDeposit() ) ) {
        return false;
    }
    if ( cash_margin_deposit == null ) {
      if ( !row.getCashMarginDepositIsNull() )
        return false;
    } else if ( row.getCashMarginDepositIsNull() || ( cash_margin_deposit.doubleValue() != row.getCashMarginDeposit() ) ) {
        return false;
    }
    if ( account_cash == null ) {
      if ( !row.getAccountCashIsNull() )
        return false;
    } else if ( row.getAccountCashIsNull() || ( account_cash.doubleValue() != row.getAccountCash() ) ) {
        return false;
    }
    if ( day_trade_restraint == null ) {
      if ( !row.getDayTradeRestraintIsNull() )
        return false;
    } else if ( row.getDayTradeRestraintIsNull() || ( day_trade_restraint.doubleValue() != row.getDayTradeRestraint() ) ) {
        return false;
    }
    if ( contract_amount == null ) {
      if ( !row.getContractAmountIsNull() )
        return false;
    } else if ( row.getContractAmountIsNull() || ( contract_amount.doubleValue() != row.getContractAmount() ) ) {
        return false;
    }
    if ( contract_asset_profit_loss == null ) {
      if ( !row.getContractAssetProfitLossIsNull() )
        return false;
    } else if ( row.getContractAssetProfitLossIsNull() || ( contract_asset_profit_loss.doubleValue() != row.getContractAssetProfitLoss() ) ) {
        return false;
    }
    if ( contract_total_cost == null ) {
      if ( !row.getContractTotalCostIsNull() )
        return false;
    } else if ( row.getContractTotalCostIsNull() || ( contract_total_cost.doubleValue() != row.getContractTotalCost() ) ) {
        return false;
    }
    if ( undeli_contract_loss == null ) {
      if ( !row.getUndeliContractLossIsNull() )
        return false;
    } else if ( row.getUndeliContractLossIsNull() || ( undeli_contract_loss.doubleValue() != row.getUndeliContractLoss() ) ) {
        return false;
    }
    if ( substitute_security_asset == null ) {
      if ( !row.getSubstituteSecurityAssetIsNull() )
        return false;
    } else if ( row.getSubstituteSecurityAssetIsNull() || ( substitute_security_asset.doubleValue() != row.getSubstituteSecurityAsset() ) ) {
        return false;
    }
    if ( margin_deposit_rate == null ) {
      if ( !row.getMarginDepositRateIsNull() )
        return false;
    } else if ( row.getMarginDepositRateIsNull() || ( margin_deposit_rate.doubleValue() != row.getMarginDepositRate() ) ) {
        return false;
    }
    if ( margin_deposit_rate_prebizdate == null ) {
      if ( !row.getMarginDepositRatePrebizdateIsNull() )
        return false;
    } else if ( row.getMarginDepositRatePrebizdateIsNull() || ( margin_deposit_rate_prebizdate.doubleValue() != row.getMarginDepositRatePrebizdate() ) ) {
        return false;
    }
    if ( substitute_security_asset_pre == null ) {
      if ( !row.getSubstituteSecurityAssetPreIsNull() )
        return false;
    } else if ( row.getSubstituteSecurityAssetPreIsNull() || ( substitute_security_asset_pre.doubleValue() != row.getSubstituteSecurityAssetPre() ) ) {
        return false;
    }
    if ( today_repay_contract_amount == null ) {
      if ( !row.getTodayRepayContractAmountIsNull() )
        return false;
    } else if ( row.getTodayRepayContractAmountIsNull() || ( today_repay_contract_amount.doubleValue() != row.getTodayRepayContractAmount() ) ) {
        return false;
    }
    if ( account_balance_non_pay == null ) {
      if ( !row.getAccountBalanceNonPayIsNull() )
        return false;
    } else if ( row.getAccountBalanceNonPayIsNull() || ( account_balance_non_pay.doubleValue() != row.getAccountBalanceNonPay() ) ) {
        return false;
    }
    if ( account_balance_non_pay_date == null ) {
      if ( row.getAccountBalanceNonPayDate() != null )
        return false;
    } else if ( !account_balance_non_pay_date.equals( row.getAccountBalanceNonPayDate() ) ) {
        return false;
    }
    if ( debit_amount == null ) {
      if ( !row.getDebitAmountIsNull() )
        return false;
    } else if ( row.getDebitAmountIsNull() || ( debit_amount.doubleValue() != row.getDebitAmount() ) ) {
        return false;
    }
    if ( special_debit_amount == null ) {
      if ( !row.getSpecialDebitAmountIsNull() )
        return false;
    } else if ( row.getSpecialDebitAmountIsNull() || ( special_debit_amount.doubleValue() != row.getSpecialDebitAmount() ) ) {
        return false;
    }
    if ( lack_account_balance == null ) {
      if ( !row.getLackAccountBalanceIsNull() )
        return false;
    } else if ( row.getLackAccountBalanceIsNull() || ( lack_account_balance.doubleValue() != row.getLackAccountBalance() ) ) {
        return false;
    }
    if ( first_deposit_amount == null ) {
      if ( !row.getFirstDepositAmountIsNull() )
        return false;
    } else if ( row.getFirstDepositAmountIsNull() || ( first_deposit_amount.doubleValue() != row.getFirstDepositAmount() ) ) {
        return false;
    }
    if ( first_settlement == null ) {
      if ( !row.getFirstSettlementIsNull() )
        return false;
    } else if ( row.getFirstSettlementIsNull() || ( first_settlement.doubleValue() != row.getFirstSettlement() ) ) {
        return false;
    }
    if ( first_deposit_pass_day == null ) {
      if ( !row.getFirstDepositPassDayIsNull() )
        return false;
    } else if ( row.getFirstDepositPassDayIsNull() || ( first_deposit_pass_day.doubleValue() != row.getFirstDepositPassDay() ) ) {
        return false;
    }
    if ( first_deposit_occurred_date == null ) {
      if ( row.getFirstDepositOccurredDate() != null )
        return false;
    } else if ( !first_deposit_occurred_date.equals( row.getFirstDepositOccurredDate() ) ) {
        return false;
    }
    if ( second_deposit_non_pay == null ) {
      if ( !row.getSecondDepositNonPayIsNull() )
        return false;
    } else if ( row.getSecondDepositNonPayIsNull() || ( second_deposit_non_pay.doubleValue() != row.getSecondDepositNonPay() ) ) {
        return false;
    }
    if ( second_settlement_non_pay == null ) {
      if ( !row.getSecondSettlementNonPayIsNull() )
        return false;
    } else if ( row.getSecondSettlementNonPayIsNull() || ( second_settlement_non_pay.doubleValue() != row.getSecondSettlementNonPay() ) ) {
        return false;
    }
    if ( second_deposit_1 == null ) {
      if ( !row.getSecondDeposit1IsNull() )
        return false;
    } else if ( row.getSecondDeposit1IsNull() || ( second_deposit_1.doubleValue() != row.getSecondDeposit1() ) ) {
        return false;
    }
    if ( second_settlement_1 == null ) {
      if ( !row.getSecondSettlement1IsNull() )
        return false;
    } else if ( row.getSecondSettlement1IsNull() || ( second_settlement_1.doubleValue() != row.getSecondSettlement1() ) ) {
        return false;
    }
    if ( second_deposit_2 == null ) {
      if ( !row.getSecondDeposit2IsNull() )
        return false;
    } else if ( row.getSecondDeposit2IsNull() || ( second_deposit_2.doubleValue() != row.getSecondDeposit2() ) ) {
        return false;
    }
    if ( second_settlement_2 == null ) {
      if ( !row.getSecondSettlement2IsNull() )
        return false;
    } else if ( row.getSecondSettlement2IsNull() || ( second_settlement_2.doubleValue() != row.getSecondSettlement2() ) ) {
        return false;
    }
    if ( first_deposit_rate == null ) {
      if ( !row.getFirstDepositRateIsNull() )
        return false;
    } else if ( row.getFirstDepositRateIsNull() || ( first_deposit_rate.doubleValue() != row.getFirstDepositRate() ) ) {
        return false;
    }
    if ( second_deposit_rate == null ) {
      if ( !row.getSecondDepositRateIsNull() )
        return false;
    } else if ( row.getSecondDepositRateIsNull() || ( second_deposit_rate.doubleValue() != row.getSecondDepositRate() ) ) {
        return false;
    }
    if ( second_deposit_back_rate == null ) {
      if ( !row.getSecondDepositBackRateIsNull() )
        return false;
    } else if ( row.getSecondDepositBackRateIsNull() || ( second_deposit_back_rate.doubleValue() != row.getSecondDepositBackRate() ) ) {
        return false;
    }
    if ( first_deposit_pass_day_valid == null ) {
      if ( !row.getFirstDepositPassDayValidIsNull() )
        return false;
    } else if ( row.getFirstDepositPassDayValidIsNull() || ( first_deposit_pass_day_valid.doubleValue() != row.getFirstDepositPassDayValid() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) account_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (family_name!=null? family_name.hashCode(): 0) 
        + (account_attribute!=null? account_attribute.hashCode(): 0) 
        + (calc_date!=null? calc_date.hashCode(): 0) 
        + (account_balance!=null? account_balance.hashCode(): 0) 
        + (today_executed_amount!=null? today_executed_amount.hashCode(): 0) 
        + (today_unexecuted_amount!=null? today_unexecuted_amount.hashCode(): 0) 
        + (sonar_cash_margin_deposit!=null? sonar_cash_margin_deposit.hashCode(): 0) 
        + (transfer_to_margin_deposit!=null? transfer_to_margin_deposit.hashCode(): 0) 
        + (transfer_from_margin_deposit!=null? transfer_from_margin_deposit.hashCode(): 0) 
        + (cash_margin_deposit!=null? cash_margin_deposit.hashCode(): 0) 
        + (account_cash!=null? account_cash.hashCode(): 0) 
        + (day_trade_restraint!=null? day_trade_restraint.hashCode(): 0) 
        + (contract_amount!=null? contract_amount.hashCode(): 0) 
        + (contract_asset_profit_loss!=null? contract_asset_profit_loss.hashCode(): 0) 
        + (contract_total_cost!=null? contract_total_cost.hashCode(): 0) 
        + (undeli_contract_loss!=null? undeli_contract_loss.hashCode(): 0) 
        + (substitute_security_asset!=null? substitute_security_asset.hashCode(): 0) 
        + (margin_deposit_rate!=null? margin_deposit_rate.hashCode(): 0) 
        + (margin_deposit_rate_prebizdate!=null? margin_deposit_rate_prebizdate.hashCode(): 0) 
        + (substitute_security_asset_pre!=null? substitute_security_asset_pre.hashCode(): 0) 
        + (today_repay_contract_amount!=null? today_repay_contract_amount.hashCode(): 0) 
        + (account_balance_non_pay!=null? account_balance_non_pay.hashCode(): 0) 
        + (account_balance_non_pay_date!=null? account_balance_non_pay_date.hashCode(): 0) 
        + (debit_amount!=null? debit_amount.hashCode(): 0) 
        + (special_debit_amount!=null? special_debit_amount.hashCode(): 0) 
        + (lack_account_balance!=null? lack_account_balance.hashCode(): 0) 
        + (first_deposit_amount!=null? first_deposit_amount.hashCode(): 0) 
        + (first_settlement!=null? first_settlement.hashCode(): 0) 
        + (first_deposit_pass_day!=null? first_deposit_pass_day.hashCode(): 0) 
        + (first_deposit_occurred_date!=null? first_deposit_occurred_date.hashCode(): 0) 
        + (second_deposit_non_pay!=null? second_deposit_non_pay.hashCode(): 0) 
        + (second_settlement_non_pay!=null? second_settlement_non_pay.hashCode(): 0) 
        + (second_deposit_1!=null? second_deposit_1.hashCode(): 0) 
        + (second_settlement_1!=null? second_settlement_1.hashCode(): 0) 
        + (second_deposit_2!=null? second_deposit_2.hashCode(): 0) 
        + (second_settlement_2!=null? second_settlement_2.hashCode(): 0) 
        + (first_deposit_rate!=null? first_deposit_rate.hashCode(): 0) 
        + (second_deposit_rate!=null? second_deposit_rate.hashCode(): 0) 
        + (second_deposit_back_rate!=null? second_deposit_back_rate.hashCode(): 0) 
        + (first_deposit_pass_day_valid!=null? first_deposit_pass_day_valid.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !family_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'family_name' must be set before inserting.");
		if ( !account_attribute_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_attribute' must be set before inserting.");
		if ( !calc_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'calc_date' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("account_id",new Long(account_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		map.put("family_name",family_name);
		map.put("account_attribute",account_attribute);
		map.put("calc_date",calc_date);
		if ( account_balance_is_set )
			map.put("account_balance",account_balance);
		if ( today_executed_amount_is_set )
			map.put("today_executed_amount",today_executed_amount);
		if ( today_unexecuted_amount_is_set )
			map.put("today_unexecuted_amount",today_unexecuted_amount);
		if ( sonar_cash_margin_deposit_is_set )
			map.put("sonar_cash_margin_deposit",sonar_cash_margin_deposit);
		if ( transfer_to_margin_deposit_is_set )
			map.put("transfer_to_margin_deposit",transfer_to_margin_deposit);
		if ( transfer_from_margin_deposit_is_set )
			map.put("transfer_from_margin_deposit",transfer_from_margin_deposit);
		if ( cash_margin_deposit_is_set )
			map.put("cash_margin_deposit",cash_margin_deposit);
		if ( account_cash_is_set )
			map.put("account_cash",account_cash);
		if ( day_trade_restraint_is_set )
			map.put("day_trade_restraint",day_trade_restraint);
		if ( contract_amount_is_set )
			map.put("contract_amount",contract_amount);
		if ( contract_asset_profit_loss_is_set )
			map.put("contract_asset_profit_loss",contract_asset_profit_loss);
		if ( contract_total_cost_is_set )
			map.put("contract_total_cost",contract_total_cost);
		if ( undeli_contract_loss_is_set )
			map.put("undeli_contract_loss",undeli_contract_loss);
		if ( substitute_security_asset_is_set )
			map.put("substitute_security_asset",substitute_security_asset);
		if ( margin_deposit_rate_is_set )
			map.put("margin_deposit_rate",margin_deposit_rate);
		if ( margin_deposit_rate_prebizdate_is_set )
			map.put("margin_deposit_rate_prebizdate",margin_deposit_rate_prebizdate);
		if ( substitute_security_asset_pre_is_set )
			map.put("substitute_security_asset_pre",substitute_security_asset_pre);
		if ( today_repay_contract_amount_is_set )
			map.put("today_repay_contract_amount",today_repay_contract_amount);
		if ( account_balance_non_pay_is_set )
			map.put("account_balance_non_pay",account_balance_non_pay);
		if ( account_balance_non_pay_date != null )
			map.put("account_balance_non_pay_date",account_balance_non_pay_date);
		if ( debit_amount_is_set )
			map.put("debit_amount",debit_amount);
		if ( special_debit_amount_is_set )
			map.put("special_debit_amount",special_debit_amount);
		if ( lack_account_balance_is_set )
			map.put("lack_account_balance",lack_account_balance);
		if ( first_deposit_amount_is_set )
			map.put("first_deposit_amount",first_deposit_amount);
		if ( first_settlement_is_set )
			map.put("first_settlement",first_settlement);
		if ( first_deposit_pass_day != null )
			map.put("first_deposit_pass_day",first_deposit_pass_day);
		if ( first_deposit_occurred_date != null )
			map.put("first_deposit_occurred_date",first_deposit_occurred_date);
		if ( second_deposit_non_pay_is_set )
			map.put("second_deposit_non_pay",second_deposit_non_pay);
		if ( second_settlement_non_pay_is_set )
			map.put("second_settlement_non_pay",second_settlement_non_pay);
		if ( second_deposit_1_is_set )
			map.put("second_deposit_1",second_deposit_1);
		if ( second_settlement_1_is_set )
			map.put("second_settlement_1",second_settlement_1);
		if ( second_deposit_2_is_set )
			map.put("second_deposit_2",second_deposit_2);
		if ( second_settlement_2_is_set )
			map.put("second_settlement_2",second_settlement_2);
		if ( first_deposit_rate_is_set )
			map.put("first_deposit_rate",first_deposit_rate);
		if ( second_deposit_rate_is_set )
			map.put("second_deposit_rate",second_deposit_rate);
		if ( second_deposit_back_rate_is_set )
			map.put("second_deposit_back_rate",second_deposit_back_rate);
		if ( first_deposit_pass_day_valid != null )
			map.put("first_deposit_pass_day_valid",first_deposit_pass_day_valid);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( family_name_is_modified )
			map.put("family_name",family_name);
		if ( account_attribute_is_modified )
			map.put("account_attribute",account_attribute);
		if ( calc_date_is_modified )
			map.put("calc_date",calc_date);
		if ( account_balance_is_modified )
			map.put("account_balance",account_balance);
		if ( today_executed_amount_is_modified )
			map.put("today_executed_amount",today_executed_amount);
		if ( today_unexecuted_amount_is_modified )
			map.put("today_unexecuted_amount",today_unexecuted_amount);
		if ( sonar_cash_margin_deposit_is_modified )
			map.put("sonar_cash_margin_deposit",sonar_cash_margin_deposit);
		if ( transfer_to_margin_deposit_is_modified )
			map.put("transfer_to_margin_deposit",transfer_to_margin_deposit);
		if ( transfer_from_margin_deposit_is_modified )
			map.put("transfer_from_margin_deposit",transfer_from_margin_deposit);
		if ( cash_margin_deposit_is_modified )
			map.put("cash_margin_deposit",cash_margin_deposit);
		if ( account_cash_is_modified )
			map.put("account_cash",account_cash);
		if ( day_trade_restraint_is_modified )
			map.put("day_trade_restraint",day_trade_restraint);
		if ( contract_amount_is_modified )
			map.put("contract_amount",contract_amount);
		if ( contract_asset_profit_loss_is_modified )
			map.put("contract_asset_profit_loss",contract_asset_profit_loss);
		if ( contract_total_cost_is_modified )
			map.put("contract_total_cost",contract_total_cost);
		if ( undeli_contract_loss_is_modified )
			map.put("undeli_contract_loss",undeli_contract_loss);
		if ( substitute_security_asset_is_modified )
			map.put("substitute_security_asset",substitute_security_asset);
		if ( margin_deposit_rate_is_modified )
			map.put("margin_deposit_rate",margin_deposit_rate);
		if ( margin_deposit_rate_prebizdate_is_modified )
			map.put("margin_deposit_rate_prebizdate",margin_deposit_rate_prebizdate);
		if ( substitute_security_asset_pre_is_modified )
			map.put("substitute_security_asset_pre",substitute_security_asset_pre);
		if ( today_repay_contract_amount_is_modified )
			map.put("today_repay_contract_amount",today_repay_contract_amount);
		if ( account_balance_non_pay_is_modified )
			map.put("account_balance_non_pay",account_balance_non_pay);
		if ( account_balance_non_pay_date_is_modified )
			map.put("account_balance_non_pay_date",account_balance_non_pay_date);
		if ( debit_amount_is_modified )
			map.put("debit_amount",debit_amount);
		if ( special_debit_amount_is_modified )
			map.put("special_debit_amount",special_debit_amount);
		if ( lack_account_balance_is_modified )
			map.put("lack_account_balance",lack_account_balance);
		if ( first_deposit_amount_is_modified )
			map.put("first_deposit_amount",first_deposit_amount);
		if ( first_settlement_is_modified )
			map.put("first_settlement",first_settlement);
		if ( first_deposit_pass_day_is_modified )
			map.put("first_deposit_pass_day",first_deposit_pass_day);
		if ( first_deposit_occurred_date_is_modified )
			map.put("first_deposit_occurred_date",first_deposit_occurred_date);
		if ( second_deposit_non_pay_is_modified )
			map.put("second_deposit_non_pay",second_deposit_non_pay);
		if ( second_settlement_non_pay_is_modified )
			map.put("second_settlement_non_pay",second_settlement_non_pay);
		if ( second_deposit_1_is_modified )
			map.put("second_deposit_1",second_deposit_1);
		if ( second_settlement_1_is_modified )
			map.put("second_settlement_1",second_settlement_1);
		if ( second_deposit_2_is_modified )
			map.put("second_deposit_2",second_deposit_2);
		if ( second_settlement_2_is_modified )
			map.put("second_settlement_2",second_settlement_2);
		if ( first_deposit_rate_is_modified )
			map.put("first_deposit_rate",first_deposit_rate);
		if ( second_deposit_rate_is_modified )
			map.put("second_deposit_rate",second_deposit_rate);
		if ( second_deposit_back_rate_is_modified )
			map.put("second_deposit_back_rate",second_deposit_back_rate);
		if ( first_deposit_pass_day_valid_is_modified )
			map.put("first_deposit_pass_day_valid",first_deposit_pass_day_valid);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			if ( family_name_is_set )
				map.put("family_name",family_name);
			if ( account_attribute_is_set )
				map.put("account_attribute",account_attribute);
			if ( calc_date_is_set )
				map.put("calc_date",calc_date);
			if ( account_balance_is_set )
				map.put("account_balance",account_balance);
			if ( today_executed_amount_is_set )
				map.put("today_executed_amount",today_executed_amount);
			if ( today_unexecuted_amount_is_set )
				map.put("today_unexecuted_amount",today_unexecuted_amount);
			if ( sonar_cash_margin_deposit_is_set )
				map.put("sonar_cash_margin_deposit",sonar_cash_margin_deposit);
			if ( transfer_to_margin_deposit_is_set )
				map.put("transfer_to_margin_deposit",transfer_to_margin_deposit);
			if ( transfer_from_margin_deposit_is_set )
				map.put("transfer_from_margin_deposit",transfer_from_margin_deposit);
			if ( cash_margin_deposit_is_set )
				map.put("cash_margin_deposit",cash_margin_deposit);
			if ( account_cash_is_set )
				map.put("account_cash",account_cash);
			if ( day_trade_restraint_is_set )
				map.put("day_trade_restraint",day_trade_restraint);
			if ( contract_amount_is_set )
				map.put("contract_amount",contract_amount);
			if ( contract_asset_profit_loss_is_set )
				map.put("contract_asset_profit_loss",contract_asset_profit_loss);
			if ( contract_total_cost_is_set )
				map.put("contract_total_cost",contract_total_cost);
			if ( undeli_contract_loss_is_set )
				map.put("undeli_contract_loss",undeli_contract_loss);
			if ( substitute_security_asset_is_set )
				map.put("substitute_security_asset",substitute_security_asset);
			if ( margin_deposit_rate_is_set )
				map.put("margin_deposit_rate",margin_deposit_rate);
			if ( margin_deposit_rate_prebizdate_is_set )
				map.put("margin_deposit_rate_prebizdate",margin_deposit_rate_prebizdate);
			if ( substitute_security_asset_pre_is_set )
				map.put("substitute_security_asset_pre",substitute_security_asset_pre);
			if ( today_repay_contract_amount_is_set )
				map.put("today_repay_contract_amount",today_repay_contract_amount);
			if ( account_balance_non_pay_is_set )
				map.put("account_balance_non_pay",account_balance_non_pay);
			map.put("account_balance_non_pay_date",account_balance_non_pay_date);
			if ( debit_amount_is_set )
				map.put("debit_amount",debit_amount);
			if ( special_debit_amount_is_set )
				map.put("special_debit_amount",special_debit_amount);
			if ( lack_account_balance_is_set )
				map.put("lack_account_balance",lack_account_balance);
			if ( first_deposit_amount_is_set )
				map.put("first_deposit_amount",first_deposit_amount);
			if ( first_settlement_is_set )
				map.put("first_settlement",first_settlement);
			map.put("first_deposit_pass_day",first_deposit_pass_day);
			map.put("first_deposit_occurred_date",first_deposit_occurred_date);
			if ( second_deposit_non_pay_is_set )
				map.put("second_deposit_non_pay",second_deposit_non_pay);
			if ( second_settlement_non_pay_is_set )
				map.put("second_settlement_non_pay",second_settlement_non_pay);
			if ( second_deposit_1_is_set )
				map.put("second_deposit_1",second_deposit_1);
			if ( second_settlement_1_is_set )
				map.put("second_settlement_1",second_settlement_1);
			if ( second_deposit_2_is_set )
				map.put("second_deposit_2",second_deposit_2);
			if ( second_settlement_2_is_set )
				map.put("second_settlement_2",second_settlement_2);
			if ( first_deposit_rate_is_set )
				map.put("first_deposit_rate",first_deposit_rate);
			if ( second_deposit_rate_is_set )
				map.put("second_deposit_rate",second_deposit_rate);
			if ( second_deposit_back_rate_is_set )
				map.put("second_deposit_back_rate",second_deposit_back_rate);
			map.put("first_deposit_pass_day_valid",first_deposit_pass_day_valid);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
  }


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
  }


  /** 
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsModified() {
    return account_code_is_modified;
  }


  /** 
   * <em>trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTraderCode()
  {
    return trader_code;
  }


  /** 
   * <em>trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsSet() {
    return trader_code_is_set;
  }


  /** 
   * <em>trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsModified() {
    return trader_code_is_modified;
  }


  /** 
   * <em>family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyName()
  {
    return family_name;
  }


  /** 
   * <em>family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameIsSet() {
    return family_name_is_set;
  }


  /** 
   * <em>family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameIsModified() {
    return family_name_is_modified;
  }


  /** 
   * <em>account_attribute</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountAttribute()
  {
    return account_attribute;
  }


  /** 
   * <em>account_attribute</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountAttributeIsSet() {
    return account_attribute_is_set;
  }


  /** 
   * <em>account_attribute</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountAttributeIsModified() {
    return account_attribute_is_modified;
  }


  /** 
   * <em>calc_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCalcDate()
  {
    return calc_date;
  }


  /** 
   * <em>calc_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcDateIsSet() {
    return calc_date_is_set;
  }


  /** 
   * <em>calc_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcDateIsModified() {
    return calc_date_is_modified;
  }


  /** 
   * <em>account_balance</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAccountBalance()
  {
    return ( account_balance==null? ((double)0): account_balance.doubleValue() );
  }


  /** 
   * <em>account_balance</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountBalanceIsNull()
  {
    return account_balance == null;
  }


  /** 
   * <em>account_balance</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalanceIsSet() {
    return account_balance_is_set;
  }


  /** 
   * <em>account_balance</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalanceIsModified() {
    return account_balance_is_modified;
  }


  /** 
   * <em>today_executed_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayExecutedAmount()
  {
    return ( today_executed_amount==null? ((double)0): today_executed_amount.doubleValue() );
  }


  /** 
   * <em>today_executed_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayExecutedAmountIsNull()
  {
    return today_executed_amount == null;
  }


  /** 
   * <em>today_executed_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmountIsSet() {
    return today_executed_amount_is_set;
  }


  /** 
   * <em>today_executed_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmountIsModified() {
    return today_executed_amount_is_modified;
  }


  /** 
   * <em>today_unexecuted_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayUnexecutedAmount()
  {
    return ( today_unexecuted_amount==null? ((double)0): today_unexecuted_amount.doubleValue() );
  }


  /** 
   * <em>today_unexecuted_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayUnexecutedAmountIsNull()
  {
    return today_unexecuted_amount == null;
  }


  /** 
   * <em>today_unexecuted_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmountIsSet() {
    return today_unexecuted_amount_is_set;
  }


  /** 
   * <em>today_unexecuted_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmountIsModified() {
    return today_unexecuted_amount_is_modified;
  }


  /** 
   * <em>sonar_cash_margin_deposit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSonarCashMarginDeposit()
  {
    return ( sonar_cash_margin_deposit==null? ((double)0): sonar_cash_margin_deposit.doubleValue() );
  }


  /** 
   * <em>sonar_cash_margin_deposit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSonarCashMarginDepositIsNull()
  {
    return sonar_cash_margin_deposit == null;
  }


  /** 
   * <em>sonar_cash_margin_deposit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCashMarginDepositIsSet() {
    return sonar_cash_margin_deposit_is_set;
  }


  /** 
   * <em>sonar_cash_margin_deposit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCashMarginDepositIsModified() {
    return sonar_cash_margin_deposit_is_modified;
  }


  /** 
   * <em>transfer_to_margin_deposit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTransferToMarginDeposit()
  {
    return ( transfer_to_margin_deposit==null? ((double)0): transfer_to_margin_deposit.doubleValue() );
  }


  /** 
   * <em>transfer_to_margin_deposit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTransferToMarginDepositIsNull()
  {
    return transfer_to_margin_deposit == null;
  }


  /** 
   * <em>transfer_to_margin_deposit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferToMarginDepositIsSet() {
    return transfer_to_margin_deposit_is_set;
  }


  /** 
   * <em>transfer_to_margin_deposit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferToMarginDepositIsModified() {
    return transfer_to_margin_deposit_is_modified;
  }


  /** 
   * <em>transfer_from_margin_deposit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTransferFromMarginDeposit()
  {
    return ( transfer_from_margin_deposit==null? ((double)0): transfer_from_margin_deposit.doubleValue() );
  }


  /** 
   * <em>transfer_from_margin_deposit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTransferFromMarginDepositIsNull()
  {
    return transfer_from_margin_deposit == null;
  }


  /** 
   * <em>transfer_from_margin_deposit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferFromMarginDepositIsSet() {
    return transfer_from_margin_deposit_is_set;
  }


  /** 
   * <em>transfer_from_margin_deposit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferFromMarginDepositIsModified() {
    return transfer_from_margin_deposit_is_modified;
  }


  /** 
   * <em>cash_margin_deposit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashMarginDeposit()
  {
    return ( cash_margin_deposit==null? ((double)0): cash_margin_deposit.doubleValue() );
  }


  /** 
   * <em>cash_margin_deposit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashMarginDepositIsNull()
  {
    return cash_margin_deposit == null;
  }


  /** 
   * <em>cash_margin_deposit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDepositIsSet() {
    return cash_margin_deposit_is_set;
  }


  /** 
   * <em>cash_margin_deposit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDepositIsModified() {
    return cash_margin_deposit_is_modified;
  }


  /** 
   * <em>account_cash</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAccountCash()
  {
    return ( account_cash==null? ((double)0): account_cash.doubleValue() );
  }


  /** 
   * <em>account_cash</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountCashIsNull()
  {
    return account_cash == null;
  }


  /** 
   * <em>account_cash</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCashIsSet() {
    return account_cash_is_set;
  }


  /** 
   * <em>account_cash</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCashIsModified() {
    return account_cash_is_modified;
  }


  /** 
   * <em>day_trade_restraint</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayTradeRestraint()
  {
    return ( day_trade_restraint==null? ((double)0): day_trade_restraint.doubleValue() );
  }


  /** 
   * <em>day_trade_restraint</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayTradeRestraintIsNull()
  {
    return day_trade_restraint == null;
  }


  /** 
   * <em>day_trade_restraint</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraintIsSet() {
    return day_trade_restraint_is_set;
  }


  /** 
   * <em>day_trade_restraint</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraintIsModified() {
    return day_trade_restraint_is_modified;
  }


  /** 
   * <em>contract_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAmount()
  {
    return ( contract_amount==null? ((double)0): contract_amount.doubleValue() );
  }


  /** 
   * <em>contract_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAmountIsNull()
  {
    return contract_amount == null;
  }


  /** 
   * <em>contract_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmountIsSet() {
    return contract_amount_is_set;
  }


  /** 
   * <em>contract_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmountIsModified() {
    return contract_amount_is_modified;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfitLoss()
  {
    return ( contract_asset_profit_loss==null? ((double)0): contract_asset_profit_loss.doubleValue() );
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfitLossIsNull()
  {
    return contract_asset_profit_loss == null;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLossIsSet() {
    return contract_asset_profit_loss_is_set;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLossIsModified() {
    return contract_asset_profit_loss_is_modified;
  }


  /** 
   * <em>contract_total_cost</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractTotalCost()
  {
    return ( contract_total_cost==null? ((double)0): contract_total_cost.doubleValue() );
  }


  /** 
   * <em>contract_total_cost</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractTotalCostIsNull()
  {
    return contract_total_cost == null;
  }


  /** 
   * <em>contract_total_cost</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCostIsSet() {
    return contract_total_cost_is_set;
  }


  /** 
   * <em>contract_total_cost</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCostIsModified() {
    return contract_total_cost_is_modified;
  }


  /** 
   * <em>undeli_contract_loss</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliContractLoss()
  {
    return ( undeli_contract_loss==null? ((double)0): undeli_contract_loss.doubleValue() );
  }


  /** 
   * <em>undeli_contract_loss</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUndeliContractLossIsNull()
  {
    return undeli_contract_loss == null;
  }


  /** 
   * <em>undeli_contract_loss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractLossIsSet() {
    return undeli_contract_loss_is_set;
  }


  /** 
   * <em>undeli_contract_loss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractLossIsModified() {
    return undeli_contract_loss_is_modified;
  }


  /** 
   * <em>substitute_security_asset</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSubstituteSecurityAsset()
  {
    return ( substitute_security_asset==null? ((double)0): substitute_security_asset.doubleValue() );
  }


  /** 
   * <em>substitute_security_asset</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSubstituteSecurityAssetIsNull()
  {
    return substitute_security_asset == null;
  }


  /** 
   * <em>substitute_security_asset</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAssetIsSet() {
    return substitute_security_asset_is_set;
  }


  /** 
   * <em>substitute_security_asset</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAssetIsModified() {
    return substitute_security_asset_is_modified;
  }


  /** 
   * <em>margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDepositRate()
  {
    return ( margin_deposit_rate==null? ((double)0): margin_deposit_rate.doubleValue() );
  }


  /** 
   * <em>margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDepositRateIsNull()
  {
    return margin_deposit_rate == null;
  }


  /** 
   * <em>margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRateIsSet() {
    return margin_deposit_rate_is_set;
  }


  /** 
   * <em>margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRateIsModified() {
    return margin_deposit_rate_is_modified;
  }


  /** 
   * <em>margin_deposit_rate_prebizdate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDepositRatePrebizdate()
  {
    return ( margin_deposit_rate_prebizdate==null? ((double)0): margin_deposit_rate_prebizdate.doubleValue() );
  }


  /** 
   * <em>margin_deposit_rate_prebizdate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDepositRatePrebizdateIsNull()
  {
    return margin_deposit_rate_prebizdate == null;
  }


  /** 
   * <em>margin_deposit_rate_prebizdate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRatePrebizdateIsSet() {
    return margin_deposit_rate_prebizdate_is_set;
  }


  /** 
   * <em>margin_deposit_rate_prebizdate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRatePrebizdateIsModified() {
    return margin_deposit_rate_prebizdate_is_modified;
  }


  /** 
   * <em>substitute_security_asset_pre</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSubstituteSecurityAssetPre()
  {
    return ( substitute_security_asset_pre==null? ((double)0): substitute_security_asset_pre.doubleValue() );
  }


  /** 
   * <em>substitute_security_asset_pre</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSubstituteSecurityAssetPreIsNull()
  {
    return substitute_security_asset_pre == null;
  }


  /** 
   * <em>substitute_security_asset_pre</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAssetPreIsSet() {
    return substitute_security_asset_pre_is_set;
  }


  /** 
   * <em>substitute_security_asset_pre</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAssetPreIsModified() {
    return substitute_security_asset_pre_is_modified;
  }


  /** 
   * <em>today_repay_contract_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayRepayContractAmount()
  {
    return ( today_repay_contract_amount==null? ((double)0): today_repay_contract_amount.doubleValue() );
  }


  /** 
   * <em>today_repay_contract_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayRepayContractAmountIsNull()
  {
    return today_repay_contract_amount == null;
  }


  /** 
   * <em>today_repay_contract_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayRepayContractAmountIsSet() {
    return today_repay_contract_amount_is_set;
  }


  /** 
   * <em>today_repay_contract_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayRepayContractAmountIsModified() {
    return today_repay_contract_amount_is_modified;
  }


  /** 
   * <em>account_balance_non_pay</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAccountBalanceNonPay()
  {
    return ( account_balance_non_pay==null? ((double)0): account_balance_non_pay.doubleValue() );
  }


  /** 
   * <em>account_balance_non_pay</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountBalanceNonPayIsNull()
  {
    return account_balance_non_pay == null;
  }


  /** 
   * <em>account_balance_non_pay</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalanceNonPayIsSet() {
    return account_balance_non_pay_is_set;
  }


  /** 
   * <em>account_balance_non_pay</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalanceNonPayIsModified() {
    return account_balance_non_pay_is_modified;
  }


  /** 
   * <em>account_balance_non_pay_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAccountBalanceNonPayDate()
  {
    return account_balance_non_pay_date;
  }


  /** 
   * <em>account_balance_non_pay_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalanceNonPayDateIsSet() {
    return account_balance_non_pay_date_is_set;
  }


  /** 
   * <em>account_balance_non_pay_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalanceNonPayDateIsModified() {
    return account_balance_non_pay_date_is_modified;
  }


  /** 
   * <em>debit_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDebitAmount()
  {
    return ( debit_amount==null? ((double)0): debit_amount.doubleValue() );
  }


  /** 
   * <em>debit_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDebitAmountIsNull()
  {
    return debit_amount == null;
  }


  /** 
   * <em>debit_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmountIsSet() {
    return debit_amount_is_set;
  }


  /** 
   * <em>debit_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmountIsModified() {
    return debit_amount_is_modified;
  }


  /** 
   * <em>special_debit_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpecialDebitAmount()
  {
    return ( special_debit_amount==null? ((double)0): special_debit_amount.doubleValue() );
  }


  /** 
   * <em>special_debit_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSpecialDebitAmountIsNull()
  {
    return special_debit_amount == null;
  }


  /** 
   * <em>special_debit_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmountIsSet() {
    return special_debit_amount_is_set;
  }


  /** 
   * <em>special_debit_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmountIsModified() {
    return special_debit_amount_is_modified;
  }


  /** 
   * <em>lack_account_balance</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLackAccountBalance()
  {
    return ( lack_account_balance==null? ((double)0): lack_account_balance.doubleValue() );
  }


  /** 
   * <em>lack_account_balance</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLackAccountBalanceIsNull()
  {
    return lack_account_balance == null;
  }


  /** 
   * <em>lack_account_balance</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLackAccountBalanceIsSet() {
    return lack_account_balance_is_set;
  }


  /** 
   * <em>lack_account_balance</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLackAccountBalanceIsModified() {
    return lack_account_balance_is_modified;
  }


  /** 
   * <em>first_deposit_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getFirstDepositAmount()
  {
    return ( first_deposit_amount==null? ((double)0): first_deposit_amount.doubleValue() );
  }


  /** 
   * <em>first_deposit_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFirstDepositAmountIsNull()
  {
    return first_deposit_amount == null;
  }


  /** 
   * <em>first_deposit_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstDepositAmountIsSet() {
    return first_deposit_amount_is_set;
  }


  /** 
   * <em>first_deposit_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstDepositAmountIsModified() {
    return first_deposit_amount_is_modified;
  }


  /** 
   * <em>first_settlement</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getFirstSettlement()
  {
    return ( first_settlement==null? ((double)0): first_settlement.doubleValue() );
  }


  /** 
   * <em>first_settlement</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFirstSettlementIsNull()
  {
    return first_settlement == null;
  }


  /** 
   * <em>first_settlement</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstSettlementIsSet() {
    return first_settlement_is_set;
  }


  /** 
   * <em>first_settlement</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstSettlementIsModified() {
    return first_settlement_is_modified;
  }


  /** 
   * <em>first_deposit_pass_day</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getFirstDepositPassDay()
  {
    return ( first_deposit_pass_day==null? ((double)0): first_deposit_pass_day.doubleValue() );
  }


  /** 
   * <em>first_deposit_pass_day</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFirstDepositPassDayIsNull()
  {
    return first_deposit_pass_day == null;
  }


  /** 
   * <em>first_deposit_pass_day</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstDepositPassDayIsSet() {
    return first_deposit_pass_day_is_set;
  }


  /** 
   * <em>first_deposit_pass_day</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstDepositPassDayIsModified() {
    return first_deposit_pass_day_is_modified;
  }


  /** 
   * <em>first_deposit_occurred_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getFirstDepositOccurredDate()
  {
    return first_deposit_occurred_date;
  }


  /** 
   * <em>first_deposit_occurred_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstDepositOccurredDateIsSet() {
    return first_deposit_occurred_date_is_set;
  }


  /** 
   * <em>first_deposit_occurred_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstDepositOccurredDateIsModified() {
    return first_deposit_occurred_date_is_modified;
  }


  /** 
   * <em>second_deposit_non_pay</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecondDepositNonPay()
  {
    return ( second_deposit_non_pay==null? ((double)0): second_deposit_non_pay.doubleValue() );
  }


  /** 
   * <em>second_deposit_non_pay</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSecondDepositNonPayIsNull()
  {
    return second_deposit_non_pay == null;
  }


  /** 
   * <em>second_deposit_non_pay</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondDepositNonPayIsSet() {
    return second_deposit_non_pay_is_set;
  }


  /** 
   * <em>second_deposit_non_pay</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondDepositNonPayIsModified() {
    return second_deposit_non_pay_is_modified;
  }


  /** 
   * <em>second_settlement_non_pay</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecondSettlementNonPay()
  {
    return ( second_settlement_non_pay==null? ((double)0): second_settlement_non_pay.doubleValue() );
  }


  /** 
   * <em>second_settlement_non_pay</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSecondSettlementNonPayIsNull()
  {
    return second_settlement_non_pay == null;
  }


  /** 
   * <em>second_settlement_non_pay</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondSettlementNonPayIsSet() {
    return second_settlement_non_pay_is_set;
  }


  /** 
   * <em>second_settlement_non_pay</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondSettlementNonPayIsModified() {
    return second_settlement_non_pay_is_modified;
  }


  /** 
   * <em>second_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecondDeposit1()
  {
    return ( second_deposit_1==null? ((double)0): second_deposit_1.doubleValue() );
  }


  /** 
   * <em>second_deposit_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSecondDeposit1IsNull()
  {
    return second_deposit_1 == null;
  }


  /** 
   * <em>second_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondDeposit1IsSet() {
    return second_deposit_1_is_set;
  }


  /** 
   * <em>second_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondDeposit1IsModified() {
    return second_deposit_1_is_modified;
  }


  /** 
   * <em>second_settlement_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecondSettlement1()
  {
    return ( second_settlement_1==null? ((double)0): second_settlement_1.doubleValue() );
  }


  /** 
   * <em>second_settlement_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSecondSettlement1IsNull()
  {
    return second_settlement_1 == null;
  }


  /** 
   * <em>second_settlement_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondSettlement1IsSet() {
    return second_settlement_1_is_set;
  }


  /** 
   * <em>second_settlement_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondSettlement1IsModified() {
    return second_settlement_1_is_modified;
  }


  /** 
   * <em>second_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecondDeposit2()
  {
    return ( second_deposit_2==null? ((double)0): second_deposit_2.doubleValue() );
  }


  /** 
   * <em>second_deposit_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSecondDeposit2IsNull()
  {
    return second_deposit_2 == null;
  }


  /** 
   * <em>second_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondDeposit2IsSet() {
    return second_deposit_2_is_set;
  }


  /** 
   * <em>second_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondDeposit2IsModified() {
    return second_deposit_2_is_modified;
  }


  /** 
   * <em>second_settlement_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecondSettlement2()
  {
    return ( second_settlement_2==null? ((double)0): second_settlement_2.doubleValue() );
  }


  /** 
   * <em>second_settlement_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSecondSettlement2IsNull()
  {
    return second_settlement_2 == null;
  }


  /** 
   * <em>second_settlement_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondSettlement2IsSet() {
    return second_settlement_2_is_set;
  }


  /** 
   * <em>second_settlement_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondSettlement2IsModified() {
    return second_settlement_2_is_modified;
  }


  /** 
   * <em>first_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getFirstDepositRate()
  {
    return ( first_deposit_rate==null? ((double)0): first_deposit_rate.doubleValue() );
  }


  /** 
   * <em>first_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFirstDepositRateIsNull()
  {
    return first_deposit_rate == null;
  }


  /** 
   * <em>first_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstDepositRateIsSet() {
    return first_deposit_rate_is_set;
  }


  /** 
   * <em>first_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstDepositRateIsModified() {
    return first_deposit_rate_is_modified;
  }


  /** 
   * <em>second_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecondDepositRate()
  {
    return ( second_deposit_rate==null? ((double)0): second_deposit_rate.doubleValue() );
  }


  /** 
   * <em>second_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSecondDepositRateIsNull()
  {
    return second_deposit_rate == null;
  }


  /** 
   * <em>second_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondDepositRateIsSet() {
    return second_deposit_rate_is_set;
  }


  /** 
   * <em>second_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondDepositRateIsModified() {
    return second_deposit_rate_is_modified;
  }


  /** 
   * <em>second_deposit_back_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecondDepositBackRate()
  {
    return ( second_deposit_back_rate==null? ((double)0): second_deposit_back_rate.doubleValue() );
  }


  /** 
   * <em>second_deposit_back_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSecondDepositBackRateIsNull()
  {
    return second_deposit_back_rate == null;
  }


  /** 
   * <em>second_deposit_back_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondDepositBackRateIsSet() {
    return second_deposit_back_rate_is_set;
  }


  /** 
   * <em>second_deposit_back_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecondDepositBackRateIsModified() {
    return second_deposit_back_rate_is_modified;
  }


  /** 
   * <em>first_deposit_pass_day_valid</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getFirstDepositPassDayValid()
  {
    return ( first_deposit_pass_day_valid==null? ((double)0): first_deposit_pass_day_valid.doubleValue() );
  }


  /** 
   * <em>first_deposit_pass_day_valid</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFirstDepositPassDayValidIsNull()
  {
    return first_deposit_pass_day_valid == null;
  }


  /** 
   * <em>first_deposit_pass_day_valid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstDepositPassDayValidIsSet() {
    return first_deposit_pass_day_valid_is_set;
  }


  /** 
   * <em>first_deposit_pass_day_valid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstDepositPassDayValidIsModified() {
    return first_deposit_pass_day_valid_is_modified;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new PaymentRequisitMngPK(account_id);
  }


  /** 
   * <em>account_id</em>カラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>branch_code</em>カラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>account_code</em>カラムの値を設定します。 
   *
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>trader_code</em>カラムの値を設定します。 
   *
   * @@param p_traderCode <em>trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setTraderCode( String p_traderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_code = p_traderCode;
    trader_code_is_set = true;
    trader_code_is_modified = true;
  }


  /** 
   * <em>family_name</em>カラムの値を設定します。 
   *
   * @@param p_familyName <em>family_name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setFamilyName( String p_familyName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_name = p_familyName;
    family_name_is_set = true;
    family_name_is_modified = true;
  }


  /** 
   * <em>account_attribute</em>カラムの値を設定します。 
   *
   * @@param p_accountAttribute <em>account_attribute</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountAttribute( String p_accountAttribute )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_attribute = p_accountAttribute;
    account_attribute_is_set = true;
    account_attribute_is_modified = true;
  }


  /** 
   * <em>calc_date</em>カラムの値を設定します。 
   *
   * @@param p_calcDate <em>calc_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCalcDate( java.sql.Timestamp p_calcDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_date = p_calcDate;
    calc_date_is_set = true;
    calc_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>calc_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCalcDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    calc_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    calc_date_is_set = true;
    calc_date_is_modified = true;
  }


  /** 
   * <em>account_balance</em>カラムの値を設定します。 
   *
   * @@param p_accountBalance <em>account_balance</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAccountBalance( double p_accountBalance )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance = new Double(p_accountBalance);
    account_balance_is_set = true;
    account_balance_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_balance</em>カラムに値を設定します。 
   */
  public final void setAccountBalance( Double p_accountBalance )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance = p_accountBalance;
    account_balance_is_set = true;
    account_balance_is_modified = true;
  }


  /** 
   * <em>today_executed_amount</em>カラムの値を設定します。 
   *
   * @@param p_todayExecutedAmount <em>today_executed_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayExecutedAmount( double p_todayExecutedAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_executed_amount = new Double(p_todayExecutedAmount);
    today_executed_amount_is_set = true;
    today_executed_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_executed_amount</em>カラムに値を設定します。 
   */
  public final void setTodayExecutedAmount( Double p_todayExecutedAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_executed_amount = p_todayExecutedAmount;
    today_executed_amount_is_set = true;
    today_executed_amount_is_modified = true;
  }


  /** 
   * <em>today_unexecuted_amount</em>カラムの値を設定します。 
   *
   * @@param p_todayUnexecutedAmount <em>today_unexecuted_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayUnexecutedAmount( double p_todayUnexecutedAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount = new Double(p_todayUnexecutedAmount);
    today_unexecuted_amount_is_set = true;
    today_unexecuted_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_unexecuted_amount</em>カラムに値を設定します。 
   */
  public final void setTodayUnexecutedAmount( Double p_todayUnexecutedAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount = p_todayUnexecutedAmount;
    today_unexecuted_amount_is_set = true;
    today_unexecuted_amount_is_modified = true;
  }


  /** 
   * <em>sonar_cash_margin_deposit</em>カラムの値を設定します。 
   *
   * @@param p_sonarCashMarginDeposit <em>sonar_cash_margin_deposit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSonarCashMarginDeposit( double p_sonarCashMarginDeposit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_cash_margin_deposit = new Double(p_sonarCashMarginDeposit);
    sonar_cash_margin_deposit_is_set = true;
    sonar_cash_margin_deposit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sonar_cash_margin_deposit</em>カラムに値を設定します。 
   */
  public final void setSonarCashMarginDeposit( Double p_sonarCashMarginDeposit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_cash_margin_deposit = p_sonarCashMarginDeposit;
    sonar_cash_margin_deposit_is_set = true;
    sonar_cash_margin_deposit_is_modified = true;
  }


  /** 
   * <em>transfer_to_margin_deposit</em>カラムの値を設定します。 
   *
   * @@param p_transferToMarginDeposit <em>transfer_to_margin_deposit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTransferToMarginDeposit( double p_transferToMarginDeposit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_to_margin_deposit = new Double(p_transferToMarginDeposit);
    transfer_to_margin_deposit_is_set = true;
    transfer_to_margin_deposit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>transfer_to_margin_deposit</em>カラムに値を設定します。 
   */
  public final void setTransferToMarginDeposit( Double p_transferToMarginDeposit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_to_margin_deposit = p_transferToMarginDeposit;
    transfer_to_margin_deposit_is_set = true;
    transfer_to_margin_deposit_is_modified = true;
  }


  /** 
   * <em>transfer_from_margin_deposit</em>カラムの値を設定します。 
   *
   * @@param p_transferFromMarginDeposit <em>transfer_from_margin_deposit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTransferFromMarginDeposit( double p_transferFromMarginDeposit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_from_margin_deposit = new Double(p_transferFromMarginDeposit);
    transfer_from_margin_deposit_is_set = true;
    transfer_from_margin_deposit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>transfer_from_margin_deposit</em>カラムに値を設定します。 
   */
  public final void setTransferFromMarginDeposit( Double p_transferFromMarginDeposit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_from_margin_deposit = p_transferFromMarginDeposit;
    transfer_from_margin_deposit_is_set = true;
    transfer_from_margin_deposit_is_modified = true;
  }


  /** 
   * <em>cash_margin_deposit</em>カラムの値を設定します。 
   *
   * @@param p_cashMarginDeposit <em>cash_margin_deposit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashMarginDeposit( double p_cashMarginDeposit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_margin_deposit = new Double(p_cashMarginDeposit);
    cash_margin_deposit_is_set = true;
    cash_margin_deposit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_margin_deposit</em>カラムに値を設定します。 
   */
  public final void setCashMarginDeposit( Double p_cashMarginDeposit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_margin_deposit = p_cashMarginDeposit;
    cash_margin_deposit_is_set = true;
    cash_margin_deposit_is_modified = true;
  }


  /** 
   * <em>account_cash</em>カラムの値を設定します。 
   *
   * @@param p_accountCash <em>account_cash</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAccountCash( double p_accountCash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_cash = new Double(p_accountCash);
    account_cash_is_set = true;
    account_cash_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_cash</em>カラムに値を設定します。 
   */
  public final void setAccountCash( Double p_accountCash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_cash = p_accountCash;
    account_cash_is_set = true;
    account_cash_is_modified = true;
  }


  /** 
   * <em>day_trade_restraint</em>カラムの値を設定します。 
   *
   * @@param p_dayTradeRestraint <em>day_trade_restraint</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayTradeRestraint( double p_dayTradeRestraint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint = new Double(p_dayTradeRestraint);
    day_trade_restraint_is_set = true;
    day_trade_restraint_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_trade_restraint</em>カラムに値を設定します。 
   */
  public final void setDayTradeRestraint( Double p_dayTradeRestraint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint = p_dayTradeRestraint;
    day_trade_restraint_is_set = true;
    day_trade_restraint_is_modified = true;
  }


  /** 
   * <em>contract_amount</em>カラムの値を設定します。 
   *
   * @@param p_contractAmount <em>contract_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAmount( double p_contractAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount = new Double(p_contractAmount);
    contract_amount_is_set = true;
    contract_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_amount</em>カラムに値を設定します。 
   */
  public final void setContractAmount( Double p_contractAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount = p_contractAmount;
    contract_amount_is_set = true;
    contract_amount_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfitLoss <em>contract_asset_profit_loss</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfitLoss( double p_contractAssetProfitLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss = new Double(p_contractAssetProfitLoss);
    contract_asset_profit_loss_is_set = true;
    contract_asset_profit_loss_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit_loss</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfitLoss( Double p_contractAssetProfitLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss = p_contractAssetProfitLoss;
    contract_asset_profit_loss_is_set = true;
    contract_asset_profit_loss_is_modified = true;
  }


  /** 
   * <em>contract_total_cost</em>カラムの値を設定します。 
   *
   * @@param p_contractTotalCost <em>contract_total_cost</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractTotalCost( double p_contractTotalCost )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost = new Double(p_contractTotalCost);
    contract_total_cost_is_set = true;
    contract_total_cost_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_total_cost</em>カラムに値を設定します。 
   */
  public final void setContractTotalCost( Double p_contractTotalCost )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost = p_contractTotalCost;
    contract_total_cost_is_set = true;
    contract_total_cost_is_modified = true;
  }


  /** 
   * <em>undeli_contract_loss</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractLoss <em>undeli_contract_loss</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliContractLoss( double p_undeliContractLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss = new Double(p_undeliContractLoss);
    undeli_contract_loss_is_set = true;
    undeli_contract_loss_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>undeli_contract_loss</em>カラムに値を設定します。 
   */
  public final void setUndeliContractLoss( Double p_undeliContractLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss = p_undeliContractLoss;
    undeli_contract_loss_is_set = true;
    undeli_contract_loss_is_modified = true;
  }


  /** 
   * <em>substitute_security_asset</em>カラムの値を設定します。 
   *
   * @@param p_substituteSecurityAsset <em>substitute_security_asset</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSubstituteSecurityAsset( double p_substituteSecurityAsset )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset = new Double(p_substituteSecurityAsset);
    substitute_security_asset_is_set = true;
    substitute_security_asset_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>substitute_security_asset</em>カラムに値を設定します。 
   */
  public final void setSubstituteSecurityAsset( Double p_substituteSecurityAsset )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset = p_substituteSecurityAsset;
    substitute_security_asset_is_set = true;
    substitute_security_asset_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate <em>margin_deposit_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDepositRate( double p_marginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate = new Double(p_marginDepositRate);
    margin_deposit_rate_is_set = true;
    margin_deposit_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_deposit_rate</em>カラムに値を設定します。 
   */
  public final void setMarginDepositRate( Double p_marginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate = p_marginDepositRate;
    margin_deposit_rate_is_set = true;
    margin_deposit_rate_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate_prebizdate</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRatePrebizdate <em>margin_deposit_rate_prebizdate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDepositRatePrebizdate( double p_marginDepositRatePrebizdate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_prebizdate = new Double(p_marginDepositRatePrebizdate);
    margin_deposit_rate_prebizdate_is_set = true;
    margin_deposit_rate_prebizdate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_deposit_rate_prebizdate</em>カラムに値を設定します。 
   */
  public final void setMarginDepositRatePrebizdate( Double p_marginDepositRatePrebizdate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_prebizdate = p_marginDepositRatePrebizdate;
    margin_deposit_rate_prebizdate_is_set = true;
    margin_deposit_rate_prebizdate_is_modified = true;
  }


  /** 
   * <em>substitute_security_asset_pre</em>カラムの値を設定します。 
   *
   * @@param p_substituteSecurityAssetPre <em>substitute_security_asset_pre</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSubstituteSecurityAssetPre( double p_substituteSecurityAssetPre )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_pre = new Double(p_substituteSecurityAssetPre);
    substitute_security_asset_pre_is_set = true;
    substitute_security_asset_pre_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>substitute_security_asset_pre</em>カラムに値を設定します。 
   */
  public final void setSubstituteSecurityAssetPre( Double p_substituteSecurityAssetPre )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_pre = p_substituteSecurityAssetPre;
    substitute_security_asset_pre_is_set = true;
    substitute_security_asset_pre_is_modified = true;
  }


  /** 
   * <em>today_repay_contract_amount</em>カラムの値を設定します。 
   *
   * @@param p_todayRepayContractAmount <em>today_repay_contract_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayRepayContractAmount( double p_todayRepayContractAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_repay_contract_amount = new Double(p_todayRepayContractAmount);
    today_repay_contract_amount_is_set = true;
    today_repay_contract_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_repay_contract_amount</em>カラムに値を設定します。 
   */
  public final void setTodayRepayContractAmount( Double p_todayRepayContractAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_repay_contract_amount = p_todayRepayContractAmount;
    today_repay_contract_amount_is_set = true;
    today_repay_contract_amount_is_modified = true;
  }


  /** 
   * <em>account_balance_non_pay</em>カラムの値を設定します。 
   *
   * @@param p_accountBalanceNonPay <em>account_balance_non_pay</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAccountBalanceNonPay( double p_accountBalanceNonPay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_non_pay = new Double(p_accountBalanceNonPay);
    account_balance_non_pay_is_set = true;
    account_balance_non_pay_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_balance_non_pay</em>カラムに値を設定します。 
   */
  public final void setAccountBalanceNonPay( Double p_accountBalanceNonPay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_non_pay = p_accountBalanceNonPay;
    account_balance_non_pay_is_set = true;
    account_balance_non_pay_is_modified = true;
  }


  /** 
   * <em>account_balance_non_pay_date</em>カラムの値を設定します。 
   *
   * @@param p_accountBalanceNonPayDate <em>account_balance_non_pay_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAccountBalanceNonPayDate( java.sql.Timestamp p_accountBalanceNonPayDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_non_pay_date = p_accountBalanceNonPayDate;
    account_balance_non_pay_date_is_set = true;
    account_balance_non_pay_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>account_balance_non_pay_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAccountBalanceNonPayDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_non_pay_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    account_balance_non_pay_date_is_set = true;
    account_balance_non_pay_date_is_modified = true;
  }


  /** 
   * <em>debit_amount</em>カラムの値を設定します。 
   *
   * @@param p_debitAmount <em>debit_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDebitAmount( double p_debitAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount = new Double(p_debitAmount);
    debit_amount_is_set = true;
    debit_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>debit_amount</em>カラムに値を設定します。 
   */
  public final void setDebitAmount( Double p_debitAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount = p_debitAmount;
    debit_amount_is_set = true;
    debit_amount_is_modified = true;
  }


  /** 
   * <em>special_debit_amount</em>カラムの値を設定します。 
   *
   * @@param p_specialDebitAmount <em>special_debit_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSpecialDebitAmount( double p_specialDebitAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_debit_amount = new Double(p_specialDebitAmount);
    special_debit_amount_is_set = true;
    special_debit_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>special_debit_amount</em>カラムに値を設定します。 
   */
  public final void setSpecialDebitAmount( Double p_specialDebitAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    special_debit_amount = p_specialDebitAmount;
    special_debit_amount_is_set = true;
    special_debit_amount_is_modified = true;
  }


  /** 
   * <em>lack_account_balance</em>カラムの値を設定します。 
   *
   * @@param p_lackAccountBalance <em>lack_account_balance</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLackAccountBalance( double p_lackAccountBalance )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    lack_account_balance = new Double(p_lackAccountBalance);
    lack_account_balance_is_set = true;
    lack_account_balance_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>lack_account_balance</em>カラムに値を設定します。 
   */
  public final void setLackAccountBalance( Double p_lackAccountBalance )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    lack_account_balance = p_lackAccountBalance;
    lack_account_balance_is_set = true;
    lack_account_balance_is_modified = true;
  }


  /** 
   * <em>first_deposit_amount</em>カラムの値を設定します。 
   *
   * @@param p_firstDepositAmount <em>first_deposit_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setFirstDepositAmount( double p_firstDepositAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    first_deposit_amount = new Double(p_firstDepositAmount);
    first_deposit_amount_is_set = true;
    first_deposit_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>first_deposit_amount</em>カラムに値を設定します。 
   */
  public final void setFirstDepositAmount( Double p_firstDepositAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    first_deposit_amount = p_firstDepositAmount;
    first_deposit_amount_is_set = true;
    first_deposit_amount_is_modified = true;
  }


  /** 
   * <em>first_settlement</em>カラムの値を設定します。 
   *
   * @@param p_firstSettlement <em>first_settlement</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setFirstSettlement( double p_firstSettlement )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    first_settlement = new Double(p_firstSettlement);
    first_settlement_is_set = true;
    first_settlement_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>first_settlement</em>カラムに値を設定します。 
   */
  public final void setFirstSettlement( Double p_firstSettlement )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    first_settlement = p_firstSettlement;
    first_settlement_is_set = true;
    first_settlement_is_modified = true;
  }


  /** 
   * <em>first_deposit_pass_day</em>カラムの値を設定します。 
   *
   * @@param p_firstDepositPassDay <em>first_deposit_pass_day</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setFirstDepositPassDay( double p_firstDepositPassDay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    first_deposit_pass_day = new Double(p_firstDepositPassDay);
    first_deposit_pass_day_is_set = true;
    first_deposit_pass_day_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>first_deposit_pass_day</em>カラムに値を設定します。 
   */
  public final void setFirstDepositPassDay( Double p_firstDepositPassDay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    first_deposit_pass_day = p_firstDepositPassDay;
    first_deposit_pass_day_is_set = true;
    first_deposit_pass_day_is_modified = true;
  }


  /** 
   * <em>first_deposit_occurred_date</em>カラムの値を設定します。 
   *
   * @@param p_firstDepositOccurredDate <em>first_deposit_occurred_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setFirstDepositOccurredDate( java.sql.Timestamp p_firstDepositOccurredDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    first_deposit_occurred_date = p_firstDepositOccurredDate;
    first_deposit_occurred_date_is_set = true;
    first_deposit_occurred_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>first_deposit_occurred_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setFirstDepositOccurredDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    first_deposit_occurred_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    first_deposit_occurred_date_is_set = true;
    first_deposit_occurred_date_is_modified = true;
  }


  /** 
   * <em>second_deposit_non_pay</em>カラムの値を設定します。 
   *
   * @@param p_secondDepositNonPay <em>second_deposit_non_pay</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecondDepositNonPay( double p_secondDepositNonPay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    second_deposit_non_pay = new Double(p_secondDepositNonPay);
    second_deposit_non_pay_is_set = true;
    second_deposit_non_pay_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>second_deposit_non_pay</em>カラムに値を設定します。 
   */
  public final void setSecondDepositNonPay( Double p_secondDepositNonPay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    second_deposit_non_pay = p_secondDepositNonPay;
    second_deposit_non_pay_is_set = true;
    second_deposit_non_pay_is_modified = true;
  }


  /** 
   * <em>second_settlement_non_pay</em>カラムの値を設定します。 
   *
   * @@param p_secondSettlementNonPay <em>second_settlement_non_pay</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecondSettlementNonPay( double p_secondSettlementNonPay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    second_settlement_non_pay = new Double(p_secondSettlementNonPay);
    second_settlement_non_pay_is_set = true;
    second_settlement_non_pay_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>second_settlement_non_pay</em>カラムに値を設定します。 
   */
  public final void setSecondSettlementNonPay( Double p_secondSettlementNonPay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    second_settlement_non_pay = p_secondSettlementNonPay;
    second_settlement_non_pay_is_set = true;
    second_settlement_non_pay_is_modified = true;
  }


  /** 
   * <em>second_deposit_1</em>カラムの値を設定します。 
   *
   * @@param p_secondDeposit1 <em>second_deposit_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecondDeposit1( double p_secondDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    second_deposit_1 = new Double(p_secondDeposit1);
    second_deposit_1_is_set = true;
    second_deposit_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>second_deposit_1</em>カラムに値を設定します。 
   */
  public final void setSecondDeposit1( Double p_secondDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    second_deposit_1 = p_secondDeposit1;
    second_deposit_1_is_set = true;
    second_deposit_1_is_modified = true;
  }


  /** 
   * <em>second_settlement_1</em>カラムの値を設定します。 
   *
   * @@param p_secondSettlement1 <em>second_settlement_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecondSettlement1( double p_secondSettlement1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    second_settlement_1 = new Double(p_secondSettlement1);
    second_settlement_1_is_set = true;
    second_settlement_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>second_settlement_1</em>カラムに値を設定します。 
   */
  public final void setSecondSettlement1( Double p_secondSettlement1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    second_settlement_1 = p_secondSettlement1;
    second_settlement_1_is_set = true;
    second_settlement_1_is_modified = true;
  }


  /** 
   * <em>second_deposit_2</em>カラムの値を設定します。 
   *
   * @@param p_secondDeposit2 <em>second_deposit_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecondDeposit2( double p_secondDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    second_deposit_2 = new Double(p_secondDeposit2);
    second_deposit_2_is_set = true;
    second_deposit_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>second_deposit_2</em>カラムに値を設定します。 
   */
  public final void setSecondDeposit2( Double p_secondDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    second_deposit_2 = p_secondDeposit2;
    second_deposit_2_is_set = true;
    second_deposit_2_is_modified = true;
  }


  /** 
   * <em>second_settlement_2</em>カラムの値を設定します。 
   *
   * @@param p_secondSettlement2 <em>second_settlement_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecondSettlement2( double p_secondSettlement2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    second_settlement_2 = new Double(p_secondSettlement2);
    second_settlement_2_is_set = true;
    second_settlement_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>second_settlement_2</em>カラムに値を設定します。 
   */
  public final void setSecondSettlement2( Double p_secondSettlement2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    second_settlement_2 = p_secondSettlement2;
    second_settlement_2_is_set = true;
    second_settlement_2_is_modified = true;
  }


  /** 
   * <em>first_deposit_rate</em>カラムの値を設定します。 
   *
   * @@param p_firstDepositRate <em>first_deposit_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setFirstDepositRate( double p_firstDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    first_deposit_rate = new Double(p_firstDepositRate);
    first_deposit_rate_is_set = true;
    first_deposit_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>first_deposit_rate</em>カラムに値を設定します。 
   */
  public final void setFirstDepositRate( Double p_firstDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    first_deposit_rate = p_firstDepositRate;
    first_deposit_rate_is_set = true;
    first_deposit_rate_is_modified = true;
  }


  /** 
   * <em>second_deposit_rate</em>カラムの値を設定します。 
   *
   * @@param p_secondDepositRate <em>second_deposit_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecondDepositRate( double p_secondDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    second_deposit_rate = new Double(p_secondDepositRate);
    second_deposit_rate_is_set = true;
    second_deposit_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>second_deposit_rate</em>カラムに値を設定します。 
   */
  public final void setSecondDepositRate( Double p_secondDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    second_deposit_rate = p_secondDepositRate;
    second_deposit_rate_is_set = true;
    second_deposit_rate_is_modified = true;
  }


  /** 
   * <em>second_deposit_back_rate</em>カラムの値を設定します。 
   *
   * @@param p_secondDepositBackRate <em>second_deposit_back_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecondDepositBackRate( double p_secondDepositBackRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    second_deposit_back_rate = new Double(p_secondDepositBackRate);
    second_deposit_back_rate_is_set = true;
    second_deposit_back_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>second_deposit_back_rate</em>カラムに値を設定します。 
   */
  public final void setSecondDepositBackRate( Double p_secondDepositBackRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    second_deposit_back_rate = p_secondDepositBackRate;
    second_deposit_back_rate_is_set = true;
    second_deposit_back_rate_is_modified = true;
  }


  /** 
   * <em>first_deposit_pass_day_valid</em>カラムの値を設定します。 
   *
   * @@param p_firstDepositPassDayValid <em>first_deposit_pass_day_valid</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setFirstDepositPassDayValid( double p_firstDepositPassDayValid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    first_deposit_pass_day_valid = new Double(p_firstDepositPassDayValid);
    first_deposit_pass_day_valid_is_set = true;
    first_deposit_pass_day_valid_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>first_deposit_pass_day_valid</em>カラムに値を設定します。 
   */
  public final void setFirstDepositPassDayValid( Double p_firstDepositPassDayValid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    first_deposit_pass_day_valid = p_firstDepositPassDayValid;
    first_deposit_pass_day_valid_is_set = true;
    first_deposit_pass_day_valid_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("account_attribute") ) {
                    return this.account_attribute;
                }
                else if ( name.equals("account_balance") ) {
                    return this.account_balance;
                }
                else if ( name.equals("account_cash") ) {
                    return this.account_cash;
                }
                else if ( name.equals("account_balance_non_pay") ) {
                    return this.account_balance_non_pay;
                }
                else if ( name.equals("account_balance_non_pay_date") ) {
                    return this.account_balance_non_pay_date;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("calc_date") ) {
                    return this.calc_date;
                }
                else if ( name.equals("cash_margin_deposit") ) {
                    return this.cash_margin_deposit;
                }
                else if ( name.equals("contract_amount") ) {
                    return this.contract_amount;
                }
                else if ( name.equals("contract_asset_profit_loss") ) {
                    return this.contract_asset_profit_loss;
                }
                else if ( name.equals("contract_total_cost") ) {
                    return this.contract_total_cost;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("day_trade_restraint") ) {
                    return this.day_trade_restraint;
                }
                else if ( name.equals("debit_amount") ) {
                    return this.debit_amount;
                }
                break;
            case 'f':
                if ( name.equals("family_name") ) {
                    return this.family_name;
                }
                else if ( name.equals("first_deposit_amount") ) {
                    return this.first_deposit_amount;
                }
                else if ( name.equals("first_settlement") ) {
                    return this.first_settlement;
                }
                else if ( name.equals("first_deposit_pass_day") ) {
                    return this.first_deposit_pass_day;
                }
                else if ( name.equals("first_deposit_occurred_date") ) {
                    return this.first_deposit_occurred_date;
                }
                else if ( name.equals("first_deposit_rate") ) {
                    return this.first_deposit_rate;
                }
                else if ( name.equals("first_deposit_pass_day_valid") ) {
                    return this.first_deposit_pass_day_valid;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("lack_account_balance") ) {
                    return this.lack_account_balance;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("margin_deposit_rate") ) {
                    return this.margin_deposit_rate;
                }
                else if ( name.equals("margin_deposit_rate_prebizdate") ) {
                    return this.margin_deposit_rate_prebizdate;
                }
                break;
            case 's':
                if ( name.equals("sonar_cash_margin_deposit") ) {
                    return this.sonar_cash_margin_deposit;
                }
                else if ( name.equals("substitute_security_asset") ) {
                    return this.substitute_security_asset;
                }
                else if ( name.equals("substitute_security_asset_pre") ) {
                    return this.substitute_security_asset_pre;
                }
                else if ( name.equals("special_debit_amount") ) {
                    return this.special_debit_amount;
                }
                else if ( name.equals("second_deposit_non_pay") ) {
                    return this.second_deposit_non_pay;
                }
                else if ( name.equals("second_settlement_non_pay") ) {
                    return this.second_settlement_non_pay;
                }
                else if ( name.equals("second_deposit_1") ) {
                    return this.second_deposit_1;
                }
                else if ( name.equals("second_settlement_1") ) {
                    return this.second_settlement_1;
                }
                else if ( name.equals("second_deposit_2") ) {
                    return this.second_deposit_2;
                }
                else if ( name.equals("second_settlement_2") ) {
                    return this.second_settlement_2;
                }
                else if ( name.equals("second_deposit_rate") ) {
                    return this.second_deposit_rate;
                }
                else if ( name.equals("second_deposit_back_rate") ) {
                    return this.second_deposit_back_rate;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("today_executed_amount") ) {
                    return this.today_executed_amount;
                }
                else if ( name.equals("today_unexecuted_amount") ) {
                    return this.today_unexecuted_amount;
                }
                else if ( name.equals("transfer_to_margin_deposit") ) {
                    return this.transfer_to_margin_deposit;
                }
                else if ( name.equals("transfer_from_margin_deposit") ) {
                    return this.transfer_from_margin_deposit;
                }
                else if ( name.equals("today_repay_contract_amount") ) {
                    return this.today_repay_contract_amount;
                }
                break;
            case 'u':
                if ( name.equals("undeli_contract_loss") ) {
                    return this.undeli_contract_loss;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("account_attribute") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_attribute' must be String: '"+value+"' is not." );
                    this.account_attribute = (String) value;
                    if (this.account_attribute_is_set)
                        this.account_attribute_is_modified = true;
                    this.account_attribute_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance' must be Double: '"+value+"' is not." );
                    this.account_balance = (Double) value;
                    if (this.account_balance_is_set)
                        this.account_balance_is_modified = true;
                    this.account_balance_is_set = true;
                    return;
                }
                else if ( name.equals("account_cash") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_cash' must be Double: '"+value+"' is not." );
                    this.account_cash = (Double) value;
                    if (this.account_cash_is_set)
                        this.account_cash_is_modified = true;
                    this.account_cash_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_non_pay") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance_non_pay' must be Double: '"+value+"' is not." );
                    this.account_balance_non_pay = (Double) value;
                    if (this.account_balance_non_pay_is_set)
                        this.account_balance_non_pay_is_modified = true;
                    this.account_balance_non_pay_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_non_pay_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'account_balance_non_pay_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.account_balance_non_pay_date = (java.sql.Timestamp) value;
                    if (this.account_balance_non_pay_date_is_set)
                        this.account_balance_non_pay_date_is_modified = true;
                    this.account_balance_non_pay_date_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("calc_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'calc_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.calc_date = (java.sql.Timestamp) value;
                    if (this.calc_date_is_set)
                        this.calc_date_is_modified = true;
                    this.calc_date_is_set = true;
                    return;
                }
                else if ( name.equals("cash_margin_deposit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_margin_deposit' must be Double: '"+value+"' is not." );
                    this.cash_margin_deposit = (Double) value;
                    if (this.cash_margin_deposit_is_set)
                        this.cash_margin_deposit_is_modified = true;
                    this.cash_margin_deposit_is_set = true;
                    return;
                }
                else if ( name.equals("contract_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_amount' must be Double: '"+value+"' is not." );
                    this.contract_amount = (Double) value;
                    if (this.contract_amount_is_set)
                        this.contract_amount_is_modified = true;
                    this.contract_amount_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_profit_loss") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_loss' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit_loss = (Double) value;
                    if (this.contract_asset_profit_loss_is_set)
                        this.contract_asset_profit_loss_is_modified = true;
                    this.contract_asset_profit_loss_is_set = true;
                    return;
                }
                else if ( name.equals("contract_total_cost") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_total_cost' must be Double: '"+value+"' is not." );
                    this.contract_total_cost = (Double) value;
                    if (this.contract_total_cost_is_set)
                        this.contract_total_cost_is_modified = true;
                    this.contract_total_cost_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("day_trade_restraint") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint' must be Double: '"+value+"' is not." );
                    this.day_trade_restraint = (Double) value;
                    if (this.day_trade_restraint_is_set)
                        this.day_trade_restraint_is_modified = true;
                    this.day_trade_restraint_is_set = true;
                    return;
                }
                else if ( name.equals("debit_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'debit_amount' must be Double: '"+value+"' is not." );
                    this.debit_amount = (Double) value;
                    if (this.debit_amount_is_set)
                        this.debit_amount_is_modified = true;
                    this.debit_amount_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("family_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_name' must be String: '"+value+"' is not." );
                    this.family_name = (String) value;
                    if (this.family_name_is_set)
                        this.family_name_is_modified = true;
                    this.family_name_is_set = true;
                    return;
                }
                else if ( name.equals("first_deposit_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'first_deposit_amount' must be Double: '"+value+"' is not." );
                    this.first_deposit_amount = (Double) value;
                    if (this.first_deposit_amount_is_set)
                        this.first_deposit_amount_is_modified = true;
                    this.first_deposit_amount_is_set = true;
                    return;
                }
                else if ( name.equals("first_settlement") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'first_settlement' must be Double: '"+value+"' is not." );
                    this.first_settlement = (Double) value;
                    if (this.first_settlement_is_set)
                        this.first_settlement_is_modified = true;
                    this.first_settlement_is_set = true;
                    return;
                }
                else if ( name.equals("first_deposit_pass_day") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'first_deposit_pass_day' must be Double: '"+value+"' is not." );
                    this.first_deposit_pass_day = (Double) value;
                    if (this.first_deposit_pass_day_is_set)
                        this.first_deposit_pass_day_is_modified = true;
                    this.first_deposit_pass_day_is_set = true;
                    return;
                }
                else if ( name.equals("first_deposit_occurred_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'first_deposit_occurred_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.first_deposit_occurred_date = (java.sql.Timestamp) value;
                    if (this.first_deposit_occurred_date_is_set)
                        this.first_deposit_occurred_date_is_modified = true;
                    this.first_deposit_occurred_date_is_set = true;
                    return;
                }
                else if ( name.equals("first_deposit_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'first_deposit_rate' must be Double: '"+value+"' is not." );
                    this.first_deposit_rate = (Double) value;
                    if (this.first_deposit_rate_is_set)
                        this.first_deposit_rate_is_modified = true;
                    this.first_deposit_rate_is_set = true;
                    return;
                }
                else if ( name.equals("first_deposit_pass_day_valid") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'first_deposit_pass_day_valid' must be Double: '"+value+"' is not." );
                    this.first_deposit_pass_day_valid = (Double) value;
                    if (this.first_deposit_pass_day_valid_is_set)
                        this.first_deposit_pass_day_valid_is_modified = true;
                    this.first_deposit_pass_day_valid_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("lack_account_balance") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'lack_account_balance' must be Double: '"+value+"' is not." );
                    this.lack_account_balance = (Double) value;
                    if (this.lack_account_balance_is_set)
                        this.lack_account_balance_is_modified = true;
                    this.lack_account_balance_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("margin_deposit_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate = (Double) value;
                    if (this.margin_deposit_rate_is_set)
                        this.margin_deposit_rate_is_modified = true;
                    this.margin_deposit_rate_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_rate_prebizdate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate_prebizdate' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate_prebizdate = (Double) value;
                    if (this.margin_deposit_rate_prebizdate_is_set)
                        this.margin_deposit_rate_prebizdate_is_modified = true;
                    this.margin_deposit_rate_prebizdate_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_cash_margin_deposit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'sonar_cash_margin_deposit' must be Double: '"+value+"' is not." );
                    this.sonar_cash_margin_deposit = (Double) value;
                    if (this.sonar_cash_margin_deposit_is_set)
                        this.sonar_cash_margin_deposit_is_modified = true;
                    this.sonar_cash_margin_deposit_is_set = true;
                    return;
                }
                else if ( name.equals("substitute_security_asset") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'substitute_security_asset' must be Double: '"+value+"' is not." );
                    this.substitute_security_asset = (Double) value;
                    if (this.substitute_security_asset_is_set)
                        this.substitute_security_asset_is_modified = true;
                    this.substitute_security_asset_is_set = true;
                    return;
                }
                else if ( name.equals("substitute_security_asset_pre") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'substitute_security_asset_pre' must be Double: '"+value+"' is not." );
                    this.substitute_security_asset_pre = (Double) value;
                    if (this.substitute_security_asset_pre_is_set)
                        this.substitute_security_asset_pre_is_modified = true;
                    this.substitute_security_asset_pre_is_set = true;
                    return;
                }
                else if ( name.equals("special_debit_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'special_debit_amount' must be Double: '"+value+"' is not." );
                    this.special_debit_amount = (Double) value;
                    if (this.special_debit_amount_is_set)
                        this.special_debit_amount_is_modified = true;
                    this.special_debit_amount_is_set = true;
                    return;
                }
                else if ( name.equals("second_deposit_non_pay") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'second_deposit_non_pay' must be Double: '"+value+"' is not." );
                    this.second_deposit_non_pay = (Double) value;
                    if (this.second_deposit_non_pay_is_set)
                        this.second_deposit_non_pay_is_modified = true;
                    this.second_deposit_non_pay_is_set = true;
                    return;
                }
                else if ( name.equals("second_settlement_non_pay") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'second_settlement_non_pay' must be Double: '"+value+"' is not." );
                    this.second_settlement_non_pay = (Double) value;
                    if (this.second_settlement_non_pay_is_set)
                        this.second_settlement_non_pay_is_modified = true;
                    this.second_settlement_non_pay_is_set = true;
                    return;
                }
                else if ( name.equals("second_deposit_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'second_deposit_1' must be Double: '"+value+"' is not." );
                    this.second_deposit_1 = (Double) value;
                    if (this.second_deposit_1_is_set)
                        this.second_deposit_1_is_modified = true;
                    this.second_deposit_1_is_set = true;
                    return;
                }
                else if ( name.equals("second_settlement_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'second_settlement_1' must be Double: '"+value+"' is not." );
                    this.second_settlement_1 = (Double) value;
                    if (this.second_settlement_1_is_set)
                        this.second_settlement_1_is_modified = true;
                    this.second_settlement_1_is_set = true;
                    return;
                }
                else if ( name.equals("second_deposit_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'second_deposit_2' must be Double: '"+value+"' is not." );
                    this.second_deposit_2 = (Double) value;
                    if (this.second_deposit_2_is_set)
                        this.second_deposit_2_is_modified = true;
                    this.second_deposit_2_is_set = true;
                    return;
                }
                else if ( name.equals("second_settlement_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'second_settlement_2' must be Double: '"+value+"' is not." );
                    this.second_settlement_2 = (Double) value;
                    if (this.second_settlement_2_is_set)
                        this.second_settlement_2_is_modified = true;
                    this.second_settlement_2_is_set = true;
                    return;
                }
                else if ( name.equals("second_deposit_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'second_deposit_rate' must be Double: '"+value+"' is not." );
                    this.second_deposit_rate = (Double) value;
                    if (this.second_deposit_rate_is_set)
                        this.second_deposit_rate_is_modified = true;
                    this.second_deposit_rate_is_set = true;
                    return;
                }
                else if ( name.equals("second_deposit_back_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'second_deposit_back_rate' must be Double: '"+value+"' is not." );
                    this.second_deposit_back_rate = (Double) value;
                    if (this.second_deposit_back_rate_is_set)
                        this.second_deposit_back_rate_is_modified = true;
                    this.second_deposit_back_rate_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trader_code' must be String: '"+value+"' is not." );
                    this.trader_code = (String) value;
                    if (this.trader_code_is_set)
                        this.trader_code_is_modified = true;
                    this.trader_code_is_set = true;
                    return;
                }
                else if ( name.equals("today_executed_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_executed_amount' must be Double: '"+value+"' is not." );
                    this.today_executed_amount = (Double) value;
                    if (this.today_executed_amount_is_set)
                        this.today_executed_amount_is_modified = true;
                    this.today_executed_amount_is_set = true;
                    return;
                }
                else if ( name.equals("today_unexecuted_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_unexecuted_amount' must be Double: '"+value+"' is not." );
                    this.today_unexecuted_amount = (Double) value;
                    if (this.today_unexecuted_amount_is_set)
                        this.today_unexecuted_amount_is_modified = true;
                    this.today_unexecuted_amount_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_to_margin_deposit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'transfer_to_margin_deposit' must be Double: '"+value+"' is not." );
                    this.transfer_to_margin_deposit = (Double) value;
                    if (this.transfer_to_margin_deposit_is_set)
                        this.transfer_to_margin_deposit_is_modified = true;
                    this.transfer_to_margin_deposit_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_from_margin_deposit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'transfer_from_margin_deposit' must be Double: '"+value+"' is not." );
                    this.transfer_from_margin_deposit = (Double) value;
                    if (this.transfer_from_margin_deposit_is_set)
                        this.transfer_from_margin_deposit_is_modified = true;
                    this.transfer_from_margin_deposit_is_set = true;
                    return;
                }
                else if ( name.equals("today_repay_contract_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_repay_contract_amount' must be Double: '"+value+"' is not." );
                    this.today_repay_contract_amount = (Double) value;
                    if (this.today_repay_contract_amount_is_set)
                        this.today_repay_contract_amount_is_modified = true;
                    this.today_repay_contract_amount_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("undeli_contract_loss") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_loss' must be Double: '"+value+"' is not." );
                    this.undeli_contract_loss = (Double) value;
                    if (this.undeli_contract_loss_is_set)
                        this.undeli_contract_loss_is_modified = true;
                    this.undeli_contract_loss_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
