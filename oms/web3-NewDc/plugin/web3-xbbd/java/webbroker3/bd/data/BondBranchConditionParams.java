head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	BondBranchConditionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * bond_branch_condition�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link BondBranchConditionRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link BondBranchConditionParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link BondBranchConditionParams}��{@@link BondBranchConditionRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondBranchConditionPK 
 * @@see BondBranchConditionRow 
 */
public class BondBranchConditionParams extends Params implements BondBranchConditionRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "bond_branch_condition";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = BondBranchConditionRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return BondBranchConditionRow.TYPE;
  }


  /** 
   * <em>branch_id</em>�J�����̒l 
   */
  public  long  branch_id;

  /** 
   * <em>enforce_div</em>�J�����̒l 
   */
  public  String  enforce_div;

  /** 
   * <em>asset_check_div</em>�J�����̒l 
   */
  public  String  asset_check_div;

  /** 
   * <em>order_lock_use_div</em>�J�����̒l 
   */
  public  String  order_lock_use_div;

  /** 
   * <em>payment_date_set_div</em>�J�����̒l 
   */
  public  String  payment_date_set_div;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>branch_recruit_limit_div</em>�J�����̒l 
   */
  public  String  branch_recruit_limit_div;

  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean enforce_div_is_set = false;
  private boolean enforce_div_is_modified = false;
  private boolean asset_check_div_is_set = false;
  private boolean asset_check_div_is_modified = false;
  private boolean order_lock_use_div_is_set = false;
  private boolean order_lock_use_div_is_modified = false;
  private boolean payment_date_set_div_is_set = false;
  private boolean payment_date_set_div_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean branch_recruit_limit_div_is_set = false;
  private boolean branch_recruit_limit_div_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "branch_id=" + branch_id
      + "," + "enforce_div=" +enforce_div
      + "," + "asset_check_div=" +asset_check_div
      + "," + "order_lock_use_div=" +order_lock_use_div
      + "," + "payment_date_set_div=" +payment_date_set_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "branch_recruit_limit_div=" +branch_recruit_limit_div
      + "}";
  }


  /** 
   * �l�����ݒ��BondBranchConditionParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public BondBranchConditionParams() {
    branch_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���BondBranchConditionRow�I�u�W�F�N�g�̒l�𗘗p����BondBranchConditionParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����BondBranchConditionRow�I�u�W�F�N�g 
   */
  public BondBranchConditionParams( BondBranchConditionRow p_row )
  {
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    enforce_div = p_row.getEnforceDiv();
    enforce_div_is_set = p_row.getEnforceDivIsSet();
    enforce_div_is_modified = p_row.getEnforceDivIsModified();
    asset_check_div = p_row.getAssetCheckDiv();
    asset_check_div_is_set = p_row.getAssetCheckDivIsSet();
    asset_check_div_is_modified = p_row.getAssetCheckDivIsModified();
    order_lock_use_div = p_row.getOrderLockUseDiv();
    order_lock_use_div_is_set = p_row.getOrderLockUseDivIsSet();
    order_lock_use_div_is_modified = p_row.getOrderLockUseDivIsModified();
    payment_date_set_div = p_row.getPaymentDateSetDiv();
    payment_date_set_div_is_set = p_row.getPaymentDateSetDivIsSet();
    payment_date_set_div_is_modified = p_row.getPaymentDateSetDivIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    branch_recruit_limit_div = p_row.getBranchRecruitLimitDiv();
    branch_recruit_limit_div_is_set = p_row.getBranchRecruitLimitDivIsSet();
    branch_recruit_limit_div_is_modified = p_row.getBranchRecruitLimitDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      enforce_div_is_set = true;
      enforce_div_is_modified = true;
      asset_check_div_is_set = true;
      asset_check_div_is_modified = true;
      order_lock_use_div_is_set = true;
      order_lock_use_div_is_modified = true;
      payment_date_set_div_is_set = true;
      payment_date_set_div_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      branch_recruit_limit_div_is_set = true;
      branch_recruit_limit_div_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof BondBranchConditionRow ) )
       return false;
    return fieldsEqual( (BondBranchConditionRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�BondBranchConditionRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( BondBranchConditionRow row )
  {
    if ( branch_id != row.getBranchId() )
      return false;
    if ( enforce_div == null ) {
      if ( row.getEnforceDiv() != null )
        return false;
    } else if ( !enforce_div.equals( row.getEnforceDiv() ) ) {
        return false;
    }
    if ( asset_check_div == null ) {
      if ( row.getAssetCheckDiv() != null )
        return false;
    } else if ( !asset_check_div.equals( row.getAssetCheckDiv() ) ) {
        return false;
    }
    if ( order_lock_use_div == null ) {
      if ( row.getOrderLockUseDiv() != null )
        return false;
    } else if ( !order_lock_use_div.equals( row.getOrderLockUseDiv() ) ) {
        return false;
    }
    if ( payment_date_set_div == null ) {
      if ( row.getPaymentDateSetDiv() != null )
        return false;
    } else if ( !payment_date_set_div.equals( row.getPaymentDateSetDiv() ) ) {
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
    if ( branch_recruit_limit_div == null ) {
      if ( row.getBranchRecruitLimitDiv() != null )
        return false;
    } else if ( !branch_recruit_limit_div.equals( row.getBranchRecruitLimitDiv() ) ) {
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
        + (enforce_div!=null? enforce_div.hashCode(): 0) 
        + (asset_check_div!=null? asset_check_div.hashCode(): 0) 
        + (order_lock_use_div!=null? order_lock_use_div.hashCode(): 0) 
        + (payment_date_set_div!=null? payment_date_set_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (branch_recruit_limit_div!=null? branch_recruit_limit_div.hashCode(): 0) 
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
		map.put("branch_id",new Long(branch_id));
		if ( enforce_div != null )
			map.put("enforce_div",enforce_div);
		if ( asset_check_div != null )
			map.put("asset_check_div",asset_check_div);
		if ( order_lock_use_div != null )
			map.put("order_lock_use_div",order_lock_use_div);
		if ( payment_date_set_div != null )
			map.put("payment_date_set_div",payment_date_set_div);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( branch_recruit_limit_div != null )
			map.put("branch_recruit_limit_div",branch_recruit_limit_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( enforce_div_is_modified )
			map.put("enforce_div",enforce_div);
		if ( asset_check_div_is_modified )
			map.put("asset_check_div",asset_check_div);
		if ( order_lock_use_div_is_modified )
			map.put("order_lock_use_div",order_lock_use_div);
		if ( payment_date_set_div_is_modified )
			map.put("payment_date_set_div",payment_date_set_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( branch_recruit_limit_div_is_modified )
			map.put("branch_recruit_limit_div",branch_recruit_limit_div);
		if (map.size() == 0) {
			map.put("enforce_div",enforce_div);
			map.put("asset_check_div",asset_check_div);
			map.put("order_lock_use_div",order_lock_use_div);
			map.put("payment_date_set_div",payment_date_set_div);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("branch_recruit_limit_div",branch_recruit_limit_div);
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
   * <em>enforce_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getEnforceDiv()
  {
    return enforce_div;
  }


  /** 
   * <em>enforce_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEnforceDivIsSet() {
    return enforce_div_is_set;
  }


  /** 
   * <em>enforce_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEnforceDivIsModified() {
    return enforce_div_is_modified;
  }


  /** 
   * <em>asset_check_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAssetCheckDiv()
  {
    return asset_check_div;
  }


  /** 
   * <em>asset_check_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAssetCheckDivIsSet() {
    return asset_check_div_is_set;
  }


  /** 
   * <em>asset_check_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAssetCheckDivIsModified() {
    return asset_check_div_is_modified;
  }


  /** 
   * <em>order_lock_use_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderLockUseDiv()
  {
    return order_lock_use_div;
  }


  /** 
   * <em>order_lock_use_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderLockUseDivIsSet() {
    return order_lock_use_div_is_set;
  }


  /** 
   * <em>order_lock_use_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderLockUseDivIsModified() {
    return order_lock_use_div_is_modified;
  }


  /** 
   * <em>payment_date_set_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPaymentDateSetDiv()
  {
    return payment_date_set_div;
  }


  /** 
   * <em>payment_date_set_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentDateSetDivIsSet() {
    return payment_date_set_div_is_set;
  }


  /** 
   * <em>payment_date_set_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentDateSetDivIsModified() {
    return payment_date_set_div_is_modified;
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
   * <em>branch_recruit_limit_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBranchRecruitLimitDiv()
  {
    return branch_recruit_limit_div;
  }


  /** 
   * <em>branch_recruit_limit_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchRecruitLimitDivIsSet() {
    return branch_recruit_limit_div_is_set;
  }


  /** 
   * <em>branch_recruit_limit_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchRecruitLimitDivIsModified() {
    return branch_recruit_limit_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new BondBranchConditionPK(branch_id);
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
   * <em>enforce_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_enforceDiv <em>enforce_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setEnforceDiv( String p_enforceDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    enforce_div = p_enforceDiv;
    enforce_div_is_set = true;
    enforce_div_is_modified = true;
  }


  /** 
   * <em>asset_check_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_assetCheckDiv <em>asset_check_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAssetCheckDiv( String p_assetCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    asset_check_div = p_assetCheckDiv;
    asset_check_div_is_set = true;
    asset_check_div_is_modified = true;
  }


  /** 
   * <em>order_lock_use_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderLockUseDiv <em>order_lock_use_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setOrderLockUseDiv( String p_orderLockUseDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_lock_use_div = p_orderLockUseDiv;
    order_lock_use_div_is_set = true;
    order_lock_use_div_is_modified = true;
  }


  /** 
   * <em>payment_date_set_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_paymentDateSetDiv <em>payment_date_set_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setPaymentDateSetDiv( String p_paymentDateSetDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_date_set_div = p_paymentDateSetDiv;
    payment_date_set_div_is_set = true;
    payment_date_set_div_is_modified = true;
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
   * <em>branch_recruit_limit_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_branchRecruitLimitDiv <em>branch_recruit_limit_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setBranchRecruitLimitDiv( String p_branchRecruitLimitDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_recruit_limit_div = p_branchRecruitLimitDiv;
    branch_recruit_limit_div_is_set = true;
    branch_recruit_limit_div_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("asset_check_div") ) {
                    return this.asset_check_div;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                else if ( name.equals("branch_recruit_limit_div") ) {
                    return this.branch_recruit_limit_div;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("enforce_div") ) {
                    return this.enforce_div;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_lock_use_div") ) {
                    return this.order_lock_use_div;
                }
                break;
            case 'p':
                if ( name.equals("payment_date_set_div") ) {
                    return this.payment_date_set_div;
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
                if ( name.equals("asset_check_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'asset_check_div' must be String: '"+value+"' is not." );
                    this.asset_check_div = (String) value;
                    if (this.asset_check_div_is_set)
                        this.asset_check_div_is_modified = true;
                    this.asset_check_div_is_set = true;
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
                else if ( name.equals("branch_recruit_limit_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_recruit_limit_div' must be String: '"+value+"' is not." );
                    this.branch_recruit_limit_div = (String) value;
                    if (this.branch_recruit_limit_div_is_set)
                        this.branch_recruit_limit_div_is_modified = true;
                    this.branch_recruit_limit_div_is_set = true;
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
            case 'e':
                if ( name.equals("enforce_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'enforce_div' must be String: '"+value+"' is not." );
                    this.enforce_div = (String) value;
                    if (this.enforce_div_is_set)
                        this.enforce_div_is_modified = true;
                    this.enforce_div_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
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
            case 'o':
                if ( name.equals("order_lock_use_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_lock_use_div' must be String: '"+value+"' is not." );
                    this.order_lock_use_div = (String) value;
                    if (this.order_lock_use_div_is_set)
                        this.order_lock_use_div_is_modified = true;
                    this.order_lock_use_div_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("payment_date_set_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_date_set_div' must be String: '"+value+"' is not." );
                    this.payment_date_set_div = (String) value;
                    if (this.payment_date_set_div_is_set)
                        this.payment_date_set_div_is_modified = true;
                    this.payment_date_set_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
