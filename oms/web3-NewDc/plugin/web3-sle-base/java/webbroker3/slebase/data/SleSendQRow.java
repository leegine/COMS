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
 * SleSendQRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>sle_send_q</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link SleSendQRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleSendQPK 
 */
public interface SleSendQRow extends Row {


  /** 
   * ����{@@link SleSendQRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "sle_send_q", "session" );


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
   * <em>product_type</em>�R�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType();


  /** 
   * <em>product_type</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductTypeIsSet();


  /** 
   * <em>market_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarketCode();


  /** 
   * <em>market_code</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarketCodeIsSet();


  /** 
   * <em>broker_name</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBrokerName();


  /** 
   * <em>broker_name</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBrokerNameIsSet();


  /** 
   * <em>institution_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInstitutionCode();


  /** 
   * <em>institution_code</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionCodeIsSet();


  /** 
   * <em>branch_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBranchCode();


  /** 
   * <em>branch_code</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchCodeIsSet();


  /** 
   * <em>product_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProductCode();


  /** 
   * <em>order_id</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getOrderId();


  /** 
   * <em>order_id</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOrderIdIsNull();


  /** 
   * <em>order_unit_id</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getOrderUnitId();


  /** 
   * <em>order_unit_id</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOrderUnitIdIsNull();


  /** 
   * <em>biz_date</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBizDate();


  /** 
   * <em>biz_date</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBizDateIsSet();


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
   * <em>order_type</em>�R�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum getOrderType();


  /** 
   * <em>order_type</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderTypeIsSet();


  /** 
   * <em>limit_price</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getLimitPrice();


  /** 
   * <em>limit_price</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLimitPriceIsNull();


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
   * <em>change_quantity</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getChangeQuantity();


  /** 
   * <em>change_quantity</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getChangeQuantityIsNull();


  /** 
   * <em>change_limit_price</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getChangeLimitPrice();


  /** 
   * <em>change_limit_price</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getChangeLimitPriceIsNull();


  /** 
   * <em>already_execd_quantity</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getAlreadyExecdQuantity();


  /** 
   * <em>already_execd_quantity</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAlreadyExecdQuantityIsNull();


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
   * <em>account_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountCode();


  /** 
   * <em>account_code</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCodeIsSet();


  /** 
   * <em>sub_account_id</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSubAccountId();


  /** 
   * <em>sub_account_id</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubAccountIdIsSet();


  /** 
   * <em>status</em>�R�����̒l���擾���܂��B
   * @@return webbroker3.slebase.enums.SleSendqProcStatusEnum�̒l 
   */
  public webbroker3.slebase.enums.SleSendqProcStatusEnum getStatus();


  /** 
   * <em>status</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatusIsSet();


  /** 
   * <em>confirmed_by_sle_rcvd_q</em>�R�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getConfirmedBySleRcvdQ();


  /** 
   * <em>confirmed_by_sle_rcvd_q</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConfirmedBySleRcvdQIsSet();


  /** 
   * <em>order_emp_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrderEmpCode();


  /** 
   * <em>order_emp_code</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderEmpCodeIsSet();


  /** 
   * <em>order_request_number</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrderRequestNumber();


  /** 
   * <em>order_request_number</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderRequestNumberIsSet();


  /** 
   * <em>send_process_date_time</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getSendProcessDateTime();


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
