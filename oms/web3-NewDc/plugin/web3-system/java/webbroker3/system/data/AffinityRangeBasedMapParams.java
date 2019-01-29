head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.22.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityRangeBasedMapParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.system.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * affinity_range_based_mapテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AffinityRangeBasedMapRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AffinityRangeBasedMapParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AffinityRangeBasedMapParams}が{@@link AffinityRangeBasedMapRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AffinityRangeBasedMapPK 
 * @@see AffinityRangeBasedMapRow 
 */
public class AffinityRangeBasedMapParams extends Params implements AffinityRangeBasedMapRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "affinity_range_based_map";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AffinityRangeBasedMapRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AffinityRangeBasedMapRow.TYPE;
  }


  /** 
   * <em>key_start</em>カラムの値 
   */
  public  long  key_start;

  /** 
   * <em>key_end</em>カラムの値 
   */
  public  long  key_end;

  /** 
   * <em>range_order_no</em>カラムの値 
   */
  public  int  range_order_no;

  /** 
   * <em>server_type</em>カラムの値 
   */
  public  int  server_type;

  /** 
   * <em>ctx_name</em>カラムの値 
   */
  public  String  ctx_name;

  /** 
   * <em>server_id</em>カラムの値 
   */
  public  String  server_id;

  private boolean key_start_is_set = false;
  private boolean key_start_is_modified = false;
  private boolean key_end_is_set = false;
  private boolean key_end_is_modified = false;
  private boolean range_order_no_is_set = false;
  private boolean range_order_no_is_modified = false;
  private boolean server_type_is_set = false;
  private boolean server_type_is_modified = false;
  private boolean server_id_is_set = false;
  private boolean server_id_is_modified = false;
  private boolean ctx_name_is_set = false;
  private boolean ctx_name_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "key_start=" + key_start
      + "," + "key_end=" + key_end
      + "," + "range_order_no=" + range_order_no
      + "," + "server_type=" + server_type
      + "," + "ctx_name=" + ctx_name
      + "," + "server_id=" +server_id
      + "}";
  }


  /** 
   * 値が未設定のAffinityRangeBasedMapParamsオブジェクトを作成します。 
   */
  public AffinityRangeBasedMapParams() {
    key_start_is_modified = true;
    key_end_is_modified = true;
    range_order_no_is_modified = true;
    server_type_is_modified = true;
    ctx_name_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAffinityRangeBasedMapRowオブジェクトの値を利用してAffinityRangeBasedMapParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAffinityRangeBasedMapRowオブジェクト 
   */
  public AffinityRangeBasedMapParams( AffinityRangeBasedMapRow p_row )
  {
    key_start = p_row.getKeyStart();
    key_start_is_set = p_row.getKeyStartIsSet();
    key_start_is_modified = p_row.getKeyStartIsModified();
    key_end = p_row.getKeyEnd();
    key_end_is_set = p_row.getKeyEndIsSet();
    key_end_is_modified = p_row.getKeyEndIsModified();
    range_order_no = p_row.getRangeOrderNo();
    range_order_no_is_set = p_row.getRangeOrderNoIsSet();
    range_order_no_is_modified = p_row.getRangeOrderNoIsModified();
    server_type = p_row.getServerType();
    server_type_is_set = p_row.getServerTypeIsSet();
    server_type_is_modified = p_row.getServerTypeIsModified();
    ctx_name = p_row.getCtxName();
    ctx_name_is_set = p_row.getCtxNameIsSet();
    ctx_name_is_modified = p_row.getCtxNameIsModified();
    server_id = p_row.getServerId();
    server_id_is_set = p_row.getServerIdIsSet();
    server_id_is_modified = p_row.getServerIdIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      server_id_is_set = true;
      server_id_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AffinityRangeBasedMapRow ) )
       return false;
    return fieldsEqual( (AffinityRangeBasedMapRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAffinityRangeBasedMapRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AffinityRangeBasedMapRow row )
  {
    if ( key_start != row.getKeyStart() )
      return false;
    if ( key_end != row.getKeyEnd() )
      return false;
    if ( range_order_no != row.getRangeOrderNo() )
      return false;
    if ( server_type != row.getServerType() )
      return false;
    if ( server_id == null ) {
      if ( row.getServerId() != null )
        return false;
    } else if ( !server_id.equals( row.getServerId() ) ) {
        return false;
    }
    if ( ctx_name == null ) {
      if ( row.getCtxName() != null )
        return false;
    } else if ( !ctx_name.equals( row.getCtxName() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) key_start)
        + ((int) key_end)
        + ((int) range_order_no)
        + ((int) server_type)
        + (server_id!=null? server_id.hashCode(): 0) 
        + (ctx_name!=null? ctx_name.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !server_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'server_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("key_start",new Long(key_start));
		map.put("key_end",new Long(key_end));
		map.put("range_order_no",new Integer(range_order_no));
		map.put("server_type",new Integer(server_type));
		map.put("server_id",server_id);
		map.put("ctx_name",ctx_name);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( server_id_is_modified )
			map.put("server_id",server_id);
		if (map.size() == 0) {
			if ( server_id_is_set )
				map.put("server_id",server_id);
		}
		return map;
	}


  /** 
   * <em>key_start</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getKeyStart()
  {
    return key_start;
  }


  /** 
   * <em>key_start</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getKeyStartIsSet() {
    return key_start_is_set;
  }


  /** 
   * <em>key_start</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getKeyStartIsModified() {
    return key_start_is_modified;
  }


  /** 
   * <em>key_end</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getKeyEnd()
  {
    return key_end;
  }


  /** 
   * <em>key_end</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getKeyEndIsSet() {
    return key_end_is_set;
  }


  /** 
   * <em>key_end</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getKeyEndIsModified() {
    return key_end_is_modified;
  }


  /** 
   * <em>range_order_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getRangeOrderNo()
  {
    return range_order_no;
  }


  /** 
   * <em>range_order_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRangeOrderNoIsSet() {
    return range_order_no_is_set;
  }


  /** 
   * <em>range_order_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRangeOrderNoIsModified() {
    return range_order_no_is_modified;
  }


  /** 
   * <em>server_type</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getServerType()
  {
    return server_type;
  }


  /** 
   * <em>server_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServerTypeIsSet() {
    return server_type_is_set;
  }


  /** 
   * <em>server_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServerTypeIsModified() {
    return server_type_is_modified;
  }


  /** 
   * <em>server_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getServerId()
  {
    return server_id;
  }


  /** 
   * <em>server_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServerIdIsSet() {
    return server_id_is_set;
  }


  /** 
   * <em>server_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServerIdIsModified() {
    return server_id_is_modified;
  }


  /** 
   * <em>ctx_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCtxName()
  {
    return ctx_name;
  }


  /** 
   * <em>ctx_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCtxNameIsSet() {
    return ctx_name_is_set;
  }


  /** 
   * <em>ctx_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCtxNameIsModified() {
    return ctx_name_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AffinityRangeBasedMapPK(key_start, key_end, range_order_no, server_type, ctx_name);
  }


  /** 
   * <em>key_start</em>カラムの値を設定します。 
   *
   * @@param p_keyStart <em>key_start</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setKeyStart( long p_keyStart )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    key_start = p_keyStart;
    key_start_is_set = true;
    key_start_is_modified = true;
  }


  /** 
   * <em>key_end</em>カラムの値を設定します。 
   *
   * @@param p_keyEnd <em>key_end</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setKeyEnd( long p_keyEnd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    key_end = p_keyEnd;
    key_end_is_set = true;
    key_end_is_modified = true;
  }


  /** 
   * <em>range_order_no</em>カラムの値を設定します。 
   *
   * @@param p_rangeOrderNo <em>range_order_no</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setRangeOrderNo( int p_rangeOrderNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    range_order_no = p_rangeOrderNo;
    range_order_no_is_set = true;
    range_order_no_is_modified = true;
  }


  /** 
   * <em>server_type</em>カラムの値を設定します。 
   *
   * @@param p_serverType <em>server_type</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setServerType( int p_serverType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    server_type = p_serverType;
    server_type_is_set = true;
    server_type_is_modified = true;
  }


  /** 
   * <em>server_id</em>カラムの値を設定します。 
   *
   * @@param p_serverId <em>server_id</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setServerId( String p_serverId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    server_id = p_serverId;
    server_id_is_set = true;
    server_id_is_modified = true;
  }


  /** 
   * <em>ctx_name</em>カラムの値を設定します。 
   *
   * @@param p_ctxName <em>ctx_name</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setCtxName( String p_ctxName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ctx_name = p_ctxName;
    ctx_name_is_set = true;
    ctx_name_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("ctx_name") ) {
                    return this.ctx_name;
                }
                break;
            case 'k':
                if ( name.equals("key_start") ) {
                    return new Long( key_start );
                }
                else if ( name.equals("key_end") ) {
                    return new Long( key_end );
                }
                break;
            case 'r':
                if ( name.equals("range_order_no") ) {
                    return new Integer( range_order_no );
                }
                break;
            case 's':
                if ( name.equals("server_type") ) {
                    return new Integer( server_type );
                }
                else if ( name.equals("server_id") ) {
                    return this.server_id;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("ctx_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ctx_name' must be String: '"+value+"' is not." );
                    this.ctx_name = (String) value;
                    if (this.ctx_name_is_set)
                        this.ctx_name_is_modified = true;
                    this.ctx_name_is_set = true;
                    return;
                }
                break;
            case 'k':
                if ( name.equals("key_start") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'key_start' must be Long: '"+value+"' is not." );
                    this.key_start = ((Long) value).longValue();
                    if (this.key_start_is_set)
                        this.key_start_is_modified = true;
                    this.key_start_is_set = true;
                    return;
                }
                else if ( name.equals("key_end") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'key_end' must be Long: '"+value+"' is not." );
                    this.key_end = ((Long) value).longValue();
                    if (this.key_end_is_set)
                        this.key_end_is_modified = true;
                    this.key_end_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("range_order_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'range_order_no' must be Integer: '"+value+"' is not." );
                    this.range_order_no = ((Integer) value).intValue();
                    if (this.range_order_no_is_set)
                        this.range_order_no_is_modified = true;
                    this.range_order_no_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("server_type") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'server_type' must be Integer: '"+value+"' is not." );
                    this.server_type = ((Integer) value).intValue();
                    if (this.server_type_is_set)
                        this.server_type_is_modified = true;
                    this.server_type_is_set = true;
                    return;
                }
                else if ( name.equals("server_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'server_id' must be String: '"+value+"' is not." );
                    this.server_id = (String) value;
                    if (this.server_id_is_set)
                        this.server_id_is_modified = true;
                    this.server_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
