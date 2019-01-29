head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.44.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	CacheMonitorOrdAccStatusPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.cachemonitor.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>CacheMonitorOrdAccStatus</b>データベーステーブルで一意である1つのレコードをあらわす<b>CacheMonitorOrdAccStatus</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>CacheMonitorOrdAccStatus</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CacheMonitorOrdAccStatusRow 
 */
public class CacheMonitorOrdAccStatusPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "cache_monitor_ord_acc_status";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: CacheMonitorOrdAccStatusRow. 
   */
  public RowType getRowType() {
    return CacheMonitorOrdAccStatusRow.TYPE;
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
   * <em>order_acc_product</em>カラムの値をあらわす2桁以下のString値 
   */
  public String order_acc_product;
  /**
   * <em>order_acc_transaction</em>カラムの値をあらわす2桁以下のString値 
   */
  public String order_acc_transaction;


  /** 
   * デフォルトコンストラクタ 
   */
  public CacheMonitorOrdAccStatusPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_orderAccProduct <em>order_acc_product</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_orderAccTransaction <em>order_acc_transaction</em>カラムの値をあらわす2桁以下のString値 
   */
  public CacheMonitorOrdAccStatusPK( String p_institutionCode, String p_branchCode, String p_orderAccProduct, String p_orderAccTransaction ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      order_acc_product = p_orderAccProduct;
      order_acc_transaction = p_orderAccTransaction;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static CacheMonitorOrdAccStatusPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    CacheMonitorOrdAccStatusPK pk = new CacheMonitorOrdAccStatusPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.order_acc_product = st.nextToken();
    pk.order_acc_transaction = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + order_acc_product + "," + order_acc_transaction;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof CacheMonitorOrdAccStatusPK) )
      return false;
    CacheMonitorOrdAccStatusPK o = (CacheMonitorOrdAccStatusPK) other;
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
    if ( order_acc_product == null ) {
      if ( o.order_acc_product != null )
        return false;
    } else if ( !order_acc_product.equals( o.order_acc_product ) ) {
        return false;
    }
    if ( order_acc_transaction == null ) {
      if ( o.order_acc_transaction != null )
        return false;
    } else if ( !order_acc_transaction.equals( o.order_acc_transaction ) ) {
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
        + (order_acc_product!=null? order_acc_product.hashCode(): 0) 
        + (order_acc_transaction!=null? order_acc_transaction.hashCode(): 0) 
    ;
  }

}

@
