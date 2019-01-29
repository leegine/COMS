head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.32.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FeqLastClosingPriceParams.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * feq_last_closing_priceテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link FeqLastClosingPriceRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link FeqLastClosingPriceParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link FeqLastClosingPriceParams}が{@@link FeqLastClosingPriceRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FeqLastClosingPricePK 
 * @@see FeqLastClosingPriceRow 
 */
public class FeqLastClosingPriceParams extends Params implements FeqLastClosingPriceRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "feq_last_closing_price";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = FeqLastClosingPriceRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return FeqLastClosingPriceRow.TYPE;
  }


  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>base_date</em>カラムの値 
   */
  public  java.sql.Timestamp  base_date;

  /** 
   * <em>feq_closing_price</em>カラムの値 
   */
  public  Double  feq_closing_price;

  /** 
   * <em>offshore_product_code</em>カラムの値 
   */
  public  String  offshore_product_code;

  /** 
   * <em>feq_closing_price_market_code</em>カラムの値 
   */
  public  String  feq_closing_price_market_code;

  /** 
   * <em>feq_closing_price_mrkt_code_s</em>カラムの値 
   */
  public  String  feq_closing_price_mrkt_code_s;

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
  private boolean feq_closing_price_is_set = false;
  private boolean feq_closing_price_is_modified = false;
  private boolean offshore_product_code_is_set = false;
  private boolean offshore_product_code_is_modified = false;
  private boolean feq_closing_price_market_code_is_set = false;
  private boolean feq_closing_price_market_code_is_modified = false;
  private boolean feq_closing_price_mrkt_code_s_is_set = false;
  private boolean feq_closing_price_mrkt_code_s_is_modified = false;
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
      + "," + "feq_closing_price=" +feq_closing_price
      + "," + "offshore_product_code=" +offshore_product_code
      + "," + "feq_closing_price_market_code=" +feq_closing_price_market_code
      + "," + "feq_closing_price_mrkt_code_s=" +feq_closing_price_mrkt_code_s
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のFeqLastClosingPriceParamsオブジェクトを作成します。 
   */
  public FeqLastClosingPriceParams() {
    product_id_is_modified = true;
    base_date_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のFeqLastClosingPriceRowオブジェクトの値を利用してFeqLastClosingPriceParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するFeqLastClosingPriceRowオブジェクト 
   */
  public FeqLastClosingPriceParams( FeqLastClosingPriceRow p_row )
  {
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    base_date = p_row.getBaseDate();
    base_date_is_set = p_row.getBaseDateIsSet();
    base_date_is_modified = p_row.getBaseDateIsModified();
    if ( !p_row.getFeqClosingPriceIsNull() )
      feq_closing_price = new Double( p_row.getFeqClosingPrice() );
    feq_closing_price_is_set = p_row.getFeqClosingPriceIsSet();
    feq_closing_price_is_modified = p_row.getFeqClosingPriceIsModified();
    offshore_product_code = p_row.getOffshoreProductCode();
    offshore_product_code_is_set = p_row.getOffshoreProductCodeIsSet();
    offshore_product_code_is_modified = p_row.getOffshoreProductCodeIsModified();
    feq_closing_price_market_code = p_row.getFeqClosingPriceMarketCode();
    feq_closing_price_market_code_is_set = p_row.getFeqClosingPriceMarketCodeIsSet();
    feq_closing_price_market_code_is_modified = p_row.getFeqClosingPriceMarketCodeIsModified();
    feq_closing_price_mrkt_code_s = p_row.getFeqClosingPriceMrktCodeS();
    feq_closing_price_mrkt_code_s_is_set = p_row.getFeqClosingPriceMrktCodeSIsSet();
    feq_closing_price_mrkt_code_s_is_modified = p_row.getFeqClosingPriceMrktCodeSIsModified();
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
      feq_closing_price_is_set = true;
      feq_closing_price_is_modified = true;
      offshore_product_code_is_set = true;
      offshore_product_code_is_modified = true;
      feq_closing_price_market_code_is_set = true;
      feq_closing_price_market_code_is_modified = true;
      feq_closing_price_mrkt_code_s_is_set = true;
      feq_closing_price_mrkt_code_s_is_modified = true;
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
    if ( !( other instanceof FeqLastClosingPriceRow ) )
       return false;
    return fieldsEqual( (FeqLastClosingPriceRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のFeqLastClosingPriceRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( FeqLastClosingPriceRow row )
  {
    if ( product_id != row.getProductId() )
      return false;
    if ( base_date == null ) {
      if ( row.getBaseDate() != null )
        return false;
    } else if ( !base_date.equals( row.getBaseDate() ) ) {
        return false;
    }
    if ( feq_closing_price == null ) {
      if ( !row.getFeqClosingPriceIsNull() )
        return false;
    } else if ( row.getFeqClosingPriceIsNull() || ( feq_closing_price.doubleValue() != row.getFeqClosingPrice() ) ) {
        return false;
    }
    if ( offshore_product_code == null ) {
      if ( row.getOffshoreProductCode() != null )
        return false;
    } else if ( !offshore_product_code.equals( row.getOffshoreProductCode() ) ) {
        return false;
    }
    if ( feq_closing_price_market_code == null ) {
      if ( row.getFeqClosingPriceMarketCode() != null )
        return false;
    } else if ( !feq_closing_price_market_code.equals( row.getFeqClosingPriceMarketCode() ) ) {
        return false;
    }
    if ( feq_closing_price_mrkt_code_s == null ) {
      if ( row.getFeqClosingPriceMrktCodeS() != null )
        return false;
    } else if ( !feq_closing_price_mrkt_code_s.equals( row.getFeqClosingPriceMrktCodeS() ) ) {
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
        + (feq_closing_price!=null? feq_closing_price.hashCode(): 0) 
        + (offshore_product_code!=null? offshore_product_code.hashCode(): 0) 
        + (feq_closing_price_market_code!=null? feq_closing_price_market_code.hashCode(): 0) 
        + (feq_closing_price_mrkt_code_s!=null? feq_closing_price_mrkt_code_s.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !offshore_product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'offshore_product_code' must be set before inserting.");
		if ( !feq_closing_price_market_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'feq_closing_price_market_code' must be set before inserting.");
		if ( !feq_closing_price_mrkt_code_s_is_set )
			throw new IllegalArgumentException("non-nullable field 'feq_closing_price_mrkt_code_s' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("product_id",new Long(product_id));
		map.put("base_date",base_date);
		if ( feq_closing_price != null )
			map.put("feq_closing_price",feq_closing_price);
		map.put("offshore_product_code",offshore_product_code);
		map.put("feq_closing_price_market_code",feq_closing_price_market_code);
		map.put("feq_closing_price_mrkt_code_s",feq_closing_price_mrkt_code_s);
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
		if ( feq_closing_price_is_modified )
			map.put("feq_closing_price",feq_closing_price);
		if ( offshore_product_code_is_modified )
			map.put("offshore_product_code",offshore_product_code);
		if ( feq_closing_price_market_code_is_modified )
			map.put("feq_closing_price_market_code",feq_closing_price_market_code);
		if ( feq_closing_price_mrkt_code_s_is_modified )
			map.put("feq_closing_price_mrkt_code_s",feq_closing_price_mrkt_code_s);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("feq_closing_price",feq_closing_price);
			if ( offshore_product_code_is_set )
				map.put("offshore_product_code",offshore_product_code);
			if ( feq_closing_price_market_code_is_set )
				map.put("feq_closing_price_market_code",feq_closing_price_market_code);
			if ( feq_closing_price_mrkt_code_s_is_set )
				map.put("feq_closing_price_mrkt_code_s",feq_closing_price_mrkt_code_s);
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
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getBaseDate()
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
   * <em>feq_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getFeqClosingPrice()
  {
    return ( feq_closing_price==null? ((double)0): feq_closing_price.doubleValue() );
  }


  /** 
   * <em>feq_closing_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFeqClosingPriceIsNull()
  {
    return feq_closing_price == null;
  }


  /** 
   * <em>feq_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqClosingPriceIsSet() {
    return feq_closing_price_is_set;
  }


  /** 
   * <em>feq_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqClosingPriceIsModified() {
    return feq_closing_price_is_modified;
  }


  /** 
   * <em>offshore_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOffshoreProductCode()
  {
    return offshore_product_code;
  }


  /** 
   * <em>offshore_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOffshoreProductCodeIsSet() {
    return offshore_product_code_is_set;
  }


  /** 
   * <em>offshore_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOffshoreProductCodeIsModified() {
    return offshore_product_code_is_modified;
  }


  /** 
   * <em>feq_closing_price_market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFeqClosingPriceMarketCode()
  {
    return feq_closing_price_market_code;
  }


  /** 
   * <em>feq_closing_price_market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqClosingPriceMarketCodeIsSet() {
    return feq_closing_price_market_code_is_set;
  }


  /** 
   * <em>feq_closing_price_market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqClosingPriceMarketCodeIsModified() {
    return feq_closing_price_market_code_is_modified;
  }


  /** 
   * <em>feq_closing_price_mrkt_code_s</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFeqClosingPriceMrktCodeS()
  {
    return feq_closing_price_mrkt_code_s;
  }


  /** 
   * <em>feq_closing_price_mrkt_code_s</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqClosingPriceMrktCodeSIsSet() {
    return feq_closing_price_mrkt_code_s_is_set;
  }


  /** 
   * <em>feq_closing_price_mrkt_code_s</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqClosingPriceMrktCodeSIsModified() {
    return feq_closing_price_mrkt_code_s_is_modified;
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
    return new FeqLastClosingPricePK(product_id, base_date);
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
   * @@param p_baseDate <em>base_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setBaseDate( java.sql.Timestamp p_baseDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_date = p_baseDate;
    base_date_is_set = true;
    base_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>base_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setBaseDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    base_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    base_date_is_set = true;
    base_date_is_modified = true;
  }


  /** 
   * <em>feq_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_feqClosingPrice <em>feq_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setFeqClosingPrice( double p_feqClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    feq_closing_price = new Double(p_feqClosingPrice);
    feq_closing_price_is_set = true;
    feq_closing_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>feq_closing_price</em>カラムに値を設定します。 
   */
  public final void setFeqClosingPrice( Double p_feqClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    feq_closing_price = p_feqClosingPrice;
    feq_closing_price_is_set = true;
    feq_closing_price_is_modified = true;
  }


  /** 
   * <em>offshore_product_code</em>カラムの値を設定します。 
   *
   * @@param p_offshoreProductCode <em>offshore_product_code</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setOffshoreProductCode( String p_offshoreProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    offshore_product_code = p_offshoreProductCode;
    offshore_product_code_is_set = true;
    offshore_product_code_is_modified = true;
  }


  /** 
   * <em>feq_closing_price_market_code</em>カラムの値を設定します。 
   *
   * @@param p_feqClosingPriceMarketCode <em>feq_closing_price_market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setFeqClosingPriceMarketCode( String p_feqClosingPriceMarketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    feq_closing_price_market_code = p_feqClosingPriceMarketCode;
    feq_closing_price_market_code_is_set = true;
    feq_closing_price_market_code_is_modified = true;
  }


  /** 
   * <em>feq_closing_price_mrkt_code_s</em>カラムの値を設定します。 
   *
   * @@param p_feqClosingPriceMrktCodeS <em>feq_closing_price_mrkt_code_s</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setFeqClosingPriceMrktCodeS( String p_feqClosingPriceMrktCodeS )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    feq_closing_price_mrkt_code_s = p_feqClosingPriceMrktCodeS;
    feq_closing_price_mrkt_code_s_is_set = true;
    feq_closing_price_mrkt_code_s_is_modified = true;
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
            case 'f':
                if ( name.equals("feq_closing_price") ) {
                    return this.feq_closing_price;
                }
                else if ( name.equals("feq_closing_price_market_code") ) {
                    return this.feq_closing_price_market_code;
                }
                else if ( name.equals("feq_closing_price_mrkt_code_s") ) {
                    return this.feq_closing_price_mrkt_code_s;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("offshore_product_code") ) {
                    return this.offshore_product_code;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
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
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'base_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.base_date = (java.sql.Timestamp) value;
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
            case 'f':
                if ( name.equals("feq_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'feq_closing_price' must be Double: '"+value+"' is not." );
                    this.feq_closing_price = (Double) value;
                    if (this.feq_closing_price_is_set)
                        this.feq_closing_price_is_modified = true;
                    this.feq_closing_price_is_set = true;
                    return;
                }
                else if ( name.equals("feq_closing_price_market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'feq_closing_price_market_code' must be String: '"+value+"' is not." );
                    this.feq_closing_price_market_code = (String) value;
                    if (this.feq_closing_price_market_code_is_set)
                        this.feq_closing_price_market_code_is_modified = true;
                    this.feq_closing_price_market_code_is_set = true;
                    return;
                }
                else if ( name.equals("feq_closing_price_mrkt_code_s") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'feq_closing_price_mrkt_code_s' must be String: '"+value+"' is not." );
                    this.feq_closing_price_mrkt_code_s = (String) value;
                    if (this.feq_closing_price_mrkt_code_s_is_set)
                        this.feq_closing_price_mrkt_code_s_is_modified = true;
                    this.feq_closing_price_mrkt_code_s_is_set = true;
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
            case 'o':
                if ( name.equals("offshore_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'offshore_product_code' must be String: '"+value+"' is not." );
                    this.offshore_product_code = (String) value;
                    if (this.offshore_product_code_is_set)
                        this.offshore_product_code_is_modified = true;
                    this.offshore_product_code_is_set = true;
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
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
