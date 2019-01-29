head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocForceLogoutRunStatusParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * doc_force_logout_run_status�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link DocForceLogoutRunStatusRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link DocForceLogoutRunStatusParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link DocForceLogoutRunStatusParams}��{@@link DocForceLogoutRunStatusRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DocForceLogoutRunStatusPK 
 * @@see DocForceLogoutRunStatusRow 
 */
public class DocForceLogoutRunStatusParams extends Params implements DocForceLogoutRunStatusRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "doc_force_logout_run_status";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = DocForceLogoutRunStatusRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return DocForceLogoutRunStatusRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>account_id_from</em>�J�����̒l 
   */
  public  long  account_id_from;

  /** 
   * <em>account_id_to</em>�J�����̒l 
   */
  public  long  account_id_to;

  /** 
   * <em>start_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  start_timestamp;

  /** 
   * <em>end_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  end_timestamp;

  /** 
   * <em>document_category_list</em>�J�����̒l 
   */
  public  String  document_category_list;

  /** 
   * <em>process_count</em>�J�����̒l 
   */
  public  Long  process_count;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  String  status;

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
  private boolean account_id_from_is_set = false;
  private boolean account_id_from_is_modified = false;
  private boolean account_id_to_is_set = false;
  private boolean account_id_to_is_modified = false;
  private boolean start_timestamp_is_set = false;
  private boolean start_timestamp_is_modified = false;
  private boolean end_timestamp_is_set = false;
  private boolean end_timestamp_is_modified = false;
  private boolean document_category_list_is_set = false;
  private boolean document_category_list_is_modified = false;
  private boolean process_count_is_set = false;
  private boolean process_count_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
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
      + "," + "account_id_from=" + account_id_from
      + "," + "account_id_to=" +account_id_to
      + "," + "start_timestamp=" +start_timestamp
      + "," + "end_timestamp=" +end_timestamp
      + "," + "document_category_list=" +document_category_list
      + "," + "process_count=" +process_count
      + "," + "status=" +status
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��DocForceLogoutRunStatusParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public DocForceLogoutRunStatusParams() {
    institution_code_is_modified = true;
    account_id_from_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���DocForceLogoutRunStatusRow�I�u�W�F�N�g�̒l�𗘗p����DocForceLogoutRunStatusParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����DocForceLogoutRunStatusRow�I�u�W�F�N�g 
   */
  public DocForceLogoutRunStatusParams( DocForceLogoutRunStatusRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    account_id_from = p_row.getAccountIdFrom();
    account_id_from_is_set = p_row.getAccountIdFromIsSet();
    account_id_from_is_modified = p_row.getAccountIdFromIsModified();
    account_id_to = p_row.getAccountIdTo();
    account_id_to_is_set = p_row.getAccountIdToIsSet();
    account_id_to_is_modified = p_row.getAccountIdToIsModified();
    start_timestamp = p_row.getStartTimestamp();
    start_timestamp_is_set = p_row.getStartTimestampIsSet();
    start_timestamp_is_modified = p_row.getStartTimestampIsModified();
    end_timestamp = p_row.getEndTimestamp();
    end_timestamp_is_set = p_row.getEndTimestampIsSet();
    end_timestamp_is_modified = p_row.getEndTimestampIsModified();
    document_category_list = p_row.getDocumentCategoryList();
    document_category_list_is_set = p_row.getDocumentCategoryListIsSet();
    document_category_list_is_modified = p_row.getDocumentCategoryListIsModified();
    if ( !p_row.getProcessCountIsNull() )
      process_count = new Long( p_row.getProcessCount() );
    process_count_is_set = p_row.getProcessCountIsSet();
    process_count_is_modified = p_row.getProcessCountIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
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
      account_id_to_is_set = true;
      account_id_to_is_modified = true;
      start_timestamp_is_set = true;
      start_timestamp_is_modified = true;
      end_timestamp_is_set = true;
      end_timestamp_is_modified = true;
      document_category_list_is_set = true;
      document_category_list_is_modified = true;
      process_count_is_set = true;
      process_count_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
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
    if ( !( other instanceof DocForceLogoutRunStatusRow ) )
       return false;
    return fieldsEqual( (DocForceLogoutRunStatusRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�DocForceLogoutRunStatusRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( DocForceLogoutRunStatusRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( account_id_from != row.getAccountIdFrom() )
      return false;
    if ( account_id_to != row.getAccountIdTo() )
      return false;
    if ( start_timestamp == null ) {
      if ( row.getStartTimestamp() != null )
        return false;
    } else if ( !start_timestamp.equals( row.getStartTimestamp() ) ) {
        return false;
    }
    if ( end_timestamp == null ) {
      if ( row.getEndTimestamp() != null )
        return false;
    } else if ( !end_timestamp.equals( row.getEndTimestamp() ) ) {
        return false;
    }
    if ( document_category_list == null ) {
      if ( row.getDocumentCategoryList() != null )
        return false;
    } else if ( !document_category_list.equals( row.getDocumentCategoryList() ) ) {
        return false;
    }
    if ( process_count == null ) {
      if ( !row.getProcessCountIsNull() )
        return false;
    } else if ( row.getProcessCountIsNull() || ( process_count.longValue() != row.getProcessCount() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
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
        + ((int) account_id_from)
        + ((int) account_id_to)
        + (start_timestamp!=null? start_timestamp.hashCode(): 0) 
        + (end_timestamp!=null? end_timestamp.hashCode(): 0) 
        + (document_category_list!=null? document_category_list.hashCode(): 0) 
        + (process_count!=null? process_count.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
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
		if ( !account_id_to_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id_to' must be set before inserting.");
		if ( !start_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'start_timestamp' must be set before inserting.");
		if ( !document_category_list_is_set )
			throw new IllegalArgumentException("non-nullable field 'document_category_list' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("account_id_from",new Long(account_id_from));
		map.put("account_id_to",new Long(account_id_to));
		map.put("start_timestamp",start_timestamp);
		if ( end_timestamp != null )
			map.put("end_timestamp",end_timestamp);
		map.put("document_category_list",document_category_list);
		if ( process_count != null )
			map.put("process_count",process_count);
		map.put("status",status);
		if ( last_updater != null )
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
		if ( account_id_to_is_modified )
			map.put("account_id_to",new Long(account_id_to));
		if ( start_timestamp_is_modified )
			map.put("start_timestamp",start_timestamp);
		if ( end_timestamp_is_modified )
			map.put("end_timestamp",end_timestamp);
		if ( document_category_list_is_modified )
			map.put("document_category_list",document_category_list);
		if ( process_count_is_modified )
			map.put("process_count",process_count);
		if ( status_is_modified )
			map.put("status",status);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_to_is_set )
				map.put("account_id_to",new Long(account_id_to));
			if ( start_timestamp_is_set )
				map.put("start_timestamp",start_timestamp);
			map.put("end_timestamp",end_timestamp);
			if ( document_category_list_is_set )
				map.put("document_category_list",document_category_list);
			map.put("process_count",process_count);
			if ( status_is_set )
				map.put("status",status);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
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
   * <em>account_id_from</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAccountIdFrom()
  {
    return account_id_from;
  }


  /** 
   * <em>account_id_from</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountIdFromIsSet() {
    return account_id_from_is_set;
  }


  /** 
   * <em>account_id_from</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountIdFromIsModified() {
    return account_id_from_is_modified;
  }


  /** 
   * <em>account_id_to</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAccountIdTo()
  {
    return account_id_to;
  }


  /** 
   * <em>account_id_to</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountIdToIsSet() {
    return account_id_to_is_set;
  }


  /** 
   * <em>account_id_to</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountIdToIsModified() {
    return account_id_to_is_modified;
  }


  /** 
   * <em>start_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getStartTimestamp()
  {
    return start_timestamp;
  }


  /** 
   * <em>start_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStartTimestampIsSet() {
    return start_timestamp_is_set;
  }


  /** 
   * <em>start_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStartTimestampIsModified() {
    return start_timestamp_is_modified;
  }


  /** 
   * <em>end_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getEndTimestamp()
  {
    return end_timestamp;
  }


  /** 
   * <em>end_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndTimestampIsSet() {
    return end_timestamp_is_set;
  }


  /** 
   * <em>end_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndTimestampIsModified() {
    return end_timestamp_is_modified;
  }


  /** 
   * <em>document_category_list</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getDocumentCategoryList()
  {
    return document_category_list;
  }


  /** 
   * <em>document_category_list</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDocumentCategoryListIsSet() {
    return document_category_list_is_set;
  }


  /** 
   * <em>document_category_list</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDocumentCategoryListIsModified() {
    return document_category_list_is_modified;
  }


  /** 
   * <em>process_count</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getProcessCount()
  {
    return ( process_count==null? ((long)0): process_count.longValue() );
  }


  /** 
   * <em>process_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getProcessCountIsNull()
  {
    return process_count == null;
  }


  /** 
   * <em>process_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProcessCountIsSet() {
    return process_count_is_set;
  }


  /** 
   * <em>process_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProcessCountIsModified() {
    return process_count_is_modified;
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
    return new DocForceLogoutRunStatusPK(institution_code, account_id_from);
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
   * <em>account_id_from</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountIdFrom <em>account_id_from</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setAccountIdFrom( long p_accountIdFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id_from = p_accountIdFrom;
    account_id_from_is_set = true;
    account_id_from_is_modified = true;
  }


  /** 
   * <em>account_id_to</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountIdTo <em>account_id_to</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setAccountIdTo( long p_accountIdTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id_to = p_accountIdTo;
    account_id_to_is_set = true;
    account_id_to_is_modified = true;
  }


  /** 
   * <em>start_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_startTimestamp <em>start_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setStartTimestamp( java.sql.Timestamp p_startTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    start_timestamp = p_startTimestamp;
    start_timestamp_is_set = true;
    start_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>start_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setStartTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    start_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    start_timestamp_is_set = true;
    start_timestamp_is_modified = true;
  }


  /** 
   * <em>end_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_endTimestamp <em>end_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setEndTimestamp( java.sql.Timestamp p_endTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    end_timestamp = p_endTimestamp;
    end_timestamp_is_set = true;
    end_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>end_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setEndTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    end_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    end_timestamp_is_set = true;
    end_timestamp_is_modified = true;
  }


  /** 
   * <em>document_category_list</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_documentCategoryList <em>document_category_list</em>�J�����̒l������킷40���ȉ���String�l 
   */
  public final void setDocumentCategoryList( String p_documentCategoryList )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    document_category_list = p_documentCategoryList;
    document_category_list_is_set = true;
    document_category_list_is_modified = true;
  }


  /** 
   * <em>process_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_processCount <em>process_count</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setProcessCount( long p_processCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    process_count = new Long(p_processCount);
    process_count_is_set = true;
    process_count_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>process_count</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setProcessCount( Long p_processCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    process_count = p_processCount;
    process_count_is_set = true;
    process_count_is_modified = true;
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
                if ( name.equals("account_id_from") ) {
                    return new Long( account_id_from );
                }
                else if ( name.equals("account_id_to") ) {
                    return new Long( account_id_to );
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("document_category_list") ) {
                    return this.document_category_list;
                }
                break;
            case 'e':
                if ( name.equals("end_timestamp") ) {
                    return this.end_timestamp;
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
            case 'p':
                if ( name.equals("process_count") ) {
                    return this.process_count;
                }
                break;
            case 's':
                if ( name.equals("start_timestamp") ) {
                    return this.start_timestamp;
                }
                else if ( name.equals("status") ) {
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
                if ( name.equals("account_id_from") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id_from' must be Long: '"+value+"' is not." );
                    this.account_id_from = ((Long) value).longValue();
                    if (this.account_id_from_is_set)
                        this.account_id_from_is_modified = true;
                    this.account_id_from_is_set = true;
                    return;
                }
                else if ( name.equals("account_id_to") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id_to' must be Long: '"+value+"' is not." );
                    this.account_id_to = ((Long) value).longValue();
                    if (this.account_id_to_is_set)
                        this.account_id_to_is_modified = true;
                    this.account_id_to_is_set = true;
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
            case 'd':
                if ( name.equals("document_category_list") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'document_category_list' must be String: '"+value+"' is not." );
                    this.document_category_list = (String) value;
                    if (this.document_category_list_is_set)
                        this.document_category_list_is_modified = true;
                    this.document_category_list_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("end_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'end_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.end_timestamp = (java.sql.Timestamp) value;
                    if (this.end_timestamp_is_set)
                        this.end_timestamp_is_modified = true;
                    this.end_timestamp_is_set = true;
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
            case 'p':
                if ( name.equals("process_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'process_count' must be Long: '"+value+"' is not." );
                    this.process_count = (Long) value;
                    if (this.process_count_is_set)
                        this.process_count_is_modified = true;
                    this.process_count_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("start_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'start_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.start_timestamp = (java.sql.Timestamp) value;
                    if (this.start_timestamp_is_set)
                        this.start_timestamp_is_modified = true;
                    this.start_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
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
