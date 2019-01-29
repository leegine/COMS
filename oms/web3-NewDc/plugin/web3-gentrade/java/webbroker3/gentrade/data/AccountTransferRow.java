head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AccountTransferRow.java;


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
 * AccountTransferRowインタフェースは変更不可でリードオンリーである<em>account_transfer</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link AccountTransferRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccountTransferPK 
 */
public interface AccountTransferRow extends Row {


  /** 
   * この{@@link AccountTransferRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "account_transfer", "session" );


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
   * <em>work_day</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getWorkDay();


  /** 
   * <em>work_day</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWorkDayIsSet();


  /** 
   * <em>work_day</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWorkDayIsModified();


  /** 
   * <em>rec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRecDiv();


  /** 
   * <em>rec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecDivIsSet();


  /** 
   * <em>rec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecDivIsModified();


  /** 
   * <em>branch_code_old</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBranchCodeOld();


  /** 
   * <em>branch_code_old</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeOldIsSet();


  /** 
   * <em>branch_code_old</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeOldIsModified();


  /** 
   * <em>account_code_old</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountCodeOld();


  /** 
   * <em>account_code_old</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeOldIsSet();


  /** 
   * <em>account_code_old</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeOldIsModified();


  /** 
   * <em>sonar_trader_code_old</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarTraderCodeOld();


  /** 
   * <em>sonar_trader_code_old</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeOldIsSet();


  /** 
   * <em>sonar_trader_code_old</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeOldIsModified();


  /** 
   * <em>branch_code_new</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBranchCodeNew();


  /** 
   * <em>branch_code_new</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeNewIsSet();


  /** 
   * <em>branch_code_new</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeNewIsModified();


  /** 
   * <em>account_code_new</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountCodeNew();


  /** 
   * <em>account_code_new</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeNewIsSet();


  /** 
   * <em>account_code_new</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeNewIsModified();


  /** 
   * <em>sonar_trader_code_new</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarTraderCodeNew();


  /** 
   * <em>sonar_trader_code_new</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeNewIsSet();


  /** 
   * <em>sonar_trader_code_new</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeNewIsModified();


  /** 
   * <em>customer_trader_code_old</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCustomerTraderCodeOld();


  /** 
   * <em>customer_trader_code_old</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustomerTraderCodeOldIsSet();


  /** 
   * <em>customer_trader_code_old</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustomerTraderCodeOldIsModified();


  /** 
   * <em>customer_trader_code_new</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCustomerTraderCodeNew();


  /** 
   * <em>customer_trader_code_new</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustomerTraderCodeNewIsSet();


  /** 
   * <em>customer_trader_code_new</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustomerTraderCodeNewIsModified();


  /** 
   * <em>del_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDelDiv();


  /** 
   * <em>del_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDelDivIsSet();


  /** 
   * <em>del_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDelDivIsModified();


  /** 
   * <em>inherit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInheritDiv();


  /** 
   * <em>inherit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInheritDivIsSet();


  /** 
   * <em>inherit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInheritDivIsModified();


  /** 
   * <em>transfer_tbl</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTransferTbl();


  /** 
   * <em>transfer_tbl</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferTblIsSet();


  /** 
   * <em>transfer_tbl</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferTblIsModified();


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
   * <em>pro_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProDiv();


  /** 
   * <em>pro_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProDivIsSet();


  /** 
   * <em>pro_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProDivIsModified();


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
