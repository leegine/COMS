head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.57.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	PaymentRequisitionMarginRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpoweradmin.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * PaymentRequisitionMarginRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>payment_requisition_margin</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link PaymentRequisitionMarginRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PaymentRequisitionMarginPK 
 */
public interface PaymentRequisitionMarginRow extends Row {


  /** 
   * ����{@@link PaymentRequisitionMarginRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "payment_requisition_margin", "session" );


  /** 
   * <em>calc_result_margin_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getCalcResultMarginId();


  /** 
   * <em>calc_result_margin_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcResultMarginIdIsSet();


  /** 
   * <em>calc_result_margin_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcResultMarginIdIsModified();


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
   * <em>family_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFamilyName();


  /** 
   * <em>family_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyNameIsSet();


  /** 
   * <em>family_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyNameIsModified();


  /** 
   * <em>account_attribute</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountAttribute();


  /** 
   * <em>account_attribute</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountAttributeIsSet();


  /** 
   * <em>account_attribute</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountAttributeIsModified();


  /** 
   * <em>sonar_trader_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSonarTraderCode();


  /** 
   * <em>sonar_trader_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSonarTraderCodeIsSet();


  /** 
   * <em>sonar_trader_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSonarTraderCodeIsModified();


  /** 
   * <em>calc_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getCalcDate();


  /** 
   * <em>calc_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcDateIsSet();


  /** 
   * <em>calc_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcDateIsModified();


  /** 
   * <em>trading_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTradingStop();


  /** 
   * <em>trading_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradingStopIsSet();


  /** 
   * <em>trading_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradingStopIsModified();


  /** 
   * <em>margin_open_position_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarginOpenPositionStop();


  /** 
   * <em>margin_open_position_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginOpenPositionStopIsSet();


  /** 
   * <em>margin_open_position_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginOpenPositionStopIsModified();


  /** 
   * <em>ifo_open_position_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoOpenPositionStop();


  /** 
   * <em>ifo_open_position_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoOpenPositionStopIsSet();


  /** 
   * <em>ifo_open_position_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoOpenPositionStopIsModified();


  /** 
   * <em>payment_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPaymentStop();


  /** 
   * <em>payment_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentStopIsSet();


  /** 
   * <em>payment_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentStopIsModified();


  /** 
   * <em>other_trading_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOtherTradingStop();


  /** 
   * <em>other_trading_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherTradingStopIsSet();


  /** 
   * <em>other_trading_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherTradingStopIsModified();


  /** 
   * <em>break20day</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getBreak20day();


  /** 
   * <em>break20day</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBreak20dayIsSet();


  /** 
   * <em>break20day</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBreak20dayIsModified();


  /** 
   * <em>break20elapsed_days</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBreak20elapsedDays();


  /** 
   * <em>break20elapsed_days</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBreak20elapsedDaysIsSet();


  /** 
   * <em>break20elapsed_days</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBreak20elapsedDaysIsModified();


  /** 
   * <em>break30day</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getBreak30day();


  /** 
   * <em>break30day</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBreak30dayIsSet();


  /** 
   * <em>break30day</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBreak30dayIsModified();


  /** 
   * <em>break30elapsed_days</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBreak30elapsedDays();


  /** 
   * <em>break30elapsed_days</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBreak30elapsedDaysIsSet();


  /** 
   * <em>break30elapsed_days</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBreak30elapsedDaysIsModified();


  /** 
   * <em>payment_requisition_amount_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentRequisitionAmount0();


  /** 
   * <em>payment_requisition_amount_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentRequisitionAmount0IsNull();


  /** 
   * <em>payment_requisition_amount_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionAmount0IsSet();


  /** 
   * <em>payment_requisition_amount_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionAmount0IsModified();


  /** 
   * <em>payment_requisition_amount_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentRequisitionAmount1();


  /** 
   * <em>payment_requisition_amount_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentRequisitionAmount1IsNull();


  /** 
   * <em>payment_requisition_amount_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionAmount1IsSet();


  /** 
   * <em>payment_requisition_amount_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionAmount1IsModified();


  /** 
   * <em>payment_requisition_amount_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentRequisitionAmount2();


  /** 
   * <em>payment_requisition_amount_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentRequisitionAmount2IsNull();


  /** 
   * <em>payment_requisition_amount_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionAmount2IsSet();


  /** 
   * <em>payment_requisition_amount_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionAmount2IsModified();


  /** 
   * <em>payment_requisition_amount_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentRequisitionAmount3();


  /** 
   * <em>payment_requisition_amount_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentRequisitionAmount3IsNull();


  /** 
   * <em>payment_requisition_amount_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionAmount3IsSet();


  /** 
   * <em>payment_requisition_amount_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionAmount3IsModified();


  /** 
   * <em>payment_requisition_amount_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentRequisitionAmount4();


  /** 
   * <em>payment_requisition_amount_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentRequisitionAmount4IsNull();


  /** 
   * <em>payment_requisition_amount_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionAmount4IsSet();


  /** 
   * <em>payment_requisition_amount_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionAmount4IsModified();


  /** 
   * <em>payment_requisition_amount_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentRequisitionAmount5();


  /** 
   * <em>payment_requisition_amount_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentRequisitionAmount5IsNull();


  /** 
   * <em>payment_requisition_amount_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionAmount5IsSet();


  /** 
   * <em>payment_requisition_amount_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionAmount5IsModified();


  /** 
   * <em>payment_requisition_division_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentRequisitionDivision0();


  /** 
   * <em>payment_requisition_division_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentRequisitionDivision0IsNull();


  /** 
   * <em>payment_requisition_division_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionDivision0IsSet();


  /** 
   * <em>payment_requisition_division_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionDivision0IsModified();


  /** 
   * <em>payment_requisition_division_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentRequisitionDivision1();


  /** 
   * <em>payment_requisition_division_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentRequisitionDivision1IsNull();


  /** 
   * <em>payment_requisition_division_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionDivision1IsSet();


  /** 
   * <em>payment_requisition_division_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionDivision1IsModified();


  /** 
   * <em>payment_requisition_division_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentRequisitionDivision2();


  /** 
   * <em>payment_requisition_division_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentRequisitionDivision2IsNull();


  /** 
   * <em>payment_requisition_division_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionDivision2IsSet();


  /** 
   * <em>payment_requisition_division_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionDivision2IsModified();


  /** 
   * <em>payment_requisition_division_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentRequisitionDivision3();


  /** 
   * <em>payment_requisition_division_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentRequisitionDivision3IsNull();


  /** 
   * <em>payment_requisition_division_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionDivision3IsSet();


  /** 
   * <em>payment_requisition_division_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionDivision3IsModified();


  /** 
   * <em>payment_requisition_division_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentRequisitionDivision4();


  /** 
   * <em>payment_requisition_division_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentRequisitionDivision4IsNull();


  /** 
   * <em>payment_requisition_division_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionDivision4IsSet();


  /** 
   * <em>payment_requisition_division_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionDivision4IsModified();


  /** 
   * <em>payment_requisition_division_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentRequisitionDivision5();


  /** 
   * <em>payment_requisition_division_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentRequisitionDivision5IsNull();


  /** 
   * <em>payment_requisition_division_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionDivision5IsSet();


  /** 
   * <em>payment_requisition_division_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentRequisitionDivision5IsModified();


  /** 
   * <em>account_balance_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getAccountBalance0();


  /** 
   * <em>account_balance_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAccountBalance0IsNull();


  /** 
   * <em>account_balance_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountBalance0IsSet();


  /** 
   * <em>account_balance_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountBalance0IsModified();


  /** 
   * <em>account_balance_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getAccountBalance1();


  /** 
   * <em>account_balance_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAccountBalance1IsNull();


  /** 
   * <em>account_balance_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountBalance1IsSet();


  /** 
   * <em>account_balance_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountBalance1IsModified();


  /** 
   * <em>account_balance_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getAccountBalance2();


  /** 
   * <em>account_balance_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAccountBalance2IsNull();


  /** 
   * <em>account_balance_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountBalance2IsSet();


  /** 
   * <em>account_balance_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountBalance2IsModified();


  /** 
   * <em>account_balance_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getAccountBalance3();


  /** 
   * <em>account_balance_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAccountBalance3IsNull();


  /** 
   * <em>account_balance_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountBalance3IsSet();


  /** 
   * <em>account_balance_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountBalance3IsModified();


  /** 
   * <em>account_balance_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getAccountBalance4();


  /** 
   * <em>account_balance_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAccountBalance4IsNull();


  /** 
   * <em>account_balance_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountBalance4IsSet();


  /** 
   * <em>account_balance_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountBalance4IsModified();


  /** 
   * <em>account_balance_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getAccountBalance5();


  /** 
   * <em>account_balance_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAccountBalance5IsNull();


  /** 
   * <em>account_balance_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountBalance5IsSet();


  /** 
   * <em>account_balance_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountBalance5IsModified();


  /** 
   * <em>cash_balance0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCashBalance0();


  /** 
   * <em>cash_balance0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCashBalance0IsNull();


  /** 
   * <em>cash_balance0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashBalance0IsSet();


  /** 
   * <em>cash_balance0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashBalance0IsModified();


  /** 
   * <em>cash_balance1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCashBalance1();


  /** 
   * <em>cash_balance1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCashBalance1IsNull();


  /** 
   * <em>cash_balance1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashBalance1IsSet();


  /** 
   * <em>cash_balance1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashBalance1IsModified();


  /** 
   * <em>cash_balance2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCashBalance2();


  /** 
   * <em>cash_balance2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCashBalance2IsNull();


  /** 
   * <em>cash_balance2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashBalance2IsSet();


  /** 
   * <em>cash_balance2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashBalance2IsModified();


  /** 
   * <em>cash_balance3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCashBalance3();


  /** 
   * <em>cash_balance3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCashBalance3IsNull();


  /** 
   * <em>cash_balance3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashBalance3IsSet();


  /** 
   * <em>cash_balance3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashBalance3IsModified();


  /** 
   * <em>cash_balance4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCashBalance4();


  /** 
   * <em>cash_balance4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCashBalance4IsNull();


  /** 
   * <em>cash_balance4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashBalance4IsSet();


  /** 
   * <em>cash_balance4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashBalance4IsModified();


  /** 
   * <em>cash_balance5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCashBalance5();


  /** 
   * <em>cash_balance5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCashBalance5IsNull();


  /** 
   * <em>cash_balance5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashBalance5IsSet();


  /** 
   * <em>cash_balance5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashBalance5IsModified();


  /** 
   * <em>day_trade_restraint_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayTradeRestraint0();


  /** 
   * <em>day_trade_restraint_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayTradeRestraint0IsNull();


  /** 
   * <em>day_trade_restraint_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayTradeRestraint0IsSet();


  /** 
   * <em>day_trade_restraint_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayTradeRestraint0IsModified();


  /** 
   * <em>day_trade_restraint_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayTradeRestraint1();


  /** 
   * <em>day_trade_restraint_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayTradeRestraint1IsNull();


  /** 
   * <em>day_trade_restraint_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayTradeRestraint1IsSet();


  /** 
   * <em>day_trade_restraint_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayTradeRestraint1IsModified();


  /** 
   * <em>day_trade_restraint_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayTradeRestraint2();


  /** 
   * <em>day_trade_restraint_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayTradeRestraint2IsNull();


  /** 
   * <em>day_trade_restraint_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayTradeRestraint2IsSet();


  /** 
   * <em>day_trade_restraint_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayTradeRestraint2IsModified();


  /** 
   * <em>day_trade_restraint_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayTradeRestraint3();


  /** 
   * <em>day_trade_restraint_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayTradeRestraint3IsNull();


  /** 
   * <em>day_trade_restraint_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayTradeRestraint3IsSet();


  /** 
   * <em>day_trade_restraint_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayTradeRestraint3IsModified();


  /** 
   * <em>day_trade_restraint_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayTradeRestraint4();


  /** 
   * <em>day_trade_restraint_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayTradeRestraint4IsNull();


  /** 
   * <em>day_trade_restraint_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayTradeRestraint4IsSet();


  /** 
   * <em>day_trade_restraint_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayTradeRestraint4IsModified();


  /** 
   * <em>other_restraint_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getOtherRestraint0();


  /** 
   * <em>other_restraint_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOtherRestraint0IsNull();


  /** 
   * <em>other_restraint_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherRestraint0IsSet();


  /** 
   * <em>other_restraint_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherRestraint0IsModified();


  /** 
   * <em>other_restraint_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getOtherRestraint1();


  /** 
   * <em>other_restraint_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOtherRestraint1IsNull();


  /** 
   * <em>other_restraint_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherRestraint1IsSet();


  /** 
   * <em>other_restraint_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherRestraint1IsModified();


  /** 
   * <em>other_restraint_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getOtherRestraint2();


  /** 
   * <em>other_restraint_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOtherRestraint2IsNull();


  /** 
   * <em>other_restraint_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherRestraint2IsSet();


  /** 
   * <em>other_restraint_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherRestraint2IsModified();


  /** 
   * <em>other_restraint_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getOtherRestraint3();


  /** 
   * <em>other_restraint_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOtherRestraint3IsNull();


  /** 
   * <em>other_restraint_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherRestraint3IsSet();


  /** 
   * <em>other_restraint_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherRestraint3IsModified();


  /** 
   * <em>other_restraint_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getOtherRestraint4();


  /** 
   * <em>other_restraint_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOtherRestraint4IsNull();


  /** 
   * <em>other_restraint_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherRestraint4IsSet();


  /** 
   * <em>other_restraint_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherRestraint4IsModified();


  /** 
   * <em>other_restraint_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getOtherRestraint5();


  /** 
   * <em>other_restraint_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOtherRestraint5IsNull();


  /** 
   * <em>other_restraint_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherRestraint5IsSet();


  /** 
   * <em>other_restraint_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherRestraint5IsModified();


  /** 
   * <em>paid_margin_deposit_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaidMarginDeposit0();


  /** 
   * <em>paid_margin_deposit_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaidMarginDeposit0IsNull();


  /** 
   * <em>paid_margin_deposit_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaidMarginDeposit0IsSet();


  /** 
   * <em>paid_margin_deposit_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaidMarginDeposit0IsModified();


  /** 
   * <em>paid_margin_deposit_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaidMarginDeposit1();


  /** 
   * <em>paid_margin_deposit_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaidMarginDeposit1IsNull();


  /** 
   * <em>paid_margin_deposit_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaidMarginDeposit1IsSet();


  /** 
   * <em>paid_margin_deposit_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaidMarginDeposit1IsModified();


  /** 
   * <em>paid_margin_deposit_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaidMarginDeposit2();


  /** 
   * <em>paid_margin_deposit_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaidMarginDeposit2IsNull();


  /** 
   * <em>paid_margin_deposit_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaidMarginDeposit2IsSet();


  /** 
   * <em>paid_margin_deposit_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaidMarginDeposit2IsModified();


  /** 
   * <em>paid_margin_deposit_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaidMarginDeposit3();


  /** 
   * <em>paid_margin_deposit_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaidMarginDeposit3IsNull();


  /** 
   * <em>paid_margin_deposit_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaidMarginDeposit3IsSet();


  /** 
   * <em>paid_margin_deposit_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaidMarginDeposit3IsModified();


  /** 
   * <em>paid_margin_deposit_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaidMarginDeposit4();


  /** 
   * <em>paid_margin_deposit_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaidMarginDeposit4IsNull();


  /** 
   * <em>paid_margin_deposit_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaidMarginDeposit4IsSet();


  /** 
   * <em>paid_margin_deposit_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaidMarginDeposit4IsModified();


  /** 
   * <em>paid_margin_deposit_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaidMarginDeposit5();


  /** 
   * <em>paid_margin_deposit_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaidMarginDeposit5IsNull();


  /** 
   * <em>paid_margin_deposit_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaidMarginDeposit5IsSet();


  /** 
   * <em>paid_margin_deposit_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaidMarginDeposit5IsModified();


  /** 
   * <em>receipt_margin_deposit_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getReceiptMarginDeposit0();


  /** 
   * <em>receipt_margin_deposit_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getReceiptMarginDeposit0IsNull();


  /** 
   * <em>receipt_margin_deposit_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptMarginDeposit0IsSet();


  /** 
   * <em>receipt_margin_deposit_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptMarginDeposit0IsModified();


  /** 
   * <em>receipt_margin_deposit_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getReceiptMarginDeposit1();


  /** 
   * <em>receipt_margin_deposit_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getReceiptMarginDeposit1IsNull();


  /** 
   * <em>receipt_margin_deposit_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptMarginDeposit1IsSet();


  /** 
   * <em>receipt_margin_deposit_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptMarginDeposit1IsModified();


  /** 
   * <em>receipt_margin_deposit_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getReceiptMarginDeposit2();


  /** 
   * <em>receipt_margin_deposit_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getReceiptMarginDeposit2IsNull();


  /** 
   * <em>receipt_margin_deposit_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptMarginDeposit2IsSet();


  /** 
   * <em>receipt_margin_deposit_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptMarginDeposit2IsModified();


  /** 
   * <em>receipt_margin_deposit_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getReceiptMarginDeposit3();


  /** 
   * <em>receipt_margin_deposit_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getReceiptMarginDeposit3IsNull();


  /** 
   * <em>receipt_margin_deposit_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptMarginDeposit3IsSet();


  /** 
   * <em>receipt_margin_deposit_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptMarginDeposit3IsModified();


  /** 
   * <em>receipt_margin_deposit_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getReceiptMarginDeposit4();


  /** 
   * <em>receipt_margin_deposit_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getReceiptMarginDeposit4IsNull();


  /** 
   * <em>receipt_margin_deposit_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptMarginDeposit4IsSet();


  /** 
   * <em>receipt_margin_deposit_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptMarginDeposit4IsModified();


  /** 
   * <em>receipt_margin_deposit_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getReceiptMarginDeposit5();


  /** 
   * <em>receipt_margin_deposit_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getReceiptMarginDeposit5IsNull();


  /** 
   * <em>receipt_margin_deposit_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptMarginDeposit5IsSet();


  /** 
   * <em>receipt_margin_deposit_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptMarginDeposit5IsModified();


  /** 
   * <em>margin_deposit_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginDeposit0();


  /** 
   * <em>margin_deposit_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDeposit0IsNull();


  /** 
   * <em>margin_deposit_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDeposit0IsSet();


  /** 
   * <em>margin_deposit_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDeposit0IsModified();


  /** 
   * <em>margin_deposit_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginDeposit1();


  /** 
   * <em>margin_deposit_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDeposit1IsNull();


  /** 
   * <em>margin_deposit_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDeposit1IsSet();


  /** 
   * <em>margin_deposit_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDeposit1IsModified();


  /** 
   * <em>margin_deposit_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginDeposit2();


  /** 
   * <em>margin_deposit_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDeposit2IsNull();


  /** 
   * <em>margin_deposit_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDeposit2IsSet();


  /** 
   * <em>margin_deposit_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDeposit2IsModified();


  /** 
   * <em>margin_deposit_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginDeposit3();


  /** 
   * <em>margin_deposit_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDeposit3IsNull();


  /** 
   * <em>margin_deposit_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDeposit3IsSet();


  /** 
   * <em>margin_deposit_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDeposit3IsModified();


  /** 
   * <em>margin_deposit_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginDeposit4();


  /** 
   * <em>margin_deposit_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDeposit4IsNull();


  /** 
   * <em>margin_deposit_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDeposit4IsSet();


  /** 
   * <em>margin_deposit_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDeposit4IsModified();


  /** 
   * <em>margin_deposit_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginDeposit5();


  /** 
   * <em>margin_deposit_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDeposit5IsNull();


  /** 
   * <em>margin_deposit_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDeposit5IsSet();


  /** 
   * <em>margin_deposit_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDeposit5IsModified();


  /** 
   * <em>cash_margin_deposit_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCashMarginDeposit0();


  /** 
   * <em>cash_margin_deposit_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCashMarginDeposit0IsNull();


  /** 
   * <em>cash_margin_deposit_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDeposit0IsSet();


  /** 
   * <em>cash_margin_deposit_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDeposit0IsModified();


  /** 
   * <em>cash_margin_deposit_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCashMarginDeposit1();


  /** 
   * <em>cash_margin_deposit_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCashMarginDeposit1IsNull();


  /** 
   * <em>cash_margin_deposit_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDeposit1IsSet();


  /** 
   * <em>cash_margin_deposit_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDeposit1IsModified();


  /** 
   * <em>cash_margin_deposit_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCashMarginDeposit2();


  /** 
   * <em>cash_margin_deposit_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCashMarginDeposit2IsNull();


  /** 
   * <em>cash_margin_deposit_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDeposit2IsSet();


  /** 
   * <em>cash_margin_deposit_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDeposit2IsModified();


  /** 
   * <em>cash_margin_deposit_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCashMarginDeposit3();


  /** 
   * <em>cash_margin_deposit_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCashMarginDeposit3IsNull();


  /** 
   * <em>cash_margin_deposit_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDeposit3IsSet();


  /** 
   * <em>cash_margin_deposit_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDeposit3IsModified();


  /** 
   * <em>cash_margin_deposit_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCashMarginDeposit4();


  /** 
   * <em>cash_margin_deposit_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCashMarginDeposit4IsNull();


  /** 
   * <em>cash_margin_deposit_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDeposit4IsSet();


  /** 
   * <em>cash_margin_deposit_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDeposit4IsModified();


  /** 
   * <em>cash_margin_deposit_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCashMarginDeposit5();


  /** 
   * <em>cash_margin_deposit_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCashMarginDeposit5IsNull();


  /** 
   * <em>cash_margin_deposit_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDeposit5IsSet();


  /** 
   * <em>cash_margin_deposit_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDeposit5IsModified();


  /** 
   * <em>contract_amount_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAmount0();


  /** 
   * <em>contract_amount_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAmount0IsNull();


  /** 
   * <em>contract_amount_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmount0IsSet();


  /** 
   * <em>contract_amount_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmount0IsModified();


  /** 
   * <em>contract_amount_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAmount1();


  /** 
   * <em>contract_amount_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAmount1IsNull();


  /** 
   * <em>contract_amount_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmount1IsSet();


  /** 
   * <em>contract_amount_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmount1IsModified();


  /** 
   * <em>contract_amount_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAmount2();


  /** 
   * <em>contract_amount_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAmount2IsNull();


  /** 
   * <em>contract_amount_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmount2IsSet();


  /** 
   * <em>contract_amount_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmount2IsModified();


  /** 
   * <em>contract_amount_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAmount3();


  /** 
   * <em>contract_amount_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAmount3IsNull();


  /** 
   * <em>contract_amount_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmount3IsSet();


  /** 
   * <em>contract_amount_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmount3IsModified();


  /** 
   * <em>contract_amount_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAmount4();


  /** 
   * <em>contract_amount_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAmount4IsNull();


  /** 
   * <em>contract_amount_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmount4IsSet();


  /** 
   * <em>contract_amount_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmount4IsModified();


  /** 
   * <em>contract_amount_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAmount5();


  /** 
   * <em>contract_amount_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAmount5IsNull();


  /** 
   * <em>contract_amount_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmount5IsSet();


  /** 
   * <em>contract_amount_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmount5IsModified();


  /** 
   * <em>margin_deposit_rate_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginDepositRate0();


  /** 
   * <em>margin_deposit_rate_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDepositRate0IsNull();


  /** 
   * <em>margin_deposit_rate_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRate0IsSet();


  /** 
   * <em>margin_deposit_rate_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRate0IsModified();


  /** 
   * <em>margin_deposit_rate_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginDepositRate1();


  /** 
   * <em>margin_deposit_rate_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDepositRate1IsNull();


  /** 
   * <em>margin_deposit_rate_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRate1IsSet();


  /** 
   * <em>margin_deposit_rate_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRate1IsModified();


  /** 
   * <em>margin_deposit_rate_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginDepositRate2();


  /** 
   * <em>margin_deposit_rate_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDepositRate2IsNull();


  /** 
   * <em>margin_deposit_rate_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRate2IsSet();


  /** 
   * <em>margin_deposit_rate_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRate2IsModified();


  /** 
   * <em>margin_deposit_rate_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginDepositRate3();


  /** 
   * <em>margin_deposit_rate_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDepositRate3IsNull();


  /** 
   * <em>margin_deposit_rate_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRate3IsSet();


  /** 
   * <em>margin_deposit_rate_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRate3IsModified();


  /** 
   * <em>margin_deposit_rate_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginDepositRate4();


  /** 
   * <em>margin_deposit_rate_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDepositRate4IsNull();


  /** 
   * <em>margin_deposit_rate_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRate4IsSet();


  /** 
   * <em>margin_deposit_rate_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRate4IsModified();


  /** 
   * <em>margin_deposit_rate_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginDepositRate5();


  /** 
   * <em>margin_deposit_rate_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDepositRate5IsNull();


  /** 
   * <em>margin_deposit_rate_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRate5IsSet();


  /** 
   * <em>margin_deposit_rate_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRate5IsModified();


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
   * <em>branch_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBranchCode();


  /** 
   * <em>branch_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchCodeIsSet();


  /** 
   * <em>branch_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchCodeIsModified();


  /** 
   * <em>account_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountCode();


  /** 
   * <em>account_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCodeIsSet();


  /** 
   * <em>account_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCodeIsModified();


}
@
