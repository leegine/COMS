head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	AccruedInterestCalcCondRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * AccruedInterestCalcCondRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>accrued_interest_calc_cond</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link AccruedInterestCalcCondRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccruedInterestCalcCondPK 
 */
public interface AccruedInterestCalcCondRow extends Row {


  /** 
   * ����{@@link AccruedInterestCalcCondRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "accrued_interest_calc_cond", "master" );


  /** 
   * <em>accrued_interest_calc_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccruedInterestCalcType();


  /** 
   * <em>accrued_interest_calc_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccruedInterestCalcTypeIsSet();


  /** 
   * <em>accrued_interest_calc_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccruedInterestCalcTypeIsModified();


  /** 
   * <em>calc_type_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCalcTypeName();


  /** 
   * <em>calc_type_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcTypeNameIsSet();


  /** 
   * <em>calc_type_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcTypeNameIsModified();


  /** 
   * <em>base_date_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBaseDateDiv();


  /** 
   * <em>base_date_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBaseDateDivIsSet();


  /** 
   * <em>base_date_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBaseDateDivIsModified();


  /** 
   * <em>base_days_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBaseDaysDiv();


  /** 
   * <em>base_days_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBaseDaysDivIsSet();


  /** 
   * <em>base_days_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBaseDaysDivIsModified();


  /** 
   * <em>elapsed_days_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getElapsedDaysDiv();


  /** 
   * <em>elapsed_days_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getElapsedDaysDivIsSet();


  /** 
   * <em>elapsed_days_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getElapsedDaysDivIsModified();


  /** 
   * <em>non_elapsed_days_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNonElapsedDaysDiv();


  /** 
   * <em>non_elapsed_days_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNonElapsedDaysDivIsSet();


  /** 
   * <em>non_elapsed_days_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNonElapsedDaysDivIsModified();


  /** 
   * <em>taxation_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTaxationDiv();


  /** 
   * <em>taxation_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxationDivIsSet();


  /** 
   * <em>taxation_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxationDivIsModified();


  /** 
   * <em>calc_base_form</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCalcBaseForm();


  /** 
   * <em>calc_base_form</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcBaseFormIsSet();


  /** 
   * <em>calc_base_form</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcBaseFormIsModified();


  /** 
   * <em>unit_price_scale</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getUnitPriceScale();


  /** 
   * <em>unit_price_scale</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnitPriceScaleIsNull();


  /** 
   * <em>unit_price_scale</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnitPriceScaleIsSet();


  /** 
   * <em>unit_price_scale</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnitPriceScaleIsModified();


  /** 
   * <em>unit_round_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getUnitRoundDiv();


  /** 
   * <em>unit_round_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnitRoundDivIsSet();


  /** 
   * <em>unit_round_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnitRoundDivIsModified();


  /** 
   * <em>amount_round_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAmountRoundDiv();


  /** 
   * <em>amount_round_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAmountRoundDivIsSet();


  /** 
   * <em>amount_round_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAmountRoundDivIsModified();


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
