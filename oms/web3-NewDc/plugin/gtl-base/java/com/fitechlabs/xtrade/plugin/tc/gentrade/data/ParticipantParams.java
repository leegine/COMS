head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.36.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ParticipantParams.java;


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
 * participantテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link ParticipantRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link ParticipantParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link ParticipantParams}が{@@link ParticipantRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see ParticipantPK 
 * @@see ParticipantRow 
 */
public class ParticipantParams extends Params implements ParticipantRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "participant";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = ParticipantRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return ParticipantRow.TYPE;
  }


  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>participant_id</em>カラムの値 
   */
  public  long  participant_id;

  /** 
   * <em>participant_code</em>カラムの値 
   */
  public  String  participant_code;

  /** 
   * <em>participant_middle_name</em>カラムの値 
   */
  public  String  participant_middle_name;

  /** 
   * <em>participant_family_name</em>カラムの値 
   */
  public  String  participant_family_name;

  /** 
   * <em>participant_given_name</em>カラムの値 
   */
  public  String  participant_given_name;

  /** 
   * <em>participant_middle_name_alt1</em>カラムの値 
   */
  public  String  participant_middle_name_alt1;

  /** 
   * <em>participant_family_name_alt1</em>カラムの値 
   */
  public  String  participant_family_name_alt1;

  /** 
   * <em>participant_given_name_alt1</em>カラムの値 
   */
  public  String  participant_given_name_alt1;

  /** 
   * <em>participant_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ParticipantTypeEnum  participant_type;

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
  private boolean participant_id_is_set = false;
  private boolean participant_id_is_modified = false;
  private boolean participant_code_is_set = false;
  private boolean participant_code_is_modified = false;
  private boolean participant_middle_name_is_set = false;
  private boolean participant_middle_name_is_modified = false;
  private boolean participant_family_name_is_set = false;
  private boolean participant_family_name_is_modified = false;
  private boolean participant_given_name_is_set = false;
  private boolean participant_given_name_is_modified = false;
  private boolean participant_middle_name_alt1_is_set = false;
  private boolean participant_middle_name_alt1_is_modified = false;
  private boolean participant_family_name_alt1_is_set = false;
  private boolean participant_family_name_alt1_is_modified = false;
  private boolean participant_given_name_alt1_is_set = false;
  private boolean participant_given_name_alt1_is_modified = false;
  private boolean participant_type_is_set = false;
  private boolean participant_type_is_modified = false;
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
      + "," + "participant_id=" + participant_id
      + "," + "participant_code=" +participant_code
      + "," + "participant_middle_name=" +participant_middle_name
      + "," + "participant_family_name=" +participant_family_name
      + "," + "participant_given_name=" +participant_given_name
      + "," + "participant_middle_name_alt1=" +participant_middle_name_alt1
      + "," + "participant_family_name_alt1=" +participant_family_name_alt1
      + "," + "participant_given_name_alt1=" +participant_given_name_alt1
      + "," + "participant_type=" +participant_type
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のParticipantParamsオブジェクトを作成します。 
   */
  public ParticipantParams() {
    account_id_is_modified = true;
    participant_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のParticipantRowオブジェクトの値を利用してParticipantParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するParticipantRowオブジェクト 
   */
  public ParticipantParams( ParticipantRow p_row )
  {
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    participant_id = p_row.getParticipantId();
    participant_id_is_set = p_row.getParticipantIdIsSet();
    participant_id_is_modified = p_row.getParticipantIdIsModified();
    participant_code = p_row.getParticipantCode();
    participant_code_is_set = p_row.getParticipantCodeIsSet();
    participant_code_is_modified = p_row.getParticipantCodeIsModified();
    participant_middle_name = p_row.getParticipantMiddleName();
    participant_middle_name_is_set = p_row.getParticipantMiddleNameIsSet();
    participant_middle_name_is_modified = p_row.getParticipantMiddleNameIsModified();
    participant_family_name = p_row.getParticipantFamilyName();
    participant_family_name_is_set = p_row.getParticipantFamilyNameIsSet();
    participant_family_name_is_modified = p_row.getParticipantFamilyNameIsModified();
    participant_given_name = p_row.getParticipantGivenName();
    participant_given_name_is_set = p_row.getParticipantGivenNameIsSet();
    participant_given_name_is_modified = p_row.getParticipantGivenNameIsModified();
    participant_middle_name_alt1 = p_row.getParticipantMiddleNameAlt1();
    participant_middle_name_alt1_is_set = p_row.getParticipantMiddleNameAlt1IsSet();
    participant_middle_name_alt1_is_modified = p_row.getParticipantMiddleNameAlt1IsModified();
    participant_family_name_alt1 = p_row.getParticipantFamilyNameAlt1();
    participant_family_name_alt1_is_set = p_row.getParticipantFamilyNameAlt1IsSet();
    participant_family_name_alt1_is_modified = p_row.getParticipantFamilyNameAlt1IsModified();
    participant_given_name_alt1 = p_row.getParticipantGivenNameAlt1();
    participant_given_name_alt1_is_set = p_row.getParticipantGivenNameAlt1IsSet();
    participant_given_name_alt1_is_modified = p_row.getParticipantGivenNameAlt1IsModified();
    participant_type = p_row.getParticipantType();
    participant_type_is_set = p_row.getParticipantTypeIsSet();
    participant_type_is_modified = p_row.getParticipantTypeIsModified();
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
      participant_code_is_set = true;
      participant_code_is_modified = true;
      participant_middle_name_is_set = true;
      participant_middle_name_is_modified = true;
      participant_family_name_is_set = true;
      participant_family_name_is_modified = true;
      participant_given_name_is_set = true;
      participant_given_name_is_modified = true;
      participant_middle_name_alt1_is_set = true;
      participant_middle_name_alt1_is_modified = true;
      participant_family_name_alt1_is_set = true;
      participant_family_name_alt1_is_modified = true;
      participant_given_name_alt1_is_set = true;
      participant_given_name_alt1_is_modified = true;
      participant_type_is_set = true;
      participant_type_is_modified = true;
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
    if ( !( other instanceof ParticipantRow ) )
       return false;
    return fieldsEqual( (ParticipantRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のParticipantRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( ParticipantRow row )
  {
    if ( account_id != row.getAccountId() )
      return false;
    if ( participant_id != row.getParticipantId() )
      return false;
    if ( participant_code == null ) {
      if ( row.getParticipantCode() != null )
        return false;
    } else if ( !participant_code.equals( row.getParticipantCode() ) ) {
        return false;
    }
    if ( participant_middle_name == null ) {
      if ( row.getParticipantMiddleName() != null )
        return false;
    } else if ( !participant_middle_name.equals( row.getParticipantMiddleName() ) ) {
        return false;
    }
    if ( participant_family_name == null ) {
      if ( row.getParticipantFamilyName() != null )
        return false;
    } else if ( !participant_family_name.equals( row.getParticipantFamilyName() ) ) {
        return false;
    }
    if ( participant_given_name == null ) {
      if ( row.getParticipantGivenName() != null )
        return false;
    } else if ( !participant_given_name.equals( row.getParticipantGivenName() ) ) {
        return false;
    }
    if ( participant_middle_name_alt1 == null ) {
      if ( row.getParticipantMiddleNameAlt1() != null )
        return false;
    } else if ( !participant_middle_name_alt1.equals( row.getParticipantMiddleNameAlt1() ) ) {
        return false;
    }
    if ( participant_family_name_alt1 == null ) {
      if ( row.getParticipantFamilyNameAlt1() != null )
        return false;
    } else if ( !participant_family_name_alt1.equals( row.getParticipantFamilyNameAlt1() ) ) {
        return false;
    }
    if ( participant_given_name_alt1 == null ) {
      if ( row.getParticipantGivenNameAlt1() != null )
        return false;
    } else if ( !participant_given_name_alt1.equals( row.getParticipantGivenNameAlt1() ) ) {
        return false;
    }
    if ( participant_type == null ) {
      if ( row.getParticipantType() != null )
        return false;
    } else if ( !participant_type.equals( row.getParticipantType() ) ) {
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
        + ((int) participant_id)
        + (participant_code!=null? participant_code.hashCode(): 0) 
        + (participant_middle_name!=null? participant_middle_name.hashCode(): 0) 
        + (participant_family_name!=null? participant_family_name.hashCode(): 0) 
        + (participant_given_name!=null? participant_given_name.hashCode(): 0) 
        + (participant_middle_name_alt1!=null? participant_middle_name_alt1.hashCode(): 0) 
        + (participant_family_name_alt1!=null? participant_family_name_alt1.hashCode(): 0) 
        + (participant_given_name_alt1!=null? participant_given_name_alt1.hashCode(): 0) 
        + (participant_type!=null? participant_type.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !participant_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'participant_code' must be set before inserting.");
		if ( !participant_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'participant_type' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("account_id",new Long(account_id));
		map.put("participant_id",new Long(participant_id));
		map.put("participant_code",participant_code);
		if ( participant_middle_name != null )
			map.put("participant_middle_name",participant_middle_name);
		if ( participant_family_name != null )
			map.put("participant_family_name",participant_family_name);
		if ( participant_given_name != null )
			map.put("participant_given_name",participant_given_name);
		if ( participant_middle_name_alt1 != null )
			map.put("participant_middle_name_alt1",participant_middle_name_alt1);
		if ( participant_family_name_alt1 != null )
			map.put("participant_family_name_alt1",participant_family_name_alt1);
		if ( participant_given_name_alt1 != null )
			map.put("participant_given_name_alt1",participant_given_name_alt1);
		map.put("participant_type",participant_type);
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
		if ( participant_code_is_modified )
			map.put("participant_code",participant_code);
		if ( participant_middle_name_is_modified )
			map.put("participant_middle_name",participant_middle_name);
		if ( participant_family_name_is_modified )
			map.put("participant_family_name",participant_family_name);
		if ( participant_given_name_is_modified )
			map.put("participant_given_name",participant_given_name);
		if ( participant_middle_name_alt1_is_modified )
			map.put("participant_middle_name_alt1",participant_middle_name_alt1);
		if ( participant_family_name_alt1_is_modified )
			map.put("participant_family_name_alt1",participant_family_name_alt1);
		if ( participant_given_name_alt1_is_modified )
			map.put("participant_given_name_alt1",participant_given_name_alt1);
		if ( participant_type_is_modified )
			map.put("participant_type",participant_type);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( participant_code_is_set )
				map.put("participant_code",participant_code);
			map.put("participant_middle_name",participant_middle_name);
			map.put("participant_family_name",participant_family_name);
			map.put("participant_given_name",participant_given_name);
			map.put("participant_middle_name_alt1",participant_middle_name_alt1);
			map.put("participant_family_name_alt1",participant_family_name_alt1);
			map.put("participant_given_name_alt1",participant_given_name_alt1);
			if ( participant_type_is_set )
				map.put("participant_type",participant_type);
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
   * <em>participant_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getParticipantId()
  {
    return participant_id;
  }


  /** 
   * <em>participant_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantIdIsSet() {
    return participant_id_is_set;
  }


  /** 
   * <em>participant_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantIdIsModified() {
    return participant_id_is_modified;
  }


  /** 
   * <em>participant_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getParticipantCode()
  {
    return participant_code;
  }


  /** 
   * <em>participant_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantCodeIsSet() {
    return participant_code_is_set;
  }


  /** 
   * <em>participant_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantCodeIsModified() {
    return participant_code_is_modified;
  }


  /** 
   * <em>participant_middle_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getParticipantMiddleName()
  {
    return participant_middle_name;
  }


  /** 
   * <em>participant_middle_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantMiddleNameIsSet() {
    return participant_middle_name_is_set;
  }


  /** 
   * <em>participant_middle_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantMiddleNameIsModified() {
    return participant_middle_name_is_modified;
  }


  /** 
   * <em>participant_family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getParticipantFamilyName()
  {
    return participant_family_name;
  }


  /** 
   * <em>participant_family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantFamilyNameIsSet() {
    return participant_family_name_is_set;
  }


  /** 
   * <em>participant_family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantFamilyNameIsModified() {
    return participant_family_name_is_modified;
  }


  /** 
   * <em>participant_given_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getParticipantGivenName()
  {
    return participant_given_name;
  }


  /** 
   * <em>participant_given_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantGivenNameIsSet() {
    return participant_given_name_is_set;
  }


  /** 
   * <em>participant_given_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantGivenNameIsModified() {
    return participant_given_name_is_modified;
  }


  /** 
   * <em>participant_middle_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getParticipantMiddleNameAlt1()
  {
    return participant_middle_name_alt1;
  }


  /** 
   * <em>participant_middle_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantMiddleNameAlt1IsSet() {
    return participant_middle_name_alt1_is_set;
  }


  /** 
   * <em>participant_middle_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantMiddleNameAlt1IsModified() {
    return participant_middle_name_alt1_is_modified;
  }


  /** 
   * <em>participant_family_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getParticipantFamilyNameAlt1()
  {
    return participant_family_name_alt1;
  }


  /** 
   * <em>participant_family_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantFamilyNameAlt1IsSet() {
    return participant_family_name_alt1_is_set;
  }


  /** 
   * <em>participant_family_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantFamilyNameAlt1IsModified() {
    return participant_family_name_alt1_is_modified;
  }


  /** 
   * <em>participant_given_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getParticipantGivenNameAlt1()
  {
    return participant_given_name_alt1;
  }


  /** 
   * <em>participant_given_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantGivenNameAlt1IsSet() {
    return participant_given_name_alt1_is_set;
  }


  /** 
   * <em>participant_given_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantGivenNameAlt1IsModified() {
    return participant_given_name_alt1_is_modified;
  }


  /** 
   * <em>participant_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ParticipantTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ParticipantTypeEnum getParticipantType()
  {
    return participant_type;
  }


  /** 
   * <em>participant_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantTypeIsSet() {
    return participant_type_is_set;
  }


  /** 
   * <em>participant_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParticipantTypeIsModified() {
    return participant_type_is_modified;
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
    return new ParticipantPK(account_id, participant_id);
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
   * <em>participant_id</em>カラムの値を設定します。 
   *
   * @@param p_participantId <em>participant_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setParticipantId( long p_participantId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    participant_id = p_participantId;
    participant_id_is_set = true;
    participant_id_is_modified = true;
  }


  /** 
   * <em>participant_code</em>カラムの値を設定します。 
   *
   * @@param p_participantCode <em>participant_code</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setParticipantCode( String p_participantCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    participant_code = p_participantCode;
    participant_code_is_set = true;
    participant_code_is_modified = true;
  }


  /** 
   * <em>participant_middle_name</em>カラムの値を設定します。 
   *
   * @@param p_participantMiddleName <em>participant_middle_name</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setParticipantMiddleName( String p_participantMiddleName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    participant_middle_name = p_participantMiddleName;
    participant_middle_name_is_set = true;
    participant_middle_name_is_modified = true;
  }


  /** 
   * <em>participant_family_name</em>カラムの値を設定します。 
   *
   * @@param p_participantFamilyName <em>participant_family_name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setParticipantFamilyName( String p_participantFamilyName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    participant_family_name = p_participantFamilyName;
    participant_family_name_is_set = true;
    participant_family_name_is_modified = true;
  }


  /** 
   * <em>participant_given_name</em>カラムの値を設定します。 
   *
   * @@param p_participantGivenName <em>participant_given_name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setParticipantGivenName( String p_participantGivenName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    participant_given_name = p_participantGivenName;
    participant_given_name_is_set = true;
    participant_given_name_is_modified = true;
  }


  /** 
   * <em>participant_middle_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_participantMiddleNameAlt1 <em>participant_middle_name_alt1</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setParticipantMiddleNameAlt1( String p_participantMiddleNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    participant_middle_name_alt1 = p_participantMiddleNameAlt1;
    participant_middle_name_alt1_is_set = true;
    participant_middle_name_alt1_is_modified = true;
  }


  /** 
   * <em>participant_family_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_participantFamilyNameAlt1 <em>participant_family_name_alt1</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setParticipantFamilyNameAlt1( String p_participantFamilyNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    participant_family_name_alt1 = p_participantFamilyNameAlt1;
    participant_family_name_alt1_is_set = true;
    participant_family_name_alt1_is_modified = true;
  }


  /** 
   * <em>participant_given_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_participantGivenNameAlt1 <em>participant_given_name_alt1</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setParticipantGivenNameAlt1( String p_participantGivenNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    participant_given_name_alt1 = p_participantGivenNameAlt1;
    participant_given_name_alt1_is_set = true;
    participant_given_name_alt1_is_modified = true;
  }


  /** 
   * <em>participant_type</em>カラムの値を設定します。 
   *
   * @@param p_participantType <em>participant_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ParticipantTypeEnum値 
   */
  public final void setParticipantType( com.fitechlabs.xtrade.plugin.tc.gentrade.ParticipantTypeEnum p_participantType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    participant_type = p_participantType;
    participant_type_is_set = true;
    participant_type_is_modified = true;
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
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("participant_id") ) {
                    return new Long( participant_id );
                }
                else if ( name.equals("participant_code") ) {
                    return this.participant_code;
                }
                else if ( name.equals("participant_middle_name") ) {
                    return this.participant_middle_name;
                }
                else if ( name.equals("participant_family_name") ) {
                    return this.participant_family_name;
                }
                else if ( name.equals("participant_given_name") ) {
                    return this.participant_given_name;
                }
                else if ( name.equals("participant_middle_name_alt1") ) {
                    return this.participant_middle_name_alt1;
                }
                else if ( name.equals("participant_family_name_alt1") ) {
                    return this.participant_family_name_alt1;
                }
                else if ( name.equals("participant_given_name_alt1") ) {
                    return this.participant_given_name_alt1;
                }
                else if ( name.equals("participant_type") ) {
                    return this.participant_type;
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
                if ( name.equals("participant_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'participant_id' must be Long: '"+value+"' is not." );
                    this.participant_id = ((Long) value).longValue();
                    if (this.participant_id_is_set)
                        this.participant_id_is_modified = true;
                    this.participant_id_is_set = true;
                    return;
                }
                else if ( name.equals("participant_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'participant_code' must be String: '"+value+"' is not." );
                    this.participant_code = (String) value;
                    if (this.participant_code_is_set)
                        this.participant_code_is_modified = true;
                    this.participant_code_is_set = true;
                    return;
                }
                else if ( name.equals("participant_middle_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'participant_middle_name' must be String: '"+value+"' is not." );
                    this.participant_middle_name = (String) value;
                    if (this.participant_middle_name_is_set)
                        this.participant_middle_name_is_modified = true;
                    this.participant_middle_name_is_set = true;
                    return;
                }
                else if ( name.equals("participant_family_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'participant_family_name' must be String: '"+value+"' is not." );
                    this.participant_family_name = (String) value;
                    if (this.participant_family_name_is_set)
                        this.participant_family_name_is_modified = true;
                    this.participant_family_name_is_set = true;
                    return;
                }
                else if ( name.equals("participant_given_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'participant_given_name' must be String: '"+value+"' is not." );
                    this.participant_given_name = (String) value;
                    if (this.participant_given_name_is_set)
                        this.participant_given_name_is_modified = true;
                    this.participant_given_name_is_set = true;
                    return;
                }
                else if ( name.equals("participant_middle_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'participant_middle_name_alt1' must be String: '"+value+"' is not." );
                    this.participant_middle_name_alt1 = (String) value;
                    if (this.participant_middle_name_alt1_is_set)
                        this.participant_middle_name_alt1_is_modified = true;
                    this.participant_middle_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("participant_family_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'participant_family_name_alt1' must be String: '"+value+"' is not." );
                    this.participant_family_name_alt1 = (String) value;
                    if (this.participant_family_name_alt1_is_set)
                        this.participant_family_name_alt1_is_modified = true;
                    this.participant_family_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("participant_given_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'participant_given_name_alt1' must be String: '"+value+"' is not." );
                    this.participant_given_name_alt1 = (String) value;
                    if (this.participant_given_name_alt1_is_set)
                        this.participant_given_name_alt1_is_modified = true;
                    this.participant_given_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("participant_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ParticipantTypeEnum) )
                        throw new IllegalArgumentException( "value for 'participant_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ParticipantTypeEnum: '"+value+"' is not." );
                    this.participant_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ParticipantTypeEnum) value;
                    if (this.participant_type_is_set)
                        this.participant_type_is_modified = true;
                    this.participant_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
