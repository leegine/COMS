head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenDlFormMasterParams.java;


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
 * acc_open_dl_form_masterテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AccOpenDlFormMasterRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AccOpenDlFormMasterParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AccOpenDlFormMasterParams}が{@@link AccOpenDlFormMasterRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenDlFormMasterPK 
 * @@see AccOpenDlFormMasterRow 
 */
public class AccOpenDlFormMasterParams extends Params implements AccOpenDlFormMasterRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "acc_open_dl_form_master";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AccOpenDlFormMasterRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AccOpenDlFormMasterRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>account_div</em>カラムの値 
   */
  public  String  account_div;

  /** 
   * <em>column_number</em>カラムの値 
   */
  public  int  column_number;

  /** 
   * <em>column_label</em>カラムの値 
   */
  public  String  column_label;

  /** 
   * <em>column_type</em>カラムの値 
   */
  public  int  column_type;

  /** 
   * <em>date_format</em>カラムの値 
   */
  public  String  date_format;

  /** 
   * <em>input_item_symbol_name</em>カラムの値 
   */
  public  String  input_item_symbol_name;

  /** 
   * <em>cat_delimitter</em>カラムの値 
   */
  public  String  cat_delimitter;

  /** 
   * <em>section_number</em>カラムの値 
   */
  public  Integer  section_number;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean account_div_is_set = false;
  private boolean account_div_is_modified = false;
  private boolean column_number_is_set = false;
  private boolean column_number_is_modified = false;
  private boolean column_label_is_set = false;
  private boolean column_label_is_modified = false;
  private boolean column_type_is_set = false;
  private boolean column_type_is_modified = false;
  private boolean date_format_is_set = false;
  private boolean date_format_is_modified = false;
  private boolean input_item_symbol_name_is_set = false;
  private boolean input_item_symbol_name_is_modified = false;
  private boolean cat_delimitter_is_set = false;
  private boolean cat_delimitter_is_modified = false;
  private boolean section_number_is_set = false;
  private boolean section_number_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "account_div=" + account_div
      + "," + "column_number=" + column_number
      + "," + "column_label=" +column_label
      + "," + "column_type=" +column_type
      + "," + "date_format=" +date_format
      + "," + "input_item_symbol_name=" +input_item_symbol_name
      + "," + "cat_delimitter=" +cat_delimitter
      + "," + "section_number=" +section_number
      + "}";
  }


  /** 
   * 値が未設定のAccOpenDlFormMasterParamsオブジェクトを作成します。 
   */
  public AccOpenDlFormMasterParams() {
    institution_code_is_modified = true;
    account_div_is_modified = true;
    column_number_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAccOpenDlFormMasterRowオブジェクトの値を利用してAccOpenDlFormMasterParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAccOpenDlFormMasterRowオブジェクト 
   */
  public AccOpenDlFormMasterParams( AccOpenDlFormMasterRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    account_div = p_row.getAccountDiv();
    account_div_is_set = p_row.getAccountDivIsSet();
    account_div_is_modified = p_row.getAccountDivIsModified();
    column_number = p_row.getColumnNumber();
    column_number_is_set = p_row.getColumnNumberIsSet();
    column_number_is_modified = p_row.getColumnNumberIsModified();
    column_label = p_row.getColumnLabel();
    column_label_is_set = p_row.getColumnLabelIsSet();
    column_label_is_modified = p_row.getColumnLabelIsModified();
    column_type = p_row.getColumnType();
    column_type_is_set = p_row.getColumnTypeIsSet();
    column_type_is_modified = p_row.getColumnTypeIsModified();
    date_format = p_row.getDateFormat();
    date_format_is_set = p_row.getDateFormatIsSet();
    date_format_is_modified = p_row.getDateFormatIsModified();
    input_item_symbol_name = p_row.getInputItemSymbolName();
    input_item_symbol_name_is_set = p_row.getInputItemSymbolNameIsSet();
    input_item_symbol_name_is_modified = p_row.getInputItemSymbolNameIsModified();
    cat_delimitter = p_row.getCatDelimitter();
    cat_delimitter_is_set = p_row.getCatDelimitterIsSet();
    cat_delimitter_is_modified = p_row.getCatDelimitterIsModified();
    if ( !p_row.getSectionNumberIsNull() )
      section_number = new Integer( p_row.getSectionNumber() );
    section_number_is_set = p_row.getSectionNumberIsSet();
    section_number_is_modified = p_row.getSectionNumberIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      column_label_is_set = true;
      column_label_is_modified = true;
      column_type_is_set = true;
      column_type_is_modified = true;
      date_format_is_set = true;
      date_format_is_modified = true;
      input_item_symbol_name_is_set = true;
      input_item_symbol_name_is_modified = true;
      cat_delimitter_is_set = true;
      cat_delimitter_is_modified = true;
      section_number_is_set = true;
      section_number_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AccOpenDlFormMasterRow ) )
       return false;
    return fieldsEqual( (AccOpenDlFormMasterRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAccOpenDlFormMasterRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AccOpenDlFormMasterRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( account_div == null ) {
      if ( row.getAccountDiv() != null )
        return false;
    } else if ( !account_div.equals( row.getAccountDiv() ) ) {
        return false;
    }
    if ( column_number != row.getColumnNumber() )
      return false;
    if ( column_label == null ) {
      if ( row.getColumnLabel() != null )
        return false;
    } else if ( !column_label.equals( row.getColumnLabel() ) ) {
        return false;
    }
    if ( column_type != row.getColumnType() )
      return false;
    if ( date_format == null ) {
      if ( row.getDateFormat() != null )
        return false;
    } else if ( !date_format.equals( row.getDateFormat() ) ) {
        return false;
    }
    if ( input_item_symbol_name == null ) {
      if ( row.getInputItemSymbolName() != null )
        return false;
    } else if ( !input_item_symbol_name.equals( row.getInputItemSymbolName() ) ) {
        return false;
    }
    if ( cat_delimitter == null ) {
      if ( row.getCatDelimitter() != null )
        return false;
    } else if ( !cat_delimitter.equals( row.getCatDelimitter() ) ) {
        return false;
    }
    if ( section_number == null ) {
      if ( !row.getSectionNumberIsNull() )
        return false;
    } else if ( row.getSectionNumberIsNull() || ( section_number.intValue() != row.getSectionNumber() ) ) {
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
        + (account_div!=null? account_div.hashCode(): 0) 
        + ((int) column_number)
        + (column_label!=null? column_label.hashCode(): 0) 
        + ((int) column_type)
        + (date_format!=null? date_format.hashCode(): 0) 
        + (input_item_symbol_name!=null? input_item_symbol_name.hashCode(): 0) 
        + (cat_delimitter!=null? cat_delimitter.hashCode(): 0) 
        + (section_number!=null? section_number.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !column_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'column_type' must be set before inserting.");
		if ( !input_item_symbol_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'input_item_symbol_name' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("account_div",account_div);
		map.put("column_number",new Integer(column_number));
		if ( column_label != null )
			map.put("column_label",column_label);
		map.put("column_type",new Integer(column_type));
		if ( date_format != null )
			map.put("date_format",date_format);
		map.put("input_item_symbol_name",input_item_symbol_name);
		if ( cat_delimitter != null )
			map.put("cat_delimitter",cat_delimitter);
		if ( section_number != null )
			map.put("section_number",section_number);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( column_label_is_modified )
			map.put("column_label",column_label);
		if ( column_type_is_modified )
			map.put("column_type",new Integer(column_type));
		if ( date_format_is_modified )
			map.put("date_format",date_format);
		if ( input_item_symbol_name_is_modified )
			map.put("input_item_symbol_name",input_item_symbol_name);
		if ( cat_delimitter_is_modified )
			map.put("cat_delimitter",cat_delimitter);
		if ( section_number_is_modified )
			map.put("section_number",section_number);
		if (map.size() == 0) {
			map.put("column_label",column_label);
			if ( column_type_is_set )
				map.put("column_type",new Integer(column_type));
			map.put("date_format",date_format);
			if ( input_item_symbol_name_is_set )
				map.put("input_item_symbol_name",input_item_symbol_name);
			map.put("cat_delimitter",cat_delimitter);
			map.put("section_number",section_number);
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
   * <em>column_number</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getColumnNumber()
  {
    return column_number;
  }


  /** 
   * <em>column_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColumnNumberIsSet() {
    return column_number_is_set;
  }


  /** 
   * <em>column_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColumnNumberIsModified() {
    return column_number_is_modified;
  }


  /** 
   * <em>column_label</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getColumnLabel()
  {
    return column_label;
  }


  /** 
   * <em>column_label</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColumnLabelIsSet() {
    return column_label_is_set;
  }


  /** 
   * <em>column_label</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColumnLabelIsModified() {
    return column_label_is_modified;
  }


  /** 
   * <em>column_type</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getColumnType()
  {
    return column_type;
  }


  /** 
   * <em>column_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColumnTypeIsSet() {
    return column_type_is_set;
  }


  /** 
   * <em>column_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColumnTypeIsModified() {
    return column_type_is_modified;
  }


  /** 
   * <em>date_format</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDateFormat()
  {
    return date_format;
  }


  /** 
   * <em>date_format</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDateFormatIsSet() {
    return date_format_is_set;
  }


  /** 
   * <em>date_format</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDateFormatIsModified() {
    return date_format_is_modified;
  }


  /** 
   * <em>input_item_symbol_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInputItemSymbolName()
  {
    return input_item_symbol_name;
  }


  /** 
   * <em>input_item_symbol_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInputItemSymbolNameIsSet() {
    return input_item_symbol_name_is_set;
  }


  /** 
   * <em>input_item_symbol_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInputItemSymbolNameIsModified() {
    return input_item_symbol_name_is_modified;
  }


  /** 
   * <em>cat_delimitter</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCatDelimitter()
  {
    return cat_delimitter;
  }


  /** 
   * <em>cat_delimitter</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCatDelimitterIsSet() {
    return cat_delimitter_is_set;
  }


  /** 
   * <em>cat_delimitter</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCatDelimitterIsModified() {
    return cat_delimitter_is_modified;
  }


  /** 
   * <em>section_number</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSectionNumber()
  {
    return ( section_number==null? ((int)0): section_number.intValue() );
  }


  /** 
   * <em>section_number</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSectionNumberIsNull()
  {
    return section_number == null;
  }


  /** 
   * <em>section_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSectionNumberIsSet() {
    return section_number_is_set;
  }


  /** 
   * <em>section_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSectionNumberIsModified() {
    return section_number_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AccOpenDlFormMasterPK(institution_code, account_div, column_number);
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
   * <em>column_number</em>カラムの値を設定します。 
   *
   * @@param p_columnNumber <em>column_number</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setColumnNumber( int p_columnNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    column_number = p_columnNumber;
    column_number_is_set = true;
    column_number_is_modified = true;
  }


  /** 
   * <em>column_label</em>カラムの値を設定します。 
   *
   * @@param p_columnLabel <em>column_label</em>カラムの値をあらわす64桁以下のString値 
   */
  public final void setColumnLabel( String p_columnLabel )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    column_label = p_columnLabel;
    column_label_is_set = true;
    column_label_is_modified = true;
  }


  /** 
   * <em>column_type</em>カラムの値を設定します。 
   *
   * @@param p_columnType <em>column_type</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setColumnType( int p_columnType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    column_type = p_columnType;
    column_type_is_set = true;
    column_type_is_modified = true;
  }


  /** 
   * <em>date_format</em>カラムの値を設定します。 
   *
   * @@param p_dateFormat <em>date_format</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setDateFormat( String p_dateFormat )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    date_format = p_dateFormat;
    date_format_is_set = true;
    date_format_is_modified = true;
  }


  /** 
   * <em>input_item_symbol_name</em>カラムの値を設定します。 
   *
   * @@param p_inputItemSymbolName <em>input_item_symbol_name</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setInputItemSymbolName( String p_inputItemSymbolName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    input_item_symbol_name = p_inputItemSymbolName;
    input_item_symbol_name_is_set = true;
    input_item_symbol_name_is_modified = true;
  }


  /** 
   * <em>cat_delimitter</em>カラムの値を設定します。 
   *
   * @@param p_catDelimitter <em>cat_delimitter</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCatDelimitter( String p_catDelimitter )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cat_delimitter = p_catDelimitter;
    cat_delimitter_is_set = true;
    cat_delimitter_is_modified = true;
  }


  /** 
   * <em>section_number</em>カラムの値を設定します。 
   *
   * @@param p_sectionNumber <em>section_number</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setSectionNumber( int p_sectionNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    section_number = new Integer(p_sectionNumber);
    section_number_is_set = true;
    section_number_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>section_number</em>カラムに値を設定します。 
   */
  public final void setSectionNumber( Integer p_sectionNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    section_number = p_sectionNumber;
    section_number_is_set = true;
    section_number_is_modified = true;
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
            case 'c':
                if ( name.equals("column_number") ) {
                    return new Integer( column_number );
                }
                else if ( name.equals("column_label") ) {
                    return this.column_label;
                }
                else if ( name.equals("column_type") ) {
                    return new Integer( column_type );
                }
                else if ( name.equals("cat_delimitter") ) {
                    return this.cat_delimitter;
                }
                break;
            case 'd':
                if ( name.equals("date_format") ) {
                    return this.date_format;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("input_item_symbol_name") ) {
                    return this.input_item_symbol_name;
                }
                break;
            case 's':
                if ( name.equals("section_number") ) {
                    return this.section_number;
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
            case 'c':
                if ( name.equals("column_number") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'column_number' must be Integer: '"+value+"' is not." );
                    this.column_number = ((Integer) value).intValue();
                    if (this.column_number_is_set)
                        this.column_number_is_modified = true;
                    this.column_number_is_set = true;
                    return;
                }
                else if ( name.equals("column_label") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'column_label' must be String: '"+value+"' is not." );
                    this.column_label = (String) value;
                    if (this.column_label_is_set)
                        this.column_label_is_modified = true;
                    this.column_label_is_set = true;
                    return;
                }
                else if ( name.equals("column_type") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'column_type' must be Integer: '"+value+"' is not." );
                    this.column_type = ((Integer) value).intValue();
                    if (this.column_type_is_set)
                        this.column_type_is_modified = true;
                    this.column_type_is_set = true;
                    return;
                }
                else if ( name.equals("cat_delimitter") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cat_delimitter' must be String: '"+value+"' is not." );
                    this.cat_delimitter = (String) value;
                    if (this.cat_delimitter_is_set)
                        this.cat_delimitter_is_modified = true;
                    this.cat_delimitter_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("date_format") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'date_format' must be String: '"+value+"' is not." );
                    this.date_format = (String) value;
                    if (this.date_format_is_set)
                        this.date_format_is_modified = true;
                    this.date_format_is_set = true;
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
                else if ( name.equals("input_item_symbol_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'input_item_symbol_name' must be String: '"+value+"' is not." );
                    this.input_item_symbol_name = (String) value;
                    if (this.input_item_symbol_name_is_set)
                        this.input_item_symbol_name_is_modified = true;
                    this.input_item_symbol_name_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("section_number") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'section_number' must be Integer: '"+value+"' is not." );
                    this.section_number = (Integer) value;
                    if (this.section_number_is_set)
                        this.section_number_is_modified = true;
                    this.section_number_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
