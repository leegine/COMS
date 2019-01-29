head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeProductParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * eqtype_product�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link EqtypeProductRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link EqtypeProductParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link EqtypeProductParams}��{@@link EqtypeProductRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeProductPK 
 * @@see EqtypeProductRow 
 */
public class EqtypeProductParams extends Params implements EqtypeProductRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "eqtype_product";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = EqtypeProductRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return EqtypeProductRow.TYPE;
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
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>product_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>standard_name</em>�J�����̒l 
   */
  public  String  standard_name;

  /** 
   * <em>mini_stock_lot_size</em>�J�����̒l 
   */
  public  Double  mini_stock_lot_size;

  /** 
   * <em>yearly_books_closing_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  yearly_books_closing_date;

  /** 
   * <em>trade_stop</em>�J�����̒l 
   */
  public  Integer  trade_stop;

  /** 
   * <em>margin_sys_trade_stop</em>�J�����̒l 
   */
  public  Integer  margin_sys_trade_stop;

  /** 
   * <em>margin_gen_trade_stop</em>�J�����̒l 
   */
  public  Integer  margin_gen_trade_stop;

  /** 
   * <em>capital_gain_tax_dealings_reg</em>�J�����̒l 
   */
  public  Integer  capital_gain_tax_dealings_reg;

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

  /** 
   * <em>decimal_devide_flag</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  decimal_devide_flag;

  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean standard_name_is_set = false;
  private boolean standard_name_is_modified = false;
  private boolean mini_stock_lot_size_is_set = false;
  private boolean mini_stock_lot_size_is_modified = false;
  private boolean yearly_books_closing_date_is_set = false;
  private boolean yearly_books_closing_date_is_modified = false;
  private boolean trade_stop_is_set = false;
  private boolean trade_stop_is_modified = false;
  private boolean margin_sys_trade_stop_is_set = false;
  private boolean margin_sys_trade_stop_is_modified = false;
  private boolean margin_gen_trade_stop_is_set = false;
  private boolean margin_gen_trade_stop_is_modified = false;
  private boolean capital_gain_tax_dealings_reg_is_set = false;
  private boolean capital_gain_tax_dealings_reg_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean decimal_devide_flag_is_set = false;
  private boolean decimal_devide_flag_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "product_id=" + product_id
      + "," + "institution_code=" +institution_code
      + "," + "product_code=" +product_code
      + "," + "product_type=" +product_type
      + "," + "standard_name=" +standard_name
      + "," + "mini_stock_lot_size=" +mini_stock_lot_size
      + "," + "yearly_books_closing_date=" +yearly_books_closing_date
      + "," + "trade_stop=" +trade_stop
      + "," + "margin_sys_trade_stop=" +margin_sys_trade_stop
      + "," + "margin_gen_trade_stop=" +margin_gen_trade_stop
      + "," + "capital_gain_tax_dealings_reg=" +capital_gain_tax_dealings_reg
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "decimal_devide_flag=" +decimal_devide_flag
      + "}";
  }


  /** 
   * �l�����ݒ��EqtypeProductParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public EqtypeProductParams() {
    product_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���EqtypeProductRow�I�u�W�F�N�g�̒l�𗘗p����EqtypeProductParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����EqtypeProductRow�I�u�W�F�N�g 
   */
  public EqtypeProductParams( EqtypeProductRow p_row )
  {
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    standard_name = p_row.getStandardName();
    standard_name_is_set = p_row.getStandardNameIsSet();
    standard_name_is_modified = p_row.getStandardNameIsModified();
    if ( !p_row.getMiniStockLotSizeIsNull() )
      mini_stock_lot_size = new Double( p_row.getMiniStockLotSize() );
    mini_stock_lot_size_is_set = p_row.getMiniStockLotSizeIsSet();
    mini_stock_lot_size_is_modified = p_row.getMiniStockLotSizeIsModified();
    yearly_books_closing_date = p_row.getYearlyBooksClosingDate();
    yearly_books_closing_date_is_set = p_row.getYearlyBooksClosingDateIsSet();
    yearly_books_closing_date_is_modified = p_row.getYearlyBooksClosingDateIsModified();
    if ( !p_row.getTradeStopIsNull() )
      trade_stop = new Integer( p_row.getTradeStop() );
    trade_stop_is_set = p_row.getTradeStopIsSet();
    trade_stop_is_modified = p_row.getTradeStopIsModified();
    if ( !p_row.getMarginSysTradeStopIsNull() )
      margin_sys_trade_stop = new Integer( p_row.getMarginSysTradeStop() );
    margin_sys_trade_stop_is_set = p_row.getMarginSysTradeStopIsSet();
    margin_sys_trade_stop_is_modified = p_row.getMarginSysTradeStopIsModified();
    if ( !p_row.getMarginGenTradeStopIsNull() )
      margin_gen_trade_stop = new Integer( p_row.getMarginGenTradeStop() );
    margin_gen_trade_stop_is_set = p_row.getMarginGenTradeStopIsSet();
    margin_gen_trade_stop_is_modified = p_row.getMarginGenTradeStopIsModified();
    if ( !p_row.getCapitalGainTaxDealingsRegIsNull() )
      capital_gain_tax_dealings_reg = new Integer( p_row.getCapitalGainTaxDealingsReg() );
    capital_gain_tax_dealings_reg_is_set = p_row.getCapitalGainTaxDealingsRegIsSet();
    capital_gain_tax_dealings_reg_is_modified = p_row.getCapitalGainTaxDealingsRegIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    decimal_devide_flag = p_row.getDecimalDevideFlag();
    decimal_devide_flag_is_set = p_row.getDecimalDevideFlagIsSet();
    decimal_devide_flag_is_modified = p_row.getDecimalDevideFlagIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      standard_name_is_set = true;
      standard_name_is_modified = true;
      mini_stock_lot_size_is_set = true;
      mini_stock_lot_size_is_modified = true;
      yearly_books_closing_date_is_set = true;
      yearly_books_closing_date_is_modified = true;
      trade_stop_is_set = true;
      trade_stop_is_modified = true;
      margin_sys_trade_stop_is_set = true;
      margin_sys_trade_stop_is_modified = true;
      margin_gen_trade_stop_is_set = true;
      margin_gen_trade_stop_is_modified = true;
      capital_gain_tax_dealings_reg_is_set = true;
      capital_gain_tax_dealings_reg_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      decimal_devide_flag_is_set = true;
      decimal_devide_flag_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof EqtypeProductRow ) )
       return false;
    return fieldsEqual( (EqtypeProductRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�EqtypeProductRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( EqtypeProductRow row )
  {
    if ( product_id != row.getProductId() )
      return false;
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
    if ( mini_stock_lot_size == null ) {
      if ( !row.getMiniStockLotSizeIsNull() )
        return false;
    } else if ( row.getMiniStockLotSizeIsNull() || ( mini_stock_lot_size.doubleValue() != row.getMiniStockLotSize() ) ) {
        return false;
    }
    if ( yearly_books_closing_date == null ) {
      if ( row.getYearlyBooksClosingDate() != null )
        return false;
    } else if ( !yearly_books_closing_date.equals( row.getYearlyBooksClosingDate() ) ) {
        return false;
    }
    if ( trade_stop == null ) {
      if ( !row.getTradeStopIsNull() )
        return false;
    } else if ( row.getTradeStopIsNull() || ( trade_stop.intValue() != row.getTradeStop() ) ) {
        return false;
    }
    if ( margin_sys_trade_stop == null ) {
      if ( !row.getMarginSysTradeStopIsNull() )
        return false;
    } else if ( row.getMarginSysTradeStopIsNull() || ( margin_sys_trade_stop.intValue() != row.getMarginSysTradeStop() ) ) {
        return false;
    }
    if ( margin_gen_trade_stop == null ) {
      if ( !row.getMarginGenTradeStopIsNull() )
        return false;
    } else if ( row.getMarginGenTradeStopIsNull() || ( margin_gen_trade_stop.intValue() != row.getMarginGenTradeStop() ) ) {
        return false;
    }
    if ( capital_gain_tax_dealings_reg == null ) {
      if ( !row.getCapitalGainTaxDealingsRegIsNull() )
        return false;
    } else if ( row.getCapitalGainTaxDealingsRegIsNull() || ( capital_gain_tax_dealings_reg.intValue() != row.getCapitalGainTaxDealingsReg() ) ) {
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
    if ( decimal_devide_flag == null ) {
      if ( row.getDecimalDevideFlag() != null )
        return false;
    } else if ( !decimal_devide_flag.equals( row.getDecimalDevideFlag() ) ) {
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
        + (product_code!=null? product_code.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + (standard_name!=null? standard_name.hashCode(): 0) 
        + (mini_stock_lot_size!=null? mini_stock_lot_size.hashCode(): 0) 
        + (yearly_books_closing_date!=null? yearly_books_closing_date.hashCode(): 0) 
        + (trade_stop!=null? trade_stop.hashCode(): 0) 
        + (margin_sys_trade_stop!=null? margin_sys_trade_stop.hashCode(): 0) 
        + (margin_gen_trade_stop!=null? margin_gen_trade_stop.hashCode(): 0) 
        + (capital_gain_tax_dealings_reg!=null? capital_gain_tax_dealings_reg.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (decimal_devide_flag!=null? decimal_devide_flag.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_code' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !yearly_books_closing_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'yearly_books_closing_date' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("product_id",new Long(product_id));
		map.put("institution_code",institution_code);
		map.put("product_code",product_code);
		map.put("product_type",product_type);
		if ( standard_name != null )
			map.put("standard_name",standard_name);
		if ( mini_stock_lot_size != null )
			map.put("mini_stock_lot_size",mini_stock_lot_size);
		map.put("yearly_books_closing_date",yearly_books_closing_date);
		if ( trade_stop != null )
			map.put("trade_stop",trade_stop);
		if ( margin_sys_trade_stop != null )
			map.put("margin_sys_trade_stop",margin_sys_trade_stop);
		if ( margin_gen_trade_stop != null )
			map.put("margin_gen_trade_stop",margin_gen_trade_stop);
		if ( capital_gain_tax_dealings_reg != null )
			map.put("capital_gain_tax_dealings_reg",capital_gain_tax_dealings_reg);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( decimal_devide_flag != null )
			map.put("decimal_devide_flag",decimal_devide_flag);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( standard_name_is_modified )
			map.put("standard_name",standard_name);
		if ( mini_stock_lot_size_is_modified )
			map.put("mini_stock_lot_size",mini_stock_lot_size);
		if ( yearly_books_closing_date_is_modified )
			map.put("yearly_books_closing_date",yearly_books_closing_date);
		if ( trade_stop_is_modified )
			map.put("trade_stop",trade_stop);
		if ( margin_sys_trade_stop_is_modified )
			map.put("margin_sys_trade_stop",margin_sys_trade_stop);
		if ( margin_gen_trade_stop_is_modified )
			map.put("margin_gen_trade_stop",margin_gen_trade_stop);
		if ( capital_gain_tax_dealings_reg_is_modified )
			map.put("capital_gain_tax_dealings_reg",capital_gain_tax_dealings_reg);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( decimal_devide_flag_is_modified )
			map.put("decimal_devide_flag",decimal_devide_flag);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( product_code_is_set )
				map.put("product_code",product_code);
			if ( product_type_is_set )
				map.put("product_type",product_type);
			map.put("standard_name",standard_name);
			map.put("mini_stock_lot_size",mini_stock_lot_size);
			if ( yearly_books_closing_date_is_set )
				map.put("yearly_books_closing_date",yearly_books_closing_date);
			map.put("trade_stop",trade_stop);
			map.put("margin_sys_trade_stop",margin_sys_trade_stop);
			map.put("margin_gen_trade_stop",margin_gen_trade_stop);
			map.put("capital_gain_tax_dealings_reg",capital_gain_tax_dealings_reg);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("decimal_devide_flag",decimal_devide_flag);
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
   * <em>mini_stock_lot_size</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getMiniStockLotSize()
  {
    return ( mini_stock_lot_size==null? ((double)0): mini_stock_lot_size.doubleValue() );
  }


  /** 
   * <em>mini_stock_lot_size</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getMiniStockLotSizeIsNull()
  {
    return mini_stock_lot_size == null;
  }


  /** 
   * <em>mini_stock_lot_size</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMiniStockLotSizeIsSet() {
    return mini_stock_lot_size_is_set;
  }


  /** 
   * <em>mini_stock_lot_size</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMiniStockLotSizeIsModified() {
    return mini_stock_lot_size_is_modified;
  }


  /** 
   * <em>yearly_books_closing_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getYearlyBooksClosingDate()
  {
    return yearly_books_closing_date;
  }


  /** 
   * <em>yearly_books_closing_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getYearlyBooksClosingDateIsSet() {
    return yearly_books_closing_date_is_set;
  }


  /** 
   * <em>yearly_books_closing_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getYearlyBooksClosingDateIsModified() {
    return yearly_books_closing_date_is_modified;
  }


  /** 
   * <em>trade_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getTradeStop()
  {
    return ( trade_stop==null? ((int)0): trade_stop.intValue() );
  }


  /** 
   * <em>trade_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTradeStopIsNull()
  {
    return trade_stop == null;
  }


  /** 
   * <em>trade_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradeStopIsSet() {
    return trade_stop_is_set;
  }


  /** 
   * <em>trade_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradeStopIsModified() {
    return trade_stop_is_modified;
  }


  /** 
   * <em>margin_sys_trade_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getMarginSysTradeStop()
  {
    return ( margin_sys_trade_stop==null? ((int)0): margin_sys_trade_stop.intValue() );
  }


  /** 
   * <em>margin_sys_trade_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getMarginSysTradeStopIsNull()
  {
    return margin_sys_trade_stop == null;
  }


  /** 
   * <em>margin_sys_trade_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarginSysTradeStopIsSet() {
    return margin_sys_trade_stop_is_set;
  }


  /** 
   * <em>margin_sys_trade_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarginSysTradeStopIsModified() {
    return margin_sys_trade_stop_is_modified;
  }


  /** 
   * <em>margin_gen_trade_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getMarginGenTradeStop()
  {
    return ( margin_gen_trade_stop==null? ((int)0): margin_gen_trade_stop.intValue() );
  }


  /** 
   * <em>margin_gen_trade_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getMarginGenTradeStopIsNull()
  {
    return margin_gen_trade_stop == null;
  }


  /** 
   * <em>margin_gen_trade_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarginGenTradeStopIsSet() {
    return margin_gen_trade_stop_is_set;
  }


  /** 
   * <em>margin_gen_trade_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarginGenTradeStopIsModified() {
    return margin_gen_trade_stop_is_modified;
  }


  /** 
   * <em>capital_gain_tax_dealings_reg</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getCapitalGainTaxDealingsReg()
  {
    return ( capital_gain_tax_dealings_reg==null? ((int)0): capital_gain_tax_dealings_reg.intValue() );
  }


  /** 
   * <em>capital_gain_tax_dealings_reg</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCapitalGainTaxDealingsRegIsNull()
  {
    return capital_gain_tax_dealings_reg == null;
  }


  /** 
   * <em>capital_gain_tax_dealings_reg</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCapitalGainTaxDealingsRegIsSet() {
    return capital_gain_tax_dealings_reg_is_set;
  }


  /** 
   * <em>capital_gain_tax_dealings_reg</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCapitalGainTaxDealingsRegIsModified() {
    return capital_gain_tax_dealings_reg_is_modified;
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
   * <em>decimal_devide_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDecimalDevideFlag()
  {
    return decimal_devide_flag;
  }


  /** 
   * <em>decimal_devide_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDecimalDevideFlagIsSet() {
    return decimal_devide_flag_is_set;
  }


  /** 
   * <em>decimal_devide_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDecimalDevideFlagIsModified() {
    return decimal_devide_flag_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new EqtypeProductPK(product_id);
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
   * <em>mini_stock_lot_size</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_miniStockLotSize <em>mini_stock_lot_size</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setMiniStockLotSize( double p_miniStockLotSize )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_lot_size = new Double(p_miniStockLotSize);
    mini_stock_lot_size_is_set = true;
    mini_stock_lot_size_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>mini_stock_lot_size</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setMiniStockLotSize( Double p_miniStockLotSize )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_lot_size = p_miniStockLotSize;
    mini_stock_lot_size_is_set = true;
    mini_stock_lot_size_is_modified = true;
  }


  /** 
   * <em>yearly_books_closing_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_yearlyBooksClosingDate <em>yearly_books_closing_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setYearlyBooksClosingDate( java.sql.Timestamp p_yearlyBooksClosingDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    yearly_books_closing_date = p_yearlyBooksClosingDate;
    yearly_books_closing_date_is_set = true;
    yearly_books_closing_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>yearly_books_closing_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setYearlyBooksClosingDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    yearly_books_closing_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    yearly_books_closing_date_is_set = true;
    yearly_books_closing_date_is_modified = true;
  }


  /** 
   * <em>trade_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tradeStop <em>trade_stop</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setTradeStop( int p_tradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_stop = new Integer(p_tradeStop);
    trade_stop_is_set = true;
    trade_stop_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>trade_stop</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTradeStop( Integer p_tradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trade_stop = p_tradeStop;
    trade_stop_is_set = true;
    trade_stop_is_modified = true;
  }


  /** 
   * <em>margin_sys_trade_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marginSysTradeStop <em>margin_sys_trade_stop</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setMarginSysTradeStop( int p_marginSysTradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sys_trade_stop = new Integer(p_marginSysTradeStop);
    margin_sys_trade_stop_is_set = true;
    margin_sys_trade_stop_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>margin_sys_trade_stop</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setMarginSysTradeStop( Integer p_marginSysTradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sys_trade_stop = p_marginSysTradeStop;
    margin_sys_trade_stop_is_set = true;
    margin_sys_trade_stop_is_modified = true;
  }


  /** 
   * <em>margin_gen_trade_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marginGenTradeStop <em>margin_gen_trade_stop</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setMarginGenTradeStop( int p_marginGenTradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_gen_trade_stop = new Integer(p_marginGenTradeStop);
    margin_gen_trade_stop_is_set = true;
    margin_gen_trade_stop_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>margin_gen_trade_stop</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setMarginGenTradeStop( Integer p_marginGenTradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_gen_trade_stop = p_marginGenTradeStop;
    margin_gen_trade_stop_is_set = true;
    margin_gen_trade_stop_is_modified = true;
  }


  /** 
   * <em>capital_gain_tax_dealings_reg</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_capitalGainTaxDealingsReg <em>capital_gain_tax_dealings_reg</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setCapitalGainTaxDealingsReg( int p_capitalGainTaxDealingsReg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain_tax_dealings_reg = new Integer(p_capitalGainTaxDealingsReg);
    capital_gain_tax_dealings_reg_is_set = true;
    capital_gain_tax_dealings_reg_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>capital_gain_tax_dealings_reg</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCapitalGainTaxDealingsReg( Integer p_capitalGainTaxDealingsReg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain_tax_dealings_reg = p_capitalGainTaxDealingsReg;
    capital_gain_tax_dealings_reg_is_set = true;
    capital_gain_tax_dealings_reg_is_modified = true;
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
   * <em>decimal_devide_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_decimalDevideFlag <em>decimal_devide_flag</em>�J�����̒l������킷1���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�l 
   */
  public final void setDecimalDevideFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_decimalDevideFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    decimal_devide_flag = p_decimalDevideFlag;
    decimal_devide_flag_is_set = true;
    decimal_devide_flag_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("capital_gain_tax_dealings_reg") ) {
                    return this.capital_gain_tax_dealings_reg;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("decimal_devide_flag") ) {
                    return this.decimal_devide_flag;
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
            case 'm':
                if ( name.equals("mini_stock_lot_size") ) {
                    return this.mini_stock_lot_size;
                }
                else if ( name.equals("margin_sys_trade_stop") ) {
                    return this.margin_sys_trade_stop;
                }
                else if ( name.equals("margin_gen_trade_stop") ) {
                    return this.margin_gen_trade_stop;
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
                if ( name.equals("standard_name") ) {
                    return this.standard_name;
                }
                break;
            case 't':
                if ( name.equals("trade_stop") ) {
                    return this.trade_stop;
                }
                break;
            case 'y':
                if ( name.equals("yearly_books_closing_date") ) {
                    return this.yearly_books_closing_date;
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
                if ( name.equals("capital_gain_tax_dealings_reg") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'capital_gain_tax_dealings_reg' must be Integer: '"+value+"' is not." );
                    this.capital_gain_tax_dealings_reg = (Integer) value;
                    if (this.capital_gain_tax_dealings_reg_is_set)
                        this.capital_gain_tax_dealings_reg_is_modified = true;
                    this.capital_gain_tax_dealings_reg_is_set = true;
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
                if ( name.equals("decimal_devide_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'decimal_devide_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.decimal_devide_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.decimal_devide_flag_is_set)
                        this.decimal_devide_flag_is_modified = true;
                    this.decimal_devide_flag_is_set = true;
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
                if ( name.equals("mini_stock_lot_size") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'mini_stock_lot_size' must be Double: '"+value+"' is not." );
                    this.mini_stock_lot_size = (Double) value;
                    if (this.mini_stock_lot_size_is_set)
                        this.mini_stock_lot_size_is_modified = true;
                    this.mini_stock_lot_size_is_set = true;
                    return;
                }
                else if ( name.equals("margin_sys_trade_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'margin_sys_trade_stop' must be Integer: '"+value+"' is not." );
                    this.margin_sys_trade_stop = (Integer) value;
                    if (this.margin_sys_trade_stop_is_set)
                        this.margin_sys_trade_stop_is_modified = true;
                    this.margin_sys_trade_stop_is_set = true;
                    return;
                }
                else if ( name.equals("margin_gen_trade_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'margin_gen_trade_stop' must be Integer: '"+value+"' is not." );
                    this.margin_gen_trade_stop = (Integer) value;
                    if (this.margin_gen_trade_stop_is_set)
                        this.margin_gen_trade_stop_is_modified = true;
                    this.margin_gen_trade_stop_is_set = true;
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
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
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
            case 's':
                if ( name.equals("standard_name") ) {
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
                if ( name.equals("trade_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'trade_stop' must be Integer: '"+value+"' is not." );
                    this.trade_stop = (Integer) value;
                    if (this.trade_stop_is_set)
                        this.trade_stop_is_modified = true;
                    this.trade_stop_is_set = true;
                    return;
                }
                break;
            case 'y':
                if ( name.equals("yearly_books_closing_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'yearly_books_closing_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.yearly_books_closing_date = (java.sql.Timestamp) value;
                    if (this.yearly_books_closing_date_is_set)
                        this.yearly_books_closing_date_is_modified = true;
                    this.yearly_books_closing_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
