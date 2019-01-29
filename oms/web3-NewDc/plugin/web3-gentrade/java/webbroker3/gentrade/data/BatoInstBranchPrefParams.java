head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.37.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BatoInstBranchPrefParams.java;


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
 * bato_inst_branch_prefテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BatoInstBranchPrefRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BatoInstBranchPrefParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BatoInstBranchPrefParams}が{@@link BatoInstBranchPrefRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BatoInstBranchPrefPK 
 * @@see BatoInstBranchPrefRow 
 */
public class BatoInstBranchPrefParams extends Params implements BatoInstBranchPrefRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bato_inst_branch_pref";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BatoInstBranchPrefRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BatoInstBranchPrefRow.TYPE;
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
   * <em>url</em>カラムの値 
   */
  public  String  url;

  /** 
   * <em>soap_url</em>カラムの値 
   */
  public  String  soap_url;

  /** 
   * <em>hash_field1</em>カラムの値 
   */
  public  String  hash_field1;

  /** 
   * <em>hash_field2</em>カラムの値 
   */
  public  String  hash_field2;

  /** 
   * <em>system_failure_flag</em>カラムの値 
   */
  public  String  system_failure_flag;

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

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean url_is_set = false;
  private boolean url_is_modified = false;
  private boolean soap_url_is_set = false;
  private boolean soap_url_is_modified = false;
  private boolean hash_field1_is_set = false;
  private boolean hash_field1_is_modified = false;
  private boolean hash_field2_is_set = false;
  private boolean hash_field2_is_modified = false;
  private boolean system_failure_flag_is_set = false;
  private boolean system_failure_flag_is_modified = false;
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
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "url=" +url
      + "," + "soap_url=" +soap_url
      + "," + "hash_field1=" +hash_field1
      + "," + "hash_field2=" +hash_field2
      + "," + "system_failure_flag=" +system_failure_flag
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のBatoInstBranchPrefParamsオブジェクトを作成します。 
   */
  public BatoInstBranchPrefParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBatoInstBranchPrefRowオブジェクトの値を利用してBatoInstBranchPrefParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBatoInstBranchPrefRowオブジェクト 
   */
  public BatoInstBranchPrefParams( BatoInstBranchPrefRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    url = p_row.getUrl();
    url_is_set = p_row.getUrlIsSet();
    url_is_modified = p_row.getUrlIsModified();
    soap_url = p_row.getSoapUrl();
    soap_url_is_set = p_row.getSoapUrlIsSet();
    soap_url_is_modified = p_row.getSoapUrlIsModified();
    hash_field1 = p_row.getHashField1();
    hash_field1_is_set = p_row.getHashField1IsSet();
    hash_field1_is_modified = p_row.getHashField1IsModified();
    hash_field2 = p_row.getHashField2();
    hash_field2_is_set = p_row.getHashField2IsSet();
    hash_field2_is_modified = p_row.getHashField2IsModified();
    system_failure_flag = p_row.getSystemFailureFlag();
    system_failure_flag_is_set = p_row.getSystemFailureFlagIsSet();
    system_failure_flag_is_modified = p_row.getSystemFailureFlagIsModified();
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
      url_is_set = true;
      url_is_modified = true;
      soap_url_is_set = true;
      soap_url_is_modified = true;
      hash_field1_is_set = true;
      hash_field1_is_modified = true;
      hash_field2_is_set = true;
      hash_field2_is_modified = true;
      system_failure_flag_is_set = true;
      system_failure_flag_is_modified = true;
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
    if ( !( other instanceof BatoInstBranchPrefRow ) )
       return false;
    return fieldsEqual( (BatoInstBranchPrefRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBatoInstBranchPrefRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BatoInstBranchPrefRow row )
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
    if ( url == null ) {
      if ( row.getUrl() != null )
        return false;
    } else if ( !url.equals( row.getUrl() ) ) {
        return false;
    }
    if ( soap_url == null ) {
      if ( row.getSoapUrl() != null )
        return false;
    } else if ( !soap_url.equals( row.getSoapUrl() ) ) {
        return false;
    }
    if ( hash_field1 == null ) {
      if ( row.getHashField1() != null )
        return false;
    } else if ( !hash_field1.equals( row.getHashField1() ) ) {
        return false;
    }
    if ( hash_field2 == null ) {
      if ( row.getHashField2() != null )
        return false;
    } else if ( !hash_field2.equals( row.getHashField2() ) ) {
        return false;
    }
    if ( system_failure_flag == null ) {
      if ( row.getSystemFailureFlag() != null )
        return false;
    } else if ( !system_failure_flag.equals( row.getSystemFailureFlag() ) ) {
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (url!=null? url.hashCode(): 0) 
        + (soap_url!=null? soap_url.hashCode(): 0) 
        + (hash_field1!=null? hash_field1.hashCode(): 0) 
        + (hash_field2!=null? hash_field2.hashCode(): 0) 
        + (system_failure_flag!=null? system_failure_flag.hashCode(): 0) 
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
		if ( !url_is_set )
			throw new IllegalArgumentException("non-nullable field 'url' must be set before inserting.");
		if ( !soap_url_is_set )
			throw new IllegalArgumentException("non-nullable field 'soap_url' must be set before inserting.");
		if ( !hash_field1_is_set )
			throw new IllegalArgumentException("non-nullable field 'hash_field1' must be set before inserting.");
		if ( !hash_field2_is_set )
			throw new IllegalArgumentException("non-nullable field 'hash_field2' must be set before inserting.");
		if ( !system_failure_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'system_failure_flag' must be set before inserting.");
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
		map.put("url",url);
		map.put("soap_url",soap_url);
		map.put("hash_field1",hash_field1);
		map.put("hash_field2",hash_field2);
		map.put("system_failure_flag",system_failure_flag);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( url_is_modified )
			map.put("url",url);
		if ( soap_url_is_modified )
			map.put("soap_url",soap_url);
		if ( hash_field1_is_modified )
			map.put("hash_field1",hash_field1);
		if ( hash_field2_is_modified )
			map.put("hash_field2",hash_field2);
		if ( system_failure_flag_is_modified )
			map.put("system_failure_flag",system_failure_flag);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( url_is_set )
				map.put("url",url);
			if ( soap_url_is_set )
				map.put("soap_url",soap_url);
			if ( hash_field1_is_set )
				map.put("hash_field1",hash_field1);
			if ( hash_field2_is_set )
				map.put("hash_field2",hash_field2);
			if ( system_failure_flag_is_set )
				map.put("system_failure_flag",system_failure_flag);
			map.put("last_updater",last_updater);
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
   * <em>url</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getUrl()
  {
    return url;
  }


  /** 
   * <em>url</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUrlIsSet() {
    return url_is_set;
  }


  /** 
   * <em>url</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUrlIsModified() {
    return url_is_modified;
  }


  /** 
   * <em>soap_url</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSoapUrl()
  {
    return soap_url;
  }


  /** 
   * <em>soap_url</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSoapUrlIsSet() {
    return soap_url_is_set;
  }


  /** 
   * <em>soap_url</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSoapUrlIsModified() {
    return soap_url_is_modified;
  }


  /** 
   * <em>hash_field1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHashField1()
  {
    return hash_field1;
  }


  /** 
   * <em>hash_field1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashField1IsSet() {
    return hash_field1_is_set;
  }


  /** 
   * <em>hash_field1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashField1IsModified() {
    return hash_field1_is_modified;
  }


  /** 
   * <em>hash_field2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHashField2()
  {
    return hash_field2;
  }


  /** 
   * <em>hash_field2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashField2IsSet() {
    return hash_field2_is_set;
  }


  /** 
   * <em>hash_field2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashField2IsModified() {
    return hash_field2_is_modified;
  }


  /** 
   * <em>system_failure_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSystemFailureFlag()
  {
    return system_failure_flag;
  }


  /** 
   * <em>system_failure_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSystemFailureFlagIsSet() {
    return system_failure_flag_is_set;
  }


  /** 
   * <em>system_failure_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSystemFailureFlagIsModified() {
    return system_failure_flag_is_modified;
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
    return new BatoInstBranchPrefPK(institution_code, branch_code);
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
   * <em>url</em>カラムの値を設定します。 
   *
   * @@param p_url <em>url</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setUrl( String p_url )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    url = p_url;
    url_is_set = true;
    url_is_modified = true;
  }


  /** 
   * <em>soap_url</em>カラムの値を設定します。 
   *
   * @@param p_soapUrl <em>soap_url</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setSoapUrl( String p_soapUrl )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    soap_url = p_soapUrl;
    soap_url_is_set = true;
    soap_url_is_modified = true;
  }


  /** 
   * <em>hash_field1</em>カラムの値を設定します。 
   *
   * @@param p_hashField1 <em>hash_field1</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setHashField1( String p_hashField1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    hash_field1 = p_hashField1;
    hash_field1_is_set = true;
    hash_field1_is_modified = true;
  }


  /** 
   * <em>hash_field2</em>カラムの値を設定します。 
   *
   * @@param p_hashField2 <em>hash_field2</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setHashField2( String p_hashField2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    hash_field2 = p_hashField2;
    hash_field2_is_set = true;
    hash_field2_is_modified = true;
  }


  /** 
   * <em>system_failure_flag</em>カラムの値を設定します。 
   *
   * @@param p_systemFailureFlag <em>system_failure_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSystemFailureFlag( String p_systemFailureFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    system_failure_flag = p_systemFailureFlag;
    system_failure_flag_is_set = true;
    system_failure_flag_is_modified = true;
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
            case 'h':
                if ( name.equals("hash_field1") ) {
                    return this.hash_field1;
                }
                else if ( name.equals("hash_field2") ) {
                    return this.hash_field2;
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
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 's':
                if ( name.equals("soap_url") ) {
                    return this.soap_url;
                }
                else if ( name.equals("system_failure_flag") ) {
                    return this.system_failure_flag;
                }
                break;
            case 'u':
                if ( name.equals("url") ) {
                    return this.url;
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
            case 'h':
                if ( name.equals("hash_field1") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'hash_field1' must be String: '"+value+"' is not." );
                    this.hash_field1 = (String) value;
                    if (this.hash_field1_is_set)
                        this.hash_field1_is_modified = true;
                    this.hash_field1_is_set = true;
                    return;
                }
                else if ( name.equals("hash_field2") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'hash_field2' must be String: '"+value+"' is not." );
                    this.hash_field2 = (String) value;
                    if (this.hash_field2_is_set)
                        this.hash_field2_is_modified = true;
                    this.hash_field2_is_set = true;
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
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("soap_url") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'soap_url' must be String: '"+value+"' is not." );
                    this.soap_url = (String) value;
                    if (this.soap_url_is_set)
                        this.soap_url_is_modified = true;
                    this.soap_url_is_set = true;
                    return;
                }
                else if ( name.equals("system_failure_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'system_failure_flag' must be String: '"+value+"' is not." );
                    this.system_failure_flag = (String) value;
                    if (this.system_failure_flag_is_set)
                        this.system_failure_flag_is_modified = true;
                    this.system_failure_flag_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("url") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'url' must be String: '"+value+"' is not." );
                    this.url = (String) value;
                    if (this.url_is_set)
                        this.url_is_modified = true;
                    this.url_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
