head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.29.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocDivManagementParams.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * doc_div_managementテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link DocDivManagementRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link DocDivManagementParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link DocDivManagementParams}が{@@link DocDivManagementRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DocDivManagementPK 
 * @@see DocDivManagementRow 
 */
public class DocDivManagementParams extends Params implements DocDivManagementRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "doc_div_management";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = DocDivManagementRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return DocDivManagementRow.TYPE;
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
   * <em>document_div</em>カラムの値 
   */
  public  String  document_div;

  /** 
   * <em>document_category</em>カラムの値 
   */
  public  String  document_category;

  /** 
   * <em>document_check_div</em>カラムの値 
   */
  public  String  document_check_div;

  /** 
   * <em>document_number</em>カラムの値 
   */
  public  String  document_number;

  /** 
   * <em>document_name</em>カラムの値 
   */
  public  String  document_name;

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
  private boolean document_div_is_set = false;
  private boolean document_div_is_modified = false;
  private boolean document_check_div_is_set = false;
  private boolean document_check_div_is_modified = false;
  private boolean document_number_is_set = false;
  private boolean document_number_is_modified = false;
  private boolean document_name_is_set = false;
  private boolean document_name_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean document_category_is_set = false;
  private boolean document_category_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "document_div=" + document_div
      + "," + "document_category=" + document_category
      + "," + "document_check_div=" +document_check_div
      + "," + "document_number=" +document_number
      + "," + "document_name=" +document_name
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のDocDivManagementParamsオブジェクトを作成します。 
   */
  public DocDivManagementParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    document_div_is_modified = true;
    document_category_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のDocDivManagementRowオブジェクトの値を利用してDocDivManagementParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するDocDivManagementRowオブジェクト 
   */
  public DocDivManagementParams( DocDivManagementRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    document_div = p_row.getDocumentDiv();
    document_div_is_set = p_row.getDocumentDivIsSet();
    document_div_is_modified = p_row.getDocumentDivIsModified();
    document_category = p_row.getDocumentCategory();
    document_category_is_set = p_row.getDocumentCategoryIsSet();
    document_category_is_modified = p_row.getDocumentCategoryIsModified();
    document_check_div = p_row.getDocumentCheckDiv();
    document_check_div_is_set = p_row.getDocumentCheckDivIsSet();
    document_check_div_is_modified = p_row.getDocumentCheckDivIsModified();
    document_number = p_row.getDocumentNumber();
    document_number_is_set = p_row.getDocumentNumberIsSet();
    document_number_is_modified = p_row.getDocumentNumberIsModified();
    document_name = p_row.getDocumentName();
    document_name_is_set = p_row.getDocumentNameIsSet();
    document_name_is_modified = p_row.getDocumentNameIsModified();
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
      document_check_div_is_set = true;
      document_check_div_is_modified = true;
      document_number_is_set = true;
      document_number_is_modified = true;
      document_name_is_set = true;
      document_name_is_modified = true;
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
    if ( !( other instanceof DocDivManagementRow ) )
       return false;
    return fieldsEqual( (DocDivManagementRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のDocDivManagementRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( DocDivManagementRow row )
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
    if ( document_div == null ) {
      if ( row.getDocumentDiv() != null )
        return false;
    } else if ( !document_div.equals( row.getDocumentDiv() ) ) {
        return false;
    }
    if ( document_check_div == null ) {
      if ( row.getDocumentCheckDiv() != null )
        return false;
    } else if ( !document_check_div.equals( row.getDocumentCheckDiv() ) ) {
        return false;
    }
    if ( document_number == null ) {
      if ( row.getDocumentNumber() != null )
        return false;
    } else if ( !document_number.equals( row.getDocumentNumber() ) ) {
        return false;
    }
    if ( document_name == null ) {
      if ( row.getDocumentName() != null )
        return false;
    } else if ( !document_name.equals( row.getDocumentName() ) ) {
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
    if ( document_category == null ) {
      if ( row.getDocumentCategory() != null )
        return false;
    } else if ( !document_category.equals( row.getDocumentCategory() ) ) {
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
        + (document_div!=null? document_div.hashCode(): 0) 
        + (document_check_div!=null? document_check_div.hashCode(): 0) 
        + (document_number!=null? document_number.hashCode(): 0) 
        + (document_name!=null? document_name.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (document_category!=null? document_category.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !document_check_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'document_check_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("document_div",document_div);
		map.put("document_check_div",document_check_div);
		if ( document_number_is_set )
			map.put("document_number",document_number);
		if ( document_name != null )
			map.put("document_name",document_name);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("document_category",document_category);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( document_check_div_is_modified )
			map.put("document_check_div",document_check_div);
		if ( document_number_is_modified )
			map.put("document_number",document_number);
		if ( document_name_is_modified )
			map.put("document_name",document_name);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( document_check_div_is_set )
				map.put("document_check_div",document_check_div);
			if ( document_number_is_set )
				map.put("document_number",document_number);
			map.put("document_name",document_name);
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
   * <em>document_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDocumentDiv()
  {
    return document_div;
  }


  /** 
   * <em>document_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocumentDivIsSet() {
    return document_div_is_set;
  }


  /** 
   * <em>document_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocumentDivIsModified() {
    return document_div_is_modified;
  }


  /** 
   * <em>document_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDocumentCheckDiv()
  {
    return document_check_div;
  }


  /** 
   * <em>document_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocumentCheckDivIsSet() {
    return document_check_div_is_set;
  }


  /** 
   * <em>document_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocumentCheckDivIsModified() {
    return document_check_div_is_modified;
  }


  /** 
   * <em>document_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDocumentNumber()
  {
    return document_number;
  }


  /** 
   * <em>document_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocumentNumberIsSet() {
    return document_number_is_set;
  }


  /** 
   * <em>document_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocumentNumberIsModified() {
    return document_number_is_modified;
  }


  /** 
   * <em>document_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDocumentName()
  {
    return document_name;
  }


  /** 
   * <em>document_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocumentNameIsSet() {
    return document_name_is_set;
  }


  /** 
   * <em>document_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocumentNameIsModified() {
    return document_name_is_modified;
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
   * <em>document_category</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDocumentCategory()
  {
    return document_category;
  }


  /** 
   * <em>document_category</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocumentCategoryIsSet() {
    return document_category_is_set;
  }


  /** 
   * <em>document_category</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocumentCategoryIsModified() {
    return document_category_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new DocDivManagementPK(institution_code, branch_code, document_div, document_category);
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
   * <em>document_div</em>カラムの値を設定します。 
   *
   * @@param p_documentDiv <em>document_div</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setDocumentDiv( String p_documentDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    document_div = p_documentDiv;
    document_div_is_set = true;
    document_div_is_modified = true;
  }


  /** 
   * <em>document_check_div</em>カラムの値を設定します。 
   *
   * @@param p_documentCheckDiv <em>document_check_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setDocumentCheckDiv( String p_documentCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    document_check_div = p_documentCheckDiv;
    document_check_div_is_set = true;
    document_check_div_is_modified = true;
  }


  /** 
   * <em>document_number</em>カラムの値を設定します。 
   *
   * @@param p_documentNumber <em>document_number</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setDocumentNumber( String p_documentNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    document_number = p_documentNumber;
    document_number_is_set = true;
    document_number_is_modified = true;
  }


  /** 
   * <em>document_name</em>カラムの値を設定します。 
   *
   * @@param p_documentName <em>document_name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setDocumentName( String p_documentName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    document_name = p_documentName;
    document_name_is_set = true;
    document_name_is_modified = true;
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
   * <em>document_category</em>カラムの値を設定します。 
   *
   * @@param p_documentCategory <em>document_category</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setDocumentCategory( String p_documentCategory )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    document_category = p_documentCategory;
    document_category_is_set = true;
    document_category_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
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
                if ( name.equals("document_div") ) {
                    return this.document_div;
                }
                else if ( name.equals("document_check_div") ) {
                    return this.document_check_div;
                }
                else if ( name.equals("document_number") ) {
                    return this.document_number;
                }
                else if ( name.equals("document_name") ) {
                    return this.document_name;
                }
                else if ( name.equals("document_category") ) {
                    return this.document_category;
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
                if ( name.equals("document_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'document_div' must be String: '"+value+"' is not." );
                    this.document_div = (String) value;
                    if (this.document_div_is_set)
                        this.document_div_is_modified = true;
                    this.document_div_is_set = true;
                    return;
                }
                else if ( name.equals("document_check_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'document_check_div' must be String: '"+value+"' is not." );
                    this.document_check_div = (String) value;
                    if (this.document_check_div_is_set)
                        this.document_check_div_is_modified = true;
                    this.document_check_div_is_set = true;
                    return;
                }
                else if ( name.equals("document_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'document_number' must be String: '"+value+"' is not." );
                    this.document_number = (String) value;
                    if (this.document_number_is_set)
                        this.document_number_is_modified = true;
                    this.document_number_is_set = true;
                    return;
                }
                else if ( name.equals("document_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'document_name' must be String: '"+value+"' is not." );
                    this.document_name = (String) value;
                    if (this.document_name_is_set)
                        this.document_name_is_modified = true;
                    this.document_name_is_set = true;
                    return;
                }
                else if ( name.equals("document_category") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'document_category' must be String: '"+value+"' is not." );
                    this.document_category = (String) value;
                    if (this.document_category_is_set)
                        this.document_category_is_modified = true;
                    this.document_category_is_set = true;
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
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
