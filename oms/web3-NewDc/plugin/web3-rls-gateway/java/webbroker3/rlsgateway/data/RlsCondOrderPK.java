head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsCondOrderPK.java;


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
 * <b>RlsCondOrder</b>データベーステーブルで一意である1つのレコードをあらわす<b>RlsCondOrder</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>RlsCondOrder</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsCondOrderRow 
 */
public class RlsCondOrderPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "rls_cond_order";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: RlsCondOrderRow. 
   */
  public RowType getRowType() {
    return RlsCondOrderRow.TYPE;
  }

  /**
   * <em>id</em>カラムの値をあらわす38桁以下のlong値 
   */
  public long id;


  /** 
   * デフォルトコンストラクタ 
   */
  public RlsCondOrderPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_id <em>id</em>カラムの値をあらわす38桁以下のlong値 
   */
  public RlsCondOrderPK( long p_id ) {
      id = p_id;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static RlsCondOrderPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    RlsCondOrderPK pk = new RlsCondOrderPK();
    pk.id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof RlsCondOrderPK) )
      return false;
    RlsCondOrderPK o = (RlsCondOrderPK) other;
    if ( id != o.id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) id)
    ;
  }

}

@
