head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	YCustomerPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>YCustomer</b>データベーステーブルで一意である1つのレコードをあらわす<b>YCustomer</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>YCustomer</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see YCustomerRow 
 */
public class YCustomerPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "y_customer";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: YCustomerRow. 
   */
  public RowType getRowType() {
    return YCustomerRow.TYPE;
  }

  /**
   * <em>y_customer_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long y_customer_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public YCustomerPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_yCustomerId <em>y_customer_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public YCustomerPK( long p_yCustomerId ) {
      y_customer_id = p_yCustomerId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static YCustomerPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    YCustomerPK pk = new YCustomerPK();
    pk.y_customer_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(y_customer_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof YCustomerPK) )
      return false;
    YCustomerPK o = (YCustomerPK) other;
    if ( y_customer_id != o.y_customer_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) y_customer_id)
    ;
  }

}

@
