head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	PasswordVoucherParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * password_voucherテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link PasswordVoucherRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link PasswordVoucherParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link PasswordVoucherParams}が{@@link PasswordVoucherRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PasswordVoucherPK 
 * @@see PasswordVoucherRow 
 */
public class PasswordVoucherParams extends Params implements PasswordVoucherRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "password_voucher";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = PasswordVoucherRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return PasswordVoucherRow.TYPE;
  }


  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>request_code</em>カラムの値 
   */
  public  String  request_code;

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
   * <em>trader_code</em>カラムの値 
   */
  public  String  trader_code;

  /** 
   * <em>acc_open_request_number</em>カラムの値 
   */
  public  String  acc_open_request_number;

  /** 
   * <em>serial_no</em>カラムの値 
   */
  public  String  serial_no;

  /** 
   * <em>regist_delete_div</em>カラムの値 
   */
  public  String  regist_delete_div;

  /** 
   * <em>data_class</em>カラムの値 
   */
  public  String  data_class;

  /** 
   * <em>voucher_name</em>カラムの値 
   */
  public  String  voucher_name;

  /** 
   * <em>regist_date</em>カラムの値 
   */
  public  String  regist_date;

  /** 
   * <em>password</em>カラムの値 
   */
  public  String  password;

  /** 
   * <em>pub_reason_div</em>カラムの値 
   */
  public  String  pub_reason_div;

  /** 
   * <em>ref_number</em>カラムの値 
   */
  public  String  ref_number;

  /** 
   * <em>modified_name</em>カラムの値 
   */
  public  String  modified_name;

  /** 
   * <em>card_type</em>カラムの値 
   */
  public  String  card_type;

  /** 
   * <em>online_batch_div</em>カラムの値 
   */
  public  String  online_batch_div;

  /** 
   * <em>seq_no</em>カラムの値 
   */
  public  String  seq_no;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>send_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  send_timestamp;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean acc_open_request_number_is_set = false;
  private boolean acc_open_request_number_is_modified = false;
  private boolean serial_no_is_set = false;
  private boolean serial_no_is_modified = false;
  private boolean regist_delete_div_is_set = false;
  private boolean regist_delete_div_is_modified = false;
  private boolean data_class_is_set = false;
  private boolean data_class_is_modified = false;
  private boolean voucher_name_is_set = false;
  private boolean voucher_name_is_modified = false;
  private boolean regist_date_is_set = false;
  private boolean regist_date_is_modified = false;
  private boolean password_is_set = false;
  private boolean password_is_modified = false;
  private boolean pub_reason_div_is_set = false;
  private boolean pub_reason_div_is_modified = false;
  private boolean ref_number_is_set = false;
  private boolean ref_number_is_modified = false;
  private boolean modified_name_is_set = false;
  private boolean modified_name_is_modified = false;
  private boolean card_type_is_set = false;
  private boolean card_type_is_modified = false;
  private boolean online_batch_div_is_set = false;
  private boolean online_batch_div_is_modified = false;
  private boolean seq_no_is_set = false;
  private boolean seq_no_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean send_timestamp_is_set = false;
  private boolean send_timestamp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "order_request_number=" + order_request_number
      + "," + "request_code=" + request_code
      + "," + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "account_code=" + account_code
      + "," + "trader_code=" +trader_code
      + "," + "acc_open_request_number=" +acc_open_request_number
      + "," + "serial_no=" +serial_no
      + "," + "regist_delete_div=" +regist_delete_div
      + "," + "data_class=" +data_class
      + "," + "voucher_name=" +voucher_name
      + "," + "regist_date=" +regist_date
      + "," + "password=" +password
      + "," + "pub_reason_div=" +pub_reason_div
      + "," + "ref_number=" +ref_number
      + "," + "modified_name=" +modified_name
      + "," + "card_type=" +card_type
      + "," + "online_batch_div=" +online_batch_div
      + "," + "seq_no=" +seq_no
      + "," + "status=" +status
      + "," + "send_timestamp=" +send_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のPasswordVoucherParamsオブジェクトを作成します。 
   */
  public PasswordVoucherParams() {
    order_request_number_is_modified = true;
    request_code_is_modified = true;
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のPasswordVoucherRowオブジェクトの値を利用してPasswordVoucherParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するPasswordVoucherRowオブジェクト 
   */
  public PasswordVoucherParams( PasswordVoucherRow p_row )
  {
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    acc_open_request_number = p_row.getAccOpenRequestNumber();
    acc_open_request_number_is_set = p_row.getAccOpenRequestNumberIsSet();
    acc_open_request_number_is_modified = p_row.getAccOpenRequestNumberIsModified();
    serial_no = p_row.getSerialNo();
    serial_no_is_set = p_row.getSerialNoIsSet();
    serial_no_is_modified = p_row.getSerialNoIsModified();
    regist_delete_div = p_row.getRegistDeleteDiv();
    regist_delete_div_is_set = p_row.getRegistDeleteDivIsSet();
    regist_delete_div_is_modified = p_row.getRegistDeleteDivIsModified();
    data_class = p_row.getDataClass();
    data_class_is_set = p_row.getDataClassIsSet();
    data_class_is_modified = p_row.getDataClassIsModified();
    voucher_name = p_row.getVoucherName();
    voucher_name_is_set = p_row.getVoucherNameIsSet();
    voucher_name_is_modified = p_row.getVoucherNameIsModified();
    regist_date = p_row.getRegistDate();
    regist_date_is_set = p_row.getRegistDateIsSet();
    regist_date_is_modified = p_row.getRegistDateIsModified();
    password = p_row.getPassword();
    password_is_set = p_row.getPasswordIsSet();
    password_is_modified = p_row.getPasswordIsModified();
    pub_reason_div = p_row.getPubReasonDiv();
    pub_reason_div_is_set = p_row.getPubReasonDivIsSet();
    pub_reason_div_is_modified = p_row.getPubReasonDivIsModified();
    ref_number = p_row.getRefNumber();
    ref_number_is_set = p_row.getRefNumberIsSet();
    ref_number_is_modified = p_row.getRefNumberIsModified();
    modified_name = p_row.getModifiedName();
    modified_name_is_set = p_row.getModifiedNameIsSet();
    modified_name_is_modified = p_row.getModifiedNameIsModified();
    card_type = p_row.getCardType();
    card_type_is_set = p_row.getCardTypeIsSet();
    card_type_is_modified = p_row.getCardTypeIsModified();
    online_batch_div = p_row.getOnlineBatchDiv();
    online_batch_div_is_set = p_row.getOnlineBatchDivIsSet();
    online_batch_div_is_modified = p_row.getOnlineBatchDivIsModified();
    seq_no = p_row.getSeqNo();
    seq_no_is_set = p_row.getSeqNoIsSet();
    seq_no_is_modified = p_row.getSeqNoIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    send_timestamp = p_row.getSendTimestamp();
    send_timestamp_is_set = p_row.getSendTimestampIsSet();
    send_timestamp_is_modified = p_row.getSendTimestampIsModified();
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
      trader_code_is_set = true;
      trader_code_is_modified = true;
      acc_open_request_number_is_set = true;
      acc_open_request_number_is_modified = true;
      serial_no_is_set = true;
      serial_no_is_modified = true;
      regist_delete_div_is_set = true;
      regist_delete_div_is_modified = true;
      data_class_is_set = true;
      data_class_is_modified = true;
      voucher_name_is_set = true;
      voucher_name_is_modified = true;
      regist_date_is_set = true;
      regist_date_is_modified = true;
      password_is_set = true;
      password_is_modified = true;
      pub_reason_div_is_set = true;
      pub_reason_div_is_modified = true;
      ref_number_is_set = true;
      ref_number_is_modified = true;
      modified_name_is_set = true;
      modified_name_is_modified = true;
      card_type_is_set = true;
      card_type_is_modified = true;
      online_batch_div_is_set = true;
      online_batch_div_is_modified = true;
      seq_no_is_set = true;
      seq_no_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      send_timestamp_is_set = true;
      send_timestamp_is_modified = true;
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
    if ( !( other instanceof PasswordVoucherRow ) )
       return false;
    return fieldsEqual( (PasswordVoucherRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のPasswordVoucherRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( PasswordVoucherRow row )
  {
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
        return false;
    }
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
    if ( trader_code == null ) {
      if ( row.getTraderCode() != null )
        return false;
    } else if ( !trader_code.equals( row.getTraderCode() ) ) {
        return false;
    }
    if ( acc_open_request_number == null ) {
      if ( row.getAccOpenRequestNumber() != null )
        return false;
    } else if ( !acc_open_request_number.equals( row.getAccOpenRequestNumber() ) ) {
        return false;
    }
    if ( serial_no == null ) {
      if ( row.getSerialNo() != null )
        return false;
    } else if ( !serial_no.equals( row.getSerialNo() ) ) {
        return false;
    }
    if ( regist_delete_div == null ) {
      if ( row.getRegistDeleteDiv() != null )
        return false;
    } else if ( !regist_delete_div.equals( row.getRegistDeleteDiv() ) ) {
        return false;
    }
    if ( data_class == null ) {
      if ( row.getDataClass() != null )
        return false;
    } else if ( !data_class.equals( row.getDataClass() ) ) {
        return false;
    }
    if ( voucher_name == null ) {
      if ( row.getVoucherName() != null )
        return false;
    } else if ( !voucher_name.equals( row.getVoucherName() ) ) {
        return false;
    }
    if ( regist_date == null ) {
      if ( row.getRegistDate() != null )
        return false;
    } else if ( !regist_date.equals( row.getRegistDate() ) ) {
        return false;
    }
    if ( password == null ) {
      if ( row.getPassword() != null )
        return false;
    } else if ( !password.equals( row.getPassword() ) ) {
        return false;
    }
    if ( pub_reason_div == null ) {
      if ( row.getPubReasonDiv() != null )
        return false;
    } else if ( !pub_reason_div.equals( row.getPubReasonDiv() ) ) {
        return false;
    }
    if ( ref_number == null ) {
      if ( row.getRefNumber() != null )
        return false;
    } else if ( !ref_number.equals( row.getRefNumber() ) ) {
        return false;
    }
    if ( modified_name == null ) {
      if ( row.getModifiedName() != null )
        return false;
    } else if ( !modified_name.equals( row.getModifiedName() ) ) {
        return false;
    }
    if ( card_type == null ) {
      if ( row.getCardType() != null )
        return false;
    } else if ( !card_type.equals( row.getCardType() ) ) {
        return false;
    }
    if ( online_batch_div == null ) {
      if ( row.getOnlineBatchDiv() != null )
        return false;
    } else if ( !online_batch_div.equals( row.getOnlineBatchDiv() ) ) {
        return false;
    }
    if ( seq_no == null ) {
      if ( row.getSeqNo() != null )
        return false;
    } else if ( !seq_no.equals( row.getSeqNo() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( send_timestamp == null ) {
      if ( row.getSendTimestamp() != null )
        return false;
    } else if ( !send_timestamp.equals( row.getSendTimestamp() ) ) {
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
      return  (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (acc_open_request_number!=null? acc_open_request_number.hashCode(): 0) 
        + (serial_no!=null? serial_no.hashCode(): 0) 
        + (regist_delete_div!=null? regist_delete_div.hashCode(): 0) 
        + (data_class!=null? data_class.hashCode(): 0) 
        + (voucher_name!=null? voucher_name.hashCode(): 0) 
        + (regist_date!=null? regist_date.hashCode(): 0) 
        + (password!=null? password.hashCode(): 0) 
        + (pub_reason_div!=null? pub_reason_div.hashCode(): 0) 
        + (ref_number!=null? ref_number.hashCode(): 0) 
        + (modified_name!=null? modified_name.hashCode(): 0) 
        + (card_type!=null? card_type.hashCode(): 0) 
        + (online_batch_div!=null? online_batch_div.hashCode(): 0) 
        + (seq_no!=null? seq_no.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (send_timestamp!=null? send_timestamp.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !acc_open_request_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'acc_open_request_number' must be set before inserting.");
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
		map.put("order_request_number",order_request_number);
		map.put("request_code",request_code);
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		map.put("acc_open_request_number",acc_open_request_number);
		if ( serial_no_is_set )
			map.put("serial_no",serial_no);
		if ( regist_delete_div != null )
			map.put("regist_delete_div",regist_delete_div);
		if ( data_class != null )
			map.put("data_class",data_class);
		if ( voucher_name != null )
			map.put("voucher_name",voucher_name);
		if ( regist_date != null )
			map.put("regist_date",regist_date);
		if ( password != null )
			map.put("password",password);
		if ( pub_reason_div != null )
			map.put("pub_reason_div",pub_reason_div);
		if ( ref_number != null )
			map.put("ref_number",ref_number);
		if ( modified_name != null )
			map.put("modified_name",modified_name);
		if ( card_type != null )
			map.put("card_type",card_type);
		if ( online_batch_div != null )
			map.put("online_batch_div",online_batch_div);
		if ( seq_no != null )
			map.put("seq_no",seq_no);
		if ( status != null )
			map.put("status",status);
		if ( send_timestamp != null )
			map.put("send_timestamp",send_timestamp);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( acc_open_request_number_is_modified )
			map.put("acc_open_request_number",acc_open_request_number);
		if ( serial_no_is_modified )
			map.put("serial_no",serial_no);
		if ( regist_delete_div_is_modified )
			map.put("regist_delete_div",regist_delete_div);
		if ( data_class_is_modified )
			map.put("data_class",data_class);
		if ( voucher_name_is_modified )
			map.put("voucher_name",voucher_name);
		if ( regist_date_is_modified )
			map.put("regist_date",regist_date);
		if ( password_is_modified )
			map.put("password",password);
		if ( pub_reason_div_is_modified )
			map.put("pub_reason_div",pub_reason_div);
		if ( ref_number_is_modified )
			map.put("ref_number",ref_number);
		if ( modified_name_is_modified )
			map.put("modified_name",modified_name);
		if ( card_type_is_modified )
			map.put("card_type",card_type);
		if ( online_batch_div_is_modified )
			map.put("online_batch_div",online_batch_div);
		if ( seq_no_is_modified )
			map.put("seq_no",seq_no);
		if ( status_is_modified )
			map.put("status",status);
		if ( send_timestamp_is_modified )
			map.put("send_timestamp",send_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("trader_code",trader_code);
			if ( acc_open_request_number_is_set )
				map.put("acc_open_request_number",acc_open_request_number);
			if ( serial_no_is_set )
				map.put("serial_no",serial_no);
			map.put("regist_delete_div",regist_delete_div);
			map.put("data_class",data_class);
			map.put("voucher_name",voucher_name);
			map.put("regist_date",regist_date);
			map.put("password",password);
			map.put("pub_reason_div",pub_reason_div);
			map.put("ref_number",ref_number);
			map.put("modified_name",modified_name);
			map.put("card_type",card_type);
			map.put("online_batch_div",online_batch_div);
			map.put("seq_no",seq_no);
			map.put("status",status);
			map.put("send_timestamp",send_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>request_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRequestCode()
  {
    return request_code;
  }


  /** 
   * <em>request_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsSet() {
    return request_code_is_set;
  }


  /** 
   * <em>request_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsModified() {
    return request_code_is_modified;
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
   * <em>trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTraderCode()
  {
    return trader_code;
  }


  /** 
   * <em>trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsSet() {
    return trader_code_is_set;
  }


  /** 
   * <em>trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsModified() {
    return trader_code_is_modified;
  }


  /** 
   * <em>acc_open_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccOpenRequestNumber()
  {
    return acc_open_request_number;
  }


  /** 
   * <em>acc_open_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenRequestNumberIsSet() {
    return acc_open_request_number_is_set;
  }


  /** 
   * <em>acc_open_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenRequestNumberIsModified() {
    return acc_open_request_number_is_modified;
  }


  /** 
   * <em>serial_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSerialNo()
  {
    return serial_no;
  }


  /** 
   * <em>serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerialNoIsSet() {
    return serial_no_is_set;
  }


  /** 
   * <em>serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerialNoIsModified() {
    return serial_no_is_modified;
  }


  /** 
   * <em>regist_delete_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRegistDeleteDiv()
  {
    return regist_delete_div;
  }


  /** 
   * <em>regist_delete_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistDeleteDivIsSet() {
    return regist_delete_div_is_set;
  }


  /** 
   * <em>regist_delete_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistDeleteDivIsModified() {
    return regist_delete_div_is_modified;
  }


  /** 
   * <em>data_class</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDataClass()
  {
    return data_class;
  }


  /** 
   * <em>data_class</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataClassIsSet() {
    return data_class_is_set;
  }


  /** 
   * <em>data_class</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataClassIsModified() {
    return data_class_is_modified;
  }


  /** 
   * <em>voucher_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getVoucherName()
  {
    return voucher_name;
  }


  /** 
   * <em>voucher_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVoucherNameIsSet() {
    return voucher_name_is_set;
  }


  /** 
   * <em>voucher_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVoucherNameIsModified() {
    return voucher_name_is_modified;
  }


  /** 
   * <em>regist_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRegistDate()
  {
    return regist_date;
  }


  /** 
   * <em>regist_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistDateIsSet() {
    return regist_date_is_set;
  }


  /** 
   * <em>regist_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistDateIsModified() {
    return regist_date_is_modified;
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
   * <em>pub_reason_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPubReasonDiv()
  {
    return pub_reason_div;
  }


  /** 
   * <em>pub_reason_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPubReasonDivIsSet() {
    return pub_reason_div_is_set;
  }


  /** 
   * <em>pub_reason_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPubReasonDivIsModified() {
    return pub_reason_div_is_modified;
  }


  /** 
   * <em>ref_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRefNumber()
  {
    return ref_number;
  }


  /** 
   * <em>ref_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRefNumberIsSet() {
    return ref_number_is_set;
  }


  /** 
   * <em>ref_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRefNumberIsModified() {
    return ref_number_is_modified;
  }


  /** 
   * <em>modified_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getModifiedName()
  {
    return modified_name;
  }


  /** 
   * <em>modified_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedNameIsSet() {
    return modified_name_is_set;
  }


  /** 
   * <em>modified_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedNameIsModified() {
    return modified_name_is_modified;
  }


  /** 
   * <em>card_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCardType()
  {
    return card_type;
  }


  /** 
   * <em>card_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCardTypeIsSet() {
    return card_type_is_set;
  }


  /** 
   * <em>card_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCardTypeIsModified() {
    return card_type_is_modified;
  }


  /** 
   * <em>online_batch_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOnlineBatchDiv()
  {
    return online_batch_div;
  }


  /** 
   * <em>online_batch_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlineBatchDivIsSet() {
    return online_batch_div_is_set;
  }


  /** 
   * <em>online_batch_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlineBatchDivIsModified() {
    return online_batch_div_is_modified;
  }


  /** 
   * <em>seq_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSeqNo()
  {
    return seq_no;
  }


  /** 
   * <em>seq_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSeqNoIsSet() {
    return seq_no_is_set;
  }


  /** 
   * <em>seq_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSeqNoIsModified() {
    return seq_no_is_modified;
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
   * <em>send_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSendTimestamp()
  {
    return send_timestamp;
  }


  /** 
   * <em>send_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendTimestampIsSet() {
    return send_timestamp_is_set;
  }


  /** 
   * <em>send_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendTimestampIsModified() {
    return send_timestamp_is_modified;
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
    return new PasswordVoucherPK(order_request_number, request_code, institution_code, branch_code, account_code);
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
   * <em>request_code</em>カラムの値を設定します。 
   *
   * @@param p_requestCode <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setRequestCode( String p_requestCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_code = p_requestCode;
    request_code_is_set = true;
    request_code_is_modified = true;
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
   * <em>trader_code</em>カラムの値を設定します。 
   *
   * @@param p_traderCode <em>trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setTraderCode( String p_traderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_code = p_traderCode;
    trader_code_is_set = true;
    trader_code_is_modified = true;
  }


  /** 
   * <em>acc_open_request_number</em>カラムの値を設定します。 
   *
   * @@param p_accOpenRequestNumber <em>acc_open_request_number</em>カラムの値をあらわす13桁以下のString値 
   */
  public final void setAccOpenRequestNumber( String p_accOpenRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_request_number = p_accOpenRequestNumber;
    acc_open_request_number_is_set = true;
    acc_open_request_number_is_modified = true;
  }


  /** 
   * <em>serial_no</em>カラムの値を設定します。 
   *
   * @@param p_serialNo <em>serial_no</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setSerialNo( String p_serialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    serial_no = p_serialNo;
    serial_no_is_set = true;
    serial_no_is_modified = true;
  }


  /** 
   * <em>regist_delete_div</em>カラムの値を設定します。 
   *
   * @@param p_registDeleteDiv <em>regist_delete_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRegistDeleteDiv( String p_registDeleteDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_delete_div = p_registDeleteDiv;
    regist_delete_div_is_set = true;
    regist_delete_div_is_modified = true;
  }


  /** 
   * <em>data_class</em>カラムの値を設定します。 
   *
   * @@param p_dataClass <em>data_class</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setDataClass( String p_dataClass )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_class = p_dataClass;
    data_class_is_set = true;
    data_class_is_modified = true;
  }


  /** 
   * <em>voucher_name</em>カラムの値を設定します。 
   *
   * @@param p_voucherName <em>voucher_name</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setVoucherName( String p_voucherName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    voucher_name = p_voucherName;
    voucher_name_is_set = true;
    voucher_name_is_modified = true;
  }


  /** 
   * <em>regist_date</em>カラムの値を設定します。 
   *
   * @@param p_registDate <em>regist_date</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setRegistDate( String p_registDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_date = p_registDate;
    regist_date_is_set = true;
    regist_date_is_modified = true;
  }


  /** 
   * <em>password</em>カラムの値を設定します。 
   *
   * @@param p_password <em>password</em>カラムの値をあらわす48桁以下のString値 
   */
  public final void setPassword( String p_password )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    password = p_password;
    password_is_set = true;
    password_is_modified = true;
  }


  /** 
   * <em>pub_reason_div</em>カラムの値を設定します。 
   *
   * @@param p_pubReasonDiv <em>pub_reason_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPubReasonDiv( String p_pubReasonDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pub_reason_div = p_pubReasonDiv;
    pub_reason_div_is_set = true;
    pub_reason_div_is_modified = true;
  }


  /** 
   * <em>ref_number</em>カラムの値を設定します。 
   *
   * @@param p_refNumber <em>ref_number</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setRefNumber( String p_refNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ref_number = p_refNumber;
    ref_number_is_set = true;
    ref_number_is_modified = true;
  }


  /** 
   * <em>modified_name</em>カラムの値を設定します。 
   *
   * @@param p_modifiedName <em>modified_name</em>カラムの値をあらわす19桁以下のString値 
   */
  public final void setModifiedName( String p_modifiedName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_name = p_modifiedName;
    modified_name_is_set = true;
    modified_name_is_modified = true;
  }


  /** 
   * <em>card_type</em>カラムの値を設定します。 
   *
   * @@param p_cardType <em>card_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCardType( String p_cardType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    card_type = p_cardType;
    card_type_is_set = true;
    card_type_is_modified = true;
  }


  /** 
   * <em>online_batch_div</em>カラムの値を設定します。 
   *
   * @@param p_onlineBatchDiv <em>online_batch_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOnlineBatchDiv( String p_onlineBatchDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    online_batch_div = p_onlineBatchDiv;
    online_batch_div_is_set = true;
    online_batch_div_is_modified = true;
  }


  /** 
   * <em>seq_no</em>カラムの値を設定します。 
   *
   * @@param p_seqNo <em>seq_no</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setSeqNo( String p_seqNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    seq_no = p_seqNo;
    seq_no_is_set = true;
    seq_no_is_modified = true;
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
   * <em>send_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_sendTimestamp <em>send_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSendTimestamp( java.sql.Timestamp p_sendTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_timestamp = p_sendTimestamp;
    send_timestamp_is_set = true;
    send_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>send_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSendTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    send_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    send_timestamp_is_set = true;
    send_timestamp_is_modified = true;
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
                else if ( name.equals("acc_open_request_number") ) {
                    return this.acc_open_request_number;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("card_type") ) {
                    return this.card_type;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("data_class") ) {
                    return this.data_class;
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
            case 'm':
                if ( name.equals("modified_name") ) {
                    return this.modified_name;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("online_batch_div") ) {
                    return this.online_batch_div;
                }
                break;
            case 'p':
                if ( name.equals("password") ) {
                    return this.password;
                }
                else if ( name.equals("pub_reason_div") ) {
                    return this.pub_reason_div;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("regist_delete_div") ) {
                    return this.regist_delete_div;
                }
                else if ( name.equals("regist_date") ) {
                    return this.regist_date;
                }
                else if ( name.equals("ref_number") ) {
                    return this.ref_number;
                }
                break;
            case 's':
                if ( name.equals("serial_no") ) {
                    return this.serial_no;
                }
                else if ( name.equals("seq_no") ) {
                    return this.seq_no;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                else if ( name.equals("send_timestamp") ) {
                    return this.send_timestamp;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                break;
            case 'v':
                if ( name.equals("voucher_name") ) {
                    return this.voucher_name;
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
                else if ( name.equals("acc_open_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc_open_request_number' must be String: '"+value+"' is not." );
                    this.acc_open_request_number = (String) value;
                    if (this.acc_open_request_number_is_set)
                        this.acc_open_request_number_is_modified = true;
                    this.acc_open_request_number_is_set = true;
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
                if ( name.equals("card_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'card_type' must be String: '"+value+"' is not." );
                    this.card_type = (String) value;
                    if (this.card_type_is_set)
                        this.card_type_is_modified = true;
                    this.card_type_is_set = true;
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
                if ( name.equals("data_class") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'data_class' must be String: '"+value+"' is not." );
                    this.data_class = (String) value;
                    if (this.data_class_is_set)
                        this.data_class_is_modified = true;
                    this.data_class_is_set = true;
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
            case 'm':
                if ( name.equals("modified_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'modified_name' must be String: '"+value+"' is not." );
                    this.modified_name = (String) value;
                    if (this.modified_name_is_set)
                        this.modified_name_is_modified = true;
                    this.modified_name_is_set = true;
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
                else if ( name.equals("online_batch_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'online_batch_div' must be String: '"+value+"' is not." );
                    this.online_batch_div = (String) value;
                    if (this.online_batch_div_is_set)
                        this.online_batch_div_is_modified = true;
                    this.online_batch_div_is_set = true;
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
                else if ( name.equals("pub_reason_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pub_reason_div' must be String: '"+value+"' is not." );
                    this.pub_reason_div = (String) value;
                    if (this.pub_reason_div_is_set)
                        this.pub_reason_div_is_modified = true;
                    this.pub_reason_div_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_code' must be String: '"+value+"' is not." );
                    this.request_code = (String) value;
                    if (this.request_code_is_set)
                        this.request_code_is_modified = true;
                    this.request_code_is_set = true;
                    return;
                }
                else if ( name.equals("regist_delete_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'regist_delete_div' must be String: '"+value+"' is not." );
                    this.regist_delete_div = (String) value;
                    if (this.regist_delete_div_is_set)
                        this.regist_delete_div_is_modified = true;
                    this.regist_delete_div_is_set = true;
                    return;
                }
                else if ( name.equals("regist_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'regist_date' must be String: '"+value+"' is not." );
                    this.regist_date = (String) value;
                    if (this.regist_date_is_set)
                        this.regist_date_is_modified = true;
                    this.regist_date_is_set = true;
                    return;
                }
                else if ( name.equals("ref_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ref_number' must be String: '"+value+"' is not." );
                    this.ref_number = (String) value;
                    if (this.ref_number_is_set)
                        this.ref_number_is_modified = true;
                    this.ref_number_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("serial_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'serial_no' must be String: '"+value+"' is not." );
                    this.serial_no = (String) value;
                    if (this.serial_no_is_set)
                        this.serial_no_is_modified = true;
                    this.serial_no_is_set = true;
                    return;
                }
                else if ( name.equals("seq_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'seq_no' must be String: '"+value+"' is not." );
                    this.seq_no = (String) value;
                    if (this.seq_no_is_set)
                        this.seq_no_is_modified = true;
                    this.seq_no_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                else if ( name.equals("send_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'send_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.send_timestamp = (java.sql.Timestamp) value;
                    if (this.send_timestamp_is_set)
                        this.send_timestamp_is_modified = true;
                    this.send_timestamp_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trader_code' must be String: '"+value+"' is not." );
                    this.trader_code = (String) value;
                    if (this.trader_code_is_set)
                        this.trader_code_is_modified = true;
                    this.trader_code_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("voucher_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'voucher_name' must be String: '"+value+"' is not." );
                    this.voucher_name = (String) value;
                    if (this.voucher_name_is_set)
                        this.voucher_name_is_modified = true;
                    this.voucher_name_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
