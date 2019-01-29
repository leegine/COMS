head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.17.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommissionCourseRegistPK.java;


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
 * <b>CommissionCourseRegist</b>データベーステーブルで一意である1つのレコードをあらわす<b>CommissionCourseRegist</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>CommissionCourseRegist</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CommissionCourseRegistRow 
 */
public class CommissionCourseRegistPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "commission_course_regist";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: CommissionCourseRegistRow. 
   */
  public RowType getRowType() {
    return CommissionCourseRegistRow.TYPE;
  }

  /**
   * <em>commission_course_regist_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long commission_course_regist_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public CommissionCourseRegistPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_commissionCourseRegistId <em>commission_course_regist_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public CommissionCourseRegistPK( long p_commissionCourseRegistId ) {
      commission_course_regist_id = p_commissionCourseRegistId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static CommissionCourseRegistPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    CommissionCourseRegistPK pk = new CommissionCourseRegistPK();
    pk.commission_course_regist_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(commission_course_regist_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof CommissionCourseRegistPK) )
      return false;
    CommissionCourseRegistPK o = (CommissionCourseRegistPK) other;
    if ( commission_course_regist_id != o.commission_course_regist_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) commission_course_regist_id)
    ;
  }

}

@
