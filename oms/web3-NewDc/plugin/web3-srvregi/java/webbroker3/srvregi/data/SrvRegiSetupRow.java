head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiSetupRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * SrvRegiSetupRowインタフェースは変更不可でリードオンリーである<em>srv_regi_setup</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link SrvRegiSetupRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SrvRegiSetupPK 
 */
public interface SrvRegiSetupRow extends Row {


  /** 
   * この{@@link SrvRegiSetupRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "srv_regi_setup", "master" );


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
   * <em>srv_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSrvDiv();


  /** 
   * <em>srv_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSrvDivIsSet();


  /** 
   * <em>srv_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSrvDivIsModified();


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
   * <em>lot_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLotDiv();


  /** 
   * <em>lot_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLotDivIsSet();


  /** 
   * <em>lot_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLotDivIsModified();


  /** 
   * <em>trial_period_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTrialPeriodDiv();


  /** 
   * <em>trial_period_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrialPeriodDivIsSet();


  /** 
   * <em>trial_period_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrialPeriodDivIsModified();


  /** 
   * <em>trial_period</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getTrialPeriod();


  /** 
   * <em>trial_period</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTrialPeriodIsNull();


  /** 
   * <em>trial_period</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrialPeriodIsSet();


  /** 
   * <em>trial_period</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrialPeriodIsModified();


  /** 
   * <em>appli_date_from</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getAppliDateFrom();


  /** 
   * <em>appli_date_from</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAppliDateFromIsNull();


  /** 
   * <em>appli_date_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAppliDateFromIsSet();


  /** 
   * <em>appli_date_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAppliDateFromIsModified();


  /** 
   * <em>appli_date_to</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getAppliDateTo();


  /** 
   * <em>appli_date_to</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAppliDateToIsNull();


  /** 
   * <em>appli_date_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAppliDateToIsSet();


  /** 
   * <em>appli_date_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAppliDateToIsModified();


  /** 
   * <em>srv_contents</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSrvContents();


  /** 
   * <em>srv_contents</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSrvContentsIsSet();


  /** 
   * <em>srv_contents</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSrvContentsIsModified();


  /** 
   * <em>srv_explan_url</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSrvExplanUrl();


  /** 
   * <em>srv_explan_url</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSrvExplanUrlIsSet();


  /** 
   * <em>srv_explan_url</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSrvExplanUrlIsModified();


  /** 
   * <em>start_mail_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStartMailDiv();


  /** 
   * <em>start_mail_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStartMailDivIsSet();


  /** 
   * <em>start_mail_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStartMailDivIsModified();


  /** 
   * <em>end_mail_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEndMailDiv();


  /** 
   * <em>end_mail_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEndMailDivIsSet();


  /** 
   * <em>end_mail_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEndMailDivIsModified();


  /** 
   * <em>send_mail_interval</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSendMailInterval();


  /** 
   * <em>send_mail_interval</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSendMailIntervalIsNull();


  /** 
   * <em>send_mail_interval</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendMailIntervalIsSet();


  /** 
   * <em>send_mail_interval</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendMailIntervalIsModified();


  /** 
   * <em>electric_issue_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getElectricIssueDiv();


  /** 
   * <em>electric_issue_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getElectricIssueDivIsSet();


  /** 
   * <em>electric_issue_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getElectricIssueDivIsModified();


  /** 
   * <em>min_comm_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMinCommAmt();


  /** 
   * <em>min_comm_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMinCommAmtIsSet();


  /** 
   * <em>min_comm_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMinCommAmtIsModified();


  /** 
   * <em>supply_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSupplyDiv();


  /** 
   * <em>supply_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSupplyDivIsSet();


  /** 
   * <em>supply_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSupplyDivIsModified();


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLastUpdater();


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsSet();


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsModified();


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
   * <em>free_coverage_length</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getFreeCoverageLength();


  /** 
   * <em>free_coverage_length</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFreeCoverageLengthIsNull();


  /** 
   * <em>free_coverage_length</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFreeCoverageLengthIsSet();


  /** 
   * <em>free_coverage_length</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFreeCoverageLengthIsModified();


}
@
