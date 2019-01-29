head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenItemAttributePK.java;


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
 * <b>AccOpenItemAttribute</b>データベーステーブルで一意である1つのレコードをあらわす<b>AccOpenItemAttribute</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AccOpenItemAttribute</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenItemAttributeRow 
 */
public class AccOpenItemAttributePK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "acc_open_item_attribute";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AccOpenItemAttributeRow. 
   */
  public RowType getRowType() {
    return AccOpenItemAttributeRow.TYPE;
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
   * <em>item_symbol_name</em>カラムの値をあらわす30桁以下のString値 
   */
  public String item_symbol_name;
  /**
   * <em>attribute_value</em>カラムの値をあらわす4桁以下のString値 
   */
  public String attribute_value;


  /** 
   * デフォルトコンストラクタ 
   */
  public AccOpenItemAttributePK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_accountDiv <em>account_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_itemSymbolName <em>item_symbol_name</em>カラムの値をあらわす30桁以下のString値 
   * @@param p_attributeValue <em>attribute_value</em>カラムの値をあらわす4桁以下のString値 
   */
  public AccOpenItemAttributePK( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_itemSymbolName, String p_attributeValue ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      account_div = p_accountDiv;
      item_symbol_name = p_itemSymbolName;
      attribute_value = p_attributeValue;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AccOpenItemAttributePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AccOpenItemAttributePK pk = new AccOpenItemAttributePK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.account_div = st.nextToken();
    pk.item_symbol_name = st.nextToken();
    pk.attribute_value = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + account_div + "," + item_symbol_name + "," + attribute_value;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AccOpenItemAttributePK) )
      return false;
    AccOpenItemAttributePK o = (AccOpenItemAttributePK) other;
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
    if ( item_symbol_name == null ) {
      if ( o.item_symbol_name != null )
        return false;
    } else if ( !item_symbol_name.equals( o.item_symbol_name ) ) {
        return false;
    }
    if ( attribute_value == null ) {
      if ( o.attribute_value != null )
        return false;
    } else if ( !attribute_value.equals( o.attribute_value ) ) {
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
        + (item_symbol_name!=null? item_symbol_name.hashCode(): 0) 
        + (attribute_value!=null? attribute_value.hashCode(): 0) 
    ;
  }

}

@
