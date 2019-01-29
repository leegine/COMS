head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SubmitTriggerInfoParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * submit_trigger_info�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link SubmitTriggerInfoRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link SubmitTriggerInfoParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link SubmitTriggerInfoParams}��{@@link SubmitTriggerInfoRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SubmitTriggerInfoPK 
 * @@see SubmitTriggerInfoRow 
 */
public class SubmitTriggerInfoParams extends Params implements SubmitTriggerInfoRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "submit_trigger_info";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = SubmitTriggerInfoRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return SubmitTriggerInfoRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>request_code</em>�J�����̒l 
   */
  public  String  request_code;

  /** 
   * <em>job_id</em>�J�����̒l 
   */
  public  String  job_id;

  /** 
   * <em>user_data</em>�J�����̒l 
   */
  public  String  user_data;

  /** 
   * <em>id_no</em>�J�����̒l 
   */
  public  String  id_no;

  /** 
   * <em>trigger_id</em>�J�����̒l 
   */
  public  String  trigger_id;

  /** 
   * <em>account_start</em>�J�����̒l 
   */
  public  long  account_start;

  /** 
   * <em>account_end</em>�J�����̒l 
   */
  public  long  account_end;

  /** 
   * <em>trading_time_type</em>�J�����̒l 
   */
  public  String  trading_time_type;

  /** 
   * <em>enable_submit_trigger_flag</em>�J�����̒l 
   */
  public  String  enable_submit_trigger_flag;

  /** 
   * <em>product_handling_div</em>�J�����̒l 
   */
  public  String  product_handling_div;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean user_data_is_set = false;
  private boolean user_data_is_modified = false;
  private boolean job_id_is_set = false;
  private boolean job_id_is_modified = false;
  private boolean id_no_is_set = false;
  private boolean id_no_is_modified = false;
  private boolean trigger_id_is_set = false;
  private boolean trigger_id_is_modified = false;
  private boolean account_start_is_set = false;
  private boolean account_start_is_modified = false;
  private boolean account_end_is_set = false;
  private boolean account_end_is_modified = false;
  private boolean trading_time_type_is_set = false;
  private boolean trading_time_type_is_modified = false;
  private boolean enable_submit_trigger_flag_is_set = false;
  private boolean enable_submit_trigger_flag_is_modified = false;
  private boolean product_handling_div_is_set = false;
  private boolean product_handling_div_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "request_code=" + request_code
      + "," + "job_id=" + job_id
      + "," + "user_data=" +user_data
      + "," + "id_no=" +id_no
      + "," + "trigger_id=" +trigger_id
      + "," + "account_start=" +account_start
      + "," + "account_end=" +account_end
      + "," + "trading_time_type=" +trading_time_type
      + "," + "enable_submit_trigger_flag=" +enable_submit_trigger_flag
      + "," + "product_handling_div=" +product_handling_div
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��SubmitTriggerInfoParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public SubmitTriggerInfoParams() {
    institution_code_is_modified = true;
    request_code_is_modified = true;
    job_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���SubmitTriggerInfoRow�I�u�W�F�N�g�̒l�𗘗p����SubmitTriggerInfoParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����SubmitTriggerInfoRow�I�u�W�F�N�g 
   */
  public SubmitTriggerInfoParams( SubmitTriggerInfoRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    job_id = p_row.getJobId();
    job_id_is_set = p_row.getJobIdIsSet();
    job_id_is_modified = p_row.getJobIdIsModified();
    user_data = p_row.getUserData();
    user_data_is_set = p_row.getUserDataIsSet();
    user_data_is_modified = p_row.getUserDataIsModified();
    id_no = p_row.getIdNo();
    id_no_is_set = p_row.getIdNoIsSet();
    id_no_is_modified = p_row.getIdNoIsModified();
    trigger_id = p_row.getTriggerId();
    trigger_id_is_set = p_row.getTriggerIdIsSet();
    trigger_id_is_modified = p_row.getTriggerIdIsModified();
    account_start = p_row.getAccountStart();
    account_start_is_set = p_row.getAccountStartIsSet();
    account_start_is_modified = p_row.getAccountStartIsModified();
    account_end = p_row.getAccountEnd();
    account_end_is_set = p_row.getAccountEndIsSet();
    account_end_is_modified = p_row.getAccountEndIsModified();
    trading_time_type = p_row.getTradingTimeType();
    trading_time_type_is_set = p_row.getTradingTimeTypeIsSet();
    trading_time_type_is_modified = p_row.getTradingTimeTypeIsModified();
    enable_submit_trigger_flag = p_row.getEnableSubmitTriggerFlag();
    enable_submit_trigger_flag_is_set = p_row.getEnableSubmitTriggerFlagIsSet();
    enable_submit_trigger_flag_is_modified = p_row.getEnableSubmitTriggerFlagIsModified();
    product_handling_div = p_row.getProductHandlingDiv();
    product_handling_div_is_set = p_row.getProductHandlingDivIsSet();
    product_handling_div_is_modified = p_row.getProductHandlingDivIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      user_data_is_set = true;
      user_data_is_modified = true;
      id_no_is_set = true;
      id_no_is_modified = true;
      trigger_id_is_set = true;
      trigger_id_is_modified = true;
      account_start_is_set = true;
      account_start_is_modified = true;
      account_end_is_set = true;
      account_end_is_modified = true;
      trading_time_type_is_set = true;
      trading_time_type_is_modified = true;
      enable_submit_trigger_flag_is_set = true;
      enable_submit_trigger_flag_is_modified = true;
      product_handling_div_is_set = true;
      product_handling_div_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof SubmitTriggerInfoRow ) )
       return false;
    return fieldsEqual( (SubmitTriggerInfoRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�SubmitTriggerInfoRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( SubmitTriggerInfoRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
        return false;
    }
    if ( user_data == null ) {
      if ( row.getUserData() != null )
        return false;
    } else if ( !user_data.equals( row.getUserData() ) ) {
        return false;
    }
    if ( job_id == null ) {
      if ( row.getJobId() != null )
        return false;
    } else if ( !job_id.equals( row.getJobId() ) ) {
        return false;
    }
    if ( id_no == null ) {
      if ( row.getIdNo() != null )
        return false;
    } else if ( !id_no.equals( row.getIdNo() ) ) {
        return false;
    }
    if ( trigger_id == null ) {
      if ( row.getTriggerId() != null )
        return false;
    } else if ( !trigger_id.equals( row.getTriggerId() ) ) {
        return false;
    }
    if ( account_start != row.getAccountStart() )
      return false;
    if ( account_end != row.getAccountEnd() )
      return false;
    if ( trading_time_type == null ) {
      if ( row.getTradingTimeType() != null )
        return false;
    } else if ( !trading_time_type.equals( row.getTradingTimeType() ) ) {
        return false;
    }
    if ( enable_submit_trigger_flag == null ) {
      if ( row.getEnableSubmitTriggerFlag() != null )
        return false;
    } else if ( !enable_submit_trigger_flag.equals( row.getEnableSubmitTriggerFlag() ) ) {
        return false;
    }
    if ( product_handling_div == null ) {
      if ( row.getProductHandlingDiv() != null )
        return false;
    } else if ( !product_handling_div.equals( row.getProductHandlingDiv() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
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
        + (request_code!=null? request_code.hashCode(): 0) 
        + (user_data!=null? user_data.hashCode(): 0) 
        + (job_id!=null? job_id.hashCode(): 0) 
        + (id_no!=null? id_no.hashCode(): 0) 
        + (trigger_id!=null? trigger_id.hashCode(): 0) 
        + ((int) account_start)
        + ((int) account_end)
        + (trading_time_type!=null? trading_time_type.hashCode(): 0) 
        + (enable_submit_trigger_flag!=null? enable_submit_trigger_flag.hashCode(): 0) 
        + (product_handling_div!=null? product_handling_div.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !id_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'id_no' must be set before inserting.");
		if ( !trigger_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'trigger_id' must be set before inserting.");
		if ( !account_start_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_start' must be set before inserting.");
		if ( !account_end_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_end' must be set before inserting.");
		if ( !enable_submit_trigger_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'enable_submit_trigger_flag' must be set before inserting.");
		if ( !product_handling_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_handling_div' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("request_code",request_code);
		if ( user_data != null )
			map.put("user_data",user_data);
		map.put("job_id",job_id);
		map.put("id_no",id_no);
		map.put("trigger_id",trigger_id);
		map.put("account_start",new Long(account_start));
		map.put("account_end",new Long(account_end));
		if ( trading_time_type != null )
			map.put("trading_time_type",trading_time_type);
		map.put("enable_submit_trigger_flag",enable_submit_trigger_flag);
		map.put("product_handling_div",product_handling_div);
		map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("created_timestamp",created_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( user_data_is_modified )
			map.put("user_data",user_data);
		if ( id_no_is_modified )
			map.put("id_no",id_no);
		if ( trigger_id_is_modified )
			map.put("trigger_id",trigger_id);
		if ( account_start_is_modified )
			map.put("account_start",new Long(account_start));
		if ( account_end_is_modified )
			map.put("account_end",new Long(account_end));
		if ( trading_time_type_is_modified )
			map.put("trading_time_type",trading_time_type);
		if ( enable_submit_trigger_flag_is_modified )
			map.put("enable_submit_trigger_flag",enable_submit_trigger_flag);
		if ( product_handling_div_is_modified )
			map.put("product_handling_div",product_handling_div);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if (map.size() == 0) {
			map.put("user_data",user_data);
			if ( id_no_is_set )
				map.put("id_no",id_no);
			if ( trigger_id_is_set )
				map.put("trigger_id",trigger_id);
			if ( account_start_is_set )
				map.put("account_start",new Long(account_start));
			if ( account_end_is_set )
				map.put("account_end",new Long(account_end));
			map.put("trading_time_type",trading_time_type);
			if ( enable_submit_trigger_flag_is_set )
				map.put("enable_submit_trigger_flag",enable_submit_trigger_flag);
			if ( product_handling_div_is_set )
				map.put("product_handling_div",product_handling_div);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
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
   * <em>user_data</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getUserData()
  {
    return user_data;
  }


  /** 
   * <em>user_data</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUserDataIsSet() {
    return user_data_is_set;
  }


  /** 
   * <em>user_data</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUserDataIsModified() {
    return user_data_is_modified;
  }


  /** 
   * <em>job_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getJobId()
  {
    return job_id;
  }


  /** 
   * <em>job_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getJobIdIsSet() {
    return job_id_is_set;
  }


  /** 
   * <em>job_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getJobIdIsModified() {
    return job_id_is_modified;
  }


  /** 
   * <em>id_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getIdNo()
  {
    return id_no;
  }


  /** 
   * <em>id_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIdNoIsSet() {
    return id_no_is_set;
  }


  /** 
   * <em>id_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIdNoIsModified() {
    return id_no_is_modified;
  }


  /** 
   * <em>trigger_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTriggerId()
  {
    return trigger_id;
  }


  /** 
   * <em>trigger_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTriggerIdIsSet() {
    return trigger_id_is_set;
  }


  /** 
   * <em>trigger_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTriggerIdIsModified() {
    return trigger_id_is_modified;
  }


  /** 
   * <em>account_start</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAccountStart()
  {
    return account_start;
  }


  /** 
   * <em>account_start</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountStartIsSet() {
    return account_start_is_set;
  }


  /** 
   * <em>account_start</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountStartIsModified() {
    return account_start_is_modified;
  }


  /** 
   * <em>account_end</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAccountEnd()
  {
    return account_end;
  }


  /** 
   * <em>account_end</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountEndIsSet() {
    return account_end_is_set;
  }


  /** 
   * <em>account_end</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountEndIsModified() {
    return account_end_is_modified;
  }


  /** 
   * <em>trading_time_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTradingTimeType()
  {
    return trading_time_type;
  }


  /** 
   * <em>trading_time_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradingTimeTypeIsSet() {
    return trading_time_type_is_set;
  }


  /** 
   * <em>trading_time_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradingTimeTypeIsModified() {
    return trading_time_type_is_modified;
  }


  /** 
   * <em>enable_submit_trigger_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getEnableSubmitTriggerFlag()
  {
    return enable_submit_trigger_flag;
  }


  /** 
   * <em>enable_submit_trigger_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEnableSubmitTriggerFlagIsSet() {
    return enable_submit_trigger_flag_is_set;
  }


  /** 
   * <em>enable_submit_trigger_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEnableSubmitTriggerFlagIsModified() {
    return enable_submit_trigger_flag_is_modified;
  }


  /** 
   * <em>product_handling_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProductHandlingDiv()
  {
    return product_handling_div;
  }


  /** 
   * <em>product_handling_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductHandlingDivIsSet() {
    return product_handling_div_is_set;
  }


  /** 
   * <em>product_handling_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductHandlingDivIsModified() {
    return product_handling_div_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new SubmitTriggerInfoPK(institution_code, request_code, job_id);
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
   * <em>request_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_requestCode <em>request_code</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setRequestCode( String p_requestCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_code = p_requestCode;
    request_code_is_set = true;
    request_code_is_modified = true;
  }


  /** 
   * <em>user_data</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_userData <em>user_data</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setUserData( String p_userData )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    user_data = p_userData;
    user_data_is_set = true;
    user_data_is_modified = true;
  }


  /** 
   * <em>job_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_jobId <em>job_id</em>�J�����̒l������킷16���ȉ���String�l 
   */
  public final void setJobId( String p_jobId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    job_id = p_jobId;
    job_id_is_set = true;
    job_id_is_modified = true;
  }


  /** 
   * <em>id_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_idNo <em>id_no</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setIdNo( String p_idNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    id_no = p_idNo;
    id_no_is_set = true;
    id_no_is_modified = true;
  }


  /** 
   * <em>trigger_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_triggerId <em>trigger_id</em>�J�����̒l������킷44���ȉ���String�l 
   */
  public final void setTriggerId( String p_triggerId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trigger_id = p_triggerId;
    trigger_id_is_set = true;
    trigger_id_is_modified = true;
  }


  /** 
   * <em>account_start</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountStart <em>account_start</em>�J�����̒l������킷15���ȉ���long�l 
   */
  public final void setAccountStart( long p_accountStart )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_start = p_accountStart;
    account_start_is_set = true;
    account_start_is_modified = true;
  }


  /** 
   * <em>account_end</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountEnd <em>account_end</em>�J�����̒l������킷15���ȉ���long�l 
   */
  public final void setAccountEnd( long p_accountEnd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_end = p_accountEnd;
    account_end_is_set = true;
    account_end_is_modified = true;
  }


  /** 
   * <em>trading_time_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tradingTimeType <em>trading_time_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setTradingTimeType( String p_tradingTimeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_time_type = p_tradingTimeType;
    trading_time_type_is_set = true;
    trading_time_type_is_modified = true;
  }


  /** 
   * <em>enable_submit_trigger_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_enableSubmitTriggerFlag <em>enable_submit_trigger_flag</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setEnableSubmitTriggerFlag( String p_enableSubmitTriggerFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    enable_submit_trigger_flag = p_enableSubmitTriggerFlag;
    enable_submit_trigger_flag_is_set = true;
    enable_submit_trigger_flag_is_modified = true;
  }


  /** 
   * <em>product_handling_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productHandlingDiv <em>product_handling_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setProductHandlingDiv( String p_productHandlingDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_handling_div = p_productHandlingDiv;
    product_handling_div_is_set = true;
    product_handling_div_is_modified = true;
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
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_start") ) {
                    return new Long( account_start );
                }
                else if ( name.equals("account_end") ) {
                    return new Long( account_end );
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("enable_submit_trigger_flag") ) {
                    return this.enable_submit_trigger_flag;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("id_no") ) {
                    return this.id_no;
                }
                break;
            case 'j':
                if ( name.equals("job_id") ) {
                    return this.job_id;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("product_handling_div") ) {
                    return this.product_handling_div;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                break;
            case 't':
                if ( name.equals("trigger_id") ) {
                    return this.trigger_id;
                }
                else if ( name.equals("trading_time_type") ) {
                    return this.trading_time_type;
                }
                break;
            case 'u':
                if ( name.equals("user_data") ) {
                    return this.user_data;
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
                if ( name.equals("account_start") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_start' must be Long: '"+value+"' is not." );
                    this.account_start = ((Long) value).longValue();
                    if (this.account_start_is_set)
                        this.account_start_is_modified = true;
                    this.account_start_is_set = true;
                    return;
                }
                else if ( name.equals("account_end") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_end' must be Long: '"+value+"' is not." );
                    this.account_end = ((Long) value).longValue();
                    if (this.account_end_is_set)
                        this.account_end_is_modified = true;
                    this.account_end_is_set = true;
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
            case 'e':
                if ( name.equals("enable_submit_trigger_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'enable_submit_trigger_flag' must be String: '"+value+"' is not." );
                    this.enable_submit_trigger_flag = (String) value;
                    if (this.enable_submit_trigger_flag_is_set)
                        this.enable_submit_trigger_flag_is_modified = true;
                    this.enable_submit_trigger_flag_is_set = true;
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
                else if ( name.equals("id_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'id_no' must be String: '"+value+"' is not." );
                    this.id_no = (String) value;
                    if (this.id_no_is_set)
                        this.id_no_is_modified = true;
                    this.id_no_is_set = true;
                    return;
                }
                break;
            case 'j':
                if ( name.equals("job_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'job_id' must be String: '"+value+"' is not." );
                    this.job_id = (String) value;
                    if (this.job_id_is_set)
                        this.job_id_is_modified = true;
                    this.job_id_is_set = true;
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
            case 'p':
                if ( name.equals("product_handling_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_handling_div' must be String: '"+value+"' is not." );
                    this.product_handling_div = (String) value;
                    if (this.product_handling_div_is_set)
                        this.product_handling_div_is_modified = true;
                    this.product_handling_div_is_set = true;
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
            case 't':
                if ( name.equals("trigger_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trigger_id' must be String: '"+value+"' is not." );
                    this.trigger_id = (String) value;
                    if (this.trigger_id_is_set)
                        this.trigger_id_is_modified = true;
                    this.trigger_id_is_set = true;
                    return;
                }
                else if ( name.equals("trading_time_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_time_type' must be String: '"+value+"' is not." );
                    this.trading_time_type = (String) value;
                    if (this.trading_time_type_is_set)
                        this.trading_time_type_is_modified = true;
                    this.trading_time_type_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("user_data") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'user_data' must be String: '"+value+"' is not." );
                    this.user_data = (String) value;
                    if (this.user_data_is_set)
                        this.user_data_is_modified = true;
                    this.user_data_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
