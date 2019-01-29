head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.35.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CodeTranslationParams.java;


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
 * code_translationテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link CodeTranslationRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link CodeTranslationParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link CodeTranslationParams}が{@@link CodeTranslationRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CodeTranslationPK 
 * @@see CodeTranslationRow 
 */
public class CodeTranslationParams extends Params implements CodeTranslationRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "code_translation";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = CodeTranslationRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return CodeTranslationRow.TYPE;
  }


  /** 
   * <em>code_type</em>カラムの値 
   */
  public  String  code_type;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>code</em>カラムの値 
   */
  public  String  code;

  /** 
   * <em>display_message</em>カラムの値 
   */
  public  String  display_message;

  /** 
   * <em>create_date</em>カラムの値 
   */
  public  java.sql.Timestamp  create_date;

  /** 
   * <em>last_update_date</em>カラムの値 
   */
  public  java.sql.Timestamp  last_update_date;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  private boolean code_type_is_set = false;
  private boolean code_type_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean code_is_set = false;
  private boolean code_is_modified = false;
  private boolean display_message_is_set = false;
  private boolean display_message_is_modified = false;
  private boolean create_date_is_set = false;
  private boolean create_date_is_modified = false;
  private boolean last_update_date_is_set = false;
  private boolean last_update_date_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "code_type=" + code_type
      + "," + "institution_code=" + institution_code
      + "," + "code=" + code
      + "," + "display_message=" +display_message
      + "," + "create_date=" +create_date
      + "," + "last_update_date=" +last_update_date
      + "," + "last_updater=" +last_updater
      + "}";
  }


  /** 
   * 値が未設定のCodeTranslationParamsオブジェクトを作成します。 
   */
  public CodeTranslationParams() {
    code_type_is_modified = true;
    institution_code_is_modified = true;
    code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のCodeTranslationRowオブジェクトの値を利用してCodeTranslationParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するCodeTranslationRowオブジェクト 
   */
  public CodeTranslationParams( CodeTranslationRow p_row )
  {
    code_type = p_row.getCodeType();
    code_type_is_set = p_row.getCodeTypeIsSet();
    code_type_is_modified = p_row.getCodeTypeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    code = p_row.getCode();
    code_is_set = p_row.getCodeIsSet();
    code_is_modified = p_row.getCodeIsModified();
    display_message = p_row.getDisplayMessage();
    display_message_is_set = p_row.getDisplayMessageIsSet();
    display_message_is_modified = p_row.getDisplayMessageIsModified();
    create_date = p_row.getCreateDate();
    create_date_is_set = p_row.getCreateDateIsSet();
    create_date_is_modified = p_row.getCreateDateIsModified();
    last_update_date = p_row.getLastUpdateDate();
    last_update_date_is_set = p_row.getLastUpdateDateIsSet();
    last_update_date_is_modified = p_row.getLastUpdateDateIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      display_message_is_set = true;
      display_message_is_modified = true;
      create_date_is_set = true;
      create_date_is_modified = true;
      last_update_date_is_set = true;
      last_update_date_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof CodeTranslationRow ) )
       return false;
    return fieldsEqual( (CodeTranslationRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のCodeTranslationRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( CodeTranslationRow row )
  {
    if ( code_type == null ) {
      if ( row.getCodeType() != null )
        return false;
    } else if ( !code_type.equals( row.getCodeType() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( code == null ) {
      if ( row.getCode() != null )
        return false;
    } else if ( !code.equals( row.getCode() ) ) {
        return false;
    }
    if ( display_message == null ) {
      if ( row.getDisplayMessage() != null )
        return false;
    } else if ( !display_message.equals( row.getDisplayMessage() ) ) {
        return false;
    }
    if ( create_date == null ) {
      if ( row.getCreateDate() != null )
        return false;
    } else if ( !create_date.equals( row.getCreateDate() ) ) {
        return false;
    }
    if ( last_update_date == null ) {
      if ( row.getLastUpdateDate() != null )
        return false;
    } else if ( !last_update_date.equals( row.getLastUpdateDate() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (code_type!=null? code_type.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (code!=null? code.hashCode(): 0) 
        + (display_message!=null? display_message.hashCode(): 0) 
        + (create_date!=null? create_date.hashCode(): 0) 
        + (last_update_date!=null? last_update_date.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("code_type",code_type);
		map.put("institution_code",institution_code);
		map.put("code",code);
		if ( display_message != null )
			map.put("display_message",display_message);
		if ( create_date != null )
			map.put("create_date",create_date);
		if ( last_update_date != null )
			map.put("last_update_date",last_update_date);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( display_message_is_modified )
			map.put("display_message",display_message);
		if ( create_date_is_modified )
			map.put("create_date",create_date);
		if ( last_update_date_is_modified )
			map.put("last_update_date",last_update_date);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if (map.size() == 0) {
			map.put("display_message",display_message);
			map.put("create_date",create_date);
			map.put("last_update_date",last_update_date);
			map.put("last_updater",last_updater);
		}
		return map;
	}


  /** 
   * <em>code_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCodeType()
  {
    return code_type;
  }


  /** 
   * <em>code_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCodeTypeIsSet() {
    return code_type_is_set;
  }


  /** 
   * <em>code_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCodeTypeIsModified() {
    return code_type_is_modified;
  }


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCode()
  {
    return code;
  }


  /** 
   * <em>code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCodeIsSet() {
    return code_is_set;
  }


  /** 
   * <em>code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCodeIsModified() {
    return code_is_modified;
  }


  /** 
   * <em>display_message</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDisplayMessage()
  {
    return display_message;
  }


  /** 
   * <em>display_message</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayMessageIsSet() {
    return display_message_is_set;
  }


  /** 
   * <em>display_message</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayMessageIsModified() {
    return display_message_is_modified;
  }


  /** 
   * <em>create_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreateDate()
  {
    return create_date;
  }


  /** 
   * <em>create_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreateDateIsSet() {
    return create_date_is_set;
  }


  /** 
   * <em>create_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreateDateIsModified() {
    return create_date_is_modified;
  }


  /** 
   * <em>last_update_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdateDate()
  {
    return last_update_date;
  }


  /** 
   * <em>last_update_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateDateIsSet() {
    return last_update_date_is_set;
  }


  /** 
   * <em>last_update_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateDateIsModified() {
    return last_update_date_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new CodeTranslationPK(code_type, institution_code, code);
  }


  /** 
   * <em>code_type</em>カラムの値を設定します。 
   *
   * @@param p_codeType <em>code_type</em>カラムの値をあらわす64桁以下のString値 
   */
  public final void setCodeType( String p_codeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    code_type = p_codeType;
    code_type_is_set = true;
    code_type_is_modified = true;
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>code</em>カラムの値を設定します。 
   *
   * @@param p_code <em>code</em>カラムの値をあらわす64桁以下のString値 
   */
  public final void setCode( String p_code )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    code = p_code;
    code_is_set = true;
    code_is_modified = true;
  }


  /** 
   * <em>display_message</em>カラムの値を設定します。 
   *
   * @@param p_displayMessage <em>display_message</em>カラムの値をあらわす128桁以下のString値 
   */
  public final void setDisplayMessage( String p_displayMessage )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    display_message = p_displayMessage;
    display_message_is_set = true;
    display_message_is_modified = true;
  }


  /** 
   * <em>create_date</em>カラムの値を設定します。 
   *
   * @@param p_createDate <em>create_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreateDate( java.sql.Timestamp p_createDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    create_date = p_createDate;
    create_date_is_set = true;
    create_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>create_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreateDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    create_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    create_date_is_set = true;
    create_date_is_modified = true;
  }


  /** 
   * <em>last_update_date</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdateDate <em>last_update_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdateDate( java.sql.Timestamp p_lastUpdateDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_date = p_lastUpdateDate;
    last_update_date_is_set = true;
    last_update_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_update_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdateDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_update_date_is_set = true;
    last_update_date_is_modified = true;
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
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("code_type") ) {
                    return this.code_type;
                }
                else if ( name.equals("code") ) {
                    return this.code;
                }
                else if ( name.equals("create_date") ) {
                    return this.create_date;
                }
                break;
            case 'd':
                if ( name.equals("display_message") ) {
                    return this.display_message;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_update_date") ) {
                    return this.last_update_date;
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
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
                if ( name.equals("code_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'code_type' must be String: '"+value+"' is not." );
                    this.code_type = (String) value;
                    if (this.code_type_is_set)
                        this.code_type_is_modified = true;
                    this.code_type_is_set = true;
                    return;
                }
                else if ( name.equals("code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'code' must be String: '"+value+"' is not." );
                    this.code = (String) value;
                    if (this.code_is_set)
                        this.code_is_modified = true;
                    this.code_is_set = true;
                    return;
                }
                else if ( name.equals("create_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'create_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.create_date = (java.sql.Timestamp) value;
                    if (this.create_date_is_set)
                        this.create_date_is_modified = true;
                    this.create_date_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("display_message") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'display_message' must be String: '"+value+"' is not." );
                    this.display_message = (String) value;
                    if (this.display_message_is_set)
                        this.display_message_is_modified = true;
                    this.display_message_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_update_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_update_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_update_date = (java.sql.Timestamp) value;
                    if (this.last_update_date_is_set)
                        this.last_update_date_is_modified = true;
                    this.last_update_date_is_set = true;
                    return;
                }
                else if ( name.equals("last_updater") ) {
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
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
