head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCashBalanceFrgnParams.java;


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
 * tp_cash_balance_frgn�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link TpCashBalanceFrgnParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link TpCashBalanceFrgnParams}��{@@link TpCashBalanceFrgnRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCashBalanceFrgnPK 
 * @@see TpCashBalanceFrgnRow 
 */
public class TpCashBalanceFrgnParams extends Params implements TpCashBalanceFrgnRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "tp_cash_balance_frgn";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = TpCashBalanceFrgnRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return TpCashBalanceFrgnRow.TYPE;
  }


  /** 
   * <em>tp_cash_balance_frgn_id</em>�J�����̒l 
   */
  public  long  tp_cash_balance_frgn_id;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>�J�����̒l 
   */
  public  long  sub_account_id;

  /** 
   * <em>currency_code</em>�J�����̒l 
   */
  public  String  currency_code;

  /** 
   * <em>cash_balance_frgn0</em>�J�����̒l 
   */
  public  double  cash_balance_frgn0;

  /** 
   * <em>cash_balance_frgn1</em>�J�����̒l 
   */
  public  double  cash_balance_frgn1;

  /** 
   * <em>cash_balance_frgn2</em>�J�����̒l 
   */
  public  double  cash_balance_frgn2;

  /** 
   * <em>cash_balance_frgn3</em>�J�����̒l 
   */
  public  double  cash_balance_frgn3;

  /** 
   * <em>cash_balance_frgn4</em>�J�����̒l 
   */
  public  double  cash_balance_frgn4;

  /** 
   * <em>cash_balance_frgn5</em>�J�����̒l 
   */
  public  double  cash_balance_frgn5;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean tp_cash_balance_frgn_id_is_set = false;
  private boolean tp_cash_balance_frgn_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean cash_balance_frgn0_is_set = false;
  private boolean cash_balance_frgn0_is_modified = false;
  private boolean cash_balance_frgn1_is_set = false;
  private boolean cash_balance_frgn1_is_modified = false;
  private boolean cash_balance_frgn2_is_set = false;
  private boolean cash_balance_frgn2_is_modified = false;
  private boolean cash_balance_frgn3_is_set = false;
  private boolean cash_balance_frgn3_is_modified = false;
  private boolean cash_balance_frgn4_is_set = false;
  private boolean cash_balance_frgn4_is_modified = false;
  private boolean cash_balance_frgn5_is_set = false;
  private boolean cash_balance_frgn5_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "tp_cash_balance_frgn_id=" + tp_cash_balance_frgn_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "currency_code=" +currency_code
      + "," + "cash_balance_frgn0=" +cash_balance_frgn0
      + "," + "cash_balance_frgn1=" +cash_balance_frgn1
      + "," + "cash_balance_frgn2=" +cash_balance_frgn2
      + "," + "cash_balance_frgn3=" +cash_balance_frgn3
      + "," + "cash_balance_frgn4=" +cash_balance_frgn4
      + "," + "cash_balance_frgn5=" +cash_balance_frgn5
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��TpCashBalanceFrgnParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public TpCashBalanceFrgnParams() {
    tp_cash_balance_frgn_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���TpCashBalanceFrgnRow�I�u�W�F�N�g�̒l�𗘗p����TpCashBalanceFrgnParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����TpCashBalanceFrgnRow�I�u�W�F�N�g 
   */
  public TpCashBalanceFrgnParams( TpCashBalanceFrgnRow p_row )
  {
    tp_cash_balance_frgn_id = p_row.getTpCashBalanceFrgnId();
    tp_cash_balance_frgn_id_is_set = p_row.getTpCashBalanceFrgnIdIsSet();
    tp_cash_balance_frgn_id_is_modified = p_row.getTpCashBalanceFrgnIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    cash_balance_frgn0 = p_row.getCashBalanceFrgn0();
    cash_balance_frgn0_is_set = p_row.getCashBalanceFrgn0IsSet();
    cash_balance_frgn0_is_modified = p_row.getCashBalanceFrgn0IsModified();
    cash_balance_frgn1 = p_row.getCashBalanceFrgn1();
    cash_balance_frgn1_is_set = p_row.getCashBalanceFrgn1IsSet();
    cash_balance_frgn1_is_modified = p_row.getCashBalanceFrgn1IsModified();
    cash_balance_frgn2 = p_row.getCashBalanceFrgn2();
    cash_balance_frgn2_is_set = p_row.getCashBalanceFrgn2IsSet();
    cash_balance_frgn2_is_modified = p_row.getCashBalanceFrgn2IsModified();
    cash_balance_frgn3 = p_row.getCashBalanceFrgn3();
    cash_balance_frgn3_is_set = p_row.getCashBalanceFrgn3IsSet();
    cash_balance_frgn3_is_modified = p_row.getCashBalanceFrgn3IsModified();
    cash_balance_frgn4 = p_row.getCashBalanceFrgn4();
    cash_balance_frgn4_is_set = p_row.getCashBalanceFrgn4IsSet();
    cash_balance_frgn4_is_modified = p_row.getCashBalanceFrgn4IsModified();
    cash_balance_frgn5 = p_row.getCashBalanceFrgn5();
    cash_balance_frgn5_is_set = p_row.getCashBalanceFrgn5IsSet();
    cash_balance_frgn5_is_modified = p_row.getCashBalanceFrgn5IsModified();
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
      currency_code_is_set = true;
      currency_code_is_modified = true;
      cash_balance_frgn0_is_set = true;
      cash_balance_frgn0_is_modified = true;
      cash_balance_frgn1_is_set = true;
      cash_balance_frgn1_is_modified = true;
      cash_balance_frgn2_is_set = true;
      cash_balance_frgn2_is_modified = true;
      cash_balance_frgn3_is_set = true;
      cash_balance_frgn3_is_modified = true;
      cash_balance_frgn4_is_set = true;
      cash_balance_frgn4_is_modified = true;
      cash_balance_frgn5_is_set = true;
      cash_balance_frgn5_is_modified = true;
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
    if ( !( other instanceof TpCashBalanceFrgnRow ) )
       return false;
    return fieldsEqual( (TpCashBalanceFrgnRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�TpCashBalanceFrgnRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( TpCashBalanceFrgnRow row )
  {
    if ( tp_cash_balance_frgn_id != row.getTpCashBalanceFrgnId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( cash_balance_frgn0 != row.getCashBalanceFrgn0() )
      return false;
    if ( cash_balance_frgn1 != row.getCashBalanceFrgn1() )
      return false;
    if ( cash_balance_frgn2 != row.getCashBalanceFrgn2() )
      return false;
    if ( cash_balance_frgn3 != row.getCashBalanceFrgn3() )
      return false;
    if ( cash_balance_frgn4 != row.getCashBalanceFrgn4() )
      return false;
    if ( cash_balance_frgn5 != row.getCashBalanceFrgn5() )
      return false;
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
      return  ((int) tp_cash_balance_frgn_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + ((int) cash_balance_frgn0)
        + ((int) cash_balance_frgn1)
        + ((int) cash_balance_frgn2)
        + ((int) cash_balance_frgn3)
        + ((int) cash_balance_frgn4)
        + ((int) cash_balance_frgn5)
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
		if ( !currency_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'currency_code' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("tp_cash_balance_frgn_id",new Long(tp_cash_balance_frgn_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("currency_code",currency_code);
		if ( cash_balance_frgn0_is_set )
			map.put("cash_balance_frgn0",new Double(cash_balance_frgn0));
		if ( cash_balance_frgn1_is_set )
			map.put("cash_balance_frgn1",new Double(cash_balance_frgn1));
		if ( cash_balance_frgn2_is_set )
			map.put("cash_balance_frgn2",new Double(cash_balance_frgn2));
		if ( cash_balance_frgn3_is_set )
			map.put("cash_balance_frgn3",new Double(cash_balance_frgn3));
		if ( cash_balance_frgn4_is_set )
			map.put("cash_balance_frgn4",new Double(cash_balance_frgn4));
		if ( cash_balance_frgn5_is_set )
			map.put("cash_balance_frgn5",new Double(cash_balance_frgn5));
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
		if ( currency_code_is_modified )
			map.put("currency_code",currency_code);
		if ( cash_balance_frgn0_is_modified )
			map.put("cash_balance_frgn0",new Double(cash_balance_frgn0));
		if ( cash_balance_frgn1_is_modified )
			map.put("cash_balance_frgn1",new Double(cash_balance_frgn1));
		if ( cash_balance_frgn2_is_modified )
			map.put("cash_balance_frgn2",new Double(cash_balance_frgn2));
		if ( cash_balance_frgn3_is_modified )
			map.put("cash_balance_frgn3",new Double(cash_balance_frgn3));
		if ( cash_balance_frgn4_is_modified )
			map.put("cash_balance_frgn4",new Double(cash_balance_frgn4));
		if ( cash_balance_frgn5_is_modified )
			map.put("cash_balance_frgn5",new Double(cash_balance_frgn5));
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( currency_code_is_set )
				map.put("currency_code",currency_code);
			if ( cash_balance_frgn0_is_set )
				map.put("cash_balance_frgn0",new Double(cash_balance_frgn0));
			if ( cash_balance_frgn1_is_set )
				map.put("cash_balance_frgn1",new Double(cash_balance_frgn1));
			if ( cash_balance_frgn2_is_set )
				map.put("cash_balance_frgn2",new Double(cash_balance_frgn2));
			if ( cash_balance_frgn3_is_set )
				map.put("cash_balance_frgn3",new Double(cash_balance_frgn3));
			if ( cash_balance_frgn4_is_set )
				map.put("cash_balance_frgn4",new Double(cash_balance_frgn4));
			if ( cash_balance_frgn5_is_set )
				map.put("cash_balance_frgn5",new Double(cash_balance_frgn5));
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>tp_cash_balance_frgn_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getTpCashBalanceFrgnId()
  {
    return tp_cash_balance_frgn_id;
  }


  /** 
   * <em>tp_cash_balance_frgn_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTpCashBalanceFrgnIdIsSet() {
    return tp_cash_balance_frgn_id_is_set;
  }


  /** 
   * <em>tp_cash_balance_frgn_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTpCashBalanceFrgnIdIsModified() {
    return tp_cash_balance_frgn_id_is_modified;
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
   * <em>currency_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCurrencyCode()
  {
    return currency_code;
  }


  /** 
   * <em>currency_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrencyCodeIsSet() {
    return currency_code_is_set;
  }


  /** 
   * <em>currency_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrencyCodeIsModified() {
    return currency_code_is_modified;
  }


  /** 
   * <em>cash_balance_frgn0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCashBalanceFrgn0()
  {
    return cash_balance_frgn0;
  }


  /** 
   * <em>cash_balance_frgn0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalanceFrgn0IsSet() {
    return cash_balance_frgn0_is_set;
  }


  /** 
   * <em>cash_balance_frgn0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalanceFrgn0IsModified() {
    return cash_balance_frgn0_is_modified;
  }


  /** 
   * <em>cash_balance_frgn1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCashBalanceFrgn1()
  {
    return cash_balance_frgn1;
  }


  /** 
   * <em>cash_balance_frgn1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalanceFrgn1IsSet() {
    return cash_balance_frgn1_is_set;
  }


  /** 
   * <em>cash_balance_frgn1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalanceFrgn1IsModified() {
    return cash_balance_frgn1_is_modified;
  }


  /** 
   * <em>cash_balance_frgn2</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCashBalanceFrgn2()
  {
    return cash_balance_frgn2;
  }


  /** 
   * <em>cash_balance_frgn2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalanceFrgn2IsSet() {
    return cash_balance_frgn2_is_set;
  }


  /** 
   * <em>cash_balance_frgn2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalanceFrgn2IsModified() {
    return cash_balance_frgn2_is_modified;
  }


  /** 
   * <em>cash_balance_frgn3</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCashBalanceFrgn3()
  {
    return cash_balance_frgn3;
  }


  /** 
   * <em>cash_balance_frgn3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalanceFrgn3IsSet() {
    return cash_balance_frgn3_is_set;
  }


  /** 
   * <em>cash_balance_frgn3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalanceFrgn3IsModified() {
    return cash_balance_frgn3_is_modified;
  }


  /** 
   * <em>cash_balance_frgn4</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCashBalanceFrgn4()
  {
    return cash_balance_frgn4;
  }


  /** 
   * <em>cash_balance_frgn4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalanceFrgn4IsSet() {
    return cash_balance_frgn4_is_set;
  }


  /** 
   * <em>cash_balance_frgn4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalanceFrgn4IsModified() {
    return cash_balance_frgn4_is_modified;
  }


  /** 
   * <em>cash_balance_frgn5</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCashBalanceFrgn5()
  {
    return cash_balance_frgn5;
  }


  /** 
   * <em>cash_balance_frgn5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalanceFrgn5IsSet() {
    return cash_balance_frgn5_is_set;
  }


  /** 
   * <em>cash_balance_frgn5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashBalanceFrgn5IsModified() {
    return cash_balance_frgn5_is_modified;
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
    return new TpCashBalanceFrgnPK(tp_cash_balance_frgn_id);
  }


  /** 
   * <em>tp_cash_balance_frgn_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tpCashBalanceFrgnId <em>tp_cash_balance_frgn_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setTpCashBalanceFrgnId( long p_tpCashBalanceFrgnId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tp_cash_balance_frgn_id = p_tpCashBalanceFrgnId;
    tp_cash_balance_frgn_id_is_set = true;
    tp_cash_balance_frgn_id_is_modified = true;
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
   * <em>currency_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_currencyCode <em>currency_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>cash_balance_frgn0</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashBalanceFrgn0 <em>cash_balance_frgn0</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCashBalanceFrgn0( double p_cashBalanceFrgn0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance_frgn0 = p_cashBalanceFrgn0;
    cash_balance_frgn0_is_set = true;
    cash_balance_frgn0_is_modified = true;
  }


  /** 
   * <em>cash_balance_frgn1</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashBalanceFrgn1 <em>cash_balance_frgn1</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCashBalanceFrgn1( double p_cashBalanceFrgn1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance_frgn1 = p_cashBalanceFrgn1;
    cash_balance_frgn1_is_set = true;
    cash_balance_frgn1_is_modified = true;
  }


  /** 
   * <em>cash_balance_frgn2</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashBalanceFrgn2 <em>cash_balance_frgn2</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCashBalanceFrgn2( double p_cashBalanceFrgn2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance_frgn2 = p_cashBalanceFrgn2;
    cash_balance_frgn2_is_set = true;
    cash_balance_frgn2_is_modified = true;
  }


  /** 
   * <em>cash_balance_frgn3</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashBalanceFrgn3 <em>cash_balance_frgn3</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCashBalanceFrgn3( double p_cashBalanceFrgn3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance_frgn3 = p_cashBalanceFrgn3;
    cash_balance_frgn3_is_set = true;
    cash_balance_frgn3_is_modified = true;
  }


  /** 
   * <em>cash_balance_frgn4</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashBalanceFrgn4 <em>cash_balance_frgn4</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCashBalanceFrgn4( double p_cashBalanceFrgn4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance_frgn4 = p_cashBalanceFrgn4;
    cash_balance_frgn4_is_set = true;
    cash_balance_frgn4_is_modified = true;
  }


  /** 
   * <em>cash_balance_frgn5</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashBalanceFrgn5 <em>cash_balance_frgn5</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCashBalanceFrgn5( double p_cashBalanceFrgn5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance_frgn5 = p_cashBalanceFrgn5;
    cash_balance_frgn5_is_set = true;
    cash_balance_frgn5_is_modified = true;
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
                if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("cash_balance_frgn0") ) {
                    return new Double( cash_balance_frgn0 );
                }
                else if ( name.equals("cash_balance_frgn1") ) {
                    return new Double( cash_balance_frgn1 );
                }
                else if ( name.equals("cash_balance_frgn2") ) {
                    return new Double( cash_balance_frgn2 );
                }
                else if ( name.equals("cash_balance_frgn3") ) {
                    return new Double( cash_balance_frgn3 );
                }
                else if ( name.equals("cash_balance_frgn4") ) {
                    return new Double( cash_balance_frgn4 );
                }
                else if ( name.equals("cash_balance_frgn5") ) {
                    return new Double( cash_balance_frgn5 );
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
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                break;
            case 't':
                if ( name.equals("tp_cash_balance_frgn_id") ) {
                    return new Long( tp_cash_balance_frgn_id );
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
                if ( name.equals("currency_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_code' must be String: '"+value+"' is not." );
                    this.currency_code = (String) value;
                    if (this.currency_code_is_set)
                        this.currency_code_is_modified = true;
                    this.currency_code_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance_frgn0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance_frgn0' must be Double: '"+value+"' is not." );
                    this.cash_balance_frgn0 = ((Double) value).doubleValue();
                    if (this.cash_balance_frgn0_is_set)
                        this.cash_balance_frgn0_is_modified = true;
                    this.cash_balance_frgn0_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance_frgn1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance_frgn1' must be Double: '"+value+"' is not." );
                    this.cash_balance_frgn1 = ((Double) value).doubleValue();
                    if (this.cash_balance_frgn1_is_set)
                        this.cash_balance_frgn1_is_modified = true;
                    this.cash_balance_frgn1_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance_frgn2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance_frgn2' must be Double: '"+value+"' is not." );
                    this.cash_balance_frgn2 = ((Double) value).doubleValue();
                    if (this.cash_balance_frgn2_is_set)
                        this.cash_balance_frgn2_is_modified = true;
                    this.cash_balance_frgn2_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance_frgn3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance_frgn3' must be Double: '"+value+"' is not." );
                    this.cash_balance_frgn3 = ((Double) value).doubleValue();
                    if (this.cash_balance_frgn3_is_set)
                        this.cash_balance_frgn3_is_modified = true;
                    this.cash_balance_frgn3_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance_frgn4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance_frgn4' must be Double: '"+value+"' is not." );
                    this.cash_balance_frgn4 = ((Double) value).doubleValue();
                    if (this.cash_balance_frgn4_is_set)
                        this.cash_balance_frgn4_is_modified = true;
                    this.cash_balance_frgn4_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance_frgn5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance_frgn5' must be Double: '"+value+"' is not." );
                    this.cash_balance_frgn5 = ((Double) value).doubleValue();
                    if (this.cash_balance_frgn5_is_set)
                        this.cash_balance_frgn5_is_modified = true;
                    this.cash_balance_frgn5_is_set = true;
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
                if ( name.equals("tp_cash_balance_frgn_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'tp_cash_balance_frgn_id' must be Long: '"+value+"' is not." );
                    this.tp_cash_balance_frgn_id = ((Long) value).longValue();
                    if (this.tp_cash_balance_frgn_id_is_set)
                        this.tp_cash_balance_frgn_id_is_modified = true;
                    this.tp_cash_balance_frgn_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
