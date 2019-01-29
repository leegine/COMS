head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.47.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	SettleDetailHistoryPK.java;


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
 * <b>SettleDetailHistory</b>データベーステーブルで一意である1つのレコードをあらわす<b>SettleDetailHistory</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SettleDetailHistory</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SettleDetailHistoryRow 
 */
public class SettleDetailHistoryPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "settle_detail_history";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SettleDetailHistoryRow. 
   */
  public RowType getRowType() {
    return SettleDetailHistoryRow.TYPE;
  }

  /**
   * <em>settle_detail_history_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long settle_detail_history_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public SettleDetailHistoryPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_settleDetailHistoryId <em>settle_detail_history_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public SettleDetailHistoryPK( long p_settleDetailHistoryId ) {
      settle_detail_history_id = p_settleDetailHistoryId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SettleDetailHistoryPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SettleDetailHistoryPK pk = new SettleDetailHistoryPK();
    pk.settle_detail_history_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(settle_detail_history_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SettleDetailHistoryPK) )
      return false;
    SettleDetailHistoryPK o = (SettleDetailHistoryPK) other;
    if ( settle_detail_history_id != o.settle_detail_history_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) settle_detail_history_id)
    ;
  }

}

@
