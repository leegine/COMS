head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.17.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TradingpowerCalcConditionPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>TradingpowerCalcCondition</b>データベーステーブルで一意である1つのレコードをあらわす<b>TradingpowerCalcCondition</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>TradingpowerCalcCondition</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TradingpowerCalcConditionRow 
 */
public class TradingpowerCalcConditionPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "tradingpower_calc_condition";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: TradingpowerCalcConditionRow. 
   */
  public RowType getRowType() {
    return TradingpowerCalcConditionRow.TYPE;
  }

  /**
   * <em>calc_condition_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long calc_condition_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public TradingpowerCalcConditionPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_calcConditionId <em>calc_condition_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public TradingpowerCalcConditionPK( long p_calcConditionId ) {
      calc_condition_id = p_calcConditionId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static TradingpowerCalcConditionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TradingpowerCalcConditionPK pk = new TradingpowerCalcConditionPK();
    pk.calc_condition_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(calc_condition_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TradingpowerCalcConditionPK) )
      return false;
    TradingpowerCalcConditionPK o = (TradingpowerCalcConditionPK) other;
    if ( calc_condition_id != o.calc_condition_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) calc_condition_id)
    ;
  }

}

@
