head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.59.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondFinTransactionPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>BondFinTransaction</b>データベーステーブルで一意である1つのレコードをあらわす<b>BondFinTransaction</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>BondFinTransaction</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondFinTransactionRow 
 */
public class BondFinTransactionPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bond_fin_transaction";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: BondFinTransactionRow. 
   */
  public RowType getRowType() {
    return BondFinTransactionRow.TYPE;
  }

  /**
   * <em>fin_transaction_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long fin_transaction_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public BondFinTransactionPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_finTransactionId <em>fin_transaction_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public BondFinTransactionPK( long p_finTransactionId ) {
      fin_transaction_id = p_finTransactionId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static BondFinTransactionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BondFinTransactionPK pk = new BondFinTransactionPK();
    pk.fin_transaction_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(fin_transaction_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BondFinTransactionPK) )
      return false;
    BondFinTransactionPK o = (BondFinTransactionPK) other;
    if ( fin_transaction_id != o.fin_transaction_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) fin_transaction_id)
    ;
  }

}

@
