head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCashBalanceParams.java;


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
 * tp_cash_balance�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link TpCashBalanceRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link TpCashBalanceParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link TpCashBalanceParams}��{@@link TpCashBalanceRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCashBalancePK 
 * @@see TpCashBalanceRow 
 */
public class TpCashBalanceParams extends Params implements TpCashBalanceRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "tp_cash_balance";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = TpCashBalanceRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return TpCashBalanceRow.TYPE;
  }


  /** 
   * <em>tp_cash_balance_id</em>�J�����̒l 
   */
  public  long  tp_cash_balance_id;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>�J�����̒l 
   */
  public  long  sub_account_id;

  /** 
   * <em>cash_balance0</em>�J�����̒l 
   */
  public  double  cash_balance0;

  /** 
   * <em>cash_balance1</em>�J�����̒l 
   */
  public  double  cash_balance1;

  /** 
   * <em>cash_balance2</em>�J�����̒l 
   */
  public  double  cash_balance2;

  /** 
   * <em>cash_balance3</em>�J�����̒l 
   */
  public  double  cash_balance3;

  /** 
   * <em>cash_balance4</em>�J�����̒l 
   */
  public  double  cash_balance4;

  /** 
   * <em>cash_balance5</em>�J�����̒l 
   */
  public  double  cash_balance5;

  /** 
   * <em>mrf_balance</em>�J�����̒l 
   */
  public  double  mrf_balance;

  /** 
   * <em>current_term_capital_gain</em>�J�����̒l 
   */
  public  Double  current_term_capital_gain;

  /** 
   * <em>next_month_capital_gain</em>�J�����̒l 
   */
  public  Double  next_month_capital_gain;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean tp_cash_balance_id_is_set = false;
  private boolean tp_cash_balance_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean cash_balance0_is_set = false;
  private boolean cash_balance0_is_modified = false;
  private boolean cash_balance1_is_set = false;
  private boolean cash_balance1_is_modified = false;
  private boolean cash_balance2_is_set = false;
  private boolean cash_balance2_is_modified = false;
  private boolean cash_balance3_is_set = false;
  private boolean cash_balance3_is_modified = false;
  private boolean cash_balance4_is_set = false;
  private boolean cash_balance4_is_modified = false;
  private boolean cash_balance5_is_set = false;
  private boolean cash_balance5_is_modified = false;
  private boolean mrf_balance_is_set = false;
  private boolean mrf_balance_is_modified = false;
  private boolean current_term_capital_gain_is_set = false;
  private boolean current_term_capital_gain_is_modified = false;
  private boolean next_month_capital_gain_is_set = false;
  private boolean next_month_capital_gain_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "tp_cash_balance_id=" + tp_cash_balance_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "cash_balance0=" +cash_balance0
      + "," + "cash_balance1=" +cash_balance1
      + "," + "cash_balance2=" +cash_balance2
      + "," + "cash_balance3=" +cash_balance3
      + "," + "cash_balance4=" +cash_balance4
      + "," + "cash_balance5=" +cash_balance5
      + "," + "mrf_balance=" +mrf_balance
      + "," + "current_term_capital_gain=" +current_term_capital_gain
      + "," + "next_month_capital_gain=" +next_month_capital_gain
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��TpCashBalanceParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public TpCashBalanceParams() {
    tp_cash_balance_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���TpCashBalanceRow�I�u�W�F�N�g�̒l�𗘗p����TpCashBalanceParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����TpCashBalanceRow�I�u�W�F�N�g 
   */
  public TpCashBalanceParams( TpCashBalanceRow p_row )
  {
    tp_cash_balance_id = p_row.getTpCashBalanceId();
    tp_cash_balance_id_is_set = p_row.getTpCashBalanceIdIsSet();
    tp_cash_balance_id_is_modified = p_row.getTpCashBalanceIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    cash_balance0 = p_row.getCashBalance0();
    cash_balance0_is_set = p_row.getCashBalance0IsSet();
    cash_balance0_is_modified = p_row.getCashBalance0IsModified();
    cash_balance1 = p_row.getCashBalance1();
    cash_balance1_is_set = p_row.getCashBalance1IsSet();
    cash_balance1_is_modified = p_row.getCashBalance1IsModified();
    cash_balance2 = p_row.getCashBalance2();
    cash_balance2_is_set = p_row.getCashBalance2IsSet();
    cash_balance2_is_modified = p_row.getCashBalance2IsModified();
    cash_balance3 = p_row.getCashBalance3();
    cash_balance3_is_set = p_row.getCashBalance3IsSet();
    cash_balance3_is_modified = p_row.getCashBalance3IsModified();
    cash_balance4 = p_row.getCashBalance4();
    cash_balance4_is_set = p_row.getCashBalance4IsSet();
    cash_balance4_is_modified = p_row.getCashBalance4IsModified();
    cash_balance5 = p_row.getCashBalance5();
    cash_balance5_is_set = p_row.getCashBalance5IsSet();
    cash_balance5_is_modified = p_row.getCashBalance5IsModified();
    mrf_balance = p_row.getMrfBalance();
    mrf_balance_is_set = p_row.getMrfBalanceIsSet();
    mrf_balance_is_modified = p_row.getMrfBalanceIsModified();
    if ( !p_row.getCurrentTermCapitalGainIsNull() )
      current_term_capital_gain = new Double( p_row.getCurrentTermCapitalGain() );
    current_term_capital_gain_is_set = p_row.getCurrentTermCapitalGainIsSet();
    current_term_capital_gain_is_modified = p_row.getCurrentTermCapitalGainIsModified();
    if ( !p_row.getNextMonthCapitalGainIsNull() )
      next_month_capital_gain = new Double( p_row.getNextMonthCapitalGain() );
    next_month_capital_gain_is_set = p_row.getNextMonthCapitalGainIsSet();
    next_month_capital_gain_is_modified = p_row.getNextMonthCapitalGainIsModified();
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
      sub_account_id_is_set = true;
      sub_account_id_is_modified = true;
      cash_balance0_is_set = true;
      cash_balance0_is_modified = true;
      cash_balance1_is_set = true;
      cash_balance1_is_modified = true;
      cash_balance2_is_set = true;
      cash_balance2_is_modified = true;
      cash_balance3_is_set = true;
      cash_balance3_is_modified = true;
      cash_balance4_is_set = true;
      cash_balance4_is_modified = true;
      cash_balance5_is_set = true;
      cash_balance5_is_modified = true;
      mrf_balance_is_set = true;
      mrf_balance_is_modified = true;
      current_term_capital_gain_is_set = true;
      current_term_capital_gain_is_modified = true;
      next_month_capital_gain_is_set = true;
      next_month_capital_gain_is_modified = true;
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
    if ( !( other instanceof TpCashBalanceRow ) )
       return false;
    return fieldsEqual( (TpCashBalanceRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�TpCashBalanceRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( TpCashBalanceRow row )
  {
    if ( tp_cash_balance_id != row.getTpCashBalanceId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( cash_balance0 != row.getCashBalance0() )
      return false;
    if ( cash_balance1 != row.getCashBalance1() )
      return false;
    if ( cash_balance2 != row.getCashBalance2() )
      return false;
    if ( cash_balance3 != row.getCashBalance3() )
      return false;
    if ( cash_balance4 != row.getCashBalance4() )
      return false;
    if ( cash_balance5 != row.getCashBalance5() )
      return false;
    if ( mrf_balance != row.getMrfBalance() )
      return false;
    if ( current_term_capital_gain == null ) {
      if ( !row.getCurrentTermCapitalGainIsNull() )
        return false;
    } else if ( row.getCurrentTermCapitalGainIsNull() || ( current_term_capital_gain.doubleValue() != row.getCurrentTermCapitalGain() ) ) {
        return false;
    }
    if ( next_month_capital_gain == null ) {
      if ( !row.getNextMonthCapitalGainIsNull() )
        return false;
    } else if ( row.getNextMonthCapitalGainIsNull() || ( next_month_capital_gain.doubleValue() != row.getNextMonthCapitalGain() ) ) {
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
      return  ((int) tp_cash_balance_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) cash_balance0)
        + ((int) cash_balance1)
        + ((int) cash_balance2)
        + ((int) cash_balance3)
        + ((int) cash_balance4)
        + ((int) cash_balance5)
        + ((int) mrf_balance)
        + (current_term_capital_gain!=null? current_term_capital_gain.hashCode(): 0) 
        + (next_month_capital_gain!=null? next_month_capital_gain.hashCode(): 0) 
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
		if ( !sub_account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'sub_account_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("tp_cash_balance_id",new Long(tp_cash_balance_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		if ( cash_balance0_is_set )
			map.put("cash_balance0",new Double(cash_balance0));
		if ( cash_balance1_is_set )
			map.put("cash_balance1",new Double(cash_balance1));
		if ( cash_balance2_is_set )
			map.put("cash_balance2",new Double(cash_balance2));
		if ( cash_balance3_is_set )
			map.put("cash_balance3",new Double(cash_balance3));
		if ( cash_balance4_is_set )
			map.put("cash_balance4",new Double(cash_balance4));
		if ( cash_balance5_is_set )
			map.put("cash_balance5",new Double(cash_balance5));
		if ( mrf_balance_is_set )
			map.put("mrf_balance",new Double(mrf_balance));
		if ( current_term_capital_gain_is_set )
			map.put("current_term_capital_gain",current_term_capital_gain);
		if ( next_month_capital_gain_is_set )
			map.put("next_month_capital_gain",next_month_capital_gain);
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
		if ( sub_account_id_is_modified )
			map.put("sub_account_id",new Long(sub_account_id));
		if ( cash_balance0_is_modified )
			map.put("cash_balance0",new Double(cash_balance0));
		if ( cash_balance1_is_modified )
			map.put("cash_balance1",new Double(cash_balance1));
		if ( cash_balance2_is_modified )
			map.put("cash_balance2",new Double(cash_balance2));
		if ( cash_balance3_is_modified )
			map.put("cash_balance3",new Double(cash_balance3));
		if ( cash_balance4_is_modified )
			map.put("cash_balance4",new Double(cash_balance4));
		if ( cash_balance5_is_modified )
			map.put("cash_balance5",new Double(cash_balance5));
		if ( mrf_balance_is_modified )
			map.put("mrf_balance",new Double(mrf_balance));
		if ( current_term_capital_gain_is_modified )
			map.put("current_term_capital_gain",current_term_capital_gain);
		if ( next_month_capital_gain_is_modified )
			map.put("next_month_capital_gain",next_month_capital_gain);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( cash_balance0_is_set )
				map.put("cash_balance0",new Double(cash_balance0));
			if ( cash_balance1_is_set )
				map.put("cash_balance1",new Double(cash_balance1));
			if ( cash_balance2_is_set )
				map.put("cash_balance2",new Double(cash_balance2));
			if ( cash_balance3_is_set )
				map.put("cash_balance3",new Double(cash_balance3));
			if ( cash_balance4_is_set )
				map.put("cash_balance4",new Double(cash_balance4));
			if ( cash_balance5_is_set )
				map.put("cash_balance5",new Double(cash_balance5));
			if ( mrf_balance_is_set )
				map.put("mrf_balance",new Double(mrf_balance));
			if ( current_term_capital_gain_is_set )
				map.put("current_term_capital_gain",current_term_capital_gain);
			if ( next_month_capital_gain_is_set )
				map.put("next_month_capital_gain",next_month_capital_gain);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>tp_cash_balance_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getTpCashBalanceId()
  {
    return tp_cash_balance_id;
  }


  /** 
   * <em>tp_cash_balance_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTpCashBalanceIdIsSet() {
    return tp_cash_balance_id_is_set;
  }


  /** 
   * <em>tp_cash_balance_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTpCashBalanceIdIsModified() {
    return tp_cash_balance_id_is_modified;
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
   * <em>sub_account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSubAccountId()
  {
    return sub_account_id;
  }


  /** 
   * <em>sub_account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubAccountIdIsSet() {
    return sub_account_id_is_set;
  }


  /** 
   * <em>sub_account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubAccountIdIsModified() {
    return sub_account_id_is_modified;
  }


  /** 
   * <em>cash_balance0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCashBalance0()
  {
    return cash_balance0;
  }


  /** 
   * <em>cash_balance0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalance0IsSet() {
    return cash_balance0_is_set;
  }


  /** 
   * <em>cash_balance0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalance0IsModified() {
    return cash_balance0_is_modified;
  }


  /** 
   * <em>cash_balance1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCashBalance1()
  {
    return cash_balance1;
  }


  /** 
   * <em>cash_balance1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalance1IsSet() {
    return cash_balance1_is_set;
  }


  /** 
   * <em>cash_balance1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalance1IsModified() {
    return cash_balance1_is_modified;
  }


  /** 
   * <em>cash_balance2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCashBalance2()
  {
    return cash_balance2;
  }


  /** 
   * <em>cash_balance2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalance2IsSet() {
    return cash_balance2_is_set;
  }


  /** 
   * <em>cash_balance2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalance2IsModified() {
    return cash_balance2_is_modified;
  }


  /** 
   * <em>cash_balance3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCashBalance3()
  {
    return cash_balance3;
  }


  /** 
   * <em>cash_balance3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalance3IsSet() {
    return cash_balance3_is_set;
  }


  /** 
   * <em>cash_balance3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalance3IsModified() {
    return cash_balance3_is_modified;
  }


  /** 
   * <em>cash_balance4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCashBalance4()
  {
    return cash_balance4;
  }


  /** 
   * <em>cash_balance4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalance4IsSet() {
    return cash_balance4_is_set;
  }


  /** 
   * <em>cash_balance4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalance4IsModified() {
    return cash_balance4_is_modified;
  }


  /** 
   * <em>cash_balance5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCashBalance5()
  {
    return cash_balance5;
  }


  /** 
   * <em>cash_balance5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalance5IsSet() {
    return cash_balance5_is_set;
  }


  /** 
   * <em>cash_balance5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalance5IsModified() {
    return cash_balance5_is_modified;
  }


  /** 
   * <em>mrf_balance</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getMrfBalance()
  {
    return mrf_balance;
  }


  /** 
   * <em>mrf_balance</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMrfBalanceIsSet() {
    return mrf_balance_is_set;
  }


  /** 
   * <em>mrf_balance</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMrfBalanceIsModified() {
    return mrf_balance_is_modified;
  }


  /** 
   * <em>current_term_capital_gain</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCurrentTermCapitalGain()
  {
    return ( current_term_capital_gain==null? ((double)0): current_term_capital_gain.doubleValue() );
  }


  /** 
   * <em>current_term_capital_gain</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCurrentTermCapitalGainIsNull()
  {
    return current_term_capital_gain == null;
  }


  /** 
   * <em>current_term_capital_gain</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrentTermCapitalGainIsSet() {
    return current_term_capital_gain_is_set;
  }


  /** 
   * <em>current_term_capital_gain</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrentTermCapitalGainIsModified() {
    return current_term_capital_gain_is_modified;
  }


  /** 
   * <em>next_month_capital_gain</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getNextMonthCapitalGain()
  {
    return ( next_month_capital_gain==null? ((double)0): next_month_capital_gain.doubleValue() );
  }


  /** 
   * <em>next_month_capital_gain</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getNextMonthCapitalGainIsNull()
  {
    return next_month_capital_gain == null;
  }


  /** 
   * <em>next_month_capital_gain</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNextMonthCapitalGainIsSet() {
    return next_month_capital_gain_is_set;
  }


  /** 
   * <em>next_month_capital_gain</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNextMonthCapitalGainIsModified() {
    return next_month_capital_gain_is_modified;
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
    return new TpCashBalancePK(tp_cash_balance_id);
  }


  /** 
   * <em>tp_cash_balance_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tpCashBalanceId <em>tp_cash_balance_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setTpCashBalanceId( long p_tpCashBalanceId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tp_cash_balance_id = p_tpCashBalanceId;
    tp_cash_balance_id_is_set = true;
    tp_cash_balance_id_is_modified = true;
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
   * <em>sub_account_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subAccountId <em>sub_account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setSubAccountId( long p_subAccountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_account_id = p_subAccountId;
    sub_account_id_is_set = true;
    sub_account_id_is_modified = true;
  }


  /** 
   * <em>cash_balance0</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashBalance0 <em>cash_balance0</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCashBalance0( double p_cashBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance0 = p_cashBalance0;
    cash_balance0_is_set = true;
    cash_balance0_is_modified = true;
  }


  /** 
   * <em>cash_balance1</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashBalance1 <em>cash_balance1</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCashBalance1( double p_cashBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance1 = p_cashBalance1;
    cash_balance1_is_set = true;
    cash_balance1_is_modified = true;
  }


  /** 
   * <em>cash_balance2</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashBalance2 <em>cash_balance2</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCashBalance2( double p_cashBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance2 = p_cashBalance2;
    cash_balance2_is_set = true;
    cash_balance2_is_modified = true;
  }


  /** 
   * <em>cash_balance3</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashBalance3 <em>cash_balance3</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCashBalance3( double p_cashBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance3 = p_cashBalance3;
    cash_balance3_is_set = true;
    cash_balance3_is_modified = true;
  }


  /** 
   * <em>cash_balance4</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashBalance4 <em>cash_balance4</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCashBalance4( double p_cashBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance4 = p_cashBalance4;
    cash_balance4_is_set = true;
    cash_balance4_is_modified = true;
  }


  /** 
   * <em>cash_balance5</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashBalance5 <em>cash_balance5</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCashBalance5( double p_cashBalance5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance5 = p_cashBalance5;
    cash_balance5_is_set = true;
    cash_balance5_is_modified = true;
  }


  /** 
   * <em>mrf_balance</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mrfBalance <em>mrf_balance</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setMrfBalance( double p_mrfBalance )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mrf_balance = p_mrfBalance;
    mrf_balance_is_set = true;
    mrf_balance_is_modified = true;
  }


  /** 
   * <em>current_term_capital_gain</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_currentTermCapitalGain <em>current_term_capital_gain</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCurrentTermCapitalGain( double p_currentTermCapitalGain )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_term_capital_gain = new Double(p_currentTermCapitalGain);
    current_term_capital_gain_is_set = true;
    current_term_capital_gain_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>current_term_capital_gain</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCurrentTermCapitalGain( Double p_currentTermCapitalGain )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    current_term_capital_gain = p_currentTermCapitalGain;
    current_term_capital_gain_is_set = true;
    current_term_capital_gain_is_modified = true;
  }


  /** 
   * <em>next_month_capital_gain</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_nextMonthCapitalGain <em>next_month_capital_gain</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setNextMonthCapitalGain( double p_nextMonthCapitalGain )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    next_month_capital_gain = new Double(p_nextMonthCapitalGain);
    next_month_capital_gain_is_set = true;
    next_month_capital_gain_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>next_month_capital_gain</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setNextMonthCapitalGain( Double p_nextMonthCapitalGain )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    next_month_capital_gain = p_nextMonthCapitalGain;
    next_month_capital_gain_is_set = true;
    next_month_capital_gain_is_modified = true;
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
                break;
            case 'c':
                if ( name.equals("cash_balance0") ) {
                    return new Double( cash_balance0 );
                }
                else if ( name.equals("cash_balance1") ) {
                    return new Double( cash_balance1 );
                }
                else if ( name.equals("cash_balance2") ) {
                    return new Double( cash_balance2 );
                }
                else if ( name.equals("cash_balance3") ) {
                    return new Double( cash_balance3 );
                }
                else if ( name.equals("cash_balance4") ) {
                    return new Double( cash_balance4 );
                }
                else if ( name.equals("cash_balance5") ) {
                    return new Double( cash_balance5 );
                }
                else if ( name.equals("current_term_capital_gain") ) {
                    return this.current_term_capital_gain;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("mrf_balance") ) {
                    return new Double( mrf_balance );
                }
                break;
            case 'n':
                if ( name.equals("next_month_capital_gain") ) {
                    return this.next_month_capital_gain;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                break;
            case 't':
                if ( name.equals("tp_cash_balance_id") ) {
                    return new Long( tp_cash_balance_id );
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
                break;
            case 'c':
                if ( name.equals("cash_balance0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance0' must be Double: '"+value+"' is not." );
                    this.cash_balance0 = ((Double) value).doubleValue();
                    if (this.cash_balance0_is_set)
                        this.cash_balance0_is_modified = true;
                    this.cash_balance0_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance1' must be Double: '"+value+"' is not." );
                    this.cash_balance1 = ((Double) value).doubleValue();
                    if (this.cash_balance1_is_set)
                        this.cash_balance1_is_modified = true;
                    this.cash_balance1_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance2' must be Double: '"+value+"' is not." );
                    this.cash_balance2 = ((Double) value).doubleValue();
                    if (this.cash_balance2_is_set)
                        this.cash_balance2_is_modified = true;
                    this.cash_balance2_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance3' must be Double: '"+value+"' is not." );
                    this.cash_balance3 = ((Double) value).doubleValue();
                    if (this.cash_balance3_is_set)
                        this.cash_balance3_is_modified = true;
                    this.cash_balance3_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance4' must be Double: '"+value+"' is not." );
                    this.cash_balance4 = ((Double) value).doubleValue();
                    if (this.cash_balance4_is_set)
                        this.cash_balance4_is_modified = true;
                    this.cash_balance4_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance5' must be Double: '"+value+"' is not." );
                    this.cash_balance5 = ((Double) value).doubleValue();
                    if (this.cash_balance5_is_set)
                        this.cash_balance5_is_modified = true;
                    this.cash_balance5_is_set = true;
                    return;
                }
                else if ( name.equals("current_term_capital_gain") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'current_term_capital_gain' must be Double: '"+value+"' is not." );
                    this.current_term_capital_gain = (Double) value;
                    if (this.current_term_capital_gain_is_set)
                        this.current_term_capital_gain_is_modified = true;
                    this.current_term_capital_gain_is_set = true;
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
                if ( name.equals("mrf_balance") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'mrf_balance' must be Double: '"+value+"' is not." );
                    this.mrf_balance = ((Double) value).doubleValue();
                    if (this.mrf_balance_is_set)
                        this.mrf_balance_is_modified = true;
                    this.mrf_balance_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("next_month_capital_gain") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'next_month_capital_gain' must be Double: '"+value+"' is not." );
                    this.next_month_capital_gain = (Double) value;
                    if (this.next_month_capital_gain_is_set)
                        this.next_month_capital_gain_is_modified = true;
                    this.next_month_capital_gain_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_account_id' must be Long: '"+value+"' is not." );
                    this.sub_account_id = ((Long) value).longValue();
                    if (this.sub_account_id_is_set)
                        this.sub_account_id_is_modified = true;
                    this.sub_account_id_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tp_cash_balance_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'tp_cash_balance_id' must be Long: '"+value+"' is not." );
                    this.tp_cash_balance_id = ((Long) value).longValue();
                    if (this.tp_cash_balance_id_is_set)
                        this.tp_cash_balance_id_is_modified = true;
                    this.tp_cash_balance_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
