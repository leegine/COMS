head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeOrderActionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * eqtype_order_actionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link EqtypeOrderActionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link EqtypeOrderActionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link EqtypeOrderActionParams}が{@@link EqtypeOrderActionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeOrderActionPK 
 * @@see EqtypeOrderActionRow 
 */
public class EqtypeOrderActionParams extends Params implements EqtypeOrderActionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "eqtype_order_action";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = EqtypeOrderActionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return EqtypeOrderActionRow.TYPE;
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
  public  com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType  execution_condition_type;

  /** 
   * <em>price_condition_type</em>カラムの値 
   */
  public  String  price_condition_type;

  /** 
   * <em>order_condition_type</em>カラムの値 
   */
  public  String  order_condition_type;

  /** 
   * <em>order_cond_operator</em>カラムの値 
   */
  public  String  order_cond_operator;

  /** 
   * <em>stop_order_price</em>カラムの値 
   */
  public  Double  stop_order_price;

  /** 
   * <em>w_limit_price</em>カラムの値 
   */
  public  Double  w_limit_price;

  /** 
   * <em>expiration_date</em>カラムの値 
   */
  public  java.sql.Timestamp  expiration_date;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  double  quantity;

  /** 
   * <em>confirmed_price</em>カラムの値 
   */
  public  Double  confirmed_price;

  /** 
   * <em>confirmed_quantity</em>カラムの値 
   */
  public  Double  confirmed_quantity;

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
   * <em>quantity_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum  quantity_type;

  /** 
   * <em>estimated_price</em>カラムの値 
   */
  public  Double  estimated_price;

  /** 
   * <em>modify_cancel_type</em>カラムの値 
   */
  public  String  modify_cancel_type;

  /** 
   * <em>order_root_div</em>カラムの値 
   */
  public  String  order_root_div;

  /** 
   * <em>closing_order_type</em>カラムの値 
   */
  public  String  closing_order_type;

  /** 
   * <em>error_reason_code</em>カラムの値 
   */
  public  String  error_reason_code;

  /** 
   * <em>request_type</em>カラムの値 
   */
  public  String  request_type;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>ip_address</em>カラムの値 
   */
  public  String  ip_address;

  /** 
   * <em>org_order_condition_type</em>カラムの値 
   */
  public  String  org_order_condition_type;

  /** 
   * <em>org_order_cond_operator</em>カラムの値 
   */
  public  String  org_order_cond_operator;

  /** 
   * <em>org_stop_order_price</em>カラムの値 
   */
  public  Double  org_stop_order_price;

  /** 
   * <em>org_w_limit_price</em>カラムの値 
   */
  public  Double  org_w_limit_price;

  /** 
   * <em>org_w_limit_exec_cond_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType  org_w_limit_exec_cond_type;

  /** 
   * <em>w_limit_exec_cond_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType  w_limit_exec_cond_type;

  /** 
   * <em>w_limit_before_limit_price</em>カラムの値 
   */
  public  Double  w_limit_before_limit_price;

  /** 
   * <em>w_limit_before_exec_cond_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType  w_limit_before_exec_cond_type;

  /** 
   * <em>confirmed_exec_condition_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType  confirmed_exec_condition_type;

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
  private boolean order_event_type_is_set = false;
  private boolean order_event_type_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean execution_condition_type_is_set = false;
  private boolean execution_condition_type_is_modified = false;
  private boolean price_condition_type_is_set = false;
  private boolean price_condition_type_is_modified = false;
  private boolean order_condition_type_is_set = false;
  private boolean order_condition_type_is_modified = false;
  private boolean order_cond_operator_is_set = false;
  private boolean order_cond_operator_is_modified = false;
  private boolean stop_order_price_is_set = false;
  private boolean stop_order_price_is_modified = false;
  private boolean w_limit_price_is_set = false;
  private boolean w_limit_price_is_modified = false;
  private boolean expiration_date_is_set = false;
  private boolean expiration_date_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean confirmed_price_is_set = false;
  private boolean confirmed_price_is_modified = false;
  private boolean confirmed_quantity_is_set = false;
  private boolean confirmed_quantity_is_modified = false;
  private boolean executed_quantity_is_set = false;
  private boolean executed_quantity_is_modified = false;
  private boolean order_status_is_set = false;
  private boolean order_status_is_modified = false;
  private boolean expiration_status_is_set = false;
  private boolean expiration_status_is_modified = false;
  private boolean order_action_serial_no_is_set = false;
  private boolean order_action_serial_no_is_modified = false;
  private boolean executed_price_is_set = false;
  private boolean executed_price_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean quantity_type_is_set = false;
  private boolean quantity_type_is_modified = false;
  private boolean estimated_price_is_set = false;
  private boolean estimated_price_is_modified = false;
  private boolean modify_cancel_type_is_set = false;
  private boolean modify_cancel_type_is_modified = false;
  private boolean order_root_div_is_set = false;
  private boolean order_root_div_is_modified = false;
  private boolean closing_order_type_is_set = false;
  private boolean closing_order_type_is_modified = false;
  private boolean error_reason_code_is_set = false;
  private boolean error_reason_code_is_modified = false;
  private boolean request_type_is_set = false;
  private boolean request_type_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean ip_address_is_set = false;
  private boolean ip_address_is_modified = false;
  private boolean org_order_condition_type_is_set = false;
  private boolean org_order_condition_type_is_modified = false;
  private boolean org_order_cond_operator_is_set = false;
  private boolean org_order_cond_operator_is_modified = false;
  private boolean org_stop_order_price_is_set = false;
  private boolean org_stop_order_price_is_modified = false;
  private boolean org_w_limit_price_is_set = false;
  private boolean org_w_limit_price_is_modified = false;
  private boolean org_w_limit_exec_cond_type_is_set = false;
  private boolean org_w_limit_exec_cond_type_is_modified = false;
  private boolean w_limit_exec_cond_type_is_set = false;
  private boolean w_limit_exec_cond_type_is_modified = false;
  private boolean w_limit_before_limit_price_is_set = false;
  private boolean w_limit_before_limit_price_is_modified = false;
  private boolean w_limit_before_exec_cond_type_is_set = false;
  private boolean w_limit_before_exec_cond_type_is_modified = false;
  private boolean confirmed_exec_condition_type_is_set = false;
  private boolean confirmed_exec_condition_type_is_modified = false;


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
      + "," + "order_event_type=" +order_event_type
      + "," + "price=" +price
      + "," + "execution_condition_type=" +execution_condition_type
      + "," + "price_condition_type=" +price_condition_type
      + "," + "order_condition_type=" +order_condition_type
      + "," + "order_cond_operator=" +order_cond_operator
      + "," + "stop_order_price=" +stop_order_price
      + "," + "w_limit_price=" +w_limit_price
      + "," + "expiration_date=" +expiration_date
      + "," + "quantity=" +quantity
      + "," + "confirmed_price=" +confirmed_price
      + "," + "confirmed_quantity=" +confirmed_quantity
      + "," + "executed_quantity=" +executed_quantity
      + "," + "order_status=" +order_status
      + "," + "expiration_status=" +expiration_status
      + "," + "order_action_serial_no=" +order_action_serial_no
      + "," + "executed_price=" +executed_price
      + "," + "product_type=" +product_type
      + "," + "product_id=" +product_id
      + "," + "quantity_type=" +quantity_type
      + "," + "estimated_price=" +estimated_price
      + "," + "modify_cancel_type=" +modify_cancel_type
      + "," + "order_root_div=" +order_root_div
      + "," + "closing_order_type=" +closing_order_type
      + "," + "error_reason_code=" +error_reason_code
      + "," + "request_type=" +request_type
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "ip_address=" +ip_address
      + "," + "org_order_condition_type=" +org_order_condition_type
      + "," + "org_order_cond_operator=" +org_order_cond_operator
      + "," + "org_stop_order_price=" +org_stop_order_price
      + "," + "org_w_limit_price=" +org_w_limit_price
      + "," + "org_w_limit_exec_cond_type=" +org_w_limit_exec_cond_type
      + "," + "w_limit_exec_cond_type=" +w_limit_exec_cond_type
      + "," + "w_limit_before_limit_price=" +w_limit_before_limit_price
      + "," + "w_limit_before_exec_cond_type=" +w_limit_before_exec_cond_type
      + "," + "confirmed_exec_condition_type=" +confirmed_exec_condition_type
      + "}";
  }


  /** 
   * 値が未設定のEqtypeOrderActionParamsオブジェクトを作成します。 
   */
  public EqtypeOrderActionParams() {
    order_action_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のEqtypeOrderActionRowオブジェクトの値を利用してEqtypeOrderActionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するEqtypeOrderActionRowオブジェクト 
   */
  public EqtypeOrderActionParams( EqtypeOrderActionRow p_row )
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
    price_condition_type = p_row.getPriceConditionType();
    price_condition_type_is_set = p_row.getPriceConditionTypeIsSet();
    price_condition_type_is_modified = p_row.getPriceConditionTypeIsModified();
    order_condition_type = p_row.getOrderConditionType();
    order_condition_type_is_set = p_row.getOrderConditionTypeIsSet();
    order_condition_type_is_modified = p_row.getOrderConditionTypeIsModified();
    order_cond_operator = p_row.getOrderCondOperator();
    order_cond_operator_is_set = p_row.getOrderCondOperatorIsSet();
    order_cond_operator_is_modified = p_row.getOrderCondOperatorIsModified();
    if ( !p_row.getStopOrderPriceIsNull() )
      stop_order_price = new Double( p_row.getStopOrderPrice() );
    stop_order_price_is_set = p_row.getStopOrderPriceIsSet();
    stop_order_price_is_modified = p_row.getStopOrderPriceIsModified();
    if ( !p_row.getWLimitPriceIsNull() )
      w_limit_price = new Double( p_row.getWLimitPrice() );
    w_limit_price_is_set = p_row.getWLimitPriceIsSet();
    w_limit_price_is_modified = p_row.getWLimitPriceIsModified();
    expiration_date = p_row.getExpirationDate();
    expiration_date_is_set = p_row.getExpirationDateIsSet();
    expiration_date_is_modified = p_row.getExpirationDateIsModified();
    quantity = p_row.getQuantity();
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    if ( !p_row.getConfirmedPriceIsNull() )
      confirmed_price = new Double( p_row.getConfirmedPrice() );
    confirmed_price_is_set = p_row.getConfirmedPriceIsSet();
    confirmed_price_is_modified = p_row.getConfirmedPriceIsModified();
    if ( !p_row.getConfirmedQuantityIsNull() )
      confirmed_quantity = new Double( p_row.getConfirmedQuantity() );
    confirmed_quantity_is_set = p_row.getConfirmedQuantityIsSet();
    confirmed_quantity_is_modified = p_row.getConfirmedQuantityIsModified();
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
    quantity_type = p_row.getQuantityType();
    quantity_type_is_set = p_row.getQuantityTypeIsSet();
    quantity_type_is_modified = p_row.getQuantityTypeIsModified();
    if ( !p_row.getEstimatedPriceIsNull() )
      estimated_price = new Double( p_row.getEstimatedPrice() );
    estimated_price_is_set = p_row.getEstimatedPriceIsSet();
    estimated_price_is_modified = p_row.getEstimatedPriceIsModified();
    modify_cancel_type = p_row.getModifyCancelType();
    modify_cancel_type_is_set = p_row.getModifyCancelTypeIsSet();
    modify_cancel_type_is_modified = p_row.getModifyCancelTypeIsModified();
    order_root_div = p_row.getOrderRootDiv();
    order_root_div_is_set = p_row.getOrderRootDivIsSet();
    order_root_div_is_modified = p_row.getOrderRootDivIsModified();
    closing_order_type = p_row.getClosingOrderType();
    closing_order_type_is_set = p_row.getClosingOrderTypeIsSet();
    closing_order_type_is_modified = p_row.getClosingOrderTypeIsModified();
    error_reason_code = p_row.getErrorReasonCode();
    error_reason_code_is_set = p_row.getErrorReasonCodeIsSet();
    error_reason_code_is_modified = p_row.getErrorReasonCodeIsModified();
    request_type = p_row.getRequestType();
    request_type_is_set = p_row.getRequestTypeIsSet();
    request_type_is_modified = p_row.getRequestTypeIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    ip_address = p_row.getIpAddress();
    ip_address_is_set = p_row.getIpAddressIsSet();
    ip_address_is_modified = p_row.getIpAddressIsModified();
    org_order_condition_type = p_row.getOrgOrderConditionType();
    org_order_condition_type_is_set = p_row.getOrgOrderConditionTypeIsSet();
    org_order_condition_type_is_modified = p_row.getOrgOrderConditionTypeIsModified();
    org_order_cond_operator = p_row.getOrgOrderCondOperator();
    org_order_cond_operator_is_set = p_row.getOrgOrderCondOperatorIsSet();
    org_order_cond_operator_is_modified = p_row.getOrgOrderCondOperatorIsModified();
    if ( !p_row.getOrgStopOrderPriceIsNull() )
      org_stop_order_price = new Double( p_row.getOrgStopOrderPrice() );
    org_stop_order_price_is_set = p_row.getOrgStopOrderPriceIsSet();
    org_stop_order_price_is_modified = p_row.getOrgStopOrderPriceIsModified();
    if ( !p_row.getOrgWLimitPriceIsNull() )
      org_w_limit_price = new Double( p_row.getOrgWLimitPrice() );
    org_w_limit_price_is_set = p_row.getOrgWLimitPriceIsSet();
    org_w_limit_price_is_modified = p_row.getOrgWLimitPriceIsModified();
    org_w_limit_exec_cond_type = p_row.getOrgWLimitExecCondType();
    org_w_limit_exec_cond_type_is_set = p_row.getOrgWLimitExecCondTypeIsSet();
    org_w_limit_exec_cond_type_is_modified = p_row.getOrgWLimitExecCondTypeIsModified();
    w_limit_exec_cond_type = p_row.getWLimitExecCondType();
    w_limit_exec_cond_type_is_set = p_row.getWLimitExecCondTypeIsSet();
    w_limit_exec_cond_type_is_modified = p_row.getWLimitExecCondTypeIsModified();
    if ( !p_row.getWLimitBeforeLimitPriceIsNull() )
      w_limit_before_limit_price = new Double( p_row.getWLimitBeforeLimitPrice() );
    w_limit_before_limit_price_is_set = p_row.getWLimitBeforeLimitPriceIsSet();
    w_limit_before_limit_price_is_modified = p_row.getWLimitBeforeLimitPriceIsModified();
    w_limit_before_exec_cond_type = p_row.getWLimitBeforeExecCondType();
    w_limit_before_exec_cond_type_is_set = p_row.getWLimitBeforeExecCondTypeIsSet();
    w_limit_before_exec_cond_type_is_modified = p_row.getWLimitBeforeExecCondTypeIsModified();
    confirmed_exec_condition_type = p_row.getConfirmedExecConditionType();
    confirmed_exec_condition_type_is_set = p_row.getConfirmedExecConditionTypeIsSet();
    confirmed_exec_condition_type_is_modified = p_row.getConfirmedExecConditionTypeIsModified();
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
      order_event_type_is_set = true;
      order_event_type_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      execution_condition_type_is_set = true;
      execution_condition_type_is_modified = true;
      price_condition_type_is_set = true;
      price_condition_type_is_modified = true;
      order_condition_type_is_set = true;
      order_condition_type_is_modified = true;
      order_cond_operator_is_set = true;
      order_cond_operator_is_modified = true;
      stop_order_price_is_set = true;
      stop_order_price_is_modified = true;
      w_limit_price_is_set = true;
      w_limit_price_is_modified = true;
      expiration_date_is_set = true;
      expiration_date_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      confirmed_price_is_set = true;
      confirmed_price_is_modified = true;
      confirmed_quantity_is_set = true;
      confirmed_quantity_is_modified = true;
      executed_quantity_is_set = true;
      executed_quantity_is_modified = true;
      order_status_is_set = true;
      order_status_is_modified = true;
      expiration_status_is_set = true;
      expiration_status_is_modified = true;
      order_action_serial_no_is_set = true;
      order_action_serial_no_is_modified = true;
      executed_price_is_set = true;
      executed_price_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      quantity_type_is_set = true;
      quantity_type_is_modified = true;
      estimated_price_is_set = true;
      estimated_price_is_modified = true;
      modify_cancel_type_is_set = true;
      modify_cancel_type_is_modified = true;
      order_root_div_is_set = true;
      order_root_div_is_modified = true;
      closing_order_type_is_set = true;
      closing_order_type_is_modified = true;
      error_reason_code_is_set = true;
      error_reason_code_is_modified = true;
      request_type_is_set = true;
      request_type_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      ip_address_is_set = true;
      ip_address_is_modified = true;
      org_order_condition_type_is_set = true;
      org_order_condition_type_is_modified = true;
      org_order_cond_operator_is_set = true;
      org_order_cond_operator_is_modified = true;
      org_stop_order_price_is_set = true;
      org_stop_order_price_is_modified = true;
      org_w_limit_price_is_set = true;
      org_w_limit_price_is_modified = true;
      org_w_limit_exec_cond_type_is_set = true;
      org_w_limit_exec_cond_type_is_modified = true;
      w_limit_exec_cond_type_is_set = true;
      w_limit_exec_cond_type_is_modified = true;
      w_limit_before_limit_price_is_set = true;
      w_limit_before_limit_price_is_modified = true;
      w_limit_before_exec_cond_type_is_set = true;
      w_limit_before_exec_cond_type_is_modified = true;
      confirmed_exec_condition_type_is_set = true;
      confirmed_exec_condition_type_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof EqtypeOrderActionRow ) )
       return false;
    return fieldsEqual( (EqtypeOrderActionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のEqtypeOrderActionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( EqtypeOrderActionRow row )
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
    if ( price_condition_type == null ) {
      if ( row.getPriceConditionType() != null )
        return false;
    } else if ( !price_condition_type.equals( row.getPriceConditionType() ) ) {
        return false;
    }
    if ( order_condition_type == null ) {
      if ( row.getOrderConditionType() != null )
        return false;
    } else if ( !order_condition_type.equals( row.getOrderConditionType() ) ) {
        return false;
    }
    if ( order_cond_operator == null ) {
      if ( row.getOrderCondOperator() != null )
        return false;
    } else if ( !order_cond_operator.equals( row.getOrderCondOperator() ) ) {
        return false;
    }
    if ( stop_order_price == null ) {
      if ( !row.getStopOrderPriceIsNull() )
        return false;
    } else if ( row.getStopOrderPriceIsNull() || ( stop_order_price.doubleValue() != row.getStopOrderPrice() ) ) {
        return false;
    }
    if ( w_limit_price == null ) {
      if ( !row.getWLimitPriceIsNull() )
        return false;
    } else if ( row.getWLimitPriceIsNull() || ( w_limit_price.doubleValue() != row.getWLimitPrice() ) ) {
        return false;
    }
    if ( expiration_date == null ) {
      if ( row.getExpirationDate() != null )
        return false;
    } else if ( !expiration_date.equals( row.getExpirationDate() ) ) {
        return false;
    }
    if ( quantity != row.getQuantity() )
      return false;
    if ( confirmed_price == null ) {
      if ( !row.getConfirmedPriceIsNull() )
        return false;
    } else if ( row.getConfirmedPriceIsNull() || ( confirmed_price.doubleValue() != row.getConfirmedPrice() ) ) {
        return false;
    }
    if ( confirmed_quantity == null ) {
      if ( !row.getConfirmedQuantityIsNull() )
        return false;
    } else if ( row.getConfirmedQuantityIsNull() || ( confirmed_quantity.doubleValue() != row.getConfirmedQuantity() ) ) {
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
    if ( quantity_type == null ) {
      if ( row.getQuantityType() != null )
        return false;
    } else if ( !quantity_type.equals( row.getQuantityType() ) ) {
        return false;
    }
    if ( estimated_price == null ) {
      if ( !row.getEstimatedPriceIsNull() )
        return false;
    } else if ( row.getEstimatedPriceIsNull() || ( estimated_price.doubleValue() != row.getEstimatedPrice() ) ) {
        return false;
    }
    if ( modify_cancel_type == null ) {
      if ( row.getModifyCancelType() != null )
        return false;
    } else if ( !modify_cancel_type.equals( row.getModifyCancelType() ) ) {
        return false;
    }
    if ( order_root_div == null ) {
      if ( row.getOrderRootDiv() != null )
        return false;
    } else if ( !order_root_div.equals( row.getOrderRootDiv() ) ) {
        return false;
    }
    if ( closing_order_type == null ) {
      if ( row.getClosingOrderType() != null )
        return false;
    } else if ( !closing_order_type.equals( row.getClosingOrderType() ) ) {
        return false;
    }
    if ( error_reason_code == null ) {
      if ( row.getErrorReasonCode() != null )
        return false;
    } else if ( !error_reason_code.equals( row.getErrorReasonCode() ) ) {
        return false;
    }
    if ( request_type == null ) {
      if ( row.getRequestType() != null )
        return false;
    } else if ( !request_type.equals( row.getRequestType() ) ) {
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
    if ( ip_address == null ) {
      if ( row.getIpAddress() != null )
        return false;
    } else if ( !ip_address.equals( row.getIpAddress() ) ) {
        return false;
    }
    if ( org_order_condition_type == null ) {
      if ( row.getOrgOrderConditionType() != null )
        return false;
    } else if ( !org_order_condition_type.equals( row.getOrgOrderConditionType() ) ) {
        return false;
    }
    if ( org_order_cond_operator == null ) {
      if ( row.getOrgOrderCondOperator() != null )
        return false;
    } else if ( !org_order_cond_operator.equals( row.getOrgOrderCondOperator() ) ) {
        return false;
    }
    if ( org_stop_order_price == null ) {
      if ( !row.getOrgStopOrderPriceIsNull() )
        return false;
    } else if ( row.getOrgStopOrderPriceIsNull() || ( org_stop_order_price.doubleValue() != row.getOrgStopOrderPrice() ) ) {
        return false;
    }
    if ( org_w_limit_price == null ) {
      if ( !row.getOrgWLimitPriceIsNull() )
        return false;
    } else if ( row.getOrgWLimitPriceIsNull() || ( org_w_limit_price.doubleValue() != row.getOrgWLimitPrice() ) ) {
        return false;
    }
    if ( org_w_limit_exec_cond_type == null ) {
      if ( row.getOrgWLimitExecCondType() != null )
        return false;
    } else if ( !org_w_limit_exec_cond_type.equals( row.getOrgWLimitExecCondType() ) ) {
        return false;
    }
    if ( w_limit_exec_cond_type == null ) {
      if ( row.getWLimitExecCondType() != null )
        return false;
    } else if ( !w_limit_exec_cond_type.equals( row.getWLimitExecCondType() ) ) {
        return false;
    }
    if ( w_limit_before_limit_price == null ) {
      if ( !row.getWLimitBeforeLimitPriceIsNull() )
        return false;
    } else if ( row.getWLimitBeforeLimitPriceIsNull() || ( w_limit_before_limit_price.doubleValue() != row.getWLimitBeforeLimitPrice() ) ) {
        return false;
    }
    if ( w_limit_before_exec_cond_type == null ) {
      if ( row.getWLimitBeforeExecCondType() != null )
        return false;
    } else if ( !w_limit_before_exec_cond_type.equals( row.getWLimitBeforeExecCondType() ) ) {
        return false;
    }
    if ( confirmed_exec_condition_type == null ) {
      if ( row.getConfirmedExecConditionType() != null )
        return false;
    } else if ( !confirmed_exec_condition_type.equals( row.getConfirmedExecConditionType() ) ) {
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
        + (order_event_type!=null? order_event_type.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (execution_condition_type!=null? execution_condition_type.hashCode(): 0) 
        + (price_condition_type!=null? price_condition_type.hashCode(): 0) 
        + (order_condition_type!=null? order_condition_type.hashCode(): 0) 
        + (order_cond_operator!=null? order_cond_operator.hashCode(): 0) 
        + (stop_order_price!=null? stop_order_price.hashCode(): 0) 
        + (w_limit_price!=null? w_limit_price.hashCode(): 0) 
        + (expiration_date!=null? expiration_date.hashCode(): 0) 
        + ((int) quantity)
        + (confirmed_price!=null? confirmed_price.hashCode(): 0) 
        + (confirmed_quantity!=null? confirmed_quantity.hashCode(): 0) 
        + (executed_quantity!=null? executed_quantity.hashCode(): 0) 
        + (order_status!=null? order_status.hashCode(): 0) 
        + (expiration_status!=null? expiration_status.hashCode(): 0) 
        + ((int) order_action_serial_no)
        + (executed_price!=null? executed_price.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + ((int) product_id)
        + (quantity_type!=null? quantity_type.hashCode(): 0) 
        + (estimated_price!=null? estimated_price.hashCode(): 0) 
        + (modify_cancel_type!=null? modify_cancel_type.hashCode(): 0) 
        + (order_root_div!=null? order_root_div.hashCode(): 0) 
        + (closing_order_type!=null? closing_order_type.hashCode(): 0) 
        + (error_reason_code!=null? error_reason_code.hashCode(): 0) 
        + (request_type!=null? request_type.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (ip_address!=null? ip_address.hashCode(): 0) 
        + (org_order_condition_type!=null? org_order_condition_type.hashCode(): 0) 
        + (org_order_cond_operator!=null? org_order_cond_operator.hashCode(): 0) 
        + (org_stop_order_price!=null? org_stop_order_price.hashCode(): 0) 
        + (org_w_limit_price!=null? org_w_limit_price.hashCode(): 0) 
        + (org_w_limit_exec_cond_type!=null? org_w_limit_exec_cond_type.hashCode(): 0) 
        + (w_limit_exec_cond_type!=null? w_limit_exec_cond_type.hashCode(): 0) 
        + (w_limit_before_limit_price!=null? w_limit_before_limit_price.hashCode(): 0) 
        + (w_limit_before_exec_cond_type!=null? w_limit_before_exec_cond_type.hashCode(): 0) 
        + (confirmed_exec_condition_type!=null? confirmed_exec_condition_type.hashCode(): 0) 
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
		if ( !order_event_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_event_type' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
		if ( !order_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_status' must be set before inserting.");
		if ( !expiration_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'expiration_status' must be set before inserting.");
		if ( !order_action_serial_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_action_serial_no' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
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
		map.put("order_event_type",order_event_type);
		if ( price != null )
			map.put("price",price);
		if ( execution_condition_type != null )
			map.put("execution_condition_type",execution_condition_type);
		if ( price_condition_type != null )
			map.put("price_condition_type",price_condition_type);
		if ( order_condition_type != null )
			map.put("order_condition_type",order_condition_type);
		if ( order_cond_operator != null )
			map.put("order_cond_operator",order_cond_operator);
		if ( stop_order_price != null )
			map.put("stop_order_price",stop_order_price);
		if ( w_limit_price != null )
			map.put("w_limit_price",w_limit_price);
		if ( expiration_date != null )
			map.put("expiration_date",expiration_date);
		map.put("quantity",new Double(quantity));
		if ( confirmed_price != null )
			map.put("confirmed_price",confirmed_price);
		if ( confirmed_quantity != null )
			map.put("confirmed_quantity",confirmed_quantity);
		if ( executed_quantity != null )
			map.put("executed_quantity",executed_quantity);
		map.put("order_status",order_status);
		map.put("expiration_status",expiration_status);
		map.put("order_action_serial_no",new Integer(order_action_serial_no));
		if ( executed_price != null )
			map.put("executed_price",executed_price);
		map.put("product_type",product_type);
		map.put("product_id",new Long(product_id));
		if ( quantity_type_is_set )
			map.put("quantity_type",quantity_type);
		if ( estimated_price != null )
			map.put("estimated_price",estimated_price);
		if ( modify_cancel_type != null )
			map.put("modify_cancel_type",modify_cancel_type);
		if ( order_root_div != null )
			map.put("order_root_div",order_root_div);
		if ( closing_order_type != null )
			map.put("closing_order_type",closing_order_type);
		if ( error_reason_code != null )
			map.put("error_reason_code",error_reason_code);
		if ( request_type != null )
			map.put("request_type",request_type);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( ip_address != null )
			map.put("ip_address",ip_address);
		if ( org_order_condition_type != null )
			map.put("org_order_condition_type",org_order_condition_type);
		if ( org_order_cond_operator != null )
			map.put("org_order_cond_operator",org_order_cond_operator);
		if ( org_stop_order_price != null )
			map.put("org_stop_order_price",org_stop_order_price);
		if ( org_w_limit_price != null )
			map.put("org_w_limit_price",org_w_limit_price);
		if ( org_w_limit_exec_cond_type != null )
			map.put("org_w_limit_exec_cond_type",org_w_limit_exec_cond_type);
		if ( w_limit_exec_cond_type != null )
			map.put("w_limit_exec_cond_type",w_limit_exec_cond_type);
		if ( w_limit_before_limit_price != null )
			map.put("w_limit_before_limit_price",w_limit_before_limit_price);
		if ( w_limit_before_exec_cond_type != null )
			map.put("w_limit_before_exec_cond_type",w_limit_before_exec_cond_type);
		if ( confirmed_exec_condition_type != null )
			map.put("confirmed_exec_condition_type",confirmed_exec_condition_type);
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
		if ( order_event_type_is_modified )
			map.put("order_event_type",order_event_type);
		if ( price_is_modified )
			map.put("price",price);
		if ( execution_condition_type_is_modified )
			map.put("execution_condition_type",execution_condition_type);
		if ( price_condition_type_is_modified )
			map.put("price_condition_type",price_condition_type);
		if ( order_condition_type_is_modified )
			map.put("order_condition_type",order_condition_type);
		if ( order_cond_operator_is_modified )
			map.put("order_cond_operator",order_cond_operator);
		if ( stop_order_price_is_modified )
			map.put("stop_order_price",stop_order_price);
		if ( w_limit_price_is_modified )
			map.put("w_limit_price",w_limit_price);
		if ( expiration_date_is_modified )
			map.put("expiration_date",expiration_date);
		if ( quantity_is_modified )
			map.put("quantity",new Double(quantity));
		if ( confirmed_price_is_modified )
			map.put("confirmed_price",confirmed_price);
		if ( confirmed_quantity_is_modified )
			map.put("confirmed_quantity",confirmed_quantity);
		if ( executed_quantity_is_modified )
			map.put("executed_quantity",executed_quantity);
		if ( order_status_is_modified )
			map.put("order_status",order_status);
		if ( expiration_status_is_modified )
			map.put("expiration_status",expiration_status);
		if ( order_action_serial_no_is_modified )
			map.put("order_action_serial_no",new Integer(order_action_serial_no));
		if ( executed_price_is_modified )
			map.put("executed_price",executed_price);
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( quantity_type_is_modified )
			map.put("quantity_type",quantity_type);
		if ( estimated_price_is_modified )
			map.put("estimated_price",estimated_price);
		if ( modify_cancel_type_is_modified )
			map.put("modify_cancel_type",modify_cancel_type);
		if ( order_root_div_is_modified )
			map.put("order_root_div",order_root_div);
		if ( closing_order_type_is_modified )
			map.put("closing_order_type",closing_order_type);
		if ( error_reason_code_is_modified )
			map.put("error_reason_code",error_reason_code);
		if ( request_type_is_modified )
			map.put("request_type",request_type);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( ip_address_is_modified )
			map.put("ip_address",ip_address);
		if ( org_order_condition_type_is_modified )
			map.put("org_order_condition_type",org_order_condition_type);
		if ( org_order_cond_operator_is_modified )
			map.put("org_order_cond_operator",org_order_cond_operator);
		if ( org_stop_order_price_is_modified )
			map.put("org_stop_order_price",org_stop_order_price);
		if ( org_w_limit_price_is_modified )
			map.put("org_w_limit_price",org_w_limit_price);
		if ( org_w_limit_exec_cond_type_is_modified )
			map.put("org_w_limit_exec_cond_type",org_w_limit_exec_cond_type);
		if ( w_limit_exec_cond_type_is_modified )
			map.put("w_limit_exec_cond_type",w_limit_exec_cond_type);
		if ( w_limit_before_limit_price_is_modified )
			map.put("w_limit_before_limit_price",w_limit_before_limit_price);
		if ( w_limit_before_exec_cond_type_is_modified )
			map.put("w_limit_before_exec_cond_type",w_limit_before_exec_cond_type);
		if ( confirmed_exec_condition_type_is_modified )
			map.put("confirmed_exec_condition_type",confirmed_exec_condition_type);
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
			if ( order_event_type_is_set )
				map.put("order_event_type",order_event_type);
			map.put("price",price);
			map.put("execution_condition_type",execution_condition_type);
			map.put("price_condition_type",price_condition_type);
			map.put("order_condition_type",order_condition_type);
			map.put("order_cond_operator",order_cond_operator);
			map.put("stop_order_price",stop_order_price);
			map.put("w_limit_price",w_limit_price);
			map.put("expiration_date",expiration_date);
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			map.put("confirmed_price",confirmed_price);
			map.put("confirmed_quantity",confirmed_quantity);
			map.put("executed_quantity",executed_quantity);
			if ( order_status_is_set )
				map.put("order_status",order_status);
			if ( expiration_status_is_set )
				map.put("expiration_status",expiration_status);
			if ( order_action_serial_no_is_set )
				map.put("order_action_serial_no",new Integer(order_action_serial_no));
			map.put("executed_price",executed_price);
			if ( product_type_is_set )
				map.put("product_type",product_type);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( quantity_type_is_set )
				map.put("quantity_type",quantity_type);
			map.put("estimated_price",estimated_price);
			map.put("modify_cancel_type",modify_cancel_type);
			map.put("order_root_div",order_root_div);
			map.put("closing_order_type",closing_order_type);
			map.put("error_reason_code",error_reason_code);
			map.put("request_type",request_type);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("ip_address",ip_address);
			map.put("org_order_condition_type",org_order_condition_type);
			map.put("org_order_cond_operator",org_order_cond_operator);
			map.put("org_stop_order_price",org_stop_order_price);
			map.put("org_w_limit_price",org_w_limit_price);
			map.put("org_w_limit_exec_cond_type",org_w_limit_exec_cond_type);
			map.put("w_limit_exec_cond_type",w_limit_exec_cond_type);
			map.put("w_limit_before_limit_price",w_limit_before_limit_price);
			map.put("w_limit_before_exec_cond_type",w_limit_before_exec_cond_type);
			map.put("confirmed_exec_condition_type",confirmed_exec_condition_type);
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
   * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionTypeの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType getExecutionConditionType()
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
   * <em>price_condition_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPriceConditionType()
  {
    return price_condition_type;
  }


  /** 
   * <em>price_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceConditionTypeIsSet() {
    return price_condition_type_is_set;
  }


  /** 
   * <em>price_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceConditionTypeIsModified() {
    return price_condition_type_is_modified;
  }


  /** 
   * <em>order_condition_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderConditionType()
  {
    return order_condition_type;
  }


  /** 
   * <em>order_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderConditionTypeIsSet() {
    return order_condition_type_is_set;
  }


  /** 
   * <em>order_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderConditionTypeIsModified() {
    return order_condition_type_is_modified;
  }


  /** 
   * <em>order_cond_operator</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderCondOperator()
  {
    return order_cond_operator;
  }


  /** 
   * <em>order_cond_operator</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCondOperatorIsSet() {
    return order_cond_operator_is_set;
  }


  /** 
   * <em>order_cond_operator</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCondOperatorIsModified() {
    return order_cond_operator_is_modified;
  }


  /** 
   * <em>stop_order_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getStopOrderPrice()
  {
    return ( stop_order_price==null? ((double)0): stop_order_price.doubleValue() );
  }


  /** 
   * <em>stop_order_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getStopOrderPriceIsNull()
  {
    return stop_order_price == null;
  }


  /** 
   * <em>stop_order_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopOrderPriceIsSet() {
    return stop_order_price_is_set;
  }


  /** 
   * <em>stop_order_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopOrderPriceIsModified() {
    return stop_order_price_is_modified;
  }


  /** 
   * <em>w_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getWLimitPrice()
  {
    return ( w_limit_price==null? ((double)0): w_limit_price.doubleValue() );
  }


  /** 
   * <em>w_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getWLimitPriceIsNull()
  {
    return w_limit_price == null;
  }


  /** 
   * <em>w_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWLimitPriceIsSet() {
    return w_limit_price_is_set;
  }


  /** 
   * <em>w_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWLimitPriceIsModified() {
    return w_limit_price_is_modified;
  }


  /** 
   * <em>expiration_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getExpirationDate()
  {
    return expiration_date;
  }


  /** 
   * <em>expiration_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExpirationDateIsSet() {
    return expiration_date_is_set;
  }


  /** 
   * <em>expiration_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExpirationDateIsModified() {
    return expiration_date_is_modified;
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
   * <em>quantity_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum getQuantityType()
  {
    return quantity_type;
  }


  /** 
   * <em>quantity_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuantityTypeIsSet() {
    return quantity_type_is_set;
  }


  /** 
   * <em>quantity_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuantityTypeIsModified() {
    return quantity_type_is_modified;
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
   * <em>modify_cancel_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getModifyCancelType()
  {
    return modify_cancel_type;
  }


  /** 
   * <em>modify_cancel_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifyCancelTypeIsSet() {
    return modify_cancel_type_is_set;
  }


  /** 
   * <em>modify_cancel_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifyCancelTypeIsModified() {
    return modify_cancel_type_is_modified;
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
   * <em>closing_order_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getClosingOrderType()
  {
    return closing_order_type;
  }


  /** 
   * <em>closing_order_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClosingOrderTypeIsSet() {
    return closing_order_type_is_set;
  }


  /** 
   * <em>closing_order_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClosingOrderTypeIsModified() {
    return closing_order_type_is_modified;
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
   * <em>request_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRequestType()
  {
    return request_type;
  }


  /** 
   * <em>request_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestTypeIsSet() {
    return request_type_is_set;
  }


  /** 
   * <em>request_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestTypeIsModified() {
    return request_type_is_modified;
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
   * <em>ip_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIpAddress()
  {
    return ip_address;
  }


  /** 
   * <em>ip_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIpAddressIsSet() {
    return ip_address_is_set;
  }


  /** 
   * <em>ip_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIpAddressIsModified() {
    return ip_address_is_modified;
  }


  /** 
   * <em>org_order_condition_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrgOrderConditionType()
  {
    return org_order_condition_type;
  }


  /** 
   * <em>org_order_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgOrderConditionTypeIsSet() {
    return org_order_condition_type_is_set;
  }


  /** 
   * <em>org_order_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgOrderConditionTypeIsModified() {
    return org_order_condition_type_is_modified;
  }


  /** 
   * <em>org_order_cond_operator</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrgOrderCondOperator()
  {
    return org_order_cond_operator;
  }


  /** 
   * <em>org_order_cond_operator</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgOrderCondOperatorIsSet() {
    return org_order_cond_operator_is_set;
  }


  /** 
   * <em>org_order_cond_operator</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgOrderCondOperatorIsModified() {
    return org_order_cond_operator_is_modified;
  }


  /** 
   * <em>org_stop_order_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOrgStopOrderPrice()
  {
    return ( org_stop_order_price==null? ((double)0): org_stop_order_price.doubleValue() );
  }


  /** 
   * <em>org_stop_order_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrgStopOrderPriceIsNull()
  {
    return org_stop_order_price == null;
  }


  /** 
   * <em>org_stop_order_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgStopOrderPriceIsSet() {
    return org_stop_order_price_is_set;
  }


  /** 
   * <em>org_stop_order_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgStopOrderPriceIsModified() {
    return org_stop_order_price_is_modified;
  }


  /** 
   * <em>org_w_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOrgWLimitPrice()
  {
    return ( org_w_limit_price==null? ((double)0): org_w_limit_price.doubleValue() );
  }


  /** 
   * <em>org_w_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrgWLimitPriceIsNull()
  {
    return org_w_limit_price == null;
  }


  /** 
   * <em>org_w_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgWLimitPriceIsSet() {
    return org_w_limit_price_is_set;
  }


  /** 
   * <em>org_w_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgWLimitPriceIsModified() {
    return org_w_limit_price_is_modified;
  }


  /** 
   * <em>org_w_limit_exec_cond_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionTypeの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType getOrgWLimitExecCondType()
  {
    return org_w_limit_exec_cond_type;
  }


  /** 
   * <em>org_w_limit_exec_cond_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgWLimitExecCondTypeIsSet() {
    return org_w_limit_exec_cond_type_is_set;
  }


  /** 
   * <em>org_w_limit_exec_cond_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgWLimitExecCondTypeIsModified() {
    return org_w_limit_exec_cond_type_is_modified;
  }


  /** 
   * <em>w_limit_exec_cond_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionTypeの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType getWLimitExecCondType()
  {
    return w_limit_exec_cond_type;
  }


  /** 
   * <em>w_limit_exec_cond_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWLimitExecCondTypeIsSet() {
    return w_limit_exec_cond_type_is_set;
  }


  /** 
   * <em>w_limit_exec_cond_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWLimitExecCondTypeIsModified() {
    return w_limit_exec_cond_type_is_modified;
  }


  /** 
   * <em>w_limit_before_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getWLimitBeforeLimitPrice()
  {
    return ( w_limit_before_limit_price==null? ((double)0): w_limit_before_limit_price.doubleValue() );
  }


  /** 
   * <em>w_limit_before_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getWLimitBeforeLimitPriceIsNull()
  {
    return w_limit_before_limit_price == null;
  }


  /** 
   * <em>w_limit_before_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWLimitBeforeLimitPriceIsSet() {
    return w_limit_before_limit_price_is_set;
  }


  /** 
   * <em>w_limit_before_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWLimitBeforeLimitPriceIsModified() {
    return w_limit_before_limit_price_is_modified;
  }


  /** 
   * <em>w_limit_before_exec_cond_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionTypeの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType getWLimitBeforeExecCondType()
  {
    return w_limit_before_exec_cond_type;
  }


  /** 
   * <em>w_limit_before_exec_cond_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWLimitBeforeExecCondTypeIsSet() {
    return w_limit_before_exec_cond_type_is_set;
  }


  /** 
   * <em>w_limit_before_exec_cond_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWLimitBeforeExecCondTypeIsModified() {
    return w_limit_before_exec_cond_type_is_modified;
  }


  /** 
   * <em>confirmed_exec_condition_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionTypeの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType getConfirmedExecConditionType()
  {
    return confirmed_exec_condition_type;
  }


  /** 
   * <em>confirmed_exec_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedExecConditionTypeIsSet() {
    return confirmed_exec_condition_type_is_set;
  }


  /** 
   * <em>confirmed_exec_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedExecConditionTypeIsModified() {
    return confirmed_exec_condition_type_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new EqtypeOrderActionPK(order_action_id);
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
   * @@param p_executionConditionType <em>execution_condition_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType値 
   */
  public final void setExecutionConditionType( com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType p_executionConditionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    execution_condition_type = p_executionConditionType;
    execution_condition_type_is_set = true;
    execution_condition_type_is_modified = true;
  }


  /** 
   * <em>price_condition_type</em>カラムの値を設定します。 
   *
   * @@param p_priceConditionType <em>price_condition_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPriceConditionType( String p_priceConditionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price_condition_type = p_priceConditionType;
    price_condition_type_is_set = true;
    price_condition_type_is_modified = true;
  }


  /** 
   * <em>order_condition_type</em>カラムの値を設定します。 
   *
   * @@param p_orderConditionType <em>order_condition_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderConditionType( String p_orderConditionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_condition_type = p_orderConditionType;
    order_condition_type_is_set = true;
    order_condition_type_is_modified = true;
  }


  /** 
   * <em>order_cond_operator</em>カラムの値を設定します。 
   *
   * @@param p_orderCondOperator <em>order_cond_operator</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderCondOperator( String p_orderCondOperator )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_cond_operator = p_orderCondOperator;
    order_cond_operator_is_set = true;
    order_cond_operator_is_modified = true;
  }


  /** 
   * <em>stop_order_price</em>カラムの値を設定します。 
   *
   * @@param p_stopOrderPrice <em>stop_order_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setStopOrderPrice( double p_stopOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_order_price = new Double(p_stopOrderPrice);
    stop_order_price_is_set = true;
    stop_order_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>stop_order_price</em>カラムに値を設定します。 
   */
  public final void setStopOrderPrice( Double p_stopOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    stop_order_price = p_stopOrderPrice;
    stop_order_price_is_set = true;
    stop_order_price_is_modified = true;
  }


  /** 
   * <em>w_limit_price</em>カラムの値を設定します。 
   *
   * @@param p_wLimitPrice <em>w_limit_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setWLimitPrice( double p_wLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    w_limit_price = new Double(p_wLimitPrice);
    w_limit_price_is_set = true;
    w_limit_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>w_limit_price</em>カラムに値を設定します。 
   */
  public final void setWLimitPrice( Double p_wLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    w_limit_price = p_wLimitPrice;
    w_limit_price_is_set = true;
    w_limit_price_is_modified = true;
  }


  /** 
   * <em>expiration_date</em>カラムの値を設定します。 
   *
   * @@param p_expirationDate <em>expiration_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setExpirationDate( java.sql.Timestamp p_expirationDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    expiration_date = p_expirationDate;
    expiration_date_is_set = true;
    expiration_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>expiration_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setExpirationDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    expiration_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    expiration_date_is_set = true;
    expiration_date_is_modified = true;
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
   * <em>quantity_type</em>カラムの値を設定します。 
   *
   * @@param p_quantityType <em>quantity_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum値 
   */
  public final void setQuantityType( com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum p_quantityType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity_type = p_quantityType;
    quantity_type_is_set = true;
    quantity_type_is_modified = true;
  }


  /** 
   * <em>estimated_price</em>カラムの値を設定します。 
   *
   * @@param p_estimatedPrice <em>estimated_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
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
   * <em>modify_cancel_type</em>カラムの値を設定します。 
   *
   * @@param p_modifyCancelType <em>modify_cancel_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setModifyCancelType( String p_modifyCancelType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modify_cancel_type = p_modifyCancelType;
    modify_cancel_type_is_set = true;
    modify_cancel_type_is_modified = true;
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
   * <em>closing_order_type</em>カラムの値を設定します。 
   *
   * @@param p_closingOrderType <em>closing_order_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setClosingOrderType( String p_closingOrderType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    closing_order_type = p_closingOrderType;
    closing_order_type_is_set = true;
    closing_order_type_is_modified = true;
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
   * <em>request_type</em>カラムの値を設定します。 
   *
   * @@param p_requestType <em>request_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRequestType( String p_requestType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_type = p_requestType;
    request_type_is_set = true;
    request_type_is_modified = true;
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
   * <em>ip_address</em>カラムの値を設定します。 
   *
   * @@param p_ipAddress <em>ip_address</em>カラムの値をあらわす15桁以下のString値 
   */
  public final void setIpAddress( String p_ipAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ip_address = p_ipAddress;
    ip_address_is_set = true;
    ip_address_is_modified = true;
  }


  /** 
   * <em>org_order_condition_type</em>カラムの値を設定します。 
   *
   * @@param p_orgOrderConditionType <em>org_order_condition_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrgOrderConditionType( String p_orgOrderConditionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    org_order_condition_type = p_orgOrderConditionType;
    org_order_condition_type_is_set = true;
    org_order_condition_type_is_modified = true;
  }


  /** 
   * <em>org_order_cond_operator</em>カラムの値を設定します。 
   *
   * @@param p_orgOrderCondOperator <em>org_order_cond_operator</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrgOrderCondOperator( String p_orgOrderCondOperator )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    org_order_cond_operator = p_orgOrderCondOperator;
    org_order_cond_operator_is_set = true;
    org_order_cond_operator_is_modified = true;
  }


  /** 
   * <em>org_stop_order_price</em>カラムの値を設定します。 
   *
   * @@param p_orgStopOrderPrice <em>org_stop_order_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOrgStopOrderPrice( double p_orgStopOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    org_stop_order_price = new Double(p_orgStopOrderPrice);
    org_stop_order_price_is_set = true;
    org_stop_order_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>org_stop_order_price</em>カラムに値を設定します。 
   */
  public final void setOrgStopOrderPrice( Double p_orgStopOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    org_stop_order_price = p_orgStopOrderPrice;
    org_stop_order_price_is_set = true;
    org_stop_order_price_is_modified = true;
  }


  /** 
   * <em>org_w_limit_price</em>カラムの値を設定します。 
   *
   * @@param p_orgWLimitPrice <em>org_w_limit_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOrgWLimitPrice( double p_orgWLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    org_w_limit_price = new Double(p_orgWLimitPrice);
    org_w_limit_price_is_set = true;
    org_w_limit_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>org_w_limit_price</em>カラムに値を設定します。 
   */
  public final void setOrgWLimitPrice( Double p_orgWLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    org_w_limit_price = p_orgWLimitPrice;
    org_w_limit_price_is_set = true;
    org_w_limit_price_is_modified = true;
  }


  /** 
   * <em>org_w_limit_exec_cond_type</em>カラムの値を設定します。 
   *
   * @@param p_orgWLimitExecCondType <em>org_w_limit_exec_cond_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType値 
   */
  public final void setOrgWLimitExecCondType( com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType p_orgWLimitExecCondType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    org_w_limit_exec_cond_type = p_orgWLimitExecCondType;
    org_w_limit_exec_cond_type_is_set = true;
    org_w_limit_exec_cond_type_is_modified = true;
  }


  /** 
   * <em>w_limit_exec_cond_type</em>カラムの値を設定します。 
   *
   * @@param p_wLimitExecCondType <em>w_limit_exec_cond_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType値 
   */
  public final void setWLimitExecCondType( com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType p_wLimitExecCondType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    w_limit_exec_cond_type = p_wLimitExecCondType;
    w_limit_exec_cond_type_is_set = true;
    w_limit_exec_cond_type_is_modified = true;
  }


  /** 
   * <em>w_limit_before_limit_price</em>カラムの値を設定します。 
   *
   * @@param p_wLimitBeforeLimitPrice <em>w_limit_before_limit_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setWLimitBeforeLimitPrice( double p_wLimitBeforeLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    w_limit_before_limit_price = new Double(p_wLimitBeforeLimitPrice);
    w_limit_before_limit_price_is_set = true;
    w_limit_before_limit_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>w_limit_before_limit_price</em>カラムに値を設定します。 
   */
  public final void setWLimitBeforeLimitPrice( Double p_wLimitBeforeLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    w_limit_before_limit_price = p_wLimitBeforeLimitPrice;
    w_limit_before_limit_price_is_set = true;
    w_limit_before_limit_price_is_modified = true;
  }


  /** 
   * <em>w_limit_before_exec_cond_type</em>カラムの値を設定します。 
   *
   * @@param p_wLimitBeforeExecCondType <em>w_limit_before_exec_cond_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType値 
   */
  public final void setWLimitBeforeExecCondType( com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType p_wLimitBeforeExecCondType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    w_limit_before_exec_cond_type = p_wLimitBeforeExecCondType;
    w_limit_before_exec_cond_type_is_set = true;
    w_limit_before_exec_cond_type_is_modified = true;
  }


  /** 
   * <em>confirmed_exec_condition_type</em>カラムの値を設定します。 
   *
   * @@param p_confirmedExecConditionType <em>confirmed_exec_condition_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType値 
   */
  public final void setConfirmedExecConditionType( com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType p_confirmedExecConditionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_exec_condition_type = p_confirmedExecConditionType;
    confirmed_exec_condition_type_is_set = true;
    confirmed_exec_condition_type_is_modified = true;
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
                break;
            case 'c':
                if ( name.equals("confirmed_price") ) {
                    return this.confirmed_price;
                }
                else if ( name.equals("confirmed_quantity") ) {
                    return this.confirmed_quantity;
                }
                else if ( name.equals("closing_order_type") ) {
                    return this.closing_order_type;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("confirmed_exec_condition_type") ) {
                    return this.confirmed_exec_condition_type;
                }
                break;
            case 'e':
                if ( name.equals("execution_condition_type") ) {
                    return this.execution_condition_type;
                }
                else if ( name.equals("expiration_date") ) {
                    return this.expiration_date;
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
                else if ( name.equals("estimated_price") ) {
                    return this.estimated_price;
                }
                else if ( name.equals("error_reason_code") ) {
                    return this.error_reason_code;
                }
                break;
            case 'i':
                if ( name.equals("ip_address") ) {
                    return this.ip_address;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return this.market_id;
                }
                else if ( name.equals("modify_cancel_type") ) {
                    return this.modify_cancel_type;
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
                else if ( name.equals("order_condition_type") ) {
                    return this.order_condition_type;
                }
                else if ( name.equals("order_cond_operator") ) {
                    return this.order_cond_operator;
                }
                else if ( name.equals("order_status") ) {
                    return this.order_status;
                }
                else if ( name.equals("order_action_serial_no") ) {
                    return new Integer( order_action_serial_no );
                }
                else if ( name.equals("order_root_div") ) {
                    return this.order_root_div;
                }
                else if ( name.equals("org_order_condition_type") ) {
                    return this.org_order_condition_type;
                }
                else if ( name.equals("org_order_cond_operator") ) {
                    return this.org_order_cond_operator;
                }
                else if ( name.equals("org_stop_order_price") ) {
                    return this.org_stop_order_price;
                }
                else if ( name.equals("org_w_limit_price") ) {
                    return this.org_w_limit_price;
                }
                else if ( name.equals("org_w_limit_exec_cond_type") ) {
                    return this.org_w_limit_exec_cond_type;
                }
                break;
            case 'p':
                if ( name.equals("price") ) {
                    return this.price;
                }
                else if ( name.equals("price_condition_type") ) {
                    return this.price_condition_type;
                }
                else if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return new Double( quantity );
                }
                else if ( name.equals("quantity_type") ) {
                    return this.quantity_type;
                }
                break;
            case 'r':
                if ( name.equals("request_type") ) {
                    return this.request_type;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("stop_order_price") ) {
                    return this.stop_order_price;
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    return this.trader_id;
                }
                break;
            case 'w':
                if ( name.equals("w_limit_price") ) {
                    return this.w_limit_price;
                }
                else if ( name.equals("w_limit_exec_cond_type") ) {
                    return this.w_limit_exec_cond_type;
                }
                else if ( name.equals("w_limit_before_limit_price") ) {
                    return this.w_limit_before_limit_price;
                }
                else if ( name.equals("w_limit_before_exec_cond_type") ) {
                    return this.w_limit_before_exec_cond_type;
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
                break;
            case 'c':
                if ( name.equals("confirmed_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'confirmed_price' must be Double: '"+value+"' is not." );
                    this.confirmed_price = (Double) value;
                    if (this.confirmed_price_is_set)
                        this.confirmed_price_is_modified = true;
                    this.confirmed_price_is_set = true;
                    return;
                }
                else if ( name.equals("confirmed_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'confirmed_quantity' must be Double: '"+value+"' is not." );
                    this.confirmed_quantity = (Double) value;
                    if (this.confirmed_quantity_is_set)
                        this.confirmed_quantity_is_modified = true;
                    this.confirmed_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("closing_order_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'closing_order_type' must be String: '"+value+"' is not." );
                    this.closing_order_type = (String) value;
                    if (this.closing_order_type_is_set)
                        this.closing_order_type_is_modified = true;
                    this.closing_order_type_is_set = true;
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
                else if ( name.equals("confirmed_exec_condition_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType) )
                        throw new IllegalArgumentException( "value for 'confirmed_exec_condition_type' must be com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType: '"+value+"' is not." );
                    this.confirmed_exec_condition_type = (com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType) value;
                    if (this.confirmed_exec_condition_type_is_set)
                        this.confirmed_exec_condition_type_is_modified = true;
                    this.confirmed_exec_condition_type_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("execution_condition_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType) )
                        throw new IllegalArgumentException( "value for 'execution_condition_type' must be com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType: '"+value+"' is not." );
                    this.execution_condition_type = (com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType) value;
                    if (this.execution_condition_type_is_set)
                        this.execution_condition_type_is_modified = true;
                    this.execution_condition_type_is_set = true;
                    return;
                }
                else if ( name.equals("expiration_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'expiration_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.expiration_date = (java.sql.Timestamp) value;
                    if (this.expiration_date_is_set)
                        this.expiration_date_is_modified = true;
                    this.expiration_date_is_set = true;
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
            case 'i':
                if ( name.equals("ip_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ip_address' must be String: '"+value+"' is not." );
                    this.ip_address = (String) value;
                    if (this.ip_address_is_set)
                        this.ip_address_is_modified = true;
                    this.ip_address_is_set = true;
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
                else if ( name.equals("modify_cancel_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'modify_cancel_type' must be String: '"+value+"' is not." );
                    this.modify_cancel_type = (String) value;
                    if (this.modify_cancel_type_is_set)
                        this.modify_cancel_type_is_modified = true;
                    this.modify_cancel_type_is_set = true;
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
                else if ( name.equals("order_condition_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_condition_type' must be String: '"+value+"' is not." );
                    this.order_condition_type = (String) value;
                    if (this.order_condition_type_is_set)
                        this.order_condition_type_is_modified = true;
                    this.order_condition_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_cond_operator") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_cond_operator' must be String: '"+value+"' is not." );
                    this.order_cond_operator = (String) value;
                    if (this.order_cond_operator_is_set)
                        this.order_cond_operator_is_modified = true;
                    this.order_cond_operator_is_set = true;
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
                else if ( name.equals("org_order_condition_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'org_order_condition_type' must be String: '"+value+"' is not." );
                    this.org_order_condition_type = (String) value;
                    if (this.org_order_condition_type_is_set)
                        this.org_order_condition_type_is_modified = true;
                    this.org_order_condition_type_is_set = true;
                    return;
                }
                else if ( name.equals("org_order_cond_operator") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'org_order_cond_operator' must be String: '"+value+"' is not." );
                    this.org_order_cond_operator = (String) value;
                    if (this.org_order_cond_operator_is_set)
                        this.org_order_cond_operator_is_modified = true;
                    this.org_order_cond_operator_is_set = true;
                    return;
                }
                else if ( name.equals("org_stop_order_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'org_stop_order_price' must be Double: '"+value+"' is not." );
                    this.org_stop_order_price = (Double) value;
                    if (this.org_stop_order_price_is_set)
                        this.org_stop_order_price_is_modified = true;
                    this.org_stop_order_price_is_set = true;
                    return;
                }
                else if ( name.equals("org_w_limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'org_w_limit_price' must be Double: '"+value+"' is not." );
                    this.org_w_limit_price = (Double) value;
                    if (this.org_w_limit_price_is_set)
                        this.org_w_limit_price_is_modified = true;
                    this.org_w_limit_price_is_set = true;
                    return;
                }
                else if ( name.equals("org_w_limit_exec_cond_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType) )
                        throw new IllegalArgumentException( "value for 'org_w_limit_exec_cond_type' must be com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType: '"+value+"' is not." );
                    this.org_w_limit_exec_cond_type = (com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType) value;
                    if (this.org_w_limit_exec_cond_type_is_set)
                        this.org_w_limit_exec_cond_type_is_modified = true;
                    this.org_w_limit_exec_cond_type_is_set = true;
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
                else if ( name.equals("price_condition_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'price_condition_type' must be String: '"+value+"' is not." );
                    this.price_condition_type = (String) value;
                    if (this.price_condition_type_is_set)
                        this.price_condition_type_is_modified = true;
                    this.price_condition_type_is_set = true;
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
                else if ( name.equals("quantity_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum) )
                        throw new IllegalArgumentException( "value for 'quantity_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum: '"+value+"' is not." );
                    this.quantity_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum) value;
                    if (this.quantity_type_is_set)
                        this.quantity_type_is_modified = true;
                    this.quantity_type_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_type' must be String: '"+value+"' is not." );
                    this.request_type = (String) value;
                    if (this.request_type_is_set)
                        this.request_type_is_modified = true;
                    this.request_type_is_set = true;
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
                else if ( name.equals("stop_order_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'stop_order_price' must be Double: '"+value+"' is not." );
                    this.stop_order_price = (Double) value;
                    if (this.stop_order_price_is_set)
                        this.stop_order_price_is_modified = true;
                    this.stop_order_price_is_set = true;
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
                break;
            case 'w':
                if ( name.equals("w_limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'w_limit_price' must be Double: '"+value+"' is not." );
                    this.w_limit_price = (Double) value;
                    if (this.w_limit_price_is_set)
                        this.w_limit_price_is_modified = true;
                    this.w_limit_price_is_set = true;
                    return;
                }
                else if ( name.equals("w_limit_exec_cond_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType) )
                        throw new IllegalArgumentException( "value for 'w_limit_exec_cond_type' must be com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType: '"+value+"' is not." );
                    this.w_limit_exec_cond_type = (com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType) value;
                    if (this.w_limit_exec_cond_type_is_set)
                        this.w_limit_exec_cond_type_is_modified = true;
                    this.w_limit_exec_cond_type_is_set = true;
                    return;
                }
                else if ( name.equals("w_limit_before_limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'w_limit_before_limit_price' must be Double: '"+value+"' is not." );
                    this.w_limit_before_limit_price = (Double) value;
                    if (this.w_limit_before_limit_price_is_set)
                        this.w_limit_before_limit_price_is_modified = true;
                    this.w_limit_before_limit_price_is_set = true;
                    return;
                }
                else if ( name.equals("w_limit_before_exec_cond_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType) )
                        throw new IllegalArgumentException( "value for 'w_limit_before_exec_cond_type' must be com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType: '"+value+"' is not." );
                    this.w_limit_before_exec_cond_type = (com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType) value;
                    if (this.w_limit_before_exec_cond_type_is_set)
                        this.w_limit_before_exec_cond_type_is_modified = true;
                    this.w_limit_before_exec_cond_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
