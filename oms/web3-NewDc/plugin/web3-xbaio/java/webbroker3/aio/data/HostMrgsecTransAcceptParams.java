head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.40.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	HostMrgsecTransAcceptParams.java;


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
 * host_mrgsec_trans_accept�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link HostMrgsecTransAcceptRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link HostMrgsecTransAcceptParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link HostMrgsecTransAcceptParams}��{@@link HostMrgsecTransAcceptRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostMrgsecTransAcceptPK 
 * @@see HostMrgsecTransAcceptRow 
 */
public class HostMrgsecTransAcceptParams extends Params implements HostMrgsecTransAcceptRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "host_mrgsec_trans_accept";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = HostMrgsecTransAcceptRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return HostMrgsecTransAcceptRow.TYPE;
  }


  /** 
   * <em>request_code</em>�J�����̒l 
   */
  public  String  request_code;

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
   * <em>order_request_number</em>�J�����̒l 
   */
  public  String  order_request_number;

  /** 
   * <em>accept_div</em>�J�����̒l 
   */
  public  String  accept_div;

  /** 
   * <em>error_message</em>�J�����̒l 
   */
  public  String  error_message;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  String  status;

  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean accept_div_is_set = false;
  private boolean accept_div_is_modified = false;
  private boolean error_message_is_set = false;
  private boolean error_message_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "request_code=" +request_code
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "order_request_number=" +order_request_number
      + "," + "accept_div=" +accept_div
      + "," + "error_message=" +error_message
      + "," + "status=" +status
      + "}";
  }


  /** 
   * �l�����ݒ��HostMrgsecTransAcceptParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public HostMrgsecTransAcceptParams() {
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���HostMrgsecTransAcceptRow�I�u�W�F�N�g�̒l�𗘗p����HostMrgsecTransAcceptParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����HostMrgsecTransAcceptRow�I�u�W�F�N�g 
   */
  public HostMrgsecTransAcceptParams( HostMrgsecTransAcceptRow p_row )
  {
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    accept_div = p_row.getAcceptDiv();
    accept_div_is_set = p_row.getAcceptDivIsSet();
    accept_div_is_modified = p_row.getAcceptDivIsModified();
    error_message = p_row.getErrorMessage();
    error_message_is_set = p_row.getErrorMessageIsSet();
    error_message_is_modified = p_row.getErrorMessageIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      request_code_is_set = true;
      request_code_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      accept_div_is_set = true;
      accept_div_is_modified = true;
      error_message_is_set = true;
      error_message_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostMrgsecTransAcceptRow ) )
       return false;
    return fieldsEqual( (HostMrgsecTransAcceptRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�HostMrgsecTransAcceptRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( HostMrgsecTransAcceptRow row )
  {
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
        return false;
    }
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
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( accept_div == null ) {
      if ( row.getAcceptDiv() != null )
        return false;
    } else if ( !accept_div.equals( row.getAcceptDiv() ) ) {
        return false;
    }
    if ( error_message == null ) {
      if ( row.getErrorMessage() != null )
        return false;
    } else if ( !error_message.equals( row.getErrorMessage() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (accept_div!=null? accept_div.hashCode(): 0) 
        + (error_message!=null? error_message.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !request_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'request_code' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !order_request_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number' must be set before inserting.");
		if ( !accept_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'accept_div' must be set before inserting.");
		if ( !error_message_is_set )
			throw new IllegalArgumentException("non-nullable field 'error_message' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("request_code",request_code);
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		map.put("order_request_number",order_request_number);
		map.put("accept_div",accept_div);
		map.put("error_message",error_message);
		map.put("status",status);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( request_code_is_modified )
			map.put("request_code",request_code);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( accept_div_is_modified )
			map.put("accept_div",accept_div);
		if ( error_message_is_modified )
			map.put("error_message",error_message);
		if ( status_is_modified )
			map.put("status",status);
		if (map.size() == 0) {
			if ( request_code_is_set )
				map.put("request_code",request_code);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			if ( order_request_number_is_set )
				map.put("order_request_number",order_request_number);
			if ( accept_div_is_set )
				map.put("accept_div",accept_div);
			if ( error_message_is_set )
				map.put("error_message",error_message);
			if ( status_is_set )
				map.put("status",status);
		}
		return map;
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
   * <em>order_request_number</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderRequestNumber()
  {
    return order_request_number;
  }


  /** 
   * <em>order_request_number</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderRequestNumberIsSet() {
    return order_request_number_is_set;
  }


  /** 
   * <em>order_request_number</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderRequestNumberIsModified() {
    return order_request_number_is_modified;
  }


  /** 
   * <em>accept_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAcceptDiv()
  {
    return accept_div;
  }


  /** 
   * <em>accept_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAcceptDivIsSet() {
    return accept_div_is_set;
  }


  /** 
   * <em>accept_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAcceptDivIsModified() {
    return accept_div_is_modified;
  }


  /** 
   * <em>error_message</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getErrorMessage()
  {
    return error_message;
  }


  /** 
   * <em>error_message</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getErrorMessageIsSet() {
    return error_message_is_set;
  }


  /** 
   * <em>error_message</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getErrorMessageIsModified() {
    return error_message_is_modified;
  }


  /** 
   * <em>status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStatusIsModified() {
    return status_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    throw new UnsupportedOperationException("Table host_mrgsec_trans_accept has no primary key.");
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
   * <em>order_request_number</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>�J�����̒l������킷9���ȉ���String�l 
   */
  public final void setOrderRequestNumber( String p_orderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number = p_orderRequestNumber;
    order_request_number_is_set = true;
    order_request_number_is_modified = true;
  }


  /** 
   * <em>accept_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_acceptDiv <em>accept_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAcceptDiv( String p_acceptDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    accept_div = p_acceptDiv;
    accept_div_is_set = true;
    accept_div_is_modified = true;
  }


  /** 
   * <em>error_message</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_errorMessage <em>error_message</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setErrorMessage( String p_errorMessage )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_message = p_errorMessage;
    error_message_is_set = true;
    error_message_is_modified = true;
  }


  /** 
   * <em>status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_status <em>status</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStatus( String p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
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
                else if ( name.equals("accept_div") ) {
                    return this.accept_div;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'e':
                if ( name.equals("error_message") ) {
                    return this.error_message;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    return this.status;
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
                else if ( name.equals("accept_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'accept_div' must be String: '"+value+"' is not." );
                    this.accept_div = (String) value;
                    if (this.accept_div_is_set)
                        this.accept_div_is_modified = true;
                    this.accept_div_is_set = true;
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
            case 'e':
                if ( name.equals("error_message") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_message' must be String: '"+value+"' is not." );
                    this.error_message = (String) value;
                    if (this.error_message_is_set)
                        this.error_message_is_modified = true;
                    this.error_message_is_set = true;
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
            case 'o':
                if ( name.equals("order_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
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
                if ( name.equals("status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
