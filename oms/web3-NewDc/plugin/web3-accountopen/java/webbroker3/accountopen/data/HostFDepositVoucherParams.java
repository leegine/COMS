head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.15.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostFDepositVoucherParams.java;


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
 * host_f_deposit_voucherテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostFDepositVoucherRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostFDepositVoucherParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostFDepositVoucherParams}が{@@link HostFDepositVoucherRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostFDepositVoucherPK 
 * @@see HostFDepositVoucherRow 
 */
public class HostFDepositVoucherParams extends Params implements HostFDepositVoucherRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_f_deposit_voucher";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostFDepositVoucherRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostFDepositVoucherRow.TYPE;
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
   * <em>regist_div</em>カラムの値 
   */
  public  String  regist_div;

  /** 
   * <em>currency_code</em>カラムの値 
   */
  public  String  currency_code;

  /** 
   * <em>bank_code</em>カラムの値 
   */
  public  String  bank_code;

  /** 
   * <em>bank_branch_code</em>カラムの値 
   */
  public  String  bank_branch_code;

  /** 
   * <em>account_no</em>カラムの値 
   */
  public  String  account_no;

  /** 
   * <em>account_name</em>カラムの値 
   */
  public  String  account_name;

  /** 
   * <em>ammount</em>カラムの値 
   */
  public  String  ammount;

  /** 
   * <em>fee</em>カラムの値 
   */
  public  String  fee;

  /** 
   * <em>account_name_eng</em>カラムの値 
   */
  public  String  account_name_eng;

  /** 
   * <em>bank_name</em>カラムの値 
   */
  public  String  bank_name;

  /** 
   * <em>bank_branch_name</em>カラムの値 
   */
  public  String  bank_branch_name;

  /** 
   * <em>extra</em>カラムの値 
   */
  public  String  extra;

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
  private boolean regist_div_is_set = false;
  private boolean regist_div_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean bank_code_is_set = false;
  private boolean bank_code_is_modified = false;
  private boolean bank_branch_code_is_set = false;
  private boolean bank_branch_code_is_modified = false;
  private boolean account_no_is_set = false;
  private boolean account_no_is_modified = false;
  private boolean account_name_is_set = false;
  private boolean account_name_is_modified = false;
  private boolean ammount_is_set = false;
  private boolean ammount_is_modified = false;
  private boolean fee_is_set = false;
  private boolean fee_is_modified = false;
  private boolean account_name_eng_is_set = false;
  private boolean account_name_eng_is_modified = false;
  private boolean bank_name_is_set = false;
  private boolean bank_name_is_modified = false;
  private boolean bank_branch_name_is_set = false;
  private boolean bank_branch_name_is_modified = false;
  private boolean extra_is_set = false;
  private boolean extra_is_modified = false;
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
      + "," + "regist_div=" +regist_div
      + "," + "currency_code=" +currency_code
      + "," + "bank_code=" +bank_code
      + "," + "bank_branch_code=" +bank_branch_code
      + "," + "account_no=" +account_no
      + "," + "account_name=" +account_name
      + "," + "ammount=" +ammount
      + "," + "fee=" +fee
      + "," + "account_name_eng=" +account_name_eng
      + "," + "bank_name=" +bank_name
      + "," + "bank_branch_name=" +bank_branch_name
      + "," + "extra=" +extra
      + "," + "status=" +status
      + "," + "send_timestamp=" +send_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のHostFDepositVoucherParamsオブジェクトを作成します。 
   */
  public HostFDepositVoucherParams() {
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
   * 指定のHostFDepositVoucherRowオブジェクトの値を利用してHostFDepositVoucherParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostFDepositVoucherRowオブジェクト 
   */
  public HostFDepositVoucherParams( HostFDepositVoucherRow p_row )
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
    regist_div = p_row.getRegistDiv();
    regist_div_is_set = p_row.getRegistDivIsSet();
    regist_div_is_modified = p_row.getRegistDivIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    bank_code = p_row.getBankCode();
    bank_code_is_set = p_row.getBankCodeIsSet();
    bank_code_is_modified = p_row.getBankCodeIsModified();
    bank_branch_code = p_row.getBankBranchCode();
    bank_branch_code_is_set = p_row.getBankBranchCodeIsSet();
    bank_branch_code_is_modified = p_row.getBankBranchCodeIsModified();
    account_no = p_row.getAccountNo();
    account_no_is_set = p_row.getAccountNoIsSet();
    account_no_is_modified = p_row.getAccountNoIsModified();
    account_name = p_row.getAccountName();
    account_name_is_set = p_row.getAccountNameIsSet();
    account_name_is_modified = p_row.getAccountNameIsModified();
    ammount = p_row.getAmmount();
    ammount_is_set = p_row.getAmmountIsSet();
    ammount_is_modified = p_row.getAmmountIsModified();
    fee = p_row.getFee();
    fee_is_set = p_row.getFeeIsSet();
    fee_is_modified = p_row.getFeeIsModified();
    account_name_eng = p_row.getAccountNameEng();
    account_name_eng_is_set = p_row.getAccountNameEngIsSet();
    account_name_eng_is_modified = p_row.getAccountNameEngIsModified();
    bank_name = p_row.getBankName();
    bank_name_is_set = p_row.getBankNameIsSet();
    bank_name_is_modified = p_row.getBankNameIsModified();
    bank_branch_name = p_row.getBankBranchName();
    bank_branch_name_is_set = p_row.getBankBranchNameIsSet();
    bank_branch_name_is_modified = p_row.getBankBranchNameIsModified();
    extra = p_row.getExtra();
    extra_is_set = p_row.getExtraIsSet();
    extra_is_modified = p_row.getExtraIsModified();
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
      regist_div_is_set = true;
      regist_div_is_modified = true;
      currency_code_is_set = true;
      currency_code_is_modified = true;
      bank_code_is_set = true;
      bank_code_is_modified = true;
      bank_branch_code_is_set = true;
      bank_branch_code_is_modified = true;
      account_no_is_set = true;
      account_no_is_modified = true;
      account_name_is_set = true;
      account_name_is_modified = true;
      ammount_is_set = true;
      ammount_is_modified = true;
      fee_is_set = true;
      fee_is_modified = true;
      account_name_eng_is_set = true;
      account_name_eng_is_modified = true;
      bank_name_is_set = true;
      bank_name_is_modified = true;
      bank_branch_name_is_set = true;
      bank_branch_name_is_modified = true;
      extra_is_set = true;
      extra_is_modified = true;
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
    if ( !( other instanceof HostFDepositVoucherRow ) )
       return false;
    return fieldsEqual( (HostFDepositVoucherRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostFDepositVoucherRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostFDepositVoucherRow row )
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
    if ( regist_div == null ) {
      if ( row.getRegistDiv() != null )
        return false;
    } else if ( !regist_div.equals( row.getRegistDiv() ) ) {
        return false;
    }
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( bank_code == null ) {
      if ( row.getBankCode() != null )
        return false;
    } else if ( !bank_code.equals( row.getBankCode() ) ) {
        return false;
    }
    if ( bank_branch_code == null ) {
      if ( row.getBankBranchCode() != null )
        return false;
    } else if ( !bank_branch_code.equals( row.getBankBranchCode() ) ) {
        return false;
    }
    if ( account_no == null ) {
      if ( row.getAccountNo() != null )
        return false;
    } else if ( !account_no.equals( row.getAccountNo() ) ) {
        return false;
    }
    if ( account_name == null ) {
      if ( row.getAccountName() != null )
        return false;
    } else if ( !account_name.equals( row.getAccountName() ) ) {
        return false;
    }
    if ( ammount == null ) {
      if ( row.getAmmount() != null )
        return false;
    } else if ( !ammount.equals( row.getAmmount() ) ) {
        return false;
    }
    if ( fee == null ) {
      if ( row.getFee() != null )
        return false;
    } else if ( !fee.equals( row.getFee() ) ) {
        return false;
    }
    if ( account_name_eng == null ) {
      if ( row.getAccountNameEng() != null )
        return false;
    } else if ( !account_name_eng.equals( row.getAccountNameEng() ) ) {
        return false;
    }
    if ( bank_name == null ) {
      if ( row.getBankName() != null )
        return false;
    } else if ( !bank_name.equals( row.getBankName() ) ) {
        return false;
    }
    if ( bank_branch_name == null ) {
      if ( row.getBankBranchName() != null )
        return false;
    } else if ( !bank_branch_name.equals( row.getBankBranchName() ) ) {
        return false;
    }
    if ( extra == null ) {
      if ( row.getExtra() != null )
        return false;
    } else if ( !extra.equals( row.getExtra() ) ) {
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
        + (regist_div!=null? regist_div.hashCode(): 0) 
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (bank_code!=null? bank_code.hashCode(): 0) 
        + (bank_branch_code!=null? bank_branch_code.hashCode(): 0) 
        + (account_no!=null? account_no.hashCode(): 0) 
        + (account_name!=null? account_name.hashCode(): 0) 
        + (ammount!=null? ammount.hashCode(): 0) 
        + (fee!=null? fee.hashCode(): 0) 
        + (account_name_eng!=null? account_name_eng.hashCode(): 0) 
        + (bank_name!=null? bank_name.hashCode(): 0) 
        + (bank_branch_name!=null? bank_branch_name.hashCode(): 0) 
        + (extra!=null? extra.hashCode(): 0) 
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
		if ( !serial_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'serial_no' must be set before inserting.");
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
		map.put("serial_no",serial_no);
		if ( regist_div != null )
			map.put("regist_div",regist_div);
		if ( currency_code != null )
			map.put("currency_code",currency_code);
		if ( bank_code != null )
			map.put("bank_code",bank_code);
		if ( bank_branch_code != null )
			map.put("bank_branch_code",bank_branch_code);
		if ( account_no != null )
			map.put("account_no",account_no);
		if ( account_name != null )
			map.put("account_name",account_name);
		if ( ammount != null )
			map.put("ammount",ammount);
		if ( fee != null )
			map.put("fee",fee);
		if ( account_name_eng != null )
			map.put("account_name_eng",account_name_eng);
		if ( bank_name != null )
			map.put("bank_name",bank_name);
		if ( bank_branch_name != null )
			map.put("bank_branch_name",bank_branch_name);
		if ( extra != null )
			map.put("extra",extra);
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
		if ( regist_div_is_modified )
			map.put("regist_div",regist_div);
		if ( currency_code_is_modified )
			map.put("currency_code",currency_code);
		if ( bank_code_is_modified )
			map.put("bank_code",bank_code);
		if ( bank_branch_code_is_modified )
			map.put("bank_branch_code",bank_branch_code);
		if ( account_no_is_modified )
			map.put("account_no",account_no);
		if ( account_name_is_modified )
			map.put("account_name",account_name);
		if ( ammount_is_modified )
			map.put("ammount",ammount);
		if ( fee_is_modified )
			map.put("fee",fee);
		if ( account_name_eng_is_modified )
			map.put("account_name_eng",account_name_eng);
		if ( bank_name_is_modified )
			map.put("bank_name",bank_name);
		if ( bank_branch_name_is_modified )
			map.put("bank_branch_name",bank_branch_name);
		if ( extra_is_modified )
			map.put("extra",extra);
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
			map.put("regist_div",regist_div);
			map.put("currency_code",currency_code);
			map.put("bank_code",bank_code);
			map.put("bank_branch_code",bank_branch_code);
			map.put("account_no",account_no);
			map.put("account_name",account_name);
			map.put("ammount",ammount);
			map.put("fee",fee);
			map.put("account_name_eng",account_name_eng);
			map.put("bank_name",bank_name);
			map.put("bank_branch_name",bank_branch_name);
			map.put("extra",extra);
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
   * <em>currency_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCurrencyCode()
  {
    return currency_code;
  }


  /** 
   * <em>currency_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsSet() {
    return currency_code_is_set;
  }


  /** 
   * <em>currency_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsModified() {
    return currency_code_is_modified;
  }


  /** 
   * <em>bank_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBankCode()
  {
    return bank_code;
  }


  /** 
   * <em>bank_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankCodeIsSet() {
    return bank_code_is_set;
  }


  /** 
   * <em>bank_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankCodeIsModified() {
    return bank_code_is_modified;
  }


  /** 
   * <em>bank_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBankBranchCode()
  {
    return bank_branch_code;
  }


  /** 
   * <em>bank_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankBranchCodeIsSet() {
    return bank_branch_code_is_set;
  }


  /** 
   * <em>bank_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankBranchCodeIsModified() {
    return bank_branch_code_is_modified;
  }


  /** 
   * <em>account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountNo()
  {
    return account_no;
  }


  /** 
   * <em>account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNoIsSet() {
    return account_no_is_set;
  }


  /** 
   * <em>account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNoIsModified() {
    return account_no_is_modified;
  }


  /** 
   * <em>account_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountName()
  {
    return account_name;
  }


  /** 
   * <em>account_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameIsSet() {
    return account_name_is_set;
  }


  /** 
   * <em>account_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameIsModified() {
    return account_name_is_modified;
  }


  /** 
   * <em>ammount</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAmmount()
  {
    return ammount;
  }


  /** 
   * <em>ammount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmmountIsSet() {
    return ammount_is_set;
  }


  /** 
   * <em>ammount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmmountIsModified() {
    return ammount_is_modified;
  }


  /** 
   * <em>fee</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFee()
  {
    return fee;
  }


  /** 
   * <em>fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeeIsSet() {
    return fee_is_set;
  }


  /** 
   * <em>fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeeIsModified() {
    return fee_is_modified;
  }


  /** 
   * <em>account_name_eng</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountNameEng()
  {
    return account_name_eng;
  }


  /** 
   * <em>account_name_eng</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameEngIsSet() {
    return account_name_eng_is_set;
  }


  /** 
   * <em>account_name_eng</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameEngIsModified() {
    return account_name_eng_is_modified;
  }


  /** 
   * <em>bank_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBankName()
  {
    return bank_name;
  }


  /** 
   * <em>bank_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankNameIsSet() {
    return bank_name_is_set;
  }


  /** 
   * <em>bank_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankNameIsModified() {
    return bank_name_is_modified;
  }


  /** 
   * <em>bank_branch_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBankBranchName()
  {
    return bank_branch_name;
  }


  /** 
   * <em>bank_branch_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankBranchNameIsSet() {
    return bank_branch_name_is_set;
  }


  /** 
   * <em>bank_branch_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankBranchNameIsModified() {
    return bank_branch_name_is_modified;
  }


  /** 
   * <em>extra</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtra()
  {
    return extra;
  }


  /** 
   * <em>extra</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtraIsSet() {
    return extra_is_set;
  }


  /** 
   * <em>extra</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtraIsModified() {
    return extra_is_modified;
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
    return new HostFDepositVoucherPK(order_request_number, request_code, institution_code, branch_code, account_code);
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
   * <em>currency_code</em>カラムの値を設定します。 
   *
   * @@param p_currencyCode <em>currency_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>bank_code</em>カラムの値を設定します。 
   *
   * @@param p_bankCode <em>bank_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setBankCode( String p_bankCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bank_code = p_bankCode;
    bank_code_is_set = true;
    bank_code_is_modified = true;
  }


  /** 
   * <em>bank_branch_code</em>カラムの値を設定します。 
   *
   * @@param p_bankBranchCode <em>bank_branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBankBranchCode( String p_bankBranchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bank_branch_code = p_bankBranchCode;
    bank_branch_code_is_set = true;
    bank_branch_code_is_modified = true;
  }


  /** 
   * <em>account_no</em>カラムの値を設定します。 
   *
   * @@param p_accountNo <em>account_no</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setAccountNo( String p_accountNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_no = p_accountNo;
    account_no_is_set = true;
    account_no_is_modified = true;
  }


  /** 
   * <em>account_name</em>カラムの値を設定します。 
   *
   * @@param p_accountName <em>account_name</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setAccountName( String p_accountName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_name = p_accountName;
    account_name_is_set = true;
    account_name_is_modified = true;
  }


  /** 
   * <em>ammount</em>カラムの値を設定します。 
   *
   * @@param p_ammount <em>ammount</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAmmount( String p_ammount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ammount = p_ammount;
    ammount_is_set = true;
    ammount_is_modified = true;
  }


  /** 
   * <em>fee</em>カラムの値を設定します。 
   *
   * @@param p_fee <em>fee</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFee( String p_fee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fee = p_fee;
    fee_is_set = true;
    fee_is_modified = true;
  }


  /** 
   * <em>account_name_eng</em>カラムの値を設定します。 
   *
   * @@param p_accountNameEng <em>account_name_eng</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setAccountNameEng( String p_accountNameEng )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_name_eng = p_accountNameEng;
    account_name_eng_is_set = true;
    account_name_eng_is_modified = true;
  }


  /** 
   * <em>bank_name</em>カラムの値を設定します。 
   *
   * @@param p_bankName <em>bank_name</em>カラムの値をあらわす32桁以下のString値 
   */
  public final void setBankName( String p_bankName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bank_name = p_bankName;
    bank_name_is_set = true;
    bank_name_is_modified = true;
  }


  /** 
   * <em>bank_branch_name</em>カラムの値を設定します。 
   *
   * @@param p_bankBranchName <em>bank_branch_name</em>カラムの値をあらわす32桁以下のString値 
   */
  public final void setBankBranchName( String p_bankBranchName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bank_branch_name = p_bankBranchName;
    bank_branch_name_is_set = true;
    bank_branch_name_is_modified = true;
  }


  /** 
   * <em>extra</em>カラムの値を設定します。 
   *
   * @@param p_extra <em>extra</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setExtra( String p_extra )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    extra = p_extra;
    extra_is_set = true;
    extra_is_modified = true;
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
                else if ( name.equals("account_no") ) {
                    return this.account_no;
                }
                else if ( name.equals("account_name") ) {
                    return this.account_name;
                }
                else if ( name.equals("ammount") ) {
                    return this.ammount;
                }
                else if ( name.equals("account_name_eng") ) {
                    return this.account_name_eng;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("bank_code") ) {
                    return this.bank_code;
                }
                else if ( name.equals("bank_branch_code") ) {
                    return this.bank_branch_code;
                }
                else if ( name.equals("bank_name") ) {
                    return this.bank_name;
                }
                else if ( name.equals("bank_branch_name") ) {
                    return this.bank_branch_name;
                }
                break;
            case 'c':
                if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("extra") ) {
                    return this.extra;
                }
                break;
            case 'f':
                if ( name.equals("fee") ) {
                    return this.fee;
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
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("regist_div") ) {
                    return this.regist_div;
                }
                break;
            case 's':
                if ( name.equals("serial_no") ) {
                    return this.serial_no;
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
                else if ( name.equals("account_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_no' must be String: '"+value+"' is not." );
                    this.account_no = (String) value;
                    if (this.account_no_is_set)
                        this.account_no_is_modified = true;
                    this.account_no_is_set = true;
                    return;
                }
                else if ( name.equals("account_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_name' must be String: '"+value+"' is not." );
                    this.account_name = (String) value;
                    if (this.account_name_is_set)
                        this.account_name_is_modified = true;
                    this.account_name_is_set = true;
                    return;
                }
                else if ( name.equals("ammount") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ammount' must be String: '"+value+"' is not." );
                    this.ammount = (String) value;
                    if (this.ammount_is_set)
                        this.ammount_is_modified = true;
                    this.ammount_is_set = true;
                    return;
                }
                else if ( name.equals("account_name_eng") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_name_eng' must be String: '"+value+"' is not." );
                    this.account_name_eng = (String) value;
                    if (this.account_name_eng_is_set)
                        this.account_name_eng_is_modified = true;
                    this.account_name_eng_is_set = true;
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
                else if ( name.equals("bank_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bank_code' must be String: '"+value+"' is not." );
                    this.bank_code = (String) value;
                    if (this.bank_code_is_set)
                        this.bank_code_is_modified = true;
                    this.bank_code_is_set = true;
                    return;
                }
                else if ( name.equals("bank_branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bank_branch_code' must be String: '"+value+"' is not." );
                    this.bank_branch_code = (String) value;
                    if (this.bank_branch_code_is_set)
                        this.bank_branch_code_is_modified = true;
                    this.bank_branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("bank_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bank_name' must be String: '"+value+"' is not." );
                    this.bank_name = (String) value;
                    if (this.bank_name_is_set)
                        this.bank_name_is_modified = true;
                    this.bank_name_is_set = true;
                    return;
                }
                else if ( name.equals("bank_branch_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bank_branch_name' must be String: '"+value+"' is not." );
                    this.bank_branch_name = (String) value;
                    if (this.bank_branch_name_is_set)
                        this.bank_branch_name_is_modified = true;
                    this.bank_branch_name_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("currency_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_code' must be String: '"+value+"' is not." );
                    this.currency_code = (String) value;
                    if (this.currency_code_is_set)
                        this.currency_code_is_modified = true;
                    this.currency_code_is_set = true;
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
            case 'e':
                if ( name.equals("extra") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'extra' must be String: '"+value+"' is not." );
                    this.extra = (String) value;
                    if (this.extra_is_set)
                        this.extra_is_modified = true;
                    this.extra_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fee") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fee' must be String: '"+value+"' is not." );
                    this.fee = (String) value;
                    if (this.fee_is_set)
                        this.fee_is_modified = true;
                    this.fee_is_set = true;
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
                else if ( name.equals("regist_div") ) {
                    if ( value != null )
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
                if ( name.equals("serial_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'serial_no' must be String: '"+value+"' is not." );
                    this.serial_no = (String) value;
                    if (this.serial_no_is_set)
                        this.serial_no_is_modified = true;
                    this.serial_no_is_set = true;
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
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
