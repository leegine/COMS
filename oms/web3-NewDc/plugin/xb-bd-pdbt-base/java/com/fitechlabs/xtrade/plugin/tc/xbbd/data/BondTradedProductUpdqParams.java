head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.58.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondTradedProductUpdqParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * bond_traded_product_updq�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link BondTradedProductUpdqRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link BondTradedProductUpdqParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link BondTradedProductUpdqParams}��{@@link BondTradedProductUpdqRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondTradedProductUpdqPK 
 * @@see BondTradedProductUpdqRow 
 */
public class BondTradedProductUpdqParams extends Params implements BondTradedProductUpdqRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "bond_traded_product_updq";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = BondTradedProductUpdqRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return BondTradedProductUpdqRow.TYPE;
  }


  /** 
   * <em>traded_product_id</em>�J�����̒l 
   */
  public  long  traded_product_id;

  /** 
   * <em>valid_for_biz_date</em>�J�����̒l 
   */
  public  String  valid_for_biz_date;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>product_id</em>�J�����̒l 
   */
  public  long  product_id;

  /** 
   * <em>market_id</em>�J�����̒l 
   */
  public  long  market_id;

  /** 
   * <em>min_par_value</em>�J�����̒l 
   */
  public  double  min_par_value;

  /** 
   * <em>weekly_order_allowed_flag</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  weekly_order_allowed_flag;

  /** 
   * <em>last_closing_price</em>�J�����̒l 
   */
  public  Double  last_closing_price;

  /** 
   * <em>stop_high_price</em>�J�����̒l 
   */
  public  Double  stop_high_price;

  /** 
   * <em>stop_low_price</em>�J�����̒l 
   */
  public  Double  stop_low_price;

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

  private boolean traded_product_id_is_set = false;
  private boolean traded_product_id_is_modified = false;
  private boolean valid_for_biz_date_is_set = false;
  private boolean valid_for_biz_date_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean min_par_value_is_set = false;
  private boolean min_par_value_is_modified = false;
  private boolean weekly_order_allowed_flag_is_set = false;
  private boolean weekly_order_allowed_flag_is_modified = false;
  private boolean last_closing_price_is_set = false;
  private boolean last_closing_price_is_modified = false;
  private boolean stop_high_price_is_set = false;
  private boolean stop_high_price_is_modified = false;
  private boolean stop_low_price_is_set = false;
  private boolean stop_low_price_is_modified = false;
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
       + "traded_product_id=" + traded_product_id
      + "," + "valid_for_biz_date=" + valid_for_biz_date
      + "," + "institution_code=" +institution_code
      + "," + "product_id=" +product_id
      + "," + "market_id=" +market_id
      + "," + "min_par_value=" +min_par_value
      + "," + "weekly_order_allowed_flag=" +weekly_order_allowed_flag
      + "," + "last_closing_price=" +last_closing_price
      + "," + "stop_high_price=" +stop_high_price
      + "," + "stop_low_price=" +stop_low_price
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��BondTradedProductUpdqParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public BondTradedProductUpdqParams() {
    traded_product_id_is_modified = true;
    valid_for_biz_date_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���BondTradedProductUpdqRow�I�u�W�F�N�g�̒l�𗘗p����BondTradedProductUpdqParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����BondTradedProductUpdqRow�I�u�W�F�N�g 
   */
  public BondTradedProductUpdqParams( BondTradedProductUpdqRow p_row )
  {
    traded_product_id = p_row.getTradedProductId();
    traded_product_id_is_set = p_row.getTradedProductIdIsSet();
    traded_product_id_is_modified = p_row.getTradedProductIdIsModified();
    valid_for_biz_date = p_row.getValidForBizDate();
    valid_for_biz_date_is_set = p_row.getValidForBizDateIsSet();
    valid_for_biz_date_is_modified = p_row.getValidForBizDateIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    min_par_value = p_row.getMinParValue();
    min_par_value_is_set = p_row.getMinParValueIsSet();
    min_par_value_is_modified = p_row.getMinParValueIsModified();
    weekly_order_allowed_flag = p_row.getWeeklyOrderAllowedFlag();
    weekly_order_allowed_flag_is_set = p_row.getWeeklyOrderAllowedFlagIsSet();
    weekly_order_allowed_flag_is_modified = p_row.getWeeklyOrderAllowedFlagIsModified();
    if ( !p_row.getLastClosingPriceIsNull() )
      last_closing_price = new Double( p_row.getLastClosingPrice() );
    last_closing_price_is_set = p_row.getLastClosingPriceIsSet();
    last_closing_price_is_modified = p_row.getLastClosingPriceIsModified();
    if ( !p_row.getStopHighPriceIsNull() )
      stop_high_price = new Double( p_row.getStopHighPrice() );
    stop_high_price_is_set = p_row.getStopHighPriceIsSet();
    stop_high_price_is_modified = p_row.getStopHighPriceIsModified();
    if ( !p_row.getStopLowPriceIsNull() )
      stop_low_price = new Double( p_row.getStopLowPrice() );
    stop_low_price_is_set = p_row.getStopLowPriceIsSet();
    stop_low_price_is_modified = p_row.getStopLowPriceIsModified();
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
      product_id_is_set = true;
      product_id_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      min_par_value_is_set = true;
      min_par_value_is_modified = true;
      weekly_order_allowed_flag_is_set = true;
      weekly_order_allowed_flag_is_modified = true;
      last_closing_price_is_set = true;
      last_closing_price_is_modified = true;
      stop_high_price_is_set = true;
      stop_high_price_is_modified = true;
      stop_low_price_is_set = true;
      stop_low_price_is_modified = true;
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
    if ( !( other instanceof BondTradedProductUpdqRow ) )
       return false;
    return fieldsEqual( (BondTradedProductUpdqRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�BondTradedProductUpdqRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( BondTradedProductUpdqRow row )
  {
    if ( traded_product_id != row.getTradedProductId() )
      return false;
    if ( valid_for_biz_date == null ) {
      if ( row.getValidForBizDate() != null )
        return false;
    } else if ( !valid_for_biz_date.equals( row.getValidForBizDate() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_id != row.getProductId() )
      return false;
    if ( market_id != row.getMarketId() )
      return false;
    if ( min_par_value != row.getMinParValue() )
      return false;
    if ( weekly_order_allowed_flag == null ) {
      if ( row.getWeeklyOrderAllowedFlag() != null )
        return false;
    } else if ( !weekly_order_allowed_flag.equals( row.getWeeklyOrderAllowedFlag() ) ) {
        return false;
    }
    if ( last_closing_price == null ) {
      if ( !row.getLastClosingPriceIsNull() )
        return false;
    } else if ( row.getLastClosingPriceIsNull() || ( last_closing_price.doubleValue() != row.getLastClosingPrice() ) ) {
        return false;
    }
    if ( stop_high_price == null ) {
      if ( !row.getStopHighPriceIsNull() )
        return false;
    } else if ( row.getStopHighPriceIsNull() || ( stop_high_price.doubleValue() != row.getStopHighPrice() ) ) {
        return false;
    }
    if ( stop_low_price == null ) {
      if ( !row.getStopLowPriceIsNull() )
        return false;
    } else if ( row.getStopLowPriceIsNull() || ( stop_low_price.doubleValue() != row.getStopLowPrice() ) ) {
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
      return  ((int) traded_product_id)
        + (valid_for_biz_date!=null? valid_for_biz_date.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) product_id)
        + ((int) market_id)
        + ((int) min_par_value)
        + (weekly_order_allowed_flag!=null? weekly_order_allowed_flag.hashCode(): 0) 
        + (last_closing_price!=null? last_closing_price.hashCode(): 0) 
        + (stop_high_price!=null? stop_high_price.hashCode(): 0) 
        + (stop_low_price!=null? stop_low_price.hashCode(): 0) 
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
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !market_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_id' must be set before inserting.");
		if ( !min_par_value_is_set )
			throw new IllegalArgumentException("non-nullable field 'min_par_value' must be set before inserting.");
		if ( !weekly_order_allowed_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'weekly_order_allowed_flag' must be set before inserting.");
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
		map.put("traded_product_id",new Long(traded_product_id));
		map.put("valid_for_biz_date",valid_for_biz_date);
		map.put("institution_code",institution_code);
		map.put("product_id",new Long(product_id));
		map.put("market_id",new Long(market_id));
		map.put("min_par_value",new Double(min_par_value));
		map.put("weekly_order_allowed_flag",weekly_order_allowed_flag);
		if ( last_closing_price != null )
			map.put("last_closing_price",last_closing_price);
		if ( stop_high_price != null )
			map.put("stop_high_price",stop_high_price);
		if ( stop_low_price != null )
			map.put("stop_low_price",stop_low_price);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
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
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if ( min_par_value_is_modified )
			map.put("min_par_value",new Double(min_par_value));
		if ( weekly_order_allowed_flag_is_modified )
			map.put("weekly_order_allowed_flag",weekly_order_allowed_flag);
		if ( last_closing_price_is_modified )
			map.put("last_closing_price",last_closing_price);
		if ( stop_high_price_is_modified )
			map.put("stop_high_price",stop_high_price);
		if ( stop_low_price_is_modified )
			map.put("stop_low_price",stop_low_price);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
			if ( min_par_value_is_set )
				map.put("min_par_value",new Double(min_par_value));
			if ( weekly_order_allowed_flag_is_set )
				map.put("weekly_order_allowed_flag",weekly_order_allowed_flag);
			map.put("last_closing_price",last_closing_price);
			map.put("stop_high_price",stop_high_price);
			map.put("stop_low_price",stop_low_price);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>traded_product_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getTradedProductId()
  {
    return traded_product_id;
  }


  /** 
   * <em>traded_product_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradedProductIdIsSet() {
    return traded_product_id_is_set;
  }


  /** 
   * <em>traded_product_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradedProductIdIsModified() {
    return traded_product_id_is_modified;
  }


  /** 
   * <em>valid_for_biz_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getValidForBizDate()
  {
    return valid_for_biz_date;
  }


  /** 
   * <em>valid_for_biz_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getValidForBizDateIsSet() {
    return valid_for_biz_date_is_set;
  }


  /** 
   * <em>valid_for_biz_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getValidForBizDateIsModified() {
    return valid_for_biz_date_is_modified;
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
   * <em>min_par_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getMinParValue()
  {
    return min_par_value;
  }


  /** 
   * <em>min_par_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMinParValueIsSet() {
    return min_par_value_is_set;
  }


  /** 
   * <em>min_par_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMinParValueIsModified() {
    return min_par_value_is_modified;
  }


  /** 
   * <em>weekly_order_allowed_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getWeeklyOrderAllowedFlag()
  {
    return weekly_order_allowed_flag;
  }


  /** 
   * <em>weekly_order_allowed_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getWeeklyOrderAllowedFlagIsSet() {
    return weekly_order_allowed_flag_is_set;
  }


  /** 
   * <em>weekly_order_allowed_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getWeeklyOrderAllowedFlagIsModified() {
    return weekly_order_allowed_flag_is_modified;
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
   * <em>stop_high_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getStopHighPrice()
  {
    return ( stop_high_price==null? ((double)0): stop_high_price.doubleValue() );
  }


  /** 
   * <em>stop_high_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getStopHighPriceIsNull()
  {
    return stop_high_price == null;
  }


  /** 
   * <em>stop_high_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopHighPriceIsSet() {
    return stop_high_price_is_set;
  }


  /** 
   * <em>stop_high_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopHighPriceIsModified() {
    return stop_high_price_is_modified;
  }


  /** 
   * <em>stop_low_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getStopLowPrice()
  {
    return ( stop_low_price==null? ((double)0): stop_low_price.doubleValue() );
  }


  /** 
   * <em>stop_low_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getStopLowPriceIsNull()
  {
    return stop_low_price == null;
  }


  /** 
   * <em>stop_low_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopLowPriceIsSet() {
    return stop_low_price_is_set;
  }


  /** 
   * <em>stop_low_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopLowPriceIsModified() {
    return stop_low_price_is_modified;
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
    return new BondTradedProductUpdqPK(traded_product_id, valid_for_biz_date);
  }


  /** 
   * <em>traded_product_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tradedProductId <em>traded_product_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setTradedProductId( long p_tradedProductId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    traded_product_id = p_tradedProductId;
    traded_product_id_is_set = true;
    traded_product_id_is_modified = true;
  }


  /** 
   * <em>valid_for_biz_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_validForBizDate <em>valid_for_biz_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setValidForBizDate( String p_validForBizDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    valid_for_biz_date = p_validForBizDate;
    valid_for_biz_date_is_set = true;
    valid_for_biz_date_is_modified = true;
  }


  /** 
   * <em>institution_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
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
   * <em>min_par_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_minParValue <em>min_par_value</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setMinParValue( double p_minParValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    min_par_value = p_minParValue;
    min_par_value_is_set = true;
    min_par_value_is_modified = true;
  }


  /** 
   * <em>weekly_order_allowed_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_weeklyOrderAllowedFlag <em>weekly_order_allowed_flag</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�l 
   */
  public final void setWeeklyOrderAllowedFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_weeklyOrderAllowedFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    weekly_order_allowed_flag = p_weeklyOrderAllowedFlag;
    weekly_order_allowed_flag_is_set = true;
    weekly_order_allowed_flag_is_modified = true;
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
   * <em>stop_high_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stopHighPrice <em>stop_high_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setStopHighPrice( double p_stopHighPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_high_price = new Double(p_stopHighPrice);
    stop_high_price_is_set = true;
    stop_high_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>stop_high_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setStopHighPrice( Double p_stopHighPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    stop_high_price = p_stopHighPrice;
    stop_high_price_is_set = true;
    stop_high_price_is_modified = true;
  }


  /** 
   * <em>stop_low_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stopLowPrice <em>stop_low_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setStopLowPrice( double p_stopLowPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_low_price = new Double(p_stopLowPrice);
    stop_low_price_is_set = true;
    stop_low_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>stop_low_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setStopLowPrice( Double p_stopLowPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    stop_low_price = p_stopLowPrice;
    stop_low_price_is_set = true;
    stop_low_price_is_modified = true;
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
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
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
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                else if ( name.equals("min_par_value") ) {
                    return new Double( min_par_value );
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                break;
            case 's':
                if ( name.equals("stop_high_price") ) {
                    return this.stop_high_price;
                }
                else if ( name.equals("stop_low_price") ) {
                    return this.stop_low_price;
                }
                break;
            case 't':
                if ( name.equals("traded_product_id") ) {
                    return new Long( traded_product_id );
                }
                break;
            case 'v':
                if ( name.equals("valid_for_biz_date") ) {
                    return this.valid_for_biz_date;
                }
                break;
            case 'w':
                if ( name.equals("weekly_order_allowed_flag") ) {
                    return this.weekly_order_allowed_flag;
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
                if ( name.equals("market_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = ((Long) value).longValue();
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                else if ( name.equals("min_par_value") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'min_par_value' must be Double: '"+value+"' is not." );
                    this.min_par_value = ((Double) value).doubleValue();
                    if (this.min_par_value_is_set)
                        this.min_par_value_is_modified = true;
                    this.min_par_value_is_set = true;
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
                break;
            case 's':
                if ( name.equals("stop_high_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'stop_high_price' must be Double: '"+value+"' is not." );
                    this.stop_high_price = (Double) value;
                    if (this.stop_high_price_is_set)
                        this.stop_high_price_is_modified = true;
                    this.stop_high_price_is_set = true;
                    return;
                }
                else if ( name.equals("stop_low_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'stop_low_price' must be Double: '"+value+"' is not." );
                    this.stop_low_price = (Double) value;
                    if (this.stop_low_price_is_set)
                        this.stop_low_price_is_modified = true;
                    this.stop_low_price_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("traded_product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'traded_product_id' must be Long: '"+value+"' is not." );
                    this.traded_product_id = ((Long) value).longValue();
                    if (this.traded_product_id_is_set)
                        this.traded_product_id_is_modified = true;
                    this.traded_product_id_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("valid_for_biz_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'valid_for_biz_date' must be String: '"+value+"' is not." );
                    this.valid_for_biz_date = (String) value;
                    if (this.valid_for_biz_date_is_set)
                        this.valid_for_biz_date_is_modified = true;
                    this.valid_for_biz_date_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("weekly_order_allowed_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'weekly_order_allowed_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.weekly_order_allowed_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.weekly_order_allowed_flag_is_set)
                        this.weekly_order_allowed_flag_is_modified = true;
                    this.weekly_order_allowed_flag_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
