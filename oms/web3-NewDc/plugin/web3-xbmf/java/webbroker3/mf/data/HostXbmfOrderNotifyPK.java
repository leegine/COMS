head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostXbmfOrderNotifyPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>HostXbmfOrderNotify</b>データベーステーブルで一意である1つのレコードをあらわす<b>HostXbmfOrderNotify</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>HostXbmfOrderNotify</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostXbmfOrderNotifyRow 
 */
public class HostXbmfOrderNotifyPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_xbmf_order_notify";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: HostXbmfOrderNotifyRow. 
   */
  public RowType getRowType() {
    return HostXbmfOrderNotifyRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String branch_code;
  /**
   * <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public String order_request_number;


  /** 
   * デフォルトコンストラクタ 
   */
  public HostXbmfOrderNotifyPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_orderRequestNumber <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public HostXbmfOrderNotifyPK( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      order_request_number = p_orderRequestNumber;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static HostXbmfOrderNotifyPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    HostXbmfOrderNotifyPK pk = new HostXbmfOrderNotifyPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.order_request_number = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + order_request_number;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof HostXbmfOrderNotifyPK) )
      return false;
    HostXbmfOrderNotifyPK o = (HostXbmfOrderNotifyPK) other;
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
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
    ;
  }

}

@
