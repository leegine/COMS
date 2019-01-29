head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.44.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	QuoteStatusPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.stdimpls.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>QuoteStatus</b>データベーステーブルで一意である1つのレコードをあらわす<b>QuoteStatus</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>QuoteStatus</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see QuoteStatusRow 
 */
public class QuoteStatusPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "quote_status";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: QuoteStatusRow. 
   */
  public RowType getRowType() {
    return QuoteStatusRow.TYPE;
  }

  /**
   * <em>host_name</em>カラムの値をあらわす64桁以下のString値 
   */
  public String host_name;


  /** 
   * デフォルトコンストラクタ 
   */
  public QuoteStatusPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_hostName <em>host_name</em>カラムの値をあらわす64桁以下のString値 
   */
  public QuoteStatusPK( String p_hostName ) {
      host_name = p_hostName;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static QuoteStatusPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new QuoteStatusPK( pkValueString );
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    return host_name;
  }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof QuoteStatusPK) )
      return false;
    QuoteStatusPK o = (QuoteStatusPK) other;
    if ( host_name == null ) {
      if ( o.host_name != null )
        return false;
    } else if ( !host_name.equals( o.host_name ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (host_name!=null? host_name.hashCode(): 0) 
    ;
  }

}

@
