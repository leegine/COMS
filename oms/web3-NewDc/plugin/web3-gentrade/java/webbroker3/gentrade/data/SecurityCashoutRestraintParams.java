head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.42.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SecurityCashoutRestraintParams.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * security_cashout_restraint�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link SecurityCashoutRestraintRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link SecurityCashoutRestraintParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link SecurityCashoutRestraintParams}��{@@link SecurityCashoutRestraintRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SecurityCashoutRestraintPK 
 * @@see SecurityCashoutRestraintRow 
 */
public class SecurityCashoutRestraintParams extends Params implements SecurityCashoutRestraintRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "security_cashout_restraint";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = SecurityCashoutRestraintRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return SecurityCashoutRestraintRow.TYPE;
  }


  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>�J�����̒l 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>�J�����̒l 
   */
  public  String  account_code;

  /** 
   * <em>use_enable_limit</em>�J�����̒l 
   */
  public  Long  use_enable_limit;

  /** 
   * <em>cashout_restraint</em>�J�����̒l 
   */
  public  Long  cashout_restraint;

  /** 
   * <em>cashout_enablie_amount</em>�J�����̒l 
   */
  public  Long  cashout_enablie_amount;

  /** 
   * <em>agree_cancel_flg</em>�J�����̒l 
   */
  public  String  agree_cancel_flg;

  /** 
   * <em>amount_lock_flg</em>�J�����̒l 
   */
  public  String  amount_lock_flg;

  /** 
   * <em>remark</em>�J�����̒l 
   */
  public  String  remark;

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

  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean use_enable_limit_is_set = false;
  private boolean use_enable_limit_is_modified = false;
  private boolean cashout_restraint_is_set = false;
  private boolean cashout_restraint_is_modified = false;
  private boolean cashout_enablie_amount_is_set = false;
  private boolean cashout_enablie_amount_is_modified = false;
  private boolean agree_cancel_flg_is_set = false;
  private boolean agree_cancel_flg_is_modified = false;
  private boolean amount_lock_flg_is_set = false;
  private boolean amount_lock_flg_is_modified = false;
  private boolean remark_is_set = false;
  private boolean remark_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "account_id=" + account_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "use_enable_limit=" +use_enable_limit
      + "," + "cashout_restraint=" +cashout_restraint
      + "," + "cashout_enablie_amount=" +cashout_enablie_amount
      + "," + "agree_cancel_flg=" +agree_cancel_flg
      + "," + "amount_lock_flg=" +amount_lock_flg
      + "," + "remark=" +remark
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��SecurityCashoutRestraintParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public SecurityCashoutRestraintParams() {
    account_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���SecurityCashoutRestraintRow�I�u�W�F�N�g�̒l�𗘗p����SecurityCashoutRestraintParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����SecurityCashoutRestraintRow�I�u�W�F�N�g 
   */
  public SecurityCashoutRestraintParams( SecurityCashoutRestraintRow p_row )
  {
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    if ( !p_row.getUseEnableLimitIsNull() )
      use_enable_limit = new Long( p_row.getUseEnableLimit() );
    use_enable_limit_is_set = p_row.getUseEnableLimitIsSet();
    use_enable_limit_is_modified = p_row.getUseEnableLimitIsModified();
    if ( !p_row.getCashoutRestraintIsNull() )
      cashout_restraint = new Long( p_row.getCashoutRestraint() );
    cashout_restraint_is_set = p_row.getCashoutRestraintIsSet();
    cashout_restraint_is_modified = p_row.getCashoutRestraintIsModified();
    if ( !p_row.getCashoutEnablieAmountIsNull() )
      cashout_enablie_amount = new Long( p_row.getCashoutEnablieAmount() );
    cashout_enablie_amount_is_set = p_row.getCashoutEnablieAmountIsSet();
    cashout_enablie_amount_is_modified = p_row.getCashoutEnablieAmountIsModified();
    agree_cancel_flg = p_row.getAgreeCancelFlg();
    agree_cancel_flg_is_set = p_row.getAgreeCancelFlgIsSet();
    agree_cancel_flg_is_modified = p_row.getAgreeCancelFlgIsModified();
    amount_lock_flg = p_row.getAmountLockFlg();
    amount_lock_flg_is_set = p_row.getAmountLockFlgIsSet();
    amount_lock_flg_is_modified = p_row.getAmountLockFlgIsModified();
    remark = p_row.getRemark();
    remark_is_set = p_row.getRemarkIsSet();
    remark_is_modified = p_row.getRemarkIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
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
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      use_enable_limit_is_set = true;
      use_enable_limit_is_modified = true;
      cashout_restraint_is_set = true;
      cashout_restraint_is_modified = true;
      cashout_enablie_amount_is_set = true;
      cashout_enablie_amount_is_modified = true;
      agree_cancel_flg_is_set = true;
      agree_cancel_flg_is_modified = true;
      amount_lock_flg_is_set = true;
      amount_lock_flg_is_modified = true;
      remark_is_set = true;
      remark_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
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
    if ( !( other instanceof SecurityCashoutRestraintRow ) )
       return false;
    return fieldsEqual( (SecurityCashoutRestraintRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�SecurityCashoutRestraintRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( SecurityCashoutRestraintRow row )
  {
    if ( account_id != row.getAccountId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( use_enable_limit == null ) {
      if ( !row.getUseEnableLimitIsNull() )
        return false;
    } else if ( row.getUseEnableLimitIsNull() || ( use_enable_limit.longValue() != row.getUseEnableLimit() ) ) {
        return false;
    }
    if ( cashout_restraint == null ) {
      if ( !row.getCashoutRestraintIsNull() )
        return false;
    } else if ( row.getCashoutRestraintIsNull() || ( cashout_restraint.longValue() != row.getCashoutRestraint() ) ) {
        return false;
    }
    if ( cashout_enablie_amount == null ) {
      if ( !row.getCashoutEnablieAmountIsNull() )
        return false;
    } else if ( row.getCashoutEnablieAmountIsNull() || ( cashout_enablie_amount.longValue() != row.getCashoutEnablieAmount() ) ) {
        return false;
    }
    if ( agree_cancel_flg == null ) {
      if ( row.getAgreeCancelFlg() != null )
        return false;
    } else if ( !agree_cancel_flg.equals( row.getAgreeCancelFlg() ) ) {
        return false;
    }
    if ( amount_lock_flg == null ) {
      if ( row.getAmountLockFlg() != null )
        return false;
    } else if ( !amount_lock_flg.equals( row.getAmountLockFlg() ) ) {
        return false;
    }
    if ( remark == null ) {
      if ( row.getRemark() != null )
        return false;
    } else if ( !remark.equals( row.getRemark() ) ) {
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
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) account_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (use_enable_limit!=null? use_enable_limit.hashCode(): 0) 
        + (cashout_restraint!=null? cashout_restraint.hashCode(): 0) 
        + (cashout_enablie_amount!=null? cashout_enablie_amount.hashCode(): 0) 
        + (agree_cancel_flg!=null? agree_cancel_flg.hashCode(): 0) 
        + (amount_lock_flg!=null? amount_lock_flg.hashCode(): 0) 
        + (remark!=null? remark.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("account_id",new Long(account_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( use_enable_limit != null )
			map.put("use_enable_limit",use_enable_limit);
		if ( cashout_restraint != null )
			map.put("cashout_restraint",cashout_restraint);
		if ( cashout_enablie_amount != null )
			map.put("cashout_enablie_amount",cashout_enablie_amount);
		if ( agree_cancel_flg != null )
			map.put("agree_cancel_flg",agree_cancel_flg);
		if ( amount_lock_flg != null )
			map.put("amount_lock_flg",amount_lock_flg);
		if ( remark != null )
			map.put("remark",remark);
		map.put("last_updater",last_updater);
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
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( use_enable_limit_is_modified )
			map.put("use_enable_limit",use_enable_limit);
		if ( cashout_restraint_is_modified )
			map.put("cashout_restraint",cashout_restraint);
		if ( cashout_enablie_amount_is_modified )
			map.put("cashout_enablie_amount",cashout_enablie_amount);
		if ( agree_cancel_flg_is_modified )
			map.put("agree_cancel_flg",agree_cancel_flg);
		if ( amount_lock_flg_is_modified )
			map.put("amount_lock_flg",amount_lock_flg);
		if ( remark_is_modified )
			map.put("remark",remark);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("use_enable_limit",use_enable_limit);
			map.put("cashout_restraint",cashout_restraint);
			map.put("cashout_enablie_amount",cashout_enablie_amount);
			map.put("agree_cancel_flg",agree_cancel_flg);
			map.put("amount_lock_flg",amount_lock_flg);
			map.put("remark",remark);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>branch_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
  }


  /** 
   * <em>account_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>account_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountCodeIsModified() {
    return account_code_is_modified;
  }


  /** 
   * <em>use_enable_limit</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getUseEnableLimit()
  {
    return ( use_enable_limit==null? ((long)0): use_enable_limit.longValue() );
  }


  /** 
   * <em>use_enable_limit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getUseEnableLimitIsNull()
  {
    return use_enable_limit == null;
  }


  /** 
   * <em>use_enable_limit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUseEnableLimitIsSet() {
    return use_enable_limit_is_set;
  }


  /** 
   * <em>use_enable_limit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUseEnableLimitIsModified() {
    return use_enable_limit_is_modified;
  }


  /** 
   * <em>cashout_restraint</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getCashoutRestraint()
  {
    return ( cashout_restraint==null? ((long)0): cashout_restraint.longValue() );
  }


  /** 
   * <em>cashout_restraint</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCashoutRestraintIsNull()
  {
    return cashout_restraint == null;
  }


  /** 
   * <em>cashout_restraint</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashoutRestraintIsSet() {
    return cashout_restraint_is_set;
  }


  /** 
   * <em>cashout_restraint</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashoutRestraintIsModified() {
    return cashout_restraint_is_modified;
  }


  /** 
   * <em>cashout_enablie_amount</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getCashoutEnablieAmount()
  {
    return ( cashout_enablie_amount==null? ((long)0): cashout_enablie_amount.longValue() );
  }


  /** 
   * <em>cashout_enablie_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCashoutEnablieAmountIsNull()
  {
    return cashout_enablie_amount == null;
  }


  /** 
   * <em>cashout_enablie_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashoutEnablieAmountIsSet() {
    return cashout_enablie_amount_is_set;
  }


  /** 
   * <em>cashout_enablie_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashoutEnablieAmountIsModified() {
    return cashout_enablie_amount_is_modified;
  }


  /** 
   * <em>agree_cancel_flg</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAgreeCancelFlg()
  {
    return agree_cancel_flg;
  }


  /** 
   * <em>agree_cancel_flg</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAgreeCancelFlgIsSet() {
    return agree_cancel_flg_is_set;
  }


  /** 
   * <em>agree_cancel_flg</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAgreeCancelFlgIsModified() {
    return agree_cancel_flg_is_modified;
  }


  /** 
   * <em>amount_lock_flg</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAmountLockFlg()
  {
    return amount_lock_flg;
  }


  /** 
   * <em>amount_lock_flg</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAmountLockFlgIsSet() {
    return amount_lock_flg_is_set;
  }


  /** 
   * <em>amount_lock_flg</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAmountLockFlgIsModified() {
    return amount_lock_flg_is_modified;
  }


  /** 
   * <em>remark</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRemark()
  {
    return remark;
  }


  /** 
   * <em>remark</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRemarkIsSet() {
    return remark_is_set;
  }


  /** 
   * <em>remark</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRemarkIsModified() {
    return remark_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new SecurityCashoutRestraintPK(account_id);
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
   * <em>institution_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>branch_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>account_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountCode <em>account_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>use_enable_limit</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_useEnableLimit <em>use_enable_limit</em>�J�����̒l������킷11���ȉ���long�l 
   */
  public final void setUseEnableLimit( long p_useEnableLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    use_enable_limit = new Long(p_useEnableLimit);
    use_enable_limit_is_set = true;
    use_enable_limit_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>use_enable_limit</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setUseEnableLimit( Long p_useEnableLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    use_enable_limit = p_useEnableLimit;
    use_enable_limit_is_set = true;
    use_enable_limit_is_modified = true;
  }


  /** 
   * <em>cashout_restraint</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashoutRestraint <em>cashout_restraint</em>�J�����̒l������킷11���ȉ���long�l 
   */
  public final void setCashoutRestraint( long p_cashoutRestraint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cashout_restraint = new Long(p_cashoutRestraint);
    cashout_restraint_is_set = true;
    cashout_restraint_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>cashout_restraint</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCashoutRestraint( Long p_cashoutRestraint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cashout_restraint = p_cashoutRestraint;
    cashout_restraint_is_set = true;
    cashout_restraint_is_modified = true;
  }


  /** 
   * <em>cashout_enablie_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashoutEnablieAmount <em>cashout_enablie_amount</em>�J�����̒l������킷11���ȉ���long�l 
   */
  public final void setCashoutEnablieAmount( long p_cashoutEnablieAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cashout_enablie_amount = new Long(p_cashoutEnablieAmount);
    cashout_enablie_amount_is_set = true;
    cashout_enablie_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>cashout_enablie_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCashoutEnablieAmount( Long p_cashoutEnablieAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cashout_enablie_amount = p_cashoutEnablieAmount;
    cashout_enablie_amount_is_set = true;
    cashout_enablie_amount_is_modified = true;
  }


  /** 
   * <em>agree_cancel_flg</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_agreeCancelFlg <em>agree_cancel_flg</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAgreeCancelFlg( String p_agreeCancelFlg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agree_cancel_flg = p_agreeCancelFlg;
    agree_cancel_flg_is_set = true;
    agree_cancel_flg_is_modified = true;
  }


  /** 
   * <em>amount_lock_flg</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_amountLockFlg <em>amount_lock_flg</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAmountLockFlg( String p_amountLockFlg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_lock_flg = p_amountLockFlg;
    amount_lock_flg_is_set = true;
    amount_lock_flg_is_modified = true;
  }


  /** 
   * <em>remark</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_remark <em>remark</em>�J�����̒l������킷200���ȉ���String�l 
   */
  public final void setRemark( String p_remark )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark = p_remark;
    remark_is_set = true;
    remark_is_modified = true;
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
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("agree_cancel_flg") ) {
                    return this.agree_cancel_flg;
                }
                else if ( name.equals("amount_lock_flg") ) {
                    return this.amount_lock_flg;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("cashout_restraint") ) {
                    return this.cashout_restraint;
                }
                else if ( name.equals("cashout_enablie_amount") ) {
                    return this.cashout_enablie_amount;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
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
            case 'r':
                if ( name.equals("remark") ) {
                    return this.remark;
                }
                break;
            case 'u':
                if ( name.equals("use_enable_limit") ) {
                    return this.use_enable_limit;
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
                else if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("agree_cancel_flg") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agree_cancel_flg' must be String: '"+value+"' is not." );
                    this.agree_cancel_flg = (String) value;
                    if (this.agree_cancel_flg_is_set)
                        this.agree_cancel_flg_is_modified = true;
                    this.agree_cancel_flg_is_set = true;
                    return;
                }
                else if ( name.equals("amount_lock_flg") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'amount_lock_flg' must be String: '"+value+"' is not." );
                    this.amount_lock_flg = (String) value;
                    if (this.amount_lock_flg_is_set)
                        this.amount_lock_flg_is_modified = true;
                    this.amount_lock_flg_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("cashout_restraint") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'cashout_restraint' must be Long: '"+value+"' is not." );
                    this.cashout_restraint = (Long) value;
                    if (this.cashout_restraint_is_set)
                        this.cashout_restraint_is_modified = true;
                    this.cashout_restraint_is_set = true;
                    return;
                }
                else if ( name.equals("cashout_enablie_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'cashout_enablie_amount' must be Long: '"+value+"' is not." );
                    this.cashout_enablie_amount = (Long) value;
                    if (this.cashout_enablie_amount_is_set)
                        this.cashout_enablie_amount_is_modified = true;
                    this.cashout_enablie_amount_is_set = true;
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
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
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
            case 'r':
                if ( name.equals("remark") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'remark' must be String: '"+value+"' is not." );
                    this.remark = (String) value;
                    if (this.remark_is_set)
                        this.remark_is_modified = true;
                    this.remark_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("use_enable_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'use_enable_limit' must be Long: '"+value+"' is not." );
                    this.use_enable_limit = (Long) value;
                    if (this.use_enable_limit_is_set)
                        this.use_enable_limit_is_modified = true;
                    this.use_enable_limit_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
