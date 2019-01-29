head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostPostalTransVoucherPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>HostPostalTransVoucher</b>データベーステーブルで一意である1つのレコードをあらわす<b>HostPostalTransVoucher</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>HostPostalTransVoucher</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostPostalTransVoucherRow 
 */
public class HostPostalTransVoucherPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_postal_trans_voucher";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: HostPostalTransVoucherRow. 
   */
  public RowType getRowType() {
    return HostPostalTransVoucherRow.TYPE;
  }

  /**
   * <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public String order_request_number;
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
   * デフォルトコンストラクタ 
   */
  public HostPostalTransVoucherPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   * @@param p_requestCode <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public HostPostalTransVoucherPK( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) {
      order_request_number = p_orderRequestNumber;
      request_code = p_requestCode;
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      account_code = p_accountCode;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static HostPostalTransVoucherPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    HostPostalTransVoucherPK pk = new HostPostalTransVoucherPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.order_request_number = st.nextToken();
    pk.request_code = st.nextToken();
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.account_code = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = order_request_number + "," + request_code + "," + institution_code + "," + branch_code + "," + account_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof HostPostalTransVoucherPK) )
      return false;
    HostPostalTransVoucherPK o = (HostPostalTransVoucherPK) other;
    if ( order_request_number == null ) {
      if ( o.order_request_number != null )
        return false;
    } else if ( !order_request_number.equals( o.order_request_number ) ) {
        return false;
    }
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
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
    ;
  }

}

@
