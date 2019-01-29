head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.34.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BatoProductManagementPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>BatoProductManagement</b>データベーステーブルで一意である1つのレコードをあらわす<b>BatoProductManagement</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>BatoProductManagement</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BatoProductManagementRow 
 */
public class BatoProductManagementPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bato_product_management";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: BatoProductManagementRow. 
   */
  public RowType getRowType() {
    return BatoProductManagementRow.TYPE;
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
   * <em>document_div</em>カラムの値をあらわす3桁以下のString値 
   */
  public String document_div;
  /**
   * <em>bato_product_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public String bato_product_code;


  /** 
   * デフォルトコンストラクタ 
   */
  public BatoProductManagementPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_documentDiv <em>document_div</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_batoProductCode <em>bato_product_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public BatoProductManagementPK( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_batoProductCode ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      document_div = p_documentDiv;
      bato_product_code = p_batoProductCode;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static BatoProductManagementPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BatoProductManagementPK pk = new BatoProductManagementPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.document_div = st.nextToken();
    pk.bato_product_code = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + document_div + "," + bato_product_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BatoProductManagementPK) )
      return false;
    BatoProductManagementPK o = (BatoProductManagementPK) other;
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
    if ( document_div == null ) {
      if ( o.document_div != null )
        return false;
    } else if ( !document_div.equals( o.document_div ) ) {
        return false;
    }
    if ( bato_product_code == null ) {
      if ( o.bato_product_code != null )
        return false;
    } else if ( !bato_product_code.equals( o.bato_product_code ) ) {
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
        + (document_div!=null? document_div.hashCode(): 0) 
        + (bato_product_code!=null? bato_product_code.hashCode(): 0) 
    ;
  }

}

@
