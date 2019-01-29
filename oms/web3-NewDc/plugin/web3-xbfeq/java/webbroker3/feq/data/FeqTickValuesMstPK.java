head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	FeqTickValuesMstPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>FeqTickValuesMst</b>データベーステーブルで一意である1つのレコードをあらわす<b>FeqTickValuesMst</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>FeqTickValuesMst</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FeqTickValuesMstRow 
 */
public class FeqTickValuesMstPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "feq_tick_values_mst";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: FeqTickValuesMstRow. 
   */
  public RowType getRowType() {
    return FeqTickValuesMstRow.TYPE;
  }

  /**
   * <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String market_code;
  /**
   * <em>low_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public double low_price;


  /** 
   * デフォルトコンストラクタ 
   */
  public FeqTickValuesMstPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_lowPrice <em>low_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public FeqTickValuesMstPK( String p_marketCode, double p_lowPrice ) {
      market_code = p_marketCode;
      low_price = p_lowPrice;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static FeqTickValuesMstPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    FeqTickValuesMstPK pk = new FeqTickValuesMstPK();
    int i = pkValueString.indexOf(',');
    pk.market_code = pkValueString.substring(0,i);
    pk.low_price = Double.valueOf(pkValueString.substring(i+1)).doubleValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = market_code + "," + low_priceFormat.format(low_price);
    return m_id;
  }

  private String m_id = null;
  private static final java.text.DecimalFormat low_priceFormat = new java.text.DecimalFormat("#.######");

  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof FeqTickValuesMstPK) )
      return false;
    FeqTickValuesMstPK o = (FeqTickValuesMstPK) other;
    if ( market_code == null ) {
      if ( o.market_code != null )
        return false;
    } else if ( !market_code.equals( o.market_code ) ) {
        return false;
    }
    if ( low_price != o.low_price )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (market_code!=null? market_code.hashCode(): 0) 
        + ((int) low_price)
    ;
  }

}

@
