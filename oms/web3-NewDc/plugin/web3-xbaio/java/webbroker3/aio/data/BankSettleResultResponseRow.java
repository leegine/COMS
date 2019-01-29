head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.45.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankSettleResultResponseRow.java;


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
 * BankSettleResultResponseRowインタフェースは変更不可でリードオンリーである<em>bank_settle_result_response</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link BankSettleResultResponseRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BankSettleResultResponsePK 
 */
public interface BankSettleResultResponseRow extends Row {


  /** 
   * この{@@link BankSettleResultResponseRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "bank_settle_result_response", "session" );


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCreatedTimestamp();


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
   * <em>protocol_version</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProtocolVersion();


  /** 
   * <em>protocol_version</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProtocolVersionIsSet();


  /** 
   * <em>protocol_version</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProtocolVersionIsModified();


  /** 
   * <em>linked_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLinked1();


  /** 
   * <em>linked_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLinked1IsSet();


  /** 
   * <em>linked_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLinked1IsModified();


  /** 
   * <em>shop_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShopId();


  /** 
   * <em>shop_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShopIdIsSet();


  /** 
   * <em>shop_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShopIdIsModified();


  /** 
   * <em>order_date_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderDateTime();


  /** 
   * <em>order_date_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderDateTimeIsSet();


  /** 
   * <em>order_date_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderDateTimeIsModified();


  /** 
   * <em>center_pay_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCenterPayId();


  /** 
   * <em>center_pay_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCenterPayIdIsSet();


  /** 
   * <em>center_pay_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCenterPayIdIsModified();


  /** 
   * <em>comondebi_sales_slip</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getComondebiSalesSlip();


  /** 
   * <em>comondebi_sales_slip</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getComondebiSalesSlipIsSet();


  /** 
   * <em>comondebi_sales_slip</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getComondebiSalesSlipIsModified();


  /** 
   * <em>comondebi_auth_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getComondebiAuthDate();


  /** 
   * <em>comondebi_auth_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getComondebiAuthDateIsSet();


  /** 
   * <em>comondebi_auth_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getComondebiAuthDateIsModified();


  /** 
   * <em>pay_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPayStatus();


  /** 
   * <em>pay_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPayStatusIsSet();


  /** 
   * <em>pay_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPayStatusIsModified();


  /** 
   * <em>comondebi_authres_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getComondebiAuthresCode();


  /** 
   * <em>comondebi_authres_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getComondebiAuthresCodeIsSet();


  /** 
   * <em>comondebi_authres_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getComondebiAuthresCodeIsModified();


  /** 
   * <em>comondebi_capture_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getComondebiCaptureDate();


  /** 
   * <em>comondebi_capture_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getComondebiCaptureDateIsSet();


  /** 
   * <em>comondebi_capture_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getComondebiCaptureDateIsModified();


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
   * <em>pay_scheme_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPaySchemeId();


  /** 
   * <em>pay_scheme_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaySchemeIdIsSet();


  /** 
   * <em>pay_scheme_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaySchemeIdIsModified();


  /** 
   * <em>access_key</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccessKey();


  /** 
   * <em>access_key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccessKeyIsSet();


  /** 
   * <em>access_key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccessKeyIsModified();


  /** 
   * <em>description</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDescription();


  /** 
   * <em>description</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDescriptionIsSet();


  /** 
   * <em>description</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDescriptionIsModified();


  /** 
   * <em>rowid</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRowid();


  /** 
   * <em>rowid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRowidIsSet();


  /** 
   * <em>rowid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRowidIsModified();


}
@
