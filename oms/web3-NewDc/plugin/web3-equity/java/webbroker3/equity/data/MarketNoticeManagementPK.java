head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MarketNoticeManagementPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>MarketNoticeManagement</b>データベーステーブルで一意である1つのレコードをあらわす<b>MarketNoticeManagement</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MarketNoticeManagement</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MarketNoticeManagementRow 
 */
public class MarketNoticeManagementPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "market_notice_management";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MarketNoticeManagementRow. 
   */
  public RowType getRowType() {
    return MarketNoticeManagementRow.TYPE;
  }

  /**
   * <em>virtual_server_number_market</em>カラムの値をあらわす7桁以下のString値 
   */
  public String virtual_server_number_market;
  /**
   * <em>notice_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public String notice_type;
  /**
   * <em>notice_number</em>カラムの値をあらわす10桁以下のlong値 
   */
  public long notice_number;
  /**
   * <em>front_order_exchange_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public String front_order_exchange_code;
  /**
   * <em>front_order_system_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public String front_order_system_code;
  /**
   * <em>biz_date_count</em>カラムの値をあらわす1桁以下のint値 
   */
  public int biz_date_count;


  /** 
   * デフォルトコンストラクタ 
   */
  public MarketNoticeManagementPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_virtualServerNumberMarket <em>virtual_server_number_market</em>カラムの値をあらわす7桁以下のString値 
   * @@param p_noticeType <em>notice_type</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_noticeNumber <em>notice_number</em>カラムの値をあらわす10桁以下のlong値 
   * @@param p_frontOrderExchangeCode <em>front_order_exchange_code</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_frontOrderSystemCode <em>front_order_system_code</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_bizDateCount <em>biz_date_count</em>カラムの値をあらわす1桁以下のint値 
   */
  public MarketNoticeManagementPK( String p_virtualServerNumberMarket, String p_noticeType, long p_noticeNumber, String p_frontOrderExchangeCode, String p_frontOrderSystemCode, int p_bizDateCount ) {
      virtual_server_number_market = p_virtualServerNumberMarket;
      notice_type = p_noticeType;
      notice_number = p_noticeNumber;
      front_order_exchange_code = p_frontOrderExchangeCode;
      front_order_system_code = p_frontOrderSystemCode;
      biz_date_count = p_bizDateCount;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MarketNoticeManagementPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MarketNoticeManagementPK pk = new MarketNoticeManagementPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.virtual_server_number_market = st.nextToken();
    pk.notice_type = st.nextToken();
    pk.notice_number = Long.valueOf(st.nextToken()).longValue();
    pk.front_order_exchange_code = st.nextToken();
    pk.front_order_system_code = st.nextToken();
    pk.biz_date_count = Integer.valueOf(st.nextToken()).intValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = virtual_server_number_market + "," + notice_type + "," + String.valueOf(notice_number) + "," + front_order_exchange_code + "," + front_order_system_code + "," + String.valueOf(biz_date_count);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MarketNoticeManagementPK) )
      return false;
    MarketNoticeManagementPK o = (MarketNoticeManagementPK) other;
    if ( virtual_server_number_market == null ) {
      if ( o.virtual_server_number_market != null )
        return false;
    } else if ( !virtual_server_number_market.equals( o.virtual_server_number_market ) ) {
        return false;
    }
    if ( notice_type == null ) {
      if ( o.notice_type != null )
        return false;
    } else if ( !notice_type.equals( o.notice_type ) ) {
        return false;
    }
    if ( notice_number != o.notice_number )
      return false;
    if ( front_order_exchange_code == null ) {
      if ( o.front_order_exchange_code != null )
        return false;
    } else if ( !front_order_exchange_code.equals( o.front_order_exchange_code ) ) {
        return false;
    }
    if ( front_order_system_code == null ) {
      if ( o.front_order_system_code != null )
        return false;
    } else if ( !front_order_system_code.equals( o.front_order_system_code ) ) {
        return false;
    }
    if ( biz_date_count != o.biz_date_count )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (virtual_server_number_market!=null? virtual_server_number_market.hashCode(): 0) 
        + (notice_type!=null? notice_type.hashCode(): 0) 
        + ((int) notice_number)
        + (front_order_exchange_code!=null? front_order_exchange_code.hashCode(): 0) 
        + (front_order_system_code!=null? front_order_system_code.hashCode(): 0) 
        + ((int) biz_date_count)
    ;
  }

}

@
