head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.15.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	InformationMailRequestParams.java;


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
 * information_mail_requestテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link InformationMailRequestRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link InformationMailRequestParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link InformationMailRequestParams}が{@@link InformationMailRequestRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see InformationMailRequestPK 
 * @@see InformationMailRequestRow 
 */
public class InformationMailRequestParams extends Params implements InformationMailRequestRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "information_mail_request";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = InformationMailRequestRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return InformationMailRequestRow.TYPE;
  }


  /** 
   * <em>information_mail_request_id</em>カラムの値 
   */
  public  long  information_mail_request_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>sendmail_dev</em>カラムの値 
   */
  public  String  sendmail_dev;

  /** 
   * <em>discernment_id</em>カラムの値 
   */
  public  String  discernment_id;

  /** 
   * <em>all_account_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  all_account_flag;

  /** 
   * <em>account_count</em>カラムの値 
   */
  public  long  account_count;

  /** 
   * <em>request_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  request_timestamp;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

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

  private boolean information_mail_request_id_is_set = false;
  private boolean information_mail_request_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean sendmail_dev_is_set = false;
  private boolean sendmail_dev_is_modified = false;
  private boolean discernment_id_is_set = false;
  private boolean discernment_id_is_modified = false;
  private boolean all_account_flag_is_set = false;
  private boolean all_account_flag_is_modified = false;
  private boolean account_count_is_set = false;
  private boolean account_count_is_modified = false;
  private boolean request_timestamp_is_set = false;
  private boolean request_timestamp_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
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
       + "information_mail_request_id=" + information_mail_request_id
      + "," + "institution_code=" +institution_code
      + "," + "sendmail_dev=" +sendmail_dev
      + "," + "discernment_id=" +discernment_id
      + "," + "all_account_flag=" +all_account_flag
      + "," + "account_count=" +account_count
      + "," + "request_timestamp=" +request_timestamp
      + "," + "status=" +status
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のInformationMailRequestParamsオブジェクトを作成します。 
   */
  public InformationMailRequestParams() {
    information_mail_request_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のInformationMailRequestRowオブジェクトの値を利用してInformationMailRequestParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するInformationMailRequestRowオブジェクト 
   */
  public InformationMailRequestParams( InformationMailRequestRow p_row )
  {
    information_mail_request_id = p_row.getInformationMailRequestId();
    information_mail_request_id_is_set = p_row.getInformationMailRequestIdIsSet();
    information_mail_request_id_is_modified = p_row.getInformationMailRequestIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    sendmail_dev = p_row.getSendmailDev();
    sendmail_dev_is_set = p_row.getSendmailDevIsSet();
    sendmail_dev_is_modified = p_row.getSendmailDevIsModified();
    discernment_id = p_row.getDiscernmentId();
    discernment_id_is_set = p_row.getDiscernmentIdIsSet();
    discernment_id_is_modified = p_row.getDiscernmentIdIsModified();
    all_account_flag = p_row.getAllAccountFlag();
    all_account_flag_is_set = p_row.getAllAccountFlagIsSet();
    all_account_flag_is_modified = p_row.getAllAccountFlagIsModified();
    account_count = p_row.getAccountCount();
    account_count_is_set = p_row.getAccountCountIsSet();
    account_count_is_modified = p_row.getAccountCountIsModified();
    request_timestamp = p_row.getRequestTimestamp();
    request_timestamp_is_set = p_row.getRequestTimestampIsSet();
    request_timestamp_is_modified = p_row.getRequestTimestampIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
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
      sendmail_dev_is_set = true;
      sendmail_dev_is_modified = true;
      discernment_id_is_set = true;
      discernment_id_is_modified = true;
      all_account_flag_is_set = true;
      all_account_flag_is_modified = true;
      account_count_is_set = true;
      account_count_is_modified = true;
      request_timestamp_is_set = true;
      request_timestamp_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
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
    if ( !( other instanceof InformationMailRequestRow ) )
       return false;
    return fieldsEqual( (InformationMailRequestRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のInformationMailRequestRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( InformationMailRequestRow row )
  {
    if ( information_mail_request_id != row.getInformationMailRequestId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( sendmail_dev == null ) {
      if ( row.getSendmailDev() != null )
        return false;
    } else if ( !sendmail_dev.equals( row.getSendmailDev() ) ) {
        return false;
    }
    if ( discernment_id == null ) {
      if ( row.getDiscernmentId() != null )
        return false;
    } else if ( !discernment_id.equals( row.getDiscernmentId() ) ) {
        return false;
    }
    if ( all_account_flag == null ) {
      if ( row.getAllAccountFlag() != null )
        return false;
    } else if ( !all_account_flag.equals( row.getAllAccountFlag() ) ) {
        return false;
    }
    if ( account_count != row.getAccountCount() )
      return false;
    if ( request_timestamp == null ) {
      if ( row.getRequestTimestamp() != null )
        return false;
    } else if ( !request_timestamp.equals( row.getRequestTimestamp() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
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
      return  ((int) information_mail_request_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (sendmail_dev!=null? sendmail_dev.hashCode(): 0) 
        + (discernment_id!=null? discernment_id.hashCode(): 0) 
        + (all_account_flag!=null? all_account_flag.hashCode(): 0) 
        + ((int) account_count)
        + (request_timestamp!=null? request_timestamp.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
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
		if ( !sendmail_dev_is_set )
			throw new IllegalArgumentException("non-nullable field 'sendmail_dev' must be set before inserting.");
		if ( !discernment_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'discernment_id' must be set before inserting.");
		if ( !account_count_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_count' must be set before inserting.");
		if ( !request_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'request_timestamp' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
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
		map.put("information_mail_request_id",new Long(information_mail_request_id));
		map.put("institution_code",institution_code);
		map.put("sendmail_dev",sendmail_dev);
		map.put("discernment_id",discernment_id);
		if ( all_account_flag_is_set )
			map.put("all_account_flag",all_account_flag);
		map.put("account_count",new Long(account_count));
		map.put("request_timestamp",request_timestamp);
		map.put("status",status);
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
		if ( sendmail_dev_is_modified )
			map.put("sendmail_dev",sendmail_dev);
		if ( discernment_id_is_modified )
			map.put("discernment_id",discernment_id);
		if ( all_account_flag_is_modified )
			map.put("all_account_flag",all_account_flag);
		if ( account_count_is_modified )
			map.put("account_count",new Long(account_count));
		if ( request_timestamp_is_modified )
			map.put("request_timestamp",request_timestamp);
		if ( status_is_modified )
			map.put("status",status);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( sendmail_dev_is_set )
				map.put("sendmail_dev",sendmail_dev);
			if ( discernment_id_is_set )
				map.put("discernment_id",discernment_id);
			if ( all_account_flag_is_set )
				map.put("all_account_flag",all_account_flag);
			if ( account_count_is_set )
				map.put("account_count",new Long(account_count));
			if ( request_timestamp_is_set )
				map.put("request_timestamp",request_timestamp);
			if ( status_is_set )
				map.put("status",status);
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
   * <em>information_mail_request_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getInformationMailRequestId()
  {
    return information_mail_request_id;
  }


  /** 
   * <em>information_mail_request_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInformationMailRequestIdIsSet() {
    return information_mail_request_id_is_set;
  }


  /** 
   * <em>information_mail_request_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInformationMailRequestIdIsModified() {
    return information_mail_request_id_is_modified;
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
   * <em>sendmail_dev</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSendmailDev()
  {
    return sendmail_dev;
  }


  /** 
   * <em>sendmail_dev</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendmailDevIsSet() {
    return sendmail_dev_is_set;
  }


  /** 
   * <em>sendmail_dev</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendmailDevIsModified() {
    return sendmail_dev_is_modified;
  }


  /** 
   * <em>discernment_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDiscernmentId()
  {
    return discernment_id;
  }


  /** 
   * <em>discernment_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDiscernmentIdIsSet() {
    return discernment_id_is_set;
  }


  /** 
   * <em>discernment_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDiscernmentIdIsModified() {
    return discernment_id_is_modified;
  }


  /** 
   * <em>all_account_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getAllAccountFlag()
  {
    return all_account_flag;
  }


  /** 
   * <em>all_account_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAllAccountFlagIsSet() {
    return all_account_flag_is_set;
  }


  /** 
   * <em>all_account_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAllAccountFlagIsModified() {
    return all_account_flag_is_modified;
  }


  /** 
   * <em>account_count</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountCount()
  {
    return account_count;
  }


  /** 
   * <em>account_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCountIsSet() {
    return account_count_is_set;
  }


  /** 
   * <em>account_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCountIsModified() {
    return account_count_is_modified;
  }


  /** 
   * <em>request_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getRequestTimestamp()
  {
    return request_timestamp;
  }


  /** 
   * <em>request_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestTimestampIsSet() {
    return request_timestamp_is_set;
  }


  /** 
   * <em>request_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestTimestampIsModified() {
    return request_timestamp_is_modified;
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
    return new InformationMailRequestPK(information_mail_request_id);
  }


  /** 
   * <em>information_mail_request_id</em>カラムの値を設定します。 
   *
   * @@param p_informationMailRequestId <em>information_mail_request_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setInformationMailRequestId( long p_informationMailRequestId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    information_mail_request_id = p_informationMailRequestId;
    information_mail_request_id_is_set = true;
    information_mail_request_id_is_modified = true;
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
   * <em>sendmail_dev</em>カラムの値を設定します。 
   *
   * @@param p_sendmailDev <em>sendmail_dev</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setSendmailDev( String p_sendmailDev )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sendmail_dev = p_sendmailDev;
    sendmail_dev_is_set = true;
    sendmail_dev_is_modified = true;
  }


  /** 
   * <em>discernment_id</em>カラムの値を設定します。 
   *
   * @@param p_discernmentId <em>discernment_id</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setDiscernmentId( String p_discernmentId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    discernment_id = p_discernmentId;
    discernment_id_is_set = true;
    discernment_id_is_modified = true;
  }


  /** 
   * <em>all_account_flag</em>カラムの値を設定します。 
   *
   * @@param p_allAccountFlag <em>all_account_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setAllAccountFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_allAccountFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    all_account_flag = p_allAccountFlag;
    all_account_flag_is_set = true;
    all_account_flag_is_modified = true;
  }


  /** 
   * <em>account_count</em>カラムの値を設定します。 
   *
   * @@param p_accountCount <em>account_count</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountCount( long p_accountCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_count = p_accountCount;
    account_count_is_set = true;
    account_count_is_modified = true;
  }


  /** 
   * <em>request_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_requestTimestamp <em>request_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setRequestTimestamp( java.sql.Timestamp p_requestTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_timestamp = p_requestTimestamp;
    request_timestamp_is_set = true;
    request_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>request_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setRequestTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    request_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    request_timestamp_is_set = true;
    request_timestamp_is_modified = true;
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
                if ( name.equals("all_account_flag") ) {
                    return this.all_account_flag;
                }
                else if ( name.equals("account_count") ) {
                    return new Long( account_count );
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("discernment_id") ) {
                    return this.discernment_id;
                }
                break;
            case 'i':
                if ( name.equals("information_mail_request_id") ) {
                    return new Long( information_mail_request_id );
                }
                else if ( name.equals("institution_code") ) {
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
                if ( name.equals("request_timestamp") ) {
                    return this.request_timestamp;
                }
                break;
            case 's':
                if ( name.equals("sendmail_dev") ) {
                    return this.sendmail_dev;
                }
                else if ( name.equals("status") ) {
                    return this.status;
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
                if ( name.equals("all_account_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'all_account_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.all_account_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.all_account_flag_is_set)
                        this.all_account_flag_is_modified = true;
                    this.all_account_flag_is_set = true;
                    return;
                }
                else if ( name.equals("account_count") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_count' must be Long: '"+value+"' is not." );
                    this.account_count = ((Long) value).longValue();
                    if (this.account_count_is_set)
                        this.account_count_is_modified = true;
                    this.account_count_is_set = true;
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
            case 'd':
                if ( name.equals("discernment_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'discernment_id' must be String: '"+value+"' is not." );
                    this.discernment_id = (String) value;
                    if (this.discernment_id_is_set)
                        this.discernment_id_is_modified = true;
                    this.discernment_id_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("information_mail_request_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'information_mail_request_id' must be Long: '"+value+"' is not." );
                    this.information_mail_request_id = ((Long) value).longValue();
                    if (this.information_mail_request_id_is_set)
                        this.information_mail_request_id_is_modified = true;
                    this.information_mail_request_id_is_set = true;
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
                if ( name.equals("request_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'request_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.request_timestamp = (java.sql.Timestamp) value;
                    if (this.request_timestamp_is_set)
                        this.request_timestamp_is_modified = true;
                    this.request_timestamp_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sendmail_dev") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sendmail_dev' must be String: '"+value+"' is not." );
                    this.sendmail_dev = (String) value;
                    if (this.sendmail_dev_is_set)
                        this.sendmail_dev_is_modified = true;
                    this.sendmail_dev_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
