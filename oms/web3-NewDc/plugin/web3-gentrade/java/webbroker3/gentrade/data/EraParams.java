head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.36.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EraParams.java;


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
 * era�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link EraRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link EraParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link EraParams}��{@@link EraRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EraPK 
 * @@see EraRow 
 */
public class EraParams extends Params implements EraRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "era";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = EraRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return EraRow.TYPE;
  }


  /** 
   * <em>japanese_era_div</em>�J�����̒l 
   */
  public  int  japanese_era_div;

  /** 
   * <em>japanese_era</em>�J�����̒l 
   */
  public  String  japanese_era;

  /** 
   * <em>start_year_jap</em>�J�����̒l 
   */
  public  String  start_year_jap;

  /** 
   * <em>start_year</em>�J�����̒l 
   */
  public  String  start_year;

  /** 
   * <em>start_date</em>�J�����̒l 
   */
  public  String  start_date;

  /** 
   * <em>end_year_jap</em>�J�����̒l 
   */
  public  String  end_year_jap;

  /** 
   * <em>end_year</em>�J�����̒l 
   */
  public  String  end_year;

  /** 
   * <em>end_date</em>�J�����̒l 
   */
  public  String  end_date;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean japanese_era_div_is_set = false;
  private boolean japanese_era_div_is_modified = false;
  private boolean japanese_era_is_set = false;
  private boolean japanese_era_is_modified = false;
  private boolean start_year_jap_is_set = false;
  private boolean start_year_jap_is_modified = false;
  private boolean start_year_is_set = false;
  private boolean start_year_is_modified = false;
  private boolean start_date_is_set = false;
  private boolean start_date_is_modified = false;
  private boolean end_year_jap_is_set = false;
  private boolean end_year_jap_is_modified = false;
  private boolean end_year_is_set = false;
  private boolean end_year_is_modified = false;
  private boolean end_date_is_set = false;
  private boolean end_date_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "japanese_era_div=" + japanese_era_div
      + "," + "japanese_era=" +japanese_era
      + "," + "start_year_jap=" +start_year_jap
      + "," + "start_year=" +start_year
      + "," + "start_date=" +start_date
      + "," + "end_year_jap=" +end_year_jap
      + "," + "end_year=" +end_year
      + "," + "end_date=" +end_date
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��EraParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public EraParams() {
    japanese_era_div_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���EraRow�I�u�W�F�N�g�̒l�𗘗p����EraParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����EraRow�I�u�W�F�N�g 
   */
  public EraParams( EraRow p_row )
  {
    japanese_era_div = p_row.getJapaneseEraDiv();
    japanese_era_div_is_set = p_row.getJapaneseEraDivIsSet();
    japanese_era_div_is_modified = p_row.getJapaneseEraDivIsModified();
    japanese_era = p_row.getJapaneseEra();
    japanese_era_is_set = p_row.getJapaneseEraIsSet();
    japanese_era_is_modified = p_row.getJapaneseEraIsModified();
    start_year_jap = p_row.getStartYearJap();
    start_year_jap_is_set = p_row.getStartYearJapIsSet();
    start_year_jap_is_modified = p_row.getStartYearJapIsModified();
    start_year = p_row.getStartYear();
    start_year_is_set = p_row.getStartYearIsSet();
    start_year_is_modified = p_row.getStartYearIsModified();
    start_date = p_row.getStartDate();
    start_date_is_set = p_row.getStartDateIsSet();
    start_date_is_modified = p_row.getStartDateIsModified();
    end_year_jap = p_row.getEndYearJap();
    end_year_jap_is_set = p_row.getEndYearJapIsSet();
    end_year_jap_is_modified = p_row.getEndYearJapIsModified();
    end_year = p_row.getEndYear();
    end_year_is_set = p_row.getEndYearIsSet();
    end_year_is_modified = p_row.getEndYearIsModified();
    end_date = p_row.getEndDate();
    end_date_is_set = p_row.getEndDateIsSet();
    end_date_is_modified = p_row.getEndDateIsModified();
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
      japanese_era_is_set = true;
      japanese_era_is_modified = true;
      start_year_jap_is_set = true;
      start_year_jap_is_modified = true;
      start_year_is_set = true;
      start_year_is_modified = true;
      start_date_is_set = true;
      start_date_is_modified = true;
      end_year_jap_is_set = true;
      end_year_jap_is_modified = true;
      end_year_is_set = true;
      end_year_is_modified = true;
      end_date_is_set = true;
      end_date_is_modified = true;
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
    if ( !( other instanceof EraRow ) )
       return false;
    return fieldsEqual( (EraRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�EraRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( EraRow row )
  {
    if ( japanese_era_div != row.getJapaneseEraDiv() )
      return false;
    if ( japanese_era == null ) {
      if ( row.getJapaneseEra() != null )
        return false;
    } else if ( !japanese_era.equals( row.getJapaneseEra() ) ) {
        return false;
    }
    if ( start_year_jap == null ) {
      if ( row.getStartYearJap() != null )
        return false;
    } else if ( !start_year_jap.equals( row.getStartYearJap() ) ) {
        return false;
    }
    if ( start_year == null ) {
      if ( row.getStartYear() != null )
        return false;
    } else if ( !start_year.equals( row.getStartYear() ) ) {
        return false;
    }
    if ( start_date == null ) {
      if ( row.getStartDate() != null )
        return false;
    } else if ( !start_date.equals( row.getStartDate() ) ) {
        return false;
    }
    if ( end_year_jap == null ) {
      if ( row.getEndYearJap() != null )
        return false;
    } else if ( !end_year_jap.equals( row.getEndYearJap() ) ) {
        return false;
    }
    if ( end_year == null ) {
      if ( row.getEndYear() != null )
        return false;
    } else if ( !end_year.equals( row.getEndYear() ) ) {
        return false;
    }
    if ( end_date == null ) {
      if ( row.getEndDate() != null )
        return false;
    } else if ( !end_date.equals( row.getEndDate() ) ) {
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
      return  ((int) japanese_era_div)
        + (japanese_era!=null? japanese_era.hashCode(): 0) 
        + (start_year_jap!=null? start_year_jap.hashCode(): 0) 
        + (start_year!=null? start_year.hashCode(): 0) 
        + (start_date!=null? start_date.hashCode(): 0) 
        + (end_year_jap!=null? end_year_jap.hashCode(): 0) 
        + (end_year!=null? end_year.hashCode(): 0) 
        + (end_date!=null? end_date.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !japanese_era_is_set )
			throw new IllegalArgumentException("non-nullable field 'japanese_era' must be set before inserting.");
		if ( !start_year_jap_is_set )
			throw new IllegalArgumentException("non-nullable field 'start_year_jap' must be set before inserting.");
		if ( !start_year_is_set )
			throw new IllegalArgumentException("non-nullable field 'start_year' must be set before inserting.");
		if ( !start_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'start_date' must be set before inserting.");
		if ( !end_year_jap_is_set )
			throw new IllegalArgumentException("non-nullable field 'end_year_jap' must be set before inserting.");
		if ( !end_year_is_set )
			throw new IllegalArgumentException("non-nullable field 'end_year' must be set before inserting.");
		if ( !end_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'end_date' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("japanese_era_div",new Integer(japanese_era_div));
		map.put("japanese_era",japanese_era);
		map.put("start_year_jap",start_year_jap);
		map.put("start_year",start_year);
		map.put("start_date",start_date);
		map.put("end_year_jap",end_year_jap);
		map.put("end_year",end_year);
		map.put("end_date",end_date);
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
		if ( japanese_era_is_modified )
			map.put("japanese_era",japanese_era);
		if ( start_year_jap_is_modified )
			map.put("start_year_jap",start_year_jap);
		if ( start_year_is_modified )
			map.put("start_year",start_year);
		if ( start_date_is_modified )
			map.put("start_date",start_date);
		if ( end_year_jap_is_modified )
			map.put("end_year_jap",end_year_jap);
		if ( end_year_is_modified )
			map.put("end_year",end_year);
		if ( end_date_is_modified )
			map.put("end_date",end_date);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( japanese_era_is_set )
				map.put("japanese_era",japanese_era);
			if ( start_year_jap_is_set )
				map.put("start_year_jap",start_year_jap);
			if ( start_year_is_set )
				map.put("start_year",start_year);
			if ( start_date_is_set )
				map.put("start_date",start_date);
			if ( end_year_jap_is_set )
				map.put("end_year_jap",end_year_jap);
			if ( end_year_is_set )
				map.put("end_year",end_year);
			if ( end_date_is_set )
				map.put("end_date",end_date);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>japanese_era_div</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getJapaneseEraDiv()
  {
    return japanese_era_div;
  }


  /** 
   * <em>japanese_era_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getJapaneseEraDivIsSet() {
    return japanese_era_div_is_set;
  }


  /** 
   * <em>japanese_era_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getJapaneseEraDivIsModified() {
    return japanese_era_div_is_modified;
  }


  /** 
   * <em>japanese_era</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getJapaneseEra()
  {
    return japanese_era;
  }


  /** 
   * <em>japanese_era</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getJapaneseEraIsSet() {
    return japanese_era_is_set;
  }


  /** 
   * <em>japanese_era</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getJapaneseEraIsModified() {
    return japanese_era_is_modified;
  }


  /** 
   * <em>start_year_jap</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStartYearJap()
  {
    return start_year_jap;
  }


  /** 
   * <em>start_year_jap</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStartYearJapIsSet() {
    return start_year_jap_is_set;
  }


  /** 
   * <em>start_year_jap</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStartYearJapIsModified() {
    return start_year_jap_is_modified;
  }


  /** 
   * <em>start_year</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStartYear()
  {
    return start_year;
  }


  /** 
   * <em>start_year</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStartYearIsSet() {
    return start_year_is_set;
  }


  /** 
   * <em>start_year</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStartYearIsModified() {
    return start_year_is_modified;
  }


  /** 
   * <em>start_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStartDate()
  {
    return start_date;
  }


  /** 
   * <em>start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStartDateIsSet() {
    return start_date_is_set;
  }


  /** 
   * <em>start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStartDateIsModified() {
    return start_date_is_modified;
  }


  /** 
   * <em>end_year_jap</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getEndYearJap()
  {
    return end_year_jap;
  }


  /** 
   * <em>end_year_jap</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndYearJapIsSet() {
    return end_year_jap_is_set;
  }


  /** 
   * <em>end_year_jap</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndYearJapIsModified() {
    return end_year_jap_is_modified;
  }


  /** 
   * <em>end_year</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getEndYear()
  {
    return end_year;
  }


  /** 
   * <em>end_year</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndYearIsSet() {
    return end_year_is_set;
  }


  /** 
   * <em>end_year</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndYearIsModified() {
    return end_year_is_modified;
  }


  /** 
   * <em>end_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getEndDate()
  {
    return end_date;
  }


  /** 
   * <em>end_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndDateIsSet() {
    return end_date_is_set;
  }


  /** 
   * <em>end_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndDateIsModified() {
    return end_date_is_modified;
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
    return new EraPK(japanese_era_div);
  }


  /** 
   * <em>japanese_era_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_japaneseEraDiv <em>japanese_era_div</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setJapaneseEraDiv( int p_japaneseEraDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    japanese_era_div = p_japaneseEraDiv;
    japanese_era_div_is_set = true;
    japanese_era_div_is_modified = true;
  }


  /** 
   * <em>japanese_era</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_japaneseEra <em>japanese_era</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setJapaneseEra( String p_japaneseEra )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    japanese_era = p_japaneseEra;
    japanese_era_is_set = true;
    japanese_era_is_modified = true;
  }


  /** 
   * <em>start_year_jap</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_startYearJap <em>start_year_jap</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setStartYearJap( String p_startYearJap )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    start_year_jap = p_startYearJap;
    start_year_jap_is_set = true;
    start_year_jap_is_modified = true;
  }


  /** 
   * <em>start_year</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_startYear <em>start_year</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setStartYear( String p_startYear )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    start_year = p_startYear;
    start_year_is_set = true;
    start_year_is_modified = true;
  }


  /** 
   * <em>start_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_startDate <em>start_date</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setStartDate( String p_startDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    start_date = p_startDate;
    start_date_is_set = true;
    start_date_is_modified = true;
  }


  /** 
   * <em>end_year_jap</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_endYearJap <em>end_year_jap</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setEndYearJap( String p_endYearJap )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    end_year_jap = p_endYearJap;
    end_year_jap_is_set = true;
    end_year_jap_is_modified = true;
  }


  /** 
   * <em>end_year</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_endYear <em>end_year</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setEndYear( String p_endYear )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    end_year = p_endYear;
    end_year_is_set = true;
    end_year_is_modified = true;
  }


  /** 
   * <em>end_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_endDate <em>end_date</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setEndDate( String p_endDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    end_date = p_endDate;
    end_date_is_set = true;
    end_date_is_modified = true;
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
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("end_year_jap") ) {
                    return this.end_year_jap;
                }
                else if ( name.equals("end_year") ) {
                    return this.end_year;
                }
                else if ( name.equals("end_date") ) {
                    return this.end_date;
                }
                break;
            case 'j':
                if ( name.equals("japanese_era_div") ) {
                    return new Integer( japanese_era_div );
                }
                else if ( name.equals("japanese_era") ) {
                    return this.japanese_era;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 's':
                if ( name.equals("start_year_jap") ) {
                    return this.start_year_jap;
                }
                else if ( name.equals("start_year") ) {
                    return this.start_year;
                }
                else if ( name.equals("start_date") ) {
                    return this.start_date;
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
                if ( name.equals("end_year_jap") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'end_year_jap' must be String: '"+value+"' is not." );
                    this.end_year_jap = (String) value;
                    if (this.end_year_jap_is_set)
                        this.end_year_jap_is_modified = true;
                    this.end_year_jap_is_set = true;
                    return;
                }
                else if ( name.equals("end_year") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'end_year' must be String: '"+value+"' is not." );
                    this.end_year = (String) value;
                    if (this.end_year_is_set)
                        this.end_year_is_modified = true;
                    this.end_year_is_set = true;
                    return;
                }
                else if ( name.equals("end_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'end_date' must be String: '"+value+"' is not." );
                    this.end_date = (String) value;
                    if (this.end_date_is_set)
                        this.end_date_is_modified = true;
                    this.end_date_is_set = true;
                    return;
                }
                break;
            case 'j':
                if ( name.equals("japanese_era_div") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'japanese_era_div' must be Integer: '"+value+"' is not." );
                    this.japanese_era_div = ((Integer) value).intValue();
                    if (this.japanese_era_div_is_set)
                        this.japanese_era_div_is_modified = true;
                    this.japanese_era_div_is_set = true;
                    return;
                }
                else if ( name.equals("japanese_era") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'japanese_era' must be String: '"+value+"' is not." );
                    this.japanese_era = (String) value;
                    if (this.japanese_era_is_set)
                        this.japanese_era_is_modified = true;
                    this.japanese_era_is_set = true;
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
            case 's':
                if ( name.equals("start_year_jap") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'start_year_jap' must be String: '"+value+"' is not." );
                    this.start_year_jap = (String) value;
                    if (this.start_year_jap_is_set)
                        this.start_year_jap_is_modified = true;
                    this.start_year_jap_is_set = true;
                    return;
                }
                else if ( name.equals("start_year") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'start_year' must be String: '"+value+"' is not." );
                    this.start_year = (String) value;
                    if (this.start_year_is_set)
                        this.start_year_is_modified = true;
                    this.start_year_is_set = true;
                    return;
                }
                else if ( name.equals("start_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'start_date' must be String: '"+value+"' is not." );
                    this.start_date = (String) value;
                    if (this.start_date_is_set)
                        this.start_date_is_modified = true;
                    this.start_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
