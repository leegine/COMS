head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleConnStatusChangesRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.slebase.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/** 
 * SleConnStatusChangesRowインタフェースは変更不可でリードオンリーである<em>sle_conn_status_changes</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link SleConnStatusChangesRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleConnStatusChangesPK 
 */
public interface SleConnStatusChangesRow extends Row {


  /** 
   * この{@@link SleConnStatusChangesRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "sle_conn_status_changes", "session" );


  /** 
   * <em>que_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public long getQueId();


  /** 
   * <em>que_id</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQueIdIsSet();


  /** 
   * <em>market_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarketCode();


  /** 
   * <em>market_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketCodeIsSet();


  /** 
   * <em>new_status</em>コラムの値を取得します。
   * @@return webbroker3.slebase.enums.SleConnectionStatusEnumの値 
   */
  public webbroker3.slebase.enums.SleConnectionStatusEnum getNewStatus();


  /** 
   * <em>new_status</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewStatusIsSet();


  /** 
   * <em>event_acked_div</em>コラムの値を取得します。
   * @@return intの値 
   */
  public int getEventAckedDiv();


  /** 
   * <em>event_acked_div</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEventAckedDivIsSet();


  /** 
   * <em>sle_conn_div</em>コラムの値を取得します。
   * @@return intの値 
   */
  public int getSleConnDiv();


  /** 
   * <em>sle_conn_div</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSleConnDivIsSet();


  /** 
   * <em>proc_status</em>コラムの値を取得します。
   * @@return intの値 
   */
  public int getProcStatus();


  /** 
   * <em>proc_status</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProcStatusIsSet();


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
