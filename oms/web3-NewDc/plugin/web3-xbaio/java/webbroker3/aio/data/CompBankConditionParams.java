head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.37.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	CompBankConditionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * comp_bank_conditionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link CompBankConditionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link CompBankConditionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link CompBankConditionParams}が{@@link CompBankConditionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CompBankConditionPK 
 * @@see CompBankConditionRow 
 */
public class CompBankConditionParams extends Params implements CompBankConditionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "comp_bank_condition";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = CompBankConditionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return CompBankConditionRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>pay_scheme_id</em>カラムの値 
   */
  public  String  pay_scheme_id;

  /** 
   * <em>max_amount_daily</em>カラムの値 
   */
  public  Long  max_amount_daily;

  /** 
   * <em>max_amount_once</em>カラムの値 
   */
  public  Long  max_amount_once;

  /** 
   * <em>min_amount_once</em>カラムの値 
   */
  public  Long  min_amount_once;

  /** 
   * <em>amount_unit</em>カラムの値 
   */
  public  Long  amount_unit;

  /** 
   * <em>max_count</em>カラムの値 
   */
  public  Integer  max_count;

  /** 
   * <em>shop_id</em>カラムの値 
   */
  public  String  shop_id;

  /** 
   * <em>access_key</em>カラムの値 
   */
  public  String  access_key;

  /** 
   * <em>sonar_code</em>カラムの値 
   */
  public  String  sonar_code;

  /** 
   * <em>payment_method</em>カラムの値 
   */
  public  String  payment_method;

  /** 
   * <em>complete_flag</em>カラムの値 
   */
  public  String  complete_flag;

  /** 
   * <em>limit_time</em>カラムの値 
   */
  public  String  limit_time;

  /** 
   * <em>suspension_start_time</em>カラムの値 
   */
  public  String  suspension_start_time;

  /** 
   * <em>suspension_end_time</em>カラムの値 
   */
  public  String  suspension_end_time;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean pay_scheme_id_is_set = false;
  private boolean pay_scheme_id_is_modified = false;
  private boolean max_amount_daily_is_set = false;
  private boolean max_amount_daily_is_modified = false;
  private boolean max_amount_once_is_set = false;
  private boolean max_amount_once_is_modified = false;
  private boolean min_amount_once_is_set = false;
  private boolean min_amount_once_is_modified = false;
  private boolean amount_unit_is_set = false;
  private boolean amount_unit_is_modified = false;
  private boolean max_count_is_set = false;
  private boolean max_count_is_modified = false;
  private boolean shop_id_is_set = false;
  private boolean shop_id_is_modified = false;
  private boolean access_key_is_set = false;
  private boolean access_key_is_modified = false;
  private boolean sonar_code_is_set = false;
  private boolean sonar_code_is_modified = false;
  private boolean payment_method_is_set = false;
  private boolean payment_method_is_modified = false;
  private boolean complete_flag_is_set = false;
  private boolean complete_flag_is_modified = false;
  private boolean limit_time_is_set = false;
  private boolean limit_time_is_modified = false;
  private boolean suspension_start_time_is_set = false;
  private boolean suspension_start_time_is_modified = false;
  private boolean suspension_end_time_is_set = false;
  private boolean suspension_end_time_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "pay_scheme_id=" + pay_scheme_id
      + "," + "max_amount_daily=" +max_amount_daily
      + "," + "max_amount_once=" +max_amount_once
      + "," + "min_amount_once=" +min_amount_once
      + "," + "amount_unit=" +amount_unit
      + "," + "max_count=" +max_count
      + "," + "shop_id=" +shop_id
      + "," + "access_key=" +access_key
      + "," + "sonar_code=" +sonar_code
      + "," + "payment_method=" +payment_method
      + "," + "complete_flag=" +complete_flag
      + "," + "limit_time=" +limit_time
      + "," + "suspension_start_time=" +suspension_start_time
      + "," + "suspension_end_time=" +suspension_end_time
      + "}";
  }


  /** 
   * 値が未設定のCompBankConditionParamsオブジェクトを作成します。 
   */
  public CompBankConditionParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    pay_scheme_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のCompBankConditionRowオブジェクトの値を利用してCompBankConditionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するCompBankConditionRowオブジェクト 
   */
  public CompBankConditionParams( CompBankConditionRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    pay_scheme_id = p_row.getPaySchemeId();
    pay_scheme_id_is_set = p_row.getPaySchemeIdIsSet();
    pay_scheme_id_is_modified = p_row.getPaySchemeIdIsModified();
    if ( !p_row.getMaxAmountDailyIsNull() )
      max_amount_daily = new Long( p_row.getMaxAmountDaily() );
    max_amount_daily_is_set = p_row.getMaxAmountDailyIsSet();
    max_amount_daily_is_modified = p_row.getMaxAmountDailyIsModified();
    if ( !p_row.getMaxAmountOnceIsNull() )
      max_amount_once = new Long( p_row.getMaxAmountOnce() );
    max_amount_once_is_set = p_row.getMaxAmountOnceIsSet();
    max_amount_once_is_modified = p_row.getMaxAmountOnceIsModified();
    if ( !p_row.getMinAmountOnceIsNull() )
      min_amount_once = new Long( p_row.getMinAmountOnce() );
    min_amount_once_is_set = p_row.getMinAmountOnceIsSet();
    min_amount_once_is_modified = p_row.getMinAmountOnceIsModified();
    if ( !p_row.getAmountUnitIsNull() )
      amount_unit = new Long( p_row.getAmountUnit() );
    amount_unit_is_set = p_row.getAmountUnitIsSet();
    amount_unit_is_modified = p_row.getAmountUnitIsModified();
    if ( !p_row.getMaxCountIsNull() )
      max_count = new Integer( p_row.getMaxCount() );
    max_count_is_set = p_row.getMaxCountIsSet();
    max_count_is_modified = p_row.getMaxCountIsModified();
    shop_id = p_row.getShopId();
    shop_id_is_set = p_row.getShopIdIsSet();
    shop_id_is_modified = p_row.getShopIdIsModified();
    access_key = p_row.getAccessKey();
    access_key_is_set = p_row.getAccessKeyIsSet();
    access_key_is_modified = p_row.getAccessKeyIsModified();
    sonar_code = p_row.getSonarCode();
    sonar_code_is_set = p_row.getSonarCodeIsSet();
    sonar_code_is_modified = p_row.getSonarCodeIsModified();
    payment_method = p_row.getPaymentMethod();
    payment_method_is_set = p_row.getPaymentMethodIsSet();
    payment_method_is_modified = p_row.getPaymentMethodIsModified();
    complete_flag = p_row.getCompleteFlag();
    complete_flag_is_set = p_row.getCompleteFlagIsSet();
    complete_flag_is_modified = p_row.getCompleteFlagIsModified();
    limit_time = p_row.getLimitTime();
    limit_time_is_set = p_row.getLimitTimeIsSet();
    limit_time_is_modified = p_row.getLimitTimeIsModified();
    suspension_start_time = p_row.getSuspensionStartTime();
    suspension_start_time_is_set = p_row.getSuspensionStartTimeIsSet();
    suspension_start_time_is_modified = p_row.getSuspensionStartTimeIsModified();
    suspension_end_time = p_row.getSuspensionEndTime();
    suspension_end_time_is_set = p_row.getSuspensionEndTimeIsSet();
    suspension_end_time_is_modified = p_row.getSuspensionEndTimeIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      max_amount_daily_is_set = true;
      max_amount_daily_is_modified = true;
      max_amount_once_is_set = true;
      max_amount_once_is_modified = true;
      min_amount_once_is_set = true;
      min_amount_once_is_modified = true;
      amount_unit_is_set = true;
      amount_unit_is_modified = true;
      max_count_is_set = true;
      max_count_is_modified = true;
      shop_id_is_set = true;
      shop_id_is_modified = true;
      access_key_is_set = true;
      access_key_is_modified = true;
      sonar_code_is_set = true;
      sonar_code_is_modified = true;
      payment_method_is_set = true;
      payment_method_is_modified = true;
      complete_flag_is_set = true;
      complete_flag_is_modified = true;
      limit_time_is_set = true;
      limit_time_is_modified = true;
      suspension_start_time_is_set = true;
      suspension_start_time_is_modified = true;
      suspension_end_time_is_set = true;
      suspension_end_time_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof CompBankConditionRow ) )
       return false;
    return fieldsEqual( (CompBankConditionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のCompBankConditionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( CompBankConditionRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( pay_scheme_id == null ) {
      if ( row.getPaySchemeId() != null )
        return false;
    } else if ( !pay_scheme_id.equals( row.getPaySchemeId() ) ) {
        return false;
    }
    if ( max_amount_daily == null ) {
      if ( !row.getMaxAmountDailyIsNull() )
        return false;
    } else if ( row.getMaxAmountDailyIsNull() || ( max_amount_daily.longValue() != row.getMaxAmountDaily() ) ) {
        return false;
    }
    if ( max_amount_once == null ) {
      if ( !row.getMaxAmountOnceIsNull() )
        return false;
    } else if ( row.getMaxAmountOnceIsNull() || ( max_amount_once.longValue() != row.getMaxAmountOnce() ) ) {
        return false;
    }
    if ( min_amount_once == null ) {
      if ( !row.getMinAmountOnceIsNull() )
        return false;
    } else if ( row.getMinAmountOnceIsNull() || ( min_amount_once.longValue() != row.getMinAmountOnce() ) ) {
        return false;
    }
    if ( amount_unit == null ) {
      if ( !row.getAmountUnitIsNull() )
        return false;
    } else if ( row.getAmountUnitIsNull() || ( amount_unit.longValue() != row.getAmountUnit() ) ) {
        return false;
    }
    if ( max_count == null ) {
      if ( !row.getMaxCountIsNull() )
        return false;
    } else if ( row.getMaxCountIsNull() || ( max_count.intValue() != row.getMaxCount() ) ) {
        return false;
    }
    if ( shop_id == null ) {
      if ( row.getShopId() != null )
        return false;
    } else if ( !shop_id.equals( row.getShopId() ) ) {
        return false;
    }
    if ( access_key == null ) {
      if ( row.getAccessKey() != null )
        return false;
    } else if ( !access_key.equals( row.getAccessKey() ) ) {
        return false;
    }
    if ( sonar_code == null ) {
      if ( row.getSonarCode() != null )
        return false;
    } else if ( !sonar_code.equals( row.getSonarCode() ) ) {
        return false;
    }
    if ( payment_method == null ) {
      if ( row.getPaymentMethod() != null )
        return false;
    } else if ( !payment_method.equals( row.getPaymentMethod() ) ) {
        return false;
    }
    if ( complete_flag == null ) {
      if ( row.getCompleteFlag() != null )
        return false;
    } else if ( !complete_flag.equals( row.getCompleteFlag() ) ) {
        return false;
    }
    if ( limit_time == null ) {
      if ( row.getLimitTime() != null )
        return false;
    } else if ( !limit_time.equals( row.getLimitTime() ) ) {
        return false;
    }
    if ( suspension_start_time == null ) {
      if ( row.getSuspensionStartTime() != null )
        return false;
    } else if ( !suspension_start_time.equals( row.getSuspensionStartTime() ) ) {
        return false;
    }
    if ( suspension_end_time == null ) {
      if ( row.getSuspensionEndTime() != null )
        return false;
    } else if ( !suspension_end_time.equals( row.getSuspensionEndTime() ) ) {
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
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (pay_scheme_id!=null? pay_scheme_id.hashCode(): 0) 
        + (max_amount_daily!=null? max_amount_daily.hashCode(): 0) 
        + (max_amount_once!=null? max_amount_once.hashCode(): 0) 
        + (min_amount_once!=null? min_amount_once.hashCode(): 0) 
        + (amount_unit!=null? amount_unit.hashCode(): 0) 
        + (max_count!=null? max_count.hashCode(): 0) 
        + (shop_id!=null? shop_id.hashCode(): 0) 
        + (access_key!=null? access_key.hashCode(): 0) 
        + (sonar_code!=null? sonar_code.hashCode(): 0) 
        + (payment_method!=null? payment_method.hashCode(): 0) 
        + (complete_flag!=null? complete_flag.hashCode(): 0) 
        + (limit_time!=null? limit_time.hashCode(): 0) 
        + (suspension_start_time!=null? suspension_start_time.hashCode(): 0) 
        + (suspension_end_time!=null? suspension_end_time.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !shop_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'shop_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("pay_scheme_id",pay_scheme_id);
		if ( max_amount_daily != null )
			map.put("max_amount_daily",max_amount_daily);
		if ( max_amount_once != null )
			map.put("max_amount_once",max_amount_once);
		if ( min_amount_once != null )
			map.put("min_amount_once",min_amount_once);
		if ( amount_unit != null )
			map.put("amount_unit",amount_unit);
		if ( max_count != null )
			map.put("max_count",max_count);
		map.put("shop_id",shop_id);
		if ( access_key != null )
			map.put("access_key",access_key);
		if ( sonar_code != null )
			map.put("sonar_code",sonar_code);
		if ( payment_method != null )
			map.put("payment_method",payment_method);
		if ( complete_flag != null )
			map.put("complete_flag",complete_flag);
		if ( limit_time_is_set )
			map.put("limit_time",limit_time);
		if ( suspension_start_time != null )
			map.put("suspension_start_time",suspension_start_time);
		if ( suspension_end_time != null )
			map.put("suspension_end_time",suspension_end_time);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( max_amount_daily_is_modified )
			map.put("max_amount_daily",max_amount_daily);
		if ( max_amount_once_is_modified )
			map.put("max_amount_once",max_amount_once);
		if ( min_amount_once_is_modified )
			map.put("min_amount_once",min_amount_once);
		if ( amount_unit_is_modified )
			map.put("amount_unit",amount_unit);
		if ( max_count_is_modified )
			map.put("max_count",max_count);
		if ( shop_id_is_modified )
			map.put("shop_id",shop_id);
		if ( access_key_is_modified )
			map.put("access_key",access_key);
		if ( sonar_code_is_modified )
			map.put("sonar_code",sonar_code);
		if ( payment_method_is_modified )
			map.put("payment_method",payment_method);
		if ( complete_flag_is_modified )
			map.put("complete_flag",complete_flag);
		if ( limit_time_is_modified )
			map.put("limit_time",limit_time);
		if ( suspension_start_time_is_modified )
			map.put("suspension_start_time",suspension_start_time);
		if ( suspension_end_time_is_modified )
			map.put("suspension_end_time",suspension_end_time);
		if (map.size() == 0) {
			map.put("max_amount_daily",max_amount_daily);
			map.put("max_amount_once",max_amount_once);
			map.put("min_amount_once",min_amount_once);
			map.put("amount_unit",amount_unit);
			map.put("max_count",max_count);
			if ( shop_id_is_set )
				map.put("shop_id",shop_id);
			map.put("access_key",access_key);
			map.put("sonar_code",sonar_code);
			map.put("payment_method",payment_method);
			map.put("complete_flag",complete_flag);
			if ( limit_time_is_set )
				map.put("limit_time",limit_time);
			map.put("suspension_start_time",suspension_start_time);
			map.put("suspension_end_time",suspension_end_time);
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
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
  }


  /** 
   * <em>pay_scheme_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaySchemeId()
  {
    return pay_scheme_id;
  }


  /** 
   * <em>pay_scheme_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaySchemeIdIsSet() {
    return pay_scheme_id_is_set;
  }


  /** 
   * <em>pay_scheme_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaySchemeIdIsModified() {
    return pay_scheme_id_is_modified;
  }


  /** 
   * <em>max_amount_daily</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMaxAmountDaily()
  {
    return ( max_amount_daily==null? ((long)0): max_amount_daily.longValue() );
  }


  /** 
   * <em>max_amount_daily</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxAmountDailyIsNull()
  {
    return max_amount_daily == null;
  }


  /** 
   * <em>max_amount_daily</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxAmountDailyIsSet() {
    return max_amount_daily_is_set;
  }


  /** 
   * <em>max_amount_daily</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxAmountDailyIsModified() {
    return max_amount_daily_is_modified;
  }


  /** 
   * <em>max_amount_once</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMaxAmountOnce()
  {
    return ( max_amount_once==null? ((long)0): max_amount_once.longValue() );
  }


  /** 
   * <em>max_amount_once</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxAmountOnceIsNull()
  {
    return max_amount_once == null;
  }


  /** 
   * <em>max_amount_once</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxAmountOnceIsSet() {
    return max_amount_once_is_set;
  }


  /** 
   * <em>max_amount_once</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxAmountOnceIsModified() {
    return max_amount_once_is_modified;
  }


  /** 
   * <em>min_amount_once</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMinAmountOnce()
  {
    return ( min_amount_once==null? ((long)0): min_amount_once.longValue() );
  }


  /** 
   * <em>min_amount_once</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMinAmountOnceIsNull()
  {
    return min_amount_once == null;
  }


  /** 
   * <em>min_amount_once</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinAmountOnceIsSet() {
    return min_amount_once_is_set;
  }


  /** 
   * <em>min_amount_once</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinAmountOnceIsModified() {
    return min_amount_once_is_modified;
  }


  /** 
   * <em>amount_unit</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountUnit()
  {
    return ( amount_unit==null? ((long)0): amount_unit.longValue() );
  }


  /** 
   * <em>amount_unit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountUnitIsNull()
  {
    return amount_unit == null;
  }


  /** 
   * <em>amount_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountUnitIsSet() {
    return amount_unit_is_set;
  }


  /** 
   * <em>amount_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountUnitIsModified() {
    return amount_unit_is_modified;
  }


  /** 
   * <em>max_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getMaxCount()
  {
    return ( max_count==null? ((int)0): max_count.intValue() );
  }


  /** 
   * <em>max_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxCountIsNull()
  {
    return max_count == null;
  }


  /** 
   * <em>max_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxCountIsSet() {
    return max_count_is_set;
  }


  /** 
   * <em>max_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxCountIsModified() {
    return max_count_is_modified;
  }


  /** 
   * <em>shop_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShopId()
  {
    return shop_id;
  }


  /** 
   * <em>shop_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShopIdIsSet() {
    return shop_id_is_set;
  }


  /** 
   * <em>shop_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShopIdIsModified() {
    return shop_id_is_modified;
  }


  /** 
   * <em>access_key</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccessKey()
  {
    return access_key;
  }


  /** 
   * <em>access_key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccessKeyIsSet() {
    return access_key_is_set;
  }


  /** 
   * <em>access_key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccessKeyIsModified() {
    return access_key_is_modified;
  }


  /** 
   * <em>sonar_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarCode()
  {
    return sonar_code;
  }


  /** 
   * <em>sonar_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCodeIsSet() {
    return sonar_code_is_set;
  }


  /** 
   * <em>sonar_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCodeIsModified() {
    return sonar_code_is_modified;
  }


  /** 
   * <em>payment_method</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentMethod()
  {
    return payment_method;
  }


  /** 
   * <em>payment_method</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentMethodIsSet() {
    return payment_method_is_set;
  }


  /** 
   * <em>payment_method</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentMethodIsModified() {
    return payment_method_is_modified;
  }


  /** 
   * <em>complete_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCompleteFlag()
  {
    return complete_flag;
  }


  /** 
   * <em>complete_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompleteFlagIsSet() {
    return complete_flag_is_set;
  }


  /** 
   * <em>complete_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompleteFlagIsModified() {
    return complete_flag_is_modified;
  }


  /** 
   * <em>limit_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLimitTime()
  {
    return limit_time;
  }


  /** 
   * <em>limit_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitTimeIsSet() {
    return limit_time_is_set;
  }


  /** 
   * <em>limit_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitTimeIsModified() {
    return limit_time_is_modified;
  }


  /** 
   * <em>suspension_start_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSuspensionStartTime()
  {
    return suspension_start_time;
  }


  /** 
   * <em>suspension_start_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSuspensionStartTimeIsSet() {
    return suspension_start_time_is_set;
  }


  /** 
   * <em>suspension_start_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSuspensionStartTimeIsModified() {
    return suspension_start_time_is_modified;
  }


  /** 
   * <em>suspension_end_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSuspensionEndTime()
  {
    return suspension_end_time;
  }


  /** 
   * <em>suspension_end_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSuspensionEndTimeIsSet() {
    return suspension_end_time_is_set;
  }


  /** 
   * <em>suspension_end_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSuspensionEndTimeIsModified() {
    return suspension_end_time_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new CompBankConditionPK(institution_code, branch_code, pay_scheme_id);
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
   * <em>branch_code</em>カラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>pay_scheme_id</em>カラムの値を設定します。 
   *
   * @@param p_paySchemeId <em>pay_scheme_id</em>カラムの値をあらわす11桁以下のString値 
   */
  public final void setPaySchemeId( String p_paySchemeId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_scheme_id = p_paySchemeId;
    pay_scheme_id_is_set = true;
    pay_scheme_id_is_modified = true;
  }


  /** 
   * <em>max_amount_daily</em>カラムの値を設定します。 
   *
   * @@param p_maxAmountDaily <em>max_amount_daily</em>カラムの値をあらわす12桁以下のlong値 
   */
  public final void setMaxAmountDaily( long p_maxAmountDaily )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_amount_daily = new Long(p_maxAmountDaily);
    max_amount_daily_is_set = true;
    max_amount_daily_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_amount_daily</em>カラムに値を設定します。 
   */
  public final void setMaxAmountDaily( Long p_maxAmountDaily )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_amount_daily = p_maxAmountDaily;
    max_amount_daily_is_set = true;
    max_amount_daily_is_modified = true;
  }


  /** 
   * <em>max_amount_once</em>カラムの値を設定します。 
   *
   * @@param p_maxAmountOnce <em>max_amount_once</em>カラムの値をあらわす12桁以下のlong値 
   */
  public final void setMaxAmountOnce( long p_maxAmountOnce )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_amount_once = new Long(p_maxAmountOnce);
    max_amount_once_is_set = true;
    max_amount_once_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_amount_once</em>カラムに値を設定します。 
   */
  public final void setMaxAmountOnce( Long p_maxAmountOnce )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_amount_once = p_maxAmountOnce;
    max_amount_once_is_set = true;
    max_amount_once_is_modified = true;
  }


  /** 
   * <em>min_amount_once</em>カラムの値を設定します。 
   *
   * @@param p_minAmountOnce <em>min_amount_once</em>カラムの値をあらわす12桁以下のlong値 
   */
  public final void setMinAmountOnce( long p_minAmountOnce )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    min_amount_once = new Long(p_minAmountOnce);
    min_amount_once_is_set = true;
    min_amount_once_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>min_amount_once</em>カラムに値を設定します。 
   */
  public final void setMinAmountOnce( Long p_minAmountOnce )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    min_amount_once = p_minAmountOnce;
    min_amount_once_is_set = true;
    min_amount_once_is_modified = true;
  }


  /** 
   * <em>amount_unit</em>カラムの値を設定します。 
   *
   * @@param p_amountUnit <em>amount_unit</em>カラムの値をあらわす12桁以下のlong値 
   */
  public final void setAmountUnit( long p_amountUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_unit = new Long(p_amountUnit);
    amount_unit_is_set = true;
    amount_unit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_unit</em>カラムに値を設定します。 
   */
  public final void setAmountUnit( Long p_amountUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_unit = p_amountUnit;
    amount_unit_is_set = true;
    amount_unit_is_modified = true;
  }


  /** 
   * <em>max_count</em>カラムの値を設定します。 
   *
   * @@param p_maxCount <em>max_count</em>カラムの値をあらわす3桁以下のint値 
   */
  public final void setMaxCount( int p_maxCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_count = new Integer(p_maxCount);
    max_count_is_set = true;
    max_count_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_count</em>カラムに値を設定します。 
   */
  public final void setMaxCount( Integer p_maxCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_count = p_maxCount;
    max_count_is_set = true;
    max_count_is_modified = true;
  }


  /** 
   * <em>shop_id</em>カラムの値を設定します。 
   *
   * @@param p_shopId <em>shop_id</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setShopId( String p_shopId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    shop_id = p_shopId;
    shop_id_is_set = true;
    shop_id_is_modified = true;
  }


  /** 
   * <em>access_key</em>カラムの値を設定します。 
   *
   * @@param p_accessKey <em>access_key</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setAccessKey( String p_accessKey )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    access_key = p_accessKey;
    access_key_is_set = true;
    access_key_is_modified = true;
  }


  /** 
   * <em>sonar_code</em>カラムの値を設定します。 
   *
   * @@param p_sonarCode <em>sonar_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setSonarCode( String p_sonarCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_code = p_sonarCode;
    sonar_code_is_set = true;
    sonar_code_is_modified = true;
  }


  /** 
   * <em>payment_method</em>カラムの値を設定します。 
   *
   * @@param p_paymentMethod <em>payment_method</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPaymentMethod( String p_paymentMethod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_method = p_paymentMethod;
    payment_method_is_set = true;
    payment_method_is_modified = true;
  }


  /** 
   * <em>complete_flag</em>カラムの値を設定します。 
   *
   * @@param p_completeFlag <em>complete_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCompleteFlag( String p_completeFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    complete_flag = p_completeFlag;
    complete_flag_is_set = true;
    complete_flag_is_modified = true;
  }


  /** 
   * <em>limit_time</em>カラムの値を設定します。 
   *
   * @@param p_limitTime <em>limit_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setLimitTime( String p_limitTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limit_time = p_limitTime;
    limit_time_is_set = true;
    limit_time_is_modified = true;
  }


  /** 
   * <em>suspension_start_time</em>カラムの値を設定します。 
   *
   * @@param p_suspensionStartTime <em>suspension_start_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setSuspensionStartTime( String p_suspensionStartTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    suspension_start_time = p_suspensionStartTime;
    suspension_start_time_is_set = true;
    suspension_start_time_is_modified = true;
  }


  /** 
   * <em>suspension_end_time</em>カラムの値を設定します。 
   *
   * @@param p_suspensionEndTime <em>suspension_end_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setSuspensionEndTime( String p_suspensionEndTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    suspension_end_time = p_suspensionEndTime;
    suspension_end_time_is_set = true;
    suspension_end_time_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("amount_unit") ) {
                    return this.amount_unit;
                }
                else if ( name.equals("access_key") ) {
                    return this.access_key;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("complete_flag") ) {
                    return this.complete_flag;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("limit_time") ) {
                    return this.limit_time;
                }
                break;
            case 'm':
                if ( name.equals("max_amount_daily") ) {
                    return this.max_amount_daily;
                }
                else if ( name.equals("max_amount_once") ) {
                    return this.max_amount_once;
                }
                else if ( name.equals("min_amount_once") ) {
                    return this.min_amount_once;
                }
                else if ( name.equals("max_count") ) {
                    return this.max_count;
                }
                break;
            case 'p':
                if ( name.equals("pay_scheme_id") ) {
                    return this.pay_scheme_id;
                }
                else if ( name.equals("payment_method") ) {
                    return this.payment_method;
                }
                break;
            case 's':
                if ( name.equals("shop_id") ) {
                    return this.shop_id;
                }
                else if ( name.equals("sonar_code") ) {
                    return this.sonar_code;
                }
                else if ( name.equals("suspension_start_time") ) {
                    return this.suspension_start_time;
                }
                else if ( name.equals("suspension_end_time") ) {
                    return this.suspension_end_time;
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
                if ( name.equals("amount_unit") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_unit' must be Long: '"+value+"' is not." );
                    this.amount_unit = (Long) value;
                    if (this.amount_unit_is_set)
                        this.amount_unit_is_modified = true;
                    this.amount_unit_is_set = true;
                    return;
                }
                else if ( name.equals("access_key") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'access_key' must be String: '"+value+"' is not." );
                    this.access_key = (String) value;
                    if (this.access_key_is_set)
                        this.access_key_is_modified = true;
                    this.access_key_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("complete_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'complete_flag' must be String: '"+value+"' is not." );
                    this.complete_flag = (String) value;
                    if (this.complete_flag_is_set)
                        this.complete_flag_is_modified = true;
                    this.complete_flag_is_set = true;
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
                if ( name.equals("limit_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'limit_time' must be String: '"+value+"' is not." );
                    this.limit_time = (String) value;
                    if (this.limit_time_is_set)
                        this.limit_time_is_modified = true;
                    this.limit_time_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("max_amount_daily") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'max_amount_daily' must be Long: '"+value+"' is not." );
                    this.max_amount_daily = (Long) value;
                    if (this.max_amount_daily_is_set)
                        this.max_amount_daily_is_modified = true;
                    this.max_amount_daily_is_set = true;
                    return;
                }
                else if ( name.equals("max_amount_once") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'max_amount_once' must be Long: '"+value+"' is not." );
                    this.max_amount_once = (Long) value;
                    if (this.max_amount_once_is_set)
                        this.max_amount_once_is_modified = true;
                    this.max_amount_once_is_set = true;
                    return;
                }
                else if ( name.equals("min_amount_once") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'min_amount_once' must be Long: '"+value+"' is not." );
                    this.min_amount_once = (Long) value;
                    if (this.min_amount_once_is_set)
                        this.min_amount_once_is_modified = true;
                    this.min_amount_once_is_set = true;
                    return;
                }
                else if ( name.equals("max_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'max_count' must be Integer: '"+value+"' is not." );
                    this.max_count = (Integer) value;
                    if (this.max_count_is_set)
                        this.max_count_is_modified = true;
                    this.max_count_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("pay_scheme_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pay_scheme_id' must be String: '"+value+"' is not." );
                    this.pay_scheme_id = (String) value;
                    if (this.pay_scheme_id_is_set)
                        this.pay_scheme_id_is_modified = true;
                    this.pay_scheme_id_is_set = true;
                    return;
                }
                else if ( name.equals("payment_method") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_method' must be String: '"+value+"' is not." );
                    this.payment_method = (String) value;
                    if (this.payment_method_is_set)
                        this.payment_method_is_modified = true;
                    this.payment_method_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("shop_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'shop_id' must be String: '"+value+"' is not." );
                    this.shop_id = (String) value;
                    if (this.shop_id_is_set)
                        this.shop_id_is_modified = true;
                    this.shop_id_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_code' must be String: '"+value+"' is not." );
                    this.sonar_code = (String) value;
                    if (this.sonar_code_is_set)
                        this.sonar_code_is_modified = true;
                    this.sonar_code_is_set = true;
                    return;
                }
                else if ( name.equals("suspension_start_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'suspension_start_time' must be String: '"+value+"' is not." );
                    this.suspension_start_time = (String) value;
                    if (this.suspension_start_time_is_set)
                        this.suspension_start_time_is_modified = true;
                    this.suspension_start_time_is_set = true;
                    return;
                }
                else if ( name.equals("suspension_end_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'suspension_end_time' must be String: '"+value+"' is not." );
                    this.suspension_end_time = (String) value;
                    if (this.suspension_end_time_is_set)
                        this.suspension_end_time_is_modified = true;
                    this.suspension_end_time_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
