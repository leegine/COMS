head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.30.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchMarketDealtCondParams.java;


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
 * branch_market_dealt_condテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BranchMarketDealtCondRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BranchMarketDealtCondParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BranchMarketDealtCondParams}が{@@link BranchMarketDealtCondRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchMarketDealtCondPK 
 * @@see BranchMarketDealtCondRow 
 */
public class BranchMarketDealtCondParams extends Params implements BranchMarketDealtCondRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "branch_market_dealt_cond";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BranchMarketDealtCondRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BranchMarketDealtCondRow.TYPE;
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
   * <em>market_code</em>カラムの値 
   */
  public  String  market_code;

  /** 
   * <em>mart_can_dealt_equity</em>カラムの値 
   */
  public  String  mart_can_dealt_equity;

  /** 
   * <em>limited_unit_1st_sec</em>カラムの値 
   */
  public  Integer  limited_unit_1st_sec;

  /** 
   * <em>limited_unit_2nd_sec</em>カラムの値 
   */
  public  Integer  limited_unit_2nd_sec;

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
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean mart_can_dealt_equity_is_set = false;
  private boolean mart_can_dealt_equity_is_modified = false;
  private boolean limited_unit_1st_sec_is_set = false;
  private boolean limited_unit_1st_sec_is_modified = false;
  private boolean limited_unit_2nd_sec_is_set = false;
  private boolean limited_unit_2nd_sec_is_modified = false;
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
      + "," + "market_code=" + market_code
      + "," + "mart_can_dealt_equity=" +mart_can_dealt_equity
      + "," + "limited_unit_1st_sec=" +limited_unit_1st_sec
      + "," + "limited_unit_2nd_sec=" +limited_unit_2nd_sec
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のBranchMarketDealtCondParamsオブジェクトを作成します。 
   */
  public BranchMarketDealtCondParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    market_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBranchMarketDealtCondRowオブジェクトの値を利用してBranchMarketDealtCondParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBranchMarketDealtCondRowオブジェクト 
   */
  public BranchMarketDealtCondParams( BranchMarketDealtCondRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    mart_can_dealt_equity = p_row.getMartCanDealtEquity();
    mart_can_dealt_equity_is_set = p_row.getMartCanDealtEquityIsSet();
    mart_can_dealt_equity_is_modified = p_row.getMartCanDealtEquityIsModified();
    if ( !p_row.getLimitedUnit1stSecIsNull() )
      limited_unit_1st_sec = new Integer( p_row.getLimitedUnit1stSec() );
    limited_unit_1st_sec_is_set = p_row.getLimitedUnit1stSecIsSet();
    limited_unit_1st_sec_is_modified = p_row.getLimitedUnit1stSecIsModified();
    if ( !p_row.getLimitedUnit2ndSecIsNull() )
      limited_unit_2nd_sec = new Integer( p_row.getLimitedUnit2ndSec() );
    limited_unit_2nd_sec_is_set = p_row.getLimitedUnit2ndSecIsSet();
    limited_unit_2nd_sec_is_modified = p_row.getLimitedUnit2ndSecIsModified();
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
      mart_can_dealt_equity_is_set = true;
      mart_can_dealt_equity_is_modified = true;
      limited_unit_1st_sec_is_set = true;
      limited_unit_1st_sec_is_modified = true;
      limited_unit_2nd_sec_is_set = true;
      limited_unit_2nd_sec_is_modified = true;
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
    if ( !( other instanceof BranchMarketDealtCondRow ) )
       return false;
    return fieldsEqual( (BranchMarketDealtCondRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBranchMarketDealtCondRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BranchMarketDealtCondRow row )
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
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( mart_can_dealt_equity == null ) {
      if ( row.getMartCanDealtEquity() != null )
        return false;
    } else if ( !mart_can_dealt_equity.equals( row.getMartCanDealtEquity() ) ) {
        return false;
    }
    if ( limited_unit_1st_sec == null ) {
      if ( !row.getLimitedUnit1stSecIsNull() )
        return false;
    } else if ( row.getLimitedUnit1stSecIsNull() || ( limited_unit_1st_sec.intValue() != row.getLimitedUnit1stSec() ) ) {
        return false;
    }
    if ( limited_unit_2nd_sec == null ) {
      if ( !row.getLimitedUnit2ndSecIsNull() )
        return false;
    } else if ( row.getLimitedUnit2ndSecIsNull() || ( limited_unit_2nd_sec.intValue() != row.getLimitedUnit2ndSec() ) ) {
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
        + (market_code!=null? market_code.hashCode(): 0) 
        + (mart_can_dealt_equity!=null? mart_can_dealt_equity.hashCode(): 0) 
        + (limited_unit_1st_sec!=null? limited_unit_1st_sec.hashCode(): 0) 
        + (limited_unit_2nd_sec!=null? limited_unit_2nd_sec.hashCode(): 0) 
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
		map.put("market_code",market_code);
		if ( mart_can_dealt_equity != null )
			map.put("mart_can_dealt_equity",mart_can_dealt_equity);
		if ( limited_unit_1st_sec != null )
			map.put("limited_unit_1st_sec",limited_unit_1st_sec);
		if ( limited_unit_2nd_sec != null )
			map.put("limited_unit_2nd_sec",limited_unit_2nd_sec);
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
		if ( mart_can_dealt_equity_is_modified )
			map.put("mart_can_dealt_equity",mart_can_dealt_equity);
		if ( limited_unit_1st_sec_is_modified )
			map.put("limited_unit_1st_sec",limited_unit_1st_sec);
		if ( limited_unit_2nd_sec_is_modified )
			map.put("limited_unit_2nd_sec",limited_unit_2nd_sec);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("mart_can_dealt_equity",mart_can_dealt_equity);
			map.put("limited_unit_1st_sec",limited_unit_1st_sec);
			map.put("limited_unit_2nd_sec",limited_unit_2nd_sec);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
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
   * <em>mart_can_dealt_equity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMartCanDealtEquity()
  {
    return mart_can_dealt_equity;
  }


  /** 
   * <em>mart_can_dealt_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMartCanDealtEquityIsSet() {
    return mart_can_dealt_equity_is_set;
  }


  /** 
   * <em>mart_can_dealt_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMartCanDealtEquityIsModified() {
    return mart_can_dealt_equity_is_modified;
  }


  /** 
   * <em>limited_unit_1st_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLimitedUnit1stSec()
  {
    return ( limited_unit_1st_sec==null? ((int)0): limited_unit_1st_sec.intValue() );
  }


  /** 
   * <em>limited_unit_1st_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLimitedUnit1stSecIsNull()
  {
    return limited_unit_1st_sec == null;
  }


  /** 
   * <em>limited_unit_1st_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnit1stSecIsSet() {
    return limited_unit_1st_sec_is_set;
  }


  /** 
   * <em>limited_unit_1st_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnit1stSecIsModified() {
    return limited_unit_1st_sec_is_modified;
  }


  /** 
   * <em>limited_unit_2nd_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLimitedUnit2ndSec()
  {
    return ( limited_unit_2nd_sec==null? ((int)0): limited_unit_2nd_sec.intValue() );
  }


  /** 
   * <em>limited_unit_2nd_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLimitedUnit2ndSecIsNull()
  {
    return limited_unit_2nd_sec == null;
  }


  /** 
   * <em>limited_unit_2nd_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnit2ndSecIsSet() {
    return limited_unit_2nd_sec_is_set;
  }


  /** 
   * <em>limited_unit_2nd_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnit2ndSecIsModified() {
    return limited_unit_2nd_sec_is_modified;
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
    return new BranchMarketDealtCondPK(institution_code, branch_code, market_code);
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
   * <em>mart_can_dealt_equity</em>カラムの値を設定します。 
   *
   * @@param p_martCanDealtEquity <em>mart_can_dealt_equity</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMartCanDealtEquity( String p_martCanDealtEquity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mart_can_dealt_equity = p_martCanDealtEquity;
    mart_can_dealt_equity_is_set = true;
    mart_can_dealt_equity_is_modified = true;
  }


  /** 
   * <em>limited_unit_1st_sec</em>カラムの値を設定します。 
   *
   * @@param p_limitedUnit1stSec <em>limited_unit_1st_sec</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setLimitedUnit1stSec( int p_limitedUnit1stSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_1st_sec = new Integer(p_limitedUnit1stSec);
    limited_unit_1st_sec_is_set = true;
    limited_unit_1st_sec_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>limited_unit_1st_sec</em>カラムに値を設定します。 
   */
  public final void setLimitedUnit1stSec( Integer p_limitedUnit1stSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_1st_sec = p_limitedUnit1stSec;
    limited_unit_1st_sec_is_set = true;
    limited_unit_1st_sec_is_modified = true;
  }


  /** 
   * <em>limited_unit_2nd_sec</em>カラムの値を設定します。 
   *
   * @@param p_limitedUnit2ndSec <em>limited_unit_2nd_sec</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setLimitedUnit2ndSec( int p_limitedUnit2ndSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_2nd_sec = new Integer(p_limitedUnit2ndSec);
    limited_unit_2nd_sec_is_set = true;
    limited_unit_2nd_sec_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>limited_unit_2nd_sec</em>カラムに値を設定します。 
   */
  public final void setLimitedUnit2ndSec( Integer p_limitedUnit2ndSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_2nd_sec = p_limitedUnit2ndSec;
    limited_unit_2nd_sec_is_set = true;
    limited_unit_2nd_sec_is_modified = true;
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
                break;
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
                if ( name.equals("limited_unit_1st_sec") ) {
                    return this.limited_unit_1st_sec;
                }
                else if ( name.equals("limited_unit_2nd_sec") ) {
                    return this.limited_unit_2nd_sec;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                else if ( name.equals("mart_can_dealt_equity") ) {
                    return this.mart_can_dealt_equity;
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
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
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
                if ( name.equals("limited_unit_1st_sec") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'limited_unit_1st_sec' must be Integer: '"+value+"' is not." );
                    this.limited_unit_1st_sec = (Integer) value;
                    if (this.limited_unit_1st_sec_is_set)
                        this.limited_unit_1st_sec_is_modified = true;
                    this.limited_unit_1st_sec_is_set = true;
                    return;
                }
                else if ( name.equals("limited_unit_2nd_sec") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'limited_unit_2nd_sec' must be Integer: '"+value+"' is not." );
                    this.limited_unit_2nd_sec = (Integer) value;
                    if (this.limited_unit_2nd_sec_is_set)
                        this.limited_unit_2nd_sec_is_modified = true;
                    this.limited_unit_2nd_sec_is_set = true;
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
                else if ( name.equals("mart_can_dealt_equity") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mart_can_dealt_equity' must be String: '"+value+"' is not." );
                    this.mart_can_dealt_equity = (String) value;
                    if (this.mart_can_dealt_equity_is_set)
                        this.mart_can_dealt_equity_is_modified = true;
                    this.mart_can_dealt_equity_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
