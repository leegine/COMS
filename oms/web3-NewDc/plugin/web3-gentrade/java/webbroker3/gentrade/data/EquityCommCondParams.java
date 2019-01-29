head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.16.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityCommCondParams.java;


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
 * equity_comm_cond�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link EquityCommCondRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link EquityCommCondParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link EquityCommCondParams}��{@@link EquityCommCondRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EquityCommCondPK 
 * @@see EquityCommCondRow 
 */
public class EquityCommCondParams extends Params implements EquityCommCondRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "equity_comm_cond";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = EquityCommCondRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return EquityCommCondRow.TYPE;
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
   * <em>reg_no</em>�J�����̒l 
   */
  public  String  reg_no;

  /** 
   * <em>appli_start_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_start_date;

  /** 
   * <em>sequence_no</em>�J�����̒l 
   */
  public  String  sequence_no;

  /** 
   * <em>appli_end_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_end_date;

  /** 
   * <em>min_turnover</em>�J�����̒l 
   */
  public  double  min_turnover;

  /** 
   * <em>max_turnover</em>�J�����̒l 
   */
  public  double  max_turnover;

  /** 
   * <em>charge_ratio</em>�J�����̒l 
   */
  public  double  charge_ratio;

  /** 
   * <em>added_price</em>�J�����̒l 
   */
  public  double  added_price;

  /** 
   * <em>reg_type</em>�J�����̒l 
   */
  public  String  reg_type;

  /** 
   * <em>reg_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  reg_date;

  /** 
   * <em>final_mod_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  final_mod_date;

  /** 
   * <em>final_mod_time</em>�J�����̒l 
   */
  public  String  final_mod_time;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>commition_per_unit</em>�J�����̒l 
   */
  public  long  commition_per_unit;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean comm_product_code_is_set = false;
  private boolean comm_product_code_is_modified = false;
  private boolean reg_no_is_set = false;
  private boolean reg_no_is_modified = false;
  private boolean appli_start_date_is_set = false;
  private boolean appli_start_date_is_modified = false;
  private boolean appli_end_date_is_set = false;
  private boolean appli_end_date_is_modified = false;
  private boolean sequence_no_is_set = false;
  private boolean sequence_no_is_modified = false;
  private boolean min_turnover_is_set = false;
  private boolean min_turnover_is_modified = false;
  private boolean max_turnover_is_set = false;
  private boolean max_turnover_is_modified = false;
  private boolean charge_ratio_is_set = false;
  private boolean charge_ratio_is_modified = false;
  private boolean added_price_is_set = false;
  private boolean added_price_is_modified = false;
  private boolean reg_type_is_set = false;
  private boolean reg_type_is_modified = false;
  private boolean reg_date_is_set = false;
  private boolean reg_date_is_modified = false;
  private boolean final_mod_date_is_set = false;
  private boolean final_mod_date_is_modified = false;
  private boolean final_mod_time_is_set = false;
  private boolean final_mod_time_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean commition_per_unit_is_set = false;
  private boolean commition_per_unit_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "comm_product_code=" + comm_product_code
      + "," + "reg_no=" + reg_no
      + "," + "appli_start_date=" + appli_start_date
      + "," + "sequence_no=" + sequence_no
      + "," + "appli_end_date=" +appli_end_date
      + "," + "min_turnover=" +min_turnover
      + "," + "max_turnover=" +max_turnover
      + "," + "charge_ratio=" +charge_ratio
      + "," + "added_price=" +added_price
      + "," + "reg_type=" +reg_type
      + "," + "reg_date=" +reg_date
      + "," + "final_mod_date=" +final_mod_date
      + "," + "final_mod_time=" +final_mod_time
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "commition_per_unit=" +commition_per_unit
      + "}";
  }


  /** 
   * �l�����ݒ��EquityCommCondParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public EquityCommCondParams() {
    institution_code_is_modified = true;
    comm_product_code_is_modified = true;
    reg_no_is_modified = true;
    appli_start_date_is_modified = true;
    sequence_no_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���EquityCommCondRow�I�u�W�F�N�g�̒l�𗘗p����EquityCommCondParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����EquityCommCondRow�I�u�W�F�N�g 
   */
  public EquityCommCondParams( EquityCommCondRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    comm_product_code = p_row.getCommProductCode();
    comm_product_code_is_set = p_row.getCommProductCodeIsSet();
    comm_product_code_is_modified = p_row.getCommProductCodeIsModified();
    reg_no = p_row.getRegNo();
    reg_no_is_set = p_row.getRegNoIsSet();
    reg_no_is_modified = p_row.getRegNoIsModified();
    appli_start_date = p_row.getAppliStartDate();
    appli_start_date_is_set = p_row.getAppliStartDateIsSet();
    appli_start_date_is_modified = p_row.getAppliStartDateIsModified();
    sequence_no = p_row.getSequenceNo();
    sequence_no_is_set = p_row.getSequenceNoIsSet();
    sequence_no_is_modified = p_row.getSequenceNoIsModified();
    appli_end_date = p_row.getAppliEndDate();
    appli_end_date_is_set = p_row.getAppliEndDateIsSet();
    appli_end_date_is_modified = p_row.getAppliEndDateIsModified();
    min_turnover = p_row.getMinTurnover();
    min_turnover_is_set = p_row.getMinTurnoverIsSet();
    min_turnover_is_modified = p_row.getMinTurnoverIsModified();
    max_turnover = p_row.getMaxTurnover();
    max_turnover_is_set = p_row.getMaxTurnoverIsSet();
    max_turnover_is_modified = p_row.getMaxTurnoverIsModified();
    charge_ratio = p_row.getChargeRatio();
    charge_ratio_is_set = p_row.getChargeRatioIsSet();
    charge_ratio_is_modified = p_row.getChargeRatioIsModified();
    added_price = p_row.getAddedPrice();
    added_price_is_set = p_row.getAddedPriceIsSet();
    added_price_is_modified = p_row.getAddedPriceIsModified();
    reg_type = p_row.getRegType();
    reg_type_is_set = p_row.getRegTypeIsSet();
    reg_type_is_modified = p_row.getRegTypeIsModified();
    reg_date = p_row.getRegDate();
    reg_date_is_set = p_row.getRegDateIsSet();
    reg_date_is_modified = p_row.getRegDateIsModified();
    final_mod_date = p_row.getFinalModDate();
    final_mod_date_is_set = p_row.getFinalModDateIsSet();
    final_mod_date_is_modified = p_row.getFinalModDateIsModified();
    final_mod_time = p_row.getFinalModTime();
    final_mod_time_is_set = p_row.getFinalModTimeIsSet();
    final_mod_time_is_modified = p_row.getFinalModTimeIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    commition_per_unit = p_row.getCommitionPerUnit();
    commition_per_unit_is_set = p_row.getCommitionPerUnitIsSet();
    commition_per_unit_is_modified = p_row.getCommitionPerUnitIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      appli_end_date_is_set = true;
      appli_end_date_is_modified = true;
      min_turnover_is_set = true;
      min_turnover_is_modified = true;
      max_turnover_is_set = true;
      max_turnover_is_modified = true;
      charge_ratio_is_set = true;
      charge_ratio_is_modified = true;
      added_price_is_set = true;
      added_price_is_modified = true;
      reg_type_is_set = true;
      reg_type_is_modified = true;
      reg_date_is_set = true;
      reg_date_is_modified = true;
      final_mod_date_is_set = true;
      final_mod_date_is_modified = true;
      final_mod_time_is_set = true;
      final_mod_time_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      commition_per_unit_is_set = true;
      commition_per_unit_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof EquityCommCondRow ) )
       return false;
    return fieldsEqual( (EquityCommCondRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�EquityCommCondRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( EquityCommCondRow row )
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
    if ( reg_no == null ) {
      if ( row.getRegNo() != null )
        return false;
    } else if ( !reg_no.equals( row.getRegNo() ) ) {
        return false;
    }
    if ( appli_start_date == null ) {
      if ( row.getAppliStartDate() != null )
        return false;
    } else if ( !appli_start_date.equals( row.getAppliStartDate() ) ) {
        return false;
    }
    if ( appli_end_date == null ) {
      if ( row.getAppliEndDate() != null )
        return false;
    } else if ( !appli_end_date.equals( row.getAppliEndDate() ) ) {
        return false;
    }
    if ( sequence_no == null ) {
      if ( row.getSequenceNo() != null )
        return false;
    } else if ( !sequence_no.equals( row.getSequenceNo() ) ) {
        return false;
    }
    if ( min_turnover != row.getMinTurnover() )
      return false;
    if ( max_turnover != row.getMaxTurnover() )
      return false;
    if ( charge_ratio != row.getChargeRatio() )
      return false;
    if ( added_price != row.getAddedPrice() )
      return false;
    if ( reg_type == null ) {
      if ( row.getRegType() != null )
        return false;
    } else if ( !reg_type.equals( row.getRegType() ) ) {
        return false;
    }
    if ( reg_date == null ) {
      if ( row.getRegDate() != null )
        return false;
    } else if ( !reg_date.equals( row.getRegDate() ) ) {
        return false;
    }
    if ( final_mod_date == null ) {
      if ( row.getFinalModDate() != null )
        return false;
    } else if ( !final_mod_date.equals( row.getFinalModDate() ) ) {
        return false;
    }
    if ( final_mod_time == null ) {
      if ( row.getFinalModTime() != null )
        return false;
    } else if ( !final_mod_time.equals( row.getFinalModTime() ) ) {
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
    if ( commition_per_unit != row.getCommitionPerUnit() )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (reg_no!=null? reg_no.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
        + (appli_end_date!=null? appli_end_date.hashCode(): 0) 
        + (sequence_no!=null? sequence_no.hashCode(): 0) 
        + ((int) min_turnover)
        + ((int) max_turnover)
        + ((int) charge_ratio)
        + ((int) added_price)
        + (reg_type!=null? reg_type.hashCode(): 0) 
        + (reg_date!=null? reg_date.hashCode(): 0) 
        + (final_mod_date!=null? final_mod_date.hashCode(): 0) 
        + (final_mod_time!=null? final_mod_time.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + ((int) commition_per_unit)
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !appli_end_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_end_date' must be set before inserting.");
		if ( !min_turnover_is_set )
			throw new IllegalArgumentException("non-nullable field 'min_turnover' must be set before inserting.");
		if ( !max_turnover_is_set )
			throw new IllegalArgumentException("non-nullable field 'max_turnover' must be set before inserting.");
		if ( !charge_ratio_is_set )
			throw new IllegalArgumentException("non-nullable field 'charge_ratio' must be set before inserting.");
		if ( !added_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'added_price' must be set before inserting.");
		if ( !commition_per_unit_is_set )
			throw new IllegalArgumentException("non-nullable field 'commition_per_unit' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("comm_product_code",comm_product_code);
		map.put("reg_no",reg_no);
		map.put("appli_start_date",appli_start_date);
		map.put("appli_end_date",appli_end_date);
		map.put("sequence_no",sequence_no);
		map.put("min_turnover",new Double(min_turnover));
		map.put("max_turnover",new Double(max_turnover));
		map.put("charge_ratio",new Double(charge_ratio));
		map.put("added_price",new Double(added_price));
		if ( reg_type != null )
			map.put("reg_type",reg_type);
		if ( reg_date != null )
			map.put("reg_date",reg_date);
		if ( final_mod_date != null )
			map.put("final_mod_date",final_mod_date);
		if ( final_mod_time != null )
			map.put("final_mod_time",final_mod_time);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("commition_per_unit",new Long(commition_per_unit));
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( appli_end_date_is_modified )
			map.put("appli_end_date",appli_end_date);
		if ( min_turnover_is_modified )
			map.put("min_turnover",new Double(min_turnover));
		if ( max_turnover_is_modified )
			map.put("max_turnover",new Double(max_turnover));
		if ( charge_ratio_is_modified )
			map.put("charge_ratio",new Double(charge_ratio));
		if ( added_price_is_modified )
			map.put("added_price",new Double(added_price));
		if ( reg_type_is_modified )
			map.put("reg_type",reg_type);
		if ( reg_date_is_modified )
			map.put("reg_date",reg_date);
		if ( final_mod_date_is_modified )
			map.put("final_mod_date",final_mod_date);
		if ( final_mod_time_is_modified )
			map.put("final_mod_time",final_mod_time);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( commition_per_unit_is_modified )
			map.put("commition_per_unit",new Long(commition_per_unit));
		if (map.size() == 0) {
			if ( appli_end_date_is_set )
				map.put("appli_end_date",appli_end_date);
			if ( min_turnover_is_set )
				map.put("min_turnover",new Double(min_turnover));
			if ( max_turnover_is_set )
				map.put("max_turnover",new Double(max_turnover));
			if ( charge_ratio_is_set )
				map.put("charge_ratio",new Double(charge_ratio));
			if ( added_price_is_set )
				map.put("added_price",new Double(added_price));
			map.put("reg_type",reg_type);
			map.put("reg_date",reg_date);
			map.put("final_mod_date",final_mod_date);
			map.put("final_mod_time",final_mod_time);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( commition_per_unit_is_set )
				map.put("commition_per_unit",new Long(commition_per_unit));
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
   * <em>reg_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRegNo()
  {
    return reg_no;
  }


  /** 
   * <em>reg_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegNoIsSet() {
    return reg_no_is_set;
  }


  /** 
   * <em>reg_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegNoIsModified() {
    return reg_no_is_modified;
  }


  /** 
   * <em>appli_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAppliStartDate()
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
   * <em>appli_end_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAppliEndDate()
  {
    return appli_end_date;
  }


  /** 
   * <em>appli_end_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliEndDateIsSet() {
    return appli_end_date_is_set;
  }


  /** 
   * <em>appli_end_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliEndDateIsModified() {
    return appli_end_date_is_modified;
  }


  /** 
   * <em>sequence_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSequenceNo()
  {
    return sequence_no;
  }


  /** 
   * <em>sequence_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSequenceNoIsSet() {
    return sequence_no_is_set;
  }


  /** 
   * <em>sequence_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSequenceNoIsModified() {
    return sequence_no_is_modified;
  }


  /** 
   * <em>min_turnover</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getMinTurnover()
  {
    return min_turnover;
  }


  /** 
   * <em>min_turnover</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMinTurnoverIsSet() {
    return min_turnover_is_set;
  }


  /** 
   * <em>min_turnover</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMinTurnoverIsModified() {
    return min_turnover_is_modified;
  }


  /** 
   * <em>max_turnover</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getMaxTurnover()
  {
    return max_turnover;
  }


  /** 
   * <em>max_turnover</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMaxTurnoverIsSet() {
    return max_turnover_is_set;
  }


  /** 
   * <em>max_turnover</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMaxTurnoverIsModified() {
    return max_turnover_is_modified;
  }


  /** 
   * <em>charge_ratio</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getChargeRatio()
  {
    return charge_ratio;
  }


  /** 
   * <em>charge_ratio</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getChargeRatioIsSet() {
    return charge_ratio_is_set;
  }


  /** 
   * <em>charge_ratio</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getChargeRatioIsModified() {
    return charge_ratio_is_modified;
  }


  /** 
   * <em>added_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAddedPrice()
  {
    return added_price;
  }


  /** 
   * <em>added_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAddedPriceIsSet() {
    return added_price_is_set;
  }


  /** 
   * <em>added_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAddedPriceIsModified() {
    return added_price_is_modified;
  }


  /** 
   * <em>reg_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRegType()
  {
    return reg_type;
  }


  /** 
   * <em>reg_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegTypeIsSet() {
    return reg_type_is_set;
  }


  /** 
   * <em>reg_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegTypeIsModified() {
    return reg_type_is_modified;
  }


  /** 
   * <em>reg_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getRegDate()
  {
    return reg_date;
  }


  /** 
   * <em>reg_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegDateIsSet() {
    return reg_date_is_set;
  }


  /** 
   * <em>reg_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegDateIsModified() {
    return reg_date_is_modified;
  }


  /** 
   * <em>final_mod_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getFinalModDate()
  {
    return final_mod_date;
  }


  /** 
   * <em>final_mod_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinalModDateIsSet() {
    return final_mod_date_is_set;
  }


  /** 
   * <em>final_mod_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinalModDateIsModified() {
    return final_mod_date_is_modified;
  }


  /** 
   * <em>final_mod_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinalModTime()
  {
    return final_mod_time;
  }


  /** 
   * <em>final_mod_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinalModTimeIsSet() {
    return final_mod_time_is_set;
  }


  /** 
   * <em>final_mod_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinalModTimeIsModified() {
    return final_mod_time_is_modified;
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
   * <em>commition_per_unit</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getCommitionPerUnit()
  {
    return commition_per_unit;
  }


  /** 
   * <em>commition_per_unit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommitionPerUnitIsSet() {
    return commition_per_unit_is_set;
  }


  /** 
   * <em>commition_per_unit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommitionPerUnitIsModified() {
    return commition_per_unit_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new EquityCommCondPK(institution_code, comm_product_code, reg_no, appli_start_date, sequence_no);
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
   * <em>reg_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_regNo <em>reg_no</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setRegNo( String p_regNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reg_no = p_regNo;
    reg_no_is_set = true;
    reg_no_is_modified = true;
  }


  /** 
   * <em>appli_start_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliStartDate <em>appli_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAppliStartDate( java.sql.Timestamp p_appliStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_date = p_appliStartDate;
    appli_start_date_is_set = true;
    appli_start_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>appli_start_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAppliStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_start_date_is_set = true;
    appli_start_date_is_modified = true;
  }


  /** 
   * <em>appli_end_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliEndDate <em>appli_end_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAppliEndDate( java.sql.Timestamp p_appliEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_date = p_appliEndDate;
    appli_end_date_is_set = true;
    appli_end_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>appli_end_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAppliEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_end_date_is_set = true;
    appli_end_date_is_modified = true;
  }


  /** 
   * <em>sequence_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sequenceNo <em>sequence_no</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setSequenceNo( String p_sequenceNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sequence_no = p_sequenceNo;
    sequence_no_is_set = true;
    sequence_no_is_modified = true;
  }


  /** 
   * <em>min_turnover</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_minTurnover <em>min_turnover</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setMinTurnover( double p_minTurnover )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    min_turnover = p_minTurnover;
    min_turnover_is_set = true;
    min_turnover_is_modified = true;
  }


  /** 
   * <em>max_turnover</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_maxTurnover <em>max_turnover</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setMaxTurnover( double p_maxTurnover )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_turnover = p_maxTurnover;
    max_turnover_is_set = true;
    max_turnover_is_modified = true;
  }


  /** 
   * <em>charge_ratio</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_chargeRatio <em>charge_ratio</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setChargeRatio( double p_chargeRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    charge_ratio = p_chargeRatio;
    charge_ratio_is_set = true;
    charge_ratio_is_modified = true;
  }


  /** 
   * <em>added_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_addedPrice <em>added_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setAddedPrice( double p_addedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    added_price = p_addedPrice;
    added_price_is_set = true;
    added_price_is_modified = true;
  }


  /** 
   * <em>reg_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_regType <em>reg_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRegType( String p_regType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reg_type = p_regType;
    reg_type_is_set = true;
    reg_type_is_modified = true;
  }


  /** 
   * <em>reg_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_regDate <em>reg_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setRegDate( java.sql.Timestamp p_regDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reg_date = p_regDate;
    reg_date_is_set = true;
    reg_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>reg_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setRegDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    reg_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    reg_date_is_set = true;
    reg_date_is_modified = true;
  }


  /** 
   * <em>final_mod_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finalModDate <em>final_mod_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setFinalModDate( java.sql.Timestamp p_finalModDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    final_mod_date = p_finalModDate;
    final_mod_date_is_set = true;
    final_mod_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>final_mod_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setFinalModDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    final_mod_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    final_mod_date_is_set = true;
    final_mod_date_is_modified = true;
  }


  /** 
   * <em>final_mod_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finalModTime <em>final_mod_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setFinalModTime( String p_finalModTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    final_mod_time = p_finalModTime;
    final_mod_time_is_set = true;
    final_mod_time_is_modified = true;
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
   * <em>commition_per_unit</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_commitionPerUnit <em>commition_per_unit</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setCommitionPerUnit( long p_commitionPerUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commition_per_unit = p_commitionPerUnit;
    commition_per_unit_is_set = true;
    commition_per_unit_is_modified = true;
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
                else if ( name.equals("appli_end_date") ) {
                    return this.appli_end_date;
                }
                else if ( name.equals("added_price") ) {
                    return new Double( added_price );
                }
                break;
            case 'c':
                if ( name.equals("comm_product_code") ) {
                    return this.comm_product_code;
                }
                else if ( name.equals("charge_ratio") ) {
                    return new Double( charge_ratio );
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("commition_per_unit") ) {
                    return new Long( commition_per_unit );
                }
                break;
            case 'f':
                if ( name.equals("final_mod_date") ) {
                    return this.final_mod_date;
                }
                else if ( name.equals("final_mod_time") ) {
                    return this.final_mod_time;
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
            case 'm':
                if ( name.equals("min_turnover") ) {
                    return new Double( min_turnover );
                }
                else if ( name.equals("max_turnover") ) {
                    return new Double( max_turnover );
                }
                break;
            case 'r':
                if ( name.equals("reg_no") ) {
                    return this.reg_no;
                }
                else if ( name.equals("reg_type") ) {
                    return this.reg_type;
                }
                else if ( name.equals("reg_date") ) {
                    return this.reg_date;
                }
                break;
            case 's':
                if ( name.equals("sequence_no") ) {
                    return this.sequence_no;
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
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_start_date = (java.sql.Timestamp) value;
                    if (this.appli_start_date_is_set)
                        this.appli_start_date_is_modified = true;
                    this.appli_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("appli_end_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_end_date = (java.sql.Timestamp) value;
                    if (this.appli_end_date_is_set)
                        this.appli_end_date_is_modified = true;
                    this.appli_end_date_is_set = true;
                    return;
                }
                else if ( name.equals("added_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'added_price' must be Double: '"+value+"' is not." );
                    this.added_price = ((Double) value).doubleValue();
                    if (this.added_price_is_set)
                        this.added_price_is_modified = true;
                    this.added_price_is_set = true;
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
                else if ( name.equals("charge_ratio") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'charge_ratio' must be Double: '"+value+"' is not." );
                    this.charge_ratio = ((Double) value).doubleValue();
                    if (this.charge_ratio_is_set)
                        this.charge_ratio_is_modified = true;
                    this.charge_ratio_is_set = true;
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
                else if ( name.equals("commition_per_unit") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'commition_per_unit' must be Long: '"+value+"' is not." );
                    this.commition_per_unit = ((Long) value).longValue();
                    if (this.commition_per_unit_is_set)
                        this.commition_per_unit_is_modified = true;
                    this.commition_per_unit_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("final_mod_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'final_mod_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.final_mod_date = (java.sql.Timestamp) value;
                    if (this.final_mod_date_is_set)
                        this.final_mod_date_is_modified = true;
                    this.final_mod_date_is_set = true;
                    return;
                }
                else if ( name.equals("final_mod_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'final_mod_time' must be String: '"+value+"' is not." );
                    this.final_mod_time = (String) value;
                    if (this.final_mod_time_is_set)
                        this.final_mod_time_is_modified = true;
                    this.final_mod_time_is_set = true;
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
            case 'm':
                if ( name.equals("min_turnover") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'min_turnover' must be Double: '"+value+"' is not." );
                    this.min_turnover = ((Double) value).doubleValue();
                    if (this.min_turnover_is_set)
                        this.min_turnover_is_modified = true;
                    this.min_turnover_is_set = true;
                    return;
                }
                else if ( name.equals("max_turnover") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_turnover' must be Double: '"+value+"' is not." );
                    this.max_turnover = ((Double) value).doubleValue();
                    if (this.max_turnover_is_set)
                        this.max_turnover_is_modified = true;
                    this.max_turnover_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("reg_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reg_no' must be String: '"+value+"' is not." );
                    this.reg_no = (String) value;
                    if (this.reg_no_is_set)
                        this.reg_no_is_modified = true;
                    this.reg_no_is_set = true;
                    return;
                }
                else if ( name.equals("reg_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reg_type' must be String: '"+value+"' is not." );
                    this.reg_type = (String) value;
                    if (this.reg_type_is_set)
                        this.reg_type_is_modified = true;
                    this.reg_type_is_set = true;
                    return;
                }
                else if ( name.equals("reg_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'reg_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.reg_date = (java.sql.Timestamp) value;
                    if (this.reg_date_is_set)
                        this.reg_date_is_modified = true;
                    this.reg_date_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sequence_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sequence_no' must be String: '"+value+"' is not." );
                    this.sequence_no = (String) value;
                    if (this.sequence_no_is_set)
                        this.sequence_no_is_modified = true;
                    this.sequence_no_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
