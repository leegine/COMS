head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	IfoDepositCalcResultRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifodeposit.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * IfoDepositCalcResultRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>ifo_deposit_calc_result</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link IfoDepositCalcResultRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoDepositCalcResultPK 
 */
public interface IfoDepositCalcResultRow extends Row {


  /** 
   * ����{@@link IfoDepositCalcResultRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "ifo_deposit_calc_result", "session" );


  /** 
   * <em>deposit_info_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getDepositInfoId();


  /** 
   * <em>deposit_info_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDepositInfoIdIsSet();


  /** 
   * <em>deposit_info_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDepositInfoIdIsModified();


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
   * <em>base_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBaseDate();


  /** 
   * <em>base_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBaseDateIsSet();


  /** 
   * <em>base_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBaseDateIsModified();


  /** 
   * <em>balance_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBalance0();


  /** 
   * <em>balance_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBalance0IsSet();


  /** 
   * <em>balance_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBalance0IsModified();


  /** 
   * <em>balance_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBalance1();


  /** 
   * <em>balance_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBalance1IsSet();


  /** 
   * <em>balance_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBalance1IsModified();


  /** 
   * <em>balance_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBalance2();


  /** 
   * <em>balance_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBalance2IsSet();


  /** 
   * <em>balance_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBalance2IsModified();


  /** 
   * <em>ifo_deposit_balance_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositBalance0();


  /** 
   * <em>ifo_deposit_balance_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositBalance0IsSet();


  /** 
   * <em>ifo_deposit_balance_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositBalance0IsModified();


  /** 
   * <em>ifo_deposit_balance_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositBalance1();


  /** 
   * <em>ifo_deposit_balance_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositBalance1IsSet();


  /** 
   * <em>ifo_deposit_balance_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositBalance1IsModified();


  /** 
   * <em>ifo_deposit_balance_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositBalance2();


  /** 
   * <em>ifo_deposit_balance_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositBalance2IsSet();


  /** 
   * <em>ifo_deposit_balance_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositBalance2IsModified();


  /** 
   * <em>ifo_deposit_balance_f0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositBalanceF0();


  /** 
   * <em>ifo_deposit_balance_f0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositBalanceF0IsSet();


  /** 
   * <em>ifo_deposit_balance_f0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositBalanceF0IsModified();


  /** 
   * <em>ifo_deposit_balance_f1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositBalanceF1();


  /** 
   * <em>ifo_deposit_balance_f1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositBalanceF1IsSet();


  /** 
   * <em>ifo_deposit_balance_f1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositBalanceF1IsModified();


  /** 
   * <em>ifo_deposit_balance_f2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositBalanceF2();


  /** 
   * <em>ifo_deposit_balance_f2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositBalanceF2IsSet();


  /** 
   * <em>ifo_deposit_balance_f2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositBalanceF2IsModified();


  /** 
   * <em>today_transfer_amt_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTodayTransferAmt0();


  /** 
   * <em>today_transfer_amt_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayTransferAmt0IsSet();


  /** 
   * <em>today_transfer_amt_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayTransferAmt0IsModified();


  /** 
   * <em>today_transfer_amt_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTodayTransferAmt1();


  /** 
   * <em>today_transfer_amt_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayTransferAmt1IsSet();


  /** 
   * <em>today_transfer_amt_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayTransferAmt1IsModified();


  /** 
   * <em>today_transfer_amt_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTodayTransferAmt2();


  /** 
   * <em>today_transfer_amt_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayTransferAmt2IsSet();


  /** 
   * <em>today_transfer_amt_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayTransferAmt2IsModified();


  /** 
   * <em>today_fut_settle_profit_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTodayFutSettleProfit0();


  /** 
   * <em>today_fut_settle_profit_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayFutSettleProfit0IsSet();


  /** 
   * <em>today_fut_settle_profit_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayFutSettleProfit0IsModified();


  /** 
   * <em>today_fut_settle_profit_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTodayFutSettleProfit1();


  /** 
   * <em>today_fut_settle_profit_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayFutSettleProfit1IsSet();


  /** 
   * <em>today_fut_settle_profit_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayFutSettleProfit1IsModified();


  /** 
   * <em>today_fut_settle_profit_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTodayFutSettleProfit2();


  /** 
   * <em>today_fut_settle_profit_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayFutSettleProfit2IsSet();


  /** 
   * <em>today_fut_settle_profit_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayFutSettleProfit2IsModified();


  /** 
   * <em>today_op_net_amt_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTodayOpNetAmt0();


  /** 
   * <em>today_op_net_amt_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayOpNetAmt0IsSet();


  /** 
   * <em>today_op_net_amt_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayOpNetAmt0IsModified();


  /** 
   * <em>today_op_net_amt_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTodayOpNetAmt1();


  /** 
   * <em>today_op_net_amt_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayOpNetAmt1IsSet();


  /** 
   * <em>today_op_net_amt_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayOpNetAmt1IsModified();


  /** 
   * <em>today_op_net_amt_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTodayOpNetAmt2();


  /** 
   * <em>today_op_net_amt_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayOpNetAmt2IsSet();


  /** 
   * <em>today_op_net_amt_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayOpNetAmt2IsModified();


  /** 
   * <em>fut_eval_profit_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFutEvalProfit0();


  /** 
   * <em>fut_eval_profit_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFutEvalProfit0IsSet();


  /** 
   * <em>fut_eval_profit_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFutEvalProfit0IsModified();


  /** 
   * <em>fut_eval_profit_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFutEvalProfit1();


  /** 
   * <em>fut_eval_profit_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFutEvalProfit1IsSet();


  /** 
   * <em>fut_eval_profit_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFutEvalProfit1IsModified();


  /** 
   * <em>fut_eval_profit_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFutEvalProfit2();


  /** 
   * <em>fut_eval_profit_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFutEvalProfit2IsSet();


  /** 
   * <em>fut_eval_profit_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFutEvalProfit2IsModified();


  /** 
   * <em>long_fut_eval_profit_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLongFutEvalProfit0();


  /** 
   * <em>long_fut_eval_profit_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongFutEvalProfit0IsSet();


  /** 
   * <em>long_fut_eval_profit_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongFutEvalProfit0IsModified();


  /** 
   * <em>long_fut_eval_profit_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLongFutEvalProfit1();


  /** 
   * <em>long_fut_eval_profit_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongFutEvalProfit1IsSet();


  /** 
   * <em>long_fut_eval_profit_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongFutEvalProfit1IsModified();


  /** 
   * <em>long_fut_eval_profit_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLongFutEvalProfit2();


  /** 
   * <em>long_fut_eval_profit_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongFutEvalProfit2IsSet();


  /** 
   * <em>long_fut_eval_profit_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongFutEvalProfit2IsModified();


  /** 
   * <em>short_fut_eval_profit_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getShortFutEvalProfit0();


  /** 
   * <em>short_fut_eval_profit_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortFutEvalProfit0IsSet();


  /** 
   * <em>short_fut_eval_profit_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortFutEvalProfit0IsModified();


  /** 
   * <em>short_fut_eval_profit_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getShortFutEvalProfit1();


  /** 
   * <em>short_fut_eval_profit_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortFutEvalProfit1IsSet();


  /** 
   * <em>short_fut_eval_profit_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortFutEvalProfit1IsModified();


  /** 
   * <em>short_fut_eval_profit_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getShortFutEvalProfit2();


  /** 
   * <em>short_fut_eval_profit_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortFutEvalProfit2IsSet();


  /** 
   * <em>short_fut_eval_profit_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortFutEvalProfit2IsModified();


  /** 
   * <em>acceptance_ifo_deposit_bal_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAcceptanceIfoDepositBal0();


  /** 
   * <em>acceptance_ifo_deposit_bal_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptanceIfoDepositBal0IsSet();


  /** 
   * <em>acceptance_ifo_deposit_bal_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptanceIfoDepositBal0IsModified();


  /** 
   * <em>acceptance_ifo_deposit_bal_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAcceptanceIfoDepositBal1();


  /** 
   * <em>acceptance_ifo_deposit_bal_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptanceIfoDepositBal1IsSet();


  /** 
   * <em>acceptance_ifo_deposit_bal_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptanceIfoDepositBal1IsModified();


  /** 
   * <em>acceptance_ifo_deposit_bal_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAcceptanceIfoDepositBal2();


  /** 
   * <em>acceptance_ifo_deposit_bal_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptanceIfoDepositBal2IsSet();


  /** 
   * <em>acceptance_ifo_deposit_bal_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptanceIfoDepositBal2IsModified();


  /** 
   * <em>acceptance_ifo_deposit_bal_f0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAcceptanceIfoDepositBalF0();


  /** 
   * <em>acceptance_ifo_deposit_bal_f0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptanceIfoDepositBalF0IsSet();


  /** 
   * <em>acceptance_ifo_deposit_bal_f0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptanceIfoDepositBalF0IsModified();


  /** 
   * <em>acceptance_ifo_deposit_bal_f1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAcceptanceIfoDepositBalF1();


  /** 
   * <em>acceptance_ifo_deposit_bal_f1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptanceIfoDepositBalF1IsSet();


  /** 
   * <em>acceptance_ifo_deposit_bal_f1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptanceIfoDepositBalF1IsModified();


  /** 
   * <em>acceptance_ifo_deposit_bal_f2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAcceptanceIfoDepositBalF2();


  /** 
   * <em>acceptance_ifo_deposit_bal_f2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptanceIfoDepositBalF2IsSet();


  /** 
   * <em>acceptance_ifo_deposit_bal_f2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptanceIfoDepositBalF2IsModified();


  /** 
   * <em>net_op_value_total_amt_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNetOpValueTotalAmt0();


  /** 
   * <em>net_op_value_total_amt_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetOpValueTotalAmt0IsSet();


  /** 
   * <em>net_op_value_total_amt_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetOpValueTotalAmt0IsModified();


  /** 
   * <em>net_op_value_total_amt_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNetOpValueTotalAmt1();


  /** 
   * <em>net_op_value_total_amt_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetOpValueTotalAmt1IsSet();


  /** 
   * <em>net_op_value_total_amt_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetOpValueTotalAmt1IsModified();


  /** 
   * <em>net_op_value_total_amt_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNetOpValueTotalAmt2();


  /** 
   * <em>net_op_value_total_amt_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetOpValueTotalAmt2IsSet();


  /** 
   * <em>net_op_value_total_amt_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetOpValueTotalAmt2IsModified();


  /** 
   * <em>net_op_value_total_amt_f0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNetOpValueTotalAmtF0();


  /** 
   * <em>net_op_value_total_amt_f0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetOpValueTotalAmtF0IsSet();


  /** 
   * <em>net_op_value_total_amt_f0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetOpValueTotalAmtF0IsModified();


  /** 
   * <em>net_op_value_total_amt_f1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNetOpValueTotalAmtF1();


  /** 
   * <em>net_op_value_total_amt_f1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetOpValueTotalAmtF1IsSet();


  /** 
   * <em>net_op_value_total_amt_f1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetOpValueTotalAmtF1IsModified();


  /** 
   * <em>net_op_value_total_amt_f2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNetOpValueTotalAmtF2();


  /** 
   * <em>net_op_value_total_amt_f2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetOpValueTotalAmtF2IsSet();


  /** 
   * <em>net_op_value_total_amt_f2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetOpValueTotalAmtF2IsModified();


  /** 
   * <em>ifo_deposit_necessary_amt_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositNecessaryAmt0();


  /** 
   * <em>ifo_deposit_necessary_amt_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositNecessaryAmt0IsSet();


  /** 
   * <em>ifo_deposit_necessary_amt_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositNecessaryAmt0IsModified();


  /** 
   * <em>ifo_deposit_necessary_amt_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositNecessaryAmt1();


  /** 
   * <em>ifo_deposit_necessary_amt_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositNecessaryAmt1IsSet();


  /** 
   * <em>ifo_deposit_necessary_amt_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositNecessaryAmt1IsModified();


  /** 
   * <em>ifo_deposit_necessary_amt_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositNecessaryAmt2();


  /** 
   * <em>ifo_deposit_necessary_amt_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositNecessaryAmt2IsSet();


  /** 
   * <em>ifo_deposit_necessary_amt_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositNecessaryAmt2IsModified();


  /** 
   * <em>ifo_deposit_necessary_amt_f0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositNecessaryAmtF0();


  /** 
   * <em>ifo_deposit_necessary_amt_f0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositNecessaryAmtF0IsSet();


  /** 
   * <em>ifo_deposit_necessary_amt_f0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositNecessaryAmtF0IsModified();


  /** 
   * <em>ifo_deposit_necessary_amt_f1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositNecessaryAmtF1();


  /** 
   * <em>ifo_deposit_necessary_amt_f1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositNecessaryAmtF1IsSet();


  /** 
   * <em>ifo_deposit_necessary_amt_f1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositNecessaryAmtF1IsModified();


  /** 
   * <em>ifo_deposit_necessary_amt_f2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositNecessaryAmtF2();


  /** 
   * <em>ifo_deposit_necessary_amt_f2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositNecessaryAmtF2IsSet();


  /** 
   * <em>ifo_deposit_necessary_amt_f2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositNecessaryAmtF2IsModified();


  /** 
   * <em>ifo_deposit_power_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositPower0();


  /** 
   * <em>ifo_deposit_power_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositPower0IsSet();


  /** 
   * <em>ifo_deposit_power_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositPower0IsModified();


  /** 
   * <em>ifo_deposit_power_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositPower1();


  /** 
   * <em>ifo_deposit_power_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositPower1IsSet();


  /** 
   * <em>ifo_deposit_power_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositPower1IsModified();


  /** 
   * <em>ifo_deposit_power_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositPower2();


  /** 
   * <em>ifo_deposit_power_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositPower2IsSet();


  /** 
   * <em>ifo_deposit_power_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositPower2IsModified();


  /** 
   * <em>ifo_deposit_transfer_power_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositTransferPower0();


  /** 
   * <em>ifo_deposit_transfer_power_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositTransferPower0IsSet();


  /** 
   * <em>ifo_deposit_transfer_power_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositTransferPower0IsModified();


  /** 
   * <em>ifo_deposit_transfer_power_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositTransferPower1();


  /** 
   * <em>ifo_deposit_transfer_power_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositTransferPower1IsSet();


  /** 
   * <em>ifo_deposit_transfer_power_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositTransferPower1IsModified();


  /** 
   * <em>ifo_deposit_transfer_power_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositTransferPower2();


  /** 
   * <em>ifo_deposit_transfer_power_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositTransferPower2IsSet();


  /** 
   * <em>ifo_deposit_transfer_power_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositTransferPower2IsModified();


  /** 
   * <em>bull_quantity_nk225_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBullQuantityNk2250();


  /** 
   * <em>bull_quantity_nk225_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBullQuantityNk2250IsSet();


  /** 
   * <em>bull_quantity_nk225_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBullQuantityNk2250IsModified();


  /** 
   * <em>bull_quantity_nk225_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBullQuantityNk2251();


  /** 
   * <em>bull_quantity_nk225_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBullQuantityNk2251IsSet();


  /** 
   * <em>bull_quantity_nk225_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBullQuantityNk2251IsModified();


  /** 
   * <em>bull_quantity_nk225_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBullQuantityNk2252();


  /** 
   * <em>bull_quantity_nk225_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBullQuantityNk2252IsSet();


  /** 
   * <em>bull_quantity_nk225_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBullQuantityNk2252IsModified();


  /** 
   * <em>bear_quantity_nk225_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBearQuantityNk2250();


  /** 
   * <em>bear_quantity_nk225_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBearQuantityNk2250IsSet();


  /** 
   * <em>bear_quantity_nk225_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBearQuantityNk2250IsModified();


  /** 
   * <em>bear_quantity_nk225_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBearQuantityNk2251();


  /** 
   * <em>bear_quantity_nk225_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBearQuantityNk2251IsSet();


  /** 
   * <em>bear_quantity_nk225_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBearQuantityNk2251IsModified();


  /** 
   * <em>bear_quantity_nk225_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBearQuantityNk2252();


  /** 
   * <em>bear_quantity_nk225_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBearQuantityNk2252IsSet();


  /** 
   * <em>bear_quantity_nk225_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBearQuantityNk2252IsModified();


  /** 
   * <em>long_pos_nk225_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLongPosNk2250();


  /** 
   * <em>long_pos_nk225_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongPosNk2250IsSet();


  /** 
   * <em>long_pos_nk225_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongPosNk2250IsModified();


  /** 
   * <em>long_pos_nk225_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLongPosNk2251();


  /** 
   * <em>long_pos_nk225_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongPosNk2251IsSet();


  /** 
   * <em>long_pos_nk225_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongPosNk2251IsModified();


  /** 
   * <em>long_pos_nk225_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLongPosNk2252();


  /** 
   * <em>long_pos_nk225_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongPosNk2252IsSet();


  /** 
   * <em>long_pos_nk225_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongPosNk2252IsModified();


  /** 
   * <em>part_long_pos_nk225_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPartLongPosNk2250();


  /** 
   * <em>part_long_pos_nk225_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartLongPosNk2250IsSet();


  /** 
   * <em>part_long_pos_nk225_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartLongPosNk2250IsModified();


  /** 
   * <em>part_long_pos_nk225_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPartLongPosNk2251();


  /** 
   * <em>part_long_pos_nk225_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartLongPosNk2251IsSet();


  /** 
   * <em>part_long_pos_nk225_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartLongPosNk2251IsModified();


  /** 
   * <em>part_long_pos_nk225_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPartLongPosNk2252();


  /** 
   * <em>part_long_pos_nk225_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartLongPosNk2252IsSet();


  /** 
   * <em>part_long_pos_nk225_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartLongPosNk2252IsModified();


  /** 
   * <em>short_pos_nk225_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getShortPosNk2250();


  /** 
   * <em>short_pos_nk225_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortPosNk2250IsSet();


  /** 
   * <em>short_pos_nk225_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortPosNk2250IsModified();


  /** 
   * <em>short_pos_nk225_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getShortPosNk2251();


  /** 
   * <em>short_pos_nk225_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortPosNk2251IsSet();


  /** 
   * <em>short_pos_nk225_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortPosNk2251IsModified();


  /** 
   * <em>short_pos_nk225_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getShortPosNk2252();


  /** 
   * <em>short_pos_nk225_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortPosNk2252IsSet();


  /** 
   * <em>short_pos_nk225_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortPosNk2252IsModified();


  /** 
   * <em>part_short_pos_nk225_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPartShortPosNk2250();


  /** 
   * <em>part_short_pos_nk225_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartShortPosNk2250IsSet();


  /** 
   * <em>part_short_pos_nk225_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartShortPosNk2250IsModified();


  /** 
   * <em>part_short_pos_nk225_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPartShortPosNk2251();


  /** 
   * <em>part_short_pos_nk225_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartShortPosNk2251IsSet();


  /** 
   * <em>part_short_pos_nk225_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartShortPosNk2251IsModified();


  /** 
   * <em>part_short_pos_nk225_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPartShortPosNk2252();


  /** 
   * <em>part_short_pos_nk225_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartShortPosNk2252IsSet();


  /** 
   * <em>part_short_pos_nk225_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartShortPosNk2252IsModified();


  /** 
   * <em>bull_quantity_nk225_mini_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBullQuantityNk225Mini0();


  /** 
   * <em>bull_quantity_nk225_mini_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBullQuantityNk225Mini0IsSet();


  /** 
   * <em>bull_quantity_nk225_mini_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBullQuantityNk225Mini0IsModified();


  /** 
   * <em>bull_quantity_nk225_mini_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBullQuantityNk225Mini1();


  /** 
   * <em>bull_quantity_nk225_mini_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBullQuantityNk225Mini1IsSet();


  /** 
   * <em>bull_quantity_nk225_mini_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBullQuantityNk225Mini1IsModified();


  /** 
   * <em>bull_quantity_nk225_mini_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBullQuantityNk225Mini2();


  /** 
   * <em>bull_quantity_nk225_mini_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBullQuantityNk225Mini2IsSet();


  /** 
   * <em>bull_quantity_nk225_mini_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBullQuantityNk225Mini2IsModified();


  /** 
   * <em>bear_quantity_nk225_mini_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBearQuantityNk225Mini0();


  /** 
   * <em>bear_quantity_nk225_mini_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBearQuantityNk225Mini0IsSet();


  /** 
   * <em>bear_quantity_nk225_mini_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBearQuantityNk225Mini0IsModified();


  /** 
   * <em>bear_quantity_nk225_mini_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBearQuantityNk225Mini1();


  /** 
   * <em>bear_quantity_nk225_mini_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBearQuantityNk225Mini1IsSet();


  /** 
   * <em>bear_quantity_nk225_mini_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBearQuantityNk225Mini1IsModified();


  /** 
   * <em>bear_quantity_nk225_mini_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBearQuantityNk225Mini2();


  /** 
   * <em>bear_quantity_nk225_mini_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBearQuantityNk225Mini2IsSet();


  /** 
   * <em>bear_quantity_nk225_mini_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBearQuantityNk225Mini2IsModified();


  /** 
   * <em>long_pos_nk225_mini_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLongPosNk225Mini0();


  /** 
   * <em>long_pos_nk225_mini_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongPosNk225Mini0IsSet();


  /** 
   * <em>long_pos_nk225_mini_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongPosNk225Mini0IsModified();


  /** 
   * <em>long_pos_nk225_mini_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLongPosNk225Mini1();


  /** 
   * <em>long_pos_nk225_mini_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongPosNk225Mini1IsSet();


  /** 
   * <em>long_pos_nk225_mini_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongPosNk225Mini1IsModified();


  /** 
   * <em>long_pos_nk225_mini_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLongPosNk225Mini2();


  /** 
   * <em>long_pos_nk225_mini_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongPosNk225Mini2IsSet();


  /** 
   * <em>long_pos_nk225_mini_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongPosNk225Mini2IsModified();


  /** 
   * <em>part_long_pos_nk225_mini_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPartLongPosNk225Mini0();


  /** 
   * <em>part_long_pos_nk225_mini_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartLongPosNk225Mini0IsSet();


  /** 
   * <em>part_long_pos_nk225_mini_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartLongPosNk225Mini0IsModified();


  /** 
   * <em>part_long_pos_nk225_mini_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPartLongPosNk225Mini1();


  /** 
   * <em>part_long_pos_nk225_mini_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartLongPosNk225Mini1IsSet();


  /** 
   * <em>part_long_pos_nk225_mini_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartLongPosNk225Mini1IsModified();


  /** 
   * <em>part_long_pos_nk225_mini_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPartLongPosNk225Mini2();


  /** 
   * <em>part_long_pos_nk225_mini_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartLongPosNk225Mini2IsSet();


  /** 
   * <em>part_long_pos_nk225_mini_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartLongPosNk225Mini2IsModified();


  /** 
   * <em>short_pos_nk225_mini_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getShortPosNk225Mini0();


  /** 
   * <em>short_pos_nk225_mini_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortPosNk225Mini0IsSet();


  /** 
   * <em>short_pos_nk225_mini_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortPosNk225Mini0IsModified();


  /** 
   * <em>short_pos_nk225_mini_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getShortPosNk225Mini1();


  /** 
   * <em>short_pos_nk225_mini_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortPosNk225Mini1IsSet();


  /** 
   * <em>short_pos_nk225_mini_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortPosNk225Mini1IsModified();


  /** 
   * <em>short_pos_nk225_mini_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getShortPosNk225Mini2();


  /** 
   * <em>short_pos_nk225_mini_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortPosNk225Mini2IsSet();


  /** 
   * <em>short_pos_nk225_mini_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortPosNk225Mini2IsModified();


  /** 
   * <em>part_short_pos_nk225_mini_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPartShortPosNk225Mini0();


  /** 
   * <em>part_short_pos_nk225_mini_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartShortPosNk225Mini0IsSet();


  /** 
   * <em>part_short_pos_nk225_mini_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartShortPosNk225Mini0IsModified();


  /** 
   * <em>part_short_pos_nk225_mini_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPartShortPosNk225Mini1();


  /** 
   * <em>part_short_pos_nk225_mini_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartShortPosNk225Mini1IsSet();


  /** 
   * <em>part_short_pos_nk225_mini_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartShortPosNk225Mini1IsModified();


  /** 
   * <em>part_short_pos_nk225_mini_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPartShortPosNk225Mini2();


  /** 
   * <em>part_short_pos_nk225_mini_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartShortPosNk225Mini2IsSet();


  /** 
   * <em>part_short_pos_nk225_mini_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPartShortPosNk225Mini2IsModified();


  /** 
   * <em>buy_contract_amt_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuyContractAmt0();


  /** 
   * <em>buy_contract_amt_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyContractAmt0IsSet();


  /** 
   * <em>buy_contract_amt_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyContractAmt0IsModified();


  /** 
   * <em>buy_contract_amt_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuyContractAmt1();


  /** 
   * <em>buy_contract_amt_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyContractAmt1IsSet();


  /** 
   * <em>buy_contract_amt_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyContractAmt1IsModified();


  /** 
   * <em>buy_contract_amt_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuyContractAmt2();


  /** 
   * <em>buy_contract_amt_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyContractAmt2IsSet();


  /** 
   * <em>buy_contract_amt_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyContractAmt2IsModified();


  /** 
   * <em>buy_contract_amt_f0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuyContractAmtF0();


  /** 
   * <em>buy_contract_amt_f0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyContractAmtF0IsSet();


  /** 
   * <em>buy_contract_amt_f0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyContractAmtF0IsModified();


  /** 
   * <em>buy_contract_amt_f1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuyContractAmtF1();


  /** 
   * <em>buy_contract_amt_f1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyContractAmtF1IsSet();


  /** 
   * <em>buy_contract_amt_f1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyContractAmtF1IsModified();


  /** 
   * <em>buy_contract_amt_f2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuyContractAmtF2();


  /** 
   * <em>buy_contract_amt_f2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyContractAmtF2IsSet();


  /** 
   * <em>buy_contract_amt_f2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyContractAmtF2IsModified();


  /** 
   * <em>sell_contract_amt_0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellContractAmt0();


  /** 
   * <em>sell_contract_amt_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellContractAmt0IsSet();


  /** 
   * <em>sell_contract_amt_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellContractAmt0IsModified();


  /** 
   * <em>sell_contract_amt_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellContractAmt1();


  /** 
   * <em>sell_contract_amt_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellContractAmt1IsSet();


  /** 
   * <em>sell_contract_amt_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellContractAmt1IsModified();


  /** 
   * <em>sell_contract_amt_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellContractAmt2();


  /** 
   * <em>sell_contract_amt_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellContractAmt2IsSet();


  /** 
   * <em>sell_contract_amt_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellContractAmt2IsModified();


  /** 
   * <em>sell_contract_amt_f0</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellContractAmtF0();


  /** 
   * <em>sell_contract_amt_f0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellContractAmtF0IsSet();


  /** 
   * <em>sell_contract_amt_f0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellContractAmtF0IsModified();


  /** 
   * <em>sell_contract_amt_f1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellContractAmtF1();


  /** 
   * <em>sell_contract_amt_f1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellContractAmtF1IsSet();


  /** 
   * <em>sell_contract_amt_f1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellContractAmtF1IsModified();


  /** 
   * <em>sell_contract_amt_f2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellContractAmtF2();


  /** 
   * <em>sell_contract_amt_f2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellContractAmtF2IsSet();


  /** 
   * <em>sell_contract_amt_f2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellContractAmtF2IsModified();


  /** 
   * <em>non_pay_amt</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNonPayAmt();


  /** 
   * <em>non_pay_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNonPayAmtIsSet();


  /** 
   * <em>non_pay_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNonPayAmtIsModified();


  /** 
   * <em>today_claim_amt</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTodayClaimAmt();


  /** 
   * <em>today_claim_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayClaimAmtIsSet();


  /** 
   * <em>today_claim_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayClaimAmtIsModified();


  /** 
   * <em>tomorrow_claim_amt</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTomorrowClaimAmt();


  /** 
   * <em>tomorrow_claim_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTomorrowClaimAmtIsSet();


  /** 
   * <em>tomorrow_claim_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTomorrowClaimAmtIsModified();


  /** 
   * <em>delivery_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getDeliveryDate();


  /** 
   * <em>delivery_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDeliveryDateIsSet();


  /** 
   * <em>delivery_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDeliveryDateIsModified();


  /** 
   * <em>ifo_deposit_insufficient_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositInsufficientFlag();


  /** 
   * <em>ifo_deposit_insufficient_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositInsufficientFlagIsSet();


  /** 
   * <em>ifo_deposit_insufficient_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositInsufficientFlagIsModified();


  /** 
   * <em>ifo_deposit_index_nk225</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositIndexNk225();


  /** 
   * <em>ifo_deposit_index_nk225</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositIndexNk225IsSet();


  /** 
   * <em>ifo_deposit_index_nk225</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositIndexNk225IsModified();


  /** 
   * <em>ifo_deposit_index_nk225_mini</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoDepositIndexNk225Mini();


  /** 
   * <em>ifo_deposit_index_nk225_mini</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositIndexNk225MiniIsSet();


  /** 
   * <em>ifo_deposit_index_nk225_mini</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoDepositIndexNk225MiniIsModified();


  /** 
   * <em>after_tomorrow_claim_amt</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAfterTomorrowClaimAmt();


  /** 
   * <em>after_tomorrow_claim_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAfterTomorrowClaimAmtIsSet();


  /** 
   * <em>after_tomorrow_claim_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAfterTomorrowClaimAmtIsModified();


  /** 
   * <em>tomorrow_claim_amt_evening</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTomorrowClaimAmtEvening();


  /** 
   * <em>tomorrow_claim_amt_evening</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTomorrowClaimAmtEveningIsSet();


  /** 
   * <em>tomorrow_claim_amt_evening</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTomorrowClaimAmtEveningIsModified();


  /** 
   * <em>status</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getStatus();


  /** 
   * <em>status</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getStatusIsNull();


  /** 
   * <em>status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatusIsSet();


  /** 
   * <em>status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatusIsModified();


  /** 
   * <em>error_message</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getErrorMessage();


  /** 
   * <em>error_message</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getErrorMessageIsSet();


  /** 
   * <em>error_message</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getErrorMessageIsModified();


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
