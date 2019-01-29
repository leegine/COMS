head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostManagementParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * host_managementテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostManagementRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostManagementParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostManagementParams}が{@@link HostManagementRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostManagementPK 
 * @@see HostManagementRow 
 */
public class HostManagementParams extends Params implements HostManagementRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_management";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostManagementRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostManagementRow.TYPE;
  }


  /** 
   * <em>host_table_name</em>カラムの値 
   */
  public  String  host_table_name;

  /** 
   * <em>host_table_physics_name</em>カラムの値 
   */
  public  String  host_table_physics_name;

  /** 
   * <em>order_request_number_div</em>カラムの値 
   */
  public  String  order_request_number_div;

  /** 
   * <em>created_timestamp_div</em>カラムの値 
   */
  public  String  created_timestamp_div;

  /** 
   * <em>queryprocessor_name</em>カラムの値 
   */
  public  String  queryprocessor_name;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean host_table_name_is_set = false;
  private boolean host_table_name_is_modified = false;
  private boolean host_table_physics_name_is_set = false;
  private boolean host_table_physics_name_is_modified = false;
  private boolean order_request_number_div_is_set = false;
  private boolean order_request_number_div_is_modified = false;
  private boolean created_timestamp_div_is_set = false;
  private boolean created_timestamp_div_is_modified = false;
  private boolean queryprocessor_name_is_set = false;
  private boolean queryprocessor_name_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "host_table_name=" + host_table_name
      + "," + "host_table_physics_name=" +host_table_physics_name
      + "," + "order_request_number_div=" +order_request_number_div
      + "," + "created_timestamp_div=" +created_timestamp_div
      + "," + "queryprocessor_name=" +queryprocessor_name
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のHostManagementParamsオブジェクトを作成します。 
   */
  public HostManagementParams() {
    host_table_name_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostManagementRowオブジェクトの値を利用してHostManagementParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostManagementRowオブジェクト 
   */
  public HostManagementParams( HostManagementRow p_row )
  {
    host_table_name = p_row.getHostTableName();
    host_table_name_is_set = p_row.getHostTableNameIsSet();
    host_table_name_is_modified = p_row.getHostTableNameIsModified();
    host_table_physics_name = p_row.getHostTablePhysicsName();
    host_table_physics_name_is_set = p_row.getHostTablePhysicsNameIsSet();
    host_table_physics_name_is_modified = p_row.getHostTablePhysicsNameIsModified();
    order_request_number_div = p_row.getOrderRequestNumberDiv();
    order_request_number_div_is_set = p_row.getOrderRequestNumberDivIsSet();
    order_request_number_div_is_modified = p_row.getOrderRequestNumberDivIsModified();
    created_timestamp_div = p_row.getCreatedTimestampDiv();
    created_timestamp_div_is_set = p_row.getCreatedTimestampDivIsSet();
    created_timestamp_div_is_modified = p_row.getCreatedTimestampDivIsModified();
    queryprocessor_name = p_row.getQueryprocessorName();
    queryprocessor_name_is_set = p_row.getQueryprocessorNameIsSet();
    queryprocessor_name_is_modified = p_row.getQueryprocessorNameIsModified();
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
      host_table_physics_name_is_set = true;
      host_table_physics_name_is_modified = true;
      order_request_number_div_is_set = true;
      order_request_number_div_is_modified = true;
      created_timestamp_div_is_set = true;
      created_timestamp_div_is_modified = true;
      queryprocessor_name_is_set = true;
      queryprocessor_name_is_modified = true;
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
    if ( !( other instanceof HostManagementRow ) )
       return false;
    return fieldsEqual( (HostManagementRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostManagementRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostManagementRow row )
  {
    if ( host_table_name == null ) {
      if ( row.getHostTableName() != null )
        return false;
    } else if ( !host_table_name.equals( row.getHostTableName() ) ) {
        return false;
    }
    if ( host_table_physics_name == null ) {
      if ( row.getHostTablePhysicsName() != null )
        return false;
    } else if ( !host_table_physics_name.equals( row.getHostTablePhysicsName() ) ) {
        return false;
    }
    if ( order_request_number_div == null ) {
      if ( row.getOrderRequestNumberDiv() != null )
        return false;
    } else if ( !order_request_number_div.equals( row.getOrderRequestNumberDiv() ) ) {
        return false;
    }
    if ( created_timestamp_div == null ) {
      if ( row.getCreatedTimestampDiv() != null )
        return false;
    } else if ( !created_timestamp_div.equals( row.getCreatedTimestampDiv() ) ) {
        return false;
    }
    if ( queryprocessor_name == null ) {
      if ( row.getQueryprocessorName() != null )
        return false;
    } else if ( !queryprocessor_name.equals( row.getQueryprocessorName() ) ) {
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
      return  (host_table_name!=null? host_table_name.hashCode(): 0) 
        + (host_table_physics_name!=null? host_table_physics_name.hashCode(): 0) 
        + (order_request_number_div!=null? order_request_number_div.hashCode(): 0) 
        + (created_timestamp_div!=null? created_timestamp_div.hashCode(): 0) 
        + (queryprocessor_name!=null? queryprocessor_name.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !host_table_physics_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'host_table_physics_name' must be set before inserting.");
		if ( !order_request_number_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number_div' must be set before inserting.");
		if ( !created_timestamp_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp_div' must be set before inserting.");
		if ( !queryprocessor_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'queryprocessor_name' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("host_table_name",host_table_name);
		map.put("host_table_physics_name",host_table_physics_name);
		map.put("order_request_number_div",order_request_number_div);
		map.put("created_timestamp_div",created_timestamp_div);
		map.put("queryprocessor_name",queryprocessor_name);
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
		if ( host_table_physics_name_is_modified )
			map.put("host_table_physics_name",host_table_physics_name);
		if ( order_request_number_div_is_modified )
			map.put("order_request_number_div",order_request_number_div);
		if ( created_timestamp_div_is_modified )
			map.put("created_timestamp_div",created_timestamp_div);
		if ( queryprocessor_name_is_modified )
			map.put("queryprocessor_name",queryprocessor_name);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( host_table_physics_name_is_set )
				map.put("host_table_physics_name",host_table_physics_name);
			if ( order_request_number_div_is_set )
				map.put("order_request_number_div",order_request_number_div);
			if ( created_timestamp_div_is_set )
				map.put("created_timestamp_div",created_timestamp_div);
			if ( queryprocessor_name_is_set )
				map.put("queryprocessor_name",queryprocessor_name);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>host_table_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHostTableName()
  {
    return host_table_name;
  }


  /** 
   * <em>host_table_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostTableNameIsSet() {
    return host_table_name_is_set;
  }


  /** 
   * <em>host_table_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostTableNameIsModified() {
    return host_table_name_is_modified;
  }


  /** 
   * <em>host_table_physics_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHostTablePhysicsName()
  {
    return host_table_physics_name;
  }


  /** 
   * <em>host_table_physics_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostTablePhysicsNameIsSet() {
    return host_table_physics_name_is_set;
  }


  /** 
   * <em>host_table_physics_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHostTablePhysicsNameIsModified() {
    return host_table_physics_name_is_modified;
  }


  /** 
   * <em>order_request_number_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRequestNumberDiv()
  {
    return order_request_number_div;
  }


  /** 
   * <em>order_request_number_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberDivIsSet() {
    return order_request_number_div_is_set;
  }


  /** 
   * <em>order_request_number_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberDivIsModified() {
    return order_request_number_div_is_modified;
  }


  /** 
   * <em>created_timestamp_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCreatedTimestampDiv()
  {
    return created_timestamp_div;
  }


  /** 
   * <em>created_timestamp_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampDivIsSet() {
    return created_timestamp_div_is_set;
  }


  /** 
   * <em>created_timestamp_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampDivIsModified() {
    return created_timestamp_div_is_modified;
  }


  /** 
   * <em>queryprocessor_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getQueryprocessorName()
  {
    return queryprocessor_name;
  }


  /** 
   * <em>queryprocessor_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQueryprocessorNameIsSet() {
    return queryprocessor_name_is_set;
  }


  /** 
   * <em>queryprocessor_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQueryprocessorNameIsModified() {
    return queryprocessor_name_is_modified;
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
    return new HostManagementPK(host_table_name);
  }


  /** 
   * <em>host_table_name</em>カラムの値を設定します。 
   *
   * @@param p_hostTableName <em>host_table_name</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setHostTableName( String p_hostTableName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    host_table_name = p_hostTableName;
    host_table_name_is_set = true;
    host_table_name_is_modified = true;
  }


  /** 
   * <em>host_table_physics_name</em>カラムの値を設定します。 
   *
   * @@param p_hostTablePhysicsName <em>host_table_physics_name</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setHostTablePhysicsName( String p_hostTablePhysicsName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    host_table_physics_name = p_hostTablePhysicsName;
    host_table_physics_name_is_set = true;
    host_table_physics_name_is_modified = true;
  }


  /** 
   * <em>order_request_number_div</em>カラムの値を設定します。 
   *
   * @@param p_orderRequestNumberDiv <em>order_request_number_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderRequestNumberDiv( String p_orderRequestNumberDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number_div = p_orderRequestNumberDiv;
    order_request_number_div_is_set = true;
    order_request_number_div_is_modified = true;
  }


  /** 
   * <em>created_timestamp_div</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestampDiv <em>created_timestamp_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCreatedTimestampDiv( String p_createdTimestampDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp_div = p_createdTimestampDiv;
    created_timestamp_div_is_set = true;
    created_timestamp_div_is_modified = true;
  }


  /** 
   * <em>queryprocessor_name</em>カラムの値を設定します。 
   *
   * @@param p_queryprocessorName <em>queryprocessor_name</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setQueryprocessorName( String p_queryprocessorName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    queryprocessor_name = p_queryprocessorName;
    queryprocessor_name_is_set = true;
    queryprocessor_name_is_modified = true;
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
                if ( name.equals("created_timestamp_div") ) {
                    return this.created_timestamp_div;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'h':
                if ( name.equals("host_table_name") ) {
                    return this.host_table_name;
                }
                else if ( name.equals("host_table_physics_name") ) {
                    return this.host_table_physics_name;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number_div") ) {
                    return this.order_request_number_div;
                }
                break;
            case 'q':
                if ( name.equals("queryprocessor_name") ) {
                    return this.queryprocessor_name;
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
                if ( name.equals("created_timestamp_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'created_timestamp_div' must be String: '"+value+"' is not." );
                    this.created_timestamp_div = (String) value;
                    if (this.created_timestamp_div_is_set)
                        this.created_timestamp_div_is_modified = true;
                    this.created_timestamp_div_is_set = true;
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
            case 'h':
                if ( name.equals("host_table_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'host_table_name' must be String: '"+value+"' is not." );
                    this.host_table_name = (String) value;
                    if (this.host_table_name_is_set)
                        this.host_table_name_is_modified = true;
                    this.host_table_name_is_set = true;
                    return;
                }
                else if ( name.equals("host_table_physics_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'host_table_physics_name' must be String: '"+value+"' is not." );
                    this.host_table_physics_name = (String) value;
                    if (this.host_table_physics_name_is_set)
                        this.host_table_physics_name_is_modified = true;
                    this.host_table_physics_name_is_set = true;
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
                if ( name.equals("order_request_number_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number_div' must be String: '"+value+"' is not." );
                    this.order_request_number_div = (String) value;
                    if (this.order_request_number_div_is_set)
                        this.order_request_number_div_is_modified = true;
                    this.order_request_number_div_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("queryprocessor_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'queryprocessor_name' must be String: '"+value+"' is not." );
                    this.queryprocessor_name = (String) value;
                    if (this.queryprocessor_name_is_set)
                        this.queryprocessor_name_is_modified = true;
                    this.queryprocessor_name_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
