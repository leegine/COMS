head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.39.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ProductParams.java;


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
 * product�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link ProductRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link ProductParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link ProductParams}��{@@link ProductRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see ProductPK 
 * @@see ProductRow 
 */
public class ProductParams extends Params implements ProductRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "product";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = ProductRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return ProductRow.TYPE;
  }


  /** 
   * <em>product_id</em>�J�����̒l 
   */
  public  long  product_id;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>product_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>standard_name</em>�J�����̒l 
   */
  public  String  standard_name;

  /** 
   * <em>name_alt1</em>�J�����̒l 
   */
  public  String  name_alt1;

  /** 
   * <em>name_alt2</em>�J�����̒l 
   */
  public  String  name_alt2;

  /** 
   * <em>lot_size</em>�J�����̒l 
   */
  public  double  lot_size;

  /** 
   * <em>calc_size</em>�J�����̒l 
   */
  public  double  calc_size;

  /** 
   * <em>estimation_price</em>�J�����̒l 
   */
  public  double  estimation_price;

  /** 
   * <em>margin_ratio</em>�J�����̒l 
   */
  public  double  margin_ratio;

  /** 
   * <em>securities_estimation_ratio</em>�J�����̒l 
   */
  public  double  securities_estimation_ratio;

  /** 
   * <em>primary_market_id</em>�J�����̒l 
   */
  public  Long  primary_market_id;

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

  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean standard_name_is_set = false;
  private boolean standard_name_is_modified = false;
  private boolean name_alt1_is_set = false;
  private boolean name_alt1_is_modified = false;
  private boolean name_alt2_is_set = false;
  private boolean name_alt2_is_modified = false;
  private boolean lot_size_is_set = false;
  private boolean lot_size_is_modified = false;
  private boolean calc_size_is_set = false;
  private boolean calc_size_is_modified = false;
  private boolean estimation_price_is_set = false;
  private boolean estimation_price_is_modified = false;
  private boolean margin_ratio_is_set = false;
  private boolean margin_ratio_is_modified = false;
  private boolean securities_estimation_ratio_is_set = false;
  private boolean securities_estimation_ratio_is_modified = false;
  private boolean primary_market_id_is_set = false;
  private boolean primary_market_id_is_modified = false;
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
       + "product_id=" + product_id
      + "," + "institution_code=" +institution_code
      + "," + "product_type=" +product_type
      + "," + "standard_name=" +standard_name
      + "," + "name_alt1=" +name_alt1
      + "," + "name_alt2=" +name_alt2
      + "," + "lot_size=" +lot_size
      + "," + "calc_size=" +calc_size
      + "," + "estimation_price=" +estimation_price
      + "," + "margin_ratio=" +margin_ratio
      + "," + "securities_estimation_ratio=" +securities_estimation_ratio
      + "," + "primary_market_id=" +primary_market_id
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��ProductParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public ProductParams() {
    product_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���ProductRow�I�u�W�F�N�g�̒l�𗘗p����ProductParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����ProductRow�I�u�W�F�N�g 
   */
  public ProductParams( ProductRow p_row )
  {
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    standard_name = p_row.getStandardName();
    standard_name_is_set = p_row.getStandardNameIsSet();
    standard_name_is_modified = p_row.getStandardNameIsModified();
    name_alt1 = p_row.getNameAlt1();
    name_alt1_is_set = p_row.getNameAlt1IsSet();
    name_alt1_is_modified = p_row.getNameAlt1IsModified();
    name_alt2 = p_row.getNameAlt2();
    name_alt2_is_set = p_row.getNameAlt2IsSet();
    name_alt2_is_modified = p_row.getNameAlt2IsModified();
    lot_size = p_row.getLotSize();
    lot_size_is_set = p_row.getLotSizeIsSet();
    lot_size_is_modified = p_row.getLotSizeIsModified();
    calc_size = p_row.getCalcSize();
    calc_size_is_set = p_row.getCalcSizeIsSet();
    calc_size_is_modified = p_row.getCalcSizeIsModified();
    estimation_price = p_row.getEstimationPrice();
    estimation_price_is_set = p_row.getEstimationPriceIsSet();
    estimation_price_is_modified = p_row.getEstimationPriceIsModified();
    margin_ratio = p_row.getMarginRatio();
    margin_ratio_is_set = p_row.getMarginRatioIsSet();
    margin_ratio_is_modified = p_row.getMarginRatioIsModified();
    securities_estimation_ratio = p_row.getSecuritiesEstimationRatio();
    securities_estimation_ratio_is_set = p_row.getSecuritiesEstimationRatioIsSet();
    securities_estimation_ratio_is_modified = p_row.getSecuritiesEstimationRatioIsModified();
    if ( !p_row.getPrimaryMarketIdIsNull() )
      primary_market_id = new Long( p_row.getPrimaryMarketId() );
    primary_market_id_is_set = p_row.getPrimaryMarketIdIsSet();
    primary_market_id_is_modified = p_row.getPrimaryMarketIdIsModified();
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
      institution_code_is_set = true;
      institution_code_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      standard_name_is_set = true;
      standard_name_is_modified = true;
      name_alt1_is_set = true;
      name_alt1_is_modified = true;
      name_alt2_is_set = true;
      name_alt2_is_modified = true;
      lot_size_is_set = true;
      lot_size_is_modified = true;
      calc_size_is_set = true;
      calc_size_is_modified = true;
      estimation_price_is_set = true;
      estimation_price_is_modified = true;
      margin_ratio_is_set = true;
      margin_ratio_is_modified = true;
      securities_estimation_ratio_is_set = true;
      securities_estimation_ratio_is_modified = true;
      primary_market_id_is_set = true;
      primary_market_id_is_modified = true;
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
    if ( !( other instanceof ProductRow ) )
       return false;
    return fieldsEqual( (ProductRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�ProductRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( ProductRow row )
  {
    if ( product_id != row.getProductId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( standard_name == null ) {
      if ( row.getStandardName() != null )
        return false;
    } else if ( !standard_name.equals( row.getStandardName() ) ) {
        return false;
    }
    if ( name_alt1 == null ) {
      if ( row.getNameAlt1() != null )
        return false;
    } else if ( !name_alt1.equals( row.getNameAlt1() ) ) {
        return false;
    }
    if ( name_alt2 == null ) {
      if ( row.getNameAlt2() != null )
        return false;
    } else if ( !name_alt2.equals( row.getNameAlt2() ) ) {
        return false;
    }
    if ( lot_size != row.getLotSize() )
      return false;
    if ( calc_size != row.getCalcSize() )
      return false;
    if ( estimation_price != row.getEstimationPrice() )
      return false;
    if ( margin_ratio != row.getMarginRatio() )
      return false;
    if ( securities_estimation_ratio != row.getSecuritiesEstimationRatio() )
      return false;
    if ( primary_market_id == null ) {
      if ( !row.getPrimaryMarketIdIsNull() )
        return false;
    } else if ( row.getPrimaryMarketIdIsNull() || ( primary_market_id.longValue() != row.getPrimaryMarketId() ) ) {
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
      return  ((int) product_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + (standard_name!=null? standard_name.hashCode(): 0) 
        + (name_alt1!=null? name_alt1.hashCode(): 0) 
        + (name_alt2!=null? name_alt2.hashCode(): 0) 
        + ((int) lot_size)
        + ((int) calc_size)
        + ((int) estimation_price)
        + ((int) margin_ratio)
        + ((int) securities_estimation_ratio)
        + (primary_market_id!=null? primary_market_id.hashCode(): 0) 
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
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !standard_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'standard_name' must be set before inserting.");
		if ( !lot_size_is_set )
			throw new IllegalArgumentException("non-nullable field 'lot_size' must be set before inserting.");
		if ( !calc_size_is_set )
			throw new IllegalArgumentException("non-nullable field 'calc_size' must be set before inserting.");
		if ( !estimation_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'estimation_price' must be set before inserting.");
		if ( !margin_ratio_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_ratio' must be set before inserting.");
		if ( !securities_estimation_ratio_is_set )
			throw new IllegalArgumentException("non-nullable field 'securities_estimation_ratio' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("product_id",new Long(product_id));
		map.put("institution_code",institution_code);
		map.put("product_type",product_type);
		map.put("standard_name",standard_name);
		if ( name_alt1 != null )
			map.put("name_alt1",name_alt1);
		if ( name_alt2 != null )
			map.put("name_alt2",name_alt2);
		map.put("lot_size",new Double(lot_size));
		map.put("calc_size",new Double(calc_size));
		map.put("estimation_price",new Double(estimation_price));
		map.put("margin_ratio",new Double(margin_ratio));
		map.put("securities_estimation_ratio",new Double(securities_estimation_ratio));
		if ( primary_market_id != null )
			map.put("primary_market_id",primary_market_id);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
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
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( standard_name_is_modified )
			map.put("standard_name",standard_name);
		if ( name_alt1_is_modified )
			map.put("name_alt1",name_alt1);
		if ( name_alt2_is_modified )
			map.put("name_alt2",name_alt2);
		if ( lot_size_is_modified )
			map.put("lot_size",new Double(lot_size));
		if ( calc_size_is_modified )
			map.put("calc_size",new Double(calc_size));
		if ( estimation_price_is_modified )
			map.put("estimation_price",new Double(estimation_price));
		if ( margin_ratio_is_modified )
			map.put("margin_ratio",new Double(margin_ratio));
		if ( securities_estimation_ratio_is_modified )
			map.put("securities_estimation_ratio",new Double(securities_estimation_ratio));
		if ( primary_market_id_is_modified )
			map.put("primary_market_id",primary_market_id);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( product_type_is_set )
				map.put("product_type",product_type);
			if ( standard_name_is_set )
				map.put("standard_name",standard_name);
			map.put("name_alt1",name_alt1);
			map.put("name_alt2",name_alt2);
			if ( lot_size_is_set )
				map.put("lot_size",new Double(lot_size));
			if ( calc_size_is_set )
				map.put("calc_size",new Double(calc_size));
			if ( estimation_price_is_set )
				map.put("estimation_price",new Double(estimation_price));
			if ( margin_ratio_is_set )
				map.put("margin_ratio",new Double(margin_ratio));
			if ( securities_estimation_ratio_is_set )
				map.put("securities_estimation_ratio",new Double(securities_estimation_ratio));
			map.put("primary_market_id",primary_market_id);
			map.put("last_updater",last_updater);
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
   * <em>product_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType()
  {
    return product_type;
  }


  /** 
   * <em>product_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductTypeIsSet() {
    return product_type_is_set;
  }


  /** 
   * <em>product_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductTypeIsModified() {
    return product_type_is_modified;
  }


  /** 
   * <em>standard_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStandardName()
  {
    return standard_name;
  }


  /** 
   * <em>standard_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStandardNameIsSet() {
    return standard_name_is_set;
  }


  /** 
   * <em>standard_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStandardNameIsModified() {
    return standard_name_is_modified;
  }


  /** 
   * <em>name_alt1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getNameAlt1()
  {
    return name_alt1;
  }


  /** 
   * <em>name_alt1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNameAlt1IsSet() {
    return name_alt1_is_set;
  }


  /** 
   * <em>name_alt1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNameAlt1IsModified() {
    return name_alt1_is_modified;
  }


  /** 
   * <em>name_alt2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getNameAlt2()
  {
    return name_alt2;
  }


  /** 
   * <em>name_alt2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNameAlt2IsSet() {
    return name_alt2_is_set;
  }


  /** 
   * <em>name_alt2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNameAlt2IsModified() {
    return name_alt2_is_modified;
  }


  /** 
   * <em>lot_size</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getLotSize()
  {
    return lot_size;
  }


  /** 
   * <em>lot_size</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLotSizeIsSet() {
    return lot_size_is_set;
  }


  /** 
   * <em>lot_size</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLotSizeIsModified() {
    return lot_size_is_modified;
  }


  /** 
   * <em>calc_size</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCalcSize()
  {
    return calc_size;
  }


  /** 
   * <em>calc_size</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcSizeIsSet() {
    return calc_size_is_set;
  }


  /** 
   * <em>calc_size</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcSizeIsModified() {
    return calc_size_is_modified;
  }


  /** 
   * <em>estimation_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getEstimationPrice()
  {
    return estimation_price;
  }


  /** 
   * <em>estimation_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEstimationPriceIsSet() {
    return estimation_price_is_set;
  }


  /** 
   * <em>estimation_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEstimationPriceIsModified() {
    return estimation_price_is_modified;
  }


  /** 
   * <em>margin_ratio</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getMarginRatio()
  {
    return margin_ratio;
  }


  /** 
   * <em>margin_ratio</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarginRatioIsSet() {
    return margin_ratio_is_set;
  }


  /** 
   * <em>margin_ratio</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarginRatioIsModified() {
    return margin_ratio_is_modified;
  }


  /** 
   * <em>securities_estimation_ratio</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getSecuritiesEstimationRatio()
  {
    return securities_estimation_ratio;
  }


  /** 
   * <em>securities_estimation_ratio</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSecuritiesEstimationRatioIsSet() {
    return securities_estimation_ratio_is_set;
  }


  /** 
   * <em>securities_estimation_ratio</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSecuritiesEstimationRatioIsModified() {
    return securities_estimation_ratio_is_modified;
  }


  /** 
   * <em>primary_market_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getPrimaryMarketId()
  {
    return ( primary_market_id==null? ((long)0): primary_market_id.longValue() );
  }


  /** 
   * <em>primary_market_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getPrimaryMarketIdIsNull()
  {
    return primary_market_id == null;
  }


  /** 
   * <em>primary_market_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPrimaryMarketIdIsSet() {
    return primary_market_id_is_set;
  }


  /** 
   * <em>primary_market_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPrimaryMarketIdIsModified() {
    return primary_market_id_is_modified;
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
    return new ProductPK(product_id);
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
   * <em>product_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productType <em>product_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   */
  public final void setProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_type = p_productType;
    product_type_is_set = true;
    product_type_is_modified = true;
  }


  /** 
   * <em>standard_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_standardName <em>standard_name</em>�J�����̒l������킷250���ȉ���String�l 
   */
  public final void setStandardName( String p_standardName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    standard_name = p_standardName;
    standard_name_is_set = true;
    standard_name_is_modified = true;
  }


  /** 
   * <em>name_alt1</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_nameAlt1 <em>name_alt1</em>�J�����̒l������킷250���ȉ���String�l 
   */
  public final void setNameAlt1( String p_nameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name_alt1 = p_nameAlt1;
    name_alt1_is_set = true;
    name_alt1_is_modified = true;
  }


  /** 
   * <em>name_alt2</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_nameAlt2 <em>name_alt2</em>�J�����̒l������킷250���ȉ���String�l 
   */
  public final void setNameAlt2( String p_nameAlt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name_alt2 = p_nameAlt2;
    name_alt2_is_set = true;
    name_alt2_is_modified = true;
  }


  /** 
   * <em>lot_size</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lotSize <em>lot_size</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setLotSize( double p_lotSize )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    lot_size = p_lotSize;
    lot_size_is_set = true;
    lot_size_is_modified = true;
  }


  /** 
   * <em>calc_size</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_calcSize <em>calc_size</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCalcSize( double p_calcSize )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_size = p_calcSize;
    calc_size_is_set = true;
    calc_size_is_modified = true;
  }


  /** 
   * <em>estimation_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_estimationPrice <em>estimation_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setEstimationPrice( double p_estimationPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    estimation_price = p_estimationPrice;
    estimation_price_is_set = true;
    estimation_price_is_modified = true;
  }


  /** 
   * <em>margin_ratio</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marginRatio <em>margin_ratio</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setMarginRatio( double p_marginRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_ratio = p_marginRatio;
    margin_ratio_is_set = true;
    margin_ratio_is_modified = true;
  }


  /** 
   * <em>securities_estimation_ratio</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_securitiesEstimationRatio <em>securities_estimation_ratio</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setSecuritiesEstimationRatio( double p_securitiesEstimationRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    securities_estimation_ratio = p_securitiesEstimationRatio;
    securities_estimation_ratio_is_set = true;
    securities_estimation_ratio_is_modified = true;
  }


  /** 
   * <em>primary_market_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_primaryMarketId <em>primary_market_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setPrimaryMarketId( long p_primaryMarketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    primary_market_id = new Long(p_primaryMarketId);
    primary_market_id_is_set = true;
    primary_market_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>primary_market_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setPrimaryMarketId( Long p_primaryMarketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    primary_market_id = p_primaryMarketId;
    primary_market_id_is_set = true;
    primary_market_id_is_modified = true;
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
                if ( name.equals("calc_size") ) {
                    return new Double( calc_size );
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("estimation_price") ) {
                    return new Double( estimation_price );
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("lot_size") ) {
                    return new Double( lot_size );
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("margin_ratio") ) {
                    return new Double( margin_ratio );
                }
                break;
            case 'n':
                if ( name.equals("name_alt1") ) {
                    return this.name_alt1;
                }
                else if ( name.equals("name_alt2") ) {
                    return this.name_alt2;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("primary_market_id") ) {
                    return this.primary_market_id;
                }
                break;
            case 's':
                if ( name.equals("standard_name") ) {
                    return this.standard_name;
                }
                else if ( name.equals("securities_estimation_ratio") ) {
                    return new Double( securities_estimation_ratio );
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
                if ( name.equals("calc_size") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'calc_size' must be Double: '"+value+"' is not." );
                    this.calc_size = ((Double) value).doubleValue();
                    if (this.calc_size_is_set)
                        this.calc_size_is_modified = true;
                    this.calc_size_is_set = true;
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
                if ( name.equals("estimation_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'estimation_price' must be Double: '"+value+"' is not." );
                    this.estimation_price = ((Double) value).doubleValue();
                    if (this.estimation_price_is_set)
                        this.estimation_price_is_modified = true;
                    this.estimation_price_is_set = true;
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
                if ( name.equals("lot_size") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'lot_size' must be Double: '"+value+"' is not." );
                    this.lot_size = ((Double) value).doubleValue();
                    if (this.lot_size_is_set)
                        this.lot_size_is_modified = true;
                    this.lot_size_is_set = true;
                    return;
                }
                else if ( name.equals("last_updater") ) {
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
                if ( name.equals("margin_ratio") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_ratio' must be Double: '"+value+"' is not." );
                    this.margin_ratio = ((Double) value).doubleValue();
                    if (this.margin_ratio_is_set)
                        this.margin_ratio_is_modified = true;
                    this.margin_ratio_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'name_alt1' must be String: '"+value+"' is not." );
                    this.name_alt1 = (String) value;
                    if (this.name_alt1_is_set)
                        this.name_alt1_is_modified = true;
                    this.name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("name_alt2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'name_alt2' must be String: '"+value+"' is not." );
                    this.name_alt2 = (String) value;
                    if (this.name_alt2_is_set)
                        this.name_alt2_is_modified = true;
                    this.name_alt2_is_set = true;
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
                else if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                else if ( name.equals("primary_market_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'primary_market_id' must be Long: '"+value+"' is not." );
                    this.primary_market_id = (Long) value;
                    if (this.primary_market_id_is_set)
                        this.primary_market_id_is_modified = true;
                    this.primary_market_id_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("standard_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'standard_name' must be String: '"+value+"' is not." );
                    this.standard_name = (String) value;
                    if (this.standard_name_is_set)
                        this.standard_name_is_modified = true;
                    this.standard_name_is_set = true;
                    return;
                }
                else if ( name.equals("securities_estimation_ratio") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'securities_estimation_ratio' must be Double: '"+value+"' is not." );
                    this.securities_estimation_ratio = ((Double) value).doubleValue();
                    if (this.securities_estimation_ratio_is_set)
                        this.securities_estimation_ratio_is_modified = true;
                    this.securities_estimation_ratio_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
