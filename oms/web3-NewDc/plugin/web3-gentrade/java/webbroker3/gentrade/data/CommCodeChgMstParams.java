head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.41.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CommCodeChgMstParams.java;


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
 * comm_code_chg_mst�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link CommCodeChgMstRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link CommCodeChgMstParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link CommCodeChgMstParams}��{@@link CommCodeChgMstRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CommCodeChgMstPK 
 * @@see CommCodeChgMstRow 
 */
public class CommCodeChgMstParams extends Params implements CommCodeChgMstRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "comm_code_chg_mst";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = CommCodeChgMstRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return CommCodeChgMstRow.TYPE;
  }


  /** 
   * <em>branch_id</em>�J�����̒l 
   */
  public  long  branch_id;

  /** 
   * <em>comm_product_code</em>�J�����̒l 
   */
  public  String  comm_product_code;

  /** 
   * <em>commission_no</em>�J�����̒l 
   */
  public  String  commission_no;

  /** 
   * <em>appli_start_date</em>�J�����̒l 
   */
  public  String  appli_start_date;

  /** 
   * <em>commission_course_div</em>�J�����̒l 
   */
  public  String  commission_course_div;

  /** 
   * <em>initial_set_div</em>�J�����̒l 
   */
  public  String  initial_set_div;

  /** 
   * <em>account_charge_ratio</em>�J�����̒l 
   */
  public  double  account_charge_ratio;

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

  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean comm_product_code_is_set = false;
  private boolean comm_product_code_is_modified = false;
  private boolean commission_no_is_set = false;
  private boolean commission_no_is_modified = false;
  private boolean appli_start_date_is_set = false;
  private boolean appli_start_date_is_modified = false;
  private boolean commission_course_div_is_set = false;
  private boolean commission_course_div_is_modified = false;
  private boolean initial_set_div_is_set = false;
  private boolean initial_set_div_is_modified = false;
  private boolean account_charge_ratio_is_set = false;
  private boolean account_charge_ratio_is_modified = false;
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
       + "branch_id=" + branch_id
      + "," + "comm_product_code=" + comm_product_code
      + "," + "commission_no=" + commission_no
      + "," + "appli_start_date=" + appli_start_date
      + "," + "commission_course_div=" +commission_course_div
      + "," + "initial_set_div=" +initial_set_div
      + "," + "account_charge_ratio=" +account_charge_ratio
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��CommCodeChgMstParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public CommCodeChgMstParams() {
    branch_id_is_modified = true;
    comm_product_code_is_modified = true;
    commission_no_is_modified = true;
    appli_start_date_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���CommCodeChgMstRow�I�u�W�F�N�g�̒l�𗘗p����CommCodeChgMstParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����CommCodeChgMstRow�I�u�W�F�N�g 
   */
  public CommCodeChgMstParams( CommCodeChgMstRow p_row )
  {
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    comm_product_code = p_row.getCommProductCode();
    comm_product_code_is_set = p_row.getCommProductCodeIsSet();
    comm_product_code_is_modified = p_row.getCommProductCodeIsModified();
    commission_no = p_row.getCommissionNo();
    commission_no_is_set = p_row.getCommissionNoIsSet();
    commission_no_is_modified = p_row.getCommissionNoIsModified();
    appli_start_date = p_row.getAppliStartDate();
    appli_start_date_is_set = p_row.getAppliStartDateIsSet();
    appli_start_date_is_modified = p_row.getAppliStartDateIsModified();
    commission_course_div = p_row.getCommissionCourseDiv();
    commission_course_div_is_set = p_row.getCommissionCourseDivIsSet();
    commission_course_div_is_modified = p_row.getCommissionCourseDivIsModified();
    initial_set_div = p_row.getInitialSetDiv();
    initial_set_div_is_set = p_row.getInitialSetDivIsSet();
    initial_set_div_is_modified = p_row.getInitialSetDivIsModified();
    account_charge_ratio = p_row.getAccountChargeRatio();
    account_charge_ratio_is_set = p_row.getAccountChargeRatioIsSet();
    account_charge_ratio_is_modified = p_row.getAccountChargeRatioIsModified();
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
      commission_course_div_is_set = true;
      commission_course_div_is_modified = true;
      initial_set_div_is_set = true;
      initial_set_div_is_modified = true;
      account_charge_ratio_is_set = true;
      account_charge_ratio_is_modified = true;
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
    if ( !( other instanceof CommCodeChgMstRow ) )
       return false;
    return fieldsEqual( (CommCodeChgMstRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�CommCodeChgMstRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( CommCodeChgMstRow row )
  {
    if ( branch_id != row.getBranchId() )
      return false;
    if ( comm_product_code == null ) {
      if ( row.getCommProductCode() != null )
        return false;
    } else if ( !comm_product_code.equals( row.getCommProductCode() ) ) {
        return false;
    }
    if ( commission_no == null ) {
      if ( row.getCommissionNo() != null )
        return false;
    } else if ( !commission_no.equals( row.getCommissionNo() ) ) {
        return false;
    }
    if ( appli_start_date == null ) {
      if ( row.getAppliStartDate() != null )
        return false;
    } else if ( !appli_start_date.equals( row.getAppliStartDate() ) ) {
        return false;
    }
    if ( commission_course_div == null ) {
      if ( row.getCommissionCourseDiv() != null )
        return false;
    } else if ( !commission_course_div.equals( row.getCommissionCourseDiv() ) ) {
        return false;
    }
    if ( initial_set_div == null ) {
      if ( row.getInitialSetDiv() != null )
        return false;
    } else if ( !initial_set_div.equals( row.getInitialSetDiv() ) ) {
        return false;
    }
    if ( account_charge_ratio != row.getAccountChargeRatio() )
      return false;
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
      return  ((int) branch_id)
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (commission_no!=null? commission_no.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
        + (commission_course_div!=null? commission_course_div.hashCode(): 0) 
        + (initial_set_div!=null? initial_set_div.hashCode(): 0) 
        + ((int) account_charge_ratio)
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
		if ( !commission_course_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'commission_course_div' must be set before inserting.");
		if ( !initial_set_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'initial_set_div' must be set before inserting.");
		if ( !account_charge_ratio_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_charge_ratio' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
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
		map.put("branch_id",new Long(branch_id));
		map.put("comm_product_code",comm_product_code);
		map.put("commission_no",commission_no);
		map.put("appli_start_date",appli_start_date);
		map.put("commission_course_div",commission_course_div);
		map.put("initial_set_div",initial_set_div);
		map.put("account_charge_ratio",new Double(account_charge_ratio));
		map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( commission_course_div_is_modified )
			map.put("commission_course_div",commission_course_div);
		if ( initial_set_div_is_modified )
			map.put("initial_set_div",initial_set_div);
		if ( account_charge_ratio_is_modified )
			map.put("account_charge_ratio",new Double(account_charge_ratio));
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( commission_course_div_is_set )
				map.put("commission_course_div",commission_course_div);
			if ( initial_set_div_is_set )
				map.put("initial_set_div",initial_set_div);
			if ( account_charge_ratio_is_set )
				map.put("account_charge_ratio",new Double(account_charge_ratio));
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
   * <em>branch_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
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
   * <em>commission_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCommissionNo()
  {
    return commission_no;
  }


  /** 
   * <em>commission_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommissionNoIsSet() {
    return commission_no_is_set;
  }


  /** 
   * <em>commission_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommissionNoIsModified() {
    return commission_no_is_modified;
  }


  /** 
   * <em>appli_start_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAppliStartDate()
  {
    return appli_start_date;
  }


  /** 
   * <em>appli_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartDateIsSet() {
    return appli_start_date_is_set;
  }


  /** 
   * <em>appli_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartDateIsModified() {
    return appli_start_date_is_modified;
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
   * <em>initial_set_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getInitialSetDiv()
  {
    return initial_set_div;
  }


  /** 
   * <em>initial_set_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInitialSetDivIsSet() {
    return initial_set_div_is_set;
  }


  /** 
   * <em>initial_set_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInitialSetDivIsModified() {
    return initial_set_div_is_modified;
  }


  /** 
   * <em>account_charge_ratio</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAccountChargeRatio()
  {
    return account_charge_ratio;
  }


  /** 
   * <em>account_charge_ratio</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountChargeRatioIsSet() {
    return account_charge_ratio_is_set;
  }


  /** 
   * <em>account_charge_ratio</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountChargeRatioIsModified() {
    return account_charge_ratio_is_modified;
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
    return new CommCodeChgMstPK(branch_id, comm_product_code, commission_no, appli_start_date);
  }


  /** 
   * <em>branch_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_branchId <em>branch_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
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
   * <em>commission_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_commissionNo <em>commission_no</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setCommissionNo( String p_commissionNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_no = p_commissionNo;
    commission_no_is_set = true;
    commission_no_is_modified = true;
  }


  /** 
   * <em>appli_start_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliStartDate <em>appli_start_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setAppliStartDate( String p_appliStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_date = p_appliStartDate;
    appli_start_date_is_set = true;
    appli_start_date_is_modified = true;
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
   * <em>initial_set_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_initialSetDiv <em>initial_set_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setInitialSetDiv( String p_initialSetDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    initial_set_div = p_initialSetDiv;
    initial_set_div_is_set = true;
    initial_set_div_is_modified = true;
  }


  /** 
   * <em>account_charge_ratio</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountChargeRatio <em>account_charge_ratio</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setAccountChargeRatio( double p_accountChargeRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_charge_ratio = p_accountChargeRatio;
    account_charge_ratio_is_set = true;
    account_charge_ratio_is_modified = true;
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
                if ( name.equals("appli_start_date") ) {
                    return this.appli_start_date;
                }
                else if ( name.equals("account_charge_ratio") ) {
                    return new Double( account_charge_ratio );
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                break;
            case 'c':
                if ( name.equals("comm_product_code") ) {
                    return this.comm_product_code;
                }
                else if ( name.equals("commission_no") ) {
                    return this.commission_no;
                }
                else if ( name.equals("commission_course_div") ) {
                    return this.commission_course_div;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("initial_set_div") ) {
                    return this.initial_set_div;
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
                if ( name.equals("appli_start_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'appli_start_date' must be String: '"+value+"' is not." );
                    this.appli_start_date = (String) value;
                    if (this.appli_start_date_is_set)
                        this.appli_start_date_is_modified = true;
                    this.appli_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("account_charge_ratio") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_charge_ratio' must be Double: '"+value+"' is not." );
                    this.account_charge_ratio = ((Double) value).doubleValue();
                    if (this.account_charge_ratio_is_set)
                        this.account_charge_ratio_is_modified = true;
                    this.account_charge_ratio_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
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
                else if ( name.equals("commission_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commission_no' must be String: '"+value+"' is not." );
                    this.commission_no = (String) value;
                    if (this.commission_no_is_set)
                        this.commission_no_is_modified = true;
                    this.commission_no_is_set = true;
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
                break;
            case 'i':
                if ( name.equals("initial_set_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'initial_set_div' must be String: '"+value+"' is not." );
                    this.initial_set_div = (String) value;
                    if (this.initial_set_div_is_set)
                        this.initial_set_div_is_modified = true;
                    this.initial_set_div_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
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
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
