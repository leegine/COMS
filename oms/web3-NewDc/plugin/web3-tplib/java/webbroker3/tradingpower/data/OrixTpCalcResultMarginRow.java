head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	OrixTpCalcResultMarginRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * OrixTpCalcResultMarginRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>orix_tp_calc_result_margin</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link OrixTpCalcResultMarginRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrixTpCalcResultMarginPK 
 */
public interface OrixTpCalcResultMarginRow extends Row {


  /** 
   * ����{@@link OrixTpCalcResultMarginRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "orix_tp_calc_result_margin", "account" );


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
   * <em>work_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getWorkDate();


  /** 
   * <em>work_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWorkDateIsSet();


  /** 
   * <em>work_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWorkDateIsModified();


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


  /** 
   * <em>asset_evaluation_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAssetEvaluationDiv();


  /** 
   * <em>asset_evaluation_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAssetEvaluationDivIsSet();


  /** 
   * <em>asset_evaluation_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAssetEvaluationDivIsModified();


  /** 
   * <em>account_balance_0</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAccountBalance0();


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
   * @@return long�̒l 
   */
  public long getAccountBalance1();


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
   * @@return long�̒l 
   */
  public long getAccountBalance2();


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
   * @@return long�̒l 
   */
  public long getAccountBalance3();


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
   * @@return long�̒l 
   */
  public long getAccountBalance4();


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
   * <em>today_unexecuted_amount_1</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getTodayUnexecutedAmount1();


  /** 
   * <em>today_unexecuted_amount_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayUnexecutedAmount1IsNull();


  /** 
   * <em>today_unexecuted_amount_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayUnexecutedAmount1IsSet();


  /** 
   * <em>today_unexecuted_amount_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayUnexecutedAmount1IsModified();


  /** 
   * <em>today_unexecuted_amount_2</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getTodayUnexecutedAmount2();


  /** 
   * <em>today_unexecuted_amount_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayUnexecutedAmount2IsNull();


  /** 
   * <em>today_unexecuted_amount_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayUnexecutedAmount2IsSet();


  /** 
   * <em>today_unexecuted_amount_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayUnexecutedAmount2IsModified();


  /** 
   * <em>today_unexecuted_amount_3</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getTodayUnexecutedAmount3();


  /** 
   * <em>today_unexecuted_amount_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayUnexecutedAmount3IsNull();


  /** 
   * <em>today_unexecuted_amount_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayUnexecutedAmount3IsSet();


  /** 
   * <em>today_unexecuted_amount_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayUnexecutedAmount3IsModified();


  /** 
   * <em>today_unexecuted_amount_4</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getTodayUnexecutedAmount4();


  /** 
   * <em>today_unexecuted_amount_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayUnexecutedAmount4IsNull();


  /** 
   * <em>today_unexecuted_amount_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayUnexecutedAmount4IsSet();


  /** 
   * <em>today_unexecuted_amount_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayUnexecutedAmount4IsModified();


  /** 
   * <em>day_trade_restraint_0</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getDayTradeRestraint0();


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
   * @@return long�̒l 
   */
  public long getDayTradeRestraint1();


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
   * @@return long�̒l 
   */
  public long getDayTradeRestraint2();


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
   * @@return long�̒l 
   */
  public long getDayTradeRestraint3();


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
   * @@return long�̒l 
   */
  public long getDayTradeRestraint4();


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
   * @@return long�̒l 
   */
  public long getOtherRestraint0();


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
   * @@return long�̒l 
   */
  public long getOtherRestraint1();


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
   * @@return long�̒l 
   */
  public long getOtherRestraint2();


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
   * @@return long�̒l 
   */
  public long getOtherRestraint3();


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
   * @@return long�̒l 
   */
  public long getOtherRestraint4();


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
   * <em>margin_account_balance_0</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginAccountBalance0();


  /** 
   * <em>margin_account_balance_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginAccountBalance0IsNull();


  /** 
   * <em>margin_account_balance_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccountBalance0IsSet();


  /** 
   * <em>margin_account_balance_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccountBalance0IsModified();


  /** 
   * <em>margin_account_balance_1</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginAccountBalance1();


  /** 
   * <em>margin_account_balance_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginAccountBalance1IsNull();


  /** 
   * <em>margin_account_balance_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccountBalance1IsSet();


  /** 
   * <em>margin_account_balance_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccountBalance1IsModified();


  /** 
   * <em>margin_account_balance_2</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginAccountBalance2();


  /** 
   * <em>margin_account_balance_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginAccountBalance2IsNull();


  /** 
   * <em>margin_account_balance_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccountBalance2IsSet();


  /** 
   * <em>margin_account_balance_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccountBalance2IsModified();


  /** 
   * <em>margin_account_balance_3</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginAccountBalance3();


  /** 
   * <em>margin_account_balance_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginAccountBalance3IsNull();


  /** 
   * <em>margin_account_balance_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccountBalance3IsSet();


  /** 
   * <em>margin_account_balance_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccountBalance3IsModified();


  /** 
   * <em>margin_account_balance_4</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginAccountBalance4();


  /** 
   * <em>margin_account_balance_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginAccountBalance4IsNull();


  /** 
   * <em>margin_account_balance_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccountBalance4IsSet();


  /** 
   * <em>margin_account_balance_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccountBalance4IsModified();


  /** 
   * <em>substitute_security_asset_0</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSubstituteSecurityAsset0();


  /** 
   * <em>substitute_security_asset_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSubstituteSecurityAsset0IsNull();


  /** 
   * <em>substitute_security_asset_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubstituteSecurityAsset0IsSet();


  /** 
   * <em>substitute_security_asset_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubstituteSecurityAsset0IsModified();


  /** 
   * <em>substitute_security_asset_1</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSubstituteSecurityAsset1();


  /** 
   * <em>substitute_security_asset_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSubstituteSecurityAsset1IsNull();


  /** 
   * <em>substitute_security_asset_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubstituteSecurityAsset1IsSet();


  /** 
   * <em>substitute_security_asset_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubstituteSecurityAsset1IsModified();


  /** 
   * <em>substitute_security_asset_2</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSubstituteSecurityAsset2();


  /** 
   * <em>substitute_security_asset_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSubstituteSecurityAsset2IsNull();


  /** 
   * <em>substitute_security_asset_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubstituteSecurityAsset2IsSet();


  /** 
   * <em>substitute_security_asset_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubstituteSecurityAsset2IsModified();


  /** 
   * <em>substitute_security_asset_3</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSubstituteSecurityAsset3();


  /** 
   * <em>substitute_security_asset_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSubstituteSecurityAsset3IsNull();


  /** 
   * <em>substitute_security_asset_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubstituteSecurityAsset3IsSet();


  /** 
   * <em>substitute_security_asset_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubstituteSecurityAsset3IsModified();


  /** 
   * <em>substitute_security_asset_4</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSubstituteSecurityAsset4();


  /** 
   * <em>substitute_security_asset_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSubstituteSecurityAsset4IsNull();


  /** 
   * <em>substitute_security_asset_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubstituteSecurityAsset4IsSet();


  /** 
   * <em>substitute_security_asset_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubstituteSecurityAsset4IsModified();


  /** 
   * <em>contract_asset_profit_loss</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getContractAssetProfitLoss();


  /** 
   * <em>contract_asset_profit_loss</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAssetProfitLossIsNull();


  /** 
   * <em>contract_asset_profit_loss</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfitLossIsSet();


  /** 
   * <em>contract_asset_profit_loss</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfitLossIsModified();


  /** 
   * <em>contract_total_cost</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getContractTotalCost();


  /** 
   * <em>contract_total_cost</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractTotalCostIsNull();


  /** 
   * <em>contract_total_cost</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractTotalCostIsSet();


  /** 
   * <em>contract_total_cost</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractTotalCostIsModified();


  /** 
   * <em>undeli_contract_loss_0</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getUndeliContractLoss0();


  /** 
   * <em>undeli_contract_loss_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUndeliContractLoss0IsNull();


  /** 
   * <em>undeli_contract_loss_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractLoss0IsSet();


  /** 
   * <em>undeli_contract_loss_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractLoss0IsModified();


  /** 
   * <em>undeli_contract_loss_1</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getUndeliContractLoss1();


  /** 
   * <em>undeli_contract_loss_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUndeliContractLoss1IsNull();


  /** 
   * <em>undeli_contract_loss_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractLoss1IsSet();


  /** 
   * <em>undeli_contract_loss_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractLoss1IsModified();


  /** 
   * <em>undeli_contract_loss_2</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getUndeliContractLoss2();


  /** 
   * <em>undeli_contract_loss_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUndeliContractLoss2IsNull();


  /** 
   * <em>undeli_contract_loss_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractLoss2IsSet();


  /** 
   * <em>undeli_contract_loss_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractLoss2IsModified();


  /** 
   * <em>undeli_contract_loss_3</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getUndeliContractLoss3();


  /** 
   * <em>undeli_contract_loss_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUndeliContractLoss3IsNull();


  /** 
   * <em>undeli_contract_loss_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractLoss3IsSet();


  /** 
   * <em>undeli_contract_loss_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractLoss3IsModified();


  /** 
   * <em>today_repay_contract_pre_asset</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getTodayRepayContractPreAsset();


  /** 
   * <em>today_repay_contract_pre_asset</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayRepayContractPreAssetIsNull();


  /** 
   * <em>today_repay_contract_pre_asset</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayRepayContractPreAssetIsSet();


  /** 
   * <em>today_repay_contract_pre_asset</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayRepayContractPreAssetIsModified();


  /** 
   * <em>contract_amount_day_repay_0</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getContractAmountDayRepay0();


  /** 
   * <em>contract_amount_day_repay_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAmountDayRepay0IsNull();


  /** 
   * <em>contract_amount_day_repay_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmountDayRepay0IsSet();


  /** 
   * <em>contract_amount_day_repay_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmountDayRepay0IsModified();


  /** 
   * <em>contract_amount_day_repay_1</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getContractAmountDayRepay1();


  /** 
   * <em>contract_amount_day_repay_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAmountDayRepay1IsNull();


  /** 
   * <em>contract_amount_day_repay_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmountDayRepay1IsSet();


  /** 
   * <em>contract_amount_day_repay_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmountDayRepay1IsModified();


  /** 
   * <em>contract_amount_day_repay_2</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getContractAmountDayRepay2();


  /** 
   * <em>contract_amount_day_repay_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAmountDayRepay2IsNull();


  /** 
   * <em>contract_amount_day_repay_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmountDayRepay2IsSet();


  /** 
   * <em>contract_amount_day_repay_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmountDayRepay2IsModified();


  /** 
   * <em>contract_amount_day_repay_3</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getContractAmountDayRepay3();


  /** 
   * <em>contract_amount_day_repay_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAmountDayRepay3IsNull();


  /** 
   * <em>contract_amount_day_repay_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmountDayRepay3IsSet();


  /** 
   * <em>contract_amount_day_repay_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmountDayRepay3IsModified();


  /** 
   * <em>contract_amount_day_repay_4</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getContractAmountDayRepay4();


  /** 
   * <em>contract_amount_day_repay_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAmountDayRepay4IsNull();


  /** 
   * <em>contract_amount_day_repay_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmountDayRepay4IsSet();


  /** 
   * <em>contract_amount_day_repay_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAmountDayRepay4IsModified();


  /** 
   * <em>margin_power_0</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginPower0();


  /** 
   * <em>margin_power_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginPower0IsNull();


  /** 
   * <em>margin_power_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginPower0IsSet();


  /** 
   * <em>margin_power_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginPower0IsModified();


  /** 
   * <em>margin_power_1</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginPower1();


  /** 
   * <em>margin_power_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginPower1IsNull();


  /** 
   * <em>margin_power_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginPower1IsSet();


  /** 
   * <em>margin_power_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginPower1IsModified();


  /** 
   * <em>margin_power_2</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginPower2();


  /** 
   * <em>margin_power_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginPower2IsNull();


  /** 
   * <em>margin_power_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginPower2IsSet();


  /** 
   * <em>margin_power_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginPower2IsModified();


  /** 
   * <em>margin_power_3</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginPower3();


  /** 
   * <em>margin_power_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginPower3IsNull();


  /** 
   * <em>margin_power_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginPower3IsSet();


  /** 
   * <em>margin_power_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginPower3IsModified();


  /** 
   * <em>margin_power_4</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginPower4();


  /** 
   * <em>margin_power_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginPower4IsNull();


  /** 
   * <em>margin_power_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginPower4IsSet();


  /** 
   * <em>margin_power_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginPower4IsModified();


  /** 
   * <em>margin_trading_power_1</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginTradingPower1();


  /** 
   * <em>margin_trading_power_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginTradingPower1IsNull();


  /** 
   * <em>margin_trading_power_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTradingPower1IsSet();


  /** 
   * <em>margin_trading_power_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTradingPower1IsModified();


  /** 
   * <em>margin_trading_power_2</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginTradingPower2();


  /** 
   * <em>margin_trading_power_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginTradingPower2IsNull();


  /** 
   * <em>margin_trading_power_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTradingPower2IsSet();


  /** 
   * <em>margin_trading_power_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTradingPower2IsModified();


  /** 
   * <em>margin_trading_power_3</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginTradingPower3();


  /** 
   * <em>margin_trading_power_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginTradingPower3IsNull();


  /** 
   * <em>margin_trading_power_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTradingPower3IsSet();


  /** 
   * <em>margin_trading_power_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTradingPower3IsModified();


  /** 
   * <em>margin_trading_power_4</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginTradingPower4();


  /** 
   * <em>margin_trading_power_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginTradingPower4IsNull();


  /** 
   * <em>margin_trading_power_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTradingPower4IsSet();


  /** 
   * <em>margin_trading_power_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTradingPower4IsModified();


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
   * <em>act_rec_trading_power_3</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getActRecTradingPower3();


  /** 
   * <em>act_rec_trading_power_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getActRecTradingPower3IsNull();


  /** 
   * <em>act_rec_trading_power_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getActRecTradingPower3IsSet();


  /** 
   * <em>act_rec_trading_power_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getActRecTradingPower3IsModified();


  /** 
   * <em>act_rec_trading_power_4</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getActRecTradingPower4();


  /** 
   * <em>act_rec_trading_power_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getActRecTradingPower4IsNull();


  /** 
   * <em>act_rec_trading_power_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getActRecTradingPower4IsSet();


  /** 
   * <em>act_rec_trading_power_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getActRecTradingPower4IsModified();


  /** 
   * <em>act_rec_trading_power_4_dash</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getActRecTradingPower4Dash();


  /** 
   * <em>act_rec_trading_power_4_dash</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getActRecTradingPower4DashIsNull();


  /** 
   * <em>act_rec_trading_power_4_dash</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getActRecTradingPower4DashIsSet();


  /** 
   * <em>act_rec_trading_power_4_dash</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getActRecTradingPower4DashIsModified();


  /** 
   * <em>equity_trading_power_3</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getEquityTradingPower3();


  /** 
   * <em>equity_trading_power_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getEquityTradingPower3IsNull();


  /** 
   * <em>equity_trading_power_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityTradingPower3IsSet();


  /** 
   * <em>equity_trading_power_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityTradingPower3IsModified();


  /** 
   * <em>equity_trading_power_4</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getEquityTradingPower4();


  /** 
   * <em>equity_trading_power_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getEquityTradingPower4IsNull();


  /** 
   * <em>equity_trading_power_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityTradingPower4IsSet();


  /** 
   * <em>equity_trading_power_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityTradingPower4IsModified();


  /** 
   * <em>equity_trading_power_4_dash</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getEquityTradingPower4Dash();


  /** 
   * <em>equity_trading_power_4_dash</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getEquityTradingPower4DashIsNull();


  /** 
   * <em>equity_trading_power_4_dash</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityTradingPower4DashIsSet();


  /** 
   * <em>equity_trading_power_4_dash</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityTradingPower4DashIsModified();


  /** 
   * <em>undeli_contract_amount_0</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getUndeliContractAmount0();


  /** 
   * <em>undeli_contract_amount_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUndeliContractAmount0IsNull();


  /** 
   * <em>undeli_contract_amount_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractAmount0IsSet();


  /** 
   * <em>undeli_contract_amount_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractAmount0IsModified();


  /** 
   * <em>undeli_contract_amount_1</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getUndeliContractAmount1();


  /** 
   * <em>undeli_contract_amount_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUndeliContractAmount1IsNull();


  /** 
   * <em>undeli_contract_amount_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractAmount1IsSet();


  /** 
   * <em>undeli_contract_amount_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractAmount1IsModified();


  /** 
   * <em>undeli_contract_amount_2</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getUndeliContractAmount2();


  /** 
   * <em>undeli_contract_amount_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUndeliContractAmount2IsNull();


  /** 
   * <em>undeli_contract_amount_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractAmount2IsSet();


  /** 
   * <em>undeli_contract_amount_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractAmount2IsModified();


  /** 
   * <em>undeli_contract_amount_3</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getUndeliContractAmount3();


  /** 
   * <em>undeli_contract_amount_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUndeliContractAmount3IsNull();


  /** 
   * <em>undeli_contract_amount_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractAmount3IsSet();


  /** 
   * <em>undeli_contract_amount_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUndeliContractAmount3IsModified();


  /** 
   * <em>margin_draw_power_0</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginDrawPower0();


  /** 
   * <em>margin_draw_power_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDrawPower0IsNull();


  /** 
   * <em>margin_draw_power_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDrawPower0IsSet();


  /** 
   * <em>margin_draw_power_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDrawPower0IsModified();


  /** 
   * <em>margin_draw_power_1</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginDrawPower1();


  /** 
   * <em>margin_draw_power_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDrawPower1IsNull();


  /** 
   * <em>margin_draw_power_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDrawPower1IsSet();


  /** 
   * <em>margin_draw_power_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDrawPower1IsModified();


  /** 
   * <em>margin_draw_power_2</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginDrawPower2();


  /** 
   * <em>margin_draw_power_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDrawPower2IsNull();


  /** 
   * <em>margin_draw_power_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDrawPower2IsSet();


  /** 
   * <em>margin_draw_power_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDrawPower2IsModified();


  /** 
   * <em>margin_draw_power_3</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginDrawPower3();


  /** 
   * <em>margin_draw_power_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDrawPower3IsNull();


  /** 
   * <em>margin_draw_power_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDrawPower3IsSet();


  /** 
   * <em>margin_draw_power_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDrawPower3IsModified();


  /** 
   * <em>margin_draw_power_4</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarginDrawPower4();


  /** 
   * <em>margin_draw_power_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDrawPower4IsNull();


  /** 
   * <em>margin_draw_power_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDrawPower4IsSet();


  /** 
   * <em>margin_draw_power_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDrawPower4IsModified();


  /** 
   * <em>other_trading_power_1</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getOtherTradingPower1();


  /** 
   * <em>other_trading_power_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOtherTradingPower1IsNull();


  /** 
   * <em>other_trading_power_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherTradingPower1IsSet();


  /** 
   * <em>other_trading_power_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherTradingPower1IsModified();


  /** 
   * <em>other_trading_power_2</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getOtherTradingPower2();


  /** 
   * <em>other_trading_power_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOtherTradingPower2IsNull();


  /** 
   * <em>other_trading_power_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherTradingPower2IsSet();


  /** 
   * <em>other_trading_power_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherTradingPower2IsModified();


  /** 
   * <em>other_trading_power_3</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getOtherTradingPower3();


  /** 
   * <em>other_trading_power_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOtherTradingPower3IsNull();


  /** 
   * <em>other_trading_power_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherTradingPower3IsSet();


  /** 
   * <em>other_trading_power_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherTradingPower3IsModified();


  /** 
   * <em>other_trading_power_4</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getOtherTradingPower4();


  /** 
   * <em>other_trading_power_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOtherTradingPower4IsNull();


  /** 
   * <em>other_trading_power_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherTradingPower4IsSet();


  /** 
   * <em>other_trading_power_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherTradingPower4IsModified();


  /** 
   * <em>demandamount0</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getDemandamount0();


  /** 
   * <em>demandamount0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDemandamount0IsNull();


  /** 
   * <em>demandamount0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDemandamount0IsSet();


  /** 
   * <em>demandamount0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDemandamount0IsModified();


  /** 
   * <em>demandamount1</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getDemandamount1();


  /** 
   * <em>demandamount1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDemandamount1IsNull();


  /** 
   * <em>demandamount1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDemandamount1IsSet();


  /** 
   * <em>demandamount1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDemandamount1IsModified();


  /** 
   * <em>demandamount2</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getDemandamount2();


  /** 
   * <em>demandamount2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDemandamount2IsNull();


  /** 
   * <em>demandamount2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDemandamount2IsSet();


  /** 
   * <em>demandamount2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDemandamount2IsModified();


  /** 
   * <em>demandamount3</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getDemandamount3();


  /** 
   * <em>demandamount3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDemandamount3IsNull();


  /** 
   * <em>demandamount3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDemandamount3IsSet();


  /** 
   * <em>demandamount3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDemandamount3IsModified();


  /** 
   * <em>demandamount4</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getDemandamount4();


  /** 
   * <em>demandamount4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDemandamount4IsNull();


  /** 
   * <em>demandamount4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDemandamount4IsSet();


  /** 
   * <em>demandamount4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDemandamount4IsModified();


  /** 
   * <em>payment_amount_designate0</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getPaymentAmountDesignate0();


  /** 
   * <em>payment_amount_designate0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentAmountDesignate0IsNull();


  /** 
   * <em>payment_amount_designate0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate0IsSet();


  /** 
   * <em>payment_amount_designate0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate0IsModified();


  /** 
   * <em>payment_amount_designate1</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getPaymentAmountDesignate1();


  /** 
   * <em>payment_amount_designate1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentAmountDesignate1IsNull();


  /** 
   * <em>payment_amount_designate1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate1IsSet();


  /** 
   * <em>payment_amount_designate1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate1IsModified();


  /** 
   * <em>payment_amount_designate2</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getPaymentAmountDesignate2();


  /** 
   * <em>payment_amount_designate2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentAmountDesignate2IsNull();


  /** 
   * <em>payment_amount_designate2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate2IsSet();


  /** 
   * <em>payment_amount_designate2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate2IsModified();


  /** 
   * <em>margin_sec_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarginSecProductCode();


  /** 
   * <em>margin_sec_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSecProductCodeIsSet();


  /** 
   * <em>margin_sec_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSecProductCodeIsModified();


  /** 
   * <em>margin_sec_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginSecRate();


  /** 
   * <em>margin_sec_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginSecRateIsNull();


  /** 
   * <em>margin_sec_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSecRateIsSet();


  /** 
   * <em>margin_sec_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSecRateIsModified();


  /** 
   * <em>equity_asset_executed</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getEquityAssetExecuted();


  /** 
   * <em>equity_asset_executed</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getEquityAssetExecutedIsNull();


  /** 
   * <em>equity_asset_executed</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityAssetExecutedIsSet();


  /** 
   * <em>equity_asset_executed</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityAssetExecutedIsModified();


  /** 
   * <em>ruito_asset_executed</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getRuitoAssetExecuted();


  /** 
   * <em>ruito_asset_executed</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRuitoAssetExecutedIsNull();


  /** 
   * <em>ruito_asset_executed</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRuitoAssetExecutedIsSet();


  /** 
   * <em>ruito_asset_executed</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRuitoAssetExecutedIsModified();


  /** 
   * <em>mutual_fund_asset_executed</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMutualFundAssetExecuted();


  /** 
   * <em>mutual_fund_asset_executed</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMutualFundAssetExecutedIsNull();


  /** 
   * <em>mutual_fund_asset_executed</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMutualFundAssetExecutedIsSet();


  /** 
   * <em>mutual_fund_asset_executed</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMutualFundAssetExecutedIsModified();


  /** 
   * <em>bond_asset_executed</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getBondAssetExecuted();


  /** 
   * <em>bond_asset_executed</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getBondAssetExecutedIsNull();


  /** 
   * <em>bond_asset_executed</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondAssetExecutedIsSet();


  /** 
   * <em>bond_asset_executed</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondAssetExecutedIsModified();


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
