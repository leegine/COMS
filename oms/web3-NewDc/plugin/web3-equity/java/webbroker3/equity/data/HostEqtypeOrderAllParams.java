head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostEqtypeOrderAllParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * host_eqtype_order_allテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostEqtypeOrderAllRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostEqtypeOrderAllParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostEqtypeOrderAllParams}が{@@link HostEqtypeOrderAllRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostEqtypeOrderAllPK 
 * @@see HostEqtypeOrderAllRow 
 */
public class HostEqtypeOrderAllParams extends Params implements HostEqtypeOrderAllRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_eqtype_order_all";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostEqtypeOrderAllRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostEqtypeOrderAllRow.TYPE;
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
   * <em>account_id</em>カラムの値 
   */
  public  Long  account_id;

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
   * <em>sonar_trader_code</em>カラムの値 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>order_action_serial_no</em>カラムの値 
   */
  public  Integer  order_action_serial_no;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>received_date_time</em>カラムの値 
   */
  public  java.sql.Timestamp  received_date_time;

  /** 
   * <em>submit_order_route_div</em>カラムの値 
   */
  public  String  submit_order_route_div;

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
   * <em>execution_condition</em>カラムの値 
   */
  public  String  execution_condition;

  /** 
   * <em>price_condition_type</em>カラムの値 
   */
  public  String  price_condition_type;

  /** 
   * <em>sonar_traded_code</em>カラムの値 
   */
  public  String  sonar_traded_code;

  /** 
   * <em>sonar_repayment_type</em>カラムの値 
   */
  public  String  sonar_repayment_type;

  /** 
   * <em>sonar_market_code</em>カラムの値 
   */
  public  String  sonar_market_code;

  /** 
   * <em>ticket_number</em>カラムの値 
   */
  public  String  ticket_number;

  /** 
   * <em>received_date_time_div</em>カラムの値 
   */
  public  String  received_date_time_div;

  /** 
   * <em>tax_type</em>カラムの値 
   */
  public  String  tax_type;

  /** 
   * <em>capital_gain_tax_type</em>カラムの値 
   */
  public  String  capital_gain_tax_type;

  /** 
   * <em>check_type</em>カラムの値 
   */
  public  String  check_type;

  /** 
   * <em>order_chanel</em>カラムの値 
   */
  public  String  order_chanel;

  /** 
   * <em>factor</em>カラムの値 
   */
  public  String  factor;

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
   * <em>short_sell_order_flag</em>カラムの値 
   */
  public  String  short_sell_order_flag;

  /** 
   * <em>change_quantity</em>カラムの値 
   */
  public  Double  change_quantity;

  /** 
   * <em>change_limit_price</em>カラムの値 
   */
  public  Double  change_limit_price;

  /** 
   * <em>change_execution_condition</em>カラムの値 
   */
  public  String  change_execution_condition;

  /** 
   * <em>change_price_condition_type</em>カラムの値 
   */
  public  String  change_price_condition_type;

  /** 
   * <em>change_order_time</em>カラムの値 
   */
  public  String  change_order_time;

  /** 
   * <em>change_order_date_div</em>カラムの値 
   */
  public  String  change_order_date_div;

  /** 
   * <em>cancel_div</em>カラムの値 
   */
  public  String  cancel_div;

  /** 
   * <em>cancel_order_time</em>カラムの値 
   */
  public  String  cancel_order_time;

  /** 
   * <em>cancel_order_date_div</em>カラムの値 
   */
  public  String  cancel_order_date_div;

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
   * <em>tradeaudit_code</em>カラムの値 
   */
  public  String  tradeaudit_code;

  /** 
   * <em>corp_code</em>カラムの値 
   */
  public  String  corp_code;

  /** 
   * <em>org_corp_code</em>カラムの値 
   */
  public  String  org_corp_code;

  /** 
   * <em>virtual_server_number_jsoes</em>カラムの値 
   */
  public  String  virtual_server_number_jsoes;

  /** 
   * <em>market_order_number</em>カラムの値 
   */
  public  Long  market_order_number;

  /** 
   * <em>amg_send_time</em>カラムの値 
   */
  public  java.sql.Timestamp  amg_send_time;

  /** 
   * <em>amg_ack_time</em>カラムの値 
   */
  public  java.sql.Timestamp  amg_ack_time;

  /** 
   * <em>market_ack_time</em>カラムの値 
   */
  public  java.sql.Timestamp  market_ack_time;

  /** 
   * <em>all_order_change_div</em>カラムの値 
   */
  public  String  all_order_change_div;

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
   * <em>market_close_flag</em>カラムの値 
   */
  public  String  market_close_flag;

  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean order_action_serial_no_is_set = false;
  private boolean order_action_serial_no_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean received_date_time_is_set = false;
  private boolean received_date_time_is_modified = false;
  private boolean submit_order_route_div_is_set = false;
  private boolean submit_order_route_div_is_modified = false;
  private boolean sell_order_quantity_is_set = false;
  private boolean sell_order_quantity_is_modified = false;
  private boolean buy_order_quantity_is_set = false;
  private boolean buy_order_quantity_is_modified = false;
  private boolean limit_price_is_set = false;
  private boolean limit_price_is_modified = false;
  private boolean execution_condition_is_set = false;
  private boolean execution_condition_is_modified = false;
  private boolean price_condition_type_is_set = false;
  private boolean price_condition_type_is_modified = false;
  private boolean sonar_traded_code_is_set = false;
  private boolean sonar_traded_code_is_modified = false;
  private boolean sonar_repayment_type_is_set = false;
  private boolean sonar_repayment_type_is_modified = false;
  private boolean sonar_market_code_is_set = false;
  private boolean sonar_market_code_is_modified = false;
  private boolean ticket_number_is_set = false;
  private boolean ticket_number_is_modified = false;
  private boolean received_date_time_div_is_set = false;
  private boolean received_date_time_div_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean capital_gain_tax_type_is_set = false;
  private boolean capital_gain_tax_type_is_modified = false;
  private boolean check_type_is_set = false;
  private boolean check_type_is_modified = false;
  private boolean order_chanel_is_set = false;
  private boolean order_chanel_is_modified = false;
  private boolean factor_is_set = false;
  private boolean factor_is_modified = false;
  private boolean commision_number_is_set = false;
  private boolean commision_number_is_modified = false;
  private boolean commision_branch_number_is_set = false;
  private boolean commision_branch_number_is_modified = false;
  private boolean commision_product_code_is_set = false;
  private boolean commision_product_code_is_modified = false;
  private boolean short_sell_order_flag_is_set = false;
  private boolean short_sell_order_flag_is_modified = false;
  private boolean change_quantity_is_set = false;
  private boolean change_quantity_is_modified = false;
  private boolean change_limit_price_is_set = false;
  private boolean change_limit_price_is_modified = false;
  private boolean change_execution_condition_is_set = false;
  private boolean change_execution_condition_is_modified = false;
  private boolean change_price_condition_type_is_set = false;
  private boolean change_price_condition_type_is_modified = false;
  private boolean change_order_time_is_set = false;
  private boolean change_order_time_is_modified = false;
  private boolean change_order_date_div_is_set = false;
  private boolean change_order_date_div_is_modified = false;
  private boolean cancel_div_is_set = false;
  private boolean cancel_div_is_modified = false;
  private boolean cancel_order_time_is_set = false;
  private boolean cancel_order_time_is_modified = false;
  private boolean cancel_order_date_div_is_set = false;
  private boolean cancel_order_date_div_is_modified = false;
  private boolean front_order_exchange_code_is_set = false;
  private boolean front_order_exchange_code_is_modified = false;
  private boolean front_order_system_code_is_set = false;
  private boolean front_order_system_code_is_modified = false;
  private boolean front_order_trade_code_is_set = false;
  private boolean front_order_trade_code_is_modified = false;
  private boolean tradeaudit_code_is_set = false;
  private boolean tradeaudit_code_is_modified = false;
  private boolean corp_code_is_set = false;
  private boolean corp_code_is_modified = false;
  private boolean org_corp_code_is_set = false;
  private boolean org_corp_code_is_modified = false;
  private boolean virtual_server_number_jsoes_is_set = false;
  private boolean virtual_server_number_jsoes_is_modified = false;
  private boolean market_order_number_is_set = false;
  private boolean market_order_number_is_modified = false;
  private boolean amg_send_time_is_set = false;
  private boolean amg_send_time_is_modified = false;
  private boolean amg_ack_time_is_set = false;
  private boolean amg_ack_time_is_modified = false;
  private boolean market_ack_time_is_set = false;
  private boolean market_ack_time_is_modified = false;
  private boolean all_order_change_div_is_set = false;
  private boolean all_order_change_div_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean market_close_flag_is_set = false;
  private boolean market_close_flag_is_modified = false;
  private boolean rowid_is_set = false;
  private boolean rowid_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "rowid=" + rowid
      + "," + "request_code=" +request_code
      + "," + "account_id=" +account_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "order_request_number=" +order_request_number
      + "," + "order_action_serial_no=" +order_action_serial_no
      + "," + "product_code=" +product_code
      + "," + "received_date_time=" +received_date_time
      + "," + "submit_order_route_div=" +submit_order_route_div
      + "," + "sell_order_quantity=" +sell_order_quantity
      + "," + "buy_order_quantity=" +buy_order_quantity
      + "," + "limit_price=" +limit_price
      + "," + "execution_condition=" +execution_condition
      + "," + "price_condition_type=" +price_condition_type
      + "," + "sonar_traded_code=" +sonar_traded_code
      + "," + "sonar_repayment_type=" +sonar_repayment_type
      + "," + "sonar_market_code=" +sonar_market_code
      + "," + "ticket_number=" +ticket_number
      + "," + "received_date_time_div=" +received_date_time_div
      + "," + "tax_type=" +tax_type
      + "," + "capital_gain_tax_type=" +capital_gain_tax_type
      + "," + "check_type=" +check_type
      + "," + "order_chanel=" +order_chanel
      + "," + "factor=" +factor
      + "," + "commision_number=" +commision_number
      + "," + "commision_branch_number=" +commision_branch_number
      + "," + "commision_product_code=" +commision_product_code
      + "," + "short_sell_order_flag=" +short_sell_order_flag
      + "," + "change_quantity=" +change_quantity
      + "," + "change_limit_price=" +change_limit_price
      + "," + "change_execution_condition=" +change_execution_condition
      + "," + "change_price_condition_type=" +change_price_condition_type
      + "," + "change_order_time=" +change_order_time
      + "," + "change_order_date_div=" +change_order_date_div
      + "," + "cancel_div=" +cancel_div
      + "," + "cancel_order_time=" +cancel_order_time
      + "," + "cancel_order_date_div=" +cancel_order_date_div
      + "," + "front_order_exchange_code=" +front_order_exchange_code
      + "," + "front_order_system_code=" +front_order_system_code
      + "," + "front_order_trade_code=" +front_order_trade_code
      + "," + "tradeaudit_code=" +tradeaudit_code
      + "," + "corp_code=" +corp_code
      + "," + "org_corp_code=" +org_corp_code
      + "," + "virtual_server_number_jsoes=" +virtual_server_number_jsoes
      + "," + "market_order_number=" +market_order_number
      + "," + "amg_send_time=" +amg_send_time
      + "," + "amg_ack_time=" +amg_ack_time
      + "," + "market_ack_time=" +market_ack_time
      + "," + "all_order_change_div=" +all_order_change_div
      + "," + "status=" +status
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "market_close_flag=" +market_close_flag
      + "}";
  }


  /** 
   * 値が未設定のHostEqtypeOrderAllParamsオブジェクトを作成します。 
   */
  public HostEqtypeOrderAllParams() {
    rowid_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostEqtypeOrderAllRowオブジェクトの値を利用してHostEqtypeOrderAllParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostEqtypeOrderAllRowオブジェクト 
   */
  public HostEqtypeOrderAllParams( HostEqtypeOrderAllRow p_row )
  {
    rowid = p_row.getRowid();
    rowid_is_set = p_row.getRowidIsSet();
    rowid_is_modified = p_row.getRowidIsModified();
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    if ( !p_row.getAccountIdIsNull() )
      account_id = new Long( p_row.getAccountId() );
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
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    if ( !p_row.getOrderActionSerialNoIsNull() )
      order_action_serial_no = new Integer( p_row.getOrderActionSerialNo() );
    order_action_serial_no_is_set = p_row.getOrderActionSerialNoIsSet();
    order_action_serial_no_is_modified = p_row.getOrderActionSerialNoIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    received_date_time = p_row.getReceivedDateTime();
    received_date_time_is_set = p_row.getReceivedDateTimeIsSet();
    received_date_time_is_modified = p_row.getReceivedDateTimeIsModified();
    submit_order_route_div = p_row.getSubmitOrderRouteDiv();
    submit_order_route_div_is_set = p_row.getSubmitOrderRouteDivIsSet();
    submit_order_route_div_is_modified = p_row.getSubmitOrderRouteDivIsModified();
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
    execution_condition = p_row.getExecutionCondition();
    execution_condition_is_set = p_row.getExecutionConditionIsSet();
    execution_condition_is_modified = p_row.getExecutionConditionIsModified();
    price_condition_type = p_row.getPriceConditionType();
    price_condition_type_is_set = p_row.getPriceConditionTypeIsSet();
    price_condition_type_is_modified = p_row.getPriceConditionTypeIsModified();
    sonar_traded_code = p_row.getSonarTradedCode();
    sonar_traded_code_is_set = p_row.getSonarTradedCodeIsSet();
    sonar_traded_code_is_modified = p_row.getSonarTradedCodeIsModified();
    sonar_repayment_type = p_row.getSonarRepaymentType();
    sonar_repayment_type_is_set = p_row.getSonarRepaymentTypeIsSet();
    sonar_repayment_type_is_modified = p_row.getSonarRepaymentTypeIsModified();
    sonar_market_code = p_row.getSonarMarketCode();
    sonar_market_code_is_set = p_row.getSonarMarketCodeIsSet();
    sonar_market_code_is_modified = p_row.getSonarMarketCodeIsModified();
    ticket_number = p_row.getTicketNumber();
    ticket_number_is_set = p_row.getTicketNumberIsSet();
    ticket_number_is_modified = p_row.getTicketNumberIsModified();
    received_date_time_div = p_row.getReceivedDateTimeDiv();
    received_date_time_div_is_set = p_row.getReceivedDateTimeDivIsSet();
    received_date_time_div_is_modified = p_row.getReceivedDateTimeDivIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    capital_gain_tax_type = p_row.getCapitalGainTaxType();
    capital_gain_tax_type_is_set = p_row.getCapitalGainTaxTypeIsSet();
    capital_gain_tax_type_is_modified = p_row.getCapitalGainTaxTypeIsModified();
    check_type = p_row.getCheckType();
    check_type_is_set = p_row.getCheckTypeIsSet();
    check_type_is_modified = p_row.getCheckTypeIsModified();
    order_chanel = p_row.getOrderChanel();
    order_chanel_is_set = p_row.getOrderChanelIsSet();
    order_chanel_is_modified = p_row.getOrderChanelIsModified();
    factor = p_row.getFactor();
    factor_is_set = p_row.getFactorIsSet();
    factor_is_modified = p_row.getFactorIsModified();
    commision_number = p_row.getCommisionNumber();
    commision_number_is_set = p_row.getCommisionNumberIsSet();
    commision_number_is_modified = p_row.getCommisionNumberIsModified();
    commision_branch_number = p_row.getCommisionBranchNumber();
    commision_branch_number_is_set = p_row.getCommisionBranchNumberIsSet();
    commision_branch_number_is_modified = p_row.getCommisionBranchNumberIsModified();
    commision_product_code = p_row.getCommisionProductCode();
    commision_product_code_is_set = p_row.getCommisionProductCodeIsSet();
    commision_product_code_is_modified = p_row.getCommisionProductCodeIsModified();
    short_sell_order_flag = p_row.getShortSellOrderFlag();
    short_sell_order_flag_is_set = p_row.getShortSellOrderFlagIsSet();
    short_sell_order_flag_is_modified = p_row.getShortSellOrderFlagIsModified();
    if ( !p_row.getChangeQuantityIsNull() )
      change_quantity = new Double( p_row.getChangeQuantity() );
    change_quantity_is_set = p_row.getChangeQuantityIsSet();
    change_quantity_is_modified = p_row.getChangeQuantityIsModified();
    if ( !p_row.getChangeLimitPriceIsNull() )
      change_limit_price = new Double( p_row.getChangeLimitPrice() );
    change_limit_price_is_set = p_row.getChangeLimitPriceIsSet();
    change_limit_price_is_modified = p_row.getChangeLimitPriceIsModified();
    change_execution_condition = p_row.getChangeExecutionCondition();
    change_execution_condition_is_set = p_row.getChangeExecutionConditionIsSet();
    change_execution_condition_is_modified = p_row.getChangeExecutionConditionIsModified();
    change_price_condition_type = p_row.getChangePriceConditionType();
    change_price_condition_type_is_set = p_row.getChangePriceConditionTypeIsSet();
    change_price_condition_type_is_modified = p_row.getChangePriceConditionTypeIsModified();
    change_order_time = p_row.getChangeOrderTime();
    change_order_time_is_set = p_row.getChangeOrderTimeIsSet();
    change_order_time_is_modified = p_row.getChangeOrderTimeIsModified();
    change_order_date_div = p_row.getChangeOrderDateDiv();
    change_order_date_div_is_set = p_row.getChangeOrderDateDivIsSet();
    change_order_date_div_is_modified = p_row.getChangeOrderDateDivIsModified();
    cancel_div = p_row.getCancelDiv();
    cancel_div_is_set = p_row.getCancelDivIsSet();
    cancel_div_is_modified = p_row.getCancelDivIsModified();
    cancel_order_time = p_row.getCancelOrderTime();
    cancel_order_time_is_set = p_row.getCancelOrderTimeIsSet();
    cancel_order_time_is_modified = p_row.getCancelOrderTimeIsModified();
    cancel_order_date_div = p_row.getCancelOrderDateDiv();
    cancel_order_date_div_is_set = p_row.getCancelOrderDateDivIsSet();
    cancel_order_date_div_is_modified = p_row.getCancelOrderDateDivIsModified();
    front_order_exchange_code = p_row.getFrontOrderExchangeCode();
    front_order_exchange_code_is_set = p_row.getFrontOrderExchangeCodeIsSet();
    front_order_exchange_code_is_modified = p_row.getFrontOrderExchangeCodeIsModified();
    front_order_system_code = p_row.getFrontOrderSystemCode();
    front_order_system_code_is_set = p_row.getFrontOrderSystemCodeIsSet();
    front_order_system_code_is_modified = p_row.getFrontOrderSystemCodeIsModified();
    front_order_trade_code = p_row.getFrontOrderTradeCode();
    front_order_trade_code_is_set = p_row.getFrontOrderTradeCodeIsSet();
    front_order_trade_code_is_modified = p_row.getFrontOrderTradeCodeIsModified();
    tradeaudit_code = p_row.getTradeauditCode();
    tradeaudit_code_is_set = p_row.getTradeauditCodeIsSet();
    tradeaudit_code_is_modified = p_row.getTradeauditCodeIsModified();
    corp_code = p_row.getCorpCode();
    corp_code_is_set = p_row.getCorpCodeIsSet();
    corp_code_is_modified = p_row.getCorpCodeIsModified();
    org_corp_code = p_row.getOrgCorpCode();
    org_corp_code_is_set = p_row.getOrgCorpCodeIsSet();
    org_corp_code_is_modified = p_row.getOrgCorpCodeIsModified();
    virtual_server_number_jsoes = p_row.getVirtualServerNumberJsoes();
    virtual_server_number_jsoes_is_set = p_row.getVirtualServerNumberJsoesIsSet();
    virtual_server_number_jsoes_is_modified = p_row.getVirtualServerNumberJsoesIsModified();
    if ( !p_row.getMarketOrderNumberIsNull() )
      market_order_number = new Long( p_row.getMarketOrderNumber() );
    market_order_number_is_set = p_row.getMarketOrderNumberIsSet();
    market_order_number_is_modified = p_row.getMarketOrderNumberIsModified();
    amg_send_time = p_row.getAmgSendTime();
    amg_send_time_is_set = p_row.getAmgSendTimeIsSet();
    amg_send_time_is_modified = p_row.getAmgSendTimeIsModified();
    amg_ack_time = p_row.getAmgAckTime();
    amg_ack_time_is_set = p_row.getAmgAckTimeIsSet();
    amg_ack_time_is_modified = p_row.getAmgAckTimeIsModified();
    market_ack_time = p_row.getMarketAckTime();
    market_ack_time_is_set = p_row.getMarketAckTimeIsSet();
    market_ack_time_is_modified = p_row.getMarketAckTimeIsModified();
    all_order_change_div = p_row.getAllOrderChangeDiv();
    all_order_change_div_is_set = p_row.getAllOrderChangeDivIsSet();
    all_order_change_div_is_modified = p_row.getAllOrderChangeDivIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    market_close_flag = p_row.getMarketCloseFlag();
    market_close_flag_is_set = p_row.getMarketCloseFlagIsSet();
    market_close_flag_is_modified = p_row.getMarketCloseFlagIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      request_code_is_set = true;
      request_code_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      order_action_serial_no_is_set = true;
      order_action_serial_no_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      received_date_time_is_set = true;
      received_date_time_is_modified = true;
      submit_order_route_div_is_set = true;
      submit_order_route_div_is_modified = true;
      sell_order_quantity_is_set = true;
      sell_order_quantity_is_modified = true;
      buy_order_quantity_is_set = true;
      buy_order_quantity_is_modified = true;
      limit_price_is_set = true;
      limit_price_is_modified = true;
      execution_condition_is_set = true;
      execution_condition_is_modified = true;
      price_condition_type_is_set = true;
      price_condition_type_is_modified = true;
      sonar_traded_code_is_set = true;
      sonar_traded_code_is_modified = true;
      sonar_repayment_type_is_set = true;
      sonar_repayment_type_is_modified = true;
      sonar_market_code_is_set = true;
      sonar_market_code_is_modified = true;
      ticket_number_is_set = true;
      ticket_number_is_modified = true;
      received_date_time_div_is_set = true;
      received_date_time_div_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      capital_gain_tax_type_is_set = true;
      capital_gain_tax_type_is_modified = true;
      check_type_is_set = true;
      check_type_is_modified = true;
      order_chanel_is_set = true;
      order_chanel_is_modified = true;
      factor_is_set = true;
      factor_is_modified = true;
      commision_number_is_set = true;
      commision_number_is_modified = true;
      commision_branch_number_is_set = true;
      commision_branch_number_is_modified = true;
      commision_product_code_is_set = true;
      commision_product_code_is_modified = true;
      short_sell_order_flag_is_set = true;
      short_sell_order_flag_is_modified = true;
      change_quantity_is_set = true;
      change_quantity_is_modified = true;
      change_limit_price_is_set = true;
      change_limit_price_is_modified = true;
      change_execution_condition_is_set = true;
      change_execution_condition_is_modified = true;
      change_price_condition_type_is_set = true;
      change_price_condition_type_is_modified = true;
      change_order_time_is_set = true;
      change_order_time_is_modified = true;
      change_order_date_div_is_set = true;
      change_order_date_div_is_modified = true;
      cancel_div_is_set = true;
      cancel_div_is_modified = true;
      cancel_order_time_is_set = true;
      cancel_order_time_is_modified = true;
      cancel_order_date_div_is_set = true;
      cancel_order_date_div_is_modified = true;
      front_order_exchange_code_is_set = true;
      front_order_exchange_code_is_modified = true;
      front_order_system_code_is_set = true;
      front_order_system_code_is_modified = true;
      front_order_trade_code_is_set = true;
      front_order_trade_code_is_modified = true;
      tradeaudit_code_is_set = true;
      tradeaudit_code_is_modified = true;
      corp_code_is_set = true;
      corp_code_is_modified = true;
      org_corp_code_is_set = true;
      org_corp_code_is_modified = true;
      virtual_server_number_jsoes_is_set = true;
      virtual_server_number_jsoes_is_modified = true;
      market_order_number_is_set = true;
      market_order_number_is_modified = true;
      amg_send_time_is_set = true;
      amg_send_time_is_modified = true;
      amg_ack_time_is_set = true;
      amg_ack_time_is_modified = true;
      market_ack_time_is_set = true;
      market_ack_time_is_modified = true;
      all_order_change_div_is_set = true;
      all_order_change_div_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      market_close_flag_is_set = true;
      market_close_flag_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostEqtypeOrderAllRow ) )
       return false;
    return fieldsEqual( (HostEqtypeOrderAllRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostEqtypeOrderAllRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostEqtypeOrderAllRow row )
  {
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
        return false;
    }
    if ( account_id == null ) {
      if ( !row.getAccountIdIsNull() )
        return false;
    } else if ( row.getAccountIdIsNull() || ( account_id.longValue() != row.getAccountId() ) ) {
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
    if ( order_action_serial_no == null ) {
      if ( !row.getOrderActionSerialNoIsNull() )
        return false;
    } else if ( row.getOrderActionSerialNoIsNull() || ( order_action_serial_no.intValue() != row.getOrderActionSerialNo() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( received_date_time == null ) {
      if ( row.getReceivedDateTime() != null )
        return false;
    } else if ( !received_date_time.equals( row.getReceivedDateTime() ) ) {
        return false;
    }
    if ( submit_order_route_div == null ) {
      if ( row.getSubmitOrderRouteDiv() != null )
        return false;
    } else if ( !submit_order_route_div.equals( row.getSubmitOrderRouteDiv() ) ) {
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
    if ( execution_condition == null ) {
      if ( row.getExecutionCondition() != null )
        return false;
    } else if ( !execution_condition.equals( row.getExecutionCondition() ) ) {
        return false;
    }
    if ( price_condition_type == null ) {
      if ( row.getPriceConditionType() != null )
        return false;
    } else if ( !price_condition_type.equals( row.getPriceConditionType() ) ) {
        return false;
    }
    if ( sonar_traded_code == null ) {
      if ( row.getSonarTradedCode() != null )
        return false;
    } else if ( !sonar_traded_code.equals( row.getSonarTradedCode() ) ) {
        return false;
    }
    if ( sonar_repayment_type == null ) {
      if ( row.getSonarRepaymentType() != null )
        return false;
    } else if ( !sonar_repayment_type.equals( row.getSonarRepaymentType() ) ) {
        return false;
    }
    if ( sonar_market_code == null ) {
      if ( row.getSonarMarketCode() != null )
        return false;
    } else if ( !sonar_market_code.equals( row.getSonarMarketCode() ) ) {
        return false;
    }
    if ( ticket_number == null ) {
      if ( row.getTicketNumber() != null )
        return false;
    } else if ( !ticket_number.equals( row.getTicketNumber() ) ) {
        return false;
    }
    if ( received_date_time_div == null ) {
      if ( row.getReceivedDateTimeDiv() != null )
        return false;
    } else if ( !received_date_time_div.equals( row.getReceivedDateTimeDiv() ) ) {
        return false;
    }
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( capital_gain_tax_type == null ) {
      if ( row.getCapitalGainTaxType() != null )
        return false;
    } else if ( !capital_gain_tax_type.equals( row.getCapitalGainTaxType() ) ) {
        return false;
    }
    if ( check_type == null ) {
      if ( row.getCheckType() != null )
        return false;
    } else if ( !check_type.equals( row.getCheckType() ) ) {
        return false;
    }
    if ( order_chanel == null ) {
      if ( row.getOrderChanel() != null )
        return false;
    } else if ( !order_chanel.equals( row.getOrderChanel() ) ) {
        return false;
    }
    if ( factor == null ) {
      if ( row.getFactor() != null )
        return false;
    } else if ( !factor.equals( row.getFactor() ) ) {
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
    if ( short_sell_order_flag == null ) {
      if ( row.getShortSellOrderFlag() != null )
        return false;
    } else if ( !short_sell_order_flag.equals( row.getShortSellOrderFlag() ) ) {
        return false;
    }
    if ( change_quantity == null ) {
      if ( !row.getChangeQuantityIsNull() )
        return false;
    } else if ( row.getChangeQuantityIsNull() || ( change_quantity.doubleValue() != row.getChangeQuantity() ) ) {
        return false;
    }
    if ( change_limit_price == null ) {
      if ( !row.getChangeLimitPriceIsNull() )
        return false;
    } else if ( row.getChangeLimitPriceIsNull() || ( change_limit_price.doubleValue() != row.getChangeLimitPrice() ) ) {
        return false;
    }
    if ( change_execution_condition == null ) {
      if ( row.getChangeExecutionCondition() != null )
        return false;
    } else if ( !change_execution_condition.equals( row.getChangeExecutionCondition() ) ) {
        return false;
    }
    if ( change_price_condition_type == null ) {
      if ( row.getChangePriceConditionType() != null )
        return false;
    } else if ( !change_price_condition_type.equals( row.getChangePriceConditionType() ) ) {
        return false;
    }
    if ( change_order_time == null ) {
      if ( row.getChangeOrderTime() != null )
        return false;
    } else if ( !change_order_time.equals( row.getChangeOrderTime() ) ) {
        return false;
    }
    if ( change_order_date_div == null ) {
      if ( row.getChangeOrderDateDiv() != null )
        return false;
    } else if ( !change_order_date_div.equals( row.getChangeOrderDateDiv() ) ) {
        return false;
    }
    if ( cancel_div == null ) {
      if ( row.getCancelDiv() != null )
        return false;
    } else if ( !cancel_div.equals( row.getCancelDiv() ) ) {
        return false;
    }
    if ( cancel_order_time == null ) {
      if ( row.getCancelOrderTime() != null )
        return false;
    } else if ( !cancel_order_time.equals( row.getCancelOrderTime() ) ) {
        return false;
    }
    if ( cancel_order_date_div == null ) {
      if ( row.getCancelOrderDateDiv() != null )
        return false;
    } else if ( !cancel_order_date_div.equals( row.getCancelOrderDateDiv() ) ) {
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
    if ( tradeaudit_code == null ) {
      if ( row.getTradeauditCode() != null )
        return false;
    } else if ( !tradeaudit_code.equals( row.getTradeauditCode() ) ) {
        return false;
    }
    if ( corp_code == null ) {
      if ( row.getCorpCode() != null )
        return false;
    } else if ( !corp_code.equals( row.getCorpCode() ) ) {
        return false;
    }
    if ( org_corp_code == null ) {
      if ( row.getOrgCorpCode() != null )
        return false;
    } else if ( !org_corp_code.equals( row.getOrgCorpCode() ) ) {
        return false;
    }
    if ( virtual_server_number_jsoes == null ) {
      if ( row.getVirtualServerNumberJsoes() != null )
        return false;
    } else if ( !virtual_server_number_jsoes.equals( row.getVirtualServerNumberJsoes() ) ) {
        return false;
    }
    if ( market_order_number == null ) {
      if ( !row.getMarketOrderNumberIsNull() )
        return false;
    } else if ( row.getMarketOrderNumberIsNull() || ( market_order_number.longValue() != row.getMarketOrderNumber() ) ) {
        return false;
    }
    if ( amg_send_time == null ) {
      if ( row.getAmgSendTime() != null )
        return false;
    } else if ( !amg_send_time.equals( row.getAmgSendTime() ) ) {
        return false;
    }
    if ( amg_ack_time == null ) {
      if ( row.getAmgAckTime() != null )
        return false;
    } else if ( !amg_ack_time.equals( row.getAmgAckTime() ) ) {
        return false;
    }
    if ( market_ack_time == null ) {
      if ( row.getMarketAckTime() != null )
        return false;
    } else if ( !market_ack_time.equals( row.getMarketAckTime() ) ) {
        return false;
    }
    if ( all_order_change_div == null ) {
      if ( row.getAllOrderChangeDiv() != null )
        return false;
    } else if ( !all_order_change_div.equals( row.getAllOrderChangeDiv() ) ) {
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
    if ( market_close_flag == null ) {
      if ( row.getMarketCloseFlag() != null )
        return false;
    } else if ( !market_close_flag.equals( row.getMarketCloseFlag() ) ) {
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
        + (account_id!=null? account_id.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (order_action_serial_no!=null? order_action_serial_no.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (received_date_time!=null? received_date_time.hashCode(): 0) 
        + (submit_order_route_div!=null? submit_order_route_div.hashCode(): 0) 
        + (sell_order_quantity!=null? sell_order_quantity.hashCode(): 0) 
        + (buy_order_quantity!=null? buy_order_quantity.hashCode(): 0) 
        + (limit_price!=null? limit_price.hashCode(): 0) 
        + (execution_condition!=null? execution_condition.hashCode(): 0) 
        + (price_condition_type!=null? price_condition_type.hashCode(): 0) 
        + (sonar_traded_code!=null? sonar_traded_code.hashCode(): 0) 
        + (sonar_repayment_type!=null? sonar_repayment_type.hashCode(): 0) 
        + (sonar_market_code!=null? sonar_market_code.hashCode(): 0) 
        + (ticket_number!=null? ticket_number.hashCode(): 0) 
        + (received_date_time_div!=null? received_date_time_div.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (capital_gain_tax_type!=null? capital_gain_tax_type.hashCode(): 0) 
        + (check_type!=null? check_type.hashCode(): 0) 
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (factor!=null? factor.hashCode(): 0) 
        + (commision_number!=null? commision_number.hashCode(): 0) 
        + (commision_branch_number!=null? commision_branch_number.hashCode(): 0) 
        + (commision_product_code!=null? commision_product_code.hashCode(): 0) 
        + (short_sell_order_flag!=null? short_sell_order_flag.hashCode(): 0) 
        + (change_quantity!=null? change_quantity.hashCode(): 0) 
        + (change_limit_price!=null? change_limit_price.hashCode(): 0) 
        + (change_execution_condition!=null? change_execution_condition.hashCode(): 0) 
        + (change_price_condition_type!=null? change_price_condition_type.hashCode(): 0) 
        + (change_order_time!=null? change_order_time.hashCode(): 0) 
        + (change_order_date_div!=null? change_order_date_div.hashCode(): 0) 
        + (cancel_div!=null? cancel_div.hashCode(): 0) 
        + (cancel_order_time!=null? cancel_order_time.hashCode(): 0) 
        + (cancel_order_date_div!=null? cancel_order_date_div.hashCode(): 0) 
        + (front_order_exchange_code!=null? front_order_exchange_code.hashCode(): 0) 
        + (front_order_system_code!=null? front_order_system_code.hashCode(): 0) 
        + (front_order_trade_code!=null? front_order_trade_code.hashCode(): 0) 
        + (tradeaudit_code!=null? tradeaudit_code.hashCode(): 0) 
        + (corp_code!=null? corp_code.hashCode(): 0) 
        + (org_corp_code!=null? org_corp_code.hashCode(): 0) 
        + (virtual_server_number_jsoes!=null? virtual_server_number_jsoes.hashCode(): 0) 
        + (market_order_number!=null? market_order_number.hashCode(): 0) 
        + (amg_send_time!=null? amg_send_time.hashCode(): 0) 
        + (amg_ack_time!=null? amg_ack_time.hashCode(): 0) 
        + (market_ack_time!=null? market_ack_time.hashCode(): 0) 
        + (all_order_change_div!=null? all_order_change_div.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (market_close_flag!=null? market_close_flag.hashCode(): 0) 
        + (rowid!=null? rowid.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !cancel_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'cancel_div' must be set before inserting.");
		if ( !corp_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'corp_code' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		if ( request_code != null )
			map.put("request_code",request_code);
		if ( account_id != null )
			map.put("account_id",account_id);
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( order_request_number != null )
			map.put("order_request_number",order_request_number);
		if ( order_action_serial_no != null )
			map.put("order_action_serial_no",order_action_serial_no);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( received_date_time != null )
			map.put("received_date_time",received_date_time);
		if ( submit_order_route_div != null )
			map.put("submit_order_route_div",submit_order_route_div);
		if ( sell_order_quantity != null )
			map.put("sell_order_quantity",sell_order_quantity);
		if ( buy_order_quantity != null )
			map.put("buy_order_quantity",buy_order_quantity);
		if ( limit_price != null )
			map.put("limit_price",limit_price);
		if ( execution_condition != null )
			map.put("execution_condition",execution_condition);
		if ( price_condition_type != null )
			map.put("price_condition_type",price_condition_type);
		if ( sonar_traded_code != null )
			map.put("sonar_traded_code",sonar_traded_code);
		if ( sonar_repayment_type != null )
			map.put("sonar_repayment_type",sonar_repayment_type);
		if ( sonar_market_code != null )
			map.put("sonar_market_code",sonar_market_code);
		if ( ticket_number != null )
			map.put("ticket_number",ticket_number);
		if ( received_date_time_div != null )
			map.put("received_date_time_div",received_date_time_div);
		if ( tax_type != null )
			map.put("tax_type",tax_type);
		if ( capital_gain_tax_type != null )
			map.put("capital_gain_tax_type",capital_gain_tax_type);
		if ( check_type != null )
			map.put("check_type",check_type);
		if ( order_chanel != null )
			map.put("order_chanel",order_chanel);
		if ( factor != null )
			map.put("factor",factor);
		if ( commision_number != null )
			map.put("commision_number",commision_number);
		if ( commision_branch_number != null )
			map.put("commision_branch_number",commision_branch_number);
		if ( commision_product_code != null )
			map.put("commision_product_code",commision_product_code);
		if ( short_sell_order_flag != null )
			map.put("short_sell_order_flag",short_sell_order_flag);
		if ( change_quantity != null )
			map.put("change_quantity",change_quantity);
		if ( change_limit_price != null )
			map.put("change_limit_price",change_limit_price);
		if ( change_execution_condition != null )
			map.put("change_execution_condition",change_execution_condition);
		if ( change_price_condition_type != null )
			map.put("change_price_condition_type",change_price_condition_type);
		if ( change_order_time != null )
			map.put("change_order_time",change_order_time);
		if ( change_order_date_div != null )
			map.put("change_order_date_div",change_order_date_div);
		map.put("cancel_div",cancel_div);
		if ( cancel_order_time != null )
			map.put("cancel_order_time",cancel_order_time);
		if ( cancel_order_date_div != null )
			map.put("cancel_order_date_div",cancel_order_date_div);
		if ( front_order_exchange_code != null )
			map.put("front_order_exchange_code",front_order_exchange_code);
		if ( front_order_system_code != null )
			map.put("front_order_system_code",front_order_system_code);
		if ( front_order_trade_code != null )
			map.put("front_order_trade_code",front_order_trade_code);
		if ( tradeaudit_code != null )
			map.put("tradeaudit_code",tradeaudit_code);
		map.put("corp_code",corp_code);
		if ( org_corp_code != null )
			map.put("org_corp_code",org_corp_code);
		if ( virtual_server_number_jsoes != null )
			map.put("virtual_server_number_jsoes",virtual_server_number_jsoes);
		if ( market_order_number != null )
			map.put("market_order_number",market_order_number);
		if ( amg_send_time != null )
			map.put("amg_send_time",amg_send_time);
		if ( amg_ack_time != null )
			map.put("amg_ack_time",amg_ack_time);
		if ( market_ack_time != null )
			map.put("market_ack_time",market_ack_time);
		if ( all_order_change_div_is_set )
			map.put("all_order_change_div",all_order_change_div);
		map.put("status",status);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( market_close_flag_is_set )
			map.put("market_close_flag",market_close_flag);
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
		if ( account_id_is_modified )
			map.put("account_id",account_id);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( order_action_serial_no_is_modified )
			map.put("order_action_serial_no",order_action_serial_no);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( received_date_time_is_modified )
			map.put("received_date_time",received_date_time);
		if ( submit_order_route_div_is_modified )
			map.put("submit_order_route_div",submit_order_route_div);
		if ( sell_order_quantity_is_modified )
			map.put("sell_order_quantity",sell_order_quantity);
		if ( buy_order_quantity_is_modified )
			map.put("buy_order_quantity",buy_order_quantity);
		if ( limit_price_is_modified )
			map.put("limit_price",limit_price);
		if ( execution_condition_is_modified )
			map.put("execution_condition",execution_condition);
		if ( price_condition_type_is_modified )
			map.put("price_condition_type",price_condition_type);
		if ( sonar_traded_code_is_modified )
			map.put("sonar_traded_code",sonar_traded_code);
		if ( sonar_repayment_type_is_modified )
			map.put("sonar_repayment_type",sonar_repayment_type);
		if ( sonar_market_code_is_modified )
			map.put("sonar_market_code",sonar_market_code);
		if ( ticket_number_is_modified )
			map.put("ticket_number",ticket_number);
		if ( received_date_time_div_is_modified )
			map.put("received_date_time_div",received_date_time_div);
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( capital_gain_tax_type_is_modified )
			map.put("capital_gain_tax_type",capital_gain_tax_type);
		if ( check_type_is_modified )
			map.put("check_type",check_type);
		if ( order_chanel_is_modified )
			map.put("order_chanel",order_chanel);
		if ( factor_is_modified )
			map.put("factor",factor);
		if ( commision_number_is_modified )
			map.put("commision_number",commision_number);
		if ( commision_branch_number_is_modified )
			map.put("commision_branch_number",commision_branch_number);
		if ( commision_product_code_is_modified )
			map.put("commision_product_code",commision_product_code);
		if ( short_sell_order_flag_is_modified )
			map.put("short_sell_order_flag",short_sell_order_flag);
		if ( change_quantity_is_modified )
			map.put("change_quantity",change_quantity);
		if ( change_limit_price_is_modified )
			map.put("change_limit_price",change_limit_price);
		if ( change_execution_condition_is_modified )
			map.put("change_execution_condition",change_execution_condition);
		if ( change_price_condition_type_is_modified )
			map.put("change_price_condition_type",change_price_condition_type);
		if ( change_order_time_is_modified )
			map.put("change_order_time",change_order_time);
		if ( change_order_date_div_is_modified )
			map.put("change_order_date_div",change_order_date_div);
		if ( cancel_div_is_modified )
			map.put("cancel_div",cancel_div);
		if ( cancel_order_time_is_modified )
			map.put("cancel_order_time",cancel_order_time);
		if ( cancel_order_date_div_is_modified )
			map.put("cancel_order_date_div",cancel_order_date_div);
		if ( front_order_exchange_code_is_modified )
			map.put("front_order_exchange_code",front_order_exchange_code);
		if ( front_order_system_code_is_modified )
			map.put("front_order_system_code",front_order_system_code);
		if ( front_order_trade_code_is_modified )
			map.put("front_order_trade_code",front_order_trade_code);
		if ( tradeaudit_code_is_modified )
			map.put("tradeaudit_code",tradeaudit_code);
		if ( corp_code_is_modified )
			map.put("corp_code",corp_code);
		if ( org_corp_code_is_modified )
			map.put("org_corp_code",org_corp_code);
		if ( virtual_server_number_jsoes_is_modified )
			map.put("virtual_server_number_jsoes",virtual_server_number_jsoes);
		if ( market_order_number_is_modified )
			map.put("market_order_number",market_order_number);
		if ( amg_send_time_is_modified )
			map.put("amg_send_time",amg_send_time);
		if ( amg_ack_time_is_modified )
			map.put("amg_ack_time",amg_ack_time);
		if ( market_ack_time_is_modified )
			map.put("market_ack_time",market_ack_time);
		if ( all_order_change_div_is_modified )
			map.put("all_order_change_div",all_order_change_div);
		if ( status_is_modified )
			map.put("status",status);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( market_close_flag_is_modified )
			map.put("market_close_flag",market_close_flag);
		if (map.size() == 0) {
			map.put("request_code",request_code);
			map.put("account_id",account_id);
			map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("sonar_trader_code",sonar_trader_code);
			map.put("order_request_number",order_request_number);
			map.put("order_action_serial_no",order_action_serial_no);
			map.put("product_code",product_code);
			map.put("received_date_time",received_date_time);
			map.put("submit_order_route_div",submit_order_route_div);
			map.put("sell_order_quantity",sell_order_quantity);
			map.put("buy_order_quantity",buy_order_quantity);
			map.put("limit_price",limit_price);
			map.put("execution_condition",execution_condition);
			map.put("price_condition_type",price_condition_type);
			map.put("sonar_traded_code",sonar_traded_code);
			map.put("sonar_repayment_type",sonar_repayment_type);
			map.put("sonar_market_code",sonar_market_code);
			map.put("ticket_number",ticket_number);
			map.put("received_date_time_div",received_date_time_div);
			map.put("tax_type",tax_type);
			map.put("capital_gain_tax_type",capital_gain_tax_type);
			map.put("check_type",check_type);
			map.put("order_chanel",order_chanel);
			map.put("factor",factor);
			map.put("commision_number",commision_number);
			map.put("commision_branch_number",commision_branch_number);
			map.put("commision_product_code",commision_product_code);
			map.put("short_sell_order_flag",short_sell_order_flag);
			map.put("change_quantity",change_quantity);
			map.put("change_limit_price",change_limit_price);
			map.put("change_execution_condition",change_execution_condition);
			map.put("change_price_condition_type",change_price_condition_type);
			map.put("change_order_time",change_order_time);
			map.put("change_order_date_div",change_order_date_div);
			if ( cancel_div_is_set )
				map.put("cancel_div",cancel_div);
			map.put("cancel_order_time",cancel_order_time);
			map.put("cancel_order_date_div",cancel_order_date_div);
			map.put("front_order_exchange_code",front_order_exchange_code);
			map.put("front_order_system_code",front_order_system_code);
			map.put("front_order_trade_code",front_order_trade_code);
			map.put("tradeaudit_code",tradeaudit_code);
			if ( corp_code_is_set )
				map.put("corp_code",corp_code);
			map.put("org_corp_code",org_corp_code);
			map.put("virtual_server_number_jsoes",virtual_server_number_jsoes);
			map.put("market_order_number",market_order_number);
			map.put("amg_send_time",amg_send_time);
			map.put("amg_ack_time",amg_ack_time);
			map.put("market_ack_time",market_ack_time);
			if ( all_order_change_div_is_set )
				map.put("all_order_change_div",all_order_change_div);
			if ( status_is_set )
				map.put("status",status);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( market_close_flag_is_set )
				map.put("market_close_flag",market_close_flag);
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
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return ( account_id==null? ((long)0): account_id.longValue() );
  }


  /** 
   * <em>account_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountIdIsNull()
  {
    return account_id == null;
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
   * <em>order_action_serial_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOrderActionSerialNo()
  {
    return ( order_action_serial_no==null? ((int)0): order_action_serial_no.intValue() );
  }


  /** 
   * <em>order_action_serial_no</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrderActionSerialNoIsNull()
  {
    return order_action_serial_no == null;
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
   * <em>ticket_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTicketNumber()
  {
    return ticket_number;
  }


  /** 
   * <em>ticket_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTicketNumberIsSet() {
    return ticket_number_is_set;
  }


  /** 
   * <em>ticket_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTicketNumberIsModified() {
    return ticket_number_is_modified;
  }


  /** 
   * <em>received_date_time_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReceivedDateTimeDiv()
  {
    return received_date_time_div;
  }


  /** 
   * <em>received_date_time_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceivedDateTimeDivIsSet() {
    return received_date_time_div_is_set;
  }


  /** 
   * <em>received_date_time_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceivedDateTimeDivIsModified() {
    return received_date_time_div_is_modified;
  }


  /** 
   * <em>tax_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTaxType()
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
   * <em>capital_gain_tax_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCapitalGainTaxType()
  {
    return capital_gain_tax_type;
  }


  /** 
   * <em>capital_gain_tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainTaxTypeIsSet() {
    return capital_gain_tax_type_is_set;
  }


  /** 
   * <em>capital_gain_tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainTaxTypeIsModified() {
    return capital_gain_tax_type_is_modified;
  }


  /** 
   * <em>check_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCheckType()
  {
    return check_type;
  }


  /** 
   * <em>check_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCheckTypeIsSet() {
    return check_type_is_set;
  }


  /** 
   * <em>check_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCheckTypeIsModified() {
    return check_type_is_modified;
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
   * <em>change_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getChangeLimitPrice()
  {
    return ( change_limit_price==null? ((double)0): change_limit_price.doubleValue() );
  }


  /** 
   * <em>change_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getChangeLimitPriceIsNull()
  {
    return change_limit_price == null;
  }


  /** 
   * <em>change_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeLimitPriceIsSet() {
    return change_limit_price_is_set;
  }


  /** 
   * <em>change_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeLimitPriceIsModified() {
    return change_limit_price_is_modified;
  }


  /** 
   * <em>change_execution_condition</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getChangeExecutionCondition()
  {
    return change_execution_condition;
  }


  /** 
   * <em>change_execution_condition</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeExecutionConditionIsSet() {
    return change_execution_condition_is_set;
  }


  /** 
   * <em>change_execution_condition</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeExecutionConditionIsModified() {
    return change_execution_condition_is_modified;
  }


  /** 
   * <em>change_price_condition_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getChangePriceConditionType()
  {
    return change_price_condition_type;
  }


  /** 
   * <em>change_price_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangePriceConditionTypeIsSet() {
    return change_price_condition_type_is_set;
  }


  /** 
   * <em>change_price_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangePriceConditionTypeIsModified() {
    return change_price_condition_type_is_modified;
  }


  /** 
   * <em>change_order_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getChangeOrderTime()
  {
    return change_order_time;
  }


  /** 
   * <em>change_order_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeOrderTimeIsSet() {
    return change_order_time_is_set;
  }


  /** 
   * <em>change_order_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeOrderTimeIsModified() {
    return change_order_time_is_modified;
  }


  /** 
   * <em>change_order_date_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getChangeOrderDateDiv()
  {
    return change_order_date_div;
  }


  /** 
   * <em>change_order_date_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeOrderDateDivIsSet() {
    return change_order_date_div_is_set;
  }


  /** 
   * <em>change_order_date_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeOrderDateDivIsModified() {
    return change_order_date_div_is_modified;
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
   * <em>cancel_order_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCancelOrderTime()
  {
    return cancel_order_time;
  }


  /** 
   * <em>cancel_order_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelOrderTimeIsSet() {
    return cancel_order_time_is_set;
  }


  /** 
   * <em>cancel_order_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelOrderTimeIsModified() {
    return cancel_order_time_is_modified;
  }


  /** 
   * <em>cancel_order_date_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCancelOrderDateDiv()
  {
    return cancel_order_date_div;
  }


  /** 
   * <em>cancel_order_date_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelOrderDateDivIsSet() {
    return cancel_order_date_div_is_set;
  }


  /** 
   * <em>cancel_order_date_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelOrderDateDivIsModified() {
    return cancel_order_date_div_is_modified;
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
   * <em>tradeaudit_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradeauditCode()
  {
    return tradeaudit_code;
  }


  /** 
   * <em>tradeaudit_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeauditCodeIsSet() {
    return tradeaudit_code_is_set;
  }


  /** 
   * <em>tradeaudit_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeauditCodeIsModified() {
    return tradeaudit_code_is_modified;
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
   * <em>virtual_server_number_jsoes</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getVirtualServerNumberJsoes()
  {
    return virtual_server_number_jsoes;
  }


  /** 
   * <em>virtual_server_number_jsoes</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVirtualServerNumberJsoesIsSet() {
    return virtual_server_number_jsoes_is_set;
  }


  /** 
   * <em>virtual_server_number_jsoes</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVirtualServerNumberJsoesIsModified() {
    return virtual_server_number_jsoes_is_modified;
  }


  /** 
   * <em>market_order_number</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarketOrderNumber()
  {
    return ( market_order_number==null? ((long)0): market_order_number.longValue() );
  }


  /** 
   * <em>market_order_number</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarketOrderNumberIsNull()
  {
    return market_order_number == null;
  }


  /** 
   * <em>market_order_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketOrderNumberIsSet() {
    return market_order_number_is_set;
  }


  /** 
   * <em>market_order_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketOrderNumberIsModified() {
    return market_order_number_is_modified;
  }


  /** 
   * <em>amg_send_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAmgSendTime()
  {
    return amg_send_time;
  }


  /** 
   * <em>amg_send_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmgSendTimeIsSet() {
    return amg_send_time_is_set;
  }


  /** 
   * <em>amg_send_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmgSendTimeIsModified() {
    return amg_send_time_is_modified;
  }


  /** 
   * <em>amg_ack_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAmgAckTime()
  {
    return amg_ack_time;
  }


  /** 
   * <em>amg_ack_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmgAckTimeIsSet() {
    return amg_ack_time_is_set;
  }


  /** 
   * <em>amg_ack_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmgAckTimeIsModified() {
    return amg_ack_time_is_modified;
  }


  /** 
   * <em>market_ack_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getMarketAckTime()
  {
    return market_ack_time;
  }


  /** 
   * <em>market_ack_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketAckTimeIsSet() {
    return market_ack_time_is_set;
  }


  /** 
   * <em>market_ack_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketAckTimeIsModified() {
    return market_ack_time_is_modified;
  }


  /** 
   * <em>all_order_change_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAllOrderChangeDiv()
  {
    return all_order_change_div;
  }


  /** 
   * <em>all_order_change_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAllOrderChangeDivIsSet() {
    return all_order_change_div_is_set;
  }


  /** 
   * <em>all_order_change_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAllOrderChangeDivIsModified() {
    return all_order_change_div_is_modified;
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
   * <em>market_close_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarketCloseFlag()
  {
    return market_close_flag;
  }


  /** 
   * <em>market_close_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCloseFlagIsSet() {
    return market_close_flag_is_set;
  }


  /** 
   * <em>market_close_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCloseFlagIsModified() {
    return market_close_flag_is_modified;
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
    return new HostEqtypeOrderAllPK(rowid);
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
   * <em>account_id</em>カラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = new Long(p_accountId);
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_id</em>カラムに値を設定します。 
   */
  public final void setAccountId( Long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
   * <em>order_action_serial_no</em>カラムの値を設定します。 
   *
   * @@param p_orderActionSerialNo <em>order_action_serial_no</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setOrderActionSerialNo( int p_orderActionSerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_action_serial_no = new Integer(p_orderActionSerialNo);
    order_action_serial_no_is_set = true;
    order_action_serial_no_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>order_action_serial_no</em>カラムに値を設定します。 
   */
  public final void setOrderActionSerialNo( Integer p_orderActionSerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_action_serial_no = p_orderActionSerialNo;
    order_action_serial_no_is_set = true;
    order_action_serial_no_is_modified = true;
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
   * <em>ticket_number</em>カラムの値を設定します。 
   *
   * @@param p_ticketNumber <em>ticket_number</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setTicketNumber( String p_ticketNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ticket_number = p_ticketNumber;
    ticket_number_is_set = true;
    ticket_number_is_modified = true;
  }


  /** 
   * <em>received_date_time_div</em>カラムの値を設定します。 
   *
   * @@param p_receivedDateTimeDiv <em>received_date_time_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setReceivedDateTimeDiv( String p_receivedDateTimeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    received_date_time_div = p_receivedDateTimeDiv;
    received_date_time_div_is_set = true;
    received_date_time_div_is_modified = true;
  }


  /** 
   * <em>tax_type</em>カラムの値を設定します。 
   *
   * @@param p_taxType <em>tax_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTaxType( String p_taxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type = p_taxType;
    tax_type_is_set = true;
    tax_type_is_modified = true;
  }


  /** 
   * <em>capital_gain_tax_type</em>カラムの値を設定します。 
   *
   * @@param p_capitalGainTaxType <em>capital_gain_tax_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCapitalGainTaxType( String p_capitalGainTaxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain_tax_type = p_capitalGainTaxType;
    capital_gain_tax_type_is_set = true;
    capital_gain_tax_type_is_modified = true;
  }


  /** 
   * <em>check_type</em>カラムの値を設定します。 
   *
   * @@param p_checkType <em>check_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCheckType( String p_checkType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    check_type = p_checkType;
    check_type_is_set = true;
    check_type_is_modified = true;
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
   * <em>change_limit_price</em>カラムの値を設定します。 
   *
   * @@param p_changeLimitPrice <em>change_limit_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setChangeLimitPrice( double p_changeLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_limit_price = new Double(p_changeLimitPrice);
    change_limit_price_is_set = true;
    change_limit_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>change_limit_price</em>カラムに値を設定します。 
   */
  public final void setChangeLimitPrice( Double p_changeLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    change_limit_price = p_changeLimitPrice;
    change_limit_price_is_set = true;
    change_limit_price_is_modified = true;
  }


  /** 
   * <em>change_execution_condition</em>カラムの値を設定します。 
   *
   * @@param p_changeExecutionCondition <em>change_execution_condition</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setChangeExecutionCondition( String p_changeExecutionCondition )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_execution_condition = p_changeExecutionCondition;
    change_execution_condition_is_set = true;
    change_execution_condition_is_modified = true;
  }


  /** 
   * <em>change_price_condition_type</em>カラムの値を設定します。 
   *
   * @@param p_changePriceConditionType <em>change_price_condition_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setChangePriceConditionType( String p_changePriceConditionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_price_condition_type = p_changePriceConditionType;
    change_price_condition_type_is_set = true;
    change_price_condition_type_is_modified = true;
  }


  /** 
   * <em>change_order_time</em>カラムの値を設定します。 
   *
   * @@param p_changeOrderTime <em>change_order_time</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setChangeOrderTime( String p_changeOrderTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_order_time = p_changeOrderTime;
    change_order_time_is_set = true;
    change_order_time_is_modified = true;
  }


  /** 
   * <em>change_order_date_div</em>カラムの値を設定します。 
   *
   * @@param p_changeOrderDateDiv <em>change_order_date_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setChangeOrderDateDiv( String p_changeOrderDateDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_order_date_div = p_changeOrderDateDiv;
    change_order_date_div_is_set = true;
    change_order_date_div_is_modified = true;
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
   * <em>cancel_order_time</em>カラムの値を設定します。 
   *
   * @@param p_cancelOrderTime <em>cancel_order_time</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setCancelOrderTime( String p_cancelOrderTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_order_time = p_cancelOrderTime;
    cancel_order_time_is_set = true;
    cancel_order_time_is_modified = true;
  }


  /** 
   * <em>cancel_order_date_div</em>カラムの値を設定します。 
   *
   * @@param p_cancelOrderDateDiv <em>cancel_order_date_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCancelOrderDateDiv( String p_cancelOrderDateDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_order_date_div = p_cancelOrderDateDiv;
    cancel_order_date_div_is_set = true;
    cancel_order_date_div_is_modified = true;
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
   * <em>tradeaudit_code</em>カラムの値を設定します。 
   *
   * @@param p_tradeauditCode <em>tradeaudit_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTradeauditCode( String p_tradeauditCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tradeaudit_code = p_tradeauditCode;
    tradeaudit_code_is_set = true;
    tradeaudit_code_is_modified = true;
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
   * <em>virtual_server_number_jsoes</em>カラムの値を設定します。 
   *
   * @@param p_virtualServerNumberJsoes <em>virtual_server_number_jsoes</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setVirtualServerNumberJsoes( String p_virtualServerNumberJsoes )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    virtual_server_number_jsoes = p_virtualServerNumberJsoes;
    virtual_server_number_jsoes_is_set = true;
    virtual_server_number_jsoes_is_modified = true;
  }


  /** 
   * <em>market_order_number</em>カラムの値を設定します。 
   *
   * @@param p_marketOrderNumber <em>market_order_number</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setMarketOrderNumber( long p_marketOrderNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_order_number = new Long(p_marketOrderNumber);
    market_order_number_is_set = true;
    market_order_number_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>market_order_number</em>カラムに値を設定します。 
   */
  public final void setMarketOrderNumber( Long p_marketOrderNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    market_order_number = p_marketOrderNumber;
    market_order_number_is_set = true;
    market_order_number_is_modified = true;
  }


  /** 
   * <em>amg_send_time</em>カラムの値を設定します。 
   *
   * @@param p_amgSendTime <em>amg_send_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAmgSendTime( java.sql.Timestamp p_amgSendTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amg_send_time = p_amgSendTime;
    amg_send_time_is_set = true;
    amg_send_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>amg_send_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAmgSendTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amg_send_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    amg_send_time_is_set = true;
    amg_send_time_is_modified = true;
  }


  /** 
   * <em>amg_ack_time</em>カラムの値を設定します。 
   *
   * @@param p_amgAckTime <em>amg_ack_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAmgAckTime( java.sql.Timestamp p_amgAckTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amg_ack_time = p_amgAckTime;
    amg_ack_time_is_set = true;
    amg_ack_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>amg_ack_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAmgAckTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amg_ack_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    amg_ack_time_is_set = true;
    amg_ack_time_is_modified = true;
  }


  /** 
   * <em>market_ack_time</em>カラムの値を設定します。 
   *
   * @@param p_marketAckTime <em>market_ack_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setMarketAckTime( java.sql.Timestamp p_marketAckTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_ack_time = p_marketAckTime;
    market_ack_time_is_set = true;
    market_ack_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>market_ack_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setMarketAckTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    market_ack_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    market_ack_time_is_set = true;
    market_ack_time_is_modified = true;
  }


  /** 
   * <em>all_order_change_div</em>カラムの値を設定します。 
   *
   * @@param p_allOrderChangeDiv <em>all_order_change_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAllOrderChangeDiv( String p_allOrderChangeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    all_order_change_div = p_allOrderChangeDiv;
    all_order_change_div_is_set = true;
    all_order_change_div_is_modified = true;
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
   * <em>market_close_flag</em>カラムの値を設定します。 
   *
   * @@param p_marketCloseFlag <em>market_close_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarketCloseFlag( String p_marketCloseFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_close_flag = p_marketCloseFlag;
    market_close_flag_is_set = true;
    market_close_flag_is_modified = true;
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
                if ( name.equals("account_id") ) {
                    return this.account_id;
                }
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("amg_send_time") ) {
                    return this.amg_send_time;
                }
                else if ( name.equals("amg_ack_time") ) {
                    return this.amg_ack_time;
                }
                else if ( name.equals("all_order_change_div") ) {
                    return this.all_order_change_div;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("buy_order_quantity") ) {
                    return this.buy_order_quantity;
                }
                break;
            case 'c':
                if ( name.equals("capital_gain_tax_type") ) {
                    return this.capital_gain_tax_type;
                }
                else if ( name.equals("check_type") ) {
                    return this.check_type;
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
                else if ( name.equals("change_limit_price") ) {
                    return this.change_limit_price;
                }
                else if ( name.equals("change_execution_condition") ) {
                    return this.change_execution_condition;
                }
                else if ( name.equals("change_price_condition_type") ) {
                    return this.change_price_condition_type;
                }
                else if ( name.equals("change_order_time") ) {
                    return this.change_order_time;
                }
                else if ( name.equals("change_order_date_div") ) {
                    return this.change_order_date_div;
                }
                else if ( name.equals("cancel_div") ) {
                    return this.cancel_div;
                }
                else if ( name.equals("cancel_order_time") ) {
                    return this.cancel_order_time;
                }
                else if ( name.equals("cancel_order_date_div") ) {
                    return this.cancel_order_date_div;
                }
                else if ( name.equals("corp_code") ) {
                    return this.corp_code;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("execution_condition") ) {
                    return this.execution_condition;
                }
                break;
            case 'f':
                if ( name.equals("factor") ) {
                    return this.factor;
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
                if ( name.equals("market_order_number") ) {
                    return this.market_order_number;
                }
                else if ( name.equals("market_ack_time") ) {
                    return this.market_ack_time;
                }
                else if ( name.equals("market_close_flag") ) {
                    return this.market_close_flag;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_action_serial_no") ) {
                    return this.order_action_serial_no;
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
                else if ( name.equals("price_condition_type") ) {
                    return this.price_condition_type;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("received_date_time") ) {
                    return this.received_date_time;
                }
                else if ( name.equals("received_date_time_div") ) {
                    return this.received_date_time_div;
                }
                else if ( name.equals("rowid") ) {
                    return this.rowid;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                else if ( name.equals("submit_order_route_div") ) {
                    return this.submit_order_route_div;
                }
                else if ( name.equals("sell_order_quantity") ) {
                    return this.sell_order_quantity;
                }
                else if ( name.equals("sonar_traded_code") ) {
                    return this.sonar_traded_code;
                }
                else if ( name.equals("sonar_repayment_type") ) {
                    return this.sonar_repayment_type;
                }
                else if ( name.equals("sonar_market_code") ) {
                    return this.sonar_market_code;
                }
                else if ( name.equals("short_sell_order_flag") ) {
                    return this.short_sell_order_flag;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("ticket_number") ) {
                    return this.ticket_number;
                }
                else if ( name.equals("tax_type") ) {
                    return this.tax_type;
                }
                else if ( name.equals("tradeaudit_code") ) {
                    return this.tradeaudit_code;
                }
                break;
            case 'v':
                if ( name.equals("virtual_server_number_jsoes") ) {
                    return this.virtual_server_number_jsoes;
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
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = (Long) value;
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("amg_send_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'amg_send_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.amg_send_time = (java.sql.Timestamp) value;
                    if (this.amg_send_time_is_set)
                        this.amg_send_time_is_modified = true;
                    this.amg_send_time_is_set = true;
                    return;
                }
                else if ( name.equals("amg_ack_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'amg_ack_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.amg_ack_time = (java.sql.Timestamp) value;
                    if (this.amg_ack_time_is_set)
                        this.amg_ack_time_is_modified = true;
                    this.amg_ack_time_is_set = true;
                    return;
                }
                else if ( name.equals("all_order_change_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'all_order_change_div' must be String: '"+value+"' is not." );
                    this.all_order_change_div = (String) value;
                    if (this.all_order_change_div_is_set)
                        this.all_order_change_div_is_modified = true;
                    this.all_order_change_div_is_set = true;
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
                if ( name.equals("capital_gain_tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'capital_gain_tax_type' must be String: '"+value+"' is not." );
                    this.capital_gain_tax_type = (String) value;
                    if (this.capital_gain_tax_type_is_set)
                        this.capital_gain_tax_type_is_modified = true;
                    this.capital_gain_tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("check_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'check_type' must be String: '"+value+"' is not." );
                    this.check_type = (String) value;
                    if (this.check_type_is_set)
                        this.check_type_is_modified = true;
                    this.check_type_is_set = true;
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
                else if ( name.equals("change_limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'change_limit_price' must be Double: '"+value+"' is not." );
                    this.change_limit_price = (Double) value;
                    if (this.change_limit_price_is_set)
                        this.change_limit_price_is_modified = true;
                    this.change_limit_price_is_set = true;
                    return;
                }
                else if ( name.equals("change_execution_condition") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'change_execution_condition' must be String: '"+value+"' is not." );
                    this.change_execution_condition = (String) value;
                    if (this.change_execution_condition_is_set)
                        this.change_execution_condition_is_modified = true;
                    this.change_execution_condition_is_set = true;
                    return;
                }
                else if ( name.equals("change_price_condition_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'change_price_condition_type' must be String: '"+value+"' is not." );
                    this.change_price_condition_type = (String) value;
                    if (this.change_price_condition_type_is_set)
                        this.change_price_condition_type_is_modified = true;
                    this.change_price_condition_type_is_set = true;
                    return;
                }
                else if ( name.equals("change_order_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'change_order_time' must be String: '"+value+"' is not." );
                    this.change_order_time = (String) value;
                    if (this.change_order_time_is_set)
                        this.change_order_time_is_modified = true;
                    this.change_order_time_is_set = true;
                    return;
                }
                else if ( name.equals("change_order_date_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'change_order_date_div' must be String: '"+value+"' is not." );
                    this.change_order_date_div = (String) value;
                    if (this.change_order_date_div_is_set)
                        this.change_order_date_div_is_modified = true;
                    this.change_order_date_div_is_set = true;
                    return;
                }
                else if ( name.equals("cancel_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cancel_div' must be String: '"+value+"' is not." );
                    this.cancel_div = (String) value;
                    if (this.cancel_div_is_set)
                        this.cancel_div_is_modified = true;
                    this.cancel_div_is_set = true;
                    return;
                }
                else if ( name.equals("cancel_order_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cancel_order_time' must be String: '"+value+"' is not." );
                    this.cancel_order_time = (String) value;
                    if (this.cancel_order_time_is_set)
                        this.cancel_order_time_is_modified = true;
                    this.cancel_order_time_is_set = true;
                    return;
                }
                else if ( name.equals("cancel_order_date_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cancel_order_date_div' must be String: '"+value+"' is not." );
                    this.cancel_order_date_div = (String) value;
                    if (this.cancel_order_date_div_is_set)
                        this.cancel_order_date_div_is_modified = true;
                    this.cancel_order_date_div_is_set = true;
                    return;
                }
                else if ( name.equals("corp_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'corp_code' must be String: '"+value+"' is not." );
                    this.corp_code = (String) value;
                    if (this.corp_code_is_set)
                        this.corp_code_is_modified = true;
                    this.corp_code_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
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
                break;
            case 'f':
                if ( name.equals("factor") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'factor' must be String: '"+value+"' is not." );
                    this.factor = (String) value;
                    if (this.factor_is_set)
                        this.factor_is_modified = true;
                    this.factor_is_set = true;
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
                    if ( value != null )
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
                if ( name.equals("market_order_number") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_order_number' must be Long: '"+value+"' is not." );
                    this.market_order_number = (Long) value;
                    if (this.market_order_number_is_set)
                        this.market_order_number_is_modified = true;
                    this.market_order_number_is_set = true;
                    return;
                }
                else if ( name.equals("market_ack_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'market_ack_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.market_ack_time = (java.sql.Timestamp) value;
                    if (this.market_ack_time_is_set)
                        this.market_ack_time_is_modified = true;
                    this.market_ack_time_is_set = true;
                    return;
                }
                else if ( name.equals("market_close_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_close_flag' must be String: '"+value+"' is not." );
                    this.market_close_flag = (String) value;
                    if (this.market_close_flag_is_set)
                        this.market_close_flag_is_modified = true;
                    this.market_close_flag_is_set = true;
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
                else if ( name.equals("order_action_serial_no") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'order_action_serial_no' must be Integer: '"+value+"' is not." );
                    this.order_action_serial_no = (Integer) value;
                    if (this.order_action_serial_no_is_set)
                        this.order_action_serial_no_is_modified = true;
                    this.order_action_serial_no_is_set = true;
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
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'received_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.received_date_time = (java.sql.Timestamp) value;
                    if (this.received_date_time_is_set)
                        this.received_date_time_is_modified = true;
                    this.received_date_time_is_set = true;
                    return;
                }
                else if ( name.equals("received_date_time_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'received_date_time_div' must be String: '"+value+"' is not." );
                    this.received_date_time_div = (String) value;
                    if (this.received_date_time_div_is_set)
                        this.received_date_time_div_is_modified = true;
                    this.received_date_time_div_is_set = true;
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
                if ( name.equals("sonar_trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_trader_code' must be String: '"+value+"' is not." );
                    this.sonar_trader_code = (String) value;
                    if (this.sonar_trader_code_is_set)
                        this.sonar_trader_code_is_modified = true;
                    this.sonar_trader_code_is_set = true;
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
                if ( name.equals("ticket_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ticket_number' must be String: '"+value+"' is not." );
                    this.ticket_number = (String) value;
                    if (this.ticket_number_is_set)
                        this.ticket_number_is_modified = true;
                    this.ticket_number_is_set = true;
                    return;
                }
                else if ( name.equals("tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be String: '"+value+"' is not." );
                    this.tax_type = (String) value;
                    if (this.tax_type_is_set)
                        this.tax_type_is_modified = true;
                    this.tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("tradeaudit_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tradeaudit_code' must be String: '"+value+"' is not." );
                    this.tradeaudit_code = (String) value;
                    if (this.tradeaudit_code_is_set)
                        this.tradeaudit_code_is_modified = true;
                    this.tradeaudit_code_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("virtual_server_number_jsoes") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'virtual_server_number_jsoes' must be String: '"+value+"' is not." );
                    this.virtual_server_number_jsoes = (String) value;
                    if (this.virtual_server_number_jsoes_is_set)
                        this.virtual_server_number_jsoes_is_modified = true;
                    this.virtual_server_number_jsoes_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
