head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.50.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoBookbuildingOrderActionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * ipo_bookbuilding_order_actionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link IpoBookbuildingOrderActionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link IpoBookbuildingOrderActionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link IpoBookbuildingOrderActionParams}が{@@link IpoBookbuildingOrderActionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IpoBookbuildingOrderActionPK 
 * @@see IpoBookbuildingOrderActionRow 
 */
public class IpoBookbuildingOrderActionParams extends Params implements IpoBookbuildingOrderActionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ipo_bookbuilding_order_action";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = IpoBookbuildingOrderActionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return IpoBookbuildingOrderActionRow.TYPE;
  }


  /** 
   * <em>bookbuilding_order_action_id</em>カラムの値 
   */
  public  long  bookbuilding_order_action_id;

  /** 
   * <em>ipo_order_id</em>カラムの値 
   */
  public  long  ipo_order_id;

  /** 
   * <em>ipo_product_id</em>カラムの値 
   */
  public  long  ipo_product_id;

  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>カラムの値 
   */
  public  long  sub_account_id;

  /** 
   * <em>order_action_serial_no</em>カラムの値 
   */
  public  int  order_action_serial_no;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  double  quantity;

  /** 
   * <em>limit_price</em>カラムの値 
   */
  public  double  limit_price;

  /** 
   * <em>order_status</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum  order_status;

  /** 
   * <em>order_open_status</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum  order_open_status;

  /** 
   * <em>price</em>カラムの値 
   */
  public  Double  price;

  /** 
   * <em>current_price</em>カラムの値 
   */
  public  Double  current_price;

  /** 
   * <em>bookbuilding_price</em>カラムの値 
   */
  public  Double  bookbuilding_price;

  /** 
   * <em>bookbuilding_created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  bookbuilding_created_timestamp;

  /** 
   * <em>trader_id</em>カラムの値 
   */
  public  Long  trader_id;

  /** 
   * <em>order_root_div</em>カラムの値 
   */
  public  String  order_root_div;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>delete_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  delete_flag;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>action_send_status</em>カラムの値 
   */
  public  String  action_send_status;

  private boolean bookbuilding_order_action_id_is_set = false;
  private boolean bookbuilding_order_action_id_is_modified = false;
  private boolean ipo_order_id_is_set = false;
  private boolean ipo_order_id_is_modified = false;
  private boolean ipo_product_id_is_set = false;
  private boolean ipo_product_id_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean order_action_serial_no_is_set = false;
  private boolean order_action_serial_no_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean limit_price_is_set = false;
  private boolean limit_price_is_modified = false;
  private boolean order_status_is_set = false;
  private boolean order_status_is_modified = false;
  private boolean order_open_status_is_set = false;
  private boolean order_open_status_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean current_price_is_set = false;
  private boolean current_price_is_modified = false;
  private boolean bookbuilding_price_is_set = false;
  private boolean bookbuilding_price_is_modified = false;
  private boolean bookbuilding_created_timestamp_is_set = false;
  private boolean bookbuilding_created_timestamp_is_modified = false;
  private boolean trader_id_is_set = false;
  private boolean trader_id_is_modified = false;
  private boolean order_root_div_is_set = false;
  private boolean order_root_div_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean action_send_status_is_set = false;
  private boolean action_send_status_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "bookbuilding_order_action_id=" + bookbuilding_order_action_id
      + "," + "ipo_order_id=" +ipo_order_id
      + "," + "ipo_product_id=" +ipo_product_id
      + "," + "branch_id=" +branch_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "order_action_serial_no=" +order_action_serial_no
      + "," + "quantity=" +quantity
      + "," + "limit_price=" +limit_price
      + "," + "order_status=" +order_status
      + "," + "order_open_status=" +order_open_status
      + "," + "price=" +price
      + "," + "current_price=" +current_price
      + "," + "bookbuilding_price=" +bookbuilding_price
      + "," + "bookbuilding_created_timestamp=" +bookbuilding_created_timestamp
      + "," + "trader_id=" +trader_id
      + "," + "order_root_div=" +order_root_div
      + "," + "last_updater=" +last_updater
      + "," + "delete_flag=" +delete_flag
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "action_send_status=" +action_send_status
      + "}";
  }


  /** 
   * 値が未設定のIpoBookbuildingOrderActionParamsオブジェクトを作成します。 
   */
  public IpoBookbuildingOrderActionParams() {
    bookbuilding_order_action_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のIpoBookbuildingOrderActionRowオブジェクトの値を利用してIpoBookbuildingOrderActionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するIpoBookbuildingOrderActionRowオブジェクト 
   */
  public IpoBookbuildingOrderActionParams( IpoBookbuildingOrderActionRow p_row )
  {
    bookbuilding_order_action_id = p_row.getBookbuildingOrderActionId();
    bookbuilding_order_action_id_is_set = p_row.getBookbuildingOrderActionIdIsSet();
    bookbuilding_order_action_id_is_modified = p_row.getBookbuildingOrderActionIdIsModified();
    ipo_order_id = p_row.getIpoOrderId();
    ipo_order_id_is_set = p_row.getIpoOrderIdIsSet();
    ipo_order_id_is_modified = p_row.getIpoOrderIdIsModified();
    ipo_product_id = p_row.getIpoProductId();
    ipo_product_id_is_set = p_row.getIpoProductIdIsSet();
    ipo_product_id_is_modified = p_row.getIpoProductIdIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    order_action_serial_no = p_row.getOrderActionSerialNo();
    order_action_serial_no_is_set = p_row.getOrderActionSerialNoIsSet();
    order_action_serial_no_is_modified = p_row.getOrderActionSerialNoIsModified();
    quantity = p_row.getQuantity();
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    limit_price = p_row.getLimitPrice();
    limit_price_is_set = p_row.getLimitPriceIsSet();
    limit_price_is_modified = p_row.getLimitPriceIsModified();
    order_status = p_row.getOrderStatus();
    order_status_is_set = p_row.getOrderStatusIsSet();
    order_status_is_modified = p_row.getOrderStatusIsModified();
    order_open_status = p_row.getOrderOpenStatus();
    order_open_status_is_set = p_row.getOrderOpenStatusIsSet();
    order_open_status_is_modified = p_row.getOrderOpenStatusIsModified();
    if ( !p_row.getPriceIsNull() )
      price = new Double( p_row.getPrice() );
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    if ( !p_row.getCurrentPriceIsNull() )
      current_price = new Double( p_row.getCurrentPrice() );
    current_price_is_set = p_row.getCurrentPriceIsSet();
    current_price_is_modified = p_row.getCurrentPriceIsModified();
    if ( !p_row.getBookbuildingPriceIsNull() )
      bookbuilding_price = new Double( p_row.getBookbuildingPrice() );
    bookbuilding_price_is_set = p_row.getBookbuildingPriceIsSet();
    bookbuilding_price_is_modified = p_row.getBookbuildingPriceIsModified();
    bookbuilding_created_timestamp = p_row.getBookbuildingCreatedTimestamp();
    bookbuilding_created_timestamp_is_set = p_row.getBookbuildingCreatedTimestampIsSet();
    bookbuilding_created_timestamp_is_modified = p_row.getBookbuildingCreatedTimestampIsModified();
    if ( !p_row.getTraderIdIsNull() )
      trader_id = new Long( p_row.getTraderId() );
    trader_id_is_set = p_row.getTraderIdIsSet();
    trader_id_is_modified = p_row.getTraderIdIsModified();
    order_root_div = p_row.getOrderRootDiv();
    order_root_div_is_set = p_row.getOrderRootDivIsSet();
    order_root_div_is_modified = p_row.getOrderRootDivIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    action_send_status = p_row.getActionSendStatus();
    action_send_status_is_set = p_row.getActionSendStatusIsSet();
    action_send_status_is_modified = p_row.getActionSendStatusIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      ipo_order_id_is_set = true;
      ipo_order_id_is_modified = true;
      ipo_product_id_is_set = true;
      ipo_product_id_is_modified = true;
      branch_id_is_set = true;
      branch_id_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      sub_account_id_is_set = true;
      sub_account_id_is_modified = true;
      order_action_serial_no_is_set = true;
      order_action_serial_no_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      limit_price_is_set = true;
      limit_price_is_modified = true;
      order_status_is_set = true;
      order_status_is_modified = true;
      order_open_status_is_set = true;
      order_open_status_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      current_price_is_set = true;
      current_price_is_modified = true;
      bookbuilding_price_is_set = true;
      bookbuilding_price_is_modified = true;
      bookbuilding_created_timestamp_is_set = true;
      bookbuilding_created_timestamp_is_modified = true;
      trader_id_is_set = true;
      trader_id_is_modified = true;
      order_root_div_is_set = true;
      order_root_div_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      action_send_status_is_set = true;
      action_send_status_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof IpoBookbuildingOrderActionRow ) )
       return false;
    return fieldsEqual( (IpoBookbuildingOrderActionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のIpoBookbuildingOrderActionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( IpoBookbuildingOrderActionRow row )
  {
    if ( bookbuilding_order_action_id != row.getBookbuildingOrderActionId() )
      return false;
    if ( ipo_order_id != row.getIpoOrderId() )
      return false;
    if ( ipo_product_id != row.getIpoProductId() )
      return false;
    if ( branch_id != row.getBranchId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( order_action_serial_no != row.getOrderActionSerialNo() )
      return false;
    if ( quantity != row.getQuantity() )
      return false;
    if ( limit_price != row.getLimitPrice() )
      return false;
    if ( order_status == null ) {
      if ( row.getOrderStatus() != null )
        return false;
    } else if ( !order_status.equals( row.getOrderStatus() ) ) {
        return false;
    }
    if ( order_open_status == null ) {
      if ( row.getOrderOpenStatus() != null )
        return false;
    } else if ( !order_open_status.equals( row.getOrderOpenStatus() ) ) {
        return false;
    }
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.doubleValue() != row.getPrice() ) ) {
        return false;
    }
    if ( current_price == null ) {
      if ( !row.getCurrentPriceIsNull() )
        return false;
    } else if ( row.getCurrentPriceIsNull() || ( current_price.doubleValue() != row.getCurrentPrice() ) ) {
        return false;
    }
    if ( bookbuilding_price == null ) {
      if ( !row.getBookbuildingPriceIsNull() )
        return false;
    } else if ( row.getBookbuildingPriceIsNull() || ( bookbuilding_price.doubleValue() != row.getBookbuildingPrice() ) ) {
        return false;
    }
    if ( bookbuilding_created_timestamp == null ) {
      if ( row.getBookbuildingCreatedTimestamp() != null )
        return false;
    } else if ( !bookbuilding_created_timestamp.equals( row.getBookbuildingCreatedTimestamp() ) ) {
        return false;
    }
    if ( trader_id == null ) {
      if ( !row.getTraderIdIsNull() )
        return false;
    } else if ( row.getTraderIdIsNull() || ( trader_id.longValue() != row.getTraderId() ) ) {
        return false;
    }
    if ( order_root_div == null ) {
      if ( row.getOrderRootDiv() != null )
        return false;
    } else if ( !order_root_div.equals( row.getOrderRootDiv() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    if ( action_send_status == null ) {
      if ( row.getActionSendStatus() != null )
        return false;
    } else if ( !action_send_status.equals( row.getActionSendStatus() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) bookbuilding_order_action_id)
        + ((int) ipo_order_id)
        + ((int) ipo_product_id)
        + ((int) branch_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) order_action_serial_no)
        + ((int) quantity)
        + ((int) limit_price)
        + (order_status!=null? order_status.hashCode(): 0) 
        + (order_open_status!=null? order_open_status.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (current_price!=null? current_price.hashCode(): 0) 
        + (bookbuilding_price!=null? bookbuilding_price.hashCode(): 0) 
        + (bookbuilding_created_timestamp!=null? bookbuilding_created_timestamp.hashCode(): 0) 
        + (trader_id!=null? trader_id.hashCode(): 0) 
        + (order_root_div!=null? order_root_div.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (action_send_status!=null? action_send_status.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !ipo_order_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'ipo_order_id' must be set before inserting.");
		if ( !ipo_product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'ipo_product_id' must be set before inserting.");
		if ( !branch_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_id' must be set before inserting.");
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !sub_account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'sub_account_id' must be set before inserting.");
		if ( !order_action_serial_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_action_serial_no' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
		if ( !limit_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'limit_price' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("bookbuilding_order_action_id",new Long(bookbuilding_order_action_id));
		map.put("ipo_order_id",new Long(ipo_order_id));
		map.put("ipo_product_id",new Long(ipo_product_id));
		map.put("branch_id",new Long(branch_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("order_action_serial_no",new Integer(order_action_serial_no));
		map.put("quantity",new Double(quantity));
		map.put("limit_price",new Double(limit_price));
		if ( order_status != null )
			map.put("order_status",order_status);
		if ( order_open_status != null )
			map.put("order_open_status",order_open_status);
		if ( price != null )
			map.put("price",price);
		if ( current_price != null )
			map.put("current_price",current_price);
		if ( bookbuilding_price != null )
			map.put("bookbuilding_price",bookbuilding_price);
		if ( bookbuilding_created_timestamp != null )
			map.put("bookbuilding_created_timestamp",bookbuilding_created_timestamp);
		if ( trader_id != null )
			map.put("trader_id",trader_id);
		if ( order_root_div != null )
			map.put("order_root_div",order_root_div);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( delete_flag != null )
			map.put("delete_flag",delete_flag);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( action_send_status != null )
			map.put("action_send_status",action_send_status);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( ipo_order_id_is_modified )
			map.put("ipo_order_id",new Long(ipo_order_id));
		if ( ipo_product_id_is_modified )
			map.put("ipo_product_id",new Long(ipo_product_id));
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( sub_account_id_is_modified )
			map.put("sub_account_id",new Long(sub_account_id));
		if ( order_action_serial_no_is_modified )
			map.put("order_action_serial_no",new Integer(order_action_serial_no));
		if ( quantity_is_modified )
			map.put("quantity",new Double(quantity));
		if ( limit_price_is_modified )
			map.put("limit_price",new Double(limit_price));
		if ( order_status_is_modified )
			map.put("order_status",order_status);
		if ( order_open_status_is_modified )
			map.put("order_open_status",order_open_status);
		if ( price_is_modified )
			map.put("price",price);
		if ( current_price_is_modified )
			map.put("current_price",current_price);
		if ( bookbuilding_price_is_modified )
			map.put("bookbuilding_price",bookbuilding_price);
		if ( bookbuilding_created_timestamp_is_modified )
			map.put("bookbuilding_created_timestamp",bookbuilding_created_timestamp);
		if ( trader_id_is_modified )
			map.put("trader_id",trader_id);
		if ( order_root_div_is_modified )
			map.put("order_root_div",order_root_div);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( action_send_status_is_modified )
			map.put("action_send_status",action_send_status);
		if (map.size() == 0) {
			if ( ipo_order_id_is_set )
				map.put("ipo_order_id",new Long(ipo_order_id));
			if ( ipo_product_id_is_set )
				map.put("ipo_product_id",new Long(ipo_product_id));
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( order_action_serial_no_is_set )
				map.put("order_action_serial_no",new Integer(order_action_serial_no));
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			if ( limit_price_is_set )
				map.put("limit_price",new Double(limit_price));
			map.put("order_status",order_status);
			map.put("order_open_status",order_open_status);
			map.put("price",price);
			map.put("current_price",current_price);
			map.put("bookbuilding_price",bookbuilding_price);
			map.put("bookbuilding_created_timestamp",bookbuilding_created_timestamp);
			map.put("trader_id",trader_id);
			map.put("order_root_div",order_root_div);
			map.put("last_updater",last_updater);
			map.put("delete_flag",delete_flag);
			map.put("created_timestamp",created_timestamp);
			map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("action_send_status",action_send_status);
		}
		return map;
	}


  /** 
   * <em>bookbuilding_order_action_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBookbuildingOrderActionId()
  {
    return bookbuilding_order_action_id;
  }


  /** 
   * <em>bookbuilding_order_action_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBookbuildingOrderActionIdIsSet() {
    return bookbuilding_order_action_id_is_set;
  }


  /** 
   * <em>bookbuilding_order_action_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBookbuildingOrderActionIdIsModified() {
    return bookbuilding_order_action_id_is_modified;
  }


  /** 
   * <em>ipo_order_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getIpoOrderId()
  {
    return ipo_order_id;
  }


  /** 
   * <em>ipo_order_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIpoOrderIdIsSet() {
    return ipo_order_id_is_set;
  }


  /** 
   * <em>ipo_order_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIpoOrderIdIsModified() {
    return ipo_order_id_is_modified;
  }


  /** 
   * <em>ipo_product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getIpoProductId()
  {
    return ipo_product_id;
  }


  /** 
   * <em>ipo_product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIpoProductIdIsSet() {
    return ipo_product_id_is_set;
  }


  /** 
   * <em>ipo_product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIpoProductIdIsModified() {
    return ipo_product_id_is_modified;
  }


  /** 
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
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
   * <em>sub_account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSubAccountId()
  {
    return sub_account_id;
  }


  /** 
   * <em>sub_account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccountIdIsSet() {
    return sub_account_id_is_set;
  }


  /** 
   * <em>sub_account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccountIdIsModified() {
    return sub_account_id_is_modified;
  }


  /** 
   * <em>order_action_serial_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOrderActionSerialNo()
  {
    return order_action_serial_no;
  }


  /** 
   * <em>order_action_serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderActionSerialNoIsSet() {
    return order_action_serial_no_is_set;
  }


  /** 
   * <em>order_action_serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderActionSerialNoIsModified() {
    return order_action_serial_no_is_modified;
  }


  /** 
   * <em>quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getQuantity()
  {
    return quantity;
  }


  /** 
   * <em>quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuantityIsSet() {
    return quantity_is_set;
  }


  /** 
   * <em>quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuantityIsModified() {
    return quantity_is_modified;
  }


  /** 
   * <em>limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLimitPrice()
  {
    return limit_price;
  }


  /** 
   * <em>limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitPriceIsSet() {
    return limit_price_is_set;
  }


  /** 
   * <em>limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitPriceIsModified() {
    return limit_price_is_modified;
  }


  /** 
   * <em>order_status</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum getOrderStatus()
  {
    return order_status;
  }


  /** 
   * <em>order_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderStatusIsSet() {
    return order_status_is_set;
  }


  /** 
   * <em>order_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderStatusIsModified() {
    return order_status_is_modified;
  }


  /** 
   * <em>order_open_status</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum getOrderOpenStatus()
  {
    return order_open_status;
  }


  /** 
   * <em>order_open_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderOpenStatusIsSet() {
    return order_open_status_is_set;
  }


  /** 
   * <em>order_open_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderOpenStatusIsModified() {
    return order_open_status_is_modified;
  }


  /** 
   * <em>price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPrice()
  {
    return ( price==null? ((double)0): price.doubleValue() );
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
   * <em>current_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCurrentPrice()
  {
    return ( current_price==null? ((double)0): current_price.doubleValue() );
  }


  /** 
   * <em>current_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCurrentPriceIsNull()
  {
    return current_price == null;
  }


  /** 
   * <em>current_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentPriceIsSet() {
    return current_price_is_set;
  }


  /** 
   * <em>current_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentPriceIsModified() {
    return current_price_is_modified;
  }


  /** 
   * <em>bookbuilding_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBookbuildingPrice()
  {
    return ( bookbuilding_price==null? ((double)0): bookbuilding_price.doubleValue() );
  }


  /** 
   * <em>bookbuilding_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBookbuildingPriceIsNull()
  {
    return bookbuilding_price == null;
  }


  /** 
   * <em>bookbuilding_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBookbuildingPriceIsSet() {
    return bookbuilding_price_is_set;
  }


  /** 
   * <em>bookbuilding_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBookbuildingPriceIsModified() {
    return bookbuilding_price_is_modified;
  }


  /** 
   * <em>bookbuilding_created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getBookbuildingCreatedTimestamp()
  {
    return bookbuilding_created_timestamp;
  }


  /** 
   * <em>bookbuilding_created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBookbuildingCreatedTimestampIsSet() {
    return bookbuilding_created_timestamp_is_set;
  }


  /** 
   * <em>bookbuilding_created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBookbuildingCreatedTimestampIsModified() {
    return bookbuilding_created_timestamp_is_modified;
  }


  /** 
   * <em>trader_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTraderId()
  {
    return ( trader_id==null? ((long)0): trader_id.longValue() );
  }


  /** 
   * <em>trader_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTraderIdIsNull()
  {
    return trader_id == null;
  }


  /** 
   * <em>trader_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderIdIsSet() {
    return trader_id_is_set;
  }


  /** 
   * <em>trader_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderIdIsModified() {
    return trader_id_is_modified;
  }


  /** 
   * <em>order_root_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRootDiv()
  {
    return order_root_div;
  }


  /** 
   * <em>order_root_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRootDivIsSet() {
    return order_root_div_is_set;
  }


  /** 
   * <em>order_root_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRootDivIsModified() {
    return order_root_div_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
  }


  /** 
   * <em>delete_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDeleteFlag()
  {
    return delete_flag;
  }


  /** 
   * <em>delete_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsSet() {
    return delete_flag_is_set;
  }


  /** 
   * <em>delete_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsModified() {
    return delete_flag_is_modified;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * <em>action_send_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getActionSendStatus()
  {
    return action_send_status;
  }


  /** 
   * <em>action_send_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActionSendStatusIsSet() {
    return action_send_status_is_set;
  }


  /** 
   * <em>action_send_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActionSendStatusIsModified() {
    return action_send_status_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new IpoBookbuildingOrderActionPK(bookbuilding_order_action_id);
  }


  /** 
   * <em>bookbuilding_order_action_id</em>カラムの値を設定します。 
   *
   * @@param p_bookbuildingOrderActionId <em>bookbuilding_order_action_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBookbuildingOrderActionId( long p_bookbuildingOrderActionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bookbuilding_order_action_id = p_bookbuildingOrderActionId;
    bookbuilding_order_action_id_is_set = true;
    bookbuilding_order_action_id_is_modified = true;
  }


  /** 
   * <em>ipo_order_id</em>カラムの値を設定します。 
   *
   * @@param p_ipoOrderId <em>ipo_order_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setIpoOrderId( long p_ipoOrderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ipo_order_id = p_ipoOrderId;
    ipo_order_id_is_set = true;
    ipo_order_id_is_modified = true;
  }


  /** 
   * <em>ipo_product_id</em>カラムの値を設定します。 
   *
   * @@param p_ipoProductId <em>ipo_product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setIpoProductId( long p_ipoProductId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ipo_product_id = p_ipoProductId;
    ipo_product_id_is_set = true;
    ipo_product_id_is_modified = true;
  }


  /** 
   * <em>branch_id</em>カラムの値を設定します。 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>account_id</em>カラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>sub_account_id</em>カラムの値を設定します。 
   *
   * @@param p_subAccountId <em>sub_account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setSubAccountId( long p_subAccountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_account_id = p_subAccountId;
    sub_account_id_is_set = true;
    sub_account_id_is_modified = true;
  }


  /** 
   * <em>order_action_serial_no</em>カラムの値を設定します。 
   *
   * @@param p_orderActionSerialNo <em>order_action_serial_no</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setOrderActionSerialNo( int p_orderActionSerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_action_serial_no = p_orderActionSerialNo;
    order_action_serial_no_is_set = true;
    order_action_serial_no_is_modified = true;
  }


  /** 
   * <em>quantity</em>カラムの値を設定します。 
   *
   * @@param p_quantity <em>quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * <em>limit_price</em>カラムの値を設定します。 
   *
   * @@param p_limitPrice <em>limit_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLimitPrice( double p_limitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limit_price = p_limitPrice;
    limit_price_is_set = true;
    limit_price_is_modified = true;
  }


  /** 
   * <em>order_status</em>カラムの値を設定します。 
   *
   * @@param p_orderStatus <em>order_status</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum値 
   */
  public final void setOrderStatus( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum p_orderStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_status = p_orderStatus;
    order_status_is_set = true;
    order_status_is_modified = true;
  }


  /** 
   * <em>order_open_status</em>カラムの値を設定します。 
   *
   * @@param p_orderOpenStatus <em>order_open_status</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum値 
   */
  public final void setOrderOpenStatus( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum p_orderOpenStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_open_status = p_orderOpenStatus;
    order_open_status_is_set = true;
    order_open_status_is_modified = true;
  }


  /** 
   * <em>price</em>カラムの値を設定します。 
   *
   * @@param p_price <em>price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPrice( double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price = new Double(p_price);
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>price</em>カラムに値を設定します。 
   */
  public final void setPrice( Double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    price = p_price;
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * <em>current_price</em>カラムの値を設定します。 
   *
   * @@param p_currentPrice <em>current_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCurrentPrice( double p_currentPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_price = new Double(p_currentPrice);
    current_price_is_set = true;
    current_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>current_price</em>カラムに値を設定します。 
   */
  public final void setCurrentPrice( Double p_currentPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    current_price = p_currentPrice;
    current_price_is_set = true;
    current_price_is_modified = true;
  }


  /** 
   * <em>bookbuilding_price</em>カラムの値を設定します。 
   *
   * @@param p_bookbuildingPrice <em>bookbuilding_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBookbuildingPrice( double p_bookbuildingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bookbuilding_price = new Double(p_bookbuildingPrice);
    bookbuilding_price_is_set = true;
    bookbuilding_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>bookbuilding_price</em>カラムに値を設定します。 
   */
  public final void setBookbuildingPrice( Double p_bookbuildingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bookbuilding_price = p_bookbuildingPrice;
    bookbuilding_price_is_set = true;
    bookbuilding_price_is_modified = true;
  }


  /** 
   * <em>bookbuilding_created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_bookbuildingCreatedTimestamp <em>bookbuilding_created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setBookbuildingCreatedTimestamp( java.sql.Timestamp p_bookbuildingCreatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bookbuilding_created_timestamp = p_bookbuildingCreatedTimestamp;
    bookbuilding_created_timestamp_is_set = true;
    bookbuilding_created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>bookbuilding_created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setBookbuildingCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bookbuilding_created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    bookbuilding_created_timestamp_is_set = true;
    bookbuilding_created_timestamp_is_modified = true;
  }


  /** 
   * <em>trader_id</em>カラムの値を設定します。 
   *
   * @@param p_traderId <em>trader_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setTraderId( long p_traderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_id = new Long(p_traderId);
    trader_id_is_set = true;
    trader_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>trader_id</em>カラムに値を設定します。 
   */
  public final void setTraderId( Long p_traderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trader_id = p_traderId;
    trader_id_is_set = true;
    trader_id_is_modified = true;
  }


  /** 
   * <em>order_root_div</em>カラムの値を設定します。 
   *
   * @@param p_orderRootDiv <em>order_root_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderRootDiv( String p_orderRootDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_root_div = p_orderRootDiv;
    order_root_div_is_set = true;
    order_root_div_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
  }


  /** 
   * <em>delete_flag</em>カラムの値を設定します。 
   *
   * @@param p_deleteFlag <em>delete_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setDeleteFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = p_deleteFlag;
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * <em>action_send_status</em>カラムの値を設定します。 
   *
   * @@param p_actionSendStatus <em>action_send_status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setActionSendStatus( String p_actionSendStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    action_send_status = p_actionSendStatus;
    action_send_status_is_set = true;
    action_send_status_is_modified = true;
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
                else if ( name.equals("action_send_status") ) {
                    return this.action_send_status;
                }
                break;
            case 'b':
                if ( name.equals("bookbuilding_order_action_id") ) {
                    return new Long( bookbuilding_order_action_id );
                }
                else if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                else if ( name.equals("bookbuilding_price") ) {
                    return this.bookbuilding_price;
                }
                else if ( name.equals("bookbuilding_created_timestamp") ) {
                    return this.bookbuilding_created_timestamp;
                }
                break;
            case 'c':
                if ( name.equals("current_price") ) {
                    return this.current_price;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                break;
            case 'i':
                if ( name.equals("ipo_order_id") ) {
                    return new Long( ipo_order_id );
                }
                else if ( name.equals("ipo_product_id") ) {
                    return new Long( ipo_product_id );
                }
                break;
            case 'l':
                if ( name.equals("limit_price") ) {
                    return new Double( limit_price );
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_action_serial_no") ) {
                    return new Integer( order_action_serial_no );
                }
                else if ( name.equals("order_status") ) {
                    return this.order_status;
                }
                else if ( name.equals("order_open_status") ) {
                    return this.order_open_status;
                }
                else if ( name.equals("order_root_div") ) {
                    return this.order_root_div;
                }
                break;
            case 'p':
                if ( name.equals("price") ) {
                    return this.price;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return new Double( quantity );
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    return this.trader_id;
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
                else if ( name.equals("action_send_status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'action_send_status' must be String: '"+value+"' is not." );
                    this.action_send_status = (String) value;
                    if (this.action_send_status_is_set)
                        this.action_send_status_is_modified = true;
                    this.action_send_status_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("bookbuilding_order_action_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'bookbuilding_order_action_id' must be Long: '"+value+"' is not." );
                    this.bookbuilding_order_action_id = ((Long) value).longValue();
                    if (this.bookbuilding_order_action_id_is_set)
                        this.bookbuilding_order_action_id_is_modified = true;
                    this.bookbuilding_order_action_id_is_set = true;
                    return;
                }
                else if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                else if ( name.equals("bookbuilding_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'bookbuilding_price' must be Double: '"+value+"' is not." );
                    this.bookbuilding_price = (Double) value;
                    if (this.bookbuilding_price_is_set)
                        this.bookbuilding_price_is_modified = true;
                    this.bookbuilding_price_is_set = true;
                    return;
                }
                else if ( name.equals("bookbuilding_created_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'bookbuilding_created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.bookbuilding_created_timestamp = (java.sql.Timestamp) value;
                    if (this.bookbuilding_created_timestamp_is_set)
                        this.bookbuilding_created_timestamp_is_modified = true;
                    this.bookbuilding_created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("current_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'current_price' must be Double: '"+value+"' is not." );
                    this.current_price = (Double) value;
                    if (this.current_price_is_set)
                        this.current_price_is_modified = true;
                    this.current_price_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("delete_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.delete_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("ipo_order_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ipo_order_id' must be Long: '"+value+"' is not." );
                    this.ipo_order_id = ((Long) value).longValue();
                    if (this.ipo_order_id_is_set)
                        this.ipo_order_id_is_modified = true;
                    this.ipo_order_id_is_set = true;
                    return;
                }
                else if ( name.equals("ipo_product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ipo_product_id' must be Long: '"+value+"' is not." );
                    this.ipo_product_id = ((Long) value).longValue();
                    if (this.ipo_product_id_is_set)
                        this.ipo_product_id_is_modified = true;
                    this.ipo_product_id_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("limit_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'limit_price' must be Double: '"+value+"' is not." );
                    this.limit_price = ((Double) value).doubleValue();
                    if (this.limit_price_is_set)
                        this.limit_price_is_modified = true;
                    this.limit_price_is_set = true;
                    return;
                }
                else if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_action_serial_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'order_action_serial_no' must be Integer: '"+value+"' is not." );
                    this.order_action_serial_no = ((Integer) value).intValue();
                    if (this.order_action_serial_no_is_set)
                        this.order_action_serial_no_is_modified = true;
                    this.order_action_serial_no_is_set = true;
                    return;
                }
                else if ( name.equals("order_status") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum) )
                        throw new IllegalArgumentException( "value for 'order_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum: '"+value+"' is not." );
                    this.order_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum) value;
                    if (this.order_status_is_set)
                        this.order_status_is_modified = true;
                    this.order_status_is_set = true;
                    return;
                }
                else if ( name.equals("order_open_status") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum) )
                        throw new IllegalArgumentException( "value for 'order_open_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum: '"+value+"' is not." );
                    this.order_open_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum) value;
                    if (this.order_open_status_is_set)
                        this.order_open_status_is_modified = true;
                    this.order_open_status_is_set = true;
                    return;
                }
                else if ( name.equals("order_root_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_root_div' must be String: '"+value+"' is not." );
                    this.order_root_div = (String) value;
                    if (this.order_root_div_is_set)
                        this.order_root_div_is_modified = true;
                    this.order_root_div_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'price' must be Double: '"+value+"' is not." );
                    this.price = (Double) value;
                    if (this.price_is_set)
                        this.price_is_modified = true;
                    this.price_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = ((Double) value).doubleValue();
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_account_id' must be Long: '"+value+"' is not." );
                    this.sub_account_id = ((Long) value).longValue();
                    if (this.sub_account_id_is_set)
                        this.sub_account_id_is_modified = true;
                    this.sub_account_id_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'trader_id' must be Long: '"+value+"' is not." );
                    this.trader_id = (Long) value;
                    if (this.trader_id_is_set)
                        this.trader_id_is_modified = true;
                    this.trader_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
