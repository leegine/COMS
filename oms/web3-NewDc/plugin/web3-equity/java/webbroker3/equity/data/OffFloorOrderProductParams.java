head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OffFloorOrderProductParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * off_floor_order_product�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link OffFloorOrderProductRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link OffFloorOrderProductParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link OffFloorOrderProductParams}��{@@link OffFloorOrderProductRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OffFloorOrderProductPK 
 * @@see OffFloorOrderProductRow 
 */
public class OffFloorOrderProductParams extends Params implements OffFloorOrderProductRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "off_floor_order_product";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = OffFloorOrderProductRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return OffFloorOrderProductRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>market_code</em>�J�����̒l 
   */
  public  String  market_code;

  /** 
   * <em>order_end_datetime</em>�J�����̒l 
   */
  public  java.sql.Timestamp  order_end_datetime;

  /** 
   * <em>order_start_datetime</em>�J�����̒l 
   */
  public  java.sql.Timestamp  order_start_datetime;

  /** 
   * <em>max_apply_quantity</em>�J�����̒l 
   */
  public  Double  max_apply_quantity;

  /** 
   * <em>off_floor_order_price</em>�J�����̒l 
   */
  public  Double  off_floor_order_price;

  /** 
   * <em>last_closing_price</em>�J�����̒l 
   */
  public  Double  last_closing_price;

  /** 
   * <em>daily_delivery_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  daily_delivery_date;

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
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean order_start_datetime_is_set = false;
  private boolean order_start_datetime_is_modified = false;
  private boolean order_end_datetime_is_set = false;
  private boolean order_end_datetime_is_modified = false;
  private boolean max_apply_quantity_is_set = false;
  private boolean max_apply_quantity_is_modified = false;
  private boolean off_floor_order_price_is_set = false;
  private boolean off_floor_order_price_is_modified = false;
  private boolean last_closing_price_is_set = false;
  private boolean last_closing_price_is_modified = false;
  private boolean daily_delivery_date_is_set = false;
  private boolean daily_delivery_date_is_modified = false;
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
      + "," + "product_code=" + product_code
      + "," + "market_code=" + market_code
      + "," + "order_end_datetime=" + order_end_datetime
      + "," + "order_start_datetime=" +order_start_datetime
      + "," + "max_apply_quantity=" +max_apply_quantity
      + "," + "off_floor_order_price=" +off_floor_order_price
      + "," + "last_closing_price=" +last_closing_price
      + "," + "daily_delivery_date=" +daily_delivery_date
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��OffFloorOrderProductParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public OffFloorOrderProductParams() {
    institution_code_is_modified = true;
    product_code_is_modified = true;
    market_code_is_modified = true;
    order_end_datetime_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���OffFloorOrderProductRow�I�u�W�F�N�g�̒l�𗘗p����OffFloorOrderProductParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����OffFloorOrderProductRow�I�u�W�F�N�g 
   */
  public OffFloorOrderProductParams( OffFloorOrderProductRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    order_end_datetime = p_row.getOrderEndDatetime();
    order_end_datetime_is_set = p_row.getOrderEndDatetimeIsSet();
    order_end_datetime_is_modified = p_row.getOrderEndDatetimeIsModified();
    order_start_datetime = p_row.getOrderStartDatetime();
    order_start_datetime_is_set = p_row.getOrderStartDatetimeIsSet();
    order_start_datetime_is_modified = p_row.getOrderStartDatetimeIsModified();
    if ( !p_row.getMaxApplyQuantityIsNull() )
      max_apply_quantity = new Double( p_row.getMaxApplyQuantity() );
    max_apply_quantity_is_set = p_row.getMaxApplyQuantityIsSet();
    max_apply_quantity_is_modified = p_row.getMaxApplyQuantityIsModified();
    if ( !p_row.getOffFloorOrderPriceIsNull() )
      off_floor_order_price = new Double( p_row.getOffFloorOrderPrice() );
    off_floor_order_price_is_set = p_row.getOffFloorOrderPriceIsSet();
    off_floor_order_price_is_modified = p_row.getOffFloorOrderPriceIsModified();
    if ( !p_row.getLastClosingPriceIsNull() )
      last_closing_price = new Double( p_row.getLastClosingPrice() );
    last_closing_price_is_set = p_row.getLastClosingPriceIsSet();
    last_closing_price_is_modified = p_row.getLastClosingPriceIsModified();
    daily_delivery_date = p_row.getDailyDeliveryDate();
    daily_delivery_date_is_set = p_row.getDailyDeliveryDateIsSet();
    daily_delivery_date_is_modified = p_row.getDailyDeliveryDateIsModified();
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
      order_start_datetime_is_set = true;
      order_start_datetime_is_modified = true;
      max_apply_quantity_is_set = true;
      max_apply_quantity_is_modified = true;
      off_floor_order_price_is_set = true;
      off_floor_order_price_is_modified = true;
      last_closing_price_is_set = true;
      last_closing_price_is_modified = true;
      daily_delivery_date_is_set = true;
      daily_delivery_date_is_modified = true;
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
    if ( !( other instanceof OffFloorOrderProductRow ) )
       return false;
    return fieldsEqual( (OffFloorOrderProductRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�OffFloorOrderProductRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( OffFloorOrderProductRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( order_start_datetime == null ) {
      if ( row.getOrderStartDatetime() != null )
        return false;
    } else if ( !order_start_datetime.equals( row.getOrderStartDatetime() ) ) {
        return false;
    }
    if ( order_end_datetime == null ) {
      if ( row.getOrderEndDatetime() != null )
        return false;
    } else if ( !order_end_datetime.equals( row.getOrderEndDatetime() ) ) {
        return false;
    }
    if ( max_apply_quantity == null ) {
      if ( !row.getMaxApplyQuantityIsNull() )
        return false;
    } else if ( row.getMaxApplyQuantityIsNull() || ( max_apply_quantity.doubleValue() != row.getMaxApplyQuantity() ) ) {
        return false;
    }
    if ( off_floor_order_price == null ) {
      if ( !row.getOffFloorOrderPriceIsNull() )
        return false;
    } else if ( row.getOffFloorOrderPriceIsNull() || ( off_floor_order_price.doubleValue() != row.getOffFloorOrderPrice() ) ) {
        return false;
    }
    if ( last_closing_price == null ) {
      if ( !row.getLastClosingPriceIsNull() )
        return false;
    } else if ( row.getLastClosingPriceIsNull() || ( last_closing_price.doubleValue() != row.getLastClosingPrice() ) ) {
        return false;
    }
    if ( daily_delivery_date == null ) {
      if ( row.getDailyDeliveryDate() != null )
        return false;
    } else if ( !daily_delivery_date.equals( row.getDailyDeliveryDate() ) ) {
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
        + (product_code!=null? product_code.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (order_start_datetime!=null? order_start_datetime.hashCode(): 0) 
        + (order_end_datetime!=null? order_end_datetime.hashCode(): 0) 
        + (max_apply_quantity!=null? max_apply_quantity.hashCode(): 0) 
        + (off_floor_order_price!=null? off_floor_order_price.hashCode(): 0) 
        + (last_closing_price!=null? last_closing_price.hashCode(): 0) 
        + (daily_delivery_date!=null? daily_delivery_date.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !order_start_datetime_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_start_datetime' must be set before inserting.");
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
		map.put("product_code",product_code);
		map.put("market_code",market_code);
		map.put("order_start_datetime",order_start_datetime);
		map.put("order_end_datetime",order_end_datetime);
		if ( max_apply_quantity != null )
			map.put("max_apply_quantity",max_apply_quantity);
		if ( off_floor_order_price != null )
			map.put("off_floor_order_price",off_floor_order_price);
		if ( last_closing_price != null )
			map.put("last_closing_price",last_closing_price);
		if ( daily_delivery_date != null )
			map.put("daily_delivery_date",daily_delivery_date);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( order_start_datetime_is_modified )
			map.put("order_start_datetime",order_start_datetime);
		if ( max_apply_quantity_is_modified )
			map.put("max_apply_quantity",max_apply_quantity);
		if ( off_floor_order_price_is_modified )
			map.put("off_floor_order_price",off_floor_order_price);
		if ( last_closing_price_is_modified )
			map.put("last_closing_price",last_closing_price);
		if ( daily_delivery_date_is_modified )
			map.put("daily_delivery_date",daily_delivery_date);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( order_start_datetime_is_set )
				map.put("order_start_datetime",order_start_datetime);
			map.put("max_apply_quantity",max_apply_quantity);
			map.put("off_floor_order_price",off_floor_order_price);
			map.put("last_closing_price",last_closing_price);
			map.put("daily_delivery_date",daily_delivery_date);
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
   * <em>order_start_datetime</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getOrderStartDatetime()
  {
    return order_start_datetime;
  }


  /** 
   * <em>order_start_datetime</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderStartDatetimeIsSet() {
    return order_start_datetime_is_set;
  }


  /** 
   * <em>order_start_datetime</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderStartDatetimeIsModified() {
    return order_start_datetime_is_modified;
  }


  /** 
   * <em>order_end_datetime</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getOrderEndDatetime()
  {
    return order_end_datetime;
  }


  /** 
   * <em>order_end_datetime</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderEndDatetimeIsSet() {
    return order_end_datetime_is_set;
  }


  /** 
   * <em>order_end_datetime</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderEndDatetimeIsModified() {
    return order_end_datetime_is_modified;
  }


  /** 
   * <em>max_apply_quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getMaxApplyQuantity()
  {
    return ( max_apply_quantity==null? ((double)0): max_apply_quantity.doubleValue() );
  }


  /** 
   * <em>max_apply_quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getMaxApplyQuantityIsNull()
  {
    return max_apply_quantity == null;
  }


  /** 
   * <em>max_apply_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMaxApplyQuantityIsSet() {
    return max_apply_quantity_is_set;
  }


  /** 
   * <em>max_apply_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMaxApplyQuantityIsModified() {
    return max_apply_quantity_is_modified;
  }


  /** 
   * <em>off_floor_order_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getOffFloorOrderPrice()
  {
    return ( off_floor_order_price==null? ((double)0): off_floor_order_price.doubleValue() );
  }


  /** 
   * <em>off_floor_order_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOffFloorOrderPriceIsNull()
  {
    return off_floor_order_price == null;
  }


  /** 
   * <em>off_floor_order_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOffFloorOrderPriceIsSet() {
    return off_floor_order_price_is_set;
  }


  /** 
   * <em>off_floor_order_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOffFloorOrderPriceIsModified() {
    return off_floor_order_price_is_modified;
  }


  /** 
   * <em>last_closing_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getLastClosingPrice()
  {
    return ( last_closing_price==null? ((double)0): last_closing_price.doubleValue() );
  }


  /** 
   * <em>last_closing_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getLastClosingPriceIsNull()
  {
    return last_closing_price == null;
  }


  /** 
   * <em>last_closing_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastClosingPriceIsSet() {
    return last_closing_price_is_set;
  }


  /** 
   * <em>last_closing_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastClosingPriceIsModified() {
    return last_closing_price_is_modified;
  }


  /** 
   * <em>daily_delivery_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getDailyDeliveryDate()
  {
    return daily_delivery_date;
  }


  /** 
   * <em>daily_delivery_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDailyDeliveryDateIsSet() {
    return daily_delivery_date_is_set;
  }


  /** 
   * <em>daily_delivery_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDailyDeliveryDateIsModified() {
    return daily_delivery_date_is_modified;
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
    return new OffFloorOrderProductPK(institution_code, product_code, market_code, order_end_datetime);
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
   * <em>product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
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
   * <em>order_start_datetime</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderStartDatetime <em>order_start_datetime</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setOrderStartDatetime( java.sql.Timestamp p_orderStartDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_start_datetime = p_orderStartDatetime;
    order_start_datetime_is_set = true;
    order_start_datetime_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>order_start_datetime</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setOrderStartDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_start_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_start_datetime_is_set = true;
    order_start_datetime_is_modified = true;
  }


  /** 
   * <em>order_end_datetime</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderEndDatetime <em>order_end_datetime</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setOrderEndDatetime( java.sql.Timestamp p_orderEndDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_end_datetime = p_orderEndDatetime;
    order_end_datetime_is_set = true;
    order_end_datetime_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>order_end_datetime</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setOrderEndDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_end_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_end_datetime_is_set = true;
    order_end_datetime_is_modified = true;
  }


  /** 
   * <em>max_apply_quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_maxApplyQuantity <em>max_apply_quantity</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setMaxApplyQuantity( double p_maxApplyQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_apply_quantity = new Double(p_maxApplyQuantity);
    max_apply_quantity_is_set = true;
    max_apply_quantity_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>max_apply_quantity</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setMaxApplyQuantity( Double p_maxApplyQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_apply_quantity = p_maxApplyQuantity;
    max_apply_quantity_is_set = true;
    max_apply_quantity_is_modified = true;
  }


  /** 
   * <em>off_floor_order_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_offFloorOrderPrice <em>off_floor_order_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setOffFloorOrderPrice( double p_offFloorOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    off_floor_order_price = new Double(p_offFloorOrderPrice);
    off_floor_order_price_is_set = true;
    off_floor_order_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>off_floor_order_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOffFloorOrderPrice( Double p_offFloorOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    off_floor_order_price = p_offFloorOrderPrice;
    off_floor_order_price_is_set = true;
    off_floor_order_price_is_modified = true;
  }


  /** 
   * <em>last_closing_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastClosingPrice <em>last_closing_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setLastClosingPrice( double p_lastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_closing_price = new Double(p_lastClosingPrice);
    last_closing_price_is_set = true;
    last_closing_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>last_closing_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setLastClosingPrice( Double p_lastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_closing_price = p_lastClosingPrice;
    last_closing_price_is_set = true;
    last_closing_price_is_modified = true;
  }


  /** 
   * <em>daily_delivery_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_dailyDeliveryDate <em>daily_delivery_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setDailyDeliveryDate( java.sql.Timestamp p_dailyDeliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    daily_delivery_date = p_dailyDeliveryDate;
    daily_delivery_date_is_set = true;
    daily_delivery_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>daily_delivery_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setDailyDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    daily_delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    daily_delivery_date_is_set = true;
    daily_delivery_date_is_modified = true;
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
                if ( name.equals("daily_delivery_date") ) {
                    return this.daily_delivery_date;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_closing_price") ) {
                    return this.last_closing_price;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                else if ( name.equals("max_apply_quantity") ) {
                    return this.max_apply_quantity;
                }
                break;
            case 'o':
                if ( name.equals("order_start_datetime") ) {
                    return this.order_start_datetime;
                }
                else if ( name.equals("order_end_datetime") ) {
                    return this.order_end_datetime;
                }
                else if ( name.equals("off_floor_order_price") ) {
                    return this.off_floor_order_price;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
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
                if ( name.equals("daily_delivery_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'daily_delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.daily_delivery_date = (java.sql.Timestamp) value;
                    if (this.daily_delivery_date_is_set)
                        this.daily_delivery_date_is_modified = true;
                    this.daily_delivery_date_is_set = true;
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
                if ( name.equals("last_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'last_closing_price' must be Double: '"+value+"' is not." );
                    this.last_closing_price = (Double) value;
                    if (this.last_closing_price_is_set)
                        this.last_closing_price_is_modified = true;
                    this.last_closing_price_is_set = true;
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
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                else if ( name.equals("max_apply_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_apply_quantity' must be Double: '"+value+"' is not." );
                    this.max_apply_quantity = (Double) value;
                    if (this.max_apply_quantity_is_set)
                        this.max_apply_quantity_is_modified = true;
                    this.max_apply_quantity_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_start_datetime") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_start_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_start_datetime = (java.sql.Timestamp) value;
                    if (this.order_start_datetime_is_set)
                        this.order_start_datetime_is_modified = true;
                    this.order_start_datetime_is_set = true;
                    return;
                }
                else if ( name.equals("order_end_datetime") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_end_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_end_datetime = (java.sql.Timestamp) value;
                    if (this.order_end_datetime_is_set)
                        this.order_end_datetime_is_modified = true;
                    this.order_end_datetime_is_set = true;
                    return;
                }
                else if ( name.equals("off_floor_order_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'off_floor_order_price' must be Double: '"+value+"' is not." );
                    this.off_floor_order_price = (Double) value;
                    if (this.off_floor_order_price_is_set)
                        this.off_floor_order_price_is_modified = true;
                    this.off_floor_order_price_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
