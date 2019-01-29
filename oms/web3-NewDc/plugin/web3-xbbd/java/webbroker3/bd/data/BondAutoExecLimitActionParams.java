head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	BondAutoExecLimitActionParams.java;


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
 * bond_auto_exec_limit_action�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link BondAutoExecLimitActionParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link BondAutoExecLimitActionParams}��{@@link BondAutoExecLimitActionRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondAutoExecLimitActionPK 
 * @@see BondAutoExecLimitActionRow 
 */
public class BondAutoExecLimitActionParams extends Params implements BondAutoExecLimitActionRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "bond_auto_exec_limit_action";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = BondAutoExecLimitActionRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return BondAutoExecLimitActionRow.TYPE;
  }


  /** 
   * <em>product_id</em>�J�����̒l 
   */
  public  long  product_id;

  /** 
   * <em>execution_update_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  execution_update_date;

  /** 
   * <em>auto_exec_amount</em>�J�����̒l 
   */
  public  Double  auto_exec_amount;

  /** 
   * <em>auto_exec_limit</em>�J�����̒l 
   */
  public  Double  auto_exec_limit;

  /** 
   * <em>last_updater</em>�J�����̒l 
   */
  public  String  last_updater;

  /** 
   * <em>online_disp_div</em>�J�����̒l 
   */
  public  String  online_disp_div;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean execution_update_date_is_set = false;
  private boolean execution_update_date_is_modified = false;
  private boolean auto_exec_amount_is_set = false;
  private boolean auto_exec_amount_is_modified = false;
  private boolean auto_exec_limit_is_set = false;
  private boolean auto_exec_limit_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean online_disp_div_is_set = false;
  private boolean online_disp_div_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "product_id=" + product_id
      + "," + "execution_update_date=" + execution_update_date
      + "," + "auto_exec_amount=" +auto_exec_amount
      + "," + "auto_exec_limit=" +auto_exec_limit
      + "," + "last_updater=" +last_updater
      + "," + "online_disp_div=" +online_disp_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��BondAutoExecLimitActionParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public BondAutoExecLimitActionParams() {
    product_id_is_modified = true;
    execution_update_date_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���BondAutoExecLimitActionRow�I�u�W�F�N�g�̒l�𗘗p����BondAutoExecLimitActionParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����BondAutoExecLimitActionRow�I�u�W�F�N�g 
   */
  public BondAutoExecLimitActionParams( BondAutoExecLimitActionRow p_row )
  {
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    execution_update_date = p_row.getExecutionUpdateDate();
    execution_update_date_is_set = p_row.getExecutionUpdateDateIsSet();
    execution_update_date_is_modified = p_row.getExecutionUpdateDateIsModified();
    if ( !p_row.getAutoExecAmountIsNull() )
      auto_exec_amount = new Double( p_row.getAutoExecAmount() );
    auto_exec_amount_is_set = p_row.getAutoExecAmountIsSet();
    auto_exec_amount_is_modified = p_row.getAutoExecAmountIsModified();
    if ( !p_row.getAutoExecLimitIsNull() )
      auto_exec_limit = new Double( p_row.getAutoExecLimit() );
    auto_exec_limit_is_set = p_row.getAutoExecLimitIsSet();
    auto_exec_limit_is_modified = p_row.getAutoExecLimitIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    online_disp_div = p_row.getOnlineDispDiv();
    online_disp_div_is_set = p_row.getOnlineDispDivIsSet();
    online_disp_div_is_modified = p_row.getOnlineDispDivIsModified();
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
      auto_exec_amount_is_set = true;
      auto_exec_amount_is_modified = true;
      auto_exec_limit_is_set = true;
      auto_exec_limit_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      online_disp_div_is_set = true;
      online_disp_div_is_modified = true;
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
    if ( !( other instanceof BondAutoExecLimitActionRow ) )
       return false;
    return fieldsEqual( (BondAutoExecLimitActionRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�BondAutoExecLimitActionRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( BondAutoExecLimitActionRow row )
  {
    if ( product_id != row.getProductId() )
      return false;
    if ( execution_update_date == null ) {
      if ( row.getExecutionUpdateDate() != null )
        return false;
    } else if ( !execution_update_date.equals( row.getExecutionUpdateDate() ) ) {
        return false;
    }
    if ( auto_exec_amount == null ) {
      if ( !row.getAutoExecAmountIsNull() )
        return false;
    } else if ( row.getAutoExecAmountIsNull() || ( auto_exec_amount.doubleValue() != row.getAutoExecAmount() ) ) {
        return false;
    }
    if ( auto_exec_limit == null ) {
      if ( !row.getAutoExecLimitIsNull() )
        return false;
    } else if ( row.getAutoExecLimitIsNull() || ( auto_exec_limit.doubleValue() != row.getAutoExecLimit() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( online_disp_div == null ) {
      if ( row.getOnlineDispDiv() != null )
        return false;
    } else if ( !online_disp_div.equals( row.getOnlineDispDiv() ) ) {
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
      return  ((int) product_id)
        + (execution_update_date!=null? execution_update_date.hashCode(): 0) 
        + (auto_exec_amount!=null? auto_exec_amount.hashCode(): 0) 
        + (auto_exec_limit!=null? auto_exec_limit.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (online_disp_div!=null? online_disp_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !online_disp_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'online_disp_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("product_id",new Long(product_id));
		map.put("execution_update_date",execution_update_date);
		if ( auto_exec_amount != null )
			map.put("auto_exec_amount",auto_exec_amount);
		if ( auto_exec_limit != null )
			map.put("auto_exec_limit",auto_exec_limit);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		map.put("online_disp_div",online_disp_div);
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
		if ( auto_exec_amount_is_modified )
			map.put("auto_exec_amount",auto_exec_amount);
		if ( auto_exec_limit_is_modified )
			map.put("auto_exec_limit",auto_exec_limit);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( online_disp_div_is_modified )
			map.put("online_disp_div",online_disp_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("auto_exec_amount",auto_exec_amount);
			map.put("auto_exec_limit",auto_exec_limit);
			map.put("last_updater",last_updater);
			if ( online_disp_div_is_set )
				map.put("online_disp_div",online_disp_div);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>product_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getProductId()
  {
    return product_id;
  }


  /** 
   * <em>product_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductIdIsSet() {
    return product_id_is_set;
  }


  /** 
   * <em>product_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductIdIsModified() {
    return product_id_is_modified;
  }


  /** 
   * <em>execution_update_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getExecutionUpdateDate()
  {
    return execution_update_date;
  }


  /** 
   * <em>execution_update_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecutionUpdateDateIsSet() {
    return execution_update_date_is_set;
  }


  /** 
   * <em>execution_update_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecutionUpdateDateIsModified() {
    return execution_update_date_is_modified;
  }


  /** 
   * <em>auto_exec_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAutoExecAmount()
  {
    return ( auto_exec_amount==null? ((double)0): auto_exec_amount.doubleValue() );
  }


  /** 
   * <em>auto_exec_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getAutoExecAmountIsNull()
  {
    return auto_exec_amount == null;
  }


  /** 
   * <em>auto_exec_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAutoExecAmountIsSet() {
    return auto_exec_amount_is_set;
  }


  /** 
   * <em>auto_exec_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAutoExecAmountIsModified() {
    return auto_exec_amount_is_modified;
  }


  /** 
   * <em>auto_exec_limit</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAutoExecLimit()
  {
    return ( auto_exec_limit==null? ((double)0): auto_exec_limit.doubleValue() );
  }


  /** 
   * <em>auto_exec_limit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getAutoExecLimitIsNull()
  {
    return auto_exec_limit == null;
  }


  /** 
   * <em>auto_exec_limit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAutoExecLimitIsSet() {
    return auto_exec_limit_is_set;
  }


  /** 
   * <em>auto_exec_limit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAutoExecLimitIsModified() {
    return auto_exec_limit_is_modified;
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
   * <em>online_disp_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOnlineDispDiv()
  {
    return online_disp_div;
  }


  /** 
   * <em>online_disp_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOnlineDispDivIsSet() {
    return online_disp_div_is_set;
  }


  /** 
   * <em>online_disp_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOnlineDispDivIsModified() {
    return online_disp_div_is_modified;
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
    return new BondAutoExecLimitActionPK(product_id, execution_update_date);
  }


  /** 
   * <em>product_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productId <em>product_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setProductId( long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = p_productId;
    product_id_is_set = true;
    product_id_is_modified = true;
  }


  /** 
   * <em>execution_update_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_executionUpdateDate <em>execution_update_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setExecutionUpdateDate( java.sql.Timestamp p_executionUpdateDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    execution_update_date = p_executionUpdateDate;
    execution_update_date_is_set = true;
    execution_update_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>execution_update_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setExecutionUpdateDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    execution_update_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    execution_update_date_is_set = true;
    execution_update_date_is_modified = true;
  }


  /** 
   * <em>auto_exec_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_autoExecAmount <em>auto_exec_amount</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setAutoExecAmount( double p_autoExecAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    auto_exec_amount = new Double(p_autoExecAmount);
    auto_exec_amount_is_set = true;
    auto_exec_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>auto_exec_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setAutoExecAmount( Double p_autoExecAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    auto_exec_amount = p_autoExecAmount;
    auto_exec_amount_is_set = true;
    auto_exec_amount_is_modified = true;
  }


  /** 
   * <em>auto_exec_limit</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_autoExecLimit <em>auto_exec_limit</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setAutoExecLimit( double p_autoExecLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    auto_exec_limit = new Double(p_autoExecLimit);
    auto_exec_limit_is_set = true;
    auto_exec_limit_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>auto_exec_limit</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setAutoExecLimit( Double p_autoExecLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    auto_exec_limit = p_autoExecLimit;
    auto_exec_limit_is_set = true;
    auto_exec_limit_is_modified = true;
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
   * <em>online_disp_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_onlineDispDiv <em>online_disp_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setOnlineDispDiv( String p_onlineDispDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    online_disp_div = p_onlineDispDiv;
    online_disp_div_is_set = true;
    online_disp_div_is_modified = true;
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
                if ( name.equals("auto_exec_amount") ) {
                    return this.auto_exec_amount;
                }
                else if ( name.equals("auto_exec_limit") ) {
                    return this.auto_exec_limit;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("execution_update_date") ) {
                    return this.execution_update_date;
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
            case 'o':
                if ( name.equals("online_disp_div") ) {
                    return this.online_disp_div;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
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
                if ( name.equals("auto_exec_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'auto_exec_amount' must be Double: '"+value+"' is not." );
                    this.auto_exec_amount = (Double) value;
                    if (this.auto_exec_amount_is_set)
                        this.auto_exec_amount_is_modified = true;
                    this.auto_exec_amount_is_set = true;
                    return;
                }
                else if ( name.equals("auto_exec_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'auto_exec_limit' must be Double: '"+value+"' is not." );
                    this.auto_exec_limit = (Double) value;
                    if (this.auto_exec_limit_is_set)
                        this.auto_exec_limit_is_modified = true;
                    this.auto_exec_limit_is_set = true;
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
                if ( name.equals("execution_update_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'execution_update_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.execution_update_date = (java.sql.Timestamp) value;
                    if (this.execution_update_date_is_set)
                        this.execution_update_date_is_modified = true;
                    this.execution_update_date_is_set = true;
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
                if ( name.equals("online_disp_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'online_disp_div' must be String: '"+value+"' is not." );
                    this.online_disp_div = (String) value;
                    if (this.online_disp_div_is_set)
                        this.online_disp_div_is_modified = true;
                    this.online_disp_div_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
