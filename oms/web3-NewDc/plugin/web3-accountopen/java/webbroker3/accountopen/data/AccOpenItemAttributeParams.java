head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.11.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenItemAttributeParams.java;


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
 * acc_open_item_attributeテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AccOpenItemAttributeRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AccOpenItemAttributeParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AccOpenItemAttributeParams}が{@@link AccOpenItemAttributeRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenItemAttributePK 
 * @@see AccOpenItemAttributeRow 
 */
public class AccOpenItemAttributeParams extends Params implements AccOpenItemAttributeRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "acc_open_item_attribute";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AccOpenItemAttributeRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AccOpenItemAttributeRow.TYPE;
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
   * <em>account_div</em>カラムの値 
   */
  public  String  account_div;

  /** 
   * <em>item_symbol_name</em>カラムの値 
   */
  public  String  item_symbol_name;

  /** 
   * <em>attribute_value</em>カラムの値 
   */
  public  String  attribute_value;

  /** 
   * <em>attribute_name</em>カラムの値 
   */
  public  String  attribute_name;

  /** 
   * <em>range_from</em>カラムの値 
   */
  public  Double  range_from;

  /** 
   * <em>range_to</em>カラムの値 
   */
  public  Double  range_to;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_div_is_set = false;
  private boolean account_div_is_modified = false;
  private boolean item_symbol_name_is_set = false;
  private boolean item_symbol_name_is_modified = false;
  private boolean attribute_name_is_set = false;
  private boolean attribute_name_is_modified = false;
  private boolean attribute_value_is_set = false;
  private boolean attribute_value_is_modified = false;
  private boolean range_from_is_set = false;
  private boolean range_from_is_modified = false;
  private boolean range_to_is_set = false;
  private boolean range_to_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "account_div=" + account_div
      + "," + "item_symbol_name=" + item_symbol_name
      + "," + "attribute_value=" + attribute_value
      + "," + "attribute_name=" +attribute_name
      + "," + "range_from=" +range_from
      + "," + "range_to=" +range_to
      + "}";
  }


  /** 
   * 値が未設定のAccOpenItemAttributeParamsオブジェクトを作成します。 
   */
  public AccOpenItemAttributeParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_div_is_modified = true;
    item_symbol_name_is_modified = true;
    attribute_value_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAccOpenItemAttributeRowオブジェクトの値を利用してAccOpenItemAttributeParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAccOpenItemAttributeRowオブジェクト 
   */
  public AccOpenItemAttributeParams( AccOpenItemAttributeRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_div = p_row.getAccountDiv();
    account_div_is_set = p_row.getAccountDivIsSet();
    account_div_is_modified = p_row.getAccountDivIsModified();
    item_symbol_name = p_row.getItemSymbolName();
    item_symbol_name_is_set = p_row.getItemSymbolNameIsSet();
    item_symbol_name_is_modified = p_row.getItemSymbolNameIsModified();
    attribute_value = p_row.getAttributeValue();
    attribute_value_is_set = p_row.getAttributeValueIsSet();
    attribute_value_is_modified = p_row.getAttributeValueIsModified();
    attribute_name = p_row.getAttributeName();
    attribute_name_is_set = p_row.getAttributeNameIsSet();
    attribute_name_is_modified = p_row.getAttributeNameIsModified();
    if ( !p_row.getRangeFromIsNull() )
      range_from = new Double( p_row.getRangeFrom() );
    range_from_is_set = p_row.getRangeFromIsSet();
    range_from_is_modified = p_row.getRangeFromIsModified();
    if ( !p_row.getRangeToIsNull() )
      range_to = new Double( p_row.getRangeTo() );
    range_to_is_set = p_row.getRangeToIsSet();
    range_to_is_modified = p_row.getRangeToIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      attribute_name_is_set = true;
      attribute_name_is_modified = true;
      range_from_is_set = true;
      range_from_is_modified = true;
      range_to_is_set = true;
      range_to_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AccOpenItemAttributeRow ) )
       return false;
    return fieldsEqual( (AccOpenItemAttributeRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAccOpenItemAttributeRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AccOpenItemAttributeRow row )
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
    if ( account_div == null ) {
      if ( row.getAccountDiv() != null )
        return false;
    } else if ( !account_div.equals( row.getAccountDiv() ) ) {
        return false;
    }
    if ( item_symbol_name == null ) {
      if ( row.getItemSymbolName() != null )
        return false;
    } else if ( !item_symbol_name.equals( row.getItemSymbolName() ) ) {
        return false;
    }
    if ( attribute_name == null ) {
      if ( row.getAttributeName() != null )
        return false;
    } else if ( !attribute_name.equals( row.getAttributeName() ) ) {
        return false;
    }
    if ( attribute_value == null ) {
      if ( row.getAttributeValue() != null )
        return false;
    } else if ( !attribute_value.equals( row.getAttributeValue() ) ) {
        return false;
    }
    if ( range_from == null ) {
      if ( !row.getRangeFromIsNull() )
        return false;
    } else if ( row.getRangeFromIsNull() || ( range_from.doubleValue() != row.getRangeFrom() ) ) {
        return false;
    }
    if ( range_to == null ) {
      if ( !row.getRangeToIsNull() )
        return false;
    } else if ( row.getRangeToIsNull() || ( range_to.doubleValue() != row.getRangeTo() ) ) {
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
        + (account_div!=null? account_div.hashCode(): 0) 
        + (item_symbol_name!=null? item_symbol_name.hashCode(): 0) 
        + (attribute_name!=null? attribute_name.hashCode(): 0) 
        + (attribute_value!=null? attribute_value.hashCode(): 0) 
        + (range_from!=null? range_from.hashCode(): 0) 
        + (range_to!=null? range_to.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !attribute_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'attribute_name' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_div",account_div);
		map.put("item_symbol_name",item_symbol_name);
		map.put("attribute_name",attribute_name);
		map.put("attribute_value",attribute_value);
		if ( range_from != null )
			map.put("range_from",range_from);
		if ( range_to != null )
			map.put("range_to",range_to);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( attribute_name_is_modified )
			map.put("attribute_name",attribute_name);
		if ( range_from_is_modified )
			map.put("range_from",range_from);
		if ( range_to_is_modified )
			map.put("range_to",range_to);
		if (map.size() == 0) {
			if ( attribute_name_is_set )
				map.put("attribute_name",attribute_name);
			map.put("range_from",range_from);
			map.put("range_to",range_to);
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
   * <em>item_symbol_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getItemSymbolName()
  {
    return item_symbol_name;
  }


  /** 
   * <em>item_symbol_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemSymbolNameIsSet() {
    return item_symbol_name_is_set;
  }


  /** 
   * <em>item_symbol_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemSymbolNameIsModified() {
    return item_symbol_name_is_modified;
  }


  /** 
   * <em>attribute_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAttributeName()
  {
    return attribute_name;
  }


  /** 
   * <em>attribute_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttributeNameIsSet() {
    return attribute_name_is_set;
  }


  /** 
   * <em>attribute_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttributeNameIsModified() {
    return attribute_name_is_modified;
  }


  /** 
   * <em>attribute_value</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAttributeValue()
  {
    return attribute_value;
  }


  /** 
   * <em>attribute_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttributeValueIsSet() {
    return attribute_value_is_set;
  }


  /** 
   * <em>attribute_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttributeValueIsModified() {
    return attribute_value_is_modified;
  }


  /** 
   * <em>range_from</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRangeFrom()
  {
    return ( range_from==null? ((double)0): range_from.doubleValue() );
  }


  /** 
   * <em>range_from</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRangeFromIsNull()
  {
    return range_from == null;
  }


  /** 
   * <em>range_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRangeFromIsSet() {
    return range_from_is_set;
  }


  /** 
   * <em>range_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRangeFromIsModified() {
    return range_from_is_modified;
  }


  /** 
   * <em>range_to</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRangeTo()
  {
    return ( range_to==null? ((double)0): range_to.doubleValue() );
  }


  /** 
   * <em>range_to</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRangeToIsNull()
  {
    return range_to == null;
  }


  /** 
   * <em>range_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRangeToIsSet() {
    return range_to_is_set;
  }


  /** 
   * <em>range_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRangeToIsModified() {
    return range_to_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AccOpenItemAttributePK(institution_code, branch_code, account_div, item_symbol_name, attribute_value);
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
   * <em>item_symbol_name</em>カラムの値を設定します。 
   *
   * @@param p_itemSymbolName <em>item_symbol_name</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setItemSymbolName( String p_itemSymbolName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_symbol_name = p_itemSymbolName;
    item_symbol_name_is_set = true;
    item_symbol_name_is_modified = true;
  }


  /** 
   * <em>attribute_name</em>カラムの値を設定します。 
   *
   * @@param p_attributeName <em>attribute_name</em>カラムの値をあらわす64桁以下のString値 
   */
  public final void setAttributeName( String p_attributeName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    attribute_name = p_attributeName;
    attribute_name_is_set = true;
    attribute_name_is_modified = true;
  }


  /** 
   * <em>attribute_value</em>カラムの値を設定します。 
   *
   * @@param p_attributeValue <em>attribute_value</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setAttributeValue( String p_attributeValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    attribute_value = p_attributeValue;
    attribute_value_is_set = true;
    attribute_value_is_modified = true;
  }


  /** 
   * <em>range_from</em>カラムの値を設定します。 
   *
   * @@param p_rangeFrom <em>range_from</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRangeFrom( double p_rangeFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    range_from = new Double(p_rangeFrom);
    range_from_is_set = true;
    range_from_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>range_from</em>カラムに値を設定します。 
   */
  public final void setRangeFrom( Double p_rangeFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    range_from = p_rangeFrom;
    range_from_is_set = true;
    range_from_is_modified = true;
  }


  /** 
   * <em>range_to</em>カラムの値を設定します。 
   *
   * @@param p_rangeTo <em>range_to</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRangeTo( double p_rangeTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    range_to = new Double(p_rangeTo);
    range_to_is_set = true;
    range_to_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>range_to</em>カラムに値を設定します。 
   */
  public final void setRangeTo( Double p_rangeTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    range_to = p_rangeTo;
    range_to_is_set = true;
    range_to_is_modified = true;
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
                else if ( name.equals("attribute_name") ) {
                    return this.attribute_name;
                }
                else if ( name.equals("attribute_value") ) {
                    return this.attribute_value;
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
                else if ( name.equals("item_symbol_name") ) {
                    return this.item_symbol_name;
                }
                break;
            case 'r':
                if ( name.equals("range_from") ) {
                    return this.range_from;
                }
                else if ( name.equals("range_to") ) {
                    return this.range_to;
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
                else if ( name.equals("attribute_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'attribute_name' must be String: '"+value+"' is not." );
                    this.attribute_name = (String) value;
                    if (this.attribute_name_is_set)
                        this.attribute_name_is_modified = true;
                    this.attribute_name_is_set = true;
                    return;
                }
                else if ( name.equals("attribute_value") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'attribute_value' must be String: '"+value+"' is not." );
                    this.attribute_value = (String) value;
                    if (this.attribute_value_is_set)
                        this.attribute_value_is_modified = true;
                    this.attribute_value_is_set = true;
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
                else if ( name.equals("item_symbol_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_symbol_name' must be String: '"+value+"' is not." );
                    this.item_symbol_name = (String) value;
                    if (this.item_symbol_name_is_set)
                        this.item_symbol_name_is_modified = true;
                    this.item_symbol_name_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("range_from") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'range_from' must be Double: '"+value+"' is not." );
                    this.range_from = (Double) value;
                    if (this.range_from_is_set)
                        this.range_from_is_modified = true;
                    this.range_from_is_set = true;
                    return;
                }
                else if ( name.equals("range_to") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'range_to' must be Double: '"+value+"' is not." );
                    this.range_to = (Double) value;
                    if (this.range_to_is_set)
                        this.range_to_is_modified = true;
                    this.range_to_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
