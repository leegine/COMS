head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.34.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MarketCalendarPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>MarketCalendar</b>データベーステーブルで一意である1つのレコードをあらわす<b>MarketCalendar</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MarketCalendar</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MarketCalendarRow 
 */
public class MarketCalendarPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "market_calendar";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MarketCalendarRow. 
   */
  public RowType getRowType() {
    return MarketCalendarRow.TYPE;
  }

  /**
   * <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long market_id;
  /**
   * <em>trade_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp trade_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public MarketCalendarPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_marketId <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_tradeDate <em>trade_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public MarketCalendarPK( long p_marketId, java.sql.Timestamp p_tradeDate ) {
      market_id = p_marketId;
      trade_date = p_tradeDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MarketCalendarPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MarketCalendarPK pk = new MarketCalendarPK();
    int i = pkValueString.indexOf(',');
    pk.market_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.trade_date = java.sql.Timestamp.valueOf(pkValueString.substring(i+1));
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(market_id) + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(trade_date);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MarketCalendarPK) )
      return false;
    MarketCalendarPK o = (MarketCalendarPK) other;
    if ( market_id != o.market_id )
      return false;
    if ( trade_date == null ) {
      if ( o.trade_date != null )
        return false;
    } else if ( !trade_date.equals( o.trade_date ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) market_id)
        + (trade_date!=null? trade_date.hashCode(): 0) 
    ;
  }

}

@
