head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	SpecialProductForeignCostPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>SpecialProductForeignCost</b>データベーステーブルで一意である1つのレコードをあらわす<b>SpecialProductForeignCost</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SpecialProductForeignCost</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SpecialProductForeignCostRow 
 */
public class SpecialProductForeignCostPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "special_product_foreign_cost";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SpecialProductForeignCostRow. 
   */
  public RowType getRowType() {
    return SpecialProductForeignCostRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String market_code;
  /**
   * <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long product_id;
  /**
   * <em>offshore_product_code</em>カラムの値をあらわす9桁以下のString値 
   */
  public String offshore_product_code;
  /**
   * <em>cost_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public String cost_div;
  /**
   * <em>appli_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp appli_start_date;
  /**
   * <em>amount_from</em>カラムの値をあらわす13桁以下のdouble値で、その精度は2桁まで
   */
  public double amount_from;
  /**
   * <em>side_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String side_div;


  /** 
   * デフォルトコンストラクタ 
   */
  public SpecialProductForeignCostPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_offshoreProductCode <em>offshore_product_code</em>カラムの値をあらわす9桁以下のString値 
   * @@param p_costDiv <em>cost_div</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_appliStartDate <em>appli_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   * @@param p_amountFrom <em>amount_from</em>カラムの値をあらわす13桁以下のdouble値で、その精度は2桁まで
   * @@param p_sideDiv <em>side_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public SpecialProductForeignCostPK( String p_institutionCode, String p_marketCode, long p_productId, String p_offshoreProductCode, String p_costDiv, java.sql.Timestamp p_appliStartDate, double p_amountFrom, String p_sideDiv ) {
      institution_code = p_institutionCode;
      market_code = p_marketCode;
      product_id = p_productId;
      offshore_product_code = p_offshoreProductCode;
      cost_div = p_costDiv;
      appli_start_date = p_appliStartDate;
      amount_from = p_amountFrom;
      side_div = p_sideDiv;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SpecialProductForeignCostPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SpecialProductForeignCostPK pk = new SpecialProductForeignCostPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.market_code = st.nextToken();
    pk.product_id = Long.valueOf(st.nextToken()).longValue();
    pk.offshore_product_code = st.nextToken();
    pk.cost_div = st.nextToken();
    pk.appli_start_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.amount_from = Double.valueOf(st.nextToken()).doubleValue();
    pk.side_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + market_code + "," + String.valueOf(product_id) + "," + offshore_product_code + "," + cost_div + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(appli_start_date) + "," + amount_fromFormat.format(amount_from) + "," + side_div;
    return m_id;
  }

  private String m_id = null;
  private static final java.text.DecimalFormat amount_fromFormat = new java.text.DecimalFormat("#.##");

  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SpecialProductForeignCostPK) )
      return false;
    SpecialProductForeignCostPK o = (SpecialProductForeignCostPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( o.market_code != null )
        return false;
    } else if ( !market_code.equals( o.market_code ) ) {
        return false;
    }
    if ( product_id != o.product_id )
      return false;
    if ( offshore_product_code == null ) {
      if ( o.offshore_product_code != null )
        return false;
    } else if ( !offshore_product_code.equals( o.offshore_product_code ) ) {
        return false;
    }
    if ( cost_div == null ) {
      if ( o.cost_div != null )
        return false;
    } else if ( !cost_div.equals( o.cost_div ) ) {
        return false;
    }
    if ( appli_start_date == null ) {
      if ( o.appli_start_date != null )
        return false;
    } else if ( !appli_start_date.equals( o.appli_start_date ) ) {
        return false;
    }
    if ( amount_from != o.amount_from )
      return false;
    if ( side_div == null ) {
      if ( o.side_div != null )
        return false;
    } else if ( !side_div.equals( o.side_div ) ) {
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
        + (market_code!=null? market_code.hashCode(): 0) 
        + ((int) product_id)
        + (offshore_product_code!=null? offshore_product_code.hashCode(): 0) 
        + (cost_div!=null? cost_div.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
        + ((int) amount_from)
        + (side_div!=null? side_div.hashCode(): 0) 
    ;
  }

}

@
