head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	SettleDetailHistoryParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * settle_detail_historyテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SettleDetailHistoryRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SettleDetailHistoryParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SettleDetailHistoryParams}が{@@link SettleDetailHistoryRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SettleDetailHistoryPK 
 * @@see SettleDetailHistoryRow 
 */
public class SettleDetailHistoryParams extends Params implements SettleDetailHistoryRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "settle_detail_history";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SettleDetailHistoryRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SettleDetailHistoryRow.TYPE;
  }


  /** 
   * <em>settle_detail_history_id</em>カラムの値 
   */
  public  long  settle_detail_history_id;

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
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>commodity_code</em>カラムの値 
   */
  public  String  commodity_code;

  /** 
   * <em>trade_code</em>カラムの値 
   */
  public  String  trade_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>market_div</em>カラムの値 
   */
  public  String  market_div;

  /** 
   * <em>buy_sell_div</em>カラムの値 
   */
  public  String  buy_sell_div;

  /** 
   * <em>open_exec_date</em>カラムの値 
   */
  public  java.sql.Timestamp  open_exec_date;

  /** 
   * <em>close_exec_date</em>カラムの値 
   */
  public  java.sql.Timestamp  close_exec_date;

  /** 
   * <em>contract_price</em>カラムの値 
   */
  public  Double  contract_price;

  /** 
   * <em>close_contract_price</em>カラムの値 
   */
  public  Double  close_contract_price;

  /** 
   * <em>contract_amount</em>カラムの値 
   */
  public  Double  contract_amount;

  /** 
   * <em>close_contract_amount</em>カラムの値 
   */
  public  Double  close_contract_amount;

  /** 
   * <em>open_commission</em>カラムの値 
   */
  public  Double  open_commission;

  /** 
   * <em>close_commission</em>カラムの値 
   */
  public  Double  close_commission;

  /** 
   * <em>open_commission_tax</em>カラムの値 
   */
  public  Double  open_commission_tax;

  /** 
   * <em>close_commission_tax</em>カラムの値 
   */
  public  Double  close_commission_tax;

  /** 
   * <em>management_fee</em>カラムの値 
   */
  public  Double  management_fee;

  /** 
   * <em>management_fee_tax</em>カラムの値 
   */
  public  Double  management_fee_tax;

  /** 
   * <em>name_transfer_fee</em>カラムの値 
   */
  public  Double  name_transfer_fee;

  /** 
   * <em>name_transfer_fee_tax</em>カラムの値 
   */
  public  Double  name_transfer_fee_tax;

  /** 
   * <em>account_div</em>カラムの値 
   */
  public  String  account_div;

  /** 
   * <em>capital_gain_tax</em>カラムの値 
   */
  public  Double  capital_gain_tax;

  /** 
   * <em>debit_daily_interest</em>カラムの値 
   */
  public  Double  debit_daily_interest;

  /** 
   * <em>credit_daily_interest</em>カラムの値 
   */
  public  Double  credit_daily_interest;

  /** 
   * <em>loan_equity_fee</em>カラムの値 
   */
  public  Double  loan_equity_fee;

  /** 
   * <em>dividend_amount</em>カラムの値 
   */
  public  Double  dividend_amount;

  /** 
   * <em>adjust_amount</em>カラムの値 
   */
  public  Double  adjust_amount;

  /** 
   * <em>net_amount</em>カラムの値 
   */
  public  Double  net_amount;

  /** 
   * <em>repayment_type</em>カラムの値 
   */
  public  String  repayment_type;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean settle_detail_history_id_is_set = false;
  private boolean settle_detail_history_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean commodity_code_is_set = false;
  private boolean commodity_code_is_modified = false;
  private boolean trade_code_is_set = false;
  private boolean trade_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean market_div_is_set = false;
  private boolean market_div_is_modified = false;
  private boolean buy_sell_div_is_set = false;
  private boolean buy_sell_div_is_modified = false;
  private boolean open_exec_date_is_set = false;
  private boolean open_exec_date_is_modified = false;
  private boolean close_exec_date_is_set = false;
  private boolean close_exec_date_is_modified = false;
  private boolean contract_price_is_set = false;
  private boolean contract_price_is_modified = false;
  private boolean close_contract_price_is_set = false;
  private boolean close_contract_price_is_modified = false;
  private boolean contract_amount_is_set = false;
  private boolean contract_amount_is_modified = false;
  private boolean close_contract_amount_is_set = false;
  private boolean close_contract_amount_is_modified = false;
  private boolean open_commission_is_set = false;
  private boolean open_commission_is_modified = false;
  private boolean close_commission_is_set = false;
  private boolean close_commission_is_modified = false;
  private boolean open_commission_tax_is_set = false;
  private boolean open_commission_tax_is_modified = false;
  private boolean close_commission_tax_is_set = false;
  private boolean close_commission_tax_is_modified = false;
  private boolean management_fee_is_set = false;
  private boolean management_fee_is_modified = false;
  private boolean management_fee_tax_is_set = false;
  private boolean management_fee_tax_is_modified = false;
  private boolean name_transfer_fee_is_set = false;
  private boolean name_transfer_fee_is_modified = false;
  private boolean name_transfer_fee_tax_is_set = false;
  private boolean name_transfer_fee_tax_is_modified = false;
  private boolean account_div_is_set = false;
  private boolean account_div_is_modified = false;
  private boolean capital_gain_tax_is_set = false;
  private boolean capital_gain_tax_is_modified = false;
  private boolean debit_daily_interest_is_set = false;
  private boolean debit_daily_interest_is_modified = false;
  private boolean credit_daily_interest_is_set = false;
  private boolean credit_daily_interest_is_modified = false;
  private boolean loan_equity_fee_is_set = false;
  private boolean loan_equity_fee_is_modified = false;
  private boolean dividend_amount_is_set = false;
  private boolean dividend_amount_is_modified = false;
  private boolean adjust_amount_is_set = false;
  private boolean adjust_amount_is_modified = false;
  private boolean net_amount_is_set = false;
  private boolean net_amount_is_modified = false;
  private boolean repayment_type_is_set = false;
  private boolean repayment_type_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "settle_detail_history_id=" + settle_detail_history_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "delivery_date=" +delivery_date
      + "," + "commodity_code=" +commodity_code
      + "," + "trade_code=" +trade_code
      + "," + "product_code=" +product_code
      + "," + "market_div=" +market_div
      + "," + "buy_sell_div=" +buy_sell_div
      + "," + "open_exec_date=" +open_exec_date
      + "," + "close_exec_date=" +close_exec_date
      + "," + "contract_price=" +contract_price
      + "," + "close_contract_price=" +close_contract_price
      + "," + "contract_amount=" +contract_amount
      + "," + "close_contract_amount=" +close_contract_amount
      + "," + "open_commission=" +open_commission
      + "," + "close_commission=" +close_commission
      + "," + "open_commission_tax=" +open_commission_tax
      + "," + "close_commission_tax=" +close_commission_tax
      + "," + "management_fee=" +management_fee
      + "," + "management_fee_tax=" +management_fee_tax
      + "," + "name_transfer_fee=" +name_transfer_fee
      + "," + "name_transfer_fee_tax=" +name_transfer_fee_tax
      + "," + "account_div=" +account_div
      + "," + "capital_gain_tax=" +capital_gain_tax
      + "," + "debit_daily_interest=" +debit_daily_interest
      + "," + "credit_daily_interest=" +credit_daily_interest
      + "," + "loan_equity_fee=" +loan_equity_fee
      + "," + "dividend_amount=" +dividend_amount
      + "," + "adjust_amount=" +adjust_amount
      + "," + "net_amount=" +net_amount
      + "," + "repayment_type=" +repayment_type
      + "," + "status=" +status
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のSettleDetailHistoryParamsオブジェクトを作成します。 
   */
  public SettleDetailHistoryParams() {
    settle_detail_history_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSettleDetailHistoryRowオブジェクトの値を利用してSettleDetailHistoryParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSettleDetailHistoryRowオブジェクト 
   */
  public SettleDetailHistoryParams( SettleDetailHistoryRow p_row )
  {
    settle_detail_history_id = p_row.getSettleDetailHistoryId();
    settle_detail_history_id_is_set = p_row.getSettleDetailHistoryIdIsSet();
    settle_detail_history_id_is_modified = p_row.getSettleDetailHistoryIdIsModified();
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
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    commodity_code = p_row.getCommodityCode();
    commodity_code_is_set = p_row.getCommodityCodeIsSet();
    commodity_code_is_modified = p_row.getCommodityCodeIsModified();
    trade_code = p_row.getTradeCode();
    trade_code_is_set = p_row.getTradeCodeIsSet();
    trade_code_is_modified = p_row.getTradeCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    market_div = p_row.getMarketDiv();
    market_div_is_set = p_row.getMarketDivIsSet();
    market_div_is_modified = p_row.getMarketDivIsModified();
    buy_sell_div = p_row.getBuySellDiv();
    buy_sell_div_is_set = p_row.getBuySellDivIsSet();
    buy_sell_div_is_modified = p_row.getBuySellDivIsModified();
    open_exec_date = p_row.getOpenExecDate();
    open_exec_date_is_set = p_row.getOpenExecDateIsSet();
    open_exec_date_is_modified = p_row.getOpenExecDateIsModified();
    close_exec_date = p_row.getCloseExecDate();
    close_exec_date_is_set = p_row.getCloseExecDateIsSet();
    close_exec_date_is_modified = p_row.getCloseExecDateIsModified();
    if ( !p_row.getContractPriceIsNull() )
      contract_price = new Double( p_row.getContractPrice() );
    contract_price_is_set = p_row.getContractPriceIsSet();
    contract_price_is_modified = p_row.getContractPriceIsModified();
    if ( !p_row.getCloseContractPriceIsNull() )
      close_contract_price = new Double( p_row.getCloseContractPrice() );
    close_contract_price_is_set = p_row.getCloseContractPriceIsSet();
    close_contract_price_is_modified = p_row.getCloseContractPriceIsModified();
    if ( !p_row.getContractAmountIsNull() )
      contract_amount = new Double( p_row.getContractAmount() );
    contract_amount_is_set = p_row.getContractAmountIsSet();
    contract_amount_is_modified = p_row.getContractAmountIsModified();
    if ( !p_row.getCloseContractAmountIsNull() )
      close_contract_amount = new Double( p_row.getCloseContractAmount() );
    close_contract_amount_is_set = p_row.getCloseContractAmountIsSet();
    close_contract_amount_is_modified = p_row.getCloseContractAmountIsModified();
    if ( !p_row.getOpenCommissionIsNull() )
      open_commission = new Double( p_row.getOpenCommission() );
    open_commission_is_set = p_row.getOpenCommissionIsSet();
    open_commission_is_modified = p_row.getOpenCommissionIsModified();
    if ( !p_row.getCloseCommissionIsNull() )
      close_commission = new Double( p_row.getCloseCommission() );
    close_commission_is_set = p_row.getCloseCommissionIsSet();
    close_commission_is_modified = p_row.getCloseCommissionIsModified();
    if ( !p_row.getOpenCommissionTaxIsNull() )
      open_commission_tax = new Double( p_row.getOpenCommissionTax() );
    open_commission_tax_is_set = p_row.getOpenCommissionTaxIsSet();
    open_commission_tax_is_modified = p_row.getOpenCommissionTaxIsModified();
    if ( !p_row.getCloseCommissionTaxIsNull() )
      close_commission_tax = new Double( p_row.getCloseCommissionTax() );
    close_commission_tax_is_set = p_row.getCloseCommissionTaxIsSet();
    close_commission_tax_is_modified = p_row.getCloseCommissionTaxIsModified();
    if ( !p_row.getManagementFeeIsNull() )
      management_fee = new Double( p_row.getManagementFee() );
    management_fee_is_set = p_row.getManagementFeeIsSet();
    management_fee_is_modified = p_row.getManagementFeeIsModified();
    if ( !p_row.getManagementFeeTaxIsNull() )
      management_fee_tax = new Double( p_row.getManagementFeeTax() );
    management_fee_tax_is_set = p_row.getManagementFeeTaxIsSet();
    management_fee_tax_is_modified = p_row.getManagementFeeTaxIsModified();
    if ( !p_row.getNameTransferFeeIsNull() )
      name_transfer_fee = new Double( p_row.getNameTransferFee() );
    name_transfer_fee_is_set = p_row.getNameTransferFeeIsSet();
    name_transfer_fee_is_modified = p_row.getNameTransferFeeIsModified();
    if ( !p_row.getNameTransferFeeTaxIsNull() )
      name_transfer_fee_tax = new Double( p_row.getNameTransferFeeTax() );
    name_transfer_fee_tax_is_set = p_row.getNameTransferFeeTaxIsSet();
    name_transfer_fee_tax_is_modified = p_row.getNameTransferFeeTaxIsModified();
    account_div = p_row.getAccountDiv();
    account_div_is_set = p_row.getAccountDivIsSet();
    account_div_is_modified = p_row.getAccountDivIsModified();
    if ( !p_row.getCapitalGainTaxIsNull() )
      capital_gain_tax = new Double( p_row.getCapitalGainTax() );
    capital_gain_tax_is_set = p_row.getCapitalGainTaxIsSet();
    capital_gain_tax_is_modified = p_row.getCapitalGainTaxIsModified();
    if ( !p_row.getDebitDailyInterestIsNull() )
      debit_daily_interest = new Double( p_row.getDebitDailyInterest() );
    debit_daily_interest_is_set = p_row.getDebitDailyInterestIsSet();
    debit_daily_interest_is_modified = p_row.getDebitDailyInterestIsModified();
    if ( !p_row.getCreditDailyInterestIsNull() )
      credit_daily_interest = new Double( p_row.getCreditDailyInterest() );
    credit_daily_interest_is_set = p_row.getCreditDailyInterestIsSet();
    credit_daily_interest_is_modified = p_row.getCreditDailyInterestIsModified();
    if ( !p_row.getLoanEquityFeeIsNull() )
      loan_equity_fee = new Double( p_row.getLoanEquityFee() );
    loan_equity_fee_is_set = p_row.getLoanEquityFeeIsSet();
    loan_equity_fee_is_modified = p_row.getLoanEquityFeeIsModified();
    if ( !p_row.getDividendAmountIsNull() )
      dividend_amount = new Double( p_row.getDividendAmount() );
    dividend_amount_is_set = p_row.getDividendAmountIsSet();
    dividend_amount_is_modified = p_row.getDividendAmountIsModified();
    if ( !p_row.getAdjustAmountIsNull() )
      adjust_amount = new Double( p_row.getAdjustAmount() );
    adjust_amount_is_set = p_row.getAdjustAmountIsSet();
    adjust_amount_is_modified = p_row.getAdjustAmountIsModified();
    if ( !p_row.getNetAmountIsNull() )
      net_amount = new Double( p_row.getNetAmount() );
    net_amount_is_set = p_row.getNetAmountIsSet();
    net_amount_is_modified = p_row.getNetAmountIsModified();
    repayment_type = p_row.getRepaymentType();
    repayment_type_is_set = p_row.getRepaymentTypeIsSet();
    repayment_type_is_modified = p_row.getRepaymentTypeIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
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
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      commodity_code_is_set = true;
      commodity_code_is_modified = true;
      trade_code_is_set = true;
      trade_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      market_div_is_set = true;
      market_div_is_modified = true;
      buy_sell_div_is_set = true;
      buy_sell_div_is_modified = true;
      open_exec_date_is_set = true;
      open_exec_date_is_modified = true;
      close_exec_date_is_set = true;
      close_exec_date_is_modified = true;
      contract_price_is_set = true;
      contract_price_is_modified = true;
      close_contract_price_is_set = true;
      close_contract_price_is_modified = true;
      contract_amount_is_set = true;
      contract_amount_is_modified = true;
      close_contract_amount_is_set = true;
      close_contract_amount_is_modified = true;
      open_commission_is_set = true;
      open_commission_is_modified = true;
      close_commission_is_set = true;
      close_commission_is_modified = true;
      open_commission_tax_is_set = true;
      open_commission_tax_is_modified = true;
      close_commission_tax_is_set = true;
      close_commission_tax_is_modified = true;
      management_fee_is_set = true;
      management_fee_is_modified = true;
      management_fee_tax_is_set = true;
      management_fee_tax_is_modified = true;
      name_transfer_fee_is_set = true;
      name_transfer_fee_is_modified = true;
      name_transfer_fee_tax_is_set = true;
      name_transfer_fee_tax_is_modified = true;
      account_div_is_set = true;
      account_div_is_modified = true;
      capital_gain_tax_is_set = true;
      capital_gain_tax_is_modified = true;
      debit_daily_interest_is_set = true;
      debit_daily_interest_is_modified = true;
      credit_daily_interest_is_set = true;
      credit_daily_interest_is_modified = true;
      loan_equity_fee_is_set = true;
      loan_equity_fee_is_modified = true;
      dividend_amount_is_set = true;
      dividend_amount_is_modified = true;
      adjust_amount_is_set = true;
      adjust_amount_is_modified = true;
      net_amount_is_set = true;
      net_amount_is_modified = true;
      repayment_type_is_set = true;
      repayment_type_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
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
    if ( !( other instanceof SettleDetailHistoryRow ) )
       return false;
    return fieldsEqual( (SettleDetailHistoryRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSettleDetailHistoryRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SettleDetailHistoryRow row )
  {
    if ( settle_detail_history_id != row.getSettleDetailHistoryId() )
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
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( commodity_code == null ) {
      if ( row.getCommodityCode() != null )
        return false;
    } else if ( !commodity_code.equals( row.getCommodityCode() ) ) {
        return false;
    }
    if ( trade_code == null ) {
      if ( row.getTradeCode() != null )
        return false;
    } else if ( !trade_code.equals( row.getTradeCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( market_div == null ) {
      if ( row.getMarketDiv() != null )
        return false;
    } else if ( !market_div.equals( row.getMarketDiv() ) ) {
        return false;
    }
    if ( buy_sell_div == null ) {
      if ( row.getBuySellDiv() != null )
        return false;
    } else if ( !buy_sell_div.equals( row.getBuySellDiv() ) ) {
        return false;
    }
    if ( open_exec_date == null ) {
      if ( row.getOpenExecDate() != null )
        return false;
    } else if ( !open_exec_date.equals( row.getOpenExecDate() ) ) {
        return false;
    }
    if ( close_exec_date == null ) {
      if ( row.getCloseExecDate() != null )
        return false;
    } else if ( !close_exec_date.equals( row.getCloseExecDate() ) ) {
        return false;
    }
    if ( contract_price == null ) {
      if ( !row.getContractPriceIsNull() )
        return false;
    } else if ( row.getContractPriceIsNull() || ( contract_price.doubleValue() != row.getContractPrice() ) ) {
        return false;
    }
    if ( close_contract_price == null ) {
      if ( !row.getCloseContractPriceIsNull() )
        return false;
    } else if ( row.getCloseContractPriceIsNull() || ( close_contract_price.doubleValue() != row.getCloseContractPrice() ) ) {
        return false;
    }
    if ( contract_amount == null ) {
      if ( !row.getContractAmountIsNull() )
        return false;
    } else if ( row.getContractAmountIsNull() || ( contract_amount.doubleValue() != row.getContractAmount() ) ) {
        return false;
    }
    if ( close_contract_amount == null ) {
      if ( !row.getCloseContractAmountIsNull() )
        return false;
    } else if ( row.getCloseContractAmountIsNull() || ( close_contract_amount.doubleValue() != row.getCloseContractAmount() ) ) {
        return false;
    }
    if ( open_commission == null ) {
      if ( !row.getOpenCommissionIsNull() )
        return false;
    } else if ( row.getOpenCommissionIsNull() || ( open_commission.doubleValue() != row.getOpenCommission() ) ) {
        return false;
    }
    if ( close_commission == null ) {
      if ( !row.getCloseCommissionIsNull() )
        return false;
    } else if ( row.getCloseCommissionIsNull() || ( close_commission.doubleValue() != row.getCloseCommission() ) ) {
        return false;
    }
    if ( open_commission_tax == null ) {
      if ( !row.getOpenCommissionTaxIsNull() )
        return false;
    } else if ( row.getOpenCommissionTaxIsNull() || ( open_commission_tax.doubleValue() != row.getOpenCommissionTax() ) ) {
        return false;
    }
    if ( close_commission_tax == null ) {
      if ( !row.getCloseCommissionTaxIsNull() )
        return false;
    } else if ( row.getCloseCommissionTaxIsNull() || ( close_commission_tax.doubleValue() != row.getCloseCommissionTax() ) ) {
        return false;
    }
    if ( management_fee == null ) {
      if ( !row.getManagementFeeIsNull() )
        return false;
    } else if ( row.getManagementFeeIsNull() || ( management_fee.doubleValue() != row.getManagementFee() ) ) {
        return false;
    }
    if ( management_fee_tax == null ) {
      if ( !row.getManagementFeeTaxIsNull() )
        return false;
    } else if ( row.getManagementFeeTaxIsNull() || ( management_fee_tax.doubleValue() != row.getManagementFeeTax() ) ) {
        return false;
    }
    if ( name_transfer_fee == null ) {
      if ( !row.getNameTransferFeeIsNull() )
        return false;
    } else if ( row.getNameTransferFeeIsNull() || ( name_transfer_fee.doubleValue() != row.getNameTransferFee() ) ) {
        return false;
    }
    if ( name_transfer_fee_tax == null ) {
      if ( !row.getNameTransferFeeTaxIsNull() )
        return false;
    } else if ( row.getNameTransferFeeTaxIsNull() || ( name_transfer_fee_tax.doubleValue() != row.getNameTransferFeeTax() ) ) {
        return false;
    }
    if ( account_div == null ) {
      if ( row.getAccountDiv() != null )
        return false;
    } else if ( !account_div.equals( row.getAccountDiv() ) ) {
        return false;
    }
    if ( capital_gain_tax == null ) {
      if ( !row.getCapitalGainTaxIsNull() )
        return false;
    } else if ( row.getCapitalGainTaxIsNull() || ( capital_gain_tax.doubleValue() != row.getCapitalGainTax() ) ) {
        return false;
    }
    if ( debit_daily_interest == null ) {
      if ( !row.getDebitDailyInterestIsNull() )
        return false;
    } else if ( row.getDebitDailyInterestIsNull() || ( debit_daily_interest.doubleValue() != row.getDebitDailyInterest() ) ) {
        return false;
    }
    if ( credit_daily_interest == null ) {
      if ( !row.getCreditDailyInterestIsNull() )
        return false;
    } else if ( row.getCreditDailyInterestIsNull() || ( credit_daily_interest.doubleValue() != row.getCreditDailyInterest() ) ) {
        return false;
    }
    if ( loan_equity_fee == null ) {
      if ( !row.getLoanEquityFeeIsNull() )
        return false;
    } else if ( row.getLoanEquityFeeIsNull() || ( loan_equity_fee.doubleValue() != row.getLoanEquityFee() ) ) {
        return false;
    }
    if ( dividend_amount == null ) {
      if ( !row.getDividendAmountIsNull() )
        return false;
    } else if ( row.getDividendAmountIsNull() || ( dividend_amount.doubleValue() != row.getDividendAmount() ) ) {
        return false;
    }
    if ( adjust_amount == null ) {
      if ( !row.getAdjustAmountIsNull() )
        return false;
    } else if ( row.getAdjustAmountIsNull() || ( adjust_amount.doubleValue() != row.getAdjustAmount() ) ) {
        return false;
    }
    if ( net_amount == null ) {
      if ( !row.getNetAmountIsNull() )
        return false;
    } else if ( row.getNetAmountIsNull() || ( net_amount.doubleValue() != row.getNetAmount() ) ) {
        return false;
    }
    if ( repayment_type == null ) {
      if ( row.getRepaymentType() != null )
        return false;
    } else if ( !repayment_type.equals( row.getRepaymentType() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
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
      return  ((int) settle_detail_history_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (commodity_code!=null? commodity_code.hashCode(): 0) 
        + (trade_code!=null? trade_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (market_div!=null? market_div.hashCode(): 0) 
        + (buy_sell_div!=null? buy_sell_div.hashCode(): 0) 
        + (open_exec_date!=null? open_exec_date.hashCode(): 0) 
        + (close_exec_date!=null? close_exec_date.hashCode(): 0) 
        + (contract_price!=null? contract_price.hashCode(): 0) 
        + (close_contract_price!=null? close_contract_price.hashCode(): 0) 
        + (contract_amount!=null? contract_amount.hashCode(): 0) 
        + (close_contract_amount!=null? close_contract_amount.hashCode(): 0) 
        + (open_commission!=null? open_commission.hashCode(): 0) 
        + (close_commission!=null? close_commission.hashCode(): 0) 
        + (open_commission_tax!=null? open_commission_tax.hashCode(): 0) 
        + (close_commission_tax!=null? close_commission_tax.hashCode(): 0) 
        + (management_fee!=null? management_fee.hashCode(): 0) 
        + (management_fee_tax!=null? management_fee_tax.hashCode(): 0) 
        + (name_transfer_fee!=null? name_transfer_fee.hashCode(): 0) 
        + (name_transfer_fee_tax!=null? name_transfer_fee_tax.hashCode(): 0) 
        + (account_div!=null? account_div.hashCode(): 0) 
        + (capital_gain_tax!=null? capital_gain_tax.hashCode(): 0) 
        + (debit_daily_interest!=null? debit_daily_interest.hashCode(): 0) 
        + (credit_daily_interest!=null? credit_daily_interest.hashCode(): 0) 
        + (loan_equity_fee!=null? loan_equity_fee.hashCode(): 0) 
        + (dividend_amount!=null? dividend_amount.hashCode(): 0) 
        + (adjust_amount!=null? adjust_amount.hashCode(): 0) 
        + (net_amount!=null? net_amount.hashCode(): 0) 
        + (repayment_type!=null? repayment_type.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
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
		if ( !delivery_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_date' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("settle_detail_history_id",new Long(settle_detail_history_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		map.put("delivery_date",delivery_date);
		if ( commodity_code != null )
			map.put("commodity_code",commodity_code);
		if ( trade_code != null )
			map.put("trade_code",trade_code);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( market_div != null )
			map.put("market_div",market_div);
		if ( buy_sell_div != null )
			map.put("buy_sell_div",buy_sell_div);
		if ( open_exec_date != null )
			map.put("open_exec_date",open_exec_date);
		if ( close_exec_date != null )
			map.put("close_exec_date",close_exec_date);
		if ( contract_price != null )
			map.put("contract_price",contract_price);
		if ( close_contract_price != null )
			map.put("close_contract_price",close_contract_price);
		if ( contract_amount != null )
			map.put("contract_amount",contract_amount);
		if ( close_contract_amount != null )
			map.put("close_contract_amount",close_contract_amount);
		if ( open_commission != null )
			map.put("open_commission",open_commission);
		if ( close_commission != null )
			map.put("close_commission",close_commission);
		if ( open_commission_tax != null )
			map.put("open_commission_tax",open_commission_tax);
		if ( close_commission_tax != null )
			map.put("close_commission_tax",close_commission_tax);
		if ( management_fee != null )
			map.put("management_fee",management_fee);
		if ( management_fee_tax != null )
			map.put("management_fee_tax",management_fee_tax);
		if ( name_transfer_fee != null )
			map.put("name_transfer_fee",name_transfer_fee);
		if ( name_transfer_fee_tax != null )
			map.put("name_transfer_fee_tax",name_transfer_fee_tax);
		if ( account_div != null )
			map.put("account_div",account_div);
		if ( capital_gain_tax != null )
			map.put("capital_gain_tax",capital_gain_tax);
		if ( debit_daily_interest != null )
			map.put("debit_daily_interest",debit_daily_interest);
		if ( credit_daily_interest != null )
			map.put("credit_daily_interest",credit_daily_interest);
		if ( loan_equity_fee != null )
			map.put("loan_equity_fee",loan_equity_fee);
		if ( dividend_amount != null )
			map.put("dividend_amount",dividend_amount);
		if ( adjust_amount != null )
			map.put("adjust_amount",adjust_amount);
		if ( net_amount != null )
			map.put("net_amount",net_amount);
		if ( repayment_type != null )
			map.put("repayment_type",repayment_type);
		map.put("status",status);
		map.put("created_timestamp",created_timestamp);
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
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( commodity_code_is_modified )
			map.put("commodity_code",commodity_code);
		if ( trade_code_is_modified )
			map.put("trade_code",trade_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( market_div_is_modified )
			map.put("market_div",market_div);
		if ( buy_sell_div_is_modified )
			map.put("buy_sell_div",buy_sell_div);
		if ( open_exec_date_is_modified )
			map.put("open_exec_date",open_exec_date);
		if ( close_exec_date_is_modified )
			map.put("close_exec_date",close_exec_date);
		if ( contract_price_is_modified )
			map.put("contract_price",contract_price);
		if ( close_contract_price_is_modified )
			map.put("close_contract_price",close_contract_price);
		if ( contract_amount_is_modified )
			map.put("contract_amount",contract_amount);
		if ( close_contract_amount_is_modified )
			map.put("close_contract_amount",close_contract_amount);
		if ( open_commission_is_modified )
			map.put("open_commission",open_commission);
		if ( close_commission_is_modified )
			map.put("close_commission",close_commission);
		if ( open_commission_tax_is_modified )
			map.put("open_commission_tax",open_commission_tax);
		if ( close_commission_tax_is_modified )
			map.put("close_commission_tax",close_commission_tax);
		if ( management_fee_is_modified )
			map.put("management_fee",management_fee);
		if ( management_fee_tax_is_modified )
			map.put("management_fee_tax",management_fee_tax);
		if ( name_transfer_fee_is_modified )
			map.put("name_transfer_fee",name_transfer_fee);
		if ( name_transfer_fee_tax_is_modified )
			map.put("name_transfer_fee_tax",name_transfer_fee_tax);
		if ( account_div_is_modified )
			map.put("account_div",account_div);
		if ( capital_gain_tax_is_modified )
			map.put("capital_gain_tax",capital_gain_tax);
		if ( debit_daily_interest_is_modified )
			map.put("debit_daily_interest",debit_daily_interest);
		if ( credit_daily_interest_is_modified )
			map.put("credit_daily_interest",credit_daily_interest);
		if ( loan_equity_fee_is_modified )
			map.put("loan_equity_fee",loan_equity_fee);
		if ( dividend_amount_is_modified )
			map.put("dividend_amount",dividend_amount);
		if ( adjust_amount_is_modified )
			map.put("adjust_amount",adjust_amount);
		if ( net_amount_is_modified )
			map.put("net_amount",net_amount);
		if ( repayment_type_is_modified )
			map.put("repayment_type",repayment_type);
		if ( status_is_modified )
			map.put("status",status);
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
			if ( delivery_date_is_set )
				map.put("delivery_date",delivery_date);
			map.put("commodity_code",commodity_code);
			map.put("trade_code",trade_code);
			map.put("product_code",product_code);
			map.put("market_div",market_div);
			map.put("buy_sell_div",buy_sell_div);
			map.put("open_exec_date",open_exec_date);
			map.put("close_exec_date",close_exec_date);
			map.put("contract_price",contract_price);
			map.put("close_contract_price",close_contract_price);
			map.put("contract_amount",contract_amount);
			map.put("close_contract_amount",close_contract_amount);
			map.put("open_commission",open_commission);
			map.put("close_commission",close_commission);
			map.put("open_commission_tax",open_commission_tax);
			map.put("close_commission_tax",close_commission_tax);
			map.put("management_fee",management_fee);
			map.put("management_fee_tax",management_fee_tax);
			map.put("name_transfer_fee",name_transfer_fee);
			map.put("name_transfer_fee_tax",name_transfer_fee_tax);
			map.put("account_div",account_div);
			map.put("capital_gain_tax",capital_gain_tax);
			map.put("debit_daily_interest",debit_daily_interest);
			map.put("credit_daily_interest",credit_daily_interest);
			map.put("loan_equity_fee",loan_equity_fee);
			map.put("dividend_amount",dividend_amount);
			map.put("adjust_amount",adjust_amount);
			map.put("net_amount",net_amount);
			map.put("repayment_type",repayment_type);
			if ( status_is_set )
				map.put("status",status);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>settle_detail_history_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSettleDetailHistoryId()
  {
    return settle_detail_history_id;
  }


  /** 
   * <em>settle_detail_history_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettleDetailHistoryIdIsSet() {
    return settle_detail_history_id_is_set;
  }


  /** 
   * <em>settle_detail_history_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettleDetailHistoryIdIsModified() {
    return settle_detail_history_id_is_modified;
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
   * <em>delivery_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDeliveryDate()
  {
    return delivery_date;
  }


  /** 
   * <em>delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsSet() {
    return delivery_date_is_set;
  }


  /** 
   * <em>delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsModified() {
    return delivery_date_is_modified;
  }


  /** 
   * <em>commodity_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommodityCode()
  {
    return commodity_code;
  }


  /** 
   * <em>commodity_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommodityCodeIsSet() {
    return commodity_code_is_set;
  }


  /** 
   * <em>commodity_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommodityCodeIsModified() {
    return commodity_code_is_modified;
  }


  /** 
   * <em>trade_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradeCode()
  {
    return trade_code;
  }


  /** 
   * <em>trade_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeCodeIsSet() {
    return trade_code_is_set;
  }


  /** 
   * <em>trade_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeCodeIsModified() {
    return trade_code_is_modified;
  }


  /** 
   * <em>product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsSet() {
    return product_code_is_set;
  }


  /** 
   * <em>product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsModified() {
    return product_code_is_modified;
  }


  /** 
   * <em>market_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarketDiv()
  {
    return market_div;
  }


  /** 
   * <em>market_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketDivIsSet() {
    return market_div_is_set;
  }


  /** 
   * <em>market_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketDivIsModified() {
    return market_div_is_modified;
  }


  /** 
   * <em>buy_sell_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuySellDiv()
  {
    return buy_sell_div;
  }


  /** 
   * <em>buy_sell_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuySellDivIsSet() {
    return buy_sell_div_is_set;
  }


  /** 
   * <em>buy_sell_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuySellDivIsModified() {
    return buy_sell_div_is_modified;
  }


  /** 
   * <em>open_exec_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOpenExecDate()
  {
    return open_exec_date;
  }


  /** 
   * <em>open_exec_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenExecDateIsSet() {
    return open_exec_date_is_set;
  }


  /** 
   * <em>open_exec_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenExecDateIsModified() {
    return open_exec_date_is_modified;
  }


  /** 
   * <em>close_exec_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCloseExecDate()
  {
    return close_exec_date;
  }


  /** 
   * <em>close_exec_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseExecDateIsSet() {
    return close_exec_date_is_set;
  }


  /** 
   * <em>close_exec_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseExecDateIsModified() {
    return close_exec_date_is_modified;
  }


  /** 
   * <em>contract_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractPrice()
  {
    return ( contract_price==null? ((double)0): contract_price.doubleValue() );
  }


  /** 
   * <em>contract_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractPriceIsNull()
  {
    return contract_price == null;
  }


  /** 
   * <em>contract_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractPriceIsSet() {
    return contract_price_is_set;
  }


  /** 
   * <em>contract_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractPriceIsModified() {
    return contract_price_is_modified;
  }


  /** 
   * <em>close_contract_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCloseContractPrice()
  {
    return ( close_contract_price==null? ((double)0): close_contract_price.doubleValue() );
  }


  /** 
   * <em>close_contract_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCloseContractPriceIsNull()
  {
    return close_contract_price == null;
  }


  /** 
   * <em>close_contract_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseContractPriceIsSet() {
    return close_contract_price_is_set;
  }


  /** 
   * <em>close_contract_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseContractPriceIsModified() {
    return close_contract_price_is_modified;
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
   * <em>close_contract_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCloseContractAmount()
  {
    return ( close_contract_amount==null? ((double)0): close_contract_amount.doubleValue() );
  }


  /** 
   * <em>close_contract_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCloseContractAmountIsNull()
  {
    return close_contract_amount == null;
  }


  /** 
   * <em>close_contract_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseContractAmountIsSet() {
    return close_contract_amount_is_set;
  }


  /** 
   * <em>close_contract_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseContractAmountIsModified() {
    return close_contract_amount_is_modified;
  }


  /** 
   * <em>open_commission</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOpenCommission()
  {
    return ( open_commission==null? ((double)0): open_commission.doubleValue() );
  }


  /** 
   * <em>open_commission</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOpenCommissionIsNull()
  {
    return open_commission == null;
  }


  /** 
   * <em>open_commission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenCommissionIsSet() {
    return open_commission_is_set;
  }


  /** 
   * <em>open_commission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenCommissionIsModified() {
    return open_commission_is_modified;
  }


  /** 
   * <em>close_commission</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCloseCommission()
  {
    return ( close_commission==null? ((double)0): close_commission.doubleValue() );
  }


  /** 
   * <em>close_commission</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCloseCommissionIsNull()
  {
    return close_commission == null;
  }


  /** 
   * <em>close_commission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseCommissionIsSet() {
    return close_commission_is_set;
  }


  /** 
   * <em>close_commission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseCommissionIsModified() {
    return close_commission_is_modified;
  }


  /** 
   * <em>open_commission_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOpenCommissionTax()
  {
    return ( open_commission_tax==null? ((double)0): open_commission_tax.doubleValue() );
  }


  /** 
   * <em>open_commission_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOpenCommissionTaxIsNull()
  {
    return open_commission_tax == null;
  }


  /** 
   * <em>open_commission_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenCommissionTaxIsSet() {
    return open_commission_tax_is_set;
  }


  /** 
   * <em>open_commission_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenCommissionTaxIsModified() {
    return open_commission_tax_is_modified;
  }


  /** 
   * <em>close_commission_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCloseCommissionTax()
  {
    return ( close_commission_tax==null? ((double)0): close_commission_tax.doubleValue() );
  }


  /** 
   * <em>close_commission_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCloseCommissionTaxIsNull()
  {
    return close_commission_tax == null;
  }


  /** 
   * <em>close_commission_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseCommissionTaxIsSet() {
    return close_commission_tax_is_set;
  }


  /** 
   * <em>close_commission_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseCommissionTaxIsModified() {
    return close_commission_tax_is_modified;
  }


  /** 
   * <em>management_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getManagementFee()
  {
    return ( management_fee==null? ((double)0): management_fee.doubleValue() );
  }


  /** 
   * <em>management_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getManagementFeeIsNull()
  {
    return management_fee == null;
  }


  /** 
   * <em>management_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getManagementFeeIsSet() {
    return management_fee_is_set;
  }


  /** 
   * <em>management_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getManagementFeeIsModified() {
    return management_fee_is_modified;
  }


  /** 
   * <em>management_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getManagementFeeTax()
  {
    return ( management_fee_tax==null? ((double)0): management_fee_tax.doubleValue() );
  }


  /** 
   * <em>management_fee_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getManagementFeeTaxIsNull()
  {
    return management_fee_tax == null;
  }


  /** 
   * <em>management_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getManagementFeeTaxIsSet() {
    return management_fee_tax_is_set;
  }


  /** 
   * <em>management_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getManagementFeeTaxIsModified() {
    return management_fee_tax_is_modified;
  }


  /** 
   * <em>name_transfer_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNameTransferFee()
  {
    return ( name_transfer_fee==null? ((double)0): name_transfer_fee.doubleValue() );
  }


  /** 
   * <em>name_transfer_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNameTransferFeeIsNull()
  {
    return name_transfer_fee == null;
  }


  /** 
   * <em>name_transfer_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameTransferFeeIsSet() {
    return name_transfer_fee_is_set;
  }


  /** 
   * <em>name_transfer_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameTransferFeeIsModified() {
    return name_transfer_fee_is_modified;
  }


  /** 
   * <em>name_transfer_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNameTransferFeeTax()
  {
    return ( name_transfer_fee_tax==null? ((double)0): name_transfer_fee_tax.doubleValue() );
  }


  /** 
   * <em>name_transfer_fee_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNameTransferFeeTaxIsNull()
  {
    return name_transfer_fee_tax == null;
  }


  /** 
   * <em>name_transfer_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameTransferFeeTaxIsSet() {
    return name_transfer_fee_tax_is_set;
  }


  /** 
   * <em>name_transfer_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameTransferFeeTaxIsModified() {
    return name_transfer_fee_tax_is_modified;
  }


  /** 
   * <em>account_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountDiv()
  {
    return account_div;
  }


  /** 
   * <em>account_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountDivIsSet() {
    return account_div_is_set;
  }


  /** 
   * <em>account_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountDivIsModified() {
    return account_div_is_modified;
  }


  /** 
   * <em>capital_gain_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCapitalGainTax()
  {
    return ( capital_gain_tax==null? ((double)0): capital_gain_tax.doubleValue() );
  }


  /** 
   * <em>capital_gain_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCapitalGainTaxIsNull()
  {
    return capital_gain_tax == null;
  }


  /** 
   * <em>capital_gain_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainTaxIsSet() {
    return capital_gain_tax_is_set;
  }


  /** 
   * <em>capital_gain_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainTaxIsModified() {
    return capital_gain_tax_is_modified;
  }


  /** 
   * <em>debit_daily_interest</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDebitDailyInterest()
  {
    return ( debit_daily_interest==null? ((double)0): debit_daily_interest.doubleValue() );
  }


  /** 
   * <em>debit_daily_interest</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDebitDailyInterestIsNull()
  {
    return debit_daily_interest == null;
  }


  /** 
   * <em>debit_daily_interest</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitDailyInterestIsSet() {
    return debit_daily_interest_is_set;
  }


  /** 
   * <em>debit_daily_interest</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitDailyInterestIsModified() {
    return debit_daily_interest_is_modified;
  }


  /** 
   * <em>credit_daily_interest</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCreditDailyInterest()
  {
    return ( credit_daily_interest==null? ((double)0): credit_daily_interest.doubleValue() );
  }


  /** 
   * <em>credit_daily_interest</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCreditDailyInterestIsNull()
  {
    return credit_daily_interest == null;
  }


  /** 
   * <em>credit_daily_interest</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreditDailyInterestIsSet() {
    return credit_daily_interest_is_set;
  }


  /** 
   * <em>credit_daily_interest</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreditDailyInterestIsModified() {
    return credit_daily_interest_is_modified;
  }


  /** 
   * <em>loan_equity_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLoanEquityFee()
  {
    return ( loan_equity_fee==null? ((double)0): loan_equity_fee.doubleValue() );
  }


  /** 
   * <em>loan_equity_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLoanEquityFeeIsNull()
  {
    return loan_equity_fee == null;
  }


  /** 
   * <em>loan_equity_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoanEquityFeeIsSet() {
    return loan_equity_fee_is_set;
  }


  /** 
   * <em>loan_equity_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoanEquityFeeIsModified() {
    return loan_equity_fee_is_modified;
  }


  /** 
   * <em>dividend_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDividendAmount()
  {
    return ( dividend_amount==null? ((double)0): dividend_amount.doubleValue() );
  }


  /** 
   * <em>dividend_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDividendAmountIsNull()
  {
    return dividend_amount == null;
  }


  /** 
   * <em>dividend_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDividendAmountIsSet() {
    return dividend_amount_is_set;
  }


  /** 
   * <em>dividend_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDividendAmountIsModified() {
    return dividend_amount_is_modified;
  }


  /** 
   * <em>adjust_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAdjustAmount()
  {
    return ( adjust_amount==null? ((double)0): adjust_amount.doubleValue() );
  }


  /** 
   * <em>adjust_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAdjustAmountIsNull()
  {
    return adjust_amount == null;
  }


  /** 
   * <em>adjust_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdjustAmountIsSet() {
    return adjust_amount_is_set;
  }


  /** 
   * <em>adjust_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdjustAmountIsModified() {
    return adjust_amount_is_modified;
  }


  /** 
   * <em>net_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNetAmount()
  {
    return ( net_amount==null? ((double)0): net_amount.doubleValue() );
  }


  /** 
   * <em>net_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNetAmountIsNull()
  {
    return net_amount == null;
  }


  /** 
   * <em>net_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetAmountIsSet() {
    return net_amount_is_set;
  }


  /** 
   * <em>net_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetAmountIsModified() {
    return net_amount_is_modified;
  }


  /** 
   * <em>repayment_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepaymentType()
  {
    return repayment_type;
  }


  /** 
   * <em>repayment_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepaymentTypeIsSet() {
    return repayment_type_is_set;
  }


  /** 
   * <em>repayment_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepaymentTypeIsModified() {
    return repayment_type_is_modified;
  }


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsModified() {
    return status_is_modified;
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
    return new SettleDetailHistoryPK(settle_detail_history_id);
  }


  /** 
   * <em>settle_detail_history_id</em>カラムの値を設定します。 
   *
   * @@param p_settleDetailHistoryId <em>settle_detail_history_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setSettleDetailHistoryId( long p_settleDetailHistoryId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    settle_detail_history_id = p_settleDetailHistoryId;
    settle_detail_history_id_is_set = true;
    settle_detail_history_id_is_modified = true;
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
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
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
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
   * <em>delivery_date</em>カラムの値を設定します。 
   *
   * @@param p_deliveryDate <em>delivery_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDeliveryDate( java.sql.Timestamp p_deliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = p_deliveryDate;
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>delivery_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


  /** 
   * <em>commodity_code</em>カラムの値を設定します。 
   *
   * @@param p_commodityCode <em>commodity_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCommodityCode( String p_commodityCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commodity_code = p_commodityCode;
    commodity_code_is_set = true;
    commodity_code_is_modified = true;
  }


  /** 
   * <em>trade_code</em>カラムの値を設定します。 
   *
   * @@param p_tradeCode <em>trade_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setTradeCode( String p_tradeCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_code = p_tradeCode;
    trade_code_is_set = true;
    trade_code_is_modified = true;
  }


  /** 
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>market_div</em>カラムの値を設定します。 
   *
   * @@param p_marketDiv <em>market_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarketDiv( String p_marketDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_div = p_marketDiv;
    market_div_is_set = true;
    market_div_is_modified = true;
  }


  /** 
   * <em>buy_sell_div</em>カラムの値を設定します。 
   *
   * @@param p_buySellDiv <em>buy_sell_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBuySellDiv( String p_buySellDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_sell_div = p_buySellDiv;
    buy_sell_div_is_set = true;
    buy_sell_div_is_modified = true;
  }


  /** 
   * <em>open_exec_date</em>カラムの値を設定します。 
   *
   * @@param p_openExecDate <em>open_exec_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOpenExecDate( java.sql.Timestamp p_openExecDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_exec_date = p_openExecDate;
    open_exec_date_is_set = true;
    open_exec_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>open_exec_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOpenExecDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_exec_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    open_exec_date_is_set = true;
    open_exec_date_is_modified = true;
  }


  /** 
   * <em>close_exec_date</em>カラムの値を設定します。 
   *
   * @@param p_closeExecDate <em>close_exec_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCloseExecDate( java.sql.Timestamp p_closeExecDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_exec_date = p_closeExecDate;
    close_exec_date_is_set = true;
    close_exec_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>close_exec_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCloseExecDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    close_exec_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    close_exec_date_is_set = true;
    close_exec_date_is_modified = true;
  }


  /** 
   * <em>contract_price</em>カラムの値を設定します。 
   *
   * @@param p_contractPrice <em>contract_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractPrice( double p_contractPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_price = new Double(p_contractPrice);
    contract_price_is_set = true;
    contract_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_price</em>カラムに値を設定します。 
   */
  public final void setContractPrice( Double p_contractPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_price = p_contractPrice;
    contract_price_is_set = true;
    contract_price_is_modified = true;
  }


  /** 
   * <em>close_contract_price</em>カラムの値を設定します。 
   *
   * @@param p_closeContractPrice <em>close_contract_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCloseContractPrice( double p_closeContractPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_contract_price = new Double(p_closeContractPrice);
    close_contract_price_is_set = true;
    close_contract_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>close_contract_price</em>カラムに値を設定します。 
   */
  public final void setCloseContractPrice( Double p_closeContractPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    close_contract_price = p_closeContractPrice;
    close_contract_price_is_set = true;
    close_contract_price_is_modified = true;
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
   * <em>close_contract_amount</em>カラムの値を設定します。 
   *
   * @@param p_closeContractAmount <em>close_contract_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCloseContractAmount( double p_closeContractAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_contract_amount = new Double(p_closeContractAmount);
    close_contract_amount_is_set = true;
    close_contract_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>close_contract_amount</em>カラムに値を設定します。 
   */
  public final void setCloseContractAmount( Double p_closeContractAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    close_contract_amount = p_closeContractAmount;
    close_contract_amount_is_set = true;
    close_contract_amount_is_modified = true;
  }


  /** 
   * <em>open_commission</em>カラムの値を設定します。 
   *
   * @@param p_openCommission <em>open_commission</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOpenCommission( double p_openCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_commission = new Double(p_openCommission);
    open_commission_is_set = true;
    open_commission_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>open_commission</em>カラムに値を設定します。 
   */
  public final void setOpenCommission( Double p_openCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_commission = p_openCommission;
    open_commission_is_set = true;
    open_commission_is_modified = true;
  }


  /** 
   * <em>close_commission</em>カラムの値を設定します。 
   *
   * @@param p_closeCommission <em>close_commission</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCloseCommission( double p_closeCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_commission = new Double(p_closeCommission);
    close_commission_is_set = true;
    close_commission_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>close_commission</em>カラムに値を設定します。 
   */
  public final void setCloseCommission( Double p_closeCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    close_commission = p_closeCommission;
    close_commission_is_set = true;
    close_commission_is_modified = true;
  }


  /** 
   * <em>open_commission_tax</em>カラムの値を設定します。 
   *
   * @@param p_openCommissionTax <em>open_commission_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOpenCommissionTax( double p_openCommissionTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_commission_tax = new Double(p_openCommissionTax);
    open_commission_tax_is_set = true;
    open_commission_tax_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>open_commission_tax</em>カラムに値を設定します。 
   */
  public final void setOpenCommissionTax( Double p_openCommissionTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_commission_tax = p_openCommissionTax;
    open_commission_tax_is_set = true;
    open_commission_tax_is_modified = true;
  }


  /** 
   * <em>close_commission_tax</em>カラムの値を設定します。 
   *
   * @@param p_closeCommissionTax <em>close_commission_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCloseCommissionTax( double p_closeCommissionTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_commission_tax = new Double(p_closeCommissionTax);
    close_commission_tax_is_set = true;
    close_commission_tax_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>close_commission_tax</em>カラムに値を設定します。 
   */
  public final void setCloseCommissionTax( Double p_closeCommissionTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    close_commission_tax = p_closeCommissionTax;
    close_commission_tax_is_set = true;
    close_commission_tax_is_modified = true;
  }


  /** 
   * <em>management_fee</em>カラムの値を設定します。 
   *
   * @@param p_managementFee <em>management_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setManagementFee( double p_managementFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    management_fee = new Double(p_managementFee);
    management_fee_is_set = true;
    management_fee_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>management_fee</em>カラムに値を設定します。 
   */
  public final void setManagementFee( Double p_managementFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    management_fee = p_managementFee;
    management_fee_is_set = true;
    management_fee_is_modified = true;
  }


  /** 
   * <em>management_fee_tax</em>カラムの値を設定します。 
   *
   * @@param p_managementFeeTax <em>management_fee_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setManagementFeeTax( double p_managementFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    management_fee_tax = new Double(p_managementFeeTax);
    management_fee_tax_is_set = true;
    management_fee_tax_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>management_fee_tax</em>カラムに値を設定します。 
   */
  public final void setManagementFeeTax( Double p_managementFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    management_fee_tax = p_managementFeeTax;
    management_fee_tax_is_set = true;
    management_fee_tax_is_modified = true;
  }


  /** 
   * <em>name_transfer_fee</em>カラムの値を設定します。 
   *
   * @@param p_nameTransferFee <em>name_transfer_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNameTransferFee( double p_nameTransferFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name_transfer_fee = new Double(p_nameTransferFee);
    name_transfer_fee_is_set = true;
    name_transfer_fee_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>name_transfer_fee</em>カラムに値を設定します。 
   */
  public final void setNameTransferFee( Double p_nameTransferFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    name_transfer_fee = p_nameTransferFee;
    name_transfer_fee_is_set = true;
    name_transfer_fee_is_modified = true;
  }


  /** 
   * <em>name_transfer_fee_tax</em>カラムの値を設定します。 
   *
   * @@param p_nameTransferFeeTax <em>name_transfer_fee_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNameTransferFeeTax( double p_nameTransferFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name_transfer_fee_tax = new Double(p_nameTransferFeeTax);
    name_transfer_fee_tax_is_set = true;
    name_transfer_fee_tax_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>name_transfer_fee_tax</em>カラムに値を設定します。 
   */
  public final void setNameTransferFeeTax( Double p_nameTransferFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    name_transfer_fee_tax = p_nameTransferFeeTax;
    name_transfer_fee_tax_is_set = true;
    name_transfer_fee_tax_is_modified = true;
  }


  /** 
   * <em>account_div</em>カラムの値を設定します。 
   *
   * @@param p_accountDiv <em>account_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountDiv( String p_accountDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_div = p_accountDiv;
    account_div_is_set = true;
    account_div_is_modified = true;
  }


  /** 
   * <em>capital_gain_tax</em>カラムの値を設定します。 
   *
   * @@param p_capitalGainTax <em>capital_gain_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCapitalGainTax( double p_capitalGainTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain_tax = new Double(p_capitalGainTax);
    capital_gain_tax_is_set = true;
    capital_gain_tax_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>capital_gain_tax</em>カラムに値を設定します。 
   */
  public final void setCapitalGainTax( Double p_capitalGainTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain_tax = p_capitalGainTax;
    capital_gain_tax_is_set = true;
    capital_gain_tax_is_modified = true;
  }


  /** 
   * <em>debit_daily_interest</em>カラムの値を設定します。 
   *
   * @@param p_debitDailyInterest <em>debit_daily_interest</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDebitDailyInterest( double p_debitDailyInterest )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_daily_interest = new Double(p_debitDailyInterest);
    debit_daily_interest_is_set = true;
    debit_daily_interest_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>debit_daily_interest</em>カラムに値を設定します。 
   */
  public final void setDebitDailyInterest( Double p_debitDailyInterest )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    debit_daily_interest = p_debitDailyInterest;
    debit_daily_interest_is_set = true;
    debit_daily_interest_is_modified = true;
  }


  /** 
   * <em>credit_daily_interest</em>カラムの値を設定します。 
   *
   * @@param p_creditDailyInterest <em>credit_daily_interest</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCreditDailyInterest( double p_creditDailyInterest )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    credit_daily_interest = new Double(p_creditDailyInterest);
    credit_daily_interest_is_set = true;
    credit_daily_interest_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>credit_daily_interest</em>カラムに値を設定します。 
   */
  public final void setCreditDailyInterest( Double p_creditDailyInterest )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    credit_daily_interest = p_creditDailyInterest;
    credit_daily_interest_is_set = true;
    credit_daily_interest_is_modified = true;
  }


  /** 
   * <em>loan_equity_fee</em>カラムの値を設定します。 
   *
   * @@param p_loanEquityFee <em>loan_equity_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLoanEquityFee( double p_loanEquityFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    loan_equity_fee = new Double(p_loanEquityFee);
    loan_equity_fee_is_set = true;
    loan_equity_fee_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>loan_equity_fee</em>カラムに値を設定します。 
   */
  public final void setLoanEquityFee( Double p_loanEquityFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    loan_equity_fee = p_loanEquityFee;
    loan_equity_fee_is_set = true;
    loan_equity_fee_is_modified = true;
  }


  /** 
   * <em>dividend_amount</em>カラムの値を設定します。 
   *
   * @@param p_dividendAmount <em>dividend_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDividendAmount( double p_dividendAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dividend_amount = new Double(p_dividendAmount);
    dividend_amount_is_set = true;
    dividend_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>dividend_amount</em>カラムに値を設定します。 
   */
  public final void setDividendAmount( Double p_dividendAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    dividend_amount = p_dividendAmount;
    dividend_amount_is_set = true;
    dividend_amount_is_modified = true;
  }


  /** 
   * <em>adjust_amount</em>カラムの値を設定します。 
   *
   * @@param p_adjustAmount <em>adjust_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAdjustAmount( double p_adjustAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    adjust_amount = new Double(p_adjustAmount);
    adjust_amount_is_set = true;
    adjust_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>adjust_amount</em>カラムに値を設定します。 
   */
  public final void setAdjustAmount( Double p_adjustAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    adjust_amount = p_adjustAmount;
    adjust_amount_is_set = true;
    adjust_amount_is_modified = true;
  }


  /** 
   * <em>net_amount</em>カラムの値を設定します。 
   *
   * @@param p_netAmount <em>net_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNetAmount( double p_netAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_amount = new Double(p_netAmount);
    net_amount_is_set = true;
    net_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>net_amount</em>カラムに値を設定します。 
   */
  public final void setNetAmount( Double p_netAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    net_amount = p_netAmount;
    net_amount_is_set = true;
    net_amount_is_modified = true;
  }


  /** 
   * <em>repayment_type</em>カラムの値を設定します。 
   *
   * @@param p_repaymentType <em>repayment_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRepaymentType( String p_repaymentType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    repayment_type = p_repaymentType;
    repayment_type_is_set = true;
    repayment_type_is_modified = true;
  }


  /** 
   * <em>status</em>カラムの値を設定します。 
   *
   * @@param p_status <em>status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStatus( String p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
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
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("account_div") ) {
                    return this.account_div;
                }
                else if ( name.equals("adjust_amount") ) {
                    return this.adjust_amount;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("buy_sell_div") ) {
                    return this.buy_sell_div;
                }
                break;
            case 'c':
                if ( name.equals("commodity_code") ) {
                    return this.commodity_code;
                }
                else if ( name.equals("close_exec_date") ) {
                    return this.close_exec_date;
                }
                else if ( name.equals("contract_price") ) {
                    return this.contract_price;
                }
                else if ( name.equals("close_contract_price") ) {
                    return this.close_contract_price;
                }
                else if ( name.equals("contract_amount") ) {
                    return this.contract_amount;
                }
                else if ( name.equals("close_contract_amount") ) {
                    return this.close_contract_amount;
                }
                else if ( name.equals("close_commission") ) {
                    return this.close_commission;
                }
                else if ( name.equals("close_commission_tax") ) {
                    return this.close_commission_tax;
                }
                else if ( name.equals("capital_gain_tax") ) {
                    return this.capital_gain_tax;
                }
                else if ( name.equals("credit_daily_interest") ) {
                    return this.credit_daily_interest;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                else if ( name.equals("debit_daily_interest") ) {
                    return this.debit_daily_interest;
                }
                else if ( name.equals("dividend_amount") ) {
                    return this.dividend_amount;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("loan_equity_fee") ) {
                    return this.loan_equity_fee;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_div") ) {
                    return this.market_div;
                }
                else if ( name.equals("management_fee") ) {
                    return this.management_fee;
                }
                else if ( name.equals("management_fee_tax") ) {
                    return this.management_fee_tax;
                }
                break;
            case 'n':
                if ( name.equals("name_transfer_fee") ) {
                    return this.name_transfer_fee;
                }
                else if ( name.equals("name_transfer_fee_tax") ) {
                    return this.name_transfer_fee_tax;
                }
                else if ( name.equals("net_amount") ) {
                    return this.net_amount;
                }
                break;
            case 'o':
                if ( name.equals("open_exec_date") ) {
                    return this.open_exec_date;
                }
                else if ( name.equals("open_commission") ) {
                    return this.open_commission;
                }
                else if ( name.equals("open_commission_tax") ) {
                    return this.open_commission_tax;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                break;
            case 'r':
                if ( name.equals("repayment_type") ) {
                    return this.repayment_type;
                }
                break;
            case 's':
                if ( name.equals("settle_detail_history_id") ) {
                    return new Long( settle_detail_history_id );
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("trade_code") ) {
                    return this.trade_code;
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
                if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("account_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_div' must be String: '"+value+"' is not." );
                    this.account_div = (String) value;
                    if (this.account_div_is_set)
                        this.account_div_is_modified = true;
                    this.account_div_is_set = true;
                    return;
                }
                else if ( name.equals("adjust_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'adjust_amount' must be Double: '"+value+"' is not." );
                    this.adjust_amount = (Double) value;
                    if (this.adjust_amount_is_set)
                        this.adjust_amount_is_modified = true;
                    this.adjust_amount_is_set = true;
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
                else if ( name.equals("buy_sell_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_sell_div' must be String: '"+value+"' is not." );
                    this.buy_sell_div = (String) value;
                    if (this.buy_sell_div_is_set)
                        this.buy_sell_div_is_modified = true;
                    this.buy_sell_div_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("commodity_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commodity_code' must be String: '"+value+"' is not." );
                    this.commodity_code = (String) value;
                    if (this.commodity_code_is_set)
                        this.commodity_code_is_modified = true;
                    this.commodity_code_is_set = true;
                    return;
                }
                else if ( name.equals("close_exec_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'close_exec_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.close_exec_date = (java.sql.Timestamp) value;
                    if (this.close_exec_date_is_set)
                        this.close_exec_date_is_modified = true;
                    this.close_exec_date_is_set = true;
                    return;
                }
                else if ( name.equals("contract_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_price' must be Double: '"+value+"' is not." );
                    this.contract_price = (Double) value;
                    if (this.contract_price_is_set)
                        this.contract_price_is_modified = true;
                    this.contract_price_is_set = true;
                    return;
                }
                else if ( name.equals("close_contract_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'close_contract_price' must be Double: '"+value+"' is not." );
                    this.close_contract_price = (Double) value;
                    if (this.close_contract_price_is_set)
                        this.close_contract_price_is_modified = true;
                    this.close_contract_price_is_set = true;
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
                else if ( name.equals("close_contract_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'close_contract_amount' must be Double: '"+value+"' is not." );
                    this.close_contract_amount = (Double) value;
                    if (this.close_contract_amount_is_set)
                        this.close_contract_amount_is_modified = true;
                    this.close_contract_amount_is_set = true;
                    return;
                }
                else if ( name.equals("close_commission") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'close_commission' must be Double: '"+value+"' is not." );
                    this.close_commission = (Double) value;
                    if (this.close_commission_is_set)
                        this.close_commission_is_modified = true;
                    this.close_commission_is_set = true;
                    return;
                }
                else if ( name.equals("close_commission_tax") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'close_commission_tax' must be Double: '"+value+"' is not." );
                    this.close_commission_tax = (Double) value;
                    if (this.close_commission_tax_is_set)
                        this.close_commission_tax_is_modified = true;
                    this.close_commission_tax_is_set = true;
                    return;
                }
                else if ( name.equals("capital_gain_tax") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'capital_gain_tax' must be Double: '"+value+"' is not." );
                    this.capital_gain_tax = (Double) value;
                    if (this.capital_gain_tax_is_set)
                        this.capital_gain_tax_is_modified = true;
                    this.capital_gain_tax_is_set = true;
                    return;
                }
                else if ( name.equals("credit_daily_interest") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'credit_daily_interest' must be Double: '"+value+"' is not." );
                    this.credit_daily_interest = (Double) value;
                    if (this.credit_daily_interest_is_set)
                        this.credit_daily_interest_is_modified = true;
                    this.credit_daily_interest_is_set = true;
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
                if ( name.equals("delivery_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                else if ( name.equals("debit_daily_interest") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'debit_daily_interest' must be Double: '"+value+"' is not." );
                    this.debit_daily_interest = (Double) value;
                    if (this.debit_daily_interest_is_set)
                        this.debit_daily_interest_is_modified = true;
                    this.debit_daily_interest_is_set = true;
                    return;
                }
                else if ( name.equals("dividend_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'dividend_amount' must be Double: '"+value+"' is not." );
                    this.dividend_amount = (Double) value;
                    if (this.dividend_amount_is_set)
                        this.dividend_amount_is_modified = true;
                    this.dividend_amount_is_set = true;
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
                if ( name.equals("loan_equity_fee") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'loan_equity_fee' must be Double: '"+value+"' is not." );
                    this.loan_equity_fee = (Double) value;
                    if (this.loan_equity_fee_is_set)
                        this.loan_equity_fee_is_modified = true;
                    this.loan_equity_fee_is_set = true;
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
                if ( name.equals("market_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_div' must be String: '"+value+"' is not." );
                    this.market_div = (String) value;
                    if (this.market_div_is_set)
                        this.market_div_is_modified = true;
                    this.market_div_is_set = true;
                    return;
                }
                else if ( name.equals("management_fee") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'management_fee' must be Double: '"+value+"' is not." );
                    this.management_fee = (Double) value;
                    if (this.management_fee_is_set)
                        this.management_fee_is_modified = true;
                    this.management_fee_is_set = true;
                    return;
                }
                else if ( name.equals("management_fee_tax") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'management_fee_tax' must be Double: '"+value+"' is not." );
                    this.management_fee_tax = (Double) value;
                    if (this.management_fee_tax_is_set)
                        this.management_fee_tax_is_modified = true;
                    this.management_fee_tax_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("name_transfer_fee") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'name_transfer_fee' must be Double: '"+value+"' is not." );
                    this.name_transfer_fee = (Double) value;
                    if (this.name_transfer_fee_is_set)
                        this.name_transfer_fee_is_modified = true;
                    this.name_transfer_fee_is_set = true;
                    return;
                }
                else if ( name.equals("name_transfer_fee_tax") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'name_transfer_fee_tax' must be Double: '"+value+"' is not." );
                    this.name_transfer_fee_tax = (Double) value;
                    if (this.name_transfer_fee_tax_is_set)
                        this.name_transfer_fee_tax_is_modified = true;
                    this.name_transfer_fee_tax_is_set = true;
                    return;
                }
                else if ( name.equals("net_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'net_amount' must be Double: '"+value+"' is not." );
                    this.net_amount = (Double) value;
                    if (this.net_amount_is_set)
                        this.net_amount_is_modified = true;
                    this.net_amount_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("open_exec_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'open_exec_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.open_exec_date = (java.sql.Timestamp) value;
                    if (this.open_exec_date_is_set)
                        this.open_exec_date_is_modified = true;
                    this.open_exec_date_is_set = true;
                    return;
                }
                else if ( name.equals("open_commission") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'open_commission' must be Double: '"+value+"' is not." );
                    this.open_commission = (Double) value;
                    if (this.open_commission_is_set)
                        this.open_commission_is_modified = true;
                    this.open_commission_is_set = true;
                    return;
                }
                else if ( name.equals("open_commission_tax") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'open_commission_tax' must be Double: '"+value+"' is not." );
                    this.open_commission_tax = (Double) value;
                    if (this.open_commission_tax_is_set)
                        this.open_commission_tax_is_modified = true;
                    this.open_commission_tax_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("repayment_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'repayment_type' must be String: '"+value+"' is not." );
                    this.repayment_type = (String) value;
                    if (this.repayment_type_is_set)
                        this.repayment_type_is_modified = true;
                    this.repayment_type_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("settle_detail_history_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'settle_detail_history_id' must be Long: '"+value+"' is not." );
                    this.settle_detail_history_id = ((Long) value).longValue();
                    if (this.settle_detail_history_id_is_set)
                        this.settle_detail_history_id_is_modified = true;
                    this.settle_detail_history_id_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
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
                else if ( name.equals("trade_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trade_code' must be String: '"+value+"' is not." );
                    this.trade_code = (String) value;
                    if (this.trade_code_is_set)
                        this.trade_code_is_modified = true;
                    this.trade_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
