head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.50.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoProductRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * IpoProductRowインタフェースは変更不可でリードオンリーである<em>ipo_product</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link IpoProductRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IpoProductPK 
 */
public interface IpoProductRow extends Row {


  /** 
   * この{@@link IpoProductRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "ipo_product", "master" );


  /** 
   * <em>ipo_product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getIpoProductId();


  /** 
   * <em>ipo_product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIpoProductIdIsSet();


  /** 
   * <em>ipo_product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIpoProductIdIsModified();


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInstitutionCode();


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsSet();


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsModified();


  /** 
   * <em>product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductCode();


  /** 
   * <em>product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductCodeIsSet();


  /** 
   * <em>product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductCodeIsModified();


  /** 
   * <em>standard_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStandardName();


  /** 
   * <em>standard_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStandardNameIsSet();


  /** 
   * <em>standard_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStandardNameIsModified();


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
   * <em>ipo_regist_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIpoRegistDiv();


  /** 
   * <em>ipo_regist_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIpoRegistDivIsSet();


  /** 
   * <em>ipo_regist_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIpoRegistDivIsModified();


  /** 
   * <em>ipo_regist_detail_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIpoRegistDetailDiv();


  /** 
   * <em>ipo_regist_detail_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIpoRegistDetailDivIsSet();


  /** 
   * <em>ipo_regist_detail_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIpoRegistDetailDivIsModified();


  /** 
   * <em>public_offering_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getPublicOfferingDate();


  /** 
   * <em>public_offering_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicOfferingDateIsSet();


  /** 
   * <em>public_offering_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicOfferingDateIsModified();


  /** 
   * <em>public_offering_date_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getPublicOfferingDateCount();


  /** 
   * <em>public_offering_date_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPublicOfferingDateCountIsNull();


  /** 
   * <em>public_offering_date_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicOfferingDateCountIsSet();


  /** 
   * <em>public_offering_date_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicOfferingDateCountIsModified();


  /** 
   * <em>public_market</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPublicMarket();


  /** 
   * <em>public_market</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicMarketIsSet();


  /** 
   * <em>public_market</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicMarketIsModified();


  /** 
   * <em>provisional_value_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProvisionalValueDiv();


  /** 
   * <em>provisional_value_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProvisionalValueDivIsSet();


  /** 
   * <em>provisional_value_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProvisionalValueDivIsModified();


  /** 
   * <em>provisional_min_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getProvisionalMinValue();


  /** 
   * <em>provisional_min_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getProvisionalMinValueIsNull();


  /** 
   * <em>provisional_min_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProvisionalMinValueIsSet();


  /** 
   * <em>provisional_min_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProvisionalMinValueIsModified();


  /** 
   * <em>provisional_max_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getProvisionalMaxValue();


  /** 
   * <em>provisional_max_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getProvisionalMaxValueIsNull();


  /** 
   * <em>provisional_max_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProvisionalMaxValueIsSet();


  /** 
   * <em>provisional_max_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProvisionalMaxValueIsModified();


  /** 
   * <em>provisional_value_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getProvisionalValueOpenDate();


  /** 
   * <em>provisional_value_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProvisionalValueOpenDateIsSet();


  /** 
   * <em>provisional_value_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProvisionalValueOpenDateIsModified();


  /** 
   * <em>public_offering_quantity</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getPublicOfferingQuantity();


  /** 
   * <em>public_offering_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPublicOfferingQuantityIsNull();


  /** 
   * <em>public_offering_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicOfferingQuantityIsSet();


  /** 
   * <em>public_offering_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicOfferingQuantityIsModified();


  /** 
   * <em>public_sales_quantity</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getPublicSalesQuantity();


  /** 
   * <em>public_sales_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPublicSalesQuantityIsNull();


  /** 
   * <em>public_sales_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicSalesQuantityIsSet();


  /** 
   * <em>public_sales_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicSalesQuantityIsModified();


  /** 
   * <em>dealing_quantity</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getDealingQuantity();


  /** 
   * <em>dealing_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDealingQuantityIsNull();


  /** 
   * <em>dealing_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDealingQuantityIsSet();


  /** 
   * <em>dealing_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDealingQuantityIsModified();


  /** 
   * <em>lead_managing_underwriter</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLeadManagingUnderwriter();


  /** 
   * <em>lead_managing_underwriter</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLeadManagingUnderwriterIsSet();


  /** 
   * <em>lead_managing_underwriter</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLeadManagingUnderwriterIsModified();


  /** 
   * <em>lot_size</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getLotSize();


  /** 
   * <em>lot_size</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLotSizeIsNull();


  /** 
   * <em>lot_size</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLotSizeIsSet();


  /** 
   * <em>lot_size</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLotSizeIsModified();


  /** 
   * <em>tick_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTickValue();


  /** 
   * <em>tick_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTickValueIsNull();


  /** 
   * <em>tick_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTickValueIsSet();


  /** 
   * <em>tick_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTickValueIsModified();


  /** 
   * <em>ipo_unit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIpoUnitDiv();


  /** 
   * <em>ipo_unit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIpoUnitDivIsSet();


  /** 
   * <em>ipo_unit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIpoUnitDivIsModified();


  /** 
   * <em>enable_market_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEnableMarketOrder();


  /** 
   * <em>enable_market_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEnableMarketOrderIsSet();


  /** 
   * <em>enable_market_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEnableMarketOrderIsModified();


  /** 
   * <em>bookbuilding_start_datetime</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getBookbuildingStartDatetime();


  /** 
   * <em>bookbuilding_start_datetime</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBookbuildingStartDatetimeIsSet();


  /** 
   * <em>bookbuilding_start_datetime</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBookbuildingStartDatetimeIsModified();


  /** 
   * <em>bookbuilding_end_datetime</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getBookbuildingEndDatetime();


  /** 
   * <em>bookbuilding_end_datetime</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBookbuildingEndDatetimeIsSet();


  /** 
   * <em>bookbuilding_end_datetime</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBookbuildingEndDatetimeIsModified();


  /** 
   * <em>public_price_settle_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getPublicPriceSettleDate();


  /** 
   * <em>public_price_settle_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicPriceSettleDateIsSet();


  /** 
   * <em>public_price_settle_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicPriceSettleDateIsModified();


  /** 
   * <em>public_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPublicPrice();


  /** 
   * <em>public_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPublicPriceIsNull();


  /** 
   * <em>public_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicPriceIsSet();


  /** 
   * <em>public_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicPriceIsModified();


  /** 
   * <em>public_price_discount_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPublicPriceDiscountRate();


  /** 
   * <em>public_price_discount_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPublicPriceDiscountRateIsNull();


  /** 
   * <em>public_price_discount_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicPriceDiscountRateIsSet();


  /** 
   * <em>public_price_discount_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPublicPriceDiscountRateIsModified();


  /** 
   * <em>lot_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getLotDate();


  /** 
   * <em>lot_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLotDateIsSet();


  /** 
   * <em>lot_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLotDateIsModified();


  /** 
   * <em>lot_date_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLotDateCount();


  /** 
   * <em>lot_date_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLotDateCountIsNull();


  /** 
   * <em>lot_date_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLotDateCountIsSet();


  /** 
   * <em>lot_date_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLotDateCountIsModified();


  /** 
   * <em>lot_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLotStatus();


  /** 
   * <em>lot_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLotStatusIsSet();


  /** 
   * <em>lot_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLotStatusIsModified();


  /** 
   * <em>offer_start_datetime</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getOfferStartDatetime();


  /** 
   * <em>offer_start_datetime</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferStartDatetimeIsSet();


  /** 
   * <em>offer_start_datetime</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferStartDatetimeIsModified();


  /** 
   * <em>offer_start_date_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getOfferStartDateCount();


  /** 
   * <em>offer_start_date_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOfferStartDateCountIsNull();


  /** 
   * <em>offer_start_date_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferStartDateCountIsSet();


  /** 
   * <em>offer_start_date_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferStartDateCountIsModified();


  /** 
   * <em>offer_end_datetime</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getOfferEndDatetime();


  /** 
   * <em>offer_end_datetime</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferEndDatetimeIsSet();


  /** 
   * <em>offer_end_datetime</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferEndDatetimeIsModified();


  /** 
   * <em>offer_end_date_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getOfferEndDateCount();


  /** 
   * <em>offer_end_date_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOfferEndDateCountIsNull();


  /** 
   * <em>offer_end_date_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferEndDateCountIsSet();


  /** 
   * <em>offer_end_date_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferEndDateCountIsModified();


  /** 
   * <em>offer_start_date_prospec</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getOfferStartDateProspec();


  /** 
   * <em>offer_start_date_prospec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferStartDateProspecIsSet();


  /** 
   * <em>offer_start_date_prospec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferStartDateProspecIsModified();


  /** 
   * <em>offer_start_date_count_prospec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getOfferStartDateCountProspec();


  /** 
   * <em>offer_start_date_count_prospec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOfferStartDateCountProspecIsNull();


  /** 
   * <em>offer_start_date_count_prospec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferStartDateCountProspecIsSet();


  /** 
   * <em>offer_start_date_count_prospec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferStartDateCountProspecIsModified();


  /** 
   * <em>offer_end_date_prospec</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getOfferEndDateProspec();


  /** 
   * <em>offer_end_date_prospec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferEndDateProspecIsSet();


  /** 
   * <em>offer_end_date_prospec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferEndDateProspecIsModified();


  /** 
   * <em>offer_end_date_count_prospec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getOfferEndDateCountProspec();


  /** 
   * <em>offer_end_date_count_prospec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOfferEndDateCountProspecIsNull();


  /** 
   * <em>offer_end_date_count_prospec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferEndDateCountProspecIsSet();


  /** 
   * <em>offer_end_date_count_prospec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfferEndDateCountProspecIsModified();


  /** 
   * <em>company_logo_url</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCompanyLogoUrl();


  /** 
   * <em>company_logo_url</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCompanyLogoUrlIsSet();


  /** 
   * <em>company_logo_url</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCompanyLogoUrlIsModified();


  /** 
   * <em>company_url</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCompanyUrl();


  /** 
   * <em>company_url</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCompanyUrlIsSet();


  /** 
   * <em>company_url</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCompanyUrlIsModified();


  /** 
   * <em>company_outline</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCompanyOutline();


  /** 
   * <em>company_outline</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCompanyOutlineIsSet();


  /** 
   * <em>company_outline</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCompanyOutlineIsModified();


  /** 
   * <em>note</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNote();


  /** 
   * <em>note</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNoteIsSet();


  /** 
   * <em>note</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNoteIsModified();


  /** 
   * <em>ipo_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIpoStop();


  /** 
   * <em>ipo_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIpoStopIsSet();


  /** 
   * <em>ipo_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIpoStopIsModified();


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
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLastUpdater();


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsSet();


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsModified();


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
   * <em>doc_reading_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDocReadingDiv();


  /** 
   * <em>doc_reading_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDocReadingDivIsSet();


  /** 
   * <em>doc_reading_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDocReadingDivIsModified();


}
@
