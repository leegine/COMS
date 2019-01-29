head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.50.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointPremiumMasterParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.point.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * point_premium_master�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link PointPremiumMasterRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link PointPremiumMasterParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link PointPremiumMasterParams}��{@@link PointPremiumMasterRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PointPremiumMasterPK 
 * @@see PointPremiumMasterRow 
 */
public class PointPremiumMasterParams extends Params implements PointPremiumMasterRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "point_premium_master";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = PointPremiumMasterRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return PointPremiumMasterRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>premium_no</em>�J�����̒l 
   */
  public  String  premium_no;

  /** 
   * <em>category_no</em>�J�����̒l 
   */
  public  int  category_no;

  /** 
   * <em>premium_name</em>�J�����̒l 
   */
  public  String  premium_name;

  /** 
   * <em>required_point</em>�J�����̒l 
   */
  public  int  required_point;

  /** 
   * <em>start_date_time</em>�J�����̒l 
   */
  public  java.sql.Timestamp  start_date_time;

  /** 
   * <em>end_date_time</em>�J�����̒l 
   */
  public  java.sql.Timestamp  end_date_time;

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
  private boolean category_no_is_set = false;
  private boolean category_no_is_modified = false;
  private boolean premium_no_is_set = false;
  private boolean premium_no_is_modified = false;
  private boolean premium_name_is_set = false;
  private boolean premium_name_is_modified = false;
  private boolean required_point_is_set = false;
  private boolean required_point_is_modified = false;
  private boolean start_date_time_is_set = false;
  private boolean start_date_time_is_modified = false;
  private boolean end_date_time_is_set = false;
  private boolean end_date_time_is_modified = false;
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
      + "," + "premium_no=" + premium_no
      + "," + "category_no=" +category_no
      + "," + "premium_name=" +premium_name
      + "," + "required_point=" +required_point
      + "," + "start_date_time=" +start_date_time
      + "," + "end_date_time=" +end_date_time
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��PointPremiumMasterParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public PointPremiumMasterParams() {
    institution_code_is_modified = true;
    premium_no_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���PointPremiumMasterRow�I�u�W�F�N�g�̒l�𗘗p����PointPremiumMasterParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����PointPremiumMasterRow�I�u�W�F�N�g 
   */
  public PointPremiumMasterParams( PointPremiumMasterRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    premium_no = p_row.getPremiumNo();
    premium_no_is_set = p_row.getPremiumNoIsSet();
    premium_no_is_modified = p_row.getPremiumNoIsModified();
    category_no = p_row.getCategoryNo();
    category_no_is_set = p_row.getCategoryNoIsSet();
    category_no_is_modified = p_row.getCategoryNoIsModified();
    premium_name = p_row.getPremiumName();
    premium_name_is_set = p_row.getPremiumNameIsSet();
    premium_name_is_modified = p_row.getPremiumNameIsModified();
    required_point = p_row.getRequiredPoint();
    required_point_is_set = p_row.getRequiredPointIsSet();
    required_point_is_modified = p_row.getRequiredPointIsModified();
    start_date_time = p_row.getStartDateTime();
    start_date_time_is_set = p_row.getStartDateTimeIsSet();
    start_date_time_is_modified = p_row.getStartDateTimeIsModified();
    end_date_time = p_row.getEndDateTime();
    end_date_time_is_set = p_row.getEndDateTimeIsSet();
    end_date_time_is_modified = p_row.getEndDateTimeIsModified();
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
      category_no_is_set = true;
      category_no_is_modified = true;
      premium_name_is_set = true;
      premium_name_is_modified = true;
      required_point_is_set = true;
      required_point_is_modified = true;
      start_date_time_is_set = true;
      start_date_time_is_modified = true;
      end_date_time_is_set = true;
      end_date_time_is_modified = true;
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
    if ( !( other instanceof PointPremiumMasterRow ) )
       return false;
    return fieldsEqual( (PointPremiumMasterRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�PointPremiumMasterRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( PointPremiumMasterRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( category_no != row.getCategoryNo() )
      return false;
    if ( premium_no == null ) {
      if ( row.getPremiumNo() != null )
        return false;
    } else if ( !premium_no.equals( row.getPremiumNo() ) ) {
        return false;
    }
    if ( premium_name == null ) {
      if ( row.getPremiumName() != null )
        return false;
    } else if ( !premium_name.equals( row.getPremiumName() ) ) {
        return false;
    }
    if ( required_point != row.getRequiredPoint() )
      return false;
    if ( start_date_time == null ) {
      if ( row.getStartDateTime() != null )
        return false;
    } else if ( !start_date_time.equals( row.getStartDateTime() ) ) {
        return false;
    }
    if ( end_date_time == null ) {
      if ( row.getEndDateTime() != null )
        return false;
    } else if ( !end_date_time.equals( row.getEndDateTime() ) ) {
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
        + ((int) category_no)
        + (premium_no!=null? premium_no.hashCode(): 0) 
        + (premium_name!=null? premium_name.hashCode(): 0) 
        + ((int) required_point)
        + (start_date_time!=null? start_date_time.hashCode(): 0) 
        + (end_date_time!=null? end_date_time.hashCode(): 0) 
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
		if ( !category_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'category_no' must be set before inserting.");
		if ( !premium_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'premium_name' must be set before inserting.");
		if ( !required_point_is_set )
			throw new IllegalArgumentException("non-nullable field 'required_point' must be set before inserting.");
		if ( !start_date_time_is_set )
			throw new IllegalArgumentException("non-nullable field 'start_date_time' must be set before inserting.");
		if ( !end_date_time_is_set )
			throw new IllegalArgumentException("non-nullable field 'end_date_time' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("category_no",new Integer(category_no));
		map.put("premium_no",premium_no);
		map.put("premium_name",premium_name);
		map.put("required_point",new Integer(required_point));
		map.put("start_date_time",start_date_time);
		map.put("end_date_time",end_date_time);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( category_no_is_modified )
			map.put("category_no",new Integer(category_no));
		if ( premium_name_is_modified )
			map.put("premium_name",premium_name);
		if ( required_point_is_modified )
			map.put("required_point",new Integer(required_point));
		if ( start_date_time_is_modified )
			map.put("start_date_time",start_date_time);
		if ( end_date_time_is_modified )
			map.put("end_date_time",end_date_time);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( category_no_is_set )
				map.put("category_no",new Integer(category_no));
			if ( premium_name_is_set )
				map.put("premium_name",premium_name);
			if ( required_point_is_set )
				map.put("required_point",new Integer(required_point));
			if ( start_date_time_is_set )
				map.put("start_date_time",start_date_time);
			if ( end_date_time_is_set )
				map.put("end_date_time",end_date_time);
			map.put("last_updater",last_updater);
			map.put("created_timestamp",created_timestamp);
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
   * <em>category_no</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getCategoryNo()
  {
    return category_no;
  }


  /** 
   * <em>category_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCategoryNoIsSet() {
    return category_no_is_set;
  }


  /** 
   * <em>category_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCategoryNoIsModified() {
    return category_no_is_modified;
  }


  /** 
   * <em>premium_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPremiumNo()
  {
    return premium_no;
  }


  /** 
   * <em>premium_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPremiumNoIsSet() {
    return premium_no_is_set;
  }


  /** 
   * <em>premium_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPremiumNoIsModified() {
    return premium_no_is_modified;
  }


  /** 
   * <em>premium_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPremiumName()
  {
    return premium_name;
  }


  /** 
   * <em>premium_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPremiumNameIsSet() {
    return premium_name_is_set;
  }


  /** 
   * <em>premium_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPremiumNameIsModified() {
    return premium_name_is_modified;
  }


  /** 
   * <em>required_point</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getRequiredPoint()
  {
    return required_point;
  }


  /** 
   * <em>required_point</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRequiredPointIsSet() {
    return required_point_is_set;
  }


  /** 
   * <em>required_point</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRequiredPointIsModified() {
    return required_point_is_modified;
  }


  /** 
   * <em>start_date_time</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getStartDateTime()
  {
    return start_date_time;
  }


  /** 
   * <em>start_date_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStartDateTimeIsSet() {
    return start_date_time_is_set;
  }


  /** 
   * <em>start_date_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStartDateTimeIsModified() {
    return start_date_time_is_modified;
  }


  /** 
   * <em>end_date_time</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getEndDateTime()
  {
    return end_date_time;
  }


  /** 
   * <em>end_date_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndDateTimeIsSet() {
    return end_date_time_is_set;
  }


  /** 
   * <em>end_date_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndDateTimeIsModified() {
    return end_date_time_is_modified;
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
    return new PointPremiumMasterPK(institution_code, premium_no);
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
   * <em>category_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_categoryNo <em>category_no</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setCategoryNo( int p_categoryNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    category_no = p_categoryNo;
    category_no_is_set = true;
    category_no_is_modified = true;
  }


  /** 
   * <em>premium_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_premiumNo <em>premium_no</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setPremiumNo( String p_premiumNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    premium_no = p_premiumNo;
    premium_no_is_set = true;
    premium_no_is_modified = true;
  }


  /** 
   * <em>premium_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_premiumName <em>premium_name</em>�J�����̒l������킷80���ȉ���String�l 
   */
  public final void setPremiumName( String p_premiumName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    premium_name = p_premiumName;
    premium_name_is_set = true;
    premium_name_is_modified = true;
  }


  /** 
   * <em>required_point</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_requiredPoint <em>required_point</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public final void setRequiredPoint( int p_requiredPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    required_point = p_requiredPoint;
    required_point_is_set = true;
    required_point_is_modified = true;
  }


  /** 
   * <em>start_date_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_startDateTime <em>start_date_time</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setStartDateTime( java.sql.Timestamp p_startDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    start_date_time = p_startDateTime;
    start_date_time_is_set = true;
    start_date_time_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>start_date_time</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setStartDateTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    start_date_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    start_date_time_is_set = true;
    start_date_time_is_modified = true;
  }


  /** 
   * <em>end_date_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_endDateTime <em>end_date_time</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setEndDateTime( java.sql.Timestamp p_endDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    end_date_time = p_endDateTime;
    end_date_time_is_set = true;
    end_date_time_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>end_date_time</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setEndDateTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    end_date_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    end_date_time_is_set = true;
    end_date_time_is_modified = true;
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
            case 'c':
                if ( name.equals("category_no") ) {
                    return new Integer( category_no );
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("end_date_time") ) {
                    return this.end_date_time;
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
                if ( name.equals("premium_no") ) {
                    return this.premium_no;
                }
                else if ( name.equals("premium_name") ) {
                    return this.premium_name;
                }
                break;
            case 'r':
                if ( name.equals("required_point") ) {
                    return new Integer( required_point );
                }
                break;
            case 's':
                if ( name.equals("start_date_time") ) {
                    return this.start_date_time;
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
                if ( name.equals("category_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'category_no' must be Integer: '"+value+"' is not." );
                    this.category_no = ((Integer) value).intValue();
                    if (this.category_no_is_set)
                        this.category_no_is_modified = true;
                    this.category_no_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
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
            case 'e':
                if ( name.equals("end_date_time") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'end_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.end_date_time = (java.sql.Timestamp) value;
                    if (this.end_date_time_is_set)
                        this.end_date_time_is_modified = true;
                    this.end_date_time_is_set = true;
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
            case 'p':
                if ( name.equals("premium_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'premium_no' must be String: '"+value+"' is not." );
                    this.premium_no = (String) value;
                    if (this.premium_no_is_set)
                        this.premium_no_is_modified = true;
                    this.premium_no_is_set = true;
                    return;
                }
                else if ( name.equals("premium_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'premium_name' must be String: '"+value+"' is not." );
                    this.premium_name = (String) value;
                    if (this.premium_name_is_set)
                        this.premium_name_is_modified = true;
                    this.premium_name_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("required_point") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'required_point' must be Integer: '"+value+"' is not." );
                    this.required_point = ((Integer) value).intValue();
                    if (this.required_point_is_set)
                        this.required_point_is_modified = true;
                    this.required_point_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("start_date_time") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'start_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.start_date_time = (java.sql.Timestamp) value;
                    if (this.start_date_time_is_set)
                        this.start_date_time_is_modified = true;
                    this.start_date_time_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
