head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdmintoEqtypeOrderUnitRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.admintriggerorder.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * AdmintoEqtypeOrderUnitRowインタフェースは変更不可でリードオンリーである<em>adminto_eqtype_order_unit</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link AdmintoEqtypeOrderUnitRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AdmintoEqtypeOrderUnitPK 
 */
public interface AdmintoEqtypeOrderUnitRow extends Row {


  /** 
   * この{@@link AdmintoEqtypeOrderUnitRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "adminto_eqtype_order_unit", "session" );


  /** 
   * <em>order_unit_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getOrderUnitId();


  /** 
   * <em>order_unit_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderUnitIdIsSet();


  /** 
   * <em>order_unit_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderUnitIdIsModified();


  /** 
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAccountId();


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountIdIsSet();


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountIdIsModified();


  /** 
   * <em>sub_account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSubAccountId();


  /** 
   * <em>sub_account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubAccountIdIsSet();


  /** 
   * <em>sub_account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubAccountIdIsModified();


  /** 
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getBranchId();


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchIdIsSet();


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchIdIsModified();


  /** 
   * <em>trader_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getTraderId();


  /** 
   * <em>trader_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTraderIdIsNull();


  /** 
   * <em>trader_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderIdIsSet();


  /** 
   * <em>trader_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderIdIsModified();


  /** 
   * <em>order_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getOrderId();


  /** 
   * <em>order_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderIdIsSet();


  /** 
   * <em>order_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderIdIsModified();


  /** 
   * <em>order_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum getOrderType();


  /** 
   * <em>order_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderTypeIsSet();


  /** 
   * <em>order_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderTypeIsModified();


  /** 
   * <em>order_categ</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum getOrderCateg();


  /** 
   * <em>order_categ</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderCategIsSet();


  /** 
   * <em>order_categ</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderCategIsModified();


  /** 
   * <em>last_order_action_serial_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLastOrderActionSerialNo();


  /** 
   * <em>last_order_action_serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastOrderActionSerialNoIsSet();


  /** 
   * <em>last_order_action_serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastOrderActionSerialNoIsModified();


  /** 
   * <em>last_execution_serial_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLastExecutionSerialNo();


  /** 
   * <em>last_execution_serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastExecutionSerialNoIsSet();


  /** 
   * <em>last_execution_serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastExecutionSerialNoIsModified();


  /** 
   * <em>product_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType();


  /** 
   * <em>product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductTypeIsSet();


  /** 
   * <em>product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductTypeIsModified();


  /** 
   * <em>market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarketId();


  /** 
   * <em>market_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarketIdIsNull();


  /** 
   * <em>market_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketIdIsSet();


  /** 
   * <em>market_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketIdIsModified();


  /** 
   * <em>quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getQuantity();


  /** 
   * <em>quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuantityIsSet();


  /** 
   * <em>quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuantityIsModified();


  /** 
   * <em>limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLimitPrice();


  /** 
   * <em>limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLimitPriceIsNull();


  /** 
   * <em>limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitPriceIsSet();


  /** 
   * <em>limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitPriceIsModified();


  /** 
   * <em>execution_condition_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionTypeの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType getExecutionConditionType();


  /** 
   * <em>execution_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecutionConditionTypeIsSet();


  /** 
   * <em>execution_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecutionConditionTypeIsModified();


  /** 
   * <em>price_condition_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPriceConditionType();


  /** 
   * <em>price_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceConditionTypeIsSet();


  /** 
   * <em>price_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceConditionTypeIsModified();


  /** 
   * <em>order_condition_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderConditionType();


  /** 
   * <em>order_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderConditionTypeIsSet();


  /** 
   * <em>order_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderConditionTypeIsModified();


  /** 
   * <em>order_cond_operator</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderCondOperator();


  /** 
   * <em>order_cond_operator</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderCondOperatorIsSet();


  /** 
   * <em>order_cond_operator</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderCondOperatorIsModified();


  /** 
   * <em>stop_order_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getStopOrderPrice();


  /** 
   * <em>stop_order_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getStopOrderPriceIsNull();


  /** 
   * <em>stop_order_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopOrderPriceIsSet();


  /** 
   * <em>stop_order_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopOrderPriceIsModified();


  /** 
   * <em>w_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getWLimitPrice();


  /** 
   * <em>w_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getWLimitPriceIsNull();


  /** 
   * <em>w_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWLimitPriceIsSet();


  /** 
   * <em>w_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWLimitPriceIsModified();


  /** 
   * <em>delivery_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getDeliveryDate();


  /** 
   * <em>delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveryDateIsSet();


  /** 
   * <em>delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveryDateIsModified();


  /** 
   * <em>expiration_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getExpirationDate();


  /** 
   * <em>expiration_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExpirationDateIsSet();


  /** 
   * <em>expiration_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExpirationDateIsModified();


  /** 
   * <em>confirmed_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getConfirmedQuantity();


  /** 
   * <em>confirmed_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getConfirmedQuantityIsNull();


  /** 
   * <em>confirmed_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedQuantityIsSet();


  /** 
   * <em>confirmed_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedQuantityIsModified();


  /** 
   * <em>confirmed_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getConfirmedPrice();


  /** 
   * <em>confirmed_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getConfirmedPriceIsNull();


  /** 
   * <em>confirmed_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedPriceIsSet();


  /** 
   * <em>confirmed_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedPriceIsModified();


  /** 
   * <em>executed_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getExecutedQuantity();


  /** 
   * <em>executed_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExecutedQuantityIsNull();


  /** 
   * <em>executed_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecutedQuantityIsSet();


  /** 
   * <em>executed_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecutedQuantityIsModified();


  /** 
   * <em>executed_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getExecutedAmount();


  /** 
   * <em>executed_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExecutedAmountIsNull();


  /** 
   * <em>executed_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecutedAmountIsSet();


  /** 
   * <em>executed_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecutedAmountIsModified();


  /** 
   * <em>order_status</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum getOrderStatus();


  /** 
   * <em>order_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderStatusIsSet();


  /** 
   * <em>order_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderStatusIsModified();


  /** 
   * <em>order_open_status</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum getOrderOpenStatus();


  /** 
   * <em>order_open_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderOpenStatusIsSet();


  /** 
   * <em>order_open_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderOpenStatusIsModified();


  /** 
   * <em>expiration_status</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum getExpirationStatus();


  /** 
   * <em>expiration_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExpirationStatusIsSet();


  /** 
   * <em>expiration_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExpirationStatusIsModified();


  /** 
   * <em>tax_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getTaxType();


  /** 
   * <em>tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxTypeIsSet();


  /** 
   * <em>tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxTypeIsModified();


  /** 
   * <em>swap_tax_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getSwapTaxType();


  /** 
   * <em>swap_tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwapTaxTypeIsSet();


  /** 
   * <em>swap_tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwapTaxTypeIsModified();


  /** 
   * <em>repayment_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRepaymentType();


  /** 
   * <em>repayment_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepaymentTypeIsSet();


  /** 
   * <em>repayment_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepaymentTypeIsModified();


  /** 
   * <em>repayment_num</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getRepaymentNum();


  /** 
   * <em>repayment_num</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRepaymentNumIsNull();


  /** 
   * <em>repayment_num</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepaymentNumIsSet();


  /** 
   * <em>repayment_num</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepaymentNumIsModified();


  /** 
   * <em>sonar_repayment_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarRepaymentType();


  /** 
   * <em>sonar_repayment_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarRepaymentTypeIsSet();


  /** 
   * <em>sonar_repayment_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarRepaymentTypeIsModified();


  /** 
   * <em>biz_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBizDate();


  /** 
   * <em>biz_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBizDateIsSet();


  /** 
   * <em>biz_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBizDateIsModified();


  /** 
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getProductId();


  /** 
   * <em>product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductIdIsSet();


  /** 
   * <em>product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductIdIsModified();


  /** 
   * <em>quantity_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum getQuantityType();


  /** 
   * <em>quantity_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuantityTypeIsSet();


  /** 
   * <em>quantity_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuantityTypeIsModified();


  /** 
   * <em>order_chanel</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderChanel();


  /** 
   * <em>order_chanel</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderChanelIsSet();


  /** 
   * <em>order_chanel</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderChanelIsModified();


  /** 
   * <em>received_date_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getReceivedDateTime();


  /** 
   * <em>received_date_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceivedDateTimeIsSet();


  /** 
   * <em>received_date_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceivedDateTimeIsModified();


  /** 
   * <em>voucher_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getVoucherNo();


  /** 
   * <em>voucher_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getVoucherNoIsSet();


  /** 
   * <em>voucher_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getVoucherNoIsModified();


  /** 
   * <em>comm_tbl_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCommTblNo();


  /** 
   * <em>comm_tbl_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommTblNoIsSet();


  /** 
   * <em>comm_tbl_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommTblNoIsModified();


  /** 
   * <em>comm_tbl_sub_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCommTblSubNo();


  /** 
   * <em>comm_tbl_sub_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommTblSubNoIsSet();


  /** 
   * <em>comm_tbl_sub_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommTblSubNoIsModified();


  /** 
   * <em>sonar_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarTraderCode();


  /** 
   * <em>sonar_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeIsSet();


  /** 
   * <em>sonar_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeIsModified();


  /** 
   * <em>price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPrice();


  /** 
   * <em>price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPriceIsNull();


  /** 
   * <em>price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceIsSet();


  /** 
   * <em>price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceIsModified();


  /** 
   * <em>order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderRequestNumber();


  /** 
   * <em>order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRequestNumberIsSet();


  /** 
   * <em>order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRequestNumberIsModified();


  /** 
   * <em>estimated_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getEstimatedPrice();


  /** 
   * <em>estimated_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getEstimatedPriceIsNull();


  /** 
   * <em>estimated_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEstimatedPriceIsSet();


  /** 
   * <em>estimated_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEstimatedPriceIsModified();


  /** 
   * <em>capital_gain</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCapitalGain();


  /** 
   * <em>capital_gain</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCapitalGainIsNull();


  /** 
   * <em>capital_gain</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCapitalGainIsSet();


  /** 
   * <em>capital_gain</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCapitalGainIsModified();


  /** 
   * <em>capital_gain_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCapitalGainTax();


  /** 
   * <em>capital_gain_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCapitalGainTaxIsNull();


  /** 
   * <em>capital_gain_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCapitalGainTaxIsSet();


  /** 
   * <em>capital_gain_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCapitalGainTaxIsModified();


  /** 
   * <em>sonar_traded_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarTradedCode();


  /** 
   * <em>sonar_traded_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTradedCodeIsSet();


  /** 
   * <em>sonar_traded_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTradedCodeIsModified();


  /** 
   * <em>sonar_market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarMarketCode();


  /** 
   * <em>sonar_market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarMarketCodeIsSet();


  /** 
   * <em>sonar_market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarMarketCodeIsModified();


  /** 
   * <em>comm_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCommProductCode();


  /** 
   * <em>comm_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommProductCodeIsSet();


  /** 
   * <em>comm_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommProductCodeIsModified();


  /** 
   * <em>short_sell_order_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShortSellOrderFlag();


  /** 
   * <em>short_sell_order_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortSellOrderFlagIsSet();


  /** 
   * <em>short_sell_order_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortSellOrderFlagIsModified();


  /** 
   * <em>modify_cancel_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getModifyCancelType();


  /** 
   * <em>modify_cancel_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getModifyCancelTypeIsSet();


  /** 
   * <em>modify_cancel_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getModifyCancelTypeIsModified();


  /** 
   * <em>order_root_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderRootDiv();


  /** 
   * <em>order_root_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRootDivIsSet();


  /** 
   * <em>order_root_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRootDivIsModified();


  /** 
   * <em>submit_order_route_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSubmitOrderRouteDiv();


  /** 
   * <em>submit_order_route_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubmitOrderRouteDivIsSet();


  /** 
   * <em>submit_order_route_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubmitOrderRouteDivIsModified();


  /** 
   * <em>confirmed_order_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getConfirmedOrderPrice();


  /** 
   * <em>confirmed_order_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getConfirmedOrderPriceIsNull();


  /** 
   * <em>confirmed_order_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedOrderPriceIsSet();


  /** 
   * <em>confirmed_order_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedOrderPriceIsModified();


  /** 
   * <em>confirmed_estimated_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getConfirmedEstimatedPrice();


  /** 
   * <em>confirmed_estimated_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getConfirmedEstimatedPriceIsNull();


  /** 
   * <em>confirmed_estimated_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedEstimatedPriceIsSet();


  /** 
   * <em>confirmed_estimated_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedEstimatedPriceIsModified();


  /** 
   * <em>confirmed_exec_condition_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionTypeの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType getConfirmedExecConditionType();


  /** 
   * <em>confirmed_exec_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedExecConditionTypeIsSet();


  /** 
   * <em>confirmed_exec_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedExecConditionTypeIsModified();


  /** 
   * <em>confirmed_price_condition_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getConfirmedPriceConditionType();


  /** 
   * <em>confirmed_price_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedPriceConditionTypeIsSet();


  /** 
   * <em>confirmed_price_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedPriceConditionTypeIsModified();


  /** 
   * <em>closing_order_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getClosingOrderType();


  /** 
   * <em>closing_order_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClosingOrderTypeIsSet();


  /** 
   * <em>closing_order_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClosingOrderTypeIsModified();


  /** 
   * <em>error_reason_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getErrorReasonCode();


  /** 
   * <em>error_reason_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getErrorReasonCodeIsSet();


  /** 
   * <em>error_reason_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getErrorReasonCodeIsModified();


  /** 
   * <em>request_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRequestType();


  /** 
   * <em>request_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRequestTypeIsSet();


  /** 
   * <em>request_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRequestTypeIsModified();


  /** 
   * <em>first_order_unit_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getFirstOrderUnitId();


  /** 
   * <em>first_order_unit_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFirstOrderUnitIdIsNull();


  /** 
   * <em>first_order_unit_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstOrderUnitIdIsSet();


  /** 
   * <em>first_order_unit_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstOrderUnitIdIsModified();


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getCreatedTimestamp();


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatedTimestampIsSet();


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatedTimestampIsModified();


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getLastUpdatedTimestamp();


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdatedTimestampIsSet();


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdatedTimestampIsModified();


  /** 
   * <em>confirmed_order_rev</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getConfirmedOrderRev();


  /** 
   * <em>confirmed_order_rev</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedOrderRevIsSet();


  /** 
   * <em>confirmed_order_rev</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedOrderRevIsModified();


  /** 
   * <em>order_rev</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderRev();


  /** 
   * <em>order_rev</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRevIsSet();


  /** 
   * <em>order_rev</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRevIsModified();


  /** 
   * <em>reserve_order_exist_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReserveOrderExistFlag();


  /** 
   * <em>reserve_order_exist_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReserveOrderExistFlagIsSet();


  /** 
   * <em>reserve_order_exist_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReserveOrderExistFlagIsModified();


  /** 
   * <em>original_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOriginalQuantity();


  /** 
   * <em>original_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOriginalQuantityIsNull();


  /** 
   * <em>original_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOriginalQuantityIsSet();


  /** 
   * <em>original_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOriginalQuantityIsModified();


  /** 
   * <em>stop_order_ordered_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getStopOrderOrderedTimestamp();


  /** 
   * <em>stop_order_ordered_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopOrderOrderedTimestampIsSet();


  /** 
   * <em>stop_order_ordered_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopOrderOrderedTimestampIsModified();


  /** 
   * <em>org_order_condition_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrgOrderConditionType();


  /** 
   * <em>org_order_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgOrderConditionTypeIsSet();


  /** 
   * <em>org_order_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgOrderConditionTypeIsModified();


  /** 
   * <em>org_order_cond_operator</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrgOrderCondOperator();


  /** 
   * <em>org_order_cond_operator</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgOrderCondOperatorIsSet();


  /** 
   * <em>org_order_cond_operator</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgOrderCondOperatorIsModified();


  /** 
   * <em>org_stop_order_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOrgStopOrderPrice();


  /** 
   * <em>org_stop_order_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOrgStopOrderPriceIsNull();


  /** 
   * <em>org_stop_order_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgStopOrderPriceIsSet();


  /** 
   * <em>org_stop_order_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgStopOrderPriceIsModified();


  /** 
   * <em>org_w_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOrgWLimitPrice();


  /** 
   * <em>org_w_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOrgWLimitPriceIsNull();


  /** 
   * <em>org_w_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgWLimitPriceIsSet();


  /** 
   * <em>org_w_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgWLimitPriceIsModified();


  /** 
   * <em>org_w_limit_exec_cond_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionTypeの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType getOrgWLimitExecCondType();


  /** 
   * <em>org_w_limit_exec_cond_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgWLimitExecCondTypeIsSet();


  /** 
   * <em>org_w_limit_exec_cond_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgWLimitExecCondTypeIsModified();


  /** 
   * <em>w_limit_exec_cond_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionTypeの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType getWLimitExecCondType();


  /** 
   * <em>w_limit_exec_cond_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWLimitExecCondTypeIsSet();


  /** 
   * <em>w_limit_exec_cond_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWLimitExecCondTypeIsModified();


  /** 
   * <em>w_limit_before_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getWLimitBeforeLimitPrice();


  /** 
   * <em>w_limit_before_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getWLimitBeforeLimitPriceIsNull();


  /** 
   * <em>w_limit_before_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWLimitBeforeLimitPriceIsSet();


  /** 
   * <em>w_limit_before_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWLimitBeforeLimitPriceIsModified();


  /** 
   * <em>w_limit_before_exec_cond_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionTypeの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType getWLimitBeforeExecCondType();


  /** 
   * <em>w_limit_before_exec_cond_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWLimitBeforeExecCondTypeIsSet();


  /** 
   * <em>w_limit_before_exec_cond_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWLimitBeforeExecCondTypeIsModified();


  /** 
   * <em>submit_order_delay_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getSubmitOrderDelayFlag();


  /** 
   * <em>submit_order_delay_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubmitOrderDelayFlagIsSet();


  /** 
   * <em>submit_order_delay_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubmitOrderDelayFlagIsModified();


}
@
