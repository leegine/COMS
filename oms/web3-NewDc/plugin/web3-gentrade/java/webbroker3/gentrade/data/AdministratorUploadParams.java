head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.36.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorUploadParams.java;


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
 * administrator_uploadテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AdministratorUploadRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AdministratorUploadParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AdministratorUploadParams}が{@@link AdministratorUploadRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AdministratorUploadPK 
 * @@see AdministratorUploadRow 
 */
public class AdministratorUploadParams extends Params implements AdministratorUploadRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "administrator_upload";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AdministratorUploadRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AdministratorUploadRow.TYPE;
  }


  /** 
   * <em>administrator_upload_id</em>カラムの値 
   */
  public  long  administrator_upload_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>upload_file_id</em>カラムの値 
   */
  public  String  upload_file_id;

  /** 
   * <em>upload_start_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  upload_start_timestamp;

  /** 
   * <em>upload_end_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  upload_end_timestamp;

  /** 
   * <em>message_code</em>カラムの値 
   */
  public  String  message_code;

  /** 
   * <em>upload_count</em>カラムの値 
   */
  public  Integer  upload_count;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>upload_key</em>カラムの値 
   */
  public  Long  upload_key;

  /** 
   * <em>note1</em>カラムの値 
   */
  public  String  note1;

  /** 
   * <em>note2</em>カラムの値 
   */
  public  String  note2;

  /** 
   * <em>note3</em>カラムの値 
   */
  public  String  note3;

  private boolean administrator_upload_id_is_set = false;
  private boolean administrator_upload_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean upload_file_id_is_set = false;
  private boolean upload_file_id_is_modified = false;
  private boolean upload_start_timestamp_is_set = false;
  private boolean upload_start_timestamp_is_modified = false;
  private boolean upload_end_timestamp_is_set = false;
  private boolean upload_end_timestamp_is_modified = false;
  private boolean message_code_is_set = false;
  private boolean message_code_is_modified = false;
  private boolean upload_count_is_set = false;
  private boolean upload_count_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean upload_key_is_set = false;
  private boolean upload_key_is_modified = false;
  private boolean note1_is_set = false;
  private boolean note1_is_modified = false;
  private boolean note2_is_set = false;
  private boolean note2_is_modified = false;
  private boolean note3_is_set = false;
  private boolean note3_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "administrator_upload_id=" + administrator_upload_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "product_type=" +product_type
      + "," + "upload_file_id=" +upload_file_id
      + "," + "upload_start_timestamp=" +upload_start_timestamp
      + "," + "upload_end_timestamp=" +upload_end_timestamp
      + "," + "message_code=" +message_code
      + "," + "upload_count=" +upload_count
      + "," + "last_updater=" +last_updater
      + "," + "upload_key=" +upload_key
      + "," + "note1=" +note1
      + "," + "note2=" +note2
      + "," + "note3=" +note3
      + "}";
  }


  /** 
   * 値が未設定のAdministratorUploadParamsオブジェクトを作成します。 
   */
  public AdministratorUploadParams() {
    administrator_upload_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAdministratorUploadRowオブジェクトの値を利用してAdministratorUploadParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAdministratorUploadRowオブジェクト 
   */
  public AdministratorUploadParams( AdministratorUploadRow p_row )
  {
    administrator_upload_id = p_row.getAdministratorUploadId();
    administrator_upload_id_is_set = p_row.getAdministratorUploadIdIsSet();
    administrator_upload_id_is_modified = p_row.getAdministratorUploadIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    upload_file_id = p_row.getUploadFileId();
    upload_file_id_is_set = p_row.getUploadFileIdIsSet();
    upload_file_id_is_modified = p_row.getUploadFileIdIsModified();
    upload_start_timestamp = p_row.getUploadStartTimestamp();
    upload_start_timestamp_is_set = p_row.getUploadStartTimestampIsSet();
    upload_start_timestamp_is_modified = p_row.getUploadStartTimestampIsModified();
    upload_end_timestamp = p_row.getUploadEndTimestamp();
    upload_end_timestamp_is_set = p_row.getUploadEndTimestampIsSet();
    upload_end_timestamp_is_modified = p_row.getUploadEndTimestampIsModified();
    message_code = p_row.getMessageCode();
    message_code_is_set = p_row.getMessageCodeIsSet();
    message_code_is_modified = p_row.getMessageCodeIsModified();
    if ( !p_row.getUploadCountIsNull() )
      upload_count = new Integer( p_row.getUploadCount() );
    upload_count_is_set = p_row.getUploadCountIsSet();
    upload_count_is_modified = p_row.getUploadCountIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    if ( !p_row.getUploadKeyIsNull() )
      upload_key = new Long( p_row.getUploadKey() );
    upload_key_is_set = p_row.getUploadKeyIsSet();
    upload_key_is_modified = p_row.getUploadKeyIsModified();
    note1 = p_row.getNote1();
    note1_is_set = p_row.getNote1IsSet();
    note1_is_modified = p_row.getNote1IsModified();
    note2 = p_row.getNote2();
    note2_is_set = p_row.getNote2IsSet();
    note2_is_modified = p_row.getNote2IsModified();
    note3 = p_row.getNote3();
    note3_is_set = p_row.getNote3IsSet();
    note3_is_modified = p_row.getNote3IsModified();
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
      product_type_is_set = true;
      product_type_is_modified = true;
      upload_file_id_is_set = true;
      upload_file_id_is_modified = true;
      upload_start_timestamp_is_set = true;
      upload_start_timestamp_is_modified = true;
      upload_end_timestamp_is_set = true;
      upload_end_timestamp_is_modified = true;
      message_code_is_set = true;
      message_code_is_modified = true;
      upload_count_is_set = true;
      upload_count_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      upload_key_is_set = true;
      upload_key_is_modified = true;
      note1_is_set = true;
      note1_is_modified = true;
      note2_is_set = true;
      note2_is_modified = true;
      note3_is_set = true;
      note3_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AdministratorUploadRow ) )
       return false;
    return fieldsEqual( (AdministratorUploadRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAdministratorUploadRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AdministratorUploadRow row )
  {
    if ( administrator_upload_id != row.getAdministratorUploadId() )
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
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( upload_file_id == null ) {
      if ( row.getUploadFileId() != null )
        return false;
    } else if ( !upload_file_id.equals( row.getUploadFileId() ) ) {
        return false;
    }
    if ( upload_start_timestamp == null ) {
      if ( row.getUploadStartTimestamp() != null )
        return false;
    } else if ( !upload_start_timestamp.equals( row.getUploadStartTimestamp() ) ) {
        return false;
    }
    if ( upload_end_timestamp == null ) {
      if ( row.getUploadEndTimestamp() != null )
        return false;
    } else if ( !upload_end_timestamp.equals( row.getUploadEndTimestamp() ) ) {
        return false;
    }
    if ( message_code == null ) {
      if ( row.getMessageCode() != null )
        return false;
    } else if ( !message_code.equals( row.getMessageCode() ) ) {
        return false;
    }
    if ( upload_count == null ) {
      if ( !row.getUploadCountIsNull() )
        return false;
    } else if ( row.getUploadCountIsNull() || ( upload_count.intValue() != row.getUploadCount() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( upload_key == null ) {
      if ( !row.getUploadKeyIsNull() )
        return false;
    } else if ( row.getUploadKeyIsNull() || ( upload_key.longValue() != row.getUploadKey() ) ) {
        return false;
    }
    if ( note1 == null ) {
      if ( row.getNote1() != null )
        return false;
    } else if ( !note1.equals( row.getNote1() ) ) {
        return false;
    }
    if ( note2 == null ) {
      if ( row.getNote2() != null )
        return false;
    } else if ( !note2.equals( row.getNote2() ) ) {
        return false;
    }
    if ( note3 == null ) {
      if ( row.getNote3() != null )
        return false;
    } else if ( !note3.equals( row.getNote3() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) administrator_upload_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + (upload_file_id!=null? upload_file_id.hashCode(): 0) 
        + (upload_start_timestamp!=null? upload_start_timestamp.hashCode(): 0) 
        + (upload_end_timestamp!=null? upload_end_timestamp.hashCode(): 0) 
        + (message_code!=null? message_code.hashCode(): 0) 
        + (upload_count!=null? upload_count.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (upload_key!=null? upload_key.hashCode(): 0) 
        + (note1!=null? note1.hashCode(): 0) 
        + (note2!=null? note2.hashCode(): 0) 
        + (note3!=null? note3.hashCode(): 0) 
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
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !upload_file_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'upload_file_id' must be set before inserting.");
		if ( !upload_start_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'upload_start_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("administrator_upload_id",new Long(administrator_upload_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("product_type",product_type);
		map.put("upload_file_id",upload_file_id);
		map.put("upload_start_timestamp",upload_start_timestamp);
		if ( upload_end_timestamp != null )
			map.put("upload_end_timestamp",upload_end_timestamp);
		if ( message_code != null )
			map.put("message_code",message_code);
		if ( upload_count != null )
			map.put("upload_count",upload_count);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( upload_key != null )
			map.put("upload_key",upload_key);
		if ( note1 != null )
			map.put("note1",note1);
		if ( note2 != null )
			map.put("note2",note2);
		if ( note3 != null )
			map.put("note3",note3);
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
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( upload_file_id_is_modified )
			map.put("upload_file_id",upload_file_id);
		if ( upload_start_timestamp_is_modified )
			map.put("upload_start_timestamp",upload_start_timestamp);
		if ( upload_end_timestamp_is_modified )
			map.put("upload_end_timestamp",upload_end_timestamp);
		if ( message_code_is_modified )
			map.put("message_code",message_code);
		if ( upload_count_is_modified )
			map.put("upload_count",upload_count);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( upload_key_is_modified )
			map.put("upload_key",upload_key);
		if ( note1_is_modified )
			map.put("note1",note1);
		if ( note2_is_modified )
			map.put("note2",note2);
		if ( note3_is_modified )
			map.put("note3",note3);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( product_type_is_set )
				map.put("product_type",product_type);
			if ( upload_file_id_is_set )
				map.put("upload_file_id",upload_file_id);
			if ( upload_start_timestamp_is_set )
				map.put("upload_start_timestamp",upload_start_timestamp);
			map.put("upload_end_timestamp",upload_end_timestamp);
			map.put("message_code",message_code);
			map.put("upload_count",upload_count);
			map.put("last_updater",last_updater);
			map.put("upload_key",upload_key);
			map.put("note1",note1);
			map.put("note2",note2);
			map.put("note3",note3);
		}
		return map;
	}


  /** 
   * <em>administrator_upload_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAdministratorUploadId()
  {
    return administrator_upload_id;
  }


  /** 
   * <em>administrator_upload_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdministratorUploadIdIsSet() {
    return administrator_upload_id_is_set;
  }


  /** 
   * <em>administrator_upload_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdministratorUploadIdIsModified() {
    return administrator_upload_id_is_modified;
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
   * <em>product_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType()
  {
    return product_type;
  }


  /** 
   * <em>product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsSet() {
    return product_type_is_set;
  }


  /** 
   * <em>product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsModified() {
    return product_type_is_modified;
  }


  /** 
   * <em>upload_file_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getUploadFileId()
  {
    return upload_file_id;
  }


  /** 
   * <em>upload_file_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUploadFileIdIsSet() {
    return upload_file_id_is_set;
  }


  /** 
   * <em>upload_file_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUploadFileIdIsModified() {
    return upload_file_id_is_modified;
  }


  /** 
   * <em>upload_start_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getUploadStartTimestamp()
  {
    return upload_start_timestamp;
  }


  /** 
   * <em>upload_start_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUploadStartTimestampIsSet() {
    return upload_start_timestamp_is_set;
  }


  /** 
   * <em>upload_start_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUploadStartTimestampIsModified() {
    return upload_start_timestamp_is_modified;
  }


  /** 
   * <em>upload_end_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getUploadEndTimestamp()
  {
    return upload_end_timestamp;
  }


  /** 
   * <em>upload_end_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUploadEndTimestampIsSet() {
    return upload_end_timestamp_is_set;
  }


  /** 
   * <em>upload_end_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUploadEndTimestampIsModified() {
    return upload_end_timestamp_is_modified;
  }


  /** 
   * <em>message_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMessageCode()
  {
    return message_code;
  }


  /** 
   * <em>message_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMessageCodeIsSet() {
    return message_code_is_set;
  }


  /** 
   * <em>message_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMessageCodeIsModified() {
    return message_code_is_modified;
  }


  /** 
   * <em>upload_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getUploadCount()
  {
    return ( upload_count==null? ((int)0): upload_count.intValue() );
  }


  /** 
   * <em>upload_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUploadCountIsNull()
  {
    return upload_count == null;
  }


  /** 
   * <em>upload_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUploadCountIsSet() {
    return upload_count_is_set;
  }


  /** 
   * <em>upload_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUploadCountIsModified() {
    return upload_count_is_modified;
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
   * <em>upload_key</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getUploadKey()
  {
    return ( upload_key==null? ((long)0): upload_key.longValue() );
  }


  /** 
   * <em>upload_key</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUploadKeyIsNull()
  {
    return upload_key == null;
  }


  /** 
   * <em>upload_key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUploadKeyIsSet() {
    return upload_key_is_set;
  }


  /** 
   * <em>upload_key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUploadKeyIsModified() {
    return upload_key_is_modified;
  }


  /** 
   * <em>note1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNote1()
  {
    return note1;
  }


  /** 
   * <em>note1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNote1IsSet() {
    return note1_is_set;
  }


  /** 
   * <em>note1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNote1IsModified() {
    return note1_is_modified;
  }


  /** 
   * <em>note2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNote2()
  {
    return note2;
  }


  /** 
   * <em>note2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNote2IsSet() {
    return note2_is_set;
  }


  /** 
   * <em>note2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNote2IsModified() {
    return note2_is_modified;
  }


  /** 
   * <em>note3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNote3()
  {
    return note3;
  }


  /** 
   * <em>note3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNote3IsSet() {
    return note3_is_set;
  }


  /** 
   * <em>note3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNote3IsModified() {
    return note3_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AdministratorUploadPK(administrator_upload_id);
  }


  /** 
   * <em>administrator_upload_id</em>カラムの値を設定します。 
   *
   * @@param p_administratorUploadId <em>administrator_upload_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAdministratorUploadId( long p_administratorUploadId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    administrator_upload_id = p_administratorUploadId;
    administrator_upload_id_is_set = true;
    administrator_upload_id_is_modified = true;
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
   * <em>product_type</em>カラムの値を設定します。 
   *
   * @@param p_productType <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public final void setProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_type = p_productType;
    product_type_is_set = true;
    product_type_is_modified = true;
  }


  /** 
   * <em>upload_file_id</em>カラムの値を設定します。 
   *
   * @@param p_uploadFileId <em>upload_file_id</em>カラムの値をあらわす128桁以下のString値 
   */
  public final void setUploadFileId( String p_uploadFileId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    upload_file_id = p_uploadFileId;
    upload_file_id_is_set = true;
    upload_file_id_is_modified = true;
  }


  /** 
   * <em>upload_start_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_uploadStartTimestamp <em>upload_start_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setUploadStartTimestamp( java.sql.Timestamp p_uploadStartTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    upload_start_timestamp = p_uploadStartTimestamp;
    upload_start_timestamp_is_set = true;
    upload_start_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>upload_start_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setUploadStartTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    upload_start_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    upload_start_timestamp_is_set = true;
    upload_start_timestamp_is_modified = true;
  }


  /** 
   * <em>upload_end_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_uploadEndTimestamp <em>upload_end_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setUploadEndTimestamp( java.sql.Timestamp p_uploadEndTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    upload_end_timestamp = p_uploadEndTimestamp;
    upload_end_timestamp_is_set = true;
    upload_end_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>upload_end_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setUploadEndTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    upload_end_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    upload_end_timestamp_is_set = true;
    upload_end_timestamp_is_modified = true;
  }


  /** 
   * <em>message_code</em>カラムの値を設定します。 
   *
   * @@param p_messageCode <em>message_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setMessageCode( String p_messageCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    message_code = p_messageCode;
    message_code_is_set = true;
    message_code_is_modified = true;
  }


  /** 
   * <em>upload_count</em>カラムの値を設定します。 
   *
   * @@param p_uploadCount <em>upload_count</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setUploadCount( int p_uploadCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    upload_count = new Integer(p_uploadCount);
    upload_count_is_set = true;
    upload_count_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>upload_count</em>カラムに値を設定します。 
   */
  public final void setUploadCount( Integer p_uploadCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    upload_count = p_uploadCount;
    upload_count_is_set = true;
    upload_count_is_modified = true;
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
   * <em>upload_key</em>カラムの値を設定します。 
   *
   * @@param p_uploadKey <em>upload_key</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setUploadKey( long p_uploadKey )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    upload_key = new Long(p_uploadKey);
    upload_key_is_set = true;
    upload_key_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>upload_key</em>カラムに値を設定します。 
   */
  public final void setUploadKey( Long p_uploadKey )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    upload_key = p_uploadKey;
    upload_key_is_set = true;
    upload_key_is_modified = true;
  }


  /** 
   * <em>note1</em>カラムの値を設定します。 
   *
   * @@param p_note1 <em>note1</em>カラムの値をあらわす128桁以下のString値 
   */
  public final void setNote1( String p_note1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    note1 = p_note1;
    note1_is_set = true;
    note1_is_modified = true;
  }


  /** 
   * <em>note2</em>カラムの値を設定します。 
   *
   * @@param p_note2 <em>note2</em>カラムの値をあらわす128桁以下のString値 
   */
  public final void setNote2( String p_note2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    note2 = p_note2;
    note2_is_set = true;
    note2_is_modified = true;
  }


  /** 
   * <em>note3</em>カラムの値を設定します。 
   *
   * @@param p_note3 <em>note3</em>カラムの値をあらわす128桁以下のString値 
   */
  public final void setNote3( String p_note3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    note3 = p_note3;
    note3_is_set = true;
    note3_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("administrator_upload_id") ) {
                    return new Long( administrator_upload_id );
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
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
                break;
            case 'm':
                if ( name.equals("message_code") ) {
                    return this.message_code;
                }
                break;
            case 'n':
                if ( name.equals("note1") ) {
                    return this.note1;
                }
                else if ( name.equals("note2") ) {
                    return this.note2;
                }
                else if ( name.equals("note3") ) {
                    return this.note3;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                break;
            case 'u':
                if ( name.equals("upload_file_id") ) {
                    return this.upload_file_id;
                }
                else if ( name.equals("upload_start_timestamp") ) {
                    return this.upload_start_timestamp;
                }
                else if ( name.equals("upload_end_timestamp") ) {
                    return this.upload_end_timestamp;
                }
                else if ( name.equals("upload_count") ) {
                    return this.upload_count;
                }
                else if ( name.equals("upload_key") ) {
                    return this.upload_key;
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
                if ( name.equals("administrator_upload_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'administrator_upload_id' must be Long: '"+value+"' is not." );
                    this.administrator_upload_id = ((Long) value).longValue();
                    if (this.administrator_upload_id_is_set)
                        this.administrator_upload_id_is_modified = true;
                    this.administrator_upload_id_is_set = true;
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
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("message_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'message_code' must be String: '"+value+"' is not." );
                    this.message_code = (String) value;
                    if (this.message_code_is_set)
                        this.message_code_is_modified = true;
                    this.message_code_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("note1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'note1' must be String: '"+value+"' is not." );
                    this.note1 = (String) value;
                    if (this.note1_is_set)
                        this.note1_is_modified = true;
                    this.note1_is_set = true;
                    return;
                }
                else if ( name.equals("note2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'note2' must be String: '"+value+"' is not." );
                    this.note2 = (String) value;
                    if (this.note2_is_set)
                        this.note2_is_modified = true;
                    this.note2_is_set = true;
                    return;
                }
                else if ( name.equals("note3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'note3' must be String: '"+value+"' is not." );
                    this.note3 = (String) value;
                    if (this.note3_is_set)
                        this.note3_is_modified = true;
                    this.note3_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("upload_file_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'upload_file_id' must be String: '"+value+"' is not." );
                    this.upload_file_id = (String) value;
                    if (this.upload_file_id_is_set)
                        this.upload_file_id_is_modified = true;
                    this.upload_file_id_is_set = true;
                    return;
                }
                else if ( name.equals("upload_start_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'upload_start_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.upload_start_timestamp = (java.sql.Timestamp) value;
                    if (this.upload_start_timestamp_is_set)
                        this.upload_start_timestamp_is_modified = true;
                    this.upload_start_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("upload_end_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'upload_end_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.upload_end_timestamp = (java.sql.Timestamp) value;
                    if (this.upload_end_timestamp_is_set)
                        this.upload_end_timestamp_is_modified = true;
                    this.upload_end_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("upload_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'upload_count' must be Integer: '"+value+"' is not." );
                    this.upload_count = (Integer) value;
                    if (this.upload_count_is_set)
                        this.upload_count_is_modified = true;
                    this.upload_count_is_set = true;
                    return;
                }
                else if ( name.equals("upload_key") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'upload_key' must be Long: '"+value+"' is not." );
                    this.upload_key = (Long) value;
                    if (this.upload_key_is_set)
                        this.upload_key_is_modified = true;
                    this.upload_key_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
