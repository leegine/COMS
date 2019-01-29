head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.21.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OnlineRunStatusPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>OnlineRunStatus</b>データベーステーブルで一意である1つのレコードをあらわす<b>OnlineRunStatus</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>OnlineRunStatus</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OnlineRunStatusRow 
 */
public class OnlineRunStatusPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "online_run_status";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: OnlineRunStatusRow. 
   */
  public RowType getRowType() {
    return OnlineRunStatusRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum product_type;
  /**
   * <em>future_option_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String future_option_div;
  /**
   * <em>online_service_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String online_service_div;
  /**
   * <em>account_id_from</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long account_id_from;


  /** 
   * デフォルトコンストラクタ 
   */
  public OnlineRunStatusPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_productType <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   * @@param p_futureOptionDiv <em>future_option_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_onlineServiceDiv <em>online_service_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_accountIdFrom <em>account_id_from</em>カラムの値をあらわす18桁以下のlong値 
   */
  public OnlineRunStatusPK( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_onlineServiceDiv, long p_accountIdFrom ) {
      institution_code = p_institutionCode;
      product_type = p_productType;
      future_option_div = p_futureOptionDiv;
      online_service_div = p_onlineServiceDiv;
      account_id_from = p_accountIdFrom;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static OnlineRunStatusPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OnlineRunStatusPK pk = new OnlineRunStatusPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.future_option_div = st.nextToken();
    pk.online_service_div = st.nextToken();
    pk.account_id_from = Long.valueOf(st.nextToken()).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + String.valueOf(product_type.intValue()) + "," + future_option_div + "," + online_service_div + "," + String.valueOf(account_id_from);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OnlineRunStatusPK) )
      return false;
    OnlineRunStatusPK o = (OnlineRunStatusPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( product_type == null ) {
      if ( o.product_type != null )
        return false;
    } else if ( !product_type.equals( o.product_type ) ) {
        return false;
    }
    if ( future_option_div == null ) {
      if ( o.future_option_div != null )
        return false;
    } else if ( !future_option_div.equals( o.future_option_div ) ) {
        return false;
    }
    if ( online_service_div == null ) {
      if ( o.online_service_div != null )
        return false;
    } else if ( !online_service_div.equals( o.online_service_div ) ) {
        return false;
    }
    if ( account_id_from != o.account_id_from )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + (future_option_div!=null? future_option_div.hashCode(): 0) 
        + (online_service_div!=null? online_service_div.hashCode(): 0) 
        + ((int) account_id_from)
    ;
  }

}

@
