head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.54.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	HostPaymentOrderPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * <b>HostPaymentOrder</b>データベーステーブルで一意である1つのレコードをあらわす<b>HostPaymentOrder</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>HostPaymentOrder</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostPaymentOrderRow 
 */
public class HostPaymentOrderPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_payment_order";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: HostPaymentOrderRow. 
   */
  public RowType getRowType() {
    return HostPaymentOrderRow.TYPE;
  }

  /**
   * <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public String request_code;
  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String branch_code;
  /**
   * <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public String account_code;
  /**
   * <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public String order_request_number;


  /** 
   * デフォルトコンストラクタ 
   */
  public HostPaymentOrderPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_requestCode <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   * @@param p_orderRequestNumber <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public HostPaymentOrderPK( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) {
      request_code = p_requestCode;
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      account_code = p_accountCode;
      order_request_number = p_orderRequestNumber;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static HostPaymentOrderPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    HostPaymentOrderPK pk = new HostPaymentOrderPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.request_code = st.nextToken();
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.account_code = st.nextToken();
    pk.order_request_number = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = request_code + "," + institution_code + "," + branch_code + "," + account_code + "," + order_request_number;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof HostPaymentOrderPK) )
      return false;
    HostPaymentOrderPK o = (HostPaymentOrderPK) other;
    if ( request_code == null ) {
      if ( o.request_code != null )
        return false;
    } else if ( !request_code.equals( o.request_code ) ) {
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
    if ( account_code == null ) {
      if ( o.account_code != null )
        return false;
    } else if ( !account_code.equals( o.account_code ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( o.order_request_number != null )
        return false;
    } else if ( !order_request_number.equals( o.order_request_number ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
    ;
  }

}

@
