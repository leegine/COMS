head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.40.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	CompBankCareerManagementParams.java;


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
 * comp_bank_career_managementテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link CompBankCareerManagementRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link CompBankCareerManagementParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link CompBankCareerManagementParams}が{@@link CompBankCareerManagementRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CompBankCareerManagementPK 
 * @@see CompBankCareerManagementRow 
 */
public class CompBankCareerManagementParams extends Params implements CompBankCareerManagementRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "comp_bank_career_management";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = CompBankCareerManagementRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return CompBankCareerManagementRow.TYPE;
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
   * <em>pay_scheme_id</em>カラムの値 
   */
  public  String  pay_scheme_id;

  /** 
   * <em>career_div</em>カラムの値 
   */
  public  String  career_div;

  /** 
   * <em>mg_flg</em>カラムの値 
   */
  public  String  mg_flg;

  /** 
   * <em>last_update_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_update_timestamp;

  /** 
   * <em>last_update_user</em>カラムの値 
   */
  public  String  last_update_user;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean pay_scheme_id_is_set = false;
  private boolean pay_scheme_id_is_modified = false;
  private boolean career_div_is_set = false;
  private boolean career_div_is_modified = false;
  private boolean mg_flg_is_set = false;
  private boolean mg_flg_is_modified = false;
  private boolean last_update_timestamp_is_set = false;
  private boolean last_update_timestamp_is_modified = false;
  private boolean last_update_user_is_set = false;
  private boolean last_update_user_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "pay_scheme_id=" + pay_scheme_id
      + "," + "career_div=" + career_div
      + "," + "mg_flg=" +mg_flg
      + "," + "last_update_timestamp=" +last_update_timestamp
      + "," + "last_update_user=" +last_update_user
      + "}";
  }


  /** 
   * 値が未設定のCompBankCareerManagementParamsオブジェクトを作成します。 
   */
  public CompBankCareerManagementParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    pay_scheme_id_is_modified = true;
    career_div_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のCompBankCareerManagementRowオブジェクトの値を利用してCompBankCareerManagementParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するCompBankCareerManagementRowオブジェクト 
   */
  public CompBankCareerManagementParams( CompBankCareerManagementRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    pay_scheme_id = p_row.getPaySchemeId();
    pay_scheme_id_is_set = p_row.getPaySchemeIdIsSet();
    pay_scheme_id_is_modified = p_row.getPaySchemeIdIsModified();
    career_div = p_row.getCareerDiv();
    career_div_is_set = p_row.getCareerDivIsSet();
    career_div_is_modified = p_row.getCareerDivIsModified();
    mg_flg = p_row.getMgFlg();
    mg_flg_is_set = p_row.getMgFlgIsSet();
    mg_flg_is_modified = p_row.getMgFlgIsModified();
    last_update_timestamp = p_row.getLastUpdateTimestamp();
    last_update_timestamp_is_set = p_row.getLastUpdateTimestampIsSet();
    last_update_timestamp_is_modified = p_row.getLastUpdateTimestampIsModified();
    last_update_user = p_row.getLastUpdateUser();
    last_update_user_is_set = p_row.getLastUpdateUserIsSet();
    last_update_user_is_modified = p_row.getLastUpdateUserIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      mg_flg_is_set = true;
      mg_flg_is_modified = true;
      last_update_timestamp_is_set = true;
      last_update_timestamp_is_modified = true;
      last_update_user_is_set = true;
      last_update_user_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof CompBankCareerManagementRow ) )
       return false;
    return fieldsEqual( (CompBankCareerManagementRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のCompBankCareerManagementRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( CompBankCareerManagementRow row )
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
    if ( pay_scheme_id == null ) {
      if ( row.getPaySchemeId() != null )
        return false;
    } else if ( !pay_scheme_id.equals( row.getPaySchemeId() ) ) {
        return false;
    }
    if ( career_div == null ) {
      if ( row.getCareerDiv() != null )
        return false;
    } else if ( !career_div.equals( row.getCareerDiv() ) ) {
        return false;
    }
    if ( mg_flg == null ) {
      if ( row.getMgFlg() != null )
        return false;
    } else if ( !mg_flg.equals( row.getMgFlg() ) ) {
        return false;
    }
    if ( last_update_timestamp == null ) {
      if ( row.getLastUpdateTimestamp() != null )
        return false;
    } else if ( !last_update_timestamp.equals( row.getLastUpdateTimestamp() ) ) {
        return false;
    }
    if ( last_update_user == null ) {
      if ( row.getLastUpdateUser() != null )
        return false;
    } else if ( !last_update_user.equals( row.getLastUpdateUser() ) ) {
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
        + (pay_scheme_id!=null? pay_scheme_id.hashCode(): 0) 
        + (career_div!=null? career_div.hashCode(): 0) 
        + (mg_flg!=null? mg_flg.hashCode(): 0) 
        + (last_update_timestamp!=null? last_update_timestamp.hashCode(): 0) 
        + (last_update_user!=null? last_update_user.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !mg_flg_is_set )
			throw new IllegalArgumentException("non-nullable field 'mg_flg' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("pay_scheme_id",pay_scheme_id);
		map.put("career_div",career_div);
		map.put("mg_flg",mg_flg);
		if ( last_update_timestamp != null )
			map.put("last_update_timestamp",last_update_timestamp);
		if ( last_update_user != null )
			map.put("last_update_user",last_update_user);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( mg_flg_is_modified )
			map.put("mg_flg",mg_flg);
		if ( last_update_timestamp_is_modified )
			map.put("last_update_timestamp",last_update_timestamp);
		if ( last_update_user_is_modified )
			map.put("last_update_user",last_update_user);
		if (map.size() == 0) {
			if ( mg_flg_is_set )
				map.put("mg_flg",mg_flg);
			map.put("last_update_timestamp",last_update_timestamp);
			map.put("last_update_user",last_update_user);
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
   * <em>pay_scheme_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaySchemeId()
  {
    return pay_scheme_id;
  }


  /** 
   * <em>pay_scheme_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaySchemeIdIsSet() {
    return pay_scheme_id_is_set;
  }


  /** 
   * <em>pay_scheme_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaySchemeIdIsModified() {
    return pay_scheme_id_is_modified;
  }


  /** 
   * <em>career_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCareerDiv()
  {
    return career_div;
  }


  /** 
   * <em>career_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCareerDivIsSet() {
    return career_div_is_set;
  }


  /** 
   * <em>career_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCareerDivIsModified() {
    return career_div_is_modified;
  }


  /** 
   * <em>mg_flg</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMgFlg()
  {
    return mg_flg;
  }


  /** 
   * <em>mg_flg</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMgFlgIsSet() {
    return mg_flg_is_set;
  }


  /** 
   * <em>mg_flg</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMgFlgIsModified() {
    return mg_flg_is_modified;
  }


  /** 
   * <em>last_update_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdateTimestamp()
  {
    return last_update_timestamp;
  }


  /** 
   * <em>last_update_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateTimestampIsSet() {
    return last_update_timestamp_is_set;
  }


  /** 
   * <em>last_update_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateTimestampIsModified() {
    return last_update_timestamp_is_modified;
  }


  /** 
   * <em>last_update_user</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdateUser()
  {
    return last_update_user;
  }


  /** 
   * <em>last_update_user</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateUserIsSet() {
    return last_update_user_is_set;
  }


  /** 
   * <em>last_update_user</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateUserIsModified() {
    return last_update_user_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new CompBankCareerManagementPK(institution_code, branch_code, pay_scheme_id, career_div);
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
   * <em>pay_scheme_id</em>カラムの値を設定します。 
   *
   * @@param p_paySchemeId <em>pay_scheme_id</em>カラムの値をあらわす11桁以下のString値 
   */
  public final void setPaySchemeId( String p_paySchemeId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_scheme_id = p_paySchemeId;
    pay_scheme_id_is_set = true;
    pay_scheme_id_is_modified = true;
  }


  /** 
   * <em>career_div</em>カラムの値を設定します。 
   *
   * @@param p_careerDiv <em>career_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCareerDiv( String p_careerDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    career_div = p_careerDiv;
    career_div_is_set = true;
    career_div_is_modified = true;
  }


  /** 
   * <em>mg_flg</em>カラムの値を設定します。 
   *
   * @@param p_mgFlg <em>mg_flg</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMgFlg( String p_mgFlg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mg_flg = p_mgFlg;
    mg_flg_is_set = true;
    mg_flg_is_modified = true;
  }


  /** 
   * <em>last_update_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdateTimestamp <em>last_update_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdateTimestamp( java.sql.Timestamp p_lastUpdateTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_timestamp = p_lastUpdateTimestamp;
    last_update_timestamp_is_set = true;
    last_update_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_update_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdateTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_update_timestamp_is_set = true;
    last_update_timestamp_is_modified = true;
  }


  /** 
   * <em>last_update_user</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdateUser <em>last_update_user</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdateUser( String p_lastUpdateUser )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_user = p_lastUpdateUser;
    last_update_user_is_set = true;
    last_update_user_is_modified = true;
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
                if ( name.equals("career_div") ) {
                    return this.career_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_update_timestamp") ) {
                    return this.last_update_timestamp;
                }
                else if ( name.equals("last_update_user") ) {
                    return this.last_update_user;
                }
                break;
            case 'm':
                if ( name.equals("mg_flg") ) {
                    return this.mg_flg;
                }
                break;
            case 'p':
                if ( name.equals("pay_scheme_id") ) {
                    return this.pay_scheme_id;
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
                if ( name.equals("career_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'career_div' must be String: '"+value+"' is not." );
                    this.career_div = (String) value;
                    if (this.career_div_is_set)
                        this.career_div_is_modified = true;
                    this.career_div_is_set = true;
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
                if ( name.equals("last_update_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_update_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_update_timestamp = (java.sql.Timestamp) value;
                    if (this.last_update_timestamp_is_set)
                        this.last_update_timestamp_is_modified = true;
                    this.last_update_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("last_update_user") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_update_user' must be String: '"+value+"' is not." );
                    this.last_update_user = (String) value;
                    if (this.last_update_user_is_set)
                        this.last_update_user_is_modified = true;
                    this.last_update_user_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("mg_flg") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mg_flg' must be String: '"+value+"' is not." );
                    this.mg_flg = (String) value;
                    if (this.mg_flg_is_set)
                        this.mg_flg_is_modified = true;
                    this.mg_flg_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("pay_scheme_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pay_scheme_id' must be String: '"+value+"' is not." );
                    this.pay_scheme_id = (String) value;
                    if (this.pay_scheme_id_is_set)
                        this.pay_scheme_id_is_modified = true;
                    this.pay_scheme_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
