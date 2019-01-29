head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.26.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MarketMarginRatioPK.java;


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
 * <b>MarketMarginRatio</b>データベーステーブルで一意である1つのレコードをあらわす<b>MarketMarginRatio</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MarketMarginRatio</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MarketMarginRatioRow 
 */
public class MarketMarginRatioPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "market_margin_ratio";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MarketMarginRatioRow. 
   */
  public RowType getRowType() {
    return MarketMarginRatioRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long market_id;
  /**
   * <em>list_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public String list_type;
  /**
   * <em>new_list_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public String new_list_type;
  /**
   * <em>securities_estimation_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String securities_estimation_div;


  /** 
   * デフォルトコンストラクタ 
   */
  public MarketMarginRatioPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_marketId <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_listType <em>list_type</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_newListType <em>new_list_type</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_securitiesEstimationDiv <em>securities_estimation_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public MarketMarginRatioPK( String p_institutionCode, long p_marketId, String p_listType, String p_newListType, String p_securitiesEstimationDiv ) {
      institution_code = p_institutionCode;
      market_id = p_marketId;
      list_type = p_listType;
      new_list_type = p_newListType;
      securities_estimation_div = p_securitiesEstimationDiv;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MarketMarginRatioPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MarketMarginRatioPK pk = new MarketMarginRatioPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.market_id = Long.valueOf(st.nextToken()).longValue();
    pk.list_type = st.nextToken();
    pk.new_list_type = st.nextToken();
    pk.securities_estimation_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + String.valueOf(market_id) + "," + list_type + "," + new_list_type + "," + securities_estimation_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MarketMarginRatioPK) )
      return false;
    MarketMarginRatioPK o = (MarketMarginRatioPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( market_id != o.market_id )
      return false;
    if ( list_type == null ) {
      if ( o.list_type != null )
        return false;
    } else if ( !list_type.equals( o.list_type ) ) {
        return false;
    }
    if ( new_list_type == null ) {
      if ( o.new_list_type != null )
        return false;
    } else if ( !new_list_type.equals( o.new_list_type ) ) {
        return false;
    }
    if ( securities_estimation_div == null ) {
      if ( o.securities_estimation_div != null )
        return false;
    } else if ( !securities_estimation_div.equals( o.securities_estimation_div ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) market_id)
        + (list_type!=null? list_type.hashCode(): 0) 
        + (new_list_type!=null? new_list_type.hashCode(): 0) 
        + (securities_estimation_div!=null? securities_estimation_div.hashCode(): 0) 
    ;
  }

}

@
