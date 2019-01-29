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
filename	MarketNoticeManagementParams.java;


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
 * market_notice_managementテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link MarketNoticeManagementRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link MarketNoticeManagementParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link MarketNoticeManagementParams}が{@@link MarketNoticeManagementRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MarketNoticeManagementPK 
 * @@see MarketNoticeManagementRow 
 */
public class MarketNoticeManagementParams extends Params implements MarketNoticeManagementRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "market_notice_management";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = MarketNoticeManagementRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return MarketNoticeManagementRow.TYPE;
  }


  /** 
   * <em>virtual_server_number_market</em>カラムの値 
   */
  public  String  virtual_server_number_market;

  /** 
   * <em>notice_type</em>カラムの値 
   */
  public  String  notice_type;

  /** 
   * <em>notice_number</em>カラムの値 
   */
  public  long  notice_number;

  /** 
   * <em>front_order_exchange_code</em>カラムの値 
   */
  public  String  front_order_exchange_code;

  /** 
   * <em>front_order_system_code</em>カラムの値 
   */
  public  String  front_order_system_code;

  /** 
   * <em>biz_date_count</em>カラムの値 
   */
  public  int  biz_date_count;

  /** 
   * <em>client_number</em>カラムの値 
   */
  public  String  client_number;

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
   * <em>front_order_trade_code</em>カラムの値 
   */
  public  String  front_order_trade_code;

  /** 
   * <em>data_class_code</em>カラムの値 
   */
  public  String  data_class_code;

  /** 
   * <em>data_class_detail_code</em>カラムの値 
   */
  public  String  data_class_detail_code;

  /** 
   * <em>corp_code</em>カラムの値 
   */
  public  String  corp_code;

  /** 
   * <em>resend_flg</em>カラムの値 
   */
  public  String  resend_flg;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>error_code</em>カラムの値 
   */
  public  String  error_code;

  /** 
   * <em>accept_number</em>カラムの値 
   */
  public  String  accept_number;

  /** 
   * <em>market_time</em>カラムの値 
   */
  public  Integer  market_time;

  /** 
   * <em>buy_sell_div</em>カラムの値 
   */
  public  String  buy_sell_div;

  /** 
   * <em>execution_condition</em>カラムの値 
   */
  public  String  execution_condition;

  /** 
   * <em>price_condition_type</em>カラムの値 
   */
  public  String  price_condition_type;

  /** 
   * <em>order_quantity</em>カラムの値 
   */
  public  Double  order_quantity;

  /** 
   * <em>limit_price</em>カラムの値 
   */
  public  Double  limit_price;

  /** 
   * <em>tradeaudit_code</em>カラムの値 
   */
  public  String  tradeaudit_code;

  /** 
   * <em>margin_code</em>カラムの値 
   */
  public  String  margin_code;

  /** 
   * <em>short_sell_order_flag</em>カラムの値 
   */
  public  String  short_sell_order_flag;

  /** 
   * <em>org_limit_price</em>カラムの値 
   */
  public  Double  org_limit_price;

  /** 
   * <em>org_corp_code</em>カラムの値 
   */
  public  String  org_corp_code;

  /** 
   * <em>cut_quantity</em>カラムの値 
   */
  public  Long  cut_quantity;

  /** 
   * <em>exec_price</em>カラムの値 
   */
  public  Double  exec_price;

  /** 
   * <em>exec_quantity</em>カラムの値 
   */
  public  Double  exec_quantity;

  /** 
   * <em>left_quantity</em>カラムの値 
   */
  public  Double  left_quantity;

  /** 
   * <em>price_mark</em>カラムの値 
   */
  public  String  price_mark;

  /** 
   * <em>exec_mark</em>カラムの値 
   */
  public  String  exec_mark;

  /** 
   * <em>exec_number</em>カラムの値 
   */
  public  Integer  exec_number;

  /** 
   * <em>modified_result</em>カラムの値 
   */
  public  String  modified_result;

  /** 
   * <em>org_execution_condition</em>カラムの値 
   */
  public  String  org_execution_condition;

  /** 
   * <em>org_price_condition_type</em>カラムの値 
   */
  public  String  org_price_condition_type;

  /** 
   * <em>org_order_quantity</em>カラムの値 
   */
  public  Double  org_order_quantity;

  /** 
   * <em>canceled_quantity</em>カラムの値 
   */
  public  Long  canceled_quantity;

  /** 
   * <em>executed_quantity</em>カラムの値 
   */
  public  Long  executed_quantity;

  /** 
   * <em>reason_code</em>カラムの値 
   */
  public  String  reason_code;

  /** 
   * <em>stop_mark</em>カラムの値 
   */
  public  String  stop_mark;

  /** 
   * <em>expiration_quantity</em>カラムの値 
   */
  public  Double  expiration_quantity;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>submit_order_route_div</em>カラムの値 
   */
  public  String  submit_order_route_div;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>stop_order_price</em>カラムの値 
   */
  public  Double  stop_order_price;

  /** 
   * <em>w_limit_price</em>カラムの値 
   */
  public  Double  w_limit_price;

  /** 
   * <em>org_stop_order_price</em>カラムの値 
   */
  public  Double  org_stop_order_price;

  /** 
   * <em>org_w_limit_price</em>カラムの値 
   */
  public  Double  org_w_limit_price;

  private boolean virtual_server_number_market_is_set = false;
  private boolean virtual_server_number_market_is_modified = false;
  private boolean notice_type_is_set = false;
  private boolean notice_type_is_modified = false;
  private boolean notice_number_is_set = false;
  private boolean notice_number_is_modified = false;
  private boolean client_number_is_set = false;
  private boolean client_number_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean front_order_exchange_code_is_set = false;
  private boolean front_order_exchange_code_is_modified = false;
  private boolean front_order_system_code_is_set = false;
  private boolean front_order_system_code_is_modified = false;
  private boolean front_order_trade_code_is_set = false;
  private boolean front_order_trade_code_is_modified = false;
  private boolean data_class_code_is_set = false;
  private boolean data_class_code_is_modified = false;
  private boolean data_class_detail_code_is_set = false;
  private boolean data_class_detail_code_is_modified = false;
  private boolean corp_code_is_set = false;
  private boolean corp_code_is_modified = false;
  private boolean resend_flg_is_set = false;
  private boolean resend_flg_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean error_code_is_set = false;
  private boolean error_code_is_modified = false;
  private boolean accept_number_is_set = false;
  private boolean accept_number_is_modified = false;
  private boolean market_time_is_set = false;
  private boolean market_time_is_modified = false;
  private boolean buy_sell_div_is_set = false;
  private boolean buy_sell_div_is_modified = false;
  private boolean execution_condition_is_set = false;
  private boolean execution_condition_is_modified = false;
  private boolean price_condition_type_is_set = false;
  private boolean price_condition_type_is_modified = false;
  private boolean order_quantity_is_set = false;
  private boolean order_quantity_is_modified = false;
  private boolean limit_price_is_set = false;
  private boolean limit_price_is_modified = false;
  private boolean tradeaudit_code_is_set = false;
  private boolean tradeaudit_code_is_modified = false;
  private boolean margin_code_is_set = false;
  private boolean margin_code_is_modified = false;
  private boolean short_sell_order_flag_is_set = false;
  private boolean short_sell_order_flag_is_modified = false;
  private boolean org_limit_price_is_set = false;
  private boolean org_limit_price_is_modified = false;
  private boolean org_corp_code_is_set = false;
  private boolean org_corp_code_is_modified = false;
  private boolean cut_quantity_is_set = false;
  private boolean cut_quantity_is_modified = false;
  private boolean exec_price_is_set = false;
  private boolean exec_price_is_modified = false;
  private boolean exec_quantity_is_set = false;
  private boolean exec_quantity_is_modified = false;
  private boolean left_quantity_is_set = false;
  private boolean left_quantity_is_modified = false;
  private boolean price_mark_is_set = false;
  private boolean price_mark_is_modified = false;
  private boolean exec_mark_is_set = false;
  private boolean exec_mark_is_modified = false;
  private boolean exec_number_is_set = false;
  private boolean exec_number_is_modified = false;
  private boolean modified_result_is_set = false;
  private boolean modified_result_is_modified = false;
  private boolean org_execution_condition_is_set = false;
  private boolean org_execution_condition_is_modified = false;
  private boolean org_price_condition_type_is_set = false;
  private boolean org_price_condition_type_is_modified = false;
  private boolean org_order_quantity_is_set = false;
  private boolean org_order_quantity_is_modified = false;
  private boolean canceled_quantity_is_set = false;
  private boolean canceled_quantity_is_modified = false;
  private boolean executed_quantity_is_set = false;
  private boolean executed_quantity_is_modified = false;
  private boolean reason_code_is_set = false;
  private boolean reason_code_is_modified = false;
  private boolean stop_mark_is_set = false;
  private boolean stop_mark_is_modified = false;
  private boolean expiration_quantity_is_set = false;
  private boolean expiration_quantity_is_modified = false;
  private boolean biz_date_count_is_set = false;
  private boolean biz_date_count_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean submit_order_route_div_is_set = false;
  private boolean submit_order_route_div_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean stop_order_price_is_set = false;
  private boolean stop_order_price_is_modified = false;
  private boolean w_limit_price_is_set = false;
  private boolean w_limit_price_is_modified = false;
  private boolean org_stop_order_price_is_set = false;
  private boolean org_stop_order_price_is_modified = false;
  private boolean org_w_limit_price_is_set = false;
  private boolean org_w_limit_price_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "virtual_server_number_market=" + virtual_server_number_market
      + "," + "notice_type=" + notice_type
      + "," + "notice_number=" + notice_number
      + "," + "front_order_exchange_code=" + front_order_exchange_code
      + "," + "front_order_system_code=" + front_order_system_code
      + "," + "biz_date_count=" + biz_date_count
      + "," + "client_number=" +client_number
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "front_order_trade_code=" +front_order_trade_code
      + "," + "data_class_code=" +data_class_code
      + "," + "data_class_detail_code=" +data_class_detail_code
      + "," + "corp_code=" +corp_code
      + "," + "resend_flg=" +resend_flg
      + "," + "product_code=" +product_code
      + "," + "error_code=" +error_code
      + "," + "accept_number=" +accept_number
      + "," + "market_time=" +market_time
      + "," + "buy_sell_div=" +buy_sell_div
      + "," + "execution_condition=" +execution_condition
      + "," + "price_condition_type=" +price_condition_type
      + "," + "order_quantity=" +order_quantity
      + "," + "limit_price=" +limit_price
      + "," + "tradeaudit_code=" +tradeaudit_code
      + "," + "margin_code=" +margin_code
      + "," + "short_sell_order_flag=" +short_sell_order_flag
      + "," + "org_limit_price=" +org_limit_price
      + "," + "org_corp_code=" +org_corp_code
      + "," + "cut_quantity=" +cut_quantity
      + "," + "exec_price=" +exec_price
      + "," + "exec_quantity=" +exec_quantity
      + "," + "left_quantity=" +left_quantity
      + "," + "price_mark=" +price_mark
      + "," + "exec_mark=" +exec_mark
      + "," + "exec_number=" +exec_number
      + "," + "modified_result=" +modified_result
      + "," + "org_execution_condition=" +org_execution_condition
      + "," + "org_price_condition_type=" +org_price_condition_type
      + "," + "org_order_quantity=" +org_order_quantity
      + "," + "canceled_quantity=" +canceled_quantity
      + "," + "executed_quantity=" +executed_quantity
      + "," + "reason_code=" +reason_code
      + "," + "stop_mark=" +stop_mark
      + "," + "expiration_quantity=" +expiration_quantity
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "submit_order_route_div=" +submit_order_route_div
      + "," + "product_type=" +product_type
      + "," + "stop_order_price=" +stop_order_price
      + "," + "w_limit_price=" +w_limit_price
      + "," + "org_stop_order_price=" +org_stop_order_price
      + "," + "org_w_limit_price=" +org_w_limit_price
      + "}";
  }


  /** 
   * 値が未設定のMarketNoticeManagementParamsオブジェクトを作成します。 
   */
  public MarketNoticeManagementParams() {
    virtual_server_number_market_is_modified = true;
    notice_type_is_modified = true;
    notice_number_is_modified = true;
    front_order_exchange_code_is_modified = true;
    front_order_system_code_is_modified = true;
    biz_date_count_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のMarketNoticeManagementRowオブジェクトの値を利用してMarketNoticeManagementParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するMarketNoticeManagementRowオブジェクト 
   */
  public MarketNoticeManagementParams( MarketNoticeManagementRow p_row )
  {
    virtual_server_number_market = p_row.getVirtualServerNumberMarket();
    virtual_server_number_market_is_set = p_row.getVirtualServerNumberMarketIsSet();
    virtual_server_number_market_is_modified = p_row.getVirtualServerNumberMarketIsModified();
    notice_type = p_row.getNoticeType();
    notice_type_is_set = p_row.getNoticeTypeIsSet();
    notice_type_is_modified = p_row.getNoticeTypeIsModified();
    notice_number = p_row.getNoticeNumber();
    notice_number_is_set = p_row.getNoticeNumberIsSet();
    notice_number_is_modified = p_row.getNoticeNumberIsModified();
    front_order_exchange_code = p_row.getFrontOrderExchangeCode();
    front_order_exchange_code_is_set = p_row.getFrontOrderExchangeCodeIsSet();
    front_order_exchange_code_is_modified = p_row.getFrontOrderExchangeCodeIsModified();
    front_order_system_code = p_row.getFrontOrderSystemCode();
    front_order_system_code_is_set = p_row.getFrontOrderSystemCodeIsSet();
    front_order_system_code_is_modified = p_row.getFrontOrderSystemCodeIsModified();
    biz_date_count = p_row.getBizDateCount();
    biz_date_count_is_set = p_row.getBizDateCountIsSet();
    biz_date_count_is_modified = p_row.getBizDateCountIsModified();
    client_number = p_row.getClientNumber();
    client_number_is_set = p_row.getClientNumberIsSet();
    client_number_is_modified = p_row.getClientNumberIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    front_order_trade_code = p_row.getFrontOrderTradeCode();
    front_order_trade_code_is_set = p_row.getFrontOrderTradeCodeIsSet();
    front_order_trade_code_is_modified = p_row.getFrontOrderTradeCodeIsModified();
    data_class_code = p_row.getDataClassCode();
    data_class_code_is_set = p_row.getDataClassCodeIsSet();
    data_class_code_is_modified = p_row.getDataClassCodeIsModified();
    data_class_detail_code = p_row.getDataClassDetailCode();
    data_class_detail_code_is_set = p_row.getDataClassDetailCodeIsSet();
    data_class_detail_code_is_modified = p_row.getDataClassDetailCodeIsModified();
    corp_code = p_row.getCorpCode();
    corp_code_is_set = p_row.getCorpCodeIsSet();
    corp_code_is_modified = p_row.getCorpCodeIsModified();
    resend_flg = p_row.getResendFlg();
    resend_flg_is_set = p_row.getResendFlgIsSet();
    resend_flg_is_modified = p_row.getResendFlgIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    error_code = p_row.getErrorCode();
    error_code_is_set = p_row.getErrorCodeIsSet();
    error_code_is_modified = p_row.getErrorCodeIsModified();
    accept_number = p_row.getAcceptNumber();
    accept_number_is_set = p_row.getAcceptNumberIsSet();
    accept_number_is_modified = p_row.getAcceptNumberIsModified();
    if ( !p_row.getMarketTimeIsNull() )
      market_time = new Integer( p_row.getMarketTime() );
    market_time_is_set = p_row.getMarketTimeIsSet();
    market_time_is_modified = p_row.getMarketTimeIsModified();
    buy_sell_div = p_row.getBuySellDiv();
    buy_sell_div_is_set = p_row.getBuySellDivIsSet();
    buy_sell_div_is_modified = p_row.getBuySellDivIsModified();
    execution_condition = p_row.getExecutionCondition();
    execution_condition_is_set = p_row.getExecutionConditionIsSet();
    execution_condition_is_modified = p_row.getExecutionConditionIsModified();
    price_condition_type = p_row.getPriceConditionType();
    price_condition_type_is_set = p_row.getPriceConditionTypeIsSet();
    price_condition_type_is_modified = p_row.getPriceConditionTypeIsModified();
    if ( !p_row.getOrderQuantityIsNull() )
      order_quantity = new Double( p_row.getOrderQuantity() );
    order_quantity_is_set = p_row.getOrderQuantityIsSet();
    order_quantity_is_modified = p_row.getOrderQuantityIsModified();
    if ( !p_row.getLimitPriceIsNull() )
      limit_price = new Double( p_row.getLimitPrice() );
    limit_price_is_set = p_row.getLimitPriceIsSet();
    limit_price_is_modified = p_row.getLimitPriceIsModified();
    tradeaudit_code = p_row.getTradeauditCode();
    tradeaudit_code_is_set = p_row.getTradeauditCodeIsSet();
    tradeaudit_code_is_modified = p_row.getTradeauditCodeIsModified();
    margin_code = p_row.getMarginCode();
    margin_code_is_set = p_row.getMarginCodeIsSet();
    margin_code_is_modified = p_row.getMarginCodeIsModified();
    short_sell_order_flag = p_row.getShortSellOrderFlag();
    short_sell_order_flag_is_set = p_row.getShortSellOrderFlagIsSet();
    short_sell_order_flag_is_modified = p_row.getShortSellOrderFlagIsModified();
    if ( !p_row.getOrgLimitPriceIsNull() )
      org_limit_price = new Double( p_row.getOrgLimitPrice() );
    org_limit_price_is_set = p_row.getOrgLimitPriceIsSet();
    org_limit_price_is_modified = p_row.getOrgLimitPriceIsModified();
    org_corp_code = p_row.getOrgCorpCode();
    org_corp_code_is_set = p_row.getOrgCorpCodeIsSet();
    org_corp_code_is_modified = p_row.getOrgCorpCodeIsModified();
    if ( !p_row.getCutQuantityIsNull() )
      cut_quantity = new Long( p_row.getCutQuantity() );
    cut_quantity_is_set = p_row.getCutQuantityIsSet();
    cut_quantity_is_modified = p_row.getCutQuantityIsModified();
    if ( !p_row.getExecPriceIsNull() )
      exec_price = new Double( p_row.getExecPrice() );
    exec_price_is_set = p_row.getExecPriceIsSet();
    exec_price_is_modified = p_row.getExecPriceIsModified();
    if ( !p_row.getExecQuantityIsNull() )
      exec_quantity = new Double( p_row.getExecQuantity() );
    exec_quantity_is_set = p_row.getExecQuantityIsSet();
    exec_quantity_is_modified = p_row.getExecQuantityIsModified();
    if ( !p_row.getLeftQuantityIsNull() )
      left_quantity = new Double( p_row.getLeftQuantity() );
    left_quantity_is_set = p_row.getLeftQuantityIsSet();
    left_quantity_is_modified = p_row.getLeftQuantityIsModified();
    price_mark = p_row.getPriceMark();
    price_mark_is_set = p_row.getPriceMarkIsSet();
    price_mark_is_modified = p_row.getPriceMarkIsModified();
    exec_mark = p_row.getExecMark();
    exec_mark_is_set = p_row.getExecMarkIsSet();
    exec_mark_is_modified = p_row.getExecMarkIsModified();
    if ( !p_row.getExecNumberIsNull() )
      exec_number = new Integer( p_row.getExecNumber() );
    exec_number_is_set = p_row.getExecNumberIsSet();
    exec_number_is_modified = p_row.getExecNumberIsModified();
    modified_result = p_row.getModifiedResult();
    modified_result_is_set = p_row.getModifiedResultIsSet();
    modified_result_is_modified = p_row.getModifiedResultIsModified();
    org_execution_condition = p_row.getOrgExecutionCondition();
    org_execution_condition_is_set = p_row.getOrgExecutionConditionIsSet();
    org_execution_condition_is_modified = p_row.getOrgExecutionConditionIsModified();
    org_price_condition_type = p_row.getOrgPriceConditionType();
    org_price_condition_type_is_set = p_row.getOrgPriceConditionTypeIsSet();
    org_price_condition_type_is_modified = p_row.getOrgPriceConditionTypeIsModified();
    if ( !p_row.getOrgOrderQuantityIsNull() )
      org_order_quantity = new Double( p_row.getOrgOrderQuantity() );
    org_order_quantity_is_set = p_row.getOrgOrderQuantityIsSet();
    org_order_quantity_is_modified = p_row.getOrgOrderQuantityIsModified();
    if ( !p_row.getCanceledQuantityIsNull() )
      canceled_quantity = new Long( p_row.getCanceledQuantity() );
    canceled_quantity_is_set = p_row.getCanceledQuantityIsSet();
    canceled_quantity_is_modified = p_row.getCanceledQuantityIsModified();
    if ( !p_row.getExecutedQuantityIsNull() )
      executed_quantity = new Long( p_row.getExecutedQuantity() );
    executed_quantity_is_set = p_row.getExecutedQuantityIsSet();
    executed_quantity_is_modified = p_row.getExecutedQuantityIsModified();
    reason_code = p_row.getReasonCode();
    reason_code_is_set = p_row.getReasonCodeIsSet();
    reason_code_is_modified = p_row.getReasonCodeIsModified();
    stop_mark = p_row.getStopMark();
    stop_mark_is_set = p_row.getStopMarkIsSet();
    stop_mark_is_modified = p_row.getStopMarkIsModified();
    if ( !p_row.getExpirationQuantityIsNull() )
      expiration_quantity = new Double( p_row.getExpirationQuantity() );
    expiration_quantity_is_set = p_row.getExpirationQuantityIsSet();
    expiration_quantity_is_modified = p_row.getExpirationQuantityIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    submit_order_route_div = p_row.getSubmitOrderRouteDiv();
    submit_order_route_div_is_set = p_row.getSubmitOrderRouteDivIsSet();
    submit_order_route_div_is_modified = p_row.getSubmitOrderRouteDivIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    if ( !p_row.getStopOrderPriceIsNull() )
      stop_order_price = new Double( p_row.getStopOrderPrice() );
    stop_order_price_is_set = p_row.getStopOrderPriceIsSet();
    stop_order_price_is_modified = p_row.getStopOrderPriceIsModified();
    if ( !p_row.getWLimitPriceIsNull() )
      w_limit_price = new Double( p_row.getWLimitPrice() );
    w_limit_price_is_set = p_row.getWLimitPriceIsSet();
    w_limit_price_is_modified = p_row.getWLimitPriceIsModified();
    if ( !p_row.getOrgStopOrderPriceIsNull() )
      org_stop_order_price = new Double( p_row.getOrgStopOrderPrice() );
    org_stop_order_price_is_set = p_row.getOrgStopOrderPriceIsSet();
    org_stop_order_price_is_modified = p_row.getOrgStopOrderPriceIsModified();
    if ( !p_row.getOrgWLimitPriceIsNull() )
      org_w_limit_price = new Double( p_row.getOrgWLimitPrice() );
    org_w_limit_price_is_set = p_row.getOrgWLimitPriceIsSet();
    org_w_limit_price_is_modified = p_row.getOrgWLimitPriceIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      client_number_is_set = true;
      client_number_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      front_order_trade_code_is_set = true;
      front_order_trade_code_is_modified = true;
      data_class_code_is_set = true;
      data_class_code_is_modified = true;
      data_class_detail_code_is_set = true;
      data_class_detail_code_is_modified = true;
      corp_code_is_set = true;
      corp_code_is_modified = true;
      resend_flg_is_set = true;
      resend_flg_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      error_code_is_set = true;
      error_code_is_modified = true;
      accept_number_is_set = true;
      accept_number_is_modified = true;
      market_time_is_set = true;
      market_time_is_modified = true;
      buy_sell_div_is_set = true;
      buy_sell_div_is_modified = true;
      execution_condition_is_set = true;
      execution_condition_is_modified = true;
      price_condition_type_is_set = true;
      price_condition_type_is_modified = true;
      order_quantity_is_set = true;
      order_quantity_is_modified = true;
      limit_price_is_set = true;
      limit_price_is_modified = true;
      tradeaudit_code_is_set = true;
      tradeaudit_code_is_modified = true;
      margin_code_is_set = true;
      margin_code_is_modified = true;
      short_sell_order_flag_is_set = true;
      short_sell_order_flag_is_modified = true;
      org_limit_price_is_set = true;
      org_limit_price_is_modified = true;
      org_corp_code_is_set = true;
      org_corp_code_is_modified = true;
      cut_quantity_is_set = true;
      cut_quantity_is_modified = true;
      exec_price_is_set = true;
      exec_price_is_modified = true;
      exec_quantity_is_set = true;
      exec_quantity_is_modified = true;
      left_quantity_is_set = true;
      left_quantity_is_modified = true;
      price_mark_is_set = true;
      price_mark_is_modified = true;
      exec_mark_is_set = true;
      exec_mark_is_modified = true;
      exec_number_is_set = true;
      exec_number_is_modified = true;
      modified_result_is_set = true;
      modified_result_is_modified = true;
      org_execution_condition_is_set = true;
      org_execution_condition_is_modified = true;
      org_price_condition_type_is_set = true;
      org_price_condition_type_is_modified = true;
      org_order_quantity_is_set = true;
      org_order_quantity_is_modified = true;
      canceled_quantity_is_set = true;
      canceled_quantity_is_modified = true;
      executed_quantity_is_set = true;
      executed_quantity_is_modified = true;
      reason_code_is_set = true;
      reason_code_is_modified = true;
      stop_mark_is_set = true;
      stop_mark_is_modified = true;
      expiration_quantity_is_set = true;
      expiration_quantity_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      submit_order_route_div_is_set = true;
      submit_order_route_div_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      stop_order_price_is_set = true;
      stop_order_price_is_modified = true;
      w_limit_price_is_set = true;
      w_limit_price_is_modified = true;
      org_stop_order_price_is_set = true;
      org_stop_order_price_is_modified = true;
      org_w_limit_price_is_set = true;
      org_w_limit_price_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof MarketNoticeManagementRow ) )
       return false;
    return fieldsEqual( (MarketNoticeManagementRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のMarketNoticeManagementRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( MarketNoticeManagementRow row )
  {
    if ( virtual_server_number_market == null ) {
      if ( row.getVirtualServerNumberMarket() != null )
        return false;
    } else if ( !virtual_server_number_market.equals( row.getVirtualServerNumberMarket() ) ) {
        return false;
    }
    if ( notice_type == null ) {
      if ( row.getNoticeType() != null )
        return false;
    } else if ( !notice_type.equals( row.getNoticeType() ) ) {
        return false;
    }
    if ( notice_number != row.getNoticeNumber() )
      return false;
    if ( client_number == null ) {
      if ( row.getClientNumber() != null )
        return false;
    } else if ( !client_number.equals( row.getClientNumber() ) ) {
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
    if ( corp_code == null ) {
      if ( row.getCorpCode() != null )
        return false;
    } else if ( !corp_code.equals( row.getCorpCode() ) ) {
        return false;
    }
    if ( resend_flg == null ) {
      if ( row.getResendFlg() != null )
        return false;
    } else if ( !resend_flg.equals( row.getResendFlg() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( error_code == null ) {
      if ( row.getErrorCode() != null )
        return false;
    } else if ( !error_code.equals( row.getErrorCode() ) ) {
        return false;
    }
    if ( accept_number == null ) {
      if ( row.getAcceptNumber() != null )
        return false;
    } else if ( !accept_number.equals( row.getAcceptNumber() ) ) {
        return false;
    }
    if ( market_time == null ) {
      if ( !row.getMarketTimeIsNull() )
        return false;
    } else if ( row.getMarketTimeIsNull() || ( market_time.intValue() != row.getMarketTime() ) ) {
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
    if ( price_condition_type == null ) {
      if ( row.getPriceConditionType() != null )
        return false;
    } else if ( !price_condition_type.equals( row.getPriceConditionType() ) ) {
        return false;
    }
    if ( order_quantity == null ) {
      if ( !row.getOrderQuantityIsNull() )
        return false;
    } else if ( row.getOrderQuantityIsNull() || ( order_quantity.doubleValue() != row.getOrderQuantity() ) ) {
        return false;
    }
    if ( limit_price == null ) {
      if ( !row.getLimitPriceIsNull() )
        return false;
    } else if ( row.getLimitPriceIsNull() || ( limit_price.doubleValue() != row.getLimitPrice() ) ) {
        return false;
    }
    if ( tradeaudit_code == null ) {
      if ( row.getTradeauditCode() != null )
        return false;
    } else if ( !tradeaudit_code.equals( row.getTradeauditCode() ) ) {
        return false;
    }
    if ( margin_code == null ) {
      if ( row.getMarginCode() != null )
        return false;
    } else if ( !margin_code.equals( row.getMarginCode() ) ) {
        return false;
    }
    if ( short_sell_order_flag == null ) {
      if ( row.getShortSellOrderFlag() != null )
        return false;
    } else if ( !short_sell_order_flag.equals( row.getShortSellOrderFlag() ) ) {
        return false;
    }
    if ( org_limit_price == null ) {
      if ( !row.getOrgLimitPriceIsNull() )
        return false;
    } else if ( row.getOrgLimitPriceIsNull() || ( org_limit_price.doubleValue() != row.getOrgLimitPrice() ) ) {
        return false;
    }
    if ( org_corp_code == null ) {
      if ( row.getOrgCorpCode() != null )
        return false;
    } else if ( !org_corp_code.equals( row.getOrgCorpCode() ) ) {
        return false;
    }
    if ( cut_quantity == null ) {
      if ( !row.getCutQuantityIsNull() )
        return false;
    } else if ( row.getCutQuantityIsNull() || ( cut_quantity.longValue() != row.getCutQuantity() ) ) {
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
    if ( left_quantity == null ) {
      if ( !row.getLeftQuantityIsNull() )
        return false;
    } else if ( row.getLeftQuantityIsNull() || ( left_quantity.doubleValue() != row.getLeftQuantity() ) ) {
        return false;
    }
    if ( price_mark == null ) {
      if ( row.getPriceMark() != null )
        return false;
    } else if ( !price_mark.equals( row.getPriceMark() ) ) {
        return false;
    }
    if ( exec_mark == null ) {
      if ( row.getExecMark() != null )
        return false;
    } else if ( !exec_mark.equals( row.getExecMark() ) ) {
        return false;
    }
    if ( exec_number == null ) {
      if ( !row.getExecNumberIsNull() )
        return false;
    } else if ( row.getExecNumberIsNull() || ( exec_number.intValue() != row.getExecNumber() ) ) {
        return false;
    }
    if ( modified_result == null ) {
      if ( row.getModifiedResult() != null )
        return false;
    } else if ( !modified_result.equals( row.getModifiedResult() ) ) {
        return false;
    }
    if ( org_execution_condition == null ) {
      if ( row.getOrgExecutionCondition() != null )
        return false;
    } else if ( !org_execution_condition.equals( row.getOrgExecutionCondition() ) ) {
        return false;
    }
    if ( org_price_condition_type == null ) {
      if ( row.getOrgPriceConditionType() != null )
        return false;
    } else if ( !org_price_condition_type.equals( row.getOrgPriceConditionType() ) ) {
        return false;
    }
    if ( org_order_quantity == null ) {
      if ( !row.getOrgOrderQuantityIsNull() )
        return false;
    } else if ( row.getOrgOrderQuantityIsNull() || ( org_order_quantity.doubleValue() != row.getOrgOrderQuantity() ) ) {
        return false;
    }
    if ( canceled_quantity == null ) {
      if ( !row.getCanceledQuantityIsNull() )
        return false;
    } else if ( row.getCanceledQuantityIsNull() || ( canceled_quantity.longValue() != row.getCanceledQuantity() ) ) {
        return false;
    }
    if ( executed_quantity == null ) {
      if ( !row.getExecutedQuantityIsNull() )
        return false;
    } else if ( row.getExecutedQuantityIsNull() || ( executed_quantity.longValue() != row.getExecutedQuantity() ) ) {
        return false;
    }
    if ( reason_code == null ) {
      if ( row.getReasonCode() != null )
        return false;
    } else if ( !reason_code.equals( row.getReasonCode() ) ) {
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
    if ( biz_date_count != row.getBizDateCount() )
      return false;
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
    if ( submit_order_route_div == null ) {
      if ( row.getSubmitOrderRouteDiv() != null )
        return false;
    } else if ( !submit_order_route_div.equals( row.getSubmitOrderRouteDiv() ) ) {
        return false;
    }
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
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
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (virtual_server_number_market!=null? virtual_server_number_market.hashCode(): 0) 
        + (notice_type!=null? notice_type.hashCode(): 0) 
        + ((int) notice_number)
        + (client_number!=null? client_number.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (front_order_exchange_code!=null? front_order_exchange_code.hashCode(): 0) 
        + (front_order_system_code!=null? front_order_system_code.hashCode(): 0) 
        + (front_order_trade_code!=null? front_order_trade_code.hashCode(): 0) 
        + (data_class_code!=null? data_class_code.hashCode(): 0) 
        + (data_class_detail_code!=null? data_class_detail_code.hashCode(): 0) 
        + (corp_code!=null? corp_code.hashCode(): 0) 
        + (resend_flg!=null? resend_flg.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (error_code!=null? error_code.hashCode(): 0) 
        + (accept_number!=null? accept_number.hashCode(): 0) 
        + (market_time!=null? market_time.hashCode(): 0) 
        + (buy_sell_div!=null? buy_sell_div.hashCode(): 0) 
        + (execution_condition!=null? execution_condition.hashCode(): 0) 
        + (price_condition_type!=null? price_condition_type.hashCode(): 0) 
        + (order_quantity!=null? order_quantity.hashCode(): 0) 
        + (limit_price!=null? limit_price.hashCode(): 0) 
        + (tradeaudit_code!=null? tradeaudit_code.hashCode(): 0) 
        + (margin_code!=null? margin_code.hashCode(): 0) 
        + (short_sell_order_flag!=null? short_sell_order_flag.hashCode(): 0) 
        + (org_limit_price!=null? org_limit_price.hashCode(): 0) 
        + (org_corp_code!=null? org_corp_code.hashCode(): 0) 
        + (cut_quantity!=null? cut_quantity.hashCode(): 0) 
        + (exec_price!=null? exec_price.hashCode(): 0) 
        + (exec_quantity!=null? exec_quantity.hashCode(): 0) 
        + (left_quantity!=null? left_quantity.hashCode(): 0) 
        + (price_mark!=null? price_mark.hashCode(): 0) 
        + (exec_mark!=null? exec_mark.hashCode(): 0) 
        + (exec_number!=null? exec_number.hashCode(): 0) 
        + (modified_result!=null? modified_result.hashCode(): 0) 
        + (org_execution_condition!=null? org_execution_condition.hashCode(): 0) 
        + (org_price_condition_type!=null? org_price_condition_type.hashCode(): 0) 
        + (org_order_quantity!=null? org_order_quantity.hashCode(): 0) 
        + (canceled_quantity!=null? canceled_quantity.hashCode(): 0) 
        + (executed_quantity!=null? executed_quantity.hashCode(): 0) 
        + (reason_code!=null? reason_code.hashCode(): 0) 
        + (stop_mark!=null? stop_mark.hashCode(): 0) 
        + (expiration_quantity!=null? expiration_quantity.hashCode(): 0) 
        + ((int) biz_date_count)
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (submit_order_route_div!=null? submit_order_route_div.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + (stop_order_price!=null? stop_order_price.hashCode(): 0) 
        + (w_limit_price!=null? w_limit_price.hashCode(): 0) 
        + (org_stop_order_price!=null? org_stop_order_price.hashCode(): 0) 
        + (org_w_limit_price!=null? org_w_limit_price.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("virtual_server_number_market",virtual_server_number_market);
		map.put("notice_type",notice_type);
		map.put("notice_number",new Long(notice_number));
		if ( client_number != null )
			map.put("client_number",client_number);
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		map.put("front_order_exchange_code",front_order_exchange_code);
		map.put("front_order_system_code",front_order_system_code);
		if ( front_order_trade_code != null )
			map.put("front_order_trade_code",front_order_trade_code);
		if ( data_class_code != null )
			map.put("data_class_code",data_class_code);
		if ( data_class_detail_code != null )
			map.put("data_class_detail_code",data_class_detail_code);
		if ( corp_code != null )
			map.put("corp_code",corp_code);
		if ( resend_flg != null )
			map.put("resend_flg",resend_flg);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( error_code != null )
			map.put("error_code",error_code);
		if ( accept_number != null )
			map.put("accept_number",accept_number);
		if ( market_time != null )
			map.put("market_time",market_time);
		if ( buy_sell_div != null )
			map.put("buy_sell_div",buy_sell_div);
		if ( execution_condition != null )
			map.put("execution_condition",execution_condition);
		if ( price_condition_type != null )
			map.put("price_condition_type",price_condition_type);
		if ( order_quantity != null )
			map.put("order_quantity",order_quantity);
		if ( limit_price != null )
			map.put("limit_price",limit_price);
		if ( tradeaudit_code != null )
			map.put("tradeaudit_code",tradeaudit_code);
		if ( margin_code != null )
			map.put("margin_code",margin_code);
		if ( short_sell_order_flag != null )
			map.put("short_sell_order_flag",short_sell_order_flag);
		if ( org_limit_price != null )
			map.put("org_limit_price",org_limit_price);
		if ( org_corp_code != null )
			map.put("org_corp_code",org_corp_code);
		if ( cut_quantity != null )
			map.put("cut_quantity",cut_quantity);
		if ( exec_price != null )
			map.put("exec_price",exec_price);
		if ( exec_quantity != null )
			map.put("exec_quantity",exec_quantity);
		if ( left_quantity != null )
			map.put("left_quantity",left_quantity);
		if ( price_mark != null )
			map.put("price_mark",price_mark);
		if ( exec_mark != null )
			map.put("exec_mark",exec_mark);
		if ( exec_number != null )
			map.put("exec_number",exec_number);
		if ( modified_result != null )
			map.put("modified_result",modified_result);
		if ( org_execution_condition != null )
			map.put("org_execution_condition",org_execution_condition);
		if ( org_price_condition_type != null )
			map.put("org_price_condition_type",org_price_condition_type);
		if ( org_order_quantity != null )
			map.put("org_order_quantity",org_order_quantity);
		if ( canceled_quantity != null )
			map.put("canceled_quantity",canceled_quantity);
		if ( executed_quantity != null )
			map.put("executed_quantity",executed_quantity);
		if ( reason_code != null )
			map.put("reason_code",reason_code);
		if ( stop_mark != null )
			map.put("stop_mark",stop_mark);
		if ( expiration_quantity != null )
			map.put("expiration_quantity",expiration_quantity);
		if ( biz_date_count_is_set )
			map.put("biz_date_count",new Integer(biz_date_count));
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( submit_order_route_div_is_set )
			map.put("submit_order_route_div",submit_order_route_div);
		if ( product_type_is_set )
			map.put("product_type",product_type);
		if ( stop_order_price != null )
			map.put("stop_order_price",stop_order_price);
		if ( w_limit_price != null )
			map.put("w_limit_price",w_limit_price);
		if ( org_stop_order_price != null )
			map.put("org_stop_order_price",org_stop_order_price);
		if ( org_w_limit_price != null )
			map.put("org_w_limit_price",org_w_limit_price);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( client_number_is_modified )
			map.put("client_number",client_number);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( front_order_trade_code_is_modified )
			map.put("front_order_trade_code",front_order_trade_code);
		if ( data_class_code_is_modified )
			map.put("data_class_code",data_class_code);
		if ( data_class_detail_code_is_modified )
			map.put("data_class_detail_code",data_class_detail_code);
		if ( corp_code_is_modified )
			map.put("corp_code",corp_code);
		if ( resend_flg_is_modified )
			map.put("resend_flg",resend_flg);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( error_code_is_modified )
			map.put("error_code",error_code);
		if ( accept_number_is_modified )
			map.put("accept_number",accept_number);
		if ( market_time_is_modified )
			map.put("market_time",market_time);
		if ( buy_sell_div_is_modified )
			map.put("buy_sell_div",buy_sell_div);
		if ( execution_condition_is_modified )
			map.put("execution_condition",execution_condition);
		if ( price_condition_type_is_modified )
			map.put("price_condition_type",price_condition_type);
		if ( order_quantity_is_modified )
			map.put("order_quantity",order_quantity);
		if ( limit_price_is_modified )
			map.put("limit_price",limit_price);
		if ( tradeaudit_code_is_modified )
			map.put("tradeaudit_code",tradeaudit_code);
		if ( margin_code_is_modified )
			map.put("margin_code",margin_code);
		if ( short_sell_order_flag_is_modified )
			map.put("short_sell_order_flag",short_sell_order_flag);
		if ( org_limit_price_is_modified )
			map.put("org_limit_price",org_limit_price);
		if ( org_corp_code_is_modified )
			map.put("org_corp_code",org_corp_code);
		if ( cut_quantity_is_modified )
			map.put("cut_quantity",cut_quantity);
		if ( exec_price_is_modified )
			map.put("exec_price",exec_price);
		if ( exec_quantity_is_modified )
			map.put("exec_quantity",exec_quantity);
		if ( left_quantity_is_modified )
			map.put("left_quantity",left_quantity);
		if ( price_mark_is_modified )
			map.put("price_mark",price_mark);
		if ( exec_mark_is_modified )
			map.put("exec_mark",exec_mark);
		if ( exec_number_is_modified )
			map.put("exec_number",exec_number);
		if ( modified_result_is_modified )
			map.put("modified_result",modified_result);
		if ( org_execution_condition_is_modified )
			map.put("org_execution_condition",org_execution_condition);
		if ( org_price_condition_type_is_modified )
			map.put("org_price_condition_type",org_price_condition_type);
		if ( org_order_quantity_is_modified )
			map.put("org_order_quantity",org_order_quantity);
		if ( canceled_quantity_is_modified )
			map.put("canceled_quantity",canceled_quantity);
		if ( executed_quantity_is_modified )
			map.put("executed_quantity",executed_quantity);
		if ( reason_code_is_modified )
			map.put("reason_code",reason_code);
		if ( stop_mark_is_modified )
			map.put("stop_mark",stop_mark);
		if ( expiration_quantity_is_modified )
			map.put("expiration_quantity",expiration_quantity);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( submit_order_route_div_is_modified )
			map.put("submit_order_route_div",submit_order_route_div);
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( stop_order_price_is_modified )
			map.put("stop_order_price",stop_order_price);
		if ( w_limit_price_is_modified )
			map.put("w_limit_price",w_limit_price);
		if ( org_stop_order_price_is_modified )
			map.put("org_stop_order_price",org_stop_order_price);
		if ( org_w_limit_price_is_modified )
			map.put("org_w_limit_price",org_w_limit_price);
		if (map.size() == 0) {
			map.put("client_number",client_number);
			map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("front_order_trade_code",front_order_trade_code);
			map.put("data_class_code",data_class_code);
			map.put("data_class_detail_code",data_class_detail_code);
			map.put("corp_code",corp_code);
			map.put("resend_flg",resend_flg);
			map.put("product_code",product_code);
			map.put("error_code",error_code);
			map.put("accept_number",accept_number);
			map.put("market_time",market_time);
			map.put("buy_sell_div",buy_sell_div);
			map.put("execution_condition",execution_condition);
			map.put("price_condition_type",price_condition_type);
			map.put("order_quantity",order_quantity);
			map.put("limit_price",limit_price);
			map.put("tradeaudit_code",tradeaudit_code);
			map.put("margin_code",margin_code);
			map.put("short_sell_order_flag",short_sell_order_flag);
			map.put("org_limit_price",org_limit_price);
			map.put("org_corp_code",org_corp_code);
			map.put("cut_quantity",cut_quantity);
			map.put("exec_price",exec_price);
			map.put("exec_quantity",exec_quantity);
			map.put("left_quantity",left_quantity);
			map.put("price_mark",price_mark);
			map.put("exec_mark",exec_mark);
			map.put("exec_number",exec_number);
			map.put("modified_result",modified_result);
			map.put("org_execution_condition",org_execution_condition);
			map.put("org_price_condition_type",org_price_condition_type);
			map.put("org_order_quantity",org_order_quantity);
			map.put("canceled_quantity",canceled_quantity);
			map.put("executed_quantity",executed_quantity);
			map.put("reason_code",reason_code);
			map.put("stop_mark",stop_mark);
			map.put("expiration_quantity",expiration_quantity);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( submit_order_route_div_is_set )
				map.put("submit_order_route_div",submit_order_route_div);
			if ( product_type_is_set )
				map.put("product_type",product_type);
			map.put("stop_order_price",stop_order_price);
			map.put("w_limit_price",w_limit_price);
			map.put("org_stop_order_price",org_stop_order_price);
			map.put("org_w_limit_price",org_w_limit_price);
		}
		return map;
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
   * <em>notice_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNoticeType()
  {
    return notice_type;
  }


  /** 
   * <em>notice_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoticeTypeIsSet() {
    return notice_type_is_set;
  }


  /** 
   * <em>notice_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoticeTypeIsModified() {
    return notice_type_is_modified;
  }


  /** 
   * <em>notice_number</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getNoticeNumber()
  {
    return notice_number;
  }


  /** 
   * <em>notice_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoticeNumberIsSet() {
    return notice_number_is_set;
  }


  /** 
   * <em>notice_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoticeNumberIsModified() {
    return notice_number_is_modified;
  }


  /** 
   * <em>client_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getClientNumber()
  {
    return client_number;
  }


  /** 
   * <em>client_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClientNumberIsSet() {
    return client_number_is_set;
  }


  /** 
   * <em>client_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClientNumberIsModified() {
    return client_number_is_modified;
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
   * <em>resend_flg</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getResendFlg()
  {
    return resend_flg;
  }


  /** 
   * <em>resend_flg</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResendFlgIsSet() {
    return resend_flg_is_set;
  }


  /** 
   * <em>resend_flg</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResendFlgIsModified() {
    return resend_flg_is_modified;
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
   * <em>error_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getErrorCode()
  {
    return error_code;
  }


  /** 
   * <em>error_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorCodeIsSet() {
    return error_code_is_set;
  }


  /** 
   * <em>error_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorCodeIsModified() {
    return error_code_is_modified;
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
   * <em>market_time</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getMarketTime()
  {
    return ( market_time==null? ((int)0): market_time.intValue() );
  }


  /** 
   * <em>market_time</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarketTimeIsNull()
  {
    return market_time == null;
  }


  /** 
   * <em>market_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketTimeIsSet() {
    return market_time_is_set;
  }


  /** 
   * <em>market_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketTimeIsModified() {
    return market_time_is_modified;
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
   * <em>order_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOrderQuantity()
  {
    return ( order_quantity==null? ((double)0): order_quantity.doubleValue() );
  }


  /** 
   * <em>order_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrderQuantityIsNull()
  {
    return order_quantity == null;
  }


  /** 
   * <em>order_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderQuantityIsSet() {
    return order_quantity_is_set;
  }


  /** 
   * <em>order_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderQuantityIsModified() {
    return order_quantity_is_modified;
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
   * <em>margin_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginCode()
  {
    return margin_code;
  }


  /** 
   * <em>margin_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginCodeIsSet() {
    return margin_code_is_set;
  }


  /** 
   * <em>margin_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginCodeIsModified() {
    return margin_code_is_modified;
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
   * <em>org_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOrgLimitPrice()
  {
    return ( org_limit_price==null? ((double)0): org_limit_price.doubleValue() );
  }


  /** 
   * <em>org_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrgLimitPriceIsNull()
  {
    return org_limit_price == null;
  }


  /** 
   * <em>org_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgLimitPriceIsSet() {
    return org_limit_price_is_set;
  }


  /** 
   * <em>org_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgLimitPriceIsModified() {
    return org_limit_price_is_modified;
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
   * <em>cut_quantity</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getCutQuantity()
  {
    return ( cut_quantity==null? ((long)0): cut_quantity.longValue() );
  }


  /** 
   * <em>cut_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCutQuantityIsNull()
  {
    return cut_quantity == null;
  }


  /** 
   * <em>cut_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCutQuantityIsSet() {
    return cut_quantity_is_set;
  }


  /** 
   * <em>cut_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCutQuantityIsModified() {
    return cut_quantity_is_modified;
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
   * <em>left_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLeftQuantity()
  {
    return ( left_quantity==null? ((double)0): left_quantity.doubleValue() );
  }


  /** 
   * <em>left_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLeftQuantityIsNull()
  {
    return left_quantity == null;
  }


  /** 
   * <em>left_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLeftQuantityIsSet() {
    return left_quantity_is_set;
  }


  /** 
   * <em>left_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLeftQuantityIsModified() {
    return left_quantity_is_modified;
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
   * <em>exec_mark</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExecMark()
  {
    return exec_mark;
  }


  /** 
   * <em>exec_mark</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecMarkIsSet() {
    return exec_mark_is_set;
  }


  /** 
   * <em>exec_mark</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecMarkIsModified() {
    return exec_mark_is_modified;
  }


  /** 
   * <em>exec_number</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getExecNumber()
  {
    return ( exec_number==null? ((int)0): exec_number.intValue() );
  }


  /** 
   * <em>exec_number</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExecNumberIsNull()
  {
    return exec_number == null;
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
   * <em>modified_result</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getModifiedResult()
  {
    return modified_result;
  }


  /** 
   * <em>modified_result</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedResultIsSet() {
    return modified_result_is_set;
  }


  /** 
   * <em>modified_result</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedResultIsModified() {
    return modified_result_is_modified;
  }


  /** 
   * <em>org_execution_condition</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrgExecutionCondition()
  {
    return org_execution_condition;
  }


  /** 
   * <em>org_execution_condition</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgExecutionConditionIsSet() {
    return org_execution_condition_is_set;
  }


  /** 
   * <em>org_execution_condition</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgExecutionConditionIsModified() {
    return org_execution_condition_is_modified;
  }


  /** 
   * <em>org_price_condition_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrgPriceConditionType()
  {
    return org_price_condition_type;
  }


  /** 
   * <em>org_price_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgPriceConditionTypeIsSet() {
    return org_price_condition_type_is_set;
  }


  /** 
   * <em>org_price_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgPriceConditionTypeIsModified() {
    return org_price_condition_type_is_modified;
  }


  /** 
   * <em>org_order_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOrgOrderQuantity()
  {
    return ( org_order_quantity==null? ((double)0): org_order_quantity.doubleValue() );
  }


  /** 
   * <em>org_order_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrgOrderQuantityIsNull()
  {
    return org_order_quantity == null;
  }


  /** 
   * <em>org_order_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgOrderQuantityIsSet() {
    return org_order_quantity_is_set;
  }


  /** 
   * <em>org_order_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgOrderQuantityIsModified() {
    return org_order_quantity_is_modified;
  }


  /** 
   * <em>canceled_quantity</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getCanceledQuantity()
  {
    return ( canceled_quantity==null? ((long)0): canceled_quantity.longValue() );
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
   * <em>executed_quantity</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExecutedQuantity()
  {
    return ( executed_quantity==null? ((long)0): executed_quantity.longValue() );
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
   * <em>biz_date_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getBizDateCount()
  {
    return biz_date_count;
  }


  /** 
   * <em>biz_date_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBizDateCountIsSet() {
    return biz_date_count_is_set;
  }


  /** 
   * <em>biz_date_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBizDateCountIsModified() {
    return biz_date_count_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new MarketNoticeManagementPK(virtual_server_number_market, notice_type, notice_number, front_order_exchange_code, front_order_system_code, biz_date_count);
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
   * <em>notice_type</em>カラムの値を設定します。 
   *
   * @@param p_noticeType <em>notice_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setNoticeType( String p_noticeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    notice_type = p_noticeType;
    notice_type_is_set = true;
    notice_type_is_modified = true;
  }


  /** 
   * <em>notice_number</em>カラムの値を設定します。 
   *
   * @@param p_noticeNumber <em>notice_number</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setNoticeNumber( long p_noticeNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    notice_number = p_noticeNumber;
    notice_number_is_set = true;
    notice_number_is_modified = true;
  }


  /** 
   * <em>client_number</em>カラムの値を設定します。 
   *
   * @@param p_clientNumber <em>client_number</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setClientNumber( String p_clientNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    client_number = p_clientNumber;
    client_number_is_set = true;
    client_number_is_modified = true;
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
   * <em>resend_flg</em>カラムの値を設定します。 
   *
   * @@param p_resendFlg <em>resend_flg</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setResendFlg( String p_resendFlg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    resend_flg = p_resendFlg;
    resend_flg_is_set = true;
    resend_flg_is_modified = true;
  }


  /** 
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす12桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>error_code</em>カラムの値を設定します。 
   *
   * @@param p_errorCode <em>error_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setErrorCode( String p_errorCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_code = p_errorCode;
    error_code_is_set = true;
    error_code_is_modified = true;
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
   * <em>market_time</em>カラムの値を設定します。 
   *
   * @@param p_marketTime <em>market_time</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setMarketTime( int p_marketTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_time = new Integer(p_marketTime);
    market_time_is_set = true;
    market_time_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>market_time</em>カラムに値を設定します。 
   */
  public final void setMarketTime( Integer p_marketTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    market_time = p_marketTime;
    market_time_is_set = true;
    market_time_is_modified = true;
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
   * <em>order_quantity</em>カラムの値を設定します。 
   *
   * @@param p_orderQuantity <em>order_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOrderQuantity( double p_orderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_quantity = new Double(p_orderQuantity);
    order_quantity_is_set = true;
    order_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>order_quantity</em>カラムに値を設定します。 
   */
  public final void setOrderQuantity( Double p_orderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_quantity = p_orderQuantity;
    order_quantity_is_set = true;
    order_quantity_is_modified = true;
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
   * <em>margin_code</em>カラムの値を設定します。 
   *
   * @@param p_marginCode <em>margin_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginCode( String p_marginCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_code = p_marginCode;
    margin_code_is_set = true;
    margin_code_is_modified = true;
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
   * <em>org_limit_price</em>カラムの値を設定します。 
   *
   * @@param p_orgLimitPrice <em>org_limit_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOrgLimitPrice( double p_orgLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    org_limit_price = new Double(p_orgLimitPrice);
    org_limit_price_is_set = true;
    org_limit_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>org_limit_price</em>カラムに値を設定します。 
   */
  public final void setOrgLimitPrice( Double p_orgLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    org_limit_price = p_orgLimitPrice;
    org_limit_price_is_set = true;
    org_limit_price_is_modified = true;
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
   * <em>cut_quantity</em>カラムの値を設定します。 
   *
   * @@param p_cutQuantity <em>cut_quantity</em>カラムの値をあらわす13桁以下のlong値 
   */
  public final void setCutQuantity( long p_cutQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cut_quantity = new Long(p_cutQuantity);
    cut_quantity_is_set = true;
    cut_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cut_quantity</em>カラムに値を設定します。 
   */
  public final void setCutQuantity( Long p_cutQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cut_quantity = p_cutQuantity;
    cut_quantity_is_set = true;
    cut_quantity_is_modified = true;
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
   * <em>left_quantity</em>カラムの値を設定します。 
   *
   * @@param p_leftQuantity <em>left_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLeftQuantity( double p_leftQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    left_quantity = new Double(p_leftQuantity);
    left_quantity_is_set = true;
    left_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>left_quantity</em>カラムに値を設定します。 
   */
  public final void setLeftQuantity( Double p_leftQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    left_quantity = p_leftQuantity;
    left_quantity_is_set = true;
    left_quantity_is_modified = true;
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
   * <em>exec_mark</em>カラムの値を設定します。 
   *
   * @@param p_execMark <em>exec_mark</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExecMark( String p_execMark )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_mark = p_execMark;
    exec_mark_is_set = true;
    exec_mark_is_modified = true;
  }


  /** 
   * <em>exec_number</em>カラムの値を設定します。 
   *
   * @@param p_execNumber <em>exec_number</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setExecNumber( int p_execNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_number = new Integer(p_execNumber);
    exec_number_is_set = true;
    exec_number_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>exec_number</em>カラムに値を設定します。 
   */
  public final void setExecNumber( Integer p_execNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_number = p_execNumber;
    exec_number_is_set = true;
    exec_number_is_modified = true;
  }


  /** 
   * <em>modified_result</em>カラムの値を設定します。 
   *
   * @@param p_modifiedResult <em>modified_result</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setModifiedResult( String p_modifiedResult )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_result = p_modifiedResult;
    modified_result_is_set = true;
    modified_result_is_modified = true;
  }


  /** 
   * <em>org_execution_condition</em>カラムの値を設定します。 
   *
   * @@param p_orgExecutionCondition <em>org_execution_condition</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrgExecutionCondition( String p_orgExecutionCondition )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    org_execution_condition = p_orgExecutionCondition;
    org_execution_condition_is_set = true;
    org_execution_condition_is_modified = true;
  }


  /** 
   * <em>org_price_condition_type</em>カラムの値を設定します。 
   *
   * @@param p_orgPriceConditionType <em>org_price_condition_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrgPriceConditionType( String p_orgPriceConditionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    org_price_condition_type = p_orgPriceConditionType;
    org_price_condition_type_is_set = true;
    org_price_condition_type_is_modified = true;
  }


  /** 
   * <em>org_order_quantity</em>カラムの値を設定します。 
   *
   * @@param p_orgOrderQuantity <em>org_order_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOrgOrderQuantity( double p_orgOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    org_order_quantity = new Double(p_orgOrderQuantity);
    org_order_quantity_is_set = true;
    org_order_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>org_order_quantity</em>カラムに値を設定します。 
   */
  public final void setOrgOrderQuantity( Double p_orgOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    org_order_quantity = p_orgOrderQuantity;
    org_order_quantity_is_set = true;
    org_order_quantity_is_modified = true;
  }


  /** 
   * <em>canceled_quantity</em>カラムの値を設定します。 
   *
   * @@param p_canceledQuantity <em>canceled_quantity</em>カラムの値をあらわす13桁以下のlong値 
   */
  public final void setCanceledQuantity( long p_canceledQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    canceled_quantity = new Long(p_canceledQuantity);
    canceled_quantity_is_set = true;
    canceled_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>canceled_quantity</em>カラムに値を設定します。 
   */
  public final void setCanceledQuantity( Long p_canceledQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    canceled_quantity = p_canceledQuantity;
    canceled_quantity_is_set = true;
    canceled_quantity_is_modified = true;
  }


  /** 
   * <em>executed_quantity</em>カラムの値を設定します。 
   *
   * @@param p_executedQuantity <em>executed_quantity</em>カラムの値をあらわす13桁以下のlong値 
   */
  public final void setExecutedQuantity( long p_executedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    executed_quantity = new Long(p_executedQuantity);
    executed_quantity_is_set = true;
    executed_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>executed_quantity</em>カラムに値を設定します。 
   */
  public final void setExecutedQuantity( Long p_executedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    executed_quantity = p_executedQuantity;
    executed_quantity_is_set = true;
    executed_quantity_is_modified = true;
  }


  /** 
   * <em>reason_code</em>カラムの値を設定します。 
   *
   * @@param p_reasonCode <em>reason_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setReasonCode( String p_reasonCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reason_code = p_reasonCode;
    reason_code_is_set = true;
    reason_code_is_modified = true;
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
   * <em>biz_date_count</em>カラムの値を設定します。 
   *
   * @@param p_bizDateCount <em>biz_date_count</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setBizDateCount( int p_bizDateCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    biz_date_count = p_bizDateCount;
    biz_date_count_is_set = true;
    biz_date_count_is_modified = true;
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
                else if ( name.equals("biz_date_count") ) {
                    return new Integer( biz_date_count );
                }
                break;
            case 'c':
                if ( name.equals("client_number") ) {
                    return this.client_number;
                }
                else if ( name.equals("corp_code") ) {
                    return this.corp_code;
                }
                else if ( name.equals("cut_quantity") ) {
                    return this.cut_quantity;
                }
                else if ( name.equals("canceled_quantity") ) {
                    return this.canceled_quantity;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("data_class_code") ) {
                    return this.data_class_code;
                }
                else if ( name.equals("data_class_detail_code") ) {
                    return this.data_class_detail_code;
                }
                break;
            case 'e':
                if ( name.equals("error_code") ) {
                    return this.error_code;
                }
                else if ( name.equals("execution_condition") ) {
                    return this.execution_condition;
                }
                else if ( name.equals("exec_price") ) {
                    return this.exec_price;
                }
                else if ( name.equals("exec_quantity") ) {
                    return this.exec_quantity;
                }
                else if ( name.equals("exec_mark") ) {
                    return this.exec_mark;
                }
                else if ( name.equals("exec_number") ) {
                    return this.exec_number;
                }
                else if ( name.equals("executed_quantity") ) {
                    return this.executed_quantity;
                }
                else if ( name.equals("expiration_quantity") ) {
                    return this.expiration_quantity;
                }
                break;
            case 'f':
                if ( name.equals("front_order_exchange_code") ) {
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
                else if ( name.equals("left_quantity") ) {
                    return this.left_quantity;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_time") ) {
                    return this.market_time;
                }
                else if ( name.equals("margin_code") ) {
                    return this.margin_code;
                }
                else if ( name.equals("modified_result") ) {
                    return this.modified_result;
                }
                break;
            case 'n':
                if ( name.equals("notice_type") ) {
                    return this.notice_type;
                }
                else if ( name.equals("notice_number") ) {
                    return new Long( notice_number );
                }
                break;
            case 'o':
                if ( name.equals("order_quantity") ) {
                    return this.order_quantity;
                }
                else if ( name.equals("org_limit_price") ) {
                    return this.org_limit_price;
                }
                else if ( name.equals("org_corp_code") ) {
                    return this.org_corp_code;
                }
                else if ( name.equals("org_execution_condition") ) {
                    return this.org_execution_condition;
                }
                else if ( name.equals("org_price_condition_type") ) {
                    return this.org_price_condition_type;
                }
                else if ( name.equals("org_order_quantity") ) {
                    return this.org_order_quantity;
                }
                else if ( name.equals("org_stop_order_price") ) {
                    return this.org_stop_order_price;
                }
                else if ( name.equals("org_w_limit_price") ) {
                    return this.org_w_limit_price;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("price_condition_type") ) {
                    return this.price_condition_type;
                }
                else if ( name.equals("price_mark") ) {
                    return this.price_mark;
                }
                else if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                break;
            case 'r':
                if ( name.equals("resend_flg") ) {
                    return this.resend_flg;
                }
                else if ( name.equals("reason_code") ) {
                    return this.reason_code;
                }
                break;
            case 's':
                if ( name.equals("short_sell_order_flag") ) {
                    return this.short_sell_order_flag;
                }
                else if ( name.equals("stop_mark") ) {
                    return this.stop_mark;
                }
                else if ( name.equals("submit_order_route_div") ) {
                    return this.submit_order_route_div;
                }
                else if ( name.equals("stop_order_price") ) {
                    return this.stop_order_price;
                }
                break;
            case 't':
                if ( name.equals("tradeaudit_code") ) {
                    return this.tradeaudit_code;
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
                else if ( name.equals("biz_date_count") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'biz_date_count' must be Integer: '"+value+"' is not." );
                    this.biz_date_count = ((Integer) value).intValue();
                    if (this.biz_date_count_is_set)
                        this.biz_date_count_is_modified = true;
                    this.biz_date_count_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("client_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'client_number' must be String: '"+value+"' is not." );
                    this.client_number = (String) value;
                    if (this.client_number_is_set)
                        this.client_number_is_modified = true;
                    this.client_number_is_set = true;
                    return;
                }
                else if ( name.equals("corp_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'corp_code' must be String: '"+value+"' is not." );
                    this.corp_code = (String) value;
                    if (this.corp_code_is_set)
                        this.corp_code_is_modified = true;
                    this.corp_code_is_set = true;
                    return;
                }
                else if ( name.equals("cut_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'cut_quantity' must be Long: '"+value+"' is not." );
                    this.cut_quantity = (Long) value;
                    if (this.cut_quantity_is_set)
                        this.cut_quantity_is_modified = true;
                    this.cut_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("canceled_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'canceled_quantity' must be Long: '"+value+"' is not." );
                    this.canceled_quantity = (Long) value;
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
                if ( name.equals("data_class_code") ) {
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
                if ( name.equals("error_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_code' must be String: '"+value+"' is not." );
                    this.error_code = (String) value;
                    if (this.error_code_is_set)
                        this.error_code_is_modified = true;
                    this.error_code_is_set = true;
                    return;
                }
                else if ( name.equals("execution_condition") ) {
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
                else if ( name.equals("exec_mark") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exec_mark' must be String: '"+value+"' is not." );
                    this.exec_mark = (String) value;
                    if (this.exec_mark_is_set)
                        this.exec_mark_is_modified = true;
                    this.exec_mark_is_set = true;
                    return;
                }
                else if ( name.equals("exec_number") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'exec_number' must be Integer: '"+value+"' is not." );
                    this.exec_number = (Integer) value;
                    if (this.exec_number_is_set)
                        this.exec_number_is_modified = true;
                    this.exec_number_is_set = true;
                    return;
                }
                else if ( name.equals("executed_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'executed_quantity' must be Long: '"+value+"' is not." );
                    this.executed_quantity = (Long) value;
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
                break;
            case 'f':
                if ( name.equals("front_order_exchange_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'front_order_exchange_code' must be String: '"+value+"' is not." );
                    this.front_order_exchange_code = (String) value;
                    if (this.front_order_exchange_code_is_set)
                        this.front_order_exchange_code_is_modified = true;
                    this.front_order_exchange_code_is_set = true;
                    return;
                }
                else if ( name.equals("front_order_system_code") ) {
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
                else if ( name.equals("left_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'left_quantity' must be Double: '"+value+"' is not." );
                    this.left_quantity = (Double) value;
                    if (this.left_quantity_is_set)
                        this.left_quantity_is_modified = true;
                    this.left_quantity_is_set = true;
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
                if ( name.equals("market_time") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'market_time' must be Integer: '"+value+"' is not." );
                    this.market_time = (Integer) value;
                    if (this.market_time_is_set)
                        this.market_time_is_modified = true;
                    this.market_time_is_set = true;
                    return;
                }
                else if ( name.equals("margin_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_code' must be String: '"+value+"' is not." );
                    this.margin_code = (String) value;
                    if (this.margin_code_is_set)
                        this.margin_code_is_modified = true;
                    this.margin_code_is_set = true;
                    return;
                }
                else if ( name.equals("modified_result") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'modified_result' must be String: '"+value+"' is not." );
                    this.modified_result = (String) value;
                    if (this.modified_result_is_set)
                        this.modified_result_is_modified = true;
                    this.modified_result_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("notice_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'notice_type' must be String: '"+value+"' is not." );
                    this.notice_type = (String) value;
                    if (this.notice_type_is_set)
                        this.notice_type_is_modified = true;
                    this.notice_type_is_set = true;
                    return;
                }
                else if ( name.equals("notice_number") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'notice_number' must be Long: '"+value+"' is not." );
                    this.notice_number = ((Long) value).longValue();
                    if (this.notice_number_is_set)
                        this.notice_number_is_modified = true;
                    this.notice_number_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'order_quantity' must be Double: '"+value+"' is not." );
                    this.order_quantity = (Double) value;
                    if (this.order_quantity_is_set)
                        this.order_quantity_is_modified = true;
                    this.order_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("org_limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'org_limit_price' must be Double: '"+value+"' is not." );
                    this.org_limit_price = (Double) value;
                    if (this.org_limit_price_is_set)
                        this.org_limit_price_is_modified = true;
                    this.org_limit_price_is_set = true;
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
                else if ( name.equals("org_execution_condition") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'org_execution_condition' must be String: '"+value+"' is not." );
                    this.org_execution_condition = (String) value;
                    if (this.org_execution_condition_is_set)
                        this.org_execution_condition_is_modified = true;
                    this.org_execution_condition_is_set = true;
                    return;
                }
                else if ( name.equals("org_price_condition_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'org_price_condition_type' must be String: '"+value+"' is not." );
                    this.org_price_condition_type = (String) value;
                    if (this.org_price_condition_type_is_set)
                        this.org_price_condition_type_is_modified = true;
                    this.org_price_condition_type_is_set = true;
                    return;
                }
                else if ( name.equals("org_order_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'org_order_quantity' must be Double: '"+value+"' is not." );
                    this.org_order_quantity = (Double) value;
                    if (this.org_order_quantity_is_set)
                        this.org_order_quantity_is_modified = true;
                    this.org_order_quantity_is_set = true;
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
                else if ( name.equals("product_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("resend_flg") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'resend_flg' must be String: '"+value+"' is not." );
                    this.resend_flg = (String) value;
                    if (this.resend_flg_is_set)
                        this.resend_flg_is_modified = true;
                    this.resend_flg_is_set = true;
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
                break;
            case 's':
                if ( name.equals("short_sell_order_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_sell_order_flag' must be String: '"+value+"' is not." );
                    this.short_sell_order_flag = (String) value;
                    if (this.short_sell_order_flag_is_set)
                        this.short_sell_order_flag_is_modified = true;
                    this.short_sell_order_flag_is_set = true;
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
                if ( name.equals("tradeaudit_code") ) {
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
                if ( name.equals("virtual_server_number_market") ) {
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
