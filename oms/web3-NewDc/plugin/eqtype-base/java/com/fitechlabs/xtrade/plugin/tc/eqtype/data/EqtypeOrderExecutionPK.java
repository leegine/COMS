head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.39.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeOrderExecutionPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>EqtypeOrderExecution</b>データベーステーブルで一意である1つのレコードをあらわす<b>EqtypeOrderExecution</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>EqtypeOrderExecution</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeOrderExecutionRow 
 */
public class EqtypeOrderExecutionPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "eqtype_order_execution";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: EqtypeOrderExecutionRow. 
   */
  public RowType getRowType() {
    return EqtypeOrderExecutionRow.TYPE;
  }

  /**
   * <em>order_execution_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long order_execution_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public EqtypeOrderExecutionPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_orderExecutionId <em>order_execution_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public EqtypeOrderExecutionPK( long p_orderExecutionId ) {
      order_execution_id = p_orderExecutionId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static EqtypeOrderExecutionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    EqtypeOrderExecutionPK pk = new EqtypeOrderExecutionPK();
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
    if ( other == null || !( other instanceof EqtypeOrderExecutionPK) )
      return false;
    EqtypeOrderExecutionPK o = (EqtypeOrderExecutionPK) other;
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
