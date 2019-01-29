head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.27.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderUnitIntroduceDivPK.java;


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
 * <b>OrderUnitIntroduceDiv</b>データベーステーブルで一意である1つのレコードをあらわす<b>OrderUnitIntroduceDiv</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>OrderUnitIntroduceDiv</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrderUnitIntroduceDivRow 
 */
public class OrderUnitIntroduceDivPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "order_unit_introduce_div";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: OrderUnitIntroduceDivRow. 
   */
  public RowType getRowType() {
    return OrderUnitIntroduceDivRow.TYPE;
  }

  /**
   * <em>order_unit_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long order_unit_id;
  /**
   * <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum product_type;


  /** 
   * デフォルトコンストラクタ 
   */
  public OrderUnitIntroduceDivPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_orderUnitId <em>order_unit_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_productType <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public OrderUnitIntroduceDivPK( long p_orderUnitId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) {
      order_unit_id = p_orderUnitId;
      product_type = p_productType;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static OrderUnitIntroduceDivPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OrderUnitIntroduceDivPK pk = new OrderUnitIntroduceDivPK();
    int i = pkValueString.indexOf(',');
    pk.order_unit_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(order_unit_id) + "," + String.valueOf(product_type.intValue());
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OrderUnitIntroduceDivPK) )
      return false;
    OrderUnitIntroduceDivPK o = (OrderUnitIntroduceDivPK) other;
    if ( order_unit_id != o.order_unit_id )
      return false;
    if ( product_type == null ) {
      if ( o.product_type != null )
        return false;
    } else if ( !product_type.equals( o.product_type ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) order_unit_id)
        + (product_type!=null? product_type.hashCode(): 0) 
    ;
  }

}

@
