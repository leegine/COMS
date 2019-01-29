head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.37.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderSwitchingParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * order_switchingテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link OrderSwitchingRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link OrderSwitchingParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link OrderSwitchingParams}が{@@link OrderSwitchingRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrderSwitchingPK 
 * @@see OrderSwitchingRow 
 */
public class OrderSwitchingParams extends Params implements OrderSwitchingRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "order_switching";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = OrderSwitchingRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return OrderSwitchingRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>market_code</em>カラムの値 
   */
  public  String  market_code;

  /** 
   * <em>submit_order_route_div</em>カラムの値 
   */
  public  String  submit_order_route_div;

  /** 
   * <em>front_order_system_code</em>カラムの値 
   */
  public  String  front_order_system_code;

  /** 
   * <em>sonar_market_code</em>カラムの値 
   */
  public  String  sonar_market_code;

  /** 
   * <em>valid_flag</em>カラムの値 
   */
  public  String  valid_flag;

  /** 
   * <em>change_cancel_enable_flag</em>カラムの値 
   */
  public  String  change_cancel_enable_flag;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>submit_mq_trigger</em>カラムの値 
   */
  public  String  submit_mq_trigger;

  /** 
   * <em>order_engine_div</em>カラムの値 
   */
  public  String  order_engine_div;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean sonar_market_code_is_set = false;
  private boolean sonar_market_code_is_modified = false;
  private boolean submit_order_route_div_is_set = false;
  private boolean submit_order_route_div_is_modified = false;
  private boolean valid_flag_is_set = false;
  private boolean valid_flag_is_modified = false;
  private boolean change_cancel_enable_flag_is_set = false;
  private boolean change_cancel_enable_flag_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean front_order_system_code_is_set = false;
  private boolean front_order_system_code_is_modified = false;
  private boolean submit_mq_trigger_is_set = false;
  private boolean submit_mq_trigger_is_modified = false;
  private boolean order_engine_div_is_set = false;
  private boolean order_engine_div_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "product_type=" + product_type
      + "," + "market_code=" + market_code
      + "," + "submit_order_route_div=" + submit_order_route_div
      + "," + "front_order_system_code=" + front_order_system_code
      + "," + "sonar_market_code=" +sonar_market_code
      + "," + "valid_flag=" +valid_flag
      + "," + "change_cancel_enable_flag=" +change_cancel_enable_flag
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "submit_mq_trigger=" +submit_mq_trigger
      + "," + "order_engine_div=" +order_engine_div
      + "}";
  }


  /** 
   * 値が未設定のOrderSwitchingParamsオブジェクトを作成します。 
   */
  public OrderSwitchingParams() {
    institution_code_is_modified = true;
    product_type_is_modified = true;
    market_code_is_modified = true;
    submit_order_route_div_is_modified = true;
    front_order_system_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のOrderSwitchingRowオブジェクトの値を利用してOrderSwitchingParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するOrderSwitchingRowオブジェクト 
   */
  public OrderSwitchingParams( OrderSwitchingRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    submit_order_route_div = p_row.getSubmitOrderRouteDiv();
    submit_order_route_div_is_set = p_row.getSubmitOrderRouteDivIsSet();
    submit_order_route_div_is_modified = p_row.getSubmitOrderRouteDivIsModified();
    front_order_system_code = p_row.getFrontOrderSystemCode();
    front_order_system_code_is_set = p_row.getFrontOrderSystemCodeIsSet();
    front_order_system_code_is_modified = p_row.getFrontOrderSystemCodeIsModified();
    sonar_market_code = p_row.getSonarMarketCode();
    sonar_market_code_is_set = p_row.getSonarMarketCodeIsSet();
    sonar_market_code_is_modified = p_row.getSonarMarketCodeIsModified();
    valid_flag = p_row.getValidFlag();
    valid_flag_is_set = p_row.getValidFlagIsSet();
    valid_flag_is_modified = p_row.getValidFlagIsModified();
    change_cancel_enable_flag = p_row.getChangeCancelEnableFlag();
    change_cancel_enable_flag_is_set = p_row.getChangeCancelEnableFlagIsSet();
    change_cancel_enable_flag_is_modified = p_row.getChangeCancelEnableFlagIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    submit_mq_trigger = p_row.getSubmitMqTrigger();
    submit_mq_trigger_is_set = p_row.getSubmitMqTriggerIsSet();
    submit_mq_trigger_is_modified = p_row.getSubmitMqTriggerIsModified();
    order_engine_div = p_row.getOrderEngineDiv();
    order_engine_div_is_set = p_row.getOrderEngineDivIsSet();
    order_engine_div_is_modified = p_row.getOrderEngineDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      sonar_market_code_is_set = true;
      sonar_market_code_is_modified = true;
      valid_flag_is_set = true;
      valid_flag_is_modified = true;
      change_cancel_enable_flag_is_set = true;
      change_cancel_enable_flag_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      submit_mq_trigger_is_set = true;
      submit_mq_trigger_is_modified = true;
      order_engine_div_is_set = true;
      order_engine_div_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof OrderSwitchingRow ) )
       return false;
    return fieldsEqual( (OrderSwitchingRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のOrderSwitchingRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( OrderSwitchingRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( sonar_market_code == null ) {
      if ( row.getSonarMarketCode() != null )
        return false;
    } else if ( !sonar_market_code.equals( row.getSonarMarketCode() ) ) {
        return false;
    }
    if ( submit_order_route_div == null ) {
      if ( row.getSubmitOrderRouteDiv() != null )
        return false;
    } else if ( !submit_order_route_div.equals( row.getSubmitOrderRouteDiv() ) ) {
        return false;
    }
    if ( valid_flag == null ) {
      if ( row.getValidFlag() != null )
        return false;
    } else if ( !valid_flag.equals( row.getValidFlag() ) ) {
        return false;
    }
    if ( change_cancel_enable_flag == null ) {
      if ( row.getChangeCancelEnableFlag() != null )
        return false;
    } else if ( !change_cancel_enable_flag.equals( row.getChangeCancelEnableFlag() ) ) {
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
    if ( front_order_system_code == null ) {
      if ( row.getFrontOrderSystemCode() != null )
        return false;
    } else if ( !front_order_system_code.equals( row.getFrontOrderSystemCode() ) ) {
        return false;
    }
    if ( submit_mq_trigger == null ) {
      if ( row.getSubmitMqTrigger() != null )
        return false;
    } else if ( !submit_mq_trigger.equals( row.getSubmitMqTrigger() ) ) {
        return false;
    }
    if ( order_engine_div == null ) {
      if ( row.getOrderEngineDiv() != null )
        return false;
    } else if ( !order_engine_div.equals( row.getOrderEngineDiv() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (sonar_market_code!=null? sonar_market_code.hashCode(): 0) 
        + (submit_order_route_div!=null? submit_order_route_div.hashCode(): 0) 
        + (valid_flag!=null? valid_flag.hashCode(): 0) 
        + (change_cancel_enable_flag!=null? change_cancel_enable_flag.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (front_order_system_code!=null? front_order_system_code.hashCode(): 0) 
        + (submit_mq_trigger!=null? submit_mq_trigger.hashCode(): 0) 
        + (order_engine_div!=null? order_engine_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !sonar_market_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'sonar_market_code' must be set before inserting.");
		if ( !submit_mq_trigger_is_set )
			throw new IllegalArgumentException("non-nullable field 'submit_mq_trigger' must be set before inserting.");
		if ( !order_engine_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_engine_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("product_type",product_type);
		map.put("market_code",market_code);
		map.put("sonar_market_code",sonar_market_code);
		map.put("submit_order_route_div",submit_order_route_div);
		if ( valid_flag_is_set )
			map.put("valid_flag",valid_flag);
		if ( change_cancel_enable_flag_is_set )
			map.put("change_cancel_enable_flag",change_cancel_enable_flag);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("front_order_system_code",front_order_system_code);
		map.put("submit_mq_trigger",submit_mq_trigger);
		map.put("order_engine_div",order_engine_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( sonar_market_code_is_modified )
			map.put("sonar_market_code",sonar_market_code);
		if ( valid_flag_is_modified )
			map.put("valid_flag",valid_flag);
		if ( change_cancel_enable_flag_is_modified )
			map.put("change_cancel_enable_flag",change_cancel_enable_flag);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( submit_mq_trigger_is_modified )
			map.put("submit_mq_trigger",submit_mq_trigger);
		if ( order_engine_div_is_modified )
			map.put("order_engine_div",order_engine_div);
		if (map.size() == 0) {
			if ( sonar_market_code_is_set )
				map.put("sonar_market_code",sonar_market_code);
			if ( valid_flag_is_set )
				map.put("valid_flag",valid_flag);
			if ( change_cancel_enable_flag_is_set )
				map.put("change_cancel_enable_flag",change_cancel_enable_flag);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( submit_mq_trigger_is_set )
				map.put("submit_mq_trigger",submit_mq_trigger);
			if ( order_engine_div_is_set )
				map.put("order_engine_div",order_engine_div);
		}
		return map;
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
   * <em>sonar_market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarMarketCode()
  {
    return sonar_market_code;
  }


  /** 
   * <em>sonar_market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarMarketCodeIsSet() {
    return sonar_market_code_is_set;
  }


  /** 
   * <em>sonar_market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarMarketCodeIsModified() {
    return sonar_market_code_is_modified;
  }


  /** 
   * <em>submit_order_route_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSubmitOrderRouteDiv()
  {
    return submit_order_route_div;
  }


  /** 
   * <em>submit_order_route_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubmitOrderRouteDivIsSet() {
    return submit_order_route_div_is_set;
  }


  /** 
   * <em>submit_order_route_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubmitOrderRouteDivIsModified() {
    return submit_order_route_div_is_modified;
  }


  /** 
   * <em>valid_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getValidFlag()
  {
    return valid_flag;
  }


  /** 
   * <em>valid_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidFlagIsSet() {
    return valid_flag_is_set;
  }


  /** 
   * <em>valid_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidFlagIsModified() {
    return valid_flag_is_modified;
  }


  /** 
   * <em>change_cancel_enable_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getChangeCancelEnableFlag()
  {
    return change_cancel_enable_flag;
  }


  /** 
   * <em>change_cancel_enable_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeCancelEnableFlagIsSet() {
    return change_cancel_enable_flag_is_set;
  }


  /** 
   * <em>change_cancel_enable_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeCancelEnableFlagIsModified() {
    return change_cancel_enable_flag_is_modified;
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
   * <em>front_order_system_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFrontOrderSystemCode()
  {
    return front_order_system_code;
  }


  /** 
   * <em>front_order_system_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderSystemCodeIsSet() {
    return front_order_system_code_is_set;
  }


  /** 
   * <em>front_order_system_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderSystemCodeIsModified() {
    return front_order_system_code_is_modified;
  }


  /** 
   * <em>submit_mq_trigger</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSubmitMqTrigger()
  {
    return submit_mq_trigger;
  }


  /** 
   * <em>submit_mq_trigger</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubmitMqTriggerIsSet() {
    return submit_mq_trigger_is_set;
  }


  /** 
   * <em>submit_mq_trigger</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubmitMqTriggerIsModified() {
    return submit_mq_trigger_is_modified;
  }


  /** 
   * <em>order_engine_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderEngineDiv()
  {
    return order_engine_div;
  }


  /** 
   * <em>order_engine_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderEngineDivIsSet() {
    return order_engine_div_is_set;
  }


  /** 
   * <em>order_engine_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderEngineDivIsModified() {
    return order_engine_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new OrderSwitchingPK(institution_code, product_type, market_code, submit_order_route_div, front_order_system_code);
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
   * <em>sonar_market_code</em>カラムの値を設定します。 
   *
   * @@param p_sonarMarketCode <em>sonar_market_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSonarMarketCode( String p_sonarMarketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_market_code = p_sonarMarketCode;
    sonar_market_code_is_set = true;
    sonar_market_code_is_modified = true;
  }


  /** 
   * <em>submit_order_route_div</em>カラムの値を設定します。 
   *
   * @@param p_submitOrderRouteDiv <em>submit_order_route_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSubmitOrderRouteDiv( String p_submitOrderRouteDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    submit_order_route_div = p_submitOrderRouteDiv;
    submit_order_route_div_is_set = true;
    submit_order_route_div_is_modified = true;
  }


  /** 
   * <em>valid_flag</em>カラムの値を設定します。 
   *
   * @@param p_validFlag <em>valid_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setValidFlag( String p_validFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    valid_flag = p_validFlag;
    valid_flag_is_set = true;
    valid_flag_is_modified = true;
  }


  /** 
   * <em>change_cancel_enable_flag</em>カラムの値を設定します。 
   *
   * @@param p_changeCancelEnableFlag <em>change_cancel_enable_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setChangeCancelEnableFlag( String p_changeCancelEnableFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_cancel_enable_flag = p_changeCancelEnableFlag;
    change_cancel_enable_flag_is_set = true;
    change_cancel_enable_flag_is_modified = true;
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
   * <em>front_order_system_code</em>カラムの値を設定します。 
   *
   * @@param p_frontOrderSystemCode <em>front_order_system_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFrontOrderSystemCode( String p_frontOrderSystemCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    front_order_system_code = p_frontOrderSystemCode;
    front_order_system_code_is_set = true;
    front_order_system_code_is_modified = true;
  }


  /** 
   * <em>submit_mq_trigger</em>カラムの値を設定します。 
   *
   * @@param p_submitMqTrigger <em>submit_mq_trigger</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSubmitMqTrigger( String p_submitMqTrigger )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    submit_mq_trigger = p_submitMqTrigger;
    submit_mq_trigger_is_set = true;
    submit_mq_trigger_is_modified = true;
  }


  /** 
   * <em>order_engine_div</em>カラムの値を設定します。 
   *
   * @@param p_orderEngineDiv <em>order_engine_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderEngineDiv( String p_orderEngineDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_engine_div = p_orderEngineDiv;
    order_engine_div_is_set = true;
    order_engine_div_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("change_cancel_enable_flag") ) {
                    return this.change_cancel_enable_flag;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("front_order_system_code") ) {
                    return this.front_order_system_code;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'o':
                if ( name.equals("order_engine_div") ) {
                    return this.order_engine_div;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                break;
            case 's':
                if ( name.equals("sonar_market_code") ) {
                    return this.sonar_market_code;
                }
                else if ( name.equals("submit_order_route_div") ) {
                    return this.submit_order_route_div;
                }
                else if ( name.equals("submit_mq_trigger") ) {
                    return this.submit_mq_trigger;
                }
                break;
            case 'v':
                if ( name.equals("valid_flag") ) {
                    return this.valid_flag;
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
                if ( name.equals("change_cancel_enable_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'change_cancel_enable_flag' must be String: '"+value+"' is not." );
                    this.change_cancel_enable_flag = (String) value;
                    if (this.change_cancel_enable_flag_is_set)
                        this.change_cancel_enable_flag_is_modified = true;
                    this.change_cancel_enable_flag_is_set = true;
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
            case 'f':
                if ( name.equals("front_order_system_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'front_order_system_code' must be String: '"+value+"' is not." );
                    this.front_order_system_code = (String) value;
                    if (this.front_order_system_code_is_set)
                        this.front_order_system_code_is_modified = true;
                    this.front_order_system_code_is_set = true;
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
                if ( name.equals("order_engine_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_engine_div' must be String: '"+value+"' is not." );
                    this.order_engine_div = (String) value;
                    if (this.order_engine_div_is_set)
                        this.order_engine_div_is_modified = true;
                    this.order_engine_div_is_set = true;
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
                break;
            case 's':
                if ( name.equals("sonar_market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_market_code' must be String: '"+value+"' is not." );
                    this.sonar_market_code = (String) value;
                    if (this.sonar_market_code_is_set)
                        this.sonar_market_code_is_modified = true;
                    this.sonar_market_code_is_set = true;
                    return;
                }
                else if ( name.equals("submit_order_route_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'submit_order_route_div' must be String: '"+value+"' is not." );
                    this.submit_order_route_div = (String) value;
                    if (this.submit_order_route_div_is_set)
                        this.submit_order_route_div_is_modified = true;
                    this.submit_order_route_div_is_set = true;
                    return;
                }
                else if ( name.equals("submit_mq_trigger") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'submit_mq_trigger' must be String: '"+value+"' is not." );
                    this.submit_mq_trigger = (String) value;
                    if (this.submit_mq_trigger_is_set)
                        this.submit_mq_trigger_is_modified = true;
                    this.submit_mq_trigger_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("valid_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'valid_flag' must be String: '"+value+"' is not." );
                    this.valid_flag = (String) value;
                    if (this.valid_flag_is_set)
                        this.valid_flag_is_modified = true;
                    this.valid_flag_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
