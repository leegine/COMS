head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsOrderMissParams.java;


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
 * rls_order_missテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link RlsOrderMissRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link RlsOrderMissParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link RlsOrderMissParams}が{@@link RlsOrderMissRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsOrderMissPK 
 * @@see RlsOrderMissRow 
 */
public class RlsOrderMissParams extends Params implements RlsOrderMissRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "rls_order_miss";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = RlsOrderMissRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return RlsOrderMissRow.TYPE;
  }


  /** 
   * <em>miss_type</em>カラムの値 
   */
  public  String  miss_type;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>カラムの値 
   */
  public  long  sub_account_id;

  /** 
   * <em>order_unit_id</em>カラムの値 
   */
  public  long  order_unit_id;

  /** 
   * <em>order_action_serial_no</em>カラムの値 
   */
  public  int  order_action_serial_no;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>oms_cond_order_type</em>カラムの値 
   */
  public  int  oms_cond_order_type;

  /** 
   * <em>detect_type</em>カラムの値 
   */
  public  String  detect_type;

  /** 
   * <em>order_id</em>カラムの値 
   */
  public  long  order_id;

  /** 
   * <em>order_event_type</em>カラムの値 
   */
  public  int  order_event_type;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  long  market_id;

  /** 
   * <em>order_cond_operator</em>カラムの値 
   */
  public  String  order_cond_operator;

  /** 
   * <em>stop_order_price</em>カラムの値 
   */
  public  Double  stop_order_price;

  /** 
   * <em>hit_tick_price</em>カラムの値 
   */
  public  Double  hit_tick_price;

  /** 
   * <em>hit_tick_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  hit_tick_timestamp;

  /** 
   * <em>order_accepted_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  order_accepted_timestamp;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>order_submit_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  order_submit_timestamp;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  private boolean miss_type_is_set = false;
  private boolean miss_type_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean order_unit_id_is_set = false;
  private boolean order_unit_id_is_modified = false;
  private boolean order_action_serial_no_is_set = false;
  private boolean order_action_serial_no_is_modified = false;
  private boolean order_event_type_is_set = false;
  private boolean order_event_type_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean oms_cond_order_type_is_set = false;
  private boolean oms_cond_order_type_is_modified = false;
  private boolean order_cond_operator_is_set = false;
  private boolean order_cond_operator_is_modified = false;
  private boolean stop_order_price_is_set = false;
  private boolean stop_order_price_is_modified = false;
  private boolean hit_tick_price_is_set = false;
  private boolean hit_tick_price_is_modified = false;
  private boolean hit_tick_timestamp_is_set = false;
  private boolean hit_tick_timestamp_is_modified = false;
  private boolean order_accepted_timestamp_is_set = false;
  private boolean order_accepted_timestamp_is_modified = false;
  private boolean detect_type_is_set = false;
  private boolean detect_type_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean order_submit_timestamp_is_set = false;
  private boolean order_submit_timestamp_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "miss_type=" + miss_type
      + "," + "account_id=" + account_id
      + "," + "sub_account_id=" + sub_account_id
      + "," + "order_unit_id=" + order_unit_id
      + "," + "order_action_serial_no=" + order_action_serial_no
      + "," + "product_type=" + product_type
      + "," + "oms_cond_order_type=" + oms_cond_order_type
      + "," + "detect_type=" + detect_type
      + "," + "order_id=" +order_id
      + "," + "order_event_type=" +order_event_type
      + "," + "product_id=" +product_id
      + "," + "market_id=" +market_id
      + "," + "order_cond_operator=" +order_cond_operator
      + "," + "stop_order_price=" +stop_order_price
      + "," + "hit_tick_price=" +hit_tick_price
      + "," + "hit_tick_timestamp=" +hit_tick_timestamp
      + "," + "order_accepted_timestamp=" +order_accepted_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "order_submit_timestamp=" +order_submit_timestamp
      + "," + "status=" +status
      + "}";
  }


  /** 
   * 値が未設定のRlsOrderMissParamsオブジェクトを作成します。 
   */
  public RlsOrderMissParams() {
    miss_type_is_modified = true;
    account_id_is_modified = true;
    sub_account_id_is_modified = true;
    order_unit_id_is_modified = true;
    order_action_serial_no_is_modified = true;
    product_type_is_modified = true;
    oms_cond_order_type_is_modified = true;
    detect_type_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のRlsOrderMissRowオブジェクトの値を利用してRlsOrderMissParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するRlsOrderMissRowオブジェクト 
   */
  public RlsOrderMissParams( RlsOrderMissRow p_row )
  {
    miss_type = p_row.getMissType();
    miss_type_is_set = p_row.getMissTypeIsSet();
    miss_type_is_modified = p_row.getMissTypeIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    order_unit_id = p_row.getOrderUnitId();
    order_unit_id_is_set = p_row.getOrderUnitIdIsSet();
    order_unit_id_is_modified = p_row.getOrderUnitIdIsModified();
    order_action_serial_no = p_row.getOrderActionSerialNo();
    order_action_serial_no_is_set = p_row.getOrderActionSerialNoIsSet();
    order_action_serial_no_is_modified = p_row.getOrderActionSerialNoIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    oms_cond_order_type = p_row.getOmsCondOrderType();
    oms_cond_order_type_is_set = p_row.getOmsCondOrderTypeIsSet();
    oms_cond_order_type_is_modified = p_row.getOmsCondOrderTypeIsModified();
    detect_type = p_row.getDetectType();
    detect_type_is_set = p_row.getDetectTypeIsSet();
    detect_type_is_modified = p_row.getDetectTypeIsModified();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    order_event_type = p_row.getOrderEventType();
    order_event_type_is_set = p_row.getOrderEventTypeIsSet();
    order_event_type_is_modified = p_row.getOrderEventTypeIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    order_cond_operator = p_row.getOrderCondOperator();
    order_cond_operator_is_set = p_row.getOrderCondOperatorIsSet();
    order_cond_operator_is_modified = p_row.getOrderCondOperatorIsModified();
    if ( !p_row.getStopOrderPriceIsNull() )
      stop_order_price = new Double( p_row.getStopOrderPrice() );
    stop_order_price_is_set = p_row.getStopOrderPriceIsSet();
    stop_order_price_is_modified = p_row.getStopOrderPriceIsModified();
    if ( !p_row.getHitTickPriceIsNull() )
      hit_tick_price = new Double( p_row.getHitTickPrice() );
    hit_tick_price_is_set = p_row.getHitTickPriceIsSet();
    hit_tick_price_is_modified = p_row.getHitTickPriceIsModified();
    hit_tick_timestamp = p_row.getHitTickTimestamp();
    hit_tick_timestamp_is_set = p_row.getHitTickTimestampIsSet();
    hit_tick_timestamp_is_modified = p_row.getHitTickTimestampIsModified();
    order_accepted_timestamp = p_row.getOrderAcceptedTimestamp();
    order_accepted_timestamp_is_set = p_row.getOrderAcceptedTimestampIsSet();
    order_accepted_timestamp_is_modified = p_row.getOrderAcceptedTimestampIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    order_submit_timestamp = p_row.getOrderSubmitTimestamp();
    order_submit_timestamp_is_set = p_row.getOrderSubmitTimestampIsSet();
    order_submit_timestamp_is_modified = p_row.getOrderSubmitTimestampIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      order_id_is_set = true;
      order_id_is_modified = true;
      order_event_type_is_set = true;
      order_event_type_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      order_cond_operator_is_set = true;
      order_cond_operator_is_modified = true;
      stop_order_price_is_set = true;
      stop_order_price_is_modified = true;
      hit_tick_price_is_set = true;
      hit_tick_price_is_modified = true;
      hit_tick_timestamp_is_set = true;
      hit_tick_timestamp_is_modified = true;
      order_accepted_timestamp_is_set = true;
      order_accepted_timestamp_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      order_submit_timestamp_is_set = true;
      order_submit_timestamp_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof RlsOrderMissRow ) )
       return false;
    return fieldsEqual( (RlsOrderMissRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のRlsOrderMissRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( RlsOrderMissRow row )
  {
    if ( miss_type == null ) {
      if ( row.getMissType() != null )
        return false;
    } else if ( !miss_type.equals( row.getMissType() ) ) {
        return false;
    }
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( order_id != row.getOrderId() )
      return false;
    if ( order_unit_id != row.getOrderUnitId() )
      return false;
    if ( order_action_serial_no != row.getOrderActionSerialNo() )
      return false;
    if ( order_event_type != row.getOrderEventType() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( product_id != row.getProductId() )
      return false;
    if ( market_id != row.getMarketId() )
      return false;
    if ( oms_cond_order_type != row.getOmsCondOrderType() )
      return false;
    if ( order_cond_operator == null ) {
      if ( row.getOrderCondOperator() != null )
        return false;
    } else if ( !order_cond_operator.equals( row.getOrderCondOperator() ) ) {
        return false;
    }
    if ( stop_order_price == null ) {
      if ( !row.getStopOrderPriceIsNull() )
        return false;
    } else if ( row.getStopOrderPriceIsNull() || ( stop_order_price.doubleValue() != row.getStopOrderPrice() ) ) {
        return false;
    }
    if ( hit_tick_price == null ) {
      if ( !row.getHitTickPriceIsNull() )
        return false;
    } else if ( row.getHitTickPriceIsNull() || ( hit_tick_price.doubleValue() != row.getHitTickPrice() ) ) {
        return false;
    }
    if ( hit_tick_timestamp == null ) {
      if ( row.getHitTickTimestamp() != null )
        return false;
    } else if ( !hit_tick_timestamp.equals( row.getHitTickTimestamp() ) ) {
        return false;
    }
    if ( order_accepted_timestamp == null ) {
      if ( row.getOrderAcceptedTimestamp() != null )
        return false;
    } else if ( !order_accepted_timestamp.equals( row.getOrderAcceptedTimestamp() ) ) {
        return false;
    }
    if ( detect_type == null ) {
      if ( row.getDetectType() != null )
        return false;
    } else if ( !detect_type.equals( row.getDetectType() ) ) {
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
    if ( order_submit_timestamp == null ) {
      if ( row.getOrderSubmitTimestamp() != null )
        return false;
    } else if ( !order_submit_timestamp.equals( row.getOrderSubmitTimestamp() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (miss_type!=null? miss_type.hashCode(): 0) 
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) order_id)
        + ((int) order_unit_id)
        + ((int) order_action_serial_no)
        + ((int) order_event_type)
        + (product_type!=null? product_type.hashCode(): 0) 
        + ((int) product_id)
        + ((int) market_id)
        + ((int) oms_cond_order_type)
        + (order_cond_operator!=null? order_cond_operator.hashCode(): 0) 
        + (stop_order_price!=null? stop_order_price.hashCode(): 0) 
        + (hit_tick_price!=null? hit_tick_price.hashCode(): 0) 
        + (hit_tick_timestamp!=null? hit_tick_timestamp.hashCode(): 0) 
        + (order_accepted_timestamp!=null? order_accepted_timestamp.hashCode(): 0) 
        + (detect_type!=null? detect_type.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (order_submit_timestamp!=null? order_submit_timestamp.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !order_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_id' must be set before inserting.");
		if ( !order_event_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_event_type' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !market_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_id' must be set before inserting.");
		if ( !order_accepted_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_accepted_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("miss_type",miss_type);
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("order_id",new Long(order_id));
		map.put("order_unit_id",new Long(order_unit_id));
		map.put("order_action_serial_no",new Integer(order_action_serial_no));
		map.put("order_event_type",new Integer(order_event_type));
		map.put("product_type",product_type);
		map.put("product_id",new Long(product_id));
		map.put("market_id",new Long(market_id));
		map.put("oms_cond_order_type",new Integer(oms_cond_order_type));
		if ( order_cond_operator != null )
			map.put("order_cond_operator",order_cond_operator);
		if ( stop_order_price != null )
			map.put("stop_order_price",stop_order_price);
		if ( hit_tick_price != null )
			map.put("hit_tick_price",hit_tick_price);
		if ( hit_tick_timestamp != null )
			map.put("hit_tick_timestamp",hit_tick_timestamp);
		map.put("order_accepted_timestamp",order_accepted_timestamp);
		map.put("detect_type",detect_type);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( order_submit_timestamp != null )
			map.put("order_submit_timestamp",order_submit_timestamp);
		if ( status != null )
			map.put("status",status);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( order_id_is_modified )
			map.put("order_id",new Long(order_id));
		if ( order_event_type_is_modified )
			map.put("order_event_type",new Integer(order_event_type));
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if ( order_cond_operator_is_modified )
			map.put("order_cond_operator",order_cond_operator);
		if ( stop_order_price_is_modified )
			map.put("stop_order_price",stop_order_price);
		if ( hit_tick_price_is_modified )
			map.put("hit_tick_price",hit_tick_price);
		if ( hit_tick_timestamp_is_modified )
			map.put("hit_tick_timestamp",hit_tick_timestamp);
		if ( order_accepted_timestamp_is_modified )
			map.put("order_accepted_timestamp",order_accepted_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( order_submit_timestamp_is_modified )
			map.put("order_submit_timestamp",order_submit_timestamp);
		if ( status_is_modified )
			map.put("status",status);
		if (map.size() == 0) {
			if ( order_id_is_set )
				map.put("order_id",new Long(order_id));
			if ( order_event_type_is_set )
				map.put("order_event_type",new Integer(order_event_type));
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
			map.put("order_cond_operator",order_cond_operator);
			map.put("stop_order_price",stop_order_price);
			map.put("hit_tick_price",hit_tick_price);
			map.put("hit_tick_timestamp",hit_tick_timestamp);
			if ( order_accepted_timestamp_is_set )
				map.put("order_accepted_timestamp",order_accepted_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("order_submit_timestamp",order_submit_timestamp);
			map.put("status",status);
		}
		return map;
	}


  /** 
   * <em>miss_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMissType()
  {
    return miss_type;
  }


  /** 
   * <em>miss_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMissTypeIsSet() {
    return miss_type_is_set;
  }


  /** 
   * <em>miss_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMissTypeIsModified() {
    return miss_type_is_modified;
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
   * <em>order_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrderId()
  {
    return order_id;
  }


  /** 
   * <em>order_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderIdIsSet() {
    return order_id_is_set;
  }


  /** 
   * <em>order_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderIdIsModified() {
    return order_id_is_modified;
  }


  /** 
   * <em>order_unit_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrderUnitId()
  {
    return order_unit_id;
  }


  /** 
   * <em>order_unit_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderUnitIdIsSet() {
    return order_unit_id_is_set;
  }


  /** 
   * <em>order_unit_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderUnitIdIsModified() {
    return order_unit_id_is_modified;
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
   * <em>order_event_type</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOrderEventType()
  {
    return order_event_type;
  }


  /** 
   * <em>order_event_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderEventTypeIsSet() {
    return order_event_type_is_set;
  }


  /** 
   * <em>order_event_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderEventTypeIsModified() {
    return order_event_type_is_modified;
  }


  /** 
   * <em>product_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType()
  {
    return product_type;
  }


  /** 
   * <em>product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsSet() {
    return product_type_is_set;
  }


  /** 
   * <em>product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsModified() {
    return product_type_is_modified;
  }


  /** 
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getProductId()
  {
    return product_id;
  }


  /** 
   * <em>product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsSet() {
    return product_id_is_set;
  }


  /** 
   * <em>product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsModified() {
    return product_id_is_modified;
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
   * <em>oms_cond_order_type</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOmsCondOrderType()
  {
    return oms_cond_order_type;
  }


  /** 
   * <em>oms_cond_order_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOmsCondOrderTypeIsSet() {
    return oms_cond_order_type_is_set;
  }


  /** 
   * <em>oms_cond_order_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOmsCondOrderTypeIsModified() {
    return oms_cond_order_type_is_modified;
  }


  /** 
   * <em>order_cond_operator</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderCondOperator()
  {
    return order_cond_operator;
  }


  /** 
   * <em>order_cond_operator</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCondOperatorIsSet() {
    return order_cond_operator_is_set;
  }


  /** 
   * <em>order_cond_operator</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCondOperatorIsModified() {
    return order_cond_operator_is_modified;
  }


  /** 
   * <em>stop_order_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getStopOrderPrice()
  {
    return ( stop_order_price==null? ((double)0): stop_order_price.doubleValue() );
  }


  /** 
   * <em>stop_order_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getStopOrderPriceIsNull()
  {
    return stop_order_price == null;
  }


  /** 
   * <em>stop_order_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopOrderPriceIsSet() {
    return stop_order_price_is_set;
  }


  /** 
   * <em>stop_order_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopOrderPriceIsModified() {
    return stop_order_price_is_modified;
  }


  /** 
   * <em>hit_tick_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getHitTickPrice()
  {
    return ( hit_tick_price==null? ((double)0): hit_tick_price.doubleValue() );
  }


  /** 
   * <em>hit_tick_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getHitTickPriceIsNull()
  {
    return hit_tick_price == null;
  }


  /** 
   * <em>hit_tick_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHitTickPriceIsSet() {
    return hit_tick_price_is_set;
  }


  /** 
   * <em>hit_tick_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHitTickPriceIsModified() {
    return hit_tick_price_is_modified;
  }


  /** 
   * <em>hit_tick_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getHitTickTimestamp()
  {
    return hit_tick_timestamp;
  }


  /** 
   * <em>hit_tick_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHitTickTimestampIsSet() {
    return hit_tick_timestamp_is_set;
  }


  /** 
   * <em>hit_tick_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHitTickTimestampIsModified() {
    return hit_tick_timestamp_is_modified;
  }


  /** 
   * <em>order_accepted_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrderAcceptedTimestamp()
  {
    return order_accepted_timestamp;
  }


  /** 
   * <em>order_accepted_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderAcceptedTimestampIsSet() {
    return order_accepted_timestamp_is_set;
  }


  /** 
   * <em>order_accepted_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderAcceptedTimestampIsModified() {
    return order_accepted_timestamp_is_modified;
  }


  /** 
   * <em>detect_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDetectType()
  {
    return detect_type;
  }


  /** 
   * <em>detect_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDetectTypeIsSet() {
    return detect_type_is_set;
  }


  /** 
   * <em>detect_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDetectTypeIsModified() {
    return detect_type_is_modified;
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
   * <em>order_submit_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrderSubmitTimestamp()
  {
    return order_submit_timestamp;
  }


  /** 
   * <em>order_submit_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderSubmitTimestampIsSet() {
    return order_submit_timestamp_is_set;
  }


  /** 
   * <em>order_submit_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderSubmitTimestampIsModified() {
    return order_submit_timestamp_is_modified;
  }


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsModified() {
    return status_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new RlsOrderMissPK(miss_type, account_id, sub_account_id, order_unit_id, order_action_serial_no, product_type, oms_cond_order_type, detect_type);
  }


  /** 
   * <em>miss_type</em>カラムの値を設定します。 
   *
   * @@param p_missType <em>miss_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMissType( String p_missType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    miss_type = p_missType;
    miss_type_is_set = true;
    miss_type_is_modified = true;
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
   * <em>order_id</em>カラムの値を設定します。 
   *
   * @@param p_orderId <em>order_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setOrderId( long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
    order_id_is_set = true;
    order_id_is_modified = true;
  }


  /** 
   * <em>order_unit_id</em>カラムの値を設定します。 
   *
   * @@param p_orderUnitId <em>order_unit_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setOrderUnitId( long p_orderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_unit_id = p_orderUnitId;
    order_unit_id_is_set = true;
    order_unit_id_is_modified = true;
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
   * <em>order_event_type</em>カラムの値を設定します。 
   *
   * @@param p_orderEventType <em>order_event_type</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setOrderEventType( int p_orderEventType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_event_type = p_orderEventType;
    order_event_type_is_set = true;
    order_event_type_is_modified = true;
  }


  /** 
   * <em>product_type</em>カラムの値を設定します。 
   *
   * @@param p_productType <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public final void setProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_type = p_productType;
    product_type_is_set = true;
    product_type_is_modified = true;
  }


  /** 
   * <em>product_id</em>カラムの値を設定します。 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setProductId( long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = p_productId;
    product_id_is_set = true;
    product_id_is_modified = true;
  }


  /** 
   * <em>market_id</em>カラムの値を設定します。 
   *
   * @@param p_marketId <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * <em>oms_cond_order_type</em>カラムの値を設定します。 
   *
   * @@param p_omsCondOrderType <em>oms_cond_order_type</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setOmsCondOrderType( int p_omsCondOrderType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    oms_cond_order_type = p_omsCondOrderType;
    oms_cond_order_type_is_set = true;
    oms_cond_order_type_is_modified = true;
  }


  /** 
   * <em>order_cond_operator</em>カラムの値を設定します。 
   *
   * @@param p_orderCondOperator <em>order_cond_operator</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderCondOperator( String p_orderCondOperator )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_cond_operator = p_orderCondOperator;
    order_cond_operator_is_set = true;
    order_cond_operator_is_modified = true;
  }


  /** 
   * <em>stop_order_price</em>カラムの値を設定します。 
   *
   * @@param p_stopOrderPrice <em>stop_order_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setStopOrderPrice( double p_stopOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_order_price = new Double(p_stopOrderPrice);
    stop_order_price_is_set = true;
    stop_order_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>stop_order_price</em>カラムに値を設定します。 
   */
  public final void setStopOrderPrice( Double p_stopOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    stop_order_price = p_stopOrderPrice;
    stop_order_price_is_set = true;
    stop_order_price_is_modified = true;
  }


  /** 
   * <em>hit_tick_price</em>カラムの値を設定します。 
   *
   * @@param p_hitTickPrice <em>hit_tick_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setHitTickPrice( double p_hitTickPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    hit_tick_price = new Double(p_hitTickPrice);
    hit_tick_price_is_set = true;
    hit_tick_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>hit_tick_price</em>カラムに値を設定します。 
   */
  public final void setHitTickPrice( Double p_hitTickPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    hit_tick_price = p_hitTickPrice;
    hit_tick_price_is_set = true;
    hit_tick_price_is_modified = true;
  }


  /** 
   * <em>hit_tick_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_hitTickTimestamp <em>hit_tick_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setHitTickTimestamp( java.sql.Timestamp p_hitTickTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    hit_tick_timestamp = p_hitTickTimestamp;
    hit_tick_timestamp_is_set = true;
    hit_tick_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>hit_tick_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setHitTickTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    hit_tick_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    hit_tick_timestamp_is_set = true;
    hit_tick_timestamp_is_modified = true;
  }


  /** 
   * <em>order_accepted_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_orderAcceptedTimestamp <em>order_accepted_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrderAcceptedTimestamp( java.sql.Timestamp p_orderAcceptedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_accepted_timestamp = p_orderAcceptedTimestamp;
    order_accepted_timestamp_is_set = true;
    order_accepted_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>order_accepted_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrderAcceptedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_accepted_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_accepted_timestamp_is_set = true;
    order_accepted_timestamp_is_modified = true;
  }


  /** 
   * <em>detect_type</em>カラムの値を設定します。 
   *
   * @@param p_detectType <em>detect_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDetectType( String p_detectType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    detect_type = p_detectType;
    detect_type_is_set = true;
    detect_type_is_modified = true;
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
   * <em>order_submit_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_orderSubmitTimestamp <em>order_submit_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrderSubmitTimestamp( java.sql.Timestamp p_orderSubmitTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_submit_timestamp = p_orderSubmitTimestamp;
    order_submit_timestamp_is_set = true;
    order_submit_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>order_submit_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrderSubmitTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_submit_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_submit_timestamp_is_set = true;
    order_submit_timestamp_is_modified = true;
  }


  /** 
   * <em>status</em>カラムの値を設定します。 
   *
   * @@param p_status <em>status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStatus( String p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
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
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("detect_type") ) {
                    return this.detect_type;
                }
                break;
            case 'h':
                if ( name.equals("hit_tick_price") ) {
                    return this.hit_tick_price;
                }
                else if ( name.equals("hit_tick_timestamp") ) {
                    return this.hit_tick_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("miss_type") ) {
                    return this.miss_type;
                }
                else if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    return new Long( order_id );
                }
                else if ( name.equals("order_unit_id") ) {
                    return new Long( order_unit_id );
                }
                else if ( name.equals("order_action_serial_no") ) {
                    return new Integer( order_action_serial_no );
                }
                else if ( name.equals("order_event_type") ) {
                    return new Integer( order_event_type );
                }
                else if ( name.equals("oms_cond_order_type") ) {
                    return new Integer( oms_cond_order_type );
                }
                else if ( name.equals("order_cond_operator") ) {
                    return this.order_cond_operator;
                }
                else if ( name.equals("order_accepted_timestamp") ) {
                    return this.order_accepted_timestamp;
                }
                else if ( name.equals("order_submit_timestamp") ) {
                    return this.order_submit_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("stop_order_price") ) {
                    return this.stop_order_price;
                }
                else if ( name.equals("status") ) {
                    return this.status;
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
                if ( name.equals("created_timestamp") ) {
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
                if ( name.equals("detect_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'detect_type' must be String: '"+value+"' is not." );
                    this.detect_type = (String) value;
                    if (this.detect_type_is_set)
                        this.detect_type_is_modified = true;
                    this.detect_type_is_set = true;
                    return;
                }
                break;
            case 'h':
                if ( name.equals("hit_tick_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'hit_tick_price' must be Double: '"+value+"' is not." );
                    this.hit_tick_price = (Double) value;
                    if (this.hit_tick_price_is_set)
                        this.hit_tick_price_is_modified = true;
                    this.hit_tick_price_is_set = true;
                    return;
                }
                else if ( name.equals("hit_tick_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'hit_tick_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.hit_tick_timestamp = (java.sql.Timestamp) value;
                    if (this.hit_tick_timestamp_is_set)
                        this.hit_tick_timestamp_is_modified = true;
                    this.hit_tick_timestamp_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("miss_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'miss_type' must be String: '"+value+"' is not." );
                    this.miss_type = (String) value;
                    if (this.miss_type_is_set)
                        this.miss_type_is_modified = true;
                    this.miss_type_is_set = true;
                    return;
                }
                else if ( name.equals("market_id") ) {
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
                if ( name.equals("order_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = ((Long) value).longValue();
                    if (this.order_id_is_set)
                        this.order_id_is_modified = true;
                    this.order_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_unit_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_unit_id' must be Long: '"+value+"' is not." );
                    this.order_unit_id = ((Long) value).longValue();
                    if (this.order_unit_id_is_set)
                        this.order_unit_id_is_modified = true;
                    this.order_unit_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_action_serial_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'order_action_serial_no' must be Integer: '"+value+"' is not." );
                    this.order_action_serial_no = ((Integer) value).intValue();
                    if (this.order_action_serial_no_is_set)
                        this.order_action_serial_no_is_modified = true;
                    this.order_action_serial_no_is_set = true;
                    return;
                }
                else if ( name.equals("order_event_type") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'order_event_type' must be Integer: '"+value+"' is not." );
                    this.order_event_type = ((Integer) value).intValue();
                    if (this.order_event_type_is_set)
                        this.order_event_type_is_modified = true;
                    this.order_event_type_is_set = true;
                    return;
                }
                else if ( name.equals("oms_cond_order_type") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'oms_cond_order_type' must be Integer: '"+value+"' is not." );
                    this.oms_cond_order_type = ((Integer) value).intValue();
                    if (this.oms_cond_order_type_is_set)
                        this.oms_cond_order_type_is_modified = true;
                    this.oms_cond_order_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_cond_operator") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_cond_operator' must be String: '"+value+"' is not." );
                    this.order_cond_operator = (String) value;
                    if (this.order_cond_operator_is_set)
                        this.order_cond_operator_is_modified = true;
                    this.order_cond_operator_is_set = true;
                    return;
                }
                else if ( name.equals("order_accepted_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_accepted_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_accepted_timestamp = (java.sql.Timestamp) value;
                    if (this.order_accepted_timestamp_is_set)
                        this.order_accepted_timestamp_is_modified = true;
                    this.order_accepted_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("order_submit_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_submit_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_submit_timestamp = (java.sql.Timestamp) value;
                    if (this.order_submit_timestamp_is_set)
                        this.order_submit_timestamp_is_modified = true;
                    this.order_submit_timestamp_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                else if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
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
                else if ( name.equals("stop_order_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'stop_order_price' must be Double: '"+value+"' is not." );
                    this.stop_order_price = (Double) value;
                    if (this.stop_order_price_is_set)
                        this.stop_order_price_is_modified = true;
                    this.stop_order_price_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
