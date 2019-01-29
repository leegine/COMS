head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	OrderExecutedCountPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.adminorderexecinquiry.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>OrderExecutedCount</b>データベーステーブルで一意である1つのレコードをあらわす<b>OrderExecutedCount</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>OrderExecutedCount</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrderExecutedCountRow 
 */
public class OrderExecutedCountPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "order_executed_count";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: OrderExecutedCountRow. 
   */
  public RowType getRowType() {
    return OrderExecutedCountRow.TYPE;
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
   * <em>record_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String record_div;
  /**
   * <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String market_code;
  /**
   * <em>product_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public String product_div;
  /**
   * <em>order_chanel</em>カラムの値をあらわす1桁以下のString値 
   */
  public String order_chanel;
  /**
   * <em>order_root_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String order_root_div;
  /**
   * <em>biz_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public String biz_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public OrderExecutedCountPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_recordDiv <em>record_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_productDiv <em>product_div</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_orderChanel <em>order_chanel</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_orderRootDiv <em>order_root_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_bizDate <em>biz_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public OrderExecutedCountPK( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_marketCode, String p_productDiv, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      record_div = p_recordDiv;
      market_code = p_marketCode;
      product_div = p_productDiv;
      order_chanel = p_orderChanel;
      order_root_div = p_orderRootDiv;
      biz_date = p_bizDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static OrderExecutedCountPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OrderExecutedCountPK pk = new OrderExecutedCountPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.record_div = st.nextToken();
    pk.market_code = st.nextToken();
    pk.product_div = st.nextToken();
    pk.order_chanel = st.nextToken();
    pk.order_root_div = st.nextToken();
    pk.biz_date = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + record_div + "," + market_code + "," + product_div + "," + order_chanel + "," + order_root_div + "," + biz_date;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OrderExecutedCountPK) )
      return false;
    OrderExecutedCountPK o = (OrderExecutedCountPK) other;
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
    if ( record_div == null ) {
      if ( o.record_div != null )
        return false;
    } else if ( !record_div.equals( o.record_div ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( o.market_code != null )
        return false;
    } else if ( !market_code.equals( o.market_code ) ) {
        return false;
    }
    if ( product_div == null ) {
      if ( o.product_div != null )
        return false;
    } else if ( !product_div.equals( o.product_div ) ) {
        return false;
    }
    if ( order_chanel == null ) {
      if ( o.order_chanel != null )
        return false;
    } else if ( !order_chanel.equals( o.order_chanel ) ) {
        return false;
    }
    if ( order_root_div == null ) {
      if ( o.order_root_div != null )
        return false;
    } else if ( !order_root_div.equals( o.order_root_div ) ) {
        return false;
    }
    if ( biz_date == null ) {
      if ( o.biz_date != null )
        return false;
    } else if ( !biz_date.equals( o.biz_date ) ) {
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
        + (record_div!=null? record_div.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (product_div!=null? product_div.hashCode(): 0) 
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (order_root_div!=null? order_root_div.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
    ;
  }

}

@
