head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.49.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5cc4d8b0586158f;
filename	EleDeliveryManagementRow.java;


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
 * EleDeliveryManagementRowインタフェースは変更不可でリードオンリーである<em>ele_delivery_management</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link EleDeliveryManagementRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EleDeliveryManagementPK 
 */
public interface EleDeliveryManagementRow extends Row {


  /** 
   * この{@@link EleDeliveryManagementRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "ele_delivery_management", "account" );


  /** 
   * <em>account_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public long getAccountId();


  /** 
   * <em>account_id</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountIdIsSet();


  /** 
   * <em>institution_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInstitutionCode();


  /** 
   * <em>institution_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsSet();


  /** 
   * <em>branch_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBranchCode();


  /** 
   * <em>branch_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeIsSet();


  /** 
   * <em>account_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountCode();


  /** 
   * <em>account_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeIsSet();


  /** 
   * <em>ele_del_regi_flag</em>コラムの値を取得します。
   * @@return intの値 
   */
  public int getEleDelRegiFlag();


  /** 
   * <em>ele_del_regi_flag</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEleDelRegiFlagIsSet();


  /** 
   * <em>ele_del_regi_upd_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getEleDelRegiUpdDate();


  /** 
   * <em>trading_report_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradingReportDiv();


  /** 
   * <em>trading_report_reg_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradingReportRegDiv();


  /** 
   * <em>trading_report_div_upd_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getTradingReportDivUpdDate();


  /** 
   * <em>position_report_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPositionReportDiv();


  /** 
   * <em>position_report_reg_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPositionReportRegDiv();


  /** 
   * <em>position_report_div_upd_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getPositionReportDivUpdDate();


  /** 
   * <em>ope_report_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOpeReportDiv();


  /** 
   * <em>ope_report_reg_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOpeReportRegDiv();


  /** 
   * <em>ope_report_div_upd_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getOpeReportDivUpdDate();


  /** 
   * <em>ord_rul_report_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrdRulReportDiv();


  /** 
   * <em>ord_rul_rep_reg_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrdRulRepRegDiv();


  /** 
   * <em>ord_rul_report_div_upd_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getOrdRulReportDivUpdDate();


  /** 
   * <em>report_div1</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReportDiv1();


  /** 
   * <em>report_reg_div1</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReportRegDiv1();


  /** 
   * <em>report_div_upd_date1</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getReportDivUpdDate1();


  /** 
   * <em>report_div2</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReportDiv2();


  /** 
   * <em>report_reg_div2</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReportRegDiv2();


  /** 
   * <em>report_div_upd_date2</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getReportDivUpdDate2();


  /** 
   * <em>report_div3</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReportDiv3();


  /** 
   * <em>report_reg_div3</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReportRegDiv3();


  /** 
   * <em>report_div_upd_date3</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getReportDivUpdDate3();


  /** 
   * <em>report_div4</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReportDiv4();


  /** 
   * <em>report_reg_div4</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReportRegDiv4();


  /** 
   * <em>report_div_upd_date4</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getReportDivUpdDate4();


  /** 
   * <em>report_div5</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReportDiv5();


  /** 
   * <em>report_reg_div5</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReportRegDiv5();


  /** 
   * <em>report_div_upd_date5</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getReportDivUpdDate5();


  /** 
   * <em>last_updater</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLastUpdater();


  /** 
   * <em>created_timestamp</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getCreatedTimestamp();


  /** 
   * <em>created_timestamp</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatedTimestampIsSet();


  /** 
   * <em>last_updated_timestamp</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getLastUpdatedTimestamp();


  /** 
   * <em>last_updated_timestamp</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdatedTimestampIsSet();


}
@
