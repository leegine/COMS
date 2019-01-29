head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.33.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MailProcPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>MailProc</b>データベーステーブルで一意である1つのレコードをあらわす<b>MailProc</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MailProc</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MailProcRow 
 */
public class MailProcPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mail_proc";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MailProcRow. 
   */
  public RowType getRowType() {
    return MailProcRow.TYPE;
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
   * <em>sendmail_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public String sendmail_div;
  /**
   * <em>discernment_id</em>カラムの値をあらわす4桁以下のString値 
   */
  public String discernment_id;
  /**
   * <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public String account_code;
  /**
   * <em>mail_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long mail_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public MailProcPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_sendmailDiv <em>sendmail_div</em>カラムの値をあらわす4桁以下のString値 
   * @@param p_discernmentId <em>discernment_id</em>カラムの値をあらわす4桁以下のString値 
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   * @@param p_mailId <em>mail_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public MailProcPK( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      sendmail_div = p_sendmailDiv;
      discernment_id = p_discernmentId;
      account_code = p_accountCode;
      mail_id = p_mailId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MailProcPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MailProcPK pk = new MailProcPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.sendmail_div = st.nextToken();
    pk.discernment_id = st.nextToken();
    pk.account_code = st.nextToken();
    pk.mail_id = Long.valueOf(st.nextToken()).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + sendmail_div + "," + discernment_id + "," + account_code + "," + String.valueOf(mail_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MailProcPK) )
      return false;
    MailProcPK o = (MailProcPK) other;
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
    if ( sendmail_div == null ) {
      if ( o.sendmail_div != null )
        return false;
    } else if ( !sendmail_div.equals( o.sendmail_div ) ) {
        return false;
    }
    if ( discernment_id == null ) {
      if ( o.discernment_id != null )
        return false;
    } else if ( !discernment_id.equals( o.discernment_id ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( o.account_code != null )
        return false;
    } else if ( !account_code.equals( o.account_code ) ) {
        return false;
    }
    if ( mail_id != o.mail_id )
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
        + (sendmail_div!=null? sendmail_div.hashCode(): 0) 
        + (discernment_id!=null? discernment_id.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + ((int) mail_id)
    ;
  }

}

@
