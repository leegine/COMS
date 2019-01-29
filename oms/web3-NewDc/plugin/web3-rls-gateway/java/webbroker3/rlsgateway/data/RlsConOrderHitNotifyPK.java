head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsConOrderHitNotifyPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>RlsConOrderHitNotify</b>データベーステーブルで一意である1つのレコードをあらわす<b>RlsConOrderHitNotify</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>RlsConOrderHitNotify</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsConOrderHitNotifyRow 
 */
public class RlsConOrderHitNotifyPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "rls_con_order_hit_notify";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: RlsConOrderHitNotifyRow. 
   */
  public RowType getRowType() {
    return RlsConOrderHitNotifyRow.TYPE;
  }

  /**
   * <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long account_id;
  /**
   * <em>sub_account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long sub_account_id;
  /**
   * <em>order_type</em>カラムの値をあらわす6桁以下のint値 
   */
  public int order_type;
  /**
   * <em>order_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long order_id;
  /**
   * <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum product_type;


  /** 
   * デフォルトコンストラクタ 
   */
  public RlsConOrderHitNotifyPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_subAccountId <em>sub_account_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_orderType <em>order_type</em>カラムの値をあらわす6桁以下のint値 
   * @@param p_orderId <em>order_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_productType <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public RlsConOrderHitNotifyPK( long p_accountId, long p_subAccountId, int p_orderType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) {
      account_id = p_accountId;
      sub_account_id = p_subAccountId;
      order_type = p_orderType;
      order_id = p_orderId;
      product_type = p_productType;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static RlsConOrderHitNotifyPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    RlsConOrderHitNotifyPK pk = new RlsConOrderHitNotifyPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.account_id = Long.valueOf(st.nextToken()).longValue();
    pk.sub_account_id = Long.valueOf(st.nextToken()).longValue();
    pk.order_type = Integer.valueOf(st.nextToken()).intValue();
    pk.order_id = Long.valueOf(st.nextToken()).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(account_id) + "," + String.valueOf(sub_account_id) + "," + String.valueOf(order_type) + "," + String.valueOf(order_id) + "," + String.valueOf(product_type.intValue());
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof RlsConOrderHitNotifyPK) )
      return false;
    RlsConOrderHitNotifyPK o = (RlsConOrderHitNotifyPK) other;
    if ( account_id != o.account_id )
      return false;
    if ( sub_account_id != o.sub_account_id )
      return false;
    if ( order_type != o.order_type )
      return false;
    if ( order_id != o.order_id )
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
    return ((int) account_id)
        + ((int) sub_account_id)
        + ((int) order_type)
        + ((int) order_id)
        + (product_type!=null? product_type.hashCode(): 0) 
    ;
  }

}

@
