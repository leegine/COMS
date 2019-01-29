head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.32.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdminPermissionParams.java;


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
 * admin_permissionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AdminPermissionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AdminPermissionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AdminPermissionParams}が{@@link AdminPermissionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AdminPermissionPK 
 * @@see AdminPermissionRow 
 */
public class AdminPermissionParams extends Params implements AdminPermissionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "admin_permission";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AdminPermissionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AdminPermissionRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>permission_level</em>カラムの値 
   */
  public  String  permission_level;

  /** 
   * <em>transaction_category</em>カラムの値 
   */
  public  String  transaction_category;

  /** 
   * <em>update_permission_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  update_permission_flag;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>update_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  update_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean permission_level_is_set = false;
  private boolean permission_level_is_modified = false;
  private boolean transaction_category_is_set = false;
  private boolean transaction_category_is_modified = false;
  private boolean update_permission_flag_is_set = false;
  private boolean update_permission_flag_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean update_timestamp_is_set = false;
  private boolean update_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "permission_level=" + permission_level
      + "," + "transaction_category=" + transaction_category
      + "," + "update_permission_flag=" +update_permission_flag
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "update_timestamp=" +update_timestamp
      + "}";
  }


  /** 
   * 値が未設定のAdminPermissionParamsオブジェクトを作成します。 
   */
  public AdminPermissionParams() {
    institution_code_is_modified = true;
    permission_level_is_modified = true;
    transaction_category_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAdminPermissionRowオブジェクトの値を利用してAdminPermissionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAdminPermissionRowオブジェクト 
   */
  public AdminPermissionParams( AdminPermissionRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    permission_level = p_row.getPermissionLevel();
    permission_level_is_set = p_row.getPermissionLevelIsSet();
    permission_level_is_modified = p_row.getPermissionLevelIsModified();
    transaction_category = p_row.getTransactionCategory();
    transaction_category_is_set = p_row.getTransactionCategoryIsSet();
    transaction_category_is_modified = p_row.getTransactionCategoryIsModified();
    update_permission_flag = p_row.getUpdatePermissionFlag();
    update_permission_flag_is_set = p_row.getUpdatePermissionFlagIsSet();
    update_permission_flag_is_modified = p_row.getUpdatePermissionFlagIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    update_timestamp = p_row.getUpdateTimestamp();
    update_timestamp_is_set = p_row.getUpdateTimestampIsSet();
    update_timestamp_is_modified = p_row.getUpdateTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      update_permission_flag_is_set = true;
      update_permission_flag_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      update_timestamp_is_set = true;
      update_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AdminPermissionRow ) )
       return false;
    return fieldsEqual( (AdminPermissionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAdminPermissionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AdminPermissionRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( permission_level == null ) {
      if ( row.getPermissionLevel() != null )
        return false;
    } else if ( !permission_level.equals( row.getPermissionLevel() ) ) {
        return false;
    }
    if ( transaction_category == null ) {
      if ( row.getTransactionCategory() != null )
        return false;
    } else if ( !transaction_category.equals( row.getTransactionCategory() ) ) {
        return false;
    }
    if ( update_permission_flag == null ) {
      if ( row.getUpdatePermissionFlag() != null )
        return false;
    } else if ( !update_permission_flag.equals( row.getUpdatePermissionFlag() ) ) {
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (permission_level!=null? permission_level.hashCode(): 0) 
        + (transaction_category!=null? transaction_category.hashCode(): 0) 
        + (update_permission_flag!=null? update_permission_flag.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (update_timestamp!=null? update_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !update_permission_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'update_permission_flag' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !update_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'update_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("permission_level",permission_level);
		map.put("transaction_category",transaction_category);
		map.put("update_permission_flag",update_permission_flag);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("update_timestamp",update_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( update_permission_flag_is_modified )
			map.put("update_permission_flag",update_permission_flag);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( update_timestamp_is_modified )
			map.put("update_timestamp",update_timestamp);
		if (map.size() == 0) {
			if ( update_permission_flag_is_set )
				map.put("update_permission_flag",update_permission_flag);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( update_timestamp_is_set )
				map.put("update_timestamp",update_timestamp);
		}
		return map;
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
   * <em>permission_level</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPermissionLevel()
  {
    return permission_level;
  }


  /** 
   * <em>permission_level</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPermissionLevelIsSet() {
    return permission_level_is_set;
  }


  /** 
   * <em>permission_level</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPermissionLevelIsModified() {
    return permission_level_is_modified;
  }


  /** 
   * <em>transaction_category</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransactionCategory()
  {
    return transaction_category;
  }


  /** 
   * <em>transaction_category</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactionCategoryIsSet() {
    return transaction_category_is_set;
  }


  /** 
   * <em>transaction_category</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactionCategoryIsModified() {
    return transaction_category_is_modified;
  }


  /** 
   * <em>update_permission_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getUpdatePermissionFlag()
  {
    return update_permission_flag;
  }


  /** 
   * <em>update_permission_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUpdatePermissionFlagIsSet() {
    return update_permission_flag_is_set;
  }


  /** 
   * <em>update_permission_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUpdatePermissionFlagIsModified() {
    return update_permission_flag_is_modified;
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
    return new AdminPermissionPK(institution_code, permission_level, transaction_category);
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>permission_level</em>カラムの値を設定します。 
   *
   * @@param p_permissionLevel <em>permission_level</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setPermissionLevel( String p_permissionLevel )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    permission_level = p_permissionLevel;
    permission_level_is_set = true;
    permission_level_is_modified = true;
  }


  /** 
   * <em>transaction_category</em>カラムの値を設定します。 
   *
   * @@param p_transactionCategory <em>transaction_category</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setTransactionCategory( String p_transactionCategory )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_category = p_transactionCategory;
    transaction_category_is_set = true;
    transaction_category_is_modified = true;
  }


  /** 
   * <em>update_permission_flag</em>カラムの値を設定します。 
   *
   * @@param p_updatePermissionFlag <em>update_permission_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setUpdatePermissionFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_updatePermissionFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    update_permission_flag = p_updatePermissionFlag;
    update_permission_flag_is_set = true;
    update_permission_flag_is_modified = true;
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
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                break;
            case 'p':
                if ( name.equals("permission_level") ) {
                    return this.permission_level;
                }
                break;
            case 't':
                if ( name.equals("transaction_category") ) {
                    return this.transaction_category;
                }
                break;
            case 'u':
                if ( name.equals("update_permission_flag") ) {
                    return this.update_permission_flag;
                }
                else if ( name.equals("update_timestamp") ) {
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
                if ( name.equals("permission_level") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'permission_level' must be String: '"+value+"' is not." );
                    this.permission_level = (String) value;
                    if (this.permission_level_is_set)
                        this.permission_level_is_modified = true;
                    this.permission_level_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("transaction_category") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transaction_category' must be String: '"+value+"' is not." );
                    this.transaction_category = (String) value;
                    if (this.transaction_category_is_set)
                        this.transaction_category_is_modified = true;
                    this.transaction_category_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("update_permission_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'update_permission_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.update_permission_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.update_permission_flag_is_set)
                        this.update_permission_flag_is_modified = true;
                    this.update_permission_flag_is_set = true;
                    return;
                }
                else if ( name.equals("update_timestamp") ) {
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
