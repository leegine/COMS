head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpAssetHistoryPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>TpAssetHistory</b>データベーステーブルで一意である1つのレコードをあらわす<b>TpAssetHistory</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>TpAssetHistory</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpAssetHistoryRow 
 */
public class TpAssetHistoryPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "tp_asset_history";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: TpAssetHistoryRow. 
   */
  public RowType getRowType() {
    return TpAssetHistoryRow.TYPE;
  }

  /**
   * <em>tp_asset_history_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long tp_asset_history_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public TpAssetHistoryPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_tpAssetHistoryId <em>tp_asset_history_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public TpAssetHistoryPK( long p_tpAssetHistoryId ) {
      tp_asset_history_id = p_tpAssetHistoryId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static TpAssetHistoryPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TpAssetHistoryPK pk = new TpAssetHistoryPK();
    pk.tp_asset_history_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(tp_asset_history_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TpAssetHistoryPK) )
      return false;
    TpAssetHistoryPK o = (TpAssetHistoryPK) other;
    if ( tp_asset_history_id != o.tp_asset_history_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) tp_asset_history_id)
    ;
  }

}

@
