head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.01.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88656402a7;
filename	FeqOrderActionRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbfeq.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * FeqOrderActionRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>feq_order_action</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link FeqOrderActionRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FeqOrderActionPK 
 */
public interface FeqOrderActionRow extends Row {


  /** 
   * ����{@@link FeqOrderActionRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "feq_order_action", "account" );


  /** 
   * <em>order_action_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getOrderActionId();


  /** 
   * <em>order_action_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderActionIdIsSet();


  /** 
   * <em>order_action_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderActionIdIsModified();


  /** 
   * <em>account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAccountId();


  /** 
   * <em>account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountIdIsSet();


  /** 
   * <em>account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountIdIsModified();


  /** 
   * <em>sub_account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSubAccountId();


  /** 
   * <em>sub_account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubAccountIdIsSet();


  /** 
   * <em>sub_account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubAccountIdIsModified();


  /** 
   * <em>order_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getOrderId();


  /** 
   * <em>order_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderIdIsSet();


  /** 
   * <em>order_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderIdIsModified();


  /** 
   * <em>order_unit_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getOrderUnitId();


  /** 
   * <em>order_unit_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderUnitIdIsSet();


  /** 
   * <em>order_unit_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderUnitIdIsModified();


  /** 
   * <em>market_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarketId();


  /** 
   * <em>market_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarketIdIsNull();


  /** 
   * <em>market_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarketIdIsSet();


  /** 
   * <em>market_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarketIdIsModified();


  /** 
   * <em>order_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum getOrderType();


  /** 
   * <em>order_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderTypeIsSet();


  /** 
   * <em>order_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderTypeIsModified();


  /** 
   * <em>order_event_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum getOrderEventType();


  /** 
   * <em>order_event_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderEventTypeIsSet();


  /** 
   * <em>order_event_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderEventTypeIsModified();


  /** 
   * <em>price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPrice();


  /** 
   * <em>price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPriceIsNull();


  /** 
   * <em>price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPriceIsSet();


  /** 
   * <em>price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPriceIsModified();


  /** 
   * <em>execution_condition_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType getExecutionConditionType();


  /** 
   * <em>execution_condition_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExecutionConditionTypeIsSet();


  /** 
   * <em>execution_condition_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExecutionConditionTypeIsModified();


  /** 
   * <em>order_condition_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrderConditionType();


  /** 
   * <em>order_condition_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderConditionTypeIsSet();


  /** 
   * <em>order_condition_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderConditionTypeIsModified();


  /** 
   * <em>order_cond_operator</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrderCondOperator();


  /** 
   * <em>order_cond_operator</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderCondOperatorIsSet();


  /** 
   * <em>order_cond_operator</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderCondOperatorIsModified();


  /** 
   * <em>stop_order_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getStopOrderPrice();


  /** 
   * <em>stop_order_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getStopOrderPriceIsNull();


  /** 
   * <em>stop_order_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStopOrderPriceIsSet();


  /** 
   * <em>stop_order_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStopOrderPriceIsModified();


  /** 
   * <em>w_limit_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getWLimitPrice();


  /** 
   * <em>w_limit_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getWLimitPriceIsNull();


  /** 
   * <em>w_limit_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWLimitPriceIsSet();


  /** 
   * <em>w_limit_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWLimitPriceIsModified();


  /** 
   * <em>expiration_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getExpirationDate();


  /** 
   * <em>expiration_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExpirationDateIsSet();


  /** 
   * <em>expiration_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExpirationDateIsModified();


  /** 
   * <em>quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getQuantity();


  /** 
   * <em>quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQuantityIsSet();


  /** 
   * <em>quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQuantityIsModified();


  /** 
   * <em>confirmed_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getConfirmedPrice();


  /** 
   * <em>confirmed_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getConfirmedPriceIsNull();


  /** 
   * <em>confirmed_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConfirmedPriceIsSet();


  /** 
   * <em>confirmed_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConfirmedPriceIsModified();


  /** 
   * <em>confirmed_quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getConfirmedQuantity();


  /** 
   * <em>confirmed_quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getConfirmedQuantityIsNull();


  /** 
   * <em>confirmed_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConfirmedQuantityIsSet();


  /** 
   * <em>confirmed_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConfirmedQuantityIsModified();


  /** 
   * <em>executed_quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getExecutedQuantity();


  /** 
   * <em>executed_quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExecutedQuantityIsNull();


  /** 
   * <em>executed_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExecutedQuantityIsSet();


  /** 
   * <em>executed_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExecutedQuantityIsModified();


  /** 
   * <em>order_status</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum getOrderStatus();


  /** 
   * <em>order_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderStatusIsSet();


  /** 
   * <em>order_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderStatusIsModified();


  /** 
   * <em>expiration_status</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum getExpirationStatus();


  /** 
   * <em>expiration_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExpirationStatusIsSet();


  /** 
   * <em>expiration_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExpirationStatusIsModified();


  /** 
   * <em>order_action_serial_no</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getOrderActionSerialNo();


  /** 
   * <em>order_action_serial_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderActionSerialNoIsSet();


  /** 
   * <em>order_action_serial_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderActionSerialNoIsModified();


  /** 
   * <em>executed_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getExecutedPrice();


  /** 
   * <em>executed_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExecutedPriceIsNull();


  /** 
   * <em>executed_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExecutedPriceIsSet();


  /** 
   * <em>executed_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExecutedPriceIsModified();


  /** 
   * <em>exec_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getExecTimestamp();


  /** 
   * <em>exec_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExecTimestampIsSet();


  /** 
   * <em>exec_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExecTimestampIsModified();


  /** 
   * <em>product_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getProductId();


  /** 
   * <em>product_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductIdIsSet();


  /** 
   * <em>product_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductIdIsModified();


  /** 
   * <em>estimated_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getEstimatedPrice();


  /** 
   * <em>estimated_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getEstimatedPriceIsNull();


  /** 
   * <em>estimated_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEstimatedPriceIsSet();


  /** 
   * <em>estimated_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEstimatedPriceIsModified();


  /** 
   * <em>f_estimated_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getFEstimatedPrice();


  /** 
   * <em>f_estimated_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getFEstimatedPriceIsNull();


  /** 
   * <em>f_estimated_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFEstimatedPriceIsSet();


  /** 
   * <em>f_estimated_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFEstimatedPriceIsModified();


  /** 
   * <em>modify_cancel_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getModifyCancelType();


  /** 
   * <em>modify_cancel_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getModifyCancelTypeIsSet();


  /** 
   * <em>modify_cancel_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getModifyCancelTypeIsModified();


  /** 
   * <em>order_root_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrderRootDiv();


  /** 
   * <em>order_root_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderRootDivIsSet();


  /** 
   * <em>order_root_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderRootDivIsModified();


  /** 
   * <em>confirmed_order_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getConfirmedOrderPrice();


  /** 
   * <em>confirmed_order_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getConfirmedOrderPriceIsNull();


  /** 
   * <em>confirmed_order_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConfirmedOrderPriceIsSet();


  /** 
   * <em>confirmed_order_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConfirmedOrderPriceIsModified();


  /** 
   * <em>confirmed_estimated_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getConfirmedEstimatedPrice();


  /** 
   * <em>confirmed_estimated_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getConfirmedEstimatedPriceIsNull();


  /** 
   * <em>confirmed_estimated_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConfirmedEstimatedPriceIsSet();


  /** 
   * <em>confirmed_estimated_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConfirmedEstimatedPriceIsModified();


  /** 
   * <em>confirmed_f_estimated_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getConfirmedFEstimatedPrice();


  /** 
   * <em>confirmed_f_estimated_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getConfirmedFEstimatedPriceIsNull();


  /** 
   * <em>confirmed_f_estimated_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConfirmedFEstimatedPriceIsSet();


  /** 
   * <em>confirmed_f_estimated_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConfirmedFEstimatedPriceIsModified();


  /** 
   * <em>confirmed_exec_condition_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType getConfirmedExecConditionType();


  /** 
   * <em>confirmed_exec_condition_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConfirmedExecConditionTypeIsSet();


  /** 
   * <em>confirmed_exec_condition_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConfirmedExecConditionTypeIsModified();


  /** 
   * <em>error_reason_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getErrorReasonCode();


  /** 
   * <em>error_reason_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getErrorReasonCodeIsSet();


  /** 
   * <em>error_reason_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getErrorReasonCodeIsModified();


  /** 
   * <em>last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLastUpdater();


  /** 
   * <em>last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdaterIsSet();


  /** 
   * <em>last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdaterIsModified();


  /** 
   * <em>created_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getCreatedTimestamp();


  /** 
   * <em>created_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCreatedTimestampIsSet();


  /** 
   * <em>created_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCreatedTimestampIsModified();


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getLastUpdatedTimestamp();


  /** 
   * <em>last_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdatedTimestampIsSet();


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdatedTimestampIsModified();


}
@