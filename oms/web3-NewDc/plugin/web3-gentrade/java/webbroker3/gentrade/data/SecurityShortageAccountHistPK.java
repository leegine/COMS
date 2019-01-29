head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.40.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SecurityShortageAccountHistPK.java;


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
 * <b>SecurityShortageAccountHist</b>データベーステーブルで一意である1つのレコードをあらわす<b>SecurityShortageAccountHist</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SecurityShortageAccountHist</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SecurityShortageAccountHistRow 
 */
public class SecurityShortageAccountHistPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "security_shortage_account_hist";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SecurityShortageAccountHistRow. 
   */
  public RowType getRowType() {
    return SecurityShortageAccountHistRow.TYPE;
  }

  /**
   * <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long account_id;
  /**
   * <em>proc_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public String proc_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public SecurityShortageAccountHistPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_procDate <em>proc_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public SecurityShortageAccountHistPK( long p_accountId, String p_procDate ) {
      account_id = p_accountId;
      proc_date = p_procDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SecurityShortageAccountHistPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SecurityShortageAccountHistPK pk = new SecurityShortageAccountHistPK();
    int i = pkValueString.indexOf(',');
    pk.account_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.proc_date = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(account_id) + "," + proc_date;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SecurityShortageAccountHistPK) )
      return false;
    SecurityShortageAccountHistPK o = (SecurityShortageAccountHistPK) other;
    if ( account_id != o.account_id )
      return false;
    if ( proc_date == null ) {
      if ( o.proc_date != null )
        return false;
    } else if ( !proc_date.equals( o.proc_date ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) account_id)
        + (proc_date!=null? proc_date.hashCode(): 0) 
    ;
  }

}

@
