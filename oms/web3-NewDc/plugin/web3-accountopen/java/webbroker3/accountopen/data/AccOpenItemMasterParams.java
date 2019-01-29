head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.13.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenItemMasterParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * acc_open_item_master�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link AccOpenItemMasterRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link AccOpenItemMasterParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link AccOpenItemMasterParams}��{@@link AccOpenItemMasterRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenItemMasterPK 
 * @@see AccOpenItemMasterRow 
 */
public class AccOpenItemMasterParams extends Params implements AccOpenItemMasterRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "acc_open_item_master";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = AccOpenItemMasterRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return AccOpenItemMasterRow.TYPE;
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
   * <em>account_div</em>�J�����̒l 
   */
  public  String  account_div;

  /** 
   * <em>validate_type</em>�J�����̒l 
   */
  public  String  validate_type;

  /** 
   * <em>item_symbol_name</em>�J�����̒l 
   */
  public  String  item_symbol_name;

  /** 
   * <em>item_name</em>�J�����̒l 
   */
  public  String  item_name;

  /** 
   * <em>necessary_flag</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  necessary_flag;

  /** 
   * <em>item_min</em>�J�����̒l 
   */
  public  Integer  item_min;

  /** 
   * <em>item_max</em>�J�����̒l 
   */
  public  Integer  item_max;

  /** 
   * <em>item_check_mode</em>�J�����̒l 
   */
  public  String  item_check_mode;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_div_is_set = false;
  private boolean account_div_is_modified = false;
  private boolean validate_type_is_set = false;
  private boolean validate_type_is_modified = false;
  private boolean item_symbol_name_is_set = false;
  private boolean item_symbol_name_is_modified = false;
  private boolean item_name_is_set = false;
  private boolean item_name_is_modified = false;
  private boolean necessary_flag_is_set = false;
  private boolean necessary_flag_is_modified = false;
  private boolean item_min_is_set = false;
  private boolean item_min_is_modified = false;
  private boolean item_max_is_set = false;
  private boolean item_max_is_modified = false;
  private boolean item_check_mode_is_set = false;
  private boolean item_check_mode_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "account_div=" + account_div
      + "," + "validate_type=" + validate_type
      + "," + "item_symbol_name=" + item_symbol_name
      + "," + "item_name=" +item_name
      + "," + "necessary_flag=" +necessary_flag
      + "," + "item_min=" +item_min
      + "," + "item_max=" +item_max
      + "," + "item_check_mode=" +item_check_mode
      + "}";
  }


  /** 
   * �l�����ݒ��AccOpenItemMasterParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public AccOpenItemMasterParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_div_is_modified = true;
    validate_type_is_modified = true;
    item_symbol_name_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���AccOpenItemMasterRow�I�u�W�F�N�g�̒l�𗘗p����AccOpenItemMasterParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����AccOpenItemMasterRow�I�u�W�F�N�g 
   */
  public AccOpenItemMasterParams( AccOpenItemMasterRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_div = p_row.getAccountDiv();
    account_div_is_set = p_row.getAccountDivIsSet();
    account_div_is_modified = p_row.getAccountDivIsModified();
    validate_type = p_row.getValidateType();
    validate_type_is_set = p_row.getValidateTypeIsSet();
    validate_type_is_modified = p_row.getValidateTypeIsModified();
    item_symbol_name = p_row.getItemSymbolName();
    item_symbol_name_is_set = p_row.getItemSymbolNameIsSet();
    item_symbol_name_is_modified = p_row.getItemSymbolNameIsModified();
    item_name = p_row.getItemName();
    item_name_is_set = p_row.getItemNameIsSet();
    item_name_is_modified = p_row.getItemNameIsModified();
    necessary_flag = p_row.getNecessaryFlag();
    necessary_flag_is_set = p_row.getNecessaryFlagIsSet();
    necessary_flag_is_modified = p_row.getNecessaryFlagIsModified();
    if ( !p_row.getItemMinIsNull() )
      item_min = new Integer( p_row.getItemMin() );
    item_min_is_set = p_row.getItemMinIsSet();
    item_min_is_modified = p_row.getItemMinIsModified();
    if ( !p_row.getItemMaxIsNull() )
      item_max = new Integer( p_row.getItemMax() );
    item_max_is_set = p_row.getItemMaxIsSet();
    item_max_is_modified = p_row.getItemMaxIsModified();
    item_check_mode = p_row.getItemCheckMode();
    item_check_mode_is_set = p_row.getItemCheckModeIsSet();
    item_check_mode_is_modified = p_row.getItemCheckModeIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      item_name_is_set = true;
      item_name_is_modified = true;
      necessary_flag_is_set = true;
      necessary_flag_is_modified = true;
      item_min_is_set = true;
      item_min_is_modified = true;
      item_max_is_set = true;
      item_max_is_modified = true;
      item_check_mode_is_set = true;
      item_check_mode_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AccOpenItemMasterRow ) )
       return false;
    return fieldsEqual( (AccOpenItemMasterRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�AccOpenItemMasterRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( AccOpenItemMasterRow row )
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
    if ( account_div == null ) {
      if ( row.getAccountDiv() != null )
        return false;
    } else if ( !account_div.equals( row.getAccountDiv() ) ) {
        return false;
    }
    if ( validate_type == null ) {
      if ( row.getValidateType() != null )
        return false;
    } else if ( !validate_type.equals( row.getValidateType() ) ) {
        return false;
    }
    if ( item_symbol_name == null ) {
      if ( row.getItemSymbolName() != null )
        return false;
    } else if ( !item_symbol_name.equals( row.getItemSymbolName() ) ) {
        return false;
    }
    if ( item_name == null ) {
      if ( row.getItemName() != null )
        return false;
    } else if ( !item_name.equals( row.getItemName() ) ) {
        return false;
    }
    if ( necessary_flag == null ) {
      if ( row.getNecessaryFlag() != null )
        return false;
    } else if ( !necessary_flag.equals( row.getNecessaryFlag() ) ) {
        return false;
    }
    if ( item_min == null ) {
      if ( !row.getItemMinIsNull() )
        return false;
    } else if ( row.getItemMinIsNull() || ( item_min.intValue() != row.getItemMin() ) ) {
        return false;
    }
    if ( item_max == null ) {
      if ( !row.getItemMaxIsNull() )
        return false;
    } else if ( row.getItemMaxIsNull() || ( item_max.intValue() != row.getItemMax() ) ) {
        return false;
    }
    if ( item_check_mode == null ) {
      if ( row.getItemCheckMode() != null )
        return false;
    } else if ( !item_check_mode.equals( row.getItemCheckMode() ) ) {
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
        + (account_div!=null? account_div.hashCode(): 0) 
        + (validate_type!=null? validate_type.hashCode(): 0) 
        + (item_symbol_name!=null? item_symbol_name.hashCode(): 0) 
        + (item_name!=null? item_name.hashCode(): 0) 
        + (necessary_flag!=null? necessary_flag.hashCode(): 0) 
        + (item_min!=null? item_min.hashCode(): 0) 
        + (item_max!=null? item_max.hashCode(): 0) 
        + (item_check_mode!=null? item_check_mode.hashCode(): 0) 
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
		map.put("account_div",account_div);
		map.put("validate_type",validate_type);
		map.put("item_symbol_name",item_symbol_name);
		if ( item_name != null )
			map.put("item_name",item_name);
		if ( necessary_flag_is_set )
			map.put("necessary_flag",necessary_flag);
		if ( item_min != null )
			map.put("item_min",item_min);
		if ( item_max != null )
			map.put("item_max",item_max);
		if ( item_check_mode != null )
			map.put("item_check_mode",item_check_mode);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( item_name_is_modified )
			map.put("item_name",item_name);
		if ( necessary_flag_is_modified )
			map.put("necessary_flag",necessary_flag);
		if ( item_min_is_modified )
			map.put("item_min",item_min);
		if ( item_max_is_modified )
			map.put("item_max",item_max);
		if ( item_check_mode_is_modified )
			map.put("item_check_mode",item_check_mode);
		if (map.size() == 0) {
			map.put("item_name",item_name);
			if ( necessary_flag_is_set )
				map.put("necessary_flag",necessary_flag);
			map.put("item_min",item_min);
			map.put("item_max",item_max);
			map.put("item_check_mode",item_check_mode);
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
   * <em>account_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAccountDiv()
  {
    return account_div;
  }


  /** 
   * <em>account_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountDivIsSet() {
    return account_div_is_set;
  }


  /** 
   * <em>account_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountDivIsModified() {
    return account_div_is_modified;
  }


  /** 
   * <em>validate_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getValidateType()
  {
    return validate_type;
  }


  /** 
   * <em>validate_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getValidateTypeIsSet() {
    return validate_type_is_set;
  }


  /** 
   * <em>validate_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getValidateTypeIsModified() {
    return validate_type_is_modified;
  }


  /** 
   * <em>item_symbol_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getItemSymbolName()
  {
    return item_symbol_name;
  }


  /** 
   * <em>item_symbol_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getItemSymbolNameIsSet() {
    return item_symbol_name_is_set;
  }


  /** 
   * <em>item_symbol_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getItemSymbolNameIsModified() {
    return item_symbol_name_is_modified;
  }


  /** 
   * <em>item_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getItemName()
  {
    return item_name;
  }


  /** 
   * <em>item_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getItemNameIsSet() {
    return item_name_is_set;
  }


  /** 
   * <em>item_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getItemNameIsModified() {
    return item_name_is_modified;
  }


  /** 
   * <em>necessary_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getNecessaryFlag()
  {
    return necessary_flag;
  }


  /** 
   * <em>necessary_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNecessaryFlagIsSet() {
    return necessary_flag_is_set;
  }


  /** 
   * <em>necessary_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNecessaryFlagIsModified() {
    return necessary_flag_is_modified;
  }


  /** 
   * <em>item_min</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getItemMin()
  {
    return ( item_min==null? ((int)0): item_min.intValue() );
  }


  /** 
   * <em>item_min</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getItemMinIsNull()
  {
    return item_min == null;
  }


  /** 
   * <em>item_min</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getItemMinIsSet() {
    return item_min_is_set;
  }


  /** 
   * <em>item_min</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getItemMinIsModified() {
    return item_min_is_modified;
  }


  /** 
   * <em>item_max</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getItemMax()
  {
    return ( item_max==null? ((int)0): item_max.intValue() );
  }


  /** 
   * <em>item_max</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getItemMaxIsNull()
  {
    return item_max == null;
  }


  /** 
   * <em>item_max</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getItemMaxIsSet() {
    return item_max_is_set;
  }


  /** 
   * <em>item_max</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getItemMaxIsModified() {
    return item_max_is_modified;
  }


  /** 
   * <em>item_check_mode</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getItemCheckMode()
  {
    return item_check_mode;
  }


  /** 
   * <em>item_check_mode</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getItemCheckModeIsSet() {
    return item_check_mode_is_set;
  }


  /** 
   * <em>item_check_mode</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getItemCheckModeIsModified() {
    return item_check_mode_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AccOpenItemMasterPK(institution_code, branch_code, account_div, validate_type, item_symbol_name);
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
   * <em>account_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountDiv <em>account_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAccountDiv( String p_accountDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_div = p_accountDiv;
    account_div_is_set = true;
    account_div_is_modified = true;
  }


  /** 
   * <em>validate_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_validateType <em>validate_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setValidateType( String p_validateType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    validate_type = p_validateType;
    validate_type_is_set = true;
    validate_type_is_modified = true;
  }


  /** 
   * <em>item_symbol_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_itemSymbolName <em>item_symbol_name</em>�J�����̒l������킷30���ȉ���String�l 
   */
  public final void setItemSymbolName( String p_itemSymbolName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_symbol_name = p_itemSymbolName;
    item_symbol_name_is_set = true;
    item_symbol_name_is_modified = true;
  }


  /** 
   * <em>item_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_itemName <em>item_name</em>�J�����̒l������킷50���ȉ���String�l 
   */
  public final void setItemName( String p_itemName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_name = p_itemName;
    item_name_is_set = true;
    item_name_is_modified = true;
  }


  /** 
   * <em>necessary_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_necessaryFlag <em>necessary_flag</em>�J�����̒l������킷1���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�l 
   */
  public final void setNecessaryFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_necessaryFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    necessary_flag = p_necessaryFlag;
    necessary_flag_is_set = true;
    necessary_flag_is_modified = true;
  }


  /** 
   * <em>item_min</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_itemMin <em>item_min</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setItemMin( int p_itemMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_min = new Integer(p_itemMin);
    item_min_is_set = true;
    item_min_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>item_min</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setItemMin( Integer p_itemMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    item_min = p_itemMin;
    item_min_is_set = true;
    item_min_is_modified = true;
  }


  /** 
   * <em>item_max</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_itemMax <em>item_max</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setItemMax( int p_itemMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_max = new Integer(p_itemMax);
    item_max_is_set = true;
    item_max_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>item_max</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setItemMax( Integer p_itemMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    item_max = p_itemMax;
    item_max_is_set = true;
    item_max_is_modified = true;
  }


  /** 
   * <em>item_check_mode</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_itemCheckMode <em>item_check_mode</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setItemCheckMode( String p_itemCheckMode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_check_mode = p_itemCheckMode;
    item_check_mode_is_set = true;
    item_check_mode_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_div") ) {
                    return this.account_div;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("item_symbol_name") ) {
                    return this.item_symbol_name;
                }
                else if ( name.equals("item_name") ) {
                    return this.item_name;
                }
                else if ( name.equals("item_min") ) {
                    return this.item_min;
                }
                else if ( name.equals("item_max") ) {
                    return this.item_max;
                }
                else if ( name.equals("item_check_mode") ) {
                    return this.item_check_mode;
                }
                break;
            case 'n':
                if ( name.equals("necessary_flag") ) {
                    return this.necessary_flag;
                }
                break;
            case 'v':
                if ( name.equals("validate_type") ) {
                    return this.validate_type;
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
                if ( name.equals("account_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_div' must be String: '"+value+"' is not." );
                    this.account_div = (String) value;
                    if (this.account_div_is_set)
                        this.account_div_is_modified = true;
                    this.account_div_is_set = true;
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
                else if ( name.equals("item_symbol_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_symbol_name' must be String: '"+value+"' is not." );
                    this.item_symbol_name = (String) value;
                    if (this.item_symbol_name_is_set)
                        this.item_symbol_name_is_modified = true;
                    this.item_symbol_name_is_set = true;
                    return;
                }
                else if ( name.equals("item_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_name' must be String: '"+value+"' is not." );
                    this.item_name = (String) value;
                    if (this.item_name_is_set)
                        this.item_name_is_modified = true;
                    this.item_name_is_set = true;
                    return;
                }
                else if ( name.equals("item_min") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'item_min' must be Integer: '"+value+"' is not." );
                    this.item_min = (Integer) value;
                    if (this.item_min_is_set)
                        this.item_min_is_modified = true;
                    this.item_min_is_set = true;
                    return;
                }
                else if ( name.equals("item_max") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'item_max' must be Integer: '"+value+"' is not." );
                    this.item_max = (Integer) value;
                    if (this.item_max_is_set)
                        this.item_max_is_modified = true;
                    this.item_max_is_set = true;
                    return;
                }
                else if ( name.equals("item_check_mode") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_check_mode' must be String: '"+value+"' is not." );
                    this.item_check_mode = (String) value;
                    if (this.item_check_mode_is_set)
                        this.item_check_mode_is_modified = true;
                    this.item_check_mode_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("necessary_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'necessary_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.necessary_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.necessary_flag_is_set)
                        this.necessary_flag_is_modified = true;
                    this.necessary_flag_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("validate_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'validate_type' must be String: '"+value+"' is not." );
                    this.validate_type = (String) value;
                    if (this.validate_type_is_set)
                        this.validate_type_is_modified = true;
                    this.validate_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
