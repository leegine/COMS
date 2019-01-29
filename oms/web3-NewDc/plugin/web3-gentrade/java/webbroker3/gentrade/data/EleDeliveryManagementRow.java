head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.49.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5cc4d8b0586158f;
filename	EleDeliveryManagementRow.java;


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
 * EleDeliveryManagementRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>ele_delivery_management</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link EleDeliveryManagementRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EleDeliveryManagementPK 
 */
public interface EleDeliveryManagementRow extends Row {


  /** 
   * ����{@@link EleDeliveryManagementRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "ele_delivery_management", "account" );


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
   * <em>ele_del_regi_flag</em>�R�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getEleDelRegiFlag();


  /** 
   * <em>ele_del_regi_flag</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEleDelRegiFlagIsSet();


  /** 
   * <em>ele_del_regi_upd_date</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getEleDelRegiUpdDate();


  /** 
   * <em>trading_report_div</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTradingReportDiv();


  /** 
   * <em>trading_report_reg_div</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTradingReportRegDiv();


  /** 
   * <em>trading_report_div_upd_date</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getTradingReportDivUpdDate();


  /** 
   * <em>position_report_div</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPositionReportDiv();


  /** 
   * <em>position_report_reg_div</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPositionReportRegDiv();


  /** 
   * <em>position_report_div_upd_date</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getPositionReportDivUpdDate();


  /** 
   * <em>ope_report_div</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOpeReportDiv();


  /** 
   * <em>ope_report_reg_div</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOpeReportRegDiv();


  /** 
   * <em>ope_report_div_upd_date</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getOpeReportDivUpdDate();


  /** 
   * <em>ord_rul_report_div</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrdRulReportDiv();


  /** 
   * <em>ord_rul_rep_reg_div</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrdRulRepRegDiv();


  /** 
   * <em>ord_rul_report_div_upd_date</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getOrdRulReportDivUpdDate();


  /** 
   * <em>report_div1</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReportDiv1();


  /** 
   * <em>report_reg_div1</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReportRegDiv1();


  /** 
   * <em>report_div_upd_date1</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getReportDivUpdDate1();


  /** 
   * <em>report_div2</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReportDiv2();


  /** 
   * <em>report_reg_div2</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReportRegDiv2();


  /** 
   * <em>report_div_upd_date2</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getReportDivUpdDate2();


  /** 
   * <em>report_div3</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReportDiv3();


  /** 
   * <em>report_reg_div3</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReportRegDiv3();


  /** 
   * <em>report_div_upd_date3</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getReportDivUpdDate3();


  /** 
   * <em>report_div4</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReportDiv4();


  /** 
   * <em>report_reg_div4</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReportRegDiv4();


  /** 
   * <em>report_div_upd_date4</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getReportDivUpdDate4();


  /** 
   * <em>report_div5</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReportDiv5();


  /** 
   * <em>report_reg_div5</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReportRegDiv5();


  /** 
   * <em>report_div_upd_date5</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getReportDivUpdDate5();


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
