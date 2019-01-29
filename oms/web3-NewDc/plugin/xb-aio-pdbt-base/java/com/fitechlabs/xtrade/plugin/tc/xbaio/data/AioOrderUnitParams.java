head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.56.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88640f7e4e;
filename	AioOrderUnitParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbaio.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * aio_order_unitテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AioOrderUnitRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AioOrderUnitParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AioOrderUnitParams}が{@@link AioOrderUnitRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AioOrderUnitPK 
 * @@see AioOrderUnitRow 
 */
public class AioOrderUnitParams extends Params implements AioOrderUnitRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "aio_order_unit";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AioOrderUnitRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AioOrderUnitRow.TYPE;
  }


  /** 
   * <em>order_unit_id</em>カラムの値 
   */
  public  long  order_unit_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>カラムの値 
   */
  public  long  sub_account_id;

  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>trader_id</em>カラムの値 
   */
  public  Long  trader_id;

  /** 
   * <em>order_id</em>カラムの値 
   */
  public  long  order_id;

  /** 
   * <em>order_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum  order_type;

  /** 
   * <em>order_categ</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum  order_categ;

  /** 
   * <em>last_order_action_serial_no</em>カラムの値 
   */
  public  int  last_order_action_serial_no;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  double  quantity;

  /** 
   * <em>order_status</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum  order_status;

  /** 
   * <em>order_open_status</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum  order_open_status;

  /** 
   * <em>expiration_status</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum  expiration_status;

  /** 
   * <em>biz_date</em>カラムの値 
   */
  public  String  biz_date;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>tax_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum  tax_type;

  /** 
   * <em>mini_stock_div</em>カラムの値 
   */
  public  String  mini_stock_div;

  /** 
   * <em>received_date_time</em>カラムの値 
   */
  public  java.sql.Timestamp  received_date_time;

  /** 
   * <em>sonar_trader_code</em>カラムの値 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>com_debit_number</em>カラムの値 
   */
  public  String  com_debit_number;

  /** 
   * <em>guarantee_div</em>カラムの値 
   */
  public  String  guarantee_div;

  /** 
   * <em>payment_application_div</em>カラムの値 
   */
  public  String  payment_application_div;

  /** 
   * <em>cancel_type</em>カラムの値 
   */
  public  String  cancel_type;

  /** 
   * <em>order_root_div</em>カラムの値 
   */
  public  String  order_root_div;

  /** 
   * <em>error_reason_code</em>カラムの値 
   */
  public  String  error_reason_code;

  /** 
   * <em>mq_status</em>カラムの値 
   */
  public  String  mq_status;

  /** 
   * <em>description</em>カラムの値 
   */
  public  String  description;

  /** 
   * <em>est_transfer_date</em>カラムの値 
   */
  public  java.sql.Timestamp  est_transfer_date;

  /** 
   * <em>transfer_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum  transfer_type;

  /** 
   * <em>transfer_done_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  transfer_done_timestamp;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>currency_code</em>カラムの値 
   */
  public  String  currency_code;

  /** 
   * <em>convert_amount</em>カラムの値 
   */
  public  Double  convert_amount;

  /** 
   * <em>remark_code</em>カラムの値 
   */
  public  String  remark_code;

  /** 
   * <em>remark_name</em>カラムの値 
   */
  public  String  remark_name;

  private boolean order_unit_id_is_set = false;
  private boolean order_unit_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean trader_id_is_set = false;
  private boolean trader_id_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean order_type_is_set = false;
  private boolean order_type_is_modified = false;
  private boolean order_categ_is_set = false;
  private boolean order_categ_is_modified = false;
  private boolean last_order_action_serial_no_is_set = false;
  private boolean last_order_action_serial_no_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean order_status_is_set = false;
  private boolean order_status_is_modified = false;
  private boolean order_open_status_is_set = false;
  private boolean order_open_status_is_modified = false;
  private boolean expiration_status_is_set = false;
  private boolean expiration_status_is_modified = false;
  private boolean biz_date_is_set = false;
  private boolean biz_date_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean mini_stock_div_is_set = false;
  private boolean mini_stock_div_is_modified = false;
  private boolean received_date_time_is_set = false;
  private boolean received_date_time_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean com_debit_number_is_set = false;
  private boolean com_debit_number_is_modified = false;
  private boolean guarantee_div_is_set = false;
  private boolean guarantee_div_is_modified = false;
  private boolean payment_application_div_is_set = false;
  private boolean payment_application_div_is_modified = false;
  private boolean cancel_type_is_set = false;
  private boolean cancel_type_is_modified = false;
  private boolean order_root_div_is_set = false;
  private boolean order_root_div_is_modified = false;
  private boolean error_reason_code_is_set = false;
  private boolean error_reason_code_is_modified = false;
  private boolean mq_status_is_set = false;
  private boolean mq_status_is_modified = false;
  private boolean description_is_set = false;
  private boolean description_is_modified = false;
  private boolean est_transfer_date_is_set = false;
  private boolean est_transfer_date_is_modified = false;
  private boolean transfer_type_is_set = false;
  private boolean transfer_type_is_modified = false;
  private boolean transfer_done_timestamp_is_set = false;
  private boolean transfer_done_timestamp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean convert_amount_is_set = false;
  private boolean convert_amount_is_modified = false;
  private boolean remark_code_is_set = false;
  private boolean remark_code_is_modified = false;
  private boolean remark_name_is_set = false;
  private boolean remark_name_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "order_unit_id=" + order_unit_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "branch_id=" +branch_id
      + "," + "trader_id=" +trader_id
      + "," + "order_id=" +order_id
      + "," + "order_type=" +order_type
      + "," + "order_categ=" +order_categ
      + "," + "last_order_action_serial_no=" +last_order_action_serial_no
      + "," + "product_type=" +product_type
      + "," + "quantity=" +quantity
      + "," + "order_status=" +order_status
      + "," + "order_open_status=" +order_open_status
      + "," + "expiration_status=" +expiration_status
      + "," + "biz_date=" +biz_date
      + "," + "delivery_date=" +delivery_date
      + "," + "product_id=" +product_id
      + "," + "tax_type=" +tax_type
      + "," + "mini_stock_div=" +mini_stock_div
      + "," + "received_date_time=" +received_date_time
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "order_request_number=" +order_request_number
      + "," + "com_debit_number=" +com_debit_number
      + "," + "guarantee_div=" +guarantee_div
      + "," + "payment_application_div=" +payment_application_div
      + "," + "cancel_type=" +cancel_type
      + "," + "order_root_div=" +order_root_div
      + "," + "error_reason_code=" +error_reason_code
      + "," + "mq_status=" +mq_status
      + "," + "description=" +description
      + "," + "est_transfer_date=" +est_transfer_date
      + "," + "transfer_type=" +transfer_type
      + "," + "transfer_done_timestamp=" +transfer_done_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "currency_code=" +currency_code
      + "," + "convert_amount=" +convert_amount
      + "," + "remark_code=" +remark_code
      + "," + "remark_name=" +remark_name
      + "}";
  }


  /** 
   * 値が未設定のAioOrderUnitParamsオブジェクトを作成します。 
   */
  public AioOrderUnitParams() {
    order_unit_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAioOrderUnitRowオブジェクトの値を利用してAioOrderUnitParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAioOrderUnitRowオブジェクト 
   */
  public AioOrderUnitParams( AioOrderUnitRow p_row )
  {
    order_unit_id = p_row.getOrderUnitId();
    order_unit_id_is_set = p_row.getOrderUnitIdIsSet();
    order_unit_id_is_modified = p_row.getOrderUnitIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    if ( !p_row.getTraderIdIsNull() )
      trader_id = new Long( p_row.getTraderId() );
    trader_id_is_set = p_row.getTraderIdIsSet();
    trader_id_is_modified = p_row.getTraderIdIsModified();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    order_type = p_row.getOrderType();
    order_type_is_set = p_row.getOrderTypeIsSet();
    order_type_is_modified = p_row.getOrderTypeIsModified();
    order_categ = p_row.getOrderCateg();
    order_categ_is_set = p_row.getOrderCategIsSet();
    order_categ_is_modified = p_row.getOrderCategIsModified();
    last_order_action_serial_no = p_row.getLastOrderActionSerialNo();
    last_order_action_serial_no_is_set = p_row.getLastOrderActionSerialNoIsSet();
    last_order_action_serial_no_is_modified = p_row.getLastOrderActionSerialNoIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    quantity = p_row.getQuantity();
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    order_status = p_row.getOrderStatus();
    order_status_is_set = p_row.getOrderStatusIsSet();
    order_status_is_modified = p_row.getOrderStatusIsModified();
    order_open_status = p_row.getOrderOpenStatus();
    order_open_status_is_set = p_row.getOrderOpenStatusIsSet();
    order_open_status_is_modified = p_row.getOrderOpenStatusIsModified();
    expiration_status = p_row.getExpirationStatus();
    expiration_status_is_set = p_row.getExpirationStatusIsSet();
    expiration_status_is_modified = p_row.getExpirationStatusIsModified();
    biz_date = p_row.getBizDate();
    biz_date_is_set = p_row.getBizDateIsSet();
    biz_date_is_modified = p_row.getBizDateIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    mini_stock_div = p_row.getMiniStockDiv();
    mini_stock_div_is_set = p_row.getMiniStockDivIsSet();
    mini_stock_div_is_modified = p_row.getMiniStockDivIsModified();
    received_date_time = p_row.getReceivedDateTime();
    received_date_time_is_set = p_row.getReceivedDateTimeIsSet();
    received_date_time_is_modified = p_row.getReceivedDateTimeIsModified();
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    com_debit_number = p_row.getComDebitNumber();
    com_debit_number_is_set = p_row.getComDebitNumberIsSet();
    com_debit_number_is_modified = p_row.getComDebitNumberIsModified();
    guarantee_div = p_row.getGuaranteeDiv();
    guarantee_div_is_set = p_row.getGuaranteeDivIsSet();
    guarantee_div_is_modified = p_row.getGuaranteeDivIsModified();
    payment_application_div = p_row.getPaymentApplicationDiv();
    payment_application_div_is_set = p_row.getPaymentApplicationDivIsSet();
    payment_application_div_is_modified = p_row.getPaymentApplicationDivIsModified();
    cancel_type = p_row.getCancelType();
    cancel_type_is_set = p_row.getCancelTypeIsSet();
    cancel_type_is_modified = p_row.getCancelTypeIsModified();
    order_root_div = p_row.getOrderRootDiv();
    order_root_div_is_set = p_row.getOrderRootDivIsSet();
    order_root_div_is_modified = p_row.getOrderRootDivIsModified();
    error_reason_code = p_row.getErrorReasonCode();
    error_reason_code_is_set = p_row.getErrorReasonCodeIsSet();
    error_reason_code_is_modified = p_row.getErrorReasonCodeIsModified();
    mq_status = p_row.getMqStatus();
    mq_status_is_set = p_row.getMqStatusIsSet();
    mq_status_is_modified = p_row.getMqStatusIsModified();
    description = p_row.getDescription();
    description_is_set = p_row.getDescriptionIsSet();
    description_is_modified = p_row.getDescriptionIsModified();
    est_transfer_date = p_row.getEstTransferDate();
    est_transfer_date_is_set = p_row.getEstTransferDateIsSet();
    est_transfer_date_is_modified = p_row.getEstTransferDateIsModified();
    transfer_type = p_row.getTransferType();
    transfer_type_is_set = p_row.getTransferTypeIsSet();
    transfer_type_is_modified = p_row.getTransferTypeIsModified();
    transfer_done_timestamp = p_row.getTransferDoneTimestamp();
    transfer_done_timestamp_is_set = p_row.getTransferDoneTimestampIsSet();
    transfer_done_timestamp_is_modified = p_row.getTransferDoneTimestampIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    if ( !p_row.getConvertAmountIsNull() )
      convert_amount = new Double( p_row.getConvertAmount() );
    convert_amount_is_set = p_row.getConvertAmountIsSet();
    convert_amount_is_modified = p_row.getConvertAmountIsModified();
    remark_code = p_row.getRemarkCode();
    remark_code_is_set = p_row.getRemarkCodeIsSet();
    remark_code_is_modified = p_row.getRemarkCodeIsModified();
    remark_name = p_row.getRemarkName();
    remark_name_is_set = p_row.getRemarkNameIsSet();
    remark_name_is_modified = p_row.getRemarkNameIsModified();
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
      branch_id_is_set = true;
      branch_id_is_modified = true;
      trader_id_is_set = true;
      trader_id_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      order_type_is_set = true;
      order_type_is_modified = true;
      order_categ_is_set = true;
      order_categ_is_modified = true;
      last_order_action_serial_no_is_set = true;
      last_order_action_serial_no_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      order_status_is_set = true;
      order_status_is_modified = true;
      order_open_status_is_set = true;
      order_open_status_is_modified = true;
      expiration_status_is_set = true;
      expiration_status_is_modified = true;
      biz_date_is_set = true;
      biz_date_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      mini_stock_div_is_set = true;
      mini_stock_div_is_modified = true;
      received_date_time_is_set = true;
      received_date_time_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      com_debit_number_is_set = true;
      com_debit_number_is_modified = true;
      guarantee_div_is_set = true;
      guarantee_div_is_modified = true;
      payment_application_div_is_set = true;
      payment_application_div_is_modified = true;
      cancel_type_is_set = true;
      cancel_type_is_modified = true;
      order_root_div_is_set = true;
      order_root_div_is_modified = true;
      error_reason_code_is_set = true;
      error_reason_code_is_modified = true;
      mq_status_is_set = true;
      mq_status_is_modified = true;
      description_is_set = true;
      description_is_modified = true;
      est_transfer_date_is_set = true;
      est_transfer_date_is_modified = true;
      transfer_type_is_set = true;
      transfer_type_is_modified = true;
      transfer_done_timestamp_is_set = true;
      transfer_done_timestamp_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      currency_code_is_set = true;
      currency_code_is_modified = true;
      convert_amount_is_set = true;
      convert_amount_is_modified = true;
      remark_code_is_set = true;
      remark_code_is_modified = true;
      remark_name_is_set = true;
      remark_name_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AioOrderUnitRow ) )
       return false;
    return fieldsEqual( (AioOrderUnitRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAioOrderUnitRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AioOrderUnitRow row )
  {
    if ( order_unit_id != row.getOrderUnitId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( branch_id != row.getBranchId() )
      return false;
    if ( trader_id == null ) {
      if ( !row.getTraderIdIsNull() )
        return false;
    } else if ( row.getTraderIdIsNull() || ( trader_id.longValue() != row.getTraderId() ) ) {
        return false;
    }
    if ( order_id != row.getOrderId() )
      return false;
    if ( order_type == null ) {
      if ( row.getOrderType() != null )
        return false;
    } else if ( !order_type.equals( row.getOrderType() ) ) {
        return false;
    }
    if ( order_categ == null ) {
      if ( row.getOrderCateg() != null )
        return false;
    } else if ( !order_categ.equals( row.getOrderCateg() ) ) {
        return false;
    }
    if ( last_order_action_serial_no != row.getLastOrderActionSerialNo() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( quantity != row.getQuantity() )
      return false;
    if ( order_status == null ) {
      if ( row.getOrderStatus() != null )
        return false;
    } else if ( !order_status.equals( row.getOrderStatus() ) ) {
        return false;
    }
    if ( order_open_status == null ) {
      if ( row.getOrderOpenStatus() != null )
        return false;
    } else if ( !order_open_status.equals( row.getOrderOpenStatus() ) ) {
        return false;
    }
    if ( expiration_status == null ) {
      if ( row.getExpirationStatus() != null )
        return false;
    } else if ( !expiration_status.equals( row.getExpirationStatus() ) ) {
        return false;
    }
    if ( biz_date == null ) {
      if ( row.getBizDate() != null )
        return false;
    } else if ( !biz_date.equals( row.getBizDate() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( product_id != row.getProductId() )
      return false;
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( mini_stock_div == null ) {
      if ( row.getMiniStockDiv() != null )
        return false;
    } else if ( !mini_stock_div.equals( row.getMiniStockDiv() ) ) {
        return false;
    }
    if ( received_date_time == null ) {
      if ( row.getReceivedDateTime() != null )
        return false;
    } else if ( !received_date_time.equals( row.getReceivedDateTime() ) ) {
        return false;
    }
    if ( sonar_trader_code == null ) {
      if ( row.getSonarTraderCode() != null )
        return false;
    } else if ( !sonar_trader_code.equals( row.getSonarTraderCode() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( com_debit_number == null ) {
      if ( row.getComDebitNumber() != null )
        return false;
    } else if ( !com_debit_number.equals( row.getComDebitNumber() ) ) {
        return false;
    }
    if ( guarantee_div == null ) {
      if ( row.getGuaranteeDiv() != null )
        return false;
    } else if ( !guarantee_div.equals( row.getGuaranteeDiv() ) ) {
        return false;
    }
    if ( payment_application_div == null ) {
      if ( row.getPaymentApplicationDiv() != null )
        return false;
    } else if ( !payment_application_div.equals( row.getPaymentApplicationDiv() ) ) {
        return false;
    }
    if ( cancel_type == null ) {
      if ( row.getCancelType() != null )
        return false;
    } else if ( !cancel_type.equals( row.getCancelType() ) ) {
        return false;
    }
    if ( order_root_div == null ) {
      if ( row.getOrderRootDiv() != null )
        return false;
    } else if ( !order_root_div.equals( row.getOrderRootDiv() ) ) {
        return false;
    }
    if ( error_reason_code == null ) {
      if ( row.getErrorReasonCode() != null )
        return false;
    } else if ( !error_reason_code.equals( row.getErrorReasonCode() ) ) {
        return false;
    }
    if ( mq_status == null ) {
      if ( row.getMqStatus() != null )
        return false;
    } else if ( !mq_status.equals( row.getMqStatus() ) ) {
        return false;
    }
    if ( description == null ) {
      if ( row.getDescription() != null )
        return false;
    } else if ( !description.equals( row.getDescription() ) ) {
        return false;
    }
    if ( est_transfer_date == null ) {
      if ( row.getEstTransferDate() != null )
        return false;
    } else if ( !est_transfer_date.equals( row.getEstTransferDate() ) ) {
        return false;
    }
    if ( transfer_type == null ) {
      if ( row.getTransferType() != null )
        return false;
    } else if ( !transfer_type.equals( row.getTransferType() ) ) {
        return false;
    }
    if ( transfer_done_timestamp == null ) {
      if ( row.getTransferDoneTimestamp() != null )
        return false;
    } else if ( !transfer_done_timestamp.equals( row.getTransferDoneTimestamp() ) ) {
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
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( convert_amount == null ) {
      if ( !row.getConvertAmountIsNull() )
        return false;
    } else if ( row.getConvertAmountIsNull() || ( convert_amount.doubleValue() != row.getConvertAmount() ) ) {
        return false;
    }
    if ( remark_code == null ) {
      if ( row.getRemarkCode() != null )
        return false;
    } else if ( !remark_code.equals( row.getRemarkCode() ) ) {
        return false;
    }
    if ( remark_name == null ) {
      if ( row.getRemarkName() != null )
        return false;
    } else if ( !remark_name.equals( row.getRemarkName() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) order_unit_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) branch_id)
        + (trader_id!=null? trader_id.hashCode(): 0) 
        + ((int) order_id)
        + (order_type!=null? order_type.hashCode(): 0) 
        + (order_categ!=null? order_categ.hashCode(): 0) 
        + ((int) last_order_action_serial_no)
        + (product_type!=null? product_type.hashCode(): 0) 
        + ((int) quantity)
        + (order_status!=null? order_status.hashCode(): 0) 
        + (order_open_status!=null? order_open_status.hashCode(): 0) 
        + (expiration_status!=null? expiration_status.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + ((int) product_id)
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (mini_stock_div!=null? mini_stock_div.hashCode(): 0) 
        + (received_date_time!=null? received_date_time.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (com_debit_number!=null? com_debit_number.hashCode(): 0) 
        + (guarantee_div!=null? guarantee_div.hashCode(): 0) 
        + (payment_application_div!=null? payment_application_div.hashCode(): 0) 
        + (cancel_type!=null? cancel_type.hashCode(): 0) 
        + (order_root_div!=null? order_root_div.hashCode(): 0) 
        + (error_reason_code!=null? error_reason_code.hashCode(): 0) 
        + (mq_status!=null? mq_status.hashCode(): 0) 
        + (description!=null? description.hashCode(): 0) 
        + (est_transfer_date!=null? est_transfer_date.hashCode(): 0) 
        + (transfer_type!=null? transfer_type.hashCode(): 0) 
        + (transfer_done_timestamp!=null? transfer_done_timestamp.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (convert_amount!=null? convert_amount.hashCode(): 0) 
        + (remark_code!=null? remark_code.hashCode(): 0) 
        + (remark_name!=null? remark_name.hashCode(): 0) 
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
		if ( !branch_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_id' must be set before inserting.");
		if ( !order_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_id' must be set before inserting.");
		if ( !order_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_type' must be set before inserting.");
		if ( !order_categ_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_categ' must be set before inserting.");
		if ( !last_order_action_serial_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_order_action_serial_no' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
		if ( !order_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_status' must be set before inserting.");
		if ( !order_open_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_open_status' must be set before inserting.");
		if ( !expiration_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'expiration_status' must be set before inserting.");
		if ( !biz_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'biz_date' must be set before inserting.");
		if ( !delivery_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_date' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !est_transfer_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'est_transfer_date' must be set before inserting.");
		if ( !transfer_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'transfer_type' must be set before inserting.");
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
		map.put("order_unit_id",new Long(order_unit_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("branch_id",new Long(branch_id));
		if ( trader_id != null )
			map.put("trader_id",trader_id);
		map.put("order_id",new Long(order_id));
		map.put("order_type",order_type);
		map.put("order_categ",order_categ);
		map.put("last_order_action_serial_no",new Integer(last_order_action_serial_no));
		map.put("product_type",product_type);
		map.put("quantity",new Double(quantity));
		map.put("order_status",order_status);
		map.put("order_open_status",order_open_status);
		map.put("expiration_status",expiration_status);
		map.put("biz_date",biz_date);
		map.put("delivery_date",delivery_date);
		map.put("product_id",new Long(product_id));
		if ( tax_type_is_set )
			map.put("tax_type",tax_type);
		if ( mini_stock_div_is_set )
			map.put("mini_stock_div",mini_stock_div);
		if ( received_date_time != null )
			map.put("received_date_time",received_date_time);
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( order_request_number != null )
			map.put("order_request_number",order_request_number);
		if ( com_debit_number != null )
			map.put("com_debit_number",com_debit_number);
		if ( guarantee_div != null )
			map.put("guarantee_div",guarantee_div);
		if ( payment_application_div != null )
			map.put("payment_application_div",payment_application_div);
		if ( cancel_type != null )
			map.put("cancel_type",cancel_type);
		if ( order_root_div != null )
			map.put("order_root_div",order_root_div);
		if ( error_reason_code != null )
			map.put("error_reason_code",error_reason_code);
		if ( mq_status != null )
			map.put("mq_status",mq_status);
		if ( description != null )
			map.put("description",description);
		map.put("est_transfer_date",est_transfer_date);
		map.put("transfer_type",transfer_type);
		if ( transfer_done_timestamp != null )
			map.put("transfer_done_timestamp",transfer_done_timestamp);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( currency_code != null )
			map.put("currency_code",currency_code);
		if ( convert_amount != null )
			map.put("convert_amount",convert_amount);
		if ( remark_code != null )
			map.put("remark_code",remark_code);
		if ( remark_name != null )
			map.put("remark_name",remark_name);
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
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( trader_id_is_modified )
			map.put("trader_id",trader_id);
		if ( order_id_is_modified )
			map.put("order_id",new Long(order_id));
		if ( order_type_is_modified )
			map.put("order_type",order_type);
		if ( order_categ_is_modified )
			map.put("order_categ",order_categ);
		if ( last_order_action_serial_no_is_modified )
			map.put("last_order_action_serial_no",new Integer(last_order_action_serial_no));
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( quantity_is_modified )
			map.put("quantity",new Double(quantity));
		if ( order_status_is_modified )
			map.put("order_status",order_status);
		if ( order_open_status_is_modified )
			map.put("order_open_status",order_open_status);
		if ( expiration_status_is_modified )
			map.put("expiration_status",expiration_status);
		if ( biz_date_is_modified )
			map.put("biz_date",biz_date);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( mini_stock_div_is_modified )
			map.put("mini_stock_div",mini_stock_div);
		if ( received_date_time_is_modified )
			map.put("received_date_time",received_date_time);
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( com_debit_number_is_modified )
			map.put("com_debit_number",com_debit_number);
		if ( guarantee_div_is_modified )
			map.put("guarantee_div",guarantee_div);
		if ( payment_application_div_is_modified )
			map.put("payment_application_div",payment_application_div);
		if ( cancel_type_is_modified )
			map.put("cancel_type",cancel_type);
		if ( order_root_div_is_modified )
			map.put("order_root_div",order_root_div);
		if ( error_reason_code_is_modified )
			map.put("error_reason_code",error_reason_code);
		if ( mq_status_is_modified )
			map.put("mq_status",mq_status);
		if ( description_is_modified )
			map.put("description",description);
		if ( est_transfer_date_is_modified )
			map.put("est_transfer_date",est_transfer_date);
		if ( transfer_type_is_modified )
			map.put("transfer_type",transfer_type);
		if ( transfer_done_timestamp_is_modified )
			map.put("transfer_done_timestamp",transfer_done_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( currency_code_is_modified )
			map.put("currency_code",currency_code);
		if ( convert_amount_is_modified )
			map.put("convert_amount",convert_amount);
		if ( remark_code_is_modified )
			map.put("remark_code",remark_code);
		if ( remark_name_is_modified )
			map.put("remark_name",remark_name);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			map.put("trader_id",trader_id);
			if ( order_id_is_set )
				map.put("order_id",new Long(order_id));
			if ( order_type_is_set )
				map.put("order_type",order_type);
			if ( order_categ_is_set )
				map.put("order_categ",order_categ);
			if ( last_order_action_serial_no_is_set )
				map.put("last_order_action_serial_no",new Integer(last_order_action_serial_no));
			if ( product_type_is_set )
				map.put("product_type",product_type);
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			if ( order_status_is_set )
				map.put("order_status",order_status);
			if ( order_open_status_is_set )
				map.put("order_open_status",order_open_status);
			if ( expiration_status_is_set )
				map.put("expiration_status",expiration_status);
			if ( biz_date_is_set )
				map.put("biz_date",biz_date);
			if ( delivery_date_is_set )
				map.put("delivery_date",delivery_date);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( tax_type_is_set )
				map.put("tax_type",tax_type);
			if ( mini_stock_div_is_set )
				map.put("mini_stock_div",mini_stock_div);
			map.put("received_date_time",received_date_time);
			map.put("sonar_trader_code",sonar_trader_code);
			map.put("order_request_number",order_request_number);
			map.put("com_debit_number",com_debit_number);
			map.put("guarantee_div",guarantee_div);
			map.put("payment_application_div",payment_application_div);
			map.put("cancel_type",cancel_type);
			map.put("order_root_div",order_root_div);
			map.put("error_reason_code",error_reason_code);
			map.put("mq_status",mq_status);
			map.put("description",description);
			if ( est_transfer_date_is_set )
				map.put("est_transfer_date",est_transfer_date);
			if ( transfer_type_is_set )
				map.put("transfer_type",transfer_type);
			map.put("transfer_done_timestamp",transfer_done_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("currency_code",currency_code);
			map.put("convert_amount",convert_amount);
			map.put("remark_code",remark_code);
			map.put("remark_name",remark_name);
		}
		return map;
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
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
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
   * <em>order_categ</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum getOrderCateg()
  {
    return order_categ;
  }


  /** 
   * <em>order_categ</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCategIsSet() {
    return order_categ_is_set;
  }


  /** 
   * <em>order_categ</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCategIsModified() {
    return order_categ_is_modified;
  }


  /** 
   * <em>last_order_action_serial_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLastOrderActionSerialNo()
  {
    return last_order_action_serial_no;
  }


  /** 
   * <em>last_order_action_serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastOrderActionSerialNoIsSet() {
    return last_order_action_serial_no_is_set;
  }


  /** 
   * <em>last_order_action_serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastOrderActionSerialNoIsModified() {
    return last_order_action_serial_no_is_modified;
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
   * <em>order_open_status</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum getOrderOpenStatus()
  {
    return order_open_status;
  }


  /** 
   * <em>order_open_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderOpenStatusIsSet() {
    return order_open_status_is_set;
  }


  /** 
   * <em>order_open_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderOpenStatusIsModified() {
    return order_open_status_is_modified;
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
   * <em>biz_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBizDate()
  {
    return biz_date;
  }


  /** 
   * <em>biz_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBizDateIsSet() {
    return biz_date_is_set;
  }


  /** 
   * <em>biz_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBizDateIsModified() {
    return biz_date_is_modified;
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
   * <em>tax_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getTaxType()
  {
    return tax_type;
  }


  /** 
   * <em>tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxTypeIsSet() {
    return tax_type_is_set;
  }


  /** 
   * <em>tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxTypeIsModified() {
    return tax_type_is_modified;
  }


  /** 
   * <em>mini_stock_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMiniStockDiv()
  {
    return mini_stock_div;
  }


  /** 
   * <em>mini_stock_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockDivIsSet() {
    return mini_stock_div_is_set;
  }


  /** 
   * <em>mini_stock_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockDivIsModified() {
    return mini_stock_div_is_modified;
  }


  /** 
   * <em>received_date_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getReceivedDateTime()
  {
    return received_date_time;
  }


  /** 
   * <em>received_date_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceivedDateTimeIsSet() {
    return received_date_time_is_set;
  }


  /** 
   * <em>received_date_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceivedDateTimeIsModified() {
    return received_date_time_is_modified;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarTraderCode()
  {
    return sonar_trader_code;
  }


  /** 
   * <em>sonar_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeIsSet() {
    return sonar_trader_code_is_set;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeIsModified() {
    return sonar_trader_code_is_modified;
  }


  /** 
   * <em>order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRequestNumber()
  {
    return order_request_number;
  }


  /** 
   * <em>order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsSet() {
    return order_request_number_is_set;
  }


  /** 
   * <em>order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsModified() {
    return order_request_number_is_modified;
  }


  /** 
   * <em>com_debit_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getComDebitNumber()
  {
    return com_debit_number;
  }


  /** 
   * <em>com_debit_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComDebitNumberIsSet() {
    return com_debit_number_is_set;
  }


  /** 
   * <em>com_debit_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComDebitNumberIsModified() {
    return com_debit_number_is_modified;
  }


  /** 
   * <em>guarantee_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGuaranteeDiv()
  {
    return guarantee_div;
  }


  /** 
   * <em>guarantee_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGuaranteeDivIsSet() {
    return guarantee_div_is_set;
  }


  /** 
   * <em>guarantee_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGuaranteeDivIsModified() {
    return guarantee_div_is_modified;
  }


  /** 
   * <em>payment_application_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentApplicationDiv()
  {
    return payment_application_div;
  }


  /** 
   * <em>payment_application_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentApplicationDivIsSet() {
    return payment_application_div_is_set;
  }


  /** 
   * <em>payment_application_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentApplicationDivIsModified() {
    return payment_application_div_is_modified;
  }


  /** 
   * <em>cancel_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCancelType()
  {
    return cancel_type;
  }


  /** 
   * <em>cancel_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelTypeIsSet() {
    return cancel_type_is_set;
  }


  /** 
   * <em>cancel_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelTypeIsModified() {
    return cancel_type_is_modified;
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
   * <em>mq_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMqStatus()
  {
    return mq_status;
  }


  /** 
   * <em>mq_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMqStatusIsSet() {
    return mq_status_is_set;
  }


  /** 
   * <em>mq_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMqStatusIsModified() {
    return mq_status_is_modified;
  }


  /** 
   * <em>description</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDescription()
  {
    return description;
  }


  /** 
   * <em>description</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDescriptionIsSet() {
    return description_is_set;
  }


  /** 
   * <em>description</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDescriptionIsModified() {
    return description_is_modified;
  }


  /** 
   * <em>est_transfer_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getEstTransferDate()
  {
    return est_transfer_date;
  }


  /** 
   * <em>est_transfer_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEstTransferDateIsSet() {
    return est_transfer_date_is_set;
  }


  /** 
   * <em>est_transfer_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEstTransferDateIsModified() {
    return est_transfer_date_is_modified;
  }


  /** 
   * <em>transfer_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum getTransferType()
  {
    return transfer_type;
  }


  /** 
   * <em>transfer_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferTypeIsSet() {
    return transfer_type_is_set;
  }


  /** 
   * <em>transfer_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferTypeIsModified() {
    return transfer_type_is_modified;
  }


  /** 
   * <em>transfer_done_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTransferDoneTimestamp()
  {
    return transfer_done_timestamp;
  }


  /** 
   * <em>transfer_done_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDoneTimestampIsSet() {
    return transfer_done_timestamp_is_set;
  }


  /** 
   * <em>transfer_done_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDoneTimestampIsModified() {
    return transfer_done_timestamp_is_modified;
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
   * <em>currency_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCurrencyCode()
  {
    return currency_code;
  }


  /** 
   * <em>currency_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsSet() {
    return currency_code_is_set;
  }


  /** 
   * <em>currency_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsModified() {
    return currency_code_is_modified;
  }


  /** 
   * <em>convert_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getConvertAmount()
  {
    return ( convert_amount==null? ((double)0): convert_amount.doubleValue() );
  }


  /** 
   * <em>convert_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getConvertAmountIsNull()
  {
    return convert_amount == null;
  }


  /** 
   * <em>convert_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConvertAmountIsSet() {
    return convert_amount_is_set;
  }


  /** 
   * <em>convert_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConvertAmountIsModified() {
    return convert_amount_is_modified;
  }


  /** 
   * <em>remark_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRemarkCode()
  {
    return remark_code;
  }


  /** 
   * <em>remark_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkCodeIsSet() {
    return remark_code_is_set;
  }


  /** 
   * <em>remark_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkCodeIsModified() {
    return remark_code_is_modified;
  }


  /** 
   * <em>remark_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRemarkName()
  {
    return remark_name;
  }


  /** 
   * <em>remark_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkNameIsSet() {
    return remark_name_is_set;
  }


  /** 
   * <em>remark_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkNameIsModified() {
    return remark_name_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AioOrderUnitPK(order_unit_id);
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
   * <em>branch_id</em>カラムの値を設定します。 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
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
   * <em>order_categ</em>カラムの値を設定します。 
   *
   * @@param p_orderCateg <em>order_categ</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum値 
   */
  public final void setOrderCateg( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum p_orderCateg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_categ = p_orderCateg;
    order_categ_is_set = true;
    order_categ_is_modified = true;
  }


  /** 
   * <em>last_order_action_serial_no</em>カラムの値を設定します。 
   *
   * @@param p_lastOrderActionSerialNo <em>last_order_action_serial_no</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setLastOrderActionSerialNo( int p_lastOrderActionSerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_order_action_serial_no = p_lastOrderActionSerialNo;
    last_order_action_serial_no_is_set = true;
    last_order_action_serial_no_is_modified = true;
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
   * <em>order_open_status</em>カラムの値を設定します。 
   *
   * @@param p_orderOpenStatus <em>order_open_status</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum値 
   */
  public final void setOrderOpenStatus( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum p_orderOpenStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_open_status = p_orderOpenStatus;
    order_open_status_is_set = true;
    order_open_status_is_modified = true;
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
   * <em>biz_date</em>カラムの値を設定します。 
   *
   * @@param p_bizDate <em>biz_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setBizDate( String p_bizDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    biz_date = p_bizDate;
    biz_date_is_set = true;
    biz_date_is_modified = true;
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
   * <em>tax_type</em>カラムの値を設定します。 
   *
   * @@param p_taxType <em>tax_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum値 
   */
  public final void setTaxType( com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type = p_taxType;
    tax_type_is_set = true;
    tax_type_is_modified = true;
  }


  /** 
   * <em>mini_stock_div</em>カラムの値を設定します。 
   *
   * @@param p_miniStockDiv <em>mini_stock_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMiniStockDiv( String p_miniStockDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_div = p_miniStockDiv;
    mini_stock_div_is_set = true;
    mini_stock_div_is_modified = true;
  }


  /** 
   * <em>received_date_time</em>カラムの値を設定します。 
   *
   * @@param p_receivedDateTime <em>received_date_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setReceivedDateTime( java.sql.Timestamp p_receivedDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    received_date_time = p_receivedDateTime;
    received_date_time_is_set = true;
    received_date_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>received_date_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setReceivedDateTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    received_date_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    received_date_time_is_set = true;
    received_date_time_is_modified = true;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値を設定します。 
   *
   * @@param p_sonarTraderCode <em>sonar_trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setSonarTraderCode( String p_sonarTraderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_trader_code = p_sonarTraderCode;
    sonar_trader_code_is_set = true;
    sonar_trader_code_is_modified = true;
  }


  /** 
   * <em>order_request_number</em>カラムの値を設定します。 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setOrderRequestNumber( String p_orderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number = p_orderRequestNumber;
    order_request_number_is_set = true;
    order_request_number_is_modified = true;
  }


  /** 
   * <em>com_debit_number</em>カラムの値を設定します。 
   *
   * @@param p_comDebitNumber <em>com_debit_number</em>カラムの値をあらわす15桁以下のString値 
   */
  public final void setComDebitNumber( String p_comDebitNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    com_debit_number = p_comDebitNumber;
    com_debit_number_is_set = true;
    com_debit_number_is_modified = true;
  }


  /** 
   * <em>guarantee_div</em>カラムの値を設定します。 
   *
   * @@param p_guaranteeDiv <em>guarantee_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGuaranteeDiv( String p_guaranteeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    guarantee_div = p_guaranteeDiv;
    guarantee_div_is_set = true;
    guarantee_div_is_modified = true;
  }


  /** 
   * <em>payment_application_div</em>カラムの値を設定します。 
   *
   * @@param p_paymentApplicationDiv <em>payment_application_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setPaymentApplicationDiv( String p_paymentApplicationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_application_div = p_paymentApplicationDiv;
    payment_application_div_is_set = true;
    payment_application_div_is_modified = true;
  }


  /** 
   * <em>cancel_type</em>カラムの値を設定します。 
   *
   * @@param p_cancelType <em>cancel_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCancelType( String p_cancelType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_type = p_cancelType;
    cancel_type_is_set = true;
    cancel_type_is_modified = true;
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
   * <em>mq_status</em>カラムの値を設定します。 
   *
   * @@param p_mqStatus <em>mq_status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMqStatus( String p_mqStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mq_status = p_mqStatus;
    mq_status_is_set = true;
    mq_status_is_modified = true;
  }


  /** 
   * <em>description</em>カラムの値を設定します。 
   *
   * @@param p_description <em>description</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setDescription( String p_description )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    description = p_description;
    description_is_set = true;
    description_is_modified = true;
  }


  /** 
   * <em>est_transfer_date</em>カラムの値を設定します。 
   *
   * @@param p_estTransferDate <em>est_transfer_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setEstTransferDate( java.sql.Timestamp p_estTransferDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    est_transfer_date = p_estTransferDate;
    est_transfer_date_is_set = true;
    est_transfer_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>est_transfer_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setEstTransferDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    est_transfer_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    est_transfer_date_is_set = true;
    est_transfer_date_is_modified = true;
  }


  /** 
   * <em>transfer_type</em>カラムの値を設定します。 
   *
   * @@param p_transferType <em>transfer_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum値 
   */
  public final void setTransferType( com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum p_transferType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_type = p_transferType;
    transfer_type_is_set = true;
    transfer_type_is_modified = true;
  }


  /** 
   * <em>transfer_done_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_transferDoneTimestamp <em>transfer_done_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTransferDoneTimestamp( java.sql.Timestamp p_transferDoneTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_done_timestamp = p_transferDoneTimestamp;
    transfer_done_timestamp_is_set = true;
    transfer_done_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>transfer_done_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTransferDoneTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_done_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    transfer_done_timestamp_is_set = true;
    transfer_done_timestamp_is_modified = true;
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
   * <em>currency_code</em>カラムの値を設定します。 
   *
   * @@param p_currencyCode <em>currency_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>convert_amount</em>カラムの値を設定します。 
   *
   * @@param p_convertAmount <em>convert_amount</em>カラムの値をあらわす21桁以下のdouble値で、その精度は6桁まで
   */
  public final void setConvertAmount( double p_convertAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    convert_amount = new Double(p_convertAmount);
    convert_amount_is_set = true;
    convert_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>convert_amount</em>カラムに値を設定します。 
   */
  public final void setConvertAmount( Double p_convertAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    convert_amount = p_convertAmount;
    convert_amount_is_set = true;
    convert_amount_is_modified = true;
  }


  /** 
   * <em>remark_code</em>カラムの値を設定します。 
   *
   * @@param p_remarkCode <em>remark_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setRemarkCode( String p_remarkCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark_code = p_remarkCode;
    remark_code_is_set = true;
    remark_code_is_modified = true;
  }


  /** 
   * <em>remark_name</em>カラムの値を設定します。 
   *
   * @@param p_remarkName <em>remark_name</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setRemarkName( String p_remarkName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark_name = p_remarkName;
    remark_name_is_set = true;
    remark_name_is_modified = true;
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
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                else if ( name.equals("biz_date") ) {
                    return this.biz_date;
                }
                break;
            case 'c':
                if ( name.equals("com_debit_number") ) {
                    return this.com_debit_number;
                }
                else if ( name.equals("cancel_type") ) {
                    return this.cancel_type;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("convert_amount") ) {
                    return this.convert_amount;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                else if ( name.equals("description") ) {
                    return this.description;
                }
                break;
            case 'e':
                if ( name.equals("expiration_status") ) {
                    return this.expiration_status;
                }
                else if ( name.equals("error_reason_code") ) {
                    return this.error_reason_code;
                }
                else if ( name.equals("est_transfer_date") ) {
                    return this.est_transfer_date;
                }
                break;
            case 'g':
                if ( name.equals("guarantee_div") ) {
                    return this.guarantee_div;
                }
                break;
            case 'l':
                if ( name.equals("last_order_action_serial_no") ) {
                    return new Integer( last_order_action_serial_no );
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("mini_stock_div") ) {
                    return this.mini_stock_div;
                }
                else if ( name.equals("mq_status") ) {
                    return this.mq_status;
                }
                break;
            case 'o':
                if ( name.equals("order_unit_id") ) {
                    return new Long( order_unit_id );
                }
                else if ( name.equals("order_id") ) {
                    return new Long( order_id );
                }
                else if ( name.equals("order_type") ) {
                    return this.order_type;
                }
                else if ( name.equals("order_categ") ) {
                    return this.order_categ;
                }
                else if ( name.equals("order_status") ) {
                    return this.order_status;
                }
                else if ( name.equals("order_open_status") ) {
                    return this.order_open_status;
                }
                else if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_root_div") ) {
                    return this.order_root_div;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("payment_application_div") ) {
                    return this.payment_application_div;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return new Double( quantity );
                }
                break;
            case 'r':
                if ( name.equals("received_date_time") ) {
                    return this.received_date_time;
                }
                else if ( name.equals("remark_code") ) {
                    return this.remark_code;
                }
                else if ( name.equals("remark_name") ) {
                    return this.remark_name;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    return this.trader_id;
                }
                else if ( name.equals("tax_type") ) {
                    return this.tax_type;
                }
                else if ( name.equals("transfer_type") ) {
                    return this.transfer_type;
                }
                else if ( name.equals("transfer_done_timestamp") ) {
                    return this.transfer_done_timestamp;
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
            case 'b':
                if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                else if ( name.equals("biz_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'biz_date' must be String: '"+value+"' is not." );
                    this.biz_date = (String) value;
                    if (this.biz_date_is_set)
                        this.biz_date_is_modified = true;
                    this.biz_date_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("com_debit_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'com_debit_number' must be String: '"+value+"' is not." );
                    this.com_debit_number = (String) value;
                    if (this.com_debit_number_is_set)
                        this.com_debit_number_is_modified = true;
                    this.com_debit_number_is_set = true;
                    return;
                }
                else if ( name.equals("cancel_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cancel_type' must be String: '"+value+"' is not." );
                    this.cancel_type = (String) value;
                    if (this.cancel_type_is_set)
                        this.cancel_type_is_modified = true;
                    this.cancel_type_is_set = true;
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
                else if ( name.equals("currency_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_code' must be String: '"+value+"' is not." );
                    this.currency_code = (String) value;
                    if (this.currency_code_is_set)
                        this.currency_code_is_modified = true;
                    this.currency_code_is_set = true;
                    return;
                }
                else if ( name.equals("convert_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'convert_amount' must be Double: '"+value+"' is not." );
                    this.convert_amount = (Double) value;
                    if (this.convert_amount_is_set)
                        this.convert_amount_is_modified = true;
                    this.convert_amount_is_set = true;
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
                else if ( name.equals("description") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'description' must be String: '"+value+"' is not." );
                    this.description = (String) value;
                    if (this.description_is_set)
                        this.description_is_modified = true;
                    this.description_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("expiration_status") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum) )
                        throw new IllegalArgumentException( "value for 'expiration_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum: '"+value+"' is not." );
                    this.expiration_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum) value;
                    if (this.expiration_status_is_set)
                        this.expiration_status_is_modified = true;
                    this.expiration_status_is_set = true;
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
                else if ( name.equals("est_transfer_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'est_transfer_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.est_transfer_date = (java.sql.Timestamp) value;
                    if (this.est_transfer_date_is_set)
                        this.est_transfer_date_is_modified = true;
                    this.est_transfer_date_is_set = true;
                    return;
                }
                break;
            case 'g':
                if ( name.equals("guarantee_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'guarantee_div' must be String: '"+value+"' is not." );
                    this.guarantee_div = (String) value;
                    if (this.guarantee_div_is_set)
                        this.guarantee_div_is_modified = true;
                    this.guarantee_div_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_order_action_serial_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'last_order_action_serial_no' must be Integer: '"+value+"' is not." );
                    this.last_order_action_serial_no = ((Integer) value).intValue();
                    if (this.last_order_action_serial_no_is_set)
                        this.last_order_action_serial_no_is_modified = true;
                    this.last_order_action_serial_no_is_set = true;
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
                if ( name.equals("mini_stock_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mini_stock_div' must be String: '"+value+"' is not." );
                    this.mini_stock_div = (String) value;
                    if (this.mini_stock_div_is_set)
                        this.mini_stock_div_is_modified = true;
                    this.mini_stock_div_is_set = true;
                    return;
                }
                else if ( name.equals("mq_status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mq_status' must be String: '"+value+"' is not." );
                    this.mq_status = (String) value;
                    if (this.mq_status_is_set)
                        this.mq_status_is_modified = true;
                    this.mq_status_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_unit_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_unit_id' must be Long: '"+value+"' is not." );
                    this.order_unit_id = ((Long) value).longValue();
                    if (this.order_unit_id_is_set)
                        this.order_unit_id_is_modified = true;
                    this.order_unit_id_is_set = true;
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
                else if ( name.equals("order_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) )
                        throw new IllegalArgumentException( "value for 'order_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum: '"+value+"' is not." );
                    this.order_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) value;
                    if (this.order_type_is_set)
                        this.order_type_is_modified = true;
                    this.order_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_categ") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum) )
                        throw new IllegalArgumentException( "value for 'order_categ' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum: '"+value+"' is not." );
                    this.order_categ = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum) value;
                    if (this.order_categ_is_set)
                        this.order_categ_is_modified = true;
                    this.order_categ_is_set = true;
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
                else if ( name.equals("order_open_status") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum) )
                        throw new IllegalArgumentException( "value for 'order_open_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum: '"+value+"' is not." );
                    this.order_open_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum) value;
                    if (this.order_open_status_is_set)
                        this.order_open_status_is_modified = true;
                    this.order_open_status_is_set = true;
                    return;
                }
                else if ( name.equals("order_request_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
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
                if ( name.equals("product_type") ) {
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
                else if ( name.equals("payment_application_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_application_div' must be String: '"+value+"' is not." );
                    this.payment_application_div = (String) value;
                    if (this.payment_application_div_is_set)
                        this.payment_application_div_is_modified = true;
                    this.payment_application_div_is_set = true;
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
            case 'r':
                if ( name.equals("received_date_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'received_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.received_date_time = (java.sql.Timestamp) value;
                    if (this.received_date_time_is_set)
                        this.received_date_time_is_modified = true;
                    this.received_date_time_is_set = true;
                    return;
                }
                else if ( name.equals("remark_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'remark_code' must be String: '"+value+"' is not." );
                    this.remark_code = (String) value;
                    if (this.remark_code_is_set)
                        this.remark_code_is_modified = true;
                    this.remark_code_is_set = true;
                    return;
                }
                else if ( name.equals("remark_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'remark_name' must be String: '"+value+"' is not." );
                    this.remark_name = (String) value;
                    if (this.remark_name_is_set)
                        this.remark_name_is_modified = true;
                    this.remark_name_is_set = true;
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
                else if ( name.equals("sonar_trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_trader_code' must be String: '"+value+"' is not." );
                    this.sonar_trader_code = (String) value;
                    if (this.sonar_trader_code_is_set)
                        this.sonar_trader_code_is_modified = true;
                    this.sonar_trader_code_is_set = true;
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
                else if ( name.equals("tax_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum: '"+value+"' is not." );
                    this.tax_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) value;
                    if (this.tax_type_is_set)
                        this.tax_type_is_modified = true;
                    this.tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum) )
                        throw new IllegalArgumentException( "value for 'transfer_type' must be com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum: '"+value+"' is not." );
                    this.transfer_type = (com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum) value;
                    if (this.transfer_type_is_set)
                        this.transfer_type_is_modified = true;
                    this.transfer_type_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_done_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'transfer_done_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.transfer_done_timestamp = (java.sql.Timestamp) value;
                    if (this.transfer_done_timestamp_is_set)
                        this.transfer_done_timestamp_is_modified = true;
                    this.transfer_done_timestamp_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
