head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	FeqOrderEmpNumberPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.*;

/** 
 * <b>FeqOrderEmpNumber</b>データベーステーブルで一意である1つのレコードをあらわす<b>FeqOrderEmpNumber</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>FeqOrderEmpNumber</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FeqOrderEmpNumberRow 
 */
public class FeqOrderEmpNumberPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "feq_order_emp_number";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: FeqOrderEmpNumberRow. 
   */
  public RowType getRowType() {
    return FeqOrderEmpNumberRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>feq_order_emp_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String feq_order_emp_div;
  /**
   * <em>biz_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp biz_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public FeqOrderEmpNumberPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_feqOrderEmpDiv <em>feq_order_emp_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_bizDate <em>biz_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public FeqOrderEmpNumberPK( String p_institutionCode, String p_feqOrderEmpDiv, java.sql.Timestamp p_bizDate ) {
      institution_code = p_institutionCode;
      feq_order_emp_div = p_feqOrderEmpDiv;
      biz_date = p_bizDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static FeqOrderEmpNumberPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    FeqOrderEmpNumberPK pk = new FeqOrderEmpNumberPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.feq_order_emp_div = st.nextToken();
    pk.biz_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + feq_order_emp_div + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(biz_date);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof FeqOrderEmpNumberPK) )
      return false;
    FeqOrderEmpNumberPK o = (FeqOrderEmpNumberPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( feq_order_emp_div == null ) {
      if ( o.feq_order_emp_div != null )
        return false;
    } else if ( !feq_order_emp_div.equals( o.feq_order_emp_div ) ) {
        return false;
    }
    if ( biz_date == null ) {
      if ( o.biz_date != null )
        return false;
    } else if ( !biz_date.equals( o.biz_date ) ) {
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
        + (feq_order_emp_div!=null? feq_order_emp_div.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
    ;
  }

}

@
