head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.21.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	VirtualServerChangePK.java;


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
 * <b>VirtualServerChange</b>データベーステーブルで一意である1つのレコードをあらわす<b>VirtualServerChange</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>VirtualServerChange</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see VirtualServerChangeRow 
 */
public class VirtualServerChangePK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "virtual_server_change";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: VirtualServerChangeRow. 
   */
  public RowType getRowType() {
    return VirtualServerChangeRow.TYPE;
  }

  /**
   * <em>virtual_server_number_market</em>カラムの値をあらわす7桁以下のString値 
   */
  public String virtual_server_number_market;
  /**
   * <em>change_req_res_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public String change_req_res_div;
  /**
   * <em>notice_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public String notice_type;
  /**
   * <em>front_order_exchange_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public String front_order_exchange_code;


  /** 
   * デフォルトコンストラクタ 
   */
  public VirtualServerChangePK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_virtualServerNumberMarket <em>virtual_server_number_market</em>カラムの値をあらわす7桁以下のString値 
   * @@param p_changeReqResDiv <em>change_req_res_div</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_noticeType <em>notice_type</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_frontOrderExchangeCode <em>front_order_exchange_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public VirtualServerChangePK( String p_virtualServerNumberMarket, String p_changeReqResDiv, String p_noticeType, String p_frontOrderExchangeCode ) {
      virtual_server_number_market = p_virtualServerNumberMarket;
      change_req_res_div = p_changeReqResDiv;
      notice_type = p_noticeType;
      front_order_exchange_code = p_frontOrderExchangeCode;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static VirtualServerChangePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    VirtualServerChangePK pk = new VirtualServerChangePK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.virtual_server_number_market = st.nextToken();
    pk.change_req_res_div = st.nextToken();
    pk.notice_type = st.nextToken();
    pk.front_order_exchange_code = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = virtual_server_number_market + "," + change_req_res_div + "," + notice_type + "," + front_order_exchange_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof VirtualServerChangePK) )
      return false;
    VirtualServerChangePK o = (VirtualServerChangePK) other;
    if ( virtual_server_number_market == null ) {
      if ( o.virtual_server_number_market != null )
        return false;
    } else if ( !virtual_server_number_market.equals( o.virtual_server_number_market ) ) {
        return false;
    }
    if ( change_req_res_div == null ) {
      if ( o.change_req_res_div != null )
        return false;
    } else if ( !change_req_res_div.equals( o.change_req_res_div ) ) {
        return false;
    }
    if ( notice_type == null ) {
      if ( o.notice_type != null )
        return false;
    } else if ( !notice_type.equals( o.notice_type ) ) {
        return false;
    }
    if ( front_order_exchange_code == null ) {
      if ( o.front_order_exchange_code != null )
        return false;
    } else if ( !front_order_exchange_code.equals( o.front_order_exchange_code ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (virtual_server_number_market!=null? virtual_server_number_market.hashCode(): 0) 
        + (change_req_res_div!=null? change_req_res_div.hashCode(): 0) 
        + (notice_type!=null? notice_type.hashCode(): 0) 
        + (front_order_exchange_code!=null? front_order_exchange_code.hashCode(): 0) 
    ;
  }

}

@
