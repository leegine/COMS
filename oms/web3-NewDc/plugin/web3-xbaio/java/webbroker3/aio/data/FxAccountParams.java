head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.40.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	FxAccountParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * fx_account�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link FxAccountRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link FxAccountParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link FxAccountParams}��{@@link FxAccountRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FxAccountPK 
 * @@see FxAccountRow 
 */
public class FxAccountParams extends Params implements FxAccountRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "fx_account";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = FxAccountRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return FxAccountRow.TYPE;
  }


  /** 
   * <em>fx_account_id</em>�J�����̒l 
   */
  public  long  fx_account_id;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>�J�����̒l 
   */
  public  String  branch_code;

  /** 
   * <em>fx_system_code</em>�J�����̒l 
   */
  public  String  fx_system_code;

  /** 
   * <em>account_code</em>�J�����̒l 
   */
  public  String  account_code;

  /** 
   * <em>fx_account_div</em>�J�����̒l 
   */
  public  String  fx_account_div;

  /** 
   * <em>fx_last_name</em>�J�����̒l 
   */
  public  String  fx_last_name;

  /** 
   * <em>fx_first_name</em>�J�����̒l 
   */
  public  String  fx_first_name;

  /** 
   * <em>fx_mail_address</em>�J�����̒l 
   */
  public  String  fx_mail_address;

  /** 
   * <em>fx_login_id</em>�J�����̒l 
   */
  public  Long  fx_login_id;

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

  private boolean fx_account_id_is_set = false;
  private boolean fx_account_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean fx_system_code_is_set = false;
  private boolean fx_system_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean fx_account_div_is_set = false;
  private boolean fx_account_div_is_modified = false;
  private boolean fx_last_name_is_set = false;
  private boolean fx_last_name_is_modified = false;
  private boolean fx_first_name_is_set = false;
  private boolean fx_first_name_is_modified = false;
  private boolean fx_mail_address_is_set = false;
  private boolean fx_mail_address_is_modified = false;
  private boolean fx_login_id_is_set = false;
  private boolean fx_login_id_is_modified = false;
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
       + "fx_account_id=" + fx_account_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "fx_system_code=" +fx_system_code
      + "," + "account_code=" +account_code
      + "," + "fx_account_div=" +fx_account_div
      + "," + "fx_last_name=" +fx_last_name
      + "," + "fx_first_name=" +fx_first_name
      + "," + "fx_mail_address=" +fx_mail_address
      + "," + "fx_login_id=" +fx_login_id
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��FxAccountParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public FxAccountParams() {
    fx_account_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���FxAccountRow�I�u�W�F�N�g�̒l�𗘗p����FxAccountParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����FxAccountRow�I�u�W�F�N�g 
   */
  public FxAccountParams( FxAccountRow p_row )
  {
    fx_account_id = p_row.getFxAccountId();
    fx_account_id_is_set = p_row.getFxAccountIdIsSet();
    fx_account_id_is_modified = p_row.getFxAccountIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    fx_system_code = p_row.getFxSystemCode();
    fx_system_code_is_set = p_row.getFxSystemCodeIsSet();
    fx_system_code_is_modified = p_row.getFxSystemCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    fx_account_div = p_row.getFxAccountDiv();
    fx_account_div_is_set = p_row.getFxAccountDivIsSet();
    fx_account_div_is_modified = p_row.getFxAccountDivIsModified();
    fx_last_name = p_row.getFxLastName();
    fx_last_name_is_set = p_row.getFxLastNameIsSet();
    fx_last_name_is_modified = p_row.getFxLastNameIsModified();
    fx_first_name = p_row.getFxFirstName();
    fx_first_name_is_set = p_row.getFxFirstNameIsSet();
    fx_first_name_is_modified = p_row.getFxFirstNameIsModified();
    fx_mail_address = p_row.getFxMailAddress();
    fx_mail_address_is_set = p_row.getFxMailAddressIsSet();
    fx_mail_address_is_modified = p_row.getFxMailAddressIsModified();
    if ( !p_row.getFxLoginIdIsNull() )
      fx_login_id = new Long( p_row.getFxLoginId() );
    fx_login_id_is_set = p_row.getFxLoginIdIsSet();
    fx_login_id_is_modified = p_row.getFxLoginIdIsModified();
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
      fx_system_code_is_set = true;
      fx_system_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      fx_account_div_is_set = true;
      fx_account_div_is_modified = true;
      fx_last_name_is_set = true;
      fx_last_name_is_modified = true;
      fx_first_name_is_set = true;
      fx_first_name_is_modified = true;
      fx_mail_address_is_set = true;
      fx_mail_address_is_modified = true;
      fx_login_id_is_set = true;
      fx_login_id_is_modified = true;
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
    if ( !( other instanceof FxAccountRow ) )
       return false;
    return fieldsEqual( (FxAccountRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�FxAccountRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( FxAccountRow row )
  {
    if ( fx_account_id != row.getFxAccountId() )
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
    if ( fx_system_code == null ) {
      if ( row.getFxSystemCode() != null )
        return false;
    } else if ( !fx_system_code.equals( row.getFxSystemCode() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( fx_account_div == null ) {
      if ( row.getFxAccountDiv() != null )
        return false;
    } else if ( !fx_account_div.equals( row.getFxAccountDiv() ) ) {
        return false;
    }
    if ( fx_last_name == null ) {
      if ( row.getFxLastName() != null )
        return false;
    } else if ( !fx_last_name.equals( row.getFxLastName() ) ) {
        return false;
    }
    if ( fx_first_name == null ) {
      if ( row.getFxFirstName() != null )
        return false;
    } else if ( !fx_first_name.equals( row.getFxFirstName() ) ) {
        return false;
    }
    if ( fx_mail_address == null ) {
      if ( row.getFxMailAddress() != null )
        return false;
    } else if ( !fx_mail_address.equals( row.getFxMailAddress() ) ) {
        return false;
    }
    if ( fx_login_id == null ) {
      if ( !row.getFxLoginIdIsNull() )
        return false;
    } else if ( row.getFxLoginIdIsNull() || ( fx_login_id.longValue() != row.getFxLoginId() ) ) {
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
      return  ((int) fx_account_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (fx_system_code!=null? fx_system_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (fx_account_div!=null? fx_account_div.hashCode(): 0) 
        + (fx_last_name!=null? fx_last_name.hashCode(): 0) 
        + (fx_first_name!=null? fx_first_name.hashCode(): 0) 
        + (fx_mail_address!=null? fx_mail_address.hashCode(): 0) 
        + (fx_login_id!=null? fx_login_id.hashCode(): 0) 
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
		if ( !fx_system_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'fx_system_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !fx_account_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'fx_account_div' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("fx_account_id",new Long(fx_account_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("fx_system_code",fx_system_code);
		map.put("account_code",account_code);
		map.put("fx_account_div",fx_account_div);
		if ( fx_last_name != null )
			map.put("fx_last_name",fx_last_name);
		if ( fx_first_name != null )
			map.put("fx_first_name",fx_first_name);
		if ( fx_mail_address != null )
			map.put("fx_mail_address",fx_mail_address);
		if ( fx_login_id != null )
			map.put("fx_login_id",fx_login_id);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
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
		if ( fx_system_code_is_modified )
			map.put("fx_system_code",fx_system_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( fx_account_div_is_modified )
			map.put("fx_account_div",fx_account_div);
		if ( fx_last_name_is_modified )
			map.put("fx_last_name",fx_last_name);
		if ( fx_first_name_is_modified )
			map.put("fx_first_name",fx_first_name);
		if ( fx_mail_address_is_modified )
			map.put("fx_mail_address",fx_mail_address);
		if ( fx_login_id_is_modified )
			map.put("fx_login_id",fx_login_id);
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
			if ( fx_system_code_is_set )
				map.put("fx_system_code",fx_system_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			if ( fx_account_div_is_set )
				map.put("fx_account_div",fx_account_div);
			map.put("fx_last_name",fx_last_name);
			map.put("fx_first_name",fx_first_name);
			map.put("fx_mail_address",fx_mail_address);
			map.put("fx_login_id",fx_login_id);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>fx_account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getFxAccountId()
  {
    return fx_account_id;
  }


  /** 
   * <em>fx_account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxAccountIdIsSet() {
    return fx_account_id_is_set;
  }


  /** 
   * <em>fx_account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxAccountIdIsModified() {
    return fx_account_id_is_modified;
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
   * <em>fx_system_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFxSystemCode()
  {
    return fx_system_code;
  }


  /** 
   * <em>fx_system_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxSystemCodeIsSet() {
    return fx_system_code_is_set;
  }


  /** 
   * <em>fx_system_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxSystemCodeIsModified() {
    return fx_system_code_is_modified;
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
   * <em>fx_account_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFxAccountDiv()
  {
    return fx_account_div;
  }


  /** 
   * <em>fx_account_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxAccountDivIsSet() {
    return fx_account_div_is_set;
  }


  /** 
   * <em>fx_account_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxAccountDivIsModified() {
    return fx_account_div_is_modified;
  }


  /** 
   * <em>fx_last_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFxLastName()
  {
    return fx_last_name;
  }


  /** 
   * <em>fx_last_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxLastNameIsSet() {
    return fx_last_name_is_set;
  }


  /** 
   * <em>fx_last_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxLastNameIsModified() {
    return fx_last_name_is_modified;
  }


  /** 
   * <em>fx_first_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFxFirstName()
  {
    return fx_first_name;
  }


  /** 
   * <em>fx_first_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxFirstNameIsSet() {
    return fx_first_name_is_set;
  }


  /** 
   * <em>fx_first_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxFirstNameIsModified() {
    return fx_first_name_is_modified;
  }


  /** 
   * <em>fx_mail_address</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFxMailAddress()
  {
    return fx_mail_address;
  }


  /** 
   * <em>fx_mail_address</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxMailAddressIsSet() {
    return fx_mail_address_is_set;
  }


  /** 
   * <em>fx_mail_address</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxMailAddressIsModified() {
    return fx_mail_address_is_modified;
  }


  /** 
   * <em>fx_login_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getFxLoginId()
  {
    return ( fx_login_id==null? ((long)0): fx_login_id.longValue() );
  }


  /** 
   * <em>fx_login_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getFxLoginIdIsNull()
  {
    return fx_login_id == null;
  }


  /** 
   * <em>fx_login_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxLoginIdIsSet() {
    return fx_login_id_is_set;
  }


  /** 
   * <em>fx_login_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFxLoginIdIsModified() {
    return fx_login_id_is_modified;
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
    return new FxAccountPK(fx_account_id);
  }


  /** 
   * <em>fx_account_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_fxAccountId <em>fx_account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setFxAccountId( long p_fxAccountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_account_id = p_fxAccountId;
    fx_account_id_is_set = true;
    fx_account_id_is_modified = true;
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
   * <em>fx_system_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_fxSystemCode <em>fx_system_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setFxSystemCode( String p_fxSystemCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_system_code = p_fxSystemCode;
    fx_system_code_is_set = true;
    fx_system_code_is_modified = true;
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
   * <em>fx_account_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_fxAccountDiv <em>fx_account_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setFxAccountDiv( String p_fxAccountDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_account_div = p_fxAccountDiv;
    fx_account_div_is_set = true;
    fx_account_div_is_modified = true;
  }


  /** 
   * <em>fx_last_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_fxLastName <em>fx_last_name</em>�J�����̒l������킷50���ȉ���String�l 
   */
  public final void setFxLastName( String p_fxLastName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_last_name = p_fxLastName;
    fx_last_name_is_set = true;
    fx_last_name_is_modified = true;
  }


  /** 
   * <em>fx_first_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_fxFirstName <em>fx_first_name</em>�J�����̒l������킷50���ȉ���String�l 
   */
  public final void setFxFirstName( String p_fxFirstName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_first_name = p_fxFirstName;
    fx_first_name_is_set = true;
    fx_first_name_is_modified = true;
  }


  /** 
   * <em>fx_mail_address</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_fxMailAddress <em>fx_mail_address</em>�J�����̒l������킷50���ȉ���String�l 
   */
  public final void setFxMailAddress( String p_fxMailAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_mail_address = p_fxMailAddress;
    fx_mail_address_is_set = true;
    fx_mail_address_is_modified = true;
  }


  /** 
   * <em>fx_login_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_fxLoginId <em>fx_login_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setFxLoginId( long p_fxLoginId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_login_id = new Long(p_fxLoginId);
    fx_login_id_is_set = true;
    fx_login_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>fx_login_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setFxLoginId( Long p_fxLoginId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    fx_login_id = p_fxLoginId;
    fx_login_id_is_set = true;
    fx_login_id_is_modified = true;
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
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("fx_account_id") ) {
                    return new Long( fx_account_id );
                }
                else if ( name.equals("fx_system_code") ) {
                    return this.fx_system_code;
                }
                else if ( name.equals("fx_account_div") ) {
                    return this.fx_account_div;
                }
                else if ( name.equals("fx_last_name") ) {
                    return this.fx_last_name;
                }
                else if ( name.equals("fx_first_name") ) {
                    return this.fx_first_name;
                }
                else if ( name.equals("fx_mail_address") ) {
                    return this.fx_mail_address;
                }
                else if ( name.equals("fx_login_id") ) {
                    return this.fx_login_id;
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
                if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
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
                if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fx_account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'fx_account_id' must be Long: '"+value+"' is not." );
                    this.fx_account_id = ((Long) value).longValue();
                    if (this.fx_account_id_is_set)
                        this.fx_account_id_is_modified = true;
                    this.fx_account_id_is_set = true;
                    return;
                }
                else if ( name.equals("fx_system_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_system_code' must be String: '"+value+"' is not." );
                    this.fx_system_code = (String) value;
                    if (this.fx_system_code_is_set)
                        this.fx_system_code_is_modified = true;
                    this.fx_system_code_is_set = true;
                    return;
                }
                else if ( name.equals("fx_account_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_account_div' must be String: '"+value+"' is not." );
                    this.fx_account_div = (String) value;
                    if (this.fx_account_div_is_set)
                        this.fx_account_div_is_modified = true;
                    this.fx_account_div_is_set = true;
                    return;
                }
                else if ( name.equals("fx_last_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_last_name' must be String: '"+value+"' is not." );
                    this.fx_last_name = (String) value;
                    if (this.fx_last_name_is_set)
                        this.fx_last_name_is_modified = true;
                    this.fx_last_name_is_set = true;
                    return;
                }
                else if ( name.equals("fx_first_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_first_name' must be String: '"+value+"' is not." );
                    this.fx_first_name = (String) value;
                    if (this.fx_first_name_is_set)
                        this.fx_first_name_is_modified = true;
                    this.fx_first_name_is_set = true;
                    return;
                }
                else if ( name.equals("fx_mail_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_mail_address' must be String: '"+value+"' is not." );
                    this.fx_mail_address = (String) value;
                    if (this.fx_mail_address_is_set)
                        this.fx_mail_address_is_modified = true;
                    this.fx_mail_address_is_set = true;
                    return;
                }
                else if ( name.equals("fx_login_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'fx_login_id' must be Long: '"+value+"' is not." );
                    this.fx_login_id = (Long) value;
                    if (this.fx_login_id_is_set)
                        this.fx_login_id_is_modified = true;
                    this.fx_login_id_is_set = true;
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
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
