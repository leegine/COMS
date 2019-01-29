head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeOrderUnitParams.java;


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
 * eqtype_order_unitテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link EqtypeOrderUnitRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link EqtypeOrderUnitParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link EqtypeOrderUnitParams}が{@@link EqtypeOrderUnitRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeOrderUnitPK 
 * @@see EqtypeOrderUnitRow 
 */
public class EqtypeOrderUnitParams extends Params implements EqtypeOrderUnitRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "eqtype_order_unit";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = EqtypeOrderUnitRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return EqtypeOrderUnitRow.TYPE;
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
   * <em>swap_tax_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum  swap_tax_type;

  /** 
   * <em>repayment_type</em>カラムの値 
   */
  public  String  repayment_type;

  /** 
   * <em>repayment_num</em>カラムの値 
   */
  public  Integer  repayment_num;

  /** 
   * <em>sonar_repayment_type</em>カラムの値 
   */
  public  String  sonar_repayment_type;

  /** 
   * <em>biz_date</em>カラムの値 
   */
  public  String  biz_date;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>quantity_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum  quantity_type;

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
   * <em>capital_gain</em>カラムの値 
   */
  public  Double  capital_gain;

  /** 
   * <em>capital_gain_tax</em>カラムの値 
   */
  public  Double  capital_gain_tax;

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
   * <em>short_sell_order_flag</em>カラムの値 
   */
  public  String  short_sell_order_flag;

  /** 
   * <em>modify_cancel_type</em>カラムの値 
   */
  public  String  modify_cancel_type;

  /** 
   * <em>order_root_div</em>カラムの値 
   */
  public  String  order_root_div;

  /** 
   * <em>submit_order_route_div</em>カラムの値 
   */
  public  String  submit_order_route_div;

  /** 
   * <em>confirmed_order_price</em>カラムの値 
   */
  public  Double  confirmed_order_price;

  /** 
   * <em>confirmed_estimated_price</em>カラムの値 
   */
  public  Double  confirmed_estimated_price;

  /** 
   * <em>confirmed_exec_condition_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType  confirmed_exec_condition_type;

  /** 
   * <em>confirmed_price_condition_type</em>カラムの値 
   */
  public  String  confirmed_price_condition_type;

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
   * <em>first_order_unit_id</em>カラムの値 
   */
  public  Long  first_order_unit_id;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>confirmed_order_rev</em>カラムの値 
   */
  public  String  confirmed_order_rev;

  /** 
   * <em>order_rev</em>カラムの値 
   */
  public  String  order_rev;

  /** 
   * <em>reserve_order_exist_flag</em>カラムの値 
   */
  public  String  reserve_order_exist_flag;

  /** 
   * <em>original_quantity</em>カラムの値 
   */
  public  Double  original_quantity;

  /** 
   * <em>stop_order_ordered_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  stop_order_ordered_timestamp;

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
   * <em>submit_order_delay_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  submit_order_delay_flag;

  /** 
   * <em>forced_settle_reason_type</em>カラムの値 
   */
  public  String  forced_settle_reason_type;

  /** 
   * <em>approve_status_type</em>カラムの値 
   */
  public  String  approve_status_type;

  /** 
   * <em>approver_code</em>カラムの値 
   */
  public  String  approver_code;

  /** 
   * <em>approve_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  approve_timestamp;

  /** 
   * <em>margin_maintenance_rate</em>カラムの値 
   */
  public  Double  margin_maintenance_rate;

  /** 
   * <em>additional_margin_date</em>カラムの値 
   */
  public  java.sql.Timestamp  additional_margin_date;

  /** 
   * <em>additional_margin_accrued_days</em>カラムの値 
   */
  public  Long  additional_margin_accrued_days;

  /** 
   * <em>forced_expire_type</em>カラムの値 
   */
  public  String  forced_expire_type;

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
  private boolean order_status_is_set = false;
  private boolean order_status_is_modified = false;
  private boolean order_open_status_is_set = false;
  private boolean order_open_status_is_modified = false;
  private boolean expiration_status_is_set = false;
  private boolean expiration_status_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean swap_tax_type_is_set = false;
  private boolean swap_tax_type_is_modified = false;
  private boolean repayment_type_is_set = false;
  private boolean repayment_type_is_modified = false;
  private boolean repayment_num_is_set = false;
  private boolean repayment_num_is_modified = false;
  private boolean sonar_repayment_type_is_set = false;
  private boolean sonar_repayment_type_is_modified = false;
  private boolean biz_date_is_set = false;
  private boolean biz_date_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean quantity_type_is_set = false;
  private boolean quantity_type_is_modified = false;
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
  private boolean capital_gain_is_set = false;
  private boolean capital_gain_is_modified = false;
  private boolean capital_gain_tax_is_set = false;
  private boolean capital_gain_tax_is_modified = false;
  private boolean sonar_traded_code_is_set = false;
  private boolean sonar_traded_code_is_modified = false;
  private boolean sonar_market_code_is_set = false;
  private boolean sonar_market_code_is_modified = false;
  private boolean comm_product_code_is_set = false;
  private boolean comm_product_code_is_modified = false;
  private boolean short_sell_order_flag_is_set = false;
  private boolean short_sell_order_flag_is_modified = false;
  private boolean modify_cancel_type_is_set = false;
  private boolean modify_cancel_type_is_modified = false;
  private boolean order_root_div_is_set = false;
  private boolean order_root_div_is_modified = false;
  private boolean submit_order_route_div_is_set = false;
  private boolean submit_order_route_div_is_modified = false;
  private boolean confirmed_order_price_is_set = false;
  private boolean confirmed_order_price_is_modified = false;
  private boolean confirmed_estimated_price_is_set = false;
  private boolean confirmed_estimated_price_is_modified = false;
  private boolean confirmed_exec_condition_type_is_set = false;
  private boolean confirmed_exec_condition_type_is_modified = false;
  private boolean confirmed_price_condition_type_is_set = false;
  private boolean confirmed_price_condition_type_is_modified = false;
  private boolean closing_order_type_is_set = false;
  private boolean closing_order_type_is_modified = false;
  private boolean error_reason_code_is_set = false;
  private boolean error_reason_code_is_modified = false;
  private boolean request_type_is_set = false;
  private boolean request_type_is_modified = false;
  private boolean first_order_unit_id_is_set = false;
  private boolean first_order_unit_id_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean confirmed_order_rev_is_set = false;
  private boolean confirmed_order_rev_is_modified = false;
  private boolean order_rev_is_set = false;
  private boolean order_rev_is_modified = false;
  private boolean reserve_order_exist_flag_is_set = false;
  private boolean reserve_order_exist_flag_is_modified = false;
  private boolean original_quantity_is_set = false;
  private boolean original_quantity_is_modified = false;
  private boolean stop_order_ordered_timestamp_is_set = false;
  private boolean stop_order_ordered_timestamp_is_modified = false;
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
  private boolean submit_order_delay_flag_is_set = false;
  private boolean submit_order_delay_flag_is_modified = false;
  private boolean forced_settle_reason_type_is_set = false;
  private boolean forced_settle_reason_type_is_modified = false;
  private boolean approve_status_type_is_set = false;
  private boolean approve_status_type_is_modified = false;
  private boolean approver_code_is_set = false;
  private boolean approver_code_is_modified = false;
  private boolean approve_timestamp_is_set = false;
  private boolean approve_timestamp_is_modified = false;
  private boolean margin_maintenance_rate_is_set = false;
  private boolean margin_maintenance_rate_is_modified = false;
  private boolean additional_margin_date_is_set = false;
  private boolean additional_margin_date_is_modified = false;
  private boolean additional_margin_accrued_days_is_set = false;
  private boolean additional_margin_accrued_days_is_modified = false;
  private boolean forced_expire_type_is_set = false;
  private boolean forced_expire_type_is_modified = false;


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
      + "," + "last_execution_serial_no=" +last_execution_serial_no
      + "," + "product_type=" +product_type
      + "," + "market_id=" +market_id
      + "," + "quantity=" +quantity
      + "," + "limit_price=" +limit_price
      + "," + "execution_condition_type=" +execution_condition_type
      + "," + "price_condition_type=" +price_condition_type
      + "," + "order_condition_type=" +order_condition_type
      + "," + "order_cond_operator=" +order_cond_operator
      + "," + "stop_order_price=" +stop_order_price
      + "," + "w_limit_price=" +w_limit_price
      + "," + "delivery_date=" +delivery_date
      + "," + "expiration_date=" +expiration_date
      + "," + "confirmed_quantity=" +confirmed_quantity
      + "," + "confirmed_price=" +confirmed_price
      + "," + "executed_quantity=" +executed_quantity
      + "," + "executed_amount=" +executed_amount
      + "," + "order_status=" +order_status
      + "," + "order_open_status=" +order_open_status
      + "," + "expiration_status=" +expiration_status
      + "," + "tax_type=" +tax_type
      + "," + "swap_tax_type=" +swap_tax_type
      + "," + "repayment_type=" +repayment_type
      + "," + "repayment_num=" +repayment_num
      + "," + "sonar_repayment_type=" +sonar_repayment_type
      + "," + "biz_date=" +biz_date
      + "," + "product_id=" +product_id
      + "," + "quantity_type=" +quantity_type
      + "," + "order_chanel=" +order_chanel
      + "," + "received_date_time=" +received_date_time
      + "," + "voucher_no=" +voucher_no
      + "," + "comm_tbl_no=" +comm_tbl_no
      + "," + "comm_tbl_sub_no=" +comm_tbl_sub_no
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "price=" +price
      + "," + "order_request_number=" +order_request_number
      + "," + "estimated_price=" +estimated_price
      + "," + "capital_gain=" +capital_gain
      + "," + "capital_gain_tax=" +capital_gain_tax
      + "," + "sonar_traded_code=" +sonar_traded_code
      + "," + "sonar_market_code=" +sonar_market_code
      + "," + "comm_product_code=" +comm_product_code
      + "," + "short_sell_order_flag=" +short_sell_order_flag
      + "," + "modify_cancel_type=" +modify_cancel_type
      + "," + "order_root_div=" +order_root_div
      + "," + "submit_order_route_div=" +submit_order_route_div
      + "," + "confirmed_order_price=" +confirmed_order_price
      + "," + "confirmed_estimated_price=" +confirmed_estimated_price
      + "," + "confirmed_exec_condition_type=" +confirmed_exec_condition_type
      + "," + "confirmed_price_condition_type=" +confirmed_price_condition_type
      + "," + "closing_order_type=" +closing_order_type
      + "," + "error_reason_code=" +error_reason_code
      + "," + "request_type=" +request_type
      + "," + "first_order_unit_id=" +first_order_unit_id
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "confirmed_order_rev=" +confirmed_order_rev
      + "," + "order_rev=" +order_rev
      + "," + "reserve_order_exist_flag=" +reserve_order_exist_flag
      + "," + "original_quantity=" +original_quantity
      + "," + "stop_order_ordered_timestamp=" +stop_order_ordered_timestamp
      + "," + "org_order_condition_type=" +org_order_condition_type
      + "," + "org_order_cond_operator=" +org_order_cond_operator
      + "," + "org_stop_order_price=" +org_stop_order_price
      + "," + "org_w_limit_price=" +org_w_limit_price
      + "," + "org_w_limit_exec_cond_type=" +org_w_limit_exec_cond_type
      + "," + "w_limit_exec_cond_type=" +w_limit_exec_cond_type
      + "," + "w_limit_before_limit_price=" +w_limit_before_limit_price
      + "," + "w_limit_before_exec_cond_type=" +w_limit_before_exec_cond_type
      + "," + "submit_order_delay_flag=" +submit_order_delay_flag
      + "," + "forced_settle_reason_type=" +forced_settle_reason_type
      + "," + "approve_status_type=" +approve_status_type
      + "," + "approver_code=" +approver_code
      + "," + "approve_timestamp=" +approve_timestamp
      + "," + "margin_maintenance_rate=" +margin_maintenance_rate
      + "," + "additional_margin_date=" +additional_margin_date
      + "," + "additional_margin_accrued_days=" +additional_margin_accrued_days
      + "," + "forced_expire_type=" +forced_expire_type
      + "}";
  }


  /** 
   * 値が未設定のEqtypeOrderUnitParamsオブジェクトを作成します。 
   */
  public EqtypeOrderUnitParams() {
    order_unit_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のEqtypeOrderUnitRowオブジェクトの値を利用してEqtypeOrderUnitParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するEqtypeOrderUnitRowオブジェクト 
   */
  public EqtypeOrderUnitParams( EqtypeOrderUnitRow p_row )
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
    swap_tax_type = p_row.getSwapTaxType();
    swap_tax_type_is_set = p_row.getSwapTaxTypeIsSet();
    swap_tax_type_is_modified = p_row.getSwapTaxTypeIsModified();
    repayment_type = p_row.getRepaymentType();
    repayment_type_is_set = p_row.getRepaymentTypeIsSet();
    repayment_type_is_modified = p_row.getRepaymentTypeIsModified();
    if ( !p_row.getRepaymentNumIsNull() )
      repayment_num = new Integer( p_row.getRepaymentNum() );
    repayment_num_is_set = p_row.getRepaymentNumIsSet();
    repayment_num_is_modified = p_row.getRepaymentNumIsModified();
    sonar_repayment_type = p_row.getSonarRepaymentType();
    sonar_repayment_type_is_set = p_row.getSonarRepaymentTypeIsSet();
    sonar_repayment_type_is_modified = p_row.getSonarRepaymentTypeIsModified();
    biz_date = p_row.getBizDate();
    biz_date_is_set = p_row.getBizDateIsSet();
    biz_date_is_modified = p_row.getBizDateIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    quantity_type = p_row.getQuantityType();
    quantity_type_is_set = p_row.getQuantityTypeIsSet();
    quantity_type_is_modified = p_row.getQuantityTypeIsModified();
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
    if ( !p_row.getCapitalGainIsNull() )
      capital_gain = new Double( p_row.getCapitalGain() );
    capital_gain_is_set = p_row.getCapitalGainIsSet();
    capital_gain_is_modified = p_row.getCapitalGainIsModified();
    if ( !p_row.getCapitalGainTaxIsNull() )
      capital_gain_tax = new Double( p_row.getCapitalGainTax() );
    capital_gain_tax_is_set = p_row.getCapitalGainTaxIsSet();
    capital_gain_tax_is_modified = p_row.getCapitalGainTaxIsModified();
    sonar_traded_code = p_row.getSonarTradedCode();
    sonar_traded_code_is_set = p_row.getSonarTradedCodeIsSet();
    sonar_traded_code_is_modified = p_row.getSonarTradedCodeIsModified();
    sonar_market_code = p_row.getSonarMarketCode();
    sonar_market_code_is_set = p_row.getSonarMarketCodeIsSet();
    sonar_market_code_is_modified = p_row.getSonarMarketCodeIsModified();
    comm_product_code = p_row.getCommProductCode();
    comm_product_code_is_set = p_row.getCommProductCodeIsSet();
    comm_product_code_is_modified = p_row.getCommProductCodeIsModified();
    short_sell_order_flag = p_row.getShortSellOrderFlag();
    short_sell_order_flag_is_set = p_row.getShortSellOrderFlagIsSet();
    short_sell_order_flag_is_modified = p_row.getShortSellOrderFlagIsModified();
    modify_cancel_type = p_row.getModifyCancelType();
    modify_cancel_type_is_set = p_row.getModifyCancelTypeIsSet();
    modify_cancel_type_is_modified = p_row.getModifyCancelTypeIsModified();
    order_root_div = p_row.getOrderRootDiv();
    order_root_div_is_set = p_row.getOrderRootDivIsSet();
    order_root_div_is_modified = p_row.getOrderRootDivIsModified();
    submit_order_route_div = p_row.getSubmitOrderRouteDiv();
    submit_order_route_div_is_set = p_row.getSubmitOrderRouteDivIsSet();
    submit_order_route_div_is_modified = p_row.getSubmitOrderRouteDivIsModified();
    if ( !p_row.getConfirmedOrderPriceIsNull() )
      confirmed_order_price = new Double( p_row.getConfirmedOrderPrice() );
    confirmed_order_price_is_set = p_row.getConfirmedOrderPriceIsSet();
    confirmed_order_price_is_modified = p_row.getConfirmedOrderPriceIsModified();
    if ( !p_row.getConfirmedEstimatedPriceIsNull() )
      confirmed_estimated_price = new Double( p_row.getConfirmedEstimatedPrice() );
    confirmed_estimated_price_is_set = p_row.getConfirmedEstimatedPriceIsSet();
    confirmed_estimated_price_is_modified = p_row.getConfirmedEstimatedPriceIsModified();
    confirmed_exec_condition_type = p_row.getConfirmedExecConditionType();
    confirmed_exec_condition_type_is_set = p_row.getConfirmedExecConditionTypeIsSet();
    confirmed_exec_condition_type_is_modified = p_row.getConfirmedExecConditionTypeIsModified();
    confirmed_price_condition_type = p_row.getConfirmedPriceConditionType();
    confirmed_price_condition_type_is_set = p_row.getConfirmedPriceConditionTypeIsSet();
    confirmed_price_condition_type_is_modified = p_row.getConfirmedPriceConditionTypeIsModified();
    closing_order_type = p_row.getClosingOrderType();
    closing_order_type_is_set = p_row.getClosingOrderTypeIsSet();
    closing_order_type_is_modified = p_row.getClosingOrderTypeIsModified();
    error_reason_code = p_row.getErrorReasonCode();
    error_reason_code_is_set = p_row.getErrorReasonCodeIsSet();
    error_reason_code_is_modified = p_row.getErrorReasonCodeIsModified();
    request_type = p_row.getRequestType();
    request_type_is_set = p_row.getRequestTypeIsSet();
    request_type_is_modified = p_row.getRequestTypeIsModified();
    if ( !p_row.getFirstOrderUnitIdIsNull() )
      first_order_unit_id = new Long( p_row.getFirstOrderUnitId() );
    first_order_unit_id_is_set = p_row.getFirstOrderUnitIdIsSet();
    first_order_unit_id_is_modified = p_row.getFirstOrderUnitIdIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    confirmed_order_rev = p_row.getConfirmedOrderRev();
    confirmed_order_rev_is_set = p_row.getConfirmedOrderRevIsSet();
    confirmed_order_rev_is_modified = p_row.getConfirmedOrderRevIsModified();
    order_rev = p_row.getOrderRev();
    order_rev_is_set = p_row.getOrderRevIsSet();
    order_rev_is_modified = p_row.getOrderRevIsModified();
    reserve_order_exist_flag = p_row.getReserveOrderExistFlag();
    reserve_order_exist_flag_is_set = p_row.getReserveOrderExistFlagIsSet();
    reserve_order_exist_flag_is_modified = p_row.getReserveOrderExistFlagIsModified();
    if ( !p_row.getOriginalQuantityIsNull() )
      original_quantity = new Double( p_row.getOriginalQuantity() );
    original_quantity_is_set = p_row.getOriginalQuantityIsSet();
    original_quantity_is_modified = p_row.getOriginalQuantityIsModified();
    stop_order_ordered_timestamp = p_row.getStopOrderOrderedTimestamp();
    stop_order_ordered_timestamp_is_set = p_row.getStopOrderOrderedTimestampIsSet();
    stop_order_ordered_timestamp_is_modified = p_row.getStopOrderOrderedTimestampIsModified();
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
    submit_order_delay_flag = p_row.getSubmitOrderDelayFlag();
    submit_order_delay_flag_is_set = p_row.getSubmitOrderDelayFlagIsSet();
    submit_order_delay_flag_is_modified = p_row.getSubmitOrderDelayFlagIsModified();
    forced_settle_reason_type = p_row.getForcedSettleReasonType();
    forced_settle_reason_type_is_set = p_row.getForcedSettleReasonTypeIsSet();
    forced_settle_reason_type_is_modified = p_row.getForcedSettleReasonTypeIsModified();
    approve_status_type = p_row.getApproveStatusType();
    approve_status_type_is_set = p_row.getApproveStatusTypeIsSet();
    approve_status_type_is_modified = p_row.getApproveStatusTypeIsModified();
    approver_code = p_row.getApproverCode();
    approver_code_is_set = p_row.getApproverCodeIsSet();
    approver_code_is_modified = p_row.getApproverCodeIsModified();
    approve_timestamp = p_row.getApproveTimestamp();
    approve_timestamp_is_set = p_row.getApproveTimestampIsSet();
    approve_timestamp_is_modified = p_row.getApproveTimestampIsModified();
    if ( !p_row.getMarginMaintenanceRateIsNull() )
      margin_maintenance_rate = new Double( p_row.getMarginMaintenanceRate() );
    margin_maintenance_rate_is_set = p_row.getMarginMaintenanceRateIsSet();
    margin_maintenance_rate_is_modified = p_row.getMarginMaintenanceRateIsModified();
    additional_margin_date = p_row.getAdditionalMarginDate();
    additional_margin_date_is_set = p_row.getAdditionalMarginDateIsSet();
    additional_margin_date_is_modified = p_row.getAdditionalMarginDateIsModified();
    if ( !p_row.getAdditionalMarginAccruedDaysIsNull() )
      additional_margin_accrued_days = new Long( p_row.getAdditionalMarginAccruedDays() );
    additional_margin_accrued_days_is_set = p_row.getAdditionalMarginAccruedDaysIsSet();
    additional_margin_accrued_days_is_modified = p_row.getAdditionalMarginAccruedDaysIsModified();
    forced_expire_type = p_row.getForcedExpireType();
    forced_expire_type_is_set = p_row.getForcedExpireTypeIsSet();
    forced_expire_type_is_modified = p_row.getForcedExpireTypeIsModified();
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
      order_status_is_set = true;
      order_status_is_modified = true;
      order_open_status_is_set = true;
      order_open_status_is_modified = true;
      expiration_status_is_set = true;
      expiration_status_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      swap_tax_type_is_set = true;
      swap_tax_type_is_modified = true;
      repayment_type_is_set = true;
      repayment_type_is_modified = true;
      repayment_num_is_set = true;
      repayment_num_is_modified = true;
      sonar_repayment_type_is_set = true;
      sonar_repayment_type_is_modified = true;
      biz_date_is_set = true;
      biz_date_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      quantity_type_is_set = true;
      quantity_type_is_modified = true;
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
      capital_gain_is_set = true;
      capital_gain_is_modified = true;
      capital_gain_tax_is_set = true;
      capital_gain_tax_is_modified = true;
      sonar_traded_code_is_set = true;
      sonar_traded_code_is_modified = true;
      sonar_market_code_is_set = true;
      sonar_market_code_is_modified = true;
      comm_product_code_is_set = true;
      comm_product_code_is_modified = true;
      short_sell_order_flag_is_set = true;
      short_sell_order_flag_is_modified = true;
      modify_cancel_type_is_set = true;
      modify_cancel_type_is_modified = true;
      order_root_div_is_set = true;
      order_root_div_is_modified = true;
      submit_order_route_div_is_set = true;
      submit_order_route_div_is_modified = true;
      confirmed_order_price_is_set = true;
      confirmed_order_price_is_modified = true;
      confirmed_estimated_price_is_set = true;
      confirmed_estimated_price_is_modified = true;
      confirmed_exec_condition_type_is_set = true;
      confirmed_exec_condition_type_is_modified = true;
      confirmed_price_condition_type_is_set = true;
      confirmed_price_condition_type_is_modified = true;
      closing_order_type_is_set = true;
      closing_order_type_is_modified = true;
      error_reason_code_is_set = true;
      error_reason_code_is_modified = true;
      request_type_is_set = true;
      request_type_is_modified = true;
      first_order_unit_id_is_set = true;
      first_order_unit_id_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      confirmed_order_rev_is_set = true;
      confirmed_order_rev_is_modified = true;
      order_rev_is_set = true;
      order_rev_is_modified = true;
      reserve_order_exist_flag_is_set = true;
      reserve_order_exist_flag_is_modified = true;
      original_quantity_is_set = true;
      original_quantity_is_modified = true;
      stop_order_ordered_timestamp_is_set = true;
      stop_order_ordered_timestamp_is_modified = true;
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
      submit_order_delay_flag_is_set = true;
      submit_order_delay_flag_is_modified = true;
      forced_settle_reason_type_is_set = true;
      forced_settle_reason_type_is_modified = true;
      approve_status_type_is_set = true;
      approve_status_type_is_modified = true;
      approver_code_is_set = true;
      approver_code_is_modified = true;
      approve_timestamp_is_set = true;
      approve_timestamp_is_modified = true;
      margin_maintenance_rate_is_set = true;
      margin_maintenance_rate_is_modified = true;
      additional_margin_date_is_set = true;
      additional_margin_date_is_modified = true;
      additional_margin_accrued_days_is_set = true;
      additional_margin_accrued_days_is_modified = true;
      forced_expire_type_is_set = true;
      forced_expire_type_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof EqtypeOrderUnitRow ) )
       return false;
    return fieldsEqual( (EqtypeOrderUnitRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のEqtypeOrderUnitRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( EqtypeOrderUnitRow row )
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
    if ( swap_tax_type == null ) {
      if ( row.getSwapTaxType() != null )
        return false;
    } else if ( !swap_tax_type.equals( row.getSwapTaxType() ) ) {
        return false;
    }
    if ( repayment_type == null ) {
      if ( row.getRepaymentType() != null )
        return false;
    } else if ( !repayment_type.equals( row.getRepaymentType() ) ) {
        return false;
    }
    if ( repayment_num == null ) {
      if ( !row.getRepaymentNumIsNull() )
        return false;
    } else if ( row.getRepaymentNumIsNull() || ( repayment_num.intValue() != row.getRepaymentNum() ) ) {
        return false;
    }
    if ( sonar_repayment_type == null ) {
      if ( row.getSonarRepaymentType() != null )
        return false;
    } else if ( !sonar_repayment_type.equals( row.getSonarRepaymentType() ) ) {
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
    if ( quantity_type == null ) {
      if ( row.getQuantityType() != null )
        return false;
    } else if ( !quantity_type.equals( row.getQuantityType() ) ) {
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
    if ( short_sell_order_flag == null ) {
      if ( row.getShortSellOrderFlag() != null )
        return false;
    } else if ( !short_sell_order_flag.equals( row.getShortSellOrderFlag() ) ) {
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
    if ( submit_order_route_div == null ) {
      if ( row.getSubmitOrderRouteDiv() != null )
        return false;
    } else if ( !submit_order_route_div.equals( row.getSubmitOrderRouteDiv() ) ) {
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
    if ( confirmed_exec_condition_type == null ) {
      if ( row.getConfirmedExecConditionType() != null )
        return false;
    } else if ( !confirmed_exec_condition_type.equals( row.getConfirmedExecConditionType() ) ) {
        return false;
    }
    if ( confirmed_price_condition_type == null ) {
      if ( row.getConfirmedPriceConditionType() != null )
        return false;
    } else if ( !confirmed_price_condition_type.equals( row.getConfirmedPriceConditionType() ) ) {
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
    if ( first_order_unit_id == null ) {
      if ( !row.getFirstOrderUnitIdIsNull() )
        return false;
    } else if ( row.getFirstOrderUnitIdIsNull() || ( first_order_unit_id.longValue() != row.getFirstOrderUnitId() ) ) {
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
    if ( confirmed_order_rev == null ) {
      if ( row.getConfirmedOrderRev() != null )
        return false;
    } else if ( !confirmed_order_rev.equals( row.getConfirmedOrderRev() ) ) {
        return false;
    }
    if ( order_rev == null ) {
      if ( row.getOrderRev() != null )
        return false;
    } else if ( !order_rev.equals( row.getOrderRev() ) ) {
        return false;
    }
    if ( reserve_order_exist_flag == null ) {
      if ( row.getReserveOrderExistFlag() != null )
        return false;
    } else if ( !reserve_order_exist_flag.equals( row.getReserveOrderExistFlag() ) ) {
        return false;
    }
    if ( original_quantity == null ) {
      if ( !row.getOriginalQuantityIsNull() )
        return false;
    } else if ( row.getOriginalQuantityIsNull() || ( original_quantity.doubleValue() != row.getOriginalQuantity() ) ) {
        return false;
    }
    if ( stop_order_ordered_timestamp == null ) {
      if ( row.getStopOrderOrderedTimestamp() != null )
        return false;
    } else if ( !stop_order_ordered_timestamp.equals( row.getStopOrderOrderedTimestamp() ) ) {
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
    if ( submit_order_delay_flag == null ) {
      if ( row.getSubmitOrderDelayFlag() != null )
        return false;
    } else if ( !submit_order_delay_flag.equals( row.getSubmitOrderDelayFlag() ) ) {
        return false;
    }
    if ( forced_settle_reason_type == null ) {
      if ( row.getForcedSettleReasonType() != null )
        return false;
    } else if ( !forced_settle_reason_type.equals( row.getForcedSettleReasonType() ) ) {
        return false;
    }
    if ( approve_status_type == null ) {
      if ( row.getApproveStatusType() != null )
        return false;
    } else if ( !approve_status_type.equals( row.getApproveStatusType() ) ) {
        return false;
    }
    if ( approver_code == null ) {
      if ( row.getApproverCode() != null )
        return false;
    } else if ( !approver_code.equals( row.getApproverCode() ) ) {
        return false;
    }
    if ( approve_timestamp == null ) {
      if ( row.getApproveTimestamp() != null )
        return false;
    } else if ( !approve_timestamp.equals( row.getApproveTimestamp() ) ) {
        return false;
    }
    if ( margin_maintenance_rate == null ) {
      if ( !row.getMarginMaintenanceRateIsNull() )
        return false;
    } else if ( row.getMarginMaintenanceRateIsNull() || ( margin_maintenance_rate.doubleValue() != row.getMarginMaintenanceRate() ) ) {
        return false;
    }
    if ( additional_margin_date == null ) {
      if ( row.getAdditionalMarginDate() != null )
        return false;
    } else if ( !additional_margin_date.equals( row.getAdditionalMarginDate() ) ) {
        return false;
    }
    if ( additional_margin_accrued_days == null ) {
      if ( !row.getAdditionalMarginAccruedDaysIsNull() )
        return false;
    } else if ( row.getAdditionalMarginAccruedDaysIsNull() || ( additional_margin_accrued_days.longValue() != row.getAdditionalMarginAccruedDays() ) ) {
        return false;
    }
    if ( forced_expire_type == null ) {
      if ( row.getForcedExpireType() != null )
        return false;
    } else if ( !forced_expire_type.equals( row.getForcedExpireType() ) ) {
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
        + ((int) last_execution_serial_no)
        + (product_type!=null? product_type.hashCode(): 0) 
        + (market_id!=null? market_id.hashCode(): 0) 
        + ((int) quantity)
        + (limit_price!=null? limit_price.hashCode(): 0) 
        + (execution_condition_type!=null? execution_condition_type.hashCode(): 0) 
        + (price_condition_type!=null? price_condition_type.hashCode(): 0) 
        + (order_condition_type!=null? order_condition_type.hashCode(): 0) 
        + (order_cond_operator!=null? order_cond_operator.hashCode(): 0) 
        + (stop_order_price!=null? stop_order_price.hashCode(): 0) 
        + (w_limit_price!=null? w_limit_price.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (expiration_date!=null? expiration_date.hashCode(): 0) 
        + (confirmed_quantity!=null? confirmed_quantity.hashCode(): 0) 
        + (confirmed_price!=null? confirmed_price.hashCode(): 0) 
        + (executed_quantity!=null? executed_quantity.hashCode(): 0) 
        + (executed_amount!=null? executed_amount.hashCode(): 0) 
        + (order_status!=null? order_status.hashCode(): 0) 
        + (order_open_status!=null? order_open_status.hashCode(): 0) 
        + (expiration_status!=null? expiration_status.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (swap_tax_type!=null? swap_tax_type.hashCode(): 0) 
        + (repayment_type!=null? repayment_type.hashCode(): 0) 
        + (repayment_num!=null? repayment_num.hashCode(): 0) 
        + (sonar_repayment_type!=null? sonar_repayment_type.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
        + ((int) product_id)
        + (quantity_type!=null? quantity_type.hashCode(): 0) 
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (received_date_time!=null? received_date_time.hashCode(): 0) 
        + (voucher_no!=null? voucher_no.hashCode(): 0) 
        + (comm_tbl_no!=null? comm_tbl_no.hashCode(): 0) 
        + (comm_tbl_sub_no!=null? comm_tbl_sub_no.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (estimated_price!=null? estimated_price.hashCode(): 0) 
        + (capital_gain!=null? capital_gain.hashCode(): 0) 
        + (capital_gain_tax!=null? capital_gain_tax.hashCode(): 0) 
        + (sonar_traded_code!=null? sonar_traded_code.hashCode(): 0) 
        + (sonar_market_code!=null? sonar_market_code.hashCode(): 0) 
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (short_sell_order_flag!=null? short_sell_order_flag.hashCode(): 0) 
        + (modify_cancel_type!=null? modify_cancel_type.hashCode(): 0) 
        + (order_root_div!=null? order_root_div.hashCode(): 0) 
        + (submit_order_route_div!=null? submit_order_route_div.hashCode(): 0) 
        + (confirmed_order_price!=null? confirmed_order_price.hashCode(): 0) 
        + (confirmed_estimated_price!=null? confirmed_estimated_price.hashCode(): 0) 
        + (confirmed_exec_condition_type!=null? confirmed_exec_condition_type.hashCode(): 0) 
        + (confirmed_price_condition_type!=null? confirmed_price_condition_type.hashCode(): 0) 
        + (closing_order_type!=null? closing_order_type.hashCode(): 0) 
        + (error_reason_code!=null? error_reason_code.hashCode(): 0) 
        + (request_type!=null? request_type.hashCode(): 0) 
        + (first_order_unit_id!=null? first_order_unit_id.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (confirmed_order_rev!=null? confirmed_order_rev.hashCode(): 0) 
        + (order_rev!=null? order_rev.hashCode(): 0) 
        + (reserve_order_exist_flag!=null? reserve_order_exist_flag.hashCode(): 0) 
        + (original_quantity!=null? original_quantity.hashCode(): 0) 
        + (stop_order_ordered_timestamp!=null? stop_order_ordered_timestamp.hashCode(): 0) 
        + (org_order_condition_type!=null? org_order_condition_type.hashCode(): 0) 
        + (org_order_cond_operator!=null? org_order_cond_operator.hashCode(): 0) 
        + (org_stop_order_price!=null? org_stop_order_price.hashCode(): 0) 
        + (org_w_limit_price!=null? org_w_limit_price.hashCode(): 0) 
        + (org_w_limit_exec_cond_type!=null? org_w_limit_exec_cond_type.hashCode(): 0) 
        + (w_limit_exec_cond_type!=null? w_limit_exec_cond_type.hashCode(): 0) 
        + (w_limit_before_limit_price!=null? w_limit_before_limit_price.hashCode(): 0) 
        + (w_limit_before_exec_cond_type!=null? w_limit_before_exec_cond_type.hashCode(): 0) 
        + (submit_order_delay_flag!=null? submit_order_delay_flag.hashCode(): 0) 
        + (forced_settle_reason_type!=null? forced_settle_reason_type.hashCode(): 0) 
        + (approve_status_type!=null? approve_status_type.hashCode(): 0) 
        + (approver_code!=null? approver_code.hashCode(): 0) 
        + (approve_timestamp!=null? approve_timestamp.hashCode(): 0) 
        + (margin_maintenance_rate!=null? margin_maintenance_rate.hashCode(): 0) 
        + (additional_margin_date!=null? additional_margin_date.hashCode(): 0) 
        + (additional_margin_accrued_days!=null? additional_margin_accrued_days.hashCode(): 0) 
        + (forced_expire_type!=null? forced_expire_type.hashCode(): 0) 
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
		if ( !swap_tax_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'swap_tax_type' must be set before inserting.");
		if ( !biz_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'biz_date' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
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
		if ( order_status_is_set )
			map.put("order_status",order_status);
		if ( order_open_status_is_set )
			map.put("order_open_status",order_open_status);
		map.put("expiration_status",expiration_status);
		map.put("tax_type",tax_type);
		map.put("swap_tax_type",swap_tax_type);
		if ( repayment_type != null )
			map.put("repayment_type",repayment_type);
		if ( repayment_num != null )
			map.put("repayment_num",repayment_num);
		if ( sonar_repayment_type != null )
			map.put("sonar_repayment_type",sonar_repayment_type);
		map.put("biz_date",biz_date);
		map.put("product_id",new Long(product_id));
		if ( quantity_type_is_set )
			map.put("quantity_type",quantity_type);
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
		if ( capital_gain_is_set )
			map.put("capital_gain",capital_gain);
		if ( capital_gain_tax_is_set )
			map.put("capital_gain_tax",capital_gain_tax);
		if ( sonar_traded_code != null )
			map.put("sonar_traded_code",sonar_traded_code);
		if ( sonar_market_code != null )
			map.put("sonar_market_code",sonar_market_code);
		if ( comm_product_code != null )
			map.put("comm_product_code",comm_product_code);
		if ( short_sell_order_flag != null )
			map.put("short_sell_order_flag",short_sell_order_flag);
		if ( modify_cancel_type != null )
			map.put("modify_cancel_type",modify_cancel_type);
		if ( order_root_div != null )
			map.put("order_root_div",order_root_div);
		if ( submit_order_route_div != null )
			map.put("submit_order_route_div",submit_order_route_div);
		if ( confirmed_order_price != null )
			map.put("confirmed_order_price",confirmed_order_price);
		if ( confirmed_estimated_price != null )
			map.put("confirmed_estimated_price",confirmed_estimated_price);
		if ( confirmed_exec_condition_type != null )
			map.put("confirmed_exec_condition_type",confirmed_exec_condition_type);
		if ( confirmed_price_condition_type != null )
			map.put("confirmed_price_condition_type",confirmed_price_condition_type);
		if ( closing_order_type != null )
			map.put("closing_order_type",closing_order_type);
		if ( error_reason_code != null )
			map.put("error_reason_code",error_reason_code);
		if ( request_type != null )
			map.put("request_type",request_type);
		if ( first_order_unit_id != null )
			map.put("first_order_unit_id",first_order_unit_id);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( confirmed_order_rev_is_set )
			map.put("confirmed_order_rev",confirmed_order_rev);
		if ( order_rev_is_set )
			map.put("order_rev",order_rev);
		if ( reserve_order_exist_flag_is_set )
			map.put("reserve_order_exist_flag",reserve_order_exist_flag);
		if ( original_quantity_is_set )
			map.put("original_quantity",original_quantity);
		if ( stop_order_ordered_timestamp != null )
			map.put("stop_order_ordered_timestamp",stop_order_ordered_timestamp);
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
		if ( submit_order_delay_flag_is_set )
			map.put("submit_order_delay_flag",submit_order_delay_flag);
		if ( forced_settle_reason_type != null )
			map.put("forced_settle_reason_type",forced_settle_reason_type);
		if ( approve_status_type != null )
			map.put("approve_status_type",approve_status_type);
		if ( approver_code != null )
			map.put("approver_code",approver_code);
		if ( approve_timestamp != null )
			map.put("approve_timestamp",approve_timestamp);
		if ( margin_maintenance_rate != null )
			map.put("margin_maintenance_rate",margin_maintenance_rate);
		if ( additional_margin_date != null )
			map.put("additional_margin_date",additional_margin_date);
		if ( additional_margin_accrued_days != null )
			map.put("additional_margin_accrued_days",additional_margin_accrued_days);
		if ( forced_expire_type_is_set )
			map.put("forced_expire_type",forced_expire_type);
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
		if ( order_status_is_modified )
			map.put("order_status",order_status);
		if ( order_open_status_is_modified )
			map.put("order_open_status",order_open_status);
		if ( expiration_status_is_modified )
			map.put("expiration_status",expiration_status);
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( swap_tax_type_is_modified )
			map.put("swap_tax_type",swap_tax_type);
		if ( repayment_type_is_modified )
			map.put("repayment_type",repayment_type);
		if ( repayment_num_is_modified )
			map.put("repayment_num",repayment_num);
		if ( sonar_repayment_type_is_modified )
			map.put("sonar_repayment_type",sonar_repayment_type);
		if ( biz_date_is_modified )
			map.put("biz_date",biz_date);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( quantity_type_is_modified )
			map.put("quantity_type",quantity_type);
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
		if ( capital_gain_is_modified )
			map.put("capital_gain",capital_gain);
		if ( capital_gain_tax_is_modified )
			map.put("capital_gain_tax",capital_gain_tax);
		if ( sonar_traded_code_is_modified )
			map.put("sonar_traded_code",sonar_traded_code);
		if ( sonar_market_code_is_modified )
			map.put("sonar_market_code",sonar_market_code);
		if ( comm_product_code_is_modified )
			map.put("comm_product_code",comm_product_code);
		if ( short_sell_order_flag_is_modified )
			map.put("short_sell_order_flag",short_sell_order_flag);
		if ( modify_cancel_type_is_modified )
			map.put("modify_cancel_type",modify_cancel_type);
		if ( order_root_div_is_modified )
			map.put("order_root_div",order_root_div);
		if ( submit_order_route_div_is_modified )
			map.put("submit_order_route_div",submit_order_route_div);
		if ( confirmed_order_price_is_modified )
			map.put("confirmed_order_price",confirmed_order_price);
		if ( confirmed_estimated_price_is_modified )
			map.put("confirmed_estimated_price",confirmed_estimated_price);
		if ( confirmed_exec_condition_type_is_modified )
			map.put("confirmed_exec_condition_type",confirmed_exec_condition_type);
		if ( confirmed_price_condition_type_is_modified )
			map.put("confirmed_price_condition_type",confirmed_price_condition_type);
		if ( closing_order_type_is_modified )
			map.put("closing_order_type",closing_order_type);
		if ( error_reason_code_is_modified )
			map.put("error_reason_code",error_reason_code);
		if ( request_type_is_modified )
			map.put("request_type",request_type);
		if ( first_order_unit_id_is_modified )
			map.put("first_order_unit_id",first_order_unit_id);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( confirmed_order_rev_is_modified )
			map.put("confirmed_order_rev",confirmed_order_rev);
		if ( order_rev_is_modified )
			map.put("order_rev",order_rev);
		if ( reserve_order_exist_flag_is_modified )
			map.put("reserve_order_exist_flag",reserve_order_exist_flag);
		if ( original_quantity_is_modified )
			map.put("original_quantity",original_quantity);
		if ( stop_order_ordered_timestamp_is_modified )
			map.put("stop_order_ordered_timestamp",stop_order_ordered_timestamp);
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
		if ( submit_order_delay_flag_is_modified )
			map.put("submit_order_delay_flag",submit_order_delay_flag);
		if ( forced_settle_reason_type_is_modified )
			map.put("forced_settle_reason_type",forced_settle_reason_type);
		if ( approve_status_type_is_modified )
			map.put("approve_status_type",approve_status_type);
		if ( approver_code_is_modified )
			map.put("approver_code",approver_code);
		if ( approve_timestamp_is_modified )
			map.put("approve_timestamp",approve_timestamp);
		if ( margin_maintenance_rate_is_modified )
			map.put("margin_maintenance_rate",margin_maintenance_rate);
		if ( additional_margin_date_is_modified )
			map.put("additional_margin_date",additional_margin_date);
		if ( additional_margin_accrued_days_is_modified )
			map.put("additional_margin_accrued_days",additional_margin_accrued_days);
		if ( forced_expire_type_is_modified )
			map.put("forced_expire_type",forced_expire_type);
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
			if ( last_execution_serial_no_is_set )
				map.put("last_execution_serial_no",new Integer(last_execution_serial_no));
			if ( product_type_is_set )
				map.put("product_type",product_type);
			map.put("market_id",market_id);
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			map.put("limit_price",limit_price);
			map.put("execution_condition_type",execution_condition_type);
			map.put("price_condition_type",price_condition_type);
			map.put("order_condition_type",order_condition_type);
			map.put("order_cond_operator",order_cond_operator);
			map.put("stop_order_price",stop_order_price);
			map.put("w_limit_price",w_limit_price);
			if ( delivery_date_is_set )
				map.put("delivery_date",delivery_date);
			map.put("expiration_date",expiration_date);
			map.put("confirmed_quantity",confirmed_quantity);
			map.put("confirmed_price",confirmed_price);
			map.put("executed_quantity",executed_quantity);
			map.put("executed_amount",executed_amount);
			if ( order_status_is_set )
				map.put("order_status",order_status);
			if ( order_open_status_is_set )
				map.put("order_open_status",order_open_status);
			if ( expiration_status_is_set )
				map.put("expiration_status",expiration_status);
			if ( tax_type_is_set )
				map.put("tax_type",tax_type);
			if ( swap_tax_type_is_set )
				map.put("swap_tax_type",swap_tax_type);
			map.put("repayment_type",repayment_type);
			map.put("repayment_num",repayment_num);
			map.put("sonar_repayment_type",sonar_repayment_type);
			if ( biz_date_is_set )
				map.put("biz_date",biz_date);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( quantity_type_is_set )
				map.put("quantity_type",quantity_type);
			map.put("order_chanel",order_chanel);
			map.put("received_date_time",received_date_time);
			map.put("voucher_no",voucher_no);
			map.put("comm_tbl_no",comm_tbl_no);
			map.put("comm_tbl_sub_no",comm_tbl_sub_no);
			map.put("sonar_trader_code",sonar_trader_code);
			map.put("price",price);
			map.put("order_request_number",order_request_number);
			map.put("estimated_price",estimated_price);
			if ( capital_gain_is_set )
				map.put("capital_gain",capital_gain);
			if ( capital_gain_tax_is_set )
				map.put("capital_gain_tax",capital_gain_tax);
			map.put("sonar_traded_code",sonar_traded_code);
			map.put("sonar_market_code",sonar_market_code);
			map.put("comm_product_code",comm_product_code);
			map.put("short_sell_order_flag",short_sell_order_flag);
			map.put("modify_cancel_type",modify_cancel_type);
			map.put("order_root_div",order_root_div);
			map.put("submit_order_route_div",submit_order_route_div);
			map.put("confirmed_order_price",confirmed_order_price);
			map.put("confirmed_estimated_price",confirmed_estimated_price);
			map.put("confirmed_exec_condition_type",confirmed_exec_condition_type);
			map.put("confirmed_price_condition_type",confirmed_price_condition_type);
			map.put("closing_order_type",closing_order_type);
			map.put("error_reason_code",error_reason_code);
			map.put("request_type",request_type);
			map.put("first_order_unit_id",first_order_unit_id);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( confirmed_order_rev_is_set )
				map.put("confirmed_order_rev",confirmed_order_rev);
			if ( order_rev_is_set )
				map.put("order_rev",order_rev);
			if ( reserve_order_exist_flag_is_set )
				map.put("reserve_order_exist_flag",reserve_order_exist_flag);
			if ( original_quantity_is_set )
				map.put("original_quantity",original_quantity);
			map.put("stop_order_ordered_timestamp",stop_order_ordered_timestamp);
			map.put("org_order_condition_type",org_order_condition_type);
			map.put("org_order_cond_operator",org_order_cond_operator);
			map.put("org_stop_order_price",org_stop_order_price);
			map.put("org_w_limit_price",org_w_limit_price);
			map.put("org_w_limit_exec_cond_type",org_w_limit_exec_cond_type);
			map.put("w_limit_exec_cond_type",w_limit_exec_cond_type);
			map.put("w_limit_before_limit_price",w_limit_before_limit_price);
			map.put("w_limit_before_exec_cond_type",w_limit_before_exec_cond_type);
			if ( submit_order_delay_flag_is_set )
				map.put("submit_order_delay_flag",submit_order_delay_flag);
			map.put("forced_settle_reason_type",forced_settle_reason_type);
			map.put("approve_status_type",approve_status_type);
			map.put("approver_code",approver_code);
			map.put("approve_timestamp",approve_timestamp);
			map.put("margin_maintenance_rate",margin_maintenance_rate);
			map.put("additional_margin_date",additional_margin_date);
			map.put("additional_margin_accrued_days",additional_margin_accrued_days);
			if ( forced_expire_type_is_set )
				map.put("forced_expire_type",forced_expire_type);
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
   * <em>swap_tax_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getSwapTaxType()
  {
    return swap_tax_type;
  }


  /** 
   * <em>swap_tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwapTaxTypeIsSet() {
    return swap_tax_type_is_set;
  }


  /** 
   * <em>swap_tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwapTaxTypeIsModified() {
    return swap_tax_type_is_modified;
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
   * <em>repayment_num</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getRepaymentNum()
  {
    return ( repayment_num==null? ((int)0): repayment_num.intValue() );
  }


  /** 
   * <em>repayment_num</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRepaymentNumIsNull()
  {
    return repayment_num == null;
  }


  /** 
   * <em>repayment_num</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepaymentNumIsSet() {
    return repayment_num_is_set;
  }


  /** 
   * <em>repayment_num</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepaymentNumIsModified() {
    return repayment_num_is_modified;
  }


  /** 
   * <em>sonar_repayment_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarRepaymentType()
  {
    return sonar_repayment_type;
  }


  /** 
   * <em>sonar_repayment_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarRepaymentTypeIsSet() {
    return sonar_repayment_type_is_set;
  }


  /** 
   * <em>sonar_repayment_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarRepaymentTypeIsModified() {
    return sonar_repayment_type_is_modified;
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
   * <em>short_sell_order_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShortSellOrderFlag()
  {
    return short_sell_order_flag;
  }


  /** 
   * <em>short_sell_order_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortSellOrderFlagIsSet() {
    return short_sell_order_flag_is_set;
  }


  /** 
   * <em>short_sell_order_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortSellOrderFlagIsModified() {
    return short_sell_order_flag_is_modified;
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
   * <em>submit_order_route_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSubmitOrderRouteDiv()
  {
    return submit_order_route_div;
  }


  /** 
   * <em>submit_order_route_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubmitOrderRouteDivIsSet() {
    return submit_order_route_div_is_set;
  }


  /** 
   * <em>submit_order_route_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubmitOrderRouteDivIsModified() {
    return submit_order_route_div_is_modified;
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
   * <em>confirmed_price_condition_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getConfirmedPriceConditionType()
  {
    return confirmed_price_condition_type;
  }


  /** 
   * <em>confirmed_price_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedPriceConditionTypeIsSet() {
    return confirmed_price_condition_type_is_set;
  }


  /** 
   * <em>confirmed_price_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedPriceConditionTypeIsModified() {
    return confirmed_price_condition_type_is_modified;
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
   * <em>confirmed_order_rev</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getConfirmedOrderRev()
  {
    return confirmed_order_rev;
  }


  /** 
   * <em>confirmed_order_rev</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedOrderRevIsSet() {
    return confirmed_order_rev_is_set;
  }


  /** 
   * <em>confirmed_order_rev</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedOrderRevIsModified() {
    return confirmed_order_rev_is_modified;
  }


  /** 
   * <em>order_rev</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRev()
  {
    return order_rev;
  }


  /** 
   * <em>order_rev</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRevIsSet() {
    return order_rev_is_set;
  }


  /** 
   * <em>order_rev</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRevIsModified() {
    return order_rev_is_modified;
  }


  /** 
   * <em>reserve_order_exist_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReserveOrderExistFlag()
  {
    return reserve_order_exist_flag;
  }


  /** 
   * <em>reserve_order_exist_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReserveOrderExistFlagIsSet() {
    return reserve_order_exist_flag_is_set;
  }


  /** 
   * <em>reserve_order_exist_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReserveOrderExistFlagIsModified() {
    return reserve_order_exist_flag_is_modified;
  }


  /** 
   * <em>original_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOriginalQuantity()
  {
    return ( original_quantity==null? ((double)0): original_quantity.doubleValue() );
  }


  /** 
   * <em>original_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOriginalQuantityIsNull()
  {
    return original_quantity == null;
  }


  /** 
   * <em>original_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOriginalQuantityIsSet() {
    return original_quantity_is_set;
  }


  /** 
   * <em>original_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOriginalQuantityIsModified() {
    return original_quantity_is_modified;
  }


  /** 
   * <em>stop_order_ordered_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getStopOrderOrderedTimestamp()
  {
    return stop_order_ordered_timestamp;
  }


  /** 
   * <em>stop_order_ordered_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopOrderOrderedTimestampIsSet() {
    return stop_order_ordered_timestamp_is_set;
  }


  /** 
   * <em>stop_order_ordered_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopOrderOrderedTimestampIsModified() {
    return stop_order_ordered_timestamp_is_modified;
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
   * <em>submit_order_delay_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getSubmitOrderDelayFlag()
  {
    return submit_order_delay_flag;
  }


  /** 
   * <em>submit_order_delay_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubmitOrderDelayFlagIsSet() {
    return submit_order_delay_flag_is_set;
  }


  /** 
   * <em>submit_order_delay_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubmitOrderDelayFlagIsModified() {
    return submit_order_delay_flag_is_modified;
  }


  /** 
   * <em>forced_settle_reason_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForcedSettleReasonType()
  {
    return forced_settle_reason_type;
  }


  /** 
   * <em>forced_settle_reason_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForcedSettleReasonTypeIsSet() {
    return forced_settle_reason_type_is_set;
  }


  /** 
   * <em>forced_settle_reason_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForcedSettleReasonTypeIsModified() {
    return forced_settle_reason_type_is_modified;
  }


  /** 
   * <em>approve_status_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getApproveStatusType()
  {
    return approve_status_type;
  }


  /** 
   * <em>approve_status_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApproveStatusTypeIsSet() {
    return approve_status_type_is_set;
  }


  /** 
   * <em>approve_status_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApproveStatusTypeIsModified() {
    return approve_status_type_is_modified;
  }


  /** 
   * <em>approver_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getApproverCode()
  {
    return approver_code;
  }


  /** 
   * <em>approver_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApproverCodeIsSet() {
    return approver_code_is_set;
  }


  /** 
   * <em>approver_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApproverCodeIsModified() {
    return approver_code_is_modified;
  }


  /** 
   * <em>approve_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getApproveTimestamp()
  {
    return approve_timestamp;
  }


  /** 
   * <em>approve_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApproveTimestampIsSet() {
    return approve_timestamp_is_set;
  }


  /** 
   * <em>approve_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApproveTimestampIsModified() {
    return approve_timestamp_is_modified;
  }


  /** 
   * <em>margin_maintenance_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginMaintenanceRate()
  {
    return ( margin_maintenance_rate==null? ((double)0): margin_maintenance_rate.doubleValue() );
  }


  /** 
   * <em>margin_maintenance_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginMaintenanceRateIsNull()
  {
    return margin_maintenance_rate == null;
  }


  /** 
   * <em>margin_maintenance_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceRateIsSet() {
    return margin_maintenance_rate_is_set;
  }


  /** 
   * <em>margin_maintenance_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceRateIsModified() {
    return margin_maintenance_rate_is_modified;
  }


  /** 
   * <em>additional_margin_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAdditionalMarginDate()
  {
    return additional_margin_date;
  }


  /** 
   * <em>additional_margin_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdditionalMarginDateIsSet() {
    return additional_margin_date_is_set;
  }


  /** 
   * <em>additional_margin_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdditionalMarginDateIsModified() {
    return additional_margin_date_is_modified;
  }


  /** 
   * <em>additional_margin_accrued_days</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAdditionalMarginAccruedDays()
  {
    return ( additional_margin_accrued_days==null? ((long)0): additional_margin_accrued_days.longValue() );
  }


  /** 
   * <em>additional_margin_accrued_days</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAdditionalMarginAccruedDaysIsNull()
  {
    return additional_margin_accrued_days == null;
  }


  /** 
   * <em>additional_margin_accrued_days</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdditionalMarginAccruedDaysIsSet() {
    return additional_margin_accrued_days_is_set;
  }


  /** 
   * <em>additional_margin_accrued_days</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdditionalMarginAccruedDaysIsModified() {
    return additional_margin_accrued_days_is_modified;
  }


  /** 
   * <em>forced_expire_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForcedExpireType()
  {
    return forced_expire_type;
  }


  /** 
   * <em>forced_expire_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForcedExpireTypeIsSet() {
    return forced_expire_type_is_set;
  }


  /** 
   * <em>forced_expire_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForcedExpireTypeIsModified() {
    return forced_expire_type_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new EqtypeOrderUnitPK(order_unit_id);
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
   * <em>swap_tax_type</em>カラムの値を設定します。 
   *
   * @@param p_swapTaxType <em>swap_tax_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum値 
   */
  public final void setSwapTaxType( com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_swapTaxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swap_tax_type = p_swapTaxType;
    swap_tax_type_is_set = true;
    swap_tax_type_is_modified = true;
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
   * <em>repayment_num</em>カラムの値を設定します。 
   *
   * @@param p_repaymentNum <em>repayment_num</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setRepaymentNum( int p_repaymentNum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    repayment_num = new Integer(p_repaymentNum);
    repayment_num_is_set = true;
    repayment_num_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>repayment_num</em>カラムに値を設定します。 
   */
  public final void setRepaymentNum( Integer p_repaymentNum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    repayment_num = p_repaymentNum;
    repayment_num_is_set = true;
    repayment_num_is_modified = true;
  }


  /** 
   * <em>sonar_repayment_type</em>カラムの値を設定します。 
   *
   * @@param p_sonarRepaymentType <em>sonar_repayment_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSonarRepaymentType( String p_sonarRepaymentType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_repayment_type = p_sonarRepaymentType;
    sonar_repayment_type_is_set = true;
    sonar_repayment_type_is_modified = true;
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
   * <em>short_sell_order_flag</em>カラムの値を設定します。 
   *
   * @@param p_shortSellOrderFlag <em>short_sell_order_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setShortSellOrderFlag( String p_shortSellOrderFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_sell_order_flag = p_shortSellOrderFlag;
    short_sell_order_flag_is_set = true;
    short_sell_order_flag_is_modified = true;
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
   * <em>submit_order_route_div</em>カラムの値を設定します。 
   *
   * @@param p_submitOrderRouteDiv <em>submit_order_route_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSubmitOrderRouteDiv( String p_submitOrderRouteDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    submit_order_route_div = p_submitOrderRouteDiv;
    submit_order_route_div_is_set = true;
    submit_order_route_div_is_modified = true;
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
   * <em>confirmed_price_condition_type</em>カラムの値を設定します。 
   *
   * @@param p_confirmedPriceConditionType <em>confirmed_price_condition_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setConfirmedPriceConditionType( String p_confirmedPriceConditionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_price_condition_type = p_confirmedPriceConditionType;
    confirmed_price_condition_type_is_set = true;
    confirmed_price_condition_type_is_modified = true;
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
   * <em>confirmed_order_rev</em>カラムの値を設定します。 
   *
   * @@param p_confirmedOrderRev <em>confirmed_order_rev</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setConfirmedOrderRev( String p_confirmedOrderRev )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_order_rev = p_confirmedOrderRev;
    confirmed_order_rev_is_set = true;
    confirmed_order_rev_is_modified = true;
  }


  /** 
   * <em>order_rev</em>カラムの値を設定します。 
   *
   * @@param p_orderRev <em>order_rev</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setOrderRev( String p_orderRev )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_rev = p_orderRev;
    order_rev_is_set = true;
    order_rev_is_modified = true;
  }


  /** 
   * <em>reserve_order_exist_flag</em>カラムの値を設定します。 
   *
   * @@param p_reserveOrderExistFlag <em>reserve_order_exist_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setReserveOrderExistFlag( String p_reserveOrderExistFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reserve_order_exist_flag = p_reserveOrderExistFlag;
    reserve_order_exist_flag_is_set = true;
    reserve_order_exist_flag_is_modified = true;
  }


  /** 
   * <em>original_quantity</em>カラムの値を設定します。 
   *
   * @@param p_originalQuantity <em>original_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOriginalQuantity( double p_originalQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    original_quantity = new Double(p_originalQuantity);
    original_quantity_is_set = true;
    original_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>original_quantity</em>カラムに値を設定します。 
   */
  public final void setOriginalQuantity( Double p_originalQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    original_quantity = p_originalQuantity;
    original_quantity_is_set = true;
    original_quantity_is_modified = true;
  }


  /** 
   * <em>stop_order_ordered_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_stopOrderOrderedTimestamp <em>stop_order_ordered_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setStopOrderOrderedTimestamp( java.sql.Timestamp p_stopOrderOrderedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_order_ordered_timestamp = p_stopOrderOrderedTimestamp;
    stop_order_ordered_timestamp_is_set = true;
    stop_order_ordered_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>stop_order_ordered_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setStopOrderOrderedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    stop_order_ordered_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    stop_order_ordered_timestamp_is_set = true;
    stop_order_ordered_timestamp_is_modified = true;
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
   * <em>submit_order_delay_flag</em>カラムの値を設定します。 
   *
   * @@param p_submitOrderDelayFlag <em>submit_order_delay_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setSubmitOrderDelayFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_submitOrderDelayFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    submit_order_delay_flag = p_submitOrderDelayFlag;
    submit_order_delay_flag_is_set = true;
    submit_order_delay_flag_is_modified = true;
  }


  /** 
   * <em>forced_settle_reason_type</em>カラムの値を設定します。 
   *
   * @@param p_forcedSettleReasonType <em>forced_settle_reason_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForcedSettleReasonType( String p_forcedSettleReasonType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    forced_settle_reason_type = p_forcedSettleReasonType;
    forced_settle_reason_type_is_set = true;
    forced_settle_reason_type_is_modified = true;
  }


  /** 
   * <em>approve_status_type</em>カラムの値を設定します。 
   *
   * @@param p_approveStatusType <em>approve_status_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setApproveStatusType( String p_approveStatusType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    approve_status_type = p_approveStatusType;
    approve_status_type_is_set = true;
    approve_status_type_is_modified = true;
  }


  /** 
   * <em>approver_code</em>カラムの値を設定します。 
   *
   * @@param p_approverCode <em>approver_code</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setApproverCode( String p_approverCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    approver_code = p_approverCode;
    approver_code_is_set = true;
    approver_code_is_modified = true;
  }


  /** 
   * <em>approve_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_approveTimestamp <em>approve_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setApproveTimestamp( java.sql.Timestamp p_approveTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    approve_timestamp = p_approveTimestamp;
    approve_timestamp_is_set = true;
    approve_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>approve_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setApproveTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    approve_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    approve_timestamp_is_set = true;
    approve_timestamp_is_modified = true;
  }


  /** 
   * <em>margin_maintenance_rate</em>カラムの値を設定します。 
   *
   * @@param p_marginMaintenanceRate <em>margin_maintenance_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginMaintenanceRate( double p_marginMaintenanceRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_maintenance_rate = new Double(p_marginMaintenanceRate);
    margin_maintenance_rate_is_set = true;
    margin_maintenance_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_maintenance_rate</em>カラムに値を設定します。 
   */
  public final void setMarginMaintenanceRate( Double p_marginMaintenanceRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_maintenance_rate = p_marginMaintenanceRate;
    margin_maintenance_rate_is_set = true;
    margin_maintenance_rate_is_modified = true;
  }


  /** 
   * <em>additional_margin_date</em>カラムの値を設定します。 
   *
   * @@param p_additionalMarginDate <em>additional_margin_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAdditionalMarginDate( java.sql.Timestamp p_additionalMarginDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    additional_margin_date = p_additionalMarginDate;
    additional_margin_date_is_set = true;
    additional_margin_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>additional_margin_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAdditionalMarginDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    additional_margin_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    additional_margin_date_is_set = true;
    additional_margin_date_is_modified = true;
  }


  /** 
   * <em>additional_margin_accrued_days</em>カラムの値を設定します。 
   *
   * @@param p_additionalMarginAccruedDays <em>additional_margin_accrued_days</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAdditionalMarginAccruedDays( long p_additionalMarginAccruedDays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    additional_margin_accrued_days = new Long(p_additionalMarginAccruedDays);
    additional_margin_accrued_days_is_set = true;
    additional_margin_accrued_days_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>additional_margin_accrued_days</em>カラムに値を設定します。 
   */
  public final void setAdditionalMarginAccruedDays( Long p_additionalMarginAccruedDays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    additional_margin_accrued_days = p_additionalMarginAccruedDays;
    additional_margin_accrued_days_is_set = true;
    additional_margin_accrued_days_is_modified = true;
  }


  /** 
   * <em>forced_expire_type</em>カラムの値を設定します。 
   *
   * @@param p_forcedExpireType <em>forced_expire_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForcedExpireType( String p_forcedExpireType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    forced_expire_type = p_forcedExpireType;
    forced_expire_type_is_set = true;
    forced_expire_type_is_modified = true;
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
                else if ( name.equals("approve_status_type") ) {
                    return this.approve_status_type;
                }
                else if ( name.equals("approver_code") ) {
                    return this.approver_code;
                }
                else if ( name.equals("approve_timestamp") ) {
                    return this.approve_timestamp;
                }
                else if ( name.equals("additional_margin_date") ) {
                    return this.additional_margin_date;
                }
                else if ( name.equals("additional_margin_accrued_days") ) {
                    return this.additional_margin_accrued_days;
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
                else if ( name.equals("comm_tbl_no") ) {
                    return this.comm_tbl_no;
                }
                else if ( name.equals("comm_tbl_sub_no") ) {
                    return this.comm_tbl_sub_no;
                }
                else if ( name.equals("capital_gain") ) {
                    return this.capital_gain;
                }
                else if ( name.equals("capital_gain_tax") ) {
                    return this.capital_gain_tax;
                }
                else if ( name.equals("comm_product_code") ) {
                    return this.comm_product_code;
                }
                else if ( name.equals("confirmed_order_price") ) {
                    return this.confirmed_order_price;
                }
                else if ( name.equals("confirmed_estimated_price") ) {
                    return this.confirmed_estimated_price;
                }
                else if ( name.equals("confirmed_exec_condition_type") ) {
                    return this.confirmed_exec_condition_type;
                }
                else if ( name.equals("confirmed_price_condition_type") ) {
                    return this.confirmed_price_condition_type;
                }
                else if ( name.equals("closing_order_type") ) {
                    return this.closing_order_type;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("confirmed_order_rev") ) {
                    return this.confirmed_order_rev;
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
                else if ( name.equals("expiration_status") ) {
                    return this.expiration_status;
                }
                else if ( name.equals("estimated_price") ) {
                    return this.estimated_price;
                }
                else if ( name.equals("error_reason_code") ) {
                    return this.error_reason_code;
                }
                break;
            case 'f':
                if ( name.equals("first_order_unit_id") ) {
                    return this.first_order_unit_id;
                }
                else if ( name.equals("forced_settle_reason_type") ) {
                    return this.forced_settle_reason_type;
                }
                else if ( name.equals("forced_expire_type") ) {
                    return this.forced_expire_type;
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
                else if ( name.equals("margin_maintenance_rate") ) {
                    return this.margin_maintenance_rate;
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
                else if ( name.equals("order_rev") ) {
                    return this.order_rev;
                }
                else if ( name.equals("original_quantity") ) {
                    return this.original_quantity;
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
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("price_condition_type") ) {
                    return this.price_condition_type;
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
                else if ( name.equals("quantity_type") ) {
                    return this.quantity_type;
                }
                break;
            case 'r':
                if ( name.equals("repayment_type") ) {
                    return this.repayment_type;
                }
                else if ( name.equals("repayment_num") ) {
                    return this.repayment_num;
                }
                else if ( name.equals("received_date_time") ) {
                    return this.received_date_time;
                }
                else if ( name.equals("request_type") ) {
                    return this.request_type;
                }
                else if ( name.equals("reserve_order_exist_flag") ) {
                    return this.reserve_order_exist_flag;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("stop_order_price") ) {
                    return this.stop_order_price;
                }
                else if ( name.equals("swap_tax_type") ) {
                    return this.swap_tax_type;
                }
                else if ( name.equals("sonar_repayment_type") ) {
                    return this.sonar_repayment_type;
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
                else if ( name.equals("short_sell_order_flag") ) {
                    return this.short_sell_order_flag;
                }
                else if ( name.equals("submit_order_route_div") ) {
                    return this.submit_order_route_div;
                }
                else if ( name.equals("stop_order_ordered_timestamp") ) {
                    return this.stop_order_ordered_timestamp;
                }
                else if ( name.equals("submit_order_delay_flag") ) {
                    return this.submit_order_delay_flag;
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    return this.trader_id;
                }
                else if ( name.equals("tax_type") ) {
                    return this.tax_type;
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
                else if ( name.equals("approve_status_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'approve_status_type' must be String: '"+value+"' is not." );
                    this.approve_status_type = (String) value;
                    if (this.approve_status_type_is_set)
                        this.approve_status_type_is_modified = true;
                    this.approve_status_type_is_set = true;
                    return;
                }
                else if ( name.equals("approver_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'approver_code' must be String: '"+value+"' is not." );
                    this.approver_code = (String) value;
                    if (this.approver_code_is_set)
                        this.approver_code_is_modified = true;
                    this.approver_code_is_set = true;
                    return;
                }
                else if ( name.equals("approve_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'approve_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.approve_timestamp = (java.sql.Timestamp) value;
                    if (this.approve_timestamp_is_set)
                        this.approve_timestamp_is_modified = true;
                    this.approve_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("additional_margin_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'additional_margin_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.additional_margin_date = (java.sql.Timestamp) value;
                    if (this.additional_margin_date_is_set)
                        this.additional_margin_date_is_modified = true;
                    this.additional_margin_date_is_set = true;
                    return;
                }
                else if ( name.equals("additional_margin_accrued_days") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'additional_margin_accrued_days' must be Long: '"+value+"' is not." );
                    this.additional_margin_accrued_days = (Long) value;
                    if (this.additional_margin_accrued_days_is_set)
                        this.additional_margin_accrued_days_is_modified = true;
                    this.additional_margin_accrued_days_is_set = true;
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
                else if ( name.equals("confirmed_price_condition_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'confirmed_price_condition_type' must be String: '"+value+"' is not." );
                    this.confirmed_price_condition_type = (String) value;
                    if (this.confirmed_price_condition_type_is_set)
                        this.confirmed_price_condition_type_is_modified = true;
                    this.confirmed_price_condition_type_is_set = true;
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
                else if ( name.equals("confirmed_order_rev") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'confirmed_order_rev' must be String: '"+value+"' is not." );
                    this.confirmed_order_rev = (String) value;
                    if (this.confirmed_order_rev_is_set)
                        this.confirmed_order_rev_is_modified = true;
                    this.confirmed_order_rev_is_set = true;
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
                break;
            case 'f':
                if ( name.equals("first_order_unit_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'first_order_unit_id' must be Long: '"+value+"' is not." );
                    this.first_order_unit_id = (Long) value;
                    if (this.first_order_unit_id_is_set)
                        this.first_order_unit_id_is_modified = true;
                    this.first_order_unit_id_is_set = true;
                    return;
                }
                else if ( name.equals("forced_settle_reason_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'forced_settle_reason_type' must be String: '"+value+"' is not." );
                    this.forced_settle_reason_type = (String) value;
                    if (this.forced_settle_reason_type_is_set)
                        this.forced_settle_reason_type_is_modified = true;
                    this.forced_settle_reason_type_is_set = true;
                    return;
                }
                else if ( name.equals("forced_expire_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'forced_expire_type' must be String: '"+value+"' is not." );
                    this.forced_expire_type = (String) value;
                    if (this.forced_expire_type_is_set)
                        this.forced_expire_type_is_modified = true;
                    this.forced_expire_type_is_set = true;
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
                else if ( name.equals("margin_maintenance_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_maintenance_rate' must be Double: '"+value+"' is not." );
                    this.margin_maintenance_rate = (Double) value;
                    if (this.margin_maintenance_rate_is_set)
                        this.margin_maintenance_rate_is_modified = true;
                    this.margin_maintenance_rate_is_set = true;
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
                else if ( name.equals("order_rev") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_rev' must be String: '"+value+"' is not." );
                    this.order_rev = (String) value;
                    if (this.order_rev_is_set)
                        this.order_rev_is_modified = true;
                    this.order_rev_is_set = true;
                    return;
                }
                else if ( name.equals("original_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'original_quantity' must be Double: '"+value+"' is not." );
                    this.original_quantity = (Double) value;
                    if (this.original_quantity_is_set)
                        this.original_quantity_is_modified = true;
                    this.original_quantity_is_set = true;
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
                if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
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
                else if ( name.equals("repayment_num") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'repayment_num' must be Integer: '"+value+"' is not." );
                    this.repayment_num = (Integer) value;
                    if (this.repayment_num_is_set)
                        this.repayment_num_is_modified = true;
                    this.repayment_num_is_set = true;
                    return;
                }
                else if ( name.equals("received_date_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'received_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.received_date_time = (java.sql.Timestamp) value;
                    if (this.received_date_time_is_set)
                        this.received_date_time_is_modified = true;
                    this.received_date_time_is_set = true;
                    return;
                }
                else if ( name.equals("request_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_type' must be String: '"+value+"' is not." );
                    this.request_type = (String) value;
                    if (this.request_type_is_set)
                        this.request_type_is_modified = true;
                    this.request_type_is_set = true;
                    return;
                }
                else if ( name.equals("reserve_order_exist_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reserve_order_exist_flag' must be String: '"+value+"' is not." );
                    this.reserve_order_exist_flag = (String) value;
                    if (this.reserve_order_exist_flag_is_set)
                        this.reserve_order_exist_flag_is_modified = true;
                    this.reserve_order_exist_flag_is_set = true;
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
                else if ( name.equals("swap_tax_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) )
                        throw new IllegalArgumentException( "value for 'swap_tax_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum: '"+value+"' is not." );
                    this.swap_tax_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) value;
                    if (this.swap_tax_type_is_set)
                        this.swap_tax_type_is_modified = true;
                    this.swap_tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_repayment_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_repayment_type' must be String: '"+value+"' is not." );
                    this.sonar_repayment_type = (String) value;
                    if (this.sonar_repayment_type_is_set)
                        this.sonar_repayment_type_is_modified = true;
                    this.sonar_repayment_type_is_set = true;
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
                else if ( name.equals("short_sell_order_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_sell_order_flag' must be String: '"+value+"' is not." );
                    this.short_sell_order_flag = (String) value;
                    if (this.short_sell_order_flag_is_set)
                        this.short_sell_order_flag_is_modified = true;
                    this.short_sell_order_flag_is_set = true;
                    return;
                }
                else if ( name.equals("submit_order_route_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'submit_order_route_div' must be String: '"+value+"' is not." );
                    this.submit_order_route_div = (String) value;
                    if (this.submit_order_route_div_is_set)
                        this.submit_order_route_div_is_modified = true;
                    this.submit_order_route_div_is_set = true;
                    return;
                }
                else if ( name.equals("stop_order_ordered_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'stop_order_ordered_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.stop_order_ordered_timestamp = (java.sql.Timestamp) value;
                    if (this.stop_order_ordered_timestamp_is_set)
                        this.stop_order_ordered_timestamp_is_modified = true;
                    this.stop_order_ordered_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("submit_order_delay_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'submit_order_delay_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.submit_order_delay_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.submit_order_delay_flag_is_set)
                        this.submit_order_delay_flag_is_modified = true;
                    this.submit_order_delay_flag_is_set = true;
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
