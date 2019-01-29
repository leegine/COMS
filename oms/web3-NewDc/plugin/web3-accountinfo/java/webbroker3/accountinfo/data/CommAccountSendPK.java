head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.15.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommAccountSendPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>CommAccountSend</b>データベーステーブルで一意である1つのレコードをあらわす<b>CommAccountSend</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>CommAccountSend</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CommAccountSendRow 
 */
public class CommAccountSendPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "comm_account_send";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: CommAccountSendRow. 
   */
  public RowType getRowType() {
    return CommAccountSendRow.TYPE;
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
   * <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public String account_code;
  /**
   * <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String comm_product_code;
  /**
   * <em>appli_start_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public String appli_start_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public CommAccountSendPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   * @@param p_commProductCode <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_appliStartDate <em>appli_start_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public CommAccountSendPK( String p_institutionCode, String p_branchCode, String p_accountCode, String p_commProductCode, String p_appliStartDate ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      account_code = p_accountCode;
      comm_product_code = p_commProductCode;
      appli_start_date = p_appliStartDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static CommAccountSendPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    CommAccountSendPK pk = new CommAccountSendPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.account_code = st.nextToken();
    pk.comm_product_code = st.nextToken();
    pk.appli_start_date = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + account_code + "," + comm_product_code + "," + appli_start_date;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof CommAccountSendPK) )
      return false;
    CommAccountSendPK o = (CommAccountSendPK) other;
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
    if ( comm_product_code == null ) {
      if ( o.comm_product_code != null )
        return false;
    } else if ( !comm_product_code.equals( o.comm_product_code ) ) {
        return false;
    }
    if ( appli_start_date == null ) {
      if ( o.appli_start_date != null )
        return false;
    } else if ( !appli_start_date.equals( o.appli_start_date ) ) {
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
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
    ;
  }

}

@
