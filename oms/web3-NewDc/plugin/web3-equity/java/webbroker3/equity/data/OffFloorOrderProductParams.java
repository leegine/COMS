head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OffFloorOrderProductParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * off_floor_order_productテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link OffFloorOrderProductRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link OffFloorOrderProductParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link OffFloorOrderProductParams}が{@@link OffFloorOrderProductRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OffFloorOrderProductPK 
 * @@see OffFloorOrderProductRow 
 */
public class OffFloorOrderProductParams extends Params implements OffFloorOrderProductRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "off_floor_order_product";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = OffFloorOrderProductRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return OffFloorOrderProductRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>market_code</em>カラムの値 
   */
  public  String  market_code;

  /** 
   * <em>order_end_datetime</em>カラムの値 
   */
  public  java.sql.Timestamp  order_end_datetime;

  /** 
   * <em>order_start_datetime</em>カラムの値 
   */
  public  java.sql.Timestamp  order_start_datetime;

  /** 
   * <em>max_apply_quantity</em>カラムの値 
   */
  public  Double  max_apply_quantity;

  /** 
   * <em>off_floor_order_price</em>カラムの値 
   */
  public  Double  off_floor_order_price;

  /** 
   * <em>last_closing_price</em>カラムの値 
   */
  public  Double  last_closing_price;

  /** 
   * <em>daily_delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  daily_delivery_date;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean order_start_datetime_is_set = false;
  private boolean order_start_datetime_is_modified = false;
  private boolean order_end_datetime_is_set = false;
  private boolean order_end_datetime_is_modified = false;
  private boolean max_apply_quantity_is_set = false;
  private boolean max_apply_quantity_is_modified = false;
  private boolean off_floor_order_price_is_set = false;
  private boolean off_floor_order_price_is_modified = false;
  private boolean last_closing_price_is_set = false;
  private boolean last_closing_price_is_modified = false;
  private boolean daily_delivery_date_is_set = false;
  private boolean daily_delivery_date_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "product_code=" + product_code
      + "," + "market_code=" + market_code
      + "," + "order_end_datetime=" + order_end_datetime
      + "," + "order_start_datetime=" +order_start_datetime
      + "," + "max_apply_quantity=" +max_apply_quantity
      + "," + "off_floor_order_price=" +off_floor_order_price
      + "," + "last_closing_price=" +last_closing_price
      + "," + "daily_delivery_date=" +daily_delivery_date
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のOffFloorOrderProductParamsオブジェクトを作成します。 
   */
  public OffFloorOrderProductParams() {
    institution_code_is_modified = true;
    product_code_is_modified = true;
    market_code_is_modified = true;
    order_end_datetime_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のOffFloorOrderProductRowオブジェクトの値を利用してOffFloorOrderProductParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するOffFloorOrderProductRowオブジェクト 
   */
  public OffFloorOrderProductParams( OffFloorOrderProductRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    order_end_datetime = p_row.getOrderEndDatetime();
    order_end_datetime_is_set = p_row.getOrderEndDatetimeIsSet();
    order_end_datetime_is_modified = p_row.getOrderEndDatetimeIsModified();
    order_start_datetime = p_row.getOrderStartDatetime();
    order_start_datetime_is_set = p_row.getOrderStartDatetimeIsSet();
    order_start_datetime_is_modified = p_row.getOrderStartDatetimeIsModified();
    if ( !p_row.getMaxApplyQuantityIsNull() )
      max_apply_quantity = new Double( p_row.getMaxApplyQuantity() );
    max_apply_quantity_is_set = p_row.getMaxApplyQuantityIsSet();
    max_apply_quantity_is_modified = p_row.getMaxApplyQuantityIsModified();
    if ( !p_row.getOffFloorOrderPriceIsNull() )
      off_floor_order_price = new Double( p_row.getOffFloorOrderPrice() );
    off_floor_order_price_is_set = p_row.getOffFloorOrderPriceIsSet();
    off_floor_order_price_is_modified = p_row.getOffFloorOrderPriceIsModified();
    if ( !p_row.getLastClosingPriceIsNull() )
      last_closing_price = new Double( p_row.getLastClosingPrice() );
    last_closing_price_is_set = p_row.getLastClosingPriceIsSet();
    last_closing_price_is_modified = p_row.getLastClosingPriceIsModified();
    daily_delivery_date = p_row.getDailyDeliveryDate();
    daily_delivery_date_is_set = p_row.getDailyDeliveryDateIsSet();
    daily_delivery_date_is_modified = p_row.getDailyDeliveryDateIsModified();
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
      order_start_datetime_is_set = true;
      order_start_datetime_is_modified = true;
      max_apply_quantity_is_set = true;
      max_apply_quantity_is_modified = true;
      off_floor_order_price_is_set = true;
      off_floor_order_price_is_modified = true;
      last_closing_price_is_set = true;
      last_closing_price_is_modified = true;
      daily_delivery_date_is_set = true;
      daily_delivery_date_is_modified = true;
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
    if ( !( other instanceof OffFloorOrderProductRow ) )
       return false;
    return fieldsEqual( (OffFloorOrderProductRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のOffFloorOrderProductRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( OffFloorOrderProductRow row )
  {
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
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( order_start_datetime == null ) {
      if ( row.getOrderStartDatetime() != null )
        return false;
    } else if ( !order_start_datetime.equals( row.getOrderStartDatetime() ) ) {
        return false;
    }
    if ( order_end_datetime == null ) {
      if ( row.getOrderEndDatetime() != null )
        return false;
    } else if ( !order_end_datetime.equals( row.getOrderEndDatetime() ) ) {
        return false;
    }
    if ( max_apply_quantity == null ) {
      if ( !row.getMaxApplyQuantityIsNull() )
        return false;
    } else if ( row.getMaxApplyQuantityIsNull() || ( max_apply_quantity.doubleValue() != row.getMaxApplyQuantity() ) ) {
        return false;
    }
    if ( off_floor_order_price == null ) {
      if ( !row.getOffFloorOrderPriceIsNull() )
        return false;
    } else if ( row.getOffFloorOrderPriceIsNull() || ( off_floor_order_price.doubleValue() != row.getOffFloorOrderPrice() ) ) {
        return false;
    }
    if ( last_closing_price == null ) {
      if ( !row.getLastClosingPriceIsNull() )
        return false;
    } else if ( row.getLastClosingPriceIsNull() || ( last_closing_price.doubleValue() != row.getLastClosingPrice() ) ) {
        return false;
    }
    if ( daily_delivery_date == null ) {
      if ( row.getDailyDeliveryDate() != null )
        return false;
    } else if ( !daily_delivery_date.equals( row.getDailyDeliveryDate() ) ) {
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (order_start_datetime!=null? order_start_datetime.hashCode(): 0) 
        + (order_end_datetime!=null? order_end_datetime.hashCode(): 0) 
        + (max_apply_quantity!=null? max_apply_quantity.hashCode(): 0) 
        + (off_floor_order_price!=null? off_floor_order_price.hashCode(): 0) 
        + (last_closing_price!=null? last_closing_price.hashCode(): 0) 
        + (daily_delivery_date!=null? daily_delivery_date.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !order_start_datetime_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_start_datetime' must be set before inserting.");
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
		map.put("institution_code",institution_code);
		map.put("product_code",product_code);
		map.put("market_code",market_code);
		map.put("order_start_datetime",order_start_datetime);
		map.put("order_end_datetime",order_end_datetime);
		if ( max_apply_quantity != null )
			map.put("max_apply_quantity",max_apply_quantity);
		if ( off_floor_order_price != null )
			map.put("off_floor_order_price",off_floor_order_price);
		if ( last_closing_price != null )
			map.put("last_closing_price",last_closing_price);
		if ( daily_delivery_date != null )
			map.put("daily_delivery_date",daily_delivery_date);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( order_start_datetime_is_modified )
			map.put("order_start_datetime",order_start_datetime);
		if ( max_apply_quantity_is_modified )
			map.put("max_apply_quantity",max_apply_quantity);
		if ( off_floor_order_price_is_modified )
			map.put("off_floor_order_price",off_floor_order_price);
		if ( last_closing_price_is_modified )
			map.put("last_closing_price",last_closing_price);
		if ( daily_delivery_date_is_modified )
			map.put("daily_delivery_date",daily_delivery_date);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( order_start_datetime_is_set )
				map.put("order_start_datetime",order_start_datetime);
			map.put("max_apply_quantity",max_apply_quantity);
			map.put("off_floor_order_price",off_floor_order_price);
			map.put("last_closing_price",last_closing_price);
			map.put("daily_delivery_date",daily_delivery_date);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarketCode()
  {
    return market_code;
  }


  /** 
   * <em>market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsSet() {
    return market_code_is_set;
  }


  /** 
   * <em>market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsModified() {
    return market_code_is_modified;
  }


  /** 
   * <em>order_start_datetime</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrderStartDatetime()
  {
    return order_start_datetime;
  }


  /** 
   * <em>order_start_datetime</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderStartDatetimeIsSet() {
    return order_start_datetime_is_set;
  }


  /** 
   * <em>order_start_datetime</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderStartDatetimeIsModified() {
    return order_start_datetime_is_modified;
  }


  /** 
   * <em>order_end_datetime</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrderEndDatetime()
  {
    return order_end_datetime;
  }


  /** 
   * <em>order_end_datetime</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderEndDatetimeIsSet() {
    return order_end_datetime_is_set;
  }


  /** 
   * <em>order_end_datetime</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderEndDatetimeIsModified() {
    return order_end_datetime_is_modified;
  }


  /** 
   * <em>max_apply_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMaxApplyQuantity()
  {
    return ( max_apply_quantity==null? ((double)0): max_apply_quantity.doubleValue() );
  }


  /** 
   * <em>max_apply_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxApplyQuantityIsNull()
  {
    return max_apply_quantity == null;
  }


  /** 
   * <em>max_apply_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxApplyQuantityIsSet() {
    return max_apply_quantity_is_set;
  }


  /** 
   * <em>max_apply_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxApplyQuantityIsModified() {
    return max_apply_quantity_is_modified;
  }


  /** 
   * <em>off_floor_order_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOffFloorOrderPrice()
  {
    return ( off_floor_order_price==null? ((double)0): off_floor_order_price.doubleValue() );
  }


  /** 
   * <em>off_floor_order_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOffFloorOrderPriceIsNull()
  {
    return off_floor_order_price == null;
  }


  /** 
   * <em>off_floor_order_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOffFloorOrderPriceIsSet() {
    return off_floor_order_price_is_set;
  }


  /** 
   * <em>off_floor_order_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOffFloorOrderPriceIsModified() {
    return off_floor_order_price_is_modified;
  }


  /** 
   * <em>last_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLastClosingPrice()
  {
    return ( last_closing_price==null? ((double)0): last_closing_price.doubleValue() );
  }


  /** 
   * <em>last_closing_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLastClosingPriceIsNull()
  {
    return last_closing_price == null;
  }


  /** 
   * <em>last_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastClosingPriceIsSet() {
    return last_closing_price_is_set;
  }


  /** 
   * <em>last_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastClosingPriceIsModified() {
    return last_closing_price_is_modified;
  }


  /** 
   * <em>daily_delivery_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDailyDeliveryDate()
  {
    return daily_delivery_date;
  }


  /** 
   * <em>daily_delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDailyDeliveryDateIsSet() {
    return daily_delivery_date_is_set;
  }


  /** 
   * <em>daily_delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDailyDeliveryDateIsModified() {
    return daily_delivery_date_is_modified;
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
    return new OffFloorOrderProductPK(institution_code, product_code, market_code, order_end_datetime);
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
   * <em>market_code</em>カラムの値を設定します。 
   *
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setMarketCode( String p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
    market_code_is_modified = true;
  }


  /** 
   * <em>order_start_datetime</em>カラムの値を設定します。 
   *
   * @@param p_orderStartDatetime <em>order_start_datetime</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrderStartDatetime( java.sql.Timestamp p_orderStartDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_start_datetime = p_orderStartDatetime;
    order_start_datetime_is_set = true;
    order_start_datetime_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>order_start_datetime</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrderStartDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_start_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_start_datetime_is_set = true;
    order_start_datetime_is_modified = true;
  }


  /** 
   * <em>order_end_datetime</em>カラムの値を設定します。 
   *
   * @@param p_orderEndDatetime <em>order_end_datetime</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrderEndDatetime( java.sql.Timestamp p_orderEndDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_end_datetime = p_orderEndDatetime;
    order_end_datetime_is_set = true;
    order_end_datetime_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>order_end_datetime</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrderEndDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_end_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_end_datetime_is_set = true;
    order_end_datetime_is_modified = true;
  }


  /** 
   * <em>max_apply_quantity</em>カラムの値を設定します。 
   *
   * @@param p_maxApplyQuantity <em>max_apply_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMaxApplyQuantity( double p_maxApplyQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_apply_quantity = new Double(p_maxApplyQuantity);
    max_apply_quantity_is_set = true;
    max_apply_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_apply_quantity</em>カラムに値を設定します。 
   */
  public final void setMaxApplyQuantity( Double p_maxApplyQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_apply_quantity = p_maxApplyQuantity;
    max_apply_quantity_is_set = true;
    max_apply_quantity_is_modified = true;
  }


  /** 
   * <em>off_floor_order_price</em>カラムの値を設定します。 
   *
   * @@param p_offFloorOrderPrice <em>off_floor_order_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOffFloorOrderPrice( double p_offFloorOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    off_floor_order_price = new Double(p_offFloorOrderPrice);
    off_floor_order_price_is_set = true;
    off_floor_order_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>off_floor_order_price</em>カラムに値を設定します。 
   */
  public final void setOffFloorOrderPrice( Double p_offFloorOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    off_floor_order_price = p_offFloorOrderPrice;
    off_floor_order_price_is_set = true;
    off_floor_order_price_is_modified = true;
  }


  /** 
   * <em>last_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_lastClosingPrice <em>last_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLastClosingPrice( double p_lastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_closing_price = new Double(p_lastClosingPrice);
    last_closing_price_is_set = true;
    last_closing_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>last_closing_price</em>カラムに値を設定します。 
   */
  public final void setLastClosingPrice( Double p_lastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_closing_price = p_lastClosingPrice;
    last_closing_price_is_set = true;
    last_closing_price_is_modified = true;
  }


  /** 
   * <em>daily_delivery_date</em>カラムの値を設定します。 
   *
   * @@param p_dailyDeliveryDate <em>daily_delivery_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDailyDeliveryDate( java.sql.Timestamp p_dailyDeliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    daily_delivery_date = p_dailyDeliveryDate;
    daily_delivery_date_is_set = true;
    daily_delivery_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>daily_delivery_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDailyDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    daily_delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    daily_delivery_date_is_set = true;
    daily_delivery_date_is_modified = true;
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
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("daily_delivery_date") ) {
                    return this.daily_delivery_date;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_closing_price") ) {
                    return this.last_closing_price;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                else if ( name.equals("max_apply_quantity") ) {
                    return this.max_apply_quantity;
                }
                break;
            case 'o':
                if ( name.equals("order_start_datetime") ) {
                    return this.order_start_datetime;
                }
                else if ( name.equals("order_end_datetime") ) {
                    return this.order_end_datetime;
                }
                else if ( name.equals("off_floor_order_price") ) {
                    return this.off_floor_order_price;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
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
                if ( name.equals("created_timestamp") ) {
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
                if ( name.equals("daily_delivery_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'daily_delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.daily_delivery_date = (java.sql.Timestamp) value;
                    if (this.daily_delivery_date_is_set)
                        this.daily_delivery_date_is_modified = true;
                    this.daily_delivery_date_is_set = true;
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
                if ( name.equals("last_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'last_closing_price' must be Double: '"+value+"' is not." );
                    this.last_closing_price = (Double) value;
                    if (this.last_closing_price_is_set)
                        this.last_closing_price_is_modified = true;
                    this.last_closing_price_is_set = true;
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
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                else if ( name.equals("max_apply_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_apply_quantity' must be Double: '"+value+"' is not." );
                    this.max_apply_quantity = (Double) value;
                    if (this.max_apply_quantity_is_set)
                        this.max_apply_quantity_is_modified = true;
                    this.max_apply_quantity_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_start_datetime") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_start_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_start_datetime = (java.sql.Timestamp) value;
                    if (this.order_start_datetime_is_set)
                        this.order_start_datetime_is_modified = true;
                    this.order_start_datetime_is_set = true;
                    return;
                }
                else if ( name.equals("order_end_datetime") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_end_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_end_datetime = (java.sql.Timestamp) value;
                    if (this.order_end_datetime_is_set)
                        this.order_end_datetime_is_modified = true;
                    this.order_end_datetime_is_set = true;
                    return;
                }
                else if ( name.equals("off_floor_order_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'off_floor_order_price' must be Double: '"+value+"' is not." );
                    this.off_floor_order_price = (Double) value;
                    if (this.off_floor_order_price_is_set)
                        this.off_floor_order_price_is_modified = true;
                    this.off_floor_order_price_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
