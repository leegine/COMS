head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundInstCommissionPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>MutualFundInstCommission</b>データベーステーブルで一意である1つのレコードをあらわす<b>MutualFundInstCommission</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MutualFundInstCommission</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundInstCommissionRow 
 */
public class MutualFundInstCommissionPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mutual_fund_inst_commission";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MutualFundInstCommissionRow. 
   */
  public RowType getRowType() {
    return MutualFundInstCommissionRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public String product_code;
  /**
   * <em>deal_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String deal_div;
  /**
   * <em>order_chanel</em>カラムの値をあらわす1桁以下のString値 
   */
  public String order_chanel;
  /**
   * <em>valid_date_from</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp valid_date_from;


  /** 
   * デフォルトコンストラクタ 
   */
  public MutualFundInstCommissionPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   * @@param p_dealDiv <em>deal_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_orderChanel <em>order_chanel</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_validDateFrom <em>valid_date_from</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public MutualFundInstCommissionPK( String p_institutionCode, String p_productCode, String p_dealDiv, String p_orderChanel, java.sql.Timestamp p_validDateFrom ) {
      institution_code = p_institutionCode;
      product_code = p_productCode;
      deal_div = p_dealDiv;
      order_chanel = p_orderChanel;
      valid_date_from = p_validDateFrom;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MutualFundInstCommissionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MutualFundInstCommissionPK pk = new MutualFundInstCommissionPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.product_code = st.nextToken();
    pk.deal_div = st.nextToken();
    pk.order_chanel = st.nextToken();
    pk.valid_date_from = java.sql.Timestamp.valueOf(st.nextToken());
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + product_code + "," + deal_div + "," + order_chanel + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(valid_date_from);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MutualFundInstCommissionPK) )
      return false;
    MutualFundInstCommissionPK o = (MutualFundInstCommissionPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( o.product_code != null )
        return false;
    } else if ( !product_code.equals( o.product_code ) ) {
        return false;
    }
    if ( deal_div == null ) {
      if ( o.deal_div != null )
        return false;
    } else if ( !deal_div.equals( o.deal_div ) ) {
        return false;
    }
    if ( order_chanel == null ) {
      if ( o.order_chanel != null )
        return false;
    } else if ( !order_chanel.equals( o.order_chanel ) ) {
        return false;
    }
    if ( valid_date_from == null ) {
      if ( o.valid_date_from != null )
        return false;
    } else if ( !valid_date_from.equals( o.valid_date_from ) ) {
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
        + (product_code!=null? product_code.hashCode(): 0) 
        + (deal_div!=null? deal_div.hashCode(): 0) 
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (valid_date_from!=null? valid_date_from.hashCode(): 0) 
    ;
  }

}

@
