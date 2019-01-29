head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.17.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommissionCourseRegistParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * commission_course_registテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link CommissionCourseRegistRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link CommissionCourseRegistParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link CommissionCourseRegistParams}が{@@link CommissionCourseRegistRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CommissionCourseRegistPK 
 * @@see CommissionCourseRegistRow 
 */
public class CommissionCourseRegistParams extends Params implements CommissionCourseRegistRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "commission_course_regist";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = CommissionCourseRegistRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return CommissionCourseRegistRow.TYPE;
  }


  /** 
   * <em>commission_course_regist_id</em>カラムの値 
   */
  public  long  commission_course_regist_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>comm_product_code</em>カラムの値 
   */
  public  String  comm_product_code;

  /** 
   * <em>appli_start_datetime</em>カラムの値 
   */
  public  java.sql.Timestamp  appli_start_datetime;

  /** 
   * <em>appli_end_datetime</em>カラムの値 
   */
  public  java.sql.Timestamp  appli_end_datetime;

  /** 
   * <em>commission_course_div</em>カラムの値 
   */
  public  String  commission_course_div;

  /** 
   * <em>regist_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  regist_timestamp;

  /** 
   * <em>regist_end_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  regist_end_timestamp;

  /** 
   * <em>download_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  download_flag;

  /** 
   * <em>delete_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  delete_flag;

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

  private boolean commission_course_regist_id_is_set = false;
  private boolean commission_course_regist_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean comm_product_code_is_set = false;
  private boolean comm_product_code_is_modified = false;
  private boolean appli_start_datetime_is_set = false;
  private boolean appli_start_datetime_is_modified = false;
  private boolean appli_end_datetime_is_set = false;
  private boolean appli_end_datetime_is_modified = false;
  private boolean commission_course_div_is_set = false;
  private boolean commission_course_div_is_modified = false;
  private boolean regist_timestamp_is_set = false;
  private boolean regist_timestamp_is_modified = false;
  private boolean regist_end_timestamp_is_set = false;
  private boolean regist_end_timestamp_is_modified = false;
  private boolean download_flag_is_set = false;
  private boolean download_flag_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
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
       + "commission_course_regist_id=" + commission_course_regist_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_id=" +branch_id
      + "," + "account_id=" +account_id
      + "," + "comm_product_code=" +comm_product_code
      + "," + "appli_start_datetime=" +appli_start_datetime
      + "," + "appli_end_datetime=" +appli_end_datetime
      + "," + "commission_course_div=" +commission_course_div
      + "," + "regist_timestamp=" +regist_timestamp
      + "," + "regist_end_timestamp=" +regist_end_timestamp
      + "," + "download_flag=" +download_flag
      + "," + "delete_flag=" +delete_flag
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のCommissionCourseRegistParamsオブジェクトを作成します。 
   */
  public CommissionCourseRegistParams() {
    commission_course_regist_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のCommissionCourseRegistRowオブジェクトの値を利用してCommissionCourseRegistParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するCommissionCourseRegistRowオブジェクト 
   */
  public CommissionCourseRegistParams( CommissionCourseRegistRow p_row )
  {
    commission_course_regist_id = p_row.getCommissionCourseRegistId();
    commission_course_regist_id_is_set = p_row.getCommissionCourseRegistIdIsSet();
    commission_course_regist_id_is_modified = p_row.getCommissionCourseRegistIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    comm_product_code = p_row.getCommProductCode();
    comm_product_code_is_set = p_row.getCommProductCodeIsSet();
    comm_product_code_is_modified = p_row.getCommProductCodeIsModified();
    appli_start_datetime = p_row.getAppliStartDatetime();
    appli_start_datetime_is_set = p_row.getAppliStartDatetimeIsSet();
    appli_start_datetime_is_modified = p_row.getAppliStartDatetimeIsModified();
    appli_end_datetime = p_row.getAppliEndDatetime();
    appli_end_datetime_is_set = p_row.getAppliEndDatetimeIsSet();
    appli_end_datetime_is_modified = p_row.getAppliEndDatetimeIsModified();
    commission_course_div = p_row.getCommissionCourseDiv();
    commission_course_div_is_set = p_row.getCommissionCourseDivIsSet();
    commission_course_div_is_modified = p_row.getCommissionCourseDivIsModified();
    regist_timestamp = p_row.getRegistTimestamp();
    regist_timestamp_is_set = p_row.getRegistTimestampIsSet();
    regist_timestamp_is_modified = p_row.getRegistTimestampIsModified();
    regist_end_timestamp = p_row.getRegistEndTimestamp();
    regist_end_timestamp_is_set = p_row.getRegistEndTimestampIsSet();
    regist_end_timestamp_is_modified = p_row.getRegistEndTimestampIsModified();
    download_flag = p_row.getDownloadFlag();
    download_flag_is_set = p_row.getDownloadFlagIsSet();
    download_flag_is_modified = p_row.getDownloadFlagIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
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
      branch_id_is_set = true;
      branch_id_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      comm_product_code_is_set = true;
      comm_product_code_is_modified = true;
      appli_start_datetime_is_set = true;
      appli_start_datetime_is_modified = true;
      appli_end_datetime_is_set = true;
      appli_end_datetime_is_modified = true;
      commission_course_div_is_set = true;
      commission_course_div_is_modified = true;
      regist_timestamp_is_set = true;
      regist_timestamp_is_modified = true;
      regist_end_timestamp_is_set = true;
      regist_end_timestamp_is_modified = true;
      download_flag_is_set = true;
      download_flag_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
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
    if ( !( other instanceof CommissionCourseRegistRow ) )
       return false;
    return fieldsEqual( (CommissionCourseRegistRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のCommissionCourseRegistRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( CommissionCourseRegistRow row )
  {
    if ( commission_course_regist_id != row.getCommissionCourseRegistId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_id != row.getBranchId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( comm_product_code == null ) {
      if ( row.getCommProductCode() != null )
        return false;
    } else if ( !comm_product_code.equals( row.getCommProductCode() ) ) {
        return false;
    }
    if ( appli_start_datetime == null ) {
      if ( row.getAppliStartDatetime() != null )
        return false;
    } else if ( !appli_start_datetime.equals( row.getAppliStartDatetime() ) ) {
        return false;
    }
    if ( appli_end_datetime == null ) {
      if ( row.getAppliEndDatetime() != null )
        return false;
    } else if ( !appli_end_datetime.equals( row.getAppliEndDatetime() ) ) {
        return false;
    }
    if ( commission_course_div == null ) {
      if ( row.getCommissionCourseDiv() != null )
        return false;
    } else if ( !commission_course_div.equals( row.getCommissionCourseDiv() ) ) {
        return false;
    }
    if ( regist_timestamp == null ) {
      if ( row.getRegistTimestamp() != null )
        return false;
    } else if ( !regist_timestamp.equals( row.getRegistTimestamp() ) ) {
        return false;
    }
    if ( regist_end_timestamp == null ) {
      if ( row.getRegistEndTimestamp() != null )
        return false;
    } else if ( !regist_end_timestamp.equals( row.getRegistEndTimestamp() ) ) {
        return false;
    }
    if ( download_flag == null ) {
      if ( row.getDownloadFlag() != null )
        return false;
    } else if ( !download_flag.equals( row.getDownloadFlag() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
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
      return  ((int) commission_course_regist_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) branch_id)
        + ((int) account_id)
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (appli_start_datetime!=null? appli_start_datetime.hashCode(): 0) 
        + (appli_end_datetime!=null? appli_end_datetime.hashCode(): 0) 
        + (commission_course_div!=null? commission_course_div.hashCode(): 0) 
        + (regist_timestamp!=null? regist_timestamp.hashCode(): 0) 
        + (regist_end_timestamp!=null? regist_end_timestamp.hashCode(): 0) 
        + (download_flag!=null? download_flag.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
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
		if ( !branch_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_id' must be set before inserting.");
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !comm_product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'comm_product_code' must be set before inserting.");
		if ( !appli_start_datetime_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_start_datetime' must be set before inserting.");
		if ( !appli_end_datetime_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_end_datetime' must be set before inserting.");
		if ( !commission_course_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'commission_course_div' must be set before inserting.");
		if ( !regist_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'regist_timestamp' must be set before inserting.");
		if ( !regist_end_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'regist_end_timestamp' must be set before inserting.");
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
		map.put("commission_course_regist_id",new Long(commission_course_regist_id));
		map.put("institution_code",institution_code);
		map.put("branch_id",new Long(branch_id));
		map.put("account_id",new Long(account_id));
		map.put("comm_product_code",comm_product_code);
		map.put("appli_start_datetime",appli_start_datetime);
		map.put("appli_end_datetime",appli_end_datetime);
		map.put("commission_course_div",commission_course_div);
		map.put("regist_timestamp",regist_timestamp);
		map.put("regist_end_timestamp",regist_end_timestamp);
		if ( download_flag_is_set )
			map.put("download_flag",download_flag);
		if ( delete_flag_is_set )
			map.put("delete_flag",delete_flag);
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
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( comm_product_code_is_modified )
			map.put("comm_product_code",comm_product_code);
		if ( appli_start_datetime_is_modified )
			map.put("appli_start_datetime",appli_start_datetime);
		if ( appli_end_datetime_is_modified )
			map.put("appli_end_datetime",appli_end_datetime);
		if ( commission_course_div_is_modified )
			map.put("commission_course_div",commission_course_div);
		if ( regist_timestamp_is_modified )
			map.put("regist_timestamp",regist_timestamp);
		if ( regist_end_timestamp_is_modified )
			map.put("regist_end_timestamp",regist_end_timestamp);
		if ( download_flag_is_modified )
			map.put("download_flag",download_flag);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( comm_product_code_is_set )
				map.put("comm_product_code",comm_product_code);
			if ( appli_start_datetime_is_set )
				map.put("appli_start_datetime",appli_start_datetime);
			if ( appli_end_datetime_is_set )
				map.put("appli_end_datetime",appli_end_datetime);
			if ( commission_course_div_is_set )
				map.put("commission_course_div",commission_course_div);
			if ( regist_timestamp_is_set )
				map.put("regist_timestamp",regist_timestamp);
			if ( regist_end_timestamp_is_set )
				map.put("regist_end_timestamp",regist_end_timestamp);
			if ( download_flag_is_set )
				map.put("download_flag",download_flag);
			if ( delete_flag_is_set )
				map.put("delete_flag",delete_flag);
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
   * <em>commission_course_regist_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getCommissionCourseRegistId()
  {
    return commission_course_regist_id;
  }


  /** 
   * <em>commission_course_regist_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionCourseRegistIdIsSet() {
    return commission_course_regist_id_is_set;
  }


  /** 
   * <em>commission_course_regist_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionCourseRegistIdIsModified() {
    return commission_course_regist_id_is_modified;
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
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
  }


  /** 
   * <em>comm_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommProductCode()
  {
    return comm_product_code;
  }


  /** 
   * <em>comm_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommProductCodeIsSet() {
    return comm_product_code_is_set;
  }


  /** 
   * <em>comm_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommProductCodeIsModified() {
    return comm_product_code_is_modified;
  }


  /** 
   * <em>appli_start_datetime</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAppliStartDatetime()
  {
    return appli_start_datetime;
  }


  /** 
   * <em>appli_start_datetime</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliStartDatetimeIsSet() {
    return appli_start_datetime_is_set;
  }


  /** 
   * <em>appli_start_datetime</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliStartDatetimeIsModified() {
    return appli_start_datetime_is_modified;
  }


  /** 
   * <em>appli_end_datetime</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAppliEndDatetime()
  {
    return appli_end_datetime;
  }


  /** 
   * <em>appli_end_datetime</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliEndDatetimeIsSet() {
    return appli_end_datetime_is_set;
  }


  /** 
   * <em>appli_end_datetime</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliEndDatetimeIsModified() {
    return appli_end_datetime_is_modified;
  }


  /** 
   * <em>commission_course_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommissionCourseDiv()
  {
    return commission_course_div;
  }


  /** 
   * <em>commission_course_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionCourseDivIsSet() {
    return commission_course_div_is_set;
  }


  /** 
   * <em>commission_course_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionCourseDivIsModified() {
    return commission_course_div_is_modified;
  }


  /** 
   * <em>regist_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getRegistTimestamp()
  {
    return regist_timestamp;
  }


  /** 
   * <em>regist_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistTimestampIsSet() {
    return regist_timestamp_is_set;
  }


  /** 
   * <em>regist_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistTimestampIsModified() {
    return regist_timestamp_is_modified;
  }


  /** 
   * <em>regist_end_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getRegistEndTimestamp()
  {
    return regist_end_timestamp;
  }


  /** 
   * <em>regist_end_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistEndTimestampIsSet() {
    return regist_end_timestamp_is_set;
  }


  /** 
   * <em>regist_end_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistEndTimestampIsModified() {
    return regist_end_timestamp_is_modified;
  }


  /** 
   * <em>download_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDownloadFlag()
  {
    return download_flag;
  }


  /** 
   * <em>download_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDownloadFlagIsSet() {
    return download_flag_is_set;
  }


  /** 
   * <em>download_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDownloadFlagIsModified() {
    return download_flag_is_modified;
  }


  /** 
   * <em>delete_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDeleteFlag()
  {
    return delete_flag;
  }


  /** 
   * <em>delete_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsSet() {
    return delete_flag_is_set;
  }


  /** 
   * <em>delete_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsModified() {
    return delete_flag_is_modified;
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
    return new CommissionCourseRegistPK(commission_course_regist_id);
  }


  /** 
   * <em>commission_course_regist_id</em>カラムの値を設定します。 
   *
   * @@param p_commissionCourseRegistId <em>commission_course_regist_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setCommissionCourseRegistId( long p_commissionCourseRegistId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_course_regist_id = p_commissionCourseRegistId;
    commission_course_regist_id_is_set = true;
    commission_course_regist_id_is_modified = true;
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
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>account_id</em>カラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>comm_product_code</em>カラムの値を設定します。 
   *
   * @@param p_commProductCode <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCommProductCode( String p_commProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comm_product_code = p_commProductCode;
    comm_product_code_is_set = true;
    comm_product_code_is_modified = true;
  }


  /** 
   * <em>appli_start_datetime</em>カラムの値を設定します。 
   *
   * @@param p_appliStartDatetime <em>appli_start_datetime</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAppliStartDatetime( java.sql.Timestamp p_appliStartDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_datetime = p_appliStartDatetime;
    appli_start_datetime_is_set = true;
    appli_start_datetime_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>appli_start_datetime</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAppliStartDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_start_datetime_is_set = true;
    appli_start_datetime_is_modified = true;
  }


  /** 
   * <em>appli_end_datetime</em>カラムの値を設定します。 
   *
   * @@param p_appliEndDatetime <em>appli_end_datetime</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAppliEndDatetime( java.sql.Timestamp p_appliEndDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_datetime = p_appliEndDatetime;
    appli_end_datetime_is_set = true;
    appli_end_datetime_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>appli_end_datetime</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAppliEndDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_end_datetime_is_set = true;
    appli_end_datetime_is_modified = true;
  }


  /** 
   * <em>commission_course_div</em>カラムの値を設定します。 
   *
   * @@param p_commissionCourseDiv <em>commission_course_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCommissionCourseDiv( String p_commissionCourseDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_course_div = p_commissionCourseDiv;
    commission_course_div_is_set = true;
    commission_course_div_is_modified = true;
  }


  /** 
   * <em>regist_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_registTimestamp <em>regist_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setRegistTimestamp( java.sql.Timestamp p_registTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_timestamp = p_registTimestamp;
    regist_timestamp_is_set = true;
    regist_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>regist_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setRegistTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    regist_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    regist_timestamp_is_set = true;
    regist_timestamp_is_modified = true;
  }


  /** 
   * <em>regist_end_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_registEndTimestamp <em>regist_end_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setRegistEndTimestamp( java.sql.Timestamp p_registEndTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_end_timestamp = p_registEndTimestamp;
    regist_end_timestamp_is_set = true;
    regist_end_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>regist_end_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setRegistEndTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    regist_end_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    regist_end_timestamp_is_set = true;
    regist_end_timestamp_is_modified = true;
  }


  /** 
   * <em>download_flag</em>カラムの値を設定します。 
   *
   * @@param p_downloadFlag <em>download_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setDownloadFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_downloadFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    download_flag = p_downloadFlag;
    download_flag_is_set = true;
    download_flag_is_modified = true;
  }


  /** 
   * <em>delete_flag</em>カラムの値を設定します。 
   *
   * @@param p_deleteFlag <em>delete_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setDeleteFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = p_deleteFlag;
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
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
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("appli_start_datetime") ) {
                    return this.appli_start_datetime;
                }
                else if ( name.equals("appli_end_datetime") ) {
                    return this.appli_end_datetime;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                break;
            case 'c':
                if ( name.equals("commission_course_regist_id") ) {
                    return new Long( commission_course_regist_id );
                }
                else if ( name.equals("comm_product_code") ) {
                    return this.comm_product_code;
                }
                else if ( name.equals("commission_course_div") ) {
                    return this.commission_course_div;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("download_flag") ) {
                    return this.download_flag;
                }
                else if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
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
            case 'r':
                if ( name.equals("regist_timestamp") ) {
                    return this.regist_timestamp;
                }
                else if ( name.equals("regist_end_timestamp") ) {
                    return this.regist_end_timestamp;
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
                if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("appli_start_datetime") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_start_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_start_datetime = (java.sql.Timestamp) value;
                    if (this.appli_start_datetime_is_set)
                        this.appli_start_datetime_is_modified = true;
                    this.appli_start_datetime_is_set = true;
                    return;
                }
                else if ( name.equals("appli_end_datetime") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_end_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_end_datetime = (java.sql.Timestamp) value;
                    if (this.appli_end_datetime_is_set)
                        this.appli_end_datetime_is_modified = true;
                    this.appli_end_datetime_is_set = true;
                    return;
                }
                break;
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
                if ( name.equals("commission_course_regist_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'commission_course_regist_id' must be Long: '"+value+"' is not." );
                    this.commission_course_regist_id = ((Long) value).longValue();
                    if (this.commission_course_regist_id_is_set)
                        this.commission_course_regist_id_is_modified = true;
                    this.commission_course_regist_id_is_set = true;
                    return;
                }
                else if ( name.equals("comm_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comm_product_code' must be String: '"+value+"' is not." );
                    this.comm_product_code = (String) value;
                    if (this.comm_product_code_is_set)
                        this.comm_product_code_is_modified = true;
                    this.comm_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("commission_course_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commission_course_div' must be String: '"+value+"' is not." );
                    this.commission_course_div = (String) value;
                    if (this.commission_course_div_is_set)
                        this.commission_course_div_is_modified = true;
                    this.commission_course_div_is_set = true;
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
            case 'd':
                if ( name.equals("download_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'download_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.download_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.download_flag_is_set)
                        this.download_flag_is_modified = true;
                    this.download_flag_is_set = true;
                    return;
                }
                else if ( name.equals("delete_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.delete_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
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
            case 'r':
                if ( name.equals("regist_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'regist_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.regist_timestamp = (java.sql.Timestamp) value;
                    if (this.regist_timestamp_is_set)
                        this.regist_timestamp_is_modified = true;
                    this.regist_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("regist_end_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'regist_end_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.regist_end_timestamp = (java.sql.Timestamp) value;
                    if (this.regist_end_timestamp_is_set)
                        this.regist_end_timestamp_is_modified = true;
                    this.regist_end_timestamp_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
