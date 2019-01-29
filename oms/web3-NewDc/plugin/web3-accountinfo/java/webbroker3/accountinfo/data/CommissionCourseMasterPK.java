head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.15.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommissionCourseMasterPK.java;


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
 * <b>CommissionCourseMaster</b>データベーステーブルで一意である1つのレコードをあらわす<b>CommissionCourseMaster</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>CommissionCourseMaster</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CommissionCourseMasterRow 
 */
public class CommissionCourseMasterPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "commission_course_master";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: CommissionCourseMasterRow. 
   */
  public RowType getRowType() {
    return CommissionCourseMasterRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String comm_product_code;
  /**
   * <em>commission_course_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public String commission_course_div;


  /** 
   * デフォルトコンストラクタ 
   */
  public CommissionCourseMasterPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_commProductCode <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_commissionCourseDiv <em>commission_course_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public CommissionCourseMasterPK( String p_institutionCode, String p_commProductCode, String p_commissionCourseDiv ) {
      institution_code = p_institutionCode;
      comm_product_code = p_commProductCode;
      commission_course_div = p_commissionCourseDiv;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static CommissionCourseMasterPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    CommissionCourseMasterPK pk = new CommissionCourseMasterPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.comm_product_code = st.nextToken();
    pk.commission_course_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + comm_product_code + "," + commission_course_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof CommissionCourseMasterPK) )
      return false;
    CommissionCourseMasterPK o = (CommissionCourseMasterPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( comm_product_code == null ) {
      if ( o.comm_product_code != null )
        return false;
    } else if ( !comm_product_code.equals( o.comm_product_code ) ) {
        return false;
    }
    if ( commission_course_div == null ) {
      if ( o.commission_course_div != null )
        return false;
    } else if ( !commission_course_div.equals( o.commission_course_div ) ) {
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
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (commission_course_div!=null? commission_course_div.hashCode(): 0) 
    ;
  }

}

@
