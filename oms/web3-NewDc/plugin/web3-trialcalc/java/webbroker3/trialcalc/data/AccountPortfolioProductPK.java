head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.50.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccountPortfolioProductPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.trialcalc.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>AccountPortfolioProduct</b>データベーステーブルで一意である1つのレコードをあらわす<b>AccountPortfolioProduct</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AccountPortfolioProduct</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccountPortfolioProductRow 
 */
public class AccountPortfolioProductPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "account_portfolio_product";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AccountPortfolioProductRow. 
   */
  public RowType getRowType() {
    return AccountPortfolioProductRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long branch_id;
  /**
   * <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long account_id;
  /**
   * <em>portfolio_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public String portfolio_code;
  /**
   * <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long product_id;
  /**
   * <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String market_code;
  /**
   * <em>buy_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public double buy_price;


  /** 
   * デフォルトコンストラクタ 
   */
  public AccountPortfolioProductPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_portfolioCode <em>portfolio_code</em>カラムの値をあらわす10桁以下のString値 
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_buyPrice <em>buy_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public AccountPortfolioProductPK( String p_institutionCode, long p_branchId, long p_accountId, String p_portfolioCode, long p_productId, String p_marketCode, double p_buyPrice ) {
      institution_code = p_institutionCode;
      branch_id = p_branchId;
      account_id = p_accountId;
      portfolio_code = p_portfolioCode;
      product_id = p_productId;
      market_code = p_marketCode;
      buy_price = p_buyPrice;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AccountPortfolioProductPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AccountPortfolioProductPK pk = new AccountPortfolioProductPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_id = Long.valueOf(st.nextToken()).longValue();
    pk.account_id = Long.valueOf(st.nextToken()).longValue();
    pk.portfolio_code = st.nextToken();
    pk.product_id = Long.valueOf(st.nextToken()).longValue();
    pk.market_code = st.nextToken();
    pk.buy_price = Double.valueOf(st.nextToken()).doubleValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + String.valueOf(branch_id) + "," + String.valueOf(account_id) + "," + portfolio_code + "," + String.valueOf(product_id) + "," + market_code + "," + buy_priceFormat.format(buy_price);
    return m_id;
  }

  private String m_id = null;
  private static final java.text.DecimalFormat buy_priceFormat = new java.text.DecimalFormat("#.######");

  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AccountPortfolioProductPK) )
      return false;
    AccountPortfolioProductPK o = (AccountPortfolioProductPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( branch_id != o.branch_id )
      return false;
    if ( account_id != o.account_id )
      return false;
    if ( portfolio_code == null ) {
      if ( o.portfolio_code != null )
        return false;
    } else if ( !portfolio_code.equals( o.portfolio_code ) ) {
        return false;
    }
    if ( product_id != o.product_id )
      return false;
    if ( market_code == null ) {
      if ( o.market_code != null )
        return false;
    } else if ( !market_code.equals( o.market_code ) ) {
        return false;
    }
    if ( buy_price != o.buy_price )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) branch_id)
        + ((int) account_id)
        + (portfolio_code!=null? portfolio_code.hashCode(): 0) 
        + ((int) product_id)
        + (market_code!=null? market_code.hashCode(): 0) 
        + ((int) buy_price)
    ;
  }

}

@
