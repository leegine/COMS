head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleConnStatusChangesPK.java;


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
 * <b>SleConnStatusChanges</b>データベーステーブルで一意である1つのレコードをあらわす<b>SleConnStatusChanges</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SleConnStatusChanges</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleConnStatusChangesRow 
 */
public class SleConnStatusChangesPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "sle_conn_status_changes";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SleConnStatusChangesRow. 
   */
  public RowType getRowType() {
    return SleConnStatusChangesRow.TYPE;
  }

  /**
   * <em>que_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public long que_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public SleConnStatusChangesPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_queId <em>que_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public SleConnStatusChangesPK( long p_queId ) {
      que_id = p_queId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SleConnStatusChangesPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SleConnStatusChangesPK pk = new SleConnStatusChangesPK();
    pk.que_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(que_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SleConnStatusChangesPK) )
      return false;
    SleConnStatusChangesPK o = (SleConnStatusChangesPK) other;
    if ( que_id != o.que_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) que_id)
    ;
  }

}

@
