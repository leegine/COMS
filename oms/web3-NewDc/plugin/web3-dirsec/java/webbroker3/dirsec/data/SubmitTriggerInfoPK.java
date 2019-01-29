head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SubmitTriggerInfoPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>SubmitTriggerInfo</b>データベーステーブルで一意である1つのレコードをあらわす<b>SubmitTriggerInfo</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SubmitTriggerInfo</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SubmitTriggerInfoRow 
 */
public class SubmitTriggerInfoPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "submit_trigger_info";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SubmitTriggerInfoRow. 
   */
  public RowType getRowType() {
    return SubmitTriggerInfoRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>request_code</em>カラムの値をあらわす6桁以下のString値 
   */
  public String request_code;
  /**
   * <em>job_id</em>カラムの値をあらわす16桁以下のString値 
   */
  public String job_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public SubmitTriggerInfoPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_requestCode <em>request_code</em>カラムの値をあらわす6桁以下のString値 
   * @@param p_jobId <em>job_id</em>カラムの値をあらわす16桁以下のString値 
   */
  public SubmitTriggerInfoPK( String p_institutionCode, String p_requestCode, String p_jobId ) {
      institution_code = p_institutionCode;
      request_code = p_requestCode;
      job_id = p_jobId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SubmitTriggerInfoPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SubmitTriggerInfoPK pk = new SubmitTriggerInfoPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.request_code = st.nextToken();
    pk.job_id = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + request_code + "," + job_id;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SubmitTriggerInfoPK) )
      return false;
    SubmitTriggerInfoPK o = (SubmitTriggerInfoPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( request_code == null ) {
      if ( o.request_code != null )
        return false;
    } else if ( !request_code.equals( o.request_code ) ) {
        return false;
    }
    if ( job_id == null ) {
      if ( o.job_id != null )
        return false;
    } else if ( !job_id.equals( o.job_id ) ) {
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
        + (request_code!=null? request_code.hashCode(): 0) 
        + (job_id!=null? job_id.hashCode(): 0) 
    ;
  }

}

@
