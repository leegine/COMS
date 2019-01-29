head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.39.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OtherOrgSrvTimeParams.java;


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
 * other_org_srv_timeテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link OtherOrgSrvTimeRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link OtherOrgSrvTimeParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link OtherOrgSrvTimeParams}が{@@link OtherOrgSrvTimeRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OtherOrgSrvTimePK 
 * @@see OtherOrgSrvTimeRow 
 */
public class OtherOrgSrvTimeParams extends Params implements OtherOrgSrvTimeRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "other_org_srv_time";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = OtherOrgSrvTimeRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return OtherOrgSrvTimeRow.TYPE;
  }


  /** 
   * <em>other_org_code</em>カラムの値 
   */
  public  String  other_org_code;

  /** 
   * <em>week_div</em>カラムの値 
   */
  public  String  week_div;

  /** 
   * <em>service_start_time</em>カラムの値 
   */
  public  String  service_start_time;

  /** 
   * <em>service_end_time</em>カラムの値 
   */
  public  String  service_end_time;

  /** 
   * <em>service_div</em>カラムの値 
   */
  public  String  service_div;

  /** 
   * <em>service_date_div</em>カラムの値 
   */
  public  String  service_date_div;

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
  private boolean week_div_is_set = false;
  private boolean week_div_is_modified = false;
  private boolean service_start_time_is_set = false;
  private boolean service_start_time_is_modified = false;
  private boolean service_end_time_is_set = false;
  private boolean service_end_time_is_modified = false;
  private boolean service_div_is_set = false;
  private boolean service_div_is_modified = false;
  private boolean service_date_div_is_set = false;
  private boolean service_date_div_is_modified = false;
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
      + "," + "week_div=" + week_div
      + "," + "service_start_time=" + service_start_time
      + "," + "service_end_time=" + service_end_time
      + "," + "service_div=" +service_div
      + "," + "service_date_div=" +service_date_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のOtherOrgSrvTimeParamsオブジェクトを作成します。 
   */
  public OtherOrgSrvTimeParams() {
    other_org_code_is_modified = true;
    week_div_is_modified = true;
    service_start_time_is_modified = true;
    service_end_time_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のOtherOrgSrvTimeRowオブジェクトの値を利用してOtherOrgSrvTimeParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するOtherOrgSrvTimeRowオブジェクト 
   */
  public OtherOrgSrvTimeParams( OtherOrgSrvTimeRow p_row )
  {
    other_org_code = p_row.getOtherOrgCode();
    other_org_code_is_set = p_row.getOtherOrgCodeIsSet();
    other_org_code_is_modified = p_row.getOtherOrgCodeIsModified();
    week_div = p_row.getWeekDiv();
    week_div_is_set = p_row.getWeekDivIsSet();
    week_div_is_modified = p_row.getWeekDivIsModified();
    service_start_time = p_row.getServiceStartTime();
    service_start_time_is_set = p_row.getServiceStartTimeIsSet();
    service_start_time_is_modified = p_row.getServiceStartTimeIsModified();
    service_end_time = p_row.getServiceEndTime();
    service_end_time_is_set = p_row.getServiceEndTimeIsSet();
    service_end_time_is_modified = p_row.getServiceEndTimeIsModified();
    service_div = p_row.getServiceDiv();
    service_div_is_set = p_row.getServiceDivIsSet();
    service_div_is_modified = p_row.getServiceDivIsModified();
    service_date_div = p_row.getServiceDateDiv();
    service_date_div_is_set = p_row.getServiceDateDivIsSet();
    service_date_div_is_modified = p_row.getServiceDateDivIsModified();
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
      service_div_is_set = true;
      service_div_is_modified = true;
      service_date_div_is_set = true;
      service_date_div_is_modified = true;
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
    if ( !( other instanceof OtherOrgSrvTimeRow ) )
       return false;
    return fieldsEqual( (OtherOrgSrvTimeRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のOtherOrgSrvTimeRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( OtherOrgSrvTimeRow row )
  {
    if ( other_org_code == null ) {
      if ( row.getOtherOrgCode() != null )
        return false;
    } else if ( !other_org_code.equals( row.getOtherOrgCode() ) ) {
        return false;
    }
    if ( week_div == null ) {
      if ( row.getWeekDiv() != null )
        return false;
    } else if ( !week_div.equals( row.getWeekDiv() ) ) {
        return false;
    }
    if ( service_start_time == null ) {
      if ( row.getServiceStartTime() != null )
        return false;
    } else if ( !service_start_time.equals( row.getServiceStartTime() ) ) {
        return false;
    }
    if ( service_end_time == null ) {
      if ( row.getServiceEndTime() != null )
        return false;
    } else if ( !service_end_time.equals( row.getServiceEndTime() ) ) {
        return false;
    }
    if ( service_div == null ) {
      if ( row.getServiceDiv() != null )
        return false;
    } else if ( !service_div.equals( row.getServiceDiv() ) ) {
        return false;
    }
    if ( service_date_div == null ) {
      if ( row.getServiceDateDiv() != null )
        return false;
    } else if ( !service_date_div.equals( row.getServiceDateDiv() ) ) {
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
        + (week_div!=null? week_div.hashCode(): 0) 
        + (service_start_time!=null? service_start_time.hashCode(): 0) 
        + (service_end_time!=null? service_end_time.hashCode(): 0) 
        + (service_div!=null? service_div.hashCode(): 0) 
        + (service_date_div!=null? service_date_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !service_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'service_div' must be set before inserting.");
		if ( !service_date_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'service_date_div' must be set before inserting.");
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
		map.put("week_div",week_div);
		map.put("service_start_time",service_start_time);
		map.put("service_end_time",service_end_time);
		map.put("service_div",service_div);
		map.put("service_date_div",service_date_div);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( service_div_is_modified )
			map.put("service_div",service_div);
		if ( service_date_div_is_modified )
			map.put("service_date_div",service_date_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( service_div_is_set )
				map.put("service_div",service_div);
			if ( service_date_div_is_set )
				map.put("service_date_div",service_date_div);
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
   * <em>week_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getWeekDiv()
  {
    return week_div;
  }


  /** 
   * <em>week_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWeekDivIsSet() {
    return week_div_is_set;
  }


  /** 
   * <em>week_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWeekDivIsModified() {
    return week_div_is_modified;
  }


  /** 
   * <em>service_start_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getServiceStartTime()
  {
    return service_start_time;
  }


  /** 
   * <em>service_start_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServiceStartTimeIsSet() {
    return service_start_time_is_set;
  }


  /** 
   * <em>service_start_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServiceStartTimeIsModified() {
    return service_start_time_is_modified;
  }


  /** 
   * <em>service_end_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getServiceEndTime()
  {
    return service_end_time;
  }


  /** 
   * <em>service_end_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServiceEndTimeIsSet() {
    return service_end_time_is_set;
  }


  /** 
   * <em>service_end_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServiceEndTimeIsModified() {
    return service_end_time_is_modified;
  }


  /** 
   * <em>service_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getServiceDiv()
  {
    return service_div;
  }


  /** 
   * <em>service_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServiceDivIsSet() {
    return service_div_is_set;
  }


  /** 
   * <em>service_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServiceDivIsModified() {
    return service_div_is_modified;
  }


  /** 
   * <em>service_date_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getServiceDateDiv()
  {
    return service_date_div;
  }


  /** 
   * <em>service_date_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServiceDateDivIsSet() {
    return service_date_div_is_set;
  }


  /** 
   * <em>service_date_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServiceDateDivIsModified() {
    return service_date_div_is_modified;
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
    return new OtherOrgSrvTimePK(other_org_code, week_div, service_start_time, service_end_time);
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
   * <em>week_div</em>カラムの値を設定します。 
   *
   * @@param p_weekDiv <em>week_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setWeekDiv( String p_weekDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    week_div = p_weekDiv;
    week_div_is_set = true;
    week_div_is_modified = true;
  }


  /** 
   * <em>service_start_time</em>カラムの値を設定します。 
   *
   * @@param p_serviceStartTime <em>service_start_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setServiceStartTime( String p_serviceStartTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    service_start_time = p_serviceStartTime;
    service_start_time_is_set = true;
    service_start_time_is_modified = true;
  }


  /** 
   * <em>service_end_time</em>カラムの値を設定します。 
   *
   * @@param p_serviceEndTime <em>service_end_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setServiceEndTime( String p_serviceEndTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    service_end_time = p_serviceEndTime;
    service_end_time_is_set = true;
    service_end_time_is_modified = true;
  }


  /** 
   * <em>service_div</em>カラムの値を設定します。 
   *
   * @@param p_serviceDiv <em>service_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setServiceDiv( String p_serviceDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    service_div = p_serviceDiv;
    service_div_is_set = true;
    service_div_is_modified = true;
  }


  /** 
   * <em>service_date_div</em>カラムの値を設定します。 
   *
   * @@param p_serviceDateDiv <em>service_date_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setServiceDateDiv( String p_serviceDateDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    service_date_div = p_serviceDateDiv;
    service_date_div_is_set = true;
    service_date_div_is_modified = true;
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
                if ( name.equals("service_start_time") ) {
                    return this.service_start_time;
                }
                else if ( name.equals("service_end_time") ) {
                    return this.service_end_time;
                }
                else if ( name.equals("service_div") ) {
                    return this.service_div;
                }
                else if ( name.equals("service_date_div") ) {
                    return this.service_date_div;
                }
                break;
            case 'w':
                if ( name.equals("week_div") ) {
                    return this.week_div;
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
                if ( name.equals("service_start_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'service_start_time' must be String: '"+value+"' is not." );
                    this.service_start_time = (String) value;
                    if (this.service_start_time_is_set)
                        this.service_start_time_is_modified = true;
                    this.service_start_time_is_set = true;
                    return;
                }
                else if ( name.equals("service_end_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'service_end_time' must be String: '"+value+"' is not." );
                    this.service_end_time = (String) value;
                    if (this.service_end_time_is_set)
                        this.service_end_time_is_modified = true;
                    this.service_end_time_is_set = true;
                    return;
                }
                else if ( name.equals("service_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'service_div' must be String: '"+value+"' is not." );
                    this.service_div = (String) value;
                    if (this.service_div_is_set)
                        this.service_div_is_modified = true;
                    this.service_div_is_set = true;
                    return;
                }
                else if ( name.equals("service_date_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'service_date_div' must be String: '"+value+"' is not." );
                    this.service_date_div = (String) value;
                    if (this.service_date_div_is_set)
                        this.service_date_div_is_modified = true;
                    this.service_date_div_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("week_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'week_div' must be String: '"+value+"' is not." );
                    this.week_div = (String) value;
                    if (this.week_div_is_set)
                        this.week_div_is_modified = true;
                    this.week_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
