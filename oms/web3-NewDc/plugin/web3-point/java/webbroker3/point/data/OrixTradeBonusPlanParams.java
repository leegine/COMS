head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.50.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	OrixTradeBonusPlanParams.java;


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
 * orix_trade_bonus_planテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link OrixTradeBonusPlanRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link OrixTradeBonusPlanParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link OrixTradeBonusPlanParams}が{@@link OrixTradeBonusPlanRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrixTradeBonusPlanPK 
 * @@see OrixTradeBonusPlanRow 
 */
public class OrixTradeBonusPlanParams extends Params implements OrixTradeBonusPlanRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "orix_trade_bonus_plan";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = OrixTradeBonusPlanRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return OrixTradeBonusPlanRow.TYPE;
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
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>apply_month_curr</em>カラムの値 
   */
  public  String  apply_month_curr;

  /** 
   * <em>trd_point_curr</em>カラムの値 
   */
  public  Integer  trd_point_curr;

  /** 
   * <em>cmp_point_curr</em>カラムの値 
   */
  public  Integer  cmp_point_curr;

  /** 
   * <em>cut_rate_curr</em>カラムの値 
   */
  public  String  cut_rate_curr;

  /** 
   * <em>apply_month_next</em>カラムの値 
   */
  public  String  apply_month_next;

  /** 
   * <em>trd_point_next</em>カラムの値 
   */
  public  Integer  trd_point_next;

  /** 
   * <em>cmp_point_next</em>カラムの値 
   */
  public  Integer  cmp_point_next;

  /** 
   * <em>cut_rate_next</em>カラムの値 
   */
  public  String  cut_rate_next;

  /** 
   * <em>sub_acc_code</em>カラムの値 
   */
  public  String  sub_acc_code;

  /** 
   * <em>free_term_from</em>カラムの値 
   */
  public  String  free_term_from;

  /** 
   * <em>free_term_to</em>カラムの値 
   */
  public  String  free_term_to;

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
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean apply_month_curr_is_set = false;
  private boolean apply_month_curr_is_modified = false;
  private boolean trd_point_curr_is_set = false;
  private boolean trd_point_curr_is_modified = false;
  private boolean cmp_point_curr_is_set = false;
  private boolean cmp_point_curr_is_modified = false;
  private boolean cut_rate_curr_is_set = false;
  private boolean cut_rate_curr_is_modified = false;
  private boolean apply_month_next_is_set = false;
  private boolean apply_month_next_is_modified = false;
  private boolean trd_point_next_is_set = false;
  private boolean trd_point_next_is_modified = false;
  private boolean cmp_point_next_is_set = false;
  private boolean cmp_point_next_is_modified = false;
  private boolean cut_rate_next_is_set = false;
  private boolean cut_rate_next_is_modified = false;
  private boolean sub_acc_code_is_set = false;
  private boolean sub_acc_code_is_modified = false;
  private boolean free_term_from_is_set = false;
  private boolean free_term_from_is_modified = false;
  private boolean free_term_to_is_set = false;
  private boolean free_term_to_is_modified = false;
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
      + "," + "account_code=" + account_code
      + "," + "apply_month_curr=" +apply_month_curr
      + "," + "trd_point_curr=" +trd_point_curr
      + "," + "cmp_point_curr=" +cmp_point_curr
      + "," + "cut_rate_curr=" +cut_rate_curr
      + "," + "apply_month_next=" +apply_month_next
      + "," + "trd_point_next=" +trd_point_next
      + "," + "cmp_point_next=" +cmp_point_next
      + "," + "cut_rate_next=" +cut_rate_next
      + "," + "sub_acc_code=" +sub_acc_code
      + "," + "free_term_from=" +free_term_from
      + "," + "free_term_to=" +free_term_to
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のOrixTradeBonusPlanParamsオブジェクトを作成します。 
   */
  public OrixTradeBonusPlanParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のOrixTradeBonusPlanRowオブジェクトの値を利用してOrixTradeBonusPlanParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するOrixTradeBonusPlanRowオブジェクト 
   */
  public OrixTradeBonusPlanParams( OrixTradeBonusPlanRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    apply_month_curr = p_row.getApplyMonthCurr();
    apply_month_curr_is_set = p_row.getApplyMonthCurrIsSet();
    apply_month_curr_is_modified = p_row.getApplyMonthCurrIsModified();
    if ( !p_row.getTrdPointCurrIsNull() )
      trd_point_curr = new Integer( p_row.getTrdPointCurr() );
    trd_point_curr_is_set = p_row.getTrdPointCurrIsSet();
    trd_point_curr_is_modified = p_row.getTrdPointCurrIsModified();
    if ( !p_row.getCmpPointCurrIsNull() )
      cmp_point_curr = new Integer( p_row.getCmpPointCurr() );
    cmp_point_curr_is_set = p_row.getCmpPointCurrIsSet();
    cmp_point_curr_is_modified = p_row.getCmpPointCurrIsModified();
    cut_rate_curr = p_row.getCutRateCurr();
    cut_rate_curr_is_set = p_row.getCutRateCurrIsSet();
    cut_rate_curr_is_modified = p_row.getCutRateCurrIsModified();
    apply_month_next = p_row.getApplyMonthNext();
    apply_month_next_is_set = p_row.getApplyMonthNextIsSet();
    apply_month_next_is_modified = p_row.getApplyMonthNextIsModified();
    if ( !p_row.getTrdPointNextIsNull() )
      trd_point_next = new Integer( p_row.getTrdPointNext() );
    trd_point_next_is_set = p_row.getTrdPointNextIsSet();
    trd_point_next_is_modified = p_row.getTrdPointNextIsModified();
    if ( !p_row.getCmpPointNextIsNull() )
      cmp_point_next = new Integer( p_row.getCmpPointNext() );
    cmp_point_next_is_set = p_row.getCmpPointNextIsSet();
    cmp_point_next_is_modified = p_row.getCmpPointNextIsModified();
    cut_rate_next = p_row.getCutRateNext();
    cut_rate_next_is_set = p_row.getCutRateNextIsSet();
    cut_rate_next_is_modified = p_row.getCutRateNextIsModified();
    sub_acc_code = p_row.getSubAccCode();
    sub_acc_code_is_set = p_row.getSubAccCodeIsSet();
    sub_acc_code_is_modified = p_row.getSubAccCodeIsModified();
    free_term_from = p_row.getFreeTermFrom();
    free_term_from_is_set = p_row.getFreeTermFromIsSet();
    free_term_from_is_modified = p_row.getFreeTermFromIsModified();
    free_term_to = p_row.getFreeTermTo();
    free_term_to_is_set = p_row.getFreeTermToIsSet();
    free_term_to_is_modified = p_row.getFreeTermToIsModified();
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
      apply_month_curr_is_set = true;
      apply_month_curr_is_modified = true;
      trd_point_curr_is_set = true;
      trd_point_curr_is_modified = true;
      cmp_point_curr_is_set = true;
      cmp_point_curr_is_modified = true;
      cut_rate_curr_is_set = true;
      cut_rate_curr_is_modified = true;
      apply_month_next_is_set = true;
      apply_month_next_is_modified = true;
      trd_point_next_is_set = true;
      trd_point_next_is_modified = true;
      cmp_point_next_is_set = true;
      cmp_point_next_is_modified = true;
      cut_rate_next_is_set = true;
      cut_rate_next_is_modified = true;
      sub_acc_code_is_set = true;
      sub_acc_code_is_modified = true;
      free_term_from_is_set = true;
      free_term_from_is_modified = true;
      free_term_to_is_set = true;
      free_term_to_is_modified = true;
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
    if ( !( other instanceof OrixTradeBonusPlanRow ) )
       return false;
    return fieldsEqual( (OrixTradeBonusPlanRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のOrixTradeBonusPlanRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( OrixTradeBonusPlanRow row )
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
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( apply_month_curr == null ) {
      if ( row.getApplyMonthCurr() != null )
        return false;
    } else if ( !apply_month_curr.equals( row.getApplyMonthCurr() ) ) {
        return false;
    }
    if ( trd_point_curr == null ) {
      if ( !row.getTrdPointCurrIsNull() )
        return false;
    } else if ( row.getTrdPointCurrIsNull() || ( trd_point_curr.intValue() != row.getTrdPointCurr() ) ) {
        return false;
    }
    if ( cmp_point_curr == null ) {
      if ( !row.getCmpPointCurrIsNull() )
        return false;
    } else if ( row.getCmpPointCurrIsNull() || ( cmp_point_curr.intValue() != row.getCmpPointCurr() ) ) {
        return false;
    }
    if ( cut_rate_curr == null ) {
      if ( row.getCutRateCurr() != null )
        return false;
    } else if ( !cut_rate_curr.equals( row.getCutRateCurr() ) ) {
        return false;
    }
    if ( apply_month_next == null ) {
      if ( row.getApplyMonthNext() != null )
        return false;
    } else if ( !apply_month_next.equals( row.getApplyMonthNext() ) ) {
        return false;
    }
    if ( trd_point_next == null ) {
      if ( !row.getTrdPointNextIsNull() )
        return false;
    } else if ( row.getTrdPointNextIsNull() || ( trd_point_next.intValue() != row.getTrdPointNext() ) ) {
        return false;
    }
    if ( cmp_point_next == null ) {
      if ( !row.getCmpPointNextIsNull() )
        return false;
    } else if ( row.getCmpPointNextIsNull() || ( cmp_point_next.intValue() != row.getCmpPointNext() ) ) {
        return false;
    }
    if ( cut_rate_next == null ) {
      if ( row.getCutRateNext() != null )
        return false;
    } else if ( !cut_rate_next.equals( row.getCutRateNext() ) ) {
        return false;
    }
    if ( sub_acc_code == null ) {
      if ( row.getSubAccCode() != null )
        return false;
    } else if ( !sub_acc_code.equals( row.getSubAccCode() ) ) {
        return false;
    }
    if ( free_term_from == null ) {
      if ( row.getFreeTermFrom() != null )
        return false;
    } else if ( !free_term_from.equals( row.getFreeTermFrom() ) ) {
        return false;
    }
    if ( free_term_to == null ) {
      if ( row.getFreeTermTo() != null )
        return false;
    } else if ( !free_term_to.equals( row.getFreeTermTo() ) ) {
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
        + (account_code!=null? account_code.hashCode(): 0) 
        + (apply_month_curr!=null? apply_month_curr.hashCode(): 0) 
        + (trd_point_curr!=null? trd_point_curr.hashCode(): 0) 
        + (cmp_point_curr!=null? cmp_point_curr.hashCode(): 0) 
        + (cut_rate_curr!=null? cut_rate_curr.hashCode(): 0) 
        + (apply_month_next!=null? apply_month_next.hashCode(): 0) 
        + (trd_point_next!=null? trd_point_next.hashCode(): 0) 
        + (cmp_point_next!=null? cmp_point_next.hashCode(): 0) 
        + (cut_rate_next!=null? cut_rate_next.hashCode(): 0) 
        + (sub_acc_code!=null? sub_acc_code.hashCode(): 0) 
        + (free_term_from!=null? free_term_from.hashCode(): 0) 
        + (free_term_to!=null? free_term_to.hashCode(): 0) 
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
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
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
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( apply_month_curr != null )
			map.put("apply_month_curr",apply_month_curr);
		if ( trd_point_curr != null )
			map.put("trd_point_curr",trd_point_curr);
		if ( cmp_point_curr != null )
			map.put("cmp_point_curr",cmp_point_curr);
		if ( cut_rate_curr != null )
			map.put("cut_rate_curr",cut_rate_curr);
		if ( apply_month_next != null )
			map.put("apply_month_next",apply_month_next);
		if ( trd_point_next != null )
			map.put("trd_point_next",trd_point_next);
		if ( cmp_point_next != null )
			map.put("cmp_point_next",cmp_point_next);
		if ( cut_rate_next != null )
			map.put("cut_rate_next",cut_rate_next);
		if ( sub_acc_code != null )
			map.put("sub_acc_code",sub_acc_code);
		if ( free_term_from != null )
			map.put("free_term_from",free_term_from);
		if ( free_term_to != null )
			map.put("free_term_to",free_term_to);
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
		if ( apply_month_curr_is_modified )
			map.put("apply_month_curr",apply_month_curr);
		if ( trd_point_curr_is_modified )
			map.put("trd_point_curr",trd_point_curr);
		if ( cmp_point_curr_is_modified )
			map.put("cmp_point_curr",cmp_point_curr);
		if ( cut_rate_curr_is_modified )
			map.put("cut_rate_curr",cut_rate_curr);
		if ( apply_month_next_is_modified )
			map.put("apply_month_next",apply_month_next);
		if ( trd_point_next_is_modified )
			map.put("trd_point_next",trd_point_next);
		if ( cmp_point_next_is_modified )
			map.put("cmp_point_next",cmp_point_next);
		if ( cut_rate_next_is_modified )
			map.put("cut_rate_next",cut_rate_next);
		if ( sub_acc_code_is_modified )
			map.put("sub_acc_code",sub_acc_code);
		if ( free_term_from_is_modified )
			map.put("free_term_from",free_term_from);
		if ( free_term_to_is_modified )
			map.put("free_term_to",free_term_to);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("apply_month_curr",apply_month_curr);
			map.put("trd_point_curr",trd_point_curr);
			map.put("cmp_point_curr",cmp_point_curr);
			map.put("cut_rate_curr",cut_rate_curr);
			map.put("apply_month_next",apply_month_next);
			map.put("trd_point_next",trd_point_next);
			map.put("cmp_point_next",cmp_point_next);
			map.put("cut_rate_next",cut_rate_next);
			map.put("sub_acc_code",sub_acc_code);
			map.put("free_term_from",free_term_from);
			map.put("free_term_to",free_term_to);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
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
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsModified() {
    return account_code_is_modified;
  }


  /** 
   * <em>apply_month_curr</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getApplyMonthCurr()
  {
    return apply_month_curr;
  }


  /** 
   * <em>apply_month_curr</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApplyMonthCurrIsSet() {
    return apply_month_curr_is_set;
  }


  /** 
   * <em>apply_month_curr</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApplyMonthCurrIsModified() {
    return apply_month_curr_is_modified;
  }


  /** 
   * <em>trd_point_curr</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getTrdPointCurr()
  {
    return ( trd_point_curr==null? ((int)0): trd_point_curr.intValue() );
  }


  /** 
   * <em>trd_point_curr</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTrdPointCurrIsNull()
  {
    return trd_point_curr == null;
  }


  /** 
   * <em>trd_point_curr</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrdPointCurrIsSet() {
    return trd_point_curr_is_set;
  }


  /** 
   * <em>trd_point_curr</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrdPointCurrIsModified() {
    return trd_point_curr_is_modified;
  }


  /** 
   * <em>cmp_point_curr</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getCmpPointCurr()
  {
    return ( cmp_point_curr==null? ((int)0): cmp_point_curr.intValue() );
  }


  /** 
   * <em>cmp_point_curr</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCmpPointCurrIsNull()
  {
    return cmp_point_curr == null;
  }


  /** 
   * <em>cmp_point_curr</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCmpPointCurrIsSet() {
    return cmp_point_curr_is_set;
  }


  /** 
   * <em>cmp_point_curr</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCmpPointCurrIsModified() {
    return cmp_point_curr_is_modified;
  }


  /** 
   * <em>cut_rate_curr</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCutRateCurr()
  {
    return cut_rate_curr;
  }


  /** 
   * <em>cut_rate_curr</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCutRateCurrIsSet() {
    return cut_rate_curr_is_set;
  }


  /** 
   * <em>cut_rate_curr</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCutRateCurrIsModified() {
    return cut_rate_curr_is_modified;
  }


  /** 
   * <em>apply_month_next</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getApplyMonthNext()
  {
    return apply_month_next;
  }


  /** 
   * <em>apply_month_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApplyMonthNextIsSet() {
    return apply_month_next_is_set;
  }


  /** 
   * <em>apply_month_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApplyMonthNextIsModified() {
    return apply_month_next_is_modified;
  }


  /** 
   * <em>trd_point_next</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getTrdPointNext()
  {
    return ( trd_point_next==null? ((int)0): trd_point_next.intValue() );
  }


  /** 
   * <em>trd_point_next</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTrdPointNextIsNull()
  {
    return trd_point_next == null;
  }


  /** 
   * <em>trd_point_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrdPointNextIsSet() {
    return trd_point_next_is_set;
  }


  /** 
   * <em>trd_point_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrdPointNextIsModified() {
    return trd_point_next_is_modified;
  }


  /** 
   * <em>cmp_point_next</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getCmpPointNext()
  {
    return ( cmp_point_next==null? ((int)0): cmp_point_next.intValue() );
  }


  /** 
   * <em>cmp_point_next</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCmpPointNextIsNull()
  {
    return cmp_point_next == null;
  }


  /** 
   * <em>cmp_point_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCmpPointNextIsSet() {
    return cmp_point_next_is_set;
  }


  /** 
   * <em>cmp_point_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCmpPointNextIsModified() {
    return cmp_point_next_is_modified;
  }


  /** 
   * <em>cut_rate_next</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCutRateNext()
  {
    return cut_rate_next;
  }


  /** 
   * <em>cut_rate_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCutRateNextIsSet() {
    return cut_rate_next_is_set;
  }


  /** 
   * <em>cut_rate_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCutRateNextIsModified() {
    return cut_rate_next_is_modified;
  }


  /** 
   * <em>sub_acc_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSubAccCode()
  {
    return sub_acc_code;
  }


  /** 
   * <em>sub_acc_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccCodeIsSet() {
    return sub_acc_code_is_set;
  }


  /** 
   * <em>sub_acc_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccCodeIsModified() {
    return sub_acc_code_is_modified;
  }


  /** 
   * <em>free_term_from</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFreeTermFrom()
  {
    return free_term_from;
  }


  /** 
   * <em>free_term_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeTermFromIsSet() {
    return free_term_from_is_set;
  }


  /** 
   * <em>free_term_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeTermFromIsModified() {
    return free_term_from_is_modified;
  }


  /** 
   * <em>free_term_to</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFreeTermTo()
  {
    return free_term_to;
  }


  /** 
   * <em>free_term_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeTermToIsSet() {
    return free_term_to_is_set;
  }


  /** 
   * <em>free_term_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeTermToIsModified() {
    return free_term_to_is_modified;
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
    return new OrixTradeBonusPlanPK(institution_code, branch_code, account_code);
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
   * <em>account_code</em>カラムの値を設定します。 
   *
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>apply_month_curr</em>カラムの値を設定します。 
   *
   * @@param p_applyMonthCurr <em>apply_month_curr</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setApplyMonthCurr( String p_applyMonthCurr )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_month_curr = p_applyMonthCurr;
    apply_month_curr_is_set = true;
    apply_month_curr_is_modified = true;
  }


  /** 
   * <em>trd_point_curr</em>カラムの値を設定します。 
   *
   * @@param p_trdPointCurr <em>trd_point_curr</em>カラムの値をあらわす3桁以下のint値 
   */
  public final void setTrdPointCurr( int p_trdPointCurr )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trd_point_curr = new Integer(p_trdPointCurr);
    trd_point_curr_is_set = true;
    trd_point_curr_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>trd_point_curr</em>カラムに値を設定します。 
   */
  public final void setTrdPointCurr( Integer p_trdPointCurr )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trd_point_curr = p_trdPointCurr;
    trd_point_curr_is_set = true;
    trd_point_curr_is_modified = true;
  }


  /** 
   * <em>cmp_point_curr</em>カラムの値を設定します。 
   *
   * @@param p_cmpPointCurr <em>cmp_point_curr</em>カラムの値をあらわす3桁以下のint値 
   */
  public final void setCmpPointCurr( int p_cmpPointCurr )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cmp_point_curr = new Integer(p_cmpPointCurr);
    cmp_point_curr_is_set = true;
    cmp_point_curr_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cmp_point_curr</em>カラムに値を設定します。 
   */
  public final void setCmpPointCurr( Integer p_cmpPointCurr )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cmp_point_curr = p_cmpPointCurr;
    cmp_point_curr_is_set = true;
    cmp_point_curr_is_modified = true;
  }


  /** 
   * <em>cut_rate_curr</em>カラムの値を設定します。 
   *
   * @@param p_cutRateCurr <em>cut_rate_curr</em>カラムの値をあらわす32桁以下のString値 
   */
  public final void setCutRateCurr( String p_cutRateCurr )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cut_rate_curr = p_cutRateCurr;
    cut_rate_curr_is_set = true;
    cut_rate_curr_is_modified = true;
  }


  /** 
   * <em>apply_month_next</em>カラムの値を設定します。 
   *
   * @@param p_applyMonthNext <em>apply_month_next</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setApplyMonthNext( String p_applyMonthNext )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_month_next = p_applyMonthNext;
    apply_month_next_is_set = true;
    apply_month_next_is_modified = true;
  }


  /** 
   * <em>trd_point_next</em>カラムの値を設定します。 
   *
   * @@param p_trdPointNext <em>trd_point_next</em>カラムの値をあらわす3桁以下のint値 
   */
  public final void setTrdPointNext( int p_trdPointNext )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trd_point_next = new Integer(p_trdPointNext);
    trd_point_next_is_set = true;
    trd_point_next_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>trd_point_next</em>カラムに値を設定します。 
   */
  public final void setTrdPointNext( Integer p_trdPointNext )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trd_point_next = p_trdPointNext;
    trd_point_next_is_set = true;
    trd_point_next_is_modified = true;
  }


  /** 
   * <em>cmp_point_next</em>カラムの値を設定します。 
   *
   * @@param p_cmpPointNext <em>cmp_point_next</em>カラムの値をあらわす3桁以下のint値 
   */
  public final void setCmpPointNext( int p_cmpPointNext )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cmp_point_next = new Integer(p_cmpPointNext);
    cmp_point_next_is_set = true;
    cmp_point_next_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cmp_point_next</em>カラムに値を設定します。 
   */
  public final void setCmpPointNext( Integer p_cmpPointNext )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cmp_point_next = p_cmpPointNext;
    cmp_point_next_is_set = true;
    cmp_point_next_is_modified = true;
  }


  /** 
   * <em>cut_rate_next</em>カラムの値を設定します。 
   *
   * @@param p_cutRateNext <em>cut_rate_next</em>カラムの値をあらわす32桁以下のString値 
   */
  public final void setCutRateNext( String p_cutRateNext )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cut_rate_next = p_cutRateNext;
    cut_rate_next_is_set = true;
    cut_rate_next_is_modified = true;
  }


  /** 
   * <em>sub_acc_code</em>カラムの値を設定します。 
   *
   * @@param p_subAccCode <em>sub_acc_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setSubAccCode( String p_subAccCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_acc_code = p_subAccCode;
    sub_acc_code_is_set = true;
    sub_acc_code_is_modified = true;
  }


  /** 
   * <em>free_term_from</em>カラムの値を設定します。 
   *
   * @@param p_freeTermFrom <em>free_term_from</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setFreeTermFrom( String p_freeTermFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    free_term_from = p_freeTermFrom;
    free_term_from_is_set = true;
    free_term_from_is_modified = true;
  }


  /** 
   * <em>free_term_to</em>カラムの値を設定します。 
   *
   * @@param p_freeTermTo <em>free_term_to</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setFreeTermTo( String p_freeTermTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    free_term_to = p_freeTermTo;
    free_term_to_is_set = true;
    free_term_to_is_modified = true;
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
            case 'a':
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("apply_month_curr") ) {
                    return this.apply_month_curr;
                }
                else if ( name.equals("apply_month_next") ) {
                    return this.apply_month_next;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("cmp_point_curr") ) {
                    return this.cmp_point_curr;
                }
                else if ( name.equals("cut_rate_curr") ) {
                    return this.cut_rate_curr;
                }
                else if ( name.equals("cmp_point_next") ) {
                    return this.cmp_point_next;
                }
                else if ( name.equals("cut_rate_next") ) {
                    return this.cut_rate_next;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("free_term_from") ) {
                    return this.free_term_from;
                }
                else if ( name.equals("free_term_to") ) {
                    return this.free_term_to;
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
                if ( name.equals("sub_acc_code") ) {
                    return this.sub_acc_code;
                }
                break;
            case 't':
                if ( name.equals("trd_point_curr") ) {
                    return this.trd_point_curr;
                }
                else if ( name.equals("trd_point_next") ) {
                    return this.trd_point_next;
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
                if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("apply_month_curr") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'apply_month_curr' must be String: '"+value+"' is not." );
                    this.apply_month_curr = (String) value;
                    if (this.apply_month_curr_is_set)
                        this.apply_month_curr_is_modified = true;
                    this.apply_month_curr_is_set = true;
                    return;
                }
                else if ( name.equals("apply_month_next") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'apply_month_next' must be String: '"+value+"' is not." );
                    this.apply_month_next = (String) value;
                    if (this.apply_month_next_is_set)
                        this.apply_month_next_is_modified = true;
                    this.apply_month_next_is_set = true;
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
                if ( name.equals("cmp_point_curr") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'cmp_point_curr' must be Integer: '"+value+"' is not." );
                    this.cmp_point_curr = (Integer) value;
                    if (this.cmp_point_curr_is_set)
                        this.cmp_point_curr_is_modified = true;
                    this.cmp_point_curr_is_set = true;
                    return;
                }
                else if ( name.equals("cut_rate_curr") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cut_rate_curr' must be String: '"+value+"' is not." );
                    this.cut_rate_curr = (String) value;
                    if (this.cut_rate_curr_is_set)
                        this.cut_rate_curr_is_modified = true;
                    this.cut_rate_curr_is_set = true;
                    return;
                }
                else if ( name.equals("cmp_point_next") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'cmp_point_next' must be Integer: '"+value+"' is not." );
                    this.cmp_point_next = (Integer) value;
                    if (this.cmp_point_next_is_set)
                        this.cmp_point_next_is_modified = true;
                    this.cmp_point_next_is_set = true;
                    return;
                }
                else if ( name.equals("cut_rate_next") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cut_rate_next' must be String: '"+value+"' is not." );
                    this.cut_rate_next = (String) value;
                    if (this.cut_rate_next_is_set)
                        this.cut_rate_next_is_modified = true;
                    this.cut_rate_next_is_set = true;
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
                if ( name.equals("free_term_from") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'free_term_from' must be String: '"+value+"' is not." );
                    this.free_term_from = (String) value;
                    if (this.free_term_from_is_set)
                        this.free_term_from_is_modified = true;
                    this.free_term_from_is_set = true;
                    return;
                }
                else if ( name.equals("free_term_to") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'free_term_to' must be String: '"+value+"' is not." );
                    this.free_term_to = (String) value;
                    if (this.free_term_to_is_set)
                        this.free_term_to_is_modified = true;
                    this.free_term_to_is_set = true;
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
            case 's':
                if ( name.equals("sub_acc_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sub_acc_code' must be String: '"+value+"' is not." );
                    this.sub_acc_code = (String) value;
                    if (this.sub_acc_code_is_set)
                        this.sub_acc_code_is_modified = true;
                    this.sub_acc_code_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trd_point_curr") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'trd_point_curr' must be Integer: '"+value+"' is not." );
                    this.trd_point_curr = (Integer) value;
                    if (this.trd_point_curr_is_set)
                        this.trd_point_curr_is_modified = true;
                    this.trd_point_curr_is_set = true;
                    return;
                }
                else if ( name.equals("trd_point_next") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'trd_point_next' must be Integer: '"+value+"' is not." );
                    this.trd_point_next = (Integer) value;
                    if (this.trd_point_next_is_set)
                        this.trd_point_next_is_modified = true;
                    this.trd_point_next_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
