head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.38.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AssetUnitParams.java;


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
 * asset_unit�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link AssetUnitRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link AssetUnitParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link AssetUnitParams}��{@@link AssetUnitRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AssetUnitPK 
 * @@see AssetUnitRow 
 */
public class AssetUnitParams extends Params implements AssetUnitRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "asset_unit";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = AssetUnitRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return AssetUnitRow.TYPE;
  }


  /** 
   * <em>asset_unit_id</em>�J�����̒l 
   */
  public  long  asset_unit_id;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>�J�����̒l 
   */
  public  long  sub_account_id;

  /** 
   * <em>product_id</em>�J�����̒l 
   */
  public  long  product_id;

  /** 
   * <em>asset_id</em>�J�����̒l 
   */
  public  long  asset_id;

  /** 
   * <em>product_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>market_id</em>�J�����̒l 
   */
  public  long  market_id;

  /** 
   * <em>original_quantity</em>�J�����̒l 
   */
  public  double  original_quantity;

  /** 
   * <em>quantity</em>�J�����̒l 
   */
  public  double  quantity;

  /** 
   * <em>acquired_price</em>�J�����̒l 
   */
  public  double  acquired_price;

  /** 
   * <em>acquired_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  acquired_timestamp;

  /** 
   * <em>delivery_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>accrued_interest_price</em>�J�����̒l 
   */
  public  long  accrued_interest_price;

  /** 
   * <em>tax_type</em>�J�����̒l 
   */
  public  int  tax_type;

  /** 
   * <em>custody_type</em>�J�����̒l 
   */
  public  String  custody_type;

  /** 
   * <em>evaluation_price</em>�J�����̒l 
   */
  public  Double  evaluation_price;

  /** 
   * <em>collateral_ratio</em>�J�����̒l 
   */
  public  Double  collateral_ratio;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean asset_unit_id_is_set = false;
  private boolean asset_unit_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean asset_id_is_set = false;
  private boolean asset_id_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean original_quantity_is_set = false;
  private boolean original_quantity_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean acquired_price_is_set = false;
  private boolean acquired_price_is_modified = false;
  private boolean acquired_timestamp_is_set = false;
  private boolean acquired_timestamp_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean accrued_interest_price_is_set = false;
  private boolean accrued_interest_price_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean custody_type_is_set = false;
  private boolean custody_type_is_modified = false;
  private boolean evaluation_price_is_set = false;
  private boolean evaluation_price_is_modified = false;
  private boolean collateral_ratio_is_set = false;
  private boolean collateral_ratio_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "asset_unit_id=" + asset_unit_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "product_id=" +product_id
      + "," + "asset_id=" +asset_id
      + "," + "product_type=" +product_type
      + "," + "market_id=" +market_id
      + "," + "original_quantity=" +original_quantity
      + "," + "quantity=" +quantity
      + "," + "acquired_price=" +acquired_price
      + "," + "acquired_timestamp=" +acquired_timestamp
      + "," + "delivery_date=" +delivery_date
      + "," + "accrued_interest_price=" +accrued_interest_price
      + "," + "tax_type=" +tax_type
      + "," + "custody_type=" +custody_type
      + "," + "evaluation_price=" +evaluation_price
      + "," + "collateral_ratio=" +collateral_ratio
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��AssetUnitParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public AssetUnitParams() {
    asset_unit_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���AssetUnitRow�I�u�W�F�N�g�̒l�𗘗p����AssetUnitParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����AssetUnitRow�I�u�W�F�N�g 
   */
  public AssetUnitParams( AssetUnitRow p_row )
  {
    asset_unit_id = p_row.getAssetUnitId();
    asset_unit_id_is_set = p_row.getAssetUnitIdIsSet();
    asset_unit_id_is_modified = p_row.getAssetUnitIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    asset_id = p_row.getAssetId();
    asset_id_is_set = p_row.getAssetIdIsSet();
    asset_id_is_modified = p_row.getAssetIdIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    original_quantity = p_row.getOriginalQuantity();
    original_quantity_is_set = p_row.getOriginalQuantityIsSet();
    original_quantity_is_modified = p_row.getOriginalQuantityIsModified();
    quantity = p_row.getQuantity();
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    acquired_price = p_row.getAcquiredPrice();
    acquired_price_is_set = p_row.getAcquiredPriceIsSet();
    acquired_price_is_modified = p_row.getAcquiredPriceIsModified();
    acquired_timestamp = p_row.getAcquiredTimestamp();
    acquired_timestamp_is_set = p_row.getAcquiredTimestampIsSet();
    acquired_timestamp_is_modified = p_row.getAcquiredTimestampIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    accrued_interest_price = p_row.getAccruedInterestPrice();
    accrued_interest_price_is_set = p_row.getAccruedInterestPriceIsSet();
    accrued_interest_price_is_modified = p_row.getAccruedInterestPriceIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    custody_type = p_row.getCustodyType();
    custody_type_is_set = p_row.getCustodyTypeIsSet();
    custody_type_is_modified = p_row.getCustodyTypeIsModified();
    if ( !p_row.getEvaluationPriceIsNull() )
      evaluation_price = new Double( p_row.getEvaluationPrice() );
    evaluation_price_is_set = p_row.getEvaluationPriceIsSet();
    evaluation_price_is_modified = p_row.getEvaluationPriceIsModified();
    if ( !p_row.getCollateralRatioIsNull() )
      collateral_ratio = new Double( p_row.getCollateralRatio() );
    collateral_ratio_is_set = p_row.getCollateralRatioIsSet();
    collateral_ratio_is_modified = p_row.getCollateralRatioIsModified();
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
      account_id_is_set = true;
      account_id_is_modified = true;
      sub_account_id_is_set = true;
      sub_account_id_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      asset_id_is_set = true;
      asset_id_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      original_quantity_is_set = true;
      original_quantity_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      acquired_price_is_set = true;
      acquired_price_is_modified = true;
      acquired_timestamp_is_set = true;
      acquired_timestamp_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      accrued_interest_price_is_set = true;
      accrued_interest_price_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      custody_type_is_set = true;
      custody_type_is_modified = true;
      evaluation_price_is_set = true;
      evaluation_price_is_modified = true;
      collateral_ratio_is_set = true;
      collateral_ratio_is_modified = true;
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
    if ( !( other instanceof AssetUnitRow ) )
       return false;
    return fieldsEqual( (AssetUnitRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�AssetUnitRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( AssetUnitRow row )
  {
    if ( asset_unit_id != row.getAssetUnitId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( product_id != row.getProductId() )
      return false;
    if ( asset_id != row.getAssetId() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( market_id != row.getMarketId() )
      return false;
    if ( original_quantity != row.getOriginalQuantity() )
      return false;
    if ( quantity != row.getQuantity() )
      return false;
    if ( acquired_price != row.getAcquiredPrice() )
      return false;
    if ( acquired_timestamp == null ) {
      if ( row.getAcquiredTimestamp() != null )
        return false;
    } else if ( !acquired_timestamp.equals( row.getAcquiredTimestamp() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( accrued_interest_price != row.getAccruedInterestPrice() )
      return false;
    if ( tax_type != row.getTaxType() )
      return false;
    if ( custody_type == null ) {
      if ( row.getCustodyType() != null )
        return false;
    } else if ( !custody_type.equals( row.getCustodyType() ) ) {
        return false;
    }
    if ( evaluation_price == null ) {
      if ( !row.getEvaluationPriceIsNull() )
        return false;
    } else if ( row.getEvaluationPriceIsNull() || ( evaluation_price.doubleValue() != row.getEvaluationPrice() ) ) {
        return false;
    }
    if ( collateral_ratio == null ) {
      if ( !row.getCollateralRatioIsNull() )
        return false;
    } else if ( row.getCollateralRatioIsNull() || ( collateral_ratio.doubleValue() != row.getCollateralRatio() ) ) {
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
      return  ((int) asset_unit_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) product_id)
        + ((int) asset_id)
        + (product_type!=null? product_type.hashCode(): 0) 
        + ((int) market_id)
        + ((int) original_quantity)
        + ((int) quantity)
        + ((int) acquired_price)
        + (acquired_timestamp!=null? acquired_timestamp.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + ((int) accrued_interest_price)
        + ((int) tax_type)
        + (custody_type!=null? custody_type.hashCode(): 0) 
        + (evaluation_price!=null? evaluation_price.hashCode(): 0) 
        + (collateral_ratio!=null? collateral_ratio.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !sub_account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'sub_account_id' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !asset_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'asset_id' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !market_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_id' must be set before inserting.");
		if ( !original_quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'original_quantity' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
		if ( !acquired_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'acquired_price' must be set before inserting.");
		if ( !acquired_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'acquired_timestamp' must be set before inserting.");
		if ( !delivery_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_date' must be set before inserting.");
		if ( !accrued_interest_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'accrued_interest_price' must be set before inserting.");
		if ( !tax_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'tax_type' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("asset_unit_id",new Long(asset_unit_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("product_id",new Long(product_id));
		map.put("asset_id",new Long(asset_id));
		map.put("product_type",product_type);
		map.put("market_id",new Long(market_id));
		map.put("original_quantity",new Double(original_quantity));
		map.put("quantity",new Double(quantity));
		map.put("acquired_price",new Double(acquired_price));
		map.put("acquired_timestamp",acquired_timestamp);
		map.put("delivery_date",delivery_date);
		map.put("accrued_interest_price",new Long(accrued_interest_price));
		map.put("tax_type",new Integer(tax_type));
		if ( custody_type != null )
			map.put("custody_type",custody_type);
		if ( evaluation_price != null )
			map.put("evaluation_price",evaluation_price);
		if ( collateral_ratio != null )
			map.put("collateral_ratio",collateral_ratio);
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
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( sub_account_id_is_modified )
			map.put("sub_account_id",new Long(sub_account_id));
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( asset_id_is_modified )
			map.put("asset_id",new Long(asset_id));
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if ( original_quantity_is_modified )
			map.put("original_quantity",new Double(original_quantity));
		if ( quantity_is_modified )
			map.put("quantity",new Double(quantity));
		if ( acquired_price_is_modified )
			map.put("acquired_price",new Double(acquired_price));
		if ( acquired_timestamp_is_modified )
			map.put("acquired_timestamp",acquired_timestamp);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( accrued_interest_price_is_modified )
			map.put("accrued_interest_price",new Long(accrued_interest_price));
		if ( tax_type_is_modified )
			map.put("tax_type",new Integer(tax_type));
		if ( custody_type_is_modified )
			map.put("custody_type",custody_type);
		if ( evaluation_price_is_modified )
			map.put("evaluation_price",evaluation_price);
		if ( collateral_ratio_is_modified )
			map.put("collateral_ratio",collateral_ratio);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( asset_id_is_set )
				map.put("asset_id",new Long(asset_id));
			if ( product_type_is_set )
				map.put("product_type",product_type);
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
			if ( original_quantity_is_set )
				map.put("original_quantity",new Double(original_quantity));
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			if ( acquired_price_is_set )
				map.put("acquired_price",new Double(acquired_price));
			if ( acquired_timestamp_is_set )
				map.put("acquired_timestamp",acquired_timestamp);
			if ( delivery_date_is_set )
				map.put("delivery_date",delivery_date);
			if ( accrued_interest_price_is_set )
				map.put("accrued_interest_price",new Long(accrued_interest_price));
			if ( tax_type_is_set )
				map.put("tax_type",new Integer(tax_type));
			map.put("custody_type",custody_type);
			map.put("evaluation_price",evaluation_price);
			map.put("collateral_ratio",collateral_ratio);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>asset_unit_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAssetUnitId()
  {
    return asset_unit_id;
  }


  /** 
   * <em>asset_unit_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAssetUnitIdIsSet() {
    return asset_unit_id_is_set;
  }


  /** 
   * <em>asset_unit_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAssetUnitIdIsModified() {
    return asset_unit_id_is_modified;
  }


  /** 
   * <em>account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
  }


  /** 
   * <em>sub_account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSubAccountId()
  {
    return sub_account_id;
  }


  /** 
   * <em>sub_account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubAccountIdIsSet() {
    return sub_account_id_is_set;
  }


  /** 
   * <em>sub_account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubAccountIdIsModified() {
    return sub_account_id_is_modified;
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
   * <em>asset_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAssetId()
  {
    return asset_id;
  }


  /** 
   * <em>asset_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAssetIdIsSet() {
    return asset_id_is_set;
  }


  /** 
   * <em>asset_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAssetIdIsModified() {
    return asset_id_is_modified;
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
   * <em>original_quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getOriginalQuantity()
  {
    return original_quantity;
  }


  /** 
   * <em>original_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOriginalQuantityIsSet() {
    return original_quantity_is_set;
  }


  /** 
   * <em>original_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOriginalQuantityIsModified() {
    return original_quantity_is_modified;
  }


  /** 
   * <em>quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getQuantity()
  {
    return quantity;
  }


  /** 
   * <em>quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityIsSet() {
    return quantity_is_set;
  }


  /** 
   * <em>quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityIsModified() {
    return quantity_is_modified;
  }


  /** 
   * <em>acquired_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAcquiredPrice()
  {
    return acquired_price;
  }


  /** 
   * <em>acquired_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAcquiredPriceIsSet() {
    return acquired_price_is_set;
  }


  /** 
   * <em>acquired_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAcquiredPriceIsModified() {
    return acquired_price_is_modified;
  }


  /** 
   * <em>acquired_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAcquiredTimestamp()
  {
    return acquired_timestamp;
  }


  /** 
   * <em>acquired_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAcquiredTimestampIsSet() {
    return acquired_timestamp_is_set;
  }


  /** 
   * <em>acquired_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAcquiredTimestampIsModified() {
    return acquired_timestamp_is_modified;
  }


  /** 
   * <em>delivery_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getDeliveryDate()
  {
    return delivery_date;
  }


  /** 
   * <em>delivery_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDeliveryDateIsSet() {
    return delivery_date_is_set;
  }


  /** 
   * <em>delivery_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDeliveryDateIsModified() {
    return delivery_date_is_modified;
  }


  /** 
   * <em>accrued_interest_price</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAccruedInterestPrice()
  {
    return accrued_interest_price;
  }


  /** 
   * <em>accrued_interest_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccruedInterestPriceIsSet() {
    return accrued_interest_price_is_set;
  }


  /** 
   * <em>accrued_interest_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccruedInterestPriceIsModified() {
    return accrued_interest_price_is_modified;
  }


  /** 
   * <em>tax_type</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getTaxType()
  {
    return tax_type;
  }


  /** 
   * <em>tax_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTaxTypeIsSet() {
    return tax_type_is_set;
  }


  /** 
   * <em>tax_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTaxTypeIsModified() {
    return tax_type_is_modified;
  }


  /** 
   * <em>custody_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCustodyType()
  {
    return custody_type;
  }


  /** 
   * <em>custody_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCustodyTypeIsSet() {
    return custody_type_is_set;
  }


  /** 
   * <em>custody_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCustodyTypeIsModified() {
    return custody_type_is_modified;
  }


  /** 
   * <em>evaluation_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getEvaluationPrice()
  {
    return ( evaluation_price==null? ((double)0): evaluation_price.doubleValue() );
  }


  /** 
   * <em>evaluation_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getEvaluationPriceIsNull()
  {
    return evaluation_price == null;
  }


  /** 
   * <em>evaluation_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEvaluationPriceIsSet() {
    return evaluation_price_is_set;
  }


  /** 
   * <em>evaluation_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEvaluationPriceIsModified() {
    return evaluation_price_is_modified;
  }


  /** 
   * <em>collateral_ratio</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCollateralRatio()
  {
    return ( collateral_ratio==null? ((double)0): collateral_ratio.doubleValue() );
  }


  /** 
   * <em>collateral_ratio</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCollateralRatioIsNull()
  {
    return collateral_ratio == null;
  }


  /** 
   * <em>collateral_ratio</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCollateralRatioIsSet() {
    return collateral_ratio_is_set;
  }


  /** 
   * <em>collateral_ratio</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCollateralRatioIsModified() {
    return collateral_ratio_is_modified;
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
    return new AssetUnitPK(asset_unit_id);
  }


  /** 
   * <em>asset_unit_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_assetUnitId <em>asset_unit_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setAssetUnitId( long p_assetUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    asset_unit_id = p_assetUnitId;
    asset_unit_id_is_set = true;
    asset_unit_id_is_modified = true;
  }


  /** 
   * <em>account_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountId <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>sub_account_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subAccountId <em>sub_account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setSubAccountId( long p_subAccountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_account_id = p_subAccountId;
    sub_account_id_is_set = true;
    sub_account_id_is_modified = true;
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
   * <em>asset_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_assetId <em>asset_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setAssetId( long p_assetId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    asset_id = p_assetId;
    asset_id_is_set = true;
    asset_id_is_modified = true;
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
   * <em>original_quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_originalQuantity <em>original_quantity</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setOriginalQuantity( double p_originalQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    original_quantity = p_originalQuantity;
    original_quantity_is_set = true;
    original_quantity_is_modified = true;
  }


  /** 
   * <em>quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quantity <em>quantity</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * <em>acquired_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_acquiredPrice <em>acquired_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setAcquiredPrice( double p_acquiredPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acquired_price = p_acquiredPrice;
    acquired_price_is_set = true;
    acquired_price_is_modified = true;
  }


  /** 
   * <em>acquired_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_acquiredTimestamp <em>acquired_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAcquiredTimestamp( java.sql.Timestamp p_acquiredTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acquired_timestamp = p_acquiredTimestamp;
    acquired_timestamp_is_set = true;
    acquired_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>acquired_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAcquiredTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    acquired_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    acquired_timestamp_is_set = true;
    acquired_timestamp_is_modified = true;
  }


  /** 
   * <em>delivery_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_deliveryDate <em>delivery_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setDeliveryDate( java.sql.Timestamp p_deliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = p_deliveryDate;
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>delivery_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


  /** 
   * <em>accrued_interest_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accruedInterestPrice <em>accrued_interest_price</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setAccruedInterestPrice( long p_accruedInterestPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    accrued_interest_price = p_accruedInterestPrice;
    accrued_interest_price_is_set = true;
    accrued_interest_price_is_modified = true;
  }


  /** 
   * <em>tax_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_taxType <em>tax_type</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setTaxType( int p_taxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type = p_taxType;
    tax_type_is_set = true;
    tax_type_is_modified = true;
  }


  /** 
   * <em>custody_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_custodyType <em>custody_type</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setCustodyType( String p_custodyType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    custody_type = p_custodyType;
    custody_type_is_set = true;
    custody_type_is_modified = true;
  }


  /** 
   * <em>evaluation_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_evaluationPrice <em>evaluation_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setEvaluationPrice( double p_evaluationPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    evaluation_price = new Double(p_evaluationPrice);
    evaluation_price_is_set = true;
    evaluation_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>evaluation_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setEvaluationPrice( Double p_evaluationPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    evaluation_price = p_evaluationPrice;
    evaluation_price_is_set = true;
    evaluation_price_is_modified = true;
  }


  /** 
   * <em>collateral_ratio</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_collateralRatio <em>collateral_ratio</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCollateralRatio( double p_collateralRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    collateral_ratio = new Double(p_collateralRatio);
    collateral_ratio_is_set = true;
    collateral_ratio_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>collateral_ratio</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCollateralRatio( Double p_collateralRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    collateral_ratio = p_collateralRatio;
    collateral_ratio_is_set = true;
    collateral_ratio_is_modified = true;
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
                if ( name.equals("asset_unit_id") ) {
                    return new Long( asset_unit_id );
                }
                else if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("asset_id") ) {
                    return new Long( asset_id );
                }
                else if ( name.equals("acquired_price") ) {
                    return new Double( acquired_price );
                }
                else if ( name.equals("acquired_timestamp") ) {
                    return this.acquired_timestamp;
                }
                else if ( name.equals("accrued_interest_price") ) {
                    return new Long( accrued_interest_price );
                }
                break;
            case 'c':
                if ( name.equals("custody_type") ) {
                    return this.custody_type;
                }
                else if ( name.equals("collateral_ratio") ) {
                    return this.collateral_ratio;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                break;
            case 'e':
                if ( name.equals("evaluation_price") ) {
                    return this.evaluation_price;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                break;
            case 'o':
                if ( name.equals("original_quantity") ) {
                    return new Double( original_quantity );
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return new Double( quantity );
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                break;
            case 't':
                if ( name.equals("tax_type") ) {
                    return new Integer( tax_type );
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
                if ( name.equals("asset_unit_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'asset_unit_id' must be Long: '"+value+"' is not." );
                    this.asset_unit_id = ((Long) value).longValue();
                    if (this.asset_unit_id_is_set)
                        this.asset_unit_id_is_modified = true;
                    this.asset_unit_id_is_set = true;
                    return;
                }
                else if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("asset_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'asset_id' must be Long: '"+value+"' is not." );
                    this.asset_id = ((Long) value).longValue();
                    if (this.asset_id_is_set)
                        this.asset_id_is_modified = true;
                    this.asset_id_is_set = true;
                    return;
                }
                else if ( name.equals("acquired_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'acquired_price' must be Double: '"+value+"' is not." );
                    this.acquired_price = ((Double) value).doubleValue();
                    if (this.acquired_price_is_set)
                        this.acquired_price_is_modified = true;
                    this.acquired_price_is_set = true;
                    return;
                }
                else if ( name.equals("acquired_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'acquired_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.acquired_timestamp = (java.sql.Timestamp) value;
                    if (this.acquired_timestamp_is_set)
                        this.acquired_timestamp_is_modified = true;
                    this.acquired_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("accrued_interest_price") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'accrued_interest_price' must be Long: '"+value+"' is not." );
                    this.accrued_interest_price = ((Long) value).longValue();
                    if (this.accrued_interest_price_is_set)
                        this.accrued_interest_price_is_modified = true;
                    this.accrued_interest_price_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("custody_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'custody_type' must be String: '"+value+"' is not." );
                    this.custody_type = (String) value;
                    if (this.custody_type_is_set)
                        this.custody_type_is_modified = true;
                    this.custody_type_is_set = true;
                    return;
                }
                else if ( name.equals("collateral_ratio") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'collateral_ratio' must be Double: '"+value+"' is not." );
                    this.collateral_ratio = (Double) value;
                    if (this.collateral_ratio_is_set)
                        this.collateral_ratio_is_modified = true;
                    this.collateral_ratio_is_set = true;
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
            case 'd':
                if ( name.equals("delivery_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("evaluation_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'evaluation_price' must be Double: '"+value+"' is not." );
                    this.evaluation_price = (Double) value;
                    if (this.evaluation_price_is_set)
                        this.evaluation_price_is_modified = true;
                    this.evaluation_price_is_set = true;
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
                if ( name.equals("market_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = ((Long) value).longValue();
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("original_quantity") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'original_quantity' must be Double: '"+value+"' is not." );
                    this.original_quantity = ((Double) value).doubleValue();
                    if (this.original_quantity_is_set)
                        this.original_quantity_is_modified = true;
                    this.original_quantity_is_set = true;
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
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = ((Double) value).doubleValue();
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_account_id' must be Long: '"+value+"' is not." );
                    this.sub_account_id = ((Long) value).longValue();
                    if (this.sub_account_id_is_set)
                        this.sub_account_id_is_modified = true;
                    this.sub_account_id_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tax_type") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be Integer: '"+value+"' is not." );
                    this.tax_type = ((Integer) value).intValue();
                    if (this.tax_type_is_set)
                        this.tax_type_is_modified = true;
                    this.tax_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
