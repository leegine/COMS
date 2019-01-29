head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.51.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoBookbuildingOrderActionPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>IpoBookbuildingOrderAction</b>データベーステーブルで一意である1つのレコードをあらわす<b>IpoBookbuildingOrderAction</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>IpoBookbuildingOrderAction</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IpoBookbuildingOrderActionRow 
 */
public class IpoBookbuildingOrderActionPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ipo_bookbuilding_order_action";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: IpoBookbuildingOrderActionRow. 
   */
  public RowType getRowType() {
    return IpoBookbuildingOrderActionRow.TYPE;
  }

  /**
   * <em>bookbuilding_order_action_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long bookbuilding_order_action_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public IpoBookbuildingOrderActionPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_bookbuildingOrderActionId <em>bookbuilding_order_action_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public IpoBookbuildingOrderActionPK( long p_bookbuildingOrderActionId ) {
      bookbuilding_order_action_id = p_bookbuildingOrderActionId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static IpoBookbuildingOrderActionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    IpoBookbuildingOrderActionPK pk = new IpoBookbuildingOrderActionPK();
    pk.bookbuilding_order_action_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(bookbuilding_order_action_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof IpoBookbuildingOrderActionPK) )
      return false;
    IpoBookbuildingOrderActionPK o = (IpoBookbuildingOrderActionPK) other;
    if ( bookbuilding_order_action_id != o.bookbuilding_order_action_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) bookbuilding_order_action_id)
    ;
  }

}

@
