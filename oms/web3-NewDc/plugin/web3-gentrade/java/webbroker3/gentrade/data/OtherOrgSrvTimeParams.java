head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.39.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OtherOrgSrvTimeParams.java;


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
 * other_org_srv_time�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link OtherOrgSrvTimeRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link OtherOrgSrvTimeParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link OtherOrgSrvTimeParams}��{@@link OtherOrgSrvTimeRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OtherOrgSrvTimePK 
 * @@see OtherOrgSrvTimeRow 
 */
public class OtherOrgSrvTimeParams extends Params implements OtherOrgSrvTimeRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "other_org_srv_time";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = OtherOrgSrvTimeRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return OtherOrgSrvTimeRow.TYPE;
  }


  /** 
   * <em>other_org_code</em>�J�����̒l 
   */
  public  String  other_org_code;

  /** 
   * <em>week_div</em>�J�����̒l 
   */
  public  String  week_div;

  /** 
   * <em>service_start_time</em>�J�����̒l 
   */
  public  String  service_start_time;

  /** 
   * <em>service_end_time</em>�J�����̒l 
   */
  public  String  service_end_time;

  /** 
   * <em>service_div</em>�J�����̒l 
   */
  public  String  service_div;

  /** 
   * <em>service_date_div</em>�J�����̒l 
   */
  public  String  service_date_div;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean other_org_code_is_set = false;
  private boolean other_org_code_is_modified = false;
  private boolean week_div_is_set = false;
  private boolean week_div_is_modified = false;
  private boolean service_start_time_is_set = false;
  private boolean service_start_time_is_modified = false;
  private boolean service_end_time_is_set = false;
  private boolean service_end_time_is_modified = false;
  private boolean service_div_is_set = false;
  private boolean service_div_is_modified = false;
  private boolean service_date_div_is_set = false;
  private boolean service_date_div_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "other_org_code=" + other_org_code
      + "," + "week_div=" + week_div
      + "," + "service_start_time=" + service_start_time
      + "," + "service_end_time=" + service_end_time
      + "," + "service_div=" +service_div
      + "," + "service_date_div=" +service_date_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��OtherOrgSrvTimeParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public OtherOrgSrvTimeParams() {
    other_org_code_is_modified = true;
    week_div_is_modified = true;
    service_start_time_is_modified = true;
    service_end_time_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���OtherOrgSrvTimeRow�I�u�W�F�N�g�̒l�𗘗p����OtherOrgSrvTimeParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����OtherOrgSrvTimeRow�I�u�W�F�N�g 
   */
  public OtherOrgSrvTimeParams( OtherOrgSrvTimeRow p_row )
  {
    other_org_code = p_row.getOtherOrgCode();
    other_org_code_is_set = p_row.getOtherOrgCodeIsSet();
    other_org_code_is_modified = p_row.getOtherOrgCodeIsModified();
    week_div = p_row.getWeekDiv();
    week_div_is_set = p_row.getWeekDivIsSet();
    week_div_is_modified = p_row.getWeekDivIsModified();
    service_start_time = p_row.getServiceStartTime();
    service_start_time_is_set = p_row.getServiceStartTimeIsSet();
    service_start_time_is_modified = p_row.getServiceStartTimeIsModified();
    service_end_time = p_row.getServiceEndTime();
    service_end_time_is_set = p_row.getServiceEndTimeIsSet();
    service_end_time_is_modified = p_row.getServiceEndTimeIsModified();
    service_div = p_row.getServiceDiv();
    service_div_is_set = p_row.getServiceDivIsSet();
    service_div_is_modified = p_row.getServiceDivIsModified();
    service_date_div = p_row.getServiceDateDiv();
    service_date_div_is_set = p_row.getServiceDateDivIsSet();
    service_date_div_is_modified = p_row.getServiceDateDivIsModified();
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
      service_div_is_set = true;
      service_div_is_modified = true;
      service_date_div_is_set = true;
      service_date_div_is_modified = true;
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
    if ( !( other instanceof OtherOrgSrvTimeRow ) )
       return false;
    return fieldsEqual( (OtherOrgSrvTimeRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�OtherOrgSrvTimeRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( OtherOrgSrvTimeRow row )
  {
    if ( other_org_code == null ) {
      if ( row.getOtherOrgCode() != null )
        return false;
    } else if ( !other_org_code.equals( row.getOtherOrgCode() ) ) {
        return false;
    }
    if ( week_div == null ) {
      if ( row.getWeekDiv() != null )
        return false;
    } else if ( !week_div.equals( row.getWeekDiv() ) ) {
        return false;
    }
    if ( service_start_time == null ) {
      if ( row.getServiceStartTime() != null )
        return false;
    } else if ( !service_start_time.equals( row.getServiceStartTime() ) ) {
        return false;
    }
    if ( service_end_time == null ) {
      if ( row.getServiceEndTime() != null )
        return false;
    } else if ( !service_end_time.equals( row.getServiceEndTime() ) ) {
        return false;
    }
    if ( service_div == null ) {
      if ( row.getServiceDiv() != null )
        return false;
    } else if ( !service_div.equals( row.getServiceDiv() ) ) {
        return false;
    }
    if ( service_date_div == null ) {
      if ( row.getServiceDateDiv() != null )
        return false;
    } else if ( !service_date_div.equals( row.getServiceDateDiv() ) ) {
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
      return  (other_org_code!=null? other_org_code.hashCode(): 0) 
        + (week_div!=null? week_div.hashCode(): 0) 
        + (service_start_time!=null? service_start_time.hashCode(): 0) 
        + (service_end_time!=null? service_end_time.hashCode(): 0) 
        + (service_div!=null? service_div.hashCode(): 0) 
        + (service_date_div!=null? service_date_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !service_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'service_div' must be set before inserting.");
		if ( !service_date_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'service_date_div' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("other_org_code",other_org_code);
		map.put("week_div",week_div);
		map.put("service_start_time",service_start_time);
		map.put("service_end_time",service_end_time);
		map.put("service_div",service_div);
		map.put("service_date_div",service_date_div);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( service_div_is_modified )
			map.put("service_div",service_div);
		if ( service_date_div_is_modified )
			map.put("service_date_div",service_date_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( service_div_is_set )
				map.put("service_div",service_div);
			if ( service_date_div_is_set )
				map.put("service_date_div",service_date_div);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>other_org_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOtherOrgCode()
  {
    return other_org_code;
  }


  /** 
   * <em>other_org_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOtherOrgCodeIsSet() {
    return other_org_code_is_set;
  }


  /** 
   * <em>other_org_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOtherOrgCodeIsModified() {
    return other_org_code_is_modified;
  }


  /** 
   * <em>week_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getWeekDiv()
  {
    return week_div;
  }


  /** 
   * <em>week_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getWeekDivIsSet() {
    return week_div_is_set;
  }


  /** 
   * <em>week_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getWeekDivIsModified() {
    return week_div_is_modified;
  }


  /** 
   * <em>service_start_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getServiceStartTime()
  {
    return service_start_time;
  }


  /** 
   * <em>service_start_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServiceStartTimeIsSet() {
    return service_start_time_is_set;
  }


  /** 
   * <em>service_start_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServiceStartTimeIsModified() {
    return service_start_time_is_modified;
  }


  /** 
   * <em>service_end_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getServiceEndTime()
  {
    return service_end_time;
  }


  /** 
   * <em>service_end_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServiceEndTimeIsSet() {
    return service_end_time_is_set;
  }


  /** 
   * <em>service_end_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServiceEndTimeIsModified() {
    return service_end_time_is_modified;
  }


  /** 
   * <em>service_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getServiceDiv()
  {
    return service_div;
  }


  /** 
   * <em>service_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServiceDivIsSet() {
    return service_div_is_set;
  }


  /** 
   * <em>service_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServiceDivIsModified() {
    return service_div_is_modified;
  }


  /** 
   * <em>service_date_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getServiceDateDiv()
  {
    return service_date_div;
  }


  /** 
   * <em>service_date_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServiceDateDivIsSet() {
    return service_date_div_is_set;
  }


  /** 
   * <em>service_date_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServiceDateDivIsModified() {
    return service_date_div_is_modified;
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
    return new OtherOrgSrvTimePK(other_org_code, week_div, service_start_time, service_end_time);
  }


  /** 
   * <em>other_org_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_otherOrgCode <em>other_org_code</em>�J�����̒l������킷11���ȉ���String�l 
   */
  public final void setOtherOrgCode( String p_otherOrgCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_org_code = p_otherOrgCode;
    other_org_code_is_set = true;
    other_org_code_is_modified = true;
  }


  /** 
   * <em>week_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_weekDiv <em>week_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setWeekDiv( String p_weekDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    week_div = p_weekDiv;
    week_div_is_set = true;
    week_div_is_modified = true;
  }


  /** 
   * <em>service_start_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_serviceStartTime <em>service_start_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setServiceStartTime( String p_serviceStartTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    service_start_time = p_serviceStartTime;
    service_start_time_is_set = true;
    service_start_time_is_modified = true;
  }


  /** 
   * <em>service_end_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_serviceEndTime <em>service_end_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setServiceEndTime( String p_serviceEndTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    service_end_time = p_serviceEndTime;
    service_end_time_is_set = true;
    service_end_time_is_modified = true;
  }


  /** 
   * <em>service_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_serviceDiv <em>service_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setServiceDiv( String p_serviceDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    service_div = p_serviceDiv;
    service_div_is_set = true;
    service_div_is_modified = true;
  }


  /** 
   * <em>service_date_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_serviceDateDiv <em>service_date_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setServiceDateDiv( String p_serviceDateDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    service_date_div = p_serviceDateDiv;
    service_date_div_is_set = true;
    service_date_div_is_modified = true;
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
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("other_org_code") ) {
                    return this.other_org_code;
                }
                break;
            case 's':
                if ( name.equals("service_start_time") ) {
                    return this.service_start_time;
                }
                else if ( name.equals("service_end_time") ) {
                    return this.service_end_time;
                }
                else if ( name.equals("service_div") ) {
                    return this.service_div;
                }
                else if ( name.equals("service_date_div") ) {
                    return this.service_date_div;
                }
                break;
            case 'w':
                if ( name.equals("week_div") ) {
                    return this.week_div;
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
            case 'o':
                if ( name.equals("other_org_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'other_org_code' must be String: '"+value+"' is not." );
                    this.other_org_code = (String) value;
                    if (this.other_org_code_is_set)
                        this.other_org_code_is_modified = true;
                    this.other_org_code_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("service_start_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'service_start_time' must be String: '"+value+"' is not." );
                    this.service_start_time = (String) value;
                    if (this.service_start_time_is_set)
                        this.service_start_time_is_modified = true;
                    this.service_start_time_is_set = true;
                    return;
                }
                else if ( name.equals("service_end_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'service_end_time' must be String: '"+value+"' is not." );
                    this.service_end_time = (String) value;
                    if (this.service_end_time_is_set)
                        this.service_end_time_is_modified = true;
                    this.service_end_time_is_set = true;
                    return;
                }
                else if ( name.equals("service_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'service_div' must be String: '"+value+"' is not." );
                    this.service_div = (String) value;
                    if (this.service_div_is_set)
                        this.service_div_is_modified = true;
                    this.service_div_is_set = true;
                    return;
                }
                else if ( name.equals("service_date_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'service_date_div' must be String: '"+value+"' is not." );
                    this.service_date_div = (String) value;
                    if (this.service_date_div_is_set)
                        this.service_date_div_is_modified = true;
                    this.service_date_div_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("week_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'week_div' must be String: '"+value+"' is not." );
                    this.week_div = (String) value;
                    if (this.week_div_is_set)
                        this.week_div_is_modified = true;
                    this.week_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
