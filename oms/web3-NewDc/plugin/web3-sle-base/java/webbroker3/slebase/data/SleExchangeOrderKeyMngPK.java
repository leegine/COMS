head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleExchangeOrderKeyMngPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.slebase.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/** 
 * <b>SleExchangeOrderKeyMng</b>データベーステーブルで一意である1つのレコードをあらわす<b>SleExchangeOrderKeyMng</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SleExchangeOrderKeyMng</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleExchangeOrderKeyMngRow 
 */
public class SleExchangeOrderKeyMngPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "sle_exchange_order_key_mng";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SleExchangeOrderKeyMngRow. 
   */
  public RowType getRowType() {
    return SleExchangeOrderKeyMngRow.TYPE;
  }

  /**
   * <em>xblocks_product_type</em>コラムの値をあらわす6桁以下のint値 
   */
  public int xblocks_product_type;
  /**
   * <em>order_unit_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public long order_unit_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public SleExchangeOrderKeyMngPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_xblocksProductType <em>xblocks_product_type</em>コラムの値をあらわす6桁以下のint値 
   * @@param p_orderUnitId <em>order_unit_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public SleExchangeOrderKeyMngPK( int p_xblocksProductType, long p_orderUnitId ) {
      xblocks_product_type = p_xblocksProductType;
      order_unit_id = p_orderUnitId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SleExchangeOrderKeyMngPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SleExchangeOrderKeyMngPK pk = new SleExchangeOrderKeyMngPK();
    int i = pkValueString.indexOf(',');
    pk.xblocks_product_type = Integer.valueOf(pkValueString.substring(0,i)).intValue();
    pk.order_unit_id = Long.valueOf(pkValueString.substring(i+1)).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(xblocks_product_type) + "," + String.valueOf(order_unit_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SleExchangeOrderKeyMngPK) )
      return false;
    SleExchangeOrderKeyMngPK o = (SleExchangeOrderKeyMngPK) other;
    if ( xblocks_product_type != o.xblocks_product_type )
      return false;
    if ( order_unit_id != o.order_unit_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) xblocks_product_type)
        + ((int) order_unit_id)
    ;
  }

}

@
