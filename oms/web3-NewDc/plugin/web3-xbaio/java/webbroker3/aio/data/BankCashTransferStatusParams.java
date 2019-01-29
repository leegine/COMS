head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.39.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankCashTransferStatusParams.java;


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
 * bank_cash_transfer_statusテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BankCashTransferStatusRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BankCashTransferStatusParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BankCashTransferStatusParams}が{@@link BankCashTransferStatusRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BankCashTransferStatusPK 
 * @@see BankCashTransferStatusRow 
 */
public class BankCashTransferStatusParams extends Params implements BankCashTransferStatusRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bank_cash_transfer_status";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BankCashTransferStatusRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BankCashTransferStatusRow.TYPE;
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
   * <em>pay_scheme_id</em>カラムの値 
   */
  public  String  pay_scheme_id;

  /** 
   * <em>amount</em>カラムの値 
   */
  public  Long  amount;

  /** 
   * <em>order_date_time</em>カラムの値 
   */
  public  java.sql.Timestamp  order_date_time;

  /** 
   * <em>delivery_scheduled_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_scheduled_date;

  /** 
   * <em>base_date</em>カラムの値 
   */
  public  java.sql.Timestamp  base_date;

  /** 
   * <em>input_div</em>カラムの値 
   */
  public  String  input_div;

  /** 
   * <em>comondebi_capture_date</em>カラムの値 
   */
  public  java.sql.Timestamp  comondebi_capture_date;

  /** 
   * <em>center_pay_id</em>カラムの値 
   */
  public  String  center_pay_id;

  /** 
   * <em>order_status_flag</em>カラムの値 
   */
  public  String  order_status_flag;

  /** 
   * <em>order_request_time</em>カラムの値 
   */
  public  java.sql.Timestamp  order_request_time;

  /** 
   * <em>order_response_time</em>カラムの値 
   */
  public  java.sql.Timestamp  order_response_time;

  /** 
   * <em>start_status_flag</em>カラムの値 
   */
  public  String  start_status_flag;

  /** 
   * <em>start_request_time</em>カラムの値 
   */
  public  java.sql.Timestamp  start_request_time;

  /** 
   * <em>start_response_time</em>カラムの値 
   */
  public  java.sql.Timestamp  start_response_time;

  /** 
   * <em>result_status_flag</em>カラムの値 
   */
  public  String  result_status_flag;

  /** 
   * <em>result_request_time</em>カラムの値 
   */
  public  java.sql.Timestamp  result_request_time;

  /** 
   * <em>result_response_time</em>カラムの値 
   */
  public  java.sql.Timestamp  result_response_time;

  /** 
   * <em>transaction_status</em>カラムの値 
   */
  public  String  transaction_status;

  /** 
   * <em>transaction_time</em>カラムの値 
   */
  public  java.sql.Timestamp  transaction_time;

  /** 
   * <em>batch_flag</em>カラムの値 
   */
  public  String  batch_flag;

  /** 
   * <em>last_update_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_update_timestamp;

  /** 
   * <em>last_update_user</em>カラムの値 
   */
  public  String  last_update_user;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean pay_scheme_id_is_set = false;
  private boolean pay_scheme_id_is_modified = false;
  private boolean amount_is_set = false;
  private boolean amount_is_modified = false;
  private boolean order_date_time_is_set = false;
  private boolean order_date_time_is_modified = false;
  private boolean delivery_scheduled_date_is_set = false;
  private boolean delivery_scheduled_date_is_modified = false;
  private boolean base_date_is_set = false;
  private boolean base_date_is_modified = false;
  private boolean input_div_is_set = false;
  private boolean input_div_is_modified = false;
  private boolean comondebi_capture_date_is_set = false;
  private boolean comondebi_capture_date_is_modified = false;
  private boolean center_pay_id_is_set = false;
  private boolean center_pay_id_is_modified = false;
  private boolean order_status_flag_is_set = false;
  private boolean order_status_flag_is_modified = false;
  private boolean order_request_time_is_set = false;
  private boolean order_request_time_is_modified = false;
  private boolean order_response_time_is_set = false;
  private boolean order_response_time_is_modified = false;
  private boolean start_status_flag_is_set = false;
  private boolean start_status_flag_is_modified = false;
  private boolean start_request_time_is_set = false;
  private boolean start_request_time_is_modified = false;
  private boolean start_response_time_is_set = false;
  private boolean start_response_time_is_modified = false;
  private boolean result_status_flag_is_set = false;
  private boolean result_status_flag_is_modified = false;
  private boolean result_request_time_is_set = false;
  private boolean result_request_time_is_modified = false;
  private boolean result_response_time_is_set = false;
  private boolean result_response_time_is_modified = false;
  private boolean transaction_status_is_set = false;
  private boolean transaction_status_is_modified = false;
  private boolean transaction_time_is_set = false;
  private boolean transaction_time_is_modified = false;
  private boolean batch_flag_is_set = false;
  private boolean batch_flag_is_modified = false;
  private boolean last_update_timestamp_is_set = false;
  private boolean last_update_timestamp_is_modified = false;
  private boolean last_update_user_is_set = false;
  private boolean last_update_user_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "order_request_number=" + order_request_number
      + "," + "account_code=" +account_code
      + "," + "pay_scheme_id=" +pay_scheme_id
      + "," + "amount=" +amount
      + "," + "order_date_time=" +order_date_time
      + "," + "delivery_scheduled_date=" +delivery_scheduled_date
      + "," + "base_date=" +base_date
      + "," + "input_div=" +input_div
      + "," + "comondebi_capture_date=" +comondebi_capture_date
      + "," + "center_pay_id=" +center_pay_id
      + "," + "order_status_flag=" +order_status_flag
      + "," + "order_request_time=" +order_request_time
      + "," + "order_response_time=" +order_response_time
      + "," + "start_status_flag=" +start_status_flag
      + "," + "start_request_time=" +start_request_time
      + "," + "start_response_time=" +start_response_time
      + "," + "result_status_flag=" +result_status_flag
      + "," + "result_request_time=" +result_request_time
      + "," + "result_response_time=" +result_response_time
      + "," + "transaction_status=" +transaction_status
      + "," + "transaction_time=" +transaction_time
      + "," + "batch_flag=" +batch_flag
      + "," + "last_update_timestamp=" +last_update_timestamp
      + "," + "last_update_user=" +last_update_user
      + "}";
  }


  /** 
   * 値が未設定のBankCashTransferStatusParamsオブジェクトを作成します。 
   */
  public BankCashTransferStatusParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    order_request_number_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBankCashTransferStatusRowオブジェクトの値を利用してBankCashTransferStatusParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBankCashTransferStatusRowオブジェクト 
   */
  public BankCashTransferStatusParams( BankCashTransferStatusRow p_row )
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
    pay_scheme_id = p_row.getPaySchemeId();
    pay_scheme_id_is_set = p_row.getPaySchemeIdIsSet();
    pay_scheme_id_is_modified = p_row.getPaySchemeIdIsModified();
    if ( !p_row.getAmountIsNull() )
      amount = new Long( p_row.getAmount() );
    amount_is_set = p_row.getAmountIsSet();
    amount_is_modified = p_row.getAmountIsModified();
    order_date_time = p_row.getOrderDateTime();
    order_date_time_is_set = p_row.getOrderDateTimeIsSet();
    order_date_time_is_modified = p_row.getOrderDateTimeIsModified();
    delivery_scheduled_date = p_row.getDeliveryScheduledDate();
    delivery_scheduled_date_is_set = p_row.getDeliveryScheduledDateIsSet();
    delivery_scheduled_date_is_modified = p_row.getDeliveryScheduledDateIsModified();
    base_date = p_row.getBaseDate();
    base_date_is_set = p_row.getBaseDateIsSet();
    base_date_is_modified = p_row.getBaseDateIsModified();
    input_div = p_row.getInputDiv();
    input_div_is_set = p_row.getInputDivIsSet();
    input_div_is_modified = p_row.getInputDivIsModified();
    comondebi_capture_date = p_row.getComondebiCaptureDate();
    comondebi_capture_date_is_set = p_row.getComondebiCaptureDateIsSet();
    comondebi_capture_date_is_modified = p_row.getComondebiCaptureDateIsModified();
    center_pay_id = p_row.getCenterPayId();
    center_pay_id_is_set = p_row.getCenterPayIdIsSet();
    center_pay_id_is_modified = p_row.getCenterPayIdIsModified();
    order_status_flag = p_row.getOrderStatusFlag();
    order_status_flag_is_set = p_row.getOrderStatusFlagIsSet();
    order_status_flag_is_modified = p_row.getOrderStatusFlagIsModified();
    order_request_time = p_row.getOrderRequestTime();
    order_request_time_is_set = p_row.getOrderRequestTimeIsSet();
    order_request_time_is_modified = p_row.getOrderRequestTimeIsModified();
    order_response_time = p_row.getOrderResponseTime();
    order_response_time_is_set = p_row.getOrderResponseTimeIsSet();
    order_response_time_is_modified = p_row.getOrderResponseTimeIsModified();
    start_status_flag = p_row.getStartStatusFlag();
    start_status_flag_is_set = p_row.getStartStatusFlagIsSet();
    start_status_flag_is_modified = p_row.getStartStatusFlagIsModified();
    start_request_time = p_row.getStartRequestTime();
    start_request_time_is_set = p_row.getStartRequestTimeIsSet();
    start_request_time_is_modified = p_row.getStartRequestTimeIsModified();
    start_response_time = p_row.getStartResponseTime();
    start_response_time_is_set = p_row.getStartResponseTimeIsSet();
    start_response_time_is_modified = p_row.getStartResponseTimeIsModified();
    result_status_flag = p_row.getResultStatusFlag();
    result_status_flag_is_set = p_row.getResultStatusFlagIsSet();
    result_status_flag_is_modified = p_row.getResultStatusFlagIsModified();
    result_request_time = p_row.getResultRequestTime();
    result_request_time_is_set = p_row.getResultRequestTimeIsSet();
    result_request_time_is_modified = p_row.getResultRequestTimeIsModified();
    result_response_time = p_row.getResultResponseTime();
    result_response_time_is_set = p_row.getResultResponseTimeIsSet();
    result_response_time_is_modified = p_row.getResultResponseTimeIsModified();
    transaction_status = p_row.getTransactionStatus();
    transaction_status_is_set = p_row.getTransactionStatusIsSet();
    transaction_status_is_modified = p_row.getTransactionStatusIsModified();
    transaction_time = p_row.getTransactionTime();
    transaction_time_is_set = p_row.getTransactionTimeIsSet();
    transaction_time_is_modified = p_row.getTransactionTimeIsModified();
    batch_flag = p_row.getBatchFlag();
    batch_flag_is_set = p_row.getBatchFlagIsSet();
    batch_flag_is_modified = p_row.getBatchFlagIsModified();
    last_update_timestamp = p_row.getLastUpdateTimestamp();
    last_update_timestamp_is_set = p_row.getLastUpdateTimestampIsSet();
    last_update_timestamp_is_modified = p_row.getLastUpdateTimestampIsModified();
    last_update_user = p_row.getLastUpdateUser();
    last_update_user_is_set = p_row.getLastUpdateUserIsSet();
    last_update_user_is_modified = p_row.getLastUpdateUserIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      account_code_is_set = true;
      account_code_is_modified = true;
      pay_scheme_id_is_set = true;
      pay_scheme_id_is_modified = true;
      amount_is_set = true;
      amount_is_modified = true;
      order_date_time_is_set = true;
      order_date_time_is_modified = true;
      delivery_scheduled_date_is_set = true;
      delivery_scheduled_date_is_modified = true;
      base_date_is_set = true;
      base_date_is_modified = true;
      input_div_is_set = true;
      input_div_is_modified = true;
      comondebi_capture_date_is_set = true;
      comondebi_capture_date_is_modified = true;
      center_pay_id_is_set = true;
      center_pay_id_is_modified = true;
      order_status_flag_is_set = true;
      order_status_flag_is_modified = true;
      order_request_time_is_set = true;
      order_request_time_is_modified = true;
      order_response_time_is_set = true;
      order_response_time_is_modified = true;
      start_status_flag_is_set = true;
      start_status_flag_is_modified = true;
      start_request_time_is_set = true;
      start_request_time_is_modified = true;
      start_response_time_is_set = true;
      start_response_time_is_modified = true;
      result_status_flag_is_set = true;
      result_status_flag_is_modified = true;
      result_request_time_is_set = true;
      result_request_time_is_modified = true;
      result_response_time_is_set = true;
      result_response_time_is_modified = true;
      transaction_status_is_set = true;
      transaction_status_is_modified = true;
      transaction_time_is_set = true;
      transaction_time_is_modified = true;
      batch_flag_is_set = true;
      batch_flag_is_modified = true;
      last_update_timestamp_is_set = true;
      last_update_timestamp_is_modified = true;
      last_update_user_is_set = true;
      last_update_user_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof BankCashTransferStatusRow ) )
       return false;
    return fieldsEqual( (BankCashTransferStatusRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBankCashTransferStatusRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BankCashTransferStatusRow row )
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
    if ( pay_scheme_id == null ) {
      if ( row.getPaySchemeId() != null )
        return false;
    } else if ( !pay_scheme_id.equals( row.getPaySchemeId() ) ) {
        return false;
    }
    if ( amount == null ) {
      if ( !row.getAmountIsNull() )
        return false;
    } else if ( row.getAmountIsNull() || ( amount.longValue() != row.getAmount() ) ) {
        return false;
    }
    if ( order_date_time == null ) {
      if ( row.getOrderDateTime() != null )
        return false;
    } else if ( !order_date_time.equals( row.getOrderDateTime() ) ) {
        return false;
    }
    if ( delivery_scheduled_date == null ) {
      if ( row.getDeliveryScheduledDate() != null )
        return false;
    } else if ( !delivery_scheduled_date.equals( row.getDeliveryScheduledDate() ) ) {
        return false;
    }
    if ( base_date == null ) {
      if ( row.getBaseDate() != null )
        return false;
    } else if ( !base_date.equals( row.getBaseDate() ) ) {
        return false;
    }
    if ( input_div == null ) {
      if ( row.getInputDiv() != null )
        return false;
    } else if ( !input_div.equals( row.getInputDiv() ) ) {
        return false;
    }
    if ( comondebi_capture_date == null ) {
      if ( row.getComondebiCaptureDate() != null )
        return false;
    } else if ( !comondebi_capture_date.equals( row.getComondebiCaptureDate() ) ) {
        return false;
    }
    if ( center_pay_id == null ) {
      if ( row.getCenterPayId() != null )
        return false;
    } else if ( !center_pay_id.equals( row.getCenterPayId() ) ) {
        return false;
    }
    if ( order_status_flag == null ) {
      if ( row.getOrderStatusFlag() != null )
        return false;
    } else if ( !order_status_flag.equals( row.getOrderStatusFlag() ) ) {
        return false;
    }
    if ( order_request_time == null ) {
      if ( row.getOrderRequestTime() != null )
        return false;
    } else if ( !order_request_time.equals( row.getOrderRequestTime() ) ) {
        return false;
    }
    if ( order_response_time == null ) {
      if ( row.getOrderResponseTime() != null )
        return false;
    } else if ( !order_response_time.equals( row.getOrderResponseTime() ) ) {
        return false;
    }
    if ( start_status_flag == null ) {
      if ( row.getStartStatusFlag() != null )
        return false;
    } else if ( !start_status_flag.equals( row.getStartStatusFlag() ) ) {
        return false;
    }
    if ( start_request_time == null ) {
      if ( row.getStartRequestTime() != null )
        return false;
    } else if ( !start_request_time.equals( row.getStartRequestTime() ) ) {
        return false;
    }
    if ( start_response_time == null ) {
      if ( row.getStartResponseTime() != null )
        return false;
    } else if ( !start_response_time.equals( row.getStartResponseTime() ) ) {
        return false;
    }
    if ( result_status_flag == null ) {
      if ( row.getResultStatusFlag() != null )
        return false;
    } else if ( !result_status_flag.equals( row.getResultStatusFlag() ) ) {
        return false;
    }
    if ( result_request_time == null ) {
      if ( row.getResultRequestTime() != null )
        return false;
    } else if ( !result_request_time.equals( row.getResultRequestTime() ) ) {
        return false;
    }
    if ( result_response_time == null ) {
      if ( row.getResultResponseTime() != null )
        return false;
    } else if ( !result_response_time.equals( row.getResultResponseTime() ) ) {
        return false;
    }
    if ( transaction_status == null ) {
      if ( row.getTransactionStatus() != null )
        return false;
    } else if ( !transaction_status.equals( row.getTransactionStatus() ) ) {
        return false;
    }
    if ( transaction_time == null ) {
      if ( row.getTransactionTime() != null )
        return false;
    } else if ( !transaction_time.equals( row.getTransactionTime() ) ) {
        return false;
    }
    if ( batch_flag == null ) {
      if ( row.getBatchFlag() != null )
        return false;
    } else if ( !batch_flag.equals( row.getBatchFlag() ) ) {
        return false;
    }
    if ( last_update_timestamp == null ) {
      if ( row.getLastUpdateTimestamp() != null )
        return false;
    } else if ( !last_update_timestamp.equals( row.getLastUpdateTimestamp() ) ) {
        return false;
    }
    if ( last_update_user == null ) {
      if ( row.getLastUpdateUser() != null )
        return false;
    } else if ( !last_update_user.equals( row.getLastUpdateUser() ) ) {
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
        + (pay_scheme_id!=null? pay_scheme_id.hashCode(): 0) 
        + (amount!=null? amount.hashCode(): 0) 
        + (order_date_time!=null? order_date_time.hashCode(): 0) 
        + (delivery_scheduled_date!=null? delivery_scheduled_date.hashCode(): 0) 
        + (base_date!=null? base_date.hashCode(): 0) 
        + (input_div!=null? input_div.hashCode(): 0) 
        + (comondebi_capture_date!=null? comondebi_capture_date.hashCode(): 0) 
        + (center_pay_id!=null? center_pay_id.hashCode(): 0) 
        + (order_status_flag!=null? order_status_flag.hashCode(): 0) 
        + (order_request_time!=null? order_request_time.hashCode(): 0) 
        + (order_response_time!=null? order_response_time.hashCode(): 0) 
        + (start_status_flag!=null? start_status_flag.hashCode(): 0) 
        + (start_request_time!=null? start_request_time.hashCode(): 0) 
        + (start_response_time!=null? start_response_time.hashCode(): 0) 
        + (result_status_flag!=null? result_status_flag.hashCode(): 0) 
        + (result_request_time!=null? result_request_time.hashCode(): 0) 
        + (result_response_time!=null? result_response_time.hashCode(): 0) 
        + (transaction_status!=null? transaction_status.hashCode(): 0) 
        + (transaction_time!=null? transaction_time.hashCode(): 0) 
        + (batch_flag!=null? batch_flag.hashCode(): 0) 
        + (last_update_timestamp!=null? last_update_timestamp.hashCode(): 0) 
        + (last_update_user!=null? last_update_user.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !pay_scheme_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'pay_scheme_id' must be set before inserting.");
		if ( !order_status_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_status_flag' must be set before inserting.");
		if ( !start_status_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'start_status_flag' must be set before inserting.");
		if ( !result_status_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'result_status_flag' must be set before inserting.");
		if ( !transaction_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'transaction_status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		map.put("order_request_number",order_request_number);
		map.put("pay_scheme_id",pay_scheme_id);
		if ( amount != null )
			map.put("amount",amount);
		if ( order_date_time != null )
			map.put("order_date_time",order_date_time);
		if ( delivery_scheduled_date != null )
			map.put("delivery_scheduled_date",delivery_scheduled_date);
		if ( base_date != null )
			map.put("base_date",base_date);
		if ( input_div != null )
			map.put("input_div",input_div);
		if ( comondebi_capture_date != null )
			map.put("comondebi_capture_date",comondebi_capture_date);
		if ( center_pay_id != null )
			map.put("center_pay_id",center_pay_id);
		map.put("order_status_flag",order_status_flag);
		if ( order_request_time != null )
			map.put("order_request_time",order_request_time);
		if ( order_response_time != null )
			map.put("order_response_time",order_response_time);
		map.put("start_status_flag",start_status_flag);
		if ( start_request_time != null )
			map.put("start_request_time",start_request_time);
		if ( start_response_time != null )
			map.put("start_response_time",start_response_time);
		map.put("result_status_flag",result_status_flag);
		if ( result_request_time != null )
			map.put("result_request_time",result_request_time);
		if ( result_response_time != null )
			map.put("result_response_time",result_response_time);
		map.put("transaction_status",transaction_status);
		if ( transaction_time != null )
			map.put("transaction_time",transaction_time);
		if ( batch_flag != null )
			map.put("batch_flag",batch_flag);
		if ( last_update_timestamp != null )
			map.put("last_update_timestamp",last_update_timestamp);
		if ( last_update_user != null )
			map.put("last_update_user",last_update_user);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( pay_scheme_id_is_modified )
			map.put("pay_scheme_id",pay_scheme_id);
		if ( amount_is_modified )
			map.put("amount",amount);
		if ( order_date_time_is_modified )
			map.put("order_date_time",order_date_time);
		if ( delivery_scheduled_date_is_modified )
			map.put("delivery_scheduled_date",delivery_scheduled_date);
		if ( base_date_is_modified )
			map.put("base_date",base_date);
		if ( input_div_is_modified )
			map.put("input_div",input_div);
		if ( comondebi_capture_date_is_modified )
			map.put("comondebi_capture_date",comondebi_capture_date);
		if ( center_pay_id_is_modified )
			map.put("center_pay_id",center_pay_id);
		if ( order_status_flag_is_modified )
			map.put("order_status_flag",order_status_flag);
		if ( order_request_time_is_modified )
			map.put("order_request_time",order_request_time);
		if ( order_response_time_is_modified )
			map.put("order_response_time",order_response_time);
		if ( start_status_flag_is_modified )
			map.put("start_status_flag",start_status_flag);
		if ( start_request_time_is_modified )
			map.put("start_request_time",start_request_time);
		if ( start_response_time_is_modified )
			map.put("start_response_time",start_response_time);
		if ( result_status_flag_is_modified )
			map.put("result_status_flag",result_status_flag);
		if ( result_request_time_is_modified )
			map.put("result_request_time",result_request_time);
		if ( result_response_time_is_modified )
			map.put("result_response_time",result_response_time);
		if ( transaction_status_is_modified )
			map.put("transaction_status",transaction_status);
		if ( transaction_time_is_modified )
			map.put("transaction_time",transaction_time);
		if ( batch_flag_is_modified )
			map.put("batch_flag",batch_flag);
		if ( last_update_timestamp_is_modified )
			map.put("last_update_timestamp",last_update_timestamp);
		if ( last_update_user_is_modified )
			map.put("last_update_user",last_update_user);
		if (map.size() == 0) {
			map.put("account_code",account_code);
			if ( pay_scheme_id_is_set )
				map.put("pay_scheme_id",pay_scheme_id);
			map.put("amount",amount);
			map.put("order_date_time",order_date_time);
			map.put("delivery_scheduled_date",delivery_scheduled_date);
			map.put("base_date",base_date);
			map.put("input_div",input_div);
			map.put("comondebi_capture_date",comondebi_capture_date);
			map.put("center_pay_id",center_pay_id);
			if ( order_status_flag_is_set )
				map.put("order_status_flag",order_status_flag);
			map.put("order_request_time",order_request_time);
			map.put("order_response_time",order_response_time);
			if ( start_status_flag_is_set )
				map.put("start_status_flag",start_status_flag);
			map.put("start_request_time",start_request_time);
			map.put("start_response_time",start_response_time);
			if ( result_status_flag_is_set )
				map.put("result_status_flag",result_status_flag);
			map.put("result_request_time",result_request_time);
			map.put("result_response_time",result_response_time);
			if ( transaction_status_is_set )
				map.put("transaction_status",transaction_status);
			map.put("transaction_time",transaction_time);
			map.put("batch_flag",batch_flag);
			map.put("last_update_timestamp",last_update_timestamp);
			map.put("last_update_user",last_update_user);
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
   * <em>pay_scheme_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaySchemeId()
  {
    return pay_scheme_id;
  }


  /** 
   * <em>pay_scheme_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaySchemeIdIsSet() {
    return pay_scheme_id_is_set;
  }


  /** 
   * <em>pay_scheme_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaySchemeIdIsModified() {
    return pay_scheme_id_is_modified;
  }


  /** 
   * <em>amount</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmount()
  {
    return ( amount==null? ((long)0): amount.longValue() );
  }


  /** 
   * <em>amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountIsNull()
  {
    return amount == null;
  }


  /** 
   * <em>amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountIsSet() {
    return amount_is_set;
  }


  /** 
   * <em>amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountIsModified() {
    return amount_is_modified;
  }


  /** 
   * <em>order_date_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrderDateTime()
  {
    return order_date_time;
  }


  /** 
   * <em>order_date_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDateTimeIsSet() {
    return order_date_time_is_set;
  }


  /** 
   * <em>order_date_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDateTimeIsModified() {
    return order_date_time_is_modified;
  }


  /** 
   * <em>delivery_scheduled_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDeliveryScheduledDate()
  {
    return delivery_scheduled_date;
  }


  /** 
   * <em>delivery_scheduled_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryScheduledDateIsSet() {
    return delivery_scheduled_date_is_set;
  }


  /** 
   * <em>delivery_scheduled_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryScheduledDateIsModified() {
    return delivery_scheduled_date_is_modified;
  }


  /** 
   * <em>base_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getBaseDate()
  {
    return base_date;
  }


  /** 
   * <em>base_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBaseDateIsSet() {
    return base_date_is_set;
  }


  /** 
   * <em>base_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBaseDateIsModified() {
    return base_date_is_modified;
  }


  /** 
   * <em>input_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInputDiv()
  {
    return input_div;
  }


  /** 
   * <em>input_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInputDivIsSet() {
    return input_div_is_set;
  }


  /** 
   * <em>input_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInputDivIsModified() {
    return input_div_is_modified;
  }


  /** 
   * <em>comondebi_capture_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getComondebiCaptureDate()
  {
    return comondebi_capture_date;
  }


  /** 
   * <em>comondebi_capture_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComondebiCaptureDateIsSet() {
    return comondebi_capture_date_is_set;
  }


  /** 
   * <em>comondebi_capture_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComondebiCaptureDateIsModified() {
    return comondebi_capture_date_is_modified;
  }


  /** 
   * <em>center_pay_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCenterPayId()
  {
    return center_pay_id;
  }


  /** 
   * <em>center_pay_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCenterPayIdIsSet() {
    return center_pay_id_is_set;
  }


  /** 
   * <em>center_pay_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCenterPayIdIsModified() {
    return center_pay_id_is_modified;
  }


  /** 
   * <em>order_status_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderStatusFlag()
  {
    return order_status_flag;
  }


  /** 
   * <em>order_status_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderStatusFlagIsSet() {
    return order_status_flag_is_set;
  }


  /** 
   * <em>order_status_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderStatusFlagIsModified() {
    return order_status_flag_is_modified;
  }


  /** 
   * <em>order_request_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrderRequestTime()
  {
    return order_request_time;
  }


  /** 
   * <em>order_request_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestTimeIsSet() {
    return order_request_time_is_set;
  }


  /** 
   * <em>order_request_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestTimeIsModified() {
    return order_request_time_is_modified;
  }


  /** 
   * <em>order_response_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrderResponseTime()
  {
    return order_response_time;
  }


  /** 
   * <em>order_response_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderResponseTimeIsSet() {
    return order_response_time_is_set;
  }


  /** 
   * <em>order_response_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderResponseTimeIsModified() {
    return order_response_time_is_modified;
  }


  /** 
   * <em>start_status_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStartStatusFlag()
  {
    return start_status_flag;
  }


  /** 
   * <em>start_status_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStartStatusFlagIsSet() {
    return start_status_flag_is_set;
  }


  /** 
   * <em>start_status_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStartStatusFlagIsModified() {
    return start_status_flag_is_modified;
  }


  /** 
   * <em>start_request_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getStartRequestTime()
  {
    return start_request_time;
  }


  /** 
   * <em>start_request_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStartRequestTimeIsSet() {
    return start_request_time_is_set;
  }


  /** 
   * <em>start_request_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStartRequestTimeIsModified() {
    return start_request_time_is_modified;
  }


  /** 
   * <em>start_response_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getStartResponseTime()
  {
    return start_response_time;
  }


  /** 
   * <em>start_response_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStartResponseTimeIsSet() {
    return start_response_time_is_set;
  }


  /** 
   * <em>start_response_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStartResponseTimeIsModified() {
    return start_response_time_is_modified;
  }


  /** 
   * <em>result_status_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getResultStatusFlag()
  {
    return result_status_flag;
  }


  /** 
   * <em>result_status_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResultStatusFlagIsSet() {
    return result_status_flag_is_set;
  }


  /** 
   * <em>result_status_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResultStatusFlagIsModified() {
    return result_status_flag_is_modified;
  }


  /** 
   * <em>result_request_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getResultRequestTime()
  {
    return result_request_time;
  }


  /** 
   * <em>result_request_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResultRequestTimeIsSet() {
    return result_request_time_is_set;
  }


  /** 
   * <em>result_request_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResultRequestTimeIsModified() {
    return result_request_time_is_modified;
  }


  /** 
   * <em>result_response_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getResultResponseTime()
  {
    return result_response_time;
  }


  /** 
   * <em>result_response_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResultResponseTimeIsSet() {
    return result_response_time_is_set;
  }


  /** 
   * <em>result_response_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResultResponseTimeIsModified() {
    return result_response_time_is_modified;
  }


  /** 
   * <em>transaction_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransactionStatus()
  {
    return transaction_status;
  }


  /** 
   * <em>transaction_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactionStatusIsSet() {
    return transaction_status_is_set;
  }


  /** 
   * <em>transaction_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactionStatusIsModified() {
    return transaction_status_is_modified;
  }


  /** 
   * <em>transaction_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTransactionTime()
  {
    return transaction_time;
  }


  /** 
   * <em>transaction_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactionTimeIsSet() {
    return transaction_time_is_set;
  }


  /** 
   * <em>transaction_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactionTimeIsModified() {
    return transaction_time_is_modified;
  }


  /** 
   * <em>batch_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBatchFlag()
  {
    return batch_flag;
  }


  /** 
   * <em>batch_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBatchFlagIsSet() {
    return batch_flag_is_set;
  }


  /** 
   * <em>batch_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBatchFlagIsModified() {
    return batch_flag_is_modified;
  }


  /** 
   * <em>last_update_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdateTimestamp()
  {
    return last_update_timestamp;
  }


  /** 
   * <em>last_update_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateTimestampIsSet() {
    return last_update_timestamp_is_set;
  }


  /** 
   * <em>last_update_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateTimestampIsModified() {
    return last_update_timestamp_is_modified;
  }


  /** 
   * <em>last_update_user</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdateUser()
  {
    return last_update_user;
  }


  /** 
   * <em>last_update_user</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateUserIsSet() {
    return last_update_user_is_set;
  }


  /** 
   * <em>last_update_user</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateUserIsModified() {
    return last_update_user_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new BankCashTransferStatusPK(institution_code, branch_code, order_request_number);
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
   * <em>pay_scheme_id</em>カラムの値を設定します。 
   *
   * @@param p_paySchemeId <em>pay_scheme_id</em>カラムの値をあらわす11桁以下のString値 
   */
  public final void setPaySchemeId( String p_paySchemeId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_scheme_id = p_paySchemeId;
    pay_scheme_id_is_set = true;
    pay_scheme_id_is_modified = true;
  }


  /** 
   * <em>amount</em>カラムの値を設定します。 
   *
   * @@param p_amount <em>amount</em>カラムの値をあらわす12桁以下のlong値 
   */
  public final void setAmount( long p_amount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount = new Long(p_amount);
    amount_is_set = true;
    amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount</em>カラムに値を設定します。 
   */
  public final void setAmount( Long p_amount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount = p_amount;
    amount_is_set = true;
    amount_is_modified = true;
  }


  /** 
   * <em>order_date_time</em>カラムの値を設定します。 
   *
   * @@param p_orderDateTime <em>order_date_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrderDateTime( java.sql.Timestamp p_orderDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_date_time = p_orderDateTime;
    order_date_time_is_set = true;
    order_date_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>order_date_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrderDateTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_date_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_date_time_is_set = true;
    order_date_time_is_modified = true;
  }


  /** 
   * <em>delivery_scheduled_date</em>カラムの値を設定します。 
   *
   * @@param p_deliveryScheduledDate <em>delivery_scheduled_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDeliveryScheduledDate( java.sql.Timestamp p_deliveryScheduledDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_scheduled_date = p_deliveryScheduledDate;
    delivery_scheduled_date_is_set = true;
    delivery_scheduled_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>delivery_scheduled_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDeliveryScheduledDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_scheduled_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    delivery_scheduled_date_is_set = true;
    delivery_scheduled_date_is_modified = true;
  }


  /** 
   * <em>base_date</em>カラムの値を設定します。 
   *
   * @@param p_baseDate <em>base_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setBaseDate( java.sql.Timestamp p_baseDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_date = p_baseDate;
    base_date_is_set = true;
    base_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>base_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setBaseDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    base_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    base_date_is_set = true;
    base_date_is_modified = true;
  }


  /** 
   * <em>input_div</em>カラムの値を設定します。 
   *
   * @@param p_inputDiv <em>input_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInputDiv( String p_inputDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    input_div = p_inputDiv;
    input_div_is_set = true;
    input_div_is_modified = true;
  }


  /** 
   * <em>comondebi_capture_date</em>カラムの値を設定します。 
   *
   * @@param p_comondebiCaptureDate <em>comondebi_capture_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setComondebiCaptureDate( java.sql.Timestamp p_comondebiCaptureDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comondebi_capture_date = p_comondebiCaptureDate;
    comondebi_capture_date_is_set = true;
    comondebi_capture_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>comondebi_capture_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setComondebiCaptureDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    comondebi_capture_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    comondebi_capture_date_is_set = true;
    comondebi_capture_date_is_modified = true;
  }


  /** 
   * <em>center_pay_id</em>カラムの値を設定します。 
   *
   * @@param p_centerPayId <em>center_pay_id</em>カラムの値をあらわす15桁以下のString値 
   */
  public final void setCenterPayId( String p_centerPayId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    center_pay_id = p_centerPayId;
    center_pay_id_is_set = true;
    center_pay_id_is_modified = true;
  }


  /** 
   * <em>order_status_flag</em>カラムの値を設定します。 
   *
   * @@param p_orderStatusFlag <em>order_status_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderStatusFlag( String p_orderStatusFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_status_flag = p_orderStatusFlag;
    order_status_flag_is_set = true;
    order_status_flag_is_modified = true;
  }


  /** 
   * <em>order_request_time</em>カラムの値を設定します。 
   *
   * @@param p_orderRequestTime <em>order_request_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrderRequestTime( java.sql.Timestamp p_orderRequestTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_time = p_orderRequestTime;
    order_request_time_is_set = true;
    order_request_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>order_request_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrderRequestTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_request_time_is_set = true;
    order_request_time_is_modified = true;
  }


  /** 
   * <em>order_response_time</em>カラムの値を設定します。 
   *
   * @@param p_orderResponseTime <em>order_response_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrderResponseTime( java.sql.Timestamp p_orderResponseTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_response_time = p_orderResponseTime;
    order_response_time_is_set = true;
    order_response_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>order_response_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrderResponseTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_response_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_response_time_is_set = true;
    order_response_time_is_modified = true;
  }


  /** 
   * <em>start_status_flag</em>カラムの値を設定します。 
   *
   * @@param p_startStatusFlag <em>start_status_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStartStatusFlag( String p_startStatusFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    start_status_flag = p_startStatusFlag;
    start_status_flag_is_set = true;
    start_status_flag_is_modified = true;
  }


  /** 
   * <em>start_request_time</em>カラムの値を設定します。 
   *
   * @@param p_startRequestTime <em>start_request_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setStartRequestTime( java.sql.Timestamp p_startRequestTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    start_request_time = p_startRequestTime;
    start_request_time_is_set = true;
    start_request_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>start_request_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setStartRequestTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    start_request_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    start_request_time_is_set = true;
    start_request_time_is_modified = true;
  }


  /** 
   * <em>start_response_time</em>カラムの値を設定します。 
   *
   * @@param p_startResponseTime <em>start_response_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setStartResponseTime( java.sql.Timestamp p_startResponseTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    start_response_time = p_startResponseTime;
    start_response_time_is_set = true;
    start_response_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>start_response_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setStartResponseTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    start_response_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    start_response_time_is_set = true;
    start_response_time_is_modified = true;
  }


  /** 
   * <em>result_status_flag</em>カラムの値を設定します。 
   *
   * @@param p_resultStatusFlag <em>result_status_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setResultStatusFlag( String p_resultStatusFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    result_status_flag = p_resultStatusFlag;
    result_status_flag_is_set = true;
    result_status_flag_is_modified = true;
  }


  /** 
   * <em>result_request_time</em>カラムの値を設定します。 
   *
   * @@param p_resultRequestTime <em>result_request_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setResultRequestTime( java.sql.Timestamp p_resultRequestTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    result_request_time = p_resultRequestTime;
    result_request_time_is_set = true;
    result_request_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>result_request_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setResultRequestTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    result_request_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    result_request_time_is_set = true;
    result_request_time_is_modified = true;
  }


  /** 
   * <em>result_response_time</em>カラムの値を設定します。 
   *
   * @@param p_resultResponseTime <em>result_response_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setResultResponseTime( java.sql.Timestamp p_resultResponseTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    result_response_time = p_resultResponseTime;
    result_response_time_is_set = true;
    result_response_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>result_response_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setResultResponseTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    result_response_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    result_response_time_is_set = true;
    result_response_time_is_modified = true;
  }


  /** 
   * <em>transaction_status</em>カラムの値を設定します。 
   *
   * @@param p_transactionStatus <em>transaction_status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTransactionStatus( String p_transactionStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_status = p_transactionStatus;
    transaction_status_is_set = true;
    transaction_status_is_modified = true;
  }


  /** 
   * <em>transaction_time</em>カラムの値を設定します。 
   *
   * @@param p_transactionTime <em>transaction_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTransactionTime( java.sql.Timestamp p_transactionTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_time = p_transactionTime;
    transaction_time_is_set = true;
    transaction_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>transaction_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTransactionTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    transaction_time_is_set = true;
    transaction_time_is_modified = true;
  }


  /** 
   * <em>batch_flag</em>カラムの値を設定します。 
   *
   * @@param p_batchFlag <em>batch_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBatchFlag( String p_batchFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    batch_flag = p_batchFlag;
    batch_flag_is_set = true;
    batch_flag_is_modified = true;
  }


  /** 
   * <em>last_update_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdateTimestamp <em>last_update_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdateTimestamp( java.sql.Timestamp p_lastUpdateTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_timestamp = p_lastUpdateTimestamp;
    last_update_timestamp_is_set = true;
    last_update_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_update_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdateTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_update_timestamp_is_set = true;
    last_update_timestamp_is_modified = true;
  }


  /** 
   * <em>last_update_user</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdateUser <em>last_update_user</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdateUser( String p_lastUpdateUser )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_user = p_lastUpdateUser;
    last_update_user_is_set = true;
    last_update_user_is_modified = true;
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
                else if ( name.equals("amount") ) {
                    return this.amount;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("base_date") ) {
                    return this.base_date;
                }
                else if ( name.equals("batch_flag") ) {
                    return this.batch_flag;
                }
                break;
            case 'c':
                if ( name.equals("comondebi_capture_date") ) {
                    return this.comondebi_capture_date;
                }
                else if ( name.equals("center_pay_id") ) {
                    return this.center_pay_id;
                }
                break;
            case 'd':
                if ( name.equals("delivery_scheduled_date") ) {
                    return this.delivery_scheduled_date;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("input_div") ) {
                    return this.input_div;
                }
                break;
            case 'l':
                if ( name.equals("last_update_timestamp") ) {
                    return this.last_update_timestamp;
                }
                else if ( name.equals("last_update_user") ) {
                    return this.last_update_user;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_date_time") ) {
                    return this.order_date_time;
                }
                else if ( name.equals("order_status_flag") ) {
                    return this.order_status_flag;
                }
                else if ( name.equals("order_request_time") ) {
                    return this.order_request_time;
                }
                else if ( name.equals("order_response_time") ) {
                    return this.order_response_time;
                }
                break;
            case 'p':
                if ( name.equals("pay_scheme_id") ) {
                    return this.pay_scheme_id;
                }
                break;
            case 'r':
                if ( name.equals("result_status_flag") ) {
                    return this.result_status_flag;
                }
                else if ( name.equals("result_request_time") ) {
                    return this.result_request_time;
                }
                else if ( name.equals("result_response_time") ) {
                    return this.result_response_time;
                }
                break;
            case 's':
                if ( name.equals("start_status_flag") ) {
                    return this.start_status_flag;
                }
                else if ( name.equals("start_request_time") ) {
                    return this.start_request_time;
                }
                else if ( name.equals("start_response_time") ) {
                    return this.start_response_time;
                }
                break;
            case 't':
                if ( name.equals("transaction_status") ) {
                    return this.transaction_status;
                }
                else if ( name.equals("transaction_time") ) {
                    return this.transaction_time;
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
                else if ( name.equals("amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount' must be Long: '"+value+"' is not." );
                    this.amount = (Long) value;
                    if (this.amount_is_set)
                        this.amount_is_modified = true;
                    this.amount_is_set = true;
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
                else if ( name.equals("base_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'base_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.base_date = (java.sql.Timestamp) value;
                    if (this.base_date_is_set)
                        this.base_date_is_modified = true;
                    this.base_date_is_set = true;
                    return;
                }
                else if ( name.equals("batch_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'batch_flag' must be String: '"+value+"' is not." );
                    this.batch_flag = (String) value;
                    if (this.batch_flag_is_set)
                        this.batch_flag_is_modified = true;
                    this.batch_flag_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("comondebi_capture_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'comondebi_capture_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.comondebi_capture_date = (java.sql.Timestamp) value;
                    if (this.comondebi_capture_date_is_set)
                        this.comondebi_capture_date_is_modified = true;
                    this.comondebi_capture_date_is_set = true;
                    return;
                }
                else if ( name.equals("center_pay_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'center_pay_id' must be String: '"+value+"' is not." );
                    this.center_pay_id = (String) value;
                    if (this.center_pay_id_is_set)
                        this.center_pay_id_is_modified = true;
                    this.center_pay_id_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("delivery_scheduled_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_scheduled_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_scheduled_date = (java.sql.Timestamp) value;
                    if (this.delivery_scheduled_date_is_set)
                        this.delivery_scheduled_date_is_modified = true;
                    this.delivery_scheduled_date_is_set = true;
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
                else if ( name.equals("input_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'input_div' must be String: '"+value+"' is not." );
                    this.input_div = (String) value;
                    if (this.input_div_is_set)
                        this.input_div_is_modified = true;
                    this.input_div_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_update_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_update_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_update_timestamp = (java.sql.Timestamp) value;
                    if (this.last_update_timestamp_is_set)
                        this.last_update_timestamp_is_modified = true;
                    this.last_update_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("last_update_user") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_update_user' must be String: '"+value+"' is not." );
                    this.last_update_user = (String) value;
                    if (this.last_update_user_is_set)
                        this.last_update_user_is_modified = true;
                    this.last_update_user_is_set = true;
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
                else if ( name.equals("order_date_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_date_time = (java.sql.Timestamp) value;
                    if (this.order_date_time_is_set)
                        this.order_date_time_is_modified = true;
                    this.order_date_time_is_set = true;
                    return;
                }
                else if ( name.equals("order_status_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_status_flag' must be String: '"+value+"' is not." );
                    this.order_status_flag = (String) value;
                    if (this.order_status_flag_is_set)
                        this.order_status_flag_is_modified = true;
                    this.order_status_flag_is_set = true;
                    return;
                }
                else if ( name.equals("order_request_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_request_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_request_time = (java.sql.Timestamp) value;
                    if (this.order_request_time_is_set)
                        this.order_request_time_is_modified = true;
                    this.order_request_time_is_set = true;
                    return;
                }
                else if ( name.equals("order_response_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_response_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_response_time = (java.sql.Timestamp) value;
                    if (this.order_response_time_is_set)
                        this.order_response_time_is_modified = true;
                    this.order_response_time_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("pay_scheme_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pay_scheme_id' must be String: '"+value+"' is not." );
                    this.pay_scheme_id = (String) value;
                    if (this.pay_scheme_id_is_set)
                        this.pay_scheme_id_is_modified = true;
                    this.pay_scheme_id_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("result_status_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'result_status_flag' must be String: '"+value+"' is not." );
                    this.result_status_flag = (String) value;
                    if (this.result_status_flag_is_set)
                        this.result_status_flag_is_modified = true;
                    this.result_status_flag_is_set = true;
                    return;
                }
                else if ( name.equals("result_request_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'result_request_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.result_request_time = (java.sql.Timestamp) value;
                    if (this.result_request_time_is_set)
                        this.result_request_time_is_modified = true;
                    this.result_request_time_is_set = true;
                    return;
                }
                else if ( name.equals("result_response_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'result_response_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.result_response_time = (java.sql.Timestamp) value;
                    if (this.result_response_time_is_set)
                        this.result_response_time_is_modified = true;
                    this.result_response_time_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("start_status_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'start_status_flag' must be String: '"+value+"' is not." );
                    this.start_status_flag = (String) value;
                    if (this.start_status_flag_is_set)
                        this.start_status_flag_is_modified = true;
                    this.start_status_flag_is_set = true;
                    return;
                }
                else if ( name.equals("start_request_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'start_request_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.start_request_time = (java.sql.Timestamp) value;
                    if (this.start_request_time_is_set)
                        this.start_request_time_is_modified = true;
                    this.start_request_time_is_set = true;
                    return;
                }
                else if ( name.equals("start_response_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'start_response_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.start_response_time = (java.sql.Timestamp) value;
                    if (this.start_response_time_is_set)
                        this.start_response_time_is_modified = true;
                    this.start_response_time_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("transaction_status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transaction_status' must be String: '"+value+"' is not." );
                    this.transaction_status = (String) value;
                    if (this.transaction_status_is_set)
                        this.transaction_status_is_modified = true;
                    this.transaction_status_is_set = true;
                    return;
                }
                else if ( name.equals("transaction_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'transaction_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.transaction_time = (java.sql.Timestamp) value;
                    if (this.transaction_time_is_set)
                        this.transaction_time_is_modified = true;
                    this.transaction_time_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
