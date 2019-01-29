head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.34.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MainAccountAllRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * MainAccountAllRowインタフェースは変更不可でリードオンリーである<em>main_account_all</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link MainAccountAllRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MainAccountAllPK 
 */
public interface MainAccountAllRow extends Row {


  /** 
   * この{@@link MainAccountAllRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "main_account_all", "session" );


  /** 
   * <em>comp_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCompCode();


  /** 
   * <em>comp_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCompCodeIsSet();


  /** 
   * <em>comp_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCompCodeIsModified();


  /** 
   * <em>gen_acc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGenAccDiv();


  /** 
   * <em>gen_acc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGenAccDivIsSet();


  /** 
   * <em>gen_acc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGenAccDivIsModified();


  /** 
   * <em>gen_br_del_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGenBrDelDiv();


  /** 
   * <em>gen_br_del_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGenBrDelDivIsSet();


  /** 
   * <em>gen_br_del_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGenBrDelDivIsModified();


  /** 
   * <em>ruito_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRuitoAccOpenDiv();


  /** 
   * <em>ruito_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRuitoAccOpenDivIsSet();


  /** 
   * <em>ruito_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRuitoAccOpenDivIsModified();


  /** 
   * <em>margin_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginAccOpenDiv();


  /** 
   * <em>margin_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccOpenDivIsSet();


  /** 
   * <em>margin_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccOpenDivIsModified();


  /** 
   * <em>when_issued_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getWhenIssuedAccOpenDiv();


  /** 
   * <em>when_issued_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWhenIssuedAccOpenDivIsSet();


  /** 
   * <em>when_issued_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWhenIssuedAccOpenDivIsModified();


  /** 
   * <em>report_dispatch_stop_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReportDispatchStopDiv();


  /** 
   * <em>report_dispatch_stop_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReportDispatchStopDivIsSet();


  /** 
   * <em>report_dispatch_stop_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReportDispatchStopDivIsModified();


  /** 
   * <em>doc_dispatch_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDocDispatchDiv();


  /** 
   * <em>doc_dispatch_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDocDispatchDivIsSet();


  /** 
   * <em>doc_dispatch_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDocDispatchDivIsModified();


  /** 
   * <em>gp_br_del_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpBrDelDiv();


  /** 
   * <em>gp_br_del_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpBrDelDivIsSet();


  /** 
   * <em>gp_br_del_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpBrDelDivIsModified();


  /** 
   * <em>account_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getAccountOpenDate();


  /** 
   * <em>account_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDateIsSet();


  /** 
   * <em>account_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDateIsModified();


  /** 
   * <em>last_update_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getLastUpdateDate();


  /** 
   * <em>last_update_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdateDateIsSet();


  /** 
   * <em>last_update_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdateDateIsModified();


  /** 
   * <em>br_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBrCode();


  /** 
   * <em>br_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBrCodeIsSet();


  /** 
   * <em>br_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBrCodeIsModified();


  /** 
   * <em>cust_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCustCode();


  /** 
   * <em>cust_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustCodeIsSet();


  /** 
   * <em>cust_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustCodeIsModified();


  /** 
   * <em>cust_code_cd</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCustCodeCd();


  /** 
   * <em>cust_code_cd</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustCodeCdIsSet();


  /** 
   * <em>cust_code_cd</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustCodeCdIsModified();


  /** 
   * <em>sonar_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarTraderCode();


  /** 
   * <em>sonar_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeIsSet();


  /** 
   * <em>sonar_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeIsModified();


  /** 
   * <em>rep_dispatch_stp_bd</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRepDispatchStpBd();


  /** 
   * <em>rep_dispatch_stp_bd</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepDispatchStpBdIsSet();


  /** 
   * <em>rep_dispatch_stp_bd</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepDispatchStpBdIsModified();


  /** 
   * <em>occupation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOccupation();


  /** 
   * <em>occupation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOccupationIsSet();


  /** 
   * <em>occupation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOccupationIsModified();


  /** 
   * <em>safe_cont_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSafeContOpenDiv();


  /** 
   * <em>safe_cont_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSafeContOpenDivIsSet();


  /** 
   * <em>safe_cont_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSafeContOpenDivIsModified();


  /** 
   * <em>foreign_sec_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForeignSecAccOpenDiv();


  /** 
   * <em>foreign_sec_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignSecAccOpenDivIsSet();


  /** 
   * <em>foreign_sec_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignSecAccOpenDivIsModified();


  /** 
   * <em>tokuyu_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTokuyuAccOpenDiv();


  /** 
   * <em>tokuyu_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokuyuAccOpenDivIsSet();


  /** 
   * <em>tokuyu_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokuyuAccOpenDivIsModified();


  /** 
   * <em>gold_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGoldAccOpenDiv();


  /** 
   * <em>gold_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGoldAccOpenDivIsSet();


  /** 
   * <em>gold_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGoldAccOpenDivIsModified();


  /** 
   * <em>total_trade_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTotalTradeOpenDiv();


  /** 
   * <em>total_trade_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTotalTradeOpenDivIsSet();


  /** 
   * <em>total_trade_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTotalTradeOpenDivIsModified();


  /** 
   * <em>tie_up_loan_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTieUpLoanOpenDiv();


  /** 
   * <em>tie_up_loan_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTieUpLoanOpenDivIsSet();


  /** 
   * <em>tie_up_loan_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTieUpLoanOpenDivIsModified();


  /** 
   * <em>ifo_acc_open_div_tokyo</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoAccOpenDivTokyo();


  /** 
   * <em>ifo_acc_open_div_tokyo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDivTokyoIsSet();


  /** 
   * <em>ifo_acc_open_div_tokyo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDivTokyoIsModified();


  /** 
   * <em>address_unknown</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressUnknown();


  /** 
   * <em>address_unknown</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressUnknownIsSet();


  /** 
   * <em>address_unknown</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressUnknownIsModified();


  /** 
   * <em>cust_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCustDiv();


  /** 
   * <em>cust_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustDivIsSet();


  /** 
   * <em>cust_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustDivIsModified();


  /** 
   * <em>deposit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDepositDiv();


  /** 
   * <em>deposit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDivIsSet();


  /** 
   * <em>deposit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositDivIsModified();


  /** 
   * <em>all_substitution_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAllSubstitutionDiv();


  /** 
   * <em>all_substitution_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAllSubstitutionDivIsSet();


  /** 
   * <em>all_substitution_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAllSubstitutionDivIsModified();


  /** 
   * <em>ins_loan_bill_mthd_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInsLoanBillMthdDiv();


  /** 
   * <em>ins_loan_bill_mthd_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsLoanBillMthdDivIsSet();


  /** 
   * <em>ins_loan_bill_mthd_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsLoanBillMthdDivIsModified();


  /** 
   * <em>ins_loan_cer_mthd_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInsLoanCerMthdDiv();


  /** 
   * <em>ins_loan_cer_mthd_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsLoanCerMthdDivIsSet();


  /** 
   * <em>ins_loan_cer_mthd_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsLoanCerMthdDivIsModified();


  /** 
   * <em>ins_loan_clause_mthd_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInsLoanClauseMthdDiv();


  /** 
   * <em>ins_loan_clause_mthd_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsLoanClauseMthdDivIsSet();


  /** 
   * <em>ins_loan_clause_mthd_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsLoanClauseMthdDivIsModified();


  /** 
   * <em>dom_tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDomTaxDiv();


  /** 
   * <em>dom_tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDomTaxDivIsSet();


  /** 
   * <em>dom_tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDomTaxDivIsModified();


  /** 
   * <em>client_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getClientTraderCode();


  /** 
   * <em>client_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClientTraderCodeIsSet();


  /** 
   * <em>client_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClientTraderCodeIsModified();


  /** 
   * <em>telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTelephone();


  /** 
   * <em>telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTelephoneIsSet();


  /** 
   * <em>telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTelephoneIsModified();


  /** 
   * <em>family_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFamilyNameAlt1();


  /** 
   * <em>family_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNameAlt1IsSet();


  /** 
   * <em>family_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNameAlt1IsModified();


  /** 
   * <em>zip_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getZipCode();


  /** 
   * <em>zip_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getZipCodeIsSet();


  /** 
   * <em>zip_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getZipCodeIsModified();


  /** 
   * <em>prefecture</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPrefecture();


  /** 
   * <em>prefecture</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrefectureIsSet();


  /** 
   * <em>prefecture</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrefectureIsModified();


  /** 
   * <em>comma</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getComma();


  /** 
   * <em>comma</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommaIsSet();


  /** 
   * <em>comma</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommaIsModified();


  /** 
   * <em>address_line1_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine1Kana();


  /** 
   * <em>address_line1_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine1KanaIsSet();


  /** 
   * <em>address_line1_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine1KanaIsModified();


  /** 
   * <em>address_line2_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine2Kana();


  /** 
   * <em>address_line2_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine2KanaIsSet();


  /** 
   * <em>address_line2_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine2KanaIsModified();


  /** 
   * <em>address_line3_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine3Kana();


  /** 
   * <em>address_line3_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine3KanaIsSet();


  /** 
   * <em>address_line3_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine3KanaIsModified();


  /** 
   * <em>family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFamilyName();


  /** 
   * <em>family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNameIsSet();


  /** 
   * <em>family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNameIsModified();


  /** 
   * <em>address_line1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine1();


  /** 
   * <em>address_line1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine1IsSet();


  /** 
   * <em>address_line1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine1IsModified();


  /** 
   * <em>address_line2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine2();


  /** 
   * <em>address_line2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine2IsSet();


  /** 
   * <em>address_line2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine2IsModified();


  /** 
   * <em>address_line3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine3();


  /** 
   * <em>address_line3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine3IsSet();


  /** 
   * <em>address_line3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine3IsModified();


  /** 
   * <em>contact_address_telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getContactAddressTelephone();


  /** 
   * <em>contact_address_telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactAddressTelephoneIsSet();


  /** 
   * <em>contact_address_telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactAddressTelephoneIsModified();


  /** 
   * <em>contact_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getContactAddress();


  /** 
   * <em>contact_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactAddressIsSet();


  /** 
   * <em>contact_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactAddressIsModified();


  /** 
   * <em>era_born</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEraBorn();


  /** 
   * <em>era_born</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEraBornIsSet();


  /** 
   * <em>era_born</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEraBornIsModified();


  /** 
   * <em>born_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBornDate();


  /** 
   * <em>born_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBornDateIsSet();


  /** 
   * <em>born_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBornDateIsModified();


  /** 
   * <em>sex</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSex();


  /** 
   * <em>sex</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSexIsSet();


  /** 
   * <em>sex</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSexIsModified();


  /** 
   * <em>before_acc_trans_br_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBeforeAccTransBrCode();


  /** 
   * <em>before_acc_trans_br_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBeforeAccTransBrCodeIsSet();


  /** 
   * <em>before_acc_trans_br_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBeforeAccTransBrCodeIsModified();


  /** 
   * <em>before_acc_trans_cust_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBeforeAccTransCustCode();


  /** 
   * <em>before_acc_trans_cust_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBeforeAccTransCustCodeIsSet();


  /** 
   * <em>before_acc_trans_cust_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBeforeAccTransCustCodeIsModified();


  /** 
   * <em>d_card_appli_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getDCardAppliDate();


  /** 
   * <em>d_card_appli_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardAppliDateIsSet();


  /** 
   * <em>d_card_appli_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardAppliDateIsModified();


  /** 
   * <em>d_card_issue_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getDCardIssueDate();


  /** 
   * <em>d_card_issue_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardIssueDateIsSet();


  /** 
   * <em>d_card_issue_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardIssueDateIsModified();


  /** 
   * <em>d_card_password</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDCardPassword();


  /** 
   * <em>d_card_password</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardPasswordIsSet();


  /** 
   * <em>d_card_password</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardPasswordIsModified();


  /** 
   * <em>d_card_issue_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDCardIssueReason();


  /** 
   * <em>d_card_issue_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardIssueReasonIsSet();


  /** 
   * <em>d_card_issue_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardIssueReasonIsModified();


  /** 
   * <em>d_card_issue_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDCardIssueNumber();


  /** 
   * <em>d_card_issue_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardIssueNumberIsSet();


  /** 
   * <em>d_card_issue_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardIssueNumberIsModified();


  /** 
   * <em>d_card_stop_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getDCardStopDate();


  /** 
   * <em>d_card_stop_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardStopDateIsSet();


  /** 
   * <em>d_card_stop_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardStopDateIsModified();


  /** 
   * <em>d_card_stop_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDCardStopReason();


  /** 
   * <em>d_card_stop_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardStopReasonIsSet();


  /** 
   * <em>d_card_stop_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardStopReasonIsModified();


  /** 
   * <em>d_card_cancel_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getDCardCancelDate();


  /** 
   * <em>d_card_cancel_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardCancelDateIsSet();


  /** 
   * <em>d_card_cancel_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardCancelDateIsModified();


  /** 
   * <em>d_card_cancel_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDCardCancelReason();


  /** 
   * <em>d_card_cancel_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardCancelReasonIsSet();


  /** 
   * <em>d_card_cancel_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardCancelReasonIsModified();


  /** 
   * <em>d_card_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDCardName();


  /** 
   * <em>d_card_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardNameIsSet();


  /** 
   * <em>d_card_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDCardNameIsModified();


  /** 
   * <em>pass_err_code_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPassErrCodeType();


  /** 
   * <em>pass_err_code_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPassErrCodeTypeIsSet();


  /** 
   * <em>pass_err_code_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPassErrCodeTypeIsModified();


  /** 
   * <em>pass_err_code_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPassErrCodeNumber();


  /** 
   * <em>pass_err_code_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPassErrCodeNumberIsSet();


  /** 
   * <em>pass_err_code_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPassErrCodeNumberIsModified();


  /** 
   * <em>pass_err_code_chg_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getPassErrCodeChgDate();


  /** 
   * <em>pass_err_code_chg_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPassErrCodeChgDateIsSet();


  /** 
   * <em>pass_err_code_chg_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPassErrCodeChgDateIsModified();


  /** 
   * <em>pass_err_code_chg_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPassErrCodeChgTime();


  /** 
   * <em>pass_err_code_chg_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPassErrCodeChgTimeIsSet();


  /** 
   * <em>pass_err_code_chg_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPassErrCodeChgTimeIsModified();


  /** 
   * <em>ans_cust_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAnsCustDiv();


  /** 
   * <em>ans_cust_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnsCustDivIsSet();


  /** 
   * <em>ans_cust_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnsCustDivIsModified();


  /** 
   * <em>ans_stock_appli_cate</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getAnsStockAppliCate();


  /** 
   * <em>ans_stock_appli_cate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnsStockAppliCateIsSet();


  /** 
   * <em>ans_stock_appli_cate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnsStockAppliCateIsModified();


  /** 
   * <em>dummy1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy1();


  /** 
   * <em>dummy1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy1IsSet();


  /** 
   * <em>dummy1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy1IsModified();


  /** 
   * <em>self_assessed_sep_tax</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSelfAssessedSepTax();


  /** 
   * <em>self_assessed_sep_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSelfAssessedSepTaxIsSet();


  /** 
   * <em>self_assessed_sep_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSelfAssessedSepTaxIsModified();


  /** 
   * <em>tokuyu_appli_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTokuyuAppliDiv();


  /** 
   * <em>tokuyu_appli_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokuyuAppliDivIsSet();


  /** 
   * <em>tokuyu_appli_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokuyuAppliDivIsModified();


  /** 
   * <em>maruyu_appli_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMaruyuAppliDiv();


  /** 
   * <em>maruyu_appli_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaruyuAppliDivIsSet();


  /** 
   * <em>maruyu_appli_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaruyuAppliDivIsModified();


  /** 
   * <em>tokuyu_laundering_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTokuyuLaunderingDiv();


  /** 
   * <em>tokuyu_laundering_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokuyuLaunderingDivIsSet();


  /** 
   * <em>tokuyu_laundering_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokuyuLaunderingDivIsModified();


  /** 
   * <em>maruyu_laundering_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMaruyuLaunderingDiv();


  /** 
   * <em>maruyu_laundering_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaruyuLaunderingDivIsSet();


  /** 
   * <em>maruyu_laundering_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaruyuLaunderingDivIsModified();


  /** 
   * <em>tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTaxDiv();


  /** 
   * <em>tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxDivIsSet();


  /** 
   * <em>tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxDivIsModified();


  /** 
   * <em>dummy2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy2();


  /** 
   * <em>dummy2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy2IsSet();


  /** 
   * <em>dummy2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy2IsModified();


  /** 
   * <em>ht_settlement_way</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHtSettlementWay();


  /** 
   * <em>ht_settlement_way</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHtSettlementWayIsSet();


  /** 
   * <em>ht_settlement_way</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHtSettlementWayIsModified();


  /** 
   * <em>dummy3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy3();


  /** 
   * <em>dummy3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy3IsSet();


  /** 
   * <em>dummy3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy3IsModified();


  /** 
   * <em>total_tax_identity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTotalTaxIdentity();


  /** 
   * <em>total_tax_identity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTotalTaxIdentityIsSet();


  /** 
   * <em>total_tax_identity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTotalTaxIdentityIsModified();


  /** 
   * <em>dummy4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy4();


  /** 
   * <em>dummy4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy4IsSet();


  /** 
   * <em>dummy4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy4IsModified();


  /** 
   * <em>ifo_acc_open_div_osaka</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoAccOpenDivOsaka();


  /** 
   * <em>ifo_acc_open_div_osaka</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDivOsakaIsSet();


  /** 
   * <em>ifo_acc_open_div_osaka</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDivOsakaIsModified();


  /** 
   * <em>mort_trade_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMortTradeOpenDiv();


  /** 
   * <em>mort_trade_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMortTradeOpenDivIsSet();


  /** 
   * <em>mort_trade_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMortTradeOpenDivIsModified();


  /** 
   * <em>ifo_acc_open_div_nagoya</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoAccOpenDivNagoya();


  /** 
   * <em>ifo_acc_open_div_nagoya</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDivNagoyaIsSet();


  /** 
   * <em>ifo_acc_open_div_nagoya</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDivNagoyaIsModified();


  /** 
   * <em>os_fnc_futures_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOsFncFuturesOpenDiv();


  /** 
   * <em>os_fnc_futures_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOsFncFuturesOpenDivIsSet();


  /** 
   * <em>os_fnc_futures_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOsFncFuturesOpenDivIsModified();


  /** 
   * <em>os_sec_futures_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOsSecFuturesOpenDiv();


  /** 
   * <em>os_sec_futures_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOsSecFuturesOpenDivIsSet();


  /** 
   * <em>os_sec_futures_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOsSecFuturesOpenDivIsModified();


  /** 
   * <em>tokyo_fnc_futures_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTokyoFncFuturesOpenDiv();


  /** 
   * <em>tokyo_fnc_futures_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokyoFncFuturesOpenDivIsSet();


  /** 
   * <em>tokyo_fnc_futures_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokyoFncFuturesOpenDivIsModified();


  /** 
   * <em>for_war_trade_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForWarTradeOpenDiv();


  /** 
   * <em>for_war_trade_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForWarTradeOpenDivIsSet();


  /** 
   * <em>for_war_trade_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForWarTradeOpenDivIsModified();


  /** 
   * <em>tentou_trade_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTentouTradeOpenDiv();


  /** 
   * <em>tentou_trade_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTentouTradeOpenDivIsSet();


  /** 
   * <em>tentou_trade_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTentouTradeOpenDivIsModified();


  /** 
   * <em>represent_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRepresentDiv();


  /** 
   * <em>represent_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepresentDivIsSet();


  /** 
   * <em>represent_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepresentDivIsModified();


  /** 
   * <em>family_nurturer_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFamilyNurturerCode();


  /** 
   * <em>family_nurturer_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNurturerCodeIsSet();


  /** 
   * <em>family_nurturer_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNurturerCodeIsModified();


  /** 
   * <em>family_unit_adimin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFamilyUnitAdimin();


  /** 
   * <em>family_unit_adimin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyUnitAdiminIsSet();


  /** 
   * <em>family_unit_adimin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyUnitAdiminIsModified();


  /** 
   * <em>new_monthly_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNewMonthlyDiv();


  /** 
   * <em>new_monthly_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewMonthlyDivIsSet();


  /** 
   * <em>new_monthly_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewMonthlyDivIsModified();


  /** 
   * <em>statement_skip1_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStatementSkip1Div();


  /** 
   * <em>statement_skip1_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatementSkip1DivIsSet();


  /** 
   * <em>statement_skip1_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatementSkip1DivIsModified();


  /** 
   * <em>statement_skip2_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStatementSkip2Div();


  /** 
   * <em>statement_skip2_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatementSkip2DivIsSet();


  /** 
   * <em>statement_skip2_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatementSkip2DivIsModified();


  /** 
   * <em>family_unit_del_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFamilyUnitDelDiv();


  /** 
   * <em>family_unit_del_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyUnitDelDivIsSet();


  /** 
   * <em>family_unit_del_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyUnitDelDivIsModified();


  /** 
   * <em>dummy5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy5();


  /** 
   * <em>dummy5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy5IsSet();


  /** 
   * <em>dummy5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy5IsModified();


  /** 
   * <em>br_dispatch_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBrDispatchDiv();


  /** 
   * <em>br_dispatch_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBrDispatchDivIsSet();


  /** 
   * <em>br_dispatch_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBrDispatchDivIsModified();


  /** 
   * <em>maruyu_grade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMaruyuGradeDiv();


  /** 
   * <em>maruyu_grade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaruyuGradeDivIsSet();


  /** 
   * <em>maruyu_grade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaruyuGradeDivIsModified();


  /** 
   * <em>dummy6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy6();


  /** 
   * <em>dummy6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy6IsSet();


  /** 
   * <em>dummy6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy6IsModified();


  /** 
   * <em>dummy7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy7();


  /** 
   * <em>dummy7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy7IsSet();


  /** 
   * <em>dummy7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy7IsModified();


  /** 
   * <em>trans_tax_div_acc_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getTransTaxDivAccDate();


  /** 
   * <em>trans_tax_div_acc_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransTaxDivAccDateIsSet();


  /** 
   * <em>trans_tax_div_acc_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransTaxDivAccDateIsModified();


  /** 
   * <em>trans_tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTransTaxDiv();


  /** 
   * <em>trans_tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransTaxDivIsSet();


  /** 
   * <em>trans_tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransTaxDivIsModified();


  /** 
   * <em>resident</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getResident();


  /** 
   * <em>resident</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getResidentIsSet();


  /** 
   * <em>resident</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getResidentIsModified();


  /** 
   * <em>bond_d_and_c_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBondDAndCOpenDiv();


  /** 
   * <em>bond_d_and_c_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondDAndCOpenDivIsSet();


  /** 
   * <em>bond_d_and_c_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondDAndCOpenDivIsModified();


  /** 
   * <em>os_cd_cp_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOsCdCpOpenDiv();


  /** 
   * <em>os_cd_cp_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOsCdCpOpenDivIsSet();


  /** 
   * <em>os_cd_cp_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOsCdCpOpenDivIsModified();


  /** 
   * <em>new_tb_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNewTbOpenDiv();


  /** 
   * <em>new_tb_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewTbOpenDivIsSet();


  /** 
   * <em>new_tb_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewTbOpenDivIsModified();


  /** 
   * <em>dom_cp_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDomCpOpenDiv();


  /** 
   * <em>dom_cp_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDomCpOpenDivIsSet();


  /** 
   * <em>dom_cp_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDomCpOpenDivIsModified();


  /** 
   * <em>bond_tentou_op_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBondTentouOpOpenDiv();


  /** 
   * <em>bond_tentou_op_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondTentouOpOpenDivIsSet();


  /** 
   * <em>bond_tentou_op_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondTentouOpOpenDivIsModified();


  /** 
   * <em>t_bond_futures_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTBondFuturesOpenDiv();


  /** 
   * <em>t_bond_futures_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTBondFuturesOpenDivIsSet();


  /** 
   * <em>t_bond_futures_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTBondFuturesOpenDivIsModified();


  /** 
   * <em>dom_war_trade_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDomWarTradeOpenDiv();


  /** 
   * <em>dom_war_trade_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDomWarTradeOpenDivIsSet();


  /** 
   * <em>dom_war_trade_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDomWarTradeOpenDivIsModified();


  /** 
   * <em>dummy8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy8();


  /** 
   * <em>dummy8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy8IsSet();


  /** 
   * <em>dummy8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy8IsModified();


  /** 
   * <em>dom_for_bond_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDomForBondOpenDiv();


  /** 
   * <em>dom_for_bond_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDomForBondOpenDivIsSet();


  /** 
   * <em>dom_for_bond_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDomForBondOpenDivIsModified();


  /** 
   * <em>tentou_sp_rule_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTentouSpRuleOpenDiv();


  /** 
   * <em>tentou_sp_rule_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTentouSpRuleOpenDivIsSet();


  /** 
   * <em>tentou_sp_rule_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTentouSpRuleOpenDivIsModified();


  /** 
   * <em>dummy9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy9();


  /** 
   * <em>dummy9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy9IsSet();


  /** 
   * <em>dummy9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy9IsModified();


  /** 
   * <em>dummy10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy10();


  /** 
   * <em>dummy10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy10IsSet();


  /** 
   * <em>dummy10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy10IsModified();


  /** 
   * <em>dummy11</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy11();


  /** 
   * <em>dummy11</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy11IsSet();


  /** 
   * <em>dummy11</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy11IsModified();


  /** 
   * <em>dummy12</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy12();


  /** 
   * <em>dummy12</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy12IsSet();


  /** 
   * <em>dummy12</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy12IsModified();


  /** 
   * <em>dummy13</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy13();


  /** 
   * <em>dummy13</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy13IsSet();


  /** 
   * <em>dummy13</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy13IsModified();


  /** 
   * <em>mrf_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMrfAccOpenDiv();


  /** 
   * <em>mrf_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMrfAccOpenDivIsSet();


  /** 
   * <em>mrf_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMrfAccOpenDivIsModified();


  /** 
   * <em>safe_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSafeContDiv();


  /** 
   * <em>safe_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSafeContDivIsSet();


  /** 
   * <em>safe_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSafeContDivIsModified();


  /** 
   * <em>foreign_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForeignContDiv();


  /** 
   * <em>foreign_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignContDivIsSet();


  /** 
   * <em>foreign_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignContDivIsModified();


  /** 
   * <em>dummy14</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy14();


  /** 
   * <em>dummy14</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy14IsSet();


  /** 
   * <em>dummy14</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy14IsModified();


  /** 
   * <em>gold_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGoldContDiv();


  /** 
   * <em>gold_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGoldContDivIsSet();


  /** 
   * <em>gold_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGoldContDivIsModified();


  /** 
   * <em>margin_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginContDiv();


  /** 
   * <em>margin_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginContDivIsSet();


  /** 
   * <em>margin_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginContDivIsModified();


  /** 
   * <em>when_issued_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getWhenIssuedContDiv();


  /** 
   * <em>when_issued_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWhenIssuedContDivIsSet();


  /** 
   * <em>when_issued_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWhenIssuedContDivIsModified();


  /** 
   * <em>futures_op_cont_div_tokyo</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFuturesOpContDivTokyo();


  /** 
   * <em>futures_op_cont_div_tokyo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFuturesOpContDivTokyoIsSet();


  /** 
   * <em>futures_op_cont_div_tokyo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFuturesOpContDivTokyoIsModified();


  /** 
   * <em>dummy15</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy15();


  /** 
   * <em>dummy15</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy15IsSet();


  /** 
   * <em>dummy15</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy15IsModified();


  /** 
   * <em>futures_op_cont_div_osaka</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFuturesOpContDivOsaka();


  /** 
   * <em>futures_op_cont_div_osaka</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFuturesOpContDivOsakaIsSet();


  /** 
   * <em>futures_op_cont_div_osaka</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFuturesOpContDivOsakaIsModified();


  /** 
   * <em>tokyo_mothers_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTokyoMothersContDiv();


  /** 
   * <em>tokyo_mothers_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokyoMothersContDivIsSet();


  /** 
   * <em>tokyo_mothers_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTokyoMothersContDivIsModified();


  /** 
   * <em>futures_op_cont_div_nagoya</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFuturesOpContDivNagoya();


  /** 
   * <em>futures_op_cont_div_nagoya</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFuturesOpContDivNagoyaIsSet();


  /** 
   * <em>futures_op_cont_div_nagoya</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFuturesOpContDivNagoyaIsModified();


  /** 
   * <em>nq_j_gl_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNqJGlContDiv();


  /** 
   * <em>nq_j_gl_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNqJGlContDivIsSet();


  /** 
   * <em>nq_j_gl_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNqJGlContDivIsModified();


  /** 
   * <em>osaka_nw_mkt_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOsakaNwMktContDiv();


  /** 
   * <em>osaka_nw_mkt_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOsakaNwMktContDivIsSet();


  /** 
   * <em>osaka_nw_mkt_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOsakaNwMktContDivIsModified();


  /** 
   * <em>nagoya_grw_cpy_mkt_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNagoyaGrwCpyMktContDiv();


  /** 
   * <em>nagoya_grw_cpy_mkt_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNagoyaGrwCpyMktContDivIsSet();


  /** 
   * <em>nagoya_grw_cpy_mkt_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNagoyaGrwCpyMktContDivIsModified();


  /** 
   * <em>for_war_trade_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForWarTradeContDiv();


  /** 
   * <em>for_war_trade_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForWarTradeContDivIsSet();


  /** 
   * <em>for_war_trade_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForWarTradeContDivIsModified();


  /** 
   * <em>tentou_trade_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTentouTradeContDiv();


  /** 
   * <em>tentou_trade_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTentouTradeContDivIsSet();


  /** 
   * <em>tentou_trade_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTentouTradeContDivIsModified();


  /** 
   * <em>bond_d_and_c_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBondDAndCContDiv();


  /** 
   * <em>bond_d_and_c_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondDAndCContDivIsSet();


  /** 
   * <em>bond_d_and_c_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondDAndCContDivIsModified();


  /** 
   * <em>sapporo_amb_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSapporoAmbContDiv();


  /** 
   * <em>sapporo_amb_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSapporoAmbContDivIsSet();


  /** 
   * <em>sapporo_amb_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSapporoAmbContDivIsModified();


  /** 
   * <em>new_tb_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNewTbContDiv();


  /** 
   * <em>new_tb_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewTbContDivIsSet();


  /** 
   * <em>new_tb_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewTbContDivIsModified();


  /** 
   * <em>gen_credit_acc_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGenCreditAccContDiv();


  /** 
   * <em>gen_credit_acc_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGenCreditAccContDivIsSet();


  /** 
   * <em>gen_credit_acc_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGenCreditAccContDivIsModified();


  /** 
   * <em>bond_tentou_op_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBondTentouOpContDiv();


  /** 
   * <em>bond_tentou_op_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondTentouOpContDivIsSet();


  /** 
   * <em>bond_tentou_op_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondTentouOpContDivIsModified();


  /** 
   * <em>fukuoka_qb_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFukuokaQbContDiv();


  /** 
   * <em>fukuoka_qb_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFukuokaQbContDivIsSet();


  /** 
   * <em>fukuoka_qb_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFukuokaQbContDivIsModified();


  /** 
   * <em>dom_war_trade_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDomWarTradeContDiv();


  /** 
   * <em>dom_war_trade_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDomWarTradeContDivIsSet();


  /** 
   * <em>dom_war_trade_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDomWarTradeContDivIsModified();


  /** 
   * <em>tentou_sec_basis_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTentouSecBasisContDiv();


  /** 
   * <em>tentou_sec_basis_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTentouSecBasisContDivIsSet();


  /** 
   * <em>tentou_sec_basis_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTentouSecBasisContDivIsModified();


  /** 
   * <em>dom_for_bond_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDomForBondContDiv();


  /** 
   * <em>dom_for_bond_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDomForBondContDivIsSet();


  /** 
   * <em>dom_for_bond_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDomForBondContDivIsModified();


  /** 
   * <em>tentou_sp_rule_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTentouSpRuleContDiv();


  /** 
   * <em>tentou_sp_rule_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTentouSpRuleContDivIsSet();


  /** 
   * <em>tentou_sp_rule_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTentouSpRuleContDivIsModified();


  /** 
   * <em>dummy16</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy16();


  /** 
   * <em>dummy16</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy16IsSet();


  /** 
   * <em>dummy16</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy16IsModified();


  /** 
   * <em>dummy17</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy17();


  /** 
   * <em>dummy17</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy17IsSet();


  /** 
   * <em>dummy17</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy17IsModified();


  /** 
   * <em>dummy18</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy18();


  /** 
   * <em>dummy18</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy18IsSet();


  /** 
   * <em>dummy18</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy18IsModified();


  /** 
   * <em>dummy19</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy19();


  /** 
   * <em>dummy19</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy19IsSet();


  /** 
   * <em>dummy19</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy19IsModified();


  /** 
   * <em>dummy20</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy20();


  /** 
   * <em>dummy20</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy20IsSet();


  /** 
   * <em>dummy20</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy20IsModified();


  /** 
   * <em>mrf_contract_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMrfContractDiv();


  /** 
   * <em>mrf_contract_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMrfContractDivIsSet();


  /** 
   * <em>mrf_contract_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMrfContractDivIsModified();


  /** 
   * <em>new_account_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNewAccountDiv();


  /** 
   * <em>new_account_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewAccountDivIsSet();


  /** 
   * <em>new_account_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewAccountDivIsModified();


  /** 
   * <em>via_trust_bank_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getViaTrustBankDiv();


  /** 
   * <em>via_trust_bank_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getViaTrustBankDivIsSet();


  /** 
   * <em>via_trust_bank_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getViaTrustBankDivIsModified();


  /** 
   * <em>class_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getClassDiv();


  /** 
   * <em>class_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClassDivIsSet();


  /** 
   * <em>class_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClassDivIsModified();


  /** 
   * <em>address_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressCode();


  /** 
   * <em>address_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressCodeIsSet();


  /** 
   * <em>address_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressCodeIsModified();


  /** 
   * <em>person_identify</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPersonIdentify();


  /** 
   * <em>person_identify</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPersonIdentifyIsSet();


  /** 
   * <em>person_identify</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPersonIdentifyIsModified();


  /** 
   * <em>cancel_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCancelDiv();


  /** 
   * <em>cancel_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCancelDivIsSet();


  /** 
   * <em>cancel_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCancelDivIsModified();


  /** 
   * <em>add_chg_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddChgDiv();


  /** 
   * <em>add_chg_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddChgDivIsSet();


  /** 
   * <em>add_chg_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddChgDivIsModified();


  /** 
   * <em>reserve</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReserve();


  /** 
   * <em>reserve</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReserveIsSet();


  /** 
   * <em>reserve</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReserveIsModified();


  /** 
   * <em>org_deposit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrgDepositDiv();


  /** 
   * <em>org_deposit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgDepositDivIsSet();


  /** 
   * <em>org_deposit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgDepositDivIsModified();


  /** 
   * <em>eq_hold_rep_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEqHoldRepDiv();


  /** 
   * <em>eq_hold_rep_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEqHoldRepDivIsSet();


  /** 
   * <em>eq_hold_rep_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEqHoldRepDivIsModified();


  /** 
   * <em>chkup_rep_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getChkupRepDiv();


  /** 
   * <em>chkup_rep_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChkupRepDivIsSet();


  /** 
   * <em>chkup_rep_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChkupRepDivIsModified();


  /** 
   * <em>fax</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFax();


  /** 
   * <em>fax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFaxIsSet();


  /** 
   * <em>fax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFaxIsModified();


  /** 
   * <em>hq_input_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHqInputDiv();


  /** 
   * <em>hq_input_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHqInputDivIsSet();


  /** 
   * <em>hq_input_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHqInputDivIsModified();


  /** 
   * <em>yellow_customer</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getYellowCustomer();


  /** 
   * <em>yellow_customer</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getYellowCustomerIsSet();


  /** 
   * <em>yellow_customer</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getYellowCustomerIsModified();


  /** 
   * <em>dummy21</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy21();


  /** 
   * <em>dummy21</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy21IsSet();


  /** 
   * <em>dummy21</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy21IsModified();


  /** 
   * <em>dummy22</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDummy22();


  /** 
   * <em>dummy22</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy22IsSet();


  /** 
   * <em>dummy22</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDummy22IsModified();


  /** 
   * <em>bond_butt_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBondButtDiv();


  /** 
   * <em>bond_butt_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondButtDivIsSet();


  /** 
   * <em>bond_butt_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondButtDivIsModified();


  /** 
   * <em>hofuri_entry</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHofuriEntry();


  /** 
   * <em>hofuri_entry</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHofuriEntryIsSet();


  /** 
   * <em>hofuri_entry</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHofuriEntryIsModified();


  /** 
   * <em>agreed_non_pkg_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgreedNonPkgDiv();


  /** 
   * <em>agreed_non_pkg_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgreedNonPkgDivIsSet();


  /** 
   * <em>agreed_non_pkg_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgreedNonPkgDivIsModified();


  /** 
   * <em>position_report_cycle_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPositionReportCycleDiv();


  /** 
   * <em>position_report_cycle_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPositionReportCycleDivIsSet();


  /** 
   * <em>position_report_cycle_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPositionReportCycleDivIsModified();


  /** 
   * <em>position_report_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPositionReportDiv();


  /** 
   * <em>position_report_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPositionReportDivIsSet();


  /** 
   * <em>position_report_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPositionReportDivIsModified();


  /** 
   * <em>certificate_deposit_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCertificateDepositFlag();


  /** 
   * <em>certificate_deposit_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCertificateDepositFlagIsSet();


  /** 
   * <em>certificate_deposit_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCertificateDepositFlagIsModified();


  /** 
   * <em>account_statement_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountStatementFlag();


  /** 
   * <em>account_statement_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountStatementFlagIsSet();


  /** 
   * <em>account_statement_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountStatementFlagIsModified();


  /** 
   * <em>trading_report_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradingReportDiv();


  /** 
   * <em>trading_report_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingReportDivIsSet();


  /** 
   * <em>trading_report_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingReportDivIsModified();


  /** 
   * <em>inv_trast_ope_report</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInvTrastOpeReport();


  /** 
   * <em>inv_trast_ope_report</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInvTrastOpeReportIsSet();


  /** 
   * <em>inv_trast_ope_report</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInvTrastOpeReportIsModified();


  /** 
   * <em>tax_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTaxType();


  /** 
   * <em>tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxTypeIsSet();


  /** 
   * <em>tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxTypeIsModified();


  /** 
   * <em>margin_tax_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginTaxType();


  /** 
   * <em>margin_tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxTypeIsSet();


  /** 
   * <em>margin_tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxTypeIsModified();


  /** 
   * <em>equity_sp_acc_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getEquitySpAccOpenDate();


  /** 
   * <em>equity_sp_acc_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquitySpAccOpenDateIsSet();


  /** 
   * <em>equity_sp_acc_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquitySpAccOpenDateIsModified();


  /** 
   * <em>margin_sp_acc_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getMarginSpAccOpenDate();


  /** 
   * <em>margin_sp_acc_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSpAccOpenDateIsSet();


  /** 
   * <em>margin_sp_acc_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSpAccOpenDateIsModified();


  /** 
   * <em>tax_type_last</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTaxTypeLast();


  /** 
   * <em>tax_type_last</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxTypeLastIsSet();


  /** 
   * <em>tax_type_last</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxTypeLastIsModified();


  /** 
   * <em>margin_tax_type_last</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginTaxTypeLast();


  /** 
   * <em>margin_tax_type_last</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxTypeLastIsSet();


  /** 
   * <em>margin_tax_type_last</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxTypeLastIsModified();


  /** 
   * <em>tax_type_next</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTaxTypeNext();


  /** 
   * <em>tax_type_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxTypeNextIsSet();


  /** 
   * <em>tax_type_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxTypeNextIsModified();


  /** 
   * <em>margin_tax_type_next</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginTaxTypeNext();


  /** 
   * <em>margin_tax_type_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxTypeNextIsSet();


  /** 
   * <em>margin_tax_type_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxTypeNextIsModified();


  /** 
   * <em>fluct_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getFluctDate();


  /** 
   * <em>fluct_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFluctDateIsSet();


  /** 
   * <em>fluct_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFluctDateIsModified();


  /** 
   * <em>margin_fluct_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getMarginFluctDate();


  /** 
   * <em>margin_fluct_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginFluctDateIsSet();


  /** 
   * <em>margin_fluct_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginFluctDateIsModified();


  /** 
   * <em>local_tax_div_last</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLocalTaxDivLast();


  /** 
   * <em>local_tax_div_last</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLocalTaxDivLastIsSet();


  /** 
   * <em>local_tax_div_last</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLocalTaxDivLastIsModified();


  /** 
   * <em>local_tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLocalTaxDiv();


  /** 
   * <em>local_tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLocalTaxDivIsSet();


  /** 
   * <em>local_tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLocalTaxDivIsModified();


  /** 
   * <em>local_tax_div_next</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLocalTaxDivNext();


  /** 
   * <em>local_tax_div_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLocalTaxDivNextIsSet();


  /** 
   * <em>local_tax_div_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLocalTaxDivNextIsModified();


  /** 
   * <em>qualified_inst_investor_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getQualifiedInstInvestorDiv();


  /** 
   * <em>qualified_inst_investor_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQualifiedInstInvestorDivIsSet();


  /** 
   * <em>qualified_inst_investor_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQualifiedInstInvestorDivIsModified();


  /** 
   * <em>quoto_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getQuotoType();


  /** 
   * <em>quoto_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuotoTypeIsSet();


  /** 
   * <em>quoto_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuotoTypeIsModified();


  /** 
   * <em>sp_acc_abolish_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getSpAccAbolishDate();


  /** 
   * <em>sp_acc_abolish_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpAccAbolishDateIsSet();


  /** 
   * <em>sp_acc_abolish_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpAccAbolishDateIsModified();


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
   * <em>reserve_area</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReserveArea();


  /** 
   * <em>reserve_area</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReserveAreaIsSet();


  /** 
   * <em>reserve_area</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReserveAreaIsModified();


  /** 
   * <em>web3_encrypted_password</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getWeb3EncryptedPassword();


  /** 
   * <em>web3_encrypted_password</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWeb3EncryptedPasswordIsSet();


  /** 
   * <em>web3_encrypted_password</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWeb3EncryptedPasswordIsModified();


  /** 
   * <em>xtrade_encrypted_password</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getXtradeEncryptedPassword();


  /** 
   * <em>xtrade_encrypted_password</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getXtradeEncryptedPasswordIsSet();


  /** 
   * <em>xtrade_encrypted_password</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getXtradeEncryptedPasswordIsModified();


  /** 
   * <em>broadcast_law</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBroadcastLaw();


  /** 
   * <em>broadcast_law</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBroadcastLawIsSet();


  /** 
   * <em>broadcast_law</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBroadcastLawIsModified();


  /** 
   * <em>aviation_law</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAviationLaw();


  /** 
   * <em>aviation_law</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAviationLawIsSet();


  /** 
   * <em>aviation_law</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAviationLawIsModified();


  /** 
   * <em>ntt_law</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNttLaw();


  /** 
   * <em>ntt_law</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNttLawIsSet();


  /** 
   * <em>ntt_law</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNttLawIsModified();


  /** 
   * <em>dividend_trans_designate</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDividendTransDesignate();


  /** 
   * <em>dividend_trans_designate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDividendTransDesignateIsSet();


  /** 
   * <em>dividend_trans_designate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDividendTransDesignateIsModified();


  /** 
   * <em>standing_proxy</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStandingProxy();


  /** 
   * <em>standing_proxy</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStandingProxyIsSet();


  /** 
   * <em>standing_proxy</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStandingProxyIsModified();


  /** 
   * <em>statutory_agent</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStatutoryAgent();


  /** 
   * <em>statutory_agent</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatutoryAgentIsSet();


  /** 
   * <em>statutory_agent</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatutoryAgentIsModified();


  /** 
   * <em>affiliate_account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAffiliateAccountCode();


  /** 
   * <em>affiliate_account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAffiliateAccountCodeIsSet();


  /** 
   * <em>affiliate_account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAffiliateAccountCodeIsModified();


  /** 
   * <em>agency_notify_cmp_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgencyNotifyCmpDiv();


  /** 
   * <em>agency_notify_cmp_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyNotifyCmpDivIsSet();


  /** 
   * <em>agency_notify_cmp_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyNotifyCmpDivIsModified();


}
@
