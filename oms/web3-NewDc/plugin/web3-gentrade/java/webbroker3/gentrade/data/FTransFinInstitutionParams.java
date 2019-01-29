head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FTransFinInstitutionParams.java;


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
 * f_trans_fin_institution�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link FTransFinInstitutionRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link FTransFinInstitutionParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link FTransFinInstitutionParams}��{@@link FTransFinInstitutionRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FTransFinInstitutionPK 
 * @@see FTransFinInstitutionRow 
 */
public class FTransFinInstitutionParams extends Params implements FTransFinInstitutionRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "f_trans_fin_institution";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = FTransFinInstitutionRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return FTransFinInstitutionRow.TYPE;
  }


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
   * <em>currency_code</em>�J�����̒l 
   */
  public  String  currency_code;

  /** 
   * <em>fin_del_div</em>�J�����̒l 
   */
  public  String  fin_del_div;

  /** 
   * <em>fin_institution_code</em>�J�����̒l 
   */
  public  String  fin_institution_code;

  /** 
   * <em>fin_institution_name</em>�J�����̒l 
   */
  public  String  fin_institution_name;

  /** 
   * <em>fin_branch_code</em>�J�����̒l 
   */
  public  String  fin_branch_code;

  /** 
   * <em>fin_branch_name</em>�J�����̒l 
   */
  public  String  fin_branch_name;

  /** 
   * <em>fin_account_no</em>�J�����̒l 
   */
  public  String  fin_account_no;

  /** 
   * <em>fin_account_name</em>�J�����̒l 
   */
  public  String  fin_account_name;

  /** 
   * <em>fin_save_div</em>�J�����̒l 
   */
  public  String  fin_save_div;

  /** 
   * <em>fin_comm_div</em>�J�����̒l 
   */
  public  String  fin_comm_div;

  /** 
   * <em>foreign_fin_account_name</em>�J�����̒l 
   */
  public  String  foreign_fin_account_name;

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

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean fin_del_div_is_set = false;
  private boolean fin_del_div_is_modified = false;
  private boolean fin_institution_code_is_set = false;
  private boolean fin_institution_code_is_modified = false;
  private boolean fin_institution_name_is_set = false;
  private boolean fin_institution_name_is_modified = false;
  private boolean fin_branch_code_is_set = false;
  private boolean fin_branch_code_is_modified = false;
  private boolean fin_branch_name_is_set = false;
  private boolean fin_branch_name_is_modified = false;
  private boolean fin_account_no_is_set = false;
  private boolean fin_account_no_is_modified = false;
  private boolean fin_account_name_is_set = false;
  private boolean fin_account_name_is_modified = false;
  private boolean fin_save_div_is_set = false;
  private boolean fin_save_div_is_modified = false;
  private boolean fin_comm_div_is_set = false;
  private boolean fin_comm_div_is_modified = false;
  private boolean foreign_fin_account_name_is_set = false;
  private boolean foreign_fin_account_name_is_modified = false;
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
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "account_code=" + account_code
      + "," + "currency_code=" + currency_code
      + "," + "fin_del_div=" +fin_del_div
      + "," + "fin_institution_code=" +fin_institution_code
      + "," + "fin_institution_name=" +fin_institution_name
      + "," + "fin_branch_code=" +fin_branch_code
      + "," + "fin_branch_name=" +fin_branch_name
      + "," + "fin_account_no=" +fin_account_no
      + "," + "fin_account_name=" +fin_account_name
      + "," + "fin_save_div=" +fin_save_div
      + "," + "fin_comm_div=" +fin_comm_div
      + "," + "foreign_fin_account_name=" +foreign_fin_account_name
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��FTransFinInstitutionParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public FTransFinInstitutionParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_code_is_modified = true;
    currency_code_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���FTransFinInstitutionRow�I�u�W�F�N�g�̒l�𗘗p����FTransFinInstitutionParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����FTransFinInstitutionRow�I�u�W�F�N�g 
   */
  public FTransFinInstitutionParams( FTransFinInstitutionRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    fin_del_div = p_row.getFinDelDiv();
    fin_del_div_is_set = p_row.getFinDelDivIsSet();
    fin_del_div_is_modified = p_row.getFinDelDivIsModified();
    fin_institution_code = p_row.getFinInstitutionCode();
    fin_institution_code_is_set = p_row.getFinInstitutionCodeIsSet();
    fin_institution_code_is_modified = p_row.getFinInstitutionCodeIsModified();
    fin_institution_name = p_row.getFinInstitutionName();
    fin_institution_name_is_set = p_row.getFinInstitutionNameIsSet();
    fin_institution_name_is_modified = p_row.getFinInstitutionNameIsModified();
    fin_branch_code = p_row.getFinBranchCode();
    fin_branch_code_is_set = p_row.getFinBranchCodeIsSet();
    fin_branch_code_is_modified = p_row.getFinBranchCodeIsModified();
    fin_branch_name = p_row.getFinBranchName();
    fin_branch_name_is_set = p_row.getFinBranchNameIsSet();
    fin_branch_name_is_modified = p_row.getFinBranchNameIsModified();
    fin_account_no = p_row.getFinAccountNo();
    fin_account_no_is_set = p_row.getFinAccountNoIsSet();
    fin_account_no_is_modified = p_row.getFinAccountNoIsModified();
    fin_account_name = p_row.getFinAccountName();
    fin_account_name_is_set = p_row.getFinAccountNameIsSet();
    fin_account_name_is_modified = p_row.getFinAccountNameIsModified();
    fin_save_div = p_row.getFinSaveDiv();
    fin_save_div_is_set = p_row.getFinSaveDivIsSet();
    fin_save_div_is_modified = p_row.getFinSaveDivIsModified();
    fin_comm_div = p_row.getFinCommDiv();
    fin_comm_div_is_set = p_row.getFinCommDivIsSet();
    fin_comm_div_is_modified = p_row.getFinCommDivIsModified();
    foreign_fin_account_name = p_row.getForeignFinAccountName();
    foreign_fin_account_name_is_set = p_row.getForeignFinAccountNameIsSet();
    foreign_fin_account_name_is_modified = p_row.getForeignFinAccountNameIsModified();
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
      fin_del_div_is_set = true;
      fin_del_div_is_modified = true;
      fin_institution_code_is_set = true;
      fin_institution_code_is_modified = true;
      fin_institution_name_is_set = true;
      fin_institution_name_is_modified = true;
      fin_branch_code_is_set = true;
      fin_branch_code_is_modified = true;
      fin_branch_name_is_set = true;
      fin_branch_name_is_modified = true;
      fin_account_no_is_set = true;
      fin_account_no_is_modified = true;
      fin_account_name_is_set = true;
      fin_account_name_is_modified = true;
      fin_save_div_is_set = true;
      fin_save_div_is_modified = true;
      fin_comm_div_is_set = true;
      fin_comm_div_is_modified = true;
      foreign_fin_account_name_is_set = true;
      foreign_fin_account_name_is_modified = true;
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
    if ( !( other instanceof FTransFinInstitutionRow ) )
       return false;
    return fieldsEqual( (FTransFinInstitutionRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�FTransFinInstitutionRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( FTransFinInstitutionRow row )
  {
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
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( fin_del_div == null ) {
      if ( row.getFinDelDiv() != null )
        return false;
    } else if ( !fin_del_div.equals( row.getFinDelDiv() ) ) {
        return false;
    }
    if ( fin_institution_code == null ) {
      if ( row.getFinInstitutionCode() != null )
        return false;
    } else if ( !fin_institution_code.equals( row.getFinInstitutionCode() ) ) {
        return false;
    }
    if ( fin_institution_name == null ) {
      if ( row.getFinInstitutionName() != null )
        return false;
    } else if ( !fin_institution_name.equals( row.getFinInstitutionName() ) ) {
        return false;
    }
    if ( fin_branch_code == null ) {
      if ( row.getFinBranchCode() != null )
        return false;
    } else if ( !fin_branch_code.equals( row.getFinBranchCode() ) ) {
        return false;
    }
    if ( fin_branch_name == null ) {
      if ( row.getFinBranchName() != null )
        return false;
    } else if ( !fin_branch_name.equals( row.getFinBranchName() ) ) {
        return false;
    }
    if ( fin_account_no == null ) {
      if ( row.getFinAccountNo() != null )
        return false;
    } else if ( !fin_account_no.equals( row.getFinAccountNo() ) ) {
        return false;
    }
    if ( fin_account_name == null ) {
      if ( row.getFinAccountName() != null )
        return false;
    } else if ( !fin_account_name.equals( row.getFinAccountName() ) ) {
        return false;
    }
    if ( fin_save_div == null ) {
      if ( row.getFinSaveDiv() != null )
        return false;
    } else if ( !fin_save_div.equals( row.getFinSaveDiv() ) ) {
        return false;
    }
    if ( fin_comm_div == null ) {
      if ( row.getFinCommDiv() != null )
        return false;
    } else if ( !fin_comm_div.equals( row.getFinCommDiv() ) ) {
        return false;
    }
    if ( foreign_fin_account_name == null ) {
      if ( row.getForeignFinAccountName() != null )
        return false;
    } else if ( !foreign_fin_account_name.equals( row.getForeignFinAccountName() ) ) {
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (fin_del_div!=null? fin_del_div.hashCode(): 0) 
        + (fin_institution_code!=null? fin_institution_code.hashCode(): 0) 
        + (fin_institution_name!=null? fin_institution_name.hashCode(): 0) 
        + (fin_branch_code!=null? fin_branch_code.hashCode(): 0) 
        + (fin_branch_name!=null? fin_branch_name.hashCode(): 0) 
        + (fin_account_no!=null? fin_account_no.hashCode(): 0) 
        + (fin_account_name!=null? fin_account_name.hashCode(): 0) 
        + (fin_save_div!=null? fin_save_div.hashCode(): 0) 
        + (fin_comm_div!=null? fin_comm_div.hashCode(): 0) 
        + (foreign_fin_account_name!=null? foreign_fin_account_name.hashCode(): 0) 
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
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		map.put("currency_code",currency_code);
		if ( fin_del_div != null )
			map.put("fin_del_div",fin_del_div);
		if ( fin_institution_code != null )
			map.put("fin_institution_code",fin_institution_code);
		if ( fin_institution_name != null )
			map.put("fin_institution_name",fin_institution_name);
		if ( fin_branch_code != null )
			map.put("fin_branch_code",fin_branch_code);
		if ( fin_branch_name != null )
			map.put("fin_branch_name",fin_branch_name);
		if ( fin_account_no != null )
			map.put("fin_account_no",fin_account_no);
		if ( fin_account_name != null )
			map.put("fin_account_name",fin_account_name);
		if ( fin_save_div != null )
			map.put("fin_save_div",fin_save_div);
		if ( fin_comm_div != null )
			map.put("fin_comm_div",fin_comm_div);
		if ( foreign_fin_account_name != null )
			map.put("foreign_fin_account_name",foreign_fin_account_name);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( fin_del_div_is_modified )
			map.put("fin_del_div",fin_del_div);
		if ( fin_institution_code_is_modified )
			map.put("fin_institution_code",fin_institution_code);
		if ( fin_institution_name_is_modified )
			map.put("fin_institution_name",fin_institution_name);
		if ( fin_branch_code_is_modified )
			map.put("fin_branch_code",fin_branch_code);
		if ( fin_branch_name_is_modified )
			map.put("fin_branch_name",fin_branch_name);
		if ( fin_account_no_is_modified )
			map.put("fin_account_no",fin_account_no);
		if ( fin_account_name_is_modified )
			map.put("fin_account_name",fin_account_name);
		if ( fin_save_div_is_modified )
			map.put("fin_save_div",fin_save_div);
		if ( fin_comm_div_is_modified )
			map.put("fin_comm_div",fin_comm_div);
		if ( foreign_fin_account_name_is_modified )
			map.put("foreign_fin_account_name",foreign_fin_account_name);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("fin_del_div",fin_del_div);
			map.put("fin_institution_code",fin_institution_code);
			map.put("fin_institution_name",fin_institution_name);
			map.put("fin_branch_code",fin_branch_code);
			map.put("fin_branch_name",fin_branch_name);
			map.put("fin_account_no",fin_account_no);
			map.put("fin_account_name",fin_account_name);
			map.put("fin_save_div",fin_save_div);
			map.put("fin_comm_div",fin_comm_div);
			map.put("foreign_fin_account_name",foreign_fin_account_name);
			map.put("last_updater",last_updater);
			map.put("created_timestamp",created_timestamp);
			map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>fin_del_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinDelDiv()
  {
    return fin_del_div;
  }


  /** 
   * <em>fin_del_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinDelDivIsSet() {
    return fin_del_div_is_set;
  }


  /** 
   * <em>fin_del_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinDelDivIsModified() {
    return fin_del_div_is_modified;
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
   * <em>fin_account_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinAccountNo()
  {
    return fin_account_no;
  }


  /** 
   * <em>fin_account_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinAccountNoIsSet() {
    return fin_account_no_is_set;
  }


  /** 
   * <em>fin_account_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinAccountNoIsModified() {
    return fin_account_no_is_modified;
  }


  /** 
   * <em>fin_account_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinAccountName()
  {
    return fin_account_name;
  }


  /** 
   * <em>fin_account_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinAccountNameIsSet() {
    return fin_account_name_is_set;
  }


  /** 
   * <em>fin_account_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinAccountNameIsModified() {
    return fin_account_name_is_modified;
  }


  /** 
   * <em>fin_save_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinSaveDiv()
  {
    return fin_save_div;
  }


  /** 
   * <em>fin_save_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinSaveDivIsSet() {
    return fin_save_div_is_set;
  }


  /** 
   * <em>fin_save_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinSaveDivIsModified() {
    return fin_save_div_is_modified;
  }


  /** 
   * <em>fin_comm_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinCommDiv()
  {
    return fin_comm_div;
  }


  /** 
   * <em>fin_comm_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinCommDivIsSet() {
    return fin_comm_div_is_set;
  }


  /** 
   * <em>fin_comm_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinCommDivIsModified() {
    return fin_comm_div_is_modified;
  }


  /** 
   * <em>foreign_fin_account_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getForeignFinAccountName()
  {
    return foreign_fin_account_name;
  }


  /** 
   * <em>foreign_fin_account_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getForeignFinAccountNameIsSet() {
    return foreign_fin_account_name_is_set;
  }


  /** 
   * <em>foreign_fin_account_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getForeignFinAccountNameIsModified() {
    return foreign_fin_account_name_is_modified;
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
    return new FTransFinInstitutionPK(institution_code, branch_code, account_code, currency_code);
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
   * <em>currency_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_currencyCode <em>currency_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>fin_del_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finDelDiv <em>fin_del_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setFinDelDiv( String p_finDelDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_del_div = p_finDelDiv;
    fin_del_div_is_set = true;
    fin_del_div_is_modified = true;
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
   * <em>fin_account_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finAccountNo <em>fin_account_no</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setFinAccountNo( String p_finAccountNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_account_no = p_finAccountNo;
    fin_account_no_is_set = true;
    fin_account_no_is_modified = true;
  }


  /** 
   * <em>fin_account_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finAccountName <em>fin_account_name</em>�J�����̒l������킷31���ȉ���String�l 
   */
  public final void setFinAccountName( String p_finAccountName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_account_name = p_finAccountName;
    fin_account_name_is_set = true;
    fin_account_name_is_modified = true;
  }


  /** 
   * <em>fin_save_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finSaveDiv <em>fin_save_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setFinSaveDiv( String p_finSaveDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_save_div = p_finSaveDiv;
    fin_save_div_is_set = true;
    fin_save_div_is_modified = true;
  }


  /** 
   * <em>fin_comm_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finCommDiv <em>fin_comm_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setFinCommDiv( String p_finCommDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_comm_div = p_finCommDiv;
    fin_comm_div_is_set = true;
    fin_comm_div_is_modified = true;
  }


  /** 
   * <em>foreign_fin_account_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_foreignFinAccountName <em>foreign_fin_account_name</em>�J�����̒l������킷30���ȉ���String�l 
   */
  public final void setForeignFinAccountName( String p_foreignFinAccountName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_fin_account_name = p_foreignFinAccountName;
    foreign_fin_account_name_is_set = true;
    foreign_fin_account_name_is_modified = true;
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
                if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("fin_del_div") ) {
                    return this.fin_del_div;
                }
                else if ( name.equals("fin_institution_code") ) {
                    return this.fin_institution_code;
                }
                else if ( name.equals("fin_institution_name") ) {
                    return this.fin_institution_name;
                }
                else if ( name.equals("fin_branch_code") ) {
                    return this.fin_branch_code;
                }
                else if ( name.equals("fin_branch_name") ) {
                    return this.fin_branch_name;
                }
                else if ( name.equals("fin_account_no") ) {
                    return this.fin_account_no;
                }
                else if ( name.equals("fin_account_name") ) {
                    return this.fin_account_name;
                }
                else if ( name.equals("fin_save_div") ) {
                    return this.fin_save_div;
                }
                else if ( name.equals("fin_comm_div") ) {
                    return this.fin_comm_div;
                }
                else if ( name.equals("foreign_fin_account_name") ) {
                    return this.foreign_fin_account_name;
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
                if ( name.equals("currency_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_code' must be String: '"+value+"' is not." );
                    this.currency_code = (String) value;
                    if (this.currency_code_is_set)
                        this.currency_code_is_modified = true;
                    this.currency_code_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( value != null )
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
                if ( name.equals("fin_del_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_del_div' must be String: '"+value+"' is not." );
                    this.fin_del_div = (String) value;
                    if (this.fin_del_div_is_set)
                        this.fin_del_div_is_modified = true;
                    this.fin_del_div_is_set = true;
                    return;
                }
                else if ( name.equals("fin_institution_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_code' must be String: '"+value+"' is not." );
                    this.fin_institution_code = (String) value;
                    if (this.fin_institution_code_is_set)
                        this.fin_institution_code_is_modified = true;
                    this.fin_institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("fin_institution_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_name' must be String: '"+value+"' is not." );
                    this.fin_institution_name = (String) value;
                    if (this.fin_institution_name_is_set)
                        this.fin_institution_name_is_modified = true;
                    this.fin_institution_name_is_set = true;
                    return;
                }
                else if ( name.equals("fin_branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_branch_code' must be String: '"+value+"' is not." );
                    this.fin_branch_code = (String) value;
                    if (this.fin_branch_code_is_set)
                        this.fin_branch_code_is_modified = true;
                    this.fin_branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("fin_branch_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_branch_name' must be String: '"+value+"' is not." );
                    this.fin_branch_name = (String) value;
                    if (this.fin_branch_name_is_set)
                        this.fin_branch_name_is_modified = true;
                    this.fin_branch_name_is_set = true;
                    return;
                }
                else if ( name.equals("fin_account_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_account_no' must be String: '"+value+"' is not." );
                    this.fin_account_no = (String) value;
                    if (this.fin_account_no_is_set)
                        this.fin_account_no_is_modified = true;
                    this.fin_account_no_is_set = true;
                    return;
                }
                else if ( name.equals("fin_account_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_account_name' must be String: '"+value+"' is not." );
                    this.fin_account_name = (String) value;
                    if (this.fin_account_name_is_set)
                        this.fin_account_name_is_modified = true;
                    this.fin_account_name_is_set = true;
                    return;
                }
                else if ( name.equals("fin_save_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_save_div' must be String: '"+value+"' is not." );
                    this.fin_save_div = (String) value;
                    if (this.fin_save_div_is_set)
                        this.fin_save_div_is_modified = true;
                    this.fin_save_div_is_set = true;
                    return;
                }
                else if ( name.equals("fin_comm_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_comm_div' must be String: '"+value+"' is not." );
                    this.fin_comm_div = (String) value;
                    if (this.fin_comm_div_is_set)
                        this.fin_comm_div_is_modified = true;
                    this.fin_comm_div_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_fin_account_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'foreign_fin_account_name' must be String: '"+value+"' is not." );
                    this.foreign_fin_account_name = (String) value;
                    if (this.foreign_fin_account_name_is_set)
                        this.foreign_fin_account_name_is_modified = true;
                    this.foreign_fin_account_name_is_set = true;
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
                    if ( value != null )
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
