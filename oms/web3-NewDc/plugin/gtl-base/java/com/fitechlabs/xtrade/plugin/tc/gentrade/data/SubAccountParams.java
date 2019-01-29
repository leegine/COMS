head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.38.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	SubAccountParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * sub_accountテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SubAccountRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SubAccountParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SubAccountParams}が{@@link SubAccountRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SubAccountPK 
 * @@see SubAccountRow 
 */
public class SubAccountParams extends Params implements SubAccountRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "sub_account";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SubAccountRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SubAccountRow.TYPE;
  }


  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>カラムの値 
   */
  public  long  sub_account_id;

  /** 
   * <em>sub_account_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum  sub_account_type;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>institution_id</em>カラムの値 
   */
  public  long  institution_id;

  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  Long  branch_id;

  /** 
   * <em>sub_account_status</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum  sub_account_status;

  /** 
   * <em>open_date</em>カラムの値 
   */
  public  java.sql.Timestamp  open_date;

  /** 
   * <em>close_date</em>カラムの値 
   */
  public  java.sql.Timestamp  close_date;

  /** 
   * <em>cash_balance</em>カラムの値 
   */
  public  Double  cash_balance;

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
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean sub_account_type_is_set = false;
  private boolean sub_account_type_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean institution_id_is_set = false;
  private boolean institution_id_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean sub_account_status_is_set = false;
  private boolean sub_account_status_is_modified = false;
  private boolean open_date_is_set = false;
  private boolean open_date_is_modified = false;
  private boolean close_date_is_set = false;
  private boolean close_date_is_modified = false;
  private boolean cash_balance_is_set = false;
  private boolean cash_balance_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "account_id=" + account_id
      + "," + "sub_account_id=" + sub_account_id
      + "," + "sub_account_type=" +sub_account_type
      + "," + "institution_code=" +institution_code
      + "," + "institution_id=" +institution_id
      + "," + "branch_id=" +branch_id
      + "," + "sub_account_status=" +sub_account_status
      + "," + "open_date=" +open_date
      + "," + "close_date=" +close_date
      + "," + "cash_balance=" +cash_balance
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のSubAccountParamsオブジェクトを作成します。 
   */
  public SubAccountParams() {
    account_id_is_modified = true;
    sub_account_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSubAccountRowオブジェクトの値を利用してSubAccountParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSubAccountRowオブジェクト 
   */
  public SubAccountParams( SubAccountRow p_row )
  {
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    sub_account_type = p_row.getSubAccountType();
    sub_account_type_is_set = p_row.getSubAccountTypeIsSet();
    sub_account_type_is_modified = p_row.getSubAccountTypeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    institution_id = p_row.getInstitutionId();
    institution_id_is_set = p_row.getInstitutionIdIsSet();
    institution_id_is_modified = p_row.getInstitutionIdIsModified();
    if ( !p_row.getBranchIdIsNull() )
      branch_id = new Long( p_row.getBranchId() );
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    sub_account_status = p_row.getSubAccountStatus();
    sub_account_status_is_set = p_row.getSubAccountStatusIsSet();
    sub_account_status_is_modified = p_row.getSubAccountStatusIsModified();
    open_date = p_row.getOpenDate();
    open_date_is_set = p_row.getOpenDateIsSet();
    open_date_is_modified = p_row.getOpenDateIsModified();
    close_date = p_row.getCloseDate();
    close_date_is_set = p_row.getCloseDateIsSet();
    close_date_is_modified = p_row.getCloseDateIsModified();
    if ( !p_row.getCashBalanceIsNull() )
      cash_balance = new Double( p_row.getCashBalance() );
    cash_balance_is_set = p_row.getCashBalanceIsSet();
    cash_balance_is_modified = p_row.getCashBalanceIsModified();
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
      sub_account_type_is_set = true;
      sub_account_type_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      institution_id_is_set = true;
      institution_id_is_modified = true;
      branch_id_is_set = true;
      branch_id_is_modified = true;
      sub_account_status_is_set = true;
      sub_account_status_is_modified = true;
      open_date_is_set = true;
      open_date_is_modified = true;
      close_date_is_set = true;
      close_date_is_modified = true;
      cash_balance_is_set = true;
      cash_balance_is_modified = true;
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
    if ( !( other instanceof SubAccountRow ) )
       return false;
    return fieldsEqual( (SubAccountRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSubAccountRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SubAccountRow row )
  {
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( sub_account_type == null ) {
      if ( row.getSubAccountType() != null )
        return false;
    } else if ( !sub_account_type.equals( row.getSubAccountType() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( institution_id != row.getInstitutionId() )
      return false;
    if ( branch_id == null ) {
      if ( !row.getBranchIdIsNull() )
        return false;
    } else if ( row.getBranchIdIsNull() || ( branch_id.longValue() != row.getBranchId() ) ) {
        return false;
    }
    if ( sub_account_status == null ) {
      if ( row.getSubAccountStatus() != null )
        return false;
    } else if ( !sub_account_status.equals( row.getSubAccountStatus() ) ) {
        return false;
    }
    if ( open_date == null ) {
      if ( row.getOpenDate() != null )
        return false;
    } else if ( !open_date.equals( row.getOpenDate() ) ) {
        return false;
    }
    if ( close_date == null ) {
      if ( row.getCloseDate() != null )
        return false;
    } else if ( !close_date.equals( row.getCloseDate() ) ) {
        return false;
    }
    if ( cash_balance == null ) {
      if ( !row.getCashBalanceIsNull() )
        return false;
    } else if ( row.getCashBalanceIsNull() || ( cash_balance.doubleValue() != row.getCashBalance() ) ) {
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
        + ((int) sub_account_id)
        + (sub_account_type!=null? sub_account_type.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) institution_id)
        + (branch_id!=null? branch_id.hashCode(): 0) 
        + (sub_account_status!=null? sub_account_status.hashCode(): 0) 
        + (open_date!=null? open_date.hashCode(): 0) 
        + (close_date!=null? close_date.hashCode(): 0) 
        + (cash_balance!=null? cash_balance.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !sub_account_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'sub_account_type' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !institution_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_id' must be set before inserting.");
		if ( !sub_account_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'sub_account_status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("sub_account_type",sub_account_type);
		map.put("institution_code",institution_code);
		map.put("institution_id",new Long(institution_id));
		if ( branch_id != null )
			map.put("branch_id",branch_id);
		map.put("sub_account_status",sub_account_status);
		if ( open_date != null )
			map.put("open_date",open_date);
		if ( close_date != null )
			map.put("close_date",close_date);
		if ( cash_balance != null )
			map.put("cash_balance",cash_balance);
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
		if ( sub_account_type_is_modified )
			map.put("sub_account_type",sub_account_type);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( institution_id_is_modified )
			map.put("institution_id",new Long(institution_id));
		if ( branch_id_is_modified )
			map.put("branch_id",branch_id);
		if ( sub_account_status_is_modified )
			map.put("sub_account_status",sub_account_status);
		if ( open_date_is_modified )
			map.put("open_date",open_date);
		if ( close_date_is_modified )
			map.put("close_date",close_date);
		if ( cash_balance_is_modified )
			map.put("cash_balance",cash_balance);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( sub_account_type_is_set )
				map.put("sub_account_type",sub_account_type);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( institution_id_is_set )
				map.put("institution_id",new Long(institution_id));
			map.put("branch_id",branch_id);
			if ( sub_account_status_is_set )
				map.put("sub_account_status",sub_account_status);
			map.put("open_date",open_date);
			map.put("close_date",close_date);
			map.put("cash_balance",cash_balance);
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
   * <em>sub_account_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum getSubAccountType()
  {
    return sub_account_type;
  }


  /** 
   * <em>sub_account_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccountTypeIsSet() {
    return sub_account_type_is_set;
  }


  /** 
   * <em>sub_account_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccountTypeIsModified() {
    return sub_account_type_is_modified;
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
   * <em>institution_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getInstitutionId()
  {
    return institution_id;
  }


  /** 
   * <em>institution_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionIdIsSet() {
    return institution_id_is_set;
  }


  /** 
   * <em>institution_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionIdIsModified() {
    return institution_id_is_modified;
  }


  /** 
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBranchId()
  {
    return ( branch_id==null? ((long)0): branch_id.longValue() );
  }


  /** 
   * <em>branch_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBranchIdIsNull()
  {
    return branch_id == null;
  }


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
  }


  /** 
   * <em>sub_account_status</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum getSubAccountStatus()
  {
    return sub_account_status;
  }


  /** 
   * <em>sub_account_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccountStatusIsSet() {
    return sub_account_status_is_set;
  }


  /** 
   * <em>sub_account_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccountStatusIsModified() {
    return sub_account_status_is_modified;
  }


  /** 
   * <em>open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOpenDate()
  {
    return open_date;
  }


  /** 
   * <em>open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenDateIsSet() {
    return open_date_is_set;
  }


  /** 
   * <em>open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenDateIsModified() {
    return open_date_is_modified;
  }


  /** 
   * <em>close_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCloseDate()
  {
    return close_date;
  }


  /** 
   * <em>close_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseDateIsSet() {
    return close_date_is_set;
  }


  /** 
   * <em>close_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseDateIsModified() {
    return close_date_is_modified;
  }


  /** 
   * <em>cash_balance</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashBalance()
  {
    return ( cash_balance==null? ((double)0): cash_balance.doubleValue() );
  }


  /** 
   * <em>cash_balance</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashBalanceIsNull()
  {
    return cash_balance == null;
  }


  /** 
   * <em>cash_balance</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalanceIsSet() {
    return cash_balance_is_set;
  }


  /** 
   * <em>cash_balance</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalanceIsModified() {
    return cash_balance_is_modified;
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
    return new SubAccountPK(account_id, sub_account_id);
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
   * <em>sub_account_type</em>カラムの値を設定します。 
   *
   * @@param p_subAccountType <em>sub_account_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum値 
   */
  public final void setSubAccountType( com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum p_subAccountType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_account_type = p_subAccountType;
    sub_account_type_is_set = true;
    sub_account_type_is_modified = true;
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
   * <em>institution_id</em>カラムの値を設定します。 
   *
   * @@param p_institutionId <em>institution_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setInstitutionId( long p_institutionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_id = p_institutionId;
    institution_id_is_set = true;
    institution_id_is_modified = true;
  }


  /** 
   * <em>branch_id</em>カラムの値を設定します。 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = new Long(p_branchId);
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>branch_id</em>カラムに値を設定します。 
   */
  public final void setBranchId( Long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>sub_account_status</em>カラムの値を設定します。 
   *
   * @@param p_subAccountStatus <em>sub_account_status</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum値 
   */
  public final void setSubAccountStatus( com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum p_subAccountStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_account_status = p_subAccountStatus;
    sub_account_status_is_set = true;
    sub_account_status_is_modified = true;
  }


  /** 
   * <em>open_date</em>カラムの値を設定します。 
   *
   * @@param p_openDate <em>open_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOpenDate( java.sql.Timestamp p_openDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_date = p_openDate;
    open_date_is_set = true;
    open_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>open_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOpenDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    open_date_is_set = true;
    open_date_is_modified = true;
  }


  /** 
   * <em>close_date</em>カラムの値を設定します。 
   *
   * @@param p_closeDate <em>close_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCloseDate( java.sql.Timestamp p_closeDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_date = p_closeDate;
    close_date_is_set = true;
    close_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>close_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCloseDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    close_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    close_date_is_set = true;
    close_date_is_modified = true;
  }


  /** 
   * <em>cash_balance</em>カラムの値を設定します。 
   *
   * @@param p_cashBalance <em>cash_balance</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashBalance( double p_cashBalance )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance = new Double(p_cashBalance);
    cash_balance_is_set = true;
    cash_balance_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_balance</em>カラムに値を設定します。 
   */
  public final void setCashBalance( Double p_cashBalance )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance = p_cashBalance;
    cash_balance_is_set = true;
    cash_balance_is_modified = true;
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
            case 'b':
                if ( name.equals("branch_id") ) {
                    return this.branch_id;
                }
                break;
            case 'c':
                if ( name.equals("close_date") ) {
                    return this.close_date;
                }
                else if ( name.equals("cash_balance") ) {
                    return this.cash_balance;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("institution_id") ) {
                    return new Long( institution_id );
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("open_date") ) {
                    return this.open_date;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("sub_account_type") ) {
                    return this.sub_account_type;
                }
                else if ( name.equals("sub_account_status") ) {
                    return this.sub_account_status;
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
            case 'b':
                if ( name.equals("branch_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = (Long) value;
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("close_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'close_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.close_date = (java.sql.Timestamp) value;
                    if (this.close_date_is_set)
                        this.close_date_is_modified = true;
                    this.close_date_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance' must be Double: '"+value+"' is not." );
                    this.cash_balance = (Double) value;
                    if (this.cash_balance_is_set)
                        this.cash_balance_is_modified = true;
                    this.cash_balance_is_set = true;
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
                else if ( name.equals("institution_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'institution_id' must be Long: '"+value+"' is not." );
                    this.institution_id = ((Long) value).longValue();
                    if (this.institution_id_is_set)
                        this.institution_id_is_modified = true;
                    this.institution_id_is_set = true;
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
            case 'o':
                if ( name.equals("open_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'open_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.open_date = (java.sql.Timestamp) value;
                    if (this.open_date_is_set)
                        this.open_date_is_modified = true;
                    this.open_date_is_set = true;
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
                else if ( name.equals("sub_account_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum) )
                        throw new IllegalArgumentException( "value for 'sub_account_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum: '"+value+"' is not." );
                    this.sub_account_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum) value;
                    if (this.sub_account_type_is_set)
                        this.sub_account_type_is_modified = true;
                    this.sub_account_type_is_set = true;
                    return;
                }
                else if ( name.equals("sub_account_status") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum) )
                        throw new IllegalArgumentException( "value for 'sub_account_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum: '"+value+"' is not." );
                    this.sub_account_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum) value;
                    if (this.sub_account_status_is_set)
                        this.sub_account_status_is_modified = true;
                    this.sub_account_status_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
