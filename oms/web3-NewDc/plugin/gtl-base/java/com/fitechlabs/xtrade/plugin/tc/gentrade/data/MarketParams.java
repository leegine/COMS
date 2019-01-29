head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.39.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MarketParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * market�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link MarketRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link MarketParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link MarketParams}��{@@link MarketRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MarketPK 
 * @@see MarketRow 
 */
public class MarketParams extends Params implements MarketRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "market";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = MarketRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return MarketRow.TYPE;
  }


  /** 
   * <em>market_id</em>�J�����̒l 
   */
  public  long  market_id;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>market_code</em>�J�����̒l 
   */
  public  String  market_code;

  /** 
   * <em>sonar_market_code</em>�J�����̒l 
   */
  public  String  sonar_market_code;

  /** 
   * <em>market_name</em>�J�����̒l 
   */
  public  String  market_name;

  /** 
   * <em>market_name_alt1</em>�J�����̒l 
   */
  public  String  market_name_alt1;

  /** 
   * <em>market_name_alt2</em>�J�����̒l 
   */
  public  String  market_name_alt2;

  /** 
   * <em>open_time</em>�J�����̒l 
   */
  public  String  open_time;

  /** 
   * <em>close_time</em>�J�����̒l 
   */
  public  String  close_time;

  /** 
   * <em>suspension</em>�J�����̒l 
   */
  public  String  suspension;

  /** 
   * <em>changeable_type</em>�J�����̒l 
   */
  public  String  changeable_type;

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

  /** 
   * <em>feq_order_emp_div</em>�J�����̒l 
   */
  public  String  feq_order_emp_div;

  /** 
   * <em>feq_order_request_div</em>�J�����̒l 
   */
  public  String  feq_order_request_div;

  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean sonar_market_code_is_set = false;
  private boolean sonar_market_code_is_modified = false;
  private boolean market_name_is_set = false;
  private boolean market_name_is_modified = false;
  private boolean market_name_alt1_is_set = false;
  private boolean market_name_alt1_is_modified = false;
  private boolean market_name_alt2_is_set = false;
  private boolean market_name_alt2_is_modified = false;
  private boolean open_time_is_set = false;
  private boolean open_time_is_modified = false;
  private boolean close_time_is_set = false;
  private boolean close_time_is_modified = false;
  private boolean suspension_is_set = false;
  private boolean suspension_is_modified = false;
  private boolean changeable_type_is_set = false;
  private boolean changeable_type_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean feq_order_emp_div_is_set = false;
  private boolean feq_order_emp_div_is_modified = false;
  private boolean feq_order_request_div_is_set = false;
  private boolean feq_order_request_div_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "market_id=" + market_id
      + "," + "institution_code=" +institution_code
      + "," + "market_code=" +market_code
      + "," + "sonar_market_code=" +sonar_market_code
      + "," + "market_name=" +market_name
      + "," + "market_name_alt1=" +market_name_alt1
      + "," + "market_name_alt2=" +market_name_alt2
      + "," + "open_time=" +open_time
      + "," + "close_time=" +close_time
      + "," + "suspension=" +suspension
      + "," + "changeable_type=" +changeable_type
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "feq_order_emp_div=" +feq_order_emp_div
      + "," + "feq_order_request_div=" +feq_order_request_div
      + "}";
  }


  /** 
   * �l�����ݒ��MarketParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public MarketParams() {
    market_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���MarketRow�I�u�W�F�N�g�̒l�𗘗p����MarketParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����MarketRow�I�u�W�F�N�g 
   */
  public MarketParams( MarketRow p_row )
  {
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    sonar_market_code = p_row.getSonarMarketCode();
    sonar_market_code_is_set = p_row.getSonarMarketCodeIsSet();
    sonar_market_code_is_modified = p_row.getSonarMarketCodeIsModified();
    market_name = p_row.getMarketName();
    market_name_is_set = p_row.getMarketNameIsSet();
    market_name_is_modified = p_row.getMarketNameIsModified();
    market_name_alt1 = p_row.getMarketNameAlt1();
    market_name_alt1_is_set = p_row.getMarketNameAlt1IsSet();
    market_name_alt1_is_modified = p_row.getMarketNameAlt1IsModified();
    market_name_alt2 = p_row.getMarketNameAlt2();
    market_name_alt2_is_set = p_row.getMarketNameAlt2IsSet();
    market_name_alt2_is_modified = p_row.getMarketNameAlt2IsModified();
    open_time = p_row.getOpenTime();
    open_time_is_set = p_row.getOpenTimeIsSet();
    open_time_is_modified = p_row.getOpenTimeIsModified();
    close_time = p_row.getCloseTime();
    close_time_is_set = p_row.getCloseTimeIsSet();
    close_time_is_modified = p_row.getCloseTimeIsModified();
    suspension = p_row.getSuspension();
    suspension_is_set = p_row.getSuspensionIsSet();
    suspension_is_modified = p_row.getSuspensionIsModified();
    changeable_type = p_row.getChangeableType();
    changeable_type_is_set = p_row.getChangeableTypeIsSet();
    changeable_type_is_modified = p_row.getChangeableTypeIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    feq_order_emp_div = p_row.getFeqOrderEmpDiv();
    feq_order_emp_div_is_set = p_row.getFeqOrderEmpDivIsSet();
    feq_order_emp_div_is_modified = p_row.getFeqOrderEmpDivIsModified();
    feq_order_request_div = p_row.getFeqOrderRequestDiv();
    feq_order_request_div_is_set = p_row.getFeqOrderRequestDivIsSet();
    feq_order_request_div_is_modified = p_row.getFeqOrderRequestDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
      market_code_is_set = true;
      market_code_is_modified = true;
      sonar_market_code_is_set = true;
      sonar_market_code_is_modified = true;
      market_name_is_set = true;
      market_name_is_modified = true;
      market_name_alt1_is_set = true;
      market_name_alt1_is_modified = true;
      market_name_alt2_is_set = true;
      market_name_alt2_is_modified = true;
      open_time_is_set = true;
      open_time_is_modified = true;
      close_time_is_set = true;
      close_time_is_modified = true;
      suspension_is_set = true;
      suspension_is_modified = true;
      changeable_type_is_set = true;
      changeable_type_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      feq_order_emp_div_is_set = true;
      feq_order_emp_div_is_modified = true;
      feq_order_request_div_is_set = true;
      feq_order_request_div_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof MarketRow ) )
       return false;
    return fieldsEqual( (MarketRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�MarketRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( MarketRow row )
  {
    if ( market_id != row.getMarketId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( sonar_market_code == null ) {
      if ( row.getSonarMarketCode() != null )
        return false;
    } else if ( !sonar_market_code.equals( row.getSonarMarketCode() ) ) {
        return false;
    }
    if ( market_name == null ) {
      if ( row.getMarketName() != null )
        return false;
    } else if ( !market_name.equals( row.getMarketName() ) ) {
        return false;
    }
    if ( market_name_alt1 == null ) {
      if ( row.getMarketNameAlt1() != null )
        return false;
    } else if ( !market_name_alt1.equals( row.getMarketNameAlt1() ) ) {
        return false;
    }
    if ( market_name_alt2 == null ) {
      if ( row.getMarketNameAlt2() != null )
        return false;
    } else if ( !market_name_alt2.equals( row.getMarketNameAlt2() ) ) {
        return false;
    }
    if ( open_time == null ) {
      if ( row.getOpenTime() != null )
        return false;
    } else if ( !open_time.equals( row.getOpenTime() ) ) {
        return false;
    }
    if ( close_time == null ) {
      if ( row.getCloseTime() != null )
        return false;
    } else if ( !close_time.equals( row.getCloseTime() ) ) {
        return false;
    }
    if ( suspension == null ) {
      if ( row.getSuspension() != null )
        return false;
    } else if ( !suspension.equals( row.getSuspension() ) ) {
        return false;
    }
    if ( changeable_type == null ) {
      if ( row.getChangeableType() != null )
        return false;
    } else if ( !changeable_type.equals( row.getChangeableType() ) ) {
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
    if ( feq_order_emp_div == null ) {
      if ( row.getFeqOrderEmpDiv() != null )
        return false;
    } else if ( !feq_order_emp_div.equals( row.getFeqOrderEmpDiv() ) ) {
        return false;
    }
    if ( feq_order_request_div == null ) {
      if ( row.getFeqOrderRequestDiv() != null )
        return false;
    } else if ( !feq_order_request_div.equals( row.getFeqOrderRequestDiv() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) market_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (sonar_market_code!=null? sonar_market_code.hashCode(): 0) 
        + (market_name!=null? market_name.hashCode(): 0) 
        + (market_name_alt1!=null? market_name_alt1.hashCode(): 0) 
        + (market_name_alt2!=null? market_name_alt2.hashCode(): 0) 
        + (open_time!=null? open_time.hashCode(): 0) 
        + (close_time!=null? close_time.hashCode(): 0) 
        + (suspension!=null? suspension.hashCode(): 0) 
        + (changeable_type!=null? changeable_type.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (feq_order_emp_div!=null? feq_order_emp_div.hashCode(): 0) 
        + (feq_order_request_div!=null? feq_order_request_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !market_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_code' must be set before inserting.");
		if ( !market_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_name' must be set before inserting.");
		if ( !open_time_is_set )
			throw new IllegalArgumentException("non-nullable field 'open_time' must be set before inserting.");
		if ( !close_time_is_set )
			throw new IllegalArgumentException("non-nullable field 'close_time' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("market_id",new Long(market_id));
		map.put("institution_code",institution_code);
		map.put("market_code",market_code);
		if ( sonar_market_code != null )
			map.put("sonar_market_code",sonar_market_code);
		map.put("market_name",market_name);
		if ( market_name_alt1 != null )
			map.put("market_name_alt1",market_name_alt1);
		if ( market_name_alt2 != null )
			map.put("market_name_alt2",market_name_alt2);
		map.put("open_time",open_time);
		map.put("close_time",close_time);
		if ( suspension != null )
			map.put("suspension",suspension);
		if ( changeable_type != null )
			map.put("changeable_type",changeable_type);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( feq_order_emp_div != null )
			map.put("feq_order_emp_div",feq_order_emp_div);
		if ( feq_order_request_div != null )
			map.put("feq_order_request_div",feq_order_request_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( market_code_is_modified )
			map.put("market_code",market_code);
		if ( sonar_market_code_is_modified )
			map.put("sonar_market_code",sonar_market_code);
		if ( market_name_is_modified )
			map.put("market_name",market_name);
		if ( market_name_alt1_is_modified )
			map.put("market_name_alt1",market_name_alt1);
		if ( market_name_alt2_is_modified )
			map.put("market_name_alt2",market_name_alt2);
		if ( open_time_is_modified )
			map.put("open_time",open_time);
		if ( close_time_is_modified )
			map.put("close_time",close_time);
		if ( suspension_is_modified )
			map.put("suspension",suspension);
		if ( changeable_type_is_modified )
			map.put("changeable_type",changeable_type);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( feq_order_emp_div_is_modified )
			map.put("feq_order_emp_div",feq_order_emp_div);
		if ( feq_order_request_div_is_modified )
			map.put("feq_order_request_div",feq_order_request_div);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( market_code_is_set )
				map.put("market_code",market_code);
			map.put("sonar_market_code",sonar_market_code);
			if ( market_name_is_set )
				map.put("market_name",market_name);
			map.put("market_name_alt1",market_name_alt1);
			map.put("market_name_alt2",market_name_alt2);
			if ( open_time_is_set )
				map.put("open_time",open_time);
			if ( close_time_is_set )
				map.put("close_time",close_time);
			map.put("suspension",suspension);
			map.put("changeable_type",changeable_type);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("feq_order_emp_div",feq_order_emp_div);
			map.put("feq_order_request_div",feq_order_request_div);
		}
		return map;
	}


  /** 
   * <em>market_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getMarketId()
  {
    return market_id;
  }


  /** 
   * <em>market_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketIdIsSet() {
    return market_id_is_set;
  }


  /** 
   * <em>market_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketIdIsModified() {
    return market_id_is_modified;
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
   * <em>market_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMarketCode()
  {
    return market_code;
  }


  /** 
   * <em>market_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketCodeIsSet() {
    return market_code_is_set;
  }


  /** 
   * <em>market_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketCodeIsModified() {
    return market_code_is_modified;
  }


  /** 
   * <em>sonar_market_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSonarMarketCode()
  {
    return sonar_market_code;
  }


  /** 
   * <em>sonar_market_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSonarMarketCodeIsSet() {
    return sonar_market_code_is_set;
  }


  /** 
   * <em>sonar_market_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSonarMarketCodeIsModified() {
    return sonar_market_code_is_modified;
  }


  /** 
   * <em>market_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMarketName()
  {
    return market_name;
  }


  /** 
   * <em>market_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketNameIsSet() {
    return market_name_is_set;
  }


  /** 
   * <em>market_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketNameIsModified() {
    return market_name_is_modified;
  }


  /** 
   * <em>market_name_alt1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMarketNameAlt1()
  {
    return market_name_alt1;
  }


  /** 
   * <em>market_name_alt1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketNameAlt1IsSet() {
    return market_name_alt1_is_set;
  }


  /** 
   * <em>market_name_alt1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketNameAlt1IsModified() {
    return market_name_alt1_is_modified;
  }


  /** 
   * <em>market_name_alt2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMarketNameAlt2()
  {
    return market_name_alt2;
  }


  /** 
   * <em>market_name_alt2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketNameAlt2IsSet() {
    return market_name_alt2_is_set;
  }


  /** 
   * <em>market_name_alt2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketNameAlt2IsModified() {
    return market_name_alt2_is_modified;
  }


  /** 
   * <em>open_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOpenTime()
  {
    return open_time;
  }


  /** 
   * <em>open_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenTimeIsSet() {
    return open_time_is_set;
  }


  /** 
   * <em>open_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenTimeIsModified() {
    return open_time_is_modified;
  }


  /** 
   * <em>close_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCloseTime()
  {
    return close_time;
  }


  /** 
   * <em>close_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCloseTimeIsSet() {
    return close_time_is_set;
  }


  /** 
   * <em>close_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCloseTimeIsModified() {
    return close_time_is_modified;
  }


  /** 
   * <em>suspension</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSuspension()
  {
    return suspension;
  }


  /** 
   * <em>suspension</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSuspensionIsSet() {
    return suspension_is_set;
  }


  /** 
   * <em>suspension</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSuspensionIsModified() {
    return suspension_is_modified;
  }


  /** 
   * <em>changeable_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getChangeableType()
  {
    return changeable_type;
  }


  /** 
   * <em>changeable_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getChangeableTypeIsSet() {
    return changeable_type_is_set;
  }


  /** 
   * <em>changeable_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getChangeableTypeIsModified() {
    return changeable_type_is_modified;
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
   * <em>feq_order_emp_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFeqOrderEmpDiv()
  {
    return feq_order_emp_div;
  }


  /** 
   * <em>feq_order_emp_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFeqOrderEmpDivIsSet() {
    return feq_order_emp_div_is_set;
  }


  /** 
   * <em>feq_order_emp_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFeqOrderEmpDivIsModified() {
    return feq_order_emp_div_is_modified;
  }


  /** 
   * <em>feq_order_request_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFeqOrderRequestDiv()
  {
    return feq_order_request_div;
  }


  /** 
   * <em>feq_order_request_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFeqOrderRequestDivIsSet() {
    return feq_order_request_div_is_set;
  }


  /** 
   * <em>feq_order_request_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFeqOrderRequestDivIsModified() {
    return feq_order_request_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new MarketPK(market_id);
  }


  /** 
   * <em>market_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marketId <em>market_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
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
   * <em>market_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marketCode <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setMarketCode( String p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
    market_code_is_modified = true;
  }


  /** 
   * <em>sonar_market_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sonarMarketCode <em>sonar_market_code</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSonarMarketCode( String p_sonarMarketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_market_code = p_sonarMarketCode;
    sonar_market_code_is_set = true;
    sonar_market_code_is_modified = true;
  }


  /** 
   * <em>market_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marketName <em>market_name</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setMarketName( String p_marketName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_name = p_marketName;
    market_name_is_set = true;
    market_name_is_modified = true;
  }


  /** 
   * <em>market_name_alt1</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marketNameAlt1 <em>market_name_alt1</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setMarketNameAlt1( String p_marketNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_name_alt1 = p_marketNameAlt1;
    market_name_alt1_is_set = true;
    market_name_alt1_is_modified = true;
  }


  /** 
   * <em>market_name_alt2</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marketNameAlt2 <em>market_name_alt2</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setMarketNameAlt2( String p_marketNameAlt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_name_alt2 = p_marketNameAlt2;
    market_name_alt2_is_set = true;
    market_name_alt2_is_modified = true;
  }


  /** 
   * <em>open_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_openTime <em>open_time</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setOpenTime( String p_openTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_time = p_openTime;
    open_time_is_set = true;
    open_time_is_modified = true;
  }


  /** 
   * <em>close_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_closeTime <em>close_time</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setCloseTime( String p_closeTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_time = p_closeTime;
    close_time_is_set = true;
    close_time_is_modified = true;
  }


  /** 
   * <em>suspension</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_suspension <em>suspension</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSuspension( String p_suspension )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    suspension = p_suspension;
    suspension_is_set = true;
    suspension_is_modified = true;
  }


  /** 
   * <em>changeable_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_changeableType <em>changeable_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setChangeableType( String p_changeableType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    changeable_type = p_changeableType;
    changeable_type_is_set = true;
    changeable_type_is_modified = true;
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
   * <em>feq_order_emp_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_feqOrderEmpDiv <em>feq_order_emp_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setFeqOrderEmpDiv( String p_feqOrderEmpDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    feq_order_emp_div = p_feqOrderEmpDiv;
    feq_order_emp_div_is_set = true;
    feq_order_emp_div_is_modified = true;
  }


  /** 
   * <em>feq_order_request_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_feqOrderRequestDiv <em>feq_order_request_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setFeqOrderRequestDiv( String p_feqOrderRequestDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    feq_order_request_div = p_feqOrderRequestDiv;
    feq_order_request_div_is_set = true;
    feq_order_request_div_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("close_time") ) {
                    return this.close_time;
                }
                else if ( name.equals("changeable_type") ) {
                    return this.changeable_type;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("feq_order_emp_div") ) {
                    return this.feq_order_emp_div;
                }
                else if ( name.equals("feq_order_request_div") ) {
                    return this.feq_order_request_div;
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
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                else if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                else if ( name.equals("market_name") ) {
                    return this.market_name;
                }
                else if ( name.equals("market_name_alt1") ) {
                    return this.market_name_alt1;
                }
                else if ( name.equals("market_name_alt2") ) {
                    return this.market_name_alt2;
                }
                break;
            case 'o':
                if ( name.equals("open_time") ) {
                    return this.open_time;
                }
                break;
            case 's':
                if ( name.equals("sonar_market_code") ) {
                    return this.sonar_market_code;
                }
                else if ( name.equals("suspension") ) {
                    return this.suspension;
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
                if ( name.equals("close_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'close_time' must be String: '"+value+"' is not." );
                    this.close_time = (String) value;
                    if (this.close_time_is_set)
                        this.close_time_is_modified = true;
                    this.close_time_is_set = true;
                    return;
                }
                else if ( name.equals("changeable_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'changeable_type' must be String: '"+value+"' is not." );
                    this.changeable_type = (String) value;
                    if (this.changeable_type_is_set)
                        this.changeable_type_is_modified = true;
                    this.changeable_type_is_set = true;
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
            case 'f':
                if ( name.equals("feq_order_emp_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'feq_order_emp_div' must be String: '"+value+"' is not." );
                    this.feq_order_emp_div = (String) value;
                    if (this.feq_order_emp_div_is_set)
                        this.feq_order_emp_div_is_modified = true;
                    this.feq_order_emp_div_is_set = true;
                    return;
                }
                else if ( name.equals("feq_order_request_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'feq_order_request_div' must be String: '"+value+"' is not." );
                    this.feq_order_request_div = (String) value;
                    if (this.feq_order_request_div_is_set)
                        this.feq_order_request_div_is_modified = true;
                    this.feq_order_request_div_is_set = true;
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
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = ((Long) value).longValue();
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                else if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                else if ( name.equals("market_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_name' must be String: '"+value+"' is not." );
                    this.market_name = (String) value;
                    if (this.market_name_is_set)
                        this.market_name_is_modified = true;
                    this.market_name_is_set = true;
                    return;
                }
                else if ( name.equals("market_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_name_alt1' must be String: '"+value+"' is not." );
                    this.market_name_alt1 = (String) value;
                    if (this.market_name_alt1_is_set)
                        this.market_name_alt1_is_modified = true;
                    this.market_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("market_name_alt2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_name_alt2' must be String: '"+value+"' is not." );
                    this.market_name_alt2 = (String) value;
                    if (this.market_name_alt2_is_set)
                        this.market_name_alt2_is_modified = true;
                    this.market_name_alt2_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("open_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'open_time' must be String: '"+value+"' is not." );
                    this.open_time = (String) value;
                    if (this.open_time_is_set)
                        this.open_time_is_modified = true;
                    this.open_time_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_market_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_market_code' must be String: '"+value+"' is not." );
                    this.sonar_market_code = (String) value;
                    if (this.sonar_market_code_is_set)
                        this.sonar_market_code_is_modified = true;
                    this.sonar_market_code_is_set = true;
                    return;
                }
                else if ( name.equals("suspension") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'suspension' must be String: '"+value+"' is not." );
                    this.suspension = (String) value;
                    if (this.suspension_is_set)
                        this.suspension_is_modified = true;
                    this.suspension_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
