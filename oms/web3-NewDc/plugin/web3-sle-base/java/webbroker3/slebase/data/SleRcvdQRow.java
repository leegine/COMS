head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleRcvdQRow.java;


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
 * SleRcvdQRowインタフェースは変更不可でリードオンリーである<em>sle_rcvd_q</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link SleRcvdQRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleRcvdQPK 
 */
public interface SleRcvdQRow extends Row {


  /** 
   * この{@@link SleRcvdQRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "sle_rcvd_q", "session" );


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
   * <em>xblocks_product_type</em>コラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getXblocksProductType();


  /** 
   * <em>xblocks_product_type</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getXblocksProductTypeIsSet();


  /** 
   * <em>replies_type</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRepliesType();


  /** 
   * <em>replies_number</em>コラムの値を取得します。
   * @@return longの値 
   */
  public long getRepliesNumber();


  /** 
   * <em>replies_number</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getRepliesNumberIsNull();


  /** 
   * <em>replies_index</em>コラムの値を取得します。
   * @@return longの値 
   */
  public long getRepliesIndex();


  /** 
   * <em>replies_index</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getRepliesIndexIsNull();


  /** 
   * <em>sub_status</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSubStatus();


  /** 
   * <em>internal_ref</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInternalRef();


  /** 
   * <em>exchange_no</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExchangeNo();


  /** 
   * <em>trade_number</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradeNumber();


  /** 
   * <em>gl_id</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGlId();


  /** 
   * <em>stock_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStockCode();


  /** 
   * <em>side</em>コラムの値を取得します。
   * @@return intの値 
   */
  public int getSide();


  /** 
   * <em>side</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getSideIsNull();


  /** 
   * <em>modality</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getModality();


  /** 
   * <em>price</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPrice();


  /** 
   * <em>price</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getPriceIsNull();


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
   * <em>trading_phase</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradingPhase();


  /** 
   * <em>exec_price</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getExecPrice();


  /** 
   * <em>exec_price</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getExecPriceIsNull();


  /** 
   * <em>avg_price</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAvgPrice();


  /** 
   * <em>avg_price</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getAvgPriceIsNull();


  /** 
   * <em>exec_qty</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getExecQty();


  /** 
   * <em>exec_qty</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getExecQtyIsNull();


  /** 
   * <em>remaining_qty</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getRemainingQty();


  /** 
   * <em>remaining_qty</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getRemainingQtyIsNull();


  /** 
   * <em>number_of_trades</em>コラムの値を取得します。
   * @@return longの値 
   */
  public long getNumberOfTrades();


  /** 
   * <em>number_of_trades</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getNumberOfTradesIsNull();


  /** 
   * <em>order_time</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderTime();


  /** 
   * <em>trade_booking_time</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradeBookingTime();


  /** 
   * <em>exec_timestamp</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getExecTimestamp();


  /** 
   * <em>order_emp_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderEmpCode();


  /** 
   * <em>execution_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getExecutionDate();


  /** 
   * <em>f_delivery_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getFDeliveryDate();


  /** 
   * <em>fx_rate</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getFxRate();


  /** 
   * <em>fx_rate</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getFxRateIsNull();


  /** 
   * <em>exec_serial_no</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExecSerialNo();


  /** 
   * <em>route_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRouteDiv();


  /** 
   * <em>ack_type</em>コラムの値を取得します。
   * @@return intの値 
   */
  public int getAckType();


  /** 
   * <em>ack_type</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getAckTypeIsNull();


  /** 
   * <em>ackd_command</em>コラムの値を取得します。
   * @@return intの値 
   */
  public int getAckdCommand();


  /** 
   * <em>ackd_command</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getAckdCommandIsNull();


  /** 
   * <em>old_qty</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOldQty();


  /** 
   * <em>old_qty</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getOldQtyIsNull();


  /** 
   * <em>old_price</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOldPrice();


  /** 
   * <em>old_price</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getOldPriceIsNull();


  /** 
   * <em>cancelled_qty</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCancelledQty();


  /** 
   * <em>cancelled_qty</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getCancelledQtyIsNull();


  /** 
   * <em>trigger_param</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTriggerParam();


  /** 
   * <em>reject_cmd_type</em>コラムの値を取得します。
   * @@return intの値 
   */
  public int getRejectCmdType();


  /** 
   * <em>reject_cmd_type</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getRejectCmdTypeIsNull();


  /** 
   * <em>reject_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRejectCode();


  /** 
   * <em>reject_time</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRejectTime();


  /** 
   * <em>exchange_msg_type</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExchangeMsgType();


  /** 
   * <em>exchange_msg_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExchangeMsgCode();


  /** 
   * <em>user_number</em>コラムの値を取得します。
   * @@return intの値 
   */
  public int getUserNumber();


  /** 
   * <em>user_number</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public  boolean  getUserNumberIsNull();


  /** 
   * <em>memo</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMemo();


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
   * <em>accept_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAcceptDiv();


  /** 
   * <em>institution_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInstitutionCode();


  /** 
   * <em>branch_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBranchCode();


  /** 
   * <em>order_request_number</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderRequestNumber();


  /** 
   * <em>elimination_message</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEliminationMessage();


  /** 
   * <em>status</em>コラムの値を取得します。
   * @@return webbroker3.slebase.enums.SleRcvdqProcStatusEnumの値 
   */
  public webbroker3.slebase.enums.SleRcvdqProcStatusEnum getStatus();


  /** 
   * <em>status</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsSet();


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
