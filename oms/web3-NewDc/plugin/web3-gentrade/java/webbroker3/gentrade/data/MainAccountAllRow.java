head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.34.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MainAccountAllRow.java;


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
 * MainAccountAllRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>main_account_all</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link MainAccountAllRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MainAccountAllPK 
 */
public interface MainAccountAllRow extends Row {


  /** 
   * ����{@@link MainAccountAllRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "main_account_all", "session" );


  /** 
   * <em>comp_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCompCode();


  /** 
   * <em>comp_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompCodeIsSet();


  /** 
   * <em>comp_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompCodeIsModified();


  /** 
   * <em>gen_acc_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGenAccDiv();


  /** 
   * <em>gen_acc_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGenAccDivIsSet();


  /** 
   * <em>gen_acc_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGenAccDivIsModified();


  /** 
   * <em>gen_br_del_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGenBrDelDiv();


  /** 
   * <em>gen_br_del_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGenBrDelDivIsSet();


  /** 
   * <em>gen_br_del_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGenBrDelDivIsModified();


  /** 
   * <em>ruito_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRuitoAccOpenDiv();


  /** 
   * <em>ruito_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRuitoAccOpenDivIsSet();


  /** 
   * <em>ruito_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRuitoAccOpenDivIsModified();


  /** 
   * <em>margin_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarginAccOpenDiv();


  /** 
   * <em>margin_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccOpenDivIsSet();


  /** 
   * <em>margin_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccOpenDivIsModified();


  /** 
   * <em>when_issued_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getWhenIssuedAccOpenDiv();


  /** 
   * <em>when_issued_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWhenIssuedAccOpenDivIsSet();


  /** 
   * <em>when_issued_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWhenIssuedAccOpenDivIsModified();


  /** 
   * <em>report_dispatch_stop_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReportDispatchStopDiv();


  /** 
   * <em>report_dispatch_stop_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReportDispatchStopDivIsSet();


  /** 
   * <em>report_dispatch_stop_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReportDispatchStopDivIsModified();


  /** 
   * <em>doc_dispatch_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDocDispatchDiv();


  /** 
   * <em>doc_dispatch_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDocDispatchDivIsSet();


  /** 
   * <em>doc_dispatch_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDocDispatchDivIsModified();


  /** 
   * <em>gp_br_del_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpBrDelDiv();


  /** 
   * <em>gp_br_del_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpBrDelDivIsSet();


  /** 
   * <em>gp_br_del_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpBrDelDivIsModified();


  /** 
   * <em>account_open_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getAccountOpenDate();


  /** 
   * <em>account_open_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDateIsSet();


  /** 
   * <em>account_open_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDateIsModified();


  /** 
   * <em>last_update_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getLastUpdateDate();


  /** 
   * <em>last_update_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdateDateIsSet();


  /** 
   * <em>last_update_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdateDateIsModified();


  /** 
   * <em>br_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBrCode();


  /** 
   * <em>br_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBrCodeIsSet();


  /** 
   * <em>br_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBrCodeIsModified();


  /** 
   * <em>cust_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCustCode();


  /** 
   * <em>cust_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCustCodeIsSet();


  /** 
   * <em>cust_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCustCodeIsModified();


  /** 
   * <em>cust_code_cd</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCustCodeCd();


  /** 
   * <em>cust_code_cd</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCustCodeCdIsSet();


  /** 
   * <em>cust_code_cd</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCustCodeCdIsModified();


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
   * <em>rep_dispatch_stp_bd</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRepDispatchStpBd();


  /** 
   * <em>rep_dispatch_stp_bd</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepDispatchStpBdIsSet();


  /** 
   * <em>rep_dispatch_stp_bd</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepDispatchStpBdIsModified();


  /** 
   * <em>occupation</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOccupation();


  /** 
   * <em>occupation</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOccupationIsSet();


  /** 
   * <em>occupation</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOccupationIsModified();


  /** 
   * <em>safe_cont_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSafeContOpenDiv();


  /** 
   * <em>safe_cont_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSafeContOpenDivIsSet();


  /** 
   * <em>safe_cont_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSafeContOpenDivIsModified();


  /** 
   * <em>foreign_sec_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForeignSecAccOpenDiv();


  /** 
   * <em>foreign_sec_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignSecAccOpenDivIsSet();


  /** 
   * <em>foreign_sec_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignSecAccOpenDivIsModified();


  /** 
   * <em>tokuyu_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTokuyuAccOpenDiv();


  /** 
   * <em>tokuyu_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTokuyuAccOpenDivIsSet();


  /** 
   * <em>tokuyu_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTokuyuAccOpenDivIsModified();


  /** 
   * <em>gold_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGoldAccOpenDiv();


  /** 
   * <em>gold_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGoldAccOpenDivIsSet();


  /** 
   * <em>gold_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGoldAccOpenDivIsModified();


  /** 
   * <em>total_trade_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTotalTradeOpenDiv();


  /** 
   * <em>total_trade_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTotalTradeOpenDivIsSet();


  /** 
   * <em>total_trade_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTotalTradeOpenDivIsModified();


  /** 
   * <em>tie_up_loan_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTieUpLoanOpenDiv();


  /** 
   * <em>tie_up_loan_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTieUpLoanOpenDivIsSet();


  /** 
   * <em>tie_up_loan_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTieUpLoanOpenDivIsModified();


  /** 
   * <em>ifo_acc_open_div_tokyo</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoAccOpenDivTokyo();


  /** 
   * <em>ifo_acc_open_div_tokyo</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDivTokyoIsSet();


  /** 
   * <em>ifo_acc_open_div_tokyo</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDivTokyoIsModified();


  /** 
   * <em>address_unknown</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressUnknown();


  /** 
   * <em>address_unknown</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressUnknownIsSet();


  /** 
   * <em>address_unknown</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressUnknownIsModified();


  /** 
   * <em>cust_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCustDiv();


  /** 
   * <em>cust_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCustDivIsSet();


  /** 
   * <em>cust_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCustDivIsModified();


  /** 
   * <em>deposit_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDepositDiv();


  /** 
   * <em>deposit_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDepositDivIsSet();


  /** 
   * <em>deposit_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDepositDivIsModified();


  /** 
   * <em>all_substitution_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAllSubstitutionDiv();


  /** 
   * <em>all_substitution_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAllSubstitutionDivIsSet();


  /** 
   * <em>all_substitution_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAllSubstitutionDivIsModified();


  /** 
   * <em>ins_loan_bill_mthd_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInsLoanBillMthdDiv();


  /** 
   * <em>ins_loan_bill_mthd_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsLoanBillMthdDivIsSet();


  /** 
   * <em>ins_loan_bill_mthd_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsLoanBillMthdDivIsModified();


  /** 
   * <em>ins_loan_cer_mthd_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInsLoanCerMthdDiv();


  /** 
   * <em>ins_loan_cer_mthd_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsLoanCerMthdDivIsSet();


  /** 
   * <em>ins_loan_cer_mthd_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsLoanCerMthdDivIsModified();


  /** 
   * <em>ins_loan_clause_mthd_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInsLoanClauseMthdDiv();


  /** 
   * <em>ins_loan_clause_mthd_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsLoanClauseMthdDivIsSet();


  /** 
   * <em>ins_loan_clause_mthd_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsLoanClauseMthdDivIsModified();


  /** 
   * <em>dom_tax_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDomTaxDiv();


  /** 
   * <em>dom_tax_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDomTaxDivIsSet();


  /** 
   * <em>dom_tax_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDomTaxDivIsModified();


  /** 
   * <em>client_trader_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getClientTraderCode();


  /** 
   * <em>client_trader_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClientTraderCodeIsSet();


  /** 
   * <em>client_trader_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClientTraderCodeIsModified();


  /** 
   * <em>telephone</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTelephone();


  /** 
   * <em>telephone</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTelephoneIsSet();


  /** 
   * <em>telephone</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTelephoneIsModified();


  /** 
   * <em>family_name_alt1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFamilyNameAlt1();


  /** 
   * <em>family_name_alt1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyNameAlt1IsSet();


  /** 
   * <em>family_name_alt1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyNameAlt1IsModified();


  /** 
   * <em>zip_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getZipCode();


  /** 
   * <em>zip_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getZipCodeIsSet();


  /** 
   * <em>zip_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getZipCodeIsModified();


  /** 
   * <em>prefecture</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPrefecture();


  /** 
   * <em>prefecture</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPrefectureIsSet();


  /** 
   * <em>prefecture</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPrefectureIsModified();


  /** 
   * <em>comma</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComma();


  /** 
   * <em>comma</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCommaIsSet();


  /** 
   * <em>comma</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCommaIsModified();


  /** 
   * <em>address_line1_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressLine1Kana();


  /** 
   * <em>address_line1_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine1KanaIsSet();


  /** 
   * <em>address_line1_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine1KanaIsModified();


  /** 
   * <em>address_line2_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressLine2Kana();


  /** 
   * <em>address_line2_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine2KanaIsSet();


  /** 
   * <em>address_line2_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine2KanaIsModified();


  /** 
   * <em>address_line3_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressLine3Kana();


  /** 
   * <em>address_line3_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine3KanaIsSet();


  /** 
   * <em>address_line3_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine3KanaIsModified();


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
   * <em>address_line1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressLine1();


  /** 
   * <em>address_line1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine1IsSet();


  /** 
   * <em>address_line1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine1IsModified();


  /** 
   * <em>address_line2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressLine2();


  /** 
   * <em>address_line2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine2IsSet();


  /** 
   * <em>address_line2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine2IsModified();


  /** 
   * <em>address_line3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressLine3();


  /** 
   * <em>address_line3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine3IsSet();


  /** 
   * <em>address_line3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine3IsModified();


  /** 
   * <em>contact_address_telephone</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getContactAddressTelephone();


  /** 
   * <em>contact_address_telephone</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactAddressTelephoneIsSet();


  /** 
   * <em>contact_address_telephone</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactAddressTelephoneIsModified();


  /** 
   * <em>contact_address</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getContactAddress();


  /** 
   * <em>contact_address</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactAddressIsSet();


  /** 
   * <em>contact_address</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactAddressIsModified();


  /** 
   * <em>era_born</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEraBorn();


  /** 
   * <em>era_born</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEraBornIsSet();


  /** 
   * <em>era_born</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEraBornIsModified();


  /** 
   * <em>born_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBornDate();


  /** 
   * <em>born_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBornDateIsSet();


  /** 
   * <em>born_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBornDateIsModified();


  /** 
   * <em>sex</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSex();


  /** 
   * <em>sex</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSexIsSet();


  /** 
   * <em>sex</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSexIsModified();


  /** 
   * <em>before_acc_trans_br_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBeforeAccTransBrCode();


  /** 
   * <em>before_acc_trans_br_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBeforeAccTransBrCodeIsSet();


  /** 
   * <em>before_acc_trans_br_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBeforeAccTransBrCodeIsModified();


  /** 
   * <em>before_acc_trans_cust_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBeforeAccTransCustCode();


  /** 
   * <em>before_acc_trans_cust_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBeforeAccTransCustCodeIsSet();


  /** 
   * <em>before_acc_trans_cust_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBeforeAccTransCustCodeIsModified();


  /** 
   * <em>d_card_appli_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getDCardAppliDate();


  /** 
   * <em>d_card_appli_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardAppliDateIsSet();


  /** 
   * <em>d_card_appli_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardAppliDateIsModified();


  /** 
   * <em>d_card_issue_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getDCardIssueDate();


  /** 
   * <em>d_card_issue_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardIssueDateIsSet();


  /** 
   * <em>d_card_issue_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardIssueDateIsModified();


  /** 
   * <em>d_card_password</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDCardPassword();


  /** 
   * <em>d_card_password</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardPasswordIsSet();


  /** 
   * <em>d_card_password</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardPasswordIsModified();


  /** 
   * <em>d_card_issue_reason</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDCardIssueReason();


  /** 
   * <em>d_card_issue_reason</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardIssueReasonIsSet();


  /** 
   * <em>d_card_issue_reason</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardIssueReasonIsModified();


  /** 
   * <em>d_card_issue_number</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDCardIssueNumber();


  /** 
   * <em>d_card_issue_number</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardIssueNumberIsSet();


  /** 
   * <em>d_card_issue_number</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardIssueNumberIsModified();


  /** 
   * <em>d_card_stop_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getDCardStopDate();


  /** 
   * <em>d_card_stop_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardStopDateIsSet();


  /** 
   * <em>d_card_stop_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardStopDateIsModified();


  /** 
   * <em>d_card_stop_reason</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDCardStopReason();


  /** 
   * <em>d_card_stop_reason</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardStopReasonIsSet();


  /** 
   * <em>d_card_stop_reason</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardStopReasonIsModified();


  /** 
   * <em>d_card_cancel_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getDCardCancelDate();


  /** 
   * <em>d_card_cancel_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardCancelDateIsSet();


  /** 
   * <em>d_card_cancel_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardCancelDateIsModified();


  /** 
   * <em>d_card_cancel_reason</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDCardCancelReason();


  /** 
   * <em>d_card_cancel_reason</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardCancelReasonIsSet();


  /** 
   * <em>d_card_cancel_reason</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardCancelReasonIsModified();


  /** 
   * <em>d_card_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDCardName();


  /** 
   * <em>d_card_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardNameIsSet();


  /** 
   * <em>d_card_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDCardNameIsModified();


  /** 
   * <em>pass_err_code_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPassErrCodeType();


  /** 
   * <em>pass_err_code_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPassErrCodeTypeIsSet();


  /** 
   * <em>pass_err_code_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPassErrCodeTypeIsModified();


  /** 
   * <em>pass_err_code_number</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPassErrCodeNumber();


  /** 
   * <em>pass_err_code_number</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPassErrCodeNumberIsSet();


  /** 
   * <em>pass_err_code_number</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPassErrCodeNumberIsModified();


  /** 
   * <em>pass_err_code_chg_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getPassErrCodeChgDate();


  /** 
   * <em>pass_err_code_chg_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPassErrCodeChgDateIsSet();


  /** 
   * <em>pass_err_code_chg_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPassErrCodeChgDateIsModified();


  /** 
   * <em>pass_err_code_chg_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPassErrCodeChgTime();


  /** 
   * <em>pass_err_code_chg_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPassErrCodeChgTimeIsSet();


  /** 
   * <em>pass_err_code_chg_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPassErrCodeChgTimeIsModified();


  /** 
   * <em>ans_cust_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAnsCustDiv();


  /** 
   * <em>ans_cust_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAnsCustDivIsSet();


  /** 
   * <em>ans_cust_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAnsCustDivIsModified();


  /** 
   * <em>ans_stock_appli_cate</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getAnsStockAppliCate();


  /** 
   * <em>ans_stock_appli_cate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAnsStockAppliCateIsSet();


  /** 
   * <em>ans_stock_appli_cate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAnsStockAppliCateIsModified();


  /** 
   * <em>dummy1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy1();


  /** 
   * <em>dummy1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy1IsSet();


  /** 
   * <em>dummy1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy1IsModified();


  /** 
   * <em>self_assessed_sep_tax</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSelfAssessedSepTax();


  /** 
   * <em>self_assessed_sep_tax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSelfAssessedSepTaxIsSet();


  /** 
   * <em>self_assessed_sep_tax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSelfAssessedSepTaxIsModified();


  /** 
   * <em>tokuyu_appli_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTokuyuAppliDiv();


  /** 
   * <em>tokuyu_appli_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTokuyuAppliDivIsSet();


  /** 
   * <em>tokuyu_appli_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTokuyuAppliDivIsModified();


  /** 
   * <em>maruyu_appli_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMaruyuAppliDiv();


  /** 
   * <em>maruyu_appli_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaruyuAppliDivIsSet();


  /** 
   * <em>maruyu_appli_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaruyuAppliDivIsModified();


  /** 
   * <em>tokuyu_laundering_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTokuyuLaunderingDiv();


  /** 
   * <em>tokuyu_laundering_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTokuyuLaunderingDivIsSet();


  /** 
   * <em>tokuyu_laundering_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTokuyuLaunderingDivIsModified();


  /** 
   * <em>maruyu_laundering_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMaruyuLaunderingDiv();


  /** 
   * <em>maruyu_laundering_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaruyuLaunderingDivIsSet();


  /** 
   * <em>maruyu_laundering_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaruyuLaunderingDivIsModified();


  /** 
   * <em>tax_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTaxDiv();


  /** 
   * <em>tax_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxDivIsSet();


  /** 
   * <em>tax_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxDivIsModified();


  /** 
   * <em>dummy2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy2();


  /** 
   * <em>dummy2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy2IsSet();


  /** 
   * <em>dummy2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy2IsModified();


  /** 
   * <em>ht_settlement_way</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHtSettlementWay();


  /** 
   * <em>ht_settlement_way</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHtSettlementWayIsSet();


  /** 
   * <em>ht_settlement_way</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHtSettlementWayIsModified();


  /** 
   * <em>dummy3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy3();


  /** 
   * <em>dummy3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy3IsSet();


  /** 
   * <em>dummy3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy3IsModified();


  /** 
   * <em>total_tax_identity</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTotalTaxIdentity();


  /** 
   * <em>total_tax_identity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTotalTaxIdentityIsSet();


  /** 
   * <em>total_tax_identity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTotalTaxIdentityIsModified();


  /** 
   * <em>dummy4</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy4();


  /** 
   * <em>dummy4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy4IsSet();


  /** 
   * <em>dummy4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy4IsModified();


  /** 
   * <em>ifo_acc_open_div_osaka</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoAccOpenDivOsaka();


  /** 
   * <em>ifo_acc_open_div_osaka</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDivOsakaIsSet();


  /** 
   * <em>ifo_acc_open_div_osaka</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDivOsakaIsModified();


  /** 
   * <em>mort_trade_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMortTradeOpenDiv();


  /** 
   * <em>mort_trade_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMortTradeOpenDivIsSet();


  /** 
   * <em>mort_trade_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMortTradeOpenDivIsModified();


  /** 
   * <em>ifo_acc_open_div_nagoya</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoAccOpenDivNagoya();


  /** 
   * <em>ifo_acc_open_div_nagoya</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDivNagoyaIsSet();


  /** 
   * <em>ifo_acc_open_div_nagoya</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDivNagoyaIsModified();


  /** 
   * <em>os_fnc_futures_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOsFncFuturesOpenDiv();


  /** 
   * <em>os_fnc_futures_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOsFncFuturesOpenDivIsSet();


  /** 
   * <em>os_fnc_futures_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOsFncFuturesOpenDivIsModified();


  /** 
   * <em>os_sec_futures_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOsSecFuturesOpenDiv();


  /** 
   * <em>os_sec_futures_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOsSecFuturesOpenDivIsSet();


  /** 
   * <em>os_sec_futures_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOsSecFuturesOpenDivIsModified();


  /** 
   * <em>tokyo_fnc_futures_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTokyoFncFuturesOpenDiv();


  /** 
   * <em>tokyo_fnc_futures_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTokyoFncFuturesOpenDivIsSet();


  /** 
   * <em>tokyo_fnc_futures_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTokyoFncFuturesOpenDivIsModified();


  /** 
   * <em>for_war_trade_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForWarTradeOpenDiv();


  /** 
   * <em>for_war_trade_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForWarTradeOpenDivIsSet();


  /** 
   * <em>for_war_trade_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForWarTradeOpenDivIsModified();


  /** 
   * <em>tentou_trade_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTentouTradeOpenDiv();


  /** 
   * <em>tentou_trade_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTentouTradeOpenDivIsSet();


  /** 
   * <em>tentou_trade_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTentouTradeOpenDivIsModified();


  /** 
   * <em>represent_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRepresentDiv();


  /** 
   * <em>represent_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepresentDivIsSet();


  /** 
   * <em>represent_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepresentDivIsModified();


  /** 
   * <em>family_nurturer_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFamilyNurturerCode();


  /** 
   * <em>family_nurturer_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyNurturerCodeIsSet();


  /** 
   * <em>family_nurturer_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyNurturerCodeIsModified();


  /** 
   * <em>family_unit_adimin</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFamilyUnitAdimin();


  /** 
   * <em>family_unit_adimin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyUnitAdiminIsSet();


  /** 
   * <em>family_unit_adimin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyUnitAdiminIsModified();


  /** 
   * <em>new_monthly_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNewMonthlyDiv();


  /** 
   * <em>new_monthly_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewMonthlyDivIsSet();


  /** 
   * <em>new_monthly_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewMonthlyDivIsModified();


  /** 
   * <em>statement_skip1_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStatementSkip1Div();


  /** 
   * <em>statement_skip1_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatementSkip1DivIsSet();


  /** 
   * <em>statement_skip1_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatementSkip1DivIsModified();


  /** 
   * <em>statement_skip2_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStatementSkip2Div();


  /** 
   * <em>statement_skip2_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatementSkip2DivIsSet();


  /** 
   * <em>statement_skip2_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatementSkip2DivIsModified();


  /** 
   * <em>family_unit_del_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFamilyUnitDelDiv();


  /** 
   * <em>family_unit_del_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyUnitDelDivIsSet();


  /** 
   * <em>family_unit_del_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyUnitDelDivIsModified();


  /** 
   * <em>dummy5</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy5();


  /** 
   * <em>dummy5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy5IsSet();


  /** 
   * <em>dummy5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy5IsModified();


  /** 
   * <em>br_dispatch_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBrDispatchDiv();


  /** 
   * <em>br_dispatch_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBrDispatchDivIsSet();


  /** 
   * <em>br_dispatch_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBrDispatchDivIsModified();


  /** 
   * <em>maruyu_grade_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMaruyuGradeDiv();


  /** 
   * <em>maruyu_grade_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaruyuGradeDivIsSet();


  /** 
   * <em>maruyu_grade_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaruyuGradeDivIsModified();


  /** 
   * <em>dummy6</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy6();


  /** 
   * <em>dummy6</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy6IsSet();


  /** 
   * <em>dummy6</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy6IsModified();


  /** 
   * <em>dummy7</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy7();


  /** 
   * <em>dummy7</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy7IsSet();


  /** 
   * <em>dummy7</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy7IsModified();


  /** 
   * <em>trans_tax_div_acc_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getTransTaxDivAccDate();


  /** 
   * <em>trans_tax_div_acc_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransTaxDivAccDateIsSet();


  /** 
   * <em>trans_tax_div_acc_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransTaxDivAccDateIsModified();


  /** 
   * <em>trans_tax_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTransTaxDiv();


  /** 
   * <em>trans_tax_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransTaxDivIsSet();


  /** 
   * <em>trans_tax_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransTaxDivIsModified();


  /** 
   * <em>resident</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getResident();


  /** 
   * <em>resident</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getResidentIsSet();


  /** 
   * <em>resident</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getResidentIsModified();


  /** 
   * <em>bond_d_and_c_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBondDAndCOpenDiv();


  /** 
   * <em>bond_d_and_c_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondDAndCOpenDivIsSet();


  /** 
   * <em>bond_d_and_c_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondDAndCOpenDivIsModified();


  /** 
   * <em>os_cd_cp_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOsCdCpOpenDiv();


  /** 
   * <em>os_cd_cp_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOsCdCpOpenDivIsSet();


  /** 
   * <em>os_cd_cp_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOsCdCpOpenDivIsModified();


  /** 
   * <em>new_tb_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNewTbOpenDiv();


  /** 
   * <em>new_tb_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewTbOpenDivIsSet();


  /** 
   * <em>new_tb_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewTbOpenDivIsModified();


  /** 
   * <em>dom_cp_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDomCpOpenDiv();


  /** 
   * <em>dom_cp_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDomCpOpenDivIsSet();


  /** 
   * <em>dom_cp_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDomCpOpenDivIsModified();


  /** 
   * <em>bond_tentou_op_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBondTentouOpOpenDiv();


  /** 
   * <em>bond_tentou_op_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondTentouOpOpenDivIsSet();


  /** 
   * <em>bond_tentou_op_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondTentouOpOpenDivIsModified();


  /** 
   * <em>t_bond_futures_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTBondFuturesOpenDiv();


  /** 
   * <em>t_bond_futures_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTBondFuturesOpenDivIsSet();


  /** 
   * <em>t_bond_futures_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTBondFuturesOpenDivIsModified();


  /** 
   * <em>dom_war_trade_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDomWarTradeOpenDiv();


  /** 
   * <em>dom_war_trade_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDomWarTradeOpenDivIsSet();


  /** 
   * <em>dom_war_trade_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDomWarTradeOpenDivIsModified();


  /** 
   * <em>dummy8</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy8();


  /** 
   * <em>dummy8</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy8IsSet();


  /** 
   * <em>dummy8</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy8IsModified();


  /** 
   * <em>dom_for_bond_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDomForBondOpenDiv();


  /** 
   * <em>dom_for_bond_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDomForBondOpenDivIsSet();


  /** 
   * <em>dom_for_bond_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDomForBondOpenDivIsModified();


  /** 
   * <em>tentou_sp_rule_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTentouSpRuleOpenDiv();


  /** 
   * <em>tentou_sp_rule_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTentouSpRuleOpenDivIsSet();


  /** 
   * <em>tentou_sp_rule_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTentouSpRuleOpenDivIsModified();


  /** 
   * <em>dummy9</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy9();


  /** 
   * <em>dummy9</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy9IsSet();


  /** 
   * <em>dummy9</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy9IsModified();


  /** 
   * <em>dummy10</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy10();


  /** 
   * <em>dummy10</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy10IsSet();


  /** 
   * <em>dummy10</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy10IsModified();


  /** 
   * <em>dummy11</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy11();


  /** 
   * <em>dummy11</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy11IsSet();


  /** 
   * <em>dummy11</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy11IsModified();


  /** 
   * <em>dummy12</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy12();


  /** 
   * <em>dummy12</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy12IsSet();


  /** 
   * <em>dummy12</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy12IsModified();


  /** 
   * <em>dummy13</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy13();


  /** 
   * <em>dummy13</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy13IsSet();


  /** 
   * <em>dummy13</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy13IsModified();


  /** 
   * <em>mrf_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMrfAccOpenDiv();


  /** 
   * <em>mrf_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMrfAccOpenDivIsSet();


  /** 
   * <em>mrf_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMrfAccOpenDivIsModified();


  /** 
   * <em>safe_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSafeContDiv();


  /** 
   * <em>safe_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSafeContDivIsSet();


  /** 
   * <em>safe_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSafeContDivIsModified();


  /** 
   * <em>foreign_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForeignContDiv();


  /** 
   * <em>foreign_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignContDivIsSet();


  /** 
   * <em>foreign_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignContDivIsModified();


  /** 
   * <em>dummy14</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy14();


  /** 
   * <em>dummy14</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy14IsSet();


  /** 
   * <em>dummy14</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy14IsModified();


  /** 
   * <em>gold_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGoldContDiv();


  /** 
   * <em>gold_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGoldContDivIsSet();


  /** 
   * <em>gold_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGoldContDivIsModified();


  /** 
   * <em>margin_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarginContDiv();


  /** 
   * <em>margin_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginContDivIsSet();


  /** 
   * <em>margin_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginContDivIsModified();


  /** 
   * <em>when_issued_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getWhenIssuedContDiv();


  /** 
   * <em>when_issued_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWhenIssuedContDivIsSet();


  /** 
   * <em>when_issued_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWhenIssuedContDivIsModified();


  /** 
   * <em>futures_op_cont_div_tokyo</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFuturesOpContDivTokyo();


  /** 
   * <em>futures_op_cont_div_tokyo</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFuturesOpContDivTokyoIsSet();


  /** 
   * <em>futures_op_cont_div_tokyo</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFuturesOpContDivTokyoIsModified();


  /** 
   * <em>dummy15</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy15();


  /** 
   * <em>dummy15</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy15IsSet();


  /** 
   * <em>dummy15</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy15IsModified();


  /** 
   * <em>futures_op_cont_div_osaka</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFuturesOpContDivOsaka();


  /** 
   * <em>futures_op_cont_div_osaka</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFuturesOpContDivOsakaIsSet();


  /** 
   * <em>futures_op_cont_div_osaka</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFuturesOpContDivOsakaIsModified();


  /** 
   * <em>tokyo_mothers_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTokyoMothersContDiv();


  /** 
   * <em>tokyo_mothers_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTokyoMothersContDivIsSet();


  /** 
   * <em>tokyo_mothers_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTokyoMothersContDivIsModified();


  /** 
   * <em>futures_op_cont_div_nagoya</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFuturesOpContDivNagoya();


  /** 
   * <em>futures_op_cont_div_nagoya</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFuturesOpContDivNagoyaIsSet();


  /** 
   * <em>futures_op_cont_div_nagoya</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFuturesOpContDivNagoyaIsModified();


  /** 
   * <em>nq_j_gl_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNqJGlContDiv();


  /** 
   * <em>nq_j_gl_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNqJGlContDivIsSet();


  /** 
   * <em>nq_j_gl_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNqJGlContDivIsModified();


  /** 
   * <em>osaka_nw_mkt_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOsakaNwMktContDiv();


  /** 
   * <em>osaka_nw_mkt_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOsakaNwMktContDivIsSet();


  /** 
   * <em>osaka_nw_mkt_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOsakaNwMktContDivIsModified();


  /** 
   * <em>nagoya_grw_cpy_mkt_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNagoyaGrwCpyMktContDiv();


  /** 
   * <em>nagoya_grw_cpy_mkt_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNagoyaGrwCpyMktContDivIsSet();


  /** 
   * <em>nagoya_grw_cpy_mkt_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNagoyaGrwCpyMktContDivIsModified();


  /** 
   * <em>for_war_trade_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForWarTradeContDiv();


  /** 
   * <em>for_war_trade_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForWarTradeContDivIsSet();


  /** 
   * <em>for_war_trade_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForWarTradeContDivIsModified();


  /** 
   * <em>tentou_trade_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTentouTradeContDiv();


  /** 
   * <em>tentou_trade_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTentouTradeContDivIsSet();


  /** 
   * <em>tentou_trade_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTentouTradeContDivIsModified();


  /** 
   * <em>bond_d_and_c_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBondDAndCContDiv();


  /** 
   * <em>bond_d_and_c_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondDAndCContDivIsSet();


  /** 
   * <em>bond_d_and_c_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondDAndCContDivIsModified();


  /** 
   * <em>sapporo_amb_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSapporoAmbContDiv();


  /** 
   * <em>sapporo_amb_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSapporoAmbContDivIsSet();


  /** 
   * <em>sapporo_amb_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSapporoAmbContDivIsModified();


  /** 
   * <em>new_tb_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNewTbContDiv();


  /** 
   * <em>new_tb_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewTbContDivIsSet();


  /** 
   * <em>new_tb_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewTbContDivIsModified();


  /** 
   * <em>gen_credit_acc_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGenCreditAccContDiv();


  /** 
   * <em>gen_credit_acc_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGenCreditAccContDivIsSet();


  /** 
   * <em>gen_credit_acc_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGenCreditAccContDivIsModified();


  /** 
   * <em>bond_tentou_op_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBondTentouOpContDiv();


  /** 
   * <em>bond_tentou_op_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondTentouOpContDivIsSet();


  /** 
   * <em>bond_tentou_op_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondTentouOpContDivIsModified();


  /** 
   * <em>fukuoka_qb_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFukuokaQbContDiv();


  /** 
   * <em>fukuoka_qb_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFukuokaQbContDivIsSet();


  /** 
   * <em>fukuoka_qb_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFukuokaQbContDivIsModified();


  /** 
   * <em>dom_war_trade_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDomWarTradeContDiv();


  /** 
   * <em>dom_war_trade_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDomWarTradeContDivIsSet();


  /** 
   * <em>dom_war_trade_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDomWarTradeContDivIsModified();


  /** 
   * <em>tentou_sec_basis_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTentouSecBasisContDiv();


  /** 
   * <em>tentou_sec_basis_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTentouSecBasisContDivIsSet();


  /** 
   * <em>tentou_sec_basis_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTentouSecBasisContDivIsModified();


  /** 
   * <em>dom_for_bond_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDomForBondContDiv();


  /** 
   * <em>dom_for_bond_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDomForBondContDivIsSet();


  /** 
   * <em>dom_for_bond_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDomForBondContDivIsModified();


  /** 
   * <em>tentou_sp_rule_cont_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTentouSpRuleContDiv();


  /** 
   * <em>tentou_sp_rule_cont_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTentouSpRuleContDivIsSet();


  /** 
   * <em>tentou_sp_rule_cont_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTentouSpRuleContDivIsModified();


  /** 
   * <em>dummy16</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy16();


  /** 
   * <em>dummy16</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy16IsSet();


  /** 
   * <em>dummy16</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy16IsModified();


  /** 
   * <em>dummy17</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy17();


  /** 
   * <em>dummy17</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy17IsSet();


  /** 
   * <em>dummy17</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy17IsModified();


  /** 
   * <em>dummy18</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy18();


  /** 
   * <em>dummy18</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy18IsSet();


  /** 
   * <em>dummy18</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy18IsModified();


  /** 
   * <em>dummy19</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy19();


  /** 
   * <em>dummy19</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy19IsSet();


  /** 
   * <em>dummy19</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy19IsModified();


  /** 
   * <em>dummy20</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy20();


  /** 
   * <em>dummy20</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy20IsSet();


  /** 
   * <em>dummy20</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy20IsModified();


  /** 
   * <em>mrf_contract_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMrfContractDiv();


  /** 
   * <em>mrf_contract_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMrfContractDivIsSet();


  /** 
   * <em>mrf_contract_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMrfContractDivIsModified();


  /** 
   * <em>new_account_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNewAccountDiv();


  /** 
   * <em>new_account_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewAccountDivIsSet();


  /** 
   * <em>new_account_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewAccountDivIsModified();


  /** 
   * <em>via_trust_bank_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getViaTrustBankDiv();


  /** 
   * <em>via_trust_bank_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getViaTrustBankDivIsSet();


  /** 
   * <em>via_trust_bank_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getViaTrustBankDivIsModified();


  /** 
   * <em>class_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getClassDiv();


  /** 
   * <em>class_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClassDivIsSet();


  /** 
   * <em>class_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClassDivIsModified();


  /** 
   * <em>address_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressCode();


  /** 
   * <em>address_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressCodeIsSet();


  /** 
   * <em>address_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressCodeIsModified();


  /** 
   * <em>person_identify</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPersonIdentify();


  /** 
   * <em>person_identify</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPersonIdentifyIsSet();


  /** 
   * <em>person_identify</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPersonIdentifyIsModified();


  /** 
   * <em>cancel_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCancelDiv();


  /** 
   * <em>cancel_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCancelDivIsSet();


  /** 
   * <em>cancel_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCancelDivIsModified();


  /** 
   * <em>add_chg_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddChgDiv();


  /** 
   * <em>add_chg_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddChgDivIsSet();


  /** 
   * <em>add_chg_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddChgDivIsModified();


  /** 
   * <em>reserve</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReserve();


  /** 
   * <em>reserve</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReserveIsSet();


  /** 
   * <em>reserve</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReserveIsModified();


  /** 
   * <em>org_deposit_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrgDepositDiv();


  /** 
   * <em>org_deposit_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrgDepositDivIsSet();


  /** 
   * <em>org_deposit_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrgDepositDivIsModified();


  /** 
   * <em>eq_hold_rep_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEqHoldRepDiv();


  /** 
   * <em>eq_hold_rep_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEqHoldRepDivIsSet();


  /** 
   * <em>eq_hold_rep_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEqHoldRepDivIsModified();


  /** 
   * <em>chkup_rep_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getChkupRepDiv();


  /** 
   * <em>chkup_rep_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getChkupRepDivIsSet();


  /** 
   * <em>chkup_rep_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getChkupRepDivIsModified();


  /** 
   * <em>fax</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFax();


  /** 
   * <em>fax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFaxIsSet();


  /** 
   * <em>fax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFaxIsModified();


  /** 
   * <em>hq_input_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHqInputDiv();


  /** 
   * <em>hq_input_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHqInputDivIsSet();


  /** 
   * <em>hq_input_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHqInputDivIsModified();


  /** 
   * <em>yellow_customer</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getYellowCustomer();


  /** 
   * <em>yellow_customer</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getYellowCustomerIsSet();


  /** 
   * <em>yellow_customer</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getYellowCustomerIsModified();


  /** 
   * <em>dummy21</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy21();


  /** 
   * <em>dummy21</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy21IsSet();


  /** 
   * <em>dummy21</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy21IsModified();


  /** 
   * <em>dummy22</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDummy22();


  /** 
   * <em>dummy22</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy22IsSet();


  /** 
   * <em>dummy22</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDummy22IsModified();


  /** 
   * <em>bond_butt_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBondButtDiv();


  /** 
   * <em>bond_butt_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondButtDivIsSet();


  /** 
   * <em>bond_butt_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondButtDivIsModified();


  /** 
   * <em>hofuri_entry</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHofuriEntry();


  /** 
   * <em>hofuri_entry</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHofuriEntryIsSet();


  /** 
   * <em>hofuri_entry</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHofuriEntryIsModified();


  /** 
   * <em>agreed_non_pkg_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgreedNonPkgDiv();


  /** 
   * <em>agreed_non_pkg_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgreedNonPkgDivIsSet();


  /** 
   * <em>agreed_non_pkg_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgreedNonPkgDivIsModified();


  /** 
   * <em>position_report_cycle_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPositionReportCycleDiv();


  /** 
   * <em>position_report_cycle_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPositionReportCycleDivIsSet();


  /** 
   * <em>position_report_cycle_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPositionReportCycleDivIsModified();


  /** 
   * <em>position_report_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPositionReportDiv();


  /** 
   * <em>position_report_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPositionReportDivIsSet();


  /** 
   * <em>position_report_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPositionReportDivIsModified();


  /** 
   * <em>certificate_deposit_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCertificateDepositFlag();


  /** 
   * <em>certificate_deposit_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCertificateDepositFlagIsSet();


  /** 
   * <em>certificate_deposit_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCertificateDepositFlagIsModified();


  /** 
   * <em>account_statement_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountStatementFlag();


  /** 
   * <em>account_statement_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountStatementFlagIsSet();


  /** 
   * <em>account_statement_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountStatementFlagIsModified();


  /** 
   * <em>trading_report_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTradingReportDiv();


  /** 
   * <em>trading_report_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradingReportDivIsSet();


  /** 
   * <em>trading_report_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradingReportDivIsModified();


  /** 
   * <em>inv_trast_ope_report</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInvTrastOpeReport();


  /** 
   * <em>inv_trast_ope_report</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInvTrastOpeReportIsSet();


  /** 
   * <em>inv_trast_ope_report</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInvTrastOpeReportIsModified();


  /** 
   * <em>tax_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTaxType();


  /** 
   * <em>tax_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxTypeIsSet();


  /** 
   * <em>tax_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxTypeIsModified();


  /** 
   * <em>margin_tax_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarginTaxType();


  /** 
   * <em>margin_tax_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTaxTypeIsSet();


  /** 
   * <em>margin_tax_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTaxTypeIsModified();


  /** 
   * <em>equity_sp_acc_open_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getEquitySpAccOpenDate();


  /** 
   * <em>equity_sp_acc_open_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquitySpAccOpenDateIsSet();


  /** 
   * <em>equity_sp_acc_open_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquitySpAccOpenDateIsModified();


  /** 
   * <em>margin_sp_acc_open_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getMarginSpAccOpenDate();


  /** 
   * <em>margin_sp_acc_open_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSpAccOpenDateIsSet();


  /** 
   * <em>margin_sp_acc_open_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSpAccOpenDateIsModified();


  /** 
   * <em>tax_type_last</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTaxTypeLast();


  /** 
   * <em>tax_type_last</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxTypeLastIsSet();


  /** 
   * <em>tax_type_last</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxTypeLastIsModified();


  /** 
   * <em>margin_tax_type_last</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarginTaxTypeLast();


  /** 
   * <em>margin_tax_type_last</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTaxTypeLastIsSet();


  /** 
   * <em>margin_tax_type_last</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTaxTypeLastIsModified();


  /** 
   * <em>tax_type_next</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTaxTypeNext();


  /** 
   * <em>tax_type_next</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxTypeNextIsSet();


  /** 
   * <em>tax_type_next</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxTypeNextIsModified();


  /** 
   * <em>margin_tax_type_next</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarginTaxTypeNext();


  /** 
   * <em>margin_tax_type_next</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTaxTypeNextIsSet();


  /** 
   * <em>margin_tax_type_next</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTaxTypeNextIsModified();


  /** 
   * <em>fluct_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getFluctDate();


  /** 
   * <em>fluct_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFluctDateIsSet();


  /** 
   * <em>fluct_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFluctDateIsModified();


  /** 
   * <em>margin_fluct_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getMarginFluctDate();


  /** 
   * <em>margin_fluct_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginFluctDateIsSet();


  /** 
   * <em>margin_fluct_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginFluctDateIsModified();


  /** 
   * <em>local_tax_div_last</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLocalTaxDivLast();


  /** 
   * <em>local_tax_div_last</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLocalTaxDivLastIsSet();


  /** 
   * <em>local_tax_div_last</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLocalTaxDivLastIsModified();


  /** 
   * <em>local_tax_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLocalTaxDiv();


  /** 
   * <em>local_tax_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLocalTaxDivIsSet();


  /** 
   * <em>local_tax_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLocalTaxDivIsModified();


  /** 
   * <em>local_tax_div_next</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLocalTaxDivNext();


  /** 
   * <em>local_tax_div_next</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLocalTaxDivNextIsSet();


  /** 
   * <em>local_tax_div_next</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLocalTaxDivNextIsModified();


  /** 
   * <em>qualified_inst_investor_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getQualifiedInstInvestorDiv();


  /** 
   * <em>qualified_inst_investor_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQualifiedInstInvestorDivIsSet();


  /** 
   * <em>qualified_inst_investor_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQualifiedInstInvestorDivIsModified();


  /** 
   * <em>quoto_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getQuotoType();


  /** 
   * <em>quoto_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQuotoTypeIsSet();


  /** 
   * <em>quoto_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQuotoTypeIsModified();


  /** 
   * <em>sp_acc_abolish_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getSpAccAbolishDate();


  /** 
   * <em>sp_acc_abolish_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpAccAbolishDateIsSet();


  /** 
   * <em>sp_acc_abolish_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpAccAbolishDateIsModified();


  /** 
   * <em>sp_mng_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSpMngAccOpenDiv();


  /** 
   * <em>sp_mng_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpMngAccOpenDivIsSet();


  /** 
   * <em>sp_mng_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpMngAccOpenDivIsModified();


  /** 
   * <em>reserve_area</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReserveArea();


  /** 
   * <em>reserve_area</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReserveAreaIsSet();


  /** 
   * <em>reserve_area</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReserveAreaIsModified();


  /** 
   * <em>web3_encrypted_password</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getWeb3EncryptedPassword();


  /** 
   * <em>web3_encrypted_password</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWeb3EncryptedPasswordIsSet();


  /** 
   * <em>web3_encrypted_password</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWeb3EncryptedPasswordIsModified();


  /** 
   * <em>xtrade_encrypted_password</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getXtradeEncryptedPassword();


  /** 
   * <em>xtrade_encrypted_password</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getXtradeEncryptedPasswordIsSet();


  /** 
   * <em>xtrade_encrypted_password</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getXtradeEncryptedPasswordIsModified();


  /** 
   * <em>broadcast_law</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBroadcastLaw();


  /** 
   * <em>broadcast_law</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBroadcastLawIsSet();


  /** 
   * <em>broadcast_law</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBroadcastLawIsModified();


  /** 
   * <em>aviation_law</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAviationLaw();


  /** 
   * <em>aviation_law</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAviationLawIsSet();


  /** 
   * <em>aviation_law</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAviationLawIsModified();


  /** 
   * <em>ntt_law</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNttLaw();


  /** 
   * <em>ntt_law</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNttLawIsSet();


  /** 
   * <em>ntt_law</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNttLawIsModified();


  /** 
   * <em>dividend_trans_designate</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDividendTransDesignate();


  /** 
   * <em>dividend_trans_designate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDividendTransDesignateIsSet();


  /** 
   * <em>dividend_trans_designate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDividendTransDesignateIsModified();


  /** 
   * <em>standing_proxy</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStandingProxy();


  /** 
   * <em>standing_proxy</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStandingProxyIsSet();


  /** 
   * <em>standing_proxy</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStandingProxyIsModified();


  /** 
   * <em>statutory_agent</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStatutoryAgent();


  /** 
   * <em>statutory_agent</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatutoryAgentIsSet();


  /** 
   * <em>statutory_agent</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatutoryAgentIsModified();


  /** 
   * <em>affiliate_account_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAffiliateAccountCode();


  /** 
   * <em>affiliate_account_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAffiliateAccountCodeIsSet();


  /** 
   * <em>affiliate_account_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAffiliateAccountCodeIsModified();


  /** 
   * <em>agency_notify_cmp_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgencyNotifyCmpDiv();


  /** 
   * <em>agency_notify_cmp_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyNotifyCmpDivIsSet();


  /** 
   * <em>agency_notify_cmp_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyNotifyCmpDivIsModified();


}
@
