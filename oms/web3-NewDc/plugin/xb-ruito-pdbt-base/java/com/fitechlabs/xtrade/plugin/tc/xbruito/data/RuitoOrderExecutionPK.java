head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.17.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4c04d8868c00da0;
filename	RuitoOrderExecutionPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbruito.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>RuitoOrderExecution</b>データベーステーブルで一意である1つのレコードをあらわす<b>RuitoOrderExecution</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>RuitoOrderExecution</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RuitoOrderExecutionRow 
 */
public class RuitoOrderExecutionPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ruito_order_execution";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: RuitoOrderExecutionRow. 
   */
  public RowType getRowType() {
    return RuitoOrderExecutionRow.TYPE;
  }

  /**
   * <em>order_execution_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long order_execution_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public RuitoOrderExecutionPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_orderExecutionId <em>order_execution_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public RuitoOrderExecutionPK( long p_orderExecutionId ) {
      order_execution_id = p_orderExecutionId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static RuitoOrderExecutionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    RuitoOrderExecutionPK pk = new RuitoOrderExecutionPK();
    pk.order_execution_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(order_execution_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof RuitoOrderExecutionPK) )
      return false;
    RuitoOrderExecutionPK o = (RuitoOrderExecutionPK) other;
    if ( order_execution_id != o.order_execution_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) order_execution_id)
    ;
  }

}

@
