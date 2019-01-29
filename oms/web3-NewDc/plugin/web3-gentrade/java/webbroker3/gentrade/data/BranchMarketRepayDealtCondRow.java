head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.24.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchMarketRepayDealtCondRow.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * BranchMarketRepayDealtCondRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>branch_market_repay_dealt_cond</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link BranchMarketRepayDealtCondRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchMarketRepayDealtCondPK 
 */
public interface BranchMarketRepayDealtCondRow extends Row {


  /** 
   * ����{@@link BranchMarketRepayDealtCondRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "branch_market_repay_dealt_cond", "master" );


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
   * <em>market_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarketCode();


  /** 
   * <em>market_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarketCodeIsSet();


  /** 
   * <em>market_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarketCodeIsModified();


  /** 
   * <em>repayment_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRepaymentDiv();


  /** 
   * <em>repayment_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepaymentDivIsSet();


  /** 
   * <em>repayment_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepaymentDivIsModified();


  /** 
   * <em>repayment_num</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getRepaymentNum();


  /** 
   * <em>repayment_num</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepaymentNumIsSet();


  /** 
   * <em>repayment_num</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepaymentNumIsModified();


  /** 
   * <em>sonar_repayment_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSonarRepaymentType();


  /** 
   * <em>sonar_repayment_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSonarRepaymentTypeIsSet();


  /** 
   * <em>sonar_repayment_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSonarRepaymentTypeIsModified();


  /** 
   * <em>mart_can_dealt</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMartCanDealt();


  /** 
   * <em>mart_can_dealt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMartCanDealtIsSet();


  /** 
   * <em>mart_can_dealt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMartCanDealtIsModified();


  /** 
   * <em>limited_unit_m_long_1st_sec</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLimitedUnitMLong1stSec();


  /** 
   * <em>limited_unit_m_long_1st_sec</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLimitedUnitMLong1stSecIsNull();


  /** 
   * <em>limited_unit_m_long_1st_sec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitMLong1stSecIsSet();


  /** 
   * <em>limited_unit_m_long_1st_sec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitMLong1stSecIsModified();


  /** 
   * <em>limited_unit_m_long_2nd_sec</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLimitedUnitMLong2ndSec();


  /** 
   * <em>limited_unit_m_long_2nd_sec</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLimitedUnitMLong2ndSecIsNull();


  /** 
   * <em>limited_unit_m_long_2nd_sec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitMLong2ndSecIsSet();


  /** 
   * <em>limited_unit_m_long_2nd_sec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitMLong2ndSecIsModified();


  /** 
   * <em>limited_unit_m_short_1st_sec</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLimitedUnitMShort1stSec();


  /** 
   * <em>limited_unit_m_short_1st_sec</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLimitedUnitMShort1stSecIsNull();


  /** 
   * <em>limited_unit_m_short_1st_sec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitMShort1stSecIsSet();


  /** 
   * <em>limited_unit_m_short_1st_sec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitMShort1stSecIsModified();


  /** 
   * <em>limited_unit_m_short_2nd_sec</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLimitedUnitMShort2ndSec();


  /** 
   * <em>limited_unit_m_short_2nd_sec</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLimitedUnitMShort2ndSecIsNull();


  /** 
   * <em>limited_unit_m_short_2nd_sec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitMShort2ndSecIsSet();


  /** 
   * <em>limited_unit_m_short_2nd_sec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitMShort2ndSecIsModified();


  /** 
   * <em>limited_unit_cm_long_1st_sec</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLimitedUnitCmLong1stSec();


  /** 
   * <em>limited_unit_cm_long_1st_sec</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLimitedUnitCmLong1stSecIsNull();


  /** 
   * <em>limited_unit_cm_long_1st_sec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitCmLong1stSecIsSet();


  /** 
   * <em>limited_unit_cm_long_1st_sec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitCmLong1stSecIsModified();


  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLimitedUnitCmLong2ndSec();


  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLimitedUnitCmLong2ndSecIsNull();


  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitCmLong2ndSecIsSet();


  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitCmLong2ndSecIsModified();


  /** 
   * <em>limited_unit_cm_short_1st_sec</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLimitedUnitCmShort1stSec();


  /** 
   * <em>limited_unit_cm_short_1st_sec</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLimitedUnitCmShort1stSecIsNull();


  /** 
   * <em>limited_unit_cm_short_1st_sec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitCmShort1stSecIsSet();


  /** 
   * <em>limited_unit_cm_short_1st_sec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitCmShort1stSecIsModified();


  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLimitedUnitCmShort2ndSec();


  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLimitedUnitCmShort2ndSecIsNull();


  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitCmShort2ndSecIsSet();


  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLimitedUnitCmShort2ndSecIsModified();


  /** 
   * <em>cont_liquidate_term</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getContLiquidateTerm();


  /** 
   * <em>cont_liquidate_term</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContLiquidateTermIsNull();


  /** 
   * <em>cont_liquidate_term</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContLiquidateTermIsSet();


  /** 
   * <em>cont_liquidate_term</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContLiquidateTermIsModified();


  /** 
   * <em>buy_interest_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getBuyInterestRate();


  /** 
   * <em>buy_interest_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getBuyInterestRateIsNull();


  /** 
   * <em>buy_interest_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyInterestRateIsSet();


  /** 
   * <em>buy_interest_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyInterestRateIsModified();


  /** 
   * <em>sell_interest_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getSellInterestRate();


  /** 
   * <em>sell_interest_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSellInterestRateIsNull();


  /** 
   * <em>sell_interest_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellInterestRateIsSet();


  /** 
   * <em>sell_interest_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellInterestRateIsModified();


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
