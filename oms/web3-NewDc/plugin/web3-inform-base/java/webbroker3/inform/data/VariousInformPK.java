head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.58.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	VariousInformPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>VariousInform</b>データベーステーブルで一意である1つのレコードをあらわす<b>VariousInform</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>VariousInform</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see VariousInformRow 
 */
public class VariousInformPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "various_inform";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: VariousInformRow. 
   */
  public RowType getRowType() {
    return VariousInformRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>inform_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public String inform_div;
  /**
   * <em>request_number</em>カラムの値をあらわす13桁以下のString値 
   */
  public String request_number;
  /**
   * <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String branch_code;


  /** 
   * デフォルトコンストラクタ 
   */
  public VariousInformPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_informDiv <em>inform_div</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_requestNumber <em>request_number</em>カラムの値をあらわす13桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public VariousInformPK( String p_institutionCode, String p_informDiv, String p_requestNumber, String p_branchCode ) {
      institution_code = p_institutionCode;
      inform_div = p_informDiv;
      request_number = p_requestNumber;
      branch_code = p_branchCode;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static VariousInformPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    VariousInformPK pk = new VariousInformPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.inform_div = st.nextToken();
    pk.request_number = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + inform_div + "," + request_number + "," + branch_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof VariousInformPK) )
      return false;
    VariousInformPK o = (VariousInformPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( inform_div == null ) {
      if ( o.inform_div != null )
        return false;
    } else if ( !inform_div.equals( o.inform_div ) ) {
        return false;
    }
    if ( request_number == null ) {
      if ( o.request_number != null )
        return false;
    } else if ( !request_number.equals( o.request_number ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( o.branch_code != null )
        return false;
    } else if ( !branch_code.equals( o.branch_code ) ) {
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
        + (inform_div!=null? inform_div.hashCode(): 0) 
        + (request_number!=null? request_number.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
    ;
  }

}

@
