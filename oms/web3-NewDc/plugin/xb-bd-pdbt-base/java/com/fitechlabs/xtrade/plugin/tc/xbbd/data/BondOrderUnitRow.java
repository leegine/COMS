head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.58.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondOrderUnitRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * BondOrderUnitRowインタフェースは変更不可でリードオンリーである<em>bond_order_unit</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link BondOrderUnitRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondOrderUnitPK 
 */
public interface BondOrderUnitRow extends Row {


  /** 
   * この{@@link BondOrderUnitRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "bond_order_unit", "account" );


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
   * <em>deal_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDealType();


  /** 
   * <em>deal_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDealTypeIsSet();


  /** 
   * <em>deal_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDealTypeIsModified();


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
   * @@return com.fitechlabs.xtrade.plugin.tc.xbbd.BondExecutionConditionTypeの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.xbbd.BondExecutionConditionType getExecutionConditionType();


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
   * <em>foreign_delivery_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getForeignDeliveryDate();


  /** 
   * <em>foreign_delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignDeliveryDateIsSet();


  /** 
   * <em>foreign_delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignDeliveryDateIsModified();


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
   * <em>lock_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLockStatus();


  /** 
   * <em>lock_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLockStatusIsSet();


  /** 
   * <em>lock_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLockStatusIsModified();


  /** 
   * <em>order_exec_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderExecStatus();


  /** 
   * <em>order_exec_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderExecStatusIsSet();


  /** 
   * <em>order_exec_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderExecStatusIsModified();


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
   * <em>foreign_biz_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForeignBizDate();


  /** 
   * <em>foreign_biz_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignBizDateIsSet();


  /** 
   * <em>foreign_biz_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignBizDateIsModified();


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
   * <em>bond_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum getBondType();


  /** 
   * <em>bond_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondTypeIsSet();


  /** 
   * <em>bond_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondTypeIsModified();


  /** 
   * <em>currency_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCurrencyCode();


  /** 
   * <em>currency_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyCodeIsSet();


  /** 
   * <em>currency_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyCodeIsModified();


  /** 
   * <em>settlement_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSettlementDiv();


  /** 
   * <em>settlement_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSettlementDivIsSet();


  /** 
   * <em>settlement_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSettlementDivIsModified();


  /** 
   * <em>auto_exec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAutoExecDiv();


  /** 
   * <em>auto_exec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAutoExecDivIsSet();


  /** 
   * <em>auto_exec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAutoExecDivIsModified();


  /** 
   * <em>executed_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getExecutedPrice();


  /** 
   * <em>executed_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExecutedPriceIsNull();


  /** 
   * <em>executed_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecutedPriceIsSet();


  /** 
   * <em>executed_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecutedPriceIsModified();


  /** 
   * <em>base_fx_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getBaseFxRate();


  /** 
   * <em>base_fx_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBaseFxRateIsNull();


  /** 
   * <em>base_fx_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBaseFxRateIsSet();


  /** 
   * <em>base_fx_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBaseFxRateIsModified();


  /** 
   * <em>exec_fx_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getExecFxRate();


  /** 
   * <em>exec_fx_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExecFxRateIsNull();


  /** 
   * <em>exec_fx_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecFxRateIsSet();


  /** 
   * <em>exec_fx_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecFxRateIsModified();


  /** 
   * <em>trading_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTradingPrice();


  /** 
   * <em>trading_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTradingPriceIsNull();


  /** 
   * <em>trading_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingPriceIsSet();


  /** 
   * <em>trading_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingPriceIsModified();


  /** 
   * <em>foreign_trading_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getForeignTradingPrice();


  /** 
   * <em>foreign_trading_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getForeignTradingPriceIsNull();


  /** 
   * <em>foreign_trading_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignTradingPriceIsSet();


  /** 
   * <em>foreign_trading_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignTradingPriceIsModified();


  /** 
   * <em>accrued_interest</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAccruedInterest();


  /** 
   * <em>accrued_interest</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccruedInterestIsNull();


  /** 
   * <em>accrued_interest</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccruedInterestIsSet();


  /** 
   * <em>accrued_interest</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccruedInterestIsModified();


  /** 
   * <em>foreign_accrued_interest</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getForeignAccruedInterest();


  /** 
   * <em>foreign_accrued_interest</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getForeignAccruedInterestIsNull();


  /** 
   * <em>foreign_accrued_interest</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignAccruedInterestIsSet();


  /** 
   * <em>foreign_accrued_interest</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignAccruedInterestIsModified();


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
   * <em>foreign_estimated_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getForeignEstimatedPrice();


  /** 
   * <em>foreign_estimated_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getForeignEstimatedPriceIsNull();


  /** 
   * <em>foreign_estimated_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignEstimatedPriceIsSet();


  /** 
   * <em>foreign_estimated_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignEstimatedPriceIsModified();


  /** 
   * <em>adjustment_before_maturity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAdjustmentBeforeMaturity();


  /** 
   * <em>adjustment_before_maturity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAdjustmentBeforeMaturityIsNull();


  /** 
   * <em>adjustment_before_maturity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdjustmentBeforeMaturityIsSet();


  /** 
   * <em>adjustment_before_maturity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdjustmentBeforeMaturityIsModified();


  /** 
   * <em>elapsed_days</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getElapsedDays();


  /** 
   * <em>elapsed_days</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getElapsedDaysIsNull();


  /** 
   * <em>elapsed_days</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getElapsedDaysIsSet();


  /** 
   * <em>elapsed_days</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getElapsedDaysIsModified();


  /** 
   * <em>calc_base_days</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getCalcBaseDays();


  /** 
   * <em>calc_base_days</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCalcBaseDaysIsNull();


  /** 
   * <em>calc_base_days</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcBaseDaysIsSet();


  /** 
   * <em>calc_base_days</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcBaseDaysIsModified();


  /** 
   * <em>exec_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getExecDate();


  /** 
   * <em>exec_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecDateIsSet();


  /** 
   * <em>exec_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecDateIsModified();


  /** 
   * <em>foreign_exec_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getForeignExecDate();


  /** 
   * <em>foreign_exec_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignExecDateIsSet();


  /** 
   * <em>foreign_exec_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignExecDateIsModified();


  /** 
   * <em>payment_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getPaymentDate();


  /** 
   * <em>payment_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentDateIsSet();


  /** 
   * <em>payment_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentDateIsModified();


  /** 
   * <em>custodian_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCustodianCode();


  /** 
   * <em>custodian_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustodianCodeIsSet();


  /** 
   * <em>custodian_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustodianCodeIsModified();


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
   * <em>host_send_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHostSendDiv();


  /** 
   * <em>host_send_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostSendDivIsSet();


  /** 
   * <em>host_send_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostSendDivIsModified();


  /** 
   * <em>administrator_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAdministratorCode();


  /** 
   * <em>administrator_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdministratorCodeIsSet();


  /** 
   * <em>administrator_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdministratorCodeIsModified();


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
   * <em>exec_host_reflect_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExecHostReflectDiv();


  /** 
   * <em>exec_host_reflect_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecHostReflectDivIsSet();


  /** 
   * <em>exec_host_reflect_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecHostReflectDivIsModified();


  /** 
   * <em>cancel_host_reflect_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCancelHostReflectDiv();


  /** 
   * <em>cancel_host_reflect_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCancelHostReflectDivIsSet();


  /** 
   * <em>cancel_host_reflect_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCancelHostReflectDivIsModified();


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


}
@
