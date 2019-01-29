head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchMarketRepayDealtCondPK.java;


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
 * <b>BranchMarketRepayDealtCond</b>データベーステーブルで一意である1つのレコードをあらわす<b>BranchMarketRepayDealtCond</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>BranchMarketRepayDealtCond</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchMarketRepayDealtCondRow 
 */
public class BranchMarketRepayDealtCondPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "branch_market_repay_dealt_cond";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: BranchMarketRepayDealtCondRow. 
   */
  public RowType getRowType() {
    return BranchMarketRepayDealtCondRow.TYPE;
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
   * <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String market_code;
  /**
   * <em>repayment_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String repayment_div;
  /**
   * <em>repayment_num</em>カラムの値をあらわす7桁以下のint値 
   */
  public int repayment_num;


  /** 
   * デフォルトコンストラクタ 
   */
  public BranchMarketRepayDealtCondPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_repaymentDiv <em>repayment_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_repaymentNum <em>repayment_num</em>カラムの値をあらわす7桁以下のint値 
   */
  public BranchMarketRepayDealtCondPK( String p_institutionCode, String p_branchCode, String p_marketCode, String p_repaymentDiv, int p_repaymentNum ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      market_code = p_marketCode;
      repayment_div = p_repaymentDiv;
      repayment_num = p_repaymentNum;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static BranchMarketRepayDealtCondPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BranchMarketRepayDealtCondPK pk = new BranchMarketRepayDealtCondPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.market_code = st.nextToken();
    pk.repayment_div = st.nextToken();
    pk.repayment_num = Integer.valueOf(st.nextToken()).intValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + market_code + "," + repayment_div + "," + String.valueOf(repayment_num);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BranchMarketRepayDealtCondPK) )
      return false;
    BranchMarketRepayDealtCondPK o = (BranchMarketRepayDealtCondPK) other;
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
    if ( market_code == null ) {
      if ( o.market_code != null )
        return false;
    } else if ( !market_code.equals( o.market_code ) ) {
        return false;
    }
    if ( repayment_div == null ) {
      if ( o.repayment_div != null )
        return false;
    } else if ( !repayment_div.equals( o.repayment_div ) ) {
        return false;
    }
    if ( repayment_num != o.repayment_num )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (repayment_div!=null? repayment_div.hashCode(): 0) 
        + ((int) repayment_num)
    ;
  }

}

@
