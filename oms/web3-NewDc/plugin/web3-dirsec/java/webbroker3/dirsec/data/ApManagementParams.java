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
filename	ApManagementParams.java;


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
 * ap_managementテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link ApManagementRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link ApManagementParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link ApManagementParams}が{@@link ApManagementRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see ApManagementPK 
 * @@see ApManagementRow 
 */
public class ApManagementParams extends Params implements ApManagementRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ap_management";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = ApManagementRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return ApManagementRow.TYPE;
  }


  /** 
   * <em>ptype</em>カラムの値 
   */
  public  String  ptype;

  /** 
   * <em>request_code</em>カラムの値 
   */
  public  String  request_code;

  /** 
   * <em>ap_name</em>カラムの値 
   */
  public  String  ap_name;

  /** 
   * <em>order_request_number_div</em>カラムの値 
   */
  public  String  order_request_number_div;

  /** 
   * <em>thread_number_div</em>カラムの値 
   */
  public  String  thread_number_div;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean ptype_is_set = false;
  private boolean ptype_is_modified = false;
  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean ap_name_is_set = false;
  private boolean ap_name_is_modified = false;
  private boolean order_request_number_div_is_set = false;
  private boolean order_request_number_div_is_modified = false;
  private boolean thread_number_div_is_set = false;
  private boolean thread_number_div_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "ptype=" + ptype
      + "," + "request_code=" + request_code
      + "," + "ap_name=" +ap_name
      + "," + "order_request_number_div=" +order_request_number_div
      + "," + "thread_number_div=" +thread_number_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のApManagementParamsオブジェクトを作成します。 
   */
  public ApManagementParams() {
    ptype_is_modified = true;
    request_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のApManagementRowオブジェクトの値を利用してApManagementParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するApManagementRowオブジェクト 
   */
  public ApManagementParams( ApManagementRow p_row )
  {
    ptype = p_row.getPtype();
    ptype_is_set = p_row.getPtypeIsSet();
    ptype_is_modified = p_row.getPtypeIsModified();
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    ap_name = p_row.getApName();
    ap_name_is_set = p_row.getApNameIsSet();
    ap_name_is_modified = p_row.getApNameIsModified();
    order_request_number_div = p_row.getOrderRequestNumberDiv();
    order_request_number_div_is_set = p_row.getOrderRequestNumberDivIsSet();
    order_request_number_div_is_modified = p_row.getOrderRequestNumberDivIsModified();
    thread_number_div = p_row.getThreadNumberDiv();
    thread_number_div_is_set = p_row.getThreadNumberDivIsSet();
    thread_number_div_is_modified = p_row.getThreadNumberDivIsModified();
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
      ap_name_is_set = true;
      ap_name_is_modified = true;
      order_request_number_div_is_set = true;
      order_request_number_div_is_modified = true;
      thread_number_div_is_set = true;
      thread_number_div_is_modified = true;
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
    if ( !( other instanceof ApManagementRow ) )
       return false;
    return fieldsEqual( (ApManagementRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のApManagementRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( ApManagementRow row )
  {
    if ( ptype == null ) {
      if ( row.getPtype() != null )
        return false;
    } else if ( !ptype.equals( row.getPtype() ) ) {
        return false;
    }
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
        return false;
    }
    if ( ap_name == null ) {
      if ( row.getApName() != null )
        return false;
    } else if ( !ap_name.equals( row.getApName() ) ) {
        return false;
    }
    if ( order_request_number_div == null ) {
      if ( row.getOrderRequestNumberDiv() != null )
        return false;
    } else if ( !order_request_number_div.equals( row.getOrderRequestNumberDiv() ) ) {
        return false;
    }
    if ( thread_number_div == null ) {
      if ( row.getThreadNumberDiv() != null )
        return false;
    } else if ( !thread_number_div.equals( row.getThreadNumberDiv() ) ) {
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
      return  (ptype!=null? ptype.hashCode(): 0) 
        + (request_code!=null? request_code.hashCode(): 0) 
        + (ap_name!=null? ap_name.hashCode(): 0) 
        + (order_request_number_div!=null? order_request_number_div.hashCode(): 0) 
        + (thread_number_div!=null? thread_number_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !ap_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'ap_name' must be set before inserting.");
		if ( !order_request_number_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number_div' must be set before inserting.");
		if ( !thread_number_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'thread_number_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("ptype",ptype);
		map.put("request_code",request_code);
		map.put("ap_name",ap_name);
		map.put("order_request_number_div",order_request_number_div);
		map.put("thread_number_div",thread_number_div);
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
		if ( ap_name_is_modified )
			map.put("ap_name",ap_name);
		if ( order_request_number_div_is_modified )
			map.put("order_request_number_div",order_request_number_div);
		if ( thread_number_div_is_modified )
			map.put("thread_number_div",thread_number_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( ap_name_is_set )
				map.put("ap_name",ap_name);
			if ( order_request_number_div_is_set )
				map.put("order_request_number_div",order_request_number_div);
			if ( thread_number_div_is_set )
				map.put("thread_number_div",thread_number_div);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>ptype</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPtype()
  {
    return ptype;
  }


  /** 
   * <em>ptype</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPtypeIsSet() {
    return ptype_is_set;
  }


  /** 
   * <em>ptype</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPtypeIsModified() {
    return ptype_is_modified;
  }


  /** 
   * <em>request_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRequestCode()
  {
    return request_code;
  }


  /** 
   * <em>request_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsSet() {
    return request_code_is_set;
  }


  /** 
   * <em>request_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsModified() {
    return request_code_is_modified;
  }


  /** 
   * <em>ap_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getApName()
  {
    return ap_name;
  }


  /** 
   * <em>ap_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApNameIsSet() {
    return ap_name_is_set;
  }


  /** 
   * <em>ap_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApNameIsModified() {
    return ap_name_is_modified;
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
   * <em>thread_number_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getThreadNumberDiv()
  {
    return thread_number_div;
  }


  /** 
   * <em>thread_number_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getThreadNumberDivIsSet() {
    return thread_number_div_is_set;
  }


  /** 
   * <em>thread_number_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getThreadNumberDivIsModified() {
    return thread_number_div_is_modified;
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
    return new ApManagementPK(ptype, request_code);
  }


  /** 
   * <em>ptype</em>カラムの値を設定します。 
   *
   * @@param p_ptype <em>ptype</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setPtype( String p_ptype )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ptype = p_ptype;
    ptype_is_set = true;
    ptype_is_modified = true;
  }


  /** 
   * <em>request_code</em>カラムの値を設定します。 
   *
   * @@param p_requestCode <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setRequestCode( String p_requestCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_code = p_requestCode;
    request_code_is_set = true;
    request_code_is_modified = true;
  }


  /** 
   * <em>ap_name</em>カラムの値を設定します。 
   *
   * @@param p_apName <em>ap_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setApName( String p_apName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ap_name = p_apName;
    ap_name_is_set = true;
    ap_name_is_modified = true;
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
   * <em>thread_number_div</em>カラムの値を設定します。 
   *
   * @@param p_threadNumberDiv <em>thread_number_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setThreadNumberDiv( String p_threadNumberDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    thread_number_div = p_threadNumberDiv;
    thread_number_div_is_set = true;
    thread_number_div_is_modified = true;
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
            case 'a':
                if ( name.equals("ap_name") ) {
                    return this.ap_name;
                }
                break;
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
                if ( name.equals("order_request_number_div") ) {
                    return this.order_request_number_div;
                }
                break;
            case 'p':
                if ( name.equals("ptype") ) {
                    return this.ptype;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                break;
            case 't':
                if ( name.equals("thread_number_div") ) {
                    return this.thread_number_div;
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
            case 'a':
                if ( name.equals("ap_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ap_name' must be String: '"+value+"' is not." );
                    this.ap_name = (String) value;
                    if (this.ap_name_is_set)
                        this.ap_name_is_modified = true;
                    this.ap_name_is_set = true;
                    return;
                }
                break;
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
            case 'p':
                if ( name.equals("ptype") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ptype' must be String: '"+value+"' is not." );
                    this.ptype = (String) value;
                    if (this.ptype_is_set)
                        this.ptype_is_modified = true;
                    this.ptype_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_code' must be String: '"+value+"' is not." );
                    this.request_code = (String) value;
                    if (this.request_code_is_set)
                        this.request_code_is_modified = true;
                    this.request_code_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("thread_number_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'thread_number_div' must be String: '"+value+"' is not." );
                    this.thread_number_div = (String) value;
                    if (this.thread_number_div_is_set)
                        this.thread_number_div_is_modified = true;
                    this.thread_number_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
