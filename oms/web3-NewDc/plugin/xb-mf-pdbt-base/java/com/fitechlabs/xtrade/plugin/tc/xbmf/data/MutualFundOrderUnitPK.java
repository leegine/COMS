head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.12.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MutualFundOrderUnitPK.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>MutualFundOrderUnit</b>データベーステーブルで一意である1つのレコードをあらわす<b>MutualFundOrderUnit</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MutualFundOrderUnit</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundOrderUnitRow 
 */
public class MutualFundOrderUnitPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mutual_fund_order_unit";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MutualFundOrderUnitRow. 
   */
  public RowType getRowType() {
    return MutualFundOrderUnitRow.TYPE;
  }

  /**
   * <em>order_unit_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long order_unit_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public MutualFundOrderUnitPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_orderUnitId <em>order_unit_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public MutualFundOrderUnitPK( long p_orderUnitId ) {
      order_unit_id = p_orderUnitId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MutualFundOrderUnitPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MutualFundOrderUnitPK pk = new MutualFundOrderUnitPK();
    pk.order_unit_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(order_unit_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MutualFundOrderUnitPK) )
      return false;
    MutualFundOrderUnitPK o = (MutualFundOrderUnitPK) other;
    if ( order_unit_id != o.order_unit_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) order_unit_id)
    ;
  }

}

@
