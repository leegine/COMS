head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.21.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MailAddressRegiParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * mail_address_regiテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link MailAddressRegiRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link MailAddressRegiParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link MailAddressRegiParams}が{@@link MailAddressRegiRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MailAddressRegiPK 
 * @@see MailAddressRegiRow 
 */
public class MailAddressRegiParams extends Params implements MailAddressRegiRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mail_address_regi";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = MailAddressRegiRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return MailAddressRegiRow.TYPE;
  }


  /** 
   * <em>mail_address_regi_id</em>カラムの値 
   */
  public  long  mail_address_regi_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>email_address</em>カラムの値 
   */
  public  String  email_address;

  /** 
   * <em>family_name</em>カラムの値 
   */
  public  String  family_name;

  /** 
   * <em>given_name</em>カラムの値 
   */
  public  String  given_name;

  /** 
   * <em>brokerage_trader_code</em>カラムの値 
   */
  public  String  brokerage_trader_code;

  /** 
   * <em>link_distinction_code</em>カラムの値 
   */
  public  String  link_distinction_code;

  /** 
   * <em>account_div</em>カラムの値 
   */
  public  String  account_div;

  /** 
   * <em>delete_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  delete_flag;

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

  private boolean mail_address_regi_id_is_set = false;
  private boolean mail_address_regi_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean email_address_is_set = false;
  private boolean email_address_is_modified = false;
  private boolean family_name_is_set = false;
  private boolean family_name_is_modified = false;
  private boolean given_name_is_set = false;
  private boolean given_name_is_modified = false;
  private boolean brokerage_trader_code_is_set = false;
  private boolean brokerage_trader_code_is_modified = false;
  private boolean link_distinction_code_is_set = false;
  private boolean link_distinction_code_is_modified = false;
  private boolean account_div_is_set = false;
  private boolean account_div_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
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
       + "mail_address_regi_id=" + mail_address_regi_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "email_address=" +email_address
      + "," + "family_name=" +family_name
      + "," + "given_name=" +given_name
      + "," + "brokerage_trader_code=" +brokerage_trader_code
      + "," + "link_distinction_code=" +link_distinction_code
      + "," + "account_div=" +account_div
      + "," + "delete_flag=" +delete_flag
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のMailAddressRegiParamsオブジェクトを作成します。 
   */
  public MailAddressRegiParams() {
    mail_address_regi_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のMailAddressRegiRowオブジェクトの値を利用してMailAddressRegiParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するMailAddressRegiRowオブジェクト 
   */
  public MailAddressRegiParams( MailAddressRegiRow p_row )
  {
    mail_address_regi_id = p_row.getMailAddressRegiId();
    mail_address_regi_id_is_set = p_row.getMailAddressRegiIdIsSet();
    mail_address_regi_id_is_modified = p_row.getMailAddressRegiIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    email_address = p_row.getEmailAddress();
    email_address_is_set = p_row.getEmailAddressIsSet();
    email_address_is_modified = p_row.getEmailAddressIsModified();
    family_name = p_row.getFamilyName();
    family_name_is_set = p_row.getFamilyNameIsSet();
    family_name_is_modified = p_row.getFamilyNameIsModified();
    given_name = p_row.getGivenName();
    given_name_is_set = p_row.getGivenNameIsSet();
    given_name_is_modified = p_row.getGivenNameIsModified();
    brokerage_trader_code = p_row.getBrokerageTraderCode();
    brokerage_trader_code_is_set = p_row.getBrokerageTraderCodeIsSet();
    brokerage_trader_code_is_modified = p_row.getBrokerageTraderCodeIsModified();
    link_distinction_code = p_row.getLinkDistinctionCode();
    link_distinction_code_is_set = p_row.getLinkDistinctionCodeIsSet();
    link_distinction_code_is_modified = p_row.getLinkDistinctionCodeIsModified();
    account_div = p_row.getAccountDiv();
    account_div_is_set = p_row.getAccountDivIsSet();
    account_div_is_modified = p_row.getAccountDivIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
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
      email_address_is_set = true;
      email_address_is_modified = true;
      family_name_is_set = true;
      family_name_is_modified = true;
      given_name_is_set = true;
      given_name_is_modified = true;
      brokerage_trader_code_is_set = true;
      brokerage_trader_code_is_modified = true;
      link_distinction_code_is_set = true;
      link_distinction_code_is_modified = true;
      account_div_is_set = true;
      account_div_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
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
    if ( !( other instanceof MailAddressRegiRow ) )
       return false;
    return fieldsEqual( (MailAddressRegiRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のMailAddressRegiRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( MailAddressRegiRow row )
  {
    if ( mail_address_regi_id != row.getMailAddressRegiId() )
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
    if ( email_address == null ) {
      if ( row.getEmailAddress() != null )
        return false;
    } else if ( !email_address.equals( row.getEmailAddress() ) ) {
        return false;
    }
    if ( family_name == null ) {
      if ( row.getFamilyName() != null )
        return false;
    } else if ( !family_name.equals( row.getFamilyName() ) ) {
        return false;
    }
    if ( given_name == null ) {
      if ( row.getGivenName() != null )
        return false;
    } else if ( !given_name.equals( row.getGivenName() ) ) {
        return false;
    }
    if ( brokerage_trader_code == null ) {
      if ( row.getBrokerageTraderCode() != null )
        return false;
    } else if ( !brokerage_trader_code.equals( row.getBrokerageTraderCode() ) ) {
        return false;
    }
    if ( link_distinction_code == null ) {
      if ( row.getLinkDistinctionCode() != null )
        return false;
    } else if ( !link_distinction_code.equals( row.getLinkDistinctionCode() ) ) {
        return false;
    }
    if ( account_div == null ) {
      if ( row.getAccountDiv() != null )
        return false;
    } else if ( !account_div.equals( row.getAccountDiv() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
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
      return  ((int) mail_address_regi_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (email_address!=null? email_address.hashCode(): 0) 
        + (family_name!=null? family_name.hashCode(): 0) 
        + (given_name!=null? given_name.hashCode(): 0) 
        + (brokerage_trader_code!=null? brokerage_trader_code.hashCode(): 0) 
        + (link_distinction_code!=null? link_distinction_code.hashCode(): 0) 
        + (account_div!=null? account_div.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
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
		if ( !email_address_is_set )
			throw new IllegalArgumentException("non-nullable field 'email_address' must be set before inserting.");
		if ( !family_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'family_name' must be set before inserting.");
		if ( !account_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_div' must be set before inserting.");
		if ( !delete_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'delete_flag' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("mail_address_regi_id",new Long(mail_address_regi_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("email_address",email_address);
		map.put("family_name",family_name);
		if ( given_name != null )
			map.put("given_name",given_name);
		if ( brokerage_trader_code != null )
			map.put("brokerage_trader_code",brokerage_trader_code);
		if ( link_distinction_code != null )
			map.put("link_distinction_code",link_distinction_code);
		map.put("account_div",account_div);
		map.put("delete_flag",delete_flag);
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
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( email_address_is_modified )
			map.put("email_address",email_address);
		if ( family_name_is_modified )
			map.put("family_name",family_name);
		if ( given_name_is_modified )
			map.put("given_name",given_name);
		if ( brokerage_trader_code_is_modified )
			map.put("brokerage_trader_code",brokerage_trader_code);
		if ( link_distinction_code_is_modified )
			map.put("link_distinction_code",link_distinction_code);
		if ( account_div_is_modified )
			map.put("account_div",account_div);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
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
			if ( email_address_is_set )
				map.put("email_address",email_address);
			if ( family_name_is_set )
				map.put("family_name",family_name);
			map.put("given_name",given_name);
			map.put("brokerage_trader_code",brokerage_trader_code);
			map.put("link_distinction_code",link_distinction_code);
			if ( account_div_is_set )
				map.put("account_div",account_div);
			if ( delete_flag_is_set )
				map.put("delete_flag",delete_flag);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>mail_address_regi_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMailAddressRegiId()
  {
    return mail_address_regi_id;
  }


  /** 
   * <em>mail_address_regi_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMailAddressRegiIdIsSet() {
    return mail_address_regi_id_is_set;
  }


  /** 
   * <em>mail_address_regi_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMailAddressRegiIdIsModified() {
    return mail_address_regi_id_is_modified;
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
   * <em>email_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEmailAddress()
  {
    return email_address;
  }


  /** 
   * <em>email_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressIsSet() {
    return email_address_is_set;
  }


  /** 
   * <em>email_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressIsModified() {
    return email_address_is_modified;
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
   * <em>brokerage_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBrokerageTraderCode()
  {
    return brokerage_trader_code;
  }


  /** 
   * <em>brokerage_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBrokerageTraderCodeIsSet() {
    return brokerage_trader_code_is_set;
  }


  /** 
   * <em>brokerage_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBrokerageTraderCodeIsModified() {
    return brokerage_trader_code_is_modified;
  }


  /** 
   * <em>link_distinction_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLinkDistinctionCode()
  {
    return link_distinction_code;
  }


  /** 
   * <em>link_distinction_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLinkDistinctionCodeIsSet() {
    return link_distinction_code_is_set;
  }


  /** 
   * <em>link_distinction_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLinkDistinctionCodeIsModified() {
    return link_distinction_code_is_modified;
  }


  /** 
   * <em>account_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountDiv()
  {
    return account_div;
  }


  /** 
   * <em>account_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountDivIsSet() {
    return account_div_is_set;
  }


  /** 
   * <em>account_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountDivIsModified() {
    return account_div_is_modified;
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
    return new MailAddressRegiPK(mail_address_regi_id);
  }


  /** 
   * <em>mail_address_regi_id</em>カラムの値を設定します。 
   *
   * @@param p_mailAddressRegiId <em>mail_address_regi_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMailAddressRegiId( long p_mailAddressRegiId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mail_address_regi_id = p_mailAddressRegiId;
    mail_address_regi_id_is_set = true;
    mail_address_regi_id_is_modified = true;
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
   * <em>email_address</em>カラムの値を設定します。 
   *
   * @@param p_emailAddress <em>email_address</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setEmailAddress( String p_emailAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    email_address = p_emailAddress;
    email_address_is_set = true;
    email_address_is_modified = true;
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
   * <em>given_name</em>カラムの値を設定します。 
   *
   * @@param p_givenName <em>given_name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setGivenName( String p_givenName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    given_name = p_givenName;
    given_name_is_set = true;
    given_name_is_modified = true;
  }


  /** 
   * <em>brokerage_trader_code</em>カラムの値を設定します。 
   *
   * @@param p_brokerageTraderCode <em>brokerage_trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setBrokerageTraderCode( String p_brokerageTraderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    brokerage_trader_code = p_brokerageTraderCode;
    brokerage_trader_code_is_set = true;
    brokerage_trader_code_is_modified = true;
  }


  /** 
   * <em>link_distinction_code</em>カラムの値を設定します。 
   *
   * @@param p_linkDistinctionCode <em>link_distinction_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setLinkDistinctionCode( String p_linkDistinctionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    link_distinction_code = p_linkDistinctionCode;
    link_distinction_code_is_set = true;
    link_distinction_code_is_modified = true;
  }


  /** 
   * <em>account_div</em>カラムの値を設定します。 
   *
   * @@param p_accountDiv <em>account_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountDiv( String p_accountDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_div = p_accountDiv;
    account_div_is_set = true;
    account_div_is_modified = true;
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
                if ( name.equals("account_div") ) {
                    return this.account_div;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("brokerage_trader_code") ) {
                    return this.brokerage_trader_code;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                break;
            case 'e':
                if ( name.equals("email_address") ) {
                    return this.email_address;
                }
                break;
            case 'f':
                if ( name.equals("family_name") ) {
                    return this.family_name;
                }
                break;
            case 'g':
                if ( name.equals("given_name") ) {
                    return this.given_name;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("link_distinction_code") ) {
                    return this.link_distinction_code;
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("mail_address_regi_id") ) {
                    return new Long( mail_address_regi_id );
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
                if ( name.equals("account_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_div' must be String: '"+value+"' is not." );
                    this.account_div = (String) value;
                    if (this.account_div_is_set)
                        this.account_div_is_modified = true;
                    this.account_div_is_set = true;
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
                else if ( name.equals("brokerage_trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'brokerage_trader_code' must be String: '"+value+"' is not." );
                    this.brokerage_trader_code = (String) value;
                    if (this.brokerage_trader_code_is_set)
                        this.brokerage_trader_code_is_modified = true;
                    this.brokerage_trader_code_is_set = true;
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
                if ( name.equals("delete_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.delete_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("email_address") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'email_address' must be String: '"+value+"' is not." );
                    this.email_address = (String) value;
                    if (this.email_address_is_set)
                        this.email_address_is_modified = true;
                    this.email_address_is_set = true;
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
                break;
            case 'g':
                if ( name.equals("given_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'given_name' must be String: '"+value+"' is not." );
                    this.given_name = (String) value;
                    if (this.given_name_is_set)
                        this.given_name_is_modified = true;
                    this.given_name_is_set = true;
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
                if ( name.equals("link_distinction_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'link_distinction_code' must be String: '"+value+"' is not." );
                    this.link_distinction_code = (String) value;
                    if (this.link_distinction_code_is_set)
                        this.link_distinction_code_is_modified = true;
                    this.link_distinction_code_is_set = true;
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
                if ( name.equals("mail_address_regi_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'mail_address_regi_id' must be Long: '"+value+"' is not." );
                    this.mail_address_regi_id = ((Long) value).longValue();
                    if (this.mail_address_regi_id_is_set)
                        this.mail_address_regi_id_is_modified = true;
                    this.mail_address_regi_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
