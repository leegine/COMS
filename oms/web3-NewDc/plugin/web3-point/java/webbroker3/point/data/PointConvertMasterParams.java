head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.49.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointConvertMasterParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.point.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * point_convert_masterテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link PointConvertMasterRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link PointConvertMasterParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link PointConvertMasterParams}が{@@link PointConvertMasterRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PointConvertMasterPK 
 * @@see PointConvertMasterRow 
 */
public class PointConvertMasterParams extends Params implements PointConvertMasterRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "point_convert_master";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = PointConvertMasterRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return PointConvertMasterRow.TYPE;
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
   * <em>fund_type</em>カラムの値 
   */
  public  String  fund_type;

  /** 
   * <em>tran_type</em>カラムの値 
   */
  public  String  tran_type;

  /** 
   * <em>buy_sell_div</em>カラムの値 
   */
  public  String  buy_sell_div;

  /** 
   * <em>convert_base</em>カラムの値 
   */
  public  Double  convert_base;

  /** 
   * <em>convert_value</em>カラムの値 
   */
  public  Double  convert_value;

  /** 
   * <em>special_term_start</em>カラムの値 
   */
  public  java.sql.Timestamp  special_term_start;

  /** 
   * <em>special_term_end</em>カラムの値 
   */
  public  java.sql.Timestamp  special_term_end;

  /** 
   * <em>special_convert_base</em>カラムの値 
   */
  public  Double  special_convert_base;

  /** 
   * <em>special_convert_value</em>カラムの値 
   */
  public  Double  special_convert_value;

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

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean fund_type_is_set = false;
  private boolean fund_type_is_modified = false;
  private boolean tran_type_is_set = false;
  private boolean tran_type_is_modified = false;
  private boolean buy_sell_div_is_set = false;
  private boolean buy_sell_div_is_modified = false;
  private boolean convert_base_is_set = false;
  private boolean convert_base_is_modified = false;
  private boolean convert_value_is_set = false;
  private boolean convert_value_is_modified = false;
  private boolean special_term_start_is_set = false;
  private boolean special_term_start_is_modified = false;
  private boolean special_term_end_is_set = false;
  private boolean special_term_end_is_modified = false;
  private boolean special_convert_base_is_set = false;
  private boolean special_convert_base_is_modified = false;
  private boolean special_convert_value_is_set = false;
  private boolean special_convert_value_is_modified = false;
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
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "fund_type=" + fund_type
      + "," + "tran_type=" + tran_type
      + "," + "buy_sell_div=" + buy_sell_div
      + "," + "convert_base=" +convert_base
      + "," + "convert_value=" +convert_value
      + "," + "special_term_start=" +special_term_start
      + "," + "special_term_end=" +special_term_end
      + "," + "special_convert_base=" +special_convert_base
      + "," + "special_convert_value=" +special_convert_value
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のPointConvertMasterParamsオブジェクトを作成します。 
   */
  public PointConvertMasterParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    fund_type_is_modified = true;
    tran_type_is_modified = true;
    buy_sell_div_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のPointConvertMasterRowオブジェクトの値を利用してPointConvertMasterParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するPointConvertMasterRowオブジェクト 
   */
  public PointConvertMasterParams( PointConvertMasterRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    fund_type = p_row.getFundType();
    fund_type_is_set = p_row.getFundTypeIsSet();
    fund_type_is_modified = p_row.getFundTypeIsModified();
    tran_type = p_row.getTranType();
    tran_type_is_set = p_row.getTranTypeIsSet();
    tran_type_is_modified = p_row.getTranTypeIsModified();
    buy_sell_div = p_row.getBuySellDiv();
    buy_sell_div_is_set = p_row.getBuySellDivIsSet();
    buy_sell_div_is_modified = p_row.getBuySellDivIsModified();
    if ( !p_row.getConvertBaseIsNull() )
      convert_base = new Double( p_row.getConvertBase() );
    convert_base_is_set = p_row.getConvertBaseIsSet();
    convert_base_is_modified = p_row.getConvertBaseIsModified();
    if ( !p_row.getConvertValueIsNull() )
      convert_value = new Double( p_row.getConvertValue() );
    convert_value_is_set = p_row.getConvertValueIsSet();
    convert_value_is_modified = p_row.getConvertValueIsModified();
    special_term_start = p_row.getSpecialTermStart();
    special_term_start_is_set = p_row.getSpecialTermStartIsSet();
    special_term_start_is_modified = p_row.getSpecialTermStartIsModified();
    special_term_end = p_row.getSpecialTermEnd();
    special_term_end_is_set = p_row.getSpecialTermEndIsSet();
    special_term_end_is_modified = p_row.getSpecialTermEndIsModified();
    if ( !p_row.getSpecialConvertBaseIsNull() )
      special_convert_base = new Double( p_row.getSpecialConvertBase() );
    special_convert_base_is_set = p_row.getSpecialConvertBaseIsSet();
    special_convert_base_is_modified = p_row.getSpecialConvertBaseIsModified();
    if ( !p_row.getSpecialConvertValueIsNull() )
      special_convert_value = new Double( p_row.getSpecialConvertValue() );
    special_convert_value_is_set = p_row.getSpecialConvertValueIsSet();
    special_convert_value_is_modified = p_row.getSpecialConvertValueIsModified();
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
      convert_base_is_set = true;
      convert_base_is_modified = true;
      convert_value_is_set = true;
      convert_value_is_modified = true;
      special_term_start_is_set = true;
      special_term_start_is_modified = true;
      special_term_end_is_set = true;
      special_term_end_is_modified = true;
      special_convert_base_is_set = true;
      special_convert_base_is_modified = true;
      special_convert_value_is_set = true;
      special_convert_value_is_modified = true;
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
    if ( !( other instanceof PointConvertMasterRow ) )
       return false;
    return fieldsEqual( (PointConvertMasterRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のPointConvertMasterRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( PointConvertMasterRow row )
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
    if ( fund_type == null ) {
      if ( row.getFundType() != null )
        return false;
    } else if ( !fund_type.equals( row.getFundType() ) ) {
        return false;
    }
    if ( tran_type == null ) {
      if ( row.getTranType() != null )
        return false;
    } else if ( !tran_type.equals( row.getTranType() ) ) {
        return false;
    }
    if ( buy_sell_div == null ) {
      if ( row.getBuySellDiv() != null )
        return false;
    } else if ( !buy_sell_div.equals( row.getBuySellDiv() ) ) {
        return false;
    }
    if ( convert_base == null ) {
      if ( !row.getConvertBaseIsNull() )
        return false;
    } else if ( row.getConvertBaseIsNull() || ( convert_base.doubleValue() != row.getConvertBase() ) ) {
        return false;
    }
    if ( convert_value == null ) {
      if ( !row.getConvertValueIsNull() )
        return false;
    } else if ( row.getConvertValueIsNull() || ( convert_value.doubleValue() != row.getConvertValue() ) ) {
        return false;
    }
    if ( special_term_start == null ) {
      if ( row.getSpecialTermStart() != null )
        return false;
    } else if ( !special_term_start.equals( row.getSpecialTermStart() ) ) {
        return false;
    }
    if ( special_term_end == null ) {
      if ( row.getSpecialTermEnd() != null )
        return false;
    } else if ( !special_term_end.equals( row.getSpecialTermEnd() ) ) {
        return false;
    }
    if ( special_convert_base == null ) {
      if ( !row.getSpecialConvertBaseIsNull() )
        return false;
    } else if ( row.getSpecialConvertBaseIsNull() || ( special_convert_base.doubleValue() != row.getSpecialConvertBase() ) ) {
        return false;
    }
    if ( special_convert_value == null ) {
      if ( !row.getSpecialConvertValueIsNull() )
        return false;
    } else if ( row.getSpecialConvertValueIsNull() || ( special_convert_value.doubleValue() != row.getSpecialConvertValue() ) ) {
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (fund_type!=null? fund_type.hashCode(): 0) 
        + (tran_type!=null? tran_type.hashCode(): 0) 
        + (buy_sell_div!=null? buy_sell_div.hashCode(): 0) 
        + (convert_base!=null? convert_base.hashCode(): 0) 
        + (convert_value!=null? convert_value.hashCode(): 0) 
        + (special_term_start!=null? special_term_start.hashCode(): 0) 
        + (special_term_end!=null? special_term_end.hashCode(): 0) 
        + (special_convert_base!=null? special_convert_base.hashCode(): 0) 
        + (special_convert_value!=null? special_convert_value.hashCode(): 0) 
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
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("fund_type",fund_type);
		map.put("tran_type",tran_type);
		map.put("buy_sell_div",buy_sell_div);
		if ( convert_base != null )
			map.put("convert_base",convert_base);
		if ( convert_value != null )
			map.put("convert_value",convert_value);
		if ( special_term_start != null )
			map.put("special_term_start",special_term_start);
		if ( special_term_end != null )
			map.put("special_term_end",special_term_end);
		if ( special_convert_base != null )
			map.put("special_convert_base",special_convert_base);
		if ( special_convert_value != null )
			map.put("special_convert_value",special_convert_value);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( convert_base_is_modified )
			map.put("convert_base",convert_base);
		if ( convert_value_is_modified )
			map.put("convert_value",convert_value);
		if ( special_term_start_is_modified )
			map.put("special_term_start",special_term_start);
		if ( special_term_end_is_modified )
			map.put("special_term_end",special_term_end);
		if ( special_convert_base_is_modified )
			map.put("special_convert_base",special_convert_base);
		if ( special_convert_value_is_modified )
			map.put("special_convert_value",special_convert_value);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("convert_base",convert_base);
			map.put("convert_value",convert_value);
			map.put("special_term_start",special_term_start);
			map.put("special_term_end",special_term_end);
			map.put("special_convert_base",special_convert_base);
			map.put("special_convert_value",special_convert_value);
			map.put("last_updater",last_updater);
			map.put("created_timestamp",created_timestamp);
			map.put("last_updated_timestamp",last_updated_timestamp);
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
   * <em>fund_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFundType()
  {
    return fund_type;
  }


  /** 
   * <em>fund_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundTypeIsSet() {
    return fund_type_is_set;
  }


  /** 
   * <em>fund_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundTypeIsModified() {
    return fund_type_is_modified;
  }


  /** 
   * <em>tran_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTranType()
  {
    return tran_type;
  }


  /** 
   * <em>tran_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTranTypeIsSet() {
    return tran_type_is_set;
  }


  /** 
   * <em>tran_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTranTypeIsModified() {
    return tran_type_is_modified;
  }


  /** 
   * <em>buy_sell_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuySellDiv()
  {
    return buy_sell_div;
  }


  /** 
   * <em>buy_sell_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuySellDivIsSet() {
    return buy_sell_div_is_set;
  }


  /** 
   * <em>buy_sell_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuySellDivIsModified() {
    return buy_sell_div_is_modified;
  }


  /** 
   * <em>convert_base</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getConvertBase()
  {
    return ( convert_base==null? ((double)0): convert_base.doubleValue() );
  }


  /** 
   * <em>convert_base</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getConvertBaseIsNull()
  {
    return convert_base == null;
  }


  /** 
   * <em>convert_base</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConvertBaseIsSet() {
    return convert_base_is_set;
  }


  /** 
   * <em>convert_base</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConvertBaseIsModified() {
    return convert_base_is_modified;
  }


  /** 
   * <em>convert_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getConvertValue()
  {
    return ( convert_value==null? ((double)0): convert_value.doubleValue() );
  }


  /** 
   * <em>convert_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getConvertValueIsNull()
  {
    return convert_value == null;
  }


  /** 
   * <em>convert_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConvertValueIsSet() {
    return convert_value_is_set;
  }


  /** 
   * <em>convert_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConvertValueIsModified() {
    return convert_value_is_modified;
  }


  /** 
   * <em>special_term_start</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSpecialTermStart()
  {
    return special_term_start;
  }


  /** 
   * <em>special_term_start</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialTermStartIsSet() {
    return special_term_start_is_set;
  }


  /** 
   * <em>special_term_start</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialTermStartIsModified() {
    return special_term_start_is_modified;
  }


  /** 
   * <em>special_term_end</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSpecialTermEnd()
  {
    return special_term_end;
  }


  /** 
   * <em>special_term_end</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialTermEndIsSet() {
    return special_term_end_is_set;
  }


  /** 
   * <em>special_term_end</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialTermEndIsModified() {
    return special_term_end_is_modified;
  }


  /** 
   * <em>special_convert_base</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpecialConvertBase()
  {
    return ( special_convert_base==null? ((double)0): special_convert_base.doubleValue() );
  }


  /** 
   * <em>special_convert_base</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSpecialConvertBaseIsNull()
  {
    return special_convert_base == null;
  }


  /** 
   * <em>special_convert_base</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialConvertBaseIsSet() {
    return special_convert_base_is_set;
  }


  /** 
   * <em>special_convert_base</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialConvertBaseIsModified() {
    return special_convert_base_is_modified;
  }


  /** 
   * <em>special_convert_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpecialConvertValue()
  {
    return ( special_convert_value==null? ((double)0): special_convert_value.doubleValue() );
  }


  /** 
   * <em>special_convert_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSpecialConvertValueIsNull()
  {
    return special_convert_value == null;
  }


  /** 
   * <em>special_convert_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialConvertValueIsSet() {
    return special_convert_value_is_set;
  }


  /** 
   * <em>special_convert_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialConvertValueIsModified() {
    return special_convert_value_is_modified;
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
    return new PointConvertMasterPK(institution_code, branch_code, fund_type, tran_type, buy_sell_div);
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
   * <em>fund_type</em>カラムの値を設定します。 
   *
   * @@param p_fundType <em>fund_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setFundType( String p_fundType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_type = p_fundType;
    fund_type_is_set = true;
    fund_type_is_modified = true;
  }


  /** 
   * <em>tran_type</em>カラムの値を設定します。 
   *
   * @@param p_tranType <em>tran_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setTranType( String p_tranType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tran_type = p_tranType;
    tran_type_is_set = true;
    tran_type_is_modified = true;
  }


  /** 
   * <em>buy_sell_div</em>カラムの値を設定します。 
   *
   * @@param p_buySellDiv <em>buy_sell_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBuySellDiv( String p_buySellDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_sell_div = p_buySellDiv;
    buy_sell_div_is_set = true;
    buy_sell_div_is_modified = true;
  }


  /** 
   * <em>convert_base</em>カラムの値を設定します。 
   *
   * @@param p_convertBase <em>convert_base</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setConvertBase( double p_convertBase )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    convert_base = new Double(p_convertBase);
    convert_base_is_set = true;
    convert_base_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>convert_base</em>カラムに値を設定します。 
   */
  public final void setConvertBase( Double p_convertBase )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    convert_base = p_convertBase;
    convert_base_is_set = true;
    convert_base_is_modified = true;
  }


  /** 
   * <em>convert_value</em>カラムの値を設定します。 
   *
   * @@param p_convertValue <em>convert_value</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setConvertValue( double p_convertValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    convert_value = new Double(p_convertValue);
    convert_value_is_set = true;
    convert_value_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>convert_value</em>カラムに値を設定します。 
   */
  public final void setConvertValue( Double p_convertValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    convert_value = p_convertValue;
    convert_value_is_set = true;
    convert_value_is_modified = true;
  }


  /** 
   * <em>special_term_start</em>カラムの値を設定します。 
   *
   * @@param p_specialTermStart <em>special_term_start</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSpecialTermStart( java.sql.Timestamp p_specialTermStart )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_term_start = p_specialTermStart;
    special_term_start_is_set = true;
    special_term_start_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>special_term_start</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSpecialTermStart( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    special_term_start = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    special_term_start_is_set = true;
    special_term_start_is_modified = true;
  }


  /** 
   * <em>special_term_end</em>カラムの値を設定します。 
   *
   * @@param p_specialTermEnd <em>special_term_end</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSpecialTermEnd( java.sql.Timestamp p_specialTermEnd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_term_end = p_specialTermEnd;
    special_term_end_is_set = true;
    special_term_end_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>special_term_end</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSpecialTermEnd( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    special_term_end = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    special_term_end_is_set = true;
    special_term_end_is_modified = true;
  }


  /** 
   * <em>special_convert_base</em>カラムの値を設定します。 
   *
   * @@param p_specialConvertBase <em>special_convert_base</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setSpecialConvertBase( double p_specialConvertBase )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_convert_base = new Double(p_specialConvertBase);
    special_convert_base_is_set = true;
    special_convert_base_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>special_convert_base</em>カラムに値を設定します。 
   */
  public final void setSpecialConvertBase( Double p_specialConvertBase )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    special_convert_base = p_specialConvertBase;
    special_convert_base_is_set = true;
    special_convert_base_is_modified = true;
  }


  /** 
   * <em>special_convert_value</em>カラムの値を設定します。 
   *
   * @@param p_specialConvertValue <em>special_convert_value</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setSpecialConvertValue( double p_specialConvertValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_convert_value = new Double(p_specialConvertValue);
    special_convert_value_is_set = true;
    special_convert_value_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>special_convert_value</em>カラムに値を設定します。 
   */
  public final void setSpecialConvertValue( Double p_specialConvertValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    special_convert_value = p_specialConvertValue;
    special_convert_value_is_set = true;
    special_convert_value_is_modified = true;
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
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("buy_sell_div") ) {
                    return this.buy_sell_div;
                }
                break;
            case 'c':
                if ( name.equals("convert_base") ) {
                    return this.convert_base;
                }
                else if ( name.equals("convert_value") ) {
                    return this.convert_value;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("fund_type") ) {
                    return this.fund_type;
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
            case 's':
                if ( name.equals("special_term_start") ) {
                    return this.special_term_start;
                }
                else if ( name.equals("special_term_end") ) {
                    return this.special_term_end;
                }
                else if ( name.equals("special_convert_base") ) {
                    return this.special_convert_base;
                }
                else if ( name.equals("special_convert_value") ) {
                    return this.special_convert_value;
                }
                break;
            case 't':
                if ( name.equals("tran_type") ) {
                    return this.tran_type;
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
                if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("buy_sell_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_sell_div' must be String: '"+value+"' is not." );
                    this.buy_sell_div = (String) value;
                    if (this.buy_sell_div_is_set)
                        this.buy_sell_div_is_modified = true;
                    this.buy_sell_div_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("convert_base") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'convert_base' must be Double: '"+value+"' is not." );
                    this.convert_base = (Double) value;
                    if (this.convert_base_is_set)
                        this.convert_base_is_modified = true;
                    this.convert_base_is_set = true;
                    return;
                }
                else if ( name.equals("convert_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'convert_value' must be Double: '"+value+"' is not." );
                    this.convert_value = (Double) value;
                    if (this.convert_value_is_set)
                        this.convert_value_is_modified = true;
                    this.convert_value_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( value != null )
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
                if ( name.equals("fund_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_type' must be String: '"+value+"' is not." );
                    this.fund_type = (String) value;
                    if (this.fund_type_is_set)
                        this.fund_type_is_modified = true;
                    this.fund_type_is_set = true;
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
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("special_term_start") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'special_term_start' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.special_term_start = (java.sql.Timestamp) value;
                    if (this.special_term_start_is_set)
                        this.special_term_start_is_modified = true;
                    this.special_term_start_is_set = true;
                    return;
                }
                else if ( name.equals("special_term_end") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'special_term_end' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.special_term_end = (java.sql.Timestamp) value;
                    if (this.special_term_end_is_set)
                        this.special_term_end_is_modified = true;
                    this.special_term_end_is_set = true;
                    return;
                }
                else if ( name.equals("special_convert_base") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'special_convert_base' must be Double: '"+value+"' is not." );
                    this.special_convert_base = (Double) value;
                    if (this.special_convert_base_is_set)
                        this.special_convert_base_is_modified = true;
                    this.special_convert_base_is_set = true;
                    return;
                }
                else if ( name.equals("special_convert_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'special_convert_value' must be Double: '"+value+"' is not." );
                    this.special_convert_value = (Double) value;
                    if (this.special_convert_value_is_set)
                        this.special_convert_value_is_modified = true;
                    this.special_convert_value_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tran_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tran_type' must be String: '"+value+"' is not." );
                    this.tran_type = (String) value;
                    if (this.tran_type_is_set)
                        this.tran_type_is_modified = true;
                    this.tran_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
