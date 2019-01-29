head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.21.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	QuoteClosingPriceRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * QuoteClosingPriceRowインタフェースは変更不可でリードオンリーである<em>quote_closing_price</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link QuoteClosingPriceRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see QuoteClosingPricePK 
 */
public interface QuoteClosingPriceRow extends Row {


  /** 
   * この{@@link QuoteClosingPriceRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "quote_closing_price", "session" );


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
   * <em>base_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBaseDate();


  /** 
   * <em>base_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBaseDateIsSet();


  /** 
   * <em>base_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBaseDateIsModified();


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
   * <em>tokyo_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTokyoClosingPrice();


  /** 
   * <em>tokyo_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokyoClosingPriceIsSet();


  /** 
   * <em>tokyo_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokyoClosingPriceIsModified();


  /** 
   * <em>tokyo_closing_price_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getTokyoClosingPriceTime();


  /** 
   * <em>tokyo_closing_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokyoClosingPriceTimeIsSet();


  /** 
   * <em>tokyo_closing_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokyoClosingPriceTimeIsModified();


  /** 
   * <em>tokyo_closing_price_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTokyoClosingPriceType();


  /** 
   * <em>tokyo_closing_price_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokyoClosingPriceTypeIsSet();


  /** 
   * <em>tokyo_closing_price_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokyoClosingPriceTypeIsModified();


  /** 
   * <em>oosaka_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOosakaClosingPrice();


  /** 
   * <em>oosaka_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOosakaClosingPriceIsSet();


  /** 
   * <em>oosaka_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOosakaClosingPriceIsModified();


  /** 
   * <em>oosaka_closing_price_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getOosakaClosingPriceTime();


  /** 
   * <em>oosaka_closing_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOosakaClosingPriceTimeIsSet();


  /** 
   * <em>oosaka_closing_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOosakaClosingPriceTimeIsModified();


  /** 
   * <em>oosaka_closing_price_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOosakaClosingPriceType();


  /** 
   * <em>oosaka_closing_price_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOosakaClosingPriceTypeIsSet();


  /** 
   * <em>oosaka_closing_price_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOosakaClosingPriceTypeIsModified();


  /** 
   * <em>nagoya_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getNagoyaClosingPrice();


  /** 
   * <em>nagoya_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNagoyaClosingPriceIsSet();


  /** 
   * <em>nagoya_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNagoyaClosingPriceIsModified();


  /** 
   * <em>nagoya_closing_price_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getNagoyaClosingPriceTime();


  /** 
   * <em>nagoya_closing_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNagoyaClosingPriceTimeIsSet();


  /** 
   * <em>nagoya_closing_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNagoyaClosingPriceTimeIsModified();


  /** 
   * <em>nagoya_closing_price_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNagoyaClosingPriceType();


  /** 
   * <em>nagoya_closing_price_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNagoyaClosingPriceTypeIsSet();


  /** 
   * <em>nagoya_closing_price_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNagoyaClosingPriceTypeIsModified();


  /** 
   * <em>other_market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getOtherMarketId();


  /** 
   * <em>other_market_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOtherMarketIdIsNull();


  /** 
   * <em>other_market_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherMarketIdIsSet();


  /** 
   * <em>other_market_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherMarketIdIsModified();


  /** 
   * <em>other_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOtherClosingPrice();


  /** 
   * <em>other_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherClosingPriceIsSet();


  /** 
   * <em>other_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherClosingPriceIsModified();


  /** 
   * <em>other_closing_price_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getOtherClosingPriceTime();


  /** 
   * <em>other_closing_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherClosingPriceTimeIsSet();


  /** 
   * <em>other_closing_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherClosingPriceTimeIsModified();


  /** 
   * <em>other_closing_price_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOtherClosingPriceType();


  /** 
   * <em>other_closing_price_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherClosingPriceTypeIsSet();


  /** 
   * <em>other_closing_price_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherClosingPriceTypeIsModified();


  /** 
   * <em>primary_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPrimaryClosingPrice();


  /** 
   * <em>primary_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrimaryClosingPriceIsSet();


  /** 
   * <em>primary_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrimaryClosingPriceIsModified();


  /** 
   * <em>primary_closing_price_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getPrimaryClosingPriceTime();


  /** 
   * <em>primary_closing_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrimaryClosingPriceTimeIsSet();


  /** 
   * <em>primary_closing_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrimaryClosingPriceTimeIsModified();


  /** 
   * <em>primary_closing_price_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPrimaryClosingPriceType();


  /** 
   * <em>primary_closing_price_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrimaryClosingPriceTypeIsSet();


  /** 
   * <em>primary_closing_price_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrimaryClosingPriceTypeIsModified();


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
