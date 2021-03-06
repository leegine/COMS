head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostFotypeOrderHistoryParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * host_fotype_order_historyテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostFotypeOrderHistoryRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostFotypeOrderHistoryParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostFotypeOrderHistoryParams}が{@@link HostFotypeOrderHistoryRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostFotypeOrderHistoryPK 
 * @@see HostFotypeOrderHistoryRow 
 */
public class HostFotypeOrderHistoryParams extends Params implements HostFotypeOrderHistoryRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_fotype_order_history";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostFotypeOrderHistoryRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostFotypeOrderHistoryRow.TYPE;
  }


  /** 
   * <em>rowid</em>カラムの値 
   */
  public  String  rowid;

  /** 
   * <em>request_code</em>カラムの値 
   */
  public  String  request_code;

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
   * <em>sonar_market_code</em>カラムの値 
   */
  public  String  sonar_market_code;

  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>target_product_code</em>カラムの値 
   */
  public  String  target_product_code;

  /** 
   * <em>delivery_month_yyyy</em>カラムの値 
   */
  public  String  delivery_month_yyyy;

  /** 
   * <em>delivery_month_mm</em>カラムの値 
   */
  public  String  delivery_month_mm;

  /** 
   * <em>future_option_product_type</em>カラムの値 
   */
  public  String  future_option_product_type;

  /** 
   * <em>strike_price</em>カラムの値 
   */
  public  Double  strike_price;

  /** 
   * <em>corp_code</em>カラムの値 
   */
  public  String  corp_code;

  /** 
   * <em>front_order_exchange_code</em>カラムの値 
   */
  public  String  front_order_exchange_code;

  /** 
   * <em>front_order_system_code</em>カラムの値 
   */
  public  String  front_order_system_code;

  /** 
   * <em>front_order_trade_code</em>カラムの値 
   */
  public  String  front_order_trade_code;

  /** 
   * <em>received_date_time</em>カラムの値 
   */
  public  String  received_date_time;

  /** 
   * <em>received_time</em>カラムの値 
   */
  public  String  received_time;

  /** 
   * <em>data_class_code</em>カラムの値 
   */
  public  String  data_class_code;

  /** 
   * <em>data_class_detail_code</em>カラムの値 
   */
  public  String  data_class_detail_code;

  /** 
   * <em>buy_sell_div</em>カラムの値 
   */
  public  String  buy_sell_div;

  /** 
   * <em>execution_condition</em>カラムの値 
   */
  public  String  execution_condition;

  /** 
   * <em>front_order_time</em>カラムの値 
   */
  public  String  front_order_time;

  /** 
   * <em>sell_order_quantity</em>カラムの値 
   */
  public  Double  sell_order_quantity;

  /** 
   * <em>buy_order_quantity</em>カラムの値 
   */
  public  Double  buy_order_quantity;

  /** 
   * <em>limit_price</em>カラムの値 
   */
  public  Double  limit_price;

  /** 
   * <em>stop_order_price</em>カラムの値 
   */
  public  Double  stop_order_price;

  /** 
   * <em>w_limit_price</em>カラムの値 
   */
  public  Double  w_limit_price;

  /** 
   * <em>transaction_type</em>カラムの値 
   */
  public  String  transaction_type;

  /** 
   * <em>contract_check</em>カラムの値 
   */
  public  String  contract_check;

  /** 
   * <em>order_chanel</em>カラムの値 
   */
  public  String  order_chanel;

  /** 
   * <em>commision_number</em>カラムの値 
   */
  public  String  commision_number;

  /** 
   * <em>commision_branch_number</em>カラムの値 
   */
  public  String  commision_branch_number;

  /** 
   * <em>commision_product_code</em>カラムの値 
   */
  public  String  commision_product_code;

  /** 
   * <em>change_quantity</em>カラムの値 
   */
  public  Double  change_quantity;

  /** 
   * <em>modified_limit_price</em>カラムの値 
   */
  public  Double  modified_limit_price;

  /** 
   * <em>modified_execution_type</em>カラムの値 
   */
  public  String  modified_execution_type;

  /** 
   * <em>modified_stop_order_price</em>カラムの値 
   */
  public  Double  modified_stop_order_price;

  /** 
   * <em>modified_w_limit_price</em>カラムの値 
   */
  public  Double  modified_w_limit_price;

  /** 
   * <em>cancel_div</em>カラムの値 
   */
  public  String  cancel_div;

  /** 
   * <em>org_corp_code</em>カラムの値 
   */
  public  String  org_corp_code;

  /** 
   * <em>canceled_quantity</em>カラムの値 
   */
  public  Double  canceled_quantity;

  /** 
   * <em>exec_price</em>カラムの値 
   */
  public  Double  exec_price;

  /** 
   * <em>exec_quantity</em>カラムの値 
   */
  public  Double  exec_quantity;

  /** 
   * <em>exec_time</em>カラムの値 
   */
  public  String  exec_time;

  /** 
   * <em>price_mark</em>カラムの値 
   */
  public  String  price_mark;

  /** 
   * <em>exec_number</em>カラムの値 
   */
  public  int  exec_number;

  /** 
   * <em>accept_number</em>カラムの値 
   */
  public  String  accept_number;

  /** 
   * <em>virtual_server_number_market</em>カラムの値 
   */
  public  String  virtual_server_number_market;

  /** 
   * <em>executed_quantity</em>カラムの値 
   */
  public  Double  executed_quantity;

  /** 
   * <em>price</em>カラムの値 
   */
  public  Double  price;

  /** 
   * <em>stop_mark</em>カラムの値 
   */
  public  String  stop_mark;

  /** 
   * <em>expiration_quantity</em>カラムの値 
   */
  public  Double  expiration_quantity;

  /** 
   * <em>reason_code</em>カラムの値 
   */
  public  String  reason_code;

  /** 
   * <em>expiration_time</em>カラムの値 
   */
  public  String  expiration_time;

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

  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean sonar_market_code_is_set = false;
  private boolean sonar_market_code_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean target_product_code_is_set = false;
  private boolean target_product_code_is_modified = false;
  private boolean delivery_month_yyyy_is_set = false;
  private boolean delivery_month_yyyy_is_modified = false;
  private boolean delivery_month_mm_is_set = false;
  private boolean delivery_month_mm_is_modified = false;
  private boolean future_option_product_type_is_set = false;
  private boolean future_option_product_type_is_modified = false;
  private boolean strike_price_is_set = false;
  private boolean strike_price_is_modified = false;
  private boolean corp_code_is_set = false;
  private boolean corp_code_is_modified = false;
  private boolean front_order_exchange_code_is_set = false;
  private boolean front_order_exchange_code_is_modified = false;
  private boolean front_order_system_code_is_set = false;
  private boolean front_order_system_code_is_modified = false;
  private boolean front_order_trade_code_is_set = false;
  private boolean front_order_trade_code_is_modified = false;
  private boolean received_date_time_is_set = false;
  private boolean received_date_time_is_modified = false;
  private boolean received_time_is_set = false;
  private boolean received_time_is_modified = false;
  private boolean data_class_code_is_set = false;
  private boolean data_class_code_is_modified = false;
  private boolean data_class_detail_code_is_set = false;
  private boolean data_class_detail_code_is_modified = false;
  private boolean buy_sell_div_is_set = false;
  private boolean buy_sell_div_is_modified = false;
  private boolean execution_condition_is_set = false;
  private boolean execution_condition_is_modified = false;
  private boolean front_order_time_is_set = false;
  private boolean front_order_time_is_modified = false;
  private boolean sell_order_quantity_is_set = false;
  private boolean sell_order_quantity_is_modified = false;
  private boolean buy_order_quantity_is_set = false;
  private boolean buy_order_quantity_is_modified = false;
  private boolean limit_price_is_set = false;
  private boolean limit_price_is_modified = false;
  private boolean stop_order_price_is_set = false;
  private boolean stop_order_price_is_modified = false;
  private boolean w_limit_price_is_set = false;
  private boolean w_limit_price_is_modified = false;
  private boolean transaction_type_is_set = false;
  private boolean transaction_type_is_modified = false;
  private boolean contract_check_is_set = false;
  private boolean contract_check_is_modified = false;
  private boolean order_chanel_is_set = false;
  private boolean order_chanel_is_modified = false;
  private boolean commision_number_is_set = false;
  private boolean commision_number_is_modified = false;
  private boolean commision_branch_number_is_set = false;
  private boolean commision_branch_number_is_modified = false;
  private boolean commision_product_code_is_set = false;
  private boolean commision_product_code_is_modified = false;
  private boolean change_quantity_is_set = false;
  private boolean change_quantity_is_modified = false;
  private boolean modified_limit_price_is_set = false;
  private boolean modified_limit_price_is_modified = false;
  private boolean modified_execution_type_is_set = false;
  private boolean modified_execution_type_is_modified = false;
  private boolean modified_stop_order_price_is_set = false;
  private boolean modified_stop_order_price_is_modified = false;
  private boolean modified_w_limit_price_is_set = false;
  private boolean modified_w_limit_price_is_modified = false;
  private boolean cancel_div_is_set = false;
  private boolean cancel_div_is_modified = false;
  private boolean org_corp_code_is_set = false;
  private boolean org_corp_code_is_modified = false;
  private boolean canceled_quantity_is_set = false;
  private boolean canceled_quantity_is_modified = false;
  private boolean exec_price_is_set = false;
  private boolean exec_price_is_modified = false;
  private boolean exec_quantity_is_set = false;
  private boolean exec_quantity_is_modified = false;
  private boolean exec_time_is_set = false;
  private boolean exec_time_is_modified = false;
  private boolean price_mark_is_set = false;
  private boolean price_mark_is_modified = false;
  private boolean exec_number_is_set = false;
  private boolean exec_number_is_modified = false;
  private boolean accept_number_is_set = false;
  private boolean accept_number_is_modified = false;
  private boolean virtual_server_number_market_is_set = false;
  private boolean virtual_server_number_market_is_modified = false;
  private boolean executed_quantity_is_set = false;
  private boolean executed_quantity_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean stop_mark_is_set = false;
  private boolean stop_mark_is_modified = false;
  private boolean expiration_quantity_is_set = false;
  private boolean expiration_quantity_is_modified = false;
  private boolean reason_code_is_set = false;
  private boolean reason_code_is_modified = false;
  private boolean expiration_time_is_set = false;
  private boolean expiration_time_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean rowid_is_set = false;
  private boolean rowid_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "rowid=" + rowid
      + "," + "request_code=" +request_code
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "sonar_market_code=" +sonar_market_code
      + "," + "order_request_number=" +order_request_number
      + "," + "product_code=" +product_code
      + "," + "target_product_code=" +target_product_code
      + "," + "delivery_month_yyyy=" +delivery_month_yyyy
      + "," + "delivery_month_mm=" +delivery_month_mm
      + "," + "future_option_product_type=" +future_option_product_type
      + "," + "strike_price=" +strike_price
      + "," + "corp_code=" +corp_code
      + "," + "front_order_exchange_code=" +front_order_exchange_code
      + "," + "front_order_system_code=" +front_order_system_code
      + "," + "front_order_trade_code=" +front_order_trade_code
      + "," + "received_date_time=" +received_date_time
      + "," + "received_time=" +received_time
      + "," + "data_class_code=" +data_class_code
      + "," + "data_class_detail_code=" +data_class_detail_code
      + "," + "buy_sell_div=" +buy_sell_div
      + "," + "execution_condition=" +execution_condition
      + "," + "front_order_time=" +front_order_time
      + "," + "sell_order_quantity=" +sell_order_quantity
      + "," + "buy_order_quantity=" +buy_order_quantity
      + "," + "limit_price=" +limit_price
      + "," + "stop_order_price=" +stop_order_price
      + "," + "w_limit_price=" +w_limit_price
      + "," + "transaction_type=" +transaction_type
      + "," + "contract_check=" +contract_check
      + "," + "order_chanel=" +order_chanel
      + "," + "commision_number=" +commision_number
      + "," + "commision_branch_number=" +commision_branch_number
      + "," + "commision_product_code=" +commision_product_code
      + "," + "change_quantity=" +change_quantity
      + "," + "modified_limit_price=" +modified_limit_price
      + "," + "modified_execution_type=" +modified_execution_type
      + "," + "modified_stop_order_price=" +modified_stop_order_price
      + "," + "modified_w_limit_price=" +modified_w_limit_price
      + "," + "cancel_div=" +cancel_div
      + "," + "org_corp_code=" +org_corp_code
      + "," + "canceled_quantity=" +canceled_quantity
      + "," + "exec_price=" +exec_price
      + "," + "exec_quantity=" +exec_quantity
      + "," + "exec_time=" +exec_time
      + "," + "price_mark=" +price_mark
      + "," + "exec_number=" +exec_number
      + "," + "accept_number=" +accept_number
      + "," + "virtual_server_number_market=" +virtual_server_number_market
      + "," + "executed_quantity=" +executed_quantity
      + "," + "price=" +price
      + "," + "stop_mark=" +stop_mark
      + "," + "expiration_quantity=" +expiration_quantity
      + "," + "reason_code=" +reason_code
      + "," + "expiration_time=" +expiration_time
      + "," + "status=" +status
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のHostFotypeOrderHistoryParamsオブジェクトを作成します。 
   */
  public HostFotypeOrderHistoryParams() {
    rowid_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostFotypeOrderHistoryRowオブジェクトの値を利用してHostFotypeOrderHistoryParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostFotypeOrderHistoryRowオブジェクト 
   */
  public HostFotypeOrderHistoryParams( HostFotypeOrderHistoryRow p_row )
  {
    rowid = p_row.getRowid();
    rowid_is_set = p_row.getRowidIsSet();
    rowid_is_modified = p_row.getRowidIsModified();
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
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
    sonar_market_code = p_row.getSonarMarketCode();
    sonar_market_code_is_set = p_row.getSonarMarketCodeIsSet();
    sonar_market_code_is_modified = p_row.getSonarMarketCodeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    target_product_code = p_row.getTargetProductCode();
    target_product_code_is_set = p_row.getTargetProductCodeIsSet();
    target_product_code_is_modified = p_row.getTargetProductCodeIsModified();
    delivery_month_yyyy = p_row.getDeliveryMonthYyyy();
    delivery_month_yyyy_is_set = p_row.getDeliveryMonthYyyyIsSet();
    delivery_month_yyyy_is_modified = p_row.getDeliveryMonthYyyyIsModified();
    delivery_month_mm = p_row.getDeliveryMonthMm();
    delivery_month_mm_is_set = p_row.getDeliveryMonthMmIsSet();
    delivery_month_mm_is_modified = p_row.getDeliveryMonthMmIsModified();
    future_option_product_type = p_row.getFutureOptionProductType();
    future_option_product_type_is_set = p_row.getFutureOptionProductTypeIsSet();
    future_option_product_type_is_modified = p_row.getFutureOptionProductTypeIsModified();
    if ( !p_row.getStrikePriceIsNull() )
      strike_price = new Double( p_row.getStrikePrice() );
    strike_price_is_set = p_row.getStrikePriceIsSet();
    strike_price_is_modified = p_row.getStrikePriceIsModified();
    corp_code = p_row.getCorpCode();
    corp_code_is_set = p_row.getCorpCodeIsSet();
    corp_code_is_modified = p_row.getCorpCodeIsModified();
    front_order_exchange_code = p_row.getFrontOrderExchangeCode();
    front_order_exchange_code_is_set = p_row.getFrontOrderExchangeCodeIsSet();
    front_order_exchange_code_is_modified = p_row.getFrontOrderExchangeCodeIsModified();
    front_order_system_code = p_row.getFrontOrderSystemCode();
    front_order_system_code_is_set = p_row.getFrontOrderSystemCodeIsSet();
    front_order_system_code_is_modified = p_row.getFrontOrderSystemCodeIsModified();
    front_order_trade_code = p_row.getFrontOrderTradeCode();
    front_order_trade_code_is_set = p_row.getFrontOrderTradeCodeIsSet();
    front_order_trade_code_is_modified = p_row.getFrontOrderTradeCodeIsModified();
    received_date_time = p_row.getReceivedDateTime();
    received_date_time_is_set = p_row.getReceivedDateTimeIsSet();
    received_date_time_is_modified = p_row.getReceivedDateTimeIsModified();
    received_time = p_row.getReceivedTime();
    received_time_is_set = p_row.getReceivedTimeIsSet();
    received_time_is_modified = p_row.getReceivedTimeIsModified();
    data_class_code = p_row.getDataClassCode();
    data_class_code_is_set = p_row.getDataClassCodeIsSet();
    data_class_code_is_modified = p_row.getDataClassCodeIsModified();
    data_class_detail_code = p_row.getDataClassDetailCode();
    data_class_detail_code_is_set = p_row.getDataClassDetailCodeIsSet();
    data_class_detail_code_is_modified = p_row.getDataClassDetailCodeIsModified();
    buy_sell_div = p_row.getBuySellDiv();
    buy_sell_div_is_set = p_row.getBuySellDivIsSet();
    buy_sell_div_is_modified = p_row.getBuySellDivIsModified();
    execution_condition = p_row.getExecutionCondition();
    execution_condition_is_set = p_row.getExecutionConditionIsSet();
    execution_condition_is_modified = p_row.getExecutionConditionIsModified();
    front_order_time = p_row.getFrontOrderTime();
    front_order_time_is_set = p_row.getFrontOrderTimeIsSet();
    front_order_time_is_modified = p_row.getFrontOrderTimeIsModified();
    if ( !p_row.getSellOrderQuantityIsNull() )
      sell_order_quantity = new Double( p_row.getSellOrderQuantity() );
    sell_order_quantity_is_set = p_row.getSellOrderQuantityIsSet();
    sell_order_quantity_is_modified = p_row.getSellOrderQuantityIsModified();
    if ( !p_row.getBuyOrderQuantityIsNull() )
      buy_order_quantity = new Double( p_row.getBuyOrderQuantity() );
    buy_order_quantity_is_set = p_row.getBuyOrderQuantityIsSet();
    buy_order_quantity_is_modified = p_row.getBuyOrderQuantityIsModified();
    if ( !p_row.getLimitPriceIsNull() )
      limit_price = new Double( p_row.getLimitPrice() );
    limit_price_is_set = p_row.getLimitPriceIsSet();
    limit_price_is_modified = p_row.getLimitPriceIsModified();
    if ( !p_row.getStopOrderPriceIsNull() )
      stop_order_price = new Double( p_row.getStopOrderPrice() );
    stop_order_price_is_set = p_row.getStopOrderPriceIsSet();
    stop_order_price_is_modified = p_row.getStopOrderPriceIsModified();
    if ( !p_row.getWLimitPriceIsNull() )
      w_limit_price = new Double( p_row.getWLimitPrice() );
    w_limit_price_is_set = p_row.getWLimitPriceIsSet();
    w_limit_price_is_modified = p_row.getWLimitPriceIsModified();
    transaction_type = p_row.getTransactionType();
    transaction_type_is_set = p_row.getTransactionTypeIsSet();
    transaction_type_is_modified = p_row.getTransactionTypeIsModified();
    contract_check = p_row.getContractCheck();
    contract_check_is_set = p_row.getContractCheckIsSet();
    contract_check_is_modified = p_row.getContractCheckIsModified();
    order_chanel = p_row.getOrderChanel();
    order_chanel_is_set = p_row.getOrderChanelIsSet();
    order_chanel_is_modified = p_row.getOrderChanelIsModified();
    commision_number = p_row.getCommisionNumber();
    commision_number_is_set = p_row.getCommisionNumberIsSet();
    commision_number_is_modified = p_row.getCommisionNumberIsModified();
    commision_branch_number = p_row.getCommisionBranchNumber();
    commision_branch_number_is_set = p_row.getCommisionBranchNumberIsSet();
    commision_branch_number_is_modified = p_row.getCommisionBranchNumberIsModified();
    commision_product_code = p_row.getCommisionProductCode();
    commision_product_code_is_set = p_row.getCommisionProductCodeIsSet();
    commision_product_code_is_modified = p_row.getCommisionProductCodeIsModified();
    if ( !p_row.getChangeQuantityIsNull() )
      change_quantity = new Double( p_row.getChangeQuantity() );
    change_quantity_is_set = p_row.getChangeQuantityIsSet();
    change_quantity_is_modified = p_row.getChangeQuantityIsModified();
    if ( !p_row.getModifiedLimitPriceIsNull() )
      modified_limit_price = new Double( p_row.getModifiedLimitPrice() );
    modified_limit_price_is_set = p_row.getModifiedLimitPriceIsSet();
    modified_limit_price_is_modified = p_row.getModifiedLimitPriceIsModified();
    modified_execution_type = p_row.getModifiedExecutionType();
    modified_execution_type_is_set = p_row.getModifiedExecutionTypeIsSet();
    modified_execution_type_is_modified = p_row.getModifiedExecutionTypeIsModified();
    if ( !p_row.getModifiedStopOrderPriceIsNull() )
      modified_stop_order_price = new Double( p_row.getModifiedStopOrderPrice() );
    modified_stop_order_price_is_set = p_row.getModifiedStopOrderPriceIsSet();
    modified_stop_order_price_is_modified = p_row.getModifiedStopOrderPriceIsModified();
    if ( !p_row.getModifiedWLimitPriceIsNull() )
      modified_w_limit_price = new Double( p_row.getModifiedWLimitPrice() );
    modified_w_limit_price_is_set = p_row.getModifiedWLimitPriceIsSet();
    modified_w_limit_price_is_modified = p_row.getModifiedWLimitPriceIsModified();
    cancel_div = p_row.getCancelDiv();
    cancel_div_is_set = p_row.getCancelDivIsSet();
    cancel_div_is_modified = p_row.getCancelDivIsModified();
    org_corp_code = p_row.getOrgCorpCode();
    org_corp_code_is_set = p_row.getOrgCorpCodeIsSet();
    org_corp_code_is_modified = p_row.getOrgCorpCodeIsModified();
    if ( !p_row.getCanceledQuantityIsNull() )
      canceled_quantity = new Double( p_row.getCanceledQuantity() );
    canceled_quantity_is_set = p_row.getCanceledQuantityIsSet();
    canceled_quantity_is_modified = p_row.getCanceledQuantityIsModified();
    if ( !p_row.getExecPriceIsNull() )
      exec_price = new Double( p_row.getExecPrice() );
    exec_price_is_set = p_row.getExecPriceIsSet();
    exec_price_is_modified = p_row.getExecPriceIsModified();
    if ( !p_row.getExecQuantityIsNull() )
      exec_quantity = new Double( p_row.getExecQuantity() );
    exec_quantity_is_set = p_row.getExecQuantityIsSet();
    exec_quantity_is_modified = p_row.getExecQuantityIsModified();
    exec_time = p_row.getExecTime();
    exec_time_is_set = p_row.getExecTimeIsSet();
    exec_time_is_modified = p_row.getExecTimeIsModified();
    price_mark = p_row.getPriceMark();
    price_mark_is_set = p_row.getPriceMarkIsSet();
    price_mark_is_modified = p_row.getPriceMarkIsModified();
    exec_number = p_row.getExecNumber();
    exec_number_is_set = p_row.getExecNumberIsSet();
    exec_number_is_modified = p_row.getExecNumberIsModified();
    accept_number = p_row.getAcceptNumber();
    accept_number_is_set = p_row.getAcceptNumberIsSet();
    accept_number_is_modified = p_row.getAcceptNumberIsModified();
    virtual_server_number_market = p_row.getVirtualServerNumberMarket();
    virtual_server_number_market_is_set = p_row.getVirtualServerNumberMarketIsSet();
    virtual_server_number_market_is_modified = p_row.getVirtualServerNumberMarketIsModified();
    if ( !p_row.getExecutedQuantityIsNull() )
      executed_quantity = new Double( p_row.getExecutedQuantity() );
    executed_quantity_is_set = p_row.getExecutedQuantityIsSet();
    executed_quantity_is_modified = p_row.getExecutedQuantityIsModified();
    if ( !p_row.getPriceIsNull() )
      price = new Double( p_row.getPrice() );
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    stop_mark = p_row.getStopMark();
    stop_mark_is_set = p_row.getStopMarkIsSet();
    stop_mark_is_modified = p_row.getStopMarkIsModified();
    if ( !p_row.getExpirationQuantityIsNull() )
      expiration_quantity = new Double( p_row.getExpirationQuantity() );
    expiration_quantity_is_set = p_row.getExpirationQuantityIsSet();
    expiration_quantity_is_modified = p_row.getExpirationQuantityIsModified();
    reason_code = p_row.getReasonCode();
    reason_code_is_set = p_row.getReasonCodeIsSet();
    reason_code_is_modified = p_row.getReasonCodeIsModified();
    expiration_time = p_row.getExpirationTime();
    expiration_time_is_set = p_row.getExpirationTimeIsSet();
    expiration_time_is_modified = p_row.getExpirationTimeIsModified();
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
      request_code_is_set = true;
      request_code_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      trader_code_is_set = true;
      trader_code_is_modified = true;
      sonar_market_code_is_set = true;
      sonar_market_code_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      target_product_code_is_set = true;
      target_product_code_is_modified = true;
      delivery_month_yyyy_is_set = true;
      delivery_month_yyyy_is_modified = true;
      delivery_month_mm_is_set = true;
      delivery_month_mm_is_modified = true;
      future_option_product_type_is_set = true;
      future_option_product_type_is_modified = true;
      strike_price_is_set = true;
      strike_price_is_modified = true;
      corp_code_is_set = true;
      corp_code_is_modified = true;
      front_order_exchange_code_is_set = true;
      front_order_exchange_code_is_modified = true;
      front_order_system_code_is_set = true;
      front_order_system_code_is_modified = true;
      front_order_trade_code_is_set = true;
      front_order_trade_code_is_modified = true;
      received_date_time_is_set = true;
      received_date_time_is_modified = true;
      received_time_is_set = true;
      received_time_is_modified = true;
      data_class_code_is_set = true;
      data_class_code_is_modified = true;
      data_class_detail_code_is_set = true;
      data_class_detail_code_is_modified = true;
      buy_sell_div_is_set = true;
      buy_sell_div_is_modified = true;
      execution_condition_is_set = true;
      execution_condition_is_modified = true;
      front_order_time_is_set = true;
      front_order_time_is_modified = true;
      sell_order_quantity_is_set = true;
      sell_order_quantity_is_modified = true;
      buy_order_quantity_is_set = true;
      buy_order_quantity_is_modified = true;
      limit_price_is_set = true;
      limit_price_is_modified = true;
      stop_order_price_is_set = true;
      stop_order_price_is_modified = true;
      w_limit_price_is_set = true;
      w_limit_price_is_modified = true;
      transaction_type_is_set = true;
      transaction_type_is_modified = true;
      contract_check_is_set = true;
      contract_check_is_modified = true;
      order_chanel_is_set = true;
      order_chanel_is_modified = true;
      commision_number_is_set = true;
      commision_number_is_modified = true;
      commision_branch_number_is_set = true;
      commision_branch_number_is_modified = true;
      commision_product_code_is_set = true;
      commision_product_code_is_modified = true;
      change_quantity_is_set = true;
      change_quantity_is_modified = true;
      modified_limit_price_is_set = true;
      modified_limit_price_is_modified = true;
      modified_execution_type_is_set = true;
      modified_execution_type_is_modified = true;
      modified_stop_order_price_is_set = true;
      modified_stop_order_price_is_modified = true;
      modified_w_limit_price_is_set = true;
      modified_w_limit_price_is_modified = true;
      cancel_div_is_set = true;
      cancel_div_is_modified = true;
      org_corp_code_is_set = true;
      org_corp_code_is_modified = true;
      canceled_quantity_is_set = true;
      canceled_quantity_is_modified = true;
      exec_price_is_set = true;
      exec_price_is_modified = true;
      exec_quantity_is_set = true;
      exec_quantity_is_modified = true;
      exec_time_is_set = true;
      exec_time_is_modified = true;
      price_mark_is_set = true;
      price_mark_is_modified = true;
      exec_number_is_set = true;
      exec_number_is_modified = true;
      accept_number_is_set = true;
      accept_number_is_modified = true;
      virtual_server_number_market_is_set = true;
      virtual_server_number_market_is_modified = true;
      executed_quantity_is_set = true;
      executed_quantity_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      stop_mark_is_set = true;
      stop_mark_is_modified = true;
      expiration_quantity_is_set = true;
      expiration_quantity_is_modified = true;
      reason_code_is_set = true;
      reason_code_is_modified = true;
      expiration_time_is_set = true;
      expiration_time_is_modified = true;
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
    if ( !( other instanceof HostFotypeOrderHistoryRow ) )
       return false;
    return fieldsEqual( (HostFotypeOrderHistoryRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostFotypeOrderHistoryRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostFotypeOrderHistoryRow row )
  {
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
        return false;
    }
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
    if ( sonar_market_code == null ) {
      if ( row.getSonarMarketCode() != null )
        return false;
    } else if ( !sonar_market_code.equals( row.getSonarMarketCode() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( target_product_code == null ) {
      if ( row.getTargetProductCode() != null )
        return false;
    } else if ( !target_product_code.equals( row.getTargetProductCode() ) ) {
        return false;
    }
    if ( delivery_month_yyyy == null ) {
      if ( row.getDeliveryMonthYyyy() != null )
        return false;
    } else if ( !delivery_month_yyyy.equals( row.getDeliveryMonthYyyy() ) ) {
        return false;
    }
    if ( delivery_month_mm == null ) {
      if ( row.getDeliveryMonthMm() != null )
        return false;
    } else if ( !delivery_month_mm.equals( row.getDeliveryMonthMm() ) ) {
        return false;
    }
    if ( future_option_product_type == null ) {
      if ( row.getFutureOptionProductType() != null )
        return false;
    } else if ( !future_option_product_type.equals( row.getFutureOptionProductType() ) ) {
        return false;
    }
    if ( strike_price == null ) {
      if ( !row.getStrikePriceIsNull() )
        return false;
    } else if ( row.getStrikePriceIsNull() || ( strike_price.doubleValue() != row.getStrikePrice() ) ) {
        return false;
    }
    if ( corp_code == null ) {
      if ( row.getCorpCode() != null )
        return false;
    } else if ( !corp_code.equals( row.getCorpCode() ) ) {
        return false;
    }
    if ( front_order_exchange_code == null ) {
      if ( row.getFrontOrderExchangeCode() != null )
        return false;
    } else if ( !front_order_exchange_code.equals( row.getFrontOrderExchangeCode() ) ) {
        return false;
    }
    if ( front_order_system_code == null ) {
      if ( row.getFrontOrderSystemCode() != null )
        return false;
    } else if ( !front_order_system_code.equals( row.getFrontOrderSystemCode() ) ) {
        return false;
    }
    if ( front_order_trade_code == null ) {
      if ( row.getFrontOrderTradeCode() != null )
        return false;
    } else if ( !front_order_trade_code.equals( row.getFrontOrderTradeCode() ) ) {
        return false;
    }
    if ( received_date_time == null ) {
      if ( row.getReceivedDateTime() != null )
        return false;
    } else if ( !received_date_time.equals( row.getReceivedDateTime() ) ) {
        return false;
    }
    if ( received_time == null ) {
      if ( row.getReceivedTime() != null )
        return false;
    } else if ( !received_time.equals( row.getReceivedTime() ) ) {
        return false;
    }
    if ( data_class_code == null ) {
      if ( row.getDataClassCode() != null )
        return false;
    } else if ( !data_class_code.equals( row.getDataClassCode() ) ) {
        return false;
    }
    if ( data_class_detail_code == null ) {
      if ( row.getDataClassDetailCode() != null )
        return false;
    } else if ( !data_class_detail_code.equals( row.getDataClassDetailCode() ) ) {
        return false;
    }
    if ( buy_sell_div == null ) {
      if ( row.getBuySellDiv() != null )
        return false;
    } else if ( !buy_sell_div.equals( row.getBuySellDiv() ) ) {
        return false;
    }
    if ( execution_condition == null ) {
      if ( row.getExecutionCondition() != null )
        return false;
    } else if ( !execution_condition.equals( row.getExecutionCondition() ) ) {
        return false;
    }
    if ( front_order_time == null ) {
      if ( row.getFrontOrderTime() != null )
        return false;
    } else if ( !front_order_time.equals( row.getFrontOrderTime() ) ) {
        return false;
    }
    if ( sell_order_quantity == null ) {
      if ( !row.getSellOrderQuantityIsNull() )
        return false;
    } else if ( row.getSellOrderQuantityIsNull() || ( sell_order_quantity.doubleValue() != row.getSellOrderQuantity() ) ) {
        return false;
    }
    if ( buy_order_quantity == null ) {
      if ( !row.getBuyOrderQuantityIsNull() )
        return false;
    } else if ( row.getBuyOrderQuantityIsNull() || ( buy_order_quantity.doubleValue() != row.getBuyOrderQuantity() ) ) {
        return false;
    }
    if ( limit_price == null ) {
      if ( !row.getLimitPriceIsNull() )
        return false;
    } else if ( row.getLimitPriceIsNull() || ( limit_price.doubleValue() != row.getLimitPrice() ) ) {
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
    if ( transaction_type == null ) {
      if ( row.getTransactionType() != null )
        return false;
    } else if ( !transaction_type.equals( row.getTransactionType() ) ) {
        return false;
    }
    if ( contract_check == null ) {
      if ( row.getContractCheck() != null )
        return false;
    } else if ( !contract_check.equals( row.getContractCheck() ) ) {
        return false;
    }
    if ( order_chanel == null ) {
      if ( row.getOrderChanel() != null )
        return false;
    } else if ( !order_chanel.equals( row.getOrderChanel() ) ) {
        return false;
    }
    if ( commision_number == null ) {
      if ( row.getCommisionNumber() != null )
        return false;
    } else if ( !commision_number.equals( row.getCommisionNumber() ) ) {
        return false;
    }
    if ( commision_branch_number == null ) {
      if ( row.getCommisionBranchNumber() != null )
        return false;
    } else if ( !commision_branch_number.equals( row.getCommisionBranchNumber() ) ) {
        return false;
    }
    if ( commision_product_code == null ) {
      if ( row.getCommisionProductCode() != null )
        return false;
    } else if ( !commision_product_code.equals( row.getCommisionProductCode() ) ) {
        return false;
    }
    if ( change_quantity == null ) {
      if ( !row.getChangeQuantityIsNull() )
        return false;
    } else if ( row.getChangeQuantityIsNull() || ( change_quantity.doubleValue() != row.getChangeQuantity() ) ) {
        return false;
    }
    if ( modified_limit_price == null ) {
      if ( !row.getModifiedLimitPriceIsNull() )
        return false;
    } else if ( row.getModifiedLimitPriceIsNull() || ( modified_limit_price.doubleValue() != row.getModifiedLimitPrice() ) ) {
        return false;
    }
    if ( modified_execution_type == null ) {
      if ( row.getModifiedExecutionType() != null )
        return false;
    } else if ( !modified_execution_type.equals( row.getModifiedExecutionType() ) ) {
        return false;
    }
    if ( modified_stop_order_price == null ) {
      if ( !row.getModifiedStopOrderPriceIsNull() )
        return false;
    } else if ( row.getModifiedStopOrderPriceIsNull() || ( modified_stop_order_price.doubleValue() != row.getModifiedStopOrderPrice() ) ) {
        return false;
    }
    if ( modified_w_limit_price == null ) {
      if ( !row.getModifiedWLimitPriceIsNull() )
        return false;
    } else if ( row.getModifiedWLimitPriceIsNull() || ( modified_w_limit_price.doubleValue() != row.getModifiedWLimitPrice() ) ) {
        return false;
    }
    if ( cancel_div == null ) {
      if ( row.getCancelDiv() != null )
        return false;
    } else if ( !cancel_div.equals( row.getCancelDiv() ) ) {
        return false;
    }
    if ( org_corp_code == null ) {
      if ( row.getOrgCorpCode() != null )
        return false;
    } else if ( !org_corp_code.equals( row.getOrgCorpCode() ) ) {
        return false;
    }
    if ( canceled_quantity == null ) {
      if ( !row.getCanceledQuantityIsNull() )
        return false;
    } else if ( row.getCanceledQuantityIsNull() || ( canceled_quantity.doubleValue() != row.getCanceledQuantity() ) ) {
        return false;
    }
    if ( exec_price == null ) {
      if ( !row.getExecPriceIsNull() )
        return false;
    } else if ( row.getExecPriceIsNull() || ( exec_price.doubleValue() != row.getExecPrice() ) ) {
        return false;
    }
    if ( exec_quantity == null ) {
      if ( !row.getExecQuantityIsNull() )
        return false;
    } else if ( row.getExecQuantityIsNull() || ( exec_quantity.doubleValue() != row.getExecQuantity() ) ) {
        return false;
    }
    if ( exec_time == null ) {
      if ( row.getExecTime() != null )
        return false;
    } else if ( !exec_time.equals( row.getExecTime() ) ) {
        return false;
    }
    if ( price_mark == null ) {
      if ( row.getPriceMark() != null )
        return false;
    } else if ( !price_mark.equals( row.getPriceMark() ) ) {
        return false;
    }
    if ( exec_number != row.getExecNumber() )
      return false;
    if ( accept_number == null ) {
      if ( row.getAcceptNumber() != null )
        return false;
    } else if ( !accept_number.equals( row.getAcceptNumber() ) ) {
        return false;
    }
    if ( virtual_server_number_market == null ) {
      if ( row.getVirtualServerNumberMarket() != null )
        return false;
    } else if ( !virtual_server_number_market.equals( row.getVirtualServerNumberMarket() ) ) {
        return false;
    }
    if ( executed_quantity == null ) {
      if ( !row.getExecutedQuantityIsNull() )
        return false;
    } else if ( row.getExecutedQuantityIsNull() || ( executed_quantity.doubleValue() != row.getExecutedQuantity() ) ) {
        return false;
    }
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.doubleValue() != row.getPrice() ) ) {
        return false;
    }
    if ( stop_mark == null ) {
      if ( row.getStopMark() != null )
        return false;
    } else if ( !stop_mark.equals( row.getStopMark() ) ) {
        return false;
    }
    if ( expiration_quantity == null ) {
      if ( !row.getExpirationQuantityIsNull() )
        return false;
    } else if ( row.getExpirationQuantityIsNull() || ( expiration_quantity.doubleValue() != row.getExpirationQuantity() ) ) {
        return false;
    }
    if ( reason_code == null ) {
      if ( row.getReasonCode() != null )
        return false;
    } else if ( !reason_code.equals( row.getReasonCode() ) ) {
        return false;
    }
    if ( expiration_time == null ) {
      if ( row.getExpirationTime() != null )
        return false;
    } else if ( !expiration_time.equals( row.getExpirationTime() ) ) {
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
    if ( rowid == null ) {
      if ( row.getRowid() != null )
        return false;
    } else if ( !rowid.equals( row.getRowid() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (sonar_market_code!=null? sonar_market_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (target_product_code!=null? target_product_code.hashCode(): 0) 
        + (delivery_month_yyyy!=null? delivery_month_yyyy.hashCode(): 0) 
        + (delivery_month_mm!=null? delivery_month_mm.hashCode(): 0) 
        + (future_option_product_type!=null? future_option_product_type.hashCode(): 0) 
        + (strike_price!=null? strike_price.hashCode(): 0) 
        + (corp_code!=null? corp_code.hashCode(): 0) 
        + (front_order_exchange_code!=null? front_order_exchange_code.hashCode(): 0) 
        + (front_order_system_code!=null? front_order_system_code.hashCode(): 0) 
        + (front_order_trade_code!=null? front_order_trade_code.hashCode(): 0) 
        + (received_date_time!=null? received_date_time.hashCode(): 0) 
        + (received_time!=null? received_time.hashCode(): 0) 
        + (data_class_code!=null? data_class_code.hashCode(): 0) 
        + (data_class_detail_code!=null? data_class_detail_code.hashCode(): 0) 
        + (buy_sell_div!=null? buy_sell_div.hashCode(): 0) 
        + (execution_condition!=null? execution_condition.hashCode(): 0) 
        + (front_order_time!=null? front_order_time.hashCode(): 0) 
        + (sell_order_quantity!=null? sell_order_quantity.hashCode(): 0) 
        + (buy_order_quantity!=null? buy_order_quantity.hashCode(): 0) 
        + (limit_price!=null? limit_price.hashCode(): 0) 
        + (stop_order_price!=null? stop_order_price.hashCode(): 0) 
        + (w_limit_price!=null? w_limit_price.hashCode(): 0) 
        + (transaction_type!=null? transaction_type.hashCode(): 0) 
        + (contract_check!=null? contract_check.hashCode(): 0) 
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (commision_number!=null? commision_number.hashCode(): 0) 
        + (commision_branch_number!=null? commision_branch_number.hashCode(): 0) 
        + (commision_product_code!=null? commision_product_code.hashCode(): 0) 
        + (change_quantity!=null? change_quantity.hashCode(): 0) 
        + (modified_limit_price!=null? modified_limit_price.hashCode(): 0) 
        + (modified_execution_type!=null? modified_execution_type.hashCode(): 0) 
        + (modified_stop_order_price!=null? modified_stop_order_price.hashCode(): 0) 
        + (modified_w_limit_price!=null? modified_w_limit_price.hashCode(): 0) 
        + (cancel_div!=null? cancel_div.hashCode(): 0) 
        + (org_corp_code!=null? org_corp_code.hashCode(): 0) 
        + (canceled_quantity!=null? canceled_quantity.hashCode(): 0) 
        + (exec_price!=null? exec_price.hashCode(): 0) 
        + (exec_quantity!=null? exec_quantity.hashCode(): 0) 
        + (exec_time!=null? exec_time.hashCode(): 0) 
        + (price_mark!=null? price_mark.hashCode(): 0) 
        + ((int) exec_number)
        + (accept_number!=null? accept_number.hashCode(): 0) 
        + (virtual_server_number_market!=null? virtual_server_number_market.hashCode(): 0) 
        + (executed_quantity!=null? executed_quantity.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (stop_mark!=null? stop_mark.hashCode(): 0) 
        + (expiration_quantity!=null? expiration_quantity.hashCode(): 0) 
        + (reason_code!=null? reason_code.hashCode(): 0) 
        + (expiration_time!=null? expiration_time.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (rowid!=null? rowid.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !exec_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'exec_number' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		if ( request_code != null )
			map.put("request_code",request_code);
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		if ( sonar_market_code != null )
			map.put("sonar_market_code",sonar_market_code);
		if ( order_request_number != null )
			map.put("order_request_number",order_request_number);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( target_product_code != null )
			map.put("target_product_code",target_product_code);
		if ( delivery_month_yyyy != null )
			map.put("delivery_month_yyyy",delivery_month_yyyy);
		if ( delivery_month_mm != null )
			map.put("delivery_month_mm",delivery_month_mm);
		if ( future_option_product_type != null )
			map.put("future_option_product_type",future_option_product_type);
		if ( strike_price != null )
			map.put("strike_price",strike_price);
		if ( corp_code != null )
			map.put("corp_code",corp_code);
		if ( front_order_exchange_code != null )
			map.put("front_order_exchange_code",front_order_exchange_code);
		if ( front_order_system_code != null )
			map.put("front_order_system_code",front_order_system_code);
		if ( front_order_trade_code != null )
			map.put("front_order_trade_code",front_order_trade_code);
		if ( received_date_time != null )
			map.put("received_date_time",received_date_time);
		if ( received_time != null )
			map.put("received_time",received_time);
		if ( data_class_code != null )
			map.put("data_class_code",data_class_code);
		if ( data_class_detail_code != null )
			map.put("data_class_detail_code",data_class_detail_code);
		if ( buy_sell_div != null )
			map.put("buy_sell_div",buy_sell_div);
		if ( execution_condition != null )
			map.put("execution_condition",execution_condition);
		if ( front_order_time != null )
			map.put("front_order_time",front_order_time);
		if ( sell_order_quantity != null )
			map.put("sell_order_quantity",sell_order_quantity);
		if ( buy_order_quantity != null )
			map.put("buy_order_quantity",buy_order_quantity);
		if ( limit_price != null )
			map.put("limit_price",limit_price);
		if ( stop_order_price != null )
			map.put("stop_order_price",stop_order_price);
		if ( w_limit_price != null )
			map.put("w_limit_price",w_limit_price);
		if ( transaction_type != null )
			map.put("transaction_type",transaction_type);
		if ( contract_check != null )
			map.put("contract_check",contract_check);
		if ( order_chanel != null )
			map.put("order_chanel",order_chanel);
		if ( commision_number != null )
			map.put("commision_number",commision_number);
		if ( commision_branch_number != null )
			map.put("commision_branch_number",commision_branch_number);
		if ( commision_product_code != null )
			map.put("commision_product_code",commision_product_code);
		if ( change_quantity != null )
			map.put("change_quantity",change_quantity);
		if ( modified_limit_price != null )
			map.put("modified_limit_price",modified_limit_price);
		if ( modified_execution_type != null )
			map.put("modified_execution_type",modified_execution_type);
		if ( modified_stop_order_price != null )
			map.put("modified_stop_order_price",modified_stop_order_price);
		if ( modified_w_limit_price != null )
			map.put("modified_w_limit_price",modified_w_limit_price);
		if ( cancel_div != null )
			map.put("cancel_div",cancel_div);
		if ( org_corp_code != null )
			map.put("org_corp_code",org_corp_code);
		if ( canceled_quantity != null )
			map.put("canceled_quantity",canceled_quantity);
		if ( exec_price != null )
			map.put("exec_price",exec_price);
		if ( exec_quantity != null )
			map.put("exec_quantity",exec_quantity);
		if ( exec_time != null )
			map.put("exec_time",exec_time);
		if ( price_mark != null )
			map.put("price_mark",price_mark);
		map.put("exec_number",new Integer(exec_number));
		if ( accept_number != null )
			map.put("accept_number",accept_number);
		if ( virtual_server_number_market != null )
			map.put("virtual_server_number_market",virtual_server_number_market);
		if ( executed_quantity != null )
			map.put("executed_quantity",executed_quantity);
		if ( price != null )
			map.put("price",price);
		if ( stop_mark != null )
			map.put("stop_mark",stop_mark);
		if ( expiration_quantity != null )
			map.put("expiration_quantity",expiration_quantity);
		if ( reason_code != null )
			map.put("reason_code",reason_code);
		if ( expiration_time != null )
			map.put("expiration_time",expiration_time);
		if ( status != null )
			map.put("status",status);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("rowid",rowid);
		map.remove("rowid");
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( request_code_is_modified )
			map.put("request_code",request_code);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( sonar_market_code_is_modified )
			map.put("sonar_market_code",sonar_market_code);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( target_product_code_is_modified )
			map.put("target_product_code",target_product_code);
		if ( delivery_month_yyyy_is_modified )
			map.put("delivery_month_yyyy",delivery_month_yyyy);
		if ( delivery_month_mm_is_modified )
			map.put("delivery_month_mm",delivery_month_mm);
		if ( future_option_product_type_is_modified )
			map.put("future_option_product_type",future_option_product_type);
		if ( strike_price_is_modified )
			map.put("strike_price",strike_price);
		if ( corp_code_is_modified )
			map.put("corp_code",corp_code);
		if ( front_order_exchange_code_is_modified )
			map.put("front_order_exchange_code",front_order_exchange_code);
		if ( front_order_system_code_is_modified )
			map.put("front_order_system_code",front_order_system_code);
		if ( front_order_trade_code_is_modified )
			map.put("front_order_trade_code",front_order_trade_code);
		if ( received_date_time_is_modified )
			map.put("received_date_time",received_date_time);
		if ( received_time_is_modified )
			map.put("received_time",received_time);
		if ( data_class_code_is_modified )
			map.put("data_class_code",data_class_code);
		if ( data_class_detail_code_is_modified )
			map.put("data_class_detail_code",data_class_detail_code);
		if ( buy_sell_div_is_modified )
			map.put("buy_sell_div",buy_sell_div);
		if ( execution_condition_is_modified )
			map.put("execution_condition",execution_condition);
		if ( front_order_time_is_modified )
			map.put("front_order_time",front_order_time);
		if ( sell_order_quantity_is_modified )
			map.put("sell_order_quantity",sell_order_quantity);
		if ( buy_order_quantity_is_modified )
			map.put("buy_order_quantity",buy_order_quantity);
		if ( limit_price_is_modified )
			map.put("limit_price",limit_price);
		if ( stop_order_price_is_modified )
			map.put("stop_order_price",stop_order_price);
		if ( w_limit_price_is_modified )
			map.put("w_limit_price",w_limit_price);
		if ( transaction_type_is_modified )
			map.put("transaction_type",transaction_type);
		if ( contract_check_is_modified )
			map.put("contract_check",contract_check);
		if ( order_chanel_is_modified )
			map.put("order_chanel",order_chanel);
		if ( commision_number_is_modified )
			map.put("commision_number",commision_number);
		if ( commision_branch_number_is_modified )
			map.put("commision_branch_number",commision_branch_number);
		if ( commision_product_code_is_modified )
			map.put("commision_product_code",commision_product_code);
		if ( change_quantity_is_modified )
			map.put("change_quantity",change_quantity);
		if ( modified_limit_price_is_modified )
			map.put("modified_limit_price",modified_limit_price);
		if ( modified_execution_type_is_modified )
			map.put("modified_execution_type",modified_execution_type);
		if ( modified_stop_order_price_is_modified )
			map.put("modified_stop_order_price",modified_stop_order_price);
		if ( modified_w_limit_price_is_modified )
			map.put("modified_w_limit_price",modified_w_limit_price);
		if ( cancel_div_is_modified )
			map.put("cancel_div",cancel_div);
		if ( org_corp_code_is_modified )
			map.put("org_corp_code",org_corp_code);
		if ( canceled_quantity_is_modified )
			map.put("canceled_quantity",canceled_quantity);
		if ( exec_price_is_modified )
			map.put("exec_price",exec_price);
		if ( exec_quantity_is_modified )
			map.put("exec_quantity",exec_quantity);
		if ( exec_time_is_modified )
			map.put("exec_time",exec_time);
		if ( price_mark_is_modified )
			map.put("price_mark",price_mark);
		if ( exec_number_is_modified )
			map.put("exec_number",new Integer(exec_number));
		if ( accept_number_is_modified )
			map.put("accept_number",accept_number);
		if ( virtual_server_number_market_is_modified )
			map.put("virtual_server_number_market",virtual_server_number_market);
		if ( executed_quantity_is_modified )
			map.put("executed_quantity",executed_quantity);
		if ( price_is_modified )
			map.put("price",price);
		if ( stop_mark_is_modified )
			map.put("stop_mark",stop_mark);
		if ( expiration_quantity_is_modified )
			map.put("expiration_quantity",expiration_quantity);
		if ( reason_code_is_modified )
			map.put("reason_code",reason_code);
		if ( expiration_time_is_modified )
			map.put("expiration_time",expiration_time);
		if ( status_is_modified )
			map.put("status",status);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("request_code",request_code);
			map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			map.put("sonar_market_code",sonar_market_code);
			map.put("order_request_number",order_request_number);
			map.put("product_code",product_code);
			map.put("target_product_code",target_product_code);
			map.put("delivery_month_yyyy",delivery_month_yyyy);
			map.put("delivery_month_mm",delivery_month_mm);
			map.put("future_option_product_type",future_option_product_type);
			map.put("strike_price",strike_price);
			map.put("corp_code",corp_code);
			map.put("front_order_exchange_code",front_order_exchange_code);
			map.put("front_order_system_code",front_order_system_code);
			map.put("front_order_trade_code",front_order_trade_code);
			map.put("received_date_time",received_date_time);
			map.put("received_time",received_time);
			map.put("data_class_code",data_class_code);
			map.put("data_class_detail_code",data_class_detail_code);
			map.put("buy_sell_div",buy_sell_div);
			map.put("execution_condition",execution_condition);
			map.put("front_order_time",front_order_time);
			map.put("sell_order_quantity",sell_order_quantity);
			map.put("buy_order_quantity",buy_order_quantity);
			map.put("limit_price",limit_price);
			map.put("stop_order_price",stop_order_price);
			map.put("w_limit_price",w_limit_price);
			map.put("transaction_type",transaction_type);
			map.put("contract_check",contract_check);
			map.put("order_chanel",order_chanel);
			map.put("commision_number",commision_number);
			map.put("commision_branch_number",commision_branch_number);
			map.put("commision_product_code",commision_product_code);
			map.put("change_quantity",change_quantity);
			map.put("modified_limit_price",modified_limit_price);
			map.put("modified_execution_type",modified_execution_type);
			map.put("modified_stop_order_price",modified_stop_order_price);
			map.put("modified_w_limit_price",modified_w_limit_price);
			map.put("cancel_div",cancel_div);
			map.put("org_corp_code",org_corp_code);
			map.put("canceled_quantity",canceled_quantity);
			map.put("exec_price",exec_price);
			map.put("exec_quantity",exec_quantity);
			map.put("exec_time",exec_time);
			map.put("price_mark",price_mark);
			if ( exec_number_is_set )
				map.put("exec_number",new Integer(exec_number));
			map.put("accept_number",accept_number);
			map.put("virtual_server_number_market",virtual_server_number_market);
			map.put("executed_quantity",executed_quantity);
			map.put("price",price);
			map.put("stop_mark",stop_mark);
			map.put("expiration_quantity",expiration_quantity);
			map.put("reason_code",reason_code);
			map.put("expiration_time",expiration_time);
			map.put("status",status);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>request_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRequestCode()
  {
    return request_code;
  }


  /** 
   * <em>request_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsSet() {
    return request_code_is_set;
  }


  /** 
   * <em>request_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsModified() {
    return request_code_is_modified;
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
   * <em>target_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTargetProductCode()
  {
    return target_product_code;
  }


  /** 
   * <em>target_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetProductCodeIsSet() {
    return target_product_code_is_set;
  }


  /** 
   * <em>target_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetProductCodeIsModified() {
    return target_product_code_is_modified;
  }


  /** 
   * <em>delivery_month_yyyy</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeliveryMonthYyyy()
  {
    return delivery_month_yyyy;
  }


  /** 
   * <em>delivery_month_yyyy</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryMonthYyyyIsSet() {
    return delivery_month_yyyy_is_set;
  }


  /** 
   * <em>delivery_month_yyyy</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryMonthYyyyIsModified() {
    return delivery_month_yyyy_is_modified;
  }


  /** 
   * <em>delivery_month_mm</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeliveryMonthMm()
  {
    return delivery_month_mm;
  }


  /** 
   * <em>delivery_month_mm</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryMonthMmIsSet() {
    return delivery_month_mm_is_set;
  }


  /** 
   * <em>delivery_month_mm</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryMonthMmIsModified() {
    return delivery_month_mm_is_modified;
  }


  /** 
   * <em>future_option_product_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFutureOptionProductType()
  {
    return future_option_product_type;
  }


  /** 
   * <em>future_option_product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureOptionProductTypeIsSet() {
    return future_option_product_type_is_set;
  }


  /** 
   * <em>future_option_product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureOptionProductTypeIsModified() {
    return future_option_product_type_is_modified;
  }


  /** 
   * <em>strike_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getStrikePrice()
  {
    return ( strike_price==null? ((double)0): strike_price.doubleValue() );
  }


  /** 
   * <em>strike_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getStrikePriceIsNull()
  {
    return strike_price == null;
  }


  /** 
   * <em>strike_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStrikePriceIsSet() {
    return strike_price_is_set;
  }


  /** 
   * <em>strike_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStrikePriceIsModified() {
    return strike_price_is_modified;
  }


  /** 
   * <em>corp_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCorpCode()
  {
    return corp_code;
  }


  /** 
   * <em>corp_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorpCodeIsSet() {
    return corp_code_is_set;
  }


  /** 
   * <em>corp_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorpCodeIsModified() {
    return corp_code_is_modified;
  }


  /** 
   * <em>front_order_exchange_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFrontOrderExchangeCode()
  {
    return front_order_exchange_code;
  }


  /** 
   * <em>front_order_exchange_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderExchangeCodeIsSet() {
    return front_order_exchange_code_is_set;
  }


  /** 
   * <em>front_order_exchange_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderExchangeCodeIsModified() {
    return front_order_exchange_code_is_modified;
  }


  /** 
   * <em>front_order_system_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFrontOrderSystemCode()
  {
    return front_order_system_code;
  }


  /** 
   * <em>front_order_system_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderSystemCodeIsSet() {
    return front_order_system_code_is_set;
  }


  /** 
   * <em>front_order_system_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderSystemCodeIsModified() {
    return front_order_system_code_is_modified;
  }


  /** 
   * <em>front_order_trade_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFrontOrderTradeCode()
  {
    return front_order_trade_code;
  }


  /** 
   * <em>front_order_trade_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderTradeCodeIsSet() {
    return front_order_trade_code_is_set;
  }


  /** 
   * <em>front_order_trade_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderTradeCodeIsModified() {
    return front_order_trade_code_is_modified;
  }


  /** 
   * <em>received_date_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReceivedDateTime()
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
   * <em>received_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReceivedTime()
  {
    return received_time;
  }


  /** 
   * <em>received_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceivedTimeIsSet() {
    return received_time_is_set;
  }


  /** 
   * <em>received_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceivedTimeIsModified() {
    return received_time_is_modified;
  }


  /** 
   * <em>data_class_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDataClassCode()
  {
    return data_class_code;
  }


  /** 
   * <em>data_class_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataClassCodeIsSet() {
    return data_class_code_is_set;
  }


  /** 
   * <em>data_class_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataClassCodeIsModified() {
    return data_class_code_is_modified;
  }


  /** 
   * <em>data_class_detail_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDataClassDetailCode()
  {
    return data_class_detail_code;
  }


  /** 
   * <em>data_class_detail_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataClassDetailCodeIsSet() {
    return data_class_detail_code_is_set;
  }


  /** 
   * <em>data_class_detail_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataClassDetailCodeIsModified() {
    return data_class_detail_code_is_modified;
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
   * <em>execution_condition</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExecutionCondition()
  {
    return execution_condition;
  }


  /** 
   * <em>execution_condition</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutionConditionIsSet() {
    return execution_condition_is_set;
  }


  /** 
   * <em>execution_condition</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutionConditionIsModified() {
    return execution_condition_is_modified;
  }


  /** 
   * <em>front_order_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFrontOrderTime()
  {
    return front_order_time;
  }


  /** 
   * <em>front_order_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderTimeIsSet() {
    return front_order_time_is_set;
  }


  /** 
   * <em>front_order_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderTimeIsModified() {
    return front_order_time_is_modified;
  }


  /** 
   * <em>sell_order_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSellOrderQuantity()
  {
    return ( sell_order_quantity==null? ((double)0): sell_order_quantity.doubleValue() );
  }


  /** 
   * <em>sell_order_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellOrderQuantityIsNull()
  {
    return sell_order_quantity == null;
  }


  /** 
   * <em>sell_order_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellOrderQuantityIsSet() {
    return sell_order_quantity_is_set;
  }


  /** 
   * <em>sell_order_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellOrderQuantityIsModified() {
    return sell_order_quantity_is_modified;
  }


  /** 
   * <em>buy_order_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBuyOrderQuantity()
  {
    return ( buy_order_quantity==null? ((double)0): buy_order_quantity.doubleValue() );
  }


  /** 
   * <em>buy_order_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyOrderQuantityIsNull()
  {
    return buy_order_quantity == null;
  }


  /** 
   * <em>buy_order_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyOrderQuantityIsSet() {
    return buy_order_quantity_is_set;
  }


  /** 
   * <em>buy_order_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyOrderQuantityIsModified() {
    return buy_order_quantity_is_modified;
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
   * <em>transaction_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransactionType()
  {
    return transaction_type;
  }


  /** 
   * <em>transaction_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactionTypeIsSet() {
    return transaction_type_is_set;
  }


  /** 
   * <em>transaction_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactionTypeIsModified() {
    return transaction_type_is_modified;
  }


  /** 
   * <em>contract_check</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getContractCheck()
  {
    return contract_check;
  }


  /** 
   * <em>contract_check</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractCheckIsSet() {
    return contract_check_is_set;
  }


  /** 
   * <em>contract_check</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractCheckIsModified() {
    return contract_check_is_modified;
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
   * <em>commision_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommisionNumber()
  {
    return commision_number;
  }


  /** 
   * <em>commision_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommisionNumberIsSet() {
    return commision_number_is_set;
  }


  /** 
   * <em>commision_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommisionNumberIsModified() {
    return commision_number_is_modified;
  }


  /** 
   * <em>commision_branch_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommisionBranchNumber()
  {
    return commision_branch_number;
  }


  /** 
   * <em>commision_branch_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommisionBranchNumberIsSet() {
    return commision_branch_number_is_set;
  }


  /** 
   * <em>commision_branch_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommisionBranchNumberIsModified() {
    return commision_branch_number_is_modified;
  }


  /** 
   * <em>commision_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommisionProductCode()
  {
    return commision_product_code;
  }


  /** 
   * <em>commision_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommisionProductCodeIsSet() {
    return commision_product_code_is_set;
  }


  /** 
   * <em>commision_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommisionProductCodeIsModified() {
    return commision_product_code_is_modified;
  }


  /** 
   * <em>change_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getChangeQuantity()
  {
    return ( change_quantity==null? ((double)0): change_quantity.doubleValue() );
  }


  /** 
   * <em>change_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getChangeQuantityIsNull()
  {
    return change_quantity == null;
  }


  /** 
   * <em>change_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeQuantityIsSet() {
    return change_quantity_is_set;
  }


  /** 
   * <em>change_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeQuantityIsModified() {
    return change_quantity_is_modified;
  }


  /** 
   * <em>modified_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getModifiedLimitPrice()
  {
    return ( modified_limit_price==null? ((double)0): modified_limit_price.doubleValue() );
  }


  /** 
   * <em>modified_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getModifiedLimitPriceIsNull()
  {
    return modified_limit_price == null;
  }


  /** 
   * <em>modified_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedLimitPriceIsSet() {
    return modified_limit_price_is_set;
  }


  /** 
   * <em>modified_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedLimitPriceIsModified() {
    return modified_limit_price_is_modified;
  }


  /** 
   * <em>modified_execution_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getModifiedExecutionType()
  {
    return modified_execution_type;
  }


  /** 
   * <em>modified_execution_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedExecutionTypeIsSet() {
    return modified_execution_type_is_set;
  }


  /** 
   * <em>modified_execution_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedExecutionTypeIsModified() {
    return modified_execution_type_is_modified;
  }


  /** 
   * <em>modified_stop_order_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getModifiedStopOrderPrice()
  {
    return ( modified_stop_order_price==null? ((double)0): modified_stop_order_price.doubleValue() );
  }


  /** 
   * <em>modified_stop_order_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getModifiedStopOrderPriceIsNull()
  {
    return modified_stop_order_price == null;
  }


  /** 
   * <em>modified_stop_order_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedStopOrderPriceIsSet() {
    return modified_stop_order_price_is_set;
  }


  /** 
   * <em>modified_stop_order_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedStopOrderPriceIsModified() {
    return modified_stop_order_price_is_modified;
  }


  /** 
   * <em>modified_w_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getModifiedWLimitPrice()
  {
    return ( modified_w_limit_price==null? ((double)0): modified_w_limit_price.doubleValue() );
  }


  /** 
   * <em>modified_w_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getModifiedWLimitPriceIsNull()
  {
    return modified_w_limit_price == null;
  }


  /** 
   * <em>modified_w_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedWLimitPriceIsSet() {
    return modified_w_limit_price_is_set;
  }


  /** 
   * <em>modified_w_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedWLimitPriceIsModified() {
    return modified_w_limit_price_is_modified;
  }


  /** 
   * <em>cancel_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCancelDiv()
  {
    return cancel_div;
  }


  /** 
   * <em>cancel_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelDivIsSet() {
    return cancel_div_is_set;
  }


  /** 
   * <em>cancel_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelDivIsModified() {
    return cancel_div_is_modified;
  }


  /** 
   * <em>org_corp_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrgCorpCode()
  {
    return org_corp_code;
  }


  /** 
   * <em>org_corp_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgCorpCodeIsSet() {
    return org_corp_code_is_set;
  }


  /** 
   * <em>org_corp_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgCorpCodeIsModified() {
    return org_corp_code_is_modified;
  }


  /** 
   * <em>canceled_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCanceledQuantity()
  {
    return ( canceled_quantity==null? ((double)0): canceled_quantity.doubleValue() );
  }


  /** 
   * <em>canceled_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCanceledQuantityIsNull()
  {
    return canceled_quantity == null;
  }


  /** 
   * <em>canceled_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCanceledQuantityIsSet() {
    return canceled_quantity_is_set;
  }


  /** 
   * <em>canceled_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCanceledQuantityIsModified() {
    return canceled_quantity_is_modified;
  }


  /** 
   * <em>exec_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExecPrice()
  {
    return ( exec_price==null? ((double)0): exec_price.doubleValue() );
  }


  /** 
   * <em>exec_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExecPriceIsNull()
  {
    return exec_price == null;
  }


  /** 
   * <em>exec_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecPriceIsSet() {
    return exec_price_is_set;
  }


  /** 
   * <em>exec_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecPriceIsModified() {
    return exec_price_is_modified;
  }


  /** 
   * <em>exec_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExecQuantity()
  {
    return ( exec_quantity==null? ((double)0): exec_quantity.doubleValue() );
  }


  /** 
   * <em>exec_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExecQuantityIsNull()
  {
    return exec_quantity == null;
  }


  /** 
   * <em>exec_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecQuantityIsSet() {
    return exec_quantity_is_set;
  }


  /** 
   * <em>exec_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecQuantityIsModified() {
    return exec_quantity_is_modified;
  }


  /** 
   * <em>exec_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExecTime()
  {
    return exec_time;
  }


  /** 
   * <em>exec_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecTimeIsSet() {
    return exec_time_is_set;
  }


  /** 
   * <em>exec_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecTimeIsModified() {
    return exec_time_is_modified;
  }


  /** 
   * <em>price_mark</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPriceMark()
  {
    return price_mark;
  }


  /** 
   * <em>price_mark</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceMarkIsSet() {
    return price_mark_is_set;
  }


  /** 
   * <em>price_mark</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceMarkIsModified() {
    return price_mark_is_modified;
  }


  /** 
   * <em>exec_number</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getExecNumber()
  {
    return exec_number;
  }


  /** 
   * <em>exec_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecNumberIsSet() {
    return exec_number_is_set;
  }


  /** 
   * <em>exec_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecNumberIsModified() {
    return exec_number_is_modified;
  }


  /** 
   * <em>accept_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAcceptNumber()
  {
    return accept_number;
  }


  /** 
   * <em>accept_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptNumberIsSet() {
    return accept_number_is_set;
  }


  /** 
   * <em>accept_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptNumberIsModified() {
    return accept_number_is_modified;
  }


  /** 
   * <em>virtual_server_number_market</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getVirtualServerNumberMarket()
  {
    return virtual_server_number_market;
  }


  /** 
   * <em>virtual_server_number_market</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVirtualServerNumberMarketIsSet() {
    return virtual_server_number_market_is_set;
  }


  /** 
   * <em>virtual_server_number_market</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVirtualServerNumberMarketIsModified() {
    return virtual_server_number_market_is_modified;
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
   * <em>stop_mark</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopMark()
  {
    return stop_mark;
  }


  /** 
   * <em>stop_mark</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopMarkIsSet() {
    return stop_mark_is_set;
  }


  /** 
   * <em>stop_mark</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopMarkIsModified() {
    return stop_mark_is_modified;
  }


  /** 
   * <em>expiration_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExpirationQuantity()
  {
    return ( expiration_quantity==null? ((double)0): expiration_quantity.doubleValue() );
  }


  /** 
   * <em>expiration_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExpirationQuantityIsNull()
  {
    return expiration_quantity == null;
  }


  /** 
   * <em>expiration_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExpirationQuantityIsSet() {
    return expiration_quantity_is_set;
  }


  /** 
   * <em>expiration_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExpirationQuantityIsModified() {
    return expiration_quantity_is_modified;
  }


  /** 
   * <em>reason_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReasonCode()
  {
    return reason_code;
  }


  /** 
   * <em>reason_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReasonCodeIsSet() {
    return reason_code_is_set;
  }


  /** 
   * <em>reason_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReasonCodeIsModified() {
    return reason_code_is_modified;
  }


  /** 
   * <em>expiration_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExpirationTime()
  {
    return expiration_time;
  }


  /** 
   * <em>expiration_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExpirationTimeIsSet() {
    return expiration_time_is_set;
  }


  /** 
   * <em>expiration_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExpirationTimeIsModified() {
    return expiration_time_is_modified;
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
   * <em>rowid</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRowid()
  {
    return rowid;
  }


  /** 
   * <em>rowid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRowidIsSet() {
    return rowid_is_set;
  }


  /** 
   * <em>rowid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRowidIsModified() {
    return rowid_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new HostFotypeOrderHistoryPK(rowid);
  }


  /** 
   * <em>request_code</em>カラムの値を設定します。 
   *
   * @@param p_requestCode <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setRequestCode( String p_requestCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_code = p_requestCode;
    request_code_is_set = true;
    request_code_is_modified = true;
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
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>target_product_code</em>カラムの値を設定します。 
   *
   * @@param p_targetProductCode <em>target_product_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setTargetProductCode( String p_targetProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    target_product_code = p_targetProductCode;
    target_product_code_is_set = true;
    target_product_code_is_modified = true;
  }


  /** 
   * <em>delivery_month_yyyy</em>カラムの値を設定します。 
   *
   * @@param p_deliveryMonthYyyy <em>delivery_month_yyyy</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setDeliveryMonthYyyy( String p_deliveryMonthYyyy )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_month_yyyy = p_deliveryMonthYyyy;
    delivery_month_yyyy_is_set = true;
    delivery_month_yyyy_is_modified = true;
  }


  /** 
   * <em>delivery_month_mm</em>カラムの値を設定します。 
   *
   * @@param p_deliveryMonthMm <em>delivery_month_mm</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setDeliveryMonthMm( String p_deliveryMonthMm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_month_mm = p_deliveryMonthMm;
    delivery_month_mm_is_set = true;
    delivery_month_mm_is_modified = true;
  }


  /** 
   * <em>future_option_product_type</em>カラムの値を設定します。 
   *
   * @@param p_futureOptionProductType <em>future_option_product_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFutureOptionProductType( String p_futureOptionProductType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    future_option_product_type = p_futureOptionProductType;
    future_option_product_type_is_set = true;
    future_option_product_type_is_modified = true;
  }


  /** 
   * <em>strike_price</em>カラムの値を設定します。 
   *
   * @@param p_strikePrice <em>strike_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setStrikePrice( double p_strikePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    strike_price = new Double(p_strikePrice);
    strike_price_is_set = true;
    strike_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>strike_price</em>カラムに値を設定します。 
   */
  public final void setStrikePrice( Double p_strikePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    strike_price = p_strikePrice;
    strike_price_is_set = true;
    strike_price_is_modified = true;
  }


  /** 
   * <em>corp_code</em>カラムの値を設定します。 
   *
   * @@param p_corpCode <em>corp_code</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setCorpCode( String p_corpCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    corp_code = p_corpCode;
    corp_code_is_set = true;
    corp_code_is_modified = true;
  }


  /** 
   * <em>front_order_exchange_code</em>カラムの値を設定します。 
   *
   * @@param p_frontOrderExchangeCode <em>front_order_exchange_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFrontOrderExchangeCode( String p_frontOrderExchangeCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    front_order_exchange_code = p_frontOrderExchangeCode;
    front_order_exchange_code_is_set = true;
    front_order_exchange_code_is_modified = true;
  }


  /** 
   * <em>front_order_system_code</em>カラムの値を設定します。 
   *
   * @@param p_frontOrderSystemCode <em>front_order_system_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFrontOrderSystemCode( String p_frontOrderSystemCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    front_order_system_code = p_frontOrderSystemCode;
    front_order_system_code_is_set = true;
    front_order_system_code_is_modified = true;
  }


  /** 
   * <em>front_order_trade_code</em>カラムの値を設定します。 
   *
   * @@param p_frontOrderTradeCode <em>front_order_trade_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFrontOrderTradeCode( String p_frontOrderTradeCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    front_order_trade_code = p_frontOrderTradeCode;
    front_order_trade_code_is_set = true;
    front_order_trade_code_is_modified = true;
  }


  /** 
   * <em>received_date_time</em>カラムの値を設定します。 
   *
   * @@param p_receivedDateTime <em>received_date_time</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setReceivedDateTime( String p_receivedDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    received_date_time = p_receivedDateTime;
    received_date_time_is_set = true;
    received_date_time_is_modified = true;
  }


  /** 
   * <em>received_time</em>カラムの値を設定します。 
   *
   * @@param p_receivedTime <em>received_time</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setReceivedTime( String p_receivedTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    received_time = p_receivedTime;
    received_time_is_set = true;
    received_time_is_modified = true;
  }


  /** 
   * <em>data_class_code</em>カラムの値を設定します。 
   *
   * @@param p_dataClassCode <em>data_class_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDataClassCode( String p_dataClassCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_class_code = p_dataClassCode;
    data_class_code_is_set = true;
    data_class_code_is_modified = true;
  }


  /** 
   * <em>data_class_detail_code</em>カラムの値を設定します。 
   *
   * @@param p_dataClassDetailCode <em>data_class_detail_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setDataClassDetailCode( String p_dataClassDetailCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_class_detail_code = p_dataClassDetailCode;
    data_class_detail_code_is_set = true;
    data_class_detail_code_is_modified = true;
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
   * <em>execution_condition</em>カラムの値を設定します。 
   *
   * @@param p_executionCondition <em>execution_condition</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExecutionCondition( String p_executionCondition )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    execution_condition = p_executionCondition;
    execution_condition_is_set = true;
    execution_condition_is_modified = true;
  }


  /** 
   * <em>front_order_time</em>カラムの値を設定します。 
   *
   * @@param p_frontOrderTime <em>front_order_time</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setFrontOrderTime( String p_frontOrderTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    front_order_time = p_frontOrderTime;
    front_order_time_is_set = true;
    front_order_time_is_modified = true;
  }


  /** 
   * <em>sell_order_quantity</em>カラムの値を設定します。 
   *
   * @@param p_sellOrderQuantity <em>sell_order_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSellOrderQuantity( double p_sellOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_order_quantity = new Double(p_sellOrderQuantity);
    sell_order_quantity_is_set = true;
    sell_order_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_order_quantity</em>カラムに値を設定します。 
   */
  public final void setSellOrderQuantity( Double p_sellOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_order_quantity = p_sellOrderQuantity;
    sell_order_quantity_is_set = true;
    sell_order_quantity_is_modified = true;
  }


  /** 
   * <em>buy_order_quantity</em>カラムの値を設定します。 
   *
   * @@param p_buyOrderQuantity <em>buy_order_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBuyOrderQuantity( double p_buyOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_order_quantity = new Double(p_buyOrderQuantity);
    buy_order_quantity_is_set = true;
    buy_order_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_order_quantity</em>カラムに値を設定します。 
   */
  public final void setBuyOrderQuantity( Double p_buyOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_order_quantity = p_buyOrderQuantity;
    buy_order_quantity_is_set = true;
    buy_order_quantity_is_modified = true;
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
   * <em>transaction_type</em>カラムの値を設定します。 
   *
   * @@param p_transactionType <em>transaction_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setTransactionType( String p_transactionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_type = p_transactionType;
    transaction_type_is_set = true;
    transaction_type_is_modified = true;
  }


  /** 
   * <em>contract_check</em>カラムの値を設定します。 
   *
   * @@param p_contractCheck <em>contract_check</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setContractCheck( String p_contractCheck )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_check = p_contractCheck;
    contract_check_is_set = true;
    contract_check_is_modified = true;
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
   * <em>commision_number</em>カラムの値を設定します。 
   *
   * @@param p_commisionNumber <em>commision_number</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCommisionNumber( String p_commisionNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commision_number = p_commisionNumber;
    commision_number_is_set = true;
    commision_number_is_modified = true;
  }


  /** 
   * <em>commision_branch_number</em>カラムの値を設定します。 
   *
   * @@param p_commisionBranchNumber <em>commision_branch_number</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCommisionBranchNumber( String p_commisionBranchNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commision_branch_number = p_commisionBranchNumber;
    commision_branch_number_is_set = true;
    commision_branch_number_is_modified = true;
  }


  /** 
   * <em>commision_product_code</em>カラムの値を設定します。 
   *
   * @@param p_commisionProductCode <em>commision_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCommisionProductCode( String p_commisionProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commision_product_code = p_commisionProductCode;
    commision_product_code_is_set = true;
    commision_product_code_is_modified = true;
  }


  /** 
   * <em>change_quantity</em>カラムの値を設定します。 
   *
   * @@param p_changeQuantity <em>change_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setChangeQuantity( double p_changeQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_quantity = new Double(p_changeQuantity);
    change_quantity_is_set = true;
    change_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>change_quantity</em>カラムに値を設定します。 
   */
  public final void setChangeQuantity( Double p_changeQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    change_quantity = p_changeQuantity;
    change_quantity_is_set = true;
    change_quantity_is_modified = true;
  }


  /** 
   * <em>modified_limit_price</em>カラムの値を設定します。 
   *
   * @@param p_modifiedLimitPrice <em>modified_limit_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setModifiedLimitPrice( double p_modifiedLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_limit_price = new Double(p_modifiedLimitPrice);
    modified_limit_price_is_set = true;
    modified_limit_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>modified_limit_price</em>カラムに値を設定します。 
   */
  public final void setModifiedLimitPrice( Double p_modifiedLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    modified_limit_price = p_modifiedLimitPrice;
    modified_limit_price_is_set = true;
    modified_limit_price_is_modified = true;
  }


  /** 
   * <em>modified_execution_type</em>カラムの値を設定します。 
   *
   * @@param p_modifiedExecutionType <em>modified_execution_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setModifiedExecutionType( String p_modifiedExecutionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_execution_type = p_modifiedExecutionType;
    modified_execution_type_is_set = true;
    modified_execution_type_is_modified = true;
  }


  /** 
   * <em>modified_stop_order_price</em>カラムの値を設定します。 
   *
   * @@param p_modifiedStopOrderPrice <em>modified_stop_order_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setModifiedStopOrderPrice( double p_modifiedStopOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_stop_order_price = new Double(p_modifiedStopOrderPrice);
    modified_stop_order_price_is_set = true;
    modified_stop_order_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>modified_stop_order_price</em>カラムに値を設定します。 
   */
  public final void setModifiedStopOrderPrice( Double p_modifiedStopOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    modified_stop_order_price = p_modifiedStopOrderPrice;
    modified_stop_order_price_is_set = true;
    modified_stop_order_price_is_modified = true;
  }


  /** 
   * <em>modified_w_limit_price</em>カラムの値を設定します。 
   *
   * @@param p_modifiedWLimitPrice <em>modified_w_limit_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setModifiedWLimitPrice( double p_modifiedWLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_w_limit_price = new Double(p_modifiedWLimitPrice);
    modified_w_limit_price_is_set = true;
    modified_w_limit_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>modified_w_limit_price</em>カラムに値を設定します。 
   */
  public final void setModifiedWLimitPrice( Double p_modifiedWLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    modified_w_limit_price = p_modifiedWLimitPrice;
    modified_w_limit_price_is_set = true;
    modified_w_limit_price_is_modified = true;
  }


  /** 
   * <em>cancel_div</em>カラムの値を設定します。 
   *
   * @@param p_cancelDiv <em>cancel_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCancelDiv( String p_cancelDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_div = p_cancelDiv;
    cancel_div_is_set = true;
    cancel_div_is_modified = true;
  }


  /** 
   * <em>org_corp_code</em>カラムの値を設定します。 
   *
   * @@param p_orgCorpCode <em>org_corp_code</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setOrgCorpCode( String p_orgCorpCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    org_corp_code = p_orgCorpCode;
    org_corp_code_is_set = true;
    org_corp_code_is_modified = true;
  }


  /** 
   * <em>canceled_quantity</em>カラムの値を設定します。 
   *
   * @@param p_canceledQuantity <em>canceled_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCanceledQuantity( double p_canceledQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    canceled_quantity = new Double(p_canceledQuantity);
    canceled_quantity_is_set = true;
    canceled_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>canceled_quantity</em>カラムに値を設定します。 
   */
  public final void setCanceledQuantity( Double p_canceledQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    canceled_quantity = p_canceledQuantity;
    canceled_quantity_is_set = true;
    canceled_quantity_is_modified = true;
  }


  /** 
   * <em>exec_price</em>カラムの値を設定します。 
   *
   * @@param p_execPrice <em>exec_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setExecPrice( double p_execPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_price = new Double(p_execPrice);
    exec_price_is_set = true;
    exec_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>exec_price</em>カラムに値を設定します。 
   */
  public final void setExecPrice( Double p_execPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_price = p_execPrice;
    exec_price_is_set = true;
    exec_price_is_modified = true;
  }


  /** 
   * <em>exec_quantity</em>カラムの値を設定します。 
   *
   * @@param p_execQuantity <em>exec_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setExecQuantity( double p_execQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_quantity = new Double(p_execQuantity);
    exec_quantity_is_set = true;
    exec_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>exec_quantity</em>カラムに値を設定します。 
   */
  public final void setExecQuantity( Double p_execQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_quantity = p_execQuantity;
    exec_quantity_is_set = true;
    exec_quantity_is_modified = true;
  }


  /** 
   * <em>exec_time</em>カラムの値を設定します。 
   *
   * @@param p_execTime <em>exec_time</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExecTime( String p_execTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_time = p_execTime;
    exec_time_is_set = true;
    exec_time_is_modified = true;
  }


  /** 
   * <em>price_mark</em>カラムの値を設定します。 
   *
   * @@param p_priceMark <em>price_mark</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPriceMark( String p_priceMark )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price_mark = p_priceMark;
    price_mark_is_set = true;
    price_mark_is_modified = true;
  }


  /** 
   * <em>exec_number</em>カラムの値を設定します。 
   *
   * @@param p_execNumber <em>exec_number</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setExecNumber( int p_execNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_number = p_execNumber;
    exec_number_is_set = true;
    exec_number_is_modified = true;
  }


  /** 
   * <em>accept_number</em>カラムの値を設定します。 
   *
   * @@param p_acceptNumber <em>accept_number</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setAcceptNumber( String p_acceptNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    accept_number = p_acceptNumber;
    accept_number_is_set = true;
    accept_number_is_modified = true;
  }


  /** 
   * <em>virtual_server_number_market</em>カラムの値を設定します。 
   *
   * @@param p_virtualServerNumberMarket <em>virtual_server_number_market</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setVirtualServerNumberMarket( String p_virtualServerNumberMarket )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    virtual_server_number_market = p_virtualServerNumberMarket;
    virtual_server_number_market_is_set = true;
    virtual_server_number_market_is_modified = true;
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
   * <em>stop_mark</em>カラムの値を設定します。 
   *
   * @@param p_stopMark <em>stop_mark</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStopMark( String p_stopMark )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_mark = p_stopMark;
    stop_mark_is_set = true;
    stop_mark_is_modified = true;
  }


  /** 
   * <em>expiration_quantity</em>カラムの値を設定します。 
   *
   * @@param p_expirationQuantity <em>expiration_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setExpirationQuantity( double p_expirationQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    expiration_quantity = new Double(p_expirationQuantity);
    expiration_quantity_is_set = true;
    expiration_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>expiration_quantity</em>カラムに値を設定します。 
   */
  public final void setExpirationQuantity( Double p_expirationQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    expiration_quantity = p_expirationQuantity;
    expiration_quantity_is_set = true;
    expiration_quantity_is_modified = true;
  }


  /** 
   * <em>reason_code</em>カラムの値を設定します。 
   *
   * @@param p_reasonCode <em>reason_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setReasonCode( String p_reasonCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reason_code = p_reasonCode;
    reason_code_is_set = true;
    reason_code_is_modified = true;
  }


  /** 
   * <em>expiration_time</em>カラムの値を設定します。 
   *
   * @@param p_expirationTime <em>expiration_time</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExpirationTime( String p_expirationTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    expiration_time = p_expirationTime;
    expiration_time_is_set = true;
    expiration_time_is_modified = true;
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
   * <em>rowid</em>カラムの値を設定します。 
   *
   * @@param p_rowid <em>rowid</em>カラムの値をあらわす20桁以下のString値で、その精度は20桁まで
   */
  public final void setRowid( String p_rowid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rowid = p_rowid;
    rowid_is_set = true;
    rowid_is_modified = true;
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
                else if ( name.equals("accept_number") ) {
                    return this.accept_number;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("buy_sell_div") ) {
                    return this.buy_sell_div;
                }
                else if ( name.equals("buy_order_quantity") ) {
                    return this.buy_order_quantity;
                }
                break;
            case 'c':
                if ( name.equals("corp_code") ) {
                    return this.corp_code;
                }
                else if ( name.equals("contract_check") ) {
                    return this.contract_check;
                }
                else if ( name.equals("commision_number") ) {
                    return this.commision_number;
                }
                else if ( name.equals("commision_branch_number") ) {
                    return this.commision_branch_number;
                }
                else if ( name.equals("commision_product_code") ) {
                    return this.commision_product_code;
                }
                else if ( name.equals("change_quantity") ) {
                    return this.change_quantity;
                }
                else if ( name.equals("cancel_div") ) {
                    return this.cancel_div;
                }
                else if ( name.equals("canceled_quantity") ) {
                    return this.canceled_quantity;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_month_yyyy") ) {
                    return this.delivery_month_yyyy;
                }
                else if ( name.equals("delivery_month_mm") ) {
                    return this.delivery_month_mm;
                }
                else if ( name.equals("data_class_code") ) {
                    return this.data_class_code;
                }
                else if ( name.equals("data_class_detail_code") ) {
                    return this.data_class_detail_code;
                }
                break;
            case 'e':
                if ( name.equals("execution_condition") ) {
                    return this.execution_condition;
                }
                else if ( name.equals("exec_price") ) {
                    return this.exec_price;
                }
                else if ( name.equals("exec_quantity") ) {
                    return this.exec_quantity;
                }
                else if ( name.equals("exec_time") ) {
                    return this.exec_time;
                }
                else if ( name.equals("exec_number") ) {
                    return new Integer( exec_number );
                }
                else if ( name.equals("executed_quantity") ) {
                    return this.executed_quantity;
                }
                else if ( name.equals("expiration_quantity") ) {
                    return this.expiration_quantity;
                }
                else if ( name.equals("expiration_time") ) {
                    return this.expiration_time;
                }
                break;
            case 'f':
                if ( name.equals("future_option_product_type") ) {
                    return this.future_option_product_type;
                }
                else if ( name.equals("front_order_exchange_code") ) {
                    return this.front_order_exchange_code;
                }
                else if ( name.equals("front_order_system_code") ) {
                    return this.front_order_system_code;
                }
                else if ( name.equals("front_order_trade_code") ) {
                    return this.front_order_trade_code;
                }
                else if ( name.equals("front_order_time") ) {
                    return this.front_order_time;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
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
                if ( name.equals("modified_limit_price") ) {
                    return this.modified_limit_price;
                }
                else if ( name.equals("modified_execution_type") ) {
                    return this.modified_execution_type;
                }
                else if ( name.equals("modified_stop_order_price") ) {
                    return this.modified_stop_order_price;
                }
                else if ( name.equals("modified_w_limit_price") ) {
                    return this.modified_w_limit_price;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_chanel") ) {
                    return this.order_chanel;
                }
                else if ( name.equals("org_corp_code") ) {
                    return this.org_corp_code;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("price_mark") ) {
                    return this.price_mark;
                }
                else if ( name.equals("price") ) {
                    return this.price;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("received_date_time") ) {
                    return this.received_date_time;
                }
                else if ( name.equals("received_time") ) {
                    return this.received_time;
                }
                else if ( name.equals("reason_code") ) {
                    return this.reason_code;
                }
                else if ( name.equals("rowid") ) {
                    return this.rowid;
                }
                break;
            case 's':
                if ( name.equals("sonar_market_code") ) {
                    return this.sonar_market_code;
                }
                else if ( name.equals("strike_price") ) {
                    return this.strike_price;
                }
                else if ( name.equals("sell_order_quantity") ) {
                    return this.sell_order_quantity;
                }
                else if ( name.equals("stop_order_price") ) {
                    return this.stop_order_price;
                }
                else if ( name.equals("stop_mark") ) {
                    return this.stop_mark;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("target_product_code") ) {
                    return this.target_product_code;
                }
                else if ( name.equals("transaction_type") ) {
                    return this.transaction_type;
                }
                break;
            case 'v':
                if ( name.equals("virtual_server_number_market") ) {
                    return this.virtual_server_number_market;
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
                if ( name.equals("account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("accept_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'accept_number' must be String: '"+value+"' is not." );
                    this.accept_number = (String) value;
                    if (this.accept_number_is_set)
                        this.accept_number_is_modified = true;
                    this.accept_number_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( value != null )
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
                else if ( name.equals("buy_order_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'buy_order_quantity' must be Double: '"+value+"' is not." );
                    this.buy_order_quantity = (Double) value;
                    if (this.buy_order_quantity_is_set)
                        this.buy_order_quantity_is_modified = true;
                    this.buy_order_quantity_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("corp_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'corp_code' must be String: '"+value+"' is not." );
                    this.corp_code = (String) value;
                    if (this.corp_code_is_set)
                        this.corp_code_is_modified = true;
                    this.corp_code_is_set = true;
                    return;
                }
                else if ( name.equals("contract_check") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'contract_check' must be String: '"+value+"' is not." );
                    this.contract_check = (String) value;
                    if (this.contract_check_is_set)
                        this.contract_check_is_modified = true;
                    this.contract_check_is_set = true;
                    return;
                }
                else if ( name.equals("commision_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commision_number' must be String: '"+value+"' is not." );
                    this.commision_number = (String) value;
                    if (this.commision_number_is_set)
                        this.commision_number_is_modified = true;
                    this.commision_number_is_set = true;
                    return;
                }
                else if ( name.equals("commision_branch_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commision_branch_number' must be String: '"+value+"' is not." );
                    this.commision_branch_number = (String) value;
                    if (this.commision_branch_number_is_set)
                        this.commision_branch_number_is_modified = true;
                    this.commision_branch_number_is_set = true;
                    return;
                }
                else if ( name.equals("commision_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commision_product_code' must be String: '"+value+"' is not." );
                    this.commision_product_code = (String) value;
                    if (this.commision_product_code_is_set)
                        this.commision_product_code_is_modified = true;
                    this.commision_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("change_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'change_quantity' must be Double: '"+value+"' is not." );
                    this.change_quantity = (Double) value;
                    if (this.change_quantity_is_set)
                        this.change_quantity_is_modified = true;
                    this.change_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("cancel_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cancel_div' must be String: '"+value+"' is not." );
                    this.cancel_div = (String) value;
                    if (this.cancel_div_is_set)
                        this.cancel_div_is_modified = true;
                    this.cancel_div_is_set = true;
                    return;
                }
                else if ( name.equals("canceled_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'canceled_quantity' must be Double: '"+value+"' is not." );
                    this.canceled_quantity = (Double) value;
                    if (this.canceled_quantity_is_set)
                        this.canceled_quantity_is_modified = true;
                    this.canceled_quantity_is_set = true;
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
                if ( name.equals("delivery_month_yyyy") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delivery_month_yyyy' must be String: '"+value+"' is not." );
                    this.delivery_month_yyyy = (String) value;
                    if (this.delivery_month_yyyy_is_set)
                        this.delivery_month_yyyy_is_modified = true;
                    this.delivery_month_yyyy_is_set = true;
                    return;
                }
                else if ( name.equals("delivery_month_mm") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delivery_month_mm' must be String: '"+value+"' is not." );
                    this.delivery_month_mm = (String) value;
                    if (this.delivery_month_mm_is_set)
                        this.delivery_month_mm_is_modified = true;
                    this.delivery_month_mm_is_set = true;
                    return;
                }
                else if ( name.equals("data_class_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'data_class_code' must be String: '"+value+"' is not." );
                    this.data_class_code = (String) value;
                    if (this.data_class_code_is_set)
                        this.data_class_code_is_modified = true;
                    this.data_class_code_is_set = true;
                    return;
                }
                else if ( name.equals("data_class_detail_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'data_class_detail_code' must be String: '"+value+"' is not." );
                    this.data_class_detail_code = (String) value;
                    if (this.data_class_detail_code_is_set)
                        this.data_class_detail_code_is_modified = true;
                    this.data_class_detail_code_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("execution_condition") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'execution_condition' must be String: '"+value+"' is not." );
                    this.execution_condition = (String) value;
                    if (this.execution_condition_is_set)
                        this.execution_condition_is_modified = true;
                    this.execution_condition_is_set = true;
                    return;
                }
                else if ( name.equals("exec_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'exec_price' must be Double: '"+value+"' is not." );
                    this.exec_price = (Double) value;
                    if (this.exec_price_is_set)
                        this.exec_price_is_modified = true;
                    this.exec_price_is_set = true;
                    return;
                }
                else if ( name.equals("exec_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'exec_quantity' must be Double: '"+value+"' is not." );
                    this.exec_quantity = (Double) value;
                    if (this.exec_quantity_is_set)
                        this.exec_quantity_is_modified = true;
                    this.exec_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("exec_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exec_time' must be String: '"+value+"' is not." );
                    this.exec_time = (String) value;
                    if (this.exec_time_is_set)
                        this.exec_time_is_modified = true;
                    this.exec_time_is_set = true;
                    return;
                }
                else if ( name.equals("exec_number") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'exec_number' must be Integer: '"+value+"' is not." );
                    this.exec_number = ((Integer) value).intValue();
                    if (this.exec_number_is_set)
                        this.exec_number_is_modified = true;
                    this.exec_number_is_set = true;
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
                else if ( name.equals("expiration_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'expiration_quantity' must be Double: '"+value+"' is not." );
                    this.expiration_quantity = (Double) value;
                    if (this.expiration_quantity_is_set)
                        this.expiration_quantity_is_modified = true;
                    this.expiration_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("expiration_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'expiration_time' must be String: '"+value+"' is not." );
                    this.expiration_time = (String) value;
                    if (this.expiration_time_is_set)
                        this.expiration_time_is_modified = true;
                    this.expiration_time_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("future_option_product_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'future_option_product_type' must be String: '"+value+"' is not." );
                    this.future_option_product_type = (String) value;
                    if (this.future_option_product_type_is_set)
                        this.future_option_product_type_is_modified = true;
                    this.future_option_product_type_is_set = true;
                    return;
                }
                else if ( name.equals("front_order_exchange_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'front_order_exchange_code' must be String: '"+value+"' is not." );
                    this.front_order_exchange_code = (String) value;
                    if (this.front_order_exchange_code_is_set)
                        this.front_order_exchange_code_is_modified = true;
                    this.front_order_exchange_code_is_set = true;
                    return;
                }
                else if ( name.equals("front_order_system_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'front_order_system_code' must be String: '"+value+"' is not." );
                    this.front_order_system_code = (String) value;
                    if (this.front_order_system_code_is_set)
                        this.front_order_system_code_is_modified = true;
                    this.front_order_system_code_is_set = true;
                    return;
                }
                else if ( name.equals("front_order_trade_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'front_order_trade_code' must be String: '"+value+"' is not." );
                    this.front_order_trade_code = (String) value;
                    if (this.front_order_trade_code_is_set)
                        this.front_order_trade_code_is_modified = true;
                    this.front_order_trade_code_is_set = true;
                    return;
                }
                else if ( name.equals("front_order_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'front_order_time' must be String: '"+value+"' is not." );
                    this.front_order_time = (String) value;
                    if (this.front_order_time_is_set)
                        this.front_order_time_is_modified = true;
                    this.front_order_time_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( value != null )
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
                if ( name.equals("modified_limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'modified_limit_price' must be Double: '"+value+"' is not." );
                    this.modified_limit_price = (Double) value;
                    if (this.modified_limit_price_is_set)
                        this.modified_limit_price_is_modified = true;
                    this.modified_limit_price_is_set = true;
                    return;
                }
                else if ( name.equals("modified_execution_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'modified_execution_type' must be String: '"+value+"' is not." );
                    this.modified_execution_type = (String) value;
                    if (this.modified_execution_type_is_set)
                        this.modified_execution_type_is_modified = true;
                    this.modified_execution_type_is_set = true;
                    return;
                }
                else if ( name.equals("modified_stop_order_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'modified_stop_order_price' must be Double: '"+value+"' is not." );
                    this.modified_stop_order_price = (Double) value;
                    if (this.modified_stop_order_price_is_set)
                        this.modified_stop_order_price_is_modified = true;
                    this.modified_stop_order_price_is_set = true;
                    return;
                }
                else if ( name.equals("modified_w_limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'modified_w_limit_price' must be Double: '"+value+"' is not." );
                    this.modified_w_limit_price = (Double) value;
                    if (this.modified_w_limit_price_is_set)
                        this.modified_w_limit_price_is_modified = true;
                    this.modified_w_limit_price_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
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
                else if ( name.equals("org_corp_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'org_corp_code' must be String: '"+value+"' is not." );
                    this.org_corp_code = (String) value;
                    if (this.org_corp_code_is_set)
                        this.org_corp_code_is_modified = true;
                    this.org_corp_code_is_set = true;
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
                else if ( name.equals("price_mark") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'price_mark' must be String: '"+value+"' is not." );
                    this.price_mark = (String) value;
                    if (this.price_mark_is_set)
                        this.price_mark_is_modified = true;
                    this.price_mark_is_set = true;
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
            case 'r':
                if ( name.equals("request_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_code' must be String: '"+value+"' is not." );
                    this.request_code = (String) value;
                    if (this.request_code_is_set)
                        this.request_code_is_modified = true;
                    this.request_code_is_set = true;
                    return;
                }
                else if ( name.equals("received_date_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'received_date_time' must be String: '"+value+"' is not." );
                    this.received_date_time = (String) value;
                    if (this.received_date_time_is_set)
                        this.received_date_time_is_modified = true;
                    this.received_date_time_is_set = true;
                    return;
                }
                else if ( name.equals("received_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'received_time' must be String: '"+value+"' is not." );
                    this.received_time = (String) value;
                    if (this.received_time_is_set)
                        this.received_time_is_modified = true;
                    this.received_time_is_set = true;
                    return;
                }
                else if ( name.equals("reason_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reason_code' must be String: '"+value+"' is not." );
                    this.reason_code = (String) value;
                    if (this.reason_code_is_set)
                        this.reason_code_is_modified = true;
                    this.reason_code_is_set = true;
                    return;
                }
                else if ( name.equals("rowid") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rowid' must be String: '"+value+"' is not." );
                    this.rowid = (String) value;
                    if (this.rowid_is_set)
                        this.rowid_is_modified = true;
                    this.rowid_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_market_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_market_code' must be String: '"+value+"' is not." );
                    this.sonar_market_code = (String) value;
                    if (this.sonar_market_code_is_set)
                        this.sonar_market_code_is_modified = true;
                    this.sonar_market_code_is_set = true;
                    return;
                }
                else if ( name.equals("strike_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'strike_price' must be Double: '"+value+"' is not." );
                    this.strike_price = (Double) value;
                    if (this.strike_price_is_set)
                        this.strike_price_is_modified = true;
                    this.strike_price_is_set = true;
                    return;
                }
                else if ( name.equals("sell_order_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'sell_order_quantity' must be Double: '"+value+"' is not." );
                    this.sell_order_quantity = (Double) value;
                    if (this.sell_order_quantity_is_set)
                        this.sell_order_quantity_is_modified = true;
                    this.sell_order_quantity_is_set = true;
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
                else if ( name.equals("stop_mark") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_mark' must be String: '"+value+"' is not." );
                    this.stop_mark = (String) value;
                    if (this.stop_mark_is_set)
                        this.stop_mark_is_modified = true;
                    this.stop_mark_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( value != null )
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
                else if ( name.equals("target_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'target_product_code' must be String: '"+value+"' is not." );
                    this.target_product_code = (String) value;
                    if (this.target_product_code_is_set)
                        this.target_product_code_is_modified = true;
                    this.target_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("transaction_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transaction_type' must be String: '"+value+"' is not." );
                    this.transaction_type = (String) value;
                    if (this.transaction_type_is_set)
                        this.transaction_type_is_modified = true;
                    this.transaction_type_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("virtual_server_number_market") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'virtual_server_number_market' must be String: '"+value+"' is not." );
                    this.virtual_server_number_market = (String) value;
                    if (this.virtual_server_number_market_is_set)
                        this.virtual_server_number_market_is_modified = true;
                    this.virtual_server_number_market_is_set = true;
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
