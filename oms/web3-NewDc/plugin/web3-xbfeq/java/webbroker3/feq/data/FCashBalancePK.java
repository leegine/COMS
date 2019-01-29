head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	FCashBalancePK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>FCashBalance</b>データベーステーブルで一意である1つのレコードをあらわす<b>FCashBalance</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>FCashBalance</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FCashBalanceRow 
 */
public class FCashBalancePK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "f_cash_balance";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: FCashBalanceRow. 
   */
  public RowType getRowType() {
    return FCashBalanceRow.TYPE;
  }

  /**
   * <em>feq_cash_balance_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long feq_cash_balance_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public FCashBalancePK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_feqCashBalanceId <em>feq_cash_balance_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public FCashBalancePK( long p_feqCashBalanceId ) {
      feq_cash_balance_id = p_feqCashBalanceId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static FCashBalancePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    FCashBalancePK pk = new FCashBalancePK();
    pk.feq_cash_balance_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(feq_cash_balance_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof FCashBalancePK) )
      return false;
    FCashBalancePK o = (FCashBalancePK) other;
    if ( feq_cash_balance_id != o.feq_cash_balance_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) feq_cash_balance_id)
    ;
  }

}

@
