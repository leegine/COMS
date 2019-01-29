head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.02.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88656402a7;
filename	FeqProductParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbfeq.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * feq_productテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link FeqProductRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link FeqProductParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link FeqProductParams}が{@@link FeqProductRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FeqProductPK 
 * @@see FeqProductRow 
 */
public class FeqProductParams extends Params implements FeqProductRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "feq_product";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = FeqProductRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return FeqProductRow.TYPE;
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
   * <em>standard_name_kana</em>カラムの値 
   */
  public  String  standard_name_kana;

  /** 
   * <em>standard_name_kanji</em>カラムの値 
   */
  public  String  standard_name_kanji;

  /** 
   * <em>offshore_product_code</em>カラムの値 
   */
  public  String  offshore_product_code;

  /** 
   * <em>market_code</em>カラムの値 
   */
  public  String  market_code;

  /** 
   * <em>currency_code</em>カラムの値 
   */
  public  String  currency_code;

  /** 
   * <em>books_closing_ym1</em>カラムの値 
   */
  public  String  books_closing_ym1;

  /** 
   * <em>books_closing_ym2</em>カラムの値 
   */
  public  String  books_closing_ym2;

  /** 
   * <em>offering_date</em>カラムの値 
   */
  public  java.sql.Timestamp  offering_date;

  /** 
   * <em>ex_right_date</em>カラムの値 
   */
  public  java.sql.Timestamp  ex_right_date;

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

  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean standard_name_kana_is_set = false;
  private boolean standard_name_kana_is_modified = false;
  private boolean standard_name_kanji_is_set = false;
  private boolean standard_name_kanji_is_modified = false;
  private boolean offshore_product_code_is_set = false;
  private boolean offshore_product_code_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean books_closing_ym1_is_set = false;
  private boolean books_closing_ym1_is_modified = false;
  private boolean books_closing_ym2_is_set = false;
  private boolean books_closing_ym2_is_modified = false;
  private boolean offering_date_is_set = false;
  private boolean offering_date_is_modified = false;
  private boolean ex_right_date_is_set = false;
  private boolean ex_right_date_is_modified = false;
  private boolean capital_gain_tax_dealings_reg_is_set = false;
  private boolean capital_gain_tax_dealings_reg_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "product_id=" + product_id
      + "," + "institution_code=" +institution_code
      + "," + "product_code=" +product_code
      + "," + "product_type=" +product_type
      + "," + "standard_name_kana=" +standard_name_kana
      + "," + "standard_name_kanji=" +standard_name_kanji
      + "," + "offshore_product_code=" +offshore_product_code
      + "," + "market_code=" +market_code
      + "," + "currency_code=" +currency_code
      + "," + "books_closing_ym1=" +books_closing_ym1
      + "," + "books_closing_ym2=" +books_closing_ym2
      + "," + "offering_date=" +offering_date
      + "," + "ex_right_date=" +ex_right_date
      + "," + "capital_gain_tax_dealings_reg=" +capital_gain_tax_dealings_reg
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のFeqProductParamsオブジェクトを作成します。 
   */
  public FeqProductParams() {
    product_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のFeqProductRowオブジェクトの値を利用してFeqProductParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するFeqProductRowオブジェクト 
   */
  public FeqProductParams( FeqProductRow p_row )
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
    standard_name_kana = p_row.getStandardNameKana();
    standard_name_kana_is_set = p_row.getStandardNameKanaIsSet();
    standard_name_kana_is_modified = p_row.getStandardNameKanaIsModified();
    standard_name_kanji = p_row.getStandardNameKanji();
    standard_name_kanji_is_set = p_row.getStandardNameKanjiIsSet();
    standard_name_kanji_is_modified = p_row.getStandardNameKanjiIsModified();
    offshore_product_code = p_row.getOffshoreProductCode();
    offshore_product_code_is_set = p_row.getOffshoreProductCodeIsSet();
    offshore_product_code_is_modified = p_row.getOffshoreProductCodeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    books_closing_ym1 = p_row.getBooksClosingYm1();
    books_closing_ym1_is_set = p_row.getBooksClosingYm1IsSet();
    books_closing_ym1_is_modified = p_row.getBooksClosingYm1IsModified();
    books_closing_ym2 = p_row.getBooksClosingYm2();
    books_closing_ym2_is_set = p_row.getBooksClosingYm2IsSet();
    books_closing_ym2_is_modified = p_row.getBooksClosingYm2IsModified();
    offering_date = p_row.getOfferingDate();
    offering_date_is_set = p_row.getOfferingDateIsSet();
    offering_date_is_modified = p_row.getOfferingDateIsModified();
    ex_right_date = p_row.getExRightDate();
    ex_right_date_is_set = p_row.getExRightDateIsSet();
    ex_right_date_is_modified = p_row.getExRightDateIsModified();
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
      standard_name_kana_is_set = true;
      standard_name_kana_is_modified = true;
      standard_name_kanji_is_set = true;
      standard_name_kanji_is_modified = true;
      offshore_product_code_is_set = true;
      offshore_product_code_is_modified = true;
      market_code_is_set = true;
      market_code_is_modified = true;
      currency_code_is_set = true;
      currency_code_is_modified = true;
      books_closing_ym1_is_set = true;
      books_closing_ym1_is_modified = true;
      books_closing_ym2_is_set = true;
      books_closing_ym2_is_modified = true;
      offering_date_is_set = true;
      offering_date_is_modified = true;
      ex_right_date_is_set = true;
      ex_right_date_is_modified = true;
      capital_gain_tax_dealings_reg_is_set = true;
      capital_gain_tax_dealings_reg_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof FeqProductRow ) )
       return false;
    return fieldsEqual( (FeqProductRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のFeqProductRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( FeqProductRow row )
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
    if ( standard_name_kana == null ) {
      if ( row.getStandardNameKana() != null )
        return false;
    } else if ( !standard_name_kana.equals( row.getStandardNameKana() ) ) {
        return false;
    }
    if ( standard_name_kanji == null ) {
      if ( row.getStandardNameKanji() != null )
        return false;
    } else if ( !standard_name_kanji.equals( row.getStandardNameKanji() ) ) {
        return false;
    }
    if ( offshore_product_code == null ) {
      if ( row.getOffshoreProductCode() != null )
        return false;
    } else if ( !offshore_product_code.equals( row.getOffshoreProductCode() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( books_closing_ym1 == null ) {
      if ( row.getBooksClosingYm1() != null )
        return false;
    } else if ( !books_closing_ym1.equals( row.getBooksClosingYm1() ) ) {
        return false;
    }
    if ( books_closing_ym2 == null ) {
      if ( row.getBooksClosingYm2() != null )
        return false;
    } else if ( !books_closing_ym2.equals( row.getBooksClosingYm2() ) ) {
        return false;
    }
    if ( offering_date == null ) {
      if ( row.getOfferingDate() != null )
        return false;
    } else if ( !offering_date.equals( row.getOfferingDate() ) ) {
        return false;
    }
    if ( ex_right_date == null ) {
      if ( row.getExRightDate() != null )
        return false;
    } else if ( !ex_right_date.equals( row.getExRightDate() ) ) {
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
        + (standard_name_kana!=null? standard_name_kana.hashCode(): 0) 
        + (standard_name_kanji!=null? standard_name_kanji.hashCode(): 0) 
        + (offshore_product_code!=null? offshore_product_code.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (books_closing_ym1!=null? books_closing_ym1.hashCode(): 0) 
        + (books_closing_ym2!=null? books_closing_ym2.hashCode(): 0) 
        + (offering_date!=null? offering_date.hashCode(): 0) 
        + (ex_right_date!=null? ex_right_date.hashCode(): 0) 
        + (capital_gain_tax_dealings_reg!=null? capital_gain_tax_dealings_reg.hashCode(): 0) 
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
		if ( !product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_code' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !market_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_code' must be set before inserting.");
		if ( !currency_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'currency_code' must be set before inserting.");
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
		if ( standard_name_kana != null )
			map.put("standard_name_kana",standard_name_kana);
		if ( standard_name_kanji != null )
			map.put("standard_name_kanji",standard_name_kanji);
		if ( offshore_product_code != null )
			map.put("offshore_product_code",offshore_product_code);
		map.put("market_code",market_code);
		map.put("currency_code",currency_code);
		if ( books_closing_ym1 != null )
			map.put("books_closing_ym1",books_closing_ym1);
		if ( books_closing_ym2 != null )
			map.put("books_closing_ym2",books_closing_ym2);
		if ( offering_date != null )
			map.put("offering_date",offering_date);
		if ( ex_right_date != null )
			map.put("ex_right_date",ex_right_date);
		if ( capital_gain_tax_dealings_reg != null )
			map.put("capital_gain_tax_dealings_reg",capital_gain_tax_dealings_reg);
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
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( standard_name_kana_is_modified )
			map.put("standard_name_kana",standard_name_kana);
		if ( standard_name_kanji_is_modified )
			map.put("standard_name_kanji",standard_name_kanji);
		if ( offshore_product_code_is_modified )
			map.put("offshore_product_code",offshore_product_code);
		if ( market_code_is_modified )
			map.put("market_code",market_code);
		if ( currency_code_is_modified )
			map.put("currency_code",currency_code);
		if ( books_closing_ym1_is_modified )
			map.put("books_closing_ym1",books_closing_ym1);
		if ( books_closing_ym2_is_modified )
			map.put("books_closing_ym2",books_closing_ym2);
		if ( offering_date_is_modified )
			map.put("offering_date",offering_date);
		if ( ex_right_date_is_modified )
			map.put("ex_right_date",ex_right_date);
		if ( capital_gain_tax_dealings_reg_is_modified )
			map.put("capital_gain_tax_dealings_reg",capital_gain_tax_dealings_reg);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( product_code_is_set )
				map.put("product_code",product_code);
			if ( product_type_is_set )
				map.put("product_type",product_type);
			map.put("standard_name_kana",standard_name_kana);
			map.put("standard_name_kanji",standard_name_kanji);
			map.put("offshore_product_code",offshore_product_code);
			if ( market_code_is_set )
				map.put("market_code",market_code);
			if ( currency_code_is_set )
				map.put("currency_code",currency_code);
			map.put("books_closing_ym1",books_closing_ym1);
			map.put("books_closing_ym2",books_closing_ym2);
			map.put("offering_date",offering_date);
			map.put("ex_right_date",ex_right_date);
			map.put("capital_gain_tax_dealings_reg",capital_gain_tax_dealings_reg);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
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
   * <em>standard_name_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStandardNameKana()
  {
    return standard_name_kana;
  }


  /** 
   * <em>standard_name_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandardNameKanaIsSet() {
    return standard_name_kana_is_set;
  }


  /** 
   * <em>standard_name_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandardNameKanaIsModified() {
    return standard_name_kana_is_modified;
  }


  /** 
   * <em>standard_name_kanji</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStandardNameKanji()
  {
    return standard_name_kanji;
  }


  /** 
   * <em>standard_name_kanji</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandardNameKanjiIsSet() {
    return standard_name_kanji_is_set;
  }


  /** 
   * <em>standard_name_kanji</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandardNameKanjiIsModified() {
    return standard_name_kanji_is_modified;
  }


  /** 
   * <em>offshore_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOffshoreProductCode()
  {
    return offshore_product_code;
  }


  /** 
   * <em>offshore_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOffshoreProductCodeIsSet() {
    return offshore_product_code_is_set;
  }


  /** 
   * <em>offshore_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOffshoreProductCodeIsModified() {
    return offshore_product_code_is_modified;
  }


  /** 
   * <em>market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarketCode()
  {
    return market_code;
  }


  /** 
   * <em>market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsSet() {
    return market_code_is_set;
  }


  /** 
   * <em>market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsModified() {
    return market_code_is_modified;
  }


  /** 
   * <em>currency_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCurrencyCode()
  {
    return currency_code;
  }


  /** 
   * <em>currency_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsSet() {
    return currency_code_is_set;
  }


  /** 
   * <em>currency_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsModified() {
    return currency_code_is_modified;
  }


  /** 
   * <em>books_closing_ym1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBooksClosingYm1()
  {
    return books_closing_ym1;
  }


  /** 
   * <em>books_closing_ym1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBooksClosingYm1IsSet() {
    return books_closing_ym1_is_set;
  }


  /** 
   * <em>books_closing_ym1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBooksClosingYm1IsModified() {
    return books_closing_ym1_is_modified;
  }


  /** 
   * <em>books_closing_ym2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBooksClosingYm2()
  {
    return books_closing_ym2;
  }


  /** 
   * <em>books_closing_ym2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBooksClosingYm2IsSet() {
    return books_closing_ym2_is_set;
  }


  /** 
   * <em>books_closing_ym2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBooksClosingYm2IsModified() {
    return books_closing_ym2_is_modified;
  }


  /** 
   * <em>offering_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOfferingDate()
  {
    return offering_date;
  }


  /** 
   * <em>offering_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfferingDateIsSet() {
    return offering_date_is_set;
  }


  /** 
   * <em>offering_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfferingDateIsModified() {
    return offering_date_is_modified;
  }


  /** 
   * <em>ex_right_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getExRightDate()
  {
    return ex_right_date;
  }


  /** 
   * <em>ex_right_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExRightDateIsSet() {
    return ex_right_date_is_set;
  }


  /** 
   * <em>ex_right_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExRightDateIsModified() {
    return ex_right_date_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new FeqProductPK(product_id);
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
   * <em>standard_name_kana</em>カラムの値を設定します。 
   *
   * @@param p_standardNameKana <em>standard_name_kana</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setStandardNameKana( String p_standardNameKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    standard_name_kana = p_standardNameKana;
    standard_name_kana_is_set = true;
    standard_name_kana_is_modified = true;
  }


  /** 
   * <em>standard_name_kanji</em>カラムの値を設定します。 
   *
   * @@param p_standardNameKanji <em>standard_name_kanji</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setStandardNameKanji( String p_standardNameKanji )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    standard_name_kanji = p_standardNameKanji;
    standard_name_kanji_is_set = true;
    standard_name_kanji_is_modified = true;
  }


  /** 
   * <em>offshore_product_code</em>カラムの値を設定します。 
   *
   * @@param p_offshoreProductCode <em>offshore_product_code</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setOffshoreProductCode( String p_offshoreProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    offshore_product_code = p_offshoreProductCode;
    offshore_product_code_is_set = true;
    offshore_product_code_is_modified = true;
  }


  /** 
   * <em>market_code</em>カラムの値を設定します。 
   *
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setMarketCode( String p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
    market_code_is_modified = true;
  }


  /** 
   * <em>currency_code</em>カラムの値を設定します。 
   *
   * @@param p_currencyCode <em>currency_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>books_closing_ym1</em>カラムの値を設定します。 
   *
   * @@param p_booksClosingYm1 <em>books_closing_ym1</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setBooksClosingYm1( String p_booksClosingYm1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    books_closing_ym1 = p_booksClosingYm1;
    books_closing_ym1_is_set = true;
    books_closing_ym1_is_modified = true;
  }


  /** 
   * <em>books_closing_ym2</em>カラムの値を設定します。 
   *
   * @@param p_booksClosingYm2 <em>books_closing_ym2</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setBooksClosingYm2( String p_booksClosingYm2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    books_closing_ym2 = p_booksClosingYm2;
    books_closing_ym2_is_set = true;
    books_closing_ym2_is_modified = true;
  }


  /** 
   * <em>offering_date</em>カラムの値を設定します。 
   *
   * @@param p_offeringDate <em>offering_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOfferingDate( java.sql.Timestamp p_offeringDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    offering_date = p_offeringDate;
    offering_date_is_set = true;
    offering_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>offering_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOfferingDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    offering_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    offering_date_is_set = true;
    offering_date_is_modified = true;
  }


  /** 
   * <em>ex_right_date</em>カラムの値を設定します。 
   *
   * @@param p_exRightDate <em>ex_right_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setExRightDate( java.sql.Timestamp p_exRightDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ex_right_date = p_exRightDate;
    ex_right_date_is_set = true;
    ex_right_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>ex_right_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setExRightDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ex_right_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    ex_right_date_is_set = true;
    ex_right_date_is_modified = true;
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
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'b':
                if ( name.equals("books_closing_ym1") ) {
                    return this.books_closing_ym1;
                }
                else if ( name.equals("books_closing_ym2") ) {
                    return this.books_closing_ym2;
                }
                break;
            case 'c':
                if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("capital_gain_tax_dealings_reg") ) {
                    return this.capital_gain_tax_dealings_reg;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("ex_right_date") ) {
                    return this.ex_right_date;
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
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'o':
                if ( name.equals("offshore_product_code") ) {
                    return this.offshore_product_code;
                }
                else if ( name.equals("offering_date") ) {
                    return this.offering_date;
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
                if ( name.equals("standard_name_kana") ) {
                    return this.standard_name_kana;
                }
                else if ( name.equals("standard_name_kanji") ) {
                    return this.standard_name_kanji;
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
                if ( name.equals("books_closing_ym1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'books_closing_ym1' must be String: '"+value+"' is not." );
                    this.books_closing_ym1 = (String) value;
                    if (this.books_closing_ym1_is_set)
                        this.books_closing_ym1_is_modified = true;
                    this.books_closing_ym1_is_set = true;
                    return;
                }
                else if ( name.equals("books_closing_ym2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'books_closing_ym2' must be String: '"+value+"' is not." );
                    this.books_closing_ym2 = (String) value;
                    if (this.books_closing_ym2_is_set)
                        this.books_closing_ym2_is_modified = true;
                    this.books_closing_ym2_is_set = true;
                    return;
                }
                break;
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
                else if ( name.equals("capital_gain_tax_dealings_reg") ) {
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
            case 'e':
                if ( name.equals("ex_right_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'ex_right_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.ex_right_date = (java.sql.Timestamp) value;
                    if (this.ex_right_date_is_set)
                        this.ex_right_date_is_modified = true;
                    this.ex_right_date_is_set = true;
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
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("offshore_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'offshore_product_code' must be String: '"+value+"' is not." );
                    this.offshore_product_code = (String) value;
                    if (this.offshore_product_code_is_set)
                        this.offshore_product_code_is_modified = true;
                    this.offshore_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("offering_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'offering_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.offering_date = (java.sql.Timestamp) value;
                    if (this.offering_date_is_set)
                        this.offering_date_is_modified = true;
                    this.offering_date_is_set = true;
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
                if ( name.equals("standard_name_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'standard_name_kana' must be String: '"+value+"' is not." );
                    this.standard_name_kana = (String) value;
                    if (this.standard_name_kana_is_set)
                        this.standard_name_kana_is_modified = true;
                    this.standard_name_kana_is_set = true;
                    return;
                }
                else if ( name.equals("standard_name_kanji") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'standard_name_kanji' must be String: '"+value+"' is not." );
                    this.standard_name_kanji = (String) value;
                    if (this.standard_name_kanji_is_set)
                        this.standard_name_kanji_is_modified = true;
                    this.standard_name_kanji_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
