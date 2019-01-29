head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.42.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	GftTransferStatusParams.java;


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
 * gft_transfer_statusテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link GftTransferStatusRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link GftTransferStatusParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link GftTransferStatusParams}が{@@link GftTransferStatusRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see GftTransferStatusPK 
 * @@see GftTransferStatusRow 
 */
public class GftTransferStatusParams extends Params implements GftTransferStatusRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "gft_transfer_status";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = GftTransferStatusRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return GftTransferStatusRow.TYPE;
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
   * <em>operation_div</em>カラムの値 
   */
  public  String  operation_div;

  /** 
   * <em>course_div</em>カラムの値 
   */
  public  String  course_div;

  /** 
   * <em>fx_account_code</em>カラムの値 
   */
  public  String  fx_account_code;

  /** 
   * <em>amount</em>カラムの値 
   */
  public  Long  amount;

  /** 
   * <em>transfer_date</em>カラムの値 
   */
  public  String  transfer_date;

  /** 
   * <em>transfer_status_div</em>カラムの値 
   */
  public  String  transfer_status_div;

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
   * <em>send_time</em>カラムの値 
   */
  public  String  send_time;

  /** 
   * <em>receive_time</em>カラムの値 
   */
  public  String  receive_time;

  /** 
   * <em>mrg_trn_order_request_number</em>カラムの値 
   */
  public  String  mrg_trn_order_request_number;

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
   * <em>fx_upload_number</em>カラムの値 
   */
  public  String  fx_upload_number;

  /** 
   * <em>result_code_soap</em>カラムの値 
   */
  public  String  result_code_soap;

  /** 
   * <em>fx_system_code</em>カラムの値 
   */
  public  String  fx_system_code;

  /** 
   * <em>io_list_trade_div</em>カラムの値 
   */
  public  String  io_list_trade_div;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean operation_div_is_set = false;
  private boolean operation_div_is_modified = false;
  private boolean course_div_is_set = false;
  private boolean course_div_is_modified = false;
  private boolean fx_account_code_is_set = false;
  private boolean fx_account_code_is_modified = false;
  private boolean amount_is_set = false;
  private boolean amount_is_modified = false;
  private boolean transfer_date_is_set = false;
  private boolean transfer_date_is_modified = false;
  private boolean transfer_status_div_is_set = false;
  private boolean transfer_status_div_is_modified = false;
  private boolean send_rcv_div_is_set = false;
  private boolean send_rcv_div_is_modified = false;
  private boolean result_code_is_set = false;
  private boolean result_code_is_modified = false;
  private boolean error_reason_code_is_set = false;
  private boolean error_reason_code_is_modified = false;
  private boolean send_time_is_set = false;
  private boolean send_time_is_modified = false;
  private boolean receive_time_is_set = false;
  private boolean receive_time_is_modified = false;
  private boolean mrg_trn_order_request_number_is_set = false;
  private boolean mrg_trn_order_request_number_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean fx_upload_number_is_set = false;
  private boolean fx_upload_number_is_modified = false;
  private boolean result_code_soap_is_set = false;
  private boolean result_code_soap_is_modified = false;
  private boolean fx_system_code_is_set = false;
  private boolean fx_system_code_is_modified = false;
  private boolean io_list_trade_div_is_set = false;
  private boolean io_list_trade_div_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "order_request_number=" + order_request_number
      + "," + "account_code=" +account_code
      + "," + "operation_div=" +operation_div
      + "," + "course_div=" +course_div
      + "," + "fx_account_code=" +fx_account_code
      + "," + "amount=" +amount
      + "," + "transfer_date=" +transfer_date
      + "," + "transfer_status_div=" +transfer_status_div
      + "," + "send_rcv_div=" +send_rcv_div
      + "," + "result_code=" +result_code
      + "," + "error_reason_code=" +error_reason_code
      + "," + "send_time=" +send_time
      + "," + "receive_time=" +receive_time
      + "," + "mrg_trn_order_request_number=" +mrg_trn_order_request_number
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "fx_upload_number=" +fx_upload_number
      + "," + "result_code_soap=" +result_code_soap
      + "," + "fx_system_code=" +fx_system_code
      + "," + "io_list_trade_div=" +io_list_trade_div
      + "}";
  }


  /** 
   * 値が未設定のGftTransferStatusParamsオブジェクトを作成します。 
   */
  public GftTransferStatusParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    order_request_number_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のGftTransferStatusRowオブジェクトの値を利用してGftTransferStatusParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するGftTransferStatusRowオブジェクト 
   */
  public GftTransferStatusParams( GftTransferStatusRow p_row )
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
    operation_div = p_row.getOperationDiv();
    operation_div_is_set = p_row.getOperationDivIsSet();
    operation_div_is_modified = p_row.getOperationDivIsModified();
    course_div = p_row.getCourseDiv();
    course_div_is_set = p_row.getCourseDivIsSet();
    course_div_is_modified = p_row.getCourseDivIsModified();
    fx_account_code = p_row.getFxAccountCode();
    fx_account_code_is_set = p_row.getFxAccountCodeIsSet();
    fx_account_code_is_modified = p_row.getFxAccountCodeIsModified();
    if ( !p_row.getAmountIsNull() )
      amount = new Long( p_row.getAmount() );
    amount_is_set = p_row.getAmountIsSet();
    amount_is_modified = p_row.getAmountIsModified();
    transfer_date = p_row.getTransferDate();
    transfer_date_is_set = p_row.getTransferDateIsSet();
    transfer_date_is_modified = p_row.getTransferDateIsModified();
    transfer_status_div = p_row.getTransferStatusDiv();
    transfer_status_div_is_set = p_row.getTransferStatusDivIsSet();
    transfer_status_div_is_modified = p_row.getTransferStatusDivIsModified();
    send_rcv_div = p_row.getSendRcvDiv();
    send_rcv_div_is_set = p_row.getSendRcvDivIsSet();
    send_rcv_div_is_modified = p_row.getSendRcvDivIsModified();
    result_code = p_row.getResultCode();
    result_code_is_set = p_row.getResultCodeIsSet();
    result_code_is_modified = p_row.getResultCodeIsModified();
    error_reason_code = p_row.getErrorReasonCode();
    error_reason_code_is_set = p_row.getErrorReasonCodeIsSet();
    error_reason_code_is_modified = p_row.getErrorReasonCodeIsModified();
    send_time = p_row.getSendTime();
    send_time_is_set = p_row.getSendTimeIsSet();
    send_time_is_modified = p_row.getSendTimeIsModified();
    receive_time = p_row.getReceiveTime();
    receive_time_is_set = p_row.getReceiveTimeIsSet();
    receive_time_is_modified = p_row.getReceiveTimeIsModified();
    mrg_trn_order_request_number = p_row.getMrgTrnOrderRequestNumber();
    mrg_trn_order_request_number_is_set = p_row.getMrgTrnOrderRequestNumberIsSet();
    mrg_trn_order_request_number_is_modified = p_row.getMrgTrnOrderRequestNumberIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    fx_upload_number = p_row.getFxUploadNumber();
    fx_upload_number_is_set = p_row.getFxUploadNumberIsSet();
    fx_upload_number_is_modified = p_row.getFxUploadNumberIsModified();
    result_code_soap = p_row.getResultCodeSoap();
    result_code_soap_is_set = p_row.getResultCodeSoapIsSet();
    result_code_soap_is_modified = p_row.getResultCodeSoapIsModified();
    fx_system_code = p_row.getFxSystemCode();
    fx_system_code_is_set = p_row.getFxSystemCodeIsSet();
    fx_system_code_is_modified = p_row.getFxSystemCodeIsModified();
    io_list_trade_div = p_row.getIoListTradeDiv();
    io_list_trade_div_is_set = p_row.getIoListTradeDivIsSet();
    io_list_trade_div_is_modified = p_row.getIoListTradeDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      account_code_is_set = true;
      account_code_is_modified = true;
      operation_div_is_set = true;
      operation_div_is_modified = true;
      course_div_is_set = true;
      course_div_is_modified = true;
      fx_account_code_is_set = true;
      fx_account_code_is_modified = true;
      amount_is_set = true;
      amount_is_modified = true;
      transfer_date_is_set = true;
      transfer_date_is_modified = true;
      transfer_status_div_is_set = true;
      transfer_status_div_is_modified = true;
      send_rcv_div_is_set = true;
      send_rcv_div_is_modified = true;
      result_code_is_set = true;
      result_code_is_modified = true;
      error_reason_code_is_set = true;
      error_reason_code_is_modified = true;
      send_time_is_set = true;
      send_time_is_modified = true;
      receive_time_is_set = true;
      receive_time_is_modified = true;
      mrg_trn_order_request_number_is_set = true;
      mrg_trn_order_request_number_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      fx_upload_number_is_set = true;
      fx_upload_number_is_modified = true;
      result_code_soap_is_set = true;
      result_code_soap_is_modified = true;
      fx_system_code_is_set = true;
      fx_system_code_is_modified = true;
      io_list_trade_div_is_set = true;
      io_list_trade_div_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof GftTransferStatusRow ) )
       return false;
    return fieldsEqual( (GftTransferStatusRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のGftTransferStatusRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( GftTransferStatusRow row )
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
    if ( operation_div == null ) {
      if ( row.getOperationDiv() != null )
        return false;
    } else if ( !operation_div.equals( row.getOperationDiv() ) ) {
        return false;
    }
    if ( course_div == null ) {
      if ( row.getCourseDiv() != null )
        return false;
    } else if ( !course_div.equals( row.getCourseDiv() ) ) {
        return false;
    }
    if ( fx_account_code == null ) {
      if ( row.getFxAccountCode() != null )
        return false;
    } else if ( !fx_account_code.equals( row.getFxAccountCode() ) ) {
        return false;
    }
    if ( amount == null ) {
      if ( !row.getAmountIsNull() )
        return false;
    } else if ( row.getAmountIsNull() || ( amount.longValue() != row.getAmount() ) ) {
        return false;
    }
    if ( transfer_date == null ) {
      if ( row.getTransferDate() != null )
        return false;
    } else if ( !transfer_date.equals( row.getTransferDate() ) ) {
        return false;
    }
    if ( transfer_status_div == null ) {
      if ( row.getTransferStatusDiv() != null )
        return false;
    } else if ( !transfer_status_div.equals( row.getTransferStatusDiv() ) ) {
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
    if ( send_time == null ) {
      if ( row.getSendTime() != null )
        return false;
    } else if ( !send_time.equals( row.getSendTime() ) ) {
        return false;
    }
    if ( receive_time == null ) {
      if ( row.getReceiveTime() != null )
        return false;
    } else if ( !receive_time.equals( row.getReceiveTime() ) ) {
        return false;
    }
    if ( mrg_trn_order_request_number == null ) {
      if ( row.getMrgTrnOrderRequestNumber() != null )
        return false;
    } else if ( !mrg_trn_order_request_number.equals( row.getMrgTrnOrderRequestNumber() ) ) {
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
    if ( fx_upload_number == null ) {
      if ( row.getFxUploadNumber() != null )
        return false;
    } else if ( !fx_upload_number.equals( row.getFxUploadNumber() ) ) {
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
    if ( io_list_trade_div == null ) {
      if ( row.getIoListTradeDiv() != null )
        return false;
    } else if ( !io_list_trade_div.equals( row.getIoListTradeDiv() ) ) {
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
        + (operation_div!=null? operation_div.hashCode(): 0) 
        + (course_div!=null? course_div.hashCode(): 0) 
        + (fx_account_code!=null? fx_account_code.hashCode(): 0) 
        + (amount!=null? amount.hashCode(): 0) 
        + (transfer_date!=null? transfer_date.hashCode(): 0) 
        + (transfer_status_div!=null? transfer_status_div.hashCode(): 0) 
        + (send_rcv_div!=null? send_rcv_div.hashCode(): 0) 
        + (result_code!=null? result_code.hashCode(): 0) 
        + (error_reason_code!=null? error_reason_code.hashCode(): 0) 
        + (send_time!=null? send_time.hashCode(): 0) 
        + (receive_time!=null? receive_time.hashCode(): 0) 
        + (mrg_trn_order_request_number!=null? mrg_trn_order_request_number.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (fx_upload_number!=null? fx_upload_number.hashCode(): 0) 
        + (result_code_soap!=null? result_code_soap.hashCode(): 0) 
        + (fx_system_code!=null? fx_system_code.hashCode(): 0) 
        + (io_list_trade_div!=null? io_list_trade_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !operation_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'operation_div' must be set before inserting.");
		if ( !course_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'course_div' must be set before inserting.");
		if ( !fx_account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'fx_account_code' must be set before inserting.");
		if ( !transfer_status_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'transfer_status_div' must be set before inserting.");
		if ( !send_rcv_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'send_rcv_div' must be set before inserting.");
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
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		map.put("order_request_number",order_request_number);
		map.put("operation_div",operation_div);
		map.put("course_div",course_div);
		map.put("fx_account_code",fx_account_code);
		if ( amount != null )
			map.put("amount",amount);
		if ( transfer_date != null )
			map.put("transfer_date",transfer_date);
		map.put("transfer_status_div",transfer_status_div);
		map.put("send_rcv_div",send_rcv_div);
		if ( result_code != null )
			map.put("result_code",result_code);
		if ( error_reason_code != null )
			map.put("error_reason_code",error_reason_code);
		if ( send_time != null )
			map.put("send_time",send_time);
		if ( receive_time != null )
			map.put("receive_time",receive_time);
		if ( mrg_trn_order_request_number != null )
			map.put("mrg_trn_order_request_number",mrg_trn_order_request_number);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( fx_upload_number != null )
			map.put("fx_upload_number",fx_upload_number);
		if ( result_code_soap != null )
			map.put("result_code_soap",result_code_soap);
		if ( fx_system_code != null )
			map.put("fx_system_code",fx_system_code);
		if ( io_list_trade_div != null )
			map.put("io_list_trade_div",io_list_trade_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( operation_div_is_modified )
			map.put("operation_div",operation_div);
		if ( course_div_is_modified )
			map.put("course_div",course_div);
		if ( fx_account_code_is_modified )
			map.put("fx_account_code",fx_account_code);
		if ( amount_is_modified )
			map.put("amount",amount);
		if ( transfer_date_is_modified )
			map.put("transfer_date",transfer_date);
		if ( transfer_status_div_is_modified )
			map.put("transfer_status_div",transfer_status_div);
		if ( send_rcv_div_is_modified )
			map.put("send_rcv_div",send_rcv_div);
		if ( result_code_is_modified )
			map.put("result_code",result_code);
		if ( error_reason_code_is_modified )
			map.put("error_reason_code",error_reason_code);
		if ( send_time_is_modified )
			map.put("send_time",send_time);
		if ( receive_time_is_modified )
			map.put("receive_time",receive_time);
		if ( mrg_trn_order_request_number_is_modified )
			map.put("mrg_trn_order_request_number",mrg_trn_order_request_number);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( fx_upload_number_is_modified )
			map.put("fx_upload_number",fx_upload_number);
		if ( result_code_soap_is_modified )
			map.put("result_code_soap",result_code_soap);
		if ( fx_system_code_is_modified )
			map.put("fx_system_code",fx_system_code);
		if ( io_list_trade_div_is_modified )
			map.put("io_list_trade_div",io_list_trade_div);
		if (map.size() == 0) {
			if ( account_code_is_set )
				map.put("account_code",account_code);
			if ( operation_div_is_set )
				map.put("operation_div",operation_div);
			if ( course_div_is_set )
				map.put("course_div",course_div);
			if ( fx_account_code_is_set )
				map.put("fx_account_code",fx_account_code);
			map.put("amount",amount);
			map.put("transfer_date",transfer_date);
			if ( transfer_status_div_is_set )
				map.put("transfer_status_div",transfer_status_div);
			if ( send_rcv_div_is_set )
				map.put("send_rcv_div",send_rcv_div);
			map.put("result_code",result_code);
			map.put("error_reason_code",error_reason_code);
			map.put("send_time",send_time);
			map.put("receive_time",receive_time);
			map.put("mrg_trn_order_request_number",mrg_trn_order_request_number);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("fx_upload_number",fx_upload_number);
			map.put("result_code_soap",result_code_soap);
			map.put("fx_system_code",fx_system_code);
			map.put("io_list_trade_div",io_list_trade_div);
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
   * <em>operation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOperationDiv()
  {
    return operation_div;
  }


  /** 
   * <em>operation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperationDivIsSet() {
    return operation_div_is_set;
  }


  /** 
   * <em>operation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperationDivIsModified() {
    return operation_div_is_modified;
  }


  /** 
   * <em>course_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCourseDiv()
  {
    return course_div;
  }


  /** 
   * <em>course_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCourseDivIsSet() {
    return course_div_is_set;
  }


  /** 
   * <em>course_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCourseDivIsModified() {
    return course_div_is_modified;
  }


  /** 
   * <em>fx_account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFxAccountCode()
  {
    return fx_account_code;
  }


  /** 
   * <em>fx_account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxAccountCodeIsSet() {
    return fx_account_code_is_set;
  }


  /** 
   * <em>fx_account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxAccountCodeIsModified() {
    return fx_account_code_is_modified;
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
   * <em>transfer_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransferDate()
  {
    return transfer_date;
  }


  /** 
   * <em>transfer_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDateIsSet() {
    return transfer_date_is_set;
  }


  /** 
   * <em>transfer_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDateIsModified() {
    return transfer_date_is_modified;
  }


  /** 
   * <em>transfer_status_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransferStatusDiv()
  {
    return transfer_status_div;
  }


  /** 
   * <em>transfer_status_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferStatusDivIsSet() {
    return transfer_status_div_is_set;
  }


  /** 
   * <em>transfer_status_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferStatusDivIsModified() {
    return transfer_status_div_is_modified;
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
   * <em>send_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSendTime()
  {
    return send_time;
  }


  /** 
   * <em>send_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendTimeIsSet() {
    return send_time_is_set;
  }


  /** 
   * <em>send_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendTimeIsModified() {
    return send_time_is_modified;
  }


  /** 
   * <em>receive_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReceiveTime()
  {
    return receive_time;
  }


  /** 
   * <em>receive_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiveTimeIsSet() {
    return receive_time_is_set;
  }


  /** 
   * <em>receive_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiveTimeIsModified() {
    return receive_time_is_modified;
  }


  /** 
   * <em>mrg_trn_order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMrgTrnOrderRequestNumber()
  {
    return mrg_trn_order_request_number;
  }


  /** 
   * <em>mrg_trn_order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMrgTrnOrderRequestNumberIsSet() {
    return mrg_trn_order_request_number_is_set;
  }


  /** 
   * <em>mrg_trn_order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMrgTrnOrderRequestNumberIsModified() {
    return mrg_trn_order_request_number_is_modified;
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
   * <em>fx_upload_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFxUploadNumber()
  {
    return fx_upload_number;
  }


  /** 
   * <em>fx_upload_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxUploadNumberIsSet() {
    return fx_upload_number_is_set;
  }


  /** 
   * <em>fx_upload_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxUploadNumberIsModified() {
    return fx_upload_number_is_modified;
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
   * <em>io_list_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIoListTradeDiv()
  {
    return io_list_trade_div;
  }


  /** 
   * <em>io_list_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIoListTradeDivIsSet() {
    return io_list_trade_div_is_set;
  }


  /** 
   * <em>io_list_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIoListTradeDivIsModified() {
    return io_list_trade_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new GftTransferStatusPK(institution_code, branch_code, order_request_number);
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
   * <em>operation_div</em>カラムの値を設定します。 
   *
   * @@param p_operationDiv <em>operation_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setOperationDiv( String p_operationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    operation_div = p_operationDiv;
    operation_div_is_set = true;
    operation_div_is_modified = true;
  }


  /** 
   * <em>course_div</em>カラムの値を設定します。 
   *
   * @@param p_courseDiv <em>course_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCourseDiv( String p_courseDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    course_div = p_courseDiv;
    course_div_is_set = true;
    course_div_is_modified = true;
  }


  /** 
   * <em>fx_account_code</em>カラムの値を設定します。 
   *
   * @@param p_fxAccountCode <em>fx_account_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setFxAccountCode( String p_fxAccountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_account_code = p_fxAccountCode;
    fx_account_code_is_set = true;
    fx_account_code_is_modified = true;
  }


  /** 
   * <em>amount</em>カラムの値を設定します。 
   *
   * @@param p_amount <em>amount</em>カラムの値をあらわす14桁以下のlong値 
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
   * <em>transfer_date</em>カラムの値を設定します。 
   *
   * @@param p_transferDate <em>transfer_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setTransferDate( String p_transferDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_date = p_transferDate;
    transfer_date_is_set = true;
    transfer_date_is_modified = true;
  }


  /** 
   * <em>transfer_status_div</em>カラムの値を設定します。 
   *
   * @@param p_transferStatusDiv <em>transfer_status_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTransferStatusDiv( String p_transferStatusDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_status_div = p_transferStatusDiv;
    transfer_status_div_is_set = true;
    transfer_status_div_is_modified = true;
  }


  /** 
   * <em>send_rcv_div</em>カラムの値を設定します。 
   *
   * @@param p_sendRcvDiv <em>send_rcv_div</em>カラムの値をあらわす1桁以下のString値 
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
   * <em>send_time</em>カラムの値を設定します。 
   *
   * @@param p_sendTime <em>send_time</em>カラムの値をあらわす14桁以下のString値 
   */
  public final void setSendTime( String p_sendTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_time = p_sendTime;
    send_time_is_set = true;
    send_time_is_modified = true;
  }


  /** 
   * <em>receive_time</em>カラムの値を設定します。 
   *
   * @@param p_receiveTime <em>receive_time</em>カラムの値をあらわす14桁以下のString値 
   */
  public final void setReceiveTime( String p_receiveTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receive_time = p_receiveTime;
    receive_time_is_set = true;
    receive_time_is_modified = true;
  }


  /** 
   * <em>mrg_trn_order_request_number</em>カラムの値を設定します。 
   *
   * @@param p_mrgTrnOrderRequestNumber <em>mrg_trn_order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setMrgTrnOrderRequestNumber( String p_mrgTrnOrderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mrg_trn_order_request_number = p_mrgTrnOrderRequestNumber;
    mrg_trn_order_request_number_is_set = true;
    mrg_trn_order_request_number_is_modified = true;
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
   * <em>fx_upload_number</em>カラムの値を設定します。 
   *
   * @@param p_fxUploadNumber <em>fx_upload_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setFxUploadNumber( String p_fxUploadNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_upload_number = p_fxUploadNumber;
    fx_upload_number_is_set = true;
    fx_upload_number_is_modified = true;
  }


  /** 
   * <em>result_code_soap</em>カラムの値を設定します。 
   *
   * @@param p_resultCodeSoap <em>result_code_soap</em>カラムの値をあらわす4桁以下のString値 
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
   * <em>io_list_trade_div</em>カラムの値を設定します。 
   *
   * @@param p_ioListTradeDiv <em>io_list_trade_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setIoListTradeDiv( String p_ioListTradeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    io_list_trade_div = p_ioListTradeDiv;
    io_list_trade_div_is_set = true;
    io_list_trade_div_is_modified = true;
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
                break;
            case 'c':
                if ( name.equals("course_div") ) {
                    return this.course_div;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("error_reason_code") ) {
                    return this.error_reason_code;
                }
                break;
            case 'f':
                if ( name.equals("fx_account_code") ) {
                    return this.fx_account_code;
                }
                else if ( name.equals("fx_upload_number") ) {
                    return this.fx_upload_number;
                }
                else if ( name.equals("fx_system_code") ) {
                    return this.fx_system_code;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("io_list_trade_div") ) {
                    return this.io_list_trade_div;
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
            case 'm':
                if ( name.equals("mrg_trn_order_request_number") ) {
                    return this.mrg_trn_order_request_number;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("operation_div") ) {
                    return this.operation_div;
                }
                break;
            case 'r':
                if ( name.equals("result_code") ) {
                    return this.result_code;
                }
                else if ( name.equals("receive_time") ) {
                    return this.receive_time;
                }
                else if ( name.equals("result_code_soap") ) {
                    return this.result_code_soap;
                }
                break;
            case 's':
                if ( name.equals("send_rcv_div") ) {
                    return this.send_rcv_div;
                }
                else if ( name.equals("send_time") ) {
                    return this.send_time;
                }
                break;
            case 't':
                if ( name.equals("transfer_date") ) {
                    return this.transfer_date;
                }
                else if ( name.equals("transfer_status_div") ) {
                    return this.transfer_status_div;
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
                break;
            case 'c':
                if ( name.equals("course_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'course_div' must be String: '"+value+"' is not." );
                    this.course_div = (String) value;
                    if (this.course_div_is_set)
                        this.course_div_is_modified = true;
                    this.course_div_is_set = true;
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
                break;
            case 'f':
                if ( name.equals("fx_account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_account_code' must be String: '"+value+"' is not." );
                    this.fx_account_code = (String) value;
                    if (this.fx_account_code_is_set)
                        this.fx_account_code_is_modified = true;
                    this.fx_account_code_is_set = true;
                    return;
                }
                else if ( name.equals("fx_upload_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_upload_number' must be String: '"+value+"' is not." );
                    this.fx_upload_number = (String) value;
                    if (this.fx_upload_number_is_set)
                        this.fx_upload_number_is_modified = true;
                    this.fx_upload_number_is_set = true;
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
                else if ( name.equals("io_list_trade_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'io_list_trade_div' must be String: '"+value+"' is not." );
                    this.io_list_trade_div = (String) value;
                    if (this.io_list_trade_div_is_set)
                        this.io_list_trade_div_is_modified = true;
                    this.io_list_trade_div_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
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
                if ( name.equals("mrg_trn_order_request_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mrg_trn_order_request_number' must be String: '"+value+"' is not." );
                    this.mrg_trn_order_request_number = (String) value;
                    if (this.mrg_trn_order_request_number_is_set)
                        this.mrg_trn_order_request_number_is_modified = true;
                    this.mrg_trn_order_request_number_is_set = true;
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
                else if ( name.equals("operation_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'operation_div' must be String: '"+value+"' is not." );
                    this.operation_div = (String) value;
                    if (this.operation_div_is_set)
                        this.operation_div_is_modified = true;
                    this.operation_div_is_set = true;
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
                else if ( name.equals("receive_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'receive_time' must be String: '"+value+"' is not." );
                    this.receive_time = (String) value;
                    if (this.receive_time_is_set)
                        this.receive_time_is_modified = true;
                    this.receive_time_is_set = true;
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
                else if ( name.equals("send_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'send_time' must be String: '"+value+"' is not." );
                    this.send_time = (String) value;
                    if (this.send_time_is_set)
                        this.send_time_is_modified = true;
                    this.send_time_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("transfer_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_date' must be String: '"+value+"' is not." );
                    this.transfer_date = (String) value;
                    if (this.transfer_date_is_set)
                        this.transfer_date_is_modified = true;
                    this.transfer_date_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_status_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_status_div' must be String: '"+value+"' is not." );
                    this.transfer_status_div = (String) value;
                    if (this.transfer_status_div_is_set)
                        this.transfer_status_div_is_modified = true;
                    this.transfer_status_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
