head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.22.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DaemonTriggerPK.java;


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
 * <b>DaemonTrigger</b>データベーステーブルで一意である1つのレコードをあらわす<b>DaemonTrigger</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>DaemonTrigger</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DaemonTriggerRow 
 */
public class DaemonTriggerPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "daemon_trigger";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: DaemonTriggerRow. 
   */
  public RowType getRowType() {
    return DaemonTriggerRow.TYPE;
  }

  /**
   * <em>thread_no</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long thread_no;


  /** 
   * デフォルトコンストラクタ 
   */
  public DaemonTriggerPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_threadNo <em>thread_no</em>カラムの値をあらわす18桁以下のlong値 
   */
  public DaemonTriggerPK( long p_threadNo ) {
      thread_no = p_threadNo;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static DaemonTriggerPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    DaemonTriggerPK pk = new DaemonTriggerPK();
    pk.thread_no = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(thread_no);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof DaemonTriggerPK) )
      return false;
    DaemonTriggerPK o = (DaemonTriggerPK) other;
    if ( thread_no != o.thread_no )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) thread_no)
    ;
  }

}

@
