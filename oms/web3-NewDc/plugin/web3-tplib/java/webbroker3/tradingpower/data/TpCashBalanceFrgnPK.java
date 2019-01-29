head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.29.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCashBalanceFrgnPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>TpCashBalanceFrgn</b>データベーステーブルで一意である1つのレコードをあらわす<b>TpCashBalanceFrgn</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>TpCashBalanceFrgn</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCashBalanceFrgnRow 
 */
public class TpCashBalanceFrgnPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "tp_cash_balance_frgn";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: TpCashBalanceFrgnRow. 
   */
  public RowType getRowType() {
    return TpCashBalanceFrgnRow.TYPE;
  }

  /**
   * <em>tp_cash_balance_frgn_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long tp_cash_balance_frgn_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public TpCashBalanceFrgnPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_tpCashBalanceFrgnId <em>tp_cash_balance_frgn_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public TpCashBalanceFrgnPK( long p_tpCashBalanceFrgnId ) {
      tp_cash_balance_frgn_id = p_tpCashBalanceFrgnId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static TpCashBalanceFrgnPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TpCashBalanceFrgnPK pk = new TpCashBalanceFrgnPK();
    pk.tp_cash_balance_frgn_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(tp_cash_balance_frgn_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TpCashBalanceFrgnPK) )
      return false;
    TpCashBalanceFrgnPK o = (TpCashBalanceFrgnPK) other;
    if ( tp_cash_balance_frgn_id != o.tp_cash_balance_frgn_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) tp_cash_balance_frgn_id)
    ;
  }

}

@
