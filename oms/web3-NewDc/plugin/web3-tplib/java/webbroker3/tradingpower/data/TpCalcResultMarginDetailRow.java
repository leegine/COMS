head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.28.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultMarginDetailRow.java;


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
 * TpCalcResultMarginDetailRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>tp_calc_result_margin_detail</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link TpCalcResultMarginDetailRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCalcResultMarginDetailPK 
 */
public interface TpCalcResultMarginDetailRow extends Row {


  /** 
   * ����{@@link TpCalcResultMarginDetailRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "tp_calc_result_margin_detail", "account" );


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
   * <em>equity_asset_delivered</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getEquityAssetDelivered();


  /** 
   * <em>equity_asset_delivered</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getEquityAssetDeliveredIsNull();


  /** 
   * <em>equity_asset_delivered</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityAssetDeliveredIsSet();


  /** 
   * <em>equity_asset_delivered</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityAssetDeliveredIsModified();


  /** 
   * <em>equity_asset_executed</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getEquityAssetExecuted();


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
   * <em>mini_stock_asset_delivered</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMiniStockAssetDelivered();


  /** 
   * <em>mini_stock_asset_delivered</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMiniStockAssetDeliveredIsNull();


  /** 
   * <em>mini_stock_asset_delivered</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiniStockAssetDeliveredIsSet();


  /** 
   * <em>mini_stock_asset_delivered</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiniStockAssetDeliveredIsModified();


  /** 
   * <em>mini_stock_asset_executed</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMiniStockAssetExecuted();


  /** 
   * <em>mini_stock_asset_executed</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMiniStockAssetExecutedIsNull();


  /** 
   * <em>mini_stock_asset_executed</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiniStockAssetExecutedIsSet();


  /** 
   * <em>mini_stock_asset_executed</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiniStockAssetExecutedIsModified();


  /** 
   * <em>ruito_asset_delivered</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getRuitoAssetDelivered();


  /** 
   * <em>ruito_asset_delivered</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRuitoAssetDeliveredIsNull();


  /** 
   * <em>ruito_asset_delivered</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRuitoAssetDeliveredIsSet();


  /** 
   * <em>ruito_asset_delivered</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRuitoAssetDeliveredIsModified();


  /** 
   * <em>ruito_asset_executed</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getRuitoAssetExecuted();


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
   * <em>mutual_fund_asset_delivered</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMutualFundAssetDelivered();


  /** 
   * <em>mutual_fund_asset_delivered</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMutualFundAssetDeliveredIsNull();


  /** 
   * <em>mutual_fund_asset_delivered</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMutualFundAssetDeliveredIsSet();


  /** 
   * <em>mutual_fund_asset_delivered</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMutualFundAssetDeliveredIsModified();


  /** 
   * <em>mutual_fund_asset_executed</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMutualFundAssetExecuted();


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
   * <em>bond_asset_delivered</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getBondAssetDelivered();


  /** 
   * <em>bond_asset_delivered</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getBondAssetDeliveredIsNull();


  /** 
   * <em>bond_asset_delivered</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondAssetDeliveredIsSet();


  /** 
   * <em>bond_asset_delivered</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBondAssetDeliveredIsModified();


  /** 
   * <em>bond_asset_executed</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getBondAssetExecuted();


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
   * <em>unexec_substi_security_asset_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecSubstiSecurityAsset0();


  /** 
   * <em>unexec_substi_security_asset_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecSubstiSecurityAsset0IsNull();


  /** 
   * <em>unexec_substi_security_asset_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecSubstiSecurityAsset0IsSet();


  /** 
   * <em>unexec_substi_security_asset_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecSubstiSecurityAsset0IsModified();


  /** 
   * <em>unexec_substi_security_asset_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecSubstiSecurityAsset1();


  /** 
   * <em>unexec_substi_security_asset_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecSubstiSecurityAsset1IsNull();


  /** 
   * <em>unexec_substi_security_asset_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecSubstiSecurityAsset1IsSet();


  /** 
   * <em>unexec_substi_security_asset_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecSubstiSecurityAsset1IsModified();


  /** 
   * <em>unexec_substi_security_asset_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecSubstiSecurityAsset2();


  /** 
   * <em>unexec_substi_security_asset_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecSubstiSecurityAsset2IsNull();


  /** 
   * <em>unexec_substi_security_asset_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecSubstiSecurityAsset2IsSet();


  /** 
   * <em>unexec_substi_security_asset_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecSubstiSecurityAsset2IsModified();


  /** 
   * <em>unexec_substi_security_asset_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecSubstiSecurityAsset3();


  /** 
   * <em>unexec_substi_security_asset_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecSubstiSecurityAsset3IsNull();


  /** 
   * <em>unexec_substi_security_asset_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecSubstiSecurityAsset3IsSet();


  /** 
   * <em>unexec_substi_security_asset_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecSubstiSecurityAsset3IsModified();


  /** 
   * <em>unexec_substi_security_asset_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecSubstiSecurityAsset4();


  /** 
   * <em>unexec_substi_security_asset_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecSubstiSecurityAsset4IsNull();


  /** 
   * <em>unexec_substi_security_asset_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecSubstiSecurityAsset4IsSet();


  /** 
   * <em>unexec_substi_security_asset_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecSubstiSecurityAsset4IsModified();


  /** 
   * <em>unexec_substi_security_asset_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecSubstiSecurityAsset5();


  /** 
   * <em>unexec_substi_security_asset_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecSubstiSecurityAsset5IsNull();


  /** 
   * <em>unexec_substi_security_asset_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecSubstiSecurityAsset5IsSet();


  /** 
   * <em>unexec_substi_security_asset_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecSubstiSecurityAsset5IsModified();


  /** 
   * <em>contract_asset_loss</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAssetLoss();


  /** 
   * <em>contract_asset_loss</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAssetLossIsNull();


  /** 
   * <em>contract_asset_loss</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetLossIsSet();


  /** 
   * <em>contract_asset_loss</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetLossIsModified();


  /** 
   * <em>contract_asset_profit</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAssetProfit();


  /** 
   * <em>contract_asset_profit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAssetProfitIsNull();


  /** 
   * <em>contract_asset_profit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfitIsSet();


  /** 
   * <em>contract_asset_profit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfitIsModified();


  /** 
   * <em>setup_fee</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getSetupFee();


  /** 
   * <em>setup_fee</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSetupFeeIsNull();


  /** 
   * <em>setup_fee</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSetupFeeIsSet();


  /** 
   * <em>setup_fee</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSetupFeeIsModified();


  /** 
   * <em>contract_interest_loss</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractInterestLoss();


  /** 
   * <em>contract_interest_loss</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractInterestLossIsNull();


  /** 
   * <em>contract_interest_loss</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestLossIsSet();


  /** 
   * <em>contract_interest_loss</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestLossIsModified();


  /** 
   * <em>contract_interest_profit</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractInterestProfit();


  /** 
   * <em>contract_interest_profit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractInterestProfitIsNull();


  /** 
   * <em>contract_interest_profit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestProfitIsSet();


  /** 
   * <em>contract_interest_profit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestProfitIsModified();


  /** 
   * <em>contract_other_cost</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractOtherCost();


  /** 
   * <em>contract_other_cost</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractOtherCostIsNull();


  /** 
   * <em>contract_other_cost</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractOtherCostIsSet();


  /** 
   * <em>contract_other_cost</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractOtherCostIsModified();


  /** 
   * <em>unexec_contract_amount_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecContractAmount0();


  /** 
   * <em>unexec_contract_amount_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecContractAmount0IsNull();


  /** 
   * <em>unexec_contract_amount_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecContractAmount0IsSet();


  /** 
   * <em>unexec_contract_amount_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecContractAmount0IsModified();


  /** 
   * <em>unexec_contract_amount_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecContractAmount1();


  /** 
   * <em>unexec_contract_amount_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecContractAmount1IsNull();


  /** 
   * <em>unexec_contract_amount_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecContractAmount1IsSet();


  /** 
   * <em>unexec_contract_amount_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecContractAmount1IsModified();


  /** 
   * <em>unexec_contract_amount_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecContractAmount2();


  /** 
   * <em>unexec_contract_amount_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecContractAmount2IsNull();


  /** 
   * <em>unexec_contract_amount_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecContractAmount2IsSet();


  /** 
   * <em>unexec_contract_amount_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecContractAmount2IsModified();


  /** 
   * <em>unexec_contract_amount_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecContractAmount3();


  /** 
   * <em>unexec_contract_amount_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecContractAmount3IsNull();


  /** 
   * <em>unexec_contract_amount_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecContractAmount3IsSet();


  /** 
   * <em>unexec_contract_amount_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecContractAmount3IsModified();


  /** 
   * <em>unexec_contract_amount_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecContractAmount4();


  /** 
   * <em>unexec_contract_amount_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecContractAmount4IsNull();


  /** 
   * <em>unexec_contract_amount_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecContractAmount4IsSet();


  /** 
   * <em>unexec_contract_amount_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecContractAmount4IsModified();


  /** 
   * <em>unexec_contract_amount_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecContractAmount5();


  /** 
   * <em>unexec_contract_amount_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecContractAmount5IsNull();


  /** 
   * <em>unexec_contract_amount_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecContractAmount5IsSet();


  /** 
   * <em>unexec_contract_amount_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecContractAmount5IsModified();


  /** 
   * <em>unexec_margin_deposit_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecMarginDeposit0();


  /** 
   * <em>unexec_margin_deposit_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecMarginDeposit0IsNull();


  /** 
   * <em>unexec_margin_deposit_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecMarginDeposit0IsSet();


  /** 
   * <em>unexec_margin_deposit_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecMarginDeposit0IsModified();


  /** 
   * <em>unexec_margin_deposit_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecMarginDeposit1();


  /** 
   * <em>unexec_margin_deposit_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecMarginDeposit1IsNull();


  /** 
   * <em>unexec_margin_deposit_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecMarginDeposit1IsSet();


  /** 
   * <em>unexec_margin_deposit_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecMarginDeposit1IsModified();


  /** 
   * <em>unexec_margin_deposit_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecMarginDeposit2();


  /** 
   * <em>unexec_margin_deposit_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecMarginDeposit2IsNull();


  /** 
   * <em>unexec_margin_deposit_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecMarginDeposit2IsSet();


  /** 
   * <em>unexec_margin_deposit_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecMarginDeposit2IsModified();


  /** 
   * <em>unexec_margin_deposit_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecMarginDeposit3();


  /** 
   * <em>unexec_margin_deposit_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecMarginDeposit3IsNull();


  /** 
   * <em>unexec_margin_deposit_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecMarginDeposit3IsSet();


  /** 
   * <em>unexec_margin_deposit_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecMarginDeposit3IsModified();


  /** 
   * <em>unexec_margin_deposit_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecMarginDeposit4();


  /** 
   * <em>unexec_margin_deposit_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecMarginDeposit4IsNull();


  /** 
   * <em>unexec_margin_deposit_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecMarginDeposit4IsSet();


  /** 
   * <em>unexec_margin_deposit_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecMarginDeposit4IsModified();


  /** 
   * <em>unexec_margin_deposit_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecMarginDeposit5();


  /** 
   * <em>unexec_margin_deposit_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecMarginDeposit5IsNull();


  /** 
   * <em>unexec_margin_deposit_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecMarginDeposit5IsSet();


  /** 
   * <em>unexec_margin_deposit_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecMarginDeposit5IsModified();


  /** 
   * <em>unexec_cash_margin_deposit_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecCashMarginDeposit0();


  /** 
   * <em>unexec_cash_margin_deposit_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecCashMarginDeposit0IsNull();


  /** 
   * <em>unexec_cash_margin_deposit_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecCashMarginDeposit0IsSet();


  /** 
   * <em>unexec_cash_margin_deposit_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecCashMarginDeposit0IsModified();


  /** 
   * <em>unexec_cash_margin_deposit_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecCashMarginDeposit1();


  /** 
   * <em>unexec_cash_margin_deposit_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecCashMarginDeposit1IsNull();


  /** 
   * <em>unexec_cash_margin_deposit_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecCashMarginDeposit1IsSet();


  /** 
   * <em>unexec_cash_margin_deposit_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecCashMarginDeposit1IsModified();


  /** 
   * <em>unexec_cash_margin_deposit_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecCashMarginDeposit2();


  /** 
   * <em>unexec_cash_margin_deposit_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecCashMarginDeposit2IsNull();


  /** 
   * <em>unexec_cash_margin_deposit_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecCashMarginDeposit2IsSet();


  /** 
   * <em>unexec_cash_margin_deposit_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecCashMarginDeposit2IsModified();


  /** 
   * <em>unexec_cash_margin_deposit_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecCashMarginDeposit3();


  /** 
   * <em>unexec_cash_margin_deposit_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecCashMarginDeposit3IsNull();


  /** 
   * <em>unexec_cash_margin_deposit_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecCashMarginDeposit3IsSet();


  /** 
   * <em>unexec_cash_margin_deposit_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecCashMarginDeposit3IsModified();


  /** 
   * <em>unexec_cash_margin_deposit_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecCashMarginDeposit4();


  /** 
   * <em>unexec_cash_margin_deposit_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecCashMarginDeposit4IsNull();


  /** 
   * <em>unexec_cash_margin_deposit_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecCashMarginDeposit4IsSet();


  /** 
   * <em>unexec_cash_margin_deposit_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecCashMarginDeposit4IsModified();


  /** 
   * <em>unexec_cash_margin_deposit_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getUnexecCashMarginDeposit5();


  /** 
   * <em>unexec_cash_margin_deposit_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getUnexecCashMarginDeposit5IsNull();


  /** 
   * <em>unexec_cash_margin_deposit_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecCashMarginDeposit5IsSet();


  /** 
   * <em>unexec_cash_margin_deposit_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnexecCashMarginDeposit5IsModified();


  /** 
   * <em>day_repay_margin_deposit_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayRepayMarginDeposit0();


  /** 
   * <em>day_repay_margin_deposit_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayRepayMarginDeposit0IsNull();


  /** 
   * <em>day_repay_margin_deposit_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayMarginDeposit0IsSet();


  /** 
   * <em>day_repay_margin_deposit_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayMarginDeposit0IsModified();


  /** 
   * <em>day_repay_margin_deposit_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayRepayMarginDeposit1();


  /** 
   * <em>day_repay_margin_deposit_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayRepayMarginDeposit1IsNull();


  /** 
   * <em>day_repay_margin_deposit_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayMarginDeposit1IsSet();


  /** 
   * <em>day_repay_margin_deposit_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayMarginDeposit1IsModified();


  /** 
   * <em>day_repay_margin_deposit_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayRepayMarginDeposit2();


  /** 
   * <em>day_repay_margin_deposit_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayRepayMarginDeposit2IsNull();


  /** 
   * <em>day_repay_margin_deposit_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayMarginDeposit2IsSet();


  /** 
   * <em>day_repay_margin_deposit_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayMarginDeposit2IsModified();


  /** 
   * <em>day_repay_margin_deposit_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayRepayMarginDeposit3();


  /** 
   * <em>day_repay_margin_deposit_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayRepayMarginDeposit3IsNull();


  /** 
   * <em>day_repay_margin_deposit_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayMarginDeposit3IsSet();


  /** 
   * <em>day_repay_margin_deposit_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayMarginDeposit3IsModified();


  /** 
   * <em>day_repay_margin_deposit_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayRepayMarginDeposit4();


  /** 
   * <em>day_repay_margin_deposit_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayRepayMarginDeposit4IsNull();


  /** 
   * <em>day_repay_margin_deposit_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayMarginDeposit4IsSet();


  /** 
   * <em>day_repay_margin_deposit_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayMarginDeposit4IsModified();


  /** 
   * <em>day_repay_margin_deposit_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayRepayMarginDeposit5();


  /** 
   * <em>day_repay_margin_deposit_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayRepayMarginDeposit5IsNull();


  /** 
   * <em>day_repay_margin_deposit_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayMarginDeposit5IsSet();


  /** 
   * <em>day_repay_margin_deposit_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayMarginDeposit5IsModified();


  /** 
   * <em>day_repay_cash_margin_deposit0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayRepayCashMarginDeposit0();


  /** 
   * <em>day_repay_cash_margin_deposit0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayRepayCashMarginDeposit0IsNull();


  /** 
   * <em>day_repay_cash_margin_deposit0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayCashMarginDeposit0IsSet();


  /** 
   * <em>day_repay_cash_margin_deposit0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayCashMarginDeposit0IsModified();


  /** 
   * <em>day_repay_cash_margin_deposit1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayRepayCashMarginDeposit1();


  /** 
   * <em>day_repay_cash_margin_deposit1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayRepayCashMarginDeposit1IsNull();


  /** 
   * <em>day_repay_cash_margin_deposit1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayCashMarginDeposit1IsSet();


  /** 
   * <em>day_repay_cash_margin_deposit1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayCashMarginDeposit1IsModified();


  /** 
   * <em>day_repay_cash_margin_deposit2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayRepayCashMarginDeposit2();


  /** 
   * <em>day_repay_cash_margin_deposit2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayRepayCashMarginDeposit2IsNull();


  /** 
   * <em>day_repay_cash_margin_deposit2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayCashMarginDeposit2IsSet();


  /** 
   * <em>day_repay_cash_margin_deposit2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayCashMarginDeposit2IsModified();


  /** 
   * <em>day_repay_cash_margin_deposit3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayRepayCashMarginDeposit3();


  /** 
   * <em>day_repay_cash_margin_deposit3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayRepayCashMarginDeposit3IsNull();


  /** 
   * <em>day_repay_cash_margin_deposit3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayCashMarginDeposit3IsSet();


  /** 
   * <em>day_repay_cash_margin_deposit3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayCashMarginDeposit3IsModified();


  /** 
   * <em>day_repay_cash_margin_deposit4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayRepayCashMarginDeposit4();


  /** 
   * <em>day_repay_cash_margin_deposit4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayRepayCashMarginDeposit4IsNull();


  /** 
   * <em>day_repay_cash_margin_deposit4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayCashMarginDeposit4IsSet();


  /** 
   * <em>day_repay_cash_margin_deposit4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayCashMarginDeposit4IsModified();


  /** 
   * <em>day_repay_cash_margin_deposit5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDayRepayCashMarginDeposit5();


  /** 
   * <em>day_repay_cash_margin_deposit5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDayRepayCashMarginDeposit5IsNull();


  /** 
   * <em>day_repay_cash_margin_deposit5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayCashMarginDeposit5IsSet();


  /** 
   * <em>day_repay_cash_margin_deposit5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayRepayCashMarginDeposit5IsModified();


  /** 
   * <em>today_repay_contract_loss</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTodayRepayContractLoss();


  /** 
   * <em>today_repay_contract_loss</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayRepayContractLossIsNull();


  /** 
   * <em>today_repay_contract_loss</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayRepayContractLossIsSet();


  /** 
   * <em>today_repay_contract_loss</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayRepayContractLossIsModified();


  /** 
   * <em>today_repay_contract_profit</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTodayRepayContractProfit();


  /** 
   * <em>today_repay_contract_profit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayRepayContractProfitIsNull();


  /** 
   * <em>today_repay_contract_profit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayRepayContractProfitIsSet();


  /** 
   * <em>today_repay_contract_profit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayRepayContractProfitIsModified();


  /** 
   * <em>today_repay_contract_pre_asset</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTodayRepayContractPreAsset();


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
   * <em>contract_loss_designate_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractLossDesignate1();


  /** 
   * <em>contract_loss_designate_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractLossDesignate1IsNull();


  /** 
   * <em>contract_loss_designate_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractLossDesignate1IsSet();


  /** 
   * <em>contract_loss_designate_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractLossDesignate1IsModified();


  /** 
   * <em>contract_loss_designate_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractLossDesignate2();


  /** 
   * <em>contract_loss_designate_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractLossDesignate2IsNull();


  /** 
   * <em>contract_loss_designate_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractLossDesignate2IsSet();


  /** 
   * <em>contract_loss_designate_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractLossDesignate2IsModified();


  /** 
   * <em>contract_loss_designate_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractLossDesignate3();


  /** 
   * <em>contract_loss_designate_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractLossDesignate3IsNull();


  /** 
   * <em>contract_loss_designate_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractLossDesignate3IsSet();


  /** 
   * <em>contract_loss_designate_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractLossDesignate3IsModified();


  /** 
   * <em>contract_loss_designate_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractLossDesignate4();


  /** 
   * <em>contract_loss_designate_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractLossDesignate4IsNull();


  /** 
   * <em>contract_loss_designate_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractLossDesignate4IsSet();


  /** 
   * <em>contract_loss_designate_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractLossDesignate4IsModified();


  /** 
   * <em>contract_loss_designate_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractLossDesignate5();


  /** 
   * <em>contract_loss_designate_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractLossDesignate5IsNull();


  /** 
   * <em>contract_loss_designate_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractLossDesignate5IsSet();


  /** 
   * <em>contract_loss_designate_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractLossDesignate5IsModified();


  /** 
   * <em>contract_profit_designate_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractProfitDesignate1();


  /** 
   * <em>contract_profit_designate_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractProfitDesignate1IsNull();


  /** 
   * <em>contract_profit_designate_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractProfitDesignate1IsSet();


  /** 
   * <em>contract_profit_designate_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractProfitDesignate1IsModified();


  /** 
   * <em>contract_profit_designate_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractProfitDesignate2();


  /** 
   * <em>contract_profit_designate_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractProfitDesignate2IsNull();


  /** 
   * <em>contract_profit_designate_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractProfitDesignate2IsSet();


  /** 
   * <em>contract_profit_designate_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractProfitDesignate2IsModified();


  /** 
   * <em>contract_profit_designate_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractProfitDesignate3();


  /** 
   * <em>contract_profit_designate_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractProfitDesignate3IsNull();


  /** 
   * <em>contract_profit_designate_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractProfitDesignate3IsSet();


  /** 
   * <em>contract_profit_designate_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractProfitDesignate3IsModified();


  /** 
   * <em>contract_profit_designate_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractProfitDesignate4();


  /** 
   * <em>contract_profit_designate_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractProfitDesignate4IsNull();


  /** 
   * <em>contract_profit_designate_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractProfitDesignate4IsSet();


  /** 
   * <em>contract_profit_designate_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractProfitDesignate4IsModified();


  /** 
   * <em>contract_profit_designate_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractProfitDesignate5();


  /** 
   * <em>contract_profit_designate_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractProfitDesignate5IsNull();


  /** 
   * <em>contract_profit_designate_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractProfitDesignate5IsSet();


  /** 
   * <em>contract_profit_designate_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractProfitDesignate5IsModified();


  /** 
   * <em>payment_amount_designate_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentAmountDesignate0();


  /** 
   * <em>payment_amount_designate_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentAmountDesignate0IsNull();


  /** 
   * <em>payment_amount_designate_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate0IsSet();


  /** 
   * <em>payment_amount_designate_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate0IsModified();


  /** 
   * <em>payment_amount_designate_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentAmountDesignate1();


  /** 
   * <em>payment_amount_designate_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentAmountDesignate1IsNull();


  /** 
   * <em>payment_amount_designate_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate1IsSet();


  /** 
   * <em>payment_amount_designate_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate1IsModified();


  /** 
   * <em>payment_amount_designate_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentAmountDesignate2();


  /** 
   * <em>payment_amount_designate_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentAmountDesignate2IsNull();


  /** 
   * <em>payment_amount_designate_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate2IsSet();


  /** 
   * <em>payment_amount_designate_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate2IsModified();


  /** 
   * <em>payment_amount_designate_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentAmountDesignate3();


  /** 
   * <em>payment_amount_designate_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentAmountDesignate3IsNull();


  /** 
   * <em>payment_amount_designate_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate3IsSet();


  /** 
   * <em>payment_amount_designate_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate3IsModified();


  /** 
   * <em>payment_amount_designate_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentAmountDesignate4();


  /** 
   * <em>payment_amount_designate_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentAmountDesignate4IsNull();


  /** 
   * <em>payment_amount_designate_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate4IsSet();


  /** 
   * <em>payment_amount_designate_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate4IsModified();


  /** 
   * <em>payment_amount_designate_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPaymentAmountDesignate5();


  /** 
   * <em>payment_amount_designate_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPaymentAmountDesignate5IsNull();


  /** 
   * <em>payment_amount_designate_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate5IsSet();


  /** 
   * <em>payment_amount_designate_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentAmountDesignate5IsModified();


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
   * <em>today_dep_fund_restraint_0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTodayDepFundRestraint0();


  /** 
   * <em>today_dep_fund_restraint_0</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayDepFundRestraint0IsNull();


  /** 
   * <em>today_dep_fund_restraint_0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRestraint0IsSet();


  /** 
   * <em>today_dep_fund_restraint_0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRestraint0IsModified();


  /** 
   * <em>today_dep_fund_restraint_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTodayDepFundRestraint1();


  /** 
   * <em>today_dep_fund_restraint_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayDepFundRestraint1IsNull();


  /** 
   * <em>today_dep_fund_restraint_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRestraint1IsSet();


  /** 
   * <em>today_dep_fund_restraint_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRestraint1IsModified();


  /** 
   * <em>today_dep_fund_restraint_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTodayDepFundRestraint2();


  /** 
   * <em>today_dep_fund_restraint_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayDepFundRestraint2IsNull();


  /** 
   * <em>today_dep_fund_restraint_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRestraint2IsSet();


  /** 
   * <em>today_dep_fund_restraint_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRestraint2IsModified();


  /** 
   * <em>today_dep_fund_restraint_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTodayDepFundRestraint3();


  /** 
   * <em>today_dep_fund_restraint_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayDepFundRestraint3IsNull();


  /** 
   * <em>today_dep_fund_restraint_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRestraint3IsSet();


  /** 
   * <em>today_dep_fund_restraint_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRestraint3IsModified();


  /** 
   * <em>today_dep_fund_restraint_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTodayDepFundRestraint4();


  /** 
   * <em>today_dep_fund_restraint_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayDepFundRestraint4IsNull();


  /** 
   * <em>today_dep_fund_restraint_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRestraint4IsSet();


  /** 
   * <em>today_dep_fund_restraint_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRestraint4IsModified();


  /** 
   * <em>today_dep_fund_restraint_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTodayDepFundRestraint5();


  /** 
   * <em>today_dep_fund_restraint_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayDepFundRestraint5IsNull();


  /** 
   * <em>today_dep_fund_restraint_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRestraint5IsSet();


  /** 
   * <em>today_dep_fund_restraint_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRestraint5IsModified();


  /** 
   * <em>contract_asset_loss_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAssetLoss1();


  /** 
   * <em>contract_asset_loss_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAssetLoss1IsNull();


  /** 
   * <em>contract_asset_loss_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetLoss1IsSet();


  /** 
   * <em>contract_asset_loss_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetLoss1IsModified();


  /** 
   * <em>contract_asset_loss_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAssetLoss2();


  /** 
   * <em>contract_asset_loss_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAssetLoss2IsNull();


  /** 
   * <em>contract_asset_loss_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetLoss2IsSet();


  /** 
   * <em>contract_asset_loss_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetLoss2IsModified();


  /** 
   * <em>contract_asset_loss_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAssetLoss3();


  /** 
   * <em>contract_asset_loss_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAssetLoss3IsNull();


  /** 
   * <em>contract_asset_loss_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetLoss3IsSet();


  /** 
   * <em>contract_asset_loss_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetLoss3IsModified();


  /** 
   * <em>contract_asset_loss_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAssetLoss4();


  /** 
   * <em>contract_asset_loss_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAssetLoss4IsNull();


  /** 
   * <em>contract_asset_loss_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetLoss4IsSet();


  /** 
   * <em>contract_asset_loss_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetLoss4IsModified();


  /** 
   * <em>contract_asset_loss_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAssetLoss5();


  /** 
   * <em>contract_asset_loss_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAssetLoss5IsNull();


  /** 
   * <em>contract_asset_loss_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetLoss5IsSet();


  /** 
   * <em>contract_asset_loss_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetLoss5IsModified();


  /** 
   * <em>contract_asset_profit_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAssetProfit1();


  /** 
   * <em>contract_asset_profit_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAssetProfit1IsNull();


  /** 
   * <em>contract_asset_profit_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfit1IsSet();


  /** 
   * <em>contract_asset_profit_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfit1IsModified();


  /** 
   * <em>contract_asset_profit_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAssetProfit2();


  /** 
   * <em>contract_asset_profit_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAssetProfit2IsNull();


  /** 
   * <em>contract_asset_profit_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfit2IsSet();


  /** 
   * <em>contract_asset_profit_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfit2IsModified();


  /** 
   * <em>contract_asset_profit_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAssetProfit3();


  /** 
   * <em>contract_asset_profit_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAssetProfit3IsNull();


  /** 
   * <em>contract_asset_profit_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfit3IsSet();


  /** 
   * <em>contract_asset_profit_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfit3IsModified();


  /** 
   * <em>contract_asset_profit_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAssetProfit4();


  /** 
   * <em>contract_asset_profit_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAssetProfit4IsNull();


  /** 
   * <em>contract_asset_profit_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfit4IsSet();


  /** 
   * <em>contract_asset_profit_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfit4IsModified();


  /** 
   * <em>contract_asset_profit_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractAssetProfit5();


  /** 
   * <em>contract_asset_profit_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractAssetProfit5IsNull();


  /** 
   * <em>contract_asset_profit_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfit5IsSet();


  /** 
   * <em>contract_asset_profit_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractAssetProfit5IsModified();


  /** 
   * <em>foreign_equity_asset_delivered</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getForeignEquityAssetDelivered();


  /** 
   * <em>foreign_equity_asset_delivered</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getForeignEquityAssetDeliveredIsNull();


  /** 
   * <em>foreign_equity_asset_delivered</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignEquityAssetDeliveredIsSet();


  /** 
   * <em>foreign_equity_asset_delivered</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignEquityAssetDeliveredIsModified();


  /** 
   * <em>foreign_equity_asset_executed</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getForeignEquityAssetExecuted();


  /** 
   * <em>foreign_equity_asset_executed</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getForeignEquityAssetExecutedIsNull();


  /** 
   * <em>foreign_equity_asset_executed</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignEquityAssetExecutedIsSet();


  /** 
   * <em>foreign_equity_asset_executed</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignEquityAssetExecutedIsModified();


  /** 
   * <em>today_repay_contract_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTodayRepayContractAmount();


  /** 
   * <em>today_repay_contract_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTodayRepayContractAmountIsNull();


  /** 
   * <em>today_repay_contract_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayRepayContractAmountIsSet();


  /** 
   * <em>today_repay_contract_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayRepayContractAmountIsModified();


  /** 
   * <em>substitute_asset_old_day_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getSubstituteAssetOldDayValue();


  /** 
   * <em>substitute_asset_old_day_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSubstituteAssetOldDayValueIsNull();


  /** 
   * <em>substitute_asset_old_day_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubstituteAssetOldDayValueIsSet();


  /** 
   * <em>substitute_asset_old_day_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubstituteAssetOldDayValueIsModified();


  /** 
   * <em>setup_fee_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getSetupFee1();


  /** 
   * <em>setup_fee_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSetupFee1IsNull();


  /** 
   * <em>setup_fee_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSetupFee1IsSet();


  /** 
   * <em>setup_fee_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSetupFee1IsModified();


  /** 
   * <em>setup_fee_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getSetupFee2();


  /** 
   * <em>setup_fee_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSetupFee2IsNull();


  /** 
   * <em>setup_fee_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSetupFee2IsSet();


  /** 
   * <em>setup_fee_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSetupFee2IsModified();


  /** 
   * <em>setup_fee_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getSetupFee3();


  /** 
   * <em>setup_fee_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSetupFee3IsNull();


  /** 
   * <em>setup_fee_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSetupFee3IsSet();


  /** 
   * <em>setup_fee_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSetupFee3IsModified();


  /** 
   * <em>setup_fee_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getSetupFee4();


  /** 
   * <em>setup_fee_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSetupFee4IsNull();


  /** 
   * <em>setup_fee_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSetupFee4IsSet();


  /** 
   * <em>setup_fee_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSetupFee4IsModified();


  /** 
   * <em>setup_fee_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getSetupFee5();


  /** 
   * <em>setup_fee_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSetupFee5IsNull();


  /** 
   * <em>setup_fee_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSetupFee5IsSet();


  /** 
   * <em>setup_fee_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSetupFee5IsModified();


  /** 
   * <em>contract_interest_loss_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractInterestLoss1();


  /** 
   * <em>contract_interest_loss_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractInterestLoss1IsNull();


  /** 
   * <em>contract_interest_loss_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestLoss1IsSet();


  /** 
   * <em>contract_interest_loss_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestLoss1IsModified();


  /** 
   * <em>contract_interest_loss_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractInterestLoss2();


  /** 
   * <em>contract_interest_loss_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractInterestLoss2IsNull();


  /** 
   * <em>contract_interest_loss_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestLoss2IsSet();


  /** 
   * <em>contract_interest_loss_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestLoss2IsModified();


  /** 
   * <em>contract_interest_loss_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractInterestLoss3();


  /** 
   * <em>contract_interest_loss_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractInterestLoss3IsNull();


  /** 
   * <em>contract_interest_loss_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestLoss3IsSet();


  /** 
   * <em>contract_interest_loss_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestLoss3IsModified();


  /** 
   * <em>contract_interest_loss_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractInterestLoss4();


  /** 
   * <em>contract_interest_loss_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractInterestLoss4IsNull();


  /** 
   * <em>contract_interest_loss_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestLoss4IsSet();


  /** 
   * <em>contract_interest_loss_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestLoss4IsModified();


  /** 
   * <em>contract_interest_loss_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractInterestLoss5();


  /** 
   * <em>contract_interest_loss_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractInterestLoss5IsNull();


  /** 
   * <em>contract_interest_loss_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestLoss5IsSet();


  /** 
   * <em>contract_interest_loss_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestLoss5IsModified();


  /** 
   * <em>contract_interest_profit_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractInterestProfit1();


  /** 
   * <em>contract_interest_profit_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractInterestProfit1IsNull();


  /** 
   * <em>contract_interest_profit_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestProfit1IsSet();


  /** 
   * <em>contract_interest_profit_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestProfit1IsModified();


  /** 
   * <em>contract_interest_profit_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractInterestProfit2();


  /** 
   * <em>contract_interest_profit_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractInterestProfit2IsNull();


  /** 
   * <em>contract_interest_profit_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestProfit2IsSet();


  /** 
   * <em>contract_interest_profit_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestProfit2IsModified();


  /** 
   * <em>contract_interest_profit_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractInterestProfit3();


  /** 
   * <em>contract_interest_profit_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractInterestProfit3IsNull();


  /** 
   * <em>contract_interest_profit_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestProfit3IsSet();


  /** 
   * <em>contract_interest_profit_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestProfit3IsModified();


  /** 
   * <em>contract_interest_profit_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractInterestProfit4();


  /** 
   * <em>contract_interest_profit_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractInterestProfit4IsNull();


  /** 
   * <em>contract_interest_profit_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestProfit4IsSet();


  /** 
   * <em>contract_interest_profit_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestProfit4IsModified();


  /** 
   * <em>contract_interest_profit_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractInterestProfit5();


  /** 
   * <em>contract_interest_profit_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractInterestProfit5IsNull();


  /** 
   * <em>contract_interest_profit_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestProfit5IsSet();


  /** 
   * <em>contract_interest_profit_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInterestProfit5IsModified();


  /** 
   * <em>contract_other_cost_1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractOtherCost1();


  /** 
   * <em>contract_other_cost_1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractOtherCost1IsNull();


  /** 
   * <em>contract_other_cost_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractOtherCost1IsSet();


  /** 
   * <em>contract_other_cost_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractOtherCost1IsModified();


  /** 
   * <em>contract_other_cost_2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractOtherCost2();


  /** 
   * <em>contract_other_cost_2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractOtherCost2IsNull();


  /** 
   * <em>contract_other_cost_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractOtherCost2IsSet();


  /** 
   * <em>contract_other_cost_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractOtherCost2IsModified();


  /** 
   * <em>contract_other_cost_3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractOtherCost3();


  /** 
   * <em>contract_other_cost_3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractOtherCost3IsNull();


  /** 
   * <em>contract_other_cost_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractOtherCost3IsSet();


  /** 
   * <em>contract_other_cost_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractOtherCost3IsModified();


  /** 
   * <em>contract_other_cost_4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractOtherCost4();


  /** 
   * <em>contract_other_cost_4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractOtherCost4IsNull();


  /** 
   * <em>contract_other_cost_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractOtherCost4IsSet();


  /** 
   * <em>contract_other_cost_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractOtherCost4IsModified();


  /** 
   * <em>contract_other_cost_5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getContractOtherCost5();


  /** 
   * <em>contract_other_cost_5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractOtherCost5IsNull();


  /** 
   * <em>contract_other_cost_5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractOtherCost5IsSet();


  /** 
   * <em>contract_other_cost_5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractOtherCost5IsModified();


}
@
