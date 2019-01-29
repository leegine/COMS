head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenDlFormMasterParams.java;


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
 * acc_open_dl_form_master�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link AccOpenDlFormMasterRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link AccOpenDlFormMasterParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link AccOpenDlFormMasterParams}��{@@link AccOpenDlFormMasterRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenDlFormMasterPK 
 * @@see AccOpenDlFormMasterRow 
 */
public class AccOpenDlFormMasterParams extends Params implements AccOpenDlFormMasterRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "acc_open_dl_form_master";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = AccOpenDlFormMasterRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return AccOpenDlFormMasterRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>account_div</em>�J�����̒l 
   */
  public  String  account_div;

  /** 
   * <em>column_number</em>�J�����̒l 
   */
  public  int  column_number;

  /** 
   * <em>column_label</em>�J�����̒l 
   */
  public  String  column_label;

  /** 
   * <em>column_type</em>�J�����̒l 
   */
  public  int  column_type;

  /** 
   * <em>date_format</em>�J�����̒l 
   */
  public  String  date_format;

  /** 
   * <em>input_item_symbol_name</em>�J�����̒l 
   */
  public  String  input_item_symbol_name;

  /** 
   * <em>cat_delimitter</em>�J�����̒l 
   */
  public  String  cat_delimitter;

  /** 
   * <em>section_number</em>�J�����̒l 
   */
  public  Integer  section_number;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean account_div_is_set = false;
  private boolean account_div_is_modified = false;
  private boolean column_number_is_set = false;
  private boolean column_number_is_modified = false;
  private boolean column_label_is_set = false;
  private boolean column_label_is_modified = false;
  private boolean column_type_is_set = false;
  private boolean column_type_is_modified = false;
  private boolean date_format_is_set = false;
  private boolean date_format_is_modified = false;
  private boolean input_item_symbol_name_is_set = false;
  private boolean input_item_symbol_name_is_modified = false;
  private boolean cat_delimitter_is_set = false;
  private boolean cat_delimitter_is_modified = false;
  private boolean section_number_is_set = false;
  private boolean section_number_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "account_div=" + account_div
      + "," + "column_number=" + column_number
      + "," + "column_label=" +column_label
      + "," + "column_type=" +column_type
      + "," + "date_format=" +date_format
      + "," + "input_item_symbol_name=" +input_item_symbol_name
      + "," + "cat_delimitter=" +cat_delimitter
      + "," + "section_number=" +section_number
      + "}";
  }


  /** 
   * �l�����ݒ��AccOpenDlFormMasterParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public AccOpenDlFormMasterParams() {
    institution_code_is_modified = true;
    account_div_is_modified = true;
    column_number_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���AccOpenDlFormMasterRow�I�u�W�F�N�g�̒l�𗘗p����AccOpenDlFormMasterParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����AccOpenDlFormMasterRow�I�u�W�F�N�g 
   */
  public AccOpenDlFormMasterParams( AccOpenDlFormMasterRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    account_div = p_row.getAccountDiv();
    account_div_is_set = p_row.getAccountDivIsSet();
    account_div_is_modified = p_row.getAccountDivIsModified();
    column_number = p_row.getColumnNumber();
    column_number_is_set = p_row.getColumnNumberIsSet();
    column_number_is_modified = p_row.getColumnNumberIsModified();
    column_label = p_row.getColumnLabel();
    column_label_is_set = p_row.getColumnLabelIsSet();
    column_label_is_modified = p_row.getColumnLabelIsModified();
    column_type = p_row.getColumnType();
    column_type_is_set = p_row.getColumnTypeIsSet();
    column_type_is_modified = p_row.getColumnTypeIsModified();
    date_format = p_row.getDateFormat();
    date_format_is_set = p_row.getDateFormatIsSet();
    date_format_is_modified = p_row.getDateFormatIsModified();
    input_item_symbol_name = p_row.getInputItemSymbolName();
    input_item_symbol_name_is_set = p_row.getInputItemSymbolNameIsSet();
    input_item_symbol_name_is_modified = p_row.getInputItemSymbolNameIsModified();
    cat_delimitter = p_row.getCatDelimitter();
    cat_delimitter_is_set = p_row.getCatDelimitterIsSet();
    cat_delimitter_is_modified = p_row.getCatDelimitterIsModified();
    if ( !p_row.getSectionNumberIsNull() )
      section_number = new Integer( p_row.getSectionNumber() );
    section_number_is_set = p_row.getSectionNumberIsSet();
    section_number_is_modified = p_row.getSectionNumberIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      column_label_is_set = true;
      column_label_is_modified = true;
      column_type_is_set = true;
      column_type_is_modified = true;
      date_format_is_set = true;
      date_format_is_modified = true;
      input_item_symbol_name_is_set = true;
      input_item_symbol_name_is_modified = true;
      cat_delimitter_is_set = true;
      cat_delimitter_is_modified = true;
      section_number_is_set = true;
      section_number_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AccOpenDlFormMasterRow ) )
       return false;
    return fieldsEqual( (AccOpenDlFormMasterRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�AccOpenDlFormMasterRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( AccOpenDlFormMasterRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( account_div == null ) {
      if ( row.getAccountDiv() != null )
        return false;
    } else if ( !account_div.equals( row.getAccountDiv() ) ) {
        return false;
    }
    if ( column_number != row.getColumnNumber() )
      return false;
    if ( column_label == null ) {
      if ( row.getColumnLabel() != null )
        return false;
    } else if ( !column_label.equals( row.getColumnLabel() ) ) {
        return false;
    }
    if ( column_type != row.getColumnType() )
      return false;
    if ( date_format == null ) {
      if ( row.getDateFormat() != null )
        return false;
    } else if ( !date_format.equals( row.getDateFormat() ) ) {
        return false;
    }
    if ( input_item_symbol_name == null ) {
      if ( row.getInputItemSymbolName() != null )
        return false;
    } else if ( !input_item_symbol_name.equals( row.getInputItemSymbolName() ) ) {
        return false;
    }
    if ( cat_delimitter == null ) {
      if ( row.getCatDelimitter() != null )
        return false;
    } else if ( !cat_delimitter.equals( row.getCatDelimitter() ) ) {
        return false;
    }
    if ( section_number == null ) {
      if ( !row.getSectionNumberIsNull() )
        return false;
    } else if ( row.getSectionNumberIsNull() || ( section_number.intValue() != row.getSectionNumber() ) ) {
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
        + (account_div!=null? account_div.hashCode(): 0) 
        + ((int) column_number)
        + (column_label!=null? column_label.hashCode(): 0) 
        + ((int) column_type)
        + (date_format!=null? date_format.hashCode(): 0) 
        + (input_item_symbol_name!=null? input_item_symbol_name.hashCode(): 0) 
        + (cat_delimitter!=null? cat_delimitter.hashCode(): 0) 
        + (section_number!=null? section_number.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !column_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'column_type' must be set before inserting.");
		if ( !input_item_symbol_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'input_item_symbol_name' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("account_div",account_div);
		map.put("column_number",new Integer(column_number));
		if ( column_label != null )
			map.put("column_label",column_label);
		map.put("column_type",new Integer(column_type));
		if ( date_format != null )
			map.put("date_format",date_format);
		map.put("input_item_symbol_name",input_item_symbol_name);
		if ( cat_delimitter != null )
			map.put("cat_delimitter",cat_delimitter);
		if ( section_number != null )
			map.put("section_number",section_number);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( column_label_is_modified )
			map.put("column_label",column_label);
		if ( column_type_is_modified )
			map.put("column_type",new Integer(column_type));
		if ( date_format_is_modified )
			map.put("date_format",date_format);
		if ( input_item_symbol_name_is_modified )
			map.put("input_item_symbol_name",input_item_symbol_name);
		if ( cat_delimitter_is_modified )
			map.put("cat_delimitter",cat_delimitter);
		if ( section_number_is_modified )
			map.put("section_number",section_number);
		if (map.size() == 0) {
			map.put("column_label",column_label);
			if ( column_type_is_set )
				map.put("column_type",new Integer(column_type));
			map.put("date_format",date_format);
			if ( input_item_symbol_name_is_set )
				map.put("input_item_symbol_name",input_item_symbol_name);
			map.put("cat_delimitter",cat_delimitter);
			map.put("section_number",section_number);
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
   * <em>column_number</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getColumnNumber()
  {
    return column_number;
  }


  /** 
   * <em>column_number</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getColumnNumberIsSet() {
    return column_number_is_set;
  }


  /** 
   * <em>column_number</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getColumnNumberIsModified() {
    return column_number_is_modified;
  }


  /** 
   * <em>column_label</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getColumnLabel()
  {
    return column_label;
  }


  /** 
   * <em>column_label</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getColumnLabelIsSet() {
    return column_label_is_set;
  }


  /** 
   * <em>column_label</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getColumnLabelIsModified() {
    return column_label_is_modified;
  }


  /** 
   * <em>column_type</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getColumnType()
  {
    return column_type;
  }


  /** 
   * <em>column_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getColumnTypeIsSet() {
    return column_type_is_set;
  }


  /** 
   * <em>column_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getColumnTypeIsModified() {
    return column_type_is_modified;
  }


  /** 
   * <em>date_format</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getDateFormat()
  {
    return date_format;
  }


  /** 
   * <em>date_format</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDateFormatIsSet() {
    return date_format_is_set;
  }


  /** 
   * <em>date_format</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDateFormatIsModified() {
    return date_format_is_modified;
  }


  /** 
   * <em>input_item_symbol_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getInputItemSymbolName()
  {
    return input_item_symbol_name;
  }


  /** 
   * <em>input_item_symbol_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputItemSymbolNameIsSet() {
    return input_item_symbol_name_is_set;
  }


  /** 
   * <em>input_item_symbol_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputItemSymbolNameIsModified() {
    return input_item_symbol_name_is_modified;
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
   * <em>section_number</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getSectionNumber()
  {
    return ( section_number==null? ((int)0): section_number.intValue() );
  }


  /** 
   * <em>section_number</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSectionNumberIsNull()
  {
    return section_number == null;
  }


  /** 
   * <em>section_number</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSectionNumberIsSet() {
    return section_number_is_set;
  }


  /** 
   * <em>section_number</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSectionNumberIsModified() {
    return section_number_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AccOpenDlFormMasterPK(institution_code, account_div, column_number);
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
   * <em>column_number</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_columnNumber <em>column_number</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setColumnNumber( int p_columnNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    column_number = p_columnNumber;
    column_number_is_set = true;
    column_number_is_modified = true;
  }


  /** 
   * <em>column_label</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_columnLabel <em>column_label</em>�J�����̒l������킷64���ȉ���String�l 
   */
  public final void setColumnLabel( String p_columnLabel )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    column_label = p_columnLabel;
    column_label_is_set = true;
    column_label_is_modified = true;
  }


  /** 
   * <em>column_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_columnType <em>column_type</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setColumnType( int p_columnType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    column_type = p_columnType;
    column_type_is_set = true;
    column_type_is_modified = true;
  }


  /** 
   * <em>date_format</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_dateFormat <em>date_format</em>�J�����̒l������킷30���ȉ���String�l 
   */
  public final void setDateFormat( String p_dateFormat )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    date_format = p_dateFormat;
    date_format_is_set = true;
    date_format_is_modified = true;
  }


  /** 
   * <em>input_item_symbol_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_inputItemSymbolName <em>input_item_symbol_name</em>�J�����̒l������킷30���ȉ���String�l 
   */
  public final void setInputItemSymbolName( String p_inputItemSymbolName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    input_item_symbol_name = p_inputItemSymbolName;
    input_item_symbol_name_is_set = true;
    input_item_symbol_name_is_modified = true;
  }


  /** 
   * <em>cat_delimitter</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_catDelimitter <em>cat_delimitter</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setCatDelimitter( String p_catDelimitter )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cat_delimitter = p_catDelimitter;
    cat_delimitter_is_set = true;
    cat_delimitter_is_modified = true;
  }


  /** 
   * <em>section_number</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sectionNumber <em>section_number</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setSectionNumber( int p_sectionNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    section_number = new Integer(p_sectionNumber);
    section_number_is_set = true;
    section_number_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>section_number</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSectionNumber( Integer p_sectionNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    section_number = p_sectionNumber;
    section_number_is_set = true;
    section_number_is_modified = true;
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
            case 'c':
                if ( name.equals("column_number") ) {
                    return new Integer( column_number );
                }
                else if ( name.equals("column_label") ) {
                    return this.column_label;
                }
                else if ( name.equals("column_type") ) {
                    return new Integer( column_type );
                }
                else if ( name.equals("cat_delimitter") ) {
                    return this.cat_delimitter;
                }
                break;
            case 'd':
                if ( name.equals("date_format") ) {
                    return this.date_format;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("input_item_symbol_name") ) {
                    return this.input_item_symbol_name;
                }
                break;
            case 's':
                if ( name.equals("section_number") ) {
                    return this.section_number;
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
            case 'c':
                if ( name.equals("column_number") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'column_number' must be Integer: '"+value+"' is not." );
                    this.column_number = ((Integer) value).intValue();
                    if (this.column_number_is_set)
                        this.column_number_is_modified = true;
                    this.column_number_is_set = true;
                    return;
                }
                else if ( name.equals("column_label") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'column_label' must be String: '"+value+"' is not." );
                    this.column_label = (String) value;
                    if (this.column_label_is_set)
                        this.column_label_is_modified = true;
                    this.column_label_is_set = true;
                    return;
                }
                else if ( name.equals("column_type") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'column_type' must be Integer: '"+value+"' is not." );
                    this.column_type = ((Integer) value).intValue();
                    if (this.column_type_is_set)
                        this.column_type_is_modified = true;
                    this.column_type_is_set = true;
                    return;
                }
                else if ( name.equals("cat_delimitter") ) {
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
            case 'd':
                if ( name.equals("date_format") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'date_format' must be String: '"+value+"' is not." );
                    this.date_format = (String) value;
                    if (this.date_format_is_set)
                        this.date_format_is_modified = true;
                    this.date_format_is_set = true;
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
                else if ( name.equals("input_item_symbol_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'input_item_symbol_name' must be String: '"+value+"' is not." );
                    this.input_item_symbol_name = (String) value;
                    if (this.input_item_symbol_name_is_set)
                        this.input_item_symbol_name_is_modified = true;
                    this.input_item_symbol_name_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("section_number") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'section_number' must be Integer: '"+value+"' is not." );
                    this.section_number = (Integer) value;
                    if (this.section_number_is_set)
                        this.section_number_is_modified = true;
                    this.section_number_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
