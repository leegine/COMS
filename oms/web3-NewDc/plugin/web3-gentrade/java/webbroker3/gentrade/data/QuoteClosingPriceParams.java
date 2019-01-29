head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.41.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	QuoteClosingPriceParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * quote_closing_priceテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link QuoteClosingPriceRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link QuoteClosingPriceParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link QuoteClosingPriceParams}が{@@link QuoteClosingPriceRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see QuoteClosingPricePK 
 * @@see QuoteClosingPriceRow 
 */
public class QuoteClosingPriceParams extends Params implements QuoteClosingPriceRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "quote_closing_price";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = QuoteClosingPriceRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return QuoteClosingPriceRow.TYPE;
  }


  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>base_date</em>カラムの値 
   */
  public  String  base_date;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>tokyo_closing_price</em>カラムの値 
   */
  public  double  tokyo_closing_price;

  /** 
   * <em>tokyo_closing_price_time</em>カラムの値 
   */
  public  java.sql.Timestamp  tokyo_closing_price_time;

  /** 
   * <em>tokyo_closing_price_type</em>カラムの値 
   */
  public  String  tokyo_closing_price_type;

  /** 
   * <em>oosaka_closing_price</em>カラムの値 
   */
  public  double  oosaka_closing_price;

  /** 
   * <em>oosaka_closing_price_time</em>カラムの値 
   */
  public  java.sql.Timestamp  oosaka_closing_price_time;

  /** 
   * <em>oosaka_closing_price_type</em>カラムの値 
   */
  public  String  oosaka_closing_price_type;

  /** 
   * <em>nagoya_closing_price</em>カラムの値 
   */
  public  double  nagoya_closing_price;

  /** 
   * <em>nagoya_closing_price_time</em>カラムの値 
   */
  public  java.sql.Timestamp  nagoya_closing_price_time;

  /** 
   * <em>nagoya_closing_price_type</em>カラムの値 
   */
  public  String  nagoya_closing_price_type;

  /** 
   * <em>other_market_id</em>カラムの値 
   */
  public  Long  other_market_id;

  /** 
   * <em>other_closing_price</em>カラムの値 
   */
  public  double  other_closing_price;

  /** 
   * <em>other_closing_price_time</em>カラムの値 
   */
  public  java.sql.Timestamp  other_closing_price_time;

  /** 
   * <em>other_closing_price_type</em>カラムの値 
   */
  public  String  other_closing_price_type;

  /** 
   * <em>primary_closing_price</em>カラムの値 
   */
  public  double  primary_closing_price;

  /** 
   * <em>primary_closing_price_time</em>カラムの値 
   */
  public  java.sql.Timestamp  primary_closing_price_time;

  /** 
   * <em>primary_closing_price_type</em>カラムの値 
   */
  public  String  primary_closing_price_type;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean base_date_is_set = false;
  private boolean base_date_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean tokyo_closing_price_is_set = false;
  private boolean tokyo_closing_price_is_modified = false;
  private boolean tokyo_closing_price_time_is_set = false;
  private boolean tokyo_closing_price_time_is_modified = false;
  private boolean tokyo_closing_price_type_is_set = false;
  private boolean tokyo_closing_price_type_is_modified = false;
  private boolean oosaka_closing_price_is_set = false;
  private boolean oosaka_closing_price_is_modified = false;
  private boolean oosaka_closing_price_time_is_set = false;
  private boolean oosaka_closing_price_time_is_modified = false;
  private boolean oosaka_closing_price_type_is_set = false;
  private boolean oosaka_closing_price_type_is_modified = false;
  private boolean nagoya_closing_price_is_set = false;
  private boolean nagoya_closing_price_is_modified = false;
  private boolean nagoya_closing_price_time_is_set = false;
  private boolean nagoya_closing_price_time_is_modified = false;
  private boolean nagoya_closing_price_type_is_set = false;
  private boolean nagoya_closing_price_type_is_modified = false;
  private boolean other_market_id_is_set = false;
  private boolean other_market_id_is_modified = false;
  private boolean other_closing_price_is_set = false;
  private boolean other_closing_price_is_modified = false;
  private boolean other_closing_price_time_is_set = false;
  private boolean other_closing_price_time_is_modified = false;
  private boolean other_closing_price_type_is_set = false;
  private boolean other_closing_price_type_is_modified = false;
  private boolean primary_closing_price_is_set = false;
  private boolean primary_closing_price_is_modified = false;
  private boolean primary_closing_price_time_is_set = false;
  private boolean primary_closing_price_time_is_modified = false;
  private boolean primary_closing_price_type_is_set = false;
  private boolean primary_closing_price_type_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "product_id=" + product_id
      + "," + "base_date=" + base_date
      + "," + "institution_code=" +institution_code
      + "," + "product_code=" +product_code
      + "," + "product_type=" +product_type
      + "," + "tokyo_closing_price=" +tokyo_closing_price
      + "," + "tokyo_closing_price_time=" +tokyo_closing_price_time
      + "," + "tokyo_closing_price_type=" +tokyo_closing_price_type
      + "," + "oosaka_closing_price=" +oosaka_closing_price
      + "," + "oosaka_closing_price_time=" +oosaka_closing_price_time
      + "," + "oosaka_closing_price_type=" +oosaka_closing_price_type
      + "," + "nagoya_closing_price=" +nagoya_closing_price
      + "," + "nagoya_closing_price_time=" +nagoya_closing_price_time
      + "," + "nagoya_closing_price_type=" +nagoya_closing_price_type
      + "," + "other_market_id=" +other_market_id
      + "," + "other_closing_price=" +other_closing_price
      + "," + "other_closing_price_time=" +other_closing_price_time
      + "," + "other_closing_price_type=" +other_closing_price_type
      + "," + "primary_closing_price=" +primary_closing_price
      + "," + "primary_closing_price_time=" +primary_closing_price_time
      + "," + "primary_closing_price_type=" +primary_closing_price_type
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のQuoteClosingPriceParamsオブジェクトを作成します。 
   */
  public QuoteClosingPriceParams() {
    product_id_is_modified = true;
    base_date_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のQuoteClosingPriceRowオブジェクトの値を利用してQuoteClosingPriceParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するQuoteClosingPriceRowオブジェクト 
   */
  public QuoteClosingPriceParams( QuoteClosingPriceRow p_row )
  {
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    base_date = p_row.getBaseDate();
    base_date_is_set = p_row.getBaseDateIsSet();
    base_date_is_modified = p_row.getBaseDateIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    tokyo_closing_price = p_row.getTokyoClosingPrice();
    tokyo_closing_price_is_set = p_row.getTokyoClosingPriceIsSet();
    tokyo_closing_price_is_modified = p_row.getTokyoClosingPriceIsModified();
    tokyo_closing_price_time = p_row.getTokyoClosingPriceTime();
    tokyo_closing_price_time_is_set = p_row.getTokyoClosingPriceTimeIsSet();
    tokyo_closing_price_time_is_modified = p_row.getTokyoClosingPriceTimeIsModified();
    tokyo_closing_price_type = p_row.getTokyoClosingPriceType();
    tokyo_closing_price_type_is_set = p_row.getTokyoClosingPriceTypeIsSet();
    tokyo_closing_price_type_is_modified = p_row.getTokyoClosingPriceTypeIsModified();
    oosaka_closing_price = p_row.getOosakaClosingPrice();
    oosaka_closing_price_is_set = p_row.getOosakaClosingPriceIsSet();
    oosaka_closing_price_is_modified = p_row.getOosakaClosingPriceIsModified();
    oosaka_closing_price_time = p_row.getOosakaClosingPriceTime();
    oosaka_closing_price_time_is_set = p_row.getOosakaClosingPriceTimeIsSet();
    oosaka_closing_price_time_is_modified = p_row.getOosakaClosingPriceTimeIsModified();
    oosaka_closing_price_type = p_row.getOosakaClosingPriceType();
    oosaka_closing_price_type_is_set = p_row.getOosakaClosingPriceTypeIsSet();
    oosaka_closing_price_type_is_modified = p_row.getOosakaClosingPriceTypeIsModified();
    nagoya_closing_price = p_row.getNagoyaClosingPrice();
    nagoya_closing_price_is_set = p_row.getNagoyaClosingPriceIsSet();
    nagoya_closing_price_is_modified = p_row.getNagoyaClosingPriceIsModified();
    nagoya_closing_price_time = p_row.getNagoyaClosingPriceTime();
    nagoya_closing_price_time_is_set = p_row.getNagoyaClosingPriceTimeIsSet();
    nagoya_closing_price_time_is_modified = p_row.getNagoyaClosingPriceTimeIsModified();
    nagoya_closing_price_type = p_row.getNagoyaClosingPriceType();
    nagoya_closing_price_type_is_set = p_row.getNagoyaClosingPriceTypeIsSet();
    nagoya_closing_price_type_is_modified = p_row.getNagoyaClosingPriceTypeIsModified();
    if ( !p_row.getOtherMarketIdIsNull() )
      other_market_id = new Long( p_row.getOtherMarketId() );
    other_market_id_is_set = p_row.getOtherMarketIdIsSet();
    other_market_id_is_modified = p_row.getOtherMarketIdIsModified();
    other_closing_price = p_row.getOtherClosingPrice();
    other_closing_price_is_set = p_row.getOtherClosingPriceIsSet();
    other_closing_price_is_modified = p_row.getOtherClosingPriceIsModified();
    other_closing_price_time = p_row.getOtherClosingPriceTime();
    other_closing_price_time_is_set = p_row.getOtherClosingPriceTimeIsSet();
    other_closing_price_time_is_modified = p_row.getOtherClosingPriceTimeIsModified();
    other_closing_price_type = p_row.getOtherClosingPriceType();
    other_closing_price_type_is_set = p_row.getOtherClosingPriceTypeIsSet();
    other_closing_price_type_is_modified = p_row.getOtherClosingPriceTypeIsModified();
    primary_closing_price = p_row.getPrimaryClosingPrice();
    primary_closing_price_is_set = p_row.getPrimaryClosingPriceIsSet();
    primary_closing_price_is_modified = p_row.getPrimaryClosingPriceIsModified();
    primary_closing_price_time = p_row.getPrimaryClosingPriceTime();
    primary_closing_price_time_is_set = p_row.getPrimaryClosingPriceTimeIsSet();
    primary_closing_price_time_is_modified = p_row.getPrimaryClosingPriceTimeIsModified();
    primary_closing_price_type = p_row.getPrimaryClosingPriceType();
    primary_closing_price_type_is_set = p_row.getPrimaryClosingPriceTypeIsSet();
    primary_closing_price_type_is_modified = p_row.getPrimaryClosingPriceTypeIsModified();
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
      institution_code_is_set = true;
      institution_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      tokyo_closing_price_is_set = true;
      tokyo_closing_price_is_modified = true;
      tokyo_closing_price_time_is_set = true;
      tokyo_closing_price_time_is_modified = true;
      tokyo_closing_price_type_is_set = true;
      tokyo_closing_price_type_is_modified = true;
      oosaka_closing_price_is_set = true;
      oosaka_closing_price_is_modified = true;
      oosaka_closing_price_time_is_set = true;
      oosaka_closing_price_time_is_modified = true;
      oosaka_closing_price_type_is_set = true;
      oosaka_closing_price_type_is_modified = true;
      nagoya_closing_price_is_set = true;
      nagoya_closing_price_is_modified = true;
      nagoya_closing_price_time_is_set = true;
      nagoya_closing_price_time_is_modified = true;
      nagoya_closing_price_type_is_set = true;
      nagoya_closing_price_type_is_modified = true;
      other_market_id_is_set = true;
      other_market_id_is_modified = true;
      other_closing_price_is_set = true;
      other_closing_price_is_modified = true;
      other_closing_price_time_is_set = true;
      other_closing_price_time_is_modified = true;
      other_closing_price_type_is_set = true;
      other_closing_price_type_is_modified = true;
      primary_closing_price_is_set = true;
      primary_closing_price_is_modified = true;
      primary_closing_price_time_is_set = true;
      primary_closing_price_time_is_modified = true;
      primary_closing_price_type_is_set = true;
      primary_closing_price_type_is_modified = true;
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
    if ( !( other instanceof QuoteClosingPriceRow ) )
       return false;
    return fieldsEqual( (QuoteClosingPriceRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のQuoteClosingPriceRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( QuoteClosingPriceRow row )
  {
    if ( product_id != row.getProductId() )
      return false;
    if ( base_date == null ) {
      if ( row.getBaseDate() != null )
        return false;
    } else if ( !base_date.equals( row.getBaseDate() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( tokyo_closing_price != row.getTokyoClosingPrice() )
      return false;
    if ( tokyo_closing_price_time == null ) {
      if ( row.getTokyoClosingPriceTime() != null )
        return false;
    } else if ( !tokyo_closing_price_time.equals( row.getTokyoClosingPriceTime() ) ) {
        return false;
    }
    if ( tokyo_closing_price_type == null ) {
      if ( row.getTokyoClosingPriceType() != null )
        return false;
    } else if ( !tokyo_closing_price_type.equals( row.getTokyoClosingPriceType() ) ) {
        return false;
    }
    if ( oosaka_closing_price != row.getOosakaClosingPrice() )
      return false;
    if ( oosaka_closing_price_time == null ) {
      if ( row.getOosakaClosingPriceTime() != null )
        return false;
    } else if ( !oosaka_closing_price_time.equals( row.getOosakaClosingPriceTime() ) ) {
        return false;
    }
    if ( oosaka_closing_price_type == null ) {
      if ( row.getOosakaClosingPriceType() != null )
        return false;
    } else if ( !oosaka_closing_price_type.equals( row.getOosakaClosingPriceType() ) ) {
        return false;
    }
    if ( nagoya_closing_price != row.getNagoyaClosingPrice() )
      return false;
    if ( nagoya_closing_price_time == null ) {
      if ( row.getNagoyaClosingPriceTime() != null )
        return false;
    } else if ( !nagoya_closing_price_time.equals( row.getNagoyaClosingPriceTime() ) ) {
        return false;
    }
    if ( nagoya_closing_price_type == null ) {
      if ( row.getNagoyaClosingPriceType() != null )
        return false;
    } else if ( !nagoya_closing_price_type.equals( row.getNagoyaClosingPriceType() ) ) {
        return false;
    }
    if ( other_market_id == null ) {
      if ( !row.getOtherMarketIdIsNull() )
        return false;
    } else if ( row.getOtherMarketIdIsNull() || ( other_market_id.longValue() != row.getOtherMarketId() ) ) {
        return false;
    }
    if ( other_closing_price != row.getOtherClosingPrice() )
      return false;
    if ( other_closing_price_time == null ) {
      if ( row.getOtherClosingPriceTime() != null )
        return false;
    } else if ( !other_closing_price_time.equals( row.getOtherClosingPriceTime() ) ) {
        return false;
    }
    if ( other_closing_price_type == null ) {
      if ( row.getOtherClosingPriceType() != null )
        return false;
    } else if ( !other_closing_price_type.equals( row.getOtherClosingPriceType() ) ) {
        return false;
    }
    if ( primary_closing_price != row.getPrimaryClosingPrice() )
      return false;
    if ( primary_closing_price_time == null ) {
      if ( row.getPrimaryClosingPriceTime() != null )
        return false;
    } else if ( !primary_closing_price_time.equals( row.getPrimaryClosingPriceTime() ) ) {
        return false;
    }
    if ( primary_closing_price_type == null ) {
      if ( row.getPrimaryClosingPriceType() != null )
        return false;
    } else if ( !primary_closing_price_type.equals( row.getPrimaryClosingPriceType() ) ) {
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
      return  ((int) product_id)
        + (base_date!=null? base_date.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + ((int) tokyo_closing_price)
        + (tokyo_closing_price_time!=null? tokyo_closing_price_time.hashCode(): 0) 
        + (tokyo_closing_price_type!=null? tokyo_closing_price_type.hashCode(): 0) 
        + ((int) oosaka_closing_price)
        + (oosaka_closing_price_time!=null? oosaka_closing_price_time.hashCode(): 0) 
        + (oosaka_closing_price_type!=null? oosaka_closing_price_type.hashCode(): 0) 
        + ((int) nagoya_closing_price)
        + (nagoya_closing_price_time!=null? nagoya_closing_price_time.hashCode(): 0) 
        + (nagoya_closing_price_type!=null? nagoya_closing_price_type.hashCode(): 0) 
        + (other_market_id!=null? other_market_id.hashCode(): 0) 
        + ((int) other_closing_price)
        + (other_closing_price_time!=null? other_closing_price_time.hashCode(): 0) 
        + (other_closing_price_type!=null? other_closing_price_type.hashCode(): 0) 
        + ((int) primary_closing_price)
        + (primary_closing_price_time!=null? primary_closing_price_time.hashCode(): 0) 
        + (primary_closing_price_type!=null? primary_closing_price_type.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_code' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("product_id",new Long(product_id));
		map.put("base_date",base_date);
		map.put("institution_code",institution_code);
		map.put("product_code",product_code);
		map.put("product_type",product_type);
		if ( tokyo_closing_price_is_set )
			map.put("tokyo_closing_price",new Double(tokyo_closing_price));
		if ( tokyo_closing_price_time != null )
			map.put("tokyo_closing_price_time",tokyo_closing_price_time);
		if ( tokyo_closing_price_type_is_set )
			map.put("tokyo_closing_price_type",tokyo_closing_price_type);
		if ( oosaka_closing_price_is_set )
			map.put("oosaka_closing_price",new Double(oosaka_closing_price));
		if ( oosaka_closing_price_time != null )
			map.put("oosaka_closing_price_time",oosaka_closing_price_time);
		if ( oosaka_closing_price_type_is_set )
			map.put("oosaka_closing_price_type",oosaka_closing_price_type);
		if ( nagoya_closing_price_is_set )
			map.put("nagoya_closing_price",new Double(nagoya_closing_price));
		if ( nagoya_closing_price_time != null )
			map.put("nagoya_closing_price_time",nagoya_closing_price_time);
		if ( nagoya_closing_price_type_is_set )
			map.put("nagoya_closing_price_type",nagoya_closing_price_type);
		if ( other_market_id != null )
			map.put("other_market_id",other_market_id);
		if ( other_closing_price_is_set )
			map.put("other_closing_price",new Double(other_closing_price));
		if ( other_closing_price_time != null )
			map.put("other_closing_price_time",other_closing_price_time);
		if ( other_closing_price_type_is_set )
			map.put("other_closing_price_type",other_closing_price_type);
		if ( primary_closing_price_is_set )
			map.put("primary_closing_price",new Double(primary_closing_price));
		if ( primary_closing_price_time != null )
			map.put("primary_closing_price_time",primary_closing_price_time);
		if ( primary_closing_price_type_is_set )
			map.put("primary_closing_price_type",primary_closing_price_type);
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
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( tokyo_closing_price_is_modified )
			map.put("tokyo_closing_price",new Double(tokyo_closing_price));
		if ( tokyo_closing_price_time_is_modified )
			map.put("tokyo_closing_price_time",tokyo_closing_price_time);
		if ( tokyo_closing_price_type_is_modified )
			map.put("tokyo_closing_price_type",tokyo_closing_price_type);
		if ( oosaka_closing_price_is_modified )
			map.put("oosaka_closing_price",new Double(oosaka_closing_price));
		if ( oosaka_closing_price_time_is_modified )
			map.put("oosaka_closing_price_time",oosaka_closing_price_time);
		if ( oosaka_closing_price_type_is_modified )
			map.put("oosaka_closing_price_type",oosaka_closing_price_type);
		if ( nagoya_closing_price_is_modified )
			map.put("nagoya_closing_price",new Double(nagoya_closing_price));
		if ( nagoya_closing_price_time_is_modified )
			map.put("nagoya_closing_price_time",nagoya_closing_price_time);
		if ( nagoya_closing_price_type_is_modified )
			map.put("nagoya_closing_price_type",nagoya_closing_price_type);
		if ( other_market_id_is_modified )
			map.put("other_market_id",other_market_id);
		if ( other_closing_price_is_modified )
			map.put("other_closing_price",new Double(other_closing_price));
		if ( other_closing_price_time_is_modified )
			map.put("other_closing_price_time",other_closing_price_time);
		if ( other_closing_price_type_is_modified )
			map.put("other_closing_price_type",other_closing_price_type);
		if ( primary_closing_price_is_modified )
			map.put("primary_closing_price",new Double(primary_closing_price));
		if ( primary_closing_price_time_is_modified )
			map.put("primary_closing_price_time",primary_closing_price_time);
		if ( primary_closing_price_type_is_modified )
			map.put("primary_closing_price_type",primary_closing_price_type);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( product_code_is_set )
				map.put("product_code",product_code);
			if ( product_type_is_set )
				map.put("product_type",product_type);
			if ( tokyo_closing_price_is_set )
				map.put("tokyo_closing_price",new Double(tokyo_closing_price));
			map.put("tokyo_closing_price_time",tokyo_closing_price_time);
			if ( tokyo_closing_price_type_is_set )
				map.put("tokyo_closing_price_type",tokyo_closing_price_type);
			if ( oosaka_closing_price_is_set )
				map.put("oosaka_closing_price",new Double(oosaka_closing_price));
			map.put("oosaka_closing_price_time",oosaka_closing_price_time);
			if ( oosaka_closing_price_type_is_set )
				map.put("oosaka_closing_price_type",oosaka_closing_price_type);
			if ( nagoya_closing_price_is_set )
				map.put("nagoya_closing_price",new Double(nagoya_closing_price));
			map.put("nagoya_closing_price_time",nagoya_closing_price_time);
			if ( nagoya_closing_price_type_is_set )
				map.put("nagoya_closing_price_type",nagoya_closing_price_type);
			map.put("other_market_id",other_market_id);
			if ( other_closing_price_is_set )
				map.put("other_closing_price",new Double(other_closing_price));
			map.put("other_closing_price_time",other_closing_price_time);
			if ( other_closing_price_type_is_set )
				map.put("other_closing_price_type",other_closing_price_type);
			if ( primary_closing_price_is_set )
				map.put("primary_closing_price",new Double(primary_closing_price));
			map.put("primary_closing_price_time",primary_closing_price_time);
			if ( primary_closing_price_type_is_set )
				map.put("primary_closing_price_type",primary_closing_price_type);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>base_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBaseDate()
  {
    return base_date;
  }


  /** 
   * <em>base_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBaseDateIsSet() {
    return base_date_is_set;
  }


  /** 
   * <em>base_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBaseDateIsModified() {
    return base_date_is_modified;
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
   * <em>tokyo_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTokyoClosingPrice()
  {
    return tokyo_closing_price;
  }


  /** 
   * <em>tokyo_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokyoClosingPriceIsSet() {
    return tokyo_closing_price_is_set;
  }


  /** 
   * <em>tokyo_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokyoClosingPriceIsModified() {
    return tokyo_closing_price_is_modified;
  }


  /** 
   * <em>tokyo_closing_price_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTokyoClosingPriceTime()
  {
    return tokyo_closing_price_time;
  }


  /** 
   * <em>tokyo_closing_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokyoClosingPriceTimeIsSet() {
    return tokyo_closing_price_time_is_set;
  }


  /** 
   * <em>tokyo_closing_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokyoClosingPriceTimeIsModified() {
    return tokyo_closing_price_time_is_modified;
  }


  /** 
   * <em>tokyo_closing_price_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTokyoClosingPriceType()
  {
    return tokyo_closing_price_type;
  }


  /** 
   * <em>tokyo_closing_price_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokyoClosingPriceTypeIsSet() {
    return tokyo_closing_price_type_is_set;
  }


  /** 
   * <em>tokyo_closing_price_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokyoClosingPriceTypeIsModified() {
    return tokyo_closing_price_type_is_modified;
  }


  /** 
   * <em>oosaka_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOosakaClosingPrice()
  {
    return oosaka_closing_price;
  }


  /** 
   * <em>oosaka_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOosakaClosingPriceIsSet() {
    return oosaka_closing_price_is_set;
  }


  /** 
   * <em>oosaka_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOosakaClosingPriceIsModified() {
    return oosaka_closing_price_is_modified;
  }


  /** 
   * <em>oosaka_closing_price_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOosakaClosingPriceTime()
  {
    return oosaka_closing_price_time;
  }


  /** 
   * <em>oosaka_closing_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOosakaClosingPriceTimeIsSet() {
    return oosaka_closing_price_time_is_set;
  }


  /** 
   * <em>oosaka_closing_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOosakaClosingPriceTimeIsModified() {
    return oosaka_closing_price_time_is_modified;
  }


  /** 
   * <em>oosaka_closing_price_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOosakaClosingPriceType()
  {
    return oosaka_closing_price_type;
  }


  /** 
   * <em>oosaka_closing_price_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOosakaClosingPriceTypeIsSet() {
    return oosaka_closing_price_type_is_set;
  }


  /** 
   * <em>oosaka_closing_price_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOosakaClosingPriceTypeIsModified() {
    return oosaka_closing_price_type_is_modified;
  }


  /** 
   * <em>nagoya_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNagoyaClosingPrice()
  {
    return nagoya_closing_price;
  }


  /** 
   * <em>nagoya_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNagoyaClosingPriceIsSet() {
    return nagoya_closing_price_is_set;
  }


  /** 
   * <em>nagoya_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNagoyaClosingPriceIsModified() {
    return nagoya_closing_price_is_modified;
  }


  /** 
   * <em>nagoya_closing_price_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getNagoyaClosingPriceTime()
  {
    return nagoya_closing_price_time;
  }


  /** 
   * <em>nagoya_closing_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNagoyaClosingPriceTimeIsSet() {
    return nagoya_closing_price_time_is_set;
  }


  /** 
   * <em>nagoya_closing_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNagoyaClosingPriceTimeIsModified() {
    return nagoya_closing_price_time_is_modified;
  }


  /** 
   * <em>nagoya_closing_price_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNagoyaClosingPriceType()
  {
    return nagoya_closing_price_type;
  }


  /** 
   * <em>nagoya_closing_price_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNagoyaClosingPriceTypeIsSet() {
    return nagoya_closing_price_type_is_set;
  }


  /** 
   * <em>nagoya_closing_price_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNagoyaClosingPriceTypeIsModified() {
    return nagoya_closing_price_type_is_modified;
  }


  /** 
   * <em>other_market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOtherMarketId()
  {
    return ( other_market_id==null? ((long)0): other_market_id.longValue() );
  }


  /** 
   * <em>other_market_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOtherMarketIdIsNull()
  {
    return other_market_id == null;
  }


  /** 
   * <em>other_market_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherMarketIdIsSet() {
    return other_market_id_is_set;
  }


  /** 
   * <em>other_market_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherMarketIdIsModified() {
    return other_market_id_is_modified;
  }


  /** 
   * <em>other_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOtherClosingPrice()
  {
    return other_closing_price;
  }


  /** 
   * <em>other_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherClosingPriceIsSet() {
    return other_closing_price_is_set;
  }


  /** 
   * <em>other_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherClosingPriceIsModified() {
    return other_closing_price_is_modified;
  }


  /** 
   * <em>other_closing_price_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOtherClosingPriceTime()
  {
    return other_closing_price_time;
  }


  /** 
   * <em>other_closing_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherClosingPriceTimeIsSet() {
    return other_closing_price_time_is_set;
  }


  /** 
   * <em>other_closing_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherClosingPriceTimeIsModified() {
    return other_closing_price_time_is_modified;
  }


  /** 
   * <em>other_closing_price_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOtherClosingPriceType()
  {
    return other_closing_price_type;
  }


  /** 
   * <em>other_closing_price_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherClosingPriceTypeIsSet() {
    return other_closing_price_type_is_set;
  }


  /** 
   * <em>other_closing_price_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherClosingPriceTypeIsModified() {
    return other_closing_price_type_is_modified;
  }


  /** 
   * <em>primary_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPrimaryClosingPrice()
  {
    return primary_closing_price;
  }


  /** 
   * <em>primary_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrimaryClosingPriceIsSet() {
    return primary_closing_price_is_set;
  }


  /** 
   * <em>primary_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrimaryClosingPriceIsModified() {
    return primary_closing_price_is_modified;
  }


  /** 
   * <em>primary_closing_price_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getPrimaryClosingPriceTime()
  {
    return primary_closing_price_time;
  }


  /** 
   * <em>primary_closing_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrimaryClosingPriceTimeIsSet() {
    return primary_closing_price_time_is_set;
  }


  /** 
   * <em>primary_closing_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrimaryClosingPriceTimeIsModified() {
    return primary_closing_price_time_is_modified;
  }


  /** 
   * <em>primary_closing_price_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPrimaryClosingPriceType()
  {
    return primary_closing_price_type;
  }


  /** 
   * <em>primary_closing_price_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrimaryClosingPriceTypeIsSet() {
    return primary_closing_price_type_is_set;
  }


  /** 
   * <em>primary_closing_price_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrimaryClosingPriceTypeIsModified() {
    return primary_closing_price_type_is_modified;
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
    return new QuoteClosingPricePK(product_id, base_date);
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
   * <em>base_date</em>カラムの値を設定します。 
   *
   * @@param p_baseDate <em>base_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setBaseDate( String p_baseDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_date = p_baseDate;
    base_date_is_set = true;
    base_date_is_modified = true;
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
   * <em>tokyo_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_tokyoClosingPrice <em>tokyo_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTokyoClosingPrice( double p_tokyoClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tokyo_closing_price = p_tokyoClosingPrice;
    tokyo_closing_price_is_set = true;
    tokyo_closing_price_is_modified = true;
  }


  /** 
   * <em>tokyo_closing_price_time</em>カラムの値を設定します。 
   *
   * @@param p_tokyoClosingPriceTime <em>tokyo_closing_price_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTokyoClosingPriceTime( java.sql.Timestamp p_tokyoClosingPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tokyo_closing_price_time = p_tokyoClosingPriceTime;
    tokyo_closing_price_time_is_set = true;
    tokyo_closing_price_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>tokyo_closing_price_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTokyoClosingPriceTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    tokyo_closing_price_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    tokyo_closing_price_time_is_set = true;
    tokyo_closing_price_time_is_modified = true;
  }


  /** 
   * <em>tokyo_closing_price_type</em>カラムの値を設定します。 
   *
   * @@param p_tokyoClosingPriceType <em>tokyo_closing_price_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTokyoClosingPriceType( String p_tokyoClosingPriceType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tokyo_closing_price_type = p_tokyoClosingPriceType;
    tokyo_closing_price_type_is_set = true;
    tokyo_closing_price_type_is_modified = true;
  }


  /** 
   * <em>oosaka_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_oosakaClosingPrice <em>oosaka_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOosakaClosingPrice( double p_oosakaClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    oosaka_closing_price = p_oosakaClosingPrice;
    oosaka_closing_price_is_set = true;
    oosaka_closing_price_is_modified = true;
  }


  /** 
   * <em>oosaka_closing_price_time</em>カラムの値を設定します。 
   *
   * @@param p_oosakaClosingPriceTime <em>oosaka_closing_price_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOosakaClosingPriceTime( java.sql.Timestamp p_oosakaClosingPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    oosaka_closing_price_time = p_oosakaClosingPriceTime;
    oosaka_closing_price_time_is_set = true;
    oosaka_closing_price_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>oosaka_closing_price_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOosakaClosingPriceTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    oosaka_closing_price_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    oosaka_closing_price_time_is_set = true;
    oosaka_closing_price_time_is_modified = true;
  }


  /** 
   * <em>oosaka_closing_price_type</em>カラムの値を設定します。 
   *
   * @@param p_oosakaClosingPriceType <em>oosaka_closing_price_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOosakaClosingPriceType( String p_oosakaClosingPriceType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    oosaka_closing_price_type = p_oosakaClosingPriceType;
    oosaka_closing_price_type_is_set = true;
    oosaka_closing_price_type_is_modified = true;
  }


  /** 
   * <em>nagoya_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_nagoyaClosingPrice <em>nagoya_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNagoyaClosingPrice( double p_nagoyaClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    nagoya_closing_price = p_nagoyaClosingPrice;
    nagoya_closing_price_is_set = true;
    nagoya_closing_price_is_modified = true;
  }


  /** 
   * <em>nagoya_closing_price_time</em>カラムの値を設定します。 
   *
   * @@param p_nagoyaClosingPriceTime <em>nagoya_closing_price_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setNagoyaClosingPriceTime( java.sql.Timestamp p_nagoyaClosingPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    nagoya_closing_price_time = p_nagoyaClosingPriceTime;
    nagoya_closing_price_time_is_set = true;
    nagoya_closing_price_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>nagoya_closing_price_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setNagoyaClosingPriceTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    nagoya_closing_price_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    nagoya_closing_price_time_is_set = true;
    nagoya_closing_price_time_is_modified = true;
  }


  /** 
   * <em>nagoya_closing_price_type</em>カラムの値を設定します。 
   *
   * @@param p_nagoyaClosingPriceType <em>nagoya_closing_price_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNagoyaClosingPriceType( String p_nagoyaClosingPriceType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    nagoya_closing_price_type = p_nagoyaClosingPriceType;
    nagoya_closing_price_type_is_set = true;
    nagoya_closing_price_type_is_modified = true;
  }


  /** 
   * <em>other_market_id</em>カラムの値を設定します。 
   *
   * @@param p_otherMarketId <em>other_market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setOtherMarketId( long p_otherMarketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_market_id = new Long(p_otherMarketId);
    other_market_id_is_set = true;
    other_market_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_market_id</em>カラムに値を設定します。 
   */
  public final void setOtherMarketId( Long p_otherMarketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_market_id = p_otherMarketId;
    other_market_id_is_set = true;
    other_market_id_is_modified = true;
  }


  /** 
   * <em>other_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_otherClosingPrice <em>other_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOtherClosingPrice( double p_otherClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_closing_price = p_otherClosingPrice;
    other_closing_price_is_set = true;
    other_closing_price_is_modified = true;
  }


  /** 
   * <em>other_closing_price_time</em>カラムの値を設定します。 
   *
   * @@param p_otherClosingPriceTime <em>other_closing_price_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOtherClosingPriceTime( java.sql.Timestamp p_otherClosingPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_closing_price_time = p_otherClosingPriceTime;
    other_closing_price_time_is_set = true;
    other_closing_price_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>other_closing_price_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOtherClosingPriceTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_closing_price_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    other_closing_price_time_is_set = true;
    other_closing_price_time_is_modified = true;
  }


  /** 
   * <em>other_closing_price_type</em>カラムの値を設定します。 
   *
   * @@param p_otherClosingPriceType <em>other_closing_price_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOtherClosingPriceType( String p_otherClosingPriceType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_closing_price_type = p_otherClosingPriceType;
    other_closing_price_type_is_set = true;
    other_closing_price_type_is_modified = true;
  }


  /** 
   * <em>primary_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_primaryClosingPrice <em>primary_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPrimaryClosingPrice( double p_primaryClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    primary_closing_price = p_primaryClosingPrice;
    primary_closing_price_is_set = true;
    primary_closing_price_is_modified = true;
  }


  /** 
   * <em>primary_closing_price_time</em>カラムの値を設定します。 
   *
   * @@param p_primaryClosingPriceTime <em>primary_closing_price_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setPrimaryClosingPriceTime( java.sql.Timestamp p_primaryClosingPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    primary_closing_price_time = p_primaryClosingPriceTime;
    primary_closing_price_time_is_set = true;
    primary_closing_price_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>primary_closing_price_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setPrimaryClosingPriceTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    primary_closing_price_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    primary_closing_price_time_is_set = true;
    primary_closing_price_time_is_modified = true;
  }


  /** 
   * <em>primary_closing_price_type</em>カラムの値を設定します。 
   *
   * @@param p_primaryClosingPriceType <em>primary_closing_price_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPrimaryClosingPriceType( String p_primaryClosingPriceType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    primary_closing_price_type = p_primaryClosingPriceType;
    primary_closing_price_type_is_set = true;
    primary_closing_price_type_is_modified = true;
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
            case 'b':
                if ( name.equals("base_date") ) {
                    return this.base_date;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("nagoya_closing_price") ) {
                    return new Double( nagoya_closing_price );
                }
                else if ( name.equals("nagoya_closing_price_time") ) {
                    return this.nagoya_closing_price_time;
                }
                else if ( name.equals("nagoya_closing_price_type") ) {
                    return this.nagoya_closing_price_type;
                }
                break;
            case 'o':
                if ( name.equals("oosaka_closing_price") ) {
                    return new Double( oosaka_closing_price );
                }
                else if ( name.equals("oosaka_closing_price_time") ) {
                    return this.oosaka_closing_price_time;
                }
                else if ( name.equals("oosaka_closing_price_type") ) {
                    return this.oosaka_closing_price_type;
                }
                else if ( name.equals("other_market_id") ) {
                    return this.other_market_id;
                }
                else if ( name.equals("other_closing_price") ) {
                    return new Double( other_closing_price );
                }
                else if ( name.equals("other_closing_price_time") ) {
                    return this.other_closing_price_time;
                }
                else if ( name.equals("other_closing_price_type") ) {
                    return this.other_closing_price_type;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("primary_closing_price") ) {
                    return new Double( primary_closing_price );
                }
                else if ( name.equals("primary_closing_price_time") ) {
                    return this.primary_closing_price_time;
                }
                else if ( name.equals("primary_closing_price_type") ) {
                    return this.primary_closing_price_type;
                }
                break;
            case 't':
                if ( name.equals("tokyo_closing_price") ) {
                    return new Double( tokyo_closing_price );
                }
                else if ( name.equals("tokyo_closing_price_time") ) {
                    return this.tokyo_closing_price_time;
                }
                else if ( name.equals("tokyo_closing_price_type") ) {
                    return this.tokyo_closing_price_type;
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
            case 'b':
                if ( name.equals("base_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'base_date' must be String: '"+value+"' is not." );
                    this.base_date = (String) value;
                    if (this.base_date_is_set)
                        this.base_date_is_modified = true;
                    this.base_date_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
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
            case 'i':
                if ( name.equals("institution_code") ) {
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
                if ( name.equals("last_updated_timestamp") ) {
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
            case 'n':
                if ( name.equals("nagoya_closing_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'nagoya_closing_price' must be Double: '"+value+"' is not." );
                    this.nagoya_closing_price = ((Double) value).doubleValue();
                    if (this.nagoya_closing_price_is_set)
                        this.nagoya_closing_price_is_modified = true;
                    this.nagoya_closing_price_is_set = true;
                    return;
                }
                else if ( name.equals("nagoya_closing_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'nagoya_closing_price_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.nagoya_closing_price_time = (java.sql.Timestamp) value;
                    if (this.nagoya_closing_price_time_is_set)
                        this.nagoya_closing_price_time_is_modified = true;
                    this.nagoya_closing_price_time_is_set = true;
                    return;
                }
                else if ( name.equals("nagoya_closing_price_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'nagoya_closing_price_type' must be String: '"+value+"' is not." );
                    this.nagoya_closing_price_type = (String) value;
                    if (this.nagoya_closing_price_type_is_set)
                        this.nagoya_closing_price_type_is_modified = true;
                    this.nagoya_closing_price_type_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("oosaka_closing_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'oosaka_closing_price' must be Double: '"+value+"' is not." );
                    this.oosaka_closing_price = ((Double) value).doubleValue();
                    if (this.oosaka_closing_price_is_set)
                        this.oosaka_closing_price_is_modified = true;
                    this.oosaka_closing_price_is_set = true;
                    return;
                }
                else if ( name.equals("oosaka_closing_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'oosaka_closing_price_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.oosaka_closing_price_time = (java.sql.Timestamp) value;
                    if (this.oosaka_closing_price_time_is_set)
                        this.oosaka_closing_price_time_is_modified = true;
                    this.oosaka_closing_price_time_is_set = true;
                    return;
                }
                else if ( name.equals("oosaka_closing_price_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'oosaka_closing_price_type' must be String: '"+value+"' is not." );
                    this.oosaka_closing_price_type = (String) value;
                    if (this.oosaka_closing_price_type_is_set)
                        this.oosaka_closing_price_type_is_modified = true;
                    this.oosaka_closing_price_type_is_set = true;
                    return;
                }
                else if ( name.equals("other_market_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'other_market_id' must be Long: '"+value+"' is not." );
                    this.other_market_id = (Long) value;
                    if (this.other_market_id_is_set)
                        this.other_market_id_is_modified = true;
                    this.other_market_id_is_set = true;
                    return;
                }
                else if ( name.equals("other_closing_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_closing_price' must be Double: '"+value+"' is not." );
                    this.other_closing_price = ((Double) value).doubleValue();
                    if (this.other_closing_price_is_set)
                        this.other_closing_price_is_modified = true;
                    this.other_closing_price_is_set = true;
                    return;
                }
                else if ( name.equals("other_closing_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'other_closing_price_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.other_closing_price_time = (java.sql.Timestamp) value;
                    if (this.other_closing_price_time_is_set)
                        this.other_closing_price_time_is_modified = true;
                    this.other_closing_price_time_is_set = true;
                    return;
                }
                else if ( name.equals("other_closing_price_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'other_closing_price_type' must be String: '"+value+"' is not." );
                    this.other_closing_price_type = (String) value;
                    if (this.other_closing_price_type_is_set)
                        this.other_closing_price_type_is_modified = true;
                    this.other_closing_price_type_is_set = true;
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
                else if ( name.equals("product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
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
                else if ( name.equals("primary_closing_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'primary_closing_price' must be Double: '"+value+"' is not." );
                    this.primary_closing_price = ((Double) value).doubleValue();
                    if (this.primary_closing_price_is_set)
                        this.primary_closing_price_is_modified = true;
                    this.primary_closing_price_is_set = true;
                    return;
                }
                else if ( name.equals("primary_closing_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'primary_closing_price_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.primary_closing_price_time = (java.sql.Timestamp) value;
                    if (this.primary_closing_price_time_is_set)
                        this.primary_closing_price_time_is_modified = true;
                    this.primary_closing_price_time_is_set = true;
                    return;
                }
                else if ( name.equals("primary_closing_price_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'primary_closing_price_type' must be String: '"+value+"' is not." );
                    this.primary_closing_price_type = (String) value;
                    if (this.primary_closing_price_type_is_set)
                        this.primary_closing_price_type_is_modified = true;
                    this.primary_closing_price_type_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tokyo_closing_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'tokyo_closing_price' must be Double: '"+value+"' is not." );
                    this.tokyo_closing_price = ((Double) value).doubleValue();
                    if (this.tokyo_closing_price_is_set)
                        this.tokyo_closing_price_is_modified = true;
                    this.tokyo_closing_price_is_set = true;
                    return;
                }
                else if ( name.equals("tokyo_closing_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'tokyo_closing_price_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.tokyo_closing_price_time = (java.sql.Timestamp) value;
                    if (this.tokyo_closing_price_time_is_set)
                        this.tokyo_closing_price_time_is_modified = true;
                    this.tokyo_closing_price_time_is_set = true;
                    return;
                }
                else if ( name.equals("tokyo_closing_price_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tokyo_closing_price_type' must be String: '"+value+"' is not." );
                    this.tokyo_closing_price_type = (String) value;
                    if (this.tokyo_closing_price_type_is_set)
                        this.tokyo_closing_price_type_is_modified = true;
                    this.tokyo_closing_price_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
