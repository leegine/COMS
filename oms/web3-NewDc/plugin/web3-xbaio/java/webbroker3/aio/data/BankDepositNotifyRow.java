head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.44.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	BankDepositNotifyRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * BankDepositNotifyRowインタフェースは変更不可でリードオンリーである<em>bank_deposit_notify</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link BankDepositNotifyRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BankDepositNotifyPK 
 */
public interface BankDepositNotifyRow extends Row {


  /** 
   * この{@@link BankDepositNotifyRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "bank_deposit_notify", "session" );


  /** 
   * <em>bank_deposit_notify_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getBankDepositNotifyId();


  /** 
   * <em>bank_deposit_notify_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBankDepositNotifyIdIsSet();


  /** 
   * <em>bank_deposit_notify_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBankDepositNotifyIdIsModified();


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInstitutionCode();


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsSet();


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsModified();


  /** 
   * <em>bank_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBankCode();


  /** 
   * <em>bank_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBankCodeIsSet();


  /** 
   * <em>bank_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBankCodeIsModified();


  /** 
   * <em>bank_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBankBranchCode();


  /** 
   * <em>bank_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBankBranchCodeIsSet();


  /** 
   * <em>bank_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBankBranchCodeIsModified();


  /** 
   * <em>bank_account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBankAccountNo();


  /** 
   * <em>bank_account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBankAccountNoIsSet();


  /** 
   * <em>bank_account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBankAccountNoIsModified();


  /** 
   * <em>deposit_data_reference_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDepositDataReferenceNo();


  /** 
   * <em>deposit_data_reference_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataReferenceNoIsSet();


  /** 
   * <em>deposit_data_reference_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataReferenceNoIsModified();


  /** 
   * <em>deposit_data_account_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDepositDataAccountDate();


  /** 
   * <em>deposit_data_account_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataAccountDateIsSet();


  /** 
   * <em>deposit_data_account_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataAccountDateIsModified();


  /** 
   * <em>deposit_data_bank_account_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDepositDataBankAccountType();


  /** 
   * <em>deposit_data_bank_account_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataBankAccountTypeIsSet();


  /** 
   * <em>deposit_data_bank_account_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataBankAccountTypeIsModified();


  /** 
   * <em>deposit_data_base_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDepositDataBaseDate();


  /** 
   * <em>deposit_data_base_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataBaseDateIsSet();


  /** 
   * <em>deposit_data_base_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataBaseDateIsModified();


  /** 
   * <em>deposit_data_deposit_amount</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDepositDataDepositAmount();


  /** 
   * <em>deposit_data_deposit_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataDepositAmountIsSet();


  /** 
   * <em>deposit_data_deposit_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataDepositAmountIsModified();


  /** 
   * <em>deposit_data_trans_person_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDepositDataTransPersonCode();


  /** 
   * <em>deposit_data_trans_person_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataTransPersonCodeIsSet();


  /** 
   * <em>deposit_data_trans_person_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataTransPersonCodeIsModified();


  /** 
   * <em>deposit_data_trans_person_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDepositDataTransPersonName();


  /** 
   * <em>deposit_data_trans_person_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataTransPersonNameIsSet();


  /** 
   * <em>deposit_data_trans_person_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDataTransPersonNameIsModified();


  /** 
   * <em>cash_transfer_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCashTransferDiv();


  /** 
   * <em>cash_transfer_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashTransferDivIsSet();


  /** 
   * <em>cash_transfer_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashTransferDivIsModified();


  /** 
   * <em>trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradeDiv();


  /** 
   * <em>trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeDivIsSet();


  /** 
   * <em>trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeDivIsModified();


  /** 
   * <em>delivered_bank_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDeliveredBankName();


  /** 
   * <em>delivered_bank_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveredBankNameIsSet();


  /** 
   * <em>delivered_bank_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveredBankNameIsModified();


  /** 
   * <em>delivered_bank_branch_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDeliveredBankBranchName();


  /** 
   * <em>delivered_bank_branch_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveredBankBranchNameIsSet();


  /** 
   * <em>delivered_bank_branch_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveredBankBranchNameIsModified();


  /** 
   * <em>summary</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSummary();


  /** 
   * <em>summary</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSummaryIsSet();


  /** 
   * <em>summary</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSummaryIsModified();


  /** 
   * <em>edi_info</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEdiInfo();


  /** 
   * <em>edi_info</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEdiInfoIsSet();


  /** 
   * <em>edi_info</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEdiInfoIsModified();


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStatus();


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsSet();


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsModified();


  /** 
   * <em>deposit_error_comment</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDepositErrorComment();


  /** 
   * <em>deposit_error_comment</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositErrorCommentIsSet();


  /** 
   * <em>deposit_error_comment</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositErrorCommentIsModified();


  /** 
   * <em>remark</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRemark();


  /** 
   * <em>remark</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRemarkIsSet();


  /** 
   * <em>remark</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRemarkIsModified();


  /** 
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBranchCode();


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeIsSet();


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeIsModified();


  /** 
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountCode();


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeIsSet();


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeIsModified();


  /** 
   * <em>last_error_history_serial_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLastErrorHistorySerialNo();


  /** 
   * <em>last_error_history_serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastErrorHistorySerialNoIsSet();


  /** 
   * <em>last_error_history_serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastErrorHistorySerialNoIsModified();


  /** 
   * <em>update_person</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getUpdatePerson();


  /** 
   * <em>update_person</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUpdatePersonIsSet();


  /** 
   * <em>update_person</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUpdatePersonIsModified();


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getCreatedTimestamp();


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatedTimestampIsSet();


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatedTimestampIsModified();


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getLastUpdatedTimestamp();


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdatedTimestampIsSet();


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdatedTimestampIsModified();


  /** 
   * <em>currency_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCurrencyCode();


  /** 
   * <em>currency_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyCodeIsSet();


  /** 
   * <em>currency_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyCodeIsModified();


  /** 
   * <em>data_load_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDataLoadDiv();


  /** 
   * <em>data_load_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDataLoadDivIsSet();


  /** 
   * <em>data_load_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDataLoadDivIsModified();


}
@
