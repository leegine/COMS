head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.28.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	RichPushHistoryTopParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * rich_push_history_topテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link RichPushHistoryTopRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link RichPushHistoryTopParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link RichPushHistoryTopParams}が{@@link RichPushHistoryTopRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RichPushHistoryTopPK 
 * @@see RichPushHistoryTopRow 
 */
public class RichPushHistoryTopParams extends Params implements RichPushHistoryTopRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "rich_push_history_top";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = RichPushHistoryTopRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return RichPushHistoryTopRow.TYPE;
  }


  /** 
   * <em>serlnum</em>カラムの値 
   */
  public  long  serlnum;

  /** 
   * <em>tpk</em>カラムの値 
   */
  public  String  tpk;

  /** 
   * <em>type</em>カラムの値 
   */
  public  String  type;

  private boolean tpk_is_set = false;
  private boolean tpk_is_modified = false;
  private boolean type_is_set = false;
  private boolean type_is_modified = false;
  private boolean serlnum_is_set = false;
  private boolean serlnum_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "serlnum=" + serlnum
      + "," + "tpk=" +tpk
      + "," + "type=" +type
      + "}";
  }


  /** 
   * 値が未設定のRichPushHistoryTopParamsオブジェクトを作成します。 
   */
  public RichPushHistoryTopParams() {
    serlnum_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のRichPushHistoryTopRowオブジェクトの値を利用してRichPushHistoryTopParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するRichPushHistoryTopRowオブジェクト 
   */
  public RichPushHistoryTopParams( RichPushHistoryTopRow p_row )
  {
    serlnum = p_row.getSerlnum();
    serlnum_is_set = p_row.getSerlnumIsSet();
    serlnum_is_modified = p_row.getSerlnumIsModified();
    tpk = p_row.getTpk();
    tpk_is_set = p_row.getTpkIsSet();
    tpk_is_modified = p_row.getTpkIsModified();
    type = p_row.getType();
    type_is_set = p_row.getTypeIsSet();
    type_is_modified = p_row.getTypeIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      tpk_is_set = true;
      tpk_is_modified = true;
      type_is_set = true;
      type_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof RichPushHistoryTopRow ) )
       return false;
    return fieldsEqual( (RichPushHistoryTopRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のRichPushHistoryTopRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( RichPushHistoryTopRow row )
  {
    if ( tpk == null ) {
      if ( row.getTpk() != null )
        return false;
    } else if ( !tpk.equals( row.getTpk() ) ) {
        return false;
    }
    if ( type == null ) {
      if ( row.getType() != null )
        return false;
    } else if ( !type.equals( row.getType() ) ) {
        return false;
    }
    if ( serlnum != row.getSerlnum() )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (tpk!=null? tpk.hashCode(): 0) 
        + (type!=null? type.hashCode(): 0) 
        + ((int) serlnum)
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !tpk_is_set )
			throw new IllegalArgumentException("non-nullable field 'tpk' must be set before inserting.");
		if ( !type_is_set )
			throw new IllegalArgumentException("non-nullable field 'type' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("tpk",tpk);
		map.put("type",type);
		map.put("serlnum",new Long(serlnum));
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( tpk_is_modified )
			map.put("tpk",tpk);
		if ( type_is_modified )
			map.put("type",type);
		if (map.size() == 0) {
			if ( tpk_is_set )
				map.put("tpk",tpk);
			if ( type_is_set )
				map.put("type",type);
		}
		return map;
	}


  /** 
   * <em>tpk</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTpk()
  {
    return tpk;
  }


  /** 
   * <em>tpk</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTpkIsSet() {
    return tpk_is_set;
  }


  /** 
   * <em>tpk</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTpkIsModified() {
    return tpk_is_modified;
  }


  /** 
   * <em>type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getType()
  {
    return type;
  }


  /** 
   * <em>type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTypeIsSet() {
    return type_is_set;
  }


  /** 
   * <em>type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTypeIsModified() {
    return type_is_modified;
  }


  /** 
   * <em>serlnum</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSerlnum()
  {
    return serlnum;
  }


  /** 
   * <em>serlnum</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerlnumIsSet() {
    return serlnum_is_set;
  }


  /** 
   * <em>serlnum</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerlnumIsModified() {
    return serlnum_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new RichPushHistoryTopPK(serlnum);
  }


  /** 
   * <em>tpk</em>カラムの値を設定します。 
   *
   * @@param p_tpk <em>tpk</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setTpk( String p_tpk )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tpk = p_tpk;
    tpk_is_set = true;
    tpk_is_modified = true;
  }


  /** 
   * <em>type</em>カラムの値を設定します。 
   *
   * @@param p_type <em>type</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setType( String p_type )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    type = p_type;
    type_is_set = true;
    type_is_modified = true;
  }


  /** 
   * <em>serlnum</em>カラムの値を設定します。 
   *
   * @@param p_serlnum <em>serlnum</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setSerlnum( long p_serlnum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    serlnum = p_serlnum;
    serlnum_is_set = true;
    serlnum_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 's':
                if ( name.equals("serlnum") ) {
                    return new Long( serlnum );
                }
                break;
            case 't':
                if ( name.equals("tpk") ) {
                    return this.tpk;
                }
                else if ( name.equals("type") ) {
                    return this.type;
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
            case 's':
                if ( name.equals("serlnum") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'serlnum' must be Long: '"+value+"' is not." );
                    this.serlnum = ((Long) value).longValue();
                    if (this.serlnum_is_set)
                        this.serlnum_is_modified = true;
                    this.serlnum_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tpk") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tpk' must be String: '"+value+"' is not." );
                    this.tpk = (String) value;
                    if (this.tpk_is_set)
                        this.tpk_is_modified = true;
                    this.tpk_is_set = true;
                    return;
                }
                else if ( name.equals("type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'type' must be String: '"+value+"' is not." );
                    this.type = (String) value;
                    if (this.type_is_set)
                        this.type_is_modified = true;
                    this.type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
