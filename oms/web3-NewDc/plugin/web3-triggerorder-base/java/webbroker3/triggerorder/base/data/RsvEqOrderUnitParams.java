head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.20.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	RsvEqOrderUnitParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * rsv_eq_order_unitテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link RsvEqOrderUnitRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link RsvEqOrderUnitParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link RsvEqOrderUnitParams}が{@@link RsvEqOrderUnitRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RsvEqOrderUnitPK 
 * @@see RsvEqOrderUnitRow 
 */
public class RsvEqOrderUnitParams extends Params implements RsvEqOrderUnitRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "rsv_eq_order_unit";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = RsvEqOrderUnitRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return RsvEqOrderUnitRow.TYPE;
  }


  /** 
   * <em>order_id</em>カラムの値 
   */
  public  long  order_id;

  /** 
   * <em>order_unit_id</em>カラムの値 
   */
  public  Long  order_unit_id;

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
   * <em>reserve_order_trading_type</em>カラムの値 
   */
  public  String  reserve_order_trading_type;

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
   * <em>price_adjust_value</em>カラムの値 
   */
  public  Double  price_adjust_value;

  /** 
   * <em>expiration_date</em>カラムの値 
   */
  public  java.sql.Timestamp  expiration_date;

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
   * <em>biz_date</em>カラムの値 
   */
  public  String  biz_date;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>order_chanel</em>カラムの値 
   */
  public  String  order_chanel;

  /** 
   * <em>received_date_time</em>カラムの値 
   */
  public  java.sql.Timestamp  received_date_time;

  /** 
   * <em>price</em>カラムの値 
   */
  public  Double  price;

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
   * <em>order_root_div</em>カラムの値 
   */
  public  String  order_root_div;

  /** 
   * <em>closing_order_type</em>カラムの値 
   */
  public  String  closing_order_type;

  /** 
   * <em>first_order_unit_id</em>カラムの値 
   */
  public  Long  first_order_unit_id;

  /** 
   * <em>order_error_code</em>カラムの値 
   */
  public  String  order_error_code;

  /** 
   * <em>parent_order_id</em>カラムの値 
   */
  public  long  parent_order_id;

  /** 
   * <em>parent_order_unit_id</em>カラムの値 
   */
  public  Long  parent_order_unit_id;

  /** 
   * <em>serial_no_in_parent</em>カラムの値 
   */
  public  Integer  serial_no_in_parent;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

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
  private boolean reserve_order_trading_type_is_set = false;
  private boolean reserve_order_trading_type_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean limit_price_is_set = false;
  private boolean limit_price_is_modified = false;
  private boolean price_adjust_value_is_set = false;
  private boolean price_adjust_value_is_modified = false;
  private boolean expiration_date_is_set = false;
  private boolean expiration_date_is_modified = false;
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
  private boolean biz_date_is_set = false;
  private boolean biz_date_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean order_chanel_is_set = false;
  private boolean order_chanel_is_modified = false;
  private boolean received_date_time_is_set = false;
  private boolean received_date_time_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean estimated_price_is_set = false;
  private boolean estimated_price_is_modified = false;
  private boolean capital_gain_is_set = false;
  private boolean capital_gain_is_modified = false;
  private boolean capital_gain_tax_is_set = false;
  private boolean capital_gain_tax_is_modified = false;
  private boolean order_root_div_is_set = false;
  private boolean order_root_div_is_modified = false;
  private boolean closing_order_type_is_set = false;
  private boolean closing_order_type_is_modified = false;
  private boolean first_order_unit_id_is_set = false;
  private boolean first_order_unit_id_is_modified = false;
  private boolean order_error_code_is_set = false;
  private boolean order_error_code_is_modified = false;
  private boolean parent_order_id_is_set = false;
  private boolean parent_order_id_is_modified = false;
  private boolean parent_order_unit_id_is_set = false;
  private boolean parent_order_unit_id_is_modified = false;
  private boolean serial_no_in_parent_is_set = false;
  private boolean serial_no_in_parent_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean forced_expire_type_is_set = false;
  private boolean forced_expire_type_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "order_id=" + order_id
      + "," + "order_unit_id=" +order_unit_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "branch_id=" +branch_id
      + "," + "trader_id=" +trader_id
      + "," + "order_type=" +order_type
      + "," + "order_categ=" +order_categ
      + "," + "last_order_action_serial_no=" +last_order_action_serial_no
      + "," + "reserve_order_trading_type=" +reserve_order_trading_type
      + "," + "product_type=" +product_type
      + "," + "market_id=" +market_id
      + "," + "quantity=" +quantity
      + "," + "limit_price=" +limit_price
      + "," + "price_adjust_value=" +price_adjust_value
      + "," + "expiration_date=" +expiration_date
      + "," + "order_status=" +order_status
      + "," + "order_open_status=" +order_open_status
      + "," + "expiration_status=" +expiration_status
      + "," + "tax_type=" +tax_type
      + "," + "swap_tax_type=" +swap_tax_type
      + "," + "repayment_type=" +repayment_type
      + "," + "repayment_num=" +repayment_num
      + "," + "biz_date=" +biz_date
      + "," + "product_id=" +product_id
      + "," + "order_chanel=" +order_chanel
      + "," + "received_date_time=" +received_date_time
      + "," + "price=" +price
      + "," + "estimated_price=" +estimated_price
      + "," + "capital_gain=" +capital_gain
      + "," + "capital_gain_tax=" +capital_gain_tax
      + "," + "order_root_div=" +order_root_div
      + "," + "closing_order_type=" +closing_order_type
      + "," + "first_order_unit_id=" +first_order_unit_id
      + "," + "order_error_code=" +order_error_code
      + "," + "parent_order_id=" +parent_order_id
      + "," + "parent_order_unit_id=" +parent_order_unit_id
      + "," + "serial_no_in_parent=" +serial_no_in_parent
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "forced_expire_type=" +forced_expire_type
      + "}";
  }


  /** 
   * 値が未設定のRsvEqOrderUnitParamsオブジェクトを作成します。 
   */
  public RsvEqOrderUnitParams() {
    order_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のRsvEqOrderUnitRowオブジェクトの値を利用してRsvEqOrderUnitParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するRsvEqOrderUnitRowオブジェクト 
   */
  public RsvEqOrderUnitParams( RsvEqOrderUnitRow p_row )
  {
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    if ( !p_row.getOrderUnitIdIsNull() )
      order_unit_id = new Long( p_row.getOrderUnitId() );
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
    order_type = p_row.getOrderType();
    order_type_is_set = p_row.getOrderTypeIsSet();
    order_type_is_modified = p_row.getOrderTypeIsModified();
    order_categ = p_row.getOrderCateg();
    order_categ_is_set = p_row.getOrderCategIsSet();
    order_categ_is_modified = p_row.getOrderCategIsModified();
    last_order_action_serial_no = p_row.getLastOrderActionSerialNo();
    last_order_action_serial_no_is_set = p_row.getLastOrderActionSerialNoIsSet();
    last_order_action_serial_no_is_modified = p_row.getLastOrderActionSerialNoIsModified();
    reserve_order_trading_type = p_row.getReserveOrderTradingType();
    reserve_order_trading_type_is_set = p_row.getReserveOrderTradingTypeIsSet();
    reserve_order_trading_type_is_modified = p_row.getReserveOrderTradingTypeIsModified();
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
    if ( !p_row.getPriceAdjustValueIsNull() )
      price_adjust_value = new Double( p_row.getPriceAdjustValue() );
    price_adjust_value_is_set = p_row.getPriceAdjustValueIsSet();
    price_adjust_value_is_modified = p_row.getPriceAdjustValueIsModified();
    expiration_date = p_row.getExpirationDate();
    expiration_date_is_set = p_row.getExpirationDateIsSet();
    expiration_date_is_modified = p_row.getExpirationDateIsModified();
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
    biz_date = p_row.getBizDate();
    biz_date_is_set = p_row.getBizDateIsSet();
    biz_date_is_modified = p_row.getBizDateIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    order_chanel = p_row.getOrderChanel();
    order_chanel_is_set = p_row.getOrderChanelIsSet();
    order_chanel_is_modified = p_row.getOrderChanelIsModified();
    received_date_time = p_row.getReceivedDateTime();
    received_date_time_is_set = p_row.getReceivedDateTimeIsSet();
    received_date_time_is_modified = p_row.getReceivedDateTimeIsModified();
    if ( !p_row.getPriceIsNull() )
      price = new Double( p_row.getPrice() );
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
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
    order_root_div = p_row.getOrderRootDiv();
    order_root_div_is_set = p_row.getOrderRootDivIsSet();
    order_root_div_is_modified = p_row.getOrderRootDivIsModified();
    closing_order_type = p_row.getClosingOrderType();
    closing_order_type_is_set = p_row.getClosingOrderTypeIsSet();
    closing_order_type_is_modified = p_row.getClosingOrderTypeIsModified();
    if ( !p_row.getFirstOrderUnitIdIsNull() )
      first_order_unit_id = new Long( p_row.getFirstOrderUnitId() );
    first_order_unit_id_is_set = p_row.getFirstOrderUnitIdIsSet();
    first_order_unit_id_is_modified = p_row.getFirstOrderUnitIdIsModified();
    order_error_code = p_row.getOrderErrorCode();
    order_error_code_is_set = p_row.getOrderErrorCodeIsSet();
    order_error_code_is_modified = p_row.getOrderErrorCodeIsModified();
    parent_order_id = p_row.getParentOrderId();
    parent_order_id_is_set = p_row.getParentOrderIdIsSet();
    parent_order_id_is_modified = p_row.getParentOrderIdIsModified();
    if ( !p_row.getParentOrderUnitIdIsNull() )
      parent_order_unit_id = new Long( p_row.getParentOrderUnitId() );
    parent_order_unit_id_is_set = p_row.getParentOrderUnitIdIsSet();
    parent_order_unit_id_is_modified = p_row.getParentOrderUnitIdIsModified();
    if ( !p_row.getSerialNoInParentIsNull() )
      serial_no_in_parent = new Integer( p_row.getSerialNoInParent() );
    serial_no_in_parent_is_set = p_row.getSerialNoInParentIsSet();
    serial_no_in_parent_is_modified = p_row.getSerialNoInParentIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    forced_expire_type = p_row.getForcedExpireType();
    forced_expire_type_is_set = p_row.getForcedExpireTypeIsSet();
    forced_expire_type_is_modified = p_row.getForcedExpireTypeIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      order_unit_id_is_set = true;
      order_unit_id_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      sub_account_id_is_set = true;
      sub_account_id_is_modified = true;
      branch_id_is_set = true;
      branch_id_is_modified = true;
      trader_id_is_set = true;
      trader_id_is_modified = true;
      order_type_is_set = true;
      order_type_is_modified = true;
      order_categ_is_set = true;
      order_categ_is_modified = true;
      last_order_action_serial_no_is_set = true;
      last_order_action_serial_no_is_modified = true;
      reserve_order_trading_type_is_set = true;
      reserve_order_trading_type_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      limit_price_is_set = true;
      limit_price_is_modified = true;
      price_adjust_value_is_set = true;
      price_adjust_value_is_modified = true;
      expiration_date_is_set = true;
      expiration_date_is_modified = true;
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
      biz_date_is_set = true;
      biz_date_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      order_chanel_is_set = true;
      order_chanel_is_modified = true;
      received_date_time_is_set = true;
      received_date_time_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      estimated_price_is_set = true;
      estimated_price_is_modified = true;
      capital_gain_is_set = true;
      capital_gain_is_modified = true;
      capital_gain_tax_is_set = true;
      capital_gain_tax_is_modified = true;
      order_root_div_is_set = true;
      order_root_div_is_modified = true;
      closing_order_type_is_set = true;
      closing_order_type_is_modified = true;
      first_order_unit_id_is_set = true;
      first_order_unit_id_is_modified = true;
      order_error_code_is_set = true;
      order_error_code_is_modified = true;
      parent_order_id_is_set = true;
      parent_order_id_is_modified = true;
      parent_order_unit_id_is_set = true;
      parent_order_unit_id_is_modified = true;
      serial_no_in_parent_is_set = true;
      serial_no_in_parent_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      forced_expire_type_is_set = true;
      forced_expire_type_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof RsvEqOrderUnitRow ) )
       return false;
    return fieldsEqual( (RsvEqOrderUnitRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のRsvEqOrderUnitRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( RsvEqOrderUnitRow row )
  {
    if ( order_unit_id == null ) {
      if ( !row.getOrderUnitIdIsNull() )
        return false;
    } else if ( row.getOrderUnitIdIsNull() || ( order_unit_id.longValue() != row.getOrderUnitId() ) ) {
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
    if ( reserve_order_trading_type == null ) {
      if ( row.getReserveOrderTradingType() != null )
        return false;
    } else if ( !reserve_order_trading_type.equals( row.getReserveOrderTradingType() ) ) {
        return false;
    }
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
    if ( price_adjust_value == null ) {
      if ( !row.getPriceAdjustValueIsNull() )
        return false;
    } else if ( row.getPriceAdjustValueIsNull() || ( price_adjust_value.doubleValue() != row.getPriceAdjustValue() ) ) {
        return false;
    }
    if ( expiration_date == null ) {
      if ( row.getExpirationDate() != null )
        return false;
    } else if ( !expiration_date.equals( row.getExpirationDate() ) ) {
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
    if ( biz_date == null ) {
      if ( row.getBizDate() != null )
        return false;
    } else if ( !biz_date.equals( row.getBizDate() ) ) {
        return false;
    }
    if ( product_id != row.getProductId() )
      return false;
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
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.doubleValue() != row.getPrice() ) ) {
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
    if ( first_order_unit_id == null ) {
      if ( !row.getFirstOrderUnitIdIsNull() )
        return false;
    } else if ( row.getFirstOrderUnitIdIsNull() || ( first_order_unit_id.longValue() != row.getFirstOrderUnitId() ) ) {
        return false;
    }
    if ( order_error_code == null ) {
      if ( row.getOrderErrorCode() != null )
        return false;
    } else if ( !order_error_code.equals( row.getOrderErrorCode() ) ) {
        return false;
    }
    if ( parent_order_id != row.getParentOrderId() )
      return false;
    if ( parent_order_unit_id == null ) {
      if ( !row.getParentOrderUnitIdIsNull() )
        return false;
    } else if ( row.getParentOrderUnitIdIsNull() || ( parent_order_unit_id.longValue() != row.getParentOrderUnitId() ) ) {
        return false;
    }
    if ( serial_no_in_parent == null ) {
      if ( !row.getSerialNoInParentIsNull() )
        return false;
    } else if ( row.getSerialNoInParentIsNull() || ( serial_no_in_parent.intValue() != row.getSerialNoInParent() ) ) {
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
      return  (order_unit_id!=null? order_unit_id.hashCode(): 0) 
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) branch_id)
        + (trader_id!=null? trader_id.hashCode(): 0) 
        + ((int) order_id)
        + (order_type!=null? order_type.hashCode(): 0) 
        + (order_categ!=null? order_categ.hashCode(): 0) 
        + ((int) last_order_action_serial_no)
        + (reserve_order_trading_type!=null? reserve_order_trading_type.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + (market_id!=null? market_id.hashCode(): 0) 
        + ((int) quantity)
        + (limit_price!=null? limit_price.hashCode(): 0) 
        + (price_adjust_value!=null? price_adjust_value.hashCode(): 0) 
        + (expiration_date!=null? expiration_date.hashCode(): 0) 
        + (order_status!=null? order_status.hashCode(): 0) 
        + (order_open_status!=null? order_open_status.hashCode(): 0) 
        + (expiration_status!=null? expiration_status.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (swap_tax_type!=null? swap_tax_type.hashCode(): 0) 
        + (repayment_type!=null? repayment_type.hashCode(): 0) 
        + (repayment_num!=null? repayment_num.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
        + ((int) product_id)
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (received_date_time!=null? received_date_time.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (estimated_price!=null? estimated_price.hashCode(): 0) 
        + (capital_gain!=null? capital_gain.hashCode(): 0) 
        + (capital_gain_tax!=null? capital_gain_tax.hashCode(): 0) 
        + (order_root_div!=null? order_root_div.hashCode(): 0) 
        + (closing_order_type!=null? closing_order_type.hashCode(): 0) 
        + (first_order_unit_id!=null? first_order_unit_id.hashCode(): 0) 
        + (order_error_code!=null? order_error_code.hashCode(): 0) 
        + ((int) parent_order_id)
        + (parent_order_unit_id!=null? parent_order_unit_id.hashCode(): 0) 
        + (serial_no_in_parent!=null? serial_no_in_parent.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
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
		if ( !order_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_type' must be set before inserting.");
		if ( !order_categ_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_categ' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
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
		if ( !parent_order_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'parent_order_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		if ( order_unit_id != null )
			map.put("order_unit_id",order_unit_id);
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
		if ( reserve_order_trading_type != null )
			map.put("reserve_order_trading_type",reserve_order_trading_type);
		map.put("product_type",product_type);
		if ( market_id != null )
			map.put("market_id",market_id);
		map.put("quantity",new Double(quantity));
		if ( limit_price != null )
			map.put("limit_price",limit_price);
		if ( price_adjust_value != null )
			map.put("price_adjust_value",price_adjust_value);
		if ( expiration_date != null )
			map.put("expiration_date",expiration_date);
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
		map.put("biz_date",biz_date);
		map.put("product_id",new Long(product_id));
		if ( order_chanel != null )
			map.put("order_chanel",order_chanel);
		if ( received_date_time != null )
			map.put("received_date_time",received_date_time);
		if ( price != null )
			map.put("price",price);
		if ( estimated_price != null )
			map.put("estimated_price",estimated_price);
		if ( capital_gain_is_set )
			map.put("capital_gain",capital_gain);
		if ( capital_gain_tax_is_set )
			map.put("capital_gain_tax",capital_gain_tax);
		if ( order_root_div != null )
			map.put("order_root_div",order_root_div);
		if ( closing_order_type != null )
			map.put("closing_order_type",closing_order_type);
		if ( first_order_unit_id != null )
			map.put("first_order_unit_id",first_order_unit_id);
		if ( order_error_code != null )
			map.put("order_error_code",order_error_code);
		map.put("parent_order_id",new Long(parent_order_id));
		if ( parent_order_unit_id != null )
			map.put("parent_order_unit_id",parent_order_unit_id);
		if ( serial_no_in_parent != null )
			map.put("serial_no_in_parent",serial_no_in_parent);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( forced_expire_type_is_set )
			map.put("forced_expire_type",forced_expire_type);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( order_unit_id_is_modified )
			map.put("order_unit_id",order_unit_id);
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( sub_account_id_is_modified )
			map.put("sub_account_id",new Long(sub_account_id));
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( trader_id_is_modified )
			map.put("trader_id",trader_id);
		if ( order_type_is_modified )
			map.put("order_type",order_type);
		if ( order_categ_is_modified )
			map.put("order_categ",order_categ);
		if ( last_order_action_serial_no_is_modified )
			map.put("last_order_action_serial_no",new Integer(last_order_action_serial_no));
		if ( reserve_order_trading_type_is_modified )
			map.put("reserve_order_trading_type",reserve_order_trading_type);
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( market_id_is_modified )
			map.put("market_id",market_id);
		if ( quantity_is_modified )
			map.put("quantity",new Double(quantity));
		if ( limit_price_is_modified )
			map.put("limit_price",limit_price);
		if ( price_adjust_value_is_modified )
			map.put("price_adjust_value",price_adjust_value);
		if ( expiration_date_is_modified )
			map.put("expiration_date",expiration_date);
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
		if ( biz_date_is_modified )
			map.put("biz_date",biz_date);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( order_chanel_is_modified )
			map.put("order_chanel",order_chanel);
		if ( received_date_time_is_modified )
			map.put("received_date_time",received_date_time);
		if ( price_is_modified )
			map.put("price",price);
		if ( estimated_price_is_modified )
			map.put("estimated_price",estimated_price);
		if ( capital_gain_is_modified )
			map.put("capital_gain",capital_gain);
		if ( capital_gain_tax_is_modified )
			map.put("capital_gain_tax",capital_gain_tax);
		if ( order_root_div_is_modified )
			map.put("order_root_div",order_root_div);
		if ( closing_order_type_is_modified )
			map.put("closing_order_type",closing_order_type);
		if ( first_order_unit_id_is_modified )
			map.put("first_order_unit_id",first_order_unit_id);
		if ( order_error_code_is_modified )
			map.put("order_error_code",order_error_code);
		if ( parent_order_id_is_modified )
			map.put("parent_order_id",new Long(parent_order_id));
		if ( parent_order_unit_id_is_modified )
			map.put("parent_order_unit_id",parent_order_unit_id);
		if ( serial_no_in_parent_is_modified )
			map.put("serial_no_in_parent",serial_no_in_parent);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( forced_expire_type_is_modified )
			map.put("forced_expire_type",forced_expire_type);
		if (map.size() == 0) {
			map.put("order_unit_id",order_unit_id);
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			map.put("trader_id",trader_id);
			if ( order_type_is_set )
				map.put("order_type",order_type);
			if ( order_categ_is_set )
				map.put("order_categ",order_categ);
			if ( last_order_action_serial_no_is_set )
				map.put("last_order_action_serial_no",new Integer(last_order_action_serial_no));
			map.put("reserve_order_trading_type",reserve_order_trading_type);
			if ( product_type_is_set )
				map.put("product_type",product_type);
			map.put("market_id",market_id);
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			map.put("limit_price",limit_price);
			map.put("price_adjust_value",price_adjust_value);
			map.put("expiration_date",expiration_date);
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
			if ( biz_date_is_set )
				map.put("biz_date",biz_date);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			map.put("order_chanel",order_chanel);
			map.put("received_date_time",received_date_time);
			map.put("price",price);
			map.put("estimated_price",estimated_price);
			if ( capital_gain_is_set )
				map.put("capital_gain",capital_gain);
			if ( capital_gain_tax_is_set )
				map.put("capital_gain_tax",capital_gain_tax);
			map.put("order_root_div",order_root_div);
			map.put("closing_order_type",closing_order_type);
			map.put("first_order_unit_id",first_order_unit_id);
			map.put("order_error_code",order_error_code);
			if ( parent_order_id_is_set )
				map.put("parent_order_id",new Long(parent_order_id));
			map.put("parent_order_unit_id",parent_order_unit_id);
			map.put("serial_no_in_parent",serial_no_in_parent);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
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
    return ( order_unit_id==null? ((long)0): order_unit_id.longValue() );
  }


  /** 
   * <em>order_unit_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrderUnitIdIsNull()
  {
    return order_unit_id == null;
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
   * <em>reserve_order_trading_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReserveOrderTradingType()
  {
    return reserve_order_trading_type;
  }


  /** 
   * <em>reserve_order_trading_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReserveOrderTradingTypeIsSet() {
    return reserve_order_trading_type_is_set;
  }


  /** 
   * <em>reserve_order_trading_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReserveOrderTradingTypeIsModified() {
    return reserve_order_trading_type_is_modified;
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
   * <em>price_adjust_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPriceAdjustValue()
  {
    return ( price_adjust_value==null? ((double)0): price_adjust_value.doubleValue() );
  }


  /** 
   * <em>price_adjust_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPriceAdjustValueIsNull()
  {
    return price_adjust_value == null;
  }


  /** 
   * <em>price_adjust_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceAdjustValueIsSet() {
    return price_adjust_value_is_set;
  }


  /** 
   * <em>price_adjust_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceAdjustValueIsModified() {
    return price_adjust_value_is_modified;
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
   * <em>order_error_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderErrorCode()
  {
    return order_error_code;
  }


  /** 
   * <em>order_error_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderErrorCodeIsSet() {
    return order_error_code_is_set;
  }


  /** 
   * <em>order_error_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderErrorCodeIsModified() {
    return order_error_code_is_modified;
  }


  /** 
   * <em>parent_order_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getParentOrderId()
  {
    return parent_order_id;
  }


  /** 
   * <em>parent_order_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParentOrderIdIsSet() {
    return parent_order_id_is_set;
  }


  /** 
   * <em>parent_order_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParentOrderIdIsModified() {
    return parent_order_id_is_modified;
  }


  /** 
   * <em>parent_order_unit_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getParentOrderUnitId()
  {
    return ( parent_order_unit_id==null? ((long)0): parent_order_unit_id.longValue() );
  }


  /** 
   * <em>parent_order_unit_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getParentOrderUnitIdIsNull()
  {
    return parent_order_unit_id == null;
  }


  /** 
   * <em>parent_order_unit_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParentOrderUnitIdIsSet() {
    return parent_order_unit_id_is_set;
  }


  /** 
   * <em>parent_order_unit_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParentOrderUnitIdIsModified() {
    return parent_order_unit_id_is_modified;
  }


  /** 
   * <em>serial_no_in_parent</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSerialNoInParent()
  {
    return ( serial_no_in_parent==null? ((int)0): serial_no_in_parent.intValue() );
  }


  /** 
   * <em>serial_no_in_parent</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSerialNoInParentIsNull()
  {
    return serial_no_in_parent == null;
  }


  /** 
   * <em>serial_no_in_parent</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerialNoInParentIsSet() {
    return serial_no_in_parent_is_set;
  }


  /** 
   * <em>serial_no_in_parent</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerialNoInParentIsModified() {
    return serial_no_in_parent_is_modified;
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
    return new RsvEqOrderUnitPK(order_id);
  }


  /** 
   * <em>order_unit_id</em>カラムの値を設定します。 
   *
   * @@param p_orderUnitId <em>order_unit_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setOrderUnitId( long p_orderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_unit_id = new Long(p_orderUnitId);
    order_unit_id_is_set = true;
    order_unit_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>order_unit_id</em>カラムに値を設定します。 
   */
  public final void setOrderUnitId( Long p_orderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
   * <em>reserve_order_trading_type</em>カラムの値を設定します。 
   *
   * @@param p_reserveOrderTradingType <em>reserve_order_trading_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setReserveOrderTradingType( String p_reserveOrderTradingType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reserve_order_trading_type = p_reserveOrderTradingType;
    reserve_order_trading_type_is_set = true;
    reserve_order_trading_type_is_modified = true;
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
   * <em>price_adjust_value</em>カラムの値を設定します。 
   *
   * @@param p_priceAdjustValue <em>price_adjust_value</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPriceAdjustValue( double p_priceAdjustValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price_adjust_value = new Double(p_priceAdjustValue);
    price_adjust_value_is_set = true;
    price_adjust_value_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>price_adjust_value</em>カラムに値を設定します。 
   */
  public final void setPriceAdjustValue( Double p_priceAdjustValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    price_adjust_value = p_priceAdjustValue;
    price_adjust_value_is_set = true;
    price_adjust_value_is_modified = true;
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
   * <em>order_error_code</em>カラムの値を設定します。 
   *
   * @@param p_orderErrorCode <em>order_error_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setOrderErrorCode( String p_orderErrorCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_error_code = p_orderErrorCode;
    order_error_code_is_set = true;
    order_error_code_is_modified = true;
  }


  /** 
   * <em>parent_order_id</em>カラムの値を設定します。 
   *
   * @@param p_parentOrderId <em>parent_order_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setParentOrderId( long p_parentOrderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    parent_order_id = p_parentOrderId;
    parent_order_id_is_set = true;
    parent_order_id_is_modified = true;
  }


  /** 
   * <em>parent_order_unit_id</em>カラムの値を設定します。 
   *
   * @@param p_parentOrderUnitId <em>parent_order_unit_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setParentOrderUnitId( long p_parentOrderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    parent_order_unit_id = new Long(p_parentOrderUnitId);
    parent_order_unit_id_is_set = true;
    parent_order_unit_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>parent_order_unit_id</em>カラムに値を設定します。 
   */
  public final void setParentOrderUnitId( Long p_parentOrderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    parent_order_unit_id = p_parentOrderUnitId;
    parent_order_unit_id_is_set = true;
    parent_order_unit_id_is_modified = true;
  }


  /** 
   * <em>serial_no_in_parent</em>カラムの値を設定します。 
   *
   * @@param p_serialNoInParent <em>serial_no_in_parent</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setSerialNoInParent( int p_serialNoInParent )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    serial_no_in_parent = new Integer(p_serialNoInParent);
    serial_no_in_parent_is_set = true;
    serial_no_in_parent_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>serial_no_in_parent</em>カラムに値を設定します。 
   */
  public final void setSerialNoInParent( Integer p_serialNoInParent )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    serial_no_in_parent = p_serialNoInParent;
    serial_no_in_parent_is_set = true;
    serial_no_in_parent_is_modified = true;
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
                if ( name.equals("capital_gain") ) {
                    return this.capital_gain;
                }
                else if ( name.equals("capital_gain_tax") ) {
                    return this.capital_gain_tax;
                }
                else if ( name.equals("closing_order_type") ) {
                    return this.closing_order_type;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("expiration_date") ) {
                    return this.expiration_date;
                }
                else if ( name.equals("expiration_status") ) {
                    return this.expiration_status;
                }
                else if ( name.equals("estimated_price") ) {
                    return this.estimated_price;
                }
                break;
            case 'f':
                if ( name.equals("first_order_unit_id") ) {
                    return this.first_order_unit_id;
                }
                else if ( name.equals("forced_expire_type") ) {
                    return this.forced_expire_type;
                }
                break;
            case 'l':
                if ( name.equals("last_order_action_serial_no") ) {
                    return new Integer( last_order_action_serial_no );
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
                break;
            case 'o':
                if ( name.equals("order_unit_id") ) {
                    return this.order_unit_id;
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
                else if ( name.equals("order_chanel") ) {
                    return this.order_chanel;
                }
                else if ( name.equals("order_root_div") ) {
                    return this.order_root_div;
                }
                else if ( name.equals("order_error_code") ) {
                    return this.order_error_code;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("price_adjust_value") ) {
                    return this.price_adjust_value;
                }
                else if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("price") ) {
                    return this.price;
                }
                else if ( name.equals("parent_order_id") ) {
                    return new Long( parent_order_id );
                }
                else if ( name.equals("parent_order_unit_id") ) {
                    return this.parent_order_unit_id;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return new Double( quantity );
                }
                break;
            case 'r':
                if ( name.equals("reserve_order_trading_type") ) {
                    return this.reserve_order_trading_type;
                }
                else if ( name.equals("repayment_type") ) {
                    return this.repayment_type;
                }
                else if ( name.equals("repayment_num") ) {
                    return this.repayment_num;
                }
                else if ( name.equals("received_date_time") ) {
                    return this.received_date_time;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("swap_tax_type") ) {
                    return this.swap_tax_type;
                }
                else if ( name.equals("serial_no_in_parent") ) {
                    return this.serial_no_in_parent;
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
                if ( name.equals("capital_gain") ) {
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
                break;
            case 'e':
                if ( name.equals("expiration_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'expiration_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.expiration_date = (java.sql.Timestamp) value;
                    if (this.expiration_date_is_set)
                        this.expiration_date_is_modified = true;
                    this.expiration_date_is_set = true;
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
                break;
            case 'o':
                if ( name.equals("order_unit_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_unit_id' must be Long: '"+value+"' is not." );
                    this.order_unit_id = (Long) value;
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
                else if ( name.equals("order_error_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_error_code' must be String: '"+value+"' is not." );
                    this.order_error_code = (String) value;
                    if (this.order_error_code_is_set)
                        this.order_error_code_is_modified = true;
                    this.order_error_code_is_set = true;
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
                else if ( name.equals("price_adjust_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'price_adjust_value' must be Double: '"+value+"' is not." );
                    this.price_adjust_value = (Double) value;
                    if (this.price_adjust_value_is_set)
                        this.price_adjust_value_is_modified = true;
                    this.price_adjust_value_is_set = true;
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
                else if ( name.equals("parent_order_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'parent_order_id' must be Long: '"+value+"' is not." );
                    this.parent_order_id = ((Long) value).longValue();
                    if (this.parent_order_id_is_set)
                        this.parent_order_id_is_modified = true;
                    this.parent_order_id_is_set = true;
                    return;
                }
                else if ( name.equals("parent_order_unit_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'parent_order_unit_id' must be Long: '"+value+"' is not." );
                    this.parent_order_unit_id = (Long) value;
                    if (this.parent_order_unit_id_is_set)
                        this.parent_order_unit_id_is_modified = true;
                    this.parent_order_unit_id_is_set = true;
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
                if ( name.equals("reserve_order_trading_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reserve_order_trading_type' must be String: '"+value+"' is not." );
                    this.reserve_order_trading_type = (String) value;
                    if (this.reserve_order_trading_type_is_set)
                        this.reserve_order_trading_type_is_modified = true;
                    this.reserve_order_trading_type_is_set = true;
                    return;
                }
                else if ( name.equals("repayment_type") ) {
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
                else if ( name.equals("swap_tax_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) )
                        throw new IllegalArgumentException( "value for 'swap_tax_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum: '"+value+"' is not." );
                    this.swap_tax_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) value;
                    if (this.swap_tax_type_is_set)
                        this.swap_tax_type_is_modified = true;
                    this.swap_tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("serial_no_in_parent") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'serial_no_in_parent' must be Integer: '"+value+"' is not." );
                    this.serial_no_in_parent = (Integer) value;
                    if (this.serial_no_in_parent_is_set)
                        this.serial_no_in_parent_is_modified = true;
                    this.serial_no_in_parent_is_set = true;
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
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
