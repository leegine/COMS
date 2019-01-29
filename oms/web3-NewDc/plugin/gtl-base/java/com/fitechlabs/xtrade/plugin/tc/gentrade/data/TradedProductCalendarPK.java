head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.36.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	TradedProductCalendarPK.java;


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
 * <b>TradedProductCalendar</b>データベーステーブルで一意である1つのレコードをあらわす<b>TradedProductCalendar</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>TradedProductCalendar</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TradedProductCalendarRow 
 */
public class TradedProductCalendarPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "traded_product_calendar";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: TradedProductCalendarRow. 
   */
  public RowType getRowType() {
    return TradedProductCalendarRow.TYPE;
  }

  /**
   * <em>traded_product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long traded_product_id;
  /**
   * <em>trade_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp trade_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public TradedProductCalendarPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_tradedProductId <em>traded_product_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_tradeDate <em>trade_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public TradedProductCalendarPK( long p_tradedProductId, java.sql.Timestamp p_tradeDate ) {
      traded_product_id = p_tradedProductId;
      trade_date = p_tradeDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static TradedProductCalendarPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TradedProductCalendarPK pk = new TradedProductCalendarPK();
    int i = pkValueString.indexOf(',');
    pk.traded_product_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.trade_date = java.sql.Timestamp.valueOf(pkValueString.substring(i+1));
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(traded_product_id) + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(trade_date);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TradedProductCalendarPK) )
      return false;
    TradedProductCalendarPK o = (TradedProductCalendarPK) other;
    if ( traded_product_id != o.traded_product_id )
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
    return ((int) traded_product_id)
        + (trade_date!=null? trade_date.hashCode(): 0) 
    ;
  }

}

@
