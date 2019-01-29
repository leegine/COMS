head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.36.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	GenFinTransactionPK.java;


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
 * <b>GenFinTransaction</b>データベーステーブルで一意である1つのレコードをあらわす<b>GenFinTransaction</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>GenFinTransaction</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see GenFinTransactionRow 
 */
public class GenFinTransactionPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "gen_fin_transaction";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: GenFinTransactionRow. 
   */
  public RowType getRowType() {
    return GenFinTransactionRow.TYPE;
  }

  /**
   * <em>transaction_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long transaction_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public GenFinTransactionPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_transactionId <em>transaction_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public GenFinTransactionPK( long p_transactionId ) {
      transaction_id = p_transactionId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static GenFinTransactionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    GenFinTransactionPK pk = new GenFinTransactionPK();
    pk.transaction_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(transaction_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof GenFinTransactionPK) )
      return false;
    GenFinTransactionPK o = (GenFinTransactionPK) other;
    if ( transaction_id != o.transaction_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) transaction_id)
    ;
  }

}

@
