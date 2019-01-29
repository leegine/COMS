head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoDeliveryMonthMasterParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * ifo_delivery_month_masterテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link IfoDeliveryMonthMasterRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link IfoDeliveryMonthMasterParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link IfoDeliveryMonthMasterParams}が{@@link IfoDeliveryMonthMasterRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoDeliveryMonthMasterPK 
 * @@see IfoDeliveryMonthMasterRow 
 */
public class IfoDeliveryMonthMasterParams extends Params implements IfoDeliveryMonthMasterRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ifo_delivery_month_master";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = IfoDeliveryMonthMasterRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return IfoDeliveryMonthMasterRow.TYPE;
  }


  /** 
   * <em>underlying_product_code</em>カラムの値 
   */
  public  String  underlying_product_code;

  /** 
   * <em>future_option_div</em>カラムの値 
   */
  public  String  future_option_div;

  /** 
   * <em>delivery_month</em>カラムの値 
   */
  public  String  delivery_month;

  /** 
   * <em>index_id</em>カラムの値 
   */
  public  long  index_id;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_date</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_date;

  private boolean underlying_product_code_is_set = false;
  private boolean underlying_product_code_is_modified = false;
  private boolean future_option_div_is_set = false;
  private boolean future_option_div_is_modified = false;
  private boolean delivery_month_is_set = false;
  private boolean delivery_month_is_modified = false;
  private boolean index_id_is_set = false;
  private boolean index_id_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_date_is_set = false;
  private boolean last_updated_date_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "underlying_product_code=" + underlying_product_code
      + "," + "future_option_div=" + future_option_div
      + "," + "delivery_month=" + delivery_month
      + "," + "index_id=" +index_id
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_date=" +last_updated_date
      + "}";
  }


  /** 
   * 値が未設定のIfoDeliveryMonthMasterParamsオブジェクトを作成します。 
   */
  public IfoDeliveryMonthMasterParams() {
    underlying_product_code_is_modified = true;
    future_option_div_is_modified = true;
    delivery_month_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のIfoDeliveryMonthMasterRowオブジェクトの値を利用してIfoDeliveryMonthMasterParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するIfoDeliveryMonthMasterRowオブジェクト 
   */
  public IfoDeliveryMonthMasterParams( IfoDeliveryMonthMasterRow p_row )
  {
    underlying_product_code = p_row.getUnderlyingProductCode();
    underlying_product_code_is_set = p_row.getUnderlyingProductCodeIsSet();
    underlying_product_code_is_modified = p_row.getUnderlyingProductCodeIsModified();
    future_option_div = p_row.getFutureOptionDiv();
    future_option_div_is_set = p_row.getFutureOptionDivIsSet();
    future_option_div_is_modified = p_row.getFutureOptionDivIsModified();
    delivery_month = p_row.getDeliveryMonth();
    delivery_month_is_set = p_row.getDeliveryMonthIsSet();
    delivery_month_is_modified = p_row.getDeliveryMonthIsModified();
    index_id = p_row.getIndexId();
    index_id_is_set = p_row.getIndexIdIsSet();
    index_id_is_modified = p_row.getIndexIdIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_date = p_row.getLastUpdatedDate();
    last_updated_date_is_set = p_row.getLastUpdatedDateIsSet();
    last_updated_date_is_modified = p_row.getLastUpdatedDateIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      index_id_is_set = true;
      index_id_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_date_is_set = true;
      last_updated_date_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof IfoDeliveryMonthMasterRow ) )
       return false;
    return fieldsEqual( (IfoDeliveryMonthMasterRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のIfoDeliveryMonthMasterRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( IfoDeliveryMonthMasterRow row )
  {
    if ( underlying_product_code == null ) {
      if ( row.getUnderlyingProductCode() != null )
        return false;
    } else if ( !underlying_product_code.equals( row.getUnderlyingProductCode() ) ) {
        return false;
    }
    if ( future_option_div == null ) {
      if ( row.getFutureOptionDiv() != null )
        return false;
    } else if ( !future_option_div.equals( row.getFutureOptionDiv() ) ) {
        return false;
    }
    if ( delivery_month == null ) {
      if ( row.getDeliveryMonth() != null )
        return false;
    } else if ( !delivery_month.equals( row.getDeliveryMonth() ) ) {
        return false;
    }
    if ( index_id != row.getIndexId() )
      return false;
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_date == null ) {
      if ( row.getLastUpdatedDate() != null )
        return false;
    } else if ( !last_updated_date.equals( row.getLastUpdatedDate() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (underlying_product_code!=null? underlying_product_code.hashCode(): 0) 
        + (future_option_div!=null? future_option_div.hashCode(): 0) 
        + (delivery_month!=null? delivery_month.hashCode(): 0) 
        + ((int) index_id)
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_date!=null? last_updated_date.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !index_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'index_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("underlying_product_code",underlying_product_code);
		map.put("future_option_div",future_option_div);
		map.put("delivery_month",delivery_month);
		map.put("index_id",new Long(index_id));
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_date != null )
			map.put("last_updated_date",last_updated_date);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( index_id_is_modified )
			map.put("index_id",new Long(index_id));
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_date_is_modified )
			map.put("last_updated_date",last_updated_date);
		if (map.size() == 0) {
			if ( index_id_is_set )
				map.put("index_id",new Long(index_id));
			map.put("created_timestamp",created_timestamp);
			map.put("last_updated_date",last_updated_date);
		}
		return map;
	}


  /** 
   * <em>underlying_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getUnderlyingProductCode()
  {
    return underlying_product_code;
  }


  /** 
   * <em>underlying_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnderlyingProductCodeIsSet() {
    return underlying_product_code_is_set;
  }


  /** 
   * <em>underlying_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnderlyingProductCodeIsModified() {
    return underlying_product_code_is_modified;
  }


  /** 
   * <em>future_option_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFutureOptionDiv()
  {
    return future_option_div;
  }


  /** 
   * <em>future_option_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureOptionDivIsSet() {
    return future_option_div_is_set;
  }


  /** 
   * <em>future_option_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureOptionDivIsModified() {
    return future_option_div_is_modified;
  }


  /** 
   * <em>delivery_month</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeliveryMonth()
  {
    return delivery_month;
  }


  /** 
   * <em>delivery_month</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryMonthIsSet() {
    return delivery_month_is_set;
  }


  /** 
   * <em>delivery_month</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryMonthIsModified() {
    return delivery_month_is_modified;
  }


  /** 
   * <em>index_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getIndexId()
  {
    return index_id;
  }


  /** 
   * <em>index_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIndexIdIsSet() {
    return index_id_is_set;
  }


  /** 
   * <em>index_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIndexIdIsModified() {
    return index_id_is_modified;
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
   * <em>last_updated_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedDate()
  {
    return last_updated_date;
  }


  /** 
   * <em>last_updated_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedDateIsSet() {
    return last_updated_date_is_set;
  }


  /** 
   * <em>last_updated_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedDateIsModified() {
    return last_updated_date_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new IfoDeliveryMonthMasterPK(underlying_product_code, future_option_div, delivery_month);
  }


  /** 
   * <em>underlying_product_code</em>カラムの値を設定します。 
   *
   * @@param p_underlyingProductCode <em>underlying_product_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setUnderlyingProductCode( String p_underlyingProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    underlying_product_code = p_underlyingProductCode;
    underlying_product_code_is_set = true;
    underlying_product_code_is_modified = true;
  }


  /** 
   * <em>future_option_div</em>カラムの値を設定します。 
   *
   * @@param p_futureOptionDiv <em>future_option_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFutureOptionDiv( String p_futureOptionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    future_option_div = p_futureOptionDiv;
    future_option_div_is_set = true;
    future_option_div_is_modified = true;
  }


  /** 
   * <em>delivery_month</em>カラムの値を設定します。 
   *
   * @@param p_deliveryMonth <em>delivery_month</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setDeliveryMonth( String p_deliveryMonth )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_month = p_deliveryMonth;
    delivery_month_is_set = true;
    delivery_month_is_modified = true;
  }


  /** 
   * <em>index_id</em>カラムの値を設定します。 
   *
   * @@param p_indexId <em>index_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setIndexId( long p_indexId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    index_id = p_indexId;
    index_id_is_set = true;
    index_id_is_modified = true;
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
   * <em>last_updated_date</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedDate <em>last_updated_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedDate( java.sql.Timestamp p_lastUpdatedDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_date = p_lastUpdatedDate;
    last_updated_date_is_set = true;
    last_updated_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_date_is_set = true;
    last_updated_date_is_modified = true;
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
                if ( name.equals("delivery_month") ) {
                    return this.delivery_month;
                }
                break;
            case 'f':
                if ( name.equals("future_option_div") ) {
                    return this.future_option_div;
                }
                break;
            case 'i':
                if ( name.equals("index_id") ) {
                    return new Long( index_id );
                }
                break;
            case 'l':
                if ( name.equals("last_updated_date") ) {
                    return this.last_updated_date;
                }
                break;
            case 'u':
                if ( name.equals("underlying_product_code") ) {
                    return this.underlying_product_code;
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
            case 'd':
                if ( name.equals("delivery_month") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delivery_month' must be String: '"+value+"' is not." );
                    this.delivery_month = (String) value;
                    if (this.delivery_month_is_set)
                        this.delivery_month_is_modified = true;
                    this.delivery_month_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("future_option_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'future_option_div' must be String: '"+value+"' is not." );
                    this.future_option_div = (String) value;
                    if (this.future_option_div_is_set)
                        this.future_option_div_is_modified = true;
                    this.future_option_div_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("index_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'index_id' must be Long: '"+value+"' is not." );
                    this.index_id = ((Long) value).longValue();
                    if (this.index_id_is_set)
                        this.index_id_is_modified = true;
                    this.index_id_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_date = (java.sql.Timestamp) value;
                    if (this.last_updated_date_is_set)
                        this.last_updated_date_is_modified = true;
                    this.last_updated_date_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("underlying_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'underlying_product_code' must be String: '"+value+"' is not." );
                    this.underlying_product_code = (String) value;
                    if (this.underlying_product_code_is_set)
                        this.underlying_product_code_is_modified = true;
                    this.underlying_product_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
