head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.01.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88656402a7;
filename	FeqFinTransactionParams.java;


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
 * feq_fin_transactionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link FeqFinTransactionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link FeqFinTransactionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link FeqFinTransactionParams}が{@@link FeqFinTransactionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FeqFinTransactionPK 
 * @@see FeqFinTransactionRow 
 */
public class FeqFinTransactionParams extends Params implements FeqFinTransactionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "feq_fin_transaction";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = FeqFinTransactionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return FeqFinTransactionRow.TYPE;
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
   * <em>settle_div</em>カラムの値 
   */
  public  String  settle_div;

  /** 
   * <em>biz_date</em>カラムの値 
   */
  public  String  biz_date;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>currency_code</em>カラムの値 
   */
  public  String  currency_code;

  /** 
   * <em>net_amount</em>カラムの値 
   */
  public  double  net_amount;

  /** 
   * <em>net_amount_fc</em>カラムの値 
   */
  public  double  net_amount_fc;

  /** 
   * <em>fx_rate</em>カラムの値 
   */
  public  double  fx_rate;

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
   * <em>reg_no</em>カラムの値 
   */
  public  String  reg_no;

  /** 
   * <em>charge_ratio</em>カラムの値 
   */
  public  Double  charge_ratio;

  /** 
   * <em>balance_amount</em>カラムの値 
   */
  public  double  balance_amount;

  /** 
   * <em>commission_fee_fc</em>カラムの値 
   */
  public  double  commission_fee_fc;

  /** 
   * <em>commission_fee_tax_fc</em>カラムの値 
   */
  public  double  commission_fee_tax_fc;

  /** 
   * <em>balance_amount_fc</em>カラムの値 
   */
  public  double  balance_amount_fc;

  /** 
   * <em>foreign_commission_fee</em>カラムの値 
   */
  public  double  foreign_commission_fee;

  /** 
   * <em>foreign_tax</em>カラムの値 
   */
  public  double  foreign_tax;

  /** 
   * <em>foreign_fee_ext1</em>カラムの値 
   */
  public  double  foreign_fee_ext1;

  /** 
   * <em>foreign_fee_ext2</em>カラムの値 
   */
  public  double  foreign_fee_ext2;

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
   * <em>capital_gain_fc</em>カラムの値 
   */
  public  double  capital_gain_fc;

  /** 
   * <em>capital_gain_tax_fc</em>カラムの値 
   */
  public  double  capital_gain_tax_fc;

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
   * <em>delete_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  delete_flag;

  /** 
   * <em>order_exec_route_div</em>カラムの値 
   */
  public  String  order_exec_route_div;

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
  private boolean settle_div_is_set = false;
  private boolean settle_div_is_modified = false;
  private boolean biz_date_is_set = false;
  private boolean biz_date_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean net_amount_is_set = false;
  private boolean net_amount_is_modified = false;
  private boolean net_amount_fc_is_set = false;
  private boolean net_amount_fc_is_modified = false;
  private boolean fx_rate_is_set = false;
  private boolean fx_rate_is_modified = false;
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
  private boolean reg_no_is_set = false;
  private boolean reg_no_is_modified = false;
  private boolean charge_ratio_is_set = false;
  private boolean charge_ratio_is_modified = false;
  private boolean balance_amount_is_set = false;
  private boolean balance_amount_is_modified = false;
  private boolean commission_fee_fc_is_set = false;
  private boolean commission_fee_fc_is_modified = false;
  private boolean commission_fee_tax_fc_is_set = false;
  private boolean commission_fee_tax_fc_is_modified = false;
  private boolean balance_amount_fc_is_set = false;
  private boolean balance_amount_fc_is_modified = false;
  private boolean foreign_commission_fee_is_set = false;
  private boolean foreign_commission_fee_is_modified = false;
  private boolean foreign_tax_is_set = false;
  private boolean foreign_tax_is_modified = false;
  private boolean foreign_fee_ext1_is_set = false;
  private boolean foreign_fee_ext1_is_modified = false;
  private boolean foreign_fee_ext2_is_set = false;
  private boolean foreign_fee_ext2_is_modified = false;
  private boolean asset_id_is_set = false;
  private boolean asset_id_is_modified = false;
  private boolean capital_gain_is_set = false;
  private boolean capital_gain_is_modified = false;
  private boolean capital_gain_tax_is_set = false;
  private boolean capital_gain_tax_is_modified = false;
  private boolean capital_gain_fc_is_set = false;
  private boolean capital_gain_fc_is_modified = false;
  private boolean capital_gain_tax_fc_is_set = false;
  private boolean capital_gain_tax_fc_is_modified = false;
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
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
  private boolean order_exec_route_div_is_set = false;
  private boolean order_exec_route_div_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
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
      + "," + "settle_div=" +settle_div
      + "," + "biz_date=" +biz_date
      + "," + "delivery_date=" +delivery_date
      + "," + "currency_code=" +currency_code
      + "," + "net_amount=" +net_amount
      + "," + "net_amount_fc=" +net_amount_fc
      + "," + "fx_rate=" +fx_rate
      + "," + "product_type=" +product_type
      + "," + "market_id=" +market_id
      + "," + "price=" +price
      + "," + "quantity=" +quantity
      + "," + "order_id=" +order_id
      + "," + "order_unit_id=" +order_unit_id
      + "," + "order_execution_id=" +order_execution_id
      + "," + "commission_fee=" +commission_fee
      + "," + "commission_fee_tax=" +commission_fee_tax
      + "," + "reg_no=" +reg_no
      + "," + "charge_ratio=" +charge_ratio
      + "," + "balance_amount=" +balance_amount
      + "," + "commission_fee_fc=" +commission_fee_fc
      + "," + "commission_fee_tax_fc=" +commission_fee_tax_fc
      + "," + "balance_amount_fc=" +balance_amount_fc
      + "," + "foreign_commission_fee=" +foreign_commission_fee
      + "," + "foreign_tax=" +foreign_tax
      + "," + "foreign_fee_ext1=" +foreign_fee_ext1
      + "," + "foreign_fee_ext2=" +foreign_fee_ext2
      + "," + "asset_id=" +asset_id
      + "," + "capital_gain=" +capital_gain
      + "," + "capital_gain_tax=" +capital_gain_tax
      + "," + "capital_gain_fc=" +capital_gain_fc
      + "," + "capital_gain_tax_fc=" +capital_gain_tax_fc
      + "," + "transfered_asset_mng_fee=" +transfered_asset_mng_fee
      + "," + "transfered_asset_mng_fee_tax=" +transfered_asset_mng_fee_tax
      + "," + "transfered_asset_setup_fee=" +transfered_asset_setup_fee
      + "," + "transfered_asset_setup_fee_tax=" +transfered_asset_setup_fee_tax
      + "," + "transfered_asset_book_value=" +transfered_asset_book_value
      + "," + "delete_flag=" +delete_flag
      + "," + "order_exec_route_div=" +order_exec_route_div
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のFeqFinTransactionParamsオブジェクトを作成します。 
   */
  public FeqFinTransactionParams() {
    fin_transaction_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のFeqFinTransactionRowオブジェクトの値を利用してFeqFinTransactionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するFeqFinTransactionRowオブジェクト 
   */
  public FeqFinTransactionParams( FeqFinTransactionRow p_row )
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
    settle_div = p_row.getSettleDiv();
    settle_div_is_set = p_row.getSettleDivIsSet();
    settle_div_is_modified = p_row.getSettleDivIsModified();
    biz_date = p_row.getBizDate();
    biz_date_is_set = p_row.getBizDateIsSet();
    biz_date_is_modified = p_row.getBizDateIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    net_amount = p_row.getNetAmount();
    net_amount_is_set = p_row.getNetAmountIsSet();
    net_amount_is_modified = p_row.getNetAmountIsModified();
    net_amount_fc = p_row.getNetAmountFc();
    net_amount_fc_is_set = p_row.getNetAmountFcIsSet();
    net_amount_fc_is_modified = p_row.getNetAmountFcIsModified();
    fx_rate = p_row.getFxRate();
    fx_rate_is_set = p_row.getFxRateIsSet();
    fx_rate_is_modified = p_row.getFxRateIsModified();
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
    reg_no = p_row.getRegNo();
    reg_no_is_set = p_row.getRegNoIsSet();
    reg_no_is_modified = p_row.getRegNoIsModified();
    if ( !p_row.getChargeRatioIsNull() )
      charge_ratio = new Double( p_row.getChargeRatio() );
    charge_ratio_is_set = p_row.getChargeRatioIsSet();
    charge_ratio_is_modified = p_row.getChargeRatioIsModified();
    balance_amount = p_row.getBalanceAmount();
    balance_amount_is_set = p_row.getBalanceAmountIsSet();
    balance_amount_is_modified = p_row.getBalanceAmountIsModified();
    commission_fee_fc = p_row.getCommissionFeeFc();
    commission_fee_fc_is_set = p_row.getCommissionFeeFcIsSet();
    commission_fee_fc_is_modified = p_row.getCommissionFeeFcIsModified();
    commission_fee_tax_fc = p_row.getCommissionFeeTaxFc();
    commission_fee_tax_fc_is_set = p_row.getCommissionFeeTaxFcIsSet();
    commission_fee_tax_fc_is_modified = p_row.getCommissionFeeTaxFcIsModified();
    balance_amount_fc = p_row.getBalanceAmountFc();
    balance_amount_fc_is_set = p_row.getBalanceAmountFcIsSet();
    balance_amount_fc_is_modified = p_row.getBalanceAmountFcIsModified();
    foreign_commission_fee = p_row.getForeignCommissionFee();
    foreign_commission_fee_is_set = p_row.getForeignCommissionFeeIsSet();
    foreign_commission_fee_is_modified = p_row.getForeignCommissionFeeIsModified();
    foreign_tax = p_row.getForeignTax();
    foreign_tax_is_set = p_row.getForeignTaxIsSet();
    foreign_tax_is_modified = p_row.getForeignTaxIsModified();
    foreign_fee_ext1 = p_row.getForeignFeeExt1();
    foreign_fee_ext1_is_set = p_row.getForeignFeeExt1IsSet();
    foreign_fee_ext1_is_modified = p_row.getForeignFeeExt1IsModified();
    foreign_fee_ext2 = p_row.getForeignFeeExt2();
    foreign_fee_ext2_is_set = p_row.getForeignFeeExt2IsSet();
    foreign_fee_ext2_is_modified = p_row.getForeignFeeExt2IsModified();
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
    capital_gain_fc = p_row.getCapitalGainFc();
    capital_gain_fc_is_set = p_row.getCapitalGainFcIsSet();
    capital_gain_fc_is_modified = p_row.getCapitalGainFcIsModified();
    capital_gain_tax_fc = p_row.getCapitalGainTaxFc();
    capital_gain_tax_fc_is_set = p_row.getCapitalGainTaxFcIsSet();
    capital_gain_tax_fc_is_modified = p_row.getCapitalGainTaxFcIsModified();
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
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
    order_exec_route_div = p_row.getOrderExecRouteDiv();
    order_exec_route_div_is_set = p_row.getOrderExecRouteDivIsSet();
    order_exec_route_div_is_modified = p_row.getOrderExecRouteDivIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
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
      settle_div_is_set = true;
      settle_div_is_modified = true;
      biz_date_is_set = true;
      biz_date_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      currency_code_is_set = true;
      currency_code_is_modified = true;
      net_amount_is_set = true;
      net_amount_is_modified = true;
      net_amount_fc_is_set = true;
      net_amount_fc_is_modified = true;
      fx_rate_is_set = true;
      fx_rate_is_modified = true;
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
      reg_no_is_set = true;
      reg_no_is_modified = true;
      charge_ratio_is_set = true;
      charge_ratio_is_modified = true;
      balance_amount_is_set = true;
      balance_amount_is_modified = true;
      commission_fee_fc_is_set = true;
      commission_fee_fc_is_modified = true;
      commission_fee_tax_fc_is_set = true;
      commission_fee_tax_fc_is_modified = true;
      balance_amount_fc_is_set = true;
      balance_amount_fc_is_modified = true;
      foreign_commission_fee_is_set = true;
      foreign_commission_fee_is_modified = true;
      foreign_tax_is_set = true;
      foreign_tax_is_modified = true;
      foreign_fee_ext1_is_set = true;
      foreign_fee_ext1_is_modified = true;
      foreign_fee_ext2_is_set = true;
      foreign_fee_ext2_is_modified = true;
      asset_id_is_set = true;
      asset_id_is_modified = true;
      capital_gain_is_set = true;
      capital_gain_is_modified = true;
      capital_gain_tax_is_set = true;
      capital_gain_tax_is_modified = true;
      capital_gain_fc_is_set = true;
      capital_gain_fc_is_modified = true;
      capital_gain_tax_fc_is_set = true;
      capital_gain_tax_fc_is_modified = true;
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
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
      order_exec_route_div_is_set = true;
      order_exec_route_div_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
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
    if ( !( other instanceof FeqFinTransactionRow ) )
       return false;
    return fieldsEqual( (FeqFinTransactionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のFeqFinTransactionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( FeqFinTransactionRow row )
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
    if ( settle_div == null ) {
      if ( row.getSettleDiv() != null )
        return false;
    } else if ( !settle_div.equals( row.getSettleDiv() ) ) {
        return false;
    }
    if ( biz_date == null ) {
      if ( row.getBizDate() != null )
        return false;
    } else if ( !biz_date.equals( row.getBizDate() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( net_amount != row.getNetAmount() )
      return false;
    if ( net_amount_fc != row.getNetAmountFc() )
      return false;
    if ( fx_rate != row.getFxRate() )
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
    if ( reg_no == null ) {
      if ( row.getRegNo() != null )
        return false;
    } else if ( !reg_no.equals( row.getRegNo() ) ) {
        return false;
    }
    if ( charge_ratio == null ) {
      if ( !row.getChargeRatioIsNull() )
        return false;
    } else if ( row.getChargeRatioIsNull() || ( charge_ratio.doubleValue() != row.getChargeRatio() ) ) {
        return false;
    }
    if ( balance_amount != row.getBalanceAmount() )
      return false;
    if ( commission_fee_fc != row.getCommissionFeeFc() )
      return false;
    if ( commission_fee_tax_fc != row.getCommissionFeeTaxFc() )
      return false;
    if ( balance_amount_fc != row.getBalanceAmountFc() )
      return false;
    if ( foreign_commission_fee != row.getForeignCommissionFee() )
      return false;
    if ( foreign_tax != row.getForeignTax() )
      return false;
    if ( foreign_fee_ext1 != row.getForeignFeeExt1() )
      return false;
    if ( foreign_fee_ext2 != row.getForeignFeeExt2() )
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
    if ( capital_gain_fc != row.getCapitalGainFc() )
      return false;
    if ( capital_gain_tax_fc != row.getCapitalGainTaxFc() )
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
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
        return false;
    }
    if ( order_exec_route_div == null ) {
      if ( row.getOrderExecRouteDiv() != null )
        return false;
    } else if ( !order_exec_route_div.equals( row.getOrderExecRouteDiv() ) ) {
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
        + (settle_div!=null? settle_div.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + ((int) net_amount)
        + ((int) net_amount_fc)
        + ((int) fx_rate)
        + (product_type!=null? product_type.hashCode(): 0) 
        + (market_id!=null? market_id.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + ((int) quantity)
        + (order_id!=null? order_id.hashCode(): 0) 
        + (order_unit_id!=null? order_unit_id.hashCode(): 0) 
        + (order_execution_id!=null? order_execution_id.hashCode(): 0) 
        + ((int) commission_fee)
        + ((int) commission_fee_tax)
        + (reg_no!=null? reg_no.hashCode(): 0) 
        + (charge_ratio!=null? charge_ratio.hashCode(): 0) 
        + ((int) balance_amount)
        + ((int) commission_fee_fc)
        + ((int) commission_fee_tax_fc)
        + ((int) balance_amount_fc)
        + ((int) foreign_commission_fee)
        + ((int) foreign_tax)
        + ((int) foreign_fee_ext1)
        + ((int) foreign_fee_ext2)
        + (asset_id!=null? asset_id.hashCode(): 0) 
        + ((int) capital_gain)
        + ((int) capital_gain_tax)
        + ((int) capital_gain_fc)
        + ((int) capital_gain_tax_fc)
        + (transfered_asset_mng_fee!=null? transfered_asset_mng_fee.hashCode(): 0) 
        + (transfered_asset_mng_fee_tax!=null? transfered_asset_mng_fee_tax.hashCode(): 0) 
        + (transfered_asset_setup_fee!=null? transfered_asset_setup_fee.hashCode(): 0) 
        + (transfered_asset_setup_fee_tax!=null? transfered_asset_setup_fee_tax.hashCode(): 0) 
        + (transfered_asset_book_value!=null? transfered_asset_book_value.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
        + (order_exec_route_div!=null? order_exec_route_div.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
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
		if ( !biz_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'biz_date' must be set before inserting.");
		if ( !delivery_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_date' must be set before inserting.");
		if ( !currency_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'currency_code' must be set before inserting.");
		if ( !net_amount_is_set )
			throw new IllegalArgumentException("non-nullable field 'net_amount' must be set before inserting.");
		if ( !net_amount_fc_is_set )
			throw new IllegalArgumentException("non-nullable field 'net_amount_fc' must be set before inserting.");
		if ( !fx_rate_is_set )
			throw new IllegalArgumentException("non-nullable field 'fx_rate' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
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
		if ( settle_div != null )
			map.put("settle_div",settle_div);
		map.put("biz_date",biz_date);
		map.put("delivery_date",delivery_date);
		map.put("currency_code",currency_code);
		map.put("net_amount",new Double(net_amount));
		map.put("net_amount_fc",new Double(net_amount_fc));
		map.put("fx_rate",new Double(fx_rate));
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
		if ( reg_no != null )
			map.put("reg_no",reg_no);
		if ( charge_ratio != null )
			map.put("charge_ratio",charge_ratio);
		if ( balance_amount_is_set )
			map.put("balance_amount",new Double(balance_amount));
		if ( commission_fee_fc_is_set )
			map.put("commission_fee_fc",new Double(commission_fee_fc));
		if ( commission_fee_tax_fc_is_set )
			map.put("commission_fee_tax_fc",new Double(commission_fee_tax_fc));
		if ( balance_amount_fc_is_set )
			map.put("balance_amount_fc",new Double(balance_amount_fc));
		if ( foreign_commission_fee_is_set )
			map.put("foreign_commission_fee",new Double(foreign_commission_fee));
		if ( foreign_tax_is_set )
			map.put("foreign_tax",new Double(foreign_tax));
		if ( foreign_fee_ext1_is_set )
			map.put("foreign_fee_ext1",new Double(foreign_fee_ext1));
		if ( foreign_fee_ext2_is_set )
			map.put("foreign_fee_ext2",new Double(foreign_fee_ext2));
		if ( asset_id != null )
			map.put("asset_id",asset_id);
		if ( capital_gain_is_set )
			map.put("capital_gain",new Double(capital_gain));
		if ( capital_gain_tax_is_set )
			map.put("capital_gain_tax",new Double(capital_gain_tax));
		if ( capital_gain_fc_is_set )
			map.put("capital_gain_fc",new Double(capital_gain_fc));
		if ( capital_gain_tax_fc_is_set )
			map.put("capital_gain_tax_fc",new Double(capital_gain_tax_fc));
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
		if ( delete_flag_is_set )
			map.put("delete_flag",delete_flag);
		if ( order_exec_route_div != null )
			map.put("order_exec_route_div",order_exec_route_div);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
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
		if ( settle_div_is_modified )
			map.put("settle_div",settle_div);
		if ( biz_date_is_modified )
			map.put("biz_date",biz_date);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( currency_code_is_modified )
			map.put("currency_code",currency_code);
		if ( net_amount_is_modified )
			map.put("net_amount",new Double(net_amount));
		if ( net_amount_fc_is_modified )
			map.put("net_amount_fc",new Double(net_amount_fc));
		if ( fx_rate_is_modified )
			map.put("fx_rate",new Double(fx_rate));
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
		if ( reg_no_is_modified )
			map.put("reg_no",reg_no);
		if ( charge_ratio_is_modified )
			map.put("charge_ratio",charge_ratio);
		if ( balance_amount_is_modified )
			map.put("balance_amount",new Double(balance_amount));
		if ( commission_fee_fc_is_modified )
			map.put("commission_fee_fc",new Double(commission_fee_fc));
		if ( commission_fee_tax_fc_is_modified )
			map.put("commission_fee_tax_fc",new Double(commission_fee_tax_fc));
		if ( balance_amount_fc_is_modified )
			map.put("balance_amount_fc",new Double(balance_amount_fc));
		if ( foreign_commission_fee_is_modified )
			map.put("foreign_commission_fee",new Double(foreign_commission_fee));
		if ( foreign_tax_is_modified )
			map.put("foreign_tax",new Double(foreign_tax));
		if ( foreign_fee_ext1_is_modified )
			map.put("foreign_fee_ext1",new Double(foreign_fee_ext1));
		if ( foreign_fee_ext2_is_modified )
			map.put("foreign_fee_ext2",new Double(foreign_fee_ext2));
		if ( asset_id_is_modified )
			map.put("asset_id",asset_id);
		if ( capital_gain_is_modified )
			map.put("capital_gain",new Double(capital_gain));
		if ( capital_gain_tax_is_modified )
			map.put("capital_gain_tax",new Double(capital_gain_tax));
		if ( capital_gain_fc_is_modified )
			map.put("capital_gain_fc",new Double(capital_gain_fc));
		if ( capital_gain_tax_fc_is_modified )
			map.put("capital_gain_tax_fc",new Double(capital_gain_tax_fc));
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
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( order_exec_route_div_is_modified )
			map.put("order_exec_route_div",order_exec_route_div);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
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
			map.put("settle_div",settle_div);
			if ( biz_date_is_set )
				map.put("biz_date",biz_date);
			if ( delivery_date_is_set )
				map.put("delivery_date",delivery_date);
			if ( currency_code_is_set )
				map.put("currency_code",currency_code);
			if ( net_amount_is_set )
				map.put("net_amount",new Double(net_amount));
			if ( net_amount_fc_is_set )
				map.put("net_amount_fc",new Double(net_amount_fc));
			if ( fx_rate_is_set )
				map.put("fx_rate",new Double(fx_rate));
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
			map.put("reg_no",reg_no);
			map.put("charge_ratio",charge_ratio);
			if ( balance_amount_is_set )
				map.put("balance_amount",new Double(balance_amount));
			if ( commission_fee_fc_is_set )
				map.put("commission_fee_fc",new Double(commission_fee_fc));
			if ( commission_fee_tax_fc_is_set )
				map.put("commission_fee_tax_fc",new Double(commission_fee_tax_fc));
			if ( balance_amount_fc_is_set )
				map.put("balance_amount_fc",new Double(balance_amount_fc));
			if ( foreign_commission_fee_is_set )
				map.put("foreign_commission_fee",new Double(foreign_commission_fee));
			if ( foreign_tax_is_set )
				map.put("foreign_tax",new Double(foreign_tax));
			if ( foreign_fee_ext1_is_set )
				map.put("foreign_fee_ext1",new Double(foreign_fee_ext1));
			if ( foreign_fee_ext2_is_set )
				map.put("foreign_fee_ext2",new Double(foreign_fee_ext2));
			map.put("asset_id",asset_id);
			if ( capital_gain_is_set )
				map.put("capital_gain",new Double(capital_gain));
			if ( capital_gain_tax_is_set )
				map.put("capital_gain_tax",new Double(capital_gain_tax));
			if ( capital_gain_fc_is_set )
				map.put("capital_gain_fc",new Double(capital_gain_fc));
			if ( capital_gain_tax_fc_is_set )
				map.put("capital_gain_tax_fc",new Double(capital_gain_tax_fc));
			if ( transfered_asset_mng_fee_is_set )
				map.put("transfered_asset_mng_fee",transfered_asset_mng_fee);
			if ( transfered_asset_mng_fee_tax_is_set )
				map.put("transfered_asset_mng_fee_tax",transfered_asset_mng_fee_tax);
			if ( transfered_asset_setup_fee_is_set )
				map.put("transfered_asset_setup_fee",transfered_asset_setup_fee);
			if ( transfered_asset_setup_fee_tax_is_set )
				map.put("transfered_asset_setup_fee_tax",transfered_asset_setup_fee_tax);
			map.put("transfered_asset_book_value",transfered_asset_book_value);
			if ( delete_flag_is_set )
				map.put("delete_flag",delete_flag);
			map.put("order_exec_route_div",order_exec_route_div);
			map.put("last_updater",last_updater);
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
   * <em>net_amount_fc</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNetAmountFc()
  {
    return net_amount_fc;
  }


  /** 
   * <em>net_amount_fc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetAmountFcIsSet() {
    return net_amount_fc_is_set;
  }


  /** 
   * <em>net_amount_fc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetAmountFcIsModified() {
    return net_amount_fc_is_modified;
  }


  /** 
   * <em>fx_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getFxRate()
  {
    return fx_rate;
  }


  /** 
   * <em>fx_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxRateIsSet() {
    return fx_rate_is_set;
  }


  /** 
   * <em>fx_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxRateIsModified() {
    return fx_rate_is_modified;
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
   * <em>reg_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRegNo()
  {
    return reg_no;
  }


  /** 
   * <em>reg_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegNoIsSet() {
    return reg_no_is_set;
  }


  /** 
   * <em>reg_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegNoIsModified() {
    return reg_no_is_modified;
  }


  /** 
   * <em>charge_ratio</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getChargeRatio()
  {
    return ( charge_ratio==null? ((double)0): charge_ratio.doubleValue() );
  }


  /** 
   * <em>charge_ratio</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getChargeRatioIsNull()
  {
    return charge_ratio == null;
  }


  /** 
   * <em>charge_ratio</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChargeRatioIsSet() {
    return charge_ratio_is_set;
  }


  /** 
   * <em>charge_ratio</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChargeRatioIsModified() {
    return charge_ratio_is_modified;
  }


  /** 
   * <em>balance_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBalanceAmount()
  {
    return balance_amount;
  }


  /** 
   * <em>balance_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBalanceAmountIsSet() {
    return balance_amount_is_set;
  }


  /** 
   * <em>balance_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBalanceAmountIsModified() {
    return balance_amount_is_modified;
  }


  /** 
   * <em>commission_fee_fc</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionFeeFc()
  {
    return commission_fee_fc;
  }


  /** 
   * <em>commission_fee_fc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeFcIsSet() {
    return commission_fee_fc_is_set;
  }


  /** 
   * <em>commission_fee_fc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeFcIsModified() {
    return commission_fee_fc_is_modified;
  }


  /** 
   * <em>commission_fee_tax_fc</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionFeeTaxFc()
  {
    return commission_fee_tax_fc;
  }


  /** 
   * <em>commission_fee_tax_fc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeTaxFcIsSet() {
    return commission_fee_tax_fc_is_set;
  }


  /** 
   * <em>commission_fee_tax_fc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeTaxFcIsModified() {
    return commission_fee_tax_fc_is_modified;
  }


  /** 
   * <em>balance_amount_fc</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBalanceAmountFc()
  {
    return balance_amount_fc;
  }


  /** 
   * <em>balance_amount_fc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBalanceAmountFcIsSet() {
    return balance_amount_fc_is_set;
  }


  /** 
   * <em>balance_amount_fc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBalanceAmountFcIsModified() {
    return balance_amount_fc_is_modified;
  }


  /** 
   * <em>foreign_commission_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getForeignCommissionFee()
  {
    return foreign_commission_fee;
  }


  /** 
   * <em>foreign_commission_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignCommissionFeeIsSet() {
    return foreign_commission_fee_is_set;
  }


  /** 
   * <em>foreign_commission_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignCommissionFeeIsModified() {
    return foreign_commission_fee_is_modified;
  }


  /** 
   * <em>foreign_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getForeignTax()
  {
    return foreign_tax;
  }


  /** 
   * <em>foreign_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignTaxIsSet() {
    return foreign_tax_is_set;
  }


  /** 
   * <em>foreign_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignTaxIsModified() {
    return foreign_tax_is_modified;
  }


  /** 
   * <em>foreign_fee_ext1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getForeignFeeExt1()
  {
    return foreign_fee_ext1;
  }


  /** 
   * <em>foreign_fee_ext1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignFeeExt1IsSet() {
    return foreign_fee_ext1_is_set;
  }


  /** 
   * <em>foreign_fee_ext1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignFeeExt1IsModified() {
    return foreign_fee_ext1_is_modified;
  }


  /** 
   * <em>foreign_fee_ext2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getForeignFeeExt2()
  {
    return foreign_fee_ext2;
  }


  /** 
   * <em>foreign_fee_ext2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignFeeExt2IsSet() {
    return foreign_fee_ext2_is_set;
  }


  /** 
   * <em>foreign_fee_ext2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignFeeExt2IsModified() {
    return foreign_fee_ext2_is_modified;
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
   * <em>capital_gain_fc</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCapitalGainFc()
  {
    return capital_gain_fc;
  }


  /** 
   * <em>capital_gain_fc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainFcIsSet() {
    return capital_gain_fc_is_set;
  }


  /** 
   * <em>capital_gain_fc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainFcIsModified() {
    return capital_gain_fc_is_modified;
  }


  /** 
   * <em>capital_gain_tax_fc</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCapitalGainTaxFc()
  {
    return capital_gain_tax_fc;
  }


  /** 
   * <em>capital_gain_tax_fc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainTaxFcIsSet() {
    return capital_gain_tax_fc_is_set;
  }


  /** 
   * <em>capital_gain_tax_fc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainTaxFcIsModified() {
    return capital_gain_tax_fc_is_modified;
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
   * <em>order_exec_route_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderExecRouteDiv()
  {
    return order_exec_route_div;
  }


  /** 
   * <em>order_exec_route_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderExecRouteDivIsSet() {
    return order_exec_route_div_is_set;
  }


  /** 
   * <em>order_exec_route_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderExecRouteDivIsModified() {
    return order_exec_route_div_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new FeqFinTransactionPK(fin_transaction_id);
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
   * <em>net_amount_fc</em>カラムの値を設定します。 
   *
   * @@param p_netAmountFc <em>net_amount_fc</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNetAmountFc( double p_netAmountFc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_amount_fc = p_netAmountFc;
    net_amount_fc_is_set = true;
    net_amount_fc_is_modified = true;
  }


  /** 
   * <em>fx_rate</em>カラムの値を設定します。 
   *
   * @@param p_fxRate <em>fx_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setFxRate( double p_fxRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_rate = p_fxRate;
    fx_rate_is_set = true;
    fx_rate_is_modified = true;
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
   * <em>reg_no</em>カラムの値を設定します。 
   *
   * @@param p_regNo <em>reg_no</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setRegNo( String p_regNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reg_no = p_regNo;
    reg_no_is_set = true;
    reg_no_is_modified = true;
  }


  /** 
   * <em>charge_ratio</em>カラムの値を設定します。 
   *
   * @@param p_chargeRatio <em>charge_ratio</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setChargeRatio( double p_chargeRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    charge_ratio = new Double(p_chargeRatio);
    charge_ratio_is_set = true;
    charge_ratio_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>charge_ratio</em>カラムに値を設定します。 
   */
  public final void setChargeRatio( Double p_chargeRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    charge_ratio = p_chargeRatio;
    charge_ratio_is_set = true;
    charge_ratio_is_modified = true;
  }


  /** 
   * <em>balance_amount</em>カラムの値を設定します。 
   *
   * @@param p_balanceAmount <em>balance_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBalanceAmount( double p_balanceAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    balance_amount = p_balanceAmount;
    balance_amount_is_set = true;
    balance_amount_is_modified = true;
  }


  /** 
   * <em>commission_fee_fc</em>カラムの値を設定します。 
   *
   * @@param p_commissionFeeFc <em>commission_fee_fc</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCommissionFeeFc( double p_commissionFeeFc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_fee_fc = p_commissionFeeFc;
    commission_fee_fc_is_set = true;
    commission_fee_fc_is_modified = true;
  }


  /** 
   * <em>commission_fee_tax_fc</em>カラムの値を設定します。 
   *
   * @@param p_commissionFeeTaxFc <em>commission_fee_tax_fc</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCommissionFeeTaxFc( double p_commissionFeeTaxFc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_fee_tax_fc = p_commissionFeeTaxFc;
    commission_fee_tax_fc_is_set = true;
    commission_fee_tax_fc_is_modified = true;
  }


  /** 
   * <em>balance_amount_fc</em>カラムの値を設定します。 
   *
   * @@param p_balanceAmountFc <em>balance_amount_fc</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBalanceAmountFc( double p_balanceAmountFc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    balance_amount_fc = p_balanceAmountFc;
    balance_amount_fc_is_set = true;
    balance_amount_fc_is_modified = true;
  }


  /** 
   * <em>foreign_commission_fee</em>カラムの値を設定します。 
   *
   * @@param p_foreignCommissionFee <em>foreign_commission_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setForeignCommissionFee( double p_foreignCommissionFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_commission_fee = p_foreignCommissionFee;
    foreign_commission_fee_is_set = true;
    foreign_commission_fee_is_modified = true;
  }


  /** 
   * <em>foreign_tax</em>カラムの値を設定します。 
   *
   * @@param p_foreignTax <em>foreign_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setForeignTax( double p_foreignTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_tax = p_foreignTax;
    foreign_tax_is_set = true;
    foreign_tax_is_modified = true;
  }


  /** 
   * <em>foreign_fee_ext1</em>カラムの値を設定します。 
   *
   * @@param p_foreignFeeExt1 <em>foreign_fee_ext1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setForeignFeeExt1( double p_foreignFeeExt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_fee_ext1 = p_foreignFeeExt1;
    foreign_fee_ext1_is_set = true;
    foreign_fee_ext1_is_modified = true;
  }


  /** 
   * <em>foreign_fee_ext2</em>カラムの値を設定します。 
   *
   * @@param p_foreignFeeExt2 <em>foreign_fee_ext2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setForeignFeeExt2( double p_foreignFeeExt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_fee_ext2 = p_foreignFeeExt2;
    foreign_fee_ext2_is_set = true;
    foreign_fee_ext2_is_modified = true;
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
   * <em>capital_gain_fc</em>カラムの値を設定します。 
   *
   * @@param p_capitalGainFc <em>capital_gain_fc</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCapitalGainFc( double p_capitalGainFc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain_fc = p_capitalGainFc;
    capital_gain_fc_is_set = true;
    capital_gain_fc_is_modified = true;
  }


  /** 
   * <em>capital_gain_tax_fc</em>カラムの値を設定します。 
   *
   * @@param p_capitalGainTaxFc <em>capital_gain_tax_fc</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCapitalGainTaxFc( double p_capitalGainTaxFc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain_tax_fc = p_capitalGainTaxFc;
    capital_gain_tax_fc_is_set = true;
    capital_gain_tax_fc_is_modified = true;
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
   * <em>order_exec_route_div</em>カラムの値を設定します。 
   *
   * @@param p_orderExecRouteDiv <em>order_exec_route_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderExecRouteDiv( String p_orderExecRouteDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_exec_route_div = p_orderExecRouteDiv;
    order_exec_route_div_is_set = true;
    order_exec_route_div_is_modified = true;
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
                break;
            case 'b':
                if ( name.equals("biz_date") ) {
                    return this.biz_date;
                }
                else if ( name.equals("balance_amount") ) {
                    return new Double( balance_amount );
                }
                else if ( name.equals("balance_amount_fc") ) {
                    return new Double( balance_amount_fc );
                }
                break;
            case 'c':
                if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("commission_fee") ) {
                    return new Double( commission_fee );
                }
                else if ( name.equals("commission_fee_tax") ) {
                    return new Double( commission_fee_tax );
                }
                else if ( name.equals("charge_ratio") ) {
                    return this.charge_ratio;
                }
                else if ( name.equals("commission_fee_fc") ) {
                    return new Double( commission_fee_fc );
                }
                else if ( name.equals("commission_fee_tax_fc") ) {
                    return new Double( commission_fee_tax_fc );
                }
                else if ( name.equals("capital_gain") ) {
                    return new Double( capital_gain );
                }
                else if ( name.equals("capital_gain_tax") ) {
                    return new Double( capital_gain_tax );
                }
                else if ( name.equals("capital_gain_fc") ) {
                    return new Double( capital_gain_fc );
                }
                else if ( name.equals("capital_gain_tax_fc") ) {
                    return new Double( capital_gain_tax_fc );
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
                else if ( name.equals("fx_rate") ) {
                    return new Double( fx_rate );
                }
                else if ( name.equals("foreign_commission_fee") ) {
                    return new Double( foreign_commission_fee );
                }
                else if ( name.equals("foreign_tax") ) {
                    return new Double( foreign_tax );
                }
                else if ( name.equals("foreign_fee_ext1") ) {
                    return new Double( foreign_fee_ext1 );
                }
                else if ( name.equals("foreign_fee_ext2") ) {
                    return new Double( foreign_fee_ext2 );
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
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
                break;
            case 'n':
                if ( name.equals("net_amount") ) {
                    return new Double( net_amount );
                }
                else if ( name.equals("net_amount_fc") ) {
                    return new Double( net_amount_fc );
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
                else if ( name.equals("order_exec_route_div") ) {
                    return this.order_exec_route_div;
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
            case 'r':
                if ( name.equals("reg_no") ) {
                    return this.reg_no;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("settle_div") ) {
                    return this.settle_div;
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
                break;
            case 'b':
                if ( name.equals("biz_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'biz_date' must be String: '"+value+"' is not." );
                    this.biz_date = (String) value;
                    if (this.biz_date_is_set)
                        this.biz_date_is_modified = true;
                    this.biz_date_is_set = true;
                    return;
                }
                else if ( name.equals("balance_amount") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'balance_amount' must be Double: '"+value+"' is not." );
                    this.balance_amount = ((Double) value).doubleValue();
                    if (this.balance_amount_is_set)
                        this.balance_amount_is_modified = true;
                    this.balance_amount_is_set = true;
                    return;
                }
                else if ( name.equals("balance_amount_fc") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'balance_amount_fc' must be Double: '"+value+"' is not." );
                    this.balance_amount_fc = ((Double) value).doubleValue();
                    if (this.balance_amount_fc_is_set)
                        this.balance_amount_fc_is_modified = true;
                    this.balance_amount_fc_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("currency_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_code' must be String: '"+value+"' is not." );
                    this.currency_code = (String) value;
                    if (this.currency_code_is_set)
                        this.currency_code_is_modified = true;
                    this.currency_code_is_set = true;
                    return;
                }
                else if ( name.equals("commission_fee") ) {
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
                else if ( name.equals("charge_ratio") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'charge_ratio' must be Double: '"+value+"' is not." );
                    this.charge_ratio = (Double) value;
                    if (this.charge_ratio_is_set)
                        this.charge_ratio_is_modified = true;
                    this.charge_ratio_is_set = true;
                    return;
                }
                else if ( name.equals("commission_fee_fc") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_fee_fc' must be Double: '"+value+"' is not." );
                    this.commission_fee_fc = ((Double) value).doubleValue();
                    if (this.commission_fee_fc_is_set)
                        this.commission_fee_fc_is_modified = true;
                    this.commission_fee_fc_is_set = true;
                    return;
                }
                else if ( name.equals("commission_fee_tax_fc") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_fee_tax_fc' must be Double: '"+value+"' is not." );
                    this.commission_fee_tax_fc = ((Double) value).doubleValue();
                    if (this.commission_fee_tax_fc_is_set)
                        this.commission_fee_tax_fc_is_modified = true;
                    this.commission_fee_tax_fc_is_set = true;
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
                else if ( name.equals("capital_gain_fc") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'capital_gain_fc' must be Double: '"+value+"' is not." );
                    this.capital_gain_fc = ((Double) value).doubleValue();
                    if (this.capital_gain_fc_is_set)
                        this.capital_gain_fc_is_modified = true;
                    this.capital_gain_fc_is_set = true;
                    return;
                }
                else if ( name.equals("capital_gain_tax_fc") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'capital_gain_tax_fc' must be Double: '"+value+"' is not." );
                    this.capital_gain_tax_fc = ((Double) value).doubleValue();
                    if (this.capital_gain_tax_fc_is_set)
                        this.capital_gain_tax_fc_is_modified = true;
                    this.capital_gain_tax_fc_is_set = true;
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
                else if ( name.equals("fx_rate") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'fx_rate' must be Double: '"+value+"' is not." );
                    this.fx_rate = ((Double) value).doubleValue();
                    if (this.fx_rate_is_set)
                        this.fx_rate_is_modified = true;
                    this.fx_rate_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_commission_fee") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'foreign_commission_fee' must be Double: '"+value+"' is not." );
                    this.foreign_commission_fee = ((Double) value).doubleValue();
                    if (this.foreign_commission_fee_is_set)
                        this.foreign_commission_fee_is_modified = true;
                    this.foreign_commission_fee_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_tax") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'foreign_tax' must be Double: '"+value+"' is not." );
                    this.foreign_tax = ((Double) value).doubleValue();
                    if (this.foreign_tax_is_set)
                        this.foreign_tax_is_modified = true;
                    this.foreign_tax_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_fee_ext1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'foreign_fee_ext1' must be Double: '"+value+"' is not." );
                    this.foreign_fee_ext1 = ((Double) value).doubleValue();
                    if (this.foreign_fee_ext1_is_set)
                        this.foreign_fee_ext1_is_modified = true;
                    this.foreign_fee_ext1_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_fee_ext2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'foreign_fee_ext2' must be Double: '"+value+"' is not." );
                    this.foreign_fee_ext2 = ((Double) value).doubleValue();
                    if (this.foreign_fee_ext2_is_set)
                        this.foreign_fee_ext2_is_modified = true;
                    this.foreign_fee_ext2_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
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
                else if ( name.equals("net_amount_fc") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'net_amount_fc' must be Double: '"+value+"' is not." );
                    this.net_amount_fc = ((Double) value).doubleValue();
                    if (this.net_amount_fc_is_set)
                        this.net_amount_fc_is_modified = true;
                    this.net_amount_fc_is_set = true;
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
                else if ( name.equals("order_exec_route_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_exec_route_div' must be String: '"+value+"' is not." );
                    this.order_exec_route_div = (String) value;
                    if (this.order_exec_route_div_is_set)
                        this.order_exec_route_div_is_modified = true;
                    this.order_exec_route_div_is_set = true;
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
            case 'r':
                if ( name.equals("reg_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reg_no' must be String: '"+value+"' is not." );
                    this.reg_no = (String) value;
                    if (this.reg_no_is_set)
                        this.reg_no_is_modified = true;
                    this.reg_no_is_set = true;
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
