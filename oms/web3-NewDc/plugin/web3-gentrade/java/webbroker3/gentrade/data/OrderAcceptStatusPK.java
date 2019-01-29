head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.32.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderAcceptStatusPK.java;


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
 * <b>OrderAcceptStatus</b>データベーステーブルで一意である1つのレコードをあらわす<b>OrderAcceptStatus</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>OrderAcceptStatus</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrderAcceptStatusRow 
 */
public class OrderAcceptStatusPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "order_accept_status";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: OrderAcceptStatusRow. 
   */
  public RowType getRowType() {
    return OrderAcceptStatusRow.TYPE;
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
  public OrderAcceptStatusPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_orderAccProduct <em>order_acc_product</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_orderAccTransaction <em>order_acc_transaction</em>カラムの値をあらわす2桁以下のString値 
   */
  public OrderAcceptStatusPK( String p_institutionCode, String p_branchCode, String p_orderAccProduct, String p_orderAccTransaction ) {
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
  public static OrderAcceptStatusPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OrderAcceptStatusPK pk = new OrderAcceptStatusPK();
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
    if ( other == null || !( other instanceof OrderAcceptStatusPK) )
      return false;
    OrderAcceptStatusPK o = (OrderAcceptStatusPK) other;
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
