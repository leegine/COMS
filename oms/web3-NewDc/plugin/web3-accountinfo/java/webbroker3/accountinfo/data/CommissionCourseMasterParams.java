head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.17.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommissionCourseMasterParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * commission_course_master�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link CommissionCourseMasterRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link CommissionCourseMasterParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link CommissionCourseMasterParams}��{@@link CommissionCourseMasterRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CommissionCourseMasterPK 
 * @@see CommissionCourseMasterRow 
 */
public class CommissionCourseMasterParams extends Params implements CommissionCourseMasterRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "commission_course_master";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = CommissionCourseMasterRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return CommissionCourseMasterRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>comm_product_code</em>�J�����̒l 
   */
  public  String  comm_product_code;

  /** 
   * <em>commission_course_div</em>�J�����̒l 
   */
  public  String  commission_course_div;

  /** 
   * <em>standard_name</em>�J�����̒l 
   */
  public  String  standard_name;

  /** 
   * <em>regist_end_day_spec</em>�J�����̒l 
   */
  public  String  regist_end_day_spec;

  /** 
   * <em>regist_end_time</em>�J�����̒l 
   */
  public  String  regist_end_time;

  /** 
   * <em>appli_start_date_div</em>�J�����̒l 
   */
  public  String  appli_start_date_div;

  /** 
   * <em>appli_start_day_count</em>�J�����̒l 
   */
  public  Integer  appli_start_day_count;

  /** 
   * <em>appli_start_end_time</em>�J�����̒l 
   */
  public  String  appli_start_end_time;

  /** 
   * <em>appli_term_div</em>�J�����̒l 
   */
  public  String  appli_term_div;

  /** 
   * <em>appli_term_date_count</em>�J�����̒l 
   */
  public  Integer  appli_term_date_count;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>commission_div</em>�J�����̒l 
   */
  public  String  commission_div;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean comm_product_code_is_set = false;
  private boolean comm_product_code_is_modified = false;
  private boolean commission_course_div_is_set = false;
  private boolean commission_course_div_is_modified = false;
  private boolean standard_name_is_set = false;
  private boolean standard_name_is_modified = false;
  private boolean regist_end_day_spec_is_set = false;
  private boolean regist_end_day_spec_is_modified = false;
  private boolean regist_end_time_is_set = false;
  private boolean regist_end_time_is_modified = false;
  private boolean appli_start_date_div_is_set = false;
  private boolean appli_start_date_div_is_modified = false;
  private boolean appli_start_day_count_is_set = false;
  private boolean appli_start_day_count_is_modified = false;
  private boolean appli_start_end_time_is_set = false;
  private boolean appli_start_end_time_is_modified = false;
  private boolean appli_term_div_is_set = false;
  private boolean appli_term_div_is_modified = false;
  private boolean appli_term_date_count_is_set = false;
  private boolean appli_term_date_count_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean commission_div_is_set = false;
  private boolean commission_div_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "comm_product_code=" + comm_product_code
      + "," + "commission_course_div=" + commission_course_div
      + "," + "standard_name=" +standard_name
      + "," + "regist_end_day_spec=" +regist_end_day_spec
      + "," + "regist_end_time=" +regist_end_time
      + "," + "appli_start_date_div=" +appli_start_date_div
      + "," + "appli_start_day_count=" +appli_start_day_count
      + "," + "appli_start_end_time=" +appli_start_end_time
      + "," + "appli_term_div=" +appli_term_div
      + "," + "appli_term_date_count=" +appli_term_date_count
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "commission_div=" +commission_div
      + "}";
  }


  /** 
   * �l�����ݒ��CommissionCourseMasterParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public CommissionCourseMasterParams() {
    institution_code_is_modified = true;
    comm_product_code_is_modified = true;
    commission_course_div_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���CommissionCourseMasterRow�I�u�W�F�N�g�̒l�𗘗p����CommissionCourseMasterParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����CommissionCourseMasterRow�I�u�W�F�N�g 
   */
  public CommissionCourseMasterParams( CommissionCourseMasterRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    comm_product_code = p_row.getCommProductCode();
    comm_product_code_is_set = p_row.getCommProductCodeIsSet();
    comm_product_code_is_modified = p_row.getCommProductCodeIsModified();
    commission_course_div = p_row.getCommissionCourseDiv();
    commission_course_div_is_set = p_row.getCommissionCourseDivIsSet();
    commission_course_div_is_modified = p_row.getCommissionCourseDivIsModified();
    standard_name = p_row.getStandardName();
    standard_name_is_set = p_row.getStandardNameIsSet();
    standard_name_is_modified = p_row.getStandardNameIsModified();
    regist_end_day_spec = p_row.getRegistEndDaySpec();
    regist_end_day_spec_is_set = p_row.getRegistEndDaySpecIsSet();
    regist_end_day_spec_is_modified = p_row.getRegistEndDaySpecIsModified();
    regist_end_time = p_row.getRegistEndTime();
    regist_end_time_is_set = p_row.getRegistEndTimeIsSet();
    regist_end_time_is_modified = p_row.getRegistEndTimeIsModified();
    appli_start_date_div = p_row.getAppliStartDateDiv();
    appli_start_date_div_is_set = p_row.getAppliStartDateDivIsSet();
    appli_start_date_div_is_modified = p_row.getAppliStartDateDivIsModified();
    if ( !p_row.getAppliStartDayCountIsNull() )
      appli_start_day_count = new Integer( p_row.getAppliStartDayCount() );
    appli_start_day_count_is_set = p_row.getAppliStartDayCountIsSet();
    appli_start_day_count_is_modified = p_row.getAppliStartDayCountIsModified();
    appli_start_end_time = p_row.getAppliStartEndTime();
    appli_start_end_time_is_set = p_row.getAppliStartEndTimeIsSet();
    appli_start_end_time_is_modified = p_row.getAppliStartEndTimeIsModified();
    appli_term_div = p_row.getAppliTermDiv();
    appli_term_div_is_set = p_row.getAppliTermDivIsSet();
    appli_term_div_is_modified = p_row.getAppliTermDivIsModified();
    if ( !p_row.getAppliTermDateCountIsNull() )
      appli_term_date_count = new Integer( p_row.getAppliTermDateCount() );
    appli_term_date_count_is_set = p_row.getAppliTermDateCountIsSet();
    appli_term_date_count_is_modified = p_row.getAppliTermDateCountIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    commission_div = p_row.getCommissionDiv();
    commission_div_is_set = p_row.getCommissionDivIsSet();
    commission_div_is_modified = p_row.getCommissionDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      standard_name_is_set = true;
      standard_name_is_modified = true;
      regist_end_day_spec_is_set = true;
      regist_end_day_spec_is_modified = true;
      regist_end_time_is_set = true;
      regist_end_time_is_modified = true;
      appli_start_date_div_is_set = true;
      appli_start_date_div_is_modified = true;
      appli_start_day_count_is_set = true;
      appli_start_day_count_is_modified = true;
      appli_start_end_time_is_set = true;
      appli_start_end_time_is_modified = true;
      appli_term_div_is_set = true;
      appli_term_div_is_modified = true;
      appli_term_date_count_is_set = true;
      appli_term_date_count_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      commission_div_is_set = true;
      commission_div_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof CommissionCourseMasterRow ) )
       return false;
    return fieldsEqual( (CommissionCourseMasterRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�CommissionCourseMasterRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( CommissionCourseMasterRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( comm_product_code == null ) {
      if ( row.getCommProductCode() != null )
        return false;
    } else if ( !comm_product_code.equals( row.getCommProductCode() ) ) {
        return false;
    }
    if ( commission_course_div == null ) {
      if ( row.getCommissionCourseDiv() != null )
        return false;
    } else if ( !commission_course_div.equals( row.getCommissionCourseDiv() ) ) {
        return false;
    }
    if ( standard_name == null ) {
      if ( row.getStandardName() != null )
        return false;
    } else if ( !standard_name.equals( row.getStandardName() ) ) {
        return false;
    }
    if ( regist_end_day_spec == null ) {
      if ( row.getRegistEndDaySpec() != null )
        return false;
    } else if ( !regist_end_day_spec.equals( row.getRegistEndDaySpec() ) ) {
        return false;
    }
    if ( regist_end_time == null ) {
      if ( row.getRegistEndTime() != null )
        return false;
    } else if ( !regist_end_time.equals( row.getRegistEndTime() ) ) {
        return false;
    }
    if ( appli_start_date_div == null ) {
      if ( row.getAppliStartDateDiv() != null )
        return false;
    } else if ( !appli_start_date_div.equals( row.getAppliStartDateDiv() ) ) {
        return false;
    }
    if ( appli_start_day_count == null ) {
      if ( !row.getAppliStartDayCountIsNull() )
        return false;
    } else if ( row.getAppliStartDayCountIsNull() || ( appli_start_day_count.intValue() != row.getAppliStartDayCount() ) ) {
        return false;
    }
    if ( appli_start_end_time == null ) {
      if ( row.getAppliStartEndTime() != null )
        return false;
    } else if ( !appli_start_end_time.equals( row.getAppliStartEndTime() ) ) {
        return false;
    }
    if ( appli_term_div == null ) {
      if ( row.getAppliTermDiv() != null )
        return false;
    } else if ( !appli_term_div.equals( row.getAppliTermDiv() ) ) {
        return false;
    }
    if ( appli_term_date_count == null ) {
      if ( !row.getAppliTermDateCountIsNull() )
        return false;
    } else if ( row.getAppliTermDateCountIsNull() || ( appli_term_date_count.intValue() != row.getAppliTermDateCount() ) ) {
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
    if ( commission_div == null ) {
      if ( row.getCommissionDiv() != null )
        return false;
    } else if ( !commission_div.equals( row.getCommissionDiv() ) ) {
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
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (commission_course_div!=null? commission_course_div.hashCode(): 0) 
        + (standard_name!=null? standard_name.hashCode(): 0) 
        + (regist_end_day_spec!=null? regist_end_day_spec.hashCode(): 0) 
        + (regist_end_time!=null? regist_end_time.hashCode(): 0) 
        + (appli_start_date_div!=null? appli_start_date_div.hashCode(): 0) 
        + (appli_start_day_count!=null? appli_start_day_count.hashCode(): 0) 
        + (appli_start_end_time!=null? appli_start_end_time.hashCode(): 0) 
        + (appli_term_div!=null? appli_term_div.hashCode(): 0) 
        + (appli_term_date_count!=null? appli_term_date_count.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (commission_div!=null? commission_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !standard_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'standard_name' must be set before inserting.");
		if ( !regist_end_day_spec_is_set )
			throw new IllegalArgumentException("non-nullable field 'regist_end_day_spec' must be set before inserting.");
		if ( !regist_end_time_is_set )
			throw new IllegalArgumentException("non-nullable field 'regist_end_time' must be set before inserting.");
		if ( !appli_start_date_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_start_date_div' must be set before inserting.");
		if ( !appli_start_end_time_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_start_end_time' must be set before inserting.");
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
		map.put("institution_code",institution_code);
		map.put("comm_product_code",comm_product_code);
		map.put("commission_course_div",commission_course_div);
		map.put("standard_name",standard_name);
		map.put("regist_end_day_spec",regist_end_day_spec);
		map.put("regist_end_time",regist_end_time);
		map.put("appli_start_date_div",appli_start_date_div);
		if ( appli_start_day_count != null )
			map.put("appli_start_day_count",appli_start_day_count);
		map.put("appli_start_end_time",appli_start_end_time);
		if ( appli_term_div != null )
			map.put("appli_term_div",appli_term_div);
		if ( appli_term_date_count != null )
			map.put("appli_term_date_count",appli_term_date_count);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( commission_div != null )
			map.put("commission_div",commission_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( standard_name_is_modified )
			map.put("standard_name",standard_name);
		if ( regist_end_day_spec_is_modified )
			map.put("regist_end_day_spec",regist_end_day_spec);
		if ( regist_end_time_is_modified )
			map.put("regist_end_time",regist_end_time);
		if ( appli_start_date_div_is_modified )
			map.put("appli_start_date_div",appli_start_date_div);
		if ( appli_start_day_count_is_modified )
			map.put("appli_start_day_count",appli_start_day_count);
		if ( appli_start_end_time_is_modified )
			map.put("appli_start_end_time",appli_start_end_time);
		if ( appli_term_div_is_modified )
			map.put("appli_term_div",appli_term_div);
		if ( appli_term_date_count_is_modified )
			map.put("appli_term_date_count",appli_term_date_count);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( commission_div_is_modified )
			map.put("commission_div",commission_div);
		if (map.size() == 0) {
			if ( standard_name_is_set )
				map.put("standard_name",standard_name);
			if ( regist_end_day_spec_is_set )
				map.put("regist_end_day_spec",regist_end_day_spec);
			if ( regist_end_time_is_set )
				map.put("regist_end_time",regist_end_time);
			if ( appli_start_date_div_is_set )
				map.put("appli_start_date_div",appli_start_date_div);
			map.put("appli_start_day_count",appli_start_day_count);
			if ( appli_start_end_time_is_set )
				map.put("appli_start_end_time",appli_start_end_time);
			map.put("appli_term_div",appli_term_div);
			map.put("appli_term_date_count",appli_term_date_count);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("commission_div",commission_div);
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
   * <em>comm_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCommProductCode()
  {
    return comm_product_code;
  }


  /** 
   * <em>comm_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommProductCodeIsSet() {
    return comm_product_code_is_set;
  }


  /** 
   * <em>comm_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommProductCodeIsModified() {
    return comm_product_code_is_modified;
  }


  /** 
   * <em>commission_course_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCommissionCourseDiv()
  {
    return commission_course_div;
  }


  /** 
   * <em>commission_course_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommissionCourseDivIsSet() {
    return commission_course_div_is_set;
  }


  /** 
   * <em>commission_course_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommissionCourseDivIsModified() {
    return commission_course_div_is_modified;
  }


  /** 
   * <em>standard_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStandardName()
  {
    return standard_name;
  }


  /** 
   * <em>standard_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStandardNameIsSet() {
    return standard_name_is_set;
  }


  /** 
   * <em>standard_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStandardNameIsModified() {
    return standard_name_is_modified;
  }


  /** 
   * <em>regist_end_day_spec</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRegistEndDaySpec()
  {
    return regist_end_day_spec;
  }


  /** 
   * <em>regist_end_day_spec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegistEndDaySpecIsSet() {
    return regist_end_day_spec_is_set;
  }


  /** 
   * <em>regist_end_day_spec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegistEndDaySpecIsModified() {
    return regist_end_day_spec_is_modified;
  }


  /** 
   * <em>regist_end_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRegistEndTime()
  {
    return regist_end_time;
  }


  /** 
   * <em>regist_end_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegistEndTimeIsSet() {
    return regist_end_time_is_set;
  }


  /** 
   * <em>regist_end_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegistEndTimeIsModified() {
    return regist_end_time_is_modified;
  }


  /** 
   * <em>appli_start_date_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAppliStartDateDiv()
  {
    return appli_start_date_div;
  }


  /** 
   * <em>appli_start_date_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartDateDivIsSet() {
    return appli_start_date_div_is_set;
  }


  /** 
   * <em>appli_start_date_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartDateDivIsModified() {
    return appli_start_date_div_is_modified;
  }


  /** 
   * <em>appli_start_day_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getAppliStartDayCount()
  {
    return ( appli_start_day_count==null? ((int)0): appli_start_day_count.intValue() );
  }


  /** 
   * <em>appli_start_day_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getAppliStartDayCountIsNull()
  {
    return appli_start_day_count == null;
  }


  /** 
   * <em>appli_start_day_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartDayCountIsSet() {
    return appli_start_day_count_is_set;
  }


  /** 
   * <em>appli_start_day_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartDayCountIsModified() {
    return appli_start_day_count_is_modified;
  }


  /** 
   * <em>appli_start_end_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAppliStartEndTime()
  {
    return appli_start_end_time;
  }


  /** 
   * <em>appli_start_end_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartEndTimeIsSet() {
    return appli_start_end_time_is_set;
  }


  /** 
   * <em>appli_start_end_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartEndTimeIsModified() {
    return appli_start_end_time_is_modified;
  }


  /** 
   * <em>appli_term_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAppliTermDiv()
  {
    return appli_term_div;
  }


  /** 
   * <em>appli_term_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliTermDivIsSet() {
    return appli_term_div_is_set;
  }


  /** 
   * <em>appli_term_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliTermDivIsModified() {
    return appli_term_div_is_modified;
  }


  /** 
   * <em>appli_term_date_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getAppliTermDateCount()
  {
    return ( appli_term_date_count==null? ((int)0): appli_term_date_count.intValue() );
  }


  /** 
   * <em>appli_term_date_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getAppliTermDateCountIsNull()
  {
    return appli_term_date_count == null;
  }


  /** 
   * <em>appli_term_date_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliTermDateCountIsSet() {
    return appli_term_date_count_is_set;
  }


  /** 
   * <em>appli_term_date_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliTermDateCountIsModified() {
    return appli_term_date_count_is_modified;
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
   * <em>commission_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCommissionDiv()
  {
    return commission_div;
  }


  /** 
   * <em>commission_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommissionDivIsSet() {
    return commission_div_is_set;
  }


  /** 
   * <em>commission_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommissionDivIsModified() {
    return commission_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new CommissionCourseMasterPK(institution_code, comm_product_code, commission_course_div);
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
   * <em>comm_product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_commProductCode <em>comm_product_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setCommProductCode( String p_commProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comm_product_code = p_commProductCode;
    comm_product_code_is_set = true;
    comm_product_code_is_modified = true;
  }


  /** 
   * <em>commission_course_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_commissionCourseDiv <em>commission_course_div</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setCommissionCourseDiv( String p_commissionCourseDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_course_div = p_commissionCourseDiv;
    commission_course_div_is_set = true;
    commission_course_div_is_modified = true;
  }


  /** 
   * <em>standard_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_standardName <em>standard_name</em>�J�����̒l������킷64���ȉ���String�l 
   */
  public final void setStandardName( String p_standardName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    standard_name = p_standardName;
    standard_name_is_set = true;
    standard_name_is_modified = true;
  }


  /** 
   * <em>regist_end_day_spec</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_registEndDaySpec <em>regist_end_day_spec</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setRegistEndDaySpec( String p_registEndDaySpec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_end_day_spec = p_registEndDaySpec;
    regist_end_day_spec_is_set = true;
    regist_end_day_spec_is_modified = true;
  }


  /** 
   * <em>regist_end_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_registEndTime <em>regist_end_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setRegistEndTime( String p_registEndTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_end_time = p_registEndTime;
    regist_end_time_is_set = true;
    regist_end_time_is_modified = true;
  }


  /** 
   * <em>appli_start_date_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliStartDateDiv <em>appli_start_date_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAppliStartDateDiv( String p_appliStartDateDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_date_div = p_appliStartDateDiv;
    appli_start_date_div_is_set = true;
    appli_start_date_div_is_modified = true;
  }


  /** 
   * <em>appli_start_day_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliStartDayCount <em>appli_start_day_count</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setAppliStartDayCount( int p_appliStartDayCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_day_count = new Integer(p_appliStartDayCount);
    appli_start_day_count_is_set = true;
    appli_start_day_count_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>appli_start_day_count</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setAppliStartDayCount( Integer p_appliStartDayCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_day_count = p_appliStartDayCount;
    appli_start_day_count_is_set = true;
    appli_start_day_count_is_modified = true;
  }


  /** 
   * <em>appli_start_end_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliStartEndTime <em>appli_start_end_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setAppliStartEndTime( String p_appliStartEndTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_end_time = p_appliStartEndTime;
    appli_start_end_time_is_set = true;
    appli_start_end_time_is_modified = true;
  }


  /** 
   * <em>appli_term_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliTermDiv <em>appli_term_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAppliTermDiv( String p_appliTermDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_term_div = p_appliTermDiv;
    appli_term_div_is_set = true;
    appli_term_div_is_modified = true;
  }


  /** 
   * <em>appli_term_date_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliTermDateCount <em>appli_term_date_count</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setAppliTermDateCount( int p_appliTermDateCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_term_date_count = new Integer(p_appliTermDateCount);
    appli_term_date_count_is_set = true;
    appli_term_date_count_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>appli_term_date_count</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setAppliTermDateCount( Integer p_appliTermDateCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_term_date_count = p_appliTermDateCount;
    appli_term_date_count_is_set = true;
    appli_term_date_count_is_modified = true;
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
   * <em>commission_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_commissionDiv <em>commission_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setCommissionDiv( String p_commissionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_div = p_commissionDiv;
    commission_div_is_set = true;
    commission_div_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("appli_start_date_div") ) {
                    return this.appli_start_date_div;
                }
                else if ( name.equals("appli_start_day_count") ) {
                    return this.appli_start_day_count;
                }
                else if ( name.equals("appli_start_end_time") ) {
                    return this.appli_start_end_time;
                }
                else if ( name.equals("appli_term_div") ) {
                    return this.appli_term_div;
                }
                else if ( name.equals("appli_term_date_count") ) {
                    return this.appli_term_date_count;
                }
                break;
            case 'c':
                if ( name.equals("comm_product_code") ) {
                    return this.comm_product_code;
                }
                else if ( name.equals("commission_course_div") ) {
                    return this.commission_course_div;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("commission_div") ) {
                    return this.commission_div;
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
            case 'r':
                if ( name.equals("regist_end_day_spec") ) {
                    return this.regist_end_day_spec;
                }
                else if ( name.equals("regist_end_time") ) {
                    return this.regist_end_time;
                }
                break;
            case 's':
                if ( name.equals("standard_name") ) {
                    return this.standard_name;
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
                if ( name.equals("appli_start_date_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'appli_start_date_div' must be String: '"+value+"' is not." );
                    this.appli_start_date_div = (String) value;
                    if (this.appli_start_date_div_is_set)
                        this.appli_start_date_div_is_modified = true;
                    this.appli_start_date_div_is_set = true;
                    return;
                }
                else if ( name.equals("appli_start_day_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'appli_start_day_count' must be Integer: '"+value+"' is not." );
                    this.appli_start_day_count = (Integer) value;
                    if (this.appli_start_day_count_is_set)
                        this.appli_start_day_count_is_modified = true;
                    this.appli_start_day_count_is_set = true;
                    return;
                }
                else if ( name.equals("appli_start_end_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'appli_start_end_time' must be String: '"+value+"' is not." );
                    this.appli_start_end_time = (String) value;
                    if (this.appli_start_end_time_is_set)
                        this.appli_start_end_time_is_modified = true;
                    this.appli_start_end_time_is_set = true;
                    return;
                }
                else if ( name.equals("appli_term_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'appli_term_div' must be String: '"+value+"' is not." );
                    this.appli_term_div = (String) value;
                    if (this.appli_term_div_is_set)
                        this.appli_term_div_is_modified = true;
                    this.appli_term_div_is_set = true;
                    return;
                }
                else if ( name.equals("appli_term_date_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'appli_term_date_count' must be Integer: '"+value+"' is not." );
                    this.appli_term_date_count = (Integer) value;
                    if (this.appli_term_date_count_is_set)
                        this.appli_term_date_count_is_modified = true;
                    this.appli_term_date_count_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("comm_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comm_product_code' must be String: '"+value+"' is not." );
                    this.comm_product_code = (String) value;
                    if (this.comm_product_code_is_set)
                        this.comm_product_code_is_modified = true;
                    this.comm_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("commission_course_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commission_course_div' must be String: '"+value+"' is not." );
                    this.commission_course_div = (String) value;
                    if (this.commission_course_div_is_set)
                        this.commission_course_div_is_modified = true;
                    this.commission_course_div_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("commission_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commission_div' must be String: '"+value+"' is not." );
                    this.commission_div = (String) value;
                    if (this.commission_div_is_set)
                        this.commission_div_is_modified = true;
                    this.commission_div_is_set = true;
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
            case 'r':
                if ( name.equals("regist_end_day_spec") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'regist_end_day_spec' must be String: '"+value+"' is not." );
                    this.regist_end_day_spec = (String) value;
                    if (this.regist_end_day_spec_is_set)
                        this.regist_end_day_spec_is_modified = true;
                    this.regist_end_day_spec_is_set = true;
                    return;
                }
                else if ( name.equals("regist_end_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'regist_end_time' must be String: '"+value+"' is not." );
                    this.regist_end_time = (String) value;
                    if (this.regist_end_time_is_set)
                        this.regist_end_time_is_modified = true;
                    this.regist_end_time_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("standard_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'standard_name' must be String: '"+value+"' is not." );
                    this.standard_name = (String) value;
                    if (this.standard_name_is_set)
                        this.standard_name_is_modified = true;
                    this.standard_name_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
