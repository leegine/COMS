head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.14.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenItemMasterPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>AccOpenItemMaster</b>データベーステーブルで一意である1つのレコードをあらわす<b>AccOpenItemMaster</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AccOpenItemMaster</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenItemMasterRow 
 */
public class AccOpenItemMasterPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "acc_open_item_master";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AccOpenItemMasterRow. 
   */
  public RowType getRowType() {
    return AccOpenItemMasterRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String branch_code;
  /**
   * <em>account_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String account_div;
  /**
   * <em>validate_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public String validate_type;
  /**
   * <em>item_symbol_name</em>カラムの値をあらわす30桁以下のString値 
   */
  public String item_symbol_name;


  /** 
   * デフォルトコンストラクタ 
   */
  public AccOpenItemMasterPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_accountDiv <em>account_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_validateType <em>validate_type</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_itemSymbolName <em>item_symbol_name</em>カラムの値をあらわす30桁以下のString値 
   */
  public AccOpenItemMasterPK( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_validateType, String p_itemSymbolName ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      account_div = p_accountDiv;
      validate_type = p_validateType;
      item_symbol_name = p_itemSymbolName;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AccOpenItemMasterPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AccOpenItemMasterPK pk = new AccOpenItemMasterPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.account_div = st.nextToken();
    pk.validate_type = st.nextToken();
    pk.item_symbol_name = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + account_div + "," + validate_type + "," + item_symbol_name;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AccOpenItemMasterPK) )
      return false;
    AccOpenItemMasterPK o = (AccOpenItemMasterPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( o.branch_code != null )
        return false;
    } else if ( !branch_code.equals( o.branch_code ) ) {
        return false;
    }
    if ( account_div == null ) {
      if ( o.account_div != null )
        return false;
    } else if ( !account_div.equals( o.account_div ) ) {
        return false;
    }
    if ( validate_type == null ) {
      if ( o.validate_type != null )
        return false;
    } else if ( !validate_type.equals( o.validate_type ) ) {
        return false;
    }
    if ( item_symbol_name == null ) {
      if ( o.item_symbol_name != null )
        return false;
    } else if ( !item_symbol_name.equals( o.item_symbol_name ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_div!=null? account_div.hashCode(): 0) 
        + (validate_type!=null? validate_type.hashCode(): 0) 
        + (item_symbol_name!=null? item_symbol_name.hashCode(): 0) 
    ;
  }

}

@
