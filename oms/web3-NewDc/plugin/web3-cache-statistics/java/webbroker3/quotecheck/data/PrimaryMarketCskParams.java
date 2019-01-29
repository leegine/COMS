head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	PrimaryMarketCskParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quotecheck.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * primary_market_cskテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link PrimaryMarketCskRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link PrimaryMarketCskParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link PrimaryMarketCskParams}が{@@link PrimaryMarketCskRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PrimaryMarketCskPK 
 * @@see PrimaryMarketCskRow 
 */
public class PrimaryMarketCskParams extends Params implements PrimaryMarketCskRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "primary_market_csk";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = PrimaryMarketCskRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return PrimaryMarketCskRow.TYPE;
  }


  /** 
   * <em>fund_code</em>カラムの値 
   */
  public  String  fund_code;

  /** 
   * <em>primary_market_code</em>カラムの値 
   */
  public  String  primary_market_code;

  /** 
   * <em>data_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  data_timestamp;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  private boolean fund_code_is_set = false;
  private boolean fund_code_is_modified = false;
  private boolean primary_market_code_is_set = false;
  private boolean primary_market_code_is_modified = false;
  private boolean data_timestamp_is_set = false;
  private boolean data_timestamp_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "fund_code=" + fund_code
      + "," + "primary_market_code=" +primary_market_code
      + "," + "data_timestamp=" +data_timestamp
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "}";
  }


  /** 
   * 値が未設定のPrimaryMarketCskParamsオブジェクトを作成します。 
   */
  public PrimaryMarketCskParams() {
    fund_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のPrimaryMarketCskRowオブジェクトの値を利用してPrimaryMarketCskParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するPrimaryMarketCskRowオブジェクト 
   */
  public PrimaryMarketCskParams( PrimaryMarketCskRow p_row )
  {
    fund_code = p_row.getFundCode();
    fund_code_is_set = p_row.getFundCodeIsSet();
    fund_code_is_modified = p_row.getFundCodeIsModified();
    primary_market_code = p_row.getPrimaryMarketCode();
    primary_market_code_is_set = p_row.getPrimaryMarketCodeIsSet();
    primary_market_code_is_modified = p_row.getPrimaryMarketCodeIsModified();
    data_timestamp = p_row.getDataTimestamp();
    data_timestamp_is_set = p_row.getDataTimestampIsSet();
    data_timestamp_is_modified = p_row.getDataTimestampIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      primary_market_code_is_set = true;
      primary_market_code_is_modified = true;
      data_timestamp_is_set = true;
      data_timestamp_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof PrimaryMarketCskRow ) )
       return false;
    return fieldsEqual( (PrimaryMarketCskRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のPrimaryMarketCskRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( PrimaryMarketCskRow row )
  {
    if ( fund_code == null ) {
      if ( row.getFundCode() != null )
        return false;
    } else if ( !fund_code.equals( row.getFundCode() ) ) {
        return false;
    }
    if ( primary_market_code == null ) {
      if ( row.getPrimaryMarketCode() != null )
        return false;
    } else if ( !primary_market_code.equals( row.getPrimaryMarketCode() ) ) {
        return false;
    }
    if ( data_timestamp == null ) {
      if ( row.getDataTimestamp() != null )
        return false;
    } else if ( !data_timestamp.equals( row.getDataTimestamp() ) ) {
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
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (fund_code!=null? fund_code.hashCode(): 0) 
        + (primary_market_code!=null? primary_market_code.hashCode(): 0) 
        + (data_timestamp!=null? data_timestamp.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !data_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'data_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("fund_code",fund_code);
		if ( primary_market_code != null )
			map.put("primary_market_code",primary_market_code);
		map.put("data_timestamp",data_timestamp);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( primary_market_code_is_modified )
			map.put("primary_market_code",primary_market_code);
		if ( data_timestamp_is_modified )
			map.put("data_timestamp",data_timestamp);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if (map.size() == 0) {
			map.put("primary_market_code",primary_market_code);
			if ( data_timestamp_is_set )
				map.put("data_timestamp",data_timestamp);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
		}
		return map;
	}


  /** 
   * <em>fund_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFundCode()
  {
    return fund_code;
  }


  /** 
   * <em>fund_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundCodeIsSet() {
    return fund_code_is_set;
  }


  /** 
   * <em>fund_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundCodeIsModified() {
    return fund_code_is_modified;
  }


  /** 
   * <em>primary_market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPrimaryMarketCode()
  {
    return primary_market_code;
  }


  /** 
   * <em>primary_market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrimaryMarketCodeIsSet() {
    return primary_market_code_is_set;
  }


  /** 
   * <em>primary_market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrimaryMarketCodeIsModified() {
    return primary_market_code_is_modified;
  }


  /** 
   * <em>data_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDataTimestamp()
  {
    return data_timestamp;
  }


  /** 
   * <em>data_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataTimestampIsSet() {
    return data_timestamp_is_set;
  }


  /** 
   * <em>data_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataTimestampIsModified() {
    return data_timestamp_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new PrimaryMarketCskPK(fund_code);
  }


  /** 
   * <em>fund_code</em>カラムの値を設定します。 
   *
   * @@param p_fundCode <em>fund_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setFundCode( String p_fundCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_code = p_fundCode;
    fund_code_is_set = true;
    fund_code_is_modified = true;
  }


  /** 
   * <em>primary_market_code</em>カラムの値を設定します。 
   *
   * @@param p_primaryMarketCode <em>primary_market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setPrimaryMarketCode( String p_primaryMarketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    primary_market_code = p_primaryMarketCode;
    primary_market_code_is_set = true;
    primary_market_code_is_modified = true;
  }


  /** 
   * <em>data_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_dataTimestamp <em>data_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDataTimestamp( java.sql.Timestamp p_dataTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_timestamp = p_dataTimestamp;
    data_timestamp_is_set = true;
    data_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>data_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDataTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    data_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    data_timestamp_is_set = true;
    data_timestamp_is_modified = true;
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
                if ( name.equals("data_timestamp") ) {
                    return this.data_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("fund_code") ) {
                    return this.fund_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                break;
            case 'p':
                if ( name.equals("primary_market_code") ) {
                    return this.primary_market_code;
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
                if ( name.equals("data_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'data_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.data_timestamp = (java.sql.Timestamp) value;
                    if (this.data_timestamp_is_set)
                        this.data_timestamp_is_modified = true;
                    this.data_timestamp_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fund_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_code' must be String: '"+value+"' is not." );
                    this.fund_code = (String) value;
                    if (this.fund_code_is_set)
                        this.fund_code_is_modified = true;
                    this.fund_code_is_set = true;
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
                break;
            case 'p':
                if ( name.equals("primary_market_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'primary_market_code' must be String: '"+value+"' is not." );
                    this.primary_market_code = (String) value;
                    if (this.primary_market_code_is_set)
                        this.primary_market_code_is_modified = true;
                    this.primary_market_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
