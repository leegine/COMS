head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.50.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccountPortfolioPK.java;


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
 * <b>AccountPortfolio</b>データベーステーブルで一意である1つのレコードをあらわす<b>AccountPortfolio</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AccountPortfolio</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccountPortfolioRow 
 */
public class AccountPortfolioPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "account_portfolio";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AccountPortfolioRow. 
   */
  public RowType getRowType() {
    return AccountPortfolioRow.TYPE;
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
   * デフォルトコンストラクタ 
   */
  public AccountPortfolioPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_portfolioCode <em>portfolio_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public AccountPortfolioPK( String p_institutionCode, long p_branchId, long p_accountId, String p_portfolioCode ) {
      institution_code = p_institutionCode;
      branch_id = p_branchId;
      account_id = p_accountId;
      portfolio_code = p_portfolioCode;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AccountPortfolioPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AccountPortfolioPK pk = new AccountPortfolioPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_id = Long.valueOf(st.nextToken()).longValue();
    pk.account_id = Long.valueOf(st.nextToken()).longValue();
    pk.portfolio_code = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + String.valueOf(branch_id) + "," + String.valueOf(account_id) + "," + portfolio_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AccountPortfolioPK) )
      return false;
    AccountPortfolioPK o = (AccountPortfolioPK) other;
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
    ;
  }

}

@
