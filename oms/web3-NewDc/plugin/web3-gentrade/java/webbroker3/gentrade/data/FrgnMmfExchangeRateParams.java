head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.28.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FrgnMmfExchangeRateParams.java;


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
 * frgn_mmf_exchange_rate�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link FrgnMmfExchangeRateRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link FrgnMmfExchangeRateParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link FrgnMmfExchangeRateParams}��{@@link FrgnMmfExchangeRateRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FrgnMmfExchangeRatePK 
 * @@see FrgnMmfExchangeRateRow 
 */
public class FrgnMmfExchangeRateParams extends Params implements FrgnMmfExchangeRateRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "frgn_mmf_exchange_rate";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = FrgnMmfExchangeRateRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return FrgnMmfExchangeRateRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>currency_code</em>�J�����̒l 
   */
  public  String  currency_code;

  /** 
   * <em>exec_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  exec_timestamp;

  /** 
   * <em>tt_selling_rate</em>�J�����̒l 
   */
  public  Double  tt_selling_rate;

  /** 
   * <em>tt_buying_rate</em>�J�����̒l 
   */
  public  Double  tt_buying_rate;

  /** 
   * <em>sub_currency_ratio</em>�J�����̒l 
   */
  public  Integer  sub_currency_ratio;

  /** 
   * <em>restrict_rate</em>�J�����̒l 
   */
  public  Double  restrict_rate;

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
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean exec_timestamp_is_set = false;
  private boolean exec_timestamp_is_modified = false;
  private boolean tt_selling_rate_is_set = false;
  private boolean tt_selling_rate_is_modified = false;
  private boolean tt_buying_rate_is_set = false;
  private boolean tt_buying_rate_is_modified = false;
  private boolean sub_currency_ratio_is_set = false;
  private boolean sub_currency_ratio_is_modified = false;
  private boolean restrict_rate_is_set = false;
  private boolean restrict_rate_is_modified = false;
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
      + "," + "currency_code=" + currency_code
      + "," + "exec_timestamp=" +exec_timestamp
      + "," + "tt_selling_rate=" +tt_selling_rate
      + "," + "tt_buying_rate=" +tt_buying_rate
      + "," + "sub_currency_ratio=" +sub_currency_ratio
      + "," + "restrict_rate=" +restrict_rate
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��FrgnMmfExchangeRateParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public FrgnMmfExchangeRateParams() {
    institution_code_is_modified = true;
    currency_code_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���FrgnMmfExchangeRateRow�I�u�W�F�N�g�̒l�𗘗p����FrgnMmfExchangeRateParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����FrgnMmfExchangeRateRow�I�u�W�F�N�g 
   */
  public FrgnMmfExchangeRateParams( FrgnMmfExchangeRateRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    exec_timestamp = p_row.getExecTimestamp();
    exec_timestamp_is_set = p_row.getExecTimestampIsSet();
    exec_timestamp_is_modified = p_row.getExecTimestampIsModified();
    if ( !p_row.getTtSellingRateIsNull() )
      tt_selling_rate = new Double( p_row.getTtSellingRate() );
    tt_selling_rate_is_set = p_row.getTtSellingRateIsSet();
    tt_selling_rate_is_modified = p_row.getTtSellingRateIsModified();
    if ( !p_row.getTtBuyingRateIsNull() )
      tt_buying_rate = new Double( p_row.getTtBuyingRate() );
    tt_buying_rate_is_set = p_row.getTtBuyingRateIsSet();
    tt_buying_rate_is_modified = p_row.getTtBuyingRateIsModified();
    if ( !p_row.getSubCurrencyRatioIsNull() )
      sub_currency_ratio = new Integer( p_row.getSubCurrencyRatio() );
    sub_currency_ratio_is_set = p_row.getSubCurrencyRatioIsSet();
    sub_currency_ratio_is_modified = p_row.getSubCurrencyRatioIsModified();
    if ( !p_row.getRestrictRateIsNull() )
      restrict_rate = new Double( p_row.getRestrictRate() );
    restrict_rate_is_set = p_row.getRestrictRateIsSet();
    restrict_rate_is_modified = p_row.getRestrictRateIsModified();
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
      exec_timestamp_is_set = true;
      exec_timestamp_is_modified = true;
      tt_selling_rate_is_set = true;
      tt_selling_rate_is_modified = true;
      tt_buying_rate_is_set = true;
      tt_buying_rate_is_modified = true;
      sub_currency_ratio_is_set = true;
      sub_currency_ratio_is_modified = true;
      restrict_rate_is_set = true;
      restrict_rate_is_modified = true;
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
    if ( !( other instanceof FrgnMmfExchangeRateRow ) )
       return false;
    return fieldsEqual( (FrgnMmfExchangeRateRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�FrgnMmfExchangeRateRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( FrgnMmfExchangeRateRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( exec_timestamp == null ) {
      if ( row.getExecTimestamp() != null )
        return false;
    } else if ( !exec_timestamp.equals( row.getExecTimestamp() ) ) {
        return false;
    }
    if ( tt_selling_rate == null ) {
      if ( !row.getTtSellingRateIsNull() )
        return false;
    } else if ( row.getTtSellingRateIsNull() || ( tt_selling_rate.doubleValue() != row.getTtSellingRate() ) ) {
        return false;
    }
    if ( tt_buying_rate == null ) {
      if ( !row.getTtBuyingRateIsNull() )
        return false;
    } else if ( row.getTtBuyingRateIsNull() || ( tt_buying_rate.doubleValue() != row.getTtBuyingRate() ) ) {
        return false;
    }
    if ( sub_currency_ratio == null ) {
      if ( !row.getSubCurrencyRatioIsNull() )
        return false;
    } else if ( row.getSubCurrencyRatioIsNull() || ( sub_currency_ratio.intValue() != row.getSubCurrencyRatio() ) ) {
        return false;
    }
    if ( restrict_rate == null ) {
      if ( !row.getRestrictRateIsNull() )
        return false;
    } else if ( row.getRestrictRateIsNull() || ( restrict_rate.doubleValue() != row.getRestrictRate() ) ) {
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
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (exec_timestamp!=null? exec_timestamp.hashCode(): 0) 
        + (tt_selling_rate!=null? tt_selling_rate.hashCode(): 0) 
        + (tt_buying_rate!=null? tt_buying_rate.hashCode(): 0) 
        + (sub_currency_ratio!=null? sub_currency_ratio.hashCode(): 0) 
        + (restrict_rate!=null? restrict_rate.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
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
		map.put("currency_code",currency_code);
		if ( exec_timestamp != null )
			map.put("exec_timestamp",exec_timestamp);
		if ( tt_selling_rate != null )
			map.put("tt_selling_rate",tt_selling_rate);
		if ( tt_buying_rate != null )
			map.put("tt_buying_rate",tt_buying_rate);
		if ( sub_currency_ratio != null )
			map.put("sub_currency_ratio",sub_currency_ratio);
		if ( restrict_rate != null )
			map.put("restrict_rate",restrict_rate);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( exec_timestamp_is_modified )
			map.put("exec_timestamp",exec_timestamp);
		if ( tt_selling_rate_is_modified )
			map.put("tt_selling_rate",tt_selling_rate);
		if ( tt_buying_rate_is_modified )
			map.put("tt_buying_rate",tt_buying_rate);
		if ( sub_currency_ratio_is_modified )
			map.put("sub_currency_ratio",sub_currency_ratio);
		if ( restrict_rate_is_modified )
			map.put("restrict_rate",restrict_rate);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("exec_timestamp",exec_timestamp);
			map.put("tt_selling_rate",tt_selling_rate);
			map.put("tt_buying_rate",tt_buying_rate);
			map.put("sub_currency_ratio",sub_currency_ratio);
			map.put("restrict_rate",restrict_rate);
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
   * <em>currency_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCurrencyCode()
  {
    return currency_code;
  }


  /** 
   * <em>currency_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrencyCodeIsSet() {
    return currency_code_is_set;
  }


  /** 
   * <em>currency_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrencyCodeIsModified() {
    return currency_code_is_modified;
  }


  /** 
   * <em>exec_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getExecTimestamp()
  {
    return exec_timestamp;
  }


  /** 
   * <em>exec_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecTimestampIsSet() {
    return exec_timestamp_is_set;
  }


  /** 
   * <em>exec_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecTimestampIsModified() {
    return exec_timestamp_is_modified;
  }


  /** 
   * <em>tt_selling_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getTtSellingRate()
  {
    return ( tt_selling_rate==null? ((double)0): tt_selling_rate.doubleValue() );
  }


  /** 
   * <em>tt_selling_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTtSellingRateIsNull()
  {
    return tt_selling_rate == null;
  }


  /** 
   * <em>tt_selling_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTtSellingRateIsSet() {
    return tt_selling_rate_is_set;
  }


  /** 
   * <em>tt_selling_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTtSellingRateIsModified() {
    return tt_selling_rate_is_modified;
  }


  /** 
   * <em>tt_buying_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getTtBuyingRate()
  {
    return ( tt_buying_rate==null? ((double)0): tt_buying_rate.doubleValue() );
  }


  /** 
   * <em>tt_buying_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTtBuyingRateIsNull()
  {
    return tt_buying_rate == null;
  }


  /** 
   * <em>tt_buying_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTtBuyingRateIsSet() {
    return tt_buying_rate_is_set;
  }


  /** 
   * <em>tt_buying_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTtBuyingRateIsModified() {
    return tt_buying_rate_is_modified;
  }


  /** 
   * <em>sub_currency_ratio</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getSubCurrencyRatio()
  {
    return ( sub_currency_ratio==null? ((int)0): sub_currency_ratio.intValue() );
  }


  /** 
   * <em>sub_currency_ratio</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSubCurrencyRatioIsNull()
  {
    return sub_currency_ratio == null;
  }


  /** 
   * <em>sub_currency_ratio</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubCurrencyRatioIsSet() {
    return sub_currency_ratio_is_set;
  }


  /** 
   * <em>sub_currency_ratio</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubCurrencyRatioIsModified() {
    return sub_currency_ratio_is_modified;
  }


  /** 
   * <em>restrict_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getRestrictRate()
  {
    return ( restrict_rate==null? ((double)0): restrict_rate.doubleValue() );
  }


  /** 
   * <em>restrict_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getRestrictRateIsNull()
  {
    return restrict_rate == null;
  }


  /** 
   * <em>restrict_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRestrictRateIsSet() {
    return restrict_rate_is_set;
  }


  /** 
   * <em>restrict_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRestrictRateIsModified() {
    return restrict_rate_is_modified;
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
    return new FrgnMmfExchangeRatePK(institution_code, currency_code);
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
   * <em>currency_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_currencyCode <em>currency_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>exec_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_execTimestamp <em>exec_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setExecTimestamp( java.sql.Timestamp p_execTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_timestamp = p_execTimestamp;
    exec_timestamp_is_set = true;
    exec_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>exec_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setExecTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    exec_timestamp_is_set = true;
    exec_timestamp_is_modified = true;
  }


  /** 
   * <em>tt_selling_rate</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ttSellingRate <em>tt_selling_rate</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setTtSellingRate( double p_ttSellingRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tt_selling_rate = new Double(p_ttSellingRate);
    tt_selling_rate_is_set = true;
    tt_selling_rate_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>tt_selling_rate</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTtSellingRate( Double p_ttSellingRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    tt_selling_rate = p_ttSellingRate;
    tt_selling_rate_is_set = true;
    tt_selling_rate_is_modified = true;
  }


  /** 
   * <em>tt_buying_rate</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ttBuyingRate <em>tt_buying_rate</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setTtBuyingRate( double p_ttBuyingRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tt_buying_rate = new Double(p_ttBuyingRate);
    tt_buying_rate_is_set = true;
    tt_buying_rate_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>tt_buying_rate</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTtBuyingRate( Double p_ttBuyingRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    tt_buying_rate = p_ttBuyingRate;
    tt_buying_rate_is_set = true;
    tt_buying_rate_is_modified = true;
  }


  /** 
   * <em>sub_currency_ratio</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subCurrencyRatio <em>sub_currency_ratio</em>�J�����̒l������킷5���ȉ���int�l 
   */
  public final void setSubCurrencyRatio( int p_subCurrencyRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_currency_ratio = new Integer(p_subCurrencyRatio);
    sub_currency_ratio_is_set = true;
    sub_currency_ratio_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>sub_currency_ratio</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSubCurrencyRatio( Integer p_subCurrencyRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sub_currency_ratio = p_subCurrencyRatio;
    sub_currency_ratio_is_set = true;
    sub_currency_ratio_is_modified = true;
  }


  /** 
   * <em>restrict_rate</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_restrictRate <em>restrict_rate</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setRestrictRate( double p_restrictRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    restrict_rate = new Double(p_restrictRate);
    restrict_rate_is_set = true;
    restrict_rate_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>restrict_rate</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setRestrictRate( Double p_restrictRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    restrict_rate = p_restrictRate;
    restrict_rate_is_set = true;
    restrict_rate_is_modified = true;
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
                if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("exec_timestamp") ) {
                    return this.exec_timestamp;
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
            case 'r':
                if ( name.equals("restrict_rate") ) {
                    return this.restrict_rate;
                }
                break;
            case 's':
                if ( name.equals("sub_currency_ratio") ) {
                    return this.sub_currency_ratio;
                }
                break;
            case 't':
                if ( name.equals("tt_selling_rate") ) {
                    return this.tt_selling_rate;
                }
                else if ( name.equals("tt_buying_rate") ) {
                    return this.tt_buying_rate;
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
                if ( name.equals("currency_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_code' must be String: '"+value+"' is not." );
                    this.currency_code = (String) value;
                    if (this.currency_code_is_set)
                        this.currency_code_is_modified = true;
                    this.currency_code_is_set = true;
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
                if ( name.equals("exec_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'exec_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.exec_timestamp = (java.sql.Timestamp) value;
                    if (this.exec_timestamp_is_set)
                        this.exec_timestamp_is_modified = true;
                    this.exec_timestamp_is_set = true;
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
            case 'r':
                if ( name.equals("restrict_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'restrict_rate' must be Double: '"+value+"' is not." );
                    this.restrict_rate = (Double) value;
                    if (this.restrict_rate_is_set)
                        this.restrict_rate_is_modified = true;
                    this.restrict_rate_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_currency_ratio") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sub_currency_ratio' must be Integer: '"+value+"' is not." );
                    this.sub_currency_ratio = (Integer) value;
                    if (this.sub_currency_ratio_is_set)
                        this.sub_currency_ratio_is_modified = true;
                    this.sub_currency_ratio_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tt_selling_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'tt_selling_rate' must be Double: '"+value+"' is not." );
                    this.tt_selling_rate = (Double) value;
                    if (this.tt_selling_rate_is_set)
                        this.tt_selling_rate_is_modified = true;
                    this.tt_selling_rate_is_set = true;
                    return;
                }
                else if ( name.equals("tt_buying_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'tt_buying_rate' must be Double: '"+value+"' is not." );
                    this.tt_buying_rate = (Double) value;
                    if (this.tt_buying_rate_is_set)
                        this.tt_buying_rate_is_modified = true;
                    this.tt_buying_rate_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
