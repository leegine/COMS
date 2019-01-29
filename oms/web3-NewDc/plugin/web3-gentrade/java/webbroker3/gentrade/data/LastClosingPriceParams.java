head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.23.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	LastClosingPriceParams.java;


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
 * last_closing_price�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link LastClosingPriceRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link LastClosingPriceParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link LastClosingPriceParams}��{@@link LastClosingPriceRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see LastClosingPricePK 
 * @@see LastClosingPriceRow 
 */
public class LastClosingPriceParams extends Params implements LastClosingPriceRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "last_closing_price";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = LastClosingPriceRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return LastClosingPriceRow.TYPE;
  }


  /** 
   * <em>product_id</em>�J�����̒l 
   */
  public  long  product_id;

  /** 
   * <em>base_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  base_date;

  /** 
   * <em>tokyo_closing_price</em>�J�����̒l 
   */
  public  Double  tokyo_closing_price;

  /** 
   * <em>oosaka_closing_price</em>�J�����̒l 
   */
  public  Double  oosaka_closing_price;

  /** 
   * <em>nagoya_closing_price</em>�J�����̒l 
   */
  public  Double  nagoya_closing_price;

  /** 
   * <em>other_closing_price</em>�J�����̒l 
   */
  public  Double  other_closing_price;

  /** 
   * <em>primary_closing_price</em>�J�����̒l 
   */
  public  Double  primary_closing_price;

  /** 
   * <em>error_code</em>�J�����̒l 
   */
  public  String  error_code;

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
  private boolean base_date_is_set = false;
  private boolean base_date_is_modified = false;
  private boolean tokyo_closing_price_is_set = false;
  private boolean tokyo_closing_price_is_modified = false;
  private boolean oosaka_closing_price_is_set = false;
  private boolean oosaka_closing_price_is_modified = false;
  private boolean nagoya_closing_price_is_set = false;
  private boolean nagoya_closing_price_is_modified = false;
  private boolean other_closing_price_is_set = false;
  private boolean other_closing_price_is_modified = false;
  private boolean primary_closing_price_is_set = false;
  private boolean primary_closing_price_is_modified = false;
  private boolean error_code_is_set = false;
  private boolean error_code_is_modified = false;
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
      + "," + "base_date=" + base_date
      + "," + "tokyo_closing_price=" +tokyo_closing_price
      + "," + "oosaka_closing_price=" +oosaka_closing_price
      + "," + "nagoya_closing_price=" +nagoya_closing_price
      + "," + "other_closing_price=" +other_closing_price
      + "," + "primary_closing_price=" +primary_closing_price
      + "," + "error_code=" +error_code
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��LastClosingPriceParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public LastClosingPriceParams() {
    product_id_is_modified = true;
    base_date_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���LastClosingPriceRow�I�u�W�F�N�g�̒l�𗘗p����LastClosingPriceParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����LastClosingPriceRow�I�u�W�F�N�g 
   */
  public LastClosingPriceParams( LastClosingPriceRow p_row )
  {
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    base_date = p_row.getBaseDate();
    base_date_is_set = p_row.getBaseDateIsSet();
    base_date_is_modified = p_row.getBaseDateIsModified();
    if ( !p_row.getTokyoClosingPriceIsNull() )
      tokyo_closing_price = new Double( p_row.getTokyoClosingPrice() );
    tokyo_closing_price_is_set = p_row.getTokyoClosingPriceIsSet();
    tokyo_closing_price_is_modified = p_row.getTokyoClosingPriceIsModified();
    if ( !p_row.getOosakaClosingPriceIsNull() )
      oosaka_closing_price = new Double( p_row.getOosakaClosingPrice() );
    oosaka_closing_price_is_set = p_row.getOosakaClosingPriceIsSet();
    oosaka_closing_price_is_modified = p_row.getOosakaClosingPriceIsModified();
    if ( !p_row.getNagoyaClosingPriceIsNull() )
      nagoya_closing_price = new Double( p_row.getNagoyaClosingPrice() );
    nagoya_closing_price_is_set = p_row.getNagoyaClosingPriceIsSet();
    nagoya_closing_price_is_modified = p_row.getNagoyaClosingPriceIsModified();
    if ( !p_row.getOtherClosingPriceIsNull() )
      other_closing_price = new Double( p_row.getOtherClosingPrice() );
    other_closing_price_is_set = p_row.getOtherClosingPriceIsSet();
    other_closing_price_is_modified = p_row.getOtherClosingPriceIsModified();
    if ( !p_row.getPrimaryClosingPriceIsNull() )
      primary_closing_price = new Double( p_row.getPrimaryClosingPrice() );
    primary_closing_price_is_set = p_row.getPrimaryClosingPriceIsSet();
    primary_closing_price_is_modified = p_row.getPrimaryClosingPriceIsModified();
    error_code = p_row.getErrorCode();
    error_code_is_set = p_row.getErrorCodeIsSet();
    error_code_is_modified = p_row.getErrorCodeIsModified();
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
      tokyo_closing_price_is_set = true;
      tokyo_closing_price_is_modified = true;
      oosaka_closing_price_is_set = true;
      oosaka_closing_price_is_modified = true;
      nagoya_closing_price_is_set = true;
      nagoya_closing_price_is_modified = true;
      other_closing_price_is_set = true;
      other_closing_price_is_modified = true;
      primary_closing_price_is_set = true;
      primary_closing_price_is_modified = true;
      error_code_is_set = true;
      error_code_is_modified = true;
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
    if ( !( other instanceof LastClosingPriceRow ) )
       return false;
    return fieldsEqual( (LastClosingPriceRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�LastClosingPriceRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( LastClosingPriceRow row )
  {
    if ( product_id != row.getProductId() )
      return false;
    if ( base_date == null ) {
      if ( row.getBaseDate() != null )
        return false;
    } else if ( !base_date.equals( row.getBaseDate() ) ) {
        return false;
    }
    if ( tokyo_closing_price == null ) {
      if ( !row.getTokyoClosingPriceIsNull() )
        return false;
    } else if ( row.getTokyoClosingPriceIsNull() || ( tokyo_closing_price.doubleValue() != row.getTokyoClosingPrice() ) ) {
        return false;
    }
    if ( oosaka_closing_price == null ) {
      if ( !row.getOosakaClosingPriceIsNull() )
        return false;
    } else if ( row.getOosakaClosingPriceIsNull() || ( oosaka_closing_price.doubleValue() != row.getOosakaClosingPrice() ) ) {
        return false;
    }
    if ( nagoya_closing_price == null ) {
      if ( !row.getNagoyaClosingPriceIsNull() )
        return false;
    } else if ( row.getNagoyaClosingPriceIsNull() || ( nagoya_closing_price.doubleValue() != row.getNagoyaClosingPrice() ) ) {
        return false;
    }
    if ( other_closing_price == null ) {
      if ( !row.getOtherClosingPriceIsNull() )
        return false;
    } else if ( row.getOtherClosingPriceIsNull() || ( other_closing_price.doubleValue() != row.getOtherClosingPrice() ) ) {
        return false;
    }
    if ( primary_closing_price == null ) {
      if ( !row.getPrimaryClosingPriceIsNull() )
        return false;
    } else if ( row.getPrimaryClosingPriceIsNull() || ( primary_closing_price.doubleValue() != row.getPrimaryClosingPrice() ) ) {
        return false;
    }
    if ( error_code == null ) {
      if ( row.getErrorCode() != null )
        return false;
    } else if ( !error_code.equals( row.getErrorCode() ) ) {
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
        + (base_date!=null? base_date.hashCode(): 0) 
        + (tokyo_closing_price!=null? tokyo_closing_price.hashCode(): 0) 
        + (oosaka_closing_price!=null? oosaka_closing_price.hashCode(): 0) 
        + (nagoya_closing_price!=null? nagoya_closing_price.hashCode(): 0) 
        + (other_closing_price!=null? other_closing_price.hashCode(): 0) 
        + (primary_closing_price!=null? primary_closing_price.hashCode(): 0) 
        + (error_code!=null? error_code.hashCode(): 0) 
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
		map.put("product_id",new Long(product_id));
		map.put("base_date",base_date);
		if ( tokyo_closing_price != null )
			map.put("tokyo_closing_price",tokyo_closing_price);
		if ( oosaka_closing_price != null )
			map.put("oosaka_closing_price",oosaka_closing_price);
		if ( nagoya_closing_price != null )
			map.put("nagoya_closing_price",nagoya_closing_price);
		if ( other_closing_price != null )
			map.put("other_closing_price",other_closing_price);
		if ( primary_closing_price != null )
			map.put("primary_closing_price",primary_closing_price);
		if ( error_code != null )
			map.put("error_code",error_code);
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
		if ( tokyo_closing_price_is_modified )
			map.put("tokyo_closing_price",tokyo_closing_price);
		if ( oosaka_closing_price_is_modified )
			map.put("oosaka_closing_price",oosaka_closing_price);
		if ( nagoya_closing_price_is_modified )
			map.put("nagoya_closing_price",nagoya_closing_price);
		if ( other_closing_price_is_modified )
			map.put("other_closing_price",other_closing_price);
		if ( primary_closing_price_is_modified )
			map.put("primary_closing_price",primary_closing_price);
		if ( error_code_is_modified )
			map.put("error_code",error_code);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("tokyo_closing_price",tokyo_closing_price);
			map.put("oosaka_closing_price",oosaka_closing_price);
			map.put("nagoya_closing_price",nagoya_closing_price);
			map.put("other_closing_price",other_closing_price);
			map.put("primary_closing_price",primary_closing_price);
			map.put("error_code",error_code);
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
   * <em>base_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getBaseDate()
  {
    return base_date;
  }


  /** 
   * <em>base_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBaseDateIsSet() {
    return base_date_is_set;
  }


  /** 
   * <em>base_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBaseDateIsModified() {
    return base_date_is_modified;
  }


  /** 
   * <em>tokyo_closing_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getTokyoClosingPrice()
  {
    return ( tokyo_closing_price==null? ((double)0): tokyo_closing_price.doubleValue() );
  }


  /** 
   * <em>tokyo_closing_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTokyoClosingPriceIsNull()
  {
    return tokyo_closing_price == null;
  }


  /** 
   * <em>tokyo_closing_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTokyoClosingPriceIsSet() {
    return tokyo_closing_price_is_set;
  }


  /** 
   * <em>tokyo_closing_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTokyoClosingPriceIsModified() {
    return tokyo_closing_price_is_modified;
  }


  /** 
   * <em>oosaka_closing_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getOosakaClosingPrice()
  {
    return ( oosaka_closing_price==null? ((double)0): oosaka_closing_price.doubleValue() );
  }


  /** 
   * <em>oosaka_closing_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOosakaClosingPriceIsNull()
  {
    return oosaka_closing_price == null;
  }


  /** 
   * <em>oosaka_closing_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOosakaClosingPriceIsSet() {
    return oosaka_closing_price_is_set;
  }


  /** 
   * <em>oosaka_closing_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOosakaClosingPriceIsModified() {
    return oosaka_closing_price_is_modified;
  }


  /** 
   * <em>nagoya_closing_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getNagoyaClosingPrice()
  {
    return ( nagoya_closing_price==null? ((double)0): nagoya_closing_price.doubleValue() );
  }


  /** 
   * <em>nagoya_closing_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getNagoyaClosingPriceIsNull()
  {
    return nagoya_closing_price == null;
  }


  /** 
   * <em>nagoya_closing_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNagoyaClosingPriceIsSet() {
    return nagoya_closing_price_is_set;
  }


  /** 
   * <em>nagoya_closing_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNagoyaClosingPriceIsModified() {
    return nagoya_closing_price_is_modified;
  }


  /** 
   * <em>other_closing_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getOtherClosingPrice()
  {
    return ( other_closing_price==null? ((double)0): other_closing_price.doubleValue() );
  }


  /** 
   * <em>other_closing_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOtherClosingPriceIsNull()
  {
    return other_closing_price == null;
  }


  /** 
   * <em>other_closing_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOtherClosingPriceIsSet() {
    return other_closing_price_is_set;
  }


  /** 
   * <em>other_closing_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOtherClosingPriceIsModified() {
    return other_closing_price_is_modified;
  }


  /** 
   * <em>primary_closing_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getPrimaryClosingPrice()
  {
    return ( primary_closing_price==null? ((double)0): primary_closing_price.doubleValue() );
  }


  /** 
   * <em>primary_closing_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getPrimaryClosingPriceIsNull()
  {
    return primary_closing_price == null;
  }


  /** 
   * <em>primary_closing_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPrimaryClosingPriceIsSet() {
    return primary_closing_price_is_set;
  }


  /** 
   * <em>primary_closing_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPrimaryClosingPriceIsModified() {
    return primary_closing_price_is_modified;
  }


  /** 
   * <em>error_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getErrorCode()
  {
    return error_code;
  }


  /** 
   * <em>error_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getErrorCodeIsSet() {
    return error_code_is_set;
  }


  /** 
   * <em>error_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getErrorCodeIsModified() {
    return error_code_is_modified;
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
    return new LastClosingPricePK(product_id, base_date);
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
   * <em>base_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_baseDate <em>base_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setBaseDate( java.sql.Timestamp p_baseDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_date = p_baseDate;
    base_date_is_set = true;
    base_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>base_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setBaseDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    base_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    base_date_is_set = true;
    base_date_is_modified = true;
  }


  /** 
   * <em>tokyo_closing_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tokyoClosingPrice <em>tokyo_closing_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setTokyoClosingPrice( double p_tokyoClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tokyo_closing_price = new Double(p_tokyoClosingPrice);
    tokyo_closing_price_is_set = true;
    tokyo_closing_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>tokyo_closing_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTokyoClosingPrice( Double p_tokyoClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    tokyo_closing_price = p_tokyoClosingPrice;
    tokyo_closing_price_is_set = true;
    tokyo_closing_price_is_modified = true;
  }


  /** 
   * <em>oosaka_closing_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_oosakaClosingPrice <em>oosaka_closing_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setOosakaClosingPrice( double p_oosakaClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    oosaka_closing_price = new Double(p_oosakaClosingPrice);
    oosaka_closing_price_is_set = true;
    oosaka_closing_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>oosaka_closing_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOosakaClosingPrice( Double p_oosakaClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    oosaka_closing_price = p_oosakaClosingPrice;
    oosaka_closing_price_is_set = true;
    oosaka_closing_price_is_modified = true;
  }


  /** 
   * <em>nagoya_closing_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_nagoyaClosingPrice <em>nagoya_closing_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setNagoyaClosingPrice( double p_nagoyaClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    nagoya_closing_price = new Double(p_nagoyaClosingPrice);
    nagoya_closing_price_is_set = true;
    nagoya_closing_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>nagoya_closing_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setNagoyaClosingPrice( Double p_nagoyaClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    nagoya_closing_price = p_nagoyaClosingPrice;
    nagoya_closing_price_is_set = true;
    nagoya_closing_price_is_modified = true;
  }


  /** 
   * <em>other_closing_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_otherClosingPrice <em>other_closing_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setOtherClosingPrice( double p_otherClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_closing_price = new Double(p_otherClosingPrice);
    other_closing_price_is_set = true;
    other_closing_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>other_closing_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOtherClosingPrice( Double p_otherClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_closing_price = p_otherClosingPrice;
    other_closing_price_is_set = true;
    other_closing_price_is_modified = true;
  }


  /** 
   * <em>primary_closing_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_primaryClosingPrice <em>primary_closing_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setPrimaryClosingPrice( double p_primaryClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    primary_closing_price = new Double(p_primaryClosingPrice);
    primary_closing_price_is_set = true;
    primary_closing_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>primary_closing_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setPrimaryClosingPrice( Double p_primaryClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    primary_closing_price = p_primaryClosingPrice;
    primary_closing_price_is_set = true;
    primary_closing_price_is_modified = true;
  }


  /** 
   * <em>error_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_errorCode <em>error_code</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setErrorCode( String p_errorCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_code = p_errorCode;
    error_code_is_set = true;
    error_code_is_modified = true;
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
                if ( name.equals("base_date") ) {
                    return this.base_date;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("error_code") ) {
                    return this.error_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("nagoya_closing_price") ) {
                    return this.nagoya_closing_price;
                }
                break;
            case 'o':
                if ( name.equals("oosaka_closing_price") ) {
                    return this.oosaka_closing_price;
                }
                else if ( name.equals("other_closing_price") ) {
                    return this.other_closing_price;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("primary_closing_price") ) {
                    return this.primary_closing_price;
                }
                break;
            case 't':
                if ( name.equals("tokyo_closing_price") ) {
                    return this.tokyo_closing_price;
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
                if ( name.equals("base_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'base_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.base_date = (java.sql.Timestamp) value;
                    if (this.base_date_is_set)
                        this.base_date_is_modified = true;
                    this.base_date_is_set = true;
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
                if ( name.equals("error_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_code' must be String: '"+value+"' is not." );
                    this.error_code = (String) value;
                    if (this.error_code_is_set)
                        this.error_code_is_modified = true;
                    this.error_code_is_set = true;
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
            case 'n':
                if ( name.equals("nagoya_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'nagoya_closing_price' must be Double: '"+value+"' is not." );
                    this.nagoya_closing_price = (Double) value;
                    if (this.nagoya_closing_price_is_set)
                        this.nagoya_closing_price_is_modified = true;
                    this.nagoya_closing_price_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("oosaka_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'oosaka_closing_price' must be Double: '"+value+"' is not." );
                    this.oosaka_closing_price = (Double) value;
                    if (this.oosaka_closing_price_is_set)
                        this.oosaka_closing_price_is_modified = true;
                    this.oosaka_closing_price_is_set = true;
                    return;
                }
                else if ( name.equals("other_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_closing_price' must be Double: '"+value+"' is not." );
                    this.other_closing_price = (Double) value;
                    if (this.other_closing_price_is_set)
                        this.other_closing_price_is_modified = true;
                    this.other_closing_price_is_set = true;
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
                else if ( name.equals("primary_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'primary_closing_price' must be Double: '"+value+"' is not." );
                    this.primary_closing_price = (Double) value;
                    if (this.primary_closing_price_is_set)
                        this.primary_closing_price_is_modified = true;
                    this.primary_closing_price_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tokyo_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'tokyo_closing_price' must be Double: '"+value+"' is not." );
                    this.tokyo_closing_price = (Double) value;
                    if (this.tokyo_closing_price_is_set)
                        this.tokyo_closing_price_is_modified = true;
                    this.tokyo_closing_price_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
