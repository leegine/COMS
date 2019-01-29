head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.31.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TradingpowerCalcConditionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * tradingpower_calc_condition�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link TradingpowerCalcConditionRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link TradingpowerCalcConditionParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link TradingpowerCalcConditionParams}��{@@link TradingpowerCalcConditionRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TradingpowerCalcConditionPK 
 * @@see TradingpowerCalcConditionRow 
 */
public class TradingpowerCalcConditionParams extends Params implements TradingpowerCalcConditionRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "tradingpower_calc_condition";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = TradingpowerCalcConditionRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return TradingpowerCalcConditionRow.TYPE;
  }


  /** 
   * <em>calc_condition_id</em>�J�����̒l 
   */
  public  long  calc_condition_id;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>branch_id</em>�J�����̒l 
   */
  public  long  branch_id;

  /** 
   * <em>asset_evaluation_div</em>�J�����̒l 
   */
  public  String  asset_evaluation_div;

  /** 
   * <em>special_debit_amount</em>�J�����̒l 
   */
  public  double  special_debit_amount;

  /** 
   * <em>trading_stop</em>�J�����̒l 
   */
  public  String  trading_stop;

  /** 
   * <em>margin_open_position_stop</em>�J�����̒l 
   */
  public  String  margin_open_position_stop;

  /** 
   * <em>ifo_open_position_stop</em>�J�����̒l 
   */
  public  String  ifo_open_position_stop;

  /** 
   * <em>payment_stop</em>�J�����̒l 
   */
  public  String  payment_stop;

  /** 
   * <em>other_trading_stop</em>�J�����̒l 
   */
  public  String  other_trading_stop;

  /** 
   * <em>last_updater</em>�J�����̒l 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>additional_deposit_stop</em>�J�����̒l 
   */
  public  String  additional_deposit_stop;

  private boolean calc_condition_id_is_set = false;
  private boolean calc_condition_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean asset_evaluation_div_is_set = false;
  private boolean asset_evaluation_div_is_modified = false;
  private boolean special_debit_amount_is_set = false;
  private boolean special_debit_amount_is_modified = false;
  private boolean trading_stop_is_set = false;
  private boolean trading_stop_is_modified = false;
  private boolean margin_open_position_stop_is_set = false;
  private boolean margin_open_position_stop_is_modified = false;
  private boolean ifo_open_position_stop_is_set = false;
  private boolean ifo_open_position_stop_is_modified = false;
  private boolean payment_stop_is_set = false;
  private boolean payment_stop_is_modified = false;
  private boolean other_trading_stop_is_set = false;
  private boolean other_trading_stop_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean additional_deposit_stop_is_set = false;
  private boolean additional_deposit_stop_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "calc_condition_id=" + calc_condition_id
      + "," + "account_id=" +account_id
      + "," + "branch_id=" +branch_id
      + "," + "asset_evaluation_div=" +asset_evaluation_div
      + "," + "special_debit_amount=" +special_debit_amount
      + "," + "trading_stop=" +trading_stop
      + "," + "margin_open_position_stop=" +margin_open_position_stop
      + "," + "ifo_open_position_stop=" +ifo_open_position_stop
      + "," + "payment_stop=" +payment_stop
      + "," + "other_trading_stop=" +other_trading_stop
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "additional_deposit_stop=" +additional_deposit_stop
      + "}";
  }


  /** 
   * �l�����ݒ��TradingpowerCalcConditionParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public TradingpowerCalcConditionParams() {
    calc_condition_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���TradingpowerCalcConditionRow�I�u�W�F�N�g�̒l�𗘗p����TradingpowerCalcConditionParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����TradingpowerCalcConditionRow�I�u�W�F�N�g 
   */
  public TradingpowerCalcConditionParams( TradingpowerCalcConditionRow p_row )
  {
    calc_condition_id = p_row.getCalcConditionId();
    calc_condition_id_is_set = p_row.getCalcConditionIdIsSet();
    calc_condition_id_is_modified = p_row.getCalcConditionIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    asset_evaluation_div = p_row.getAssetEvaluationDiv();
    asset_evaluation_div_is_set = p_row.getAssetEvaluationDivIsSet();
    asset_evaluation_div_is_modified = p_row.getAssetEvaluationDivIsModified();
    special_debit_amount = p_row.getSpecialDebitAmount();
    special_debit_amount_is_set = p_row.getSpecialDebitAmountIsSet();
    special_debit_amount_is_modified = p_row.getSpecialDebitAmountIsModified();
    trading_stop = p_row.getTradingStop();
    trading_stop_is_set = p_row.getTradingStopIsSet();
    trading_stop_is_modified = p_row.getTradingStopIsModified();
    margin_open_position_stop = p_row.getMarginOpenPositionStop();
    margin_open_position_stop_is_set = p_row.getMarginOpenPositionStopIsSet();
    margin_open_position_stop_is_modified = p_row.getMarginOpenPositionStopIsModified();
    ifo_open_position_stop = p_row.getIfoOpenPositionStop();
    ifo_open_position_stop_is_set = p_row.getIfoOpenPositionStopIsSet();
    ifo_open_position_stop_is_modified = p_row.getIfoOpenPositionStopIsModified();
    payment_stop = p_row.getPaymentStop();
    payment_stop_is_set = p_row.getPaymentStopIsSet();
    payment_stop_is_modified = p_row.getPaymentStopIsModified();
    other_trading_stop = p_row.getOtherTradingStop();
    other_trading_stop_is_set = p_row.getOtherTradingStopIsSet();
    other_trading_stop_is_modified = p_row.getOtherTradingStopIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    additional_deposit_stop = p_row.getAdditionalDepositStop();
    additional_deposit_stop_is_set = p_row.getAdditionalDepositStopIsSet();
    additional_deposit_stop_is_modified = p_row.getAdditionalDepositStopIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      account_id_is_set = true;
      account_id_is_modified = true;
      branch_id_is_set = true;
      branch_id_is_modified = true;
      asset_evaluation_div_is_set = true;
      asset_evaluation_div_is_modified = true;
      special_debit_amount_is_set = true;
      special_debit_amount_is_modified = true;
      trading_stop_is_set = true;
      trading_stop_is_modified = true;
      margin_open_position_stop_is_set = true;
      margin_open_position_stop_is_modified = true;
      ifo_open_position_stop_is_set = true;
      ifo_open_position_stop_is_modified = true;
      payment_stop_is_set = true;
      payment_stop_is_modified = true;
      other_trading_stop_is_set = true;
      other_trading_stop_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      additional_deposit_stop_is_set = true;
      additional_deposit_stop_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof TradingpowerCalcConditionRow ) )
       return false;
    return fieldsEqual( (TradingpowerCalcConditionRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�TradingpowerCalcConditionRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( TradingpowerCalcConditionRow row )
  {
    if ( calc_condition_id != row.getCalcConditionId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( branch_id != row.getBranchId() )
      return false;
    if ( asset_evaluation_div == null ) {
      if ( row.getAssetEvaluationDiv() != null )
        return false;
    } else if ( !asset_evaluation_div.equals( row.getAssetEvaluationDiv() ) ) {
        return false;
    }
    if ( special_debit_amount != row.getSpecialDebitAmount() )
      return false;
    if ( trading_stop == null ) {
      if ( row.getTradingStop() != null )
        return false;
    } else if ( !trading_stop.equals( row.getTradingStop() ) ) {
        return false;
    }
    if ( margin_open_position_stop == null ) {
      if ( row.getMarginOpenPositionStop() != null )
        return false;
    } else if ( !margin_open_position_stop.equals( row.getMarginOpenPositionStop() ) ) {
        return false;
    }
    if ( ifo_open_position_stop == null ) {
      if ( row.getIfoOpenPositionStop() != null )
        return false;
    } else if ( !ifo_open_position_stop.equals( row.getIfoOpenPositionStop() ) ) {
        return false;
    }
    if ( payment_stop == null ) {
      if ( row.getPaymentStop() != null )
        return false;
    } else if ( !payment_stop.equals( row.getPaymentStop() ) ) {
        return false;
    }
    if ( other_trading_stop == null ) {
      if ( row.getOtherTradingStop() != null )
        return false;
    } else if ( !other_trading_stop.equals( row.getOtherTradingStop() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    if ( additional_deposit_stop == null ) {
      if ( row.getAdditionalDepositStop() != null )
        return false;
    } else if ( !additional_deposit_stop.equals( row.getAdditionalDepositStop() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) calc_condition_id)
        + ((int) account_id)
        + ((int) branch_id)
        + (asset_evaluation_div!=null? asset_evaluation_div.hashCode(): 0) 
        + ((int) special_debit_amount)
        + (trading_stop!=null? trading_stop.hashCode(): 0) 
        + (margin_open_position_stop!=null? margin_open_position_stop.hashCode(): 0) 
        + (ifo_open_position_stop!=null? ifo_open_position_stop.hashCode(): 0) 
        + (payment_stop!=null? payment_stop.hashCode(): 0) 
        + (other_trading_stop!=null? other_trading_stop.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (additional_deposit_stop!=null? additional_deposit_stop.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !branch_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_id' must be set before inserting.");
		if ( !asset_evaluation_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'asset_evaluation_div' must be set before inserting.");
		if ( !special_debit_amount_is_set )
			throw new IllegalArgumentException("non-nullable field 'special_debit_amount' must be set before inserting.");
		if ( !trading_stop_is_set )
			throw new IllegalArgumentException("non-nullable field 'trading_stop' must be set before inserting.");
		if ( !margin_open_position_stop_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_open_position_stop' must be set before inserting.");
		if ( !ifo_open_position_stop_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_open_position_stop' must be set before inserting.");
		if ( !payment_stop_is_set )
			throw new IllegalArgumentException("non-nullable field 'payment_stop' must be set before inserting.");
		if ( !other_trading_stop_is_set )
			throw new IllegalArgumentException("non-nullable field 'other_trading_stop' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
		if ( !additional_deposit_stop_is_set )
			throw new IllegalArgumentException("non-nullable field 'additional_deposit_stop' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("calc_condition_id",new Long(calc_condition_id));
		map.put("account_id",new Long(account_id));
		map.put("branch_id",new Long(branch_id));
		map.put("asset_evaluation_div",asset_evaluation_div);
		map.put("special_debit_amount",new Double(special_debit_amount));
		map.put("trading_stop",trading_stop);
		map.put("margin_open_position_stop",margin_open_position_stop);
		map.put("ifo_open_position_stop",ifo_open_position_stop);
		map.put("payment_stop",payment_stop);
		map.put("other_trading_stop",other_trading_stop);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("additional_deposit_stop",additional_deposit_stop);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( asset_evaluation_div_is_modified )
			map.put("asset_evaluation_div",asset_evaluation_div);
		if ( special_debit_amount_is_modified )
			map.put("special_debit_amount",new Double(special_debit_amount));
		if ( trading_stop_is_modified )
			map.put("trading_stop",trading_stop);
		if ( margin_open_position_stop_is_modified )
			map.put("margin_open_position_stop",margin_open_position_stop);
		if ( ifo_open_position_stop_is_modified )
			map.put("ifo_open_position_stop",ifo_open_position_stop);
		if ( payment_stop_is_modified )
			map.put("payment_stop",payment_stop);
		if ( other_trading_stop_is_modified )
			map.put("other_trading_stop",other_trading_stop);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( additional_deposit_stop_is_modified )
			map.put("additional_deposit_stop",additional_deposit_stop);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			if ( asset_evaluation_div_is_set )
				map.put("asset_evaluation_div",asset_evaluation_div);
			if ( special_debit_amount_is_set )
				map.put("special_debit_amount",new Double(special_debit_amount));
			if ( trading_stop_is_set )
				map.put("trading_stop",trading_stop);
			if ( margin_open_position_stop_is_set )
				map.put("margin_open_position_stop",margin_open_position_stop);
			if ( ifo_open_position_stop_is_set )
				map.put("ifo_open_position_stop",ifo_open_position_stop);
			if ( payment_stop_is_set )
				map.put("payment_stop",payment_stop);
			if ( other_trading_stop_is_set )
				map.put("other_trading_stop",other_trading_stop);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( additional_deposit_stop_is_set )
				map.put("additional_deposit_stop",additional_deposit_stop);
		}
		return map;
	}


  /** 
   * <em>calc_condition_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getCalcConditionId()
  {
    return calc_condition_id;
  }


  /** 
   * <em>calc_condition_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcConditionIdIsSet() {
    return calc_condition_id_is_set;
  }


  /** 
   * <em>calc_condition_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcConditionIdIsModified() {
    return calc_condition_id_is_modified;
  }


  /** 
   * <em>account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
  }


  /** 
   * <em>branch_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
  }


  /** 
   * <em>asset_evaluation_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAssetEvaluationDiv()
  {
    return asset_evaluation_div;
  }


  /** 
   * <em>asset_evaluation_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAssetEvaluationDivIsSet() {
    return asset_evaluation_div_is_set;
  }


  /** 
   * <em>asset_evaluation_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAssetEvaluationDivIsModified() {
    return asset_evaluation_div_is_modified;
  }


  /** 
   * <em>special_debit_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getSpecialDebitAmount()
  {
    return special_debit_amount;
  }


  /** 
   * <em>special_debit_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecialDebitAmountIsSet() {
    return special_debit_amount_is_set;
  }


  /** 
   * <em>special_debit_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecialDebitAmountIsModified() {
    return special_debit_amount_is_modified;
  }


  /** 
   * <em>trading_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTradingStop()
  {
    return trading_stop;
  }


  /** 
   * <em>trading_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradingStopIsSet() {
    return trading_stop_is_set;
  }


  /** 
   * <em>trading_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradingStopIsModified() {
    return trading_stop_is_modified;
  }


  /** 
   * <em>margin_open_position_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMarginOpenPositionStop()
  {
    return margin_open_position_stop;
  }


  /** 
   * <em>margin_open_position_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarginOpenPositionStopIsSet() {
    return margin_open_position_stop_is_set;
  }


  /** 
   * <em>margin_open_position_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarginOpenPositionStopIsModified() {
    return margin_open_position_stop_is_modified;
  }


  /** 
   * <em>ifo_open_position_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getIfoOpenPositionStop()
  {
    return ifo_open_position_stop;
  }


  /** 
   * <em>ifo_open_position_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoOpenPositionStopIsSet() {
    return ifo_open_position_stop_is_set;
  }


  /** 
   * <em>ifo_open_position_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoOpenPositionStopIsModified() {
    return ifo_open_position_stop_is_modified;
  }


  /** 
   * <em>payment_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPaymentStop()
  {
    return payment_stop;
  }


  /** 
   * <em>payment_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentStopIsSet() {
    return payment_stop_is_set;
  }


  /** 
   * <em>payment_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentStopIsModified() {
    return payment_stop_is_modified;
  }


  /** 
   * <em>other_trading_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOtherTradingStop()
  {
    return other_trading_stop;
  }


  /** 
   * <em>other_trading_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOtherTradingStopIsSet() {
    return other_trading_stop_is_set;
  }


  /** 
   * <em>other_trading_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOtherTradingStopIsModified() {
    return other_trading_stop_is_modified;
  }


  /** 
   * <em>last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * <em>additional_deposit_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAdditionalDepositStop()
  {
    return additional_deposit_stop;
  }


  /** 
   * <em>additional_deposit_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAdditionalDepositStopIsSet() {
    return additional_deposit_stop_is_set;
  }


  /** 
   * <em>additional_deposit_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAdditionalDepositStopIsModified() {
    return additional_deposit_stop_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new TradingpowerCalcConditionPK(calc_condition_id);
  }


  /** 
   * <em>calc_condition_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_calcConditionId <em>calc_condition_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setCalcConditionId( long p_calcConditionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_condition_id = p_calcConditionId;
    calc_condition_id_is_set = true;
    calc_condition_id_is_modified = true;
  }


  /** 
   * <em>account_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountId <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>branch_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_branchId <em>branch_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>asset_evaluation_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_assetEvaluationDiv <em>asset_evaluation_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAssetEvaluationDiv( String p_assetEvaluationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    asset_evaluation_div = p_assetEvaluationDiv;
    asset_evaluation_div_is_set = true;
    asset_evaluation_div_is_modified = true;
  }


  /** 
   * <em>special_debit_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_specialDebitAmount <em>special_debit_amount</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setSpecialDebitAmount( double p_specialDebitAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_debit_amount = p_specialDebitAmount;
    special_debit_amount_is_set = true;
    special_debit_amount_is_modified = true;
  }


  /** 
   * <em>trading_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tradingStop <em>trading_stop</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setTradingStop( String p_tradingStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_stop = p_tradingStop;
    trading_stop_is_set = true;
    trading_stop_is_modified = true;
  }


  /** 
   * <em>margin_open_position_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marginOpenPositionStop <em>margin_open_position_stop</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setMarginOpenPositionStop( String p_marginOpenPositionStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_open_position_stop = p_marginOpenPositionStop;
    margin_open_position_stop_is_set = true;
    margin_open_position_stop_is_modified = true;
  }


  /** 
   * <em>ifo_open_position_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ifoOpenPositionStop <em>ifo_open_position_stop</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setIfoOpenPositionStop( String p_ifoOpenPositionStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_open_position_stop = p_ifoOpenPositionStop;
    ifo_open_position_stop_is_set = true;
    ifo_open_position_stop_is_modified = true;
  }


  /** 
   * <em>payment_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_paymentStop <em>payment_stop</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setPaymentStop( String p_paymentStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_stop = p_paymentStop;
    payment_stop_is_set = true;
    payment_stop_is_modified = true;
  }


  /** 
   * <em>other_trading_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_otherTradingStop <em>other_trading_stop</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setOtherTradingStop( String p_otherTradingStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_trading_stop = p_otherTradingStop;
    other_trading_stop_is_set = true;
    other_trading_stop_is_modified = true;
  }


  /** 
   * <em>last_updater</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdater <em>last_updater</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>created_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>last_updated_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * <em>additional_deposit_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_additionalDepositStop <em>additional_deposit_stop</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAdditionalDepositStop( String p_additionalDepositStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    additional_deposit_stop = p_additionalDepositStop;
    additional_deposit_stop_is_set = true;
    additional_deposit_stop_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("asset_evaluation_div") ) {
                    return this.asset_evaluation_div;
                }
                else if ( name.equals("additional_deposit_stop") ) {
                    return this.additional_deposit_stop;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                break;
            case 'c':
                if ( name.equals("calc_condition_id") ) {
                    return new Long( calc_condition_id );
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("ifo_open_position_stop") ) {
                    return this.ifo_open_position_stop;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("margin_open_position_stop") ) {
                    return this.margin_open_position_stop;
                }
                break;
            case 'o':
                if ( name.equals("other_trading_stop") ) {
                    return this.other_trading_stop;
                }
                break;
            case 'p':
                if ( name.equals("payment_stop") ) {
                    return this.payment_stop;
                }
                break;
            case 's':
                if ( name.equals("special_debit_amount") ) {
                    return new Double( special_debit_amount );
                }
                break;
            case 't':
                if ( name.equals("trading_stop") ) {
                    return this.trading_stop;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("asset_evaluation_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'asset_evaluation_div' must be String: '"+value+"' is not." );
                    this.asset_evaluation_div = (String) value;
                    if (this.asset_evaluation_div_is_set)
                        this.asset_evaluation_div_is_modified = true;
                    this.asset_evaluation_div_is_set = true;
                    return;
                }
                else if ( name.equals("additional_deposit_stop") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'additional_deposit_stop' must be String: '"+value+"' is not." );
                    this.additional_deposit_stop = (String) value;
                    if (this.additional_deposit_stop_is_set)
                        this.additional_deposit_stop_is_modified = true;
                    this.additional_deposit_stop_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("calc_condition_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'calc_condition_id' must be Long: '"+value+"' is not." );
                    this.calc_condition_id = ((Long) value).longValue();
                    if (this.calc_condition_id_is_set)
                        this.calc_condition_id_is_modified = true;
                    this.calc_condition_id_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("ifo_open_position_stop") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_open_position_stop' must be String: '"+value+"' is not." );
                    this.ifo_open_position_stop = (String) value;
                    if (this.ifo_open_position_stop_is_set)
                        this.ifo_open_position_stop_is_modified = true;
                    this.ifo_open_position_stop_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("margin_open_position_stop") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_open_position_stop' must be String: '"+value+"' is not." );
                    this.margin_open_position_stop = (String) value;
                    if (this.margin_open_position_stop_is_set)
                        this.margin_open_position_stop_is_modified = true;
                    this.margin_open_position_stop_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("other_trading_stop") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'other_trading_stop' must be String: '"+value+"' is not." );
                    this.other_trading_stop = (String) value;
                    if (this.other_trading_stop_is_set)
                        this.other_trading_stop_is_modified = true;
                    this.other_trading_stop_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("payment_stop") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_stop' must be String: '"+value+"' is not." );
                    this.payment_stop = (String) value;
                    if (this.payment_stop_is_set)
                        this.payment_stop_is_modified = true;
                    this.payment_stop_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("special_debit_amount") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'special_debit_amount' must be Double: '"+value+"' is not." );
                    this.special_debit_amount = ((Double) value).doubleValue();
                    if (this.special_debit_amount_is_set)
                        this.special_debit_amount_is_modified = true;
                    this.special_debit_amount_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trading_stop") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_stop' must be String: '"+value+"' is not." );
                    this.trading_stop = (String) value;
                    if (this.trading_stop_is_set)
                        this.trading_stop_is_modified = true;
                    this.trading_stop_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
