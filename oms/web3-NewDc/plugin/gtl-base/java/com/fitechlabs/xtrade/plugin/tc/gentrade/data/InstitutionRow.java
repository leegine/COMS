head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.34.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	InstitutionRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

/** 
 * InstitutionRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>institution</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link InstitutionRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see InstitutionPK 
 */
public interface InstitutionRow extends Row {


  /** 
   * ����{@@link InstitutionRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "institution", "master" );


  /** 
   * <em>institution_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getInstitutionId();


  /** 
   * <em>institution_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionIdIsSet();


  /** 
   * <em>institution_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionIdIsModified();


  /** 
   * <em>institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInstitutionCode();


  /** 
   * <em>institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionCodeIsSet();


  /** 
   * <em>institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionCodeIsModified();


  /** 
   * <em>institution_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInstitutionName();


  /** 
   * <em>institution_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionNameIsSet();


  /** 
   * <em>institution_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionNameIsModified();


  /** 
   * <em>asset_evaluation</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAssetEvaluation();


  /** 
   * <em>asset_evaluation</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAssetEvaluationIsSet();


  /** 
   * <em>asset_evaluation</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAssetEvaluationIsModified();


  /** 
   * <em>maximum_assessment</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMaximumAssessment();


  /** 
   * <em>maximum_assessment</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaximumAssessmentIsNull();


  /** 
   * <em>maximum_assessment</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaximumAssessmentIsSet();


  /** 
   * <em>maximum_assessment</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaximumAssessmentIsModified();


  /** 
   * <em>stock_evaluation</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStockEvaluation();


  /** 
   * <em>stock_evaluation</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStockEvaluationIsSet();


  /** 
   * <em>stock_evaluation</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStockEvaluationIsModified();


  /** 
   * <em>gp_evaluation</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpEvaluation();


  /** 
   * <em>gp_evaluation</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpEvaluationIsSet();


  /** 
   * <em>gp_evaluation</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpEvaluationIsModified();


  /** 
   * <em>fund_evaluation</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFundEvaluation();


  /** 
   * <em>fund_evaluation</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFundEvaluationIsSet();


  /** 
   * <em>fund_evaluation</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFundEvaluationIsModified();


  /** 
   * <em>bond_evaluation</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBondEvaluation();


  /** 
   * <em>bond_evaluation</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondEvaluationIsSet();


  /** 
   * <em>bond_evaluation</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondEvaluationIsModified();


  /** 
   * <em>enable_ipo_quantity_change</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEnableIpoQuantityChange();


  /** 
   * <em>enable_ipo_quantity_change</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEnableIpoQuantityChangeIsSet();


  /** 
   * <em>enable_ipo_quantity_change</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEnableIpoQuantityChangeIsModified();


  /** 
   * <em>deposit_inform_mail_send</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDepositInformMailSend();


  /** 
   * <em>deposit_inform_mail_send</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDepositInformMailSendIsSet();


  /** 
   * <em>deposit_inform_mail_send</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDepositInformMailSendIsModified();


  /** 
   * <em>payment_apply_trigger</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPaymentApplyTrigger();


  /** 
   * <em>payment_apply_trigger</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentApplyTriggerIsSet();


  /** 
   * <em>payment_apply_trigger</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentApplyTriggerIsModified();


  /** 
   * <em>payment_apply_admin_task</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPaymentApplyAdminTask();


  /** 
   * <em>payment_apply_admin_task</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentApplyAdminTaskIsSet();


  /** 
   * <em>payment_apply_admin_task</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentApplyAdminTaskIsModified();


  /** 
   * <em>payment_reserve</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPaymentReserve();


  /** 
   * <em>payment_reserve</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentReserveIsSet();


  /** 
   * <em>payment_reserve</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentReserveIsModified();


  /** 
   * <em>the_day_transfer</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTheDayTransfer();


  /** 
   * <em>the_day_transfer</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTheDayTransferIsSet();


  /** 
   * <em>the_day_transfer</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTheDayTransferIsModified();


  /** 
   * <em>payment_automatic</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPaymentAutomatic();


  /** 
   * <em>payment_automatic</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAutomaticIsSet();


  /** 
   * <em>payment_automatic</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAutomaticIsModified();


  /** 
   * <em>max_order_quantity</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMaxOrderQuantity();


  /** 
   * <em>max_order_quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxOrderQuantityIsNull();


  /** 
   * <em>max_order_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxOrderQuantityIsSet();


  /** 
   * <em>max_order_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxOrderQuantityIsModified();


  /** 
   * <em>off_floor_order_start_hhmmss</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOffFloorOrderStartHhmmss();


  /** 
   * <em>off_floor_order_start_hhmmss</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOffFloorOrderStartHhmmssIsSet();


  /** 
   * <em>off_floor_order_start_hhmmss</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOffFloorOrderStartHhmmssIsModified();


  /** 
   * <em>off_floor_order_end_hhmmss</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOffFloorOrderEndHhmmss();


  /** 
   * <em>off_floor_order_end_hhmmss</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOffFloorOrderEndHhmmssIsSet();


  /** 
   * <em>off_floor_order_end_hhmmss</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOffFloorOrderEndHhmmssIsModified();


  /** 
   * <em>system_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSystemStatus();


  /** 
   * <em>system_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSystemStatusIsSet();


  /** 
   * <em>system_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSystemStatusIsModified();


  /** 
   * <em>hash_send_a</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHashSendA();


  /** 
   * <em>hash_send_a</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHashSendAIsSet();


  /** 
   * <em>hash_send_a</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHashSendAIsModified();


  /** 
   * <em>hash_send_b</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHashSendB();


  /** 
   * <em>hash_send_b</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHashSendBIsSet();


  /** 
   * <em>hash_send_b</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHashSendBIsModified();


  /** 
   * <em>hash_receive_a</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHashReceiveA();


  /** 
   * <em>hash_receive_a</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHashReceiveAIsSet();


  /** 
   * <em>hash_receive_a</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHashReceiveAIsModified();


  /** 
   * <em>hash_receive_b</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHashReceiveB();


  /** 
   * <em>hash_receive_b</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHashReceiveBIsSet();


  /** 
   * <em>hash_receive_b</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHashReceiveBIsModified();


  /** 
   * <em>email_address</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEmailAddress();


  /** 
   * <em>email_address</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailAddressIsSet();


  /** 
   * <em>email_address</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailAddressIsModified();


  /** 
   * <em>slingshot_check_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSlingshotCheckFlag();


  /** 
   * <em>slingshot_check_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSlingshotCheckFlagIsSet();


  /** 
   * <em>slingshot_check_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSlingshotCheckFlagIsModified();


  /** 
   * <em>trd_code_min</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getTrdCodeMin();


  /** 
   * <em>trd_code_min</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTrdCodeMinIsNull();


  /** 
   * <em>trd_code_min</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTrdCodeMinIsSet();


  /** 
   * <em>trd_code_min</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTrdCodeMinIsModified();


  /** 
   * <em>trd_code_max</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getTrdCodeMax();


  /** 
   * <em>trd_code_max</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTrdCodeMaxIsNull();


  /** 
   * <em>trd_code_max</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTrdCodeMaxIsSet();


  /** 
   * <em>trd_code_max</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTrdCodeMaxIsModified();


  /** 
   * <em>trd_code_check_mode</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTrdCodeCheckMode();


  /** 
   * <em>trd_code_check_mode</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTrdCodeCheckModeIsSet();


  /** 
   * <em>trd_code_check_mode</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTrdCodeCheckModeIsModified();


  /** 
   * <em>admin_code_min</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getAdminCodeMin();


  /** 
   * <em>admin_code_min</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAdminCodeMinIsNull();


  /** 
   * <em>admin_code_min</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAdminCodeMinIsSet();


  /** 
   * <em>admin_code_min</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAdminCodeMinIsModified();


  /** 
   * <em>admin_code_max</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getAdminCodeMax();


  /** 
   * <em>admin_code_max</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAdminCodeMaxIsNull();


  /** 
   * <em>admin_code_max</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAdminCodeMaxIsSet();


  /** 
   * <em>admin_code_max</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAdminCodeMaxIsModified();


  /** 
   * <em>admin_code_check_mode</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAdminCodeCheckMode();


  /** 
   * <em>admin_code_check_mode</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAdminCodeCheckModeIsSet();


  /** 
   * <em>admin_code_check_mode</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAdminCodeCheckModeIsModified();


  /** 
   * <em>ifo_real_price_calc_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoRealPriceCalcDiv();


  /** 
   * <em>ifo_real_price_calc_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoRealPriceCalcDivIsSet();


  /** 
   * <em>ifo_real_price_calc_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoRealPriceCalcDivIsModified();


  /** 
   * <em>simple_span_calc_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSimpleSpanCalcDiv();


  /** 
   * <em>simple_span_calc_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSimpleSpanCalcDivIsSet();


  /** 
   * <em>simple_span_calc_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSimpleSpanCalcDivIsModified();


  /** 
   * <em>span_trouble_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSpanTroubleDiv();


  /** 
   * <em>span_trouble_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpanTroubleDivIsSet();


  /** 
   * <em>span_trouble_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpanTroubleDivIsModified();


  /** 
   * <em>span_factor</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getSpanFactor();


  /** 
   * <em>span_factor</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSpanFactorIsNull();


  /** 
   * <em>span_factor</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpanFactorIsSet();


  /** 
   * <em>span_factor</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpanFactorIsModified();


  /** 
   * <em>span_factor_red</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getSpanFactorRed();


  /** 
   * <em>span_factor_red</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSpanFactorRedIsNull();


  /** 
   * <em>span_factor_red</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpanFactorRedIsSet();


  /** 
   * <em>span_factor_red</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpanFactorRedIsModified();


  /** 
   * <em>transfer_power_factor</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTransferPowerFactor();


  /** 
   * <em>transfer_power_factor</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTransferPowerFactorIsNull();


  /** 
   * <em>transfer_power_factor</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferPowerFactorIsSet();


  /** 
   * <em>transfer_power_factor</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferPowerFactorIsModified();


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


  /** 
   * <em>future_day_trade_charge_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFutureDayTradeChargeDiv();


  /** 
   * <em>future_day_trade_charge_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFutureDayTradeChargeDivIsSet();


  /** 
   * <em>future_day_trade_charge_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFutureDayTradeChargeDivIsModified();


  /** 
   * <em>securitites_cos_id_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSecurititesCosIdCode();


  /** 
   * <em>securitites_cos_id_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSecurititesCosIdCodeIsSet();


  /** 
   * <em>securitites_cos_id_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSecurititesCosIdCodeIsModified();


  /** 
   * <em>db_current_price_check_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDbCurrentPriceCheckDiv();


  /** 
   * <em>db_current_price_check_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDbCurrentPriceCheckDivIsSet();


  /** 
   * <em>db_current_price_check_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDbCurrentPriceCheckDivIsModified();


  /** 
   * <em>forcedsettleorder_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForcedsettleorderDiv();


  /** 
   * <em>forcedsettleorder_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForcedsettleorderDivIsSet();


  /** 
   * <em>forcedsettleorder_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForcedsettleorderDivIsModified();


  /** 
   * <em>forcedsettleorder_closeday_cnt</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getForcedsettleorderClosedayCnt();


  /** 
   * <em>forcedsettleorder_closeday_cnt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getForcedsettleorderClosedayCntIsNull();


  /** 
   * <em>forcedsettleorder_closeday_cnt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForcedsettleorderClosedayCntIsSet();


  /** 
   * <em>forcedsettleorder_closeday_cnt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForcedsettleorderClosedayCntIsModified();


}
@
