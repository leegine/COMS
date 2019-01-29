head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsPriceCondPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>RlsPriceCond</b>データベーステーブルで一意である1つのレコードをあらわす<b>RlsPriceCond</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>RlsPriceCond</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsPriceCondRow 
 */
public class RlsPriceCondPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "rls_price_cond";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: RlsPriceCondRow. 
   */
  public RowType getRowType() {
    return RlsPriceCondRow.TYPE;
  }

  /**
   * <em>cond_ord_id</em>カラムの値をあらわすlong値
   */
  public long cond_ord_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public RlsPriceCondPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_condOrdId <em>cond_ord_id</em>カラムの値をあらわすlong値
   */
  public RlsPriceCondPK( long p_condOrdId ) {
      cond_ord_id = p_condOrdId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static RlsPriceCondPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    RlsPriceCondPK pk = new RlsPriceCondPK();
    pk.cond_ord_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(cond_ord_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof RlsPriceCondPK) )
      return false;
    RlsPriceCondPK o = (RlsPriceCondPK) other;
    if ( cond_ord_id != o.cond_ord_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) cond_ord_id)
    ;
  }

}

@
