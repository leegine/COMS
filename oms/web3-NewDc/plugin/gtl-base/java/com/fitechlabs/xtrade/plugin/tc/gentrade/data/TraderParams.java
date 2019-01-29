head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.39.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	TraderParams.java;


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
 * traderテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link TraderRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link TraderParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link TraderParams}が{@@link TraderRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TraderPK 
 * @@see TraderRow 
 */
public class TraderParams extends Params implements TraderRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "trader";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = TraderRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return TraderRow.TYPE;
  }


  /** 
   * <em>trader_id</em>カラムの値 
   */
  public  long  trader_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>trader_code</em>カラムの値 
   */
  public  String  trader_code;

  /** 
   * <em>trader_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum  trader_type;

  /** 
   * <em>login_id</em>カラムの値 
   */
  public  long  login_id;

  /** 
   * <em>family_name</em>カラムの値 
   */
  public  String  family_name;

  /** 
   * <em>middle_name</em>カラムの値 
   */
  public  String  middle_name;

  /** 
   * <em>given_name</em>カラムの値 
   */
  public  String  given_name;

  /** 
   * <em>family_name_alt1</em>カラムの値 
   */
  public  String  family_name_alt1;

  /** 
   * <em>middle_name_alt1</em>カラムの値 
   */
  public  String  middle_name_alt1;

  /** 
   * <em>given_name_alt1</em>カラムの値 
   */
  public  String  given_name_alt1;

  /** 
   * <em>family_name_alt2</em>カラムの値 
   */
  public  String  family_name_alt2;

  /** 
   * <em>middle_name_alt2</em>カラムの値 
   */
  public  String  middle_name_alt2;

  /** 
   * <em>given_name_alt2</em>カラムの値 
   */
  public  String  given_name_alt2;

  /** 
   * <em>trading_password</em>カラムの値 
   */
  public  String  trading_password;

  /** 
   * <em>account_order_flag</em>カラムの値 
   */
  public  String  account_order_flag;

  /** 
   * <em>department_code</em>カラムの値 
   */
  public  String  department_code;

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

  private boolean trader_id_is_set = false;
  private boolean trader_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean trader_type_is_set = false;
  private boolean trader_type_is_modified = false;
  private boolean login_id_is_set = false;
  private boolean login_id_is_modified = false;
  private boolean family_name_is_set = false;
  private boolean family_name_is_modified = false;
  private boolean middle_name_is_set = false;
  private boolean middle_name_is_modified = false;
  private boolean given_name_is_set = false;
  private boolean given_name_is_modified = false;
  private boolean family_name_alt1_is_set = false;
  private boolean family_name_alt1_is_modified = false;
  private boolean middle_name_alt1_is_set = false;
  private boolean middle_name_alt1_is_modified = false;
  private boolean given_name_alt1_is_set = false;
  private boolean given_name_alt1_is_modified = false;
  private boolean family_name_alt2_is_set = false;
  private boolean family_name_alt2_is_modified = false;
  private boolean middle_name_alt2_is_set = false;
  private boolean middle_name_alt2_is_modified = false;
  private boolean given_name_alt2_is_set = false;
  private boolean given_name_alt2_is_modified = false;
  private boolean trading_password_is_set = false;
  private boolean trading_password_is_modified = false;
  private boolean account_order_flag_is_set = false;
  private boolean account_order_flag_is_modified = false;
  private boolean department_code_is_set = false;
  private boolean department_code_is_modified = false;
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
       + "trader_id=" + trader_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_id=" +branch_id
      + "," + "branch_code=" +branch_code
      + "," + "trader_code=" +trader_code
      + "," + "trader_type=" +trader_type
      + "," + "login_id=" +login_id
      + "," + "family_name=" +family_name
      + "," + "middle_name=" +middle_name
      + "," + "given_name=" +given_name
      + "," + "family_name_alt1=" +family_name_alt1
      + "," + "middle_name_alt1=" +middle_name_alt1
      + "," + "given_name_alt1=" +given_name_alt1
      + "," + "family_name_alt2=" +family_name_alt2
      + "," + "middle_name_alt2=" +middle_name_alt2
      + "," + "given_name_alt2=" +given_name_alt2
      + "," + "trading_password=" +trading_password
      + "," + "account_order_flag=" +account_order_flag
      + "," + "department_code=" +department_code
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のTraderParamsオブジェクトを作成します。 
   */
  public TraderParams() {
    trader_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のTraderRowオブジェクトの値を利用してTraderParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するTraderRowオブジェクト 
   */
  public TraderParams( TraderRow p_row )
  {
    trader_id = p_row.getTraderId();
    trader_id_is_set = p_row.getTraderIdIsSet();
    trader_id_is_modified = p_row.getTraderIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    trader_type = p_row.getTraderType();
    trader_type_is_set = p_row.getTraderTypeIsSet();
    trader_type_is_modified = p_row.getTraderTypeIsModified();
    login_id = p_row.getLoginId();
    login_id_is_set = p_row.getLoginIdIsSet();
    login_id_is_modified = p_row.getLoginIdIsModified();
    family_name = p_row.getFamilyName();
    family_name_is_set = p_row.getFamilyNameIsSet();
    family_name_is_modified = p_row.getFamilyNameIsModified();
    middle_name = p_row.getMiddleName();
    middle_name_is_set = p_row.getMiddleNameIsSet();
    middle_name_is_modified = p_row.getMiddleNameIsModified();
    given_name = p_row.getGivenName();
    given_name_is_set = p_row.getGivenNameIsSet();
    given_name_is_modified = p_row.getGivenNameIsModified();
    family_name_alt1 = p_row.getFamilyNameAlt1();
    family_name_alt1_is_set = p_row.getFamilyNameAlt1IsSet();
    family_name_alt1_is_modified = p_row.getFamilyNameAlt1IsModified();
    middle_name_alt1 = p_row.getMiddleNameAlt1();
    middle_name_alt1_is_set = p_row.getMiddleNameAlt1IsSet();
    middle_name_alt1_is_modified = p_row.getMiddleNameAlt1IsModified();
    given_name_alt1 = p_row.getGivenNameAlt1();
    given_name_alt1_is_set = p_row.getGivenNameAlt1IsSet();
    given_name_alt1_is_modified = p_row.getGivenNameAlt1IsModified();
    family_name_alt2 = p_row.getFamilyNameAlt2();
    family_name_alt2_is_set = p_row.getFamilyNameAlt2IsSet();
    family_name_alt2_is_modified = p_row.getFamilyNameAlt2IsModified();
    middle_name_alt2 = p_row.getMiddleNameAlt2();
    middle_name_alt2_is_set = p_row.getMiddleNameAlt2IsSet();
    middle_name_alt2_is_modified = p_row.getMiddleNameAlt2IsModified();
    given_name_alt2 = p_row.getGivenNameAlt2();
    given_name_alt2_is_set = p_row.getGivenNameAlt2IsSet();
    given_name_alt2_is_modified = p_row.getGivenNameAlt2IsModified();
    trading_password = p_row.getTradingPassword();
    trading_password_is_set = p_row.getTradingPasswordIsSet();
    trading_password_is_modified = p_row.getTradingPasswordIsModified();
    account_order_flag = p_row.getAccountOrderFlag();
    account_order_flag_is_set = p_row.getAccountOrderFlagIsSet();
    account_order_flag_is_modified = p_row.getAccountOrderFlagIsModified();
    department_code = p_row.getDepartmentCode();
    department_code_is_set = p_row.getDepartmentCodeIsSet();
    department_code_is_modified = p_row.getDepartmentCodeIsModified();
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
      branch_id_is_set = true;
      branch_id_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      trader_code_is_set = true;
      trader_code_is_modified = true;
      trader_type_is_set = true;
      trader_type_is_modified = true;
      login_id_is_set = true;
      login_id_is_modified = true;
      family_name_is_set = true;
      family_name_is_modified = true;
      middle_name_is_set = true;
      middle_name_is_modified = true;
      given_name_is_set = true;
      given_name_is_modified = true;
      family_name_alt1_is_set = true;
      family_name_alt1_is_modified = true;
      middle_name_alt1_is_set = true;
      middle_name_alt1_is_modified = true;
      given_name_alt1_is_set = true;
      given_name_alt1_is_modified = true;
      family_name_alt2_is_set = true;
      family_name_alt2_is_modified = true;
      middle_name_alt2_is_set = true;
      middle_name_alt2_is_modified = true;
      given_name_alt2_is_set = true;
      given_name_alt2_is_modified = true;
      trading_password_is_set = true;
      trading_password_is_modified = true;
      account_order_flag_is_set = true;
      account_order_flag_is_modified = true;
      department_code_is_set = true;
      department_code_is_modified = true;
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
    if ( !( other instanceof TraderRow ) )
       return false;
    return fieldsEqual( (TraderRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のTraderRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( TraderRow row )
  {
    if ( trader_id != row.getTraderId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_id != row.getBranchId() )
      return false;
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( trader_code == null ) {
      if ( row.getTraderCode() != null )
        return false;
    } else if ( !trader_code.equals( row.getTraderCode() ) ) {
        return false;
    }
    if ( trader_type == null ) {
      if ( row.getTraderType() != null )
        return false;
    } else if ( !trader_type.equals( row.getTraderType() ) ) {
        return false;
    }
    if ( login_id != row.getLoginId() )
      return false;
    if ( family_name == null ) {
      if ( row.getFamilyName() != null )
        return false;
    } else if ( !family_name.equals( row.getFamilyName() ) ) {
        return false;
    }
    if ( middle_name == null ) {
      if ( row.getMiddleName() != null )
        return false;
    } else if ( !middle_name.equals( row.getMiddleName() ) ) {
        return false;
    }
    if ( given_name == null ) {
      if ( row.getGivenName() != null )
        return false;
    } else if ( !given_name.equals( row.getGivenName() ) ) {
        return false;
    }
    if ( family_name_alt1 == null ) {
      if ( row.getFamilyNameAlt1() != null )
        return false;
    } else if ( !family_name_alt1.equals( row.getFamilyNameAlt1() ) ) {
        return false;
    }
    if ( middle_name_alt1 == null ) {
      if ( row.getMiddleNameAlt1() != null )
        return false;
    } else if ( !middle_name_alt1.equals( row.getMiddleNameAlt1() ) ) {
        return false;
    }
    if ( given_name_alt1 == null ) {
      if ( row.getGivenNameAlt1() != null )
        return false;
    } else if ( !given_name_alt1.equals( row.getGivenNameAlt1() ) ) {
        return false;
    }
    if ( family_name_alt2 == null ) {
      if ( row.getFamilyNameAlt2() != null )
        return false;
    } else if ( !family_name_alt2.equals( row.getFamilyNameAlt2() ) ) {
        return false;
    }
    if ( middle_name_alt2 == null ) {
      if ( row.getMiddleNameAlt2() != null )
        return false;
    } else if ( !middle_name_alt2.equals( row.getMiddleNameAlt2() ) ) {
        return false;
    }
    if ( given_name_alt2 == null ) {
      if ( row.getGivenNameAlt2() != null )
        return false;
    } else if ( !given_name_alt2.equals( row.getGivenNameAlt2() ) ) {
        return false;
    }
    if ( trading_password == null ) {
      if ( row.getTradingPassword() != null )
        return false;
    } else if ( !trading_password.equals( row.getTradingPassword() ) ) {
        return false;
    }
    if ( account_order_flag == null ) {
      if ( row.getAccountOrderFlag() != null )
        return false;
    } else if ( !account_order_flag.equals( row.getAccountOrderFlag() ) ) {
        return false;
    }
    if ( department_code == null ) {
      if ( row.getDepartmentCode() != null )
        return false;
    } else if ( !department_code.equals( row.getDepartmentCode() ) ) {
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
      return  ((int) trader_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) branch_id)
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (trader_type!=null? trader_type.hashCode(): 0) 
        + ((int) login_id)
        + (family_name!=null? family_name.hashCode(): 0) 
        + (middle_name!=null? middle_name.hashCode(): 0) 
        + (given_name!=null? given_name.hashCode(): 0) 
        + (family_name_alt1!=null? family_name_alt1.hashCode(): 0) 
        + (middle_name_alt1!=null? middle_name_alt1.hashCode(): 0) 
        + (given_name_alt1!=null? given_name_alt1.hashCode(): 0) 
        + (family_name_alt2!=null? family_name_alt2.hashCode(): 0) 
        + (middle_name_alt2!=null? middle_name_alt2.hashCode(): 0) 
        + (given_name_alt2!=null? given_name_alt2.hashCode(): 0) 
        + (trading_password!=null? trading_password.hashCode(): 0) 
        + (account_order_flag!=null? account_order_flag.hashCode(): 0) 
        + (department_code!=null? department_code.hashCode(): 0) 
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
		if ( !branch_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_id' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !trader_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'trader_code' must be set before inserting.");
		if ( !trader_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'trader_type' must be set before inserting.");
		if ( !login_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'login_id' must be set before inserting.");
		if ( !family_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'family_name' must be set before inserting.");
		if ( !given_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'given_name' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("trader_id",new Long(trader_id));
		map.put("institution_code",institution_code);
		map.put("branch_id",new Long(branch_id));
		map.put("branch_code",branch_code);
		map.put("trader_code",trader_code);
		map.put("trader_type",trader_type);
		map.put("login_id",new Long(login_id));
		map.put("family_name",family_name);
		if ( middle_name != null )
			map.put("middle_name",middle_name);
		map.put("given_name",given_name);
		if ( family_name_alt1 != null )
			map.put("family_name_alt1",family_name_alt1);
		if ( middle_name_alt1 != null )
			map.put("middle_name_alt1",middle_name_alt1);
		if ( given_name_alt1 != null )
			map.put("given_name_alt1",given_name_alt1);
		if ( family_name_alt2 != null )
			map.put("family_name_alt2",family_name_alt2);
		if ( middle_name_alt2 != null )
			map.put("middle_name_alt2",middle_name_alt2);
		if ( given_name_alt2 != null )
			map.put("given_name_alt2",given_name_alt2);
		if ( trading_password != null )
			map.put("trading_password",trading_password);
		if ( account_order_flag != null )
			map.put("account_order_flag",account_order_flag);
		if ( department_code != null )
			map.put("department_code",department_code);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
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
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( trader_type_is_modified )
			map.put("trader_type",trader_type);
		if ( login_id_is_modified )
			map.put("login_id",new Long(login_id));
		if ( family_name_is_modified )
			map.put("family_name",family_name);
		if ( middle_name_is_modified )
			map.put("middle_name",middle_name);
		if ( given_name_is_modified )
			map.put("given_name",given_name);
		if ( family_name_alt1_is_modified )
			map.put("family_name_alt1",family_name_alt1);
		if ( middle_name_alt1_is_modified )
			map.put("middle_name_alt1",middle_name_alt1);
		if ( given_name_alt1_is_modified )
			map.put("given_name_alt1",given_name_alt1);
		if ( family_name_alt2_is_modified )
			map.put("family_name_alt2",family_name_alt2);
		if ( middle_name_alt2_is_modified )
			map.put("middle_name_alt2",middle_name_alt2);
		if ( given_name_alt2_is_modified )
			map.put("given_name_alt2",given_name_alt2);
		if ( trading_password_is_modified )
			map.put("trading_password",trading_password);
		if ( account_order_flag_is_modified )
			map.put("account_order_flag",account_order_flag);
		if ( department_code_is_modified )
			map.put("department_code",department_code);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( trader_code_is_set )
				map.put("trader_code",trader_code);
			if ( trader_type_is_set )
				map.put("trader_type",trader_type);
			if ( login_id_is_set )
				map.put("login_id",new Long(login_id));
			if ( family_name_is_set )
				map.put("family_name",family_name);
			map.put("middle_name",middle_name);
			if ( given_name_is_set )
				map.put("given_name",given_name);
			map.put("family_name_alt1",family_name_alt1);
			map.put("middle_name_alt1",middle_name_alt1);
			map.put("given_name_alt1",given_name_alt1);
			map.put("family_name_alt2",family_name_alt2);
			map.put("middle_name_alt2",middle_name_alt2);
			map.put("given_name_alt2",given_name_alt2);
			map.put("trading_password",trading_password);
			map.put("account_order_flag",account_order_flag);
			map.put("department_code",department_code);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>trader_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTraderId()
  {
    return trader_id;
  }


  /** 
   * <em>trader_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderIdIsSet() {
    return trader_id_is_set;
  }


  /** 
   * <em>trader_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderIdIsModified() {
    return trader_id_is_modified;
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
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBranchId()
  {
    return branch_id;
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
   * <em>trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTraderCode()
  {
    return trader_code;
  }


  /** 
   * <em>trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsSet() {
    return trader_code_is_set;
  }


  /** 
   * <em>trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsModified() {
    return trader_code_is_modified;
  }


  /** 
   * <em>trader_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum getTraderType()
  {
    return trader_type;
  }


  /** 
   * <em>trader_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderTypeIsSet() {
    return trader_type_is_set;
  }


  /** 
   * <em>trader_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderTypeIsModified() {
    return trader_type_is_modified;
  }


  /** 
   * <em>login_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getLoginId()
  {
    return login_id;
  }


  /** 
   * <em>login_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginIdIsSet() {
    return login_id_is_set;
  }


  /** 
   * <em>login_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginIdIsModified() {
    return login_id_is_modified;
  }


  /** 
   * <em>family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyName()
  {
    return family_name;
  }


  /** 
   * <em>family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameIsSet() {
    return family_name_is_set;
  }


  /** 
   * <em>family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameIsModified() {
    return family_name_is_modified;
  }


  /** 
   * <em>middle_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMiddleName()
  {
    return middle_name;
  }


  /** 
   * <em>middle_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiddleNameIsSet() {
    return middle_name_is_set;
  }


  /** 
   * <em>middle_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiddleNameIsModified() {
    return middle_name_is_modified;
  }


  /** 
   * <em>given_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGivenName()
  {
    return given_name;
  }


  /** 
   * <em>given_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGivenNameIsSet() {
    return given_name_is_set;
  }


  /** 
   * <em>given_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGivenNameIsModified() {
    return given_name_is_modified;
  }


  /** 
   * <em>family_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyNameAlt1()
  {
    return family_name_alt1;
  }


  /** 
   * <em>family_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameAlt1IsSet() {
    return family_name_alt1_is_set;
  }


  /** 
   * <em>family_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameAlt1IsModified() {
    return family_name_alt1_is_modified;
  }


  /** 
   * <em>middle_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMiddleNameAlt1()
  {
    return middle_name_alt1;
  }


  /** 
   * <em>middle_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiddleNameAlt1IsSet() {
    return middle_name_alt1_is_set;
  }


  /** 
   * <em>middle_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiddleNameAlt1IsModified() {
    return middle_name_alt1_is_modified;
  }


  /** 
   * <em>given_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGivenNameAlt1()
  {
    return given_name_alt1;
  }


  /** 
   * <em>given_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGivenNameAlt1IsSet() {
    return given_name_alt1_is_set;
  }


  /** 
   * <em>given_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGivenNameAlt1IsModified() {
    return given_name_alt1_is_modified;
  }


  /** 
   * <em>family_name_alt2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyNameAlt2()
  {
    return family_name_alt2;
  }


  /** 
   * <em>family_name_alt2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameAlt2IsSet() {
    return family_name_alt2_is_set;
  }


  /** 
   * <em>family_name_alt2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameAlt2IsModified() {
    return family_name_alt2_is_modified;
  }


  /** 
   * <em>middle_name_alt2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMiddleNameAlt2()
  {
    return middle_name_alt2;
  }


  /** 
   * <em>middle_name_alt2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiddleNameAlt2IsSet() {
    return middle_name_alt2_is_set;
  }


  /** 
   * <em>middle_name_alt2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiddleNameAlt2IsModified() {
    return middle_name_alt2_is_modified;
  }


  /** 
   * <em>given_name_alt2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGivenNameAlt2()
  {
    return given_name_alt2;
  }


  /** 
   * <em>given_name_alt2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGivenNameAlt2IsSet() {
    return given_name_alt2_is_set;
  }


  /** 
   * <em>given_name_alt2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGivenNameAlt2IsModified() {
    return given_name_alt2_is_modified;
  }


  /** 
   * <em>trading_password</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradingPassword()
  {
    return trading_password;
  }


  /** 
   * <em>trading_password</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingPasswordIsSet() {
    return trading_password_is_set;
  }


  /** 
   * <em>trading_password</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingPasswordIsModified() {
    return trading_password_is_modified;
  }


  /** 
   * <em>account_order_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOrderFlag()
  {
    return account_order_flag;
  }


  /** 
   * <em>account_order_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOrderFlagIsSet() {
    return account_order_flag_is_set;
  }


  /** 
   * <em>account_order_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOrderFlagIsModified() {
    return account_order_flag_is_modified;
  }


  /** 
   * <em>department_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepartmentCode()
  {
    return department_code;
  }


  /** 
   * <em>department_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepartmentCodeIsSet() {
    return department_code_is_set;
  }


  /** 
   * <em>department_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepartmentCodeIsModified() {
    return department_code_is_modified;
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
    return new TraderPK(trader_id);
  }


  /** 
   * <em>trader_id</em>カラムの値を設定します。 
   *
   * @@param p_traderId <em>trader_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setTraderId( long p_traderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_id = p_traderId;
    trader_id_is_set = true;
    trader_id_is_modified = true;
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
   * <em>branch_id</em>カラムの値を設定します。 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
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
   * <em>trader_code</em>カラムの値を設定します。 
   *
   * @@param p_traderCode <em>trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setTraderCode( String p_traderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_code = p_traderCode;
    trader_code_is_set = true;
    trader_code_is_modified = true;
  }


  /** 
   * <em>trader_type</em>カラムの値を設定します。 
   *
   * @@param p_traderType <em>trader_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum値 
   */
  public final void setTraderType( com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum p_traderType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_type = p_traderType;
    trader_type_is_set = true;
    trader_type_is_modified = true;
  }


  /** 
   * <em>login_id</em>カラムの値を設定します。 
   *
   * @@param p_loginId <em>login_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setLoginId( long p_loginId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    login_id = p_loginId;
    login_id_is_set = true;
    login_id_is_modified = true;
  }


  /** 
   * <em>family_name</em>カラムの値を設定します。 
   *
   * @@param p_familyName <em>family_name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setFamilyName( String p_familyName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_name = p_familyName;
    family_name_is_set = true;
    family_name_is_modified = true;
  }


  /** 
   * <em>middle_name</em>カラムの値を設定します。 
   *
   * @@param p_middleName <em>middle_name</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setMiddleName( String p_middleName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    middle_name = p_middleName;
    middle_name_is_set = true;
    middle_name_is_modified = true;
  }


  /** 
   * <em>given_name</em>カラムの値を設定します。 
   *
   * @@param p_givenName <em>given_name</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setGivenName( String p_givenName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    given_name = p_givenName;
    given_name_is_set = true;
    given_name_is_modified = true;
  }


  /** 
   * <em>family_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_familyNameAlt1 <em>family_name_alt1</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setFamilyNameAlt1( String p_familyNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_name_alt1 = p_familyNameAlt1;
    family_name_alt1_is_set = true;
    family_name_alt1_is_modified = true;
  }


  /** 
   * <em>middle_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_middleNameAlt1 <em>middle_name_alt1</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setMiddleNameAlt1( String p_middleNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    middle_name_alt1 = p_middleNameAlt1;
    middle_name_alt1_is_set = true;
    middle_name_alt1_is_modified = true;
  }


  /** 
   * <em>given_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_givenNameAlt1 <em>given_name_alt1</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setGivenNameAlt1( String p_givenNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    given_name_alt1 = p_givenNameAlt1;
    given_name_alt1_is_set = true;
    given_name_alt1_is_modified = true;
  }


  /** 
   * <em>family_name_alt2</em>カラムの値を設定します。 
   *
   * @@param p_familyNameAlt2 <em>family_name_alt2</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setFamilyNameAlt2( String p_familyNameAlt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_name_alt2 = p_familyNameAlt2;
    family_name_alt2_is_set = true;
    family_name_alt2_is_modified = true;
  }


  /** 
   * <em>middle_name_alt2</em>カラムの値を設定します。 
   *
   * @@param p_middleNameAlt2 <em>middle_name_alt2</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setMiddleNameAlt2( String p_middleNameAlt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    middle_name_alt2 = p_middleNameAlt2;
    middle_name_alt2_is_set = true;
    middle_name_alt2_is_modified = true;
  }


  /** 
   * <em>given_name_alt2</em>カラムの値を設定します。 
   *
   * @@param p_givenNameAlt2 <em>given_name_alt2</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setGivenNameAlt2( String p_givenNameAlt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    given_name_alt2 = p_givenNameAlt2;
    given_name_alt2_is_set = true;
    given_name_alt2_is_modified = true;
  }


  /** 
   * <em>trading_password</em>カラムの値を設定します。 
   *
   * @@param p_tradingPassword <em>trading_password</em>カラムの値をあらわす48桁以下のString値 
   */
  public final void setTradingPassword( String p_tradingPassword )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_password = p_tradingPassword;
    trading_password_is_set = true;
    trading_password_is_modified = true;
  }


  /** 
   * <em>account_order_flag</em>カラムの値を設定します。 
   *
   * @@param p_accountOrderFlag <em>account_order_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOrderFlag( String p_accountOrderFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_order_flag = p_accountOrderFlag;
    account_order_flag_is_set = true;
    account_order_flag_is_modified = true;
  }


  /** 
   * <em>department_code</em>カラムの値を設定します。 
   *
   * @@param p_departmentCode <em>department_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setDepartmentCode( String p_departmentCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    department_code = p_departmentCode;
    department_code_is_set = true;
    department_code_is_modified = true;
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
                if ( name.equals("account_order_flag") ) {
                    return this.account_order_flag;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                else if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("department_code") ) {
                    return this.department_code;
                }
                break;
            case 'f':
                if ( name.equals("family_name") ) {
                    return this.family_name;
                }
                else if ( name.equals("family_name_alt1") ) {
                    return this.family_name_alt1;
                }
                else if ( name.equals("family_name_alt2") ) {
                    return this.family_name_alt2;
                }
                break;
            case 'g':
                if ( name.equals("given_name") ) {
                    return this.given_name;
                }
                else if ( name.equals("given_name_alt1") ) {
                    return this.given_name_alt1;
                }
                else if ( name.equals("given_name_alt2") ) {
                    return this.given_name_alt2;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("login_id") ) {
                    return new Long( login_id );
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("middle_name") ) {
                    return this.middle_name;
                }
                else if ( name.equals("middle_name_alt1") ) {
                    return this.middle_name_alt1;
                }
                else if ( name.equals("middle_name_alt2") ) {
                    return this.middle_name_alt2;
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    return new Long( trader_id );
                }
                else if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("trader_type") ) {
                    return this.trader_type;
                }
                else if ( name.equals("trading_password") ) {
                    return this.trading_password;
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
                if ( name.equals("account_order_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_order_flag' must be String: '"+value+"' is not." );
                    this.account_order_flag = (String) value;
                    if (this.account_order_flag_is_set)
                        this.account_order_flag_is_modified = true;
                    this.account_order_flag_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                else if ( name.equals("branch_code") ) {
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
                if ( name.equals("department_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'department_code' must be String: '"+value+"' is not." );
                    this.department_code = (String) value;
                    if (this.department_code_is_set)
                        this.department_code_is_modified = true;
                    this.department_code_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("family_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_name' must be String: '"+value+"' is not." );
                    this.family_name = (String) value;
                    if (this.family_name_is_set)
                        this.family_name_is_modified = true;
                    this.family_name_is_set = true;
                    return;
                }
                else if ( name.equals("family_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_name_alt1' must be String: '"+value+"' is not." );
                    this.family_name_alt1 = (String) value;
                    if (this.family_name_alt1_is_set)
                        this.family_name_alt1_is_modified = true;
                    this.family_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("family_name_alt2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_name_alt2' must be String: '"+value+"' is not." );
                    this.family_name_alt2 = (String) value;
                    if (this.family_name_alt2_is_set)
                        this.family_name_alt2_is_modified = true;
                    this.family_name_alt2_is_set = true;
                    return;
                }
                break;
            case 'g':
                if ( name.equals("given_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'given_name' must be String: '"+value+"' is not." );
                    this.given_name = (String) value;
                    if (this.given_name_is_set)
                        this.given_name_is_modified = true;
                    this.given_name_is_set = true;
                    return;
                }
                else if ( name.equals("given_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'given_name_alt1' must be String: '"+value+"' is not." );
                    this.given_name_alt1 = (String) value;
                    if (this.given_name_alt1_is_set)
                        this.given_name_alt1_is_modified = true;
                    this.given_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("given_name_alt2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'given_name_alt2' must be String: '"+value+"' is not." );
                    this.given_name_alt2 = (String) value;
                    if (this.given_name_alt2_is_set)
                        this.given_name_alt2_is_modified = true;
                    this.given_name_alt2_is_set = true;
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
                if ( name.equals("login_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'login_id' must be Long: '"+value+"' is not." );
                    this.login_id = ((Long) value).longValue();
                    if (this.login_id_is_set)
                        this.login_id_is_modified = true;
                    this.login_id_is_set = true;
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
                if ( name.equals("middle_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'middle_name' must be String: '"+value+"' is not." );
                    this.middle_name = (String) value;
                    if (this.middle_name_is_set)
                        this.middle_name_is_modified = true;
                    this.middle_name_is_set = true;
                    return;
                }
                else if ( name.equals("middle_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'middle_name_alt1' must be String: '"+value+"' is not." );
                    this.middle_name_alt1 = (String) value;
                    if (this.middle_name_alt1_is_set)
                        this.middle_name_alt1_is_modified = true;
                    this.middle_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("middle_name_alt2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'middle_name_alt2' must be String: '"+value+"' is not." );
                    this.middle_name_alt2 = (String) value;
                    if (this.middle_name_alt2_is_set)
                        this.middle_name_alt2_is_modified = true;
                    this.middle_name_alt2_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'trader_id' must be Long: '"+value+"' is not." );
                    this.trader_id = ((Long) value).longValue();
                    if (this.trader_id_is_set)
                        this.trader_id_is_modified = true;
                    this.trader_id_is_set = true;
                    return;
                }
                else if ( name.equals("trader_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trader_code' must be String: '"+value+"' is not." );
                    this.trader_code = (String) value;
                    if (this.trader_code_is_set)
                        this.trader_code_is_modified = true;
                    this.trader_code_is_set = true;
                    return;
                }
                else if ( name.equals("trader_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum) )
                        throw new IllegalArgumentException( "value for 'trader_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum: '"+value+"' is not." );
                    this.trader_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum) value;
                    if (this.trader_type_is_set)
                        this.trader_type_is_modified = true;
                    this.trader_type_is_set = true;
                    return;
                }
                else if ( name.equals("trading_password") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_password' must be String: '"+value+"' is not." );
                    this.trading_password = (String) value;
                    if (this.trading_password_is_set)
                        this.trading_password_is_modified = true;
                    this.trading_password_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
