head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleConnStatusChangesParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.slebase.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/** 
 * sle_conn_status_changesテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SleConnStatusChangesRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SleConnStatusChangesParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SleConnStatusChangesParams}が{@@link SleConnStatusChangesRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleConnStatusChangesPK 
 * @@see SleConnStatusChangesRow 
 */
public class SleConnStatusChangesParams extends Params implements SleConnStatusChangesRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "sle_conn_status_changes";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SleConnStatusChangesRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SleConnStatusChangesRow.TYPE;
  }


  /** 
   * <em>que_id</em>コラムの値 
   */
  public  long  que_id;

  /** 
   * <em>market_code</em>コラムの値 
   */
  public  String  market_code;

  /** 
   * <em>new_status</em>コラムの値 
   */
  public  webbroker3.slebase.enums.SleConnectionStatusEnum  new_status;

  /** 
   * <em>event_acked_div</em>コラムの値 
   */
  public  int  event_acked_div;

  /** 
   * <em>sle_conn_div</em>コラムの値 
   */
  public  int  sle_conn_div;

  /** 
   * <em>proc_status</em>コラムの値 
   */
  public  int  proc_status;

  /** 
   * <em>created_timestamp</em>コラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>コラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean que_id_is_set;
  private boolean market_code_is_set;
  private boolean new_status_is_set;
  private boolean event_acked_div_is_set;
  private boolean sle_conn_div_is_set;
  private boolean proc_status_is_set;
  private boolean created_timestamp_is_set;
  private boolean last_updated_timestamp_is_set;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "que_id=" + que_id
      + "," + "market_code=" +market_code
      + "," + "new_status=" +new_status
      + "," + "event_acked_div=" +event_acked_div
      + "," + "sle_conn_div=" +sle_conn_div
      + "," + "proc_status=" +proc_status
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のSleConnStatusChangesParamsオブジェクトを作成します。 
   */
  public SleConnStatusChangesParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSleConnStatusChangesRowオブジェクトの値を利用してSleConnStatusChangesParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSleConnStatusChangesRowオブジェクト 
   */
  public SleConnStatusChangesParams( SleConnStatusChangesRow p_row )
  {
    que_id = p_row.getQueId();
    que_id_is_set = p_row.getQueIdIsSet();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    new_status = p_row.getNewStatus();
    new_status_is_set = p_row.getNewStatusIsSet();
    event_acked_div = p_row.getEventAckedDiv();
    event_acked_div_is_set = p_row.getEventAckedDivIsSet();
    sle_conn_div = p_row.getSleConnDiv();
    sle_conn_div_is_set = p_row.getSleConnDivIsSet();
    proc_status = p_row.getProcStatus();
    proc_status_is_set = p_row.getProcStatusIsSet();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      market_code_is_set = true;
      new_status_is_set = true;
      event_acked_div_is_set = true;
      sle_conn_div_is_set = true;
      proc_status_is_set = true;
      created_timestamp_is_set = true;
      last_updated_timestamp_is_set = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof SleConnStatusChangesRow ) )
       return false;
    return fieldsEqual( (SleConnStatusChangesRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSleConnStatusChangesRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SleConnStatusChangesRow row )
  {
    if ( que_id != row.getQueId() )
      return false;
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( new_status == null ) {
      if ( row.getNewStatus() != null )
        return false;
    } else if ( !new_status.equals( row.getNewStatus() ) ) {
        return false;
    }
    if ( event_acked_div != row.getEventAckedDiv() )
      return false;
    if ( sle_conn_div != row.getSleConnDiv() )
      return false;
    if ( proc_status != row.getProcStatus() )
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
      return  ((int) que_id)
        + (market_code!=null? market_code.hashCode(): 0) 
        + (new_status!=null? new_status.hashCode(): 0) 
        + ((int) event_acked_div)
        + ((int) sle_conn_div)
        + ((int) proc_status)
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !market_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_code' must be set before inserting.");
		if ( !new_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'new_status' must be set before inserting.");
		if ( !event_acked_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'event_acked_div' must be set before inserting.");
		if ( !sle_conn_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'sle_conn_div' must be set before inserting.");
		if ( !proc_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'proc_status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("que_id",new Long(que_id));
		map.put("market_code",market_code);
		map.put("new_status",new_status);
		map.put("event_acked_div",new Integer(event_acked_div));
		map.put("sle_conn_div",new Integer(sle_conn_div));
		map.put("proc_status",new Integer(proc_status));
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.remove("rowid");
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( market_code_is_set )
			map.put("market_code",market_code);
		if ( new_status_is_set )
			map.put("new_status",new_status);
		if ( event_acked_div_is_set )
			map.put("event_acked_div",new Integer(event_acked_div));
		if ( sle_conn_div_is_set )
			map.put("sle_conn_div",new Integer(sle_conn_div));
		if ( proc_status_is_set )
			map.put("proc_status",new Integer(proc_status));
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * <em>que_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public final long getQueId()
  {
    return que_id;
  }


  /** 
   * <em>que_id</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQueIdIsSet() {
    return que_id_is_set;
  }


  /** 
   * <em>market_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarketCode()
  {
    return market_code;
  }


  /** 
   * <em>market_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsSet() {
    return market_code_is_set;
  }


  /** 
   * <em>new_status</em>コラムの値を取得します。
   * @@return webbroker3.slebase.enums.SleConnectionStatusEnumの値 
   */
  public final webbroker3.slebase.enums.SleConnectionStatusEnum getNewStatus()
  {
    return new_status;
  }


  /** 
   * <em>new_status</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewStatusIsSet() {
    return new_status_is_set;
  }


  /** 
   * <em>event_acked_div</em>コラムの値を取得します。
   * @@return intの値 
   */
  public final int getEventAckedDiv()
  {
    return event_acked_div;
  }


  /** 
   * <em>event_acked_div</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEventAckedDivIsSet() {
    return event_acked_div_is_set;
  }


  /** 
   * <em>sle_conn_div</em>コラムの値を取得します。
   * @@return intの値 
   */
  public final int getSleConnDiv()
  {
    return sle_conn_div;
  }


  /** 
   * <em>sle_conn_div</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSleConnDivIsSet() {
    return sle_conn_div_is_set;
  }


  /** 
   * <em>proc_status</em>コラムの値を取得します。
   * @@return intの値 
   */
  public final int getProcStatus()
  {
    return proc_status;
  }


  /** 
   * <em>proc_status</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProcStatusIsSet() {
    return proc_status_is_set;
  }


  /** 
   * <em>created_timestamp</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new SleConnStatusChangesPK(que_id);
  }


  /** 
   * <em>que_id</em>コラムの値を設定します。 
   *
   * @@param p_queId <em>que_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public final void setQueId( long p_queId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    que_id = p_queId;
    que_id_is_set = true;
  }


  /** 
   * <em>market_code</em>コラムの値を設定します。 
   *
   * @@param p_marketCode <em>market_code</em>コラムの値をあらわす20桁以下のString値 
   */
  public final void setMarketCode( String p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
  }


  /** 
   * <em>new_status</em>コラムの値を設定します。 
   *
   * @@param p_newStatus <em>new_status</em>コラムの値をあらわす6桁以下のwebbroker3.slebase.enums.SleConnectionStatusEnum値 
   */
  public final void setNewStatus( webbroker3.slebase.enums.SleConnectionStatusEnum p_newStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_status = p_newStatus;
    new_status_is_set = true;
  }


  /** 
   * <em>event_acked_div</em>コラムの値を設定します。 
   *
   * @@param p_eventAckedDiv <em>event_acked_div</em>コラムの値をあらわす6桁以下のint値 
   */
  public final void setEventAckedDiv( int p_eventAckedDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    event_acked_div = p_eventAckedDiv;
    event_acked_div_is_set = true;
  }


  /** 
   * <em>sle_conn_div</em>コラムの値を設定します。 
   *
   * @@param p_sleConnDiv <em>sle_conn_div</em>コラムの値をあらわす6桁以下のint値 
   */
  public final void setSleConnDiv( int p_sleConnDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sle_conn_div = p_sleConnDiv;
    sle_conn_div_is_set = true;
  }


  /** 
   * <em>proc_status</em>コラムの値を設定します。 
   *
   * @@param p_procStatus <em>proc_status</em>コラムの値をあらわす6桁以下のint値 
   */
  public final void setProcStatus( int p_procStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    proc_status = p_procStatus;
    proc_status_is_set = true;
  }


  /** 
   * <em>created_timestamp</em>コラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
  }


  /** 
   * <em>last_updated_timestamp</em>コラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
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
            case 'e':
                if ( name.equals("event_acked_div") ) {
                    return new Integer( event_acked_div );
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'n':
                if ( name.equals("new_status") ) {
                    return this.new_status;
                }
                break;
            case 'p':
                if ( name.equals("proc_status") ) {
                    return new Integer( proc_status );
                }
                break;
            case 'q':
                if ( name.equals("que_id") ) {
                    return new Long( que_id );
                }
                break;
            case 's':
                if ( name.equals("sle_conn_div") ) {
                    return new Integer( sle_conn_div );
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
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("event_acked_div") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'event_acked_div' must be Integer: '"+value+"' is not." );
                    this.event_acked_div = ((Integer) value).intValue();
                    this.event_acked_div_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    this.market_code_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("new_status") ) {
                    if ( !(value instanceof webbroker3.slebase.enums.SleConnectionStatusEnum) )
                        throw new IllegalArgumentException( "value for 'new_status' must be webbroker3.slebase.enums.SleConnectionStatusEnum: '"+value+"' is not." );
                    this.new_status = (webbroker3.slebase.enums.SleConnectionStatusEnum) value;
                    this.new_status_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("proc_status") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'proc_status' must be Integer: '"+value+"' is not." );
                    this.proc_status = ((Integer) value).intValue();
                    this.proc_status_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("que_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'que_id' must be Long: '"+value+"' is not." );
                    this.que_id = ((Long) value).longValue();
                    this.que_id_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sle_conn_div") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sle_conn_div' must be Integer: '"+value+"' is not." );
                    this.sle_conn_div = ((Integer) value).intValue();
                    this.sle_conn_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
