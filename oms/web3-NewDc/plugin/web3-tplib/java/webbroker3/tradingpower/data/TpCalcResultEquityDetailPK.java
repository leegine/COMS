head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.29.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultEquityDetailPK.java;


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
 * <b>TpCalcResultEquityDetail</b>データベーステーブルで一意である1つのレコードをあらわす<b>TpCalcResultEquityDetail</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>TpCalcResultEquityDetail</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCalcResultEquityDetailRow 
 */
public class TpCalcResultEquityDetailPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "tp_calc_result_equity_detail";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: TpCalcResultEquityDetailRow. 
   */
  public RowType getRowType() {
    return TpCalcResultEquityDetailRow.TYPE;
  }

  /**
   * <em>calc_result_equity_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long calc_result_equity_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public TpCalcResultEquityDetailPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_calcResultEquityId <em>calc_result_equity_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public TpCalcResultEquityDetailPK( long p_calcResultEquityId ) {
      calc_result_equity_id = p_calcResultEquityId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static TpCalcResultEquityDetailPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TpCalcResultEquityDetailPK pk = new TpCalcResultEquityDetailPK();
    pk.calc_result_equity_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(calc_result_equity_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TpCalcResultEquityDetailPK) )
      return false;
    TpCalcResultEquityDetailPK o = (TpCalcResultEquityDetailPK) other;
    if ( calc_result_equity_id != o.calc_result_equity_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) calc_result_equity_id)
    ;
  }

}

@
