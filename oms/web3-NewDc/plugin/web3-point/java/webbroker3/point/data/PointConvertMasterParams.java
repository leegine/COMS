head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.49.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointConvertMasterParams.java;


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
 * point_convert_master�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link PointConvertMasterRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link PointConvertMasterParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link PointConvertMasterParams}��{@@link PointConvertMasterRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PointConvertMasterPK 
 * @@see PointConvertMasterRow 
 */
public class PointConvertMasterParams extends Params implements PointConvertMasterRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "point_convert_master";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = PointConvertMasterRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return PointConvertMasterRow.TYPE;
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
   * <em>fund_type</em>�J�����̒l 
   */
  public  String  fund_type;

  /** 
   * <em>tran_type</em>�J�����̒l 
   */
  public  String  tran_type;

  /** 
   * <em>buy_sell_div</em>�J�����̒l 
   */
  public  String  buy_sell_div;

  /** 
   * <em>convert_base</em>�J�����̒l 
   */
  public  Double  convert_base;

  /** 
   * <em>convert_value</em>�J�����̒l 
   */
  public  Double  convert_value;

  /** 
   * <em>special_term_start</em>�J�����̒l 
   */
  public  java.sql.Timestamp  special_term_start;

  /** 
   * <em>special_term_end</em>�J�����̒l 
   */
  public  java.sql.Timestamp  special_term_end;

  /** 
   * <em>special_convert_base</em>�J�����̒l 
   */
  public  Double  special_convert_base;

  /** 
   * <em>special_convert_value</em>�J�����̒l 
   */
  public  Double  special_convert_value;

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
  private boolean fund_type_is_set = false;
  private boolean fund_type_is_modified = false;
  private boolean tran_type_is_set = false;
  private boolean tran_type_is_modified = false;
  private boolean buy_sell_div_is_set = false;
  private boolean buy_sell_div_is_modified = false;
  private boolean convert_base_is_set = false;
  private boolean convert_base_is_modified = false;
  private boolean convert_value_is_set = false;
  private boolean convert_value_is_modified = false;
  private boolean special_term_start_is_set = false;
  private boolean special_term_start_is_modified = false;
  private boolean special_term_end_is_set = false;
  private boolean special_term_end_is_modified = false;
  private boolean special_convert_base_is_set = false;
  private boolean special_convert_base_is_modified = false;
  private boolean special_convert_value_is_set = false;
  private boolean special_convert_value_is_modified = false;
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
      + "," + "fund_type=" + fund_type
      + "," + "tran_type=" + tran_type
      + "," + "buy_sell_div=" + buy_sell_div
      + "," + "convert_base=" +convert_base
      + "," + "convert_value=" +convert_value
      + "," + "special_term_start=" +special_term_start
      + "," + "special_term_end=" +special_term_end
      + "," + "special_convert_base=" +special_convert_base
      + "," + "special_convert_value=" +special_convert_value
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��PointConvertMasterParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public PointConvertMasterParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    fund_type_is_modified = true;
    tran_type_is_modified = true;
    buy_sell_div_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���PointConvertMasterRow�I�u�W�F�N�g�̒l�𗘗p����PointConvertMasterParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����PointConvertMasterRow�I�u�W�F�N�g 
   */
  public PointConvertMasterParams( PointConvertMasterRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    fund_type = p_row.getFundType();
    fund_type_is_set = p_row.getFundTypeIsSet();
    fund_type_is_modified = p_row.getFundTypeIsModified();
    tran_type = p_row.getTranType();
    tran_type_is_set = p_row.getTranTypeIsSet();
    tran_type_is_modified = p_row.getTranTypeIsModified();
    buy_sell_div = p_row.getBuySellDiv();
    buy_sell_div_is_set = p_row.getBuySellDivIsSet();
    buy_sell_div_is_modified = p_row.getBuySellDivIsModified();
    if ( !p_row.getConvertBaseIsNull() )
      convert_base = new Double( p_row.getConvertBase() );
    convert_base_is_set = p_row.getConvertBaseIsSet();
    convert_base_is_modified = p_row.getConvertBaseIsModified();
    if ( !p_row.getConvertValueIsNull() )
      convert_value = new Double( p_row.getConvertValue() );
    convert_value_is_set = p_row.getConvertValueIsSet();
    convert_value_is_modified = p_row.getConvertValueIsModified();
    special_term_start = p_row.getSpecialTermStart();
    special_term_start_is_set = p_row.getSpecialTermStartIsSet();
    special_term_start_is_modified = p_row.getSpecialTermStartIsModified();
    special_term_end = p_row.getSpecialTermEnd();
    special_term_end_is_set = p_row.getSpecialTermEndIsSet();
    special_term_end_is_modified = p_row.getSpecialTermEndIsModified();
    if ( !p_row.getSpecialConvertBaseIsNull() )
      special_convert_base = new Double( p_row.getSpecialConvertBase() );
    special_convert_base_is_set = p_row.getSpecialConvertBaseIsSet();
    special_convert_base_is_modified = p_row.getSpecialConvertBaseIsModified();
    if ( !p_row.getSpecialConvertValueIsNull() )
      special_convert_value = new Double( p_row.getSpecialConvertValue() );
    special_convert_value_is_set = p_row.getSpecialConvertValueIsSet();
    special_convert_value_is_modified = p_row.getSpecialConvertValueIsModified();
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
      convert_base_is_set = true;
      convert_base_is_modified = true;
      convert_value_is_set = true;
      convert_value_is_modified = true;
      special_term_start_is_set = true;
      special_term_start_is_modified = true;
      special_term_end_is_set = true;
      special_term_end_is_modified = true;
      special_convert_base_is_set = true;
      special_convert_base_is_modified = true;
      special_convert_value_is_set = true;
      special_convert_value_is_modified = true;
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
    if ( !( other instanceof PointConvertMasterRow ) )
       return false;
    return fieldsEqual( (PointConvertMasterRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�PointConvertMasterRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( PointConvertMasterRow row )
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
    if ( fund_type == null ) {
      if ( row.getFundType() != null )
        return false;
    } else if ( !fund_type.equals( row.getFundType() ) ) {
        return false;
    }
    if ( tran_type == null ) {
      if ( row.getTranType() != null )
        return false;
    } else if ( !tran_type.equals( row.getTranType() ) ) {
        return false;
    }
    if ( buy_sell_div == null ) {
      if ( row.getBuySellDiv() != null )
        return false;
    } else if ( !buy_sell_div.equals( row.getBuySellDiv() ) ) {
        return false;
    }
    if ( convert_base == null ) {
      if ( !row.getConvertBaseIsNull() )
        return false;
    } else if ( row.getConvertBaseIsNull() || ( convert_base.doubleValue() != row.getConvertBase() ) ) {
        return false;
    }
    if ( convert_value == null ) {
      if ( !row.getConvertValueIsNull() )
        return false;
    } else if ( row.getConvertValueIsNull() || ( convert_value.doubleValue() != row.getConvertValue() ) ) {
        return false;
    }
    if ( special_term_start == null ) {
      if ( row.getSpecialTermStart() != null )
        return false;
    } else if ( !special_term_start.equals( row.getSpecialTermStart() ) ) {
        return false;
    }
    if ( special_term_end == null ) {
      if ( row.getSpecialTermEnd() != null )
        return false;
    } else if ( !special_term_end.equals( row.getSpecialTermEnd() ) ) {
        return false;
    }
    if ( special_convert_base == null ) {
      if ( !row.getSpecialConvertBaseIsNull() )
        return false;
    } else if ( row.getSpecialConvertBaseIsNull() || ( special_convert_base.doubleValue() != row.getSpecialConvertBase() ) ) {
        return false;
    }
    if ( special_convert_value == null ) {
      if ( !row.getSpecialConvertValueIsNull() )
        return false;
    } else if ( row.getSpecialConvertValueIsNull() || ( special_convert_value.doubleValue() != row.getSpecialConvertValue() ) ) {
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
        + (fund_type!=null? fund_type.hashCode(): 0) 
        + (tran_type!=null? tran_type.hashCode(): 0) 
        + (buy_sell_div!=null? buy_sell_div.hashCode(): 0) 
        + (convert_base!=null? convert_base.hashCode(): 0) 
        + (convert_value!=null? convert_value.hashCode(): 0) 
        + (special_term_start!=null? special_term_start.hashCode(): 0) 
        + (special_term_end!=null? special_term_end.hashCode(): 0) 
        + (special_convert_base!=null? special_convert_base.hashCode(): 0) 
        + (special_convert_value!=null? special_convert_value.hashCode(): 0) 
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
		map.put("fund_type",fund_type);
		map.put("tran_type",tran_type);
		map.put("buy_sell_div",buy_sell_div);
		if ( convert_base != null )
			map.put("convert_base",convert_base);
		if ( convert_value != null )
			map.put("convert_value",convert_value);
		if ( special_term_start != null )
			map.put("special_term_start",special_term_start);
		if ( special_term_end != null )
			map.put("special_term_end",special_term_end);
		if ( special_convert_base != null )
			map.put("special_convert_base",special_convert_base);
		if ( special_convert_value != null )
			map.put("special_convert_value",special_convert_value);
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
		if ( convert_base_is_modified )
			map.put("convert_base",convert_base);
		if ( convert_value_is_modified )
			map.put("convert_value",convert_value);
		if ( special_term_start_is_modified )
			map.put("special_term_start",special_term_start);
		if ( special_term_end_is_modified )
			map.put("special_term_end",special_term_end);
		if ( special_convert_base_is_modified )
			map.put("special_convert_base",special_convert_base);
		if ( special_convert_value_is_modified )
			map.put("special_convert_value",special_convert_value);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("convert_base",convert_base);
			map.put("convert_value",convert_value);
			map.put("special_term_start",special_term_start);
			map.put("special_term_end",special_term_end);
			map.put("special_convert_base",special_convert_base);
			map.put("special_convert_value",special_convert_value);
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
   * <em>fund_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFundType()
  {
    return fund_type;
  }


  /** 
   * <em>fund_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFundTypeIsSet() {
    return fund_type_is_set;
  }


  /** 
   * <em>fund_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFundTypeIsModified() {
    return fund_type_is_modified;
  }


  /** 
   * <em>tran_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTranType()
  {
    return tran_type;
  }


  /** 
   * <em>tran_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTranTypeIsSet() {
    return tran_type_is_set;
  }


  /** 
   * <em>tran_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTranTypeIsModified() {
    return tran_type_is_modified;
  }


  /** 
   * <em>buy_sell_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBuySellDiv()
  {
    return buy_sell_div;
  }


  /** 
   * <em>buy_sell_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuySellDivIsSet() {
    return buy_sell_div_is_set;
  }


  /** 
   * <em>buy_sell_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuySellDivIsModified() {
    return buy_sell_div_is_modified;
  }


  /** 
   * <em>convert_base</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getConvertBase()
  {
    return ( convert_base==null? ((double)0): convert_base.doubleValue() );
  }


  /** 
   * <em>convert_base</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getConvertBaseIsNull()
  {
    return convert_base == null;
  }


  /** 
   * <em>convert_base</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConvertBaseIsSet() {
    return convert_base_is_set;
  }


  /** 
   * <em>convert_base</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConvertBaseIsModified() {
    return convert_base_is_modified;
  }


  /** 
   * <em>convert_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getConvertValue()
  {
    return ( convert_value==null? ((double)0): convert_value.doubleValue() );
  }


  /** 
   * <em>convert_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getConvertValueIsNull()
  {
    return convert_value == null;
  }


  /** 
   * <em>convert_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConvertValueIsSet() {
    return convert_value_is_set;
  }


  /** 
   * <em>convert_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConvertValueIsModified() {
    return convert_value_is_modified;
  }


  /** 
   * <em>special_term_start</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getSpecialTermStart()
  {
    return special_term_start;
  }


  /** 
   * <em>special_term_start</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecialTermStartIsSet() {
    return special_term_start_is_set;
  }


  /** 
   * <em>special_term_start</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecialTermStartIsModified() {
    return special_term_start_is_modified;
  }


  /** 
   * <em>special_term_end</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getSpecialTermEnd()
  {
    return special_term_end;
  }


  /** 
   * <em>special_term_end</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecialTermEndIsSet() {
    return special_term_end_is_set;
  }


  /** 
   * <em>special_term_end</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecialTermEndIsModified() {
    return special_term_end_is_modified;
  }


  /** 
   * <em>special_convert_base</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getSpecialConvertBase()
  {
    return ( special_convert_base==null? ((double)0): special_convert_base.doubleValue() );
  }


  /** 
   * <em>special_convert_base</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSpecialConvertBaseIsNull()
  {
    return special_convert_base == null;
  }


  /** 
   * <em>special_convert_base</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecialConvertBaseIsSet() {
    return special_convert_base_is_set;
  }


  /** 
   * <em>special_convert_base</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecialConvertBaseIsModified() {
    return special_convert_base_is_modified;
  }


  /** 
   * <em>special_convert_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getSpecialConvertValue()
  {
    return ( special_convert_value==null? ((double)0): special_convert_value.doubleValue() );
  }


  /** 
   * <em>special_convert_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSpecialConvertValueIsNull()
  {
    return special_convert_value == null;
  }


  /** 
   * <em>special_convert_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecialConvertValueIsSet() {
    return special_convert_value_is_set;
  }


  /** 
   * <em>special_convert_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecialConvertValueIsModified() {
    return special_convert_value_is_modified;
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
    return new PointConvertMasterPK(institution_code, branch_code, fund_type, tran_type, buy_sell_div);
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
   * <em>fund_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_fundType <em>fund_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setFundType( String p_fundType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_type = p_fundType;
    fund_type_is_set = true;
    fund_type_is_modified = true;
  }


  /** 
   * <em>tran_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tranType <em>tran_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setTranType( String p_tranType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tran_type = p_tranType;
    tran_type_is_set = true;
    tran_type_is_modified = true;
  }


  /** 
   * <em>buy_sell_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_buySellDiv <em>buy_sell_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setBuySellDiv( String p_buySellDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_sell_div = p_buySellDiv;
    buy_sell_div_is_set = true;
    buy_sell_div_is_modified = true;
  }


  /** 
   * <em>convert_base</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_convertBase <em>convert_base</em>�J�����̒l������킷7���ȉ���double�l�ŁA���̐��x��2���܂�
   */
  public final void setConvertBase( double p_convertBase )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    convert_base = new Double(p_convertBase);
    convert_base_is_set = true;
    convert_base_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>convert_base</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setConvertBase( Double p_convertBase )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    convert_base = p_convertBase;
    convert_base_is_set = true;
    convert_base_is_modified = true;
  }


  /** 
   * <em>convert_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_convertValue <em>convert_value</em>�J�����̒l������킷7���ȉ���double�l�ŁA���̐��x��2���܂�
   */
  public final void setConvertValue( double p_convertValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    convert_value = new Double(p_convertValue);
    convert_value_is_set = true;
    convert_value_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>convert_value</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setConvertValue( Double p_convertValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    convert_value = p_convertValue;
    convert_value_is_set = true;
    convert_value_is_modified = true;
  }


  /** 
   * <em>special_term_start</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_specialTermStart <em>special_term_start</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setSpecialTermStart( java.sql.Timestamp p_specialTermStart )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_term_start = p_specialTermStart;
    special_term_start_is_set = true;
    special_term_start_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>special_term_start</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setSpecialTermStart( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    special_term_start = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    special_term_start_is_set = true;
    special_term_start_is_modified = true;
  }


  /** 
   * <em>special_term_end</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_specialTermEnd <em>special_term_end</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setSpecialTermEnd( java.sql.Timestamp p_specialTermEnd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_term_end = p_specialTermEnd;
    special_term_end_is_set = true;
    special_term_end_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>special_term_end</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setSpecialTermEnd( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    special_term_end = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    special_term_end_is_set = true;
    special_term_end_is_modified = true;
  }


  /** 
   * <em>special_convert_base</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_specialConvertBase <em>special_convert_base</em>�J�����̒l������킷7���ȉ���double�l�ŁA���̐��x��2���܂�
   */
  public final void setSpecialConvertBase( double p_specialConvertBase )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_convert_base = new Double(p_specialConvertBase);
    special_convert_base_is_set = true;
    special_convert_base_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>special_convert_base</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSpecialConvertBase( Double p_specialConvertBase )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    special_convert_base = p_specialConvertBase;
    special_convert_base_is_set = true;
    special_convert_base_is_modified = true;
  }


  /** 
   * <em>special_convert_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_specialConvertValue <em>special_convert_value</em>�J�����̒l������킷7���ȉ���double�l�ŁA���̐��x��2���܂�
   */
  public final void setSpecialConvertValue( double p_specialConvertValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_convert_value = new Double(p_specialConvertValue);
    special_convert_value_is_set = true;
    special_convert_value_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>special_convert_value</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSpecialConvertValue( Double p_specialConvertValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    special_convert_value = p_specialConvertValue;
    special_convert_value_is_set = true;
    special_convert_value_is_modified = true;
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
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("buy_sell_div") ) {
                    return this.buy_sell_div;
                }
                break;
            case 'c':
                if ( name.equals("convert_base") ) {
                    return this.convert_base;
                }
                else if ( name.equals("convert_value") ) {
                    return this.convert_value;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("fund_type") ) {
                    return this.fund_type;
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
                if ( name.equals("special_term_start") ) {
                    return this.special_term_start;
                }
                else if ( name.equals("special_term_end") ) {
                    return this.special_term_end;
                }
                else if ( name.equals("special_convert_base") ) {
                    return this.special_convert_base;
                }
                else if ( name.equals("special_convert_value") ) {
                    return this.special_convert_value;
                }
                break;
            case 't':
                if ( name.equals("tran_type") ) {
                    return this.tran_type;
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
                else if ( name.equals("buy_sell_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_sell_div' must be String: '"+value+"' is not." );
                    this.buy_sell_div = (String) value;
                    if (this.buy_sell_div_is_set)
                        this.buy_sell_div_is_modified = true;
                    this.buy_sell_div_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("convert_base") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'convert_base' must be Double: '"+value+"' is not." );
                    this.convert_base = (Double) value;
                    if (this.convert_base_is_set)
                        this.convert_base_is_modified = true;
                    this.convert_base_is_set = true;
                    return;
                }
                else if ( name.equals("convert_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'convert_value' must be Double: '"+value+"' is not." );
                    this.convert_value = (Double) value;
                    if (this.convert_value_is_set)
                        this.convert_value_is_modified = true;
                    this.convert_value_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
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
            case 'f':
                if ( name.equals("fund_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_type' must be String: '"+value+"' is not." );
                    this.fund_type = (String) value;
                    if (this.fund_type_is_set)
                        this.fund_type_is_modified = true;
                    this.fund_type_is_set = true;
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
            case 's':
                if ( name.equals("special_term_start") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'special_term_start' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.special_term_start = (java.sql.Timestamp) value;
                    if (this.special_term_start_is_set)
                        this.special_term_start_is_modified = true;
                    this.special_term_start_is_set = true;
                    return;
                }
                else if ( name.equals("special_term_end") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'special_term_end' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.special_term_end = (java.sql.Timestamp) value;
                    if (this.special_term_end_is_set)
                        this.special_term_end_is_modified = true;
                    this.special_term_end_is_set = true;
                    return;
                }
                else if ( name.equals("special_convert_base") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'special_convert_base' must be Double: '"+value+"' is not." );
                    this.special_convert_base = (Double) value;
                    if (this.special_convert_base_is_set)
                        this.special_convert_base_is_modified = true;
                    this.special_convert_base_is_set = true;
                    return;
                }
                else if ( name.equals("special_convert_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'special_convert_value' must be Double: '"+value+"' is not." );
                    this.special_convert_value = (Double) value;
                    if (this.special_convert_value_is_set)
                        this.special_convert_value_is_modified = true;
                    this.special_convert_value_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tran_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tran_type' must be String: '"+value+"' is not." );
                    this.tran_type = (String) value;
                    if (this.tran_type_is_set)
                        this.tran_type_is_modified = true;
                    this.tran_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
