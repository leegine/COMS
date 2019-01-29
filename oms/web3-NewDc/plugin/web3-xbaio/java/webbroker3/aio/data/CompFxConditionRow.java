head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.52.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	CompFxConditionRow.java;


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
 * CompFxConditionRowインタフェースは変更不可でリードオンリーである<em>comp_fx_condition</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link CompFxConditionRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CompFxConditionPK 
 */
public interface CompFxConditionRow extends Row {


  /** 
   * この{@@link CompFxConditionRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "comp_fx_condition", "master" );


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
   * <em>fx_system_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFxSystemCode();


  /** 
   * <em>fx_system_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxSystemCodeIsSet();


  /** 
   * <em>fx_system_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxSystemCodeIsModified();


  /** 
   * <em>group_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGroupName();


  /** 
   * <em>group_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGroupNameIsSet();


  /** 
   * <em>group_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGroupNameIsModified();


  /** 
   * <em>url</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getUrl();


  /** 
   * <em>url</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUrlIsSet();


  /** 
   * <em>url</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUrlIsModified();


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
   * <em>fx_head_of_login_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFxHeadOfLoginId();


  /** 
   * <em>fx_head_of_login_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxHeadOfLoginIdIsSet();


  /** 
   * <em>fx_head_of_login_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxHeadOfLoginIdIsModified();


  /** 
   * <em>single_sign_on_url</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSingleSignOnUrl();


  /** 
   * <em>single_sign_on_url</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSingleSignOnUrlIsSet();


  /** 
   * <em>single_sign_on_url</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSingleSignOnUrlIsModified();


  /** 
   * <em>valid_term</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getValidTerm();


  /** 
   * <em>valid_term</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getValidTermIsSet();


  /** 
   * <em>valid_term</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getValidTermIsModified();


  /** 
   * <em>fx_system_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFxSystemDiv();


  /** 
   * <em>fx_system_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxSystemDivIsSet();


  /** 
   * <em>fx_system_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxSystemDivIsModified();


  /** 
   * <em>ext_connect_system_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtConnectSystemCode();


  /** 
   * <em>ext_connect_system_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtConnectSystemCodeIsSet();


  /** 
   * <em>ext_connect_system_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtConnectSystemCodeIsModified();


  /** 
   * <em>trading_time_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradingTimeType();


  /** 
   * <em>trading_time_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingTimeTypeIsSet();


  /** 
   * <em>trading_time_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingTimeTypeIsModified();


  /** 
   * <em>online_acc_open</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOnlineAccOpen();


  /** 
   * <em>online_acc_open</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOnlineAccOpenIsSet();


  /** 
   * <em>online_acc_open</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOnlineAccOpenIsModified();


  /** 
   * <em>soap_connect_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSoapConnectDiv();


  /** 
   * <em>soap_connect_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSoapConnectDivIsSet();


  /** 
   * <em>soap_connect_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSoapConnectDivIsModified();


  /** 
   * <em>acc_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccType();


  /** 
   * <em>acc_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccTypeIsSet();


  /** 
   * <em>acc_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccTypeIsModified();


  /** 
   * <em>mrf_allow_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMrfAllowDiv();


  /** 
   * <em>mrf_allow_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMrfAllowDivIsSet();


  /** 
   * <em>mrf_allow_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMrfAllowDivIsModified();


  /** 
   * <em>acc_open_real_update</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccOpenRealUpdate();


  /** 
   * <em>acc_open_real_update</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccOpenRealUpdateIsSet();


  /** 
   * <em>acc_open_real_update</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccOpenRealUpdateIsModified();


  /** 
   * <em>question_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getQuestionCheckDiv();


  /** 
   * <em>question_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuestionCheckDivIsSet();


  /** 
   * <em>question_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuestionCheckDivIsModified();


  /** 
   * <em>fx_system_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getFxSystemId();


  /** 
   * <em>fx_system_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxSystemIdIsSet();


  /** 
   * <em>fx_system_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxSystemIdIsModified();


  /** 
   * <em>get_transferable_amt_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGetTransferableAmtDiv();


  /** 
   * <em>get_transferable_amt_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGetTransferableAmtDivIsSet();


  /** 
   * <em>get_transferable_amt_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGetTransferableAmtDivIsModified();


}
@
