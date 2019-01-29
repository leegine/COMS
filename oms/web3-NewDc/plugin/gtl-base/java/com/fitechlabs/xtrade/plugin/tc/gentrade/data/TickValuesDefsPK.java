head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.36.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	TickValuesDefsPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>TickValuesDefs</b>データベーステーブルで一意である1つのレコードをあらわす<b>TickValuesDefs</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>TickValuesDefs</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TickValuesDefsRow 
 */
public class TickValuesDefsPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "tick_values_defs";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: TickValuesDefsRow. 
   */
  public RowType getRowType() {
    return TickValuesDefsRow.TYPE;
  }

  /**
   * <em>tick_values_defs_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long tick_values_defs_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public TickValuesDefsPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_tickValuesDefsId <em>tick_values_defs_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public TickValuesDefsPK( long p_tickValuesDefsId ) {
      tick_values_defs_id = p_tickValuesDefsId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static TickValuesDefsPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TickValuesDefsPK pk = new TickValuesDefsPK();
    pk.tick_values_defs_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(tick_values_defs_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TickValuesDefsPK) )
      return false;
    TickValuesDefsPK o = (TickValuesDefsPK) other;
    if ( tick_values_defs_id != o.tick_values_defs_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) tick_values_defs_id)
    ;
  }

}

@
