head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.19.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	RsvIfoOrderUnitPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * <b>RsvIfoOrderUnit</b>データベーステーブルで一意である1つのレコードをあらわす<b>RsvIfoOrderUnit</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>RsvIfoOrderUnit</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RsvIfoOrderUnitRow 
 */
public class RsvIfoOrderUnitPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "rsv_ifo_order_unit";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: RsvIfoOrderUnitRow. 
   */
  public RowType getRowType() {
    return RsvIfoOrderUnitRow.TYPE;
  }

  /**
   * <em>order_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long order_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public RsvIfoOrderUnitPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_orderId <em>order_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public RsvIfoOrderUnitPK( long p_orderId ) {
      order_id = p_orderId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static RsvIfoOrderUnitPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    RsvIfoOrderUnitPK pk = new RsvIfoOrderUnitPK();
    pk.order_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(order_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof RsvIfoOrderUnitPK) )
      return false;
    RsvIfoOrderUnitPK o = (RsvIfoOrderUnitPK) other;
    if ( order_id != o.order_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) order_id)
    ;
  }

}

@
