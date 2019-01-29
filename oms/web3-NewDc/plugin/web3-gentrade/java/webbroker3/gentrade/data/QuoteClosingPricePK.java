head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	QuoteClosingPricePK.java;


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
 * <b>QuoteClosingPrice</b>データベーステーブルで一意である1つのレコードをあらわす<b>QuoteClosingPrice</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>QuoteClosingPrice</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see QuoteClosingPriceRow 
 */
public class QuoteClosingPricePK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "quote_closing_price";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: QuoteClosingPriceRow. 
   */
  public RowType getRowType() {
    return QuoteClosingPriceRow.TYPE;
  }

  /**
   * <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long product_id;
  /**
   * <em>base_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public String base_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public QuoteClosingPricePK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_baseDate <em>base_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public QuoteClosingPricePK( long p_productId, String p_baseDate ) {
      product_id = p_productId;
      base_date = p_baseDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static QuoteClosingPricePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    QuoteClosingPricePK pk = new QuoteClosingPricePK();
    int i = pkValueString.indexOf(',');
    pk.product_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.base_date = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(product_id) + "," + base_date;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof QuoteClosingPricePK) )
      return false;
    QuoteClosingPricePK o = (QuoteClosingPricePK) other;
    if ( product_id != o.product_id )
      return false;
    if ( base_date == null ) {
      if ( o.base_date != null )
        return false;
    } else if ( !base_date.equals( o.base_date ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) product_id)
        + (base_date!=null? base_date.hashCode(): 0) 
    ;
  }

}

@
