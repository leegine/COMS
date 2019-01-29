head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.39.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	CooperateBankMasterParams.java;


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
 * cooperate_bank_masterテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link CooperateBankMasterRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link CooperateBankMasterParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link CooperateBankMasterParams}が{@@link CooperateBankMasterRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CooperateBankMasterPK 
 * @@see CooperateBankMasterRow 
 */
public class CooperateBankMasterParams extends Params implements CooperateBankMasterRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "cooperate_bank_master";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = CooperateBankMasterRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return CooperateBankMasterRow.TYPE;
  }


  /** 
   * <em>pay_scheme_id</em>カラムの値 
   */
  public  String  pay_scheme_id;

  /** 
   * <em>name</em>カラムの値 
   */
  public  String  name;

  /** 
   * <em>short_name</em>カラムの値 
   */
  public  String  short_name;

  private boolean pay_scheme_id_is_set = false;
  private boolean pay_scheme_id_is_modified = false;
  private boolean name_is_set = false;
  private boolean name_is_modified = false;
  private boolean short_name_is_set = false;
  private boolean short_name_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "pay_scheme_id=" + pay_scheme_id
      + "," + "name=" +name
      + "," + "short_name=" +short_name
      + "}";
  }


  /** 
   * 値が未設定のCooperateBankMasterParamsオブジェクトを作成します。 
   */
  public CooperateBankMasterParams() {
    pay_scheme_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のCooperateBankMasterRowオブジェクトの値を利用してCooperateBankMasterParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するCooperateBankMasterRowオブジェクト 
   */
  public CooperateBankMasterParams( CooperateBankMasterRow p_row )
  {
    pay_scheme_id = p_row.getPaySchemeId();
    pay_scheme_id_is_set = p_row.getPaySchemeIdIsSet();
    pay_scheme_id_is_modified = p_row.getPaySchemeIdIsModified();
    name = p_row.getName();
    name_is_set = p_row.getNameIsSet();
    name_is_modified = p_row.getNameIsModified();
    short_name = p_row.getShortName();
    short_name_is_set = p_row.getShortNameIsSet();
    short_name_is_modified = p_row.getShortNameIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      name_is_set = true;
      name_is_modified = true;
      short_name_is_set = true;
      short_name_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof CooperateBankMasterRow ) )
       return false;
    return fieldsEqual( (CooperateBankMasterRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のCooperateBankMasterRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( CooperateBankMasterRow row )
  {
    if ( pay_scheme_id == null ) {
      if ( row.getPaySchemeId() != null )
        return false;
    } else if ( !pay_scheme_id.equals( row.getPaySchemeId() ) ) {
        return false;
    }
    if ( name == null ) {
      if ( row.getName() != null )
        return false;
    } else if ( !name.equals( row.getName() ) ) {
        return false;
    }
    if ( short_name == null ) {
      if ( row.getShortName() != null )
        return false;
    } else if ( !short_name.equals( row.getShortName() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (pay_scheme_id!=null? pay_scheme_id.hashCode(): 0) 
        + (name!=null? name.hashCode(): 0) 
        + (short_name!=null? short_name.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !name_is_set )
			throw new IllegalArgumentException("non-nullable field 'name' must be set before inserting.");
		if ( !short_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'short_name' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("pay_scheme_id",pay_scheme_id);
		map.put("name",name);
		map.put("short_name",short_name);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( name_is_modified )
			map.put("name",name);
		if ( short_name_is_modified )
			map.put("short_name",short_name);
		if (map.size() == 0) {
			if ( name_is_set )
				map.put("name",name);
			if ( short_name_is_set )
				map.put("short_name",short_name);
		}
		return map;
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
   * <em>name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getName()
  {
    return name;
  }


  /** 
   * <em>name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameIsSet() {
    return name_is_set;
  }


  /** 
   * <em>name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameIsModified() {
    return name_is_modified;
  }


  /** 
   * <em>short_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShortName()
  {
    return short_name;
  }


  /** 
   * <em>short_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortNameIsSet() {
    return short_name_is_set;
  }


  /** 
   * <em>short_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortNameIsModified() {
    return short_name_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new CooperateBankMasterPK(pay_scheme_id);
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
   * <em>name</em>カラムの値を設定します。 
   *
   * @@param p_name <em>name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setName( String p_name )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name = p_name;
    name_is_set = true;
    name_is_modified = true;
  }


  /** 
   * <em>short_name</em>カラムの値を設定します。 
   *
   * @@param p_shortName <em>short_name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setShortName( String p_shortName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_name = p_shortName;
    short_name_is_set = true;
    short_name_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'n':
                if ( name.equals("name") ) {
                    return this.name;
                }
                break;
            case 'p':
                if ( name.equals("pay_scheme_id") ) {
                    return this.pay_scheme_id;
                }
                break;
            case 's':
                if ( name.equals("short_name") ) {
                    return this.short_name;
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
            case 'n':
                if ( name.equals("name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'name' must be String: '"+value+"' is not." );
                    this.name = (String) value;
                    if (this.name_is_set)
                        this.name_is_modified = true;
                    this.name_is_set = true;
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
            case 's':
                if ( name.equals("short_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_name' must be String: '"+value+"' is not." );
                    this.short_name = (String) value;
                    if (this.short_name_is_set)
                        this.short_name_is_modified = true;
                    this.short_name_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
