head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityKeyBasedMapParams.java;


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
 * affinity_key_based_mapテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AffinityKeyBasedMapRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AffinityKeyBasedMapParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AffinityKeyBasedMapParams}が{@@link AffinityKeyBasedMapRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AffinityKeyBasedMapPK 
 * @@see AffinityKeyBasedMapRow 
 */
public class AffinityKeyBasedMapParams extends Params implements AffinityKeyBasedMapRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "affinity_key_based_map";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AffinityKeyBasedMapRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AffinityKeyBasedMapRow.TYPE;
  }


  /** 
   * <em>app_id</em>カラムの値 
   */
  public  String  app_id;

  /** 
   * <em>db_id</em>カラムの値 
   */
  public  String  db_id;

  /** 
   * <em>key</em>カラムの値 
   */
  public  String  key;

  private boolean key_is_set = false;
  private boolean key_is_modified = false;
  private boolean app_id_is_set = false;
  private boolean app_id_is_modified = false;
  private boolean db_id_is_set = false;
  private boolean db_id_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "app_id=" + app_id
      + "," + "db_id=" + db_id
      + "," + "key=" +key
      + "}";
  }


  /** 
   * 値が未設定のAffinityKeyBasedMapParamsオブジェクトを作成します。 
   */
  public AffinityKeyBasedMapParams() {
    app_id_is_modified = true;
    db_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAffinityKeyBasedMapRowオブジェクトの値を利用してAffinityKeyBasedMapParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAffinityKeyBasedMapRowオブジェクト 
   */
  public AffinityKeyBasedMapParams( AffinityKeyBasedMapRow p_row )
  {
    app_id = p_row.getAppId();
    app_id_is_set = p_row.getAppIdIsSet();
    app_id_is_modified = p_row.getAppIdIsModified();
    db_id = p_row.getDbId();
    db_id_is_set = p_row.getDbIdIsSet();
    db_id_is_modified = p_row.getDbIdIsModified();
    key = p_row.getKey();
    key_is_set = p_row.getKeyIsSet();
    key_is_modified = p_row.getKeyIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      key_is_set = true;
      key_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AffinityKeyBasedMapRow ) )
       return false;
    return fieldsEqual( (AffinityKeyBasedMapRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAffinityKeyBasedMapRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AffinityKeyBasedMapRow row )
  {
    if ( key == null ) {
      if ( row.getKey() != null )
        return false;
    } else if ( !key.equals( row.getKey() ) ) {
        return false;
    }
    if ( app_id == null ) {
      if ( row.getAppId() != null )
        return false;
    } else if ( !app_id.equals( row.getAppId() ) ) {
        return false;
    }
    if ( db_id == null ) {
      if ( row.getDbId() != null )
        return false;
    } else if ( !db_id.equals( row.getDbId() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (key!=null? key.hashCode(): 0) 
        + (app_id!=null? app_id.hashCode(): 0) 
        + (db_id!=null? db_id.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !key_is_set )
			throw new IllegalArgumentException("non-nullable field 'key' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("key",key);
		map.put("app_id",app_id);
		map.put("db_id",db_id);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( key_is_modified )
			map.put("key",key);
		if (map.size() == 0) {
			if ( key_is_set )
				map.put("key",key);
		}
		return map;
	}


  /** 
   * <em>key</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getKey()
  {
    return key;
  }


  /** 
   * <em>key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getKeyIsSet() {
    return key_is_set;
  }


  /** 
   * <em>key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getKeyIsModified() {
    return key_is_modified;
  }


  /** 
   * <em>app_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAppId()
  {
    return app_id;
  }


  /** 
   * <em>app_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppIdIsSet() {
    return app_id_is_set;
  }


  /** 
   * <em>app_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppIdIsModified() {
    return app_id_is_modified;
  }


  /** 
   * <em>db_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDbId()
  {
    return db_id;
  }


  /** 
   * <em>db_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDbIdIsSet() {
    return db_id_is_set;
  }


  /** 
   * <em>db_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDbIdIsModified() {
    return db_id_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AffinityKeyBasedMapPK(app_id, db_id);
  }


  /** 
   * <em>key</em>カラムの値を設定します。 
   *
   * @@param p_key <em>key</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setKey( String p_key )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    key = p_key;
    key_is_set = true;
    key_is_modified = true;
  }


  /** 
   * <em>app_id</em>カラムの値を設定します。 
   *
   * @@param p_appId <em>app_id</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setAppId( String p_appId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    app_id = p_appId;
    app_id_is_set = true;
    app_id_is_modified = true;
  }


  /** 
   * <em>db_id</em>カラムの値を設定します。 
   *
   * @@param p_dbId <em>db_id</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setDbId( String p_dbId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    db_id = p_dbId;
    db_id_is_set = true;
    db_id_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("app_id") ) {
                    return this.app_id;
                }
                break;
            case 'd':
                if ( name.equals("db_id") ) {
                    return this.db_id;
                }
                break;
            case 'k':
                if ( name.equals("key") ) {
                    return this.key;
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
            case 'a':
                if ( name.equals("app_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'app_id' must be String: '"+value+"' is not." );
                    this.app_id = (String) value;
                    if (this.app_id_is_set)
                        this.app_id_is_modified = true;
                    this.app_id_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("db_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'db_id' must be String: '"+value+"' is not." );
                    this.db_id = (String) value;
                    if (this.db_id_is_set)
                        this.db_id_is_modified = true;
                    this.db_id_is_set = true;
                    return;
                }
                break;
            case 'k':
                if ( name.equals("key") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'key' must be String: '"+value+"' is not." );
                    this.key = (String) value;
                    if (this.key_is_set)
                        this.key_is_modified = true;
                    this.key_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
