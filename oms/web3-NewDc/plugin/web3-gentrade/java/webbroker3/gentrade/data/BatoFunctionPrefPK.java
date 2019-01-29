head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.40.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BatoFunctionPrefPK.java;


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
 * <b>BatoFunctionPref</b>データベーステーブルで一意である1つのレコードをあらわす<b>BatoFunctionPref</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>BatoFunctionPref</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BatoFunctionPrefRow 
 */
public class BatoFunctionPrefPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bato_function_pref";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: BatoFunctionPrefRow. 
   */
  public RowType getRowType() {
    return BatoFunctionPrefRow.TYPE;
  }

  /**
   * <em>function_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public String function_div;


  /** 
   * デフォルトコンストラクタ 
   */
  public BatoFunctionPrefPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_functionDiv <em>function_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public BatoFunctionPrefPK( String p_functionDiv ) {
      function_div = p_functionDiv;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static BatoFunctionPrefPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new BatoFunctionPrefPK( pkValueString );
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    return function_div;
  }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BatoFunctionPrefPK) )
      return false;
    BatoFunctionPrefPK o = (BatoFunctionPrefPK) other;
    if ( function_div == null ) {
      if ( o.function_div != null )
        return false;
    } else if ( !function_div.equals( o.function_div ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (function_div!=null? function_div.hashCode(): 0) 
    ;
  }

}

@
