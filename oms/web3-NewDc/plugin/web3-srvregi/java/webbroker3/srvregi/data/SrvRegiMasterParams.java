head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiMasterParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * srv_regi_masterテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SrvRegiMasterRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SrvRegiMasterParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SrvRegiMasterParams}が{@@link SrvRegiMasterRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SrvRegiMasterPK 
 * @@see SrvRegiMasterRow 
 */
public class SrvRegiMasterParams extends Params implements SrvRegiMasterRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "srv_regi_master";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SrvRegiMasterRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SrvRegiMasterRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>srv_div</em>カラムの値 
   */
  public  String  srv_div;

  /** 
   * <em>srv_name</em>カラムの値 
   */
  public  String  srv_name;

  /** 
   * <em>srv_status</em>カラムの値 
   */
  public  String  srv_status;

  /** 
   * <em>offering_div</em>カラムの値 
   */
  public  String  offering_div;

  /** 
   * <em>srv_url</em>カラムの値 
   */
  public  String  srv_url;

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

  /** 
   * <em>special_process_div</em>カラムの値 
   */
  public  String  special_process_div;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean srv_div_is_set = false;
  private boolean srv_div_is_modified = false;
  private boolean srv_name_is_set = false;
  private boolean srv_name_is_modified = false;
  private boolean srv_status_is_set = false;
  private boolean srv_status_is_modified = false;
  private boolean offering_div_is_set = false;
  private boolean offering_div_is_modified = false;
  private boolean srv_url_is_set = false;
  private boolean srv_url_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean special_process_div_is_set = false;
  private boolean special_process_div_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "srv_div=" + srv_div
      + "," + "srv_name=" +srv_name
      + "," + "srv_status=" +srv_status
      + "," + "offering_div=" +offering_div
      + "," + "srv_url=" +srv_url
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "special_process_div=" +special_process_div
      + "}";
  }


  /** 
   * 値が未設定のSrvRegiMasterParamsオブジェクトを作成します。 
   */
  public SrvRegiMasterParams() {
    institution_code_is_modified = true;
    srv_div_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSrvRegiMasterRowオブジェクトの値を利用してSrvRegiMasterParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSrvRegiMasterRowオブジェクト 
   */
  public SrvRegiMasterParams( SrvRegiMasterRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    srv_div = p_row.getSrvDiv();
    srv_div_is_set = p_row.getSrvDivIsSet();
    srv_div_is_modified = p_row.getSrvDivIsModified();
    srv_name = p_row.getSrvName();
    srv_name_is_set = p_row.getSrvNameIsSet();
    srv_name_is_modified = p_row.getSrvNameIsModified();
    srv_status = p_row.getSrvStatus();
    srv_status_is_set = p_row.getSrvStatusIsSet();
    srv_status_is_modified = p_row.getSrvStatusIsModified();
    offering_div = p_row.getOfferingDiv();
    offering_div_is_set = p_row.getOfferingDivIsSet();
    offering_div_is_modified = p_row.getOfferingDivIsModified();
    srv_url = p_row.getSrvUrl();
    srv_url_is_set = p_row.getSrvUrlIsSet();
    srv_url_is_modified = p_row.getSrvUrlIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    special_process_div = p_row.getSpecialProcessDiv();
    special_process_div_is_set = p_row.getSpecialProcessDivIsSet();
    special_process_div_is_modified = p_row.getSpecialProcessDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      srv_name_is_set = true;
      srv_name_is_modified = true;
      srv_status_is_set = true;
      srv_status_is_modified = true;
      offering_div_is_set = true;
      offering_div_is_modified = true;
      srv_url_is_set = true;
      srv_url_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      special_process_div_is_set = true;
      special_process_div_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof SrvRegiMasterRow ) )
       return false;
    return fieldsEqual( (SrvRegiMasterRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSrvRegiMasterRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SrvRegiMasterRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( srv_div == null ) {
      if ( row.getSrvDiv() != null )
        return false;
    } else if ( !srv_div.equals( row.getSrvDiv() ) ) {
        return false;
    }
    if ( srv_name == null ) {
      if ( row.getSrvName() != null )
        return false;
    } else if ( !srv_name.equals( row.getSrvName() ) ) {
        return false;
    }
    if ( srv_status == null ) {
      if ( row.getSrvStatus() != null )
        return false;
    } else if ( !srv_status.equals( row.getSrvStatus() ) ) {
        return false;
    }
    if ( offering_div == null ) {
      if ( row.getOfferingDiv() != null )
        return false;
    } else if ( !offering_div.equals( row.getOfferingDiv() ) ) {
        return false;
    }
    if ( srv_url == null ) {
      if ( row.getSrvUrl() != null )
        return false;
    } else if ( !srv_url.equals( row.getSrvUrl() ) ) {
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
    if ( special_process_div == null ) {
      if ( row.getSpecialProcessDiv() != null )
        return false;
    } else if ( !special_process_div.equals( row.getSpecialProcessDiv() ) ) {
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
        + (srv_div!=null? srv_div.hashCode(): 0) 
        + (srv_name!=null? srv_name.hashCode(): 0) 
        + (srv_status!=null? srv_status.hashCode(): 0) 
        + (offering_div!=null? offering_div.hashCode(): 0) 
        + (srv_url!=null? srv_url.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (special_process_div!=null? special_process_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !srv_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'srv_name' must be set before inserting.");
		if ( !srv_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'srv_status' must be set before inserting.");
		if ( !offering_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'offering_div' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
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
		map.put("srv_div",srv_div);
		map.put("srv_name",srv_name);
		map.put("srv_status",srv_status);
		map.put("offering_div",offering_div);
		if ( srv_url != null )
			map.put("srv_url",srv_url);
		map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( special_process_div != null )
			map.put("special_process_div",special_process_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( srv_name_is_modified )
			map.put("srv_name",srv_name);
		if ( srv_status_is_modified )
			map.put("srv_status",srv_status);
		if ( offering_div_is_modified )
			map.put("offering_div",offering_div);
		if ( srv_url_is_modified )
			map.put("srv_url",srv_url);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( special_process_div_is_modified )
			map.put("special_process_div",special_process_div);
		if (map.size() == 0) {
			if ( srv_name_is_set )
				map.put("srv_name",srv_name);
			if ( srv_status_is_set )
				map.put("srv_status",srv_status);
			if ( offering_div_is_set )
				map.put("offering_div",offering_div);
			map.put("srv_url",srv_url);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("special_process_div",special_process_div);
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
   * <em>srv_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSrvDiv()
  {
    return srv_div;
  }


  /** 
   * <em>srv_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvDivIsSet() {
    return srv_div_is_set;
  }


  /** 
   * <em>srv_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvDivIsModified() {
    return srv_div_is_modified;
  }


  /** 
   * <em>srv_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSrvName()
  {
    return srv_name;
  }


  /** 
   * <em>srv_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvNameIsSet() {
    return srv_name_is_set;
  }


  /** 
   * <em>srv_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvNameIsModified() {
    return srv_name_is_modified;
  }


  /** 
   * <em>srv_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSrvStatus()
  {
    return srv_status;
  }


  /** 
   * <em>srv_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvStatusIsSet() {
    return srv_status_is_set;
  }


  /** 
   * <em>srv_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvStatusIsModified() {
    return srv_status_is_modified;
  }


  /** 
   * <em>offering_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOfferingDiv()
  {
    return offering_div;
  }


  /** 
   * <em>offering_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfferingDivIsSet() {
    return offering_div_is_set;
  }


  /** 
   * <em>offering_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfferingDivIsModified() {
    return offering_div_is_modified;
  }


  /** 
   * <em>srv_url</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSrvUrl()
  {
    return srv_url;
  }


  /** 
   * <em>srv_url</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvUrlIsSet() {
    return srv_url_is_set;
  }


  /** 
   * <em>srv_url</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvUrlIsModified() {
    return srv_url_is_modified;
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
   * <em>special_process_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSpecialProcessDiv()
  {
    return special_process_div;
  }


  /** 
   * <em>special_process_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialProcessDivIsSet() {
    return special_process_div_is_set;
  }


  /** 
   * <em>special_process_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialProcessDivIsModified() {
    return special_process_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new SrvRegiMasterPK(institution_code, srv_div);
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
   * <em>srv_div</em>カラムの値を設定します。 
   *
   * @@param p_srvDiv <em>srv_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setSrvDiv( String p_srvDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_div = p_srvDiv;
    srv_div_is_set = true;
    srv_div_is_modified = true;
  }


  /** 
   * <em>srv_name</em>カラムの値を設定します。 
   *
   * @@param p_srvName <em>srv_name</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setSrvName( String p_srvName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_name = p_srvName;
    srv_name_is_set = true;
    srv_name_is_modified = true;
  }


  /** 
   * <em>srv_status</em>カラムの値を設定します。 
   *
   * @@param p_srvStatus <em>srv_status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSrvStatus( String p_srvStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_status = p_srvStatus;
    srv_status_is_set = true;
    srv_status_is_modified = true;
  }


  /** 
   * <em>offering_div</em>カラムの値を設定します。 
   *
   * @@param p_offeringDiv <em>offering_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOfferingDiv( String p_offeringDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    offering_div = p_offeringDiv;
    offering_div_is_set = true;
    offering_div_is_modified = true;
  }


  /** 
   * <em>srv_url</em>カラムの値を設定します。 
   *
   * @@param p_srvUrl <em>srv_url</em>カラムの値をあらわす256桁以下のString値 
   */
  public final void setSrvUrl( String p_srvUrl )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_url = p_srvUrl;
    srv_url_is_set = true;
    srv_url_is_modified = true;
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
   * <em>special_process_div</em>カラムの値を設定します。 
   *
   * @@param p_specialProcessDiv <em>special_process_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSpecialProcessDiv( String p_specialProcessDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_process_div = p_specialProcessDiv;
    special_process_div_is_set = true;
    special_process_div_is_modified = true;
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
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("offering_div") ) {
                    return this.offering_div;
                }
                break;
            case 's':
                if ( name.equals("srv_div") ) {
                    return this.srv_div;
                }
                else if ( name.equals("srv_name") ) {
                    return this.srv_name;
                }
                else if ( name.equals("srv_status") ) {
                    return this.srv_status;
                }
                else if ( name.equals("srv_url") ) {
                    return this.srv_url;
                }
                else if ( name.equals("special_process_div") ) {
                    return this.special_process_div;
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
            case 'o':
                if ( name.equals("offering_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'offering_div' must be String: '"+value+"' is not." );
                    this.offering_div = (String) value;
                    if (this.offering_div_is_set)
                        this.offering_div_is_modified = true;
                    this.offering_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("srv_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'srv_div' must be String: '"+value+"' is not." );
                    this.srv_div = (String) value;
                    if (this.srv_div_is_set)
                        this.srv_div_is_modified = true;
                    this.srv_div_is_set = true;
                    return;
                }
                else if ( name.equals("srv_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'srv_name' must be String: '"+value+"' is not." );
                    this.srv_name = (String) value;
                    if (this.srv_name_is_set)
                        this.srv_name_is_modified = true;
                    this.srv_name_is_set = true;
                    return;
                }
                else if ( name.equals("srv_status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'srv_status' must be String: '"+value+"' is not." );
                    this.srv_status = (String) value;
                    if (this.srv_status_is_set)
                        this.srv_status_is_modified = true;
                    this.srv_status_is_set = true;
                    return;
                }
                else if ( name.equals("srv_url") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'srv_url' must be String: '"+value+"' is not." );
                    this.srv_url = (String) value;
                    if (this.srv_url_is_set)
                        this.srv_url_is_modified = true;
                    this.srv_url_is_set = true;
                    return;
                }
                else if ( name.equals("special_process_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'special_process_div' must be String: '"+value+"' is not." );
                    this.special_process_div = (String) value;
                    if (this.special_process_div_is_set)
                        this.special_process_div_is_modified = true;
                    this.special_process_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
