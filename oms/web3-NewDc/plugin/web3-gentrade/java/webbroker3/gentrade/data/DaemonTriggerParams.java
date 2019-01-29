head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.25.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DaemonTriggerParams.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * daemon_triggerテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link DaemonTriggerRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link DaemonTriggerParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link DaemonTriggerParams}が{@@link DaemonTriggerRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DaemonTriggerPK 
 * @@see DaemonTriggerRow 
 */
public class DaemonTriggerParams extends Params implements DaemonTriggerRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "daemon_trigger";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = DaemonTriggerRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return DaemonTriggerRow.TYPE;
  }


  /** 
   * <em>thread_no</em>カラムの値 
   */
  public  long  thread_no;

  /** 
   * <em>trigger_type</em>カラムの値 
   */
  public  String  trigger_type;

  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>range_from</em>カラムの値 
   */
  public  long  range_from;

  /** 
   * <em>range_to</em>カラムの値 
   */
  public  long  range_to;

  /** 
   * <em>trigger_status</em>カラムの値 
   */
  public  String  trigger_status;

  /** 
   * <em>trigger_date</em>カラムの値 
   */
  public  java.sql.Timestamp  trigger_date;

  private boolean trigger_type_is_set = false;
  private boolean trigger_type_is_modified = false;
  private boolean thread_no_is_set = false;
  private boolean thread_no_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean range_from_is_set = false;
  private boolean range_from_is_modified = false;
  private boolean range_to_is_set = false;
  private boolean range_to_is_modified = false;
  private boolean trigger_status_is_set = false;
  private boolean trigger_status_is_modified = false;
  private boolean trigger_date_is_set = false;
  private boolean trigger_date_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "thread_no=" + thread_no
      + "," + "trigger_type=" +trigger_type
      + "," + "order_request_number=" +order_request_number
      + "," + "range_from=" +range_from
      + "," + "range_to=" +range_to
      + "," + "trigger_status=" +trigger_status
      + "," + "trigger_date=" +trigger_date
      + "}";
  }


  /** 
   * 値が未設定のDaemonTriggerParamsオブジェクトを作成します。 
   */
  public DaemonTriggerParams() {
    thread_no_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のDaemonTriggerRowオブジェクトの値を利用してDaemonTriggerParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するDaemonTriggerRowオブジェクト 
   */
  public DaemonTriggerParams( DaemonTriggerRow p_row )
  {
    thread_no = p_row.getThreadNo();
    thread_no_is_set = p_row.getThreadNoIsSet();
    thread_no_is_modified = p_row.getThreadNoIsModified();
    trigger_type = p_row.getTriggerType();
    trigger_type_is_set = p_row.getTriggerTypeIsSet();
    trigger_type_is_modified = p_row.getTriggerTypeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    range_from = p_row.getRangeFrom();
    range_from_is_set = p_row.getRangeFromIsSet();
    range_from_is_modified = p_row.getRangeFromIsModified();
    range_to = p_row.getRangeTo();
    range_to_is_set = p_row.getRangeToIsSet();
    range_to_is_modified = p_row.getRangeToIsModified();
    trigger_status = p_row.getTriggerStatus();
    trigger_status_is_set = p_row.getTriggerStatusIsSet();
    trigger_status_is_modified = p_row.getTriggerStatusIsModified();
    trigger_date = p_row.getTriggerDate();
    trigger_date_is_set = p_row.getTriggerDateIsSet();
    trigger_date_is_modified = p_row.getTriggerDateIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      trigger_type_is_set = true;
      trigger_type_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      range_from_is_set = true;
      range_from_is_modified = true;
      range_to_is_set = true;
      range_to_is_modified = true;
      trigger_status_is_set = true;
      trigger_status_is_modified = true;
      trigger_date_is_set = true;
      trigger_date_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof DaemonTriggerRow ) )
       return false;
    return fieldsEqual( (DaemonTriggerRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のDaemonTriggerRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( DaemonTriggerRow row )
  {
    if ( trigger_type == null ) {
      if ( row.getTriggerType() != null )
        return false;
    } else if ( !trigger_type.equals( row.getTriggerType() ) ) {
        return false;
    }
    if ( thread_no != row.getThreadNo() )
      return false;
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( range_from != row.getRangeFrom() )
      return false;
    if ( range_to != row.getRangeTo() )
      return false;
    if ( trigger_status == null ) {
      if ( row.getTriggerStatus() != null )
        return false;
    } else if ( !trigger_status.equals( row.getTriggerStatus() ) ) {
        return false;
    }
    if ( trigger_date == null ) {
      if ( row.getTriggerDate() != null )
        return false;
    } else if ( !trigger_date.equals( row.getTriggerDate() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (trigger_type!=null? trigger_type.hashCode(): 0) 
        + ((int) thread_no)
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + ((int) range_from)
        + ((int) range_to)
        + (trigger_status!=null? trigger_status.hashCode(): 0) 
        + (trigger_date!=null? trigger_date.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !trigger_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'trigger_type' must be set before inserting.");
		if ( !order_request_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("trigger_type",trigger_type);
		map.put("thread_no",new Long(thread_no));
		map.put("order_request_number",order_request_number);
		if ( range_from_is_set )
			map.put("range_from",new Long(range_from));
		if ( range_to_is_set )
			map.put("range_to",new Long(range_to));
		if ( trigger_status_is_set )
			map.put("trigger_status",trigger_status);
		if ( trigger_date != null )
			map.put("trigger_date",trigger_date);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( trigger_type_is_modified )
			map.put("trigger_type",trigger_type);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( range_from_is_modified )
			map.put("range_from",new Long(range_from));
		if ( range_to_is_modified )
			map.put("range_to",new Long(range_to));
		if ( trigger_status_is_modified )
			map.put("trigger_status",trigger_status);
		if ( trigger_date_is_modified )
			map.put("trigger_date",trigger_date);
		if (map.size() == 0) {
			if ( trigger_type_is_set )
				map.put("trigger_type",trigger_type);
			if ( order_request_number_is_set )
				map.put("order_request_number",order_request_number);
			if ( range_from_is_set )
				map.put("range_from",new Long(range_from));
			if ( range_to_is_set )
				map.put("range_to",new Long(range_to));
			if ( trigger_status_is_set )
				map.put("trigger_status",trigger_status);
			map.put("trigger_date",trigger_date);
		}
		return map;
	}


  /** 
   * <em>trigger_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTriggerType()
  {
    return trigger_type;
  }


  /** 
   * <em>trigger_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTriggerTypeIsSet() {
    return trigger_type_is_set;
  }


  /** 
   * <em>trigger_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTriggerTypeIsModified() {
    return trigger_type_is_modified;
  }


  /** 
   * <em>thread_no</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getThreadNo()
  {
    return thread_no;
  }


  /** 
   * <em>thread_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getThreadNoIsSet() {
    return thread_no_is_set;
  }


  /** 
   * <em>thread_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getThreadNoIsModified() {
    return thread_no_is_modified;
  }


  /** 
   * <em>order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRequestNumber()
  {
    return order_request_number;
  }


  /** 
   * <em>order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsSet() {
    return order_request_number_is_set;
  }


  /** 
   * <em>order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsModified() {
    return order_request_number_is_modified;
  }


  /** 
   * <em>range_from</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getRangeFrom()
  {
    return range_from;
  }


  /** 
   * <em>range_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRangeFromIsSet() {
    return range_from_is_set;
  }


  /** 
   * <em>range_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRangeFromIsModified() {
    return range_from_is_modified;
  }


  /** 
   * <em>range_to</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getRangeTo()
  {
    return range_to;
  }


  /** 
   * <em>range_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRangeToIsSet() {
    return range_to_is_set;
  }


  /** 
   * <em>range_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRangeToIsModified() {
    return range_to_is_modified;
  }


  /** 
   * <em>trigger_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTriggerStatus()
  {
    return trigger_status;
  }


  /** 
   * <em>trigger_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTriggerStatusIsSet() {
    return trigger_status_is_set;
  }


  /** 
   * <em>trigger_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTriggerStatusIsModified() {
    return trigger_status_is_modified;
  }


  /** 
   * <em>trigger_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTriggerDate()
  {
    return trigger_date;
  }


  /** 
   * <em>trigger_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTriggerDateIsSet() {
    return trigger_date_is_set;
  }


  /** 
   * <em>trigger_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTriggerDateIsModified() {
    return trigger_date_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new DaemonTriggerPK(thread_no);
  }


  /** 
   * <em>trigger_type</em>カラムの値を設定します。 
   *
   * @@param p_triggerType <em>trigger_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTriggerType( String p_triggerType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trigger_type = p_triggerType;
    trigger_type_is_set = true;
    trigger_type_is_modified = true;
  }


  /** 
   * <em>thread_no</em>カラムの値を設定します。 
   *
   * @@param p_threadNo <em>thread_no</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setThreadNo( long p_threadNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    thread_no = p_threadNo;
    thread_no_is_set = true;
    thread_no_is_modified = true;
  }


  /** 
   * <em>order_request_number</em>カラムの値を設定します。 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setOrderRequestNumber( String p_orderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number = p_orderRequestNumber;
    order_request_number_is_set = true;
    order_request_number_is_modified = true;
  }


  /** 
   * <em>range_from</em>カラムの値を設定します。 
   *
   * @@param p_rangeFrom <em>range_from</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setRangeFrom( long p_rangeFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    range_from = p_rangeFrom;
    range_from_is_set = true;
    range_from_is_modified = true;
  }


  /** 
   * <em>range_to</em>カラムの値を設定します。 
   *
   * @@param p_rangeTo <em>range_to</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setRangeTo( long p_rangeTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    range_to = p_rangeTo;
    range_to_is_set = true;
    range_to_is_modified = true;
  }


  /** 
   * <em>trigger_status</em>カラムの値を設定します。 
   *
   * @@param p_triggerStatus <em>trigger_status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTriggerStatus( String p_triggerStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trigger_status = p_triggerStatus;
    trigger_status_is_set = true;
    trigger_status_is_modified = true;
  }


  /** 
   * <em>trigger_date</em>カラムの値を設定します。 
   *
   * @@param p_triggerDate <em>trigger_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTriggerDate( java.sql.Timestamp p_triggerDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trigger_date = p_triggerDate;
    trigger_date_is_set = true;
    trigger_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>trigger_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTriggerDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trigger_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    trigger_date_is_set = true;
    trigger_date_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                break;
            case 'r':
                if ( name.equals("range_from") ) {
                    return new Long( range_from );
                }
                else if ( name.equals("range_to") ) {
                    return new Long( range_to );
                }
                break;
            case 't':
                if ( name.equals("trigger_type") ) {
                    return this.trigger_type;
                }
                else if ( name.equals("thread_no") ) {
                    return new Long( thread_no );
                }
                else if ( name.equals("trigger_status") ) {
                    return this.trigger_status;
                }
                else if ( name.equals("trigger_date") ) {
                    return this.trigger_date;
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
            case 'o':
                if ( name.equals("order_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("range_from") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'range_from' must be Long: '"+value+"' is not." );
                    this.range_from = ((Long) value).longValue();
                    if (this.range_from_is_set)
                        this.range_from_is_modified = true;
                    this.range_from_is_set = true;
                    return;
                }
                else if ( name.equals("range_to") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'range_to' must be Long: '"+value+"' is not." );
                    this.range_to = ((Long) value).longValue();
                    if (this.range_to_is_set)
                        this.range_to_is_modified = true;
                    this.range_to_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trigger_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trigger_type' must be String: '"+value+"' is not." );
                    this.trigger_type = (String) value;
                    if (this.trigger_type_is_set)
                        this.trigger_type_is_modified = true;
                    this.trigger_type_is_set = true;
                    return;
                }
                else if ( name.equals("thread_no") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'thread_no' must be Long: '"+value+"' is not." );
                    this.thread_no = ((Long) value).longValue();
                    if (this.thread_no_is_set)
                        this.thread_no_is_modified = true;
                    this.thread_no_is_set = true;
                    return;
                }
                else if ( name.equals("trigger_status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trigger_status' must be String: '"+value+"' is not." );
                    this.trigger_status = (String) value;
                    if (this.trigger_status_is_set)
                        this.trigger_status_is_modified = true;
                    this.trigger_status_is_set = true;
                    return;
                }
                else if ( name.equals("trigger_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'trigger_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.trigger_date = (java.sql.Timestamp) value;
                    if (this.trigger_date_is_set)
                        this.trigger_date_is_modified = true;
                    this.trigger_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
