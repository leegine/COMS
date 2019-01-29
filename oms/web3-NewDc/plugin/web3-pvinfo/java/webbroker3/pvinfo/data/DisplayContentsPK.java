head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.11.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	DisplayContentsPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.pvinfo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>DisplayContents</b>データベーステーブルで一意である1つのレコードをあらわす<b>DisplayContents</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>DisplayContents</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DisplayContentsRow 
 */
public class DisplayContentsPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "display_contents";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: DisplayContentsRow. 
   */
  public RowType getRowType() {
    return DisplayContentsRow.TYPE;
  }

  /**
   * <em>display_contents_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long display_contents_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public DisplayContentsPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_displayContentsId <em>display_contents_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public DisplayContentsPK( long p_displayContentsId ) {
      display_contents_id = p_displayContentsId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static DisplayContentsPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    DisplayContentsPK pk = new DisplayContentsPK();
    pk.display_contents_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(display_contents_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof DisplayContentsPK) )
      return false;
    DisplayContentsPK o = (DisplayContentsPK) other;
    if ( display_contents_id != o.display_contents_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) display_contents_id)
    ;
  }

}

@
