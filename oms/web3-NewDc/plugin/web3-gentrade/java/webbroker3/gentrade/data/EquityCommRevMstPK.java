head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityCommRevMstPK.java;


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
 * <b>EquityCommRevMst</b>データベーステーブルで一意である1つのレコードをあらわす<b>EquityCommRevMst</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>EquityCommRevMst</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EquityCommRevMstRow 
 */
public class EquityCommRevMstPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "equity_comm_rev_mst";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: EquityCommRevMstRow. 
   */
  public RowType getRowType() {
    return EquityCommRevMstRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String comm_product_code;
  /**
   * <em>appli_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp appli_start_date;
  /**
   * <em>order_channel</em>カラムの値をあらわす1桁以下のString値 
   */
  public String order_channel;
  /**
   * <em>transaction_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public String transaction_type;
  /**
   * <em>exec_cond_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public String exec_cond_type;
  /**
   * <em>pay_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public String pay_type;
  /**
   * <em>sonar_market_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public String sonar_market_code;
  /**
   * <em>underlying_product_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public String underlying_product_code;
  /**
   * <em>day_trade_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public String day_trade_type;


  /** 
   * デフォルトコンストラクタ 
   */
  public EquityCommRevMstPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_commProductCode <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_appliStartDate <em>appli_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   * @@param p_orderChannel <em>order_channel</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_transactionType <em>transaction_type</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_execCondType <em>exec_cond_type</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_payType <em>pay_type</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_sonarMarketCode <em>sonar_market_code</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_underlyingProductCode <em>underlying_product_code</em>カラムの値をあらわす4桁以下のString値 
   * @@param p_dayTradeType <em>day_trade_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public EquityCommRevMstPK( String p_institutionCode, String p_commProductCode, java.sql.Timestamp p_appliStartDate, String p_orderChannel, String p_transactionType, String p_execCondType, String p_payType, String p_sonarMarketCode, String p_underlyingProductCode, String p_dayTradeType ) {
      institution_code = p_institutionCode;
      comm_product_code = p_commProductCode;
      appli_start_date = p_appliStartDate;
      order_channel = p_orderChannel;
      transaction_type = p_transactionType;
      exec_cond_type = p_execCondType;
      pay_type = p_payType;
      sonar_market_code = p_sonarMarketCode;
      underlying_product_code = p_underlyingProductCode;
      day_trade_type = p_dayTradeType;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static EquityCommRevMstPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    EquityCommRevMstPK pk = new EquityCommRevMstPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.comm_product_code = st.nextToken();
    pk.appli_start_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.order_channel = st.nextToken();
    pk.transaction_type = st.nextToken();
    pk.exec_cond_type = st.nextToken();
    pk.pay_type = st.nextToken();
    pk.sonar_market_code = st.nextToken();
    pk.underlying_product_code = st.nextToken();
    pk.day_trade_type = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + comm_product_code + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(appli_start_date) + "," + order_channel + "," + transaction_type + "," + exec_cond_type + "," + pay_type + "," + sonar_market_code + "," + underlying_product_code + "," + day_trade_type;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof EquityCommRevMstPK) )
      return false;
    EquityCommRevMstPK o = (EquityCommRevMstPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( comm_product_code == null ) {
      if ( o.comm_product_code != null )
        return false;
    } else if ( !comm_product_code.equals( o.comm_product_code ) ) {
        return false;
    }
    if ( appli_start_date == null ) {
      if ( o.appli_start_date != null )
        return false;
    } else if ( !appli_start_date.equals( o.appli_start_date ) ) {
        return false;
    }
    if ( order_channel == null ) {
      if ( o.order_channel != null )
        return false;
    } else if ( !order_channel.equals( o.order_channel ) ) {
        return false;
    }
    if ( transaction_type == null ) {
      if ( o.transaction_type != null )
        return false;
    } else if ( !transaction_type.equals( o.transaction_type ) ) {
        return false;
    }
    if ( exec_cond_type == null ) {
      if ( o.exec_cond_type != null )
        return false;
    } else if ( !exec_cond_type.equals( o.exec_cond_type ) ) {
        return false;
    }
    if ( pay_type == null ) {
      if ( o.pay_type != null )
        return false;
    } else if ( !pay_type.equals( o.pay_type ) ) {
        return false;
    }
    if ( sonar_market_code == null ) {
      if ( o.sonar_market_code != null )
        return false;
    } else if ( !sonar_market_code.equals( o.sonar_market_code ) ) {
        return false;
    }
    if ( underlying_product_code == null ) {
      if ( o.underlying_product_code != null )
        return false;
    } else if ( !underlying_product_code.equals( o.underlying_product_code ) ) {
        return false;
    }
    if ( day_trade_type == null ) {
      if ( o.day_trade_type != null )
        return false;
    } else if ( !day_trade_type.equals( o.day_trade_type ) ) {
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
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
        + (order_channel!=null? order_channel.hashCode(): 0) 
        + (transaction_type!=null? transaction_type.hashCode(): 0) 
        + (exec_cond_type!=null? exec_cond_type.hashCode(): 0) 
        + (pay_type!=null? pay_type.hashCode(): 0) 
        + (sonar_market_code!=null? sonar_market_code.hashCode(): 0) 
        + (underlying_product_code!=null? underlying_product_code.hashCode(): 0) 
        + (day_trade_type!=null? day_trade_type.hashCode(): 0) 
    ;
  }

}

@
