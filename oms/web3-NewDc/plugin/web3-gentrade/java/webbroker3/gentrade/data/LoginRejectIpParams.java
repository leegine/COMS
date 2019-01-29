head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.16.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	LoginRejectIpParams.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * login_reject_ip�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link LoginRejectIpRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link LoginRejectIpParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link LoginRejectIpParams}��{@@link LoginRejectIpRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see LoginRejectIpPK 
 * @@see LoginRejectIpRow 
 */
public class LoginRejectIpParams extends Params implements LoginRejectIpRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "login_reject_ip";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = LoginRejectIpRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return LoginRejectIpRow.TYPE;
  }


  /** 
   * <em>login_reject_id</em>�J�����̒l 
   */
  public  long  login_reject_id;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>ip_address</em>�J�����̒l 
   */
  public  String  ip_address;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  String  status;

  /** 
   * <em>appli_start_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_start_timestamp;

  /** 
   * <em>appli_end_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_end_timestamp;

  /** 
   * <em>regist_div</em>�J�����̒l 
   */
  public  String  regist_div;

  /** 
   * <em>updated_div</em>�J�����̒l 
   */
  public  String  updated_div;

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

  private boolean login_reject_id_is_set = false;
  private boolean login_reject_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean ip_address_is_set = false;
  private boolean ip_address_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean appli_start_timestamp_is_set = false;
  private boolean appli_start_timestamp_is_modified = false;
  private boolean appli_end_timestamp_is_set = false;
  private boolean appli_end_timestamp_is_modified = false;
  private boolean regist_div_is_set = false;
  private boolean regist_div_is_modified = false;
  private boolean updated_div_is_set = false;
  private boolean updated_div_is_modified = false;
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
       + "login_reject_id=" + login_reject_id
      + "," + "institution_code=" +institution_code
      + "," + "ip_address=" +ip_address
      + "," + "status=" +status
      + "," + "appli_start_timestamp=" +appli_start_timestamp
      + "," + "appli_end_timestamp=" +appli_end_timestamp
      + "," + "regist_div=" +regist_div
      + "," + "updated_div=" +updated_div
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��LoginRejectIpParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public LoginRejectIpParams() {
    login_reject_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���LoginRejectIpRow�I�u�W�F�N�g�̒l�𗘗p����LoginRejectIpParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����LoginRejectIpRow�I�u�W�F�N�g 
   */
  public LoginRejectIpParams( LoginRejectIpRow p_row )
  {
    login_reject_id = p_row.getLoginRejectId();
    login_reject_id_is_set = p_row.getLoginRejectIdIsSet();
    login_reject_id_is_modified = p_row.getLoginRejectIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    ip_address = p_row.getIpAddress();
    ip_address_is_set = p_row.getIpAddressIsSet();
    ip_address_is_modified = p_row.getIpAddressIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    appli_start_timestamp = p_row.getAppliStartTimestamp();
    appli_start_timestamp_is_set = p_row.getAppliStartTimestampIsSet();
    appli_start_timestamp_is_modified = p_row.getAppliStartTimestampIsModified();
    appli_end_timestamp = p_row.getAppliEndTimestamp();
    appli_end_timestamp_is_set = p_row.getAppliEndTimestampIsSet();
    appli_end_timestamp_is_modified = p_row.getAppliEndTimestampIsModified();
    regist_div = p_row.getRegistDiv();
    regist_div_is_set = p_row.getRegistDivIsSet();
    regist_div_is_modified = p_row.getRegistDivIsModified();
    updated_div = p_row.getUpdatedDiv();
    updated_div_is_set = p_row.getUpdatedDivIsSet();
    updated_div_is_modified = p_row.getUpdatedDivIsModified();
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
      institution_code_is_set = true;
      institution_code_is_modified = true;
      ip_address_is_set = true;
      ip_address_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      appli_start_timestamp_is_set = true;
      appli_start_timestamp_is_modified = true;
      appli_end_timestamp_is_set = true;
      appli_end_timestamp_is_modified = true;
      regist_div_is_set = true;
      regist_div_is_modified = true;
      updated_div_is_set = true;
      updated_div_is_modified = true;
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
    if ( !( other instanceof LoginRejectIpRow ) )
       return false;
    return fieldsEqual( (LoginRejectIpRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�LoginRejectIpRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( LoginRejectIpRow row )
  {
    if ( login_reject_id != row.getLoginRejectId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( ip_address == null ) {
      if ( row.getIpAddress() != null )
        return false;
    } else if ( !ip_address.equals( row.getIpAddress() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( appli_start_timestamp == null ) {
      if ( row.getAppliStartTimestamp() != null )
        return false;
    } else if ( !appli_start_timestamp.equals( row.getAppliStartTimestamp() ) ) {
        return false;
    }
    if ( appli_end_timestamp == null ) {
      if ( row.getAppliEndTimestamp() != null )
        return false;
    } else if ( !appli_end_timestamp.equals( row.getAppliEndTimestamp() ) ) {
        return false;
    }
    if ( regist_div == null ) {
      if ( row.getRegistDiv() != null )
        return false;
    } else if ( !regist_div.equals( row.getRegistDiv() ) ) {
        return false;
    }
    if ( updated_div == null ) {
      if ( row.getUpdatedDiv() != null )
        return false;
    } else if ( !updated_div.equals( row.getUpdatedDiv() ) ) {
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
      return  ((int) login_reject_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (ip_address!=null? ip_address.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (appli_start_timestamp!=null? appli_start_timestamp.hashCode(): 0) 
        + (appli_end_timestamp!=null? appli_end_timestamp.hashCode(): 0) 
        + (regist_div!=null? regist_div.hashCode(): 0) 
        + (updated_div!=null? updated_div.hashCode(): 0) 
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
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !ip_address_is_set )
			throw new IllegalArgumentException("non-nullable field 'ip_address' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
		if ( !appli_start_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_start_timestamp' must be set before inserting.");
		if ( !appli_end_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_end_timestamp' must be set before inserting.");
		if ( !regist_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'regist_div' must be set before inserting.");
		if ( !updated_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'updated_div' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("login_reject_id",new Long(login_reject_id));
		map.put("institution_code",institution_code);
		map.put("ip_address",ip_address);
		map.put("status",status);
		map.put("appli_start_timestamp",appli_start_timestamp);
		map.put("appli_end_timestamp",appli_end_timestamp);
		map.put("regist_div",regist_div);
		map.put("updated_div",updated_div);
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
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( ip_address_is_modified )
			map.put("ip_address",ip_address);
		if ( status_is_modified )
			map.put("status",status);
		if ( appli_start_timestamp_is_modified )
			map.put("appli_start_timestamp",appli_start_timestamp);
		if ( appli_end_timestamp_is_modified )
			map.put("appli_end_timestamp",appli_end_timestamp);
		if ( regist_div_is_modified )
			map.put("regist_div",regist_div);
		if ( updated_div_is_modified )
			map.put("updated_div",updated_div);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( ip_address_is_set )
				map.put("ip_address",ip_address);
			if ( status_is_set )
				map.put("status",status);
			if ( appli_start_timestamp_is_set )
				map.put("appli_start_timestamp",appli_start_timestamp);
			if ( appli_end_timestamp_is_set )
				map.put("appli_end_timestamp",appli_end_timestamp);
			if ( regist_div_is_set )
				map.put("regist_div",regist_div);
			if ( updated_div_is_set )
				map.put("updated_div",updated_div);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>login_reject_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getLoginRejectId()
  {
    return login_reject_id;
  }


  /** 
   * <em>login_reject_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLoginRejectIdIsSet() {
    return login_reject_id_is_set;
  }


  /** 
   * <em>login_reject_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLoginRejectIdIsModified() {
    return login_reject_id_is_modified;
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
   * <em>ip_address</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getIpAddress()
  {
    return ip_address;
  }


  /** 
   * <em>ip_address</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpAddressIsSet() {
    return ip_address_is_set;
  }


  /** 
   * <em>ip_address</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpAddressIsModified() {
    return ip_address_is_modified;
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
   * <em>appli_start_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAppliStartTimestamp()
  {
    return appli_start_timestamp;
  }


  /** 
   * <em>appli_start_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartTimestampIsSet() {
    return appli_start_timestamp_is_set;
  }


  /** 
   * <em>appli_start_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartTimestampIsModified() {
    return appli_start_timestamp_is_modified;
  }


  /** 
   * <em>appli_end_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAppliEndTimestamp()
  {
    return appli_end_timestamp;
  }


  /** 
   * <em>appli_end_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliEndTimestampIsSet() {
    return appli_end_timestamp_is_set;
  }


  /** 
   * <em>appli_end_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliEndTimestampIsModified() {
    return appli_end_timestamp_is_modified;
  }


  /** 
   * <em>regist_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRegistDiv()
  {
    return regist_div;
  }


  /** 
   * <em>regist_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegistDivIsSet() {
    return regist_div_is_set;
  }


  /** 
   * <em>regist_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegistDivIsModified() {
    return regist_div_is_modified;
  }


  /** 
   * <em>updated_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getUpdatedDiv()
  {
    return updated_div;
  }


  /** 
   * <em>updated_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUpdatedDivIsSet() {
    return updated_div_is_set;
  }


  /** 
   * <em>updated_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUpdatedDivIsModified() {
    return updated_div_is_modified;
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
    return new LoginRejectIpPK(login_reject_id);
  }


  /** 
   * <em>login_reject_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_loginRejectId <em>login_reject_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setLoginRejectId( long p_loginRejectId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    login_reject_id = p_loginRejectId;
    login_reject_id_is_set = true;
    login_reject_id_is_modified = true;
  }


  /** 
   * <em>institution_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>ip_address</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ipAddress <em>ip_address</em>�J�����̒l������킷15���ȉ���String�l 
   */
  public final void setIpAddress( String p_ipAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ip_address = p_ipAddress;
    ip_address_is_set = true;
    ip_address_is_modified = true;
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
   * <em>appli_start_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliStartTimestamp <em>appli_start_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAppliStartTimestamp( java.sql.Timestamp p_appliStartTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_timestamp = p_appliStartTimestamp;
    appli_start_timestamp_is_set = true;
    appli_start_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>appli_start_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAppliStartTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_start_timestamp_is_set = true;
    appli_start_timestamp_is_modified = true;
  }


  /** 
   * <em>appli_end_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliEndTimestamp <em>appli_end_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAppliEndTimestamp( java.sql.Timestamp p_appliEndTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_timestamp = p_appliEndTimestamp;
    appli_end_timestamp_is_set = true;
    appli_end_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>appli_end_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAppliEndTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_end_timestamp_is_set = true;
    appli_end_timestamp_is_modified = true;
  }


  /** 
   * <em>regist_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_registDiv <em>regist_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRegistDiv( String p_registDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_div = p_registDiv;
    regist_div_is_set = true;
    regist_div_is_modified = true;
  }


  /** 
   * <em>updated_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_updatedDiv <em>updated_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setUpdatedDiv( String p_updatedDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    updated_div = p_updatedDiv;
    updated_div_is_set = true;
    updated_div_is_modified = true;
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
                if ( name.equals("appli_start_timestamp") ) {
                    return this.appli_start_timestamp;
                }
                else if ( name.equals("appli_end_timestamp") ) {
                    return this.appli_end_timestamp;
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
                else if ( name.equals("ip_address") ) {
                    return this.ip_address;
                }
                break;
            case 'l':
                if ( name.equals("login_reject_id") ) {
                    return new Long( login_reject_id );
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'r':
                if ( name.equals("regist_div") ) {
                    return this.regist_div;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 'u':
                if ( name.equals("updated_div") ) {
                    return this.updated_div;
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
                if ( name.equals("appli_start_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_start_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_start_timestamp = (java.sql.Timestamp) value;
                    if (this.appli_start_timestamp_is_set)
                        this.appli_start_timestamp_is_modified = true;
                    this.appli_start_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("appli_end_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_end_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_end_timestamp = (java.sql.Timestamp) value;
                    if (this.appli_end_timestamp_is_set)
                        this.appli_end_timestamp_is_modified = true;
                    this.appli_end_timestamp_is_set = true;
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
                else if ( name.equals("ip_address") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ip_address' must be String: '"+value+"' is not." );
                    this.ip_address = (String) value;
                    if (this.ip_address_is_set)
                        this.ip_address_is_modified = true;
                    this.ip_address_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("login_reject_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'login_reject_id' must be Long: '"+value+"' is not." );
                    this.login_reject_id = ((Long) value).longValue();
                    if (this.login_reject_id_is_set)
                        this.login_reject_id_is_modified = true;
                    this.login_reject_id_is_set = true;
                    return;
                }
                else if ( name.equals("last_updater") ) {
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
            case 'r':
                if ( name.equals("regist_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'regist_div' must be String: '"+value+"' is not." );
                    this.regist_div = (String) value;
                    if (this.regist_div_is_set)
                        this.regist_div_is_modified = true;
                    this.regist_div_is_set = true;
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
            case 'u':
                if ( name.equals("updated_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'updated_div' must be String: '"+value+"' is not." );
                    this.updated_div = (String) value;
                    if (this.updated_div_is_set)
                        this.updated_div_is_modified = true;
                    this.updated_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
