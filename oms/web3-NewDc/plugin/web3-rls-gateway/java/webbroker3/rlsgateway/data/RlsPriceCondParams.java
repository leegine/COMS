head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsPriceCondParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * rls_price_condテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link RlsPriceCondRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link RlsPriceCondParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link RlsPriceCondParams}が{@@link RlsPriceCondRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsPriceCondPK 
 * @@see RlsPriceCondRow 
 */
public class RlsPriceCondParams extends Params implements RlsPriceCondRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "rls_price_cond";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = RlsPriceCondRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return RlsPriceCondRow.TYPE;
  }


  /** 
   * <em>cond_ord_id</em>カラムの値 
   */
  public  long  cond_ord_id;

  /** 
   * <em>price</em>カラムの値 
   */
  public  long  price;

  /** 
   * <em>direction</em>カラムの値 
   */
  public  long  direction;

  /** 
   * <em>prod_id</em>カラムの値 
   */
  public  long  prod_id;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  long  market_id;

  private boolean cond_ord_id_is_set = false;
  private boolean cond_ord_id_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean direction_is_set = false;
  private boolean direction_is_modified = false;
  private boolean prod_id_is_set = false;
  private boolean prod_id_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "cond_ord_id=" + cond_ord_id
      + "," + "price=" +price
      + "," + "direction=" +direction
      + "," + "prod_id=" +prod_id
      + "," + "market_id=" +market_id
      + "}";
  }


  /** 
   * 値が未設定のRlsPriceCondParamsオブジェクトを作成します。 
   */
  public RlsPriceCondParams() {
    cond_ord_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のRlsPriceCondRowオブジェクトの値を利用してRlsPriceCondParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するRlsPriceCondRowオブジェクト 
   */
  public RlsPriceCondParams( RlsPriceCondRow p_row )
  {
    cond_ord_id = p_row.getCondOrdId();
    cond_ord_id_is_set = p_row.getCondOrdIdIsSet();
    cond_ord_id_is_modified = p_row.getCondOrdIdIsModified();
    price = p_row.getPrice();
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    direction = p_row.getDirection();
    direction_is_set = p_row.getDirectionIsSet();
    direction_is_modified = p_row.getDirectionIsModified();
    prod_id = p_row.getProdId();
    prod_id_is_set = p_row.getProdIdIsSet();
    prod_id_is_modified = p_row.getProdIdIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      price_is_set = true;
      price_is_modified = true;
      direction_is_set = true;
      direction_is_modified = true;
      prod_id_is_set = true;
      prod_id_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof RlsPriceCondRow ) )
       return false;
    return fieldsEqual( (RlsPriceCondRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のRlsPriceCondRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( RlsPriceCondRow row )
  {
    if ( cond_ord_id != row.getCondOrdId() )
      return false;
    if ( price != row.getPrice() )
      return false;
    if ( direction != row.getDirection() )
      return false;
    if ( prod_id != row.getProdId() )
      return false;
    if ( market_id != row.getMarketId() )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) cond_ord_id)
        + ((int) price)
        + ((int) direction)
        + ((int) prod_id)
        + ((int) market_id)
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !price_is_set )
			throw new IllegalArgumentException("non-nullable field 'price' must be set before inserting.");
		if ( !direction_is_set )
			throw new IllegalArgumentException("non-nullable field 'direction' must be set before inserting.");
		if ( !prod_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'prod_id' must be set before inserting.");
		if ( !market_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("cond_ord_id",new Long(cond_ord_id));
		map.put("price",new Long(price));
		map.put("direction",new Long(direction));
		map.put("prod_id",new Long(prod_id));
		map.put("market_id",new Long(market_id));
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( price_is_modified )
			map.put("price",new Long(price));
		if ( direction_is_modified )
			map.put("direction",new Long(direction));
		if ( prod_id_is_modified )
			map.put("prod_id",new Long(prod_id));
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if (map.size() == 0) {
			if ( price_is_set )
				map.put("price",new Long(price));
			if ( direction_is_set )
				map.put("direction",new Long(direction));
			if ( prod_id_is_set )
				map.put("prod_id",new Long(prod_id));
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
		}
		return map;
	}


  /** 
   * <em>cond_ord_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getCondOrdId()
  {
    return cond_ord_id;
  }


  /** 
   * <em>cond_ord_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCondOrdIdIsSet() {
    return cond_ord_id_is_set;
  }


  /** 
   * <em>cond_ord_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCondOrdIdIsModified() {
    return cond_ord_id_is_modified;
  }


  /** 
   * <em>price</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getPrice()
  {
    return price;
  }


  /** 
   * <em>price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceIsSet() {
    return price_is_set;
  }


  /** 
   * <em>price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceIsModified() {
    return price_is_modified;
  }


  /** 
   * <em>direction</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDirection()
  {
    return direction;
  }


  /** 
   * <em>direction</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectionIsSet() {
    return direction_is_set;
  }


  /** 
   * <em>direction</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectionIsModified() {
    return direction_is_modified;
  }


  /** 
   * <em>prod_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getProdId()
  {
    return prod_id;
  }


  /** 
   * <em>prod_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProdIdIsSet() {
    return prod_id_is_set;
  }


  /** 
   * <em>prod_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProdIdIsModified() {
    return prod_id_is_modified;
  }


  /** 
   * <em>market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarketId()
  {
    return market_id;
  }


  /** 
   * <em>market_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsSet() {
    return market_id_is_set;
  }


  /** 
   * <em>market_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsModified() {
    return market_id_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new RlsPriceCondPK(cond_ord_id);
  }


  /** 
   * <em>cond_ord_id</em>カラムの値を設定します。 
   *
   * @@param p_condOrdId <em>cond_ord_id</em>カラムの値をあらわすlong値
   */
  public final void setCondOrdId( long p_condOrdId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cond_ord_id = p_condOrdId;
    cond_ord_id_is_set = true;
    cond_ord_id_is_modified = true;
  }


  /** 
   * <em>price</em>カラムの値を設定します。 
   *
   * @@param p_price <em>price</em>カラムの値をあらわすlong値
   */
  public final void setPrice( long p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price = p_price;
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * <em>direction</em>カラムの値を設定します。 
   *
   * @@param p_direction <em>direction</em>カラムの値をあらわすlong値
   */
  public final void setDirection( long p_direction )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    direction = p_direction;
    direction_is_set = true;
    direction_is_modified = true;
  }


  /** 
   * <em>prod_id</em>カラムの値を設定します。 
   *
   * @@param p_prodId <em>prod_id</em>カラムの値をあらわすlong値
   */
  public final void setProdId( long p_prodId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    prod_id = p_prodId;
    prod_id_is_set = true;
    prod_id_is_modified = true;
  }


  /** 
   * <em>market_id</em>カラムの値を設定します。 
   *
   * @@param p_marketId <em>market_id</em>カラムの値をあらわすlong値
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("cond_ord_id") ) {
                    return new Long( cond_ord_id );
                }
                break;
            case 'd':
                if ( name.equals("direction") ) {
                    return new Long( direction );
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                break;
            case 'p':
                if ( name.equals("price") ) {
                    return new Long( price );
                }
                else if ( name.equals("prod_id") ) {
                    return new Long( prod_id );
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
                if ( name.equals("cond_ord_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'cond_ord_id' must be Long: '"+value+"' is not." );
                    this.cond_ord_id = ((Long) value).longValue();
                    if (this.cond_ord_id_is_set)
                        this.cond_ord_id_is_modified = true;
                    this.cond_ord_id_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("direction") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'direction' must be Long: '"+value+"' is not." );
                    this.direction = ((Long) value).longValue();
                    if (this.direction_is_set)
                        this.direction_is_modified = true;
                    this.direction_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = ((Long) value).longValue();
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("price") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'price' must be Long: '"+value+"' is not." );
                    this.price = ((Long) value).longValue();
                    if (this.price_is_set)
                        this.price_is_modified = true;
                    this.price_is_set = true;
                    return;
                }
                else if ( name.equals("prod_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'prod_id' must be Long: '"+value+"' is not." );
                    this.prod_id = ((Long) value).longValue();
                    if (this.prod_id_is_set)
                        this.prod_id_is_modified = true;
                    this.prod_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
