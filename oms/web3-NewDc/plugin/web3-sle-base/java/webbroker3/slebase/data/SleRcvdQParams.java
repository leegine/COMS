head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.28.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleRcvdQParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.slebase.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/** 
 * sle_rcvd_qテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SleRcvdQRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SleRcvdQParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SleRcvdQParams}が{@@link SleRcvdQRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleRcvdQPK 
 * @@see SleRcvdQRow 
 */
public class SleRcvdQParams extends Params implements SleRcvdQRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "sle_rcvd_q";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SleRcvdQRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SleRcvdQRow.TYPE;
  }


  /** 
   * <em>queue_id</em>コラムの値 
   */
  public  long  queue_id;

  /** 
   * <em>xblocks_product_type</em>コラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  xblocks_product_type;

  /** 
   * <em>replies_type</em>コラムの値 
   */
  public  String  replies_type;

  /** 
   * <em>replies_number</em>コラムの値 
   */
  public  Long  replies_number;

  /** 
   * <em>replies_index</em>コラムの値 
   */
  public  Long  replies_index;

  /** 
   * <em>sub_status</em>コラムの値 
   */
  public  String  sub_status;

  /** 
   * <em>internal_ref</em>コラムの値 
   */
  public  String  internal_ref;

  /** 
   * <em>exchange_no</em>コラムの値 
   */
  public  String  exchange_no;

  /** 
   * <em>trade_number</em>コラムの値 
   */
  public  String  trade_number;

  /** 
   * <em>gl_id</em>コラムの値 
   */
  public  String  gl_id;

  /** 
   * <em>stock_code</em>コラムの値 
   */
  public  String  stock_code;

  /** 
   * <em>side</em>コラムの値 
   */
  public  Integer  side;

  /** 
   * <em>modality</em>コラムの値 
   */
  public  String  modality;

  /** 
   * <em>price</em>コラムの値 
   */
  public  Double  price;

  /** 
   * <em>quantity</em>コラムの値 
   */
  public  Double  quantity;

  /** 
   * <em>trading_phase</em>コラムの値 
   */
  public  String  trading_phase;

  /** 
   * <em>exec_price</em>コラムの値 
   */
  public  Double  exec_price;

  /** 
   * <em>avg_price</em>コラムの値 
   */
  public  Double  avg_price;

  /** 
   * <em>exec_qty</em>コラムの値 
   */
  public  Double  exec_qty;

  /** 
   * <em>remaining_qty</em>コラムの値 
   */
  public  Double  remaining_qty;

  /** 
   * <em>number_of_trades</em>コラムの値 
   */
  public  Long  number_of_trades;

  /** 
   * <em>order_time</em>コラムの値 
   */
  public  String  order_time;

  /** 
   * <em>trade_booking_time</em>コラムの値 
   */
  public  String  trade_booking_time;

  /** 
   * <em>exec_timestamp</em>コラムの値 
   */
  public  java.sql.Timestamp  exec_timestamp;

  /** 
   * <em>order_emp_code</em>コラムの値 
   */
  public  String  order_emp_code;

  /** 
   * <em>execution_date</em>コラムの値 
   */
  public  java.sql.Timestamp  execution_date;

  /** 
   * <em>f_delivery_date</em>コラムの値 
   */
  public  java.sql.Timestamp  f_delivery_date;

  /** 
   * <em>fx_rate</em>コラムの値 
   */
  public  Double  fx_rate;

  /** 
   * <em>exec_serial_no</em>コラムの値 
   */
  public  String  exec_serial_no;

  /** 
   * <em>route_div</em>コラムの値 
   */
  public  String  route_div;

  /** 
   * <em>ack_type</em>コラムの値 
   */
  public  Integer  ack_type;

  /** 
   * <em>ackd_command</em>コラムの値 
   */
  public  Integer  ackd_command;

  /** 
   * <em>old_qty</em>コラムの値 
   */
  public  Double  old_qty;

  /** 
   * <em>old_price</em>コラムの値 
   */
  public  Double  old_price;

  /** 
   * <em>cancelled_qty</em>コラムの値 
   */
  public  Double  cancelled_qty;

  /** 
   * <em>trigger_param</em>コラムの値 
   */
  public  String  trigger_param;

  /** 
   * <em>reject_cmd_type</em>コラムの値 
   */
  public  Integer  reject_cmd_type;

  /** 
   * <em>reject_code</em>コラムの値 
   */
  public  String  reject_code;

  /** 
   * <em>reject_time</em>コラムの値 
   */
  public  String  reject_time;

  /** 
   * <em>exchange_msg_type</em>コラムの値 
   */
  public  String  exchange_msg_type;

  /** 
   * <em>exchange_msg_code</em>コラムの値 
   */
  public  String  exchange_msg_code;

  /** 
   * <em>user_number</em>コラムの値 
   */
  public  Integer  user_number;

  /** 
   * <em>memo</em>コラムの値 
   */
  public  String  memo;

  /** 
   * <em>account_id</em>コラムの値 
   */
  public  long  account_id;

  /** 
   * <em>op_type</em>コラムの値 
   */
  public  webbroker3.slebase.enums.SleSendqOpTypeEnum  op_type;

  /** 
   * <em>accept_div</em>コラムの値 
   */
  public  String  accept_div;

  /** 
   * <em>institution_code</em>コラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>コラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>order_request_number</em>コラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>elimination_message</em>コラムの値 
   */
  public  String  elimination_message;

  /** 
   * <em>status</em>コラムの値 
   */
  public  webbroker3.slebase.enums.SleRcvdqProcStatusEnum  status;

  /** 
   * <em>last_updater</em>コラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>コラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>コラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean queue_id_is_set;
  private boolean xblocks_product_type_is_set;
  private boolean account_id_is_set;
  private boolean op_type_is_set;
  private boolean status_is_set;
  private boolean created_timestamp_is_set;
  private boolean last_updated_timestamp_is_set;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "queue_id=" + queue_id
      + "," + "xblocks_product_type=" +xblocks_product_type
      + "," + "replies_type=" +replies_type
      + "," + "replies_number=" +replies_number
      + "," + "replies_index=" +replies_index
      + "," + "sub_status=" +sub_status
      + "," + "internal_ref=" +internal_ref
      + "," + "exchange_no=" +exchange_no
      + "," + "trade_number=" +trade_number
      + "," + "gl_id=" +gl_id
      + "," + "stock_code=" +stock_code
      + "," + "side=" +side
      + "," + "modality=" +modality
      + "," + "price=" +price
      + "," + "quantity=" +quantity
      + "," + "trading_phase=" +trading_phase
      + "," + "exec_price=" +exec_price
      + "," + "avg_price=" +avg_price
      + "," + "exec_qty=" +exec_qty
      + "," + "remaining_qty=" +remaining_qty
      + "," + "number_of_trades=" +number_of_trades
      + "," + "order_time=" +order_time
      + "," + "trade_booking_time=" +trade_booking_time
      + "," + "exec_timestamp=" +exec_timestamp
      + "," + "order_emp_code=" +order_emp_code
      + "," + "execution_date=" +execution_date
      + "," + "f_delivery_date=" +f_delivery_date
      + "," + "fx_rate=" +fx_rate
      + "," + "exec_serial_no=" +exec_serial_no
      + "," + "route_div=" +route_div
      + "," + "ack_type=" +ack_type
      + "," + "ackd_command=" +ackd_command
      + "," + "old_qty=" +old_qty
      + "," + "old_price=" +old_price
      + "," + "cancelled_qty=" +cancelled_qty
      + "," + "trigger_param=" +trigger_param
      + "," + "reject_cmd_type=" +reject_cmd_type
      + "," + "reject_code=" +reject_code
      + "," + "reject_time=" +reject_time
      + "," + "exchange_msg_type=" +exchange_msg_type
      + "," + "exchange_msg_code=" +exchange_msg_code
      + "," + "user_number=" +user_number
      + "," + "memo=" +memo
      + "," + "account_id=" +account_id
      + "," + "op_type=" +op_type
      + "," + "accept_div=" +accept_div
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "order_request_number=" +order_request_number
      + "," + "elimination_message=" +elimination_message
      + "," + "status=" +status
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のSleRcvdQParamsオブジェクトを作成します。 
   */
  public SleRcvdQParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSleRcvdQRowオブジェクトの値を利用してSleRcvdQParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSleRcvdQRowオブジェクト 
   */
  public SleRcvdQParams( SleRcvdQRow p_row )
  {
    queue_id = p_row.getQueueId();
    queue_id_is_set = p_row.getQueueIdIsSet();
    xblocks_product_type = p_row.getXblocksProductType();
    xblocks_product_type_is_set = p_row.getXblocksProductTypeIsSet();
    replies_type = p_row.getRepliesType();
    if ( !p_row.getRepliesNumberIsNull() )
      replies_number = new Long( p_row.getRepliesNumber() );
    if ( !p_row.getRepliesIndexIsNull() )
      replies_index = new Long( p_row.getRepliesIndex() );
    sub_status = p_row.getSubStatus();
    internal_ref = p_row.getInternalRef();
    exchange_no = p_row.getExchangeNo();
    trade_number = p_row.getTradeNumber();
    gl_id = p_row.getGlId();
    stock_code = p_row.getStockCode();
    if ( !p_row.getSideIsNull() )
      side = new Integer( p_row.getSide() );
    modality = p_row.getModality();
    if ( !p_row.getPriceIsNull() )
      price = new Double( p_row.getPrice() );
    if ( !p_row.getQuantityIsNull() )
      quantity = new Double( p_row.getQuantity() );
    trading_phase = p_row.getTradingPhase();
    if ( !p_row.getExecPriceIsNull() )
      exec_price = new Double( p_row.getExecPrice() );
    if ( !p_row.getAvgPriceIsNull() )
      avg_price = new Double( p_row.getAvgPrice() );
    if ( !p_row.getExecQtyIsNull() )
      exec_qty = new Double( p_row.getExecQty() );
    if ( !p_row.getRemainingQtyIsNull() )
      remaining_qty = new Double( p_row.getRemainingQty() );
    if ( !p_row.getNumberOfTradesIsNull() )
      number_of_trades = new Long( p_row.getNumberOfTrades() );
    order_time = p_row.getOrderTime();
    trade_booking_time = p_row.getTradeBookingTime();
    exec_timestamp = p_row.getExecTimestamp();
    order_emp_code = p_row.getOrderEmpCode();
    execution_date = p_row.getExecutionDate();
    f_delivery_date = p_row.getFDeliveryDate();
    if ( !p_row.getFxRateIsNull() )
      fx_rate = new Double( p_row.getFxRate() );
    exec_serial_no = p_row.getExecSerialNo();
    route_div = p_row.getRouteDiv();
    if ( !p_row.getAckTypeIsNull() )
      ack_type = new Integer( p_row.getAckType() );
    if ( !p_row.getAckdCommandIsNull() )
      ackd_command = new Integer( p_row.getAckdCommand() );
    if ( !p_row.getOldQtyIsNull() )
      old_qty = new Double( p_row.getOldQty() );
    if ( !p_row.getOldPriceIsNull() )
      old_price = new Double( p_row.getOldPrice() );
    if ( !p_row.getCancelledQtyIsNull() )
      cancelled_qty = new Double( p_row.getCancelledQty() );
    trigger_param = p_row.getTriggerParam();
    if ( !p_row.getRejectCmdTypeIsNull() )
      reject_cmd_type = new Integer( p_row.getRejectCmdType() );
    reject_code = p_row.getRejectCode();
    reject_time = p_row.getRejectTime();
    exchange_msg_type = p_row.getExchangeMsgType();
    exchange_msg_code = p_row.getExchangeMsgCode();
    if ( !p_row.getUserNumberIsNull() )
      user_number = new Integer( p_row.getUserNumber() );
    memo = p_row.getMemo();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    op_type = p_row.getOpType();
    op_type_is_set = p_row.getOpTypeIsSet();
    accept_div = p_row.getAcceptDiv();
    institution_code = p_row.getInstitutionCode();
    branch_code = p_row.getBranchCode();
    order_request_number = p_row.getOrderRequestNumber();
    elimination_message = p_row.getEliminationMessage();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    last_updater = p_row.getLastUpdater();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      xblocks_product_type_is_set = true;
      account_id_is_set = true;
      op_type_is_set = true;
      status_is_set = true;
      created_timestamp_is_set = true;
      last_updated_timestamp_is_set = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof SleRcvdQRow ) )
       return false;
    return fieldsEqual( (SleRcvdQRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSleRcvdQRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SleRcvdQRow row )
  {
    if ( queue_id != row.getQueueId() )
      return false;
    if ( xblocks_product_type == null ) {
      if ( row.getXblocksProductType() != null )
        return false;
    } else if ( !xblocks_product_type.equals( row.getXblocksProductType() ) ) {
        return false;
    }
    if ( replies_type == null ) {
      if ( row.getRepliesType() != null )
        return false;
    } else if ( !replies_type.equals( row.getRepliesType() ) ) {
        return false;
    }
    if ( replies_number == null ) {
      if ( !row.getRepliesNumberIsNull() )
        return false;
    } else if ( row.getRepliesNumberIsNull() || ( replies_number.longValue() != row.getRepliesNumber() ) ) {
        return false;
    }
    if ( replies_index == null ) {
      if ( !row.getRepliesIndexIsNull() )
        return false;
    } else if ( row.getRepliesIndexIsNull() || ( replies_index.longValue() != row.getRepliesIndex() ) ) {
        return false;
    }
    if ( sub_status == null ) {
      if ( row.getSubStatus() != null )
        return false;
    } else if ( !sub_status.equals( row.getSubStatus() ) ) {
        return false;
    }
    if ( internal_ref == null ) {
      if ( row.getInternalRef() != null )
        return false;
    } else if ( !internal_ref.equals( row.getInternalRef() ) ) {
        return false;
    }
    if ( exchange_no == null ) {
      if ( row.getExchangeNo() != null )
        return false;
    } else if ( !exchange_no.equals( row.getExchangeNo() ) ) {
        return false;
    }
    if ( trade_number == null ) {
      if ( row.getTradeNumber() != null )
        return false;
    } else if ( !trade_number.equals( row.getTradeNumber() ) ) {
        return false;
    }
    if ( gl_id == null ) {
      if ( row.getGlId() != null )
        return false;
    } else if ( !gl_id.equals( row.getGlId() ) ) {
        return false;
    }
    if ( stock_code == null ) {
      if ( row.getStockCode() != null )
        return false;
    } else if ( !stock_code.equals( row.getStockCode() ) ) {
        return false;
    }
    if ( side == null ) {
      if ( !row.getSideIsNull() )
        return false;
    } else if ( row.getSideIsNull() || ( side.intValue() != row.getSide() ) ) {
        return false;
    }
    if ( modality == null ) {
      if ( row.getModality() != null )
        return false;
    } else if ( !modality.equals( row.getModality() ) ) {
        return false;
    }
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.doubleValue() != row.getPrice() ) ) {
        return false;
    }
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.doubleValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( trading_phase == null ) {
      if ( row.getTradingPhase() != null )
        return false;
    } else if ( !trading_phase.equals( row.getTradingPhase() ) ) {
        return false;
    }
    if ( exec_price == null ) {
      if ( !row.getExecPriceIsNull() )
        return false;
    } else if ( row.getExecPriceIsNull() || ( exec_price.doubleValue() != row.getExecPrice() ) ) {
        return false;
    }
    if ( avg_price == null ) {
      if ( !row.getAvgPriceIsNull() )
        return false;
    } else if ( row.getAvgPriceIsNull() || ( avg_price.doubleValue() != row.getAvgPrice() ) ) {
        return false;
    }
    if ( exec_qty == null ) {
      if ( !row.getExecQtyIsNull() )
        return false;
    } else if ( row.getExecQtyIsNull() || ( exec_qty.doubleValue() != row.getExecQty() ) ) {
        return false;
    }
    if ( remaining_qty == null ) {
      if ( !row.getRemainingQtyIsNull() )
        return false;
    } else if ( row.getRemainingQtyIsNull() || ( remaining_qty.doubleValue() != row.getRemainingQty() ) ) {
        return false;
    }
    if ( number_of_trades == null ) {
      if ( !row.getNumberOfTradesIsNull() )
        return false;
    } else if ( row.getNumberOfTradesIsNull() || ( number_of_trades.longValue() != row.getNumberOfTrades() ) ) {
        return false;
    }
    if ( order_time == null ) {
      if ( row.getOrderTime() != null )
        return false;
    } else if ( !order_time.equals( row.getOrderTime() ) ) {
        return false;
    }
    if ( trade_booking_time == null ) {
      if ( row.getTradeBookingTime() != null )
        return false;
    } else if ( !trade_booking_time.equals( row.getTradeBookingTime() ) ) {
        return false;
    }
    if ( exec_timestamp == null ) {
      if ( row.getExecTimestamp() != null )
        return false;
    } else if ( !exec_timestamp.equals( row.getExecTimestamp() ) ) {
        return false;
    }
    if ( order_emp_code == null ) {
      if ( row.getOrderEmpCode() != null )
        return false;
    } else if ( !order_emp_code.equals( row.getOrderEmpCode() ) ) {
        return false;
    }
    if ( execution_date == null ) {
      if ( row.getExecutionDate() != null )
        return false;
    } else if ( !execution_date.equals( row.getExecutionDate() ) ) {
        return false;
    }
    if ( f_delivery_date == null ) {
      if ( row.getFDeliveryDate() != null )
        return false;
    } else if ( !f_delivery_date.equals( row.getFDeliveryDate() ) ) {
        return false;
    }
    if ( fx_rate == null ) {
      if ( !row.getFxRateIsNull() )
        return false;
    } else if ( row.getFxRateIsNull() || ( fx_rate.doubleValue() != row.getFxRate() ) ) {
        return false;
    }
    if ( exec_serial_no == null ) {
      if ( row.getExecSerialNo() != null )
        return false;
    } else if ( !exec_serial_no.equals( row.getExecSerialNo() ) ) {
        return false;
    }
    if ( route_div == null ) {
      if ( row.getRouteDiv() != null )
        return false;
    } else if ( !route_div.equals( row.getRouteDiv() ) ) {
        return false;
    }
    if ( ack_type == null ) {
      if ( !row.getAckTypeIsNull() )
        return false;
    } else if ( row.getAckTypeIsNull() || ( ack_type.intValue() != row.getAckType() ) ) {
        return false;
    }
    if ( ackd_command == null ) {
      if ( !row.getAckdCommandIsNull() )
        return false;
    } else if ( row.getAckdCommandIsNull() || ( ackd_command.intValue() != row.getAckdCommand() ) ) {
        return false;
    }
    if ( old_qty == null ) {
      if ( !row.getOldQtyIsNull() )
        return false;
    } else if ( row.getOldQtyIsNull() || ( old_qty.doubleValue() != row.getOldQty() ) ) {
        return false;
    }
    if ( old_price == null ) {
      if ( !row.getOldPriceIsNull() )
        return false;
    } else if ( row.getOldPriceIsNull() || ( old_price.doubleValue() != row.getOldPrice() ) ) {
        return false;
    }
    if ( cancelled_qty == null ) {
      if ( !row.getCancelledQtyIsNull() )
        return false;
    } else if ( row.getCancelledQtyIsNull() || ( cancelled_qty.doubleValue() != row.getCancelledQty() ) ) {
        return false;
    }
    if ( trigger_param == null ) {
      if ( row.getTriggerParam() != null )
        return false;
    } else if ( !trigger_param.equals( row.getTriggerParam() ) ) {
        return false;
    }
    if ( reject_cmd_type == null ) {
      if ( !row.getRejectCmdTypeIsNull() )
        return false;
    } else if ( row.getRejectCmdTypeIsNull() || ( reject_cmd_type.intValue() != row.getRejectCmdType() ) ) {
        return false;
    }
    if ( reject_code == null ) {
      if ( row.getRejectCode() != null )
        return false;
    } else if ( !reject_code.equals( row.getRejectCode() ) ) {
        return false;
    }
    if ( reject_time == null ) {
      if ( row.getRejectTime() != null )
        return false;
    } else if ( !reject_time.equals( row.getRejectTime() ) ) {
        return false;
    }
    if ( exchange_msg_type == null ) {
      if ( row.getExchangeMsgType() != null )
        return false;
    } else if ( !exchange_msg_type.equals( row.getExchangeMsgType() ) ) {
        return false;
    }
    if ( exchange_msg_code == null ) {
      if ( row.getExchangeMsgCode() != null )
        return false;
    } else if ( !exchange_msg_code.equals( row.getExchangeMsgCode() ) ) {
        return false;
    }
    if ( user_number == null ) {
      if ( !row.getUserNumberIsNull() )
        return false;
    } else if ( row.getUserNumberIsNull() || ( user_number.intValue() != row.getUserNumber() ) ) {
        return false;
    }
    if ( memo == null ) {
      if ( row.getMemo() != null )
        return false;
    } else if ( !memo.equals( row.getMemo() ) ) {
        return false;
    }
    if ( account_id != row.getAccountId() )
      return false;
    if ( op_type == null ) {
      if ( row.getOpType() != null )
        return false;
    } else if ( !op_type.equals( row.getOpType() ) ) {
        return false;
    }
    if ( accept_div == null ) {
      if ( row.getAcceptDiv() != null )
        return false;
    } else if ( !accept_div.equals( row.getAcceptDiv() ) ) {
        return false;
    }
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
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( elimination_message == null ) {
      if ( row.getEliminationMessage() != null )
        return false;
    } else if ( !elimination_message.equals( row.getEliminationMessage() ) ) {
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
        + (xblocks_product_type!=null? xblocks_product_type.hashCode(): 0) 
        + (replies_type!=null? replies_type.hashCode(): 0) 
        + (replies_number!=null? replies_number.hashCode(): 0) 
        + (replies_index!=null? replies_index.hashCode(): 0) 
        + (sub_status!=null? sub_status.hashCode(): 0) 
        + (internal_ref!=null? internal_ref.hashCode(): 0) 
        + (exchange_no!=null? exchange_no.hashCode(): 0) 
        + (trade_number!=null? trade_number.hashCode(): 0) 
        + (gl_id!=null? gl_id.hashCode(): 0) 
        + (stock_code!=null? stock_code.hashCode(): 0) 
        + (side!=null? side.hashCode(): 0) 
        + (modality!=null? modality.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + (trading_phase!=null? trading_phase.hashCode(): 0) 
        + (exec_price!=null? exec_price.hashCode(): 0) 
        + (avg_price!=null? avg_price.hashCode(): 0) 
        + (exec_qty!=null? exec_qty.hashCode(): 0) 
        + (remaining_qty!=null? remaining_qty.hashCode(): 0) 
        + (number_of_trades!=null? number_of_trades.hashCode(): 0) 
        + (order_time!=null? order_time.hashCode(): 0) 
        + (trade_booking_time!=null? trade_booking_time.hashCode(): 0) 
        + (exec_timestamp!=null? exec_timestamp.hashCode(): 0) 
        + (order_emp_code!=null? order_emp_code.hashCode(): 0) 
        + (execution_date!=null? execution_date.hashCode(): 0) 
        + (f_delivery_date!=null? f_delivery_date.hashCode(): 0) 
        + (fx_rate!=null? fx_rate.hashCode(): 0) 
        + (exec_serial_no!=null? exec_serial_no.hashCode(): 0) 
        + (route_div!=null? route_div.hashCode(): 0) 
        + (ack_type!=null? ack_type.hashCode(): 0) 
        + (ackd_command!=null? ackd_command.hashCode(): 0) 
        + (old_qty!=null? old_qty.hashCode(): 0) 
        + (old_price!=null? old_price.hashCode(): 0) 
        + (cancelled_qty!=null? cancelled_qty.hashCode(): 0) 
        + (trigger_param!=null? trigger_param.hashCode(): 0) 
        + (reject_cmd_type!=null? reject_cmd_type.hashCode(): 0) 
        + (reject_code!=null? reject_code.hashCode(): 0) 
        + (reject_time!=null? reject_time.hashCode(): 0) 
        + (exchange_msg_type!=null? exchange_msg_type.hashCode(): 0) 
        + (exchange_msg_code!=null? exchange_msg_code.hashCode(): 0) 
        + (user_number!=null? user_number.hashCode(): 0) 
        + (memo!=null? memo.hashCode(): 0) 
        + ((int) account_id)
        + (op_type!=null? op_type.hashCode(): 0) 
        + (accept_div!=null? accept_div.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (elimination_message!=null? elimination_message.hashCode(): 0) 
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
		if ( !xblocks_product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'xblocks_product_type' must be set before inserting.");
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !op_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'op_type' must be set before inserting.");
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
		map.put("xblocks_product_type",xblocks_product_type);
		if ( replies_type != null )
			map.put("replies_type",replies_type);
		if ( replies_number != null )
			map.put("replies_number",replies_number);
		if ( replies_index != null )
			map.put("replies_index",replies_index);
		if ( sub_status != null )
			map.put("sub_status",sub_status);
		if ( internal_ref != null )
			map.put("internal_ref",internal_ref);
		if ( exchange_no != null )
			map.put("exchange_no",exchange_no);
		if ( trade_number != null )
			map.put("trade_number",trade_number);
		if ( gl_id != null )
			map.put("gl_id",gl_id);
		if ( stock_code != null )
			map.put("stock_code",stock_code);
		if ( side != null )
			map.put("side",side);
		if ( modality != null )
			map.put("modality",modality);
		if ( price != null )
			map.put("price",price);
		if ( quantity != null )
			map.put("quantity",quantity);
		if ( trading_phase != null )
			map.put("trading_phase",trading_phase);
		if ( exec_price != null )
			map.put("exec_price",exec_price);
		if ( avg_price != null )
			map.put("avg_price",avg_price);
		if ( exec_qty != null )
			map.put("exec_qty",exec_qty);
		if ( remaining_qty != null )
			map.put("remaining_qty",remaining_qty);
		if ( number_of_trades != null )
			map.put("number_of_trades",number_of_trades);
		if ( order_time != null )
			map.put("order_time",order_time);
		if ( trade_booking_time != null )
			map.put("trade_booking_time",trade_booking_time);
		if ( exec_timestamp != null )
			map.put("exec_timestamp",exec_timestamp);
		if ( order_emp_code != null )
			map.put("order_emp_code",order_emp_code);
		if ( execution_date != null )
			map.put("execution_date",execution_date);
		if ( f_delivery_date != null )
			map.put("f_delivery_date",f_delivery_date);
		if ( fx_rate != null )
			map.put("fx_rate",fx_rate);
		if ( exec_serial_no != null )
			map.put("exec_serial_no",exec_serial_no);
		if ( route_div != null )
			map.put("route_div",route_div);
		if ( ack_type != null )
			map.put("ack_type",ack_type);
		if ( ackd_command != null )
			map.put("ackd_command",ackd_command);
		if ( old_qty != null )
			map.put("old_qty",old_qty);
		if ( old_price != null )
			map.put("old_price",old_price);
		if ( cancelled_qty != null )
			map.put("cancelled_qty",cancelled_qty);
		if ( trigger_param != null )
			map.put("trigger_param",trigger_param);
		if ( reject_cmd_type != null )
			map.put("reject_cmd_type",reject_cmd_type);
		if ( reject_code != null )
			map.put("reject_code",reject_code);
		if ( reject_time != null )
			map.put("reject_time",reject_time);
		if ( exchange_msg_type != null )
			map.put("exchange_msg_type",exchange_msg_type);
		if ( exchange_msg_code != null )
			map.put("exchange_msg_code",exchange_msg_code);
		if ( user_number != null )
			map.put("user_number",user_number);
		if ( memo != null )
			map.put("memo",memo);
		map.put("account_id",new Long(account_id));
		map.put("op_type",op_type);
		if ( accept_div != null )
			map.put("accept_div",accept_div);
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( order_request_number != null )
			map.put("order_request_number",order_request_number);
		if ( elimination_message != null )
			map.put("elimination_message",elimination_message);
		map.put("status",status);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.remove("rowid");
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( xblocks_product_type_is_set )
			map.put("xblocks_product_type",xblocks_product_type);
		map.put("replies_type",replies_type);
		map.put("replies_number",replies_number);
		map.put("replies_index",replies_index);
		map.put("sub_status",sub_status);
		map.put("internal_ref",internal_ref);
		map.put("exchange_no",exchange_no);
		map.put("trade_number",trade_number);
		map.put("gl_id",gl_id);
		map.put("stock_code",stock_code);
		map.put("side",side);
		map.put("modality",modality);
		map.put("price",price);
		map.put("quantity",quantity);
		map.put("trading_phase",trading_phase);
		map.put("exec_price",exec_price);
		map.put("avg_price",avg_price);
		map.put("exec_qty",exec_qty);
		map.put("remaining_qty",remaining_qty);
		map.put("number_of_trades",number_of_trades);
		map.put("order_time",order_time);
		map.put("trade_booking_time",trade_booking_time);
		map.put("exec_timestamp",exec_timestamp);
		map.put("order_emp_code",order_emp_code);
		map.put("execution_date",execution_date);
		map.put("f_delivery_date",f_delivery_date);
		map.put("fx_rate",fx_rate);
		map.put("exec_serial_no",exec_serial_no);
		map.put("route_div",route_div);
		map.put("ack_type",ack_type);
		map.put("ackd_command",ackd_command);
		map.put("old_qty",old_qty);
		map.put("old_price",old_price);
		map.put("cancelled_qty",cancelled_qty);
		map.put("trigger_param",trigger_param);
		map.put("reject_cmd_type",reject_cmd_type);
		map.put("reject_code",reject_code);
		map.put("reject_time",reject_time);
		map.put("exchange_msg_type",exchange_msg_type);
		map.put("exchange_msg_code",exchange_msg_code);
		map.put("user_number",user_number);
		map.put("memo",memo);
		if ( account_id_is_set )
			map.put("account_id",new Long(account_id));
		if ( op_type_is_set )
			map.put("op_type",op_type);
		map.put("accept_div",accept_div);
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("order_request_number",order_request_number);
		map.put("elimination_message",elimination_message);
		if ( status_is_set )
			map.put("status",status);
		map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * <em>queue_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public final long getQueueId()
  {
    return queue_id;
  }


  /** 
   * <em>queue_id</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQueueIdIsSet() {
    return queue_id_is_set;
  }


  /** 
   * <em>xblocks_product_type</em>コラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getXblocksProductType()
  {
    return xblocks_product_type;
  }


  /** 
   * <em>xblocks_product_type</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getXblocksProductTypeIsSet() {
    return xblocks_product_type_is_set;
  }


  /** 
   * <em>replies_type</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepliesType()
  {
    return replies_type;
  }


  /** 
   * <em>replies_number</em>コラムの値を取得します。
   * @@return longの値 
   */
  public final long getRepliesNumber()
  {
    return ( replies_number==null? ((long)0): replies_number.longValue() );
  }


  /** 
   * <em>replies_number</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getRepliesNumberIsNull()
  {
    return replies_number == null;
  }


  /** 
   * <em>replies_index</em>コラムの値を取得します。
   * @@return longの値 
   */
  public final long getRepliesIndex()
  {
    return ( replies_index==null? ((long)0): replies_index.longValue() );
  }


  /** 
   * <em>replies_index</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getRepliesIndexIsNull()
  {
    return replies_index == null;
  }


  /** 
   * <em>sub_status</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSubStatus()
  {
    return sub_status;
  }


  /** 
   * <em>internal_ref</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInternalRef()
  {
    return internal_ref;
  }


  /** 
   * <em>exchange_no</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExchangeNo()
  {
    return exchange_no;
  }


  /** 
   * <em>trade_number</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradeNumber()
  {
    return trade_number;
  }


  /** 
   * <em>gl_id</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGlId()
  {
    return gl_id;
  }


  /** 
   * <em>stock_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStockCode()
  {
    return stock_code;
  }


  /** 
   * <em>side</em>コラムの値を取得します。
   * @@return intの値 
   */
  public final int getSide()
  {
    return ( side==null? ((int)0): side.intValue() );
  }


  /** 
   * <em>side</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getSideIsNull()
  {
    return side == null;
  }


  /** 
   * <em>modality</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getModality()
  {
    return modality;
  }


  /** 
   * <em>price</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPrice()
  {
    return ( price==null? ((double)0): price.doubleValue() );
  }


  /** 
   * <em>price</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getPriceIsNull()
  {
    return price == null;
  }


  /** 
   * <em>quantity</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getQuantity()
  {
    return ( quantity==null? ((double)0): quantity.doubleValue() );
  }


  /** 
   * <em>quantity</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getQuantityIsNull()
  {
    return quantity == null;
  }


  /** 
   * <em>trading_phase</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradingPhase()
  {
    return trading_phase;
  }


  /** 
   * <em>exec_price</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExecPrice()
  {
    return ( exec_price==null? ((double)0): exec_price.doubleValue() );
  }


  /** 
   * <em>exec_price</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getExecPriceIsNull()
  {
    return exec_price == null;
  }


  /** 
   * <em>avg_price</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAvgPrice()
  {
    return ( avg_price==null? ((double)0): avg_price.doubleValue() );
  }


  /** 
   * <em>avg_price</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getAvgPriceIsNull()
  {
    return avg_price == null;
  }


  /** 
   * <em>exec_qty</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExecQty()
  {
    return ( exec_qty==null? ((double)0): exec_qty.doubleValue() );
  }


  /** 
   * <em>exec_qty</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getExecQtyIsNull()
  {
    return exec_qty == null;
  }


  /** 
   * <em>remaining_qty</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRemainingQty()
  {
    return ( remaining_qty==null? ((double)0): remaining_qty.doubleValue() );
  }


  /** 
   * <em>remaining_qty</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getRemainingQtyIsNull()
  {
    return remaining_qty == null;
  }


  /** 
   * <em>number_of_trades</em>コラムの値を取得します。
   * @@return longの値 
   */
  public final long getNumberOfTrades()
  {
    return ( number_of_trades==null? ((long)0): number_of_trades.longValue() );
  }


  /** 
   * <em>number_of_trades</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getNumberOfTradesIsNull()
  {
    return number_of_trades == null;
  }


  /** 
   * <em>order_time</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderTime()
  {
    return order_time;
  }


  /** 
   * <em>trade_booking_time</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradeBookingTime()
  {
    return trade_booking_time;
  }


  /** 
   * <em>exec_timestamp</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getExecTimestamp()
  {
    return exec_timestamp;
  }


  /** 
   * <em>order_emp_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderEmpCode()
  {
    return order_emp_code;
  }


  /** 
   * <em>execution_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getExecutionDate()
  {
    return execution_date;
  }


  /** 
   * <em>f_delivery_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getFDeliveryDate()
  {
    return f_delivery_date;
  }


  /** 
   * <em>fx_rate</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getFxRate()
  {
    return ( fx_rate==null? ((double)0): fx_rate.doubleValue() );
  }


  /** 
   * <em>fx_rate</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getFxRateIsNull()
  {
    return fx_rate == null;
  }


  /** 
   * <em>exec_serial_no</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExecSerialNo()
  {
    return exec_serial_no;
  }


  /** 
   * <em>route_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRouteDiv()
  {
    return route_div;
  }


  /** 
   * <em>ack_type</em>コラムの値を取得します。
   * @@return intの値 
   */
  public final int getAckType()
  {
    return ( ack_type==null? ((int)0): ack_type.intValue() );
  }


  /** 
   * <em>ack_type</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getAckTypeIsNull()
  {
    return ack_type == null;
  }


  /** 
   * <em>ackd_command</em>コラムの値を取得します。
   * @@return intの値 
   */
  public final int getAckdCommand()
  {
    return ( ackd_command==null? ((int)0): ackd_command.intValue() );
  }


  /** 
   * <em>ackd_command</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getAckdCommandIsNull()
  {
    return ackd_command == null;
  }


  /** 
   * <em>old_qty</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOldQty()
  {
    return ( old_qty==null? ((double)0): old_qty.doubleValue() );
  }


  /** 
   * <em>old_qty</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getOldQtyIsNull()
  {
    return old_qty == null;
  }


  /** 
   * <em>old_price</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOldPrice()
  {
    return ( old_price==null? ((double)0): old_price.doubleValue() );
  }


  /** 
   * <em>old_price</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getOldPriceIsNull()
  {
    return old_price == null;
  }


  /** 
   * <em>cancelled_qty</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCancelledQty()
  {
    return ( cancelled_qty==null? ((double)0): cancelled_qty.doubleValue() );
  }


  /** 
   * <em>cancelled_qty</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getCancelledQtyIsNull()
  {
    return cancelled_qty == null;
  }


  /** 
   * <em>trigger_param</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTriggerParam()
  {
    return trigger_param;
  }


  /** 
   * <em>reject_cmd_type</em>コラムの値を取得します。
   * @@return intの値 
   */
  public final int getRejectCmdType()
  {
    return ( reject_cmd_type==null? ((int)0): reject_cmd_type.intValue() );
  }


  /** 
   * <em>reject_cmd_type</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getRejectCmdTypeIsNull()
  {
    return reject_cmd_type == null;
  }


  /** 
   * <em>reject_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRejectCode()
  {
    return reject_code;
  }


  /** 
   * <em>reject_time</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRejectTime()
  {
    return reject_time;
  }


  /** 
   * <em>exchange_msg_type</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExchangeMsgType()
  {
    return exchange_msg_type;
  }


  /** 
   * <em>exchange_msg_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExchangeMsgCode()
  {
    return exchange_msg_code;
  }


  /** 
   * <em>user_number</em>コラムの値を取得します。
   * @@return intの値 
   */
  public final int getUserNumber()
  {
    return ( user_number==null? ((int)0): user_number.intValue() );
  }


  /** 
   * <em>user_number</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getUserNumberIsNull()
  {
    return user_number == null;
  }


  /** 
   * <em>memo</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMemo()
  {
    return memo;
  }


  /** 
   * <em>account_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>op_type</em>コラムの値を取得します。
   * @@return webbroker3.slebase.enums.SleSendqOpTypeEnumの値 
   */
  public final webbroker3.slebase.enums.SleSendqOpTypeEnum getOpType()
  {
    return op_type;
  }


  /** 
   * <em>op_type</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpTypeIsSet() {
    return op_type_is_set;
  }


  /** 
   * <em>accept_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAcceptDiv()
  {
    return accept_div;
  }


  /** 
   * <em>institution_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>branch_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>order_request_number</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRequestNumber()
  {
    return order_request_number;
  }


  /** 
   * <em>elimination_message</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEliminationMessage()
  {
    return elimination_message;
  }


  /** 
   * <em>status</em>コラムの値を取得します。
   * @@return webbroker3.slebase.enums.SleRcvdqProcStatusEnumの値 
   */
  public final webbroker3.slebase.enums.SleRcvdqProcStatusEnum getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>last_updater</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>created_timestamp</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new SleRcvdQPK(queue_id);
  }


  /** 
   * <em>queue_id</em>コラムの値を設定します。 
   *
   * @@param p_queueId <em>queue_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public final void setQueueId( long p_queueId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    queue_id = p_queueId;
    queue_id_is_set = true;
  }


  /** 
   * <em>xblocks_product_type</em>コラムの値を設定します。 
   *
   * @@param p_xblocksProductType <em>xblocks_product_type</em>コラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public final void setXblocksProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_xblocksProductType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    xblocks_product_type = p_xblocksProductType;
    xblocks_product_type_is_set = true;
  }


  /** 
   * <em>replies_type</em>コラムの値を設定します。 
   *
   * @@param p_repliesType <em>replies_type</em>コラムの値をあらわす5桁以下のString値 
   */
  public final void setRepliesType( String p_repliesType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    replies_type = p_repliesType;
  }


  /** 
   * <em>replies_number</em>コラムの値を設定します。 
   *
   * @@param p_repliesNumber <em>replies_number</em>コラムの値をあらわす18桁以下のlong値 
   */
  public final void setRepliesNumber( long p_repliesNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    replies_number = new Long(p_repliesNumber);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>replies_number</em>コラムに値を設定します。 
   */
  public final void setRepliesNumber( Long p_repliesNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    replies_number = p_repliesNumber;
  }


  /** 
   * <em>replies_index</em>コラムの値を設定します。 
   *
   * @@param p_repliesIndex <em>replies_index</em>コラムの値をあらわす18桁以下のlong値 
   */
  public final void setRepliesIndex( long p_repliesIndex )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    replies_index = new Long(p_repliesIndex);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>replies_index</em>コラムに値を設定します。 
   */
  public final void setRepliesIndex( Long p_repliesIndex )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    replies_index = p_repliesIndex;
  }


  /** 
   * <em>sub_status</em>コラムの値を設定します。 
   *
   * @@param p_subStatus <em>sub_status</em>コラムの値をあらわす5桁以下のString値 
   */
  public final void setSubStatus( String p_subStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_status = p_subStatus;
  }


  /** 
   * <em>internal_ref</em>コラムの値を設定します。 
   *
   * @@param p_internalRef <em>internal_ref</em>コラムの値をあらわす20桁以下のString値 
   */
  public final void setInternalRef( String p_internalRef )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    internal_ref = p_internalRef;
  }


  /** 
   * <em>exchange_no</em>コラムの値を設定します。 
   *
   * @@param p_exchangeNo <em>exchange_no</em>コラムの値をあらわす50桁以下のString値 
   */
  public final void setExchangeNo( String p_exchangeNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exchange_no = p_exchangeNo;
  }


  /** 
   * <em>trade_number</em>コラムの値を設定します。 
   *
   * @@param p_tradeNumber <em>trade_number</em>コラムの値をあらわす50桁以下のString値 
   */
  public final void setTradeNumber( String p_tradeNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_number = p_tradeNumber;
  }


  /** 
   * <em>gl_id</em>コラムの値を設定します。 
   *
   * @@param p_glId <em>gl_id</em>コラムの値をあらわす15桁以下のString値 
   */
  public final void setGlId( String p_glId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gl_id = p_glId;
  }


  /** 
   * <em>stock_code</em>コラムの値を設定します。 
   *
   * @@param p_stockCode <em>stock_code</em>コラムの値をあらわす20桁以下のString値 
   */
  public final void setStockCode( String p_stockCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stock_code = p_stockCode;
  }


  /** 
   * <em>side</em>コラムの値を設定します。 
   *
   * @@param p_side <em>side</em>コラムの値をあらわす5桁以下のint値 
   */
  public final void setSide( int p_side )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    side = new Integer(p_side);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>side</em>コラムに値を設定します。 
   */
  public final void setSide( Integer p_side )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    side = p_side;
  }


  /** 
   * <em>modality</em>コラムの値を設定します。 
   *
   * @@param p_modality <em>modality</em>コラムの値をあらわす5桁以下のString値 
   */
  public final void setModality( String p_modality )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modality = p_modality;
  }


  /** 
   * <em>price</em>コラムの値を設定します。 
   *
   * @@param p_price <em>price</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPrice( double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price = new Double(p_price);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>price</em>コラムに値を設定します。 
   */
  public final void setPrice( Double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    price = p_price;
  }


  /** 
   * <em>quantity</em>コラムの値を設定します。 
   *
   * @@param p_quantity <em>quantity</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = new Double(p_quantity);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>quantity</em>コラムに値を設定します。 
   */
  public final void setQuantity( Double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
  }


  /** 
   * <em>trading_phase</em>コラムの値を設定します。 
   *
   * @@param p_tradingPhase <em>trading_phase</em>コラムの値をあらわす5桁以下のString値 
   */
  public final void setTradingPhase( String p_tradingPhase )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_phase = p_tradingPhase;
  }


  /** 
   * <em>exec_price</em>コラムの値を設定します。 
   *
   * @@param p_execPrice <em>exec_price</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setExecPrice( double p_execPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_price = new Double(p_execPrice);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>exec_price</em>コラムに値を設定します。 
   */
  public final void setExecPrice( Double p_execPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_price = p_execPrice;
  }


  /** 
   * <em>avg_price</em>コラムの値を設定します。 
   *
   * @@param p_avgPrice <em>avg_price</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAvgPrice( double p_avgPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    avg_price = new Double(p_avgPrice);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>avg_price</em>コラムに値を設定します。 
   */
  public final void setAvgPrice( Double p_avgPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    avg_price = p_avgPrice;
  }


  /** 
   * <em>exec_qty</em>コラムの値を設定します。 
   *
   * @@param p_execQty <em>exec_qty</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setExecQty( double p_execQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_qty = new Double(p_execQty);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>exec_qty</em>コラムに値を設定します。 
   */
  public final void setExecQty( Double p_execQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_qty = p_execQty;
  }


  /** 
   * <em>remaining_qty</em>コラムの値を設定します。 
   *
   * @@param p_remainingQty <em>remaining_qty</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRemainingQty( double p_remainingQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remaining_qty = new Double(p_remainingQty);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>remaining_qty</em>コラムに値を設定します。 
   */
  public final void setRemainingQty( Double p_remainingQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    remaining_qty = p_remainingQty;
  }


  /** 
   * <em>number_of_trades</em>コラムの値を設定します。 
   *
   * @@param p_numberOfTrades <em>number_of_trades</em>コラムの値をあらわす18桁以下のlong値 
   */
  public final void setNumberOfTrades( long p_numberOfTrades )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    number_of_trades = new Long(p_numberOfTrades);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>number_of_trades</em>コラムに値を設定します。 
   */
  public final void setNumberOfTrades( Long p_numberOfTrades )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    number_of_trades = p_numberOfTrades;
  }


  /** 
   * <em>order_time</em>コラムの値を設定します。 
   *
   * @@param p_orderTime <em>order_time</em>コラムの値をあらわす14桁以下のString値 
   */
  public final void setOrderTime( String p_orderTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_time = p_orderTime;
  }


  /** 
   * <em>trade_booking_time</em>コラムの値を設定します。 
   *
   * @@param p_tradeBookingTime <em>trade_booking_time</em>コラムの値をあらわす14桁以下のString値 
   */
  public final void setTradeBookingTime( String p_tradeBookingTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_booking_time = p_tradeBookingTime;
  }


  /** 
   * <em>exec_timestamp</em>コラムの値を設定します。 
   *
   * @@param p_execTimestamp <em>exec_timestamp</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setExecTimestamp( java.sql.Timestamp p_execTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_timestamp = p_execTimestamp;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>exec_timestamp</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setExecTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>order_emp_code</em>コラムの値を設定します。 
   *
   * @@param p_orderEmpCode <em>order_emp_code</em>コラムの値をあらわす7桁以下のString値 
   */
  public final void setOrderEmpCode( String p_orderEmpCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_emp_code = p_orderEmpCode;
  }


  /** 
   * <em>execution_date</em>コラムの値を設定します。 
   *
   * @@param p_executionDate <em>execution_date</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setExecutionDate( java.sql.Timestamp p_executionDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    execution_date = p_executionDate;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>execution_date</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setExecutionDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    execution_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>f_delivery_date</em>コラムの値を設定します。 
   *
   * @@param p_fDeliveryDate <em>f_delivery_date</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setFDeliveryDate( java.sql.Timestamp p_fDeliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    f_delivery_date = p_fDeliveryDate;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>f_delivery_date</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setFDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    f_delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>fx_rate</em>コラムの値を設定します。 
   *
   * @@param p_fxRate <em>fx_rate</em>コラムの値をあらわす7桁以下のdouble値で、その精度は4桁まで
   */
  public final void setFxRate( double p_fxRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_rate = new Double(p_fxRate);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>fx_rate</em>コラムに値を設定します。 
   */
  public final void setFxRate( Double p_fxRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    fx_rate = p_fxRate;
  }


  /** 
   * <em>exec_serial_no</em>コラムの値を設定します。 
   *
   * @@param p_execSerialNo <em>exec_serial_no</em>コラムの値をあらわす3桁以下のString値 
   */
  public final void setExecSerialNo( String p_execSerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_serial_no = p_execSerialNo;
  }


  /** 
   * <em>route_div</em>コラムの値を設定します。 
   *
   * @@param p_routeDiv <em>route_div</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setRouteDiv( String p_routeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    route_div = p_routeDiv;
  }


  /** 
   * <em>ack_type</em>コラムの値を設定します。 
   *
   * @@param p_ackType <em>ack_type</em>コラムの値をあらわす5桁以下のint値 
   */
  public final void setAckType( int p_ackType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ack_type = new Integer(p_ackType);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ack_type</em>コラムに値を設定します。 
   */
  public final void setAckType( Integer p_ackType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ack_type = p_ackType;
  }


  /** 
   * <em>ackd_command</em>コラムの値を設定します。 
   *
   * @@param p_ackdCommand <em>ackd_command</em>コラムの値をあらわす5桁以下のint値 
   */
  public final void setAckdCommand( int p_ackdCommand )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ackd_command = new Integer(p_ackdCommand);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ackd_command</em>コラムに値を設定します。 
   */
  public final void setAckdCommand( Integer p_ackdCommand )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ackd_command = p_ackdCommand;
  }


  /** 
   * <em>old_qty</em>コラムの値を設定します。 
   *
   * @@param p_oldQty <em>old_qty</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOldQty( double p_oldQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    old_qty = new Double(p_oldQty);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>old_qty</em>コラムに値を設定します。 
   */
  public final void setOldQty( Double p_oldQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    old_qty = p_oldQty;
  }


  /** 
   * <em>old_price</em>コラムの値を設定します。 
   *
   * @@param p_oldPrice <em>old_price</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOldPrice( double p_oldPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    old_price = new Double(p_oldPrice);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>old_price</em>コラムに値を設定します。 
   */
  public final void setOldPrice( Double p_oldPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    old_price = p_oldPrice;
  }


  /** 
   * <em>cancelled_qty</em>コラムの値を設定します。 
   *
   * @@param p_cancelledQty <em>cancelled_qty</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCancelledQty( double p_cancelledQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancelled_qty = new Double(p_cancelledQty);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cancelled_qty</em>コラムに値を設定します。 
   */
  public final void setCancelledQty( Double p_cancelledQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cancelled_qty = p_cancelledQty;
  }


  /** 
   * <em>trigger_param</em>コラムの値を設定します。 
   *
   * @@param p_triggerParam <em>trigger_param</em>コラムの値をあらわす5桁以下のString値 
   */
  public final void setTriggerParam( String p_triggerParam )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trigger_param = p_triggerParam;
  }


  /** 
   * <em>reject_cmd_type</em>コラムの値を設定します。 
   *
   * @@param p_rejectCmdType <em>reject_cmd_type</em>コラムの値をあらわす5桁以下のint値 
   */
  public final void setRejectCmdType( int p_rejectCmdType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reject_cmd_type = new Integer(p_rejectCmdType);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>reject_cmd_type</em>コラムに値を設定します。 
   */
  public final void setRejectCmdType( Integer p_rejectCmdType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    reject_cmd_type = p_rejectCmdType;
  }


  /** 
   * <em>reject_code</em>コラムの値を設定します。 
   *
   * @@param p_rejectCode <em>reject_code</em>コラムの値をあらわす50桁以下のString値 
   */
  public final void setRejectCode( String p_rejectCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reject_code = p_rejectCode;
  }


  /** 
   * <em>reject_time</em>コラムの値を設定します。 
   *
   * @@param p_rejectTime <em>reject_time</em>コラムの値をあらわす14桁以下のString値 
   */
  public final void setRejectTime( String p_rejectTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reject_time = p_rejectTime;
  }


  /** 
   * <em>exchange_msg_type</em>コラムの値を設定します。 
   *
   * @@param p_exchangeMsgType <em>exchange_msg_type</em>コラムの値をあらわす5桁以下のString値 
   */
  public final void setExchangeMsgType( String p_exchangeMsgType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exchange_msg_type = p_exchangeMsgType;
  }


  /** 
   * <em>exchange_msg_code</em>コラムの値を設定します。 
   *
   * @@param p_exchangeMsgCode <em>exchange_msg_code</em>コラムの値をあらわす50桁以下のString値 
   */
  public final void setExchangeMsgCode( String p_exchangeMsgCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exchange_msg_code = p_exchangeMsgCode;
  }


  /** 
   * <em>user_number</em>コラムの値を設定します。 
   *
   * @@param p_userNumber <em>user_number</em>コラムの値をあらわす5桁以下のint値 
   */
  public final void setUserNumber( int p_userNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    user_number = new Integer(p_userNumber);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>user_number</em>コラムに値を設定します。 
   */
  public final void setUserNumber( Integer p_userNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    user_number = p_userNumber;
  }


  /** 
   * <em>memo</em>コラムの値を設定します。 
   *
   * @@param p_memo <em>memo</em>コラムの値をあらわす50桁以下のString値 
   */
  public final void setMemo( String p_memo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    memo = p_memo;
  }


  /** 
   * <em>account_id</em>コラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
  }


  /** 
   * <em>op_type</em>コラムの値を設定します。 
   *
   * @@param p_opType <em>op_type</em>コラムの値をあらわす6桁以下のwebbroker3.slebase.enums.SleSendqOpTypeEnum値 
   */
  public final void setOpType( webbroker3.slebase.enums.SleSendqOpTypeEnum p_opType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    op_type = p_opType;
    op_type_is_set = true;
  }


  /** 
   * <em>accept_div</em>コラムの値を設定します。 
   *
   * @@param p_acceptDiv <em>accept_div</em>コラムの値をあらわす2桁以下のString値 
   */
  public final void setAcceptDiv( String p_acceptDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    accept_div = p_acceptDiv;
  }


  /** 
   * <em>institution_code</em>コラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>コラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
  }


  /** 
   * <em>branch_code</em>コラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>コラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
  }


  /** 
   * <em>order_request_number</em>コラムの値を設定します。 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>コラムの値をあらわす9桁以下のString値 
   */
  public final void setOrderRequestNumber( String p_orderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number = p_orderRequestNumber;
  }


  /** 
   * <em>elimination_message</em>コラムの値を設定します。 
   *
   * @@param p_eliminationMessage <em>elimination_message</em>コラムの値をあらわす20桁以下のString値 
   */
  public final void setEliminationMessage( String p_eliminationMessage )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    elimination_message = p_eliminationMessage;
  }


  /** 
   * <em>status</em>コラムの値を設定します。 
   *
   * @@param p_status <em>status</em>コラムの値をあらわす6桁以下のwebbroker3.slebase.enums.SleRcvdqProcStatusEnum値 
   */
  public final void setStatus( webbroker3.slebase.enums.SleRcvdqProcStatusEnum p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
  }


  /** 
   * <em>last_updater</em>コラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>コラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
  }


  /** 
   * <em>created_timestamp</em>コラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
  }


  /** 
   * <em>last_updated_timestamp</em>コラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("avg_price") ) {
                    return this.avg_price;
                }
                else if ( name.equals("ack_type") ) {
                    return this.ack_type;
                }
                else if ( name.equals("ackd_command") ) {
                    return this.ackd_command;
                }
                else if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("accept_div") ) {
                    return this.accept_div;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("cancelled_qty") ) {
                    return this.cancelled_qty;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("exchange_no") ) {
                    return this.exchange_no;
                }
                else if ( name.equals("exec_price") ) {
                    return this.exec_price;
                }
                else if ( name.equals("exec_qty") ) {
                    return this.exec_qty;
                }
                else if ( name.equals("exec_timestamp") ) {
                    return this.exec_timestamp;
                }
                else if ( name.equals("execution_date") ) {
                    return this.execution_date;
                }
                else if ( name.equals("exec_serial_no") ) {
                    return this.exec_serial_no;
                }
                else if ( name.equals("exchange_msg_type") ) {
                    return this.exchange_msg_type;
                }
                else if ( name.equals("exchange_msg_code") ) {
                    return this.exchange_msg_code;
                }
                else if ( name.equals("elimination_message") ) {
                    return this.elimination_message;
                }
                break;
            case 'f':
                if ( name.equals("f_delivery_date") ) {
                    return this.f_delivery_date;
                }
                else if ( name.equals("fx_rate") ) {
                    return this.fx_rate;
                }
                break;
            case 'g':
                if ( name.equals("gl_id") ) {
                    return this.gl_id;
                }
                break;
            case 'i':
                if ( name.equals("internal_ref") ) {
                    return this.internal_ref;
                }
                else if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("modality") ) {
                    return this.modality;
                }
                else if ( name.equals("memo") ) {
                    return this.memo;
                }
                break;
            case 'n':
                if ( name.equals("number_of_trades") ) {
                    return this.number_of_trades;
                }
                break;
            case 'o':
                if ( name.equals("order_time") ) {
                    return this.order_time;
                }
                else if ( name.equals("order_emp_code") ) {
                    return this.order_emp_code;
                }
                else if ( name.equals("old_qty") ) {
                    return this.old_qty;
                }
                else if ( name.equals("old_price") ) {
                    return this.old_price;
                }
                else if ( name.equals("op_type") ) {
                    return this.op_type;
                }
                else if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                break;
            case 'p':
                if ( name.equals("price") ) {
                    return this.price;
                }
                break;
            case 'q':
                if ( name.equals("queue_id") ) {
                    return new Long( queue_id );
                }
                else if ( name.equals("quantity") ) {
                    return this.quantity;
                }
                break;
            case 'r':
                if ( name.equals("replies_type") ) {
                    return this.replies_type;
                }
                else if ( name.equals("replies_number") ) {
                    return this.replies_number;
                }
                else if ( name.equals("replies_index") ) {
                    return this.replies_index;
                }
                else if ( name.equals("remaining_qty") ) {
                    return this.remaining_qty;
                }
                else if ( name.equals("route_div") ) {
                    return this.route_div;
                }
                else if ( name.equals("reject_cmd_type") ) {
                    return this.reject_cmd_type;
                }
                else if ( name.equals("reject_code") ) {
                    return this.reject_code;
                }
                else if ( name.equals("reject_time") ) {
                    return this.reject_time;
                }
                break;
            case 's':
                if ( name.equals("sub_status") ) {
                    return this.sub_status;
                }
                else if ( name.equals("stock_code") ) {
                    return this.stock_code;
                }
                else if ( name.equals("side") ) {
                    return this.side;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trade_number") ) {
                    return this.trade_number;
                }
                else if ( name.equals("trading_phase") ) {
                    return this.trading_phase;
                }
                else if ( name.equals("trade_booking_time") ) {
                    return this.trade_booking_time;
                }
                else if ( name.equals("trigger_param") ) {
                    return this.trigger_param;
                }
                break;
            case 'u':
                if ( name.equals("user_number") ) {
                    return this.user_number;
                }
                break;
            case 'x':
                if ( name.equals("xblocks_product_type") ) {
                    return this.xblocks_product_type;
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
                if ( name.equals("avg_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'avg_price' must be Double: '"+value+"' is not." );
                    this.avg_price = (Double) value;
                    return;
                }
                else if ( name.equals("ack_type") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'ack_type' must be Integer: '"+value+"' is not." );
                    this.ack_type = (Integer) value;
                    return;
                }
                else if ( name.equals("ackd_command") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'ackd_command' must be Integer: '"+value+"' is not." );
                    this.ackd_command = (Integer) value;
                    return;
                }
                else if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("accept_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'accept_div' must be String: '"+value+"' is not." );
                    this.accept_div = (String) value;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("cancelled_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cancelled_qty' must be Double: '"+value+"' is not." );
                    this.cancelled_qty = (Double) value;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("exchange_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exchange_no' must be String: '"+value+"' is not." );
                    this.exchange_no = (String) value;
                    return;
                }
                else if ( name.equals("exec_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'exec_price' must be Double: '"+value+"' is not." );
                    this.exec_price = (Double) value;
                    return;
                }
                else if ( name.equals("exec_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'exec_qty' must be Double: '"+value+"' is not." );
                    this.exec_qty = (Double) value;
                    return;
                }
                else if ( name.equals("exec_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'exec_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.exec_timestamp = (java.sql.Timestamp) value;
                    return;
                }
                else if ( name.equals("execution_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'execution_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.execution_date = (java.sql.Timestamp) value;
                    return;
                }
                else if ( name.equals("exec_serial_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exec_serial_no' must be String: '"+value+"' is not." );
                    this.exec_serial_no = (String) value;
                    return;
                }
                else if ( name.equals("exchange_msg_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exchange_msg_type' must be String: '"+value+"' is not." );
                    this.exchange_msg_type = (String) value;
                    return;
                }
                else if ( name.equals("exchange_msg_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exchange_msg_code' must be String: '"+value+"' is not." );
                    this.exchange_msg_code = (String) value;
                    return;
                }
                else if ( name.equals("elimination_message") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'elimination_message' must be String: '"+value+"' is not." );
                    this.elimination_message = (String) value;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("f_delivery_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'f_delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.f_delivery_date = (java.sql.Timestamp) value;
                    return;
                }
                else if ( name.equals("fx_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'fx_rate' must be Double: '"+value+"' is not." );
                    this.fx_rate = (Double) value;
                    return;
                }
                break;
            case 'g':
                if ( name.equals("gl_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gl_id' must be String: '"+value+"' is not." );
                    this.gl_id = (String) value;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("internal_ref") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'internal_ref' must be String: '"+value+"' is not." );
                    this.internal_ref = (String) value;
                    return;
                }
                else if ( name.equals("institution_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("modality") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'modality' must be String: '"+value+"' is not." );
                    this.modality = (String) value;
                    return;
                }
                else if ( name.equals("memo") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'memo' must be String: '"+value+"' is not." );
                    this.memo = (String) value;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("number_of_trades") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'number_of_trades' must be Long: '"+value+"' is not." );
                    this.number_of_trades = (Long) value;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_time' must be String: '"+value+"' is not." );
                    this.order_time = (String) value;
                    return;
                }
                else if ( name.equals("order_emp_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_emp_code' must be String: '"+value+"' is not." );
                    this.order_emp_code = (String) value;
                    return;
                }
                else if ( name.equals("old_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'old_qty' must be Double: '"+value+"' is not." );
                    this.old_qty = (Double) value;
                    return;
                }
                else if ( name.equals("old_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'old_price' must be Double: '"+value+"' is not." );
                    this.old_price = (Double) value;
                    return;
                }
                else if ( name.equals("op_type") ) {
                    if ( !(value instanceof webbroker3.slebase.enums.SleSendqOpTypeEnum) )
                        throw new IllegalArgumentException( "value for 'op_type' must be webbroker3.slebase.enums.SleSendqOpTypeEnum: '"+value+"' is not." );
                    this.op_type = (webbroker3.slebase.enums.SleSendqOpTypeEnum) value;
                    this.op_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_request_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'price' must be Double: '"+value+"' is not." );
                    this.price = (Double) value;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("queue_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'queue_id' must be Long: '"+value+"' is not." );
                    this.queue_id = ((Long) value).longValue();
                    this.queue_id_is_set = true;
                    return;
                }
                else if ( name.equals("quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = (Double) value;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("replies_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'replies_type' must be String: '"+value+"' is not." );
                    this.replies_type = (String) value;
                    return;
                }
                else if ( name.equals("replies_number") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'replies_number' must be Long: '"+value+"' is not." );
                    this.replies_number = (Long) value;
                    return;
                }
                else if ( name.equals("replies_index") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'replies_index' must be Long: '"+value+"' is not." );
                    this.replies_index = (Long) value;
                    return;
                }
                else if ( name.equals("remaining_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'remaining_qty' must be Double: '"+value+"' is not." );
                    this.remaining_qty = (Double) value;
                    return;
                }
                else if ( name.equals("route_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'route_div' must be String: '"+value+"' is not." );
                    this.route_div = (String) value;
                    return;
                }
                else if ( name.equals("reject_cmd_type") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'reject_cmd_type' must be Integer: '"+value+"' is not." );
                    this.reject_cmd_type = (Integer) value;
                    return;
                }
                else if ( name.equals("reject_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reject_code' must be String: '"+value+"' is not." );
                    this.reject_code = (String) value;
                    return;
                }
                else if ( name.equals("reject_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reject_time' must be String: '"+value+"' is not." );
                    this.reject_time = (String) value;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sub_status' must be String: '"+value+"' is not." );
                    this.sub_status = (String) value;
                    return;
                }
                else if ( name.equals("stock_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stock_code' must be String: '"+value+"' is not." );
                    this.stock_code = (String) value;
                    return;
                }
                else if ( name.equals("side") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'side' must be Integer: '"+value+"' is not." );
                    this.side = (Integer) value;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( !(value instanceof webbroker3.slebase.enums.SleRcvdqProcStatusEnum) )
                        throw new IllegalArgumentException( "value for 'status' must be webbroker3.slebase.enums.SleRcvdqProcStatusEnum: '"+value+"' is not." );
                    this.status = (webbroker3.slebase.enums.SleRcvdqProcStatusEnum) value;
                    this.status_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trade_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trade_number' must be String: '"+value+"' is not." );
                    this.trade_number = (String) value;
                    return;
                }
                else if ( name.equals("trading_phase") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_phase' must be String: '"+value+"' is not." );
                    this.trading_phase = (String) value;
                    return;
                }
                else if ( name.equals("trade_booking_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trade_booking_time' must be String: '"+value+"' is not." );
                    this.trade_booking_time = (String) value;
                    return;
                }
                else if ( name.equals("trigger_param") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trigger_param' must be String: '"+value+"' is not." );
                    this.trigger_param = (String) value;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("user_number") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'user_number' must be Integer: '"+value+"' is not." );
                    this.user_number = (Integer) value;
                    return;
                }
                break;
            case 'x':
                if ( name.equals("xblocks_product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'xblocks_product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.xblocks_product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    this.xblocks_product_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
