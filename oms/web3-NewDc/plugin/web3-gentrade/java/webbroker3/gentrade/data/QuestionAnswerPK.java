head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.23.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	QuestionAnswerPK.java;


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
 * <b>QuestionAnswer</b>データベーステーブルで一意である1つのレコードをあらわす<b>QuestionAnswer</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>QuestionAnswer</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see QuestionAnswerRow 
 */
public class QuestionAnswerPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "question_answer";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: QuestionAnswerRow. 
   */
  public RowType getRowType() {
    return QuestionAnswerRow.TYPE;
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
   * <em>question_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public String question_div;
  /**
   * <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public String order_request_number;
  /**
   * <em>question_no</em>カラムの値をあらわす3桁以下のString値 
   */
  public String question_no;


  /** 
   * デフォルトコンストラクタ 
   */
  public QuestionAnswerPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_questionDiv <em>question_div</em>カラムの値をあらわす4桁以下のString値 
   * @@param p_orderRequestNumber <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   * @@param p_questionNo <em>question_no</em>カラムの値をあらわす3桁以下のString値 
   */
  public QuestionAnswerPK( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_orderRequestNumber, String p_questionNo ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      question_div = p_questionDiv;
      order_request_number = p_orderRequestNumber;
      question_no = p_questionNo;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static QuestionAnswerPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    QuestionAnswerPK pk = new QuestionAnswerPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.question_div = st.nextToken();
    pk.order_request_number = st.nextToken();
    pk.question_no = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + question_div + "," + order_request_number + "," + question_no;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof QuestionAnswerPK) )
      return false;
    QuestionAnswerPK o = (QuestionAnswerPK) other;
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
    if ( question_div == null ) {
      if ( o.question_div != null )
        return false;
    } else if ( !question_div.equals( o.question_div ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( o.order_request_number != null )
        return false;
    } else if ( !order_request_number.equals( o.order_request_number ) ) {
        return false;
    }
    if ( question_no == null ) {
      if ( o.question_no != null )
        return false;
    } else if ( !question_no.equals( o.question_no ) ) {
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
        + (question_div!=null? question_div.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (question_no!=null? question_no.hashCode(): 0) 
    ;
  }

}

@
