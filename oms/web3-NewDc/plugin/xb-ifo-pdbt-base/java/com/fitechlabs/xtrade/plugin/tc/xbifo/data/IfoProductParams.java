head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.07.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoProductParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbifo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * ifo_product�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link IfoProductRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link IfoProductParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link IfoProductParams}��{@@link IfoProductRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoProductPK 
 * @@see IfoProductRow 
 */
public class IfoProductParams extends Params implements IfoProductRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "ifo_product";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = IfoProductRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return IfoProductRow.TYPE;
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
   * <em>underlying_product_code</em>�J�����̒l 
   */
  public  String  underlying_product_code;

  /** 
   * <em>derivative_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum  derivative_type;

  /** 
   * <em>strike_price</em>�J�����̒l 
   */
  public  double  strike_price;

  /** 
   * <em>month_of_delivery</em>�J�����̒l 
   */
  public  String  month_of_delivery;

  /** 
   * <em>exercise_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  exercise_date;

  /** 
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>split_type</em>�J�����̒l 
   */
  public  String  split_type;

  /** 
   * <em>target_market_id</em>�J�����̒l 
   */
  public  Long  target_market_id;

  /** 
   * <em>index_id</em>�J�����̒l 
   */
  public  Long  index_id;

  /** 
   * <em>product_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>future_option_div</em>�J�����̒l 
   */
  public  String  future_option_div;

  /** 
   * <em>standard_name</em>�J�����̒l 
   */
  public  String  standard_name;

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
  private boolean underlying_product_code_is_set = false;
  private boolean underlying_product_code_is_modified = false;
  private boolean derivative_type_is_set = false;
  private boolean derivative_type_is_modified = false;
  private boolean strike_price_is_set = false;
  private boolean strike_price_is_modified = false;
  private boolean month_of_delivery_is_set = false;
  private boolean month_of_delivery_is_modified = false;
  private boolean exercise_date_is_set = false;
  private boolean exercise_date_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean split_type_is_set = false;
  private boolean split_type_is_modified = false;
  private boolean target_market_id_is_set = false;
  private boolean target_market_id_is_modified = false;
  private boolean index_id_is_set = false;
  private boolean index_id_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean future_option_div_is_set = false;
  private boolean future_option_div_is_modified = false;
  private boolean standard_name_is_set = false;
  private boolean standard_name_is_modified = false;
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
      + "," + "underlying_product_code=" +underlying_product_code
      + "," + "derivative_type=" +derivative_type
      + "," + "strike_price=" +strike_price
      + "," + "month_of_delivery=" +month_of_delivery
      + "," + "exercise_date=" +exercise_date
      + "," + "product_code=" +product_code
      + "," + "split_type=" +split_type
      + "," + "target_market_id=" +target_market_id
      + "," + "index_id=" +index_id
      + "," + "product_type=" +product_type
      + "," + "future_option_div=" +future_option_div
      + "," + "standard_name=" +standard_name
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��IfoProductParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public IfoProductParams() {
    product_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���IfoProductRow�I�u�W�F�N�g�̒l�𗘗p����IfoProductParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����IfoProductRow�I�u�W�F�N�g 
   */
  public IfoProductParams( IfoProductRow p_row )
  {
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    underlying_product_code = p_row.getUnderlyingProductCode();
    underlying_product_code_is_set = p_row.getUnderlyingProductCodeIsSet();
    underlying_product_code_is_modified = p_row.getUnderlyingProductCodeIsModified();
    derivative_type = p_row.getDerivativeType();
    derivative_type_is_set = p_row.getDerivativeTypeIsSet();
    derivative_type_is_modified = p_row.getDerivativeTypeIsModified();
    strike_price = p_row.getStrikePrice();
    strike_price_is_set = p_row.getStrikePriceIsSet();
    strike_price_is_modified = p_row.getStrikePriceIsModified();
    month_of_delivery = p_row.getMonthOfDelivery();
    month_of_delivery_is_set = p_row.getMonthOfDeliveryIsSet();
    month_of_delivery_is_modified = p_row.getMonthOfDeliveryIsModified();
    exercise_date = p_row.getExerciseDate();
    exercise_date_is_set = p_row.getExerciseDateIsSet();
    exercise_date_is_modified = p_row.getExerciseDateIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    split_type = p_row.getSplitType();
    split_type_is_set = p_row.getSplitTypeIsSet();
    split_type_is_modified = p_row.getSplitTypeIsModified();
    if ( !p_row.getTargetMarketIdIsNull() )
      target_market_id = new Long( p_row.getTargetMarketId() );
    target_market_id_is_set = p_row.getTargetMarketIdIsSet();
    target_market_id_is_modified = p_row.getTargetMarketIdIsModified();
    if ( !p_row.getIndexIdIsNull() )
      index_id = new Long( p_row.getIndexId() );
    index_id_is_set = p_row.getIndexIdIsSet();
    index_id_is_modified = p_row.getIndexIdIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    future_option_div = p_row.getFutureOptionDiv();
    future_option_div_is_set = p_row.getFutureOptionDivIsSet();
    future_option_div_is_modified = p_row.getFutureOptionDivIsModified();
    standard_name = p_row.getStandardName();
    standard_name_is_set = p_row.getStandardNameIsSet();
    standard_name_is_modified = p_row.getStandardNameIsModified();
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
      underlying_product_code_is_set = true;
      underlying_product_code_is_modified = true;
      derivative_type_is_set = true;
      derivative_type_is_modified = true;
      strike_price_is_set = true;
      strike_price_is_modified = true;
      month_of_delivery_is_set = true;
      month_of_delivery_is_modified = true;
      exercise_date_is_set = true;
      exercise_date_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      split_type_is_set = true;
      split_type_is_modified = true;
      target_market_id_is_set = true;
      target_market_id_is_modified = true;
      index_id_is_set = true;
      index_id_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      future_option_div_is_set = true;
      future_option_div_is_modified = true;
      standard_name_is_set = true;
      standard_name_is_modified = true;
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
    if ( !( other instanceof IfoProductRow ) )
       return false;
    return fieldsEqual( (IfoProductRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�IfoProductRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( IfoProductRow row )
  {
    if ( product_id != row.getProductId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( underlying_product_code == null ) {
      if ( row.getUnderlyingProductCode() != null )
        return false;
    } else if ( !underlying_product_code.equals( row.getUnderlyingProductCode() ) ) {
        return false;
    }
    if ( derivative_type == null ) {
      if ( row.getDerivativeType() != null )
        return false;
    } else if ( !derivative_type.equals( row.getDerivativeType() ) ) {
        return false;
    }
    if ( strike_price != row.getStrikePrice() )
      return false;
    if ( month_of_delivery == null ) {
      if ( row.getMonthOfDelivery() != null )
        return false;
    } else if ( !month_of_delivery.equals( row.getMonthOfDelivery() ) ) {
        return false;
    }
    if ( exercise_date == null ) {
      if ( row.getExerciseDate() != null )
        return false;
    } else if ( !exercise_date.equals( row.getExerciseDate() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( split_type == null ) {
      if ( row.getSplitType() != null )
        return false;
    } else if ( !split_type.equals( row.getSplitType() ) ) {
        return false;
    }
    if ( target_market_id == null ) {
      if ( !row.getTargetMarketIdIsNull() )
        return false;
    } else if ( row.getTargetMarketIdIsNull() || ( target_market_id.longValue() != row.getTargetMarketId() ) ) {
        return false;
    }
    if ( index_id == null ) {
      if ( !row.getIndexIdIsNull() )
        return false;
    } else if ( row.getIndexIdIsNull() || ( index_id.longValue() != row.getIndexId() ) ) {
        return false;
    }
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( future_option_div == null ) {
      if ( row.getFutureOptionDiv() != null )
        return false;
    } else if ( !future_option_div.equals( row.getFutureOptionDiv() ) ) {
        return false;
    }
    if ( standard_name == null ) {
      if ( row.getStandardName() != null )
        return false;
    } else if ( !standard_name.equals( row.getStandardName() ) ) {
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
        + (underlying_product_code!=null? underlying_product_code.hashCode(): 0) 
        + (derivative_type!=null? derivative_type.hashCode(): 0) 
        + ((int) strike_price)
        + (month_of_delivery!=null? month_of_delivery.hashCode(): 0) 
        + (exercise_date!=null? exercise_date.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (split_type!=null? split_type.hashCode(): 0) 
        + (target_market_id!=null? target_market_id.hashCode(): 0) 
        + (index_id!=null? index_id.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + (future_option_div!=null? future_option_div.hashCode(): 0) 
        + (standard_name!=null? standard_name.hashCode(): 0) 
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
		if ( !underlying_product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'underlying_product_code' must be set before inserting.");
		if ( !derivative_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'derivative_type' must be set before inserting.");
		if ( !strike_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'strike_price' must be set before inserting.");
		if ( !month_of_delivery_is_set )
			throw new IllegalArgumentException("non-nullable field 'month_of_delivery' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("product_id",new Long(product_id));
		map.put("institution_code",institution_code);
		map.put("underlying_product_code",underlying_product_code);
		map.put("derivative_type",derivative_type);
		map.put("strike_price",new Double(strike_price));
		map.put("month_of_delivery",month_of_delivery);
		if ( exercise_date != null )
			map.put("exercise_date",exercise_date);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( split_type != null )
			map.put("split_type",split_type);
		if ( target_market_id != null )
			map.put("target_market_id",target_market_id);
		if ( index_id != null )
			map.put("index_id",index_id);
		if ( product_type != null )
			map.put("product_type",product_type);
		if ( future_option_div != null )
			map.put("future_option_div",future_option_div);
		if ( standard_name != null )
			map.put("standard_name",standard_name);
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
		if ( underlying_product_code_is_modified )
			map.put("underlying_product_code",underlying_product_code);
		if ( derivative_type_is_modified )
			map.put("derivative_type",derivative_type);
		if ( strike_price_is_modified )
			map.put("strike_price",new Double(strike_price));
		if ( month_of_delivery_is_modified )
			map.put("month_of_delivery",month_of_delivery);
		if ( exercise_date_is_modified )
			map.put("exercise_date",exercise_date);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( split_type_is_modified )
			map.put("split_type",split_type);
		if ( target_market_id_is_modified )
			map.put("target_market_id",target_market_id);
		if ( index_id_is_modified )
			map.put("index_id",index_id);
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( future_option_div_is_modified )
			map.put("future_option_div",future_option_div);
		if ( standard_name_is_modified )
			map.put("standard_name",standard_name);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( underlying_product_code_is_set )
				map.put("underlying_product_code",underlying_product_code);
			if ( derivative_type_is_set )
				map.put("derivative_type",derivative_type);
			if ( strike_price_is_set )
				map.put("strike_price",new Double(strike_price));
			if ( month_of_delivery_is_set )
				map.put("month_of_delivery",month_of_delivery);
			map.put("exercise_date",exercise_date);
			map.put("product_code",product_code);
			map.put("split_type",split_type);
			map.put("target_market_id",target_market_id);
			map.put("index_id",index_id);
			map.put("product_type",product_type);
			map.put("future_option_div",future_option_div);
			map.put("standard_name",standard_name);
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
   * <em>underlying_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getUnderlyingProductCode()
  {
    return underlying_product_code;
  }


  /** 
   * <em>underlying_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUnderlyingProductCodeIsSet() {
    return underlying_product_code_is_set;
  }


  /** 
   * <em>underlying_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUnderlyingProductCodeIsModified() {
    return underlying_product_code_is_modified;
  }


  /** 
   * <em>derivative_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum getDerivativeType()
  {
    return derivative_type;
  }


  /** 
   * <em>derivative_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDerivativeTypeIsSet() {
    return derivative_type_is_set;
  }


  /** 
   * <em>derivative_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDerivativeTypeIsModified() {
    return derivative_type_is_modified;
  }


  /** 
   * <em>strike_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getStrikePrice()
  {
    return strike_price;
  }


  /** 
   * <em>strike_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStrikePriceIsSet() {
    return strike_price_is_set;
  }


  /** 
   * <em>strike_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStrikePriceIsModified() {
    return strike_price_is_modified;
  }


  /** 
   * <em>month_of_delivery</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMonthOfDelivery()
  {
    return month_of_delivery;
  }


  /** 
   * <em>month_of_delivery</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMonthOfDeliveryIsSet() {
    return month_of_delivery_is_set;
  }


  /** 
   * <em>month_of_delivery</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMonthOfDeliveryIsModified() {
    return month_of_delivery_is_modified;
  }


  /** 
   * <em>exercise_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getExerciseDate()
  {
    return exercise_date;
  }


  /** 
   * <em>exercise_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExerciseDateIsSet() {
    return exercise_date_is_set;
  }


  /** 
   * <em>exercise_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExerciseDateIsModified() {
    return exercise_date_is_modified;
  }


  /** 
   * <em>product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductCodeIsSet() {
    return product_code_is_set;
  }


  /** 
   * <em>product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductCodeIsModified() {
    return product_code_is_modified;
  }


  /** 
   * <em>split_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSplitType()
  {
    return split_type;
  }


  /** 
   * <em>split_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSplitTypeIsSet() {
    return split_type_is_set;
  }


  /** 
   * <em>split_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSplitTypeIsModified() {
    return split_type_is_modified;
  }


  /** 
   * <em>target_market_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getTargetMarketId()
  {
    return ( target_market_id==null? ((long)0): target_market_id.longValue() );
  }


  /** 
   * <em>target_market_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTargetMarketIdIsNull()
  {
    return target_market_id == null;
  }


  /** 
   * <em>target_market_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTargetMarketIdIsSet() {
    return target_market_id_is_set;
  }


  /** 
   * <em>target_market_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTargetMarketIdIsModified() {
    return target_market_id_is_modified;
  }


  /** 
   * <em>index_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getIndexId()
  {
    return ( index_id==null? ((long)0): index_id.longValue() );
  }


  /** 
   * <em>index_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getIndexIdIsNull()
  {
    return index_id == null;
  }


  /** 
   * <em>index_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIndexIdIsSet() {
    return index_id_is_set;
  }


  /** 
   * <em>index_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIndexIdIsModified() {
    return index_id_is_modified;
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
   * <em>future_option_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFutureOptionDiv()
  {
    return future_option_div;
  }


  /** 
   * <em>future_option_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFutureOptionDivIsSet() {
    return future_option_div_is_set;
  }


  /** 
   * <em>future_option_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFutureOptionDivIsModified() {
    return future_option_div_is_modified;
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
    return new IfoProductPK(product_id);
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
   * <em>underlying_product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_underlyingProductCode <em>underlying_product_code</em>�J�����̒l������킷9���ȉ���String�l 
   */
  public final void setUnderlyingProductCode( String p_underlyingProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    underlying_product_code = p_underlyingProductCode;
    underlying_product_code_is_set = true;
    underlying_product_code_is_modified = true;
  }


  /** 
   * <em>derivative_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_derivativeType <em>derivative_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum�l 
   */
  public final void setDerivativeType( com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum p_derivativeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    derivative_type = p_derivativeType;
    derivative_type_is_set = true;
    derivative_type_is_modified = true;
  }


  /** 
   * <em>strike_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_strikePrice <em>strike_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setStrikePrice( double p_strikePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    strike_price = p_strikePrice;
    strike_price_is_set = true;
    strike_price_is_modified = true;
  }


  /** 
   * <em>month_of_delivery</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_monthOfDelivery <em>month_of_delivery</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setMonthOfDelivery( String p_monthOfDelivery )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    month_of_delivery = p_monthOfDelivery;
    month_of_delivery_is_set = true;
    month_of_delivery_is_modified = true;
  }


  /** 
   * <em>exercise_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_exerciseDate <em>exercise_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setExerciseDate( java.sql.Timestamp p_exerciseDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exercise_date = p_exerciseDate;
    exercise_date_is_set = true;
    exercise_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>exercise_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setExerciseDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exercise_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    exercise_date_is_set = true;
    exercise_date_is_modified = true;
  }


  /** 
   * <em>product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷9���ȉ���String�l 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>split_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_splitType <em>split_type</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setSplitType( String p_splitType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    split_type = p_splitType;
    split_type_is_set = true;
    split_type_is_modified = true;
  }


  /** 
   * <em>target_market_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_targetMarketId <em>target_market_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setTargetMarketId( long p_targetMarketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    target_market_id = new Long(p_targetMarketId);
    target_market_id_is_set = true;
    target_market_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>target_market_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTargetMarketId( Long p_targetMarketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    target_market_id = p_targetMarketId;
    target_market_id_is_set = true;
    target_market_id_is_modified = true;
  }


  /** 
   * <em>index_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_indexId <em>index_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setIndexId( long p_indexId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    index_id = new Long(p_indexId);
    index_id_is_set = true;
    index_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>index_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setIndexId( Long p_indexId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    index_id = p_indexId;
    index_id_is_set = true;
    index_id_is_modified = true;
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
   * <em>future_option_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_futureOptionDiv <em>future_option_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setFutureOptionDiv( String p_futureOptionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    future_option_div = p_futureOptionDiv;
    future_option_div_is_set = true;
    future_option_div_is_modified = true;
  }


  /** 
   * <em>standard_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_standardName <em>standard_name</em>�J�����̒l������킷50���ȉ���String�l 
   */
  public final void setStandardName( String p_standardName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    standard_name = p_standardName;
    standard_name_is_set = true;
    standard_name_is_modified = true;
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
            case 'd':
                if ( name.equals("derivative_type") ) {
                    return this.derivative_type;
                }
                break;
            case 'e':
                if ( name.equals("exercise_date") ) {
                    return this.exercise_date;
                }
                break;
            case 'f':
                if ( name.equals("future_option_div") ) {
                    return this.future_option_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("index_id") ) {
                    return this.index_id;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("month_of_delivery") ) {
                    return this.month_of_delivery;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                break;
            case 's':
                if ( name.equals("strike_price") ) {
                    return new Double( strike_price );
                }
                else if ( name.equals("split_type") ) {
                    return this.split_type;
                }
                else if ( name.equals("standard_name") ) {
                    return this.standard_name;
                }
                break;
            case 't':
                if ( name.equals("target_market_id") ) {
                    return this.target_market_id;
                }
                break;
            case 'u':
                if ( name.equals("underlying_product_code") ) {
                    return this.underlying_product_code;
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
            case 'd':
                if ( name.equals("derivative_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum) )
                        throw new IllegalArgumentException( "value for 'derivative_type' must be com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum: '"+value+"' is not." );
                    this.derivative_type = (com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum) value;
                    if (this.derivative_type_is_set)
                        this.derivative_type_is_modified = true;
                    this.derivative_type_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("exercise_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'exercise_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.exercise_date = (java.sql.Timestamp) value;
                    if (this.exercise_date_is_set)
                        this.exercise_date_is_modified = true;
                    this.exercise_date_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("future_option_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'future_option_div' must be String: '"+value+"' is not." );
                    this.future_option_div = (String) value;
                    if (this.future_option_div_is_set)
                        this.future_option_div_is_modified = true;
                    this.future_option_div_is_set = true;
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
                else if ( name.equals("index_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'index_id' must be Long: '"+value+"' is not." );
                    this.index_id = (Long) value;
                    if (this.index_id_is_set)
                        this.index_id_is_modified = true;
                    this.index_id_is_set = true;
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
                if ( name.equals("month_of_delivery") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'month_of_delivery' must be String: '"+value+"' is not." );
                    this.month_of_delivery = (String) value;
                    if (this.month_of_delivery_is_set)
                        this.month_of_delivery_is_modified = true;
                    this.month_of_delivery_is_set = true;
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
                else if ( name.equals("product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                else if ( name.equals("product_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("strike_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'strike_price' must be Double: '"+value+"' is not." );
                    this.strike_price = ((Double) value).doubleValue();
                    if (this.strike_price_is_set)
                        this.strike_price_is_modified = true;
                    this.strike_price_is_set = true;
                    return;
                }
                else if ( name.equals("split_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'split_type' must be String: '"+value+"' is not." );
                    this.split_type = (String) value;
                    if (this.split_type_is_set)
                        this.split_type_is_modified = true;
                    this.split_type_is_set = true;
                    return;
                }
                else if ( name.equals("standard_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'standard_name' must be String: '"+value+"' is not." );
                    this.standard_name = (String) value;
                    if (this.standard_name_is_set)
                        this.standard_name_is_modified = true;
                    this.standard_name_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("target_market_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'target_market_id' must be Long: '"+value+"' is not." );
                    this.target_market_id = (Long) value;
                    if (this.target_market_id_is_set)
                        this.target_market_id_is_modified = true;
                    this.target_market_id_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("underlying_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'underlying_product_code' must be String: '"+value+"' is not." );
                    this.underlying_product_code = (String) value;
                    if (this.underlying_product_code_is_set)
                        this.underlying_product_code_is_modified = true;
                    this.underlying_product_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
