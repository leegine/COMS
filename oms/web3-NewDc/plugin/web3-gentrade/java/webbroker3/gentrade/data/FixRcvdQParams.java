head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.17.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FixRcvdQParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * fix_rcvd_qテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link FixRcvdQRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link FixRcvdQParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link FixRcvdQParams}が{@@link FixRcvdQRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FixRcvdQPK 
 * @@see FixRcvdQRow 
 */
public class FixRcvdQParams extends Params implements FixRcvdQRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "fix_rcvd_q";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = FixRcvdQRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return FixRcvdQRow.TYPE;
  }


  /** 
   * <em>queue_id</em>カラムの値 
   */
  public  long  queue_id;

  /** 
   * <em>session_id</em>カラムの値 
   */
  public  int  session_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  Long  account_id;

  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>order_action_serial_no</em>カラムの値 
   */
  public  int  order_action_serial_no;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>op_type</em>カラムの値 
   */
  public  int  op_type;

  /** 
   * <em>replies_type</em>カラムの値 
   */
  public  String  replies_type;

  /** 
   * <em>replies_number</em>カラムの値 
   */
  public  long  replies_number;

  /** 
   * <em>avg_px</em>カラムの値 
   */
  public  Double  avg_px;

  /** 
   * <em>cl_ord_id</em>カラムの値 
   */
  public  String  cl_ord_id;

  /** 
   * <em>cum_qty</em>カラムの値 
   */
  public  Double  cum_qty;

  /** 
   * <em>exec_id</em>カラムの値 
   */
  public  String  exec_id;

  /** 
   * <em>exec_ref_id</em>カラムの値 
   */
  public  String  exec_ref_id;

  /** 
   * <em>exec_trans_type</em>カラムの値 
   */
  public  String  exec_trans_type;

  /** 
   * <em>last_px</em>カラムの値 
   */
  public  Double  last_px;

  /** 
   * <em>last_shares</em>カラムの値 
   */
  public  Double  last_shares;

  /** 
   * <em>order_id</em>カラムの値 
   */
  public  String  order_id;

  /** 
   * <em>order_qty</em>カラムの値 
   */
  public  Double  order_qty;

  /** 
   * <em>ord_status</em>カラムの値 
   */
  public  String  ord_status;

  /** 
   * <em>ord_type</em>カラムの値 
   */
  public  String  ord_type;

  /** 
   * <em>orig_cl_ord_id</em>カラムの値 
   */
  public  String  orig_cl_ord_id;

  /** 
   * <em>price</em>カラムの値 
   */
  public  Double  price;

  /** 
   * <em>order_capacity</em>カラムの値 
   */
  public  String  order_capacity;

  /** 
   * <em>side</em>カラムの値 
   */
  public  String  side;

  /** 
   * <em>symbol</em>カラムの値 
   */
  public  String  symbol;

  /** 
   * <em>text</em>カラムの値 
   */
  public  String  text;

  /** 
   * <em>time_in_force</em>カラムの値 
   */
  public  String  time_in_force;

  /** 
   * <em>transact_time</em>カラムの値 
   */
  public  java.sql.Timestamp  transact_time;

  /** 
   * <em>ex_destination</em>カラムの値 
   */
  public  String  ex_destination;

  /** 
   * <em>ord_rej_reason</em>カラムの値 
   */
  public  String  ord_rej_reason;

  /** 
   * <em>cxl_rej_reason</em>カラムの値 
   */
  public  String  cxl_rej_reason;

  /** 
   * <em>cxl_rej_response_to</em>カラムの値 
   */
  public  String  cxl_rej_response_to;

  /** 
   * <em>exec_type</em>カラムの値 
   */
  public  String  exec_type;

  /** 
   * <em>leaves_qty</em>カラムの値 
   */
  public  Double  leaves_qty;

  /** 
   * <em>msg_id</em>カラムの値 
   */
  public  String  msg_id;

  /** 
   * <em>account</em>カラムの値 
   */
  public  String  account;

  /** 
   * <em>client_id</em>カラムの値 
   */
  public  String  client_id;

  /** 
   * <em>trade_date</em>カラムの値 
   */
  public  java.sql.Timestamp  trade_date;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean queue_id_is_set = false;
  private boolean queue_id_is_modified = false;
  private boolean session_id_is_set = false;
  private boolean session_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean order_action_serial_no_is_set = false;
  private boolean order_action_serial_no_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean op_type_is_set = false;
  private boolean op_type_is_modified = false;
  private boolean replies_type_is_set = false;
  private boolean replies_type_is_modified = false;
  private boolean replies_number_is_set = false;
  private boolean replies_number_is_modified = false;
  private boolean avg_px_is_set = false;
  private boolean avg_px_is_modified = false;
  private boolean cl_ord_id_is_set = false;
  private boolean cl_ord_id_is_modified = false;
  private boolean cum_qty_is_set = false;
  private boolean cum_qty_is_modified = false;
  private boolean exec_id_is_set = false;
  private boolean exec_id_is_modified = false;
  private boolean exec_ref_id_is_set = false;
  private boolean exec_ref_id_is_modified = false;
  private boolean exec_trans_type_is_set = false;
  private boolean exec_trans_type_is_modified = false;
  private boolean last_px_is_set = false;
  private boolean last_px_is_modified = false;
  private boolean last_shares_is_set = false;
  private boolean last_shares_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean order_qty_is_set = false;
  private boolean order_qty_is_modified = false;
  private boolean ord_status_is_set = false;
  private boolean ord_status_is_modified = false;
  private boolean ord_type_is_set = false;
  private boolean ord_type_is_modified = false;
  private boolean orig_cl_ord_id_is_set = false;
  private boolean orig_cl_ord_id_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean order_capacity_is_set = false;
  private boolean order_capacity_is_modified = false;
  private boolean side_is_set = false;
  private boolean side_is_modified = false;
  private boolean symbol_is_set = false;
  private boolean symbol_is_modified = false;
  private boolean text_is_set = false;
  private boolean text_is_modified = false;
  private boolean time_in_force_is_set = false;
  private boolean time_in_force_is_modified = false;
  private boolean transact_time_is_set = false;
  private boolean transact_time_is_modified = false;
  private boolean ex_destination_is_set = false;
  private boolean ex_destination_is_modified = false;
  private boolean ord_rej_reason_is_set = false;
  private boolean ord_rej_reason_is_modified = false;
  private boolean cxl_rej_reason_is_set = false;
  private boolean cxl_rej_reason_is_modified = false;
  private boolean cxl_rej_response_to_is_set = false;
  private boolean cxl_rej_response_to_is_modified = false;
  private boolean exec_type_is_set = false;
  private boolean exec_type_is_modified = false;
  private boolean leaves_qty_is_set = false;
  private boolean leaves_qty_is_modified = false;
  private boolean msg_id_is_set = false;
  private boolean msg_id_is_modified = false;
  private boolean account_is_set = false;
  private boolean account_is_modified = false;
  private boolean client_id_is_set = false;
  private boolean client_id_is_modified = false;
  private boolean trade_date_is_set = false;
  private boolean trade_date_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "queue_id=" + queue_id
      + "," + "session_id=" +session_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "account_id=" +account_id
      + "," + "order_request_number=" +order_request_number
      + "," + "order_action_serial_no=" +order_action_serial_no
      + "," + "product_type=" +product_type
      + "," + "op_type=" +op_type
      + "," + "replies_type=" +replies_type
      + "," + "replies_number=" +replies_number
      + "," + "avg_px=" +avg_px
      + "," + "cl_ord_id=" +cl_ord_id
      + "," + "cum_qty=" +cum_qty
      + "," + "exec_id=" +exec_id
      + "," + "exec_ref_id=" +exec_ref_id
      + "," + "exec_trans_type=" +exec_trans_type
      + "," + "last_px=" +last_px
      + "," + "last_shares=" +last_shares
      + "," + "order_id=" +order_id
      + "," + "order_qty=" +order_qty
      + "," + "ord_status=" +ord_status
      + "," + "ord_type=" +ord_type
      + "," + "orig_cl_ord_id=" +orig_cl_ord_id
      + "," + "price=" +price
      + "," + "order_capacity=" +order_capacity
      + "," + "side=" +side
      + "," + "symbol=" +symbol
      + "," + "text=" +text
      + "," + "time_in_force=" +time_in_force
      + "," + "transact_time=" +transact_time
      + "," + "ex_destination=" +ex_destination
      + "," + "ord_rej_reason=" +ord_rej_reason
      + "," + "cxl_rej_reason=" +cxl_rej_reason
      + "," + "cxl_rej_response_to=" +cxl_rej_response_to
      + "," + "exec_type=" +exec_type
      + "," + "leaves_qty=" +leaves_qty
      + "," + "msg_id=" +msg_id
      + "," + "account=" +account
      + "," + "client_id=" +client_id
      + "," + "trade_date=" +trade_date
      + "," + "status=" +status
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のFixRcvdQParamsオブジェクトを作成します。 
   */
  public FixRcvdQParams() {
    queue_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のFixRcvdQRowオブジェクトの値を利用してFixRcvdQParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するFixRcvdQRowオブジェクト 
   */
  public FixRcvdQParams( FixRcvdQRow p_row )
  {
    queue_id = p_row.getQueueId();
    queue_id_is_set = p_row.getQueueIdIsSet();
    queue_id_is_modified = p_row.getQueueIdIsModified();
    session_id = p_row.getSessionId();
    session_id_is_set = p_row.getSessionIdIsSet();
    session_id_is_modified = p_row.getSessionIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    if ( !p_row.getAccountIdIsNull() )
      account_id = new Long( p_row.getAccountId() );
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    order_action_serial_no = p_row.getOrderActionSerialNo();
    order_action_serial_no_is_set = p_row.getOrderActionSerialNoIsSet();
    order_action_serial_no_is_modified = p_row.getOrderActionSerialNoIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    op_type = p_row.getOpType();
    op_type_is_set = p_row.getOpTypeIsSet();
    op_type_is_modified = p_row.getOpTypeIsModified();
    replies_type = p_row.getRepliesType();
    replies_type_is_set = p_row.getRepliesTypeIsSet();
    replies_type_is_modified = p_row.getRepliesTypeIsModified();
    replies_number = p_row.getRepliesNumber();
    replies_number_is_set = p_row.getRepliesNumberIsSet();
    replies_number_is_modified = p_row.getRepliesNumberIsModified();
    if ( !p_row.getAvgPxIsNull() )
      avg_px = new Double( p_row.getAvgPx() );
    avg_px_is_set = p_row.getAvgPxIsSet();
    avg_px_is_modified = p_row.getAvgPxIsModified();
    cl_ord_id = p_row.getClOrdId();
    cl_ord_id_is_set = p_row.getClOrdIdIsSet();
    cl_ord_id_is_modified = p_row.getClOrdIdIsModified();
    if ( !p_row.getCumQtyIsNull() )
      cum_qty = new Double( p_row.getCumQty() );
    cum_qty_is_set = p_row.getCumQtyIsSet();
    cum_qty_is_modified = p_row.getCumQtyIsModified();
    exec_id = p_row.getExecId();
    exec_id_is_set = p_row.getExecIdIsSet();
    exec_id_is_modified = p_row.getExecIdIsModified();
    exec_ref_id = p_row.getExecRefId();
    exec_ref_id_is_set = p_row.getExecRefIdIsSet();
    exec_ref_id_is_modified = p_row.getExecRefIdIsModified();
    exec_trans_type = p_row.getExecTransType();
    exec_trans_type_is_set = p_row.getExecTransTypeIsSet();
    exec_trans_type_is_modified = p_row.getExecTransTypeIsModified();
    if ( !p_row.getLastPxIsNull() )
      last_px = new Double( p_row.getLastPx() );
    last_px_is_set = p_row.getLastPxIsSet();
    last_px_is_modified = p_row.getLastPxIsModified();
    if ( !p_row.getLastSharesIsNull() )
      last_shares = new Double( p_row.getLastShares() );
    last_shares_is_set = p_row.getLastSharesIsSet();
    last_shares_is_modified = p_row.getLastSharesIsModified();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    if ( !p_row.getOrderQtyIsNull() )
      order_qty = new Double( p_row.getOrderQty() );
    order_qty_is_set = p_row.getOrderQtyIsSet();
    order_qty_is_modified = p_row.getOrderQtyIsModified();
    ord_status = p_row.getOrdStatus();
    ord_status_is_set = p_row.getOrdStatusIsSet();
    ord_status_is_modified = p_row.getOrdStatusIsModified();
    ord_type = p_row.getOrdType();
    ord_type_is_set = p_row.getOrdTypeIsSet();
    ord_type_is_modified = p_row.getOrdTypeIsModified();
    orig_cl_ord_id = p_row.getOrigClOrdId();
    orig_cl_ord_id_is_set = p_row.getOrigClOrdIdIsSet();
    orig_cl_ord_id_is_modified = p_row.getOrigClOrdIdIsModified();
    if ( !p_row.getPriceIsNull() )
      price = new Double( p_row.getPrice() );
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    order_capacity = p_row.getOrderCapacity();
    order_capacity_is_set = p_row.getOrderCapacityIsSet();
    order_capacity_is_modified = p_row.getOrderCapacityIsModified();
    side = p_row.getSide();
    side_is_set = p_row.getSideIsSet();
    side_is_modified = p_row.getSideIsModified();
    symbol = p_row.getSymbol();
    symbol_is_set = p_row.getSymbolIsSet();
    symbol_is_modified = p_row.getSymbolIsModified();
    text = p_row.getText();
    text_is_set = p_row.getTextIsSet();
    text_is_modified = p_row.getTextIsModified();
    time_in_force = p_row.getTimeInForce();
    time_in_force_is_set = p_row.getTimeInForceIsSet();
    time_in_force_is_modified = p_row.getTimeInForceIsModified();
    transact_time = p_row.getTransactTime();
    transact_time_is_set = p_row.getTransactTimeIsSet();
    transact_time_is_modified = p_row.getTransactTimeIsModified();
    ex_destination = p_row.getExDestination();
    ex_destination_is_set = p_row.getExDestinationIsSet();
    ex_destination_is_modified = p_row.getExDestinationIsModified();
    ord_rej_reason = p_row.getOrdRejReason();
    ord_rej_reason_is_set = p_row.getOrdRejReasonIsSet();
    ord_rej_reason_is_modified = p_row.getOrdRejReasonIsModified();
    cxl_rej_reason = p_row.getCxlRejReason();
    cxl_rej_reason_is_set = p_row.getCxlRejReasonIsSet();
    cxl_rej_reason_is_modified = p_row.getCxlRejReasonIsModified();
    cxl_rej_response_to = p_row.getCxlRejResponseTo();
    cxl_rej_response_to_is_set = p_row.getCxlRejResponseToIsSet();
    cxl_rej_response_to_is_modified = p_row.getCxlRejResponseToIsModified();
    exec_type = p_row.getExecType();
    exec_type_is_set = p_row.getExecTypeIsSet();
    exec_type_is_modified = p_row.getExecTypeIsModified();
    if ( !p_row.getLeavesQtyIsNull() )
      leaves_qty = new Double( p_row.getLeavesQty() );
    leaves_qty_is_set = p_row.getLeavesQtyIsSet();
    leaves_qty_is_modified = p_row.getLeavesQtyIsModified();
    msg_id = p_row.getMsgId();
    msg_id_is_set = p_row.getMsgIdIsSet();
    msg_id_is_modified = p_row.getMsgIdIsModified();
    account = p_row.getAccount();
    account_is_set = p_row.getAccountIsSet();
    account_is_modified = p_row.getAccountIsModified();
    client_id = p_row.getClientId();
    client_id_is_set = p_row.getClientIdIsSet();
    client_id_is_modified = p_row.getClientIdIsModified();
    trade_date = p_row.getTradeDate();
    trade_date_is_set = p_row.getTradeDateIsSet();
    trade_date_is_modified = p_row.getTradeDateIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      session_id_is_set = true;
      session_id_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      order_action_serial_no_is_set = true;
      order_action_serial_no_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      op_type_is_set = true;
      op_type_is_modified = true;
      replies_type_is_set = true;
      replies_type_is_modified = true;
      replies_number_is_set = true;
      replies_number_is_modified = true;
      avg_px_is_set = true;
      avg_px_is_modified = true;
      cl_ord_id_is_set = true;
      cl_ord_id_is_modified = true;
      cum_qty_is_set = true;
      cum_qty_is_modified = true;
      exec_id_is_set = true;
      exec_id_is_modified = true;
      exec_ref_id_is_set = true;
      exec_ref_id_is_modified = true;
      exec_trans_type_is_set = true;
      exec_trans_type_is_modified = true;
      last_px_is_set = true;
      last_px_is_modified = true;
      last_shares_is_set = true;
      last_shares_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      order_qty_is_set = true;
      order_qty_is_modified = true;
      ord_status_is_set = true;
      ord_status_is_modified = true;
      ord_type_is_set = true;
      ord_type_is_modified = true;
      orig_cl_ord_id_is_set = true;
      orig_cl_ord_id_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      order_capacity_is_set = true;
      order_capacity_is_modified = true;
      side_is_set = true;
      side_is_modified = true;
      symbol_is_set = true;
      symbol_is_modified = true;
      text_is_set = true;
      text_is_modified = true;
      time_in_force_is_set = true;
      time_in_force_is_modified = true;
      transact_time_is_set = true;
      transact_time_is_modified = true;
      ex_destination_is_set = true;
      ex_destination_is_modified = true;
      ord_rej_reason_is_set = true;
      ord_rej_reason_is_modified = true;
      cxl_rej_reason_is_set = true;
      cxl_rej_reason_is_modified = true;
      cxl_rej_response_to_is_set = true;
      cxl_rej_response_to_is_modified = true;
      exec_type_is_set = true;
      exec_type_is_modified = true;
      leaves_qty_is_set = true;
      leaves_qty_is_modified = true;
      msg_id_is_set = true;
      msg_id_is_modified = true;
      account_is_set = true;
      account_is_modified = true;
      client_id_is_set = true;
      client_id_is_modified = true;
      trade_date_is_set = true;
      trade_date_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof FixRcvdQRow ) )
       return false;
    return fieldsEqual( (FixRcvdQRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のFixRcvdQRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( FixRcvdQRow row )
  {
    if ( queue_id != row.getQueueId() )
      return false;
    if ( session_id != row.getSessionId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( account_id == null ) {
      if ( !row.getAccountIdIsNull() )
        return false;
    } else if ( row.getAccountIdIsNull() || ( account_id.longValue() != row.getAccountId() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( order_action_serial_no != row.getOrderActionSerialNo() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( op_type != row.getOpType() )
      return false;
    if ( replies_type == null ) {
      if ( row.getRepliesType() != null )
        return false;
    } else if ( !replies_type.equals( row.getRepliesType() ) ) {
        return false;
    }
    if ( replies_number != row.getRepliesNumber() )
      return false;
    if ( avg_px == null ) {
      if ( !row.getAvgPxIsNull() )
        return false;
    } else if ( row.getAvgPxIsNull() || ( avg_px.doubleValue() != row.getAvgPx() ) ) {
        return false;
    }
    if ( cl_ord_id == null ) {
      if ( row.getClOrdId() != null )
        return false;
    } else if ( !cl_ord_id.equals( row.getClOrdId() ) ) {
        return false;
    }
    if ( cum_qty == null ) {
      if ( !row.getCumQtyIsNull() )
        return false;
    } else if ( row.getCumQtyIsNull() || ( cum_qty.doubleValue() != row.getCumQty() ) ) {
        return false;
    }
    if ( exec_id == null ) {
      if ( row.getExecId() != null )
        return false;
    } else if ( !exec_id.equals( row.getExecId() ) ) {
        return false;
    }
    if ( exec_ref_id == null ) {
      if ( row.getExecRefId() != null )
        return false;
    } else if ( !exec_ref_id.equals( row.getExecRefId() ) ) {
        return false;
    }
    if ( exec_trans_type == null ) {
      if ( row.getExecTransType() != null )
        return false;
    } else if ( !exec_trans_type.equals( row.getExecTransType() ) ) {
        return false;
    }
    if ( last_px == null ) {
      if ( !row.getLastPxIsNull() )
        return false;
    } else if ( row.getLastPxIsNull() || ( last_px.doubleValue() != row.getLastPx() ) ) {
        return false;
    }
    if ( last_shares == null ) {
      if ( !row.getLastSharesIsNull() )
        return false;
    } else if ( row.getLastSharesIsNull() || ( last_shares.doubleValue() != row.getLastShares() ) ) {
        return false;
    }
    if ( order_id == null ) {
      if ( row.getOrderId() != null )
        return false;
    } else if ( !order_id.equals( row.getOrderId() ) ) {
        return false;
    }
    if ( order_qty == null ) {
      if ( !row.getOrderQtyIsNull() )
        return false;
    } else if ( row.getOrderQtyIsNull() || ( order_qty.doubleValue() != row.getOrderQty() ) ) {
        return false;
    }
    if ( ord_status == null ) {
      if ( row.getOrdStatus() != null )
        return false;
    } else if ( !ord_status.equals( row.getOrdStatus() ) ) {
        return false;
    }
    if ( ord_type == null ) {
      if ( row.getOrdType() != null )
        return false;
    } else if ( !ord_type.equals( row.getOrdType() ) ) {
        return false;
    }
    if ( orig_cl_ord_id == null ) {
      if ( row.getOrigClOrdId() != null )
        return false;
    } else if ( !orig_cl_ord_id.equals( row.getOrigClOrdId() ) ) {
        return false;
    }
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.doubleValue() != row.getPrice() ) ) {
        return false;
    }
    if ( order_capacity == null ) {
      if ( row.getOrderCapacity() != null )
        return false;
    } else if ( !order_capacity.equals( row.getOrderCapacity() ) ) {
        return false;
    }
    if ( side == null ) {
      if ( row.getSide() != null )
        return false;
    } else if ( !side.equals( row.getSide() ) ) {
        return false;
    }
    if ( symbol == null ) {
      if ( row.getSymbol() != null )
        return false;
    } else if ( !symbol.equals( row.getSymbol() ) ) {
        return false;
    }
    if ( text == null ) {
      if ( row.getText() != null )
        return false;
    } else if ( !text.equals( row.getText() ) ) {
        return false;
    }
    if ( time_in_force == null ) {
      if ( row.getTimeInForce() != null )
        return false;
    } else if ( !time_in_force.equals( row.getTimeInForce() ) ) {
        return false;
    }
    if ( transact_time == null ) {
      if ( row.getTransactTime() != null )
        return false;
    } else if ( !transact_time.equals( row.getTransactTime() ) ) {
        return false;
    }
    if ( ex_destination == null ) {
      if ( row.getExDestination() != null )
        return false;
    } else if ( !ex_destination.equals( row.getExDestination() ) ) {
        return false;
    }
    if ( ord_rej_reason == null ) {
      if ( row.getOrdRejReason() != null )
        return false;
    } else if ( !ord_rej_reason.equals( row.getOrdRejReason() ) ) {
        return false;
    }
    if ( cxl_rej_reason == null ) {
      if ( row.getCxlRejReason() != null )
        return false;
    } else if ( !cxl_rej_reason.equals( row.getCxlRejReason() ) ) {
        return false;
    }
    if ( cxl_rej_response_to == null ) {
      if ( row.getCxlRejResponseTo() != null )
        return false;
    } else if ( !cxl_rej_response_to.equals( row.getCxlRejResponseTo() ) ) {
        return false;
    }
    if ( exec_type == null ) {
      if ( row.getExecType() != null )
        return false;
    } else if ( !exec_type.equals( row.getExecType() ) ) {
        return false;
    }
    if ( leaves_qty == null ) {
      if ( !row.getLeavesQtyIsNull() )
        return false;
    } else if ( row.getLeavesQtyIsNull() || ( leaves_qty.doubleValue() != row.getLeavesQty() ) ) {
        return false;
    }
    if ( msg_id == null ) {
      if ( row.getMsgId() != null )
        return false;
    } else if ( !msg_id.equals( row.getMsgId() ) ) {
        return false;
    }
    if ( account == null ) {
      if ( row.getAccount() != null )
        return false;
    } else if ( !account.equals( row.getAccount() ) ) {
        return false;
    }
    if ( client_id == null ) {
      if ( row.getClientId() != null )
        return false;
    } else if ( !client_id.equals( row.getClientId() ) ) {
        return false;
    }
    if ( trade_date == null ) {
      if ( row.getTradeDate() != null )
        return false;
    } else if ( !trade_date.equals( row.getTradeDate() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) queue_id)
        + ((int) session_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (account_id!=null? account_id.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + ((int) order_action_serial_no)
        + (product_type!=null? product_type.hashCode(): 0) 
        + ((int) op_type)
        + (replies_type!=null? replies_type.hashCode(): 0) 
        + ((int) replies_number)
        + (avg_px!=null? avg_px.hashCode(): 0) 
        + (cl_ord_id!=null? cl_ord_id.hashCode(): 0) 
        + (cum_qty!=null? cum_qty.hashCode(): 0) 
        + (exec_id!=null? exec_id.hashCode(): 0) 
        + (exec_ref_id!=null? exec_ref_id.hashCode(): 0) 
        + (exec_trans_type!=null? exec_trans_type.hashCode(): 0) 
        + (last_px!=null? last_px.hashCode(): 0) 
        + (last_shares!=null? last_shares.hashCode(): 0) 
        + (order_id!=null? order_id.hashCode(): 0) 
        + (order_qty!=null? order_qty.hashCode(): 0) 
        + (ord_status!=null? ord_status.hashCode(): 0) 
        + (ord_type!=null? ord_type.hashCode(): 0) 
        + (orig_cl_ord_id!=null? orig_cl_ord_id.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (order_capacity!=null? order_capacity.hashCode(): 0) 
        + (side!=null? side.hashCode(): 0) 
        + (symbol!=null? symbol.hashCode(): 0) 
        + (text!=null? text.hashCode(): 0) 
        + (time_in_force!=null? time_in_force.hashCode(): 0) 
        + (transact_time!=null? transact_time.hashCode(): 0) 
        + (ex_destination!=null? ex_destination.hashCode(): 0) 
        + (ord_rej_reason!=null? ord_rej_reason.hashCode(): 0) 
        + (cxl_rej_reason!=null? cxl_rej_reason.hashCode(): 0) 
        + (cxl_rej_response_to!=null? cxl_rej_response_to.hashCode(): 0) 
        + (exec_type!=null? exec_type.hashCode(): 0) 
        + (leaves_qty!=null? leaves_qty.hashCode(): 0) 
        + (msg_id!=null? msg_id.hashCode(): 0) 
        + (account!=null? account.hashCode(): 0) 
        + (client_id!=null? client_id.hashCode(): 0) 
        + (trade_date!=null? trade_date.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !session_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'session_id' must be set before inserting.");
		if ( !order_action_serial_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_action_serial_no' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !op_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'op_type' must be set before inserting.");
		if ( !replies_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'replies_number' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("queue_id",new Long(queue_id));
		map.put("session_id",new Integer(session_id));
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( account_id != null )
			map.put("account_id",account_id);
		if ( order_request_number != null )
			map.put("order_request_number",order_request_number);
		map.put("order_action_serial_no",new Integer(order_action_serial_no));
		map.put("product_type",product_type);
		map.put("op_type",new Integer(op_type));
		if ( replies_type != null )
			map.put("replies_type",replies_type);
		map.put("replies_number",new Long(replies_number));
		if ( avg_px != null )
			map.put("avg_px",avg_px);
		if ( cl_ord_id != null )
			map.put("cl_ord_id",cl_ord_id);
		if ( cum_qty != null )
			map.put("cum_qty",cum_qty);
		if ( exec_id != null )
			map.put("exec_id",exec_id);
		if ( exec_ref_id != null )
			map.put("exec_ref_id",exec_ref_id);
		if ( exec_trans_type != null )
			map.put("exec_trans_type",exec_trans_type);
		if ( last_px != null )
			map.put("last_px",last_px);
		if ( last_shares != null )
			map.put("last_shares",last_shares);
		if ( order_id != null )
			map.put("order_id",order_id);
		if ( order_qty != null )
			map.put("order_qty",order_qty);
		if ( ord_status != null )
			map.put("ord_status",ord_status);
		if ( ord_type != null )
			map.put("ord_type",ord_type);
		if ( orig_cl_ord_id != null )
			map.put("orig_cl_ord_id",orig_cl_ord_id);
		if ( price != null )
			map.put("price",price);
		if ( order_capacity != null )
			map.put("order_capacity",order_capacity);
		if ( side != null )
			map.put("side",side);
		if ( symbol != null )
			map.put("symbol",symbol);
		if ( text != null )
			map.put("text",text);
		if ( time_in_force != null )
			map.put("time_in_force",time_in_force);
		if ( transact_time != null )
			map.put("transact_time",transact_time);
		if ( ex_destination != null )
			map.put("ex_destination",ex_destination);
		if ( ord_rej_reason != null )
			map.put("ord_rej_reason",ord_rej_reason);
		if ( cxl_rej_reason != null )
			map.put("cxl_rej_reason",cxl_rej_reason);
		if ( cxl_rej_response_to != null )
			map.put("cxl_rej_response_to",cxl_rej_response_to);
		if ( exec_type != null )
			map.put("exec_type",exec_type);
		if ( leaves_qty != null )
			map.put("leaves_qty",leaves_qty);
		if ( msg_id != null )
			map.put("msg_id",msg_id);
		if ( account != null )
			map.put("account",account);
		if ( client_id != null )
			map.put("client_id",client_id);
		if ( trade_date != null )
			map.put("trade_date",trade_date);
		map.put("status",status);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( session_id_is_modified )
			map.put("session_id",new Integer(session_id));
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( account_id_is_modified )
			map.put("account_id",account_id);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( order_action_serial_no_is_modified )
			map.put("order_action_serial_no",new Integer(order_action_serial_no));
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( op_type_is_modified )
			map.put("op_type",new Integer(op_type));
		if ( replies_type_is_modified )
			map.put("replies_type",replies_type);
		if ( replies_number_is_modified )
			map.put("replies_number",new Long(replies_number));
		if ( avg_px_is_modified )
			map.put("avg_px",avg_px);
		if ( cl_ord_id_is_modified )
			map.put("cl_ord_id",cl_ord_id);
		if ( cum_qty_is_modified )
			map.put("cum_qty",cum_qty);
		if ( exec_id_is_modified )
			map.put("exec_id",exec_id);
		if ( exec_ref_id_is_modified )
			map.put("exec_ref_id",exec_ref_id);
		if ( exec_trans_type_is_modified )
			map.put("exec_trans_type",exec_trans_type);
		if ( last_px_is_modified )
			map.put("last_px",last_px);
		if ( last_shares_is_modified )
			map.put("last_shares",last_shares);
		if ( order_id_is_modified )
			map.put("order_id",order_id);
		if ( order_qty_is_modified )
			map.put("order_qty",order_qty);
		if ( ord_status_is_modified )
			map.put("ord_status",ord_status);
		if ( ord_type_is_modified )
			map.put("ord_type",ord_type);
		if ( orig_cl_ord_id_is_modified )
			map.put("orig_cl_ord_id",orig_cl_ord_id);
		if ( price_is_modified )
			map.put("price",price);
		if ( order_capacity_is_modified )
			map.put("order_capacity",order_capacity);
		if ( side_is_modified )
			map.put("side",side);
		if ( symbol_is_modified )
			map.put("symbol",symbol);
		if ( text_is_modified )
			map.put("text",text);
		if ( time_in_force_is_modified )
			map.put("time_in_force",time_in_force);
		if ( transact_time_is_modified )
			map.put("transact_time",transact_time);
		if ( ex_destination_is_modified )
			map.put("ex_destination",ex_destination);
		if ( ord_rej_reason_is_modified )
			map.put("ord_rej_reason",ord_rej_reason);
		if ( cxl_rej_reason_is_modified )
			map.put("cxl_rej_reason",cxl_rej_reason);
		if ( cxl_rej_response_to_is_modified )
			map.put("cxl_rej_response_to",cxl_rej_response_to);
		if ( exec_type_is_modified )
			map.put("exec_type",exec_type);
		if ( leaves_qty_is_modified )
			map.put("leaves_qty",leaves_qty);
		if ( msg_id_is_modified )
			map.put("msg_id",msg_id);
		if ( account_is_modified )
			map.put("account",account);
		if ( client_id_is_modified )
			map.put("client_id",client_id);
		if ( trade_date_is_modified )
			map.put("trade_date",trade_date);
		if ( status_is_modified )
			map.put("status",status);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( session_id_is_set )
				map.put("session_id",new Integer(session_id));
			map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("account_id",account_id);
			map.put("order_request_number",order_request_number);
			if ( order_action_serial_no_is_set )
				map.put("order_action_serial_no",new Integer(order_action_serial_no));
			if ( product_type_is_set )
				map.put("product_type",product_type);
			if ( op_type_is_set )
				map.put("op_type",new Integer(op_type));
			map.put("replies_type",replies_type);
			if ( replies_number_is_set )
				map.put("replies_number",new Long(replies_number));
			map.put("avg_px",avg_px);
			map.put("cl_ord_id",cl_ord_id);
			map.put("cum_qty",cum_qty);
			map.put("exec_id",exec_id);
			map.put("exec_ref_id",exec_ref_id);
			map.put("exec_trans_type",exec_trans_type);
			map.put("last_px",last_px);
			map.put("last_shares",last_shares);
			map.put("order_id",order_id);
			map.put("order_qty",order_qty);
			map.put("ord_status",ord_status);
			map.put("ord_type",ord_type);
			map.put("orig_cl_ord_id",orig_cl_ord_id);
			map.put("price",price);
			map.put("order_capacity",order_capacity);
			map.put("side",side);
			map.put("symbol",symbol);
			map.put("text",text);
			map.put("time_in_force",time_in_force);
			map.put("transact_time",transact_time);
			map.put("ex_destination",ex_destination);
			map.put("ord_rej_reason",ord_rej_reason);
			map.put("cxl_rej_reason",cxl_rej_reason);
			map.put("cxl_rej_response_to",cxl_rej_response_to);
			map.put("exec_type",exec_type);
			map.put("leaves_qty",leaves_qty);
			map.put("msg_id",msg_id);
			map.put("account",account);
			map.put("client_id",client_id);
			map.put("trade_date",trade_date);
			if ( status_is_set )
				map.put("status",status);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>queue_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getQueueId()
  {
    return queue_id;
  }


  /** 
   * <em>queue_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQueueIdIsSet() {
    return queue_id_is_set;
  }


  /** 
   * <em>queue_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQueueIdIsModified() {
    return queue_id_is_modified;
  }


  /** 
   * <em>session_id</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSessionId()
  {
    return session_id;
  }


  /** 
   * <em>session_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSessionIdIsSet() {
    return session_id_is_set;
  }


  /** 
   * <em>session_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSessionIdIsModified() {
    return session_id_is_modified;
  }


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
  }


  /** 
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsModified() {
    return account_code_is_modified;
  }


  /** 
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return ( account_id==null? ((long)0): account_id.longValue() );
  }


  /** 
   * <em>account_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountIdIsNull()
  {
    return account_id == null;
  }


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
  }


  /** 
   * <em>order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRequestNumber()
  {
    return order_request_number;
  }


  /** 
   * <em>order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsSet() {
    return order_request_number_is_set;
  }


  /** 
   * <em>order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsModified() {
    return order_request_number_is_modified;
  }


  /** 
   * <em>order_action_serial_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOrderActionSerialNo()
  {
    return order_action_serial_no;
  }


  /** 
   * <em>order_action_serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderActionSerialNoIsSet() {
    return order_action_serial_no_is_set;
  }


  /** 
   * <em>order_action_serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderActionSerialNoIsModified() {
    return order_action_serial_no_is_modified;
  }


  /** 
   * <em>product_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType()
  {
    return product_type;
  }


  /** 
   * <em>product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsSet() {
    return product_type_is_set;
  }


  /** 
   * <em>product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsModified() {
    return product_type_is_modified;
  }


  /** 
   * <em>op_type</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOpType()
  {
    return op_type;
  }


  /** 
   * <em>op_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpTypeIsSet() {
    return op_type_is_set;
  }


  /** 
   * <em>op_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpTypeIsModified() {
    return op_type_is_modified;
  }


  /** 
   * <em>replies_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepliesType()
  {
    return replies_type;
  }


  /** 
   * <em>replies_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepliesTypeIsSet() {
    return replies_type_is_set;
  }


  /** 
   * <em>replies_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepliesTypeIsModified() {
    return replies_type_is_modified;
  }


  /** 
   * <em>replies_number</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getRepliesNumber()
  {
    return replies_number;
  }


  /** 
   * <em>replies_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepliesNumberIsSet() {
    return replies_number_is_set;
  }


  /** 
   * <em>replies_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepliesNumberIsModified() {
    return replies_number_is_modified;
  }


  /** 
   * <em>avg_px</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAvgPx()
  {
    return ( avg_px==null? ((double)0): avg_px.doubleValue() );
  }


  /** 
   * <em>avg_px</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAvgPxIsNull()
  {
    return avg_px == null;
  }


  /** 
   * <em>avg_px</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAvgPxIsSet() {
    return avg_px_is_set;
  }


  /** 
   * <em>avg_px</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAvgPxIsModified() {
    return avg_px_is_modified;
  }


  /** 
   * <em>cl_ord_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getClOrdId()
  {
    return cl_ord_id;
  }


  /** 
   * <em>cl_ord_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClOrdIdIsSet() {
    return cl_ord_id_is_set;
  }


  /** 
   * <em>cl_ord_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClOrdIdIsModified() {
    return cl_ord_id_is_modified;
  }


  /** 
   * <em>cum_qty</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCumQty()
  {
    return ( cum_qty==null? ((double)0): cum_qty.doubleValue() );
  }


  /** 
   * <em>cum_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCumQtyIsNull()
  {
    return cum_qty == null;
  }


  /** 
   * <em>cum_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCumQtyIsSet() {
    return cum_qty_is_set;
  }


  /** 
   * <em>cum_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCumQtyIsModified() {
    return cum_qty_is_modified;
  }


  /** 
   * <em>exec_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExecId()
  {
    return exec_id;
  }


  /** 
   * <em>exec_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecIdIsSet() {
    return exec_id_is_set;
  }


  /** 
   * <em>exec_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecIdIsModified() {
    return exec_id_is_modified;
  }


  /** 
   * <em>exec_ref_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExecRefId()
  {
    return exec_ref_id;
  }


  /** 
   * <em>exec_ref_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecRefIdIsSet() {
    return exec_ref_id_is_set;
  }


  /** 
   * <em>exec_ref_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecRefIdIsModified() {
    return exec_ref_id_is_modified;
  }


  /** 
   * <em>exec_trans_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExecTransType()
  {
    return exec_trans_type;
  }


  /** 
   * <em>exec_trans_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecTransTypeIsSet() {
    return exec_trans_type_is_set;
  }


  /** 
   * <em>exec_trans_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecTransTypeIsModified() {
    return exec_trans_type_is_modified;
  }


  /** 
   * <em>last_px</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLastPx()
  {
    return ( last_px==null? ((double)0): last_px.doubleValue() );
  }


  /** 
   * <em>last_px</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLastPxIsNull()
  {
    return last_px == null;
  }


  /** 
   * <em>last_px</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastPxIsSet() {
    return last_px_is_set;
  }


  /** 
   * <em>last_px</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastPxIsModified() {
    return last_px_is_modified;
  }


  /** 
   * <em>last_shares</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLastShares()
  {
    return ( last_shares==null? ((double)0): last_shares.doubleValue() );
  }


  /** 
   * <em>last_shares</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLastSharesIsNull()
  {
    return last_shares == null;
  }


  /** 
   * <em>last_shares</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastSharesIsSet() {
    return last_shares_is_set;
  }


  /** 
   * <em>last_shares</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastSharesIsModified() {
    return last_shares_is_modified;
  }


  /** 
   * <em>order_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderId()
  {
    return order_id;
  }


  /** 
   * <em>order_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderIdIsSet() {
    return order_id_is_set;
  }


  /** 
   * <em>order_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderIdIsModified() {
    return order_id_is_modified;
  }


  /** 
   * <em>order_qty</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOrderQty()
  {
    return ( order_qty==null? ((double)0): order_qty.doubleValue() );
  }


  /** 
   * <em>order_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrderQtyIsNull()
  {
    return order_qty == null;
  }


  /** 
   * <em>order_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderQtyIsSet() {
    return order_qty_is_set;
  }


  /** 
   * <em>order_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderQtyIsModified() {
    return order_qty_is_modified;
  }


  /** 
   * <em>ord_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrdStatus()
  {
    return ord_status;
  }


  /** 
   * <em>ord_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdStatusIsSet() {
    return ord_status_is_set;
  }


  /** 
   * <em>ord_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdStatusIsModified() {
    return ord_status_is_modified;
  }


  /** 
   * <em>ord_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrdType()
  {
    return ord_type;
  }


  /** 
   * <em>ord_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdTypeIsSet() {
    return ord_type_is_set;
  }


  /** 
   * <em>ord_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdTypeIsModified() {
    return ord_type_is_modified;
  }


  /** 
   * <em>orig_cl_ord_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrigClOrdId()
  {
    return orig_cl_ord_id;
  }


  /** 
   * <em>orig_cl_ord_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrigClOrdIdIsSet() {
    return orig_cl_ord_id_is_set;
  }


  /** 
   * <em>orig_cl_ord_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrigClOrdIdIsModified() {
    return orig_cl_ord_id_is_modified;
  }


  /** 
   * <em>price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPrice()
  {
    return ( price==null? ((double)0): price.doubleValue() );
  }


  /** 
   * <em>price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPriceIsNull()
  {
    return price == null;
  }


  /** 
   * <em>price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceIsSet() {
    return price_is_set;
  }


  /** 
   * <em>price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceIsModified() {
    return price_is_modified;
  }


  /** 
   * <em>order_capacity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderCapacity()
  {
    return order_capacity;
  }


  /** 
   * <em>order_capacity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCapacityIsSet() {
    return order_capacity_is_set;
  }


  /** 
   * <em>order_capacity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCapacityIsModified() {
    return order_capacity_is_modified;
  }


  /** 
   * <em>side</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSide()
  {
    return side;
  }


  /** 
   * <em>side</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSideIsSet() {
    return side_is_set;
  }


  /** 
   * <em>side</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSideIsModified() {
    return side_is_modified;
  }


  /** 
   * <em>symbol</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSymbol()
  {
    return symbol;
  }


  /** 
   * <em>symbol</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSymbolIsSet() {
    return symbol_is_set;
  }


  /** 
   * <em>symbol</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSymbolIsModified() {
    return symbol_is_modified;
  }


  /** 
   * <em>text</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getText()
  {
    return text;
  }


  /** 
   * <em>text</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTextIsSet() {
    return text_is_set;
  }


  /** 
   * <em>text</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTextIsModified() {
    return text_is_modified;
  }


  /** 
   * <em>time_in_force</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTimeInForce()
  {
    return time_in_force;
  }


  /** 
   * <em>time_in_force</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTimeInForceIsSet() {
    return time_in_force_is_set;
  }


  /** 
   * <em>time_in_force</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTimeInForceIsModified() {
    return time_in_force_is_modified;
  }


  /** 
   * <em>transact_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTransactTime()
  {
    return transact_time;
  }


  /** 
   * <em>transact_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactTimeIsSet() {
    return transact_time_is_set;
  }


  /** 
   * <em>transact_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactTimeIsModified() {
    return transact_time_is_modified;
  }


  /** 
   * <em>ex_destination</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExDestination()
  {
    return ex_destination;
  }


  /** 
   * <em>ex_destination</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExDestinationIsSet() {
    return ex_destination_is_set;
  }


  /** 
   * <em>ex_destination</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExDestinationIsModified() {
    return ex_destination_is_modified;
  }


  /** 
   * <em>ord_rej_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrdRejReason()
  {
    return ord_rej_reason;
  }


  /** 
   * <em>ord_rej_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdRejReasonIsSet() {
    return ord_rej_reason_is_set;
  }


  /** 
   * <em>ord_rej_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdRejReasonIsModified() {
    return ord_rej_reason_is_modified;
  }


  /** 
   * <em>cxl_rej_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCxlRejReason()
  {
    return cxl_rej_reason;
  }


  /** 
   * <em>cxl_rej_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCxlRejReasonIsSet() {
    return cxl_rej_reason_is_set;
  }


  /** 
   * <em>cxl_rej_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCxlRejReasonIsModified() {
    return cxl_rej_reason_is_modified;
  }


  /** 
   * <em>cxl_rej_response_to</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCxlRejResponseTo()
  {
    return cxl_rej_response_to;
  }


  /** 
   * <em>cxl_rej_response_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCxlRejResponseToIsSet() {
    return cxl_rej_response_to_is_set;
  }


  /** 
   * <em>cxl_rej_response_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCxlRejResponseToIsModified() {
    return cxl_rej_response_to_is_modified;
  }


  /** 
   * <em>exec_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExecType()
  {
    return exec_type;
  }


  /** 
   * <em>exec_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecTypeIsSet() {
    return exec_type_is_set;
  }


  /** 
   * <em>exec_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecTypeIsModified() {
    return exec_type_is_modified;
  }


  /** 
   * <em>leaves_qty</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLeavesQty()
  {
    return ( leaves_qty==null? ((double)0): leaves_qty.doubleValue() );
  }


  /** 
   * <em>leaves_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLeavesQtyIsNull()
  {
    return leaves_qty == null;
  }


  /** 
   * <em>leaves_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLeavesQtyIsSet() {
    return leaves_qty_is_set;
  }


  /** 
   * <em>leaves_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLeavesQtyIsModified() {
    return leaves_qty_is_modified;
  }


  /** 
   * <em>msg_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMsgId()
  {
    return msg_id;
  }


  /** 
   * <em>msg_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMsgIdIsSet() {
    return msg_id_is_set;
  }


  /** 
   * <em>msg_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMsgIdIsModified() {
    return msg_id_is_modified;
  }


  /** 
   * <em>account</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccount()
  {
    return account;
  }


  /** 
   * <em>account</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIsSet() {
    return account_is_set;
  }


  /** 
   * <em>account</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIsModified() {
    return account_is_modified;
  }


  /** 
   * <em>client_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getClientId()
  {
    return client_id;
  }


  /** 
   * <em>client_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClientIdIsSet() {
    return client_id_is_set;
  }


  /** 
   * <em>client_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClientIdIsModified() {
    return client_id_is_modified;
  }


  /** 
   * <em>trade_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTradeDate()
  {
    return trade_date;
  }


  /** 
   * <em>trade_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeDateIsSet() {
    return trade_date_is_set;
  }


  /** 
   * <em>trade_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeDateIsModified() {
    return trade_date_is_modified;
  }


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsModified() {
    return status_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new FixRcvdQPK(queue_id);
  }


  /** 
   * <em>queue_id</em>カラムの値を設定します。 
   *
   * @@param p_queueId <em>queue_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setQueueId( long p_queueId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    queue_id = p_queueId;
    queue_id_is_set = true;
    queue_id_is_modified = true;
  }


  /** 
   * <em>session_id</em>カラムの値を設定します。 
   *
   * @@param p_sessionId <em>session_id</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setSessionId( int p_sessionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    session_id = p_sessionId;
    session_id_is_set = true;
    session_id_is_modified = true;
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>branch_code</em>カラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>account_code</em>カラムの値を設定します。 
   *
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>account_id</em>カラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = new Long(p_accountId);
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_id</em>カラムに値を設定します。 
   */
  public final void setAccountId( Long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>order_request_number</em>カラムの値を設定します。 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setOrderRequestNumber( String p_orderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number = p_orderRequestNumber;
    order_request_number_is_set = true;
    order_request_number_is_modified = true;
  }


  /** 
   * <em>order_action_serial_no</em>カラムの値を設定します。 
   *
   * @@param p_orderActionSerialNo <em>order_action_serial_no</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setOrderActionSerialNo( int p_orderActionSerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_action_serial_no = p_orderActionSerialNo;
    order_action_serial_no_is_set = true;
    order_action_serial_no_is_modified = true;
  }


  /** 
   * <em>product_type</em>カラムの値を設定します。 
   *
   * @@param p_productType <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public final void setProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_type = p_productType;
    product_type_is_set = true;
    product_type_is_modified = true;
  }


  /** 
   * <em>op_type</em>カラムの値を設定します。 
   *
   * @@param p_opType <em>op_type</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setOpType( int p_opType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    op_type = p_opType;
    op_type_is_set = true;
    op_type_is_modified = true;
  }


  /** 
   * <em>replies_type</em>カラムの値を設定します。 
   *
   * @@param p_repliesType <em>replies_type</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setRepliesType( String p_repliesType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    replies_type = p_repliesType;
    replies_type_is_set = true;
    replies_type_is_modified = true;
  }


  /** 
   * <em>replies_number</em>カラムの値を設定します。 
   *
   * @@param p_repliesNumber <em>replies_number</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setRepliesNumber( long p_repliesNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    replies_number = p_repliesNumber;
    replies_number_is_set = true;
    replies_number_is_modified = true;
  }


  /** 
   * <em>avg_px</em>カラムの値を設定します。 
   *
   * @@param p_avgPx <em>avg_px</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAvgPx( double p_avgPx )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    avg_px = new Double(p_avgPx);
    avg_px_is_set = true;
    avg_px_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>avg_px</em>カラムに値を設定します。 
   */
  public final void setAvgPx( Double p_avgPx )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    avg_px = p_avgPx;
    avg_px_is_set = true;
    avg_px_is_modified = true;
  }


  /** 
   * <em>cl_ord_id</em>カラムの値を設定します。 
   *
   * @@param p_clOrdId <em>cl_ord_id</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setClOrdId( String p_clOrdId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cl_ord_id = p_clOrdId;
    cl_ord_id_is_set = true;
    cl_ord_id_is_modified = true;
  }


  /** 
   * <em>cum_qty</em>カラムの値を設定します。 
   *
   * @@param p_cumQty <em>cum_qty</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCumQty( double p_cumQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cum_qty = new Double(p_cumQty);
    cum_qty_is_set = true;
    cum_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cum_qty</em>カラムに値を設定します。 
   */
  public final void setCumQty( Double p_cumQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cum_qty = p_cumQty;
    cum_qty_is_set = true;
    cum_qty_is_modified = true;
  }


  /** 
   * <em>exec_id</em>カラムの値を設定します。 
   *
   * @@param p_execId <em>exec_id</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setExecId( String p_execId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_id = p_execId;
    exec_id_is_set = true;
    exec_id_is_modified = true;
  }


  /** 
   * <em>exec_ref_id</em>カラムの値を設定します。 
   *
   * @@param p_execRefId <em>exec_ref_id</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setExecRefId( String p_execRefId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_ref_id = p_execRefId;
    exec_ref_id_is_set = true;
    exec_ref_id_is_modified = true;
  }


  /** 
   * <em>exec_trans_type</em>カラムの値を設定します。 
   *
   * @@param p_execTransType <em>exec_trans_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExecTransType( String p_execTransType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_trans_type = p_execTransType;
    exec_trans_type_is_set = true;
    exec_trans_type_is_modified = true;
  }


  /** 
   * <em>last_px</em>カラムの値を設定します。 
   *
   * @@param p_lastPx <em>last_px</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLastPx( double p_lastPx )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_px = new Double(p_lastPx);
    last_px_is_set = true;
    last_px_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>last_px</em>カラムに値を設定します。 
   */
  public final void setLastPx( Double p_lastPx )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_px = p_lastPx;
    last_px_is_set = true;
    last_px_is_modified = true;
  }


  /** 
   * <em>last_shares</em>カラムの値を設定します。 
   *
   * @@param p_lastShares <em>last_shares</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLastShares( double p_lastShares )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_shares = new Double(p_lastShares);
    last_shares_is_set = true;
    last_shares_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>last_shares</em>カラムに値を設定します。 
   */
  public final void setLastShares( Double p_lastShares )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_shares = p_lastShares;
    last_shares_is_set = true;
    last_shares_is_modified = true;
  }


  /** 
   * <em>order_id</em>カラムの値を設定します。 
   *
   * @@param p_orderId <em>order_id</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setOrderId( String p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
    order_id_is_set = true;
    order_id_is_modified = true;
  }


  /** 
   * <em>order_qty</em>カラムの値を設定します。 
   *
   * @@param p_orderQty <em>order_qty</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOrderQty( double p_orderQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_qty = new Double(p_orderQty);
    order_qty_is_set = true;
    order_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>order_qty</em>カラムに値を設定します。 
   */
  public final void setOrderQty( Double p_orderQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_qty = p_orderQty;
    order_qty_is_set = true;
    order_qty_is_modified = true;
  }


  /** 
   * <em>ord_status</em>カラムの値を設定します。 
   *
   * @@param p_ordStatus <em>ord_status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrdStatus( String p_ordStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ord_status = p_ordStatus;
    ord_status_is_set = true;
    ord_status_is_modified = true;
  }


  /** 
   * <em>ord_type</em>カラムの値を設定します。 
   *
   * @@param p_ordType <em>ord_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrdType( String p_ordType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ord_type = p_ordType;
    ord_type_is_set = true;
    ord_type_is_modified = true;
  }


  /** 
   * <em>orig_cl_ord_id</em>カラムの値を設定します。 
   *
   * @@param p_origClOrdId <em>orig_cl_ord_id</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setOrigClOrdId( String p_origClOrdId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    orig_cl_ord_id = p_origClOrdId;
    orig_cl_ord_id_is_set = true;
    orig_cl_ord_id_is_modified = true;
  }


  /** 
   * <em>price</em>カラムの値を設定します。 
   *
   * @@param p_price <em>price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPrice( double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price = new Double(p_price);
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>price</em>カラムに値を設定します。 
   */
  public final void setPrice( Double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    price = p_price;
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * <em>order_capacity</em>カラムの値を設定します。 
   *
   * @@param p_orderCapacity <em>order_capacity</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderCapacity( String p_orderCapacity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_capacity = p_orderCapacity;
    order_capacity_is_set = true;
    order_capacity_is_modified = true;
  }


  /** 
   * <em>side</em>カラムの値を設定します。 
   *
   * @@param p_side <em>side</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSide( String p_side )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    side = p_side;
    side_is_set = true;
    side_is_modified = true;
  }


  /** 
   * <em>symbol</em>カラムの値を設定します。 
   *
   * @@param p_symbol <em>symbol</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setSymbol( String p_symbol )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    symbol = p_symbol;
    symbol_is_set = true;
    symbol_is_modified = true;
  }


  /** 
   * <em>text</em>カラムの値を設定します。 
   *
   * @@param p_text <em>text</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setText( String p_text )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    text = p_text;
    text_is_set = true;
    text_is_modified = true;
  }


  /** 
   * <em>time_in_force</em>カラムの値を設定します。 
   *
   * @@param p_timeInForce <em>time_in_force</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTimeInForce( String p_timeInForce )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    time_in_force = p_timeInForce;
    time_in_force_is_set = true;
    time_in_force_is_modified = true;
  }


  /** 
   * <em>transact_time</em>カラムの値を設定します。 
   *
   * @@param p_transactTime <em>transact_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTransactTime( java.sql.Timestamp p_transactTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transact_time = p_transactTime;
    transact_time_is_set = true;
    transact_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>transact_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTransactTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transact_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    transact_time_is_set = true;
    transact_time_is_modified = true;
  }


  /** 
   * <em>ex_destination</em>カラムの値を設定します。 
   *
   * @@param p_exDestination <em>ex_destination</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExDestination( String p_exDestination )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ex_destination = p_exDestination;
    ex_destination_is_set = true;
    ex_destination_is_modified = true;
  }


  /** 
   * <em>ord_rej_reason</em>カラムの値を設定します。 
   *
   * @@param p_ordRejReason <em>ord_rej_reason</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setOrdRejReason( String p_ordRejReason )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ord_rej_reason = p_ordRejReason;
    ord_rej_reason_is_set = true;
    ord_rej_reason_is_modified = true;
  }


  /** 
   * <em>cxl_rej_reason</em>カラムの値を設定します。 
   *
   * @@param p_cxlRejReason <em>cxl_rej_reason</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setCxlRejReason( String p_cxlRejReason )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cxl_rej_reason = p_cxlRejReason;
    cxl_rej_reason_is_set = true;
    cxl_rej_reason_is_modified = true;
  }


  /** 
   * <em>cxl_rej_response_to</em>カラムの値を設定します。 
   *
   * @@param p_cxlRejResponseTo <em>cxl_rej_response_to</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCxlRejResponseTo( String p_cxlRejResponseTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cxl_rej_response_to = p_cxlRejResponseTo;
    cxl_rej_response_to_is_set = true;
    cxl_rej_response_to_is_modified = true;
  }


  /** 
   * <em>exec_type</em>カラムの値を設定します。 
   *
   * @@param p_execType <em>exec_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExecType( String p_execType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_type = p_execType;
    exec_type_is_set = true;
    exec_type_is_modified = true;
  }


  /** 
   * <em>leaves_qty</em>カラムの値を設定します。 
   *
   * @@param p_leavesQty <em>leaves_qty</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLeavesQty( double p_leavesQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    leaves_qty = new Double(p_leavesQty);
    leaves_qty_is_set = true;
    leaves_qty_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>leaves_qty</em>カラムに値を設定します。 
   */
  public final void setLeavesQty( Double p_leavesQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    leaves_qty = p_leavesQty;
    leaves_qty_is_set = true;
    leaves_qty_is_modified = true;
  }


  /** 
   * <em>msg_id</em>カラムの値を設定します。 
   *
   * @@param p_msgId <em>msg_id</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setMsgId( String p_msgId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    msg_id = p_msgId;
    msg_id_is_set = true;
    msg_id_is_modified = true;
  }


  /** 
   * <em>account</em>カラムの値を設定します。 
   *
   * @@param p_account <em>account</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setAccount( String p_account )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account = p_account;
    account_is_set = true;
    account_is_modified = true;
  }


  /** 
   * <em>client_id</em>カラムの値を設定します。 
   *
   * @@param p_clientId <em>client_id</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setClientId( String p_clientId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    client_id = p_clientId;
    client_id_is_set = true;
    client_id_is_modified = true;
  }


  /** 
   * <em>trade_date</em>カラムの値を設定します。 
   *
   * @@param p_tradeDate <em>trade_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTradeDate( java.sql.Timestamp p_tradeDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_date = p_tradeDate;
    trade_date_is_set = true;
    trade_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>trade_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTradeDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trade_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    trade_date_is_set = true;
    trade_date_is_modified = true;
  }


  /** 
   * <em>status</em>カラムの値を設定します。 
   *
   * @@param p_status <em>status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStatus( String p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("account_id") ) {
                    return this.account_id;
                }
                else if ( name.equals("avg_px") ) {
                    return this.avg_px;
                }
                else if ( name.equals("account") ) {
                    return this.account;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("cl_ord_id") ) {
                    return this.cl_ord_id;
                }
                else if ( name.equals("cum_qty") ) {
                    return this.cum_qty;
                }
                else if ( name.equals("cxl_rej_reason") ) {
                    return this.cxl_rej_reason;
                }
                else if ( name.equals("cxl_rej_response_to") ) {
                    return this.cxl_rej_response_to;
                }
                else if ( name.equals("client_id") ) {
                    return this.client_id;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("exec_id") ) {
                    return this.exec_id;
                }
                else if ( name.equals("exec_ref_id") ) {
                    return this.exec_ref_id;
                }
                else if ( name.equals("exec_trans_type") ) {
                    return this.exec_trans_type;
                }
                else if ( name.equals("ex_destination") ) {
                    return this.ex_destination;
                }
                else if ( name.equals("exec_type") ) {
                    return this.exec_type;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_px") ) {
                    return this.last_px;
                }
                else if ( name.equals("last_shares") ) {
                    return this.last_shares;
                }
                else if ( name.equals("leaves_qty") ) {
                    return this.leaves_qty;
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("msg_id") ) {
                    return this.msg_id;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_action_serial_no") ) {
                    return new Integer( order_action_serial_no );
                }
                else if ( name.equals("op_type") ) {
                    return new Integer( op_type );
                }
                else if ( name.equals("order_id") ) {
                    return this.order_id;
                }
                else if ( name.equals("order_qty") ) {
                    return this.order_qty;
                }
                else if ( name.equals("ord_status") ) {
                    return this.ord_status;
                }
                else if ( name.equals("ord_type") ) {
                    return this.ord_type;
                }
                else if ( name.equals("orig_cl_ord_id") ) {
                    return this.orig_cl_ord_id;
                }
                else if ( name.equals("order_capacity") ) {
                    return this.order_capacity;
                }
                else if ( name.equals("ord_rej_reason") ) {
                    return this.ord_rej_reason;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("price") ) {
                    return this.price;
                }
                break;
            case 'q':
                if ( name.equals("queue_id") ) {
                    return new Long( queue_id );
                }
                break;
            case 'r':
                if ( name.equals("replies_type") ) {
                    return this.replies_type;
                }
                else if ( name.equals("replies_number") ) {
                    return new Long( replies_number );
                }
                break;
            case 's':
                if ( name.equals("session_id") ) {
                    return new Integer( session_id );
                }
                else if ( name.equals("side") ) {
                    return this.side;
                }
                else if ( name.equals("symbol") ) {
                    return this.symbol;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("text") ) {
                    return this.text;
                }
                else if ( name.equals("time_in_force") ) {
                    return this.time_in_force;
                }
                else if ( name.equals("transact_time") ) {
                    return this.transact_time;
                }
                else if ( name.equals("trade_date") ) {
                    return this.trade_date;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("account_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = (Long) value;
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("avg_px") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'avg_px' must be Double: '"+value+"' is not." );
                    this.avg_px = (Double) value;
                    if (this.avg_px_is_set)
                        this.avg_px_is_modified = true;
                    this.avg_px_is_set = true;
                    return;
                }
                else if ( name.equals("account") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account' must be String: '"+value+"' is not." );
                    this.account = (String) value;
                    if (this.account_is_set)
                        this.account_is_modified = true;
                    this.account_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("cl_ord_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cl_ord_id' must be String: '"+value+"' is not." );
                    this.cl_ord_id = (String) value;
                    if (this.cl_ord_id_is_set)
                        this.cl_ord_id_is_modified = true;
                    this.cl_ord_id_is_set = true;
                    return;
                }
                else if ( name.equals("cum_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cum_qty' must be Double: '"+value+"' is not." );
                    this.cum_qty = (Double) value;
                    if (this.cum_qty_is_set)
                        this.cum_qty_is_modified = true;
                    this.cum_qty_is_set = true;
                    return;
                }
                else if ( name.equals("cxl_rej_reason") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cxl_rej_reason' must be String: '"+value+"' is not." );
                    this.cxl_rej_reason = (String) value;
                    if (this.cxl_rej_reason_is_set)
                        this.cxl_rej_reason_is_modified = true;
                    this.cxl_rej_reason_is_set = true;
                    return;
                }
                else if ( name.equals("cxl_rej_response_to") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cxl_rej_response_to' must be String: '"+value+"' is not." );
                    this.cxl_rej_response_to = (String) value;
                    if (this.cxl_rej_response_to_is_set)
                        this.cxl_rej_response_to_is_modified = true;
                    this.cxl_rej_response_to_is_set = true;
                    return;
                }
                else if ( name.equals("client_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'client_id' must be String: '"+value+"' is not." );
                    this.client_id = (String) value;
                    if (this.client_id_is_set)
                        this.client_id_is_modified = true;
                    this.client_id_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("exec_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exec_id' must be String: '"+value+"' is not." );
                    this.exec_id = (String) value;
                    if (this.exec_id_is_set)
                        this.exec_id_is_modified = true;
                    this.exec_id_is_set = true;
                    return;
                }
                else if ( name.equals("exec_ref_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exec_ref_id' must be String: '"+value+"' is not." );
                    this.exec_ref_id = (String) value;
                    if (this.exec_ref_id_is_set)
                        this.exec_ref_id_is_modified = true;
                    this.exec_ref_id_is_set = true;
                    return;
                }
                else if ( name.equals("exec_trans_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exec_trans_type' must be String: '"+value+"' is not." );
                    this.exec_trans_type = (String) value;
                    if (this.exec_trans_type_is_set)
                        this.exec_trans_type_is_modified = true;
                    this.exec_trans_type_is_set = true;
                    return;
                }
                else if ( name.equals("ex_destination") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ex_destination' must be String: '"+value+"' is not." );
                    this.ex_destination = (String) value;
                    if (this.ex_destination_is_set)
                        this.ex_destination_is_modified = true;
                    this.ex_destination_is_set = true;
                    return;
                }
                else if ( name.equals("exec_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exec_type' must be String: '"+value+"' is not." );
                    this.exec_type = (String) value;
                    if (this.exec_type_is_set)
                        this.exec_type_is_modified = true;
                    this.exec_type_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_px") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'last_px' must be Double: '"+value+"' is not." );
                    this.last_px = (Double) value;
                    if (this.last_px_is_set)
                        this.last_px_is_modified = true;
                    this.last_px_is_set = true;
                    return;
                }
                else if ( name.equals("last_shares") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'last_shares' must be Double: '"+value+"' is not." );
                    this.last_shares = (Double) value;
                    if (this.last_shares_is_set)
                        this.last_shares_is_modified = true;
                    this.last_shares_is_set = true;
                    return;
                }
                else if ( name.equals("leaves_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'leaves_qty' must be Double: '"+value+"' is not." );
                    this.leaves_qty = (Double) value;
                    if (this.leaves_qty_is_set)
                        this.leaves_qty_is_modified = true;
                    this.leaves_qty_is_set = true;
                    return;
                }
                else if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("msg_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'msg_id' must be String: '"+value+"' is not." );
                    this.msg_id = (String) value;
                    if (this.msg_id_is_set)
                        this.msg_id_is_modified = true;
                    this.msg_id_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
                    return;
                }
                else if ( name.equals("order_action_serial_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'order_action_serial_no' must be Integer: '"+value+"' is not." );
                    this.order_action_serial_no = ((Integer) value).intValue();
                    if (this.order_action_serial_no_is_set)
                        this.order_action_serial_no_is_modified = true;
                    this.order_action_serial_no_is_set = true;
                    return;
                }
                else if ( name.equals("op_type") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'op_type' must be Integer: '"+value+"' is not." );
                    this.op_type = ((Integer) value).intValue();
                    if (this.op_type_is_set)
                        this.op_type_is_modified = true;
                    this.op_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_id' must be String: '"+value+"' is not." );
                    this.order_id = (String) value;
                    if (this.order_id_is_set)
                        this.order_id_is_modified = true;
                    this.order_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'order_qty' must be Double: '"+value+"' is not." );
                    this.order_qty = (Double) value;
                    if (this.order_qty_is_set)
                        this.order_qty_is_modified = true;
                    this.order_qty_is_set = true;
                    return;
                }
                else if ( name.equals("ord_status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ord_status' must be String: '"+value+"' is not." );
                    this.ord_status = (String) value;
                    if (this.ord_status_is_set)
                        this.ord_status_is_modified = true;
                    this.ord_status_is_set = true;
                    return;
                }
                else if ( name.equals("ord_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ord_type' must be String: '"+value+"' is not." );
                    this.ord_type = (String) value;
                    if (this.ord_type_is_set)
                        this.ord_type_is_modified = true;
                    this.ord_type_is_set = true;
                    return;
                }
                else if ( name.equals("orig_cl_ord_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'orig_cl_ord_id' must be String: '"+value+"' is not." );
                    this.orig_cl_ord_id = (String) value;
                    if (this.orig_cl_ord_id_is_set)
                        this.orig_cl_ord_id_is_modified = true;
                    this.orig_cl_ord_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_capacity") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_capacity' must be String: '"+value+"' is not." );
                    this.order_capacity = (String) value;
                    if (this.order_capacity_is_set)
                        this.order_capacity_is_modified = true;
                    this.order_capacity_is_set = true;
                    return;
                }
                else if ( name.equals("ord_rej_reason") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ord_rej_reason' must be String: '"+value+"' is not." );
                    this.ord_rej_reason = (String) value;
                    if (this.ord_rej_reason_is_set)
                        this.ord_rej_reason_is_modified = true;
                    this.ord_rej_reason_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                else if ( name.equals("price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'price' must be Double: '"+value+"' is not." );
                    this.price = (Double) value;
                    if (this.price_is_set)
                        this.price_is_modified = true;
                    this.price_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("queue_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'queue_id' must be Long: '"+value+"' is not." );
                    this.queue_id = ((Long) value).longValue();
                    if (this.queue_id_is_set)
                        this.queue_id_is_modified = true;
                    this.queue_id_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("replies_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'replies_type' must be String: '"+value+"' is not." );
                    this.replies_type = (String) value;
                    if (this.replies_type_is_set)
                        this.replies_type_is_modified = true;
                    this.replies_type_is_set = true;
                    return;
                }
                else if ( name.equals("replies_number") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'replies_number' must be Long: '"+value+"' is not." );
                    this.replies_number = ((Long) value).longValue();
                    if (this.replies_number_is_set)
                        this.replies_number_is_modified = true;
                    this.replies_number_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("session_id") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'session_id' must be Integer: '"+value+"' is not." );
                    this.session_id = ((Integer) value).intValue();
                    if (this.session_id_is_set)
                        this.session_id_is_modified = true;
                    this.session_id_is_set = true;
                    return;
                }
                else if ( name.equals("side") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'side' must be String: '"+value+"' is not." );
                    this.side = (String) value;
                    if (this.side_is_set)
                        this.side_is_modified = true;
                    this.side_is_set = true;
                    return;
                }
                else if ( name.equals("symbol") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'symbol' must be String: '"+value+"' is not." );
                    this.symbol = (String) value;
                    if (this.symbol_is_set)
                        this.symbol_is_modified = true;
                    this.symbol_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("text") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'text' must be String: '"+value+"' is not." );
                    this.text = (String) value;
                    if (this.text_is_set)
                        this.text_is_modified = true;
                    this.text_is_set = true;
                    return;
                }
                else if ( name.equals("time_in_force") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'time_in_force' must be String: '"+value+"' is not." );
                    this.time_in_force = (String) value;
                    if (this.time_in_force_is_set)
                        this.time_in_force_is_modified = true;
                    this.time_in_force_is_set = true;
                    return;
                }
                else if ( name.equals("transact_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'transact_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.transact_time = (java.sql.Timestamp) value;
                    if (this.transact_time_is_set)
                        this.transact_time_is_modified = true;
                    this.transact_time_is_set = true;
                    return;
                }
                else if ( name.equals("trade_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'trade_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.trade_date = (java.sql.Timestamp) value;
                    if (this.trade_date_is_set)
                        this.trade_date_is_modified = true;
                    this.trade_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
