head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorParams.java;


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
 * administratorテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AdministratorRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AdministratorParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AdministratorParams}が{@@link AdministratorRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AdministratorPK 
 * @@see AdministratorRow 
 */
public class AdministratorParams extends Params implements AdministratorRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "administrator";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AdministratorRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AdministratorRow.TYPE;
  }


  /** 
   * <em>administrator_id</em>カラムの値 
   */
  public  long  administrator_id;

  /** 
   * <em>administrator_code</em>カラムの値 
   */
  public  String  administrator_code;

  /** 
   * <em>institution_id</em>カラムの値 
   */
  public  long  institution_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  Long  branch_id;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>login_id</em>カラムの値 
   */
  public  long  login_id;

  /** 
   * <em>name</em>カラムの値 
   */
  public  String  name;

  /** 
   * <em>email_address</em>カラムの値 
   */
  public  String  email_address;

  /** 
   * <em>trading_password</em>カラムの値 
   */
  public  String  trading_password;

  /** 
   * <em>permission_level</em>カラムの値 
   */
  public  String  permission_level;

  /** 
   * <em>note</em>カラムの値 
   */
  public  String  note;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean administrator_id_is_set = false;
  private boolean administrator_id_is_modified = false;
  private boolean administrator_code_is_set = false;
  private boolean administrator_code_is_modified = false;
  private boolean institution_id_is_set = false;
  private boolean institution_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean login_id_is_set = false;
  private boolean login_id_is_modified = false;
  private boolean name_is_set = false;
  private boolean name_is_modified = false;
  private boolean email_address_is_set = false;
  private boolean email_address_is_modified = false;
  private boolean trading_password_is_set = false;
  private boolean trading_password_is_modified = false;
  private boolean permission_level_is_set = false;
  private boolean permission_level_is_modified = false;
  private boolean note_is_set = false;
  private boolean note_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "administrator_id=" + administrator_id
      + "," + "administrator_code=" +administrator_code
      + "," + "institution_id=" +institution_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_id=" +branch_id
      + "," + "branch_code=" +branch_code
      + "," + "login_id=" +login_id
      + "," + "name=" +name
      + "," + "email_address=" +email_address
      + "," + "trading_password=" +trading_password
      + "," + "permission_level=" +permission_level
      + "," + "note=" +note
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のAdministratorParamsオブジェクトを作成します。 
   */
  public AdministratorParams() {
    administrator_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAdministratorRowオブジェクトの値を利用してAdministratorParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAdministratorRowオブジェクト 
   */
  public AdministratorParams( AdministratorRow p_row )
  {
    administrator_id = p_row.getAdministratorId();
    administrator_id_is_set = p_row.getAdministratorIdIsSet();
    administrator_id_is_modified = p_row.getAdministratorIdIsModified();
    administrator_code = p_row.getAdministratorCode();
    administrator_code_is_set = p_row.getAdministratorCodeIsSet();
    administrator_code_is_modified = p_row.getAdministratorCodeIsModified();
    institution_id = p_row.getInstitutionId();
    institution_id_is_set = p_row.getInstitutionIdIsSet();
    institution_id_is_modified = p_row.getInstitutionIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    if ( !p_row.getBranchIdIsNull() )
      branch_id = new Long( p_row.getBranchId() );
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    login_id = p_row.getLoginId();
    login_id_is_set = p_row.getLoginIdIsSet();
    login_id_is_modified = p_row.getLoginIdIsModified();
    name = p_row.getName();
    name_is_set = p_row.getNameIsSet();
    name_is_modified = p_row.getNameIsModified();
    email_address = p_row.getEmailAddress();
    email_address_is_set = p_row.getEmailAddressIsSet();
    email_address_is_modified = p_row.getEmailAddressIsModified();
    trading_password = p_row.getTradingPassword();
    trading_password_is_set = p_row.getTradingPasswordIsSet();
    trading_password_is_modified = p_row.getTradingPasswordIsModified();
    permission_level = p_row.getPermissionLevel();
    permission_level_is_set = p_row.getPermissionLevelIsSet();
    permission_level_is_modified = p_row.getPermissionLevelIsModified();
    note = p_row.getNote();
    note_is_set = p_row.getNoteIsSet();
    note_is_modified = p_row.getNoteIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
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
      administrator_code_is_set = true;
      administrator_code_is_modified = true;
      institution_id_is_set = true;
      institution_id_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_id_is_set = true;
      branch_id_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      login_id_is_set = true;
      login_id_is_modified = true;
      name_is_set = true;
      name_is_modified = true;
      email_address_is_set = true;
      email_address_is_modified = true;
      trading_password_is_set = true;
      trading_password_is_modified = true;
      permission_level_is_set = true;
      permission_level_is_modified = true;
      note_is_set = true;
      note_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
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
    if ( !( other instanceof AdministratorRow ) )
       return false;
    return fieldsEqual( (AdministratorRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAdministratorRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AdministratorRow row )
  {
    if ( administrator_id != row.getAdministratorId() )
      return false;
    if ( administrator_code == null ) {
      if ( row.getAdministratorCode() != null )
        return false;
    } else if ( !administrator_code.equals( row.getAdministratorCode() ) ) {
        return false;
    }
    if ( institution_id != row.getInstitutionId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_id == null ) {
      if ( !row.getBranchIdIsNull() )
        return false;
    } else if ( row.getBranchIdIsNull() || ( branch_id.longValue() != row.getBranchId() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( login_id != row.getLoginId() )
      return false;
    if ( name == null ) {
      if ( row.getName() != null )
        return false;
    } else if ( !name.equals( row.getName() ) ) {
        return false;
    }
    if ( email_address == null ) {
      if ( row.getEmailAddress() != null )
        return false;
    } else if ( !email_address.equals( row.getEmailAddress() ) ) {
        return false;
    }
    if ( trading_password == null ) {
      if ( row.getTradingPassword() != null )
        return false;
    } else if ( !trading_password.equals( row.getTradingPassword() ) ) {
        return false;
    }
    if ( permission_level == null ) {
      if ( row.getPermissionLevel() != null )
        return false;
    } else if ( !permission_level.equals( row.getPermissionLevel() ) ) {
        return false;
    }
    if ( note == null ) {
      if ( row.getNote() != null )
        return false;
    } else if ( !note.equals( row.getNote() ) ) {
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
      return  ((int) administrator_id)
        + (administrator_code!=null? administrator_code.hashCode(): 0) 
        + ((int) institution_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_id!=null? branch_id.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + ((int) login_id)
        + (name!=null? name.hashCode(): 0) 
        + (email_address!=null? email_address.hashCode(): 0) 
        + (trading_password!=null? trading_password.hashCode(): 0) 
        + (permission_level!=null? permission_level.hashCode(): 0) 
        + (note!=null? note.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !administrator_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'administrator_code' must be set before inserting.");
		if ( !institution_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_id' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !login_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'login_id' must be set before inserting.");
		if ( !permission_level_is_set )
			throw new IllegalArgumentException("non-nullable field 'permission_level' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("administrator_id",new Long(administrator_id));
		map.put("administrator_code",administrator_code);
		map.put("institution_id",new Long(institution_id));
		map.put("institution_code",institution_code);
		if ( branch_id != null )
			map.put("branch_id",branch_id);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		map.put("login_id",new Long(login_id));
		if ( name != null )
			map.put("name",name);
		if ( email_address != null )
			map.put("email_address",email_address);
		if ( trading_password != null )
			map.put("trading_password",trading_password);
		map.put("permission_level",permission_level);
		if ( note != null )
			map.put("note",note);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
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
		if ( administrator_code_is_modified )
			map.put("administrator_code",administrator_code);
		if ( institution_id_is_modified )
			map.put("institution_id",new Long(institution_id));
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_id_is_modified )
			map.put("branch_id",branch_id);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( login_id_is_modified )
			map.put("login_id",new Long(login_id));
		if ( name_is_modified )
			map.put("name",name);
		if ( email_address_is_modified )
			map.put("email_address",email_address);
		if ( trading_password_is_modified )
			map.put("trading_password",trading_password);
		if ( permission_level_is_modified )
			map.put("permission_level",permission_level);
		if ( note_is_modified )
			map.put("note",note);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( administrator_code_is_set )
				map.put("administrator_code",administrator_code);
			if ( institution_id_is_set )
				map.put("institution_id",new Long(institution_id));
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			map.put("branch_id",branch_id);
			map.put("branch_code",branch_code);
			if ( login_id_is_set )
				map.put("login_id",new Long(login_id));
			map.put("name",name);
			map.put("email_address",email_address);
			map.put("trading_password",trading_password);
			if ( permission_level_is_set )
				map.put("permission_level",permission_level);
			map.put("note",note);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>administrator_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAdministratorId()
  {
    return administrator_id;
  }


  /** 
   * <em>administrator_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdministratorIdIsSet() {
    return administrator_id_is_set;
  }


  /** 
   * <em>administrator_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdministratorIdIsModified() {
    return administrator_id_is_modified;
  }


  /** 
   * <em>administrator_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAdministratorCode()
  {
    return administrator_code;
  }


  /** 
   * <em>administrator_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdministratorCodeIsSet() {
    return administrator_code_is_set;
  }


  /** 
   * <em>administrator_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdministratorCodeIsModified() {
    return administrator_code_is_modified;
  }


  /** 
   * <em>institution_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getInstitutionId()
  {
    return institution_id;
  }


  /** 
   * <em>institution_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionIdIsSet() {
    return institution_id_is_set;
  }


  /** 
   * <em>institution_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionIdIsModified() {
    return institution_id_is_modified;
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
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBranchId()
  {
    return ( branch_id==null? ((long)0): branch_id.longValue() );
  }


  /** 
   * <em>branch_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBranchIdIsNull()
  {
    return branch_id == null;
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
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
  }


  /** 
   * <em>login_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getLoginId()
  {
    return login_id;
  }


  /** 
   * <em>login_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginIdIsSet() {
    return login_id_is_set;
  }


  /** 
   * <em>login_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginIdIsModified() {
    return login_id_is_modified;
  }


  /** 
   * <em>name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getName()
  {
    return name;
  }


  /** 
   * <em>name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameIsSet() {
    return name_is_set;
  }


  /** 
   * <em>name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameIsModified() {
    return name_is_modified;
  }


  /** 
   * <em>email_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEmailAddress()
  {
    return email_address;
  }


  /** 
   * <em>email_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressIsSet() {
    return email_address_is_set;
  }


  /** 
   * <em>email_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressIsModified() {
    return email_address_is_modified;
  }


  /** 
   * <em>trading_password</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradingPassword()
  {
    return trading_password;
  }


  /** 
   * <em>trading_password</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingPasswordIsSet() {
    return trading_password_is_set;
  }


  /** 
   * <em>trading_password</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingPasswordIsModified() {
    return trading_password_is_modified;
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
   * <em>note</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNote()
  {
    return note;
  }


  /** 
   * <em>note</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoteIsSet() {
    return note_is_set;
  }


  /** 
   * <em>note</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoteIsModified() {
    return note_is_modified;
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
    return new AdministratorPK(administrator_id);
  }


  /** 
   * <em>administrator_id</em>カラムの値を設定します。 
   *
   * @@param p_administratorId <em>administrator_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAdministratorId( long p_administratorId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    administrator_id = p_administratorId;
    administrator_id_is_set = true;
    administrator_id_is_modified = true;
  }


  /** 
   * <em>administrator_code</em>カラムの値を設定します。 
   *
   * @@param p_administratorCode <em>administrator_code</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setAdministratorCode( String p_administratorCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    administrator_code = p_administratorCode;
    administrator_code_is_set = true;
    administrator_code_is_modified = true;
  }


  /** 
   * <em>institution_id</em>カラムの値を設定します。 
   *
   * @@param p_institutionId <em>institution_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setInstitutionId( long p_institutionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_id = p_institutionId;
    institution_id_is_set = true;
    institution_id_is_modified = true;
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
   * <em>branch_id</em>カラムの値を設定します。 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = new Long(p_branchId);
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>branch_id</em>カラムに値を設定します。 
   */
  public final void setBranchId( Long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>branch_code</em>カラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>login_id</em>カラムの値を設定します。 
   *
   * @@param p_loginId <em>login_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setLoginId( long p_loginId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    login_id = p_loginId;
    login_id_is_set = true;
    login_id_is_modified = true;
  }


  /** 
   * <em>name</em>カラムの値を設定します。 
   *
   * @@param p_name <em>name</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setName( String p_name )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name = p_name;
    name_is_set = true;
    name_is_modified = true;
  }


  /** 
   * <em>email_address</em>カラムの値を設定します。 
   *
   * @@param p_emailAddress <em>email_address</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setEmailAddress( String p_emailAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    email_address = p_emailAddress;
    email_address_is_set = true;
    email_address_is_modified = true;
  }


  /** 
   * <em>trading_password</em>カラムの値を設定します。 
   *
   * @@param p_tradingPassword <em>trading_password</em>カラムの値をあらわす48桁以下のString値 
   */
  public final void setTradingPassword( String p_tradingPassword )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_password = p_tradingPassword;
    trading_password_is_set = true;
    trading_password_is_modified = true;
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
   * <em>note</em>カラムの値を設定します。 
   *
   * @@param p_note <em>note</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setNote( String p_note )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    note = p_note;
    note_is_set = true;
    note_is_modified = true;
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
                if ( name.equals("administrator_id") ) {
                    return new Long( administrator_id );
                }
                else if ( name.equals("administrator_code") ) {
                    return this.administrator_code;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return this.branch_id;
                }
                else if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("email_address") ) {
                    return this.email_address;
                }
                break;
            case 'i':
                if ( name.equals("institution_id") ) {
                    return new Long( institution_id );
                }
                else if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("login_id") ) {
                    return new Long( login_id );
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("name") ) {
                    return this.name;
                }
                else if ( name.equals("note") ) {
                    return this.note;
                }
                break;
            case 'p':
                if ( name.equals("permission_level") ) {
                    return this.permission_level;
                }
                break;
            case 't':
                if ( name.equals("trading_password") ) {
                    return this.trading_password;
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
                if ( name.equals("administrator_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'administrator_id' must be Long: '"+value+"' is not." );
                    this.administrator_id = ((Long) value).longValue();
                    if (this.administrator_id_is_set)
                        this.administrator_id_is_modified = true;
                    this.administrator_id_is_set = true;
                    return;
                }
                else if ( name.equals("administrator_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'administrator_code' must be String: '"+value+"' is not." );
                    this.administrator_code = (String) value;
                    if (this.administrator_code_is_set)
                        this.administrator_code_is_modified = true;
                    this.administrator_code_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = (Long) value;
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                else if ( name.equals("branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("email_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'email_address' must be String: '"+value+"' is not." );
                    this.email_address = (String) value;
                    if (this.email_address_is_set)
                        this.email_address_is_modified = true;
                    this.email_address_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'institution_id' must be Long: '"+value+"' is not." );
                    this.institution_id = ((Long) value).longValue();
                    if (this.institution_id_is_set)
                        this.institution_id_is_modified = true;
                    this.institution_id_is_set = true;
                    return;
                }
                else if ( name.equals("institution_code") ) {
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
                if ( name.equals("login_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'login_id' must be Long: '"+value+"' is not." );
                    this.login_id = ((Long) value).longValue();
                    if (this.login_id_is_set)
                        this.login_id_is_modified = true;
                    this.login_id_is_set = true;
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
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'name' must be String: '"+value+"' is not." );
                    this.name = (String) value;
                    if (this.name_is_set)
                        this.name_is_modified = true;
                    this.name_is_set = true;
                    return;
                }
                else if ( name.equals("note") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'note' must be String: '"+value+"' is not." );
                    this.note = (String) value;
                    if (this.note_is_set)
                        this.note_is_modified = true;
                    this.note_is_set = true;
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
                if ( name.equals("trading_password") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_password' must be String: '"+value+"' is not." );
                    this.trading_password = (String) value;
                    if (this.trading_password_is_set)
                        this.trading_password_is_modified = true;
                    this.trading_password_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
