head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.17.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostConditionRegVoucherRow.java;


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
 * HostConditionRegVoucherRowインタフェースは変更不可でリードオンリーである<em>host_condition_reg_voucher</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link HostConditionRegVoucherRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostConditionRegVoucherPK 
 */
public interface HostConditionRegVoucherRow extends Row {


  /** 
   * この{@@link HostConditionRegVoucherRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "host_condition_reg_voucher", "session" );


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
   * <em>pos_report_term_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPosReportTermDiv();


  /** 
   * <em>pos_report_term_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPosReportTermDivIsSet();


  /** 
   * <em>pos_report_term_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPosReportTermDivIsModified();


  /** 
   * <em>pos_report_cycle_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPosReportCycleDiv();


  /** 
   * <em>pos_report_cycle_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPosReportCycleDivIsSet();


  /** 
   * <em>pos_report_cycle_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPosReportCycleDivIsModified();


  /** 
   * <em>pos_report_certif_depo_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPosReportCertifDepoDiv();


  /** 
   * <em>pos_report_certif_depo_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPosReportCertifDepoDivIsSet();


  /** 
   * <em>pos_report_certif_depo_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPosReportCertifDepoDivIsModified();


  /** 
   * <em>pos_report_acc_state_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPosReportAccStateDiv();


  /** 
   * <em>pos_report_acc_state_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPosReportAccStateDivIsSet();


  /** 
   * <em>pos_report_acc_state_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPosReportAccStateDivIsModified();


  /** 
   * <em>trading_e_report_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradingEReportDiv();


  /** 
   * <em>trading_e_report_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingEReportDivIsSet();


  /** 
   * <em>trading_e_report_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingEReportDivIsModified();


  /** 
   * <em>inv_e_report_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInvEReportDiv();


  /** 
   * <em>inv_e_report_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInvEReportDivIsSet();


  /** 
   * <em>inv_e_report_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInvEReportDivIsModified();


  /** 
   * <em>refund_e_report_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRefundEReportDiv();


  /** 
   * <em>refund_e_report_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRefundEReportDivIsSet();


  /** 
   * <em>refund_e_report_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRefundEReportDivIsModified();


  /** 
   * <em>equity_tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEquityTaxDiv();


  /** 
   * <em>equity_tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityTaxDivIsSet();


  /** 
   * <em>equity_tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityTaxDivIsModified();


  /** 
   * <em>equity_tax_div_next</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEquityTaxDivNext();


  /** 
   * <em>equity_tax_div_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityTaxDivNextIsSet();


  /** 
   * <em>equity_tax_div_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityTaxDivNextIsModified();


  /** 
   * <em>equity_sp_acc_open_dat</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEquitySpAccOpenDat();


  /** 
   * <em>equity_sp_acc_open_dat</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquitySpAccOpenDatIsSet();


  /** 
   * <em>equity_sp_acc_open_dat</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquitySpAccOpenDatIsModified();


  /** 
   * <em>margin_tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginTaxDiv();


  /** 
   * <em>margin_tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxDivIsSet();


  /** 
   * <em>margin_tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxDivIsModified();


  /** 
   * <em>margin_tax_div_next</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginTaxDivNext();


  /** 
   * <em>margin_tax_div_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxDivNextIsSet();


  /** 
   * <em>margin_tax_div_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxDivNextIsModified();


  /** 
   * <em>margin_sp_acc_open_dat</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginSpAccOpenDat();


  /** 
   * <em>margin_sp_acc_open_dat</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSpAccOpenDatIsSet();


  /** 
   * <em>margin_sp_acc_open_dat</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSpAccOpenDatIsModified();


  /** 
   * <em>sp_mng_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSpMngAccOpenDiv();


  /** 
   * <em>sp_mng_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpMngAccOpenDivIsSet();


  /** 
   * <em>sp_mng_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpMngAccOpenDivIsModified();


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
