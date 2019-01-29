head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.47.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TradeDetailHistoryParams.java;


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
 * trade_detail_historyテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link TradeDetailHistoryRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link TradeDetailHistoryParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link TradeDetailHistoryParams}が{@@link TradeDetailHistoryRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TradeDetailHistoryPK 
 * @@see TradeDetailHistoryRow 
 */
public class TradeDetailHistoryParams extends Params implements TradeDetailHistoryRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "trade_detail_history";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = TradeDetailHistoryRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return TradeDetailHistoryRow.TYPE;
  }


  /** 
   * <em>trade_detail_history_id</em>カラムの値 
   */
  public  long  trade_detail_history_id;

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
   * <em>exec_date</em>カラムの値 
   */
  public  java.sql.Timestamp  exec_date;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  Double  quantity;

  /** 
   * <em>price</em>カラムの値 
   */
  public  Double  price;

  /** 
   * <em>executed_amount</em>カラムの値 
   */
  public  Double  executed_amount;

  /** 
   * <em>commission_fee</em>カラムの値 
   */
  public  Double  commission_fee;

  /** 
   * <em>commission_fee_tax</em>カラムの値 
   */
  public  Double  commission_fee_tax;

  /** 
   * <em>account_div</em>カラムの値 
   */
  public  String  account_div;

  /** 
   * <em>capital_gain_tax</em>カラムの値 
   */
  public  Double  capital_gain_tax;

  /** 
   * <em>net_amount</em>カラムの値 
   */
  public  Double  net_amount;

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

  /** 
   * <em>monetary_unit</em>カラムの値 
   */
  public  String  monetary_unit;

  /** 
   * <em>fstk_exch_div</em>カラムの値 
   */
  public  String  fstk_exch_div;

  /** 
   * <em>monetary_unit_executed_amount</em>カラムの値 
   */
  public  Double  monetary_unit_executed_amount;

  /** 
   * <em>monetary_unit_comission</em>カラムの値 
   */
  public  Double  monetary_unit_comission;

  /** 
   * <em>monetary_unit_trade_tax</em>カラムの値 
   */
  public  Double  monetary_unit_trade_tax;

  /** 
   * <em>monetary_unit_interest</em>カラムの値 
   */
  public  Double  monetary_unit_interest;

  /** 
   * <em>local_settle_amount</em>カラムの値 
   */
  public  Double  local_settle_amount;

  /** 
   * <em>local_settle_amount_yen</em>カラムの値 
   */
  public  Double  local_settle_amount_yen;

  /** 
   * <em>exec_exchange</em>カラムの値 
   */
  public  Double  exec_exchange;

  /** 
   * <em>domestic_commission</em>カラムの値 
   */
  public  Double  domestic_commission;

  /** 
   * <em>payment_div</em>カラムの値 
   */
  public  String  payment_div;

  /** 
   * <em>accrued_interest_yen</em>カラムの値 
   */
  public  Double  accrued_interest_yen;

  /** 
   * <em>accrued_interest</em>カラムの値 
   */
  public  Double  accrued_interest;

  /** 
   * <em>denomination</em>カラムの値 
   */
  public  Double  denomination;

  private boolean trade_detail_history_id_is_set = false;
  private boolean trade_detail_history_id_is_modified = false;
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
  private boolean exec_date_is_set = false;
  private boolean exec_date_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean executed_amount_is_set = false;
  private boolean executed_amount_is_modified = false;
  private boolean commission_fee_is_set = false;
  private boolean commission_fee_is_modified = false;
  private boolean commission_fee_tax_is_set = false;
  private boolean commission_fee_tax_is_modified = false;
  private boolean account_div_is_set = false;
  private boolean account_div_is_modified = false;
  private boolean capital_gain_tax_is_set = false;
  private boolean capital_gain_tax_is_modified = false;
  private boolean net_amount_is_set = false;
  private boolean net_amount_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean monetary_unit_is_set = false;
  private boolean monetary_unit_is_modified = false;
  private boolean fstk_exch_div_is_set = false;
  private boolean fstk_exch_div_is_modified = false;
  private boolean monetary_unit_executed_amount_is_set = false;
  private boolean monetary_unit_executed_amount_is_modified = false;
  private boolean monetary_unit_comission_is_set = false;
  private boolean monetary_unit_comission_is_modified = false;
  private boolean monetary_unit_trade_tax_is_set = false;
  private boolean monetary_unit_trade_tax_is_modified = false;
  private boolean monetary_unit_interest_is_set = false;
  private boolean monetary_unit_interest_is_modified = false;
  private boolean local_settle_amount_is_set = false;
  private boolean local_settle_amount_is_modified = false;
  private boolean local_settle_amount_yen_is_set = false;
  private boolean local_settle_amount_yen_is_modified = false;
  private boolean exec_exchange_is_set = false;
  private boolean exec_exchange_is_modified = false;
  private boolean domestic_commission_is_set = false;
  private boolean domestic_commission_is_modified = false;
  private boolean payment_div_is_set = false;
  private boolean payment_div_is_modified = false;
  private boolean accrued_interest_yen_is_set = false;
  private boolean accrued_interest_yen_is_modified = false;
  private boolean accrued_interest_is_set = false;
  private boolean accrued_interest_is_modified = false;
  private boolean denomination_is_set = false;
  private boolean denomination_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "trade_detail_history_id=" + trade_detail_history_id
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
      + "," + "exec_date=" +exec_date
      + "," + "quantity=" +quantity
      + "," + "price=" +price
      + "," + "executed_amount=" +executed_amount
      + "," + "commission_fee=" +commission_fee
      + "," + "commission_fee_tax=" +commission_fee_tax
      + "," + "account_div=" +account_div
      + "," + "capital_gain_tax=" +capital_gain_tax
      + "," + "net_amount=" +net_amount
      + "," + "status=" +status
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "monetary_unit=" +monetary_unit
      + "," + "fstk_exch_div=" +fstk_exch_div
      + "," + "monetary_unit_executed_amount=" +monetary_unit_executed_amount
      + "," + "monetary_unit_comission=" +monetary_unit_comission
      + "," + "monetary_unit_trade_tax=" +monetary_unit_trade_tax
      + "," + "monetary_unit_interest=" +monetary_unit_interest
      + "," + "local_settle_amount=" +local_settle_amount
      + "," + "local_settle_amount_yen=" +local_settle_amount_yen
      + "," + "exec_exchange=" +exec_exchange
      + "," + "domestic_commission=" +domestic_commission
      + "," + "payment_div=" +payment_div
      + "," + "accrued_interest_yen=" +accrued_interest_yen
      + "," + "accrued_interest=" +accrued_interest
      + "," + "denomination=" +denomination
      + "}";
  }


  /** 
   * 値が未設定のTradeDetailHistoryParamsオブジェクトを作成します。 
   */
  public TradeDetailHistoryParams() {
    trade_detail_history_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のTradeDetailHistoryRowオブジェクトの値を利用してTradeDetailHistoryParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するTradeDetailHistoryRowオブジェクト 
   */
  public TradeDetailHistoryParams( TradeDetailHistoryRow p_row )
  {
    trade_detail_history_id = p_row.getTradeDetailHistoryId();
    trade_detail_history_id_is_set = p_row.getTradeDetailHistoryIdIsSet();
    trade_detail_history_id_is_modified = p_row.getTradeDetailHistoryIdIsModified();
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
    exec_date = p_row.getExecDate();
    exec_date_is_set = p_row.getExecDateIsSet();
    exec_date_is_modified = p_row.getExecDateIsModified();
    if ( !p_row.getQuantityIsNull() )
      quantity = new Double( p_row.getQuantity() );
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    if ( !p_row.getPriceIsNull() )
      price = new Double( p_row.getPrice() );
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    if ( !p_row.getExecutedAmountIsNull() )
      executed_amount = new Double( p_row.getExecutedAmount() );
    executed_amount_is_set = p_row.getExecutedAmountIsSet();
    executed_amount_is_modified = p_row.getExecutedAmountIsModified();
    if ( !p_row.getCommissionFeeIsNull() )
      commission_fee = new Double( p_row.getCommissionFee() );
    commission_fee_is_set = p_row.getCommissionFeeIsSet();
    commission_fee_is_modified = p_row.getCommissionFeeIsModified();
    if ( !p_row.getCommissionFeeTaxIsNull() )
      commission_fee_tax = new Double( p_row.getCommissionFeeTax() );
    commission_fee_tax_is_set = p_row.getCommissionFeeTaxIsSet();
    commission_fee_tax_is_modified = p_row.getCommissionFeeTaxIsModified();
    account_div = p_row.getAccountDiv();
    account_div_is_set = p_row.getAccountDivIsSet();
    account_div_is_modified = p_row.getAccountDivIsModified();
    if ( !p_row.getCapitalGainTaxIsNull() )
      capital_gain_tax = new Double( p_row.getCapitalGainTax() );
    capital_gain_tax_is_set = p_row.getCapitalGainTaxIsSet();
    capital_gain_tax_is_modified = p_row.getCapitalGainTaxIsModified();
    if ( !p_row.getNetAmountIsNull() )
      net_amount = new Double( p_row.getNetAmount() );
    net_amount_is_set = p_row.getNetAmountIsSet();
    net_amount_is_modified = p_row.getNetAmountIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    monetary_unit = p_row.getMonetaryUnit();
    monetary_unit_is_set = p_row.getMonetaryUnitIsSet();
    monetary_unit_is_modified = p_row.getMonetaryUnitIsModified();
    fstk_exch_div = p_row.getFstkExchDiv();
    fstk_exch_div_is_set = p_row.getFstkExchDivIsSet();
    fstk_exch_div_is_modified = p_row.getFstkExchDivIsModified();
    if ( !p_row.getMonetaryUnitExecutedAmountIsNull() )
      monetary_unit_executed_amount = new Double( p_row.getMonetaryUnitExecutedAmount() );
    monetary_unit_executed_amount_is_set = p_row.getMonetaryUnitExecutedAmountIsSet();
    monetary_unit_executed_amount_is_modified = p_row.getMonetaryUnitExecutedAmountIsModified();
    if ( !p_row.getMonetaryUnitComissionIsNull() )
      monetary_unit_comission = new Double( p_row.getMonetaryUnitComission() );
    monetary_unit_comission_is_set = p_row.getMonetaryUnitComissionIsSet();
    monetary_unit_comission_is_modified = p_row.getMonetaryUnitComissionIsModified();
    if ( !p_row.getMonetaryUnitTradeTaxIsNull() )
      monetary_unit_trade_tax = new Double( p_row.getMonetaryUnitTradeTax() );
    monetary_unit_trade_tax_is_set = p_row.getMonetaryUnitTradeTaxIsSet();
    monetary_unit_trade_tax_is_modified = p_row.getMonetaryUnitTradeTaxIsModified();
    if ( !p_row.getMonetaryUnitInterestIsNull() )
      monetary_unit_interest = new Double( p_row.getMonetaryUnitInterest() );
    monetary_unit_interest_is_set = p_row.getMonetaryUnitInterestIsSet();
    monetary_unit_interest_is_modified = p_row.getMonetaryUnitInterestIsModified();
    if ( !p_row.getLocalSettleAmountIsNull() )
      local_settle_amount = new Double( p_row.getLocalSettleAmount() );
    local_settle_amount_is_set = p_row.getLocalSettleAmountIsSet();
    local_settle_amount_is_modified = p_row.getLocalSettleAmountIsModified();
    if ( !p_row.getLocalSettleAmountYenIsNull() )
      local_settle_amount_yen = new Double( p_row.getLocalSettleAmountYen() );
    local_settle_amount_yen_is_set = p_row.getLocalSettleAmountYenIsSet();
    local_settle_amount_yen_is_modified = p_row.getLocalSettleAmountYenIsModified();
    if ( !p_row.getExecExchangeIsNull() )
      exec_exchange = new Double( p_row.getExecExchange() );
    exec_exchange_is_set = p_row.getExecExchangeIsSet();
    exec_exchange_is_modified = p_row.getExecExchangeIsModified();
    if ( !p_row.getDomesticCommissionIsNull() )
      domestic_commission = new Double( p_row.getDomesticCommission() );
    domestic_commission_is_set = p_row.getDomesticCommissionIsSet();
    domestic_commission_is_modified = p_row.getDomesticCommissionIsModified();
    payment_div = p_row.getPaymentDiv();
    payment_div_is_set = p_row.getPaymentDivIsSet();
    payment_div_is_modified = p_row.getPaymentDivIsModified();
    if ( !p_row.getAccruedInterestYenIsNull() )
      accrued_interest_yen = new Double( p_row.getAccruedInterestYen() );
    accrued_interest_yen_is_set = p_row.getAccruedInterestYenIsSet();
    accrued_interest_yen_is_modified = p_row.getAccruedInterestYenIsModified();
    if ( !p_row.getAccruedInterestIsNull() )
      accrued_interest = new Double( p_row.getAccruedInterest() );
    accrued_interest_is_set = p_row.getAccruedInterestIsSet();
    accrued_interest_is_modified = p_row.getAccruedInterestIsModified();
    if ( !p_row.getDenominationIsNull() )
      denomination = new Double( p_row.getDenomination() );
    denomination_is_set = p_row.getDenominationIsSet();
    denomination_is_modified = p_row.getDenominationIsModified();
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
      exec_date_is_set = true;
      exec_date_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      executed_amount_is_set = true;
      executed_amount_is_modified = true;
      commission_fee_is_set = true;
      commission_fee_is_modified = true;
      commission_fee_tax_is_set = true;
      commission_fee_tax_is_modified = true;
      account_div_is_set = true;
      account_div_is_modified = true;
      capital_gain_tax_is_set = true;
      capital_gain_tax_is_modified = true;
      net_amount_is_set = true;
      net_amount_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      monetary_unit_is_set = true;
      monetary_unit_is_modified = true;
      fstk_exch_div_is_set = true;
      fstk_exch_div_is_modified = true;
      monetary_unit_executed_amount_is_set = true;
      monetary_unit_executed_amount_is_modified = true;
      monetary_unit_comission_is_set = true;
      monetary_unit_comission_is_modified = true;
      monetary_unit_trade_tax_is_set = true;
      monetary_unit_trade_tax_is_modified = true;
      monetary_unit_interest_is_set = true;
      monetary_unit_interest_is_modified = true;
      local_settle_amount_is_set = true;
      local_settle_amount_is_modified = true;
      local_settle_amount_yen_is_set = true;
      local_settle_amount_yen_is_modified = true;
      exec_exchange_is_set = true;
      exec_exchange_is_modified = true;
      domestic_commission_is_set = true;
      domestic_commission_is_modified = true;
      payment_div_is_set = true;
      payment_div_is_modified = true;
      accrued_interest_yen_is_set = true;
      accrued_interest_yen_is_modified = true;
      accrued_interest_is_set = true;
      accrued_interest_is_modified = true;
      denomination_is_set = true;
      denomination_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof TradeDetailHistoryRow ) )
       return false;
    return fieldsEqual( (TradeDetailHistoryRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のTradeDetailHistoryRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( TradeDetailHistoryRow row )
  {
    if ( trade_detail_history_id != row.getTradeDetailHistoryId() )
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
    if ( exec_date == null ) {
      if ( row.getExecDate() != null )
        return false;
    } else if ( !exec_date.equals( row.getExecDate() ) ) {
        return false;
    }
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.doubleValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.doubleValue() != row.getPrice() ) ) {
        return false;
    }
    if ( executed_amount == null ) {
      if ( !row.getExecutedAmountIsNull() )
        return false;
    } else if ( row.getExecutedAmountIsNull() || ( executed_amount.doubleValue() != row.getExecutedAmount() ) ) {
        return false;
    }
    if ( commission_fee == null ) {
      if ( !row.getCommissionFeeIsNull() )
        return false;
    } else if ( row.getCommissionFeeIsNull() || ( commission_fee.doubleValue() != row.getCommissionFee() ) ) {
        return false;
    }
    if ( commission_fee_tax == null ) {
      if ( !row.getCommissionFeeTaxIsNull() )
        return false;
    } else if ( row.getCommissionFeeTaxIsNull() || ( commission_fee_tax.doubleValue() != row.getCommissionFeeTax() ) ) {
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
    if ( net_amount == null ) {
      if ( !row.getNetAmountIsNull() )
        return false;
    } else if ( row.getNetAmountIsNull() || ( net_amount.doubleValue() != row.getNetAmount() ) ) {
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
    if ( monetary_unit == null ) {
      if ( row.getMonetaryUnit() != null )
        return false;
    } else if ( !monetary_unit.equals( row.getMonetaryUnit() ) ) {
        return false;
    }
    if ( fstk_exch_div == null ) {
      if ( row.getFstkExchDiv() != null )
        return false;
    } else if ( !fstk_exch_div.equals( row.getFstkExchDiv() ) ) {
        return false;
    }
    if ( monetary_unit_executed_amount == null ) {
      if ( !row.getMonetaryUnitExecutedAmountIsNull() )
        return false;
    } else if ( row.getMonetaryUnitExecutedAmountIsNull() || ( monetary_unit_executed_amount.doubleValue() != row.getMonetaryUnitExecutedAmount() ) ) {
        return false;
    }
    if ( monetary_unit_comission == null ) {
      if ( !row.getMonetaryUnitComissionIsNull() )
        return false;
    } else if ( row.getMonetaryUnitComissionIsNull() || ( monetary_unit_comission.doubleValue() != row.getMonetaryUnitComission() ) ) {
        return false;
    }
    if ( monetary_unit_trade_tax == null ) {
      if ( !row.getMonetaryUnitTradeTaxIsNull() )
        return false;
    } else if ( row.getMonetaryUnitTradeTaxIsNull() || ( monetary_unit_trade_tax.doubleValue() != row.getMonetaryUnitTradeTax() ) ) {
        return false;
    }
    if ( monetary_unit_interest == null ) {
      if ( !row.getMonetaryUnitInterestIsNull() )
        return false;
    } else if ( row.getMonetaryUnitInterestIsNull() || ( monetary_unit_interest.doubleValue() != row.getMonetaryUnitInterest() ) ) {
        return false;
    }
    if ( local_settle_amount == null ) {
      if ( !row.getLocalSettleAmountIsNull() )
        return false;
    } else if ( row.getLocalSettleAmountIsNull() || ( local_settle_amount.doubleValue() != row.getLocalSettleAmount() ) ) {
        return false;
    }
    if ( local_settle_amount_yen == null ) {
      if ( !row.getLocalSettleAmountYenIsNull() )
        return false;
    } else if ( row.getLocalSettleAmountYenIsNull() || ( local_settle_amount_yen.doubleValue() != row.getLocalSettleAmountYen() ) ) {
        return false;
    }
    if ( exec_exchange == null ) {
      if ( !row.getExecExchangeIsNull() )
        return false;
    } else if ( row.getExecExchangeIsNull() || ( exec_exchange.doubleValue() != row.getExecExchange() ) ) {
        return false;
    }
    if ( domestic_commission == null ) {
      if ( !row.getDomesticCommissionIsNull() )
        return false;
    } else if ( row.getDomesticCommissionIsNull() || ( domestic_commission.doubleValue() != row.getDomesticCommission() ) ) {
        return false;
    }
    if ( payment_div == null ) {
      if ( row.getPaymentDiv() != null )
        return false;
    } else if ( !payment_div.equals( row.getPaymentDiv() ) ) {
        return false;
    }
    if ( accrued_interest_yen == null ) {
      if ( !row.getAccruedInterestYenIsNull() )
        return false;
    } else if ( row.getAccruedInterestYenIsNull() || ( accrued_interest_yen.doubleValue() != row.getAccruedInterestYen() ) ) {
        return false;
    }
    if ( accrued_interest == null ) {
      if ( !row.getAccruedInterestIsNull() )
        return false;
    } else if ( row.getAccruedInterestIsNull() || ( accrued_interest.doubleValue() != row.getAccruedInterest() ) ) {
        return false;
    }
    if ( denomination == null ) {
      if ( !row.getDenominationIsNull() )
        return false;
    } else if ( row.getDenominationIsNull() || ( denomination.doubleValue() != row.getDenomination() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) trade_detail_history_id)
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
        + (exec_date!=null? exec_date.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (executed_amount!=null? executed_amount.hashCode(): 0) 
        + (commission_fee!=null? commission_fee.hashCode(): 0) 
        + (commission_fee_tax!=null? commission_fee_tax.hashCode(): 0) 
        + (account_div!=null? account_div.hashCode(): 0) 
        + (capital_gain_tax!=null? capital_gain_tax.hashCode(): 0) 
        + (net_amount!=null? net_amount.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (monetary_unit!=null? monetary_unit.hashCode(): 0) 
        + (fstk_exch_div!=null? fstk_exch_div.hashCode(): 0) 
        + (monetary_unit_executed_amount!=null? monetary_unit_executed_amount.hashCode(): 0) 
        + (monetary_unit_comission!=null? monetary_unit_comission.hashCode(): 0) 
        + (monetary_unit_trade_tax!=null? monetary_unit_trade_tax.hashCode(): 0) 
        + (monetary_unit_interest!=null? monetary_unit_interest.hashCode(): 0) 
        + (local_settle_amount!=null? local_settle_amount.hashCode(): 0) 
        + (local_settle_amount_yen!=null? local_settle_amount_yen.hashCode(): 0) 
        + (exec_exchange!=null? exec_exchange.hashCode(): 0) 
        + (domestic_commission!=null? domestic_commission.hashCode(): 0) 
        + (payment_div!=null? payment_div.hashCode(): 0) 
        + (accrued_interest_yen!=null? accrued_interest_yen.hashCode(): 0) 
        + (accrued_interest!=null? accrued_interest.hashCode(): 0) 
        + (denomination!=null? denomination.hashCode(): 0) 
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
		map.put("trade_detail_history_id",new Long(trade_detail_history_id));
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
		if ( exec_date != null )
			map.put("exec_date",exec_date);
		if ( quantity != null )
			map.put("quantity",quantity);
		if ( price != null )
			map.put("price",price);
		if ( executed_amount != null )
			map.put("executed_amount",executed_amount);
		if ( commission_fee != null )
			map.put("commission_fee",commission_fee);
		if ( commission_fee_tax != null )
			map.put("commission_fee_tax",commission_fee_tax);
		if ( account_div != null )
			map.put("account_div",account_div);
		if ( capital_gain_tax != null )
			map.put("capital_gain_tax",capital_gain_tax);
		if ( net_amount != null )
			map.put("net_amount",net_amount);
		map.put("status",status);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( monetary_unit != null )
			map.put("monetary_unit",monetary_unit);
		if ( fstk_exch_div != null )
			map.put("fstk_exch_div",fstk_exch_div);
		if ( monetary_unit_executed_amount != null )
			map.put("monetary_unit_executed_amount",monetary_unit_executed_amount);
		if ( monetary_unit_comission != null )
			map.put("monetary_unit_comission",monetary_unit_comission);
		if ( monetary_unit_trade_tax != null )
			map.put("monetary_unit_trade_tax",monetary_unit_trade_tax);
		if ( monetary_unit_interest != null )
			map.put("monetary_unit_interest",monetary_unit_interest);
		if ( local_settle_amount != null )
			map.put("local_settle_amount",local_settle_amount);
		if ( local_settle_amount_yen != null )
			map.put("local_settle_amount_yen",local_settle_amount_yen);
		if ( exec_exchange != null )
			map.put("exec_exchange",exec_exchange);
		if ( domestic_commission != null )
			map.put("domestic_commission",domestic_commission);
		if ( payment_div != null )
			map.put("payment_div",payment_div);
		if ( accrued_interest_yen != null )
			map.put("accrued_interest_yen",accrued_interest_yen);
		if ( accrued_interest != null )
			map.put("accrued_interest",accrued_interest);
		if ( denomination != null )
			map.put("denomination",denomination);
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
		if ( exec_date_is_modified )
			map.put("exec_date",exec_date);
		if ( quantity_is_modified )
			map.put("quantity",quantity);
		if ( price_is_modified )
			map.put("price",price);
		if ( executed_amount_is_modified )
			map.put("executed_amount",executed_amount);
		if ( commission_fee_is_modified )
			map.put("commission_fee",commission_fee);
		if ( commission_fee_tax_is_modified )
			map.put("commission_fee_tax",commission_fee_tax);
		if ( account_div_is_modified )
			map.put("account_div",account_div);
		if ( capital_gain_tax_is_modified )
			map.put("capital_gain_tax",capital_gain_tax);
		if ( net_amount_is_modified )
			map.put("net_amount",net_amount);
		if ( status_is_modified )
			map.put("status",status);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( monetary_unit_is_modified )
			map.put("monetary_unit",monetary_unit);
		if ( fstk_exch_div_is_modified )
			map.put("fstk_exch_div",fstk_exch_div);
		if ( monetary_unit_executed_amount_is_modified )
			map.put("monetary_unit_executed_amount",monetary_unit_executed_amount);
		if ( monetary_unit_comission_is_modified )
			map.put("monetary_unit_comission",monetary_unit_comission);
		if ( monetary_unit_trade_tax_is_modified )
			map.put("monetary_unit_trade_tax",monetary_unit_trade_tax);
		if ( monetary_unit_interest_is_modified )
			map.put("monetary_unit_interest",monetary_unit_interest);
		if ( local_settle_amount_is_modified )
			map.put("local_settle_amount",local_settle_amount);
		if ( local_settle_amount_yen_is_modified )
			map.put("local_settle_amount_yen",local_settle_amount_yen);
		if ( exec_exchange_is_modified )
			map.put("exec_exchange",exec_exchange);
		if ( domestic_commission_is_modified )
			map.put("domestic_commission",domestic_commission);
		if ( payment_div_is_modified )
			map.put("payment_div",payment_div);
		if ( accrued_interest_yen_is_modified )
			map.put("accrued_interest_yen",accrued_interest_yen);
		if ( accrued_interest_is_modified )
			map.put("accrued_interest",accrued_interest);
		if ( denomination_is_modified )
			map.put("denomination",denomination);
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
			map.put("exec_date",exec_date);
			map.put("quantity",quantity);
			map.put("price",price);
			map.put("executed_amount",executed_amount);
			map.put("commission_fee",commission_fee);
			map.put("commission_fee_tax",commission_fee_tax);
			map.put("account_div",account_div);
			map.put("capital_gain_tax",capital_gain_tax);
			map.put("net_amount",net_amount);
			if ( status_is_set )
				map.put("status",status);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("monetary_unit",monetary_unit);
			map.put("fstk_exch_div",fstk_exch_div);
			map.put("monetary_unit_executed_amount",monetary_unit_executed_amount);
			map.put("monetary_unit_comission",monetary_unit_comission);
			map.put("monetary_unit_trade_tax",monetary_unit_trade_tax);
			map.put("monetary_unit_interest",monetary_unit_interest);
			map.put("local_settle_amount",local_settle_amount);
			map.put("local_settle_amount_yen",local_settle_amount_yen);
			map.put("exec_exchange",exec_exchange);
			map.put("domestic_commission",domestic_commission);
			map.put("payment_div",payment_div);
			map.put("accrued_interest_yen",accrued_interest_yen);
			map.put("accrued_interest",accrued_interest);
			map.put("denomination",denomination);
		}
		return map;
	}


  /** 
   * <em>trade_detail_history_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTradeDetailHistoryId()
  {
    return trade_detail_history_id;
  }


  /** 
   * <em>trade_detail_history_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeDetailHistoryIdIsSet() {
    return trade_detail_history_id_is_set;
  }


  /** 
   * <em>trade_detail_history_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeDetailHistoryIdIsModified() {
    return trade_detail_history_id_is_modified;
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
   * <em>exec_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getExecDate()
  {
    return exec_date;
  }


  /** 
   * <em>exec_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecDateIsSet() {
    return exec_date_is_set;
  }


  /** 
   * <em>exec_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecDateIsModified() {
    return exec_date_is_modified;
  }


  /** 
   * <em>quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getQuantity()
  {
    return ( quantity==null? ((double)0): quantity.doubleValue() );
  }


  /** 
   * <em>quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getQuantityIsNull()
  {
    return quantity == null;
  }


  /** 
   * <em>quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuantityIsSet() {
    return quantity_is_set;
  }


  /** 
   * <em>quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuantityIsModified() {
    return quantity_is_modified;
  }


  /** 
   * <em>price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPrice()
  {
    return ( price==null? ((double)0): price.doubleValue() );
  }


  /** 
   * <em>price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPriceIsNull()
  {
    return price == null;
  }


  /** 
   * <em>price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceIsSet() {
    return price_is_set;
  }


  /** 
   * <em>price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceIsModified() {
    return price_is_modified;
  }


  /** 
   * <em>executed_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExecutedAmount()
  {
    return ( executed_amount==null? ((double)0): executed_amount.doubleValue() );
  }


  /** 
   * <em>executed_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExecutedAmountIsNull()
  {
    return executed_amount == null;
  }


  /** 
   * <em>executed_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedAmountIsSet() {
    return executed_amount_is_set;
  }


  /** 
   * <em>executed_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedAmountIsModified() {
    return executed_amount_is_modified;
  }


  /** 
   * <em>commission_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionFee()
  {
    return ( commission_fee==null? ((double)0): commission_fee.doubleValue() );
  }


  /** 
   * <em>commission_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionFeeIsNull()
  {
    return commission_fee == null;
  }


  /** 
   * <em>commission_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeIsSet() {
    return commission_fee_is_set;
  }


  /** 
   * <em>commission_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeIsModified() {
    return commission_fee_is_modified;
  }


  /** 
   * <em>commission_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionFeeTax()
  {
    return ( commission_fee_tax==null? ((double)0): commission_fee_tax.doubleValue() );
  }


  /** 
   * <em>commission_fee_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionFeeTaxIsNull()
  {
    return commission_fee_tax == null;
  }


  /** 
   * <em>commission_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeTaxIsSet() {
    return commission_fee_tax_is_set;
  }


  /** 
   * <em>commission_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeTaxIsModified() {
    return commission_fee_tax_is_modified;
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
   * <em>monetary_unit</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMonetaryUnit()
  {
    return monetary_unit;
  }


  /** 
   * <em>monetary_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMonetaryUnitIsSet() {
    return monetary_unit_is_set;
  }


  /** 
   * <em>monetary_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMonetaryUnitIsModified() {
    return monetary_unit_is_modified;
  }


  /** 
   * <em>fstk_exch_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFstkExchDiv()
  {
    return fstk_exch_div;
  }


  /** 
   * <em>fstk_exch_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFstkExchDivIsSet() {
    return fstk_exch_div_is_set;
  }


  /** 
   * <em>fstk_exch_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFstkExchDivIsModified() {
    return fstk_exch_div_is_modified;
  }


  /** 
   * <em>monetary_unit_executed_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMonetaryUnitExecutedAmount()
  {
    return ( monetary_unit_executed_amount==null? ((double)0): monetary_unit_executed_amount.doubleValue() );
  }


  /** 
   * <em>monetary_unit_executed_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMonetaryUnitExecutedAmountIsNull()
  {
    return monetary_unit_executed_amount == null;
  }


  /** 
   * <em>monetary_unit_executed_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMonetaryUnitExecutedAmountIsSet() {
    return monetary_unit_executed_amount_is_set;
  }


  /** 
   * <em>monetary_unit_executed_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMonetaryUnitExecutedAmountIsModified() {
    return monetary_unit_executed_amount_is_modified;
  }


  /** 
   * <em>monetary_unit_comission</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMonetaryUnitComission()
  {
    return ( monetary_unit_comission==null? ((double)0): monetary_unit_comission.doubleValue() );
  }


  /** 
   * <em>monetary_unit_comission</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMonetaryUnitComissionIsNull()
  {
    return monetary_unit_comission == null;
  }


  /** 
   * <em>monetary_unit_comission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMonetaryUnitComissionIsSet() {
    return monetary_unit_comission_is_set;
  }


  /** 
   * <em>monetary_unit_comission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMonetaryUnitComissionIsModified() {
    return monetary_unit_comission_is_modified;
  }


  /** 
   * <em>monetary_unit_trade_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMonetaryUnitTradeTax()
  {
    return ( monetary_unit_trade_tax==null? ((double)0): monetary_unit_trade_tax.doubleValue() );
  }


  /** 
   * <em>monetary_unit_trade_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMonetaryUnitTradeTaxIsNull()
  {
    return monetary_unit_trade_tax == null;
  }


  /** 
   * <em>monetary_unit_trade_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMonetaryUnitTradeTaxIsSet() {
    return monetary_unit_trade_tax_is_set;
  }


  /** 
   * <em>monetary_unit_trade_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMonetaryUnitTradeTaxIsModified() {
    return monetary_unit_trade_tax_is_modified;
  }


  /** 
   * <em>monetary_unit_interest</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMonetaryUnitInterest()
  {
    return ( monetary_unit_interest==null? ((double)0): monetary_unit_interest.doubleValue() );
  }


  /** 
   * <em>monetary_unit_interest</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMonetaryUnitInterestIsNull()
  {
    return monetary_unit_interest == null;
  }


  /** 
   * <em>monetary_unit_interest</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMonetaryUnitInterestIsSet() {
    return monetary_unit_interest_is_set;
  }


  /** 
   * <em>monetary_unit_interest</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMonetaryUnitInterestIsModified() {
    return monetary_unit_interest_is_modified;
  }


  /** 
   * <em>local_settle_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLocalSettleAmount()
  {
    return ( local_settle_amount==null? ((double)0): local_settle_amount.doubleValue() );
  }


  /** 
   * <em>local_settle_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLocalSettleAmountIsNull()
  {
    return local_settle_amount == null;
  }


  /** 
   * <em>local_settle_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLocalSettleAmountIsSet() {
    return local_settle_amount_is_set;
  }


  /** 
   * <em>local_settle_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLocalSettleAmountIsModified() {
    return local_settle_amount_is_modified;
  }


  /** 
   * <em>local_settle_amount_yen</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLocalSettleAmountYen()
  {
    return ( local_settle_amount_yen==null? ((double)0): local_settle_amount_yen.doubleValue() );
  }


  /** 
   * <em>local_settle_amount_yen</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLocalSettleAmountYenIsNull()
  {
    return local_settle_amount_yen == null;
  }


  /** 
   * <em>local_settle_amount_yen</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLocalSettleAmountYenIsSet() {
    return local_settle_amount_yen_is_set;
  }


  /** 
   * <em>local_settle_amount_yen</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLocalSettleAmountYenIsModified() {
    return local_settle_amount_yen_is_modified;
  }


  /** 
   * <em>exec_exchange</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExecExchange()
  {
    return ( exec_exchange==null? ((double)0): exec_exchange.doubleValue() );
  }


  /** 
   * <em>exec_exchange</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExecExchangeIsNull()
  {
    return exec_exchange == null;
  }


  /** 
   * <em>exec_exchange</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecExchangeIsSet() {
    return exec_exchange_is_set;
  }


  /** 
   * <em>exec_exchange</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecExchangeIsModified() {
    return exec_exchange_is_modified;
  }


  /** 
   * <em>domestic_commission</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDomesticCommission()
  {
    return ( domestic_commission==null? ((double)0): domestic_commission.doubleValue() );
  }


  /** 
   * <em>domestic_commission</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDomesticCommissionIsNull()
  {
    return domestic_commission == null;
  }


  /** 
   * <em>domestic_commission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomesticCommissionIsSet() {
    return domestic_commission_is_set;
  }


  /** 
   * <em>domestic_commission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomesticCommissionIsModified() {
    return domestic_commission_is_modified;
  }


  /** 
   * <em>payment_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentDiv()
  {
    return payment_div;
  }


  /** 
   * <em>payment_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentDivIsSet() {
    return payment_div_is_set;
  }


  /** 
   * <em>payment_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentDivIsModified() {
    return payment_div_is_modified;
  }


  /** 
   * <em>accrued_interest_yen</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAccruedInterestYen()
  {
    return ( accrued_interest_yen==null? ((double)0): accrued_interest_yen.doubleValue() );
  }


  /** 
   * <em>accrued_interest_yen</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccruedInterestYenIsNull()
  {
    return accrued_interest_yen == null;
  }


  /** 
   * <em>accrued_interest_yen</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccruedInterestYenIsSet() {
    return accrued_interest_yen_is_set;
  }


  /** 
   * <em>accrued_interest_yen</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccruedInterestYenIsModified() {
    return accrued_interest_yen_is_modified;
  }


  /** 
   * <em>accrued_interest</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAccruedInterest()
  {
    return ( accrued_interest==null? ((double)0): accrued_interest.doubleValue() );
  }


  /** 
   * <em>accrued_interest</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccruedInterestIsNull()
  {
    return accrued_interest == null;
  }


  /** 
   * <em>accrued_interest</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccruedInterestIsSet() {
    return accrued_interest_is_set;
  }


  /** 
   * <em>accrued_interest</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccruedInterestIsModified() {
    return accrued_interest_is_modified;
  }


  /** 
   * <em>denomination</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDenomination()
  {
    return ( denomination==null? ((double)0): denomination.doubleValue() );
  }


  /** 
   * <em>denomination</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDenominationIsNull()
  {
    return denomination == null;
  }


  /** 
   * <em>denomination</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDenominationIsSet() {
    return denomination_is_set;
  }


  /** 
   * <em>denomination</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDenominationIsModified() {
    return denomination_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new TradeDetailHistoryPK(trade_detail_history_id);
  }


  /** 
   * <em>trade_detail_history_id</em>カラムの値を設定します。 
   *
   * @@param p_tradeDetailHistoryId <em>trade_detail_history_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setTradeDetailHistoryId( long p_tradeDetailHistoryId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_detail_history_id = p_tradeDetailHistoryId;
    trade_detail_history_id_is_set = true;
    trade_detail_history_id_is_modified = true;
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
   * <em>exec_date</em>カラムの値を設定します。 
   *
   * @@param p_execDate <em>exec_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setExecDate( java.sql.Timestamp p_execDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_date = p_execDate;
    exec_date_is_set = true;
    exec_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>exec_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setExecDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    exec_date_is_set = true;
    exec_date_is_modified = true;
  }


  /** 
   * <em>quantity</em>カラムの値を設定します。 
   *
   * @@param p_quantity <em>quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = new Double(p_quantity);
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>quantity</em>カラムに値を設定します。 
   */
  public final void setQuantity( Double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * <em>price</em>カラムの値を設定します。 
   *
   * @@param p_price <em>price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPrice( double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price = new Double(p_price);
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>price</em>カラムに値を設定します。 
   */
  public final void setPrice( Double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    price = p_price;
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * <em>executed_amount</em>カラムの値を設定します。 
   *
   * @@param p_executedAmount <em>executed_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setExecutedAmount( double p_executedAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    executed_amount = new Double(p_executedAmount);
    executed_amount_is_set = true;
    executed_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>executed_amount</em>カラムに値を設定します。 
   */
  public final void setExecutedAmount( Double p_executedAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    executed_amount = p_executedAmount;
    executed_amount_is_set = true;
    executed_amount_is_modified = true;
  }


  /** 
   * <em>commission_fee</em>カラムの値を設定します。 
   *
   * @@param p_commissionFee <em>commission_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCommissionFee( double p_commissionFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_fee = new Double(p_commissionFee);
    commission_fee_is_set = true;
    commission_fee_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_fee</em>カラムに値を設定します。 
   */
  public final void setCommissionFee( Double p_commissionFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_fee = p_commissionFee;
    commission_fee_is_set = true;
    commission_fee_is_modified = true;
  }


  /** 
   * <em>commission_fee_tax</em>カラムの値を設定します。 
   *
   * @@param p_commissionFeeTax <em>commission_fee_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCommissionFeeTax( double p_commissionFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_fee_tax = new Double(p_commissionFeeTax);
    commission_fee_tax_is_set = true;
    commission_fee_tax_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_fee_tax</em>カラムに値を設定します。 
   */
  public final void setCommissionFeeTax( Double p_commissionFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_fee_tax = p_commissionFeeTax;
    commission_fee_tax_is_set = true;
    commission_fee_tax_is_modified = true;
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
   * <em>monetary_unit</em>カラムの値を設定します。 
   *
   * @@param p_monetaryUnit <em>monetary_unit</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setMonetaryUnit( String p_monetaryUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    monetary_unit = p_monetaryUnit;
    monetary_unit_is_set = true;
    monetary_unit_is_modified = true;
  }


  /** 
   * <em>fstk_exch_div</em>カラムの値を設定します。 
   *
   * @@param p_fstkExchDiv <em>fstk_exch_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setFstkExchDiv( String p_fstkExchDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fstk_exch_div = p_fstkExchDiv;
    fstk_exch_div_is_set = true;
    fstk_exch_div_is_modified = true;
  }


  /** 
   * <em>monetary_unit_executed_amount</em>カラムの値を設定します。 
   *
   * @@param p_monetaryUnitExecutedAmount <em>monetary_unit_executed_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMonetaryUnitExecutedAmount( double p_monetaryUnitExecutedAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    monetary_unit_executed_amount = new Double(p_monetaryUnitExecutedAmount);
    monetary_unit_executed_amount_is_set = true;
    monetary_unit_executed_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>monetary_unit_executed_amount</em>カラムに値を設定します。 
   */
  public final void setMonetaryUnitExecutedAmount( Double p_monetaryUnitExecutedAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    monetary_unit_executed_amount = p_monetaryUnitExecutedAmount;
    monetary_unit_executed_amount_is_set = true;
    monetary_unit_executed_amount_is_modified = true;
  }


  /** 
   * <em>monetary_unit_comission</em>カラムの値を設定します。 
   *
   * @@param p_monetaryUnitComission <em>monetary_unit_comission</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMonetaryUnitComission( double p_monetaryUnitComission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    monetary_unit_comission = new Double(p_monetaryUnitComission);
    monetary_unit_comission_is_set = true;
    monetary_unit_comission_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>monetary_unit_comission</em>カラムに値を設定します。 
   */
  public final void setMonetaryUnitComission( Double p_monetaryUnitComission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    monetary_unit_comission = p_monetaryUnitComission;
    monetary_unit_comission_is_set = true;
    monetary_unit_comission_is_modified = true;
  }


  /** 
   * <em>monetary_unit_trade_tax</em>カラムの値を設定します。 
   *
   * @@param p_monetaryUnitTradeTax <em>monetary_unit_trade_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMonetaryUnitTradeTax( double p_monetaryUnitTradeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    monetary_unit_trade_tax = new Double(p_monetaryUnitTradeTax);
    monetary_unit_trade_tax_is_set = true;
    monetary_unit_trade_tax_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>monetary_unit_trade_tax</em>カラムに値を設定します。 
   */
  public final void setMonetaryUnitTradeTax( Double p_monetaryUnitTradeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    monetary_unit_trade_tax = p_monetaryUnitTradeTax;
    monetary_unit_trade_tax_is_set = true;
    monetary_unit_trade_tax_is_modified = true;
  }


  /** 
   * <em>monetary_unit_interest</em>カラムの値を設定します。 
   *
   * @@param p_monetaryUnitInterest <em>monetary_unit_interest</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMonetaryUnitInterest( double p_monetaryUnitInterest )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    monetary_unit_interest = new Double(p_monetaryUnitInterest);
    monetary_unit_interest_is_set = true;
    monetary_unit_interest_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>monetary_unit_interest</em>カラムに値を設定します。 
   */
  public final void setMonetaryUnitInterest( Double p_monetaryUnitInterest )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    monetary_unit_interest = p_monetaryUnitInterest;
    monetary_unit_interest_is_set = true;
    monetary_unit_interest_is_modified = true;
  }


  /** 
   * <em>local_settle_amount</em>カラムの値を設定します。 
   *
   * @@param p_localSettleAmount <em>local_settle_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLocalSettleAmount( double p_localSettleAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    local_settle_amount = new Double(p_localSettleAmount);
    local_settle_amount_is_set = true;
    local_settle_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>local_settle_amount</em>カラムに値を設定します。 
   */
  public final void setLocalSettleAmount( Double p_localSettleAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    local_settle_amount = p_localSettleAmount;
    local_settle_amount_is_set = true;
    local_settle_amount_is_modified = true;
  }


  /** 
   * <em>local_settle_amount_yen</em>カラムの値を設定します。 
   *
   * @@param p_localSettleAmountYen <em>local_settle_amount_yen</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLocalSettleAmountYen( double p_localSettleAmountYen )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    local_settle_amount_yen = new Double(p_localSettleAmountYen);
    local_settle_amount_yen_is_set = true;
    local_settle_amount_yen_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>local_settle_amount_yen</em>カラムに値を設定します。 
   */
  public final void setLocalSettleAmountYen( Double p_localSettleAmountYen )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    local_settle_amount_yen = p_localSettleAmountYen;
    local_settle_amount_yen_is_set = true;
    local_settle_amount_yen_is_modified = true;
  }


  /** 
   * <em>exec_exchange</em>カラムの値を設定します。 
   *
   * @@param p_execExchange <em>exec_exchange</em>カラムの値をあらわす11桁以下のdouble値で、その精度は7桁まで
   */
  public final void setExecExchange( double p_execExchange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_exchange = new Double(p_execExchange);
    exec_exchange_is_set = true;
    exec_exchange_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>exec_exchange</em>カラムに値を設定します。 
   */
  public final void setExecExchange( Double p_execExchange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_exchange = p_execExchange;
    exec_exchange_is_set = true;
    exec_exchange_is_modified = true;
  }


  /** 
   * <em>domestic_commission</em>カラムの値を設定します。 
   *
   * @@param p_domesticCommission <em>domestic_commission</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDomesticCommission( double p_domesticCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    domestic_commission = new Double(p_domesticCommission);
    domestic_commission_is_set = true;
    domestic_commission_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>domestic_commission</em>カラムに値を設定します。 
   */
  public final void setDomesticCommission( Double p_domesticCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    domestic_commission = p_domesticCommission;
    domestic_commission_is_set = true;
    domestic_commission_is_modified = true;
  }


  /** 
   * <em>payment_div</em>カラムの値を設定します。 
   *
   * @@param p_paymentDiv <em>payment_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPaymentDiv( String p_paymentDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_div = p_paymentDiv;
    payment_div_is_set = true;
    payment_div_is_modified = true;
  }


  /** 
   * <em>accrued_interest_yen</em>カラムの値を設定します。 
   *
   * @@param p_accruedInterestYen <em>accrued_interest_yen</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAccruedInterestYen( double p_accruedInterestYen )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    accrued_interest_yen = new Double(p_accruedInterestYen);
    accrued_interest_yen_is_set = true;
    accrued_interest_yen_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>accrued_interest_yen</em>カラムに値を設定します。 
   */
  public final void setAccruedInterestYen( Double p_accruedInterestYen )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    accrued_interest_yen = p_accruedInterestYen;
    accrued_interest_yen_is_set = true;
    accrued_interest_yen_is_modified = true;
  }


  /** 
   * <em>accrued_interest</em>カラムの値を設定します。 
   *
   * @@param p_accruedInterest <em>accrued_interest</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAccruedInterest( double p_accruedInterest )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    accrued_interest = new Double(p_accruedInterest);
    accrued_interest_is_set = true;
    accrued_interest_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>accrued_interest</em>カラムに値を設定します。 
   */
  public final void setAccruedInterest( Double p_accruedInterest )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    accrued_interest = p_accruedInterest;
    accrued_interest_is_set = true;
    accrued_interest_is_modified = true;
  }


  /** 
   * <em>denomination</em>カラムの値を設定します。 
   *
   * @@param p_denomination <em>denomination</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDenomination( double p_denomination )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    denomination = new Double(p_denomination);
    denomination_is_set = true;
    denomination_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>denomination</em>カラムに値を設定します。 
   */
  public final void setDenomination( Double p_denomination )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    denomination = p_denomination;
    denomination_is_set = true;
    denomination_is_modified = true;
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
                else if ( name.equals("accrued_interest_yen") ) {
                    return this.accrued_interest_yen;
                }
                else if ( name.equals("accrued_interest") ) {
                    return this.accrued_interest;
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
                else if ( name.equals("commission_fee") ) {
                    return this.commission_fee;
                }
                else if ( name.equals("commission_fee_tax") ) {
                    return this.commission_fee_tax;
                }
                else if ( name.equals("capital_gain_tax") ) {
                    return this.capital_gain_tax;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                else if ( name.equals("domestic_commission") ) {
                    return this.domestic_commission;
                }
                else if ( name.equals("denomination") ) {
                    return this.denomination;
                }
                break;
            case 'e':
                if ( name.equals("exec_date") ) {
                    return this.exec_date;
                }
                else if ( name.equals("executed_amount") ) {
                    return this.executed_amount;
                }
                else if ( name.equals("exec_exchange") ) {
                    return this.exec_exchange;
                }
                break;
            case 'f':
                if ( name.equals("fstk_exch_div") ) {
                    return this.fstk_exch_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                else if ( name.equals("local_settle_amount") ) {
                    return this.local_settle_amount;
                }
                else if ( name.equals("local_settle_amount_yen") ) {
                    return this.local_settle_amount_yen;
                }
                break;
            case 'm':
                if ( name.equals("market_div") ) {
                    return this.market_div;
                }
                else if ( name.equals("monetary_unit") ) {
                    return this.monetary_unit;
                }
                else if ( name.equals("monetary_unit_executed_amount") ) {
                    return this.monetary_unit_executed_amount;
                }
                else if ( name.equals("monetary_unit_comission") ) {
                    return this.monetary_unit_comission;
                }
                else if ( name.equals("monetary_unit_trade_tax") ) {
                    return this.monetary_unit_trade_tax;
                }
                else if ( name.equals("monetary_unit_interest") ) {
                    return this.monetary_unit_interest;
                }
                break;
            case 'n':
                if ( name.equals("net_amount") ) {
                    return this.net_amount;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("price") ) {
                    return this.price;
                }
                else if ( name.equals("payment_div") ) {
                    return this.payment_div;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return this.quantity;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trade_detail_history_id") ) {
                    return new Long( trade_detail_history_id );
                }
                else if ( name.equals("trader_code") ) {
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
                else if ( name.equals("accrued_interest_yen") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'accrued_interest_yen' must be Double: '"+value+"' is not." );
                    this.accrued_interest_yen = (Double) value;
                    if (this.accrued_interest_yen_is_set)
                        this.accrued_interest_yen_is_modified = true;
                    this.accrued_interest_yen_is_set = true;
                    return;
                }
                else if ( name.equals("accrued_interest") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'accrued_interest' must be Double: '"+value+"' is not." );
                    this.accrued_interest = (Double) value;
                    if (this.accrued_interest_is_set)
                        this.accrued_interest_is_modified = true;
                    this.accrued_interest_is_set = true;
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
                else if ( name.equals("commission_fee") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_fee' must be Double: '"+value+"' is not." );
                    this.commission_fee = (Double) value;
                    if (this.commission_fee_is_set)
                        this.commission_fee_is_modified = true;
                    this.commission_fee_is_set = true;
                    return;
                }
                else if ( name.equals("commission_fee_tax") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_fee_tax' must be Double: '"+value+"' is not." );
                    this.commission_fee_tax = (Double) value;
                    if (this.commission_fee_tax_is_set)
                        this.commission_fee_tax_is_modified = true;
                    this.commission_fee_tax_is_set = true;
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
                else if ( name.equals("domestic_commission") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'domestic_commission' must be Double: '"+value+"' is not." );
                    this.domestic_commission = (Double) value;
                    if (this.domestic_commission_is_set)
                        this.domestic_commission_is_modified = true;
                    this.domestic_commission_is_set = true;
                    return;
                }
                else if ( name.equals("denomination") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'denomination' must be Double: '"+value+"' is not." );
                    this.denomination = (Double) value;
                    if (this.denomination_is_set)
                        this.denomination_is_modified = true;
                    this.denomination_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("exec_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'exec_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.exec_date = (java.sql.Timestamp) value;
                    if (this.exec_date_is_set)
                        this.exec_date_is_modified = true;
                    this.exec_date_is_set = true;
                    return;
                }
                else if ( name.equals("executed_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'executed_amount' must be Double: '"+value+"' is not." );
                    this.executed_amount = (Double) value;
                    if (this.executed_amount_is_set)
                        this.executed_amount_is_modified = true;
                    this.executed_amount_is_set = true;
                    return;
                }
                else if ( name.equals("exec_exchange") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'exec_exchange' must be Double: '"+value+"' is not." );
                    this.exec_exchange = (Double) value;
                    if (this.exec_exchange_is_set)
                        this.exec_exchange_is_modified = true;
                    this.exec_exchange_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fstk_exch_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fstk_exch_div' must be String: '"+value+"' is not." );
                    this.fstk_exch_div = (String) value;
                    if (this.fstk_exch_div_is_set)
                        this.fstk_exch_div_is_modified = true;
                    this.fstk_exch_div_is_set = true;
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
                if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("local_settle_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'local_settle_amount' must be Double: '"+value+"' is not." );
                    this.local_settle_amount = (Double) value;
                    if (this.local_settle_amount_is_set)
                        this.local_settle_amount_is_modified = true;
                    this.local_settle_amount_is_set = true;
                    return;
                }
                else if ( name.equals("local_settle_amount_yen") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'local_settle_amount_yen' must be Double: '"+value+"' is not." );
                    this.local_settle_amount_yen = (Double) value;
                    if (this.local_settle_amount_yen_is_set)
                        this.local_settle_amount_yen_is_modified = true;
                    this.local_settle_amount_yen_is_set = true;
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
                else if ( name.equals("monetary_unit") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'monetary_unit' must be String: '"+value+"' is not." );
                    this.monetary_unit = (String) value;
                    if (this.monetary_unit_is_set)
                        this.monetary_unit_is_modified = true;
                    this.monetary_unit_is_set = true;
                    return;
                }
                else if ( name.equals("monetary_unit_executed_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'monetary_unit_executed_amount' must be Double: '"+value+"' is not." );
                    this.monetary_unit_executed_amount = (Double) value;
                    if (this.monetary_unit_executed_amount_is_set)
                        this.monetary_unit_executed_amount_is_modified = true;
                    this.monetary_unit_executed_amount_is_set = true;
                    return;
                }
                else if ( name.equals("monetary_unit_comission") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'monetary_unit_comission' must be Double: '"+value+"' is not." );
                    this.monetary_unit_comission = (Double) value;
                    if (this.monetary_unit_comission_is_set)
                        this.monetary_unit_comission_is_modified = true;
                    this.monetary_unit_comission_is_set = true;
                    return;
                }
                else if ( name.equals("monetary_unit_trade_tax") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'monetary_unit_trade_tax' must be Double: '"+value+"' is not." );
                    this.monetary_unit_trade_tax = (Double) value;
                    if (this.monetary_unit_trade_tax_is_set)
                        this.monetary_unit_trade_tax_is_modified = true;
                    this.monetary_unit_trade_tax_is_set = true;
                    return;
                }
                else if ( name.equals("monetary_unit_interest") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'monetary_unit_interest' must be Double: '"+value+"' is not." );
                    this.monetary_unit_interest = (Double) value;
                    if (this.monetary_unit_interest_is_set)
                        this.monetary_unit_interest_is_modified = true;
                    this.monetary_unit_interest_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("net_amount") ) {
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
                else if ( name.equals("price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'price' must be Double: '"+value+"' is not." );
                    this.price = (Double) value;
                    if (this.price_is_set)
                        this.price_is_modified = true;
                    this.price_is_set = true;
                    return;
                }
                else if ( name.equals("payment_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_div' must be String: '"+value+"' is not." );
                    this.payment_div = (String) value;
                    if (this.payment_div_is_set)
                        this.payment_div_is_modified = true;
                    this.payment_div_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = (Double) value;
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
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
                if ( name.equals("trade_detail_history_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'trade_detail_history_id' must be Long: '"+value+"' is not." );
                    this.trade_detail_history_id = ((Long) value).longValue();
                    if (this.trade_detail_history_id_is_set)
                        this.trade_detail_history_id_is_modified = true;
                    this.trade_detail_history_id_is_set = true;
                    return;
                }
                else if ( name.equals("trader_code") ) {
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
