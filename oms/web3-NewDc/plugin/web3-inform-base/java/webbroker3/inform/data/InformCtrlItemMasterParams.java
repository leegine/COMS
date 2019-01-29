head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.58.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	InformCtrlItemMasterParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * inform_ctrl_item_masterテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link InformCtrlItemMasterRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link InformCtrlItemMasterParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link InformCtrlItemMasterParams}が{@@link InformCtrlItemMasterRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see InformCtrlItemMasterPK 
 * @@see InformCtrlItemMasterRow 
 */
public class InformCtrlItemMasterParams extends Params implements InformCtrlItemMasterRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "inform_ctrl_item_master";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = InformCtrlItemMasterRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return InformCtrlItemMasterRow.TYPE;
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
   * <em>inform_div</em>カラムの値 
   */
  public  String  inform_div;

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
  public  int  necessary_flag;

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
  private boolean inform_div_is_set = false;
  private boolean inform_div_is_modified = false;
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
      + "," + "inform_div=" + inform_div
      + "," + "item_symbol_name=" + item_symbol_name
      + "," + "item_name=" +item_name
      + "," + "necessary_flag=" +necessary_flag
      + "," + "item_min=" +item_min
      + "," + "item_max=" +item_max
      + "," + "item_check_mode=" +item_check_mode
      + "}";
  }


  /** 
   * 値が未設定のInformCtrlItemMasterParamsオブジェクトを作成します。 
   */
  public InformCtrlItemMasterParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    inform_div_is_modified = true;
    item_symbol_name_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のInformCtrlItemMasterRowオブジェクトの値を利用してInformCtrlItemMasterParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するInformCtrlItemMasterRowオブジェクト 
   */
  public InformCtrlItemMasterParams( InformCtrlItemMasterRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    inform_div = p_row.getInformDiv();
    inform_div_is_set = p_row.getInformDivIsSet();
    inform_div_is_modified = p_row.getInformDivIsModified();
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
    if ( !( other instanceof InformCtrlItemMasterRow ) )
       return false;
    return fieldsEqual( (InformCtrlItemMasterRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のInformCtrlItemMasterRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( InformCtrlItemMasterRow row )
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
    if ( inform_div == null ) {
      if ( row.getInformDiv() != null )
        return false;
    } else if ( !inform_div.equals( row.getInformDiv() ) ) {
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
    if ( necessary_flag != row.getNecessaryFlag() )
      return false;
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
        + (inform_div!=null? inform_div.hashCode(): 0) 
        + (item_symbol_name!=null? item_symbol_name.hashCode(): 0) 
        + (item_name!=null? item_name.hashCode(): 0) 
        + ((int) necessary_flag)
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
		if ( !necessary_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'necessary_flag' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("inform_div",inform_div);
		map.put("item_symbol_name",item_symbol_name);
		if ( item_name != null )
			map.put("item_name",item_name);
		map.put("necessary_flag",new Integer(necessary_flag));
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
			map.put("necessary_flag",new Integer(necessary_flag));
		if ( item_min_is_modified )
			map.put("item_min",item_min);
		if ( item_max_is_modified )
			map.put("item_max",item_max);
		if ( item_check_mode_is_modified )
			map.put("item_check_mode",item_check_mode);
		if (map.size() == 0) {
			map.put("item_name",item_name);
			if ( necessary_flag_is_set )
				map.put("necessary_flag",new Integer(necessary_flag));
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
   * <em>inform_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInformDiv()
  {
    return inform_div;
  }


  /** 
   * <em>inform_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInformDivIsSet() {
    return inform_div_is_set;
  }


  /** 
   * <em>inform_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInformDivIsModified() {
    return inform_div_is_modified;
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
   * @@return intの値 
   */
  public final int getNecessaryFlag()
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
    return new InformCtrlItemMasterPK(institution_code, branch_code, inform_div, item_symbol_name);
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
   * <em>inform_div</em>カラムの値を設定します。 
   *
   * @@param p_informDiv <em>inform_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setInformDiv( String p_informDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    inform_div = p_informDiv;
    inform_div_is_set = true;
    inform_div_is_modified = true;
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
   * @@param p_necessaryFlag <em>necessary_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setNecessaryFlag( int p_necessaryFlag )  {
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
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("inform_div") ) {
                    return this.inform_div;
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
                    return new Integer( necessary_flag );
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
                else if ( name.equals("inform_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'inform_div' must be String: '"+value+"' is not." );
                    this.inform_div = (String) value;
                    if (this.inform_div_is_set)
                        this.inform_div_is_modified = true;
                    this.inform_div_is_set = true;
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
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'necessary_flag' must be Integer: '"+value+"' is not." );
                    this.necessary_flag = ((Integer) value).intValue();
                    if (this.necessary_flag_is_set)
                        this.necessary_flag_is_modified = true;
                    this.necessary_flag_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
