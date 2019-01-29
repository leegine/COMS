head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.16.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4c04d8868c00da0;
filename	RuitoFinTransactionPK.java;


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
 * <b>RuitoFinTransaction</b>データベーステーブルで一意である1つのレコードをあらわす<b>RuitoFinTransaction</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>RuitoFinTransaction</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RuitoFinTransactionRow 
 */
public class RuitoFinTransactionPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ruito_fin_transaction";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: RuitoFinTransactionRow. 
   */
  public RowType getRowType() {
    return RuitoFinTransactionRow.TYPE;
  }

  /**
   * <em>fin_transaction_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long fin_transaction_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public RuitoFinTransactionPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_finTransactionId <em>fin_transaction_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public RuitoFinTransactionPK( long p_finTransactionId ) {
      fin_transaction_id = p_finTransactionId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static RuitoFinTransactionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    RuitoFinTransactionPK pk = new RuitoFinTransactionPK();
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
    if ( other == null || !( other instanceof RuitoFinTransactionPK) )
      return false;
    RuitoFinTransactionPK o = (RuitoFinTransactionPK) other;
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
