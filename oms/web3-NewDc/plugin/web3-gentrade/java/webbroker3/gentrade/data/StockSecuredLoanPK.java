head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	StockSecuredLoanPK.java;


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
 * <b>StockSecuredLoan</b>データベーステーブルで一意である1つのレコードをあらわす<b>StockSecuredLoan</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>StockSecuredLoan</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see StockSecuredLoanRow 
 */
public class StockSecuredLoanPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "stock_secured_loan";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: StockSecuredLoanRow. 
   */
  public RowType getRowType() {
    return StockSecuredLoanRow.TYPE;
  }

  /**
   * <em>stock_loan_account_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public String stock_loan_account_code;


  /** 
   * デフォルトコンストラクタ 
   */
  public StockSecuredLoanPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_stockLoanAccountCode <em>stock_loan_account_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public StockSecuredLoanPK( String p_stockLoanAccountCode ) {
      stock_loan_account_code = p_stockLoanAccountCode;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static StockSecuredLoanPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new StockSecuredLoanPK( pkValueString );
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    return stock_loan_account_code;
  }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof StockSecuredLoanPK) )
      return false;
    StockSecuredLoanPK o = (StockSecuredLoanPK) other;
    if ( stock_loan_account_code == null ) {
      if ( o.stock_loan_account_code != null )
        return false;
    } else if ( !stock_loan_account_code.equals( o.stock_loan_account_code ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (stock_loan_account_code!=null? stock_loan_account_code.hashCode(): 0) 
    ;
  }

}

@
