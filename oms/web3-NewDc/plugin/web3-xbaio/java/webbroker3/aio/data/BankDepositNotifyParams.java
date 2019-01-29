head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.51.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	BankDepositNotifyParams.java;


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
 * bank_deposit_notifyテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BankDepositNotifyRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BankDepositNotifyParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BankDepositNotifyParams}が{@@link BankDepositNotifyRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BankDepositNotifyPK 
 * @@see BankDepositNotifyRow 
 */
public class BankDepositNotifyParams extends Params implements BankDepositNotifyRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bank_deposit_notify";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BankDepositNotifyRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BankDepositNotifyRow.TYPE;
  }


  /** 
   * <em>bank_deposit_notify_id</em>カラムの値 
   */
  public  long  bank_deposit_notify_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>data_load_div</em>カラムの値 
   */
  public  String  data_load_div;

  /** 
   * <em>bank_code</em>カラムの値 
   */
  public  String  bank_code;

  /** 
   * <em>bank_branch_code</em>カラムの値 
   */
  public  String  bank_branch_code;

  /** 
   * <em>bank_account_no</em>カラムの値 
   */
  public  String  bank_account_no;

  /** 
   * <em>deposit_data_reference_no</em>カラムの値 
   */
  public  String  deposit_data_reference_no;

  /** 
   * <em>deposit_data_account_date</em>カラムの値 
   */
  public  String  deposit_data_account_date;

  /** 
   * <em>deposit_data_bank_account_type</em>カラムの値 
   */
  public  String  deposit_data_bank_account_type;

  /** 
   * <em>deposit_data_base_date</em>カラムの値 
   */
  public  String  deposit_data_base_date;

  /** 
   * <em>deposit_data_deposit_amount</em>カラムの値 
   */
  public  String  deposit_data_deposit_amount;

  /** 
   * <em>deposit_data_trans_person_code</em>カラムの値 
   */
  public  String  deposit_data_trans_person_code;

  /** 
   * <em>deposit_data_trans_person_name</em>カラムの値 
   */
  public  String  deposit_data_trans_person_name;

  /** 
   * <em>cash_transfer_div</em>カラムの値 
   */
  public  String  cash_transfer_div;

  /** 
   * <em>trade_div</em>カラムの値 
   */
  public  String  trade_div;

  /** 
   * <em>delivered_bank_name</em>カラムの値 
   */
  public  String  delivered_bank_name;

  /** 
   * <em>delivered_bank_branch_name</em>カラムの値 
   */
  public  String  delivered_bank_branch_name;

  /** 
   * <em>summary</em>カラムの値 
   */
  public  String  summary;

  /** 
   * <em>edi_info</em>カラムの値 
   */
  public  String  edi_info;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>deposit_error_comment</em>カラムの値 
   */
  public  String  deposit_error_comment;

  /** 
   * <em>remark</em>カラムの値 
   */
  public  String  remark;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>last_error_history_serial_no</em>カラムの値 
   */
  public  int  last_error_history_serial_no;

  /** 
   * <em>update_person</em>カラムの値 
   */
  public  String  update_person;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>currency_code</em>カラムの値 
   */
  public  String  currency_code;

  private boolean bank_deposit_notify_id_is_set = false;
  private boolean bank_deposit_notify_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean bank_code_is_set = false;
  private boolean bank_code_is_modified = false;
  private boolean bank_branch_code_is_set = false;
  private boolean bank_branch_code_is_modified = false;
  private boolean bank_account_no_is_set = false;
  private boolean bank_account_no_is_modified = false;
  private boolean deposit_data_reference_no_is_set = false;
  private boolean deposit_data_reference_no_is_modified = false;
  private boolean deposit_data_account_date_is_set = false;
  private boolean deposit_data_account_date_is_modified = false;
  private boolean deposit_data_bank_account_type_is_set = false;
  private boolean deposit_data_bank_account_type_is_modified = false;
  private boolean deposit_data_base_date_is_set = false;
  private boolean deposit_data_base_date_is_modified = false;
  private boolean deposit_data_deposit_amount_is_set = false;
  private boolean deposit_data_deposit_amount_is_modified = false;
  private boolean deposit_data_trans_person_code_is_set = false;
  private boolean deposit_data_trans_person_code_is_modified = false;
  private boolean deposit_data_trans_person_name_is_set = false;
  private boolean deposit_data_trans_person_name_is_modified = false;
  private boolean cash_transfer_div_is_set = false;
  private boolean cash_transfer_div_is_modified = false;
  private boolean trade_div_is_set = false;
  private boolean trade_div_is_modified = false;
  private boolean delivered_bank_name_is_set = false;
  private boolean delivered_bank_name_is_modified = false;
  private boolean delivered_bank_branch_name_is_set = false;
  private boolean delivered_bank_branch_name_is_modified = false;
  private boolean summary_is_set = false;
  private boolean summary_is_modified = false;
  private boolean edi_info_is_set = false;
  private boolean edi_info_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean deposit_error_comment_is_set = false;
  private boolean deposit_error_comment_is_modified = false;
  private boolean remark_is_set = false;
  private boolean remark_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean last_error_history_serial_no_is_set = false;
  private boolean last_error_history_serial_no_is_modified = false;
  private boolean update_person_is_set = false;
  private boolean update_person_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean data_load_div_is_set = false;
  private boolean data_load_div_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "bank_deposit_notify_id=" + bank_deposit_notify_id
      + "," + "institution_code=" + institution_code
      + "," + "data_load_div=" + data_load_div
      + "," + "bank_code=" +bank_code
      + "," + "bank_branch_code=" +bank_branch_code
      + "," + "bank_account_no=" +bank_account_no
      + "," + "deposit_data_reference_no=" +deposit_data_reference_no
      + "," + "deposit_data_account_date=" +deposit_data_account_date
      + "," + "deposit_data_bank_account_type=" +deposit_data_bank_account_type
      + "," + "deposit_data_base_date=" +deposit_data_base_date
      + "," + "deposit_data_deposit_amount=" +deposit_data_deposit_amount
      + "," + "deposit_data_trans_person_code=" +deposit_data_trans_person_code
      + "," + "deposit_data_trans_person_name=" +deposit_data_trans_person_name
      + "," + "cash_transfer_div=" +cash_transfer_div
      + "," + "trade_div=" +trade_div
      + "," + "delivered_bank_name=" +delivered_bank_name
      + "," + "delivered_bank_branch_name=" +delivered_bank_branch_name
      + "," + "summary=" +summary
      + "," + "edi_info=" +edi_info
      + "," + "status=" +status
      + "," + "deposit_error_comment=" +deposit_error_comment
      + "," + "remark=" +remark
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "last_error_history_serial_no=" +last_error_history_serial_no
      + "," + "update_person=" +update_person
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "currency_code=" +currency_code
      + "}";
  }


  /** 
   * 値が未設定のBankDepositNotifyParamsオブジェクトを作成します。 
   */
  public BankDepositNotifyParams() {
    bank_deposit_notify_id_is_modified = true;
    institution_code_is_modified = true;
    data_load_div_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBankDepositNotifyRowオブジェクトの値を利用してBankDepositNotifyParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBankDepositNotifyRowオブジェクト 
   */
  public BankDepositNotifyParams( BankDepositNotifyRow p_row )
  {
    bank_deposit_notify_id = p_row.getBankDepositNotifyId();
    bank_deposit_notify_id_is_set = p_row.getBankDepositNotifyIdIsSet();
    bank_deposit_notify_id_is_modified = p_row.getBankDepositNotifyIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    data_load_div = p_row.getDataLoadDiv();
    data_load_div_is_set = p_row.getDataLoadDivIsSet();
    data_load_div_is_modified = p_row.getDataLoadDivIsModified();
    bank_code = p_row.getBankCode();
    bank_code_is_set = p_row.getBankCodeIsSet();
    bank_code_is_modified = p_row.getBankCodeIsModified();
    bank_branch_code = p_row.getBankBranchCode();
    bank_branch_code_is_set = p_row.getBankBranchCodeIsSet();
    bank_branch_code_is_modified = p_row.getBankBranchCodeIsModified();
    bank_account_no = p_row.getBankAccountNo();
    bank_account_no_is_set = p_row.getBankAccountNoIsSet();
    bank_account_no_is_modified = p_row.getBankAccountNoIsModified();
    deposit_data_reference_no = p_row.getDepositDataReferenceNo();
    deposit_data_reference_no_is_set = p_row.getDepositDataReferenceNoIsSet();
    deposit_data_reference_no_is_modified = p_row.getDepositDataReferenceNoIsModified();
    deposit_data_account_date = p_row.getDepositDataAccountDate();
    deposit_data_account_date_is_set = p_row.getDepositDataAccountDateIsSet();
    deposit_data_account_date_is_modified = p_row.getDepositDataAccountDateIsModified();
    deposit_data_bank_account_type = p_row.getDepositDataBankAccountType();
    deposit_data_bank_account_type_is_set = p_row.getDepositDataBankAccountTypeIsSet();
    deposit_data_bank_account_type_is_modified = p_row.getDepositDataBankAccountTypeIsModified();
    deposit_data_base_date = p_row.getDepositDataBaseDate();
    deposit_data_base_date_is_set = p_row.getDepositDataBaseDateIsSet();
    deposit_data_base_date_is_modified = p_row.getDepositDataBaseDateIsModified();
    deposit_data_deposit_amount = p_row.getDepositDataDepositAmount();
    deposit_data_deposit_amount_is_set = p_row.getDepositDataDepositAmountIsSet();
    deposit_data_deposit_amount_is_modified = p_row.getDepositDataDepositAmountIsModified();
    deposit_data_trans_person_code = p_row.getDepositDataTransPersonCode();
    deposit_data_trans_person_code_is_set = p_row.getDepositDataTransPersonCodeIsSet();
    deposit_data_trans_person_code_is_modified = p_row.getDepositDataTransPersonCodeIsModified();
    deposit_data_trans_person_name = p_row.getDepositDataTransPersonName();
    deposit_data_trans_person_name_is_set = p_row.getDepositDataTransPersonNameIsSet();
    deposit_data_trans_person_name_is_modified = p_row.getDepositDataTransPersonNameIsModified();
    cash_transfer_div = p_row.getCashTransferDiv();
    cash_transfer_div_is_set = p_row.getCashTransferDivIsSet();
    cash_transfer_div_is_modified = p_row.getCashTransferDivIsModified();
    trade_div = p_row.getTradeDiv();
    trade_div_is_set = p_row.getTradeDivIsSet();
    trade_div_is_modified = p_row.getTradeDivIsModified();
    delivered_bank_name = p_row.getDeliveredBankName();
    delivered_bank_name_is_set = p_row.getDeliveredBankNameIsSet();
    delivered_bank_name_is_modified = p_row.getDeliveredBankNameIsModified();
    delivered_bank_branch_name = p_row.getDeliveredBankBranchName();
    delivered_bank_branch_name_is_set = p_row.getDeliveredBankBranchNameIsSet();
    delivered_bank_branch_name_is_modified = p_row.getDeliveredBankBranchNameIsModified();
    summary = p_row.getSummary();
    summary_is_set = p_row.getSummaryIsSet();
    summary_is_modified = p_row.getSummaryIsModified();
    edi_info = p_row.getEdiInfo();
    edi_info_is_set = p_row.getEdiInfoIsSet();
    edi_info_is_modified = p_row.getEdiInfoIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    deposit_error_comment = p_row.getDepositErrorComment();
    deposit_error_comment_is_set = p_row.getDepositErrorCommentIsSet();
    deposit_error_comment_is_modified = p_row.getDepositErrorCommentIsModified();
    remark = p_row.getRemark();
    remark_is_set = p_row.getRemarkIsSet();
    remark_is_modified = p_row.getRemarkIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    last_error_history_serial_no = p_row.getLastErrorHistorySerialNo();
    last_error_history_serial_no_is_set = p_row.getLastErrorHistorySerialNoIsSet();
    last_error_history_serial_no_is_modified = p_row.getLastErrorHistorySerialNoIsModified();
    update_person = p_row.getUpdatePerson();
    update_person_is_set = p_row.getUpdatePersonIsSet();
    update_person_is_modified = p_row.getUpdatePersonIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      bank_code_is_set = true;
      bank_code_is_modified = true;
      bank_branch_code_is_set = true;
      bank_branch_code_is_modified = true;
      bank_account_no_is_set = true;
      bank_account_no_is_modified = true;
      deposit_data_reference_no_is_set = true;
      deposit_data_reference_no_is_modified = true;
      deposit_data_account_date_is_set = true;
      deposit_data_account_date_is_modified = true;
      deposit_data_bank_account_type_is_set = true;
      deposit_data_bank_account_type_is_modified = true;
      deposit_data_base_date_is_set = true;
      deposit_data_base_date_is_modified = true;
      deposit_data_deposit_amount_is_set = true;
      deposit_data_deposit_amount_is_modified = true;
      deposit_data_trans_person_code_is_set = true;
      deposit_data_trans_person_code_is_modified = true;
      deposit_data_trans_person_name_is_set = true;
      deposit_data_trans_person_name_is_modified = true;
      cash_transfer_div_is_set = true;
      cash_transfer_div_is_modified = true;
      trade_div_is_set = true;
      trade_div_is_modified = true;
      delivered_bank_name_is_set = true;
      delivered_bank_name_is_modified = true;
      delivered_bank_branch_name_is_set = true;
      delivered_bank_branch_name_is_modified = true;
      summary_is_set = true;
      summary_is_modified = true;
      edi_info_is_set = true;
      edi_info_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      deposit_error_comment_is_set = true;
      deposit_error_comment_is_modified = true;
      remark_is_set = true;
      remark_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      last_error_history_serial_no_is_set = true;
      last_error_history_serial_no_is_modified = true;
      update_person_is_set = true;
      update_person_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      currency_code_is_set = true;
      currency_code_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof BankDepositNotifyRow ) )
       return false;
    return fieldsEqual( (BankDepositNotifyRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBankDepositNotifyRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BankDepositNotifyRow row )
  {
    if ( bank_deposit_notify_id != row.getBankDepositNotifyId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
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
    if ( bank_account_no == null ) {
      if ( row.getBankAccountNo() != null )
        return false;
    } else if ( !bank_account_no.equals( row.getBankAccountNo() ) ) {
        return false;
    }
    if ( deposit_data_reference_no == null ) {
      if ( row.getDepositDataReferenceNo() != null )
        return false;
    } else if ( !deposit_data_reference_no.equals( row.getDepositDataReferenceNo() ) ) {
        return false;
    }
    if ( deposit_data_account_date == null ) {
      if ( row.getDepositDataAccountDate() != null )
        return false;
    } else if ( !deposit_data_account_date.equals( row.getDepositDataAccountDate() ) ) {
        return false;
    }
    if ( deposit_data_bank_account_type == null ) {
      if ( row.getDepositDataBankAccountType() != null )
        return false;
    } else if ( !deposit_data_bank_account_type.equals( row.getDepositDataBankAccountType() ) ) {
        return false;
    }
    if ( deposit_data_base_date == null ) {
      if ( row.getDepositDataBaseDate() != null )
        return false;
    } else if ( !deposit_data_base_date.equals( row.getDepositDataBaseDate() ) ) {
        return false;
    }
    if ( deposit_data_deposit_amount == null ) {
      if ( row.getDepositDataDepositAmount() != null )
        return false;
    } else if ( !deposit_data_deposit_amount.equals( row.getDepositDataDepositAmount() ) ) {
        return false;
    }
    if ( deposit_data_trans_person_code == null ) {
      if ( row.getDepositDataTransPersonCode() != null )
        return false;
    } else if ( !deposit_data_trans_person_code.equals( row.getDepositDataTransPersonCode() ) ) {
        return false;
    }
    if ( deposit_data_trans_person_name == null ) {
      if ( row.getDepositDataTransPersonName() != null )
        return false;
    } else if ( !deposit_data_trans_person_name.equals( row.getDepositDataTransPersonName() ) ) {
        return false;
    }
    if ( cash_transfer_div == null ) {
      if ( row.getCashTransferDiv() != null )
        return false;
    } else if ( !cash_transfer_div.equals( row.getCashTransferDiv() ) ) {
        return false;
    }
    if ( trade_div == null ) {
      if ( row.getTradeDiv() != null )
        return false;
    } else if ( !trade_div.equals( row.getTradeDiv() ) ) {
        return false;
    }
    if ( delivered_bank_name == null ) {
      if ( row.getDeliveredBankName() != null )
        return false;
    } else if ( !delivered_bank_name.equals( row.getDeliveredBankName() ) ) {
        return false;
    }
    if ( delivered_bank_branch_name == null ) {
      if ( row.getDeliveredBankBranchName() != null )
        return false;
    } else if ( !delivered_bank_branch_name.equals( row.getDeliveredBankBranchName() ) ) {
        return false;
    }
    if ( summary == null ) {
      if ( row.getSummary() != null )
        return false;
    } else if ( !summary.equals( row.getSummary() ) ) {
        return false;
    }
    if ( edi_info == null ) {
      if ( row.getEdiInfo() != null )
        return false;
    } else if ( !edi_info.equals( row.getEdiInfo() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( deposit_error_comment == null ) {
      if ( row.getDepositErrorComment() != null )
        return false;
    } else if ( !deposit_error_comment.equals( row.getDepositErrorComment() ) ) {
        return false;
    }
    if ( remark == null ) {
      if ( row.getRemark() != null )
        return false;
    } else if ( !remark.equals( row.getRemark() ) ) {
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
    if ( last_error_history_serial_no != row.getLastErrorHistorySerialNo() )
      return false;
    if ( update_person == null ) {
      if ( row.getUpdatePerson() != null )
        return false;
    } else if ( !update_person.equals( row.getUpdatePerson() ) ) {
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
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( data_load_div == null ) {
      if ( row.getDataLoadDiv() != null )
        return false;
    } else if ( !data_load_div.equals( row.getDataLoadDiv() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) bank_deposit_notify_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (bank_code!=null? bank_code.hashCode(): 0) 
        + (bank_branch_code!=null? bank_branch_code.hashCode(): 0) 
        + (bank_account_no!=null? bank_account_no.hashCode(): 0) 
        + (deposit_data_reference_no!=null? deposit_data_reference_no.hashCode(): 0) 
        + (deposit_data_account_date!=null? deposit_data_account_date.hashCode(): 0) 
        + (deposit_data_bank_account_type!=null? deposit_data_bank_account_type.hashCode(): 0) 
        + (deposit_data_base_date!=null? deposit_data_base_date.hashCode(): 0) 
        + (deposit_data_deposit_amount!=null? deposit_data_deposit_amount.hashCode(): 0) 
        + (deposit_data_trans_person_code!=null? deposit_data_trans_person_code.hashCode(): 0) 
        + (deposit_data_trans_person_name!=null? deposit_data_trans_person_name.hashCode(): 0) 
        + (cash_transfer_div!=null? cash_transfer_div.hashCode(): 0) 
        + (trade_div!=null? trade_div.hashCode(): 0) 
        + (delivered_bank_name!=null? delivered_bank_name.hashCode(): 0) 
        + (delivered_bank_branch_name!=null? delivered_bank_branch_name.hashCode(): 0) 
        + (summary!=null? summary.hashCode(): 0) 
        + (edi_info!=null? edi_info.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (deposit_error_comment!=null? deposit_error_comment.hashCode(): 0) 
        + (remark!=null? remark.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + ((int) last_error_history_serial_no)
        + (update_person!=null? update_person.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (data_load_div!=null? data_load_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !bank_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'bank_code' must be set before inserting.");
		if ( !bank_branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'bank_branch_code' must be set before inserting.");
		if ( !bank_account_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'bank_account_no' must be set before inserting.");
		if ( !deposit_data_reference_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'deposit_data_reference_no' must be set before inserting.");
		if ( !deposit_data_account_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'deposit_data_account_date' must be set before inserting.");
		if ( !cash_transfer_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_transfer_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("bank_deposit_notify_id",new Long(bank_deposit_notify_id));
		map.put("institution_code",institution_code);
		map.put("bank_code",bank_code);
		map.put("bank_branch_code",bank_branch_code);
		map.put("bank_account_no",bank_account_no);
		map.put("deposit_data_reference_no",deposit_data_reference_no);
		map.put("deposit_data_account_date",deposit_data_account_date);
		if ( deposit_data_bank_account_type != null )
			map.put("deposit_data_bank_account_type",deposit_data_bank_account_type);
		if ( deposit_data_base_date != null )
			map.put("deposit_data_base_date",deposit_data_base_date);
		if ( deposit_data_deposit_amount != null )
			map.put("deposit_data_deposit_amount",deposit_data_deposit_amount);
		if ( deposit_data_trans_person_code != null )
			map.put("deposit_data_trans_person_code",deposit_data_trans_person_code);
		if ( deposit_data_trans_person_name != null )
			map.put("deposit_data_trans_person_name",deposit_data_trans_person_name);
		map.put("cash_transfer_div",cash_transfer_div);
		if ( trade_div != null )
			map.put("trade_div",trade_div);
		if ( delivered_bank_name != null )
			map.put("delivered_bank_name",delivered_bank_name);
		if ( delivered_bank_branch_name != null )
			map.put("delivered_bank_branch_name",delivered_bank_branch_name);
		if ( summary != null )
			map.put("summary",summary);
		if ( edi_info != null )
			map.put("edi_info",edi_info);
		if ( status != null )
			map.put("status",status);
		if ( deposit_error_comment != null )
			map.put("deposit_error_comment",deposit_error_comment);
		if ( remark != null )
			map.put("remark",remark);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( last_error_history_serial_no_is_set )
			map.put("last_error_history_serial_no",new Integer(last_error_history_serial_no));
		if ( update_person != null )
			map.put("update_person",update_person);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( currency_code != null )
			map.put("currency_code",currency_code);
		map.put("data_load_div",data_load_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( bank_code_is_modified )
			map.put("bank_code",bank_code);
		if ( bank_branch_code_is_modified )
			map.put("bank_branch_code",bank_branch_code);
		if ( bank_account_no_is_modified )
			map.put("bank_account_no",bank_account_no);
		if ( deposit_data_reference_no_is_modified )
			map.put("deposit_data_reference_no",deposit_data_reference_no);
		if ( deposit_data_account_date_is_modified )
			map.put("deposit_data_account_date",deposit_data_account_date);
		if ( deposit_data_bank_account_type_is_modified )
			map.put("deposit_data_bank_account_type",deposit_data_bank_account_type);
		if ( deposit_data_base_date_is_modified )
			map.put("deposit_data_base_date",deposit_data_base_date);
		if ( deposit_data_deposit_amount_is_modified )
			map.put("deposit_data_deposit_amount",deposit_data_deposit_amount);
		if ( deposit_data_trans_person_code_is_modified )
			map.put("deposit_data_trans_person_code",deposit_data_trans_person_code);
		if ( deposit_data_trans_person_name_is_modified )
			map.put("deposit_data_trans_person_name",deposit_data_trans_person_name);
		if ( cash_transfer_div_is_modified )
			map.put("cash_transfer_div",cash_transfer_div);
		if ( trade_div_is_modified )
			map.put("trade_div",trade_div);
		if ( delivered_bank_name_is_modified )
			map.put("delivered_bank_name",delivered_bank_name);
		if ( delivered_bank_branch_name_is_modified )
			map.put("delivered_bank_branch_name",delivered_bank_branch_name);
		if ( summary_is_modified )
			map.put("summary",summary);
		if ( edi_info_is_modified )
			map.put("edi_info",edi_info);
		if ( status_is_modified )
			map.put("status",status);
		if ( deposit_error_comment_is_modified )
			map.put("deposit_error_comment",deposit_error_comment);
		if ( remark_is_modified )
			map.put("remark",remark);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( last_error_history_serial_no_is_modified )
			map.put("last_error_history_serial_no",new Integer(last_error_history_serial_no));
		if ( update_person_is_modified )
			map.put("update_person",update_person);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( currency_code_is_modified )
			map.put("currency_code",currency_code);
		if (map.size() == 0) {
			if ( bank_code_is_set )
				map.put("bank_code",bank_code);
			if ( bank_branch_code_is_set )
				map.put("bank_branch_code",bank_branch_code);
			if ( bank_account_no_is_set )
				map.put("bank_account_no",bank_account_no);
			if ( deposit_data_reference_no_is_set )
				map.put("deposit_data_reference_no",deposit_data_reference_no);
			if ( deposit_data_account_date_is_set )
				map.put("deposit_data_account_date",deposit_data_account_date);
			map.put("deposit_data_bank_account_type",deposit_data_bank_account_type);
			map.put("deposit_data_base_date",deposit_data_base_date);
			map.put("deposit_data_deposit_amount",deposit_data_deposit_amount);
			map.put("deposit_data_trans_person_code",deposit_data_trans_person_code);
			map.put("deposit_data_trans_person_name",deposit_data_trans_person_name);
			if ( cash_transfer_div_is_set )
				map.put("cash_transfer_div",cash_transfer_div);
			map.put("trade_div",trade_div);
			map.put("delivered_bank_name",delivered_bank_name);
			map.put("delivered_bank_branch_name",delivered_bank_branch_name);
			map.put("summary",summary);
			map.put("edi_info",edi_info);
			map.put("status",status);
			map.put("deposit_error_comment",deposit_error_comment);
			map.put("remark",remark);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			if ( last_error_history_serial_no_is_set )
				map.put("last_error_history_serial_no",new Integer(last_error_history_serial_no));
			map.put("update_person",update_person);
			map.put("created_timestamp",created_timestamp);
			map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("currency_code",currency_code);
		}
		return map;
	}


  /** 
   * <em>bank_deposit_notify_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBankDepositNotifyId()
  {
    return bank_deposit_notify_id;
  }


  /** 
   * <em>bank_deposit_notify_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankDepositNotifyIdIsSet() {
    return bank_deposit_notify_id_is_set;
  }


  /** 
   * <em>bank_deposit_notify_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankDepositNotifyIdIsModified() {
    return bank_deposit_notify_id_is_modified;
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
   * <em>bank_account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBankAccountNo()
  {
    return bank_account_no;
  }


  /** 
   * <em>bank_account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankAccountNoIsSet() {
    return bank_account_no_is_set;
  }


  /** 
   * <em>bank_account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankAccountNoIsModified() {
    return bank_account_no_is_modified;
  }


  /** 
   * <em>deposit_data_reference_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositDataReferenceNo()
  {
    return deposit_data_reference_no;
  }


  /** 
   * <em>deposit_data_reference_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataReferenceNoIsSet() {
    return deposit_data_reference_no_is_set;
  }


  /** 
   * <em>deposit_data_reference_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataReferenceNoIsModified() {
    return deposit_data_reference_no_is_modified;
  }


  /** 
   * <em>deposit_data_account_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositDataAccountDate()
  {
    return deposit_data_account_date;
  }


  /** 
   * <em>deposit_data_account_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataAccountDateIsSet() {
    return deposit_data_account_date_is_set;
  }


  /** 
   * <em>deposit_data_account_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataAccountDateIsModified() {
    return deposit_data_account_date_is_modified;
  }


  /** 
   * <em>deposit_data_bank_account_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositDataBankAccountType()
  {
    return deposit_data_bank_account_type;
  }


  /** 
   * <em>deposit_data_bank_account_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataBankAccountTypeIsSet() {
    return deposit_data_bank_account_type_is_set;
  }


  /** 
   * <em>deposit_data_bank_account_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataBankAccountTypeIsModified() {
    return deposit_data_bank_account_type_is_modified;
  }


  /** 
   * <em>deposit_data_base_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositDataBaseDate()
  {
    return deposit_data_base_date;
  }


  /** 
   * <em>deposit_data_base_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataBaseDateIsSet() {
    return deposit_data_base_date_is_set;
  }


  /** 
   * <em>deposit_data_base_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataBaseDateIsModified() {
    return deposit_data_base_date_is_modified;
  }


  /** 
   * <em>deposit_data_deposit_amount</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositDataDepositAmount()
  {
    return deposit_data_deposit_amount;
  }


  /** 
   * <em>deposit_data_deposit_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataDepositAmountIsSet() {
    return deposit_data_deposit_amount_is_set;
  }


  /** 
   * <em>deposit_data_deposit_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataDepositAmountIsModified() {
    return deposit_data_deposit_amount_is_modified;
  }


  /** 
   * <em>deposit_data_trans_person_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositDataTransPersonCode()
  {
    return deposit_data_trans_person_code;
  }


  /** 
   * <em>deposit_data_trans_person_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataTransPersonCodeIsSet() {
    return deposit_data_trans_person_code_is_set;
  }


  /** 
   * <em>deposit_data_trans_person_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataTransPersonCodeIsModified() {
    return deposit_data_trans_person_code_is_modified;
  }


  /** 
   * <em>deposit_data_trans_person_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositDataTransPersonName()
  {
    return deposit_data_trans_person_name;
  }


  /** 
   * <em>deposit_data_trans_person_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataTransPersonNameIsSet() {
    return deposit_data_trans_person_name_is_set;
  }


  /** 
   * <em>deposit_data_trans_person_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDataTransPersonNameIsModified() {
    return deposit_data_trans_person_name_is_modified;
  }


  /** 
   * <em>cash_transfer_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCashTransferDiv()
  {
    return cash_transfer_div;
  }


  /** 
   * <em>cash_transfer_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashTransferDivIsSet() {
    return cash_transfer_div_is_set;
  }


  /** 
   * <em>cash_transfer_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashTransferDivIsModified() {
    return cash_transfer_div_is_modified;
  }


  /** 
   * <em>trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradeDiv()
  {
    return trade_div;
  }


  /** 
   * <em>trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeDivIsSet() {
    return trade_div_is_set;
  }


  /** 
   * <em>trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeDivIsModified() {
    return trade_div_is_modified;
  }


  /** 
   * <em>delivered_bank_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeliveredBankName()
  {
    return delivered_bank_name;
  }


  /** 
   * <em>delivered_bank_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveredBankNameIsSet() {
    return delivered_bank_name_is_set;
  }


  /** 
   * <em>delivered_bank_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveredBankNameIsModified() {
    return delivered_bank_name_is_modified;
  }


  /** 
   * <em>delivered_bank_branch_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeliveredBankBranchName()
  {
    return delivered_bank_branch_name;
  }


  /** 
   * <em>delivered_bank_branch_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveredBankBranchNameIsSet() {
    return delivered_bank_branch_name_is_set;
  }


  /** 
   * <em>delivered_bank_branch_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveredBankBranchNameIsModified() {
    return delivered_bank_branch_name_is_modified;
  }


  /** 
   * <em>summary</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSummary()
  {
    return summary;
  }


  /** 
   * <em>summary</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSummaryIsSet() {
    return summary_is_set;
  }


  /** 
   * <em>summary</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSummaryIsModified() {
    return summary_is_modified;
  }


  /** 
   * <em>edi_info</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEdiInfo()
  {
    return edi_info;
  }


  /** 
   * <em>edi_info</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEdiInfoIsSet() {
    return edi_info_is_set;
  }


  /** 
   * <em>edi_info</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEdiInfoIsModified() {
    return edi_info_is_modified;
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
   * <em>deposit_error_comment</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositErrorComment()
  {
    return deposit_error_comment;
  }


  /** 
   * <em>deposit_error_comment</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositErrorCommentIsSet() {
    return deposit_error_comment_is_set;
  }


  /** 
   * <em>deposit_error_comment</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositErrorCommentIsModified() {
    return deposit_error_comment_is_modified;
  }


  /** 
   * <em>remark</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRemark()
  {
    return remark;
  }


  /** 
   * <em>remark</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkIsSet() {
    return remark_is_set;
  }


  /** 
   * <em>remark</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkIsModified() {
    return remark_is_modified;
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
   * <em>last_error_history_serial_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLastErrorHistorySerialNo()
  {
    return last_error_history_serial_no;
  }


  /** 
   * <em>last_error_history_serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastErrorHistorySerialNoIsSet() {
    return last_error_history_serial_no_is_set;
  }


  /** 
   * <em>last_error_history_serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastErrorHistorySerialNoIsModified() {
    return last_error_history_serial_no_is_modified;
  }


  /** 
   * <em>update_person</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getUpdatePerson()
  {
    return update_person;
  }


  /** 
   * <em>update_person</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUpdatePersonIsSet() {
    return update_person_is_set;
  }


  /** 
   * <em>update_person</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUpdatePersonIsModified() {
    return update_person_is_modified;
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
   * <em>data_load_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDataLoadDiv()
  {
    return data_load_div;
  }


  /** 
   * <em>data_load_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataLoadDivIsSet() {
    return data_load_div_is_set;
  }


  /** 
   * <em>data_load_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataLoadDivIsModified() {
    return data_load_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new BankDepositNotifyPK(bank_deposit_notify_id, institution_code, data_load_div);
  }


  /** 
   * <em>bank_deposit_notify_id</em>カラムの値を設定します。 
   *
   * @@param p_bankDepositNotifyId <em>bank_deposit_notify_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBankDepositNotifyId( long p_bankDepositNotifyId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bank_deposit_notify_id = p_bankDepositNotifyId;
    bank_deposit_notify_id_is_set = true;
    bank_deposit_notify_id_is_modified = true;
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
   * <em>bank_account_no</em>カラムの値を設定します。 
   *
   * @@param p_bankAccountNo <em>bank_account_no</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setBankAccountNo( String p_bankAccountNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bank_account_no = p_bankAccountNo;
    bank_account_no_is_set = true;
    bank_account_no_is_modified = true;
  }


  /** 
   * <em>deposit_data_reference_no</em>カラムの値を設定します。 
   *
   * @@param p_depositDataReferenceNo <em>deposit_data_reference_no</em>カラムの値をあらわす22桁以下のString値 
   */
  public final void setDepositDataReferenceNo( String p_depositDataReferenceNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_data_reference_no = p_depositDataReferenceNo;
    deposit_data_reference_no_is_set = true;
    deposit_data_reference_no_is_modified = true;
  }


  /** 
   * <em>deposit_data_account_date</em>カラムの値を設定します。 
   *
   * @@param p_depositDataAccountDate <em>deposit_data_account_date</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setDepositDataAccountDate( String p_depositDataAccountDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_data_account_date = p_depositDataAccountDate;
    deposit_data_account_date_is_set = true;
    deposit_data_account_date_is_modified = true;
  }


  /** 
   * <em>deposit_data_bank_account_type</em>カラムの値を設定します。 
   *
   * @@param p_depositDataBankAccountType <em>deposit_data_bank_account_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDepositDataBankAccountType( String p_depositDataBankAccountType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_data_bank_account_type = p_depositDataBankAccountType;
    deposit_data_bank_account_type_is_set = true;
    deposit_data_bank_account_type_is_modified = true;
  }


  /** 
   * <em>deposit_data_base_date</em>カラムの値を設定します。 
   *
   * @@param p_depositDataBaseDate <em>deposit_data_base_date</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setDepositDataBaseDate( String p_depositDataBaseDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_data_base_date = p_depositDataBaseDate;
    deposit_data_base_date_is_set = true;
    deposit_data_base_date_is_modified = true;
  }


  /** 
   * <em>deposit_data_deposit_amount</em>カラムの値を設定します。 
   *
   * @@param p_depositDataDepositAmount <em>deposit_data_deposit_amount</em>カラムの値をあらわす12桁以下のString値 
   */
  public final void setDepositDataDepositAmount( String p_depositDataDepositAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_data_deposit_amount = p_depositDataDepositAmount;
    deposit_data_deposit_amount_is_set = true;
    deposit_data_deposit_amount_is_modified = true;
  }


  /** 
   * <em>deposit_data_trans_person_code</em>カラムの値を設定します。 
   *
   * @@param p_depositDataTransPersonCode <em>deposit_data_trans_person_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setDepositDataTransPersonCode( String p_depositDataTransPersonCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_data_trans_person_code = p_depositDataTransPersonCode;
    deposit_data_trans_person_code_is_set = true;
    deposit_data_trans_person_code_is_modified = true;
  }


  /** 
   * <em>deposit_data_trans_person_name</em>カラムの値を設定します。 
   *
   * @@param p_depositDataTransPersonName <em>deposit_data_trans_person_name</em>カラムの値をあらわす96桁以下のString値 
   */
  public final void setDepositDataTransPersonName( String p_depositDataTransPersonName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_data_trans_person_name = p_depositDataTransPersonName;
    deposit_data_trans_person_name_is_set = true;
    deposit_data_trans_person_name_is_modified = true;
  }


  /** 
   * <em>cash_transfer_div</em>カラムの値を設定します。 
   *
   * @@param p_cashTransferDiv <em>cash_transfer_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCashTransferDiv( String p_cashTransferDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_transfer_div = p_cashTransferDiv;
    cash_transfer_div_is_set = true;
    cash_transfer_div_is_modified = true;
  }


  /** 
   * <em>trade_div</em>カラムの値を設定します。 
   *
   * @@param p_tradeDiv <em>trade_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setTradeDiv( String p_tradeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_div = p_tradeDiv;
    trade_div_is_set = true;
    trade_div_is_modified = true;
  }


  /** 
   * <em>delivered_bank_name</em>カラムの値を設定します。 
   *
   * @@param p_deliveredBankName <em>delivered_bank_name</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setDeliveredBankName( String p_deliveredBankName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivered_bank_name = p_deliveredBankName;
    delivered_bank_name_is_set = true;
    delivered_bank_name_is_modified = true;
  }


  /** 
   * <em>delivered_bank_branch_name</em>カラムの値を設定します。 
   *
   * @@param p_deliveredBankBranchName <em>delivered_bank_branch_name</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setDeliveredBankBranchName( String p_deliveredBankBranchName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivered_bank_branch_name = p_deliveredBankBranchName;
    delivered_bank_branch_name_is_set = true;
    delivered_bank_branch_name_is_modified = true;
  }


  /** 
   * <em>summary</em>カラムの値を設定します。 
   *
   * @@param p_summary <em>summary</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setSummary( String p_summary )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    summary = p_summary;
    summary_is_set = true;
    summary_is_modified = true;
  }


  /** 
   * <em>edi_info</em>カラムの値を設定します。 
   *
   * @@param p_ediInfo <em>edi_info</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setEdiInfo( String p_ediInfo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    edi_info = p_ediInfo;
    edi_info_is_set = true;
    edi_info_is_modified = true;
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
   * <em>deposit_error_comment</em>カラムの値を設定します。 
   *
   * @@param p_depositErrorComment <em>deposit_error_comment</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setDepositErrorComment( String p_depositErrorComment )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_error_comment = p_depositErrorComment;
    deposit_error_comment_is_set = true;
    deposit_error_comment_is_modified = true;
  }


  /** 
   * <em>remark</em>カラムの値を設定します。 
   *
   * @@param p_remark <em>remark</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setRemark( String p_remark )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark = p_remark;
    remark_is_set = true;
    remark_is_modified = true;
  }


  /** 
   * <em>branch_code</em>カラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす20桁以下のString値 
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
   * <em>last_error_history_serial_no</em>カラムの値を設定します。 
   *
   * @@param p_lastErrorHistorySerialNo <em>last_error_history_serial_no</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setLastErrorHistorySerialNo( int p_lastErrorHistorySerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_error_history_serial_no = p_lastErrorHistorySerialNo;
    last_error_history_serial_no_is_set = true;
    last_error_history_serial_no_is_modified = true;
  }


  /** 
   * <em>update_person</em>カラムの値を設定します。 
   *
   * @@param p_updatePerson <em>update_person</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setUpdatePerson( String p_updatePerson )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    update_person = p_updatePerson;
    update_person_is_set = true;
    update_person_is_modified = true;
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
   * <em>currency_code</em>カラムの値を設定します。 
   *
   * @@param p_currencyCode <em>currency_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>data_load_div</em>カラムの値を設定します。 
   *
   * @@param p_dataLoadDiv <em>data_load_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDataLoadDiv( String p_dataLoadDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_load_div = p_dataLoadDiv;
    data_load_div_is_set = true;
    data_load_div_is_modified = true;
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
                if ( name.equals("bank_deposit_notify_id") ) {
                    return new Long( bank_deposit_notify_id );
                }
                else if ( name.equals("bank_code") ) {
                    return this.bank_code;
                }
                else if ( name.equals("bank_branch_code") ) {
                    return this.bank_branch_code;
                }
                else if ( name.equals("bank_account_no") ) {
                    return this.bank_account_no;
                }
                else if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("cash_transfer_div") ) {
                    return this.cash_transfer_div;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                break;
            case 'd':
                if ( name.equals("deposit_data_reference_no") ) {
                    return this.deposit_data_reference_no;
                }
                else if ( name.equals("deposit_data_account_date") ) {
                    return this.deposit_data_account_date;
                }
                else if ( name.equals("deposit_data_bank_account_type") ) {
                    return this.deposit_data_bank_account_type;
                }
                else if ( name.equals("deposit_data_base_date") ) {
                    return this.deposit_data_base_date;
                }
                else if ( name.equals("deposit_data_deposit_amount") ) {
                    return this.deposit_data_deposit_amount;
                }
                else if ( name.equals("deposit_data_trans_person_code") ) {
                    return this.deposit_data_trans_person_code;
                }
                else if ( name.equals("deposit_data_trans_person_name") ) {
                    return this.deposit_data_trans_person_name;
                }
                else if ( name.equals("delivered_bank_name") ) {
                    return this.delivered_bank_name;
                }
                else if ( name.equals("delivered_bank_branch_name") ) {
                    return this.delivered_bank_branch_name;
                }
                else if ( name.equals("deposit_error_comment") ) {
                    return this.deposit_error_comment;
                }
                else if ( name.equals("data_load_div") ) {
                    return this.data_load_div;
                }
                break;
            case 'e':
                if ( name.equals("edi_info") ) {
                    return this.edi_info;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_error_history_serial_no") ) {
                    return new Integer( last_error_history_serial_no );
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'r':
                if ( name.equals("remark") ) {
                    return this.remark;
                }
                break;
            case 's':
                if ( name.equals("summary") ) {
                    return this.summary;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trade_div") ) {
                    return this.trade_div;
                }
                break;
            case 'u':
                if ( name.equals("update_person") ) {
                    return this.update_person;
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
                    if ( value != null )
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
                if ( name.equals("bank_deposit_notify_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'bank_deposit_notify_id' must be Long: '"+value+"' is not." );
                    this.bank_deposit_notify_id = ((Long) value).longValue();
                    if (this.bank_deposit_notify_id_is_set)
                        this.bank_deposit_notify_id_is_modified = true;
                    this.bank_deposit_notify_id_is_set = true;
                    return;
                }
                else if ( name.equals("bank_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bank_code' must be String: '"+value+"' is not." );
                    this.bank_code = (String) value;
                    if (this.bank_code_is_set)
                        this.bank_code_is_modified = true;
                    this.bank_code_is_set = true;
                    return;
                }
                else if ( name.equals("bank_branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bank_branch_code' must be String: '"+value+"' is not." );
                    this.bank_branch_code = (String) value;
                    if (this.bank_branch_code_is_set)
                        this.bank_branch_code_is_modified = true;
                    this.bank_branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("bank_account_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bank_account_no' must be String: '"+value+"' is not." );
                    this.bank_account_no = (String) value;
                    if (this.bank_account_no_is_set)
                        this.bank_account_no_is_modified = true;
                    this.bank_account_no_is_set = true;
                    return;
                }
                else if ( name.equals("branch_code") ) {
                    if ( value != null )
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
                if ( name.equals("cash_transfer_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cash_transfer_div' must be String: '"+value+"' is not." );
                    this.cash_transfer_div = (String) value;
                    if (this.cash_transfer_div_is_set)
                        this.cash_transfer_div_is_modified = true;
                    this.cash_transfer_div_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("currency_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_code' must be String: '"+value+"' is not." );
                    this.currency_code = (String) value;
                    if (this.currency_code_is_set)
                        this.currency_code_is_modified = true;
                    this.currency_code_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("deposit_data_reference_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_data_reference_no' must be String: '"+value+"' is not." );
                    this.deposit_data_reference_no = (String) value;
                    if (this.deposit_data_reference_no_is_set)
                        this.deposit_data_reference_no_is_modified = true;
                    this.deposit_data_reference_no_is_set = true;
                    return;
                }
                else if ( name.equals("deposit_data_account_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_data_account_date' must be String: '"+value+"' is not." );
                    this.deposit_data_account_date = (String) value;
                    if (this.deposit_data_account_date_is_set)
                        this.deposit_data_account_date_is_modified = true;
                    this.deposit_data_account_date_is_set = true;
                    return;
                }
                else if ( name.equals("deposit_data_bank_account_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_data_bank_account_type' must be String: '"+value+"' is not." );
                    this.deposit_data_bank_account_type = (String) value;
                    if (this.deposit_data_bank_account_type_is_set)
                        this.deposit_data_bank_account_type_is_modified = true;
                    this.deposit_data_bank_account_type_is_set = true;
                    return;
                }
                else if ( name.equals("deposit_data_base_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_data_base_date' must be String: '"+value+"' is not." );
                    this.deposit_data_base_date = (String) value;
                    if (this.deposit_data_base_date_is_set)
                        this.deposit_data_base_date_is_modified = true;
                    this.deposit_data_base_date_is_set = true;
                    return;
                }
                else if ( name.equals("deposit_data_deposit_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_data_deposit_amount' must be String: '"+value+"' is not." );
                    this.deposit_data_deposit_amount = (String) value;
                    if (this.deposit_data_deposit_amount_is_set)
                        this.deposit_data_deposit_amount_is_modified = true;
                    this.deposit_data_deposit_amount_is_set = true;
                    return;
                }
                else if ( name.equals("deposit_data_trans_person_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_data_trans_person_code' must be String: '"+value+"' is not." );
                    this.deposit_data_trans_person_code = (String) value;
                    if (this.deposit_data_trans_person_code_is_set)
                        this.deposit_data_trans_person_code_is_modified = true;
                    this.deposit_data_trans_person_code_is_set = true;
                    return;
                }
                else if ( name.equals("deposit_data_trans_person_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_data_trans_person_name' must be String: '"+value+"' is not." );
                    this.deposit_data_trans_person_name = (String) value;
                    if (this.deposit_data_trans_person_name_is_set)
                        this.deposit_data_trans_person_name_is_modified = true;
                    this.deposit_data_trans_person_name_is_set = true;
                    return;
                }
                else if ( name.equals("delivered_bank_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delivered_bank_name' must be String: '"+value+"' is not." );
                    this.delivered_bank_name = (String) value;
                    if (this.delivered_bank_name_is_set)
                        this.delivered_bank_name_is_modified = true;
                    this.delivered_bank_name_is_set = true;
                    return;
                }
                else if ( name.equals("delivered_bank_branch_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delivered_bank_branch_name' must be String: '"+value+"' is not." );
                    this.delivered_bank_branch_name = (String) value;
                    if (this.delivered_bank_branch_name_is_set)
                        this.delivered_bank_branch_name_is_modified = true;
                    this.delivered_bank_branch_name_is_set = true;
                    return;
                }
                else if ( name.equals("deposit_error_comment") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_error_comment' must be String: '"+value+"' is not." );
                    this.deposit_error_comment = (String) value;
                    if (this.deposit_error_comment_is_set)
                        this.deposit_error_comment_is_modified = true;
                    this.deposit_error_comment_is_set = true;
                    return;
                }
                else if ( name.equals("data_load_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'data_load_div' must be String: '"+value+"' is not." );
                    this.data_load_div = (String) value;
                    if (this.data_load_div_is_set)
                        this.data_load_div_is_modified = true;
                    this.data_load_div_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("edi_info") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'edi_info' must be String: '"+value+"' is not." );
                    this.edi_info = (String) value;
                    if (this.edi_info_is_set)
                        this.edi_info_is_modified = true;
                    this.edi_info_is_set = true;
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
                if ( name.equals("last_error_history_serial_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'last_error_history_serial_no' must be Integer: '"+value+"' is not." );
                    this.last_error_history_serial_no = ((Integer) value).intValue();
                    if (this.last_error_history_serial_no_is_set)
                        this.last_error_history_serial_no_is_modified = true;
                    this.last_error_history_serial_no_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( value != null )
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
                if ( name.equals("remark") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'remark' must be String: '"+value+"' is not." );
                    this.remark = (String) value;
                    if (this.remark_is_set)
                        this.remark_is_modified = true;
                    this.remark_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("summary") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'summary' must be String: '"+value+"' is not." );
                    this.summary = (String) value;
                    if (this.summary_is_set)
                        this.summary_is_modified = true;
                    this.summary_is_set = true;
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
                break;
            case 't':
                if ( name.equals("trade_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trade_div' must be String: '"+value+"' is not." );
                    this.trade_div = (String) value;
                    if (this.trade_div_is_set)
                        this.trade_div_is_modified = true;
                    this.trade_div_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("update_person") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'update_person' must be String: '"+value+"' is not." );
                    this.update_person = (String) value;
                    if (this.update_person_is_set)
                        this.update_person_is_modified = true;
                    this.update_person_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
