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
 * eqtype_productテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link EqtypeProductRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link EqtypeProductParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link EqtypeProductParams}が{@@link EqtypeProductRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeProductPK 
 * @@see EqtypeProductRow 
 */
public class EqtypeProductParams extends Params implements EqtypeProductRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "eqtype_product";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = EqtypeProductRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return EqtypeProductRow.TYPE;
  }


  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>standard_name</em>カラムの値 
   */
  public  String  standard_name;

  /** 
   * <em>mini_stock_lot_size</em>カラムの値 
   */
  public  Double  mini_stock_lot_size;

  /** 
   * <em>yearly_books_closing_date</em>カラムの値 
   */
  public  java.sql.Timestamp  yearly_books_closing_date;

  /** 
   * <em>trade_stop</em>カラムの値 
   */
  public  Integer  trade_stop;

  /** 
   * <em>margin_sys_trade_stop</em>カラムの値 
   */
  public  Integer  margin_sys_trade_stop;

  /** 
   * <em>margin_gen_trade_stop</em>カラムの値 
   */
  public  Integer  margin_gen_trade_stop;

  /** 
   * <em>capital_gain_tax_dealings_reg</em>カラムの値 
   */
  public  Integer  capital_gain_tax_dealings_reg;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>decimal_devide_flag</em>カラムの値 
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
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
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
   * 値が未設定のEqtypeProductParamsオブジェクトを作成します。 
   */
  public EqtypeProductParams() {
    product_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のEqtypeProductRowオブジェクトの値を利用してEqtypeProductParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するEqtypeProductRowオブジェクト 
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
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof EqtypeProductRow ) )
       return false;
    return fieldsEqual( (EqtypeProductRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のEqtypeProductRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
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
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
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
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getProductId()
  {
    return product_id;
  }


  /** 
   * <em>product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsSet() {
    return product_id_is_set;
  }


  /** 
   * <em>product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsModified() {
    return product_id_is_modified;
  }


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsSet() {
    return product_code_is_set;
  }


  /** 
   * <em>product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsModified() {
    return product_code_is_modified;
  }


  /** 
   * <em>product_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType()
  {
    return product_type;
  }


  /** 
   * <em>product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsSet() {
    return product_type_is_set;
  }


  /** 
   * <em>product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsModified() {
    return product_type_is_modified;
  }


  /** 
   * <em>standard_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStandardName()
  {
    return standard_name;
  }


  /** 
   * <em>standard_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandardNameIsSet() {
    return standard_name_is_set;
  }


  /** 
   * <em>standard_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandardNameIsModified() {
    return standard_name_is_modified;
  }


  /** 
   * <em>mini_stock_lot_size</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMiniStockLotSize()
  {
    return ( mini_stock_lot_size==null? ((double)0): mini_stock_lot_size.doubleValue() );
  }


  /** 
   * <em>mini_stock_lot_size</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMiniStockLotSizeIsNull()
  {
    return mini_stock_lot_size == null;
  }


  /** 
   * <em>mini_stock_lot_size</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockLotSizeIsSet() {
    return mini_stock_lot_size_is_set;
  }


  /** 
   * <em>mini_stock_lot_size</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockLotSizeIsModified() {
    return mini_stock_lot_size_is_modified;
  }


  /** 
   * <em>yearly_books_closing_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getYearlyBooksClosingDate()
  {
    return yearly_books_closing_date;
  }


  /** 
   * <em>yearly_books_closing_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getYearlyBooksClosingDateIsSet() {
    return yearly_books_closing_date_is_set;
  }


  /** 
   * <em>yearly_books_closing_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getYearlyBooksClosingDateIsModified() {
    return yearly_books_closing_date_is_modified;
  }


  /** 
   * <em>trade_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getTradeStop()
  {
    return ( trade_stop==null? ((int)0): trade_stop.intValue() );
  }


  /** 
   * <em>trade_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTradeStopIsNull()
  {
    return trade_stop == null;
  }


  /** 
   * <em>trade_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeStopIsSet() {
    return trade_stop_is_set;
  }


  /** 
   * <em>trade_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeStopIsModified() {
    return trade_stop_is_modified;
  }


  /** 
   * <em>margin_sys_trade_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getMarginSysTradeStop()
  {
    return ( margin_sys_trade_stop==null? ((int)0): margin_sys_trade_stop.intValue() );
  }


  /** 
   * <em>margin_sys_trade_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginSysTradeStopIsNull()
  {
    return margin_sys_trade_stop == null;
  }


  /** 
   * <em>margin_sys_trade_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSysTradeStopIsSet() {
    return margin_sys_trade_stop_is_set;
  }


  /** 
   * <em>margin_sys_trade_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSysTradeStopIsModified() {
    return margin_sys_trade_stop_is_modified;
  }


  /** 
   * <em>margin_gen_trade_stop</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getMarginGenTradeStop()
  {
    return ( margin_gen_trade_stop==null? ((int)0): margin_gen_trade_stop.intValue() );
  }


  /** 
   * <em>margin_gen_trade_stop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginGenTradeStopIsNull()
  {
    return margin_gen_trade_stop == null;
  }


  /** 
   * <em>margin_gen_trade_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginGenTradeStopIsSet() {
    return margin_gen_trade_stop_is_set;
  }


  /** 
   * <em>margin_gen_trade_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginGenTradeStopIsModified() {
    return margin_gen_trade_stop_is_modified;
  }


  /** 
   * <em>capital_gain_tax_dealings_reg</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getCapitalGainTaxDealingsReg()
  {
    return ( capital_gain_tax_dealings_reg==null? ((int)0): capital_gain_tax_dealings_reg.intValue() );
  }


  /** 
   * <em>capital_gain_tax_dealings_reg</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCapitalGainTaxDealingsRegIsNull()
  {
    return capital_gain_tax_dealings_reg == null;
  }


  /** 
   * <em>capital_gain_tax_dealings_reg</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainTaxDealingsRegIsSet() {
    return capital_gain_tax_dealings_reg_is_set;
  }


  /** 
   * <em>capital_gain_tax_dealings_reg</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainTaxDealingsRegIsModified() {
    return capital_gain_tax_dealings_reg_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * <em>decimal_devide_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDecimalDevideFlag()
  {
    return decimal_devide_flag;
  }


  /** 
   * <em>decimal_devide_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDecimalDevideFlagIsSet() {
    return decimal_devide_flag_is_set;
  }


  /** 
   * <em>decimal_devide_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
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
   * <em>product_id</em>カラムの値を設定します。 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setProductId( long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = p_productId;
    product_id_is_set = true;
    product_id_is_modified = true;
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>product_type</em>カラムの値を設定します。 
   *
   * @@param p_productType <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public final void setProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_type = p_productType;
    product_type_is_set = true;
    product_type_is_modified = true;
  }


  /** 
   * <em>standard_name</em>カラムの値を設定します。 
   *
   * @@param p_standardName <em>standard_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setStandardName( String p_standardName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    standard_name = p_standardName;
    standard_name_is_set = true;
    standard_name_is_modified = true;
  }


  /** 
   * <em>mini_stock_lot_size</em>カラムの値を設定します。 
   *
   * @@param p_miniStockLotSize <em>mini_stock_lot_size</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMiniStockLotSize( double p_miniStockLotSize )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_lot_size = new Double(p_miniStockLotSize);
    mini_stock_lot_size_is_set = true;
    mini_stock_lot_size_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>mini_stock_lot_size</em>カラムに値を設定します。 
   */
  public final void setMiniStockLotSize( Double p_miniStockLotSize )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_lot_size = p_miniStockLotSize;
    mini_stock_lot_size_is_set = true;
    mini_stock_lot_size_is_modified = true;
  }


  /** 
   * <em>yearly_books_closing_date</em>カラムの値を設定します。 
   *
   * @@param p_yearlyBooksClosingDate <em>yearly_books_closing_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setYearlyBooksClosingDate( java.sql.Timestamp p_yearlyBooksClosingDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    yearly_books_closing_date = p_yearlyBooksClosingDate;
    yearly_books_closing_date_is_set = true;
    yearly_books_closing_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>yearly_books_closing_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setYearlyBooksClosingDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    yearly_books_closing_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    yearly_books_closing_date_is_set = true;
    yearly_books_closing_date_is_modified = true;
  }


  /** 
   * <em>trade_stop</em>カラムの値を設定します。 
   *
   * @@param p_tradeStop <em>trade_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setTradeStop( int p_tradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_stop = new Integer(p_tradeStop);
    trade_stop_is_set = true;
    trade_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>trade_stop</em>カラムに値を設定します。 
   */
  public final void setTradeStop( Integer p_tradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trade_stop = p_tradeStop;
    trade_stop_is_set = true;
    trade_stop_is_modified = true;
  }


  /** 
   * <em>margin_sys_trade_stop</em>カラムの値を設定します。 
   *
   * @@param p_marginSysTradeStop <em>margin_sys_trade_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setMarginSysTradeStop( int p_marginSysTradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sys_trade_stop = new Integer(p_marginSysTradeStop);
    margin_sys_trade_stop_is_set = true;
    margin_sys_trade_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_sys_trade_stop</em>カラムに値を設定します。 
   */
  public final void setMarginSysTradeStop( Integer p_marginSysTradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sys_trade_stop = p_marginSysTradeStop;
    margin_sys_trade_stop_is_set = true;
    margin_sys_trade_stop_is_modified = true;
  }


  /** 
   * <em>margin_gen_trade_stop</em>カラムの値を設定します。 
   *
   * @@param p_marginGenTradeStop <em>margin_gen_trade_stop</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setMarginGenTradeStop( int p_marginGenTradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_gen_trade_stop = new Integer(p_marginGenTradeStop);
    margin_gen_trade_stop_is_set = true;
    margin_gen_trade_stop_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_gen_trade_stop</em>カラムに値を設定します。 
   */
  public final void setMarginGenTradeStop( Integer p_marginGenTradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_gen_trade_stop = p_marginGenTradeStop;
    margin_gen_trade_stop_is_set = true;
    margin_gen_trade_stop_is_modified = true;
  }


  /** 
   * <em>capital_gain_tax_dealings_reg</em>カラムの値を設定します。 
   *
   * @@param p_capitalGainTaxDealingsReg <em>capital_gain_tax_dealings_reg</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setCapitalGainTaxDealingsReg( int p_capitalGainTaxDealingsReg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain_tax_dealings_reg = new Integer(p_capitalGainTaxDealingsReg);
    capital_gain_tax_dealings_reg_is_set = true;
    capital_gain_tax_dealings_reg_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>capital_gain_tax_dealings_reg</em>カラムに値を設定します。 
   */
  public final void setCapitalGainTaxDealingsReg( Integer p_capitalGainTaxDealingsReg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain_tax_dealings_reg = p_capitalGainTaxDealingsReg;
    capital_gain_tax_dealings_reg_is_set = true;
    capital_gain_tax_dealings_reg_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * <em>decimal_devide_flag</em>カラムの値を設定します。 
   *
   * @@param p_decimalDevideFlag <em>decimal_devide_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
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
