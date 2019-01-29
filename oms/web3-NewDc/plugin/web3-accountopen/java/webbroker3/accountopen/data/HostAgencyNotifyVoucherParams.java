head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.17.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostAgencyNotifyVoucherParams.java;


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
 * host_agency_notify_voucherテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostAgencyNotifyVoucherRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostAgencyNotifyVoucherParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostAgencyNotifyVoucherParams}が{@@link HostAgencyNotifyVoucherRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostAgencyNotifyVoucherPK 
 * @@see HostAgencyNotifyVoucherRow 
 */
public class HostAgencyNotifyVoucherParams extends Params implements HostAgencyNotifyVoucherRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_agency_notify_voucher";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostAgencyNotifyVoucherRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostAgencyNotifyVoucherRow.TYPE;
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
   * <em>account_name_kana1</em>カラムの値 
   */
  public  String  account_name_kana1;

  /** 
   * <em>account_name1</em>カラムの値 
   */
  public  String  account_name1;

  /** 
   * <em>zip_code1</em>カラムの値 
   */
  public  String  zip_code1;

  /** 
   * <em>zip_code2</em>カラムの値 
   */
  public  String  zip_code2;

  /** 
   * <em>address_line1</em>カラムの値 
   */
  public  String  address_line1;

  /** 
   * <em>represent_post</em>カラムの値 
   */
  public  String  represent_post;

  /** 
   * <em>represent_name_kana1</em>カラムの値 
   */
  public  String  represent_name_kana1;

  /** 
   * <em>represent_name1</em>カラムの値 
   */
  public  String  represent_name1;

  /** 
   * <em>receipt_kana</em>カラムの値 
   */
  public  String  receipt_kana;

  /** 
   * <em>receipt_name1</em>カラムの値 
   */
  public  String  receipt_name1;

  /** 
   * <em>receipt_fin_institution</em>カラムの値 
   */
  public  String  receipt_fin_institution;

  /** 
   * <em>receipt_fin_branch</em>カラムの値 
   */
  public  String  receipt_fin_branch;

  /** 
   * <em>receipt_fin_acc_type</em>カラムの値 
   */
  public  String  receipt_fin_acc_type;

  /** 
   * <em>receipt_fin_acc_no</em>カラムの値 
   */
  public  String  receipt_fin_acc_no;

  /** 
   * <em>receipt_fin_acc_div</em>カラムの値 
   */
  public  String  receipt_fin_acc_div;

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
  private boolean account_name_kana1_is_set = false;
  private boolean account_name_kana1_is_modified = false;
  private boolean account_name1_is_set = false;
  private boolean account_name1_is_modified = false;
  private boolean zip_code1_is_set = false;
  private boolean zip_code1_is_modified = false;
  private boolean zip_code2_is_set = false;
  private boolean zip_code2_is_modified = false;
  private boolean address_line1_is_set = false;
  private boolean address_line1_is_modified = false;
  private boolean represent_post_is_set = false;
  private boolean represent_post_is_modified = false;
  private boolean represent_name_kana1_is_set = false;
  private boolean represent_name_kana1_is_modified = false;
  private boolean represent_name1_is_set = false;
  private boolean represent_name1_is_modified = false;
  private boolean receipt_kana_is_set = false;
  private boolean receipt_kana_is_modified = false;
  private boolean receipt_name1_is_set = false;
  private boolean receipt_name1_is_modified = false;
  private boolean receipt_fin_institution_is_set = false;
  private boolean receipt_fin_institution_is_modified = false;
  private boolean receipt_fin_branch_is_set = false;
  private boolean receipt_fin_branch_is_modified = false;
  private boolean receipt_fin_acc_type_is_set = false;
  private boolean receipt_fin_acc_type_is_modified = false;
  private boolean receipt_fin_acc_no_is_set = false;
  private boolean receipt_fin_acc_no_is_modified = false;
  private boolean receipt_fin_acc_div_is_set = false;
  private boolean receipt_fin_acc_div_is_modified = false;
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
      + "," + "account_name_kana1=" +account_name_kana1
      + "," + "account_name1=" +account_name1
      + "," + "zip_code1=" +zip_code1
      + "," + "zip_code2=" +zip_code2
      + "," + "address_line1=" +address_line1
      + "," + "represent_post=" +represent_post
      + "," + "represent_name_kana1=" +represent_name_kana1
      + "," + "represent_name1=" +represent_name1
      + "," + "receipt_kana=" +receipt_kana
      + "," + "receipt_name1=" +receipt_name1
      + "," + "receipt_fin_institution=" +receipt_fin_institution
      + "," + "receipt_fin_branch=" +receipt_fin_branch
      + "," + "receipt_fin_acc_type=" +receipt_fin_acc_type
      + "," + "receipt_fin_acc_no=" +receipt_fin_acc_no
      + "," + "receipt_fin_acc_div=" +receipt_fin_acc_div
      + "," + "status=" +status
      + "," + "send_timestamp=" +send_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のHostAgencyNotifyVoucherParamsオブジェクトを作成します。 
   */
  public HostAgencyNotifyVoucherParams() {
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
   * 指定のHostAgencyNotifyVoucherRowオブジェクトの値を利用してHostAgencyNotifyVoucherParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostAgencyNotifyVoucherRowオブジェクト 
   */
  public HostAgencyNotifyVoucherParams( HostAgencyNotifyVoucherRow p_row )
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
    account_name_kana1 = p_row.getAccountNameKana1();
    account_name_kana1_is_set = p_row.getAccountNameKana1IsSet();
    account_name_kana1_is_modified = p_row.getAccountNameKana1IsModified();
    account_name1 = p_row.getAccountName1();
    account_name1_is_set = p_row.getAccountName1IsSet();
    account_name1_is_modified = p_row.getAccountName1IsModified();
    zip_code1 = p_row.getZipCode1();
    zip_code1_is_set = p_row.getZipCode1IsSet();
    zip_code1_is_modified = p_row.getZipCode1IsModified();
    zip_code2 = p_row.getZipCode2();
    zip_code2_is_set = p_row.getZipCode2IsSet();
    zip_code2_is_modified = p_row.getZipCode2IsModified();
    address_line1 = p_row.getAddressLine1();
    address_line1_is_set = p_row.getAddressLine1IsSet();
    address_line1_is_modified = p_row.getAddressLine1IsModified();
    represent_post = p_row.getRepresentPost();
    represent_post_is_set = p_row.getRepresentPostIsSet();
    represent_post_is_modified = p_row.getRepresentPostIsModified();
    represent_name_kana1 = p_row.getRepresentNameKana1();
    represent_name_kana1_is_set = p_row.getRepresentNameKana1IsSet();
    represent_name_kana1_is_modified = p_row.getRepresentNameKana1IsModified();
    represent_name1 = p_row.getRepresentName1();
    represent_name1_is_set = p_row.getRepresentName1IsSet();
    represent_name1_is_modified = p_row.getRepresentName1IsModified();
    receipt_kana = p_row.getReceiptKana();
    receipt_kana_is_set = p_row.getReceiptKanaIsSet();
    receipt_kana_is_modified = p_row.getReceiptKanaIsModified();
    receipt_name1 = p_row.getReceiptName1();
    receipt_name1_is_set = p_row.getReceiptName1IsSet();
    receipt_name1_is_modified = p_row.getReceiptName1IsModified();
    receipt_fin_institution = p_row.getReceiptFinInstitution();
    receipt_fin_institution_is_set = p_row.getReceiptFinInstitutionIsSet();
    receipt_fin_institution_is_modified = p_row.getReceiptFinInstitutionIsModified();
    receipt_fin_branch = p_row.getReceiptFinBranch();
    receipt_fin_branch_is_set = p_row.getReceiptFinBranchIsSet();
    receipt_fin_branch_is_modified = p_row.getReceiptFinBranchIsModified();
    receipt_fin_acc_type = p_row.getReceiptFinAccType();
    receipt_fin_acc_type_is_set = p_row.getReceiptFinAccTypeIsSet();
    receipt_fin_acc_type_is_modified = p_row.getReceiptFinAccTypeIsModified();
    receipt_fin_acc_no = p_row.getReceiptFinAccNo();
    receipt_fin_acc_no_is_set = p_row.getReceiptFinAccNoIsSet();
    receipt_fin_acc_no_is_modified = p_row.getReceiptFinAccNoIsModified();
    receipt_fin_acc_div = p_row.getReceiptFinAccDiv();
    receipt_fin_acc_div_is_set = p_row.getReceiptFinAccDivIsSet();
    receipt_fin_acc_div_is_modified = p_row.getReceiptFinAccDivIsModified();
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
      account_name_kana1_is_set = true;
      account_name_kana1_is_modified = true;
      account_name1_is_set = true;
      account_name1_is_modified = true;
      zip_code1_is_set = true;
      zip_code1_is_modified = true;
      zip_code2_is_set = true;
      zip_code2_is_modified = true;
      address_line1_is_set = true;
      address_line1_is_modified = true;
      represent_post_is_set = true;
      represent_post_is_modified = true;
      represent_name_kana1_is_set = true;
      represent_name_kana1_is_modified = true;
      represent_name1_is_set = true;
      represent_name1_is_modified = true;
      receipt_kana_is_set = true;
      receipt_kana_is_modified = true;
      receipt_name1_is_set = true;
      receipt_name1_is_modified = true;
      receipt_fin_institution_is_set = true;
      receipt_fin_institution_is_modified = true;
      receipt_fin_branch_is_set = true;
      receipt_fin_branch_is_modified = true;
      receipt_fin_acc_type_is_set = true;
      receipt_fin_acc_type_is_modified = true;
      receipt_fin_acc_no_is_set = true;
      receipt_fin_acc_no_is_modified = true;
      receipt_fin_acc_div_is_set = true;
      receipt_fin_acc_div_is_modified = true;
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
    if ( !( other instanceof HostAgencyNotifyVoucherRow ) )
       return false;
    return fieldsEqual( (HostAgencyNotifyVoucherRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostAgencyNotifyVoucherRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostAgencyNotifyVoucherRow row )
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
    if ( account_name_kana1 == null ) {
      if ( row.getAccountNameKana1() != null )
        return false;
    } else if ( !account_name_kana1.equals( row.getAccountNameKana1() ) ) {
        return false;
    }
    if ( account_name1 == null ) {
      if ( row.getAccountName1() != null )
        return false;
    } else if ( !account_name1.equals( row.getAccountName1() ) ) {
        return false;
    }
    if ( zip_code1 == null ) {
      if ( row.getZipCode1() != null )
        return false;
    } else if ( !zip_code1.equals( row.getZipCode1() ) ) {
        return false;
    }
    if ( zip_code2 == null ) {
      if ( row.getZipCode2() != null )
        return false;
    } else if ( !zip_code2.equals( row.getZipCode2() ) ) {
        return false;
    }
    if ( address_line1 == null ) {
      if ( row.getAddressLine1() != null )
        return false;
    } else if ( !address_line1.equals( row.getAddressLine1() ) ) {
        return false;
    }
    if ( represent_post == null ) {
      if ( row.getRepresentPost() != null )
        return false;
    } else if ( !represent_post.equals( row.getRepresentPost() ) ) {
        return false;
    }
    if ( represent_name_kana1 == null ) {
      if ( row.getRepresentNameKana1() != null )
        return false;
    } else if ( !represent_name_kana1.equals( row.getRepresentNameKana1() ) ) {
        return false;
    }
    if ( represent_name1 == null ) {
      if ( row.getRepresentName1() != null )
        return false;
    } else if ( !represent_name1.equals( row.getRepresentName1() ) ) {
        return false;
    }
    if ( receipt_kana == null ) {
      if ( row.getReceiptKana() != null )
        return false;
    } else if ( !receipt_kana.equals( row.getReceiptKana() ) ) {
        return false;
    }
    if ( receipt_name1 == null ) {
      if ( row.getReceiptName1() != null )
        return false;
    } else if ( !receipt_name1.equals( row.getReceiptName1() ) ) {
        return false;
    }
    if ( receipt_fin_institution == null ) {
      if ( row.getReceiptFinInstitution() != null )
        return false;
    } else if ( !receipt_fin_institution.equals( row.getReceiptFinInstitution() ) ) {
        return false;
    }
    if ( receipt_fin_branch == null ) {
      if ( row.getReceiptFinBranch() != null )
        return false;
    } else if ( !receipt_fin_branch.equals( row.getReceiptFinBranch() ) ) {
        return false;
    }
    if ( receipt_fin_acc_type == null ) {
      if ( row.getReceiptFinAccType() != null )
        return false;
    } else if ( !receipt_fin_acc_type.equals( row.getReceiptFinAccType() ) ) {
        return false;
    }
    if ( receipt_fin_acc_no == null ) {
      if ( row.getReceiptFinAccNo() != null )
        return false;
    } else if ( !receipt_fin_acc_no.equals( row.getReceiptFinAccNo() ) ) {
        return false;
    }
    if ( receipt_fin_acc_div == null ) {
      if ( row.getReceiptFinAccDiv() != null )
        return false;
    } else if ( !receipt_fin_acc_div.equals( row.getReceiptFinAccDiv() ) ) {
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
        + (account_name_kana1!=null? account_name_kana1.hashCode(): 0) 
        + (account_name1!=null? account_name1.hashCode(): 0) 
        + (zip_code1!=null? zip_code1.hashCode(): 0) 
        + (zip_code2!=null? zip_code2.hashCode(): 0) 
        + (address_line1!=null? address_line1.hashCode(): 0) 
        + (represent_post!=null? represent_post.hashCode(): 0) 
        + (represent_name_kana1!=null? represent_name_kana1.hashCode(): 0) 
        + (represent_name1!=null? represent_name1.hashCode(): 0) 
        + (receipt_kana!=null? receipt_kana.hashCode(): 0) 
        + (receipt_name1!=null? receipt_name1.hashCode(): 0) 
        + (receipt_fin_institution!=null? receipt_fin_institution.hashCode(): 0) 
        + (receipt_fin_branch!=null? receipt_fin_branch.hashCode(): 0) 
        + (receipt_fin_acc_type!=null? receipt_fin_acc_type.hashCode(): 0) 
        + (receipt_fin_acc_no!=null? receipt_fin_acc_no.hashCode(): 0) 
        + (receipt_fin_acc_div!=null? receipt_fin_acc_div.hashCode(): 0) 
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
		if ( trader_code_is_set )
			map.put("trader_code",trader_code);
		map.put("acc_open_request_number",acc_open_request_number);
		if ( serial_no_is_set )
			map.put("serial_no",serial_no);
		if ( regist_div != null )
			map.put("regist_div",regist_div);
		if ( account_name_kana1 != null )
			map.put("account_name_kana1",account_name_kana1);
		if ( account_name1 != null )
			map.put("account_name1",account_name1);
		if ( zip_code1 != null )
			map.put("zip_code1",zip_code1);
		if ( zip_code2 != null )
			map.put("zip_code2",zip_code2);
		if ( address_line1 != null )
			map.put("address_line1",address_line1);
		if ( represent_post != null )
			map.put("represent_post",represent_post);
		if ( represent_name_kana1 != null )
			map.put("represent_name_kana1",represent_name_kana1);
		if ( represent_name1 != null )
			map.put("represent_name1",represent_name1);
		if ( receipt_kana != null )
			map.put("receipt_kana",receipt_kana);
		if ( receipt_name1 != null )
			map.put("receipt_name1",receipt_name1);
		if ( receipt_fin_institution != null )
			map.put("receipt_fin_institution",receipt_fin_institution);
		if ( receipt_fin_branch != null )
			map.put("receipt_fin_branch",receipt_fin_branch);
		if ( receipt_fin_acc_type != null )
			map.put("receipt_fin_acc_type",receipt_fin_acc_type);
		if ( receipt_fin_acc_no != null )
			map.put("receipt_fin_acc_no",receipt_fin_acc_no);
		if ( receipt_fin_acc_div != null )
			map.put("receipt_fin_acc_div",receipt_fin_acc_div);
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
		if ( account_name_kana1_is_modified )
			map.put("account_name_kana1",account_name_kana1);
		if ( account_name1_is_modified )
			map.put("account_name1",account_name1);
		if ( zip_code1_is_modified )
			map.put("zip_code1",zip_code1);
		if ( zip_code2_is_modified )
			map.put("zip_code2",zip_code2);
		if ( address_line1_is_modified )
			map.put("address_line1",address_line1);
		if ( represent_post_is_modified )
			map.put("represent_post",represent_post);
		if ( represent_name_kana1_is_modified )
			map.put("represent_name_kana1",represent_name_kana1);
		if ( represent_name1_is_modified )
			map.put("represent_name1",represent_name1);
		if ( receipt_kana_is_modified )
			map.put("receipt_kana",receipt_kana);
		if ( receipt_name1_is_modified )
			map.put("receipt_name1",receipt_name1);
		if ( receipt_fin_institution_is_modified )
			map.put("receipt_fin_institution",receipt_fin_institution);
		if ( receipt_fin_branch_is_modified )
			map.put("receipt_fin_branch",receipt_fin_branch);
		if ( receipt_fin_acc_type_is_modified )
			map.put("receipt_fin_acc_type",receipt_fin_acc_type);
		if ( receipt_fin_acc_no_is_modified )
			map.put("receipt_fin_acc_no",receipt_fin_acc_no);
		if ( receipt_fin_acc_div_is_modified )
			map.put("receipt_fin_acc_div",receipt_fin_acc_div);
		if ( status_is_modified )
			map.put("status",status);
		if ( send_timestamp_is_modified )
			map.put("send_timestamp",send_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( trader_code_is_set )
				map.put("trader_code",trader_code);
			if ( acc_open_request_number_is_set )
				map.put("acc_open_request_number",acc_open_request_number);
			if ( serial_no_is_set )
				map.put("serial_no",serial_no);
			map.put("regist_div",regist_div);
			map.put("account_name_kana1",account_name_kana1);
			map.put("account_name1",account_name1);
			map.put("zip_code1",zip_code1);
			map.put("zip_code2",zip_code2);
			map.put("address_line1",address_line1);
			map.put("represent_post",represent_post);
			map.put("represent_name_kana1",represent_name_kana1);
			map.put("represent_name1",represent_name1);
			map.put("receipt_kana",receipt_kana);
			map.put("receipt_name1",receipt_name1);
			map.put("receipt_fin_institution",receipt_fin_institution);
			map.put("receipt_fin_branch",receipt_fin_branch);
			map.put("receipt_fin_acc_type",receipt_fin_acc_type);
			map.put("receipt_fin_acc_no",receipt_fin_acc_no);
			map.put("receipt_fin_acc_div",receipt_fin_acc_div);
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
   * <em>account_name_kana1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountNameKana1()
  {
    return account_name_kana1;
  }


  /** 
   * <em>account_name_kana1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameKana1IsSet() {
    return account_name_kana1_is_set;
  }


  /** 
   * <em>account_name_kana1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameKana1IsModified() {
    return account_name_kana1_is_modified;
  }


  /** 
   * <em>account_name1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountName1()
  {
    return account_name1;
  }


  /** 
   * <em>account_name1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountName1IsSet() {
    return account_name1_is_set;
  }


  /** 
   * <em>account_name1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountName1IsModified() {
    return account_name1_is_modified;
  }


  /** 
   * <em>zip_code1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getZipCode1()
  {
    return zip_code1;
  }


  /** 
   * <em>zip_code1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getZipCode1IsSet() {
    return zip_code1_is_set;
  }


  /** 
   * <em>zip_code1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getZipCode1IsModified() {
    return zip_code1_is_modified;
  }


  /** 
   * <em>zip_code2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getZipCode2()
  {
    return zip_code2;
  }


  /** 
   * <em>zip_code2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getZipCode2IsSet() {
    return zip_code2_is_set;
  }


  /** 
   * <em>zip_code2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getZipCode2IsModified() {
    return zip_code2_is_modified;
  }


  /** 
   * <em>address_line1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddressLine1()
  {
    return address_line1;
  }


  /** 
   * <em>address_line1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine1IsSet() {
    return address_line1_is_set;
  }


  /** 
   * <em>address_line1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine1IsModified() {
    return address_line1_is_modified;
  }


  /** 
   * <em>represent_post</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepresentPost()
  {
    return represent_post;
  }


  /** 
   * <em>represent_post</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentPostIsSet() {
    return represent_post_is_set;
  }


  /** 
   * <em>represent_post</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentPostIsModified() {
    return represent_post_is_modified;
  }


  /** 
   * <em>represent_name_kana1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepresentNameKana1()
  {
    return represent_name_kana1;
  }


  /** 
   * <em>represent_name_kana1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentNameKana1IsSet() {
    return represent_name_kana1_is_set;
  }


  /** 
   * <em>represent_name_kana1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentNameKana1IsModified() {
    return represent_name_kana1_is_modified;
  }


  /** 
   * <em>represent_name1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepresentName1()
  {
    return represent_name1;
  }


  /** 
   * <em>represent_name1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentName1IsSet() {
    return represent_name1_is_set;
  }


  /** 
   * <em>represent_name1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentName1IsModified() {
    return represent_name1_is_modified;
  }


  /** 
   * <em>receipt_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReceiptKana()
  {
    return receipt_kana;
  }


  /** 
   * <em>receipt_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptKanaIsSet() {
    return receipt_kana_is_set;
  }


  /** 
   * <em>receipt_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptKanaIsModified() {
    return receipt_kana_is_modified;
  }


  /** 
   * <em>receipt_name1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReceiptName1()
  {
    return receipt_name1;
  }


  /** 
   * <em>receipt_name1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptName1IsSet() {
    return receipt_name1_is_set;
  }


  /** 
   * <em>receipt_name1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptName1IsModified() {
    return receipt_name1_is_modified;
  }


  /** 
   * <em>receipt_fin_institution</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReceiptFinInstitution()
  {
    return receipt_fin_institution;
  }


  /** 
   * <em>receipt_fin_institution</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptFinInstitutionIsSet() {
    return receipt_fin_institution_is_set;
  }


  /** 
   * <em>receipt_fin_institution</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptFinInstitutionIsModified() {
    return receipt_fin_institution_is_modified;
  }


  /** 
   * <em>receipt_fin_branch</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReceiptFinBranch()
  {
    return receipt_fin_branch;
  }


  /** 
   * <em>receipt_fin_branch</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptFinBranchIsSet() {
    return receipt_fin_branch_is_set;
  }


  /** 
   * <em>receipt_fin_branch</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptFinBranchIsModified() {
    return receipt_fin_branch_is_modified;
  }


  /** 
   * <em>receipt_fin_acc_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReceiptFinAccType()
  {
    return receipt_fin_acc_type;
  }


  /** 
   * <em>receipt_fin_acc_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptFinAccTypeIsSet() {
    return receipt_fin_acc_type_is_set;
  }


  /** 
   * <em>receipt_fin_acc_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptFinAccTypeIsModified() {
    return receipt_fin_acc_type_is_modified;
  }


  /** 
   * <em>receipt_fin_acc_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReceiptFinAccNo()
  {
    return receipt_fin_acc_no;
  }


  /** 
   * <em>receipt_fin_acc_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptFinAccNoIsSet() {
    return receipt_fin_acc_no_is_set;
  }


  /** 
   * <em>receipt_fin_acc_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptFinAccNoIsModified() {
    return receipt_fin_acc_no_is_modified;
  }


  /** 
   * <em>receipt_fin_acc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReceiptFinAccDiv()
  {
    return receipt_fin_acc_div;
  }


  /** 
   * <em>receipt_fin_acc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptFinAccDivIsSet() {
    return receipt_fin_acc_div_is_set;
  }


  /** 
   * <em>receipt_fin_acc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptFinAccDivIsModified() {
    return receipt_fin_acc_div_is_modified;
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
    return new HostAgencyNotifyVoucherPK(order_request_number, request_code, institution_code, branch_code, account_code);
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
   * <em>account_name_kana1</em>カラムの値を設定します。 
   *
   * @@param p_accountNameKana1 <em>account_name_kana1</em>カラムの値をあらわす120桁以下のString値 
   */
  public final void setAccountNameKana1( String p_accountNameKana1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_name_kana1 = p_accountNameKana1;
    account_name_kana1_is_set = true;
    account_name_kana1_is_modified = true;
  }


  /** 
   * <em>account_name1</em>カラムの値を設定します。 
   *
   * @@param p_accountName1 <em>account_name1</em>カラムの値をあらわす120桁以下のString値 
   */
  public final void setAccountName1( String p_accountName1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_name1 = p_accountName1;
    account_name1_is_set = true;
    account_name1_is_modified = true;
  }


  /** 
   * <em>zip_code1</em>カラムの値を設定します。 
   *
   * @@param p_zipCode1 <em>zip_code1</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setZipCode1( String p_zipCode1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    zip_code1 = p_zipCode1;
    zip_code1_is_set = true;
    zip_code1_is_modified = true;
  }


  /** 
   * <em>zip_code2</em>カラムの値を設定します。 
   *
   * @@param p_zipCode2 <em>zip_code2</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setZipCode2( String p_zipCode2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    zip_code2 = p_zipCode2;
    zip_code2_is_set = true;
    zip_code2_is_modified = true;
  }


  /** 
   * <em>address_line1</em>カラムの値を設定します。 
   *
   * @@param p_addressLine1 <em>address_line1</em>カラムの値をあらわす96桁以下のString値 
   */
  public final void setAddressLine1( String p_addressLine1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_line1 = p_addressLine1;
    address_line1_is_set = true;
    address_line1_is_modified = true;
  }


  /** 
   * <em>represent_post</em>カラムの値を設定します。 
   *
   * @@param p_representPost <em>represent_post</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setRepresentPost( String p_representPost )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    represent_post = p_representPost;
    represent_post_is_set = true;
    represent_post_is_modified = true;
  }


  /** 
   * <em>represent_name_kana1</em>カラムの値を設定します。 
   *
   * @@param p_representNameKana1 <em>represent_name_kana1</em>カラムの値をあらわす120桁以下のString値 
   */
  public final void setRepresentNameKana1( String p_representNameKana1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    represent_name_kana1 = p_representNameKana1;
    represent_name_kana1_is_set = true;
    represent_name_kana1_is_modified = true;
  }


  /** 
   * <em>represent_name1</em>カラムの値を設定します。 
   *
   * @@param p_representName1 <em>represent_name1</em>カラムの値をあらわす120桁以下のString値 
   */
  public final void setRepresentName1( String p_representName1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    represent_name1 = p_representName1;
    represent_name1_is_set = true;
    represent_name1_is_modified = true;
  }


  /** 
   * <em>receipt_kana</em>カラムの値を設定します。 
   *
   * @@param p_receiptKana <em>receipt_kana</em>カラムの値をあらわす38桁以下のString値 
   */
  public final void setReceiptKana( String p_receiptKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_kana = p_receiptKana;
    receipt_kana_is_set = true;
    receipt_kana_is_modified = true;
  }


  /** 
   * <em>receipt_name1</em>カラムの値を設定します。 
   *
   * @@param p_receiptName1 <em>receipt_name1</em>カラムの値をあらわす80桁以下のString値 
   */
  public final void setReceiptName1( String p_receiptName1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_name1 = p_receiptName1;
    receipt_name1_is_set = true;
    receipt_name1_is_modified = true;
  }


  /** 
   * <em>receipt_fin_institution</em>カラムの値を設定します。 
   *
   * @@param p_receiptFinInstitution <em>receipt_fin_institution</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setReceiptFinInstitution( String p_receiptFinInstitution )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_fin_institution = p_receiptFinInstitution;
    receipt_fin_institution_is_set = true;
    receipt_fin_institution_is_modified = true;
  }


  /** 
   * <em>receipt_fin_branch</em>カラムの値を設定します。 
   *
   * @@param p_receiptFinBranch <em>receipt_fin_branch</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setReceiptFinBranch( String p_receiptFinBranch )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_fin_branch = p_receiptFinBranch;
    receipt_fin_branch_is_set = true;
    receipt_fin_branch_is_modified = true;
  }


  /** 
   * <em>receipt_fin_acc_type</em>カラムの値を設定します。 
   *
   * @@param p_receiptFinAccType <em>receipt_fin_acc_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setReceiptFinAccType( String p_receiptFinAccType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_fin_acc_type = p_receiptFinAccType;
    receipt_fin_acc_type_is_set = true;
    receipt_fin_acc_type_is_modified = true;
  }


  /** 
   * <em>receipt_fin_acc_no</em>カラムの値を設定します。 
   *
   * @@param p_receiptFinAccNo <em>receipt_fin_acc_no</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setReceiptFinAccNo( String p_receiptFinAccNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_fin_acc_no = p_receiptFinAccNo;
    receipt_fin_acc_no_is_set = true;
    receipt_fin_acc_no_is_modified = true;
  }


  /** 
   * <em>receipt_fin_acc_div</em>カラムの値を設定します。 
   *
   * @@param p_receiptFinAccDiv <em>receipt_fin_acc_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setReceiptFinAccDiv( String p_receiptFinAccDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_fin_acc_div = p_receiptFinAccDiv;
    receipt_fin_acc_div_is_set = true;
    receipt_fin_acc_div_is_modified = true;
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
                else if ( name.equals("account_name_kana1") ) {
                    return this.account_name_kana1;
                }
                else if ( name.equals("account_name1") ) {
                    return this.account_name1;
                }
                else if ( name.equals("address_line1") ) {
                    return this.address_line1;
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
                else if ( name.equals("represent_post") ) {
                    return this.represent_post;
                }
                else if ( name.equals("represent_name_kana1") ) {
                    return this.represent_name_kana1;
                }
                else if ( name.equals("represent_name1") ) {
                    return this.represent_name1;
                }
                else if ( name.equals("receipt_kana") ) {
                    return this.receipt_kana;
                }
                else if ( name.equals("receipt_name1") ) {
                    return this.receipt_name1;
                }
                else if ( name.equals("receipt_fin_institution") ) {
                    return this.receipt_fin_institution;
                }
                else if ( name.equals("receipt_fin_branch") ) {
                    return this.receipt_fin_branch;
                }
                else if ( name.equals("receipt_fin_acc_type") ) {
                    return this.receipt_fin_acc_type;
                }
                else if ( name.equals("receipt_fin_acc_no") ) {
                    return this.receipt_fin_acc_no;
                }
                else if ( name.equals("receipt_fin_acc_div") ) {
                    return this.receipt_fin_acc_div;
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
            case 'z':
                if ( name.equals("zip_code1") ) {
                    return this.zip_code1;
                }
                else if ( name.equals("zip_code2") ) {
                    return this.zip_code2;
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
                else if ( name.equals("account_name_kana1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_name_kana1' must be String: '"+value+"' is not." );
                    this.account_name_kana1 = (String) value;
                    if (this.account_name_kana1_is_set)
                        this.account_name_kana1_is_modified = true;
                    this.account_name_kana1_is_set = true;
                    return;
                }
                else if ( name.equals("account_name1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_name1' must be String: '"+value+"' is not." );
                    this.account_name1 = (String) value;
                    if (this.account_name1_is_set)
                        this.account_name1_is_modified = true;
                    this.account_name1_is_set = true;
                    return;
                }
                else if ( name.equals("address_line1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address_line1' must be String: '"+value+"' is not." );
                    this.address_line1 = (String) value;
                    if (this.address_line1_is_set)
                        this.address_line1_is_modified = true;
                    this.address_line1_is_set = true;
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
                else if ( name.equals("represent_post") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'represent_post' must be String: '"+value+"' is not." );
                    this.represent_post = (String) value;
                    if (this.represent_post_is_set)
                        this.represent_post_is_modified = true;
                    this.represent_post_is_set = true;
                    return;
                }
                else if ( name.equals("represent_name_kana1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'represent_name_kana1' must be String: '"+value+"' is not." );
                    this.represent_name_kana1 = (String) value;
                    if (this.represent_name_kana1_is_set)
                        this.represent_name_kana1_is_modified = true;
                    this.represent_name_kana1_is_set = true;
                    return;
                }
                else if ( name.equals("represent_name1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'represent_name1' must be String: '"+value+"' is not." );
                    this.represent_name1 = (String) value;
                    if (this.represent_name1_is_set)
                        this.represent_name1_is_modified = true;
                    this.represent_name1_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'receipt_kana' must be String: '"+value+"' is not." );
                    this.receipt_kana = (String) value;
                    if (this.receipt_kana_is_set)
                        this.receipt_kana_is_modified = true;
                    this.receipt_kana_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_name1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'receipt_name1' must be String: '"+value+"' is not." );
                    this.receipt_name1 = (String) value;
                    if (this.receipt_name1_is_set)
                        this.receipt_name1_is_modified = true;
                    this.receipt_name1_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_fin_institution") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'receipt_fin_institution' must be String: '"+value+"' is not." );
                    this.receipt_fin_institution = (String) value;
                    if (this.receipt_fin_institution_is_set)
                        this.receipt_fin_institution_is_modified = true;
                    this.receipt_fin_institution_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_fin_branch") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'receipt_fin_branch' must be String: '"+value+"' is not." );
                    this.receipt_fin_branch = (String) value;
                    if (this.receipt_fin_branch_is_set)
                        this.receipt_fin_branch_is_modified = true;
                    this.receipt_fin_branch_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_fin_acc_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'receipt_fin_acc_type' must be String: '"+value+"' is not." );
                    this.receipt_fin_acc_type = (String) value;
                    if (this.receipt_fin_acc_type_is_set)
                        this.receipt_fin_acc_type_is_modified = true;
                    this.receipt_fin_acc_type_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_fin_acc_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'receipt_fin_acc_no' must be String: '"+value+"' is not." );
                    this.receipt_fin_acc_no = (String) value;
                    if (this.receipt_fin_acc_no_is_set)
                        this.receipt_fin_acc_no_is_modified = true;
                    this.receipt_fin_acc_no_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_fin_acc_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'receipt_fin_acc_div' must be String: '"+value+"' is not." );
                    this.receipt_fin_acc_div = (String) value;
                    if (this.receipt_fin_acc_div_is_set)
                        this.receipt_fin_acc_div_is_modified = true;
                    this.receipt_fin_acc_div_is_set = true;
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
            case 'z':
                if ( name.equals("zip_code1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'zip_code1' must be String: '"+value+"' is not." );
                    this.zip_code1 = (String) value;
                    if (this.zip_code1_is_set)
                        this.zip_code1_is_modified = true;
                    this.zip_code1_is_set = true;
                    return;
                }
                else if ( name.equals("zip_code2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'zip_code2' must be String: '"+value+"' is not." );
                    this.zip_code2 = (String) value;
                    if (this.zip_code2_is_set)
                        this.zip_code2_is_modified = true;
                    this.zip_code2_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
