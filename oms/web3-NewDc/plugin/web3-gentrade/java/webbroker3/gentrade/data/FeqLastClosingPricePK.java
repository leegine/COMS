head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.32.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FeqLastClosingPricePK.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>FeqLastClosingPrice</b>データベーステーブルで一意である1つのレコードをあらわす<b>FeqLastClosingPrice</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>FeqLastClosingPrice</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FeqLastClosingPriceRow 
 */
public class FeqLastClosingPricePK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "feq_last_closing_price";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: FeqLastClosingPriceRow. 
   */
  public RowType getRowType() {
    return FeqLastClosingPriceRow.TYPE;
  }

  /**
   * <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long product_id;
  /**
   * <em>base_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp base_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public FeqLastClosingPricePK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_baseDate <em>base_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public FeqLastClosingPricePK( long p_productId, java.sql.Timestamp p_baseDate ) {
      product_id = p_productId;
      base_date = p_baseDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static FeqLastClosingPricePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    FeqLastClosingPricePK pk = new FeqLastClosingPricePK();
    int i = pkValueString.indexOf(',');
    pk.product_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.base_date = java.sql.Timestamp.valueOf(pkValueString.substring(i+1));
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(product_id) + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(base_date);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof FeqLastClosingPricePK) )
      return false;
    FeqLastClosingPricePK o = (FeqLastClosingPricePK) other;
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
