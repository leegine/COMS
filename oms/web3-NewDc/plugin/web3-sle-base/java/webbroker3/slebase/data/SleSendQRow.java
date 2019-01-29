head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleSendQRow.java;


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
 * SleSendQRowインタフェースは変更不可でリードオンリーである<em>sle_send_q</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link SleSendQRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleSendQPK 
 */
public interface SleSendQRow extends Row {


  /** 
   * この{@@link SleSendQRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "sle_send_q", "session" );


  /** 
   * <em>queue_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public long getQueueId();


  /** 
   * <em>queue_id</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQueueIdIsSet();


  /** 
   * <em>product_type</em>コラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType();


  /** 
   * <em>product_type</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductTypeIsSet();


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
   * <em>broker_name</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBrokerName();


  /** 
   * <em>broker_name</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBrokerNameIsSet();


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
   * <em>product_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductCode();


  /** 
   * <em>order_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public long getOrderId();


  /** 
   * <em>order_id</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getOrderIdIsNull();


  /** 
   * <em>order_unit_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public long getOrderUnitId();


  /** 
   * <em>order_unit_id</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getOrderUnitIdIsNull();


  /** 
   * <em>biz_date</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBizDate();


  /** 
   * <em>biz_date</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBizDateIsSet();


  /** 
   * <em>op_type</em>コラムの値を取得します。
   * @@return webbroker3.slebase.enums.SleSendqOpTypeEnumの値 
   */
  public webbroker3.slebase.enums.SleSendqOpTypeEnum getOpType();


  /** 
   * <em>op_type</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpTypeIsSet();


  /** 
   * <em>order_type</em>コラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum getOrderType();


  /** 
   * <em>order_type</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderTypeIsSet();


  /** 
   * <em>limit_price</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLimitPrice();


  /** 
   * <em>limit_price</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getLimitPriceIsNull();


  /** 
   * <em>quantity</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getQuantity();


  /** 
   * <em>quantity</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getQuantityIsNull();


  /** 
   * <em>change_quantity</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getChangeQuantity();


  /** 
   * <em>change_quantity</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getChangeQuantityIsNull();


  /** 
   * <em>change_limit_price</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getChangeLimitPrice();


  /** 
   * <em>change_limit_price</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getChangeLimitPriceIsNull();


  /** 
   * <em>already_execd_quantity</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAlreadyExecdQuantity();


  /** 
   * <em>already_execd_quantity</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getAlreadyExecdQuantityIsNull();


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
   * <em>sub_account_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public long getSubAccountId();


  /** 
   * <em>sub_account_id</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubAccountIdIsSet();


  /** 
   * <em>status</em>コラムの値を取得します。
   * @@return webbroker3.slebase.enums.SleSendqProcStatusEnumの値 
   */
  public webbroker3.slebase.enums.SleSendqProcStatusEnum getStatus();


  /** 
   * <em>status</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsSet();


  /** 
   * <em>confirmed_by_sle_rcvd_q</em>コラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getConfirmedBySleRcvdQ();


  /** 
   * <em>confirmed_by_sle_rcvd_q</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmedBySleRcvdQIsSet();


  /** 
   * <em>order_emp_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderEmpCode();


  /** 
   * <em>order_emp_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderEmpCodeIsSet();


  /** 
   * <em>order_request_number</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderRequestNumber();


  /** 
   * <em>order_request_number</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRequestNumberIsSet();


  /** 
   * <em>send_process_date_time</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getSendProcessDateTime();


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
