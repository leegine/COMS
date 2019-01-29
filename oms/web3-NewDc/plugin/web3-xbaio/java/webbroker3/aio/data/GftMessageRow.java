head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.44.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	GftMessageRow.java;


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
 * GftMessageRowインタフェースは変更不可でリードオンリーである<em>gft_message</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link GftMessageRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see GftMessagePK 
 */
public interface GftMessageRow extends Row {


  /** 
   * この{@@link GftMessageRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "gft_message", "session" );


  /** 
   * <em>message_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMessageDiv();


  /** 
   * <em>message_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMessageDivIsSet();


  /** 
   * <em>message_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMessageDivIsModified();


  /** 
   * <em>dir_send_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirSendTime();


  /** 
   * <em>dir_send_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirSendTimeIsSet();


  /** 
   * <em>dir_send_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirSendTimeIsModified();


  /** 
   * <em>operation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOperation();


  /** 
   * <em>operation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOperationIsSet();


  /** 
   * <em>operation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOperationIsModified();


  /** 
   * <em>account</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccount();


  /** 
   * <em>account</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountIsSet();


  /** 
   * <em>account</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountIsModified();


  /** 
   * <em>email</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEmail();


  /** 
   * <em>email</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailIsSet();


  /** 
   * <em>email</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailIsModified();


  /** 
   * <em>gft_link_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGftLink1();


  /** 
   * <em>gft_link_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGftLink1IsSet();


  /** 
   * <em>gft_link_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGftLink1IsModified();


  /** 
   * <em>gft_link_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGftLink2();


  /** 
   * <em>gft_link_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGftLink2IsSet();


  /** 
   * <em>gft_link_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGftLink2IsModified();


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
   * <em>amount</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAmount();


  /** 
   * <em>amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountIsSet();


  /** 
   * <em>amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountIsModified();


  /** 
   * <em>wolf_session_key</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getWolfSessionKey();


  /** 
   * <em>wolf_session_key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWolfSessionKeyIsSet();


  /** 
   * <em>wolf_session_key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWolfSessionKeyIsModified();


  /** 
   * <em>aa_aid</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAaAid();


  /** 
   * <em>aa_aid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAaAidIsSet();


  /** 
   * <em>aa_aid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAaAidIsModified();


  /** 
   * <em>aa_rsid</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAaRsid();


  /** 
   * <em>aa_rsid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAaRsidIsSet();


  /** 
   * <em>aa_rsid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAaRsidIsModified();


  /** 
   * <em>ssid</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSsid();


  /** 
   * <em>ssid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSsidIsSet();


  /** 
   * <em>ssid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSsidIsModified();


  /** 
   * <em>cpy</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCpy();


  /** 
   * <em>cpy</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCpyIsSet();


  /** 
   * <em>cpy</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCpyIsModified();


  /** 
   * <em>brn</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBrn();


  /** 
   * <em>brn</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBrnIsSet();


  /** 
   * <em>brn</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBrnIsModified();


  /** 
   * <em>acc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAcc();


  /** 
   * <em>acc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccIsSet();


  /** 
   * <em>acc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccIsModified();


  /** 
   * <em>req</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReq();


  /** 
   * <em>req</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReqIsSet();


  /** 
   * <em>req</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReqIsModified();


  /** 
   * <em>result_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getResultCode();


  /** 
   * <em>result_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getResultCodeIsSet();


  /** 
   * <em>result_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getResultCodeIsModified();


  /** 
   * <em>gft_send_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGftSendTime();


  /** 
   * <em>gft_send_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGftSendTimeIsSet();


  /** 
   * <em>gft_send_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGftSendTimeIsModified();


  /** 
   * <em>gft_ac1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGftAc1();


  /** 
   * <em>gft_ac1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGftAc1IsSet();


  /** 
   * <em>gft_ac1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGftAc1IsModified();


  /** 
   * <em>gft_ac2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGftAc2();


  /** 
   * <em>gft_ac2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGftAc2IsSet();


  /** 
   * <em>gft_ac2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGftAc2IsModified();


  /** 
   * <em>last_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLastName();


  /** 
   * <em>last_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastNameIsSet();


  /** 
   * <em>last_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastNameIsModified();


  /** 
   * <em>first_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFirstName();


  /** 
   * <em>first_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstNameIsSet();


  /** 
   * <em>first_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstNameIsModified();


  /** 
   * <em>hash_key</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHashKey();


  /** 
   * <em>hash_key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHashKeyIsSet();


  /** 
   * <em>hash_key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHashKeyIsModified();


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
   * <em>delivery_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDeliveryDate();


  /** 
   * <em>delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveryDateIsSet();


  /** 
   * <em>delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveryDateIsModified();


  /** 
   * <em>amount2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAmount2();


  /** 
   * <em>amount2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmount2IsSet();


  /** 
   * <em>amount2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmount2IsModified();


  /** 
   * <em>address1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddress1();


  /** 
   * <em>address1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddress1IsSet();


  /** 
   * <em>address1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddress1IsModified();


  /** 
   * <em>address2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddress2();


  /** 
   * <em>address2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddress2IsSet();


  /** 
   * <em>address2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddress2IsModified();


  /** 
   * <em>address3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddress3();


  /** 
   * <em>address3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddress3IsSet();


  /** 
   * <em>address3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddress3IsModified();


}
@
