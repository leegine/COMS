head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.41.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankDepositErrorHistoryParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * bank_deposit_error_historyテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BankDepositErrorHistoryRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BankDepositErrorHistoryParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BankDepositErrorHistoryParams}が{@@link BankDepositErrorHistoryRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BankDepositErrorHistoryPK 
 * @@see BankDepositErrorHistoryRow 
 */
public class BankDepositErrorHistoryParams extends Params implements BankDepositErrorHistoryRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bank_deposit_error_history";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BankDepositErrorHistoryRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BankDepositErrorHistoryRow.TYPE;
  }


  /** 
   * <em>bank_deposit_error_history_id</em>カラムの値 
   */
  public  long  bank_deposit_error_history_id;

  /** 
   * <em>bank_deposit_notify_id</em>カラムの値 
   */
  public  long  bank_deposit_notify_id;

  /** 
   * <em>serial_no</em>カラムの値 
   */
  public  int  serial_no;

  /** 
   * <em>deposit_error_comment</em>カラムの値 
   */
  public  String  deposit_error_comment;

  /** 
   * <em>remark</em>カラムの値 
   */
  public  String  remark;

  /** 
   * <em>update_person</em>カラムの値 
   */
  public  String  update_person;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>data_load_div</em>カラムの値 
   */
  public  String  data_load_div;

  private boolean bank_deposit_error_history_id_is_set = false;
  private boolean bank_deposit_error_history_id_is_modified = false;
  private boolean bank_deposit_notify_id_is_set = false;
  private boolean bank_deposit_notify_id_is_modified = false;
  private boolean serial_no_is_set = false;
  private boolean serial_no_is_modified = false;
  private boolean deposit_error_comment_is_set = false;
  private boolean deposit_error_comment_is_modified = false;
  private boolean remark_is_set = false;
  private boolean remark_is_modified = false;
  private boolean update_person_is_set = false;
  private boolean update_person_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean data_load_div_is_set = false;
  private boolean data_load_div_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "bank_deposit_error_history_id=" + bank_deposit_error_history_id
      + "," + "bank_deposit_notify_id=" +bank_deposit_notify_id
      + "," + "serial_no=" +serial_no
      + "," + "deposit_error_comment=" +deposit_error_comment
      + "," + "remark=" +remark
      + "," + "update_person=" +update_person
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "institution_code=" +institution_code
      + "," + "data_load_div=" +data_load_div
      + "}";
  }


  /** 
   * 値が未設定のBankDepositErrorHistoryParamsオブジェクトを作成します。 
   */
  public BankDepositErrorHistoryParams() {
    bank_deposit_error_history_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBankDepositErrorHistoryRowオブジェクトの値を利用してBankDepositErrorHistoryParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBankDepositErrorHistoryRowオブジェクト 
   */
  public BankDepositErrorHistoryParams( BankDepositErrorHistoryRow p_row )
  {
    bank_deposit_error_history_id = p_row.getBankDepositErrorHistoryId();
    bank_deposit_error_history_id_is_set = p_row.getBankDepositErrorHistoryIdIsSet();
    bank_deposit_error_history_id_is_modified = p_row.getBankDepositErrorHistoryIdIsModified();
    bank_deposit_notify_id = p_row.getBankDepositNotifyId();
    bank_deposit_notify_id_is_set = p_row.getBankDepositNotifyIdIsSet();
    bank_deposit_notify_id_is_modified = p_row.getBankDepositNotifyIdIsModified();
    serial_no = p_row.getSerialNo();
    serial_no_is_set = p_row.getSerialNoIsSet();
    serial_no_is_modified = p_row.getSerialNoIsModified();
    deposit_error_comment = p_row.getDepositErrorComment();
    deposit_error_comment_is_set = p_row.getDepositErrorCommentIsSet();
    deposit_error_comment_is_modified = p_row.getDepositErrorCommentIsModified();
    remark = p_row.getRemark();
    remark_is_set = p_row.getRemarkIsSet();
    remark_is_modified = p_row.getRemarkIsModified();
    update_person = p_row.getUpdatePerson();
    update_person_is_set = p_row.getUpdatePersonIsSet();
    update_person_is_modified = p_row.getUpdatePersonIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    data_load_div = p_row.getDataLoadDiv();
    data_load_div_is_set = p_row.getDataLoadDivIsSet();
    data_load_div_is_modified = p_row.getDataLoadDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      bank_deposit_notify_id_is_set = true;
      bank_deposit_notify_id_is_modified = true;
      serial_no_is_set = true;
      serial_no_is_modified = true;
      deposit_error_comment_is_set = true;
      deposit_error_comment_is_modified = true;
      remark_is_set = true;
      remark_is_modified = true;
      update_person_is_set = true;
      update_person_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      data_load_div_is_set = true;
      data_load_div_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof BankDepositErrorHistoryRow ) )
       return false;
    return fieldsEqual( (BankDepositErrorHistoryRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBankDepositErrorHistoryRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BankDepositErrorHistoryRow row )
  {
    if ( bank_deposit_error_history_id != row.getBankDepositErrorHistoryId() )
      return false;
    if ( bank_deposit_notify_id != row.getBankDepositNotifyId() )
      return false;
    if ( serial_no != row.getSerialNo() )
      return false;
    if ( deposit_error_comment == null ) {
      if ( row.getDepositErrorComment() != null )
        return false;
    } else if ( !deposit_error_comment.equals( row.getDepositErrorComment() ) ) {
        return false;
    }
    if ( remark == null ) {
      if ( row.getRemark() != null )
        return false;
    } else if ( !remark.equals( row.getRemark() ) ) {
        return false;
    }
    if ( update_person == null ) {
      if ( row.getUpdatePerson() != null )
        return false;
    } else if ( !update_person.equals( row.getUpdatePerson() ) ) {
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
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( data_load_div == null ) {
      if ( row.getDataLoadDiv() != null )
        return false;
    } else if ( !data_load_div.equals( row.getDataLoadDiv() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) bank_deposit_error_history_id)
        + ((int) bank_deposit_notify_id)
        + ((int) serial_no)
        + (deposit_error_comment!=null? deposit_error_comment.hashCode(): 0) 
        + (remark!=null? remark.hashCode(): 0) 
        + (update_person!=null? update_person.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (data_load_div!=null? data_load_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !bank_deposit_notify_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'bank_deposit_notify_id' must be set before inserting.");
		if ( !serial_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'serial_no' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !data_load_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'data_load_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("bank_deposit_error_history_id",new Long(bank_deposit_error_history_id));
		map.put("bank_deposit_notify_id",new Long(bank_deposit_notify_id));
		map.put("serial_no",new Integer(serial_no));
		if ( deposit_error_comment != null )
			map.put("deposit_error_comment",deposit_error_comment);
		if ( remark != null )
			map.put("remark",remark);
		if ( update_person != null )
			map.put("update_person",update_person);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("institution_code",institution_code);
		map.put("data_load_div",data_load_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( bank_deposit_notify_id_is_modified )
			map.put("bank_deposit_notify_id",new Long(bank_deposit_notify_id));
		if ( serial_no_is_modified )
			map.put("serial_no",new Integer(serial_no));
		if ( deposit_error_comment_is_modified )
			map.put("deposit_error_comment",deposit_error_comment);
		if ( remark_is_modified )
			map.put("remark",remark);
		if ( update_person_is_modified )
			map.put("update_person",update_person);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( data_load_div_is_modified )
			map.put("data_load_div",data_load_div);
		if (map.size() == 0) {
			if ( bank_deposit_notify_id_is_set )
				map.put("bank_deposit_notify_id",new Long(bank_deposit_notify_id));
			if ( serial_no_is_set )
				map.put("serial_no",new Integer(serial_no));
			map.put("deposit_error_comment",deposit_error_comment);
			map.put("remark",remark);
			map.put("update_person",update_person);
			map.put("created_timestamp",created_timestamp);
			map.put("last_updated_timestamp",last_updated_timestamp);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( data_load_div_is_set )
				map.put("data_load_div",data_load_div);
		}
		return map;
	}


  /** 
   * <em>bank_deposit_error_history_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBankDepositErrorHistoryId()
  {
    return bank_deposit_error_history_id;
  }


  /** 
   * <em>bank_deposit_error_history_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankDepositErrorHistoryIdIsSet() {
    return bank_deposit_error_history_id_is_set;
  }


  /** 
   * <em>bank_deposit_error_history_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankDepositErrorHistoryIdIsModified() {
    return bank_deposit_error_history_id_is_modified;
  }


  /** 
   * <em>bank_deposit_notify_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBankDepositNotifyId()
  {
    return bank_deposit_notify_id;
  }


  /** 
   * <em>bank_deposit_notify_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankDepositNotifyIdIsSet() {
    return bank_deposit_notify_id_is_set;
  }


  /** 
   * <em>bank_deposit_notify_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankDepositNotifyIdIsModified() {
    return bank_deposit_notify_id_is_modified;
  }


  /** 
   * <em>serial_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSerialNo()
  {
    return serial_no;
  }


  /** 
   * <em>serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerialNoIsSet() {
    return serial_no_is_set;
  }


  /** 
   * <em>serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerialNoIsModified() {
    return serial_no_is_modified;
  }


  /** 
   * <em>deposit_error_comment</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositErrorComment()
  {
    return deposit_error_comment;
  }


  /** 
   * <em>deposit_error_comment</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositErrorCommentIsSet() {
    return deposit_error_comment_is_set;
  }


  /** 
   * <em>deposit_error_comment</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositErrorCommentIsModified() {
    return deposit_error_comment_is_modified;
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
   * <em>update_person</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getUpdatePerson()
  {
    return update_person;
  }


  /** 
   * <em>update_person</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUpdatePersonIsSet() {
    return update_person_is_set;
  }


  /** 
   * <em>update_person</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUpdatePersonIsModified() {
    return update_person_is_modified;
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
   * <em>data_load_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDataLoadDiv()
  {
    return data_load_div;
  }


  /** 
   * <em>data_load_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataLoadDivIsSet() {
    return data_load_div_is_set;
  }


  /** 
   * <em>data_load_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataLoadDivIsModified() {
    return data_load_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new BankDepositErrorHistoryPK(bank_deposit_error_history_id);
  }


  /** 
   * <em>bank_deposit_error_history_id</em>カラムの値を設定します。 
   *
   * @@param p_bankDepositErrorHistoryId <em>bank_deposit_error_history_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBankDepositErrorHistoryId( long p_bankDepositErrorHistoryId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bank_deposit_error_history_id = p_bankDepositErrorHistoryId;
    bank_deposit_error_history_id_is_set = true;
    bank_deposit_error_history_id_is_modified = true;
  }


  /** 
   * <em>bank_deposit_notify_id</em>カラムの値を設定します。 
   *
   * @@param p_bankDepositNotifyId <em>bank_deposit_notify_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBankDepositNotifyId( long p_bankDepositNotifyId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bank_deposit_notify_id = p_bankDepositNotifyId;
    bank_deposit_notify_id_is_set = true;
    bank_deposit_notify_id_is_modified = true;
  }


  /** 
   * <em>serial_no</em>カラムの値を設定します。 
   *
   * @@param p_serialNo <em>serial_no</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setSerialNo( int p_serialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    serial_no = p_serialNo;
    serial_no_is_set = true;
    serial_no_is_modified = true;
  }


  /** 
   * <em>deposit_error_comment</em>カラムの値を設定します。 
   *
   * @@param p_depositErrorComment <em>deposit_error_comment</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setDepositErrorComment( String p_depositErrorComment )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_error_comment = p_depositErrorComment;
    deposit_error_comment_is_set = true;
    deposit_error_comment_is_modified = true;
  }


  /** 
   * <em>remark</em>カラムの値を設定します。 
   *
   * @@param p_remark <em>remark</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setRemark( String p_remark )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark = p_remark;
    remark_is_set = true;
    remark_is_modified = true;
  }


  /** 
   * <em>update_person</em>カラムの値を設定します。 
   *
   * @@param p_updatePerson <em>update_person</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setUpdatePerson( String p_updatePerson )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    update_person = p_updatePerson;
    update_person_is_set = true;
    update_person_is_modified = true;
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
   * <em>data_load_div</em>カラムの値を設定します。 
   *
   * @@param p_dataLoadDiv <em>data_load_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDataLoadDiv( String p_dataLoadDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_load_div = p_dataLoadDiv;
    data_load_div_is_set = true;
    data_load_div_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'b':
                if ( name.equals("bank_deposit_error_history_id") ) {
                    return new Long( bank_deposit_error_history_id );
                }
                else if ( name.equals("bank_deposit_notify_id") ) {
                    return new Long( bank_deposit_notify_id );
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("deposit_error_comment") ) {
                    return this.deposit_error_comment;
                }
                else if ( name.equals("data_load_div") ) {
                    return this.data_load_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'r':
                if ( name.equals("remark") ) {
                    return this.remark;
                }
                break;
            case 's':
                if ( name.equals("serial_no") ) {
                    return new Integer( serial_no );
                }
                break;
            case 'u':
                if ( name.equals("update_person") ) {
                    return this.update_person;
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
            case 'b':
                if ( name.equals("bank_deposit_error_history_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'bank_deposit_error_history_id' must be Long: '"+value+"' is not." );
                    this.bank_deposit_error_history_id = ((Long) value).longValue();
                    if (this.bank_deposit_error_history_id_is_set)
                        this.bank_deposit_error_history_id_is_modified = true;
                    this.bank_deposit_error_history_id_is_set = true;
                    return;
                }
                else if ( name.equals("bank_deposit_notify_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'bank_deposit_notify_id' must be Long: '"+value+"' is not." );
                    this.bank_deposit_notify_id = ((Long) value).longValue();
                    if (this.bank_deposit_notify_id_is_set)
                        this.bank_deposit_notify_id_is_modified = true;
                    this.bank_deposit_notify_id_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( value != null )
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
                if ( name.equals("deposit_error_comment") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_error_comment' must be String: '"+value+"' is not." );
                    this.deposit_error_comment = (String) value;
                    if (this.deposit_error_comment_is_set)
                        this.deposit_error_comment_is_modified = true;
                    this.deposit_error_comment_is_set = true;
                    return;
                }
                else if ( name.equals("data_load_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'data_load_div' must be String: '"+value+"' is not." );
                    this.data_load_div = (String) value;
                    if (this.data_load_div_is_set)
                        this.data_load_div_is_modified = true;
                    this.data_load_div_is_set = true;
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
                if ( name.equals("last_updated_timestamp") ) {
                    if ( value != null )
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
            case 's':
                if ( name.equals("serial_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'serial_no' must be Integer: '"+value+"' is not." );
                    this.serial_no = ((Integer) value).intValue();
                    if (this.serial_no_is_set)
                        this.serial_no_is_modified = true;
                    this.serial_no_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("update_person") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'update_person' must be String: '"+value+"' is not." );
                    this.update_person = (String) value;
                    if (this.update_person_is_set)
                        this.update_person_is_modified = true;
                    this.update_person_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
