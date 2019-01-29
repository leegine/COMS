head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AccountTransferParams.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * account_transferテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AccountTransferRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AccountTransferParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AccountTransferParams}が{@@link AccountTransferRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccountTransferPK 
 * @@see AccountTransferRow 
 */
public class AccountTransferParams extends Params implements AccountTransferRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "account_transfer";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AccountTransferRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AccountTransferRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>work_day</em>カラムの値 
   */
  public  java.sql.Timestamp  work_day;

  /** 
   * <em>branch_code_old</em>カラムの値 
   */
  public  String  branch_code_old;

  /** 
   * <em>account_code_old</em>カラムの値 
   */
  public  String  account_code_old;

  /** 
   * <em>transfer_tbl</em>カラムの値 
   */
  public  String  transfer_tbl;

  /** 
   * <em>rec_div</em>カラムの値 
   */
  public  String  rec_div;

  /** 
   * <em>sonar_trader_code_old</em>カラムの値 
   */
  public  String  sonar_trader_code_old;

  /** 
   * <em>branch_code_new</em>カラムの値 
   */
  public  String  branch_code_new;

  /** 
   * <em>account_code_new</em>カラムの値 
   */
  public  String  account_code_new;

  /** 
   * <em>sonar_trader_code_new</em>カラムの値 
   */
  public  String  sonar_trader_code_new;

  /** 
   * <em>customer_trader_code_old</em>カラムの値 
   */
  public  String  customer_trader_code_old;

  /** 
   * <em>customer_trader_code_new</em>カラムの値 
   */
  public  String  customer_trader_code_new;

  /** 
   * <em>del_div</em>カラムの値 
   */
  public  String  del_div;

  /** 
   * <em>inherit_div</em>カラムの値 
   */
  public  String  inherit_div;

  /** 
   * <em>transfer_div</em>カラムの値 
   */
  public  String  transfer_div;

  /** 
   * <em>pro_div</em>カラムの値 
   */
  public  String  pro_div;

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
  private boolean work_day_is_set = false;
  private boolean work_day_is_modified = false;
  private boolean rec_div_is_set = false;
  private boolean rec_div_is_modified = false;
  private boolean branch_code_old_is_set = false;
  private boolean branch_code_old_is_modified = false;
  private boolean account_code_old_is_set = false;
  private boolean account_code_old_is_modified = false;
  private boolean sonar_trader_code_old_is_set = false;
  private boolean sonar_trader_code_old_is_modified = false;
  private boolean branch_code_new_is_set = false;
  private boolean branch_code_new_is_modified = false;
  private boolean account_code_new_is_set = false;
  private boolean account_code_new_is_modified = false;
  private boolean sonar_trader_code_new_is_set = false;
  private boolean sonar_trader_code_new_is_modified = false;
  private boolean customer_trader_code_old_is_set = false;
  private boolean customer_trader_code_old_is_modified = false;
  private boolean customer_trader_code_new_is_set = false;
  private boolean customer_trader_code_new_is_modified = false;
  private boolean del_div_is_set = false;
  private boolean del_div_is_modified = false;
  private boolean inherit_div_is_set = false;
  private boolean inherit_div_is_modified = false;
  private boolean transfer_tbl_is_set = false;
  private boolean transfer_tbl_is_modified = false;
  private boolean transfer_div_is_set = false;
  private boolean transfer_div_is_modified = false;
  private boolean pro_div_is_set = false;
  private boolean pro_div_is_modified = false;
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
      + "," + "work_day=" + work_day
      + "," + "branch_code_old=" + branch_code_old
      + "," + "account_code_old=" + account_code_old
      + "," + "transfer_tbl=" + transfer_tbl
      + "," + "rec_div=" +rec_div
      + "," + "sonar_trader_code_old=" +sonar_trader_code_old
      + "," + "branch_code_new=" +branch_code_new
      + "," + "account_code_new=" +account_code_new
      + "," + "sonar_trader_code_new=" +sonar_trader_code_new
      + "," + "customer_trader_code_old=" +customer_trader_code_old
      + "," + "customer_trader_code_new=" +customer_trader_code_new
      + "," + "del_div=" +del_div
      + "," + "inherit_div=" +inherit_div
      + "," + "transfer_div=" +transfer_div
      + "," + "pro_div=" +pro_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のAccountTransferParamsオブジェクトを作成します。 
   */
  public AccountTransferParams() {
    institution_code_is_modified = true;
    work_day_is_modified = true;
    branch_code_old_is_modified = true;
    account_code_old_is_modified = true;
    transfer_tbl_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAccountTransferRowオブジェクトの値を利用してAccountTransferParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAccountTransferRowオブジェクト 
   */
  public AccountTransferParams( AccountTransferRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    work_day = p_row.getWorkDay();
    work_day_is_set = p_row.getWorkDayIsSet();
    work_day_is_modified = p_row.getWorkDayIsModified();
    branch_code_old = p_row.getBranchCodeOld();
    branch_code_old_is_set = p_row.getBranchCodeOldIsSet();
    branch_code_old_is_modified = p_row.getBranchCodeOldIsModified();
    account_code_old = p_row.getAccountCodeOld();
    account_code_old_is_set = p_row.getAccountCodeOldIsSet();
    account_code_old_is_modified = p_row.getAccountCodeOldIsModified();
    transfer_tbl = p_row.getTransferTbl();
    transfer_tbl_is_set = p_row.getTransferTblIsSet();
    transfer_tbl_is_modified = p_row.getTransferTblIsModified();
    rec_div = p_row.getRecDiv();
    rec_div_is_set = p_row.getRecDivIsSet();
    rec_div_is_modified = p_row.getRecDivIsModified();
    sonar_trader_code_old = p_row.getSonarTraderCodeOld();
    sonar_trader_code_old_is_set = p_row.getSonarTraderCodeOldIsSet();
    sonar_trader_code_old_is_modified = p_row.getSonarTraderCodeOldIsModified();
    branch_code_new = p_row.getBranchCodeNew();
    branch_code_new_is_set = p_row.getBranchCodeNewIsSet();
    branch_code_new_is_modified = p_row.getBranchCodeNewIsModified();
    account_code_new = p_row.getAccountCodeNew();
    account_code_new_is_set = p_row.getAccountCodeNewIsSet();
    account_code_new_is_modified = p_row.getAccountCodeNewIsModified();
    sonar_trader_code_new = p_row.getSonarTraderCodeNew();
    sonar_trader_code_new_is_set = p_row.getSonarTraderCodeNewIsSet();
    sonar_trader_code_new_is_modified = p_row.getSonarTraderCodeNewIsModified();
    customer_trader_code_old = p_row.getCustomerTraderCodeOld();
    customer_trader_code_old_is_set = p_row.getCustomerTraderCodeOldIsSet();
    customer_trader_code_old_is_modified = p_row.getCustomerTraderCodeOldIsModified();
    customer_trader_code_new = p_row.getCustomerTraderCodeNew();
    customer_trader_code_new_is_set = p_row.getCustomerTraderCodeNewIsSet();
    customer_trader_code_new_is_modified = p_row.getCustomerTraderCodeNewIsModified();
    del_div = p_row.getDelDiv();
    del_div_is_set = p_row.getDelDivIsSet();
    del_div_is_modified = p_row.getDelDivIsModified();
    inherit_div = p_row.getInheritDiv();
    inherit_div_is_set = p_row.getInheritDivIsSet();
    inherit_div_is_modified = p_row.getInheritDivIsModified();
    transfer_div = p_row.getTransferDiv();
    transfer_div_is_set = p_row.getTransferDivIsSet();
    transfer_div_is_modified = p_row.getTransferDivIsModified();
    pro_div = p_row.getProDiv();
    pro_div_is_set = p_row.getProDivIsSet();
    pro_div_is_modified = p_row.getProDivIsModified();
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
      rec_div_is_set = true;
      rec_div_is_modified = true;
      sonar_trader_code_old_is_set = true;
      sonar_trader_code_old_is_modified = true;
      branch_code_new_is_set = true;
      branch_code_new_is_modified = true;
      account_code_new_is_set = true;
      account_code_new_is_modified = true;
      sonar_trader_code_new_is_set = true;
      sonar_trader_code_new_is_modified = true;
      customer_trader_code_old_is_set = true;
      customer_trader_code_old_is_modified = true;
      customer_trader_code_new_is_set = true;
      customer_trader_code_new_is_modified = true;
      del_div_is_set = true;
      del_div_is_modified = true;
      inherit_div_is_set = true;
      inherit_div_is_modified = true;
      transfer_div_is_set = true;
      transfer_div_is_modified = true;
      pro_div_is_set = true;
      pro_div_is_modified = true;
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
    if ( !( other instanceof AccountTransferRow ) )
       return false;
    return fieldsEqual( (AccountTransferRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAccountTransferRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AccountTransferRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( work_day == null ) {
      if ( row.getWorkDay() != null )
        return false;
    } else if ( !work_day.equals( row.getWorkDay() ) ) {
        return false;
    }
    if ( rec_div == null ) {
      if ( row.getRecDiv() != null )
        return false;
    } else if ( !rec_div.equals( row.getRecDiv() ) ) {
        return false;
    }
    if ( branch_code_old == null ) {
      if ( row.getBranchCodeOld() != null )
        return false;
    } else if ( !branch_code_old.equals( row.getBranchCodeOld() ) ) {
        return false;
    }
    if ( account_code_old == null ) {
      if ( row.getAccountCodeOld() != null )
        return false;
    } else if ( !account_code_old.equals( row.getAccountCodeOld() ) ) {
        return false;
    }
    if ( sonar_trader_code_old == null ) {
      if ( row.getSonarTraderCodeOld() != null )
        return false;
    } else if ( !sonar_trader_code_old.equals( row.getSonarTraderCodeOld() ) ) {
        return false;
    }
    if ( branch_code_new == null ) {
      if ( row.getBranchCodeNew() != null )
        return false;
    } else if ( !branch_code_new.equals( row.getBranchCodeNew() ) ) {
        return false;
    }
    if ( account_code_new == null ) {
      if ( row.getAccountCodeNew() != null )
        return false;
    } else if ( !account_code_new.equals( row.getAccountCodeNew() ) ) {
        return false;
    }
    if ( sonar_trader_code_new == null ) {
      if ( row.getSonarTraderCodeNew() != null )
        return false;
    } else if ( !sonar_trader_code_new.equals( row.getSonarTraderCodeNew() ) ) {
        return false;
    }
    if ( customer_trader_code_old == null ) {
      if ( row.getCustomerTraderCodeOld() != null )
        return false;
    } else if ( !customer_trader_code_old.equals( row.getCustomerTraderCodeOld() ) ) {
        return false;
    }
    if ( customer_trader_code_new == null ) {
      if ( row.getCustomerTraderCodeNew() != null )
        return false;
    } else if ( !customer_trader_code_new.equals( row.getCustomerTraderCodeNew() ) ) {
        return false;
    }
    if ( del_div == null ) {
      if ( row.getDelDiv() != null )
        return false;
    } else if ( !del_div.equals( row.getDelDiv() ) ) {
        return false;
    }
    if ( inherit_div == null ) {
      if ( row.getInheritDiv() != null )
        return false;
    } else if ( !inherit_div.equals( row.getInheritDiv() ) ) {
        return false;
    }
    if ( transfer_tbl == null ) {
      if ( row.getTransferTbl() != null )
        return false;
    } else if ( !transfer_tbl.equals( row.getTransferTbl() ) ) {
        return false;
    }
    if ( transfer_div == null ) {
      if ( row.getTransferDiv() != null )
        return false;
    } else if ( !transfer_div.equals( row.getTransferDiv() ) ) {
        return false;
    }
    if ( pro_div == null ) {
      if ( row.getProDiv() != null )
        return false;
    } else if ( !pro_div.equals( row.getProDiv() ) ) {
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
        + (work_day!=null? work_day.hashCode(): 0) 
        + (rec_div!=null? rec_div.hashCode(): 0) 
        + (branch_code_old!=null? branch_code_old.hashCode(): 0) 
        + (account_code_old!=null? account_code_old.hashCode(): 0) 
        + (sonar_trader_code_old!=null? sonar_trader_code_old.hashCode(): 0) 
        + (branch_code_new!=null? branch_code_new.hashCode(): 0) 
        + (account_code_new!=null? account_code_new.hashCode(): 0) 
        + (sonar_trader_code_new!=null? sonar_trader_code_new.hashCode(): 0) 
        + (customer_trader_code_old!=null? customer_trader_code_old.hashCode(): 0) 
        + (customer_trader_code_new!=null? customer_trader_code_new.hashCode(): 0) 
        + (del_div!=null? del_div.hashCode(): 0) 
        + (inherit_div!=null? inherit_div.hashCode(): 0) 
        + (transfer_tbl!=null? transfer_tbl.hashCode(): 0) 
        + (transfer_div!=null? transfer_div.hashCode(): 0) 
        + (pro_div!=null? pro_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !sonar_trader_code_old_is_set )
			throw new IllegalArgumentException("non-nullable field 'sonar_trader_code_old' must be set before inserting.");
		if ( !branch_code_new_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code_new' must be set before inserting.");
		if ( !account_code_new_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code_new' must be set before inserting.");
		if ( !sonar_trader_code_new_is_set )
			throw new IllegalArgumentException("non-nullable field 'sonar_trader_code_new' must be set before inserting.");
		if ( !inherit_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'inherit_div' must be set before inserting.");
		if ( !transfer_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'transfer_div' must be set before inserting.");
		if ( !pro_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'pro_div' must be set before inserting.");
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
		map.put("institution_code",institution_code);
		map.put("work_day",work_day);
		if ( rec_div != null )
			map.put("rec_div",rec_div);
		map.put("branch_code_old",branch_code_old);
		map.put("account_code_old",account_code_old);
		map.put("sonar_trader_code_old",sonar_trader_code_old);
		map.put("branch_code_new",branch_code_new);
		map.put("account_code_new",account_code_new);
		map.put("sonar_trader_code_new",sonar_trader_code_new);
		if ( customer_trader_code_old != null )
			map.put("customer_trader_code_old",customer_trader_code_old);
		if ( customer_trader_code_new != null )
			map.put("customer_trader_code_new",customer_trader_code_new);
		if ( del_div != null )
			map.put("del_div",del_div);
		map.put("inherit_div",inherit_div);
		map.put("transfer_tbl",transfer_tbl);
		map.put("transfer_div",transfer_div);
		map.put("pro_div",pro_div);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( rec_div_is_modified )
			map.put("rec_div",rec_div);
		if ( sonar_trader_code_old_is_modified )
			map.put("sonar_trader_code_old",sonar_trader_code_old);
		if ( branch_code_new_is_modified )
			map.put("branch_code_new",branch_code_new);
		if ( account_code_new_is_modified )
			map.put("account_code_new",account_code_new);
		if ( sonar_trader_code_new_is_modified )
			map.put("sonar_trader_code_new",sonar_trader_code_new);
		if ( customer_trader_code_old_is_modified )
			map.put("customer_trader_code_old",customer_trader_code_old);
		if ( customer_trader_code_new_is_modified )
			map.put("customer_trader_code_new",customer_trader_code_new);
		if ( del_div_is_modified )
			map.put("del_div",del_div);
		if ( inherit_div_is_modified )
			map.put("inherit_div",inherit_div);
		if ( transfer_div_is_modified )
			map.put("transfer_div",transfer_div);
		if ( pro_div_is_modified )
			map.put("pro_div",pro_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("rec_div",rec_div);
			if ( sonar_trader_code_old_is_set )
				map.put("sonar_trader_code_old",sonar_trader_code_old);
			if ( branch_code_new_is_set )
				map.put("branch_code_new",branch_code_new);
			if ( account_code_new_is_set )
				map.put("account_code_new",account_code_new);
			if ( sonar_trader_code_new_is_set )
				map.put("sonar_trader_code_new",sonar_trader_code_new);
			map.put("customer_trader_code_old",customer_trader_code_old);
			map.put("customer_trader_code_new",customer_trader_code_new);
			map.put("del_div",del_div);
			if ( inherit_div_is_set )
				map.put("inherit_div",inherit_div);
			if ( transfer_div_is_set )
				map.put("transfer_div",transfer_div);
			if ( pro_div_is_set )
				map.put("pro_div",pro_div);
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
   * <em>work_day</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getWorkDay()
  {
    return work_day;
  }


  /** 
   * <em>work_day</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWorkDayIsSet() {
    return work_day_is_set;
  }


  /** 
   * <em>work_day</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWorkDayIsModified() {
    return work_day_is_modified;
  }


  /** 
   * <em>rec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRecDiv()
  {
    return rec_div;
  }


  /** 
   * <em>rec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecDivIsSet() {
    return rec_div_is_set;
  }


  /** 
   * <em>rec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecDivIsModified() {
    return rec_div_is_modified;
  }


  /** 
   * <em>branch_code_old</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCodeOld()
  {
    return branch_code_old;
  }


  /** 
   * <em>branch_code_old</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeOldIsSet() {
    return branch_code_old_is_set;
  }


  /** 
   * <em>branch_code_old</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeOldIsModified() {
    return branch_code_old_is_modified;
  }


  /** 
   * <em>account_code_old</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCodeOld()
  {
    return account_code_old;
  }


  /** 
   * <em>account_code_old</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeOldIsSet() {
    return account_code_old_is_set;
  }


  /** 
   * <em>account_code_old</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeOldIsModified() {
    return account_code_old_is_modified;
  }


  /** 
   * <em>sonar_trader_code_old</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarTraderCodeOld()
  {
    return sonar_trader_code_old;
  }


  /** 
   * <em>sonar_trader_code_old</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeOldIsSet() {
    return sonar_trader_code_old_is_set;
  }


  /** 
   * <em>sonar_trader_code_old</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeOldIsModified() {
    return sonar_trader_code_old_is_modified;
  }


  /** 
   * <em>branch_code_new</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCodeNew()
  {
    return branch_code_new;
  }


  /** 
   * <em>branch_code_new</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeNewIsSet() {
    return branch_code_new_is_set;
  }


  /** 
   * <em>branch_code_new</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeNewIsModified() {
    return branch_code_new_is_modified;
  }


  /** 
   * <em>account_code_new</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCodeNew()
  {
    return account_code_new;
  }


  /** 
   * <em>account_code_new</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeNewIsSet() {
    return account_code_new_is_set;
  }


  /** 
   * <em>account_code_new</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeNewIsModified() {
    return account_code_new_is_modified;
  }


  /** 
   * <em>sonar_trader_code_new</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarTraderCodeNew()
  {
    return sonar_trader_code_new;
  }


  /** 
   * <em>sonar_trader_code_new</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeNewIsSet() {
    return sonar_trader_code_new_is_set;
  }


  /** 
   * <em>sonar_trader_code_new</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeNewIsModified() {
    return sonar_trader_code_new_is_modified;
  }


  /** 
   * <em>customer_trader_code_old</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCustomerTraderCodeOld()
  {
    return customer_trader_code_old;
  }


  /** 
   * <em>customer_trader_code_old</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustomerTraderCodeOldIsSet() {
    return customer_trader_code_old_is_set;
  }


  /** 
   * <em>customer_trader_code_old</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustomerTraderCodeOldIsModified() {
    return customer_trader_code_old_is_modified;
  }


  /** 
   * <em>customer_trader_code_new</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCustomerTraderCodeNew()
  {
    return customer_trader_code_new;
  }


  /** 
   * <em>customer_trader_code_new</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustomerTraderCodeNewIsSet() {
    return customer_trader_code_new_is_set;
  }


  /** 
   * <em>customer_trader_code_new</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustomerTraderCodeNewIsModified() {
    return customer_trader_code_new_is_modified;
  }


  /** 
   * <em>del_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDelDiv()
  {
    return del_div;
  }


  /** 
   * <em>del_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDelDivIsSet() {
    return del_div_is_set;
  }


  /** 
   * <em>del_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDelDivIsModified() {
    return del_div_is_modified;
  }


  /** 
   * <em>inherit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInheritDiv()
  {
    return inherit_div;
  }


  /** 
   * <em>inherit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInheritDivIsSet() {
    return inherit_div_is_set;
  }


  /** 
   * <em>inherit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInheritDivIsModified() {
    return inherit_div_is_modified;
  }


  /** 
   * <em>transfer_tbl</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransferTbl()
  {
    return transfer_tbl;
  }


  /** 
   * <em>transfer_tbl</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferTblIsSet() {
    return transfer_tbl_is_set;
  }


  /** 
   * <em>transfer_tbl</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferTblIsModified() {
    return transfer_tbl_is_modified;
  }


  /** 
   * <em>transfer_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransferDiv()
  {
    return transfer_div;
  }


  /** 
   * <em>transfer_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDivIsSet() {
    return transfer_div_is_set;
  }


  /** 
   * <em>transfer_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDivIsModified() {
    return transfer_div_is_modified;
  }


  /** 
   * <em>pro_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProDiv()
  {
    return pro_div;
  }


  /** 
   * <em>pro_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProDivIsSet() {
    return pro_div_is_set;
  }


  /** 
   * <em>pro_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProDivIsModified() {
    return pro_div_is_modified;
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
    return new AccountTransferPK(institution_code, work_day, branch_code_old, account_code_old, transfer_tbl);
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>work_day</em>カラムの値を設定します。 
   *
   * @@param p_workDay <em>work_day</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setWorkDay( java.sql.Timestamp p_workDay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    work_day = p_workDay;
    work_day_is_set = true;
    work_day_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>work_day</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setWorkDay( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    work_day = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    work_day_is_set = true;
    work_day_is_modified = true;
  }


  /** 
   * <em>rec_div</em>カラムの値を設定します。 
   *
   * @@param p_recDiv <em>rec_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRecDiv( String p_recDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rec_div = p_recDiv;
    rec_div_is_set = true;
    rec_div_is_modified = true;
  }


  /** 
   * <em>branch_code_old</em>カラムの値を設定します。 
   *
   * @@param p_branchCodeOld <em>branch_code_old</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCodeOld( String p_branchCodeOld )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code_old = p_branchCodeOld;
    branch_code_old_is_set = true;
    branch_code_old_is_modified = true;
  }


  /** 
   * <em>account_code_old</em>カラムの値を設定します。 
   *
   * @@param p_accountCodeOld <em>account_code_old</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setAccountCodeOld( String p_accountCodeOld )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code_old = p_accountCodeOld;
    account_code_old_is_set = true;
    account_code_old_is_modified = true;
  }


  /** 
   * <em>sonar_trader_code_old</em>カラムの値を設定します。 
   *
   * @@param p_sonarTraderCodeOld <em>sonar_trader_code_old</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setSonarTraderCodeOld( String p_sonarTraderCodeOld )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_trader_code_old = p_sonarTraderCodeOld;
    sonar_trader_code_old_is_set = true;
    sonar_trader_code_old_is_modified = true;
  }


  /** 
   * <em>branch_code_new</em>カラムの値を設定します。 
   *
   * @@param p_branchCodeNew <em>branch_code_new</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCodeNew( String p_branchCodeNew )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code_new = p_branchCodeNew;
    branch_code_new_is_set = true;
    branch_code_new_is_modified = true;
  }


  /** 
   * <em>account_code_new</em>カラムの値を設定します。 
   *
   * @@param p_accountCodeNew <em>account_code_new</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setAccountCodeNew( String p_accountCodeNew )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code_new = p_accountCodeNew;
    account_code_new_is_set = true;
    account_code_new_is_modified = true;
  }


  /** 
   * <em>sonar_trader_code_new</em>カラムの値を設定します。 
   *
   * @@param p_sonarTraderCodeNew <em>sonar_trader_code_new</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setSonarTraderCodeNew( String p_sonarTraderCodeNew )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_trader_code_new = p_sonarTraderCodeNew;
    sonar_trader_code_new_is_set = true;
    sonar_trader_code_new_is_modified = true;
  }


  /** 
   * <em>customer_trader_code_old</em>カラムの値を設定します。 
   *
   * @@param p_customerTraderCodeOld <em>customer_trader_code_old</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setCustomerTraderCodeOld( String p_customerTraderCodeOld )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    customer_trader_code_old = p_customerTraderCodeOld;
    customer_trader_code_old_is_set = true;
    customer_trader_code_old_is_modified = true;
  }


  /** 
   * <em>customer_trader_code_new</em>カラムの値を設定します。 
   *
   * @@param p_customerTraderCodeNew <em>customer_trader_code_new</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setCustomerTraderCodeNew( String p_customerTraderCodeNew )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    customer_trader_code_new = p_customerTraderCodeNew;
    customer_trader_code_new_is_set = true;
    customer_trader_code_new_is_modified = true;
  }


  /** 
   * <em>del_div</em>カラムの値を設定します。 
   *
   * @@param p_delDiv <em>del_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDelDiv( String p_delDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    del_div = p_delDiv;
    del_div_is_set = true;
    del_div_is_modified = true;
  }


  /** 
   * <em>inherit_div</em>カラムの値を設定します。 
   *
   * @@param p_inheritDiv <em>inherit_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInheritDiv( String p_inheritDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    inherit_div = p_inheritDiv;
    inherit_div_is_set = true;
    inherit_div_is_modified = true;
  }


  /** 
   * <em>transfer_tbl</em>カラムの値を設定します。 
   *
   * @@param p_transferTbl <em>transfer_tbl</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setTransferTbl( String p_transferTbl )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_tbl = p_transferTbl;
    transfer_tbl_is_set = true;
    transfer_tbl_is_modified = true;
  }


  /** 
   * <em>transfer_div</em>カラムの値を設定します。 
   *
   * @@param p_transferDiv <em>transfer_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTransferDiv( String p_transferDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_div = p_transferDiv;
    transfer_div_is_set = true;
    transfer_div_is_modified = true;
  }


  /** 
   * <em>pro_div</em>カラムの値を設定します。 
   *
   * @@param p_proDiv <em>pro_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setProDiv( String p_proDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pro_div = p_proDiv;
    pro_div_is_set = true;
    pro_div_is_modified = true;
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
                if ( name.equals("account_code_old") ) {
                    return this.account_code_old;
                }
                else if ( name.equals("account_code_new") ) {
                    return this.account_code_new;
                }
                break;
            case 'b':
                if ( name.equals("branch_code_old") ) {
                    return this.branch_code_old;
                }
                else if ( name.equals("branch_code_new") ) {
                    return this.branch_code_new;
                }
                break;
            case 'c':
                if ( name.equals("customer_trader_code_old") ) {
                    return this.customer_trader_code_old;
                }
                else if ( name.equals("customer_trader_code_new") ) {
                    return this.customer_trader_code_new;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("del_div") ) {
                    return this.del_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("inherit_div") ) {
                    return this.inherit_div;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("pro_div") ) {
                    return this.pro_div;
                }
                break;
            case 'r':
                if ( name.equals("rec_div") ) {
                    return this.rec_div;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code_old") ) {
                    return this.sonar_trader_code_old;
                }
                else if ( name.equals("sonar_trader_code_new") ) {
                    return this.sonar_trader_code_new;
                }
                break;
            case 't':
                if ( name.equals("transfer_tbl") ) {
                    return this.transfer_tbl;
                }
                else if ( name.equals("transfer_div") ) {
                    return this.transfer_div;
                }
                break;
            case 'w':
                if ( name.equals("work_day") ) {
                    return this.work_day;
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
                if ( name.equals("account_code_old") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code_old' must be String: '"+value+"' is not." );
                    this.account_code_old = (String) value;
                    if (this.account_code_old_is_set)
                        this.account_code_old_is_modified = true;
                    this.account_code_old_is_set = true;
                    return;
                }
                else if ( name.equals("account_code_new") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code_new' must be String: '"+value+"' is not." );
                    this.account_code_new = (String) value;
                    if (this.account_code_new_is_set)
                        this.account_code_new_is_modified = true;
                    this.account_code_new_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code_old") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code_old' must be String: '"+value+"' is not." );
                    this.branch_code_old = (String) value;
                    if (this.branch_code_old_is_set)
                        this.branch_code_old_is_modified = true;
                    this.branch_code_old_is_set = true;
                    return;
                }
                else if ( name.equals("branch_code_new") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code_new' must be String: '"+value+"' is not." );
                    this.branch_code_new = (String) value;
                    if (this.branch_code_new_is_set)
                        this.branch_code_new_is_modified = true;
                    this.branch_code_new_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("customer_trader_code_old") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'customer_trader_code_old' must be String: '"+value+"' is not." );
                    this.customer_trader_code_old = (String) value;
                    if (this.customer_trader_code_old_is_set)
                        this.customer_trader_code_old_is_modified = true;
                    this.customer_trader_code_old_is_set = true;
                    return;
                }
                else if ( name.equals("customer_trader_code_new") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'customer_trader_code_new' must be String: '"+value+"' is not." );
                    this.customer_trader_code_new = (String) value;
                    if (this.customer_trader_code_new_is_set)
                        this.customer_trader_code_new_is_modified = true;
                    this.customer_trader_code_new_is_set = true;
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
                if ( name.equals("del_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'del_div' must be String: '"+value+"' is not." );
                    this.del_div = (String) value;
                    if (this.del_div_is_set)
                        this.del_div_is_modified = true;
                    this.del_div_is_set = true;
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
                else if ( name.equals("inherit_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'inherit_div' must be String: '"+value+"' is not." );
                    this.inherit_div = (String) value;
                    if (this.inherit_div_is_set)
                        this.inherit_div_is_modified = true;
                    this.inherit_div_is_set = true;
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
            case 'p':
                if ( name.equals("pro_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pro_div' must be String: '"+value+"' is not." );
                    this.pro_div = (String) value;
                    if (this.pro_div_is_set)
                        this.pro_div_is_modified = true;
                    this.pro_div_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("rec_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rec_div' must be String: '"+value+"' is not." );
                    this.rec_div = (String) value;
                    if (this.rec_div_is_set)
                        this.rec_div_is_modified = true;
                    this.rec_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code_old") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_trader_code_old' must be String: '"+value+"' is not." );
                    this.sonar_trader_code_old = (String) value;
                    if (this.sonar_trader_code_old_is_set)
                        this.sonar_trader_code_old_is_modified = true;
                    this.sonar_trader_code_old_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_trader_code_new") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_trader_code_new' must be String: '"+value+"' is not." );
                    this.sonar_trader_code_new = (String) value;
                    if (this.sonar_trader_code_new_is_set)
                        this.sonar_trader_code_new_is_modified = true;
                    this.sonar_trader_code_new_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("transfer_tbl") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_tbl' must be String: '"+value+"' is not." );
                    this.transfer_tbl = (String) value;
                    if (this.transfer_tbl_is_set)
                        this.transfer_tbl_is_modified = true;
                    this.transfer_tbl_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_div' must be String: '"+value+"' is not." );
                    this.transfer_div = (String) value;
                    if (this.transfer_div_is_set)
                        this.transfer_div_is_modified = true;
                    this.transfer_div_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("work_day") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'work_day' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.work_day = (java.sql.Timestamp) value;
                    if (this.work_day_is_set)
                        this.work_day_is_modified = true;
                    this.work_day_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
