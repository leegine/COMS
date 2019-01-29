head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.37.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AssetParams.java;


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
 * asset�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link AssetRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link AssetParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link AssetParams}��{@@link AssetRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AssetPK 
 * @@see AssetRow 
 */
public class AssetParams extends Params implements AssetRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "asset";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = AssetRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return AssetRow.TYPE;
  }


  /** 
   * <em>asset_id</em>�J�����̒l 
   */
  public  long  asset_id;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>�J�����̒l 
   */
  public  long  sub_account_id;

  /** 
   * <em>product_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>quantity</em>�J�����̒l 
   */
  public  double  quantity;

  /** 
   * <em>quantity_cannot_sell</em>�J�����̒l 
   */
  public  double  quantity_cannot_sell;

  /** 
   * <em>quantity_for_book_value</em>�J�����̒l 
   */
  public  double  quantity_for_book_value;

  /** 
   * <em>book_value</em>�J�����̒l 
   */
  public  double  book_value;

  /** 
   * <em>input_book_value</em>�J�����̒l 
   */
  public  Double  input_book_value;

  /** 
   * <em>input_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  input_timestamp;

  /** 
   * <em>setup_fee</em>�J�����̒l 
   */
  public  double  setup_fee;

  /** 
   * <em>setup_fee_tax</em>�J�����̒l 
   */
  public  double  setup_fee_tax;

  /** 
   * <em>management_fee</em>�J�����̒l 
   */
  public  double  management_fee;

  /** 
   * <em>management_fee_tax</em>�J�����̒l 
   */
  public  double  management_fee_tax;

  /** 
   * <em>product_id</em>�J�����̒l 
   */
  public  long  product_id;

  /** 
   * <em>tax_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum  tax_type;

  /** 
   * <em>mini_stock_div</em>�J�����̒l 
   */
  public  String  mini_stock_div;

  /** 
   * <em>profit_distribution</em>�J�����̒l 
   */
  public  Double  profit_distribution;

  /** 
   * <em>count_before_penalty</em>�J�����̒l 
   */
  public  Double  count_before_penalty;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean asset_id_is_set = false;
  private boolean asset_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean quantity_cannot_sell_is_set = false;
  private boolean quantity_cannot_sell_is_modified = false;
  private boolean quantity_for_book_value_is_set = false;
  private boolean quantity_for_book_value_is_modified = false;
  private boolean book_value_is_set = false;
  private boolean book_value_is_modified = false;
  private boolean input_book_value_is_set = false;
  private boolean input_book_value_is_modified = false;
  private boolean input_timestamp_is_set = false;
  private boolean input_timestamp_is_modified = false;
  private boolean setup_fee_is_set = false;
  private boolean setup_fee_is_modified = false;
  private boolean setup_fee_tax_is_set = false;
  private boolean setup_fee_tax_is_modified = false;
  private boolean management_fee_is_set = false;
  private boolean management_fee_is_modified = false;
  private boolean management_fee_tax_is_set = false;
  private boolean management_fee_tax_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean mini_stock_div_is_set = false;
  private boolean mini_stock_div_is_modified = false;
  private boolean profit_distribution_is_set = false;
  private boolean profit_distribution_is_modified = false;
  private boolean count_before_penalty_is_set = false;
  private boolean count_before_penalty_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "asset_id=" + asset_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "product_type=" +product_type
      + "," + "quantity=" +quantity
      + "," + "quantity_cannot_sell=" +quantity_cannot_sell
      + "," + "quantity_for_book_value=" +quantity_for_book_value
      + "," + "book_value=" +book_value
      + "," + "input_book_value=" +input_book_value
      + "," + "input_timestamp=" +input_timestamp
      + "," + "setup_fee=" +setup_fee
      + "," + "setup_fee_tax=" +setup_fee_tax
      + "," + "management_fee=" +management_fee
      + "," + "management_fee_tax=" +management_fee_tax
      + "," + "product_id=" +product_id
      + "," + "tax_type=" +tax_type
      + "," + "mini_stock_div=" +mini_stock_div
      + "," + "profit_distribution=" +profit_distribution
      + "," + "count_before_penalty=" +count_before_penalty
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��AssetParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public AssetParams() {
    asset_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���AssetRow�I�u�W�F�N�g�̒l�𗘗p����AssetParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����AssetRow�I�u�W�F�N�g 
   */
  public AssetParams( AssetRow p_row )
  {
    asset_id = p_row.getAssetId();
    asset_id_is_set = p_row.getAssetIdIsSet();
    asset_id_is_modified = p_row.getAssetIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    quantity = p_row.getQuantity();
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    quantity_cannot_sell = p_row.getQuantityCannotSell();
    quantity_cannot_sell_is_set = p_row.getQuantityCannotSellIsSet();
    quantity_cannot_sell_is_modified = p_row.getQuantityCannotSellIsModified();
    quantity_for_book_value = p_row.getQuantityForBookValue();
    quantity_for_book_value_is_set = p_row.getQuantityForBookValueIsSet();
    quantity_for_book_value_is_modified = p_row.getQuantityForBookValueIsModified();
    book_value = p_row.getBookValue();
    book_value_is_set = p_row.getBookValueIsSet();
    book_value_is_modified = p_row.getBookValueIsModified();
    if ( !p_row.getInputBookValueIsNull() )
      input_book_value = new Double( p_row.getInputBookValue() );
    input_book_value_is_set = p_row.getInputBookValueIsSet();
    input_book_value_is_modified = p_row.getInputBookValueIsModified();
    input_timestamp = p_row.getInputTimestamp();
    input_timestamp_is_set = p_row.getInputTimestampIsSet();
    input_timestamp_is_modified = p_row.getInputTimestampIsModified();
    setup_fee = p_row.getSetupFee();
    setup_fee_is_set = p_row.getSetupFeeIsSet();
    setup_fee_is_modified = p_row.getSetupFeeIsModified();
    setup_fee_tax = p_row.getSetupFeeTax();
    setup_fee_tax_is_set = p_row.getSetupFeeTaxIsSet();
    setup_fee_tax_is_modified = p_row.getSetupFeeTaxIsModified();
    management_fee = p_row.getManagementFee();
    management_fee_is_set = p_row.getManagementFeeIsSet();
    management_fee_is_modified = p_row.getManagementFeeIsModified();
    management_fee_tax = p_row.getManagementFeeTax();
    management_fee_tax_is_set = p_row.getManagementFeeTaxIsSet();
    management_fee_tax_is_modified = p_row.getManagementFeeTaxIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    mini_stock_div = p_row.getMiniStockDiv();
    mini_stock_div_is_set = p_row.getMiniStockDivIsSet();
    mini_stock_div_is_modified = p_row.getMiniStockDivIsModified();
    if ( !p_row.getProfitDistributionIsNull() )
      profit_distribution = new Double( p_row.getProfitDistribution() );
    profit_distribution_is_set = p_row.getProfitDistributionIsSet();
    profit_distribution_is_modified = p_row.getProfitDistributionIsModified();
    if ( !p_row.getCountBeforePenaltyIsNull() )
      count_before_penalty = new Double( p_row.getCountBeforePenalty() );
    count_before_penalty_is_set = p_row.getCountBeforePenaltyIsSet();
    count_before_penalty_is_modified = p_row.getCountBeforePenaltyIsModified();
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
      product_type_is_set = true;
      product_type_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      quantity_cannot_sell_is_set = true;
      quantity_cannot_sell_is_modified = true;
      quantity_for_book_value_is_set = true;
      quantity_for_book_value_is_modified = true;
      book_value_is_set = true;
      book_value_is_modified = true;
      input_book_value_is_set = true;
      input_book_value_is_modified = true;
      input_timestamp_is_set = true;
      input_timestamp_is_modified = true;
      setup_fee_is_set = true;
      setup_fee_is_modified = true;
      setup_fee_tax_is_set = true;
      setup_fee_tax_is_modified = true;
      management_fee_is_set = true;
      management_fee_is_modified = true;
      management_fee_tax_is_set = true;
      management_fee_tax_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      mini_stock_div_is_set = true;
      mini_stock_div_is_modified = true;
      profit_distribution_is_set = true;
      profit_distribution_is_modified = true;
      count_before_penalty_is_set = true;
      count_before_penalty_is_modified = true;
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
    if ( !( other instanceof AssetRow ) )
       return false;
    return fieldsEqual( (AssetRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�AssetRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( AssetRow row )
  {
    if ( asset_id != row.getAssetId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( quantity != row.getQuantity() )
      return false;
    if ( quantity_cannot_sell != row.getQuantityCannotSell() )
      return false;
    if ( quantity_for_book_value != row.getQuantityForBookValue() )
      return false;
    if ( book_value != row.getBookValue() )
      return false;
    if ( input_book_value == null ) {
      if ( !row.getInputBookValueIsNull() )
        return false;
    } else if ( row.getInputBookValueIsNull() || ( input_book_value.doubleValue() != row.getInputBookValue() ) ) {
        return false;
    }
    if ( input_timestamp == null ) {
      if ( row.getInputTimestamp() != null )
        return false;
    } else if ( !input_timestamp.equals( row.getInputTimestamp() ) ) {
        return false;
    }
    if ( setup_fee != row.getSetupFee() )
      return false;
    if ( setup_fee_tax != row.getSetupFeeTax() )
      return false;
    if ( management_fee != row.getManagementFee() )
      return false;
    if ( management_fee_tax != row.getManagementFeeTax() )
      return false;
    if ( product_id != row.getProductId() )
      return false;
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( mini_stock_div == null ) {
      if ( row.getMiniStockDiv() != null )
        return false;
    } else if ( !mini_stock_div.equals( row.getMiniStockDiv() ) ) {
        return false;
    }
    if ( profit_distribution == null ) {
      if ( !row.getProfitDistributionIsNull() )
        return false;
    } else if ( row.getProfitDistributionIsNull() || ( profit_distribution.doubleValue() != row.getProfitDistribution() ) ) {
        return false;
    }
    if ( count_before_penalty == null ) {
      if ( !row.getCountBeforePenaltyIsNull() )
        return false;
    } else if ( row.getCountBeforePenaltyIsNull() || ( count_before_penalty.doubleValue() != row.getCountBeforePenalty() ) ) {
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
      return  ((int) asset_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + (product_type!=null? product_type.hashCode(): 0) 
        + ((int) quantity)
        + ((int) quantity_cannot_sell)
        + ((int) quantity_for_book_value)
        + ((int) book_value)
        + (input_book_value!=null? input_book_value.hashCode(): 0) 
        + (input_timestamp!=null? input_timestamp.hashCode(): 0) 
        + ((int) setup_fee)
        + ((int) setup_fee_tax)
        + ((int) management_fee)
        + ((int) management_fee_tax)
        + ((int) product_id)
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (mini_stock_div!=null? mini_stock_div.hashCode(): 0) 
        + (profit_distribution!=null? profit_distribution.hashCode(): 0) 
        + (count_before_penalty!=null? count_before_penalty.hashCode(): 0) 
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
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
		if ( !quantity_cannot_sell_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity_cannot_sell' must be set before inserting.");
		if ( !quantity_for_book_value_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity_for_book_value' must be set before inserting.");
		if ( !book_value_is_set )
			throw new IllegalArgumentException("non-nullable field 'book_value' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !tax_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'tax_type' must be set before inserting.");
		if ( !mini_stock_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'mini_stock_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("asset_id",new Long(asset_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("product_type",product_type);
		map.put("quantity",new Double(quantity));
		map.put("quantity_cannot_sell",new Double(quantity_cannot_sell));
		map.put("quantity_for_book_value",new Double(quantity_for_book_value));
		map.put("book_value",new Double(book_value));
		if ( input_book_value != null )
			map.put("input_book_value",input_book_value);
		if ( input_timestamp != null )
			map.put("input_timestamp",input_timestamp);
		if ( setup_fee_is_set )
			map.put("setup_fee",new Double(setup_fee));
		if ( setup_fee_tax_is_set )
			map.put("setup_fee_tax",new Double(setup_fee_tax));
		if ( management_fee_is_set )
			map.put("management_fee",new Double(management_fee));
		if ( management_fee_tax_is_set )
			map.put("management_fee_tax",new Double(management_fee_tax));
		map.put("product_id",new Long(product_id));
		map.put("tax_type",tax_type);
		map.put("mini_stock_div",mini_stock_div);
		if ( profit_distribution != null )
			map.put("profit_distribution",profit_distribution);
		if ( count_before_penalty != null )
			map.put("count_before_penalty",count_before_penalty);
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
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( quantity_is_modified )
			map.put("quantity",new Double(quantity));
		if ( quantity_cannot_sell_is_modified )
			map.put("quantity_cannot_sell",new Double(quantity_cannot_sell));
		if ( quantity_for_book_value_is_modified )
			map.put("quantity_for_book_value",new Double(quantity_for_book_value));
		if ( book_value_is_modified )
			map.put("book_value",new Double(book_value));
		if ( input_book_value_is_modified )
			map.put("input_book_value",input_book_value);
		if ( input_timestamp_is_modified )
			map.put("input_timestamp",input_timestamp);
		if ( setup_fee_is_modified )
			map.put("setup_fee",new Double(setup_fee));
		if ( setup_fee_tax_is_modified )
			map.put("setup_fee_tax",new Double(setup_fee_tax));
		if ( management_fee_is_modified )
			map.put("management_fee",new Double(management_fee));
		if ( management_fee_tax_is_modified )
			map.put("management_fee_tax",new Double(management_fee_tax));
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( mini_stock_div_is_modified )
			map.put("mini_stock_div",mini_stock_div);
		if ( profit_distribution_is_modified )
			map.put("profit_distribution",profit_distribution);
		if ( count_before_penalty_is_modified )
			map.put("count_before_penalty",count_before_penalty);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( product_type_is_set )
				map.put("product_type",product_type);
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			if ( quantity_cannot_sell_is_set )
				map.put("quantity_cannot_sell",new Double(quantity_cannot_sell));
			if ( quantity_for_book_value_is_set )
				map.put("quantity_for_book_value",new Double(quantity_for_book_value));
			if ( book_value_is_set )
				map.put("book_value",new Double(book_value));
			map.put("input_book_value",input_book_value);
			map.put("input_timestamp",input_timestamp);
			if ( setup_fee_is_set )
				map.put("setup_fee",new Double(setup_fee));
			if ( setup_fee_tax_is_set )
				map.put("setup_fee_tax",new Double(setup_fee_tax));
			if ( management_fee_is_set )
				map.put("management_fee",new Double(management_fee));
			if ( management_fee_tax_is_set )
				map.put("management_fee_tax",new Double(management_fee_tax));
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( tax_type_is_set )
				map.put("tax_type",tax_type);
			if ( mini_stock_div_is_set )
				map.put("mini_stock_div",mini_stock_div);
			map.put("profit_distribution",profit_distribution);
			map.put("count_before_penalty",count_before_penalty);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>quantity_cannot_sell</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getQuantityCannotSell()
  {
    return quantity_cannot_sell;
  }


  /** 
   * <em>quantity_cannot_sell</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityCannotSellIsSet() {
    return quantity_cannot_sell_is_set;
  }


  /** 
   * <em>quantity_cannot_sell</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityCannotSellIsModified() {
    return quantity_cannot_sell_is_modified;
  }


  /** 
   * <em>quantity_for_book_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getQuantityForBookValue()
  {
    return quantity_for_book_value;
  }


  /** 
   * <em>quantity_for_book_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityForBookValueIsSet() {
    return quantity_for_book_value_is_set;
  }


  /** 
   * <em>quantity_for_book_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityForBookValueIsModified() {
    return quantity_for_book_value_is_modified;
  }


  /** 
   * <em>book_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getBookValue()
  {
    return book_value;
  }


  /** 
   * <em>book_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBookValueIsSet() {
    return book_value_is_set;
  }


  /** 
   * <em>book_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBookValueIsModified() {
    return book_value_is_modified;
  }


  /** 
   * <em>input_book_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getInputBookValue()
  {
    return ( input_book_value==null? ((double)0): input_book_value.doubleValue() );
  }


  /** 
   * <em>input_book_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getInputBookValueIsNull()
  {
    return input_book_value == null;
  }


  /** 
   * <em>input_book_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputBookValueIsSet() {
    return input_book_value_is_set;
  }


  /** 
   * <em>input_book_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputBookValueIsModified() {
    return input_book_value_is_modified;
  }


  /** 
   * <em>input_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getInputTimestamp()
  {
    return input_timestamp;
  }


  /** 
   * <em>input_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputTimestampIsSet() {
    return input_timestamp_is_set;
  }


  /** 
   * <em>input_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputTimestampIsModified() {
    return input_timestamp_is_modified;
  }


  /** 
   * <em>setup_fee</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getSetupFee()
  {
    return setup_fee;
  }


  /** 
   * <em>setup_fee</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSetupFeeIsSet() {
    return setup_fee_is_set;
  }


  /** 
   * <em>setup_fee</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSetupFeeIsModified() {
    return setup_fee_is_modified;
  }


  /** 
   * <em>setup_fee_tax</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getSetupFeeTax()
  {
    return setup_fee_tax;
  }


  /** 
   * <em>setup_fee_tax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSetupFeeTaxIsSet() {
    return setup_fee_tax_is_set;
  }


  /** 
   * <em>setup_fee_tax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSetupFeeTaxIsModified() {
    return setup_fee_tax_is_modified;
  }


  /** 
   * <em>management_fee</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getManagementFee()
  {
    return management_fee;
  }


  /** 
   * <em>management_fee</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getManagementFeeIsSet() {
    return management_fee_is_set;
  }


  /** 
   * <em>management_fee</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getManagementFeeIsModified() {
    return management_fee_is_modified;
  }


  /** 
   * <em>management_fee_tax</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getManagementFeeTax()
  {
    return management_fee_tax;
  }


  /** 
   * <em>management_fee_tax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getManagementFeeTaxIsSet() {
    return management_fee_tax_is_set;
  }


  /** 
   * <em>management_fee_tax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getManagementFeeTaxIsModified() {
    return management_fee_tax_is_modified;
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
   * <em>tax_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getTaxType()
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
   * <em>mini_stock_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMiniStockDiv()
  {
    return mini_stock_div;
  }


  /** 
   * <em>mini_stock_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMiniStockDivIsSet() {
    return mini_stock_div_is_set;
  }


  /** 
   * <em>mini_stock_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMiniStockDivIsModified() {
    return mini_stock_div_is_modified;
  }


  /** 
   * <em>profit_distribution</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getProfitDistribution()
  {
    return ( profit_distribution==null? ((double)0): profit_distribution.doubleValue() );
  }


  /** 
   * <em>profit_distribution</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getProfitDistributionIsNull()
  {
    return profit_distribution == null;
  }


  /** 
   * <em>profit_distribution</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProfitDistributionIsSet() {
    return profit_distribution_is_set;
  }


  /** 
   * <em>profit_distribution</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProfitDistributionIsModified() {
    return profit_distribution_is_modified;
  }


  /** 
   * <em>count_before_penalty</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCountBeforePenalty()
  {
    return ( count_before_penalty==null? ((double)0): count_before_penalty.doubleValue() );
  }


  /** 
   * <em>count_before_penalty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCountBeforePenaltyIsNull()
  {
    return count_before_penalty == null;
  }


  /** 
   * <em>count_before_penalty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCountBeforePenaltyIsSet() {
    return count_before_penalty_is_set;
  }


  /** 
   * <em>count_before_penalty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCountBeforePenaltyIsModified() {
    return count_before_penalty_is_modified;
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
    return new AssetPK(asset_id);
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
   * <em>quantity_cannot_sell</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quantityCannotSell <em>quantity_cannot_sell</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setQuantityCannotSell( double p_quantityCannotSell )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity_cannot_sell = p_quantityCannotSell;
    quantity_cannot_sell_is_set = true;
    quantity_cannot_sell_is_modified = true;
  }


  /** 
   * <em>quantity_for_book_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quantityForBookValue <em>quantity_for_book_value</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setQuantityForBookValue( double p_quantityForBookValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity_for_book_value = p_quantityForBookValue;
    quantity_for_book_value_is_set = true;
    quantity_for_book_value_is_modified = true;
  }


  /** 
   * <em>book_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bookValue <em>book_value</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setBookValue( double p_bookValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    book_value = p_bookValue;
    book_value_is_set = true;
    book_value_is_modified = true;
  }


  /** 
   * <em>input_book_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_inputBookValue <em>input_book_value</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setInputBookValue( double p_inputBookValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    input_book_value = new Double(p_inputBookValue);
    input_book_value_is_set = true;
    input_book_value_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>input_book_value</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setInputBookValue( Double p_inputBookValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    input_book_value = p_inputBookValue;
    input_book_value_is_set = true;
    input_book_value_is_modified = true;
  }


  /** 
   * <em>input_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_inputTimestamp <em>input_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setInputTimestamp( java.sql.Timestamp p_inputTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    input_timestamp = p_inputTimestamp;
    input_timestamp_is_set = true;
    input_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>input_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setInputTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    input_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    input_timestamp_is_set = true;
    input_timestamp_is_modified = true;
  }


  /** 
   * <em>setup_fee</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_setupFee <em>setup_fee</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setSetupFee( double p_setupFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee = p_setupFee;
    setup_fee_is_set = true;
    setup_fee_is_modified = true;
  }


  /** 
   * <em>setup_fee_tax</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_setupFeeTax <em>setup_fee_tax</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setSetupFeeTax( double p_setupFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee_tax = p_setupFeeTax;
    setup_fee_tax_is_set = true;
    setup_fee_tax_is_modified = true;
  }


  /** 
   * <em>management_fee</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_managementFee <em>management_fee</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setManagementFee( double p_managementFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    management_fee = p_managementFee;
    management_fee_is_set = true;
    management_fee_is_modified = true;
  }


  /** 
   * <em>management_fee_tax</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_managementFeeTax <em>management_fee_tax</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setManagementFeeTax( double p_managementFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    management_fee_tax = p_managementFeeTax;
    management_fee_tax_is_set = true;
    management_fee_tax_is_modified = true;
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
   * <em>tax_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_taxType <em>tax_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum�l 
   */
  public final void setTaxType( com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type = p_taxType;
    tax_type_is_set = true;
    tax_type_is_modified = true;
  }


  /** 
   * <em>mini_stock_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_miniStockDiv <em>mini_stock_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setMiniStockDiv( String p_miniStockDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_div = p_miniStockDiv;
    mini_stock_div_is_set = true;
    mini_stock_div_is_modified = true;
  }


  /** 
   * <em>profit_distribution</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_profitDistribution <em>profit_distribution</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setProfitDistribution( double p_profitDistribution )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    profit_distribution = new Double(p_profitDistribution);
    profit_distribution_is_set = true;
    profit_distribution_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>profit_distribution</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setProfitDistribution( Double p_profitDistribution )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    profit_distribution = p_profitDistribution;
    profit_distribution_is_set = true;
    profit_distribution_is_modified = true;
  }


  /** 
   * <em>count_before_penalty</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_countBeforePenalty <em>count_before_penalty</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCountBeforePenalty( double p_countBeforePenalty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    count_before_penalty = new Double(p_countBeforePenalty);
    count_before_penalty_is_set = true;
    count_before_penalty_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>count_before_penalty</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCountBeforePenalty( Double p_countBeforePenalty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    count_before_penalty = p_countBeforePenalty;
    count_before_penalty_is_set = true;
    count_before_penalty_is_modified = true;
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
                if ( name.equals("asset_id") ) {
                    return new Long( asset_id );
                }
                else if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                break;
            case 'b':
                if ( name.equals("book_value") ) {
                    return new Double( book_value );
                }
                break;
            case 'c':
                if ( name.equals("count_before_penalty") ) {
                    return this.count_before_penalty;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("input_book_value") ) {
                    return this.input_book_value;
                }
                else if ( name.equals("input_timestamp") ) {
                    return this.input_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("management_fee") ) {
                    return new Double( management_fee );
                }
                else if ( name.equals("management_fee_tax") ) {
                    return new Double( management_fee_tax );
                }
                else if ( name.equals("mini_stock_div") ) {
                    return this.mini_stock_div;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("profit_distribution") ) {
                    return this.profit_distribution;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return new Double( quantity );
                }
                else if ( name.equals("quantity_cannot_sell") ) {
                    return new Double( quantity_cannot_sell );
                }
                else if ( name.equals("quantity_for_book_value") ) {
                    return new Double( quantity_for_book_value );
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("setup_fee") ) {
                    return new Double( setup_fee );
                }
                else if ( name.equals("setup_fee_tax") ) {
                    return new Double( setup_fee_tax );
                }
                break;
            case 't':
                if ( name.equals("tax_type") ) {
                    return this.tax_type;
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
                if ( name.equals("asset_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'asset_id' must be Long: '"+value+"' is not." );
                    this.asset_id = ((Long) value).longValue();
                    if (this.asset_id_is_set)
                        this.asset_id_is_modified = true;
                    this.asset_id_is_set = true;
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
                break;
            case 'b':
                if ( name.equals("book_value") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'book_value' must be Double: '"+value+"' is not." );
                    this.book_value = ((Double) value).doubleValue();
                    if (this.book_value_is_set)
                        this.book_value_is_modified = true;
                    this.book_value_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("count_before_penalty") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'count_before_penalty' must be Double: '"+value+"' is not." );
                    this.count_before_penalty = (Double) value;
                    if (this.count_before_penalty_is_set)
                        this.count_before_penalty_is_modified = true;
                    this.count_before_penalty_is_set = true;
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
            case 'i':
                if ( name.equals("input_book_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'input_book_value' must be Double: '"+value+"' is not." );
                    this.input_book_value = (Double) value;
                    if (this.input_book_value_is_set)
                        this.input_book_value_is_modified = true;
                    this.input_book_value_is_set = true;
                    return;
                }
                else if ( name.equals("input_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'input_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.input_timestamp = (java.sql.Timestamp) value;
                    if (this.input_timestamp_is_set)
                        this.input_timestamp_is_modified = true;
                    this.input_timestamp_is_set = true;
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
                if ( name.equals("management_fee") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'management_fee' must be Double: '"+value+"' is not." );
                    this.management_fee = ((Double) value).doubleValue();
                    if (this.management_fee_is_set)
                        this.management_fee_is_modified = true;
                    this.management_fee_is_set = true;
                    return;
                }
                else if ( name.equals("management_fee_tax") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'management_fee_tax' must be Double: '"+value+"' is not." );
                    this.management_fee_tax = ((Double) value).doubleValue();
                    if (this.management_fee_tax_is_set)
                        this.management_fee_tax_is_modified = true;
                    this.management_fee_tax_is_set = true;
                    return;
                }
                else if ( name.equals("mini_stock_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mini_stock_div' must be String: '"+value+"' is not." );
                    this.mini_stock_div = (String) value;
                    if (this.mini_stock_div_is_set)
                        this.mini_stock_div_is_modified = true;
                    this.mini_stock_div_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                else if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                else if ( name.equals("profit_distribution") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'profit_distribution' must be Double: '"+value+"' is not." );
                    this.profit_distribution = (Double) value;
                    if (this.profit_distribution_is_set)
                        this.profit_distribution_is_modified = true;
                    this.profit_distribution_is_set = true;
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
                else if ( name.equals("quantity_cannot_sell") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity_cannot_sell' must be Double: '"+value+"' is not." );
                    this.quantity_cannot_sell = ((Double) value).doubleValue();
                    if (this.quantity_cannot_sell_is_set)
                        this.quantity_cannot_sell_is_modified = true;
                    this.quantity_cannot_sell_is_set = true;
                    return;
                }
                else if ( name.equals("quantity_for_book_value") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity_for_book_value' must be Double: '"+value+"' is not." );
                    this.quantity_for_book_value = ((Double) value).doubleValue();
                    if (this.quantity_for_book_value_is_set)
                        this.quantity_for_book_value_is_modified = true;
                    this.quantity_for_book_value_is_set = true;
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
                else if ( name.equals("setup_fee") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'setup_fee' must be Double: '"+value+"' is not." );
                    this.setup_fee = ((Double) value).doubleValue();
                    if (this.setup_fee_is_set)
                        this.setup_fee_is_modified = true;
                    this.setup_fee_is_set = true;
                    return;
                }
                else if ( name.equals("setup_fee_tax") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'setup_fee_tax' must be Double: '"+value+"' is not." );
                    this.setup_fee_tax = ((Double) value).doubleValue();
                    if (this.setup_fee_tax_is_set)
                        this.setup_fee_tax_is_modified = true;
                    this.setup_fee_tax_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tax_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum: '"+value+"' is not." );
                    this.tax_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) value;
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
