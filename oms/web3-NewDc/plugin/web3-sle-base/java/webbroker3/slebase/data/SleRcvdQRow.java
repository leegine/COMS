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
 * SleRcvdQRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>sle_rcvd_q</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link SleRcvdQRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleRcvdQPK 
 */
public interface SleRcvdQRow extends Row {


  /** 
   * ����{@@link SleRcvdQRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "sle_rcvd_q", "session" );


  /** 
   * <em>queue_id</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getQueueId();


  /** 
   * <em>queue_id</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQueueIdIsSet();


  /** 
   * <em>xblocks_product_type</em>�R�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getXblocksProductType();


  /** 
   * <em>xblocks_product_type</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getXblocksProductTypeIsSet();


  /** 
   * <em>replies_type</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRepliesType();


  /** 
   * <em>replies_number</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getRepliesNumber();


  /** 
   * <em>replies_number</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRepliesNumberIsNull();


  /** 
   * <em>replies_index</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getRepliesIndex();


  /** 
   * <em>replies_index</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRepliesIndexIsNull();


  /** 
   * <em>sub_status</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSubStatus();


  /** 
   * <em>internal_ref</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInternalRef();


  /** 
   * <em>exchange_no</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExchangeNo();


  /** 
   * <em>trade_number</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTradeNumber();


  /** 
   * <em>gl_id</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGlId();


  /** 
   * <em>stock_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStockCode();


  /** 
   * <em>side</em>�R�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getSide();


  /** 
   * <em>side</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSideIsNull();


  /** 
   * <em>modality</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getModality();


  /** 
   * <em>price</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPrice();


  /** 
   * <em>price</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPriceIsNull();


  /** 
   * <em>quantity</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getQuantity();


  /** 
   * <em>quantity</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getQuantityIsNull();


  /** 
   * <em>trading_phase</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTradingPhase();


  /** 
   * <em>exec_price</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getExecPrice();


  /** 
   * <em>exec_price</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExecPriceIsNull();


  /** 
   * <em>avg_price</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getAvgPrice();


  /** 
   * <em>avg_price</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAvgPriceIsNull();


  /** 
   * <em>exec_qty</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getExecQty();


  /** 
   * <em>exec_qty</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExecQtyIsNull();


  /** 
   * <em>remaining_qty</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getRemainingQty();


  /** 
   * <em>remaining_qty</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRemainingQtyIsNull();


  /** 
   * <em>number_of_trades</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getNumberOfTrades();


  /** 
   * <em>number_of_trades</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getNumberOfTradesIsNull();


  /** 
   * <em>order_time</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrderTime();


  /** 
   * <em>trade_booking_time</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTradeBookingTime();


  /** 
   * <em>exec_timestamp</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getExecTimestamp();


  /** 
   * <em>order_emp_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrderEmpCode();


  /** 
   * <em>execution_date</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getExecutionDate();


  /** 
   * <em>f_delivery_date</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getFDeliveryDate();


  /** 
   * <em>fx_rate</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getFxRate();


  /** 
   * <em>fx_rate</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getFxRateIsNull();


  /** 
   * <em>exec_serial_no</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExecSerialNo();


  /** 
   * <em>route_div</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRouteDiv();


  /** 
   * <em>ack_type</em>�R�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getAckType();


  /** 
   * <em>ack_type</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAckTypeIsNull();


  /** 
   * <em>ackd_command</em>�R�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getAckdCommand();


  /** 
   * <em>ackd_command</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAckdCommandIsNull();


  /** 
   * <em>old_qty</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getOldQty();


  /** 
   * <em>old_qty</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOldQtyIsNull();


  /** 
   * <em>old_price</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getOldPrice();


  /** 
   * <em>old_price</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOldPriceIsNull();


  /** 
   * <em>cancelled_qty</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCancelledQty();


  /** 
   * <em>cancelled_qty</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCancelledQtyIsNull();


  /** 
   * <em>trigger_param</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTriggerParam();


  /** 
   * <em>reject_cmd_type</em>�R�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getRejectCmdType();


  /** 
   * <em>reject_cmd_type</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRejectCmdTypeIsNull();


  /** 
   * <em>reject_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRejectCode();


  /** 
   * <em>reject_time</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRejectTime();


  /** 
   * <em>exchange_msg_type</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExchangeMsgType();


  /** 
   * <em>exchange_msg_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExchangeMsgCode();


  /** 
   * <em>user_number</em>�R�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getUserNumber();


  /** 
   * <em>user_number</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUserNumberIsNull();


  /** 
   * <em>memo</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMemo();


  /** 
   * <em>account_id</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAccountId();


  /** 
   * <em>account_id</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountIdIsSet();


  /** 
   * <em>op_type</em>�R�����̒l���擾���܂��B
   * @@return webbroker3.slebase.enums.SleSendqOpTypeEnum�̒l 
   */
  public webbroker3.slebase.enums.SleSendqOpTypeEnum getOpType();


  /** 
   * <em>op_type</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOpTypeIsSet();


  /** 
   * <em>accept_div</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAcceptDiv();


  /** 
   * <em>institution_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInstitutionCode();


  /** 
   * <em>branch_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBranchCode();


  /** 
   * <em>order_request_number</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrderRequestNumber();


  /** 
   * <em>elimination_message</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEliminationMessage();


  /** 
   * <em>status</em>�R�����̒l���擾���܂��B
   * @@return webbroker3.slebase.enums.SleRcvdqProcStatusEnum�̒l 
   */
  public webbroker3.slebase.enums.SleRcvdqProcStatusEnum getStatus();


  /** 
   * <em>status</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatusIsSet();


  /** 
   * <em>last_updater</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLastUpdater();


  /** 
   * <em>created_timestamp</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getCreatedTimestamp();


  /** 
   * <em>created_timestamp</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCreatedTimestampIsSet();


  /** 
   * <em>last_updated_timestamp</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getLastUpdatedTimestamp();


  /** 
   * <em>last_updated_timestamp</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdatedTimestampIsSet();


}
@
