head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsOmsOrderPK.java;


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
 * <b>RlsOmsOrder</b>データベーステーブルで一意である1つのレコードをあらわす<b>RlsOmsOrder</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>RlsOmsOrder</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsOmsOrderRow 
 */
public class RlsOmsOrderPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "rls_oms_order";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: RlsOmsOrderRow. 
   */
  public RowType getRowType() {
    return RlsOmsOrderRow.TYPE;
  }

  /**
   * <em>ord_id</em>カラムの値をあらわすlong値
   */
  public long ord_id;
  /**
   * <em>ord_type</em>カラムの値をあらわすlong値
   */
  public long ord_type;


  /** 
   * デフォルトコンストラクタ 
   */
  public RlsOmsOrderPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_ordId <em>ord_id</em>カラムの値をあらわすlong値
   * @@param p_ordType <em>ord_type</em>カラムの値をあらわすlong値
   */
  public RlsOmsOrderPK( long p_ordId, long p_ordType ) {
      ord_id = p_ordId;
      ord_type = p_ordType;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static RlsOmsOrderPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    RlsOmsOrderPK pk = new RlsOmsOrderPK();
    int i = pkValueString.indexOf(',');
    pk.ord_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.ord_type = Long.valueOf(pkValueString.substring(i+1)).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(ord_id) + "," + String.valueOf(ord_type);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof RlsOmsOrderPK) )
      return false;
    RlsOmsOrderPK o = (RlsOmsOrderPK) other;
    if ( ord_id != o.ord_id )
      return false;
    if ( ord_type != o.ord_type )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) ord_id)
        + ((int) ord_type)
    ;
  }

}

@
