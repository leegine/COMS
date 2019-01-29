head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.16.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenVoucherItemParams.java;


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
 * acc_open_voucher_item�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link AccOpenVoucherItemRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link AccOpenVoucherItemParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link AccOpenVoucherItemParams}��{@@link AccOpenVoucherItemRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenVoucherItemPK 
 * @@see AccOpenVoucherItemRow 
 */
public class AccOpenVoucherItemParams extends Params implements AccOpenVoucherItemRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "acc_open_voucher_item";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = AccOpenVoucherItemRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return AccOpenVoucherItemRow.TYPE;
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
   * <em>request_code</em>�J�����̒l 
   */
  public  String  request_code;

  /** 
   * <em>serial_no</em>�J�����̒l 
   */
  public  String  serial_no;

  /** 
   * <em>output_item_symbol_name</em>�J�����̒l 
   */
  public  String  output_item_symbol_name;

  /** 
   * <em>edit_way_div</em>�J�����̒l 
   */
  public  String  edit_way_div;

  /** 
   * <em>fixed_value</em>�J�����̒l 
   */
  public  String  fixed_value;

  /** 
   * <em>input_item_symbol_name1</em>�J�����̒l 
   */
  public  String  input_item_symbol_name1;

  /** 
   * <em>input_item_symbol_name2</em>�J�����̒l 
   */
  public  String  input_item_symbol_name2;

  /** 
   * <em>input_item_symbol_name3</em>�J�����̒l 
   */
  public  String  input_item_symbol_name3;

  /** 
   * <em>cat_delimitter</em>�J�����̒l 
   */
  public  String  cat_delimitter;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_div_is_set = false;
  private boolean account_div_is_modified = false;
  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean serial_no_is_set = false;
  private boolean serial_no_is_modified = false;
  private boolean output_item_symbol_name_is_set = false;
  private boolean output_item_symbol_name_is_modified = false;
  private boolean edit_way_div_is_set = false;
  private boolean edit_way_div_is_modified = false;
  private boolean fixed_value_is_set = false;
  private boolean fixed_value_is_modified = false;
  private boolean input_item_symbol_name1_is_set = false;
  private boolean input_item_symbol_name1_is_modified = false;
  private boolean input_item_symbol_name2_is_set = false;
  private boolean input_item_symbol_name2_is_modified = false;
  private boolean input_item_symbol_name3_is_set = false;
  private boolean input_item_symbol_name3_is_modified = false;
  private boolean cat_delimitter_is_set = false;
  private boolean cat_delimitter_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "account_div=" + account_div
      + "," + "request_code=" + request_code
      + "," + "serial_no=" + serial_no
      + "," + "output_item_symbol_name=" + output_item_symbol_name
      + "," + "edit_way_div=" +edit_way_div
      + "," + "fixed_value=" +fixed_value
      + "," + "input_item_symbol_name1=" +input_item_symbol_name1
      + "," + "input_item_symbol_name2=" +input_item_symbol_name2
      + "," + "input_item_symbol_name3=" +input_item_symbol_name3
      + "," + "cat_delimitter=" +cat_delimitter
      + "}";
  }


  /** 
   * �l�����ݒ��AccOpenVoucherItemParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public AccOpenVoucherItemParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_div_is_modified = true;
    request_code_is_modified = true;
    serial_no_is_modified = true;
    output_item_symbol_name_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���AccOpenVoucherItemRow�I�u�W�F�N�g�̒l�𗘗p����AccOpenVoucherItemParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����AccOpenVoucherItemRow�I�u�W�F�N�g 
   */
  public AccOpenVoucherItemParams( AccOpenVoucherItemRow p_row )
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
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    serial_no = p_row.getSerialNo();
    serial_no_is_set = p_row.getSerialNoIsSet();
    serial_no_is_modified = p_row.getSerialNoIsModified();
    output_item_symbol_name = p_row.getOutputItemSymbolName();
    output_item_symbol_name_is_set = p_row.getOutputItemSymbolNameIsSet();
    output_item_symbol_name_is_modified = p_row.getOutputItemSymbolNameIsModified();
    edit_way_div = p_row.getEditWayDiv();
    edit_way_div_is_set = p_row.getEditWayDivIsSet();
    edit_way_div_is_modified = p_row.getEditWayDivIsModified();
    fixed_value = p_row.getFixedValue();
    fixed_value_is_set = p_row.getFixedValueIsSet();
    fixed_value_is_modified = p_row.getFixedValueIsModified();
    input_item_symbol_name1 = p_row.getInputItemSymbolName1();
    input_item_symbol_name1_is_set = p_row.getInputItemSymbolName1IsSet();
    input_item_symbol_name1_is_modified = p_row.getInputItemSymbolName1IsModified();
    input_item_symbol_name2 = p_row.getInputItemSymbolName2();
    input_item_symbol_name2_is_set = p_row.getInputItemSymbolName2IsSet();
    input_item_symbol_name2_is_modified = p_row.getInputItemSymbolName2IsModified();
    input_item_symbol_name3 = p_row.getInputItemSymbolName3();
    input_item_symbol_name3_is_set = p_row.getInputItemSymbolName3IsSet();
    input_item_symbol_name3_is_modified = p_row.getInputItemSymbolName3IsModified();
    cat_delimitter = p_row.getCatDelimitter();
    cat_delimitter_is_set = p_row.getCatDelimitterIsSet();
    cat_delimitter_is_modified = p_row.getCatDelimitterIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      edit_way_div_is_set = true;
      edit_way_div_is_modified = true;
      fixed_value_is_set = true;
      fixed_value_is_modified = true;
      input_item_symbol_name1_is_set = true;
      input_item_symbol_name1_is_modified = true;
      input_item_symbol_name2_is_set = true;
      input_item_symbol_name2_is_modified = true;
      input_item_symbol_name3_is_set = true;
      input_item_symbol_name3_is_modified = true;
      cat_delimitter_is_set = true;
      cat_delimitter_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AccOpenVoucherItemRow ) )
       return false;
    return fieldsEqual( (AccOpenVoucherItemRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�AccOpenVoucherItemRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( AccOpenVoucherItemRow row )
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
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
        return false;
    }
    if ( serial_no == null ) {
      if ( row.getSerialNo() != null )
        return false;
    } else if ( !serial_no.equals( row.getSerialNo() ) ) {
        return false;
    }
    if ( output_item_symbol_name == null ) {
      if ( row.getOutputItemSymbolName() != null )
        return false;
    } else if ( !output_item_symbol_name.equals( row.getOutputItemSymbolName() ) ) {
        return false;
    }
    if ( edit_way_div == null ) {
      if ( row.getEditWayDiv() != null )
        return false;
    } else if ( !edit_way_div.equals( row.getEditWayDiv() ) ) {
        return false;
    }
    if ( fixed_value == null ) {
      if ( row.getFixedValue() != null )
        return false;
    } else if ( !fixed_value.equals( row.getFixedValue() ) ) {
        return false;
    }
    if ( input_item_symbol_name1 == null ) {
      if ( row.getInputItemSymbolName1() != null )
        return false;
    } else if ( !input_item_symbol_name1.equals( row.getInputItemSymbolName1() ) ) {
        return false;
    }
    if ( input_item_symbol_name2 == null ) {
      if ( row.getInputItemSymbolName2() != null )
        return false;
    } else if ( !input_item_symbol_name2.equals( row.getInputItemSymbolName2() ) ) {
        return false;
    }
    if ( input_item_symbol_name3 == null ) {
      if ( row.getInputItemSymbolName3() != null )
        return false;
    } else if ( !input_item_symbol_name3.equals( row.getInputItemSymbolName3() ) ) {
        return false;
    }
    if ( cat_delimitter == null ) {
      if ( row.getCatDelimitter() != null )
        return false;
    } else if ( !cat_delimitter.equals( row.getCatDelimitter() ) ) {
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
        + (request_code!=null? request_code.hashCode(): 0) 
        + (serial_no!=null? serial_no.hashCode(): 0) 
        + (output_item_symbol_name!=null? output_item_symbol_name.hashCode(): 0) 
        + (edit_way_div!=null? edit_way_div.hashCode(): 0) 
        + (fixed_value!=null? fixed_value.hashCode(): 0) 
        + (input_item_symbol_name1!=null? input_item_symbol_name1.hashCode(): 0) 
        + (input_item_symbol_name2!=null? input_item_symbol_name2.hashCode(): 0) 
        + (input_item_symbol_name3!=null? input_item_symbol_name3.hashCode(): 0) 
        + (cat_delimitter!=null? cat_delimitter.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !edit_way_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'edit_way_div' must be set before inserting.");
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
		map.put("request_code",request_code);
		map.put("serial_no",serial_no);
		map.put("output_item_symbol_name",output_item_symbol_name);
		map.put("edit_way_div",edit_way_div);
		if ( fixed_value != null )
			map.put("fixed_value",fixed_value);
		if ( input_item_symbol_name1 != null )
			map.put("input_item_symbol_name1",input_item_symbol_name1);
		if ( input_item_symbol_name2 != null )
			map.put("input_item_symbol_name2",input_item_symbol_name2);
		if ( input_item_symbol_name3 != null )
			map.put("input_item_symbol_name3",input_item_symbol_name3);
		if ( cat_delimitter != null )
			map.put("cat_delimitter",cat_delimitter);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( edit_way_div_is_modified )
			map.put("edit_way_div",edit_way_div);
		if ( fixed_value_is_modified )
			map.put("fixed_value",fixed_value);
		if ( input_item_symbol_name1_is_modified )
			map.put("input_item_symbol_name1",input_item_symbol_name1);
		if ( input_item_symbol_name2_is_modified )
			map.put("input_item_symbol_name2",input_item_symbol_name2);
		if ( input_item_symbol_name3_is_modified )
			map.put("input_item_symbol_name3",input_item_symbol_name3);
		if ( cat_delimitter_is_modified )
			map.put("cat_delimitter",cat_delimitter);
		if (map.size() == 0) {
			if ( edit_way_div_is_set )
				map.put("edit_way_div",edit_way_div);
			map.put("fixed_value",fixed_value);
			map.put("input_item_symbol_name1",input_item_symbol_name1);
			map.put("input_item_symbol_name2",input_item_symbol_name2);
			map.put("input_item_symbol_name3",input_item_symbol_name3);
			map.put("cat_delimitter",cat_delimitter);
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
   * <em>request_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRequestCode()
  {
    return request_code;
  }


  /** 
   * <em>request_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRequestCodeIsSet() {
    return request_code_is_set;
  }


  /** 
   * <em>request_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRequestCodeIsModified() {
    return request_code_is_modified;
  }


  /** 
   * <em>serial_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSerialNo()
  {
    return serial_no;
  }


  /** 
   * <em>serial_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSerialNoIsSet() {
    return serial_no_is_set;
  }


  /** 
   * <em>serial_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSerialNoIsModified() {
    return serial_no_is_modified;
  }


  /** 
   * <em>output_item_symbol_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOutputItemSymbolName()
  {
    return output_item_symbol_name;
  }


  /** 
   * <em>output_item_symbol_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOutputItemSymbolNameIsSet() {
    return output_item_symbol_name_is_set;
  }


  /** 
   * <em>output_item_symbol_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOutputItemSymbolNameIsModified() {
    return output_item_symbol_name_is_modified;
  }


  /** 
   * <em>edit_way_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getEditWayDiv()
  {
    return edit_way_div;
  }


  /** 
   * <em>edit_way_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEditWayDivIsSet() {
    return edit_way_div_is_set;
  }


  /** 
   * <em>edit_way_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEditWayDivIsModified() {
    return edit_way_div_is_modified;
  }


  /** 
   * <em>fixed_value</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFixedValue()
  {
    return fixed_value;
  }


  /** 
   * <em>fixed_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFixedValueIsSet() {
    return fixed_value_is_set;
  }


  /** 
   * <em>fixed_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFixedValueIsModified() {
    return fixed_value_is_modified;
  }


  /** 
   * <em>input_item_symbol_name1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getInputItemSymbolName1()
  {
    return input_item_symbol_name1;
  }


  /** 
   * <em>input_item_symbol_name1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputItemSymbolName1IsSet() {
    return input_item_symbol_name1_is_set;
  }


  /** 
   * <em>input_item_symbol_name1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputItemSymbolName1IsModified() {
    return input_item_symbol_name1_is_modified;
  }


  /** 
   * <em>input_item_symbol_name2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getInputItemSymbolName2()
  {
    return input_item_symbol_name2;
  }


  /** 
   * <em>input_item_symbol_name2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputItemSymbolName2IsSet() {
    return input_item_symbol_name2_is_set;
  }


  /** 
   * <em>input_item_symbol_name2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputItemSymbolName2IsModified() {
    return input_item_symbol_name2_is_modified;
  }


  /** 
   * <em>input_item_symbol_name3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getInputItemSymbolName3()
  {
    return input_item_symbol_name3;
  }


  /** 
   * <em>input_item_symbol_name3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputItemSymbolName3IsSet() {
    return input_item_symbol_name3_is_set;
  }


  /** 
   * <em>input_item_symbol_name3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputItemSymbolName3IsModified() {
    return input_item_symbol_name3_is_modified;
  }


  /** 
   * <em>cat_delimitter</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCatDelimitter()
  {
    return cat_delimitter;
  }


  /** 
   * <em>cat_delimitter</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCatDelimitterIsSet() {
    return cat_delimitter_is_set;
  }


  /** 
   * <em>cat_delimitter</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCatDelimitterIsModified() {
    return cat_delimitter_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AccOpenVoucherItemPK(institution_code, branch_code, account_div, request_code, serial_no, output_item_symbol_name);
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
   * <em>request_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_requestCode <em>request_code</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setRequestCode( String p_requestCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_code = p_requestCode;
    request_code_is_set = true;
    request_code_is_modified = true;
  }


  /** 
   * <em>serial_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_serialNo <em>serial_no</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setSerialNo( String p_serialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    serial_no = p_serialNo;
    serial_no_is_set = true;
    serial_no_is_modified = true;
  }


  /** 
   * <em>output_item_symbol_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_outputItemSymbolName <em>output_item_symbol_name</em>�J�����̒l������킷30���ȉ���String�l 
   */
  public final void setOutputItemSymbolName( String p_outputItemSymbolName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    output_item_symbol_name = p_outputItemSymbolName;
    output_item_symbol_name_is_set = true;
    output_item_symbol_name_is_modified = true;
  }


  /** 
   * <em>edit_way_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_editWayDiv <em>edit_way_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setEditWayDiv( String p_editWayDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    edit_way_div = p_editWayDiv;
    edit_way_div_is_set = true;
    edit_way_div_is_modified = true;
  }


  /** 
   * <em>fixed_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_fixedValue <em>fixed_value</em>�J�����̒l������킷128���ȉ���String�l 
   */
  public final void setFixedValue( String p_fixedValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fixed_value = p_fixedValue;
    fixed_value_is_set = true;
    fixed_value_is_modified = true;
  }


  /** 
   * <em>input_item_symbol_name1</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_inputItemSymbolName1 <em>input_item_symbol_name1</em>�J�����̒l������킷30���ȉ���String�l 
   */
  public final void setInputItemSymbolName1( String p_inputItemSymbolName1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    input_item_symbol_name1 = p_inputItemSymbolName1;
    input_item_symbol_name1_is_set = true;
    input_item_symbol_name1_is_modified = true;
  }


  /** 
   * <em>input_item_symbol_name2</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_inputItemSymbolName2 <em>input_item_symbol_name2</em>�J�����̒l������킷30���ȉ���String�l 
   */
  public final void setInputItemSymbolName2( String p_inputItemSymbolName2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    input_item_symbol_name2 = p_inputItemSymbolName2;
    input_item_symbol_name2_is_set = true;
    input_item_symbol_name2_is_modified = true;
  }


  /** 
   * <em>input_item_symbol_name3</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_inputItemSymbolName3 <em>input_item_symbol_name3</em>�J�����̒l������킷30���ȉ���String�l 
   */
  public final void setInputItemSymbolName3( String p_inputItemSymbolName3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    input_item_symbol_name3 = p_inputItemSymbolName3;
    input_item_symbol_name3_is_set = true;
    input_item_symbol_name3_is_modified = true;
  }


  /** 
   * <em>cat_delimitter</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_catDelimitter <em>cat_delimitter</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setCatDelimitter( String p_catDelimitter )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cat_delimitter = p_catDelimitter;
    cat_delimitter_is_set = true;
    cat_delimitter_is_modified = true;
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
            case 'c':
                if ( name.equals("cat_delimitter") ) {
                    return this.cat_delimitter;
                }
                break;
            case 'e':
                if ( name.equals("edit_way_div") ) {
                    return this.edit_way_div;
                }
                break;
            case 'f':
                if ( name.equals("fixed_value") ) {
                    return this.fixed_value;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("input_item_symbol_name1") ) {
                    return this.input_item_symbol_name1;
                }
                else if ( name.equals("input_item_symbol_name2") ) {
                    return this.input_item_symbol_name2;
                }
                else if ( name.equals("input_item_symbol_name3") ) {
                    return this.input_item_symbol_name3;
                }
                break;
            case 'o':
                if ( name.equals("output_item_symbol_name") ) {
                    return this.output_item_symbol_name;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                break;
            case 's':
                if ( name.equals("serial_no") ) {
                    return this.serial_no;
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
            case 'c':
                if ( name.equals("cat_delimitter") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cat_delimitter' must be String: '"+value+"' is not." );
                    this.cat_delimitter = (String) value;
                    if (this.cat_delimitter_is_set)
                        this.cat_delimitter_is_modified = true;
                    this.cat_delimitter_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("edit_way_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'edit_way_div' must be String: '"+value+"' is not." );
                    this.edit_way_div = (String) value;
                    if (this.edit_way_div_is_set)
                        this.edit_way_div_is_modified = true;
                    this.edit_way_div_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fixed_value") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fixed_value' must be String: '"+value+"' is not." );
                    this.fixed_value = (String) value;
                    if (this.fixed_value_is_set)
                        this.fixed_value_is_modified = true;
                    this.fixed_value_is_set = true;
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
                else if ( name.equals("input_item_symbol_name1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'input_item_symbol_name1' must be String: '"+value+"' is not." );
                    this.input_item_symbol_name1 = (String) value;
                    if (this.input_item_symbol_name1_is_set)
                        this.input_item_symbol_name1_is_modified = true;
                    this.input_item_symbol_name1_is_set = true;
                    return;
                }
                else if ( name.equals("input_item_symbol_name2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'input_item_symbol_name2' must be String: '"+value+"' is not." );
                    this.input_item_symbol_name2 = (String) value;
                    if (this.input_item_symbol_name2_is_set)
                        this.input_item_symbol_name2_is_modified = true;
                    this.input_item_symbol_name2_is_set = true;
                    return;
                }
                else if ( name.equals("input_item_symbol_name3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'input_item_symbol_name3' must be String: '"+value+"' is not." );
                    this.input_item_symbol_name3 = (String) value;
                    if (this.input_item_symbol_name3_is_set)
                        this.input_item_symbol_name3_is_modified = true;
                    this.input_item_symbol_name3_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("output_item_symbol_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'output_item_symbol_name' must be String: '"+value+"' is not." );
                    this.output_item_symbol_name = (String) value;
                    if (this.output_item_symbol_name_is_set)
                        this.output_item_symbol_name_is_modified = true;
                    this.output_item_symbol_name_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_code' must be String: '"+value+"' is not." );
                    this.request_code = (String) value;
                    if (this.request_code_is_set)
                        this.request_code_is_modified = true;
                    this.request_code_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("serial_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'serial_no' must be String: '"+value+"' is not." );
                    this.serial_no = (String) value;
                    if (this.serial_no_is_set)
                        this.serial_no_is_modified = true;
                    this.serial_no_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
