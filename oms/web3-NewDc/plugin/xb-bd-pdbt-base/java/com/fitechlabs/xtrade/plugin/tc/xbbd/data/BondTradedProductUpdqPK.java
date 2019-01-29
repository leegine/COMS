head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.58.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondTradedProductUpdqPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>BondTradedProductUpdq</b>データベーステーブルで一意である1つのレコードをあらわす<b>BondTradedProductUpdq</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>BondTradedProductUpdq</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondTradedProductUpdqRow 
 */
public class BondTradedProductUpdqPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bond_traded_product_updq";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: BondTradedProductUpdqRow. 
   */
  public RowType getRowType() {
    return BondTradedProductUpdqRow.TYPE;
  }

  /**
   * <em>traded_product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long traded_product_id;
  /**
   * <em>valid_for_biz_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public String valid_for_biz_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public BondTradedProductUpdqPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_tradedProductId <em>traded_product_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_validForBizDate <em>valid_for_biz_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public BondTradedProductUpdqPK( long p_tradedProductId, String p_validForBizDate ) {
      traded_product_id = p_tradedProductId;
      valid_for_biz_date = p_validForBizDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static BondTradedProductUpdqPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BondTradedProductUpdqPK pk = new BondTradedProductUpdqPK();
    int i = pkValueString.indexOf(',');
    pk.traded_product_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.valid_for_biz_date = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(traded_product_id) + "," + valid_for_biz_date;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BondTradedProductUpdqPK) )
      return false;
    BondTradedProductUpdqPK o = (BondTradedProductUpdqPK) other;
    if ( traded_product_id != o.traded_product_id )
      return false;
    if ( valid_for_biz_date == null ) {
      if ( o.valid_for_biz_date != null )
        return false;
    } else if ( !valid_for_biz_date.equals( o.valid_for_biz_date ) ) {
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
        + (valid_for_biz_date!=null? valid_for_biz_date.hashCode(): 0) 
    ;
  }

}

@
