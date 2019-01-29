head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.42.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorUploadTempRow.java;


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
 * AdministratorUploadTempRowインタフェースは変更不可でリードオンリーである<em>administrator_upload_temp</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link AdministratorUploadTempRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AdministratorUploadTempPK 
 */
public interface AdministratorUploadTempRow extends Row {


  /** 
   * この{@@link AdministratorUploadTempRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "administrator_upload_temp", "session" );


  /** 
   * <em>administrator_upload_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAdministratorUploadId();


  /** 
   * <em>administrator_upload_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdministratorUploadIdIsSet();


  /** 
   * <em>administrator_upload_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdministratorUploadIdIsModified();


  /** 
   * <em>line_number</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLineNumber();


  /** 
   * <em>line_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLineNumberIsSet();


  /** 
   * <em>line_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLineNumberIsModified();


  /** 
   * <em>csv_line_value</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCsvLineValue();


  /** 
   * <em>csv_line_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCsvLineValueIsSet();


  /** 
   * <em>csv_line_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCsvLineValueIsModified();


  /** 
   * <em>update_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getUpdateTimestamp();


  /** 
   * <em>update_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUpdateTimestampIsSet();


  /** 
   * <em>update_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUpdateTimestampIsModified();


}
@
