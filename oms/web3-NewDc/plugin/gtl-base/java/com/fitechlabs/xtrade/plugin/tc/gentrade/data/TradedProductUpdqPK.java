head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.40.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	TradedProductUpdqPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>TradedProductUpdq</b>データベーステーブルで一意である1つのレコードをあらわす<b>TradedProductUpdq</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>TradedProductUpdq</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TradedProductUpdqRow 
 */
public class TradedProductUpdqPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "traded_product_updq";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: TradedProductUpdqRow. 
   */
  public RowType getRowType() {
    return TradedProductUpdqRow.TYPE;
  }

  /**
   * <em>traded_product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long traded_product_id;
  /**
   * <em>valid_until_biz_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public String valid_until_biz_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public TradedProductUpdqPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_tradedProductId <em>traded_product_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_validUntilBizDate <em>valid_until_biz_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public TradedProductUpdqPK( long p_tradedProductId, String p_validUntilBizDate ) {
      traded_product_id = p_tradedProductId;
      valid_until_biz_date = p_validUntilBizDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static TradedProductUpdqPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TradedProductUpdqPK pk = new TradedProductUpdqPK();
    int i = pkValueString.indexOf(',');
    pk.traded_product_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.valid_until_biz_date = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(traded_product_id) + "," + valid_until_biz_date;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TradedProductUpdqPK) )
      return false;
    TradedProductUpdqPK o = (TradedProductUpdqPK) other;
    if ( traded_product_id != o.traded_product_id )
      return false;
    if ( valid_until_biz_date == null ) {
      if ( o.valid_until_biz_date != null )
        return false;
    } else if ( !valid_until_biz_date.equals( o.valid_until_biz_date ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) traded_product_id)
        + (valid_until_biz_date!=null? valid_until_biz_date.hashCode(): 0) 
    ;
  }

}

@
