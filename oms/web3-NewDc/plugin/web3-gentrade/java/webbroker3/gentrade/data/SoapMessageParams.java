head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.16.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SoapMessageParams.java;


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
 * soap_messageテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SoapMessageRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SoapMessageParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SoapMessageParams}が{@@link SoapMessageRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SoapMessagePK 
 * @@see SoapMessageRow 
 */
public class SoapMessageParams extends Params implements SoapMessageRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "soap_message";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SoapMessageRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SoapMessageRow.TYPE;
  }


  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>connect_div</em>カラムの値 
   */
  public  String  connect_div;

  /** 
   * <em>send_receive_div</em>カラムの値 
   */
  public  String  send_receive_div;

  /** 
   * <em>soap_message</em>カラムの値 
   */
  public  String  soap_message;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean connect_div_is_set = false;
  private boolean connect_div_is_modified = false;
  private boolean send_receive_div_is_set = false;
  private boolean send_receive_div_is_modified = false;
  private boolean soap_message_is_set = false;
  private boolean soap_message_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "branch_id=" +branch_id
      + "," + "connect_div=" +connect_div
      + "," + "send_receive_div=" +send_receive_div
      + "," + "soap_message=" +soap_message
      + "," + "created_timestamp=" +created_timestamp
      + "}";
  }


  /** 
   * 値が未設定のSoapMessageParamsオブジェクトを作成します。 
   */
  public SoapMessageParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSoapMessageRowオブジェクトの値を利用してSoapMessageParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSoapMessageRowオブジェクト 
   */
  public SoapMessageParams( SoapMessageRow p_row )
  {
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    connect_div = p_row.getConnectDiv();
    connect_div_is_set = p_row.getConnectDivIsSet();
    connect_div_is_modified = p_row.getConnectDivIsModified();
    send_receive_div = p_row.getSendReceiveDiv();
    send_receive_div_is_set = p_row.getSendReceiveDivIsSet();
    send_receive_div_is_modified = p_row.getSendReceiveDivIsModified();
    soap_message = p_row.getSoapMessage();
    soap_message_is_set = p_row.getSoapMessageIsSet();
    soap_message_is_modified = p_row.getSoapMessageIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      branch_id_is_set = true;
      branch_id_is_modified = true;
      connect_div_is_set = true;
      connect_div_is_modified = true;
      send_receive_div_is_set = true;
      send_receive_div_is_modified = true;
      soap_message_is_set = true;
      soap_message_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof SoapMessageRow ) )
       return false;
    return fieldsEqual( (SoapMessageRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSoapMessageRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SoapMessageRow row )
  {
    if ( branch_id != row.getBranchId() )
      return false;
    if ( connect_div == null ) {
      if ( row.getConnectDiv() != null )
        return false;
    } else if ( !connect_div.equals( row.getConnectDiv() ) ) {
        return false;
    }
    if ( send_receive_div == null ) {
      if ( row.getSendReceiveDiv() != null )
        return false;
    } else if ( !send_receive_div.equals( row.getSendReceiveDiv() ) ) {
        return false;
    }
    if ( soap_message == null ) {
      if ( row.getSoapMessage() != null )
        return false;
    } else if ( !soap_message.equals( row.getSoapMessage() ) ) {
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
      return  ((int) branch_id)
        + (connect_div!=null? connect_div.hashCode(): 0) 
        + (send_receive_div!=null? send_receive_div.hashCode(): 0) 
        + (soap_message!=null? soap_message.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !branch_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_id' must be set before inserting.");
		if ( !connect_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'connect_div' must be set before inserting.");
		if ( !send_receive_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'send_receive_div' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("branch_id",new Long(branch_id));
		map.put("connect_div",connect_div);
		map.put("send_receive_div",send_receive_div);
		if ( soap_message != null )
			map.put("soap_message",soap_message);
		map.put("created_timestamp",created_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( connect_div_is_modified )
			map.put("connect_div",connect_div);
		if ( send_receive_div_is_modified )
			map.put("send_receive_div",send_receive_div);
		if ( soap_message_is_modified )
			map.put("soap_message",soap_message);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if (map.size() == 0) {
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			if ( connect_div_is_set )
				map.put("connect_div",connect_div);
			if ( send_receive_div_is_set )
				map.put("send_receive_div",send_receive_div);
			map.put("soap_message",soap_message);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
		}
		return map;
	}


  /** 
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
  }


  /** 
   * <em>connect_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getConnectDiv()
  {
    return connect_div;
  }


  /** 
   * <em>connect_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConnectDivIsSet() {
    return connect_div_is_set;
  }


  /** 
   * <em>connect_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConnectDivIsModified() {
    return connect_div_is_modified;
  }


  /** 
   * <em>send_receive_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSendReceiveDiv()
  {
    return send_receive_div;
  }


  /** 
   * <em>send_receive_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendReceiveDivIsSet() {
    return send_receive_div_is_set;
  }


  /** 
   * <em>send_receive_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendReceiveDivIsModified() {
    return send_receive_div_is_modified;
  }


  /** 
   * <em>soap_message</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSoapMessage()
  {
    return soap_message;
  }


  /** 
   * <em>soap_message</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSoapMessageIsSet() {
    return soap_message_is_set;
  }


  /** 
   * <em>soap_message</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSoapMessageIsModified() {
    return soap_message_is_modified;
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
    throw new UnsupportedOperationException("Table soap_message has no primary key.");
  }


  /** 
   * <em>branch_id</em>カラムの値を設定します。 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>connect_div</em>カラムの値を設定します。 
   *
   * @@param p_connectDiv <em>connect_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setConnectDiv( String p_connectDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    connect_div = p_connectDiv;
    connect_div_is_set = true;
    connect_div_is_modified = true;
  }


  /** 
   * <em>send_receive_div</em>カラムの値を設定します。 
   *
   * @@param p_sendReceiveDiv <em>send_receive_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSendReceiveDiv( String p_sendReceiveDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_receive_div = p_sendReceiveDiv;
    send_receive_div_is_set = true;
    send_receive_div_is_modified = true;
  }


  /** 
   * <em>soap_message</em>カラムの値を設定します。 
   *
   * @@param p_soapMessage <em>soap_message</em>カラムの値をあらわす4000桁以下のString値 
   */
  public final void setSoapMessage( String p_soapMessage )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    soap_message = p_soapMessage;
    soap_message_is_set = true;
    soap_message_is_modified = true;
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
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                break;
            case 'c':
                if ( name.equals("connect_div") ) {
                    return this.connect_div;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 's':
                if ( name.equals("send_receive_div") ) {
                    return this.send_receive_div;
                }
                else if ( name.equals("soap_message") ) {
                    return this.soap_message;
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
            case 'b':
                if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("connect_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'connect_div' must be String: '"+value+"' is not." );
                    this.connect_div = (String) value;
                    if (this.connect_div_is_set)
                        this.connect_div_is_modified = true;
                    this.connect_div_is_set = true;
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
            case 's':
                if ( name.equals("send_receive_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'send_receive_div' must be String: '"+value+"' is not." );
                    this.send_receive_div = (String) value;
                    if (this.send_receive_div_is_set)
                        this.send_receive_div_is_modified = true;
                    this.send_receive_div_is_set = true;
                    return;
                }
                else if ( name.equals("soap_message") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'soap_message' must be String: '"+value+"' is not." );
                    this.soap_message = (String) value;
                    if (this.soap_message_is_set)
                        this.soap_message_is_modified = true;
                    this.soap_message_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
