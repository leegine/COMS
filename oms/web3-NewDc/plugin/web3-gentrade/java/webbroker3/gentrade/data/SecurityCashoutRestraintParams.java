head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.42.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SecurityCashoutRestraintParams.java;


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
 * security_cashout_restraintテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SecurityCashoutRestraintRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SecurityCashoutRestraintParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SecurityCashoutRestraintParams}が{@@link SecurityCashoutRestraintRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SecurityCashoutRestraintPK 
 * @@see SecurityCashoutRestraintRow 
 */
public class SecurityCashoutRestraintParams extends Params implements SecurityCashoutRestraintRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "security_cashout_restraint";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SecurityCashoutRestraintRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SecurityCashoutRestraintRow.TYPE;
  }


  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

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
   * <em>use_enable_limit</em>カラムの値 
   */
  public  Long  use_enable_limit;

  /** 
   * <em>cashout_restraint</em>カラムの値 
   */
  public  Long  cashout_restraint;

  /** 
   * <em>cashout_enablie_amount</em>カラムの値 
   */
  public  Long  cashout_enablie_amount;

  /** 
   * <em>agree_cancel_flg</em>カラムの値 
   */
  public  String  agree_cancel_flg;

  /** 
   * <em>amount_lock_flg</em>カラムの値 
   */
  public  String  amount_lock_flg;

  /** 
   * <em>remark</em>カラムの値 
   */
  public  String  remark;

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

  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean use_enable_limit_is_set = false;
  private boolean use_enable_limit_is_modified = false;
  private boolean cashout_restraint_is_set = false;
  private boolean cashout_restraint_is_modified = false;
  private boolean cashout_enablie_amount_is_set = false;
  private boolean cashout_enablie_amount_is_modified = false;
  private boolean agree_cancel_flg_is_set = false;
  private boolean agree_cancel_flg_is_modified = false;
  private boolean amount_lock_flg_is_set = false;
  private boolean amount_lock_flg_is_modified = false;
  private boolean remark_is_set = false;
  private boolean remark_is_modified = false;
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
       + "account_id=" + account_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "use_enable_limit=" +use_enable_limit
      + "," + "cashout_restraint=" +cashout_restraint
      + "," + "cashout_enablie_amount=" +cashout_enablie_amount
      + "," + "agree_cancel_flg=" +agree_cancel_flg
      + "," + "amount_lock_flg=" +amount_lock_flg
      + "," + "remark=" +remark
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のSecurityCashoutRestraintParamsオブジェクトを作成します。 
   */
  public SecurityCashoutRestraintParams() {
    account_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSecurityCashoutRestraintRowオブジェクトの値を利用してSecurityCashoutRestraintParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSecurityCashoutRestraintRowオブジェクト 
   */
  public SecurityCashoutRestraintParams( SecurityCashoutRestraintRow p_row )
  {
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    if ( !p_row.getUseEnableLimitIsNull() )
      use_enable_limit = new Long( p_row.getUseEnableLimit() );
    use_enable_limit_is_set = p_row.getUseEnableLimitIsSet();
    use_enable_limit_is_modified = p_row.getUseEnableLimitIsModified();
    if ( !p_row.getCashoutRestraintIsNull() )
      cashout_restraint = new Long( p_row.getCashoutRestraint() );
    cashout_restraint_is_set = p_row.getCashoutRestraintIsSet();
    cashout_restraint_is_modified = p_row.getCashoutRestraintIsModified();
    if ( !p_row.getCashoutEnablieAmountIsNull() )
      cashout_enablie_amount = new Long( p_row.getCashoutEnablieAmount() );
    cashout_enablie_amount_is_set = p_row.getCashoutEnablieAmountIsSet();
    cashout_enablie_amount_is_modified = p_row.getCashoutEnablieAmountIsModified();
    agree_cancel_flg = p_row.getAgreeCancelFlg();
    agree_cancel_flg_is_set = p_row.getAgreeCancelFlgIsSet();
    agree_cancel_flg_is_modified = p_row.getAgreeCancelFlgIsModified();
    amount_lock_flg = p_row.getAmountLockFlg();
    amount_lock_flg_is_set = p_row.getAmountLockFlgIsSet();
    amount_lock_flg_is_modified = p_row.getAmountLockFlgIsModified();
    remark = p_row.getRemark();
    remark_is_set = p_row.getRemarkIsSet();
    remark_is_modified = p_row.getRemarkIsModified();
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
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      use_enable_limit_is_set = true;
      use_enable_limit_is_modified = true;
      cashout_restraint_is_set = true;
      cashout_restraint_is_modified = true;
      cashout_enablie_amount_is_set = true;
      cashout_enablie_amount_is_modified = true;
      agree_cancel_flg_is_set = true;
      agree_cancel_flg_is_modified = true;
      amount_lock_flg_is_set = true;
      amount_lock_flg_is_modified = true;
      remark_is_set = true;
      remark_is_modified = true;
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
    if ( !( other instanceof SecurityCashoutRestraintRow ) )
       return false;
    return fieldsEqual( (SecurityCashoutRestraintRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSecurityCashoutRestraintRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SecurityCashoutRestraintRow row )
  {
    if ( account_id != row.getAccountId() )
      return false;
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
    if ( use_enable_limit == null ) {
      if ( !row.getUseEnableLimitIsNull() )
        return false;
    } else if ( row.getUseEnableLimitIsNull() || ( use_enable_limit.longValue() != row.getUseEnableLimit() ) ) {
        return false;
    }
    if ( cashout_restraint == null ) {
      if ( !row.getCashoutRestraintIsNull() )
        return false;
    } else if ( row.getCashoutRestraintIsNull() || ( cashout_restraint.longValue() != row.getCashoutRestraint() ) ) {
        return false;
    }
    if ( cashout_enablie_amount == null ) {
      if ( !row.getCashoutEnablieAmountIsNull() )
        return false;
    } else if ( row.getCashoutEnablieAmountIsNull() || ( cashout_enablie_amount.longValue() != row.getCashoutEnablieAmount() ) ) {
        return false;
    }
    if ( agree_cancel_flg == null ) {
      if ( row.getAgreeCancelFlg() != null )
        return false;
    } else if ( !agree_cancel_flg.equals( row.getAgreeCancelFlg() ) ) {
        return false;
    }
    if ( amount_lock_flg == null ) {
      if ( row.getAmountLockFlg() != null )
        return false;
    } else if ( !amount_lock_flg.equals( row.getAmountLockFlg() ) ) {
        return false;
    }
    if ( remark == null ) {
      if ( row.getRemark() != null )
        return false;
    } else if ( !remark.equals( row.getRemark() ) ) {
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
      return  ((int) account_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (use_enable_limit!=null? use_enable_limit.hashCode(): 0) 
        + (cashout_restraint!=null? cashout_restraint.hashCode(): 0) 
        + (cashout_enablie_amount!=null? cashout_enablie_amount.hashCode(): 0) 
        + (agree_cancel_flg!=null? agree_cancel_flg.hashCode(): 0) 
        + (amount_lock_flg!=null? amount_lock_flg.hashCode(): 0) 
        + (remark!=null? remark.hashCode(): 0) 
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
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("account_id",new Long(account_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( use_enable_limit != null )
			map.put("use_enable_limit",use_enable_limit);
		if ( cashout_restraint != null )
			map.put("cashout_restraint",cashout_restraint);
		if ( cashout_enablie_amount != null )
			map.put("cashout_enablie_amount",cashout_enablie_amount);
		if ( agree_cancel_flg != null )
			map.put("agree_cancel_flg",agree_cancel_flg);
		if ( amount_lock_flg != null )
			map.put("amount_lock_flg",amount_lock_flg);
		if ( remark != null )
			map.put("remark",remark);
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
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( use_enable_limit_is_modified )
			map.put("use_enable_limit",use_enable_limit);
		if ( cashout_restraint_is_modified )
			map.put("cashout_restraint",cashout_restraint);
		if ( cashout_enablie_amount_is_modified )
			map.put("cashout_enablie_amount",cashout_enablie_amount);
		if ( agree_cancel_flg_is_modified )
			map.put("agree_cancel_flg",agree_cancel_flg);
		if ( amount_lock_flg_is_modified )
			map.put("amount_lock_flg",amount_lock_flg);
		if ( remark_is_modified )
			map.put("remark",remark);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("use_enable_limit",use_enable_limit);
			map.put("cashout_restraint",cashout_restraint);
			map.put("cashout_enablie_amount",cashout_enablie_amount);
			map.put("agree_cancel_flg",agree_cancel_flg);
			map.put("amount_lock_flg",amount_lock_flg);
			map.put("remark",remark);
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
   * <em>use_enable_limit</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getUseEnableLimit()
  {
    return ( use_enable_limit==null? ((long)0): use_enable_limit.longValue() );
  }


  /** 
   * <em>use_enable_limit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUseEnableLimitIsNull()
  {
    return use_enable_limit == null;
  }


  /** 
   * <em>use_enable_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUseEnableLimitIsSet() {
    return use_enable_limit_is_set;
  }


  /** 
   * <em>use_enable_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUseEnableLimitIsModified() {
    return use_enable_limit_is_modified;
  }


  /** 
   * <em>cashout_restraint</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getCashoutRestraint()
  {
    return ( cashout_restraint==null? ((long)0): cashout_restraint.longValue() );
  }


  /** 
   * <em>cashout_restraint</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashoutRestraintIsNull()
  {
    return cashout_restraint == null;
  }


  /** 
   * <em>cashout_restraint</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashoutRestraintIsSet() {
    return cashout_restraint_is_set;
  }


  /** 
   * <em>cashout_restraint</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashoutRestraintIsModified() {
    return cashout_restraint_is_modified;
  }


  /** 
   * <em>cashout_enablie_amount</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getCashoutEnablieAmount()
  {
    return ( cashout_enablie_amount==null? ((long)0): cashout_enablie_amount.longValue() );
  }


  /** 
   * <em>cashout_enablie_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashoutEnablieAmountIsNull()
  {
    return cashout_enablie_amount == null;
  }


  /** 
   * <em>cashout_enablie_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashoutEnablieAmountIsSet() {
    return cashout_enablie_amount_is_set;
  }


  /** 
   * <em>cashout_enablie_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashoutEnablieAmountIsModified() {
    return cashout_enablie_amount_is_modified;
  }


  /** 
   * <em>agree_cancel_flg</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgreeCancelFlg()
  {
    return agree_cancel_flg;
  }


  /** 
   * <em>agree_cancel_flg</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgreeCancelFlgIsSet() {
    return agree_cancel_flg_is_set;
  }


  /** 
   * <em>agree_cancel_flg</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgreeCancelFlgIsModified() {
    return agree_cancel_flg_is_modified;
  }


  /** 
   * <em>amount_lock_flg</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAmountLockFlg()
  {
    return amount_lock_flg;
  }


  /** 
   * <em>amount_lock_flg</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountLockFlgIsSet() {
    return amount_lock_flg_is_set;
  }


  /** 
   * <em>amount_lock_flg</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountLockFlgIsModified() {
    return amount_lock_flg_is_modified;
  }


  /** 
   * <em>remark</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRemark()
  {
    return remark;
  }


  /** 
   * <em>remark</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkIsSet() {
    return remark_is_set;
  }


  /** 
   * <em>remark</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkIsModified() {
    return remark_is_modified;
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
    return new SecurityCashoutRestraintPK(account_id);
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
   * <em>use_enable_limit</em>カラムの値を設定します。 
   *
   * @@param p_useEnableLimit <em>use_enable_limit</em>カラムの値をあらわす11桁以下のlong値 
   */
  public final void setUseEnableLimit( long p_useEnableLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    use_enable_limit = new Long(p_useEnableLimit);
    use_enable_limit_is_set = true;
    use_enable_limit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>use_enable_limit</em>カラムに値を設定します。 
   */
  public final void setUseEnableLimit( Long p_useEnableLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    use_enable_limit = p_useEnableLimit;
    use_enable_limit_is_set = true;
    use_enable_limit_is_modified = true;
  }


  /** 
   * <em>cashout_restraint</em>カラムの値を設定します。 
   *
   * @@param p_cashoutRestraint <em>cashout_restraint</em>カラムの値をあらわす11桁以下のlong値 
   */
  public final void setCashoutRestraint( long p_cashoutRestraint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cashout_restraint = new Long(p_cashoutRestraint);
    cashout_restraint_is_set = true;
    cashout_restraint_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cashout_restraint</em>カラムに値を設定します。 
   */
  public final void setCashoutRestraint( Long p_cashoutRestraint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cashout_restraint = p_cashoutRestraint;
    cashout_restraint_is_set = true;
    cashout_restraint_is_modified = true;
  }


  /** 
   * <em>cashout_enablie_amount</em>カラムの値を設定します。 
   *
   * @@param p_cashoutEnablieAmount <em>cashout_enablie_amount</em>カラムの値をあらわす11桁以下のlong値 
   */
  public final void setCashoutEnablieAmount( long p_cashoutEnablieAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cashout_enablie_amount = new Long(p_cashoutEnablieAmount);
    cashout_enablie_amount_is_set = true;
    cashout_enablie_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cashout_enablie_amount</em>カラムに値を設定します。 
   */
  public final void setCashoutEnablieAmount( Long p_cashoutEnablieAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cashout_enablie_amount = p_cashoutEnablieAmount;
    cashout_enablie_amount_is_set = true;
    cashout_enablie_amount_is_modified = true;
  }


  /** 
   * <em>agree_cancel_flg</em>カラムの値を設定します。 
   *
   * @@param p_agreeCancelFlg <em>agree_cancel_flg</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAgreeCancelFlg( String p_agreeCancelFlg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agree_cancel_flg = p_agreeCancelFlg;
    agree_cancel_flg_is_set = true;
    agree_cancel_flg_is_modified = true;
  }


  /** 
   * <em>amount_lock_flg</em>カラムの値を設定します。 
   *
   * @@param p_amountLockFlg <em>amount_lock_flg</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAmountLockFlg( String p_amountLockFlg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_lock_flg = p_amountLockFlg;
    amount_lock_flg_is_set = true;
    amount_lock_flg_is_modified = true;
  }


  /** 
   * <em>remark</em>カラムの値を設定します。 
   *
   * @@param p_remark <em>remark</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setRemark( String p_remark )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark = p_remark;
    remark_is_set = true;
    remark_is_modified = true;
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
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("agree_cancel_flg") ) {
                    return this.agree_cancel_flg;
                }
                else if ( name.equals("amount_lock_flg") ) {
                    return this.amount_lock_flg;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("cashout_restraint") ) {
                    return this.cashout_restraint;
                }
                else if ( name.equals("cashout_enablie_amount") ) {
                    return this.cashout_enablie_amount;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
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
            case 'r':
                if ( name.equals("remark") ) {
                    return this.remark;
                }
                break;
            case 'u':
                if ( name.equals("use_enable_limit") ) {
                    return this.use_enable_limit;
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
                else if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("agree_cancel_flg") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agree_cancel_flg' must be String: '"+value+"' is not." );
                    this.agree_cancel_flg = (String) value;
                    if (this.agree_cancel_flg_is_set)
                        this.agree_cancel_flg_is_modified = true;
                    this.agree_cancel_flg_is_set = true;
                    return;
                }
                else if ( name.equals("amount_lock_flg") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'amount_lock_flg' must be String: '"+value+"' is not." );
                    this.amount_lock_flg = (String) value;
                    if (this.amount_lock_flg_is_set)
                        this.amount_lock_flg_is_modified = true;
                    this.amount_lock_flg_is_set = true;
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
                if ( name.equals("cashout_restraint") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'cashout_restraint' must be Long: '"+value+"' is not." );
                    this.cashout_restraint = (Long) value;
                    if (this.cashout_restraint_is_set)
                        this.cashout_restraint_is_modified = true;
                    this.cashout_restraint_is_set = true;
                    return;
                }
                else if ( name.equals("cashout_enablie_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'cashout_enablie_amount' must be Long: '"+value+"' is not." );
                    this.cashout_enablie_amount = (Long) value;
                    if (this.cashout_enablie_amount_is_set)
                        this.cashout_enablie_amount_is_modified = true;
                    this.cashout_enablie_amount_is_set = true;
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
            case 'r':
                if ( name.equals("remark") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'remark' must be String: '"+value+"' is not." );
                    this.remark = (String) value;
                    if (this.remark_is_set)
                        this.remark_is_modified = true;
                    this.remark_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("use_enable_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'use_enable_limit' must be Long: '"+value+"' is not." );
                    this.use_enable_limit = (Long) value;
                    if (this.use_enable_limit_is_set)
                        this.use_enable_limit_is_modified = true;
                    this.use_enable_limit_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
