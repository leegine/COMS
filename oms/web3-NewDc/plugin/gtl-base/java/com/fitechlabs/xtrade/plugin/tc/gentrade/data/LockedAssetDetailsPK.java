head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.36.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	LockedAssetDetailsPK.java;


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
 * <b>LockedAssetDetails</b>データベーステーブルで一意である1つのレコードをあらわす<b>LockedAssetDetails</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>LockedAssetDetails</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see LockedAssetDetailsRow 
 */
public class LockedAssetDetailsPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "locked_asset_details";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: LockedAssetDetailsRow. 
   */
  public RowType getRowType() {
    return LockedAssetDetailsRow.TYPE;
  }

  /**
   * <em>asset_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long asset_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public LockedAssetDetailsPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_assetId <em>asset_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public LockedAssetDetailsPK( long p_assetId ) {
      asset_id = p_assetId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static LockedAssetDetailsPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    LockedAssetDetailsPK pk = new LockedAssetDetailsPK();
    pk.asset_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(asset_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof LockedAssetDetailsPK) )
      return false;
    LockedAssetDetailsPK o = (LockedAssetDetailsPK) other;
    if ( asset_id != o.asset_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) asset_id)
    ;
  }

}

@
