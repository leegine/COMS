head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.42.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	SsoMessageParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * sso_messageテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SsoMessageRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SsoMessageParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SsoMessageParams}が{@@link SsoMessageRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SsoMessagePK 
 * @@see SsoMessageRow 
 */
public class SsoMessageParams extends Params implements SsoMessageRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "sso_message";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SsoMessageRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SsoMessageRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>fx_login_id</em>カラムの値 
   */
  public  long  fx_login_id;

  /** 
   * <em>random</em>カラムの値 
   */
  public  String  random;

  /** 
   * <em>operation_time</em>カラムの値 
   */
  public  String  operation_time;

  /** 
   * <em>encrypt_string</em>カラムの値 
   */
  public  String  encrypt_string;

  /** 
   * <em>secret_key</em>カラムの値 
   */
  public  String  secret_key;

  /** 
   * <em>hash_key</em>カラムの値 
   */
  public  String  hash_key;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean fx_login_id_is_set = false;
  private boolean fx_login_id_is_modified = false;
  private boolean random_is_set = false;
  private boolean random_is_modified = false;
  private boolean operation_time_is_set = false;
  private boolean operation_time_is_modified = false;
  private boolean encrypt_string_is_set = false;
  private boolean encrypt_string_is_modified = false;
  private boolean secret_key_is_set = false;
  private boolean secret_key_is_modified = false;
  private boolean hash_key_is_set = false;
  private boolean hash_key_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "fx_login_id=" +fx_login_id
      + "," + "random=" +random
      + "," + "operation_time=" +operation_time
      + "," + "encrypt_string=" +encrypt_string
      + "," + "secret_key=" +secret_key
      + "," + "hash_key=" +hash_key
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のSsoMessageParamsオブジェクトを作成します。 
   */
  public SsoMessageParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSsoMessageRowオブジェクトの値を利用してSsoMessageParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSsoMessageRowオブジェクト 
   */
  public SsoMessageParams( SsoMessageRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    fx_login_id = p_row.getFxLoginId();
    fx_login_id_is_set = p_row.getFxLoginIdIsSet();
    fx_login_id_is_modified = p_row.getFxLoginIdIsModified();
    random = p_row.getRandom();
    random_is_set = p_row.getRandomIsSet();
    random_is_modified = p_row.getRandomIsModified();
    operation_time = p_row.getOperationTime();
    operation_time_is_set = p_row.getOperationTimeIsSet();
    operation_time_is_modified = p_row.getOperationTimeIsModified();
    encrypt_string = p_row.getEncryptString();
    encrypt_string_is_set = p_row.getEncryptStringIsSet();
    encrypt_string_is_modified = p_row.getEncryptStringIsModified();
    secret_key = p_row.getSecretKey();
    secret_key_is_set = p_row.getSecretKeyIsSet();
    secret_key_is_modified = p_row.getSecretKeyIsModified();
    hash_key = p_row.getHashKey();
    hash_key_is_set = p_row.getHashKeyIsSet();
    hash_key_is_modified = p_row.getHashKeyIsModified();
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
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      fx_login_id_is_set = true;
      fx_login_id_is_modified = true;
      random_is_set = true;
      random_is_modified = true;
      operation_time_is_set = true;
      operation_time_is_modified = true;
      encrypt_string_is_set = true;
      encrypt_string_is_modified = true;
      secret_key_is_set = true;
      secret_key_is_modified = true;
      hash_key_is_set = true;
      hash_key_is_modified = true;
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
    if ( !( other instanceof SsoMessageRow ) )
       return false;
    return fieldsEqual( (SsoMessageRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSsoMessageRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SsoMessageRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( fx_login_id != row.getFxLoginId() )
      return false;
    if ( random == null ) {
      if ( row.getRandom() != null )
        return false;
    } else if ( !random.equals( row.getRandom() ) ) {
        return false;
    }
    if ( operation_time == null ) {
      if ( row.getOperationTime() != null )
        return false;
    } else if ( !operation_time.equals( row.getOperationTime() ) ) {
        return false;
    }
    if ( encrypt_string == null ) {
      if ( row.getEncryptString() != null )
        return false;
    } else if ( !encrypt_string.equals( row.getEncryptString() ) ) {
        return false;
    }
    if ( secret_key == null ) {
      if ( row.getSecretKey() != null )
        return false;
    } else if ( !secret_key.equals( row.getSecretKey() ) ) {
        return false;
    }
    if ( hash_key == null ) {
      if ( row.getHashKey() != null )
        return false;
    } else if ( !hash_key.equals( row.getHashKey() ) ) {
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + ((int) fx_login_id)
        + (random!=null? random.hashCode(): 0) 
        + (operation_time!=null? operation_time.hashCode(): 0) 
        + (encrypt_string!=null? encrypt_string.hashCode(): 0) 
        + (secret_key!=null? secret_key.hashCode(): 0) 
        + (hash_key!=null? hash_key.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !fx_login_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'fx_login_id' must be set before inserting.");
		if ( !random_is_set )
			throw new IllegalArgumentException("non-nullable field 'random' must be set before inserting.");
		if ( !operation_time_is_set )
			throw new IllegalArgumentException("non-nullable field 'operation_time' must be set before inserting.");
		if ( !encrypt_string_is_set )
			throw new IllegalArgumentException("non-nullable field 'encrypt_string' must be set before inserting.");
		if ( !secret_key_is_set )
			throw new IllegalArgumentException("non-nullable field 'secret_key' must be set before inserting.");
		if ( !hash_key_is_set )
			throw new IllegalArgumentException("non-nullable field 'hash_key' must be set before inserting.");
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
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		map.put("fx_login_id",new Long(fx_login_id));
		map.put("random",random);
		map.put("operation_time",operation_time);
		map.put("encrypt_string",encrypt_string);
		map.put("secret_key",secret_key);
		map.put("hash_key",hash_key);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( fx_login_id_is_modified )
			map.put("fx_login_id",new Long(fx_login_id));
		if ( random_is_modified )
			map.put("random",random);
		if ( operation_time_is_modified )
			map.put("operation_time",operation_time);
		if ( encrypt_string_is_modified )
			map.put("encrypt_string",encrypt_string);
		if ( secret_key_is_modified )
			map.put("secret_key",secret_key);
		if ( hash_key_is_modified )
			map.put("hash_key",hash_key);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			if ( fx_login_id_is_set )
				map.put("fx_login_id",new Long(fx_login_id));
			if ( random_is_set )
				map.put("random",random);
			if ( operation_time_is_set )
				map.put("operation_time",operation_time);
			if ( encrypt_string_is_set )
				map.put("encrypt_string",encrypt_string);
			if ( secret_key_is_set )
				map.put("secret_key",secret_key);
			if ( hash_key_is_set )
				map.put("hash_key",hash_key);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
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
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsModified() {
    return account_code_is_modified;
  }


  /** 
   * <em>fx_login_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFxLoginId()
  {
    return fx_login_id;
  }


  /** 
   * <em>fx_login_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxLoginIdIsSet() {
    return fx_login_id_is_set;
  }


  /** 
   * <em>fx_login_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxLoginIdIsModified() {
    return fx_login_id_is_modified;
  }


  /** 
   * <em>random</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRandom()
  {
    return random;
  }


  /** 
   * <em>random</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRandomIsSet() {
    return random_is_set;
  }


  /** 
   * <em>random</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRandomIsModified() {
    return random_is_modified;
  }


  /** 
   * <em>operation_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOperationTime()
  {
    return operation_time;
  }


  /** 
   * <em>operation_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperationTimeIsSet() {
    return operation_time_is_set;
  }


  /** 
   * <em>operation_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperationTimeIsModified() {
    return operation_time_is_modified;
  }


  /** 
   * <em>encrypt_string</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEncryptString()
  {
    return encrypt_string;
  }


  /** 
   * <em>encrypt_string</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEncryptStringIsSet() {
    return encrypt_string_is_set;
  }


  /** 
   * <em>encrypt_string</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEncryptStringIsModified() {
    return encrypt_string_is_modified;
  }


  /** 
   * <em>secret_key</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSecretKey()
  {
    return secret_key;
  }


  /** 
   * <em>secret_key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecretKeyIsSet() {
    return secret_key_is_set;
  }


  /** 
   * <em>secret_key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecretKeyIsModified() {
    return secret_key_is_modified;
  }


  /** 
   * <em>hash_key</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHashKey()
  {
    return hash_key;
  }


  /** 
   * <em>hash_key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashKeyIsSet() {
    return hash_key_is_set;
  }


  /** 
   * <em>hash_key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashKeyIsModified() {
    return hash_key_is_modified;
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
    throw new UnsupportedOperationException("Table sso_message has no primary key.");
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
   * <em>account_code</em>カラムの値を設定します。 
   *
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>fx_login_id</em>カラムの値を設定します。 
   *
   * @@param p_fxLoginId <em>fx_login_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setFxLoginId( long p_fxLoginId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_login_id = p_fxLoginId;
    fx_login_id_is_set = true;
    fx_login_id_is_modified = true;
  }


  /** 
   * <em>random</em>カラムの値を設定します。 
   *
   * @@param p_random <em>random</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setRandom( String p_random )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    random = p_random;
    random_is_set = true;
    random_is_modified = true;
  }


  /** 
   * <em>operation_time</em>カラムの値を設定します。 
   *
   * @@param p_operationTime <em>operation_time</em>カラムの値をあらわす12桁以下のString値 
   */
  public final void setOperationTime( String p_operationTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    operation_time = p_operationTime;
    operation_time_is_set = true;
    operation_time_is_modified = true;
  }


  /** 
   * <em>encrypt_string</em>カラムの値を設定します。 
   *
   * @@param p_encryptString <em>encrypt_string</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setEncryptString( String p_encryptString )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    encrypt_string = p_encryptString;
    encrypt_string_is_set = true;
    encrypt_string_is_modified = true;
  }


  /** 
   * <em>secret_key</em>カラムの値を設定します。 
   *
   * @@param p_secretKey <em>secret_key</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setSecretKey( String p_secretKey )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    secret_key = p_secretKey;
    secret_key_is_set = true;
    secret_key_is_modified = true;
  }


  /** 
   * <em>hash_key</em>カラムの値を設定します。 
   *
   * @@param p_hashKey <em>hash_key</em>カラムの値をあらわす32桁以下のString値 
   */
  public final void setHashKey( String p_hashKey )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    hash_key = p_hashKey;
    hash_key_is_set = true;
    hash_key_is_modified = true;
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
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("encrypt_string") ) {
                    return this.encrypt_string;
                }
                break;
            case 'f':
                if ( name.equals("fx_login_id") ) {
                    return new Long( fx_login_id );
                }
                break;
            case 'h':
                if ( name.equals("hash_key") ) {
                    return this.hash_key;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("operation_time") ) {
                    return this.operation_time;
                }
                break;
            case 'r':
                if ( name.equals("random") ) {
                    return this.random;
                }
                break;
            case 's':
                if ( name.equals("secret_key") ) {
                    return this.secret_key;
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
                if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
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
                if ( name.equals("encrypt_string") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'encrypt_string' must be String: '"+value+"' is not." );
                    this.encrypt_string = (String) value;
                    if (this.encrypt_string_is_set)
                        this.encrypt_string_is_modified = true;
                    this.encrypt_string_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fx_login_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'fx_login_id' must be Long: '"+value+"' is not." );
                    this.fx_login_id = ((Long) value).longValue();
                    if (this.fx_login_id_is_set)
                        this.fx_login_id_is_modified = true;
                    this.fx_login_id_is_set = true;
                    return;
                }
                break;
            case 'h':
                if ( name.equals("hash_key") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'hash_key' must be String: '"+value+"' is not." );
                    this.hash_key = (String) value;
                    if (this.hash_key_is_set)
                        this.hash_key_is_modified = true;
                    this.hash_key_is_set = true;
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
                if ( name.equals("operation_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'operation_time' must be String: '"+value+"' is not." );
                    this.operation_time = (String) value;
                    if (this.operation_time_is_set)
                        this.operation_time_is_modified = true;
                    this.operation_time_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("random") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'random' must be String: '"+value+"' is not." );
                    this.random = (String) value;
                    if (this.random_is_set)
                        this.random_is_modified = true;
                    this.random_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("secret_key") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'secret_key' must be String: '"+value+"' is not." );
                    this.secret_key = (String) value;
                    if (this.secret_key_is_set)
                        this.secret_key_is_modified = true;
                    this.secret_key_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
