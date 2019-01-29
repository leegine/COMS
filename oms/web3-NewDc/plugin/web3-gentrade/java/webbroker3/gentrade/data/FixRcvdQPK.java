head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.42.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FixRcvdQPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>FixRcvdQ</b>データベーステーブルで一意である1つのレコードをあらわす<b>FixRcvdQ</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>FixRcvdQ</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FixRcvdQRow 
 */
public class FixRcvdQPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "fix_rcvd_q";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: FixRcvdQRow. 
   */
  public RowType getRowType() {
    return FixRcvdQRow.TYPE;
  }

  /**
   * <em>queue_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long queue_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public FixRcvdQPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_queueId <em>queue_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public FixRcvdQPK( long p_queueId ) {
      queue_id = p_queueId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static FixRcvdQPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    FixRcvdQPK pk = new FixRcvdQPK();
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
    if ( other == null || !( other instanceof FixRcvdQPK) )
      return false;
    FixRcvdQPK o = (FixRcvdQPK) other;
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
