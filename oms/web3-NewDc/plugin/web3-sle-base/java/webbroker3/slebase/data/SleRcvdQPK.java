head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleRcvdQPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.slebase.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/** 
 * <b>SleRcvdQ</b>データベーステーブルで一意である1つのレコードをあらわす<b>SleRcvdQ</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SleRcvdQ</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleRcvdQRow 
 */
public class SleRcvdQPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "sle_rcvd_q";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SleRcvdQRow. 
   */
  public RowType getRowType() {
    return SleRcvdQRow.TYPE;
  }

  /**
   * <em>queue_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public long queue_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public SleRcvdQPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_queueId <em>queue_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public SleRcvdQPK( long p_queueId ) {
      queue_id = p_queueId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SleRcvdQPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SleRcvdQPK pk = new SleRcvdQPK();
    pk.queue_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(queue_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SleRcvdQPK) )
      return false;
    SleRcvdQPK o = (SleRcvdQPK) other;
    if ( queue_id != o.queue_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) queue_id)
    ;
  }

}

@
