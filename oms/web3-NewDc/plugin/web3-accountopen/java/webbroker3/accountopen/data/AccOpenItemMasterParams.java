head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.13.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenItemMasterParams.java;


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
 * acc_open_item_masterテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AccOpenItemMasterRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AccOpenItemMasterParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AccOpenItemMasterParams}が{@@link AccOpenItemMasterRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenItemMasterPK 
 * @@see AccOpenItemMasterRow 
 */
public class AccOpenItemMasterParams extends Params implements AccOpenItemMasterRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "acc_open_item_master";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AccOpenItemMasterRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AccOpenItemMasterRow.TYPE;
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
   * <em>validate_type</em>カラムの値 
   */
  public  String  validate_type;

  /** 
   * <em>item_symbol_name</em>カラムの値 
   */
  public  String  item_symbol_name;

  /** 
   * <em>item_name</em>カラムの値 
   */
  public  String  item_name;

  /** 
   * <em>necessary_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  necessary_flag;

  /** 
   * <em>item_min</em>カラムの値 
   */
  public  Integer  item_min;

  /** 
   * <em>item_max</em>カラムの値 
   */
  public  Integer  item_max;

  /** 
   * <em>item_check_mode</em>カラムの値 
   */
  public  String  item_check_mode;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_div_is_set = false;
  private boolean account_div_is_modified = false;
  private boolean validate_type_is_set = false;
  private boolean validate_type_is_modified = false;
  private boolean item_symbol_name_is_set = false;
  private boolean item_symbol_name_is_modified = false;
  private boolean item_name_is_set = false;
  private boolean item_name_is_modified = false;
  private boolean necessary_flag_is_set = false;
  private boolean necessary_flag_is_modified = false;
  private boolean item_min_is_set = false;
  private boolean item_min_is_modified = false;
  private boolean item_max_is_set = false;
  private boolean item_max_is_modified = false;
  private boolean item_check_mode_is_set = false;
  private boolean item_check_mode_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "account_div=" + account_div
      + "," + "validate_type=" + validate_type
      + "," + "item_symbol_name=" + item_symbol_name
      + "," + "item_name=" +item_name
      + "," + "necessary_flag=" +necessary_flag
      + "," + "item_min=" +item_min
      + "," + "item_max=" +item_max
      + "," + "item_check_mode=" +item_check_mode
      + "}";
  }


  /** 
   * 値が未設定のAccOpenItemMasterParamsオブジェクトを作成します。 
   */
  public AccOpenItemMasterParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_div_is_modified = true;
    validate_type_is_modified = true;
    item_symbol_name_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAccOpenItemMasterRowオブジェクトの値を利用してAccOpenItemMasterParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAccOpenItemMasterRowオブジェクト 
   */
  public AccOpenItemMasterParams( AccOpenItemMasterRow p_row )
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
    validate_type = p_row.getValidateType();
    validate_type_is_set = p_row.getValidateTypeIsSet();
    validate_type_is_modified = p_row.getValidateTypeIsModified();
    item_symbol_name = p_row.getItemSymbolName();
    item_symbol_name_is_set = p_row.getItemSymbolNameIsSet();
    item_symbol_name_is_modified = p_row.getItemSymbolNameIsModified();
    item_name = p_row.getItemName();
    item_name_is_set = p_row.getItemNameIsSet();
    item_name_is_modified = p_row.getItemNameIsModified();
    necessary_flag = p_row.getNecessaryFlag();
    necessary_flag_is_set = p_row.getNecessaryFlagIsSet();
    necessary_flag_is_modified = p_row.getNecessaryFlagIsModified();
    if ( !p_row.getItemMinIsNull() )
      item_min = new Integer( p_row.getItemMin() );
    item_min_is_set = p_row.getItemMinIsSet();
    item_min_is_modified = p_row.getItemMinIsModified();
    if ( !p_row.getItemMaxIsNull() )
      item_max = new Integer( p_row.getItemMax() );
    item_max_is_set = p_row.getItemMaxIsSet();
    item_max_is_modified = p_row.getItemMaxIsModified();
    item_check_mode = p_row.getItemCheckMode();
    item_check_mode_is_set = p_row.getItemCheckModeIsSet();
    item_check_mode_is_modified = p_row.getItemCheckModeIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      item_name_is_set = true;
      item_name_is_modified = true;
      necessary_flag_is_set = true;
      necessary_flag_is_modified = true;
      item_min_is_set = true;
      item_min_is_modified = true;
      item_max_is_set = true;
      item_max_is_modified = true;
      item_check_mode_is_set = true;
      item_check_mode_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AccOpenItemMasterRow ) )
       return false;
    return fieldsEqual( (AccOpenItemMasterRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAccOpenItemMasterRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AccOpenItemMasterRow row )
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
    if ( validate_type == null ) {
      if ( row.getValidateType() != null )
        return false;
    } else if ( !validate_type.equals( row.getValidateType() ) ) {
        return false;
    }
    if ( item_symbol_name == null ) {
      if ( row.getItemSymbolName() != null )
        return false;
    } else if ( !item_symbol_name.equals( row.getItemSymbolName() ) ) {
        return false;
    }
    if ( item_name == null ) {
      if ( row.getItemName() != null )
        return false;
    } else if ( !item_name.equals( row.getItemName() ) ) {
        return false;
    }
    if ( necessary_flag == null ) {
      if ( row.getNecessaryFlag() != null )
        return false;
    } else if ( !necessary_flag.equals( row.getNecessaryFlag() ) ) {
        return false;
    }
    if ( item_min == null ) {
      if ( !row.getItemMinIsNull() )
        return false;
    } else if ( row.getItemMinIsNull() || ( item_min.intValue() != row.getItemMin() ) ) {
        return false;
    }
    if ( item_max == null ) {
      if ( !row.getItemMaxIsNull() )
        return false;
    } else if ( row.getItemMaxIsNull() || ( item_max.intValue() != row.getItemMax() ) ) {
        return false;
    }
    if ( item_check_mode == null ) {
      if ( row.getItemCheckMode() != null )
        return false;
    } else if ( !item_check_mode.equals( row.getItemCheckMode() ) ) {
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
        + (validate_type!=null? validate_type.hashCode(): 0) 
        + (item_symbol_name!=null? item_symbol_name.hashCode(): 0) 
        + (item_name!=null? item_name.hashCode(): 0) 
        + (necessary_flag!=null? necessary_flag.hashCode(): 0) 
        + (item_min!=null? item_min.hashCode(): 0) 
        + (item_max!=null? item_max.hashCode(): 0) 
        + (item_check_mode!=null? item_check_mode.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
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
		map.put("validate_type",validate_type);
		map.put("item_symbol_name",item_symbol_name);
		if ( item_name != null )
			map.put("item_name",item_name);
		if ( necessary_flag_is_set )
			map.put("necessary_flag",necessary_flag);
		if ( item_min != null )
			map.put("item_min",item_min);
		if ( item_max != null )
			map.put("item_max",item_max);
		if ( item_check_mode != null )
			map.put("item_check_mode",item_check_mode);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( item_name_is_modified )
			map.put("item_name",item_name);
		if ( necessary_flag_is_modified )
			map.put("necessary_flag",necessary_flag);
		if ( item_min_is_modified )
			map.put("item_min",item_min);
		if ( item_max_is_modified )
			map.put("item_max",item_max);
		if ( item_check_mode_is_modified )
			map.put("item_check_mode",item_check_mode);
		if (map.size() == 0) {
			map.put("item_name",item_name);
			if ( necessary_flag_is_set )
				map.put("necessary_flag",necessary_flag);
			map.put("item_min",item_min);
			map.put("item_max",item_max);
			map.put("item_check_mode",item_check_mode);
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
   * <em>validate_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getValidateType()
  {
    return validate_type;
  }


  /** 
   * <em>validate_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidateTypeIsSet() {
    return validate_type_is_set;
  }


  /** 
   * <em>validate_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidateTypeIsModified() {
    return validate_type_is_modified;
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
   * <em>item_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getItemName()
  {
    return item_name;
  }


  /** 
   * <em>item_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemNameIsSet() {
    return item_name_is_set;
  }


  /** 
   * <em>item_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemNameIsModified() {
    return item_name_is_modified;
  }


  /** 
   * <em>necessary_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getNecessaryFlag()
  {
    return necessary_flag;
  }


  /** 
   * <em>necessary_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNecessaryFlagIsSet() {
    return necessary_flag_is_set;
  }


  /** 
   * <em>necessary_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNecessaryFlagIsModified() {
    return necessary_flag_is_modified;
  }


  /** 
   * <em>item_min</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getItemMin()
  {
    return ( item_min==null? ((int)0): item_min.intValue() );
  }


  /** 
   * <em>item_min</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getItemMinIsNull()
  {
    return item_min == null;
  }


  /** 
   * <em>item_min</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemMinIsSet() {
    return item_min_is_set;
  }


  /** 
   * <em>item_min</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemMinIsModified() {
    return item_min_is_modified;
  }


  /** 
   * <em>item_max</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getItemMax()
  {
    return ( item_max==null? ((int)0): item_max.intValue() );
  }


  /** 
   * <em>item_max</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getItemMaxIsNull()
  {
    return item_max == null;
  }


  /** 
   * <em>item_max</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemMaxIsSet() {
    return item_max_is_set;
  }


  /** 
   * <em>item_max</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemMaxIsModified() {
    return item_max_is_modified;
  }


  /** 
   * <em>item_check_mode</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getItemCheckMode()
  {
    return item_check_mode;
  }


  /** 
   * <em>item_check_mode</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemCheckModeIsSet() {
    return item_check_mode_is_set;
  }


  /** 
   * <em>item_check_mode</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemCheckModeIsModified() {
    return item_check_mode_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AccOpenItemMasterPK(institution_code, branch_code, account_div, validate_type, item_symbol_name);
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
   * <em>validate_type</em>カラムの値を設定します。 
   *
   * @@param p_validateType <em>validate_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setValidateType( String p_validateType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    validate_type = p_validateType;
    validate_type_is_set = true;
    validate_type_is_modified = true;
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
   * <em>item_name</em>カラムの値を設定します。 
   *
   * @@param p_itemName <em>item_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setItemName( String p_itemName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_name = p_itemName;
    item_name_is_set = true;
    item_name_is_modified = true;
  }


  /** 
   * <em>necessary_flag</em>カラムの値を設定します。 
   *
   * @@param p_necessaryFlag <em>necessary_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setNecessaryFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_necessaryFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    necessary_flag = p_necessaryFlag;
    necessary_flag_is_set = true;
    necessary_flag_is_modified = true;
  }


  /** 
   * <em>item_min</em>カラムの値を設定します。 
   *
   * @@param p_itemMin <em>item_min</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setItemMin( int p_itemMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_min = new Integer(p_itemMin);
    item_min_is_set = true;
    item_min_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>item_min</em>カラムに値を設定します。 
   */
  public final void setItemMin( Integer p_itemMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    item_min = p_itemMin;
    item_min_is_set = true;
    item_min_is_modified = true;
  }


  /** 
   * <em>item_max</em>カラムの値を設定します。 
   *
   * @@param p_itemMax <em>item_max</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setItemMax( int p_itemMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_max = new Integer(p_itemMax);
    item_max_is_set = true;
    item_max_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>item_max</em>カラムに値を設定します。 
   */
  public final void setItemMax( Integer p_itemMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    item_max = p_itemMax;
    item_max_is_set = true;
    item_max_is_modified = true;
  }


  /** 
   * <em>item_check_mode</em>カラムの値を設定します。 
   *
   * @@param p_itemCheckMode <em>item_check_mode</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setItemCheckMode( String p_itemCheckMode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_check_mode = p_itemCheckMode;
    item_check_mode_is_set = true;
    item_check_mode_is_modified = true;
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
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("item_symbol_name") ) {
                    return this.item_symbol_name;
                }
                else if ( name.equals("item_name") ) {
                    return this.item_name;
                }
                else if ( name.equals("item_min") ) {
                    return this.item_min;
                }
                else if ( name.equals("item_max") ) {
                    return this.item_max;
                }
                else if ( name.equals("item_check_mode") ) {
                    return this.item_check_mode;
                }
                break;
            case 'n':
                if ( name.equals("necessary_flag") ) {
                    return this.necessary_flag;
                }
                break;
            case 'v':
                if ( name.equals("validate_type") ) {
                    return this.validate_type;
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
                else if ( name.equals("item_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_name' must be String: '"+value+"' is not." );
                    this.item_name = (String) value;
                    if (this.item_name_is_set)
                        this.item_name_is_modified = true;
                    this.item_name_is_set = true;
                    return;
                }
                else if ( name.equals("item_min") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'item_min' must be Integer: '"+value+"' is not." );
                    this.item_min = (Integer) value;
                    if (this.item_min_is_set)
                        this.item_min_is_modified = true;
                    this.item_min_is_set = true;
                    return;
                }
                else if ( name.equals("item_max") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'item_max' must be Integer: '"+value+"' is not." );
                    this.item_max = (Integer) value;
                    if (this.item_max_is_set)
                        this.item_max_is_modified = true;
                    this.item_max_is_set = true;
                    return;
                }
                else if ( name.equals("item_check_mode") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_check_mode' must be String: '"+value+"' is not." );
                    this.item_check_mode = (String) value;
                    if (this.item_check_mode_is_set)
                        this.item_check_mode_is_modified = true;
                    this.item_check_mode_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("necessary_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'necessary_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.necessary_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.necessary_flag_is_set)
                        this.necessary_flag_is_modified = true;
                    this.necessary_flag_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("validate_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'validate_type' must be String: '"+value+"' is not." );
                    this.validate_type = (String) value;
                    if (this.validate_type_is_set)
                        this.validate_type_is_modified = true;
                    this.validate_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
