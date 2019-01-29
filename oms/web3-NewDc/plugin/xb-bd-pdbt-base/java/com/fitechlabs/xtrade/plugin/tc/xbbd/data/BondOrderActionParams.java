head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.58.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondOrderActionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * bond_order_actionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BondOrderActionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BondOrderActionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BondOrderActionParams}が{@@link BondOrderActionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondOrderActionPK 
 * @@see BondOrderActionRow 
 */
public class BondOrderActionParams extends Params implements BondOrderActionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bond_order_action";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BondOrderActionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BondOrderActionRow.TYPE;
  }


  /** 
   * <em>order_action_id</em>カラムの値 
   */
  public  long  order_action_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>カラムの値 
   */
  public  long  sub_account_id;

  /** 
   * <em>trader_id</em>カラムの値 
   */
  public  Long  trader_id;

  /** 
   * <em>order_id</em>カラムの値 
   */
  public  long  order_id;

  /** 
   * <em>order_unit_id</em>カラムの値 
   */
  public  long  order_unit_id;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  Long  market_id;

  /** 
   * <em>order_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum  order_type;

  /** 
   * <em>deal_type</em>カラムの値 
   */
  public  String  deal_type;

  /** 
   * <em>order_event_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum  order_event_type;

  /** 
   * <em>price</em>カラムの値 
   */
  public  Double  price;

  /** 
   * <em>execution_condition_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.xbbd.BondExecutionConditionType  execution_condition_type;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  double  quantity;

  /** 
   * <em>limit_price</em>カラムの値 
   */
  public  Double  limit_price;

  /** 
   * <em>confirmed_quantity</em>カラムの値 
   */
  public  Double  confirmed_quantity;

  /** 
   * <em>confirmed_price</em>カラムの値 
   */
  public  Double  confirmed_price;

  /** 
   * <em>executed_quantity</em>カラムの値 
   */
  public  Double  executed_quantity;

  /** 
   * <em>order_status</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum  order_status;

  /** 
   * <em>expiration_status</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum  expiration_status;

  /** 
   * <em>order_exec_status</em>カラムの値 
   */
  public  String  order_exec_status;

  /** 
   * <em>order_action_serial_no</em>カラムの値 
   */
  public  int  order_action_serial_no;

  /** 
   * <em>executed_price</em>カラムの値 
   */
  public  Double  executed_price;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>exec_date</em>カラムの値 
   */
  public  java.sql.Timestamp  exec_date;

  /** 
   * <em>foreign_exec_date</em>カラムの値 
   */
  public  java.sql.Timestamp  foreign_exec_date;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>foreign_delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  foreign_delivery_date;

  /** 
   * <em>payment_date</em>カラムの値 
   */
  public  java.sql.Timestamp  payment_date;

  /** 
   * <em>base_fx_rate</em>カラムの値 
   */
  public  Double  base_fx_rate;

  /** 
   * <em>exec_fx_rate</em>カラムの値 
   */
  public  Double  exec_fx_rate;

  /** 
   * <em>trading_price</em>カラムの値 
   */
  public  Double  trading_price;

  /** 
   * <em>foreign_trading_price</em>カラムの値 
   */
  public  Double  foreign_trading_price;

  /** 
   * <em>accrued_interest</em>カラムの値 
   */
  public  Double  accrued_interest;

  /** 
   * <em>foreign_accrued_interest</em>カラムの値 
   */
  public  Double  foreign_accrued_interest;

  /** 
   * <em>estimated_price</em>カラムの値 
   */
  public  Double  estimated_price;

  /** 
   * <em>foreign_estimated_price</em>カラムの値 
   */
  public  Double  foreign_estimated_price;

  /** 
   * <em>adjustment_before_maturity</em>カラムの値 
   */
  public  Double  adjustment_before_maturity;

  /** 
   * <em>elapsed_days</em>カラムの値 
   */
  public  Integer  elapsed_days;

  /** 
   * <em>calc_base_days</em>カラムの値 
   */
  public  Integer  calc_base_days;

  /** 
   * <em>custodian_code</em>カラムの値 
   */
  public  String  custodian_code;

  /** 
   * <em>order_root_div</em>カラムの値 
   */
  public  String  order_root_div;

  /** 
   * <em>administrator_code</em>カラムの値 
   */
  public  String  administrator_code;

  /** 
   * <em>error_reason_code</em>カラムの値 
   */
  public  String  error_reason_code;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean order_action_id_is_set = false;
  private boolean order_action_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean trader_id_is_set = false;
  private boolean trader_id_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean order_unit_id_is_set = false;
  private boolean order_unit_id_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean order_type_is_set = false;
  private boolean order_type_is_modified = false;
  private boolean deal_type_is_set = false;
  private boolean deal_type_is_modified = false;
  private boolean order_event_type_is_set = false;
  private boolean order_event_type_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean execution_condition_type_is_set = false;
  private boolean execution_condition_type_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean limit_price_is_set = false;
  private boolean limit_price_is_modified = false;
  private boolean confirmed_quantity_is_set = false;
  private boolean confirmed_quantity_is_modified = false;
  private boolean confirmed_price_is_set = false;
  private boolean confirmed_price_is_modified = false;
  private boolean executed_quantity_is_set = false;
  private boolean executed_quantity_is_modified = false;
  private boolean order_status_is_set = false;
  private boolean order_status_is_modified = false;
  private boolean expiration_status_is_set = false;
  private boolean expiration_status_is_modified = false;
  private boolean order_exec_status_is_set = false;
  private boolean order_exec_status_is_modified = false;
  private boolean order_action_serial_no_is_set = false;
  private boolean order_action_serial_no_is_modified = false;
  private boolean executed_price_is_set = false;
  private boolean executed_price_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean exec_date_is_set = false;
  private boolean exec_date_is_modified = false;
  private boolean foreign_exec_date_is_set = false;
  private boolean foreign_exec_date_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean foreign_delivery_date_is_set = false;
  private boolean foreign_delivery_date_is_modified = false;
  private boolean payment_date_is_set = false;
  private boolean payment_date_is_modified = false;
  private boolean base_fx_rate_is_set = false;
  private boolean base_fx_rate_is_modified = false;
  private boolean exec_fx_rate_is_set = false;
  private boolean exec_fx_rate_is_modified = false;
  private boolean trading_price_is_set = false;
  private boolean trading_price_is_modified = false;
  private boolean foreign_trading_price_is_set = false;
  private boolean foreign_trading_price_is_modified = false;
  private boolean accrued_interest_is_set = false;
  private boolean accrued_interest_is_modified = false;
  private boolean foreign_accrued_interest_is_set = false;
  private boolean foreign_accrued_interest_is_modified = false;
  private boolean estimated_price_is_set = false;
  private boolean estimated_price_is_modified = false;
  private boolean foreign_estimated_price_is_set = false;
  private boolean foreign_estimated_price_is_modified = false;
  private boolean adjustment_before_maturity_is_set = false;
  private boolean adjustment_before_maturity_is_modified = false;
  private boolean elapsed_days_is_set = false;
  private boolean elapsed_days_is_modified = false;
  private boolean calc_base_days_is_set = false;
  private boolean calc_base_days_is_modified = false;
  private boolean custodian_code_is_set = false;
  private boolean custodian_code_is_modified = false;
  private boolean order_root_div_is_set = false;
  private boolean order_root_div_is_modified = false;
  private boolean administrator_code_is_set = false;
  private boolean administrator_code_is_modified = false;
  private boolean error_reason_code_is_set = false;
  private boolean error_reason_code_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "order_action_id=" + order_action_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "trader_id=" +trader_id
      + "," + "order_id=" +order_id
      + "," + "order_unit_id=" +order_unit_id
      + "," + "market_id=" +market_id
      + "," + "order_type=" +order_type
      + "," + "deal_type=" +deal_type
      + "," + "order_event_type=" +order_event_type
      + "," + "price=" +price
      + "," + "execution_condition_type=" +execution_condition_type
      + "," + "quantity=" +quantity
      + "," + "limit_price=" +limit_price
      + "," + "confirmed_quantity=" +confirmed_quantity
      + "," + "confirmed_price=" +confirmed_price
      + "," + "executed_quantity=" +executed_quantity
      + "," + "order_status=" +order_status
      + "," + "expiration_status=" +expiration_status
      + "," + "order_exec_status=" +order_exec_status
      + "," + "order_action_serial_no=" +order_action_serial_no
      + "," + "executed_price=" +executed_price
      + "," + "product_type=" +product_type
      + "," + "product_id=" +product_id
      + "," + "exec_date=" +exec_date
      + "," + "foreign_exec_date=" +foreign_exec_date
      + "," + "delivery_date=" +delivery_date
      + "," + "foreign_delivery_date=" +foreign_delivery_date
      + "," + "payment_date=" +payment_date
      + "," + "base_fx_rate=" +base_fx_rate
      + "," + "exec_fx_rate=" +exec_fx_rate
      + "," + "trading_price=" +trading_price
      + "," + "foreign_trading_price=" +foreign_trading_price
      + "," + "accrued_interest=" +accrued_interest
      + "," + "foreign_accrued_interest=" +foreign_accrued_interest
      + "," + "estimated_price=" +estimated_price
      + "," + "foreign_estimated_price=" +foreign_estimated_price
      + "," + "adjustment_before_maturity=" +adjustment_before_maturity
      + "," + "elapsed_days=" +elapsed_days
      + "," + "calc_base_days=" +calc_base_days
      + "," + "custodian_code=" +custodian_code
      + "," + "order_root_div=" +order_root_div
      + "," + "administrator_code=" +administrator_code
      + "," + "error_reason_code=" +error_reason_code
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のBondOrderActionParamsオブジェクトを作成します。 
   */
  public BondOrderActionParams() {
    order_action_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBondOrderActionRowオブジェクトの値を利用してBondOrderActionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBondOrderActionRowオブジェクト 
   */
  public BondOrderActionParams( BondOrderActionRow p_row )
  {
    order_action_id = p_row.getOrderActionId();
    order_action_id_is_set = p_row.getOrderActionIdIsSet();
    order_action_id_is_modified = p_row.getOrderActionIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    if ( !p_row.getTraderIdIsNull() )
      trader_id = new Long( p_row.getTraderId() );
    trader_id_is_set = p_row.getTraderIdIsSet();
    trader_id_is_modified = p_row.getTraderIdIsModified();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    order_unit_id = p_row.getOrderUnitId();
    order_unit_id_is_set = p_row.getOrderUnitIdIsSet();
    order_unit_id_is_modified = p_row.getOrderUnitIdIsModified();
    if ( !p_row.getMarketIdIsNull() )
      market_id = new Long( p_row.getMarketId() );
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    order_type = p_row.getOrderType();
    order_type_is_set = p_row.getOrderTypeIsSet();
    order_type_is_modified = p_row.getOrderTypeIsModified();
    deal_type = p_row.getDealType();
    deal_type_is_set = p_row.getDealTypeIsSet();
    deal_type_is_modified = p_row.getDealTypeIsModified();
    order_event_type = p_row.getOrderEventType();
    order_event_type_is_set = p_row.getOrderEventTypeIsSet();
    order_event_type_is_modified = p_row.getOrderEventTypeIsModified();
    if ( !p_row.getPriceIsNull() )
      price = new Double( p_row.getPrice() );
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    execution_condition_type = p_row.getExecutionConditionType();
    execution_condition_type_is_set = p_row.getExecutionConditionTypeIsSet();
    execution_condition_type_is_modified = p_row.getExecutionConditionTypeIsModified();
    quantity = p_row.getQuantity();
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    if ( !p_row.getLimitPriceIsNull() )
      limit_price = new Double( p_row.getLimitPrice() );
    limit_price_is_set = p_row.getLimitPriceIsSet();
    limit_price_is_modified = p_row.getLimitPriceIsModified();
    if ( !p_row.getConfirmedQuantityIsNull() )
      confirmed_quantity = new Double( p_row.getConfirmedQuantity() );
    confirmed_quantity_is_set = p_row.getConfirmedQuantityIsSet();
    confirmed_quantity_is_modified = p_row.getConfirmedQuantityIsModified();
    if ( !p_row.getConfirmedPriceIsNull() )
      confirmed_price = new Double( p_row.getConfirmedPrice() );
    confirmed_price_is_set = p_row.getConfirmedPriceIsSet();
    confirmed_price_is_modified = p_row.getConfirmedPriceIsModified();
    if ( !p_row.getExecutedQuantityIsNull() )
      executed_quantity = new Double( p_row.getExecutedQuantity() );
    executed_quantity_is_set = p_row.getExecutedQuantityIsSet();
    executed_quantity_is_modified = p_row.getExecutedQuantityIsModified();
    order_status = p_row.getOrderStatus();
    order_status_is_set = p_row.getOrderStatusIsSet();
    order_status_is_modified = p_row.getOrderStatusIsModified();
    expiration_status = p_row.getExpirationStatus();
    expiration_status_is_set = p_row.getExpirationStatusIsSet();
    expiration_status_is_modified = p_row.getExpirationStatusIsModified();
    order_exec_status = p_row.getOrderExecStatus();
    order_exec_status_is_set = p_row.getOrderExecStatusIsSet();
    order_exec_status_is_modified = p_row.getOrderExecStatusIsModified();
    order_action_serial_no = p_row.getOrderActionSerialNo();
    order_action_serial_no_is_set = p_row.getOrderActionSerialNoIsSet();
    order_action_serial_no_is_modified = p_row.getOrderActionSerialNoIsModified();
    if ( !p_row.getExecutedPriceIsNull() )
      executed_price = new Double( p_row.getExecutedPrice() );
    executed_price_is_set = p_row.getExecutedPriceIsSet();
    executed_price_is_modified = p_row.getExecutedPriceIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    exec_date = p_row.getExecDate();
    exec_date_is_set = p_row.getExecDateIsSet();
    exec_date_is_modified = p_row.getExecDateIsModified();
    foreign_exec_date = p_row.getForeignExecDate();
    foreign_exec_date_is_set = p_row.getForeignExecDateIsSet();
    foreign_exec_date_is_modified = p_row.getForeignExecDateIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    foreign_delivery_date = p_row.getForeignDeliveryDate();
    foreign_delivery_date_is_set = p_row.getForeignDeliveryDateIsSet();
    foreign_delivery_date_is_modified = p_row.getForeignDeliveryDateIsModified();
    payment_date = p_row.getPaymentDate();
    payment_date_is_set = p_row.getPaymentDateIsSet();
    payment_date_is_modified = p_row.getPaymentDateIsModified();
    if ( !p_row.getBaseFxRateIsNull() )
      base_fx_rate = new Double( p_row.getBaseFxRate() );
    base_fx_rate_is_set = p_row.getBaseFxRateIsSet();
    base_fx_rate_is_modified = p_row.getBaseFxRateIsModified();
    if ( !p_row.getExecFxRateIsNull() )
      exec_fx_rate = new Double( p_row.getExecFxRate() );
    exec_fx_rate_is_set = p_row.getExecFxRateIsSet();
    exec_fx_rate_is_modified = p_row.getExecFxRateIsModified();
    if ( !p_row.getTradingPriceIsNull() )
      trading_price = new Double( p_row.getTradingPrice() );
    trading_price_is_set = p_row.getTradingPriceIsSet();
    trading_price_is_modified = p_row.getTradingPriceIsModified();
    if ( !p_row.getForeignTradingPriceIsNull() )
      foreign_trading_price = new Double( p_row.getForeignTradingPrice() );
    foreign_trading_price_is_set = p_row.getForeignTradingPriceIsSet();
    foreign_trading_price_is_modified = p_row.getForeignTradingPriceIsModified();
    if ( !p_row.getAccruedInterestIsNull() )
      accrued_interest = new Double( p_row.getAccruedInterest() );
    accrued_interest_is_set = p_row.getAccruedInterestIsSet();
    accrued_interest_is_modified = p_row.getAccruedInterestIsModified();
    if ( !p_row.getForeignAccruedInterestIsNull() )
      foreign_accrued_interest = new Double( p_row.getForeignAccruedInterest() );
    foreign_accrued_interest_is_set = p_row.getForeignAccruedInterestIsSet();
    foreign_accrued_interest_is_modified = p_row.getForeignAccruedInterestIsModified();
    if ( !p_row.getEstimatedPriceIsNull() )
      estimated_price = new Double( p_row.getEstimatedPrice() );
    estimated_price_is_set = p_row.getEstimatedPriceIsSet();
    estimated_price_is_modified = p_row.getEstimatedPriceIsModified();
    if ( !p_row.getForeignEstimatedPriceIsNull() )
      foreign_estimated_price = new Double( p_row.getForeignEstimatedPrice() );
    foreign_estimated_price_is_set = p_row.getForeignEstimatedPriceIsSet();
    foreign_estimated_price_is_modified = p_row.getForeignEstimatedPriceIsModified();
    if ( !p_row.getAdjustmentBeforeMaturityIsNull() )
      adjustment_before_maturity = new Double( p_row.getAdjustmentBeforeMaturity() );
    adjustment_before_maturity_is_set = p_row.getAdjustmentBeforeMaturityIsSet();
    adjustment_before_maturity_is_modified = p_row.getAdjustmentBeforeMaturityIsModified();
    if ( !p_row.getElapsedDaysIsNull() )
      elapsed_days = new Integer( p_row.getElapsedDays() );
    elapsed_days_is_set = p_row.getElapsedDaysIsSet();
    elapsed_days_is_modified = p_row.getElapsedDaysIsModified();
    if ( !p_row.getCalcBaseDaysIsNull() )
      calc_base_days = new Integer( p_row.getCalcBaseDays() );
    calc_base_days_is_set = p_row.getCalcBaseDaysIsSet();
    calc_base_days_is_modified = p_row.getCalcBaseDaysIsModified();
    custodian_code = p_row.getCustodianCode();
    custodian_code_is_set = p_row.getCustodianCodeIsSet();
    custodian_code_is_modified = p_row.getCustodianCodeIsModified();
    order_root_div = p_row.getOrderRootDiv();
    order_root_div_is_set = p_row.getOrderRootDivIsSet();
    order_root_div_is_modified = p_row.getOrderRootDivIsModified();
    administrator_code = p_row.getAdministratorCode();
    administrator_code_is_set = p_row.getAdministratorCodeIsSet();
    administrator_code_is_modified = p_row.getAdministratorCodeIsModified();
    error_reason_code = p_row.getErrorReasonCode();
    error_reason_code_is_set = p_row.getErrorReasonCodeIsSet();
    error_reason_code_is_modified = p_row.getErrorReasonCodeIsModified();
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
      account_id_is_set = true;
      account_id_is_modified = true;
      sub_account_id_is_set = true;
      sub_account_id_is_modified = true;
      trader_id_is_set = true;
      trader_id_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      order_unit_id_is_set = true;
      order_unit_id_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      order_type_is_set = true;
      order_type_is_modified = true;
      deal_type_is_set = true;
      deal_type_is_modified = true;
      order_event_type_is_set = true;
      order_event_type_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      execution_condition_type_is_set = true;
      execution_condition_type_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      limit_price_is_set = true;
      limit_price_is_modified = true;
      confirmed_quantity_is_set = true;
      confirmed_quantity_is_modified = true;
      confirmed_price_is_set = true;
      confirmed_price_is_modified = true;
      executed_quantity_is_set = true;
      executed_quantity_is_modified = true;
      order_status_is_set = true;
      order_status_is_modified = true;
      expiration_status_is_set = true;
      expiration_status_is_modified = true;
      order_exec_status_is_set = true;
      order_exec_status_is_modified = true;
      order_action_serial_no_is_set = true;
      order_action_serial_no_is_modified = true;
      executed_price_is_set = true;
      executed_price_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      exec_date_is_set = true;
      exec_date_is_modified = true;
      foreign_exec_date_is_set = true;
      foreign_exec_date_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      foreign_delivery_date_is_set = true;
      foreign_delivery_date_is_modified = true;
      payment_date_is_set = true;
      payment_date_is_modified = true;
      base_fx_rate_is_set = true;
      base_fx_rate_is_modified = true;
      exec_fx_rate_is_set = true;
      exec_fx_rate_is_modified = true;
      trading_price_is_set = true;
      trading_price_is_modified = true;
      foreign_trading_price_is_set = true;
      foreign_trading_price_is_modified = true;
      accrued_interest_is_set = true;
      accrued_interest_is_modified = true;
      foreign_accrued_interest_is_set = true;
      foreign_accrued_interest_is_modified = true;
      estimated_price_is_set = true;
      estimated_price_is_modified = true;
      foreign_estimated_price_is_set = true;
      foreign_estimated_price_is_modified = true;
      adjustment_before_maturity_is_set = true;
      adjustment_before_maturity_is_modified = true;
      elapsed_days_is_set = true;
      elapsed_days_is_modified = true;
      calc_base_days_is_set = true;
      calc_base_days_is_modified = true;
      custodian_code_is_set = true;
      custodian_code_is_modified = true;
      order_root_div_is_set = true;
      order_root_div_is_modified = true;
      administrator_code_is_set = true;
      administrator_code_is_modified = true;
      error_reason_code_is_set = true;
      error_reason_code_is_modified = true;
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
    if ( !( other instanceof BondOrderActionRow ) )
       return false;
    return fieldsEqual( (BondOrderActionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBondOrderActionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BondOrderActionRow row )
  {
    if ( order_action_id != row.getOrderActionId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( trader_id == null ) {
      if ( !row.getTraderIdIsNull() )
        return false;
    } else if ( row.getTraderIdIsNull() || ( trader_id.longValue() != row.getTraderId() ) ) {
        return false;
    }
    if ( order_id != row.getOrderId() )
      return false;
    if ( order_unit_id != row.getOrderUnitId() )
      return false;
    if ( market_id == null ) {
      if ( !row.getMarketIdIsNull() )
        return false;
    } else if ( row.getMarketIdIsNull() || ( market_id.longValue() != row.getMarketId() ) ) {
        return false;
    }
    if ( order_type == null ) {
      if ( row.getOrderType() != null )
        return false;
    } else if ( !order_type.equals( row.getOrderType() ) ) {
        return false;
    }
    if ( deal_type == null ) {
      if ( row.getDealType() != null )
        return false;
    } else if ( !deal_type.equals( row.getDealType() ) ) {
        return false;
    }
    if ( order_event_type == null ) {
      if ( row.getOrderEventType() != null )
        return false;
    } else if ( !order_event_type.equals( row.getOrderEventType() ) ) {
        return false;
    }
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.doubleValue() != row.getPrice() ) ) {
        return false;
    }
    if ( execution_condition_type == null ) {
      if ( row.getExecutionConditionType() != null )
        return false;
    } else if ( !execution_condition_type.equals( row.getExecutionConditionType() ) ) {
        return false;
    }
    if ( quantity != row.getQuantity() )
      return false;
    if ( limit_price == null ) {
      if ( !row.getLimitPriceIsNull() )
        return false;
    } else if ( row.getLimitPriceIsNull() || ( limit_price.doubleValue() != row.getLimitPrice() ) ) {
        return false;
    }
    if ( confirmed_quantity == null ) {
      if ( !row.getConfirmedQuantityIsNull() )
        return false;
    } else if ( row.getConfirmedQuantityIsNull() || ( confirmed_quantity.doubleValue() != row.getConfirmedQuantity() ) ) {
        return false;
    }
    if ( confirmed_price == null ) {
      if ( !row.getConfirmedPriceIsNull() )
        return false;
    } else if ( row.getConfirmedPriceIsNull() || ( confirmed_price.doubleValue() != row.getConfirmedPrice() ) ) {
        return false;
    }
    if ( executed_quantity == null ) {
      if ( !row.getExecutedQuantityIsNull() )
        return false;
    } else if ( row.getExecutedQuantityIsNull() || ( executed_quantity.doubleValue() != row.getExecutedQuantity() ) ) {
        return false;
    }
    if ( order_status == null ) {
      if ( row.getOrderStatus() != null )
        return false;
    } else if ( !order_status.equals( row.getOrderStatus() ) ) {
        return false;
    }
    if ( expiration_status == null ) {
      if ( row.getExpirationStatus() != null )
        return false;
    } else if ( !expiration_status.equals( row.getExpirationStatus() ) ) {
        return false;
    }
    if ( order_exec_status == null ) {
      if ( row.getOrderExecStatus() != null )
        return false;
    } else if ( !order_exec_status.equals( row.getOrderExecStatus() ) ) {
        return false;
    }
    if ( order_action_serial_no != row.getOrderActionSerialNo() )
      return false;
    if ( executed_price == null ) {
      if ( !row.getExecutedPriceIsNull() )
        return false;
    } else if ( row.getExecutedPriceIsNull() || ( executed_price.doubleValue() != row.getExecutedPrice() ) ) {
        return false;
    }
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( product_id != row.getProductId() )
      return false;
    if ( exec_date == null ) {
      if ( row.getExecDate() != null )
        return false;
    } else if ( !exec_date.equals( row.getExecDate() ) ) {
        return false;
    }
    if ( foreign_exec_date == null ) {
      if ( row.getForeignExecDate() != null )
        return false;
    } else if ( !foreign_exec_date.equals( row.getForeignExecDate() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( foreign_delivery_date == null ) {
      if ( row.getForeignDeliveryDate() != null )
        return false;
    } else if ( !foreign_delivery_date.equals( row.getForeignDeliveryDate() ) ) {
        return false;
    }
    if ( payment_date == null ) {
      if ( row.getPaymentDate() != null )
        return false;
    } else if ( !payment_date.equals( row.getPaymentDate() ) ) {
        return false;
    }
    if ( base_fx_rate == null ) {
      if ( !row.getBaseFxRateIsNull() )
        return false;
    } else if ( row.getBaseFxRateIsNull() || ( base_fx_rate.doubleValue() != row.getBaseFxRate() ) ) {
        return false;
    }
    if ( exec_fx_rate == null ) {
      if ( !row.getExecFxRateIsNull() )
        return false;
    } else if ( row.getExecFxRateIsNull() || ( exec_fx_rate.doubleValue() != row.getExecFxRate() ) ) {
        return false;
    }
    if ( trading_price == null ) {
      if ( !row.getTradingPriceIsNull() )
        return false;
    } else if ( row.getTradingPriceIsNull() || ( trading_price.doubleValue() != row.getTradingPrice() ) ) {
        return false;
    }
    if ( foreign_trading_price == null ) {
      if ( !row.getForeignTradingPriceIsNull() )
        return false;
    } else if ( row.getForeignTradingPriceIsNull() || ( foreign_trading_price.doubleValue() != row.getForeignTradingPrice() ) ) {
        return false;
    }
    if ( accrued_interest == null ) {
      if ( !row.getAccruedInterestIsNull() )
        return false;
    } else if ( row.getAccruedInterestIsNull() || ( accrued_interest.doubleValue() != row.getAccruedInterest() ) ) {
        return false;
    }
    if ( foreign_accrued_interest == null ) {
      if ( !row.getForeignAccruedInterestIsNull() )
        return false;
    } else if ( row.getForeignAccruedInterestIsNull() || ( foreign_accrued_interest.doubleValue() != row.getForeignAccruedInterest() ) ) {
        return false;
    }
    if ( estimated_price == null ) {
      if ( !row.getEstimatedPriceIsNull() )
        return false;
    } else if ( row.getEstimatedPriceIsNull() || ( estimated_price.doubleValue() != row.getEstimatedPrice() ) ) {
        return false;
    }
    if ( foreign_estimated_price == null ) {
      if ( !row.getForeignEstimatedPriceIsNull() )
        return false;
    } else if ( row.getForeignEstimatedPriceIsNull() || ( foreign_estimated_price.doubleValue() != row.getForeignEstimatedPrice() ) ) {
        return false;
    }
    if ( adjustment_before_maturity == null ) {
      if ( !row.getAdjustmentBeforeMaturityIsNull() )
        return false;
    } else if ( row.getAdjustmentBeforeMaturityIsNull() || ( adjustment_before_maturity.doubleValue() != row.getAdjustmentBeforeMaturity() ) ) {
        return false;
    }
    if ( elapsed_days == null ) {
      if ( !row.getElapsedDaysIsNull() )
        return false;
    } else if ( row.getElapsedDaysIsNull() || ( elapsed_days.intValue() != row.getElapsedDays() ) ) {
        return false;
    }
    if ( calc_base_days == null ) {
      if ( !row.getCalcBaseDaysIsNull() )
        return false;
    } else if ( row.getCalcBaseDaysIsNull() || ( calc_base_days.intValue() != row.getCalcBaseDays() ) ) {
        return false;
    }
    if ( custodian_code == null ) {
      if ( row.getCustodianCode() != null )
        return false;
    } else if ( !custodian_code.equals( row.getCustodianCode() ) ) {
        return false;
    }
    if ( order_root_div == null ) {
      if ( row.getOrderRootDiv() != null )
        return false;
    } else if ( !order_root_div.equals( row.getOrderRootDiv() ) ) {
        return false;
    }
    if ( administrator_code == null ) {
      if ( row.getAdministratorCode() != null )
        return false;
    } else if ( !administrator_code.equals( row.getAdministratorCode() ) ) {
        return false;
    }
    if ( error_reason_code == null ) {
      if ( row.getErrorReasonCode() != null )
        return false;
    } else if ( !error_reason_code.equals( row.getErrorReasonCode() ) ) {
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
      return  ((int) order_action_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + (trader_id!=null? trader_id.hashCode(): 0) 
        + ((int) order_id)
        + ((int) order_unit_id)
        + (market_id!=null? market_id.hashCode(): 0) 
        + (order_type!=null? order_type.hashCode(): 0) 
        + (deal_type!=null? deal_type.hashCode(): 0) 
        + (order_event_type!=null? order_event_type.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (execution_condition_type!=null? execution_condition_type.hashCode(): 0) 
        + ((int) quantity)
        + (limit_price!=null? limit_price.hashCode(): 0) 
        + (confirmed_quantity!=null? confirmed_quantity.hashCode(): 0) 
        + (confirmed_price!=null? confirmed_price.hashCode(): 0) 
        + (executed_quantity!=null? executed_quantity.hashCode(): 0) 
        + (order_status!=null? order_status.hashCode(): 0) 
        + (expiration_status!=null? expiration_status.hashCode(): 0) 
        + (order_exec_status!=null? order_exec_status.hashCode(): 0) 
        + ((int) order_action_serial_no)
        + (executed_price!=null? executed_price.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + ((int) product_id)
        + (exec_date!=null? exec_date.hashCode(): 0) 
        + (foreign_exec_date!=null? foreign_exec_date.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (foreign_delivery_date!=null? foreign_delivery_date.hashCode(): 0) 
        + (payment_date!=null? payment_date.hashCode(): 0) 
        + (base_fx_rate!=null? base_fx_rate.hashCode(): 0) 
        + (exec_fx_rate!=null? exec_fx_rate.hashCode(): 0) 
        + (trading_price!=null? trading_price.hashCode(): 0) 
        + (foreign_trading_price!=null? foreign_trading_price.hashCode(): 0) 
        + (accrued_interest!=null? accrued_interest.hashCode(): 0) 
        + (foreign_accrued_interest!=null? foreign_accrued_interest.hashCode(): 0) 
        + (estimated_price!=null? estimated_price.hashCode(): 0) 
        + (foreign_estimated_price!=null? foreign_estimated_price.hashCode(): 0) 
        + (adjustment_before_maturity!=null? adjustment_before_maturity.hashCode(): 0) 
        + (elapsed_days!=null? elapsed_days.hashCode(): 0) 
        + (calc_base_days!=null? calc_base_days.hashCode(): 0) 
        + (custodian_code!=null? custodian_code.hashCode(): 0) 
        + (order_root_div!=null? order_root_div.hashCode(): 0) 
        + (administrator_code!=null? administrator_code.hashCode(): 0) 
        + (error_reason_code!=null? error_reason_code.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !sub_account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'sub_account_id' must be set before inserting.");
		if ( !order_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_id' must be set before inserting.");
		if ( !order_unit_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_unit_id' must be set before inserting.");
		if ( !order_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_type' must be set before inserting.");
		if ( !deal_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'deal_type' must be set before inserting.");
		if ( !order_event_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_event_type' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
		if ( !order_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_status' must be set before inserting.");
		if ( !expiration_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'expiration_status' must be set before inserting.");
		if ( !order_exec_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_exec_status' must be set before inserting.");
		if ( !order_action_serial_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_action_serial_no' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
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
		map.put("order_action_id",new Long(order_action_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		if ( trader_id != null )
			map.put("trader_id",trader_id);
		map.put("order_id",new Long(order_id));
		map.put("order_unit_id",new Long(order_unit_id));
		if ( market_id != null )
			map.put("market_id",market_id);
		map.put("order_type",order_type);
		map.put("deal_type",deal_type);
		map.put("order_event_type",order_event_type);
		if ( price != null )
			map.put("price",price);
		if ( execution_condition_type != null )
			map.put("execution_condition_type",execution_condition_type);
		map.put("quantity",new Double(quantity));
		if ( limit_price != null )
			map.put("limit_price",limit_price);
		if ( confirmed_quantity != null )
			map.put("confirmed_quantity",confirmed_quantity);
		if ( confirmed_price != null )
			map.put("confirmed_price",confirmed_price);
		if ( executed_quantity != null )
			map.put("executed_quantity",executed_quantity);
		map.put("order_status",order_status);
		map.put("expiration_status",expiration_status);
		map.put("order_exec_status",order_exec_status);
		map.put("order_action_serial_no",new Integer(order_action_serial_no));
		if ( executed_price != null )
			map.put("executed_price",executed_price);
		map.put("product_type",product_type);
		map.put("product_id",new Long(product_id));
		if ( exec_date != null )
			map.put("exec_date",exec_date);
		if ( foreign_exec_date != null )
			map.put("foreign_exec_date",foreign_exec_date);
		if ( delivery_date != null )
			map.put("delivery_date",delivery_date);
		if ( foreign_delivery_date != null )
			map.put("foreign_delivery_date",foreign_delivery_date);
		if ( payment_date != null )
			map.put("payment_date",payment_date);
		if ( base_fx_rate != null )
			map.put("base_fx_rate",base_fx_rate);
		if ( exec_fx_rate != null )
			map.put("exec_fx_rate",exec_fx_rate);
		if ( trading_price != null )
			map.put("trading_price",trading_price);
		if ( foreign_trading_price != null )
			map.put("foreign_trading_price",foreign_trading_price);
		if ( accrued_interest != null )
			map.put("accrued_interest",accrued_interest);
		if ( foreign_accrued_interest != null )
			map.put("foreign_accrued_interest",foreign_accrued_interest);
		if ( estimated_price != null )
			map.put("estimated_price",estimated_price);
		if ( foreign_estimated_price != null )
			map.put("foreign_estimated_price",foreign_estimated_price);
		if ( adjustment_before_maturity != null )
			map.put("adjustment_before_maturity",adjustment_before_maturity);
		if ( elapsed_days != null )
			map.put("elapsed_days",elapsed_days);
		if ( calc_base_days != null )
			map.put("calc_base_days",calc_base_days);
		if ( custodian_code != null )
			map.put("custodian_code",custodian_code);
		if ( order_root_div != null )
			map.put("order_root_div",order_root_div);
		if ( administrator_code != null )
			map.put("administrator_code",administrator_code);
		if ( error_reason_code != null )
			map.put("error_reason_code",error_reason_code);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( sub_account_id_is_modified )
			map.put("sub_account_id",new Long(sub_account_id));
		if ( trader_id_is_modified )
			map.put("trader_id",trader_id);
		if ( order_id_is_modified )
			map.put("order_id",new Long(order_id));
		if ( order_unit_id_is_modified )
			map.put("order_unit_id",new Long(order_unit_id));
		if ( market_id_is_modified )
			map.put("market_id",market_id);
		if ( order_type_is_modified )
			map.put("order_type",order_type);
		if ( deal_type_is_modified )
			map.put("deal_type",deal_type);
		if ( order_event_type_is_modified )
			map.put("order_event_type",order_event_type);
		if ( price_is_modified )
			map.put("price",price);
		if ( execution_condition_type_is_modified )
			map.put("execution_condition_type",execution_condition_type);
		if ( quantity_is_modified )
			map.put("quantity",new Double(quantity));
		if ( limit_price_is_modified )
			map.put("limit_price",limit_price);
		if ( confirmed_quantity_is_modified )
			map.put("confirmed_quantity",confirmed_quantity);
		if ( confirmed_price_is_modified )
			map.put("confirmed_price",confirmed_price);
		if ( executed_quantity_is_modified )
			map.put("executed_quantity",executed_quantity);
		if ( order_status_is_modified )
			map.put("order_status",order_status);
		if ( expiration_status_is_modified )
			map.put("expiration_status",expiration_status);
		if ( order_exec_status_is_modified )
			map.put("order_exec_status",order_exec_status);
		if ( order_action_serial_no_is_modified )
			map.put("order_action_serial_no",new Integer(order_action_serial_no));
		if ( executed_price_is_modified )
			map.put("executed_price",executed_price);
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( exec_date_is_modified )
			map.put("exec_date",exec_date);
		if ( foreign_exec_date_is_modified )
			map.put("foreign_exec_date",foreign_exec_date);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( foreign_delivery_date_is_modified )
			map.put("foreign_delivery_date",foreign_delivery_date);
		if ( payment_date_is_modified )
			map.put("payment_date",payment_date);
		if ( base_fx_rate_is_modified )
			map.put("base_fx_rate",base_fx_rate);
		if ( exec_fx_rate_is_modified )
			map.put("exec_fx_rate",exec_fx_rate);
		if ( trading_price_is_modified )
			map.put("trading_price",trading_price);
		if ( foreign_trading_price_is_modified )
			map.put("foreign_trading_price",foreign_trading_price);
		if ( accrued_interest_is_modified )
			map.put("accrued_interest",accrued_interest);
		if ( foreign_accrued_interest_is_modified )
			map.put("foreign_accrued_interest",foreign_accrued_interest);
		if ( estimated_price_is_modified )
			map.put("estimated_price",estimated_price);
		if ( foreign_estimated_price_is_modified )
			map.put("foreign_estimated_price",foreign_estimated_price);
		if ( adjustment_before_maturity_is_modified )
			map.put("adjustment_before_maturity",adjustment_before_maturity);
		if ( elapsed_days_is_modified )
			map.put("elapsed_days",elapsed_days);
		if ( calc_base_days_is_modified )
			map.put("calc_base_days",calc_base_days);
		if ( custodian_code_is_modified )
			map.put("custodian_code",custodian_code);
		if ( order_root_div_is_modified )
			map.put("order_root_div",order_root_div);
		if ( administrator_code_is_modified )
			map.put("administrator_code",administrator_code);
		if ( error_reason_code_is_modified )
			map.put("error_reason_code",error_reason_code);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			map.put("trader_id",trader_id);
			if ( order_id_is_set )
				map.put("order_id",new Long(order_id));
			if ( order_unit_id_is_set )
				map.put("order_unit_id",new Long(order_unit_id));
			map.put("market_id",market_id);
			if ( order_type_is_set )
				map.put("order_type",order_type);
			if ( deal_type_is_set )
				map.put("deal_type",deal_type);
			if ( order_event_type_is_set )
				map.put("order_event_type",order_event_type);
			map.put("price",price);
			map.put("execution_condition_type",execution_condition_type);
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			map.put("limit_price",limit_price);
			map.put("confirmed_quantity",confirmed_quantity);
			map.put("confirmed_price",confirmed_price);
			map.put("executed_quantity",executed_quantity);
			if ( order_status_is_set )
				map.put("order_status",order_status);
			if ( expiration_status_is_set )
				map.put("expiration_status",expiration_status);
			if ( order_exec_status_is_set )
				map.put("order_exec_status",order_exec_status);
			if ( order_action_serial_no_is_set )
				map.put("order_action_serial_no",new Integer(order_action_serial_no));
			map.put("executed_price",executed_price);
			if ( product_type_is_set )
				map.put("product_type",product_type);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			map.put("exec_date",exec_date);
			map.put("foreign_exec_date",foreign_exec_date);
			map.put("delivery_date",delivery_date);
			map.put("foreign_delivery_date",foreign_delivery_date);
			map.put("payment_date",payment_date);
			map.put("base_fx_rate",base_fx_rate);
			map.put("exec_fx_rate",exec_fx_rate);
			map.put("trading_price",trading_price);
			map.put("foreign_trading_price",foreign_trading_price);
			map.put("accrued_interest",accrued_interest);
			map.put("foreign_accrued_interest",foreign_accrued_interest);
			map.put("estimated_price",estimated_price);
			map.put("foreign_estimated_price",foreign_estimated_price);
			map.put("adjustment_before_maturity",adjustment_before_maturity);
			map.put("elapsed_days",elapsed_days);
			map.put("calc_base_days",calc_base_days);
			map.put("custodian_code",custodian_code);
			map.put("order_root_div",order_root_div);
			map.put("administrator_code",administrator_code);
			map.put("error_reason_code",error_reason_code);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>order_action_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrderActionId()
  {
    return order_action_id;
  }


  /** 
   * <em>order_action_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderActionIdIsSet() {
    return order_action_id_is_set;
  }


  /** 
   * <em>order_action_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderActionIdIsModified() {
    return order_action_id_is_modified;
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
   * <em>sub_account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSubAccountId()
  {
    return sub_account_id;
  }


  /** 
   * <em>sub_account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccountIdIsSet() {
    return sub_account_id_is_set;
  }


  /** 
   * <em>sub_account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccountIdIsModified() {
    return sub_account_id_is_modified;
  }


  /** 
   * <em>trader_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTraderId()
  {
    return ( trader_id==null? ((long)0): trader_id.longValue() );
  }


  /** 
   * <em>trader_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTraderIdIsNull()
  {
    return trader_id == null;
  }


  /** 
   * <em>trader_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderIdIsSet() {
    return trader_id_is_set;
  }


  /** 
   * <em>trader_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderIdIsModified() {
    return trader_id_is_modified;
  }


  /** 
   * <em>order_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrderId()
  {
    return order_id;
  }


  /** 
   * <em>order_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderIdIsSet() {
    return order_id_is_set;
  }


  /** 
   * <em>order_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderIdIsModified() {
    return order_id_is_modified;
  }


  /** 
   * <em>order_unit_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrderUnitId()
  {
    return order_unit_id;
  }


  /** 
   * <em>order_unit_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderUnitIdIsSet() {
    return order_unit_id_is_set;
  }


  /** 
   * <em>order_unit_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderUnitIdIsModified() {
    return order_unit_id_is_modified;
  }


  /** 
   * <em>market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarketId()
  {
    return ( market_id==null? ((long)0): market_id.longValue() );
  }


  /** 
   * <em>market_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarketIdIsNull()
  {
    return market_id == null;
  }


  /** 
   * <em>market_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsSet() {
    return market_id_is_set;
  }


  /** 
   * <em>market_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsModified() {
    return market_id_is_modified;
  }


  /** 
   * <em>order_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum getOrderType()
  {
    return order_type;
  }


  /** 
   * <em>order_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderTypeIsSet() {
    return order_type_is_set;
  }


  /** 
   * <em>order_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderTypeIsModified() {
    return order_type_is_modified;
  }


  /** 
   * <em>deal_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDealType()
  {
    return deal_type;
  }


  /** 
   * <em>deal_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDealTypeIsSet() {
    return deal_type_is_set;
  }


  /** 
   * <em>deal_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDealTypeIsModified() {
    return deal_type_is_modified;
  }


  /** 
   * <em>order_event_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum getOrderEventType()
  {
    return order_event_type;
  }


  /** 
   * <em>order_event_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderEventTypeIsSet() {
    return order_event_type_is_set;
  }


  /** 
   * <em>order_event_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderEventTypeIsModified() {
    return order_event_type_is_modified;
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
   * <em>execution_condition_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.xbbd.BondExecutionConditionTypeの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.xbbd.BondExecutionConditionType getExecutionConditionType()
  {
    return execution_condition_type;
  }


  /** 
   * <em>execution_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutionConditionTypeIsSet() {
    return execution_condition_type_is_set;
  }


  /** 
   * <em>execution_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutionConditionTypeIsModified() {
    return execution_condition_type_is_modified;
  }


  /** 
   * <em>quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getQuantity()
  {
    return quantity;
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
   * <em>limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLimitPrice()
  {
    return ( limit_price==null? ((double)0): limit_price.doubleValue() );
  }


  /** 
   * <em>limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLimitPriceIsNull()
  {
    return limit_price == null;
  }


  /** 
   * <em>limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitPriceIsSet() {
    return limit_price_is_set;
  }


  /** 
   * <em>limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitPriceIsModified() {
    return limit_price_is_modified;
  }


  /** 
   * <em>confirmed_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getConfirmedQuantity()
  {
    return ( confirmed_quantity==null? ((double)0): confirmed_quantity.doubleValue() );
  }


  /** 
   * <em>confirmed_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getConfirmedQuantityIsNull()
  {
    return confirmed_quantity == null;
  }


  /** 
   * <em>confirmed_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedQuantityIsSet() {
    return confirmed_quantity_is_set;
  }


  /** 
   * <em>confirmed_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedQuantityIsModified() {
    return confirmed_quantity_is_modified;
  }


  /** 
   * <em>confirmed_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getConfirmedPrice()
  {
    return ( confirmed_price==null? ((double)0): confirmed_price.doubleValue() );
  }


  /** 
   * <em>confirmed_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getConfirmedPriceIsNull()
  {
    return confirmed_price == null;
  }


  /** 
   * <em>confirmed_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedPriceIsSet() {
    return confirmed_price_is_set;
  }


  /** 
   * <em>confirmed_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedPriceIsModified() {
    return confirmed_price_is_modified;
  }


  /** 
   * <em>executed_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExecutedQuantity()
  {
    return ( executed_quantity==null? ((double)0): executed_quantity.doubleValue() );
  }


  /** 
   * <em>executed_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExecutedQuantityIsNull()
  {
    return executed_quantity == null;
  }


  /** 
   * <em>executed_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedQuantityIsSet() {
    return executed_quantity_is_set;
  }


  /** 
   * <em>executed_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedQuantityIsModified() {
    return executed_quantity_is_modified;
  }


  /** 
   * <em>order_status</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum getOrderStatus()
  {
    return order_status;
  }


  /** 
   * <em>order_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderStatusIsSet() {
    return order_status_is_set;
  }


  /** 
   * <em>order_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderStatusIsModified() {
    return order_status_is_modified;
  }


  /** 
   * <em>expiration_status</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum getExpirationStatus()
  {
    return expiration_status;
  }


  /** 
   * <em>expiration_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExpirationStatusIsSet() {
    return expiration_status_is_set;
  }


  /** 
   * <em>expiration_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExpirationStatusIsModified() {
    return expiration_status_is_modified;
  }


  /** 
   * <em>order_exec_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderExecStatus()
  {
    return order_exec_status;
  }


  /** 
   * <em>order_exec_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderExecStatusIsSet() {
    return order_exec_status_is_set;
  }


  /** 
   * <em>order_exec_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderExecStatusIsModified() {
    return order_exec_status_is_modified;
  }


  /** 
   * <em>order_action_serial_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOrderActionSerialNo()
  {
    return order_action_serial_no;
  }


  /** 
   * <em>order_action_serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderActionSerialNoIsSet() {
    return order_action_serial_no_is_set;
  }


  /** 
   * <em>order_action_serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderActionSerialNoIsModified() {
    return order_action_serial_no_is_modified;
  }


  /** 
   * <em>executed_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExecutedPrice()
  {
    return ( executed_price==null? ((double)0): executed_price.doubleValue() );
  }


  /** 
   * <em>executed_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExecutedPriceIsNull()
  {
    return executed_price == null;
  }


  /** 
   * <em>executed_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedPriceIsSet() {
    return executed_price_is_set;
  }


  /** 
   * <em>executed_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedPriceIsModified() {
    return executed_price_is_modified;
  }


  /** 
   * <em>product_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType()
  {
    return product_type;
  }


  /** 
   * <em>product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsSet() {
    return product_type_is_set;
  }


  /** 
   * <em>product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsModified() {
    return product_type_is_modified;
  }


  /** 
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getProductId()
  {
    return product_id;
  }


  /** 
   * <em>product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsSet() {
    return product_id_is_set;
  }


  /** 
   * <em>product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsModified() {
    return product_id_is_modified;
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
   * <em>foreign_exec_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getForeignExecDate()
  {
    return foreign_exec_date;
  }


  /** 
   * <em>foreign_exec_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignExecDateIsSet() {
    return foreign_exec_date_is_set;
  }


  /** 
   * <em>foreign_exec_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignExecDateIsModified() {
    return foreign_exec_date_is_modified;
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
   * <em>foreign_delivery_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getForeignDeliveryDate()
  {
    return foreign_delivery_date;
  }


  /** 
   * <em>foreign_delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignDeliveryDateIsSet() {
    return foreign_delivery_date_is_set;
  }


  /** 
   * <em>foreign_delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignDeliveryDateIsModified() {
    return foreign_delivery_date_is_modified;
  }


  /** 
   * <em>payment_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getPaymentDate()
  {
    return payment_date;
  }


  /** 
   * <em>payment_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentDateIsSet() {
    return payment_date_is_set;
  }


  /** 
   * <em>payment_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentDateIsModified() {
    return payment_date_is_modified;
  }


  /** 
   * <em>base_fx_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBaseFxRate()
  {
    return ( base_fx_rate==null? ((double)0): base_fx_rate.doubleValue() );
  }


  /** 
   * <em>base_fx_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBaseFxRateIsNull()
  {
    return base_fx_rate == null;
  }


  /** 
   * <em>base_fx_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBaseFxRateIsSet() {
    return base_fx_rate_is_set;
  }


  /** 
   * <em>base_fx_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBaseFxRateIsModified() {
    return base_fx_rate_is_modified;
  }


  /** 
   * <em>exec_fx_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExecFxRate()
  {
    return ( exec_fx_rate==null? ((double)0): exec_fx_rate.doubleValue() );
  }


  /** 
   * <em>exec_fx_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExecFxRateIsNull()
  {
    return exec_fx_rate == null;
  }


  /** 
   * <em>exec_fx_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecFxRateIsSet() {
    return exec_fx_rate_is_set;
  }


  /** 
   * <em>exec_fx_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecFxRateIsModified() {
    return exec_fx_rate_is_modified;
  }


  /** 
   * <em>trading_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTradingPrice()
  {
    return ( trading_price==null? ((double)0): trading_price.doubleValue() );
  }


  /** 
   * <em>trading_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTradingPriceIsNull()
  {
    return trading_price == null;
  }


  /** 
   * <em>trading_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingPriceIsSet() {
    return trading_price_is_set;
  }


  /** 
   * <em>trading_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingPriceIsModified() {
    return trading_price_is_modified;
  }


  /** 
   * <em>foreign_trading_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getForeignTradingPrice()
  {
    return ( foreign_trading_price==null? ((double)0): foreign_trading_price.doubleValue() );
  }


  /** 
   * <em>foreign_trading_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getForeignTradingPriceIsNull()
  {
    return foreign_trading_price == null;
  }


  /** 
   * <em>foreign_trading_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignTradingPriceIsSet() {
    return foreign_trading_price_is_set;
  }


  /** 
   * <em>foreign_trading_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignTradingPriceIsModified() {
    return foreign_trading_price_is_modified;
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
   * <em>foreign_accrued_interest</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getForeignAccruedInterest()
  {
    return ( foreign_accrued_interest==null? ((double)0): foreign_accrued_interest.doubleValue() );
  }


  /** 
   * <em>foreign_accrued_interest</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getForeignAccruedInterestIsNull()
  {
    return foreign_accrued_interest == null;
  }


  /** 
   * <em>foreign_accrued_interest</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignAccruedInterestIsSet() {
    return foreign_accrued_interest_is_set;
  }


  /** 
   * <em>foreign_accrued_interest</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignAccruedInterestIsModified() {
    return foreign_accrued_interest_is_modified;
  }


  /** 
   * <em>estimated_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getEstimatedPrice()
  {
    return ( estimated_price==null? ((double)0): estimated_price.doubleValue() );
  }


  /** 
   * <em>estimated_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getEstimatedPriceIsNull()
  {
    return estimated_price == null;
  }


  /** 
   * <em>estimated_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEstimatedPriceIsSet() {
    return estimated_price_is_set;
  }


  /** 
   * <em>estimated_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEstimatedPriceIsModified() {
    return estimated_price_is_modified;
  }


  /** 
   * <em>foreign_estimated_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getForeignEstimatedPrice()
  {
    return ( foreign_estimated_price==null? ((double)0): foreign_estimated_price.doubleValue() );
  }


  /** 
   * <em>foreign_estimated_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getForeignEstimatedPriceIsNull()
  {
    return foreign_estimated_price == null;
  }


  /** 
   * <em>foreign_estimated_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignEstimatedPriceIsSet() {
    return foreign_estimated_price_is_set;
  }


  /** 
   * <em>foreign_estimated_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignEstimatedPriceIsModified() {
    return foreign_estimated_price_is_modified;
  }


  /** 
   * <em>adjustment_before_maturity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAdjustmentBeforeMaturity()
  {
    return ( adjustment_before_maturity==null? ((double)0): adjustment_before_maturity.doubleValue() );
  }


  /** 
   * <em>adjustment_before_maturity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAdjustmentBeforeMaturityIsNull()
  {
    return adjustment_before_maturity == null;
  }


  /** 
   * <em>adjustment_before_maturity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdjustmentBeforeMaturityIsSet() {
    return adjustment_before_maturity_is_set;
  }


  /** 
   * <em>adjustment_before_maturity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdjustmentBeforeMaturityIsModified() {
    return adjustment_before_maturity_is_modified;
  }


  /** 
   * <em>elapsed_days</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getElapsedDays()
  {
    return ( elapsed_days==null? ((int)0): elapsed_days.intValue() );
  }


  /** 
   * <em>elapsed_days</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getElapsedDaysIsNull()
  {
    return elapsed_days == null;
  }


  /** 
   * <em>elapsed_days</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getElapsedDaysIsSet() {
    return elapsed_days_is_set;
  }


  /** 
   * <em>elapsed_days</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getElapsedDaysIsModified() {
    return elapsed_days_is_modified;
  }


  /** 
   * <em>calc_base_days</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getCalcBaseDays()
  {
    return ( calc_base_days==null? ((int)0): calc_base_days.intValue() );
  }


  /** 
   * <em>calc_base_days</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCalcBaseDaysIsNull()
  {
    return calc_base_days == null;
  }


  /** 
   * <em>calc_base_days</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcBaseDaysIsSet() {
    return calc_base_days_is_set;
  }


  /** 
   * <em>calc_base_days</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcBaseDaysIsModified() {
    return calc_base_days_is_modified;
  }


  /** 
   * <em>custodian_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCustodianCode()
  {
    return custodian_code;
  }


  /** 
   * <em>custodian_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustodianCodeIsSet() {
    return custodian_code_is_set;
  }


  /** 
   * <em>custodian_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustodianCodeIsModified() {
    return custodian_code_is_modified;
  }


  /** 
   * <em>order_root_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRootDiv()
  {
    return order_root_div;
  }


  /** 
   * <em>order_root_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRootDivIsSet() {
    return order_root_div_is_set;
  }


  /** 
   * <em>order_root_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRootDivIsModified() {
    return order_root_div_is_modified;
  }


  /** 
   * <em>administrator_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAdministratorCode()
  {
    return administrator_code;
  }


  /** 
   * <em>administrator_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdministratorCodeIsSet() {
    return administrator_code_is_set;
  }


  /** 
   * <em>administrator_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdministratorCodeIsModified() {
    return administrator_code_is_modified;
  }


  /** 
   * <em>error_reason_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getErrorReasonCode()
  {
    return error_reason_code;
  }


  /** 
   * <em>error_reason_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorReasonCodeIsSet() {
    return error_reason_code_is_set;
  }


  /** 
   * <em>error_reason_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorReasonCodeIsModified() {
    return error_reason_code_is_modified;
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
    return new BondOrderActionPK(order_action_id);
  }


  /** 
   * <em>order_action_id</em>カラムの値を設定します。 
   *
   * @@param p_orderActionId <em>order_action_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setOrderActionId( long p_orderActionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_action_id = p_orderActionId;
    order_action_id_is_set = true;
    order_action_id_is_modified = true;
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
   * <em>sub_account_id</em>カラムの値を設定します。 
   *
   * @@param p_subAccountId <em>sub_account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setSubAccountId( long p_subAccountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_account_id = p_subAccountId;
    sub_account_id_is_set = true;
    sub_account_id_is_modified = true;
  }


  /** 
   * <em>trader_id</em>カラムの値を設定します。 
   *
   * @@param p_traderId <em>trader_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setTraderId( long p_traderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_id = new Long(p_traderId);
    trader_id_is_set = true;
    trader_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>trader_id</em>カラムに値を設定します。 
   */
  public final void setTraderId( Long p_traderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trader_id = p_traderId;
    trader_id_is_set = true;
    trader_id_is_modified = true;
  }


  /** 
   * <em>order_id</em>カラムの値を設定します。 
   *
   * @@param p_orderId <em>order_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setOrderId( long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
    order_id_is_set = true;
    order_id_is_modified = true;
  }


  /** 
   * <em>order_unit_id</em>カラムの値を設定します。 
   *
   * @@param p_orderUnitId <em>order_unit_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setOrderUnitId( long p_orderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_unit_id = p_orderUnitId;
    order_unit_id_is_set = true;
    order_unit_id_is_modified = true;
  }


  /** 
   * <em>market_id</em>カラムの値を設定します。 
   *
   * @@param p_marketId <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = new Long(p_marketId);
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>market_id</em>カラムに値を設定します。 
   */
  public final void setMarketId( Long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * <em>order_type</em>カラムの値を設定します。 
   *
   * @@param p_orderType <em>order_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum値 
   */
  public final void setOrderType( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum p_orderType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_type = p_orderType;
    order_type_is_set = true;
    order_type_is_modified = true;
  }


  /** 
   * <em>deal_type</em>カラムの値を設定します。 
   *
   * @@param p_dealType <em>deal_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setDealType( String p_dealType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deal_type = p_dealType;
    deal_type_is_set = true;
    deal_type_is_modified = true;
  }


  /** 
   * <em>order_event_type</em>カラムの値を設定します。 
   *
   * @@param p_orderEventType <em>order_event_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum値 
   */
  public final void setOrderEventType( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum p_orderEventType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_event_type = p_orderEventType;
    order_event_type_is_set = true;
    order_event_type_is_modified = true;
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
   * <em>execution_condition_type</em>カラムの値を設定します。 
   *
   * @@param p_executionConditionType <em>execution_condition_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.xbbd.BondExecutionConditionType値 
   */
  public final void setExecutionConditionType( com.fitechlabs.xtrade.plugin.tc.xbbd.BondExecutionConditionType p_executionConditionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    execution_condition_type = p_executionConditionType;
    execution_condition_type_is_set = true;
    execution_condition_type_is_modified = true;
  }


  /** 
   * <em>quantity</em>カラムの値を設定します。 
   *
   * @@param p_quantity <em>quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * <em>limit_price</em>カラムの値を設定します。 
   *
   * @@param p_limitPrice <em>limit_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLimitPrice( double p_limitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limit_price = new Double(p_limitPrice);
    limit_price_is_set = true;
    limit_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>limit_price</em>カラムに値を設定します。 
   */
  public final void setLimitPrice( Double p_limitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limit_price = p_limitPrice;
    limit_price_is_set = true;
    limit_price_is_modified = true;
  }


  /** 
   * <em>confirmed_quantity</em>カラムの値を設定します。 
   *
   * @@param p_confirmedQuantity <em>confirmed_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setConfirmedQuantity( double p_confirmedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_quantity = new Double(p_confirmedQuantity);
    confirmed_quantity_is_set = true;
    confirmed_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>confirmed_quantity</em>カラムに値を設定します。 
   */
  public final void setConfirmedQuantity( Double p_confirmedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_quantity = p_confirmedQuantity;
    confirmed_quantity_is_set = true;
    confirmed_quantity_is_modified = true;
  }


  /** 
   * <em>confirmed_price</em>カラムの値を設定します。 
   *
   * @@param p_confirmedPrice <em>confirmed_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setConfirmedPrice( double p_confirmedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_price = new Double(p_confirmedPrice);
    confirmed_price_is_set = true;
    confirmed_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>confirmed_price</em>カラムに値を設定します。 
   */
  public final void setConfirmedPrice( Double p_confirmedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_price = p_confirmedPrice;
    confirmed_price_is_set = true;
    confirmed_price_is_modified = true;
  }


  /** 
   * <em>executed_quantity</em>カラムの値を設定します。 
   *
   * @@param p_executedQuantity <em>executed_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setExecutedQuantity( double p_executedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    executed_quantity = new Double(p_executedQuantity);
    executed_quantity_is_set = true;
    executed_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>executed_quantity</em>カラムに値を設定します。 
   */
  public final void setExecutedQuantity( Double p_executedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    executed_quantity = p_executedQuantity;
    executed_quantity_is_set = true;
    executed_quantity_is_modified = true;
  }


  /** 
   * <em>order_status</em>カラムの値を設定します。 
   *
   * @@param p_orderStatus <em>order_status</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum値 
   */
  public final void setOrderStatus( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum p_orderStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_status = p_orderStatus;
    order_status_is_set = true;
    order_status_is_modified = true;
  }


  /** 
   * <em>expiration_status</em>カラムの値を設定します。 
   *
   * @@param p_expirationStatus <em>expiration_status</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum値 
   */
  public final void setExpirationStatus( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum p_expirationStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    expiration_status = p_expirationStatus;
    expiration_status_is_set = true;
    expiration_status_is_modified = true;
  }


  /** 
   * <em>order_exec_status</em>カラムの値を設定します。 
   *
   * @@param p_orderExecStatus <em>order_exec_status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderExecStatus( String p_orderExecStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_exec_status = p_orderExecStatus;
    order_exec_status_is_set = true;
    order_exec_status_is_modified = true;
  }


  /** 
   * <em>order_action_serial_no</em>カラムの値を設定します。 
   *
   * @@param p_orderActionSerialNo <em>order_action_serial_no</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setOrderActionSerialNo( int p_orderActionSerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_action_serial_no = p_orderActionSerialNo;
    order_action_serial_no_is_set = true;
    order_action_serial_no_is_modified = true;
  }


  /** 
   * <em>executed_price</em>カラムの値を設定します。 
   *
   * @@param p_executedPrice <em>executed_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setExecutedPrice( double p_executedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    executed_price = new Double(p_executedPrice);
    executed_price_is_set = true;
    executed_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>executed_price</em>カラムに値を設定します。 
   */
  public final void setExecutedPrice( Double p_executedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    executed_price = p_executedPrice;
    executed_price_is_set = true;
    executed_price_is_modified = true;
  }


  /** 
   * <em>product_type</em>カラムの値を設定します。 
   *
   * @@param p_productType <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public final void setProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_type = p_productType;
    product_type_is_set = true;
    product_type_is_modified = true;
  }


  /** 
   * <em>product_id</em>カラムの値を設定します。 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setProductId( long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = p_productId;
    product_id_is_set = true;
    product_id_is_modified = true;
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
   * <em>foreign_exec_date</em>カラムの値を設定します。 
   *
   * @@param p_foreignExecDate <em>foreign_exec_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setForeignExecDate( java.sql.Timestamp p_foreignExecDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_exec_date = p_foreignExecDate;
    foreign_exec_date_is_set = true;
    foreign_exec_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>foreign_exec_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setForeignExecDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_exec_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    foreign_exec_date_is_set = true;
    foreign_exec_date_is_modified = true;
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
   * <em>foreign_delivery_date</em>カラムの値を設定します。 
   *
   * @@param p_foreignDeliveryDate <em>foreign_delivery_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setForeignDeliveryDate( java.sql.Timestamp p_foreignDeliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_delivery_date = p_foreignDeliveryDate;
    foreign_delivery_date_is_set = true;
    foreign_delivery_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>foreign_delivery_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setForeignDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    foreign_delivery_date_is_set = true;
    foreign_delivery_date_is_modified = true;
  }


  /** 
   * <em>payment_date</em>カラムの値を設定します。 
   *
   * @@param p_paymentDate <em>payment_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setPaymentDate( java.sql.Timestamp p_paymentDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_date = p_paymentDate;
    payment_date_is_set = true;
    payment_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>payment_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setPaymentDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    payment_date_is_set = true;
    payment_date_is_modified = true;
  }


  /** 
   * <em>base_fx_rate</em>カラムの値を設定します。 
   *
   * @@param p_baseFxRate <em>base_fx_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBaseFxRate( double p_baseFxRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_fx_rate = new Double(p_baseFxRate);
    base_fx_rate_is_set = true;
    base_fx_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>base_fx_rate</em>カラムに値を設定します。 
   */
  public final void setBaseFxRate( Double p_baseFxRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    base_fx_rate = p_baseFxRate;
    base_fx_rate_is_set = true;
    base_fx_rate_is_modified = true;
  }


  /** 
   * <em>exec_fx_rate</em>カラムの値を設定します。 
   *
   * @@param p_execFxRate <em>exec_fx_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setExecFxRate( double p_execFxRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_fx_rate = new Double(p_execFxRate);
    exec_fx_rate_is_set = true;
    exec_fx_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>exec_fx_rate</em>カラムに値を設定します。 
   */
  public final void setExecFxRate( Double p_execFxRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_fx_rate = p_execFxRate;
    exec_fx_rate_is_set = true;
    exec_fx_rate_is_modified = true;
  }


  /** 
   * <em>trading_price</em>カラムの値を設定します。 
   *
   * @@param p_tradingPrice <em>trading_price</em>カラムの値をあらわす21桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTradingPrice( double p_tradingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_price = new Double(p_tradingPrice);
    trading_price_is_set = true;
    trading_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>trading_price</em>カラムに値を設定します。 
   */
  public final void setTradingPrice( Double p_tradingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trading_price = p_tradingPrice;
    trading_price_is_set = true;
    trading_price_is_modified = true;
  }


  /** 
   * <em>foreign_trading_price</em>カラムの値を設定します。 
   *
   * @@param p_foreignTradingPrice <em>foreign_trading_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setForeignTradingPrice( double p_foreignTradingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_trading_price = new Double(p_foreignTradingPrice);
    foreign_trading_price_is_set = true;
    foreign_trading_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>foreign_trading_price</em>カラムに値を設定します。 
   */
  public final void setForeignTradingPrice( Double p_foreignTradingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_trading_price = p_foreignTradingPrice;
    foreign_trading_price_is_set = true;
    foreign_trading_price_is_modified = true;
  }


  /** 
   * <em>accrued_interest</em>カラムの値を設定します。 
   *
   * @@param p_accruedInterest <em>accrued_interest</em>カラムの値をあらわす21桁以下のdouble値で、その精度は6桁まで
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
   * <em>foreign_accrued_interest</em>カラムの値を設定します。 
   *
   * @@param p_foreignAccruedInterest <em>foreign_accrued_interest</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setForeignAccruedInterest( double p_foreignAccruedInterest )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_accrued_interest = new Double(p_foreignAccruedInterest);
    foreign_accrued_interest_is_set = true;
    foreign_accrued_interest_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>foreign_accrued_interest</em>カラムに値を設定します。 
   */
  public final void setForeignAccruedInterest( Double p_foreignAccruedInterest )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_accrued_interest = p_foreignAccruedInterest;
    foreign_accrued_interest_is_set = true;
    foreign_accrued_interest_is_modified = true;
  }


  /** 
   * <em>estimated_price</em>カラムの値を設定します。 
   *
   * @@param p_estimatedPrice <em>estimated_price</em>カラムの値をあらわす21桁以下のdouble値で、その精度は6桁まで
   */
  public final void setEstimatedPrice( double p_estimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    estimated_price = new Double(p_estimatedPrice);
    estimated_price_is_set = true;
    estimated_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>estimated_price</em>カラムに値を設定します。 
   */
  public final void setEstimatedPrice( Double p_estimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    estimated_price = p_estimatedPrice;
    estimated_price_is_set = true;
    estimated_price_is_modified = true;
  }


  /** 
   * <em>foreign_estimated_price</em>カラムの値を設定します。 
   *
   * @@param p_foreignEstimatedPrice <em>foreign_estimated_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setForeignEstimatedPrice( double p_foreignEstimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_estimated_price = new Double(p_foreignEstimatedPrice);
    foreign_estimated_price_is_set = true;
    foreign_estimated_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>foreign_estimated_price</em>カラムに値を設定します。 
   */
  public final void setForeignEstimatedPrice( Double p_foreignEstimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_estimated_price = p_foreignEstimatedPrice;
    foreign_estimated_price_is_set = true;
    foreign_estimated_price_is_modified = true;
  }


  /** 
   * <em>adjustment_before_maturity</em>カラムの値を設定します。 
   *
   * @@param p_adjustmentBeforeMaturity <em>adjustment_before_maturity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAdjustmentBeforeMaturity( double p_adjustmentBeforeMaturity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    adjustment_before_maturity = new Double(p_adjustmentBeforeMaturity);
    adjustment_before_maturity_is_set = true;
    adjustment_before_maturity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>adjustment_before_maturity</em>カラムに値を設定します。 
   */
  public final void setAdjustmentBeforeMaturity( Double p_adjustmentBeforeMaturity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    adjustment_before_maturity = p_adjustmentBeforeMaturity;
    adjustment_before_maturity_is_set = true;
    adjustment_before_maturity_is_modified = true;
  }


  /** 
   * <em>elapsed_days</em>カラムの値を設定します。 
   *
   * @@param p_elapsedDays <em>elapsed_days</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setElapsedDays( int p_elapsedDays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    elapsed_days = new Integer(p_elapsedDays);
    elapsed_days_is_set = true;
    elapsed_days_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>elapsed_days</em>カラムに値を設定します。 
   */
  public final void setElapsedDays( Integer p_elapsedDays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    elapsed_days = p_elapsedDays;
    elapsed_days_is_set = true;
    elapsed_days_is_modified = true;
  }


  /** 
   * <em>calc_base_days</em>カラムの値を設定します。 
   *
   * @@param p_calcBaseDays <em>calc_base_days</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setCalcBaseDays( int p_calcBaseDays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_base_days = new Integer(p_calcBaseDays);
    calc_base_days_is_set = true;
    calc_base_days_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>calc_base_days</em>カラムに値を設定します。 
   */
  public final void setCalcBaseDays( Integer p_calcBaseDays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    calc_base_days = p_calcBaseDays;
    calc_base_days_is_set = true;
    calc_base_days_is_modified = true;
  }


  /** 
   * <em>custodian_code</em>カラムの値を設定します。 
   *
   * @@param p_custodianCode <em>custodian_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCustodianCode( String p_custodianCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    custodian_code = p_custodianCode;
    custodian_code_is_set = true;
    custodian_code_is_modified = true;
  }


  /** 
   * <em>order_root_div</em>カラムの値を設定します。 
   *
   * @@param p_orderRootDiv <em>order_root_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderRootDiv( String p_orderRootDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_root_div = p_orderRootDiv;
    order_root_div_is_set = true;
    order_root_div_is_modified = true;
  }


  /** 
   * <em>administrator_code</em>カラムの値を設定します。 
   *
   * @@param p_administratorCode <em>administrator_code</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setAdministratorCode( String p_administratorCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    administrator_code = p_administratorCode;
    administrator_code_is_set = true;
    administrator_code_is_modified = true;
  }


  /** 
   * <em>error_reason_code</em>カラムの値を設定します。 
   *
   * @@param p_errorReasonCode <em>error_reason_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setErrorReasonCode( String p_errorReasonCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_reason_code = p_errorReasonCode;
    error_reason_code_is_set = true;
    error_reason_code_is_modified = true;
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
                else if ( name.equals("accrued_interest") ) {
                    return this.accrued_interest;
                }
                else if ( name.equals("adjustment_before_maturity") ) {
                    return this.adjustment_before_maturity;
                }
                else if ( name.equals("administrator_code") ) {
                    return this.administrator_code;
                }
                break;
            case 'b':
                if ( name.equals("base_fx_rate") ) {
                    return this.base_fx_rate;
                }
                break;
            case 'c':
                if ( name.equals("confirmed_quantity") ) {
                    return this.confirmed_quantity;
                }
                else if ( name.equals("confirmed_price") ) {
                    return this.confirmed_price;
                }
                else if ( name.equals("calc_base_days") ) {
                    return this.calc_base_days;
                }
                else if ( name.equals("custodian_code") ) {
                    return this.custodian_code;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("deal_type") ) {
                    return this.deal_type;
                }
                else if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                break;
            case 'e':
                if ( name.equals("execution_condition_type") ) {
                    return this.execution_condition_type;
                }
                else if ( name.equals("executed_quantity") ) {
                    return this.executed_quantity;
                }
                else if ( name.equals("expiration_status") ) {
                    return this.expiration_status;
                }
                else if ( name.equals("executed_price") ) {
                    return this.executed_price;
                }
                else if ( name.equals("exec_date") ) {
                    return this.exec_date;
                }
                else if ( name.equals("exec_fx_rate") ) {
                    return this.exec_fx_rate;
                }
                else if ( name.equals("estimated_price") ) {
                    return this.estimated_price;
                }
                else if ( name.equals("elapsed_days") ) {
                    return this.elapsed_days;
                }
                else if ( name.equals("error_reason_code") ) {
                    return this.error_reason_code;
                }
                break;
            case 'f':
                if ( name.equals("foreign_exec_date") ) {
                    return this.foreign_exec_date;
                }
                else if ( name.equals("foreign_delivery_date") ) {
                    return this.foreign_delivery_date;
                }
                else if ( name.equals("foreign_trading_price") ) {
                    return this.foreign_trading_price;
                }
                else if ( name.equals("foreign_accrued_interest") ) {
                    return this.foreign_accrued_interest;
                }
                else if ( name.equals("foreign_estimated_price") ) {
                    return this.foreign_estimated_price;
                }
                break;
            case 'l':
                if ( name.equals("limit_price") ) {
                    return this.limit_price;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return this.market_id;
                }
                break;
            case 'o':
                if ( name.equals("order_action_id") ) {
                    return new Long( order_action_id );
                }
                else if ( name.equals("order_id") ) {
                    return new Long( order_id );
                }
                else if ( name.equals("order_unit_id") ) {
                    return new Long( order_unit_id );
                }
                else if ( name.equals("order_type") ) {
                    return this.order_type;
                }
                else if ( name.equals("order_event_type") ) {
                    return this.order_event_type;
                }
                else if ( name.equals("order_status") ) {
                    return this.order_status;
                }
                else if ( name.equals("order_exec_status") ) {
                    return this.order_exec_status;
                }
                else if ( name.equals("order_action_serial_no") ) {
                    return new Integer( order_action_serial_no );
                }
                else if ( name.equals("order_root_div") ) {
                    return this.order_root_div;
                }
                break;
            case 'p':
                if ( name.equals("price") ) {
                    return this.price;
                }
                else if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("payment_date") ) {
                    return this.payment_date;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return new Double( quantity );
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    return this.trader_id;
                }
                else if ( name.equals("trading_price") ) {
                    return this.trading_price;
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
                else if ( name.equals("adjustment_before_maturity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'adjustment_before_maturity' must be Double: '"+value+"' is not." );
                    this.adjustment_before_maturity = (Double) value;
                    if (this.adjustment_before_maturity_is_set)
                        this.adjustment_before_maturity_is_modified = true;
                    this.adjustment_before_maturity_is_set = true;
                    return;
                }
                else if ( name.equals("administrator_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'administrator_code' must be String: '"+value+"' is not." );
                    this.administrator_code = (String) value;
                    if (this.administrator_code_is_set)
                        this.administrator_code_is_modified = true;
                    this.administrator_code_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("base_fx_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'base_fx_rate' must be Double: '"+value+"' is not." );
                    this.base_fx_rate = (Double) value;
                    if (this.base_fx_rate_is_set)
                        this.base_fx_rate_is_modified = true;
                    this.base_fx_rate_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("confirmed_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'confirmed_quantity' must be Double: '"+value+"' is not." );
                    this.confirmed_quantity = (Double) value;
                    if (this.confirmed_quantity_is_set)
                        this.confirmed_quantity_is_modified = true;
                    this.confirmed_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("confirmed_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'confirmed_price' must be Double: '"+value+"' is not." );
                    this.confirmed_price = (Double) value;
                    if (this.confirmed_price_is_set)
                        this.confirmed_price_is_modified = true;
                    this.confirmed_price_is_set = true;
                    return;
                }
                else if ( name.equals("calc_base_days") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'calc_base_days' must be Integer: '"+value+"' is not." );
                    this.calc_base_days = (Integer) value;
                    if (this.calc_base_days_is_set)
                        this.calc_base_days_is_modified = true;
                    this.calc_base_days_is_set = true;
                    return;
                }
                else if ( name.equals("custodian_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'custodian_code' must be String: '"+value+"' is not." );
                    this.custodian_code = (String) value;
                    if (this.custodian_code_is_set)
                        this.custodian_code_is_modified = true;
                    this.custodian_code_is_set = true;
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
                if ( name.equals("deal_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deal_type' must be String: '"+value+"' is not." );
                    this.deal_type = (String) value;
                    if (this.deal_type_is_set)
                        this.deal_type_is_modified = true;
                    this.deal_type_is_set = true;
                    return;
                }
                else if ( name.equals("delivery_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("execution_condition_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.xbbd.BondExecutionConditionType) )
                        throw new IllegalArgumentException( "value for 'execution_condition_type' must be com.fitechlabs.xtrade.plugin.tc.xbbd.BondExecutionConditionType: '"+value+"' is not." );
                    this.execution_condition_type = (com.fitechlabs.xtrade.plugin.tc.xbbd.BondExecutionConditionType) value;
                    if (this.execution_condition_type_is_set)
                        this.execution_condition_type_is_modified = true;
                    this.execution_condition_type_is_set = true;
                    return;
                }
                else if ( name.equals("executed_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'executed_quantity' must be Double: '"+value+"' is not." );
                    this.executed_quantity = (Double) value;
                    if (this.executed_quantity_is_set)
                        this.executed_quantity_is_modified = true;
                    this.executed_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("expiration_status") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum) )
                        throw new IllegalArgumentException( "value for 'expiration_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum: '"+value+"' is not." );
                    this.expiration_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum) value;
                    if (this.expiration_status_is_set)
                        this.expiration_status_is_modified = true;
                    this.expiration_status_is_set = true;
                    return;
                }
                else if ( name.equals("executed_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'executed_price' must be Double: '"+value+"' is not." );
                    this.executed_price = (Double) value;
                    if (this.executed_price_is_set)
                        this.executed_price_is_modified = true;
                    this.executed_price_is_set = true;
                    return;
                }
                else if ( name.equals("exec_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'exec_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.exec_date = (java.sql.Timestamp) value;
                    if (this.exec_date_is_set)
                        this.exec_date_is_modified = true;
                    this.exec_date_is_set = true;
                    return;
                }
                else if ( name.equals("exec_fx_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'exec_fx_rate' must be Double: '"+value+"' is not." );
                    this.exec_fx_rate = (Double) value;
                    if (this.exec_fx_rate_is_set)
                        this.exec_fx_rate_is_modified = true;
                    this.exec_fx_rate_is_set = true;
                    return;
                }
                else if ( name.equals("estimated_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'estimated_price' must be Double: '"+value+"' is not." );
                    this.estimated_price = (Double) value;
                    if (this.estimated_price_is_set)
                        this.estimated_price_is_modified = true;
                    this.estimated_price_is_set = true;
                    return;
                }
                else if ( name.equals("elapsed_days") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'elapsed_days' must be Integer: '"+value+"' is not." );
                    this.elapsed_days = (Integer) value;
                    if (this.elapsed_days_is_set)
                        this.elapsed_days_is_modified = true;
                    this.elapsed_days_is_set = true;
                    return;
                }
                else if ( name.equals("error_reason_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_reason_code' must be String: '"+value+"' is not." );
                    this.error_reason_code = (String) value;
                    if (this.error_reason_code_is_set)
                        this.error_reason_code_is_modified = true;
                    this.error_reason_code_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("foreign_exec_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'foreign_exec_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.foreign_exec_date = (java.sql.Timestamp) value;
                    if (this.foreign_exec_date_is_set)
                        this.foreign_exec_date_is_modified = true;
                    this.foreign_exec_date_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_delivery_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'foreign_delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.foreign_delivery_date = (java.sql.Timestamp) value;
                    if (this.foreign_delivery_date_is_set)
                        this.foreign_delivery_date_is_modified = true;
                    this.foreign_delivery_date_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_trading_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'foreign_trading_price' must be Double: '"+value+"' is not." );
                    this.foreign_trading_price = (Double) value;
                    if (this.foreign_trading_price_is_set)
                        this.foreign_trading_price_is_modified = true;
                    this.foreign_trading_price_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_accrued_interest") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'foreign_accrued_interest' must be Double: '"+value+"' is not." );
                    this.foreign_accrued_interest = (Double) value;
                    if (this.foreign_accrued_interest_is_set)
                        this.foreign_accrued_interest_is_modified = true;
                    this.foreign_accrued_interest_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_estimated_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'foreign_estimated_price' must be Double: '"+value+"' is not." );
                    this.foreign_estimated_price = (Double) value;
                    if (this.foreign_estimated_price_is_set)
                        this.foreign_estimated_price_is_modified = true;
                    this.foreign_estimated_price_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'limit_price' must be Double: '"+value+"' is not." );
                    this.limit_price = (Double) value;
                    if (this.limit_price_is_set)
                        this.limit_price_is_modified = true;
                    this.limit_price_is_set = true;
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
                if ( name.equals("market_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = (Long) value;
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_action_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_action_id' must be Long: '"+value+"' is not." );
                    this.order_action_id = ((Long) value).longValue();
                    if (this.order_action_id_is_set)
                        this.order_action_id_is_modified = true;
                    this.order_action_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = ((Long) value).longValue();
                    if (this.order_id_is_set)
                        this.order_id_is_modified = true;
                    this.order_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_unit_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_unit_id' must be Long: '"+value+"' is not." );
                    this.order_unit_id = ((Long) value).longValue();
                    if (this.order_unit_id_is_set)
                        this.order_unit_id_is_modified = true;
                    this.order_unit_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) )
                        throw new IllegalArgumentException( "value for 'order_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum: '"+value+"' is not." );
                    this.order_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) value;
                    if (this.order_type_is_set)
                        this.order_type_is_modified = true;
                    this.order_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_event_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum) )
                        throw new IllegalArgumentException( "value for 'order_event_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum: '"+value+"' is not." );
                    this.order_event_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum) value;
                    if (this.order_event_type_is_set)
                        this.order_event_type_is_modified = true;
                    this.order_event_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_status") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum) )
                        throw new IllegalArgumentException( "value for 'order_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum: '"+value+"' is not." );
                    this.order_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum) value;
                    if (this.order_status_is_set)
                        this.order_status_is_modified = true;
                    this.order_status_is_set = true;
                    return;
                }
                else if ( name.equals("order_exec_status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_exec_status' must be String: '"+value+"' is not." );
                    this.order_exec_status = (String) value;
                    if (this.order_exec_status_is_set)
                        this.order_exec_status_is_modified = true;
                    this.order_exec_status_is_set = true;
                    return;
                }
                else if ( name.equals("order_action_serial_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'order_action_serial_no' must be Integer: '"+value+"' is not." );
                    this.order_action_serial_no = ((Integer) value).intValue();
                    if (this.order_action_serial_no_is_set)
                        this.order_action_serial_no_is_modified = true;
                    this.order_action_serial_no_is_set = true;
                    return;
                }
                else if ( name.equals("order_root_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_root_div' must be String: '"+value+"' is not." );
                    this.order_root_div = (String) value;
                    if (this.order_root_div_is_set)
                        this.order_root_div_is_modified = true;
                    this.order_root_div_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'price' must be Double: '"+value+"' is not." );
                    this.price = (Double) value;
                    if (this.price_is_set)
                        this.price_is_modified = true;
                    this.price_is_set = true;
                    return;
                }
                else if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                else if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                else if ( name.equals("payment_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'payment_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.payment_date = (java.sql.Timestamp) value;
                    if (this.payment_date_is_set)
                        this.payment_date_is_modified = true;
                    this.payment_date_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = ((Double) value).doubleValue();
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_account_id' must be Long: '"+value+"' is not." );
                    this.sub_account_id = ((Long) value).longValue();
                    if (this.sub_account_id_is_set)
                        this.sub_account_id_is_modified = true;
                    this.sub_account_id_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'trader_id' must be Long: '"+value+"' is not." );
                    this.trader_id = (Long) value;
                    if (this.trader_id_is_set)
                        this.trader_id_is_modified = true;
                    this.trader_id_is_set = true;
                    return;
                }
                else if ( name.equals("trading_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'trading_price' must be Double: '"+value+"' is not." );
                    this.trading_price = (Double) value;
                    if (this.trading_price_is_set)
                        this.trading_price_is_modified = true;
                    this.trading_price_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
