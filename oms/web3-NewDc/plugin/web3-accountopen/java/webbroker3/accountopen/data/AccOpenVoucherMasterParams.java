head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.18.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenVoucherMasterParams.java;


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
 * acc_open_voucher_master�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link AccOpenVoucherMasterRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link AccOpenVoucherMasterParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link AccOpenVoucherMasterParams}��{@@link AccOpenVoucherMasterRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenVoucherMasterPK 
 * @@see AccOpenVoucherMasterRow 
 */
public class AccOpenVoucherMasterParams extends Params implements AccOpenVoucherMasterRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "acc_open_voucher_master";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = AccOpenVoucherMasterRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return AccOpenVoucherMasterRow.TYPE;
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
   * <em>create_voucher_column</em>�J�����̒l 
   */
  public  String  create_voucher_column;

  /** 
   * <em>create_voucher_value</em>�J�����̒l 
   */
  public  String  create_voucher_value;

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
  private boolean create_voucher_column_is_set = false;
  private boolean create_voucher_column_is_modified = false;
  private boolean create_voucher_value_is_set = false;
  private boolean create_voucher_value_is_modified = false;


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
      + "," + "create_voucher_column=" +create_voucher_column
      + "," + "create_voucher_value=" +create_voucher_value
      + "}";
  }


  /** 
   * �l�����ݒ��AccOpenVoucherMasterParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public AccOpenVoucherMasterParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_div_is_modified = true;
    request_code_is_modified = true;
    serial_no_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���AccOpenVoucherMasterRow�I�u�W�F�N�g�̒l�𗘗p����AccOpenVoucherMasterParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����AccOpenVoucherMasterRow�I�u�W�F�N�g 
   */
  public AccOpenVoucherMasterParams( AccOpenVoucherMasterRow p_row )
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
    create_voucher_column = p_row.getCreateVoucherColumn();
    create_voucher_column_is_set = p_row.getCreateVoucherColumnIsSet();
    create_voucher_column_is_modified = p_row.getCreateVoucherColumnIsModified();
    create_voucher_value = p_row.getCreateVoucherValue();
    create_voucher_value_is_set = p_row.getCreateVoucherValueIsSet();
    create_voucher_value_is_modified = p_row.getCreateVoucherValueIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      create_voucher_column_is_set = true;
      create_voucher_column_is_modified = true;
      create_voucher_value_is_set = true;
      create_voucher_value_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AccOpenVoucherMasterRow ) )
       return false;
    return fieldsEqual( (AccOpenVoucherMasterRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�AccOpenVoucherMasterRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( AccOpenVoucherMasterRow row )
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
    if ( create_voucher_column == null ) {
      if ( row.getCreateVoucherColumn() != null )
        return false;
    } else if ( !create_voucher_column.equals( row.getCreateVoucherColumn() ) ) {
        return false;
    }
    if ( create_voucher_value == null ) {
      if ( row.getCreateVoucherValue() != null )
        return false;
    } else if ( !create_voucher_value.equals( row.getCreateVoucherValue() ) ) {
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
        + (create_voucher_column!=null? create_voucher_column.hashCode(): 0) 
        + (create_voucher_value!=null? create_voucher_value.hashCode(): 0) 
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
		map.put("request_code",request_code);
		map.put("serial_no",serial_no);
		if ( create_voucher_column != null )
			map.put("create_voucher_column",create_voucher_column);
		if ( create_voucher_value != null )
			map.put("create_voucher_value",create_voucher_value);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( create_voucher_column_is_modified )
			map.put("create_voucher_column",create_voucher_column);
		if ( create_voucher_value_is_modified )
			map.put("create_voucher_value",create_voucher_value);
		if (map.size() == 0) {
			map.put("create_voucher_column",create_voucher_column);
			map.put("create_voucher_value",create_voucher_value);
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
   * <em>create_voucher_column</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCreateVoucherColumn()
  {
    return create_voucher_column;
  }


  /** 
   * <em>create_voucher_column</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreateVoucherColumnIsSet() {
    return create_voucher_column_is_set;
  }


  /** 
   * <em>create_voucher_column</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreateVoucherColumnIsModified() {
    return create_voucher_column_is_modified;
  }


  /** 
   * <em>create_voucher_value</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCreateVoucherValue()
  {
    return create_voucher_value;
  }


  /** 
   * <em>create_voucher_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreateVoucherValueIsSet() {
    return create_voucher_value_is_set;
  }


  /** 
   * <em>create_voucher_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreateVoucherValueIsModified() {
    return create_voucher_value_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AccOpenVoucherMasterPK(institution_code, branch_code, account_div, request_code, serial_no);
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
   * <em>create_voucher_column</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_createVoucherColumn <em>create_voucher_column</em>�J�����̒l������킷30���ȉ���String�l 
   */
  public final void setCreateVoucherColumn( String p_createVoucherColumn )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    create_voucher_column = p_createVoucherColumn;
    create_voucher_column_is_set = true;
    create_voucher_column_is_modified = true;
  }


  /** 
   * <em>create_voucher_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_createVoucherValue <em>create_voucher_value</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setCreateVoucherValue( String p_createVoucherValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    create_voucher_value = p_createVoucherValue;
    create_voucher_value_is_set = true;
    create_voucher_value_is_modified = true;
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
                if ( name.equals("create_voucher_column") ) {
                    return this.create_voucher_column;
                }
                else if ( name.equals("create_voucher_value") ) {
                    return this.create_voucher_value;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
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
                if ( name.equals("create_voucher_column") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'create_voucher_column' must be String: '"+value+"' is not." );
                    this.create_voucher_column = (String) value;
                    if (this.create_voucher_column_is_set)
                        this.create_voucher_column_is_modified = true;
                    this.create_voucher_column_is_set = true;
                    return;
                }
                else if ( name.equals("create_voucher_value") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'create_voucher_value' must be String: '"+value+"' is not." );
                    this.create_voucher_value = (String) value;
                    if (this.create_voucher_value_is_set)
                        this.create_voucher_value_is_modified = true;
                    this.create_voucher_value_is_set = true;
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
