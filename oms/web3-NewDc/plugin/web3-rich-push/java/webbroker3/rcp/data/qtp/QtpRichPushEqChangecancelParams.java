head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.26.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	QtpRichPushEqChangecancelParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data.qtp;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * qtp_rich_push_eq_changecancelテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link QtpRichPushEqChangecancelRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link QtpRichPushEqChangecancelParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link QtpRichPushEqChangecancelParams}が{@@link QtpRichPushEqChangecancelRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see QtpRichPushEqChangecancelPK 
 * @@see QtpRichPushEqChangecancelRow 
 */
public class QtpRichPushEqChangecancelParams extends Params implements QtpRichPushEqChangecancelRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "qtp_rich_push_eq_changecancel";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = QtpRichPushEqChangecancelRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return QtpRichPushEqChangecancelRow.TYPE;
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
   * <em>modified_quantity</em>カラムの値 
   */
  public  Double  modified_quantity;

  /** 
   * <em>modified_limit_price</em>カラムの値 
   */
  public  Double  modified_limit_price;

  /** 
   * <em>modified_execution_type</em>カラムの値 
   */
  public  String  modified_execution_type;

  /** 
   * <em>modified_price_condition_type</em>カラムの値 
   */
  public  String  modified_price_condition_type;

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
   * <em>order_id</em>カラムの値 
   */
  public  long  order_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>submit_order_route_div</em>カラムの値 
   */
  public  String  submit_order_route_div;

  /** 
   * <em>order_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum  order_type;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>product_name</em>カラムの値 
   */
  public  String  product_name;

  /** 
   * <em>market_code</em>カラムの値 
   */
  public  String  market_code;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>sid</em>カラムの値 
   */
  public  String  sid;

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
  private boolean modified_quantity_is_set = false;
  private boolean modified_quantity_is_modified = false;
  private boolean modified_limit_price_is_set = false;
  private boolean modified_limit_price_is_modified = false;
  private boolean modified_execution_type_is_set = false;
  private boolean modified_execution_type_is_modified = false;
  private boolean modified_price_condition_type_is_set = false;
  private boolean modified_price_condition_type_is_modified = false;
  private boolean modified_result_is_set = false;
  private boolean modified_result_is_modified = false;
  private boolean canmod_receipt_type_is_set = false;
  private boolean canmod_receipt_type_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean submit_order_route_div_is_set = false;
  private boolean submit_order_route_div_is_modified = false;
  private boolean order_type_is_set = false;
  private boolean order_type_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean product_name_is_set = false;
  private boolean product_name_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean sid_is_set = false;
  private boolean sid_is_modified = false;
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
      + "," + "modified_quantity=" +modified_quantity
      + "," + "modified_limit_price=" +modified_limit_price
      + "," + "modified_execution_type=" +modified_execution_type
      + "," + "modified_price_condition_type=" +modified_price_condition_type
      + "," + "modified_result=" +modified_result
      + "," + "canmod_receipt_type=" +canmod_receipt_type
      + "," + "status=" +status
      + "," + "order_id=" +order_id
      + "," + "account_id=" +account_id
      + "," + "submit_order_route_div=" +submit_order_route_div
      + "," + "order_type=" +order_type
      + "," + "product_code=" +product_code
      + "," + "product_name=" +product_name
      + "," + "market_code=" +market_code
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "sid=" +sid
      + "}";
  }


  /** 
   * 値が未設定のQtpRichPushEqChangecancelParamsオブジェクトを作成します。 
   */
  public QtpRichPushEqChangecancelParams() {
    serlnum_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のQtpRichPushEqChangecancelRowオブジェクトの値を利用してQtpRichPushEqChangecancelParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するQtpRichPushEqChangecancelRowオブジェクト 
   */
  public QtpRichPushEqChangecancelParams( QtpRichPushEqChangecancelRow p_row )
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
    if ( !p_row.getModifiedQuantityIsNull() )
      modified_quantity = new Double( p_row.getModifiedQuantity() );
    modified_quantity_is_set = p_row.getModifiedQuantityIsSet();
    modified_quantity_is_modified = p_row.getModifiedQuantityIsModified();
    if ( !p_row.getModifiedLimitPriceIsNull() )
      modified_limit_price = new Double( p_row.getModifiedLimitPrice() );
    modified_limit_price_is_set = p_row.getModifiedLimitPriceIsSet();
    modified_limit_price_is_modified = p_row.getModifiedLimitPriceIsModified();
    modified_execution_type = p_row.getModifiedExecutionType();
    modified_execution_type_is_set = p_row.getModifiedExecutionTypeIsSet();
    modified_execution_type_is_modified = p_row.getModifiedExecutionTypeIsModified();
    modified_price_condition_type = p_row.getModifiedPriceConditionType();
    modified_price_condition_type_is_set = p_row.getModifiedPriceConditionTypeIsSet();
    modified_price_condition_type_is_modified = p_row.getModifiedPriceConditionTypeIsModified();
    modified_result = p_row.getModifiedResult();
    modified_result_is_set = p_row.getModifiedResultIsSet();
    modified_result_is_modified = p_row.getModifiedResultIsModified();
    canmod_receipt_type = p_row.getCanmodReceiptType();
    canmod_receipt_type_is_set = p_row.getCanmodReceiptTypeIsSet();
    canmod_receipt_type_is_modified = p_row.getCanmodReceiptTypeIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    submit_order_route_div = p_row.getSubmitOrderRouteDiv();
    submit_order_route_div_is_set = p_row.getSubmitOrderRouteDivIsSet();
    submit_order_route_div_is_modified = p_row.getSubmitOrderRouteDivIsModified();
    order_type = p_row.getOrderType();
    order_type_is_set = p_row.getOrderTypeIsSet();
    order_type_is_modified = p_row.getOrderTypeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    product_name = p_row.getProductName();
    product_name_is_set = p_row.getProductNameIsSet();
    product_name_is_modified = p_row.getProductNameIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    sid = p_row.getSid();
    sid_is_set = p_row.getSidIsSet();
    sid_is_modified = p_row.getSidIsModified();
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
      modified_quantity_is_set = true;
      modified_quantity_is_modified = true;
      modified_limit_price_is_set = true;
      modified_limit_price_is_modified = true;
      modified_execution_type_is_set = true;
      modified_execution_type_is_modified = true;
      modified_price_condition_type_is_set = true;
      modified_price_condition_type_is_modified = true;
      modified_result_is_set = true;
      modified_result_is_modified = true;
      canmod_receipt_type_is_set = true;
      canmod_receipt_type_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      submit_order_route_div_is_set = true;
      submit_order_route_div_is_modified = true;
      order_type_is_set = true;
      order_type_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      product_name_is_set = true;
      product_name_is_modified = true;
      market_code_is_set = true;
      market_code_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      sid_is_set = true;
      sid_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof QtpRichPushEqChangecancelRow ) )
       return false;
    return fieldsEqual( (QtpRichPushEqChangecancelRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のQtpRichPushEqChangecancelRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( QtpRichPushEqChangecancelRow row )
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
    if ( modified_quantity == null ) {
      if ( !row.getModifiedQuantityIsNull() )
        return false;
    } else if ( row.getModifiedQuantityIsNull() || ( modified_quantity.doubleValue() != row.getModifiedQuantity() ) ) {
        return false;
    }
    if ( modified_limit_price == null ) {
      if ( !row.getModifiedLimitPriceIsNull() )
        return false;
    } else if ( row.getModifiedLimitPriceIsNull() || ( modified_limit_price.doubleValue() != row.getModifiedLimitPrice() ) ) {
        return false;
    }
    if ( modified_execution_type == null ) {
      if ( row.getModifiedExecutionType() != null )
        return false;
    } else if ( !modified_execution_type.equals( row.getModifiedExecutionType() ) ) {
        return false;
    }
    if ( modified_price_condition_type == null ) {
      if ( row.getModifiedPriceConditionType() != null )
        return false;
    } else if ( !modified_price_condition_type.equals( row.getModifiedPriceConditionType() ) ) {
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
    if ( order_id != row.getOrderId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( submit_order_route_div == null ) {
      if ( row.getSubmitOrderRouteDiv() != null )
        return false;
    } else if ( !submit_order_route_div.equals( row.getSubmitOrderRouteDiv() ) ) {
        return false;
    }
    if ( order_type == null ) {
      if ( row.getOrderType() != null )
        return false;
    } else if ( !order_type.equals( row.getOrderType() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( product_name == null ) {
      if ( row.getProductName() != null )
        return false;
    } else if ( !product_name.equals( row.getProductName() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
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
    if ( sid == null ) {
      if ( row.getSid() != null )
        return false;
    } else if ( !sid.equals( row.getSid() ) ) {
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
        + (modified_quantity!=null? modified_quantity.hashCode(): 0) 
        + (modified_limit_price!=null? modified_limit_price.hashCode(): 0) 
        + (modified_execution_type!=null? modified_execution_type.hashCode(): 0) 
        + (modified_price_condition_type!=null? modified_price_condition_type.hashCode(): 0) 
        + (modified_result!=null? modified_result.hashCode(): 0) 
        + (canmod_receipt_type!=null? canmod_receipt_type.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + ((int) order_id)
        + ((int) account_id)
        + (submit_order_route_div!=null? submit_order_route_div.hashCode(): 0) 
        + (order_type!=null? order_type.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (product_name!=null? product_name.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (sid!=null? sid.hashCode(): 0) 
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
		if ( !order_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_id' must be set before inserting.");
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !order_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_type' must be set before inserting.");
		if ( !product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_code' must be set before inserting.");
		if ( !market_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_code' must be set before inserting.");
		if ( !sid_is_set )
			throw new IllegalArgumentException("non-nullable field 'sid' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("tpk",tpk);
		map.put("type",type);
		if ( request_code != null )
			map.put("request_code",request_code);
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		if ( modified_quantity != null )
			map.put("modified_quantity",modified_quantity);
		if ( modified_limit_price != null )
			map.put("modified_limit_price",modified_limit_price);
		if ( modified_execution_type != null )
			map.put("modified_execution_type",modified_execution_type);
		if ( modified_price_condition_type != null )
			map.put("modified_price_condition_type",modified_price_condition_type);
		if ( modified_result != null )
			map.put("modified_result",modified_result);
		if ( canmod_receipt_type != null )
			map.put("canmod_receipt_type",canmod_receipt_type);
		if ( status != null )
			map.put("status",status);
		map.put("order_id",new Long(order_id));
		map.put("account_id",new Long(account_id));
		if ( submit_order_route_div != null )
			map.put("submit_order_route_div",submit_order_route_div);
		map.put("order_type",order_type);
		map.put("product_code",product_code);
		if ( product_name != null )
			map.put("product_name",product_name);
		map.put("market_code",market_code);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("sid",sid);
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
		if ( modified_quantity_is_modified )
			map.put("modified_quantity",modified_quantity);
		if ( modified_limit_price_is_modified )
			map.put("modified_limit_price",modified_limit_price);
		if ( modified_execution_type_is_modified )
			map.put("modified_execution_type",modified_execution_type);
		if ( modified_price_condition_type_is_modified )
			map.put("modified_price_condition_type",modified_price_condition_type);
		if ( modified_result_is_modified )
			map.put("modified_result",modified_result);
		if ( canmod_receipt_type_is_modified )
			map.put("canmod_receipt_type",canmod_receipt_type);
		if ( status_is_modified )
			map.put("status",status);
		if ( order_id_is_modified )
			map.put("order_id",new Long(order_id));
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( submit_order_route_div_is_modified )
			map.put("submit_order_route_div",submit_order_route_div);
		if ( order_type_is_modified )
			map.put("order_type",order_type);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( product_name_is_modified )
			map.put("product_name",product_name);
		if ( market_code_is_modified )
			map.put("market_code",market_code);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( sid_is_modified )
			map.put("sid",sid);
		if (map.size() == 0) {
			if ( tpk_is_set )
				map.put("tpk",tpk);
			if ( type_is_set )
				map.put("type",type);
			map.put("request_code",request_code);
			map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			map.put("modified_quantity",modified_quantity);
			map.put("modified_limit_price",modified_limit_price);
			map.put("modified_execution_type",modified_execution_type);
			map.put("modified_price_condition_type",modified_price_condition_type);
			map.put("modified_result",modified_result);
			map.put("canmod_receipt_type",canmod_receipt_type);
			map.put("status",status);
			if ( order_id_is_set )
				map.put("order_id",new Long(order_id));
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			map.put("submit_order_route_div",submit_order_route_div);
			if ( order_type_is_set )
				map.put("order_type",order_type);
			if ( product_code_is_set )
				map.put("product_code",product_code);
			map.put("product_name",product_name);
			if ( market_code_is_set )
				map.put("market_code",market_code);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( sid_is_set )
				map.put("sid",sid);
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
   * <em>modified_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getModifiedQuantity()
  {
    return ( modified_quantity==null? ((double)0): modified_quantity.doubleValue() );
  }


  /** 
   * <em>modified_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getModifiedQuantityIsNull()
  {
    return modified_quantity == null;
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
    return ( modified_limit_price==null? ((double)0): modified_limit_price.doubleValue() );
  }


  /** 
   * <em>modified_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getModifiedLimitPriceIsNull()
  {
    return modified_limit_price == null;
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
   * <em>modified_price_condition_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getModifiedPriceConditionType()
  {
    return modified_price_condition_type;
  }


  /** 
   * <em>modified_price_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedPriceConditionTypeIsSet() {
    return modified_price_condition_type_is_set;
  }


  /** 
   * <em>modified_price_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getModifiedPriceConditionTypeIsModified() {
    return modified_price_condition_type_is_modified;
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
   * <em>submit_order_route_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSubmitOrderRouteDiv()
  {
    return submit_order_route_div;
  }


  /** 
   * <em>submit_order_route_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubmitOrderRouteDivIsSet() {
    return submit_order_route_div_is_set;
  }


  /** 
   * <em>submit_order_route_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubmitOrderRouteDivIsModified() {
    return submit_order_route_div_is_modified;
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
   * <em>product_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductName()
  {
    return product_name;
  }


  /** 
   * <em>product_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductNameIsSet() {
    return product_name_is_set;
  }


  /** 
   * <em>product_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductNameIsModified() {
    return product_name_is_modified;
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
   * <em>sid</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSid()
  {
    return sid;
  }


  /** 
   * <em>sid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSidIsSet() {
    return sid_is_set;
  }


  /** 
   * <em>sid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSidIsModified() {
    return sid_is_modified;
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
    return new QtpRichPushEqChangecancelPK(serlnum);
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
   * <em>modified_quantity</em>カラムの値を設定します。 
   *
   * @@param p_modifiedQuantity <em>modified_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setModifiedQuantity( double p_modifiedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_quantity = new Double(p_modifiedQuantity);
    modified_quantity_is_set = true;
    modified_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>modified_quantity</em>カラムに値を設定します。 
   */
  public final void setModifiedQuantity( Double p_modifiedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    modified_limit_price = new Double(p_modifiedLimitPrice);
    modified_limit_price_is_set = true;
    modified_limit_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>modified_limit_price</em>カラムに値を設定します。 
   */
  public final void setModifiedLimitPrice( Double p_modifiedLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
   * <em>modified_price_condition_type</em>カラムの値を設定します。 
   *
   * @@param p_modifiedPriceConditionType <em>modified_price_condition_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setModifiedPriceConditionType( String p_modifiedPriceConditionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    modified_price_condition_type = p_modifiedPriceConditionType;
    modified_price_condition_type_is_set = true;
    modified_price_condition_type_is_modified = true;
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
   * <em>submit_order_route_div</em>カラムの値を設定します。 
   *
   * @@param p_submitOrderRouteDiv <em>submit_order_route_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSubmitOrderRouteDiv( String p_submitOrderRouteDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    submit_order_route_div = p_submitOrderRouteDiv;
    submit_order_route_div_is_set = true;
    submit_order_route_div_is_modified = true;
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
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>product_name</em>カラムの値を設定します。 
   *
   * @@param p_productName <em>product_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setProductName( String p_productName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_name = p_productName;
    product_name_is_set = true;
    product_name_is_modified = true;
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
   * <em>sid</em>カラムの値を設定します。 
   *
   * @@param p_sid <em>sid</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setSid( String p_sid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sid = p_sid;
    sid_is_set = true;
    sid_is_modified = true;
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
                    return this.modified_quantity;
                }
                else if ( name.equals("modified_limit_price") ) {
                    return this.modified_limit_price;
                }
                else if ( name.equals("modified_execution_type") ) {
                    return this.modified_execution_type;
                }
                else if ( name.equals("modified_price_condition_type") ) {
                    return this.modified_price_condition_type;
                }
                else if ( name.equals("modified_result") ) {
                    return this.modified_result;
                }
                else if ( name.equals("market_code") ) {
                    return this.market_code;
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
                else if ( name.equals("product_name") ) {
                    return this.product_name;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    return this.status;
                }
                else if ( name.equals("submit_order_route_div") ) {
                    return this.submit_order_route_div;
                }
                else if ( name.equals("sid") ) {
                    return this.sid;
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
                    if ( value != null )
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
                    if ( value != null )
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
                    if ( value != null )
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
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'modified_quantity' must be Double: '"+value+"' is not." );
                    this.modified_quantity = (Double) value;
                    if (this.modified_quantity_is_set)
                        this.modified_quantity_is_modified = true;
                    this.modified_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("modified_limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'modified_limit_price' must be Double: '"+value+"' is not." );
                    this.modified_limit_price = (Double) value;
                    if (this.modified_limit_price_is_set)
                        this.modified_limit_price_is_modified = true;
                    this.modified_limit_price_is_set = true;
                    return;
                }
                else if ( name.equals("modified_execution_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'modified_execution_type' must be String: '"+value+"' is not." );
                    this.modified_execution_type = (String) value;
                    if (this.modified_execution_type_is_set)
                        this.modified_execution_type_is_modified = true;
                    this.modified_execution_type_is_set = true;
                    return;
                }
                else if ( name.equals("modified_price_condition_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'modified_price_condition_type' must be String: '"+value+"' is not." );
                    this.modified_price_condition_type = (String) value;
                    if (this.modified_price_condition_type_is_set)
                        this.modified_price_condition_type_is_modified = true;
                    this.modified_price_condition_type_is_set = true;
                    return;
                }
                else if ( name.equals("modified_result") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'modified_result' must be String: '"+value+"' is not." );
                    this.modified_result = (String) value;
                    if (this.modified_result_is_set)
                        this.modified_result_is_modified = true;
                    this.modified_result_is_set = true;
                    return;
                }
                else if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
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
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                else if ( name.equals("product_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_name' must be String: '"+value+"' is not." );
                    this.product_name = (String) value;
                    if (this.product_name_is_set)
                        this.product_name_is_modified = true;
                    this.product_name_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_code' must be String: '"+value+"' is not." );
                    this.request_code = (String) value;
                    if (this.request_code_is_set)
                        this.request_code_is_modified = true;
                    this.request_code_is_set = true;
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
                else if ( name.equals("submit_order_route_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'submit_order_route_div' must be String: '"+value+"' is not." );
                    this.submit_order_route_div = (String) value;
                    if (this.submit_order_route_div_is_set)
                        this.submit_order_route_div_is_modified = true;
                    this.submit_order_route_div_is_set = true;
                    return;
                }
                else if ( name.equals("sid") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sid' must be String: '"+value+"' is not." );
                    this.sid = (String) value;
                    if (this.sid_is_set)
                        this.sid_is_modified = true;
                    this.sid_is_set = true;
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
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
