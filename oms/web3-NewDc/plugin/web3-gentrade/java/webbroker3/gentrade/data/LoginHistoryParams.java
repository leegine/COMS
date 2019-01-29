head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.30.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	LoginHistoryParams.java;


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
 * login_historyテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link LoginHistoryRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link LoginHistoryParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link LoginHistoryParams}が{@@link LoginHistoryRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see LoginHistoryPK 
 * @@see LoginHistoryRow 
 */
public class LoginHistoryParams extends Params implements LoginHistoryRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "login_history";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = LoginHistoryRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return LoginHistoryRow.TYPE;
  }


  /** 
   * <em>login_history_id</em>カラムの値 
   */
  public  long  login_history_id;

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
   * <em>discrimination_code</em>カラムの値 
   */
  public  String  discrimination_code;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>ip_address</em>カラムの値 
   */
  public  String  ip_address;

  /** 
   * <em>order_root_div</em>カラムの値 
   */
  public  String  order_root_div;

  /** 
   * <em>order_channel</em>カラムの値 
   */
  public  String  order_channel;

  /** 
   * <em>login_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  login_timestamp;

  /** 
   * <em>login_failure</em>カラムの値 
   */
  public  String  login_failure;

  /** 
   * <em>login_error_info</em>カラムの値 
   */
  public  String  login_error_info;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean login_history_id_is_set = false;
  private boolean login_history_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean discrimination_code_is_set = false;
  private boolean discrimination_code_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean ip_address_is_set = false;
  private boolean ip_address_is_modified = false;
  private boolean order_root_div_is_set = false;
  private boolean order_root_div_is_modified = false;
  private boolean order_channel_is_set = false;
  private boolean order_channel_is_modified = false;
  private boolean login_timestamp_is_set = false;
  private boolean login_timestamp_is_modified = false;
  private boolean login_failure_is_set = false;
  private boolean login_failure_is_modified = false;
  private boolean login_error_info_is_set = false;
  private boolean login_error_info_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "login_history_id=" + login_history_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "discrimination_code=" +discrimination_code
      + "," + "account_id=" +account_id
      + "," + "ip_address=" +ip_address
      + "," + "order_root_div=" +order_root_div
      + "," + "order_channel=" +order_channel
      + "," + "login_timestamp=" +login_timestamp
      + "," + "login_failure=" +login_failure
      + "," + "login_error_info=" +login_error_info
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のLoginHistoryParamsオブジェクトを作成します。 
   */
  public LoginHistoryParams() {
    login_history_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のLoginHistoryRowオブジェクトの値を利用してLoginHistoryParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するLoginHistoryRowオブジェクト 
   */
  public LoginHistoryParams( LoginHistoryRow p_row )
  {
    login_history_id = p_row.getLoginHistoryId();
    login_history_id_is_set = p_row.getLoginHistoryIdIsSet();
    login_history_id_is_modified = p_row.getLoginHistoryIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    discrimination_code = p_row.getDiscriminationCode();
    discrimination_code_is_set = p_row.getDiscriminationCodeIsSet();
    discrimination_code_is_modified = p_row.getDiscriminationCodeIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    ip_address = p_row.getIpAddress();
    ip_address_is_set = p_row.getIpAddressIsSet();
    ip_address_is_modified = p_row.getIpAddressIsModified();
    order_root_div = p_row.getOrderRootDiv();
    order_root_div_is_set = p_row.getOrderRootDivIsSet();
    order_root_div_is_modified = p_row.getOrderRootDivIsModified();
    order_channel = p_row.getOrderChannel();
    order_channel_is_set = p_row.getOrderChannelIsSet();
    order_channel_is_modified = p_row.getOrderChannelIsModified();
    login_timestamp = p_row.getLoginTimestamp();
    login_timestamp_is_set = p_row.getLoginTimestampIsSet();
    login_timestamp_is_modified = p_row.getLoginTimestampIsModified();
    login_failure = p_row.getLoginFailure();
    login_failure_is_set = p_row.getLoginFailureIsSet();
    login_failure_is_modified = p_row.getLoginFailureIsModified();
    login_error_info = p_row.getLoginErrorInfo();
    login_error_info_is_set = p_row.getLoginErrorInfoIsSet();
    login_error_info_is_modified = p_row.getLoginErrorInfoIsModified();
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
      discrimination_code_is_set = true;
      discrimination_code_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      ip_address_is_set = true;
      ip_address_is_modified = true;
      order_root_div_is_set = true;
      order_root_div_is_modified = true;
      order_channel_is_set = true;
      order_channel_is_modified = true;
      login_timestamp_is_set = true;
      login_timestamp_is_modified = true;
      login_failure_is_set = true;
      login_failure_is_modified = true;
      login_error_info_is_set = true;
      login_error_info_is_modified = true;
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
    if ( !( other instanceof LoginHistoryRow ) )
       return false;
    return fieldsEqual( (LoginHistoryRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のLoginHistoryRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( LoginHistoryRow row )
  {
    if ( login_history_id != row.getLoginHistoryId() )
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
    if ( discrimination_code == null ) {
      if ( row.getDiscriminationCode() != null )
        return false;
    } else if ( !discrimination_code.equals( row.getDiscriminationCode() ) ) {
        return false;
    }
    if ( account_id != row.getAccountId() )
      return false;
    if ( ip_address == null ) {
      if ( row.getIpAddress() != null )
        return false;
    } else if ( !ip_address.equals( row.getIpAddress() ) ) {
        return false;
    }
    if ( order_root_div == null ) {
      if ( row.getOrderRootDiv() != null )
        return false;
    } else if ( !order_root_div.equals( row.getOrderRootDiv() ) ) {
        return false;
    }
    if ( order_channel == null ) {
      if ( row.getOrderChannel() != null )
        return false;
    } else if ( !order_channel.equals( row.getOrderChannel() ) ) {
        return false;
    }
    if ( login_timestamp == null ) {
      if ( row.getLoginTimestamp() != null )
        return false;
    } else if ( !login_timestamp.equals( row.getLoginTimestamp() ) ) {
        return false;
    }
    if ( login_failure == null ) {
      if ( row.getLoginFailure() != null )
        return false;
    } else if ( !login_failure.equals( row.getLoginFailure() ) ) {
        return false;
    }
    if ( login_error_info == null ) {
      if ( row.getLoginErrorInfo() != null )
        return false;
    } else if ( !login_error_info.equals( row.getLoginErrorInfo() ) ) {
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
      return  ((int) login_history_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (discrimination_code!=null? discrimination_code.hashCode(): 0) 
        + ((int) account_id)
        + (ip_address!=null? ip_address.hashCode(): 0) 
        + (order_root_div!=null? order_root_div.hashCode(): 0) 
        + (order_channel!=null? order_channel.hashCode(): 0) 
        + (login_timestamp!=null? login_timestamp.hashCode(): 0) 
        + (login_failure!=null? login_failure.hashCode(): 0) 
        + (login_error_info!=null? login_error_info.hashCode(): 0) 
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
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !order_root_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_root_div' must be set before inserting.");
		if ( !order_channel_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_channel' must be set before inserting.");
		if ( !login_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'login_timestamp' must be set before inserting.");
		if ( !login_failure_is_set )
			throw new IllegalArgumentException("non-nullable field 'login_failure' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("login_history_id",new Long(login_history_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( discrimination_code != null )
			map.put("discrimination_code",discrimination_code);
		map.put("account_id",new Long(account_id));
		if ( ip_address != null )
			map.put("ip_address",ip_address);
		map.put("order_root_div",order_root_div);
		map.put("order_channel",order_channel);
		map.put("login_timestamp",login_timestamp);
		map.put("login_failure",login_failure);
		if ( login_error_info != null )
			map.put("login_error_info",login_error_info);
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
		if ( discrimination_code_is_modified )
			map.put("discrimination_code",discrimination_code);
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( ip_address_is_modified )
			map.put("ip_address",ip_address);
		if ( order_root_div_is_modified )
			map.put("order_root_div",order_root_div);
		if ( order_channel_is_modified )
			map.put("order_channel",order_channel);
		if ( login_timestamp_is_modified )
			map.put("login_timestamp",login_timestamp);
		if ( login_failure_is_modified )
			map.put("login_failure",login_failure);
		if ( login_error_info_is_modified )
			map.put("login_error_info",login_error_info);
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
			map.put("discrimination_code",discrimination_code);
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			map.put("ip_address",ip_address);
			if ( order_root_div_is_set )
				map.put("order_root_div",order_root_div);
			if ( order_channel_is_set )
				map.put("order_channel",order_channel);
			if ( login_timestamp_is_set )
				map.put("login_timestamp",login_timestamp);
			if ( login_failure_is_set )
				map.put("login_failure",login_failure);
			map.put("login_error_info",login_error_info);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>login_history_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getLoginHistoryId()
  {
    return login_history_id;
  }


  /** 
   * <em>login_history_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginHistoryIdIsSet() {
    return login_history_id_is_set;
  }


  /** 
   * <em>login_history_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginHistoryIdIsModified() {
    return login_history_id_is_modified;
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
   * <em>discrimination_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDiscriminationCode()
  {
    return discrimination_code;
  }


  /** 
   * <em>discrimination_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDiscriminationCodeIsSet() {
    return discrimination_code_is_set;
  }


  /** 
   * <em>discrimination_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDiscriminationCodeIsModified() {
    return discrimination_code_is_modified;
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
   * <em>ip_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIpAddress()
  {
    return ip_address;
  }


  /** 
   * <em>ip_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIpAddressIsSet() {
    return ip_address_is_set;
  }


  /** 
   * <em>ip_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIpAddressIsModified() {
    return ip_address_is_modified;
  }


  /** 
   * <em>order_root_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRootDiv()
  {
    return order_root_div;
  }


  /** 
   * <em>order_root_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRootDivIsSet() {
    return order_root_div_is_set;
  }


  /** 
   * <em>order_root_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRootDivIsModified() {
    return order_root_div_is_modified;
  }


  /** 
   * <em>order_channel</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderChannel()
  {
    return order_channel;
  }


  /** 
   * <em>order_channel</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderChannelIsSet() {
    return order_channel_is_set;
  }


  /** 
   * <em>order_channel</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderChannelIsModified() {
    return order_channel_is_modified;
  }


  /** 
   * <em>login_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLoginTimestamp()
  {
    return login_timestamp;
  }


  /** 
   * <em>login_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginTimestampIsSet() {
    return login_timestamp_is_set;
  }


  /** 
   * <em>login_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginTimestampIsModified() {
    return login_timestamp_is_modified;
  }


  /** 
   * <em>login_failure</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLoginFailure()
  {
    return login_failure;
  }


  /** 
   * <em>login_failure</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginFailureIsSet() {
    return login_failure_is_set;
  }


  /** 
   * <em>login_failure</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginFailureIsModified() {
    return login_failure_is_modified;
  }


  /** 
   * <em>login_error_info</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLoginErrorInfo()
  {
    return login_error_info;
  }


  /** 
   * <em>login_error_info</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginErrorInfoIsSet() {
    return login_error_info_is_set;
  }


  /** 
   * <em>login_error_info</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoginErrorInfoIsModified() {
    return login_error_info_is_modified;
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
    return new LoginHistoryPK(login_history_id);
  }


  /** 
   * <em>login_history_id</em>カラムの値を設定します。 
   *
   * @@param p_loginHistoryId <em>login_history_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setLoginHistoryId( long p_loginHistoryId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    login_history_id = p_loginHistoryId;
    login_history_id_is_set = true;
    login_history_id_is_modified = true;
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
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす12桁以下のString値 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>discrimination_code</em>カラムの値を設定します。 
   *
   * @@param p_discriminationCode <em>discrimination_code</em>カラムの値をあらわす64桁以下のString値 
   */
  public final void setDiscriminationCode( String p_discriminationCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    discrimination_code = p_discriminationCode;
    discrimination_code_is_set = true;
    discrimination_code_is_modified = true;
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
   * <em>ip_address</em>カラムの値を設定します。 
   *
   * @@param p_ipAddress <em>ip_address</em>カラムの値をあらわす15桁以下のString値 
   */
  public final void setIpAddress( String p_ipAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ip_address = p_ipAddress;
    ip_address_is_set = true;
    ip_address_is_modified = true;
  }


  /** 
   * <em>order_root_div</em>カラムの値を設定します。 
   *
   * @@param p_orderRootDiv <em>order_root_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderRootDiv( String p_orderRootDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_root_div = p_orderRootDiv;
    order_root_div_is_set = true;
    order_root_div_is_modified = true;
  }


  /** 
   * <em>order_channel</em>カラムの値を設定します。 
   *
   * @@param p_orderChannel <em>order_channel</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderChannel( String p_orderChannel )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_channel = p_orderChannel;
    order_channel_is_set = true;
    order_channel_is_modified = true;
  }


  /** 
   * <em>login_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_loginTimestamp <em>login_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLoginTimestamp( java.sql.Timestamp p_loginTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    login_timestamp = p_loginTimestamp;
    login_timestamp_is_set = true;
    login_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>login_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLoginTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    login_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    login_timestamp_is_set = true;
    login_timestamp_is_modified = true;
  }


  /** 
   * <em>login_failure</em>カラムの値を設定します。 
   *
   * @@param p_loginFailure <em>login_failure</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setLoginFailure( String p_loginFailure )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    login_failure = p_loginFailure;
    login_failure_is_set = true;
    login_failure_is_modified = true;
  }


  /** 
   * <em>login_error_info</em>カラムの値を設定します。 
   *
   * @@param p_loginErrorInfo <em>login_error_info</em>カラムの値をあらわす256桁以下のString値 
   */
  public final void setLoginErrorInfo( String p_loginErrorInfo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    login_error_info = p_loginErrorInfo;
    login_error_info_is_set = true;
    login_error_info_is_modified = true;
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
                else if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                break;
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
            case 'd':
                if ( name.equals("discrimination_code") ) {
                    return this.discrimination_code;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("ip_address") ) {
                    return this.ip_address;
                }
                break;
            case 'l':
                if ( name.equals("login_history_id") ) {
                    return new Long( login_history_id );
                }
                else if ( name.equals("login_timestamp") ) {
                    return this.login_timestamp;
                }
                else if ( name.equals("login_failure") ) {
                    return this.login_failure;
                }
                else if ( name.equals("login_error_info") ) {
                    return this.login_error_info;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_root_div") ) {
                    return this.order_root_div;
                }
                else if ( name.equals("order_channel") ) {
                    return this.order_channel;
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
                else if ( name.equals("account_id") ) {
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
                if ( name.equals("discrimination_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'discrimination_code' must be String: '"+value+"' is not." );
                    this.discrimination_code = (String) value;
                    if (this.discrimination_code_is_set)
                        this.discrimination_code_is_modified = true;
                    this.discrimination_code_is_set = true;
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
                else if ( name.equals("ip_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ip_address' must be String: '"+value+"' is not." );
                    this.ip_address = (String) value;
                    if (this.ip_address_is_set)
                        this.ip_address_is_modified = true;
                    this.ip_address_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("login_history_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'login_history_id' must be Long: '"+value+"' is not." );
                    this.login_history_id = ((Long) value).longValue();
                    if (this.login_history_id_is_set)
                        this.login_history_id_is_modified = true;
                    this.login_history_id_is_set = true;
                    return;
                }
                else if ( name.equals("login_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'login_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.login_timestamp = (java.sql.Timestamp) value;
                    if (this.login_timestamp_is_set)
                        this.login_timestamp_is_modified = true;
                    this.login_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("login_failure") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'login_failure' must be String: '"+value+"' is not." );
                    this.login_failure = (String) value;
                    if (this.login_failure_is_set)
                        this.login_failure_is_modified = true;
                    this.login_failure_is_set = true;
                    return;
                }
                else if ( name.equals("login_error_info") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'login_error_info' must be String: '"+value+"' is not." );
                    this.login_error_info = (String) value;
                    if (this.login_error_info_is_set)
                        this.login_error_info_is_modified = true;
                    this.login_error_info_is_set = true;
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
            case 'o':
                if ( name.equals("order_root_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_root_div' must be String: '"+value+"' is not." );
                    this.order_root_div = (String) value;
                    if (this.order_root_div_is_set)
                        this.order_root_div_is_modified = true;
                    this.order_root_div_is_set = true;
                    return;
                }
                else if ( name.equals("order_channel") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_channel' must be String: '"+value+"' is not." );
                    this.order_channel = (String) value;
                    if (this.order_channel_is_set)
                        this.order_channel_is_modified = true;
                    this.order_channel_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
