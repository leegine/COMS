head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.46.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TradeHistoryPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>TradeHistory</b>データベーステーブルで一意である1つのレコードをあらわす<b>TradeHistory</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>TradeHistory</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TradeHistoryRow 
 */
public class TradeHistoryPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "trade_history";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: TradeHistoryRow. 
   */
  public RowType getRowType() {
    return TradeHistoryRow.TYPE;
  }

  /**
   * <em>trade_history_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long trade_history_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public TradeHistoryPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_tradeHistoryId <em>trade_history_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public TradeHistoryPK( long p_tradeHistoryId ) {
      trade_history_id = p_tradeHistoryId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static TradeHistoryPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TradeHistoryPK pk = new TradeHistoryPK();
    pk.trade_history_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(trade_history_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TradeHistoryPK) )
      return false;
    TradeHistoryPK o = (TradeHistoryPK) other;
    if ( trade_history_id != o.trade_history_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) trade_history_id)
    ;
  }

}

@
