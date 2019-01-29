head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.16.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	LoginRejectIpParams.java;


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
 * login_reject_ipテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link LoginRejectIpRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link LoginRejectIpParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link LoginRejectIpParams}が{@@link LoginRejectIpRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see LoginRejectIpPK 
 * @@see LoginRejectIpRow 
 */
public class LoginRejectIpParams extends Params implements LoginRejectIpRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "login_reject_ip";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = LoginRejectIpRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return LoginRejectIpRow.TYPE;
  }


  /** 
   * <em>login_reject_id</em>カラムの値 
   */
  public  long  login_reject_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>ip_address</em>カラムの値 
   */
  public  String  ip_address;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>appli_start_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  appli_start_timestamp;

  /** 
   * <em>appli_end_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  appli_end_timestamp;

  /** 
   * <em>regist_div</em>カラムの値 
   */
  public  String  regist_div;

  /** 
   * <em>updated_div</em>カラムの値 
   */
  public  String  updated_div;

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

  private boolean login_reject_id_is_set = false;
  private boolean login_reject_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean ip_address_is_set = false;
  private boolean ip_address_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean appli_start_timestamp_is_set = false;
  private boolean appli_start_timestamp_is_modified = false;
  private boolean appli_end_timestamp_is_set = false;
  private boolean appli_end_timestamp_is_modified = false;
  private boolean regist_div_is_set = false;
  private boolean regist_div_is_modified = false;
  private boolean updated_div_is_set = false;
  private boolean updated_div_is_modified = false;
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
       + "login_reject_id=" + login_reject_id
      + "," + "institution_code=" +institution_code
      + "," + "ip_address=" +ip_address
      + "," + "status=" +status
      + "," + "appli_start_timestamp=" +appli_start_timestamp
      + "," + "appli_end_timestamp=" +appli_end_timestamp
      + "," + "regist_div=" +regist_div
      + "," + "updated_div=" +updated_div
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のLoginRejectIpParamsオブジェクトを作成します。 
   */
  public LoginRejectIpParams() {
    login_reject_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のLoginRejectIpRowオブジェクトの値を利用してLoginRejectIpParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するLoginRejectIpRowオブジェクト 
   */
  public LoginRejectIpParams( LoginRejectIpRow p_row )
  {
    login_reject_id = p_row.getLoginRejectId();
    login_reject_id_is_set = p_row.getLoginRejectIdIsSet();
    login_reject_id_is_modified = p_row.getLoginRejectIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    ip_address = p_row.getIpAddress();
    ip_address_is_set = p_row.getIpAddressIsSet();
    ip_address_is_modified = p_row.getIpAddressIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    appli_start_timestamp = p_row.getAppliStartTimestamp();
    appli_start_timestamp_is_set = p_row.getAppliStartTimestampIsSet();
    appli_start_timestamp_is_modified = p_row.getAppliStartTimestampIsModified();
    appli_end_timestamp = p_row.getAppliEndTimestamp();
    appli_end_timestamp_is_set = p_row.getAppliEndTimestampIsSet();
    appli_end_timestamp_is_modified = p_row.getAppliEndTimestampIsModified();
    regist_div = p_row.getRegistDiv();
    regist_div_is_set = p_row.getRegistDivIsSet();
    regist_div_is_modified = p_row.getRegistDivIsModified();
    updated_div = p_row.getUpdatedDiv();
    updated_div_is_set = p_row.getUpdatedDivIsSet();
    updated_div_is_modified = p_row.getUpdatedDivIsModified();
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
      institution_code_is_set = true;
      institution_code_is_modified = true;
      ip_address_is_set = true;
      ip_address_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      appli_start_timestamp_is_set = true;
      appli_start_timestamp_is_modified = true;
      appli_end_timestamp_is_set = true;
      appli_end_timestamp_is_modified = true;
      regist_div_is_set = true;
      regist_div_is_modified = true;
      updated_div_is_set = true;
      updated_div_is_modified = true;
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
    if ( !( other instanceof LoginRejectIpRow ) )
       return false;
    return fieldsEqual( (LoginRejectIpRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のLoginRejectIpRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( LoginRejectIpRow row )
  {
    if ( login_reject_id != row.getLoginRejectId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( ip_address == null ) {
      if ( row.getIpAddress() != null )
        return false;
    } else if ( !ip_address.equals( row.getIpAddress() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( appli_start_timestamp == null ) {
      if ( row.getAppliStartTimestamp() != null )
        return false;
    } else if ( !appli_start_timestamp.equals( row.getAppliStartTimestamp() ) ) {
        return false;
    }
    if ( appli_end_timestamp == null ) {
      if ( row.getAppliEndTimestamp() != null )
        return false;
    } else if ( !appli_end_timestamp.equals( row.getAppliEndTimestamp() ) ) {
        return false;
    }
    if ( regist_div == null ) {
      if ( row.getRegistDiv() != null )
        return false;
    } else if ( !regist_div.equals( row.getRegistDiv() ) ) {
        return false;
    }
    if ( updated_div == null ) {
      if ( row.getUpdatedDiv() != null )
        return false;
    } else if ( !updated_div.equals( row.getUpdatedDiv() ) ) {
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
      return  ((int) login_reject_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (ip_address!=null? ip_address.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (appli_start_timestamp!=null? appli_start_timestamp.hashCode(): 0) 
        + (appli_end_timestamp!=null? appli_end_timestamp.hashCode(): 0) 
        + (regist_div!=null? regist_div.hashCode(): 0) 
        + (updated_div!=null? updated_div.hashCode(): 0) 
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
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !ip_address_is_set )
			throw new IllegalArgumentException("non-nullable field 'ip_address' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
		if ( !appli_start_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_start_timestamp' must be set before inserting.");
		if ( !appli_end_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_end_timestamp' must be set before inserting.");
		if ( !regist_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'regist_div' must be set before inserting.");
		if ( !updated_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'updated_div' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("login_reject_id",new Long(login_reject_id));
		map.put("institution_code",institution_code);
		map.put("ip_address",ip_address);
		map.put("status",status);
		map.put("appli_start_timestamp",appli_start_timestamp);
		map.put("appli_end_timestamp",appli_end_timestamp);
		map.put("regist_div",regist_div);
		map.put("updated_div",updated_div);
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
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( ip_address_is_modified )
			map.put("ip_address",ip_address);
		if ( status_is_modified )
			map.put("status",status);
		if ( appli_start_timestamp_is_modified )
			map.put("appli_start_timestamp",appli_start_timestamp);
		if ( appli_end_timestamp_is_modified )
			map.put("appli_end_timestamp",appli_end_timestamp);
		if ( regist_div_is_modified )
			map.put("regist_div",regist_div);
		if ( updated_div_is_modified )
			map.put("updated_div",updated_div);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( ip_address_is_set )
				map.put("ip_address",ip_address);
			if ( status_is_set )
				map.put("status",status);
			if ( appli_start_timestamp_is_set )
				map.put("appli_start_timestamp",appli_start_timestamp);
			if ( appli_end_timestamp_is_set )
				map.put("appli_end_timestamp",appli_end_timestamp);
			if ( regist_div_is_set )
				map.put("regist_div",regist_div);
			if ( updated_div_is_set )
				map.put("updated_div",updated_div);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>login_reject_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getLoginRejectId()
  {
    return login_reject_id;
  }


  /** 
   * <em>login_reject_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginRejectIdIsSet() {
    return login_reject_id_is_set;
  }


  /** 
   * <em>login_reject_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginRejectIdIsModified() {
    return login_reject_id_is_modified;
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
   * <em>ip_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIpAddress()
  {
    return ip_address;
  }


  /** 
   * <em>ip_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIpAddressIsSet() {
    return ip_address_is_set;
  }


  /** 
   * <em>ip_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIpAddressIsModified() {
    return ip_address_is_modified;
  }


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsModified() {
    return status_is_modified;
  }


  /** 
   * <em>appli_start_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAppliStartTimestamp()
  {
    return appli_start_timestamp;
  }


  /** 
   * <em>appli_start_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliStartTimestampIsSet() {
    return appli_start_timestamp_is_set;
  }


  /** 
   * <em>appli_start_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliStartTimestampIsModified() {
    return appli_start_timestamp_is_modified;
  }


  /** 
   * <em>appli_end_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAppliEndTimestamp()
  {
    return appli_end_timestamp;
  }


  /** 
   * <em>appli_end_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliEndTimestampIsSet() {
    return appli_end_timestamp_is_set;
  }


  /** 
   * <em>appli_end_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliEndTimestampIsModified() {
    return appli_end_timestamp_is_modified;
  }


  /** 
   * <em>regist_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRegistDiv()
  {
    return regist_div;
  }


  /** 
   * <em>regist_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistDivIsSet() {
    return regist_div_is_set;
  }


  /** 
   * <em>regist_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistDivIsModified() {
    return regist_div_is_modified;
  }


  /** 
   * <em>updated_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getUpdatedDiv()
  {
    return updated_div;
  }


  /** 
   * <em>updated_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUpdatedDivIsSet() {
    return updated_div_is_set;
  }


  /** 
   * <em>updated_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUpdatedDivIsModified() {
    return updated_div_is_modified;
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
    return new LoginRejectIpPK(login_reject_id);
  }


  /** 
   * <em>login_reject_id</em>カラムの値を設定します。 
   *
   * @@param p_loginRejectId <em>login_reject_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setLoginRejectId( long p_loginRejectId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    login_reject_id = p_loginRejectId;
    login_reject_id_is_set = true;
    login_reject_id_is_modified = true;
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
   * <em>ip_address</em>カラムの値を設定します。 
   *
   * @@param p_ipAddress <em>ip_address</em>カラムの値をあらわす15桁以下のString値 
   */
  public final void setIpAddress( String p_ipAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ip_address = p_ipAddress;
    ip_address_is_set = true;
    ip_address_is_modified = true;
  }


  /** 
   * <em>status</em>カラムの値を設定します。 
   *
   * @@param p_status <em>status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStatus( String p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
  }


  /** 
   * <em>appli_start_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_appliStartTimestamp <em>appli_start_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAppliStartTimestamp( java.sql.Timestamp p_appliStartTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_timestamp = p_appliStartTimestamp;
    appli_start_timestamp_is_set = true;
    appli_start_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>appli_start_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAppliStartTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_start_timestamp_is_set = true;
    appli_start_timestamp_is_modified = true;
  }


  /** 
   * <em>appli_end_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_appliEndTimestamp <em>appli_end_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAppliEndTimestamp( java.sql.Timestamp p_appliEndTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_timestamp = p_appliEndTimestamp;
    appli_end_timestamp_is_set = true;
    appli_end_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>appli_end_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAppliEndTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_end_timestamp_is_set = true;
    appli_end_timestamp_is_modified = true;
  }


  /** 
   * <em>regist_div</em>カラムの値を設定します。 
   *
   * @@param p_registDiv <em>regist_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRegistDiv( String p_registDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_div = p_registDiv;
    regist_div_is_set = true;
    regist_div_is_modified = true;
  }


  /** 
   * <em>updated_div</em>カラムの値を設定します。 
   *
   * @@param p_updatedDiv <em>updated_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setUpdatedDiv( String p_updatedDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    updated_div = p_updatedDiv;
    updated_div_is_set = true;
    updated_div_is_modified = true;
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
                if ( name.equals("appli_start_timestamp") ) {
                    return this.appli_start_timestamp;
                }
                else if ( name.equals("appli_end_timestamp") ) {
                    return this.appli_end_timestamp;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("ip_address") ) {
                    return this.ip_address;
                }
                break;
            case 'l':
                if ( name.equals("login_reject_id") ) {
                    return new Long( login_reject_id );
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'r':
                if ( name.equals("regist_div") ) {
                    return this.regist_div;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 'u':
                if ( name.equals("updated_div") ) {
                    return this.updated_div;
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
                if ( name.equals("appli_start_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_start_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_start_timestamp = (java.sql.Timestamp) value;
                    if (this.appli_start_timestamp_is_set)
                        this.appli_start_timestamp_is_modified = true;
                    this.appli_start_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("appli_end_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_end_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_end_timestamp = (java.sql.Timestamp) value;
                    if (this.appli_end_timestamp_is_set)
                        this.appli_end_timestamp_is_modified = true;
                    this.appli_end_timestamp_is_set = true;
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
                else if ( name.equals("ip_address") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ip_address' must be String: '"+value+"' is not." );
                    this.ip_address = (String) value;
                    if (this.ip_address_is_set)
                        this.ip_address_is_modified = true;
                    this.ip_address_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("login_reject_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'login_reject_id' must be Long: '"+value+"' is not." );
                    this.login_reject_id = ((Long) value).longValue();
                    if (this.login_reject_id_is_set)
                        this.login_reject_id_is_modified = true;
                    this.login_reject_id_is_set = true;
                    return;
                }
                else if ( name.equals("last_updater") ) {
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
            case 'r':
                if ( name.equals("regist_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'regist_div' must be String: '"+value+"' is not." );
                    this.regist_div = (String) value;
                    if (this.regist_div_is_set)
                        this.regist_div_is_modified = true;
                    this.regist_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("updated_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'updated_div' must be String: '"+value+"' is not." );
                    this.updated_div = (String) value;
                    if (this.updated_div_is_set)
                        this.updated_div_is_modified = true;
                    this.updated_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
