head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.25.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	QtpRichPushIfoLapsePK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data.qtp;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>QtpRichPushIfoLapse</b>データベーステーブルで一意である1つのレコードをあらわす<b>QtpRichPushIfoLapse</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>QtpRichPushIfoLapse</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see QtpRichPushIfoLapseRow 
 */
public class QtpRichPushIfoLapsePK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "qtp_rich_push_ifo_lapse";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: QtpRichPushIfoLapseRow. 
   */
  public RowType getRowType() {
    return QtpRichPushIfoLapseRow.TYPE;
  }

  /**
   * <em>serlnum</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long serlnum;


  /** 
   * デフォルトコンストラクタ 
   */
  public QtpRichPushIfoLapsePK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_serlnum <em>serlnum</em>カラムの値をあらわす18桁以下のlong値 
   */
  public QtpRichPushIfoLapsePK( long p_serlnum ) {
      serlnum = p_serlnum;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static QtpRichPushIfoLapsePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    QtpRichPushIfoLapsePK pk = new QtpRichPushIfoLapsePK();
    pk.serlnum = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(serlnum);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof QtpRichPushIfoLapsePK) )
      return false;
    QtpRichPushIfoLapsePK o = (QtpRichPushIfoLapsePK) other;
    if ( serlnum != o.serlnum )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) serlnum)
    ;
  }

}

@
