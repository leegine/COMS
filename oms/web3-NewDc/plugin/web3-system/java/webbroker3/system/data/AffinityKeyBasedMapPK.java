head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityKeyBasedMapPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.system.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>AffinityKeyBasedMap</b>データベーステーブルで一意である1つのレコードをあらわす<b>AffinityKeyBasedMap</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AffinityKeyBasedMap</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AffinityKeyBasedMapRow 
 */
public class AffinityKeyBasedMapPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "affinity_key_based_map";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AffinityKeyBasedMapRow. 
   */
  public RowType getRowType() {
    return AffinityKeyBasedMapRow.TYPE;
  }

  /**
   * <em>app_id</em>カラムの値をあらわす18桁以下のString値 
   */
  public String app_id;
  /**
   * <em>db_id</em>カラムの値をあらわす18桁以下のString値 
   */
  public String db_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public AffinityKeyBasedMapPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_appId <em>app_id</em>カラムの値をあらわす18桁以下のString値 
   * @@param p_dbId <em>db_id</em>カラムの値をあらわす18桁以下のString値 
   */
  public AffinityKeyBasedMapPK( String p_appId, String p_dbId ) {
      app_id = p_appId;
      db_id = p_dbId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AffinityKeyBasedMapPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AffinityKeyBasedMapPK pk = new AffinityKeyBasedMapPK();
    int i = pkValueString.indexOf(',');
    pk.app_id = pkValueString.substring(0,i);
    pk.db_id = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = app_id + "," + db_id;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AffinityKeyBasedMapPK) )
      return false;
    AffinityKeyBasedMapPK o = (AffinityKeyBasedMapPK) other;
    if ( app_id == null ) {
      if ( o.app_id != null )
        return false;
    } else if ( !app_id.equals( o.app_id ) ) {
        return false;
    }
    if ( db_id == null ) {
      if ( o.db_id != null )
        return false;
    } else if ( !db_id.equals( o.db_id ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (app_id!=null? app_id.hashCode(): 0) 
        + (db_id!=null? db_id.hashCode(): 0) 
    ;
  }

}

@
