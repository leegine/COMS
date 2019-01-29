head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.30.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpAssetHistoryParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * tp_asset_history�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link TpAssetHistoryRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link TpAssetHistoryParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link TpAssetHistoryParams}��{@@link TpAssetHistoryRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpAssetHistoryPK 
 * @@see TpAssetHistoryRow 
 */
public class TpAssetHistoryParams extends Params implements TpAssetHistoryRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "tp_asset_history";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = TpAssetHistoryRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return TpAssetHistoryRow.TYPE;
  }


  /** 
   * <em>tp_asset_history_id</em>�J�����̒l 
   */
  public  long  tp_asset_history_id;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>calc_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  calc_date;

  /** 
   * <em>account_balance</em>�J�����̒l 
   */
  public  Double  account_balance;

  /** 
   * <em>equity_asset_delivered</em>�J�����̒l 
   */
  public  Double  equity_asset_delivered;

  /** 
   * <em>mini_stock_asset_delivered</em>�J�����̒l 
   */
  public  Double  mini_stock_asset_delivered;

  /** 
   * <em>ruito_asset_delivered</em>�J�����̒l 
   */
  public  Double  ruito_asset_delivered;

  /** 
   * <em>mutual_fund_asset_delivered</em>�J�����̒l 
   */
  public  Double  mutual_fund_asset_delivered;

  /** 
   * <em>bond_asset_delivered</em>�J�����̒l 
   */
  public  Double  bond_asset_delivered;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean tp_asset_history_id_is_set = false;
  private boolean tp_asset_history_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean calc_date_is_set = false;
  private boolean calc_date_is_modified = false;
  private boolean account_balance_is_set = false;
  private boolean account_balance_is_modified = false;
  private boolean equity_asset_delivered_is_set = false;
  private boolean equity_asset_delivered_is_modified = false;
  private boolean mini_stock_asset_delivered_is_set = false;
  private boolean mini_stock_asset_delivered_is_modified = false;
  private boolean ruito_asset_delivered_is_set = false;
  private boolean ruito_asset_delivered_is_modified = false;
  private boolean mutual_fund_asset_delivered_is_set = false;
  private boolean mutual_fund_asset_delivered_is_modified = false;
  private boolean bond_asset_delivered_is_set = false;
  private boolean bond_asset_delivered_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "tp_asset_history_id=" + tp_asset_history_id
      + "," + "account_id=" +account_id
      + "," + "calc_date=" +calc_date
      + "," + "account_balance=" +account_balance
      + "," + "equity_asset_delivered=" +equity_asset_delivered
      + "," + "mini_stock_asset_delivered=" +mini_stock_asset_delivered
      + "," + "ruito_asset_delivered=" +ruito_asset_delivered
      + "," + "mutual_fund_asset_delivered=" +mutual_fund_asset_delivered
      + "," + "bond_asset_delivered=" +bond_asset_delivered
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��TpAssetHistoryParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public TpAssetHistoryParams() {
    tp_asset_history_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���TpAssetHistoryRow�I�u�W�F�N�g�̒l�𗘗p����TpAssetHistoryParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����TpAssetHistoryRow�I�u�W�F�N�g 
   */
  public TpAssetHistoryParams( TpAssetHistoryRow p_row )
  {
    tp_asset_history_id = p_row.getTpAssetHistoryId();
    tp_asset_history_id_is_set = p_row.getTpAssetHistoryIdIsSet();
    tp_asset_history_id_is_modified = p_row.getTpAssetHistoryIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    calc_date = p_row.getCalcDate();
    calc_date_is_set = p_row.getCalcDateIsSet();
    calc_date_is_modified = p_row.getCalcDateIsModified();
    if ( !p_row.getAccountBalanceIsNull() )
      account_balance = new Double( p_row.getAccountBalance() );
    account_balance_is_set = p_row.getAccountBalanceIsSet();
    account_balance_is_modified = p_row.getAccountBalanceIsModified();
    if ( !p_row.getEquityAssetDeliveredIsNull() )
      equity_asset_delivered = new Double( p_row.getEquityAssetDelivered() );
    equity_asset_delivered_is_set = p_row.getEquityAssetDeliveredIsSet();
    equity_asset_delivered_is_modified = p_row.getEquityAssetDeliveredIsModified();
    if ( !p_row.getMiniStockAssetDeliveredIsNull() )
      mini_stock_asset_delivered = new Double( p_row.getMiniStockAssetDelivered() );
    mini_stock_asset_delivered_is_set = p_row.getMiniStockAssetDeliveredIsSet();
    mini_stock_asset_delivered_is_modified = p_row.getMiniStockAssetDeliveredIsModified();
    if ( !p_row.getRuitoAssetDeliveredIsNull() )
      ruito_asset_delivered = new Double( p_row.getRuitoAssetDelivered() );
    ruito_asset_delivered_is_set = p_row.getRuitoAssetDeliveredIsSet();
    ruito_asset_delivered_is_modified = p_row.getRuitoAssetDeliveredIsModified();
    if ( !p_row.getMutualFundAssetDeliveredIsNull() )
      mutual_fund_asset_delivered = new Double( p_row.getMutualFundAssetDelivered() );
    mutual_fund_asset_delivered_is_set = p_row.getMutualFundAssetDeliveredIsSet();
    mutual_fund_asset_delivered_is_modified = p_row.getMutualFundAssetDeliveredIsModified();
    if ( !p_row.getBondAssetDeliveredIsNull() )
      bond_asset_delivered = new Double( p_row.getBondAssetDelivered() );
    bond_asset_delivered_is_set = p_row.getBondAssetDeliveredIsSet();
    bond_asset_delivered_is_modified = p_row.getBondAssetDeliveredIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      account_id_is_set = true;
      account_id_is_modified = true;
      calc_date_is_set = true;
      calc_date_is_modified = true;
      account_balance_is_set = true;
      account_balance_is_modified = true;
      equity_asset_delivered_is_set = true;
      equity_asset_delivered_is_modified = true;
      mini_stock_asset_delivered_is_set = true;
      mini_stock_asset_delivered_is_modified = true;
      ruito_asset_delivered_is_set = true;
      ruito_asset_delivered_is_modified = true;
      mutual_fund_asset_delivered_is_set = true;
      mutual_fund_asset_delivered_is_modified = true;
      bond_asset_delivered_is_set = true;
      bond_asset_delivered_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof TpAssetHistoryRow ) )
       return false;
    return fieldsEqual( (TpAssetHistoryRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�TpAssetHistoryRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( TpAssetHistoryRow row )
  {
    if ( tp_asset_history_id != row.getTpAssetHistoryId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( calc_date == null ) {
      if ( row.getCalcDate() != null )
        return false;
    } else if ( !calc_date.equals( row.getCalcDate() ) ) {
        return false;
    }
    if ( account_balance == null ) {
      if ( !row.getAccountBalanceIsNull() )
        return false;
    } else if ( row.getAccountBalanceIsNull() || ( account_balance.doubleValue() != row.getAccountBalance() ) ) {
        return false;
    }
    if ( equity_asset_delivered == null ) {
      if ( !row.getEquityAssetDeliveredIsNull() )
        return false;
    } else if ( row.getEquityAssetDeliveredIsNull() || ( equity_asset_delivered.doubleValue() != row.getEquityAssetDelivered() ) ) {
        return false;
    }
    if ( mini_stock_asset_delivered == null ) {
      if ( !row.getMiniStockAssetDeliveredIsNull() )
        return false;
    } else if ( row.getMiniStockAssetDeliveredIsNull() || ( mini_stock_asset_delivered.doubleValue() != row.getMiniStockAssetDelivered() ) ) {
        return false;
    }
    if ( ruito_asset_delivered == null ) {
      if ( !row.getRuitoAssetDeliveredIsNull() )
        return false;
    } else if ( row.getRuitoAssetDeliveredIsNull() || ( ruito_asset_delivered.doubleValue() != row.getRuitoAssetDelivered() ) ) {
        return false;
    }
    if ( mutual_fund_asset_delivered == null ) {
      if ( !row.getMutualFundAssetDeliveredIsNull() )
        return false;
    } else if ( row.getMutualFundAssetDeliveredIsNull() || ( mutual_fund_asset_delivered.doubleValue() != row.getMutualFundAssetDelivered() ) ) {
        return false;
    }
    if ( bond_asset_delivered == null ) {
      if ( !row.getBondAssetDeliveredIsNull() )
        return false;
    } else if ( row.getBondAssetDeliveredIsNull() || ( bond_asset_delivered.doubleValue() != row.getBondAssetDelivered() ) ) {
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
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) tp_asset_history_id)
        + ((int) account_id)
        + (calc_date!=null? calc_date.hashCode(): 0) 
        + (account_balance!=null? account_balance.hashCode(): 0) 
        + (equity_asset_delivered!=null? equity_asset_delivered.hashCode(): 0) 
        + (mini_stock_asset_delivered!=null? mini_stock_asset_delivered.hashCode(): 0) 
        + (ruito_asset_delivered!=null? ruito_asset_delivered.hashCode(): 0) 
        + (mutual_fund_asset_delivered!=null? mutual_fund_asset_delivered.hashCode(): 0) 
        + (bond_asset_delivered!=null? bond_asset_delivered.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !calc_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'calc_date' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("tp_asset_history_id",new Long(tp_asset_history_id));
		map.put("account_id",new Long(account_id));
		map.put("calc_date",calc_date);
		if ( account_balance_is_set )
			map.put("account_balance",account_balance);
		if ( equity_asset_delivered_is_set )
			map.put("equity_asset_delivered",equity_asset_delivered);
		if ( mini_stock_asset_delivered_is_set )
			map.put("mini_stock_asset_delivered",mini_stock_asset_delivered);
		if ( ruito_asset_delivered_is_set )
			map.put("ruito_asset_delivered",ruito_asset_delivered);
		if ( mutual_fund_asset_delivered_is_set )
			map.put("mutual_fund_asset_delivered",mutual_fund_asset_delivered);
		if ( bond_asset_delivered_is_set )
			map.put("bond_asset_delivered",bond_asset_delivered);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( calc_date_is_modified )
			map.put("calc_date",calc_date);
		if ( account_balance_is_modified )
			map.put("account_balance",account_balance);
		if ( equity_asset_delivered_is_modified )
			map.put("equity_asset_delivered",equity_asset_delivered);
		if ( mini_stock_asset_delivered_is_modified )
			map.put("mini_stock_asset_delivered",mini_stock_asset_delivered);
		if ( ruito_asset_delivered_is_modified )
			map.put("ruito_asset_delivered",ruito_asset_delivered);
		if ( mutual_fund_asset_delivered_is_modified )
			map.put("mutual_fund_asset_delivered",mutual_fund_asset_delivered);
		if ( bond_asset_delivered_is_modified )
			map.put("bond_asset_delivered",bond_asset_delivered);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( calc_date_is_set )
				map.put("calc_date",calc_date);
			if ( account_balance_is_set )
				map.put("account_balance",account_balance);
			if ( equity_asset_delivered_is_set )
				map.put("equity_asset_delivered",equity_asset_delivered);
			if ( mini_stock_asset_delivered_is_set )
				map.put("mini_stock_asset_delivered",mini_stock_asset_delivered);
			if ( ruito_asset_delivered_is_set )
				map.put("ruito_asset_delivered",ruito_asset_delivered);
			if ( mutual_fund_asset_delivered_is_set )
				map.put("mutual_fund_asset_delivered",mutual_fund_asset_delivered);
			if ( bond_asset_delivered_is_set )
				map.put("bond_asset_delivered",bond_asset_delivered);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>tp_asset_history_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getTpAssetHistoryId()
  {
    return tp_asset_history_id;
  }


  /** 
   * <em>tp_asset_history_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTpAssetHistoryIdIsSet() {
    return tp_asset_history_id_is_set;
  }


  /** 
   * <em>tp_asset_history_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTpAssetHistoryIdIsModified() {
    return tp_asset_history_id_is_modified;
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
   * <em>calc_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getCalcDate()
  {
    return calc_date;
  }


  /** 
   * <em>calc_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcDateIsSet() {
    return calc_date_is_set;
  }


  /** 
   * <em>calc_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcDateIsModified() {
    return calc_date_is_modified;
  }


  /** 
   * <em>account_balance</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAccountBalance()
  {
    return ( account_balance==null? ((double)0): account_balance.doubleValue() );
  }


  /** 
   * <em>account_balance</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getAccountBalanceIsNull()
  {
    return account_balance == null;
  }


  /** 
   * <em>account_balance</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountBalanceIsSet() {
    return account_balance_is_set;
  }


  /** 
   * <em>account_balance</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountBalanceIsModified() {
    return account_balance_is_modified;
  }


  /** 
   * <em>equity_asset_delivered</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getEquityAssetDelivered()
  {
    return ( equity_asset_delivered==null? ((double)0): equity_asset_delivered.doubleValue() );
  }


  /** 
   * <em>equity_asset_delivered</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getEquityAssetDeliveredIsNull()
  {
    return equity_asset_delivered == null;
  }


  /** 
   * <em>equity_asset_delivered</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEquityAssetDeliveredIsSet() {
    return equity_asset_delivered_is_set;
  }


  /** 
   * <em>equity_asset_delivered</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEquityAssetDeliveredIsModified() {
    return equity_asset_delivered_is_modified;
  }


  /** 
   * <em>mini_stock_asset_delivered</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getMiniStockAssetDelivered()
  {
    return ( mini_stock_asset_delivered==null? ((double)0): mini_stock_asset_delivered.doubleValue() );
  }


  /** 
   * <em>mini_stock_asset_delivered</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getMiniStockAssetDeliveredIsNull()
  {
    return mini_stock_asset_delivered == null;
  }


  /** 
   * <em>mini_stock_asset_delivered</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMiniStockAssetDeliveredIsSet() {
    return mini_stock_asset_delivered_is_set;
  }


  /** 
   * <em>mini_stock_asset_delivered</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMiniStockAssetDeliveredIsModified() {
    return mini_stock_asset_delivered_is_modified;
  }


  /** 
   * <em>ruito_asset_delivered</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getRuitoAssetDelivered()
  {
    return ( ruito_asset_delivered==null? ((double)0): ruito_asset_delivered.doubleValue() );
  }


  /** 
   * <em>ruito_asset_delivered</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getRuitoAssetDeliveredIsNull()
  {
    return ruito_asset_delivered == null;
  }


  /** 
   * <em>ruito_asset_delivered</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRuitoAssetDeliveredIsSet() {
    return ruito_asset_delivered_is_set;
  }


  /** 
   * <em>ruito_asset_delivered</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRuitoAssetDeliveredIsModified() {
    return ruito_asset_delivered_is_modified;
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getMutualFundAssetDelivered()
  {
    return ( mutual_fund_asset_delivered==null? ((double)0): mutual_fund_asset_delivered.doubleValue() );
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getMutualFundAssetDeliveredIsNull()
  {
    return mutual_fund_asset_delivered == null;
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMutualFundAssetDeliveredIsSet() {
    return mutual_fund_asset_delivered_is_set;
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMutualFundAssetDeliveredIsModified() {
    return mutual_fund_asset_delivered_is_modified;
  }


  /** 
   * <em>bond_asset_delivered</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getBondAssetDelivered()
  {
    return ( bond_asset_delivered==null? ((double)0): bond_asset_delivered.doubleValue() );
  }


  /** 
   * <em>bond_asset_delivered</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getBondAssetDeliveredIsNull()
  {
    return bond_asset_delivered == null;
  }


  /** 
   * <em>bond_asset_delivered</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBondAssetDeliveredIsSet() {
    return bond_asset_delivered_is_set;
  }


  /** 
   * <em>bond_asset_delivered</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBondAssetDeliveredIsModified() {
    return bond_asset_delivered_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new TpAssetHistoryPK(tp_asset_history_id);
  }


  /** 
   * <em>tp_asset_history_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tpAssetHistoryId <em>tp_asset_history_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setTpAssetHistoryId( long p_tpAssetHistoryId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tp_asset_history_id = p_tpAssetHistoryId;
    tp_asset_history_id_is_set = true;
    tp_asset_history_id_is_modified = true;
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
   * <em>calc_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_calcDate <em>calc_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setCalcDate( java.sql.Timestamp p_calcDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_date = p_calcDate;
    calc_date_is_set = true;
    calc_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>calc_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setCalcDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    calc_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    calc_date_is_set = true;
    calc_date_is_modified = true;
  }


  /** 
   * <em>account_balance</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountBalance <em>account_balance</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setAccountBalance( double p_accountBalance )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance = new Double(p_accountBalance);
    account_balance_is_set = true;
    account_balance_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>account_balance</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setAccountBalance( Double p_accountBalance )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance = p_accountBalance;
    account_balance_is_set = true;
    account_balance_is_modified = true;
  }


  /** 
   * <em>equity_asset_delivered</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_equityAssetDelivered <em>equity_asset_delivered</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setEquityAssetDelivered( double p_equityAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_asset_delivered = new Double(p_equityAssetDelivered);
    equity_asset_delivered_is_set = true;
    equity_asset_delivered_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>equity_asset_delivered</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setEquityAssetDelivered( Double p_equityAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    equity_asset_delivered = p_equityAssetDelivered;
    equity_asset_delivered_is_set = true;
    equity_asset_delivered_is_modified = true;
  }


  /** 
   * <em>mini_stock_asset_delivered</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_miniStockAssetDelivered <em>mini_stock_asset_delivered</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setMiniStockAssetDelivered( double p_miniStockAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_asset_delivered = new Double(p_miniStockAssetDelivered);
    mini_stock_asset_delivered_is_set = true;
    mini_stock_asset_delivered_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>mini_stock_asset_delivered</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setMiniStockAssetDelivered( Double p_miniStockAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_asset_delivered = p_miniStockAssetDelivered;
    mini_stock_asset_delivered_is_set = true;
    mini_stock_asset_delivered_is_modified = true;
  }


  /** 
   * <em>ruito_asset_delivered</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ruitoAssetDelivered <em>ruito_asset_delivered</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setRuitoAssetDelivered( double p_ruitoAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_asset_delivered = new Double(p_ruitoAssetDelivered);
    ruito_asset_delivered_is_set = true;
    ruito_asset_delivered_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>ruito_asset_delivered</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setRuitoAssetDelivered( Double p_ruitoAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_asset_delivered = p_ruitoAssetDelivered;
    ruito_asset_delivered_is_set = true;
    ruito_asset_delivered_is_modified = true;
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mutualFundAssetDelivered <em>mutual_fund_asset_delivered</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setMutualFundAssetDelivered( double p_mutualFundAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mutual_fund_asset_delivered = new Double(p_mutualFundAssetDelivered);
    mutual_fund_asset_delivered_is_set = true;
    mutual_fund_asset_delivered_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>mutual_fund_asset_delivered</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setMutualFundAssetDelivered( Double p_mutualFundAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mutual_fund_asset_delivered = p_mutualFundAssetDelivered;
    mutual_fund_asset_delivered_is_set = true;
    mutual_fund_asset_delivered_is_modified = true;
  }


  /** 
   * <em>bond_asset_delivered</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bondAssetDelivered <em>bond_asset_delivered</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setBondAssetDelivered( double p_bondAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_asset_delivered = new Double(p_bondAssetDelivered);
    bond_asset_delivered_is_set = true;
    bond_asset_delivered_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>bond_asset_delivered</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setBondAssetDelivered( Double p_bondAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bond_asset_delivered = p_bondAssetDelivered;
    bond_asset_delivered_is_set = true;
    bond_asset_delivered_is_modified = true;
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
                else if ( name.equals("account_balance") ) {
                    return this.account_balance;
                }
                break;
            case 'b':
                if ( name.equals("bond_asset_delivered") ) {
                    return this.bond_asset_delivered;
                }
                break;
            case 'c':
                if ( name.equals("calc_date") ) {
                    return this.calc_date;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("equity_asset_delivered") ) {
                    return this.equity_asset_delivered;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("mini_stock_asset_delivered") ) {
                    return this.mini_stock_asset_delivered;
                }
                else if ( name.equals("mutual_fund_asset_delivered") ) {
                    return this.mutual_fund_asset_delivered;
                }
                break;
            case 'r':
                if ( name.equals("ruito_asset_delivered") ) {
                    return this.ruito_asset_delivered;
                }
                break;
            case 't':
                if ( name.equals("tp_asset_history_id") ) {
                    return new Long( tp_asset_history_id );
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
                else if ( name.equals("account_balance") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance' must be Double: '"+value+"' is not." );
                    this.account_balance = (Double) value;
                    if (this.account_balance_is_set)
                        this.account_balance_is_modified = true;
                    this.account_balance_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("bond_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'bond_asset_delivered' must be Double: '"+value+"' is not." );
                    this.bond_asset_delivered = (Double) value;
                    if (this.bond_asset_delivered_is_set)
                        this.bond_asset_delivered_is_modified = true;
                    this.bond_asset_delivered_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("calc_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'calc_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.calc_date = (java.sql.Timestamp) value;
                    if (this.calc_date_is_set)
                        this.calc_date_is_modified = true;
                    this.calc_date_is_set = true;
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
            case 'e':
                if ( name.equals("equity_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'equity_asset_delivered' must be Double: '"+value+"' is not." );
                    this.equity_asset_delivered = (Double) value;
                    if (this.equity_asset_delivered_is_set)
                        this.equity_asset_delivered_is_modified = true;
                    this.equity_asset_delivered_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
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
                if ( name.equals("mini_stock_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'mini_stock_asset_delivered' must be Double: '"+value+"' is not." );
                    this.mini_stock_asset_delivered = (Double) value;
                    if (this.mini_stock_asset_delivered_is_set)
                        this.mini_stock_asset_delivered_is_modified = true;
                    this.mini_stock_asset_delivered_is_set = true;
                    return;
                }
                else if ( name.equals("mutual_fund_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'mutual_fund_asset_delivered' must be Double: '"+value+"' is not." );
                    this.mutual_fund_asset_delivered = (Double) value;
                    if (this.mutual_fund_asset_delivered_is_set)
                        this.mutual_fund_asset_delivered_is_modified = true;
                    this.mutual_fund_asset_delivered_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("ruito_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ruito_asset_delivered' must be Double: '"+value+"' is not." );
                    this.ruito_asset_delivered = (Double) value;
                    if (this.ruito_asset_delivered_is_set)
                        this.ruito_asset_delivered_is_modified = true;
                    this.ruito_asset_delivered_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tp_asset_history_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'tp_asset_history_id' must be Long: '"+value+"' is not." );
                    this.tp_asset_history_id = ((Long) value).longValue();
                    if (this.tp_asset_history_id_is_set)
                        this.tp_asset_history_id_is_modified = true;
                    this.tp_asset_history_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
