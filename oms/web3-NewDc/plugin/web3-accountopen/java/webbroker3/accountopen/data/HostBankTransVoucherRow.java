head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.15.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostBankTransVoucherRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * HostBankTransVoucherRowインタフェースは変更不可でリードオンリーである<em>host_bank_trans_voucher</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link HostBankTransVoucherRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostBankTransVoucherPK 
 */
public interface HostBankTransVoucherRow extends Row {


  /** 
   * この{@@link HostBankTransVoucherRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "host_bank_trans_voucher", "session" );


  /** 
   * <em>order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderRequestNumber();


  /** 
   * <em>order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRequestNumberIsSet();


  /** 
   * <em>order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRequestNumberIsModified();


  /** 
   * <em>request_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRequestCode();


  /** 
   * <em>request_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRequestCodeIsSet();


  /** 
   * <em>request_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRequestCodeIsModified();


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
   * <em>trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTraderCode();


  /** 
   * <em>trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderCodeIsSet();


  /** 
   * <em>trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderCodeIsModified();


  /** 
   * <em>acc_open_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccOpenRequestNumber();


  /** 
   * <em>acc_open_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccOpenRequestNumberIsSet();


  /** 
   * <em>acc_open_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccOpenRequestNumberIsModified();


  /** 
   * <em>serial_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSerialNo();


  /** 
   * <em>serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSerialNoIsSet();


  /** 
   * <em>serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSerialNoIsModified();


  /** 
   * <em>transfer_range</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTransferRange();


  /** 
   * <em>transfer_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferRangeIsSet();


  /** 
   * <em>transfer_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferRangeIsModified();


  /** 
   * <em>product_type_code_spec</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductTypeCodeSpec();


  /** 
   * <em>product_type_code_spec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductTypeCodeSpecIsSet();


  /** 
   * <em>product_type_code_spec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductTypeCodeSpecIsModified();


  /** 
   * <em>product_code_spec</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductCodeSpec();


  /** 
   * <em>product_code_spec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductCodeSpecIsSet();


  /** 
   * <em>product_code_spec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductCodeSpecIsModified();


  /** 
   * <em>regist_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRegistDiv();


  /** 
   * <em>regist_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegistDivIsSet();


  /** 
   * <em>regist_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegistDivIsModified();


  /** 
   * <em>transfer_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTransferDiv();


  /** 
   * <em>transfer_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferDivIsSet();


  /** 
   * <em>transfer_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferDivIsModified();


  /** 
   * <em>trans_commission</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTransCommission();


  /** 
   * <em>trans_commission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransCommissionIsSet();


  /** 
   * <em>trans_commission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransCommissionIsModified();


  /** 
   * <em>trans_deal_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTransDealDiv();


  /** 
   * <em>trans_deal_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransDealDivIsSet();


  /** 
   * <em>trans_deal_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransDealDivIsModified();


  /** 
   * <em>fin_institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFinInstitutionCode();


  /** 
   * <em>fin_institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinInstitutionCodeIsSet();


  /** 
   * <em>fin_institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinInstitutionCodeIsModified();


  /** 
   * <em>fin_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFinBranchCode();


  /** 
   * <em>fin_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinBranchCodeIsSet();


  /** 
   * <em>fin_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinBranchCodeIsModified();


  /** 
   * <em>fin_save_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFinSaveDiv();


  /** 
   * <em>fin_save_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinSaveDivIsSet();


  /** 
   * <em>fin_save_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinSaveDivIsModified();


  /** 
   * <em>fin_account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFinAccountNo();


  /** 
   * <em>fin_account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinAccountNoIsSet();


  /** 
   * <em>fin_account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinAccountNoIsModified();


  /** 
   * <em>fin_account_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFinAccountName();


  /** 
   * <em>fin_account_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinAccountNameIsSet();


  /** 
   * <em>fin_account_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinAccountNameIsModified();


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
   * <em>send_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getSendTimestamp();


  /** 
   * <em>send_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendTimestampIsSet();


  /** 
   * <em>send_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendTimestampIsModified();


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


}
@
