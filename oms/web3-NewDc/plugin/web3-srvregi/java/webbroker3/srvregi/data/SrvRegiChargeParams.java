head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiChargeParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * srv_regi_charge�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link SrvRegiChargeRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link SrvRegiChargeParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link SrvRegiChargeParams}��{@@link SrvRegiChargeRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SrvRegiChargePK 
 * @@see SrvRegiChargeRow 
 */
public class SrvRegiChargeParams extends Params implements SrvRegiChargeRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "srv_regi_charge";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = SrvRegiChargeRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return SrvRegiChargeRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>srv_div</em>�J�����̒l 
   */
  public  String  srv_div;

  /** 
   * <em>consecutive_numbers</em>�J�����̒l 
   */
  public  long  consecutive_numbers;

  /** 
   * <em>srv_use_period_div</em>�J�����̒l 
   */
  public  String  srv_use_period_div;

  /** 
   * <em>srv_use_period</em>�J�����̒l 
   */
  public  int  srv_use_period;

  /** 
   * <em>use_amt</em>�J�����̒l 
   */
  public  long  use_amt;

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
  private boolean srv_div_is_set = false;
  private boolean srv_div_is_modified = false;
  private boolean consecutive_numbers_is_set = false;
  private boolean consecutive_numbers_is_modified = false;
  private boolean srv_use_period_div_is_set = false;
  private boolean srv_use_period_div_is_modified = false;
  private boolean srv_use_period_is_set = false;
  private boolean srv_use_period_is_modified = false;
  private boolean use_amt_is_set = false;
  private boolean use_amt_is_modified = false;
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
      + "," + "srv_div=" + srv_div
      + "," + "consecutive_numbers=" + consecutive_numbers
      + "," + "srv_use_period_div=" +srv_use_period_div
      + "," + "srv_use_period=" +srv_use_period
      + "," + "use_amt=" +use_amt
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��SrvRegiChargeParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public SrvRegiChargeParams() {
    institution_code_is_modified = true;
    srv_div_is_modified = true;
    consecutive_numbers_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���SrvRegiChargeRow�I�u�W�F�N�g�̒l�𗘗p����SrvRegiChargeParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����SrvRegiChargeRow�I�u�W�F�N�g 
   */
  public SrvRegiChargeParams( SrvRegiChargeRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    srv_div = p_row.getSrvDiv();
    srv_div_is_set = p_row.getSrvDivIsSet();
    srv_div_is_modified = p_row.getSrvDivIsModified();
    consecutive_numbers = p_row.getConsecutiveNumbers();
    consecutive_numbers_is_set = p_row.getConsecutiveNumbersIsSet();
    consecutive_numbers_is_modified = p_row.getConsecutiveNumbersIsModified();
    srv_use_period_div = p_row.getSrvUsePeriodDiv();
    srv_use_period_div_is_set = p_row.getSrvUsePeriodDivIsSet();
    srv_use_period_div_is_modified = p_row.getSrvUsePeriodDivIsModified();
    srv_use_period = p_row.getSrvUsePeriod();
    srv_use_period_is_set = p_row.getSrvUsePeriodIsSet();
    srv_use_period_is_modified = p_row.getSrvUsePeriodIsModified();
    use_amt = p_row.getUseAmt();
    use_amt_is_set = p_row.getUseAmtIsSet();
    use_amt_is_modified = p_row.getUseAmtIsModified();
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
      srv_use_period_div_is_set = true;
      srv_use_period_div_is_modified = true;
      srv_use_period_is_set = true;
      srv_use_period_is_modified = true;
      use_amt_is_set = true;
      use_amt_is_modified = true;
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
    if ( !( other instanceof SrvRegiChargeRow ) )
       return false;
    return fieldsEqual( (SrvRegiChargeRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�SrvRegiChargeRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( SrvRegiChargeRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( srv_div == null ) {
      if ( row.getSrvDiv() != null )
        return false;
    } else if ( !srv_div.equals( row.getSrvDiv() ) ) {
        return false;
    }
    if ( consecutive_numbers != row.getConsecutiveNumbers() )
      return false;
    if ( srv_use_period_div == null ) {
      if ( row.getSrvUsePeriodDiv() != null )
        return false;
    } else if ( !srv_use_period_div.equals( row.getSrvUsePeriodDiv() ) ) {
        return false;
    }
    if ( srv_use_period != row.getSrvUsePeriod() )
      return false;
    if ( use_amt != row.getUseAmt() )
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (srv_div!=null? srv_div.hashCode(): 0) 
        + ((int) consecutive_numbers)
        + (srv_use_period_div!=null? srv_use_period_div.hashCode(): 0) 
        + ((int) srv_use_period)
        + ((int) use_amt)
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
		if ( !srv_use_period_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'srv_use_period_div' must be set before inserting.");
		if ( !srv_use_period_is_set )
			throw new IllegalArgumentException("non-nullable field 'srv_use_period' must be set before inserting.");
		if ( !use_amt_is_set )
			throw new IllegalArgumentException("non-nullable field 'use_amt' must be set before inserting.");
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
		map.put("institution_code",institution_code);
		map.put("srv_div",srv_div);
		map.put("consecutive_numbers",new Long(consecutive_numbers));
		map.put("srv_use_period_div",srv_use_period_div);
		map.put("srv_use_period",new Integer(srv_use_period));
		map.put("use_amt",new Long(use_amt));
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
		if ( srv_use_period_div_is_modified )
			map.put("srv_use_period_div",srv_use_period_div);
		if ( srv_use_period_is_modified )
			map.put("srv_use_period",new Integer(srv_use_period));
		if ( use_amt_is_modified )
			map.put("use_amt",new Long(use_amt));
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( srv_use_period_div_is_set )
				map.put("srv_use_period_div",srv_use_period_div);
			if ( srv_use_period_is_set )
				map.put("srv_use_period",new Integer(srv_use_period));
			if ( use_amt_is_set )
				map.put("use_amt",new Long(use_amt));
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
   * <em>srv_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSrvDiv()
  {
    return srv_div;
  }


  /** 
   * <em>srv_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSrvDivIsSet() {
    return srv_div_is_set;
  }


  /** 
   * <em>srv_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSrvDivIsModified() {
    return srv_div_is_modified;
  }


  /** 
   * <em>consecutive_numbers</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getConsecutiveNumbers()
  {
    return consecutive_numbers;
  }


  /** 
   * <em>consecutive_numbers</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConsecutiveNumbersIsSet() {
    return consecutive_numbers_is_set;
  }


  /** 
   * <em>consecutive_numbers</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConsecutiveNumbersIsModified() {
    return consecutive_numbers_is_modified;
  }


  /** 
   * <em>srv_use_period_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSrvUsePeriodDiv()
  {
    return srv_use_period_div;
  }


  /** 
   * <em>srv_use_period_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSrvUsePeriodDivIsSet() {
    return srv_use_period_div_is_set;
  }


  /** 
   * <em>srv_use_period_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSrvUsePeriodDivIsModified() {
    return srv_use_period_div_is_modified;
  }


  /** 
   * <em>srv_use_period</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getSrvUsePeriod()
  {
    return srv_use_period;
  }


  /** 
   * <em>srv_use_period</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSrvUsePeriodIsSet() {
    return srv_use_period_is_set;
  }


  /** 
   * <em>srv_use_period</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSrvUsePeriodIsModified() {
    return srv_use_period_is_modified;
  }


  /** 
   * <em>use_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getUseAmt()
  {
    return use_amt;
  }


  /** 
   * <em>use_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUseAmtIsSet() {
    return use_amt_is_set;
  }


  /** 
   * <em>use_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUseAmtIsModified() {
    return use_amt_is_modified;
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
    return new SrvRegiChargePK(institution_code, srv_div, consecutive_numbers);
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
   * <em>srv_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_srvDiv <em>srv_div</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setSrvDiv( String p_srvDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_div = p_srvDiv;
    srv_div_is_set = true;
    srv_div_is_modified = true;
  }


  /** 
   * <em>consecutive_numbers</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_consecutiveNumbers <em>consecutive_numbers</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setConsecutiveNumbers( long p_consecutiveNumbers )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    consecutive_numbers = p_consecutiveNumbers;
    consecutive_numbers_is_set = true;
    consecutive_numbers_is_modified = true;
  }


  /** 
   * <em>srv_use_period_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_srvUsePeriodDiv <em>srv_use_period_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSrvUsePeriodDiv( String p_srvUsePeriodDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_use_period_div = p_srvUsePeriodDiv;
    srv_use_period_div_is_set = true;
    srv_use_period_div_is_modified = true;
  }


  /** 
   * <em>srv_use_period</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_srvUsePeriod <em>srv_use_period</em>�J�����̒l������킷3���ȉ���int�l 
   */
  public final void setSrvUsePeriod( int p_srvUsePeriod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_use_period = p_srvUsePeriod;
    srv_use_period_is_set = true;
    srv_use_period_is_modified = true;
  }


  /** 
   * <em>use_amt</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_useAmt <em>use_amt</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setUseAmt( long p_useAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    use_amt = p_useAmt;
    use_amt_is_set = true;
    use_amt_is_modified = true;
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
                if ( name.equals("consecutive_numbers") ) {
                    return new Long( consecutive_numbers );
                }
                else if ( name.equals("created_timestamp") ) {
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
            case 's':
                if ( name.equals("srv_div") ) {
                    return this.srv_div;
                }
                else if ( name.equals("srv_use_period_div") ) {
                    return this.srv_use_period_div;
                }
                else if ( name.equals("srv_use_period") ) {
                    return new Integer( srv_use_period );
                }
                break;
            case 'u':
                if ( name.equals("use_amt") ) {
                    return new Long( use_amt );
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
                if ( name.equals("consecutive_numbers") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'consecutive_numbers' must be Long: '"+value+"' is not." );
                    this.consecutive_numbers = ((Long) value).longValue();
                    if (this.consecutive_numbers_is_set)
                        this.consecutive_numbers_is_modified = true;
                    this.consecutive_numbers_is_set = true;
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
            case 's':
                if ( name.equals("srv_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'srv_div' must be String: '"+value+"' is not." );
                    this.srv_div = (String) value;
                    if (this.srv_div_is_set)
                        this.srv_div_is_modified = true;
                    this.srv_div_is_set = true;
                    return;
                }
                else if ( name.equals("srv_use_period_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'srv_use_period_div' must be String: '"+value+"' is not." );
                    this.srv_use_period_div = (String) value;
                    if (this.srv_use_period_div_is_set)
                        this.srv_use_period_div_is_modified = true;
                    this.srv_use_period_div_is_set = true;
                    return;
                }
                else if ( name.equals("srv_use_period") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'srv_use_period' must be Integer: '"+value+"' is not." );
                    this.srv_use_period = ((Integer) value).intValue();
                    if (this.srv_use_period_is_set)
                        this.srv_use_period_is_modified = true;
                    this.srv_use_period_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("use_amt") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'use_amt' must be Long: '"+value+"' is not." );
                    this.use_amt = ((Long) value).longValue();
                    if (this.use_amt_is_set)
                        this.use_amt_is_modified = true;
                    this.use_amt_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
