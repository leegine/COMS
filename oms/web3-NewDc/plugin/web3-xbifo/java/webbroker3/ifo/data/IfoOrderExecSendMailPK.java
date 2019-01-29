head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoOrderExecSendMailPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * <b>IfoOrderExecSendMail</b>データベーステーブルで一意である1つのレコードをあらわす<b>IfoOrderExecSendMail</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>IfoOrderExecSendMail</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoOrderExecSendMailRow 
 */
public class IfoOrderExecSendMailPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ifo_order_exec_send_mail";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: IfoOrderExecSendMailRow. 
   */
  public RowType getRowType() {
    return IfoOrderExecSendMailRow.TYPE;
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
   * <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public String order_request_number;
  /**
   * <em>order_exec_status</em>カラムの値をあらわす1桁以下のString値 
   */
  public String order_exec_status;
  /**
   * <em>order_action_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long order_action_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public IfoOrderExecSendMailPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_orderRequestNumber <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   * @@param p_orderExecStatus <em>order_exec_status</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_orderActionId <em>order_action_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public IfoOrderExecSendMailPK( String p_institutionCode, String p_branchCode, String p_orderRequestNumber, String p_orderExecStatus, long p_orderActionId ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      order_request_number = p_orderRequestNumber;
      order_exec_status = p_orderExecStatus;
      order_action_id = p_orderActionId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static IfoOrderExecSendMailPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    IfoOrderExecSendMailPK pk = new IfoOrderExecSendMailPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.order_request_number = st.nextToken();
    pk.order_exec_status = st.nextToken();
    pk.order_action_id = Long.valueOf(st.nextToken()).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + order_request_number + "," + order_exec_status + "," + String.valueOf(order_action_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof IfoOrderExecSendMailPK) )
      return false;
    IfoOrderExecSendMailPK o = (IfoOrderExecSendMailPK) other;
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
    if ( order_exec_status == null ) {
      if ( o.order_exec_status != null )
        return false;
    } else if ( !order_exec_status.equals( o.order_exec_status ) ) {
        return false;
    }
    if ( order_action_id != o.order_action_id )
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
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (order_exec_status!=null? order_exec_status.hashCode(): 0) 
        + ((int) order_action_id)
    ;
  }

}

@
