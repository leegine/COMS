head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsOmsOrderParams.java;


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
 * rls_oms_orderテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link RlsOmsOrderRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link RlsOmsOrderParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link RlsOmsOrderParams}が{@@link RlsOmsOrderRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsOmsOrderPK 
 * @@see RlsOmsOrderRow 
 */
public class RlsOmsOrderParams extends Params implements RlsOmsOrderRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "rls_oms_order";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = RlsOmsOrderRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return RlsOmsOrderRow.TYPE;
  }


  /** 
   * <em>ord_id</em>カラムの値 
   */
  public  long  ord_id;

  /** 
   * <em>ord_type</em>カラムの値 
   */
  public  long  ord_type;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>prod_id</em>カラムの値 
   */
  public  long  prod_id;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  long  market_id;

  /** 
   * <em>exec_type</em>カラムの値 
   */
  public  long  exec_type;

  /** 
   * <em>side</em>カラムの値 
   */
  public  long  side;

  /** 
   * <em>orig_qty</em>カラムの値 
   */
  public  long  orig_qty;

  /** 
   * <em>price</em>カラムの値 
   */
  public  Long  price;

  /** 
   * <em>cond_ord_id</em>カラムの値 
   */
  public  long  cond_ord_id;

  /** 
   * <em>tstamp</em>カラムの値 
   */
  public  java.sql.Timestamp  tstamp;

  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean ord_id_is_set = false;
  private boolean ord_id_is_modified = false;
  private boolean prod_id_is_set = false;
  private boolean prod_id_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean ord_type_is_set = false;
  private boolean ord_type_is_modified = false;
  private boolean exec_type_is_set = false;
  private boolean exec_type_is_modified = false;
  private boolean side_is_set = false;
  private boolean side_is_modified = false;
  private boolean orig_qty_is_set = false;
  private boolean orig_qty_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean cond_ord_id_is_set = false;
  private boolean cond_ord_id_is_modified = false;
  private boolean tstamp_is_set = false;
  private boolean tstamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "ord_id=" + ord_id
      + "," + "ord_type=" + ord_type
      + "," + "account_id=" +account_id
      + "," + "prod_id=" +prod_id
      + "," + "market_id=" +market_id
      + "," + "exec_type=" +exec_type
      + "," + "side=" +side
      + "," + "orig_qty=" +orig_qty
      + "," + "price=" +price
      + "," + "cond_ord_id=" +cond_ord_id
      + "," + "tstamp=" +tstamp
      + "}";
  }


  /** 
   * 値が未設定のRlsOmsOrderParamsオブジェクトを作成します。 
   */
  public RlsOmsOrderParams() {
    ord_id_is_modified = true;
    ord_type_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のRlsOmsOrderRowオブジェクトの値を利用してRlsOmsOrderParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するRlsOmsOrderRowオブジェクト 
   */
  public RlsOmsOrderParams( RlsOmsOrderRow p_row )
  {
    ord_id = p_row.getOrdId();
    ord_id_is_set = p_row.getOrdIdIsSet();
    ord_id_is_modified = p_row.getOrdIdIsModified();
    ord_type = p_row.getOrdType();
    ord_type_is_set = p_row.getOrdTypeIsSet();
    ord_type_is_modified = p_row.getOrdTypeIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    prod_id = p_row.getProdId();
    prod_id_is_set = p_row.getProdIdIsSet();
    prod_id_is_modified = p_row.getProdIdIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    exec_type = p_row.getExecType();
    exec_type_is_set = p_row.getExecTypeIsSet();
    exec_type_is_modified = p_row.getExecTypeIsModified();
    side = p_row.getSide();
    side_is_set = p_row.getSideIsSet();
    side_is_modified = p_row.getSideIsModified();
    orig_qty = p_row.getOrigQty();
    orig_qty_is_set = p_row.getOrigQtyIsSet();
    orig_qty_is_modified = p_row.getOrigQtyIsModified();
    if ( !p_row.getPriceIsNull() )
      price = new Long( p_row.getPrice() );
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    cond_ord_id = p_row.getCondOrdId();
    cond_ord_id_is_set = p_row.getCondOrdIdIsSet();
    cond_ord_id_is_modified = p_row.getCondOrdIdIsModified();
    tstamp = p_row.getTstamp();
    tstamp_is_set = p_row.getTstampIsSet();
    tstamp_is_modified = p_row.getTstampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      account_id_is_set = true;
      account_id_is_modified = true;
      prod_id_is_set = true;
      prod_id_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      exec_type_is_set = true;
      exec_type_is_modified = true;
      side_is_set = true;
      side_is_modified = true;
      orig_qty_is_set = true;
      orig_qty_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      cond_ord_id_is_set = true;
      cond_ord_id_is_modified = true;
      tstamp_is_set = true;
      tstamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof RlsOmsOrderRow ) )
       return false;
    return fieldsEqual( (RlsOmsOrderRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のRlsOmsOrderRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( RlsOmsOrderRow row )
  {
    if ( account_id != row.getAccountId() )
      return false;
    if ( ord_id != row.getOrdId() )
      return false;
    if ( prod_id != row.getProdId() )
      return false;
    if ( market_id != row.getMarketId() )
      return false;
    if ( ord_type != row.getOrdType() )
      return false;
    if ( exec_type != row.getExecType() )
      return false;
    if ( side != row.getSide() )
      return false;
    if ( orig_qty != row.getOrigQty() )
      return false;
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.longValue() != row.getPrice() ) ) {
        return false;
    }
    if ( cond_ord_id != row.getCondOrdId() )
      return false;
    if ( tstamp == null ) {
      if ( row.getTstamp() != null )
        return false;
    } else if ( !tstamp.equals( row.getTstamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) account_id)
        + ((int) ord_id)
        + ((int) prod_id)
        + ((int) market_id)
        + ((int) ord_type)
        + ((int) exec_type)
        + ((int) side)
        + ((int) orig_qty)
        + (price!=null? price.hashCode(): 0) 
        + ((int) cond_ord_id)
        + (tstamp!=null? tstamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !prod_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'prod_id' must be set before inserting.");
		if ( !market_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_id' must be set before inserting.");
		if ( !exec_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'exec_type' must be set before inserting.");
		if ( !side_is_set )
			throw new IllegalArgumentException("non-nullable field 'side' must be set before inserting.");
		if ( !orig_qty_is_set )
			throw new IllegalArgumentException("non-nullable field 'orig_qty' must be set before inserting.");
		if ( !cond_ord_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'cond_ord_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("account_id",new Long(account_id));
		map.put("ord_id",new Long(ord_id));
		map.put("prod_id",new Long(prod_id));
		map.put("market_id",new Long(market_id));
		map.put("ord_type",new Long(ord_type));
		map.put("exec_type",new Long(exec_type));
		map.put("side",new Long(side));
		map.put("orig_qty",new Long(orig_qty));
		if ( price != null )
			map.put("price",price);
		map.put("cond_ord_id",new Long(cond_ord_id));
		if ( tstamp_is_set )
			map.put("tstamp",tstamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( prod_id_is_modified )
			map.put("prod_id",new Long(prod_id));
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if ( exec_type_is_modified )
			map.put("exec_type",new Long(exec_type));
		if ( side_is_modified )
			map.put("side",new Long(side));
		if ( orig_qty_is_modified )
			map.put("orig_qty",new Long(orig_qty));
		if ( price_is_modified )
			map.put("price",price);
		if ( cond_ord_id_is_modified )
			map.put("cond_ord_id",new Long(cond_ord_id));
		if ( tstamp_is_modified )
			map.put("tstamp",tstamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( prod_id_is_set )
				map.put("prod_id",new Long(prod_id));
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
			if ( exec_type_is_set )
				map.put("exec_type",new Long(exec_type));
			if ( side_is_set )
				map.put("side",new Long(side));
			if ( orig_qty_is_set )
				map.put("orig_qty",new Long(orig_qty));
			map.put("price",price);
			if ( cond_ord_id_is_set )
				map.put("cond_ord_id",new Long(cond_ord_id));
			if ( tstamp_is_set )
				map.put("tstamp",tstamp);
		}
		return map;
	}


  /** 
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
  }


  /** 
   * <em>ord_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrdId()
  {
    return ord_id;
  }


  /** 
   * <em>ord_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdIdIsSet() {
    return ord_id_is_set;
  }


  /** 
   * <em>ord_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdIdIsModified() {
    return ord_id_is_modified;
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
   * <em>ord_type</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrdType()
  {
    return ord_type;
  }


  /** 
   * <em>ord_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdTypeIsSet() {
    return ord_type_is_set;
  }


  /** 
   * <em>ord_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdTypeIsModified() {
    return ord_type_is_modified;
  }


  /** 
   * <em>exec_type</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExecType()
  {
    return exec_type;
  }


  /** 
   * <em>exec_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecTypeIsSet() {
    return exec_type_is_set;
  }


  /** 
   * <em>exec_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecTypeIsModified() {
    return exec_type_is_modified;
  }


  /** 
   * <em>side</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSide()
  {
    return side;
  }


  /** 
   * <em>side</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSideIsSet() {
    return side_is_set;
  }


  /** 
   * <em>side</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSideIsModified() {
    return side_is_modified;
  }


  /** 
   * <em>orig_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrigQty()
  {
    return orig_qty;
  }


  /** 
   * <em>orig_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrigQtyIsSet() {
    return orig_qty_is_set;
  }


  /** 
   * <em>orig_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrigQtyIsModified() {
    return orig_qty_is_modified;
  }


  /** 
   * <em>price</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getPrice()
  {
    return ( price==null? ((long)0): price.longValue() );
  }


  /** 
   * <em>price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPriceIsNull()
  {
    return price == null;
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
   * <em>tstamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTstamp()
  {
    return tstamp;
  }


  /** 
   * <em>tstamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTstampIsSet() {
    return tstamp_is_set;
  }


  /** 
   * <em>tstamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTstampIsModified() {
    return tstamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new RlsOmsOrderPK(ord_id, ord_type);
  }


  /** 
   * <em>account_id</em>カラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわすlong値
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>ord_id</em>カラムの値を設定します。 
   *
   * @@param p_ordId <em>ord_id</em>カラムの値をあらわすlong値
   */
  public final void setOrdId( long p_ordId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ord_id = p_ordId;
    ord_id_is_set = true;
    ord_id_is_modified = true;
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
   * <em>ord_type</em>カラムの値を設定します。 
   *
   * @@param p_ordType <em>ord_type</em>カラムの値をあらわすlong値
   */
  public final void setOrdType( long p_ordType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ord_type = p_ordType;
    ord_type_is_set = true;
    ord_type_is_modified = true;
  }


  /** 
   * <em>exec_type</em>カラムの値を設定します。 
   *
   * @@param p_execType <em>exec_type</em>カラムの値をあらわすlong値
   */
  public final void setExecType( long p_execType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_type = p_execType;
    exec_type_is_set = true;
    exec_type_is_modified = true;
  }


  /** 
   * <em>side</em>カラムの値を設定します。 
   *
   * @@param p_side <em>side</em>カラムの値をあらわすlong値
   */
  public final void setSide( long p_side )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    side = p_side;
    side_is_set = true;
    side_is_modified = true;
  }


  /** 
   * <em>orig_qty</em>カラムの値を設定します。 
   *
   * @@param p_origQty <em>orig_qty</em>カラムの値をあらわすlong値
   */
  public final void setOrigQty( long p_origQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    orig_qty = p_origQty;
    orig_qty_is_set = true;
    orig_qty_is_modified = true;
  }


  /** 
   * <em>price</em>カラムの値を設定します。 
   *
   * @@param p_price <em>price</em>カラムの値をあらわすlong値
   */
  public final void setPrice( long p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price = new Long(p_price);
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>price</em>カラムに値を設定します。 
   */
  public final void setPrice( Long p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    price = p_price;
    price_is_set = true;
    price_is_modified = true;
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
   * <em>tstamp</em>カラムの値を設定します。 
   *
   * @@param p_tstamp <em>tstamp</em>カラムの値をあらわす9桁以下のjava.sql.Timestamp値 
   */
  public final void setTstamp( java.sql.Timestamp p_tstamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tstamp = p_tstamp;
    tstamp_is_set = true;
    tstamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>tstamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTstamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    tstamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    tstamp_is_set = true;
    tstamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                break;
            case 'c':
                if ( name.equals("cond_ord_id") ) {
                    return new Long( cond_ord_id );
                }
                break;
            case 'e':
                if ( name.equals("exec_type") ) {
                    return new Long( exec_type );
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                break;
            case 'o':
                if ( name.equals("ord_id") ) {
                    return new Long( ord_id );
                }
                else if ( name.equals("ord_type") ) {
                    return new Long( ord_type );
                }
                else if ( name.equals("orig_qty") ) {
                    return new Long( orig_qty );
                }
                break;
            case 'p':
                if ( name.equals("prod_id") ) {
                    return new Long( prod_id );
                }
                else if ( name.equals("price") ) {
                    return this.price;
                }
                break;
            case 's':
                if ( name.equals("side") ) {
                    return new Long( side );
                }
                break;
            case 't':
                if ( name.equals("tstamp") ) {
                    return this.tstamp;
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
                if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                break;
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
            case 'e':
                if ( name.equals("exec_type") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'exec_type' must be Long: '"+value+"' is not." );
                    this.exec_type = ((Long) value).longValue();
                    if (this.exec_type_is_set)
                        this.exec_type_is_modified = true;
                    this.exec_type_is_set = true;
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
            case 'o':
                if ( name.equals("ord_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ord_id' must be Long: '"+value+"' is not." );
                    this.ord_id = ((Long) value).longValue();
                    if (this.ord_id_is_set)
                        this.ord_id_is_modified = true;
                    this.ord_id_is_set = true;
                    return;
                }
                else if ( name.equals("ord_type") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ord_type' must be Long: '"+value+"' is not." );
                    this.ord_type = ((Long) value).longValue();
                    if (this.ord_type_is_set)
                        this.ord_type_is_modified = true;
                    this.ord_type_is_set = true;
                    return;
                }
                else if ( name.equals("orig_qty") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'orig_qty' must be Long: '"+value+"' is not." );
                    this.orig_qty = ((Long) value).longValue();
                    if (this.orig_qty_is_set)
                        this.orig_qty_is_modified = true;
                    this.orig_qty_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("prod_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'prod_id' must be Long: '"+value+"' is not." );
                    this.prod_id = ((Long) value).longValue();
                    if (this.prod_id_is_set)
                        this.prod_id_is_modified = true;
                    this.prod_id_is_set = true;
                    return;
                }
                else if ( name.equals("price") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'price' must be Long: '"+value+"' is not." );
                    this.price = (Long) value;
                    if (this.price_is_set)
                        this.price_is_modified = true;
                    this.price_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("side") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'side' must be Long: '"+value+"' is not." );
                    this.side = ((Long) value).longValue();
                    if (this.side_is_set)
                        this.side_is_modified = true;
                    this.side_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tstamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'tstamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.tstamp = (java.sql.Timestamp) value;
                    if (this.tstamp_is_set)
                        this.tstamp_is_modified = true;
                    this.tstamp_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
