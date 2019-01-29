head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.43.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiDealingCommPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>SrvRegiDealingComm</b>データベーステーブルで一意である1つのレコードをあらわす<b>SrvRegiDealingComm</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SrvRegiDealingComm</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SrvRegiDealingCommRow 
 */
public class SrvRegiDealingCommPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "srv_regi_dealing_comm";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SrvRegiDealingCommRow. 
   */
  public RowType getRowType() {
    return SrvRegiDealingCommRow.TYPE;
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
   * <em>account_code</em>カラムの値をあらわす20桁以下のString値 
   */
  public String account_code;
  /**
   * <em>accumulate_term</em>カラムの値をあらわす6桁以下のString値 
   */
  public String accumulate_term;
  /**
   * <em>order_acc_product</em>カラムの値をあらわす2桁以下のString値 
   */
  public String order_acc_product;


  /** 
   * デフォルトコンストラクタ 
   */
  public SrvRegiDealingCommPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす20桁以下のString値 
   * @@param p_accumulateTerm <em>accumulate_term</em>カラムの値をあらわす6桁以下のString値 
   * @@param p_orderAccProduct <em>order_acc_product</em>カラムの値をあらわす2桁以下のString値 
   */
  public SrvRegiDealingCommPK( String p_institutionCode, String p_branchCode, String p_accountCode, String p_accumulateTerm, String p_orderAccProduct ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      account_code = p_accountCode;
      accumulate_term = p_accumulateTerm;
      order_acc_product = p_orderAccProduct;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SrvRegiDealingCommPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SrvRegiDealingCommPK pk = new SrvRegiDealingCommPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.account_code = st.nextToken();
    pk.accumulate_term = st.nextToken();
    pk.order_acc_product = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + account_code + "," + accumulate_term + "," + order_acc_product;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SrvRegiDealingCommPK) )
      return false;
    SrvRegiDealingCommPK o = (SrvRegiDealingCommPK) other;
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
    if ( account_code == null ) {
      if ( o.account_code != null )
        return false;
    } else if ( !account_code.equals( o.account_code ) ) {
        return false;
    }
    if ( accumulate_term == null ) {
      if ( o.accumulate_term != null )
        return false;
    } else if ( !accumulate_term.equals( o.accumulate_term ) ) {
        return false;
    }
    if ( order_acc_product == null ) {
      if ( o.order_acc_product != null )
        return false;
    } else if ( !order_acc_product.equals( o.order_acc_product ) ) {
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
        + (account_code!=null? account_code.hashCode(): 0) 
        + (accumulate_term!=null? accumulate_term.hashCode(): 0) 
        + (order_acc_product!=null? order_acc_product.hashCode(): 0) 
    ;
  }

}

@
