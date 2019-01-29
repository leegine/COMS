head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.30.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpOtherTempRestraintParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * tp_other_temp_restraintテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link TpOtherTempRestraintRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link TpOtherTempRestraintParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link TpOtherTempRestraintParams}が{@@link TpOtherTempRestraintRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpOtherTempRestraintPK 
 * @@see TpOtherTempRestraintRow 
 */
public class TpOtherTempRestraintParams extends Params implements TpOtherTempRestraintRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "tp_other_temp_restraint";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = TpOtherTempRestraintRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return TpOtherTempRestraintRow.TYPE;
  }


  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>restraint_div</em>カラムの値 
   */
  public  String  restraint_div;

  /** 
   * <em>amount</em>カラムの値 
   */
  public  double  amount;

  /** 
   * <em>transaction_date</em>カラムの値 
   */
  public  java.sql.Timestamp  transaction_date;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>delete_key1</em>カラムの値 
   */
  public  String  delete_key1;

  /** 
   * <em>delete_key2</em>カラムの値 
   */
  public  String  delete_key2;

  /** 
   * <em>delete_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  delete_flag;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean restraint_div_is_set = false;
  private boolean restraint_div_is_modified = false;
  private boolean amount_is_set = false;
  private boolean amount_is_modified = false;
  private boolean transaction_date_is_set = false;
  private boolean transaction_date_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean delete_key1_is_set = false;
  private boolean delete_key1_is_modified = false;
  private boolean delete_key2_is_set = false;
  private boolean delete_key2_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "account_id=" +account_id
      + "," + "restraint_div=" +restraint_div
      + "," + "amount=" +amount
      + "," + "transaction_date=" +transaction_date
      + "," + "delivery_date=" +delivery_date
      + "," + "delete_key1=" +delete_key1
      + "," + "delete_key2=" +delete_key2
      + "," + "delete_flag=" +delete_flag
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のTpOtherTempRestraintParamsオブジェクトを作成します。 
   */
  public TpOtherTempRestraintParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のTpOtherTempRestraintRowオブジェクトの値を利用してTpOtherTempRestraintParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するTpOtherTempRestraintRowオブジェクト 
   */
  public TpOtherTempRestraintParams( TpOtherTempRestraintRow p_row )
  {
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    restraint_div = p_row.getRestraintDiv();
    restraint_div_is_set = p_row.getRestraintDivIsSet();
    restraint_div_is_modified = p_row.getRestraintDivIsModified();
    amount = p_row.getAmount();
    amount_is_set = p_row.getAmountIsSet();
    amount_is_modified = p_row.getAmountIsModified();
    transaction_date = p_row.getTransactionDate();
    transaction_date_is_set = p_row.getTransactionDateIsSet();
    transaction_date_is_modified = p_row.getTransactionDateIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    delete_key1 = p_row.getDeleteKey1();
    delete_key1_is_set = p_row.getDeleteKey1IsSet();
    delete_key1_is_modified = p_row.getDeleteKey1IsModified();
    delete_key2 = p_row.getDeleteKey2();
    delete_key2_is_set = p_row.getDeleteKey2IsSet();
    delete_key2_is_modified = p_row.getDeleteKey2IsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
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
      restraint_div_is_set = true;
      restraint_div_is_modified = true;
      amount_is_set = true;
      amount_is_modified = true;
      transaction_date_is_set = true;
      transaction_date_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      delete_key1_is_set = true;
      delete_key1_is_modified = true;
      delete_key2_is_set = true;
      delete_key2_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
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
    if ( !( other instanceof TpOtherTempRestraintRow ) )
       return false;
    return fieldsEqual( (TpOtherTempRestraintRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のTpOtherTempRestraintRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( TpOtherTempRestraintRow row )
  {
    if ( account_id != row.getAccountId() )
      return false;
    if ( restraint_div == null ) {
      if ( row.getRestraintDiv() != null )
        return false;
    } else if ( !restraint_div.equals( row.getRestraintDiv() ) ) {
        return false;
    }
    if ( amount != row.getAmount() )
      return false;
    if ( transaction_date == null ) {
      if ( row.getTransactionDate() != null )
        return false;
    } else if ( !transaction_date.equals( row.getTransactionDate() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( delete_key1 == null ) {
      if ( row.getDeleteKey1() != null )
        return false;
    } else if ( !delete_key1.equals( row.getDeleteKey1() ) ) {
        return false;
    }
    if ( delete_key2 == null ) {
      if ( row.getDeleteKey2() != null )
        return false;
    } else if ( !delete_key2.equals( row.getDeleteKey2() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
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
      return  ((int) account_id)
        + (restraint_div!=null? restraint_div.hashCode(): 0) 
        + ((int) amount)
        + (transaction_date!=null? transaction_date.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (delete_key1!=null? delete_key1.hashCode(): 0) 
        + (delete_key2!=null? delete_key2.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
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
		if ( !restraint_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'restraint_div' must be set before inserting.");
		if ( !amount_is_set )
			throw new IllegalArgumentException("non-nullable field 'amount' must be set before inserting.");
		if ( !delivery_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_date' must be set before inserting.");
		if ( !delete_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'delete_flag' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("account_id",new Long(account_id));
		map.put("restraint_div",restraint_div);
		map.put("amount",new Double(amount));
		if ( transaction_date != null )
			map.put("transaction_date",transaction_date);
		map.put("delivery_date",delivery_date);
		if ( delete_key1 != null )
			map.put("delete_key1",delete_key1);
		if ( delete_key2 != null )
			map.put("delete_key2",delete_key2);
		map.put("delete_flag",delete_flag);
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
		if ( restraint_div_is_modified )
			map.put("restraint_div",restraint_div);
		if ( amount_is_modified )
			map.put("amount",new Double(amount));
		if ( transaction_date_is_modified )
			map.put("transaction_date",transaction_date);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( delete_key1_is_modified )
			map.put("delete_key1",delete_key1);
		if ( delete_key2_is_modified )
			map.put("delete_key2",delete_key2);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( restraint_div_is_set )
				map.put("restraint_div",restraint_div);
			if ( amount_is_set )
				map.put("amount",new Double(amount));
			map.put("transaction_date",transaction_date);
			if ( delivery_date_is_set )
				map.put("delivery_date",delivery_date);
			map.put("delete_key1",delete_key1);
			map.put("delete_key2",delete_key2);
			if ( delete_flag_is_set )
				map.put("delete_flag",delete_flag);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>restraint_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRestraintDiv()
  {
    return restraint_div;
  }


  /** 
   * <em>restraint_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRestraintDivIsSet() {
    return restraint_div_is_set;
  }


  /** 
   * <em>restraint_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRestraintDivIsModified() {
    return restraint_div_is_modified;
  }


  /** 
   * <em>amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAmount()
  {
    return amount;
  }


  /** 
   * <em>amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountIsSet() {
    return amount_is_set;
  }


  /** 
   * <em>amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountIsModified() {
    return amount_is_modified;
  }


  /** 
   * <em>transaction_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTransactionDate()
  {
    return transaction_date;
  }


  /** 
   * <em>transaction_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactionDateIsSet() {
    return transaction_date_is_set;
  }


  /** 
   * <em>transaction_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactionDateIsModified() {
    return transaction_date_is_modified;
  }


  /** 
   * <em>delivery_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDeliveryDate()
  {
    return delivery_date;
  }


  /** 
   * <em>delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsSet() {
    return delivery_date_is_set;
  }


  /** 
   * <em>delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsModified() {
    return delivery_date_is_modified;
  }


  /** 
   * <em>delete_key1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeleteKey1()
  {
    return delete_key1;
  }


  /** 
   * <em>delete_key1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteKey1IsSet() {
    return delete_key1_is_set;
  }


  /** 
   * <em>delete_key1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteKey1IsModified() {
    return delete_key1_is_modified;
  }


  /** 
   * <em>delete_key2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeleteKey2()
  {
    return delete_key2;
  }


  /** 
   * <em>delete_key2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteKey2IsSet() {
    return delete_key2_is_set;
  }


  /** 
   * <em>delete_key2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteKey2IsModified() {
    return delete_key2_is_modified;
  }


  /** 
   * <em>delete_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDeleteFlag()
  {
    return delete_flag;
  }


  /** 
   * <em>delete_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsSet() {
    return delete_flag_is_set;
  }


  /** 
   * <em>delete_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsModified() {
    return delete_flag_is_modified;
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
    throw new UnsupportedOperationException("Table tp_other_temp_restraint has no primary key.");
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
   * <em>restraint_div</em>カラムの値を設定します。 
   *
   * @@param p_restraintDiv <em>restraint_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRestraintDiv( String p_restraintDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    restraint_div = p_restraintDiv;
    restraint_div_is_set = true;
    restraint_div_is_modified = true;
  }


  /** 
   * <em>amount</em>カラムの値を設定します。 
   *
   * @@param p_amount <em>amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAmount( double p_amount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount = p_amount;
    amount_is_set = true;
    amount_is_modified = true;
  }


  /** 
   * <em>transaction_date</em>カラムの値を設定します。 
   *
   * @@param p_transactionDate <em>transaction_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTransactionDate( java.sql.Timestamp p_transactionDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_date = p_transactionDate;
    transaction_date_is_set = true;
    transaction_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>transaction_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTransactionDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    transaction_date_is_set = true;
    transaction_date_is_modified = true;
  }


  /** 
   * <em>delivery_date</em>カラムの値を設定します。 
   *
   * @@param p_deliveryDate <em>delivery_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDeliveryDate( java.sql.Timestamp p_deliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = p_deliveryDate;
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>delivery_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


  /** 
   * <em>delete_key1</em>カラムの値を設定します。 
   *
   * @@param p_deleteKey1 <em>delete_key1</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setDeleteKey1( String p_deleteKey1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_key1 = p_deleteKey1;
    delete_key1_is_set = true;
    delete_key1_is_modified = true;
  }


  /** 
   * <em>delete_key2</em>カラムの値を設定します。 
   *
   * @@param p_deleteKey2 <em>delete_key2</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setDeleteKey2( String p_deleteKey2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_key2 = p_deleteKey2;
    delete_key2_is_set = true;
    delete_key2_is_modified = true;
  }


  /** 
   * <em>delete_flag</em>カラムの値を設定します。 
   *
   * @@param p_deleteFlag <em>delete_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setDeleteFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = p_deleteFlag;
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
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
                else if ( name.equals("amount") ) {
                    return new Double( amount );
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                else if ( name.equals("delete_key1") ) {
                    return this.delete_key1;
                }
                else if ( name.equals("delete_key2") ) {
                    return this.delete_key2;
                }
                else if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'r':
                if ( name.equals("restraint_div") ) {
                    return this.restraint_div;
                }
                break;
            case 't':
                if ( name.equals("transaction_date") ) {
                    return this.transaction_date;
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
                else if ( name.equals("amount") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'amount' must be Double: '"+value+"' is not." );
                    this.amount = ((Double) value).doubleValue();
                    if (this.amount_is_set)
                        this.amount_is_modified = true;
                    this.amount_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
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
                if ( name.equals("delivery_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                else if ( name.equals("delete_key1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delete_key1' must be String: '"+value+"' is not." );
                    this.delete_key1 = (String) value;
                    if (this.delete_key1_is_set)
                        this.delete_key1_is_modified = true;
                    this.delete_key1_is_set = true;
                    return;
                }
                else if ( name.equals("delete_key2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delete_key2' must be String: '"+value+"' is not." );
                    this.delete_key2 = (String) value;
                    if (this.delete_key2_is_set)
                        this.delete_key2_is_modified = true;
                    this.delete_key2_is_set = true;
                    return;
                }
                else if ( name.equals("delete_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.delete_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
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
            case 'r':
                if ( name.equals("restraint_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'restraint_div' must be String: '"+value+"' is not." );
                    this.restraint_div = (String) value;
                    if (this.restraint_div_is_set)
                        this.restraint_div_is_modified = true;
                    this.restraint_div_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("transaction_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'transaction_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.transaction_date = (java.sql.Timestamp) value;
                    if (this.transaction_date_is_set)
                        this.transaction_date_is_modified = true;
                    this.transaction_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
