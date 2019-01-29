head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityRangeBasedMapPK.java;


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
 * <b>AffinityRangeBasedMap</b>データベーステーブルで一意である1つのレコードをあらわす<b>AffinityRangeBasedMap</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AffinityRangeBasedMap</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AffinityRangeBasedMapRow 
 */
public class AffinityRangeBasedMapPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "affinity_range_based_map";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AffinityRangeBasedMapRow. 
   */
  public RowType getRowType() {
    return AffinityRangeBasedMapRow.TYPE;
  }

  /**
   * <em>key_start</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long key_start;
  /**
   * <em>key_end</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long key_end;
  /**
   * <em>range_order_no</em>カラムの値をあらわす6桁以下のint値 
   */
  public int range_order_no;
  /**
   * <em>server_type</em>カラムの値をあらわす1桁以下のint値 
   */
  public int server_type;
  /**
   * <em>ctx_name</em>カラムの値をあらわす100桁以下のString値 
   */
  public String ctx_name;


  /** 
   * デフォルトコンストラクタ 
   */
  public AffinityRangeBasedMapPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_keyStart <em>key_start</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_keyEnd <em>key_end</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_rangeOrderNo <em>range_order_no</em>カラムの値をあらわす6桁以下のint値 
   * @@param p_serverType <em>server_type</em>カラムの値をあらわす1桁以下のint値 
   * @@param p_ctxName <em>ctx_name</em>カラムの値をあらわす100桁以下のString値 
   */
  public AffinityRangeBasedMapPK( long p_keyStart, long p_keyEnd, int p_rangeOrderNo, int p_serverType, String p_ctxName ) {
      key_start = p_keyStart;
      key_end = p_keyEnd;
      range_order_no = p_rangeOrderNo;
      server_type = p_serverType;
      ctx_name = p_ctxName;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AffinityRangeBasedMapPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AffinityRangeBasedMapPK pk = new AffinityRangeBasedMapPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.key_start = Long.valueOf(st.nextToken()).longValue();
    pk.key_end = Long.valueOf(st.nextToken()).longValue();
    pk.range_order_no = Integer.valueOf(st.nextToken()).intValue();
    pk.server_type = Integer.valueOf(st.nextToken()).intValue();
    pk.ctx_name = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(key_start) + "," + String.valueOf(key_end) + "," + String.valueOf(range_order_no) + "," + String.valueOf(server_type) + "," + ctx_name;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AffinityRangeBasedMapPK) )
      return false;
    AffinityRangeBasedMapPK o = (AffinityRangeBasedMapPK) other;
    if ( key_start != o.key_start )
      return false;
    if ( key_end != o.key_end )
      return false;
    if ( range_order_no != o.range_order_no )
      return false;
    if ( server_type != o.server_type )
      return false;
    if ( ctx_name == null ) {
      if ( o.ctx_name != null )
        return false;
    } else if ( !ctx_name.equals( o.ctx_name ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) key_start)
        + ((int) key_end)
        + ((int) range_order_no)
        + ((int) server_type)
        + (ctx_name!=null? ctx_name.hashCode(): 0) 
    ;
  }

}

@
