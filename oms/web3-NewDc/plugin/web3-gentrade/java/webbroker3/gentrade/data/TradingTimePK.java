head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.24.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TradingTimePK.java;


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
 * <b>TradingTime</b>データベーステーブルで一意である1つのレコードをあらわす<b>TradingTime</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>TradingTime</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TradingTimeRow 
 */
public class TradingTimePK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "trading_time";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: TradingTimeRow. 
   */
  public RowType getRowType() {
    return TradingTimeRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String branch_code;
  /**
   * <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String market_code;
  /**
   * <em>trading_time_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public String trading_time_type;
  /**
   * <em>product_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public String product_code;
  /**
   * <em>biz_date_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public String biz_date_type;
  /**
   * <em>start_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public String start_time;


  /** 
   * デフォルトコンストラクタ 
   */
  public TradingTimePK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_tradingTimeType <em>trading_time_type</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす7桁以下のString値 
   * @@param p_bizDateType <em>biz_date_type</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_startTime <em>start_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public TradingTimePK( String p_institutionCode, String p_branchCode, String p_marketCode, String p_tradingTimeType, String p_productCode, String p_bizDateType, String p_startTime ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      market_code = p_marketCode;
      trading_time_type = p_tradingTimeType;
      product_code = p_productCode;
      biz_date_type = p_bizDateType;
      start_time = p_startTime;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static TradingTimePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TradingTimePK pk = new TradingTimePK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.market_code = st.nextToken();
    pk.trading_time_type = st.nextToken();
    pk.product_code = st.nextToken();
    pk.biz_date_type = st.nextToken();
    pk.start_time = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + market_code + "," + trading_time_type + "," + product_code + "," + biz_date_type + "," + start_time;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TradingTimePK) )
      return false;
    TradingTimePK o = (TradingTimePK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( o.branch_code != null )
        return false;
    } else if ( !branch_code.equals( o.branch_code ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( o.market_code != null )
        return false;
    } else if ( !market_code.equals( o.market_code ) ) {
        return false;
    }
    if ( trading_time_type == null ) {
      if ( o.trading_time_type != null )
        return false;
    } else if ( !trading_time_type.equals( o.trading_time_type ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( o.product_code != null )
        return false;
    } else if ( !product_code.equals( o.product_code ) ) {
        return false;
    }
    if ( biz_date_type == null ) {
      if ( o.biz_date_type != null )
        return false;
    } else if ( !biz_date_type.equals( o.biz_date_type ) ) {
        return false;
    }
    if ( start_time == null ) {
      if ( o.start_time != null )
        return false;
    } else if ( !start_time.equals( o.start_time ) ) {
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
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (trading_time_type!=null? trading_time_type.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (biz_date_type!=null? biz_date_type.hashCode(): 0) 
        + (start_time!=null? start_time.hashCode(): 0) 
    ;
  }

}

@
