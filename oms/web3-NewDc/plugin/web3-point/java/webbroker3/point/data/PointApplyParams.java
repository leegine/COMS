head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.50.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointApplyParams.java;


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
 * point_apply�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link PointApplyRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link PointApplyParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link PointApplyParams}��{@@link PointApplyRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PointApplyPK 
 * @@see PointApplyRow 
 */
public class PointApplyParams extends Params implements PointApplyRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "point_apply";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = PointApplyRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return PointApplyRow.TYPE;
  }


  /** 
   * <em>apply_id</em>�J�����̒l 
   */
  public  long  apply_id;

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
   * <em>trader_code</em>�J�����̒l 
   */
  public  String  trader_code;

  /** 
   * <em>premium_no</em>�J�����̒l 
   */
  public  String  premium_no;

  /** 
   * <em>used_point</em>�J�����̒l 
   */
  public  Integer  used_point;

  /** 
   * <em>apply_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  apply_timestamp;

  /** 
   * <em>apply_root_div</em>�J�����̒l 
   */
  public  String  apply_root_div;

  /** 
   * <em>apply_accept_div</em>�J�����̒l 
   */
  public  String  apply_accept_div;

  /** 
   * <em>apply_accept_user</em>�J�����̒l 
   */
  public  String  apply_accept_user;

  /** 
   * <em>apply_accept_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  apply_accept_timestamp;

  /** 
   * <em>apply_cancel_div</em>�J�����̒l 
   */
  public  String  apply_cancel_div;

  /** 
   * <em>apply_cancel_user</em>�J�����̒l 
   */
  public  String  apply_cancel_user;

  /** 
   * <em>apply_cancel_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  apply_cancel_timestamp;

  /** 
   * <em>last_updater</em>�J�����̒l 
   */
  public  String  last_updater;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean apply_id_is_set = false;
  private boolean apply_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean premium_no_is_set = false;
  private boolean premium_no_is_modified = false;
  private boolean used_point_is_set = false;
  private boolean used_point_is_modified = false;
  private boolean apply_timestamp_is_set = false;
  private boolean apply_timestamp_is_modified = false;
  private boolean apply_root_div_is_set = false;
  private boolean apply_root_div_is_modified = false;
  private boolean apply_accept_div_is_set = false;
  private boolean apply_accept_div_is_modified = false;
  private boolean apply_accept_user_is_set = false;
  private boolean apply_accept_user_is_modified = false;
  private boolean apply_accept_timestamp_is_set = false;
  private boolean apply_accept_timestamp_is_modified = false;
  private boolean apply_cancel_div_is_set = false;
  private boolean apply_cancel_div_is_modified = false;
  private boolean apply_cancel_user_is_set = false;
  private boolean apply_cancel_user_is_modified = false;
  private boolean apply_cancel_timestamp_is_set = false;
  private boolean apply_cancel_timestamp_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "apply_id=" + apply_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "premium_no=" +premium_no
      + "," + "used_point=" +used_point
      + "," + "apply_timestamp=" +apply_timestamp
      + "," + "apply_root_div=" +apply_root_div
      + "," + "apply_accept_div=" +apply_accept_div
      + "," + "apply_accept_user=" +apply_accept_user
      + "," + "apply_accept_timestamp=" +apply_accept_timestamp
      + "," + "apply_cancel_div=" +apply_cancel_div
      + "," + "apply_cancel_user=" +apply_cancel_user
      + "," + "apply_cancel_timestamp=" +apply_cancel_timestamp
      + "," + "last_updater=" +last_updater
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��PointApplyParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public PointApplyParams() {
    apply_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���PointApplyRow�I�u�W�F�N�g�̒l�𗘗p����PointApplyParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����PointApplyRow�I�u�W�F�N�g 
   */
  public PointApplyParams( PointApplyRow p_row )
  {
    apply_id = p_row.getApplyId();
    apply_id_is_set = p_row.getApplyIdIsSet();
    apply_id_is_modified = p_row.getApplyIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    premium_no = p_row.getPremiumNo();
    premium_no_is_set = p_row.getPremiumNoIsSet();
    premium_no_is_modified = p_row.getPremiumNoIsModified();
    if ( !p_row.getUsedPointIsNull() )
      used_point = new Integer( p_row.getUsedPoint() );
    used_point_is_set = p_row.getUsedPointIsSet();
    used_point_is_modified = p_row.getUsedPointIsModified();
    apply_timestamp = p_row.getApplyTimestamp();
    apply_timestamp_is_set = p_row.getApplyTimestampIsSet();
    apply_timestamp_is_modified = p_row.getApplyTimestampIsModified();
    apply_root_div = p_row.getApplyRootDiv();
    apply_root_div_is_set = p_row.getApplyRootDivIsSet();
    apply_root_div_is_modified = p_row.getApplyRootDivIsModified();
    apply_accept_div = p_row.getApplyAcceptDiv();
    apply_accept_div_is_set = p_row.getApplyAcceptDivIsSet();
    apply_accept_div_is_modified = p_row.getApplyAcceptDivIsModified();
    apply_accept_user = p_row.getApplyAcceptUser();
    apply_accept_user_is_set = p_row.getApplyAcceptUserIsSet();
    apply_accept_user_is_modified = p_row.getApplyAcceptUserIsModified();
    apply_accept_timestamp = p_row.getApplyAcceptTimestamp();
    apply_accept_timestamp_is_set = p_row.getApplyAcceptTimestampIsSet();
    apply_accept_timestamp_is_modified = p_row.getApplyAcceptTimestampIsModified();
    apply_cancel_div = p_row.getApplyCancelDiv();
    apply_cancel_div_is_set = p_row.getApplyCancelDivIsSet();
    apply_cancel_div_is_modified = p_row.getApplyCancelDivIsModified();
    apply_cancel_user = p_row.getApplyCancelUser();
    apply_cancel_user_is_set = p_row.getApplyCancelUserIsSet();
    apply_cancel_user_is_modified = p_row.getApplyCancelUserIsModified();
    apply_cancel_timestamp = p_row.getApplyCancelTimestamp();
    apply_cancel_timestamp_is_set = p_row.getApplyCancelTimestampIsSet();
    apply_cancel_timestamp_is_modified = p_row.getApplyCancelTimestampIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
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
      trader_code_is_set = true;
      trader_code_is_modified = true;
      premium_no_is_set = true;
      premium_no_is_modified = true;
      used_point_is_set = true;
      used_point_is_modified = true;
      apply_timestamp_is_set = true;
      apply_timestamp_is_modified = true;
      apply_root_div_is_set = true;
      apply_root_div_is_modified = true;
      apply_accept_div_is_set = true;
      apply_accept_div_is_modified = true;
      apply_accept_user_is_set = true;
      apply_accept_user_is_modified = true;
      apply_accept_timestamp_is_set = true;
      apply_accept_timestamp_is_modified = true;
      apply_cancel_div_is_set = true;
      apply_cancel_div_is_modified = true;
      apply_cancel_user_is_set = true;
      apply_cancel_user_is_modified = true;
      apply_cancel_timestamp_is_set = true;
      apply_cancel_timestamp_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof PointApplyRow ) )
       return false;
    return fieldsEqual( (PointApplyRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�PointApplyRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( PointApplyRow row )
  {
    if ( apply_id != row.getApplyId() )
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
    if ( trader_code == null ) {
      if ( row.getTraderCode() != null )
        return false;
    } else if ( !trader_code.equals( row.getTraderCode() ) ) {
        return false;
    }
    if ( premium_no == null ) {
      if ( row.getPremiumNo() != null )
        return false;
    } else if ( !premium_no.equals( row.getPremiumNo() ) ) {
        return false;
    }
    if ( used_point == null ) {
      if ( !row.getUsedPointIsNull() )
        return false;
    } else if ( row.getUsedPointIsNull() || ( used_point.intValue() != row.getUsedPoint() ) ) {
        return false;
    }
    if ( apply_timestamp == null ) {
      if ( row.getApplyTimestamp() != null )
        return false;
    } else if ( !apply_timestamp.equals( row.getApplyTimestamp() ) ) {
        return false;
    }
    if ( apply_root_div == null ) {
      if ( row.getApplyRootDiv() != null )
        return false;
    } else if ( !apply_root_div.equals( row.getApplyRootDiv() ) ) {
        return false;
    }
    if ( apply_accept_div == null ) {
      if ( row.getApplyAcceptDiv() != null )
        return false;
    } else if ( !apply_accept_div.equals( row.getApplyAcceptDiv() ) ) {
        return false;
    }
    if ( apply_accept_user == null ) {
      if ( row.getApplyAcceptUser() != null )
        return false;
    } else if ( !apply_accept_user.equals( row.getApplyAcceptUser() ) ) {
        return false;
    }
    if ( apply_accept_timestamp == null ) {
      if ( row.getApplyAcceptTimestamp() != null )
        return false;
    } else if ( !apply_accept_timestamp.equals( row.getApplyAcceptTimestamp() ) ) {
        return false;
    }
    if ( apply_cancel_div == null ) {
      if ( row.getApplyCancelDiv() != null )
        return false;
    } else if ( !apply_cancel_div.equals( row.getApplyCancelDiv() ) ) {
        return false;
    }
    if ( apply_cancel_user == null ) {
      if ( row.getApplyCancelUser() != null )
        return false;
    } else if ( !apply_cancel_user.equals( row.getApplyCancelUser() ) ) {
        return false;
    }
    if ( apply_cancel_timestamp == null ) {
      if ( row.getApplyCancelTimestamp() != null )
        return false;
    } else if ( !apply_cancel_timestamp.equals( row.getApplyCancelTimestamp() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
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
      return  ((int) apply_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (premium_no!=null? premium_no.hashCode(): 0) 
        + (used_point!=null? used_point.hashCode(): 0) 
        + (apply_timestamp!=null? apply_timestamp.hashCode(): 0) 
        + (apply_root_div!=null? apply_root_div.hashCode(): 0) 
        + (apply_accept_div!=null? apply_accept_div.hashCode(): 0) 
        + (apply_accept_user!=null? apply_accept_user.hashCode(): 0) 
        + (apply_accept_timestamp!=null? apply_accept_timestamp.hashCode(): 0) 
        + (apply_cancel_div!=null? apply_cancel_div.hashCode(): 0) 
        + (apply_cancel_user!=null? apply_cancel_user.hashCode(): 0) 
        + (apply_cancel_timestamp!=null? apply_cancel_timestamp.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
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
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !apply_root_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'apply_root_div' must be set before inserting.");
		if ( !apply_accept_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'apply_accept_div' must be set before inserting.");
		if ( !apply_cancel_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'apply_cancel_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("apply_id",new Long(apply_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		if ( premium_no != null )
			map.put("premium_no",premium_no);
		if ( used_point != null )
			map.put("used_point",used_point);
		if ( apply_timestamp != null )
			map.put("apply_timestamp",apply_timestamp);
		map.put("apply_root_div",apply_root_div);
		map.put("apply_accept_div",apply_accept_div);
		if ( apply_accept_user != null )
			map.put("apply_accept_user",apply_accept_user);
		if ( apply_accept_timestamp != null )
			map.put("apply_accept_timestamp",apply_accept_timestamp);
		map.put("apply_cancel_div",apply_cancel_div);
		if ( apply_cancel_user != null )
			map.put("apply_cancel_user",apply_cancel_user);
		if ( apply_cancel_timestamp != null )
			map.put("apply_cancel_timestamp",apply_cancel_timestamp);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( last_updated_timestamp != null )
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
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( premium_no_is_modified )
			map.put("premium_no",premium_no);
		if ( used_point_is_modified )
			map.put("used_point",used_point);
		if ( apply_timestamp_is_modified )
			map.put("apply_timestamp",apply_timestamp);
		if ( apply_root_div_is_modified )
			map.put("apply_root_div",apply_root_div);
		if ( apply_accept_div_is_modified )
			map.put("apply_accept_div",apply_accept_div);
		if ( apply_accept_user_is_modified )
			map.put("apply_accept_user",apply_accept_user);
		if ( apply_accept_timestamp_is_modified )
			map.put("apply_accept_timestamp",apply_accept_timestamp);
		if ( apply_cancel_div_is_modified )
			map.put("apply_cancel_div",apply_cancel_div);
		if ( apply_cancel_user_is_modified )
			map.put("apply_cancel_user",apply_cancel_user);
		if ( apply_cancel_timestamp_is_modified )
			map.put("apply_cancel_timestamp",apply_cancel_timestamp);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			map.put("premium_no",premium_no);
			map.put("used_point",used_point);
			map.put("apply_timestamp",apply_timestamp);
			if ( apply_root_div_is_set )
				map.put("apply_root_div",apply_root_div);
			if ( apply_accept_div_is_set )
				map.put("apply_accept_div",apply_accept_div);
			map.put("apply_accept_user",apply_accept_user);
			map.put("apply_accept_timestamp",apply_accept_timestamp);
			if ( apply_cancel_div_is_set )
				map.put("apply_cancel_div",apply_cancel_div);
			map.put("apply_cancel_user",apply_cancel_user);
			map.put("apply_cancel_timestamp",apply_cancel_timestamp);
			map.put("last_updater",last_updater);
			map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>apply_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getApplyId()
  {
    return apply_id;
  }


  /** 
   * <em>apply_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyIdIsSet() {
    return apply_id_is_set;
  }


  /** 
   * <em>apply_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyIdIsModified() {
    return apply_id_is_modified;
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
   * <em>trader_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTraderCode()
  {
    return trader_code;
  }


  /** 
   * <em>trader_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTraderCodeIsSet() {
    return trader_code_is_set;
  }


  /** 
   * <em>trader_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTraderCodeIsModified() {
    return trader_code_is_modified;
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
   * <em>used_point</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getUsedPoint()
  {
    return ( used_point==null? ((int)0): used_point.intValue() );
  }


  /** 
   * <em>used_point</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getUsedPointIsNull()
  {
    return used_point == null;
  }


  /** 
   * <em>used_point</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUsedPointIsSet() {
    return used_point_is_set;
  }


  /** 
   * <em>used_point</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUsedPointIsModified() {
    return used_point_is_modified;
  }


  /** 
   * <em>apply_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getApplyTimestamp()
  {
    return apply_timestamp;
  }


  /** 
   * <em>apply_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyTimestampIsSet() {
    return apply_timestamp_is_set;
  }


  /** 
   * <em>apply_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyTimestampIsModified() {
    return apply_timestamp_is_modified;
  }


  /** 
   * <em>apply_root_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getApplyRootDiv()
  {
    return apply_root_div;
  }


  /** 
   * <em>apply_root_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyRootDivIsSet() {
    return apply_root_div_is_set;
  }


  /** 
   * <em>apply_root_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyRootDivIsModified() {
    return apply_root_div_is_modified;
  }


  /** 
   * <em>apply_accept_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getApplyAcceptDiv()
  {
    return apply_accept_div;
  }


  /** 
   * <em>apply_accept_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyAcceptDivIsSet() {
    return apply_accept_div_is_set;
  }


  /** 
   * <em>apply_accept_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyAcceptDivIsModified() {
    return apply_accept_div_is_modified;
  }


  /** 
   * <em>apply_accept_user</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getApplyAcceptUser()
  {
    return apply_accept_user;
  }


  /** 
   * <em>apply_accept_user</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyAcceptUserIsSet() {
    return apply_accept_user_is_set;
  }


  /** 
   * <em>apply_accept_user</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyAcceptUserIsModified() {
    return apply_accept_user_is_modified;
  }


  /** 
   * <em>apply_accept_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getApplyAcceptTimestamp()
  {
    return apply_accept_timestamp;
  }


  /** 
   * <em>apply_accept_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyAcceptTimestampIsSet() {
    return apply_accept_timestamp_is_set;
  }


  /** 
   * <em>apply_accept_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyAcceptTimestampIsModified() {
    return apply_accept_timestamp_is_modified;
  }


  /** 
   * <em>apply_cancel_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getApplyCancelDiv()
  {
    return apply_cancel_div;
  }


  /** 
   * <em>apply_cancel_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyCancelDivIsSet() {
    return apply_cancel_div_is_set;
  }


  /** 
   * <em>apply_cancel_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyCancelDivIsModified() {
    return apply_cancel_div_is_modified;
  }


  /** 
   * <em>apply_cancel_user</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getApplyCancelUser()
  {
    return apply_cancel_user;
  }


  /** 
   * <em>apply_cancel_user</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyCancelUserIsSet() {
    return apply_cancel_user_is_set;
  }


  /** 
   * <em>apply_cancel_user</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyCancelUserIsModified() {
    return apply_cancel_user_is_modified;
  }


  /** 
   * <em>apply_cancel_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getApplyCancelTimestamp()
  {
    return apply_cancel_timestamp;
  }


  /** 
   * <em>apply_cancel_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyCancelTimestampIsSet() {
    return apply_cancel_timestamp_is_set;
  }


  /** 
   * <em>apply_cancel_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyCancelTimestampIsModified() {
    return apply_cancel_timestamp_is_modified;
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
    return new PointApplyPK(apply_id);
  }


  /** 
   * <em>apply_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_applyId <em>apply_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setApplyId( long p_applyId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_id = p_applyId;
    apply_id_is_set = true;
    apply_id_is_modified = true;
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
   * <em>trader_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_traderCode <em>trader_code</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setTraderCode( String p_traderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_code = p_traderCode;
    trader_code_is_set = true;
    trader_code_is_modified = true;
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
   * <em>used_point</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_usedPoint <em>used_point</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public final void setUsedPoint( int p_usedPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    used_point = new Integer(p_usedPoint);
    used_point_is_set = true;
    used_point_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>used_point</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setUsedPoint( Integer p_usedPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    used_point = p_usedPoint;
    used_point_is_set = true;
    used_point_is_modified = true;
  }


  /** 
   * <em>apply_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_applyTimestamp <em>apply_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setApplyTimestamp( java.sql.Timestamp p_applyTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_timestamp = p_applyTimestamp;
    apply_timestamp_is_set = true;
    apply_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>apply_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setApplyTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    apply_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    apply_timestamp_is_set = true;
    apply_timestamp_is_modified = true;
  }


  /** 
   * <em>apply_root_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_applyRootDiv <em>apply_root_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setApplyRootDiv( String p_applyRootDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_root_div = p_applyRootDiv;
    apply_root_div_is_set = true;
    apply_root_div_is_modified = true;
  }


  /** 
   * <em>apply_accept_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_applyAcceptDiv <em>apply_accept_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setApplyAcceptDiv( String p_applyAcceptDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_accept_div = p_applyAcceptDiv;
    apply_accept_div_is_set = true;
    apply_accept_div_is_modified = true;
  }


  /** 
   * <em>apply_accept_user</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_applyAcceptUser <em>apply_accept_user</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setApplyAcceptUser( String p_applyAcceptUser )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_accept_user = p_applyAcceptUser;
    apply_accept_user_is_set = true;
    apply_accept_user_is_modified = true;
  }


  /** 
   * <em>apply_accept_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_applyAcceptTimestamp <em>apply_accept_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setApplyAcceptTimestamp( java.sql.Timestamp p_applyAcceptTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_accept_timestamp = p_applyAcceptTimestamp;
    apply_accept_timestamp_is_set = true;
    apply_accept_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>apply_accept_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setApplyAcceptTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    apply_accept_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    apply_accept_timestamp_is_set = true;
    apply_accept_timestamp_is_modified = true;
  }


  /** 
   * <em>apply_cancel_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_applyCancelDiv <em>apply_cancel_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setApplyCancelDiv( String p_applyCancelDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_cancel_div = p_applyCancelDiv;
    apply_cancel_div_is_set = true;
    apply_cancel_div_is_modified = true;
  }


  /** 
   * <em>apply_cancel_user</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_applyCancelUser <em>apply_cancel_user</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setApplyCancelUser( String p_applyCancelUser )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_cancel_user = p_applyCancelUser;
    apply_cancel_user_is_set = true;
    apply_cancel_user_is_modified = true;
  }


  /** 
   * <em>apply_cancel_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_applyCancelTimestamp <em>apply_cancel_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setApplyCancelTimestamp( java.sql.Timestamp p_applyCancelTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_cancel_timestamp = p_applyCancelTimestamp;
    apply_cancel_timestamp_is_set = true;
    apply_cancel_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>apply_cancel_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setApplyCancelTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    apply_cancel_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    apply_cancel_timestamp_is_set = true;
    apply_cancel_timestamp_is_modified = true;
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
                if ( name.equals("apply_id") ) {
                    return new Long( apply_id );
                }
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("apply_timestamp") ) {
                    return this.apply_timestamp;
                }
                else if ( name.equals("apply_root_div") ) {
                    return this.apply_root_div;
                }
                else if ( name.equals("apply_accept_div") ) {
                    return this.apply_accept_div;
                }
                else if ( name.equals("apply_accept_user") ) {
                    return this.apply_accept_user;
                }
                else if ( name.equals("apply_accept_timestamp") ) {
                    return this.apply_accept_timestamp;
                }
                else if ( name.equals("apply_cancel_div") ) {
                    return this.apply_cancel_div;
                }
                else if ( name.equals("apply_cancel_user") ) {
                    return this.apply_cancel_user;
                }
                else if ( name.equals("apply_cancel_timestamp") ) {
                    return this.apply_cancel_timestamp;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
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
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                break;
            case 'u':
                if ( name.equals("used_point") ) {
                    return this.used_point;
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
                if ( name.equals("apply_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'apply_id' must be Long: '"+value+"' is not." );
                    this.apply_id = ((Long) value).longValue();
                    if (this.apply_id_is_set)
                        this.apply_id_is_modified = true;
                    this.apply_id_is_set = true;
                    return;
                }
                else if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("apply_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'apply_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.apply_timestamp = (java.sql.Timestamp) value;
                    if (this.apply_timestamp_is_set)
                        this.apply_timestamp_is_modified = true;
                    this.apply_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("apply_root_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'apply_root_div' must be String: '"+value+"' is not." );
                    this.apply_root_div = (String) value;
                    if (this.apply_root_div_is_set)
                        this.apply_root_div_is_modified = true;
                    this.apply_root_div_is_set = true;
                    return;
                }
                else if ( name.equals("apply_accept_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'apply_accept_div' must be String: '"+value+"' is not." );
                    this.apply_accept_div = (String) value;
                    if (this.apply_accept_div_is_set)
                        this.apply_accept_div_is_modified = true;
                    this.apply_accept_div_is_set = true;
                    return;
                }
                else if ( name.equals("apply_accept_user") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'apply_accept_user' must be String: '"+value+"' is not." );
                    this.apply_accept_user = (String) value;
                    if (this.apply_accept_user_is_set)
                        this.apply_accept_user_is_modified = true;
                    this.apply_accept_user_is_set = true;
                    return;
                }
                else if ( name.equals("apply_accept_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'apply_accept_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.apply_accept_timestamp = (java.sql.Timestamp) value;
                    if (this.apply_accept_timestamp_is_set)
                        this.apply_accept_timestamp_is_modified = true;
                    this.apply_accept_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("apply_cancel_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'apply_cancel_div' must be String: '"+value+"' is not." );
                    this.apply_cancel_div = (String) value;
                    if (this.apply_cancel_div_is_set)
                        this.apply_cancel_div_is_modified = true;
                    this.apply_cancel_div_is_set = true;
                    return;
                }
                else if ( name.equals("apply_cancel_user") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'apply_cancel_user' must be String: '"+value+"' is not." );
                    this.apply_cancel_user = (String) value;
                    if (this.apply_cancel_user_is_set)
                        this.apply_cancel_user_is_modified = true;
                    this.apply_cancel_user_is_set = true;
                    return;
                }
                else if ( name.equals("apply_cancel_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'apply_cancel_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.apply_cancel_timestamp = (java.sql.Timestamp) value;
                    if (this.apply_cancel_timestamp_is_set)
                        this.apply_cancel_timestamp_is_modified = true;
                    this.apply_cancel_timestamp_is_set = true;
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
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'premium_no' must be String: '"+value+"' is not." );
                    this.premium_no = (String) value;
                    if (this.premium_no_is_set)
                        this.premium_no_is_modified = true;
                    this.premium_no_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trader_code' must be String: '"+value+"' is not." );
                    this.trader_code = (String) value;
                    if (this.trader_code_is_set)
                        this.trader_code_is_modified = true;
                    this.trader_code_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("used_point") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'used_point' must be Integer: '"+value+"' is not." );
                    this.used_point = (Integer) value;
                    if (this.used_point_is_set)
                        this.used_point_is_modified = true;
                    this.used_point_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
