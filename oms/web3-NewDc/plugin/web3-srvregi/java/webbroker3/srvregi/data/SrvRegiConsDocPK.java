head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.41.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiConsDocPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>SrvRegiConsDoc</b>データベーステーブルで一意である1つのレコードをあらわす<b>SrvRegiConsDoc</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SrvRegiConsDoc</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SrvRegiConsDocRow 
 */
public class SrvRegiConsDocPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "srv_regi_cons_doc";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SrvRegiConsDocRow. 
   */
  public RowType getRowType() {
    return SrvRegiConsDocRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>srv_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public String srv_div;
  /**
   * <em>line_number</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long line_number;


  /** 
   * デフォルトコンストラクタ 
   */
  public SrvRegiConsDocPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_srvDiv <em>srv_div</em>カラムの値をあらわす4桁以下のString値 
   * @@param p_lineNumber <em>line_number</em>カラムの値をあらわす18桁以下のlong値 
   */
  public SrvRegiConsDocPK( String p_institutionCode, String p_srvDiv, long p_lineNumber ) {
      institution_code = p_institutionCode;
      srv_div = p_srvDiv;
      line_number = p_lineNumber;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SrvRegiConsDocPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SrvRegiConsDocPK pk = new SrvRegiConsDocPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.srv_div = st.nextToken();
    pk.line_number = Long.valueOf(st.nextToken()).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + srv_div + "," + String.valueOf(line_number);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SrvRegiConsDocPK) )
      return false;
    SrvRegiConsDocPK o = (SrvRegiConsDocPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( srv_div == null ) {
      if ( o.srv_div != null )
        return false;
    } else if ( !srv_div.equals( o.srv_div ) ) {
        return false;
    }
    if ( line_number != o.line_number )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (srv_div!=null? srv_div.hashCode(): 0) 
        + ((int) line_number)
    ;
  }

}

@
