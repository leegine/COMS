head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.37.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CalendarPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>Calendar</b>データベーステーブルで一意である1つのレコードをあらわす<b>Calendar</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>Calendar</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CalendarRow 
 */
public class CalendarPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "calendar";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: CalendarRow. 
   */
  public RowType getRowType() {
    return CalendarRow.TYPE;
  }

  /**
   * <em>holiday</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp holiday;


  /** 
   * デフォルトコンストラクタ 
   */
  public CalendarPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_holiday <em>holiday</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public CalendarPK( java.sql.Timestamp p_holiday ) {
      holiday = p_holiday;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static CalendarPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    CalendarPK pk = new CalendarPK();
    pk.holiday = java.sql.Timestamp.valueOf(pkValueString);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(holiday);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof CalendarPK) )
      return false;
    CalendarPK o = (CalendarPK) other;
    if ( holiday == null ) {
      if ( o.holiday != null )
        return false;
    } else if ( !holiday.equals( o.holiday ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (holiday!=null? holiday.hashCode(): 0) 
    ;
  }

}

@
