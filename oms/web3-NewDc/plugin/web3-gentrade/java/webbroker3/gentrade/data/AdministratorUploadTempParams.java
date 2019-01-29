head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.25.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorUploadTempParams.java;


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
 * administrator_upload_tempテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AdministratorUploadTempRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AdministratorUploadTempParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AdministratorUploadTempParams}が{@@link AdministratorUploadTempRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AdministratorUploadTempPK 
 * @@see AdministratorUploadTempRow 
 */
public class AdministratorUploadTempParams extends Params implements AdministratorUploadTempRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "administrator_upload_temp";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AdministratorUploadTempRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AdministratorUploadTempRow.TYPE;
  }


  /** 
   * <em>administrator_upload_id</em>カラムの値 
   */
  public  long  administrator_upload_id;

  /** 
   * <em>line_number</em>カラムの値 
   */
  public  int  line_number;

  /** 
   * <em>csv_line_value</em>カラムの値 
   */
  public  String  csv_line_value;

  /** 
   * <em>update_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  update_timestamp;

  private boolean administrator_upload_id_is_set = false;
  private boolean administrator_upload_id_is_modified = false;
  private boolean line_number_is_set = false;
  private boolean line_number_is_modified = false;
  private boolean csv_line_value_is_set = false;
  private boolean csv_line_value_is_modified = false;
  private boolean update_timestamp_is_set = false;
  private boolean update_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "administrator_upload_id=" + administrator_upload_id
      + "," + "line_number=" + line_number
      + "," + "csv_line_value=" +csv_line_value
      + "," + "update_timestamp=" +update_timestamp
      + "}";
  }


  /** 
   * 値が未設定のAdministratorUploadTempParamsオブジェクトを作成します。 
   */
  public AdministratorUploadTempParams() {
    administrator_upload_id_is_modified = true;
    line_number_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAdministratorUploadTempRowオブジェクトの値を利用してAdministratorUploadTempParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAdministratorUploadTempRowオブジェクト 
   */
  public AdministratorUploadTempParams( AdministratorUploadTempRow p_row )
  {
    administrator_upload_id = p_row.getAdministratorUploadId();
    administrator_upload_id_is_set = p_row.getAdministratorUploadIdIsSet();
    administrator_upload_id_is_modified = p_row.getAdministratorUploadIdIsModified();
    line_number = p_row.getLineNumber();
    line_number_is_set = p_row.getLineNumberIsSet();
    line_number_is_modified = p_row.getLineNumberIsModified();
    csv_line_value = p_row.getCsvLineValue();
    csv_line_value_is_set = p_row.getCsvLineValueIsSet();
    csv_line_value_is_modified = p_row.getCsvLineValueIsModified();
    update_timestamp = p_row.getUpdateTimestamp();
    update_timestamp_is_set = p_row.getUpdateTimestampIsSet();
    update_timestamp_is_modified = p_row.getUpdateTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      csv_line_value_is_set = true;
      csv_line_value_is_modified = true;
      update_timestamp_is_set = true;
      update_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AdministratorUploadTempRow ) )
       return false;
    return fieldsEqual( (AdministratorUploadTempRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAdministratorUploadTempRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AdministratorUploadTempRow row )
  {
    if ( administrator_upload_id != row.getAdministratorUploadId() )
      return false;
    if ( line_number != row.getLineNumber() )
      return false;
    if ( csv_line_value == null ) {
      if ( row.getCsvLineValue() != null )
        return false;
    } else if ( !csv_line_value.equals( row.getCsvLineValue() ) ) {
        return false;
    }
    if ( update_timestamp == null ) {
      if ( row.getUpdateTimestamp() != null )
        return false;
    } else if ( !update_timestamp.equals( row.getUpdateTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) administrator_upload_id)
        + ((int) line_number)
        + (csv_line_value!=null? csv_line_value.hashCode(): 0) 
        + (update_timestamp!=null? update_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !csv_line_value_is_set )
			throw new IllegalArgumentException("non-nullable field 'csv_line_value' must be set before inserting.");
		if ( !update_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'update_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("administrator_upload_id",new Long(administrator_upload_id));
		map.put("line_number",new Integer(line_number));
		map.put("csv_line_value",csv_line_value);
		map.put("update_timestamp",update_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( csv_line_value_is_modified )
			map.put("csv_line_value",csv_line_value);
		if ( update_timestamp_is_modified )
			map.put("update_timestamp",update_timestamp);
		if (map.size() == 0) {
			if ( csv_line_value_is_set )
				map.put("csv_line_value",csv_line_value);
			if ( update_timestamp_is_set )
				map.put("update_timestamp",update_timestamp);
		}
		return map;
	}


  /** 
   * <em>administrator_upload_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAdministratorUploadId()
  {
    return administrator_upload_id;
  }


  /** 
   * <em>administrator_upload_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdministratorUploadIdIsSet() {
    return administrator_upload_id_is_set;
  }


  /** 
   * <em>administrator_upload_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdministratorUploadIdIsModified() {
    return administrator_upload_id_is_modified;
  }


  /** 
   * <em>line_number</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLineNumber()
  {
    return line_number;
  }


  /** 
   * <em>line_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLineNumberIsSet() {
    return line_number_is_set;
  }


  /** 
   * <em>line_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLineNumberIsModified() {
    return line_number_is_modified;
  }


  /** 
   * <em>csv_line_value</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCsvLineValue()
  {
    return csv_line_value;
  }


  /** 
   * <em>csv_line_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCsvLineValueIsSet() {
    return csv_line_value_is_set;
  }


  /** 
   * <em>csv_line_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCsvLineValueIsModified() {
    return csv_line_value_is_modified;
  }


  /** 
   * <em>update_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getUpdateTimestamp()
  {
    return update_timestamp;
  }


  /** 
   * <em>update_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUpdateTimestampIsSet() {
    return update_timestamp_is_set;
  }


  /** 
   * <em>update_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUpdateTimestampIsModified() {
    return update_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AdministratorUploadTempPK(administrator_upload_id, line_number);
  }


  /** 
   * <em>administrator_upload_id</em>カラムの値を設定します。 
   *
   * @@param p_administratorUploadId <em>administrator_upload_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAdministratorUploadId( long p_administratorUploadId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    administrator_upload_id = p_administratorUploadId;
    administrator_upload_id_is_set = true;
    administrator_upload_id_is_modified = true;
  }


  /** 
   * <em>line_number</em>カラムの値を設定します。 
   *
   * @@param p_lineNumber <em>line_number</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setLineNumber( int p_lineNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    line_number = p_lineNumber;
    line_number_is_set = true;
    line_number_is_modified = true;
  }


  /** 
   * <em>csv_line_value</em>カラムの値を設定します。 
   *
   * @@param p_csvLineValue <em>csv_line_value</em>カラムの値をあらわす1500桁以下のString値 
   */
  public final void setCsvLineValue( String p_csvLineValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    csv_line_value = p_csvLineValue;
    csv_line_value_is_set = true;
    csv_line_value_is_modified = true;
  }


  /** 
   * <em>update_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_updateTimestamp <em>update_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setUpdateTimestamp( java.sql.Timestamp p_updateTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    update_timestamp = p_updateTimestamp;
    update_timestamp_is_set = true;
    update_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>update_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setUpdateTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    update_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    update_timestamp_is_set = true;
    update_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("administrator_upload_id") ) {
                    return new Long( administrator_upload_id );
                }
                break;
            case 'c':
                if ( name.equals("csv_line_value") ) {
                    return this.csv_line_value;
                }
                break;
            case 'l':
                if ( name.equals("line_number") ) {
                    return new Integer( line_number );
                }
                break;
            case 'u':
                if ( name.equals("update_timestamp") ) {
                    return this.update_timestamp;
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
                if ( name.equals("administrator_upload_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'administrator_upload_id' must be Long: '"+value+"' is not." );
                    this.administrator_upload_id = ((Long) value).longValue();
                    if (this.administrator_upload_id_is_set)
                        this.administrator_upload_id_is_modified = true;
                    this.administrator_upload_id_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("csv_line_value") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'csv_line_value' must be String: '"+value+"' is not." );
                    this.csv_line_value = (String) value;
                    if (this.csv_line_value_is_set)
                        this.csv_line_value_is_modified = true;
                    this.csv_line_value_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("line_number") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'line_number' must be Integer: '"+value+"' is not." );
                    this.line_number = ((Integer) value).intValue();
                    if (this.line_number_is_set)
                        this.line_number_is_modified = true;
                    this.line_number_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("update_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'update_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.update_timestamp = (java.sql.Timestamp) value;
                    if (this.update_timestamp_is_set)
                        this.update_timestamp_is_modified = true;
                    this.update_timestamp_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
