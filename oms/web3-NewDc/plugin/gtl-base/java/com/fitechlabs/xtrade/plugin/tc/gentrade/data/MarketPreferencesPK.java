head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.35.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MarketPreferencesPK.java;


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
 * <b>MarketPreferences</b>データベーステーブルで一意である1つのレコードをあらわす<b>MarketPreferences</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MarketPreferences</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MarketPreferencesRow 
 */
public class MarketPreferencesPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "market_preferences";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MarketPreferencesRow. 
   */
  public RowType getRowType() {
    return MarketPreferencesRow.TYPE;
  }

  /**
   * <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long market_id;
  /**
   * <em>name</em>カラムの値をあらわす80桁以下のString値 
   */
  public String name;
  /**
   * <em>name_serial_no</em>カラムの値をあらわす6桁以下のint値 
   */
  public int name_serial_no;


  /** 
   * デフォルトコンストラクタ 
   */
  public MarketPreferencesPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_marketId <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_name <em>name</em>カラムの値をあらわす80桁以下のString値 
   * @@param p_nameSerialNo <em>name_serial_no</em>カラムの値をあらわす6桁以下のint値 
   */
  public MarketPreferencesPK( long p_marketId, String p_name, int p_nameSerialNo ) {
      market_id = p_marketId;
      name = p_name;
      name_serial_no = p_nameSerialNo;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MarketPreferencesPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MarketPreferencesPK pk = new MarketPreferencesPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.market_id = Long.valueOf(st.nextToken()).longValue();
    pk.name = st.nextToken();
    pk.name_serial_no = Integer.valueOf(st.nextToken()).intValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(market_id) + "," + name + "," + String.valueOf(name_serial_no);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MarketPreferencesPK) )
      return false;
    MarketPreferencesPK o = (MarketPreferencesPK) other;
    if ( market_id != o.market_id )
      return false;
    if ( name == null ) {
      if ( o.name != null )
        return false;
    } else if ( !name.equals( o.name ) ) {
        return false;
    }
    if ( name_serial_no != o.name_serial_no )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) market_id)
        + (name!=null? name.hashCode(): 0) 
        + ((int) name_serial_no)
    ;
  }

}

@
