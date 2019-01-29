head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	FeqOrderChangeStatusParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.*;

/** 
 * feq_order_change_status�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link FeqOrderChangeStatusRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link FeqOrderChangeStatusParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link FeqOrderChangeStatusParams}��{@@link FeqOrderChangeStatusRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FeqOrderChangeStatusPK 
 * @@see FeqOrderChangeStatusRow 
 */
public class FeqOrderChangeStatusParams extends Params implements FeqOrderChangeStatusRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "feq_order_change_status";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = FeqOrderChangeStatusRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return FeqOrderChangeStatusRow.TYPE;
  }


  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>original_order_id</em>�J�����̒l 
   */
  public  long  original_order_id;

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
   * <em>new_order_id</em>�J�����̒l 
   */
  public  Long  new_order_id;

  /** 
   * <em>received_date_time</em>�J�����̒l 
   */
  public  java.sql.Timestamp  received_date_time;

  /** 
   * <em>original_order_status</em>�J�����̒l 
   */
  public  String  original_order_status;

  /** 
   * <em>original_order_error_message</em>�J�����̒l 
   */
  public  String  original_order_error_message;

  /** 
   * <em>new_order_status</em>�J�����̒l 
   */
  public  String  new_order_status;

  /** 
   * <em>new_order_error_message</em>�J�����̒l 
   */
  public  String  new_order_error_message;

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
  private boolean original_order_id_is_set = false;
  private boolean original_order_id_is_modified = false;
  private boolean new_order_id_is_set = false;
  private boolean new_order_id_is_modified = false;
  private boolean received_date_time_is_set = false;
  private boolean received_date_time_is_modified = false;
  private boolean original_order_status_is_set = false;
  private boolean original_order_status_is_modified = false;
  private boolean original_order_error_message_is_set = false;
  private boolean original_order_error_message_is_modified = false;
  private boolean new_order_status_is_set = false;
  private boolean new_order_status_is_modified = false;
  private boolean new_order_error_message_is_set = false;
  private boolean new_order_error_message_is_modified = false;
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
      + "," + "original_order_id=" + original_order_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "new_order_id=" +new_order_id
      + "," + "received_date_time=" +received_date_time
      + "," + "original_order_status=" +original_order_status
      + "," + "original_order_error_message=" +original_order_error_message
      + "," + "new_order_status=" +new_order_status
      + "," + "new_order_error_message=" +new_order_error_message
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��FeqOrderChangeStatusParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public FeqOrderChangeStatusParams() {
    account_id_is_modified = true;
    original_order_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���FeqOrderChangeStatusRow�I�u�W�F�N�g�̒l�𗘗p����FeqOrderChangeStatusParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����FeqOrderChangeStatusRow�I�u�W�F�N�g 
   */
  public FeqOrderChangeStatusParams( FeqOrderChangeStatusRow p_row )
  {
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    original_order_id = p_row.getOriginalOrderId();
    original_order_id_is_set = p_row.getOriginalOrderIdIsSet();
    original_order_id_is_modified = p_row.getOriginalOrderIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    if ( !p_row.getNewOrderIdIsNull() )
      new_order_id = new Long( p_row.getNewOrderId() );
    new_order_id_is_set = p_row.getNewOrderIdIsSet();
    new_order_id_is_modified = p_row.getNewOrderIdIsModified();
    received_date_time = p_row.getReceivedDateTime();
    received_date_time_is_set = p_row.getReceivedDateTimeIsSet();
    received_date_time_is_modified = p_row.getReceivedDateTimeIsModified();
    original_order_status = p_row.getOriginalOrderStatus();
    original_order_status_is_set = p_row.getOriginalOrderStatusIsSet();
    original_order_status_is_modified = p_row.getOriginalOrderStatusIsModified();
    original_order_error_message = p_row.getOriginalOrderErrorMessage();
    original_order_error_message_is_set = p_row.getOriginalOrderErrorMessageIsSet();
    original_order_error_message_is_modified = p_row.getOriginalOrderErrorMessageIsModified();
    new_order_status = p_row.getNewOrderStatus();
    new_order_status_is_set = p_row.getNewOrderStatusIsSet();
    new_order_status_is_modified = p_row.getNewOrderStatusIsModified();
    new_order_error_message = p_row.getNewOrderErrorMessage();
    new_order_error_message_is_set = p_row.getNewOrderErrorMessageIsSet();
    new_order_error_message_is_modified = p_row.getNewOrderErrorMessageIsModified();
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
      new_order_id_is_set = true;
      new_order_id_is_modified = true;
      received_date_time_is_set = true;
      received_date_time_is_modified = true;
      original_order_status_is_set = true;
      original_order_status_is_modified = true;
      original_order_error_message_is_set = true;
      original_order_error_message_is_modified = true;
      new_order_status_is_set = true;
      new_order_status_is_modified = true;
      new_order_error_message_is_set = true;
      new_order_error_message_is_modified = true;
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
    if ( !( other instanceof FeqOrderChangeStatusRow ) )
       return false;
    return fieldsEqual( (FeqOrderChangeStatusRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�FeqOrderChangeStatusRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( FeqOrderChangeStatusRow row )
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
    if ( original_order_id != row.getOriginalOrderId() )
      return false;
    if ( new_order_id == null ) {
      if ( !row.getNewOrderIdIsNull() )
        return false;
    } else if ( row.getNewOrderIdIsNull() || ( new_order_id.longValue() != row.getNewOrderId() ) ) {
        return false;
    }
    if ( received_date_time == null ) {
      if ( row.getReceivedDateTime() != null )
        return false;
    } else if ( !received_date_time.equals( row.getReceivedDateTime() ) ) {
        return false;
    }
    if ( original_order_status == null ) {
      if ( row.getOriginalOrderStatus() != null )
        return false;
    } else if ( !original_order_status.equals( row.getOriginalOrderStatus() ) ) {
        return false;
    }
    if ( original_order_error_message == null ) {
      if ( row.getOriginalOrderErrorMessage() != null )
        return false;
    } else if ( !original_order_error_message.equals( row.getOriginalOrderErrorMessage() ) ) {
        return false;
    }
    if ( new_order_status == null ) {
      if ( row.getNewOrderStatus() != null )
        return false;
    } else if ( !new_order_status.equals( row.getNewOrderStatus() ) ) {
        return false;
    }
    if ( new_order_error_message == null ) {
      if ( row.getNewOrderErrorMessage() != null )
        return false;
    } else if ( !new_order_error_message.equals( row.getNewOrderErrorMessage() ) ) {
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
        + ((int) original_order_id)
        + (new_order_id!=null? new_order_id.hashCode(): 0) 
        + (received_date_time!=null? received_date_time.hashCode(): 0) 
        + (original_order_status!=null? original_order_status.hashCode(): 0) 
        + (original_order_error_message!=null? original_order_error_message.hashCode(): 0) 
        + (new_order_status!=null? new_order_status.hashCode(): 0) 
        + (new_order_error_message!=null? new_order_error_message.hashCode(): 0) 
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
		map.put("account_id",new Long(account_id));
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		map.put("original_order_id",new Long(original_order_id));
		if ( new_order_id != null )
			map.put("new_order_id",new_order_id);
		if ( received_date_time != null )
			map.put("received_date_time",received_date_time);
		if ( original_order_status != null )
			map.put("original_order_status",original_order_status);
		if ( original_order_error_message != null )
			map.put("original_order_error_message",original_order_error_message);
		if ( new_order_status != null )
			map.put("new_order_status",new_order_status);
		if ( new_order_error_message != null )
			map.put("new_order_error_message",new_order_error_message);
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
		if ( new_order_id_is_modified )
			map.put("new_order_id",new_order_id);
		if ( received_date_time_is_modified )
			map.put("received_date_time",received_date_time);
		if ( original_order_status_is_modified )
			map.put("original_order_status",original_order_status);
		if ( original_order_error_message_is_modified )
			map.put("original_order_error_message",original_order_error_message);
		if ( new_order_status_is_modified )
			map.put("new_order_status",new_order_status);
		if ( new_order_error_message_is_modified )
			map.put("new_order_error_message",new_order_error_message);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("new_order_id",new_order_id);
			map.put("received_date_time",received_date_time);
			map.put("original_order_status",original_order_status);
			map.put("original_order_error_message",original_order_error_message);
			map.put("new_order_status",new_order_status);
			map.put("new_order_error_message",new_order_error_message);
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
   * <em>original_order_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getOriginalOrderId()
  {
    return original_order_id;
  }


  /** 
   * <em>original_order_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOriginalOrderIdIsSet() {
    return original_order_id_is_set;
  }


  /** 
   * <em>original_order_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOriginalOrderIdIsModified() {
    return original_order_id_is_modified;
  }


  /** 
   * <em>new_order_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getNewOrderId()
  {
    return ( new_order_id==null? ((long)0): new_order_id.longValue() );
  }


  /** 
   * <em>new_order_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getNewOrderIdIsNull()
  {
    return new_order_id == null;
  }


  /** 
   * <em>new_order_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNewOrderIdIsSet() {
    return new_order_id_is_set;
  }


  /** 
   * <em>new_order_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNewOrderIdIsModified() {
    return new_order_id_is_modified;
  }


  /** 
   * <em>received_date_time</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getReceivedDateTime()
  {
    return received_date_time;
  }


  /** 
   * <em>received_date_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getReceivedDateTimeIsSet() {
    return received_date_time_is_set;
  }


  /** 
   * <em>received_date_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getReceivedDateTimeIsModified() {
    return received_date_time_is_modified;
  }


  /** 
   * <em>original_order_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOriginalOrderStatus()
  {
    return original_order_status;
  }


  /** 
   * <em>original_order_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOriginalOrderStatusIsSet() {
    return original_order_status_is_set;
  }


  /** 
   * <em>original_order_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOriginalOrderStatusIsModified() {
    return original_order_status_is_modified;
  }


  /** 
   * <em>original_order_error_message</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOriginalOrderErrorMessage()
  {
    return original_order_error_message;
  }


  /** 
   * <em>original_order_error_message</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOriginalOrderErrorMessageIsSet() {
    return original_order_error_message_is_set;
  }


  /** 
   * <em>original_order_error_message</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOriginalOrderErrorMessageIsModified() {
    return original_order_error_message_is_modified;
  }


  /** 
   * <em>new_order_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getNewOrderStatus()
  {
    return new_order_status;
  }


  /** 
   * <em>new_order_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNewOrderStatusIsSet() {
    return new_order_status_is_set;
  }


  /** 
   * <em>new_order_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNewOrderStatusIsModified() {
    return new_order_status_is_modified;
  }


  /** 
   * <em>new_order_error_message</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getNewOrderErrorMessage()
  {
    return new_order_error_message;
  }


  /** 
   * <em>new_order_error_message</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNewOrderErrorMessageIsSet() {
    return new_order_error_message_is_set;
  }


  /** 
   * <em>new_order_error_message</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNewOrderErrorMessageIsModified() {
    return new_order_error_message_is_modified;
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
    return new FeqOrderChangeStatusPK(account_id, original_order_id);
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
   * <em>original_order_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_originalOrderId <em>original_order_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setOriginalOrderId( long p_originalOrderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    original_order_id = p_originalOrderId;
    original_order_id_is_set = true;
    original_order_id_is_modified = true;
  }


  /** 
   * <em>new_order_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_newOrderId <em>new_order_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setNewOrderId( long p_newOrderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_order_id = new Long(p_newOrderId);
    new_order_id_is_set = true;
    new_order_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>new_order_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setNewOrderId( Long p_newOrderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    new_order_id = p_newOrderId;
    new_order_id_is_set = true;
    new_order_id_is_modified = true;
  }


  /** 
   * <em>received_date_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_receivedDateTime <em>received_date_time</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setReceivedDateTime( java.sql.Timestamp p_receivedDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    received_date_time = p_receivedDateTime;
    received_date_time_is_set = true;
    received_date_time_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>received_date_time</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setReceivedDateTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    received_date_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    received_date_time_is_set = true;
    received_date_time_is_modified = true;
  }


  /** 
   * <em>original_order_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_originalOrderStatus <em>original_order_status</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setOriginalOrderStatus( String p_originalOrderStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    original_order_status = p_originalOrderStatus;
    original_order_status_is_set = true;
    original_order_status_is_modified = true;
  }


  /** 
   * <em>original_order_error_message</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_originalOrderErrorMessage <em>original_order_error_message</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setOriginalOrderErrorMessage( String p_originalOrderErrorMessage )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    original_order_error_message = p_originalOrderErrorMessage;
    original_order_error_message_is_set = true;
    original_order_error_message_is_modified = true;
  }


  /** 
   * <em>new_order_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_newOrderStatus <em>new_order_status</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setNewOrderStatus( String p_newOrderStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_order_status = p_newOrderStatus;
    new_order_status_is_set = true;
    new_order_status_is_modified = true;
  }


  /** 
   * <em>new_order_error_message</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_newOrderErrorMessage <em>new_order_error_message</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setNewOrderErrorMessage( String p_newOrderErrorMessage )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_order_error_message = p_newOrderErrorMessage;
    new_order_error_message_is_set = true;
    new_order_error_message_is_modified = true;
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
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("new_order_id") ) {
                    return this.new_order_id;
                }
                else if ( name.equals("new_order_status") ) {
                    return this.new_order_status;
                }
                else if ( name.equals("new_order_error_message") ) {
                    return this.new_order_error_message;
                }
                break;
            case 'o':
                if ( name.equals("original_order_id") ) {
                    return new Long( original_order_id );
                }
                else if ( name.equals("original_order_status") ) {
                    return this.original_order_status;
                }
                else if ( name.equals("original_order_error_message") ) {
                    return this.original_order_error_message;
                }
                break;
            case 'r':
                if ( name.equals("received_date_time") ) {
                    return this.received_date_time;
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
                    if ( value != null )
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
                    if ( value != null )
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
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( value != null )
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
                if ( name.equals("last_updated_timestamp") ) {
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
            case 'n':
                if ( name.equals("new_order_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'new_order_id' must be Long: '"+value+"' is not." );
                    this.new_order_id = (Long) value;
                    if (this.new_order_id_is_set)
                        this.new_order_id_is_modified = true;
                    this.new_order_id_is_set = true;
                    return;
                }
                else if ( name.equals("new_order_status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'new_order_status' must be String: '"+value+"' is not." );
                    this.new_order_status = (String) value;
                    if (this.new_order_status_is_set)
                        this.new_order_status_is_modified = true;
                    this.new_order_status_is_set = true;
                    return;
                }
                else if ( name.equals("new_order_error_message") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'new_order_error_message' must be String: '"+value+"' is not." );
                    this.new_order_error_message = (String) value;
                    if (this.new_order_error_message_is_set)
                        this.new_order_error_message_is_modified = true;
                    this.new_order_error_message_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("original_order_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'original_order_id' must be Long: '"+value+"' is not." );
                    this.original_order_id = ((Long) value).longValue();
                    if (this.original_order_id_is_set)
                        this.original_order_id_is_modified = true;
                    this.original_order_id_is_set = true;
                    return;
                }
                else if ( name.equals("original_order_status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'original_order_status' must be String: '"+value+"' is not." );
                    this.original_order_status = (String) value;
                    if (this.original_order_status_is_set)
                        this.original_order_status_is_modified = true;
                    this.original_order_status_is_set = true;
                    return;
                }
                else if ( name.equals("original_order_error_message") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'original_order_error_message' must be String: '"+value+"' is not." );
                    this.original_order_error_message = (String) value;
                    if (this.original_order_error_message_is_set)
                        this.original_order_error_message_is_modified = true;
                    this.original_order_error_message_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("received_date_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'received_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.received_date_time = (java.sql.Timestamp) value;
                    if (this.received_date_time_is_set)
                        this.received_date_time_is_modified = true;
                    this.received_date_time_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
