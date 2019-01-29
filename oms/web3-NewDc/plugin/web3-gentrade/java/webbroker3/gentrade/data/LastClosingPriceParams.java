head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.23.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	LastClosingPriceParams.java;


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
 * last_closing_priceテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link LastClosingPriceRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link LastClosingPriceParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link LastClosingPriceParams}が{@@link LastClosingPriceRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see LastClosingPricePK 
 * @@see LastClosingPriceRow 
 */
public class LastClosingPriceParams extends Params implements LastClosingPriceRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "last_closing_price";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = LastClosingPriceRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return LastClosingPriceRow.TYPE;
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
   * <em>tokyo_closing_price</em>カラムの値 
   */
  public  Double  tokyo_closing_price;

  /** 
   * <em>oosaka_closing_price</em>カラムの値 
   */
  public  Double  oosaka_closing_price;

  /** 
   * <em>nagoya_closing_price</em>カラムの値 
   */
  public  Double  nagoya_closing_price;

  /** 
   * <em>other_closing_price</em>カラムの値 
   */
  public  Double  other_closing_price;

  /** 
   * <em>primary_closing_price</em>カラムの値 
   */
  public  Double  primary_closing_price;

  /** 
   * <em>error_code</em>カラムの値 
   */
  public  String  error_code;

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
  private boolean tokyo_closing_price_is_set = false;
  private boolean tokyo_closing_price_is_modified = false;
  private boolean oosaka_closing_price_is_set = false;
  private boolean oosaka_closing_price_is_modified = false;
  private boolean nagoya_closing_price_is_set = false;
  private boolean nagoya_closing_price_is_modified = false;
  private boolean other_closing_price_is_set = false;
  private boolean other_closing_price_is_modified = false;
  private boolean primary_closing_price_is_set = false;
  private boolean primary_closing_price_is_modified = false;
  private boolean error_code_is_set = false;
  private boolean error_code_is_modified = false;
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
      + "," + "tokyo_closing_price=" +tokyo_closing_price
      + "," + "oosaka_closing_price=" +oosaka_closing_price
      + "," + "nagoya_closing_price=" +nagoya_closing_price
      + "," + "other_closing_price=" +other_closing_price
      + "," + "primary_closing_price=" +primary_closing_price
      + "," + "error_code=" +error_code
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のLastClosingPriceParamsオブジェクトを作成します。 
   */
  public LastClosingPriceParams() {
    product_id_is_modified = true;
    base_date_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のLastClosingPriceRowオブジェクトの値を利用してLastClosingPriceParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するLastClosingPriceRowオブジェクト 
   */
  public LastClosingPriceParams( LastClosingPriceRow p_row )
  {
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    base_date = p_row.getBaseDate();
    base_date_is_set = p_row.getBaseDateIsSet();
    base_date_is_modified = p_row.getBaseDateIsModified();
    if ( !p_row.getTokyoClosingPriceIsNull() )
      tokyo_closing_price = new Double( p_row.getTokyoClosingPrice() );
    tokyo_closing_price_is_set = p_row.getTokyoClosingPriceIsSet();
    tokyo_closing_price_is_modified = p_row.getTokyoClosingPriceIsModified();
    if ( !p_row.getOosakaClosingPriceIsNull() )
      oosaka_closing_price = new Double( p_row.getOosakaClosingPrice() );
    oosaka_closing_price_is_set = p_row.getOosakaClosingPriceIsSet();
    oosaka_closing_price_is_modified = p_row.getOosakaClosingPriceIsModified();
    if ( !p_row.getNagoyaClosingPriceIsNull() )
      nagoya_closing_price = new Double( p_row.getNagoyaClosingPrice() );
    nagoya_closing_price_is_set = p_row.getNagoyaClosingPriceIsSet();
    nagoya_closing_price_is_modified = p_row.getNagoyaClosingPriceIsModified();
    if ( !p_row.getOtherClosingPriceIsNull() )
      other_closing_price = new Double( p_row.getOtherClosingPrice() );
    other_closing_price_is_set = p_row.getOtherClosingPriceIsSet();
    other_closing_price_is_modified = p_row.getOtherClosingPriceIsModified();
    if ( !p_row.getPrimaryClosingPriceIsNull() )
      primary_closing_price = new Double( p_row.getPrimaryClosingPrice() );
    primary_closing_price_is_set = p_row.getPrimaryClosingPriceIsSet();
    primary_closing_price_is_modified = p_row.getPrimaryClosingPriceIsModified();
    error_code = p_row.getErrorCode();
    error_code_is_set = p_row.getErrorCodeIsSet();
    error_code_is_modified = p_row.getErrorCodeIsModified();
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
      tokyo_closing_price_is_set = true;
      tokyo_closing_price_is_modified = true;
      oosaka_closing_price_is_set = true;
      oosaka_closing_price_is_modified = true;
      nagoya_closing_price_is_set = true;
      nagoya_closing_price_is_modified = true;
      other_closing_price_is_set = true;
      other_closing_price_is_modified = true;
      primary_closing_price_is_set = true;
      primary_closing_price_is_modified = true;
      error_code_is_set = true;
      error_code_is_modified = true;
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
    if ( !( other instanceof LastClosingPriceRow ) )
       return false;
    return fieldsEqual( (LastClosingPriceRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のLastClosingPriceRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( LastClosingPriceRow row )
  {
    if ( product_id != row.getProductId() )
      return false;
    if ( base_date == null ) {
      if ( row.getBaseDate() != null )
        return false;
    } else if ( !base_date.equals( row.getBaseDate() ) ) {
        return false;
    }
    if ( tokyo_closing_price == null ) {
      if ( !row.getTokyoClosingPriceIsNull() )
        return false;
    } else if ( row.getTokyoClosingPriceIsNull() || ( tokyo_closing_price.doubleValue() != row.getTokyoClosingPrice() ) ) {
        return false;
    }
    if ( oosaka_closing_price == null ) {
      if ( !row.getOosakaClosingPriceIsNull() )
        return false;
    } else if ( row.getOosakaClosingPriceIsNull() || ( oosaka_closing_price.doubleValue() != row.getOosakaClosingPrice() ) ) {
        return false;
    }
    if ( nagoya_closing_price == null ) {
      if ( !row.getNagoyaClosingPriceIsNull() )
        return false;
    } else if ( row.getNagoyaClosingPriceIsNull() || ( nagoya_closing_price.doubleValue() != row.getNagoyaClosingPrice() ) ) {
        return false;
    }
    if ( other_closing_price == null ) {
      if ( !row.getOtherClosingPriceIsNull() )
        return false;
    } else if ( row.getOtherClosingPriceIsNull() || ( other_closing_price.doubleValue() != row.getOtherClosingPrice() ) ) {
        return false;
    }
    if ( primary_closing_price == null ) {
      if ( !row.getPrimaryClosingPriceIsNull() )
        return false;
    } else if ( row.getPrimaryClosingPriceIsNull() || ( primary_closing_price.doubleValue() != row.getPrimaryClosingPrice() ) ) {
        return false;
    }
    if ( error_code == null ) {
      if ( row.getErrorCode() != null )
        return false;
    } else if ( !error_code.equals( row.getErrorCode() ) ) {
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
        + (tokyo_closing_price!=null? tokyo_closing_price.hashCode(): 0) 
        + (oosaka_closing_price!=null? oosaka_closing_price.hashCode(): 0) 
        + (nagoya_closing_price!=null? nagoya_closing_price.hashCode(): 0) 
        + (other_closing_price!=null? other_closing_price.hashCode(): 0) 
        + (primary_closing_price!=null? primary_closing_price.hashCode(): 0) 
        + (error_code!=null? error_code.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("product_id",new Long(product_id));
		map.put("base_date",base_date);
		if ( tokyo_closing_price != null )
			map.put("tokyo_closing_price",tokyo_closing_price);
		if ( oosaka_closing_price != null )
			map.put("oosaka_closing_price",oosaka_closing_price);
		if ( nagoya_closing_price != null )
			map.put("nagoya_closing_price",nagoya_closing_price);
		if ( other_closing_price != null )
			map.put("other_closing_price",other_closing_price);
		if ( primary_closing_price != null )
			map.put("primary_closing_price",primary_closing_price);
		if ( error_code != null )
			map.put("error_code",error_code);
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
		if ( tokyo_closing_price_is_modified )
			map.put("tokyo_closing_price",tokyo_closing_price);
		if ( oosaka_closing_price_is_modified )
			map.put("oosaka_closing_price",oosaka_closing_price);
		if ( nagoya_closing_price_is_modified )
			map.put("nagoya_closing_price",nagoya_closing_price);
		if ( other_closing_price_is_modified )
			map.put("other_closing_price",other_closing_price);
		if ( primary_closing_price_is_modified )
			map.put("primary_closing_price",primary_closing_price);
		if ( error_code_is_modified )
			map.put("error_code",error_code);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("tokyo_closing_price",tokyo_closing_price);
			map.put("oosaka_closing_price",oosaka_closing_price);
			map.put("nagoya_closing_price",nagoya_closing_price);
			map.put("other_closing_price",other_closing_price);
			map.put("primary_closing_price",primary_closing_price);
			map.put("error_code",error_code);
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
   * <em>tokyo_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTokyoClosingPrice()
  {
    return ( tokyo_closing_price==null? ((double)0): tokyo_closing_price.doubleValue() );
  }


  /** 
   * <em>tokyo_closing_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTokyoClosingPriceIsNull()
  {
    return tokyo_closing_price == null;
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
   * <em>oosaka_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOosakaClosingPrice()
  {
    return ( oosaka_closing_price==null? ((double)0): oosaka_closing_price.doubleValue() );
  }


  /** 
   * <em>oosaka_closing_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOosakaClosingPriceIsNull()
  {
    return oosaka_closing_price == null;
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
   * <em>nagoya_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNagoyaClosingPrice()
  {
    return ( nagoya_closing_price==null? ((double)0): nagoya_closing_price.doubleValue() );
  }


  /** 
   * <em>nagoya_closing_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNagoyaClosingPriceIsNull()
  {
    return nagoya_closing_price == null;
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
   * <em>other_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOtherClosingPrice()
  {
    return ( other_closing_price==null? ((double)0): other_closing_price.doubleValue() );
  }


  /** 
   * <em>other_closing_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOtherClosingPriceIsNull()
  {
    return other_closing_price == null;
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
   * <em>primary_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPrimaryClosingPrice()
  {
    return ( primary_closing_price==null? ((double)0): primary_closing_price.doubleValue() );
  }


  /** 
   * <em>primary_closing_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPrimaryClosingPriceIsNull()
  {
    return primary_closing_price == null;
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
   * <em>error_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getErrorCode()
  {
    return error_code;
  }


  /** 
   * <em>error_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorCodeIsSet() {
    return error_code_is_set;
  }


  /** 
   * <em>error_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorCodeIsModified() {
    return error_code_is_modified;
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
    return new LastClosingPricePK(product_id, base_date);
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
   * <em>tokyo_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_tokyoClosingPrice <em>tokyo_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTokyoClosingPrice( double p_tokyoClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tokyo_closing_price = new Double(p_tokyoClosingPrice);
    tokyo_closing_price_is_set = true;
    tokyo_closing_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>tokyo_closing_price</em>カラムに値を設定します。 
   */
  public final void setTokyoClosingPrice( Double p_tokyoClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    tokyo_closing_price = p_tokyoClosingPrice;
    tokyo_closing_price_is_set = true;
    tokyo_closing_price_is_modified = true;
  }


  /** 
   * <em>oosaka_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_oosakaClosingPrice <em>oosaka_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOosakaClosingPrice( double p_oosakaClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    oosaka_closing_price = new Double(p_oosakaClosingPrice);
    oosaka_closing_price_is_set = true;
    oosaka_closing_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>oosaka_closing_price</em>カラムに値を設定します。 
   */
  public final void setOosakaClosingPrice( Double p_oosakaClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    oosaka_closing_price = p_oosakaClosingPrice;
    oosaka_closing_price_is_set = true;
    oosaka_closing_price_is_modified = true;
  }


  /** 
   * <em>nagoya_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_nagoyaClosingPrice <em>nagoya_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNagoyaClosingPrice( double p_nagoyaClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    nagoya_closing_price = new Double(p_nagoyaClosingPrice);
    nagoya_closing_price_is_set = true;
    nagoya_closing_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>nagoya_closing_price</em>カラムに値を設定します。 
   */
  public final void setNagoyaClosingPrice( Double p_nagoyaClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    nagoya_closing_price = p_nagoyaClosingPrice;
    nagoya_closing_price_is_set = true;
    nagoya_closing_price_is_modified = true;
  }


  /** 
   * <em>other_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_otherClosingPrice <em>other_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOtherClosingPrice( double p_otherClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_closing_price = new Double(p_otherClosingPrice);
    other_closing_price_is_set = true;
    other_closing_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_closing_price</em>カラムに値を設定します。 
   */
  public final void setOtherClosingPrice( Double p_otherClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_closing_price = p_otherClosingPrice;
    other_closing_price_is_set = true;
    other_closing_price_is_modified = true;
  }


  /** 
   * <em>primary_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_primaryClosingPrice <em>primary_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPrimaryClosingPrice( double p_primaryClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    primary_closing_price = new Double(p_primaryClosingPrice);
    primary_closing_price_is_set = true;
    primary_closing_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>primary_closing_price</em>カラムに値を設定します。 
   */
  public final void setPrimaryClosingPrice( Double p_primaryClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    primary_closing_price = p_primaryClosingPrice;
    primary_closing_price_is_set = true;
    primary_closing_price_is_modified = true;
  }


  /** 
   * <em>error_code</em>カラムの値を設定します。 
   *
   * @@param p_errorCode <em>error_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setErrorCode( String p_errorCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_code = p_errorCode;
    error_code_is_set = true;
    error_code_is_modified = true;
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
            case 'e':
                if ( name.equals("error_code") ) {
                    return this.error_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("nagoya_closing_price") ) {
                    return this.nagoya_closing_price;
                }
                break;
            case 'o':
                if ( name.equals("oosaka_closing_price") ) {
                    return this.oosaka_closing_price;
                }
                else if ( name.equals("other_closing_price") ) {
                    return this.other_closing_price;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("primary_closing_price") ) {
                    return this.primary_closing_price;
                }
                break;
            case 't':
                if ( name.equals("tokyo_closing_price") ) {
                    return this.tokyo_closing_price;
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
            case 'e':
                if ( name.equals("error_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_code' must be String: '"+value+"' is not." );
                    this.error_code = (String) value;
                    if (this.error_code_is_set)
                        this.error_code_is_modified = true;
                    this.error_code_is_set = true;
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
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'nagoya_closing_price' must be Double: '"+value+"' is not." );
                    this.nagoya_closing_price = (Double) value;
                    if (this.nagoya_closing_price_is_set)
                        this.nagoya_closing_price_is_modified = true;
                    this.nagoya_closing_price_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("oosaka_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'oosaka_closing_price' must be Double: '"+value+"' is not." );
                    this.oosaka_closing_price = (Double) value;
                    if (this.oosaka_closing_price_is_set)
                        this.oosaka_closing_price_is_modified = true;
                    this.oosaka_closing_price_is_set = true;
                    return;
                }
                else if ( name.equals("other_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_closing_price' must be Double: '"+value+"' is not." );
                    this.other_closing_price = (Double) value;
                    if (this.other_closing_price_is_set)
                        this.other_closing_price_is_modified = true;
                    this.other_closing_price_is_set = true;
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
                else if ( name.equals("primary_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'primary_closing_price' must be Double: '"+value+"' is not." );
                    this.primary_closing_price = (Double) value;
                    if (this.primary_closing_price_is_set)
                        this.primary_closing_price_is_modified = true;
                    this.primary_closing_price_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tokyo_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'tokyo_closing_price' must be Double: '"+value+"' is not." );
                    this.tokyo_closing_price = (Double) value;
                    if (this.tokyo_closing_price_is_set)
                        this.tokyo_closing_price_is_modified = true;
                    this.tokyo_closing_price_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
