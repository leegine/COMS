head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	BondProductCouponPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * <b>BondProductCoupon</b>データベーステーブルで一意である1つのレコードをあらわす<b>BondProductCoupon</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>BondProductCoupon</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondProductCouponRow 
 */
public class BondProductCouponPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bond_product_coupon";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: BondProductCouponRow. 
   */
  public RowType getRowType() {
    return BondProductCouponRow.TYPE;
  }

  /**
   * <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long product_id;
  /**
   * <em>interest_payment_day</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp interest_payment_day;


  /** 
   * デフォルトコンストラクタ 
   */
  public BondProductCouponPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_interestPaymentDay <em>interest_payment_day</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public BondProductCouponPK( long p_productId, java.sql.Timestamp p_interestPaymentDay ) {
      product_id = p_productId;
      interest_payment_day = p_interestPaymentDay;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static BondProductCouponPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BondProductCouponPK pk = new BondProductCouponPK();
    int i = pkValueString.indexOf(',');
    pk.product_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.interest_payment_day = java.sql.Timestamp.valueOf(pkValueString.substring(i+1));
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(product_id) + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(interest_payment_day);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BondProductCouponPK) )
      return false;
    BondProductCouponPK o = (BondProductCouponPK) other;
    if ( product_id != o.product_id )
      return false;
    if ( interest_payment_day == null ) {
      if ( o.interest_payment_day != null )
        return false;
    } else if ( !interest_payment_day.equals( o.interest_payment_day ) ) {
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
        + (interest_payment_day!=null? interest_payment_day.hashCode(): 0) 
    ;
  }

}

@
