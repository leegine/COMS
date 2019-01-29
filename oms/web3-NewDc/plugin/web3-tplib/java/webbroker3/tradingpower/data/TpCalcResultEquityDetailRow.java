head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.28.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultEquityDetailRow.java;


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
 * TpCalcResultEquityDetailRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>tp_calc_result_equity_detail</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link TpCalcResultEquityDetailRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCalcResultEquityDetailPK 
 */
public interface TpCalcResultEquityDetailRow extends Row {


  /** 
   * ����{@@link TpCalcResultEquityDetailRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "tp_calc_result_equity_detail", "account" );


  /** 
   * <em>calc_result_equity_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getCalcResultEquityId();


  /** 
   * <em>calc_result_equity_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcResultEquityIdIsSet();


  /** 
   * <em>calc_result_equity_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcResultEquityIdIsModified();


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


}
@
