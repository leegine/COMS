head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.28.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	OrixTpCalcResultMarginPK.java;


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
 * <b>OrixTpCalcResultMargin</b>データベーステーブルで一意である1つのレコードをあらわす<b>OrixTpCalcResultMargin</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>OrixTpCalcResultMargin</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrixTpCalcResultMarginRow 
 */
public class OrixTpCalcResultMarginPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "orix_tp_calc_result_margin";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: OrixTpCalcResultMarginRow. 
   */
  public RowType getRowType() {
    return OrixTpCalcResultMarginRow.TYPE;
  }

  /**
   * <em>calc_result_margin_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long calc_result_margin_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public OrixTpCalcResultMarginPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_calcResultMarginId <em>calc_result_margin_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public OrixTpCalcResultMarginPK( long p_calcResultMarginId ) {
      calc_result_margin_id = p_calcResultMarginId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static OrixTpCalcResultMarginPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OrixTpCalcResultMarginPK pk = new OrixTpCalcResultMarginPK();
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
    if ( other == null || !( other instanceof OrixTpCalcResultMarginPK) )
      return false;
    OrixTpCalcResultMarginPK o = (OrixTpCalcResultMarginPK) other;
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
