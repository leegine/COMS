head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.12.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MfTradedProductUpdqPK.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>MfTradedProductUpdq</b>データベーステーブルで一意である1つのレコードをあらわす<b>MfTradedProductUpdq</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MfTradedProductUpdq</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MfTradedProductUpdqRow 
 */
public class MfTradedProductUpdqPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mf_traded_product_updq";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MfTradedProductUpdqRow. 
   */
  public RowType getRowType() {
    return MfTradedProductUpdqRow.TYPE;
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
  public MfTradedProductUpdqPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_tradedProductId <em>traded_product_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_validForBizDate <em>valid_for_biz_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public MfTradedProductUpdqPK( long p_tradedProductId, String p_validForBizDate ) {
      traded_product_id = p_tradedProductId;
      valid_for_biz_date = p_validForBizDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MfTradedProductUpdqPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MfTradedProductUpdqPK pk = new MfTradedProductUpdqPK();
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
    if ( other == null || !( other instanceof MfTradedProductUpdqPK) )
      return false;
    MfTradedProductUpdqPK o = (MfTradedProductUpdqPK) other;
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
