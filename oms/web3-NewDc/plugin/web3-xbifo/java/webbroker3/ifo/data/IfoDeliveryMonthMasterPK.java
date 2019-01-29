head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoDeliveryMonthMasterPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * <b>IfoDeliveryMonthMaster</b>データベーステーブルで一意である1つのレコードをあらわす<b>IfoDeliveryMonthMaster</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>IfoDeliveryMonthMaster</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoDeliveryMonthMasterRow 
 */
public class IfoDeliveryMonthMasterPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ifo_delivery_month_master";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: IfoDeliveryMonthMasterRow. 
   */
  public RowType getRowType() {
    return IfoDeliveryMonthMasterRow.TYPE;
  }

  /**
   * <em>underlying_product_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public String underlying_product_code;
  /**
   * <em>future_option_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String future_option_div;
  /**
   * <em>delivery_month</em>カラムの値をあらわす6桁以下のString値 
   */
  public String delivery_month;


  /** 
   * デフォルトコンストラクタ 
   */
  public IfoDeliveryMonthMasterPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_underlyingProductCode <em>underlying_product_code</em>カラムの値をあらわす4桁以下のString値 
   * @@param p_futureOptionDiv <em>future_option_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_deliveryMonth <em>delivery_month</em>カラムの値をあらわす6桁以下のString値 
   */
  public IfoDeliveryMonthMasterPK( String p_underlyingProductCode, String p_futureOptionDiv, String p_deliveryMonth ) {
      underlying_product_code = p_underlyingProductCode;
      future_option_div = p_futureOptionDiv;
      delivery_month = p_deliveryMonth;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static IfoDeliveryMonthMasterPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    IfoDeliveryMonthMasterPK pk = new IfoDeliveryMonthMasterPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.underlying_product_code = st.nextToken();
    pk.future_option_div = st.nextToken();
    pk.delivery_month = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = underlying_product_code + "," + future_option_div + "," + delivery_month;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof IfoDeliveryMonthMasterPK) )
      return false;
    IfoDeliveryMonthMasterPK o = (IfoDeliveryMonthMasterPK) other;
    if ( underlying_product_code == null ) {
      if ( o.underlying_product_code != null )
        return false;
    } else if ( !underlying_product_code.equals( o.underlying_product_code ) ) {
        return false;
    }
    if ( future_option_div == null ) {
      if ( o.future_option_div != null )
        return false;
    } else if ( !future_option_div.equals( o.future_option_div ) ) {
        return false;
    }
    if ( delivery_month == null ) {
      if ( o.delivery_month != null )
        return false;
    } else if ( !delivery_month.equals( o.delivery_month ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (underlying_product_code!=null? underlying_product_code.hashCode(): 0) 
        + (future_option_div!=null? future_option_div.hashCode(): 0) 
        + (delivery_month!=null? delivery_month.hashCode(): 0) 
    ;
  }

}

@
