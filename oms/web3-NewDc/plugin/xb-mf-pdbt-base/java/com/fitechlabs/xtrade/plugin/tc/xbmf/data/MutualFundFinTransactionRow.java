head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.13.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MutualFundFinTransactionRow.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * MutualFundFinTransactionRowインタフェースは変更不可でリードオンリーである<em>mutual_fund_fin_transaction</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link MutualFundFinTransactionRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundFinTransactionPK 
 */
public interface MutualFundFinTransactionRow extends Row {


  /** 
   * この{@@link MutualFundFinTransactionRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "mutual_fund_fin_transaction", "account" );


  /** 
   * <em>fin_transaction_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getFinTransactionId();


  /** 
   * <em>fin_transaction_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinTransactionIdIsSet();


  /** 
   * <em>fin_transaction_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinTransactionIdIsModified();


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
   * <em>fin_transaction_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionTypeの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType getFinTransactionType();


  /** 
   * <em>fin_transaction_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinTransactionTypeIsSet();


  /** 
   * <em>fin_transaction_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinTransactionTypeIsModified();


  /** 
   * <em>fin_transaction_categ</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCategの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg getFinTransactionCateg();


  /** 
   * <em>fin_transaction_categ</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinTransactionCategIsSet();


  /** 
   * <em>fin_transaction_categ</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinTransactionCategIsModified();


  /** 
   * <em>fin_transaction_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getFinTransactionTimestamp();


  /** 
   * <em>fin_transaction_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinTransactionTimestampIsSet();


  /** 
   * <em>fin_transaction_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinTransactionTimestampIsModified();


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
   * <em>net_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getNetAmount();


  /** 
   * <em>net_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetAmountIsSet();


  /** 
   * <em>net_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNetAmountIsModified();


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
   * <em>order_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getOrderId();


  /** 
   * <em>order_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOrderIdIsNull();


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
   * <em>order_unit_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getOrderUnitId();


  /** 
   * <em>order_unit_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOrderUnitIdIsNull();


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
   * <em>order_execution_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getOrderExecutionId();


  /** 
   * <em>order_execution_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOrderExecutionIdIsNull();


  /** 
   * <em>order_execution_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderExecutionIdIsSet();


  /** 
   * <em>order_execution_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderExecutionIdIsModified();


  /** 
   * <em>commission_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCommissionFee();


  /** 
   * <em>commission_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionFeeIsSet();


  /** 
   * <em>commission_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionFeeIsModified();


  /** 
   * <em>commission_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCommissionFeeTax();


  /** 
   * <em>commission_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionFeeTaxIsSet();


  /** 
   * <em>commission_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionFeeTaxIsModified();


  /** 
   * <em>asset_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAssetId();


  /** 
   * <em>asset_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAssetIdIsNull();


  /** 
   * <em>asset_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetIdIsSet();


  /** 
   * <em>asset_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetIdIsModified();


  /** 
   * <em>capital_gain</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCapitalGain();


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
   * <em>transfered_asset_mng_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTransferedAssetMngFee();


  /** 
   * <em>transfered_asset_mng_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTransferedAssetMngFeeIsNull();


  /** 
   * <em>transfered_asset_mng_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferedAssetMngFeeIsSet();


  /** 
   * <em>transfered_asset_mng_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferedAssetMngFeeIsModified();


  /** 
   * <em>transfered_asset_mng_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTransferedAssetMngFeeTax();


  /** 
   * <em>transfered_asset_mng_fee_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTransferedAssetMngFeeTaxIsNull();


  /** 
   * <em>transfered_asset_mng_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferedAssetMngFeeTaxIsSet();


  /** 
   * <em>transfered_asset_mng_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferedAssetMngFeeTaxIsModified();


  /** 
   * <em>transfered_asset_setup_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTransferedAssetSetupFee();


  /** 
   * <em>transfered_asset_setup_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTransferedAssetSetupFeeIsNull();


  /** 
   * <em>transfered_asset_setup_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferedAssetSetupFeeIsSet();


  /** 
   * <em>transfered_asset_setup_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferedAssetSetupFeeIsModified();


  /** 
   * <em>transfered_asset_setup_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTransferedAssetSetupFeeTax();


  /** 
   * <em>transfered_asset_setup_fee_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTransferedAssetSetupFeeTaxIsNull();


  /** 
   * <em>transfered_asset_setup_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferedAssetSetupFeeTaxIsSet();


  /** 
   * <em>transfered_asset_setup_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferedAssetSetupFeeTaxIsModified();


  /** 
   * <em>transfered_asset_book_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTransferedAssetBookValue();


  /** 
   * <em>transfered_asset_book_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTransferedAssetBookValueIsNull();


  /** 
   * <em>transfered_asset_book_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferedAssetBookValueIsSet();


  /** 
   * <em>transfered_asset_book_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferedAssetBookValueIsModified();


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
   * <em>delete_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDeleteFlag();


  /** 
   * <em>delete_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeleteFlagIsSet();


  /** 
   * <em>delete_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeleteFlagIsModified();


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
