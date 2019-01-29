head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.29.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OtherOrgOutOfSrvDateParams.java;


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
 * other_org_out_of_srv_dateテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link OtherOrgOutOfSrvDateRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link OtherOrgOutOfSrvDateParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link OtherOrgOutOfSrvDateParams}が{@@link OtherOrgOutOfSrvDateRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OtherOrgOutOfSrvDatePK 
 * @@see OtherOrgOutOfSrvDateRow 
 */
public class OtherOrgOutOfSrvDateParams extends Params implements OtherOrgOutOfSrvDateRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "other_org_out_of_srv_date";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = OtherOrgOutOfSrvDateRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return OtherOrgOutOfSrvDateRow.TYPE;
  }


  /** 
   * <em>other_org_code</em>カラムの値 
   */
  public  String  other_org_code;

  /** 
   * <em>suspension_date</em>カラムの値 
   */
  public  String  suspension_date;

  /** 
   * <em>suspension_start_time</em>カラムの値 
   */
  public  String  suspension_start_time;

  /** 
   * <em>suspension_end_time</em>カラムの値 
   */
  public  String  suspension_end_time;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean other_org_code_is_set = false;
  private boolean other_org_code_is_modified = false;
  private boolean suspension_date_is_set = false;
  private boolean suspension_date_is_modified = false;
  private boolean suspension_start_time_is_set = false;
  private boolean suspension_start_time_is_modified = false;
  private boolean suspension_end_time_is_set = false;
  private boolean suspension_end_time_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "other_org_code=" + other_org_code
      + "," + "suspension_date=" + suspension_date
      + "," + "suspension_start_time=" + suspension_start_time
      + "," + "suspension_end_time=" + suspension_end_time
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のOtherOrgOutOfSrvDateParamsオブジェクトを作成します。 
   */
  public OtherOrgOutOfSrvDateParams() {
    other_org_code_is_modified = true;
    suspension_date_is_modified = true;
    suspension_start_time_is_modified = true;
    suspension_end_time_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のOtherOrgOutOfSrvDateRowオブジェクトの値を利用してOtherOrgOutOfSrvDateParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するOtherOrgOutOfSrvDateRowオブジェクト 
   */
  public OtherOrgOutOfSrvDateParams( OtherOrgOutOfSrvDateRow p_row )
  {
    other_org_code = p_row.getOtherOrgCode();
    other_org_code_is_set = p_row.getOtherOrgCodeIsSet();
    other_org_code_is_modified = p_row.getOtherOrgCodeIsModified();
    suspension_date = p_row.getSuspensionDate();
    suspension_date_is_set = p_row.getSuspensionDateIsSet();
    suspension_date_is_modified = p_row.getSuspensionDateIsModified();
    suspension_start_time = p_row.getSuspensionStartTime();
    suspension_start_time_is_set = p_row.getSuspensionStartTimeIsSet();
    suspension_start_time_is_modified = p_row.getSuspensionStartTimeIsModified();
    suspension_end_time = p_row.getSuspensionEndTime();
    suspension_end_time_is_set = p_row.getSuspensionEndTimeIsSet();
    suspension_end_time_is_modified = p_row.getSuspensionEndTimeIsModified();
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
    if ( !( other instanceof OtherOrgOutOfSrvDateRow ) )
       return false;
    return fieldsEqual( (OtherOrgOutOfSrvDateRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のOtherOrgOutOfSrvDateRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( OtherOrgOutOfSrvDateRow row )
  {
    if ( other_org_code == null ) {
      if ( row.getOtherOrgCode() != null )
        return false;
    } else if ( !other_org_code.equals( row.getOtherOrgCode() ) ) {
        return false;
    }
    if ( suspension_date == null ) {
      if ( row.getSuspensionDate() != null )
        return false;
    } else if ( !suspension_date.equals( row.getSuspensionDate() ) ) {
        return false;
    }
    if ( suspension_start_time == null ) {
      if ( row.getSuspensionStartTime() != null )
        return false;
    } else if ( !suspension_start_time.equals( row.getSuspensionStartTime() ) ) {
        return false;
    }
    if ( suspension_end_time == null ) {
      if ( row.getSuspensionEndTime() != null )
        return false;
    } else if ( !suspension_end_time.equals( row.getSuspensionEndTime() ) ) {
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
      return  (other_org_code!=null? other_org_code.hashCode(): 0) 
        + (suspension_date!=null? suspension_date.hashCode(): 0) 
        + (suspension_start_time!=null? suspension_start_time.hashCode(): 0) 
        + (suspension_end_time!=null? suspension_end_time.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
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
		map.put("other_org_code",other_org_code);
		map.put("suspension_date",suspension_date);
		map.put("suspension_start_time",suspension_start_time);
		map.put("suspension_end_time",suspension_end_time);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>other_org_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOtherOrgCode()
  {
    return other_org_code;
  }


  /** 
   * <em>other_org_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherOrgCodeIsSet() {
    return other_org_code_is_set;
  }


  /** 
   * <em>other_org_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherOrgCodeIsModified() {
    return other_org_code_is_modified;
  }


  /** 
   * <em>suspension_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSuspensionDate()
  {
    return suspension_date;
  }


  /** 
   * <em>suspension_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSuspensionDateIsSet() {
    return suspension_date_is_set;
  }


  /** 
   * <em>suspension_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSuspensionDateIsModified() {
    return suspension_date_is_modified;
  }


  /** 
   * <em>suspension_start_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSuspensionStartTime()
  {
    return suspension_start_time;
  }


  /** 
   * <em>suspension_start_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSuspensionStartTimeIsSet() {
    return suspension_start_time_is_set;
  }


  /** 
   * <em>suspension_start_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSuspensionStartTimeIsModified() {
    return suspension_start_time_is_modified;
  }


  /** 
   * <em>suspension_end_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSuspensionEndTime()
  {
    return suspension_end_time;
  }


  /** 
   * <em>suspension_end_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSuspensionEndTimeIsSet() {
    return suspension_end_time_is_set;
  }


  /** 
   * <em>suspension_end_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSuspensionEndTimeIsModified() {
    return suspension_end_time_is_modified;
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
    return new OtherOrgOutOfSrvDatePK(other_org_code, suspension_date, suspension_start_time, suspension_end_time);
  }


  /** 
   * <em>other_org_code</em>カラムの値を設定します。 
   *
   * @@param p_otherOrgCode <em>other_org_code</em>カラムの値をあらわす11桁以下のString値 
   */
  public final void setOtherOrgCode( String p_otherOrgCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_org_code = p_otherOrgCode;
    other_org_code_is_set = true;
    other_org_code_is_modified = true;
  }


  /** 
   * <em>suspension_date</em>カラムの値を設定します。 
   *
   * @@param p_suspensionDate <em>suspension_date</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setSuspensionDate( String p_suspensionDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    suspension_date = p_suspensionDate;
    suspension_date_is_set = true;
    suspension_date_is_modified = true;
  }


  /** 
   * <em>suspension_start_time</em>カラムの値を設定します。 
   *
   * @@param p_suspensionStartTime <em>suspension_start_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setSuspensionStartTime( String p_suspensionStartTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    suspension_start_time = p_suspensionStartTime;
    suspension_start_time_is_set = true;
    suspension_start_time_is_modified = true;
  }


  /** 
   * <em>suspension_end_time</em>カラムの値を設定します。 
   *
   * @@param p_suspensionEndTime <em>suspension_end_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setSuspensionEndTime( String p_suspensionEndTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    suspension_end_time = p_suspensionEndTime;
    suspension_end_time_is_set = true;
    suspension_end_time_is_modified = true;
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
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("other_org_code") ) {
                    return this.other_org_code;
                }
                break;
            case 's':
                if ( name.equals("suspension_date") ) {
                    return this.suspension_date;
                }
                else if ( name.equals("suspension_start_time") ) {
                    return this.suspension_start_time;
                }
                else if ( name.equals("suspension_end_time") ) {
                    return this.suspension_end_time;
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
            case 'o':
                if ( name.equals("other_org_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'other_org_code' must be String: '"+value+"' is not." );
                    this.other_org_code = (String) value;
                    if (this.other_org_code_is_set)
                        this.other_org_code_is_modified = true;
                    this.other_org_code_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("suspension_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'suspension_date' must be String: '"+value+"' is not." );
                    this.suspension_date = (String) value;
                    if (this.suspension_date_is_set)
                        this.suspension_date_is_modified = true;
                    this.suspension_date_is_set = true;
                    return;
                }
                else if ( name.equals("suspension_start_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'suspension_start_time' must be String: '"+value+"' is not." );
                    this.suspension_start_time = (String) value;
                    if (this.suspension_start_time_is_set)
                        this.suspension_start_time_is_modified = true;
                    this.suspension_start_time_is_set = true;
                    return;
                }
                else if ( name.equals("suspension_end_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'suspension_end_time' must be String: '"+value+"' is not." );
                    this.suspension_end_time = (String) value;
                    if (this.suspension_end_time_is_set)
                        this.suspension_end_time_is_modified = true;
                    this.suspension_end_time_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
