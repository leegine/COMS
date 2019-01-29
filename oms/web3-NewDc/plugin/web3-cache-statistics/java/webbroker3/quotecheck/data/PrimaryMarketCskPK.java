head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	PrimaryMarketCskPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quotecheck.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>PrimaryMarketCsk</b>データベーステーブルで一意である1つのレコードをあらわす<b>PrimaryMarketCsk</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>PrimaryMarketCsk</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PrimaryMarketCskRow 
 */
public class PrimaryMarketCskPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "primary_market_csk";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: PrimaryMarketCskRow. 
   */
  public RowType getRowType() {
    return PrimaryMarketCskRow.TYPE;
  }

  /**
   * <em>fund_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public String fund_code;


  /** 
   * デフォルトコンストラクタ 
   */
  public PrimaryMarketCskPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_fundCode <em>fund_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public PrimaryMarketCskPK( String p_fundCode ) {
      fund_code = p_fundCode;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static PrimaryMarketCskPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new PrimaryMarketCskPK( pkValueString );
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    return fund_code;
  }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof PrimaryMarketCskPK) )
      return false;
    PrimaryMarketCskPK o = (PrimaryMarketCskPK) other;
    if ( fund_code == null ) {
      if ( o.fund_code != null )
        return false;
    } else if ( !fund_code.equals( o.fund_code ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (fund_code!=null? fund_code.hashCode(): 0) 
    ;
  }

}

@
