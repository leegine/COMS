head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.50.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointTotalParams.java;


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
 * point_total�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link PointTotalRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link PointTotalParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link PointTotalParams}��{@@link PointTotalRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PointTotalPK 
 * @@see PointTotalRow 
 */
public class PointTotalParams extends Params implements PointTotalRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "point_total";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = PointTotalRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return PointTotalRow.TYPE;
  }


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
   * <em>period</em>�J�����̒l 
   */
  public  String  period;

  /** 
   * <em>total_get_point</em>�J�����̒l 
   */
  public  Integer  total_get_point;

  /** 
   * <em>total_apply_point</em>�J�����̒l 
   */
  public  Integer  total_apply_point;

  /** 
   * <em>total_adjust_point</em>�J�����̒l 
   */
  public  Integer  total_adjust_point;

  /** 
   * <em>withdrawn_apply_point</em>�J�����̒l 
   */
  public  Integer  withdrawn_apply_point;

  /** 
   * <em>withdrawn_adjust_point</em>�J�����̒l 
   */
  public  Integer  withdrawn_adjust_point;

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
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean period_is_set = false;
  private boolean period_is_modified = false;
  private boolean total_get_point_is_set = false;
  private boolean total_get_point_is_modified = false;
  private boolean total_apply_point_is_set = false;
  private boolean total_apply_point_is_modified = false;
  private boolean total_adjust_point_is_set = false;
  private boolean total_adjust_point_is_modified = false;
  private boolean withdrawn_apply_point_is_set = false;
  private boolean withdrawn_apply_point_is_modified = false;
  private boolean withdrawn_adjust_point_is_set = false;
  private boolean withdrawn_adjust_point_is_modified = false;
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
      + "," + "branch_code=" + branch_code
      + "," + "account_code=" + account_code
      + "," + "period=" + period
      + "," + "total_get_point=" +total_get_point
      + "," + "total_apply_point=" +total_apply_point
      + "," + "total_adjust_point=" +total_adjust_point
      + "," + "withdrawn_apply_point=" +withdrawn_apply_point
      + "," + "withdrawn_adjust_point=" +withdrawn_adjust_point
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��PointTotalParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public PointTotalParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_code_is_modified = true;
    period_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���PointTotalRow�I�u�W�F�N�g�̒l�𗘗p����PointTotalParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����PointTotalRow�I�u�W�F�N�g 
   */
  public PointTotalParams( PointTotalRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    period = p_row.getPeriod();
    period_is_set = p_row.getPeriodIsSet();
    period_is_modified = p_row.getPeriodIsModified();
    if ( !p_row.getTotalGetPointIsNull() )
      total_get_point = new Integer( p_row.getTotalGetPoint() );
    total_get_point_is_set = p_row.getTotalGetPointIsSet();
    total_get_point_is_modified = p_row.getTotalGetPointIsModified();
    if ( !p_row.getTotalApplyPointIsNull() )
      total_apply_point = new Integer( p_row.getTotalApplyPoint() );
    total_apply_point_is_set = p_row.getTotalApplyPointIsSet();
    total_apply_point_is_modified = p_row.getTotalApplyPointIsModified();
    if ( !p_row.getTotalAdjustPointIsNull() )
      total_adjust_point = new Integer( p_row.getTotalAdjustPoint() );
    total_adjust_point_is_set = p_row.getTotalAdjustPointIsSet();
    total_adjust_point_is_modified = p_row.getTotalAdjustPointIsModified();
    if ( !p_row.getWithdrawnApplyPointIsNull() )
      withdrawn_apply_point = new Integer( p_row.getWithdrawnApplyPoint() );
    withdrawn_apply_point_is_set = p_row.getWithdrawnApplyPointIsSet();
    withdrawn_apply_point_is_modified = p_row.getWithdrawnApplyPointIsModified();
    if ( !p_row.getWithdrawnAdjustPointIsNull() )
      withdrawn_adjust_point = new Integer( p_row.getWithdrawnAdjustPoint() );
    withdrawn_adjust_point_is_set = p_row.getWithdrawnAdjustPointIsSet();
    withdrawn_adjust_point_is_modified = p_row.getWithdrawnAdjustPointIsModified();
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
      total_get_point_is_set = true;
      total_get_point_is_modified = true;
      total_apply_point_is_set = true;
      total_apply_point_is_modified = true;
      total_adjust_point_is_set = true;
      total_adjust_point_is_modified = true;
      withdrawn_apply_point_is_set = true;
      withdrawn_apply_point_is_modified = true;
      withdrawn_adjust_point_is_set = true;
      withdrawn_adjust_point_is_modified = true;
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
    if ( !( other instanceof PointTotalRow ) )
       return false;
    return fieldsEqual( (PointTotalRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�PointTotalRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( PointTotalRow row )
  {
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
    if ( period == null ) {
      if ( row.getPeriod() != null )
        return false;
    } else if ( !period.equals( row.getPeriod() ) ) {
        return false;
    }
    if ( total_get_point == null ) {
      if ( !row.getTotalGetPointIsNull() )
        return false;
    } else if ( row.getTotalGetPointIsNull() || ( total_get_point.intValue() != row.getTotalGetPoint() ) ) {
        return false;
    }
    if ( total_apply_point == null ) {
      if ( !row.getTotalApplyPointIsNull() )
        return false;
    } else if ( row.getTotalApplyPointIsNull() || ( total_apply_point.intValue() != row.getTotalApplyPoint() ) ) {
        return false;
    }
    if ( total_adjust_point == null ) {
      if ( !row.getTotalAdjustPointIsNull() )
        return false;
    } else if ( row.getTotalAdjustPointIsNull() || ( total_adjust_point.intValue() != row.getTotalAdjustPoint() ) ) {
        return false;
    }
    if ( withdrawn_apply_point == null ) {
      if ( !row.getWithdrawnApplyPointIsNull() )
        return false;
    } else if ( row.getWithdrawnApplyPointIsNull() || ( withdrawn_apply_point.intValue() != row.getWithdrawnApplyPoint() ) ) {
        return false;
    }
    if ( withdrawn_adjust_point == null ) {
      if ( !row.getWithdrawnAdjustPointIsNull() )
        return false;
    } else if ( row.getWithdrawnAdjustPointIsNull() || ( withdrawn_adjust_point.intValue() != row.getWithdrawnAdjustPoint() ) ) {
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
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (period!=null? period.hashCode(): 0) 
        + (total_get_point!=null? total_get_point.hashCode(): 0) 
        + (total_apply_point!=null? total_apply_point.hashCode(): 0) 
        + (total_adjust_point!=null? total_adjust_point.hashCode(): 0) 
        + (withdrawn_apply_point!=null? withdrawn_apply_point.hashCode(): 0) 
        + (withdrawn_adjust_point!=null? withdrawn_adjust_point.hashCode(): 0) 
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
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		map.put("period",period);
		if ( total_get_point != null )
			map.put("total_get_point",total_get_point);
		if ( total_apply_point != null )
			map.put("total_apply_point",total_apply_point);
		if ( total_adjust_point != null )
			map.put("total_adjust_point",total_adjust_point);
		if ( withdrawn_apply_point != null )
			map.put("withdrawn_apply_point",withdrawn_apply_point);
		if ( withdrawn_adjust_point != null )
			map.put("withdrawn_adjust_point",withdrawn_adjust_point);
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
		if ( total_get_point_is_modified )
			map.put("total_get_point",total_get_point);
		if ( total_apply_point_is_modified )
			map.put("total_apply_point",total_apply_point);
		if ( total_adjust_point_is_modified )
			map.put("total_adjust_point",total_adjust_point);
		if ( withdrawn_apply_point_is_modified )
			map.put("withdrawn_apply_point",withdrawn_apply_point);
		if ( withdrawn_adjust_point_is_modified )
			map.put("withdrawn_adjust_point",withdrawn_adjust_point);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("total_get_point",total_get_point);
			map.put("total_apply_point",total_apply_point);
			map.put("total_adjust_point",total_adjust_point);
			map.put("withdrawn_apply_point",withdrawn_apply_point);
			map.put("withdrawn_adjust_point",withdrawn_adjust_point);
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
   * <em>period</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPeriod()
  {
    return period;
  }


  /** 
   * <em>period</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPeriodIsSet() {
    return period_is_set;
  }


  /** 
   * <em>period</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPeriodIsModified() {
    return period_is_modified;
  }


  /** 
   * <em>total_get_point</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getTotalGetPoint()
  {
    return ( total_get_point==null? ((int)0): total_get_point.intValue() );
  }


  /** 
   * <em>total_get_point</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTotalGetPointIsNull()
  {
    return total_get_point == null;
  }


  /** 
   * <em>total_get_point</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTotalGetPointIsSet() {
    return total_get_point_is_set;
  }


  /** 
   * <em>total_get_point</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTotalGetPointIsModified() {
    return total_get_point_is_modified;
  }


  /** 
   * <em>total_apply_point</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getTotalApplyPoint()
  {
    return ( total_apply_point==null? ((int)0): total_apply_point.intValue() );
  }


  /** 
   * <em>total_apply_point</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTotalApplyPointIsNull()
  {
    return total_apply_point == null;
  }


  /** 
   * <em>total_apply_point</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTotalApplyPointIsSet() {
    return total_apply_point_is_set;
  }


  /** 
   * <em>total_apply_point</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTotalApplyPointIsModified() {
    return total_apply_point_is_modified;
  }


  /** 
   * <em>total_adjust_point</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getTotalAdjustPoint()
  {
    return ( total_adjust_point==null? ((int)0): total_adjust_point.intValue() );
  }


  /** 
   * <em>total_adjust_point</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTotalAdjustPointIsNull()
  {
    return total_adjust_point == null;
  }


  /** 
   * <em>total_adjust_point</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTotalAdjustPointIsSet() {
    return total_adjust_point_is_set;
  }


  /** 
   * <em>total_adjust_point</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTotalAdjustPointIsModified() {
    return total_adjust_point_is_modified;
  }


  /** 
   * <em>withdrawn_apply_point</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getWithdrawnApplyPoint()
  {
    return ( withdrawn_apply_point==null? ((int)0): withdrawn_apply_point.intValue() );
  }


  /** 
   * <em>withdrawn_apply_point</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getWithdrawnApplyPointIsNull()
  {
    return withdrawn_apply_point == null;
  }


  /** 
   * <em>withdrawn_apply_point</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getWithdrawnApplyPointIsSet() {
    return withdrawn_apply_point_is_set;
  }


  /** 
   * <em>withdrawn_apply_point</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getWithdrawnApplyPointIsModified() {
    return withdrawn_apply_point_is_modified;
  }


  /** 
   * <em>withdrawn_adjust_point</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getWithdrawnAdjustPoint()
  {
    return ( withdrawn_adjust_point==null? ((int)0): withdrawn_adjust_point.intValue() );
  }


  /** 
   * <em>withdrawn_adjust_point</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getWithdrawnAdjustPointIsNull()
  {
    return withdrawn_adjust_point == null;
  }


  /** 
   * <em>withdrawn_adjust_point</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getWithdrawnAdjustPointIsSet() {
    return withdrawn_adjust_point_is_set;
  }


  /** 
   * <em>withdrawn_adjust_point</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getWithdrawnAdjustPointIsModified() {
    return withdrawn_adjust_point_is_modified;
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
    return new PointTotalPK(institution_code, branch_code, account_code, period);
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
   * <em>period</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_period <em>period</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setPeriod( String p_period )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    period = p_period;
    period_is_set = true;
    period_is_modified = true;
  }


  /** 
   * <em>total_get_point</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_totalGetPoint <em>total_get_point</em>�J�����̒l������킷9���ȉ���int�l 
   */
  public final void setTotalGetPoint( int p_totalGetPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    total_get_point = new Integer(p_totalGetPoint);
    total_get_point_is_set = true;
    total_get_point_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>total_get_point</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTotalGetPoint( Integer p_totalGetPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    total_get_point = p_totalGetPoint;
    total_get_point_is_set = true;
    total_get_point_is_modified = true;
  }


  /** 
   * <em>total_apply_point</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_totalApplyPoint <em>total_apply_point</em>�J�����̒l������킷9���ȉ���int�l 
   */
  public final void setTotalApplyPoint( int p_totalApplyPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    total_apply_point = new Integer(p_totalApplyPoint);
    total_apply_point_is_set = true;
    total_apply_point_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>total_apply_point</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTotalApplyPoint( Integer p_totalApplyPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    total_apply_point = p_totalApplyPoint;
    total_apply_point_is_set = true;
    total_apply_point_is_modified = true;
  }


  /** 
   * <em>total_adjust_point</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_totalAdjustPoint <em>total_adjust_point</em>�J�����̒l������킷9���ȉ���int�l 
   */
  public final void setTotalAdjustPoint( int p_totalAdjustPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    total_adjust_point = new Integer(p_totalAdjustPoint);
    total_adjust_point_is_set = true;
    total_adjust_point_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>total_adjust_point</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTotalAdjustPoint( Integer p_totalAdjustPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    total_adjust_point = p_totalAdjustPoint;
    total_adjust_point_is_set = true;
    total_adjust_point_is_modified = true;
  }


  /** 
   * <em>withdrawn_apply_point</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_withdrawnApplyPoint <em>withdrawn_apply_point</em>�J�����̒l������킷9���ȉ���int�l 
   */
  public final void setWithdrawnApplyPoint( int p_withdrawnApplyPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    withdrawn_apply_point = new Integer(p_withdrawnApplyPoint);
    withdrawn_apply_point_is_set = true;
    withdrawn_apply_point_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>withdrawn_apply_point</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setWithdrawnApplyPoint( Integer p_withdrawnApplyPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    withdrawn_apply_point = p_withdrawnApplyPoint;
    withdrawn_apply_point_is_set = true;
    withdrawn_apply_point_is_modified = true;
  }


  /** 
   * <em>withdrawn_adjust_point</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_withdrawnAdjustPoint <em>withdrawn_adjust_point</em>�J�����̒l������킷9���ȉ���int�l 
   */
  public final void setWithdrawnAdjustPoint( int p_withdrawnAdjustPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    withdrawn_adjust_point = new Integer(p_withdrawnAdjustPoint);
    withdrawn_adjust_point_is_set = true;
    withdrawn_adjust_point_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>withdrawn_adjust_point</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setWithdrawnAdjustPoint( Integer p_withdrawnAdjustPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    withdrawn_adjust_point = p_withdrawnAdjustPoint;
    withdrawn_adjust_point_is_set = true;
    withdrawn_adjust_point_is_modified = true;
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
                if ( name.equals("account_code") ) {
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
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("period") ) {
                    return this.period;
                }
                break;
            case 't':
                if ( name.equals("total_get_point") ) {
                    return this.total_get_point;
                }
                else if ( name.equals("total_apply_point") ) {
                    return this.total_apply_point;
                }
                else if ( name.equals("total_adjust_point") ) {
                    return this.total_adjust_point;
                }
                break;
            case 'w':
                if ( name.equals("withdrawn_apply_point") ) {
                    return this.withdrawn_apply_point;
                }
                else if ( name.equals("withdrawn_adjust_point") ) {
                    return this.withdrawn_adjust_point;
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
                if ( name.equals("period") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'period' must be String: '"+value+"' is not." );
                    this.period = (String) value;
                    if (this.period_is_set)
                        this.period_is_modified = true;
                    this.period_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("total_get_point") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'total_get_point' must be Integer: '"+value+"' is not." );
                    this.total_get_point = (Integer) value;
                    if (this.total_get_point_is_set)
                        this.total_get_point_is_modified = true;
                    this.total_get_point_is_set = true;
                    return;
                }
                else if ( name.equals("total_apply_point") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'total_apply_point' must be Integer: '"+value+"' is not." );
                    this.total_apply_point = (Integer) value;
                    if (this.total_apply_point_is_set)
                        this.total_apply_point_is_modified = true;
                    this.total_apply_point_is_set = true;
                    return;
                }
                else if ( name.equals("total_adjust_point") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'total_adjust_point' must be Integer: '"+value+"' is not." );
                    this.total_adjust_point = (Integer) value;
                    if (this.total_adjust_point_is_set)
                        this.total_adjust_point_is_modified = true;
                    this.total_adjust_point_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("withdrawn_apply_point") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'withdrawn_apply_point' must be Integer: '"+value+"' is not." );
                    this.withdrawn_apply_point = (Integer) value;
                    if (this.withdrawn_apply_point_is_set)
                        this.withdrawn_apply_point_is_modified = true;
                    this.withdrawn_apply_point_is_set = true;
                    return;
                }
                else if ( name.equals("withdrawn_adjust_point") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'withdrawn_adjust_point' must be Integer: '"+value+"' is not." );
                    this.withdrawn_adjust_point = (Integer) value;
                    if (this.withdrawn_adjust_point_is_set)
                        this.withdrawn_adjust_point_is_modified = true;
                    this.withdrawn_adjust_point_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
