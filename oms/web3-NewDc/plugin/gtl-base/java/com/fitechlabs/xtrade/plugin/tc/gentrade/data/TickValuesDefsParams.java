head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.35.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	TickValuesDefsParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * tick_values_defsテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link TickValuesDefsRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link TickValuesDefsParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link TickValuesDefsParams}が{@@link TickValuesDefsRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TickValuesDefsPK 
 * @@see TickValuesDefsRow 
 */
public class TickValuesDefsParams extends Params implements TickValuesDefsRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "tick_values_defs";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = TickValuesDefsRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return TickValuesDefsRow.TYPE;
  }


  /** 
   * <em>tick_values_defs_id</em>カラムの値 
   */
  public  long  tick_values_defs_id;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  long  market_id;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  Long  product_id;

  /** 
   * <em>cap_price</em>カラムの値 
   */
  public  double  cap_price;

  /** 
   * <em>tick_value</em>カラムの値 
   */
  public  double  tick_value;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean tick_values_defs_id_is_set = false;
  private boolean tick_values_defs_id_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean cap_price_is_set = false;
  private boolean cap_price_is_modified = false;
  private boolean tick_value_is_set = false;
  private boolean tick_value_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "tick_values_defs_id=" + tick_values_defs_id
      + "," + "market_id=" +market_id
      + "," + "product_type=" +product_type
      + "," + "product_id=" +product_id
      + "," + "cap_price=" +cap_price
      + "," + "tick_value=" +tick_value
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のTickValuesDefsParamsオブジェクトを作成します。 
   */
  public TickValuesDefsParams() {
    tick_values_defs_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のTickValuesDefsRowオブジェクトの値を利用してTickValuesDefsParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するTickValuesDefsRowオブジェクト 
   */
  public TickValuesDefsParams( TickValuesDefsRow p_row )
  {
    tick_values_defs_id = p_row.getTickValuesDefsId();
    tick_values_defs_id_is_set = p_row.getTickValuesDefsIdIsSet();
    tick_values_defs_id_is_modified = p_row.getTickValuesDefsIdIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    if ( !p_row.getProductIdIsNull() )
      product_id = new Long( p_row.getProductId() );
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    cap_price = p_row.getCapPrice();
    cap_price_is_set = p_row.getCapPriceIsSet();
    cap_price_is_modified = p_row.getCapPriceIsModified();
    tick_value = p_row.getTickValue();
    tick_value_is_set = p_row.getTickValueIsSet();
    tick_value_is_modified = p_row.getTickValueIsModified();
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
      market_id_is_set = true;
      market_id_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      cap_price_is_set = true;
      cap_price_is_modified = true;
      tick_value_is_set = true;
      tick_value_is_modified = true;
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
    if ( !( other instanceof TickValuesDefsRow ) )
       return false;
    return fieldsEqual( (TickValuesDefsRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のTickValuesDefsRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( TickValuesDefsRow row )
  {
    if ( tick_values_defs_id != row.getTickValuesDefsId() )
      return false;
    if ( market_id != row.getMarketId() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( product_id == null ) {
      if ( !row.getProductIdIsNull() )
        return false;
    } else if ( row.getProductIdIsNull() || ( product_id.longValue() != row.getProductId() ) ) {
        return false;
    }
    if ( cap_price != row.getCapPrice() )
      return false;
    if ( tick_value != row.getTickValue() )
      return false;
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
      return  ((int) tick_values_defs_id)
        + ((int) market_id)
        + (product_type!=null? product_type.hashCode(): 0) 
        + (product_id!=null? product_id.hashCode(): 0) 
        + ((int) cap_price)
        + ((int) tick_value)
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !market_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_id' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !cap_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'cap_price' must be set before inserting.");
		if ( !tick_value_is_set )
			throw new IllegalArgumentException("non-nullable field 'tick_value' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("tick_values_defs_id",new Long(tick_values_defs_id));
		map.put("market_id",new Long(market_id));
		map.put("product_type",product_type);
		if ( product_id != null )
			map.put("product_id",product_id);
		map.put("cap_price",new Double(cap_price));
		map.put("tick_value",new Double(tick_value));
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
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( product_id_is_modified )
			map.put("product_id",product_id);
		if ( cap_price_is_modified )
			map.put("cap_price",new Double(cap_price));
		if ( tick_value_is_modified )
			map.put("tick_value",new Double(tick_value));
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
			if ( product_type_is_set )
				map.put("product_type",product_type);
			map.put("product_id",product_id);
			if ( cap_price_is_set )
				map.put("cap_price",new Double(cap_price));
			if ( tick_value_is_set )
				map.put("tick_value",new Double(tick_value));
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>tick_values_defs_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTickValuesDefsId()
  {
    return tick_values_defs_id;
  }


  /** 
   * <em>tick_values_defs_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTickValuesDefsIdIsSet() {
    return tick_values_defs_id_is_set;
  }


  /** 
   * <em>tick_values_defs_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTickValuesDefsIdIsModified() {
    return tick_values_defs_id_is_modified;
  }


  /** 
   * <em>market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarketId()
  {
    return market_id;
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
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getProductId()
  {
    return ( product_id==null? ((long)0): product_id.longValue() );
  }


  /** 
   * <em>product_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getProductIdIsNull()
  {
    return product_id == null;
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
   * <em>cap_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCapPrice()
  {
    return cap_price;
  }


  /** 
   * <em>cap_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapPriceIsSet() {
    return cap_price_is_set;
  }


  /** 
   * <em>cap_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapPriceIsModified() {
    return cap_price_is_modified;
  }


  /** 
   * <em>tick_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTickValue()
  {
    return tick_value;
  }


  /** 
   * <em>tick_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTickValueIsSet() {
    return tick_value_is_set;
  }


  /** 
   * <em>tick_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTickValueIsModified() {
    return tick_value_is_modified;
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
    return new TickValuesDefsPK(tick_values_defs_id);
  }


  /** 
   * <em>tick_values_defs_id</em>カラムの値を設定します。 
   *
   * @@param p_tickValuesDefsId <em>tick_values_defs_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setTickValuesDefsId( long p_tickValuesDefsId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tick_values_defs_id = p_tickValuesDefsId;
    tick_values_defs_id_is_set = true;
    tick_values_defs_id_is_modified = true;
  }


  /** 
   * <em>market_id</em>カラムの値を設定します。 
   *
   * @@param p_marketId <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
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
   * <em>product_id</em>カラムの値を設定します。 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setProductId( long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = new Long(p_productId);
    product_id_is_set = true;
    product_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>product_id</em>カラムに値を設定します。 
   */
  public final void setProductId( Long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = p_productId;
    product_id_is_set = true;
    product_id_is_modified = true;
  }


  /** 
   * <em>cap_price</em>カラムの値を設定します。 
   *
   * @@param p_capPrice <em>cap_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCapPrice( double p_capPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cap_price = p_capPrice;
    cap_price_is_set = true;
    cap_price_is_modified = true;
  }


  /** 
   * <em>tick_value</em>カラムの値を設定します。 
   *
   * @@param p_tickValue <em>tick_value</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTickValue( double p_tickValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tick_value = p_tickValue;
    tick_value_is_set = true;
    tick_value_is_modified = true;
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
            case 'c':
                if ( name.equals("cap_price") ) {
                    return new Double( cap_price );
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("product_id") ) {
                    return this.product_id;
                }
                break;
            case 't':
                if ( name.equals("tick_values_defs_id") ) {
                    return new Long( tick_values_defs_id );
                }
                else if ( name.equals("tick_value") ) {
                    return new Double( tick_value );
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
            case 'c':
                if ( name.equals("cap_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cap_price' must be Double: '"+value+"' is not." );
                    this.cap_price = ((Double) value).doubleValue();
                    if (this.cap_price_is_set)
                        this.cap_price_is_modified = true;
                    this.cap_price_is_set = true;
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
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = ((Long) value).longValue();
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                else if ( name.equals("product_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = (Long) value;
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tick_values_defs_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'tick_values_defs_id' must be Long: '"+value+"' is not." );
                    this.tick_values_defs_id = ((Long) value).longValue();
                    if (this.tick_values_defs_id_is_set)
                        this.tick_values_defs_id_is_modified = true;
                    this.tick_values_defs_id_is_set = true;
                    return;
                }
                else if ( name.equals("tick_value") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'tick_value' must be Double: '"+value+"' is not." );
                    this.tick_value = ((Double) value).doubleValue();
                    if (this.tick_value_is_set)
                        this.tick_value_is_modified = true;
                    this.tick_value_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
