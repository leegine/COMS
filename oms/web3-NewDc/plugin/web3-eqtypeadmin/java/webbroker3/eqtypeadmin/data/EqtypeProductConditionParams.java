head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EqtypeProductConditionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * eqtype_product_conditionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link EqtypeProductConditionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link EqtypeProductConditionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link EqtypeProductConditionParams}が{@@link EqtypeProductConditionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeProductConditionPK 
 * @@see EqtypeProductConditionRow 
 */
public class EqtypeProductConditionParams extends Params implements EqtypeProductConditionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "eqtype_product_condition";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = EqtypeProductConditionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return EqtypeProductConditionRow.TYPE;
  }


  /** 
   * <em>eqtype_product_condition_id</em>カラムの値 
   */
  public  long  eqtype_product_condition_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>market_code</em>カラムの値 
   */
  public  String  market_code;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  long  market_id;

  /** 
   * <em>table_name</em>カラムの値 
   */
  public  String  table_name;

  /** 
   * <em>column_name</em>カラムの値 
   */
  public  String  column_name;

  /** 
   * <em>data_type</em>カラムの値 
   */
  public  String  data_type;

  /** 
   * <em>large_item_div</em>カラムの値 
   */
  public  String  large_item_div;

  /** 
   * <em>small_item_div</em>カラムの値 
   */
  public  String  small_item_div;

  /** 
   * <em>data_today</em>カラムの値 
   */
  public  String  data_today;

  /** 
   * <em>data_next_day</em>カラムの値 
   */
  public  String  data_next_day;

  /** 
   * <em>data_plan</em>カラムの値 
   */
  public  String  data_plan;

  /** 
   * <em>term_from</em>カラムの値 
   */
  public  java.sql.Timestamp  term_from;

  /** 
   * <em>term_to</em>カラムの値 
   */
  public  java.sql.Timestamp  term_to;

  /** 
   * <em>delete_flg</em>カラムの値 
   */
  public  String  delete_flg;

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

  private boolean eqtype_product_condition_id_is_set = false;
  private boolean eqtype_product_condition_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean table_name_is_set = false;
  private boolean table_name_is_modified = false;
  private boolean column_name_is_set = false;
  private boolean column_name_is_modified = false;
  private boolean data_type_is_set = false;
  private boolean data_type_is_modified = false;
  private boolean large_item_div_is_set = false;
  private boolean large_item_div_is_modified = false;
  private boolean small_item_div_is_set = false;
  private boolean small_item_div_is_modified = false;
  private boolean data_today_is_set = false;
  private boolean data_today_is_modified = false;
  private boolean data_next_day_is_set = false;
  private boolean data_next_day_is_modified = false;
  private boolean data_plan_is_set = false;
  private boolean data_plan_is_modified = false;
  private boolean term_from_is_set = false;
  private boolean term_from_is_modified = false;
  private boolean term_to_is_set = false;
  private boolean term_to_is_modified = false;
  private boolean delete_flg_is_set = false;
  private boolean delete_flg_is_modified = false;
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
       + "eqtype_product_condition_id=" + eqtype_product_condition_id
      + "," + "institution_code=" +institution_code
      + "," + "product_code=" +product_code
      + "," + "product_id=" +product_id
      + "," + "market_code=" +market_code
      + "," + "market_id=" +market_id
      + "," + "table_name=" +table_name
      + "," + "column_name=" +column_name
      + "," + "data_type=" +data_type
      + "," + "large_item_div=" +large_item_div
      + "," + "small_item_div=" +small_item_div
      + "," + "data_today=" +data_today
      + "," + "data_next_day=" +data_next_day
      + "," + "data_plan=" +data_plan
      + "," + "term_from=" +term_from
      + "," + "term_to=" +term_to
      + "," + "delete_flg=" +delete_flg
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のEqtypeProductConditionParamsオブジェクトを作成します。 
   */
  public EqtypeProductConditionParams() {
    eqtype_product_condition_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のEqtypeProductConditionRowオブジェクトの値を利用してEqtypeProductConditionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するEqtypeProductConditionRowオブジェクト 
   */
  public EqtypeProductConditionParams( EqtypeProductConditionRow p_row )
  {
    eqtype_product_condition_id = p_row.getEqtypeProductConditionId();
    eqtype_product_condition_id_is_set = p_row.getEqtypeProductConditionIdIsSet();
    eqtype_product_condition_id_is_modified = p_row.getEqtypeProductConditionIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    table_name = p_row.getTableName();
    table_name_is_set = p_row.getTableNameIsSet();
    table_name_is_modified = p_row.getTableNameIsModified();
    column_name = p_row.getColumnName();
    column_name_is_set = p_row.getColumnNameIsSet();
    column_name_is_modified = p_row.getColumnNameIsModified();
    data_type = p_row.getDataType();
    data_type_is_set = p_row.getDataTypeIsSet();
    data_type_is_modified = p_row.getDataTypeIsModified();
    large_item_div = p_row.getLargeItemDiv();
    large_item_div_is_set = p_row.getLargeItemDivIsSet();
    large_item_div_is_modified = p_row.getLargeItemDivIsModified();
    small_item_div = p_row.getSmallItemDiv();
    small_item_div_is_set = p_row.getSmallItemDivIsSet();
    small_item_div_is_modified = p_row.getSmallItemDivIsModified();
    data_today = p_row.getDataToday();
    data_today_is_set = p_row.getDataTodayIsSet();
    data_today_is_modified = p_row.getDataTodayIsModified();
    data_next_day = p_row.getDataNextDay();
    data_next_day_is_set = p_row.getDataNextDayIsSet();
    data_next_day_is_modified = p_row.getDataNextDayIsModified();
    data_plan = p_row.getDataPlan();
    data_plan_is_set = p_row.getDataPlanIsSet();
    data_plan_is_modified = p_row.getDataPlanIsModified();
    term_from = p_row.getTermFrom();
    term_from_is_set = p_row.getTermFromIsSet();
    term_from_is_modified = p_row.getTermFromIsModified();
    term_to = p_row.getTermTo();
    term_to_is_set = p_row.getTermToIsSet();
    term_to_is_modified = p_row.getTermToIsModified();
    delete_flg = p_row.getDeleteFlg();
    delete_flg_is_set = p_row.getDeleteFlgIsSet();
    delete_flg_is_modified = p_row.getDeleteFlgIsModified();
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
      product_id_is_set = true;
      product_id_is_modified = true;
      market_code_is_set = true;
      market_code_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      table_name_is_set = true;
      table_name_is_modified = true;
      column_name_is_set = true;
      column_name_is_modified = true;
      data_type_is_set = true;
      data_type_is_modified = true;
      large_item_div_is_set = true;
      large_item_div_is_modified = true;
      small_item_div_is_set = true;
      small_item_div_is_modified = true;
      data_today_is_set = true;
      data_today_is_modified = true;
      data_next_day_is_set = true;
      data_next_day_is_modified = true;
      data_plan_is_set = true;
      data_plan_is_modified = true;
      term_from_is_set = true;
      term_from_is_modified = true;
      term_to_is_set = true;
      term_to_is_modified = true;
      delete_flg_is_set = true;
      delete_flg_is_modified = true;
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
    if ( !( other instanceof EqtypeProductConditionRow ) )
       return false;
    return fieldsEqual( (EqtypeProductConditionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のEqtypeProductConditionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( EqtypeProductConditionRow row )
  {
    if ( eqtype_product_condition_id != row.getEqtypeProductConditionId() )
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
    if ( product_id != row.getProductId() )
      return false;
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( market_id != row.getMarketId() )
      return false;
    if ( table_name == null ) {
      if ( row.getTableName() != null )
        return false;
    } else if ( !table_name.equals( row.getTableName() ) ) {
        return false;
    }
    if ( column_name == null ) {
      if ( row.getColumnName() != null )
        return false;
    } else if ( !column_name.equals( row.getColumnName() ) ) {
        return false;
    }
    if ( data_type == null ) {
      if ( row.getDataType() != null )
        return false;
    } else if ( !data_type.equals( row.getDataType() ) ) {
        return false;
    }
    if ( large_item_div == null ) {
      if ( row.getLargeItemDiv() != null )
        return false;
    } else if ( !large_item_div.equals( row.getLargeItemDiv() ) ) {
        return false;
    }
    if ( small_item_div == null ) {
      if ( row.getSmallItemDiv() != null )
        return false;
    } else if ( !small_item_div.equals( row.getSmallItemDiv() ) ) {
        return false;
    }
    if ( data_today == null ) {
      if ( row.getDataToday() != null )
        return false;
    } else if ( !data_today.equals( row.getDataToday() ) ) {
        return false;
    }
    if ( data_next_day == null ) {
      if ( row.getDataNextDay() != null )
        return false;
    } else if ( !data_next_day.equals( row.getDataNextDay() ) ) {
        return false;
    }
    if ( data_plan == null ) {
      if ( row.getDataPlan() != null )
        return false;
    } else if ( !data_plan.equals( row.getDataPlan() ) ) {
        return false;
    }
    if ( term_from == null ) {
      if ( row.getTermFrom() != null )
        return false;
    } else if ( !term_from.equals( row.getTermFrom() ) ) {
        return false;
    }
    if ( term_to == null ) {
      if ( row.getTermTo() != null )
        return false;
    } else if ( !term_to.equals( row.getTermTo() ) ) {
        return false;
    }
    if ( delete_flg == null ) {
      if ( row.getDeleteFlg() != null )
        return false;
    } else if ( !delete_flg.equals( row.getDeleteFlg() ) ) {
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
      return  ((int) eqtype_product_condition_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + ((int) product_id)
        + (market_code!=null? market_code.hashCode(): 0) 
        + ((int) market_id)
        + (table_name!=null? table_name.hashCode(): 0) 
        + (column_name!=null? column_name.hashCode(): 0) 
        + (data_type!=null? data_type.hashCode(): 0) 
        + (large_item_div!=null? large_item_div.hashCode(): 0) 
        + (small_item_div!=null? small_item_div.hashCode(): 0) 
        + (data_today!=null? data_today.hashCode(): 0) 
        + (data_next_day!=null? data_next_day.hashCode(): 0) 
        + (data_plan!=null? data_plan.hashCode(): 0) 
        + (term_from!=null? term_from.hashCode(): 0) 
        + (term_to!=null? term_to.hashCode(): 0) 
        + (delete_flg!=null? delete_flg.hashCode(): 0) 
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
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !market_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_code' must be set before inserting.");
		if ( !market_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_id' must be set before inserting.");
		if ( !table_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'table_name' must be set before inserting.");
		if ( !column_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'column_name' must be set before inserting.");
		if ( !large_item_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'large_item_div' must be set before inserting.");
		if ( !small_item_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'small_item_div' must be set before inserting.");
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
		map.put("eqtype_product_condition_id",new Long(eqtype_product_condition_id));
		map.put("institution_code",institution_code);
		map.put("product_code",product_code);
		map.put("product_id",new Long(product_id));
		map.put("market_code",market_code);
		map.put("market_id",new Long(market_id));
		map.put("table_name",table_name);
		map.put("column_name",column_name);
		if ( data_type != null )
			map.put("data_type",data_type);
		map.put("large_item_div",large_item_div);
		map.put("small_item_div",small_item_div);
		if ( data_today != null )
			map.put("data_today",data_today);
		if ( data_next_day != null )
			map.put("data_next_day",data_next_day);
		if ( data_plan != null )
			map.put("data_plan",data_plan);
		if ( term_from != null )
			map.put("term_from",term_from);
		if ( term_to != null )
			map.put("term_to",term_to);
		if ( delete_flg != null )
			map.put("delete_flg",delete_flg);
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
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( market_code_is_modified )
			map.put("market_code",market_code);
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if ( table_name_is_modified )
			map.put("table_name",table_name);
		if ( column_name_is_modified )
			map.put("column_name",column_name);
		if ( data_type_is_modified )
			map.put("data_type",data_type);
		if ( large_item_div_is_modified )
			map.put("large_item_div",large_item_div);
		if ( small_item_div_is_modified )
			map.put("small_item_div",small_item_div);
		if ( data_today_is_modified )
			map.put("data_today",data_today);
		if ( data_next_day_is_modified )
			map.put("data_next_day",data_next_day);
		if ( data_plan_is_modified )
			map.put("data_plan",data_plan);
		if ( term_from_is_modified )
			map.put("term_from",term_from);
		if ( term_to_is_modified )
			map.put("term_to",term_to);
		if ( delete_flg_is_modified )
			map.put("delete_flg",delete_flg);
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
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( market_code_is_set )
				map.put("market_code",market_code);
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
			if ( table_name_is_set )
				map.put("table_name",table_name);
			if ( column_name_is_set )
				map.put("column_name",column_name);
			map.put("data_type",data_type);
			if ( large_item_div_is_set )
				map.put("large_item_div",large_item_div);
			if ( small_item_div_is_set )
				map.put("small_item_div",small_item_div);
			map.put("data_today",data_today);
			map.put("data_next_day",data_next_day);
			map.put("data_plan",data_plan);
			map.put("term_from",term_from);
			map.put("term_to",term_to);
			map.put("delete_flg",delete_flg);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>eqtype_product_condition_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getEqtypeProductConditionId()
  {
    return eqtype_product_condition_id;
  }


  /** 
   * <em>eqtype_product_condition_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEqtypeProductConditionIdIsSet() {
    return eqtype_product_condition_id_is_set;
  }


  /** 
   * <em>eqtype_product_condition_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEqtypeProductConditionIdIsModified() {
    return eqtype_product_condition_id_is_modified;
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
   * <em>market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarketId()
  {
    return market_id;
  }


  /** 
   * <em>market_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsSet() {
    return market_id_is_set;
  }


  /** 
   * <em>market_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsModified() {
    return market_id_is_modified;
  }


  /** 
   * <em>table_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTableName()
  {
    return table_name;
  }


  /** 
   * <em>table_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTableNameIsSet() {
    return table_name_is_set;
  }


  /** 
   * <em>table_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTableNameIsModified() {
    return table_name_is_modified;
  }


  /** 
   * <em>column_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getColumnName()
  {
    return column_name;
  }


  /** 
   * <em>column_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColumnNameIsSet() {
    return column_name_is_set;
  }


  /** 
   * <em>column_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColumnNameIsModified() {
    return column_name_is_modified;
  }


  /** 
   * <em>data_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDataType()
  {
    return data_type;
  }


  /** 
   * <em>data_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataTypeIsSet() {
    return data_type_is_set;
  }


  /** 
   * <em>data_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataTypeIsModified() {
    return data_type_is_modified;
  }


  /** 
   * <em>large_item_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLargeItemDiv()
  {
    return large_item_div;
  }


  /** 
   * <em>large_item_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLargeItemDivIsSet() {
    return large_item_div_is_set;
  }


  /** 
   * <em>large_item_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLargeItemDivIsModified() {
    return large_item_div_is_modified;
  }


  /** 
   * <em>small_item_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSmallItemDiv()
  {
    return small_item_div;
  }


  /** 
   * <em>small_item_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSmallItemDivIsSet() {
    return small_item_div_is_set;
  }


  /** 
   * <em>small_item_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSmallItemDivIsModified() {
    return small_item_div_is_modified;
  }


  /** 
   * <em>data_today</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDataToday()
  {
    return data_today;
  }


  /** 
   * <em>data_today</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataTodayIsSet() {
    return data_today_is_set;
  }


  /** 
   * <em>data_today</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataTodayIsModified() {
    return data_today_is_modified;
  }


  /** 
   * <em>data_next_day</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDataNextDay()
  {
    return data_next_day;
  }


  /** 
   * <em>data_next_day</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataNextDayIsSet() {
    return data_next_day_is_set;
  }


  /** 
   * <em>data_next_day</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataNextDayIsModified() {
    return data_next_day_is_modified;
  }


  /** 
   * <em>data_plan</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDataPlan()
  {
    return data_plan;
  }


  /** 
   * <em>data_plan</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataPlanIsSet() {
    return data_plan_is_set;
  }


  /** 
   * <em>data_plan</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataPlanIsModified() {
    return data_plan_is_modified;
  }


  /** 
   * <em>term_from</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTermFrom()
  {
    return term_from;
  }


  /** 
   * <em>term_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTermFromIsSet() {
    return term_from_is_set;
  }


  /** 
   * <em>term_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTermFromIsModified() {
    return term_from_is_modified;
  }


  /** 
   * <em>term_to</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTermTo()
  {
    return term_to;
  }


  /** 
   * <em>term_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTermToIsSet() {
    return term_to_is_set;
  }


  /** 
   * <em>term_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTermToIsModified() {
    return term_to_is_modified;
  }


  /** 
   * <em>delete_flg</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeleteFlg()
  {
    return delete_flg;
  }


  /** 
   * <em>delete_flg</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlgIsSet() {
    return delete_flg_is_set;
  }


  /** 
   * <em>delete_flg</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlgIsModified() {
    return delete_flg_is_modified;
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
    return new EqtypeProductConditionPK(eqtype_product_condition_id);
  }


  /** 
   * <em>eqtype_product_condition_id</em>カラムの値を設定します。 
   *
   * @@param p_eqtypeProductConditionId <em>eqtype_product_condition_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setEqtypeProductConditionId( long p_eqtypeProductConditionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    eqtype_product_condition_id = p_eqtypeProductConditionId;
    eqtype_product_condition_id_is_set = true;
    eqtype_product_condition_id_is_modified = true;
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
   * <em>market_id</em>カラムの値を設定します。 
   *
   * @@param p_marketId <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * <em>table_name</em>カラムの値を設定します。 
   *
   * @@param p_tableName <em>table_name</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setTableName( String p_tableName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    table_name = p_tableName;
    table_name_is_set = true;
    table_name_is_modified = true;
  }


  /** 
   * <em>column_name</em>カラムの値を設定します。 
   *
   * @@param p_columnName <em>column_name</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setColumnName( String p_columnName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    column_name = p_columnName;
    column_name_is_set = true;
    column_name_is_modified = true;
  }


  /** 
   * <em>data_type</em>カラムの値を設定します。 
   *
   * @@param p_dataType <em>data_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDataType( String p_dataType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_type = p_dataType;
    data_type_is_set = true;
    data_type_is_modified = true;
  }


  /** 
   * <em>large_item_div</em>カラムの値を設定します。 
   *
   * @@param p_largeItemDiv <em>large_item_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setLargeItemDiv( String p_largeItemDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    large_item_div = p_largeItemDiv;
    large_item_div_is_set = true;
    large_item_div_is_modified = true;
  }


  /** 
   * <em>small_item_div</em>カラムの値を設定します。 
   *
   * @@param p_smallItemDiv <em>small_item_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setSmallItemDiv( String p_smallItemDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    small_item_div = p_smallItemDiv;
    small_item_div_is_set = true;
    small_item_div_is_modified = true;
  }


  /** 
   * <em>data_today</em>カラムの値を設定します。 
   *
   * @@param p_dataToday <em>data_today</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setDataToday( String p_dataToday )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_today = p_dataToday;
    data_today_is_set = true;
    data_today_is_modified = true;
  }


  /** 
   * <em>data_next_day</em>カラムの値を設定します。 
   *
   * @@param p_dataNextDay <em>data_next_day</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setDataNextDay( String p_dataNextDay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_next_day = p_dataNextDay;
    data_next_day_is_set = true;
    data_next_day_is_modified = true;
  }


  /** 
   * <em>data_plan</em>カラムの値を設定します。 
   *
   * @@param p_dataPlan <em>data_plan</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setDataPlan( String p_dataPlan )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_plan = p_dataPlan;
    data_plan_is_set = true;
    data_plan_is_modified = true;
  }


  /** 
   * <em>term_from</em>カラムの値を設定します。 
   *
   * @@param p_termFrom <em>term_from</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTermFrom( java.sql.Timestamp p_termFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    term_from = p_termFrom;
    term_from_is_set = true;
    term_from_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>term_from</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTermFrom( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    term_from = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    term_from_is_set = true;
    term_from_is_modified = true;
  }


  /** 
   * <em>term_to</em>カラムの値を設定します。 
   *
   * @@param p_termTo <em>term_to</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTermTo( java.sql.Timestamp p_termTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    term_to = p_termTo;
    term_to_is_set = true;
    term_to_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>term_to</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTermTo( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    term_to = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    term_to_is_set = true;
    term_to_is_modified = true;
  }


  /** 
   * <em>delete_flg</em>カラムの値を設定します。 
   *
   * @@param p_deleteFlg <em>delete_flg</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDeleteFlg( String p_deleteFlg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flg = p_deleteFlg;
    delete_flg_is_set = true;
    delete_flg_is_modified = true;
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
            case 'c':
                if ( name.equals("column_name") ) {
                    return this.column_name;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("data_type") ) {
                    return this.data_type;
                }
                else if ( name.equals("data_today") ) {
                    return this.data_today;
                }
                else if ( name.equals("data_next_day") ) {
                    return this.data_next_day;
                }
                else if ( name.equals("data_plan") ) {
                    return this.data_plan;
                }
                else if ( name.equals("delete_flg") ) {
                    return this.delete_flg;
                }
                break;
            case 'e':
                if ( name.equals("eqtype_product_condition_id") ) {
                    return new Long( eqtype_product_condition_id );
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("large_item_div") ) {
                    return this.large_item_div;
                }
                else if ( name.equals("last_updater") ) {
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
                else if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                break;
            case 's':
                if ( name.equals("small_item_div") ) {
                    return this.small_item_div;
                }
                break;
            case 't':
                if ( name.equals("table_name") ) {
                    return this.table_name;
                }
                else if ( name.equals("term_from") ) {
                    return this.term_from;
                }
                else if ( name.equals("term_to") ) {
                    return this.term_to;
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
                if ( name.equals("column_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'column_name' must be String: '"+value+"' is not." );
                    this.column_name = (String) value;
                    if (this.column_name_is_set)
                        this.column_name_is_modified = true;
                    this.column_name_is_set = true;
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
                if ( name.equals("data_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'data_type' must be String: '"+value+"' is not." );
                    this.data_type = (String) value;
                    if (this.data_type_is_set)
                        this.data_type_is_modified = true;
                    this.data_type_is_set = true;
                    return;
                }
                else if ( name.equals("data_today") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'data_today' must be String: '"+value+"' is not." );
                    this.data_today = (String) value;
                    if (this.data_today_is_set)
                        this.data_today_is_modified = true;
                    this.data_today_is_set = true;
                    return;
                }
                else if ( name.equals("data_next_day") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'data_next_day' must be String: '"+value+"' is not." );
                    this.data_next_day = (String) value;
                    if (this.data_next_day_is_set)
                        this.data_next_day_is_modified = true;
                    this.data_next_day_is_set = true;
                    return;
                }
                else if ( name.equals("data_plan") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'data_plan' must be String: '"+value+"' is not." );
                    this.data_plan = (String) value;
                    if (this.data_plan_is_set)
                        this.data_plan_is_modified = true;
                    this.data_plan_is_set = true;
                    return;
                }
                else if ( name.equals("delete_flg") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delete_flg' must be String: '"+value+"' is not." );
                    this.delete_flg = (String) value;
                    if (this.delete_flg_is_set)
                        this.delete_flg_is_modified = true;
                    this.delete_flg_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("eqtype_product_condition_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'eqtype_product_condition_id' must be Long: '"+value+"' is not." );
                    this.eqtype_product_condition_id = ((Long) value).longValue();
                    if (this.eqtype_product_condition_id_is_set)
                        this.eqtype_product_condition_id_is_modified = true;
                    this.eqtype_product_condition_id_is_set = true;
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
                if ( name.equals("large_item_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'large_item_div' must be String: '"+value+"' is not." );
                    this.large_item_div = (String) value;
                    if (this.large_item_div_is_set)
                        this.large_item_div_is_modified = true;
                    this.large_item_div_is_set = true;
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
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                else if ( name.equals("market_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = ((Long) value).longValue();
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
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
                else if ( name.equals("product_id") ) {
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
                if ( name.equals("small_item_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'small_item_div' must be String: '"+value+"' is not." );
                    this.small_item_div = (String) value;
                    if (this.small_item_div_is_set)
                        this.small_item_div_is_modified = true;
                    this.small_item_div_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("table_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'table_name' must be String: '"+value+"' is not." );
                    this.table_name = (String) value;
                    if (this.table_name_is_set)
                        this.table_name_is_modified = true;
                    this.table_name_is_set = true;
                    return;
                }
                else if ( name.equals("term_from") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'term_from' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.term_from = (java.sql.Timestamp) value;
                    if (this.term_from_is_set)
                        this.term_from_is_modified = true;
                    this.term_from_is_set = true;
                    return;
                }
                else if ( name.equals("term_to") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'term_to' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.term_to = (java.sql.Timestamp) value;
                    if (this.term_to_is_set)
                        this.term_to_is_modified = true;
                    this.term_to_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
