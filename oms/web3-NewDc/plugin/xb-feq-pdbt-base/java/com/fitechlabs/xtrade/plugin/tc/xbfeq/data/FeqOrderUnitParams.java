head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.03.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88656402a7;
filename	FeqOrderUnitParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbfeq.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * feq_order_unitテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link FeqOrderUnitRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link FeqOrderUnitParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link FeqOrderUnitParams}が{@@link FeqOrderUnitRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FeqOrderUnitPK 
 * @@see FeqOrderUnitRow 
 */
public class FeqOrderUnitParams extends Params implements FeqOrderUnitRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "feq_order_unit";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = FeqOrderUnitRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return FeqOrderUnitRow.TYPE;
  }


  /** 
   * <em>order_unit_id</em>カラムの値 
   */
  public  long  order_unit_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

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
   * <em>last_execution_serial_no</em>カラムの値 
   */
  public  int  last_execution_serial_no;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  Long  market_id;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  double  quantity;

  /** 
   * <em>limit_price</em>カラムの値 
   */
  public  Double  limit_price;

  /** 
   * <em>execution_condition_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType  execution_condition_type;

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
   * <em>settle_div</em>カラムの値 
   */
  public  String  settle_div;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>expiration_date</em>カラムの値 
   */
  public  java.sql.Timestamp  expiration_date;

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
   * <em>executed_amount</em>カラムの値 
   */
  public  Double  executed_amount;

  /** 
   * <em>executed_amount_yen</em>カラムの値 
   */
  public  Double  executed_amount_yen;

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
   * <em>tax_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum  tax_type;

  /** 
   * <em>biz_date</em>カラムの値 
   */
  public  String  biz_date;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>currency_code</em>カラムの値 
   */
  public  String  currency_code;

  /** 
   * <em>order_chanel</em>カラムの値 
   */
  public  String  order_chanel;

  /** 
   * <em>received_date_time</em>カラムの値 
   */
  public  java.sql.Timestamp  received_date_time;

  /** 
   * <em>voucher_no</em>カラムの値 
   */
  public  String  voucher_no;

  /** 
   * <em>comm_tbl_no</em>カラムの値 
   */
  public  String  comm_tbl_no;

  /** 
   * <em>comm_tbl_sub_no</em>カラムの値 
   */
  public  String  comm_tbl_sub_no;

  /** 
   * <em>sonar_trader_code</em>カラムの値 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>price</em>カラムの値 
   */
  public  Double  price;

  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>estimated_price</em>カラムの値 
   */
  public  Double  estimated_price;

  /** 
   * <em>f_estimated_price</em>カラムの値 
   */
  public  Double  f_estimated_price;

  /** 
   * <em>sonar_traded_code</em>カラムの値 
   */
  public  String  sonar_traded_code;

  /** 
   * <em>sonar_market_code</em>カラムの値 
   */
  public  String  sonar_market_code;

  /** 
   * <em>comm_product_code</em>カラムの値 
   */
  public  String  comm_product_code;

  /** 
   * <em>capital_gain</em>カラムの値 
   */
  public  Double  capital_gain;

  /** 
   * <em>capital_gain_tax</em>カラムの値 
   */
  public  Double  capital_gain_tax;

  /** 
   * <em>modify_cancel_type</em>カラムの値 
   */
  public  String  modify_cancel_type;

  /** 
   * <em>order_root_div</em>カラムの値 
   */
  public  String  order_root_div;

  /** 
   * <em>confirmed_order_price</em>カラムの値 
   */
  public  Double  confirmed_order_price;

  /** 
   * <em>confirmed_estimated_price</em>カラムの値 
   */
  public  Double  confirmed_estimated_price;

  /** 
   * <em>confirmed_f_estimated_price</em>カラムの値 
   */
  public  Double  confirmed_f_estimated_price;

  /** 
   * <em>confirmed_exec_condition_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType  confirmed_exec_condition_type;

  /** 
   * <em>error_reason_code</em>カラムの値 
   */
  public  String  error_reason_code;

  /** 
   * <em>factor</em>カラムの値 
   */
  public  String  factor;

  /** 
   * <em>order_emp_code</em>カラムの値 
   */
  public  String  order_emp_code;

  /** 
   * <em>account_div</em>カラムの値 
   */
  public  String  account_div;

  /** 
   * <em>exec_end_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  exec_end_timestamp;

  /** 
   * <em>exec_file_send_flag</em>カラムの値 
   */
  public  String  exec_file_send_flag;

  /** 
   * <em>first_order_unit_id</em>カラムの値 
   */
  public  Long  first_order_unit_id;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>execution_seq_no</em>カラムの値 
   */
  public  Integer  execution_seq_no;

  /** 
   * <em>temporary_execution_flag</em>カラムの値 
   */
  public  String  temporary_execution_flag;

  private boolean order_unit_id_is_set = false;
  private boolean order_unit_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
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
  private boolean last_execution_serial_no_is_set = false;
  private boolean last_execution_serial_no_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean limit_price_is_set = false;
  private boolean limit_price_is_modified = false;
  private boolean execution_condition_type_is_set = false;
  private boolean execution_condition_type_is_modified = false;
  private boolean order_condition_type_is_set = false;
  private boolean order_condition_type_is_modified = false;
  private boolean order_cond_operator_is_set = false;
  private boolean order_cond_operator_is_modified = false;
  private boolean stop_order_price_is_set = false;
  private boolean stop_order_price_is_modified = false;
  private boolean w_limit_price_is_set = false;
  private boolean w_limit_price_is_modified = false;
  private boolean settle_div_is_set = false;
  private boolean settle_div_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean expiration_date_is_set = false;
  private boolean expiration_date_is_modified = false;
  private boolean confirmed_quantity_is_set = false;
  private boolean confirmed_quantity_is_modified = false;
  private boolean confirmed_price_is_set = false;
  private boolean confirmed_price_is_modified = false;
  private boolean executed_quantity_is_set = false;
  private boolean executed_quantity_is_modified = false;
  private boolean executed_amount_is_set = false;
  private boolean executed_amount_is_modified = false;
  private boolean executed_amount_yen_is_set = false;
  private boolean executed_amount_yen_is_modified = false;
  private boolean order_status_is_set = false;
  private boolean order_status_is_modified = false;
  private boolean order_open_status_is_set = false;
  private boolean order_open_status_is_modified = false;
  private boolean expiration_status_is_set = false;
  private boolean expiration_status_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean biz_date_is_set = false;
  private boolean biz_date_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean order_chanel_is_set = false;
  private boolean order_chanel_is_modified = false;
  private boolean received_date_time_is_set = false;
  private boolean received_date_time_is_modified = false;
  private boolean voucher_no_is_set = false;
  private boolean voucher_no_is_modified = false;
  private boolean comm_tbl_no_is_set = false;
  private boolean comm_tbl_no_is_modified = false;
  private boolean comm_tbl_sub_no_is_set = false;
  private boolean comm_tbl_sub_no_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean estimated_price_is_set = false;
  private boolean estimated_price_is_modified = false;
  private boolean f_estimated_price_is_set = false;
  private boolean f_estimated_price_is_modified = false;
  private boolean sonar_traded_code_is_set = false;
  private boolean sonar_traded_code_is_modified = false;
  private boolean sonar_market_code_is_set = false;
  private boolean sonar_market_code_is_modified = false;
  private boolean comm_product_code_is_set = false;
  private boolean comm_product_code_is_modified = false;
  private boolean capital_gain_is_set = false;
  private boolean capital_gain_is_modified = false;
  private boolean capital_gain_tax_is_set = false;
  private boolean capital_gain_tax_is_modified = false;
  private boolean modify_cancel_type_is_set = false;
  private boolean modify_cancel_type_is_modified = false;
  private boolean order_root_div_is_set = false;
  private boolean order_root_div_is_modified = false;
  private boolean confirmed_order_price_is_set = false;
  private boolean confirmed_order_price_is_modified = false;
  private boolean confirmed_estimated_price_is_set = false;
  private boolean confirmed_estimated_price_is_modified = false;
  private boolean confirmed_f_estimated_price_is_set = false;
  private boolean confirmed_f_estimated_price_is_modified = false;
  private boolean confirmed_exec_condition_type_is_set = false;
  private boolean confirmed_exec_condition_type_is_modified = false;
  private boolean error_reason_code_is_set = false;
  private boolean error_reason_code_is_modified = false;
  private boolean factor_is_set = false;
  private boolean factor_is_modified = false;
  private boolean order_emp_code_is_set = false;
  private boolean order_emp_code_is_modified = false;
  private boolean account_div_is_set = false;
  private boolean account_div_is_modified = false;
  private boolean exec_end_timestamp_is_set = false;
  private boolean exec_end_timestamp_is_modified = false;
  private boolean exec_file_send_flag_is_set = false;
  private boolean exec_file_send_flag_is_modified = false;
  private boolean first_order_unit_id_is_set = false;
  private boolean first_order_unit_id_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean execution_seq_no_is_set = false;
  private boolean execution_seq_no_is_modified = false;
  private boolean temporary_execution_flag_is_set = false;
  private boolean temporary_execution_flag_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "order_unit_id=" + order_unit_id
      + "," + "institution_code=" +institution_code
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "branch_id=" +branch_id
      + "," + "trader_id=" +trader_id
      + "," + "order_id=" +order_id
      + "," + "order_type=" +order_type
      + "," + "order_categ=" +order_categ
      + "," + "last_order_action_serial_no=" +last_order_action_serial_no
      + "," + "last_execution_serial_no=" +last_execution_serial_no
      + "," + "product_type=" +product_type
      + "," + "market_id=" +market_id
      + "," + "quantity=" +quantity
      + "," + "limit_price=" +limit_price
      + "," + "execution_condition_type=" +execution_condition_type
      + "," + "order_condition_type=" +order_condition_type
      + "," + "order_cond_operator=" +order_cond_operator
      + "," + "stop_order_price=" +stop_order_price
      + "," + "w_limit_price=" +w_limit_price
      + "," + "settle_div=" +settle_div
      + "," + "delivery_date=" +delivery_date
      + "," + "expiration_date=" +expiration_date
      + "," + "confirmed_quantity=" +confirmed_quantity
      + "," + "confirmed_price=" +confirmed_price
      + "," + "executed_quantity=" +executed_quantity
      + "," + "executed_amount=" +executed_amount
      + "," + "executed_amount_yen=" +executed_amount_yen
      + "," + "order_status=" +order_status
      + "," + "order_open_status=" +order_open_status
      + "," + "expiration_status=" +expiration_status
      + "," + "tax_type=" +tax_type
      + "," + "biz_date=" +biz_date
      + "," + "product_id=" +product_id
      + "," + "currency_code=" +currency_code
      + "," + "order_chanel=" +order_chanel
      + "," + "received_date_time=" +received_date_time
      + "," + "voucher_no=" +voucher_no
      + "," + "comm_tbl_no=" +comm_tbl_no
      + "," + "comm_tbl_sub_no=" +comm_tbl_sub_no
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "price=" +price
      + "," + "order_request_number=" +order_request_number
      + "," + "estimated_price=" +estimated_price
      + "," + "f_estimated_price=" +f_estimated_price
      + "," + "sonar_traded_code=" +sonar_traded_code
      + "," + "sonar_market_code=" +sonar_market_code
      + "," + "comm_product_code=" +comm_product_code
      + "," + "capital_gain=" +capital_gain
      + "," + "capital_gain_tax=" +capital_gain_tax
      + "," + "modify_cancel_type=" +modify_cancel_type
      + "," + "order_root_div=" +order_root_div
      + "," + "confirmed_order_price=" +confirmed_order_price
      + "," + "confirmed_estimated_price=" +confirmed_estimated_price
      + "," + "confirmed_f_estimated_price=" +confirmed_f_estimated_price
      + "," + "confirmed_exec_condition_type=" +confirmed_exec_condition_type
      + "," + "error_reason_code=" +error_reason_code
      + "," + "factor=" +factor
      + "," + "order_emp_code=" +order_emp_code
      + "," + "account_div=" +account_div
      + "," + "exec_end_timestamp=" +exec_end_timestamp
      + "," + "exec_file_send_flag=" +exec_file_send_flag
      + "," + "first_order_unit_id=" +first_order_unit_id
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "execution_seq_no=" +execution_seq_no
      + "," + "temporary_execution_flag=" +temporary_execution_flag
      + "}";
  }


  /** 
   * 値が未設定のFeqOrderUnitParamsオブジェクトを作成します。 
   */
  public FeqOrderUnitParams() {
    order_unit_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のFeqOrderUnitRowオブジェクトの値を利用してFeqOrderUnitParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するFeqOrderUnitRowオブジェクト 
   */
  public FeqOrderUnitParams( FeqOrderUnitRow p_row )
  {
    order_unit_id = p_row.getOrderUnitId();
    order_unit_id_is_set = p_row.getOrderUnitIdIsSet();
    order_unit_id_is_modified = p_row.getOrderUnitIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
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
    last_execution_serial_no = p_row.getLastExecutionSerialNo();
    last_execution_serial_no_is_set = p_row.getLastExecutionSerialNoIsSet();
    last_execution_serial_no_is_modified = p_row.getLastExecutionSerialNoIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    if ( !p_row.getMarketIdIsNull() )
      market_id = new Long( p_row.getMarketId() );
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    quantity = p_row.getQuantity();
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    if ( !p_row.getLimitPriceIsNull() )
      limit_price = new Double( p_row.getLimitPrice() );
    limit_price_is_set = p_row.getLimitPriceIsSet();
    limit_price_is_modified = p_row.getLimitPriceIsModified();
    execution_condition_type = p_row.getExecutionConditionType();
    execution_condition_type_is_set = p_row.getExecutionConditionTypeIsSet();
    execution_condition_type_is_modified = p_row.getExecutionConditionTypeIsModified();
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
    settle_div = p_row.getSettleDiv();
    settle_div_is_set = p_row.getSettleDivIsSet();
    settle_div_is_modified = p_row.getSettleDivIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    expiration_date = p_row.getExpirationDate();
    expiration_date_is_set = p_row.getExpirationDateIsSet();
    expiration_date_is_modified = p_row.getExpirationDateIsModified();
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
    if ( !p_row.getExecutedAmountIsNull() )
      executed_amount = new Double( p_row.getExecutedAmount() );
    executed_amount_is_set = p_row.getExecutedAmountIsSet();
    executed_amount_is_modified = p_row.getExecutedAmountIsModified();
    if ( !p_row.getExecutedAmountYenIsNull() )
      executed_amount_yen = new Double( p_row.getExecutedAmountYen() );
    executed_amount_yen_is_set = p_row.getExecutedAmountYenIsSet();
    executed_amount_yen_is_modified = p_row.getExecutedAmountYenIsModified();
    order_status = p_row.getOrderStatus();
    order_status_is_set = p_row.getOrderStatusIsSet();
    order_status_is_modified = p_row.getOrderStatusIsModified();
    order_open_status = p_row.getOrderOpenStatus();
    order_open_status_is_set = p_row.getOrderOpenStatusIsSet();
    order_open_status_is_modified = p_row.getOrderOpenStatusIsModified();
    expiration_status = p_row.getExpirationStatus();
    expiration_status_is_set = p_row.getExpirationStatusIsSet();
    expiration_status_is_modified = p_row.getExpirationStatusIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    biz_date = p_row.getBizDate();
    biz_date_is_set = p_row.getBizDateIsSet();
    biz_date_is_modified = p_row.getBizDateIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    order_chanel = p_row.getOrderChanel();
    order_chanel_is_set = p_row.getOrderChanelIsSet();
    order_chanel_is_modified = p_row.getOrderChanelIsModified();
    received_date_time = p_row.getReceivedDateTime();
    received_date_time_is_set = p_row.getReceivedDateTimeIsSet();
    received_date_time_is_modified = p_row.getReceivedDateTimeIsModified();
    voucher_no = p_row.getVoucherNo();
    voucher_no_is_set = p_row.getVoucherNoIsSet();
    voucher_no_is_modified = p_row.getVoucherNoIsModified();
    comm_tbl_no = p_row.getCommTblNo();
    comm_tbl_no_is_set = p_row.getCommTblNoIsSet();
    comm_tbl_no_is_modified = p_row.getCommTblNoIsModified();
    comm_tbl_sub_no = p_row.getCommTblSubNo();
    comm_tbl_sub_no_is_set = p_row.getCommTblSubNoIsSet();
    comm_tbl_sub_no_is_modified = p_row.getCommTblSubNoIsModified();
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    if ( !p_row.getPriceIsNull() )
      price = new Double( p_row.getPrice() );
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    if ( !p_row.getEstimatedPriceIsNull() )
      estimated_price = new Double( p_row.getEstimatedPrice() );
    estimated_price_is_set = p_row.getEstimatedPriceIsSet();
    estimated_price_is_modified = p_row.getEstimatedPriceIsModified();
    if ( !p_row.getFEstimatedPriceIsNull() )
      f_estimated_price = new Double( p_row.getFEstimatedPrice() );
    f_estimated_price_is_set = p_row.getFEstimatedPriceIsSet();
    f_estimated_price_is_modified = p_row.getFEstimatedPriceIsModified();
    sonar_traded_code = p_row.getSonarTradedCode();
    sonar_traded_code_is_set = p_row.getSonarTradedCodeIsSet();
    sonar_traded_code_is_modified = p_row.getSonarTradedCodeIsModified();
    sonar_market_code = p_row.getSonarMarketCode();
    sonar_market_code_is_set = p_row.getSonarMarketCodeIsSet();
    sonar_market_code_is_modified = p_row.getSonarMarketCodeIsModified();
    comm_product_code = p_row.getCommProductCode();
    comm_product_code_is_set = p_row.getCommProductCodeIsSet();
    comm_product_code_is_modified = p_row.getCommProductCodeIsModified();
    if ( !p_row.getCapitalGainIsNull() )
      capital_gain = new Double( p_row.getCapitalGain() );
    capital_gain_is_set = p_row.getCapitalGainIsSet();
    capital_gain_is_modified = p_row.getCapitalGainIsModified();
    if ( !p_row.getCapitalGainTaxIsNull() )
      capital_gain_tax = new Double( p_row.getCapitalGainTax() );
    capital_gain_tax_is_set = p_row.getCapitalGainTaxIsSet();
    capital_gain_tax_is_modified = p_row.getCapitalGainTaxIsModified();
    modify_cancel_type = p_row.getModifyCancelType();
    modify_cancel_type_is_set = p_row.getModifyCancelTypeIsSet();
    modify_cancel_type_is_modified = p_row.getModifyCancelTypeIsModified();
    order_root_div = p_row.getOrderRootDiv();
    order_root_div_is_set = p_row.getOrderRootDivIsSet();
    order_root_div_is_modified = p_row.getOrderRootDivIsModified();
    if ( !p_row.getConfirmedOrderPriceIsNull() )
      confirmed_order_price = new Double( p_row.getConfirmedOrderPrice() );
    confirmed_order_price_is_set = p_row.getConfirmedOrderPriceIsSet();
    confirmed_order_price_is_modified = p_row.getConfirmedOrderPriceIsModified();
    if ( !p_row.getConfirmedEstimatedPriceIsNull() )
      confirmed_estimated_price = new Double( p_row.getConfirmedEstimatedPrice() );
    confirmed_estimated_price_is_set = p_row.getConfirmedEstimatedPriceIsSet();
    confirmed_estimated_price_is_modified = p_row.getConfirmedEstimatedPriceIsModified();
    if ( !p_row.getConfirmedFEstimatedPriceIsNull() )
      confirmed_f_estimated_price = new Double( p_row.getConfirmedFEstimatedPrice() );
    confirmed_f_estimated_price_is_set = p_row.getConfirmedFEstimatedPriceIsSet();
    confirmed_f_estimated_price_is_modified = p_row.getConfirmedFEstimatedPriceIsModified();
    confirmed_exec_condition_type = p_row.getConfirmedExecConditionType();
    confirmed_exec_condition_type_is_set = p_row.getConfirmedExecConditionTypeIsSet();
    confirmed_exec_condition_type_is_modified = p_row.getConfirmedExecConditionTypeIsModified();
    error_reason_code = p_row.getErrorReasonCode();
    error_reason_code_is_set = p_row.getErrorReasonCodeIsSet();
    error_reason_code_is_modified = p_row.getErrorReasonCodeIsModified();
    factor = p_row.getFactor();
    factor_is_set = p_row.getFactorIsSet();
    factor_is_modified = p_row.getFactorIsModified();
    order_emp_code = p_row.getOrderEmpCode();
    order_emp_code_is_set = p_row.getOrderEmpCodeIsSet();
    order_emp_code_is_modified = p_row.getOrderEmpCodeIsModified();
    account_div = p_row.getAccountDiv();
    account_div_is_set = p_row.getAccountDivIsSet();
    account_div_is_modified = p_row.getAccountDivIsModified();
    exec_end_timestamp = p_row.getExecEndTimestamp();
    exec_end_timestamp_is_set = p_row.getExecEndTimestampIsSet();
    exec_end_timestamp_is_modified = p_row.getExecEndTimestampIsModified();
    exec_file_send_flag = p_row.getExecFileSendFlag();
    exec_file_send_flag_is_set = p_row.getExecFileSendFlagIsSet();
    exec_file_send_flag_is_modified = p_row.getExecFileSendFlagIsModified();
    if ( !p_row.getFirstOrderUnitIdIsNull() )
      first_order_unit_id = new Long( p_row.getFirstOrderUnitId() );
    first_order_unit_id_is_set = p_row.getFirstOrderUnitIdIsSet();
    first_order_unit_id_is_modified = p_row.getFirstOrderUnitIdIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    if ( !p_row.getExecutionSeqNoIsNull() )
      execution_seq_no = new Integer( p_row.getExecutionSeqNo() );
    execution_seq_no_is_set = p_row.getExecutionSeqNoIsSet();
    execution_seq_no_is_modified = p_row.getExecutionSeqNoIsModified();
    temporary_execution_flag = p_row.getTemporaryExecutionFlag();
    temporary_execution_flag_is_set = p_row.getTemporaryExecutionFlagIsSet();
    temporary_execution_flag_is_modified = p_row.getTemporaryExecutionFlagIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
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
      last_execution_serial_no_is_set = true;
      last_execution_serial_no_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      limit_price_is_set = true;
      limit_price_is_modified = true;
      execution_condition_type_is_set = true;
      execution_condition_type_is_modified = true;
      order_condition_type_is_set = true;
      order_condition_type_is_modified = true;
      order_cond_operator_is_set = true;
      order_cond_operator_is_modified = true;
      stop_order_price_is_set = true;
      stop_order_price_is_modified = true;
      w_limit_price_is_set = true;
      w_limit_price_is_modified = true;
      settle_div_is_set = true;
      settle_div_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      expiration_date_is_set = true;
      expiration_date_is_modified = true;
      confirmed_quantity_is_set = true;
      confirmed_quantity_is_modified = true;
      confirmed_price_is_set = true;
      confirmed_price_is_modified = true;
      executed_quantity_is_set = true;
      executed_quantity_is_modified = true;
      executed_amount_is_set = true;
      executed_amount_is_modified = true;
      executed_amount_yen_is_set = true;
      executed_amount_yen_is_modified = true;
      order_status_is_set = true;
      order_status_is_modified = true;
      order_open_status_is_set = true;
      order_open_status_is_modified = true;
      expiration_status_is_set = true;
      expiration_status_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      biz_date_is_set = true;
      biz_date_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      currency_code_is_set = true;
      currency_code_is_modified = true;
      order_chanel_is_set = true;
      order_chanel_is_modified = true;
      received_date_time_is_set = true;
      received_date_time_is_modified = true;
      voucher_no_is_set = true;
      voucher_no_is_modified = true;
      comm_tbl_no_is_set = true;
      comm_tbl_no_is_modified = true;
      comm_tbl_sub_no_is_set = true;
      comm_tbl_sub_no_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      estimated_price_is_set = true;
      estimated_price_is_modified = true;
      f_estimated_price_is_set = true;
      f_estimated_price_is_modified = true;
      sonar_traded_code_is_set = true;
      sonar_traded_code_is_modified = true;
      sonar_market_code_is_set = true;
      sonar_market_code_is_modified = true;
      comm_product_code_is_set = true;
      comm_product_code_is_modified = true;
      capital_gain_is_set = true;
      capital_gain_is_modified = true;
      capital_gain_tax_is_set = true;
      capital_gain_tax_is_modified = true;
      modify_cancel_type_is_set = true;
      modify_cancel_type_is_modified = true;
      order_root_div_is_set = true;
      order_root_div_is_modified = true;
      confirmed_order_price_is_set = true;
      confirmed_order_price_is_modified = true;
      confirmed_estimated_price_is_set = true;
      confirmed_estimated_price_is_modified = true;
      confirmed_f_estimated_price_is_set = true;
      confirmed_f_estimated_price_is_modified = true;
      confirmed_exec_condition_type_is_set = true;
      confirmed_exec_condition_type_is_modified = true;
      error_reason_code_is_set = true;
      error_reason_code_is_modified = true;
      factor_is_set = true;
      factor_is_modified = true;
      order_emp_code_is_set = true;
      order_emp_code_is_modified = true;
      account_div_is_set = true;
      account_div_is_modified = true;
      exec_end_timestamp_is_set = true;
      exec_end_timestamp_is_modified = true;
      exec_file_send_flag_is_set = true;
      exec_file_send_flag_is_modified = true;
      first_order_unit_id_is_set = true;
      first_order_unit_id_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      execution_seq_no_is_set = true;
      execution_seq_no_is_modified = true;
      temporary_execution_flag_is_set = true;
      temporary_execution_flag_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof FeqOrderUnitRow ) )
       return false;
    return fieldsEqual( (FeqOrderUnitRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のFeqOrderUnitRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( FeqOrderUnitRow row )
  {
    if ( order_unit_id != row.getOrderUnitId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
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
    if ( last_execution_serial_no != row.getLastExecutionSerialNo() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( market_id == null ) {
      if ( !row.getMarketIdIsNull() )
        return false;
    } else if ( row.getMarketIdIsNull() || ( market_id.longValue() != row.getMarketId() ) ) {
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
    if ( execution_condition_type == null ) {
      if ( row.getExecutionConditionType() != null )
        return false;
    } else if ( !execution_condition_type.equals( row.getExecutionConditionType() ) ) {
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
    if ( settle_div == null ) {
      if ( row.getSettleDiv() != null )
        return false;
    } else if ( !settle_div.equals( row.getSettleDiv() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( expiration_date == null ) {
      if ( row.getExpirationDate() != null )
        return false;
    } else if ( !expiration_date.equals( row.getExpirationDate() ) ) {
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
    if ( executed_amount == null ) {
      if ( !row.getExecutedAmountIsNull() )
        return false;
    } else if ( row.getExecutedAmountIsNull() || ( executed_amount.doubleValue() != row.getExecutedAmount() ) ) {
        return false;
    }
    if ( executed_amount_yen == null ) {
      if ( !row.getExecutedAmountYenIsNull() )
        return false;
    } else if ( row.getExecutedAmountYenIsNull() || ( executed_amount_yen.doubleValue() != row.getExecutedAmountYen() ) ) {
        return false;
    }
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
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( biz_date == null ) {
      if ( row.getBizDate() != null )
        return false;
    } else if ( !biz_date.equals( row.getBizDate() ) ) {
        return false;
    }
    if ( product_id != row.getProductId() )
      return false;
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( order_chanel == null ) {
      if ( row.getOrderChanel() != null )
        return false;
    } else if ( !order_chanel.equals( row.getOrderChanel() ) ) {
        return false;
    }
    if ( received_date_time == null ) {
      if ( row.getReceivedDateTime() != null )
        return false;
    } else if ( !received_date_time.equals( row.getReceivedDateTime() ) ) {
        return false;
    }
    if ( voucher_no == null ) {
      if ( row.getVoucherNo() != null )
        return false;
    } else if ( !voucher_no.equals( row.getVoucherNo() ) ) {
        return false;
    }
    if ( comm_tbl_no == null ) {
      if ( row.getCommTblNo() != null )
        return false;
    } else if ( !comm_tbl_no.equals( row.getCommTblNo() ) ) {
        return false;
    }
    if ( comm_tbl_sub_no == null ) {
      if ( row.getCommTblSubNo() != null )
        return false;
    } else if ( !comm_tbl_sub_no.equals( row.getCommTblSubNo() ) ) {
        return false;
    }
    if ( sonar_trader_code == null ) {
      if ( row.getSonarTraderCode() != null )
        return false;
    } else if ( !sonar_trader_code.equals( row.getSonarTraderCode() ) ) {
        return false;
    }
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.doubleValue() != row.getPrice() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( estimated_price == null ) {
      if ( !row.getEstimatedPriceIsNull() )
        return false;
    } else if ( row.getEstimatedPriceIsNull() || ( estimated_price.doubleValue() != row.getEstimatedPrice() ) ) {
        return false;
    }
    if ( f_estimated_price == null ) {
      if ( !row.getFEstimatedPriceIsNull() )
        return false;
    } else if ( row.getFEstimatedPriceIsNull() || ( f_estimated_price.doubleValue() != row.getFEstimatedPrice() ) ) {
        return false;
    }
    if ( sonar_traded_code == null ) {
      if ( row.getSonarTradedCode() != null )
        return false;
    } else if ( !sonar_traded_code.equals( row.getSonarTradedCode() ) ) {
        return false;
    }
    if ( sonar_market_code == null ) {
      if ( row.getSonarMarketCode() != null )
        return false;
    } else if ( !sonar_market_code.equals( row.getSonarMarketCode() ) ) {
        return false;
    }
    if ( comm_product_code == null ) {
      if ( row.getCommProductCode() != null )
        return false;
    } else if ( !comm_product_code.equals( row.getCommProductCode() ) ) {
        return false;
    }
    if ( capital_gain == null ) {
      if ( !row.getCapitalGainIsNull() )
        return false;
    } else if ( row.getCapitalGainIsNull() || ( capital_gain.doubleValue() != row.getCapitalGain() ) ) {
        return false;
    }
    if ( capital_gain_tax == null ) {
      if ( !row.getCapitalGainTaxIsNull() )
        return false;
    } else if ( row.getCapitalGainTaxIsNull() || ( capital_gain_tax.doubleValue() != row.getCapitalGainTax() ) ) {
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
    if ( confirmed_order_price == null ) {
      if ( !row.getConfirmedOrderPriceIsNull() )
        return false;
    } else if ( row.getConfirmedOrderPriceIsNull() || ( confirmed_order_price.doubleValue() != row.getConfirmedOrderPrice() ) ) {
        return false;
    }
    if ( confirmed_estimated_price == null ) {
      if ( !row.getConfirmedEstimatedPriceIsNull() )
        return false;
    } else if ( row.getConfirmedEstimatedPriceIsNull() || ( confirmed_estimated_price.doubleValue() != row.getConfirmedEstimatedPrice() ) ) {
        return false;
    }
    if ( confirmed_f_estimated_price == null ) {
      if ( !row.getConfirmedFEstimatedPriceIsNull() )
        return false;
    } else if ( row.getConfirmedFEstimatedPriceIsNull() || ( confirmed_f_estimated_price.doubleValue() != row.getConfirmedFEstimatedPrice() ) ) {
        return false;
    }
    if ( confirmed_exec_condition_type == null ) {
      if ( row.getConfirmedExecConditionType() != null )
        return false;
    } else if ( !confirmed_exec_condition_type.equals( row.getConfirmedExecConditionType() ) ) {
        return false;
    }
    if ( error_reason_code == null ) {
      if ( row.getErrorReasonCode() != null )
        return false;
    } else if ( !error_reason_code.equals( row.getErrorReasonCode() ) ) {
        return false;
    }
    if ( factor == null ) {
      if ( row.getFactor() != null )
        return false;
    } else if ( !factor.equals( row.getFactor() ) ) {
        return false;
    }
    if ( order_emp_code == null ) {
      if ( row.getOrderEmpCode() != null )
        return false;
    } else if ( !order_emp_code.equals( row.getOrderEmpCode() ) ) {
        return false;
    }
    if ( account_div == null ) {
      if ( row.getAccountDiv() != null )
        return false;
    } else if ( !account_div.equals( row.getAccountDiv() ) ) {
        return false;
    }
    if ( exec_end_timestamp == null ) {
      if ( row.getExecEndTimestamp() != null )
        return false;
    } else if ( !exec_end_timestamp.equals( row.getExecEndTimestamp() ) ) {
        return false;
    }
    if ( exec_file_send_flag == null ) {
      if ( row.getExecFileSendFlag() != null )
        return false;
    } else if ( !exec_file_send_flag.equals( row.getExecFileSendFlag() ) ) {
        return false;
    }
    if ( first_order_unit_id == null ) {
      if ( !row.getFirstOrderUnitIdIsNull() )
        return false;
    } else if ( row.getFirstOrderUnitIdIsNull() || ( first_order_unit_id.longValue() != row.getFirstOrderUnitId() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
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
    if ( execution_seq_no == null ) {
      if ( !row.getExecutionSeqNoIsNull() )
        return false;
    } else if ( row.getExecutionSeqNoIsNull() || ( execution_seq_no.intValue() != row.getExecutionSeqNo() ) ) {
        return false;
    }
    if ( temporary_execution_flag == null ) {
      if ( row.getTemporaryExecutionFlag() != null )
        return false;
    } else if ( !temporary_execution_flag.equals( row.getTemporaryExecutionFlag() ) ) {
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
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) branch_id)
        + (trader_id!=null? trader_id.hashCode(): 0) 
        + ((int) order_id)
        + (order_type!=null? order_type.hashCode(): 0) 
        + (order_categ!=null? order_categ.hashCode(): 0) 
        + ((int) last_order_action_serial_no)
        + ((int) last_execution_serial_no)
        + (product_type!=null? product_type.hashCode(): 0) 
        + (market_id!=null? market_id.hashCode(): 0) 
        + ((int) quantity)
        + (limit_price!=null? limit_price.hashCode(): 0) 
        + (execution_condition_type!=null? execution_condition_type.hashCode(): 0) 
        + (order_condition_type!=null? order_condition_type.hashCode(): 0) 
        + (order_cond_operator!=null? order_cond_operator.hashCode(): 0) 
        + (stop_order_price!=null? stop_order_price.hashCode(): 0) 
        + (w_limit_price!=null? w_limit_price.hashCode(): 0) 
        + (settle_div!=null? settle_div.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (expiration_date!=null? expiration_date.hashCode(): 0) 
        + (confirmed_quantity!=null? confirmed_quantity.hashCode(): 0) 
        + (confirmed_price!=null? confirmed_price.hashCode(): 0) 
        + (executed_quantity!=null? executed_quantity.hashCode(): 0) 
        + (executed_amount!=null? executed_amount.hashCode(): 0) 
        + (executed_amount_yen!=null? executed_amount_yen.hashCode(): 0) 
        + (order_status!=null? order_status.hashCode(): 0) 
        + (order_open_status!=null? order_open_status.hashCode(): 0) 
        + (expiration_status!=null? expiration_status.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
        + ((int) product_id)
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (received_date_time!=null? received_date_time.hashCode(): 0) 
        + (voucher_no!=null? voucher_no.hashCode(): 0) 
        + (comm_tbl_no!=null? comm_tbl_no.hashCode(): 0) 
        + (comm_tbl_sub_no!=null? comm_tbl_sub_no.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (estimated_price!=null? estimated_price.hashCode(): 0) 
        + (f_estimated_price!=null? f_estimated_price.hashCode(): 0) 
        + (sonar_traded_code!=null? sonar_traded_code.hashCode(): 0) 
        + (sonar_market_code!=null? sonar_market_code.hashCode(): 0) 
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (capital_gain!=null? capital_gain.hashCode(): 0) 
        + (capital_gain_tax!=null? capital_gain_tax.hashCode(): 0) 
        + (modify_cancel_type!=null? modify_cancel_type.hashCode(): 0) 
        + (order_root_div!=null? order_root_div.hashCode(): 0) 
        + (confirmed_order_price!=null? confirmed_order_price.hashCode(): 0) 
        + (confirmed_estimated_price!=null? confirmed_estimated_price.hashCode(): 0) 
        + (confirmed_f_estimated_price!=null? confirmed_f_estimated_price.hashCode(): 0) 
        + (confirmed_exec_condition_type!=null? confirmed_exec_condition_type.hashCode(): 0) 
        + (error_reason_code!=null? error_reason_code.hashCode(): 0) 
        + (factor!=null? factor.hashCode(): 0) 
        + (order_emp_code!=null? order_emp_code.hashCode(): 0) 
        + (account_div!=null? account_div.hashCode(): 0) 
        + (exec_end_timestamp!=null? exec_end_timestamp.hashCode(): 0) 
        + (exec_file_send_flag!=null? exec_file_send_flag.hashCode(): 0) 
        + (first_order_unit_id!=null? first_order_unit_id.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (execution_seq_no!=null? execution_seq_no.hashCode(): 0) 
        + (temporary_execution_flag!=null? temporary_execution_flag.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
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
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
		if ( !delivery_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_date' must be set before inserting.");
		if ( !expiration_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'expiration_status' must be set before inserting.");
		if ( !tax_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'tax_type' must be set before inserting.");
		if ( !biz_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'biz_date' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !currency_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'currency_code' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("order_unit_id",new Long(order_unit_id));
		map.put("institution_code",institution_code);
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("branch_id",new Long(branch_id));
		if ( trader_id != null )
			map.put("trader_id",trader_id);
		map.put("order_id",new Long(order_id));
		map.put("order_type",order_type);
		map.put("order_categ",order_categ);
		if ( last_order_action_serial_no_is_set )
			map.put("last_order_action_serial_no",new Integer(last_order_action_serial_no));
		if ( last_execution_serial_no_is_set )
			map.put("last_execution_serial_no",new Integer(last_execution_serial_no));
		map.put("product_type",product_type);
		if ( market_id != null )
			map.put("market_id",market_id);
		map.put("quantity",new Double(quantity));
		if ( limit_price != null )
			map.put("limit_price",limit_price);
		if ( execution_condition_type != null )
			map.put("execution_condition_type",execution_condition_type);
		if ( order_condition_type != null )
			map.put("order_condition_type",order_condition_type);
		if ( order_cond_operator != null )
			map.put("order_cond_operator",order_cond_operator);
		if ( stop_order_price != null )
			map.put("stop_order_price",stop_order_price);
		if ( w_limit_price != null )
			map.put("w_limit_price",w_limit_price);
		if ( settle_div != null )
			map.put("settle_div",settle_div);
		map.put("delivery_date",delivery_date);
		if ( expiration_date != null )
			map.put("expiration_date",expiration_date);
		if ( confirmed_quantity != null )
			map.put("confirmed_quantity",confirmed_quantity);
		if ( confirmed_price != null )
			map.put("confirmed_price",confirmed_price);
		if ( executed_quantity != null )
			map.put("executed_quantity",executed_quantity);
		if ( executed_amount != null )
			map.put("executed_amount",executed_amount);
		if ( executed_amount_yen != null )
			map.put("executed_amount_yen",executed_amount_yen);
		if ( order_status_is_set )
			map.put("order_status",order_status);
		if ( order_open_status_is_set )
			map.put("order_open_status",order_open_status);
		map.put("expiration_status",expiration_status);
		map.put("tax_type",tax_type);
		map.put("biz_date",biz_date);
		map.put("product_id",new Long(product_id));
		map.put("currency_code",currency_code);
		if ( order_chanel != null )
			map.put("order_chanel",order_chanel);
		if ( received_date_time != null )
			map.put("received_date_time",received_date_time);
		if ( voucher_no != null )
			map.put("voucher_no",voucher_no);
		if ( comm_tbl_no != null )
			map.put("comm_tbl_no",comm_tbl_no);
		if ( comm_tbl_sub_no != null )
			map.put("comm_tbl_sub_no",comm_tbl_sub_no);
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( price != null )
			map.put("price",price);
		if ( order_request_number != null )
			map.put("order_request_number",order_request_number);
		if ( estimated_price != null )
			map.put("estimated_price",estimated_price);
		if ( f_estimated_price != null )
			map.put("f_estimated_price",f_estimated_price);
		if ( sonar_traded_code != null )
			map.put("sonar_traded_code",sonar_traded_code);
		if ( sonar_market_code != null )
			map.put("sonar_market_code",sonar_market_code);
		if ( comm_product_code != null )
			map.put("comm_product_code",comm_product_code);
		if ( capital_gain != null )
			map.put("capital_gain",capital_gain);
		if ( capital_gain_tax != null )
			map.put("capital_gain_tax",capital_gain_tax);
		if ( modify_cancel_type != null )
			map.put("modify_cancel_type",modify_cancel_type);
		if ( order_root_div != null )
			map.put("order_root_div",order_root_div);
		if ( confirmed_order_price != null )
			map.put("confirmed_order_price",confirmed_order_price);
		if ( confirmed_estimated_price != null )
			map.put("confirmed_estimated_price",confirmed_estimated_price);
		if ( confirmed_f_estimated_price != null )
			map.put("confirmed_f_estimated_price",confirmed_f_estimated_price);
		if ( confirmed_exec_condition_type != null )
			map.put("confirmed_exec_condition_type",confirmed_exec_condition_type);
		if ( error_reason_code != null )
			map.put("error_reason_code",error_reason_code);
		if ( factor != null )
			map.put("factor",factor);
		if ( order_emp_code != null )
			map.put("order_emp_code",order_emp_code);
		if ( account_div != null )
			map.put("account_div",account_div);
		if ( exec_end_timestamp != null )
			map.put("exec_end_timestamp",exec_end_timestamp);
		if ( exec_file_send_flag != null )
			map.put("exec_file_send_flag",exec_file_send_flag);
		if ( first_order_unit_id != null )
			map.put("first_order_unit_id",first_order_unit_id);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( execution_seq_no_is_set )
			map.put("execution_seq_no",execution_seq_no);
		if ( temporary_execution_flag_is_set )
			map.put("temporary_execution_flag",temporary_execution_flag);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
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
		if ( last_execution_serial_no_is_modified )
			map.put("last_execution_serial_no",new Integer(last_execution_serial_no));
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( market_id_is_modified )
			map.put("market_id",market_id);
		if ( quantity_is_modified )
			map.put("quantity",new Double(quantity));
		if ( limit_price_is_modified )
			map.put("limit_price",limit_price);
		if ( execution_condition_type_is_modified )
			map.put("execution_condition_type",execution_condition_type);
		if ( order_condition_type_is_modified )
			map.put("order_condition_type",order_condition_type);
		if ( order_cond_operator_is_modified )
			map.put("order_cond_operator",order_cond_operator);
		if ( stop_order_price_is_modified )
			map.put("stop_order_price",stop_order_price);
		if ( w_limit_price_is_modified )
			map.put("w_limit_price",w_limit_price);
		if ( settle_div_is_modified )
			map.put("settle_div",settle_div);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( expiration_date_is_modified )
			map.put("expiration_date",expiration_date);
		if ( confirmed_quantity_is_modified )
			map.put("confirmed_quantity",confirmed_quantity);
		if ( confirmed_price_is_modified )
			map.put("confirmed_price",confirmed_price);
		if ( executed_quantity_is_modified )
			map.put("executed_quantity",executed_quantity);
		if ( executed_amount_is_modified )
			map.put("executed_amount",executed_amount);
		if ( executed_amount_yen_is_modified )
			map.put("executed_amount_yen",executed_amount_yen);
		if ( order_status_is_modified )
			map.put("order_status",order_status);
		if ( order_open_status_is_modified )
			map.put("order_open_status",order_open_status);
		if ( expiration_status_is_modified )
			map.put("expiration_status",expiration_status);
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( biz_date_is_modified )
			map.put("biz_date",biz_date);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( currency_code_is_modified )
			map.put("currency_code",currency_code);
		if ( order_chanel_is_modified )
			map.put("order_chanel",order_chanel);
		if ( received_date_time_is_modified )
			map.put("received_date_time",received_date_time);
		if ( voucher_no_is_modified )
			map.put("voucher_no",voucher_no);
		if ( comm_tbl_no_is_modified )
			map.put("comm_tbl_no",comm_tbl_no);
		if ( comm_tbl_sub_no_is_modified )
			map.put("comm_tbl_sub_no",comm_tbl_sub_no);
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( price_is_modified )
			map.put("price",price);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( estimated_price_is_modified )
			map.put("estimated_price",estimated_price);
		if ( f_estimated_price_is_modified )
			map.put("f_estimated_price",f_estimated_price);
		if ( sonar_traded_code_is_modified )
			map.put("sonar_traded_code",sonar_traded_code);
		if ( sonar_market_code_is_modified )
			map.put("sonar_market_code",sonar_market_code);
		if ( comm_product_code_is_modified )
			map.put("comm_product_code",comm_product_code);
		if ( capital_gain_is_modified )
			map.put("capital_gain",capital_gain);
		if ( capital_gain_tax_is_modified )
			map.put("capital_gain_tax",capital_gain_tax);
		if ( modify_cancel_type_is_modified )
			map.put("modify_cancel_type",modify_cancel_type);
		if ( order_root_div_is_modified )
			map.put("order_root_div",order_root_div);
		if ( confirmed_order_price_is_modified )
			map.put("confirmed_order_price",confirmed_order_price);
		if ( confirmed_estimated_price_is_modified )
			map.put("confirmed_estimated_price",confirmed_estimated_price);
		if ( confirmed_f_estimated_price_is_modified )
			map.put("confirmed_f_estimated_price",confirmed_f_estimated_price);
		if ( confirmed_exec_condition_type_is_modified )
			map.put("confirmed_exec_condition_type",confirmed_exec_condition_type);
		if ( error_reason_code_is_modified )
			map.put("error_reason_code",error_reason_code);
		if ( factor_is_modified )
			map.put("factor",factor);
		if ( order_emp_code_is_modified )
			map.put("order_emp_code",order_emp_code);
		if ( account_div_is_modified )
			map.put("account_div",account_div);
		if ( exec_end_timestamp_is_modified )
			map.put("exec_end_timestamp",exec_end_timestamp);
		if ( exec_file_send_flag_is_modified )
			map.put("exec_file_send_flag",exec_file_send_flag);
		if ( first_order_unit_id_is_modified )
			map.put("first_order_unit_id",first_order_unit_id);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( execution_seq_no_is_modified )
			map.put("execution_seq_no",execution_seq_no);
		if ( temporary_execution_flag_is_modified )
			map.put("temporary_execution_flag",temporary_execution_flag);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
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
			if ( last_execution_serial_no_is_set )
				map.put("last_execution_serial_no",new Integer(last_execution_serial_no));
			if ( product_type_is_set )
				map.put("product_type",product_type);
			map.put("market_id",market_id);
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			map.put("limit_price",limit_price);
			map.put("execution_condition_type",execution_condition_type);
			map.put("order_condition_type",order_condition_type);
			map.put("order_cond_operator",order_cond_operator);
			map.put("stop_order_price",stop_order_price);
			map.put("w_limit_price",w_limit_price);
			map.put("settle_div",settle_div);
			if ( delivery_date_is_set )
				map.put("delivery_date",delivery_date);
			map.put("expiration_date",expiration_date);
			map.put("confirmed_quantity",confirmed_quantity);
			map.put("confirmed_price",confirmed_price);
			map.put("executed_quantity",executed_quantity);
			map.put("executed_amount",executed_amount);
			map.put("executed_amount_yen",executed_amount_yen);
			if ( order_status_is_set )
				map.put("order_status",order_status);
			if ( order_open_status_is_set )
				map.put("order_open_status",order_open_status);
			if ( expiration_status_is_set )
				map.put("expiration_status",expiration_status);
			if ( tax_type_is_set )
				map.put("tax_type",tax_type);
			if ( biz_date_is_set )
				map.put("biz_date",biz_date);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( currency_code_is_set )
				map.put("currency_code",currency_code);
			map.put("order_chanel",order_chanel);
			map.put("received_date_time",received_date_time);
			map.put("voucher_no",voucher_no);
			map.put("comm_tbl_no",comm_tbl_no);
			map.put("comm_tbl_sub_no",comm_tbl_sub_no);
			map.put("sonar_trader_code",sonar_trader_code);
			map.put("price",price);
			map.put("order_request_number",order_request_number);
			map.put("estimated_price",estimated_price);
			map.put("f_estimated_price",f_estimated_price);
			map.put("sonar_traded_code",sonar_traded_code);
			map.put("sonar_market_code",sonar_market_code);
			map.put("comm_product_code",comm_product_code);
			map.put("capital_gain",capital_gain);
			map.put("capital_gain_tax",capital_gain_tax);
			map.put("modify_cancel_type",modify_cancel_type);
			map.put("order_root_div",order_root_div);
			map.put("confirmed_order_price",confirmed_order_price);
			map.put("confirmed_estimated_price",confirmed_estimated_price);
			map.put("confirmed_f_estimated_price",confirmed_f_estimated_price);
			map.put("confirmed_exec_condition_type",confirmed_exec_condition_type);
			map.put("error_reason_code",error_reason_code);
			map.put("factor",factor);
			map.put("order_emp_code",order_emp_code);
			map.put("account_div",account_div);
			map.put("exec_end_timestamp",exec_end_timestamp);
			map.put("exec_file_send_flag",exec_file_send_flag);
			map.put("first_order_unit_id",first_order_unit_id);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( execution_seq_no_is_set )
				map.put("execution_seq_no",execution_seq_no);
			if ( temporary_execution_flag_is_set )
				map.put("temporary_execution_flag",temporary_execution_flag);
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
   * <em>last_execution_serial_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLastExecutionSerialNo()
  {
    return last_execution_serial_no;
  }


  /** 
   * <em>last_execution_serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastExecutionSerialNoIsSet() {
    return last_execution_serial_no_is_set;
  }


  /** 
   * <em>last_execution_serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastExecutionSerialNoIsModified() {
    return last_execution_serial_no_is_modified;
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
   * <em>execution_condition_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionTypeの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType getExecutionConditionType()
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
   * <em>settle_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSettleDiv()
  {
    return settle_div;
  }


  /** 
   * <em>settle_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettleDivIsSet() {
    return settle_div_is_set;
  }


  /** 
   * <em>settle_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettleDivIsModified() {
    return settle_div_is_modified;
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
   * <em>executed_amount_yen</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExecutedAmountYen()
  {
    return ( executed_amount_yen==null? ((double)0): executed_amount_yen.doubleValue() );
  }


  /** 
   * <em>executed_amount_yen</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExecutedAmountYenIsNull()
  {
    return executed_amount_yen == null;
  }


  /** 
   * <em>executed_amount_yen</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedAmountYenIsSet() {
    return executed_amount_yen_is_set;
  }


  /** 
   * <em>executed_amount_yen</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedAmountYenIsModified() {
    return executed_amount_yen_is_modified;
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
   * <em>order_chanel</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderChanel()
  {
    return order_chanel;
  }


  /** 
   * <em>order_chanel</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderChanelIsSet() {
    return order_chanel_is_set;
  }


  /** 
   * <em>order_chanel</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderChanelIsModified() {
    return order_chanel_is_modified;
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
   * <em>voucher_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getVoucherNo()
  {
    return voucher_no;
  }


  /** 
   * <em>voucher_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVoucherNoIsSet() {
    return voucher_no_is_set;
  }


  /** 
   * <em>voucher_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVoucherNoIsModified() {
    return voucher_no_is_modified;
  }


  /** 
   * <em>comm_tbl_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommTblNo()
  {
    return comm_tbl_no;
  }


  /** 
   * <em>comm_tbl_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommTblNoIsSet() {
    return comm_tbl_no_is_set;
  }


  /** 
   * <em>comm_tbl_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommTblNoIsModified() {
    return comm_tbl_no_is_modified;
  }


  /** 
   * <em>comm_tbl_sub_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommTblSubNo()
  {
    return comm_tbl_sub_no;
  }


  /** 
   * <em>comm_tbl_sub_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommTblSubNoIsSet() {
    return comm_tbl_sub_no_is_set;
  }


  /** 
   * <em>comm_tbl_sub_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommTblSubNoIsModified() {
    return comm_tbl_sub_no_is_modified;
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
   * <em>f_estimated_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getFEstimatedPrice()
  {
    return ( f_estimated_price==null? ((double)0): f_estimated_price.doubleValue() );
  }


  /** 
   * <em>f_estimated_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFEstimatedPriceIsNull()
  {
    return f_estimated_price == null;
  }


  /** 
   * <em>f_estimated_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFEstimatedPriceIsSet() {
    return f_estimated_price_is_set;
  }


  /** 
   * <em>f_estimated_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFEstimatedPriceIsModified() {
    return f_estimated_price_is_modified;
  }


  /** 
   * <em>sonar_traded_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarTradedCode()
  {
    return sonar_traded_code;
  }


  /** 
   * <em>sonar_traded_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTradedCodeIsSet() {
    return sonar_traded_code_is_set;
  }


  /** 
   * <em>sonar_traded_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTradedCodeIsModified() {
    return sonar_traded_code_is_modified;
  }


  /** 
   * <em>sonar_market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarMarketCode()
  {
    return sonar_market_code;
  }


  /** 
   * <em>sonar_market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarMarketCodeIsSet() {
    return sonar_market_code_is_set;
  }


  /** 
   * <em>sonar_market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarMarketCodeIsModified() {
    return sonar_market_code_is_modified;
  }


  /** 
   * <em>comm_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommProductCode()
  {
    return comm_product_code;
  }


  /** 
   * <em>comm_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommProductCodeIsSet() {
    return comm_product_code_is_set;
  }


  /** 
   * <em>comm_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommProductCodeIsModified() {
    return comm_product_code_is_modified;
  }


  /** 
   * <em>capital_gain</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCapitalGain()
  {
    return ( capital_gain==null? ((double)0): capital_gain.doubleValue() );
  }


  /** 
   * <em>capital_gain</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCapitalGainIsNull()
  {
    return capital_gain == null;
  }


  /** 
   * <em>capital_gain</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainIsSet() {
    return capital_gain_is_set;
  }


  /** 
   * <em>capital_gain</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainIsModified() {
    return capital_gain_is_modified;
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
   * <em>confirmed_order_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getConfirmedOrderPrice()
  {
    return ( confirmed_order_price==null? ((double)0): confirmed_order_price.doubleValue() );
  }


  /** 
   * <em>confirmed_order_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getConfirmedOrderPriceIsNull()
  {
    return confirmed_order_price == null;
  }


  /** 
   * <em>confirmed_order_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedOrderPriceIsSet() {
    return confirmed_order_price_is_set;
  }


  /** 
   * <em>confirmed_order_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedOrderPriceIsModified() {
    return confirmed_order_price_is_modified;
  }


  /** 
   * <em>confirmed_estimated_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getConfirmedEstimatedPrice()
  {
    return ( confirmed_estimated_price==null? ((double)0): confirmed_estimated_price.doubleValue() );
  }


  /** 
   * <em>confirmed_estimated_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getConfirmedEstimatedPriceIsNull()
  {
    return confirmed_estimated_price == null;
  }


  /** 
   * <em>confirmed_estimated_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedEstimatedPriceIsSet() {
    return confirmed_estimated_price_is_set;
  }


  /** 
   * <em>confirmed_estimated_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedEstimatedPriceIsModified() {
    return confirmed_estimated_price_is_modified;
  }


  /** 
   * <em>confirmed_f_estimated_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getConfirmedFEstimatedPrice()
  {
    return ( confirmed_f_estimated_price==null? ((double)0): confirmed_f_estimated_price.doubleValue() );
  }


  /** 
   * <em>confirmed_f_estimated_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getConfirmedFEstimatedPriceIsNull()
  {
    return confirmed_f_estimated_price == null;
  }


  /** 
   * <em>confirmed_f_estimated_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedFEstimatedPriceIsSet() {
    return confirmed_f_estimated_price_is_set;
  }


  /** 
   * <em>confirmed_f_estimated_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedFEstimatedPriceIsModified() {
    return confirmed_f_estimated_price_is_modified;
  }


  /** 
   * <em>confirmed_exec_condition_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionTypeの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType getConfirmedExecConditionType()
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
   * <em>factor</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFactor()
  {
    return factor;
  }


  /** 
   * <em>factor</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFactorIsSet() {
    return factor_is_set;
  }


  /** 
   * <em>factor</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFactorIsModified() {
    return factor_is_modified;
  }


  /** 
   * <em>order_emp_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderEmpCode()
  {
    return order_emp_code;
  }


  /** 
   * <em>order_emp_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderEmpCodeIsSet() {
    return order_emp_code_is_set;
  }


  /** 
   * <em>order_emp_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderEmpCodeIsModified() {
    return order_emp_code_is_modified;
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
   * <em>exec_end_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getExecEndTimestamp()
  {
    return exec_end_timestamp;
  }


  /** 
   * <em>exec_end_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecEndTimestampIsSet() {
    return exec_end_timestamp_is_set;
  }


  /** 
   * <em>exec_end_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecEndTimestampIsModified() {
    return exec_end_timestamp_is_modified;
  }


  /** 
   * <em>exec_file_send_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExecFileSendFlag()
  {
    return exec_file_send_flag;
  }


  /** 
   * <em>exec_file_send_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecFileSendFlagIsSet() {
    return exec_file_send_flag_is_set;
  }


  /** 
   * <em>exec_file_send_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecFileSendFlagIsModified() {
    return exec_file_send_flag_is_modified;
  }


  /** 
   * <em>first_order_unit_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFirstOrderUnitId()
  {
    return ( first_order_unit_id==null? ((long)0): first_order_unit_id.longValue() );
  }


  /** 
   * <em>first_order_unit_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFirstOrderUnitIdIsNull()
  {
    return first_order_unit_id == null;
  }


  /** 
   * <em>first_order_unit_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstOrderUnitIdIsSet() {
    return first_order_unit_id_is_set;
  }


  /** 
   * <em>first_order_unit_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstOrderUnitIdIsModified() {
    return first_order_unit_id_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
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
   * <em>execution_seq_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getExecutionSeqNo()
  {
    return ( execution_seq_no==null? ((int)0): execution_seq_no.intValue() );
  }


  /** 
   * <em>execution_seq_no</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExecutionSeqNoIsNull()
  {
    return execution_seq_no == null;
  }


  /** 
   * <em>execution_seq_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutionSeqNoIsSet() {
    return execution_seq_no_is_set;
  }


  /** 
   * <em>execution_seq_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutionSeqNoIsModified() {
    return execution_seq_no_is_modified;
  }


  /** 
   * <em>temporary_execution_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTemporaryExecutionFlag()
  {
    return temporary_execution_flag;
  }


  /** 
   * <em>temporary_execution_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTemporaryExecutionFlagIsSet() {
    return temporary_execution_flag_is_set;
  }


  /** 
   * <em>temporary_execution_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTemporaryExecutionFlagIsModified() {
    return temporary_execution_flag_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new FeqOrderUnitPK(order_unit_id);
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
   * <em>last_execution_serial_no</em>カラムの値を設定します。 
   *
   * @@param p_lastExecutionSerialNo <em>last_execution_serial_no</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setLastExecutionSerialNo( int p_lastExecutionSerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_execution_serial_no = p_lastExecutionSerialNo;
    last_execution_serial_no_is_set = true;
    last_execution_serial_no_is_modified = true;
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
   * <em>execution_condition_type</em>カラムの値を設定します。 
   *
   * @@param p_executionConditionType <em>execution_condition_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType値 
   */
  public final void setExecutionConditionType( com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType p_executionConditionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    execution_condition_type = p_executionConditionType;
    execution_condition_type_is_set = true;
    execution_condition_type_is_modified = true;
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
   * <em>settle_div</em>カラムの値を設定します。 
   *
   * @@param p_settleDiv <em>settle_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSettleDiv( String p_settleDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    settle_div = p_settleDiv;
    settle_div_is_set = true;
    settle_div_is_modified = true;
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
   * <em>executed_amount_yen</em>カラムの値を設定します。 
   *
   * @@param p_executedAmountYen <em>executed_amount_yen</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setExecutedAmountYen( double p_executedAmountYen )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    executed_amount_yen = new Double(p_executedAmountYen);
    executed_amount_yen_is_set = true;
    executed_amount_yen_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>executed_amount_yen</em>カラムに値を設定します。 
   */
  public final void setExecutedAmountYen( Double p_executedAmountYen )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    executed_amount_yen = p_executedAmountYen;
    executed_amount_yen_is_set = true;
    executed_amount_yen_is_modified = true;
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
   * <em>currency_code</em>カラムの値を設定します。 
   *
   * @@param p_currencyCode <em>currency_code</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>order_chanel</em>カラムの値を設定します。 
   *
   * @@param p_orderChanel <em>order_chanel</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderChanel( String p_orderChanel )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_chanel = p_orderChanel;
    order_chanel_is_set = true;
    order_chanel_is_modified = true;
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
   * <em>voucher_no</em>カラムの値を設定します。 
   *
   * @@param p_voucherNo <em>voucher_no</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setVoucherNo( String p_voucherNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    voucher_no = p_voucherNo;
    voucher_no_is_set = true;
    voucher_no_is_modified = true;
  }


  /** 
   * <em>comm_tbl_no</em>カラムの値を設定します。 
   *
   * @@param p_commTblNo <em>comm_tbl_no</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCommTblNo( String p_commTblNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comm_tbl_no = p_commTblNo;
    comm_tbl_no_is_set = true;
    comm_tbl_no_is_modified = true;
  }


  /** 
   * <em>comm_tbl_sub_no</em>カラムの値を設定します。 
   *
   * @@param p_commTblSubNo <em>comm_tbl_sub_no</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCommTblSubNo( String p_commTblSubNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comm_tbl_sub_no = p_commTblSubNo;
    comm_tbl_sub_no_is_set = true;
    comm_tbl_sub_no_is_modified = true;
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
   * <em>f_estimated_price</em>カラムの値を設定します。 
   *
   * @@param p_fEstimatedPrice <em>f_estimated_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setFEstimatedPrice( double p_fEstimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    f_estimated_price = new Double(p_fEstimatedPrice);
    f_estimated_price_is_set = true;
    f_estimated_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>f_estimated_price</em>カラムに値を設定します。 
   */
  public final void setFEstimatedPrice( Double p_fEstimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    f_estimated_price = p_fEstimatedPrice;
    f_estimated_price_is_set = true;
    f_estimated_price_is_modified = true;
  }


  /** 
   * <em>sonar_traded_code</em>カラムの値を設定します。 
   *
   * @@param p_sonarTradedCode <em>sonar_traded_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setSonarTradedCode( String p_sonarTradedCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_traded_code = p_sonarTradedCode;
    sonar_traded_code_is_set = true;
    sonar_traded_code_is_modified = true;
  }


  /** 
   * <em>sonar_market_code</em>カラムの値を設定します。 
   *
   * @@param p_sonarMarketCode <em>sonar_market_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSonarMarketCode( String p_sonarMarketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_market_code = p_sonarMarketCode;
    sonar_market_code_is_set = true;
    sonar_market_code_is_modified = true;
  }


  /** 
   * <em>comm_product_code</em>カラムの値を設定します。 
   *
   * @@param p_commProductCode <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCommProductCode( String p_commProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comm_product_code = p_commProductCode;
    comm_product_code_is_set = true;
    comm_product_code_is_modified = true;
  }


  /** 
   * <em>capital_gain</em>カラムの値を設定します。 
   *
   * @@param p_capitalGain <em>capital_gain</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCapitalGain( double p_capitalGain )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain = new Double(p_capitalGain);
    capital_gain_is_set = true;
    capital_gain_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>capital_gain</em>カラムに値を設定します。 
   */
  public final void setCapitalGain( Double p_capitalGain )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain = p_capitalGain;
    capital_gain_is_set = true;
    capital_gain_is_modified = true;
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
   * <em>confirmed_order_price</em>カラムの値を設定します。 
   *
   * @@param p_confirmedOrderPrice <em>confirmed_order_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setConfirmedOrderPrice( double p_confirmedOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_order_price = new Double(p_confirmedOrderPrice);
    confirmed_order_price_is_set = true;
    confirmed_order_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>confirmed_order_price</em>カラムに値を設定します。 
   */
  public final void setConfirmedOrderPrice( Double p_confirmedOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_order_price = p_confirmedOrderPrice;
    confirmed_order_price_is_set = true;
    confirmed_order_price_is_modified = true;
  }


  /** 
   * <em>confirmed_estimated_price</em>カラムの値を設定します。 
   *
   * @@param p_confirmedEstimatedPrice <em>confirmed_estimated_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setConfirmedEstimatedPrice( double p_confirmedEstimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_estimated_price = new Double(p_confirmedEstimatedPrice);
    confirmed_estimated_price_is_set = true;
    confirmed_estimated_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>confirmed_estimated_price</em>カラムに値を設定します。 
   */
  public final void setConfirmedEstimatedPrice( Double p_confirmedEstimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_estimated_price = p_confirmedEstimatedPrice;
    confirmed_estimated_price_is_set = true;
    confirmed_estimated_price_is_modified = true;
  }


  /** 
   * <em>confirmed_f_estimated_price</em>カラムの値を設定します。 
   *
   * @@param p_confirmedFEstimatedPrice <em>confirmed_f_estimated_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setConfirmedFEstimatedPrice( double p_confirmedFEstimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_f_estimated_price = new Double(p_confirmedFEstimatedPrice);
    confirmed_f_estimated_price_is_set = true;
    confirmed_f_estimated_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>confirmed_f_estimated_price</em>カラムに値を設定します。 
   */
  public final void setConfirmedFEstimatedPrice( Double p_confirmedFEstimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_f_estimated_price = p_confirmedFEstimatedPrice;
    confirmed_f_estimated_price_is_set = true;
    confirmed_f_estimated_price_is_modified = true;
  }


  /** 
   * <em>confirmed_exec_condition_type</em>カラムの値を設定します。 
   *
   * @@param p_confirmedExecConditionType <em>confirmed_exec_condition_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType値 
   */
  public final void setConfirmedExecConditionType( com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType p_confirmedExecConditionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_exec_condition_type = p_confirmedExecConditionType;
    confirmed_exec_condition_type_is_set = true;
    confirmed_exec_condition_type_is_modified = true;
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
   * <em>factor</em>カラムの値を設定します。 
   *
   * @@param p_factor <em>factor</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFactor( String p_factor )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    factor = p_factor;
    factor_is_set = true;
    factor_is_modified = true;
  }


  /** 
   * <em>order_emp_code</em>カラムの値を設定します。 
   *
   * @@param p_orderEmpCode <em>order_emp_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setOrderEmpCode( String p_orderEmpCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_emp_code = p_orderEmpCode;
    order_emp_code_is_set = true;
    order_emp_code_is_modified = true;
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
   * <em>exec_end_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_execEndTimestamp <em>exec_end_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setExecEndTimestamp( java.sql.Timestamp p_execEndTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_end_timestamp = p_execEndTimestamp;
    exec_end_timestamp_is_set = true;
    exec_end_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>exec_end_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setExecEndTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_end_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    exec_end_timestamp_is_set = true;
    exec_end_timestamp_is_modified = true;
  }


  /** 
   * <em>exec_file_send_flag</em>カラムの値を設定します。 
   *
   * @@param p_execFileSendFlag <em>exec_file_send_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExecFileSendFlag( String p_execFileSendFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_file_send_flag = p_execFileSendFlag;
    exec_file_send_flag_is_set = true;
    exec_file_send_flag_is_modified = true;
  }


  /** 
   * <em>first_order_unit_id</em>カラムの値を設定します。 
   *
   * @@param p_firstOrderUnitId <em>first_order_unit_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setFirstOrderUnitId( long p_firstOrderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    first_order_unit_id = new Long(p_firstOrderUnitId);
    first_order_unit_id_is_set = true;
    first_order_unit_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>first_order_unit_id</em>カラムに値を設定します。 
   */
  public final void setFirstOrderUnitId( Long p_firstOrderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    first_order_unit_id = p_firstOrderUnitId;
    first_order_unit_id_is_set = true;
    first_order_unit_id_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
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
   * <em>execution_seq_no</em>カラムの値を設定します。 
   *
   * @@param p_executionSeqNo <em>execution_seq_no</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setExecutionSeqNo( int p_executionSeqNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    execution_seq_no = new Integer(p_executionSeqNo);
    execution_seq_no_is_set = true;
    execution_seq_no_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>execution_seq_no</em>カラムに値を設定します。 
   */
  public final void setExecutionSeqNo( Integer p_executionSeqNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    execution_seq_no = p_executionSeqNo;
    execution_seq_no_is_set = true;
    execution_seq_no_is_modified = true;
  }


  /** 
   * <em>temporary_execution_flag</em>カラムの値を設定します。 
   *
   * @@param p_temporaryExecutionFlag <em>temporary_execution_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTemporaryExecutionFlag( String p_temporaryExecutionFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    temporary_execution_flag = p_temporaryExecutionFlag;
    temporary_execution_flag_is_set = true;
    temporary_execution_flag_is_modified = true;
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
                else if ( name.equals("account_div") ) {
                    return this.account_div;
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
                if ( name.equals("confirmed_quantity") ) {
                    return this.confirmed_quantity;
                }
                else if ( name.equals("confirmed_price") ) {
                    return this.confirmed_price;
                }
                else if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("comm_tbl_no") ) {
                    return this.comm_tbl_no;
                }
                else if ( name.equals("comm_tbl_sub_no") ) {
                    return this.comm_tbl_sub_no;
                }
                else if ( name.equals("comm_product_code") ) {
                    return this.comm_product_code;
                }
                else if ( name.equals("capital_gain") ) {
                    return this.capital_gain;
                }
                else if ( name.equals("capital_gain_tax") ) {
                    return this.capital_gain_tax;
                }
                else if ( name.equals("confirmed_order_price") ) {
                    return this.confirmed_order_price;
                }
                else if ( name.equals("confirmed_estimated_price") ) {
                    return this.confirmed_estimated_price;
                }
                else if ( name.equals("confirmed_f_estimated_price") ) {
                    return this.confirmed_f_estimated_price;
                }
                else if ( name.equals("confirmed_exec_condition_type") ) {
                    return this.confirmed_exec_condition_type;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
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
                else if ( name.equals("executed_amount") ) {
                    return this.executed_amount;
                }
                else if ( name.equals("executed_amount_yen") ) {
                    return this.executed_amount_yen;
                }
                else if ( name.equals("expiration_status") ) {
                    return this.expiration_status;
                }
                else if ( name.equals("estimated_price") ) {
                    return this.estimated_price;
                }
                else if ( name.equals("error_reason_code") ) {
                    return this.error_reason_code;
                }
                else if ( name.equals("exec_end_timestamp") ) {
                    return this.exec_end_timestamp;
                }
                else if ( name.equals("exec_file_send_flag") ) {
                    return this.exec_file_send_flag;
                }
                else if ( name.equals("execution_seq_no") ) {
                    return this.execution_seq_no;
                }
                break;
            case 'f':
                if ( name.equals("f_estimated_price") ) {
                    return this.f_estimated_price;
                }
                else if ( name.equals("factor") ) {
                    return this.factor;
                }
                else if ( name.equals("first_order_unit_id") ) {
                    return this.first_order_unit_id;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_order_action_serial_no") ) {
                    return new Integer( last_order_action_serial_no );
                }
                else if ( name.equals("last_execution_serial_no") ) {
                    return new Integer( last_execution_serial_no );
                }
                else if ( name.equals("limit_price") ) {
                    return this.limit_price;
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
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
                else if ( name.equals("order_condition_type") ) {
                    return this.order_condition_type;
                }
                else if ( name.equals("order_cond_operator") ) {
                    return this.order_cond_operator;
                }
                else if ( name.equals("order_status") ) {
                    return this.order_status;
                }
                else if ( name.equals("order_open_status") ) {
                    return this.order_open_status;
                }
                else if ( name.equals("order_chanel") ) {
                    return this.order_chanel;
                }
                else if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_root_div") ) {
                    return this.order_root_div;
                }
                else if ( name.equals("order_emp_code") ) {
                    return this.order_emp_code;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("price") ) {
                    return this.price;
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
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("stop_order_price") ) {
                    return this.stop_order_price;
                }
                else if ( name.equals("settle_div") ) {
                    return this.settle_div;
                }
                else if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                else if ( name.equals("sonar_traded_code") ) {
                    return this.sonar_traded_code;
                }
                else if ( name.equals("sonar_market_code") ) {
                    return this.sonar_market_code;
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    return this.trader_id;
                }
                else if ( name.equals("tax_type") ) {
                    return this.tax_type;
                }
                else if ( name.equals("temporary_execution_flag") ) {
                    return this.temporary_execution_flag;
                }
                break;
            case 'v':
                if ( name.equals("voucher_no") ) {
                    return this.voucher_no;
                }
                break;
            case 'w':
                if ( name.equals("w_limit_price") ) {
                    return this.w_limit_price;
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
                else if ( name.equals("currency_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_code' must be String: '"+value+"' is not." );
                    this.currency_code = (String) value;
                    if (this.currency_code_is_set)
                        this.currency_code_is_modified = true;
                    this.currency_code_is_set = true;
                    return;
                }
                else if ( name.equals("comm_tbl_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comm_tbl_no' must be String: '"+value+"' is not." );
                    this.comm_tbl_no = (String) value;
                    if (this.comm_tbl_no_is_set)
                        this.comm_tbl_no_is_modified = true;
                    this.comm_tbl_no_is_set = true;
                    return;
                }
                else if ( name.equals("comm_tbl_sub_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comm_tbl_sub_no' must be String: '"+value+"' is not." );
                    this.comm_tbl_sub_no = (String) value;
                    if (this.comm_tbl_sub_no_is_set)
                        this.comm_tbl_sub_no_is_modified = true;
                    this.comm_tbl_sub_no_is_set = true;
                    return;
                }
                else if ( name.equals("comm_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comm_product_code' must be String: '"+value+"' is not." );
                    this.comm_product_code = (String) value;
                    if (this.comm_product_code_is_set)
                        this.comm_product_code_is_modified = true;
                    this.comm_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("capital_gain") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'capital_gain' must be Double: '"+value+"' is not." );
                    this.capital_gain = (Double) value;
                    if (this.capital_gain_is_set)
                        this.capital_gain_is_modified = true;
                    this.capital_gain_is_set = true;
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
                else if ( name.equals("confirmed_order_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'confirmed_order_price' must be Double: '"+value+"' is not." );
                    this.confirmed_order_price = (Double) value;
                    if (this.confirmed_order_price_is_set)
                        this.confirmed_order_price_is_modified = true;
                    this.confirmed_order_price_is_set = true;
                    return;
                }
                else if ( name.equals("confirmed_estimated_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'confirmed_estimated_price' must be Double: '"+value+"' is not." );
                    this.confirmed_estimated_price = (Double) value;
                    if (this.confirmed_estimated_price_is_set)
                        this.confirmed_estimated_price_is_modified = true;
                    this.confirmed_estimated_price_is_set = true;
                    return;
                }
                else if ( name.equals("confirmed_f_estimated_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'confirmed_f_estimated_price' must be Double: '"+value+"' is not." );
                    this.confirmed_f_estimated_price = (Double) value;
                    if (this.confirmed_f_estimated_price_is_set)
                        this.confirmed_f_estimated_price_is_modified = true;
                    this.confirmed_f_estimated_price_is_set = true;
                    return;
                }
                else if ( name.equals("confirmed_exec_condition_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType) )
                        throw new IllegalArgumentException( "value for 'confirmed_exec_condition_type' must be com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType: '"+value+"' is not." );
                    this.confirmed_exec_condition_type = (com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType) value;
                    if (this.confirmed_exec_condition_type_is_set)
                        this.confirmed_exec_condition_type_is_modified = true;
                    this.confirmed_exec_condition_type_is_set = true;
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
                break;
            case 'e':
                if ( name.equals("execution_condition_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType) )
                        throw new IllegalArgumentException( "value for 'execution_condition_type' must be com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType: '"+value+"' is not." );
                    this.execution_condition_type = (com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType) value;
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
                else if ( name.equals("executed_amount_yen") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'executed_amount_yen' must be Double: '"+value+"' is not." );
                    this.executed_amount_yen = (Double) value;
                    if (this.executed_amount_yen_is_set)
                        this.executed_amount_yen_is_modified = true;
                    this.executed_amount_yen_is_set = true;
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
                else if ( name.equals("exec_end_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'exec_end_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.exec_end_timestamp = (java.sql.Timestamp) value;
                    if (this.exec_end_timestamp_is_set)
                        this.exec_end_timestamp_is_modified = true;
                    this.exec_end_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("exec_file_send_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exec_file_send_flag' must be String: '"+value+"' is not." );
                    this.exec_file_send_flag = (String) value;
                    if (this.exec_file_send_flag_is_set)
                        this.exec_file_send_flag_is_modified = true;
                    this.exec_file_send_flag_is_set = true;
                    return;
                }
                else if ( name.equals("execution_seq_no") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'execution_seq_no' must be Integer: '"+value+"' is not." );
                    this.execution_seq_no = (Integer) value;
                    if (this.execution_seq_no_is_set)
                        this.execution_seq_no_is_modified = true;
                    this.execution_seq_no_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("f_estimated_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'f_estimated_price' must be Double: '"+value+"' is not." );
                    this.f_estimated_price = (Double) value;
                    if (this.f_estimated_price_is_set)
                        this.f_estimated_price_is_modified = true;
                    this.f_estimated_price_is_set = true;
                    return;
                }
                else if ( name.equals("factor") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'factor' must be String: '"+value+"' is not." );
                    this.factor = (String) value;
                    if (this.factor_is_set)
                        this.factor_is_modified = true;
                    this.factor_is_set = true;
                    return;
                }
                else if ( name.equals("first_order_unit_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'first_order_unit_id' must be Long: '"+value+"' is not." );
                    this.first_order_unit_id = (Long) value;
                    if (this.first_order_unit_id_is_set)
                        this.first_order_unit_id_is_modified = true;
                    this.first_order_unit_id_is_set = true;
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
                if ( name.equals("last_order_action_serial_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'last_order_action_serial_no' must be Integer: '"+value+"' is not." );
                    this.last_order_action_serial_no = ((Integer) value).intValue();
                    if (this.last_order_action_serial_no_is_set)
                        this.last_order_action_serial_no_is_modified = true;
                    this.last_order_action_serial_no_is_set = true;
                    return;
                }
                else if ( name.equals("last_execution_serial_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'last_execution_serial_no' must be Integer: '"+value+"' is not." );
                    this.last_execution_serial_no = ((Integer) value).intValue();
                    if (this.last_execution_serial_no_is_set)
                        this.last_execution_serial_no_is_modified = true;
                    this.last_execution_serial_no_is_set = true;
                    return;
                }
                else if ( name.equals("limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'limit_price' must be Double: '"+value+"' is not." );
                    this.limit_price = (Double) value;
                    if (this.limit_price_is_set)
                        this.limit_price_is_modified = true;
                    this.limit_price_is_set = true;
                    return;
                }
                else if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
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
                else if ( name.equals("order_open_status") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum) )
                        throw new IllegalArgumentException( "value for 'order_open_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum: '"+value+"' is not." );
                    this.order_open_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum) value;
                    if (this.order_open_status_is_set)
                        this.order_open_status_is_modified = true;
                    this.order_open_status_is_set = true;
                    return;
                }
                else if ( name.equals("order_chanel") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_chanel' must be String: '"+value+"' is not." );
                    this.order_chanel = (String) value;
                    if (this.order_chanel_is_set)
                        this.order_chanel_is_modified = true;
                    this.order_chanel_is_set = true;
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
                else if ( name.equals("order_emp_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_emp_code' must be String: '"+value+"' is not." );
                    this.order_emp_code = (String) value;
                    if (this.order_emp_code_is_set)
                        this.order_emp_code_is_modified = true;
                    this.order_emp_code_is_set = true;
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
                else if ( name.equals("settle_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'settle_div' must be String: '"+value+"' is not." );
                    this.settle_div = (String) value;
                    if (this.settle_div_is_set)
                        this.settle_div_is_modified = true;
                    this.settle_div_is_set = true;
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
                else if ( name.equals("sonar_traded_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_traded_code' must be String: '"+value+"' is not." );
                    this.sonar_traded_code = (String) value;
                    if (this.sonar_traded_code_is_set)
                        this.sonar_traded_code_is_modified = true;
                    this.sonar_traded_code_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_market_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_market_code' must be String: '"+value+"' is not." );
                    this.sonar_market_code = (String) value;
                    if (this.sonar_market_code_is_set)
                        this.sonar_market_code_is_modified = true;
                    this.sonar_market_code_is_set = true;
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
                else if ( name.equals("temporary_execution_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'temporary_execution_flag' must be String: '"+value+"' is not." );
                    this.temporary_execution_flag = (String) value;
                    if (this.temporary_execution_flag_is_set)
                        this.temporary_execution_flag_is_modified = true;
                    this.temporary_execution_flag_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("voucher_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'voucher_no' must be String: '"+value+"' is not." );
                    this.voucher_no = (String) value;
                    if (this.voucher_no_is_set)
                        this.voucher_no_is_modified = true;
                    this.voucher_no_is_set = true;
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
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
