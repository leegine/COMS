head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocForceLogoutRunStatusPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>DocForceLogoutRunStatus</b>データベーステーブルで一意である1つのレコードをあらわす<b>DocForceLogoutRunStatus</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>DocForceLogoutRunStatus</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DocForceLogoutRunStatusRow 
 */
public class DocForceLogoutRunStatusPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "doc_force_logout_run_status";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: DocForceLogoutRunStatusRow. 
   */
  public RowType getRowType() {
    return DocForceLogoutRunStatusRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>account_id_from</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long account_id_from;


  /** 
   * デフォルトコンストラクタ 
   */
  public DocForceLogoutRunStatusPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_accountIdFrom <em>account_id_from</em>カラムの値をあらわす18桁以下のlong値 
   */
  public DocForceLogoutRunStatusPK( String p_institutionCode, long p_accountIdFrom ) {
      institution_code = p_institutionCode;
      account_id_from = p_accountIdFrom;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static DocForceLogoutRunStatusPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    DocForceLogoutRunStatusPK pk = new DocForceLogoutRunStatusPK();
    int i = pkValueString.indexOf(',');
    pk.institution_code = pkValueString.substring(0,i);
    pk.account_id_from = Long.valueOf(pkValueString.substring(i+1)).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + String.valueOf(account_id_from);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof DocForceLogoutRunStatusPK) )
      return false;
    DocForceLogoutRunStatusPK o = (DocForceLogoutRunStatusPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( account_id_from != o.account_id_from )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) account_id_from)
    ;
  }

}

@
