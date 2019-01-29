head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.45.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	GftAccountOpenStatusParams.java;


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
 * gft_account_open_statusテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link GftAccountOpenStatusRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link GftAccountOpenStatusParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link GftAccountOpenStatusParams}が{@@link GftAccountOpenStatusRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see GftAccountOpenStatusPK 
 * @@see GftAccountOpenStatusRow 
 */
public class GftAccountOpenStatusParams extends Params implements GftAccountOpenStatusRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "gft_account_open_status";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = GftAccountOpenStatusRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return GftAccountOpenStatusRow.TYPE;
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
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>last_name</em>カラムの値 
   */
  public  String  last_name;

  /** 
   * <em>first_name</em>カラムの値 
   */
  public  String  first_name;

  /** 
   * <em>mail_address</em>カラムの値 
   */
  public  String  mail_address;

  /** 
   * <em>login_id</em>カラムの値 
   */
  public  String  login_id;

  /** 
   * <em>password</em>カラムの値 
   */
  public  String  password;

  /** 
   * <em>fx_account_code_01</em>カラムの値 
   */
  public  String  fx_account_code_01;

  /** 
   * <em>fx_account_code_10</em>カラムの値 
   */
  public  String  fx_account_code_10;

  /** 
   * <em>account_open_status_div</em>カラムの値 
   */
  public  String  account_open_status_div;

  /** 
   * <em>send_rcv_div</em>カラムの値 
   */
  public  String  send_rcv_div;

  /** 
   * <em>result_code</em>カラムの値 
   */
  public  String  result_code;

  /** 
   * <em>error_reason_code</em>カラムの値 
   */
  public  String  error_reason_code;

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
   * <em>agreement_div</em>カラムの値 
   */
  public  String  agreement_div;

  /** 
   * <em>result_code_soap</em>カラムの値 
   */
  public  String  result_code_soap;

  /** 
   * <em>fx_system_code</em>カラムの値 
   */
  public  String  fx_system_code;

  /** 
   * <em>ext_account_code</em>カラムの値 
   */
  public  String  ext_account_code;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean last_name_is_set = false;
  private boolean last_name_is_modified = false;
  private boolean first_name_is_set = false;
  private boolean first_name_is_modified = false;
  private boolean mail_address_is_set = false;
  private boolean mail_address_is_modified = false;
  private boolean login_id_is_set = false;
  private boolean login_id_is_modified = false;
  private boolean password_is_set = false;
  private boolean password_is_modified = false;
  private boolean fx_account_code_01_is_set = false;
  private boolean fx_account_code_01_is_modified = false;
  private boolean fx_account_code_10_is_set = false;
  private boolean fx_account_code_10_is_modified = false;
  private boolean account_open_status_div_is_set = false;
  private boolean account_open_status_div_is_modified = false;
  private boolean send_rcv_div_is_set = false;
  private boolean send_rcv_div_is_modified = false;
  private boolean result_code_is_set = false;
  private boolean result_code_is_modified = false;
  private boolean error_reason_code_is_set = false;
  private boolean error_reason_code_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean agreement_div_is_set = false;
  private boolean agreement_div_is_modified = false;
  private boolean result_code_soap_is_set = false;
  private boolean result_code_soap_is_modified = false;
  private boolean fx_system_code_is_set = false;
  private boolean fx_system_code_is_modified = false;
  private boolean ext_account_code_is_set = false;
  private boolean ext_account_code_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "order_request_number=" + order_request_number
      + "," + "account_code=" +account_code
      + "," + "last_name=" +last_name
      + "," + "first_name=" +first_name
      + "," + "mail_address=" +mail_address
      + "," + "login_id=" +login_id
      + "," + "password=" +password
      + "," + "fx_account_code_01=" +fx_account_code_01
      + "," + "fx_account_code_10=" +fx_account_code_10
      + "," + "account_open_status_div=" +account_open_status_div
      + "," + "send_rcv_div=" +send_rcv_div
      + "," + "result_code=" +result_code
      + "," + "error_reason_code=" +error_reason_code
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "agreement_div=" +agreement_div
      + "," + "result_code_soap=" +result_code_soap
      + "," + "fx_system_code=" +fx_system_code
      + "," + "ext_account_code=" +ext_account_code
      + "}";
  }


  /** 
   * 値が未設定のGftAccountOpenStatusParamsオブジェクトを作成します。 
   */
  public GftAccountOpenStatusParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    order_request_number_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のGftAccountOpenStatusRowオブジェクトの値を利用してGftAccountOpenStatusParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するGftAccountOpenStatusRowオブジェクト 
   */
  public GftAccountOpenStatusParams( GftAccountOpenStatusRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    last_name = p_row.getLastName();
    last_name_is_set = p_row.getLastNameIsSet();
    last_name_is_modified = p_row.getLastNameIsModified();
    first_name = p_row.getFirstName();
    first_name_is_set = p_row.getFirstNameIsSet();
    first_name_is_modified = p_row.getFirstNameIsModified();
    mail_address = p_row.getMailAddress();
    mail_address_is_set = p_row.getMailAddressIsSet();
    mail_address_is_modified = p_row.getMailAddressIsModified();
    login_id = p_row.getLoginId();
    login_id_is_set = p_row.getLoginIdIsSet();
    login_id_is_modified = p_row.getLoginIdIsModified();
    password = p_row.getPassword();
    password_is_set = p_row.getPasswordIsSet();
    password_is_modified = p_row.getPasswordIsModified();
    fx_account_code_01 = p_row.getFxAccountCode01();
    fx_account_code_01_is_set = p_row.getFxAccountCode01IsSet();
    fx_account_code_01_is_modified = p_row.getFxAccountCode01IsModified();
    fx_account_code_10 = p_row.getFxAccountCode10();
    fx_account_code_10_is_set = p_row.getFxAccountCode10IsSet();
    fx_account_code_10_is_modified = p_row.getFxAccountCode10IsModified();
    account_open_status_div = p_row.getAccountOpenStatusDiv();
    account_open_status_div_is_set = p_row.getAccountOpenStatusDivIsSet();
    account_open_status_div_is_modified = p_row.getAccountOpenStatusDivIsModified();
    send_rcv_div = p_row.getSendRcvDiv();
    send_rcv_div_is_set = p_row.getSendRcvDivIsSet();
    send_rcv_div_is_modified = p_row.getSendRcvDivIsModified();
    result_code = p_row.getResultCode();
    result_code_is_set = p_row.getResultCodeIsSet();
    result_code_is_modified = p_row.getResultCodeIsModified();
    error_reason_code = p_row.getErrorReasonCode();
    error_reason_code_is_set = p_row.getErrorReasonCodeIsSet();
    error_reason_code_is_modified = p_row.getErrorReasonCodeIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    agreement_div = p_row.getAgreementDiv();
    agreement_div_is_set = p_row.getAgreementDivIsSet();
    agreement_div_is_modified = p_row.getAgreementDivIsModified();
    result_code_soap = p_row.getResultCodeSoap();
    result_code_soap_is_set = p_row.getResultCodeSoapIsSet();
    result_code_soap_is_modified = p_row.getResultCodeSoapIsModified();
    fx_system_code = p_row.getFxSystemCode();
    fx_system_code_is_set = p_row.getFxSystemCodeIsSet();
    fx_system_code_is_modified = p_row.getFxSystemCodeIsModified();
    ext_account_code = p_row.getExtAccountCode();
    ext_account_code_is_set = p_row.getExtAccountCodeIsSet();
    ext_account_code_is_modified = p_row.getExtAccountCodeIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      account_code_is_set = true;
      account_code_is_modified = true;
      last_name_is_set = true;
      last_name_is_modified = true;
      first_name_is_set = true;
      first_name_is_modified = true;
      mail_address_is_set = true;
      mail_address_is_modified = true;
      login_id_is_set = true;
      login_id_is_modified = true;
      password_is_set = true;
      password_is_modified = true;
      fx_account_code_01_is_set = true;
      fx_account_code_01_is_modified = true;
      fx_account_code_10_is_set = true;
      fx_account_code_10_is_modified = true;
      account_open_status_div_is_set = true;
      account_open_status_div_is_modified = true;
      send_rcv_div_is_set = true;
      send_rcv_div_is_modified = true;
      result_code_is_set = true;
      result_code_is_modified = true;
      error_reason_code_is_set = true;
      error_reason_code_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      agreement_div_is_set = true;
      agreement_div_is_modified = true;
      result_code_soap_is_set = true;
      result_code_soap_is_modified = true;
      fx_system_code_is_set = true;
      fx_system_code_is_modified = true;
      ext_account_code_is_set = true;
      ext_account_code_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof GftAccountOpenStatusRow ) )
       return false;
    return fieldsEqual( (GftAccountOpenStatusRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のGftAccountOpenStatusRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( GftAccountOpenStatusRow row )
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
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( last_name == null ) {
      if ( row.getLastName() != null )
        return false;
    } else if ( !last_name.equals( row.getLastName() ) ) {
        return false;
    }
    if ( first_name == null ) {
      if ( row.getFirstName() != null )
        return false;
    } else if ( !first_name.equals( row.getFirstName() ) ) {
        return false;
    }
    if ( mail_address == null ) {
      if ( row.getMailAddress() != null )
        return false;
    } else if ( !mail_address.equals( row.getMailAddress() ) ) {
        return false;
    }
    if ( login_id == null ) {
      if ( row.getLoginId() != null )
        return false;
    } else if ( !login_id.equals( row.getLoginId() ) ) {
        return false;
    }
    if ( password == null ) {
      if ( row.getPassword() != null )
        return false;
    } else if ( !password.equals( row.getPassword() ) ) {
        return false;
    }
    if ( fx_account_code_01 == null ) {
      if ( row.getFxAccountCode01() != null )
        return false;
    } else if ( !fx_account_code_01.equals( row.getFxAccountCode01() ) ) {
        return false;
    }
    if ( fx_account_code_10 == null ) {
      if ( row.getFxAccountCode10() != null )
        return false;
    } else if ( !fx_account_code_10.equals( row.getFxAccountCode10() ) ) {
        return false;
    }
    if ( account_open_status_div == null ) {
      if ( row.getAccountOpenStatusDiv() != null )
        return false;
    } else if ( !account_open_status_div.equals( row.getAccountOpenStatusDiv() ) ) {
        return false;
    }
    if ( send_rcv_div == null ) {
      if ( row.getSendRcvDiv() != null )
        return false;
    } else if ( !send_rcv_div.equals( row.getSendRcvDiv() ) ) {
        return false;
    }
    if ( result_code == null ) {
      if ( row.getResultCode() != null )
        return false;
    } else if ( !result_code.equals( row.getResultCode() ) ) {
        return false;
    }
    if ( error_reason_code == null ) {
      if ( row.getErrorReasonCode() != null )
        return false;
    } else if ( !error_reason_code.equals( row.getErrorReasonCode() ) ) {
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
    if ( agreement_div == null ) {
      if ( row.getAgreementDiv() != null )
        return false;
    } else if ( !agreement_div.equals( row.getAgreementDiv() ) ) {
        return false;
    }
    if ( result_code_soap == null ) {
      if ( row.getResultCodeSoap() != null )
        return false;
    } else if ( !result_code_soap.equals( row.getResultCodeSoap() ) ) {
        return false;
    }
    if ( fx_system_code == null ) {
      if ( row.getFxSystemCode() != null )
        return false;
    } else if ( !fx_system_code.equals( row.getFxSystemCode() ) ) {
        return false;
    }
    if ( ext_account_code == null ) {
      if ( row.getExtAccountCode() != null )
        return false;
    } else if ( !ext_account_code.equals( row.getExtAccountCode() ) ) {
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
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (last_name!=null? last_name.hashCode(): 0) 
        + (first_name!=null? first_name.hashCode(): 0) 
        + (mail_address!=null? mail_address.hashCode(): 0) 
        + (login_id!=null? login_id.hashCode(): 0) 
        + (password!=null? password.hashCode(): 0) 
        + (fx_account_code_01!=null? fx_account_code_01.hashCode(): 0) 
        + (fx_account_code_10!=null? fx_account_code_10.hashCode(): 0) 
        + (account_open_status_div!=null? account_open_status_div.hashCode(): 0) 
        + (send_rcv_div!=null? send_rcv_div.hashCode(): 0) 
        + (result_code!=null? result_code.hashCode(): 0) 
        + (error_reason_code!=null? error_reason_code.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (agreement_div!=null? agreement_div.hashCode(): 0) 
        + (result_code_soap!=null? result_code_soap.hashCode(): 0) 
        + (fx_system_code!=null? fx_system_code.hashCode(): 0) 
        + (ext_account_code!=null? ext_account_code.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !account_open_status_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_open_status_div' must be set before inserting.");
		if ( !send_rcv_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'send_rcv_div' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
		if ( !agreement_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'agreement_div' must be set before inserting.");
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
		map.put("order_request_number",order_request_number);
		if ( last_name != null )
			map.put("last_name",last_name);
		if ( first_name != null )
			map.put("first_name",first_name);
		if ( mail_address != null )
			map.put("mail_address",mail_address);
		if ( login_id != null )
			map.put("login_id",login_id);
		if ( password != null )
			map.put("password",password);
		if ( fx_account_code_01 != null )
			map.put("fx_account_code_01",fx_account_code_01);
		if ( fx_account_code_10 != null )
			map.put("fx_account_code_10",fx_account_code_10);
		map.put("account_open_status_div",account_open_status_div);
		map.put("send_rcv_div",send_rcv_div);
		if ( result_code != null )
			map.put("result_code",result_code);
		if ( error_reason_code != null )
			map.put("error_reason_code",error_reason_code);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("agreement_div",agreement_div);
		if ( result_code_soap != null )
			map.put("result_code_soap",result_code_soap);
		if ( fx_system_code != null )
			map.put("fx_system_code",fx_system_code);
		if ( ext_account_code != null )
			map.put("ext_account_code",ext_account_code);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( last_name_is_modified )
			map.put("last_name",last_name);
		if ( first_name_is_modified )
			map.put("first_name",first_name);
		if ( mail_address_is_modified )
			map.put("mail_address",mail_address);
		if ( login_id_is_modified )
			map.put("login_id",login_id);
		if ( password_is_modified )
			map.put("password",password);
		if ( fx_account_code_01_is_modified )
			map.put("fx_account_code_01",fx_account_code_01);
		if ( fx_account_code_10_is_modified )
			map.put("fx_account_code_10",fx_account_code_10);
		if ( account_open_status_div_is_modified )
			map.put("account_open_status_div",account_open_status_div);
		if ( send_rcv_div_is_modified )
			map.put("send_rcv_div",send_rcv_div);
		if ( result_code_is_modified )
			map.put("result_code",result_code);
		if ( error_reason_code_is_modified )
			map.put("error_reason_code",error_reason_code);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( agreement_div_is_modified )
			map.put("agreement_div",agreement_div);
		if ( result_code_soap_is_modified )
			map.put("result_code_soap",result_code_soap);
		if ( fx_system_code_is_modified )
			map.put("fx_system_code",fx_system_code);
		if ( ext_account_code_is_modified )
			map.put("ext_account_code",ext_account_code);
		if (map.size() == 0) {
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("last_name",last_name);
			map.put("first_name",first_name);
			map.put("mail_address",mail_address);
			map.put("login_id",login_id);
			map.put("password",password);
			map.put("fx_account_code_01",fx_account_code_01);
			map.put("fx_account_code_10",fx_account_code_10);
			if ( account_open_status_div_is_set )
				map.put("account_open_status_div",account_open_status_div);
			if ( send_rcv_div_is_set )
				map.put("send_rcv_div",send_rcv_div);
			map.put("result_code",result_code);
			map.put("error_reason_code",error_reason_code);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( agreement_div_is_set )
				map.put("agreement_div",agreement_div);
			map.put("result_code_soap",result_code_soap);
			map.put("fx_system_code",fx_system_code);
			map.put("ext_account_code",ext_account_code);
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
   * <em>order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRequestNumber()
  {
    return order_request_number;
  }


  /** 
   * <em>order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsSet() {
    return order_request_number_is_set;
  }


  /** 
   * <em>order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsModified() {
    return order_request_number_is_modified;
  }


  /** 
   * <em>last_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastName()
  {
    return last_name;
  }


  /** 
   * <em>last_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastNameIsSet() {
    return last_name_is_set;
  }


  /** 
   * <em>last_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastNameIsModified() {
    return last_name_is_modified;
  }


  /** 
   * <em>first_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFirstName()
  {
    return first_name;
  }


  /** 
   * <em>first_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstNameIsSet() {
    return first_name_is_set;
  }


  /** 
   * <em>first_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstNameIsModified() {
    return first_name_is_modified;
  }


  /** 
   * <em>mail_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMailAddress()
  {
    return mail_address;
  }


  /** 
   * <em>mail_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMailAddressIsSet() {
    return mail_address_is_set;
  }


  /** 
   * <em>mail_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMailAddressIsModified() {
    return mail_address_is_modified;
  }


  /** 
   * <em>login_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLoginId()
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
   * <em>password</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPassword()
  {
    return password;
  }


  /** 
   * <em>password</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPasswordIsSet() {
    return password_is_set;
  }


  /** 
   * <em>password</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPasswordIsModified() {
    return password_is_modified;
  }


  /** 
   * <em>fx_account_code_01</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFxAccountCode01()
  {
    return fx_account_code_01;
  }


  /** 
   * <em>fx_account_code_01</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxAccountCode01IsSet() {
    return fx_account_code_01_is_set;
  }


  /** 
   * <em>fx_account_code_01</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxAccountCode01IsModified() {
    return fx_account_code_01_is_modified;
  }


  /** 
   * <em>fx_account_code_10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFxAccountCode10()
  {
    return fx_account_code_10;
  }


  /** 
   * <em>fx_account_code_10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxAccountCode10IsSet() {
    return fx_account_code_10_is_set;
  }


  /** 
   * <em>fx_account_code_10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxAccountCode10IsModified() {
    return fx_account_code_10_is_modified;
  }


  /** 
   * <em>account_open_status_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenStatusDiv()
  {
    return account_open_status_div;
  }


  /** 
   * <em>account_open_status_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenStatusDivIsSet() {
    return account_open_status_div_is_set;
  }


  /** 
   * <em>account_open_status_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenStatusDivIsModified() {
    return account_open_status_div_is_modified;
  }


  /** 
   * <em>send_rcv_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSendRcvDiv()
  {
    return send_rcv_div;
  }


  /** 
   * <em>send_rcv_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendRcvDivIsSet() {
    return send_rcv_div_is_set;
  }


  /** 
   * <em>send_rcv_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendRcvDivIsModified() {
    return send_rcv_div_is_modified;
  }


  /** 
   * <em>result_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getResultCode()
  {
    return result_code;
  }


  /** 
   * <em>result_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResultCodeIsSet() {
    return result_code_is_set;
  }


  /** 
   * <em>result_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResultCodeIsModified() {
    return result_code_is_modified;
  }


  /** 
   * <em>error_reason_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getErrorReasonCode()
  {
    return error_reason_code;
  }


  /** 
   * <em>error_reason_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorReasonCodeIsSet() {
    return error_reason_code_is_set;
  }


  /** 
   * <em>error_reason_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorReasonCodeIsModified() {
    return error_reason_code_is_modified;
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
   * <em>agreement_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgreementDiv()
  {
    return agreement_div;
  }


  /** 
   * <em>agreement_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgreementDivIsSet() {
    return agreement_div_is_set;
  }


  /** 
   * <em>agreement_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgreementDivIsModified() {
    return agreement_div_is_modified;
  }


  /** 
   * <em>result_code_soap</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getResultCodeSoap()
  {
    return result_code_soap;
  }


  /** 
   * <em>result_code_soap</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResultCodeSoapIsSet() {
    return result_code_soap_is_set;
  }


  /** 
   * <em>result_code_soap</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResultCodeSoapIsModified() {
    return result_code_soap_is_modified;
  }


  /** 
   * <em>fx_system_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFxSystemCode()
  {
    return fx_system_code;
  }


  /** 
   * <em>fx_system_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxSystemCodeIsSet() {
    return fx_system_code_is_set;
  }


  /** 
   * <em>fx_system_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxSystemCodeIsModified() {
    return fx_system_code_is_modified;
  }


  /** 
   * <em>ext_account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtAccountCode()
  {
    return ext_account_code;
  }


  /** 
   * <em>ext_account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtAccountCodeIsSet() {
    return ext_account_code_is_set;
  }


  /** 
   * <em>ext_account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtAccountCodeIsModified() {
    return ext_account_code_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new GftAccountOpenStatusPK(institution_code, branch_code, order_request_number);
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
   * <em>order_request_number</em>カラムの値を設定します。 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setOrderRequestNumber( String p_orderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number = p_orderRequestNumber;
    order_request_number_is_set = true;
    order_request_number_is_modified = true;
  }


  /** 
   * <em>last_name</em>カラムの値を設定します。 
   *
   * @@param p_lastName <em>last_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setLastName( String p_lastName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_name = p_lastName;
    last_name_is_set = true;
    last_name_is_modified = true;
  }


  /** 
   * <em>first_name</em>カラムの値を設定します。 
   *
   * @@param p_firstName <em>first_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setFirstName( String p_firstName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    first_name = p_firstName;
    first_name_is_set = true;
    first_name_is_modified = true;
  }


  /** 
   * <em>mail_address</em>カラムの値を設定します。 
   *
   * @@param p_mailAddress <em>mail_address</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setMailAddress( String p_mailAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mail_address = p_mailAddress;
    mail_address_is_set = true;
    mail_address_is_modified = true;
  }


  /** 
   * <em>login_id</em>カラムの値を設定します。 
   *
   * @@param p_loginId <em>login_id</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setLoginId( String p_loginId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    login_id = p_loginId;
    login_id_is_set = true;
    login_id_is_modified = true;
  }


  /** 
   * <em>password</em>カラムの値を設定します。 
   *
   * @@param p_password <em>password</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setPassword( String p_password )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    password = p_password;
    password_is_set = true;
    password_is_modified = true;
  }


  /** 
   * <em>fx_account_code_01</em>カラムの値を設定します。 
   *
   * @@param p_fxAccountCode01 <em>fx_account_code_01</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setFxAccountCode01( String p_fxAccountCode01 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_account_code_01 = p_fxAccountCode01;
    fx_account_code_01_is_set = true;
    fx_account_code_01_is_modified = true;
  }


  /** 
   * <em>fx_account_code_10</em>カラムの値を設定します。 
   *
   * @@param p_fxAccountCode10 <em>fx_account_code_10</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setFxAccountCode10( String p_fxAccountCode10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_account_code_10 = p_fxAccountCode10;
    fx_account_code_10_is_set = true;
    fx_account_code_10_is_modified = true;
  }


  /** 
   * <em>account_open_status_div</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenStatusDiv <em>account_open_status_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setAccountOpenStatusDiv( String p_accountOpenStatusDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_status_div = p_accountOpenStatusDiv;
    account_open_status_div_is_set = true;
    account_open_status_div_is_modified = true;
  }


  /** 
   * <em>send_rcv_div</em>カラムの値を設定します。 
   *
   * @@param p_sendRcvDiv <em>send_rcv_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setSendRcvDiv( String p_sendRcvDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_rcv_div = p_sendRcvDiv;
    send_rcv_div_is_set = true;
    send_rcv_div_is_modified = true;
  }


  /** 
   * <em>result_code</em>カラムの値を設定します。 
   *
   * @@param p_resultCode <em>result_code</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setResultCode( String p_resultCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    result_code = p_resultCode;
    result_code_is_set = true;
    result_code_is_modified = true;
  }


  /** 
   * <em>error_reason_code</em>カラムの値を設定します。 
   *
   * @@param p_errorReasonCode <em>error_reason_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setErrorReasonCode( String p_errorReasonCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_reason_code = p_errorReasonCode;
    error_reason_code_is_set = true;
    error_reason_code_is_modified = true;
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
   * <em>agreement_div</em>カラムの値を設定します。 
   *
   * @@param p_agreementDiv <em>agreement_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAgreementDiv( String p_agreementDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agreement_div = p_agreementDiv;
    agreement_div_is_set = true;
    agreement_div_is_modified = true;
  }


  /** 
   * <em>result_code_soap</em>カラムの値を設定します。 
   *
   * @@param p_resultCodeSoap <em>result_code_soap</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setResultCodeSoap( String p_resultCodeSoap )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    result_code_soap = p_resultCodeSoap;
    result_code_soap_is_set = true;
    result_code_soap_is_modified = true;
  }


  /** 
   * <em>fx_system_code</em>カラムの値を設定します。 
   *
   * @@param p_fxSystemCode <em>fx_system_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setFxSystemCode( String p_fxSystemCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_system_code = p_fxSystemCode;
    fx_system_code_is_set = true;
    fx_system_code_is_modified = true;
  }


  /** 
   * <em>ext_account_code</em>カラムの値を設定します。 
   *
   * @@param p_extAccountCode <em>ext_account_code</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setExtAccountCode( String p_extAccountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_account_code = p_extAccountCode;
    ext_account_code_is_set = true;
    ext_account_code_is_modified = true;
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
                else if ( name.equals("account_open_status_div") ) {
                    return this.account_open_status_div;
                }
                else if ( name.equals("agreement_div") ) {
                    return this.agreement_div;
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
                if ( name.equals("error_reason_code") ) {
                    return this.error_reason_code;
                }
                else if ( name.equals("ext_account_code") ) {
                    return this.ext_account_code;
                }
                break;
            case 'f':
                if ( name.equals("first_name") ) {
                    return this.first_name;
                }
                else if ( name.equals("fx_account_code_01") ) {
                    return this.fx_account_code_01;
                }
                else if ( name.equals("fx_account_code_10") ) {
                    return this.fx_account_code_10;
                }
                else if ( name.equals("fx_system_code") ) {
                    return this.fx_system_code;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_name") ) {
                    return this.last_name;
                }
                else if ( name.equals("login_id") ) {
                    return this.login_id;
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("mail_address") ) {
                    return this.mail_address;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                break;
            case 'p':
                if ( name.equals("password") ) {
                    return this.password;
                }
                break;
            case 'r':
                if ( name.equals("result_code") ) {
                    return this.result_code;
                }
                else if ( name.equals("result_code_soap") ) {
                    return this.result_code_soap;
                }
                break;
            case 's':
                if ( name.equals("send_rcv_div") ) {
                    return this.send_rcv_div;
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
                else if ( name.equals("account_open_status_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_status_div' must be String: '"+value+"' is not." );
                    this.account_open_status_div = (String) value;
                    if (this.account_open_status_div_is_set)
                        this.account_open_status_div_is_modified = true;
                    this.account_open_status_div_is_set = true;
                    return;
                }
                else if ( name.equals("agreement_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agreement_div' must be String: '"+value+"' is not." );
                    this.agreement_div = (String) value;
                    if (this.agreement_div_is_set)
                        this.agreement_div_is_modified = true;
                    this.agreement_div_is_set = true;
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
                if ( name.equals("error_reason_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_reason_code' must be String: '"+value+"' is not." );
                    this.error_reason_code = (String) value;
                    if (this.error_reason_code_is_set)
                        this.error_reason_code_is_modified = true;
                    this.error_reason_code_is_set = true;
                    return;
                }
                else if ( name.equals("ext_account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_account_code' must be String: '"+value+"' is not." );
                    this.ext_account_code = (String) value;
                    if (this.ext_account_code_is_set)
                        this.ext_account_code_is_modified = true;
                    this.ext_account_code_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("first_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'first_name' must be String: '"+value+"' is not." );
                    this.first_name = (String) value;
                    if (this.first_name_is_set)
                        this.first_name_is_modified = true;
                    this.first_name_is_set = true;
                    return;
                }
                else if ( name.equals("fx_account_code_01") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_account_code_01' must be String: '"+value+"' is not." );
                    this.fx_account_code_01 = (String) value;
                    if (this.fx_account_code_01_is_set)
                        this.fx_account_code_01_is_modified = true;
                    this.fx_account_code_01_is_set = true;
                    return;
                }
                else if ( name.equals("fx_account_code_10") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_account_code_10' must be String: '"+value+"' is not." );
                    this.fx_account_code_10 = (String) value;
                    if (this.fx_account_code_10_is_set)
                        this.fx_account_code_10_is_modified = true;
                    this.fx_account_code_10_is_set = true;
                    return;
                }
                else if ( name.equals("fx_system_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_system_code' must be String: '"+value+"' is not." );
                    this.fx_system_code = (String) value;
                    if (this.fx_system_code_is_set)
                        this.fx_system_code_is_modified = true;
                    this.fx_system_code_is_set = true;
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
                if ( name.equals("last_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_name' must be String: '"+value+"' is not." );
                    this.last_name = (String) value;
                    if (this.last_name_is_set)
                        this.last_name_is_modified = true;
                    this.last_name_is_set = true;
                    return;
                }
                else if ( name.equals("login_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'login_id' must be String: '"+value+"' is not." );
                    this.login_id = (String) value;
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
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("mail_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mail_address' must be String: '"+value+"' is not." );
                    this.mail_address = (String) value;
                    if (this.mail_address_is_set)
                        this.mail_address_is_modified = true;
                    this.mail_address_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("password") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'password' must be String: '"+value+"' is not." );
                    this.password = (String) value;
                    if (this.password_is_set)
                        this.password_is_modified = true;
                    this.password_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("result_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'result_code' must be String: '"+value+"' is not." );
                    this.result_code = (String) value;
                    if (this.result_code_is_set)
                        this.result_code_is_modified = true;
                    this.result_code_is_set = true;
                    return;
                }
                else if ( name.equals("result_code_soap") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'result_code_soap' must be String: '"+value+"' is not." );
                    this.result_code_soap = (String) value;
                    if (this.result_code_soap_is_set)
                        this.result_code_soap_is_modified = true;
                    this.result_code_soap_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("send_rcv_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'send_rcv_div' must be String: '"+value+"' is not." );
                    this.send_rcv_div = (String) value;
                    if (this.send_rcv_div_is_set)
                        this.send_rcv_div_is_modified = true;
                    this.send_rcv_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
