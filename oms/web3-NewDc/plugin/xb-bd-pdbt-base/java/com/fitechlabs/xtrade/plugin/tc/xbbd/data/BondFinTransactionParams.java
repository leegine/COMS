head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.59.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondFinTransactionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * bond_fin_transactionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BondFinTransactionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BondFinTransactionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BondFinTransactionParams}が{@@link BondFinTransactionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondFinTransactionPK 
 * @@see BondFinTransactionRow 
 */
public class BondFinTransactionParams extends Params implements BondFinTransactionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bond_fin_transaction";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BondFinTransactionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BondFinTransactionRow.TYPE;
  }


  /** 
   * <em>fin_transaction_id</em>カラムの値 
   */
  public  long  fin_transaction_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>カラムの値 
   */
  public  long  sub_account_id;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>fin_transaction_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType  fin_transaction_type;

  /** 
   * <em>fin_transaction_categ</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg  fin_transaction_categ;

  /** 
   * <em>fin_transaction_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  fin_transaction_timestamp;

  /** 
   * <em>tax_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum  tax_type;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>net_amount</em>カラムの値 
   */
  public  double  net_amount;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  Long  market_id;

  /** 
   * <em>price</em>カラムの値 
   */
  public  Double  price;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  double  quantity;

  /** 
   * <em>order_id</em>カラムの値 
   */
  public  Long  order_id;

  /** 
   * <em>order_unit_id</em>カラムの値 
   */
  public  Long  order_unit_id;

  /** 
   * <em>order_execution_id</em>カラムの値 
   */
  public  Long  order_execution_id;

  /** 
   * <em>commission_fee</em>カラムの値 
   */
  public  double  commission_fee;

  /** 
   * <em>commission_fee_tax</em>カラムの値 
   */
  public  double  commission_fee_tax;

  /** 
   * <em>asset_id</em>カラムの値 
   */
  public  Long  asset_id;

  /** 
   * <em>capital_gain</em>カラムの値 
   */
  public  double  capital_gain;

  /** 
   * <em>capital_gain_tax</em>カラムの値 
   */
  public  double  capital_gain_tax;

  /** 
   * <em>transfered_asset_mng_fee</em>カラムの値 
   */
  public  Double  transfered_asset_mng_fee;

  /** 
   * <em>transfered_asset_mng_fee_tax</em>カラムの値 
   */
  public  Double  transfered_asset_mng_fee_tax;

  /** 
   * <em>transfered_asset_setup_fee</em>カラムの値 
   */
  public  Double  transfered_asset_setup_fee;

  /** 
   * <em>transfered_asset_setup_fee_tax</em>カラムの値 
   */
  public  Double  transfered_asset_setup_fee_tax;

  /** 
   * <em>transfered_asset_book_value</em>カラムの値 
   */
  public  Double  transfered_asset_book_value;

  /** 
   * <em>accrued_interest</em>カラムの値 
   */
  public  Double  accrued_interest;

  /** 
   * <em>delete_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  delete_flag;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean fin_transaction_id_is_set = false;
  private boolean fin_transaction_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean fin_transaction_type_is_set = false;
  private boolean fin_transaction_type_is_modified = false;
  private boolean fin_transaction_categ_is_set = false;
  private boolean fin_transaction_categ_is_modified = false;
  private boolean fin_transaction_timestamp_is_set = false;
  private boolean fin_transaction_timestamp_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean net_amount_is_set = false;
  private boolean net_amount_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean order_unit_id_is_set = false;
  private boolean order_unit_id_is_modified = false;
  private boolean order_execution_id_is_set = false;
  private boolean order_execution_id_is_modified = false;
  private boolean commission_fee_is_set = false;
  private boolean commission_fee_is_modified = false;
  private boolean commission_fee_tax_is_set = false;
  private boolean commission_fee_tax_is_modified = false;
  private boolean asset_id_is_set = false;
  private boolean asset_id_is_modified = false;
  private boolean capital_gain_is_set = false;
  private boolean capital_gain_is_modified = false;
  private boolean capital_gain_tax_is_set = false;
  private boolean capital_gain_tax_is_modified = false;
  private boolean transfered_asset_mng_fee_is_set = false;
  private boolean transfered_asset_mng_fee_is_modified = false;
  private boolean transfered_asset_mng_fee_tax_is_set = false;
  private boolean transfered_asset_mng_fee_tax_is_modified = false;
  private boolean transfered_asset_setup_fee_is_set = false;
  private boolean transfered_asset_setup_fee_is_modified = false;
  private boolean transfered_asset_setup_fee_tax_is_set = false;
  private boolean transfered_asset_setup_fee_tax_is_modified = false;
  private boolean transfered_asset_book_value_is_set = false;
  private boolean transfered_asset_book_value_is_modified = false;
  private boolean accrued_interest_is_set = false;
  private boolean accrued_interest_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "fin_transaction_id=" + fin_transaction_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "product_id=" +product_id
      + "," + "fin_transaction_type=" +fin_transaction_type
      + "," + "fin_transaction_categ=" +fin_transaction_categ
      + "," + "fin_transaction_timestamp=" +fin_transaction_timestamp
      + "," + "tax_type=" +tax_type
      + "," + "delivery_date=" +delivery_date
      + "," + "net_amount=" +net_amount
      + "," + "product_type=" +product_type
      + "," + "market_id=" +market_id
      + "," + "price=" +price
      + "," + "quantity=" +quantity
      + "," + "order_id=" +order_id
      + "," + "order_unit_id=" +order_unit_id
      + "," + "order_execution_id=" +order_execution_id
      + "," + "commission_fee=" +commission_fee
      + "," + "commission_fee_tax=" +commission_fee_tax
      + "," + "asset_id=" +asset_id
      + "," + "capital_gain=" +capital_gain
      + "," + "capital_gain_tax=" +capital_gain_tax
      + "," + "transfered_asset_mng_fee=" +transfered_asset_mng_fee
      + "," + "transfered_asset_mng_fee_tax=" +transfered_asset_mng_fee_tax
      + "," + "transfered_asset_setup_fee=" +transfered_asset_setup_fee
      + "," + "transfered_asset_setup_fee_tax=" +transfered_asset_setup_fee_tax
      + "," + "transfered_asset_book_value=" +transfered_asset_book_value
      + "," + "accrued_interest=" +accrued_interest
      + "," + "delete_flag=" +delete_flag
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のBondFinTransactionParamsオブジェクトを作成します。 
   */
  public BondFinTransactionParams() {
    fin_transaction_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBondFinTransactionRowオブジェクトの値を利用してBondFinTransactionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBondFinTransactionRowオブジェクト 
   */
  public BondFinTransactionParams( BondFinTransactionRow p_row )
  {
    fin_transaction_id = p_row.getFinTransactionId();
    fin_transaction_id_is_set = p_row.getFinTransactionIdIsSet();
    fin_transaction_id_is_modified = p_row.getFinTransactionIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    fin_transaction_type = p_row.getFinTransactionType();
    fin_transaction_type_is_set = p_row.getFinTransactionTypeIsSet();
    fin_transaction_type_is_modified = p_row.getFinTransactionTypeIsModified();
    fin_transaction_categ = p_row.getFinTransactionCateg();
    fin_transaction_categ_is_set = p_row.getFinTransactionCategIsSet();
    fin_transaction_categ_is_modified = p_row.getFinTransactionCategIsModified();
    fin_transaction_timestamp = p_row.getFinTransactionTimestamp();
    fin_transaction_timestamp_is_set = p_row.getFinTransactionTimestampIsSet();
    fin_transaction_timestamp_is_modified = p_row.getFinTransactionTimestampIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    net_amount = p_row.getNetAmount();
    net_amount_is_set = p_row.getNetAmountIsSet();
    net_amount_is_modified = p_row.getNetAmountIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    if ( !p_row.getMarketIdIsNull() )
      market_id = new Long( p_row.getMarketId() );
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    if ( !p_row.getPriceIsNull() )
      price = new Double( p_row.getPrice() );
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    quantity = p_row.getQuantity();
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    if ( !p_row.getOrderIdIsNull() )
      order_id = new Long( p_row.getOrderId() );
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    if ( !p_row.getOrderUnitIdIsNull() )
      order_unit_id = new Long( p_row.getOrderUnitId() );
    order_unit_id_is_set = p_row.getOrderUnitIdIsSet();
    order_unit_id_is_modified = p_row.getOrderUnitIdIsModified();
    if ( !p_row.getOrderExecutionIdIsNull() )
      order_execution_id = new Long( p_row.getOrderExecutionId() );
    order_execution_id_is_set = p_row.getOrderExecutionIdIsSet();
    order_execution_id_is_modified = p_row.getOrderExecutionIdIsModified();
    commission_fee = p_row.getCommissionFee();
    commission_fee_is_set = p_row.getCommissionFeeIsSet();
    commission_fee_is_modified = p_row.getCommissionFeeIsModified();
    commission_fee_tax = p_row.getCommissionFeeTax();
    commission_fee_tax_is_set = p_row.getCommissionFeeTaxIsSet();
    commission_fee_tax_is_modified = p_row.getCommissionFeeTaxIsModified();
    if ( !p_row.getAssetIdIsNull() )
      asset_id = new Long( p_row.getAssetId() );
    asset_id_is_set = p_row.getAssetIdIsSet();
    asset_id_is_modified = p_row.getAssetIdIsModified();
    capital_gain = p_row.getCapitalGain();
    capital_gain_is_set = p_row.getCapitalGainIsSet();
    capital_gain_is_modified = p_row.getCapitalGainIsModified();
    capital_gain_tax = p_row.getCapitalGainTax();
    capital_gain_tax_is_set = p_row.getCapitalGainTaxIsSet();
    capital_gain_tax_is_modified = p_row.getCapitalGainTaxIsModified();
    if ( !p_row.getTransferedAssetMngFeeIsNull() )
      transfered_asset_mng_fee = new Double( p_row.getTransferedAssetMngFee() );
    transfered_asset_mng_fee_is_set = p_row.getTransferedAssetMngFeeIsSet();
    transfered_asset_mng_fee_is_modified = p_row.getTransferedAssetMngFeeIsModified();
    if ( !p_row.getTransferedAssetMngFeeTaxIsNull() )
      transfered_asset_mng_fee_tax = new Double( p_row.getTransferedAssetMngFeeTax() );
    transfered_asset_mng_fee_tax_is_set = p_row.getTransferedAssetMngFeeTaxIsSet();
    transfered_asset_mng_fee_tax_is_modified = p_row.getTransferedAssetMngFeeTaxIsModified();
    if ( !p_row.getTransferedAssetSetupFeeIsNull() )
      transfered_asset_setup_fee = new Double( p_row.getTransferedAssetSetupFee() );
    transfered_asset_setup_fee_is_set = p_row.getTransferedAssetSetupFeeIsSet();
    transfered_asset_setup_fee_is_modified = p_row.getTransferedAssetSetupFeeIsModified();
    if ( !p_row.getTransferedAssetSetupFeeTaxIsNull() )
      transfered_asset_setup_fee_tax = new Double( p_row.getTransferedAssetSetupFeeTax() );
    transfered_asset_setup_fee_tax_is_set = p_row.getTransferedAssetSetupFeeTaxIsSet();
    transfered_asset_setup_fee_tax_is_modified = p_row.getTransferedAssetSetupFeeTaxIsModified();
    if ( !p_row.getTransferedAssetBookValueIsNull() )
      transfered_asset_book_value = new Double( p_row.getTransferedAssetBookValue() );
    transfered_asset_book_value_is_set = p_row.getTransferedAssetBookValueIsSet();
    transfered_asset_book_value_is_modified = p_row.getTransferedAssetBookValueIsModified();
    if ( !p_row.getAccruedInterestIsNull() )
      accrued_interest = new Double( p_row.getAccruedInterest() );
    accrued_interest_is_set = p_row.getAccruedInterestIsSet();
    accrued_interest_is_modified = p_row.getAccruedInterestIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
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
      account_id_is_set = true;
      account_id_is_modified = true;
      sub_account_id_is_set = true;
      sub_account_id_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      fin_transaction_type_is_set = true;
      fin_transaction_type_is_modified = true;
      fin_transaction_categ_is_set = true;
      fin_transaction_categ_is_modified = true;
      fin_transaction_timestamp_is_set = true;
      fin_transaction_timestamp_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      net_amount_is_set = true;
      net_amount_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      order_unit_id_is_set = true;
      order_unit_id_is_modified = true;
      order_execution_id_is_set = true;
      order_execution_id_is_modified = true;
      commission_fee_is_set = true;
      commission_fee_is_modified = true;
      commission_fee_tax_is_set = true;
      commission_fee_tax_is_modified = true;
      asset_id_is_set = true;
      asset_id_is_modified = true;
      capital_gain_is_set = true;
      capital_gain_is_modified = true;
      capital_gain_tax_is_set = true;
      capital_gain_tax_is_modified = true;
      transfered_asset_mng_fee_is_set = true;
      transfered_asset_mng_fee_is_modified = true;
      transfered_asset_mng_fee_tax_is_set = true;
      transfered_asset_mng_fee_tax_is_modified = true;
      transfered_asset_setup_fee_is_set = true;
      transfered_asset_setup_fee_is_modified = true;
      transfered_asset_setup_fee_tax_is_set = true;
      transfered_asset_setup_fee_tax_is_modified = true;
      transfered_asset_book_value_is_set = true;
      transfered_asset_book_value_is_modified = true;
      accrued_interest_is_set = true;
      accrued_interest_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
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
    if ( !( other instanceof BondFinTransactionRow ) )
       return false;
    return fieldsEqual( (BondFinTransactionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBondFinTransactionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BondFinTransactionRow row )
  {
    if ( fin_transaction_id != row.getFinTransactionId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( product_id != row.getProductId() )
      return false;
    if ( fin_transaction_type == null ) {
      if ( row.getFinTransactionType() != null )
        return false;
    } else if ( !fin_transaction_type.equals( row.getFinTransactionType() ) ) {
        return false;
    }
    if ( fin_transaction_categ == null ) {
      if ( row.getFinTransactionCateg() != null )
        return false;
    } else if ( !fin_transaction_categ.equals( row.getFinTransactionCateg() ) ) {
        return false;
    }
    if ( fin_transaction_timestamp == null ) {
      if ( row.getFinTransactionTimestamp() != null )
        return false;
    } else if ( !fin_transaction_timestamp.equals( row.getFinTransactionTimestamp() ) ) {
        return false;
    }
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( net_amount != row.getNetAmount() )
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
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.doubleValue() != row.getPrice() ) ) {
        return false;
    }
    if ( quantity != row.getQuantity() )
      return false;
    if ( order_id == null ) {
      if ( !row.getOrderIdIsNull() )
        return false;
    } else if ( row.getOrderIdIsNull() || ( order_id.longValue() != row.getOrderId() ) ) {
        return false;
    }
    if ( order_unit_id == null ) {
      if ( !row.getOrderUnitIdIsNull() )
        return false;
    } else if ( row.getOrderUnitIdIsNull() || ( order_unit_id.longValue() != row.getOrderUnitId() ) ) {
        return false;
    }
    if ( order_execution_id == null ) {
      if ( !row.getOrderExecutionIdIsNull() )
        return false;
    } else if ( row.getOrderExecutionIdIsNull() || ( order_execution_id.longValue() != row.getOrderExecutionId() ) ) {
        return false;
    }
    if ( commission_fee != row.getCommissionFee() )
      return false;
    if ( commission_fee_tax != row.getCommissionFeeTax() )
      return false;
    if ( asset_id == null ) {
      if ( !row.getAssetIdIsNull() )
        return false;
    } else if ( row.getAssetIdIsNull() || ( asset_id.longValue() != row.getAssetId() ) ) {
        return false;
    }
    if ( capital_gain != row.getCapitalGain() )
      return false;
    if ( capital_gain_tax != row.getCapitalGainTax() )
      return false;
    if ( transfered_asset_mng_fee == null ) {
      if ( !row.getTransferedAssetMngFeeIsNull() )
        return false;
    } else if ( row.getTransferedAssetMngFeeIsNull() || ( transfered_asset_mng_fee.doubleValue() != row.getTransferedAssetMngFee() ) ) {
        return false;
    }
    if ( transfered_asset_mng_fee_tax == null ) {
      if ( !row.getTransferedAssetMngFeeTaxIsNull() )
        return false;
    } else if ( row.getTransferedAssetMngFeeTaxIsNull() || ( transfered_asset_mng_fee_tax.doubleValue() != row.getTransferedAssetMngFeeTax() ) ) {
        return false;
    }
    if ( transfered_asset_setup_fee == null ) {
      if ( !row.getTransferedAssetSetupFeeIsNull() )
        return false;
    } else if ( row.getTransferedAssetSetupFeeIsNull() || ( transfered_asset_setup_fee.doubleValue() != row.getTransferedAssetSetupFee() ) ) {
        return false;
    }
    if ( transfered_asset_setup_fee_tax == null ) {
      if ( !row.getTransferedAssetSetupFeeTaxIsNull() )
        return false;
    } else if ( row.getTransferedAssetSetupFeeTaxIsNull() || ( transfered_asset_setup_fee_tax.doubleValue() != row.getTransferedAssetSetupFeeTax() ) ) {
        return false;
    }
    if ( transfered_asset_book_value == null ) {
      if ( !row.getTransferedAssetBookValueIsNull() )
        return false;
    } else if ( row.getTransferedAssetBookValueIsNull() || ( transfered_asset_book_value.doubleValue() != row.getTransferedAssetBookValue() ) ) {
        return false;
    }
    if ( accrued_interest == null ) {
      if ( !row.getAccruedInterestIsNull() )
        return false;
    } else if ( row.getAccruedInterestIsNull() || ( accrued_interest.doubleValue() != row.getAccruedInterest() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
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
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) fin_transaction_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) product_id)
        + (fin_transaction_type!=null? fin_transaction_type.hashCode(): 0) 
        + (fin_transaction_categ!=null? fin_transaction_categ.hashCode(): 0) 
        + (fin_transaction_timestamp!=null? fin_transaction_timestamp.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + ((int) net_amount)
        + (product_type!=null? product_type.hashCode(): 0) 
        + (market_id!=null? market_id.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + ((int) quantity)
        + (order_id!=null? order_id.hashCode(): 0) 
        + (order_unit_id!=null? order_unit_id.hashCode(): 0) 
        + (order_execution_id!=null? order_execution_id.hashCode(): 0) 
        + ((int) commission_fee)
        + ((int) commission_fee_tax)
        + (asset_id!=null? asset_id.hashCode(): 0) 
        + ((int) capital_gain)
        + ((int) capital_gain_tax)
        + (transfered_asset_mng_fee!=null? transfered_asset_mng_fee.hashCode(): 0) 
        + (transfered_asset_mng_fee_tax!=null? transfered_asset_mng_fee_tax.hashCode(): 0) 
        + (transfered_asset_setup_fee!=null? transfered_asset_setup_fee.hashCode(): 0) 
        + (transfered_asset_setup_fee_tax!=null? transfered_asset_setup_fee_tax.hashCode(): 0) 
        + (transfered_asset_book_value!=null? transfered_asset_book_value.hashCode(): 0) 
        + (accrued_interest!=null? accrued_interest.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
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
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !fin_transaction_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_transaction_type' must be set before inserting.");
		if ( !fin_transaction_categ_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_transaction_categ' must be set before inserting.");
		if ( !fin_transaction_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_transaction_timestamp' must be set before inserting.");
		if ( !tax_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'tax_type' must be set before inserting.");
		if ( !delivery_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_date' must be set before inserting.");
		if ( !net_amount_is_set )
			throw new IllegalArgumentException("non-nullable field 'net_amount' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
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
		map.put("fin_transaction_id",new Long(fin_transaction_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("product_id",new Long(product_id));
		map.put("fin_transaction_type",fin_transaction_type);
		map.put("fin_transaction_categ",fin_transaction_categ);
		map.put("fin_transaction_timestamp",fin_transaction_timestamp);
		map.put("tax_type",tax_type);
		map.put("delivery_date",delivery_date);
		map.put("net_amount",new Double(net_amount));
		map.put("product_type",product_type);
		if ( market_id != null )
			map.put("market_id",market_id);
		if ( price != null )
			map.put("price",price);
		map.put("quantity",new Double(quantity));
		if ( order_id != null )
			map.put("order_id",order_id);
		if ( order_unit_id != null )
			map.put("order_unit_id",order_unit_id);
		if ( order_execution_id != null )
			map.put("order_execution_id",order_execution_id);
		if ( commission_fee_is_set )
			map.put("commission_fee",new Double(commission_fee));
		if ( commission_fee_tax_is_set )
			map.put("commission_fee_tax",new Double(commission_fee_tax));
		if ( asset_id != null )
			map.put("asset_id",asset_id);
		if ( capital_gain_is_set )
			map.put("capital_gain",new Double(capital_gain));
		if ( capital_gain_tax_is_set )
			map.put("capital_gain_tax",new Double(capital_gain_tax));
		if ( transfered_asset_mng_fee_is_set )
			map.put("transfered_asset_mng_fee",transfered_asset_mng_fee);
		if ( transfered_asset_mng_fee_tax_is_set )
			map.put("transfered_asset_mng_fee_tax",transfered_asset_mng_fee_tax);
		if ( transfered_asset_setup_fee_is_set )
			map.put("transfered_asset_setup_fee",transfered_asset_setup_fee);
		if ( transfered_asset_setup_fee_tax_is_set )
			map.put("transfered_asset_setup_fee_tax",transfered_asset_setup_fee_tax);
		if ( transfered_asset_book_value != null )
			map.put("transfered_asset_book_value",transfered_asset_book_value);
		if ( accrued_interest != null )
			map.put("accrued_interest",accrued_interest);
		if ( delete_flag_is_set )
			map.put("delete_flag",delete_flag);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
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
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( fin_transaction_type_is_modified )
			map.put("fin_transaction_type",fin_transaction_type);
		if ( fin_transaction_categ_is_modified )
			map.put("fin_transaction_categ",fin_transaction_categ);
		if ( fin_transaction_timestamp_is_modified )
			map.put("fin_transaction_timestamp",fin_transaction_timestamp);
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( net_amount_is_modified )
			map.put("net_amount",new Double(net_amount));
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( market_id_is_modified )
			map.put("market_id",market_id);
		if ( price_is_modified )
			map.put("price",price);
		if ( quantity_is_modified )
			map.put("quantity",new Double(quantity));
		if ( order_id_is_modified )
			map.put("order_id",order_id);
		if ( order_unit_id_is_modified )
			map.put("order_unit_id",order_unit_id);
		if ( order_execution_id_is_modified )
			map.put("order_execution_id",order_execution_id);
		if ( commission_fee_is_modified )
			map.put("commission_fee",new Double(commission_fee));
		if ( commission_fee_tax_is_modified )
			map.put("commission_fee_tax",new Double(commission_fee_tax));
		if ( asset_id_is_modified )
			map.put("asset_id",asset_id);
		if ( capital_gain_is_modified )
			map.put("capital_gain",new Double(capital_gain));
		if ( capital_gain_tax_is_modified )
			map.put("capital_gain_tax",new Double(capital_gain_tax));
		if ( transfered_asset_mng_fee_is_modified )
			map.put("transfered_asset_mng_fee",transfered_asset_mng_fee);
		if ( transfered_asset_mng_fee_tax_is_modified )
			map.put("transfered_asset_mng_fee_tax",transfered_asset_mng_fee_tax);
		if ( transfered_asset_setup_fee_is_modified )
			map.put("transfered_asset_setup_fee",transfered_asset_setup_fee);
		if ( transfered_asset_setup_fee_tax_is_modified )
			map.put("transfered_asset_setup_fee_tax",transfered_asset_setup_fee_tax);
		if ( transfered_asset_book_value_is_modified )
			map.put("transfered_asset_book_value",transfered_asset_book_value);
		if ( accrued_interest_is_modified )
			map.put("accrued_interest",accrued_interest);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( fin_transaction_type_is_set )
				map.put("fin_transaction_type",fin_transaction_type);
			if ( fin_transaction_categ_is_set )
				map.put("fin_transaction_categ",fin_transaction_categ);
			if ( fin_transaction_timestamp_is_set )
				map.put("fin_transaction_timestamp",fin_transaction_timestamp);
			if ( tax_type_is_set )
				map.put("tax_type",tax_type);
			if ( delivery_date_is_set )
				map.put("delivery_date",delivery_date);
			if ( net_amount_is_set )
				map.put("net_amount",new Double(net_amount));
			if ( product_type_is_set )
				map.put("product_type",product_type);
			map.put("market_id",market_id);
			map.put("price",price);
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			map.put("order_id",order_id);
			map.put("order_unit_id",order_unit_id);
			map.put("order_execution_id",order_execution_id);
			if ( commission_fee_is_set )
				map.put("commission_fee",new Double(commission_fee));
			if ( commission_fee_tax_is_set )
				map.put("commission_fee_tax",new Double(commission_fee_tax));
			map.put("asset_id",asset_id);
			if ( capital_gain_is_set )
				map.put("capital_gain",new Double(capital_gain));
			if ( capital_gain_tax_is_set )
				map.put("capital_gain_tax",new Double(capital_gain_tax));
			if ( transfered_asset_mng_fee_is_set )
				map.put("transfered_asset_mng_fee",transfered_asset_mng_fee);
			if ( transfered_asset_mng_fee_tax_is_set )
				map.put("transfered_asset_mng_fee_tax",transfered_asset_mng_fee_tax);
			if ( transfered_asset_setup_fee_is_set )
				map.put("transfered_asset_setup_fee",transfered_asset_setup_fee);
			if ( transfered_asset_setup_fee_tax_is_set )
				map.put("transfered_asset_setup_fee_tax",transfered_asset_setup_fee_tax);
			map.put("transfered_asset_book_value",transfered_asset_book_value);
			map.put("accrued_interest",accrued_interest);
			if ( delete_flag_is_set )
				map.put("delete_flag",delete_flag);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>fin_transaction_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFinTransactionId()
  {
    return fin_transaction_id;
  }


  /** 
   * <em>fin_transaction_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionIdIsSet() {
    return fin_transaction_id_is_set;
  }


  /** 
   * <em>fin_transaction_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionIdIsModified() {
    return fin_transaction_id_is_modified;
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
   * <em>fin_transaction_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionTypeの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType getFinTransactionType()
  {
    return fin_transaction_type;
  }


  /** 
   * <em>fin_transaction_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionTypeIsSet() {
    return fin_transaction_type_is_set;
  }


  /** 
   * <em>fin_transaction_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionTypeIsModified() {
    return fin_transaction_type_is_modified;
  }


  /** 
   * <em>fin_transaction_categ</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCategの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg getFinTransactionCateg()
  {
    return fin_transaction_categ;
  }


  /** 
   * <em>fin_transaction_categ</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionCategIsSet() {
    return fin_transaction_categ_is_set;
  }


  /** 
   * <em>fin_transaction_categ</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionCategIsModified() {
    return fin_transaction_categ_is_modified;
  }


  /** 
   * <em>fin_transaction_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getFinTransactionTimestamp()
  {
    return fin_transaction_timestamp;
  }


  /** 
   * <em>fin_transaction_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionTimestampIsSet() {
    return fin_transaction_timestamp_is_set;
  }


  /** 
   * <em>fin_transaction_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionTimestampIsModified() {
    return fin_transaction_timestamp_is_modified;
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
   * <em>net_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNetAmount()
  {
    return net_amount;
  }


  /** 
   * <em>net_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetAmountIsSet() {
    return net_amount_is_set;
  }


  /** 
   * <em>net_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetAmountIsModified() {
    return net_amount_is_modified;
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
   * <em>order_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrderId()
  {
    return ( order_id==null? ((long)0): order_id.longValue() );
  }


  /** 
   * <em>order_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrderIdIsNull()
  {
    return order_id == null;
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
   * <em>order_execution_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrderExecutionId()
  {
    return ( order_execution_id==null? ((long)0): order_execution_id.longValue() );
  }


  /** 
   * <em>order_execution_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrderExecutionIdIsNull()
  {
    return order_execution_id == null;
  }


  /** 
   * <em>order_execution_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderExecutionIdIsSet() {
    return order_execution_id_is_set;
  }


  /** 
   * <em>order_execution_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderExecutionIdIsModified() {
    return order_execution_id_is_modified;
  }


  /** 
   * <em>commission_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionFee()
  {
    return commission_fee;
  }


  /** 
   * <em>commission_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeIsSet() {
    return commission_fee_is_set;
  }


  /** 
   * <em>commission_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeIsModified() {
    return commission_fee_is_modified;
  }


  /** 
   * <em>commission_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionFeeTax()
  {
    return commission_fee_tax;
  }


  /** 
   * <em>commission_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeTaxIsSet() {
    return commission_fee_tax_is_set;
  }


  /** 
   * <em>commission_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeTaxIsModified() {
    return commission_fee_tax_is_modified;
  }


  /** 
   * <em>asset_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAssetId()
  {
    return ( asset_id==null? ((long)0): asset_id.longValue() );
  }


  /** 
   * <em>asset_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAssetIdIsNull()
  {
    return asset_id == null;
  }


  /** 
   * <em>asset_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetIdIsSet() {
    return asset_id_is_set;
  }


  /** 
   * <em>asset_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetIdIsModified() {
    return asset_id_is_modified;
  }


  /** 
   * <em>capital_gain</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCapitalGain()
  {
    return capital_gain;
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
    return capital_gain_tax;
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
   * <em>transfered_asset_mng_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTransferedAssetMngFee()
  {
    return ( transfered_asset_mng_fee==null? ((double)0): transfered_asset_mng_fee.doubleValue() );
  }


  /** 
   * <em>transfered_asset_mng_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTransferedAssetMngFeeIsNull()
  {
    return transfered_asset_mng_fee == null;
  }


  /** 
   * <em>transfered_asset_mng_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferedAssetMngFeeIsSet() {
    return transfered_asset_mng_fee_is_set;
  }


  /** 
   * <em>transfered_asset_mng_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferedAssetMngFeeIsModified() {
    return transfered_asset_mng_fee_is_modified;
  }


  /** 
   * <em>transfered_asset_mng_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTransferedAssetMngFeeTax()
  {
    return ( transfered_asset_mng_fee_tax==null? ((double)0): transfered_asset_mng_fee_tax.doubleValue() );
  }


  /** 
   * <em>transfered_asset_mng_fee_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTransferedAssetMngFeeTaxIsNull()
  {
    return transfered_asset_mng_fee_tax == null;
  }


  /** 
   * <em>transfered_asset_mng_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferedAssetMngFeeTaxIsSet() {
    return transfered_asset_mng_fee_tax_is_set;
  }


  /** 
   * <em>transfered_asset_mng_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferedAssetMngFeeTaxIsModified() {
    return transfered_asset_mng_fee_tax_is_modified;
  }


  /** 
   * <em>transfered_asset_setup_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTransferedAssetSetupFee()
  {
    return ( transfered_asset_setup_fee==null? ((double)0): transfered_asset_setup_fee.doubleValue() );
  }


  /** 
   * <em>transfered_asset_setup_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTransferedAssetSetupFeeIsNull()
  {
    return transfered_asset_setup_fee == null;
  }


  /** 
   * <em>transfered_asset_setup_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferedAssetSetupFeeIsSet() {
    return transfered_asset_setup_fee_is_set;
  }


  /** 
   * <em>transfered_asset_setup_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferedAssetSetupFeeIsModified() {
    return transfered_asset_setup_fee_is_modified;
  }


  /** 
   * <em>transfered_asset_setup_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTransferedAssetSetupFeeTax()
  {
    return ( transfered_asset_setup_fee_tax==null? ((double)0): transfered_asset_setup_fee_tax.doubleValue() );
  }


  /** 
   * <em>transfered_asset_setup_fee_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTransferedAssetSetupFeeTaxIsNull()
  {
    return transfered_asset_setup_fee_tax == null;
  }


  /** 
   * <em>transfered_asset_setup_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferedAssetSetupFeeTaxIsSet() {
    return transfered_asset_setup_fee_tax_is_set;
  }


  /** 
   * <em>transfered_asset_setup_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferedAssetSetupFeeTaxIsModified() {
    return transfered_asset_setup_fee_tax_is_modified;
  }


  /** 
   * <em>transfered_asset_book_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTransferedAssetBookValue()
  {
    return ( transfered_asset_book_value==null? ((double)0): transfered_asset_book_value.doubleValue() );
  }


  /** 
   * <em>transfered_asset_book_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTransferedAssetBookValueIsNull()
  {
    return transfered_asset_book_value == null;
  }


  /** 
   * <em>transfered_asset_book_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferedAssetBookValueIsSet() {
    return transfered_asset_book_value_is_set;
  }


  /** 
   * <em>transfered_asset_book_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferedAssetBookValueIsModified() {
    return transfered_asset_book_value_is_modified;
  }


  /** 
   * <em>accrued_interest</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAccruedInterest()
  {
    return ( accrued_interest==null? ((double)0): accrued_interest.doubleValue() );
  }


  /** 
   * <em>accrued_interest</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccruedInterestIsNull()
  {
    return accrued_interest == null;
  }


  /** 
   * <em>accrued_interest</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccruedInterestIsSet() {
    return accrued_interest_is_set;
  }


  /** 
   * <em>accrued_interest</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccruedInterestIsModified() {
    return accrued_interest_is_modified;
  }


  /** 
   * <em>delete_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDeleteFlag()
  {
    return delete_flag;
  }


  /** 
   * <em>delete_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsSet() {
    return delete_flag_is_set;
  }


  /** 
   * <em>delete_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsModified() {
    return delete_flag_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new BondFinTransactionPK(fin_transaction_id);
  }


  /** 
   * <em>fin_transaction_id</em>カラムの値を設定します。 
   *
   * @@param p_finTransactionId <em>fin_transaction_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setFinTransactionId( long p_finTransactionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_transaction_id = p_finTransactionId;
    fin_transaction_id_is_set = true;
    fin_transaction_id_is_modified = true;
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
   * <em>fin_transaction_type</em>カラムの値を設定します。 
   *
   * @@param p_finTransactionType <em>fin_transaction_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType値 
   */
  public final void setFinTransactionType( com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType p_finTransactionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_transaction_type = p_finTransactionType;
    fin_transaction_type_is_set = true;
    fin_transaction_type_is_modified = true;
  }


  /** 
   * <em>fin_transaction_categ</em>カラムの値を設定します。 
   *
   * @@param p_finTransactionCateg <em>fin_transaction_categ</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg値 
   */
  public final void setFinTransactionCateg( com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg p_finTransactionCateg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_transaction_categ = p_finTransactionCateg;
    fin_transaction_categ_is_set = true;
    fin_transaction_categ_is_modified = true;
  }


  /** 
   * <em>fin_transaction_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_finTransactionTimestamp <em>fin_transaction_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setFinTransactionTimestamp( java.sql.Timestamp p_finTransactionTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_transaction_timestamp = p_finTransactionTimestamp;
    fin_transaction_timestamp_is_set = true;
    fin_transaction_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>fin_transaction_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setFinTransactionTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    fin_transaction_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    fin_transaction_timestamp_is_set = true;
    fin_transaction_timestamp_is_modified = true;
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
   * <em>net_amount</em>カラムの値を設定します。 
   *
   * @@param p_netAmount <em>net_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNetAmount( double p_netAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_amount = p_netAmount;
    net_amount_is_set = true;
    net_amount_is_modified = true;
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
   * <em>order_id</em>カラムの値を設定します。 
   *
   * @@param p_orderId <em>order_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setOrderId( long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = new Long(p_orderId);
    order_id_is_set = true;
    order_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>order_id</em>カラムに値を設定します。 
   */
  public final void setOrderId( Long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
    order_id_is_set = true;
    order_id_is_modified = true;
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
   * <em>order_execution_id</em>カラムの値を設定します。 
   *
   * @@param p_orderExecutionId <em>order_execution_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setOrderExecutionId( long p_orderExecutionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_execution_id = new Long(p_orderExecutionId);
    order_execution_id_is_set = true;
    order_execution_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>order_execution_id</em>カラムに値を設定します。 
   */
  public final void setOrderExecutionId( Long p_orderExecutionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_execution_id = p_orderExecutionId;
    order_execution_id_is_set = true;
    order_execution_id_is_modified = true;
  }


  /** 
   * <em>commission_fee</em>カラムの値を設定します。 
   *
   * @@param p_commissionFee <em>commission_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCommissionFee( double p_commissionFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_fee = p_commissionFee;
    commission_fee_is_set = true;
    commission_fee_is_modified = true;
  }


  /** 
   * <em>commission_fee_tax</em>カラムの値を設定します。 
   *
   * @@param p_commissionFeeTax <em>commission_fee_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCommissionFeeTax( double p_commissionFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_fee_tax = p_commissionFeeTax;
    commission_fee_tax_is_set = true;
    commission_fee_tax_is_modified = true;
  }


  /** 
   * <em>asset_id</em>カラムの値を設定します。 
   *
   * @@param p_assetId <em>asset_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAssetId( long p_assetId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    asset_id = new Long(p_assetId);
    asset_id_is_set = true;
    asset_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>asset_id</em>カラムに値を設定します。 
   */
  public final void setAssetId( Long p_assetId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    asset_id = p_assetId;
    asset_id_is_set = true;
    asset_id_is_modified = true;
  }


  /** 
   * <em>capital_gain</em>カラムの値を設定します。 
   *
   * @@param p_capitalGain <em>capital_gain</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCapitalGain( double p_capitalGain )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
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
    capital_gain_tax = p_capitalGainTax;
    capital_gain_tax_is_set = true;
    capital_gain_tax_is_modified = true;
  }


  /** 
   * <em>transfered_asset_mng_fee</em>カラムの値を設定します。 
   *
   * @@param p_transferedAssetMngFee <em>transfered_asset_mng_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTransferedAssetMngFee( double p_transferedAssetMngFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfered_asset_mng_fee = new Double(p_transferedAssetMngFee);
    transfered_asset_mng_fee_is_set = true;
    transfered_asset_mng_fee_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>transfered_asset_mng_fee</em>カラムに値を設定します。 
   */
  public final void setTransferedAssetMngFee( Double p_transferedAssetMngFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfered_asset_mng_fee = p_transferedAssetMngFee;
    transfered_asset_mng_fee_is_set = true;
    transfered_asset_mng_fee_is_modified = true;
  }


  /** 
   * <em>transfered_asset_mng_fee_tax</em>カラムの値を設定します。 
   *
   * @@param p_transferedAssetMngFeeTax <em>transfered_asset_mng_fee_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTransferedAssetMngFeeTax( double p_transferedAssetMngFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfered_asset_mng_fee_tax = new Double(p_transferedAssetMngFeeTax);
    transfered_asset_mng_fee_tax_is_set = true;
    transfered_asset_mng_fee_tax_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>transfered_asset_mng_fee_tax</em>カラムに値を設定します。 
   */
  public final void setTransferedAssetMngFeeTax( Double p_transferedAssetMngFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfered_asset_mng_fee_tax = p_transferedAssetMngFeeTax;
    transfered_asset_mng_fee_tax_is_set = true;
    transfered_asset_mng_fee_tax_is_modified = true;
  }


  /** 
   * <em>transfered_asset_setup_fee</em>カラムの値を設定します。 
   *
   * @@param p_transferedAssetSetupFee <em>transfered_asset_setup_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTransferedAssetSetupFee( double p_transferedAssetSetupFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfered_asset_setup_fee = new Double(p_transferedAssetSetupFee);
    transfered_asset_setup_fee_is_set = true;
    transfered_asset_setup_fee_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>transfered_asset_setup_fee</em>カラムに値を設定します。 
   */
  public final void setTransferedAssetSetupFee( Double p_transferedAssetSetupFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfered_asset_setup_fee = p_transferedAssetSetupFee;
    transfered_asset_setup_fee_is_set = true;
    transfered_asset_setup_fee_is_modified = true;
  }


  /** 
   * <em>transfered_asset_setup_fee_tax</em>カラムの値を設定します。 
   *
   * @@param p_transferedAssetSetupFeeTax <em>transfered_asset_setup_fee_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTransferedAssetSetupFeeTax( double p_transferedAssetSetupFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfered_asset_setup_fee_tax = new Double(p_transferedAssetSetupFeeTax);
    transfered_asset_setup_fee_tax_is_set = true;
    transfered_asset_setup_fee_tax_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>transfered_asset_setup_fee_tax</em>カラムに値を設定します。 
   */
  public final void setTransferedAssetSetupFeeTax( Double p_transferedAssetSetupFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfered_asset_setup_fee_tax = p_transferedAssetSetupFeeTax;
    transfered_asset_setup_fee_tax_is_set = true;
    transfered_asset_setup_fee_tax_is_modified = true;
  }


  /** 
   * <em>transfered_asset_book_value</em>カラムの値を設定します。 
   *
   * @@param p_transferedAssetBookValue <em>transfered_asset_book_value</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTransferedAssetBookValue( double p_transferedAssetBookValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfered_asset_book_value = new Double(p_transferedAssetBookValue);
    transfered_asset_book_value_is_set = true;
    transfered_asset_book_value_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>transfered_asset_book_value</em>カラムに値を設定します。 
   */
  public final void setTransferedAssetBookValue( Double p_transferedAssetBookValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfered_asset_book_value = p_transferedAssetBookValue;
    transfered_asset_book_value_is_set = true;
    transfered_asset_book_value_is_modified = true;
  }


  /** 
   * <em>accrued_interest</em>カラムの値を設定します。 
   *
   * @@param p_accruedInterest <em>accrued_interest</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAccruedInterest( double p_accruedInterest )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    accrued_interest = new Double(p_accruedInterest);
    accrued_interest_is_set = true;
    accrued_interest_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>accrued_interest</em>カラムに値を設定します。 
   */
  public final void setAccruedInterest( Double p_accruedInterest )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    accrued_interest = p_accruedInterest;
    accrued_interest_is_set = true;
    accrued_interest_is_modified = true;
  }


  /** 
   * <em>delete_flag</em>カラムの値を設定します。 
   *
   * @@param p_deleteFlag <em>delete_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setDeleteFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = p_deleteFlag;
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
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
                else if ( name.equals("asset_id") ) {
                    return this.asset_id;
                }
                else if ( name.equals("accrued_interest") ) {
                    return this.accrued_interest;
                }
                break;
            case 'c':
                if ( name.equals("commission_fee") ) {
                    return new Double( commission_fee );
                }
                else if ( name.equals("commission_fee_tax") ) {
                    return new Double( commission_fee_tax );
                }
                else if ( name.equals("capital_gain") ) {
                    return new Double( capital_gain );
                }
                else if ( name.equals("capital_gain_tax") ) {
                    return new Double( capital_gain_tax );
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                else if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                break;
            case 'f':
                if ( name.equals("fin_transaction_id") ) {
                    return new Long( fin_transaction_id );
                }
                else if ( name.equals("fin_transaction_type") ) {
                    return this.fin_transaction_type;
                }
                else if ( name.equals("fin_transaction_categ") ) {
                    return this.fin_transaction_categ;
                }
                else if ( name.equals("fin_transaction_timestamp") ) {
                    return this.fin_transaction_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return this.market_id;
                }
                break;
            case 'n':
                if ( name.equals("net_amount") ) {
                    return new Double( net_amount );
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    return this.order_id;
                }
                else if ( name.equals("order_unit_id") ) {
                    return this.order_unit_id;
                }
                else if ( name.equals("order_execution_id") ) {
                    return this.order_execution_id;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("product_type") ) {
                    return this.product_type;
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
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                break;
            case 't':
                if ( name.equals("tax_type") ) {
                    return this.tax_type;
                }
                else if ( name.equals("transfered_asset_mng_fee") ) {
                    return this.transfered_asset_mng_fee;
                }
                else if ( name.equals("transfered_asset_mng_fee_tax") ) {
                    return this.transfered_asset_mng_fee_tax;
                }
                else if ( name.equals("transfered_asset_setup_fee") ) {
                    return this.transfered_asset_setup_fee;
                }
                else if ( name.equals("transfered_asset_setup_fee_tax") ) {
                    return this.transfered_asset_setup_fee_tax;
                }
                else if ( name.equals("transfered_asset_book_value") ) {
                    return this.transfered_asset_book_value;
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
                else if ( name.equals("asset_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'asset_id' must be Long: '"+value+"' is not." );
                    this.asset_id = (Long) value;
                    if (this.asset_id_is_set)
                        this.asset_id_is_modified = true;
                    this.asset_id_is_set = true;
                    return;
                }
                else if ( name.equals("accrued_interest") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'accrued_interest' must be Double: '"+value+"' is not." );
                    this.accrued_interest = (Double) value;
                    if (this.accrued_interest_is_set)
                        this.accrued_interest_is_modified = true;
                    this.accrued_interest_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("commission_fee") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_fee' must be Double: '"+value+"' is not." );
                    this.commission_fee = ((Double) value).doubleValue();
                    if (this.commission_fee_is_set)
                        this.commission_fee_is_modified = true;
                    this.commission_fee_is_set = true;
                    return;
                }
                else if ( name.equals("commission_fee_tax") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_fee_tax' must be Double: '"+value+"' is not." );
                    this.commission_fee_tax = ((Double) value).doubleValue();
                    if (this.commission_fee_tax_is_set)
                        this.commission_fee_tax_is_modified = true;
                    this.commission_fee_tax_is_set = true;
                    return;
                }
                else if ( name.equals("capital_gain") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'capital_gain' must be Double: '"+value+"' is not." );
                    this.capital_gain = ((Double) value).doubleValue();
                    if (this.capital_gain_is_set)
                        this.capital_gain_is_modified = true;
                    this.capital_gain_is_set = true;
                    return;
                }
                else if ( name.equals("capital_gain_tax") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'capital_gain_tax' must be Double: '"+value+"' is not." );
                    this.capital_gain_tax = ((Double) value).doubleValue();
                    if (this.capital_gain_tax_is_set)
                        this.capital_gain_tax_is_modified = true;
                    this.capital_gain_tax_is_set = true;
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
                else if ( name.equals("delete_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.delete_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fin_transaction_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'fin_transaction_id' must be Long: '"+value+"' is not." );
                    this.fin_transaction_id = ((Long) value).longValue();
                    if (this.fin_transaction_id_is_set)
                        this.fin_transaction_id_is_modified = true;
                    this.fin_transaction_id_is_set = true;
                    return;
                }
                else if ( name.equals("fin_transaction_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType) )
                        throw new IllegalArgumentException( "value for 'fin_transaction_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType: '"+value+"' is not." );
                    this.fin_transaction_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType) value;
                    if (this.fin_transaction_type_is_set)
                        this.fin_transaction_type_is_modified = true;
                    this.fin_transaction_type_is_set = true;
                    return;
                }
                else if ( name.equals("fin_transaction_categ") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg) )
                        throw new IllegalArgumentException( "value for 'fin_transaction_categ' must be com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg: '"+value+"' is not." );
                    this.fin_transaction_categ = (com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg) value;
                    if (this.fin_transaction_categ_is_set)
                        this.fin_transaction_categ_is_modified = true;
                    this.fin_transaction_categ_is_set = true;
                    return;
                }
                else if ( name.equals("fin_transaction_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'fin_transaction_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.fin_transaction_timestamp = (java.sql.Timestamp) value;
                    if (this.fin_transaction_timestamp_is_set)
                        this.fin_transaction_timestamp_is_modified = true;
                    this.fin_transaction_timestamp_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
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
            case 'n':
                if ( name.equals("net_amount") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'net_amount' must be Double: '"+value+"' is not." );
                    this.net_amount = ((Double) value).doubleValue();
                    if (this.net_amount_is_set)
                        this.net_amount_is_modified = true;
                    this.net_amount_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = (Long) value;
                    if (this.order_id_is_set)
                        this.order_id_is_modified = true;
                    this.order_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_unit_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_unit_id' must be Long: '"+value+"' is not." );
                    this.order_unit_id = (Long) value;
                    if (this.order_unit_id_is_set)
                        this.order_unit_id_is_modified = true;
                    this.order_unit_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_execution_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_execution_id' must be Long: '"+value+"' is not." );
                    this.order_execution_id = (Long) value;
                    if (this.order_execution_id_is_set)
                        this.order_execution_id_is_modified = true;
                    this.order_execution_id_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                else if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
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
                break;
            case 't':
                if ( name.equals("tax_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum: '"+value+"' is not." );
                    this.tax_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) value;
                    if (this.tax_type_is_set)
                        this.tax_type_is_modified = true;
                    this.tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("transfered_asset_mng_fee") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'transfered_asset_mng_fee' must be Double: '"+value+"' is not." );
                    this.transfered_asset_mng_fee = (Double) value;
                    if (this.transfered_asset_mng_fee_is_set)
                        this.transfered_asset_mng_fee_is_modified = true;
                    this.transfered_asset_mng_fee_is_set = true;
                    return;
                }
                else if ( name.equals("transfered_asset_mng_fee_tax") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'transfered_asset_mng_fee_tax' must be Double: '"+value+"' is not." );
                    this.transfered_asset_mng_fee_tax = (Double) value;
                    if (this.transfered_asset_mng_fee_tax_is_set)
                        this.transfered_asset_mng_fee_tax_is_modified = true;
                    this.transfered_asset_mng_fee_tax_is_set = true;
                    return;
                }
                else if ( name.equals("transfered_asset_setup_fee") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'transfered_asset_setup_fee' must be Double: '"+value+"' is not." );
                    this.transfered_asset_setup_fee = (Double) value;
                    if (this.transfered_asset_setup_fee_is_set)
                        this.transfered_asset_setup_fee_is_modified = true;
                    this.transfered_asset_setup_fee_is_set = true;
                    return;
                }
                else if ( name.equals("transfered_asset_setup_fee_tax") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'transfered_asset_setup_fee_tax' must be Double: '"+value+"' is not." );
                    this.transfered_asset_setup_fee_tax = (Double) value;
                    if (this.transfered_asset_setup_fee_tax_is_set)
                        this.transfered_asset_setup_fee_tax_is_modified = true;
                    this.transfered_asset_setup_fee_tax_is_set = true;
                    return;
                }
                else if ( name.equals("transfered_asset_book_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'transfered_asset_book_value' must be Double: '"+value+"' is not." );
                    this.transfered_asset_book_value = (Double) value;
                    if (this.transfered_asset_book_value_is_set)
                        this.transfered_asset_book_value_is_modified = true;
                    this.transfered_asset_book_value_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
