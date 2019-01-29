head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfSubAssetPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>MfSubAsset</b>データベーステーブルで一意である1つのレコードをあらわす<b>MfSubAsset</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MfSubAsset</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MfSubAssetRow 
 */
public class MfSubAssetPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mf_sub_asset";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MfSubAssetRow. 
   */
  public RowType getRowType() {
    return MfSubAssetRow.TYPE;
  }

  /**
   * <em>asset_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long asset_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public MfSubAssetPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_assetId <em>asset_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public MfSubAssetPK( long p_assetId ) {
      asset_id = p_assetId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MfSubAssetPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MfSubAssetPK pk = new MfSubAssetPK();
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
    if ( other == null || !( other instanceof MfSubAssetPK) )
      return false;
    MfSubAssetPK o = (MfSubAssetPK) other;
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
