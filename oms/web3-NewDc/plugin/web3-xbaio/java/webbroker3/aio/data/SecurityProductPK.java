head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.37.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	SecurityProductPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * <b>SecurityProduct</b>データベーステーブルで一意である1つのレコードをあらわす<b>SecurityProduct</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SecurityProduct</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SecurityProductRow 
 */
public class SecurityProductPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "security_product";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SecurityProductRow. 
   */
  public RowType getRowType() {
    return SecurityProductRow.TYPE;
  }

  /**
   * <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long product_id;
  /**
   * <em>apply_term_from</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp apply_term_from;


  /** 
   * デフォルトコンストラクタ 
   */
  public SecurityProductPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_applyTermFrom <em>apply_term_from</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public SecurityProductPK( long p_productId, java.sql.Timestamp p_applyTermFrom ) {
      product_id = p_productId;
      apply_term_from = p_applyTermFrom;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SecurityProductPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SecurityProductPK pk = new SecurityProductPK();
    int i = pkValueString.indexOf(',');
    pk.product_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.apply_term_from = java.sql.Timestamp.valueOf(pkValueString.substring(i+1));
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(product_id) + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(apply_term_from);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SecurityProductPK) )
      return false;
    SecurityProductPK o = (SecurityProductPK) other;
    if ( product_id != o.product_id )
      return false;
    if ( apply_term_from == null ) {
      if ( o.apply_term_from != null )
        return false;
    } else if ( !apply_term_from.equals( o.apply_term_from ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) product_id)
        + (apply_term_from!=null? apply_term_from.hashCode(): 0) 
    ;
  }

}

@
