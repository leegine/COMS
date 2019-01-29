head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AccountTransferPK.java;


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
 * <b>AccountTransfer</b>データベーステーブルで一意である1つのレコードをあらわす<b>AccountTransfer</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AccountTransfer</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccountTransferRow 
 */
public class AccountTransferPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "account_transfer";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AccountTransferRow. 
   */
  public RowType getRowType() {
    return AccountTransferRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>work_day</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp work_day;
  /**
   * <em>branch_code_old</em>カラムの値をあらわす3桁以下のString値 
   */
  public String branch_code_old;
  /**
   * <em>account_code_old</em>カラムの値をあらわす7桁以下のString値 
   */
  public String account_code_old;
  /**
   * <em>transfer_tbl</em>カラムの値をあらわす30桁以下のString値 
   */
  public String transfer_tbl;


  /** 
   * デフォルトコンストラクタ 
   */
  public AccountTransferPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_workDay <em>work_day</em>カラムの値をあらわすjava.sql.Timestamp値
   * @@param p_branchCodeOld <em>branch_code_old</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_accountCodeOld <em>account_code_old</em>カラムの値をあらわす7桁以下のString値 
   * @@param p_transferTbl <em>transfer_tbl</em>カラムの値をあらわす30桁以下のString値 
   */
  public AccountTransferPK( String p_institutionCode, java.sql.Timestamp p_workDay, String p_branchCodeOld, String p_accountCodeOld, String p_transferTbl ) {
      institution_code = p_institutionCode;
      work_day = p_workDay;
      branch_code_old = p_branchCodeOld;
      account_code_old = p_accountCodeOld;
      transfer_tbl = p_transferTbl;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AccountTransferPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AccountTransferPK pk = new AccountTransferPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.work_day = java.sql.Timestamp.valueOf(st.nextToken());
    pk.branch_code_old = st.nextToken();
    pk.account_code_old = st.nextToken();
    pk.transfer_tbl = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(work_day) + "," + branch_code_old + "," + account_code_old + "," + transfer_tbl;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AccountTransferPK) )
      return false;
    AccountTransferPK o = (AccountTransferPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( work_day == null ) {
      if ( o.work_day != null )
        return false;
    } else if ( !work_day.equals( o.work_day ) ) {
        return false;
    }
    if ( branch_code_old == null ) {
      if ( o.branch_code_old != null )
        return false;
    } else if ( !branch_code_old.equals( o.branch_code_old ) ) {
        return false;
    }
    if ( account_code_old == null ) {
      if ( o.account_code_old != null )
        return false;
    } else if ( !account_code_old.equals( o.account_code_old ) ) {
        return false;
    }
    if ( transfer_tbl == null ) {
      if ( o.transfer_tbl != null )
        return false;
    } else if ( !transfer_tbl.equals( o.transfer_tbl ) ) {
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
        + (work_day!=null? work_day.hashCode(): 0) 
        + (branch_code_old!=null? branch_code_old.hashCode(): 0) 
        + (account_code_old!=null? account_code_old.hashCode(): 0) 
        + (transfer_tbl!=null? transfer_tbl.hashCode(): 0) 
    ;
  }

}

@
