head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.31.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchIndexDealtCondPK.java;


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
 * <b>BranchIndexDealtCond</b>データベーステーブルで一意である1つのレコードをあらわす<b>BranchIndexDealtCond</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>BranchIndexDealtCond</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchIndexDealtCondRow 
 */
public class BranchIndexDealtCondPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "branch_index_dealt_cond";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: BranchIndexDealtCondRow. 
   */
  public RowType getRowType() {
    return BranchIndexDealtCondRow.TYPE;
  }

  /**
   * <em>target_product_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public String target_product_code;
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
   * <em>future_option_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String future_option_div;


  /** 
   * デフォルトコンストラクタ 
   */
  public BranchIndexDealtCondPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_targetProductCode <em>target_product_code</em>カラムの値をあらわす4桁以下のString値 
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_futureOptionDiv <em>future_option_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public BranchIndexDealtCondPK( String p_targetProductCode, String p_institutionCode, String p_branchCode, String p_marketCode, String p_futureOptionDiv ) {
      target_product_code = p_targetProductCode;
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      market_code = p_marketCode;
      future_option_div = p_futureOptionDiv;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static BranchIndexDealtCondPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BranchIndexDealtCondPK pk = new BranchIndexDealtCondPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.target_product_code = st.nextToken();
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.market_code = st.nextToken();
    pk.future_option_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = target_product_code + "," + institution_code + "," + branch_code + "," + market_code + "," + future_option_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BranchIndexDealtCondPK) )
      return false;
    BranchIndexDealtCondPK o = (BranchIndexDealtCondPK) other;
    if ( target_product_code == null ) {
      if ( o.target_product_code != null )
        return false;
    } else if ( !target_product_code.equals( o.target_product_code ) ) {
        return false;
    }
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
    if ( future_option_div == null ) {
      if ( o.future_option_div != null )
        return false;
    } else if ( !future_option_div.equals( o.future_option_div ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (target_product_code!=null? target_product_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (future_option_div!=null? future_option_div.hashCode(): 0) 
    ;
  }

}

@
