head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultMarginDetailPK.java;


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
 * <b>TpCalcResultMarginDetail</b>データベーステーブルで一意である1つのレコードをあらわす<b>TpCalcResultMarginDetail</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>TpCalcResultMarginDetail</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCalcResultMarginDetailRow 
 */
public class TpCalcResultMarginDetailPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "tp_calc_result_margin_detail";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: TpCalcResultMarginDetailRow. 
   */
  public RowType getRowType() {
    return TpCalcResultMarginDetailRow.TYPE;
  }

  /**
   * <em>calc_result_margin_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long calc_result_margin_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public TpCalcResultMarginDetailPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_calcResultMarginId <em>calc_result_margin_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public TpCalcResultMarginDetailPK( long p_calcResultMarginId ) {
      calc_result_margin_id = p_calcResultMarginId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static TpCalcResultMarginDetailPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TpCalcResultMarginDetailPK pk = new TpCalcResultMarginDetailPK();
    pk.calc_result_margin_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(calc_result_margin_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TpCalcResultMarginDetailPK) )
      return false;
    TpCalcResultMarginDetailPK o = (TpCalcResultMarginDetailPK) other;
    if ( calc_result_margin_id != o.calc_result_margin_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) calc_result_margin_id)
    ;
  }

}

@
