head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostFotypeOrderClmdNotifyParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * host_fotype_order_clmd_notifyテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostFotypeOrderClmdNotifyRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostFotypeOrderClmdNotifyParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostFotypeOrderClmdNotifyParams}が{@@link HostFotypeOrderClmdNotifyRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostFotypeOrderClmdNotifyPK 
 * @@see HostFotypeOrderClmdNotifyRow 
 */
public class HostFotypeOrderClmdNotifyParams extends Params implements HostFotypeOrderClmdNotifyRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_fotype_order_clmd_notify";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostFotypeOrderClmdNotifyRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostFotypeOrderClmdNotifyRow.TYPE;
  }


  /** 
   * <em>rowid</em>カラムの値 
   */
  public  String  rowid;

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
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>modified_quantity</em>カラムの値 
   */
  public  double  modified_quantity;

  /** 
   * <em>modified_limit_price</em>カラムの値 
   */
  public  double  modified_limit_price;

  /** 
   * <em>modified_execution_type</em>カラムの値 
   */
  public  String  modified_execution_type;

  /** 
   * <em>modified_result</em>カラムの値 
   */
  public  String  modified_result;

  /** 
   * <em>canmod_receipt_type</em>カラムの値 
   */
  public  String  canmod_receipt_type;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>mod_submit_order_route_div</em>カラムの値 
   */
  public  String  mod_submit_order_route_div;

  /** 
   * <em>modified_order_rev</em>カラムの値 
   */
  public  String  modified_order_rev;

  /** 
   * <em>virtual_server_number_market</em>カラムの値 
   */
  public  String  virtual_server_number_market;

  /** 
   * <em>notice_type</em>カラムの値 
   */
  public  String  notice_type;

  /** 
   * <em>notice_number</em>カラムの値 
   */
  public  Long  notice_number;

  /** 
   * <em>modified_stop_order_price</em>カラムの値 
   */
  public  Double  modified_stop_order_price;

  /** 
   * <em>modified_w_limit_price</em>カラムの値 
   */
  public  Double  modified_w_limit_price;

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
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean modified_quantity_is_set = false;
  private boolean modified_quantity_is_modified = false;
  private boolean modified_limit_price_is_set = false;
  private boolean modified_limit_price_is_modified = false;
  private boolean modified_execution_type_is_set = false;
  private boolean modified_execution_type_is_modified = false;
  private boolean modified_result_is_set = false;
  private boolean modified_result_is_modified = false;
  private boolean canmod_receipt_type_is_set = false;
  private boolean canmod_receipt_type_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean mod_submit_order_route_div_is_set = false;
  private boolean mod_submit_order_route_div_is_modified = false;
  private boolean modified_order_rev_is_set = false;
  private boolean modified_order_rev_is_modified = false;
  private boolean virtual_server_number_market_is_set = false;
  private boolean virtual_server_number_market_is_modified = false;
  private boolean notice_type_is_set = false;
  private boolean notice_type_is_modified = false;
  private boolean notice_number_is_set = false;
  private boolean notice_number_is_modified = false;
  private boolean modified_stop_order_price_is_set = false;
  private boolean modified_stop_order_price_is_modified = false;
  private boolean modified_w_limit_price_is_set = false;
  private boolean modified_w_limit_price_is_modified = false;
  private boolean rowid_is_set = false;
  private boolean rowid_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "rowid=" + rowid
      + "," + "request_code=" +request_code
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "order_request_number=" +order_request_number
      + "," + "modified_quantity=" +modified_quantity
      + "," + "modified_limit_price=" +modified_limit_price
      + "," + "modified_execution_type=" +modified_execution_type
      + "," + "modified_result=" +modified_result
      + "," + "canmod_receipt_type=" +canmod_receipt_type
      + "," + "status=" +status
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "mod_submit_order_route_div=" +mod_submit_order_route_div
      + "," + "modified_order_rev=" +modified_order_rev
      + "," + "virtual_server_number_market=" +virtual_server_number_market
      + "," + "notice_type=" +notice_type
      + "," + "notice_number=" +notice_number
      + "," + "modified_stop_order_price=" +modified_stop_order_price
      + "," + "modified_w_limit_price=" +modified_w_limit_price
      + "}";
  }


  /** 
   * 値が未設定のHostFotypeOrderClmdNotifyParamsオブジェクトを作成します。 
   */
  public HostFotypeOrderClmdNotifyParams() {
    rowid_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostFotypeOrderClmdNotifyRowオブジェクトの値を利用してHostFotypeOrderClmdNotifyParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostFotypeOrderClmdNotifyRowオブジェクト 
   */
  public HostFotypeOrderClmdNotifyParams( HostFotypeOrderClmdNotifyRow p_row )
  {
    rowid = p_row.getRowid();
    rowid_is_set = p_row.getRowidIsSet();
    rowid_is_modified = p_row.getRowidIsModified();
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
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    modified_quantity = p_row.getModifiedQuantity();
    modified_quantity_is_set = p_row.getModifiedQuantityIsSet();
    modified_quantity_is_modified = p_row.getModifiedQuantityIsModified();
    modified_limit_price = p_row.getModifiedLimitPrice();
    modified_limit_price_is_set = p_row.getModifiedLimitPriceIsSet();
    modified_limit_price_is_modified = p_row.getModifiedLimitPriceIsModified();
    modified_execution_type = p_row.getModifiedExecutionType();
    modified_execution_type_is_set = p_row.getModifiedExecutionTypeIsSet();
    modified_execution_type_is_modified = p_row.getModifiedExecutionTypeIsModified();
    modified_result = p_row.getModifiedResult();
    modified_result_is_set = p_row.getModifiedResultIsSet();
    modified_result_is_modified = p_row.getModifiedResultIsModified();
    canmod_receipt_type = p_row.getCanmodReceiptType();
    canmod_receipt_type_is_set = p_row.getCanmodReceiptTypeIsSet();
    canmod_receipt_type_is_modified = p_row.getCanmodReceiptTypeIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    mod_submit_order_route_div = p_row.getModSubmitOrderRouteDiv();
    mod_submit_order_route_div_is_set = p_row.getModSubmitOrderRouteDivIsSet();
    mod_submit_order_route_div_is_modified = p_row.getModSubmitOrderRouteDivIsModified();
    modified_order_rev = p_row.getModifiedOrderRev();
    modified_order_rev_is_set = p_row.getModifiedOrderRevIsSet();
    modified_order_rev_is_modified = p_row.getModifiedOrderRevIsModified();
    virtual_server_number_market = p_row.getVirtualServerNumberMarket();
    virtual_server_number_market_is_set = p_row.getVirtualServerNumberMarketIsSet();
    virtual_server_number_market_is_modified = p_row.getVirtualServerNumberMarketIsModified();
    notice_type = p_row.getNoticeType();
    notice_type_is_set = p_row.getNoticeTypeIsSet();
    notice_type_is_modified = p_row.getNoticeTypeIsModified();
    if ( !p_row.getNoticeNumberIsNull() )
      notice_number = new Long( p_row.getNoticeNumber() );
    notice_number_is_set = p_row.getNoticeNumberIsSet();
    notice_number_is_modified = p_row.getNoticeNumberIsModified();
    if ( !p_row.getModifiedStopOrderPriceIsNull() )
      modified_stop_order_price = new Double( p_row.getModifiedStopOrderPrice() );
    modified_stop_order_price_is_set = p_row.getModifiedStopOrderPriceIsSet();
    modified_stop_order_price_is_modified = p_row.getModifiedStopOrderPriceIsModified();
    if ( !p_row.getModifiedWLimitPriceIsNull() )
      modified_w_limit_price = new Double( p_row.getModifiedWLimitPrice() );
    modified_w_limit_price_is_set = p_row.getModifiedWLimitPriceIsSet();
    modified_w_limit_price_is_modified = p_row.getModifiedWLimitPriceIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
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
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      modified_quantity_is_set = true;
      modified_quantity_is_modified = true;
      modified_limit_price_is_set = true;
      modified_limit_price_is_modified = true;
      modified_execution_type_is_set = true;
      modified_execution_type_is_modified = true;
      modified_result_is_set = true;
      modified_result_is_modified = true;
      canmod_receipt_type_is_set = true;
      canmod_receipt_type_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      mod_submit_order_route_div_is_set = true;
      mod_submit_order_route_div_is_modified = true;
      modified_order_rev_is_set = true;
      modified_order_rev_is_modified = true;
      virtual_server_number_market_is_set = true;
      virtual_server_number_market_is_modified = true;
      notice_type_is_set = true;
      notice_type_is_modified = true;
      notice_number_is_set = true;
      notice_number_is_modified = true;
      modified_stop_order_price_is_set = true;
      modified_stop_order_price_is_modified = true;
      modified_w_limit_price_is_set = true;
      modified_w_limit_price_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostFotypeOrderClmdNotifyRow ) )
       return false;
    return fieldsEqual( (HostFotypeOrderClmdNotifyRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostFotypeOrderClmdNotifyRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostFotypeOrderClmdNotifyRow row )
  {
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
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( modified_quantity != row.getModifiedQuantity() )
      return false;
    if ( modified_limit_price != row.getModifiedLimitPrice() )
      return false;
    if ( modified_execution_type == null ) {
      if ( row.getModifiedExecutionType() != null )
        return false;
    } else if ( !modified_execution_type.equals( row.getModifiedExecutionType() ) ) {
        return false;
    }
    if ( modified_result == null ) {
      if ( row.getModifiedResult() != null )
        return false;
    } else if ( !modified_result.equals( row.getModifiedResult() ) ) {
        return false;
    }
    if ( canmod_receipt_type == null ) {
      if ( row.getCanmodReceiptType() != null )
        return false;
    } else if ( !canmod_receipt_type.equals( row.getCanmodReceiptType() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
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
    if ( mod_submit_order_route_div == null ) {
      if ( row.getModSubmitOrderRouteDiv() != null )
        return false;
    } else if ( !mod_submit_order_route_div.equals( row.getModSubmitOrderRouteDiv() ) ) {
        return false;
    }
    if ( modified_order_rev == null ) {
      if ( row.getModifiedOrderRev() != null )
        return false;
    } else if ( !modified_order_rev.equals( row.getModifiedOrderRev() ) ) {
        return false;
    }
    if ( virtual_server_number_market == null ) {
      if ( row.getVirtualServerNumberMarket() != null )
        return false;
    } else if ( !virtual_server_number_market.equals( row.getVirtualServerNumberMarket() ) ) {
        return false;
    }
    if ( notice_type == null ) {
      if ( row.getNoticeType() != null )
        return false;
    } else if ( !notice_type.equals( row.getNoticeType() ) ) {
        return false;
    }
    if ( notice_number == null ) {
      if ( !row.getNoticeNumberIsNull() )
        return false;
    } else if ( row.getNoticeNumberIsNull() || ( notice_number.longValue() != row.getNoticeNumber() ) ) {
        return false;
    }
    if ( modified_stop_order_price == null ) {
      if ( !row.getModifiedStopOrderPriceIsNull() )
        return false;
    } else if ( row.getModifiedStopOrderPriceIsNull() || ( modified_stop_order_price.doubleValue() != row.getModifiedStopOrderPrice() ) ) {
        return false;
    }
    if ( modified_w_limit_price == null ) {
      if ( !row.getModifiedWLimitPriceIsNull() )
        return false;
    } else if ( row.getModifiedWLimitPriceIsNull() || ( modified_w_limit_price.doubleValue() != row.getModifiedWLimitPrice() ) ) {
        return false;
    }
    if ( rowid == null ) {
      if ( row.getRowid() != null )
        return false;
    } else if ( !rowid.equals( row.getRowid() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + ((int) modified_quantity)
        + ((int) modified_limit_price)
        + (modified_execution_type!=null? modified_execution_type.hashCode(): 0) 
        + (modified_result!=null? modified_result.hashCode(): 0) 
        + (canmod_receipt_type!=null? canmod_receipt_type.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (mod_submit_order_route_div!=null? mod_submit_order_route_div.hashCode(): 0) 
        + (modified_order_rev!=null? modified_order_rev.hashCode(): 0) 
        + (virtual_server_number_market!=null? virtual_server_number_market.hashCode(): 0) 
        + (notice_type!=null? notice_type.hashCode(): 0) 
        + (notice_number!=null? notice_number.hashCode(): 0) 
        + (modified_stop_order_price!=null? modified_stop_order_price.hashCode(): 0) 
        + (modified_w_limit_price!=null? modified_w_limit_price.hashCode(): 0) 
        + (rowid!=null? rowid.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !request_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'request_code' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !order_request_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number' must be set before inserting.");
		if ( !modified_quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'modified_quantity' must be set before inserting.");
		if ( !modified_limit_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'modified_limit_price' must be set before inserting.");
		if ( !modified_execution_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'modified_execution_type' must be set before inserting.");
		if ( !modified_result_is_set )
			throw new IllegalArgumentException("non-nullable field 'modified_result' must be set before inserting.");
		if ( !canmod_receipt_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'canmod_receipt_type' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("request_code",request_code);
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		map.put("order_request_number",order_request_number);
		map.put("modified_quantity",new Double(modified_quantity));
		map.put("modified_limit_price",new Double(modified_limit_price));
		map.put("modified_execution_type",modified_execution_type);
		map.put("modified_result",modified_result);
		map.put("canmod_receipt_type",canmod_receipt_type);
		map.put("status",status);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( mod_submit_order_route_div != null )
			map.put("mod_submit_order_route_div",mod_submit_order_route_div);
		if ( modified_order_rev_is_set )
			map.put("modified_order_rev",modified_order_rev);
		if ( virtual_server_number_market != null )
			map.put("virtual_server_number_market",virtual_server_number_market);
		if ( notice_type != null )
			map.put("notice_type",notice_type);
		if ( notice_number != null )
			map.put("notice_number",notice_number);
		if ( modified_stop_order_price != null )
			map.put("modified_stop_order_price",modified_stop_order_price);
		if ( modified_w_limit_price != null )
			map.put("modified_w_limit_price",modified_w_limit_price);
		map.put("rowid",rowid);
		map.remove("rowid");
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
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
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( modified_quantity_is_modified )
			map.put("modified_quantity",new Double(modified_quantity));
		if ( modified_limit_price_is_modified )
			map.put("modified_limit_price",new Double(modified_limit_price));
		if ( modified_execution_type_is_modified )
			map.put("modified_execution_type",modified_execution_type);
		if ( modified_result_is_modified )
			map.put("modified_result",modified_result);
		if ( canmod_receipt_type_is_modified )
			map.put("canmod_receipt_type",canmod_receipt_type);
		if ( status_is_modified )
			map.put("status",status);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( mod_submit_order_route_div_is_modified )
			map.put("mod_submit_order_route_div",mod_submit_order_route_div);
		if ( modified_order_rev_is_modified )
			map.put("modified_order_rev",modified_order_rev);
		if ( virtual_server_number_market_is_modified )
			map.put("virtual_server_number_market",virtual_server_number_market);
		if ( notice_type_is_modified )
			map.put("notice_type",notice_type);
		if ( notice_number_is_modified )
			map.put("notice_number",notice_number);
		if ( modified_stop_order_price_is_modified )
			map.put("modified_stop_order_price",modified_stop_order_price);
		if ( modified_w_limit_price_is_modified )
			map.put("modified_w_limit_price",modified_w_limit_price);
		if (map.size() == 0) {
			if ( request_code_is_set )
				map.put("request_code",request_code);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			if ( order_request_number_is_set )
				map.put("order_request_number",order_request_number);
			if ( modified_quantity_is_set )
				map.put("modified_quantity",new Double(modified_quantity));
			if ( modified_limit_price_is_set )
				map.put("modified_limit_price",new Double(modified_limit_price));
			if ( modified_execution_type_is_set )
				map.put("modified_execution_type",modified_execution_type);
			if ( modified_result_is_set )
				map.put("modified_result",modified_result);
			if ( canmod_receipt_type_is_set )
				map.put("canmod_receipt_type",canmod_receipt_type);
			if ( status_is_set )
				map.put("status",status);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("mod_submit_order_route_div",mod_submit_order_route_div);
			if ( modified_order_rev_is_set )
				map.put("modified_order_rev",modified_order_rev);
			map.put("virtual_server_number_market",virtual_server_number_market);
			map.put("notice_type",notice_type);
			map.put("notice_number",notice_number);
			map.put("modified_stop_order_price",modified_stop_order_price);
			map.put("modified_w_limit_price",modified_w_limit_price);
		}
		return map;
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
   * <em>order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRequestNumber()
  {
    return order_request_number;
  }


  /** 
   * <em>order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsSet() {
    return order_request_number_is_set;
  }


  /** 
   * <em>order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsModified() {
    return order_request_number_is_modified;
  }


  /** 
   * <em>modified_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getModifiedQuantity()
  {
    return modified_quantity;
  }


  /** 
   * <em>modified_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedQuantityIsSet() {
    return modified_quantity_is_set;
  }


  /** 
   * <em>modified_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedQuantityIsModified() {
    return modified_quantity_is_modified;
  }


  /** 
   * <em>modified_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getModifiedLimitPrice()
  {
    return modified_limit_price;
  }


  /** 
   * <em>modified_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedLimitPriceIsSet() {
    return modified_limit_price_is_set;
  }


  /** 
   * <em>modified_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedLimitPriceIsModified() {
    return modified_limit_price_is_modified;
  }


  /** 
   * <em>modified_execution_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getModifiedExecutionType()
  {
    return modified_execution_type;
  }


  /** 
   * <em>modified_execution_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedExecutionTypeIsSet() {
    return modified_execution_type_is_set;
  }


  /** 
   * <em>modified_execution_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedExecutionTypeIsModified() {
    return modified_execution_type_is_modified;
  }


  /** 
   * <em>modified_result</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getModifiedResult()
  {
    return modified_result;
  }


  /** 
   * <em>modified_result</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedResultIsSet() {
    return modified_result_is_set;
  }


  /** 
   * <em>modified_result</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedResultIsModified() {
    return modified_result_is_modified;
  }


  /** 
   * <em>canmod_receipt_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCanmodReceiptType()
  {
    return canmod_receipt_type;
  }


  /** 
   * <em>canmod_receipt_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCanmodReceiptTypeIsSet() {
    return canmod_receipt_type_is_set;
  }


  /** 
   * <em>canmod_receipt_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCanmodReceiptTypeIsModified() {
    return canmod_receipt_type_is_modified;
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
   * <em>mod_submit_order_route_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getModSubmitOrderRouteDiv()
  {
    return mod_submit_order_route_div;
  }


  /** 
   * <em>mod_submit_order_route_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModSubmitOrderRouteDivIsSet() {
    return mod_submit_order_route_div_is_set;
  }


  /** 
   * <em>mod_submit_order_route_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModSubmitOrderRouteDivIsModified() {
    return mod_submit_order_route_div_is_modified;
  }


  /** 
   * <em>modified_order_rev</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getModifiedOrderRev()
  {
    return modified_order_rev;
  }


  /** 
   * <em>modified_order_rev</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedOrderRevIsSet() {
    return modified_order_rev_is_set;
  }


  /** 
   * <em>modified_order_rev</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedOrderRevIsModified() {
    return modified_order_rev_is_modified;
  }


  /** 
   * <em>virtual_server_number_market</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getVirtualServerNumberMarket()
  {
    return virtual_server_number_market;
  }


  /** 
   * <em>virtual_server_number_market</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVirtualServerNumberMarketIsSet() {
    return virtual_server_number_market_is_set;
  }


  /** 
   * <em>virtual_server_number_market</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVirtualServerNumberMarketIsModified() {
    return virtual_server_number_market_is_modified;
  }


  /** 
   * <em>notice_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNoticeType()
  {
    return notice_type;
  }


  /** 
   * <em>notice_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoticeTypeIsSet() {
    return notice_type_is_set;
  }


  /** 
   * <em>notice_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoticeTypeIsModified() {
    return notice_type_is_modified;
  }


  /** 
   * <em>notice_number</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getNoticeNumber()
  {
    return ( notice_number==null? ((long)0): notice_number.longValue() );
  }


  /** 
   * <em>notice_number</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNoticeNumberIsNull()
  {
    return notice_number == null;
  }


  /** 
   * <em>notice_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoticeNumberIsSet() {
    return notice_number_is_set;
  }


  /** 
   * <em>notice_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoticeNumberIsModified() {
    return notice_number_is_modified;
  }


  /** 
   * <em>modified_stop_order_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getModifiedStopOrderPrice()
  {
    return ( modified_stop_order_price==null? ((double)0): modified_stop_order_price.doubleValue() );
  }


  /** 
   * <em>modified_stop_order_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getModifiedStopOrderPriceIsNull()
  {
    return modified_stop_order_price == null;
  }


  /** 
   * <em>modified_stop_order_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedStopOrderPriceIsSet() {
    return modified_stop_order_price_is_set;
  }


  /** 
   * <em>modified_stop_order_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedStopOrderPriceIsModified() {
    return modified_stop_order_price_is_modified;
  }


  /** 
   * <em>modified_w_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getModifiedWLimitPrice()
  {
    return ( modified_w_limit_price==null? ((double)0): modified_w_limit_price.doubleValue() );
  }


  /** 
   * <em>modified_w_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getModifiedWLimitPriceIsNull()
  {
    return modified_w_limit_price == null;
  }


  /** 
   * <em>modified_w_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedWLimitPriceIsSet() {
    return modified_w_limit_price_is_set;
  }


  /** 
   * <em>modified_w_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedWLimitPriceIsModified() {
    return modified_w_limit_price_is_modified;
  }


  /** 
   * <em>rowid</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRowid()
  {
    return rowid;
  }


  /** 
   * <em>rowid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRowidIsSet() {
    return rowid_is_set;
  }


  /** 
   * <em>rowid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRowidIsModified() {
    return rowid_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new HostFotypeOrderClmdNotifyPK(rowid);
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
   * <em>order_request_number</em>カラムの値を設定します。 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setOrderRequestNumber( String p_orderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number = p_orderRequestNumber;
    order_request_number_is_set = true;
    order_request_number_is_modified = true;
  }


  /** 
   * <em>modified_quantity</em>カラムの値を設定します。 
   *
   * @@param p_modifiedQuantity <em>modified_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setModifiedQuantity( double p_modifiedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_quantity = p_modifiedQuantity;
    modified_quantity_is_set = true;
    modified_quantity_is_modified = true;
  }


  /** 
   * <em>modified_limit_price</em>カラムの値を設定します。 
   *
   * @@param p_modifiedLimitPrice <em>modified_limit_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setModifiedLimitPrice( double p_modifiedLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_limit_price = p_modifiedLimitPrice;
    modified_limit_price_is_set = true;
    modified_limit_price_is_modified = true;
  }


  /** 
   * <em>modified_execution_type</em>カラムの値を設定します。 
   *
   * @@param p_modifiedExecutionType <em>modified_execution_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setModifiedExecutionType( String p_modifiedExecutionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_execution_type = p_modifiedExecutionType;
    modified_execution_type_is_set = true;
    modified_execution_type_is_modified = true;
  }


  /** 
   * <em>modified_result</em>カラムの値を設定します。 
   *
   * @@param p_modifiedResult <em>modified_result</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setModifiedResult( String p_modifiedResult )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_result = p_modifiedResult;
    modified_result_is_set = true;
    modified_result_is_modified = true;
  }


  /** 
   * <em>canmod_receipt_type</em>カラムの値を設定します。 
   *
   * @@param p_canmodReceiptType <em>canmod_receipt_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCanmodReceiptType( String p_canmodReceiptType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    canmod_receipt_type = p_canmodReceiptType;
    canmod_receipt_type_is_set = true;
    canmod_receipt_type_is_modified = true;
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
   * <em>mod_submit_order_route_div</em>カラムの値を設定します。 
   *
   * @@param p_modSubmitOrderRouteDiv <em>mod_submit_order_route_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setModSubmitOrderRouteDiv( String p_modSubmitOrderRouteDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mod_submit_order_route_div = p_modSubmitOrderRouteDiv;
    mod_submit_order_route_div_is_set = true;
    mod_submit_order_route_div_is_modified = true;
  }


  /** 
   * <em>modified_order_rev</em>カラムの値を設定します。 
   *
   * @@param p_modifiedOrderRev <em>modified_order_rev</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setModifiedOrderRev( String p_modifiedOrderRev )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_order_rev = p_modifiedOrderRev;
    modified_order_rev_is_set = true;
    modified_order_rev_is_modified = true;
  }


  /** 
   * <em>virtual_server_number_market</em>カラムの値を設定します。 
   *
   * @@param p_virtualServerNumberMarket <em>virtual_server_number_market</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setVirtualServerNumberMarket( String p_virtualServerNumberMarket )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    virtual_server_number_market = p_virtualServerNumberMarket;
    virtual_server_number_market_is_set = true;
    virtual_server_number_market_is_modified = true;
  }


  /** 
   * <em>notice_type</em>カラムの値を設定します。 
   *
   * @@param p_noticeType <em>notice_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setNoticeType( String p_noticeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    notice_type = p_noticeType;
    notice_type_is_set = true;
    notice_type_is_modified = true;
  }


  /** 
   * <em>notice_number</em>カラムの値を設定します。 
   *
   * @@param p_noticeNumber <em>notice_number</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setNoticeNumber( long p_noticeNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    notice_number = new Long(p_noticeNumber);
    notice_number_is_set = true;
    notice_number_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>notice_number</em>カラムに値を設定します。 
   */
  public final void setNoticeNumber( Long p_noticeNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    notice_number = p_noticeNumber;
    notice_number_is_set = true;
    notice_number_is_modified = true;
  }


  /** 
   * <em>modified_stop_order_price</em>カラムの値を設定します。 
   *
   * @@param p_modifiedStopOrderPrice <em>modified_stop_order_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setModifiedStopOrderPrice( double p_modifiedStopOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_stop_order_price = new Double(p_modifiedStopOrderPrice);
    modified_stop_order_price_is_set = true;
    modified_stop_order_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>modified_stop_order_price</em>カラムに値を設定します。 
   */
  public final void setModifiedStopOrderPrice( Double p_modifiedStopOrderPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    modified_stop_order_price = p_modifiedStopOrderPrice;
    modified_stop_order_price_is_set = true;
    modified_stop_order_price_is_modified = true;
  }


  /** 
   * <em>modified_w_limit_price</em>カラムの値を設定します。 
   *
   * @@param p_modifiedWLimitPrice <em>modified_w_limit_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setModifiedWLimitPrice( double p_modifiedWLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_w_limit_price = new Double(p_modifiedWLimitPrice);
    modified_w_limit_price_is_set = true;
    modified_w_limit_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>modified_w_limit_price</em>カラムに値を設定します。 
   */
  public final void setModifiedWLimitPrice( Double p_modifiedWLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    modified_w_limit_price = p_modifiedWLimitPrice;
    modified_w_limit_price_is_set = true;
    modified_w_limit_price_is_modified = true;
  }


  /** 
   * <em>rowid</em>カラムの値を設定します。 
   *
   * @@param p_rowid <em>rowid</em>カラムの値をあらわす20桁以下のString値で、その精度は20桁まで
   */
  public final void setRowid( String p_rowid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rowid = p_rowid;
    rowid_is_set = true;
    rowid_is_modified = true;
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
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("canmod_receipt_type") ) {
                    return this.canmod_receipt_type;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
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
                if ( name.equals("modified_quantity") ) {
                    return new Double( modified_quantity );
                }
                else if ( name.equals("modified_limit_price") ) {
                    return new Double( modified_limit_price );
                }
                else if ( name.equals("modified_execution_type") ) {
                    return this.modified_execution_type;
                }
                else if ( name.equals("modified_result") ) {
                    return this.modified_result;
                }
                else if ( name.equals("mod_submit_order_route_div") ) {
                    return this.mod_submit_order_route_div;
                }
                else if ( name.equals("modified_order_rev") ) {
                    return this.modified_order_rev;
                }
                else if ( name.equals("modified_stop_order_price") ) {
                    return this.modified_stop_order_price;
                }
                else if ( name.equals("modified_w_limit_price") ) {
                    return this.modified_w_limit_price;
                }
                break;
            case 'n':
                if ( name.equals("notice_type") ) {
                    return this.notice_type;
                }
                else if ( name.equals("notice_number") ) {
                    return this.notice_number;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("rowid") ) {
                    return this.rowid;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                break;
            case 'v':
                if ( name.equals("virtual_server_number_market") ) {
                    return this.virtual_server_number_market;
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
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
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
                if ( name.equals("canmod_receipt_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'canmod_receipt_type' must be String: '"+value+"' is not." );
                    this.canmod_receipt_type = (String) value;
                    if (this.canmod_receipt_type_is_set)
                        this.canmod_receipt_type_is_modified = true;
                    this.canmod_receipt_type_is_set = true;
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
                if ( name.equals("modified_quantity") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'modified_quantity' must be Double: '"+value+"' is not." );
                    this.modified_quantity = ((Double) value).doubleValue();
                    if (this.modified_quantity_is_set)
                        this.modified_quantity_is_modified = true;
                    this.modified_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("modified_limit_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'modified_limit_price' must be Double: '"+value+"' is not." );
                    this.modified_limit_price = ((Double) value).doubleValue();
                    if (this.modified_limit_price_is_set)
                        this.modified_limit_price_is_modified = true;
                    this.modified_limit_price_is_set = true;
                    return;
                }
                else if ( name.equals("modified_execution_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'modified_execution_type' must be String: '"+value+"' is not." );
                    this.modified_execution_type = (String) value;
                    if (this.modified_execution_type_is_set)
                        this.modified_execution_type_is_modified = true;
                    this.modified_execution_type_is_set = true;
                    return;
                }
                else if ( name.equals("modified_result") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'modified_result' must be String: '"+value+"' is not." );
                    this.modified_result = (String) value;
                    if (this.modified_result_is_set)
                        this.modified_result_is_modified = true;
                    this.modified_result_is_set = true;
                    return;
                }
                else if ( name.equals("mod_submit_order_route_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mod_submit_order_route_div' must be String: '"+value+"' is not." );
                    this.mod_submit_order_route_div = (String) value;
                    if (this.mod_submit_order_route_div_is_set)
                        this.mod_submit_order_route_div_is_modified = true;
                    this.mod_submit_order_route_div_is_set = true;
                    return;
                }
                else if ( name.equals("modified_order_rev") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'modified_order_rev' must be String: '"+value+"' is not." );
                    this.modified_order_rev = (String) value;
                    if (this.modified_order_rev_is_set)
                        this.modified_order_rev_is_modified = true;
                    this.modified_order_rev_is_set = true;
                    return;
                }
                else if ( name.equals("modified_stop_order_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'modified_stop_order_price' must be Double: '"+value+"' is not." );
                    this.modified_stop_order_price = (Double) value;
                    if (this.modified_stop_order_price_is_set)
                        this.modified_stop_order_price_is_modified = true;
                    this.modified_stop_order_price_is_set = true;
                    return;
                }
                else if ( name.equals("modified_w_limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'modified_w_limit_price' must be Double: '"+value+"' is not." );
                    this.modified_w_limit_price = (Double) value;
                    if (this.modified_w_limit_price_is_set)
                        this.modified_w_limit_price_is_modified = true;
                    this.modified_w_limit_price_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("notice_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'notice_type' must be String: '"+value+"' is not." );
                    this.notice_type = (String) value;
                    if (this.notice_type_is_set)
                        this.notice_type_is_modified = true;
                    this.notice_type_is_set = true;
                    return;
                }
                else if ( name.equals("notice_number") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'notice_number' must be Long: '"+value+"' is not." );
                    this.notice_number = (Long) value;
                    if (this.notice_number_is_set)
                        this.notice_number_is_modified = true;
                    this.notice_number_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
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
                else if ( name.equals("rowid") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rowid' must be String: '"+value+"' is not." );
                    this.rowid = (String) value;
                    if (this.rowid_is_set)
                        this.rowid_is_modified = true;
                    this.rowid_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
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
            case 'v':
                if ( name.equals("virtual_server_number_market") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'virtual_server_number_market' must be String: '"+value+"' is not." );
                    this.virtual_server_number_market = (String) value;
                    if (this.virtual_server_number_market_is_set)
                        this.virtual_server_number_market_is_modified = true;
                    this.virtual_server_number_market_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
