head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.36.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FinInstitutionBankParams.java;


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
 * fin_institution_bank�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link FinInstitutionBankRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link FinInstitutionBankParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link FinInstitutionBankParams}��{@@link FinInstitutionBankRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FinInstitutionBankPK 
 * @@see FinInstitutionBankRow 
 */
public class FinInstitutionBankParams extends Params implements FinInstitutionBankRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "fin_institution_bank";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = FinInstitutionBankRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return FinInstitutionBankRow.TYPE;
  }


  /** 
   * <em>fin_institution_code</em>�J�����̒l 
   */
  public  String  fin_institution_code;

  /** 
   * <em>fin_branch_code</em>�J�����̒l 
   */
  public  String  fin_branch_code;

  /** 
   * <em>fin_institution_name_kana</em>�J�����̒l 
   */
  public  String  fin_institution_name_kana;

  /** 
   * <em>fin_branch_name_kana</em>�J�����̒l 
   */
  public  String  fin_branch_name_kana;

  /** 
   * <em>fin_institution_name</em>�J�����̒l 
   */
  public  String  fin_institution_name;

  /** 
   * <em>fin_branch_name</em>�J�����̒l 
   */
  public  String  fin_branch_name;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean fin_institution_code_is_set = false;
  private boolean fin_institution_code_is_modified = false;
  private boolean fin_branch_code_is_set = false;
  private boolean fin_branch_code_is_modified = false;
  private boolean fin_institution_name_kana_is_set = false;
  private boolean fin_institution_name_kana_is_modified = false;
  private boolean fin_branch_name_kana_is_set = false;
  private boolean fin_branch_name_kana_is_modified = false;
  private boolean fin_institution_name_is_set = false;
  private boolean fin_institution_name_is_modified = false;
  private boolean fin_branch_name_is_set = false;
  private boolean fin_branch_name_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "fin_institution_code=" + fin_institution_code
      + "," + "fin_branch_code=" + fin_branch_code
      + "," + "fin_institution_name_kana=" +fin_institution_name_kana
      + "," + "fin_branch_name_kana=" +fin_branch_name_kana
      + "," + "fin_institution_name=" +fin_institution_name
      + "," + "fin_branch_name=" +fin_branch_name
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��FinInstitutionBankParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public FinInstitutionBankParams() {
    fin_institution_code_is_modified = true;
    fin_branch_code_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���FinInstitutionBankRow�I�u�W�F�N�g�̒l�𗘗p����FinInstitutionBankParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����FinInstitutionBankRow�I�u�W�F�N�g 
   */
  public FinInstitutionBankParams( FinInstitutionBankRow p_row )
  {
    fin_institution_code = p_row.getFinInstitutionCode();
    fin_institution_code_is_set = p_row.getFinInstitutionCodeIsSet();
    fin_institution_code_is_modified = p_row.getFinInstitutionCodeIsModified();
    fin_branch_code = p_row.getFinBranchCode();
    fin_branch_code_is_set = p_row.getFinBranchCodeIsSet();
    fin_branch_code_is_modified = p_row.getFinBranchCodeIsModified();
    fin_institution_name_kana = p_row.getFinInstitutionNameKana();
    fin_institution_name_kana_is_set = p_row.getFinInstitutionNameKanaIsSet();
    fin_institution_name_kana_is_modified = p_row.getFinInstitutionNameKanaIsModified();
    fin_branch_name_kana = p_row.getFinBranchNameKana();
    fin_branch_name_kana_is_set = p_row.getFinBranchNameKanaIsSet();
    fin_branch_name_kana_is_modified = p_row.getFinBranchNameKanaIsModified();
    fin_institution_name = p_row.getFinInstitutionName();
    fin_institution_name_is_set = p_row.getFinInstitutionNameIsSet();
    fin_institution_name_is_modified = p_row.getFinInstitutionNameIsModified();
    fin_branch_name = p_row.getFinBranchName();
    fin_branch_name_is_set = p_row.getFinBranchNameIsSet();
    fin_branch_name_is_modified = p_row.getFinBranchNameIsModified();
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
      fin_institution_name_kana_is_set = true;
      fin_institution_name_kana_is_modified = true;
      fin_branch_name_kana_is_set = true;
      fin_branch_name_kana_is_modified = true;
      fin_institution_name_is_set = true;
      fin_institution_name_is_modified = true;
      fin_branch_name_is_set = true;
      fin_branch_name_is_modified = true;
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
    if ( !( other instanceof FinInstitutionBankRow ) )
       return false;
    return fieldsEqual( (FinInstitutionBankRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�FinInstitutionBankRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( FinInstitutionBankRow row )
  {
    if ( fin_institution_code == null ) {
      if ( row.getFinInstitutionCode() != null )
        return false;
    } else if ( !fin_institution_code.equals( row.getFinInstitutionCode() ) ) {
        return false;
    }
    if ( fin_branch_code == null ) {
      if ( row.getFinBranchCode() != null )
        return false;
    } else if ( !fin_branch_code.equals( row.getFinBranchCode() ) ) {
        return false;
    }
    if ( fin_institution_name_kana == null ) {
      if ( row.getFinInstitutionNameKana() != null )
        return false;
    } else if ( !fin_institution_name_kana.equals( row.getFinInstitutionNameKana() ) ) {
        return false;
    }
    if ( fin_branch_name_kana == null ) {
      if ( row.getFinBranchNameKana() != null )
        return false;
    } else if ( !fin_branch_name_kana.equals( row.getFinBranchNameKana() ) ) {
        return false;
    }
    if ( fin_institution_name == null ) {
      if ( row.getFinInstitutionName() != null )
        return false;
    } else if ( !fin_institution_name.equals( row.getFinInstitutionName() ) ) {
        return false;
    }
    if ( fin_branch_name == null ) {
      if ( row.getFinBranchName() != null )
        return false;
    } else if ( !fin_branch_name.equals( row.getFinBranchName() ) ) {
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
      return  (fin_institution_code!=null? fin_institution_code.hashCode(): 0) 
        + (fin_branch_code!=null? fin_branch_code.hashCode(): 0) 
        + (fin_institution_name_kana!=null? fin_institution_name_kana.hashCode(): 0) 
        + (fin_branch_name_kana!=null? fin_branch_name_kana.hashCode(): 0) 
        + (fin_institution_name!=null? fin_institution_name.hashCode(): 0) 
        + (fin_branch_name!=null? fin_branch_name.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !fin_institution_name_kana_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_institution_name_kana' must be set before inserting.");
		if ( !fin_branch_name_kana_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_branch_name_kana' must be set before inserting.");
		if ( !fin_institution_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_institution_name' must be set before inserting.");
		if ( !fin_branch_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_branch_name' must be set before inserting.");
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
		map.put("fin_institution_code",fin_institution_code);
		map.put("fin_branch_code",fin_branch_code);
		map.put("fin_institution_name_kana",fin_institution_name_kana);
		map.put("fin_branch_name_kana",fin_branch_name_kana);
		map.put("fin_institution_name",fin_institution_name);
		map.put("fin_branch_name",fin_branch_name);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( fin_institution_name_kana_is_modified )
			map.put("fin_institution_name_kana",fin_institution_name_kana);
		if ( fin_branch_name_kana_is_modified )
			map.put("fin_branch_name_kana",fin_branch_name_kana);
		if ( fin_institution_name_is_modified )
			map.put("fin_institution_name",fin_institution_name);
		if ( fin_branch_name_is_modified )
			map.put("fin_branch_name",fin_branch_name);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( fin_institution_name_kana_is_set )
				map.put("fin_institution_name_kana",fin_institution_name_kana);
			if ( fin_branch_name_kana_is_set )
				map.put("fin_branch_name_kana",fin_branch_name_kana);
			if ( fin_institution_name_is_set )
				map.put("fin_institution_name",fin_institution_name);
			if ( fin_branch_name_is_set )
				map.put("fin_branch_name",fin_branch_name);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>fin_institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinInstitutionCode()
  {
    return fin_institution_code;
  }


  /** 
   * <em>fin_institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinInstitutionCodeIsSet() {
    return fin_institution_code_is_set;
  }


  /** 
   * <em>fin_institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinInstitutionCodeIsModified() {
    return fin_institution_code_is_modified;
  }


  /** 
   * <em>fin_branch_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinBranchCode()
  {
    return fin_branch_code;
  }


  /** 
   * <em>fin_branch_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinBranchCodeIsSet() {
    return fin_branch_code_is_set;
  }


  /** 
   * <em>fin_branch_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinBranchCodeIsModified() {
    return fin_branch_code_is_modified;
  }


  /** 
   * <em>fin_institution_name_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinInstitutionNameKana()
  {
    return fin_institution_name_kana;
  }


  /** 
   * <em>fin_institution_name_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinInstitutionNameKanaIsSet() {
    return fin_institution_name_kana_is_set;
  }


  /** 
   * <em>fin_institution_name_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinInstitutionNameKanaIsModified() {
    return fin_institution_name_kana_is_modified;
  }


  /** 
   * <em>fin_branch_name_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinBranchNameKana()
  {
    return fin_branch_name_kana;
  }


  /** 
   * <em>fin_branch_name_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinBranchNameKanaIsSet() {
    return fin_branch_name_kana_is_set;
  }


  /** 
   * <em>fin_branch_name_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinBranchNameKanaIsModified() {
    return fin_branch_name_kana_is_modified;
  }


  /** 
   * <em>fin_institution_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinInstitutionName()
  {
    return fin_institution_name;
  }


  /** 
   * <em>fin_institution_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinInstitutionNameIsSet() {
    return fin_institution_name_is_set;
  }


  /** 
   * <em>fin_institution_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinInstitutionNameIsModified() {
    return fin_institution_name_is_modified;
  }


  /** 
   * <em>fin_branch_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinBranchName()
  {
    return fin_branch_name;
  }


  /** 
   * <em>fin_branch_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinBranchNameIsSet() {
    return fin_branch_name_is_set;
  }


  /** 
   * <em>fin_branch_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinBranchNameIsModified() {
    return fin_branch_name_is_modified;
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
    return new FinInstitutionBankPK(fin_institution_code, fin_branch_code);
  }


  /** 
   * <em>fin_institution_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finInstitutionCode <em>fin_institution_code</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setFinInstitutionCode( String p_finInstitutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_institution_code = p_finInstitutionCode;
    fin_institution_code_is_set = true;
    fin_institution_code_is_modified = true;
  }


  /** 
   * <em>fin_branch_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finBranchCode <em>fin_branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setFinBranchCode( String p_finBranchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_branch_code = p_finBranchCode;
    fin_branch_code_is_set = true;
    fin_branch_code_is_modified = true;
  }


  /** 
   * <em>fin_institution_name_kana</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finInstitutionNameKana <em>fin_institution_name_kana</em>�J�����̒l������킷15���ȉ���String�l 
   */
  public final void setFinInstitutionNameKana( String p_finInstitutionNameKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_institution_name_kana = p_finInstitutionNameKana;
    fin_institution_name_kana_is_set = true;
    fin_institution_name_kana_is_modified = true;
  }


  /** 
   * <em>fin_branch_name_kana</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finBranchNameKana <em>fin_branch_name_kana</em>�J�����̒l������킷15���ȉ���String�l 
   */
  public final void setFinBranchNameKana( String p_finBranchNameKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_branch_name_kana = p_finBranchNameKana;
    fin_branch_name_kana_is_set = true;
    fin_branch_name_kana_is_modified = true;
  }


  /** 
   * <em>fin_institution_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finInstitutionName <em>fin_institution_name</em>�J�����̒l������킷32���ȉ���String�l 
   */
  public final void setFinInstitutionName( String p_finInstitutionName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_institution_name = p_finInstitutionName;
    fin_institution_name_is_set = true;
    fin_institution_name_is_modified = true;
  }


  /** 
   * <em>fin_branch_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finBranchName <em>fin_branch_name</em>�J�����̒l������킷32���ȉ���String�l 
   */
  public final void setFinBranchName( String p_finBranchName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_branch_name = p_finBranchName;
    fin_branch_name_is_set = true;
    fin_branch_name_is_modified = true;
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
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("fin_institution_code") ) {
                    return this.fin_institution_code;
                }
                else if ( name.equals("fin_branch_code") ) {
                    return this.fin_branch_code;
                }
                else if ( name.equals("fin_institution_name_kana") ) {
                    return this.fin_institution_name_kana;
                }
                else if ( name.equals("fin_branch_name_kana") ) {
                    return this.fin_branch_name_kana;
                }
                else if ( name.equals("fin_institution_name") ) {
                    return this.fin_institution_name;
                }
                else if ( name.equals("fin_branch_name") ) {
                    return this.fin_branch_name;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
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
                if ( name.equals("fin_institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_code' must be String: '"+value+"' is not." );
                    this.fin_institution_code = (String) value;
                    if (this.fin_institution_code_is_set)
                        this.fin_institution_code_is_modified = true;
                    this.fin_institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("fin_branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_branch_code' must be String: '"+value+"' is not." );
                    this.fin_branch_code = (String) value;
                    if (this.fin_branch_code_is_set)
                        this.fin_branch_code_is_modified = true;
                    this.fin_branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("fin_institution_name_kana") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_name_kana' must be String: '"+value+"' is not." );
                    this.fin_institution_name_kana = (String) value;
                    if (this.fin_institution_name_kana_is_set)
                        this.fin_institution_name_kana_is_modified = true;
                    this.fin_institution_name_kana_is_set = true;
                    return;
                }
                else if ( name.equals("fin_branch_name_kana") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_branch_name_kana' must be String: '"+value+"' is not." );
                    this.fin_branch_name_kana = (String) value;
                    if (this.fin_branch_name_kana_is_set)
                        this.fin_branch_name_kana_is_modified = true;
                    this.fin_branch_name_kana_is_set = true;
                    return;
                }
                else if ( name.equals("fin_institution_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_name' must be String: '"+value+"' is not." );
                    this.fin_institution_name = (String) value;
                    if (this.fin_institution_name_is_set)
                        this.fin_institution_name_is_modified = true;
                    this.fin_institution_name_is_set = true;
                    return;
                }
                else if ( name.equals("fin_branch_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_branch_name' must be String: '"+value+"' is not." );
                    this.fin_branch_name = (String) value;
                    if (this.fin_branch_name_is_set)
                        this.fin_branch_name_is_modified = true;
                    this.fin_branch_name_is_set = true;
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
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
