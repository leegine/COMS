head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.30.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	RichPushIfoLapseParams.java;


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
 * rich_push_ifo_lapseテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link RichPushIfoLapseRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link RichPushIfoLapseParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link RichPushIfoLapseParams}が{@@link RichPushIfoLapseRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RichPushIfoLapsePK 
 * @@see RichPushIfoLapseRow 
 */
public class RichPushIfoLapseParams extends Params implements RichPushIfoLapseRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "rich_push_ifo_lapse";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = RichPushIfoLapseRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return RichPushIfoLapseRow.TYPE;
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

  /** 
   * <em>request_code</em>カラムの値 
   */
  public  String  request_code;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>trader_code</em>カラムの値 
   */
  public  String  trader_code;

  /** 
   * <em>executed_quantity</em>カラムの値 
   */
  public  Double  executed_quantity;

  /** 
   * <em>reason_code</em>カラムの値 
   */
  public  String  reason_code;

  /** 
   * <em>close_notify_type</em>カラムの値 
   */
  public  String  close_notify_type;

  /** 
   * <em>error_message</em>カラムの値 
   */
  public  String  error_message;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>order_id</em>カラムの値 
   */
  public  long  order_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>order_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum  order_type;

  /** 
   * <em>market_code</em>カラムの値 
   */
  public  String  market_code;

  /** 
   * <em>underlying_product_code</em>カラムの値 
   */
  public  String  underlying_product_code;

  /** 
   * <em>month_of_delivery</em>カラムの値 
   */
  public  String  month_of_delivery;

  /** 
   * <em>derivative_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum  derivative_type;

  /** 
   * <em>strike_price</em>カラムの値 
   */
  public  double  strike_price;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean tpk_is_set = false;
  private boolean tpk_is_modified = false;
  private boolean type_is_set = false;
  private boolean type_is_modified = false;
  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean executed_quantity_is_set = false;
  private boolean executed_quantity_is_modified = false;
  private boolean reason_code_is_set = false;
  private boolean reason_code_is_modified = false;
  private boolean close_notify_type_is_set = false;
  private boolean close_notify_type_is_modified = false;
  private boolean error_message_is_set = false;
  private boolean error_message_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean order_type_is_set = false;
  private boolean order_type_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean underlying_product_code_is_set = false;
  private boolean underlying_product_code_is_modified = false;
  private boolean month_of_delivery_is_set = false;
  private boolean month_of_delivery_is_modified = false;
  private boolean derivative_type_is_set = false;
  private boolean derivative_type_is_modified = false;
  private boolean strike_price_is_set = false;
  private boolean strike_price_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
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
      + "," + "request_code=" +request_code
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "executed_quantity=" +executed_quantity
      + "," + "reason_code=" +reason_code
      + "," + "close_notify_type=" +close_notify_type
      + "," + "error_message=" +error_message
      + "," + "status=" +status
      + "," + "order_id=" +order_id
      + "," + "account_id=" +account_id
      + "," + "product_code=" +product_code
      + "," + "order_type=" +order_type
      + "," + "market_code=" +market_code
      + "," + "underlying_product_code=" +underlying_product_code
      + "," + "month_of_delivery=" +month_of_delivery
      + "," + "derivative_type=" +derivative_type
      + "," + "strike_price=" +strike_price
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のRichPushIfoLapseParamsオブジェクトを作成します。 
   */
  public RichPushIfoLapseParams() {
    serlnum_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のRichPushIfoLapseRowオブジェクトの値を利用してRichPushIfoLapseParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するRichPushIfoLapseRowオブジェクト 
   */
  public RichPushIfoLapseParams( RichPushIfoLapseRow p_row )
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
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    if ( !p_row.getExecutedQuantityIsNull() )
      executed_quantity = new Double( p_row.getExecutedQuantity() );
    executed_quantity_is_set = p_row.getExecutedQuantityIsSet();
    executed_quantity_is_modified = p_row.getExecutedQuantityIsModified();
    reason_code = p_row.getReasonCode();
    reason_code_is_set = p_row.getReasonCodeIsSet();
    reason_code_is_modified = p_row.getReasonCodeIsModified();
    close_notify_type = p_row.getCloseNotifyType();
    close_notify_type_is_set = p_row.getCloseNotifyTypeIsSet();
    close_notify_type_is_modified = p_row.getCloseNotifyTypeIsModified();
    error_message = p_row.getErrorMessage();
    error_message_is_set = p_row.getErrorMessageIsSet();
    error_message_is_modified = p_row.getErrorMessageIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    order_type = p_row.getOrderType();
    order_type_is_set = p_row.getOrderTypeIsSet();
    order_type_is_modified = p_row.getOrderTypeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    underlying_product_code = p_row.getUnderlyingProductCode();
    underlying_product_code_is_set = p_row.getUnderlyingProductCodeIsSet();
    underlying_product_code_is_modified = p_row.getUnderlyingProductCodeIsModified();
    month_of_delivery = p_row.getMonthOfDelivery();
    month_of_delivery_is_set = p_row.getMonthOfDeliveryIsSet();
    month_of_delivery_is_modified = p_row.getMonthOfDeliveryIsModified();
    derivative_type = p_row.getDerivativeType();
    derivative_type_is_set = p_row.getDerivativeTypeIsSet();
    derivative_type_is_modified = p_row.getDerivativeTypeIsModified();
    strike_price = p_row.getStrikePrice();
    strike_price_is_set = p_row.getStrikePriceIsSet();
    strike_price_is_modified = p_row.getStrikePriceIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
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
      request_code_is_set = true;
      request_code_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      trader_code_is_set = true;
      trader_code_is_modified = true;
      executed_quantity_is_set = true;
      executed_quantity_is_modified = true;
      reason_code_is_set = true;
      reason_code_is_modified = true;
      close_notify_type_is_set = true;
      close_notify_type_is_modified = true;
      error_message_is_set = true;
      error_message_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      order_type_is_set = true;
      order_type_is_modified = true;
      market_code_is_set = true;
      market_code_is_modified = true;
      underlying_product_code_is_set = true;
      underlying_product_code_is_modified = true;
      month_of_delivery_is_set = true;
      month_of_delivery_is_modified = true;
      derivative_type_is_set = true;
      derivative_type_is_modified = true;
      strike_price_is_set = true;
      strike_price_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof RichPushIfoLapseRow ) )
       return false;
    return fieldsEqual( (RichPushIfoLapseRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のRichPushIfoLapseRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( RichPushIfoLapseRow row )
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
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( trader_code == null ) {
      if ( row.getTraderCode() != null )
        return false;
    } else if ( !trader_code.equals( row.getTraderCode() ) ) {
        return false;
    }
    if ( executed_quantity == null ) {
      if ( !row.getExecutedQuantityIsNull() )
        return false;
    } else if ( row.getExecutedQuantityIsNull() || ( executed_quantity.doubleValue() != row.getExecutedQuantity() ) ) {
        return false;
    }
    if ( reason_code == null ) {
      if ( row.getReasonCode() != null )
        return false;
    } else if ( !reason_code.equals( row.getReasonCode() ) ) {
        return false;
    }
    if ( close_notify_type == null ) {
      if ( row.getCloseNotifyType() != null )
        return false;
    } else if ( !close_notify_type.equals( row.getCloseNotifyType() ) ) {
        return false;
    }
    if ( error_message == null ) {
      if ( row.getErrorMessage() != null )
        return false;
    } else if ( !error_message.equals( row.getErrorMessage() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( order_id != row.getOrderId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( order_type == null ) {
      if ( row.getOrderType() != null )
        return false;
    } else if ( !order_type.equals( row.getOrderType() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( underlying_product_code == null ) {
      if ( row.getUnderlyingProductCode() != null )
        return false;
    } else if ( !underlying_product_code.equals( row.getUnderlyingProductCode() ) ) {
        return false;
    }
    if ( month_of_delivery == null ) {
      if ( row.getMonthOfDelivery() != null )
        return false;
    } else if ( !month_of_delivery.equals( row.getMonthOfDelivery() ) ) {
        return false;
    }
    if ( derivative_type == null ) {
      if ( row.getDerivativeType() != null )
        return false;
    } else if ( !derivative_type.equals( row.getDerivativeType() ) ) {
        return false;
    }
    if ( strike_price != row.getStrikePrice() )
      return false;
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
        + (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (executed_quantity!=null? executed_quantity.hashCode(): 0) 
        + (reason_code!=null? reason_code.hashCode(): 0) 
        + (close_notify_type!=null? close_notify_type.hashCode(): 0) 
        + (error_message!=null? error_message.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + ((int) order_id)
        + ((int) account_id)
        + (product_code!=null? product_code.hashCode(): 0) 
        + (order_type!=null? order_type.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (underlying_product_code!=null? underlying_product_code.hashCode(): 0) 
        + (month_of_delivery!=null? month_of_delivery.hashCode(): 0) 
        + (derivative_type!=null? derivative_type.hashCode(): 0) 
        + ((int) strike_price)
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
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
		if ( !request_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'request_code' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !close_notify_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'close_notify_type' must be set before inserting.");
		if ( !order_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_id' must be set before inserting.");
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !order_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_type' must be set before inserting.");
		if ( !market_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_code' must be set before inserting.");
		if ( !underlying_product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'underlying_product_code' must be set before inserting.");
		if ( !month_of_delivery_is_set )
			throw new IllegalArgumentException("non-nullable field 'month_of_delivery' must be set before inserting.");
		if ( !derivative_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'derivative_type' must be set before inserting.");
		if ( !strike_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'strike_price' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("tpk",tpk);
		map.put("type",type);
		map.put("request_code",request_code);
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		if ( executed_quantity != null )
			map.put("executed_quantity",executed_quantity);
		if ( reason_code != null )
			map.put("reason_code",reason_code);
		map.put("close_notify_type",close_notify_type);
		if ( error_message != null )
			map.put("error_message",error_message);
		if ( status != null )
			map.put("status",status);
		map.put("order_id",new Long(order_id));
		map.put("account_id",new Long(account_id));
		if ( product_code != null )
			map.put("product_code",product_code);
		map.put("order_type",order_type);
		map.put("market_code",market_code);
		map.put("underlying_product_code",underlying_product_code);
		map.put("month_of_delivery",month_of_delivery);
		map.put("derivative_type",derivative_type);
		map.put("strike_price",new Double(strike_price));
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
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
		if ( request_code_is_modified )
			map.put("request_code",request_code);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( executed_quantity_is_modified )
			map.put("executed_quantity",executed_quantity);
		if ( reason_code_is_modified )
			map.put("reason_code",reason_code);
		if ( close_notify_type_is_modified )
			map.put("close_notify_type",close_notify_type);
		if ( error_message_is_modified )
			map.put("error_message",error_message);
		if ( status_is_modified )
			map.put("status",status);
		if ( order_id_is_modified )
			map.put("order_id",new Long(order_id));
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( order_type_is_modified )
			map.put("order_type",order_type);
		if ( market_code_is_modified )
			map.put("market_code",market_code);
		if ( underlying_product_code_is_modified )
			map.put("underlying_product_code",underlying_product_code);
		if ( month_of_delivery_is_modified )
			map.put("month_of_delivery",month_of_delivery);
		if ( derivative_type_is_modified )
			map.put("derivative_type",derivative_type);
		if ( strike_price_is_modified )
			map.put("strike_price",new Double(strike_price));
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( tpk_is_set )
				map.put("tpk",tpk);
			if ( type_is_set )
				map.put("type",type);
			if ( request_code_is_set )
				map.put("request_code",request_code);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			map.put("executed_quantity",executed_quantity);
			map.put("reason_code",reason_code);
			if ( close_notify_type_is_set )
				map.put("close_notify_type",close_notify_type);
			map.put("error_message",error_message);
			map.put("status",status);
			if ( order_id_is_set )
				map.put("order_id",new Long(order_id));
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			map.put("product_code",product_code);
			if ( order_type_is_set )
				map.put("order_type",order_type);
			if ( market_code_is_set )
				map.put("market_code",market_code);
			if ( underlying_product_code_is_set )
				map.put("underlying_product_code",underlying_product_code);
			if ( month_of_delivery_is_set )
				map.put("month_of_delivery",month_of_delivery);
			if ( derivative_type_is_set )
				map.put("derivative_type",derivative_type);
			if ( strike_price_is_set )
				map.put("strike_price",new Double(strike_price));
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
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
   * <em>request_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRequestCode()
  {
    return request_code;
  }


  /** 
   * <em>request_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsSet() {
    return request_code_is_set;
  }


  /** 
   * <em>request_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsModified() {
    return request_code_is_modified;
  }


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
  }


  /** 
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsModified() {
    return account_code_is_modified;
  }


  /** 
   * <em>trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTraderCode()
  {
    return trader_code;
  }


  /** 
   * <em>trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsSet() {
    return trader_code_is_set;
  }


  /** 
   * <em>trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsModified() {
    return trader_code_is_modified;
  }


  /** 
   * <em>executed_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExecutedQuantity()
  {
    return ( executed_quantity==null? ((double)0): executed_quantity.doubleValue() );
  }


  /** 
   * <em>executed_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExecutedQuantityIsNull()
  {
    return executed_quantity == null;
  }


  /** 
   * <em>executed_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedQuantityIsSet() {
    return executed_quantity_is_set;
  }


  /** 
   * <em>executed_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedQuantityIsModified() {
    return executed_quantity_is_modified;
  }


  /** 
   * <em>reason_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReasonCode()
  {
    return reason_code;
  }


  /** 
   * <em>reason_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReasonCodeIsSet() {
    return reason_code_is_set;
  }


  /** 
   * <em>reason_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReasonCodeIsModified() {
    return reason_code_is_modified;
  }


  /** 
   * <em>close_notify_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCloseNotifyType()
  {
    return close_notify_type;
  }


  /** 
   * <em>close_notify_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseNotifyTypeIsSet() {
    return close_notify_type_is_set;
  }


  /** 
   * <em>close_notify_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseNotifyTypeIsModified() {
    return close_notify_type_is_modified;
  }


  /** 
   * <em>error_message</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getErrorMessage()
  {
    return error_message;
  }


  /** 
   * <em>error_message</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorMessageIsSet() {
    return error_message_is_set;
  }


  /** 
   * <em>error_message</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorMessageIsModified() {
    return error_message_is_modified;
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
   * <em>product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsSet() {
    return product_code_is_set;
  }


  /** 
   * <em>product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsModified() {
    return product_code_is_modified;
  }


  /** 
   * <em>order_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum getOrderType()
  {
    return order_type;
  }


  /** 
   * <em>order_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderTypeIsSet() {
    return order_type_is_set;
  }


  /** 
   * <em>order_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderTypeIsModified() {
    return order_type_is_modified;
  }


  /** 
   * <em>market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarketCode()
  {
    return market_code;
  }


  /** 
   * <em>market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsSet() {
    return market_code_is_set;
  }


  /** 
   * <em>market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsModified() {
    return market_code_is_modified;
  }


  /** 
   * <em>underlying_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getUnderlyingProductCode()
  {
    return underlying_product_code;
  }


  /** 
   * <em>underlying_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnderlyingProductCodeIsSet() {
    return underlying_product_code_is_set;
  }


  /** 
   * <em>underlying_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnderlyingProductCodeIsModified() {
    return underlying_product_code_is_modified;
  }


  /** 
   * <em>month_of_delivery</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMonthOfDelivery()
  {
    return month_of_delivery;
  }


  /** 
   * <em>month_of_delivery</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMonthOfDeliveryIsSet() {
    return month_of_delivery_is_set;
  }


  /** 
   * <em>month_of_delivery</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMonthOfDeliveryIsModified() {
    return month_of_delivery_is_modified;
  }


  /** 
   * <em>derivative_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum getDerivativeType()
  {
    return derivative_type;
  }


  /** 
   * <em>derivative_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDerivativeTypeIsSet() {
    return derivative_type_is_set;
  }


  /** 
   * <em>derivative_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDerivativeTypeIsModified() {
    return derivative_type_is_modified;
  }


  /** 
   * <em>strike_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getStrikePrice()
  {
    return strike_price;
  }


  /** 
   * <em>strike_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStrikePriceIsSet() {
    return strike_price_is_set;
  }


  /** 
   * <em>strike_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStrikePriceIsModified() {
    return strike_price_is_modified;
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
    return new RichPushIfoLapsePK(serlnum);
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
   * <em>request_code</em>カラムの値を設定します。 
   *
   * @@param p_requestCode <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setRequestCode( String p_requestCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_code = p_requestCode;
    request_code_is_set = true;
    request_code_is_modified = true;
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>branch_code</em>カラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>account_code</em>カラムの値を設定します。 
   *
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>trader_code</em>カラムの値を設定します。 
   *
   * @@param p_traderCode <em>trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setTraderCode( String p_traderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_code = p_traderCode;
    trader_code_is_set = true;
    trader_code_is_modified = true;
  }


  /** 
   * <em>executed_quantity</em>カラムの値を設定します。 
   *
   * @@param p_executedQuantity <em>executed_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setExecutedQuantity( double p_executedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    executed_quantity = new Double(p_executedQuantity);
    executed_quantity_is_set = true;
    executed_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>executed_quantity</em>カラムに値を設定します。 
   */
  public final void setExecutedQuantity( Double p_executedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    executed_quantity = p_executedQuantity;
    executed_quantity_is_set = true;
    executed_quantity_is_modified = true;
  }


  /** 
   * <em>reason_code</em>カラムの値を設定します。 
   *
   * @@param p_reasonCode <em>reason_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setReasonCode( String p_reasonCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reason_code = p_reasonCode;
    reason_code_is_set = true;
    reason_code_is_modified = true;
  }


  /** 
   * <em>close_notify_type</em>カラムの値を設定します。 
   *
   * @@param p_closeNotifyType <em>close_notify_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCloseNotifyType( String p_closeNotifyType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_notify_type = p_closeNotifyType;
    close_notify_type_is_set = true;
    close_notify_type_is_modified = true;
  }


  /** 
   * <em>error_message</em>カラムの値を設定します。 
   *
   * @@param p_errorMessage <em>error_message</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setErrorMessage( String p_errorMessage )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_message = p_errorMessage;
    error_message_is_set = true;
    error_message_is_modified = true;
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
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>order_type</em>カラムの値を設定します。 
   *
   * @@param p_orderType <em>order_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum値 
   */
  public final void setOrderType( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum p_orderType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_type = p_orderType;
    order_type_is_set = true;
    order_type_is_modified = true;
  }


  /** 
   * <em>market_code</em>カラムの値を設定します。 
   *
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setMarketCode( String p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
    market_code_is_modified = true;
  }


  /** 
   * <em>underlying_product_code</em>カラムの値を設定します。 
   *
   * @@param p_underlyingProductCode <em>underlying_product_code</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setUnderlyingProductCode( String p_underlyingProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    underlying_product_code = p_underlyingProductCode;
    underlying_product_code_is_set = true;
    underlying_product_code_is_modified = true;
  }


  /** 
   * <em>month_of_delivery</em>カラムの値を設定します。 
   *
   * @@param p_monthOfDelivery <em>month_of_delivery</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setMonthOfDelivery( String p_monthOfDelivery )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    month_of_delivery = p_monthOfDelivery;
    month_of_delivery_is_set = true;
    month_of_delivery_is_modified = true;
  }


  /** 
   * <em>derivative_type</em>カラムの値を設定します。 
   *
   * @@param p_derivativeType <em>derivative_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum値 
   */
  public final void setDerivativeType( com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum p_derivativeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    derivative_type = p_derivativeType;
    derivative_type_is_set = true;
    derivative_type_is_modified = true;
  }


  /** 
   * <em>strike_price</em>カラムの値を設定します。 
   *
   * @@param p_strikePrice <em>strike_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setStrikePrice( double p_strikePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    strike_price = p_strikePrice;
    strike_price_is_set = true;
    strike_price_is_modified = true;
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
            case 'a':
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("close_notify_type") ) {
                    return this.close_notify_type;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("derivative_type") ) {
                    return this.derivative_type;
                }
                break;
            case 'e':
                if ( name.equals("executed_quantity") ) {
                    return this.executed_quantity;
                }
                else if ( name.equals("error_message") ) {
                    return this.error_message;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                else if ( name.equals("month_of_delivery") ) {
                    return this.month_of_delivery;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    return new Long( order_id );
                }
                else if ( name.equals("order_type") ) {
                    return this.order_type;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("reason_code") ) {
                    return this.reason_code;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    return this.status;
                }
                else if ( name.equals("strike_price") ) {
                    return new Double( strike_price );
                }
                else if ( name.equals("serlnum") ) {
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
                else if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                break;
            case 'u':
                if ( name.equals("underlying_product_code") ) {
                    return this.underlying_product_code;
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
                if ( name.equals("account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("close_notify_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'close_notify_type' must be String: '"+value+"' is not." );
                    this.close_notify_type = (String) value;
                    if (this.close_notify_type_is_set)
                        this.close_notify_type_is_modified = true;
                    this.close_notify_type_is_set = true;
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
                if ( name.equals("derivative_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum) )
                        throw new IllegalArgumentException( "value for 'derivative_type' must be com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum: '"+value+"' is not." );
                    this.derivative_type = (com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum) value;
                    if (this.derivative_type_is_set)
                        this.derivative_type_is_modified = true;
                    this.derivative_type_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("executed_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'executed_quantity' must be Double: '"+value+"' is not." );
                    this.executed_quantity = (Double) value;
                    if (this.executed_quantity_is_set)
                        this.executed_quantity_is_modified = true;
                    this.executed_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("error_message") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_message' must be String: '"+value+"' is not." );
                    this.error_message = (String) value;
                    if (this.error_message_is_set)
                        this.error_message_is_modified = true;
                    this.error_message_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
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
            case 'm':
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                else if ( name.equals("month_of_delivery") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'month_of_delivery' must be String: '"+value+"' is not." );
                    this.month_of_delivery = (String) value;
                    if (this.month_of_delivery_is_set)
                        this.month_of_delivery_is_modified = true;
                    this.month_of_delivery_is_set = true;
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
                else if ( name.equals("order_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) )
                        throw new IllegalArgumentException( "value for 'order_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum: '"+value+"' is not." );
                    this.order_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) value;
                    if (this.order_type_is_set)
                        this.order_type_is_modified = true;
                    this.order_type_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_code' must be String: '"+value+"' is not." );
                    this.request_code = (String) value;
                    if (this.request_code_is_set)
                        this.request_code_is_modified = true;
                    this.request_code_is_set = true;
                    return;
                }
                else if ( name.equals("reason_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reason_code' must be String: '"+value+"' is not." );
                    this.reason_code = (String) value;
                    if (this.reason_code_is_set)
                        this.reason_code_is_modified = true;
                    this.reason_code_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                else if ( name.equals("strike_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'strike_price' must be Double: '"+value+"' is not." );
                    this.strike_price = ((Double) value).doubleValue();
                    if (this.strike_price_is_set)
                        this.strike_price_is_modified = true;
                    this.strike_price_is_set = true;
                    return;
                }
                else if ( name.equals("serlnum") ) {
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
                else if ( name.equals("trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trader_code' must be String: '"+value+"' is not." );
                    this.trader_code = (String) value;
                    if (this.trader_code_is_set)
                        this.trader_code_is_modified = true;
                    this.trader_code_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("underlying_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'underlying_product_code' must be String: '"+value+"' is not." );
                    this.underlying_product_code = (String) value;
                    if (this.underlying_product_code_is_set)
                        this.underlying_product_code_is_modified = true;
                    this.underlying_product_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
