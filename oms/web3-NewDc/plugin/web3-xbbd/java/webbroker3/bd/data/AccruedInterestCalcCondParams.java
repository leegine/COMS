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
filename	AccruedInterestCalcCondParams.java;


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
 * accrued_interest_calc_cond�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link AccruedInterestCalcCondRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link AccruedInterestCalcCondParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link AccruedInterestCalcCondParams}��{@@link AccruedInterestCalcCondRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccruedInterestCalcCondPK 
 * @@see AccruedInterestCalcCondRow 
 */
public class AccruedInterestCalcCondParams extends Params implements AccruedInterestCalcCondRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "accrued_interest_calc_cond";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = AccruedInterestCalcCondRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return AccruedInterestCalcCondRow.TYPE;
  }


  /** 
   * <em>accrued_interest_calc_type</em>�J�����̒l 
   */
  public  String  accrued_interest_calc_type;

  /** 
   * <em>calc_type_name</em>�J�����̒l 
   */
  public  String  calc_type_name;

  /** 
   * <em>base_date_div</em>�J�����̒l 
   */
  public  String  base_date_div;

  /** 
   * <em>base_days_div</em>�J�����̒l 
   */
  public  String  base_days_div;

  /** 
   * <em>elapsed_days_div</em>�J�����̒l 
   */
  public  String  elapsed_days_div;

  /** 
   * <em>non_elapsed_days_div</em>�J�����̒l 
   */
  public  String  non_elapsed_days_div;

  /** 
   * <em>taxation_div</em>�J�����̒l 
   */
  public  String  taxation_div;

  /** 
   * <em>calc_base_form</em>�J�����̒l 
   */
  public  String  calc_base_form;

  /** 
   * <em>unit_price_scale</em>�J�����̒l 
   */
  public  Integer  unit_price_scale;

  /** 
   * <em>unit_round_div</em>�J�����̒l 
   */
  public  String  unit_round_div;

  /** 
   * <em>amount_round_div</em>�J�����̒l 
   */
  public  String  amount_round_div;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean accrued_interest_calc_type_is_set = false;
  private boolean accrued_interest_calc_type_is_modified = false;
  private boolean calc_type_name_is_set = false;
  private boolean calc_type_name_is_modified = false;
  private boolean base_date_div_is_set = false;
  private boolean base_date_div_is_modified = false;
  private boolean base_days_div_is_set = false;
  private boolean base_days_div_is_modified = false;
  private boolean elapsed_days_div_is_set = false;
  private boolean elapsed_days_div_is_modified = false;
  private boolean non_elapsed_days_div_is_set = false;
  private boolean non_elapsed_days_div_is_modified = false;
  private boolean taxation_div_is_set = false;
  private boolean taxation_div_is_modified = false;
  private boolean calc_base_form_is_set = false;
  private boolean calc_base_form_is_modified = false;
  private boolean unit_price_scale_is_set = false;
  private boolean unit_price_scale_is_modified = false;
  private boolean unit_round_div_is_set = false;
  private boolean unit_round_div_is_modified = false;
  private boolean amount_round_div_is_set = false;
  private boolean amount_round_div_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "accrued_interest_calc_type=" + accrued_interest_calc_type
      + "," + "calc_type_name=" +calc_type_name
      + "," + "base_date_div=" +base_date_div
      + "," + "base_days_div=" +base_days_div
      + "," + "elapsed_days_div=" +elapsed_days_div
      + "," + "non_elapsed_days_div=" +non_elapsed_days_div
      + "," + "taxation_div=" +taxation_div
      + "," + "calc_base_form=" +calc_base_form
      + "," + "unit_price_scale=" +unit_price_scale
      + "," + "unit_round_div=" +unit_round_div
      + "," + "amount_round_div=" +amount_round_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��AccruedInterestCalcCondParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public AccruedInterestCalcCondParams() {
    accrued_interest_calc_type_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���AccruedInterestCalcCondRow�I�u�W�F�N�g�̒l�𗘗p����AccruedInterestCalcCondParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����AccruedInterestCalcCondRow�I�u�W�F�N�g 
   */
  public AccruedInterestCalcCondParams( AccruedInterestCalcCondRow p_row )
  {
    accrued_interest_calc_type = p_row.getAccruedInterestCalcType();
    accrued_interest_calc_type_is_set = p_row.getAccruedInterestCalcTypeIsSet();
    accrued_interest_calc_type_is_modified = p_row.getAccruedInterestCalcTypeIsModified();
    calc_type_name = p_row.getCalcTypeName();
    calc_type_name_is_set = p_row.getCalcTypeNameIsSet();
    calc_type_name_is_modified = p_row.getCalcTypeNameIsModified();
    base_date_div = p_row.getBaseDateDiv();
    base_date_div_is_set = p_row.getBaseDateDivIsSet();
    base_date_div_is_modified = p_row.getBaseDateDivIsModified();
    base_days_div = p_row.getBaseDaysDiv();
    base_days_div_is_set = p_row.getBaseDaysDivIsSet();
    base_days_div_is_modified = p_row.getBaseDaysDivIsModified();
    elapsed_days_div = p_row.getElapsedDaysDiv();
    elapsed_days_div_is_set = p_row.getElapsedDaysDivIsSet();
    elapsed_days_div_is_modified = p_row.getElapsedDaysDivIsModified();
    non_elapsed_days_div = p_row.getNonElapsedDaysDiv();
    non_elapsed_days_div_is_set = p_row.getNonElapsedDaysDivIsSet();
    non_elapsed_days_div_is_modified = p_row.getNonElapsedDaysDivIsModified();
    taxation_div = p_row.getTaxationDiv();
    taxation_div_is_set = p_row.getTaxationDivIsSet();
    taxation_div_is_modified = p_row.getTaxationDivIsModified();
    calc_base_form = p_row.getCalcBaseForm();
    calc_base_form_is_set = p_row.getCalcBaseFormIsSet();
    calc_base_form_is_modified = p_row.getCalcBaseFormIsModified();
    if ( !p_row.getUnitPriceScaleIsNull() )
      unit_price_scale = new Integer( p_row.getUnitPriceScale() );
    unit_price_scale_is_set = p_row.getUnitPriceScaleIsSet();
    unit_price_scale_is_modified = p_row.getUnitPriceScaleIsModified();
    unit_round_div = p_row.getUnitRoundDiv();
    unit_round_div_is_set = p_row.getUnitRoundDivIsSet();
    unit_round_div_is_modified = p_row.getUnitRoundDivIsModified();
    amount_round_div = p_row.getAmountRoundDiv();
    amount_round_div_is_set = p_row.getAmountRoundDivIsSet();
    amount_round_div_is_modified = p_row.getAmountRoundDivIsModified();
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
      calc_type_name_is_set = true;
      calc_type_name_is_modified = true;
      base_date_div_is_set = true;
      base_date_div_is_modified = true;
      base_days_div_is_set = true;
      base_days_div_is_modified = true;
      elapsed_days_div_is_set = true;
      elapsed_days_div_is_modified = true;
      non_elapsed_days_div_is_set = true;
      non_elapsed_days_div_is_modified = true;
      taxation_div_is_set = true;
      taxation_div_is_modified = true;
      calc_base_form_is_set = true;
      calc_base_form_is_modified = true;
      unit_price_scale_is_set = true;
      unit_price_scale_is_modified = true;
      unit_round_div_is_set = true;
      unit_round_div_is_modified = true;
      amount_round_div_is_set = true;
      amount_round_div_is_modified = true;
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
    if ( !( other instanceof AccruedInterestCalcCondRow ) )
       return false;
    return fieldsEqual( (AccruedInterestCalcCondRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�AccruedInterestCalcCondRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( AccruedInterestCalcCondRow row )
  {
    if ( accrued_interest_calc_type == null ) {
      if ( row.getAccruedInterestCalcType() != null )
        return false;
    } else if ( !accrued_interest_calc_type.equals( row.getAccruedInterestCalcType() ) ) {
        return false;
    }
    if ( calc_type_name == null ) {
      if ( row.getCalcTypeName() != null )
        return false;
    } else if ( !calc_type_name.equals( row.getCalcTypeName() ) ) {
        return false;
    }
    if ( base_date_div == null ) {
      if ( row.getBaseDateDiv() != null )
        return false;
    } else if ( !base_date_div.equals( row.getBaseDateDiv() ) ) {
        return false;
    }
    if ( base_days_div == null ) {
      if ( row.getBaseDaysDiv() != null )
        return false;
    } else if ( !base_days_div.equals( row.getBaseDaysDiv() ) ) {
        return false;
    }
    if ( elapsed_days_div == null ) {
      if ( row.getElapsedDaysDiv() != null )
        return false;
    } else if ( !elapsed_days_div.equals( row.getElapsedDaysDiv() ) ) {
        return false;
    }
    if ( non_elapsed_days_div == null ) {
      if ( row.getNonElapsedDaysDiv() != null )
        return false;
    } else if ( !non_elapsed_days_div.equals( row.getNonElapsedDaysDiv() ) ) {
        return false;
    }
    if ( taxation_div == null ) {
      if ( row.getTaxationDiv() != null )
        return false;
    } else if ( !taxation_div.equals( row.getTaxationDiv() ) ) {
        return false;
    }
    if ( calc_base_form == null ) {
      if ( row.getCalcBaseForm() != null )
        return false;
    } else if ( !calc_base_form.equals( row.getCalcBaseForm() ) ) {
        return false;
    }
    if ( unit_price_scale == null ) {
      if ( !row.getUnitPriceScaleIsNull() )
        return false;
    } else if ( row.getUnitPriceScaleIsNull() || ( unit_price_scale.intValue() != row.getUnitPriceScale() ) ) {
        return false;
    }
    if ( unit_round_div == null ) {
      if ( row.getUnitRoundDiv() != null )
        return false;
    } else if ( !unit_round_div.equals( row.getUnitRoundDiv() ) ) {
        return false;
    }
    if ( amount_round_div == null ) {
      if ( row.getAmountRoundDiv() != null )
        return false;
    } else if ( !amount_round_div.equals( row.getAmountRoundDiv() ) ) {
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
      return  (accrued_interest_calc_type!=null? accrued_interest_calc_type.hashCode(): 0) 
        + (calc_type_name!=null? calc_type_name.hashCode(): 0) 
        + (base_date_div!=null? base_date_div.hashCode(): 0) 
        + (base_days_div!=null? base_days_div.hashCode(): 0) 
        + (elapsed_days_div!=null? elapsed_days_div.hashCode(): 0) 
        + (non_elapsed_days_div!=null? non_elapsed_days_div.hashCode(): 0) 
        + (taxation_div!=null? taxation_div.hashCode(): 0) 
        + (calc_base_form!=null? calc_base_form.hashCode(): 0) 
        + (unit_price_scale!=null? unit_price_scale.hashCode(): 0) 
        + (unit_round_div!=null? unit_round_div.hashCode(): 0) 
        + (amount_round_div!=null? amount_round_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !calc_type_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'calc_type_name' must be set before inserting.");
		if ( !base_date_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'base_date_div' must be set before inserting.");
		if ( !base_days_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'base_days_div' must be set before inserting.");
		if ( !elapsed_days_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'elapsed_days_div' must be set before inserting.");
		if ( !non_elapsed_days_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'non_elapsed_days_div' must be set before inserting.");
		if ( !taxation_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'taxation_div' must be set before inserting.");
		if ( !calc_base_form_is_set )
			throw new IllegalArgumentException("non-nullable field 'calc_base_form' must be set before inserting.");
		if ( !amount_round_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'amount_round_div' must be set before inserting.");
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
		map.put("accrued_interest_calc_type",accrued_interest_calc_type);
		map.put("calc_type_name",calc_type_name);
		map.put("base_date_div",base_date_div);
		map.put("base_days_div",base_days_div);
		map.put("elapsed_days_div",elapsed_days_div);
		map.put("non_elapsed_days_div",non_elapsed_days_div);
		map.put("taxation_div",taxation_div);
		map.put("calc_base_form",calc_base_form);
		if ( unit_price_scale != null )
			map.put("unit_price_scale",unit_price_scale);
		if ( unit_round_div != null )
			map.put("unit_round_div",unit_round_div);
		map.put("amount_round_div",amount_round_div);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( calc_type_name_is_modified )
			map.put("calc_type_name",calc_type_name);
		if ( base_date_div_is_modified )
			map.put("base_date_div",base_date_div);
		if ( base_days_div_is_modified )
			map.put("base_days_div",base_days_div);
		if ( elapsed_days_div_is_modified )
			map.put("elapsed_days_div",elapsed_days_div);
		if ( non_elapsed_days_div_is_modified )
			map.put("non_elapsed_days_div",non_elapsed_days_div);
		if ( taxation_div_is_modified )
			map.put("taxation_div",taxation_div);
		if ( calc_base_form_is_modified )
			map.put("calc_base_form",calc_base_form);
		if ( unit_price_scale_is_modified )
			map.put("unit_price_scale",unit_price_scale);
		if ( unit_round_div_is_modified )
			map.put("unit_round_div",unit_round_div);
		if ( amount_round_div_is_modified )
			map.put("amount_round_div",amount_round_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( calc_type_name_is_set )
				map.put("calc_type_name",calc_type_name);
			if ( base_date_div_is_set )
				map.put("base_date_div",base_date_div);
			if ( base_days_div_is_set )
				map.put("base_days_div",base_days_div);
			if ( elapsed_days_div_is_set )
				map.put("elapsed_days_div",elapsed_days_div);
			if ( non_elapsed_days_div_is_set )
				map.put("non_elapsed_days_div",non_elapsed_days_div);
			if ( taxation_div_is_set )
				map.put("taxation_div",taxation_div);
			if ( calc_base_form_is_set )
				map.put("calc_base_form",calc_base_form);
			map.put("unit_price_scale",unit_price_scale);
			map.put("unit_round_div",unit_round_div);
			if ( amount_round_div_is_set )
				map.put("amount_round_div",amount_round_div);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>accrued_interest_calc_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAccruedInterestCalcType()
  {
    return accrued_interest_calc_type;
  }


  /** 
   * <em>accrued_interest_calc_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccruedInterestCalcTypeIsSet() {
    return accrued_interest_calc_type_is_set;
  }


  /** 
   * <em>accrued_interest_calc_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccruedInterestCalcTypeIsModified() {
    return accrued_interest_calc_type_is_modified;
  }


  /** 
   * <em>calc_type_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCalcTypeName()
  {
    return calc_type_name;
  }


  /** 
   * <em>calc_type_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcTypeNameIsSet() {
    return calc_type_name_is_set;
  }


  /** 
   * <em>calc_type_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcTypeNameIsModified() {
    return calc_type_name_is_modified;
  }


  /** 
   * <em>base_date_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBaseDateDiv()
  {
    return base_date_div;
  }


  /** 
   * <em>base_date_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBaseDateDivIsSet() {
    return base_date_div_is_set;
  }


  /** 
   * <em>base_date_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBaseDateDivIsModified() {
    return base_date_div_is_modified;
  }


  /** 
   * <em>base_days_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBaseDaysDiv()
  {
    return base_days_div;
  }


  /** 
   * <em>base_days_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBaseDaysDivIsSet() {
    return base_days_div_is_set;
  }


  /** 
   * <em>base_days_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBaseDaysDivIsModified() {
    return base_days_div_is_modified;
  }


  /** 
   * <em>elapsed_days_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getElapsedDaysDiv()
  {
    return elapsed_days_div;
  }


  /** 
   * <em>elapsed_days_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getElapsedDaysDivIsSet() {
    return elapsed_days_div_is_set;
  }


  /** 
   * <em>elapsed_days_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getElapsedDaysDivIsModified() {
    return elapsed_days_div_is_modified;
  }


  /** 
   * <em>non_elapsed_days_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getNonElapsedDaysDiv()
  {
    return non_elapsed_days_div;
  }


  /** 
   * <em>non_elapsed_days_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNonElapsedDaysDivIsSet() {
    return non_elapsed_days_div_is_set;
  }


  /** 
   * <em>non_elapsed_days_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNonElapsedDaysDivIsModified() {
    return non_elapsed_days_div_is_modified;
  }


  /** 
   * <em>taxation_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTaxationDiv()
  {
    return taxation_div;
  }


  /** 
   * <em>taxation_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTaxationDivIsSet() {
    return taxation_div_is_set;
  }


  /** 
   * <em>taxation_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTaxationDivIsModified() {
    return taxation_div_is_modified;
  }


  /** 
   * <em>calc_base_form</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCalcBaseForm()
  {
    return calc_base_form;
  }


  /** 
   * <em>calc_base_form</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcBaseFormIsSet() {
    return calc_base_form_is_set;
  }


  /** 
   * <em>calc_base_form</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcBaseFormIsModified() {
    return calc_base_form_is_modified;
  }


  /** 
   * <em>unit_price_scale</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getUnitPriceScale()
  {
    return ( unit_price_scale==null? ((int)0): unit_price_scale.intValue() );
  }


  /** 
   * <em>unit_price_scale</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getUnitPriceScaleIsNull()
  {
    return unit_price_scale == null;
  }


  /** 
   * <em>unit_price_scale</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUnitPriceScaleIsSet() {
    return unit_price_scale_is_set;
  }


  /** 
   * <em>unit_price_scale</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUnitPriceScaleIsModified() {
    return unit_price_scale_is_modified;
  }


  /** 
   * <em>unit_round_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getUnitRoundDiv()
  {
    return unit_round_div;
  }


  /** 
   * <em>unit_round_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUnitRoundDivIsSet() {
    return unit_round_div_is_set;
  }


  /** 
   * <em>unit_round_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUnitRoundDivIsModified() {
    return unit_round_div_is_modified;
  }


  /** 
   * <em>amount_round_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAmountRoundDiv()
  {
    return amount_round_div;
  }


  /** 
   * <em>amount_round_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAmountRoundDivIsSet() {
    return amount_round_div_is_set;
  }


  /** 
   * <em>amount_round_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAmountRoundDivIsModified() {
    return amount_round_div_is_modified;
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
    return new AccruedInterestCalcCondPK(accrued_interest_calc_type);
  }


  /** 
   * <em>accrued_interest_calc_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accruedInterestCalcType <em>accrued_interest_calc_type</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setAccruedInterestCalcType( String p_accruedInterestCalcType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    accrued_interest_calc_type = p_accruedInterestCalcType;
    accrued_interest_calc_type_is_set = true;
    accrued_interest_calc_type_is_modified = true;
  }


  /** 
   * <em>calc_type_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_calcTypeName <em>calc_type_name</em>�J�����̒l������킷60���ȉ���String�l 
   */
  public final void setCalcTypeName( String p_calcTypeName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_type_name = p_calcTypeName;
    calc_type_name_is_set = true;
    calc_type_name_is_modified = true;
  }


  /** 
   * <em>base_date_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_baseDateDiv <em>base_date_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setBaseDateDiv( String p_baseDateDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_date_div = p_baseDateDiv;
    base_date_div_is_set = true;
    base_date_div_is_modified = true;
  }


  /** 
   * <em>base_days_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_baseDaysDiv <em>base_days_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setBaseDaysDiv( String p_baseDaysDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_days_div = p_baseDaysDiv;
    base_days_div_is_set = true;
    base_days_div_is_modified = true;
  }


  /** 
   * <em>elapsed_days_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_elapsedDaysDiv <em>elapsed_days_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setElapsedDaysDiv( String p_elapsedDaysDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    elapsed_days_div = p_elapsedDaysDiv;
    elapsed_days_div_is_set = true;
    elapsed_days_div_is_modified = true;
  }


  /** 
   * <em>non_elapsed_days_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_nonElapsedDaysDiv <em>non_elapsed_days_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setNonElapsedDaysDiv( String p_nonElapsedDaysDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    non_elapsed_days_div = p_nonElapsedDaysDiv;
    non_elapsed_days_div_is_set = true;
    non_elapsed_days_div_is_modified = true;
  }


  /** 
   * <em>taxation_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_taxationDiv <em>taxation_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setTaxationDiv( String p_taxationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    taxation_div = p_taxationDiv;
    taxation_div_is_set = true;
    taxation_div_is_modified = true;
  }


  /** 
   * <em>calc_base_form</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_calcBaseForm <em>calc_base_form</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setCalcBaseForm( String p_calcBaseForm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_base_form = p_calcBaseForm;
    calc_base_form_is_set = true;
    calc_base_form_is_modified = true;
  }


  /** 
   * <em>unit_price_scale</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_unitPriceScale <em>unit_price_scale</em>�J�����̒l������킷2���ȉ���int�l 
   */
  public final void setUnitPriceScale( int p_unitPriceScale )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unit_price_scale = new Integer(p_unitPriceScale);
    unit_price_scale_is_set = true;
    unit_price_scale_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>unit_price_scale</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setUnitPriceScale( Integer p_unitPriceScale )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unit_price_scale = p_unitPriceScale;
    unit_price_scale_is_set = true;
    unit_price_scale_is_modified = true;
  }


  /** 
   * <em>unit_round_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_unitRoundDiv <em>unit_round_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setUnitRoundDiv( String p_unitRoundDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unit_round_div = p_unitRoundDiv;
    unit_round_div_is_set = true;
    unit_round_div_is_modified = true;
  }


  /** 
   * <em>amount_round_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_amountRoundDiv <em>amount_round_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAmountRoundDiv( String p_amountRoundDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_round_div = p_amountRoundDiv;
    amount_round_div_is_set = true;
    amount_round_div_is_modified = true;
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
                if ( name.equals("accrued_interest_calc_type") ) {
                    return this.accrued_interest_calc_type;
                }
                else if ( name.equals("amount_round_div") ) {
                    return this.amount_round_div;
                }
                break;
            case 'b':
                if ( name.equals("base_date_div") ) {
                    return this.base_date_div;
                }
                else if ( name.equals("base_days_div") ) {
                    return this.base_days_div;
                }
                break;
            case 'c':
                if ( name.equals("calc_type_name") ) {
                    return this.calc_type_name;
                }
                else if ( name.equals("calc_base_form") ) {
                    return this.calc_base_form;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("elapsed_days_div") ) {
                    return this.elapsed_days_div;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("non_elapsed_days_div") ) {
                    return this.non_elapsed_days_div;
                }
                break;
            case 't':
                if ( name.equals("taxation_div") ) {
                    return this.taxation_div;
                }
                break;
            case 'u':
                if ( name.equals("unit_price_scale") ) {
                    return this.unit_price_scale;
                }
                else if ( name.equals("unit_round_div") ) {
                    return this.unit_round_div;
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
                if ( name.equals("accrued_interest_calc_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'accrued_interest_calc_type' must be String: '"+value+"' is not." );
                    this.accrued_interest_calc_type = (String) value;
                    if (this.accrued_interest_calc_type_is_set)
                        this.accrued_interest_calc_type_is_modified = true;
                    this.accrued_interest_calc_type_is_set = true;
                    return;
                }
                else if ( name.equals("amount_round_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'amount_round_div' must be String: '"+value+"' is not." );
                    this.amount_round_div = (String) value;
                    if (this.amount_round_div_is_set)
                        this.amount_round_div_is_modified = true;
                    this.amount_round_div_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("base_date_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'base_date_div' must be String: '"+value+"' is not." );
                    this.base_date_div = (String) value;
                    if (this.base_date_div_is_set)
                        this.base_date_div_is_modified = true;
                    this.base_date_div_is_set = true;
                    return;
                }
                else if ( name.equals("base_days_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'base_days_div' must be String: '"+value+"' is not." );
                    this.base_days_div = (String) value;
                    if (this.base_days_div_is_set)
                        this.base_days_div_is_modified = true;
                    this.base_days_div_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("calc_type_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'calc_type_name' must be String: '"+value+"' is not." );
                    this.calc_type_name = (String) value;
                    if (this.calc_type_name_is_set)
                        this.calc_type_name_is_modified = true;
                    this.calc_type_name_is_set = true;
                    return;
                }
                else if ( name.equals("calc_base_form") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'calc_base_form' must be String: '"+value+"' is not." );
                    this.calc_base_form = (String) value;
                    if (this.calc_base_form_is_set)
                        this.calc_base_form_is_modified = true;
                    this.calc_base_form_is_set = true;
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
            case 'e':
                if ( name.equals("elapsed_days_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'elapsed_days_div' must be String: '"+value+"' is not." );
                    this.elapsed_days_div = (String) value;
                    if (this.elapsed_days_div_is_set)
                        this.elapsed_days_div_is_modified = true;
                    this.elapsed_days_div_is_set = true;
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
            case 'n':
                if ( name.equals("non_elapsed_days_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'non_elapsed_days_div' must be String: '"+value+"' is not." );
                    this.non_elapsed_days_div = (String) value;
                    if (this.non_elapsed_days_div_is_set)
                        this.non_elapsed_days_div_is_modified = true;
                    this.non_elapsed_days_div_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("taxation_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'taxation_div' must be String: '"+value+"' is not." );
                    this.taxation_div = (String) value;
                    if (this.taxation_div_is_set)
                        this.taxation_div_is_modified = true;
                    this.taxation_div_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("unit_price_scale") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'unit_price_scale' must be Integer: '"+value+"' is not." );
                    this.unit_price_scale = (Integer) value;
                    if (this.unit_price_scale_is_set)
                        this.unit_price_scale_is_modified = true;
                    this.unit_price_scale_is_set = true;
                    return;
                }
                else if ( name.equals("unit_round_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'unit_round_div' must be String: '"+value+"' is not." );
                    this.unit_round_div = (String) value;
                    if (this.unit_round_div_is_set)
                        this.unit_round_div_is_modified = true;
                    this.unit_round_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
