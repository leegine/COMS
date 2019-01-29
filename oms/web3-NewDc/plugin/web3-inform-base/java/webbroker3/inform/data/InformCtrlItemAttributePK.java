head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.57.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	InformCtrlItemAttributePK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>InformCtrlItemAttribute</b>データベーステーブルで一意である1つのレコードをあらわす<b>InformCtrlItemAttribute</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>InformCtrlItemAttribute</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see InformCtrlItemAttributeRow 
 */
public class InformCtrlItemAttributePK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "inform_ctrl_item_attribute";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: InformCtrlItemAttributeRow. 
   */
  public RowType getRowType() {
    return InformCtrlItemAttributeRow.TYPE;
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
   * <em>inform_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public String inform_div;
  /**
   * <em>item_symbol_name</em>カラムの値をあらわす30桁以下のString値 
   */
  public String item_symbol_name;
  /**
   * <em>attribute_value</em>カラムの値をあらわす2桁以下のString値 
   */
  public String attribute_value;


  /** 
   * デフォルトコンストラクタ 
   */
  public InformCtrlItemAttributePK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_informDiv <em>inform_div</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_itemSymbolName <em>item_symbol_name</em>カラムの値をあらわす30桁以下のString値 
   * @@param p_attributeValue <em>attribute_value</em>カラムの値をあらわす2桁以下のString値 
   */
  public InformCtrlItemAttributePK( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName, String p_attributeValue ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      inform_div = p_informDiv;
      item_symbol_name = p_itemSymbolName;
      attribute_value = p_attributeValue;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static InformCtrlItemAttributePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    InformCtrlItemAttributePK pk = new InformCtrlItemAttributePK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.inform_div = st.nextToken();
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
      m_id = institution_code + "," + branch_code + "," + inform_div + "," + item_symbol_name + "," + attribute_value;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof InformCtrlItemAttributePK) )
      return false;
    InformCtrlItemAttributePK o = (InformCtrlItemAttributePK) other;
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
    if ( inform_div == null ) {
      if ( o.inform_div != null )
        return false;
    } else if ( !inform_div.equals( o.inform_div ) ) {
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
        + (inform_div!=null? inform_div.hashCode(): 0) 
        + (item_symbol_name!=null? item_symbol_name.hashCode(): 0) 
        + (attribute_value!=null? attribute_value.hashCode(): 0) 
    ;
  }

}

@
