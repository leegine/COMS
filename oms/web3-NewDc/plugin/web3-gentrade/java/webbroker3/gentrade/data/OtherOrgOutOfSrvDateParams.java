head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.29.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OtherOrgOutOfSrvDateParams.java;


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
 * other_org_out_of_srv_date�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link OtherOrgOutOfSrvDateRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link OtherOrgOutOfSrvDateParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link OtherOrgOutOfSrvDateParams}��{@@link OtherOrgOutOfSrvDateRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OtherOrgOutOfSrvDatePK 
 * @@see OtherOrgOutOfSrvDateRow 
 */
public class OtherOrgOutOfSrvDateParams extends Params implements OtherOrgOutOfSrvDateRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "other_org_out_of_srv_date";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = OtherOrgOutOfSrvDateRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return OtherOrgOutOfSrvDateRow.TYPE;
  }


  /** 
   * <em>other_org_code</em>�J�����̒l 
   */
  public  String  other_org_code;

  /** 
   * <em>suspension_date</em>�J�����̒l 
   */
  public  String  suspension_date;

  /** 
   * <em>suspension_start_time</em>�J�����̒l 
   */
  public  String  suspension_start_time;

  /** 
   * <em>suspension_end_time</em>�J�����̒l 
   */
  public  String  suspension_end_time;

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
  private boolean suspension_date_is_set = false;
  private boolean suspension_date_is_modified = false;
  private boolean suspension_start_time_is_set = false;
  private boolean suspension_start_time_is_modified = false;
  private boolean suspension_end_time_is_set = false;
  private boolean suspension_end_time_is_modified = false;
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
      + "," + "suspension_date=" + suspension_date
      + "," + "suspension_start_time=" + suspension_start_time
      + "," + "suspension_end_time=" + suspension_end_time
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��OtherOrgOutOfSrvDateParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public OtherOrgOutOfSrvDateParams() {
    other_org_code_is_modified = true;
    suspension_date_is_modified = true;
    suspension_start_time_is_modified = true;
    suspension_end_time_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���OtherOrgOutOfSrvDateRow�I�u�W�F�N�g�̒l�𗘗p����OtherOrgOutOfSrvDateParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����OtherOrgOutOfSrvDateRow�I�u�W�F�N�g 
   */
  public OtherOrgOutOfSrvDateParams( OtherOrgOutOfSrvDateRow p_row )
  {
    other_org_code = p_row.getOtherOrgCode();
    other_org_code_is_set = p_row.getOtherOrgCodeIsSet();
    other_org_code_is_modified = p_row.getOtherOrgCodeIsModified();
    suspension_date = p_row.getSuspensionDate();
    suspension_date_is_set = p_row.getSuspensionDateIsSet();
    suspension_date_is_modified = p_row.getSuspensionDateIsModified();
    suspension_start_time = p_row.getSuspensionStartTime();
    suspension_start_time_is_set = p_row.getSuspensionStartTimeIsSet();
    suspension_start_time_is_modified = p_row.getSuspensionStartTimeIsModified();
    suspension_end_time = p_row.getSuspensionEndTime();
    suspension_end_time_is_set = p_row.getSuspensionEndTimeIsSet();
    suspension_end_time_is_modified = p_row.getSuspensionEndTimeIsModified();
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
    if ( !( other instanceof OtherOrgOutOfSrvDateRow ) )
       return false;
    return fieldsEqual( (OtherOrgOutOfSrvDateRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�OtherOrgOutOfSrvDateRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( OtherOrgOutOfSrvDateRow row )
  {
    if ( other_org_code == null ) {
      if ( row.getOtherOrgCode() != null )
        return false;
    } else if ( !other_org_code.equals( row.getOtherOrgCode() ) ) {
        return false;
    }
    if ( suspension_date == null ) {
      if ( row.getSuspensionDate() != null )
        return false;
    } else if ( !suspension_date.equals( row.getSuspensionDate() ) ) {
        return false;
    }
    if ( suspension_start_time == null ) {
      if ( row.getSuspensionStartTime() != null )
        return false;
    } else if ( !suspension_start_time.equals( row.getSuspensionStartTime() ) ) {
        return false;
    }
    if ( suspension_end_time == null ) {
      if ( row.getSuspensionEndTime() != null )
        return false;
    } else if ( !suspension_end_time.equals( row.getSuspensionEndTime() ) ) {
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
        + (suspension_date!=null? suspension_date.hashCode(): 0) 
        + (suspension_start_time!=null? suspension_start_time.hashCode(): 0) 
        + (suspension_end_time!=null? suspension_end_time.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
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
		map.put("suspension_date",suspension_date);
		map.put("suspension_start_time",suspension_start_time);
		map.put("suspension_end_time",suspension_end_time);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
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
   * <em>suspension_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSuspensionDate()
  {
    return suspension_date;
  }


  /** 
   * <em>suspension_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSuspensionDateIsSet() {
    return suspension_date_is_set;
  }


  /** 
   * <em>suspension_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSuspensionDateIsModified() {
    return suspension_date_is_modified;
  }


  /** 
   * <em>suspension_start_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSuspensionStartTime()
  {
    return suspension_start_time;
  }


  /** 
   * <em>suspension_start_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSuspensionStartTimeIsSet() {
    return suspension_start_time_is_set;
  }


  /** 
   * <em>suspension_start_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSuspensionStartTimeIsModified() {
    return suspension_start_time_is_modified;
  }


  /** 
   * <em>suspension_end_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSuspensionEndTime()
  {
    return suspension_end_time;
  }


  /** 
   * <em>suspension_end_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSuspensionEndTimeIsSet() {
    return suspension_end_time_is_set;
  }


  /** 
   * <em>suspension_end_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSuspensionEndTimeIsModified() {
    return suspension_end_time_is_modified;
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
    return new OtherOrgOutOfSrvDatePK(other_org_code, suspension_date, suspension_start_time, suspension_end_time);
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
   * <em>suspension_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_suspensionDate <em>suspension_date</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setSuspensionDate( String p_suspensionDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    suspension_date = p_suspensionDate;
    suspension_date_is_set = true;
    suspension_date_is_modified = true;
  }


  /** 
   * <em>suspension_start_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_suspensionStartTime <em>suspension_start_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setSuspensionStartTime( String p_suspensionStartTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    suspension_start_time = p_suspensionStartTime;
    suspension_start_time_is_set = true;
    suspension_start_time_is_modified = true;
  }


  /** 
   * <em>suspension_end_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_suspensionEndTime <em>suspension_end_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setSuspensionEndTime( String p_suspensionEndTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    suspension_end_time = p_suspensionEndTime;
    suspension_end_time_is_set = true;
    suspension_end_time_is_modified = true;
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
                if ( name.equals("suspension_date") ) {
                    return this.suspension_date;
                }
                else if ( name.equals("suspension_start_time") ) {
                    return this.suspension_start_time;
                }
                else if ( name.equals("suspension_end_time") ) {
                    return this.suspension_end_time;
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
                if ( name.equals("suspension_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'suspension_date' must be String: '"+value+"' is not." );
                    this.suspension_date = (String) value;
                    if (this.suspension_date_is_set)
                        this.suspension_date_is_modified = true;
                    this.suspension_date_is_set = true;
                    return;
                }
                else if ( name.equals("suspension_start_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'suspension_start_time' must be String: '"+value+"' is not." );
                    this.suspension_start_time = (String) value;
                    if (this.suspension_start_time_is_set)
                        this.suspension_start_time_is_modified = true;
                    this.suspension_start_time_is_set = true;
                    return;
                }
                else if ( name.equals("suspension_end_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'suspension_end_time' must be String: '"+value+"' is not." );
                    this.suspension_end_time = (String) value;
                    if (this.suspension_end_time_is_set)
                        this.suspension_end_time_is_modified = true;
                    this.suspension_end_time_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
