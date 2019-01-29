head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoLimitPriceRangeMasterPK.java;


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
 * <b>IfoLimitPriceRangeMaster</b>データベーステーブルで一意である1つのレコードをあらわす<b>IfoLimitPriceRangeMaster</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>IfoLimitPriceRangeMaster</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoLimitPriceRangeMasterRow 
 */
public class IfoLimitPriceRangeMasterPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ifo_limit_price_range_master";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: IfoLimitPriceRangeMasterRow. 
   */
  public RowType getRowType() {
    return IfoLimitPriceRangeMasterRow.TYPE;
  }

  /**
   * <em>target_product_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public String target_product_code;
  /**
   * <em>future_option_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String future_option_div;
  /**
   * <em>low_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public double low_price;


  /** 
   * デフォルトコンストラクタ 
   */
  public IfoLimitPriceRangeMasterPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_targetProductCode <em>target_product_code</em>カラムの値をあらわす4桁以下のString値 
   * @@param p_futureOptionDiv <em>future_option_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_lowPrice <em>low_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public IfoLimitPriceRangeMasterPK( String p_targetProductCode, String p_futureOptionDiv, double p_lowPrice ) {
      target_product_code = p_targetProductCode;
      future_option_div = p_futureOptionDiv;
      low_price = p_lowPrice;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static IfoLimitPriceRangeMasterPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    IfoLimitPriceRangeMasterPK pk = new IfoLimitPriceRangeMasterPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.target_product_code = st.nextToken();
    pk.future_option_div = st.nextToken();
    pk.low_price = Double.valueOf(st.nextToken()).doubleValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = target_product_code + "," + future_option_div + "," + low_priceFormat.format(low_price);
    return m_id;
  }

  private String m_id = null;
  private static final java.text.DecimalFormat low_priceFormat = new java.text.DecimalFormat("#.######");

  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof IfoLimitPriceRangeMasterPK) )
      return false;
    IfoLimitPriceRangeMasterPK o = (IfoLimitPriceRangeMasterPK) other;
    if ( target_product_code == null ) {
      if ( o.target_product_code != null )
        return false;
    } else if ( !target_product_code.equals( o.target_product_code ) ) {
        return false;
    }
    if ( future_option_div == null ) {
      if ( o.future_option_div != null )
        return false;
    } else if ( !future_option_div.equals( o.future_option_div ) ) {
        return false;
    }
    if ( low_price != o.low_price )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (target_product_code!=null? target_product_code.hashCode(): 0) 
        + (future_option_div!=null? future_option_div.hashCode(): 0) 
        + ((int) low_price)
    ;
  }

}

@
