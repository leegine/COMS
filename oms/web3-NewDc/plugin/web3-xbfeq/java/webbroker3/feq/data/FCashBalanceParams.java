head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	FCashBalanceParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * f_cash_balanceテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link FCashBalanceRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link FCashBalanceParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link FCashBalanceParams}が{@@link FCashBalanceRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FCashBalancePK 
 * @@see FCashBalanceRow 
 */
public class FCashBalanceParams extends Params implements FCashBalanceRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "f_cash_balance";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = FCashBalanceRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return FCashBalanceRow.TYPE;
  }


  /** 
   * <em>feq_cash_balance_id</em>カラムの値 
   */
  public  long  feq_cash_balance_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>カラムの値 
   */
  public  long  sub_account_id;

  /** 
   * <em>currency_code</em>カラムの値 
   */
  public  String  currency_code;

  /** 
   * <em>cash_balance0</em>カラムの値 
   */
  public  Double  cash_balance0;

  /** 
   * <em>cash_balance1</em>カラムの値 
   */
  public  Double  cash_balance1;

  /** 
   * <em>cash_balance2</em>カラムの値 
   */
  public  Double  cash_balance2;

  /** 
   * <em>cash_balance3</em>カラムの値 
   */
  public  Double  cash_balance3;

  /** 
   * <em>cash_balance4</em>カラムの値 
   */
  public  Double  cash_balance4;

  /** 
   * <em>cash_balance5</em>カラムの値 
   */
  public  Double  cash_balance5;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean feq_cash_balance_id_is_set = false;
  private boolean feq_cash_balance_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean cash_balance0_is_set = false;
  private boolean cash_balance0_is_modified = false;
  private boolean cash_balance1_is_set = false;
  private boolean cash_balance1_is_modified = false;
  private boolean cash_balance2_is_set = false;
  private boolean cash_balance2_is_modified = false;
  private boolean cash_balance3_is_set = false;
  private boolean cash_balance3_is_modified = false;
  private boolean cash_balance4_is_set = false;
  private boolean cash_balance4_is_modified = false;
  private boolean cash_balance5_is_set = false;
  private boolean cash_balance5_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "feq_cash_balance_id=" + feq_cash_balance_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "currency_code=" +currency_code
      + "," + "cash_balance0=" +cash_balance0
      + "," + "cash_balance1=" +cash_balance1
      + "," + "cash_balance2=" +cash_balance2
      + "," + "cash_balance3=" +cash_balance3
      + "," + "cash_balance4=" +cash_balance4
      + "," + "cash_balance5=" +cash_balance5
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のFCashBalanceParamsオブジェクトを作成します。 
   */
  public FCashBalanceParams() {
    feq_cash_balance_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のFCashBalanceRowオブジェクトの値を利用してFCashBalanceParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するFCashBalanceRowオブジェクト 
   */
  public FCashBalanceParams( FCashBalanceRow p_row )
  {
    feq_cash_balance_id = p_row.getFeqCashBalanceId();
    feq_cash_balance_id_is_set = p_row.getFeqCashBalanceIdIsSet();
    feq_cash_balance_id_is_modified = p_row.getFeqCashBalanceIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    if ( !p_row.getCashBalance0IsNull() )
      cash_balance0 = new Double( p_row.getCashBalance0() );
    cash_balance0_is_set = p_row.getCashBalance0IsSet();
    cash_balance0_is_modified = p_row.getCashBalance0IsModified();
    if ( !p_row.getCashBalance1IsNull() )
      cash_balance1 = new Double( p_row.getCashBalance1() );
    cash_balance1_is_set = p_row.getCashBalance1IsSet();
    cash_balance1_is_modified = p_row.getCashBalance1IsModified();
    if ( !p_row.getCashBalance2IsNull() )
      cash_balance2 = new Double( p_row.getCashBalance2() );
    cash_balance2_is_set = p_row.getCashBalance2IsSet();
    cash_balance2_is_modified = p_row.getCashBalance2IsModified();
    if ( !p_row.getCashBalance3IsNull() )
      cash_balance3 = new Double( p_row.getCashBalance3() );
    cash_balance3_is_set = p_row.getCashBalance3IsSet();
    cash_balance3_is_modified = p_row.getCashBalance3IsModified();
    if ( !p_row.getCashBalance4IsNull() )
      cash_balance4 = new Double( p_row.getCashBalance4() );
    cash_balance4_is_set = p_row.getCashBalance4IsSet();
    cash_balance4_is_modified = p_row.getCashBalance4IsModified();
    if ( !p_row.getCashBalance5IsNull() )
      cash_balance5 = new Double( p_row.getCashBalance5() );
    cash_balance5_is_set = p_row.getCashBalance5IsSet();
    cash_balance5_is_modified = p_row.getCashBalance5IsModified();
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
      account_id_is_set = true;
      account_id_is_modified = true;
      sub_account_id_is_set = true;
      sub_account_id_is_modified = true;
      currency_code_is_set = true;
      currency_code_is_modified = true;
      cash_balance0_is_set = true;
      cash_balance0_is_modified = true;
      cash_balance1_is_set = true;
      cash_balance1_is_modified = true;
      cash_balance2_is_set = true;
      cash_balance2_is_modified = true;
      cash_balance3_is_set = true;
      cash_balance3_is_modified = true;
      cash_balance4_is_set = true;
      cash_balance4_is_modified = true;
      cash_balance5_is_set = true;
      cash_balance5_is_modified = true;
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
    if ( !( other instanceof FCashBalanceRow ) )
       return false;
    return fieldsEqual( (FCashBalanceRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のFCashBalanceRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( FCashBalanceRow row )
  {
    if ( feq_cash_balance_id != row.getFeqCashBalanceId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( cash_balance0 == null ) {
      if ( !row.getCashBalance0IsNull() )
        return false;
    } else if ( row.getCashBalance0IsNull() || ( cash_balance0.doubleValue() != row.getCashBalance0() ) ) {
        return false;
    }
    if ( cash_balance1 == null ) {
      if ( !row.getCashBalance1IsNull() )
        return false;
    } else if ( row.getCashBalance1IsNull() || ( cash_balance1.doubleValue() != row.getCashBalance1() ) ) {
        return false;
    }
    if ( cash_balance2 == null ) {
      if ( !row.getCashBalance2IsNull() )
        return false;
    } else if ( row.getCashBalance2IsNull() || ( cash_balance2.doubleValue() != row.getCashBalance2() ) ) {
        return false;
    }
    if ( cash_balance3 == null ) {
      if ( !row.getCashBalance3IsNull() )
        return false;
    } else if ( row.getCashBalance3IsNull() || ( cash_balance3.doubleValue() != row.getCashBalance3() ) ) {
        return false;
    }
    if ( cash_balance4 == null ) {
      if ( !row.getCashBalance4IsNull() )
        return false;
    } else if ( row.getCashBalance4IsNull() || ( cash_balance4.doubleValue() != row.getCashBalance4() ) ) {
        return false;
    }
    if ( cash_balance5 == null ) {
      if ( !row.getCashBalance5IsNull() )
        return false;
    } else if ( row.getCashBalance5IsNull() || ( cash_balance5.doubleValue() != row.getCashBalance5() ) ) {
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
      return  ((int) feq_cash_balance_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (cash_balance0!=null? cash_balance0.hashCode(): 0) 
        + (cash_balance1!=null? cash_balance1.hashCode(): 0) 
        + (cash_balance2!=null? cash_balance2.hashCode(): 0) 
        + (cash_balance3!=null? cash_balance3.hashCode(): 0) 
        + (cash_balance4!=null? cash_balance4.hashCode(): 0) 
        + (cash_balance5!=null? cash_balance5.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !sub_account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'sub_account_id' must be set before inserting.");
		if ( !currency_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'currency_code' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("feq_cash_balance_id",new Long(feq_cash_balance_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("currency_code",currency_code);
		if ( cash_balance0 != null )
			map.put("cash_balance0",cash_balance0);
		if ( cash_balance1 != null )
			map.put("cash_balance1",cash_balance1);
		if ( cash_balance2 != null )
			map.put("cash_balance2",cash_balance2);
		if ( cash_balance3 != null )
			map.put("cash_balance3",cash_balance3);
		if ( cash_balance4 != null )
			map.put("cash_balance4",cash_balance4);
		if ( cash_balance5 != null )
			map.put("cash_balance5",cash_balance5);
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
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( sub_account_id_is_modified )
			map.put("sub_account_id",new Long(sub_account_id));
		if ( currency_code_is_modified )
			map.put("currency_code",currency_code);
		if ( cash_balance0_is_modified )
			map.put("cash_balance0",cash_balance0);
		if ( cash_balance1_is_modified )
			map.put("cash_balance1",cash_balance1);
		if ( cash_balance2_is_modified )
			map.put("cash_balance2",cash_balance2);
		if ( cash_balance3_is_modified )
			map.put("cash_balance3",cash_balance3);
		if ( cash_balance4_is_modified )
			map.put("cash_balance4",cash_balance4);
		if ( cash_balance5_is_modified )
			map.put("cash_balance5",cash_balance5);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( currency_code_is_set )
				map.put("currency_code",currency_code);
			map.put("cash_balance0",cash_balance0);
			map.put("cash_balance1",cash_balance1);
			map.put("cash_balance2",cash_balance2);
			map.put("cash_balance3",cash_balance3);
			map.put("cash_balance4",cash_balance4);
			map.put("cash_balance5",cash_balance5);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>feq_cash_balance_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFeqCashBalanceId()
  {
    return feq_cash_balance_id;
  }


  /** 
   * <em>feq_cash_balance_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqCashBalanceIdIsSet() {
    return feq_cash_balance_id_is_set;
  }


  /** 
   * <em>feq_cash_balance_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqCashBalanceIdIsModified() {
    return feq_cash_balance_id_is_modified;
  }


  /** 
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
  }


  /** 
   * <em>sub_account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSubAccountId()
  {
    return sub_account_id;
  }


  /** 
   * <em>sub_account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccountIdIsSet() {
    return sub_account_id_is_set;
  }


  /** 
   * <em>sub_account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccountIdIsModified() {
    return sub_account_id_is_modified;
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
   * <em>cash_balance0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashBalance0()
  {
    return ( cash_balance0==null? ((double)0): cash_balance0.doubleValue() );
  }


  /** 
   * <em>cash_balance0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashBalance0IsNull()
  {
    return cash_balance0 == null;
  }


  /** 
   * <em>cash_balance0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance0IsSet() {
    return cash_balance0_is_set;
  }


  /** 
   * <em>cash_balance0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance0IsModified() {
    return cash_balance0_is_modified;
  }


  /** 
   * <em>cash_balance1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashBalance1()
  {
    return ( cash_balance1==null? ((double)0): cash_balance1.doubleValue() );
  }


  /** 
   * <em>cash_balance1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashBalance1IsNull()
  {
    return cash_balance1 == null;
  }


  /** 
   * <em>cash_balance1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance1IsSet() {
    return cash_balance1_is_set;
  }


  /** 
   * <em>cash_balance1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance1IsModified() {
    return cash_balance1_is_modified;
  }


  /** 
   * <em>cash_balance2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashBalance2()
  {
    return ( cash_balance2==null? ((double)0): cash_balance2.doubleValue() );
  }


  /** 
   * <em>cash_balance2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashBalance2IsNull()
  {
    return cash_balance2 == null;
  }


  /** 
   * <em>cash_balance2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance2IsSet() {
    return cash_balance2_is_set;
  }


  /** 
   * <em>cash_balance2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance2IsModified() {
    return cash_balance2_is_modified;
  }


  /** 
   * <em>cash_balance3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashBalance3()
  {
    return ( cash_balance3==null? ((double)0): cash_balance3.doubleValue() );
  }


  /** 
   * <em>cash_balance3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashBalance3IsNull()
  {
    return cash_balance3 == null;
  }


  /** 
   * <em>cash_balance3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance3IsSet() {
    return cash_balance3_is_set;
  }


  /** 
   * <em>cash_balance3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance3IsModified() {
    return cash_balance3_is_modified;
  }


  /** 
   * <em>cash_balance4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashBalance4()
  {
    return ( cash_balance4==null? ((double)0): cash_balance4.doubleValue() );
  }


  /** 
   * <em>cash_balance4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashBalance4IsNull()
  {
    return cash_balance4 == null;
  }


  /** 
   * <em>cash_balance4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance4IsSet() {
    return cash_balance4_is_set;
  }


  /** 
   * <em>cash_balance4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance4IsModified() {
    return cash_balance4_is_modified;
  }


  /** 
   * <em>cash_balance5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashBalance5()
  {
    return ( cash_balance5==null? ((double)0): cash_balance5.doubleValue() );
  }


  /** 
   * <em>cash_balance5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashBalance5IsNull()
  {
    return cash_balance5 == null;
  }


  /** 
   * <em>cash_balance5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance5IsSet() {
    return cash_balance5_is_set;
  }


  /** 
   * <em>cash_balance5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance5IsModified() {
    return cash_balance5_is_modified;
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
    return new FCashBalancePK(feq_cash_balance_id);
  }


  /** 
   * <em>feq_cash_balance_id</em>カラムの値を設定します。 
   *
   * @@param p_feqCashBalanceId <em>feq_cash_balance_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setFeqCashBalanceId( long p_feqCashBalanceId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    feq_cash_balance_id = p_feqCashBalanceId;
    feq_cash_balance_id_is_set = true;
    feq_cash_balance_id_is_modified = true;
  }


  /** 
   * <em>account_id</em>カラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>sub_account_id</em>カラムの値を設定します。 
   *
   * @@param p_subAccountId <em>sub_account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setSubAccountId( long p_subAccountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_account_id = p_subAccountId;
    sub_account_id_is_set = true;
    sub_account_id_is_modified = true;
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
   * <em>cash_balance0</em>カラムの値を設定します。 
   *
   * @@param p_cashBalance0 <em>cash_balance0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashBalance0( double p_cashBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance0 = new Double(p_cashBalance0);
    cash_balance0_is_set = true;
    cash_balance0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_balance0</em>カラムに値を設定します。 
   */
  public final void setCashBalance0( Double p_cashBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance0 = p_cashBalance0;
    cash_balance0_is_set = true;
    cash_balance0_is_modified = true;
  }


  /** 
   * <em>cash_balance1</em>カラムの値を設定します。 
   *
   * @@param p_cashBalance1 <em>cash_balance1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashBalance1( double p_cashBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance1 = new Double(p_cashBalance1);
    cash_balance1_is_set = true;
    cash_balance1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_balance1</em>カラムに値を設定します。 
   */
  public final void setCashBalance1( Double p_cashBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance1 = p_cashBalance1;
    cash_balance1_is_set = true;
    cash_balance1_is_modified = true;
  }


  /** 
   * <em>cash_balance2</em>カラムの値を設定します。 
   *
   * @@param p_cashBalance2 <em>cash_balance2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashBalance2( double p_cashBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance2 = new Double(p_cashBalance2);
    cash_balance2_is_set = true;
    cash_balance2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_balance2</em>カラムに値を設定します。 
   */
  public final void setCashBalance2( Double p_cashBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance2 = p_cashBalance2;
    cash_balance2_is_set = true;
    cash_balance2_is_modified = true;
  }


  /** 
   * <em>cash_balance3</em>カラムの値を設定します。 
   *
   * @@param p_cashBalance3 <em>cash_balance3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashBalance3( double p_cashBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance3 = new Double(p_cashBalance3);
    cash_balance3_is_set = true;
    cash_balance3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_balance3</em>カラムに値を設定します。 
   */
  public final void setCashBalance3( Double p_cashBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance3 = p_cashBalance3;
    cash_balance3_is_set = true;
    cash_balance3_is_modified = true;
  }


  /** 
   * <em>cash_balance4</em>カラムの値を設定します。 
   *
   * @@param p_cashBalance4 <em>cash_balance4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashBalance4( double p_cashBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance4 = new Double(p_cashBalance4);
    cash_balance4_is_set = true;
    cash_balance4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_balance4</em>カラムに値を設定します。 
   */
  public final void setCashBalance4( Double p_cashBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance4 = p_cashBalance4;
    cash_balance4_is_set = true;
    cash_balance4_is_modified = true;
  }


  /** 
   * <em>cash_balance5</em>カラムの値を設定します。 
   *
   * @@param p_cashBalance5 <em>cash_balance5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashBalance5( double p_cashBalance5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance5 = new Double(p_cashBalance5);
    cash_balance5_is_set = true;
    cash_balance5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_balance5</em>カラムに値を設定します。 
   */
  public final void setCashBalance5( Double p_cashBalance5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance5 = p_cashBalance5;
    cash_balance5_is_set = true;
    cash_balance5_is_modified = true;
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
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                break;
            case 'c':
                if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("cash_balance0") ) {
                    return this.cash_balance0;
                }
                else if ( name.equals("cash_balance1") ) {
                    return this.cash_balance1;
                }
                else if ( name.equals("cash_balance2") ) {
                    return this.cash_balance2;
                }
                else if ( name.equals("cash_balance3") ) {
                    return this.cash_balance3;
                }
                else if ( name.equals("cash_balance4") ) {
                    return this.cash_balance4;
                }
                else if ( name.equals("cash_balance5") ) {
                    return this.cash_balance5;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("feq_cash_balance_id") ) {
                    return new Long( feq_cash_balance_id );
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
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
                if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
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
                else if ( name.equals("cash_balance0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance0' must be Double: '"+value+"' is not." );
                    this.cash_balance0 = (Double) value;
                    if (this.cash_balance0_is_set)
                        this.cash_balance0_is_modified = true;
                    this.cash_balance0_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance1' must be Double: '"+value+"' is not." );
                    this.cash_balance1 = (Double) value;
                    if (this.cash_balance1_is_set)
                        this.cash_balance1_is_modified = true;
                    this.cash_balance1_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance2' must be Double: '"+value+"' is not." );
                    this.cash_balance2 = (Double) value;
                    if (this.cash_balance2_is_set)
                        this.cash_balance2_is_modified = true;
                    this.cash_balance2_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance3' must be Double: '"+value+"' is not." );
                    this.cash_balance3 = (Double) value;
                    if (this.cash_balance3_is_set)
                        this.cash_balance3_is_modified = true;
                    this.cash_balance3_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance4' must be Double: '"+value+"' is not." );
                    this.cash_balance4 = (Double) value;
                    if (this.cash_balance4_is_set)
                        this.cash_balance4_is_modified = true;
                    this.cash_balance4_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance5' must be Double: '"+value+"' is not." );
                    this.cash_balance5 = (Double) value;
                    if (this.cash_balance5_is_set)
                        this.cash_balance5_is_modified = true;
                    this.cash_balance5_is_set = true;
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
                if ( name.equals("feq_cash_balance_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'feq_cash_balance_id' must be Long: '"+value+"' is not." );
                    this.feq_cash_balance_id = ((Long) value).longValue();
                    if (this.feq_cash_balance_id_is_set)
                        this.feq_cash_balance_id_is_modified = true;
                    this.feq_cash_balance_id_is_set = true;
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
            case 's':
                if ( name.equals("sub_account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_account_id' must be Long: '"+value+"' is not." );
                    this.sub_account_id = ((Long) value).longValue();
                    if (this.sub_account_id_is_set)
                        this.sub_account_id_is_modified = true;
                    this.sub_account_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
