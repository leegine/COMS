head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	AccruedInterestCalcCondParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * accrued_interest_calc_condテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AccruedInterestCalcCondRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AccruedInterestCalcCondParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AccruedInterestCalcCondParams}が{@@link AccruedInterestCalcCondRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccruedInterestCalcCondPK 
 * @@see AccruedInterestCalcCondRow 
 */
public class AccruedInterestCalcCondParams extends Params implements AccruedInterestCalcCondRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "accrued_interest_calc_cond";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AccruedInterestCalcCondRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AccruedInterestCalcCondRow.TYPE;
  }


  /** 
   * <em>accrued_interest_calc_type</em>カラムの値 
   */
  public  String  accrued_interest_calc_type;

  /** 
   * <em>calc_type_name</em>カラムの値 
   */
  public  String  calc_type_name;

  /** 
   * <em>base_date_div</em>カラムの値 
   */
  public  String  base_date_div;

  /** 
   * <em>base_days_div</em>カラムの値 
   */
  public  String  base_days_div;

  /** 
   * <em>elapsed_days_div</em>カラムの値 
   */
  public  String  elapsed_days_div;

  /** 
   * <em>non_elapsed_days_div</em>カラムの値 
   */
  public  String  non_elapsed_days_div;

  /** 
   * <em>taxation_div</em>カラムの値 
   */
  public  String  taxation_div;

  /** 
   * <em>calc_base_form</em>カラムの値 
   */
  public  String  calc_base_form;

  /** 
   * <em>unit_price_scale</em>カラムの値 
   */
  public  Integer  unit_price_scale;

  /** 
   * <em>unit_round_div</em>カラムの値 
   */
  public  String  unit_round_div;

  /** 
   * <em>amount_round_div</em>カラムの値 
   */
  public  String  amount_round_div;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean accrued_interest_calc_type_is_set = false;
  private boolean accrued_interest_calc_type_is_modified = false;
  private boolean calc_type_name_is_set = false;
  private boolean calc_type_name_is_modified = false;
  private boolean base_date_div_is_set = false;
  private boolean base_date_div_is_modified = false;
  private boolean base_days_div_is_set = false;
  private boolean base_days_div_is_modified = false;
  private boolean elapsed_days_div_is_set = false;
  private boolean elapsed_days_div_is_modified = false;
  private boolean non_elapsed_days_div_is_set = false;
  private boolean non_elapsed_days_div_is_modified = false;
  private boolean taxation_div_is_set = false;
  private boolean taxation_div_is_modified = false;
  private boolean calc_base_form_is_set = false;
  private boolean calc_base_form_is_modified = false;
  private boolean unit_price_scale_is_set = false;
  private boolean unit_price_scale_is_modified = false;
  private boolean unit_round_div_is_set = false;
  private boolean unit_round_div_is_modified = false;
  private boolean amount_round_div_is_set = false;
  private boolean amount_round_div_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "accrued_interest_calc_type=" + accrued_interest_calc_type
      + "," + "calc_type_name=" +calc_type_name
      + "," + "base_date_div=" +base_date_div
      + "," + "base_days_div=" +base_days_div
      + "," + "elapsed_days_div=" +elapsed_days_div
      + "," + "non_elapsed_days_div=" +non_elapsed_days_div
      + "," + "taxation_div=" +taxation_div
      + "," + "calc_base_form=" +calc_base_form
      + "," + "unit_price_scale=" +unit_price_scale
      + "," + "unit_round_div=" +unit_round_div
      + "," + "amount_round_div=" +amount_round_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のAccruedInterestCalcCondParamsオブジェクトを作成します。 
   */
  public AccruedInterestCalcCondParams() {
    accrued_interest_calc_type_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAccruedInterestCalcCondRowオブジェクトの値を利用してAccruedInterestCalcCondParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAccruedInterestCalcCondRowオブジェクト 
   */
  public AccruedInterestCalcCondParams( AccruedInterestCalcCondRow p_row )
  {
    accrued_interest_calc_type = p_row.getAccruedInterestCalcType();
    accrued_interest_calc_type_is_set = p_row.getAccruedInterestCalcTypeIsSet();
    accrued_interest_calc_type_is_modified = p_row.getAccruedInterestCalcTypeIsModified();
    calc_type_name = p_row.getCalcTypeName();
    calc_type_name_is_set = p_row.getCalcTypeNameIsSet();
    calc_type_name_is_modified = p_row.getCalcTypeNameIsModified();
    base_date_div = p_row.getBaseDateDiv();
    base_date_div_is_set = p_row.getBaseDateDivIsSet();
    base_date_div_is_modified = p_row.getBaseDateDivIsModified();
    base_days_div = p_row.getBaseDaysDiv();
    base_days_div_is_set = p_row.getBaseDaysDivIsSet();
    base_days_div_is_modified = p_row.getBaseDaysDivIsModified();
    elapsed_days_div = p_row.getElapsedDaysDiv();
    elapsed_days_div_is_set = p_row.getElapsedDaysDivIsSet();
    elapsed_days_div_is_modified = p_row.getElapsedDaysDivIsModified();
    non_elapsed_days_div = p_row.getNonElapsedDaysDiv();
    non_elapsed_days_div_is_set = p_row.getNonElapsedDaysDivIsSet();
    non_elapsed_days_div_is_modified = p_row.getNonElapsedDaysDivIsModified();
    taxation_div = p_row.getTaxationDiv();
    taxation_div_is_set = p_row.getTaxationDivIsSet();
    taxation_div_is_modified = p_row.getTaxationDivIsModified();
    calc_base_form = p_row.getCalcBaseForm();
    calc_base_form_is_set = p_row.getCalcBaseFormIsSet();
    calc_base_form_is_modified = p_row.getCalcBaseFormIsModified();
    if ( !p_row.getUnitPriceScaleIsNull() )
      unit_price_scale = new Integer( p_row.getUnitPriceScale() );
    unit_price_scale_is_set = p_row.getUnitPriceScaleIsSet();
    unit_price_scale_is_modified = p_row.getUnitPriceScaleIsModified();
    unit_round_div = p_row.getUnitRoundDiv();
    unit_round_div_is_set = p_row.getUnitRoundDivIsSet();
    unit_round_div_is_modified = p_row.getUnitRoundDivIsModified();
    amount_round_div = p_row.getAmountRoundDiv();
    amount_round_div_is_set = p_row.getAmountRoundDivIsSet();
    amount_round_div_is_modified = p_row.getAmountRoundDivIsModified();
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
      calc_type_name_is_set = true;
      calc_type_name_is_modified = true;
      base_date_div_is_set = true;
      base_date_div_is_modified = true;
      base_days_div_is_set = true;
      base_days_div_is_modified = true;
      elapsed_days_div_is_set = true;
      elapsed_days_div_is_modified = true;
      non_elapsed_days_div_is_set = true;
      non_elapsed_days_div_is_modified = true;
      taxation_div_is_set = true;
      taxation_div_is_modified = true;
      calc_base_form_is_set = true;
      calc_base_form_is_modified = true;
      unit_price_scale_is_set = true;
      unit_price_scale_is_modified = true;
      unit_round_div_is_set = true;
      unit_round_div_is_modified = true;
      amount_round_div_is_set = true;
      amount_round_div_is_modified = true;
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
    if ( !( other instanceof AccruedInterestCalcCondRow ) )
       return false;
    return fieldsEqual( (AccruedInterestCalcCondRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAccruedInterestCalcCondRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AccruedInterestCalcCondRow row )
  {
    if ( accrued_interest_calc_type == null ) {
      if ( row.getAccruedInterestCalcType() != null )
        return false;
    } else if ( !accrued_interest_calc_type.equals( row.getAccruedInterestCalcType() ) ) {
        return false;
    }
    if ( calc_type_name == null ) {
      if ( row.getCalcTypeName() != null )
        return false;
    } else if ( !calc_type_name.equals( row.getCalcTypeName() ) ) {
        return false;
    }
    if ( base_date_div == null ) {
      if ( row.getBaseDateDiv() != null )
        return false;
    } else if ( !base_date_div.equals( row.getBaseDateDiv() ) ) {
        return false;
    }
    if ( base_days_div == null ) {
      if ( row.getBaseDaysDiv() != null )
        return false;
    } else if ( !base_days_div.equals( row.getBaseDaysDiv() ) ) {
        return false;
    }
    if ( elapsed_days_div == null ) {
      if ( row.getElapsedDaysDiv() != null )
        return false;
    } else if ( !elapsed_days_div.equals( row.getElapsedDaysDiv() ) ) {
        return false;
    }
    if ( non_elapsed_days_div == null ) {
      if ( row.getNonElapsedDaysDiv() != null )
        return false;
    } else if ( !non_elapsed_days_div.equals( row.getNonElapsedDaysDiv() ) ) {
        return false;
    }
    if ( taxation_div == null ) {
      if ( row.getTaxationDiv() != null )
        return false;
    } else if ( !taxation_div.equals( row.getTaxationDiv() ) ) {
        return false;
    }
    if ( calc_base_form == null ) {
      if ( row.getCalcBaseForm() != null )
        return false;
    } else if ( !calc_base_form.equals( row.getCalcBaseForm() ) ) {
        return false;
    }
    if ( unit_price_scale == null ) {
      if ( !row.getUnitPriceScaleIsNull() )
        return false;
    } else if ( row.getUnitPriceScaleIsNull() || ( unit_price_scale.intValue() != row.getUnitPriceScale() ) ) {
        return false;
    }
    if ( unit_round_div == null ) {
      if ( row.getUnitRoundDiv() != null )
        return false;
    } else if ( !unit_round_div.equals( row.getUnitRoundDiv() ) ) {
        return false;
    }
    if ( amount_round_div == null ) {
      if ( row.getAmountRoundDiv() != null )
        return false;
    } else if ( !amount_round_div.equals( row.getAmountRoundDiv() ) ) {
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
      return  (accrued_interest_calc_type!=null? accrued_interest_calc_type.hashCode(): 0) 
        + (calc_type_name!=null? calc_type_name.hashCode(): 0) 
        + (base_date_div!=null? base_date_div.hashCode(): 0) 
        + (base_days_div!=null? base_days_div.hashCode(): 0) 
        + (elapsed_days_div!=null? elapsed_days_div.hashCode(): 0) 
        + (non_elapsed_days_div!=null? non_elapsed_days_div.hashCode(): 0) 
        + (taxation_div!=null? taxation_div.hashCode(): 0) 
        + (calc_base_form!=null? calc_base_form.hashCode(): 0) 
        + (unit_price_scale!=null? unit_price_scale.hashCode(): 0) 
        + (unit_round_div!=null? unit_round_div.hashCode(): 0) 
        + (amount_round_div!=null? amount_round_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !calc_type_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'calc_type_name' must be set before inserting.");
		if ( !base_date_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'base_date_div' must be set before inserting.");
		if ( !base_days_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'base_days_div' must be set before inserting.");
		if ( !elapsed_days_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'elapsed_days_div' must be set before inserting.");
		if ( !non_elapsed_days_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'non_elapsed_days_div' must be set before inserting.");
		if ( !taxation_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'taxation_div' must be set before inserting.");
		if ( !calc_base_form_is_set )
			throw new IllegalArgumentException("non-nullable field 'calc_base_form' must be set before inserting.");
		if ( !amount_round_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'amount_round_div' must be set before inserting.");
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
		map.put("accrued_interest_calc_type",accrued_interest_calc_type);
		map.put("calc_type_name",calc_type_name);
		map.put("base_date_div",base_date_div);
		map.put("base_days_div",base_days_div);
		map.put("elapsed_days_div",elapsed_days_div);
		map.put("non_elapsed_days_div",non_elapsed_days_div);
		map.put("taxation_div",taxation_div);
		map.put("calc_base_form",calc_base_form);
		if ( unit_price_scale != null )
			map.put("unit_price_scale",unit_price_scale);
		if ( unit_round_div != null )
			map.put("unit_round_div",unit_round_div);
		map.put("amount_round_div",amount_round_div);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( calc_type_name_is_modified )
			map.put("calc_type_name",calc_type_name);
		if ( base_date_div_is_modified )
			map.put("base_date_div",base_date_div);
		if ( base_days_div_is_modified )
			map.put("base_days_div",base_days_div);
		if ( elapsed_days_div_is_modified )
			map.put("elapsed_days_div",elapsed_days_div);
		if ( non_elapsed_days_div_is_modified )
			map.put("non_elapsed_days_div",non_elapsed_days_div);
		if ( taxation_div_is_modified )
			map.put("taxation_div",taxation_div);
		if ( calc_base_form_is_modified )
			map.put("calc_base_form",calc_base_form);
		if ( unit_price_scale_is_modified )
			map.put("unit_price_scale",unit_price_scale);
		if ( unit_round_div_is_modified )
			map.put("unit_round_div",unit_round_div);
		if ( amount_round_div_is_modified )
			map.put("amount_round_div",amount_round_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( calc_type_name_is_set )
				map.put("calc_type_name",calc_type_name);
			if ( base_date_div_is_set )
				map.put("base_date_div",base_date_div);
			if ( base_days_div_is_set )
				map.put("base_days_div",base_days_div);
			if ( elapsed_days_div_is_set )
				map.put("elapsed_days_div",elapsed_days_div);
			if ( non_elapsed_days_div_is_set )
				map.put("non_elapsed_days_div",non_elapsed_days_div);
			if ( taxation_div_is_set )
				map.put("taxation_div",taxation_div);
			if ( calc_base_form_is_set )
				map.put("calc_base_form",calc_base_form);
			map.put("unit_price_scale",unit_price_scale);
			map.put("unit_round_div",unit_round_div);
			if ( amount_round_div_is_set )
				map.put("amount_round_div",amount_round_div);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>accrued_interest_calc_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccruedInterestCalcType()
  {
    return accrued_interest_calc_type;
  }


  /** 
   * <em>accrued_interest_calc_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccruedInterestCalcTypeIsSet() {
    return accrued_interest_calc_type_is_set;
  }


  /** 
   * <em>accrued_interest_calc_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccruedInterestCalcTypeIsModified() {
    return accrued_interest_calc_type_is_modified;
  }


  /** 
   * <em>calc_type_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCalcTypeName()
  {
    return calc_type_name;
  }


  /** 
   * <em>calc_type_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcTypeNameIsSet() {
    return calc_type_name_is_set;
  }


  /** 
   * <em>calc_type_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcTypeNameIsModified() {
    return calc_type_name_is_modified;
  }


  /** 
   * <em>base_date_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBaseDateDiv()
  {
    return base_date_div;
  }


  /** 
   * <em>base_date_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBaseDateDivIsSet() {
    return base_date_div_is_set;
  }


  /** 
   * <em>base_date_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBaseDateDivIsModified() {
    return base_date_div_is_modified;
  }


  /** 
   * <em>base_days_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBaseDaysDiv()
  {
    return base_days_div;
  }


  /** 
   * <em>base_days_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBaseDaysDivIsSet() {
    return base_days_div_is_set;
  }


  /** 
   * <em>base_days_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBaseDaysDivIsModified() {
    return base_days_div_is_modified;
  }


  /** 
   * <em>elapsed_days_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getElapsedDaysDiv()
  {
    return elapsed_days_div;
  }


  /** 
   * <em>elapsed_days_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getElapsedDaysDivIsSet() {
    return elapsed_days_div_is_set;
  }


  /** 
   * <em>elapsed_days_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getElapsedDaysDivIsModified() {
    return elapsed_days_div_is_modified;
  }


  /** 
   * <em>non_elapsed_days_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNonElapsedDaysDiv()
  {
    return non_elapsed_days_div;
  }


  /** 
   * <em>non_elapsed_days_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNonElapsedDaysDivIsSet() {
    return non_elapsed_days_div_is_set;
  }


  /** 
   * <em>non_elapsed_days_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNonElapsedDaysDivIsModified() {
    return non_elapsed_days_div_is_modified;
  }


  /** 
   * <em>taxation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTaxationDiv()
  {
    return taxation_div;
  }


  /** 
   * <em>taxation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxationDivIsSet() {
    return taxation_div_is_set;
  }


  /** 
   * <em>taxation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxationDivIsModified() {
    return taxation_div_is_modified;
  }


  /** 
   * <em>calc_base_form</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCalcBaseForm()
  {
    return calc_base_form;
  }


  /** 
   * <em>calc_base_form</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcBaseFormIsSet() {
    return calc_base_form_is_set;
  }


  /** 
   * <em>calc_base_form</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcBaseFormIsModified() {
    return calc_base_form_is_modified;
  }


  /** 
   * <em>unit_price_scale</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getUnitPriceScale()
  {
    return ( unit_price_scale==null? ((int)0): unit_price_scale.intValue() );
  }


  /** 
   * <em>unit_price_scale</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnitPriceScaleIsNull()
  {
    return unit_price_scale == null;
  }


  /** 
   * <em>unit_price_scale</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitPriceScaleIsSet() {
    return unit_price_scale_is_set;
  }


  /** 
   * <em>unit_price_scale</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitPriceScaleIsModified() {
    return unit_price_scale_is_modified;
  }


  /** 
   * <em>unit_round_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getUnitRoundDiv()
  {
    return unit_round_div;
  }


  /** 
   * <em>unit_round_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitRoundDivIsSet() {
    return unit_round_div_is_set;
  }


  /** 
   * <em>unit_round_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitRoundDivIsModified() {
    return unit_round_div_is_modified;
  }


  /** 
   * <em>amount_round_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAmountRoundDiv()
  {
    return amount_round_div;
  }


  /** 
   * <em>amount_round_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountRoundDivIsSet() {
    return amount_round_div_is_set;
  }


  /** 
   * <em>amount_round_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountRoundDivIsModified() {
    return amount_round_div_is_modified;
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
    return new AccruedInterestCalcCondPK(accrued_interest_calc_type);
  }


  /** 
   * <em>accrued_interest_calc_type</em>カラムの値を設定します。 
   *
   * @@param p_accruedInterestCalcType <em>accrued_interest_calc_type</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setAccruedInterestCalcType( String p_accruedInterestCalcType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    accrued_interest_calc_type = p_accruedInterestCalcType;
    accrued_interest_calc_type_is_set = true;
    accrued_interest_calc_type_is_modified = true;
  }


  /** 
   * <em>calc_type_name</em>カラムの値を設定します。 
   *
   * @@param p_calcTypeName <em>calc_type_name</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setCalcTypeName( String p_calcTypeName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_type_name = p_calcTypeName;
    calc_type_name_is_set = true;
    calc_type_name_is_modified = true;
  }


  /** 
   * <em>base_date_div</em>カラムの値を設定します。 
   *
   * @@param p_baseDateDiv <em>base_date_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBaseDateDiv( String p_baseDateDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_date_div = p_baseDateDiv;
    base_date_div_is_set = true;
    base_date_div_is_modified = true;
  }


  /** 
   * <em>base_days_div</em>カラムの値を設定します。 
   *
   * @@param p_baseDaysDiv <em>base_days_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBaseDaysDiv( String p_baseDaysDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_days_div = p_baseDaysDiv;
    base_days_div_is_set = true;
    base_days_div_is_modified = true;
  }


  /** 
   * <em>elapsed_days_div</em>カラムの値を設定します。 
   *
   * @@param p_elapsedDaysDiv <em>elapsed_days_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setElapsedDaysDiv( String p_elapsedDaysDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    elapsed_days_div = p_elapsedDaysDiv;
    elapsed_days_div_is_set = true;
    elapsed_days_div_is_modified = true;
  }


  /** 
   * <em>non_elapsed_days_div</em>カラムの値を設定します。 
   *
   * @@param p_nonElapsedDaysDiv <em>non_elapsed_days_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNonElapsedDaysDiv( String p_nonElapsedDaysDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    non_elapsed_days_div = p_nonElapsedDaysDiv;
    non_elapsed_days_div_is_set = true;
    non_elapsed_days_div_is_modified = true;
  }


  /** 
   * <em>taxation_div</em>カラムの値を設定します。 
   *
   * @@param p_taxationDiv <em>taxation_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTaxationDiv( String p_taxationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    taxation_div = p_taxationDiv;
    taxation_div_is_set = true;
    taxation_div_is_modified = true;
  }


  /** 
   * <em>calc_base_form</em>カラムの値を設定します。 
   *
   * @@param p_calcBaseForm <em>calc_base_form</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCalcBaseForm( String p_calcBaseForm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_base_form = p_calcBaseForm;
    calc_base_form_is_set = true;
    calc_base_form_is_modified = true;
  }


  /** 
   * <em>unit_price_scale</em>カラムの値を設定します。 
   *
   * @@param p_unitPriceScale <em>unit_price_scale</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setUnitPriceScale( int p_unitPriceScale )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unit_price_scale = new Integer(p_unitPriceScale);
    unit_price_scale_is_set = true;
    unit_price_scale_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unit_price_scale</em>カラムに値を設定します。 
   */
  public final void setUnitPriceScale( Integer p_unitPriceScale )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unit_price_scale = p_unitPriceScale;
    unit_price_scale_is_set = true;
    unit_price_scale_is_modified = true;
  }


  /** 
   * <em>unit_round_div</em>カラムの値を設定します。 
   *
   * @@param p_unitRoundDiv <em>unit_round_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setUnitRoundDiv( String p_unitRoundDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unit_round_div = p_unitRoundDiv;
    unit_round_div_is_set = true;
    unit_round_div_is_modified = true;
  }


  /** 
   * <em>amount_round_div</em>カラムの値を設定します。 
   *
   * @@param p_amountRoundDiv <em>amount_round_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAmountRoundDiv( String p_amountRoundDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_round_div = p_amountRoundDiv;
    amount_round_div_is_set = true;
    amount_round_div_is_modified = true;
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
            case 'a':
                if ( name.equals("accrued_interest_calc_type") ) {
                    return this.accrued_interest_calc_type;
                }
                else if ( name.equals("amount_round_div") ) {
                    return this.amount_round_div;
                }
                break;
            case 'b':
                if ( name.equals("base_date_div") ) {
                    return this.base_date_div;
                }
                else if ( name.equals("base_days_div") ) {
                    return this.base_days_div;
                }
                break;
            case 'c':
                if ( name.equals("calc_type_name") ) {
                    return this.calc_type_name;
                }
                else if ( name.equals("calc_base_form") ) {
                    return this.calc_base_form;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("elapsed_days_div") ) {
                    return this.elapsed_days_div;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("non_elapsed_days_div") ) {
                    return this.non_elapsed_days_div;
                }
                break;
            case 't':
                if ( name.equals("taxation_div") ) {
                    return this.taxation_div;
                }
                break;
            case 'u':
                if ( name.equals("unit_price_scale") ) {
                    return this.unit_price_scale;
                }
                else if ( name.equals("unit_round_div") ) {
                    return this.unit_round_div;
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
                if ( name.equals("accrued_interest_calc_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'accrued_interest_calc_type' must be String: '"+value+"' is not." );
                    this.accrued_interest_calc_type = (String) value;
                    if (this.accrued_interest_calc_type_is_set)
                        this.accrued_interest_calc_type_is_modified = true;
                    this.accrued_interest_calc_type_is_set = true;
                    return;
                }
                else if ( name.equals("amount_round_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'amount_round_div' must be String: '"+value+"' is not." );
                    this.amount_round_div = (String) value;
                    if (this.amount_round_div_is_set)
                        this.amount_round_div_is_modified = true;
                    this.amount_round_div_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("base_date_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'base_date_div' must be String: '"+value+"' is not." );
                    this.base_date_div = (String) value;
                    if (this.base_date_div_is_set)
                        this.base_date_div_is_modified = true;
                    this.base_date_div_is_set = true;
                    return;
                }
                else if ( name.equals("base_days_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'base_days_div' must be String: '"+value+"' is not." );
                    this.base_days_div = (String) value;
                    if (this.base_days_div_is_set)
                        this.base_days_div_is_modified = true;
                    this.base_days_div_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("calc_type_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'calc_type_name' must be String: '"+value+"' is not." );
                    this.calc_type_name = (String) value;
                    if (this.calc_type_name_is_set)
                        this.calc_type_name_is_modified = true;
                    this.calc_type_name_is_set = true;
                    return;
                }
                else if ( name.equals("calc_base_form") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'calc_base_form' must be String: '"+value+"' is not." );
                    this.calc_base_form = (String) value;
                    if (this.calc_base_form_is_set)
                        this.calc_base_form_is_modified = true;
                    this.calc_base_form_is_set = true;
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
                if ( name.equals("elapsed_days_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'elapsed_days_div' must be String: '"+value+"' is not." );
                    this.elapsed_days_div = (String) value;
                    if (this.elapsed_days_div_is_set)
                        this.elapsed_days_div_is_modified = true;
                    this.elapsed_days_div_is_set = true;
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
            case 'n':
                if ( name.equals("non_elapsed_days_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'non_elapsed_days_div' must be String: '"+value+"' is not." );
                    this.non_elapsed_days_div = (String) value;
                    if (this.non_elapsed_days_div_is_set)
                        this.non_elapsed_days_div_is_modified = true;
                    this.non_elapsed_days_div_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("taxation_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'taxation_div' must be String: '"+value+"' is not." );
                    this.taxation_div = (String) value;
                    if (this.taxation_div_is_set)
                        this.taxation_div_is_modified = true;
                    this.taxation_div_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("unit_price_scale") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'unit_price_scale' must be Integer: '"+value+"' is not." );
                    this.unit_price_scale = (Integer) value;
                    if (this.unit_price_scale_is_set)
                        this.unit_price_scale_is_modified = true;
                    this.unit_price_scale_is_set = true;
                    return;
                }
                else if ( name.equals("unit_round_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'unit_round_div' must be String: '"+value+"' is not." );
                    this.unit_round_div = (String) value;
                    if (this.unit_round_div_is_set)
                        this.unit_round_div_is_modified = true;
                    this.unit_round_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
