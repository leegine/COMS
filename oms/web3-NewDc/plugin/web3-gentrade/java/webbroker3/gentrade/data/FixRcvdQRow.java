head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.29.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FixRcvdQRow.java;


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
 * FixRcvdQRowインタフェースは変更不可でリードオンリーである<em>fix_rcvd_q</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link FixRcvdQRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FixRcvdQPK 
 */
public interface FixRcvdQRow extends Row {


  /** 
   * この{@@link FixRcvdQRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "fix_rcvd_q", "session" );


  /** 
   * <em>queue_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getQueueId();


  /** 
   * <em>queue_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQueueIdIsSet();


  /** 
   * <em>queue_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQueueIdIsModified();


  /** 
   * <em>session_id</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSessionId();


  /** 
   * <em>session_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSessionIdIsSet();


  /** 
   * <em>session_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSessionIdIsModified();


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
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountCode();


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeIsSet();


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeIsModified();


  /** 
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAccountId();


  /** 
   * <em>account_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountIdIsNull();


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountIdIsSet();


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountIdIsModified();


  /** 
   * <em>order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderRequestNumber();


  /** 
   * <em>order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRequestNumberIsSet();


  /** 
   * <em>order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRequestNumberIsModified();


  /** 
   * <em>order_action_serial_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getOrderActionSerialNo();


  /** 
   * <em>order_action_serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderActionSerialNoIsSet();


  /** 
   * <em>order_action_serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderActionSerialNoIsModified();


  /** 
   * <em>product_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType();


  /** 
   * <em>product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductTypeIsSet();


  /** 
   * <em>product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductTypeIsModified();


  /** 
   * <em>op_type</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getOpType();


  /** 
   * <em>op_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpTypeIsSet();


  /** 
   * <em>op_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpTypeIsModified();


  /** 
   * <em>replies_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRepliesType();


  /** 
   * <em>replies_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepliesTypeIsSet();


  /** 
   * <em>replies_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepliesTypeIsModified();


  /** 
   * <em>replies_number</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getRepliesNumber();


  /** 
   * <em>replies_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepliesNumberIsSet();


  /** 
   * <em>replies_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepliesNumberIsModified();


  /** 
   * <em>avg_px</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAvgPx();


  /** 
   * <em>avg_px</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAvgPxIsNull();


  /** 
   * <em>avg_px</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAvgPxIsSet();


  /** 
   * <em>avg_px</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAvgPxIsModified();


  /** 
   * <em>cl_ord_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getClOrdId();


  /** 
   * <em>cl_ord_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClOrdIdIsSet();


  /** 
   * <em>cl_ord_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClOrdIdIsModified();


  /** 
   * <em>cum_qty</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCumQty();


  /** 
   * <em>cum_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCumQtyIsNull();


  /** 
   * <em>cum_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCumQtyIsSet();


  /** 
   * <em>cum_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCumQtyIsModified();


  /** 
   * <em>exec_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExecId();


  /** 
   * <em>exec_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecIdIsSet();


  /** 
   * <em>exec_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecIdIsModified();


  /** 
   * <em>exec_ref_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExecRefId();


  /** 
   * <em>exec_ref_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecRefIdIsSet();


  /** 
   * <em>exec_ref_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecRefIdIsModified();


  /** 
   * <em>exec_trans_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExecTransType();


  /** 
   * <em>exec_trans_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecTransTypeIsSet();


  /** 
   * <em>exec_trans_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecTransTypeIsModified();


  /** 
   * <em>last_px</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLastPx();


  /** 
   * <em>last_px</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLastPxIsNull();


  /** 
   * <em>last_px</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastPxIsSet();


  /** 
   * <em>last_px</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastPxIsModified();


  /** 
   * <em>last_shares</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLastShares();


  /** 
   * <em>last_shares</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLastSharesIsNull();


  /** 
   * <em>last_shares</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastSharesIsSet();


  /** 
   * <em>last_shares</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastSharesIsModified();


  /** 
   * <em>order_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderId();


  /** 
   * <em>order_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderIdIsSet();


  /** 
   * <em>order_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderIdIsModified();


  /** 
   * <em>order_qty</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOrderQty();


  /** 
   * <em>order_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOrderQtyIsNull();


  /** 
   * <em>order_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderQtyIsSet();


  /** 
   * <em>order_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderQtyIsModified();


  /** 
   * <em>ord_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrdStatus();


  /** 
   * <em>ord_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrdStatusIsSet();


  /** 
   * <em>ord_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrdStatusIsModified();


  /** 
   * <em>ord_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrdType();


  /** 
   * <em>ord_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrdTypeIsSet();


  /** 
   * <em>ord_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrdTypeIsModified();


  /** 
   * <em>orig_cl_ord_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrigClOrdId();


  /** 
   * <em>orig_cl_ord_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrigClOrdIdIsSet();


  /** 
   * <em>orig_cl_ord_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrigClOrdIdIsModified();


  /** 
   * <em>price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPrice();


  /** 
   * <em>price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPriceIsNull();


  /** 
   * <em>price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceIsSet();


  /** 
   * <em>price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceIsModified();


  /** 
   * <em>order_capacity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderCapacity();


  /** 
   * <em>order_capacity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderCapacityIsSet();


  /** 
   * <em>order_capacity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderCapacityIsModified();


  /** 
   * <em>side</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSide();


  /** 
   * <em>side</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSideIsSet();


  /** 
   * <em>side</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSideIsModified();


  /** 
   * <em>symbol</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSymbol();


  /** 
   * <em>symbol</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSymbolIsSet();


  /** 
   * <em>symbol</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSymbolIsModified();


  /** 
   * <em>text</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getText();


  /** 
   * <em>text</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTextIsSet();


  /** 
   * <em>text</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTextIsModified();


  /** 
   * <em>time_in_force</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTimeInForce();


  /** 
   * <em>time_in_force</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTimeInForceIsSet();


  /** 
   * <em>time_in_force</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTimeInForceIsModified();


  /** 
   * <em>transact_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getTransactTime();


  /** 
   * <em>transact_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransactTimeIsSet();


  /** 
   * <em>transact_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransactTimeIsModified();


  /** 
   * <em>ex_destination</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExDestination();


  /** 
   * <em>ex_destination</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExDestinationIsSet();


  /** 
   * <em>ex_destination</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExDestinationIsModified();


  /** 
   * <em>ord_rej_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrdRejReason();


  /** 
   * <em>ord_rej_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrdRejReasonIsSet();


  /** 
   * <em>ord_rej_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrdRejReasonIsModified();


  /** 
   * <em>cxl_rej_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCxlRejReason();


  /** 
   * <em>cxl_rej_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCxlRejReasonIsSet();


  /** 
   * <em>cxl_rej_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCxlRejReasonIsModified();


  /** 
   * <em>cxl_rej_response_to</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCxlRejResponseTo();


  /** 
   * <em>cxl_rej_response_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCxlRejResponseToIsSet();


  /** 
   * <em>cxl_rej_response_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCxlRejResponseToIsModified();


  /** 
   * <em>exec_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExecType();


  /** 
   * <em>exec_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecTypeIsSet();


  /** 
   * <em>exec_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecTypeIsModified();


  /** 
   * <em>leaves_qty</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLeavesQty();


  /** 
   * <em>leaves_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLeavesQtyIsNull();


  /** 
   * <em>leaves_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLeavesQtyIsSet();


  /** 
   * <em>leaves_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLeavesQtyIsModified();


  /** 
   * <em>msg_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMsgId();


  /** 
   * <em>msg_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMsgIdIsSet();


  /** 
   * <em>msg_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMsgIdIsModified();


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
   * <em>client_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getClientId();


  /** 
   * <em>client_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClientIdIsSet();


  /** 
   * <em>client_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClientIdIsModified();


  /** 
   * <em>trade_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getTradeDate();


  /** 
   * <em>trade_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeDateIsSet();


  /** 
   * <em>trade_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeDateIsModified();


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStatus();


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsSet();


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsModified();


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


}
@
